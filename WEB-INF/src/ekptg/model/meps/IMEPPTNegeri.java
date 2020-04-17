package ekptg.model.meps;

import java.util.Vector;

import ekptg.model.entities.Tblrujsuburusan;


public interface IMEPPTNegeri {
	
	public Vector getJumlahPermohonan(String socTahun,String socBulan) throws Exception;
	public Vector getJumlahPermohonan(String socTahun,String socTahunHingga
			,String idSubUrusan,String idNegeri)throws Exception;	 		 
	public Vector getJumlahPermohonan2(String socTahun,String socBulan) throws Exception;
	public String generateXML(String nama_laporan);
	public String generateXMLPermohonan2(String nama_laporan);				
	public Tblrujsuburusan getSubUrusan(String idSuburusan) throws Exception;


}	
