package ekptg.view.php2;
/*
 * 	AUTHOR: NORZAILY BINTI JASMI
 *  DATE: 22.06.2010
 */
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmAPBLaporanPasirData;
import ekptg.model.php2.frmAPBLaporanPasirTransaction;

public class FrmAPBLaporanPasir extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmAPBLaporanPasir.class);
	
	// MODEL
	FrmAPBLaporanPasirData modelLaporanPasir = new FrmAPBLaporanPasirData();
	frmAPBLaporanPasirTransaction modelTrasaction = new frmAPBLaporanPasirTransaction();

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession(); 
		
    	// GET USER LOGIN DETAILS
		String usid = "";  
   		usid = (String)session.getAttribute("_ekptg_user_id");  
    	userData(usid);
    	String userIdNeg = userData(usid); 
		
		// DEFAULT
		String vm = "";   
		this.context.put("Util",new lebah.util.Util());	// UNTUK FORMAT UTIL.DECIMAL (EX: 12,000.00)
    	String doPost = (String)session.getAttribute("doPost");
    	String action = getParam("action"); 					// ACTION UTK SETUP PAGING SHJ
    	String submit = getParam("command");					// ACTION FROM JSP		
    	String actionLaporanPasir = getParam("actionLaporanPasir");
    	myLogger.info("SUBMIT :: "+submit);
    	
    	Vector list = null; 
    	Vector listLaporan = null;
    	Vector listPasir = null;
    	Vector dataPelesen = null;
    	Vector dataLaporan = null;
    	Vector listDokumen = null;
    	context.put("returnChecking", false);
		
		// SCREEN JSP
   		String SkrinListDepan = "app/php2/frmAPBLaporanPasir.jsp";
   		String SkrinListLaporan = "app/php2/frmAPBLaporanPasirSenarai.jsp";
   		String SkrinDaftarLaporan = "app/php2/frmAPBLaporanPasirDaftar.jsp";
   		String SkrinDaftarPasir = "app/php2/frmAPBLaporanPasirDaftarPengeluaranPasirLaut.jsp";   
   		String SkrinUploadLampiran = "app/php2/frmAPBLampiranPasirLaut.jsp"; 
   		
   		// GET PARAMETER VALUES
   		String id_jadualkedualesenAPB = getParam("id_jadualkedualesenAPB");
   		context.put("id_jadualkedualesenAPB", id_jadualkedualesenAPB);
   		
   		String id_laporanpasir = getParam("id_laporanpasir");
   		context.put("id_laporanpasir", id_laporanpasir);
   		
   		String id_borangk2k3 = getParam("id_borangk2k3");
   		context.put("id_borangk2k3", id_borangk2k3);
   		
   		String bulan_pengambilan = getParam("bulan_pengambilan");
   		context.put("bulan_pengambilan", bulan_pengambilan);
   		
   		String idJamDari = getParam("socJamDari");
		if (idJamDari == null || idJamDari.trim().length() == 0) {
			idJamDari = "99999";
		}
		String idMinitDari = getParam("socMinitDari");
		if (idMinitDari == null || idMinitDari.trim().length() == 0) {
			idMinitDari = "99999";
		}
		String id_dokumen = getParam("id_dokumen");
   		context.put("id_dokumen", id_dokumen);
		
   		
   		
   		// GET DATA PELESEN
		modelLaporanPasir.getMaklumatPelesen(id_jadualkedualesenAPB);
		Vector getMaklumatPelesen = modelLaporanPasir.getMaklumatPelesen();		
		context.put("dataPelesen", dataPelesen);
		Double txtKadarRoyalti = 0.00;
		if(getMaklumatPelesen.size()!=0){
			Hashtable a = (Hashtable)getMaklumatPelesen.get(0);
			txtKadarRoyalti = Double.parseDouble(a.get("kadar_royalti").toString());
		}		
		context.put("txtKadarRoyalti", txtKadarRoyalti);
		
		if(getMaklumatPelesen.size()!=0){
			Hashtable a = (Hashtable) getMaklumatPelesen.get(0);
			String lblNamaPelesen = (String) a.get("nama");
			context.put("lblNamaPelesen", lblNamaPelesen);
			String lblNoPelesen = (String)a.get("no_siri_lesen");
			context.put("lblNoPelesen", lblNoPelesen);
			// ALAMAT TETAP
			String alamat1tetap = (String)a.get("alamat1_tetap");
			context.put("lblAlamat1Tetap", alamat1tetap);
			String alamat2tetap = (String)a.get("alamat2_tetap");
			context.put("lblAlamat2Tetap", alamat2tetap);			
			String alamat3tetap = (String)a.get("alamat3_tetap");
			context.put("lblAlamat3Tetap", alamat3tetap);	
			String poskodtetap = (String)a.get("poskod_tetap");
			context.put("lblPoskodTetap", poskodtetap);		
			String bandartetap = (String)a.get("bandartetap");
			context.put("lblBandarTetap", bandartetap);
			String negeritetap = (String)a.get("negeritetap");
			context.put("lblNegeriTetap", negeritetap);
			// ALAMAT SURAT-MENYURAT
			String alamat1surat = (String)a.get("alamat1_surat");
			context.put("lblAlamat1Surat", alamat1surat);
			String alamat2surat = (String)a.get("alamat2_surat");
			context.put("lblAlamat2Surat", alamat2tetap);			
			String alamat3surat = (String)a.get("alamat3_surat");
			context.put("lblAlamat3Surat", alamat3tetap);	
			String poskodsurat = (String)a.get("poskod_surat");
			context.put("lblPoskodSurat", poskodtetap);		
			String bandarsurat = (String)a.get("bandarsurat");
			context.put("lblBandarSurat", bandartetap);
			String negerisurat = (String)a.get("negerisurat");
			context.put("lblNegeriSurat", negerisurat);			
			
			String lblNoHp = (String)a.get("no_tel_bimbit");
			context.put("lblNoHp", lblNoHp);			
			String lblNoPej = (String)a.get("no_tel_pejabat");
			context.put("lblNoPej", lblNoPej);	
			String lblNoRumah = (String)a.get("no_tel_rumah");
			context.put("lblNoRumah", lblNoRumah);	
			
		}
   		
    	if("papar_pelesen".equals(actionLaporanPasir)){ 
    		
        	// VALUES JSP
        	String socBulan = "";
    		context.put("socBulan", "");
    			
    		String txtTahun = "";
    		context.put("txtTahun", ""); 
    		context.put("PermohonanList", "");
           	// GET LIST DATA
    		listLaporan = modelLaporanPasir.getListLaporan(id_jadualkedualesenAPB);	
    		context.put("PermohonanList", listLaporan);
    		context.put("list_size", listLaporan.size());  	    		          		 		
    		
    		// PAGING
    		setupPageLaporan(session,action,listLaporan,SkrinListLaporan);
    		
        	// SCREEN
        	vm = SkrinListLaporan;
        		
        }else if("daftarLaporan".equals(actionLaporanPasir)){
        	
        	// DATA
        	context.put("selectBulan",HTML.SelectBulan( "socBulan","style=width:auto" ));
           	
        	// VALIDATION
        	context.put("button", "add");
        	context.put("clearForm", "yes");
        	context.put("flag", "");
        	context.put("mode", "");
        	
        	
        	// SCREEN
        	vm = SkrinDaftarLaporan;
        	
        }else if("simpanLaporan".equals(actionLaporanPasir)){
        	
        	String socBulan = getParam("socBulan");
        	String txtTahun = getParam("txtTahun");
        	String txtTarikh = getParam("txtTarikhOperasi");
	        String txtBulan = txtTarikh.substring(3,5);
			String txtTahunOps = txtTarikh.substring(6,10);
        
        	boolean returnChecking = false;
        	
        	// returnChecking = FrmAPBLaporanPasirData.isBulanExist(id_jadualkedualesenAPB,socBulan,txtTahun);
        	returnChecking = FrmAPBLaporanPasirData.isBulanExist(id_jadualkedualesenAPB,txtBulan,txtTahunOps);
        	if(returnChecking == false){
        		
            	// INSERT TBLPHPLAPORANPASIR        	
            	String result = "";
            	result = simpanLaporan(usid,id_jadualkedualesenAPB);
            	
            	context.put("id_laporanpasir",result);
            	
            	id_laporanpasir = result;
            	myLogger.info("id_laporanpasir :: "+id_laporanpasir);        	
            	
            	if(id_laporanpasir!=""){
            		
            		// GET LATEST DATA LAPORAN
            		modelLaporanPasir.getMaklumatLaporan(id_laporanpasir);
            		Vector getMaklumatLaporan = modelLaporanPasir.getMaklumatLaporan();
            		context.put("dataLaporan", getMaklumatLaporan);
            		
            		String bulan = "";
            		if(getMaklumatLaporan.size()!=0){
            			Hashtable a = (Hashtable) getMaklumatLaporan.get(0);
            			bulan = (String)a.get("bulan_pengambilan");
            		}
     	
                	// DATA
                	context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan),"disabled", " class=\"disabled\""));
                	context.put("bulan_pengambilan",bulan);
                	
                	// VALIDATION
                	context.put("button", "view");
                	context.put("clearForm", "");
                	context.put("flag", "semak");
                	context.put("mode", "disabled"); 
//                	context.put("returnChecking", false);
                	
                	// GET LIST PENGELUARAN PASIR LAUT
                	//listPasir = modelLaporanPasir.getListPasir(id_laporanpasir);	
            		//context.put("PermohonanPasir", listPasir);
            		//context.put("list_size", listPasir.size());  	    		          		 		
            		
            		// PAGING
            		setupPagePasir(session,action,listPasir);
                	            	
                	// SCREEN
                	vm = SkrinDaftarLaporan;      
            		
            	}else{
            		
                	// VALUES JSP
                	socBulan = "";
            		context.put("socBulan", "");
            			
            		txtTahun = "";
            		context.put("txtTahun", ""); 

                   	// GET LIST DATA
            		listLaporan = modelLaporanPasir.getListLaporan(id_jadualkedualesenAPB);	
            		listLaporan = new Vector();
            		context.put("PermohonanList", listLaporan);
            		context.put("list_size", listLaporan.size());  	    		          		 		
            		
            		// PAGING
            		setupPageLaporan(session,action,listLaporan,SkrinListLaporan);
            		
                	// SCREEN
                	vm = SkrinListLaporan; 
                	
            	}// CLOSE id_laporanpasir!=""
            	
        	}else{
        		
        		// VALIDATION
            	context.put("button", "add");
            	context.put("clearForm", "");
            	context.put("flag", "");
            	context.put("mode", ""); 
        		context.put("returnChecking", true);
        		
        		// SCREEN
            	vm = SkrinDaftarLaporan; 
            	
        	}// CLOSE returnChecking == false
        	
        
        	
    	}else if("papar_laporan".equals(actionLaporanPasir)){
    		
       		// GET LATEST DATA LAPORAN
    		modelLaporanPasir.getMaklumatLaporan(id_laporanpasir);
    		Vector getMaklumatLaporan = modelLaporanPasir.getMaklumatLaporan();
    		context.put("dataLaporan", getMaklumatLaporan);
    		
    		String bulan = "";
    		if(getMaklumatLaporan.size()!=0){
    			Hashtable a = (Hashtable) getMaklumatLaporan.get(0);
    			bulan = (String)a.get("bulan_pengambilan");
    		}
	
        	// DATA LAPORAN
        	context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan), "style=width:auto disabled", " class=\"disabled\""));   	
        	
        	// KIRAAN JUMLAH KUANTITI & JUMLAH ROYALTI
        	modelLaporanPasir.getJumlahKiraan(id_laporanpasir);
    		Vector getJumlahKiraan = modelLaporanPasir.getJumlahKiraan();
    		double jumKuantiti = 0.00;
    		double jumAnggaran_royalti = 0.00;
    		if (getJumlahKiraan.size()!=0){
    			Hashtable a = (Hashtable)getJumlahKiraan.get(0);
    			jumKuantiti = Double.parseDouble(a.get("jumKuantiti").toString());
    			jumAnggaran_royalti = Double.parseDouble(a.get("jumAnggaran_royalti").toString());    			
    		}
    		context.put("txtJumKuantiti",jumKuantiti);
    		context.put("txtJumRoyalti", jumAnggaran_royalti);
        	
        	// VALIDATION
        	context.put("button", "view");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "disabled");
        	
        	/*// GET LIST PENGELUARAN PASIR LAUT
        	listPasir = modelLaporanPasir.getListPasir(id_laporanpasir);	
    		context.put("PermohonanPasir", listPasir);
    		context.put("list_size", listPasir.size());  	    		          		 		
    		
    		// PAGING
    		setupPagePasir(session,action,listPasir);*/
        	
        	// GET LIST DOKUMEN PENGELUARAN PASIR LAUT
        	listDokumen = modelLaporanPasir.getListDokumen(id_laporanpasir);	
    		context.put("SenaraiDokumen", listDokumen);
    		context.put("list_size", listDokumen.size());  	    		          		 		
    		
    		// PAGING
    		setupPageUpload(session,action,listDokumen);
        	
    		
    		// SCREEN
        	vm = SkrinDaftarLaporan;        	
        	
		}else if("hapusLaporan".equals(actionLaporanPasir)){			
						
			modelTrasaction.deleteLaporanPasir(getParam("id_laporanpasir"));          

            if((!id_laporanpasir.equals("")) && (!id_laporanpasir.equals(null))){
               	// GET LIST DATA
        		listLaporan = modelLaporanPasir.getListLaporan(id_jadualkedualesenAPB);	
        		context.put("PermohonanList", listLaporan);
        		context.put("list_size", listLaporan.size());     		
			}else{				
				context.put("PermohonanList", "");
				context.put("list_size", 0);
			}
            
            vm = SkrinListLaporan;	
            
        	
        }else if("kemaskiniLaporan".equals(actionLaporanPasir)){
        	
       		// GET LATEST DATA LAPORAN
    		modelLaporanPasir.getMaklumatLaporan(id_laporanpasir);
    		Vector getMaklumatLaporan = modelLaporanPasir.getMaklumatLaporan();
    		context.put("dataLaporan", getMaklumatLaporan);
    		
    		String bulan = "";
    		if(getMaklumatLaporan.size()!=0){
    			Hashtable a = (Hashtable) getMaklumatLaporan.get(0);
    			bulan = (String)a.get("bulan_pengambilan");
    		}
	
        	// DATA
        	context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan), "style=width:auto" ));   	
        	
        	// VALIDATION
        	context.put("button", "edit");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "");       	
        	
        	
        	// SCREEN
        	vm = SkrinDaftarLaporan;         	  
        	
        }else if("simpanEditLaporan".equals(actionLaporanPasir)){
        	
        	simpanEditLaporan(usid,id_laporanpasir);
        	
    		// GET LATEST DATA LAPORAN
    		modelLaporanPasir.getMaklumatLaporan(id_laporanpasir);
    		Vector getMaklumatLaporan = modelLaporanPasir.getMaklumatLaporan();
    		context.put("dataLaporan", getMaklumatLaporan);
    		
    		String bulan = "";
    		if(getMaklumatLaporan.size()!=0){
    			Hashtable a = (Hashtable) getMaklumatLaporan.get(0);
    			bulan = (String)a.get("bulan_pengambilan");
    		}
	
        	// DATA
    		context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan), "style=width:auto disabled", " class=\"disabled\""));            	
        	
        	// VALIDATION
        	context.put("button", "view");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "disabled");        	
        	
        	// SCREEN
        	vm = SkrinDaftarLaporan;           	        	 
        	
        }else if("daftarPasirLaut".equals(actionLaporanPasir)){    
        	
       		String jumKuantitiHidden = getParam("jumKuantitiHidden");
       		context.put("jumKuantitiHidden", jumKuantitiHidden);
       		myLogger.info("Kuantiti >>> "+jumKuantitiHidden);
       		
       		String jumRoyaltiHidden = getParam("jumRoyaltiHidden");
       		context.put("jumRoyaltiHidden", jumRoyaltiHidden);
       		myLogger.info("Royalti >>> "+jumRoyaltiHidden);
        	
        	// VALIDATION
        	context.put("button", "add");
        	context.put("clearForm", "yes");
        	context.put("flag", "");
        	context.put("mode", "");
        	
        	this.context.put("selectJamDari", HTML.SelectJam("socJamDari",Long.parseLong(idJamDari), "", ""));
			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "", ""));
        	// SCREEN
        	vm = SkrinDaftarPasir;              	        	
        	
        }else if("simpanPasir".equals(actionLaporanPasir)){
        	
           	// INSERT TBLPHPBORANGK2K3        	
        	String result = "";
        	result = simpanPasir(usid,id_laporanpasir);
        	
        	context.put("id_borangk2k3",result);
        	
        	id_borangk2k3 = result;
        	myLogger.info("id_borangk2k3 :: "+id_borangk2k3);        	
        	
        	if(id_borangk2k3!=""){
        		
        		// GET LATEST DATA LAPORAN
        		modelLaporanPasir.getMaklumatPasir(id_borangk2k3);
        		Vector getMaklumatPasir = modelLaporanPasir.getMaklumatPasir();
        		context.put("dataPasir", getMaklumatPasir);
        		
        		if (getMaklumatPasir.size() != 0) {
					Hashtable hashMaklumatPasir = (Hashtable) modelLaporanPasir.getMaklumatPasir().get(0);
					idJamDari = (String) (hashMaklumatPasir.get("jam_hantar"));
					idMinitDari = (String) (hashMaklumatPasir.get("minit_hantar"));
				}
        		
            	// GET KIRAAN JUMLAH KUANTITI & JUMLAH ROYALTI
            	modelLaporanPasir.getJumlahKiraan(id_laporanpasir);
        		Vector getJumlahKiraan = modelLaporanPasir.getJumlahKiraan();
        		double jumKuantiti = 0.00;
        		double jumAnggaran_royalti = 0.00;
        		if (getJumlahKiraan.size()!=0){
        			Hashtable a = (Hashtable)getJumlahKiraan.get(0);
        			jumKuantiti = Double.parseDouble(a.get("jumKuantiti").toString());
        			jumAnggaran_royalti = Double.parseDouble(a.get("jumAnggaran_royalti").toString());    			
        		}
        		context.put("jumKuantitiHidden",jumKuantiti);
        		context.put("jumRoyaltiHidden", jumAnggaran_royalti);
        		this.context.put("selectJamDari", HTML.SelectJam("socJamDari",	Long.parseLong(idJamDari), "disabled"," class=\"disabled\""));
				this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari),"disabled", " class=\"disabled\""));
        		
        		// UPDATE TBLPHPLAPORANPASIR
        		updateJumlahKiraan(usid,id_laporanpasir,jumKuantiti,jumAnggaran_royalti);

	        	// VALIDATION
	        	context.put("button", "view");
	        	context.put("clearForm", "");
	        	context.put("flag", "semak");
	        	context.put("mode", "disabled");
	        	  
	        	// SCREEN
	        	vm = SkrinDaftarPasir;    
	        	
        	}else if("paparPasir".equals(actionLaporanPasir)){
        	
    		// GET LATEST DATA LAPORAN
    		modelLaporanPasir.getMaklumatPasir(id_borangk2k3);
    		Vector getMaklumatPasir = modelLaporanPasir.getMaklumatPasir();
    		context.put("dataPasir", getMaklumatPasir);
    		
    		if (getMaklumatPasir.size() != 0) {
				Hashtable hashMaklumatPasir = (Hashtable) modelLaporanPasir.getMaklumatPasir().get(0);
				idJamDari = (String) (hashMaklumatPasir.get("jam_hantar"));
				idMinitDari = (String) (hashMaklumatPasir.get("minit_hantar"));
			}
    		
    		if (idJamDari == null || idJamDari.trim().length() == 0) {
    			idJamDari = "99999";
    		}

    		if (idMinitDari == null || idMinitDari.trim().length() == 0) {
    			idMinitDari = "99999";
    		}
    		
    		this.context.put("selectJamDari", HTML.SelectJam("socJamDari",	Long.parseLong(idJamDari), "disabled"," class=\"disabled\""));
			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari),"disabled", " class=\"disabled\""));

        	// VALIDATION
        	context.put("button", "view");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "disabled");       	
        	  
        	// SCREEN
        	vm = SkrinDaftarPasir;   	        	

        }else if("hapusBorang".equals(actionLaporanPasir)){
        	
			modelTrasaction.deleteLaporanBorang(getParam("id_borangk2k3"));          

            if((!id_borangk2k3.equals("")) && (!id_borangk2k3.equals(null))){
            	
           		// GET LATEST DATA LAPORAN
        		modelLaporanPasir.getMaklumatLaporan(id_laporanpasir);
        		Vector getMaklumatLaporan = modelLaporanPasir.getMaklumatLaporan();
        		context.put("dataLaporan", getMaklumatLaporan);
        		
        		String bulan = "";
        		if(getMaklumatLaporan.size()!=0){
        			Hashtable a = (Hashtable) getMaklumatLaporan.get(0);
        			bulan = (String)a.get("bulan_pengambilan");
        		}
    	
            	// DATA LAPORAN
            	context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan), "style=width:auto disabled", " class=\"disabled\""));   	
            	
            	// VALIDATION
            	context.put("button", "view");
            	context.put("clearForm", "");
            	context.put("flag", "semak");
            	context.put("mode", "disabled");
            	
            	// GET LIST PENGELUARAN PASIR LAUT
            	listPasir = modelLaporanPasir.getListPasir(id_laporanpasir);	
        		context.put("PermohonanPasir", listPasir);
        		context.put("list_size", listPasir.size());  	    		          		 		        		

			}else{				
				context.put("PermohonanList", "");
				context.put("list_size", 0);
			}
            
    		// PAGING
    		setupPagePasir(session,action,listPasir);
  
        	// SCREEN
        	vm = SkrinDaftarLaporan;  	
        	
        }else if("kemaskiniBorang".equals(actionLaporanPasir)){
        	
    		// GET LATEST DATA LAPORAN
    		modelLaporanPasir.getMaklumatPasir(id_borangk2k3);
    		Vector getMaklumatPasir = modelLaporanPasir.getMaklumatPasir();
    		context.put("dataPasir", getMaklumatPasir);
    		
    		if (getMaklumatPasir.size() != 0) {
				Hashtable hashMaklumatPasir = (Hashtable) modelLaporanPasir.getMaklumatPasir().get(0);
				idJamDari = (String) (hashMaklumatPasir.get("jam_hantar"));
				idMinitDari = (String) (hashMaklumatPasir.get("minit_hantar"));
			}
    		
    		if (idJamDari == null || idJamDari.trim().length() == 0) {
    			idJamDari = "99999";
    		}
    		if (idMinitDari == null || idMinitDari.trim().length() == 0) {
    			idMinitDari = "99999";
    		}
    		
    		this.context.put("selectJamDari", HTML.SelectJam("socJamDari",Long.parseLong(idJamDari), "", ""));
			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "", ""));

        	// VALIDATION
        	context.put("button", "edit");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "");          	
        	
        	// SCREEN
        	vm = SkrinDaftarPasir;   
        	
        }else if("simpanEditBorang".equals(actionLaporanPasir)){
        	
        	// UPDATE TBLPHPBORANGK2K3
        	simpanEditBorang(usid,id_borangk2k3);  
        	
    		// GET LATEST DATA LAPORAN
    		modelLaporanPasir.getMaklumatPasir(id_borangk2k3);
    		Vector getMaklumatPasir = modelLaporanPasir.getMaklumatPasir();
    		context.put("dataPasir", getMaklumatPasir);

    		if (getMaklumatPasir.size() != 0) {
				Hashtable hashMaklumatPasir = (Hashtable) modelLaporanPasir.getMaklumatPasir().get(0);
				idJamDari = (String) (hashMaklumatPasir.get("jam_hantar"));
				idMinitDari = (String) (hashMaklumatPasir.get("minit_hantar"));
			}
    		
    		this.context.put("selectJamDari", HTML.SelectJam("socJamDari",	Long.parseLong(idJamDari), "disabled"," class=\"disabled\""));
			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari),"disabled", " class=\"disabled\""));
        	// VALIDATION
        	context.put("button", "view");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "disabled");
        	        	
        	// SCREEN
        	vm = SkrinDaftarPasir;           	        	
        	
        }else if("batalBaru".equals(actionLaporanPasir)){
        	
        	// DATA
        	context.put("selectBulan",HTML.SelectBulan( "socBulan","style=width:auto" ));
        	
        	// VALIDATION
        	context.put("clearForm", "yes");
        	context.put("button", "add");
        	
        	// SCREEN
        	vm = SkrinDaftarLaporan;
        	
        }else{
        		
           		// GET LATEST DATA LAPORAN
        		modelLaporanPasir.getMaklumatLaporan(id_laporanpasir);
        		Vector getMaklumatLaporan = modelLaporanPasir.getMaklumatLaporan();
        		context.put("dataLaporan", getMaklumatLaporan);
        		
        		String bulan = "";
        		if(getMaklumatLaporan.size()!=0){
        			Hashtable a = (Hashtable) getMaklumatLaporan.get(0);
        			bulan = (String)a.get("bulan_pengambilan");
        		}
    	
            	// DATA LAPORAN
            	context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan), "style=width:auto disabled", " class=\"disabled\""));   	
            	
            	// VALIDATION
            	context.put("button", "view");
            	context.put("clearForm", "");
            	context.put("flag", "semak");
            	context.put("mode", "disabled");
            	
            	// GET LIST PENGELUARAN PASIR LAUT
            	listPasir = modelLaporanPasir.getListPasir(id_laporanpasir);	
        		context.put("PermohonanPasir", listPasir);
        		context.put("list_size", listPasir.size());  	    		          		 		
        		
        		// PAGING
        		setupPagePasir(session,action,listPasir);
            	        		
        		// SCREEN
        		vm = SkrinUploadLampiran;
        		
        	}	
	
        	this.context.put("selectJamDari", HTML.SelectJam("socJamDari",	Long.parseLong(idJamDari), "disabled"," class=\"disabled\""));
			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari),"disabled", " class=\"disabled\""));
        	
        }else if("search_laporan".equals(actionLaporanPasir)){
        	
        	// GET METHOD CARIAN
        	carianLaporan(id_jadualkedualesenAPB);
        	listLaporan = modelLaporanPasir.getListCarianLaporan();
        	
       		// DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listLaporan);
    		context.put("list_size", listLaporan.size());  

    		// MAINTAIN SEARCHING VALUES 
    		this.context.put("socBulan", getParam("socBulan"));
    		this.context.put("txtTahun", getParam("txtTahun"));
    		
    		// PAGING
    		setupPageLaporan(session,action,listLaporan,SkrinListLaporan);
        		     		
        	// SCREEN
        	vm = SkrinListLaporan;
        	
        }else if("Cari".equals(actionLaporanPasir)){
        	
        	// GET METHOD CARIAN
			carianPermohonan();
			list = modelLaporanPasir.getListCarian();

    		// DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", list);
    		context.put("list_size", list.size());  

    		// MAINTAIN SEARCHING VALUES 
    		this.context.put("txtNamaPelesen", getParam("txtNamaPelesen"));
    		this.context.put("txtNoLesen", getParam("txtNoLesen"));
    		
    		// PAGING
    		setupPagePelesen(session,action,list);
    		
        	// SCREEN
        	vm = SkrinListDepan;  
   		
   		
        }else if("uploadBaruDokumen".equals(actionLaporanPasir)){
        	
        	// VALIDATION
        	context.put("button", "add");
        	context.put("clearForm", "yes");
        	context.put("flag", "");
        	context.put("mode", "");
        	
        	// SCREEN
        	vm = SkrinUploadLampiran;

        	 
        //FUNCTION UPLOAD LAMPIRAN BY AIN 08062017
        }else if("simpanDokumen".equals(actionLaporanPasir)){

			// INSERT TBLPHPDOKUMEN        	
        	String result = "";
        	result = simpanDokumen(usid, id_laporanpasir);
        	
        	context.put("id_dokumen",result);
        	
        	id_dokumen = result;
        	myLogger.info("id_dokumen :: "+id_dokumen);        	
        	
        	if(id_dokumen!=""){
        		
        		// GET LATEST DATA LAPORAN
        		modelLaporanPasir.getMaklumatDokumen(id_laporanpasir);
        		Vector getMaklumatDokumen = modelLaporanPasir.getMaklumatDokumen();
        		context.put("dataDokumen", getMaklumatDokumen);
        		
	        	// VALIDATION
	        	context.put("button", "view");
	        	context.put("clearForm", "");
	        	context.put("flag", "semak");
	        	context.put("mode", "disabled");
	        	  
	        	// SCREEN
	        	vm = SkrinUploadLampiran;  
	        	
        	}else if("papar_dokumen".equals(actionLaporanPasir)){ 
    		
	       		// GET LATEST DATA LAPORAN
	    		modelLaporanPasir.getMaklumatDokumen(id_dokumen);
	    		Vector getMaklumatDokumen = modelLaporanPasir.getMaklumatDokumen();
	    		context.put("dataDokumen", getMaklumatDokumen);
		
	    		// VALIDATION
	        	context.put("button", "view");
	        	context.put("clearForm", "");
	        	context.put("flag", "semak");
	        	context.put("mode", "disabled"); 
	        	
	        	// GET LIST DOKUMEN PENGELUARAN PASIR LAUT
	        	listDokumen = modelLaporanPasir.getListDokumen(id_dokumen);	
	    		context.put("SenaraiDokumen", listDokumen);
	    		context.put("list_size", listDokumen.size());   		          		 		
	    		
	    		// PAGING
	    		setupPageUpload(session,action,listDokumen);         	
	    		
	    		// SCREEN
	        	vm = SkrinDaftarLaporan;  
	        	
        	}else{
        		
        		// GET LATEST DATA DOKUMEN
        		modelLaporanPasir.getMaklumatDokumen(id_dokumen);
        		Vector getMaklumatDokumen = modelLaporanPasir.getMaklumatDokumen();
        		context.put("dataDokumen", getMaklumatDokumen);
        		
            	// VALIDATION
            	context.put("button", "view");
            	context.put("clearForm", "");
            	context.put("flag", "semak");
            	context.put("mode", "disabled");
            	
            	// GET LIST DOKUMEN PENGELUARAN PASIR LAUT
            	listDokumen = modelLaporanPasir.getListDokumen(id_dokumen);	
        		context.put("SenaraiDokumen", listDokumen); 	    		          		 		
            	        		
        		// SCREEN
        		vm = SkrinDaftarLaporan;
        	}
        	
        }else if("kemaskiniDokumen".equals(actionLaporanPasir)){
        	
    		// GET LATEST DATA DOKUMEN
    		modelLaporanPasir.getMaklumatDokumen(id_dokumen);
    		Vector getMaklumatDokumen = modelLaporanPasir.getMaklumatDokumen();
    		context.put("dataDokumen", getMaklumatDokumen);
    		
        	// VALIDATION
        	context.put("button", "edit");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "");          	
        	
        	// SCREEN
        	vm = SkrinUploadLampiran; 
        	
    	}else if("simpanKemaskiniLampiran".equals(actionLaporanPasir)){
        	
        	// UPDATE TBLPHPDOKUMEN
    		simpanKemaskiniDokumen(usid,id_dokumen);  
        	
    		// GET LATEST DATA LAPORAN
    		modelLaporanPasir.getMaklumatDokumen(id_dokumen);
    		Vector getMaklumatDokumen = modelLaporanPasir.getMaklumatDokumen();
    		context.put("dataDokumen", getMaklumatDokumen);

    		// VALIDATION
        	context.put("button", "view");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "disabled");
        	        	
        	// SCREEN
        	vm = SkrinUploadLampiran;           	        	
        	
        }else if("paparDokumen".equals(actionLaporanPasir)){
        	
			// GET LATEST DATA LAPORAN
        	id_dokumen = getParam("id_dokumen");
			modelLaporanPasir.getMaklumatDokumen(id_dokumen);
    		Vector getMaklumatDokumen = modelLaporanPasir.getMaklumatDokumen();
    		context.put("dataDokumen", getMaklumatDokumen);
    		
        	// VALIDATION
        	context.put("button", "view");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "disabled");   
        	
        	vm = SkrinUploadLampiran;
        	
    	}else if("hapusDokumen".equals(actionLaporanPasir)){
        	
			modelTrasaction.deleteDokumen(getParam("id_dokumen"));          

            if((!id_dokumen.equals("")) && (!id_dokumen.equals(null))){
            	
           		// GET LATEST DATA LAPORAN
            	modelLaporanPasir.getMaklumatDokumen(id_laporanpasir);
        		Vector getMaklumatDokumen = modelLaporanPasir.getMaklumatDokumen();
        		context.put("dataDokumen", getMaklumatDokumen);
        		
            	// VALIDATION
            	context.put("button", "view");
            	context.put("clearForm", "");
            	context.put("flag", "semak");
            	context.put("mode", "disabled");
            	
            	// GET LIST PENGELUARAN PASIR LAUT
            	listDokumen = modelLaporanPasir.getListDokumen(id_laporanpasir);	
        		context.put("SenaraiDokumen", listDokumen);
        		context.put("list_size", listDokumen.size());  	    		          		 		        		

			}else{				
				context.put("SenaraiDokumen", "");
				context.put("list_size", 0);
			}
            
    		// PAGING
    		setupPageUpload(session,action,listDokumen);
  
        	// SCREEN
        	vm = SkrinDaftarLaporan;  	
        	
        }else {  
        		
        	// VALUES JSP
        	String txtNamaPelesen = "";
    		context.put("txtNamaPelesen", "");
    			
    		String txtNoLesen = "";
    		context.put("txtNoLesen", "");

           	//GET LIST DATA
    		list = modelLaporanPasir.getListPemohon();	
    		context.put("PermohonanList", list);
    		context.put("list_size", list.size());  	    		          		 		
    		
    		// PAGING
    		setupPagePelesen(session,action,list);
        		
        	// SCREEN
        	vm = SkrinListDepan;   		
        }   	  	
    	
    	//SET DEFAULT PARAM
        this.context.put("actionLaporanPasir", actionLaporanPasir);
        
        return vm;
	}
	
// STARTING METHOD
	
	// UPDATELAPORAN
	private void simpanEditBorang(String usid, String id_borangk2k3) throws Exception {
		
		String txdTarikhHantar = getParam("txdTarikhHantar");
		String txtNamaBarge = getParam("txtNamaBarge");
		String txtLokasi = getParam("txtLokasi");
		String txtKuantiti = getParam("txtKuantiti");
		String txtAnggaranRoyalti = getParam("txtAnggaranRoyalti");
		String txtNoKastam = getParam("txtNoKastam");
		String txdHariHantar = getParam("txdHariHantar");
		
		String idJamDari = getParam("socJamDari");
		if (idJamDari == null || idJamDari.trim().length() == 0) {
			idJamDari = "99999";
		}
		String idMinitDari = getParam("socMinitDari");
		if (idMinitDari == null || idMinitDari.trim().length() == 0) {
			idMinitDari = "99999";
		}
		modelTrasaction.simpanEditBorang(usid,id_borangk2k3,txdTarikhHantar,txtNamaBarge,txtLokasi,
				txtKuantiti,txtAnggaranRoyalti,txtNoKastam,txdHariHantar, idJamDari, idMinitDari);
				
	}// CLOSE UPDATELAPORAN		
	
	private String userData(String id_user) throws Exception{
		
		Vector listUserid = new Vector();
		listUserid.clear();
		
		modelLaporanPasir.setGetUserId(id_user);
	    listUserid = modelLaporanPasir.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    
	    return userIdNeg;
	}	
	
	// CARIAN DEPAN
	private void carianPermohonan() throws Exception {
		
		String txtNamaPelesen = getParam("txtNamaPelesen");
		String txtNoLesen = getParam("txtNoLesen");
		modelLaporanPasir.setCarianFail(txtNamaPelesen,txtNoLesen);	
		
	}// CLOSE CARIAN
	
	// CARIAN LAPORAN
	private void carianLaporan(String id_jadualkedualesenAPB) throws Exception {
		
		String socBulan = getParam("socBulan");
		String txtTahun = getParam("txtTahun");
		modelLaporanPasir.setCarianLaporan(socBulan,txtTahun,id_jadualkedualesenAPB);	
		
	}// CLOSE CARIAN
	
	// SIMPAN LAPORAN
	private String simpanLaporan(String usid, String id_jadualkedualesenAPB) throws Exception {
		
		String txtJumKuantiti = getParam("txtJumKuantiti");
		String txtJumRoyalti = getParam("txtJumRoyalti");
//		String socBulan = getParam("socBulan");
//		String txtTahun = getParam("txtTahun");
		/*String txtKontraktor = getParam("txtKontraktor");*/
		/*String txtPembeli = getParam("txtPembeli");*/
		String txtTarikhOperasi = getParam("txtTarikhOperasi");
		String txtMasaOperasi  = getParam("txtMasaOperasi");
    	String txtHariOperasi  = getParam("txtHariOperasi");
    	String txtBulan = txtTarikhOperasi.substring(3,5);
    	String txtTahun = txtTarikhOperasi.substring(6,10);
    	String txtKapal = getParam("txtNamaKapal");
		
		return modelTrasaction.simpanLaporan(usid,id_jadualkedualesenAPB,txtJumKuantiti,txtJumRoyalti,txtTarikhOperasi,txtBulan,txtTahun,txtMasaOperasi,txtHariOperasi,txtKapal);	
		
	}// CLOSE SIMPAN LAPORAN
	
	// SIMPAN PASIR
	private String simpanPasir(String usid, String id_laporanpasir) throws Exception {
		
		String txdTarikhHantar = getParam("txdTarikhHantar");
		String txtNamaBarge = getParam("txtNamaBarge");
		String txtLokasi = getParam("txtLokasi");
		String txtKuantiti = getParam("txtKuantiti");
		String txtAnggaranRoyalti = getParam("txtAnggaranRoyalti");
		String txtAkuanKastam = "K3";
		String txtNoKastam = getParam("txtNoKastam");
		String txdHariHantar = getParam("txdHariHantar");
		
		String idJamDari = getParam("socJamDari");
		if (idJamDari == null || idJamDari.trim().length() == 0) {
			idJamDari = "99999";
		}
		String idMinitDari = getParam("socMinitDari");
		if (idMinitDari == null || idMinitDari.trim().length() == 0) {
			idMinitDari = "99999";
		}
		
		return modelTrasaction.simpanPasir(usid,id_laporanpasir,txdTarikhHantar,txtNamaBarge,
				txtLokasi,txtKuantiti,txtAnggaranRoyalti,txtAkuanKastam,txtNoKastam, txdHariHantar, idJamDari, idMinitDari);		
		
	}// CLOSE SIMPAN PASIR
	
	// SIMPAN DOKUMEN -AIN08062017-
	private String simpanDokumen(String usid, String id_laporanpasir) throws Exception {
		
		String txtNamaDokumen = getParam("txtNamaDokumen");
		String txtCatatan = getParam("txtCatatan");
		
		return modelTrasaction.simpanDokumen(usid, id_laporanpasir, txtNamaDokumen, txtCatatan);		
		
	}// CLOSE SIMPAN DOKUMEN
	
	// UPDATELAPORAN
	private void simpanKemaskiniDokumen(String usid, String id_dokumen) throws Exception {
			
		String txtNamaDokumen = getParam("txtNamaDokumen");
		String txtCatatan = getParam("txtCatatan");
		
		modelTrasaction.simpanKemaskiniDokumen(usid,id_dokumen,txtNamaDokumen,txtCatatan);
				
	}// CLOSE UPDATELAPORAN
	
	// UPDATELAPORAN
	private void simpanEditLaporan(String usid, String id_laporanpasir) throws Exception {
		
		String txtJumKuantiti = getParam("txtJumKuantiti");
		String txtJumRoyalti = getParam("txtJumRoyalti");
//		String socBulan = getParam("socBulan");
//		String txtTahun = getParam("txtTahun");
//		String txtKontraktor = getParam("txtKontraktor");
//		String txtPembeli = getParam("txtPembeli");
		String txtTarikhOperasi = getParam("txtTarikhOperasi");
		String txtMasaOperasi  = getParam("txtMasaOperasi");
		String txtHariOperasi  = getParam("txtHariOperasi");
		String txtBulan = txtTarikhOperasi.substring(3,5);
		String txtTahun = txtTarikhOperasi.substring(6,10);
		String txtKapal = getParam("txtNamaKapal");
		
		modelTrasaction.simpanEditLaporan(usid,id_laporanpasir,txtJumKuantiti,txtJumRoyalti,
				txtTarikhOperasi,txtBulan,txtTahun,txtMasaOperasi,txtHariOperasi,txtKapal);
				
	}// CLOSE UPDATELAPORAN	
	
	
	
	// UPDATE KIRAAN KUANTITI DAN ROYALTI
	private void updateJumlahKiraan(String usid,String id_laporanpasir,Double jumKuantiti,Double jumAnggaran_royalti) throws Exception {
		
		modelTrasaction.updateJumlahKiraan(usid,id_laporanpasir,jumKuantiti,jumAnggaran_royalti);
				
	}// CLOSE KIRAAN KUANTITI DAN ROYALTI		
	
	
	// PAGING SKRIN DEPAN
	public void setupPagePelesen(HttpSession session,String action,Vector list) {
		try {		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}	    
	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }	    	
	    Paging paging = new Paging(session,list,itemsPerPage);		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("PermohonanList",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}	
	// CLOSE PAGING SKRIN DEPAN
	
	// PAGING SKRIN LAPORAN
	public void setupPageLaporan(HttpSession session,String action,Vector listLaporan, String SkrinListLaporan ) {
		try {
		String vm = SkrinListLaporan;
		
		this.context.put("totalRecords",listLaporan.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}	    
	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }	    	
	    Paging paging = new Paging(session,listLaporan,itemsPerPage);		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("PermohonanList",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}	// CLOSE PAGING SKRIN LAPORAN
	
	// PAGING SKRIN PASIR
	public void setupPagePasir(HttpSession session,String action,Vector listPasir) {
		try {		
		this.context.put("totalRecords",listPasir.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}	    
	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }	    	
	    Paging paging = new Paging(session,listPasir,itemsPerPage);		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("PermohonanPasir",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}	// CLOSE PAGING SKRIN PASIR
	
	public void setupPageUpload(HttpSession session,String action,Vector listDokumen) {
		try {		
		this.context.put("totalRecords",listDokumen.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}	    
	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }	    	
	    Paging paging = new Paging(session,listDokumen,itemsPerPage);		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("SenaraiDokumen",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}	// CLOSE PAGING SKRIN UPLOAD
}
