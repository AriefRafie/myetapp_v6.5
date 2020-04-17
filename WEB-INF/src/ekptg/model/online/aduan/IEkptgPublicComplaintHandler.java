package ekptg.model.online.aduan;

import java.util.Vector;

public interface IEkptgPublicComplaintHandler {
	public Complaint processComplaint(Complaint complaint)throws Exception;
	public Vector<ComplaintType> getComplaintType();
	public Complaint getComplaint(String idComplaint);
	public Vector<Complaint> getComplaintByUserId(String userId);
}
