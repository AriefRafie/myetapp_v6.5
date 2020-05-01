package ekptg.model.admin;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ekptg.engine.EmailSender;
//import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserBean;

public class EmailConfig {
	
	static Logger myLog = Logger.getLogger(ekptg.model.admin.EmailConfig.class);
	public EmailSender mail = null;
	public String tajuk = "";
	public String kandungan = "";
//	public IUserPegawai iUser = null;
	public List<Map<String,String>> senaraiPengguna = null;

	public EmailConfig() {
		mail = EmailSender.getInstance();
	}
	
	public void sendByRole(String userMail
		,String role
		,String idNegeri
		,String tajuk
		,String kandungan) throws Exception {
		
		//getUser().
		UserBean ub = new UserBean();
		List<Map<String,String>> senaraiPengguna = ub.penggunaMengikutRole(role, idNegeri);
		myLog.info("senarai size="+senaraiPengguna.size());
		
		kandungan+= " <br><br>Sekian, terima kasih.<br><br><br>";			
		kandungan+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
		
		mail.SUBJECT = tajuk;
		mail.MESSAGE = kandungan;		
		
		//GET EMEL MENGIKUT ROLE
		//mail.MULTIPLE_RECIEPIENT = new String[senaraiPengguna.size()];
		mail.MULTIPLE_RECIEPIENT = new String[1];
//		for(int i = 0; i < senaraiPengguna.size();i++){			   
//			Map<String,String> m = (Map<String,String>) senaraiPengguna.get(i);
//			myLog.info(" EMEL PENGGUNA :"+(String) m.get("EMEL"));
//			//EMEL UNTUK PENGGUNA
//			mail.MULTIPLE_RECIEPIENT[i] = (String) m.get("EMEL");		
//			//"simple1001plan@gmail.com";//	  
//		   
//		}
		mail.MULTIPLE_RECIEPIENT[0] = userMail;
		
//		mail.TO_CC = new String[1];		
//		mail.TO_CC[0] = userMail;
		mail.sendEmail();	
		
	}
	
	public void sendByOnlineUser(String userMail
		,String tajuk
		,String kandungan) throws Exception {
		
		kandungan+= " <br><br>Sekian, terima kasih.<br><br><br>";			
		kandungan+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
		
		mail.SUBJECT = tajuk;
		mail.MESSAGE = kandungan;		
		mail.RECIEPIENT = userMail;
//		mail.TO_CC = new String[1];		
		mail.sendEmail();	
	}
	
	public void sendByKJPPenyedia(String idKementerian, String userMail, String tajuk,
			String kandungan) throws Exception {
		
		UserBean ub = new UserBean();
		List<Map<String,String>> senaraiKJPPenyedia = ub.getKementerianPenyedia(idKementerian);
		myLog.info("senarai size="+senaraiKJPPenyedia.size());
		
		kandungan+= " <br><br>Sekian, terima kasih.<br><br><br>";			
		kandungan+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
		
		mail.SUBJECT = tajuk;
		mail.MESSAGE = kandungan;		
		mail.MULTIPLE_RECIEPIENT = new String[1];
		mail.MULTIPLE_RECIEPIENT[0] = userMail;
		mail.sendEmail();

	}
		
//	private IUserPegawai getUser(){
//		if(iUser== null)
//			iUser = new UserBean();
//		return iUser;
//
//	}	
	
	
}
