package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmLaporanKemasukanBilFailData {

	static Logger mylog = Logger.getLogger(ekptg.model.ppk.FrmLaporanKemasukanBilFailData.class);
	public FrmLaporanKemasukanBilFailData() {	}
	
	public static boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLLAPORANBILFAIL WHERE ID_LAPORANBILFAIL="+id;
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}
	
	public static boolean insert(Hashtable<?, ?> parameters, String uid) {
		String sql = "";
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long idBilangan = DB.getNextID("TBLLAPORANBILFAIL_SEQ");

			r.add("ID_LAPORANBILFAIL", idBilangan);
			r.add("ID_DAERAH", r.unquote((String)parameters.get("id_Daerah")));
			r.add("BILANGAN", r.unquote((String)parameters.get("bilangan")));
			r.add("id_masuk", uid);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("id_kemaskini", uid);
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLLAPORANBILFAIL");
			//mylog.info("insert("+parameters+","+uid+"):TBLLAPORANBILFAIL:: "+sql);

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
			  sql = "UPDATE TBLLAPORANBILFAIL SET ";
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  sql = sql + name.replace("Form_","") + "='"+ value + "'" + (x<parameters.size()?",":"") ;
			   }
			  sql = sql + ",tarikh_kemaskini=SYSDATE ";
			  sql = sql + " WHERE ID_LAPORANBILFAIL = " +id+ "";
			  mylog.info(sql);
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
	
	
	public static Vector<Hashtable<String, String>> getSenarai(String idPejabat,String idDaerah)	throws Exception{
		Db db = null;
		Vector<Hashtable<String, String>> lists = new Vector<Hashtable<String, String>>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "  SELECT RD.NAMA_DAERAH,RB.BILANGAN,TO_CHAR(RB.TARIKH_MASUK,'dd/mm/yyyy') TARIKH_KEMASUKAN "+
			" ,RPU.ID_PEJABATJKPTG,RPU.ID_DAERAHURUS,RB.ID_LAPORANBILFAIL"+
			" FROM TBLLAPORANBILFAIL RB,TBLRUJDAERAH RD, TBLRUJPEJABATURUSAN RPU  "+
			" WHERE RD.ID_DAERAH=RB.ID_DAERAH "+
			" AND RPU.ID_DAERAHURUS = RB.ID_DAERAH "+
			" AND RPU.ID_SEKSYEN=2 "+
			" AND RPU.ID_JENISPEJABAT=22 ";
			if (idPejabat != null ) {
				sql += " and RPU.ID_PEJABATJKPTG="+idPejabat;
			}
			if (idDaerah != null ) {
				sql += " and RPU.ID_DAERAHURUS="+idDaerah;
			}
			sql += " order by RPU.ID_DAERAHURUS";
	        //System.out.println("getSenarai("+idPejabat+","+idDaerah+"):"+sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> record = null;
			while(rs.next()) {
				record = new Hashtable<String, String>();
				record.put("idlaporanbilfail",rs.getString("ID_LAPORANBILFAIL"));
				record.put("namadaerah",rs.getString("NAMA_DAERAH"));
				record.put("bilangan",rs.getString("BILANGAN"));
				record.put("tarikh",rs.getString("TARIKH_KEMASUKAN"));
				record.put("iddaerah",rs.getString("ID_DAERAHURUS"));
				record.put("idpejabat",rs.getString("ID_PEJABATJKPTG"));
				lists.addElement(record);
			}
		} catch (Exception e) {
				
		}finally  {
			if (db != null) db.close();
		}
		return lists;		
	}

	
}
