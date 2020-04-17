/**
 * 
 */
package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.FrmPYWPerjanjianTambahanData;

/**
 * 
 *
 */
public class FrmPYWPerjanjianTambahanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPYWHeaderData logicHeader = new FrmPYWHeaderData();
	FrmPYWPerjanjianTambahanData logic = new FrmPYWPerjanjianTambahanData();
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String)session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		//GET DEFAULT PARAM
	    String submit = getParam("command");  
	    String vm = ""; 
        String mode = getParam("mode");
        String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
        String actionPenyewaan = getParam("actionPenyewaan");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String hitButton = getParam("hitButton");
		
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idUrusan = getParam("idUrusan");
        String idSuburusan = getParam("idSuburusan");
        String idStatus = getParam("idStatus");
        String flagAktif = getParam("flagAktif");
        
        String idPermohonanPerjanjianTambahan = getParam("idPermohonanPerjanjianTambahan");
        String idStatusPerjanjianTambahan = getParam("idStatusPerjanjianTambahan");
        String idPejabat = getParam("idPejabat");
        String idUlasanTeknikal = getParam("idUlasanTeknikal");
        String idMesyuarat = getParam("idMesyuarat");
        String flagStatus = getParam("flagStatus");
        String aktif = getParam("aktif");
        String idPerjanjian = getParam("idPerjanjian");
        String idKeputusan = getParam("idKeputusan");
        String idHakmilikPerjanjianTambahan = "";
        
        //VECTOR
        Vector senaraiPerjanjianTambahan = null;
        Vector beanHeader = null;
        Vector beanHeaderPerjanjianTambahan = null;
        Vector beanMaklumatPermohonan = null;
        
        Vector beanMaklumatJPPH = null;
        Vector beanMaklumatPejabat = null;
        Vector senaraiJPPH = null;
        Vector beanMaklumatMesyuarat = null;
        Vector senaraiMesyuarat = null; 
        Vector beanMaklumatKeputusan = null;
        Vector beanMaklumatPerjanjian = null;
        Vector beanMaklumatMaklumbalas = null;
        
        //DROPDOWN
        String flagJenisPerjanjian = getParam("flagJenisPerjanjian");
        String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		
		String idLokasi = getParam("socLokasi");
		if (idLokasi == null || idLokasi.trim().length() == 0){
			idLokasi = "99999";
		}
		String idJamDari = getParam("socJamDari");
		if (idJamDari == null || idJamDari.trim().length() == 0){
			idJamDari = "99999";
		}
		String idMinitDari = getParam("socMinitDari");
		if (idMinitDari == null || idMinitDari.trim().length() == 0){
			idMinitDari = "99999";
		}
		String idJamHingga = getParam("socJamHingga");
		if (idJamHingga == null || idJamHingga.trim().length() == 0){
			idJamHingga = "99999";
		}
		String idMinitHingga = getParam("socMinitHingga");
		if (idMinitHingga == null || idMinitHingga.trim().length() == 0){
			idMinitHingga = "99999";
		}
        
        //DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		this.context.put("onload", "");
		
		//HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)){
				idPermohonanPerjanjianTambahan = logic.daftarBaru(idFail, getParam("tarikhTerima"), getParam("tarikhSurat"), getParam("txtNoRujukanSurat"),
								getParam("socFlagJenisPerjanjian"), getParam("txtTujuan"), getParam("tarikhMulaPerjanjian"),
								idLuasKegunaan, getParam("txtLuasAsal"), idLuas, getParam("txtLuasMohon1"), getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
								getParam("txtLuasBersamaan"), getParam("txtBakiLuas"), getParam("txtCatatan"), session);
				idStatusPerjanjianTambahan = "1610198";
			}			
			if ("simpanMaklumatJPPH".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJPPH(idPermohonanPerjanjianTambahan, getParam("idPejabatJPPH"), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJPPH".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJPPH(idUlasanTeknikal, idPermohonanPerjanjianTambahan, getParam("idPejabatJPPH"), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJPPH".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatJPPH(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"),
        				getParam("txtKadarSewaBulan"), getParam("txtKadarSewaTahun"), session);
    		}
        	if ("hapusMaklumatKJPKJT".equals(hitButton)){
    			logic.hapusMaklumatKJPKJT(idUlasanTeknikal);
    		}
        	if ("simpanMesyuarat".equals(hitButton)){
    			idMesyuarat = logic.simpanMesyuarat(idPermohonanPerjanjianTambahan, getParam("txtTajukMesyuarat"), getParam("txtBilMesyuarat"), getParam("txtTarikhMesyuarat"),
    					idJamDari, idMinitDari, idJamHingga, idMinitHingga, idLokasi, getParam("socSyor"), getParam("txtCatatanMesyuarat"), session);
    		}
        	if ("simpanKemaskiniMesyuarat".equals(hitButton)){
    			logic.simpanKemaskiniMesyuarat(idMesyuarat, getParam("txtTajukMesyuarat"), getParam("txtBilMesyuarat"), getParam("txtTarikhMesyuarat"),
    					idJamDari, idMinitDari, idJamHingga, idMinitHingga, idLokasi, getParam("socSyor"), getParam("txtCatatanMesyuarat"), session);
    		}
        	if ("hapusMesyuarat".equals(hitButton)){
    			logic.hapusMesyuarat(idMesyuarat);
    		} 
        	if ("doSimpan".equals(hitButton)){
			    logic.simpanMaklumatKeputusan(idPermohonanPerjanjianTambahan, idKeputusan, getParam("txtTarikhKeputusan"), idPerjanjian, 
			    		getParam("txtTarikhMula"), getParam("txtKadarSewa"), getParam("txtCagaran"), session);
			}
        	if ("simpanKemaskiniPerjanjian".equals(hitButton)) {
        		logic.simpanKemaskiniPerjanjian(idPerjanjian, getParam("txtNoSiri"), getParam("txtTarikhMulaTabPerjanjian"),
			    		getParam("txtKadarSewa"), getParam("txtCagaran"), session);
			}
        	if ("simpanKemaskiniMaklumbalas".equals(hitButton)) {
        		logic.simpanKemaskiniMaklumbalas(idPerjanjian, getParam("txtTarikhTerimaCagaran"), getParam("txtNoRujukanCagaran"),
			    		getParam("socCagaran"), getParam("txtTarikhTerimaTandatangan"), getParam("txtNoRujukanTandatangan"), getParam("socTandatangan"),
			    		getParam("txtTarikhTerimaMatiSetem"), getParam("txtNoRujukanMatiSetem"), getParam("socMatiSetem"), session);
			}
        	if ("simpanKemaskiniMaklumatPermohonan".equals(hitButton)){
				logic.simpanKemaskiniMaklumatPermohonan(idPermohonanPerjanjianTambahan, getParam("tarikhTerima"), getParam("tarikhSurat"), getParam("txtNoRujukanSurat"),
								flagJenisPerjanjian, getParam("txtTujuan"), getParam("tarikhMulaPerjanjian"),
								idLuasKegunaan, getParam("txtLuasAsal"), idLuas, getParam("txtLuasMohon1"), getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
								getParam("txtLuasBersamaan"), getParam("txtBakiLuas"), getParam("txtCatatan"), session);
			}
        	if ("hapusPermohonan".equals(hitButton)){
    			logic.hapusPermohonan(idPermohonanPerjanjianTambahan);
    		}
        	if ("seterusnyaMP".equals(hitButton)){
    			logic.seterusnyaMP(idPermohonanPerjanjianTambahan, flagJenisPerjanjian);
    			if ("1".equals(flagJenisPerjanjian) || "2".equals(flagJenisPerjanjian)){
    				selectedTabUpper = "2";
    			} else {
    				selectedTabUpper = "1";
    			}
    		}
        	if ("seterusnyaJT".equals(hitButton)){
    			logic.seterusnyaJT(idPermohonanPerjanjianTambahan);
    			selectedTabUpper = "2";
    		}
        	if ("seterusnyaMS".equals(hitButton)){
    			logic.seterusnyaMS(idPermohonanPerjanjianTambahan);
    			selectedTabUpper = "3";
    		}
        	if ("seterusnyaLulus".equals(hitButton)){
    			logic.seterusnyaLulus(idPermohonanPerjanjianTambahan);
    			selectedTabUpper = "4";
    		}
        	if ("seterusnyaTolak".equals(hitButton)){
    			logic.seterusnyaTolak(idPermohonanPerjanjianTambahan);
    		}
        	if ("seterusnyaPA".equals(hitButton)){
        		if (logic.checkEmptyNoSiri(idPermohonanPerjanjianTambahan)){
        			logic.seterusnyaPA(idPermohonanPerjanjianTambahan);
        		} else {
        			this.context.put("onload", " \"alert('Sila Masukkan No Siri Perjanjian.')\"");        			
        		}    			
    		}
		}
		
        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, getParam("initiateFlagBuka"), session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
			idUrusan = (String) hashHeader.get("idUrusan");
			idSuburusan = (String) hashHeader.get("idSuburusan");
			flagAktif = (String) hashHeader.get("flagAktif");
		}
		
		//HEADER PERJANJIAN TAMBAHAN
        beanHeaderPerjanjianTambahan = new Vector();
        logic.setHeaderPerjanjianTambahan(idPermohonanPerjanjianTambahan);
        beanHeaderPerjanjianTambahan = logic.getBeanHeaderPerjanjianTambahan();
		this.context.put("BeanHeaderPerjanjianTambahan", beanHeaderPerjanjianTambahan);
		
		if (logic.getBeanHeaderPerjanjianTambahan().size() != 0){
			Hashtable hashHeaderPerjanjianTambahan = (Hashtable) logic.getBeanHeaderPerjanjianTambahan().get(0);
			idPermohonanPerjanjianTambahan = (String) hashHeaderPerjanjianTambahan.get("idPermohonanPerjanjianTambahan");
			idStatusPerjanjianTambahan = (String) hashHeaderPerjanjianTambahan.get("idStatusPerjanjianTambahan");
			flagJenisPerjanjian = (String) hashHeaderPerjanjianTambahan.get("flagJenisPerjanjian");
			idHakmilikPerjanjianTambahan = (String) hashHeaderPerjanjianTambahan.get("idHakmilikPerjanjianTambahan");
			idKeputusan = (String) hashHeaderPerjanjianTambahan.get("idKeputusan");
		}
		
		if ("papar".equals(actionPenyewaan)){		
			
			idHakmilikPerjanjianTambahan = logic.getIdHakmilikByIdPermohonan(idPermohonanPerjanjianTambahan);
			
			vm = "app/php2/frmPYWPerjanjianTambahan.jsp";
			
			//MAKLUMAT PERMOHONAN
			if ("0".equals(selectedTabUpper)){
				
				if("view".equals(mode)){
					 
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
			    	
					//MAKLUMAT PERMOHONAN
					beanMaklumatPermohonan = new Vector();
					logic.setMaklumatPermohonan(idPermohonanPerjanjianTambahan);
					beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			    	this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			    	
			    	if (beanMaklumatPermohonan.size() != 0){
			    		Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
			    		idLuasKegunaan = (String) hashPermohonan.get("flagGuna");
			    		idLuas = (String) hashPermohonan.get("idLuasMohon");
			    		flagJenisPerjanjian = (String) hashPermohonan.get("flagJenisPerjanjian");
			    	}
			    	
			    	this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
			      		
				 } else if("update".equals(mode)){ 
					 	
		        	 this.context.put("readonly", "");
			    	 this.context.put("inputTextClass", "");
			    	 this.context.put("disabled", "");		
			    	 
			    	//MAKLUMAT PERMOHONAN
			    	String luasAsal = "";
			    	String keteranganLuasAsal = "";
					logic.setMaklumatPermohonan(idPermohonanPerjanjianTambahan);
					if (logic.getBeanMaklumatPermohonan().size() != 0){
			    		Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
			    		luasAsal = (String) hashPermohonan.get("luasAsal");
			    		keteranganLuasAsal = (String) hashPermohonan.get("keteranganLuasAsal");
			    	}
				    
					if ("1610198".equals(idStatusPerjanjianTambahan) && !"".equals(submit)){
						flagJenisPerjanjian = getParam("socFlagJenisPerjanjian");
					}					

				    // MAKLUMAT PERMOHONAN
					beanMaklumatPermohonan = new Vector();
					Hashtable hashPermohonan = new Hashtable();
					hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
					hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
					hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
					hashPermohonan.put("flagJenisPerjanjian", flagJenisPerjanjian);
					hashPermohonan.put("tujuan", getParam("txtTujuan") == null ? "": getParam("txtTujuan"));
					hashPermohonan.put("tarikhMulaPerjanjian", getParam("tarikhMulaPerjanjian") == null ? "": getParam("tarikhMulaPerjanjian"));
					
					hashPermohonan.put("luasAsal", luasAsal);
					hashPermohonan.put("keteranganLuasAsal", keteranganLuasAsal);			
					if ("doChangeLuas".equals(submit)){
						hashPermohonan.put("luas1", "");
						hashPermohonan.put("luas2", "");
						hashPermohonan.put("luas3", "");
						hashPermohonan.put("luasBersamaan", "");
						hashPermohonan.put("luasBaki", "");
					} else {
						hashPermohonan.put("luas1", getParam("txtLuasMohon1"));
						hashPermohonan.put("luas2", getParam("txtLuasMohon2"));
						hashPermohonan.put("luas3", getParam("txtLuasMohon3"));
						if ("1".equals(idLuasKegunaan)){
							hashPermohonan.put("luasBersamaan", luasAsal);		
							hashPermohonan.put("luasBaki", Utils.formatLuas(0D));
						} else {
							hashPermohonan.put("luasBersamaan", getParam("txtLuasBersamaan"));			
							hashPermohonan.put("luasBaki", getParam("txtBakiLuas"));		
						}
					}
					
					hashPermohonan.put("catatan", getParam("txtCatatan") == null ? "": getParam("txtCatatan"));
					beanMaklumatPermohonan.addElement(hashPermohonan);
					this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
					
					this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));
				 }				
			}
			
			//JABATAN TEKNIKAL
			if ("1".equals(selectedTabUpper)){
				
				//OPEN POPUP DETAIL MAKLUMAT JPPH
				if ("openJPPH".equals(flagPopup)){
					
					if ("new".equals(modePopup)){
						this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			if ("".equals(submit)){
		    				
		    				beanMaklumatJPPH = new Vector();    			
			    			Hashtable hashMaklumatJPPH = new Hashtable();
			    			hashMaklumatJPPH.put("tarikhHantar", "");
			    			hashMaklumatJPPH.put("jangkamasa", "");
			    			hashMaklumatJPPH.put("tarikhJangkaTerima", "");

			    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
							this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
							
							idPejabat = logic.getIdPejabatJPPH(idHakmilikPerjanjianTambahan);
							
							beanMaklumatPejabat = new Vector();
							logic.setMaklumatPejabat(idPejabat);
							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
							this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
							
		    			} else {
		    				
		    				beanMaklumatJPPH = new Vector();    			
			    			Hashtable hashMaklumatJPPH = new Hashtable();
			    			hashMaklumatJPPH.put("tarikhHantar", getParam("txtTarikhHantar"));
			    			hashMaklumatJPPH.put("jangkamasa", getParam("txtJangkaMasa"));
			    			hashMaklumatJPPH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

			    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
							this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
							
		    			}
					} else if ("newUlangan".equals(modePopup)){
						this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			if ("".equals(submit)){
		    				
		    				beanMaklumatJPPH = new Vector();    			
			    			Hashtable hashMaklumatJPPH = new Hashtable();
			    			hashMaklumatJPPH.put("tarikhHantar", "");
			    			hashMaklumatJPPH.put("jangkamasa", "");
			    			hashMaklumatJPPH.put("tarikhJangkaTerima", "");

			    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
							this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);						
							
		    			} else {
		    				
		    				beanMaklumatJPPH = new Vector();    			
			    			Hashtable hashMaklumatJPPH = new Hashtable();
			    			hashMaklumatJPPH.put("tarikhHantar", getParam("txtTarikhHantar"));
			    			hashMaklumatJPPH.put("jangkamasa", getParam("txtJangkaMasa"));
			    			hashMaklumatJPPH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

			    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
							this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
							
		    			}
		    			
		    			idPejabat = getParam("idPejabatJPPH");
		    			
		    			beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
						
					} else if ("view".equals(modePopup)){
						this.context.put("readonlyPopup", "readonly");
		    			this.context.put("inputTextClassPopup", "disabled");
						
		    			beanMaklumatJPPH = new Vector();
						logic.setMaklumatJPPH(idUlasanTeknikal);
						beanMaklumatJPPH = logic.getBeanMaklumatJPPH();
						this.context.put("BeanMaklumatJPPH",beanMaklumatJPPH);
						
						if (beanMaklumatJPPH.size() != 0){
							Hashtable hashMaklumatJPPH = (Hashtable) logic.getBeanMaklumatJPPH().get(0);
							idPejabat = (String) hashMaklumatJPPH.get("idPejabat");
							flagStatus = (String) hashMaklumatJPPH.get("flagStatus");
							aktif = (String) hashMaklumatJPPH.get("aktif");
						}
						
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
						
					} else if ("update".equals(modePopup)){					
						this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			beanMaklumatJPPH = new Vector();    			
		    			Hashtable hashMaklumatJPPH = new Hashtable();
		    			hashMaklumatJPPH.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJPPH.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJPPH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
		    			hashMaklumatJPPH.put("tarikhTerima", getParam("txtTarikhTerima"));
		    			hashMaklumatJPPH.put("tarikhSurat", getParam("txtTarikhSurat"));
		    			hashMaklumatJPPH.put("noRujukan", getParam("txtNoRujukanSurat"));
		    			hashMaklumatJPPH.put("ulasan", getParam("txtUlasan"));
		    			hashMaklumatJPPH.put("kadarSewaBulan", getParam("txtKadarSewaBulan"));
		    			hashMaklumatJPPH.put("kadarSewaTahun", getParam("txtKadarSewaTahun"));
		    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
						this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
						
						idPejabat = getParam("idPejabatJPPH");
		    			
		    			beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					}
				}
				
				//DOKUMEN JPPH
				senaraiJPPH = new Vector();
				logic.setSenaraiJPPH(idPermohonanPerjanjianTambahan);
				senaraiJPPH = logic.getListJPPH();
				this.context.put("SenaraiJPPH", senaraiJPPH);
			}
			
			//MESYUARAT
			if ("2".equals(selectedTabUpper)){
				
				//OPEN POPUP DAFTAR BARU MESYUARAT
				if ("openMesyuarat".equals(flagPopup)){
					
					//MODE NEW
		        	if ("new".equals(modePopup)){
		    				
		    			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		        		
		        		beanMaklumatMesyuarat = new Vector();    		   	
		        		Hashtable hashMesyuarat = new Hashtable();
		        		if (getParam("txtTajukMesyuarat") == null || "".equals(getParam("txtTajukMesyuarat"))){
		        			hashMesyuarat.put("tajukMesyuarat", "MESYUARAT JAWATANKUASA PENYEWAAN DAN PENGUATKUASAN HARTA TANAH PERSEKUTUAN");
		        		} else {
		        			hashMesyuarat.put("tajukMesyuarat", getParam("txtTajukMesyuarat"));
		        		}        		
		        		hashMesyuarat.put("bilMesyuarat", getParam("txtBilMesyuarat"));
		        		hashMesyuarat.put("tarikhMesyuarat", getParam("txtTarikhMesyuarat"));
		        		hashMesyuarat.put("catatan", getParam("txtCatatanMesyuarat"));
		        		hashMesyuarat.put("flagSyor", getParam("socSyor"));
		        		beanMaklumatMesyuarat.addElement(hashMesyuarat);
		    			this.context.put("BeanMaklumatMesyuarat",beanMaklumatMesyuarat);
		    			
		    			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "",""));
		    			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "",""));
		    			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "",""));
		    			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "",""));
		    			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "",""));
		    				
		        	} else if ("view".equals(modePopup)){ //MODE VIEW
		    				
		    			this.context.put("readonlyPopup", "readonly");
		    			this.context.put("inputTextClassPopup", "disabled");
		    			
		    			beanMaklumatMesyuarat = new Vector();
			   			logic.setMaklumatMesyuarat(idMesyuarat);
			   			beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
			   			this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);
			   			
			   			if (beanMaklumatMesyuarat.size() != 0){
							Hashtable hashMesyuarat = (Hashtable) logic.getBeanMaklumatMesyuarat().get(0);
							idLokasi = (String)(hashMesyuarat.get("idLokasi"));
							idJamDari = (String)(hashMesyuarat.get("idJamDari"));
							idMinitDari = (String)(hashMesyuarat.get("idMinitDari"));
							idJamHingga = (String)(hashMesyuarat.get("idJamHingga"));
							idMinitHingga = (String)(hashMesyuarat.get("idMinitHingga"));
			   			}
		    			
		    			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "disabled", " class=\"disabled\""));
		    			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "disabled", " class=\"disabled\""));
		    			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "disabled", " class=\"disabled\""));
		    			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "disabled", " class=\"disabled\""));
		    			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "disabled", " class=\"disabled\""));
		    				
		        	} else if ("update".equals(modePopup)){
		    				
		    			this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		        		
		        		beanMaklumatMesyuarat = new Vector();    		   	
		        		Hashtable hashMesyuarat = new Hashtable();
		        		hashMesyuarat.put("tajukMesyuarat", getParam("txtTajukMesyuarat"));
		        		hashMesyuarat.put("bilMesyuarat", getParam("txtBilMesyuarat"));
		        		hashMesyuarat.put("tarikhMesyuarat", getParam("txtTarikhMesyuarat"));
		        		hashMesyuarat.put("catatan", getParam("txtCatatanMesyuarat"));
		        		hashMesyuarat.put("flagSyor", getParam("socSyor"));
		        		beanMaklumatMesyuarat.addElement(hashMesyuarat);
		    			this.context.put("BeanMaklumatMesyuarat",beanMaklumatMesyuarat);
		    			
		    			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "",""));
		    			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "",""));
		    			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "",""));
		    			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "",""));
		    			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "",""));
		    				
		        	}
				}
							
				//SENARAI MESYUARAT
				senaraiMesyuarat = new Vector();
		        logic.setSenaraiMesyuarat(idPermohonanPerjanjianTambahan);
				senaraiMesyuarat = logic.getListMesyuarat();
				this.context.put("SenaraiMesyuarat",senaraiMesyuarat);
			}
			
			//KEPUTUSAN
			if ("3".equals(selectedTabUpper)){
				
				idPerjanjian = logic.getIdPerjanjianByIdPermohonan(idPermohonanPerjanjianTambahan);

		        if("view".equals(mode)){
					 
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
					 
					//MAKLUMAT KEPUTUSAN
					beanMaklumatKeputusan = new Vector();
					logic.setMaklumatKeputusan(idPermohonanPerjanjianTambahan);
					beanMaklumatKeputusan = logic.getBeanMaklumatKeputusan();
			    	this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
			    	
			    	//MAKLUMAT PERJANJIAN
					beanMaklumatPerjanjian = new Vector();
					logic.setMaklumatPerjanjian(idPerjanjian);
					beanMaklumatPerjanjian = logic.getBeanMaklumatPerjanjian();
			    	this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
			      		
				 } else if("update".equals(mode)){ 
					 	
		        	 this.context.put("readonly", "");
			    	 this.context.put("inputTextClass", "");
			    	 this.context.put("disabled", "");
			    
			    	//MAKLUMAT KEPUTUSAN
			    	beanMaklumatKeputusan = new Vector();
			    	logic.setMaklumatKeputusan(idPermohonanPerjanjianTambahan);
				    Hashtable hashMaklumatKeputusanDB = (Hashtable) logic.getBeanMaklumatKeputusan().get(0);
					Hashtable hashMaklumatKeputusan = new Hashtable();
					hashMaklumatKeputusan.put("keputusan", getParam("socKeputusan"));	
					hashMaklumatKeputusan.put("tarikhKeputusan", getParam("txtTarikhKeputusan"));	
					beanMaklumatKeputusan.addElement(hashMaklumatKeputusan);
					this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
					
					if ("1610206".equals(idStatusPerjanjianTambahan)){
						idKeputusan = getParam("socKeputusan");
					}			
					
					//MAKLUMAT PERJANJIAN
			    	beanMaklumatPerjanjian = new Vector();
					Hashtable hashMaklumatPerjanjian = new Hashtable();	
					hashMaklumatPerjanjian.put("tarikhMula", getParam("txtTarikhMula"));	
					hashMaklumatPerjanjian.put("kadarSewa", getParam("txtKadarSewa"));
					hashMaklumatPerjanjian.put("cagaran", getParam("txtCagaran"));	
					
					beanMaklumatPerjanjian.addElement(hashMaklumatPerjanjian);
					this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
				}		        
			}
			
			//PERJANJIAN
			if ("4".equals(selectedTabUpper)){
				
				idPerjanjian = logic.getIdPerjanjianByIdPermohonan(idPermohonanPerjanjianTambahan);

		        if("view".equals(mode)){
					 
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
			    	
			    	//MAKLUMAT PERJANJIAN
					beanMaklumatPerjanjian = new Vector();
					logic.setMaklumatPerjanjian(idPerjanjian);
					beanMaklumatPerjanjian = logic.getBeanMaklumatPerjanjian();
			    	this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
			      		
				 } else if("update".equals(mode)){ 
					 	
		        	 this.context.put("readonly", "");
			    	 this.context.put("inputTextClass", "");
			    	 this.context.put("disabled", "");		
			    	 
			    	//MAKLUMAT PERJANJIAN
					beanMaklumatPerjanjian = new Vector();
					logic.setMaklumatPerjanjian(idPerjanjian);
					beanMaklumatPerjanjian = logic.getBeanMaklumatPerjanjian();
				    this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
				 }
			}
			
			//MAKLUMBALAS
			if ("5".equals(selectedTabUpper)){
				
				idPerjanjian = logic.getIdPerjanjianByIdPermohonan(idPermohonanPerjanjianTambahan);

		        if("view".equals(mode)){
					 
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
			    	
					//MAKLUMBALAS
					beanMaklumatMaklumbalas = new Vector();
					logic.setMaklumatMaklumbalas(idPerjanjian);
					beanMaklumatMaklumbalas = logic.getBeanMaklumatMaklumbalas();
			    	this.context.put("BeanMaklumatMaklumbalas", beanMaklumatMaklumbalas);
			      		
				 } else if("update".equals(mode)){ 
					 	
		        	 this.context.put("readonly", "");
			    	 this.context.put("inputTextClass", "");
			    	 this.context.put("disabled", "");		
			    	 
			    	//MAKLUMBALAS
					beanMaklumatMaklumbalas = new Vector();
					logic.setMaklumatMaklumbalas(idPerjanjian);
					beanMaklumatMaklumbalas = logic.getBeanMaklumatMaklumbalas();
				    this.context.put("BeanMaklumatMaklumbalas", beanMaklumatMaklumbalas);
				 }		    	
			}
			
		} else if ("daftarBaru".equals(actionPenyewaan)){
			
			vm = "app/php2/frmPYWDaftarManualPerjanjianTambahan.jsp"; 
			
			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");
			
			//MAKLUMAT LUAS TANAH
			String luasAsal = "";
			String keteranganLuasAsal = "";
			logic.setMaklumatSewa(idPermohonan);
			if (logic.getBeanMaklumatSewa().size() != 0){
				Hashtable hashMaklumatSewa = (Hashtable) logic.getBeanMaklumatSewa().get(0);
				luasAsal = (String) hashMaklumatSewa.get("luasAsal");
				keteranganLuasAsal = (String) hashMaklumatSewa.get("keteranganLuasAsal");
			}
			
			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
			hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
			hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
			hashPermohonan.put("flagJenisPerjanjian", getParam("socFlagJenisPerjanjian") == null ? "": getParam("socFlagJenisPerjanjian"));
			hashPermohonan.put("tujuan", getParam("txtTujuan") == null ? "": getParam("txtTujuan"));
			hashPermohonan.put("tarikhMulaPerjanjian", getParam("tarikhMulaPerjanjian") == null ? "": getParam("tarikhMulaPerjanjian"));
			
			hashPermohonan.put("luasAsal", luasAsal);
			hashPermohonan.put("keteranganLuasAsal", keteranganLuasAsal);			
			if ("doChangeLuas".equals(submit)){
				hashPermohonan.put("luas1", "");
				hashPermohonan.put("luas2", "");
				hashPermohonan.put("luas3", "");
				hashPermohonan.put("luasBersamaan", "");
				hashPermohonan.put("luasBaki", "");
			} else {
				hashPermohonan.put("luas1", getParam("txtLuasMohon1"));
				hashPermohonan.put("luas2", getParam("txtLuasMohon2"));
				hashPermohonan.put("luas3", getParam("txtLuasMohon3"));
				if ("1".equals(idLuasKegunaan)){
					hashPermohonan.put("luasBersamaan", luasAsal);		
					hashPermohonan.put("luasBaki", Utils.formatLuas(0D));
				} else {
					hashPermohonan.put("luasBersamaan", getParam("txtLuasBersamaan"));			
					hashPermohonan.put("luasBaki", getParam("txtBakiLuas"));		
				}
			}
			
			hashPermohonan.put("catatan", getParam("txtCatatan") == null ? "": getParam("txtCatatan"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));
			
			flagJenisPerjanjian = getParam("socFlagJenisPerjanjian");
			
		} else {
			
			vm = "app/php2/frmPYWSenaraiPerjanjianTambahan.jsp"; 
			
			//SENARAI PERJANJIAN TAMBAHAN
			senaraiPerjanjianTambahan = new Vector();
			logic.setSenaraiPerjanjianTambahan(idFail);
			senaraiPerjanjianTambahan = logic.getListPerjanjianTambahan();
			this.context.put("SenaraiPerjanjianTambahan", senaraiPerjanjianTambahan);
		}
		
        //SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("actionPenyewaan", actionPenyewaan);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idUrusan", idUrusan);
        this.context.put("idSuburusan", idSuburusan);
        this.context.put("idStatus", idStatus);      
        this.context.put("flagAktif", flagAktif);
        
        this.context.put("idPermohonanPerjanjianTambahan", idPermohonanPerjanjianTambahan);
        this.context.put("idStatusPerjanjianTambahan", idStatusPerjanjianTambahan);  
        this.context.put("flagJenisPerjanjian", flagJenisPerjanjian);
        this.context.put("idPejabat", idPejabat);
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idMesyuarat", idMesyuarat);
        this.context.put("flagStatus", flagStatus);
        this.context.put("aktif", aktif);
        this.context.put("idKeputusan", idKeputusan);
        this.context.put("idPerjanjian", idPerjanjian);
        
        this.context.put("idLuasKegunaan", idLuasKegunaan);
        this.context.put("idLuas", idLuas);
		
		return vm;
	}
	
}
