package ekptg.model.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmAmbilTindakanKeAtasAduan {
	
	private static Vector list = new Vector();
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
		      r.add("e.nama_Seksyen");
		      r.add("a.tarikh_Pengagihan");
		      r.add("f.nama_Pegawai");
		      r.add("a.tarikh_Pengagihan_Kpdpegawai");
		      
		      r.add("a.id_Jenisaduan",r.unquote("c.id_Jenisaduan"));
		      r.add("a.id_Seksyen",r.unquote("e.id_Seksyen"));
		      r.add("a.id_Pegawai",r.unquote("f.id_Pegawai"));
		      r.add("a.id_Aduan",id);
		      r.add("a.id_Aduan",r.unquote("d.id_Aduan"));
		      r.add("b.id_Pengadu",r.unquote("d.id_Pengadu"));
		      
		      sql = r.getSQLSelect("Tblonlineaduan a, Tblonlinepengadu b, Tblrujjenisaduan c, Tblonlinepengaduan d, Tblrujseksyen e, Tblrujpegawai f");

		      
		      
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
		    	  h.put("nama_Seksyen",rs.getString("nama_Seksyen")== null?"":rs.getString("nama_Seksyen"));
		    	  h.put("tarikh_Pengagihan",rs.getDate("tarikh_Pengagihan") == null?"":sdf.format(rs.getDate("tarikh_Pengagihan")));
		    	  h.put("nama_Pegawai",rs.getString("nama_Pegawai")== null?"":rs.getString("nama_Pegawai"));
		    	  h.put("tarikh_Pengagihan_Kpdpegawai",rs.getDate("tarikh_Pengagihan_Kpdpegawai") == null?"":sdf.format(rs.getDate("tarikh_Pengagihan_Kpdpegawai")));

		    	  
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
	 public static void updateAduan(Hashtable data) throws Exception {
		 
		 Db db = null;
		    String sql = "";
		    try
		    {
		    	 int idAduan = (Integer)data.get("id_Aduan");
		    	 String tindakanSusulan = (String)data.get("tindakan_Susulan");
		    	 String idKemaskini = (String)data.get("id_Kemaskini");
		    	 
		    	 db = new Db();
				 Statement stmt = db.getStatement();
				 SQLRenderer r = new SQLRenderer();
				 
				 r.update("id_Aduan", idAduan);
				 r.add("tindakan_Susulan", tindakanSusulan);
				 r.add("id_Status","152");
				 r.add("id_Kemaskini",idKemaskini);
				 r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
				 
				 sql = r.getSQLUpdate("tblonlineaduan");
			     stmt.executeUpdate(sql);
		    }finally {
			      if (db != null) db.close();
		    }
	 }

}
