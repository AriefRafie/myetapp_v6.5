package ekptg.model.admin;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ekptg.engine.EmailSender;
//import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserBean;

public class KonfigurasiEmel {
	
	static Logger myLog = Logger.getLogger(ekptg.model.admin.KonfigurasiEmel.class);
	public EmailSender mail = null;
	public String tajuk = "";
	public String kandungan = "";
//	public IUserPegawai iUser = null;
	public List<Map<String,String>> senaraiPengguna = null;

	public KonfigurasiEmel() {
		mail = EmailSender.getInstance();
	}
	
	public void emelMengikutRole(String userMail
		,String role
		,String idNegeri
		,String tajuk
		,String kandungan) throws Exception {
		
		//getUser().
		UserBean ub = new UserBean();
		List<Map<String,String>> senaraiPengguna = ub.penggunaMengikutRole(role, idNegeri);
					
		kandungan+= " <br><br>Sekian, terima kasih.<br><br><br>";			
		kandungan+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
		
		mail.SUBJECT = tajuk;
		mail.MESSAGE = kandungan;		
		
		//GET EMEL PEGAWAI ADMINEKPTG MENGIKUT NEGERI
		mail.MULTIPLE_RECIEPIENT = new String[senaraiPengguna.size()];
		for(int i = 0; i < senaraiPengguna.size();i++){			   
			Map<String,String> m = (Map<String,String>) senaraiPengguna.get(i);
			myLog.info(" EMEL PENGGUNA :"+(String) m.get("EMEL"));
			//EMEL UNTUK PENGGUNA
			mail.MULTIPLE_RECIEPIENT[i] = (String) m.get("EMEL");		
			//"simple1001plan@gmail.com";//	  
		   
		}
		
		mail.TO_CC = new String[1];		
		mail.TO_CC[0] = userMail;
		//mail.TO_CC[0] = "razman.zainal@gmail.com";
		mail.sendEmail();	
		
	}
		
//	private IUserPegawai getUser(){
//		if(iUser== null)
//			iUser = new UserBean();
//		return iUser;
//
//	}	
	
	
}
