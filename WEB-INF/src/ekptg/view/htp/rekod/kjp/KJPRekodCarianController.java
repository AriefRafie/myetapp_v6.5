package ekptg.view.htp.rekod.kjp;

/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.rekod.kjp.FrmKjpRekodDB;

public class KJPRekodCarianController extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.rekod.kjp.KJPRekodCarianController.class);
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		Vector<Hashtable<String,String>> listResult = new Vector<Hashtable<String,String>>();		
		String action = getParam("action");
		
    	//screen jsp
		String folder = "app/htp/kjp/rekod/";
    	String path = folder+"carian.jsp";
    	String displayResult = "";   	
    	String submit = getParam("command");
    	//myLog.info("doTemplate2:submit= " + submit);
    	
    	if("clear".equals(submit)){
    		getDropdown(null,null,null,null,null);
    		context.put("txtNoHakmilik", "");
    		context.put("txtNoLot", "");
    		
    	}else if("filterDropdown".equals(submit)){
    		getAndSetData();
    	}else{
    		myLog.info("doTemplate2 else:action= " + action);
    		getAndSetData();
    		//searchQuery(getParam("socNegeri"),getParam("socDaerah"),getParam("socMukim"),getParam("socKementerian"),getParam("socAgensi"),getParam("txtNoHakmilik"),getParam("txtNoLot"));
    		searchQuery(getParam("socNegeri"),getParam("socDaerah"),getParam("socMukim")
    				,getParam("socKementerian"),getParam("socAgensi")
    				,getParam("txtNoHakmilik"),getParam("txtnowarta"),getParam("txtNoLot"));
    		listResult = FrmKjpRekodDB.getListCarian();
    		displayResult = "true";
    		
		}//close else
    		
    	context.put("utils",new Utils());
    	context.put("listResult", listResult);
    	context.put("displayResult", displayResult);
    	setupPage(session,action,listResult);
    	return path;
    		
	}//close public template
	
	//public void searchQuery(String idNegeri,String idDaerah,String idMukim,String idKementerian,String idAgensi,String noHakmilik,String noLot) throws Exception{
	public void searchQuery(String idNegeri,String idDaerah,String idMukim,String idKementerian,String idAgensi,String noHakmilik,String noWarta,String noLot) throws Exception{
		FrmKjpRekodDB.setListCarian(idNegeri,idDaerah,idMukim,idKementerian,idAgensi,noHakmilik,noWarta,noLot);
	}
	
	public void getAndSetData() throws Exception{
		context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		context.put("txtNoLot", getParam("txtNoLot"));		
		context.put("txtNoWarta", getParam("txtnowarta"));		
		//dropdown
		getDropdown(getParam("socNegeri"),getParam("socDaerah"),getParam("socMukim"),getParam("socKementerian"),getParam("socAgensi"));
	
	}
	
	public void getDropdown(String idNegeri,String idDaerah,String idMukim,String idKementerian,String idAgensi) throws Exception {		
		//myLog.info("getDropdown");
		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),null,"style=width:300px  onChange=\"doFilterDropdown();\" "));
		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doFilterDropdown();\" "));
		
		if(idNegeri!=null && idNegeri!=""){
			context.put("selectDaerah",HTML.SelectDaerahByNegeri(idNegeri,"socDaerah",Utils.parseLong(idDaerah),null,"style=width:300px  onChange=\"doFilterDropdown();\" "));
		}else{
			context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(idDaerah),null,"style=width:300px  onChange=\"doFilterMukim();\" "));
		}
		
		if(idDaerah!=null && idDaerah!=""){
			context.put("selectMukim",HTML.SelectMukimByDaerah(idDaerah,"socMukim",Utils.parseLong(idMukim),null,"style=width:300px"));
		}else{
			context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(idMukim),"style=width:300px"));
		}
		
		if(idKementerian!=null && idKementerian!=""){
			context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi",idKementerian,Utils.parseLong(idAgensi),"style=width:500px"));
		}else{
			context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Utils.parseLong(idAgensi),"style=width:500px"));	
		}
		
	}
	
	@SuppressWarnings("unchecked")
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
			this.context.put("listResult",paging.getPage(page));
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
	
	
}//close class
