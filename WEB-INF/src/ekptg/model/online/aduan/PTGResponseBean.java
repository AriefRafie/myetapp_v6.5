package ekptg.model.online.aduan;

import java.util.Vector;

import ekptg.model.online.aduan.entity.ComplaintResponse;

public class PTGResponseBean extends ComplaintResponseFacade implements IComplaintResponseBean, IComplaintActivityHandler{
	private long complaintId = 0;
	private String loginId;
	@Override
	public ComplaintResponse doResponse(ComplaintResponse temp) {
		complaintId = temp.getComplaint().getId();
		loginId = temp.getIdKemaskini();
		ComplaintResponse response = getResponse(String.valueOf(temp.getId()));
		response.setId(temp.getId());
		response.setJawapan(temp.getJawapan());
		response.setIdKemaskini(loginId);
		response.setIdMasuk(loginId);
		response.setResponseStatus(ResponseStatus.SELESAI);
		update(response);
		new ComplaintActivityBean().setActivity(this);
		return response;
	}

	@Override
	public Vector<ComplaintResponse> getMyTaskList(String loginID,
			String responseStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getActivityType() {
		return "PTG_RESPONSE";
	}

	@Override
	public long getComplaintNo() {
		return complaintId;
	}

	@Override
	public String loginId() {
		return loginId;
	}

	@Override
	public String activityRemarks() {
		// TODO Auto-generated method stub
		return null;
	}

}
