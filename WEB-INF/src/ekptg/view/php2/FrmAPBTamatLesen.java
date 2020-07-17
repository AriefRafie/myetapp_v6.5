/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBTamatLesenData;

/**
 * @author by hilda
 *
 */
public class FrmAPBTamatLesen extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmAPBTamatLesen.class);
	private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBTamatLesenData logic = new FrmAPBTamatLesenData();
	Utils utils = new Utils();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String)session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String submit = getParam("command");  
	    
		String selectedTabUpper = (String)getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
	    String vm = ""; 
	    String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
	    String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String flagStatus = getParam("flagStatus");
		//GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String actionTamat = getParam("actionTamat");
        String idStatus = getParam("idStatus");        
        String idMohonTamat = getParam("idMohonTamat");
        String flagAktif = getParam("flagAktif");
        
        String jenisDokumen = getParam("jenisDokumen");
        String idSuratKe = getParam("idSuratKe");
        
        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}	
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0){
			idPejabat = "99999";
		}
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
        String idKementerianTanah = getParam("idKementerianTanah");
        String idAgensiTanah = getParam("idAgensiTanah");
        //VECTOR
        Vector beanHeader = null;
        Vector senaraiPermohonanTamat = null; 
        Vector beanMaklumatMohonTamat = null;
		Vector beanMaklumatJKPTG = null;
        Vector beanMaklumatMesyuarat = null;
        Vector beanMaklumatKeputusan = null;
        Vector beanMaklumatNotis = null;
        Vector beanMaklumatPejabat=null;
        Vector senaraiNotis = null;
        
		if (postDB) {
  	
			if ("simpanMaklumatMohonTamat".equals(hitButton)){
					idMohonTamat = simpanMaklumatMohonTamat(idFail,idPermohonan, session);
			}
			if ("simpanKemaskiniMaklumatMohonTamat".equals(hitButton)){
					simpanKemaskiniMaklumatMohonTamat(idMohonTamat, session);
					this.context.put("selectedTabUpper", "0");
			}
			if ("simpanKemaskiniMaklumatJKPTG".equals(hitButton)){
					simpanKemaskiniMaklumatJKPTG(idMohonTamat, session);
					this.context.put("selectedTabUpper", "1");
			}	
			if ("simpanKemaskiniMaklumatMesyuarat".equals(hitButton)){
					simpanKemaskiniMaklumatMesyuarat(idMohonTamat, session);
					this.context.put("selectedTabUpper", "2");
			}
			if ("simpanKemaskiniMaklumatKeputusan".equals(hitButton)){
					simpanKemaskiniMaklumatKeputusan(idMohonTamat, session);
					this.context.put("selectedTabUpper", "3");
			}	
			if ("doSeterusnya".equals(hitButton)){
				logic.updateStatus(idFail, idPermohonan,idMohonTamat, session);
			}
    		if ("simpanMaklumatNotis".equals(hitButton)){
        		//idUlasanTeknikal = logic.simpanMaklumatNotis(idPermohonan,idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        		//		getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"),getParam("idSuratKe"), idKementerianTanah, idAgensiTanah, session);
    			idUlasanTeknikal = logic.simpanMaklumatNotis(idMohonTamat,getParam("jenisDokumen"),idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"),getParam("idSuratKe"), idKementerianTanah, idAgensiTanah, session);
    		}
    		if ("simpanMaklumatUlanganNotis".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlangaNotis(idUlasanTeknikal, idMohonTamat, idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"),getParam("idSuratKe"),idKementerianTanah, idAgensiTanah, session);
    		}
    		if ("simpanKemaskiniMaklumatNotis".equals(hitButton)){
        		logic.simpanKemaskiniMaklumaNotis(idUlasanTeknikal,idPejabat, idNegeri, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukan"), getParam("txtUlasan"),getParam("idSuratKe"),idKementerianTanah, idAgensiTanah,
        				getParam("txtNamaPegawai"),getParam("txtJawatan"),session);
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
			idPermohonan =(String)hashHeader.get("idPermohonan");
			idStatus = (String)hashHeader.get("idStatus");
			flagAktif = (String)hashHeader.get("flagAktif");
		}

		if("add".equals(actionTamat)){
			
			vm = "app/php2/frmAPBPTamatLesenMohonNew.jsp";
			
			if("view".equals(mode)){
				
				//OPEN POPUP DETAIL MAKLUMAT TAMAT LESEN
				if ("openPopupTamatLesen".equals(flagPopup)){
					
					if ("new".equals(modePopup)){
						
						this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			
		    			beanMaklumatMohonTamat = new Vector();    			
		    			Hashtable hashMaklumatMohonTamat = new Hashtable();
		    			hashMaklumatMohonTamat.put("socFlagDari", getParam("socFlagDari") == null ? "" : getParam("socFlagDari"));
		    			hashMaklumatMohonTamat.put("tarikhSurat", getParam("tarikhSurat") == null ? "" : getParam("tarikhSurat"));
		    			hashMaklumatMohonTamat.put("tarikhTerima", getParam("tarikhTerima") == null ? "" : getParam("tarikhTerima"));
		    			hashMaklumatMohonTamat.put("rujukan", getParam("rujukan") == null ? "" : getParam("rujukan"));
		    			hashMaklumatMohonTamat.put("sebabTamat", getParam("sebabTamat") == null ? "" : getParam("sebabTamat"));
	    			
		    			beanMaklumatMohonTamat.addElement(hashMaklumatMohonTamat);
						this.context.put("BeanMaklumatMohonTamat", beanMaklumatMohonTamat);
					}
				}
			}
		}
		else
		if("papar".equals(actionTamat)){
			 
			vm = "app/php2/frmAPBTamatLesenMohonDetail.jsp";
			
			if("add".equals(mode)){
				
				//OPEN POPUP DETAIL MAKLUMAT SURAT NOTIS
				if ("openMaklumatNotis".equals(flagPopup)){
					
					if ("new".equals(modePopup)){
						
						this.context.put("readonlyPopup", "");
		    			this.context.put("inputTextClassPopup", "");
		    			this.context.put("jenisDokumen", jenisDokumen);
		    			this.context.put("idSuratKe", idSuratKe);
		    			
		    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
						
		    			if ("".equals(submit)){
		    				
		    				beanMaklumatNotis = new Vector();    			
			    			Hashtable hashMaklumatNotis = new Hashtable();
			    			hashMaklumatNotis.put("tarikhHantar", "");
			    			hashMaklumatNotis.put("jangkamasa", "");
			    			hashMaklumatNotis.put("tarikhJangkaTerima", "");
			    			beanMaklumatNotis.addElement(hashMaklumatNotis);
							this.context.put("BeanMaklumatNotis", beanMaklumatNotis);
						
							idNegeri = "99999";
							idPejabat = "99999";
							
		    			} else {
		    				
		    				beanMaklumatNotis = new Vector();    			
			    			Hashtable hashMaklumatNotis = new Hashtable();
			    			hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
			    			hashMaklumatNotis.put("jangkamasa", getParam("txtJangkaMasa"));
			    			hashMaklumatNotis.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
			    			beanMaklumatNotis.addElement(hashMaklumatNotis);
							this.context.put("BeanMaklumatNotis", beanMaklumatNotis);
		    			}
		    			
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						
						beanMaklumatNotis = new Vector();
				        logic.setMaklumatNotis(idMohonTamat);
				        beanMaklumatNotis = logic.getBeanMaklumatNotis();
						this.context.put("BeanMaklumatNotis", beanMaklumatNotis);	
						this.context.put("selectedTabUpper", "1");
						
		    			beanMaklumatMohonTamat = new Vector();    			
		    			Hashtable hashMaklumatMohonTamat = new Hashtable();
		    			hashMaklumatMohonTamat.put("socFlagDari", getParam("socFlagDari") == null ? "" : getParam("socFlagDari"));
		    			hashMaklumatMohonTamat.put("tarikhSurat", getParam("tarikhSurat") == null ? "" : getParam("tarikhSurat"));
		    			hashMaklumatMohonTamat.put("tarikhTerima", getParam("tarikhTerima") == null ? "" : getParam("tarikhTerima"));
		    			hashMaklumatMohonTamat.put("rujukan", getParam("rujukan") == null ? "" : getParam("rujukan"));
		    			hashMaklumatMohonTamat.put("sebabTamat", getParam("sebabTamat") == null ? "" : getParam("sebabTamat"));
	    			
		    			beanMaklumatMohonTamat.addElement(hashMaklumatMohonTamat);
						this.context.put("BeanMaklumatMohonTamat", beanMaklumatMohonTamat);
					}
				}
			}
			
			if("view".equals(mode)){
				
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");
				
				if("0".equals(selectedTabUpper)){
					//MAKLUMAT TAMAT LESEN
					beanMaklumatMohonTamat = new Vector();
					logic.setMaklumatMohonTamat(idMohonTamat);
					beanMaklumatMohonTamat = logic.getBeanMaklumatMohonTamat();
					this.context.put("BeanMaklumatMohonTamat", beanMaklumatMohonTamat);
					this.context.put("selectedTabUpper", "0");
				}
				if("1".equals(selectedTabUpper)){	
					this.context.put("selectedTabUpper", "1");
					
					//OPEN POPUP DETAIL MAKLUMAT SURAT NOTIS
					if ("openMaklumatNotis".equals(flagPopup)){
						
						if ("new".equals(modePopup)){
							
							this.context.put("readonlyPopup", "");
			    			this.context.put("inputTextClassPopup", "");
			    			this.context.put("jenisDokumen", jenisDokumen);
			    			this.context.put("idSuratKe", idSuratKe);
			    			
			    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
							
			    			if ("".equals(submit)){
			    				
			    				beanMaklumatNotis = new Vector();    			
				    			Hashtable hashMaklumatNotis = new Hashtable();
				    			hashMaklumatNotis.put("tarikhHantar", "");
				    			hashMaklumatNotis.put("jangkamasa", "");
				    			hashMaklumatNotis.put("tarikhJangkaTerima", "");
				    			beanMaklumatNotis.addElement(hashMaklumatNotis);
								this.context.put("BeanMaklumatNotis", beanMaklumatNotis);
							
								idNegeri = "99999";
								idPejabat = "99999";
								
			    			} else {
			    				
			    				beanMaklumatNotis = new Vector();    			
				    			Hashtable hashMaklumatNotis = new Hashtable();
				    			hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
				    			hashMaklumatNotis.put("jangkamasa", getParam("txtJangkaMasa"));
				    			hashMaklumatNotis.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
				    			beanMaklumatNotis.addElement(hashMaklumatNotis);
								this.context.put("BeanMaklumatNotis", beanMaklumatNotis);
			    			}
							beanMaklumatPejabat = new Vector();
							logic.setMaklumatPejabat(idPejabat);
							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
							
							beanMaklumatNotis = new Vector();
					        logic.setMaklumatNotis(idMohonTamat);
					        beanMaklumatNotis = logic.getBeanMaklumatNotis();
							this.context.put("BeanMaklumatNotis", beanMaklumatNotis);	
							this.context.put("selectedTabUpper", "1");
							
			    			beanMaklumatMohonTamat = new Vector();    			
			    			Hashtable hashMaklumatMohonTamat = new Hashtable();
			    			hashMaklumatMohonTamat.put("socFlagDari", getParam("socFlagDari") == null ? "" : getParam("socFlagDari"));
			    			hashMaklumatMohonTamat.put("tarikhSurat", getParam("tarikhSurat") == null ? "" : getParam("tarikhSurat"));
			    			hashMaklumatMohonTamat.put("tarikhTerima", getParam("tarikhTerima") == null ? "" : getParam("tarikhTerima"));
			    			hashMaklumatMohonTamat.put("rujukan", getParam("rujukan") == null ? "" : getParam("rujukan"));
			    			hashMaklumatMohonTamat.put("sebabTamat", getParam("sebabTamat") == null ? "" : getParam("sebabTamat"));
		    			
			    			beanMaklumatMohonTamat.addElement(hashMaklumatMohonTamat);
							this.context.put("BeanMaklumatMohonTamat", beanMaklumatMohonTamat);
						}
						
						if ("view".equals(modePopup)){
							
							this.context.put("readonlyPopup", "");
			    			this.context.put("inputTextClassPopup", "");
			    			this.context.put("jenisDokumen", jenisDokumen);
			    			this.context.put("idSuratKe", idSuratKe);
			    			
			    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
							
			    			if ("".equals(submit)){
			    				
			    				beanMaklumatPejabat = new Vector();
								logic.setMaklumatPejabat(idPejabat);
								beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
								this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);								
								
								beanMaklumatNotis = new Vector();
						        logic.setMaklumatNotis(idMohonTamat);
						        beanMaklumatNotis = logic.getBeanMaklumatNotis();
								this.context.put("BeanMaklumatNotis", beanMaklumatNotis);	
								this.context.put("selectedTabUpper", "1");
								
				    			beanMaklumatMohonTamat = new Vector();    			
				    			Hashtable hashMaklumatMohonTamat = new Hashtable();
				    			hashMaklumatMohonTamat.put("socFlagDari", getParam("socFlagDari") == null ? "" : getParam("socFlagDari"));
				    			hashMaklumatMohonTamat.put("tarikhSurat", getParam("tarikhSurat") == null ? "" : getParam("tarikhSurat"));
				    			hashMaklumatMohonTamat.put("tarikhTerima", getParam("tarikhTerima") == null ? "" : getParam("tarikhTerima"));
				    			hashMaklumatMohonTamat.put("rujukan", getParam("rujukan") == null ? "" : getParam("rujukan"));
				    			hashMaklumatMohonTamat.put("sebabTamat", getParam("sebabTamat") == null ? "" : getParam("sebabTamat"));
			    			
				    			beanMaklumatMohonTamat.addElement(hashMaklumatMohonTamat);
								this.context.put("BeanMaklumatMohonTamat", beanMaklumatMohonTamat);
								
			    			}
		    			}
					}
					//MAKLUMAT NOTIS
					beanMaklumatNotis = new Vector();
			        logic.setMaklumatNotis(idMohonTamat);
			        beanMaklumatNotis = logic.getBeanMaklumatNotis();
					this.context.put("BeanMaklumatNotis", beanMaklumatNotis);
					
					//DOKUMEN NOTIS
					//senaraiNotis = new Vector();
					//logic.setSenaraiNotis(idPermohonan);
					//senaraiNotis = logic.getListNotis();
					//this.context.put("SenaraiNotis", senaraiNotis);
					
					//DOKUMEN NOTIS
					senaraiNotis = new Vector();
					logic.setSenaraiNotis(idMohonTamat);
					senaraiNotis = logic.getListNotis();
					this.context.put("SenaraiNotis", senaraiNotis);
					
					this.context.put("selectedTabUpper", "1");
				}
				if("2".equals(selectedTabUpper)){
					//ULASAN JKPTG
					beanMaklumatJKPTG = new Vector();
			        logic.setMaklumatJKPTG(idMohonTamat);
			        beanMaklumatJKPTG = logic.getBeanMaklumatJKPTG();
					this.context.put("BeanMaklumatJKPTG", beanMaklumatJKPTG);	
					this.context.put("selectedTabUpper", "2");
				}
				if("3".equals(selectedTabUpper)){
					//MESYUARAT
					beanMaklumatMesyuarat = new Vector();
			        logic.setMaklumatMesyuarat(idMohonTamat);
			        beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
					this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);
					this.context.put("selectedTabUpper", "3");
				}
				if("4".equals(selectedTabUpper)){
					//KEPUTUSAN
					beanMaklumatKeputusan = new Vector();
			        logic.setMaklumatKeputusan(idMohonTamat);
			        beanMaklumatKeputusan = logic.getBeanMaklumatKeputusan();
					this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);
					this.context.put("selectedTabUpper", "4");
				}
			}
			if(mode.equals("update")){
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				this.context.put("disabled", "");
				
				if("0".equals(selectedTabUpper)){
					//MAKLUAMT TAMAT LESEN
	    			beanMaklumatMohonTamat = new Vector();    	
					logic.setMaklumatMohonTamat(idMohonTamat);
					beanMaklumatMohonTamat = logic.getBeanMaklumatMohonTamat();
					this.context.put("BeanMaklumatMohonTamat", beanMaklumatMohonTamat);
					this.context.put("selectedTabUpper", "0");
				}
				if("2".equals(selectedTabUpper)){
					//ULASAN JKPTG
					beanMaklumatJKPTG = new Vector();    			
			        logic.setMaklumatJKPTG(idMohonTamat);
			        beanMaklumatJKPTG = logic.getBeanMaklumatJKPTG();
					this.context.put("BeanMaklumatJKPTG", beanMaklumatJKPTG);
					this.context.put("selectedTabUpper", "2");
				}
				if("3".equals(selectedTabUpper)){
					//MESYUARAT
					beanMaklumatKeputusan = new Vector();
					logic.setMaklumatMesyuarat(idMohonTamat);
			        beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
	    			this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);		
					this.context.put("selectedTabUpper", "3");
				}				
				if("4".equals(selectedTabUpper)){
					//KEPUTUSAN
					beanMaklumatKeputusan = new Vector();    	
			        logic.setMaklumatKeputusan(idMohonTamat);
			        beanMaklumatKeputusan = logic.getBeanMaklumatKeputusan();
					this.context.put("BeanMaklumatKeputusan", beanMaklumatKeputusan);		
					this.context.put("selectedTabUpper", "4");
				}
			}
		}
		else {
			vm = "app/php2/frmAPBTamatLesen.jsp";
			
			//SENARAI PERMOHONAN TAMAT
			logic.setSenaraiPermohonanTamat(idPermohonan);
			senaraiPermohonanTamat = logic.getListPermohonanTamat();
			this.context.put("SenaraiPermohonanTamat", senaraiPermohonanTamat);
			setupPage(session,action,senaraiPermohonanTamat);

		}
		
		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("flagStatus", flagStatus);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idMohonTamat", idMohonTamat);
        this.context.put("actionTamat", actionTamat);
        this.context.put("idStatus", idStatus);
        this.context.put("flagAktif", flagAktif);
		return vm;
	}

	private String simpanMaklumatMohonTamat(String idFail,String idPermohonan, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("socFlagDari", getParam("socFlagDari"));
		hash.put("tarikhSurat", getParam("tarikhSurat"));
		hash.put("tarikhTerima", getParam("tarikhTerima"));
		hash.put("rujukan", getParam("rujukan"));
		hash.put("sebabTamat", getParam("sebabTamat"));	

		String idMohonTamat = logic.simpanMaklumatMohonTamat(idFail,idPermohonan, hash, session);
		return idMohonTamat;
	}
	
	private void simpanKemaskiniMaklumatMohonTamat(String idMohonTamat, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("socFlagDari", getParam("socFlagDari"));
		hash.put("tarikhSurat", getParam("tarikhSurat"));
		hash.put("tarikhTerima", getParam("tarikhTerima"));
		hash.put("rujukan", getParam("rujukan"));
		hash.put("sebabTamat", getParam("sebabTamat"));
	
		logic.simpanKemaskiniMaklumatMohonTamat(idMohonTamat, hash, session);
	}
	private void simpanKemaskiniMaklumatJKPTG(String idMohonTamat, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("ulasanJKPTG", getParam("ulasanJKPTG"));	
		
		logic.simpanKemaskiniMaklumatJKPTG(idMohonTamat, hash, session);
	}
	private void simpanKemaskiniMaklumatMesyuarat(String idMohonTamat, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("tarikhMesyuarat", getParam("tarikhMesyuarat"));	
		hash.put("txtBilMesyuarat", getParam("txtBilMesyuarat"));
		hash.put("txtWaktuDari", getParam("txtWaktuDari"));
		hash.put("socMasaDari", getParam("socMasaDari"));
		hash.put("txtWaktuHingga", getParam("txtWaktuHingga"));
		hash.put("socMasaHingga", getParam("socMasaHingga"));
		hash.put("txtTujuanMesyuarat", getParam("txtTujuanMesyuarat"));
		hash.put("syorMesyuarat", getParam("syorMesyuarat"));
		
		logic.simpanKemaskiniMaklumatMesyuarat(idMohonTamat, hash, session);
	}	
	private void simpanKemaskiniMaklumatKeputusan(String idMohonTamat, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("tarikhKeputusan", getParam("tarikhKeputusan"));	
		hash.put("socKeputusan", getParam("socKeputusan"));
		hash.put("ulasanMenteri", getParam("ulasanMenteri"));
		
		// ELLY ALTER 05072010
		hash.put("socFlagLesen", getParam("socFlagLesen"));
		hash.put("socFlagTunggakan", getParam("socFlagTunggakan"));
		
		logic.simpanKemaskiniMaklumatKeputusan(idMohonTamat, hash, session);
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
				this.context.put("SenaraiPermohonanTamat",paging.getPage(page));
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
}
