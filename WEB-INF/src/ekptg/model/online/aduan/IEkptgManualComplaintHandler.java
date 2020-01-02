package ekptg.model.online.aduan;

import java.util.Vector;

public interface IEkptgManualComplaintHandler {
	public Complaint processComplaint(Complaint complaint);
	public Complaint processUpdateComplaint(Complaint complaint);
	public Vector<ComplaintType> getComplaintType();
	public Complaint getComplaint(String idComplaint);
	public Vector<Complaint> search(String noAduan,String statusAduan,String tarikhAduan);
}
