package ekptg.model.online.aduan;

import java.util.Vector;

public interface IComplaintSource {
	public ComplaintSource getComplaintSource(String code);
	public ComplaintSource getComplaintSource(long id);
	public Vector<ComplaintSource> getList();
}
