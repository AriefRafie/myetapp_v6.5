package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class List {
	
	public static Vector getNegeri() throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Negeri");
	      r.add("kod_Negeri");
	      r.add("nama_Negeri");
	      r.add("kod_Mampu");
	      sql = r.getSQLSelect("tblrujnegeri", "kod_Mampu");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Negeri"));
	        h.put("kod", rs.getString("kod_Negeri"));
	        h.put("nama", rs.getString("nama_Negeri"));
	        h.put("mampu", rs.getString("kod_Mampu"));
	        v.addElement(h);
	      }
	      System.out.println(v.size());
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector getKementerian() throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Kementerian");
	      r.add("kod_Kementerian");
	      r.add("nama_Kementerian");
	      sql = r.getSQLSelect("tblrujnegeri", "kod_Kementerian");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Kementerian"));
	        h.put("kod", rs.getString("kod_Kementerian"));
	        h.put("nama", rs.getString("nama_Kementerian"));
	        v.addElement(h);
	      }
	      System.out.println(v.size());
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
}
