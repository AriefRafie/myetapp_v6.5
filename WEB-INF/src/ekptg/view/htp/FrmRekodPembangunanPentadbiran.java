package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.htp.FrmRekodPembangunanPentadbiranData;

public class FrmRekodPembangunanPentadbiran extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FrmRekodPembangunanPentadbiran.class);
	
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");
		this.context.put("submit",submit);
		log.info("submit :" +submit);
		
		String action = getParam("action");
		this.context.put("action",action);
		log.info("action :" +action);
		
		HttpSession session = this.request.getSession();
		Vector listPerihal =null;
		
		String idHakmilikPerihal = getParam("idHakmilikPerihal");
		this.context.put("idHakmilikPerihal", idHakmilikPerihal);
		vm = "app/htp/frmRekodPembangunanPentadbiran.jsp";
		
		
		if("PendaftaranPembangunan".equals(submit))
			
			vm = "app/htp/frmRekodPembangunanPentadbiran.jsp";
			//listPerihal = view_modeSenaraiPerihal(session);
			this.context.put("SenaraiPerihal", listPerihal);
			setupPage(session,action,listPerihal);
			this.context.put("jenis_button","3");
		
			this.context.put("txtBangunan","");
			this.context.put("txtJalan","");
			this.context.put("txtPadang","");
			this.context.put("txtParking","");
			this.context.put("txtLain","");
			this.context.put("txtLuasAsal","");
			this.context.put("txtJumlahGuna","");

		// ADD DETAIL PENTADBIRAN TANAH
		if("tambahDetailKeluasan".equals(submit)){
			
			Hashtable hAddDetailKeluasan = new Hashtable();
			hAddDetailKeluasan.put("txtBangunan", getParam("txtBangunan"));
			hAddDetailKeluasan.put("txtJalan", getParam("txtJalan"));
			hAddDetailKeluasan.put("txtPadang", getParam("txtPadang"));
			hAddDetailKeluasan.put("txtParking", getParam("txtParking"));			
			hAddDetailKeluasan.put("txtLain", getParam("txtLain"));
			hAddDetailKeluasan.put("txtLuasAsal", getParam("txtLuasAsal"));
			hAddDetailKeluasan.put("txtBakiBelum", getParam("txtBakiBelum"));
			
			
			String idHakmilikPerihalBaru = FrmRekodPembangunanPentadbiranData.addDetailKeluasan(hAddDetailKeluasan);
			//listPerihal = view_modeSenaraiPerihal(session);
			this.context.put("SenaraiPerihal", listPerihal);
			
			vm = "app/htp/frmRekodPembangunanPentadbiran.jsp";
		}
		// VIEW DETAIL PENTADBIRAN TAHAH BY ID
		if("viewDetailKeluasan".equals(submit)){
			
			this.context.put("readOnly", "readOnly");
			this.context.put("disabled", "disabled");
			this.context.put("mode", "view");
		
			view_modePerihalByIdHakmilikPerihal(session,idHakmilikPerihal);
			vm = "app/htp/frmRekodPembangunanPentadbiran.jsp";
			this.context.put("idHakmilikPerihalLastet", idHakmilikPerihal);
		}
		//KEMASKINI DETAIL PENTADBIRAN TANAH BY ID
		if("kemaskiniDetailKeluasan".equals(submit)){
			this.context.put("readOnly", "");
			this.context.put("disabled", "");
			this.context.put("mode", "kemaskini");
		
			view_modePerihalByIdHakmilikPerihal(session,idHakmilikPerihal);
			vm = "app/htp/frmRekodPembangunanPentadbiran.jsp";
		}
		// PENDAFTARAN IMEJAN
		if("PendaftaranImej".equals(submit)){
			this.context.put("jenis_button","4");
			vm = "app/htp/frmRekodPembangunanImej.jsp";
		}
		// PENDAFTARAN IMEJAN
		if("PendaftaranHakmilik".equals(submit)){
			this.context.put("jenis_button","2");
			vm = "app/htp/frmRekodPendaftaranHakmilik.jsp";
		}
			return vm;
	}
	//VIEW HAKMILIKPERIHAL BY ID
	private void view_modePerihalByIdHakmilikPerihal(HttpSession session,String idHakmilikPerihal) throws Exception {
		
		Vector list =null;
		list = FrmRekodPembangunanPentadbiranData.getMaklumatPerihalById(idHakmilikPerihal);
		Hashtable hPergerakanById = (Hashtable) list.get(0);
		
		this.context.put("idPerihalHakmilik",(String)hPergerakanById.get("idHakmilikPerihal"));
		this.context.put("txtBangunan",(String)hPergerakanById.get("bangunan"));
		this.context.put("txtJalan",(String)hPergerakanById.get("jalan"));
		this.context.put("txtPadang", (String)hPergerakanById.get("padang"));
		this.context.put("txtParking",(String)hPergerakanById.get("parking"));
		this.context.put("txtLain",(String)hPergerakanById.get("lain"));
		this.context.put("txtJumlahGuna", (String)hPergerakanById.get("belum"));
		this.context.put("txtLuasAsal",(String)hPergerakanById.get("luasAsal"));
	
	}
	// VIEW SENARAI FAIL HAKMILIK DAN RIZAB
	//private Vector view_modeSenaraiPerihal(HttpSession session) throws Exception {
		//String idHakmilik = getParam("idHakmilik");
		//return FrmRekodPembangunanPentadbiranData.getMaklumatPerihalByIdHakmilik("53");
	//}
	// SETUP PAGING FOR LIST SENARAI PERGERAKAN HAKMILIK	
	public void setupPagePergerakanHakmilikDetail(HttpSession session,String action,Vector list) {
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
		this.context.put("SenaraiPerihal",paging.getPage(page));
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
