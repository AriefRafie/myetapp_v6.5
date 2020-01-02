package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanUtilitiesBatalPermohonanData;

/**
 * @author Firzan.Fir
 *
 */
public class XFrmPajakanUtilitiesBatalPermohonanView extends AjaxBasedModule {
	
	 private static final long serialVersionUID = 1L;
	 private static Logger log = Logger.getLogger(XFrmPajakanUtilitiesBatalPermohonanView.class);
	 FrmPajakanUtilitiesBatalPermohonanData logic = new FrmPajakanUtilitiesBatalPermohonanData();
	 FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
		
		this.hitButton = getParam("hitButton");
		this.actionPajakan = getParam("actionPajakan");
		this.idFail = getParam("idFail");
		this.mode = getParam("mode");
		this.submit = getParam("submit");
		
		log.info("hitButton : " + hitButton);
		log.info("actionPajakan : " + actionPajakan);
		log.info("idFail : " + idFail);
		log.info("mode : " + mode);
		log.info("submit : " + submit);
		
		 //VECTOR
        Vector list = null;
        Vector beanHeader = null;
		
		beanHeader = new Vector();
	    logicHeader.setMaklumatPermohonan(idFail);
	    beanHeader = logicHeader.getBeanMaklumatPermohonan();
	    this.context.put("BeanHeader", beanHeader);
		
		String vm = null;
		vm = "app/htp/frmPajakanUtilitiesBatalPermohonan.jsp";
		if(actionPajakan.equalsIgnoreCase("papar")){
			vm = "app/htp/frmPajakanPenamatanPajakan.jsp";
			String[] cbBatal = this.request.getParameterValues("cbBatal");
			BatalPermohonan(mode,cbBatal, session);
			
			
		}else{
			//GO TO LIST FAIL PAJAKAN        	
        	vm = "app/htp/frmPajakanUtilitiesBatalPermohonan.jsp";

        	logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"));
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);
			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			
			setupPage(session,action,list);
		}
		
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

	public void BatalPermohonan(String mode, String[] cbBatal, HttpSession session ) throws Exception{
		try{
			if(mode.equalsIgnoreCase("viewFail")){
				
				
				
			}else{
				//logic.BatalPermohonan(cbBatal,session);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
