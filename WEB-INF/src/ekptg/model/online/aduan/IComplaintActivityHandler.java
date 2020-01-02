package ekptg.model.online.aduan;

public interface IComplaintActivityHandler {
	public String getActivityType();
	public long getComplaintNo();
	public String loginId();
	public String activityRemarks();
}
