package ekptg.model.htp.utiliti.fail;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.htp.entity.HtpPermohonan;

public interface IHTPFail {	
	
	public  Vector<Hashtable<String, String>> getSenaraiFail(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhBukaFail)throws Exception;
	public  Vector<Hashtable<String, String>> getSenaraiFail(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhBukaFail,boolean display)throws Exception;
	public  Vector<Hashtable<String, String>> getSenaraiFail(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhTerima,String tarikhBukaFail
			,String status, String pemohon, boolean isSearch)
			throws Exception;
	public  Vector<Hashtable<String, String>> getSenaraiFailMengikutUrusans(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhTerima,String tarikhBukaFail
			,String status,boolean isSearch)
			throws Exception;
	public void XkemaskiniMaklumatAsasTanah(Hashtable<String, String> data) throws Exception;			
	public String XkemaskiniMaklumatTanah(Hashtable<String, String> data) throws Exception;			
	public void XsimpanMaklumatAsasTanah(Hashtable<String, String> data) throws Exception;	
	public String XsimpanMaklumatTanah(Hashtable<String, String> data) throws Exception;		
	public Hashtable<String, String> XgetMaklumatAsasTanahKemaskini(String idhakmilikurusan)throws Exception;
	public Hashtable<String,String> getMaklumatPermohonan(String idFail) throws Exception;
	public Vector<Hashtable<String, String>> getMaklumatPermohonans(String idFail) throws Exception;
	public HtpPermohonan findPermohonan(String idFail, String idPermohonan,String idHtpPermohonan) 
		throws Exception;

	public  Vector<Hashtable<String, String>> getSenaraiFailMengikutUrusans(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhTerima,String tarikhBukaFail
			,String status,boolean isSearch, String nofaillain)throws Exception ;

}
