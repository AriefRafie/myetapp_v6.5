package ekptg.view.htp.utiliti;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import ekptg.engine.EmailSender;
import ekptg.engine.EmailProperty;
import ekptg.model.htp.HTPEmelPermohonanBean;
import ekptg.model.htp.HTPEmelSemakBean;
import ekptg.model.htp.IHTPEmel;
import ekptg.model.htp.IHtp;

public class SendMail extends AjaxModule {
		private IHtp iHTP = null;  
	private ekptg.model.htp.IHTPEmel iHTPEmel = null;
	@Override
	public String doAction() throws Exception {
	//try{	
		HttpSession ses = request.getSession();
		String command = getParam("command");
		String action = getParam("action");

		EmailProperty pro = EmailProperty.getInstance();
		String EMAIL_HOST= pro.getHost();
		EmailSender email = EmailSender.getInstance();
		if(EMAIL_HOST.equals("mail.hla-group.com")){
			email.FROM = "razman@hla-group.com";
		}else{
			email.FROM = "etapp_webmaster@ekptg.gov.my";
		}
		email.SUBJECT="SEMAKAN PERMOHONAN PERAKUAN PEMBELIAN";
		email.MESSAGE = "Cubaan Hantar emel";
		email.MULTIPLE_RECIEPIENT = new String[1];
		email.MULTIPLE_RECIEPIENT[0] = "razman@hla-group.com";
		email.TO_CC = new String[1];
		email.TO_CC[0]  = "rosli@hla-group.com";
		//email.sendEmail();		
		return "";
				
	}
	
	private IHTPEmel getHTPEmel(){
		if(iHTPEmel == null)
			iHTPEmel = new HTPEmelPermohonanBean();
		return iHTPEmel;
	}

	private IHTPEmel getHTPEmelSemak(){
		if(iHTPEmel == null)
			iHTPEmel = new HTPEmelSemakBean();
		return iHTPEmel;
	}	
}


