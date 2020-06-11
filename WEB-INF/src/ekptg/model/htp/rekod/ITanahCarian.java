package ekptg.model.htp.rekod;

import java.util.Hashtable;
import java.util.Vector;


public interface ITanahCarian{
	//13 Param
	public Vector getCarianSenaraiHakmilikRizab(String idJenisTanah
			, String idNegeri, String idDaerah, String idMukim
			, String noFail
			, String idJenisHakmilik,String noHakmilik
			, String noWarta
			, String idLot, String noLot
			, String idAgensi, String idKementerian
			, String idStatus) throws Exception ;
	public Vector getCarianSenaraiHakmilikRizab(String idJenisTanah
			, String idNegeri, String idDaerah, String idMukim
			, String noFail
			, String idJenisHakmilik,String noHakmilik
			, String noWarta
			, String idLot, String noLot
			, String idAgensi, String idKementerian
			, String idStatus,String kegunaan) throws Exception ;

	//12 param
	public Vector getCarianSenaraiHakmilikRizab(String idJenisTanah
			, String idNegeri, String idDaerah, String idMukim
			, String noFail
			, String idJenisHakmilik
			, String noHakmilikWarta
			, String idLot, String noLot
			, String idAgensi, String idKementerian
			, String idStatus) throws Exception ;
	
	public Hashtable<String, String> getCarianHakmilikRizab(String idHakmilik) throws Exception ;

	
	
}	
