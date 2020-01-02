package ekptg.model.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.File;

public class FrmKemaskiniPengesahanPembayaranData {
	
	public static void addPembayaran(Hashtable data) throws Exception{
		
		Db db = null;
	    String sql = "";  
	    Date now = new Date();
	    try
	    {	 
	    	long idBayaran = DB.getNextID("TBLPEMBAYARANONLINE_SEQ");
	    	Integer id_Jenisbayaran = (Integer)data.get("id_Jenisbayaran");
	    	Integer modeBayaran = (Integer)data.get("mod_Pembayaran");
	    	String noAkaunBil = (String)data.get("no_Akaun_Bil");
	    	String kaedahPembayaran = (String)data.get("kaedahPembayaran");
	    	String idBank = (String)data.get("bank");
	    	String noAkaunPembayar = (String)data.get("noAkaunPembayar");
	    	String namaPemegangAkaun = (String)data.get("namaPemegangAkaun");
	    	String noAkaunPenerima = (String)data.get("noAkaunPenerima");
	    	String emel = (String)data.get("emel");
	    	String amaunBayaran = (String)data.get("amaun_Bayaran");
	    	String idMasuk = (String)data.get("id_Masuk");
	    	
	    	
	    	 db = new Db();
		     Statement stmt = db.getStatement();
		     SQLRenderer r = new SQLRenderer();
		     
		     r = new SQLRenderer();
		     r.add("id_Pembayaranonline",idBayaran);
		     r.add("id_Status","159");
		     r.add("tarikh_Pembayaran",r.unquote("sysdate"));
		     r.add("tarikh_Efektif",r.unquote("sysdate"));
		     r.add("id_Jenisbayaran",id_Jenisbayaran);
		     r.add("mod_Pembayaran",modeBayaran);
		     r.add("no_Akaun_Bil",noAkaunBil);
		     r.add("kaedah_Pembayaran",kaedahPembayaran);
		     r.add("id_Bank",idBank);
		     r.add("no_Akaun",noAkaunPembayar);
		     r.add("amaun_Bayaran",amaunBayaran);
		     r.add("nama_Pemegang_Akaun",namaPemegangAkaun);
		     r.add("no_Akaun_Penerima",noAkaunPenerima);
		     r.add("id_Masuk",idMasuk);
		     r.add("id_Pengguna",idMasuk);
		     r.add("tarikh_Masuk",r.unquote("sysdate"));
		     r.add("emel",emel);
		     
		     if (modeBayaran == 1){
		    	 r.add("no_Transaksi_Fpx",generateNoSeqFPX(id_Jenisbayaran));
		     }
		     else{
		    	 r.add("no_Rujukan",generateNoSeqInternetBank(id_Jenisbayaran));
		     }
		     
		     sql = r.getSQLInsert("tblpembayaranonline");
		     
		     System.out.println("SQL tambah bayaran :" +sql);
		     
		     
		     stmt.executeUpdate(sql);
		     
	    	
	    }finally {
		      if (db != null) db.close();
	    }
	}

	public static String generateNoSeqFPX(Integer id_Jenisbayaran) throws Exception{
	String sql = "";
	String noFPX = "";
	
	Db db = null;
	db = new Db();
    Statement stmt = db.getStatement();
    
    
    	sql = "select a.kod_jenis_bayaran"+
		" from  tblrujjenisbayaran a"+
		" where a.id_Jenisbayaran = " + id_Jenisbayaran ; 
		
    	
    	ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		
		
		noFPX =   rs.getString(1) + String.format("%09d",File.getSeqNoFPXOnline(id_Jenisbayaran));
					
		System.out.println("No FPX : " + noFPX);

		return noFPX;
	
		
    }
	
	public static String generateNoSeqInternetBank(Integer id_Jenisbayaran) throws Exception{
		String sql = "";
	String noInternetBank = "";
	
	Db db = null;
	db = new Db();
    Statement stmt = db.getStatement();
    
    
	sql = "select a.kod_jenis_bayaran"+
	" from  tblrujjenisbayaran a"+
	" where a.id_Jenisbayaran = " + id_Jenisbayaran ; 
	
		
    	
    	ResultSet rs = stmt.executeQuery(sql);
		rs.next();

		noInternetBank =   rs.getString(1) + String.format("%09d",File.getSeqNoInternetBankOnline(id_Jenisbayaran));
					
		System.out.println("No Internet Bank : " + noInternetBank);

		return noInternetBank;
	
		
    }
	 

}
