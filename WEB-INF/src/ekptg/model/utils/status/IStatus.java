package ekptg.model.utils.status;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.entities.Tblrujsuburusanstatusfail;

public interface IStatus {
	
	public void hapusSubUrusanStatusPermohonan(Tblrujsuburusanstatusfail rsus) throws Exception;
	public void kemaskiniStatus(Tblrujsuburusanstatusfail s) throws Exception;
//	public void kemaskiniStatusPermohonan(String idPermohonan, String suburusan, String langkah,String userId) 
//		throws Exception;
	
	public String getIdSuburusanStatusByLangkah(String langkah, String idsuburusan, String op) throws Exception;
	public String simpan(Hashtable<String,String> hash) throws Exception;
	public String simpanStatusAktif(Tblrujsuburusanstatusfail susf) throws Exception;
	public Tblrujsuburusanstatusfail getStatusFailPermohonanAktif(String idFail,String idPermohonan) throws Exception;	
//	public Tblrujsuburusanstatusfail kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail rsusf,Tblrujsuburusanstatusfail rsusfb) 
//		throws Exception;
	public Vector<Hashtable<String,String>> getInfoStatusPermohonan(String idUrusan,String idSubUrusan,String langkah) 
		throws Exception;
	public Vector<Hashtable<String,String>> getStatusPermohonanByIndividu(String idUrusan,String idUser,String langkah) 
			throws Exception;

	
}
