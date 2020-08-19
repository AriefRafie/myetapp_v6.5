package ekptg.model.utils.emel;

public interface IEmel {

	// class IEmel

	public String setKandungan(String tajuk,String daripada);
	public String setKandungan(String tajuk,String daripada,String noRujukan);
	public String seTajuk(String namaSubmodul);
	public String checkEmail(String userId) throws Exception;
	public String setEmailSign(String noFail);
	public String setEmailSign(String noRujukan,String tajuk, String dari) ;
	//public String setEmailHantarPermohonan(String noFail,String tajuk) ;
}
