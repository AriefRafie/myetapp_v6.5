package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.ppk.FrmPrmhnnStatusPengunaOnlineData;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;
import ekptg.view.ppt.email.EmailTester;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.model.utils.emel.IEmel;


public class FrmPopupTAC extends AjaxBasedModule {
private static final long serialVersionUID = 1L;
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	static Logger myLogger = Logger.getLogger(FrmPopupTAC.class);
	
	FrmPrmhnnStatusPengunaOnlineData myInfo = new FrmPrmhnnStatusPengunaOnlineData();
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");
		context.put("userId", USER_LOGIN_SYSTEM);
		//String action = getParam("action");
		//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
		
		String vm = "";
	
		
		//String signedData = "";
			
		String id_Permohonan = getParam("id_Permohonan"); //lps klik dr cetakborangf senarai
		myLogger.info("id_Permohonan"+id_Permohonan);
		String idFail = getParam("idFail");
		myLogger.info("idFail"+idFail);

		
		vm = "app/ppk/frmPopupTAC.jsp";	
	
		//signedData = logic.getSignedData(idPerbicaraan);
		//this.context.put("signedData", signedData);
			
		//this.context.put("idPerbicaraan", idPerbicaraan);
		this.context.put("idFail", idFail);
		this.context.put("id_Permohonan", id_Permohonan);
		
		String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if ("simpan".equals(submit)) {
    			     
    		myLogger.info("masuk sini");
    		String result = "";
    		String result1 = "";
    		
    		if (doPost.equals("true")) {
    			result = addTAC(session);
    			//result1 = sendEmail(userId);
    		sendEmail(USER_LOGIN_SYSTEM,"TAC","","","",""); 
        		//sendEmail(idP); 
     		}
    		context.put("ResultAdd",result);
    		context.put("ResultAdd1",result1);
    		
    		id_Permohonan = (String) context.get("ResultAdd");
    		idFail = getParam("idFail");
    		
    		myLogger.info("USER_LOGIN_SYSTEM >>> "+USER_LOGIN_SYSTEM);
    		
    		myLogger.info(result);
    		myLogger.info("SESSION"+USER_LOGIN_SYSTEM);
    		
    		//result1 = sendEmail(userId);
    		sendEmail(USER_LOGIN_SYSTEM,"TAC","","","",""); 
    		result = addTAC(session);
 
   
        		//form validation
        		context.put("semak", "yes");
        		context.put("edit", "no");
        		
        	
		}//close simpan
    		
		return vm;
		
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void sendEmail(String userId,String jenisSend,String nofail,String nama_projek,String tarikh_permohonan,String nama_kementerian) throws Exception{
		
    	Vector checkEmail = new Vector();
    	checkEmail.clear();
    	
		//check email (pengarah)
		checkEmail = myInfo.checkEmail(userId);
		String emel= "";
		if(checkEmail.size()!=0){
			Hashtable ceP = (Hashtable)checkEmail.get(0);
			emel = (String)ceP.get("EMEL");
		}

		EmailTester et = new EmailTester();
		
		//if(emelPengarah!="" ){
			//et.setEmail("ppk",emelPengarah,"hantarUntukTAC");
		et.setEmail("PPK",emel,"hantarUntukTAC","","","","");
		
	}//close sendEmail
	
	
	public String checkEmailA(String userId) throws Exception {
		String returnValue = "";
		//checkEmail = new Vector();
		//checkEmail.clear();	
		Db db = null;
		String sql = "";		
		
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT EMEL FROM USERS_ONLINE WHERE USER_ID = '"+userId+"' AND EMEL IS NOT NULL";
	
			myLogger.info("checkEmail:sql="+sql.toUpperCase());		
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				
				returnValue = Utils.isNull(rs.getString("EMEL"));
			}
			EmailConfig et = new EmailConfig();
		
		//return checkEmail;
		return returnValue;
	
	}
	@SuppressWarnings("unchecked")
	private String addTAC(HttpSession session) throws Exception{
		
	    	Hashtable h = new Hashtable();
	    	    	
	    	h.put("idFail", getParam("idFail"));
	    	h.put("no_rujukan_upt", getParam("no_rujukan_upt"));	    	
	    	
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	
	    	myLogger.info(h.put("idFail", getParam("idFail")));
	    	
	    	return FrmPrmhnnStatusPengunaOnlineData.addTAC(h);
	  
	}//close add
	
	  private static char[] OTP(int len) 
	    { 
		   myLogger.info("Generating OTP using random() : "); 
		   myLogger.info("You OTP is : "); 
	  
	        // Using numeric values 
	        String numbers = "0123456789"; 
	  
	        // Using random method 
	        Random rndm_method = new Random(); 
	  
	        char[] otp = new char[len]; 
	  
	        for (int i = 0; i < len; i++) 
	        { 
	            // Use of charAt() method : to get character value 
	            // Use of nextInt() as it is scanning the value as int 
	            otp[i] = 
	             numbers.charAt(rndm_method.nextInt(numbers.length())); 
	        } 
	        return otp; 
	    } 
	    public static void main(String[] args) 
	    { 
	        int length = 6; 
	        myLogger.info(OTP(length)); 
	    } 
	    
	  
	
	
}
