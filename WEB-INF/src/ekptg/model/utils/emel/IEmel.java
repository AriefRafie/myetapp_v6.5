package ekptg.model.utils.emel;

<<<<<<< HEAD
//yati 13/8/2020
public interface IEmel {	
	
	public String setKandungan(String tajuk,String daripada); 
	public String setKandungan(String tajuk,String daripada,String noRujukan); 
	public String seTajuk(String namaSubmodul); 
=======

public interface IEmel {

	// class IEmel

	public String setKandungan(String tajuk,String daripada);
	public String setKandungan(String tajuk,String daripada,String noRujukan);
	public String seTajuk(String namaSubmodul);
>>>>>>> a047f985e3b41aa215eb09e873b864fdede17840
	public String checkEmail(String userId) throws Exception;
	public String setEmailSign(String noFail);
	public String setEmailSign(String noRujukan,String tajuk, String dari) ;
	//public String setEmailHantarPermohonan(String noFail,String tajuk) ;
}
