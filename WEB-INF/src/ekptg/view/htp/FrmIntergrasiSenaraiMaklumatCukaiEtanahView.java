/**
 * 
 */
package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmIntergrasiSenaraiMaklumatCukaiEtanahData;

/**
 * @author mohd faizal
 *
 */
public class FrmIntergrasiSenaraiMaklumatCukaiEtanahView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmIntergrasiSenaraiMaklumatCukaiEtanahData logic = new FrmIntergrasiSenaraiMaklumatCukaiEtanahData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionHakmilik = getParam("actionCukai");
	    String submit = getParam("command");
	    String idHakmilik = getParam("idHakmilik");
	    String tahun = getParam("tahun");
	    
	   //VECTOR
        Vector list = null;        
        Vector beanMaklumatCukai = null;
        
      //GO TO LIST HAKMILIK        	
    	vm = "app/htp/frmHTPIntergrasiSenaraiMaklumatCukaiEtanah.jsp";

		this.context.put("txtIDHakmilik", getParam("txtIDHakmilik"));
		this.context.put("txtTahun", getParam("txtTahun"));

		list = new Vector();
		logic.carianMaklumatCukai(getParam("txtIDHakmilik"), getParam("txtTahun"));
		list = logic.getSenaraiMaklumatCukai();
		this.context.put("SenaraiMaklumatCukai", list);
    	
    	setupPage(session,action,list);
    	
    	this.context.put("idHakmilik", idHakmilik);
	    
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
				this.context.put("SenaraiMaklumatCukai",paging.getPage(page));
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
