package ekptg.model.htp.cukai;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.DbException;
import ekptg.model.htp.cukai.entity.BayaranCukai;
import ekptg.model.htp.cukai.entity.CukaiTemp;
import ekptg.model.htp.cukai.entity.CukaiUtama;
import ekptg.model.htp.entity.HakMilik;


public interface ICukai {
	
	public HakMilik getCukai(String idCukai);
	public boolean isFailCukaiSelesai(String idNegeri);
	//public String simpanBayaran(HttpSession session,String idPeringkatbayaran) throws Exception	;
	public Vector getSenaraiBaucer(String idNegeri, String idPeringkatBayaran, String peringkatBayaran)throws Exception ;		
	public Vector getSenaraiBaucer(String idNegeri, String idPeringkatBayaran, String peringkatBayaran,String tahun)throws Exception ;		
	public Vector<Hashtable<String, String>> getListDaerah(String idPeringkatBayaran) throws Exception ;
	public Vector<Hashtable<String, Comparable>> getListDaerah(String idPeringkatBayaran,String tahun) throws Exception ;
	public Hashtable getPermohonanInfo(String idPermohonan)throws Exception ;
	public Vector<Hashtable<String, String>> senaraiPenyataTerperinci(String idNegeri,String idDaerah, String idMukim)throws Exception ;
//08/02/1012
//	public Vector<Hashtable<String, String>> senaraiPenyataTerperinci(String idNegeri,String idDaerah, String idMukim, String tahun)throws Exception ;
	public void cukaiTerperinciHapus(String idCukaiTerperinci) ;
	public CukaiUtama getCukaiUtama(String idPeringkatBayaran) throws Exception ;
	public Vector<Hashtable<String, String>> CukaiSenaraiKemaskiniFail(String idNegeri,String idDaerah, String idMukim,String tahun)throws Exception ;

	public HakMilik getHakmilikByMukimNohakmilikNolot(String idMukim,String idHakmilik,String noHakmilik,String idLot,String noLot);

	public Vector getSenaraiCukaiUtkKemaskini(int idHakmilikCukai, String noHakmilik
			, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim)throws Exception;
// 09/02/2012
//	public Vector getSenaraiHakmilikCukai(String noHakmilik, Long idJenisHakmilik,
// 			Long idNegeri, Long idDaerah, Long idMukim, String tahun)throws Exception ;
	public Hashtable getHakmilikCukaiTerperinci(String idCukaiTemp)throws Exception ;
	public HakMilik getCukaiHakmilik(String idCukaiTemp);
//	public String kemaskiniCukai(Hashtable data) throws Exception ;
	public void kemaskiniHakmilikCukaiTemp(CukaiTemp data) throws SQLException, DbException;
	public Vector salinCukai(String idnegeri,String iddaerah,String idmukim,String socTahun);
// 08/02/2012	
//	public Vector<Hashtable<String, String>> CukaiSenaraiHakmilik(String idNegeri,String idDaerah, String idMukim,String tahun)throws Exception ;
//	public Vector<Hashtable<String, String>> cukaiSenaraiHakmilik(String idNegeri,String idDaerah
//			, String idMukim,String tahun,String noHakmilik)throws Exception ;
	public Vector<Hashtable<String, String>> senaraiHakmilik(String idNegeri
			,String idDaerah, String idMukim,String tahun)throws Exception ;
	public Vector<Hashtable<String, String>> senaraiHakmilik(String idNegeri
			,String idDaerah, String idMukim,String tahun,String noHakmilik)throws Exception ;
	public Vector senaraiHakmilik(String idNegeri
			,String idDaerah, String idMukim,String tahun,String noHakmilik, String idJenisHakmilik)
			throws Exception ;
	public Vector senaraiHakmilik(String idNegeri
			,String idDaerah, String idMukim,String tahun
			,String noHakmilik, String idJenisHakmilik,String noLot)
			throws Exception ;

	public HakMilik simpanCukaiHakmilikTemp(HakMilik hakmilik);
	public HakMilik salinanCukaiBaru(HakMilik hakmilik);
	public void simpanCukaiHakmilikTerperinci(HakMilik hakmilik) throws Exception;		
	public Vector<Hashtable<String, String>> senaraiPenyataTerperinciCukai(String idNegeri,String idDaerah
			, String idMukim, String tahun)throws Exception ;
// 08/02/2012
//	public Vector<Hashtable<String, String>> senaraiPenyataTerperinci(String idNegeri,String idDaerah
//			, String idMukim, String tahun, String noHakmilik)throws Exception ;
	public void cukaiTempHapus(String idCukaiTemp);
	public Vector<Hashtable<String, String>> senaraiPenyataCukaiTemp(String idNegeri,String idDaerah, String idMukim, String tahun)throws Exception;
	public ekptg.model.htp.entity.HakMilik getIdHakmilikCukai(HakMilik hakmilikLama)throws Exception ;	
	public Vector<Hashtable<String, Comparable>> getListNegeri(String idPeringkatbayaran) throws Exception;
	public Vector<Hashtable<String, Comparable>> getListNegeri(String idPeringkatBayaran,String tahun) throws Exception ;
	public BayaranCukai bayaranView(String idBayaranCukai) throws Exception ;
	public boolean isCukaiUtama(String idPeringkat,String tahun )throws Exception ;
	public boolean isCukaiUtama(String idPeringkat,String iddaerah,String tahun )throws Exception ;
	public boolean isCukaiPeringkatBayaran(String idPeringkat,String tahun )throws Exception ;
	public void hapusBayaranCukai(String pk) throws Exception ;
	public Vector<Hashtable<String, Comparable>> getSenaraiNegeriXPenyata(String tahun) throws Exception ;



}	
