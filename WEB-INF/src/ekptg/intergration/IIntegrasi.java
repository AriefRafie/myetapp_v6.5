package ekptg.intergration;

import java.util.Vector;

import ekptg.model.integrasi.entities.MaklumaTanahGIS;


public interface IIntegrasi {
	
	public void simpan(String noFail, int statusTanah) throws Exception ;
	public void simpan(String noFail,int statusTanah,String idHakmilik,String ph) throws Exception;		
	public Vector<MaklumaTanahGIS> getSenarai(
			String idNegeri, String idDaerah,String idMukim
			,String noFail
			,String idJenisHakmilik,String noHakmilikWarta
			,String idLot,String noLot
			,String idAgensi, String idKementerian
			,String tajuk,String idUrusan,String tarikhBukaFail) throws Exception;
	public void deleteGIS(String idGIS) throws Exception;
	
	
}	
