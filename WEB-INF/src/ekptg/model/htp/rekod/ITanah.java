package ekptg.model.htp.rekod;

import java.util.Hashtable;
import java.util.Vector;

public interface ITanah{
	
//	public Vector getPaparMaklumatFailById(String idHakmilik) throws Exception;
	public boolean isHakmilik(String idNegeri, String idDaerah,String idMukim
		,String idJenisHakmilik
		,String noHakmilik) throws Exception;
	public boolean isWarta(String idNegeri, String idDaerah,String idMukim
		,String noWarta
		,String idLot, String noLot) throws Exception;
	public Hashtable<String,String> getHakmilikUrusan(String idHakmilik,boolean pemilik);
	public String getLuas(String jenisLuas
		,String luas,String luas2,String luas3
		,String luas4,String luas5,String luas6);
	public String getKodJenisHakmilik(String idJenisHakmilik) throws Exception;
	public String getSQLHakmilik(String idHakmilik);
	public String getSQLHakmilikPermohonan(String idPermohonan);
	public Vector<Hashtable<String,String>> getPaparMaklumatFailById(String idHakmilik) throws Exception;
	public Vector getSenaraiHakmilikSambungan(String noHakmilikAsal
		,String idNegeri,String idDaerah,String idMukim) throws Exception;
		public void kemaskiniHakmilikTambahSambungan(Hashtable data) throws Exception;

}	
