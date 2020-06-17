package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class UserKJPBean implements IUserPegawai {

	static Logger myLog = Logger.getLogger(ekptg.model.utils.UserKJPBean.class);
	//public UserKJPBean() {	}
	/**
	 * 13/06/2020 Dibuat oleh Mohamad Rosli 
	 * Senarai emel mengikut role KJP
	 * 
	 * */
	public List<Map<String,String>> getPenggunaMengikutRole(String role,String idKementerian) throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List<Map<String,String>> listPengunaByRole = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();	
			sql = " SELECT U.USER_NAME,,NVL(UK.EMEL,UI.EMEL) EMEL FROM USERS U, USERS_INTERNAL UI, USERS_KEMENTERIAN UK "
					+ " WHERE U.USER_ID = UI.USER_ID "
					+ " AND U.USER_ID = UK.USER_ID "
					+ " AND UI.ID_JAWATAN = '"+role+"' "
					+ " AND UI.FLAG_AKTIF = '1' " 
					+ "";
			
		    if(idKementerian!=null)
		    	sql += " AND UK.ID_KEMENTERIAN = '"+idKementerian+"' ";
		    
		    sql +=	" ORDER BY U.USER_NAME ";				
			
			myLog.info("getPenggunaMengikutRole :sql="+ sql);			
			rs = stmt.executeQuery(sql);
			listPengunaByRole = Collections.synchronizedList(new ArrayList<Map<String,String>>());
			Map<String,String> h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap<String,String>());
				bil++;
				h.put("bil",String.valueOf(bil));
				h.put("user",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("emel",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				listPengunaByRole.add(h);
				
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPengunaByRole;

	}
	
	public Vector<Hashtable<String, String>> getSenaraiPegawai(
		int idNegeri,String idUnit,String tahun) throws Exception{
		Db db = null;
		Vector<Hashtable<String, String>> lists = new Vector<Hashtable<String, String>>();
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			String sql = " " +
				"SELECT DISTINCT PE.ID_JKPTG,PE.ID_UNITPSK,PE.NAMA_PEGAWAI "+
//				" FROM TBLPPKRUJUNIT PE,TBLPPKPERBICARAAN PKB " +
				" FROM TBLPPKRUJUNIT PE,TBLPPKPERINTAH PKB " +
				" WHERE PE.ID_UNITPSK = PKB.ID_UNITPSK " +
//				"AND PE.STATUS_PEG=1 " +
				"";
			if(idNegeri!=0)
				sql+=" AND PE.ID_NEGERI = "+idNegeri;
			if(!idUnit.equals("0"))
				sql+=" AND PE.ID_JKPTG="+idUnit;//1611137 --LABUAN
				
//			sql+=" AND TO_CHAR(PKB.TARIKH_BICARA,'YYYY') ="+tahun ;
			sql+=" AND TO_CHAR(PKB.TARIKH_PERINTAH,'YYYY') ="+tahun ;
			sql+=" GROUP BY PE.ID_JKPTG,PE.ID_UNITPSK,PE.NAMA_PEGAWAI "+
				" ORDER BY PE.ID_JKPTG,PE.NAMA_PEGAWAI";
			//myLog.info("getSenaraiPegawai perbicaraan:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> record = null;
			while(rs.next()) {
				record = new Hashtable<String, String>();
				record.put("idJkptg",rs.getString("ID_JKPTG"));
				record.put("idPegawai",rs.getString("ID_UNITPSK"));
				record.put("nama",rs.getString("NAMA_PEGAWAI").toUpperCase());
				lists.addElement(record);
				
			}
		} catch (Exception e) {
				
		}finally  {
			if (db != null) db.close();
		}
		return lists;	
		
	}
	
	public boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLRUJUSERPEGAWAI WHERE ID_USERPEGAWAI = "+id;
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}
	
	public boolean insert(Hashtable<?, ?> parameters,String uid) {
		String sql = "";
	    String name = "";
		String value ="";
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long idBilangan = DB.getNextID("TBLRUJUSERPEGAWAI_SEQ");
			int x = 1;

			r.add("ID_USERPEGAWAI", idBilangan);
			  for (Enumeration<?> e = parameters.keys(); e.hasMoreElements();x++) {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  r.add(name.replace("Form_",""),value);
			  }

			r.add("id_masuk", uid);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("id_kemaskini", uid);
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJUSERPEGAWAI");
			//mylog.info("insert("+parameters+","+uid+"):TBLRUJUSERPEGAWAI:: "+sql);

			  stmt.executeUpdate(sql);
		  } catch (Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		  finally {
			  if (db != null) db.close();
		  }
		  
		  return true;
	}
	
	  public boolean update(Hashtable<?, ?> parameters,String id) {
		  String sql="";
		  String name="";
		  String value="";
		  if (id != null) {
			  sql = "UPDATE TBLRUJUSERPEGAWAI SET ";
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  sql = sql + name.replace("Form_","") + "='"+ value + "'" + (x<parameters.size()?",":"") ;
			   }
			  sql = sql + ",tarikh_kemaskini = SYSDATE ";
			  sql = sql + " WHERE ID_USERPEGAWAI = " +id+ "";
			  //myLog.info(sql);
			  Db db = null;
			  try {
				  db = new Db();
				  Statement stmt = db.getStatement();
				  stmt.executeUpdate(sql);
	
			  } catch (Exception e) { 
				  e.printStackTrace();
				  return false;
			  }
			  finally {
				  if (db != null) db.close();
			  }
		  }
		  
		  return true;
	  }
	
	public Vector<Hashtable<String, String>> getSenaraiUsersByNegeri(String idNegeri) throws Exception{
		Db db = null;
		Vector<Hashtable<String, String>> lists = new Vector<Hashtable<String, String>>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = " " +
				"SELECT U.USER_NAME,U.USER_ID FROM USERS U,USERS_INTERNAL UI "+
				" WHERE UI.USER_ID = U.USER_ID "+
				" AND UI.ID_NEGERI = "+idNegeri;

			//mylog.info("getSenarai("+idPejabat+"):"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> record = null;
			while(rs.next()) {
				record = new Hashtable<String, String>();
				record.put("username",rs.getString("USER_NAME"));
				record.put("userid",rs.getString("USER_ID"));
				lists.addElement(record);
			}
		} catch (Exception e) {
				
		}finally  {
			if (db != null) db.close();
		}
		return lists;		
	}
	/**
	 * Dibuat oleh Mohamad Rosli 2009/12/14
	 * Senarai Mapping pegawai dengan pengguna sistem
	 * */
	public Vector<Hashtable<String, String>> getSenaraiPegawaiMapping(String idNegeri) 
		throws Exception {		
		Db db = null;
		String sql = "";
		Integer count = 0;
		Vector<Hashtable<String, String>> senaraiFail = new Vector<Hashtable<String, String>>();

		try {	
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT RUP.ID_USERPEGAWAI , RU.nama_pegawai,U.USER_NAME ,U.USER_LOGIN ,RU.id_negeri "+
				" ,U.USER_ID,RU.ID_UNITPSK "+
				" from tblppkrujunit RU,TBLRUJUSERPEGAWAI RUP,USERS U "+
				" WHERE RUP.ID_UNITPSK = RU.ID_UNITPSK "+
				" AND RUP.USER_ID = U.USER_ID "+
				" AND RU.id_negeri = "+idNegeri;
			//myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> h;			
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("iduserpegawai", rs.getString("ID_USERPEGAWAI") == null ? "" : rs.getString("ID_USERPEGAWAI"));
				h.put("namapegawai", rs.getString("nama_pegawai") == null ? "" : rs.getString("nama_pegawai"));
				h.put("username", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("userlogin", rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
				h.put("idnegeri", rs.getString("id_negeri") == null ? "" : rs.getString("id_negeri"));
				h.put("userid", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("idunit", rs.getString("ID_UNITPSK") == null ? "" : rs.getString("ID_UNITPSK"));
				senaraiFail.addElement(h);
				count++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFail;
	}
	/**
	 * Dibuat oleh Mohamad Rosli 2010/02/05
	 * Senarai Mapping pegawai (TBLRUJPEGAWAI)
	 * */
	public Vector<Hashtable<String, String>> getSenaraiPegawai(String idSeksyen,String idPegawai,String idNegeri) throws Exception {		
		Db db = null;
		String sql = "";
		Integer count = 0;
		Vector<Hashtable<String, String>> senaraiFail = new Vector<Hashtable<String, String>>();
		try {	
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT U.USER_NAME,RP.NO_TEL_PEJABAT,UI.EMEL,RP.ID_PEGAWAI "+
				" ,RS.NAMA_SEKSYEN,RS.KOD_SEKSYEN "+
				" FROM TBLRUJPEGAWAI RP,USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN RS "+
				" WHERE "+
				" UI.USER_ID = U.USER_ID "+
				" AND U.USER_ID = RP.USER_ID " +
				" AND RS.ID_SEKSYEN = UI.ID_SEKSYEN"+
				" AND RP.FLAG_AKTIF = 'Y' "+
				" AND UI.ID_SEKSYEN = "+idSeksyen;
			if(idNegeri!=null)
				sql +=" AND UI.ID_NEGERI = "+idNegeri;
			if(idPegawai!="0")
				sql +=" AND RP.ID_PEGAWAI = "+idPegawai;
			
			sql += " ORDER BY U.USER_NAME ASC";
			//
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> h;			
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("idpegawai", rs.getString("ID_PEGAWAI") == null ? "" : rs.getString("ID_PEGAWAI"));
				h.put("namapegawai", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				h.put("notelefon", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs.getString("NO_TEL_PEJABAT"));
				h.put("email", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("kodseksyen", rs.getString("KOD_SEKSYEN") == null ? "" : rs.getString("KOD_SEKSYEN"));
				h.put("namaseksyen", rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				senaraiFail.addElement(h);
				count++;
				
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFail;
	}
	/**
	 * Dibuat oleh Mohamad Rosli 2010/02/05
	 * Senarai Mapping pegawai (USER,USERS_INTERNAL) 
	 * return Hashtable
	 * */
	public Hashtable<String, String> getSenaraiPegawai(String idPegawai) throws Exception {		
		Db db = null;
		String sql = "";
		Hashtable<String, String> h;
		h = new Hashtable<String, String>();
		try {	
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT(U.USER_NAME)" +
				" ,RP.NO_TEL_PEJABAT,RP.ID_PEGAWAI " +
				" ,UI.EMEL,UI.ID_JAWATAN "+
				" ,RS.NAMA_SEKSYEN,RS.KOD_SEKSYEN" +
				" ,RJ.KETERANGAN "+
				" FROM TBLRUJPEGAWAI RP,USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN RS,TBLRUJJAWATAN RJ "+
				" WHERE " +
				" UI.USER_ID = U.USER_ID "+
				" AND U.USER_ID = RP.USER_ID " +
				" AND RS.ID_SEKSYEN = UI.ID_SEKSYEN" +
				" AND RJ.ID_JAWATAN = UI.ID_JAWATAN "+
				" AND RP.ID_PEGAWAI = "+idPegawai;
			//myLog.info("getSenaraiPegawai:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);		
			while (rs.next()) {
				h.put("idpegawai", rs.getString("ID_PEGAWAI") == null ? "0" : rs.getString("ID_PEGAWAI"));
				h.put("namapegawai", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("notelefon", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs.getString("NO_TEL_PEJABAT"));
				h.put("email", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("kodseksyen", rs.getString("KOD_SEKSYEN") == null ? "" : rs.getString("KOD_SEKSYEN"));
				h.put("namaseksyen", rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				h.put("idjawatan", rs.getString("ID_JAWATAN") == null ? "0" : rs.getString("ID_JAWATAN"));
				h.put("jawatan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
	
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return h;
	}
	
	/**
	 * Dibuat oleh Mohamad Rosli 2012/02/09
	 * Senarai Mapping Pengguna (USER,USERS_INTERNAL) 
	 * return Hashtable
	 * */
	public Hashtable<String, String> getPengguna(String idPengguna) throws Exception {		
		Db db = null;
		String sql = "";
		Hashtable<String, String> h;
		h = new Hashtable<String, String>();
		try {	
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT(U.USER_NAME) USER_NAME,UI.EMEL,UI.ID_JAWATAN "+
				" ,RJ.KETERANGAN "+
				" FROM USERS U,USERS_INTERNAL UI,TBLRUJJAWATAN RJ "+
				" WHERE " +
				" UI.USER_ID = U.USER_ID "+
				" AND RJ.ID_JAWATAN = UI.ID_JAWATAN "+
				" AND U.USER_ID = "+idPengguna;
			//mylog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);		
			while (rs.next()) {
				h.put("idpegawai", rs.getString("ID_PEGAWAI") == null ? "" : rs.getString("ID_PEGAWAI"));
				h.put("namapegawai", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				//h.put("notelefon", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs.getString("NO_TEL_PEJABAT"));
				h.put("email", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				//h.put("kodseksyen", rs.getString("KOD_SEKSYEN") == null ? "" : rs.getString("KOD_SEKSYEN"));
				//h.put("namaseksyen", rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				h.put("idjawatan", rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
				h.put("jawatan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return h;
	}
	
	
}
