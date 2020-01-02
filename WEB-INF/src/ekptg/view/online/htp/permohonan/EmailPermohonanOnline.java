package ekptg.view.online.htp.permohonan;

import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.model.ppt.MyInfoPPTData;
//import ekptg.intergration.EmailProperty;
//import ekptg.model.htp.HTPEmelPermohonanBean;
//import ekptg.model.htp.IHTPEmel;
////import ekptg.view.ppt.email.EkptgEmailSender;
//import ekptg.intergration.EkptgEmailSender;

public class EmailPermohonanOnline {
	static Logger myLog = Logger.getLogger(ekptg.view.online.htp.permohonan.EmailPermohonanOnline.class);
	private ekptg.model.htp.IHTPEmel iEmel = null;

	public static void main(String args []){
		
	}
	
	/**
	 * @param userIdKementerian
	 * @param id_jawatan_user
	 * @param userID
	 */
	public void setEmailStatusPermohonanOnline(String userIdKementerian, String id_jawatan_user, String userID) throws Exception{	
		
//		myLog.debug("jenisSuburusan ::::: " + jenisSuburusan);
//		myLog.debug("emailTO ::::: " + emailTO);
//		myLog.debug("emailType ::::: " + emailType);
//		myLog.debug("nofail ::::: " + nofail);
//		myLog.debug("nama_projek ::::: " + nama_projek);
//		myLog.debug("tarikh_permohonan ::::: " + tarikh_permohonan);
//		myLog.debug("nama_kementerian ::::: " + nama_kementerian);
//		myLog.debug("setEmailTest userIdKementerian ::::: " + userIdKementerian);
//		myLog.debug("setEmailTest id_jawatan_user ::::: " + id_jawatan_user);
//		myLog.debug("setEmailTest userID ::::: " + userID);
		
		String subject_emel = "";	
		if(id_jawatan_user.equals("20") || id_jawatan_user.equals("24")){
			subject_emel = "MyeTaPP : Pengesahan Permohonan Online ";
		}else if(id_jawatan_user.equals("9")){
			subject_emel = "MyeTaPP : Kelulusan Permohonan Online ";
		}else if(id_jawatan_user.equals("4")){
			subject_emel = "MyeTaPP : Permohonan Telah Diluluskan ";
		}else{
			subject_emel = "MyeTaPP";
		}		
				
		//comment by aishah 11052017
		Vector checkEmailPengguna = new Vector();
		checkEmailPengguna.clear();

		// check email (pengarah)
		MyInfoPPTData myInfo = new MyInfoPPTData();
		checkEmailPengguna = myInfo.checkEmailKementerian(userIdKementerian, id_jawatan_user, userID);
		
		EmailProperty emailProperty = EmailProperty.getInstance();
		EmailSender emailSender = EmailSender.getInstance();
		myLog.debug("checkEmailPengguna rekod ::::: " + checkEmailPengguna.size() );
		emailSender.MULTIPLE_RECIEPIENT = new String[checkEmailPengguna.size()];
		if (checkEmailPengguna.size() != 0) {
			for(int i = 0; i < checkEmailPengguna.size();i++)
			{
				String emelPengguna = "";
				Hashtable ceP = (Hashtable) checkEmailPengguna.get(i);
				emelPengguna = (String) ceP.get("EMEL");					
				myLog.debug(":::: emelPengguna ::::"+emelPengguna);
				emailSender.MULTIPLE_RECIEPIENT[i] = emelPengguna;
			}
		}
	
		emailSender.FROM = emailProperty.getFrom();
		emailSender.SUBJECT = subject_emel;
		/*emailSender.MESSAGE =setMessageTable(emailType,nama_projek)+""+setParaGraph(emailType,"",nofail,tarikh_permohonan,nama_kementerian,nama_projek);*/
		emailSender.MESSAGE = setParagraph(userID,id_jawatan_user);
		//emailSender.RECIEPIENT = emelPengguna;
		/*emailSender.TO_CC = new String[1];		
		email.TO_CC[0] = "nurul_aishah@si-protech.com.my";*/
		emailSender.sendEmail();
	}
	
	
	/**
	 * @param id_jawatan_user
	 * @param userID
	 */
	public String setParagraph(String idUser, String id_jawatan_user){
	//public String setParagraph(String noRujukanOnline,String tarikh_permohonan,String namaKementerian,String tajuk){
		StringBuffer bff = new StringBuffer();
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");		  
	    //bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG.");
	    
	    if(id_jawatan_user.equals("20") || id_jawatan_user.equals("24")){
	    	bff.append("Permohonan ini memerlukan pengesahan sebelum dihantar kepada JKPTG.");
		}else if(id_jawatan_user.equals("9")){
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG.");
		}else if(id_jawatan_user.equals("4")){
			bff.append("Permohonan telah diluluskan, permohonan ini perlu dihantar kepada JKPTG.");
		}
		
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> ke <a href=\"http://www.myetapp.gov.my\" >www.myetapp.gov.my</a> untuk semakan selanjutnya.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, terima kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("myetappsupport@ekptg.gov.my");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Email ini adalah dijana oleh Sistem MyeTaPP dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
		
	}
}
