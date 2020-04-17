package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmKementerianData {
	public static void add(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try
	    {
	      String kod = (String)data.get("kod_kementerian");
	      String nama = (String)data.get("nama_kementerian");
	      String alamat1 = (String)data.get("alamat1");
	      String alamat2 = (String)data.get("alamat2");
	      String alamat3 = (String)data.get("alamat3");
	      String poskod = (String)data.get("poskod");
	      String jawatan = (String)data.get("jawatan");
	      int idNegeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("kod_kementerian", kod);
	      r.add("nama_kementerian", nama);
	      r.add("alamat1", alamat1);
	      r.add("alamat2", alamat2);
	      r.add("alamat3", alamat3);
	      r.add("poskod", poskod);
	      r.add("jawatan", jawatan);
	      r.add("id_negeri", idNegeri);
	      sql = r.getSQLInsert("tblrujkementerian");
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
	      r.add("id_kementerian");
	      r.add("kod_kementerian");
	      r.add("nama_kementerian");
	      r.add("alamat1");
	      r.add("alamat2");
	      r.add("alamat3");
	      r.add("poskod");
	      r.add("id_negeri");
	      r.add("jawatan");
	      sql = r.getSQLSelect("Tblrujkementerian", "kod_kementerian");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();

	       while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id_kementerian", rs.getInt("id_kementerian"));
	        h.put("kod_kementerian", rs.getString("kod_kementerian"));
	        if(rs.getString("nama_kementerian") == null){
	        	h.put("nama_kementerian", "");
	        }else{
	        	h.put("nama_kementerian", rs.getString("nama_kementerian"));
	        }
	        if(rs.getString("alamat1") == null){
	        	h.put("alamat1", "");
	        }else{
	        	h.put("alamat1", rs.getString("alamat1"));
	        }
	        if(rs.getString("alamat2") == null){
	        	h.put("alamat2", "");
	        }else{
	        	h.put("alamat2", rs.getString("alamat2"));
	        }
	        if(rs.getString("alamat3") == null){
	        	h.put("alamat3", "");
	        }else{
	        	h.put("alamat3", rs.getString("alamat3"));
	        }
	        if(rs.getString("poskod") == null){
	        	h.put("poskod", "");
	        }else{
	        	h.put("poskod", rs.getString("poskod"));
	        }
	        if(rs.getString("jawatan") == null){
	        	h.put("jawatan", "");
	        }else{
	        	h.put("jawatan", rs.getString("jawatan"));
	        }
	        v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Hashtable getList(String idkementerian) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_kementerian");
		      r.add("kod_kementerian");
		      r.add("nama_kementerian");
		      r.add("alamat1");
		      r.add("alamat2");
		      r.add("alamat3");
		      r.add("poskod");
		      r.add("id_negeri");
		      r.add("jawatan");
		      r.add("id_kementerian",idkementerian);
		      sql = r.getSQLSelect("Tblrujkementerian", "kod_kementerian");
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		       while (rs.next()) {
  
		        h.put("id_kementerian", rs.getInt("id_kementerian"));
		        h.put("kod_kementerian", rs.getString("kod_kementerian"));
		        if(rs.getString("nama_kementerian") == null){
		        	h.put("nama_kementerian", "");
		        }else{
		        	h.put("nama_kementerian", rs.getString("nama_kementerian"));
		        }
		        if(rs.getString("alamat1") == null){
		        	h.put("alamat1", "");
		        }else{
		        	h.put("alamat1", rs.getString("alamat1"));
		        }
		        if(rs.getString("alamat2") == null){
		        	h.put("alamat2", "");
		        }else{
		        	h.put("alamat2", rs.getString("alamat2"));
		        }
		        if(rs.getString("alamat3") == null){
		        	h.put("alamat3", "");
		        }else{
		        	h.put("alamat3", rs.getString("alamat3"));
		        }
		        if(rs.getString("poskod") == null){
		        	h.put("poskod", "");
		        }else{
		        	h.put("poskod", rs.getString("poskod"));
		        }
		        if(rs.getString("jawatan") == null){
		        	h.put("jawatan", "");
		        }else{
		        	h.put("jawatan", rs.getString("jawatan"));
		        }
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
	      int idKementerian = (Integer)data.get("id_kementerian");
	      String kod = (String)data.get("kod_kementerian");
		  String nama = (String)data.get("nama_kementerian");
		  String alamat1 = (String)data.get("alamat1");
	      String alamat2 = (String)data.get("alamat2");
	      String alamat3 = (String)data.get("alamat3");
	      String poskod = (String)data.get("poskod");
	      String jawatan = (String)data.get("jawatan");
		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_kementerian", idKementerian);
		  r.add("kod_kementerian", kod);
		  r.add("nama_kementerian", nama);
		  r.add("alamat1", alamat1);
	      r.add("alamat2", alamat2);
	      r.add("alamat3", alamat3);
	      r.add("poskod", poskod);
	      r.add("jawatan", jawatan);
	      sql = r.getSQLUpdate("tblrujkementerian");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String uid) throws Exception {
	    Db db = null;
	    int idKementerian= Integer.parseInt(uid);
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_kementerian", idKementerian);
	      sql = r.getSQLDelete("tblrujkementerian");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
}
