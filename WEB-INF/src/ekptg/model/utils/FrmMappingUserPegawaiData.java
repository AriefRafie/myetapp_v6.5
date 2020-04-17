package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmMappingUserPegawaiData {

	static Logger myLog = Logger.getLogger(ekptg.model.utils.FrmMappingUserPegawaiData.class);
	public FrmMappingUserPegawaiData() {	}
	
	public static boolean delete(String id) {
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
	
	public static boolean insert(Hashtable<?, ?> parameters,String uid) {
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
	
	  public static boolean update(Hashtable<?, ?> parameters,String id) {
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
	
	public static Vector<Hashtable<String, String>> getSenaraiUsersByNegeri(String idNegeri)	throws Exception{
		Db db = null;
		Vector<Hashtable<String, String>> lists = new Vector<Hashtable<String, String>>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = " SELECT U.USER_NAME,U.USER_ID FROM USERS U,USERS_INTERNAL UI "+
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
	public static Vector<Hashtable<String, String>> getSenaraiPegawaiMapping(String idNegeri) 
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
	 * Dikemaskini pada 2012/12/27, Tambah AND RP.FLAG_AKTIF = 'Y' 
	 * */
	public static Vector<Hashtable<String, String>> getSenaraiPegawai(String idSeksyen,String idSuburusan,String idNegeri) throws Exception {		
		Db db = null;
		String sql = "";
		Integer count = 0;
		Vector<Hashtable<String, String>> senaraiFail = new Vector<Hashtable<String, String>>();
		try {	
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT(UPPER(U.USER_NAME)) USER_NAME,RP.NO_TEL_PEJABAT,RP.EMEL,RP.ID_PEGAWAI "+
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
			if(idSuburusan!="0")
				sql +=" AND RP.ID_PEGAWAI = "+idSuburusan;
			//mylog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> h;		
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("idpegawai", rs.getString("ID_PEGAWAI") == null ? "" : rs.getString("ID_PEGAWAI"));
				h.put("namapegawai", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
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
	 * Senarai Mapping pegawai (TBLRUJPEGAWAI) 
	 * return Hashtable
	 * */
	public static Hashtable<String, String> getSenaraiPegawai(String idPegawai) throws Exception {		
		Db db = null;
		String sql = "";
		Hashtable<String, String> h;
		h = new Hashtable<String, String>();
		try {	
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT(UPPER(U.USER_NAME)) USER_NAME,RP.NO_TEL_PEJABAT,RP.EMEL,RP.ID_PEGAWAI "+
				" ,RS.NAMA_SEKSYEN,RS.KOD_SEKSYEN,RJ.KETERANGAN "+
				" FROM TBLRUJPEGAWAI RP,USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN RS,TBLRUJJAWATAN RJ "+
				" WHERE " +
				" UI.USER_ID = U.USER_ID "+
				" AND U.USER_ID = RP.USER_ID "+
				" AND RS.ID_SEKSYEN = UI.ID_SEKSYEN "+
				" AND RJ.ID_JAWATAN = UI.ID_JAWATAN "+
				" AND RP.ID_PEGAWAI = '"+idPegawai+"'";
			//mylog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);		
			while (rs.next()) {
				h.put("idpegawai", rs.getString("ID_PEGAWAI") == null ? "" : rs.getString("ID_PEGAWAI"));
				h.put("namapegawai", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("notelefon", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs.getString("NO_TEL_PEJABAT"));
				h.put("email", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("kodseksyen", rs.getString("KOD_SEKSYEN") == null ? "" : rs.getString("KOD_SEKSYEN"));
				h.put("namaseksyen", rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				h.put("jawatan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return h;
	}	
	
	
}
