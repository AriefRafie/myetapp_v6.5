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
//import ekptg.model.php2.online.frmAPBLaporanPasirTransaction;
import ekptg.model.php2.online.FrmAPBOnlineSenaraiFailData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.utils.lampiran.ILampiran;


public class FrmAPBOnlineSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView.class);
	private ILampiran iLampiran = null;
	
	FrmAPBOnlineSenaraiFailData logic = new FrmAPBOnlineSenaraiFailData();
	FrmAPBHeaderData header = new FrmAPBHeaderData();
	FrmSemakan semak = null;
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    String vm = ""; 
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
        String actionOnline = getParam("actionOnline");      
        String submit = getParam("command"); 
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
 
        String idFailSession = "";  
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        } 
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		
		//GET ID PARAM
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
        String idPermohonanLama = getParam("idPermohonanLama");
		String idPermohonan = getParam("idPermohonan");
		String idPengarah = getParam("idPengarah");
        String idPembeliPasir = getParam("idPembeliPasir");
        String idProjek = getParam("idProjek");
        String idPakar = getParam("idPakar");
        String idKoordinat = getParam("idKoordinat");
        String idPemohon = getParam("idPemohon");
        String idDokumen = getParam("idDokumen"); // ADD MAKLUMAT LAMPIRAN
        String kategori = getParam("kategori");
        String idLaporanPasir = getParam("idLaporanPasir");
        String id_laporanpasir = getParam("id_laporanpasir");
   		String id_jadualkedualesenAPB = getParam("id_jadualkedualesenAPB");
		String id_dokumen = getParam("id_dokumen");
		
		//MAKLUMAT BORANG A
		String idJadualKedua = getParam("idJadualKedua");
		String idBorangA = getParam("idBorangA");
		String idBarge = getParam("idBarge");
		String idBorangB = getParam("idBorangB");
        
   		context.put("id_laporanpasir", id_laporanpasir);
   		context.put("idPermohonan",idPermohonan);
   		context.put("id_jadualkedualesenAPB", id_jadualkedualesenAPB);
   		context.put("id_dokumen", id_dokumen);
		        
        //VECTOR
        Vector<Hashtable<String,String>> list = null;
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
        Vector beanPelesen = null;
        Vector beanMaklumatPejabat = null; //BARU TAMBAH
        Vector beanHeaderBorangA = null;
        Vector beanMaklumatBarge = null;
        Vector listBorangA = null;
        Vector senaraiBarge = null;
        Vector beanLaporan = null;
        Vector listPasir = null;
        Vector listLaporan = null;
        Vector listB = null;
        Vector beanMaklumatDokumen = null;//BARU TAMBAH
        Vector senaraiDokumenPasir = null;
      
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
		String idJenisPermohonan = getParam("socJenisPermohonan");
		if (idJenisPermohonan == null || idJenisPermohonan.trim().length() == 0){
			idJenisPermohonan = "99999";
		}
		String idFailLama = getParam("socNoFailLama"); //new ADD 
		if (idFailLama == null || idFailLama.trim().length() == 0) {
			idFailLama = "99999";
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
        String idBulan = getParam("socBulan");
		if (idBulan == null || idBulan.trim().length() == 0){
			idBulan = "99999";
		}
		String idBulanList = getParam("socBulanList");
		if (idBulanList == null || idBulanList.trim().length() == 0){
			idBulanList = "99999";
		}
		String idWarganegara = getParam("socWarganegara");
		if (idWarganegara == null || idWarganegara.trim().length() == 0){
			idWarganegara = "99999";
		}
		String idBangsa = getParam("socBangsa");
		if (idBangsa == null || idBangsa.trim().length() == 0){
			idBangsa = "99999";
		}
      
		this.context.put("onload", "sembunyikanLainLainNegara('LainLainNegara','LainLainBangsa','JenisPengenalan');");
		
		String namaPemohon = "";
     	namaPemohon =  FrmAPBOnlineSenaraiFailView.namaPemohon(idFail);
     	
     	String noLesen = "";
     	noLesen =  FrmAPBOnlineSenaraiFailView.noLesen(idFail);
    
     	String idJadualKeduaLesen = "";
     	idJadualKeduaLesen =  FrmAPBOnlineSenaraiFailView.setIdJadualKeduaLesen(idFail);
     	this.context.put("idJadualKeduaLesen",idJadualKeduaLesen);

		//SAVE TO DB		
		if (postDB){
			if ("daftarBaru".equals(hitButton)){
				if("1".equals(idJenisPermohonan)) {
					idFail = logic.daftarBaruLesen(idJenisPermohonan, idJenisLesen, getParam("socKaitanTujuan"), getParam("txtTujuanPengambilan"),
							getParam("socTempoh"), getParam("txtRingkasanPengalaman"), getParam("txtUndangUndang"), getParam("txtJenisPerniagaan"), 
							getParam("socFlagLuar"), idNegeriPerairan, getParam("txtLokasi"), getParam("txtLuas"), getParam("socLuas"), session);
				
				} else if("2".equals(idJenisPermohonan)) {
					
				}
	    	}
			if ("daftarBaruLesen".equals(hitButton)){
				idFail = logic.daftarBaru(getParam("socKaitanTujuan"),getParam("socJenisTujuan"), getParam("socJenisLesen"), getParam("txtTujuanPengambilan"), getParam("socTempoh"),
						getParam("txtRingkasanPengalaman"), getParam("socNegeri"), getParam("txtLokasi"), getParam("txtLuas"), getParam("socLuas"),
						getParam("socJenisPengenalanIndividu"),getParam("socKategoriPemohon"), getParam("socIndividuBukanIndividu"), getParam("socJantina"), 
						getParam("socBangsa"), getParam("socBandar"), getParam("socNegeriSykt"), getParam("socBandarSykt"), 
						idJenisPermohonan, getParam("idPermohonanLama"), getParam("txtUndangUndang"), getParam("socFlagLuar"), getParam("txtJenisPerniagaan"), session);
	    	}
			if ("daftarBaruBorangA".equals(hitButton)){ //yati tambah borang A
				
				idFail = logic.daftarBaru(getParam("socKaitanTujuan"),getParam("socJenisTujuan"), getParam("socJenisLesen"), getParam("txtTujuanPengambilan"), getParam("socTempoh"),
						getParam("txtRingkasanPengalaman"), getParam("socNegeri"), getParam("txtLokasi"), getParam("txtLuas"), 
						getParam("socLuas"),getParam("socJenisPengenalanIndividu"),getParam("socKategoriPemohon"),
						getParam("socIndividuBukanIndividu"), getParam("socJantina"), getParam("socBangsa"), getParam("socBandar"),
						getParam("socNegeriSykt"), getParam("socBandarSykt"), getParam("idJenisPermohonan"), 
						getParam("idPermohonanLama"), getParam("txtUndangUndang"), getParam("socFlagLuar"), getParam("txtJenisPerniagaan"), session);
	    	}
			if ("doSimpanKemaskiniPermohonan".equals(hitButton)){
				logic.updatePermohonan(idFail,idPermohonan,idPemohon,getParam("socKaitanTujuan"), getParam("txtTujuanPengambilan"), getParam("socTempoh"), 
						getParam("txtRingkasanPengalaman"), getParam("socNegeri"), getParam("txtLokasi"), getParam("txtLuas"), getParam("socLuas"),
						getParam("txtModalBenar"), getParam("txtModalJelas"), getParam("socJenisTujuan"), getParam("idJenisLesen"), 
						getParam("txtUndangUndang"), getParam("txtJenisPerniagaan"), getParam("txtJumlahModal"), getParam("txtJumlahModal1"), session);
	    	}
			if ("doSimpanPengarah".equals(hitButton)){
	    		idPengarah = logic.savePengarah(idPemohon, getParam("socWarganegara"), getParam("txtNamaPengarah"), getParam("socJenisPengenalan"),
	    				getParam("txtNoPengenalan"), getParam("socBangsa"), getParam("txtSaham"), getParam("txtWarga"), getParam("txtBangsa"), session);
	    	}
			if ("doSimpanKemaskiniPengarah".equals(hitButton)){
	    		logic.updatePengarah(idPengarah, getParam("socWarganegara"), getParam("txtNamaPengarah"), getParam("socJenisPengenalan"),
	    				getParam("socBangsa"), getParam("txtNoPengenalan"), getParam("txtSaham"), getParam("txtWarga"), getParam("txtBangsa"), session);
	    	}
			if ("doHapusPengarah".equals(hitButton)){
	    		logic.removePengarah(idPengarah);
	    	}
	    	if ("doSimpanPembeliPasir".equals(hitButton)){
	    		idPembeliPasir = logic.savePembeliPasir(idPermohonan, getParam("txtNamaPembeliPasir"), getParam("txtAlamat1PembeliPasir"), getParam("txtAlamat2PembeliPasir"),
	    				getParam("txtAlamat3PembeliPasir"), getParam("txtPoskodPembeliPasir"), getParam("socNegeriPembeliPasir"), getParam("socBandarPembeliPasir"), 
	    				getParam("txtNoTelPembeliPasir"), getParam("txtNoFaxPembeliPasir"), getParam("socJenisPerjanjian"), session);
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
	    			this.context.put("onload","\"alert('Masih terdapat maklumat permohonan yang belum lengkap.')\"");
	    		} else {
	    			logic.updatePermohonanEmel(idFail,idPermohonan,session);
	    		}
			}
	    	if ("doHapus".equals(hitButton)){
				logic.hapusPermohonan(idFail);
			}
			//SENARAI SEMAK		
			if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
	    		String cbsemaks [] = this.request.getParameterValues("idsSenaraiSemak");
					
				FrmSemakan frmSemak = new FrmSemakan();
				frmSemak.semakanHapusByPermohonan(idPermohonan);
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						FrmSemakan.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
					}
				}
					
	    	}
			if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(id_laporanpasir, idPermohonan, session);
			}
			if ("simpanKemaskiniLampiran".equals(hitButton)) {
				//uploadFilesUpdate(id_laporanpasir, idDokumen,session);
				logic.simpanKemaskiniLampiran(idDokumen, getParam("txtNamaDokumen"), getParam("txtCatatan"), session);
			}
			if ("hapusDokumen".equals(hitButton)) {
				logic.hapusLampiran(idDokumen, session);
			}
			if ("simpanMaklumatAmbilPasir".equals(hitButton)) {
//				idBorangA = logic.simpanMaklumatAmbilPasir(getParam("idJadualKedua"), idBulan, getParam("txtTahun"), getParam("txtTujuanAmbil"), 
//						getParam("txtDestinasiDihantar"), getParam("txtAnggaranPasir"), Utils.RemoveSymbol(getParam("txtJumlahRoyalti")), getParam("txtKontraktor"), getParam("txtPembeli"),
//						getParam("txtTarikhMula"), getParam("txtTarikhTamat"), getParam("txtLaluan"), 
//						getParam("txtKaedah"), getParam("txtKawasan"), session);
				idBorangA = logic.simpanMaklumatAmbilPasir(getParam("idJadualKedua"), idBulan, getParam("txtTahun"), getParam("txtTujuanAmbil"), 
						getParam("txtDestinasiDihantar"), getParam("txtAnggaranPasir"), Utils.RemoveSymbol(getParam("txtJumlahRoyalti")), getParam("txtKontraktor"), getParam("txtPembeli"),
						getParam("txtTarikhMula"), getParam("txtTarikhTamat"), getParam("txtLaluan"), 
						getParam("txtKaedah"), getParam("txtKawasan"), getParam("txtLabelTitik"), 
						getParam("txtDarjahT"), getParam("txtDarjahU"), getParam("txtMinitT"), getParam("txtMinitU"), getParam("txtSaatT"), getParam("txtSaatU"), session);
			}
			if ("simpanKemaskiniMaklumatPasir".equals(hitButton)) {
				log.info("idJadualKedua kemaskini :"+idJadualKedua);
//				logic.simpanKemaskiniMaklumatPasir(idBorangA, idBulan, getParam("txtTahun"), getParam("txtTujuanAmbil"), getParam("txtDestinasiDihantar"),
//						getParam("txtAnggaranPasir"), Utils.RemoveSymbol(getParam("txtJumlahRoyalti")), getParam("txtKontraktor"), getParam("txtPembeli"), 
//						getParam("txtTarikhMula"), getParam("txtTarikhTamat"), getParam("txtLaluan"), getParam("txtKaedah"), getParam("txtKawasan"), session);
				logic.simpanKemaskiniMaklumatPasir(idBorangA, idBulan, getParam("txtTahun"), getParam("txtTujuanAmbil"), getParam("txtDestinasiDihantar"),
						getParam("txtAnggaranPasir"), Utils.RemoveSymbol(getParam("txtJumlahRoyalti")), getParam("txtKontraktor"), getParam("txtPembeli"), 
						getParam("txtTarikhMula"), getParam("txtTarikhTamat"), getParam("txtLaluan"), getParam("txtKaedah"), getParam("txtKawasan"), getParam("txtLabelTitik"), 
						getParam("txtDarjahT"), getParam("txtDarjahU"), getParam("txtMinitT"), getParam("txtMinitU"), getParam("txtSaatT"), getParam("txtSaatU"), session);
			}
			if ("simpanMaklumatBarge".equals(hitButton)) {

				idBarge = logic.simpanMaklumatBarge(idBorangA, getParam("txtNamaDaftar"), getParam("txtNoPendaftaran"), 
						getParam("txtKapasiti"), getParam("txtJenis"), getParam("txtNoTel"), session);
			}
			if ("simpanKemaskiniMaklumatBarge".equals(hitButton)) {

				logic.simpanKemaskiniMaklumatBarge(idBarge,getParam("txtNamaDaftar"),
						getParam("txtNoPendaftaran"), getParam("txtKapasiti"), getParam("txtJenis"), getParam("txtNoTel"), session);
			}
			if ("doHapusBarge".equals(hitButton)) {
				logic.hapusBarge(idBarge, session);
			}
			if ("simpanLaporan".equals(hitButton)) {
				idBorangB = logic.simpanLaporan(id_user, getParam("idJadualKeduaLesen"),
						getParam("txtJumKuantiti"), getParam("txtJumRoyalti"), getParam("txdTarikhPengeluaran"), getParam("socBulan"),getParam("txtTahun"));
			}
			if ("simpanKemaskiniMaklumatLaporan".equals(hitButton)) {
				logic.simpanLaporan(id_user, getParam("idJadualKeduaLesen"), getParam("txtJumKuantiti"), getParam("txtJumRoyalti"),
						getParam("txdTarikhPengeluaran"), getParam("socBulan"),getParam("txtTahun"));
			}
		}
		this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session));
		
		if ("daftarBaru".equals(actionOnline)) {

			vm = "app/php2/online/frmAPBDaftarManual.jsp";
			
			mode = "new";
	       	this.context.put("mode", "new");
	    	this.context.put("readonly", "");
	    	this.context.put("inputTextClass", "");
	    	this.context.put("disabled", "");
	    	this.context.put("idStatus", "");
	    	
	    	//MAKLUMAT PEMOHON
			Vector<Hashtable<String,String>> vec = header.setMaklumatPemohon(id_user);
			this.context.put("pemohon", vec.get(0));
	    
	    	//JENIS PERMOHONAN
			this.context.put("selectJenisPermohonan", HTML.SelectJenisPermohonanAPB("socJenisPermohonan", Long.parseLong(idJenisPermohonan), "", " onChange=\"doChangeJenisPermohonan();\""));
			
			if("2".equals(idJenisPermohonan)) {
    			this.context.put("selectNoFailLama", PHPUtilHTML.SelectNoFailByIdPemohon(id_user, "9", "socNoFailLama", Long.parseLong(idFailLama), "", " onChange=\"doChangeNoFailLama();\""));
//				this.context.put("selectNoFailLama", PHPUtilHTML.SelectNoFailByIdPemohon(vec.get(0).get("noPengenalan"), "socNoFailLama", Long.parseLong(idFailLama), "", " onChange=\"doChangeNoFailLama();\"", "9"));  
			}
			
			idPermohonanLama = logic.getIdPermohonanByNoFail(idFailLama);
			
			if ("doChangeNoFailLama".equals(submit)) {
								
				//MAKLUMAT PERMOHONAN
				beanMaklumatPermohonan = new Vector();   				
				logic.setMaklumatPermohonan(idPermohonanLama);
				beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
				if(logic.getBeanMaklumatPermohonan().size() != 0) {
					Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
					idJenisLesen = (String) hashPermohonan.get("idJenisLesen");
					idKaitanTujuan = (String) hashPermohonan.get("idKaitanTujuan");
				}
				this.context.put("selectJenisLesen", HTML.SelectJenisLesen("socJenisLesen", Long.parseLong(idJenisLesen), "disabled", " class=\"disabled\""));
				this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"", " "));

				//KAWASAN PERMOHONAN
				beanMaklumatKawasanMohon = new Vector();
				logic.setBeanMaklumatKawasanMohon(idFailLama);
				beanMaklumatKawasanMohon = logic.getBeanMaklumatKawasanMohon();
				this.context.put("BeanMaklumatKawasanMohon", beanMaklumatKawasanMohon);
				if(logic.getBeanMaklumatKawasanMohon().size() != 0){
					Hashtable hashKwsnMhn = (Hashtable) logic.getBeanMaklumatKawasanMohon().get(0);
					idFlagLuar = (String) hashKwsnMhn.get("idFlagLuar");
					idNegeriPerairan = (String) hashKwsnMhn.get("idNegeriPerairan");
					idLuas = (String) hashKwsnMhn.get("idLuas");
				}				
				this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " class=\"disabled\""));
				this.context.put("selectNegeriPerairan",HTML.SelectNegeriAPB("socNegeriPerairan",Long.parseLong(idNegeriPerairan),"", " "));
				this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"", " "));
				this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"", " style=\"width:250px\""));
			}
			
			//MAKLUMAT PERMOHONAN
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
			hashMaklumatPermohonan.put("undangUndang", getParam("txtUndangUndang"));
			hashMaklumatPermohonan.put("jenisPerniagaan", getParam("txtJenisPerniagaan")); 
			beanMaklumatPermohonan.addElement(hashMaklumatPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);	
			
			this.context.put("selectJenisLesen", HTML.SelectJenisLesen("socJenisLesen", Long.parseLong(idJenisLesen), "", "onChange=\"doChangeJenisLesen();\""));
			this.context.put("selectJenisTujuan", PHPUtilHTML.SelectJenisTujuanAPB("socJenisTujuan", Long.parseLong(idJenistujuan), "", "onChange=\"doChangeJenisTujuan();\""));
			this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"", " "));
			
			//KAWASAN PERMOHONAN
			beanMaklumatKawasanMohon = new Vector();
			Hashtable hashMakalumatKawasan = new Hashtable();
			hashMakalumatKawasan.put("lokasi", getParam("txtLokasi"));
			hashMakalumatKawasan.put("luas", getParam("txtLuas"));
			beanMaklumatKawasanMohon.addElement(hashMakalumatKawasan);
			this.context.put("BeanMaklumatKawasanMohon", beanMaklumatKawasanMohon);	
			
			this.context.put("selectNegeriPerairan",HTML.SelectNegeriAPB("socNegeriPerairan",Long.parseLong(idNegeriPerairan),"", " "));
	    	this.context.put("selectNegeri",HTML.SelectNegeriAPB("socNegeri",Long.parseLong(idNegeri),"", " "));
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"", " style=\"width:100px\""));
			this.context.put("selectLuas",HTML.SelectLuasAPB("socLuas", Long.parseLong(idLuas),"", " style=\"width:250px\""));
			
			if (!"".equals(idFailLama) && !"99999".equals(idFailLama)) {
				  logic.setMaklumatPermohonan(idPermohonanLama); 	
				  beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan(); 	
				  this.context.put("BeanMaklumatPermohonan",beanMaklumatPermohonan);	
				  
				  logic.setBeanMaklumatKawasanMohon(idFailLama);
				  beanMaklumatKawasanMohon = logic.getBeanMaklumatKawasanMohon();
				  this.context.put("BeanMaklumatKawasanMohon", beanMaklumatKawasanMohon);
			}
									
		} else if ("daftarBaruBorangA".equals(actionOnline)) {
			
			log.info("daftar Baru Borang A");
			
			vm = "app/php2/online/frmAPBBorangAOnlineSenaraiBorang.jsp";

			logic.carianFailBorangA(namaPemohon,noLesen, idJadualKeduaLesen);
			list = new Vector();
			list = logic.getSenaraiFailBorangA();
			this.context.put("SenaraiFailBorangA", list);

			log.info("SenaraiFailBorangA : "+list);
			this.context.put("namaPemohon", namaPemohon);
			this.context.put("noLesen", noLesen);

			setupPage(session, action, list);
			
		} else if ("daftarBaruBorangB".equals(actionOnline)) {
			
			idJadualKeduaLesen = getParam("idJadualKeduaLesen");
				
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

		} else if ("seterusnya".equals(actionOnline)) {
        	        	
        	//GO TO MAKLUMAT PERMOHONAN  
       		vm = "app/php2/online/frmAPBMaklumatPermohonan.jsp";
       		       		
        	//HEADER
            beanHeader = new Vector(); 
            logic.setMaklumatHeader(idFail);
            beanHeader = logic.getBeanMaklumatHeader();
    		this.context.put("BeanHeader", beanHeader);
    	
    		Vector<Hashtable<String,String>> vec = header.setMaklumatPemohon(id_user);
			this.context.put("pemohon", vec.get(0));
    		
    		if (beanHeader.size() != 0) {
    			Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatHeader().get(0);
    			idFail = (String) hashHeader.get("idFail");
    			idPermohonan = (String) hashHeader.get("idPermohonan");
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
        		
        		//SENARAI PAKAR
    			logic.setSenaraiPakar(idPermohonan);
    			senaraiPakar = logic.getListPakar();
    			this.context.put("SenaraiPakar", senaraiPakar);
    			
    			//SENARAI PENGARAH
        		logic.setSenaraiPengarah(idPemohon);
        		senaraiPengarah = logic.getListPengarah();
        		this.context.put("SenaraiPengarah", senaraiPengarah);
        		this.context.put("completed", false);
        		this.context.put("flagPopup", "closePopupLampiran");
        		
	    	} else if ("1".equals(selectedTabUpper)) {

            	this.context.put("completed", false);
        		this.context.put("flagPopup", "closePopupLampiran");
            	
            	maklumatPembeliPasir(mode, idPembeliPasir, idPermohonan);
            	
            	//SENARAI PEMBELI PASIR
        		logic.setSenaraiPembeliPasir(idPermohonan);
        		senaraiPembeliPasir = logic.getListPembeliPasir();
        		this.context.put("SenaraiPembeliPasir", senaraiPembeliPasir);
        		
	    	} else if ("2".equals(selectedTabUpper)) {

            	this.context.put("completed", false);
        		this.context.put("flagPopup", "closePopupLampiran");
        		
				semak = new FrmSemakan();
				semak.mode = mode;
				senaraiSemak = semak.getSenaraiSemakanAttach("phpapb",idPermohonan);
    			this.context.put("SenaraiSemak", senaraiSemak);
    			
	    	} else if ("4".equals(selectedTabUpper)) {

				logic = new FrmAPBOnlineSenaraiFailData();
				Vector<Hashtable<String,String>> vec1 = logic.setMaklumatPejabatJKPTG();
				this.context.put("maklumatPejabat", vec1.get(0));
	    	}
	   	
        } else if ("doMaklumatPasir".equals(actionOnline)) {
        	
			Vector beanMaklumatAmbilPasir = null;
			
			idFail = getParam("idfail");
			idJadualKeduaLesen = getParam("idJadualKeduaLesen");
			idBorangA = getParam("idBorangA");
			
			// HEADER
			beanHeaderBorangA = new Vector();
			log.info("jadual kedua :"+idJadualKeduaLesen);
			logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
			beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
			this.context.put("beanHeaderBorangA", beanHeaderBorangA);
			
			//GO TO MAKLUMAT AMBIL PASIR
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
				
				hashMaklumatAmbilPasir.put("labelTitik",getParam("txtLabelTitik") == null ? "": getParam("txtLabelTitik"));
				hashMaklumatAmbilPasir.put("darjahT",getParam("txtDarjahT") == null ? "": getParam("txtDarjahT"));
				hashMaklumatAmbilPasir.put("darjahU",getParam("txtDarjahU") == null ? "": getParam("txtDarjahU"));
				hashMaklumatAmbilPasir.put("minitT",getParam("txtMinitT") == null ? "": getParam("txtMinitT"));
				hashMaklumatAmbilPasir.put("minitU",getParam("txtMinitU") == null ? "": getParam("txtMinitU"));
				hashMaklumatAmbilPasir.put("saatT",getParam("txtSaatT") == null ? "": getParam("txtSaatT"));
				hashMaklumatAmbilPasir.put("saatU",getParam("txtSaatU") == null ? "": getParam("txtSaatU"));
				
				beanMaklumatAmbilPasir.addElement(hashMaklumatAmbilPasir);
				this.context.put("BeanMaklumatAmbilPasir",beanMaklumatAmbilPasir);
				this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), ""));
				
//				//MAKLUMAT KOORDINAT
//				hashMaklumatAmbilPasir.put("labelTitik",getParam("txtLabelTitik") == null ? "": getParam("txtLabelTitik"));
//				hashMaklumatAmbilPasir.put("darjahU",getParam("txtDarjahU") == null ? "": getParam("txtDarjahU"));
//				hashMaklumatAmbilPasir.put("minitU",getParam("txtMinitU") == null ? "": getParam("txtMinitU"));
//				hashMaklumatAmbilPasir.put("saatU",getParam("txtSaatU") == null ? "": getParam("txtSaatU"));
//				hashMaklumatAmbilPasir.put("darjahT",getParam("txtDarjahT") == null ? "": getParam("txtDarjahT"));
//				hashMaklumatAmbilPasir.put("minitT",getParam("txtMinitT") == null ? "": getParam("txtMinitT"));
//				hashMaklumatAmbilPasir.put("saatT",getParam("txtSaatT") == null ? "": getParam("txtSaatT"));
//				logic.setMaklumatKoordinat(idKoordinat);
//				beanMaklumatAmbilPasir = logic.getBeanMaklumatKoordinat();
//				this.context.put("BeanMaklumatKoordinat", beanMaklumatAmbilPasir);
				
			} if ("view".equals(mode)) {

				
				 this.context.put("readonly", "readonly");
				 this.context.put("inputTextClass", "disabled");
				
				 idJadualKeduaLesen = getParam("idJadualKeduaLesen");
				 idBorangA = getParam("idBorangA");
				 
				 // HEADER
				 beanHeaderBorangA = new Vector();
				 logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
				 beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();	
					
				 this.context.put("beanHeaderBorangA", beanHeaderBorangA);
				 log.info("beanHeaderBorangA ..1 : "+beanHeaderBorangA);
				 
				 beanMaklumatAmbilPasir = new Vector();
				 logic.setMaklumatAmbilPasir(idBorangA);
				 beanMaklumatAmbilPasir = logic.getBeanMaklumatAmbilPasir();
				 this.context.put("BeanMaklumatAmbilPasir",beanMaklumatAmbilPasir);
				 this.context.put("idBorangA", idBorangA);
				 
				 if (logic.getBeanMaklumatAmbilPasir().size() != 0){
					 Hashtable hashPasir = (Hashtable) logic.getBeanMaklumatAmbilPasir().get(0);
					 idBulan = (String) hashPasir.get("bulan");
				 }
				 
				 this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), "disabled", " class=\"disabled\""));
				
//				 //SENARAI TITIK KOORDINAT
//	    		 logic.setSenaraiKoordinat(idPermohonan);
//	    		 senaraiKoordinat = logic.getListKoordinat();
//	    		 this.context.put("SenaraiKoordinat", senaraiKoordinat);
			}
			
		    if ("update".equals(mode)) {

	        	 this.context.put("readonly", "");
				 this.context.put("inputTextClass", "");
				
				 idJadualKeduaLesen = getParam("idJadualKeduaLesen");
				 idBorangA = getParam("idBorangA");
				 // HEADER
				 beanHeaderBorangA = new Vector();
				 log.info("jadual kedua barge :"+idJadualKeduaLesen);
				 logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
				 beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
					
				 this.context.put("beanHeaderBorangA", beanHeaderBorangA);
				 
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
				 
				 hashMaklumatAmbilPasir.put("labelTitik",getParam("txtLabelTitik") == null ? "": getParam("txtLabelTitik"));
				 hashMaklumatAmbilPasir.put("darjahT",getParam("txtDarjahT") == null ? "": getParam("txtDarjahT"));
				 hashMaklumatAmbilPasir.put("darjahU",getParam("txtDarjahU") == null ? "": getParam("txtDarjahU"));
				 hashMaklumatAmbilPasir.put("minitT",getParam("txtMinitT") == null ? "": getParam("txtMinitT"));
				 hashMaklumatAmbilPasir.put("minitU",getParam("txtMinitU") == null ? "": getParam("txtMinitU"));
				 hashMaklumatAmbilPasir.put("saatT",getParam("txtSaatT") == null ? "": getParam("txtSaatT"));
				 hashMaklumatAmbilPasir.put("saatU",getParam("txtSaatU") == null ? "": getParam("txtSaatU"));
					
				 beanMaklumatAmbilPasir.addElement(hashMaklumatAmbilPasir);
				 this.context.put("BeanMaklumatAmbilPasir",beanMaklumatAmbilPasir);
				 this.context.put("selectBulan",HTML.SelectBulan("socBulan", Long.parseLong(idBulan), ""));
				 
//				//SENARAI TITIK KOORDINAT
//	    		logic.setSenaraiKoordinat(idPermohonan);
//	    		senaraiKoordinat = logic.getListKoordinat();
//	    		this.context.put("SenaraiKoordinat", senaraiKoordinat);

		    } 
		    
			if("newBarge".equals(mode)) {
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				idJadualKeduaLesen = getParam("idJadualKedua");
				
				// HEADER
				beanHeaderBorangA = new Vector();
				log.info("jadual kedua barge :"+idJadualKeduaLesen);
				logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
				beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
				
				this.context.put("beanHeaderBorangA", beanHeaderBorangA);
			
				beanMaklumatBarge = new Vector();
				Hashtable hashMaklumatBarge = new Hashtable();
				hashMaklumatBarge.put("namaDidaftarkan","");
				hashMaklumatBarge.put("noPendaftaran","");
				hashMaklumatBarge.put("kapasiti","");
				hashMaklumatBarge.put("jenis","");
				hashMaklumatBarge.put("noTel","");
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
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				 
				idJadualKeduaLesen = getParam("idJadualKeduaLesen");
				// HEADER
				beanHeaderBorangA = new Vector();
				log.info("jadual kedua :"+idJadualKeduaLesen);
				logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
				beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
						
				this.context.put("beanHeaderBorangA", beanHeaderBorangA);
				
				 //logic.setMaklumatBarge(idBarge);
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
		
			//LIST BARGE
			senaraiBarge = new Vector();
//			senaraiBarge = logic.getSenaraiBarge();

			logic.carianBarge(idBorangA);
			senaraiBarge = logic.getSenaraiBarge();
			
			this.context.put("SenaraiBarge", senaraiBarge);
			setupPage2(session, action, senaraiBarge);
			
		} else if ("papar".equals(actionOnline)) {

        	vm = "app/php2/online/frmAPBBorangAOnlineSenaraiBorang.jsp";
			
			logic.maklumatPelesen(idJadualKedua);
			beanPelesen = logic.getBeanPelesen();
			if (beanPelesen.size() != 0) {
				Hashtable hashPelesen = (Hashtable) logic.getBeanPelesen().get(0);
				this.context.put("namaPelesen", (String) hashPelesen.get("namaPelesen").toString());
				this.context.put("noLesen", (String) hashPelesen.get("noLesen").toString());
//				this.context.put("txtnamaPemohon", namaPemohon);
//				this.context.put("txtNoLesen", noLesen);
			}

			listBorangA = new Vector();
			logic.carianFailBorangA(idJadualKeduaLesen, idBulanList, getParam("txtTahun2"));
			listBorangA = logic.getSenaraiFailBorangA();
			this.context.put("SenaraiBorangA", listBorangA);
			this.context.put("selectBulanList",HTML.SelectBulan("socBulanList", Long.parseLong(idBulanList)," style=\"width:auto\""));
			this.context.put("tahun", getParam("txtTahun2"));

			setupPage1(session, action, listBorangA);
	
			// GO TO LIST FAIL LESEN
			vm = "app/php2/online/frmAPBBorangAOnlineSenaraiBorang.jsp";

			logic.carianFailBorangA(namaPemohon,noLesen, idJadualKeduaLesen);
			//logic.carianFail(getParam("txtNamaPelesen"),getParam("txtNoLesen"));
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);
			this.context.put("txtNamaPelesen", getParam("txtNamaPelesen"));
			this.context.put("txtNoLesen", getParam("txtNoLesen"));

			setupPage(session, action, list);
			
        } else if ("simpanLaporan".equals(actionOnline)) {

			idJadualKeduaLesen = getParam("idJadualKeduaLesen");
        	log.info("jadual kedua borang B :"+idJadualKeduaLesen);
        		
			// HEADER
			beanHeaderBorangA = new Vector();
			logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
			beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
			this.context.put("beanHeaderBorangA", beanHeaderBorangA);
				
			String socBulan = getParam("socBulan");
	        String txtTahun = getParam("txtTahun");
	        
	        // ika tambah 9/11/2020
	        String txtTarikh = getParam("txtTarikhOperasi");
	        String txtBulan = txtTarikh.substring(3,5);
			String txtTahunOps = txtTarikh.substring(6,10);
			
	        boolean returnChecking = false;
	        
	        returnChecking = FrmAPBLaporanPasirData.isBulanExist(idJadualKeduaLesen,txtBulan,txtTahunOps);
	        //returnChecking = FrmAPBLaporanPasirData.isBulanExist(idJadualKeduaLesen,txtTarikh);
	        log.info("returnChecking "+returnChecking);
	        	
	        if(returnChecking == false){

	           	// INSERT TBLPHPLAPORANPASIR        	
	           	String result = "";
	           	result = simpanLaporan(id_user,idJadualKeduaLesen);
	           	context.put("id_laporanpasir",result);
	            id_laporanpasir = result;
	            log.info("id_laporanpasir :: "+id_laporanpasir);        	
	            	
	            if(id_laporanpasir != "") {

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
	            	
	            	this.context.put("bulan_pengambilan", bulan);
	            	
	     	        // DATA
	            	context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan),"disabled", " class=\"disabled\""));
	                context.put("bulan_pengambilan",bulan);
	                	
	                // VALIDATION
	                context.put("button", "view");
	                context.put("clearForm", "");
	                context.put("flag", "semak");
	                context.put("mode", "disabled"); 
	                //context.put("returnChecking", false);
	                
	                // GET LIST PENGELUARAN PASIR LAUT
	                //listPasir = modelLaporanPasir.getListPasir(id_laporanpasir);	
	            	//context.put("PermohonanPasir", listPasir);
	            	//context.put("list_size", listPasir.size());  	    		          		 		
	            	
	            	// PAGING
	            	setupPagePasir(session,action,listPasir);
	                            	
	                // SCREEN
	            	vm = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";
	            	
	            } else{
	            	
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
	        	
	        } else {
	    		
	    		// VALIDATION
	        	context.put("button", "add");
	        	context.put("clearForm", "");
	        	context.put("flag", "");
	        	context.put("mode", ""); 
	    		context.put("returnChecking", true);
	    	
	    		// SCREEN
	        	vm = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";	    	
	        }
			
		} else if("papar_laporan".equals(actionOnline)) {

       		// GET LATEST DATA LAPORAN
			logic.getMaklumatLaporan(id_laporanpasir);
    		Vector getMaklumatLaporan = logic.getMaklumatLaporan();
    		context.put("dataLaporan", getMaklumatLaporan);
    		
    		idJadualKeduaLesen = getParam("idJadualKeduaLesen");
    		
			// HEADER
			beanHeaderBorangA = new Vector();
			log.info("jadual kedua papar laporan :"+idJadualKeduaLesen);
			logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
			beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
					
			this.context.put("beanHeaderBorangA", beanHeaderBorangA);
    		
    		String bulan = "";
    		if(getMaklumatLaporan.size()!=0){
    			Hashtable a = (Hashtable) getMaklumatLaporan.get(0);
    			bulan = (String)a.get("bulan_pengambilan");
    		}
    		this.context.put("bulan_pengambilan", bulan);
        	// DATA LAPORAN
        	context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan), "style=width:auto disabled", " class=\"disabled\""));   	
        	
        	// KIRAAN JUMLAH KUANTITI & JUMLAH ROYALTI
        	logic.getJumlahKiraan(id_laporanpasir);
    		Vector getJumlahKiraan = logic.getJumlahKiraan();
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
        	
        	// GET LIST DOKUMEN PENGELUARAN PASIR LAUT
//    		context.put("SenaraiDokumen", listDokumen);
//    		context.put("list_size", listDokumen.size()); 
        	
        	log.info("id 10112020 :" + id_laporanpasir);
    		
        	senaraiDokumenPasir = new Vector();
			logic.setSenaraiDokumen(id_laporanpasir);
			senaraiDokumenPasir = logic.getListDokumen();
			this.context.put("SenaraiDokumen", senaraiDokumenPasir);
        	
    		// PAGING
//    		setupPageUpload(session,action,listDokumen);
    		
    		// SCREEN
    		vm = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";        	
        	
		} else if("kemaskiniLaporan".equals(actionOnline)){

			idJadualKeduaLesen = getParam("idJadualKeduaLesen");
    		log.info("jadual kedua kemaskini :"+idJadualKeduaLesen);
        	
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
        	context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan), "style=width:auto" ));   	
        	
        	// VALIDATION
        	context.put("button", "edit");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "");       	
        	
        	
        	// SCREEN
        	vm = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";        	  
        				
		} else if("simpanEditLaporan".equals(actionOnline)) {

        	idJadualKeduaLesen = getParam("idJadualKeduaLesen");
        	
        	simpanEditLaporan(id_user,id_laporanpasir);
        	
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
    		context.put("selectBulan",HTML.SelectBulan( "socBulan",Utils.parseLong(bulan), "style=width:auto disabled", " class=\"disabled\""));            	
        	
        	// VALIDATION
        	context.put("button", "view");
        	context.put("clearForm", "");
        	context.put("flag", "semak");
        	context.put("mode", "disabled");
        	context.put("idJadualKeduaLesen",idJadualKeduaLesen);
        	
        	// SCREEN
        	vm = "app/php2/online/frmAPBBorangBOnlineDaftar.jsp";           	        	 
			
		} else if("hapusLaporan".equals(actionOnline)) {

        	idJadualKeduaLesen = getParam("idJadualKeduaLesen");
        	idLaporanPasir = getParam("id_laporanpasir");
    		log.info("id laporan pasir hapus :"+idLaporanPasir);
    		
    		//HAPUS
    		logic.hapusLaporan(idLaporanPasir, session);
    		vm = "app/php2/online/frmAPBBorangBOnline.jsp";
    		
    		log.info("namaPemohon"+namaPemohon);
    		log.info("noLesen"+noLesen);

    		logic.carianFailBorangB(namaPemohon,noLesen, idJadualKeduaLesen);
    		list = new Vector();
    		list = logic.getSenaraiFailBorangB();

    		this.context.put("SenaraiFailBorangB", list);

    		log.info("SenaraiFailBorangB : "+list);
    		this.context.put("txtNamaPelesen", getParam("txtNamaPelesen"));
			this.context.put("txtNoLesen", getParam("txtNoLesen"));
    			
    		setupPage(session, action, list);
        	
		} else if("daftarLaporan".equals(actionOnline)) {

    		idJadualKeduaLesen = getParam("idJadualKeduaLesen");
    		log.info("jadual kedua daftar :"+idJadualKeduaLesen);
    		
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
    
		} else if("uploadBaruDokumen".equals(actionOnline)){

        	context.put("idJadualKeduaLesen",idJadualKeduaLesen);
        	
        	if ("new".equals(modePopup)) {
		
				this.context.put("readonlyPopup", "");
				this.context.put("inputTextClassPopup", "");
	
				beanMaklumatDokumen = new Vector();
				Hashtable hashMaklumatDokumen = new Hashtable();
				hashMaklumatDokumen.put("namaLampiran", "");
				hashMaklumatDokumen.put("catatanLampiran", "");
				beanMaklumatDokumen.addElement(hashMaklumatDokumen);
				this.context.put("BeanMaklumatDokumen", beanMaklumatDokumen);
		
			} else if ("update".equals(modePopup)) {

				this.context.put("readonlyPopup", "");
				this.context.put("inputTextClassPopup", "");

				// MAKLUMAT DOKUMEN
				beanMaklumatDokumen = new Vector();
				logic.setMaklumatLaporanPasir(idDokumen);
				beanMaklumatDokumen = logic.getbeanMaklumatDokumen();
				this.context.put("BeanMaklumatDokumen", beanMaklumatDokumen);

			} else if ("view".equals(modePopup)) {

				this.context.put("readonlyPopup", "readonly");
				this.context.put("inputTextClassPopup", "disabled");

				// MAKLUMAT DOKUMEN
				beanMaklumatDokumen = new Vector();
				logic.setMaklumatLaporanPasir(idDokumen);
				beanMaklumatDokumen = logic.getbeanMaklumatDokumen();
				this.context.put("BeanMaklumatDokumen", beanMaklumatDokumen);
			}    		          		 		
	    		
			senaraiDokumenPasir = new Vector();
			logic.setSenaraiDokumen(id_laporanpasir);
			senaraiDokumenPasir = logic.getListDokumen();
			this.context.put("  ", senaraiDokumenPasir);
					
        	idJadualKeduaLesen = getParam("idJadualKeduaLesen");
    		log.info("jadual kedua daftar :"+idJadualKeduaLesen);
    		
			// HEADER
			beanHeaderBorangA = new Vector();
			logic.setMaklumatPermohonanBorangA(idJadualKeduaLesen);
			beanHeaderBorangA = logic.getBeanMaklumatPermohonanBorangA();
			
//			beanMaklumatDokumen = new Vector();
//			Hashtable hashMaklumatDokumen = new Hashtable();
//			hashMaklumatDokumen.put("namaLampiran", "");
//			hashMaklumatDokumen.put("catatanLampiran", "");
//			beanMaklumatDokumen.addElement(hashMaklumatDokumen);
//			this.context.put("BeanMaklumatDokumen", beanMaklumatDokumen);
			
			this.context.put("beanHeaderBorangA", beanHeaderBorangA);
				
	        // SCREEN
	        vm = "app/php2/online/frmAPBOnlineLampiranPasirLaut.jsp";
        
		} else {

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
		
		this.context.put("idJenisLesen", idJenisLesen);
		this.context.put("idNegeriPerairan", idNegeriPerairan);
		this.context.put("idJenisPermohonan", idJenisPermohonan);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idFailLama", idFailLama);		
		this.context.put("idPengarah", idPengarah);
		this.context.put("idPembeliPasir", idPembeliPasir);
	    this.context.put("idProjek", idProjek);
	    this.context.put("idPakar", idPakar);
	    this.context.put("idKoordinat", idKoordinat);
	    this.context.put("idPemohon", idPemohon);
	    this.context.put("idKategoriIndividu", idKategoriIndividu);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
		this.context.put("idDokumen", idDokumen);
		this.context.put("idJadualKeduaLesen", idJadualKeduaLesen);
		this.context.put("idBorangA", idBorangA);
		this.context.put("idBarge", idBarge);
		this.context.put("idWarganegara", idWarganegara);
		this.context.put("idBangsa", idBangsa);

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
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		String idFlagLuar = "1";
		
		if ("update".equals(mode)){
			
			this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	this.context.put("disabled", "");
	
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
			
			this.context.put("selectTujuanKaitan",HTML.SelectPHPRujTujuanKaitan("socKaitanTujuan", Long.parseLong(idKaitanTujuan),"", " "));
			this.context.put("selectJenisTujuan",PHPUtilHTML.SelectJenisTujuanAPB("socJenisTujuan", Long.parseLong(idJenistujuan),"disabled", " class=\"disabled\""));
			this.context.put("selectJenisLesen",HTML.SelectJenisLesen("socJenisLesen", Long.parseLong(idJenisLesen),"disabled", " class=\"disabled\""));
			
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
			hashMaklumatPermohonan.put("undangUndang", getParam("txtUndangUndang"));
			hashMaklumatPermohonan.put("jenisPerniagaan", getParam("txtJenisPerniagaan"));
			hashMaklumatPermohonan.put("jumlahModal", getParam("txtJumlahModal"));
			hashMaklumatPermohonan.put("jumlahModal1", getParam("txtJumlahModal1"));
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
				
        	} else{
        		
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
			
			this.context.put("mode", "update");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	this.context.put("disabled", "");
        	
        	//MAKLUMAT PENGARAH
			this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong(idWarganegara), "", "onChange=\"doChangeWarganegara();\""));
			this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong(idJenisPengenalan), "", ""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), "", "onChange=\"doChangeBangsa();\""));
		
		} else if ("viewPengarah".equals(mode)){
			
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	this.context.put("disabled", "disabled");
        	
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
			
			String submit = getParam("command");
			this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	this.context.put("disabled", "");
        	
        	//MAKLUMAT PENGARAH			
			beanMaklumatPengarah = new Vector();
			Hashtable hashPengarah = new Hashtable();
    		hashPengarah.put("nama", "");
			hashPengarah.put("noPengenalan","");
			hashPengarah.put("saham","");
			hashPengarah.put("warga", "");
			hashPengarah.put("bangsa", "");
			beanMaklumatPengarah.addElement(hashPengarah);
			this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);
			this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong("99999"), "", "onChange=\"doChangeWarganegara();\""));
			this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong("99999"), "", ""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong("99999"), "", "onChange=\"doChangeBangsa();\""));
			
			if ("doChangeWarganegara".equals(submit)){
				
				hashPengarah.put("nama", getParam("txtNamaPengarah") == null ? "" : getParam("txtNamaPengarah"));		
				hashPengarah.put("noPengenalan", getParam("txtNoPengenalan") == null ? "" : getParam("txtNoPengenalan"));
				hashPengarah.put("saham", getParam("txtSaham") == null ? "" : getParam("txtSaham"));
				hashPengarah.put("warga", getParam("txtWarga") == null ? "" : getParam("txtWarga"));
				hashPengarah.put("bangsa", getParam("txtBangsa") == null ? "" : getParam("txtBangsa"));
				beanMaklumatPengarah.addElement(hashPengarah);
				this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);	
				this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong(idJenisPengenalan), "", "onChange=\"doChangeJenisPengenalan();\""));
				this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), "", "onChange=\"doChangeBangsa();\""));
			
        	} 
			
			if ("doChangeBangsa".equals(submit)){
				
				hashPengarah.put("nama", getParam("txtNamaPengarah") == null ? "" : getParam("txtNamaPengarah"));		
				hashPengarah.put("noPengenalan", getParam("txtNoPengenalan") == null ? "" : getParam("txtNoPengenalan"));
				hashPengarah.put("saham", getParam("txtSaham") == null ? "" : getParam("txtSaham"));
				hashPengarah.put("warga", getParam("txtWarga") == null ? "" : getParam("txtWarga"));
				hashPengarah.put("bangsa", getParam("txtBangsa") == null ? "" : getParam("txtBangsa"));
				beanMaklumatPengarah.addElement(hashPengarah);
				this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);	
				this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong(idWarganegara), "", "onChange=\"doChangeWarganegara();\""));
				this.context.put("selectJenisPengenalan",HTML.SelectJenisNoPbIndividu("socJenisPengenalan", Long.parseLong(idJenisPengenalan), "", ""));
			
        	} 			
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
			
			//MAKLUMAT PAKAR
			logic.setMaklumatPakar(idPakar);
			beanMaklumatPakar = logic.getBeanMaklumatPakar();
			this.context.put("BeanMaklumatPakar", beanMaklumatPakar);
			
		} else if ("newPakar".equals(mode)){
			
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
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
	
	//UPLOAD LAMPIRAN BARU TAMBAH21062020
	private void uploadLampiran(String idPermohonan, HttpSession session) throws Exception {
		log.info("lalu A");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
					log.info("lalu B");
					saveLampiran(item, idPermohonan, session);
				}
			}
		}
	}
		
	private void saveLampiran(FileItem item, String idPermohonan, HttpSession session) throws Exception {
		log.info("lalu C");

		Db db = null;
			String userId = (String) session.getAttribute("_ekptg_user_id");
				
			try {

				db = new Db();

				// TBLPHPDOKUMEN
				long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
				Connection con = db.getConnection();
				con.setAutoCommit(false);
				PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?)");
				ps.setLong(1, idDokumen);
				ps.setString(2, getParam("namaLampiran"));
				ps.setString(3, getParam("catatanLampiran"));
				ps.setString(4, userId);
				ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
				ps.setString(6, item.getContentType());
				ps.setString(7, item.getName());
				ps.setString(8, "L");
				ps.setString(9, idPermohonan);
				ps.executeUpdate();

				con.commit();
				log.info("lalu D");
					
				AuditTrail.logActivity("1610198", "4", null, session, "INS", "FAIL [" + idPermohonan + "] DIDAFTARKAN");
					
			} finally {
				if (db != null)
					db.close();
			}
		this.context.put("completed", true);
	}
		
	private ILampiran getDocPHP(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;
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
	public void setupPage1(HttpSession session, String action, Vector list) {

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
			this.context.put("SenaraiBorangA", paging.getPage(page));
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
			if (page > paging.getTotalPages()) 
				page = 1; //reset page number
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
	}				

	// SIMPAN LAPORAN
    private String simpanLaporan(String id_user, String idJadualKeduaLesen) throws Exception {
    	
    	String txtJumKuantiti = getParam("txtJumKuantiti");
    	String txtJumRoyalti = getParam("txtJumRoyalti");
    	String txtTarikhOperasi = getParam("txtTarikhOperasi");
    	//String socBulan = getParam("socBulan");
    	//String txtTahun = getParam("txtTahun");
    	/*String txtKontraktor = getParam("txtKontraktor");*/
    	/*String txtPembeli = getParam("txtPembeli");*/
    	String txtMasaOperasi  = getParam("txtMasaOperasi");
    	String txtHariOperasi  = getParam("txtHariOperasi");
    	String txtBulan = txtTarikhOperasi.substring(3,5);
    	String txtTahun = txtTarikhOperasi.substring(6,10);
    	String txtKapal = getParam("txtNamaKapal");
    	
    	return logic.simpanLaporan(id_user,idJadualKeduaLesen,txtJumKuantiti,txtJumRoyalti,txtTarikhOperasi,txtBulan,txtTahun,txtMasaOperasi,txtHariOperasi,txtKapal);		
    }
				
	// PAGING SKRIN LAPORAN ADD14102020
	public void setupPageLaporan(HttpSession session,String action,Vector listLaporan, String SkrinListLaporan ) {
		
		try {

			String vm = "app/php2/online/frmAPBBorangBOnline.jsp";
			
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
			if (page > paging.getTotalPages()) 
				page = 1; //reset page number
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
	
	//Add 14102020
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
			if (page > paging.getTotalPages()) 
				page = 1; //reset page number
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
	}
				
	// UPDATELAPORAN
	private void simpanEditLaporan(String id_user, String id_laporanpasir) throws Exception {
		
		String txtJumKuantiti = getParam("txtJumKuantiti");
		String txtJumRoyalti = getParam("txtJumRoyalti");
		String txtTarikhOperasi = getParam("txtTarikhOperasi");
//		String socBulan = getParam("socBulan");
//		String txtTahun = getParam("txtTahun");
//		String txtKontraktor = getParam("txtKontraktor");
//		String txtPembeli = getParam("txtPembeli");
		String txtMasaOperasi  = getParam("txtMasaOperasi");
		String txtHariOperasi  = getParam("txtHariOperasi");
		String txtBulan = txtTarikhOperasi.substring(3,5);
		String txtTahun = txtTarikhOperasi.substring(6,10);
		String txtKapal = getParam("txtNamaKapal");
		
		logic.simpanEditLaporan(id_user,id_laporanpasir,txtJumKuantiti,txtJumRoyalti,txtTarikhOperasi,
				txtBulan,txtTahun,txtMasaOperasi,txtHariOperasi,txtKapal);
	}	

	// UPLOAD FILE
	private void uploadFiles(String id_laporanpasir, String idPermohonan, HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, id_laporanpasir, idPermohonan, session);
				}
			}
		}
	}
				
	private void uploadFilesUpdate(String id_laporanpasir, String idDokumen, HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveDataUpdate(item, id_laporanpasir, idDokumen, session);
				}
			}
		}
	}
				
	private void saveDataUpdate(FileItem item, String id_laporanpasir, String idDokumen, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();

			// TBLPHPDOKUMEN
			//long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("UPDATE TBLPHPDOKUMEN"
							+ "SET NAMA_DOKUMEN = ?, CATATAN = ?, ID_MASUK = ?,TARIKH_MASUK = ?, CONTENT = ?, JENIS_MIME = ?, NAMA_FAIL = ?"
							+ "WHERE ID_DOKUMEN = ?");
			ps.setString(1, getParam("namaLampiran"));
			ps.setString(2, getParam("catatanLampiran"));
			ps.setString(3, userId);
			ps.setBinaryStream(4, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idDokumen);
			ps.executeUpdate();
			
			Log.info("INSERT DOKUMEN1"+ps);
			
			con.commit();
			this.context.put("idDokumen", idDokumen);

		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}

	private void saveData(FileItem item, String id_laporanpasir, String idPermohonan, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		
		try {
			db = new Db();

			// TBLPHPDOKUMEN
			long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_LAPORANPASIR,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaLampiran"));
			ps.setString(3, getParam("catatanLampiran"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, id_laporanpasir);
			ps.setString(9, "L");// laporan pasir
			ps.setString(10, idPermohonan);
			ps.executeUpdate();
			
			Log.info("INSERT DOKUMEN1"+ps);
			
			con.commit();
			this.context.put("idDokumen", idDokumen);

		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}
}
