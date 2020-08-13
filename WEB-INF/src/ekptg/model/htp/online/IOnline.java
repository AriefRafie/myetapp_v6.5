package ekptg.model.htp.online;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.entity.Bangunan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;

public interface IOnline {

	// class IOnline

	public HtpPermohonan simpanPermohonan(HtpPermohonan htpPermohonan)throws Exception;
	public HtpPermohonan simpanPermohonanOnline(HtpPermohonan htpPermohonan)throws Exception;
	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String ipPermohonan,String idHtpPermohonan)throws Exception;
	//Diguna
	public HtpPermohonan findPermohonan(String idPermohonan, String idHtpPermohonan) throws Exception;

	public Vector<HtpPermohonan> findFail(String carian, String noFail, String idNegeri);
	public Vector<HtpPermohonan> findFailOnline(String carian, String noFail, String idNegeri,String idKementerian);
	public Vector<HtpPermohonan> findFailOnlineAktif(String carian, String noFail, String idNegeri,String idKementerian);
	public Vector<HtpPermohonan> findFailOnlineUrusan(String carian
		,String noFail
		,String idNegeri
		,String idKementerian
		,String idUrusan);
	public HakmilikUrusan simpanHakmilik(HakmilikUrusan urusan);
	public HakmilikUrusan simpanHakmilikOnline(HakmilikUrusan urusan);
	public HakmilikUrusan updateHakmilik(HakmilikUrusan urusan);
	public void deleteHakmilik(String idHakmilikUrusan) throws Exception;
	public HakmilikUrusan findByHakmilikUrusanId(String hakmilikUrusanId);
	public Vector<HakmilikUrusan> getHakmilikList(String idPermohonan);

	public Pemohon simpanPenjual(Pemohon pemohon);
	public Pemohon updatePenjual(Pemohon pemohon);
	public Pemohon findByPermohonan (String idPermohonan);

	public Bangunan simpanBangunan(Bangunan bangunan);
	public Bangunan updateBangunan(Bangunan bangunan);
	public Bangunan findBangunan(String idBangunan);
	public Vector<Bangunan> findBangunanByPermohonan(String idPermohonan);
	public void deleteBangunan(String idBangunan);
	//Diguna
	public void kemaskiniStatusPermohonanAktif(Tblrujsuburusanstatusfail s);
	//Diguna
	public void simpanStatusPermohonan(Tblrujsuburusanstatusfail s);
	//Diguna
	public void kemaskiniFail(HtpPermohonan htpPermohonan,String ipPermohonan,String idHtpPermohonan)throws Exception;

	//public void kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru) throws Exception ;

	public Vector<Hashtable<String,String>> senaraiFailPemajak(String idPemohon) throws Exception;
	public String addMasuk(Hashtable<String,String> data)throws Exception;
	public Vector<Hashtable<String,String>> getDataDokumen(String idFail)	throws Exception;
	public Vector<Hashtable<String, String>> getLampiranByPermohonan(String id)throws Exception;
	public Vector<Hashtable<String,String>> getLampiranByDokumen(String id)throws Exception ;
	public void hapusLampiran(String idDokumen,String idLampiran) throws Exception;
	public boolean isHantar(Long idSubUrusan,Long idPermohonan
			,Long idFail,String langkah) throws Exception;
	public String kemaskiniDokumen(Hashtable<String,String> data)throws Exception;
	public boolean isHantarAktif(Long idSubUrusan,Long idPermohonan
			,Long idFail,String langkah) throws Exception;
}
