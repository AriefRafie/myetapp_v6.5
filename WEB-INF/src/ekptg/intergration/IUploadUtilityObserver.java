package ekptg.intergration;

public interface IUploadUtilityObserver {
	public void deleteUploadFiles(IFileUploadUtilityProperty prop)throws Exception;
	public void storeUploadFile(IFileUploadUtilityProperty prop)throws Exception;
}
