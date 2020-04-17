package ekptg.model.online.aduan;

public interface IComplaintEmailNotification {
	public void notifyPengadu(String idAduan);
	public void notifySeksyen(String idAduan,String idAduanResponse);
}
