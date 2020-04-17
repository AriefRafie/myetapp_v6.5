package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraMaklumatPampasanPBTerimaCekData {
	
	static Vector paparTerimaCek = null;
	static Vector checkHakmilik = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void setDataCheck(String idHakmilikpb)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			checkHakmilik = new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT ID_BAYARAN FROM TBLPPTBAYARAN WHERE CARA_BAYAR = '1' AND ID_HAKMILIKPB = '"+idHakmilikpb+"'";
			
		    ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_BAYARAN", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
		    	  checkHakmilik.addElement(h);
		      }
		      
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public static void setDataTerimaCek(String idBayaran)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparTerimaCek= new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT A.ID_BAYARAN,A.TARIKH_TERIMA,C.NAMA_PB,A.NO_BAYARAN,A.AMAUN_BAYARAN,A.TARIKH_AKHIR_CEK,A.TARIKH_CEK,C.NO_PB," +
    			  " A.NO_EFT,A.NO_RUJUKAN_SURATEFT,A.TARIKH_TERIMA_EFT,A.TARIKH_SURAT_EFT,A.TARIKH_BAYARAN_EFT," +
		          " A.TARIKH_SERAH_CEK,A.FLAG_SERAH_CEK,A.TARIKH_AMBIL_CEK,A.MASA_AMBIL_CEK FROM TBLPPTBAYARAN A, TBLPPTHAKMILIKPB B, TBLPPTPIHAKBERKEPENTINGAN C" +
		    		" WHERE B.ID_HAKMILIKPB = A.ID_HAKMILIKPB AND C.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN AND A.ID_BAYARAN= '"+idBayaran+"'";
		    
		    ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_BAYARAN", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
		    	  h.put("TARIKH_TERIMA", rs.getString("TARIKH_TERIMA")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA")));
		    	  h.put("NAMA_PB", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	  h.put("NO_BAYARAN", rs.getString("NO_BAYARAN")==null?"":rs.getString("NO_BAYARAN"));
		    	  h.put("AMAUN_BAYARAN", rs.getString("AMAUN_BAYARAN")==null?"":rs.getString("AMAUN_BAYARAN"));
		    	  h.put("TARIKH_AKHIR_CEK", rs.getString("TARIKH_AKHIR_CEK")==null?"":sdf.format(rs.getDate("TARIKH_AKHIR_CEK")));
		    	  h.put("TARIKH_CEK", rs.getString("TARIKH_CEK")==null?"":sdf.format(rs.getDate("TARIKH_CEK")));
		    	  h.put("NO_PB", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("TARIKH_SERAH_CEK", rs.getString("TARIKH_SERAH_CEK")==null?"":sdf.format(rs.getDate("TARIKH_SERAH_CEK")));
		    	  h.put("FLAG_SERAH_CEK", rs.getString("FLAG_SERAH_CEK")==null?"":rs.getString("FLAG_SERAH_CEK"));
		    	  h.put("NO_EFT", rs.getString("NO_EFT")==null?"":rs.getString("NO_EFT"));
		    	  h.put("NO_RUJUKAN_SURATEFT", rs.getString("NO_RUJUKAN_SURATEFT")==null?"":rs.getString("NO_RUJUKAN_SURATEFT"));
		    	  h.put("TARIKH_TERIMA_EFT", rs.getString("TARIKH_TERIMA_EFT")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_EFT")));
		    	  h.put("TARIKH_SURAT_EFT", rs.getString("TARIKH_SURAT_EFT")==null?"":sdf.format(rs.getDate("TARIKH_SURAT_EFT")));
		    	  h.put("TARIKH_BAYARAN_EFT", rs.getString("TARIKH_BAYARAN_EFT")==null?"":sdf.format(rs.getDate("TARIKH_BAYARAN_EFT")));
		    	  h.put("TARIKH_AMBIL_CEK", rs.getString("TARIKH_AMBIL_CEK")==null?"":sdf.format(rs.getDate("TARIKH_AMBIL_CEK")));
		    	  h.put("MASA_AMBIL_CEK", rs.getString("MASA_AMBIL_CEK")==null?"":rs.getString("MASA_AMBIL_CEK"));

		    	  paparTerimaCek.addElement(h);
		    	  
		      }
			
		}
		finally {
			if(db != null) db.close();
		}

		
	}
	public static String simpanTerimaCek(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			long idBayaran = DB.getNextID("TBLPPTBAYARAN_SEQ");
			String idHakmilikPB = (String)data.get("idHakmilikPB");
			String tarikhTerimaCek = (String)data.get("tarikhTerimaCek");
			String noCek = (String)data.get("noCek");
			String tarikhCek = (String)data.get("tarikhCek");
			String amaunCek = (String)data.get("amaunCek");
			String tarikhAkhirCek = (String)data.get("tarikhAkhirCek");
			String masaAmbilCek = (String)data.get("masaAmbilCek");
			String tarikhAmbilCek = (String)data.get("tarikhAmbilCek");
			String idMasuk = (String)data.get("idMasuk");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("id_Bayaran",idBayaran);
			r.add("id_Hakmilikpb",idHakmilikPB);
			r.add("tarikh_Terima",r.unquote("to_date('" + tarikhTerimaCek + "','dd/MM/yyyy')"));
			r.add("no_Bayaran",noCek);
			r.add("tarikh_Cek",r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			r.add("amaun_Bayaran",amaunCek);
			r.add("tarikh_Akhir_Cek",r.unquote("to_date('" + tarikhAkhirCek + "','dd/MM/yyyy')"));
			r.add("masa_Ambil_Cek",masaAmbilCek);
			r.add("tarikh_Ambil_Cek",r.unquote("to_date('" + tarikhAmbilCek + "','dd/MM/yyyy')"));
			r.add("jenis_Award","1");
			r.add("cara_Bayar","1");
			r.add("flag_Terima_Cek","1");
			r.add("id_Masuk",idMasuk);
			r.add("tarikh_Masuk",r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptbayaran");
			stmt.executeUpdate(sql);
			
			return ""+idBayaran;
						
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public static void updateTerimaCek(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			String idBayaran = (String)data.get("idBayaran");
			String tarikhTerimaCek = (String)data.get("tarikhTerimaCek");
			String noCek = (String)data.get("noCek");
			String tarikhCek = (String)data.get("tarikhCek");
			String amaunCek = (String)data.get("amaunCek");
			String tarikhAkhirCek = (String)data.get("tarikhAkhirCek");
			String masaAmbilCek = (String)data.get("masaAmbilCek");
			String tarikhAmbilCek = (String)data.get("tarikhAmbilCek");
			String idKemaskini = (String)data.get("idKemaskini");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("id_Bayaran",idBayaran);
			r.add("tarikh_Terima",r.unquote("to_date('" + tarikhTerimaCek + "','dd/MM/yyyy')"));
			r.add("no_Bayaran",noCek);
			r.add("tarikh_Cek",r.unquote("to_date('" + tarikhCek + "','dd/MM/yyyy')"));
			r.add("amaun_Bayaran",amaunCek);
			r.add("tarikh_Akhir_Cek",r.unquote("to_date('" + tarikhAkhirCek + "','dd/MM/yyyy')"));
			r.add("masa_Ambil_Cek",masaAmbilCek);
			r.add("tarikh_Ambil_Cek",r.unquote("to_date('" + tarikhAmbilCek + "','dd/MM/yyyy')"));
			r.add("id_Kemaskini",idKemaskini);
			r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptbayaran");
			stmt.executeUpdate(sql);
			
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	public static Vector getDataTerimaCek(){
		return paparTerimaCek;
	}
	
	public static Vector getDataCheck(){
		return checkHakmilik;
	}

}
