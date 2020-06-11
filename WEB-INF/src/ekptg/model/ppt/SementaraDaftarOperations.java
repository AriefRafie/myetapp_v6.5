package ekptg.model.ppt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;

public class SementaraDaftarOperations {
	static Logger myLogger = Logger.getLogger(SementaraDaftarOperations.class);

	@SuppressWarnings("unchecked") //PPT-41 NEW
	public static String addSementara(Hashtable data) throws Exception
	  {
		
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    String output="";
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	
	    	//user login id
	    	String id_user = (String)data.get("id_user");
	    	
	    	//Table pptpermohonan
	    	String no_rujukan_ptg = (String)data.get("no_rujukan_ptg");	       
	    	String no_rujukan_ptd = (String)data.get("no_rujukan_ptd");
	    	
	    	String txdTarikhPermohonan = (String)data.get("txdTarikhPermohonan");
	    	String tarikh_kehendaki = (String)data.get("tarikh_kehendaki");
	    	String tarikh_surat = (String)data.get("tarikh_surat");	       
	    	String flag_peruntukan = (String)data.get("flag_peruntukan");
	    	String flag_segera = (String)data.get("flag_segera");
	    	String tujuan = (String)data.get("tujuan");
	    	String rujukan_surat = (String)data.get("rujukan_kementerian");	      
	    	String id_agensi = (String)data.get("agensi");  
	    	String id_daerah = (String)data.get("daerah");
	    	
	    	//PPT-41
	    	String tarikh_pendudukan_mula = (String)data.get("tarikh_pendudukan_mula");
	    	String tarikh_pendudukan_akhir = (String)data.get("tarikh_pendudukan_akhir");
	    	String tempoh_pendudukan = (String)data.get("tempoh_pendudukan");
	  
	    	String sorJenisKodDaerah = (String)data.get("sorJenisKodDaerah");
	    	
	    	//table pfdfail
	    	String id_suburusan = (String)data.get("suburusan");
	    	String id_projek_negeri = (String)data.get("projek_negeri");
	    	String id_kementerian = (String)data.get("kementerian");

	    	//convert string to date
	    	String TP = "to_date('" + txdTarikhPermohonan + "','dd/MM/yyyy')";
	    	String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
	    	String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
	    	//PPT-41
	    	String TPM = "to_date('" + tarikh_pendudukan_mula + "','dd/MM/yyyy')";
	    	String TPA = "to_date('" + tarikh_pendudukan_akhir + "','dd/MM/yyyy')";
	      
	    	//status "PERMOHONAN CAWANGAN"
	    	int status = 11;
	    	
	    	//status "PERMOHONAN KAUNTER"
	    	int flag_jenisserahan = 2;
	      
	    	//generate no permohonan "JKPTG/PPT/kod_suburusan/this_year-000001
	    	long id_permohonan = DB.getNextID("TBLPPTPERMOHONAN_SEQ");    
	    	long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
	    	Date now = new Date();
	    	SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
	    	String tahun = formatter.format(now);
	    	int thn = Integer.parseInt(tahun);

	    	//generate no permohonan	    	
	    	String seq = String.format("%06d",File.getSeqNo(1,1,0,0,0,false,false,thn,0));	    	
	    	String noPermohonan = tahun+"-"+seq;
	      
	    	SQLRenderer rF = new SQLRenderer();
	    	rF.add("id_fail",idFail);
	    	rF.add("id_suburusan", id_suburusan);
	    	rF.add("id_negeri", id_projek_negeri);
	    	rF.add("id_kementerian", id_kementerian);
	    	rF.add("tarikh_masuk",rF.unquote("sysdate"));
			rF.add("id_masuk",id_user);
	    	sql = rF.getSQLInsert("tblpfdfail");
	    	stmt.executeUpdate(sql);
	      
	    	SQLRenderer rPH = new SQLRenderer();	
	     	rPH.add("id_permohonan",id_permohonan);
	     	rPH.add("no_permohonan",noPermohonan);
	     	rPH.add("id_fail",idFail);
	     	rPH.add("flag_peruntukan", flag_peruntukan);
	     	rPH.add("tujuan", tujuan);
	     	rPH.add("no_rujukan_surat", rujukan_surat);
	     	rPH.add("tarikh_permohonan", rPH.unquote(TP));
	     	rPH.add("tarikh_kehendaki", rPH.unquote(TK));
	     	rPH.add("tarikh_surat", rPH.unquote(TS));
	     	rPH.add("id_daerah", id_daerah);
	     	rPH.add("id_status", status);
	     	rPH.add("id_agensi", id_agensi);
	     	rPH.add("flag_jenispermohonan", flag_jenisserahan);
	     	rPH.add("no_rujukan_ptg",no_rujukan_ptg);
	     	rPH.add("no_rujukan_ptd",no_rujukan_ptd);
	     	
	     	//PPT-41
	     	rPH.add("tarikh_pendudukan_mula", rPH.unquote(TPM));
	     	rPH.add("tarikh_pendudukan_akhir", rPH.unquote(TPA));
	     	rPH.add("tempoh_pendudukan", tempoh_pendudukan);
	     	
	     	rPH.add("flag_jenis_kod_daerah",sorJenisKodDaerah);
	     	
	     	rPH.add("tarikh_masuk",rPH.unquote("sysdate"));
			rPH.add("id_masuk",id_user);
	     	sql = rPH.getSQLInsert("tblpptpermohonan");
	     	stmt.executeUpdate(sql);
	     	
	     	myLogger.info("SQL Sementara PPT-41:: "+sql);
	     	
	    	//id suburusanstatusfail
	     	String id_suburusanstatus = "";
	     	if(id_suburusan.equals("51")){
	     		id_suburusanstatus = "1420";
	     	}else if(id_suburusan.equals("52")){
	     		id_suburusanstatus = "1466";
	     	}else if(id_suburusan.equals("53")){
	     		id_suburusanstatus = "1512";
	     	}
	     	
	     	String sql5 = "";
	     		SQLRenderer r5 = new SQLRenderer();
	     		r5.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
	     		r5.add("ID_PERMOHONAN",id_permohonan);
	     		r5.add("ID_FAIL",idFail);
	     		r5.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
	     		r5.add("AKTIF",1);
				r5.add("ID_MASUK",id_user);
				r5.add("ID_KEMASKINI",id_user);
				r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
				r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
				sql5 = r5.getSQLInsert("tblrujsuburusanstatusfailppt");
				stmt.executeUpdate(sql5);	
	     	
	     	
	     	output = ""+id_permohonan;
	    	
	     	conn.commit();	
		      
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data ");
	    }
	    finally {
	      if (db != null) db.close();
	    }
	    
	    return output;
	   
	  }//close add

	public static void simpanHM(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";	
	    String sql2 = "";
	    try{	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		
	    		long id_hakmilik = DB.getNextID("TBLPPTHAKMILIK_SEQ"); 
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String id_negeriProjek = (String)data.get("id_negeriProjek");
	    		String id_daerahProjek = (String)data.get("id_daerahProjek");
	    		String id_mukimProjek = (String)data.get("socMukim");
	    		String txtseksyen = (String)data.get("txtseksyen");
	    		String catatan = (String)data.get("txtCatatan");
	    		String txtnolot = (String)data.get("txtNoLot");
	    		String txtnopt = (String)data.get("txtNoPT");
	    		
	    		String id_jenishakmilik = (String)data.get("jenisHakMilik");	 
	    		String no_hakmilik = (String)data.get("txtNoHakmilik");
	    		String id_lot = (String)data.get("kodLot");
	    		String id_luasasal = (String)data.get("unitLuas");
	    		String luas_ambil = (String)data.get("txtLuasAmbil");	
	    		String luas_asal = (String)data.get("txtLuasAsal");
	    		
	    		String tarikhLuput = (String)data.get("txdTarikhLuput");	 
	    		String tarikhDaftar = (String)data.get("txdTarikhDaftar");
	    		String baki = (String)data.get("txtBakiTempoh");
	    		String id_kategoriTanah = (String)data.get("socKategoriTanah");
	    		String lokasi = (String)data.get("txtLokasi");	
	    	
	    		//new
	    		String id_luasambil = (String)data.get("unitLuasAmbil");
	    		String nama_luas_asal = (String)data.get("txtLuasLotAsalSebelumConvert");
	    		String nama_luas_ambil = (String)data.get("txtLuasLotAmbilSebelumConvert");
	    		String id_unitluaslot_convert = (String)data.get("sorDropdownUnitAsal");
	    		String id_unitluasambil_convert = (String)data.get("sorDropdownUnitAmbil");
	    		
	    		//rizab
	    		String sorJenisRizab = (String)data.get("sorJenisRizab");
	    		String txtLain = (String)data.get("txtLain");
	    		String txtNoWartaRizab = (String)data.get("txtNoWartaRizab");
	    		String txdTarikhWarta = (String)data.get("txdTarikhWarta");
	    		
	    		String syaratNyata = (String)data.get("txtSyaratNyata");	 
	    		String syaratKhas = (String)data.get("txtSyaratKhas");
	    		String sekatanKepentingan = (String)data.get("txtSekatanKepentingan");
	    		String sekatanHak = (String)data.get("txtSekatanHak");
	    		String noSyit = (String)data.get("txtNoSyit");	
	    		
	    		String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";
	    		String TD = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";
	    		
	    		// MAKLUMAT SEMENTARA
	    		String txdTkhMula = (String)data.get("txdTkhMula");
	    		String txdTkhAkhir = (String)data.get("txdTkhAkhir");
	    		
	    		String txtTempoh = (String)data.get("txtTempoh");
	    		String socUnitTempoh = (String)data.get("socUnitTempoh");	    		
	    		String tarikhMula = "to_date('" + txdTkhMula + "','dd/MM/yyyy')";
	    		String tarikhAkhir = "to_date('" + txdTkhAkhir + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_hakmilik",id_hakmilik);
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("id_negeri", id_negeriProjek); 	
	    		r.add("id_jenishakmilik", id_jenishakmilik);
	    		r.add("id_daerah", id_daerahProjek);
	    		r.add("no_warta_rizab", txtNoWartaRizab); 	
	    		r.add("tarikh_warta_rizab", r.unquote(TW));
	    		r.add("flag_jenis_rizab", sorJenisRizab); 	
	    		r.add("nama_lain_rizab", txtLain);
	    		r.add("id_mukim", id_mukimProjek);
	    		r.add("id_unitluaslot", id_luasasal);
	    		r.add("id_lot", id_lot);
	    		r.add("luas_lot",luas_asal);
	    		r.add("luas_ambil", luas_ambil);
	    		r.add("no_hakmilik", no_hakmilik);
	    		r.add("no_lot", txtnolot);
	    		r.add("no_pt", txtnopt);
	    		r.add("catatan",catatan);
	    		r.add("seksyen",txtseksyen);	    		
	    		r.add("tarikh_daftar",r.unquote(TD));
	    		r.add("tarikh_luput",r.unquote(TL));
	    		r.add("tempoh_luput", baki);
	    		r.add("id_kategoritanah",id_kategoriTanah);
	    		r.add("lokasi",lokasi);	    		
	    		r.add("syarat_nyata", syaratNyata);
	    		r.add("syarat_khas", syaratKhas);
	    		r.add("sekatan_kepentingan",sekatanKepentingan);
	    		r.add("sekatan_hak",sekatanHak);
	    		r.add("no_syit",noSyit);	
	    		
	    		//new
	    		r.add("id_unitluasambil", id_luasambil);
	    		r.add("nama_luas_asal", nama_luas_asal);
	    		r.add("nama_luas_ambil", nama_luas_ambil);
	    		r.add("id_unitluaslot_convert", id_unitluaslot_convert);
	    		r.add("id_unitluasambil_convert", id_unitluasambil_convert);	
	    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblppthakmilik");
	    		stmt.executeUpdate(sql);
	    		myLogger.info("SQL simpanHM tblppthakmilik :: "+sql);
	    		
	    		// TBLPPTBORANGQ
			    Statement stmtQ = db.getStatement();
			    SQLRenderer rQ = new SQLRenderer();
			    
			    rQ.add("id_permohonan", id_permohonan);
			    rQ.add("tempoh_Pendudukan", txtTempoh);
			    rQ.add("unit_Tempoh",socUnitTempoh);
			    rQ.add("id_Hakmilik",id_hakmilik);
			    rQ.add("tarikh_Mula", rQ.unquote(tarikhMula));
			    rQ.add("tarikh_Akhir", rQ.unquote(tarikhAkhir));
			    rQ.add("luas_Sewa", luas_ambil);
			    rQ.add("id_Unitluas", id_luasambil);
			    rQ.add("id_Masuk",id_user);
			    rQ.add("tarikh_Masuk",rQ.unquote("sysdate")); 
			    sql2 = rQ.getSQLInsert("tblpptborangq");
			        		
			    myLogger.info("SQL simpanHM tblpptborangq :: "+sql2);
			    stmtQ.executeUpdate(sql2);	
	    		
	    		
	    		//remove subjaket kalau dah ada
//	    		if(flagSubjaket.equals("1")){
//	    			
//	    			r.clear();
//	    		
//	    			//update flag di tblpptpermohonan
//	    			r.update("id_permohonan", id_permohonan);		    				
//	    			r.add("flag_subjaket", "");
//	    			r.add("tarikh_kemaskini",r.unquote("sysdate"));
//	    			r.add("id_kemaskini",id_user);    		
//	    			sql = r.getSQLUpdate("Tblpptpermohonan");
//	    			stmt.executeUpdate(sql);
//	    			
//	    			r.clear();
//	    			
//	    			r.update("id_permohonan", id_permohonan);		    				
//	    			r.add("no_subjaket", "");
//	    			r.add("tarikh_kemaskini",r.unquote("sysdate"));
//	    			r.add("id_kemaskini",id_user);    		
//	    			sql = r.getSQLUpdate("Tblppthakmilik");
//	    			stmt.executeUpdate(sql);
//	    		}
	    		
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanHM

	public static void updateHM(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";	
	    String sql2 = "";
	    try{	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    	
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String id_mukimProjek = (String)data.get("socMukim");
	    		String txtseksyen = (String)data.get("txtseksyen");
	    		String catatan = (String)data.get("txtCatatan");
	    		String txtnolot = (String)data.get("txtNoLot");
	    		String txtnopt = (String)data.get("txtNoPT");
	    		
	    		String id_jenishakmilik = (String)data.get("jenisHakMilik");	 
	    		String no_hakmilik = (String)data.get("txtNoHakmilik");
	    		String id_lot = (String)data.get("kodLot");
	    		String id_luas = (String)data.get("unitLuas");
	    		String luas_ambil = (String)data.get("txtLuasAmbil");	
	    		String luas_asal = (String)data.get("txtLuasAsal");

	    		String tarikhLuput = (String)data.get("txdTarikhLuput");	 
	    		String tarikhDaftar = (String)data.get("txdTarikhDaftar");
	    		String baki = (String)data.get("txtBakiTempoh");
	    		String id_kategoriTanah = (String)data.get("socKategoriTanah");
	    		String lokasi = (String)data.get("txtLokasi");	
	    	
	    		//rizab
		    	String sorJenisRizab = (String)data.get("sorJenisRizab");
		    	String txtLain = (String)data.get("txtLain");
		    	String txtNoWartaRizab = (String)data.get("txtNoWartaRizab");
		    	String txdTarikhWarta = (String)data.get("txdTarikhWarta");
		    	 
	    		String syaratNyata = (String)data.get("txtSyaratNyata");	 
	    		String syaratKhas = (String)data.get("txtSyaratKhas");
	    		String sekatanKepentingan = (String)data.get("txtSekatanKepentingan");
	    		String sekatanHak = (String)data.get("txtSekatanHak");
	    		String noSyit = (String)data.get("txtNoSyit");	
	    		
	    		//new
	    		String id_luasambil = (String)data.get("unitLuasAmbil");
	    		String nama_luas_asal = (String)data.get("txtLuasLotAsalSebelumConvert");
	    		String nama_luas_ambil = (String)data.get("txtLuasLotAmbilSebelumConvert");
	    		String id_unitluaslot_convert = (String)data.get("sorDropdownUnitAsal");
	    		String id_unitluasambil_convert = (String)data.get("sorDropdownUnitAmbil");
	    			    		
	    		String TL = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";
	    		String TD = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";
	    		String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
	    		
	    		// MAKLUMAT SEMENTARA
	    		String txdTkhMula = (String)data.get("txdTkhMula");
	    		String txdTkhAkhir = (String)data.get("txdTkhAkhir");
	    		
	    		String txtTempoh = (String)data.get("txtTempoh");
	    		String socUnitTempoh = (String)data.get("socUnitTempoh");	    		
	    		String tarikhMula = "to_date('" + txdTkhMula + "','dd/MM/yyyy')";
	    		String tarikhAkhir = "to_date('" + txdTkhAkhir + "','dd/MM/yyyy')";
	    		 
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_hakmilik", id_hakmilik);	
	    		r.add("id_jenishakmilik", id_jenishakmilik);
	    		r.add("id_mukim", id_mukimProjek);
	    		r.add("id_unitluaslot", id_luas);
	    		r.add("id_lot", id_lot);
	    		r.add("luas_lot",luas_asal);
	    		r.add("no_warta_rizab", txtNoWartaRizab); 	
		    	r.add("tarikh_warta_rizab", r.unquote(TW));
		    	r.add("flag_jenis_rizab", sorJenisRizab); 	
		    	r.add("nama_lain_rizab", txtLain);
	    		r.add("luas_ambil", luas_ambil);
	    		r.add("no_hakmilik", no_hakmilik);
	    		r.add("no_lot", txtnolot);
	    		r.add("no_pt", txtnopt);
	    		r.add("catatan",catatan);
	    		r.add("seksyen",txtseksyen);	    		
	    		r.add("tarikh_daftar",r.unquote(TD));
	    		r.add("tarikh_luput",r.unquote(TL));
	    		r.add("tempoh_luput", baki);
	    		r.add("id_kategoritanah",id_kategoriTanah);
	    		r.add("lokasi",lokasi);	    		
	    		r.add("syarat_nyata", syaratNyata);
	    		r.add("syarat_khas", syaratKhas);
	    		r.add("sekatan_kepentingan",sekatanKepentingan);
	    		r.add("sekatan_hak",sekatanHak);
	    		r.add("no_syit",noSyit);	    
	    		
	    		//new
	    		r.add("id_unitluasambil", id_luasambil);
	    		r.add("nama_luas_asal", nama_luas_asal);
	    		r.add("nama_luas_ambil", nama_luas_ambil);
	    		r.add("id_unitluaslot_convert", id_unitluaslot_convert);
	    		r.add("id_unitluasambil_convert", id_unitluasambil_convert);		    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);   
	    			    		
	    		sql = r.getSQLUpdate("tblppthakmilik");
	    		stmt.executeUpdate(sql);	
	    		myLogger.info("SQL UPDATE TBLPPTHAKMILIK :: "+sql);
	    		

			    r.clear();
			    
			    //TBLPPTBORANGQ
			    r.update("id_hakmilik",id_hakmilik);
			    
			    r.add("tempoh_pendudukan", txtTempoh);
			    r.add("unit_tempoh",socUnitTempoh);			    
			    r.add("tarikh_mula", r.unquote(tarikhMula));
			    r.add("tarikh_akhir", r.unquote(tarikhAkhir));
			    r.add("luas_Sewa", luas_ambil);
			    r.add("id_unitluas", id_luasambil);
			    r.add("id_kemaskini",id_user);
			    r.add("tarikh_kemaskini",r.unquote("sysdate")); 
			    
			    sql = r.getSQLUpdate("tblpptborangq");
			    myLogger.info("UPDATE ::-> "+sql);	
			    stmt.executeUpdate(sql);		    		
	    		
	    		
	    		// TBLPPTBORANGQ
//			    Statement stmtQ = db.getStatement();
//			    SQLRenderer rQ = new SQLRenderer();
//			    
//			    rQ.update("id_permohonan", id_permohonan);
//			    rQ.update("id_Hakmilik",id_hakmilik);
//			    
//			    rQ.add("tempoh_Pendudukan", txtTempoh);
//			    rQ.add("unit_Tempoh",socUnitTempoh);			    
//			    rQ.add("tarikh_Mula", rQ.unquote(tarikhMula));
//			    rQ.add("tarikh_Akhir", rQ.unquote(tarikhAkhir));
//			    rQ.add("luas_Sewa", luas_ambil);
//			    rQ.add("id_Unitluas", id_luasambil);
//			    rQ.add("id_kemaskini",id_user);
//			    rQ.add("tarikh_kemaskini",rQ.unquote("sysdate")); 
			    
//			    myLogger.info("SQL UPDATE TBLPPTBORANGQ :: "+sql2);			    	
//			    sql2 = rQ.getSQLUpdate("tblpptborangq");
//			    stmt.executeUpdate(sql2);	
	    		
	    		
	    		
	    		
  	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateHM
	
	
	@SuppressWarnings("unchecked") //PPT-41 UPDATTE
	public static void updateSementara(Hashtable data) throws Exception {
		
	    Db db = null;
	    String sql = "";
	   
	    try{
	      
	    	 	db = new Db();
	    	 	Statement stmt = db.getStatement();
	    	 	
	    	 	String id_user = (String)data.get("id_user");
	    	 	
	    	 	String no_rujukan_ptg = (String)data.get("no_rujukan_ptg");
	    	 	String no_rujukan_ptd = (String)data.get("no_rujukan_ptd");
	    	 	
	    		String id_permohonan = (String)data.get("id_permohonan"); 
	    		String id_fail = (String)data.get("id_fail"); 
	    		String id_check = (String)data.get("id_senaraisemak"); 
	      
	    		String sorJenisKodDaerah = (String)data.get("sorJenisKodDaerah"); 
	    		
	    		String id_urusan = (String)data.get("id_urusan"); 
	    		int idUrusan = 0;
	    		if(id_urusan!=""){
	    			idUrusan = Integer.parseInt(id_urusan);
	    		}
	      
	    		//checkbox seksyen 8
//	    		String semak_1 = (String)data.get("semak1"); 
//	    		String semak_2 = (String)data.get("semak2"); 
//	    		String semak_3 = (String)data.get("semak3"); 
//	    		String semak_4 = (String)data.get("semak4"); 
//	    		String semak_5 = (String)data.get("semak5"); 
//	    		String semak_6 = (String)data.get("semak6"); 
//	    		String semak_7 = (String)data.get("semak7"); 
	      
	    		//checkbox seksyen 4
//	    		String semak_10 = (String)data.get("semak10"); 
//	    		String semak_20 = (String)data.get("semak20");
	     
	    		String id_daerah = (String)data.get("daerah");
	    		String id_projekNegeri = (String)data.get("projeknegeri");
	    		String id_agensi = (String)data.get("agensi");
	    		String id_kementerian = (String)data.get("kementerian");
		  
	    		String txdTarikhPermohonan = (String)data.get("txdTarikhPermohonan");
	    		String tujuan = (String)data.get("tujuan");
	    		String rujukan_kementerian = (String)data.get("rujukan_kementerian");
	    		String tarikh_hendak = (String)data.get("tarikh_hendak");
	    		String tarikh_surat = (String)data.get("tarikh_surat");
	    		String flagPeruntukan = (String)data.get("flag_peruntukan");
//	    		String flagSegera = (String)data.get("flag_segera");
	    		
		    	//PPT-41 
		    	String tarikh_pendudukan_mula = (String)data.get("tarikh_pendudukan_mula");
		    	String tarikh_pendudukan_akhir = (String)data.get("tarikh_pendudukan_akhir");
		    	String tempoh_pendudukan = (String)data.get("tempoh_pendudukan");
		  		  
	    		String TP = "to_date('" + txdTarikhPermohonan + "','dd/MM/yyyy')";
	    		String TH = "to_date('" + tarikh_hendak + "','dd/MM/yyyy')";
	    		String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
	    		
		    	//PPT-41
		    	String TPM = "to_date('" + tarikh_pendudukan_mula + "','dd/MM/yyyy')";
		    	String TPA = "to_date('" + tarikh_pendudukan_akhir + "','dd/MM/yyyy')";
		  
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);
	    		
	    		r.add("tarikh_kehendaki", r.unquote(TH));
	    		r.add("tarikh_surat", r.unquote(TS));
	    		r.add("tarikh_permohonan", r.unquote(TP));
	    		r.add("tujuan", tujuan);
	    		r.add("no_rujukan_surat", rujukan_kementerian);
	    		r.add("id_agensi", id_agensi);
	    		r.add("id_daerah",id_daerah);
	    		r.add("flag_peruntukan", flagPeruntukan);
//	    		r.add("flag_segera", flagSegera);
	    		r.add("no_rujukan_ptg",no_rujukan_ptg);
	    		
		     	//PPT-41
		     	r.add("tarikh_pendudukan_mula", r.unquote(TPM));
		     	r.add("tarikh_pendudukan_akhir", r.unquote(TPA));
		     	r.add("tempoh_pendudukan", tempoh_pendudukan);
	    		
	    		r.add("flag_jenis_kod_daerah",sorJenisKodDaerah);
	    		
	    		r.add("no_rujukan_ptd",no_rujukan_ptd);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	   	      	r.add("id_kemaskini",id_user);
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		myLogger.info("SQL UPDATE PERMOHONAN :: "+sql);
	    		stmt.executeUpdate(sql);

	    		//projek negeri
	    		SQLRenderer rF = new SQLRenderer();
	    		rF.update("id_fail",id_fail);
	    		
	    		rF.add("id_negeri", id_projekNegeri);
	    		rF.add("id_kementerian", id_kementerian);
	    		rF.add("tarikh_kemaskini",rF.unquote("sysdate"));
	   	      	rF.add("id_kemaskini",id_user);
	    		sql = rF.getSQLUpdate("tblpfdfail");
	    		stmt.executeUpdate(sql);
	      
	    		//checkbutton seksyen 8
//	    		if(idUrusan==52)
//	    		{
//	    			SQLRenderer rCK = new SQLRenderer();
//	    			rCK.update("i.id_senaraisemak",id_check);
//	    			rCK.add("i.id_permohonan", id_permohonan);
//	    			rCK.add("i.semak1", semak_1);
//	    			rCK.add("i.semak2", semak_2);
//	    			rCK.add("i.semak3", semak_3);
//	    			rCK.add("i.semak4", semak_4);
//	    			rCK.add("i.semak5", semak_5);
//	    			rCK.add("i.semak6", semak_6);
//	    			rCK.add("i.semak7", semak_7);
//	    			rCK.add("tarikh_kemaskini",rCK.unquote("sysdate"));
//	    		    rCK.add("id_kemaskini",id_user);
//	    			sql = rCK.getSQLUpdate("tblpptsenaraisemak i");
//	    			stmt.executeUpdate(sql);
//	    		}
	      
	    		//checkbutton seksyen 4
//	    		if(idUrusan==51)
//	    		{
//	    			SQLRenderer rCK4 = new SQLRenderer();
//	    			rCK4.update("j.id_senaraisemak",id_check);
//	    			rCK4.add("j.id_permohonan", id_permohonan);
//	    			rCK4.add("j.semak1", semak_10);
//	    			rCK4.add("j.semak2", semak_20);
//	    			rCK4.add("tarikh_kemaskini",rCK4.unquote("sysdate"));
//	    		    rCK4.add("id_kemaskini",id_user);
//	    			sql = rCK4.getSQLUpdate("tblpptsenaraisemak j");
//	    			stmt.executeUpdate(sql);
//	    		}
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close update	
	

}
