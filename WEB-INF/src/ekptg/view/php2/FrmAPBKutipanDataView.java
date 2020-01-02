package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmAPBKutipanData;

public class FrmAPBKutipanDataView extends AjaxBasedModule {
	
	FrmAPBKutipanData logic = new FrmAPBKutipanData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionLesen = getParam("actionLesen");
        String submit = getParam("command");   
        
        //GET ID PARAM
        String idFail = getParam("idFail");
        String idFailLama = getParam("idFailLama");
        String idStatus = getParam("idStatus");
        
        //VECTOR
        Vector list = null;
        
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
		
		//ACTION BUTTON
		if (postDB){
        	if ("doDaftarBaru".equals(submit)){
        		idFail = daftarBaru(session);
        	}
    	}
        
		if ("papar".equals(actionLesen)){
			
			//GO TO VIEW LESEN      
			vm = "app/php2/frmAPBKutipanData.jsp";
			
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
		
		} else {
        	
        	//GO TO DAFTAR BARU LESEN        	
        	vm = "app/php2/frmAPBKutipanData.jsp";
        	
        	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail",getParam("noFail") == null  || "".equals(getParam("noFail"))? "JKPTG(S)/SPHP/8-2 SK" : getParam("noFail"));
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
				context.put("namaNegeriPerairan", logic.getNamaNegeriByIdNegeri(idNegeriPerairan));
			}
			
			if ("doChangeNoPendaftaran".equals(submit)){
				beanMaklumatPemohon = new Vector();
				idFailLama = logic.getNoFailByNoPendaftaran(getParam("txtNoPengenalanSykt"));
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
        } 
        
        //SET DEFAULT PARAM
		this.context.put("actionLesen", actionLesen);
		
		//SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
        
		return vm;
	}

	private String daftarBaru(HttpSession session) throws Exception {
		String idFail = "";
		
		Hashtable hash = new Hashtable();
		
		hash.put("noFail",getParam("noFail"));
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

}
