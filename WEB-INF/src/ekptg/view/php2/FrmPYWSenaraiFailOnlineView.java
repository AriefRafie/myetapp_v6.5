package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.php2.FrmPYWSenaraiFailOnlineData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.php2.utiliti.PHPUtilHTML;
import ekptg.model.utils.lampiran.ILampiran;

public class FrmPYWSenaraiFailOnlineView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPYWSenaraiFailOnlineData logic = new FrmPYWSenaraiFailOnlineData();
	private ILampiran iLampiran = null;
	FrmSemakan semak = null;
	
	String userId = null;
	String idNegeriUser = null;
	
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    userId = (String)session.getAttribute("_ekptg_user_id");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
		
		this.context.put("userId", userId);
		this.context.put("idNegeriUser", idNegeriUser);
	   	    	    
	    // DROP DOWN PENDAFTARAN
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionOnline = getParam("actionOnline");
        String submit = getParam("command");   
        
        //GET ID PARAM
        String idFail = getParam("idFail");
        String idStatus = getParam("idStatus");
		String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idUrusan = getParam("idUrusan");
        String hitButton = getParam("hitButton");
        String idHakmilikAgensi = getParam("idHakmilikAgensi");
        String idSuburusan = getParam("idSuburusan");
        String idSubsuburusan = getParam("idSuburusan");
		        
        //VECTOR
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatPemohon = null;
        Vector senaraiSemak = null;
        
        String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0) {
			idBandar = "99999";
		}
        
       //ACTION BUTTON
		if (postDB){
        	if ("daftarBaru".equals(hitButton)){
        		logic.updateDaftarOnline(idUrusan,idFail,idPermohonan,getParam("txtNoFailNegeri"),getParam("txtPerkara"),getParam("txtCatatan"),
        				idHakmilikAgensi,idSuburusan,session);
        	}
    	}
	    		
        if ("papar".equals(actionOnline)){
        	
        	this.context.put("mode", "view");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	
        	vm = "app/php2/frmPYWDaftarOnline.jsp";
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

       		if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				idSuburusan = (String) hashPermohonan.get("idSuburusan");
				idSubsuburusan = (String) hashPermohonan.get("idSubsuburusan");
			}
	
         	this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled"," class=\"disabled\""));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan),"disabled", " class=\"disabled\""));
			this.context.put("selectSubSuburusan", PHPUtilHTML.SelectSubsuburusanByIdSuburusan(idSuburusan, "socSubsuburusan", Long.parseLong(idSubsuburusan), "disabled", " class=\"disabled\""));

			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
			}
			
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
			
			// MAKLUMAT TANAH
			beanMaklumatTanah = new Vector();
			idHakmilikAgensi = logic.getIdHakmilik(idFail);
			logic.setMaklumatTanah(idHakmilikAgensi);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
           		      	
        	
        }  else if ("daftar".equals(actionOnline)){
        	
           	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	vm = "app/php2/frmPYWDaftarOnline.jsp";
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

       		if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				idSuburusan = (String) hashPermohonan.get("idSuburusan");
				idSubsuburusan = (String) hashPermohonan.get("idSubsuburusan");
			}

   			this.context.put("selectUrusan",HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled", " class=\"disabled\""));
   			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "disabled", " class=\"disabled\""));
			this.context.put("selectSubSuburusan", PHPUtilHTML.SelectSubsuburusanByIdSuburusan(idSuburusan, "socSubsuburusan", Long.parseLong(idSubsuburusan), "disabled", " class=\"disabled\""));
    			
   			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
			}
			
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
			
			
   			//MAKLUMAT TANAH
   			beanMaklumatTanah = new Vector();
   			idHakmilikAgensi = logic.getIdHakmilik(idFail);
   			logic.setMaklumatTanah(idHakmilikAgensi);
   			beanMaklumatTanah = logic.getBeanMaklumatTanah();
   			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
   			
   			//SENARAI SEMAK
			semak = new FrmSemakan();
			if ("1".equals(idKategoriPemohon)) {
				senaraiSemak = semak.getSenaraiSemakanAttach("phppywindividu",idPermohonan);
			} 
			else if ("2".equals(idKategoriPemohon)) {
				senaraiSemak = semak.getSenaraiSemakanAttach("phppywsyarikat",idPermohonan);
			}
			this.context.put("SenaraiSemak", senaraiSemak);
			
        }
        else {
        	
        	//FILTER BY NEGERI
			if (idNegeriUser != null && idNegeriUser.length() > 0){
				idNegeri = idNegeriUser;
			}
        	
	     	// GO TO LIST FAIL PYW
			vm = "app/php2/frmPYWSenaraiFailOnline.jsp";
	
			logic.carianFail(getParam("txtNoPermohonan"),getParam("txdTarikhPermohonan"), getParam("txtPemohon"), 
					getParam("txtNoPengenalan"), idNegeri);
			list = new Vector();
			list = logic.getSenaraiFail(); 
			this.context.put("SenaraiFail", list);
			this.context.put("txtNoPermohonan", getParam("txtNoPermohonan"));
			this.context.put("txtPemohon", getParam("txtPemohon"));
			this.context.put("txtNoPengenalan", getParam("txtNoPengenalan"));
			this.context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
	
			setupPage(session, action, list);
        }
		
		//SET DEFAULT ID PARAM
		this.context.put("actionOnline", actionOnline);
//		this.context.put("mode", mode);
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idPemohon", idPemohon);
	    this.context.put("idHakmilikAgensi", idHakmilikAgensi);
	    this.context.put("idUrusan", idUrusan);
	    this.context.put("idSuburusan", idSuburusan);
	    this.context.put("idSubsuburusan", idSubsuburusan);
		return vm;
	}
	
	private ILampiran getDocPHP(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;
				
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
	
