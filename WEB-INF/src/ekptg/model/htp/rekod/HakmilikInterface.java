package ekptg.model.htp.rekod;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikAgensi;


public interface HakmilikInterface {
	//public HakMilik getHakmilikUrusan(String idHakmilik);
	public Hashtable<String,String> getHakmilikUrusan(String idHakmilik,boolean pemilik);
	public Vector<HakMilik> transferRecord(String idPermohonan);
	public HakMilik getHakmilik(String idHakmilik);
	public HakMilik kemaskiniHakmilikCatatan(String idHakmilik,String catatan);
	public HakmilikAgensi kemaskiniHakmilikAgensi(HakmilikAgensi ha) throws Exception;
	public void kemaskiniHakmilikTambahSambungan(Hashtable data) throws Exception;
	public String getKodJenisHakmilik(String idJenisHakmilik) throws Exception;
	public Vector<Hashtable<String,String>> getSenaraiHakmilikSambungan(String noHakmilikAsal,String idNegeri
			,String idDaerah,String idMukim) throws Exception;
	public void hapusHakmilikById(String idHakmilik) throws Exception ;
	public Vector getMaklumatImejByIdHakmilikDist(String id) throws Exception;
	public HakMilik getMaklumatFailById(String id) throws Exception ;
	public HakMilik getWarta(String idHakmilik) throws Exception ;
	public boolean isHakmilikAgensi(String idHakmilik) throws Exception;
	public boolean isHakmilik(String idNegeri, String idDaerah,String idMukim
			,String idJenisHakmilik, String noHakmilik) throws Exception;
	public boolean isHakmilik(String idNegeri, String idDaerah,String idMukim
			,String idJenisHakmilik, String noHakmilik,String bang, String ting, String petak) throws Exception;
	public boolean isHakmilik(String idNegeri, String idDaerah,String idMukim
			,String idJenisHakmilik, String noHakmilik,String bang, String ting, String petak
			,String idLot, String noLot) throws Exception;
	public boolean isWarta(String idNegeri, String idDaerah,String idMukim
			,String noWarta, String idLot, String noLot) throws Exception;
	public String getSQLHakmilik(String idHakmilik);
	public Vector<Hashtable<String,String>> getPaparMaklumatFailById(String idHakmilik) throws Exception;
	public String getSQLHakmilikPermohonan(String idPermohonan);

}	
