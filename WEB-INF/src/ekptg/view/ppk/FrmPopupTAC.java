package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	
	static FrmPrmhnnStatusPengunaOnlineData myInfo = new FrmPrmhnnStatusPengunaOnlineData();
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");
		context.put("userId", USER_LOGIN_SYSTEM);
		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Date currentDate = new Date();
         String newDate = df.format(currentDate);
		//String action = getParam("action");
		//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");		
		String vm = "";	
		String otp = "";
		String notac = "";
		String exp = "";
				
		
		String id_Permohonan = getParam("id_Permohonan"); //lps klik dr cetakborangf senarai
		String idFail = getParam("idFail");
		
		//11112020
		String flagBorang = getParam("flagBorang");
		String idCetakan = getParam("idCetakan");
		String idPermohonan = getParam("idPermohonan");
		
		vm = "app/ppk/frmPopupTAC.jsp";	

		context.put("notac","");
		context.put("verify","");
		this.context.put("idFail", idFail);
		this.context.put("id_Permohonan", id_Permohonan);
		
		this.context.put("flagBorang", flagBorang);
		this.context.put("idCetakan", idCetakan);
		this.context.put("idPermohonan", idPermohonan);
		
		String submit = getParam("command");
		
    	myLogger.info("submit : " + submit);
    	
    	if ("simpan".equals(submit)) {
    			    
    		notac = "";  
    		otp = "";    
    		String result = "";
   	
    		context.put("ResultAdd",result);
    		
    		id_Permohonan = (String) context.get("ResultAdd");
    		idFail = getParam("idFail");
    		flagBorang = getParam("flagBorang");
    		idCetakan = getParam("idCetakan");
    		idPermohonan = getParam("idPermohonan");
    
    		//OTP();
    		otp =  FrmPopupTAC.OTP();
    		exp =  FrmPopupTAC.expDate();
    		context.put("otp",otp);
    		context.put("exp",exp);
    		
    		sendEmail(USER_LOGIN_SYSTEM,otp,exp);
    		String flag = "1" ;
    		context.put("flag",flag);
    		result = addTAC(session,otp,flag);
 
        	//form validation
        	context.put("semak", "yes");
        	context.put("edit", "no");       		
        	
		}//close simpan
    	else 
    		if ("hantar".equals(submit)){
    			
    		notac = getParam("notac");
    		String otpDB = "";
    		otpDB =  FrmPopupTAC.OtpDB(USER_LOGIN_SYSTEM, idFail);
    		
    		String dateotpDB = "";
    		
    		dateotpDB =  FrmPopupTAC.dateOtpDB(USER_LOGIN_SYSTEM, idFail);
    		context.put("notac", notac);
    		//myLogger.info("notac" +notac);
    		context.put("otp", "otp");
    		context.put("otpDB", "otpDB");
    		context.put("dateotpDB", "dateotpDB");	
    		context.put("exp", "exp");
    		context.put("dateDB", "dateDB");
    		myLogger.info("dateotpDB : " +dateotpDB);
    		
    		idCetakan = getParam("idCetakan");
    		idPermohonan = getParam("idPermohonan");
    		
    		String flagCetak = "";
    		
    		flagCetak = FrmPopupTAC.semakCetakan(idPermohonan, idCetakan);
    		myLogger.info("flag  : "+ flagCetak);
    		
    		if (flagCetak.equals("1")) {
    			
    			String verifyOTP = "";
    			
        		verifyOTP = FrmPopupTAC.verifyOTP(notac, otpDB, dateotpDB);
        		myLogger.info("verifyotp : "+verifyOTP);
        		
        		if(verifyOTP == "false"){
        			
        			myLogger.info("No. TAC tidak tepat");
        			
        			context.put("verify", "false");
        			context.put("note", "No. TAC tidak sah!");
        			
        		} 
        		else if (verifyOTP == "expired"){
        			
        			myLogger.info("No. TAC expired");
        			
        			context.put("verify", "false");
        			context.put("note", "No. TAC telah tamat tempoh!");
        			
        		} 
        		else if (verifyOTP == "true"){
        			
        			myLogger.info("No. TAC sah");
        			
        			context.put("verify", "true");
        			context.put("note", "No. TAC sah.");
        		}
    		} else {
    			context.put("verify", "false");
    			context.put("note", "Perintah telah dimuat turun!");
    		}
    		
    		//context.put("notac", "notac");
    	} else
    		if ("update".equals(submit)){
    			
        		idPermohonan = getParam("idPermohonan");
        		idCetakan = getParam("idCetakan");
        		
        		myLogger.info("UPDATE FLAG 1: " + idCetakan + " - "+idPermohonan);
        		
        		updateFlag(idPermohonan,idCetakan);
    		}
		return vm;		
	}
	
	private static String OtpDB(String userId,String idFail) throws Exception{
	        
			Vector checkOTPDB = new Vector();
	    	checkOTPDB.clear();
			checkOTPDB = myInfo.checkOTP(userId,idFail);
			String otpDB= "";
			if(checkOTPDB.size()!=0){
				Hashtable ceP = (Hashtable)checkOTPDB.get(0);
				otpDB = (String)ceP.get("NO_TAC");
			}
			//myLogger.info("OTP DARI DB"+otpDB);
	           return otpDB;        	        		
	    } 
	private static String dateOtpDB(String userId,String idFail) throws Exception{
       
		Vector dateOTPDB = new Vector();
    	dateOTPDB.clear();
		dateOTPDB = myInfo.checkDateOTP(userId,idFail);
		String dateotpDB = "";
				
		if(dateOTPDB.size()!=0){
			Hashtable ceP = (Hashtable)dateOTPDB.get(0);
			dateotpDB = (String)ceP.get("TARIKH_MASUK");
			
		}
		
        return dateotpDB;        	        		
    } 
	
	 private static String OTP() 
	    { 		       
	      //generate random number
	        java.util.Random generator = new java.util.Random();
	        generator.setSeed(System.currentTimeMillis());
	        int i = generator.nextInt(1000000) % 1000000;
	        java.text.DecimalFormat f = new java.text.DecimalFormat("000000");
	       
	        String otp = f.format(i);	               	        
	        return otp; 
	    } 
	 
	 private static String expDate() 
	    {
		 	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    	
	    	Calendar c = Calendar.getInstance();
	        Date currentDate = new Date();
			c.setTime(currentDate);
	      
			c.add(Calendar.DATE, 1); // date sehari
	       /* c.add(Calendar.YEAR, 1);
	        c.add(Calendar.MONTH, 1);
	        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
	        c.add(Calendar.HOUR, 1);
	        c.add(Calendar.MINUTE, 1);
	        c.add(Calendar.SECOND, 1);*/
	        // convert calendar to date
	        Date currentDatePlusOne = c.getTime();
   
	        String exp = format.format(currentDatePlusOne);	               	        
	        return exp; 
	    } 
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void sendEmail(String userId,String otp,String exp) throws Exception{
	
    	Vector checkEmail = new Vector();
    	checkEmail.clear();
		checkEmail = myInfo.checkEmail(userId);
		String emel= "";
		if(checkEmail.size()!=0){
			Hashtable ceP = (Hashtable)checkEmail.get(0);
			emel = (String)ceP.get("EMEL");
		}
		myLogger.info(otp);
		
		EmailConfig ef = new EmailConfig();
		String userMail = emel;
		String tajuk = "Modul Pengurusan Pusaka: Permohonan TAC Untuk Cetakan Perintah";
		String kandungan = "<br/>" +
				"No. TAC:" + 
				otp +
				"<br></br>" +
				"No.TAC ini hanya sah sehingga " + exp + 
				"";
		ef.sendTo(userMail,tajuk, kandungan);
		
	}//close sendEmail
		
	@SuppressWarnings("unchecked")
	private String addTAC(HttpSession session,String otp,String flag) throws Exception{
		
	    Hashtable h = new Hashtable();
	    	    	
	    h.put("idFail", getParam("idFail"));
	    h.put("no_rujukan_upt", getParam("no_rujukan_upt"));	    		    	
	    h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    h.put("otp",otp);
	    h.put("flag",flag);
	 
	    return FrmPrmhnnStatusPengunaOnlineData.addTAC(h,otp,flag);	  
	}//close add
	
	// update flag cetakan 11112020
	@SuppressWarnings("unchecked")
	private String updateFlag(String idPermohonan,String idCetakan) throws Exception{
		
	    Hashtable h = new Hashtable();
	    	    	
	    h.put("idPermohonan", idPermohonan);
	    h.put("idCetakan", idCetakan);	    		    	
	    h.put("flagCetakan","2");
	    
	    myLogger.info("UPDATE FLAG 2: " + idCetakan + " - "+idPermohonan);
	 
	    return FrmPrmhnnStatusPengunaOnlineData.updateFlag(h);	  
	}//close add
		 	 	
	private static String verifyOTP(String otpFrmUser,String otpFrmDB, String masaMasukOTPdb) 
    { 	  
	     //current date ni kne ikut format sysdate tu untuk compare
         DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Date currentDate = new Date();
         //ni get current datetime
         //guna untuk compare masa user terima otp dan waktu verify
         String currentD=df.format(currentDate);
	
         //compare otpDB dan otpUser
         if(otpFrmUser.equals(otpFrmDB)){
        	 myLogger.info("otp match");
         //ni pun kene ikut frmat sysdate
         //SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	 		Date d1 = null;
	 		Date d2 = null;
	 		//ni declaration ikut seken,minit,jam,dan hari
	 		//pilih nak guna yg mana
	 		//biasa otp guna minit je
	 		long diffSeconds;
	 	    long diffMinutes = 0;
	 	    long diffHours;
	 	    long diffDays;
	 	    //otptime ni dlam minit *5 minit
	 	    //skrg ni hardcoded, nak set dekat config boleh
	 	    //nak set dekat db pun boleh
	 	    //klau set dekat db kene get dan bawa ke function ni
	 	    int otptime = 1; //1 days
	 	    //ni waktu/masa save otp didalam db.. akan guna 
	 	    //untuk compare waktu user terima otp dan masa dia verify
	 	    //expired atau tidak
	 	    String otpExp = masaMasukOTPdb;
	
	 		try {
	 			d1 = df.parse(otpExp);
	 			d2 = df.parse(currentD);
	 		
	 			//in milliseconds
	 			long diff = d2.getTime() - d1.getTime();
	 			
	 			diffSeconds = diff / 1000 % 60;
	 			diffMinutes = diff / (60 * 1000) % 60;
	 			diffHours = diff / (60 * 60 * 1000) % 24;
	 			diffDays = diff / (24 * 60 * 60 * 1000);

	 			myLogger.info(diffDays + " days, ");
	 			myLogger.info(diffHours + " hours, ");
	 			myLogger.info(diffMinutes + " minutes, ");
	 			myLogger.info(diffSeconds + " seconds.");

	 		} catch (Exception e) {
	 			e.printStackTrace();
	 			myLogger.info("exception "+e);
	 			return "exception masa calculation";
	 		}            
	 		//ni compare setelah tolak waktu dia verify dan waktu
	 		//terima otp. klau minit masa dia verify tu lebih dari
	 		//waktu kita set 5 minit di atas td, kira expired lah otp tu
             if(diffDays>otptime){               
            	myLogger.info("otp already expired"); 
                return "expired";                 
             }else{
                //ni otp valid
            	myLogger.info("otp valid");  
             }          
         }else{
             //ni otp x sama..yg dia terima dgn yg dia verify
        	 myLogger.info("otp not match");
             return "false";            
         }       
        //class ni return string
         //ubah lah ikut kesesuaian
         	return "true"; 
    }
	
	//11112020
	private static String semakCetakan(String idPermohonan, String idCetakan) throws Exception {
		Vector checkMuatTurun = new Vector();
		checkMuatTurun.clear();
		checkMuatTurun = myInfo.checkFlagCetakan(idPermohonan,idCetakan);
		
		String flagDownload = "";
		
		if(checkMuatTurun.size()!=0){
			Hashtable h = (Hashtable)checkMuatTurun.get(0);
			flagDownload = (String)h.get("FLAG_CETAK_PERINTAH");
		}
		myLogger.info("FLAG_CETAK_PERINTAH -> " + flagDownload);
		return flagDownload;	
	}

}
