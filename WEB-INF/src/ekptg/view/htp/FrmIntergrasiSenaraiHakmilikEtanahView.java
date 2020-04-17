/**
 * 
 */
package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmIntergrasiSenaraiHakmilikEtanahData;

/**
 * @author mohd faizal
 *
 */
public class FrmIntergrasiSenaraiHakmilikEtanahView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmIntergrasiSenaraiHakmilikEtanahData logic = new FrmIntergrasiSenaraiHakmilikEtanahData();

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionHakmilik = getParam("actionHakmilik");
	    String submit = getParam("command");
	    String idHakmilik = getParam("idHakmilik");
	    
	    //VECTOR
        Vector list = null;        
        Vector beanMaklumatHakmilik = null;
        
        if ("papar".equals(actionHakmilik)){
	    	
	    	//GO TO DETAIL MAKLUMAT HAKMILIK        	
        	vm = "app/htp/frmHTPIntergrasiMaklumatHakmilikEtanah.jsp";
        	
        	beanMaklumatHakmilik = new Vector();
			logic.setMaklumatHakmilik(idHakmilik);
			beanMaklumatHakmilik = logic.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatHakmilik", beanMaklumatHakmilik);
	    	
	    } else {
	    	
	    	//GO TO LIST HAKMILIK        	
	    	vm = "app/htp/frmHTPIntergrasiSenaraiHakmilikEtanah.jsp";

			this.context.put("txtIDHakmilik", getParam("txtIDHakmilik"));

			list = new Vector();
			logic.carianHakmilik(getParam("txtIDHakmilik"));
			list = logic.getSenaraiHakmilik();
			this.context.put("SenaraiHakmilik", list);
        	
        	setupPage(session,action,list);
	    }

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
				this.context.put("SenaraiHakmilik",paging.getPage(page));
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
