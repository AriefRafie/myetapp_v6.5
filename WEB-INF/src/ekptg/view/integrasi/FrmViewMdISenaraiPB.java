package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.integrasi.FrmModelMaklumatKebangkrapan;

public class FrmViewMdISenaraiPB extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	String CARIAN_NOFAIL = "";
	
	String ID_PERMOHONAN = "", ID_HAKMILIK = "";
	
    String userName = "", userRole = "", userID = "";
	
	@SuppressWarnings("rawtypes")
	public String doTemplate2() throws Exception{
        HttpSession session = this.request.getSession();
        
        FrmModelMaklumatKebangkrapan model = new FrmModelMaklumatKebangkrapan();
        
		String vm = "app/integrasi/frmMdiSenaraiFail.jsp";
        String action = getParam("action2");
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        
        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        ID_HAKMILIK = getParam("ID_HAKMILIK");
        
        if ("searchFail".equalsIgnoreCase(action)) {
        	CARIAN_NOFAIL = getParam("CARIAN_NOFAIL");
        	Vector<?> vList = new Vector<Object>();
        	vList = model.viewSenaraiPBFail(CARIAN_NOFAIL);
        	context.put("ListFail", vList);
        	setupPage(session, action, vList);
        } else if ("viewFail".equalsIgnoreCase(action)) {
    		if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
    			Vector<?> vList = new Vector<Object>();
    			vList = model.viewSenaraiPBMaklumatFail(ID_PERMOHONAN);
    			if (vList.size() > 0) {
    				Hashtable h = new Hashtable();
    				h = (Hashtable) vList.get(0);
    				context.put("NO_FAIL", h.get("NO_FAIL"));
    				context.put("NO_RUJUKAN_PTG", h.get("NO_RUJUKAN_PTG"));
    				context.put("NO_RUJUKAN_PTD", h.get("NO_RUJUKAN_PTD"));
    				context.put("NAMA_KEMENTERIAN", h.get("NAMA_KEMENTERIAN"));
    				context.put("NAMA_AGENSI", h.get("NAMA_AGENSI"));
    				context.put("NEGERI", h.get("NEGERI"));
    				context.put("DAERAH", h.get("DAERAH"));
    				context.put("TUJUAN", h.get("TUJUAN"));
        			vList = model.viewSenaraiPB(ID_PERMOHONAN, ID_HAKMILIK);
        			context.put("ListPB", vList);
        			vm = "app/integrasi/frmMdiSenaraiPB.jsp";
    			}
    		}
        } else if ("sendStatus".equalsIgnoreCase(action.trim())) {
        	if (!"".equalsIgnoreCase(ID_PERMOHONAN) && !"".equalsIgnoreCase(ID_HAKMILIK)) {
            	String[] ID_PB = this.request.getParameterValues("ID_PB");
            	model.addPBDataList(ID_PERMOHONAN, ID_PB, userID);
            	
    			Vector<?> vList = new Vector<Object>();
    			vList = model.viewSenaraiPBMaklumatFail(ID_PERMOHONAN);
    			if (vList.size() > 0) {
    				Hashtable h = new Hashtable();
    				h = (Hashtable) vList.get(0);
    				context.put("NO_FAIL", h.get("NO_FAIL"));
    				context.put("NO_RUJUKAN_PTG", h.get("NO_RUJUKAN_PTG"));
    				context.put("NO_RUJUKAN_PTD", h.get("NO_RUJUKAN_PTD"));
    				context.put("NAMA_KEMENTERIAN", h.get("NAMA_KEMENTERIAN"));
    				context.put("NAMA_AGENSI", h.get("NAMA_AGENSI"));
    				context.put("NEGERI", h.get("NEGERI"));
    				context.put("DAERAH", h.get("DAERAH"));
    				context.put("TUJUAN", h.get("TUJUAN"));
        			vList = model.viewSenaraiPB(ID_PERMOHONAN, ID_HAKMILIK);
        			context.put("ListPB", vList);
        			vm = "app/integrasi/frmMdiSenaraiPB.jsp";
    			}
        	}
        } else {
        	vm = "app/integrasi/frmMdiSenaraiFail.jsp";
        }
        
        context.put("ID_PERMOHONAN", ID_PERMOHONAN);
        context.put("ID_HAKMILIK", ID_HAKMILIK);
        context.put("action2", action);
        
		return vm;
	}
	
	@SuppressWarnings("rawtypes")
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
			this.context.put("ListPermohonan",paging.getPage(page));
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