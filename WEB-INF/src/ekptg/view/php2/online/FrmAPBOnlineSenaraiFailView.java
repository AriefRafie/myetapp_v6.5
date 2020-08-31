package ekptg.view.php2.online;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PUT;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.model.php2.utiliti.PHPUtilData;
import ekptg.helpers.HTML;
import ekptg.model.php2.utiliti.PHPUtilHTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBLaporanPasirData;
import ekptg.model.php2.frmAPBLaporanPasirTransaction;
import ekptg.model.php2.online.FrmAPBOnlineSenaraiFailData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.utils.lampiran.ILampiran;


public class FrmAPBOnlineSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView.class);
	private ILampiran iLampiran = null;
	
	FrmAPBOnlineSenaraiFailData logic = new FrmAPBOnlineSenaraiFailData();
	
	FrmAPBHeaderData header = new FrmAPBHeaderData();
	private String[] semaks;
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }	    	    
	    // DROP DOWN PENDAFTARAN
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionOnline = getParam("actionOnline");      
        String submit = getParam("command");   
 
        //GET ID PARAM
        String idFailSession = "";       
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
        String idFail = getParam("idFail");
        String idStatus = getParam("idStatus");
        String idJenisPermohonan = getParam("idJenisPermohonan");
        String idPermohonanLama = getParam("idPermohonanLama");
 
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String idPermohonan = getParam("idPermohonan");
		String idPengarah = getParam("idPengarah");
        String idPembeliPasir = getParam("idPembeliPasir");
        String idProjek = getParam("idProjek");
        String idPakar = getParam("idPakar");
        String idKoordinat = getParam("idKoordinat");
        String idPemohon = getParam("idPemohon");
        String idFailLama = getParam("idFailLama");
        String idDokumen = getParam("idDokumen"); // ADD MAKLUMAT LAMPIRAN
        String kategori = getParam("kategori");
        
        String id_laporanpasir = getParam("id_laporanpasir");
   		context.put("id_laporanpasir", id_laporanpasir);
        		        
        //VECTOR
        Vector<Hashtable<String,String>> list = null;
        
        //Vector list = null;
        Vector beanMaklumatPemohon = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanHeader = null;
        Vector beanMaklumatProjek = null; 
        Vector senaraiPengarah = null;
        Vector senaraiPembeliPasir = null;
        Vector senaraiProjek = null;
        Vector senaraiKoordinat = null;
        Vector senaraiPakar = null;
        Vector beanMaklumatKawasanMohon = null;
        Vector beanMaklumatLampiran = null;
        Vector senaraiLampiran = null;
        Vector senaraiSemak = null;
        Vector beanMaklumatPejabat = null; //BARU TAMBAH
        Vector beanHeaderBorangA = null;
        Vector beanMaklumatBarge = null;
        Vector listBorangA = null;
        
        Vector senaraiBarge = null;
        
        Vector beanLaporan = null;
        
        Vector listPasir = null;
        
        Vector listLaporan = null;
        
        Vector listB = null;
      
        //GET DROPDOWN PARAM
        String idKaitanTujuan = getParam("socKaitanTujuan");
		if (idKaitanTujuan == null || idKaitanTujuan.trim().length() == 0){
			idKaitanTujuan = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idFlagLuar = getParam("socFlagLuar");
		if (idFlagLuar == null || idFlagLuar.trim().length() == 0){
			idFlagLuar = "99999";
		}
		
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999"; 
		}
		String idJenisLesen = getParam("socJenisLesen");
		if (idJenisLesen == null || idJenisLesen.trim().length() == 0){
			idJenisLesen = "99999";
		}
		String idJenistujuan = getParam("socJenisTujuan"); //yati tambah
		if (idJenistujuan == null || idJenistujuan.trim().length() == 0){
			idJenistujuan = "99999";
		}
		String idNegeriPerairan = getParam("socNegeriPerairan");
		if (idNegeriPerairan == null || idNegeriPerairan.trim().length() == 0){
			idNegeriPerairan = "99999";
		}
		String idKategoriIndividu = getParam("socIndividuBukanIndividu");
		if (idKategoriIndividu == null || idKategoriIndividu.trim().length() == 0){
			idKategoriIndividu = "99999";
		}
		String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0){
			idKategoriPemohon = "99999";
		}
      
		this.context.put("onload", "");
		
		String namaPemohon = "";
     	namaPemohon =  FrmAPBOnlineSenaraiFailView.namaPemohon(idFail);
     	
     	String noLesen = "";
     	noLesen =  FrmAPBOnlineSenaraiFailView.noLesen(idFail);
    
     	String idJadualKeduaLesen = "";
     	idJadualKeduaLesen =  FrmAPBOnlineSenaraiFailView.setIdJadualKeduaLesen(idFail);
     	this.context.put("idJadualKeduaLesen",idJadualKeduaLesen);

		log.info("actionOnline :" +actionOnline);
		log.info("hitButton : " +hitButton);
		log.info("mode : " +mode);
		log.info("modePopup : " +modePopup);
		log.info("flagPopup : " +flagPopup);
		
		FrmSemakan semak = null;
		//MAKLUMAT BORANG A
		String idJadualKedua = getParam("idJadualKedua");
		String idBorangA = getParam("idBorangA");
		String idBarge = getParam("idBarge");
		String idBorangB = getParam("idBorangB");
		
		//GET DROPDOWN PARAM
        String idBulan = getParam("socBulan");
		if (idBulan == null || idBulan.trim().length() == 0){
			idBulan = "99999";
		}
		String idBulanList = getParam("socBulanList");
		if (idBulanList == null || idBulanList.trim().length() == 0){
			idBulanList = "99999";
		}

		
		//SAVE TO DB		
		if (postDB){
				if ("daftarBaru".equals(hitButton)){
					idFail = logic.daftarBaru(getParam("socKaitanTujuan"), getParam("socJenisTujuan"), getParam("socJenisLesen"), getParam("txtTujuanPengambilan"), getParam("socTempoh"),
							getParam("txtRingkasanPengalaman"), getParam("socNegeri"), getParam("txtLokasi"), getParam("txtLuas"), 
							getParam("socLuas"),getParam("socJenisPengenalanIndividu"),getParam("socKategoriPemohon"),
							getParam("socIndividuBukanIndividu"), getParam("socJantina"), getParam("socBangsa"), getParam("socBandar"),
							getParam("socNegeriSykt"), getParam("socBandarSykt"), getParam("idJenisPermohonan"), getParam("idPermohonanLama") ,session);
	        	}
				if ("daftarBaruLesen".equals(hitButton)){
					
					idFail = logic.daftarBaru(getParam("socKaitanTujuan"),getParam("socJenisTujuan"), getParam("socJenisLesen"), getParam("txtTujuanPengambilan"), getParam("socTempoh"),
							getParam("txtRingkasanPengalaman"), getParam("socNegeri"), getParam("txtLokasi"), getParam("txtLuas"), 
							getParam("socLuas"),getParam("socJenisPengenalanIndividu"),getParam("socKategoriPemohon"),
							getParam("socIndividuBukanIndividu"), getParam("socJantina"), getParam("socBangsa"), getParam("socBandar"),
							getParam("socNegeriSykt"), getParam("socBandarSykt"), getParam("idJenisPermohonan"), getParam("idPermohonanLama"), session);
	        	}
				if ("daftarBaruBorangA".equals(hitButton)){ //yati tambah borang A
					
					idFail = logic.daftarBaru(getParam("socKaitanTujuan"),getParam("socJenisTujuan"), getParam("socJenisLesen"), getParam("txtTujuanPengambilan"), getParam("socTempoh"),
							getParam("txtRingkasanPengalaman"), getParam("socNegeri"), getParam("txtLokasi"), getParam("txtLuas"), 
							getParam("socLuas"),getParam("socJenisPengenalanIndividu"),getParam("socKategoriPemohon"),
							getParam("socIndividuBukanIndividu"), getParam("socJantina"), getParam("socBangsa"), getParam("socBandar"),
							getParam("socNegeriSykt"), getParam("socBandarSykt"), getParam("idJenisPermohonan"), getParam("idPermohonanLama"), session);
	        	}
				if ("doSimpanKemaskiniPermohonan".equals(hitButton)){
					logic.updatePermohonan(idFail,idPermohonan,idPemohon,getParam("socKaitanTujuan"), getParam("txtTujuanPengambilan"), getParam("socTempoh"), 
							getParam("txtRingkasanPengalaman"), getParam("socNegeri"), getParam("txtLokasi"), getParam("txtLuas"), 
							getParam("socLuas"),getParam("txtModalBenar"),getParam("txtModalJelas"), getParam("socJenisTujuan"), getParam("idJenisPermohonan"), getParam("idJenisLesen"), session);
	        	}
				if ("doSimpanPengarah".equals(hitButton)){
	        		idPengarah = logic.savePengarah(idPemohon, getParam("socWarganegara"), getParam("txtNamaPengarah"), getParam("socJenisPengenalan"),
	        				getParam("socBangsa"), getParam("txtNoPengenalan"), getParam("txtSaham"), session);
		
	        	}
	        	if ("doSimpanKemaskiniPengarah".equals(hitButton)){
	        		logic.updatePengarah(idPengarah, getParam("socWarganegara"), getParam("txtNamaPengarah"), getParam("socJenisPengenalan"),
	        				getParam("socBangsa"), getParam("txtNoPengenalan"), getParam("txtSaham"), session);
	        	}
	        	if ("doHapusPengarah".equals(hitButton)){
	        		logic.removePengarah(idPengarah);
	        	}
	        	if ("doSimpanPembeliPasir".equals(hitButton)){
	        		idPembeliPasir = logic.savePembeliPasir(idPermohonan, getParam("txtNamaPembeliPasir"), getParam("txtAlamat1PembeliPasir"), getParam("txtAlamat2PembeliPasir"),
	        				getParam("txtAlamat3PembeliPasir"), getParam("txtPoskodPembeliPasir"), getParam("socNegeriPembeliPasir"), getParam("socBandarPembeliPasir"), getParam("txtNoTelPembeliPasir"),
	        				getParam("txtNoFaxPembeliPasir"), getParam("socJenisPerjanjian"), session);
	        	}
	        	if ("doSimpanKemaskiniPembeliPasir".equals(hitButton)){
	        		logic.updatePembeliPasir(idPembeliPasir, getParam("txtNamaPembeliPasir"), getParam("txtAlamat1PembeliPasir"), getParam("txtAlamat2PembeliPasir"),
	        				getParam("txtAlamat3PembeliPasir"), getParam("txtPoskodPembeliPasir"), getParam("socNegeriPembeliPasir"), getParam("socBandarPembeliPasir"), getParam("txtNoTelPembeliPasir"),
	        				getParam("txtNoFaxPembeliPasir"), getParam("socJenisPerjanjian"), session);
	        	}
	        	if ("doHapusPembeliPasir".equals(hitButton)){
	        		logic.removePembeliPasir(idPembeliPasir);
	        	}        	
	        	if ("doSimpanProjek".equals(hitButton)){
	        		logic.saveProjek(idPermohonan, getParam("txtNamaProjek"), session);	
	        	}
	        	if ("doSimpanKemaskiniProjek".equals(hitButton)){
	        		logic.updateProjek(idProjek, getParam("txtNamaProjek"), session);
	        	}
	        	if ("doHapusProjek".equals(hitButton)){
	        		logic.removeProjek(idProjek);
	        	}
	        	if ("doSimpanPakar".equals(hitButton)){
	        		idPakar = logic.savePakar(idPermohonan, getParam("txtNamaPakar"), getParam("txtKelayakan"), session);
	        	}
	        	if ("doSimpanKemaskiniPakar".equals(hitButton)){
	        		logic.updatePakar(idPakar, getParam("txtNamaPakar"), getParam("txtKelayakan"), session);
	        	}
	        	if ("doHapusPakar".equals(hitButton)){
	        		logic.removePakar(idPakar);
	        	}        	
	        	if ("doSimpanKoordinat".equals(hitButton)){
	        		idKoordinat = logic.saveKoordinat(idPermohonan, getParam("txtLabelTitik"), getParam("txtDarjahU"), 
	        				getParam("txtMinitU"), getParam("txtSaatU"), getParam("txtDarjahT"), getParam("txtMinitT"), getParam("txtSaatT"), session);
	        	}
	        	if ("doSimpanKemaskiniKoordinat".equals(hitButton)){
	        		logic.updateKoordinat(idKoordinat, getParam("txtLabelTitik"), getParam("txtDarjahU"), 
	        				getParam("txtMinitU"), getParam("txtSaatU"), getParam("txtDarjahT"), getParam("txtMinitT"), getParam("txtSaatT"), session);
	        	}
	        	if ("doHapusKoordinat".equals(hitButton)){
	        		logic.removeKoordinat(idKoordinat);
	        	}
	        	if ("doHantarEmel".equals(hitButton)){
	        		if (logic.checkMaklumatAPBLengkap(idPermohonan)){
	        			logic.updatePermohonanEmel(idFail,idPermohonan,session);
	        		} else {
	        			this.context.put("onload","\"alert('Masih terdapat maklumat permohonan yang belum lengkap.')\"");
	        		}
				}
				if ("doHapus".equals(hitButton)){
					logic.hapusPermohonan(idFail);
				}
				
				//BARU TAMBAH
				//SENARAI SEMAK		
				if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
	        		String cbsemaks [] = this.request.getParameterValues("idsSenaraiSemak");
//	    			logic.updateSenaraiSemak(idPermohonan,semaks,session);
	    				
	        		//String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	    			FrmSemakan frmSemak = new FrmSemakan();
	    			frmSemak.semakanHapusByPermohonan(idPermohonan);
	    			if (cbsemaks != null) {
	    				for (int i = 0; i < cbsemaks.length; i++) {
	    					FrmSemakan.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
	    				}
	    			}
	    				
	        	}
				
				//LAMPIRAN
				if("doSimpanSenaraiSemak".equals(hitButton)){
					logic.simpanKemaskiniLampiran(idDokumen, getParam("txtNamaLampiran"), getParam("txtCatatanLampiran"), session);
				}
				
				if ("simpanKemaskiniLampiran".equals(hitButton)) {
					logic.simpanKemaskiniLampiran(idDokumen, getParam("txtNamaLampiran"), getParam("txtCatatanLampiran"), session);
				}
				if ("hapusLampiran".equals(hitButton)) {
					logic.hapusLampiran(idDokumen, session);
				}
				// HITBUTTON FOR POPUP MAKLUMAT KJPKJT
		
					if ("simpanMaklumatAmbilPasir".equals(hitButton)) {

					//	idJadualKedua = getParam("idJadualKedua");
						//Log.info("idJadualKeduaxx :"+idJadualKedua);
						idBorangA = logic.simpanMaklumatAmbilPasir(getParam("idJadualKedua"),
								idBulan, getParam("txtTahun"), getParam("txtTujuanAmbil"),
								getParam("txtDestinasiDihantar"),
								getParam("txtAnggaranPasir"), Utils.RemoveSymbol(getParam("txtJumlahRoyalti")), getParam("txtKontraktor"),
								getParam("txtPembeli"),getParam("txtTarikhMula"),getParam("txtTarikhTamat"),getParam("txtLaluan"),
								getParam("txtKaedah"),getParam("txtKawasan"),
								session);
					}

					if ("simpanKemaskiniMaklumatPasir".equals(hitButton)) {

						logic.simpanKemaskiniMaklumatPasir(idBorangA,
								idBulan, getParam("txtTahun"), getParam("txtTujuanAmbil"),
								getParam("txtDestinasiDihantar"),
								getParam("txtAnggaranPasir"), Utils.RemoveSymbol(getParam("txtJumlahRoyalti")), getParam("txtKontraktor"),
								getParam("txtPembeli"),getParam("txtTarikhMula"),getParam("txtTarikhTamat"),getParam("txtLaluan"),
								getParam("txtKaedah"),getParam("txtKawasan"),
								session);

					}
					if ("simpanMaklumatBarge".equals(hitButton)) {

						idBarge = logic.simpanMaklumatBarge(idBorangA,
								getParam("txtNamaDaftar"),
								getParam("txtNoPendaftaran"), getParam("txtKapasiti"), getParam("txtJenis"), getParam("txtNoTel"), 
								session);
					}
					if ("simpanKemaskiniMaklumatBarge".equals(hitButton)) {

						logic.simpanKemaskiniMaklumatBarge(idBarge,
								getParam("txtNamaDaftar"),
								getParam("txtNoPendaftaran"), getParam("txtKapasiti"), getParam("txtJenis"), getParam("txtNoTel"), 
								session);
					}
					if ("simpanLaporan".equals(hitButton)) {

						idBorangB = logic.simpanLaporan(id_user,
								getParam("idJadualKeduaLesen"),
								getParam("txtJumKuantiti"), getParam("txtJumRoyalti"),getParam("socBulan"),getParam("txtTahun"));

					}
					if ("simpanKemaskiniMaklumatLaporan".equals(hitButton)) {

						logic.simpanLaporan(id_user,
								getParam("idJadualKeduaLesen"),
								getParam("txtJumKuantiti"), getParam("txtJumRoyalti"),getParam("socBulan"),getParam("txtTahun"));


					}
				
					
		}
		
	//	this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session));
		//daftar Baru
		if ("daftarBaru".equals(actionOnline)){
		log.info("masuk daftar Baru");
		vm = "app/php2/online/frmAPBDaftarManual.jsp";
		mode = "new";
       	this.context.put("mode", "new");
    	this.context.put("readonly", "");
    	this.context.put("inputTextClass", "");
    	this.context.put("disabled", "");
    	this.context.put("jenisPermohonan", "PERMOHONAN BARU");
    	this.context.put("idJenisPermohonan", "1");
    	this.context.put("idJenisLesen", "idJenisLesen");
    	this.context.put("idStatus", "");
    	this.context.put("noFailLama", "");
    
    	//JENIS PERMOHONAN
		//this.context.put("selectJenisPermohonan", HTML.SelectJenisPermohonanAPB("socJenisPermohonan", Long.parseLong(idJenisPermohonan), "", " onChange=\"doChangeJenisPermohonan();\""));
    	
		String noFailLama = getParam("txtNoFailLama");
		beanMaklumatPermohonan = new Vector();
		idFailLama = logic.getIdFailByNoFail(noFailLama);
		
		if ("doChangeNoFailAPB".equals(submit)){
			
			beanMaklumatPermohonan = new Vector();   				
			logic.setMaklumatPermohonan(idFailLama);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			beanMaklumatKawasanMohon = new Vector();
			logic.setBeanMaklumatKawasanMohon(idFailLama);
			beanMaklumatKawasanMohon = logic.getBeanMaklumatKawasanMohon();
			this.context.put("BeanMaklumatKawasanMohon", beanMaklumatKawasanMohon);
			if(logic.getBeanMaklumatKawasanMohon().size() != 0){
				Hashtable hashKwsnMhn = (Hashtable) logic.getBeanMaklumatKawasanMohon().get(0);
				idFlagLuar = (String) hashKwsnMhn.get("idFlagLuar");
				idNegeriPerairan = (String) hashKwsnMhn.get("idNegeriPerairan");
				idJenisLesen = (String) hashKwsnMhn.get("idJenisLesen");
				idJenistujuan = (String) hashKwsnMhn.get("idJenistujuan");
			}
			
			//KAWASAN PERMOHONAN
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " class=\"disabled\""));
			this.context.put("selectNegeriPerairan",HTML.SelectNegeri("socNegeriPerairan",Long.parseLong(idNegeriPerairan),"disabled", " class=\"disabled\""));
			this.context.put("selectJenisLesen", HTML.SelectJenisLesen("socJenisLesen", Long.parseLong(idJenisLesen), "disabled", " class=\"disabled\""));
			this.context.put("selectJenisTujuan", PHPUtilHTML.SelectJenisTujuanAPB("socJenisTujuan", Long.parseLong(idJenistujuan), "disabled", " class=\"disabled\"")); //yati tambah
	}
		
    	//MAKLUMAT PEMOHON
		Vector<Hashtable<String,String>> vec = header.setMaklumatPemohon(id_user);
		this.context.put("pemohon", vec.get(0));
    	
    	//MAKLUMAT PERMOHONAN
    	this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"", " "));
		this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"", " "));
		
		//KAWASAN PERMOHONAN
		this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"", " style=\"width:100px\""));
		this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"", " style=\"width:250px\""));
		
		beanMaklumatPermohonan = new Vector();
		Hashtable hashMaklumatPermohonan = new Hashtable();
		hashMaklumatPermohonan.put("noFail","");
		hashMaklumatPermohonan.put("idPermohonan","");
		hashMaklumatPermohonan.put("noPermohonan","");
		hashMaklumatPermohonan.put("tarikhTerima", getParam("tarikhTerima"));	
		hashMaklumatPermohonan.put("tarikhSurat", getParam("tarikhSurat"));	
		hashMaklumatPermohonan.put("perkara", getParam("txtPerkara"));	
		hashMaklumatPermohonan.put("tujuanPengambilan", getParam("txtTujuanPengambilan"));	
		hashMaklumatPermohonan.put("tempoh", getParam("txtTempoh"));
		hashMaklumatPermohonan.put("pengalaman", getParam("txtPengalaman"));
		hashMaklumatPermohonan.put("lokasi", getParam("txtLokasi"));
		hashMaklumatPermohonan.put("luas", getParam("txtLuas"));
		beanMaklumatPermohonan.addElement(hashMaklumatPermohonan);
		this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);	
		
		//JENIS LESEN
		this.context.put("selectJenisLesen", HTML.SelectJenisLesen("socJenisLesen", Long.parseLong(idJenisLesen), "", "onChange=\"doChangeJenisLesen();\""));
		
		this.context.put("selectJenisTujuan", PHPUtilHTML.SelectJenisTujuanAPB("socJenisTujuan", Long.parseLong(idJenistujuan), "", "onChange=\"doChangeJenisTujuan();\""));

		}
		else if ("daftarBaruLesen".equals(actionOnline)){
		log.info("daftar Baru Lesen");
    	idPermohonan = getParam("idPermohonan");
		beanMaklumatPermohonan = new Vector();
		idPermohonan = logic.getIdPermohonanByNoFail(idFail);
		this.context.put("idPermohonan", idPermohonan);
    	
    	idPermohonanLama = getParam("idPermohonanLama");
		beanMaklumatPermohonan = new Vector();
		idPermohonanLama = logic.getIdPermohonanByNoFail(idFail);
    	this.context.put("idPermohonanLama", idPermohonanLama);

        vm = "app/php2/online/frmAPBDaftarManual.jsp";
        	
    	mode = "new";
       	this.context.put("mode", "new");
    	this.context.put("readonly", "");
    	this.context.put("inputTextClass", "");
    	this.context.put("disabled", "");
    	this.context.put("idJenisPermohonan", "2");
    	this.context.put("idFail", idFail);
    	  	
		String noFailLama = getParam("txtNoFailLama");
		String noPermohonan = getParam("txtNoPermohonan");
		//String noFailOnline = getParam();
		beanMaklumatPermohonan = new Vector();
		idFailLama = logic.getIdFailByNoFail(noFailLama);   
		noFailLama = logic.getNoFail(idFail);
		Log.info("noFailLama : "+noFailLama);
		if(noFailLama != "") {
		context.put("noFailLama", noFailLama);
		}
		noPermohonan = logic.getNoFailPermohonan(idFail);
		context.put("noPermohonan", noPermohonan);
		
		if ("doChangeNoFailAPB".equals(submit)){
			
			beanMaklumatPermohonan = new Vector();   				
			logic.setMaklumatPermohonan(idFailLama);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			beanMaklumatKawasanMohon = new Vector();
			logic.setBeanMaklumatKawasanMohon(idFailLama);
			beanMaklumatKawasanMohon = logic.getBeanMaklumatKawasanMohon();
			this.context.put("BeanMaklumatKawasanMohon", beanMaklumatKawasanMohon);
			if(logic.getBeanMaklumatKawasanMohon().size() != 0){
				Hashtable hashKwsnMhn = (Hashtable) logic.getBeanMaklumatKawasanMohon().get(0);
				idFlagLuar = (String) hashKwsnMhn.get("idFlagLuar");
				idNegeriPerairan = (String) hashKwsnMhn.get("idNegeriPerairan");
				idJenisLesen = (String) hashKwsnMhn.get("idJenisLesen");
			}
			
			//KAWASAN PERMOHONAN
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " class=\"disabled\""));
			this.context.put("selectNegeriPerairan",HTML.SelectNegeri("socNegeriPerairan",Long.parseLong(idNegeriPerairan),"disabled", " class=\"disabled\""));
			this.context.put("selectJenisLesen", HTML.SelectJenisLesen("socJenisLesen", Long.parseLong(idJenisLesen), "disabled", " class=\"disabled\""));
	}
		
    	//MAKLUMAT PEMOHON
		Vector<Hashtable<String,String>> vec = header.setMaklumatPemohon(id_user);
		this.context.put("pemohon", vec.get(0));
    	
    	//MAKLUMAT PERMOHONAN
    	this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"", " "));
		this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"", " "));
		
		this.context.put("selectJenisTujuan",PHPUtilHTML.SelectJenisTujuanAPB("socJenisTujuan", Long.parseLong(idJenistujuan),"", " "));
		
		//KAWASAN PERMOHONAN
		this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"", " style=\"width:100px\""));
		this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"", " style=\"width:250px\""));
		
		beanMaklumatPermohonan = new Vector();
		Hashtable hashMaklumatPermohonan = new Hashtable();
		hashMaklumatPermohonan.put("noFail","");
		hashMaklumatPermohonan.put("idPermohonan","");
		hashMaklumatPermohonan.put("noPermohonan","");
		hashMaklumatPermohonan.put("tarikhTerima", getParam("tarikhTerima"));	
		hashMaklumatPermohonan.put("tarikhSurat", getParam("tarikhSurat"));	
		hashMaklumatPermohonan.put("perkara", getParam("txtPerkara"));	
		hashMaklumatPermohonan.put("tujuanPengambilan", getParam("txtTujuanPengambilan"));	
		hashMaklumatPermohonan.put("tempoh", getParam("txtTempoh"));
		hashMaklumatPermohonan.put("pengalaman", getParam("txtPengalaman"));
		hashMaklumatPermohonan.put("lokasi", getParam("txtLokasi"));
		hashMaklumatPermohonan.put("luas", getParam("txtLuas"));
		beanMaklumatPermohonan.addElement(hashMaklumatPermohonan);
		this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);	

		//JENIS LESEN
		this.context.put("selectJenisLesen", HTML.SelectJenisLesen("socJenisLesen", Long.parseLong(idJenisLesen), "", "onChange=\"doChangeJenisLesen();\""));
	}		 
		else if ("daftarBaruBorangA".equals(actionOnline)){
			
			log.info("daftar Baru Borang A");
			vm = "app/php2/online/frmAPBBorangAOnlineSenaraiBorang.jsp";
			
			log.info("namaPemohon"+namaPemohon);
			log.info("noLesen"+noLesen);
			logic.carianFailBorangA(namaPemohon,noLesen, idJadualKeduaLesen);
			list = new Vector();
			list = logic.getSenaraiFailBorangA();

			this.context.put("SenaraiFailBorangA", list);

			log.info("SenaraiFailBorangA : "+list);
			this.context.put("txtnamaPemohon", namaPemohon);
			this.context.put("txtNoLesen", noLesen);

			setupPage(session, action, list);

	}
		
		else if ("daftarBaruBorangB".equals(actionOnline)){
			
			log.info("daftar Baru Borang B");
			vm = "app/php2/online/frmAPBBorangBOnline.jsp";

			log.info("namaPemohon"+namaPemohon);
			log.info("noLesen"+noLesen);
			logic.carianFailBorangB(namaPemohon,noLesen, idJadualKeduaLesen);
			list = new Vector();
			list = logic.getSenaraiFailBorangB();

			this.context.put("SenaraiFailBorangB", list);

			log.info("SenaraiFailBorangB : "+list);
			this.context.put("txtnamaPemohon", namaPemohon);
			this.context.put("txtNoLesen", noLesen);
			
			setupPage(session, action, list);

	}
        else if ("seterusnya".equals(actionOnline)){
        	
        	log.info("masuk here");
        	
        	//GO TO MAKLUMAT PERMOHONAN  
       		vm = "app/php2/online/frmAPBMaklumatPermohonan.jsp";
       		       		
        	//HEADER
            beanHeader = new Vector();
            logic.setMaklumatHeader(idFail);
            beanHeader = logic.getBeanMaklumatHeader();
    		this.context.put("BeanHeader", beanHeader);
    		log.info("BeanHeader : "+beanHeader);
    	
    		Vector<Hashtable<String,String>> vec = header.setMaklumatPemohon(id_user);
			this.context.put("pemohon", vec.get(0));
    		
    		if (beanHeader.size() != 0){
    			Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatHeader().get(0);
    			idFail = (String) hashHeader.get("idFail");
    			idPermohonan = (String) hashHeader.get("idPermohonan");
    		
    			this.context.put("idPermohonan", idPermohonan);
    			idStatus = (String) hashHeader.get("idStatus");
    			idPemohon = (String)hashHeader.get("idPemohon");	
    		}

	    		if ("0".equals(selectedTabUpper)){
	    			
	    			maklumatPermohonan(mode, idPermohonan, session);			
	    			maklumatProjek(mode, idPermohonan, idProjek);
	    			maklumatPakar(mode, idPermohonan, idPakar);
	    			maklumatKoordinat(mode, idPermohonan, idKoordinat);
	    			maklumatPengarah(mode, idPemohon, idPengarah);
	    			
	    			///SENARAI PROJEK
	    			logic.setSenaraiProjek(idPermohonan);
	    			senaraiProjek = logic.getListProjek();
	    			this.context.put("SenaraiProjek", senaraiProjek);
	        		
	        		//SENARAI TITIK KOORDINAT
	    			logic.setSenaraiKoordinat(idPermohonan);
	    			senaraiKoordinat = logic.getListKoordinat();
	    			this.context.put("SenaraiKoordinat", senaraiKoordinat);
	        		
	        		//SENARAI TITIK KOORDINAT
	    			logic.setSenaraiPakar(idPermohonan);
	    			senaraiPakar = logic.getListPakar();
	    			this.context.put("SenaraiPakar", senaraiPakar);
	    			
	    			//SENARAI PENGARAH
	        		logic.setSenaraiPengarah(idPemohon);
	        		senaraiPengarah = logic.getListPengarah();
	        		this.context.put("SenaraiPengarah", senaraiPengarah);
	        		this.context.put("completed", false);
	        		this.context.put("flagPopup", "closePopupLampiran");
	        		
	            } else if ("1".equals(selectedTabUpper)){

	            	this.context.put("completed", false);
	        		this.context.put("flagPopup", "closePopupLampiran");
	            	
	            	maklumatPembeliPasir(mode, idPembeliPasir, idPermohonan);
	            	
	            	//SENARAI PEMBELI PASIR
	        		logic.setSenaraiPembeliPasir(idPermohonan);
	        		senaraiPembeliPasir = logic.getListPembeliPasir();
	        		this.context.put("SenaraiPembeliPasir", senaraiPembeliPasir);
	        		
	            }	else if ("2".equals(selectedTabUpper)){

	            	this.context.put("completed", false);
	        		this.context.put("flagPopup", "closePopupLampiran");
					semak = new FrmSemakan();
					semak.mode = mode;
					senaraiSemak = semak.getSenaraiSemakanAttach("phpapb",idPermohonan);
	            	
	            	//senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
	    			this.context.put("SenaraiSemak", senaraiSemak);
	    			
	    			if (mode.equals("update")){
	    				
	        			//senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
		    			//this.context.put("SenaraiSemak", senaraiSemak);
	    			}
	    			else if (mode.equals("view")){
	        			
	        			//senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
		    			//this.context.put("SenaraiSemak", senaraiSemak);
		    			
	        		}
	    			
	            } 	    		
	           else if ("4".equals(selectedTabUpper)){
	           
				logic = new FrmAPBOnlineSenaraiFailData();
				Vector<Hashtable<String,String>> vec1 = logic.setMaklumatPejabatJKPTG();
				this.context.put("maklumatPejabat", vec1.get(0));
	           }
	    		this.context.put("idFail", idFail);
	    		this.context.put("idStatus", idStatus);
	    		this.context.put("idPermohonan", idPermohonan);
	    		this.context.put("namaPemohon",namaPemohon);
	    		this.context.put("noLesen",noLesen);
	    		this.context.put("idJadualKeduaLesen",idJadualKeduaLesen);
	   	
			
        } 
        	else if ("doMaklumatPasir".equals(actionOnline)) {
			
		
			Vector beanMaklumatAmbilPasir = null;
		
			idJadualKeduaLesen = getParam("idJadualKeduaLesen");
			idBorangA = getParam("idBorangA");
			// HEADER
			beanHeaderBorangA = new Vector();
			log.info("jadual kedua :"+idJadualKeduaLesen);
			logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
			beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
			
			this.context.put("beanHeaderBorangA", beanHeaderBorangA);
			
			log.info("beanHeaderBorangA : "+beanHeaderBorangA);
			
			vm = "app/php2/online/frmAPBBorangAOnlineMaklumatAmbilPasir.jsp";
			
			if("new".equals(mode)){

				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				beanMaklumatAmbilPasir = new Vector();
				 Hashtable hashMaklumatAmbilPasir = new Hashtable();
				 hashMaklumatAmbilPasir.put("tujuanAmbil",getParam("txtTujuanAmbil") == null ? "": getParam("txtTujuanAmbil"));
				 hashMaklumatAmbilPasir.put("destinasiHantar",getParam("txtDestinasiDihantar") == null ? "": getParam("txtDestinasiDihantar"));
				 hashMaklumatAmbilPasir.put("jumlahPasir",getParam("txtAnggaranPasir") == null ? "": getParam("txtAnggaranPasir"));
				 hashMaklumatAmbilPasir.put("jumlahRoyalti",getParam("txtJumlahRoyalti")== null ? "": getParam("txtJumlahRoyalti"));
				 hashMaklumatAmbilPasir.put("bulan",getParam("txtBulan") == null ?"": getParam("txtBulan"));
				 hashMaklumatAmbilPasir.put("tahun",getParam("txtTahun") == null ? "": getParam("txtTahun"));
				 
				 hashMaklumatAmbilPasir.put("kontraktor",getParam("txtKontraktor") == null ? "": getParam("txtKontraktor"));
				 hashMaklumatAmbilPasir.put("pembeli",getParam("txtPembeli") == null ? "": getParam("txtPembeli"));
				 hashMaklumatAmbilPasir.put("tarikhMula",getParam("txtTarikhMula") == null ? "": getParam("txtTarikhMula"));
				 hashMaklumatAmbilPasir.put("tarikhTamat",getParam("txtTarikhTamat")== null ? "": getParam("txtTarikhTamat"));
				 hashMaklumatAmbilPasir.put("laluan",getParam("txtLaluan") == null ?"": getParam("txtLaluan"));
				 hashMaklumatAmbilPasir.put("kaedah",getParam("txtKaedah") == null ? "": getParam("txtKaedah"));
				 hashMaklumatAmbilPasir.put("kawasan",getParam("txtKawasan") == null ? "": getParam("txtKawasan"));

				 beanMaklumatAmbilPasir.addElement(hashMaklumatAmbilPasir);
				
				 this.context.put("BeanMaklumatAmbilPasir",beanMaklumatAmbilPasir);
			
				 this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), ""));
				 
			}
			
			if ("view".equals(mode)) {
									
				 this.context.put("readonly", "readonly");
				 this.context.put("inputTextClass", "disabled");
				
				 beanMaklumatAmbilPasir = new Vector();
				 logic.setMaklumatAmbilPasir(idBorangA);
				 beanMaklumatAmbilPasir = logic.getBeanMaklumatAmbilPasir();
				 this.context.put("BeanMaklumatAmbilPasir",beanMaklumatAmbilPasir);		
				 
				 this.context.put("idBorangA", idBorangA);
				 log.info("idBorangA : "+idBorangA);
				 log.info("BEAN PASIR VIEWWWW : "+beanMaklumatAmbilPasir);
				 idJadualKeduaLesen = getParam("idJadualKeduaLesen");
					// HEADER
					beanHeaderBorangA = new Vector();
					log.info("jadual kedua :"+idJadualKeduaLesen);
					logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
					beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
					
					this.context.put("beanHeaderBorangA", beanHeaderBorangA);
				
				
				 
				 if (logic.getBeanMaklumatAmbilPasir().size() != 0){
					 Hashtable hashPasir = (Hashtable) logic.getBeanMaklumatAmbilPasir().get(0);
					 idBulan = (String) hashPasir.get("bulan");
					 }
				 
				 this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), "disabled", " class=\"disabled\""));
				 }
			
			if ("update".equals(mode)) {
				
				 this.context.put("readonly", "");
				 this.context.put("inputTextClass", "");
				
				 beanMaklumatAmbilPasir = new Vector();
				 logic.setMaklumatAmbilPasir(idBorangA);
				 
				 Hashtable hashMaklumatAmbilPasir = new Hashtable();
				 hashMaklumatAmbilPasir.put("tujuanAmbil",getParam("txtTujuanAmbil")== null ? "": getParam("txtTujuanAmbil"));
				 hashMaklumatAmbilPasir.put("destinasiHantar",getParam("txtDestinasiDihantar") == null ? "": getParam("txtDestinasiDihantar"));
				 hashMaklumatAmbilPasir.put("jumlahPasir",getParam("txtAnggaranPasir")== null ? "": getParam("txtAnggaranPasir"));
				 hashMaklumatAmbilPasir.put("jumlahRoyalti",getParam("txtJumlahRoyalti")== null ? "": getParam("txtJumlahRoyalti"));
				 hashMaklumatAmbilPasir.put("tahun",getParam("txtTahun") == null ? "": getParam("txtTahun"));
				 hashMaklumatAmbilPasir.put("kontraktor",getParam("txtKontraktor") == null ? "": getParam("txtKontraktor"));
				 hashMaklumatAmbilPasir.put("pembeli",getParam("txtPembeli") == null ? "": getParam("txtPembeli"));
				 hashMaklumatAmbilPasir.put("tarikhMula",getParam("txtTarikhMula") == null ? "": getParam("txtTarikhMula"));
				 hashMaklumatAmbilPasir.put("tarikhTamat",getParam("txtTarikhTamat")== null ? "": getParam("txtTarikhTamat"));
				 hashMaklumatAmbilPasir.put("laluan",getParam("txtLaluan") == null ?"": getParam("txtLaluan"));
				 hashMaklumatAmbilPasir.put("kaedah",getParam("txtKaedah") == null ? "": getParam("txtKaedah"));
				 hashMaklumatAmbilPasir.put("kawasan",getParam("txtKawasan") == null ? "": getParam("txtKawasan"));
				
				 beanMaklumatAmbilPasir.addElement(hashMaklumatAmbilPasir);
				 this.context.put("BeanMaklumatAmbilPasir",beanMaklumatAmbilPasir);
				 
				 this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), ""));
				 }
			
			if("newBarge".equals(mode)){
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				idJadualKeduaLesen = getParam("idJadualKedua");
				// HEADER
				beanHeaderBorangA = new Vector();
				log.info("jadual kedua :"+idJadualKeduaLesen);
				logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
				beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
				
				this.context.put("beanHeaderBorangA", beanHeaderBorangA);
			
				
				beanMaklumatBarge = new Vector();
				Hashtable hashMaklumatBarge = new Hashtable();
				hashMaklumatBarge.put("namaDidaftarkan",getParam("txtNamaDaftar") == null ? "": getParam("txtNamaDaftar"));
				hashMaklumatBarge.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
				hashMaklumatBarge.put("kapasiti",getParam("txtKapasiti") == null ? "": getParam("txtKapasiti"));
				
				hashMaklumatBarge.put("jenis",getParam("txtJenis") == null ? "": getParam("txtJenis"));
				hashMaklumatBarge.put("noTel",getParam("txtNoTel") == null ? "": getParam("txtNoTel"));

				beanMaklumatBarge.addElement(hashMaklumatBarge);
				
				 this.context.put("BeanMaklumatBarge",beanMaklumatBarge);
			}
			
	
			if ("viewBarge".equals(mode)) {
									
				 this.context.put("readonly", "readonly");
				 this.context.put("inputTextClass", "disabled");
				 

					idJadualKeduaLesen = getParam("idJadualKedua");
					// HEADER
					beanHeaderBorangA = new Vector();
					log.info("jadual kedua update :"+idJadualKeduaLesen);
					logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
					beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
					
					this.context.put("beanHeaderBorangA", beanHeaderBorangA);
									
				 beanMaklumatBarge = new Vector();
				 logic.setMaklumatBarge(idBarge);
				 beanMaklumatBarge = logic.getBeanMaklumatBarge();
				 
				 this.context.put("BeanMaklumatBarge",beanMaklumatBarge);
				
			}
				
				 if ("updateBarge".equals(mode)) {
					 

						idJadualKeduaLesen = getParam("idJadualKedua");
						// HEADER
						beanHeaderBorangA = new Vector();
						log.info("jadual kedua :"+idJadualKeduaLesen);
						logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
						beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
						
						this.context.put("beanHeaderBorangA", beanHeaderBorangA);
				
				 this.context.put("readonly", "");
				 this.context.put("inputTextClass", "");
				
				 logic.setMaklumatBarge(idBarge);
				 beanMaklumatBarge = new Vector();
				 Hashtable hashMaklumatBarge = new Hashtable();
				 hashMaklumatBarge.put("namaDidaftarkan",getParam("txtNamaDaftar")== null ? "": getParam("txtNamaDaftar"));
				 hashMaklumatBarge.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
				 hashMaklumatBarge.put("kapasiti",getParam("txtKapasiti") == null ? "": getParam("txtKapasiti"));
				 
				 hashMaklumatBarge.put("jenis",getParam("txtJenis") == null ? "": getParam("txtJenis"));
				 hashMaklumatBarge.put("noTel",getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
									
				 beanMaklumatBarge.addElement(hashMaklumatBarge);
				 this.context.put("BeanMaklumatBarge",beanMaklumatBarge);
				
			}
				 else {
		
			//LIST BARGE
			senaraiBarge = new Vector();
			senaraiBarge = logic.getSenaraiBarge();

			//logic.carianBarge(idBorangA);
			//senaraiBarge = logic.getSenaraiBarge();
			
			this.context.put("SenaraiBarge", senaraiBarge);
			setupPage2(session, action, senaraiBarge);
				 }
        	
			
		}
        	else if ("simpanLaporan".equals(actionOnline)) {
    			
        		idJadualKeduaLesen = getParam("idJadualKedua");
        		log.info("jadual kedua :"+idJadualKeduaLesen);
        		
				// HEADER
				beanHeaderBorangA = new Vector();
				logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
				beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
				
				this.context.put("beanHeaderBorangA", beanHeaderBorangA);
				
				String socBulan = getParam("socBulan");
	        	String txtTahun = getParam("txtTahun");
	        	boolean returnChecking = false;
	        	
	        	returnChecking = FrmAPBLaporanPasirData.isBulanExist(idJadualKeduaLesen,socBulan,txtTahun);
	        	log.info("returnChecking "+returnChecking);
	        	if(returnChecking == false){
	        		
	            	// INSERT TBLPHPLAPORANPASIR        	
	            	String result = "";
	            	result = simpanLaporan(id_user,idJadualKeduaLesen);
	            	
	            	context.put("id_laporanpasir",result);
	            	
	            	
	            	id_laporanpasir = result;
	            	log.info("id_laporanpasir :: "+id_laporanpasir);        	
	            	
	            	if(id_laporanpasir!=""){
	            		
	            		log.info("masuk sini tak?");
	            		// GET LATEST DATA LAPORAN
	            		logic.getMaklumatLaporan(id_laporanpasir);
	            		Vector getMaklumatLaporan = logic.getMaklumatLaporan();
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
//	                	context.put("returnChecking", false);
	                	
	                	// GET LIST PENGELUARAN PASIR LAUT
	                	//listPasir = modelLaporanPasir.getListPasir(id_laporanpasir);	
	            		//context.put("PermohonanPasir", listPasir);
	            		//context.put("list_size", listPasir.size());  	    		          		 		
	            		
	            		// PAGING
	            		setupPagePasir(session,action,listPasir);
	                	            	
	                	// SCREEN
	            		vm = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";
	            		
	            	}else{
	            		
	            		log.info("masuk else je");
	                	// VALUES JSP
	                	socBulan = "";
	            		context.put("socBulan", "");
	            			
	            		txtTahun = "";
	            		context.put("txtTahun", ""); 

	                   	// GET LIST DATA
	            		listLaporan = logic.getListLaporan(idJadualKeduaLesen);	
	            		listLaporan = new Vector();
	            		context.put("PermohonanList", listLaporan);
	            		context.put("list_size", listLaporan.size());  	    		          		 		
	            		
	            		// PAGING
	            		String SkrinListLaporan = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";
	            		setupPageLaporan(session,action,listLaporan,SkrinListLaporan);
	            		
	                	// SCREEN
	            		vm = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";
	                	
	            	}// CLOSE id_laporanpasir!=""
	            	
	        	}else{
	        		
	        		// VALIDATION
	            	context.put("button", "add");
	            	context.put("clearForm", "");
	            	context.put("flag", "");
	            	context.put("mode", ""); 
	        		context.put("returnChecking", true);
	        	
	        		// SCREEN
	            	vm = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";
   	
	        	}
        }
        	else if("daftarLaporan".equals(actionOnline)){
        	
        		idJadualKeduaLesen = getParam("idJadualKeduaLesen");
        		log.info("jadual kedua :"+idJadualKeduaLesen);
        		
				// HEADER
				beanHeaderBorangA = new Vector();
				logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
				beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
				
				this.context.put("beanHeaderBorangA", beanHeaderBorangA);
								
        	// DATA
        	context.put("selectBulan",HTML.SelectBulan( "socBulan","style=width:auto" ));
           	
        	// VALIDATION
        	context.put("button", "add");
        	context.put("clearForm", "yes");
        	context.put("flag", "");
        	context.put("mode", "");
        	
        	vm = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";
        
        }   
            	
		else {
		//default screen
		//GO TO LIST FAIL APB  
    	  	
    	vm = "app/php2/online/frmAPBSenaraiFail.jsp";
    	logic.carianFail(getParam("txtNoPermohonan"),getParam("txtNoFail"),getParam("txdTarikhTerima"), id_user);
		list = new Vector();
		list = logic.getSenaraiFail();
		this.context.put("SenaraiFail", list);
		this.context.put("txtNoFail", getParam("txtNoFail"));
		this.context.put("txtNoPermohonan", getParam("txtNoPermohonan"));
		this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		this.context.put("actionOnline", "");   

		setupPage(session,action,list); 
		}
        //SET DEFAULT PARAM
		this.context.put("actionOnline", actionOnline);
		this.context.put("hitButton", hitButton);
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		//this.context.put("idJenisPermohonan", idJenisPermohonan);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
		
		//Log.info("ID PERMOHONAN LAMA : "+idPermohonanLama);
		this.context.put("idPengarah", idPengarah);
		this.context.put("idPembeliPasir", idPembeliPasir);
	    this.context.put("idProjek", idProjek);
	    this.context.put("idPakar", idPakar);
	    this.context.put("idKoordinat", idKoordinat);
	    this.context.put("idPemohon", idPemohon);
	    this.context.put("idKategoriIndividu", idKategoriIndividu);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
		//this.context.put("idJenisPermohonan", idJenisPermohonan);
		this.context.put("idDokumen", idDokumen);

		log.info("vm : "+vm);
		return vm;
	}
	
	public void setupPage(HttpSession session,String action,Vector list) {
		
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
				this.context.put("SenaraiFail",paging.getPage(page));
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
private void maklumatPermohonan(String mode, String idPermohonan, HttpSession session) throws Exception, Exception {
		
		Vector beanMaklumatPermohonan = null;
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		
		//GET DROPDOWN PARAM
		String idKaitanTujuan = getParam("socKaitanTujuan");
		if (idKaitanTujuan == null || idKaitanTujuan.trim().length() == 0){
			idKaitanTujuan = "99999";
		}
		String idTempoh = getParam("socTempoh");
		if (idTempoh == null || idTempoh.trim().length() == 0){
			idTempoh = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idJenistujuan = getParam("socJenisTujuan");
		if (idJenistujuan == null || idJenistujuan.trim().length() == 0){		
			idJenistujuan = "99999";
		}
		String idJenisLesen = getParam("socJenisLesen");
		if (idJenisLesen == null || idJenisLesen.trim().length() == 0){
			idJenisLesen = "99999";
		}
		String idFlagLuar = "1";
		
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		
		if ("update".equals(mode)){
			
			this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	this.context.put("disabled", "");
        	
        	//MAKLUMAT PERMOHONAN
			this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"", " "));
			
			this.context.put("selectJenisTujuan",PHPUtilHTML.SelectJenisTujuanAPB("socJenisTujuan", Long.parseLong(idJenistujuan),"", " "));
			
			//KAWASAN PERMOHONAN
			this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"", " "));
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " style=\"width:100px\" class=\"disabled\""));
			this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"", " style=\"width:250px\""));
			
			beanMaklumatPermohonan = new Vector();
			Hashtable hashMaklumatPermohonan = new Hashtable();
			hashMaklumatPermohonan.put("tarikhTerima", getParam("tarikhTerima"));	
			hashMaklumatPermohonan.put("tarikhSurat", getParam("tarikhSurat"));	
			hashMaklumatPermohonan.put("perkara", getParam("txtPerkara"));	
			hashMaklumatPermohonan.put("tujuanPengambilan", getParam("txtTujuanPengambilan"));	
			hashMaklumatPermohonan.put("tempoh", getParam("socTempoh"));
			hashMaklumatPermohonan.put("pengalaman", getParam("txtRingkasanPengalaman"));
			hashMaklumatPermohonan.put("lokasi", getParam("txtLokasi"));
			hashMaklumatPermohonan.put("luas", getParam("txtLuas"));
			hashMaklumatPermohonan.put("modalSemasa", getParam("txtModalSemasa"));
			hashMaklumatPermohonan.put("modalSedia", getParam("txtModalSedia"));
			hashMaklumatPermohonan.put("modalBenar", getParam("txtModalBenar"));
			hashMaklumatPermohonan.put("modalJelas", getParam("txtModalJelas"));
			beanMaklumatPermohonan.addElement(hashMaklumatPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);		
		
		} else if ("view".equals(mode)){
			//log.info("masuk sini view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	this.context.put("disabled", "disabled");
			
        	//MAKLUMAT PEMOHON
        	header = new FrmAPBHeaderData();
			Vector<Hashtable<String,String>> vec = header.setMaklumatPemohon(id_user);
			this.context.put("pemohon", vec.get(0));
	
			//MAKLUMAT PERMOHONAN
			this.context.put("idPermohonan", "idPermohonan");
	
			logic.setMaklumatPermohonan(idPermohonan);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashMaklumatPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				
				idNegeri = (String) hashMaklumatPermohonan.get("idNegeri");
				idKaitanTujuan = (String) hashMaklumatPermohonan.get("idKaitanTujuan");
				idFlagLuar = (String) hashMaklumatPermohonan.get("idFlagLuar");
				idLuas = (String) hashMaklumatPermohonan.get("idLuas");
				idJenistujuan = (String) hashMaklumatPermohonan.get("idJenistujuan");
				idJenisLesen = (String) hashMaklumatPermohonan.get("idJenisLesen");
			}
			
			this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"disabled", " class=\"disabled\""));
			
			this.context.put("selectJenisTujuan",PHPUtilHTML.SelectJenisTujuanAPB("socJenisTujuan", Long.parseLong(idJenistujuan),"disabled", " class=\"disabled\""));
			
			this.context.put("selectJenisLesen",HTML.SelectJenisLesen("socJenisLesen", Long.parseLong(idJenisLesen),"disabled", " class=\"disabled\""));
			//log.info(" idJenisLesen :"+idJenisLesen);
			//KAWASAN PERMOHONAN
			this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " style=\"width:100px\" class=\"disabled\""));
			this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"disabled", " style=\"width:250px\" class=\"disabled\""));
		}		
	}

	private void maklumatPembeliPasir(String mode, String idPembeliPasir, String idPermohonan) throws Exception {
		
		Vector beanMaklumatPembeliPasir = null;
        
        //GET DROPDOWN PARAM
		String idNegeri = getParam("socNegeriPembeliPasir");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandarPembeliPasir");
		if (idBandar == null || idBandar.trim().length() == 0){
			idBandar = "99999";
		}
		String idJenisPerjanjian = getParam("socJenisPerjanjian");
		if (idJenisPerjanjian == null || idJenisPerjanjian.trim().length() == 0){
			idJenisPerjanjian = "99999";
		}
		
		if ("updatePembeliPasir".equals(mode)){
			
			this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PEMBELI PASIR
        	this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeriAPB("socNegeriPembeliPasir",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong(idBandar), ""));
			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong(idJenisPerjanjian), "", " "));
			
			beanMaklumatPembeliPasir = new Vector();
			Hashtable hashPembeliPasir = new Hashtable();
			hashPembeliPasir.put("nama", getParam("txtNamaPembeliPasir"));		
			hashPembeliPasir.put("alamat1", getParam("txtAlamat1PembeliPasir"));
			hashPembeliPasir.put("alamat2", getParam("txtAlamat2PembeliPasir"));
			hashPembeliPasir.put("alamat3", getParam("txtAlamat3PembeliPasir"));
			hashPembeliPasir.put("poskod", getParam("txtPoskodPembeliPasir"));
			hashPembeliPasir.put("noTel", getParam("txtNoTelPembeliPasir"));
			hashPembeliPasir.put("noFax", getParam("txtNoFaxPembeliPasir"));			
			beanMaklumatPembeliPasir.addElement(hashPembeliPasir);
			this.context.put("BeanMaklumatPembeliPasir", beanMaklumatPembeliPasir);		
		
		} else if ("viewPembeliPasir".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT PEMBELI PASIR
			logic.setMaklumatPembeliPasir(idPembeliPasir);
			beanMaklumatPembeliPasir = logic.getBeanMaklumatPembeliPasir();
			this.context.put("BeanMaklumatPembeliPasir", beanMaklumatPembeliPasir);
			
			if (logic.getBeanMaklumatPembeliPasir().size() != 0){
				Hashtable hashPembeliPasir = (Hashtable) logic.getBeanMaklumatPembeliPasir().get(0);
				
				idNegeri = (String) hashPembeliPasir.get("idNegeri");
				idBandar = (String) hashPembeliPasir.get("idBandar");
				idJenisPerjanjian = (String) hashPembeliPasir.get("idJenisPerjanjian");
			}
			
			this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeriAPB("socNegeriPembeliPasir",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong(idJenisPerjanjian), "disabled", " class=\"disabled\""));
			
		} else if ("newPembeliPasir".equals(mode)){
			
			String submit = getParam("command");   
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PEMBELI PASIR
        	this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeriAPB("socNegeriPembeliPasir",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong(idBandar), ""));
			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong(idJenisPerjanjian), "", " "));
			
			beanMaklumatPembeliPasir = new Vector();
			Hashtable hashPembeliPasir = new Hashtable();
        	
        	if (!"".equals(submit)){
            	
        		hashPembeliPasir.put("nama", getParam("txtNamaPembeliPasir"));		
				hashPembeliPasir.put("alamat1", getParam("txtAlamat1PembeliPasir"));
				hashPembeliPasir.put("alamat2", getParam("txtAlamat2PembeliPasir"));
				hashPembeliPasir.put("alamat3", getParam("txtAlamat3PembeliPasir"));
				hashPembeliPasir.put("poskod", getParam("txtPoskodPembeliPasir"));
				hashPembeliPasir.put("noTel", getParam("txtNoTelPembeliPasir"));
				hashPembeliPasir.put("noFax", getParam("txtNoFaxPembeliPasir"));	
        	}
        	else{
        		
        		hashPembeliPasir.put("nama", "");		
    			hashPembeliPasir.put("alamat1", "");
    			hashPembeliPasir.put("alamat2", "");
    			hashPembeliPasir.put("alamat3", "");
    			hashPembeliPasir.put("poskod", "");
    			hashPembeliPasir.put("noTel", "");
    			hashPembeliPasir.put("noFax", "");
    			this.context.put("selectNegeriPembeliPasir",HTML.SelectNegeriAPB("socNegeriPembeliPasir",Long.parseLong("99999"),"","onChange=\"doChangeNegeri();\""));
    			this.context.put("selectBandarPembeliPasir",HTML.SelectBandarByNegeri(idNegeri, "socBandarPembeliPasir", Long.parseLong("99999"), ""));
    			this.context.put("selectJenisPerjanjian",HTML.SelectPHPJenisPerjanjian("socJenisPerjanjian", Long.parseLong("99999"), "", " "));
        	}
        	
        	beanMaklumatPembeliPasir.addElement(hashPembeliPasir);
			this.context.put("BeanMaklumatPembeliPasir", beanMaklumatPembeliPasir);
		}
	}

	private void maklumatPengarah(String mode, String idPemohon, String idPengarah) throws Exception {

		Vector beanMaklumatPengarah = null;
        
        //GET DROPDOWN PARAM
		String idWarganegara = getParam("socWarganegara");
		if (idWarganegara == null || idWarganegara.trim().length() == 0){
			idWarganegara = "99999";
		}
		String idJenisPengenalan = getParam("socJenisPengenalan");
		if (idJenisPengenalan == null || idJenisPengenalan.trim().length() == 0){
			idJenisPengenalan = "99999";
		}
		String idBangsa = getParam("socBangsa");
		if (idBangsa == null || idBangsa.trim().length() == 0){
			idBangsa = "99999";
		}
		
		if ("updatePengarah".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PENGARAH
			this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong(idWarganegara), "", ""));
			this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong(idJenisPengenalan), "", ""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), "", ""));
		
		} else if ("viewPengarah".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT PENGARAH
			logic.setMaklumatPengarah(idPengarah);
			beanMaklumatPengarah = logic.getBeanMaklumatPengarah();
			this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);
			
			if (logic.getBeanMaklumatPengarah().size() != 0){
				Hashtable hashPengarah = (Hashtable) logic.getBeanMaklumatPengarah().get(0);
				
				idJenisPengenalan = (String) hashPengarah.get("idJenisPengenalan");
				idWarganegara = (String) hashPengarah.get("idWarganegara");
				idBangsa = (String) hashPengarah.get("idBangsa");
			}
			
			this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong(idWarganegara), "disabled", " class=\"disabled\""));
			this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong(idJenisPengenalan), "disabled", " class=\"disabled\""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), "disabled", " class=\"disabled\""));
			
		} else if ("newPengarah".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PENGARAH
			this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong("99999"), "", ""));
			this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong("99999"), "", ""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong("99999"), "", ""));
			
			beanMaklumatPengarah = new Vector();
			Hashtable hashPengarah = new Hashtable();
			hashPengarah.put("nama", "");
			hashPengarah.put("noPengenalan","");
			hashPengarah.put("saham","");					
			beanMaklumatPengarah.addElement(hashPengarah);
			this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);	
		}
	}
	
private void maklumatProjek(String mode, String idPermohonan, String idProjek) throws Exception {
		
		Vector beanMaklumatProjek = null;
		
		if ("updateProjek".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
		
		} else if ("viewProjek".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT PROJEK
			logic.setMaklumatProjek(idProjek);
			beanMaklumatProjek = logic.getBeanMaklumatProjek();
			this.context.put("BeanMaklumatProjek", beanMaklumatProjek);
			
		} else if ("newProjek".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PROJEK			
			beanMaklumatProjek = new Vector();
			Hashtable hashProjek = new Hashtable();
			hashProjek.put("namaProjek","");					
			beanMaklumatProjek.addElement(hashProjek);
			this.context.put("BeanMaklumatProjek", beanMaklumatProjek);			
		}
	}
	
	private void maklumatPakar(String mode, String idPermohonan, String idPakar) throws Exception {

		Vector beanMaklumatPakar = null;
		
		if ("updatePakar".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
		
		} else if ("viewPakar".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT Pakar
			logic.setMaklumatPakar(idPakar);
			beanMaklumatPakar = logic.getBeanMaklumatPakar();
			this.context.put("BeanMaklumatPakar", beanMaklumatPakar);
			
		} else if ("newPakar".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT Pakar			
			beanMaklumatPakar = new Vector();
			Hashtable hashPakar = new Hashtable();
			
			hashPakar.put("nama", "");	
			hashPakar.put("kelayakan", "");		
			beanMaklumatPakar.addElement(hashPakar);
			this.context.put("BeanMaklumatPakar", beanMaklumatPakar);
		}
	}
	
	private void maklumatKoordinat(String mode, String idPermohonan, String idKoordinat) throws Exception {

		Vector beanMaklumatKoordinat = null;
		
		if ("updateKoordinat".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
		
		} else if ("viewKoordinat".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
			
			//MAKLUMAT KOORDINAT
			logic.setMaklumatKoordinat(idKoordinat);
			beanMaklumatKoordinat = logic.getBeanMaklumatKoordinat();
			this.context.put("BeanMaklumatKoordinat", beanMaklumatKoordinat);
			
		} else if ("newKoordinat".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT Koordinat			
			beanMaklumatKoordinat = new Vector();
			Hashtable hashKoordinat = new Hashtable();
			hashKoordinat.put("labelTitik", "");	
			hashKoordinat.put("darjahU", "");	
			hashKoordinat.put("minitU", "");	
			hashKoordinat.put("saatU", "");	
			hashKoordinat.put("darjahT", "");	
			hashKoordinat.put("minitT", "");	
			hashKoordinat.put("saatT", "");	
			beanMaklumatKoordinat.addElement(hashKoordinat);
			this.context.put("BeanMaklumatKoordinat", beanMaklumatKoordinat);			
		}
	}
	//yati tambah
	private static String namaPemohon(String idFail) throws Exception{
	    
		Vector checkPemohon = null;
		FrmAPBOnlineSenaraiFailData logic = new FrmAPBOnlineSenaraiFailData();
		
		checkPemohon = logic.setnamaPemohon(idFail);

		String namaPemohonOnline = "";
		if(checkPemohon.size()!=0){
			Hashtable ceP = (Hashtable)checkPemohon.get(0);
			namaPemohonOnline = (String)ceP.get("NAMA_PEMOHON");
		}
	       return namaPemohonOnline;        	        		
	} 

	//yati tambah
		private static String noLesen(String idFail) throws Exception{
		    
			Vector checkLesen = null;
			FrmAPBOnlineSenaraiFailData logic = new FrmAPBOnlineSenaraiFailData();
			
			checkLesen = logic.setnoLesen(idFail);

			String noLesen = "";
			if(checkLesen.size()!=0){
				Hashtable ceP = (Hashtable)checkLesen.get(0);
				noLesen = (String)ceP.get("NO_SIRI_LESEN");
			}
		       return noLesen;        	        		
		} 
		
		//yati tambah
				private static String setIdJadualKeduaLesen(String idFail) throws Exception{
				    
					Vector checkJadualLesen = null;
					FrmAPBOnlineSenaraiFailData logic = new FrmAPBOnlineSenaraiFailData();
					
					checkJadualLesen = logic.setIdJadualKeduaLesen(idFail);

					String idJadualKeduaLesen = "";
					if(checkJadualLesen.size()!=0){
						Hashtable ceP = (Hashtable)checkJadualLesen.get(0);
						idJadualKeduaLesen = (String)ceP.get("idJadualKeduaLesen");//("NO_SIRI_LESEN");

					}
				       return idJadualKeduaLesen;        	        		
				} 
			//yati tambah	
				public void setupPage2(HttpSession session, String action, Vector list) {

					try {

						this.context.put("totalRecords", list.size());
						int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

						int itemsPerPage;
						if (this.context.get("itemsPerPage") == null
								|| this.context.get("itemsPerPage") == "") {
							itemsPerPage = getParam("itemsPerPage") == "" ? 10
									: getParamAsInteger("itemsPerPage");
						} else {
							itemsPerPage = (Integer) this.context.get("itemsPerPage");
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

						Paging paging = new Paging(session, list, itemsPerPage);

						if (page > paging.getTotalPages())
							page = 1; // reset page number
						this.context.put("SenaraiBarge", paging.getPage(page));
						this.context.put("page", new Integer(page));
						this.context.put("itemsPerPage", new Integer(itemsPerPage));
						this.context.put("totalPages", new Integer(paging.getTotalPages()));
						this.context.put("startNumber", new Integer(paging.getTopNumber()));
						this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
						this.context.put("isLastPage", new Boolean(paging.isLastPage()));

					} catch (Exception e) {
						e.printStackTrace();
						this.context.put("error", e.getMessage());
					}
				}
				
			
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
				

				// SIMPAN LAPORAN
				private String simpanLaporan(String id_user, String idJadualKeduaLesen) throws Exception {
					
					String txtJumKuantiti = getParam("txtJumKuantiti");
					String txtJumRoyalti = getParam("txtJumRoyalti");
					String socBulan = getParam("socBulan");
					String txtTahun = getParam("txtTahun");
					/*String txtKontraktor = getParam("txtKontraktor");*/
					/*String txtPembeli = getParam("txtPembeli");*/
					
					return logic.simpanLaporan(id_user,idJadualKeduaLesen,txtJumKuantiti,txtJumRoyalti,
							socBulan,txtTahun);		
					
				}// CLOSE SIMPAN LAPORAN
				
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
				

}
