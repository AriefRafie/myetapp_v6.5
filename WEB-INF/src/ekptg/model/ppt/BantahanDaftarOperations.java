package ekptg.model.ppt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public class BantahanDaftarOperations {
	static Logger myLogger = Logger.getLogger(BantahanDaftarOperations.class);
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	
	public void daftarBantahan (HttpSession session,String txtNoBantahan, String txdTkhMasuk,
		String txdTkhTerimaBrgN, String txdBrgN, String txtNoLot,
		String txtNoPt, String txdTkhBrgH, String txdTkhAward,
		String txtNoHakmilik, String txtNama, String txtAlamat1,
		String txtAlamat2, String txtAlamat3, String txtPoskod,
		String txtKptgnAtasTnh, String txtAlasanBantahan,
		String socPihakPembantah,String usid,
		String idKementerian,String idAgensi,String id_hakmilik,
		String id_pihakberkepentingan,String jenis_pembantah,String flag_syarat,
		String ukuran_luas,String amaun_pampasan,String terima_pampasan,
		String umpuk_pampasan,String id_hakmilikpb,String txtAmaunTuntutan,String id_permohonan,String txtMaklumatBantahanTamat) throws Exception {
		
	 	Db db = null;	 	
	 	Connection conn = null;
	    String sql = "";
	    Date now = new Date();
	    
    	int id_stat = 80;		// ID_STATUS = 80 [BANTAHAN TAWARAN]
    	String tahun = sdf.format(now);	    
	    String no_bantahan = tahun+"-"+String.format("%06d",getSeqNoBantahan(id_stat));	
	    
	    try{				    	  
	    	  long id_bantahan = DB.getNextID("TBLPPTBANTAHAN_SEQ");
	    	  String TT = "to_date('" + txdTkhTerimaBrgN + "','dd/MM/yyyy')";
	    	  String TBN = "to_date('" + txdBrgN + "','dd/MM/yyyy')";	
	    	  String TTW = "to_date('" + txdTkhAward + "','dd/MM/yyyy')";
		      db = new Db();
		      //SET AUTOCOMMIT TO FALSE
	          conn = db.getConnection();
	          conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
	    	
		      //TBLPPTBANTAHAN
		      r = new SQLRenderer();
		      r.add("id_bantahan",id_bantahan);
		      r.add("no_bantahan",no_bantahan);		      
		      r.add("jenis_pembantah",1);				// JENIS PEMBANTAH : 1 [PIHAK BERKEPENTINGAN] & 2 [AGENSI PEMOHON]
		      r.add("tarikh_terima",r.unquote(TT) );		      
		      r.add("tarikh_borangn",r.unquote(TBN) );
		      r.add("id_kementerian",idKementerian);
		      r.add("id_agensi",idAgensi);
		      r.add("id_pihakberkepentingan",id_pihakberkepentingan);
		      r.add("alasan",txtAlasanBantahan);
		      r.add("kepentingankeatas",txtKptgnAtasTnh);
		      r.add("flag_syarat",flag_syarat);	
		      r.add("id_hakmilikpb",id_hakmilikpb);
		      r.add("tarikh_terima_award",r.unquote(TTW) );
		      r.add("flag_penerima_pampasan",ukuran_luas);
		      r.add("flag_bahagian_pampasan",amaun_pampasan);
		      r.add("flag_ukur_luas",terima_pampasan);
		      r.add("flag_pampasan",umpuk_pampasan);
		      r.add("status_bantahan",181);				// 181 = [DALAM PROSES]
		      r.add("amaun_tuntutan",txtAmaunTuntutan);
		      r.add("id_masuk",usid);
		      r.add("tarikh_masuk",r.unquote("sysdate"));
		      
		      //24022012
		      r.add("maklumat_bantahan_tamat_tempoh",txtMaklumatBantahanTamat);
		      
		      sql = r.getSQLInsert("Tblpptbantahan");
		      myLogger.info("INSERT :sql="+sql);
		      stmt.executeUpdate(sql);
		      
		      r.clear();
		      
		      //TBLPPTHAKMILIKPB
		      r.update("id_hakmilikpb", id_hakmilikpb);
		      
			  r.add("flag_bantahan",1); 						// 1 = YA
			  r.add("id_kemaskini",usid);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));
			  sql = r.getSQLUpdate("Tblppthakmilikpb");
			  myLogger.info("UPDATE ::sql= "+sql);
			  stmt.executeUpdate(sql);	
			  
		      r.clear();
		      
		      //TBLRUJSUBURUSANSTATUSBANTAHAN		      
			  r.add("id_permohonan",id_permohonan); 
			  r.add("id_hakmilik",id_hakmilik);
			  r.add("id_bantahan",id_bantahan);
			  r.add("id_suburusanstatus",1545);			// ID_SUBURUSANSTATUS DALAM PROSES = 181
			  r.add("aktif",1);
			  r.add("id_masuk",usid);
			  r.add("tarikh_masuk",r.unquote("sysdate"));
			  r.add("id_kemaskini",usid);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));			  
		      sql = r.getSQLInsert("Tblrujsuburusanstatusbantahan");
		      myLogger.info("INSERT TBLRUJSUBURUSANSTATUSBANTAHAN ::sql="+sql);
		      stmt.executeUpdate(sql);
			  
		      conn.commit();	
		      
		      
		        Db db1 = null;				
				db1 = new Db();
				Statement stmt1 = db.getStatement();
				String NO_BANTAHAN_temp = "";
				String AMAUN_TUNTUTAN_temp = "";			
				String sql1 = " SELECT NO_BANTAHAN,TRIM(TO_CHAR(AMAUN_TUNTUTAN,'999,999,990.99')) AS AMAUN_TUNTUTAN FROM TBLPPTBANTAHAN" +
						"" +
						" WHERE ID_BANTAHAN = '"+id_bantahan+"'";			
				ResultSet rs = stmt1.executeQuery(sql1);	
				myLogger.info("SQL  :"+sql1);
				while (rs.next()){				
					NO_BANTAHAN_temp = rs.getString("NO_BANTAHAN");	
					AMAUN_TUNTUTAN_temp = rs.getString("AMAUN_TUNTUTAN");
			    }			  		     	
//			    AuditTrail at = new AuditTrail();
			    AuditTrail.logActivity("","1",null,session,"INS","BANTAHAN PIHAK BERKEPENTINGAN [BIL. BANTAHAN : "+NO_BANTAHAN_temp+", AMAUN TUNTUTAN : RM "+AMAUN_TUNTUTAN_temp+"] INSERT");
				
				
	    } catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
	    } finally {
	      if (db != null) db.close();
	    }	
	    
	}	

	 public boolean isNoBantahanExist(String no_bantahan) {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 r.add("no_bantahan");
			 r.add("no_bantahan",no_bantahan);
			 sql = r.getSQLSelect("tblpptbantahan");
			 ResultSet rs = stmt.executeQuery(sql);
			 if (rs.next()) {
				 return true;
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 if (db != null) db.close();
		 }
		 return false;
	 }

	public void updateStatusDalamProses(String id_permohonan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", id_permohonan);		    
		    r.add("id_status", 181);	// 181=DALAM PROSES
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

	public void add_deposit(String usid,String id_bantahan,String txdTkhTerimaResit, String txdTkhResit,
			String txtNoResit, String txtAmaunResit, String socCaraBayar,
			String txtNoCek, String txtNoAkaun, String txtNamaBank,
			String txdTkhPulang, String socStatusPulang, String txdTkhHantar,
			String txtNamaPenghantar, String txtCatatanPlgDeposit )throws Exception {
	
	 	Db db = null;	 	
	 	Connection conn = null;
	    String sql = "";
	    Date now = new Date();
	    try
	    {		
	    	  if (isNoResitExist(txtNoResit)) {
	    		  throw new Exception("Rekod No. Resit "+ txtNoResit +" Telah Wujud.");
	    	  }			    	  
	    	  long id_borango = DB.getNextID("TBLPPTBORANGO_SEQ");
	    	  String TTR = "to_date('" + txdTkhTerimaResit + "','dd/MM/yyyy')";
	    	  String TR = "to_date('" + txdTkhResit + "','dd/MM/yyyy')";
	    	  String TP = "to_date('" + txdTkhPulang + "','dd/MM/yyyy')";
	    	  String TH = "to_date('" + txdTkhHantar + "','dd/MM/yyyy')";
		      db = new Db();
		      //SET AUTOCOMMIT TO FALSE
	          conn = db.getConnection();
	          conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
	    	
		      //TBLPPTBANTAHAN
		      r = new SQLRenderer();
		      r.update("id_bantahan",id_bantahan);
		      
		      r.add("tarikh_terima_resit",r.unquote(TTR) );		      
		      r.add("tarikh_resit",r.unquote(TR) );
		      r.add("no_resit",txtNoResit);
		      r.add("amaun_deposit",txtAmaunResit);
		      r.add("cara_bayar",socCaraBayar);
		      r.add("no_rujukan_bayaran",txtNoCek);
		      r.add("flag_terimadeposit",socStatusPulang);
		      r.add("tarikh_surat_bayardeposit",r.unquote(TP));
		      r.add("tarikh_akhir_bayardeposit",r.unquote(TH));
		      r.add("status_bantahan", 183);					// 183=URUSAN DEPOSIT BANTAHAN
		      r.add("id_kemaskini",usid);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLUpdate("Tblpptbantahan");
		      myLogger.info("UPDATE ::"+sql);
		      stmt.executeUpdate(sql);		      
		      
		      r.clear();
		      //TBLPPTBORANGO
		      r.add("id_borango", id_borango);
		      r.add("id_bantahan",id_bantahan);
//			  r.add("id_bank",socBank);
		      r.add("nama_bank",txtNamaBank);
			  r.add("no_akaun",txtNoAkaun);	
			  r.add("nama_penghantar",txtNamaPenghantar);
//			  r.add("flag_pulang_deposit",socStatusPulang);
			  r.add("catatan_pemulangan_deposit",txtCatatanPlgDeposit);
			  r.add("id_masuk",usid);
			  r.add("tarikh_masuk",r.unquote("sysdate"));
			  sql = r.getSQLInsert("Tblpptborango");
			  myLogger.info("INSERT ::-> "+sql);	
			  stmt.executeUpdate(sql);		      		      
		      conn.commit();		      		
	    } catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Deposit:"+se.getMessage());
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	 public boolean isNoResitExist(String no_resit) {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 r.add("no_resit");
			 r.add("no_resit",no_resit);
			 sql = r.getSQLSelect("Tblpptbantahan");
			 ResultSet rs = stmt.executeQuery(sql);
			 if (rs.next()) {
				 return true;
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 if (db != null) db.close();
		 }
		 return false;
	 }

	public void updateStatusUrusanDeposit(String id_permohonan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", id_permohonan);		    
		    r.add("id_status", 183);			// 183=URUSAN DEPOSIT
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

	public void daftarBorangO(String usid,String idBorangO,String id_bantahan,String txdTkhBrgO,
			String idPejabatMahkamah,String txdTkhHantarBorangO,String txtNamaPenghantarBorangO,String txtNamaPenerimaBorangO) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {	
	    	String TBO = "to_date('" + txdTkhBrgO + "','dd/MM/yyyy')";
	    	String THBO = "to_date('" + txdTkhHantarBorangO + "','dd/MM/yyyy')";
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		    myLogger.info("idBorangO = "+idBorangO);
		    //TBLPPTBORANGO
		    r.update("id_borango", idBorangO);		    
		    r.add("tarikh_borango", r.unquote(TBO));
		    r.add("id_mahkamah",idPejabatMahkamah);
		    r.add("nama_penghantar_borango",txtNamaPenghantarBorangO);
		    r.add("nama_penerima_borango",txtNamaPenerimaBorangO);
		    r.add("tarikh_hantar_borango", r.unquote(THBO));
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptborango");	
		    myLogger.info("daftarBorangO = "+sql);
		    stmt.executeUpdate(sql);	
		    
		    r.clear();
		    
		    //TBLPPTBANTAHAN
		    r.update("id_bantahan", id_bantahan);	
		    r.add("status_bantahan",184);		// 184=URUSAN MAHKAMAH
		    r.add("id_kemaskini",usid);
		    r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptbantahan");
		    myLogger.info("UPDATE ::-> "+sql);	
		    stmt.executeUpdate(sql);		      		      
 
    	}
    	finally {
    		if (db != null) db.close();
    	}
	}

	public void updateBantahan(String id_bantahan,String txtNoBantahan, String txdTkhMasuk,
			String txdTkhTerimaBrgN, String txdBrgN, String txtNoLot,
			String txtNoPt, String txdTkhBrgH, String txdTkhAward,
			String txtNoHakmilik, String txtNama, String txtAlamat1,
			String txtAlamat2, String txtAlamat3, String txtPoskod,
			String txtKptgnAtasTnh, String txtAlasanBantahan,
			String sbcBantahan, String socPihakPembantah, String usid,
			String idKementerian, String idAgensi, String id_hakmilik,
			String id_pihakberkepentingan, String jenis_pembantah,
			String flag_syarat, String ukuran_luas, String amaun_pampasan, 
			String terima_pampasan, String umpuk_pampasan, String txtAmaunTuntutan,String txtMaklumatBantahanTamat) throws Exception {	
		
//		myLogger.info("umpuk_pampasan :"+umpuk_pampasan+";terima_pampasan :"+terima_pampasan+";amaun_pampasan :"+amaun_pampasan+";ukuran_luas:"+ukuran_luas);
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	    	String TT = "to_date('" + txdTkhTerimaBrgN + "','dd/MM/yyyy')";
	    	String TBN = "to_date('" + txdBrgN + "','dd/MM/yyyy')";	
	    	String TTW = "to_date('" + txdTkhAward + "','dd/MM/yyyy')";
	    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bantahan", id_bantahan);		    
		    r.add("no_bantahan",txtNoBantahan);
		    r.add("jenis_pembantah",1);							// JENIS PEMBANTAH : 1 [PIHAK BERKEPENTINGAN] & 2 [AGENSI PEMOHON]
		    r.add("tarikh_terima",r.unquote(TT) );		      
		    r.add("tarikh_borangn",r.unquote(TBN) );
		    r.add("id_kementerian",idKementerian);
		    r.add("id_agensi",idAgensi);
		    r.add("id_pihakberkepentingan",id_pihakberkepentingan);
		    r.add("alasan",txtAlasanBantahan);
		    r.add("kepentingankeatas",txtKptgnAtasTnh);
		    r.add("flag_syarat",flag_syarat);	
		    r.add("flag_penerima_pampasan",ukuran_luas);
		    r.add("flag_bahagian_pampasan",amaun_pampasan);
		    r.add("flag_ukur_luas",terima_pampasan);
		    r.add("flag_pampasan",umpuk_pampasan);	
		    r.add("amaun_tuntutan",txtAmaunTuntutan);
		    r.add("tarikh_terima_award",r.unquote(TTW) );
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
			
			//24022012
			r.add("maklumat_bantahan_tamat_tempoh",txtMaklumatBantahanTamat);
			
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("EDIT TBLPPTBANTAHAN = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}		
	}
	public void simpanLanjutan(String usid,String idBorangO,String txdTarikhLanjutanOB, String txdTarikhLanjutanPT) throws Exception {
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	    	
	    	String TROB = "to_date('" + txdTarikhLanjutanOB + "','dd/MM/yyyy')";
	    	String TRPT = "to_date('" + txdTarikhLanjutanPT + "','dd/MM/yyyy')";
	    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		      //TBLPPTBANTAHAN
		      r = new SQLRenderer();
		    
		      //TBLPPTBORANGO
		      r.update("id_borango", idBorangO);		    
		      r.add("tarikh_lanjutan_mahkamah_ob",r.unquote(TROB) );
		      r.add("tarikh_lanjutan_mahkamah_pt",r.unquote(TRPT) );
		      r.add("id_kemaskini",usid);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		
		      sql = r.getSQLUpdate("Tblpptborango");	
		      myLogger.info("SQL 2 :: "+sql);
		      stmt.executeUpdate(sql);
		      
		     }

	    	finally {
	    		if (db != null) db.close();
	    	}				
	}
	
	public void simpanSusulan(String usid,String idBorangO,String id_bantahan,String id_hakmilikpb,String txtNoProsiding,
			String sorKeputusanMahkamah, String sorStatusPulangDep,
			String txdTkhPerintah, String txdTkhTerimaPerintah,
			String txtAmaunPampasanBantahan, String txtKosPengapitHakim,
			String txtTempohBayaran, String unitTempohBayaran, String id_award, String txtKeteranganPampasan,
			String txtNoRujukanMahkamah,String txtKosJPPH,String txtNamaJPPH,
			String txtKosSwasta,String txtNamaSwasta,String txtNamaSyarikat) throws Exception {
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	    	String TP = "to_date('" + txdTkhPerintah + "','dd/MM/yyyy')";
	    	String TTP = "to_date('" + txdTkhTerimaPerintah + "','dd/MM/yyyy')";	
	    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		      //TBLPPTBANTAHAN
		      r = new SQLRenderer();
		      r.update("id_bantahan",id_bantahan);
		      r.add("tempoh_bayar",txtTempohBayaran);
		      r.add("unit_tempoh",unitTempohBayaran);
		      r.add("amaun_award",txtAmaunPampasanBantahan);
		      r.add("status_bantahan",184);						// 184 = URUSAN MAHKAMAH
		      r.add("id_kemaskini",usid);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLUpdate("Tblpptbantahan");
		      myLogger.info("SQL 1 :: "+sql);
		      stmt.executeUpdate(sql);	
		      
		      r.clear();
		      
		      //TBLPPTBORANGO
		      r.update("id_borango", idBorangO);		    
		      r.add("tarikh_terima_perintah",r.unquote(TTP) );		      
		      r.add("tarikh_perintah",r.unquote(TP) );
		      r.add("flag_pulang_deposit",sorStatusPulangDep);
		      r.add("keputusan_mahkamah",sorKeputusanMahkamah);
		      r.add("no_rujukan_tanah",txtNoProsiding);
		      r.add("kos_pengapit_hakim",txtKosPengapitHakim);
		      r.add("kos_jpph",txtKosJPPH);
		      r.add("nama_jpph",txtNamaJPPH);
		      r.add("kos_swasta",txtKosSwasta);
		      r.add("nama_swasta",txtNamaSwasta);
		      r.add("syarikat_swasta",txtNamaSyarikat);
		      r.add("id_kemaskini",usid);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      
		      //24022012
		      r.add("no_rujukan_mahkamah",txtNoRujukanMahkamah);
		      
		      sql = r.getSQLUpdate("Tblpptborango");	
		      myLogger.info("SQL 2 :: "+sql);
		      stmt.executeUpdate(sql);
		      
		      r.clear();
//		      //TBLPPTHAKMILIKPB
		      r.update("id_hakmilikpb", id_hakmilikpb);
			  r.add("jumlah_award_bantahan",txtAmaunPampasanBantahan);
			  r.add("id_kemaskini",usid);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));
			  sql = r.getSQLUpdate("Tblppthakmilikpb");
			  myLogger.info("SQL 3 ::-> "+sql);	
			  stmt.executeUpdate(sql);	
			  
		      r.clear();
//		      //TBLPPTTAMBAHAWARD		      			  
			  r.add("id_award",id_award);
			  r.add("nilai_award",txtAmaunPampasanBantahan);
			  r.add("keterangan",txtKeteranganPampasan);			  
			  r.add("id_masuk",usid);
			  r.add("tarikh_masuk",r.unquote("sysdate"));
			  sql = r.getSQLInsert("Tblppttambahaward");
			  myLogger.info("SQL 4 ::-> "+sql);	
			  stmt.executeUpdate(sql);			  

	    	}

	    	finally {
	    		if (db != null) db.close();
	    	}				
	}
	public void simpanPemulanganDeposit(String usid,String idBorangO,String id_bantahan,String id_hakmilikpb,String sorStatusPulangDep) throws Exception {
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	    	
	    	
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    
		     		      
		      //TBLPPTBORANGO
		      r.update("id_borango", idBorangO);		    
		      r.add("flag_pulang_deposit",sorStatusPulangDep);
		      r.add("id_kemaskini",usid);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      
		     	      
		      sql = r.getSQLUpdate("Tblpptborango");	
		      myLogger.info("SQL 2 :: "+sql);
		      stmt.executeUpdate(sql);
		      
		     	}

	    	finally {
	    		if (db != null) db.close();
	    	}				
	}

	public void updateStatusUrusanMahkamah(String id_permohonan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", id_permohonan);		    
		    r.add("id_status", 184);	// 184 = URUSAN MAHKAMAH
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptpermohonan");	
		    myLogger.info("BORANG O = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}				
	}

	public void update_deposit(String usid, String id_bantahan, String idBorangO,
			String txdTkhTerimaResit, String txdTkhResit, String txtNoResit,
			String txtAmaunResit, String socCaraBayar, String txtNoCek,
			String txtNoAkaun, String txtNamaBank, String txdTkhPulang,
			String socStatusPulang, String txdTkhHantar, String txtNamaPenghantar, 
			String txtCatatanPlgDeposit) throws Exception {

	 	Db db = null;	 	
	 	Connection conn = null;
	    String sql = "";
	    Date now = new Date();
	    try
	    {		
//	    	  if (isNoResitExist(txtNoResit)) {
//	    		  throw new Exception("Rekod No. Resit "+ txtNoResit +" Telah Wujud.");
//	    	  }			    	  
	    	 
	    	  String TTR = "to_date('" + txdTkhTerimaResit + "','dd/MM/yyyy')";
	    	  String TR = "to_date('" + txdTkhResit + "','dd/MM/yyyy')";
	    	  String TP = "to_date('" + txdTkhPulang + "','dd/MM/yyyy')";
	    	  String TH = "to_date('" + txdTkhHantar + "','dd/MM/yyyy')";
		      db = new Db();
		      //SET AUTOCOMMIT TO FALSE
	          conn = db.getConnection();
	          conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
	    	
		      //TBLPPTBANTAHAN
		      r = new SQLRenderer();
		      r.update("id_bantahan",id_bantahan);		      
		      r.add("tarikh_terima_resit",r.unquote(TTR) );		      
		      r.add("tarikh_resit",r.unquote(TR) );
		      r.add("no_resit",txtNoResit);
		      r.add("amaun_deposit",txtAmaunResit);
		      r.add("cara_bayar",socCaraBayar);
		      r.add("no_rujukan_bayaran",txtNoCek);
		      r.add("flag_terimadeposit",socStatusPulang);
		      r.add("tarikh_surat_bayardeposit",r.unquote(TP));
		      r.add("tarikh_akhir_bayardeposit",r.unquote(TH));
		      r.add("status_bantahan",183);						// 183=URUSAN DEPOSIT
		      r.add("id_kemaskini",usid);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLUpdate("Tblpptbantahan");
		      myLogger.info("UPDATE ::"+sql);
		      stmt.executeUpdate(sql);		      
		      
		      r.clear();
		      
		      //TBLPPTBORANGO
		      r.update("id_borango", idBorangO);
//			  r.add("id_bank",socBank);
			  r.add("nama_bank",txtNamaBank);
			  r.add("no_akaun",txtNoAkaun);		
			  r.add("nama_penghantar",txtNamaPenghantar);
			  r.add("catatan_pemulangan_deposit",txtCatatanPlgDeposit);
			  r.add("id_kemaskini",usid);
			  r.add("tarikh_kemaskini",r.unquote("sysdate"));
			  sql = r.getSQLUpdate("Tblpptborango");
			  myLogger.info("UPDATE ::-> "+sql);	
			  stmt.executeUpdate(sql);		      		      
		      
			  conn.commit();	
		      
	    } catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Deposit:"+se.getMessage());
	    } finally {
	      if (db != null) db.close();
	    }		
		
	}

	public void simpanTarikBalik(String usid, String id_bantahan,String txdTkhTerimaSurat, String txdTkhSurat, 
			String txtNoRujSurat) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {
	    	String TR = "to_date('" + txdTkhTerimaSurat + "','dd/MM/yyyy')";
	    	String TS = "to_date('" + txdTkhSurat + "','dd/MM/yyyy')";
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bantahan", id_bantahan);		    
		    r.add("no_rujukan_surattarikbalik", txtNoRujSurat);	
		    r.add("tarikh_terima_tarikbalik",r.unquote(TR));
		    r.add("tarikh_surat_tarikbalik",r.unquote(TS));
		    r.add("flag_tarikbalik","1");					// 1 = YA @ 2 = TIDAK
		    r.add("status_bantahan",185);					// 185 = TARIK BALIK BANTAHAN
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("TBLPPTBANTAHAN = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}						
	}
	
	public void updateStatusTarikBalik(String id_permohonan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", id_permohonan);		    
		    r.add("id_status", 185);				// 185 = TARIK BALIK
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
	
	public void simpanBatalMahkamah(String usid, String id_bantahan,String txdTkhTerimaSurat, String txdTkhSurat, 
			String txtNoRujSurat,String txtCatatanBatalMahkamah) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {
	    	String TR = "to_date('" + txdTkhTerimaSurat + "','dd/MM/yyyy')";
	    	String TS = "to_date('" + txdTkhSurat + "','dd/MM/yyyy')";
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bantahan", id_bantahan);		    
		    r.add("no_rujukan_surat_batalmahkamah", txtNoRujSurat);	
		    r.add("tarikh_terima_batalmahkamah",r.unquote(TR));
		    r.add("tarikh_surat_batalmahkamah",r.unquote(TS));
		    r.add("flag_batal_mahkamah","1");				// 1 = YA @ 2 = TIDAK
		    r.add("catatan_batal_mahkamah",txtCatatanBatalMahkamah);
		    r.add("status_bantahan",220);					// 220 = PEMBATALAN OLEH MT
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("TBLPPTBANTAHAN = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}						
	}	

	public void updateFiles(String usid, String id_dokumen, String txtnamadokumen, 
			String txtketerangandokumen)throws Exception {

	    Db db = null;
	    String sql = "";		   
		    try
		    {
		    	  db = new Db();			      
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();			      
			      r.update("ID_DOKUMEN", id_dokumen);		  
			      r.add("TAJUK", txtnamadokumen);
			      r.add("KETERANGAN", txtketerangandokumen);			     		    
			      r.add("ID_KEMASKINI", usid);			     
			      r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));     			      
			      sql = r.getSQLUpdate("tblpptdokumen");			      
			      myLogger.info("SQL UPDATE DOKUMEN :"+sql.toUpperCase());
			      stmt.executeUpdate(sql);		    	
		    }
		    finally {
			      if (db != null) db.close();			      
			}    		
	}

	public void deleteDokumen(String id_dokumen) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();					
			String sql = "DELETE FROM TBLPPTDOKUMEN WHERE ID_DOKUMEN = " + id_dokumen;			
			myLogger.info("DELETE :: "+sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	///************** SUBURUSANSTATUSFAILBANTAHAN
	
	public static void deposit_tblrujsuburusanstatusbantahan(String usid,String id_bantahan, String id_permohonan,
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
				r.add("id_suburusanstatus",1547);			// ID_SUBURUSANSTATUS URUSAN DEPOSIT = 183
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

	
	public static void urusanMahkamah_tblrujsuburusanstatusbantahan(String usid,String id_bantahan, String id_permohonan,
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
				r.add("id_suburusanstatus",1548);			// ID_SUBURUSANSTATUS URUSAN MAHKAMAH = 184
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

	
	public static void tarikBalik_tblrujsuburusanstatusbantahan(String usid,String id_bantahan, String id_permohonan,
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
				r.add("id_suburusanstatus",1549);			// ID_SUBURUSANSTATUS TARIK BALIK BANTAHAN = 185
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
	

	public static void pembatalanOlehMT_tblrujsuburusanstatusbantahan(String usid,String id_bantahan, String id_permohonan,
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
				r.add("id_suburusanstatus",16102121);			// ID_SUBURUSANSTATUS PEMBATALAN BANTAHAN OLEH MT= 220
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
		
	
	
	
	///************** CREATE SEQUENCE TEMP 
	
	 public static synchronized int getSeqNoBantahan(int id_jenisaduan) throws DbException {
			return getSeqNoAduan(id_jenisaduan);
		}	 

	 public static synchronized int getSeqNoAduan(int id_jenisaduan) throws DbException  {

			Db db = null;
			Connection conn = null;
			//File f = null;
			StringBuffer sb = new StringBuffer();
			int seqno=0;
			try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
		//	f = new File();
			boolean found = false;
			
			sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQADUAN WHERE ");
			sb.append("ID_JENISADUAN = " +id_jenisaduan);
			
			
			ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
			
			if (rs.next()) found = true;
			if (found) {
			//	f.increaseSeqAduan(id_jenisaduan); 
				increaseSeqAduan(id_jenisaduan); 
			} else {
			//	f.addNewAduan(id_jenisaduan);	
				addNewAduan(id_jenisaduan);	
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
	        if ( rs2.next() ) {
	      	  
	      		  seqno = rs2.getInt("NO_TURUTAN");
	      	  
	        }
			conn.commit();
			
			} catch (Exception ex) {
			try {
			conn.rollback(); } catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
			} 
			finally {
			if (db != null) db.close();
			}
			
			return seqno;
			}
	 
	 public static void increaseSeqAduan(int id_jenisaduan) throws DbException  {

			Db db = null;
		
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE TBLRUJSEQADUAN  SET "); 
			sb.append("no_turutan = no_turutan + 1 ");
			sb.append(" WHERE ");
			sb.append("id_jenisaduan = '"+id_jenisaduan+"'");
			
			try {
				db = new Db();
				try{
				db.getStatement().executeUpdate(sb.toString());
				} catch (SQLException x) {x.printStackTrace();}
			}catch (Exception ex) {
				throw new DbException(ex.getMessage() + ": " + sb.toString());
			}
			finally {
			if (db != null) db.close();
			}
		}
	 public static void addNewAduan(int id_jenisaduan) throws DbException {

			Db db = null;
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO TBLRUJSEQADUAN (ID_JENISADUAN,NO_TURUTAN)");
			sb.append(" VALUES (");
			sb.append("'"+id_jenisaduan+"'");
			sb.append(",1)"); //initial value
			
			try {
			db = new Db();
			db.getStatement().executeUpdate(sb.toString());
			}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
			}
			finally {
			if (db != null) db.close();
			}
		}
	 
		////***** END CREATE SEQUENCE TEMP
	 
	 
	 
	 
	 

		@SuppressWarnings("unchecked")
		public static void updateSuburusanHakmilik(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
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
		   
		  }//close addSuburusanHakmilik

		
		public static void updateSuburusanStatusFailPPT(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
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

	public static void updateStatus(String idpermohonan,String idUser,String idstatus) throws Exception
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
	public static void updateSuburusanHakmilik_deposit(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
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

	public static void updateSuburusanStatusFailPPT_deposit(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
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
	
	
	public static void updateStatus_borangO(String idpermohonan,String idUser,String idstatus) throws Exception
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

	public static void updateSuburusanStatusFailPPT_borangO(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
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


	@SuppressWarnings("unchecked")
	public static void updateSuburusanHakmilik_borangO(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
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

	@SuppressWarnings("unchecked")
	public static void updateSuburusanHakmilik_tarikbalik(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
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
	   
	  }//close addSuburusanHakmilik_tarikbalik





		public static void updateSuburusanStatusFailPPT_tarikbalik(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
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
		   
		  }//close updateSuburusanStatusFailPPT_tarikbalik		

	
		public static void updateStatus_pembatalanOlehMT(String idpermohonan,String idUser,String idstatus) throws Exception
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
		public static void updateSuburusanHakmilik_pembatalanOlehMT(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
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


			public static void updateSuburusanStatusFailPPT_pembatalanOlehMT(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
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


			public void tolakPermohonanOnline(String id_bantahan,String usid) throws Exception {
			    Db db = null;
			    String sql = "";	    
			    try
			    {			  
			    	db = new Db();
				    Statement stmt = db.getStatement();
				    SQLRenderer r = new SQLRenderer();
				    r.update("id_bantahan", id_bantahan);		    
				    //r.add("flag_online", 1);	// TUKAR MASE UPDATE KAT ONLINE NANTI
				    r.add("status_bantahan","1610250"); //Status permohonan ditolak
				    r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));
				    sql = r.getSQLUpdate("Tblpptbantahan");	
				    stmt.executeUpdate(sql);	    
			    	
			    }
			    finally {
			    	if (db != null) db.close();
			    }				
			}

			@SuppressWarnings("unchecked")
			public static void updateFlag(Hashtable data) throws Exception {
				
			    Db db = null;
			    String sql = "";
			   
			    try{
			      
			    	 	db = new Db();
			    	 	Statement stmt = db.getStatement();
			    	 	
			    	 	String id_user = (String)data.get("id_user");
			    		String id_bantahan = (String)data.get("id_bantahan"); 
			    		String flag_semakan_online = (String)data.get("flag_semakan_online"); 
			    		
			    		SQLRenderer r = new SQLRenderer();
			    		r.update("id_bantahan", id_bantahan);
			    		r.add("flag_semakan_online",flag_semakan_online);
			    		r.add("id_kemaskini",id_user);
			    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
			    		sql = r.getSQLUpdate("tblpptbantahan");	    		
			    		stmt.executeUpdate(sql);

			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }//close updateFlag
	
}
