package ekptg.model.online.aduan;

import java.sql.Statement;

import lebah.db.Db;
import ekptg.engine.EmailSender;
import ekptg.engine.EmailProperty;
import ekptg.model.online.aduan.entity.ComplaintResponse;
import ekptg.model.online.aduan.entity.ComplaintTindakan;
import ekptg.model.utils.emel.EmailConfig;

public class ComplaintEmailNotificationX implements IComplaintEmailNotification {
	private IEkptgManageComplaintHandler manageComplaint;
	private Complaint complaint = null;
	private ComplaintResponse response;
	private IComplaintResponseBean complaintResponse = null;
	public ComplaintEmailNotificationX(){
		if(manageComplaint == null)
			manageComplaint = new EkptgManageComplaintHandler();
		if(complaintResponse == null)
			complaintResponse = new ComplaintResponseBean();
	}
	@Override
	public void notifyPengadu(String idAduan) throws Exception {
		if(!isNotifiedPengadu(idAduan)){
			sendNotifiedPengaduMessage();
			updateFlagNotifiedPengadu();
		}

	}
	
	public boolean isNotifiedPengadu(String idAduan){
		complaint = manageComplaint.getComplaint(idAduan);
		if(complaint.getFlagNotifiedPengadu().equals("Y")){
//		if(complaint.getFlagNotifiedPengadu() != null && complaint.getFlagNotifiedPengadu().equals("Y")){
			return true;
		}
		else{
			return false;
		}
		
	}
	private void updateFlagNotifiedPengadu(){
		Db db = null;
		try{
			db = new Db();
			Statement stat = db.getStatement();
			String sql ="UPDATE TBLONLINEEADUAN SET FLAG_NOTIFYPENGADU='Y' WHERE ID_EADUAN="+complaint.getId();
			stat.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	private void sendNotifiedPengaduMessage () throws Exception {
		StringBuffer msg = new StringBuffer();
		msg.append("<p>Salam sejahtera "+complaint.getNamaPengadu()+",</p>");
		msg.append("<p>Aduan anda bertarikh "+complaint.getTarikhAduan()+" telah kami semak dan didapati palsu.</p>");
		//msg.append("<p>Sekian, terima kasih.</p>");
		String tajuk ="ADUAN TANAH: " + complaint.getNoAduan()+ " (Status Aduan : "+complaint.getStatusPenyelesaian()+")";

		System.out.println(msg.toString());
		if(complaint.getEmailPengadu() != null && !complaint.getEmailPengadu().equals("")){
//			EmailSender email = EmailSender.getInstance();
//			EmailProperty prop = EmailProperty.getInstance();
			EmailConfig ec = new EmailConfig();
			ec.mail.TO_CC = new String[1];		
			ec.mail.TO_CC[0] = "roslizakariasip@gmail.com";

			ec.sendTo(complaint.getEmailPengadu(), tajuk, msg.toString());

//			email.FROM = prop.getAduanFrom();
//			email.SUBJECT = "Perkhidmatan Online eTaPP : Aduan Online No. "+complaint.getId();
//			email.RECIEPIENT = complaint.getEmailPengadu();
//			email.MESSAGE = msg.toString();
//			email.sendEmail();
		}
	}
	
	private void sendNotifiedSeksyenMessage() throws Exception{
		String emailSubject ="Perkhidmatan Online eTaPP : Agihan Tugasan Aduan Online No. "+complaint.getId();
		StringBuffer msg = new StringBuffer();
		msg.append("<p>Salam Sejahtera, <br> Aduan No. "+complaint.getId()+" telah diagihkan kepada anda. Sila login ke  <a href='http://www.etapp.gov.my'>www.etapp.gov.my</a> untuk mendapatkan perincian.</p>");
		msg.append("<p>Sekian, terima kasih.</p>");
		ComplaintTindakan tindakan = response.getTindakan();
		String groupEmail = tindakan.getGroupEmail();
		if(groupEmail != null && !groupEmail.equals("")){
			EmailSender email = EmailSender.getInstance();
			EmailProperty prop = EmailProperty.getInstance();
			email.FROM = prop.getAduanFrom();
			email.SUBJECT =emailSubject;
			email.RECIEPIENT = groupEmail;
			email.MESSAGE = msg.toString();
			email.sendEmail();
		}
		
	}
	@Override
	public void notifySeksyen(String idAduan, String idAduanResponse) throws Exception{
		complaint = manageComplaint.getComplaint(idAduan);
		response = complaintResponse.getResponse(idAduanResponse);
		if(response != null){
			sendNotifiedSeksyenMessage();
		}
		
	}

}
