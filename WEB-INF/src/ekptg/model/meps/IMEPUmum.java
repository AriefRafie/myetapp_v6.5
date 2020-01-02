package ekptg.model.meps;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

public interface IMEPUmum {
	
	public Vector getJumlahPermohonan(String ID_NEGERI, String ID_PEJABATJKPTG, String socTahun,String socTahunHingga) throws Exception;	 		 
	
	public Vector<Hashtable<String, Comparable>> getJumlahPermohonan(String ID_NEGERI, String ID_PEJABATJKPTG,
			String socTahun,String socTahunHingga,String idSubUrusan) throws Exception;		
	
	public Vector<Hashtable<String, Comparable>> getJumlahLaporanFail(
			String Tahun,String TahunHingga,String negeri,String daerah,String idSubUrusan) throws Exception;	
	
	public Vector<Hashtable<String, Comparable>> getJumlahPermohonan(String ID_NEGERI, String ID_PEJABATJKPTG, 
			String socTahun,String socTahunHingga,String socBulan,String socBulanHingga
			,String idSubUrusan) throws Exception;
	
	public Vector<Hashtable<String, Comparable>> getJumlahPermohonanNegeriAgama(String ID_NEGERI, String ID_PEJABATJKPTG, 
			String socTahun,String socTahunHingga
			,String idSubUrusan) throws Exception;
	
	public Vector<Hashtable<String, Comparable>> getJumlahPermohonanNegeriAgama(String ID_NEGERI, String ID_PEJABATJKPTG, 
			String socTahun,String socTahunHingga,String socBulan,String socBulanHingga
			,String idSubUrusan) throws Exception;
	
	public List listTableRujukanV3(HttpSession session, String tableRujukan, String id_filter,String USER_ID)throws Exception; 
	
	public List listPejabatJKPTG(HttpSession session, String ID_SEKSYEN, String ID_NEGERI)throws Exception;
	
	public String getNamaPejabat(HttpSession session, String iD_PEJABATJKPTG)throws Exception;
	
	public String getSQL();

	

}	
