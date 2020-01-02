package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmDaerahData {
	public static void add(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try
	    {
	      String kod = (String)data.get("kod_daerah");
	      String nama = (String)data.get("nama_daerah");
	      int id_negeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("kod_daerah", kod);
	      r.add("nama_daerah", nama);
	      r.add("id_negeri", id_negeri);
	      sql = r.getSQLInsert("tblrujdaerah");
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }

	  public static Vector getList() throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_daerah");
	      r.add("kod_daerah");
	      r.add("nama_daerah");
	      r.add("id_negeri");
	      sql = r.getSQLSelect("tblrujdaerah", "id_negeri");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id_daerah", rs.getInt("id_daerah"));
	        h.put("kod_daerah", rs.getString("kod_daerah"));
	        h.put("nama_daerah", rs.getString("nama_daerah"));
	        v.addElement(h);
	      }
	      System.out.println(v.size());
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void update(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
	      //String id = (String)data.get("id_negeri");
	      int idDaerah = (Integer)data.get("id_daerah");
	      String kod = (String)data.get("kod_daerah");
		  String nama = (String)data.get("nama_daerah");
		  //int id_daerah = 1;
		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_daerah", idDaerah);
		  r.add("kod_daerah", kod);
		  r.add("nama_daerah", nama);
		  //r.add("id_negeri", id_daerah);
	      sql = r.getSQLUpdate("tblrujdaerah");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String uid) throws Exception {
	    Db db = null;
	    int idDaerah= Integer.parseInt(uid);
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_daerah", idDaerah);
	      sql = r.getSQLDelete("tblrujdaerah");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
}
