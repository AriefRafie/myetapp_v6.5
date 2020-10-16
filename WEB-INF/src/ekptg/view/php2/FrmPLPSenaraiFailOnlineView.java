package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.php2.FrmPLPSenaraiFailOnlineData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.utils.lampiran.ILampiran;

public class FrmPLPSenaraiFailOnlineView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmPLPSenaraiFailOnlineData logic = new FrmPLPSenaraiFailOnlineData();
	FrmSemakan semak = null;
	private ILampiran iLampiran = null;

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		Boolean postDB = false;
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
        String idFail = getParam("idFail");
        String idStatus = getParam("idStatus");
		String idPermohonan = getParam("idPermohonan");
        String idPemohon = getParam("idPemohon");
        String idUrusan = getParam("idUrusan");
        String hitButton = getParam("hitButton");
        String idHakmilikAgensi = getParam("idHakmilikAgensi");
        String idSuburusan = getParam("idSuburusan");

        //VECTOR
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatPemohon= null;
        Vector beanMaklumatAgensi= null;
        Vector beanMaklumatPelepasan = null;
        Vector senaraiSemak = null;

        //GET DROPDOWN PARAM
        String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}

       //ACTION BUTTON
		if (postDB){
        	if ("daftarBaru".equals(hitButton)){
            		logic.updateDaftarOnline(idFail,idPermohonan,getParam("txtPerkara"),idHakmilikAgensi,idSuburusan,session);
        	}
    	}


		this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session, "phptkr"));

        if ("papar".equals(actionOnline)){

        	this.context.put("mode", "view");
        	this.context.put("readonlyTajuk", "readonly");
        	this.context.put("inputTextClassTajuk", "disabled");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	this.context.put("disabled", "disabled");

        	vm = "app/php2/frmPLPDaftarOnline.jsp";

        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

       		if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				idSuburusan = (String) hashPermohonan.get("idSuburusan");
			}

       		this.context.put("selectSubUrusanPelepasanMain", HTML.SelectSubUrusanPelepasan(idSuburusan,"soSubUrusanPelepasanMain", Long.parseLong(idSuburusan),"disabled", " class=\"disabled\""));

       		// MAKLUMAT PEMOHON
           	beanMaklumatPemohon = new Vector();
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idKementerian = (String) hashPemohon.get("idKementerian");
				idAgensi = (String) hashPemohon.get("idAgensi");

			}

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPelepasan("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));

			beanMaklumatAgensi = new Vector();
			logic.setMaklumatAgensi(idAgensi);
			beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
			this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);

        	// MAKLUMAT TANAH
			beanMaklumatTanah = new Vector();
			idHakmilikAgensi = logic.getIdHakmilik(idFail);
			logic.setMaklumatTanah(idHakmilikAgensi);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

			//MAKLUMAT PELEPASAN
			beanMaklumatPelepasan = new Vector();
			logic.setMaklumatPelepasan(idPermohonan);
			beanMaklumatPelepasan = logic.getBeanMaklumatPelepasan();
			this.context.put("BeanMaklumatPelepasan", beanMaklumatPelepasan);
			if (beanMaklumatPelepasan.size() != 0){
    			Hashtable hashMaklumatPelepasan = (Hashtable) logic.getBeanMaklumatPelepasan().get(0);
        		if (hashMaklumatPelepasan.get("flagGuna") != null && hashMaklumatPelepasan.get("flagGuna").toString().trim().length() != 0){
        			idLuasKegunaan = (String) hashMaklumatPelepasan.get("flagGuna");
        		} else {
        			idLuasKegunaan = "99999";
        		}
        		if (hashMaklumatPelepasan.get("idLuasMohon") != null && hashMaklumatPelepasan.get("idLuasMohon").toString().trim().length() != 0){
        			idLuas = (String) hashMaklumatPelepasan.get("idLuasMohon");
        		} else {
        			idLuas = "99999";
        		}
    		}

			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));

        }  else if ("daftar".equals(actionOnline)){

           	this.context.put("mode", "new");
        	this.context.put("readonlyTajuk", "");
        	this.context.put("inputTextClassTajuk", "");
        	this.context.put("readonly", "readonly");
        	this.context.put("inputTextClass", "disabled");
        	this.context.put("disabled", "disabled");

        	vm = "app/php2/frmPLPDaftarOnline.jsp";

        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

       		if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				idSuburusan = (String) hashPermohonan.get("idSuburusan");
			}

           	this.context.put("selectSubUrusanPelepasanMain", HTML.SelectSubUrusanPelepasan(idSuburusan,"soSubUrusanPelepasanMain", Long.parseLong(idSuburusan),"disabled", " class=\"disabled\""));

           	// MAKLUMAT PEMOHON
           	beanMaklumatPemohon = new Vector();
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idKementerian = (String) hashPemohon.get("idKementerian");
				idAgensi = (String) hashPemohon.get("idAgensi");
			}

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPelepasan("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));

			beanMaklumatAgensi = new Vector();
			logic.setMaklumatAgensi(idAgensi);
			beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
			this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);

           	// MAKLUMAT TANAH
			beanMaklumatTanah = new Vector();
			idHakmilikAgensi = logic.getIdHakmilik(idFail);
			logic.setMaklumatTanah(idHakmilikAgensi);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

			//MAKLUMAT PELEPASAN
			beanMaklumatPelepasan = new Vector();
			logic.setMaklumatPelepasan(idPermohonan);
			beanMaklumatPelepasan = logic.getBeanMaklumatPelepasan();
			this.context.put("BeanMaklumatPelepasan", beanMaklumatPelepasan);
			if (beanMaklumatPelepasan.size() != 0){
    			Hashtable hashMaklumatPelepasan = (Hashtable) logic.getBeanMaklumatPelepasan().get(0);
        		if (hashMaklumatPelepasan.get("flagGuna") != null && hashMaklumatPelepasan.get("flagGuna").toString().trim().length() != 0){
        			idLuasKegunaan = (String) hashMaklumatPelepasan.get("flagGuna");
        		} else {
        			idLuasKegunaan = "99999";
        		}
        		if (hashMaklumatPelepasan.get("idLuasMohon") != null && hashMaklumatPelepasan.get("idLuasMohon").toString().trim().length() != 0){
        			idLuas = (String) hashMaklumatPelepasan.get("idLuasMohon");
        		} else {
        			idLuas = "99999";
        		}
    		}
			this.context.put("idLuas",idLuas);
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));

			semak = new FrmSemakan();
			if(idSuburusan.equals("32")){
				senaraiSemak = semak.getSenaraiSemakanAttach2("phppnw",idPermohonan);
			}else{
				senaraiSemak = semak.getSenaraiSemakanAttach2("phptukar",idPermohonan);
			}
			this.context.put("SenaraiSemak", senaraiSemak);
        }
        else {
	     	// GO TO LIST FAIL PELEPASAN
			vm = "app/php2/frmPLPSenaraiFailOnline.jsp";

			logic.carianFail(getParam("txtNoPermohonan"),getParam("txdTarikhPermohonan"));
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);
			this.context.put("txtNoPermohonan", getParam("txtNoPermohonan"));
			this.context
					.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));

			setupPage(session, action, list);
        }

		//SET DEFAULT ID PARAM
		this.context.put("actionOnline", actionOnline);
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idPemohon", idPemohon);
	    this.context.put("idHakmilikAgensi", idHakmilikAgensi);
	    this.context.put("idUrusan", idUrusan);
	    this.context.put("idSuburusan", idSuburusan);
	    this.context.put("idLuasKegunaan", idLuasKegunaan);
	    this.context.put("idLuas", idLuas);

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
	private ILampiran getDocPHP(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;

	}
}

