/**
 * 
 */
package ekptg.view.php2;


import java.util.Vector;
import javax.servlet.http.HttpSession;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmPYWPopupRingkasanPertimbanganData;
import ekptg.model.php2.utiliti.PHPUtilHTML;

/**
 * 
 *
 */
public class FrmAPBPopupRingkasanPertimbanganView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPYWPopupRingkasanPertimbanganData logic = new FrmPYWPopupRingkasanPertimbanganData();
	
	String idNegeriUser = null;

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionPopup = getParam("actionPopup");
	    String submit = getParam("command");
	    String hitButton = getParam("hitButton");
	    String idPermohonan = getParam("idPermohonan");
	    String catatanRingkasan = getParam("catatanRingkasan");
	    String step = getParam("step");
	    String idMesyuarat = getParam("idMesyuarat");

	    idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");		
	    
	    //VECTOR
        Vector senaraiFail = null;
        Vector maklumatRingkasanPertimbangan = null;
        Vector maklumatRingkasanPemohon = null;
        
		//SEND TO MODEL
		if ("doSimpanRingkasanPertimbangan".equals(hitButton)) {
			logic.simpanCatatanRingkasanPertimbangan(idPermohonan, catatanRingkasan, session);
		}	
		
		if ("tutup".equals(actionPopup)){
	    	
	    } else {
	    	
        	//MAKLUMAT PEMOHON
	    	maklumatRingkasanPemohon = new Vector();
	    	logic.setMaklumatRingkasanPemohon(idPermohonan);
	    	maklumatRingkasanPemohon = logic.getMaklumatRingkasanPemohon();
	    	this.context.put("MaklumatRingkasanPemohon", maklumatRingkasanPemohon);
	    	
	    	//MAKLUMAT ULASAN JABATAN TEKNIKAL
	    	maklumatRingkasanPertimbangan= new Vector();
        	logic.setAPBMaklumatKertasRingkasan(idPermohonan);
        	maklumatRingkasanPertimbangan = logic.getMaklumatRingkasanPertimbangan();
			this.context.put("MaklumatRingkasanPertimbangan", maklumatRingkasanPertimbangan);   
			        	
        	vm = "app/php2/frmAPBPopupRingkasanPertimbangan.jsp";  
        	
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
