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
	
	FrmPrmhnnStatusPengunaOnlineData myInfo = new FrmPrmhnnStatusPengunaOnlineData();
	
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
		String exp = "";
				
		String id_Permohonan = getParam("id_Permohonan"); //lps klik dr cetakborangf senarai
		String idFail = getParam("idFail");
		
		vm = "app/ppk/frmPopupTAC.jsp";	

		this.context.put("idFail", idFail);
		this.context.put("id_Permohonan", id_Permohonan);
		
		String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if ("simpan".equals(submit)) {
    			    
    		otp = "";    
    		myLogger.info("masuk sini");
    		String result = "";
    		String result1 = "";
   	
    		context.put("ResultAdd",result);
    		context.put("ResultAdd1",result1);
    		
    		id_Permohonan = (String) context.get("ResultAdd");
    		idFail = getParam("idFail");
    
    		//OTP();
    		otp =  FrmPopupTAC.OTP();
    		exp =  FrmPopupTAC.expDate();
    		context.put("otp",otp);
    		context.put("exp",exp);
    		
    		myLogger.info("otp : "+otp);
    		myLogger.info("exp : "+exp);
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

    		String notac = getParam("notac");
    		
    		//String otpDB =  FrmPrmhnnStatusPengunaOnlineData.checkOTP(idFail,USER_LOGIN_SYSTEM);
    		context.put("notac", "notac");
    		myLogger.info("notac" +notac);
    		context.put("otp", "otp");
    		context.put("exp", "exp");
			String verifyOTP = "";
			//context.put("otpUser",otpUser);
    		verifyOTP = FrmPopupTAC.verifyOTP(notac, otp, exp);
    		myLogger.info("verifyotp : "+verifyOTP);
    	}    		
		return vm;		
	}
	
	 private static String OTP() 
	    { 
		   myLogger.info("Generating OTP using random() : "); 
		   myLogger.info("You OTP is : "); 
	        
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
		 SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    	
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

			System.out.println("DATE EXPIRED FORMAT "+format.format(currentDatePlusOne));
   
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
	    	myLogger.info(h.put("otp", getParam("otp")));
	    	
	    	return FrmPrmhnnStatusPengunaOnlineData.addTAC(h,otp,flag);
	  
	}//close add
		 	 	
	private static String verifyOTP(String otpFrmUser,String otpFrmDB, String masaMasukOTPdb) 
    { 	  
	     //current date ni kne ikut format sysdate tu untuk compare
         DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Date currentDate = new Date();
         //ni get current datetime
         //guna untuk compare masa user terima otp dan waktu verify
         String currentD=df.format(currentDate);
	
         System.out.println("current date ---- "+currentD);	
         System.out.println("otpFrmUser ---- "+otpFrmUser);	
         System.out.println("otpFrmDB ---- "+otpFrmDB);	
         System.out.println("masaMasukOTPdb ---- "+masaMasukOTPdb);		

         //compare otpDB dan otpUser
         if(otpFrmUser.equals(otpFrmDB)){
        	 System.out.println("otp match");
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

				System.out.println("date expired ----------- "+d1);
				System.out.println("date current ----------- "+d2);

	 			//in milliseconds
	 			long diff = d2.getTime() - d1.getTime();

	 			 diffSeconds = diff / 1000 % 60;
	 			 diffMinutes = diff / (60 * 1000) % 60;
	 			 diffHours = diff / (60 * 60 * 1000) % 24;
	 			 diffDays = diff / (24 * 60 * 60 * 1000);

	 			System.out.print(diffDays + " days, ");
	 			System.out.print(diffHours + " hours, ");
	 			System.out.print(diffMinutes + " minutes, ");
	 			System.out.print(diffSeconds + " seconds.");

	 		} catch (Exception e) {
	 			e.printStackTrace();
	 			System.out.print("exception "+e);
	 			return "exception masa calculation";
	 		}
             
	 		//ni compare setelah tolak waktu dia verify dan waktu
	 		//terima otp. klau minit masa dia verify tu lebih dari
	 		//waktu kita set 5 minit di atas td, kira expired lah otp tu
             if(diffDays>otptime){
                
                 System.out.println("otp already expired"); 
                 return "otp expired";
                 
             }else{
                //ni otp valid
                 System.out.println("otp valid");  
               
             }
             
         }else{
             //ni otp x sama..yg dia terima dgn yg dia verify
             System.out.println("otp not match");
             return "otp not match";
             
         }
        
        //class ni return string
         //ubah lah ikut kesesuaian
        return "otp valid"; 
    }
	    
	  
	
	
}
