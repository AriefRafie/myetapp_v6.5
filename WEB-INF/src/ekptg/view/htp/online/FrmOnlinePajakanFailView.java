/**
 * 
 */
package ekptg.view.htp.online;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmOnlinePajakanHeaderData;
//import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmOnlinePajakanSenaraiFailData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
/**
 * 
 *
 */
public class FrmOnlinePajakanFailView extends AjaxBasedModule {

	private final String PATH="app/htp/online/";
	private static final long serialVersionUID = 1L;
	
	FrmOnlinePajakanHeaderData logicHeader = new FrmOnlinePajakanHeaderData();
	FrmOnlinePajakanSenaraiFailData logic = new FrmOnlinePajakanSenaraiFailData();
	private static Logger log = Logger.getLogger(ekptg.view.online.htp.pajakan.FrmPajakanSenaraiFailViewOnline.class);
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private HakmilikUrusan urusan = null;
	private IPembelian iPembelian = null;
	private HtpPermohonan htpPermohonan = null;

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
        String actionPajakan = getParam("actionPajakan");
        String submit = getParam("command");   
        String mode = getParam("mode");
        String hitButton = getParam("hitButton");
        
        //GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String subUrusan = getParam("subUrusan");
        String idHakmilikUrusan = getParam("idHakmilikUrusan");
        String idUser = (String) session.getAttribute("_ekptg_user_id");
        
        //VECTOR
        Vector beanHeader = null;
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector senaraiHakmilik = null;
        Vector beanMaklumatPemohon = null;
        
        //GET DROPDOWN PARAM
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
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		String idStatusTanah = getParam("socStatusTanah");
		if (idStatusTanah == null || idStatusTanah.trim().length() == 0){
			idStatusTanah = "99999";
		}
		String idJenisFail = getParam("socJenisFail");
		if (idJenisFail == null || idJenisFail.trim().length() == 0){
			idJenisFail = "99999";
		}
		log.info("actionPajakan:"+actionPajakan);
		//HITBUTTON
		if (postDB){
    		if ("simpan".equals(hitButton)){
    			log.info("simpan");
         		idFail = logic.simpan(idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
         		session.setAttribute("idFail", idFail);
        	}
        	if ("simpanbyswasta".equals(hitButton)){
        		log.info("simpanbyswasta");
         		idFail = logic.simpan(idPermohonan,idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
         		session.setAttribute("idFail", idFail);
        	}
    	}
		
		if ("papar".equals(actionPajakan)){
			vm = "app/htp/online/frmPajakanDaftarOnline.jsp";
			//GO TO PAPAR PAJAKAN  
			logicHeader.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = new Vector<Hashtable<String, String>>();

    		beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
    		
			this.context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri", Long.parseLong(idNegeri), " disabled class=disabled", ""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled class=disabled", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), " disabled class=disabled", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), " disabled class=disabled", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), " disabled class=disabled", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), " disabled class=disabled", ""));

        	this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
        	MaklumatPermohonanView("view");
        	this.context.put("mode", mode);
		}
		
		//ACTION PAJAKAN
		else if ("daftarBaru".equals(actionPajakan)){  
			
		//if ("daftarBaru".equals(submit)){       	
        	//GO TO DAFTAR BARU PAJAKAN   
        	vm = "app/htp/online/frmPajakanDaftarOnline.jsp";
        	log.info("daftarBaru :: "+vm);
        	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PEMOHON
        	Vector<Hashtable<String,String>> vec = logicHeader.setMaklumatPemohon(idUser);
			this.context.put("pemohon", vec.get(0));
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			this.context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri", Long.parseLong(idNegeri), "", ""));
			//this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian),"", "onChange=\"doChangeKementerian();\""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), "", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
        	
        }else if ("daftarBaruSwasta".equals(actionPajakan)){  
  
		//if ("daftarBaru".equals(submit)){       	
        	//GO TO DAFTAR BARU PAJAKAN   
        	vm = PATH+"frmPajakanPenerimaanPermohonanDaftarSwasta.jsp";
        	log.info("daftarBaruSwasta :: "+vm);
        	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PERMOHONAN
        	// idHakmilik (parameter dari skrin)
			urusan = getIPembelian().findByHakmilikUrusanId(getParam("idHakmilik"));
			//idPermohonan = urusan.getPermohonan().getNoPermohonan(); 
			htpPermohonan = getIPembelian().findPermohonan(urusan.getPermohonan().getNoPermohonan(), "");
			fail = htpPermohonan.getPermohonan().getPfdFail();
			
        	beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			this.context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri", Long.parseLong(urusan.getIdNegeri()), "", ""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", " onChange=\"doChangeKementerian();\""));//fail.getIdKementerian(), "",
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,htpPermohonan.getIdAgensi(), "", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(String.valueOf("18")), " disabled", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
        	
        } else {       	
        	//GO TO LIST FAIL PAJAKAN        	
        	vm = "app/htp/online/frmPajakanSenaraiFail.jsp";
        	log.info("Else :: "+vm);
        	logic.carianFailOnline2(getParam("txtNoFail"), getParam("txdTarikhTerima"),getParam("txtTajukFail"),getParam("txtNamaPemohon"),idUser);
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("senaraiFail", list);
			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			
			setupPage(session,action,list);
        }
        
        //SET DEFAULT PARAM
		this.context.put("actionPajakan", actionPajakan);
		
		//SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("subUrusan", subUrusan);
        
		log.info(vm);
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
	
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 
	
	public void MaklumatPermohonanView(String mode) throws Exception{
		try {
			if (mode.equalsIgnoreCase("view")){    			
				this.context.put("readOnly", "readOnly");
	        	this.context.put("classDis", "disabled");
	        	this.context.put("inputTextClass", "disabled");
	        
	        //mode = update		
	        }else if(mode.equalsIgnoreCase("update")){	    			
	        	this.context.put("readOnly", "");
	        	this.context.put("classDis", "");
	    	}

		} catch (Exception e){
			e.printStackTrace();
	    }
		
	}
}
