package ekptg.model.htp;

public interface IUtilHTML {
	public String SelectPegawaiMengikutSeksyen(String idSeksyen,
			String idPegawai, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception;
	public String SelectStatusPermohonan(String selectName) throws Exception;
	public String SelectStatusPermohonan(String selectName,String jsFunction,String idSubUrusans) 
		throws Exception;
	public String SelectStatusPermohonan(String selectName, Long selectedValue,
		String disability,String idSubUrusans) throws Exception;
	
}
