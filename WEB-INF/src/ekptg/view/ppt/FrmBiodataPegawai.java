package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.ppt.FrmPermohonanUPTData;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmBiodataPegawai extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmBiodataPegawai.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		String vm = "";
		
    	//screen jsp
    	String mainScreen = "app/ppt/frmBiodataPegawai.jsp";
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	anchor();
    	
		//user login id
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	
    	//get nama pengarah dan id negeri user
    	nameAndId(id_user);
    	String userIdNeg = nameAndId(id_user);
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange","no");

    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("xxx".equals(submit)){
    		
    		//screen
    		vm = mainScreen;
    		
    	}//close   
    	
    	else{	
    		
    		//dropdown
    		if(userIdNeg!=""){
    			context.put("selectPegawai",HTML.SelectPegawaiPPTByNegeri(userIdNeg,"socPegawai",null,null,"style=width:325px onChange=\"doChangePegawai()\""));
        	}else{
    			context.put("selectPegawai",HTML.SelectPegawaiPPT("socPegawai",null,null,"style=width:325px onChange=\"doChangePegawai()\""));
        	}
    		
    		resetValue();
			
    		//screen
    		vm = mainScreen;
    		
		}//close else
    	
    		return vm;
    		
	    }//close public template
	
	
	
	
//--METHOD--//	
	
	private void resetValue() throws Exception{
    	
		context.put("", "");
		context.put("", "");
		context.put("", "");
		
	}//close resetValue
	
	private void anchor() throws Exception{
    	
		String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
	}//close anchor
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String nameAndId(String id_user) throws Exception{
		
		Vector listUserid = new Vector();
		
		listUserid.clear();
		
		modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	   
	    context.put("userIdNeg", userIdNeg);
	    
	    return userIdNeg;
	    
	}//close nameAndId
	
}//close class
