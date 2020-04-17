package ekptg.model.htp;


public interface IHTPEmel {	
	
	public String checkEmail(String userId) throws Exception;
	public String setEmailSign(String noFail);
	public String setEmailSign(String noRujukan,String tajuk, String dari) ;
	//public String setEmailHantarPermohonan(String noFail,String tajuk) ;
	
		
}
