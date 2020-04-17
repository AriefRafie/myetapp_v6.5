package ekptg.model.ppt;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class BantahanPampasanOperations {
	static Logger myLogger = Logger.getLogger(BantahanPampasanOperations.class);
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	
	public void add_terimaCek(String usid, String no_pb, String id_hakmilik, String id_pihakberkepentingan, String txdTkhTerima, String txtPenamaCek, String txtNoCek,
			String txdTkhCek, String txtAmaunCek, String txdTkhAkhirCek, String id_hakmilikpb, 
			String id_bantahan, String txdTkhAmbilCek, String txtMasaAmbilCek) throws Exception {
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	    	long id_bayaran = DB.getNextID("TBLPPTBAYARAN_SEQ");
	    	String TT = "to_date('" + txdTkhTerima + "','dd/MM/yyyy')";
	    	String TC = "to_date('" + txdTkhCek + "','dd/MM/yyyy')";
	    	String TAC = "to_date('" + txdTkhAkhirCek + "','dd/MM/yyyy')";
	    	String TAMC = "to_date('" + txdTkhAmbilCek + "','dd/MM/yyyy')";
	    		    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		    r.add("no_bayaran",txtNoCek);
		    r.add("amaun_bayaran",txtAmaunCek);		    
		    r.add("tarikh_terima",r.unquote(TT));		    
		    r.add("cara_bayar",1);				// 1=CEK @ 2=EFT
		    r.add("tarikh_cek",r.unquote(TC));
		    r.add("jenis_award",2);				// 1=PAMPASAN BIASA @ 2=PAMPASAN TAMBAHAN(BANTAH) @ 3=PAMPASAN TAMBAHAN(PU)
		    r.add("penerima_cek",txtPenamaCek);
		    r.add("no_pb",no_pb);
		    r.add("id_pihakberkepentingan",id_pihakberkepentingan);	
		    r.add("id_hakmilikpb",id_hakmilikpb);
		    r.add("tarikh_akhir_cek",r.unquote(TAC));
		    r.add("tarikh_ambil_cek",r.unquote(TAMC));
		    r.add("masa_ambil_cek",txtMasaAmbilCek);
		    r.add("id_bantahan",id_bantahan);
		    r.add("id_masuk",usid);
			r.add("tarikh_masuk",r.unquote("sysdate"));		
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));				
		    sql = r.getSQLInsert("Tblpptbayaran");	
		    myLogger.info("add_terimaCek = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    r.clear();
		    
		    r.update("id_hakmilikpb", id_hakmilikpb);
		    r.add("flag_bayar_bantahan",1); 	// 1=YA @ 2=TIDAK [STATUS PEMBAYARAN PAMPASAN TAMBAHAN]
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblppthakmilikpb");	
		    myLogger.info("EDIT TBLPPTHAKMILIKPB = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    r.clear();
		    
		    r.update("id_bantahan", id_bantahan);
		    r.add("status_bantahan",186); 		// 186 = URUSAN BAYARAN BANTAHAN
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("EDIT TBLPPTBANTAHAN = "+sql);
		    stmt.executeUpdate(sql);		    
		    
		    } finally {
		      if (db != null) db.close();
		    }	
	}

	
	public void add_terimaCekAgensi(String usid, String no_pb, String id_hakmilik, String id_pihakberkepentingan, String txdTkhTerima, String txtPenamaCek, String txtNoCek,
			String txdTkhCek, String txtAmaunCek, String txdTkhAkhirCek, String id_hakmilikpb, 
			String id_bantahan, String txdTkhAmbilCek, String txtMasaAmbilCek, String id_agensi) throws Exception {
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	    	long id_bayaran = DB.getNextID("TBLPPTBAYARAN_SEQ");
	    	String TT = "to_date('" + txdTkhTerima + "','dd/MM/yyyy')";
	    	String TC = "to_date('" + txdTkhCek + "','dd/MM/yyyy')";
	    	String TAC = "to_date('" + txdTkhAkhirCek + "','dd/MM/yyyy')";
	    	String TAMC = "to_date('" + txdTkhAmbilCek + "','dd/MM/yyyy')";
	    		    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		    r.add("no_bayaran",txtNoCek);
		    r.add("amaun_bayaran",txtAmaunCek);		    
		    r.add("tarikh_terima",r.unquote(TT));		    
		    r.add("cara_bayar",1);				// 1=CEK @ 2=EFT
		    r.add("tarikh_cek",r.unquote(TC));
		    r.add("jenis_award",2);				// 1=PAMPASAN BIASA @ 2=PAMPASAN TAMBAHAN(BANTAH) @ 3=PAMPASAN TAMBAHAN(PU)
		    r.add("penerima_cek",txtPenamaCek);
//		    r.add("no_pb",no_pb);
//		    r.add("id_pihakberkepentingan",id_pihakberkepentingan);	
//		    r.add("id_hakmilikpb",id_hakmilikpb);
		    r.add("id_hakmilik",id_hakmilik);
		    r.add("id_agensi",id_agensi);
		    r.add("tarikh_akhir_cek",r.unquote(TAC));
		    r.add("tarikh_ambil_cek",r.unquote(TAMC));
		    r.add("masa_ambil_cek",txtMasaAmbilCek);
		    r.add("id_masuk",usid);
			r.add("tarikh_masuk",r.unquote("sysdate"));			
		    sql = r.getSQLInsert("Tblpptbayaran");	
		    myLogger.info("add_terimaCek = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    r.clear();
		    
		    r.update("id_hakmilik", id_hakmilik);
		    r.add("flag_bayar_bantahan",1); 	// 1=YA @ 2=TIDAK [STATUS PEMBAYARAN PAMPASAN TAMBAHAN]
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblppthakmilik");	
		    myLogger.info("EDIT TBLPPTHAKMILIK = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    r.clear();
		    
		    r.update("id_bantahan", id_bantahan);
		    r.add("status_bantahan_ap",204); 		// 204 = URUSAN BAYARAN(AGENSI)
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("EDIT TBLPPTBANTAHAN = "+sql);
		    stmt.executeUpdate(sql);		    
		    
		    } finally {
		      if (db != null) db.close();
		    }	
	}
	
	
	
//	public void updateStatusUrusanBayaran(String id_permohonan, String usid) throws Exception {
//	    Db db = null;
//	    String sql = "";	    
//	    try
//	    {			  
//		    db = new Db();
//		    Statement stmt = db.getStatement();
//		    SQLRenderer r = new SQLRenderer();
//		    r.update("id_permohonan", id_permohonan);		    
//		    r.add("id_status", 186);	// 186=URUSAN BAYARAN
//		    r.add("id_kemaskini",usid);
//			r.add("tarikh_kemaskini",r.unquote("sysdate"));
//		    sql = r.getSQLUpdate("Tblpptpermohonan");	
//		    myLogger.info("EDIT IDSTATUS TBLPPTPERMOHONAN = "+sql);
//		    stmt.executeUpdate(sql);		    
//	    	}
//	    	finally {
//	    		if (db != null) db.close();
//	    	}				
//	}

	public void update_terimaCek(String usid, String id_bayaran, String txdTkhTerima, String txtNoCek, 
			String txdTkhCek, String txdTkhAkhirCek, String txdTkhAmbilCek, String txtMasaAmbilCek) throws Exception {
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {	
	    	String TT = "to_date('" + txdTkhTerima + "','dd/MM/yyyy')";
	    	String TC = "to_date('" + txdTkhCek + "','dd/MM/yyyy')";
	    	String TAC = "to_date('" + txdTkhAkhirCek + "','dd/MM/yyyy')";
	    	String TAMC = "to_date('" + txdTkhAmbilCek + "','dd/MM/yyyy')";
	    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bayaran", id_bayaran);	
		    r.add("no_bayaran",txtNoCek);
		    r.add("tarikh_terima",r.unquote(TT));
		    r.add("tarikh_cek",r.unquote(TC));
		    r.add("tarikh_akhir_cek",r.unquote(TAC));
		    r.add("tarikh_ambil_cek",r.unquote(TAMC));
		    r.add("masa_ambil_cek",txtMasaAmbilCek);
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptbayaran");	
		    myLogger.info("EDIT TBLPPTBAYARAN = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}					
	}

	public void update_serahCek(String usid, String id_bayaran, String id_bantahan, String txtPenerimaX, String txtNoKPx, 
			String txdTkhSerah, String socStatusSerah) throws Exception {
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {	
	    	String TS = "to_date('" + txdTkhSerah + "','dd/MM/yyyy')";	    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bayaran", id_bayaran);	
		    r.add("nama_wakil",txtPenerimaX);
		    r.add("no_wakil",txtNoKPx);
		    r.add("tarikh_serah_cek",r.unquote(TS));
		    r.add("flag_serah_cek",1); 			//STATUS SERAHAN CEK  1=DISERAHKAN @ 2=TIDAK DISERAHKAN
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptbayaran");	
		    myLogger.info("EDIT TBLPPTBAYARAN = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    r.clear();
		    
		    r.update("id_bantahan", id_bantahan);
		    r.add("status_bantahan",187); 		// 187 = SELESAI
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("EDIT TBLPPTBANTAHAN = "+sql);
		    stmt.executeUpdate(sql);		    		    
		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}									
	}
	
	public void update_serahCekAgensi(String usid, String id_bayaran, String id_bantahan, String txtPenerimaX, String txtNoKPx, 
			String txdTkhSerah, String socStatusSerah, String id_agensi) throws Exception {
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {	
	    	String TS = "to_date('" + txdTkhSerah + "','dd/MM/yyyy')";	    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bayaran", id_bayaran);	
		    r.add("nama_wakil",txtPenerimaX);
		    r.add("no_wakil",txtNoKPx);
		    r.add("id_agensi",id_agensi);
		    r.add("tarikh_serah_cek",r.unquote(TS));
		    r.add("flag_serah_cek",1); 			//STATUS SERAHAN CEK  1=DISERAHKAN @ 2=TIDAK DISERAHKAN
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptbayaran");	
		    myLogger.info("EDIT TBLPPTBAYARAN = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    r.clear();
		    
		    r.update("id_bantahan", id_bantahan);
		    r.add("status_bantahan_ap",205); 		// 205 = SELESAI BANTAHAN(AGENSI)
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("EDIT TBLPPTBANTAHAN = "+sql);
		    stmt.executeUpdate(sql);		    		    
		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}									
	}	

	public void updateStatusSelesai(String id_permohonan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", id_permohonan);		    
		    r.add("id_status", 187);	// 187 = SELESAI
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptpermohonan");	
		    myLogger.info("EDIT IDSTATUS TBLPPTPERMOHONAN = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}				
	}

	public void simpan_eft(String usid, String id_bantahan, String txtNoRujSurat,String txdTkhSurat, String txtNoEFT, 
			String txdTkhTerimaSurat,String txdTkhBayaran, String txtNoBaucer, String txtAmaun, 
			String no_pb, String id_pihakberkepentingan, String id_hakmilikpb) throws Exception {

	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	    	long id_bayaran = DB.getNextID("TBLPPTBAYARAN_SEQ");
	    	String TSE = "to_date('" + txdTkhSurat + "','dd/MM/yyyy')";
	    	String TTS = "to_date('" + txdTkhTerimaSurat + "','dd/MM/yyyy')";
	    	String TBE = "to_date('" + txdTkhBayaran + "','dd/MM/yyyy')";
	    		    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		    r.add("no_rujukan_surateft",txtNoRujSurat);
		    r.add("tarikh_surat_eft",r.unquote(TSE));	
		    r.add("no_eft",txtNoEFT);
		    r.add("tarikh_terima_eft",r.unquote(TTS));	
		    r.add("tarikh_bayaran_eft",r.unquote(TBE));	
		    r.add("no_baucer",txtNoBaucer);  
		    r.add("amaun_bayaran",txtAmaun);		
		    r.add("cara_bayar",2);							// 1=CEK @ 2=EFT
		    r.add("jenis_award",2);							// 1=PAMPASAN BIASA @ 2=PAMPASAN TAMBAHAN(BANTAH) @ 3=PAMPASAN TAMBAHAN(PU)
		    r.add("no_pb",no_pb);
		    r.add("id_pihakberkepentingan",id_pihakberkepentingan);	
		    r.add("id_hakmilikpb",id_hakmilikpb);
		    r.add("id_bantahan",id_bantahan);
		    r.add("id_masuk",usid);
			r.add("tarikh_masuk",r.unquote("sysdate"));			
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));					
		    sql = r.getSQLInsert("Tblpptbayaran");	
		    myLogger.info("simpan_eft = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    r.clear();
		    
		    r.update("id_hakmilikpb", id_hakmilikpb);
		    r.add("flag_bayar_bantahan",1); 				// 1=YA @ 2=TIDAK [STATUS PEMBAYARAN PAMPASAN TAMBAHAN]
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblppthakmilikpb");	
		    myLogger.info("EDIT TBLPPTPIHAKBERKEPENTINGAN = "+sql);
		    stmt.executeUpdate(sql);
		    
		    r.clear();
		    
		    r.update("id_bantahan", id_bantahan);
		    r.add("status_bantahan",187);					// 187 = SELESAI
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("EDIT TBLPPTBANTAHAN = "+sql);
		    stmt.executeUpdate(sql);		    
		    
		    } finally {
		      if (db != null) db.close();
		    }			
	}
	
	
	public void simpan_eftAgensi(String usid, String id_bantahan, String txtNoRujSurat,String txdTkhSurat, String txtNoEFT, 
			String txdTkhTerimaSurat,String txdTkhBayaran, String txtNoBaucer, String txtAmaun, 
			String no_pb, String id_pihakberkepentingan, String id_hakmilikpb,String id_hakmilik,String id_agensi) throws Exception {

	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	    	long id_bayaran = DB.getNextID("TBLPPTBAYARAN_SEQ");
	    	String TSE = "to_date('" + txdTkhSurat + "','dd/MM/yyyy')";
	    	String TTS = "to_date('" + txdTkhTerimaSurat + "','dd/MM/yyyy')";
	    	String TBE = "to_date('" + txdTkhBayaran + "','dd/MM/yyyy')";
	    		    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		    r.add("no_rujukan_surateft",txtNoRujSurat);
		    r.add("tarikh_surat_eft",r.unquote(TSE));	
		    r.add("no_eft",txtNoEFT);
		    r.add("tarikh_terima_eft",r.unquote(TTS));	
		    r.add("tarikh_bayaran_eft",r.unquote(TBE));	
		    r.add("no_baucer",txtNoBaucer);  
		    r.add("amaun_bayaran",txtAmaun);		
		    r.add("cara_bayar",2);							// 1=CEK @ 2=EFT
		    r.add("jenis_award",2);							// 1=PAMPASAN BIASA @ 2=PAMPASAN TAMBAHAN(BANTAH) @ 3=PAMPASAN TAMBAHAN(PU)
//		    r.add("no_pb",no_pb);
//		    r.add("id_pihakberkepentingan",id_pihakberkepentingan);	
//		    r.add("id_hakmilikpb",id_hakmilikpb);
		    r.add("id_agensi",id_agensi);
		    r.add("id_hakmilik",id_hakmilik);
		    r.add("id_masuk",usid);
			r.add("tarikh_masuk",r.unquote("sysdate"));			
		    sql = r.getSQLInsert("Tblpptbayaran");	
		    myLogger.info("simpan_eft = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    r.clear();
		    
		    r.update("id_hakmilikpb", id_hakmilikpb);
		    r.add("flag_bayar_bantahan",1); 				// 1=YA @ 2=TIDAK [STATUS PEMBAYARAN PAMPASAN TAMBAHAN]
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblppthakmilik");	
		    myLogger.info("EDIT Tblppthakmilik = "+sql);
		    stmt.executeUpdate(sql);
		    
		    r.clear();
		    
		    r.update("id_bantahan", id_bantahan);
		    r.add("status_bantahan",187);					// 187 = SELESAI
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("EDIT TBLPPTBANTAHAN = "+sql);
		    stmt.executeUpdate(sql);		    
		    
		    } finally {
		      if (db != null) db.close();
		    }			
	}	

	public void edit_eft(String usid, String id_bayaran, String txtNoRujSurat,
			String txdTkhSurat, String txtNoEFT, String txdTkhTerimaSurat,
			String txdTkhBayaran, String txtNoBaucer, String txtAmaun) throws Exception{

	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	   
	    	String TSE = "to_date('" + txdTkhSurat + "','dd/MM/yyyy')";
	    	String TTS = "to_date('" + txdTkhTerimaSurat + "','dd/MM/yyyy')";
	    	String TBE = "to_date('" + txdTkhBayaran + "','dd/MM/yyyy')";
	    		    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bayaran", id_bayaran);
		    r.add("no_rujukan_surateft",txtNoRujSurat);
		    r.add("tarikh_surat_eft",r.unquote(TSE));	
		    r.add("no_eft",txtNoEFT);
		    r.add("tarikh_terima_eft",r.unquote(TTS));	
		    r.add("tarikh_bayaran_eft",r.unquote(TBE));	
		    r.add("no_baucer",txtNoBaucer);  
		    r.add("amaun_bayaran",txtAmaun);		
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
		    sql = r.getSQLUpdate("Tblpptbayaran");	
		    myLogger.info("edit_eft = "+sql);
		    stmt.executeUpdate(sql);	
		    
	    } finally {
		      if (db != null) db.close();
		    }		    
	}	
	
	//******************* TBLRUJSUBURUSANSTATUSBANTAHAN

	public static void urusanBayaran_tblrujsuburusanstatusbantahan(String usid,String id_bantahan, String id_permohonan,
			String id_hakmilik, String id_fail) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	   
	    try
	    {		    		    			
			db = new Db();			
				
			//** UPDATE TBLRUJSUBURUSANSTATUSFAIL				  
			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  
			  r2.update("id_bantahan", id_bantahan);

			  r2.add("aktif",0);
			  r2.add("id_kemaskini",usid);
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
			  
			  sql2 = r2.getSQLUpdate("tblrujsuburusanstatusbantahan");
			  myLogger.info("UPDATE TBLRUJSUBURUSANSTATUSBANTAHAN --> "+sql2);
			  stmt2.executeUpdate(sql2);
			  
			//** INSERT TBLRUJSUBURUSANSTATUSBANTAHAN
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("id_permohonan",id_permohonan);
				r.add("id_hakmilik",id_hakmilik);
				r.add("id_bantahan",id_bantahan);				
				r.add("id_suburusanstatus",1504);	// ID_SUBURUSANSTATUS URUSAN BAYARAN = 186
				r.add("aktif",1);
				r.add("id_fail",id_fail);
				r.add("id_masuk",usid);
				r.add("tarikh_masuk",r.unquote("sysdate"));
				r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));					

				sql = r.getSQLInsert("Tblrujsuburusanstatusbantahan");	
				myLogger.info("INSERT TBLRUJSUBURUSANSTATUSBANTAHAN --> "+sql);
				stmt.executeUpdate(sql);			  				  
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }	
		
	public static void selesaiPampasanBantahan_tblrujsuburusanstatusbantahan(String usid,String id_bantahan, String id_permohonan,
			String id_hakmilik, String id_fail) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	   
	    try
	    {		    		    			
			db = new Db();			
				
			//** UPDATE TBLRUJSUBURUSANSTATUSFAIL				  
			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  
			  r2.update("id_bantahan", id_bantahan);

			  r2.add("aktif",0);
			  r2.add("id_kemaskini",usid);
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
			  
			  sql2 = r2.getSQLUpdate("tblrujsuburusanstatusbantahan");
			  myLogger.info("UPDATE TBLRUJSUBURUSANSTATUSBANTAHAN --> "+sql2);
			  stmt2.executeUpdate(sql2);
			  
			//** INSERT TBLRUJSUBURUSANSTATUSBANTAHAN
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("id_permohonan",id_permohonan);
				r.add("id_hakmilik",id_hakmilik);
				r.add("id_bantahan",id_bantahan);				
				r.add("id_suburusanstatus",1505);	// ID_SUBURUSANSTATUS SELESAI BANTAHAN = 187
				r.add("aktif",1);
				r.add("id_fail",id_fail);
				r.add("id_masuk",usid);
				r.add("tarikh_masuk",r.unquote("sysdate"));
				r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));					

				sql = r.getSQLInsert("Tblrujsuburusanstatusbantahan");	
				myLogger.info("INSERT TBLRUJSUBURUSANSTATUSBANTAHAN --> "+sql);
				stmt.executeUpdate(sql);			  				  
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }		

	public static void selesaiPampasanBantahanAgensi_tblrujsuburusanstatusbantahan(String usid,String id_bantahan, String id_permohonan,
			String id_hakmilik, String id_fail) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	   
	    try
	    {		    		    			
			db = new Db();			
				
			//** UPDATE TBLRUJSUBURUSANSTATUSFAIL				  
			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  
			  r2.update("id_bantahan", id_bantahan);

			  r2.add("aktif",0);
			  r2.add("id_kemaskini",usid);
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
			  
			  sql2 = r2.getSQLUpdate("tblrujsuburusanstatusbantahan");
			  myLogger.info("UPDATE TBLRUJSUBURUSANSTATUSBANTAHAN --> "+sql2);
			  stmt2.executeUpdate(sql2);
			  
			//** INSERT TBLRUJSUBURUSANSTATUSBANTAHAN
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("id_permohonan",id_permohonan);
				r.add("id_hakmilik",id_hakmilik);
				r.add("id_bantahan",id_bantahan);				
				r.add("id_suburusanstatus",16102120);	// ID_SUBURUSANSTATUS SELESAI BANTAHAN(AGENSI) = 205
				r.add("aktif",1);
				r.add("id_fail",id_fail);
				r.add("id_masuk",usid);
				r.add("tarikh_masuk",r.unquote("sysdate"));
				r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));					

				sql = r.getSQLInsert("Tblrujsuburusanstatusbantahan");	
				myLogger.info("INSERT TBLRUJSUBURUSANSTATUSBANTAHAN --> "+sql);
				stmt.executeUpdate(sql);			  				  
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }


	public static void updateStatus_urusanBayaran(String idpermohonan,String idUser,String idstatus) throws Exception
	{
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_permohonan",idpermohonan);
		    		r.add("id_status",idstatus);      			
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",idUser);    		
		    		sql = r.getSQLUpdate("tblpptpermohonan");
		    		stmt.executeUpdate(sql);
		
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		
	}
	

	@SuppressWarnings("unchecked")
	public static void updateSuburusanHakmilik_urusanBayaran(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String id_fail = (String)data.get("id_fail");
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		//update n add tblrujsuburusanstatus
			    SQLRenderer r = new SQLRenderer();
				r.update("ID_SUBURUSANSTATUSHAKMILIK", currentSuburusanstatushakmilik);	
				r.add("AKTIF",0);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);	 
				  
				r.clear();
				
	     		r.add("ID_SUBURUSANSTATUSHAKMILIK",DB.getNextID("TBLRUJSUBURUSANHAKMILIK_SEQ"));
	     		r.add("ID_PERMOHONAN",id_permohonan);
	     		r.add("ID_HAKMILIK",id_hakmilik);
	     		r.add("ID_FAIL",id_fail);
	     		r.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
	     		r.add("AKTIF",1);
				r.add("ID_MASUK",id_user);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLInsert("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close addSuburusanHakmilik_deposit





		public static void updateSuburusanStatusFailPPT_urusanBayaran(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_permohonan = (String)data.get("id_permohonan");
		    		String id_fail = (String)data.get("id_fail");
		    		
		    		//update n add tblrujsuburusanstatus
				    SQLRenderer r = new SQLRenderer();
					r.update("ID_SUBURUSANSTATUSFAILPPT", currentSuburusanstatusfailppt);	
					r.add("AKTIF",0);
					r.add("ID_KEMASKINI",id_user);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLUpdate("tblrujsuburusanstatusfailppt");
					stmt.executeUpdate(sql);	 
					  
					r.clear();
					
					r.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
					r.add("ID_PERMOHONAN",id_permohonan);
					r.add("ID_FAIL",id_fail);
					r.add("ID_SUBURUSANSTATUS",newSuburusanStatus);
					r.add("AKTIF",1);
					r.add("ID_MASUK",id_user);
					r.add("ID_KEMASKINI",id_user);
					r.add("TARIKH_MASUK",r.unquote("sysdate"));
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLInsert("tblrujsuburusanstatusfailppt");
					stmt.executeUpdate(sql);
		    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close updateSuburusanStatusFailPPT		

		public static void urusanBayaranAgensi_tblrujsuburusanstatusbantahan(String usid,String id_bantahan, String id_permohonan,
				String id_hakmilik, String id_fail) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    		    			
				db = new Db();			
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL				  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_bantahan", id_bantahan);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("tblrujsuburusanstatusbantahan");
				  myLogger.info("UPDATE TBLRUJSUBURUSANSTATUSBANTAHAN --> "+sql2);
				  stmt2.executeUpdate(sql2);
				  
				//** INSERT TBLRUJSUBURUSANSTATUSBANTAHAN
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_permohonan",id_permohonan);
					r.add("id_hakmilik",id_hakmilik);
					r.add("id_bantahan",id_bantahan);				
					r.add("id_suburusanstatus",16102118);	// ID_SUBURUSANSTATUS URUSAN BAYARAN = 204
					r.add("aktif",1);
					r.add("id_fail",id_fail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));					

					sql = r.getSQLInsert("Tblrujsuburusanstatusbantahan");	
					myLogger.info("INSERT TBLRUJSUBURUSANSTATUSBANTAHAN --> "+sql);
					stmt.executeUpdate(sql);			  				  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		
		public static void updateStatus_urusanBayaranAgensi(String idpermohonan,String idUser,String idstatus) throws Exception
		{
				
			    Db db = null;
			    String sql = "";
			    
			    try{
			      
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			    		
			    		SQLRenderer r = new SQLRenderer();
			    		r.update("id_permohonan",idpermohonan);
			    		r.add("id_status",idstatus);      			
			    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
			    		r.add("id_kemaskini",idUser);    		
			    		sql = r.getSQLUpdate("tblpptpermohonan");
			    		stmt.executeUpdate(sql);
			
			    }//close try 
			    finally {
			      if (db != null) db.close();
			    }//close finally
			
		}
		

		@SuppressWarnings("unchecked")
		public static void updateSuburusanHakmilik_urusanBayaranAgensi(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_permohonan = (String)data.get("id_permohonan");
		    		String id_fail = (String)data.get("id_fail");
		    		String id_hakmilik = (String)data.get("id_hakmilik");
		    		
		    		//update n add tblrujsuburusanstatus
				    SQLRenderer r = new SQLRenderer();
					r.update("ID_SUBURUSANSTATUSHAKMILIK", currentSuburusanstatushakmilik);	
					r.add("AKTIF",0);
					r.add("ID_KEMASKINI",id_user);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLUpdate("tblrujsuburusanstatushakmilik");
					stmt.executeUpdate(sql);	 
					  
					r.clear();
					
		     		r.add("ID_SUBURUSANSTATUSHAKMILIK",DB.getNextID("TBLRUJSUBURUSANHAKMILIK_SEQ"));
		     		r.add("ID_PERMOHONAN",id_permohonan);
		     		r.add("ID_HAKMILIK",id_hakmilik);
		     		r.add("ID_FAIL",id_fail);
		     		r.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
		     		r.add("AKTIF",1);
					r.add("ID_MASUK",id_user);
					r.add("ID_KEMASKINI",id_user);
					r.add("TARIKH_MASUK",r.unquote("sysdate"));
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLInsert("tblrujsuburusanstatushakmilik");
					stmt.executeUpdate(sql);
		    		
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close addSuburusanHakmilik_urusanBayaranAgensi





			public static void updateSuburusanStatusFailPPT_urusanBayaranAgensi(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
			  {
				
			    Db db = null;
			    String sql = "";
			    
			    try{
			      
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			    		
			    		String id_user = (String)data.get("id_user");
			    		String id_permohonan = (String)data.get("id_permohonan");
			    		String id_fail = (String)data.get("id_fail");
			    		
			    		//update n add tblrujsuburusanstatus
					    SQLRenderer r = new SQLRenderer();
						r.update("ID_SUBURUSANSTATUSFAILPPT", currentSuburusanstatusfailppt);	
						r.add("AKTIF",0);
						r.add("ID_KEMASKINI",id_user);
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						sql = r.getSQLUpdate("tblrujsuburusanstatusfailppt");
						stmt.executeUpdate(sql);	 
						  
						r.clear();
						
						r.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
						r.add("ID_PERMOHONAN",id_permohonan);
						r.add("ID_FAIL",id_fail);
						r.add("ID_SUBURUSANSTATUS",newSuburusanStatus);
						r.add("AKTIF",1);
						r.add("ID_MASUK",id_user);
						r.add("ID_KEMASKINI",id_user);
						r.add("TARIKH_MASUK",r.unquote("sysdate"));
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						sql = r.getSQLInsert("tblrujsuburusanstatusfailppt");
						stmt.executeUpdate(sql);
			    	
			    }//close try 
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
			  }//close updateSuburusanStatusFailPPT_urusanBayaranAgensi					


			public static void updateStatus_selesaiBantahan(String idpermohonan,String idUser,String idstatus) throws Exception
			{
					
				    Db db = null;
				    String sql = "";
				    
				    try{
				      
				    		db = new Db();
				    		Statement stmt = db.getStatement();
				    		
				    		SQLRenderer r = new SQLRenderer();
				    		r.update("id_permohonan",idpermohonan);
				    		r.add("id_status",idstatus);      			
				    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
				    		r.add("id_kemaskini",idUser);    		
				    		sql = r.getSQLUpdate("tblpptpermohonan");
				    		stmt.executeUpdate(sql);
				
				    }//close try 
				    finally {
				      if (db != null) db.close();
				    }//close finally
				
			}
			

			@SuppressWarnings("unchecked")
			public static void updateSuburusanHakmilik_selesaiBantahan(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
			  {
				
			    Db db = null;
			    String sql = "";
			    
			    try{
			      
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			    		
			    		String id_user = (String)data.get("id_user");
			    		String id_permohonan = (String)data.get("id_permohonan");
			    		String id_fail = (String)data.get("id_fail");
			    		String id_hakmilik = (String)data.get("id_hakmilik");
			    		
			    		//update n add tblrujsuburusanstatus
					    SQLRenderer r = new SQLRenderer();
						r.update("ID_SUBURUSANSTATUSHAKMILIK", currentSuburusanstatushakmilik);	
						r.add("AKTIF",0);
						r.add("ID_KEMASKINI",id_user);
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						sql = r.getSQLUpdate("tblrujsuburusanstatushakmilik");
						stmt.executeUpdate(sql);	 
						  
						r.clear();
						
			     		r.add("ID_SUBURUSANSTATUSHAKMILIK",DB.getNextID("TBLRUJSUBURUSANHAKMILIK_SEQ"));
			     		r.add("ID_PERMOHONAN",id_permohonan);
			     		r.add("ID_HAKMILIK",id_hakmilik);
			     		r.add("ID_FAIL",id_fail);
			     		r.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
			     		r.add("AKTIF",1);
						r.add("ID_MASUK",id_user);
						r.add("ID_KEMASKINI",id_user);
						r.add("TARIKH_MASUK",r.unquote("sysdate"));
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						sql = r.getSQLInsert("tblrujsuburusanstatushakmilik");
						stmt.executeUpdate(sql);
			    		
			    }//close try 
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
			  }//close addSuburusanHakmilik_deposit





				public static void updateSuburusanStatusFailPPT_selesaiBantahan(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
				  {
					
				    Db db = null;
				    String sql = "";
				    
				    try{
				      
				    		db = new Db();
				    		Statement stmt = db.getStatement();
				    		
				    		String id_user = (String)data.get("id_user");
				    		String id_permohonan = (String)data.get("id_permohonan");
				    		String id_fail = (String)data.get("id_fail");
				    		
				    		//update n add tblrujsuburusanstatus
						    SQLRenderer r = new SQLRenderer();
							r.update("ID_SUBURUSANSTATUSFAILPPT", currentSuburusanstatusfailppt);	
							r.add("AKTIF",0);
							r.add("ID_KEMASKINI",id_user);
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							sql = r.getSQLUpdate("tblrujsuburusanstatusfailppt");
							stmt.executeUpdate(sql);	 
							  
							r.clear();
							
							r.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
							r.add("ID_PERMOHONAN",id_permohonan);
							r.add("ID_FAIL",id_fail);
							r.add("ID_SUBURUSANSTATUS",newSuburusanStatus);
							r.add("AKTIF",1);
							r.add("ID_MASUK",id_user);
							r.add("ID_KEMASKINI",id_user);
							r.add("TARIKH_MASUK",r.unquote("sysdate"));
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							sql = r.getSQLInsert("tblrujsuburusanstatusfailppt");
							stmt.executeUpdate(sql);
				    	
				    }//close try 
				    finally {
				      if (db != null) db.close();
				    }//close finally
				   
				  }//close updateSuburusanStatusFailPPT		
				
				
								
				public static void updateStatus_selesaiBantahanAgensi(String idpermohonan,String idUser,String idstatus) throws Exception
				{
						
					    Db db = null;
					    String sql = "";
					    
					    try{
					      
					    		db = new Db();
					    		Statement stmt = db.getStatement();
					    		
					    		SQLRenderer r = new SQLRenderer();
					    		r.update("id_permohonan",idpermohonan);
					    		r.add("id_status",idstatus);      			
					    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
					    		r.add("id_kemaskini",idUser);    		
					    		sql = r.getSQLUpdate("tblpptpermohonan");
					    		stmt.executeUpdate(sql);
					
					    }//close try 
					    finally {
					      if (db != null) db.close();
					    }//close finally
					
				}


				@SuppressWarnings("unchecked")
				public static void updateSuburusanHakmilik_selesaiBantahanAgensi(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
				  {
					
				    Db db = null;
				    String sql = "";
				    
				    try{
				      
				    		db = new Db();
				    		Statement stmt = db.getStatement();
				    		
				    		String id_user = (String)data.get("id_user");
				    		String id_permohonan = (String)data.get("id_permohonan");
				    		String id_fail = (String)data.get("id_fail");
				    		String id_hakmilik = (String)data.get("id_hakmilik");
				    		
				    		//update n add tblrujsuburusanstatus
						    SQLRenderer r = new SQLRenderer();
							r.update("ID_SUBURUSANSTATUSHAKMILIK", currentSuburusanstatushakmilik);	
							r.add("AKTIF",0);
							r.add("ID_KEMASKINI",id_user);
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							sql = r.getSQLUpdate("tblrujsuburusanstatushakmilik");
							stmt.executeUpdate(sql);	 
							  
							r.clear();
							
				     		r.add("ID_SUBURUSANSTATUSHAKMILIK",DB.getNextID("TBLRUJSUBURUSANHAKMILIK_SEQ"));
				     		r.add("ID_PERMOHONAN",id_permohonan);
				     		r.add("ID_HAKMILIK",id_hakmilik);
				     		r.add("ID_FAIL",id_fail);
				     		r.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
				     		r.add("AKTIF",1);
							r.add("ID_MASUK",id_user);
							r.add("ID_KEMASKINI",id_user);
							r.add("TARIKH_MASUK",r.unquote("sysdate"));
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							sql = r.getSQLInsert("tblrujsuburusanstatushakmilik");
							stmt.executeUpdate(sql);
				    		
				    }//close try 
				    finally {
				      if (db != null) db.close();
				    }//close finally
				   
				  }//close addSuburusanHakmilik_selesaiBantahanAgensi





					public static void updateSuburusanStatusFailPPT_selesaiBantahanAgensi(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
					  {
						
					    Db db = null;
					    String sql = "";
					    
					    try{
					      
					    		db = new Db();
					    		Statement stmt = db.getStatement();
					    		
					    		String id_user = (String)data.get("id_user");
					    		String id_permohonan = (String)data.get("id_permohonan");
					    		String id_fail = (String)data.get("id_fail");
					    		
					    		//update n add tblrujsuburusanstatus
							    SQLRenderer r = new SQLRenderer();
								r.update("ID_SUBURUSANSTATUSFAILPPT", currentSuburusanstatusfailppt);	
								r.add("AKTIF",0);
								r.add("ID_KEMASKINI",id_user);
								r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
								sql = r.getSQLUpdate("tblrujsuburusanstatusfailppt");
								stmt.executeUpdate(sql);	 
								  
								r.clear();
								
								r.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
								r.add("ID_PERMOHONAN",id_permohonan);
								r.add("ID_FAIL",id_fail);
								r.add("ID_SUBURUSANSTATUS",newSuburusanStatus);
								r.add("AKTIF",1);
								r.add("ID_MASUK",id_user);
								r.add("ID_KEMASKINI",id_user);
								r.add("TARIKH_MASUK",r.unquote("sysdate"));
								r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
								sql = r.getSQLInsert("tblrujsuburusanstatusfailppt");
								stmt.executeUpdate(sql);
					    	
					    }//close try 
					    finally {
					      if (db != null) db.close();
					    }//close finally
					   
					  }//close updateSuburusanStatusFailPPT_selesaiBantahanAgensi						
				
}
