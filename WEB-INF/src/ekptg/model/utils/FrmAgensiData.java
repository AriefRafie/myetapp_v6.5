package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmAgensiData {
	public static void add(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try
	    {
	      String kod = (String)data.get("kod_agensi");
	      String nama = (String)data.get("nama_agensi");
	      String alamat1 = (String)data.get("alamat1");
	      String alamat2 = (String)data.get("alamat2");
	      String alamat3 = (String)data.get("alamat3");
	      String poskod = (String)data.get("poskod");
	      String jawatan = (String)data.get("jawatan");
	      int idKementerian = 1;
	      int idNegeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("kod_agensi", kod);
	      r.add("nama_agensi", nama);
	      r.add("alamat1", alamat1);
	      r.add("alamat2", alamat2);
	      r.add("alamat3", alamat3);
	      r.add("poskod", poskod);
	      r.add("jawatan", jawatan);
	      r.add("id_negeri", idNegeri);
	      r.add("id_kementerian", idKementerian);
	      sql = r.getSQLInsert("tblrujkementerian");
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }

	  public static Vector getList() throws Exception {
	    Db db = null;
//	    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_agensi");
	      r.add("kod_agensi");
	      r.add("nama_agensi");
	      r.add("alamat1");
	      r.add("alamat2");
	      r.add("alamat3");
	      r.add("poskod");
	      r.add("id_negeri");
	      r.add("jawatan");
	      r.add("id_kementerian");
	      sql = r.getSQLSelect("Tblrujagensi", "id_negeri");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id_agensi", rs.getInt("id_agensi"));
	        h.put("kod_agensi", rs.getString("kod_agensi"));
	        if(rs.getString("nama_agensi") == null){
	        	h.put("nama_agensi", "");
	        }else{
	        	h.put("nama_agensi", rs.getString("nama_agensi"));
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

	  public static Hashtable getList(String idagensi) throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_agensi");
		      r.add("kod_agensi");
		      r.add("nama_agensi");
		      r.add("alamat1");
		      r.add("alamat2");
		      r.add("alamat3");
		      r.add("poskod");
		      r.add("id_negeri");
		      r.add("jawatan");
		      r.add("id_kementerian");
		      r.add("id_agensi",idagensi);
		      sql = r.getSQLSelect("Tblrujagensi", "id_negeri");
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      while (rs.next()) {
		        h.put("id_agensi", rs.getInt("id_agensi"));
		        h.put("kod_agensi", rs.getString("kod_agensi"));
		        h.put("id_negeri", rs.getString("id_negeri"));
		        if(rs.getString("nama_agensi") == null){
		        	h.put("nama_agensi", "");
		        }else{
		        	h.put("nama_agensi", rs.getString("nama_agensi"));
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
	      int idagensi = (Integer)data.get("id_agensi");
	      String kod = (String)data.get("kod_agensi");
		  String nama = (String)data.get("nama_agensi");
		  String alamat1 = (String)data.get("alamat1");
	      String alamat2 = (String)data.get("alamat2");
	      String alamat3 = (String)data.get("alamat3");
	      String poskod = (String)data.get("poskod");
	      String jawatan = (String)data.get("jawatan");
		  db = new Db();
		  Statement stmt = db.getStatement();
		  SQLRenderer r = new SQLRenderer();
		  r.update("id_agensi", idagensi);
		  r.add("kod_agensi", kod);
		  r.add("nama_agensi", nama);
		  r.add("alamat1", alamat1);
	      r.add("alamat2", alamat2);
	      r.add("alamat3", alamat3);
	      r.add("poskod", poskod);
	      r.add("jawatan", jawatan);
	      sql = r.getSQLUpdate("tblrujagensi");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String uid) throws Exception {
	    Db db = null;
	    int idAgensi= Integer.parseInt(uid);
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_agensi", idAgensi);
	      sql = r.getSQLDelete("tblrujagensi");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
}
