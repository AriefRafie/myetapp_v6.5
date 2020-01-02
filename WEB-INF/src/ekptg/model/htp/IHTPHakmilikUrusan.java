package ekptg.model.htp;

import java.util.Hashtable;
import java.util.Vector;

public interface IHTPHakmilikUrusan {	

	// exist in class ekptg.model.htp.FrmGadaianPenHamilikData, update(String idHakmilikUrusan)
	public void kemaskini(Hashtable<String,String> data) throws Exception;
	// exist in class ekptg.model.htp.FrmGadaianPenHamilikData, simpan(String idHakmilikUrusan)
	public void simpan(Hashtable<String,String> data) throws Exception;
	public Vector<HakmilikUrusan> getSenaraiHakmilikUrusan(String idPermohonan) ;
	public etapp.entity.htp.HakmilikUrusan simpan(etapp.entity.htp.HakmilikUrusan hu) throws Exception ;
	public HakmilikUrusan findByIdHakmilikUrusan(String idHakmilikUrusan) ;
	public etapp.entity.htp.HakmilikUrusan getHakmilikUrusan(String idHakmilikUrusan) throws Exception ;
	// exist in class ekptg.model.htp.FrmPajakanPendaftaranData, deleteHakmilik(String idHakmilikUrusan)
	public void hapusHakmilik(String idHakmilikUrusan) throws Exception ;
	public etapp.entity.htp.HakmilikUrusan kemaskini(etapp.entity.htp.HakmilikUrusan huTemp) throws Exception ;
	public Vector<Hashtable<String,String>> getCarianSenaraiHakmilik(String idJenisTanah
		,String idNegeri, String idDaerah,String idMukim
		,String noFail
		,String idJenisHakmilik,String noHakmilik
		,String noWarta
		,String idLot,String noLot
		,String idAgensi) throws Exception;
	
	
}
