package ekptg.model.htp;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.entities.Tblrujsuburusanstatus;
import ekptg.model.entities.Tblrujsuburusanstatusfail;

public interface IHTPStatus {	

//	public boolean isSubUrusanStatusPermohonanCukai(Tblrujsuburusanstatusfail rsus, String tahun) throws Exception;
	public boolean isSubUrusanStatusPermohonan(Tblrujsuburusanstatusfail rsus, String param) throws Exception;
	
	public Hashtable<String, String> getInfoStatusPermohonan(String idPermohonan,String langkah) throws Exception;		
	public Hashtable<String, String> getInfoStatusPermohonanFail(String idFail,String idPermohonan,String langkah) 
		throws Exception;	
	
	public String getIdStatusSah(String kod) throws Exception;
	public String getIdSuburusanStatusByLangkah(String langkah, String idsuburusan, String op) throws Exception;
	public String kemaskiniSimpanStatusAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru) 
		throws Exception;
	public String kemaskiniSimpanStatusAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru,String strTarikh) 
		throws Exception;
	
	public Tblrujsuburusanstatus getDetails(String id);
	public Tblrujsuburusanstatus getStatusPermohonanAktif(String idFail,String idPermohonan) throws Exception;	
	public Tblrujsuburusanstatusfail getStatusFailPermohonanAktif(String idFail,String idPermohonan) throws Exception;	
	public Tblrujsuburusanstatusfail kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail s
		,Tblrujsuburusanstatusfail sBaru
		,String Tahun) throws Exception;
	
	public Vector<Hashtable<String,String>> getInfoStatusPermohonan(String idUrusan,String idSubUrusan
		,String langkah) throws Exception;
	public Vector<Hashtable<String,String>> getInfoStatusPermohonan(String idUrusan,String idSubUrusan
		,String langkah
		,String negeri) throws Exception;
	
	public void hapusSubUrusanStatusPermohonan(Tblrujsuburusanstatusfail rsus) throws Exception;

	//	public void kemaskiniSimpanStatusCukai(Tblrujsuburusanstatusfail s
//		,Tblrujsuburusanstatusfail sBaru, String Tahun) throws Exception;
	public void kemaskiniStatus(Tblrujsuburusanstatusfail s) throws Exception;
	public void kemaskiniStatus(Tblrujsuburusanstatusfail s, String strTarikh) throws Exception;
	public void kemaskiniStatusPermohonan(String idPermohonan, String suburusan, String langkah,String userId) 
		throws Exception;
//	public void simpanKemaskiniStatus(Tblrujsuburusanstatusfail s,String setIdStatus) throws Exception;
	public void simpanKemaskiniStatus(Tblrujsuburusanstatusfail s,Long setIdStatus) throws Exception;
	// Untuk kegunaan semua Modul HTP 
	public void statusChangeActionL1(String idFail,String idPermohonan,String idSuburusan,String user);
	public void semakStatusSemasa(org.apache.velocity.VelocityContext context
			,String akses
			,String idPermohonan
			,String idFail
			,String portal_role) throws Exception;
	
}
