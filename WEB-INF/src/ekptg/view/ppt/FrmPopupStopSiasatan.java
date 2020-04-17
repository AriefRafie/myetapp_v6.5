package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmPermohonanUPTData;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */


public class FrmPopupStopSiasatan extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPopupStopSiasatan.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

    	//default
    	String vm = "";
    	context.put("showAlert","no");
    	
    	//screen jsp
    	String screenPopup = "app/ppt/frmPopupStopSiasatan.jsp";

    	//id
    	String id_hakmilik = getParam("id_hakmilik");
    	String id_permohonan = getParam("id_permohonan");
    	String id_suburusanstatusfailppt = getParam("id_suburusanstatusfailppt");
    	String id_fail = getParam("id_fail");
    	
    	//data hakmilik
    	getData(id_hakmilik);
    	
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if ("updateCatatan".equals(submit)){
    		updateCatatan(session,id_hakmilik,id_permohonan,id_fail,id_suburusanstatusfailppt);
    		context.put("showAlert","yes");
    		//data hakmilik
        	getData(id_hakmilik);
    	}
	
    	//screen
    	vm = screenPopup;
    
    	context.put("id_hakmilik",id_hakmilik);
    	context.put("id_permohonan",id_permohonan);
    	context.put("id_fail",id_fail);
    	context.put("id_suburusanstatusfailppt",id_suburusanstatusfailppt);
    	return vm;
    	
	    }//close public template
	
	
//--METHOD--//	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getData(String id_hakmilik) throws Exception{
		
		Vector dataStopSiasatan = new Vector();
		dataStopSiasatan.clear();		
		dataStopSiasatan = modelUPT.getDataStopSiasatan(id_hakmilik);
		
		//data
		context.put("dataStopSiasatan",dataStopSiasatan);
		
	}//close getDataTolakPermohonan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateCatatan(HttpSession session,String id_hakmilik,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
		
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_fail", id_fail);
		h.put("id_suburusanstatusfailppt", id_suburusanstatusfailppt);
		h.put("txtCatatan", getParam("txtCatatan").toUpperCase());
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelUPT.updateCatatan(h);
		
	}//close simpanCatatanTolak
	
}//close class
