package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmPinjamanFailData {
	
	Vector paparFail = null;
	static Vector paparPergerakan = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setDataFail(String id)throws Exception {
		
		 Db db = null;
		 String sql = "";
		 
		 try {
			  paparFail = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Fail");
		      r.add("a.no_Fail");
		      r.add("a.tajuk_Fail");
		      r.add("b.keterangan");
		      r.add("d.keterangan as status_pergerakan");
		      r.add("e.user_Name");
		      r.add("a.tarikh_Daftar_Fail");
		      
		      r.add("a.id_Fail",id);
		      r.add("a.id_Status",r.unquote("b.id_Status(+)"));
		      r.add("a.id_Fail",r.unquote("c.id_Fail(+)"));
		      r.add("c.id_Status",r.unquote("d.id_Status(+)"));
		      r.add("a.id_Masuk",r.unquote("e.user_Id(+)"));
		     
		    
		      sql = r.getSQLSelect("Tblpfdfail a, Tblrujstatus b, Tblpfdpergerakanfail c, Tblrujstatus d, users e");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("idFail",rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail"));
		    	  h.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  h.put("keterangan1",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    	  h.put("keterangan2",rs.getString("status_pergerakan")== null?"":rs.getString("status_pergerakan"));
		    	  h.put("user_Name", rs.getString("user_Name") == null?"":rs.getString("user_Name"));
		    	  h.put("tarikh_Daftar_Fail", rs.getString("tarikh_Daftar_Fail") == null?"":sdf.format(rs.getDate("tarikh_Daftar_Fail")));
		    	  paparFail.addElement(h);
		    	  
		    	  
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	
	public static String addPinjaman(Hashtable data)throws Exception{
		
		Db db = new Db();
	    String sql = "";
	    try
	    {
	    	
	    	long idPergerakanFail = DB.getNextID("TBLPFDPERGERAKANFAIL_SEQ");
	    	String idFail = (String)data.get("idFail");
	    	String tarikhPinjaman = (String)data.get("tarikhPinjaman");
	    	String tkhPinjaman = "to_date('" + tarikhPinjaman + "','dd/MM/yyyy')";
	    	String flagPinjamanFail = (String)data.get("flagPinjamanFail");
	    	String tempohBiasaDari = (String)data.get("tempohBiasaDari");
	    	String tmphBiasaDari = "to_date('" + tempohBiasaDari + "','dd/MM/yyyy')";
	    	String tempohBiasaHingga = (String)data.get("tempohBiasaHingga");
	    	String tmphBiasaHingga = "to_date('" + tempohBiasaHingga + "','dd/MM/yyyy')";
	    	String tempohSdpDari = (String)data.get("tempohSdpDari");
	    	String tmphSdpDari = "to_date('" + tempohSdpDari + "','dd/MM/yyyy')";
	    	String tempohSdpHingga = (String)data.get("tempohSdpHingga");
	    	String tmphSdpHingga = "to_date('" + tempohSdpHingga + "','dd/MM/yyyy')";
	    	String tujuanPinjaman = (String)data.get("tujuanPinjaman");
	    	String flagLanjutTempoh = (String)data.get("flagLanjutTempoh");
	    	String tempohLanjutDari = (String)data.get("tempohLanjutDari");
	    	String tmphLanjutDari = "to_date('" + tempohLanjutDari + "','dd/MM/yyyy')";
	    	String tempohLanjutHingga = (String)data.get("tempohLanjutHingga");
	    	String tmphLanjutHingga = "to_date('" + tempohLanjutHingga + "','dd/MM/yyyy')";
	    	String idMasuk = (String)data.get("id_Masuk");
	    	String idPeminjam = (String)data.get("idPeminjam");
	    	
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		    r.add("id_Pergerakanfail",idPergerakanFail);
		    r.add("id_Fail",idFail);
		    r.add("id_Peminjam",idPeminjam);
		    r.add("tarikh_Permohonan_Pinjaman",r.unquote(tkhPinjaman));
		    r.add("flag_Pinjaman_Fail",flagPinjamanFail);
		    r.add("tempoh_Biasa_Dari",r.unquote(tmphBiasaDari));
		    r.add("tempoh_Biasa_Hingga",r.unquote(tmphBiasaHingga));
		    r.add("tempoh_Sdp_Dari",r.unquote(tmphSdpDari));
		    r.add("tempoh_Sdp_Hingga",r.unquote(tmphSdpHingga));
		    r.add("tujuan_Pinjaman",tujuanPinjaman);
		    r.add("flag_Lanjut_Tempoh",flagLanjutTempoh);
		    r.add("tempoh_Lanjut_Dari",r.unquote(tmphLanjutDari));
		    r.add("tempoh_Lanjut_Hingga",r.unquote(tmphLanjutHingga));
		    r.add("id_Masuk",idMasuk);
		    r.add("tarikh_Masuk",r.unquote("sysdate"));
		    sql = r.getSQLInsert("tblpfdpergerakanfail");
		    System.out.println("sql add pergerakan " +sql);
		    stmt.executeUpdate(sql);
	    	
	    	return ""+idPergerakanFail;
	    }finally {
		      if (db != null) db.close();
	    }
	     
		
	}
	public static void updatePinjaman(Hashtable data)throws Exception{
		
		Db db = new Db();
	    String sql = "";
	    try
	    {
	    	
	    	String idPergerakanFail = (String)data.get("idPergerakanfail");
	    	String idFail = (String)data.get("idFail");
	    	String tarikhPinjaman = (String)data.get("tarikhPinjaman");
	    	String tkhPinjaman = "to_date('" + tarikhPinjaman + "','dd/MM/yyyy')";
	    	String flagPinjamanFail = (String)data.get("flagPinjamanFail");
	    	String tempohBiasaDari = (String)data.get("tempohBiasaDari");
	    	String tmphBiasaDari = "to_date('" + tempohBiasaDari + "','dd/MM/yyyy')";
	    	String tempohBiasaHingga = (String)data.get("tempohBiasaHingga");
	    	String tmphBiasaHingga = "to_date('" + tempohBiasaHingga + "','dd/MM/yyyy')";
	    	String tempohSdpDari = (String)data.get("tempohSdpDari");
	    	String tmphSdpDari = "to_date('" + tempohSdpDari + "','dd/MM/yyyy')";
	    	String tempohSdpHingga = (String)data.get("tempohSdpHingga");
	    	String tmphSdpHingga = "to_date('" + tempohSdpHingga + "','dd/MM/yyyy')";
	    	String tujuanPinjaman = (String)data.get("tujuanPinjaman");
	    	String flagLanjutTempoh = (String)data.get("flagLanjutTempoh");
	    	String tempohLanjutDari = (String)data.get("tempohLanjutDari");
	    	String tmphLanjutDari = "to_date('" + tempohLanjutDari + "','dd/MM/yyyy')";
	    	String tempohLanjutHingga = (String)data.get("tempohLanjutHingga");
	    	String tmphLanjutHingga = "to_date('" + tempohLanjutHingga + "','dd/MM/yyyy')";
	    	String idKemaskini = (String)data.get("id_Kemaskini");
	    	
	    	db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		    r.update("id_Pergerakanfail",idPergerakanFail);
		    r.add("id_Fail",idFail);
		    r.add("tarikh_Permohonan_Pinjaman",r.unquote(tkhPinjaman));
		    r.add("flag_Pinjaman_Fail",flagPinjamanFail);
		    r.add("tempoh_Biasa_Dari",r.unquote(tmphBiasaDari));
		    r.add("tempoh_Biasa_Hingga",r.unquote(tmphBiasaHingga));
		    r.add("tempoh_Sdp_Dari",r.unquote(tmphSdpDari));
		    r.add("tempoh_Sdp_Hingga",r.unquote(tmphSdpHingga));
		    r.add("tujuan_Pinjaman",tujuanPinjaman);
		    r.add("flag_Lanjut_Tempoh",flagLanjutTempoh);
		    r.add("tempoh_Lanjut_Dari",r.unquote(tmphLanjutDari));
		    r.add("tempoh_Lanjut_Hingga",r.unquote(tmphLanjutHingga));
		    r.add("id_Kemaskini",idKemaskini);
		    r.add("tarikh_Kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("tblpfdpergerakanfail");
		    stmt.executeUpdate(sql);
	    	
	    	
	    }finally {
		      if (db != null) db.close();
	    }
	     
		
	}
	
	public static void paparPergerakan(String idPergerakan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparPergerakan = new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		    r.add("b.user_Name");
		    r.add("a.tarikh_Permohonan_Pinjaman");
		    r.add("a.flag_Pinjaman_Fail");
		    r.add("a.tempoh_Biasa_Dari");
		    r.add("a.tempoh_Biasa_Hingga");
		    r.add("a.tempoh_Sdp_Dari");
		    r.add("a.tempoh_Sdp_Hingga");
		    r.add("a.tujuan_Pinjaman");
		    r.add("a.flag_Lanjut_Tempoh");
		    r.add("a.tempoh_Lanjut_Dari");
		    r.add("a.tempoh_Lanjut_Hingga");
		    r.add("id_Pergerakanfail",idPergerakan);
		    r.add("b.user_id",r.unquote("a.id_Peminjam"));
			
		    sql = r.getSQLSelect("Tblpfdpergerakanfail a,users b");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("namaPeminjam",rs.getString("user_Name"));
		    	  h.put("tarikhPrmhnnPinjaman", rs.getString("tarikh_Permohonan_Pinjaman")== null?"":sdf.format(rs.getDate("tarikh_Permohonan_Pinjaman")));
		    	  h.put("flagPinjamanFail",rs.getString("flag_Pinjaman_Fail")== null?"":rs.getString("flag_Pinjaman_Fail"));
		    	  h.put("tempohBiasaDari",rs.getString("tempoh_Biasa_Dari")== null?"":sdf.format(rs.getDate("tempoh_Biasa_Dari")));
		    	  h.put("tempohBiasaHingga",rs.getString("tempoh_Biasa_Hingga")== null?"":sdf.format(rs.getDate("tempoh_Biasa_Hingga")));
		    	  h.put("tempohSdpDari", rs.getString("tempoh_Sdp_Dari") == null?"":sdf.format(rs.getDate("tempoh_Sdp_Dari")));
		    	  h.put("tempohSdpHingga", rs.getString("tempoh_Sdp_Hingga") == null?"":sdf.format(rs.getDate("tempoh_Sdp_Hingga")));
		    	  h.put("tujuan_Pinjaman",rs.getString("tujuan_Pinjaman")== null?"":rs.getString("tujuan_Pinjaman"));
		    	  h.put("flagLanjutTempoh", rs.getString("flag_Lanjut_Tempoh")== null?"": rs.getString("flag_Lanjut_Tempoh"));
		    	  h.put("tempohLanjutDari",rs.getString("tempoh_Lanjut_Dari")== null?"":sdf.format(rs.getDate("tempoh_Lanjut_Dari")));
		    	  h.put("tempohLanjutHingga",rs.getString("tempoh_Lanjut_Hingga")== null?"":sdf.format(rs.getDate("tempoh_Lanjut_Hingga")));
		    	  h.put("idPergerakanfail",rs.getString("id_Pergerakanfail")== null?"":rs.getString("id_Pergerakanfail"));
		    
		    	  paparPergerakan.addElement(h);
		    	  
		    	  
		    	  
		      }
		}
		finally {
		      if (db != null) db.close();
	    }
		
	}
	
	public Vector getDataFail(){
		 
		  return paparFail;
	}
	
	public Vector getDataPergerakan(){
		 
		  return paparPergerakan;
	}


}
