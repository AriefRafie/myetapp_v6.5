package ekptg.model.htp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import ekptg.intergration.entity.BorangK;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;

public interface IHtp {
	public Vector<Hashtable<String, String>> carianFail(String noFail,String tajukFail,String tarikhDaftar,String tarikhTerima
		,String namaPemohon
		,String idNegeri
		,String idKementerian,String idAgensi
		,String jenisHakmilik,String noHakmilik,String jenisLot,String noLot
		,String subUrusan
		,String idStatus) throws Exception;
	public Vector<HtpPermohonan> findFail(String idUrusan
		,String noFail
		,String tajukFail
		,String pemohon
		,String idNegeri
		,String idDaerah
		,String idMukim
		,String idKementerian
		,String idAgensi
		,String tarikhPohon
		,String tarikhBukaFail
		,String tarikhBukaFailHingga);
	public Vector<Hashtable<String, String>> carianFail(String noFail, String tajukFail,String tarikhTerima, String namaPemohon
		,String idNegeri,String idKementerian,String idAgensi) throws Exception ;
	public Vector<Hashtable<String, String>> carianFail(String noFail, String tajukFail,String tarikhTerima, String namaPemohon
		,String idNegeri,String idKementerian,String idAgensi,
		String idUrusan, String langkah) throws Exception;
	public void hapusBayaran(String idBayaran);
	public void hapusNotis(String idNotis);
	public BorangK carianFailPPT(String noFail, String idHakmilik) throws Exception ;
	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String ipPermohonan,String idHtpPermohonan)
		throws Exception;
	public HtpPermohonan kemaskiniPermohonanKutipan(HtpPermohonan htpPermohonan,String ipPermohonan
		,String idHtpPermohonan) throws Exception;
	public HtpPermohonan findPermohonan(String idPermohonan, String idHtpPermohonan) throws Exception;
	public HtpPermohonan findPermohonan(String idFail,String idPermohonan, String idHtpPermohonan) throws Exception;
	public HtpPermohonan findPermohonanKutipan(String idPermohonan, String idHtpPermohonan) throws Exception;
	public Hashtable<String,String> getInfoSelesaiPermohonan(String idPermohonan,String langkah)throws Exception ;
	public Hashtable<String,String> getInfoSelesaiPermohonan(String idPermohonan) throws Exception ;
	public Hashtable<String,String> getInfoTamatSelesaiPermohonan(String idPermohonan)throws Exception ;
	public void hapusHakmilik(String idHakmilikUrusan) throws Exception ;
	public void kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru)
		throws Exception ;
	public void kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru
		,String strTarikh) throws Exception ;
	public void kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail s,String strTarikh) throws Exception ;
	public String SelectJenisHakmilik(String selectName,Long selectedValue, String disability) throws Exception;
	public String SelectJenisHakmilik(String selectName,Long selectedValue, String disability, String jsFunction)
		throws Exception;
	public String getDaerahMengikutBandar(String idBandar) throws Exception ;
	public Pemohon carianPemohonMengikutPermohonan(String idPermohonan);
	public Vector<HtpPermohonan> getSenaraiFailMengikutUrusanDanPengguna(String idUrusan
		,String carian
		,String noFail
		,String idNegeri
		,String user) throws Exception;
	public Vector<HtpPermohonan> getSenaraiFailMengikutUrusanDanPengguna(String idUrusan
		,String carian
		,String noFail
		,String idNegeri) throws Exception;
	public HtpPermohonan simpanPermohonan(HtpPermohonan htpPermohonan)throws Exception;
	public void simpanStatusPermohonan(Tblrujsuburusanstatusfail s,Connection conn) throws Exception;
	public Tblrujsuburusanstatusfail getPfdFailValues(String idSubUrusan,Long idPermohonan,Long idFail,Long idMasuk)
		throws Exception ;
	public String getDaerahDefaultMengikutNegeri(String idNegeri) throws Exception ;
	public String getAgensiDefault(String kodAgensi) throws Exception ;
	public String getAgensiDefaultMengikutKementerian(String idKementerian) throws Exception ;
	public String getErrorHTML(String msg) throws Exception,SQLException;
	public void hapusTindakan(String idsusulan);
}
