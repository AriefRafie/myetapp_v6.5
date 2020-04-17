package ekptg.view.pfd;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.planner.EventData;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmModelMesyuaratTempahan;

@SuppressWarnings({ "serial", "unused" })
public class FrmViewMesyuaratTempahan extends AjaxBasedModule{

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	String Tempahan_Lokasi = "";
	String Tempahan_Tarikh = "";
	String Tempahan_Dari = "";
	String Tempahan_Hingga = "";
	String Tempahan_Seksyen = "";
	String Tempahan_NoRujukanDokumen = "";
	String Tempahan_Tajuk = "";
	String Tempahan_Bulan = "";
	String Tempahan_Tahun = "";
    
    String SOC_LOKASI = "SOC_LOKASI";
    String SOC_SEKSYEN = "SOC_SEKSYEN";
    String SOC_BULAN = "SOC_BULAN";
    String SOC_TAHUN = "SOC_TAHUN";
    
    Boolean locIsAvailable = false;
    Boolean isCheckedAvailability = false;
    Boolean isSavedSuccessfully = false;
    
    @SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "";

        HttpSession session = this.request.getSession();
        
        FrmModelMesyuaratTempahan modelTempahan = new FrmModelMesyuaratTempahan();
        
        String action = getParam("action");
        String command = getParam("command");
        
        Tempahan_Lokasi = getParam("Tempahan_Lokasi");
        Tempahan_Tarikh = getParam("Tempahan_Tarikh");
        Tempahan_Dari = getParam("Tempahan_Dari");
        Tempahan_Hingga = getParam("Tempahan_Hingga");
        Tempahan_Seksyen = getParam("Tempahan_Seksyen");
        Tempahan_NoRujukanDokumen = getParam("Tempahan_NoRujukanDokumen");
        Tempahan_Tajuk = getParam("Tempahan_Tajuk");
        Tempahan_Bulan = getParam("Tempahan_Bulan");
        Tempahan_Tahun = getParam("Tempahan_Tahun");
        SOC_LOKASI = getParam("SOC_LOKASI");
        SOC_SEKSYEN = getParam("SOC_SEKSYEN");
        SOC_BULAN = getParam("SOC_BULAN");
        SOC_TAHUN = getParam("SOC_TAHUN");
        
    	Vector vData = new Vector();
    	Vector vList = new Vector();
    	Hashtable h = new Hashtable();
        
    	vm = "app/pfd/frmMesyuaratTempahanBilikMesyuarat.jsp";
        if ("checkAvailability".equalsIgnoreCase(command)) {
        	if (!"".equalsIgnoreCase(SOC_LOKASI) && !"".equalsIgnoreCase(Tempahan_Tarikh) && !"".equalsIgnoreCase(Tempahan_Dari) & !"".equalsIgnoreCase(Tempahan_Hingga)) {
        		// kalau ada data, enable fieldset tempahan
        		int MASA_DARI = -1, MASA_HINGGA = -1;
    			if (FrmModelMesyuaratTempahan.isNumeric(Tempahan_Dari.substring(0, 2))) {
    				MASA_DARI = Integer.parseInt(Tempahan_Dari.substring(0, 2));
    			}
    			if (FrmModelMesyuaratTempahan.isNumeric(Tempahan_Hingga.substring(0, 2))) {
    				MASA_HINGGA = Integer.parseInt(Tempahan_Hingga.substring(0, 2));
    			}
        		locIsAvailable = modelTempahan.locIsAvailable(SOC_LOKASI, Tempahan_Tarikh, MASA_DARI, MASA_HINGGA);
        		isCheckedAvailability = true;
        	}
        } else if ("bookTempahan".equalsIgnoreCase(command)) {
        	if (!"".equalsIgnoreCase(SOC_LOKASI) && !"".equalsIgnoreCase(Tempahan_Tarikh) && !"".equalsIgnoreCase(Tempahan_Dari) & !"".equalsIgnoreCase(Tempahan_Hingga) && !"".equalsIgnoreCase(Tempahan_NoRujukanDokumen) && !"".equalsIgnoreCase(Tempahan_Tajuk) && !"".equalsIgnoreCase(SOC_SEKSYEN)) {
        		isSavedSuccessfully = modelTempahan.saveTempahan(Tempahan_NoRujukanDokumen, Tempahan_Tajuk, SOC_SEKSYEN, SOC_LOKASI, Tempahan_Tarikh, Tempahan_Dari, Tempahan_Hingga);
        		if (isSavedSuccessfully) {
            		int eventYear = 0, eventMonth = 0, eventDay = 0;
            		String user_id = (String)session.getAttribute("_ekptg_user_id");
            		if (FrmModelMesyuaratTempahan.isNumeric(Tempahan_Tarikh.substring(6, 10))) {
            			eventYear = Integer.parseInt(Tempahan_Tarikh.substring(6, 10));
            		}
            		if (FrmModelMesyuaratTempahan.isNumeric(Tempahan_Tarikh.substring(3, 5))) {
            			eventMonth = Integer.parseInt(Tempahan_Tarikh.substring(3, 5));
            		}
            		if (FrmModelMesyuaratTempahan.isNumeric(Tempahan_Tarikh.substring(0, 2))) {
            			eventDay = Integer.parseInt(Tempahan_Tarikh.substring(0, 2));
            		}
	        		Hashtable event = new Hashtable();
	                event.put("userId", user_id);
	                event.put("eventId", Tempahan_Tajuk);
	                event.put("eventText", Tempahan_Tajuk);
	                event.put("viewScope", "public");
	                EventData.addEvent(eventYear, eventMonth, eventDay, event);
        		}
        	}
        	SOC_LOKASI = "";
    		Tempahan_Tarikh = "";
    		Tempahan_Dari = "";
    		Tempahan_Hingga = "";
    		Tempahan_NoRujukanDokumen = "";
        	Tempahan_Tajuk = "";
        	SOC_SEKSYEN = "";
        	context.remove("List_Tempahan");
        } else if ("searchTempahan".equalsIgnoreCase(command)) {
        	if (!"".equalsIgnoreCase(SOC_BULAN) && !"".equalsIgnoreCase(SOC_TAHUN)) {
        		vList = modelTempahan.getTempahanList(SOC_BULAN, SOC_TAHUN);
        		context.put("List_Tempahan", vList);
        	} else {
        		SOC_LOKASI = "";
        		Tempahan_Tarikh = "";
        		Tempahan_Dari = "";
        		Tempahan_Hingga = "";
        	}
        	Tempahan_NoRujukanDokumen = "";
        	Tempahan_Tajuk = "";
        	SOC_SEKSYEN = "";
        }

        int iCount = 0;
        Calendar cal = Calendar.getInstance();
        
        String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
        context.put("idNegeri",user_negeri);
        
        if("16".equalsIgnoreCase(user_negeri)){
        	Tempahan_Lokasi = modelTempahan.getSOCLokasiMesyuarat("SOC_LOKASI", SOC_LOKASI);  
        }
        else{
        	Tempahan_Lokasi = modelTempahan.getSOCLokasiMesyuaratNegeri("SOC_LOKASI", SOC_LOKASI, user_negeri); 
        }
        
        Tempahan_Seksyen = HTML.SelectSeksyen("SOC_SEKSYEN", Utils.parseLong(SOC_SEKSYEN), "");
        Tempahan_Bulan = "<select id=\"SOC_BULAN\" name=\"SOC_BULAN\" style=\"width:auto\">";
        for (iCount = 1; iCount <= 12; iCount++) {
        	if ("".equalsIgnoreCase(SOC_BULAN)) {
	        	if (iCount == cal.get(Calendar.MONTH) + 1) {
	        		Tempahan_Bulan += "<option value=\"" + iCount + "\" selected=\"selected\">" + iCount + "</option>";
	        	} else {
	        		Tempahan_Bulan += "<option value=\"" + iCount + "\">" + iCount + "</option>";
	        	}
        	} else {
	        	if (Integer.toString(iCount).equalsIgnoreCase(SOC_BULAN)) {
	        		Tempahan_Bulan += "<option value=\"" + iCount + "\" selected=\"selected\">" + iCount + "</option>";
	        	} else {
	        		Tempahan_Bulan += "<option value=\"" + iCount + "\">" + iCount + "</option>";
	        	}
        	}
        }
        Tempahan_Bulan += "</select>";
        Tempahan_Tahun = "<select id=\"SOC_TAHUN\" name=\"SOC_TAHUN\" style=\"width:auto\">";
        for (iCount = 2009; iCount <= cal.get(Calendar.YEAR); iCount++) {
        	if ("".equalsIgnoreCase(SOC_TAHUN)) {
	        	if (iCount == cal.get(Calendar.YEAR)) {
	        		Tempahan_Tahun += "<option value=\"" + iCount + "\" selected=\"selected\">" + iCount + "</option>";
	        	} else {
	        		Tempahan_Tahun += "<option value=\"" + iCount + "\">" + iCount + "</option>";
	        	}
        	} else {
        		if (Integer.toString(iCount).equalsIgnoreCase(SOC_TAHUN)) {
        			Tempahan_Tahun += "<option value=\"" + iCount + "\" selected=\"selected\">" + iCount + "</option>";
	        	} else {
	        		Tempahan_Tahun += "<option value=\"" + iCount + "\">" + iCount + "</option>";
	        	}
        	}
        }
        Tempahan_Tahun += "</select>";
        
        context.put("locIsAvailable", locIsAvailable);
        context.put("isCheckedAvailability", isCheckedAvailability);
        context.put("isSavedSuccessfully", isSavedSuccessfully);
        context.put("Tempahan_Lokasi", Tempahan_Lokasi);
        context.put("Tempahan_Tarikh", Tempahan_Tarikh);
        context.put("Tempahan_Dari", Tempahan_Dari);
        context.put("Tempahan_Hingga", Tempahan_Hingga);
        context.put("Tempahan_NoRujukanDokumen", Tempahan_NoRujukanDokumen);
        context.put("Tempahan_Tajuk", Tempahan_Tajuk);
        context.put("Tempahan_Seksyen", Tempahan_Seksyen);
        context.put("Tempahan_Bulan", Tempahan_Bulan);
        context.put("Tempahan_Tahun", Tempahan_Tahun);
        
		return vm;
	}

	public void setupPage(HttpSession session, String action, Vector list) {
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