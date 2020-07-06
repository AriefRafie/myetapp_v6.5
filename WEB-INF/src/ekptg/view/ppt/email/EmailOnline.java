package ekptg.view.ppt.email;

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
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.EmelSemakanBean;
import ekptg.model.utils.emel.IEmel;

public class EmailOnline {
	static Logger myLog = Logger.getLogger(ekptg.view.ppt.email.EmailOnline.class);
	private ekptg.model.utils.emel.IEmel iEmel = null;

	public static void main(String args []){
		
	}
	
	public void setEmail_(String jenisSuburusan, String emailTO, String emailType, 
								String nofail,String nama_projek,String tarikh_permohonan, String nama_kementerian, String userIdKementerian, String id_jawatan_user, String userID) 
										throws Exception{	
		EmailConfig ec = new EmailConfig();
		String emelSubjek = ec.tajukSemakan +"Online Pengambilan Tanah";
		//String kandungan = getEmelSemak().setKandungan(nama_projek,);
		// = getEmelSemak().setKandungan(htpPermohonan.getPermohonan().getPfdFail().getTajukFail(), String.valueOf(hashUser.get("nama")));

		String subject_emel = "";
		if(emailType.equals("hantarSemakanPenarikan") || emailType.equals("hantarLulusPenarikan")){
			subject_emel = "MyeTaPP : Pengesahan Permohonan Penarikan Balik Online ";
		}else if(emailType.equals("hantarSemakanPembatalan") || emailType.equals("hantarLulusPembatalan")){
			subject_emel = "MyeTaPP : Pengesahan Permohonan Pembatalan Online ";
		}else if(emailType.equals("hantarSemakanBantahan") || emailType.equals("hantarLulusBantahan")){
			subject_emel = "MyeTaPP : Pengesahan Permohonan Bantahan Online ";
		}else if(emailType.equals("mintaBayaranPampasan")){
			subject_emel = "MyeTaPP : Mohon Supaya Menyediakan Bayaran Pampasan ";
		}else{
			subject_emel = "MyeTaPP : Pengesahan Permohonan Online Pengambilan Tanah ";
		}		
		
		//default sender email
		/*String email_from = "etappsupport@jkptg.gov.my";
		EmailProperty pro = EmailProperty.getInstance();
		if(pro.getHost().equals("mail.hla-group.com")) 
			email_from = "rosli@hla-group.com";
		else if (pro.getHost().equals("smtp.gmail.com"))
			email_from = "roslizakaria@gmail.com";
		else{
			//email_from = getEmel().checkEmail(userId);
		}*/
		
		//comment by aishah 09052017
//		EkptgEmailSender email = EkptgEmailSender.getInstance();
//		email.FROM = email_from;
//		email.SUBJECT = subject_emel;
//		email.MESSAGE =setMessageTable(emailType,nama_projek)+""+setParaGraph(emailType,"",nofail,tarikh_permohonan,nama_kementerian,nama_projek);	
//		email.MULTIPLE_RECIEPIENT = new String[1];
//		email.MULTIPLE_RECIEPIENT[0] = "etappsupport@jkptg.gov.my";
//		
//		email.TO_CC = new String[4];
//		email.TO_CC[0] = "noorazam@hla-group.com";
//		email.TO_CC[1] = "faresh@hla-group.com";
//		email.TO_CC[2] = "razman@hla-group.com";
//		email.TO_CC[3] = "roslizakaria@gmail.com";
//	
//		email.sendEmail();

		//String EMAIL_HOST = pro.getHost_GM();
		
		//comment by aishah 11052017
		Vector checkEmailPengguna = new Vector();
		checkEmailPengguna.clear();

		// check email (pengarah)
		MyInfoPPTData myInfo = new MyInfoPPTData();
		checkEmailPengguna = myInfo.checkEmailKementerian(userIdKementerian, id_jawatan_user, userID);
		
		
		
		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		
		email.MULTIPLE_RECIEPIENT = new String[checkEmailPengguna.size()];
		if (checkEmailPengguna.size() != 0) {
			for(int i = 0; i < checkEmailPengguna.size();i++)
			{
				String emelPengguna = "";
				Hashtable ceP = (Hashtable) checkEmailPengguna.get(i);
				emelPengguna = (String) ceP.get("EMEL");					
				System.out.println(":::: emelPengguna ::::"+emelPengguna);
				email.MULTIPLE_RECIEPIENT[i] = emelPengguna;
			}
		}
	
		email.FROM = pro.getFrom();
		email.SUBJECT = subject_emel;
		email.MESSAGE =setMessageTable(emailType,nama_projek)+""+setParaGraph(emailType,"",nofail,tarikh_permohonan,nama_kementerian,nama_projek);
		//email.RECIEPIENT = emelPengguna;
		/*email.TO_CC = new String[1];		
		email.TO_CC[0] = "nurul_aishah@si-protech.com.my";*/
		email.sendEmail();
	}
	/**
	 * 03/07/2020
	 * @param jenisSuburusan
	 * @param emailTO
	 * @param emailType
	 * @param nofail
	 * @param nama_projek
	 * @param tarikh_permohonan
	 * @param nama_kementerian
	 * @param idKementerian
	 * @param idJawatan
	 * @param userID
	 * @param userName
	 * @throws Exception
	 * 
	 */
	public void setEmail(String jenisSuburusan, String emailTO, String emailType, String nofail,String nama_projek
		,String tarikh_permohonan, String nama_kementerian, String idKementerian, String idJawatan, String userID
		,String userName) throws Exception{	
		EmailConfig ec = new EmailConfig();
		String subject_emeld = ec.tajukSemakan;
		String subject_emel = "";
		String kandungan = getEmelSemak().setKandungan(nama_projek,userName);

		if(emailType.equals("hantarSemakanPenarikan") || emailType.equals("hantarLulusPenarikan")){
			subject_emel = "Penarikan Balik Online ";
		}else if(emailType.equals("hantarSemakanPembatalan") || emailType.equals("hantarLulusPembatalan")){
			subject_emel = "Pembatalan Online ";
		}else if(emailType.equals("hantarSemakanBantahan") || emailType.equals("hantarLulusBantahan")){
			subject_emel = "Bantahan Online ";
		}else if(emailType.equals("mintaBayaranPampasan")){
			subject_emel = "Supaya Menyediakan Bayaran Pampasan ";
		}else{
			subject_emel = "Online Pengambilan Tanah ";
		}		
		subject_emeld +=subject_emel;
		//if(!getEmelSemak().checkEmail(userID).equals(""))
		//	getIHTP().getErrorHTML("[ONLINE-HTP PERMOHONAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

		if(idJawatan.equals("20")||idJawatan.equals("24")){
			ec.sendByRoleKJP(getEmelSemak().checkEmail(userID), "9", idKementerian, subject_emeld, kandungan);
		}else if (idJawatan.equals("9")){
			ec.sendByRoleKJP(getEmelSemak().checkEmail(userID), "4", idKementerian, subject_emeld, kandungan);
		}else if (idJawatan.equals("4")){
			subject_emel = ec.tajukHantarPermohonan + subject_emel;		
			kandungan = getEmelSemak().setKandungan(nama_projek,nama_kementerian,nofail);
			
			//   (HTP)HQPenggunaPermohonan, (HTP)HQPengguna
			//ec.hantarPermohonan(emailTO, "(HTP)HQPenggunaPermohonan", subject_emel, kandungan);
			ec.sendByOnlineUser(emailTO, subject_emel, kandungan);
			
		}
		
	}
	
	public static String setMessageTable(String emailType,String nama_projek){
		StringBuffer bff = new StringBuffer();
		bff.append("<table border='0'>");
		bff.append("<tr>");
		bff.append("<td><b>NAMA PROJEK : "+nama_projek.toUpperCase()+"</b></td>");
		bff.append("</tr>");
		bff.append("</table>");	
		return bff.toString();
		
	}
	
	public static String setParaGraph(String emailType,String suburusan,String nofail,String tarikh_permohonan_kjp,String nama_kementerian,String nama_projek){		
		StringBuffer bff = new StringBuffer();
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");
				
		// Perdaftaran Permohonan
		if(emailType.equals("hantarSemakan")){		
			//bff.append("Sila sahkan maklumat permohonan Pengambilan Tanah yang didaftar pada "+tarikh_permohonan_kjp+".");	
			bff.append("Sila sahkan maklumat permohonan Pengambilan Tanah untuk projek "+nofail+" "+nama_projek+".");	
		}else if(emailType.equals("hantarLulus")){
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG. ");
		}else if(emailType.equals("lulus")){
			bff.append("Permohonan telah diluluskan, permohonan ini perlu dihantar kepada JKPTG. ");		
		// Perdaftaran Penarikan
		}else if(emailType.equals("hantarSemakanPenarikan")){		
			bff.append("Sila sahkan maklumat permohonan Penarikan Balik sebelum dihantar kepada Pengarah untuk proses kelulusan.");	
		}else if(emailType.equals("hantarLulusPenarikan")){		
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG.");	
		// Perdaftaran Pembatalan
		}else if(emailType.equals("hantarSemakanPembatalan")){		
			bff.append("Sila sahkan maklumat permohonan Pembatalan sebelum dihantar kepada Pengarah untuk proses kelulusan.");	
		}else if(emailType.equals("hantarLulusPembatalan")){		
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG.");	
		// Perdaftaran Bantahan
		}else if(emailType.equals("hantarSemakanBantahan")){		
			bff.append("Sila sahkan maklumat permohonan Bantahan sebelum dihantar kepada Pengarah untuk proses kelulusan.");	
		}else if(emailType.equals("hantarLulusBantahan")){		
			bff.append("Maklumat Permohonan telah disemak, permohonan ini memerlukan kelulusan dari Pengarah sebelum dihantar kepada JKPTG.");	
		}else if(emailType.equals("mintaBayaranPampasan")){		
			bff.append("MEMOHON BAYARAN PAMPASAN PENGAMBILAN TANAH UNTUK PROJEK "+nama_projek+".");	
		}
		
		if(!emailType.equals("mintaBayaranPampasan")){	
			bff.append("<br/>");
			bff.append("<br/>");
			bff.append("Sila <i>login</i> masuk ke www.etapp.gov.my untuk semakan dari pihak tuan/puan.");
		}
		
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, Terima Kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Emel ini dijana oleh Sistem eTaPP dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
	
	}
	
	/**
	 * @param jenisSuburusan
	 * @param emailTO
	 * @param emailType
	 * @param nofail
	 * @param nama_projek
	 * @param tarikh_permohonan
	 * @param nama_kementerian
	 */
	public void hantarEmel(String jenisSuburusan, String emailTO, String emailType, 
			String nofail,String nama_projek,String tarikh_permohonan,String nama_kementerian
			,String userId) throws Exception{
		
		String subject_emel = "MyeTaPP : PENGESAHAN PERMOHONAN ONLINE PENGAMBILAN TANAH";	
		
		//comment by aishah 
		Vector checkEmailPengguna = new Vector();
		checkEmailPengguna.clear();
		
		// check email (pengarah)
		MyInfoPPTData myInfo = new MyInfoPPTData();
		checkEmailPengguna = myInfo.checkEmail(userId);
		
		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		
		email.MULTIPLE_RECIEPIENT = new String[checkEmailPengguna.size()];
		if (checkEmailPengguna.size() != 0) {
			for(int i = 0; i < checkEmailPengguna.size();i++)
				{
					String emelPengguna = "";
					Hashtable ceP = (Hashtable) checkEmailPengguna.get(i);
					emelPengguna = (String) ceP.get("EMEL");					
					System.out.println(":::: emelPengguna ::::"+emelPengguna);
					email.MULTIPLE_RECIEPIENT[i] = emelPengguna;
				}
		}
		
		email.FROM = pro.getFrom();
//		email.SUBJECT = subject_emel;
//		email.MESSAGE =setMessageTable(emailType,nama_projek)+""+setParagraph(emailType,"",nofail,tarikh_permohonan,nama_kementerian,nama_projek);
		email.SUBJECT = subject_emel;
		email.MESSAGE = setMessageTable(emailType,nama_projek)+""+setParagraph("",nofail,tarikh_permohonan,nama_kementerian,nama_projek);	
		//email.RECIEPIENT = emelPengguna;
		/*email.TO_CC = new String[1];		
		email.TO_CC[0] = "nurul_aishah@si-protech.com.my";*/
		email.sendEmail();
	}
//	public void hantarEmel(String jenisSuburusan, String emailTO, String emailType, 
//			String nofail,String nama_projek,String tarikh_permohonan,String nama_kementerian
//			,String userId) throws Exception{
//		String subject_emel = "MyeTaPP : PENGESAHAN PERMOHONAN ONLINE PENGAMBILAN TANAH";		
//		//default sender email
//		String email_from = "etappsupport@jkptg.gov.my";
//		EmailProperty pro = EmailProperty.getInstance();
//		if(pro.getHost().equals("mail.hla-group.com")) 
//			email_from = "rosli@hla-group.com";
//		else if (pro.getHost().equals("smtp.gmail.com"))
//			email_from = "roslizakaria@gmail.com";
//		else{
//			//email_from = getEmel().checkEmail(userId);
//		}
//		
//		EkptgEmailSender email = EkptgEmailSender.getInstance();
//		email.FROM = email_from;
//		email.SUBJECT = subject_emel;
//		email.MESSAGE = setMessageTable(emailType,nama_projek)+""+setParagraph("",nofail,tarikh_permohonan,nama_kementerian,nama_projek);	
//		email.MULTIPLE_RECIEPIENT = new String[1];
//		email.MULTIPLE_RECIEPIENT[0] = "etappsupport@jkptg.gov.my";
//		
//		email.TO_CC = new String[4];
//		email.TO_CC[0]  = "noorazam@hla-group.com";
//		email.TO_CC[1]  = "faresh@hla-group.com";
//		email.TO_CC[2]  = "razman@hla-group.com";
//		email.TO_CC[3]  = "roslizakaria@gmail.com";
//
//		email.sendEmail();
//	
//	}
	
	public String setParagraph(String suburusan,String noRujukan,String tarikh_permohonan
			,String namaKementerian,String tajuk){
		StringBuffer bff = new StringBuffer();
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");		  
	    bff.append("Diminta Tuan/Puan menyemak/mengesahkan permohonan " +
	    		"<b>"+tajuk+"</b> daripada <b>"+namaKementerian+"</b> dan nombor rujukan adalah seperti berikut <b>"+noRujukan+"</b>.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> ke <a href=\"http://www.etapp.gov.my\" >www.etapp.gov.my</a> untuk semakan selanjutnya.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, terima kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("etappsupport@ekptg.gov.my");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Email ini adalah dijana oleh Sistem MyeTaPP dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
		
	}

	private IEmel getEmelSemak(){
		if(iEmel == null)
			iEmel = new EmelSemakanBean();
		return iEmel;

	}

	
}
