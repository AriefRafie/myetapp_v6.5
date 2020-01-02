package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmSuburusanRoleData {

	static Logger mylog = Logger.getLogger(ekptg.model.utils.FrmSuburusanRoleData.class);
	public FrmSuburusanRoleData() {
		
	}
	
	public boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLRUJROLESUBURUSAN WHERE ID_ROLESUBURUSAN="+id+" ";
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
		  String name = "";
		  String value ="";
		  Db db = null;
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  int x = 1;
			  long id_Rolesuburusan= DB.getNextID("TBLRUJROLESUBURUSAN_SEQ");

			  r.add("ID_ROLESUBURUSAN", id_Rolesuburusan);
			  for (Enumeration<?> e = parameters.keys(); e.hasMoreElements();x++) {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  r.add(name.replace("Form_",""),value);
			  }
			  r.add("id_masuk", uid);
			  r.add("tarikh_masuk",r.unquote("SYSDATE"));
			  r.add("id_kemaskini", uid);
			  r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			  r.add("id_db",r.unquote("99"));
			  sql = r.getSQLInsert("TBLRUJROLESUBURUSAN");
			  mylog.info("insert::TBLRUJROLESUBURUSAN = "+sql);

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
			  sql = "UPDATE TBLRUJROLESUBURUSAN SET ";
			  int x = 1;
			  for (Enumeration<?> e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  sql = sql + name.replace("Form_","") + "='"+ value + "'" + (x<parameters.size()?",":"") ;
			   }
			  sql = sql + ",tarikh_kemaskini=SYSDATE ";
			  sql = sql + " WHERE ID_ROLESUBURUSAN = '" +id+ "' ";
			  
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
	
	  @SuppressWarnings("unchecked")
	public Hashtable<String, Comparable> getDetails(String id) {
		  Hashtable<String, Comparable> details_data = null;
		  String sql = " ";
		  Db db = null;
		  try {
			  db = new Db();
			  details_data = new Hashtable<String, Comparable>();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.add("rrsu.ID_ROLESUBURUSAN");
			  r.add("rrsu.ID_SUBURUSAN");
			  r.add("rrsu.NAME");
			  r.add("RSU.ID_URUSAN");
			  r.add("RRSU.ID_SUBURUSAN",r.unquote("RSU.ID_SUBURUSAN"));		    		
			  r.add("rrsu.ID_ROLESUBURUSAN",r.unquote(id));		    		
			  sql = r.getSQLSelect("tblrujrolesuburusan rrsu,TBLRUJSUBURUSAN RSU");

			  mylog.info("getDetails("+id+")->sql:"+sql);
			  ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()) {
				  details_data.put("idrolesuburusan",rs.getString("ID_ROLESUBURUSAN")); 
				  details_data.put("idsuburusan",rs.getString("id_Suburusan")); 
   				  details_data.put("kodurusan",rs.getString("name")); 
   				  details_data.put("idurusan",rs.getString("ID_URUSAN")); 
			  }	
			  return details_data;
		  }catch (Exception e) {
			  e.printStackTrace();
		  }finally {
			  if (db != null) db.close();
		  }
		  return details_data;
	  }
	
	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
	
	public String getShortName(String nicename) {
		nicename = nicename.replace("WILAYAH PERSEKUTUAN","");
		return nicename;
	}
	
}
