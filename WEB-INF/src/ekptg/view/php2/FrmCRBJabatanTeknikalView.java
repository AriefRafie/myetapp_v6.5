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
import ekptg.model.php2.FrmCRBHeaderData;
import ekptg.model.php2.FrmCRBJabatanTeknikalData;

public class FrmCRBJabatanTeknikalView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmCRBHeaderData logicHeader = new FrmCRBHeaderData();
	FrmCRBJabatanTeknikalData logic = new FrmCRBJabatanTeknikalData();

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
        String idNegeriTanah = "";
        String idKementerianTanah = getParam("idKementerianTanah");
        String idAgensiTanah = getParam("idAgensiTanah");
        String flagStatus = getParam("flagStatus");
        String flagAktif = getParam("flagAktif");
        String flagSuratKe = getParam("idSuratKe");
        String idHakmilik = "";
        
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
		String idPBT = getParam("socPBT");
		if (idPBT == null || idPBT.trim().length() == 0){
			idPBT = "99999";
		}
		
        //VECTOR
        Vector beanHeader = null;
        Vector senaraiKJT = null;
        Vector beanMaklumatPBT = null;
        Vector beanMaklumatKJT = null;
        Vector beanMaklumatPejabat = null;
        Vector beanMaklumatAgensi = null;
        Vector senaraiPBT = null;
        Vector beanMaklumatNotis425 = null;
        Vector senaraiNotis425 = null;
        Vector senaraiNotis = null;
        Vector beanMaklumatNotis = null;
        
        boolean flagOpenDetail = false;
		String status = "";
		
		String step = getParam("step");
        
        vm = "app/php2/frmCRBJabatanTeknikal.jsp";
        
        //SEND TO MODEL
        if (postDB) {
        	if ("simpanMaklumatKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatKJT(idPermohonan, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"),getParam("idSuratKe"), idKementerianTanah, idAgensiTanah, session);
    		}
        	if ("simpanMaklumatUlanganKJT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganKJT(idUlasanTeknikal, idPermohonan, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"),getParam("idSuratKe"),idKementerianTanah, idAgensiTanah, session);
    		}
        	if ("simpanKemaskiniMaklumatKJT".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, idDokumen, idKementerian, idAgensi, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"),getParam("idSuratKe"),idKementerianTanah, idAgensiTanah,
        				getParam("txtNamaPegawai"),getParam("txtJawatan"),session);
    		}
    		if ("hapusMaklumatKJPKJT".equals(hitButton)){
    			logic.hapusMaklumatKJPKJT(idUlasanTeknikal, session);
    		} 
    		if ("simpanMaklumatPBT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatPBT(idPermohonan, idPBT, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
    		if ("simpanMaklumatUlanganPBT".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganPBT(idUlasanTeknikal, idPermohonan,idPBT, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
    		if ("simpanKemaskiniMaklumatPBT".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatPBT(idUlasanTeknikal,idPBT, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"), 
        				getParam("txtNamaPegawai"),getParam("txtJawatan"),session);
    		}
    		if ("simpanMaklumatNotis425".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatNotis425(idPermohonan,idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"),getParam("idSuratKe"), idKementerianTanah, idAgensiTanah, session);
    		}
    		if ("simpanMaklumatUlanganNotis425".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlangaNotis425(idUlasanTeknikal, idPermohonan, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"),getParam("idSuratKe"),idKementerianTanah, idAgensiTanah, session);
    		}
    		if ("simpanKemaskiniMaklumatNotis425".equals(hitButton)){
        		logic.simpanKemaskiniMaklumaNotis425(idUlasanTeknikal,idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"),getParam("idSuratKe"),idKementerianTanah, idAgensiTanah,
        				getParam("txtNamaPegawai"),getParam("txtJawatan"),session);
    		}
    		if ("simpanMaklumatNotis".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatNotis(idPermohonan, idDokumen, getParam("idPejabat"), getParam("txtTarikhHantar"), session);
    		}
        	if ("simpanKemaskiniMaklumatNotis".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatNotis(idUlasanTeknikal, idDokumen, getParam("idPejabat"), getParam("txtTarikhHantar"), session);
    		}
        	if ("hapusMaklumatNotis".equals(hitButton)){
    			logic.hapusMaklumatNotis(idUlasanTeknikal, session);
    		}
           	if ("doSeterusnya".equals(hitButton)){
        		logic.updateStatus(idFail, idPermohonan, session);
        	}
           	
           	if ("doSelesaiPermohonan".equals(hitButton)){
				logicHeader.doSelesaiPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhSelesai"), getParam("txtSebab"), session);
    			step = "";
    		}
			if ("doBatalPermohonan".equals(hitButton)){
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
        }

        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail, session);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String)hashHeader.get("idFail");
			idPermohonan = (String)hashHeader.get("idPermohonan");
			idStatus = (String)hashHeader.get("idStatus");
			status = (String) hashHeader.get("status");		
			idNegeriTanah = (String)hashHeader.get("idNegeriTanah");
			idKementerianTanah = (String)hashHeader.get("idKementerianTanah");
			idAgensiTanah = (String)hashHeader.get("idAgensiTanah");
			idHakmilik = (String)hashHeader.get("idHakmilik");
		}
		
		//GET FLAG OPEN DETAIL
		flagOpenDetail =  logicHeader.getFlagOpenDetail(idStatus, 4); //4 = JABATAN TEKNIKAL

		// MODE VIEW
		if("view".equals(mode)){
			
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			
//			//OPEN POPUP DETAIL MAKLUMAT KJT
//			if ("openKJT".equals(flagPopup)){
//				
//				if ("new".equals(modePopup)){
//					this.context.put("readonlyPopup", "");
//	    			this.context.put("inputTextClassPopup", "");
//	    			this.context.put("disabled", "");
//	     			this.context.put("idSuratKe", "");
//	     			this.context.put("readonlyS", "");
//	    			this.context.put("inputTextClassS", "");
//	    			
//	     			if("doChangeNegeri".equals(submit)){
//	     				idPejabat = "99999";
//	     			}
//	     			
//	    			if ("".equals(submit)){
//	    				
//	    				beanMaklumatKJT = new Vector();    			
//		    			Hashtable hashMaklumatKJT = new Hashtable();
//		    			hashMaklumatKJT.put("tarikhHantar", "");
//		    			hashMaklumatKJT.put("jangkamasa", "");
//		    			hashMaklumatKJT.put("tarikhJangkaTerima", "");
//		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
//						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
//						
//						idDokumen = "99999";
//						idKementerian = "99999";
//						idAgensi = "99999";
//						idNegeri = "99999";
//						idPejabat = "99999";
//						
//	    			} else {
//	    				
//	    				beanMaklumatKJT = new Vector();    			
//		    			Hashtable hashMaklumatKJT = new Hashtable();
//		    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
//		    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
//		    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
//		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
//						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
//	    			}
//	    			
//	    			if ("doChangeSuratKe".equals(submit)){
//						this.context.put("idSuratKe", getParam("idSuratKe"));
//						idKementerian = "99999";
//						idAgensi = "99999";
//						idNegeri = "99999";
//						idPejabat = "99999";
//					}
//	    			
//	    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
//	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
//					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
//					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
//						idNegeri = idNegeriTanah;
//					}
//					
//					if("1".equals(idDokumen)){
//						if("PTG".equals(flagSuratKe)){
//							this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
//							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "1"));
//							
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabat(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//						} else if ("PTD".equals(flagSuratKe)){
//							this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
//							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
//							
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabat(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//						} else if ("JKPTG".equals(flagSuratKe)){
//							this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
//							this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri));
//							
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabatJKPTG(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//						} else if ("KJP".equals(flagSuratKe)){
//							
//							beanMaklumatAgensi = new Vector();
//							logic.setMaklumatAgensi(idAgensiTanah);
//							beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//							this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
//							
//						} else if ("KJT".equals(flagSuratKe)){
//							
//							beanMaklumatAgensi = new Vector();
//							logic.setMaklumatAgensi(idAgensi);
//							beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//							this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);							
//						}
//					}
//					
//					if("4".equals(idDokumen)){
//						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
//						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri));
//						
//						beanMaklumatPejabat = new Vector();
//						logic.setMaklumatPejabatJKPTG(idPejabat);
//						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//					}
//				
//				} else if ("newUlangan".equals(modePopup)){
//					this.context.put("readonlyPopup", "");
//	    			this.context.put("inputTextClassPopup", "");
//	    			this.context.put("disabled", "");
//	    			this.context.put("readonlyS", "readonly");
//	    			this.context.put("inputTextClassS", "disabled");
//	    			
//	    			if ("".equals(submit)){
//	    				
//	    				beanMaklumatKJT = new Vector();    			
//		    			Hashtable hashMaklumatKJT = new Hashtable();
//		    			hashMaklumatKJT.put("tarikhHantar", "");
//		    			hashMaklumatKJT.put("jangkamasa", "");
//		    			hashMaklumatKJT.put("tarikhJangkaTerima", "");
//		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
//						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
//						
//	    			} else {
//	    				
//	    				beanMaklumatKJT = new Vector();    			
//		    			Hashtable hashMaklumatKJT = new Hashtable();
//		    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
//		    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
//		    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
//		    			beanMaklumatKJT.addElement(hashMaklumatKJT);
//						this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
//	    			}
//	    			
//	    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
//	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
//					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
//					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
//						idNegeri = idNegeriTanah;
//					}
//					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
//					
//					if("1".equals(idDokumen)){
//						if("PTG".equals(flagSuratKe)){
//							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "1"));
//							
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabat(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//						} else if ("PTD".equals(flagSuratKe)){
//							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "2"));
//							
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabat(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//						} else if ("JKPTG".equals(flagSuratKe)){
//							this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
//														
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabatJKPTG(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//						
//						} else if ("KJP".equals(flagSuratKe)){
//							
//							beanMaklumatAgensi = new Vector();
//							logic.setMaklumatAgensi(idAgensiTanah);
//							beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//							this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
//							
//						} else if ("KJT".equals(flagSuratKe)){
//							
//							beanMaklumatAgensi = new Vector();
//							logic.setMaklumatAgensi(idAgensi);
//							beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//							this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);							
//						}
//					}
//					
//					if("4".equals(idDokumen)){
//						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
//						this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
//						
//						beanMaklumatPejabat = new Vector();
//						logic.setMaklumatPejabatJKPTG(idPejabat);
//						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//					}
//				
//				} else if ("view".equals(modePopup)){
//					this.context.put("readonlyPopup", "readonly");
//	    			this.context.put("inputTextClassPopup", "disabled");
//	    			this.context.put("disabled", "disabled");
//	    			this.context.put("readonlyS", "readonly");
//	    			this.context.put("inputTextClassS", "disabled");
//					
//	    			beanMaklumatKJT = new Vector();
//					logic.setMaklumatKJT(idUlasanTeknikal);
//					beanMaklumatKJT = logic.getBeanMaklumatKJT();
//					this.context.put("BeanMaklumatKJT",beanMaklumatKJT);
//				
//					if (beanMaklumatKJT.size() != 0){
//						Hashtable hashMaklumatKJT = (Hashtable) logic.getBeanMaklumatKJT().get(0);
//						idDokumen = (String) hashMaklumatKJT.get("idDokumen");
//						idKementerian = (String) hashMaklumatKJT.get("idKementerian");
//						idAgensi = (String) hashMaklumatKJT.get("idAgensi");
//						idNegeri = (String) hashMaklumatKJT.get("idNegeri");
//						idPejabat = (String) hashMaklumatKJT.get("idPejabat");
//						flagStatus = (String) hashMaklumatKJT.get("flagStatus");
//						flagAktif = (String) hashMaklumatKJT.get("flagAktif");
//						flagSuratKe = (String) hashMaklumatKJT.get("flagKJP");
//					}
//					this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
//	    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
//					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
//					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
//					
//					if("1".equals(idDokumen)){
//						if("PTD".equals(flagSuratKe)) {
//							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "2"));
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabat(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//						} else if("PTG".equals(flagSuratKe)) {
//							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "1"));
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabat(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//							
//						} else if("JKPTG".equals(flagSuratKe)) {
//							this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
//			    			beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabatJKPTG(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//						} else if ("KJP".equals(flagSuratKe)){
//							
//							beanMaklumatAgensi = new Vector();
//							logic.setMaklumatAgensi(idAgensiTanah);
//							beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//							this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
//							
//						} else if ("KJT".equals(flagSuratKe)){
//							
//							beanMaklumatAgensi = new Vector();
//							logic.setMaklumatAgensi(idAgensi);
//							beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//							this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);							
//						}
//					} else {
//						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
//						beanMaklumatPejabat = new Vector();
//						logic.setMaklumatPejabatJKPTG(idPejabat);
//						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//
//					}
//					
//				} else if ("update".equals(modePopup)){					
//					this.context.put("readonlyPopup", "");
//	    			this.context.put("inputTextClassPopup", "");
//	    			this.context.put("disabled", "");
//	    			
//	    			beanMaklumatKJT = new Vector();    			
//	    			Hashtable hashMaklumatKJT = new Hashtable();
//	    			hashMaklumatKJT.put("tarikhHantar", getParam("txtTarikhHantar"));
//	    			hashMaklumatKJT.put("jangkamasa", getParam("txtJangkaMasa"));
//	    			hashMaklumatKJT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
//	    			hashMaklumatKJT.put("tarikhTerima", getParam("txtTarikhTerima"));
//	    			hashMaklumatKJT.put("tarikhSurat", getParam("txtTarikhSurat"));
//	    			hashMaklumatKJT.put("noRujukan", getParam("txtNoRujukanSurat"));
//	    			hashMaklumatKJT.put("ulasan", getParam("txtUlasan"));
//	    			hashMaklumatKJT.put("namaPegawai", getParam("txtNamaPegawai"));
//	    			hashMaklumatKJT.put("jawatan", getParam("txtJawatan"));
//	    			beanMaklumatKJT.addElement(hashMaklumatKJT);
//					this.context.put("BeanMaklumatKJT", beanMaklumatKJT);
//	    			
//					if ("1".equals(flagStatus)){
//						this.context.put("readonlyS", "");
//		    			this.context.put("inputTextClassS", "");
//						this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
//		    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
//						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
//						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
//							idNegeri = idNegeriTanah;
//						}
//						if("1".equals(idDokumen)){
//							if("PTG".equals(flagSuratKe)){
//								this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
//								this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "1"));
//								
//								beanMaklumatPejabat = new Vector();
//								logic.setMaklumatPejabat(idPejabat);
//								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//								this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//							} else if ("PTD".equals(flagSuratKe)){
//								this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
//								this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
//								
//								beanMaklumatPejabat = new Vector();
//								logic.setMaklumatPejabat(idPejabat);
//								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//								this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//							} else if ("JKPTG".equals(flagSuratKe)){
//								this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
//								this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri));
//								
//								beanMaklumatPejabat = new Vector();
//								logic.setMaklumatPejabatJKPTG(idPejabat);
//								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//								this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//							} else if ("KJP".equals(flagSuratKe)){
//								
//								beanMaklumatAgensi = new Vector();
//								logic.setMaklumatAgensi(idAgensiTanah);
//								beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//								this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
//								
//							} else if ("KJT".equals(flagSuratKe)){
//								
//								beanMaklumatAgensi = new Vector();
//								logic.setMaklumatAgensi(idAgensi);
//								beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//								this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);							
//							}
//						}
//						
//						if("4".equals(idDokumen)){
//							this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
//							this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri));
//							
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabatJKPTG(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//						}
//					} else {
//						this.context.put("readonlyS", "readonly");
//		    			this.context.put("inputTextClassS", "disabled");
//						this.context.put("selectDokumen", HTML.SelectPHPRujDokumen("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
//		    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), "disabled", " class=\"disabled\""));
//						this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "disabled", " class=\"disabled\""));
//						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
//							idNegeri = idNegeriTanah;
//						}
//						if("1".equals(idDokumen)){
//							if("PTD".equals(flagSuratKe)) {
//								this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "2"));
//								beanMaklumatPejabat = new Vector();
//								logic.setMaklumatPejabat(idPejabat);
//								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//								this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//							} else if("PTG".equals(flagSuratKe)) {
//								this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "1"));
//								beanMaklumatPejabat = new Vector();
//								logic.setMaklumatPejabat(idPejabat);
//								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//								this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//								
//							} else if("JKPTG".equals(flagSuratKe)) {
//								this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
//				    			beanMaklumatPejabat = new Vector();
//								logic.setMaklumatPejabatJKPTG(idPejabat);
//								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//								this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//							} else if ("KJP".equals(flagSuratKe)){
//								
//								beanMaklumatAgensi = new Vector();
//								logic.setMaklumatAgensi(idAgensiTanah);
//								beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//								this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
//								
//							} else if ("KJT".equals(flagSuratKe)){
//								
//								beanMaklumatAgensi = new Vector();
//								logic.setMaklumatAgensi(idAgensi);
//								beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
//								this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);							
//							}
//						} else {
//							this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
//							beanMaklumatPejabat = new Vector();
//							logic.setMaklumatPejabatJKPTG(idPejabat);
//							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//							this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
//
//						}
//					}
//				}
//			}
//			
//			//OPEN POPUP DETAIL MAKLUMAT PBT
//			if ("openPBT".equals(flagPopup)){
//				
//				if ("new".equals(modePopup)){
//					this.context.put("readonlyPopup", "");
//	    			this.context.put("inputTextClassPopup", "");
//	    			this.context.put("disabled", "");
//	    				    			
//	    			if("doChangeNegeri".equals(submit)){
//	     				idPejabat = "99999";
//	     				idPBT = "99999";
//	     			}
//	    			
//	    			if ("".equals(submit)){
//	    				
//	    				beanMaklumatPBT = new Vector();    			
//		    			Hashtable hashMaklumatPBT = new Hashtable();
//		    			hashMaklumatPBT.put("tarikhHantar", "");
//		    			hashMaklumatPBT.put("jangkamasa", "");
//		    			hashMaklumatPBT.put("tarikhJangkaTerima", "");
//		    			beanMaklumatPBT.addElement(hashMaklumatPBT);
//						this.context.put("BeanMaklumatPBT", beanMaklumatPBT);
//												
//	    			} else {
//	    				
//	    				beanMaklumatPBT = new Vector();    			
//		    			Hashtable hashMaklumatPBT = new Hashtable();
//		    			hashMaklumatPBT.put("tarikhHantar", getParam("txtTarikhHantar"));
//		    			hashMaklumatPBT.put("jangkamasa", getParam("txtJangkaMasa"));
//		    			hashMaklumatPBT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
//		    			beanMaklumatPBT.addElement(hashMaklumatPBT);
//		    			this.context.put("BeanMaklumatPBT", beanMaklumatPBT);
//	    			}
//	    			
//	    			if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
//						idNegeri = idNegeriTanah;
//					}
//					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));	
//					this.context.put("selectPBT", HTML.SelectPejabatPBTByNegeri(idNegeri,"socPBT",Long.parseLong(idPBT), "", " onChange=\"doChangePBT();\" "));
//					
//					beanMaklumatPejabat = new Vector();
//					logic.setMaklumatPejabatPBT(idPBT);
//					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//									
//				} else if ("newUlangan".equals(modePopup)){
//					this.context.put("readonlyPopup", "");
//	    			this.context.put("inputTextClassPopup", "");
//	    			this.context.put("disabled", "");
//	    			
//	    			if ("".equals(submit)){
//	    				
//	    				beanMaklumatPBT = new Vector();    			
//		    			Hashtable hashMaklumatPBT = new Hashtable();
//		    			hashMaklumatPBT.put("tarikhHantar", "");
//		    			hashMaklumatPBT.put("jangkamasa", "");
//		    			hashMaklumatPBT.put("tarikhJangkaTerima", "");
//		    			beanMaklumatPBT.addElement(hashMaklumatPBT);
//		    			this.context.put("BeanMaklumatPBT", beanMaklumatPBT);
//						
//	    			} else {
//	    				
//	    				beanMaklumatPBT = new Vector();    			
//		    			Hashtable hashMaklumatPBT = new Hashtable();
//		    			hashMaklumatPBT.put("tarikhHantar", getParam("txtTarikhHantar"));
//		    			hashMaklumatPBT.put("jangkamasa", getParam("txtJangkaMasa"));
//		    			hashMaklumatPBT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
//		    			beanMaklumatPBT.addElement(hashMaklumatPBT);
//		    			this.context.put("BeanMaklumatPBT", beanMaklumatPBT);
//	    			}
//	    			
//	    			if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
//						idNegeri = idNegeriTanah;
//					}
//					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
//					this.context.put("selectPBT", HTML.SelectPejabatPBTByNegeri(idNegeri,"socPBT",Long.parseLong(idPBT), "disabled", " class=\"disabled\""));
//				
//					beanMaklumatPejabat = new Vector();
//					logic.setMaklumatPejabatPBT(idPBT);
//					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//					
//				} else if ("view".equals(modePopup)){
//					this.context.put("readonlyPopup", "readonly");
//	    			this.context.put("inputTextClassPopup", "disabled");
//	    			this.context.put("disabled", "disabled");
//					
//	    			beanMaklumatPBT = new Vector();
//					logic.setMaklumatPBT(idUlasanTeknikal);
//					beanMaklumatPBT = logic.getBeanMaklumatPBT();
//					this.context.put("BeanMaklumatPBT",beanMaklumatPBT);
//					
//					if (beanMaklumatPBT.size() != 0){
//						Hashtable hashMaklumatPBT = (Hashtable) logic.getBeanMaklumatPBT().get(0);
//						idNegeri = (String) hashMaklumatPBT.get("idNegeri");
//						flagStatus = (String) hashMaklumatPBT.get("flagStatus");
//						flagAktif = (String) hashMaklumatPBT.get("flagAktif");
//						idPBT = (String) hashMaklumatPBT.get("idPBT");
//					}
//					
//					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
//					this.context.put("selectPBT", HTML.SelectPejabatPBTByNegeri(idNegeri,"socPBT",Long.parseLong(idPBT), "disabled", " class=\"disabled\""));
//								
//					beanMaklumatPejabat = new Vector();
//					logic.setMaklumatPejabatPBT(idPBT);
//					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//					
//				} else if ("update".equals(modePopup)){					
//					this.context.put("readonlyPopup", "");
//	    			this.context.put("inputTextClassPopup", "");
//	    			this.context.put("disabled", "");
//	    			
//	    			beanMaklumatPBT = new Vector();    			
//	    			Hashtable hashMaklumatPBT = new Hashtable();
//	    			hashMaklumatPBT.put("tarikhHantar", getParam("txtTarikhHantar"));
//	    			hashMaklumatPBT.put("jangkamasa", getParam("txtJangkaMasa"));
//	    			hashMaklumatPBT.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
//	    			hashMaklumatPBT.put("tarikhTerima", getParam("txtTarikhTerima"));
//	    			hashMaklumatPBT.put("tarikhSurat", getParam("txtTarikhSurat"));
//	    			hashMaklumatPBT.put("noRujukan", getParam("txtNoRujukanSurat"));
//	    			hashMaklumatPBT.put("ulasan", getParam("txtUlasan"));
//	    			hashMaklumatPBT.put("namaPegawai", getParam("txtNamaPegawai"));
//	    			hashMaklumatPBT.put("jawatan", getParam("txtJawatan"));
//	    			beanMaklumatPBT.addElement(hashMaklumatPBT);
//					this.context.put("BeanMaklumatPBT", beanMaklumatPBT);
//	    			
//					if ("1".equals(flagStatus)){
//						
//						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
//							idNegeri = idNegeriTanah;
//						}
//						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
//						this.context.put("selectPBT", HTML.SelectPejabatPBTByNegeri(idNegeri,"socPBT",Long.parseLong(idPBT), "", " onChange=\"doChangePBT();\""));
//					} else {
//						
//						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
//							idNegeri = idNegeriTanah;
//						}
//						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
//						this.context.put("selectPBT", HTML.SelectPejabatPBTByNegeri(idNegeri,"socPBT",Long.parseLong(idPBT), "disabled", " class=\"disabled\""));
//					}					
//				
//					beanMaklumatPejabat = new Vector();
//					logic.setMaklumatPejabatPBT(idPBT);
//					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
//					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
//				}
//				
//			}	
	
			//OPEN POPUP DETAIL MAKLUMAT NOTIS 425
			if ("openNotis425".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			this.context.put("idSuratKe", flagSuratKe);
	     			this.context.put("readonlyS", "");
	    			this.context.put("inputTextClassS", "");
	    			
	    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
	    			
	    			if("doChangeSuratKe".equals(submit) || "doChangeNegeri".equals(submit)){
	    				idPejabat = "99999";
	    			}
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatNotis425 = new Vector();    			
		    			Hashtable hashMaklumatNotis = new Hashtable();
		    			hashMaklumatNotis.put("tarikhHantar", "");
		    			hashMaklumatNotis.put("jangkamasa", "");
		    			hashMaklumatNotis.put("tarikhJangkaTerima", "");
		    			beanMaklumatNotis425.addElement(hashMaklumatNotis);
						this.context.put("BeanMaklumatNotis425", beanMaklumatNotis425);
					
						idNegeri = "99999";
						idPejabat = "99999";
						
	    			} else {
	    				
	    				beanMaklumatNotis425 = new Vector();    			
		    			Hashtable hashMaklumatNotis = new Hashtable();
		    			hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatNotis.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatNotis.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
		    			beanMaklumatNotis425.addElement(hashMaklumatNotis);
						this.context.put("BeanMaklumatNotis425", beanMaklumatNotis425);
	    			}
	    			
	    			if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
											
					if ("PTD".equals(flagSuratKe)){
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
						
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						
					} else if ("JKPTG".equals(flagSuratKe)){
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));
						
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabatJKPTG(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
						
					} else if("PTG".equals(flagSuratKe)) {
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "", "  onChange=\"doChangePejabat();\"",idNegeri, "1"));
					
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					}
				
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			this.context.put("readonlyS", "readonly");
	    			this.context.put("inputTextClassS", "disabled");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatNotis425 = new Vector();    			
		    			Hashtable hashMaklumatNotis = new Hashtable();
		    			hashMaklumatNotis.put("tarikhHantar", "");
		    			hashMaklumatNotis.put("jangkamasa", "");
		    			hashMaklumatNotis.put("tarikhJangkaTerima", "");
		    			beanMaklumatNotis425.addElement(hashMaklumatNotis);
						this.context.put("BeanMaklumatNotis425", beanMaklumatNotis425);
						
	    			} else {
	    				
	    				beanMaklumatNotis425 = new Vector();    			
		    			Hashtable hashMaklumatNotis = new Hashtable();
		    			hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatNotis.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatNotis.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
		    			beanMaklumatNotis425.addElement(hashMaklumatNotis);
		    			this.context.put("BeanMaklumatNotis425", beanMaklumatNotis425);
	    			}
	    			
	    			if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					
					if ("PTD".equals(flagSuratKe)){
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "2"));
						
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					} else if ("JKPTG".equals(flagSuratKe)){
						this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
													
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabatJKPTG(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					} else if ("PTG".equals(flagSuratKe)){
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "1"));
						
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					}
					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
	    			this.context.put("readonlyS", "readonly");
	    			this.context.put("inputTextClassS", "disabled");
					
	    			beanMaklumatNotis425 = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatNotis425 = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatNotis425",beanMaklumatNotis425);
					
					if (beanMaklumatNotis425.size() != 0){
						Hashtable hashMaklumatKJT = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						idNegeri = (String) hashMaklumatKJT.get("idNegeri");
						idPejabat = (String) hashMaklumatKJT.get("idPejabat");
						flagStatus = (String) hashMaklumatKJT.get("flagStatus");
						flagAktif = (String) hashMaklumatKJT.get("flagAktif");
						flagSuratKe = (String) hashMaklumatKJT.get("flagKJP");
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					if("PTD".equals(flagSuratKe)) {
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "2"));
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					} else if("JKPTG".equals(flagSuratKe)) {
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
		    			beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabatJKPTG(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					}else if("PTG".equals(flagSuratKe)) {
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "1"));
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					}
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if("doChangeSuratKe".equals(submit) || "doChangeNegeri".equals(submit)){
	    				idPejabat = "99999";
	    			}
	    			
	    			beanMaklumatNotis425 = new Vector();    			
	    			Hashtable hashMaklumatNotis = new Hashtable();
	    			hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatNotis.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatNotis.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
	    			hashMaklumatNotis.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatNotis.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatNotis.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatNotis.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatNotis.put("namaPegawai", getParam("txtNamaPegawai"));
	    			hashMaklumatNotis.put("jawatan", getParam("txtJawatan"));
	    			beanMaklumatNotis425.addElement(hashMaklumatNotis);
					this.context.put("BeanMaklumatNotis425", beanMaklumatNotis425);
	    			
					if ("1".equals(flagStatus)){
						this.context.put("readonlyS", "");
		    			this.context.put("inputTextClassS", "");
					
						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
							idNegeri = idNegeriTanah;
						}
						
							if ("PTD".equals(flagSuratKe)){
								this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
								this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
								
								beanMaklumatPejabat = new Vector();
								logic.setMaklumatPejabat(idPejabat);
								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
								this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
							} else if ("JKPTG".equals(flagSuratKe)){
								this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
								this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri));
								
								beanMaklumatPejabat = new Vector();
								logic.setMaklumatPejabatJKPTG(idPejabat);
								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
								this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
							}else if ("PTG".equals(flagSuratKe)){
								this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
								this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "1"));
								
								beanMaklumatPejabat = new Vector();
								logic.setMaklumatPejabat(idPejabat);
								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
								this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
							}
													
					} else {
						this.context.put("readonlyS", "readonly");
		    			this.context.put("inputTextClassS", "disabled");
						
						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)){
							idNegeri = idNegeriTanah;
						}
						
						if("PTD".equals(flagSuratKe)) {
							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "2"));
							beanMaklumatPejabat = new Vector();
							logic.setMaklumatPejabat(idPejabat);
							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						} else if("JKPTG".equals(flagSuratKe)) {
							this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri));
				    		beanMaklumatPejabat = new Vector();
							logic.setMaklumatPejabatJKPTG(idPejabat);
							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
							this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
						}else if("PTG".equals(flagSuratKe)) {
							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "1"));
							beanMaklumatPejabat = new Vector();
							logic.setMaklumatPejabat(idPejabat);
							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						}
					}					
				}
			}
			
			//OPEN POPUP DETAIL MAKLUMAT NOTIS
			if ("openNotis".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatNotis = new Vector();    			
		    			Hashtable hashMaklumatNotis = new Hashtable();
		    			hashMaklumatNotis.put("tarikhHantar", "");
		    			beanMaklumatNotis.addElement(hashMaklumatNotis);
						this.context.put("BeanMaklumatNotis", beanMaklumatNotis);
						
						idDokumen = "99999";
						
	    			} else {
	    				
	    				beanMaklumatNotis = new Vector();    			
		    			Hashtable hashMaklumatNotis = new Hashtable();
		    			hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			beanMaklumatNotis.addElement(hashMaklumatNotis);
						this.context.put("BeanMaklumatNotis", beanMaklumatNotis);
	    			}
	    			
	    			this.context.put("selectDokumen", HTML.SelectPHPRujDokumenNotisPeringatan("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
	    			
	    			idPejabat = logic.getIdPejabatPTD(idHakmilik);
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
	    			
				} else if ("view".equals(modePopup)){
								
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
	    			
	    			beanMaklumatNotis = new Vector();
					logic.setMaklumatNotis(idUlasanTeknikal);
					beanMaklumatNotis = logic.getBeanMaklumatNotis();
					this.context.put("BeanMaklumatNotis",beanMaklumatNotis);
					
					if (beanMaklumatNotis.size() != 0){
						Hashtable hashMaklumatNotis = (Hashtable) logic.getBeanMaklumatNotis().get(0);
						idPejabat = (String) hashMaklumatNotis.get("idPejabat");
						idDokumen = (String) hashMaklumatNotis.get("idDokumen");
						flagStatus = (String) hashMaklumatNotis.get("flagStatus");
						flagAktif = (String) hashMaklumatNotis.get("flagAktif");
					}
					
					this.context.put("selectDokumen", HTML.SelectPHPRujDokumenNotisPeringatan("socDokumen", Utils.parseLong(idDokumen), "disabled", " class=\"disabled\""));
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
				
				} else if ("update".equals(modePopup)){	
					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatNotis = new Vector();    			
	    			Hashtable hashMaklumatNotis = new Hashtable();
	    			hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			beanMaklumatNotis.addElement(hashMaklumatNotis);
					this.context.put("BeanMaklumatNotis", beanMaklumatNotis);	
					
					this.context.put("selectDokumen", HTML.SelectPHPRujDokumenNotisPeringatan("socDokumen", Utils.parseLong(idDokumen), "", " onChange=\"doChangeJenisDokumen();\""));
	    			
	    			if ("9".equals(idDokumen)){
	    				
	    				idPejabat = logic.getIdPejabatPTD(idHakmilik);
	    				
	    				beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
	    			}
				}			
			}
			
//			//DOKUMEN ULASAN
//			senaraiKJT = new Vector();
//			logic.setSenaraiKJT(idPermohonan);
//			senaraiKJT = logic.getListKJT();
//			this.context.put("SenaraiKJT", senaraiKJT);
//			
//			//DOKUMEN PBT
//			senaraiPBT = new Vector();
//			logic.setSenaraiPBT(idPermohonan);
//			senaraiPBT = logic.getListPBT();
//			this.context.put("SenaraiPBT", senaraiPBT);
			
			//DOKUMEN NOTIS 425
			senaraiNotis425 = new Vector();
			logic.setSenaraiNotis425(idPermohonan);
			senaraiNotis425 = logic.getListNotis425();
			this.context.put("SenaraiNotis425", senaraiNotis425);
			
			//SENARAI NOTIS PENGOSONGAN
			senaraiNotis = new Vector();
			logic.setSenaraiNotis(idPermohonan);
			senaraiNotis = logic.getListNotis();
			this.context.put("SenaraiNotis", senaraiNotis);
			
		} 
		
		if ("selesaiPermohonan".equals(step)){
        	vm = "app/php2/frmSelesaiPermohonan.jsp";
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
        this.context.put("idUlasanTeknikal", idUlasanTeknikal);
        this.context.put("idKementerianTanah", idKementerianTanah);
        this.context.put("idAgensiTanah", idAgensiTanah);
        this.context.put("idDokumen", idDokumen);
        this.context.put("flagStatus", flagStatus);
        this.context.put("flagAktif", flagAktif);
        this.context.put("idSuratKe", flagSuratKe);
        
        this.context.put("flagOpenDetail", flagOpenDetail);
	    this.context.put("status", status.toUpperCase());

		return vm;
	}
}
