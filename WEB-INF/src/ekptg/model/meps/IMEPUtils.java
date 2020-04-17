package ekptg.model.meps;

import java.util.Vector;

public interface IMEPUtils{
	
	public String SelectTahun(Vector tahun,String selectName,String selectedValue,String disability,String jsFunction) 
		throws Exception;
	public String generateXML(String sql, String namaLaporan);
	public String generateXMLFail(String sql, String namaLaporan);
	public String generateXMLFailLine(String sql, String namaLaporan);


}	
