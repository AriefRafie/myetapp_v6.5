package ekptg.model.htp.permohonan;

import java.util.Hashtable;
import java.util.Vector;

public interface IPermohonanPerizapan {	
	
	public  Vector<Hashtable<String, String>> TerimaPohongetList(String idUser,String nofail,String tajukfail
		,String id_kementerian,String id_agensi
		,String id_negeri,String id_daerah,String id_mukim
		,String id_urusan
		,String tarikhSurat,String tarikhBukaFail,String tarikhHantar,String tarikhKeputusan) throws Exception;
	public  Vector<Hashtable<String, String>> TerimaPohongetList(
			String idUser,String nofail,String tajukfail,String id_kementerian,String id_agensi,
			String id_negeri,String id_daerah,String id_mukim,String id_urusan,String tarikhBukaFail) throws Exception;
	
	public void kemaskiniMaklumatAsasTanah(Hashtable<String, String> data) throws Exception;			
	
	public String kemaskiniMaklumatTanah(Hashtable<String, String> data) throws Exception;			
	
	public void simpanMaklumatAsasTanah(Hashtable<String, String> data) throws Exception;	
	
	public String simpanMaklumatTanah(Hashtable<String, String> data) throws Exception;	
	
	public  Vector<Hashtable<String, String>> TerimaPohongetList(
			String idUser,String nofail,String tajukfail,String id_kementerian,String id_agensi,
			String id_negeri,String id_daerah,String id_mukim,String id_urusan,String tarikhBukaFail,boolean display) throws Exception;

	public Hashtable<String, String> getMaklumatAsasTanahKemaskini(String idhakmilikurusan) throws Exception ;
	
	public void hapuskeputusan(String id_);
	
	public Vector<Hashtable<String, String>> senaraiPermohonanOnline(
			String userId, String string, String string2, String string3,
			String string4, String string5, String string6, String string7,
			String string8, String tarikhBukaFail) throws Exception;

}
