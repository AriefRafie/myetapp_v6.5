package ekptg.view.online.htp.pajakan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanSenaraiFailData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HTPeringatanBean;
import ekptg.model.htp.IHTPeringatan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;

public class FrmPemajak extends AjaxBasedModule {

	private final String PATH="app/htp/pajakan/online/";
	private IHTPeringatan iHTPP = null;  
	private IOnline iOnline = null;
	private static final long serialVersionUID = 1L;

	FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
	FrmPajakanSenaraiFailData logic = new FrmPajakanSenaraiFailData();
	private static Logger myLog = Logger.getLogger(ekptg.view.online.htp.pajakan.FrmPemajak.class);
	SimpleDateFormat sdfTahun = new SimpleDateFormat("yyyy");
	SimpleDateFormat sdfBulan = new SimpleDateFormat("MM");
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private HakmilikUrusan urusan = null;
	private IPembelian iPembelian = null;
	private HtpPermohonan htpPermohonan = null;
	private String userLogin = "";

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		userLogin = (String)session.getAttribute("_portal_login");	
		myLog.info(userLogin);
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
        
        //VECTOR
        Vector beanHeader = null;
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector senaraiHakmilik = null;
        
        //GET DROPDOWN PARAM
        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "";
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
		myLog.info("actionPajakan:"+actionPajakan);
		//HITBUTTON
		if (postDB){
    		if ("simpan".equals(hitButton)){
    			myLog.info("simpan");
         		idFail = logic.simpan(idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
         		session.setAttribute("idFail", idFail);
        	}
        	if ("simpanbyswasta".equals(hitButton)){
        		myLog.info("simpanbyswasta");
         		idFail = logic.simpan(idPermohonan,idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
         		session.setAttribute("idFail", idFail);
        	}
    	}
		
		//ACTION PAJAKAN
		if ("daftarBaru".equals(actionPajakan)){  
			
		//if ("daftarBaru".equals(submit)){       	
        	//GO TO DAFTAR BARU PAJAKAN   
        	vm = "app/htp/frmPajakanPenerimaanPermohonanDaftar.jsp";
        	myLog.info("daftarBaru :: "+vm);
        	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
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
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(getParam("socKementerian")),"", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), "", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
        	
        }else if ("daftarBaruSwasta".equals(actionPajakan)){  
  
		//if ("daftarBaru".equals(submit)){       	
        	//GO TO DAFTAR BARU PAJAKAN   
        	vm = PATH+"frmPajakanPenerimaanPermohonanDaftarSwasta.jsp";
        	myLog.info("daftarBaruSwasta :: "+vm);
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
        }else if("bayaran".equals(actionPajakan)){
            vm  = PATH+"senaraiBayaran.jsp";

        	 beanHeader = new Vector();
        	 logicHeader.setMaklumatPermohonan(idFail);
        	 beanHeader = logicHeader.getBeanMaklumatPermohonan();
        	 this.context.put("BeanHeader", beanHeader);
		
			if (beanHeader.size() != 0){
				Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
				idFail = hashHeader.get("idFail").toString();
				idPermohonan = hashHeader.get("idPermohonan").toString();
				idStatus = hashHeader.get("idStatus").toString();
				subUrusan = hashHeader.get("subUrusan").toString();
				
//				 if(actionPajakan.equalsIgnoreCase("BayaranPajakan")){       	
//			        
//	//		        logic.setListMaklumatBayaran(idPermohonan);
//					 logic.setListMaklumatBayaran(idFailSession);
//			        BayaranView(mode, idBayaran);
//			        	
//			     }
			}
        } else {       	
        	//GO TO LIST FAIL PAJAKAN        	
        	vm = PATH+"index.jsp";
        	//logic.carianFailOnline2(getParam("txtNoFail"), getParam("txdTarikhTerima"),getParam("txtTajukFail"),getParam("txtNamaPemohon"),idKementerian);
			list = new Vector();
			//list = logic.getSenaraiFail();
			// _portal_login for login id 
			list = getIOnline().senaraiFailPemajak(userLogin);
			this.context.put("SenaraiFail", list);
			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			
			setupPage(session,action,list);
			String s = "";
			for (int i = 0; i < list.size(); i++) {
				Hashtable f = (Hashtable) list.get(i);				
				
				if (i==0) {
					s = ""+f.get("idPermohonan");
				} else {
					s += ","+f.get("idPermohonan");
				}
				
			}
			myLog.info(s);
			Vector peringatanBayaran = getIHTPP().getPeringatanJenisBayaranPer(s, "3",sdfTahun.format(new Date()),"21");
    		
    		Vector peringatanCukai = null;
    			int tahunHadapan = Integer.parseInt(sdfTahun.format(new Date()))+ 1;
	    	if (sdfBulan.format(new Date()).equals("12")){
	    		peringatanCukai = getIHTPP().getSenaraiPeringatanBayaranPer(s, "3",String.valueOf(tahunHadapan));
	    	
	    	}else{
	    		peringatanCukai = getIHTPP().getSenaraiPeringatanBayaranPer(s, "3",sdfTahun.format(new Date()));
	    		
	    	}

			this.context.put("cukai", peringatanCukai.size());
			this.context.put("bayarPajakan", peringatanBayaran);

        }
        
        //SET DEFAULT PARAM
		this.context.put("actionPajakan", actionPajakan);
		
		//SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("subUrusan", subUrusan);
        
		//log.info(vm);
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
	
	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;
		
	}

	private IHTPeringatan getIHTPP(){
		if(iHTPP== null)
			iHTPP = new HTPeringatanBean();
		return iHTPP;
	}
	
}
