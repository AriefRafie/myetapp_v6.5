package ekptg.view.php2.online;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.online.FrmAPBOnlineSenaraiFailData;

public class FrmAPBOnlineSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmAPBOnlineSenaraiFailData logic = new FrmAPBOnlineSenaraiFailData();
	
	String userId = null;
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    	    
	    //DROP DOWN PENDAFTARAN
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionOnline = getParam("actionOnline");
        String submit = getParam("command");   
        
        //GET ID PARAM
        String idFailSession = "";
        if (session.getAttribute("idFail")!= null){
        	idFailSession =(String) session.getAttribute("idFail");
        }
        String idFail = getParam("idFail");
        String idStatus = getParam("idStatus");
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
	    //if(session.getAttribute("user_id") != null){
	    //    userId =  (String) session.getAttribute("user_id");
	    //}
		//this.context.put("userId",userId);
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String idPermohonan = getParam("idPermohonan");
		//String idPengarah = getParam("idPengarah");
        //String idPembeliPasir = getParam("idPembeliPasir");
        //String idProjek = getParam("idProjek");
        //String idPakar = getParam("idPakar");
        //String idKoordinat = getParam("idKoordinat");
        String idPemohon = getParam("idPemohon");
        
        //VECTOR
        Vector list = null;
        Vector beanHeader = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanMaklumatPemohon = null;
        
        //GET DROPDOWN PARAM
        String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0){
			idKategoriPemohon = "99999";
		}
		String idJenisPengenalanIndividu = getParam("socJenisPengenalanIndividu");
		if (idJenisPengenalanIndividu == null || idJenisPengenalanIndividu.trim().length() == 0){
			idJenisPengenalanIndividu = "99999";
		}
		String idJantina = getParam("socJantina");
		if (idJantina == null || idJantina.trim().length() == 0){
			idJantina = "99999";
		}
		String idBangsa = getParam("socBangsa");
		if (idBangsa == null || idBangsa.trim().length() == 0){
			idBangsa = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0){
			idBandar = "99999";
		}
		String idNegeriSykt = getParam("socNegeriSykt");
		if (idNegeriSykt == null || idNegeriSykt.trim().length() == 0){
			idNegeriSykt = "99999";
		}
		String idBandarSykt = getParam("socBandarSykt");
		if (idBandarSykt == null || idBandarSykt.trim().length() == 0){
			idBandarSykt = "99999";
		}		
		String idFlagLuar = getParam("socFlagLuar");
		if (idFlagLuar == null || idFlagLuar.trim().length() == 0){
			idFlagLuar = "99999";
		}
		String idNegeriPerairan = getParam("socNegeriPerairan");
		if (idNegeriPerairan == null || idNegeriPerairan.trim().length() == 0){
			idNegeriPerairan = "99999";
		}
		
		this.context.put("onload", "");
	
		//SAVE TO DB
		if (postDB){
        	if ("doDaftarBaru".equals(submit)){
        		idFail = daftarBaru(session);
        	}
    	}
			
        if ("papar".equals(actionOnline)){
        	
        	//GO TO DAFTAR BARU APB    
        	vm = "app/php2/online/frmAPBDaftarManual.jsp";
   
        	this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			//MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
			
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idJenisPengenalanIndividu = (String) hashPemohon.get("idJenisPengenalan");
				idJantina = (String) hashPemohon.get("idJantina");
				idBangsa = (String) hashPemohon.get("idBangsa");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
				idNegeriSykt = (String) hashPemohon.get("idNegeriSykt");
				idBandarSykt = (String) hashPemohon.get("idBandarSykt");
			}
			this.context.put("selectKategoriPemohon",HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			
			//INDIVIDU
			this.context.put("selectJenisPengenalanIndividu",HTML.SelectJenisNoPbIndividu("socJenisPengenalanIndividu", Long.parseLong(idJenisPengenalanIndividu), "disabled", " class=\"disabled\""));
			this.context.put("selectJantina",HTML.SelectJantina("socJantina", Long.parseLong(idJantina), "disabled", " class=\"disabled\""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"disabled", " class=\"disabled\""));
			this.context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
			
			//SYARIKAT
			this.context.put("selectNegeriSykt",HTML.SelectNegeri("socNegeriSykt",Long.parseLong(idNegeriSykt),"disabled", " class=\"disabled\""));
			this.context.put("selectBandarSykt",HTML.SelectBandarByNegeri(idNegeriSykt, "socBandarSykt", Long.parseLong(idBandarSykt), "disabled", " class=\"disabled\""));
			
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"disabled", " class=\"disabled\""));
			this.context.put("selectNegeriPerairan",HTML.SelectNegeri("socNegeriPerairan",Long.parseLong(idNegeriPerairan),"disabled", " class=\"disabled\""));
		
		} else if ("daftarBaru".equals(actionOnline)){
			
			       		
        	//GO TO DAFTAR BARU APB        	
        	vm = "app/php2/online/frmAPBDaftarManual.jsp";
        	
           	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	this.context.put("disabled","");
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("tarikhTerima", getParam("tarikhTerima") );
			hashPermohonan.put("tarikhSurat", getParam("tarikhSurat") );
			hashPermohonan.put("noRujSurat", getParam("txtNoRujukanSurat"));
			hashPermohonan.put("perkara", getParam("txtPerkara"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			//MAKLUMAT PEMOHON
			this.context.put("selectKategoriPemohon",HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "", " onChange=\"doChangeKategoriPemohon();\""));
			
			beanMaklumatPemohon = new Vector();
			Hashtable hashPemohon = new Hashtable();			
			//INDIVIDU
			hashPemohon.put("nama", getParam("txtNama"));
			hashPemohon.put("noPengenalan", getParam("txtNoPengenalan"));
			hashPemohon.put("pekerjaan", getParam("txtPekerjaan"));			
			hashPemohon.put("alamat1", getParam("txtAlamat1"));
			hashPemohon.put("alamat2", getParam("txtAlamat2"));
			hashPemohon.put("alamat3", getParam("txtAlamat3"));
			hashPemohon.put("poskod", getParam("txtPoskod"));
			hashPemohon.put("noTel", getParam("txtNoTel"));
			hashPemohon.put("noTelBim", getParam("txtNoTelBimbit"));
			hashPemohon.put("noFax", getParam("txtNoFax"));
			hashPemohon.put("emel", getParam("txtEmel"));
			
			//SYARIKAT
			hashPemohon.put("namaSykt", getParam("txtNamaSykt"));
			hashPemohon.put("noPengenalanSykt", getParam("txtNoPengenalanSykt"));
			hashPemohon.put("pekerjaanSykt", getParam("txtPekerjaanSykt"));			
			hashPemohon.put("alamat1Sykt", getParam("txtAlamat1Sykt"));
			hashPemohon.put("alamat2Sykt", getParam("txtAlamat2Sykt"));
			hashPemohon.put("alamat3Sykt", getParam("txtAlamat3Sykt"));
			hashPemohon.put("poskodSykt", getParam("txtPoskodSykt"));
			hashPemohon.put("noTelSykt", getParam("txtNoTelSykt"));
			hashPemohon.put("noFaxSykt", getParam("txtNoFaxSykt"));
			hashPemohon.put("emelSykt", getParam("txtEmelSykt"));
			
			beanMaklumatPemohon.addElement(hashPemohon);
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
			
			//INDIVIDU
			this.context.put("selectJenisPengenalanIndividu",HTML.SelectJenisNoPbIndividu("socJenisPengenalanIndividu", Long.parseLong(idJenisPengenalanIndividu), ""));
			this.context.put("selectJantina",HTML.SelectJantina("socJantina", Long.parseLong(idJantina), ""));
			this.context.put("selectBangsa",HTML.SelectBangsa("socBangsa", Long.parseLong(idBangsa), ""));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
			
			//SYARIKAT
			this.context.put("selectNegeriSykt",HTML.SelectNegeri("socNegeriSykt",Long.parseLong(idNegeriSykt),"","onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandarSykt",HTML.SelectBandarByNegeri(idNegeriSykt, "socBandarSykt", Long.parseLong(idBandarSykt), ""));
			
			this.context.put("selectFlagLuar",HTML.SelectFlagYaTidak("socFlagLuar", Long.parseLong(idFlagLuar),"", " style=\"width:100px\""));
			this.context.put("selectNegeriPerairan",HTML.SelectNegeri("socNegeriPerairan", Long.parseLong(idNegeriPerairan), " onChange=\"doChangeNegeriPerairan();\""));
			if ("doChangeNegeriPerairan".equals(submit)){
				context.put("namaNegeriPerairan", logic.getNamaNegeriByIdNegeri(idNegeriPerairan).replace("NEGERI", "").trim());
			}
			
			if ("doChangeNoPendaftaran".equals(submit)){
				beanMaklumatPemohon = new Vector();
				String idFailLama = logic.getNoFailByNoPendaftaran(getParam("txtNoPengenalanSykt"));
				logic.setMaklumatPemohon(idFailLama);
				beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
				this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
				if (logic.getBeanMaklumatPemohon().size() != 0){
					Hashtable hashPemohonDB = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
					idNegeriSykt = (String) hashPemohonDB.get("idNegeriSykt");
					idBandarSykt = (String) hashPemohonDB.get("idBandarSykt");
				}
				this.context.put("selectNegeriSykt",HTML.SelectNegeri("socNegeriSykt",Long.parseLong(idNegeriSykt),"", "onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandarSykt",HTML.SelectBandarByNegeri(idNegeriSykt, "socBandarSykt", Long.parseLong(idBandarSykt), "", ""));
				
				if (idFailLama.isEmpty()){
					
					beanMaklumatPemohon = new Vector();
					Hashtable hashP = new Hashtable();
					hashP.put("namaSykt", getParam("txtNamaSykt"));
					hashP.put("noPengenalanSykt", getParam("txtNoPengenalanSykt"));
					hashP.put("pekerjaanSykt", "");			
					hashP.put("alamat1Sykt", "");
					hashP.put("alamat2Sykt", "");
					hashP.put("alamat3Sykt", "");
					hashP.put("poskodSykt", "");
					hashP.put("noTelSykt", "");
					hashP.put("noFaxSykt", "");
					hashP.put("emelSykt", "");
					beanMaklumatPemohon.addElement(hashP);
					this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
					idNegeriSykt = "99999";
					idNegeriSykt = "99999";
					this.context.put("selectNegeriSykt",HTML.SelectNegeri("socNegeriSykt",Long.parseLong(idNegeriSykt),"","onChange=\"doChangeNegeri();\""));
					this.context.put("selectBandarSykt",HTML.SelectBandarByNegeri(idNegeriSykt, "socBandarSykt", Long.parseLong(idBandarSykt), ""));
				}				
			}
        	
        } else {
        	
        	String idStatusC = getParam("socStatusC");
			if (idStatusC == null || idStatusC.trim().length() == 0) {
				idStatusC = "99999";
			}

			// GO TO MAKLUMAT PERMOHONAN  
       		vm = "app/php2/online/frmAPBMaklumatPermohonan.jsp";
			
			//CARIAN      	
			vm = "app/php2/online/frmAPBSenaraiFail.jsp";

			logic.carianFail(getParam("txtNoFail"),getParam("txtPemohon"),getParam("txdTarikhTerima"),idStatusC);
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);
			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtPemohon", getParam("txtPemohon"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			this.context.put("selectStatus", HTML.SelectStatusAPB("socStatusC", Long.parseLong(idStatusC), "", ""));
			setupPage(session,action,list);
        }	
        
        //SET DEFAULT PARAM
		this.context.put("actionOnline", actionOnline);	
		this.context.put("mode",mode);
		
		//SET DEFAULT ID PARAM
        this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
        
		return vm;
	}

	private String daftarBaru(HttpSession session) throws Exception {
		String idFail = "";
		
		Hashtable hash = new Hashtable();

		hash.put("tarikhTerima", getParam("tarikhTerima"));
		hash.put("tarikhSurat", getParam("tarikhSurat"));
		hash.put("noRujSurat", getParam("txtNoRujukanSurat"));
		hash.put("perkara", getParam("txtPerkara"));
		hash.put("idKategoriPemohon", getParam("socKategoriPemohon"));
		
		hash.put("nama", getParam("txtNama"));
		hash.put("idJenisPengenalanIndividu", getParam("socJenisPengenalanIndividu"));
		hash.put("noPengenalan", getParam("txtNoPengenalan"));
		hash.put("pekerjaan", getParam("txtPekerjaan"));
		hash.put("idJantina", getParam("socJantina"));
		hash.put("idBangsa", getParam("socBangsa"));
		hash.put("alamat1", getParam("txtAlamat1"));
		hash.put("alamat2", getParam("txtAlamat2"));
		hash.put("alamat3", getParam("txtAlamat3"));		
		hash.put("poskod", getParam("txtPoskod"));
		hash.put("idNegeri", getParam("socNegeri"));
		hash.put("idBandar", getParam("socBandar"));		
		hash.put("noTel", getParam("txtNoTel"));
		hash.put("noTelBim", getParam("txtNoTelBimbit"));
		hash.put("noFax", getParam("txtNoFax"));
		hash.put("emel", getParam("txtEmel"));
		
		hash.put("namaSykt", getParam("txtNamaSykt"));
		hash.put("noPengenalanSykt", getParam("txtNoPengenalanSykt"));
		hash.put("pekerjaanSykt", getParam("txtPekerjaanSykt"));
		hash.put("alamat1Sykt", getParam("txtAlamat1Sykt"));
		hash.put("alamat2Sykt", getParam("txtAlamat2Sykt"));
		hash.put("alamat3Sykt", getParam("txtAlamat3Sykt"));		
		hash.put("poskodSykt", getParam("txtPoskodSykt"));
		hash.put("idNegeriSykt", getParam("socNegeriSykt"));
		hash.put("idBandarSykt", getParam("socBandarSykt"));		
		hash.put("noTelSykt", getParam("txtNoTelSykt"));
		hash.put("noFaxSykt", getParam("txtNoFaxSykt"));
		hash.put("emelSykt", getParam("txtEmelSykt"));
		
		hash.put("idFlagLuar", getParam("socFlagLuar"));
		hash.put("idNegeriPerairan", getParam("socNegeriPerairan"));
		
		idFail = logic.daftarBaru(hash, session);
		
		return idFail;
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
}