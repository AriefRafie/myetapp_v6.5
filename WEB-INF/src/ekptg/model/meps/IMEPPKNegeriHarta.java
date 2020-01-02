package ekptg.model.meps;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

public interface IMEPPKNegeriHarta {
	
	public Vector getJumlahHarta(String ID_NEGERI, String ID_PEJABATJKPTG, String socTahun,String socTahunHingga,String Suburusan) throws Exception;		 		 
	//public String generateXMLJumlahHarta(String nama_laporan);
	
	public Vector getJumlahHartaSelesai(String ID_NEGERI, String ID_PEJABATJKPTG, String socTahun,String socTahunHingga, String idSuburusan)throws Exception;
	
	//Hamidah add
	public List listTableRujukanV3(HttpSession session, String tableRujukan, String id_filter,String USER_ID)throws Exception; 
		
	
	public List listPejabatJKPTG(HttpSession session, String ID_SEKSYEN, String ID_NEGERI)throws Exception;

	public String getNamaPejabat(HttpSession session, String iD_PEJABATJKPTG)throws Exception;
	
}
	
	