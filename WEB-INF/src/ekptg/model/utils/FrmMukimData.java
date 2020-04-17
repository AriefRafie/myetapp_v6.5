package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmMukimData {
	private static Vector v = new Vector();
	public static void add(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try
	    {
	      String kod = (String)data.get("kod_mukim");
	      String nama = (String)data.get("nama_mukim");
	      int id_daerah = 1;
	      int id_negeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("kod_mukim", kod);
	      r.add("nama_mukim", nama);
	      r.add("id_daerah", id_daerah);
	      r.add("id_negeri", id_negeri);
	      sql = r.getSQLInsert("tblrujmukim");
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }

	  /**
	 * @param key
	 * @throws Exception
	 */
	public static void setList(String key) throws Exception {
	    Db db = null;
	    String cari = key;
	    String Like = "";
	    String sql = "";
	    v.clear();
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
//	      SQLRenderer r = new SQLRenderer();

//	      r.add("id_mukim");
//	      r.add("kod_mukim");
//	      r.add("id_daerah");
//	      r.add("id_negeri");
	      if(cari.equalsIgnoreCase(null)){
	    	  Like = "";
//	    	  Like = cari;
	      }else{
	    	  Like = "WHERE nama_mukim like '%"+cari+"%' ";
//	    	  r.set("nama_mukim", '%'+cari+'%');
//	    	  r.set("nama_mukim", "'%'" + cari + "'%'");
	      }
//	      r.add("nama_mukim");
//	      sql = r.getSQLSelect("tblrujmukim", "id_daerah");
//	      sql = r.getSQLSelect("tblrujmukim", "id_daerah");
	      sql = "SELECT id_mukim, kod_mukim, id_daerah, id_negeri, nama_mukim FROM Tblrujmukim " + Like + " order by id_daerah";
	      
	      ResultSet rs = stmt.executeQuery(sql);

	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id_mukim", rs.getInt("id_mukim"));
	        if(rs.getString("kod_mukim") == null){
	        	h.put("kod_mukim", "");
	        }else{
	        	h.put("kod_mukim", rs.getString("kod_mukim"));
	        }
	        if(rs.getString("nama_mukim") == null){
	        	h.put("nama_mukim", "");
	        }else{
	        	h.put("nama_mukim", rs.getString("nama_mukim"));
	        }
	        v.addElement(h);
	      }
	      System.out.println(v.size());
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static Vector getList(){
		  System.out.println(v.size());
		  return v;
	  }

	  public static void update(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
	      //String id = (String)data.get("id_negeri");
	      int idMukim = (Integer)data.get("id_mukim");
	      String kod = (String)data.get("kod_mukim");
		  String nama = (String)data.get("nama_mukim");
		  //int id_daerah = 1;
		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_mukim", idMukim);
		  r.add("kod_mukim", kod);
		  r.add("nama_mukim", nama);
		  //r.add("id_negeri", id_daerah);
	      sql = r.getSQLUpdate("tblrujmukim");
	      System.out.println("FrmMukimData::Update::TBLRUJMUKIM = "+sql);
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String uid) throws Exception {
	    Db db = null;
	    int idMukim= Integer.parseInt(uid);
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_mukim", idMukim);
	      sql = r.getSQLDelete("tblrujmukim");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
}
