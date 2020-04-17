package ekptg.model.ppk.perintah;

public interface IPerintah {
	public boolean isJenisPerintah(String noFail) throws Exception;
	public boolean isPerintahBatalWasiatPermohonan(String noFail) throws Exception;
	public boolean isPerintahBatalWasiatPerbicaraan(String noFail) throws Exception;
	public boolean isPindahWasiat(String noFail) throws Exception;

}
