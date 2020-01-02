package ekptg.model.htp.utiliti;

public interface IUtilHTMLPilihan {	
	public String Pilihan(String nama) throws Exception;
	public String Pilihan(String nama,String fungsiJS) throws Exception;
	public String Pilihan(String nama,String nilaiPilih,String disability) throws Exception;
	public String Pilihan(String nama,String nilaiPilih,String disability,String fungsiJS) throws Exception;
	
}
