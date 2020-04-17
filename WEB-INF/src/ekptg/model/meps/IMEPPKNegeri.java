package ekptg.model.meps;

import java.util.Vector;

public interface IMEPPKNegeri {
	
	public Vector getJumlahHarta(String socTahun,String socTahunHingga,String idNegeri) throws Exception;		 		 
	public String generateXMLJumlahHarta(String nama_laporan);


}	
