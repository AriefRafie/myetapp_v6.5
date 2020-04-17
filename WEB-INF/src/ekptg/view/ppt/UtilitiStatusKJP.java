package ekptg.view.ppt;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.ppt.MyInfoPPTData;

public class UtilitiStatusKJP extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	MyInfoPPTData logic = null;
	static Logger myLogger = Logger.getLogger(UtilitiKemaskiniFail.class);
	

	@Override
	public String doTemplate2() throws Exception {
		logic = new MyInfoPPTData();
		HttpSession session = this.request.getSession();
		
		String vm = ""; 
		String submit =  getParam("command");
		this.context.put("command",submit);
		String action = getParam("action");
        myLogger.info("ACTION >>> "+action);
		this.context.put("action",action);
		
		Vector list = new Vector();
		list.clear();
		
		vm = "app/ppt/UtilitiStatusKJP.jsp";
		
		String id_kementerian = getParam("id_kementerian");
		String id_agensi = getParam("id_agensi");
		String id_negeri = getParam("id_negeri");
		String id_daerah = getParam("id_daerah");
		
		String socKementerian = getParam("socKementerian");
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String socAgensi = getParam("socAgensi");
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String socNegeri = getParam("socNegeri");
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String socDaerah = getParam("socDaerah");
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0) {
			idDaerah = "99999";
		}
		String socMukim = getParam("socMukim");
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0) {
			idMukim = "99999";
		}
		
		String findNamaProjek = getParam("findNamaProjek");
		String findNoRujukan = getParam("findNoRujukan");
		
		this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
		this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian,  Long.parseLong(idAgensi), "", ""));
		
		/*this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri("socDaerah", idNegeri,  Long.parseLong(idDaerah), "", ""));*/
		
		this.context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri", Long.parseLong(idNegeri), "", "onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah",HTML.SelectDaerahByIdNegeri(idNegeri, "socDaerah",  Long.parseLong(idDaerah), "","onChange=\"doChangeDaerah();\""));
		this.context.put("selectMukim",HTML.SelectMukimByDaerah(idDaerah,"socMukim" , Long.parseLong(idMukim), ""));
		
		list = logic.utilitiStatusKJP(id_kementerian, id_agensi, id_negeri, id_daerah, socKementerian, socAgensi, socNegeri, socDaerah, findNamaProjek, findNoRujukan);
		this.context.put("SenaraiFail", list);
		
		setupPage(session, action, list);
		
		return vm;
	}
	
	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session, String action, Vector list) {
		try {
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10 : getParamAsInteger("itemsPerPage");
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
