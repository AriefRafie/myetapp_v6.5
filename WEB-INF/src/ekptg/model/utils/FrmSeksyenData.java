package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmSeksyenData {
	public static void add(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try
	    {
	      String kod = (String)data.get("kod_seksyen");
	      String nama = (String)data.get("nama_seksyen");

	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("kod_seksyen", kod);
	      r.add("nama_seksyen", nama);

	      sql = r.getSQLInsert("tblrujseksyen");
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
	      r.add("id_seksyen");
	      r.add("kod_seksyen");
	      r.add("nama_seksyen");

	      sql = r.getSQLSelect("tblrujseksyen", "id_seksyen");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id_seksyen", rs.getInt("id_seksyen"));
	        h.put("kod_seksyen", rs.getString("kod_seksyen"));
	        h.put("nama_seksyen", rs.getString("nama_seksyen"));

	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static Hashtable getList(String idSeksyen) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_seksyen");
		      r.add("kod_seksyen");
		      r.add("nama_seksyen");
		      r.add("id_seksyen",idSeksyen);
		      sql = r.getSQLSelect("tblrujseksyen", "kod_seksyen");
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      while (rs.next()) {
		        h.put("id_seksyen", rs.getInt("id_seksyen"));
		        h.put("kod_seksyen", rs.getString("kod_seksyen"));
		        h.put("nama_seksyen", rs.getString("nama_seksyen"));

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

	      int idSeksyen = (Integer)data.get("id_seksyen");
	      String kod = (String)data.get("kod_seksyen");
		  String nama = (String)data.get("nama_seksyen");

		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_seksyen", idSeksyen);
		  r.add("kod_seksyen", kod);
		  r.add("nama_seksyen", nama);

	      sql = r.getSQLUpdate("tblrujseksyen");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String uid) throws Exception {
	    Db db = null;
	    int idSeksyen= Integer.parseInt(uid);
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_seksyen", idSeksyen);
	      sql = r.getSQLDelete("tblrujseksyen");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
}
