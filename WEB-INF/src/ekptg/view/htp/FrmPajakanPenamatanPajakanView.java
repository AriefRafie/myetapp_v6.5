/**
 * 
 */
package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.htp.FrmPajakanSenaraiFailData;

/**
 * @author Firzan.Fir
 *
 */
public class FrmPajakanPenamatanPajakanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FrmPajakanPenamatanPajakanView.class);
	FrmPajakanSenaraiFailData logic = new FrmPajakanSenaraiFailData();
	
	 String hitButton = null;
	 Boolean postDB = false;
	 String action = null;
	 String actionPajakan = null;
	 String submit = null;
	 String mode = null;
	 String idFail = null;
	 
	 Vector list = null;
	

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		//GET DEFAULT PARAM
		this.hitButton = getParam("hitButton");
		this.actionPajakan = getParam("actionPajakan");
		this.mode = getParam("mode");
		this.submit = getParam("submit");
		String vm = ""; 		
		
		 //GET ID PARAM
		this.idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String subUrusan = getParam("subUrusan");
        String idHakmilikUrusan = getParam("idHakmilikUrusan");
		
      //ACTION PAJAKAN
		if ("papar".equals(actionPajakan)){
        	
        	//GO TO PENAMATAN PAJAKAN  
        	vm = "app/htp/frmPajakanPenamatanPajakan.jsp";
        	
//        	this.context.put("mode", "new");
//        	this.context.put("readonly", "");
//        	this.context.put("inputTextClass", "");
        	
        	
        	
        	
        } else {
        	
        	//GO TO LIST FAIL PAJAKAN        	
        	vm = "app/htp/frmPajakanPenamatanPajakanSenaraiFail.jsp";

        	logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"),getParam("txtTajukFail"),getParam("txtNamaPemohon"));
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);
			
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

}
