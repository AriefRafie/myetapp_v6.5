package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.integrasi.FrmModelMaklumatKebangkrapan;

@SuppressWarnings({ "serial", "unused" })
public class FrmViewMaklumatKebangkrapan extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	String Carian_NoFail = "";
	String Carian_NoPermohonan = "";
	String Carian_NamaPemohon = "";
	String Carian_NoKPPemohon = "";
	
	String ID_PERMOHONAN = "";
	String ID_PB = "";
	
	String MP_NAMAPEMOHON = "";
	String MP_TARIKHPERMOHONAN = "";
	String MP_NOKPPEMOHON = "";
	String MP_ALAMATPEMOHON = "";
	String MP_STATUSKEBANGKRAPAN = "";
	String MP_JENISNOPB = "";
	String MP_SEMAKINDIVIDU = "";
	String MP_CATATAN = "";
	String MP_TARIKHHUKUM = "";
	String MP_TARIKHGULUNG = "";
	String MP_TARIKHSEBUTAN = "";
	String MP_CAWANGAN = "";
	String MP_NOESTET = "";
		
	Boolean isJIMUser = false, haveINTData = false;
    String userName = "", userRole = "";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doTemplate2() throws Exception{
        HttpSession session = this.request.getSession();
        
        FrmModelMaklumatKebangkrapan modelMaklumatKebangkrapan = new FrmModelMaklumatKebangkrapan();
        
		String vm = "";
        String action = getParam("action2");                
        Boolean Page_Carian = false;
    	Boolean saveMaklumatKebangkrapan = false, sendMaklumatKebangkrapan = false, deleteMaklumatKebangkrapan = false;
                
        userName = (String) session.getAttribute("_portal_username");
        userRole = (String) session.getAttribute("_portal_role");
        isJIMUser = isJIMUser(userRole);
        
        ID_PERMOHONAN = getParam("ID_PERMOHONAN");
        ID_PB = getParam("ID_PB");        
        
    	MP_STATUSKEBANGKRAPAN = getParam("MP_STATUSKEBANGKRAPAN");
    	MP_CATATAN = getParam("MP_CATATAN");
    	MP_TARIKHHUKUM = getParam("MP_TARIKHHUKUM");
    	MP_TARIKHGULUNG = getParam("MP_TARIKHGULUNG");
    	MP_TARIKHSEBUTAN = getParam("MP_TARIKHSEBUTAN");
    	MP_CAWANGAN = getParam("MP_CAWANGAN");
    	MP_NOESTET = getParam("MP_NOESTET");

    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
    	
        if ("searchMaklumatKebangkrapan".equalsIgnoreCase(action)) {
        	//Page_Carian = true;
        } else if ("viewMaklumatKebangkrapan".equalsIgnoreCase(action)) {
    		if (!"".equalsIgnoreCase(ID_PB)) {
    			vData = modelMaklumatKebangkrapan.viewMaklumatKebangkrapanMaklumat(ID_PB);
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePageMP(h);
    			}
    			vList = modelMaklumatKebangkrapan.viewMaklumatHTA(ID_PB);
    			context.put("ListHTA", vList);
    		} else {
    			//Page_Carian = true;
    		}
        } else if ("saveMaklumatKebangkrapan".equalsIgnoreCase(action)) {
        	if (!"".equalsIgnoreCase(ID_PB)) {
    			h = new Hashtable();
				h.put("MP_STATUSKEBANGKRAPAN", MP_STATUSKEBANGKRAPAN);
				h.put("MP_TARIKHHUKUM", MP_TARIKHHUKUM);
				h.put("MP_TARIKHGULUNG", MP_TARIKHGULUNG);
				h.put("MP_CATATAN", MP_CATATAN);
				h.put("MP_TARIKHSEBUTAN", MP_TARIKHSEBUTAN);
				h.put("MP_CAWANGAN", MP_CAWANGAN);
				h.put("MP_NOESTET", MP_NOESTET);
				saveMaklumatKebangkrapan = modelMaklumatKebangkrapan.saveMaklumatKebangkrapan(ID_PB, ID_PERMOHONAN, (String) session.getAttribute("_ekptg_user_id"), isJIMUser, h);
				
    			vData = modelMaklumatKebangkrapan.viewMaklumatKebangkrapanMaklumat(ID_PB);
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePageMP(h);
    			}
    			vList = modelMaklumatKebangkrapan.viewMaklumatHTA(ID_PB);
    			context.put("ListHTA", vList);
    		} else {
    			//Page_Carian = true;
    		}
        } else if ("sendMaklumatKebangkrapan".equalsIgnoreCase(action)) {
        	if (!"".equalsIgnoreCase(ID_PB)) {
    			h = new Hashtable();
				h.put("MP_STATUSKEBANGKRAPAN", MP_STATUSKEBANGKRAPAN);
				h.put("MP_TARIKHHUKUM", MP_TARIKHHUKUM);
				h.put("MP_TARIKHGULUNG", MP_TARIKHGULUNG);
				h.put("MP_CATATAN", MP_CATATAN);
				h.put("MP_TARIKHSEBUTAN", MP_TARIKHSEBUTAN);
				h.put("MP_CAWANGAN", MP_CAWANGAN);
				h.put("MP_NOESTET", MP_NOESTET);
				saveMaklumatKebangkrapan = modelMaklumatKebangkrapan.saveMaklumatKebangkrapan(ID_PB, ID_PERMOHONAN, (String) session.getAttribute("_ekptg_user_id"), isJIMUser, h);
        		sendMaklumatKebangkrapan = modelMaklumatKebangkrapan.sendMaklumatKebangkrapan(ID_PB, ID_PERMOHONAN, isJIMUser);

        		vData = modelMaklumatKebangkrapan.viewMaklumatKebangkrapanMaklumat(ID_PB);
    			if (!vData.isEmpty()) {
    				h = (Hashtable) vData.get(0);
    				populatePageMP(h);
    			}
    			vList = modelMaklumatKebangkrapan.viewMaklumatHTA(ID_PB);
    			context.put("ListHTA", vList);
    		} else {
    			//Page_Carian = true;
    		}
        } else if ("deleteMaklumatKebangkrapan".equalsIgnoreCase(action)) {
        	//Page_Carian = true;

        	if (!"".equalsIgnoreCase(ID_PB)) {
        		deleteMaklumatKebangkrapan = modelMaklumatKebangkrapan.deleteMaklumatKebangkrapan(ID_PB, ID_PERMOHONAN);
        		if (!deleteMaklumatKebangkrapan) {
    				Page_Carian = false;
        		}
        	}
        } else {
    		//Page_Carian = true;
        }
        
        if (Page_Carian) {
        	vm = "app/integrasi/frmMaklumatKebangkrapanCarian.jsp";

        	Carian_NoFail = getParam("Carian_NoFail");
            Carian_NoPermohonan = getParam("Carian_NoPermohonan");
            Carian_NamaPemohon = getParam("Carian_NamaPemohon");
            Carian_NoKPPemohon = getParam("Carian_NoKPPemohon");

            context.put("Carian_NoFail", Carian_NoFail);
        	context.put("Carian_NoPermohonan", Carian_NoPermohonan);
        	context.put("Carian_NamaPemohon", Carian_NamaPemohon);
        	context.put("Carian_NoKPPemohon", Carian_NoKPPemohon);
        	
        	context.put("deleteMaklumatKebangkrapan", deleteMaklumatKebangkrapan);
        	context.remove("ListPermohonan");
        	
        	if ("searchMaklumatKebangkrapan".equalsIgnoreCase(action)) {
        		vList = modelMaklumatKebangkrapan.searchMaklumatKebangkrapan(Carian_NoFail, Carian_NoPermohonan, Carian_NamaPemohon, Carian_NoKPPemohon);
            	setupPage(session, action, vList);
        	}
        } else {
        	vm = "app/integrasi/frmMaklumatKebangkrapan.jsp";
        	
        	if ("B".equalsIgnoreCase(MP_JENISNOPB) || "P".equalsIgnoreCase(MP_JENISNOPB) || "L".equalsIgnoreCase(MP_JENISNOPB) 
        			|| "T".equalsIgnoreCase(MP_JENISNOPB) || "I".equalsIgnoreCase(MP_JENISNOPB) || "A".equalsIgnoreCase(MP_JENISNOPB) 
        			|| "G".equalsIgnoreCase(MP_JENISNOPB)) {
        		MP_SEMAKINDIVIDU = "true";
        	} else {
        		MP_SEMAKINDIVIDU = "false";
        	}
        	
	    	context.put("MP_NAMAPEMOHON", MP_NAMAPEMOHON);
	        context.put("MP_TARIKHPERMOHONAN", MP_TARIKHPERMOHONAN);
	    	context.put("MP_NOKPPEMOHON", MP_NOKPPEMOHON);
	    	context.put("MP_ALAMATPEMOHON", MP_ALAMATPEMOHON);
	    	context.put("MP_ALAMATPEMOHON", MP_ALAMATPEMOHON);
	    	context.put("MP_STATUSKEBANGKRAPAN", MP_STATUSKEBANGKRAPAN);
	    	context.put("MP_CATATAN", MP_CATATAN);
	    	context.put("MP_TARIKHSEBUTAN", MP_TARIKHSEBUTAN);
	    	context.put("MP_TARIKHHUKUM", MP_TARIKHHUKUM);
	    	context.put("MP_TARIKHGULUNG", MP_TARIKHGULUNG);
	    	context.put("MP_SEMAKINDIVIDU", MP_SEMAKINDIVIDU);
	    	context.put("MP_CAWANGAN", MP_CAWANGAN);
	    	context.put("MP_NOESTET", MP_NOESTET);
	    	context.put("ID_PERMOHONAN", ID_PERMOHONAN);
	        context.put("ID_PB", ID_PB);
	        context.put("haveINTData", haveINTData);
	        context.put("saveMaklumatKebangkrapan", saveMaklumatKebangkrapan);
	        context.put("sendMaklumatKebangkrapan", sendMaklumatKebangkrapan);
        }
        if (isJIMUser) {
        	context.put("READONLY_JIM", "");	
        	context.put("DISABLE_JIM", "");
        	context.put("READONLY_JKPTG", " readonly=\"readonly\" ");	
        	context.put("DISABLE_JKPTG", " readonly=\"readonly\" ");
        } else {
        	context.put("READONLY_JIM", " readonly=\"readonly\" ");
        	context.put("DISABLE_JIM", " disabled=\"disabled\" ");
        	context.put("READONLY_JKPTG", "");
        	context.put("DISABLE_JKPTG", "");
        }
        context.put("isJIMUser", isJIMUser);
        context.put("action2", action);
		return vm;
	}
	
	@SuppressWarnings("unchecked")
	private void populatePageMP(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			MP_NAMAPEMOHON = (String) h.get("MP_NAMAPEMOHON");
			MP_TARIKHPERMOHONAN = (String) h.get("MP_TARIKHPERMOHONAN");
			MP_NOKPPEMOHON = (String) h.get("MP_NOKPPEMOHON");
			MP_ALAMATPEMOHON = (String) h.get("MP_ALAMATPEMOHON");
			MP_STATUSKEBANGKRAPAN = (String) h.get("MP_STATUSKEBANGKRAPAN");
			MP_JENISNOPB = (String) h.get("MP_JENISNOPB");
			MP_TARIKHHUKUM = (String) h.get("MP_TARIKHHUKUM");
			MP_TARIKHGULUNG = (String) h.get("MP_TARIKHGULUNG");
			MP_TARIKHSEBUTAN = (String) h.get("MP_TARIKHSEBUTAN");
			MP_CAWANGAN = (String) h.get("MP_CAWANGAN");
			MP_NOESTET = (String) h.get("MP_NOESTET");
			MP_CATATAN = (String) h.get("MP_CATATAN");
			haveINTData = (Boolean) h.get("haveINTData");
		}
	}
	
	private Boolean isJIMUser(String userRole) throws Exception {
		Boolean returnValue = false;
		returnValue = "jim".equalsIgnoreCase(userRole);
		return returnValue;
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