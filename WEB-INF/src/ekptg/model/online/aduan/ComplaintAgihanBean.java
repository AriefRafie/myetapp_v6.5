package ekptg.model.online.aduan;

import java.sql.SQLException;
import java.util.Vector;

import ekptg.model.online.aduan.entity.ComplaintResponse;
import ekptg.model.online.aduan.setup.ComplaintTindakanBean;
import ekptg.model.online.aduan.setup.IComplaintTindakanBean;

public class ComplaintAgihanBean extends ComplaintResponseFacade implements IComplaintResponseBean, IComplaintActivityHandler {

	private Long idAduan;
	private String idMasuk;
	private IEkptgManageComplaintHandler complaintHandler =null;
	private IComplaintTindakanBean complaintTindakan = null;
	private IComplaintEmailNotification  emailNotification;
	public ComplaintAgihanBean(){
		if(complaintHandler == null)
			complaintHandler = new EkptgManageComplaintHandler();
		if(complaintTindakan == null)
			complaintTindakan = new ComplaintTindakanBean();
		if(emailNotification == null)
			emailNotification = new ComplaintEmailNotification();
	}
	@Override
	public ComplaintResponse doResponse(ComplaintResponse response){
		idAduan = response.getComplaint().getId();
		idMasuk = response.getIdMasuk();
		response.setResponseStatus(ResponseStatus.BARU);
		response = save(response);
		Complaint complaint = complaintHandler.getComplaint(String.valueOf(response.getComplaint().getId()));
		complaint.setIdPegawai(response.getComplaint().getIdPegawai());
		complaint.setLoginName(response.getComplaint().getLoginName());
		complaintHandler.processComplaint(response.getComplaint());
		new ComplaintActivityBean().setActivity(this);
		try{
			emailNotification.notifySeksyen(String.valueOf(idAduan), String.valueOf(response.getId()));		
		}catch (Exception e) {
			System.out.println(e.getMessage());
			//throw se.getMessage();
    		//throw new Exception("Rollback error:"+se.getMessage());
		}
	
		return response;
	
	}
	

	@Override
	public String getActivityType() {
			return "SEMAKKAN_SEKSYEN";
	}

	@Override
	public long getComplaintNo() {
		return idAduan;
	}

	@Override
	public String loginId() {
		return idMasuk;
	}
	@Override
	public Vector<ComplaintResponse> getMyTaskList(String loginID,
			String responseStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String activityRemarks() {
		return "";
	}

}
