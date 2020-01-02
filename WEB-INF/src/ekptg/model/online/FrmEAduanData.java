package ekptg.model.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.File;

public class FrmEAduanData {
	private static Vector list = new Vector();
public static void addAduan(Hashtable data) throws Exception{
		
		Db db = null;
	    String sql = "";
	   
	    
	    Date now = new Date();
	    try
	    {	 
	    	  long idAduan = DB.getNextID("TBLONLINEADUAN_SEQ");
	    	  long idPengadu = DB.getNextID("TBLONLINEPENGADU_SEQ");
	    	  long idPengaduan = DB.getNextID("TBLONLINEPENGADUAN_SEQ");
	    	  Integer jenisAduan = (Integer)data.get("id_Jenisaduan");
		      String aduan = (String)data.get("aduan");
		      String namaPengadu = (String)data.get("nama_Pengadu");
		      String homeTel = (String)data.get("home_Tel");
		      String hpTel = (String)data.get("hp_Tel");
		      String emel = (String)data.get("emel");
		      String idMasuk = (String)data.get("id_Masuk");
		      String idPengguna = (String)data.get("id_Pengguna");
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      //SIMPAN DALAM TABLE TBLONLINEADUAN
		      r = new SQLRenderer();
		      r.add("id_Aduan",idAduan);
		      r.add("aduan",aduan);
		      r.add("no_Aduan_Online",generateNoAduan(jenisAduan));
		      r.add("id_Jenisaduan", jenisAduan);
		      r.add("tarikh_Aduan",r.unquote("sysdate")); 
		      r.add("tarikh_Masuk",r.unquote("sysdate"));
		      r.add("id_Status","156");
		      r.add("id_Masuk",idMasuk);
		      sql = r.getSQLInsert("tblonlineaduan");
		      stmt.executeUpdate(sql);
		      
		     //SIMPAN DALAM TABLE TBLONLINEPENGADU
		      r = new SQLRenderer();
		      r.add("id_Pengadu",idPengadu);
		      r.add("nama_Pengadu", namaPengadu);
		      r.add("no_Tel_Rumah", homeTel);
		      r.add("no_Tel_Bimbit",hpTel); 
		      r.add("emel",emel); 
		      r.add("id_Pengguna",idPengguna);
		      sql = r.getSQLInsert("tblonlinepengadu");
		      stmt.executeUpdate(sql);
		      
		      //SIMPAN DALAM TABLE TBLONLINEPENGADUAN
		      r = new SQLRenderer();
		      r.add("id_Pengaduan",idPengaduan);
		      r.add("id_Aduan",idAduan);
		      r.add("id_Pengadu",idPengadu);
		      sql = r.getSQLInsert("tblonlinepengaduan");
		      stmt.executeUpdate(sql);
		      
		    } finally {
		      if (db != null) db.close();
		    }
	}

	public static String generateNoAduan(Integer id_jenisaduan) throws Exception{
		String sql = "";
	String noFail = "";
	
	Db db = null;
	db = new Db();
    Statement stmt = db.getStatement();
    
    
    	sql = "select a.kod_jenis_aduan"+
		" from  tblrujjenisaduan a"+
		" where a.id_Jenisaduan = " + id_jenisaduan ; 
		
    	
    	ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		
		
		noFail =   rs.getString(1) + String.format("%06d",File.getSeqNoAduanOnline(id_jenisaduan));
					
		return noFail;
	
		
    }
	
	public static void setData(int id)throws Exception {
		 Db db = null;
		 list.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Aduan");
		      r.add("a.no_Aduan_Online");
		      r.add("c.jenis_Aduan");
		      r.add("a.aduan");
		      r.add("b.nama_Pengadu");
		      r.add("b.no_Tel_Rumah");
		      r.add("b.no_Tel_Bimbit");
		      r.add("b.emel");
		      r.add("a.tarikh_Aduan");
		      
		      r.add("a.id_Jenisaduan",r.unquote("c.id_Jenisaduan"));
		      r.add("a.id_Aduan",id);
		      r.add("a.id_Aduan",r.unquote("d.id_Aduan"));
		      r.add("b.id_Pengadu",r.unquote("d.id_Pengadu"));
		      
		      sql = r.getSQLSelect("Tblonlineaduan a, Tblonlinepengadu b, Tblrujjenisaduan c, Tblonlinepengaduan d");

		      
		      
		      ResultSet rs = stmt.executeQuery(sql); 
		      Hashtable h;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_Aduan", rs.getString("id_Aduan"));
		    	  h.put("no_Aduan_Online", rs.getString("no_Aduan_Online"));
		    	  h.put("jenis_Aduan", rs.getString("jenis_Aduan")==null?"":rs.getString("jenis_Aduan"));
		    	  h.put("aduan",rs.getString("aduan")== null?"":rs.getString("aduan"));
		    	  h.put("nama_Pengadu",rs.getString("nama_Pengadu")== null?"":rs.getString("nama_Pengadu"));
		    	  h.put("no_Tel_Rumah",rs.getString("no_Tel_Rumah")== null?"":rs.getString("no_Tel_Rumah"));
		    	  h.put("no_Tel_Bimbit",rs.getString("no_Tel_Bimbit")== null?"":rs.getString("no_Tel_Bimbit"));
		    	  h.put("emel",rs.getString("emel")== null?"":rs.getString("emel"));
		    	  h.put("tarikh_Aduan",rs.getDate("tarikh_Aduan") == null?"":sdf.format(rs.getDate("tarikh_Aduan")));
		    	  list.addElement(h);

		    	  
		      }
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }  
	}
	 public static Vector getData(){
		 
		  return list;
	  }


}
