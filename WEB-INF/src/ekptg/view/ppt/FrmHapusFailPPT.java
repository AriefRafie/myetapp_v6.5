package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppt.MyInfoPPTData;

public class FrmHapusFailPPT extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmHapusFailPPT.class);
	
	//model
	MyInfoPPTData model = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public String doTemplate2() throws Exception{

		HttpSession session = request.getSession();

		Vector list = new Vector();  	
    	list.clear();

    	//screen
    	String screenHapus = "app/ppt/frmHapusFailPPT.jsp";
    	
    	//default
    	String vm = "";
   		context.put("showItem","no");
   		context.put("showDelete","no");
   		
   		//anchor
    	anchor();
    	
   		String doPost = (String) session.getAttribute("doPost");
   		String idNegeriUser =(String)session.getAttribute("_ekptg_user_negeri");
   		
    	String submit = getParam("command");
    	myLogger.info("[submit] :: " +submit);

    	if ("semakFail".equals(submit)){
    		
    		//model
    		model = new MyInfoPPTData();
    		
    		//form validation
    		context.put("showItem","yes");
    		
    		//get and set data
    		collectionData(getParam("txtNoFail"),idNegeriUser);
  
    		String submit2 = getParam("command2");
        	myLogger.info("[submit2] :: " +submit2);

        	if ("hapusFail".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			ChangeStatus(session,getParam("id_permohonan"));
        		}
        		
        		//reset data
        		resetValue();
        		
        		//form validation
        		context.put("showItem","no");
        		context.put("showDelete","yes");
        		
        	}//close hapusFail
    		
    		vm = screenHapus;
    		
    	}//close cari
    	
    	else{

    		//reset data
    		resetValue();

    		vm = screenHapus;
    		
    	}//close else
    	
    	return vm;
    	
	}//close class

	
	//change status
	@SuppressWarnings({ "unchecked", "static-access" })
	private void ChangeStatus(HttpSession session,String id_permohonan) throws Exception{
		
		Hashtable h = new Hashtable();
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("id_permohonan", id_permohonan);
		
		model.updateStatus(h);
		
	}//close ChangeStatus
	
	
	//get Collection Data from no fail
	@SuppressWarnings({ "unchecked", "static-access" })
	private void collectionData(String no_fail, String idNegeriUser) throws Exception{
		 
		Vector dataCollectionData = new Vector();
		dataCollectionData.clear();
		
		model.setCollectionData(no_fail,idNegeriUser);
		dataCollectionData = model.getCollectionData();
		
		String id_permohonan = "";
		String id_fail = "";
		
		if(dataCollectionData.size()!=0){
			Hashtable dcd = (Hashtable)dataCollectionData.get(0);	
			id_permohonan = (String)dcd.get("id_permohonan");
			id_fail = (String)dcd.get("id_fail");
		}
		
		//id
		context.put("id_permohonan",id_permohonan);
		context.put("id_fail",id_fail);
		
		//data
		context.put("dataCollectionData",dataCollectionData);
		context.put("saiz_dataCollectionData",dataCollectionData.size());
		
		//set
		context.put("txtNoFail", no_fail.trim());
		
	}//close getCollectionData
	
	//reset value
	private void resetValue() throws Exception{
		   
		context.put("txtNoFail","");
		context.put("dataCollectionData","");
		context.put("saiz_dataCollectionData",0);
		
	}//close resetValue
	
	private void anchor() throws Exception{
		String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
	}//close anchor
	
}//close class
