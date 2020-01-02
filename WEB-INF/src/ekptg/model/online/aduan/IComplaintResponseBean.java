package ekptg.model.online.aduan;

import java.util.Vector;

import ekptg.model.online.aduan.entity.ComplaintResponse;

public interface IComplaintResponseBean {
	public ComplaintResponse doResponse(ComplaintResponse response);
	public ComplaintResponse save(ComplaintResponse response);
	public ComplaintResponse update(ComplaintResponse response);
	public Vector<ComplaintResponse> getComplaintResponse(String idAduan);
	public ComplaintResponse getResponse(String idResponse);
	public Vector<ComplaintResponse> getMyResponsibility();
	public Vector<ComplaintResponse> getMyTaskList(String loginID,String responseStatus);
}
