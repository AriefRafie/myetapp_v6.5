package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.integrasi.FrmModelMyInfoPermohonanTertangguh;

@SuppressWarnings("serial")
public class FrmViewMyInfoPermohonanTertangguh extends AjaxBasedModule {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String userName = "";
	
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "app/integrasi/frmMyInfoPermohonanTertangguh.jsp";
		
		String action = getParam("action2");

        HttpSession session = this.request.getSession();
        
        FrmModelMyInfoPermohonanTertangguh modelPermohonan = new FrmModelMyInfoPermohonanTertangguh();
        
        userName = (String) session.getAttribute("_portal_username");
        
    	Vector vList = new Vector();

    	if ("sendEmail".equalsIgnoreCase(action)) {
    		String formType = getParam("formType");
    		String emailFrom = "", emailReceipient = "", emailSubject = "", emailMessage = "", fileName = "";
    		if ("borangj".equalsIgnoreCase(formType)) {
    			fileName = getParam("CHK_BORANGJ");
    			emailFrom = "admin_etapp@kptg.gov.my";
    			emailReceipient = "zulkhaimi@hla-group.com";
    			emailSubject = "Permohonan Tertangguh - Borang J";
    			emailMessage = "Permohonan tertangguh bagi borang J<br />" + fileName;
    		} else if ("HTAAH".equalsIgnoreCase(formType)) {
    			fileName = getParam("CHK_HTAAH");
    			emailFrom = "admin_etapp@kptg.gov.my";
    			emailReceipient = "zulkhaimi@hla-group.com";
    			emailSubject = "Permohonan Tertangguh - Nilaian Harta Tak Alih (Ada Hakmilik)";
    			emailMessage = "Permohonan tertangguh bagi Nilaian Harta Tak Alih (Ada Hakmilik)<br />" + fileName;
    		} else if ("HTATH".equalsIgnoreCase(formType)) {
    			fileName = getParam("CHK_HTATH");
    			emailFrom = "admin_etapp@kptg.gov.my";
    			emailReceipient = "zulkhaimi@hla-group.com";
    			emailSubject = "Permohonan Tertangguh - Nilaian Harta Tak Alih (Tiada Hakmilik)";
    			emailMessage = "Permohonan tertangguh bagi Nilaian Harta Tak Alih (Tiada Hakmilik)<br />" + fileName;
    		} else if ("HAK".equalsIgnoreCase(formType)) {
    			fileName = getParam("CHK_HAL");
    			emailFrom = "admin_etapp@kptg.gov.my";
    			emailReceipient = "zulkhaimi@hla-group.com";
    			emailSubject = "Permohonan Tertangguh - Nilaian Harta Alih Kenderaan";
    			emailMessage = "Permohonan tertangguh bagi Nilaian Harta Alih Kenderaan<br />" + fileName;
    		} else if ("boranglampirana1".equalsIgnoreCase(formType)) {
    			fileName = getParam("CHK_BORANGLAMPIRANA1");
    			emailFrom = "admin_etapp@kptg.gov.my";
    			emailReceipient = "zulkhaimi@hla-group.com";
    			emailSubject = "Permohonan Tertangguh - Borang Lampiran A1";
    			emailMessage = "Permohonan tertangguh bagi borang Lampiran A1<br />" + fileName;
    		}
    		System.out.println(emailMessage);
//			EkptgEmailSender email = EkptgEmailSender.getInstance();
//			email.FROM = emailFrom;
//			email.RECIEPIENT = emailReceipient;
//			email.SUBJECT = emailSubject;
//			email.MESSAGE = emailMessage;
//			email.sendEmail();
    	} else {
	        vList = modelPermohonan.searchBorangJ();
	        context.put("ListBorangJ", vList);
	        vList = modelPermohonan.searchNilaianHTA(0, userName);
	        context.put("ListNilaianHTAAH", vList);
	        vList = modelPermohonan.searchNilaianHTA(1, userName);
	        context.put("ListNilaianHTATH", vList);
	        vList = modelPermohonan.searchNilaianHAK(userName);
	        context.put("ListNilaianHAK", vList);
	        vList = modelPermohonan.searchBorangLampiranA1();
    	}
        context.put("ListBorangLampiranA1", vList);
        
    	return vm;
	}
}