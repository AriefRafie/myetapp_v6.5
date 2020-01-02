package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmAPBAduanData;
import ekptg.model.php2.FrmAPBHeaderData;

public class FrmAPBAduanView extends  AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmAPBAduanView.class);
	
private static final long serialVersionUID = 1L;
	
	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBAduanData logic = new FrmAPBAduanData();
	
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
			
			Boolean postDB = false;
			String doPost = (String) session.getAttribute("doPost");
		    if (doPost.equals("true")) {
		        postDB = true;
		    }
		    
		    //GET DEFAULT PARAM
		    String action = getParam("action");
		    String vm = "";  
	        String actionAduan = getParam("actionAduan");
	        String hitButton = getParam("hitButton"); 
	        String submit = getParam("command");  
	        
	        myLogger.info("ACTION ADUAN :: "+actionAduan);
	        myLogger.info("HIT BUTTON :: "+hitButton);
	        myLogger.info("SUBMIT :: "+submit);
	        
			//GET ID PARAM
	        String idFail = getParam("idFail");
	        String idPermohonan = getParam("idPermohonan");
	        String idPemohon = getParam("idPemohon");
	        String idStatus = getParam("idStatus");
	        String flagAktif = getParam("flagAktif");
	        String idPengadu = getParam("idPengadu");
	        String selectedTabUpper = getParam("selectedTabUpper").toString();
			if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
				selectedTabUpper = "0";
			}
			String mode = getParam("mode");
	        if (mode.isEmpty()){
	        	mode = "view";
	        }
			
	               
	        //VECTOR
			Vector beanHeader= null;
			Vector beanMaklumatPengadu = null;
			Vector beanMaklumatAgensi = null;
			Vector list = null;
			Vector beanAduan = null;
			Vector beanMaklumatAduan = null;
			Vector beanMaklumatSiasatan = null;
			Vector beanMaklumatKeputusanAduan = null;
			
			//GET DROPDOWN PARAM
	        String idKategoriPengadu = getParam("socKategoriPengadu");
			if (idKategoriPengadu == null || idKategoriPengadu.trim().length() == 0){
				idKategoriPengadu = "99999";
			}
			String idNegeri = getParam("socNegeri");
			if (idNegeri == null || idNegeri.trim().length() == 0){
				idNegeri = "99999";
			}
			String idKementerian = getParam("socKementerian");
			if (idKementerian == null || idKementerian.trim().length() == 0){
				idKementerian = "99999";
			}
			String idAgensi = getParam("socAgensi");
			if (idAgensi == null || idAgensi.trim().length() == 0){
				idAgensi = "99999";
			}
		        
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date currentDate = new Date();
			
			
			//ACTION BUTTON
			if (postDB){
	        	if ("doDaftarBaru".equals(hitButton)){
	        		idPengadu = logic.daftarBaru(idPermohonan,getParam("tarikhTerima"), getParam("tarikhSurat"),getParam("txtPerkara"),
	        				idKategoriPengadu, getParam("txtNama"),getParam("txtAlamat1"),getParam("txtAlamat2"),
	    					getParam("txtAlamat3"),getParam("txtPoskod"),idNegeri,idKementerian, idAgensi,getParam("txtRujSurat"),session);	
	        	}
	           	if ("doSimpanKemaskiniPengadu".equals(hitButton)){
					logic.updatePengadu(idPengadu, getParam("txtNama"),getParam("txtAlamat1"),getParam("txtAlamat2"),
	    					getParam("txtAlamat3"),getParam("txtPoskod"),idNegeri,idKementerian, idAgensi,
	    					getParam("txtNoHp"),getParam("txtNoTelefon"),getParam("txtEmail"),session);	
	            }
	        	if ("doSimpanKemaskiniBasicAduan".equals(hitButton)){
					logic.updateBasicAduan(idPengadu, getParam("tarikhTerima"), getParam("tarikhSurat"),getParam("txtPerkara"),
	        				getParam("txtRujSurat"),getParam("socJenisDaftar"),session);
	            }	           	
	        	if ("doSimpanKemaskiniMaklumatAduan".equals(hitButton)){
					logic.updateAduan(idPengadu, getParam("txtKeteranganAduan"),getParam("txtSumberAduan"),session);	
	            }
	        	if ("doSimpanKemaskiniMaklumatSiasatan".equals(hitButton)){
					logic.updateSiasatan(idPengadu, getParam("txtTarikhTerimaSuratAPMM"),getParam("txtTarikhSuratAPMM")
							,getParam("txtNoRujSuratAPMM"),getParam("txtSiasatanAPMM"),getParam("txtSiasatanJKPTG"),getParam("txtCatatanMaklumbalas"),session);	
	            }
	        	if ("doSimpanKemaskiniKeputusanAduan".equals(hitButton)){
					logic.updateKeputusan(idPengadu, getParam("txtTarikhKeputusan"),getParam("txtUlasanKeputusan"),session);	
	            }
	    	}
	        	
			
	        //HEADER
	        beanHeader = new Vector();
	        logicHeader.setMaklumatPermohonan(idFail, session);
	        beanHeader = logicHeader.getBeanMaklumatPermohonan();
			this.context.put("BeanHeader", beanHeader);
						
			if (beanHeader.size() != 0){
				Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
				
				idFail = hashHeader.get("idFail").toString();
				idPermohonan = hashHeader.get("idPermohonan").toString();
				idPemohon = hashHeader.get("idPemohon").toString();
				idStatus = hashHeader.get("idStatus").toString();	
				flagAktif = (String)hashHeader.get("flagAktif");
			}
			
	        if ("papar".equals(actionAduan)){
	        	
	        	vm = "app/php2/frmAPBAduanDaftar.jsp";
	        	
	        	this.context.put("mode", "view");
	        	this.context.put("readonly", "readonly");
	        	this.context.put("inputTextClass", "disabled");
	        	
	        	}
	        else if ("daftarBaru".equals(actionAduan)){
	        	
	        	 vm = "app/php2/frmAPBAduanDaftar.jsp";
	        	 mode = "new";
	        	this.context.put("mode", "new");
	         	this.context.put("readonly", "");
	         	this.context.put("inputTextClass", "");
	         	
	         	//MAKLUMAT PENDAFTARAN
	         	beanAduan = new Vector();
	 			Hashtable hashPermohonan = new Hashtable();
	 			hashPermohonan.put("noFail", "");
	 			hashPermohonan.put("tarikhTerima", getParam("tarikhTerima") );
	 			hashPermohonan.put("tarikhSurat", getParam("tarikhSurat") );
	 			hashPermohonan.put("perkara", getParam("txtPerkara"));
	 			hashPermohonan.put("rujSurat", getParam("txtRujSurat"));
	 			beanAduan.addElement(hashPermohonan);
	 			this.context.put("BeanAduan", beanAduan);
	 			
	 			//MAKLUMAT PENGADU
	 			this.context.put("selectKategoriPengadu",HTML.SelectKategoriPengaduAPB("socKategoriPengadu", Long.parseLong(idKategoriPengadu), "", " onChange=\"doChangeKategoriPengadu();\""));
	 			
	 			beanMaklumatPengadu = new Vector();
	 			Hashtable hashPemohon = new Hashtable();
	 			hashPemohon.put("nama", getParam("txtNama"));
	 			hashPemohon.put("alamat1", getParam("txtAlamat1"));
	 			hashPemohon.put("alamat2", getParam("txtAlamat2"));
	 			hashPemohon.put("alamat3", getParam("txtAlamat3"));
	 			hashPemohon.put("poskod", getParam("txtPoskod"));		
	 			beanMaklumatPengadu.addElement(hashPemohon);
	 			this.context.put("BeanMaklumatPengadu", beanMaklumatPengadu);
	 			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
	 			
	 			if ("3".equals(idKategoriPengadu)) {
					
					this.context.put("jenisPemohon", "kementerian/agensi");
					this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
					this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
					
					if ("doChangeKementerian".equals(submit)){
						this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
						idAgensi = "99999";
						
					}
					
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idAgensi);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
					
					if ("doChangeAgensi".equals(submit)){
						beanMaklumatAgensi = new Vector();
						logic.setMaklumatAgensi(idAgensi);
						beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
						this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);	
					}
					
					this.context.put("idKementerian", idKementerian);
					this.context.put("idAgensi", idAgensi);
					
				}
	        }
	        else if ("maklumatAduan".equals(actionAduan)){
	        	
	        	vm = "app/php2/frmAPBMaklumatAduan.jsp";
	        	
	        	// MODE VIEW
	    		if("view".equals(mode)){
	    			
	    			this.context.put("readonly", "readonly");
	    			this.context.put("inputTextClass", "disabled");
	    			this.context.put("disabled", "disabled");
	    			
	    			//MAKLUMAT PENDAFTARAN
		        	beanAduan = new Vector();
					logic.setMaklumatPendaftaran(idPengadu);
					beanAduan = logic.getBeanMaklumatPendaftaran();
		 			this.context.put("BeanAduan", beanAduan);
		 			
		 			//MAKLUMAT PENGADU
		 			logic.setMaklumatPemohon(idPengadu);
		 			beanMaklumatPengadu = logic.getBeanMaklumatPengadu();
					this.context.put("BeanMaklumatPengadu", beanMaklumatPengadu);
					
					if (logic.getBeanMaklumatPengadu().size() != 0){
						Hashtable hashPengadu = (Hashtable) logic.getBeanMaklumatPengadu().get(0);
						idKategoriPengadu = (String) hashPengadu.get("idKategoriPengadu");
						idNegeri = (String) hashPengadu.get("idNegeri");
						idAgensi = (String) hashPengadu.get("idAgensi");
						idKementerian = (String) hashPengadu.get("idKementerian");					
					}		
					this.context.put("selectKategoriPengadu",HTML.SelectKategoriPengaduAPB("socKategoriPengadu", Long.parseLong(idKategoriPengadu), "disabled", " class=\"disabled\""));
			
					if("3".equals(idKategoriPengadu)){
						//IF PEMOHON ADALAH KEMENTERIAN/AGENSI
						this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
						this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
						
						beanMaklumatAgensi = new Vector();
						logic.setMaklumatAgensi(idAgensi);
						beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
						this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
					}
					else {
						this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
					}
					
					//MAKLUMAT ADUAN
					beanMaklumatAduan = new Vector();
					logic.setMaklumatAduan(idPengadu);
					beanMaklumatAduan = logic.getBeanMaklumatAduan();
		 			this.context.put("BeanMaklumatAduan", beanMaklumatAduan);
		 			
		 			//MAKLUMAT LAPORAN/ULASAN JKPTG
		 			beanMaklumatSiasatan = new Vector();
					logic.setMaklumatSiasatan(idPengadu);
					beanMaklumatSiasatan = logic.getBeanMaklumatSiasatan();
		 			this.context.put("BeanMaklumatSiasatan", beanMaklumatSiasatan);
		 			
		 			//MAKLUMAT KEPUTUSAN
		 			beanMaklumatKeputusanAduan = new Vector();
					logic.setMaklumatKeputusanAduan(idPengadu);
					beanMaklumatKeputusanAduan = logic.getBeanMaklumatKeputusanAduan();
		 			this.context.put("BeanMaklumatKeputusanAduan", beanMaklumatKeputusanAduan);
			
	    		}
	    		
	    		// MODE UPDATE
	    		else if("update".equals(mode)){
	    			
	    			this.context.put("readonly", "");
	        		this.context.put("inputTextClass", "");
	        		this.context.put("disabled", "");
	        		
	        		//MAKLUMAT PENDAFTARAN
		        	beanAduan = new Vector();
					logic.setMaklumatPendaftaran(idPengadu);
					beanAduan = logic.getBeanMaklumatPendaftaran();
		 			this.context.put("BeanAduan", beanAduan);
	        		
	        		//MAKLUMAT PENGADU
	        		beanMaklumatPengadu = new Vector();
	        		logic.setMaklumatPemohon(idPengadu);
	        		Hashtable hashPengaduDB = (Hashtable) logic.getBeanMaklumatPengadu().get(0);
	        		Hashtable hashPengadu = new Hashtable();
	        		hashPengadu.put("nama",getParam("txtNama") == null ? hashPengaduDB.get("nama") : getParam("txtNama"));
	        		hashPengadu.put("alamat1", getParam("txtAlamat1") == null ? hashPengaduDB.get("alamat1") : getParam("txtAlamat1"));
	        		hashPengadu.put("alamat2", getParam("txtAlamat2") == null ? hashPengaduDB.get("alamat2") : getParam("txtAlamat2"));
	        		hashPengadu.put("alamat3", getParam("txtAlamat3") == null ? hashPengaduDB.get("alamat3") : getParam("txtAlamat3"));
	        		hashPengadu.put("poskod", getParam("txtPoskod")== null ? hashPengaduDB.get("poskod") : getParam("txtPoskod"));	
	        		
	        		// ELLY ALTER 05072010
	        		hashPengadu.put("txtNoHp", getParam("txtNoHp")== null ? hashPengaduDB.get("txtNoHp") : getParam("txtNoHp"));
	        		hashPengadu.put("txtNoTelefon", getParam("txtNoTelefon")== null ? hashPengaduDB.get("txtNoTelefon") : getParam("txtNoTelefon"));
	        		hashPengadu.put("txtEmail", getParam("txtEmail")== null ? hashPengaduDB.get("txtEmail") : getParam("txtEmail"));
	        		
	        		beanMaklumatPengadu.addElement(hashPengadu);
					this.context.put("BeanMaklumatPengadu", beanMaklumatPengadu);
					           			    	    		
    	    		if(beanMaklumatPengadu.size()!= 0){
    	    			Hashtable hashDB = (Hashtable) logic.getBeanMaklumatPengadu().get(0);
    	    			
    	    			if (idKementerian == "99999"){
    	    				idKementerian = (String) hashDB.get("idKementerian");
    	    			}		
    	    			if (idAgensi == "99999"){
    	    				idAgensi = (String) hashDB.get("idAgensi");
    	    			}
    	    			if (idNegeri == "99999"){
    	    				idNegeri = (String) hashDB.get("idNegeri");
    	    			}	
//    	    			if (idKategoriPengadu == "99999"){
//    	    				idKategoriPengadu = (String) hashDB.get("idKategoriPengadu");
//    	    			}	
    	    		}
    				
    	    		this.context.put("selectKategoriPengadu",HTML.SelectKategoriPengaduAPB("socKategoriPengadu", Long.parseLong(idKategoriPengadu), "disabled", " class=\"disabled\""));
    	    		
    	    		if("3".equals(idKategoriPengadu)){
	    	    		this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
	    				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian,Long.parseLong(idAgensi), "", " "));
	    				
	    				if ("doChangeKementerian".equals(submit)){
	    					this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
							idAgensi = "99999";
	    					
	    				}
	    				beanMaklumatAgensi = new Vector();
						logic.setMaklumatAgensi(idAgensi);
						beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
						this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
						
						if ("doChangeAgensi".equals(submit)){
							beanMaklumatAgensi = new Vector();
							logic.setMaklumatAgensi(idAgensi);
							beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
							this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
						}
						
						this.context.put("idKementerian", idKementerian);
						this.context.put("idAgensi", idAgensi);
	    				
    	    		 }
    	    		else {
    	    		
    	    			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
    	    		}
    	    		   	    		
	        		//MAKLUMAT ADUAN
					beanMaklumatAduan = new Vector();
					logic.setMaklumatAduan(idPengadu);
					beanMaklumatAduan = logic.getBeanMaklumatAduan();
		 			this.context.put("BeanMaklumatAduan", beanMaklumatAduan);
		 			
		 			//MAKLUMAT LAPORAN/ULASAN JKPTG
		 			beanMaklumatSiasatan = new Vector();
					logic.setMaklumatSiasatan(idPengadu);
					beanMaklumatSiasatan = logic.getBeanMaklumatSiasatan();
		 			this.context.put("BeanMaklumatSiasatan", beanMaklumatSiasatan);
		 			
		 			//MAKLUMAT KEPUTUSAN
		 			beanMaklumatKeputusanAduan = new Vector();
					logic.setMaklumatKeputusanAduan(idPengadu);
					beanMaklumatKeputusanAduan = logic.getBeanMaklumatKeputusanAduan();
		 			this.context.put("BeanMaklumatKeputusanAduan", beanMaklumatKeputusanAduan);
	    		}
	        }
	        
	        else {
	        	
	        	//GO TO LIST ADUAN        	
	        	vm = "app/php2/frmAPBSenaraiAduan.jsp";
	        	
	        	logic.carianFail(getParam("txtNamaPengadu"),idPermohonan);
				list = new Vector();
				list = logic.getSenaraiFail();
				this.context.put("SenaraiPengadu", list);
				
				this.context.put("txtNamaPengadu", getParam("txtNamaPengadu"));
				
				setupPage(session,action,list);
				
	        }
	        
	        //SET DEFAULT PARAM
	        this.context.put("actionAduan", actionAduan);
	        this.context.put("selectedTabUpper", selectedTabUpper);
	        this.context.put("mode", mode);
			
			//SET ID PARAM
			this.context.put("idFail", idFail);
	        this.context.put("idPermohonan", idPermohonan);
	        this.context.put("idPemohon", idPemohon);
	        this.context.put("idStatus", idStatus);
	        this.context.put("flagAktif", flagAktif);
	        this.context.put("idKategoriPengadu", idKategoriPengadu);
	        this.context.put("idPengadu", idPengadu);
	        
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
				this.context.put("SenaraiPengadu",paging.getPage(page));
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
