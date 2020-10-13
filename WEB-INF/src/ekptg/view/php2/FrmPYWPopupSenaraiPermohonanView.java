/**
 * 
 */
package ekptg.view.php2;


import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
//import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmPYWPopupSenaraiPermohonanData;
import ekptg.model.php2.utiliti.PHPUtilHTML;

/**
 * 
 *
 */
public class FrmPYWPopupSenaraiPermohonanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPYWPopupSenaraiPermohonanData logic = new FrmPYWPopupSenaraiPermohonanData();
	
	String idNegeriUser = null;

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionPopup = getParam("actionPopup");
	    //String submit = getParam("command");
	    String hitButton = getParam("hitButton");
	    //String idFail = getParam("idFail");
	    String step = getParam("step");
	    String idMesyuarat = getParam("idMesyuarat");
	    
	    idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");		
	    
	    //VECTOR
        @SuppressWarnings("rawtypes")
		Vector senaraiFail = null;
	    		
		//SEND TO MODEL
		if ("doSimpanPilihan".equals(hitButton)) {
			
			String idPermohonan="";
			String idJenisPermohonan="";
			String[] cbPilihan = request.getParameterValues("checkPermohonan");
			String ddJenisPermohonan = getParam("socJenisPermohonan");
			for(int i = 0; i < cbPilihan.length; i++){
				 idPermohonan=cbPilihan[i].toString();
				 idJenisPermohonan = logic.getJenisPermohonan(idPermohonan);
				 if(ddJenisPermohonan.equals("3")){
					 logic.simpanPilihanLain(idMesyuarat, idPermohonan, session);
				 }else{
					 if (idJenisPermohonan.equals("1")){
						 logic.simpanPilihanBaru(idMesyuarat, idPermohonan, session);
					 }else if (idJenisPermohonan.equals("2")){
						logic.simpanPilihanLanjutan(idMesyuarat, idPermohonan, session);
					 } 
				 }
			}
			this.context.put("close_window", "yes");
		}	
		
		if ("tutup".equals(actionPopup)){
			
	    	
	    } else {
	    	String carianNoFail = getParam("txtCarianNoFail");
	    	// DROP DOWN CARIAN
	    	String idJenisPermohonan = getParam("socJenisPermohonan");
	    	if (idJenisPermohonan == null || idJenisPermohonan.trim().length() == 0) {
	    		idJenisPermohonan = "99999";
	    	}
	    	String carianNamaPemohon = getParam("txtCarianNamaPemohon");

	    	logic.carianFail(carianNoFail,idJenisPermohonan,carianNamaPemohon);
	    	
	    	//GO TO LIST TANAH        	
        	vm = "app/php2/frmPYWPopupSenaraiPermohonan.jsp";  
        	
        	senaraiFail = new Vector();
        	//logic.setSenaraiFailMesyuarat(idFail);
        	senaraiFail = logic.getSenaraiFailMesyuarat();
			this.context.put("SenaraiFail", senaraiFail);   
			
			this.context.put("selectJenisPermohonan", PHPUtilHTML.SelectJenisPermohonan("socJenisPermohonan", Long.parseLong(idJenisPermohonan), "", ""));
        	setupPage(session,action,senaraiFail);
	    }
		
	    this.context.put("actionPopup", actionPopup);
	    this.context.put("step", step);
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
				this.context.put("SenaraiTanah",paging.getPage(page));
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
