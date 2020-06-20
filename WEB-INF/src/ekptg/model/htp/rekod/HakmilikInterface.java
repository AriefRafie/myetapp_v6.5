package ekptg.model.htp.rekod;

import java.util.Vector;

import ekptg.model.htp.entity.HakMilik;


public interface HakmilikInterface {
	//public HakMilik getHakmilikUrusan(String idHakmilik);
	public Vector<HakMilik> transferRecord(String idPermohonan);
	public HakMilik getHakmilik(String idHakmilik);
	public HakMilik kemaskini(HakMilik hakmilik) throws Exception;
	public HakMilik kemaskiniHakmilikCatatan(String idHakmilik,String catatan);

	//public HakmilikAgensi kemaskiniHakmilikAgensi(HakmilikAgensi ha) throws Exception;
	//public void hapusHakmilikById(String idHakmilik) throws Exception ;
	//public Vector getMaklumatImejByIdHakmilikDist(String id) throws Exception;
	public HakMilik getMaklumatFailById(String id) throws Exception ;
	public HakMilik getWarta(String idHakmilik) throws Exception ;
//	public boolean isHakmilikAgensi(String idHakmilik) throws Exception;
//	public boolean isHakmilik(String idNegeri, String idDaerah,String idMukim
//			,String idJenisHakmilik, String noHakmilik,String bang, String ting, String petak) throws Exception;
//	public boolean isHakmilik(String idNegeri, String idDaerah,String idMukim
//			,String idJenisHakmilik, String noHakmilik,String bang, String ting, String petak
//			,String idLot, String noLot) throws Exception;
///	public String getSQLHakmilik(String idHakmilik);
//	public Vector getPaparMaklumatFailById(String idHakmilik) throws Exception;
//	public String getSQLHakmilikPermohonan(String idPermohonan);
	//ITanah
//	
//	public HakMilik kemaskini(HakMilik hakmilik) throws Exception;

}	
