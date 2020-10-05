package ekptg.model.online.aduan;

import java.util.Vector;

import ekptg.model.entities.Tblrujnegeri;

public interface IEkptgManageComplaintHandler {
	public Vector<Complaint> getComplaintByRole(String role);
	public Complaint processComplaint(Complaint complaint);
	public Complaint getComplaint(String idComplaint);
	public Vector<Tblrujnegeri> getAvailableNegeri();
	public Vector<Complaint> search(String noAduan,String status,String tarikh);
	public Vector<Complaint> getComplaintTanah();
}
