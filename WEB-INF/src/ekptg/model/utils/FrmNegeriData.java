package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmNegeriData {
	public static void add(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try
	    {
	      String kod = (String)data.get("kod_negeri");
	      String nama = (String)data.get("nama_negeri");
	      int idNegara = 1;
	      String mampu = (String)data.get("kod_mampu");
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("kod_negeri", kod);
	      r.add("nama_negeri", nama);
	      r.add("id_negara", idNegara);
	      r.add("kod_mampu", mampu);
	      sql = r.getSQLInsert("tblrujnegeri");
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }

	  public static Vector getList() throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_negeri");
	      r.add("kod_negeri");
	      r.add("nama_negeri");
	      //r.add("id_negara");
	      r.add("kod_mampu");
	      sql = r.getSQLSelect("tblrujnegeri", "kod_mampu");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id_negeri", rs.getInt("id_negeri"));
	        h.put("kod_negeri", rs.getString("kod_negeri"));
	        h.put("nama_negeri", rs.getString("nama_negeri"));
	        //h.put("id_negara", rs.getString("id_negara"));
	        h.put("kod_mampu", rs.getString("kod_mampu"));
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static Hashtable getList(String idnegeri) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_negeri");
		      r.add("kod_negeri");
		      r.add("nama_negeri");
		      r.add("kod_mampu");
		      r.add("id_negeri",idnegeri);
		      sql = r.getSQLSelect("tblrujnegeri", "kod_mampu");
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      while (rs.next()) {
		        h.put("id_negeri", rs.getInt("id_negeri"));
		        h.put("kod_negeri", rs.getString("kod_negeri"));
		        h.put("nama_negeri", rs.getString("nama_negeri"));
		        h.put("kod_mampu", rs.getString("kod_mampu"));
		      }
		      return h;
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
	      int idNegeri = (Integer)data.get("id_negeri");
	      String kod = (String)data.get("kod_negeri");
		  String nama = (String)data.get("nama_negeri");
		  int idNegara = 1;
		  String mampu = (String)data.get("kod_mampu");
		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_negeri", idNegeri);
		  r.add("kod_negeri", kod);
		  r.add("nama_negeri", nama);
		  r.add("id_negara", idNegara);
		  r.add("kod_mampu", mampu);
	      sql = r.getSQLUpdate("tblrujnegeri");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String uid) throws Exception {
	    Db db = null;
	    int idNegeri= Integer.parseInt(uid);
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_negeri", idNegeri);
	      sql = r.getSQLDelete("tblrujnegeri");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
}
