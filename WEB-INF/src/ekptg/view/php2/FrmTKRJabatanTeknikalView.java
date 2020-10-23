/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmTKRHeaderData;
import ekptg.model.php2.FrmTKRJabatanTeknikalData;
import ekptg.model.php2.online.FrmTKROnlineKJPSenaraiFailData;

public class FrmTKRJabatanTeknikalView extends AjaxBasedModule {
	
private static final long serialVersionUID = 1L;
	
	FrmTKRHeaderData logicHeader = new FrmTKRHeaderData();
	FrmTKRJabatanTeknikalData logic = new FrmTKRJabatanTeknikalData();
	FrmTKROnlineKJPSenaraiFailData logicKJP = new FrmTKROnlineKJPSenaraiFailData();

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	  //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String submit = getParam("command");  
	    String vm = ""; 
	    String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String selectedTabUpper = (String)getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
		
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");        
        String idUlasanTeknikal = getParam("idUlasanTeknikal");
        String idKertasKerja = getParam("idKertasKerja");
        String idNegeriTanah = getParam("idNegeriTanah");
        String idKementerianTanah = getParam("idKementerianTanah");
        String idAgensiTanah = getParam("idAgensiTanah");
       
        String flagStatus = getParam("flagStatus");
        String flagAktif = getParam("flagAktif");
        
        String keputusan = "";
        
        //GET DROPDOWN PARAM
        String idDokumen = getParam("socDokumen");
		if (idDokumen == null || idDokumen.trim().length() == 0){
			idDokumen = "99999";
		}
        String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}		
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}	
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0){
			idPejabat = "99999";
		}
		String idPejabatJPPH = getParam("socPejabatJPPH");
		if (idPejabatJPPH == null || idPejabatJPPH.trim().length() == 0){
			idPejabatJPPH = "99999";
		}
		
		//VECTOR
        Vector beanHeader = null;
        Vector senaraiKJT = null;
        Vector beanMaklumatKJT = null;
        Vector senaraiKJP = null;
        Vector senaraiTanahPohon = null;
        Vector senaraiTanahGanti = null;
        Vector beanMaklumatKJP = null;
        Vector beanMaklumatPejabat = null;
        Vector senaraiJPPH = null;
        Vector beanMaklumatJPPH = null;
        Vector beanMaklumatLampiran = null;
        this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);
        
        String step = getParam("step");
        
        vm = "app/php2/frmTKRJabatanTeknikal.jsp";
        
        //SEND TO MODEL
        if (postDB) {
        	if ("simpanMaklumatKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatKJT(idPermohonan, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganKJT(idUlasanTeknikal, idPermohonan, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatKJT".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), session);
    		}
        	if ("simpanMaklumatKJP".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatKJP(idPermohonan, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		
        		if("1".equals(idDokumen) && !"".equals(idDokumen)){
            		logic.sendEmailtoKJP(idPermohonan, idKementerianTanah, session);
            		
        		} else if("4".equals(idDokumen) && !"".equals(idDokumen)){
        			logic.sendEmailtoPejabatJKPTG(idUlasanTeknikal, session);
        		}
    		}
        	if ("simpanMaklumatUlanganKJP".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganKJP(idUlasanTeknikal, idPermohonan, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		
        		if("1".equals(idDokumen) && !"".equals(idDokumen)){
            		logic.sendEmailtoKJP(idPermohonan, idKementerianTanah, session);
            		
        		} else if("4".equals(idDokumen) && !"".equals(idDokumen)){
        			logic.sendEmailtoPejabatJKPTG(idUlasanTeknikal, session);
        		}
    		}
        	if ("simpanKemaskiniMaklumatKJP".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJP(idUlasanTeknikal, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), getParam("socKeputusan"), session);
    		}
    		if ("hapusMaklumatKJPKJT".equals(hitButton)){
    			logic.hapusMaklumatKJPKJT(idUlasanTeknikal, session);
    		} 
        	if ("doSimpanKemaskiniMaklumatCadangan".equals(hitButton)){
				logic.updateKertasCadangan(idKertasKerja, getParam("txtTajukKertas"), getParam("txtTujuan"),
						getParam("txtLatarBelakangTanah"), getParam("txtLaporanNilaian"), getParam("txtCadanganPembangunan"),
						getParam("txtPemohon"),getParam("txtPerakuanPTP"), session);
			}
        	if ("doSimpanKemaskiniMaklumatNilaian".equals(hitButton)){
        		simpanKemaskiniMaklumatNilaian(idPermohonan, session);
			}
        	if ("simpanMaklumatJPPH".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJPPH(idPermohonan, idPejabatJPPH, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJPPH".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJPPH(idUlasanTeknikal, idPermohonan, idPejabatJPPH, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJPPH".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatJPPH(idUlasanTeknikal, idPejabatJPPH, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"),
        				session);
    		}
        	if ("doSeterusnya".equals(hitButton)){
        		logic.updateStatus(idFail, idPermohonan, session);
        	}
        	if ("doBatalPermohonan".equals(hitButton)){
    			logic.doBatalPermohonan(idFail, idPermohonan, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
        }

      //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		if (logicHeader.getBeanMaklumatPermohonan().size() != 0){
			Hashtable hashPermohonan = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashPermohonan.get("idFail");
			idPermohonan = (String) hashPermohonan.get("idPermohonan");
			idStatus = (String) hashPermohonan.get("idStatus");
		}
		
		//MAKLUMAT HAKMILIK
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			idNegeriTanah = (String) hashHakmilik.get("idNegeri");		
			idKementerianTanah = (String) hashHakmilik.get("idKementerian");			
			idAgensiTanah = (String) hashHakmilik.get("idAgensi");			
		}

		// MODE VIEW
		if("view".equals(mode)){
			
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			
			//OPEN POPUP DETAIL MAKLUMAT KJT
			if ("openKJT".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatKJT = new Vector();    			
		    			Hashtable hashMaklumatKJT = new Hashtable();
		    			hashMaklumatKJT.put("tarikhHantar", "");
		    			hashMaklumatKJT.put("jangkamasa", "");
		    			hashMaklumatKJT.put("tarikhJangkaTerima", "");

		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
						
						idDokumen = "99999";
						idKementerian = "99999";
						idKementerian = "99999";
						idNegeri = "99999";
						idPejabat = "99999";
						
	    			} else {
	    				
	    				beanMaklumatKJT = new Vector();    			
		    			Hashtable hashMaklumatKJT = new Hashtable();
		    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
	    			}
	    			
	    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", ""));
					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatKJT = new Vector();    			
		    			Hashtable hashMaklumatKJT = new Hashtable();
		    			hashMaklumatKJT.put("tarikhHantar", "");
		    			hashMaklumatKJT.put("jangkamasa", "");
		    			hashMaklumatKJT.put("tarikhJangkaTerima", "");

		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
						
	    			} else {
	    				
	    				beanMaklumatKJT = new Vector();    			
		    			Hashtable hashMaklumatKJT = new Hashtable();
		    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
	    			}
	    			
	    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatKJT = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatKJT = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatKJT",beanMaklumatKJT);
					
					if (beanMaklumatKJT.size() != 0){
						Hashtable hashMaklumatKJT = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						idDokumen = (String) hashMaklumatKJT.get("idDokumen");
						idKementerian = (String) hashMaklumatKJT.get("idKementerian");
						idAgensi = (String) hashMaklumatKJT.get("idAgensi");
						idNegeri = (String) hashMaklumatKJT.get("idNegeri");
						idPejabat = (String) hashMaklumatKJT.get("idPejabat");
						flagStatus = (String) hashMaklumatKJT.get("flagStatus");
						flagAktif = (String) hashMaklumatKJT.get("flagAktif");
					}
					this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					
	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatKJT = new Vector();    			
	    			Hashtable hashMaklumatKJT = new Hashtable();
	    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
	    			hashMaklumatKJT.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatKJT.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatKJT.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatKJT.put("ulasan", getParam("txtUlasan"));
	    			beanMaklumatKJT.addElement(hashMaklumatKJT);
					this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
	    			
					if ("1".equals(flagStatus)){
						this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
		    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", ""));
						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
							idNegeri = idNegeriTanah;
						}
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));
					} else {
						this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
		    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
							idNegeri = idNegeriTanah;
						}
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					}					
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				}
			}
			
			//OPEN POPUP DETAIL MAKLUMAT KJP
			if ("openKJP".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
		    			/*hashMaklumatKJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianTanah));
		    			hashMaklumatKJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiTanah));*/
		    			hashMaklumatKJP.put("tarikhHantar", "");
		    			hashMaklumatKJP.put("jangkamasa", "");
		    			hashMaklumatKJP.put("tarikhJangkaTerima", "");

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
						
						//add by Ain 13072017 
						idDokumen = "99999";
						idKementerian = "99999";
						idAgensi = "99999";
						idNegeri = "99999";
						idPejabat = "99999";
						
	    			} else {
	    				
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
		    			/*hashMaklumatKJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianTanah));
		    			hashMaklumatKJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiTanah));*/
		    			hashMaklumatKJP.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatKJP.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatKJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
						
	    			}
	    			
	    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", ""));
					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
	    			
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");

	    			if ("".equals(submit)){
	    				
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
		    			/*hashMaklumatKJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianTanah));
		    			hashMaklumatKJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiTanah));*/
		    			hashMaklumatKJP.put("tarikhHantar", "");
		    			hashMaklumatKJP.put("jangkamasa", "");
		    			hashMaklumatKJP.put("tarikhJangkaTerima", "");

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
						
	    			} else {
	    				
	    				beanMaklumatKJP = new Vector();    			
		    			Hashtable hashMaklumatKJP = new Hashtable();
		    			/*hashMaklumatKJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianTanah));
		    			hashMaklumatKJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiTanah));*/
		    			hashMaklumatKJP.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatKJP.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatKJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatKJP.addElement(hashMaklumatKJP);
						this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
						
	    			}	
	    			
	    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
	    			
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatKJP = new Vector();
					logic.setMaklumatKJP(idUlasanTeknikal, logicKJP.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
					beanMaklumatKJP = logic.getBeanMaklumatKJP();
					this.context.put("BeanMaklumatKJP",beanMaklumatKJP);
					
					if (beanMaklumatKJP.size() != 0){
						Hashtable hashMaklumatKJP = (Hashtable) logic.getBeanMaklumatKJP().get(0);
						flagStatus = (String) hashMaklumatKJP.get("flagStatus");
						flagAktif = (String) hashMaklumatKJP.get("flagAktif");
						
						keputusan = (String) hashMaklumatKJP.get("flagKeputusan");
						
						idDokumen = (String) hashMaklumatKJP.get("idDokumen");
						idKementerian = (String) hashMaklumatKJP.get("idKementerian");
						idAgensi = (String) hashMaklumatKJP.get("idAgensi");
						idNegeri = (String) hashMaklumatKJP.get("idNegeri");
						idPejabat = (String) hashMaklumatKJP.get("idPejabat");
					}
					
					this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					
	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatKJP = new Vector();    			
	    			Hashtable hashMaklumatKJP = new Hashtable();
	    			/*hashMaklumatKJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianTanah));
	    			hashMaklumatKJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiTanah));*/
	    			hashMaklumatKJP.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatKJP.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatKJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));	    			
	    			hashMaklumatKJP.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatKJP.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatKJP.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatKJP.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatKJP.put("flagKeputusan", getParam("socKeputusan"));
	    			beanMaklumatKJP.addElement(hashMaklumatKJP);
					this.context.put("BeanMaklumatKJP", beanMaklumatKJP);
					
					if ("1".equals(flagStatus)){
						this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
		    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", ""));
						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
							idNegeri = idNegeriTanah;
						}
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));
					} else {
						this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
		    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
							idNegeri = idNegeriTanah;
						}
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					}					
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				}
			}
			
			//OPEN POPUP DETAIL MAKLUMAT JPPH
			if ("openJPPH".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				idPejabatJPPH = "99999";
	    				
	    				beanMaklumatJPPH = new Vector();    			
		    			Hashtable hashMaklumatJPPH = new Hashtable();
		    			hashMaklumatJPPH.put("tarikhHantar", "");
		    			hashMaklumatJPPH.put("jangkamasa", "");
		    			hashMaklumatJPPH.put("tarikhJangkaTerima", "");

		    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
						this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
						
						this.context.put("selectPejabatJPPH", HTML.SelectPejabatJPPHPelepasan(idPermohonan, "socPejabatJPPH", Utils.parseLong(idPejabatJPPH), "", "onChange=\"doChangePejabatJPPH();\""));

	    			} else {
	    				
	    				beanMaklumatJPPH = new Vector();    			
		    			Hashtable hashMaklumatJPPH = new Hashtable();
		    			hashMaklumatJPPH.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJPPH.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJPPH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
						this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
						
						this.context.put("selectPejabatJPPH", HTML.SelectPejabatJPPHPelepasan(idPermohonan, "socPejabatJPPH", Utils.parseLong(idPejabatJPPH), "", "onChange=\"doChangePejabatJPPH();\""));
												
	    			}

	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabatJPPH);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
	    			
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
						
						this.context.put("selectPejabatJPPH", HTML.SelectPejabatJPPHPelepasan(idPermohonan, "socPejabatJPPH", Utils.parseLong(idPejabatJPPH), "disabled", " class=\"disabled\""));
						
	    			} else {
	    				
	    				beanMaklumatJPPH = new Vector();    			
		    			Hashtable hashMaklumatJPPH = new Hashtable();
		    			hashMaklumatJPPH.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJPPH.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJPPH.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
						this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
						
						this.context.put("selectPejabatJPPH", HTML.SelectPejabatJPPHPelepasan(idPermohonan, "socPejabatJPPH", Utils.parseLong(idPejabatJPPH), "disabled", " class=\"disabled\""));
	    			}
	    				    			
	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabatJPPH);
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
						idPejabatJPPH = (String) hashMaklumatJPPH.get("idPejabat");
						flagStatus = (String) hashMaklumatJPPH.get("flagStatus");
						flagAktif = (String) hashMaklumatJPPH.get("flagAktif");
						
					}
					this.context.put("selectPejabatJPPH", HTML.SelectPejabatJPPHPelepasan(idPermohonan, "socPejabatJPPH", Utils.parseLong(idPejabatJPPH), "disabled", " class=\"disabled\""));
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabatJPPH);
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
	    			beanMaklumatJPPH.addElement(hashMaklumatJPPH);
					this.context.put("BeanMaklumatJPPH", beanMaklumatJPPH);
					
					this.context.put("selectPejabatJPPH", HTML.SelectPejabatJPPHPelepasan(idPermohonan, "socPejabatJPPH", Utils.parseLong(idPejabatJPPH), "", " onChange=\"doChangePejabatJPPH()\" style=\"width:auto\""));
	    			
	    			beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabatJPPH);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
				}
			}
			
			//DOKUMEN KJT
			senaraiKJT = new Vector();
			logic.setSenaraiKJT(idPermohonan);
			senaraiKJT = logic.getListKJT();
			this.context.put("SenaraiKJT", senaraiKJT);
			
			//DOKUMEN KJP
			senaraiKJP = new Vector();
			logic.setSenaraiKJP(idPermohonan);
			senaraiKJP = logic.getListKJP();
			this.context.put("SenaraiKJP", senaraiKJP);
			
			//NILAIAN TANAH DIPOHON
			senaraiTanahPohon = new Vector();
			logic.setSenaraiTanahPohon(idPermohonan);
			senaraiTanahPohon = logic.getListTanahPohon();
			this.context.put("SenaraiTanahPohon", senaraiTanahPohon);
			
			//NILAIAN TANAH GANTI
			senaraiTanahGanti = new Vector();
			logic.setSenaraiTanahGanti(idPermohonan);
			senaraiTanahGanti = logic.getListTanahGanti();
			this.context.put("SenaraiTanahGanti", senaraiTanahGanti);
			
			//DOKUMEN JPPH
			senaraiJPPH = new Vector();
			logic.setSenaraiJPPH(idPermohonan);
			senaraiJPPH = logic.getListJPPH();
			this.context.put("SenaraiJPPH", senaraiJPPH);
			
			//LAMPIRAN ULASAN TEKNIKAL
			beanMaklumatLampiran = new Vector();
			beanMaklumatLampiran = logic.getBeanMaklumatLampiran(idUlasanTeknikal);
			this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);
			
		} else if(mode.equals("update")){
			
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			
			//NILAIAN TANAH DIPOHON
			senaraiTanahPohon = new Vector();
			logic.setSenaraiTanahPohon(idPermohonan);
			senaraiTanahPohon = logic.getListTanahPohon();
			this.context.put("SenaraiTanahPohon", senaraiTanahPohon);
			
			//NILAIAN TANAH GANTI
			senaraiTanahGanti = new Vector();
			logic.setSenaraiTanahGanti(idPermohonan);
			senaraiTanahGanti = logic.getListTanahGanti();
			this.context.put("SenaraiTanahGanti", senaraiTanahGanti);
			
			//LAMPIRAN ULASAN TEKNIKAL
			beanMaklumatLampiran = new Vector();
			beanMaklumatLampiran = logic.getBeanMaklumatLampiran(idUlasanTeknikal);
			this.context.put("BeanMaklumatLampiran",beanMaklumatLampiran);
		}
		
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }
		
		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idStatus", idStatus);
        this.context.put("idNegeriTanah", idNegeriTanah);
        this.context.put("idKementerianTanah", idKementerianTanah);
        this.context.put("idAgensiTanah", idAgensiTanah);
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idKertasKerja", idKertasKerja);
        this.context.put("idDokumen", idDokumen);
        
        this.context.put("flagStatus", flagStatus);
        this.context.put("flagAktif", flagAktif);
        this.context.put("keputusan", keputusan);

        this.context.put("step",step);
        
		return vm;
	}

	private void simpanKemaskiniMaklumatNilaian(String idPermohonan, HttpSession session) throws Exception {
		//TANAH POHON
		String idHakmilikPermohonan[] = request.getParameterValues("idHakmilikPermohonan");
		String txtNilaiTanahPohon[] = request.getParameterValues("txtNilaiTanahPohon");
		String txtNilaiBangunanPohon[] = request.getParameterValues("txtNilaiBangunanPohon");
		if (idHakmilikPermohonan != null) {
			for (int i = 0; i < idHakmilikPermohonan.length; i++) {
				logic.updateNilaianTanahPohon(idHakmilikPermohonan[i], txtNilaiTanahPohon[i], txtNilaiBangunanPohon[i], session);
			}
		}
		
		//TANAH GANTI
		String idTanahGanti[] = request.getParameterValues("idTanahGanti");
		String txtNilaiTanahGanti[] = request.getParameterValues("txtNilaiTanahGanti");
		String txtNilaiBangunanGanti[] = request.getParameterValues("txtNilaiBangunanGanti");
		if (idTanahGanti != null) {
			for (int i = 0; i < idTanahGanti.length; i++) {
				logic.updateNilaianTanahGanti(idTanahGanti[i], txtNilaiTanahGanti[i], txtNilaiBangunanGanti[i], session);
			}
		}
	}
}
