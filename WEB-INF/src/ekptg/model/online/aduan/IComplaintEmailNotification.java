package ekptg.model.online.aduan;

public interface IComplaintEmailNotification {
	public void notifyPengadu(String idAduan) throws Exception ;
	public void notifySeksyen(String idAduan,String idAduanResponse) throws Exception;
}
