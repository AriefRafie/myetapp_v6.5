package ekptg.model.htp.rekod;

import java.util.Vector;

import ekptg.intergration.entity.BorangK;
import ekptg.model.htp.entity.HtpPermohonan;

public interface IPenawaranTukarLepas {	
	public Vector findFail(String idUrusan,String noFail,String tajukFail,String pemohon,String idNegeri
			,String idDaerah,String idMukim,String idKementerian,String idAgensi
			,String tarikhPohon,String tarikhBukaFail,String tarikhBukaFailHingga);
	
	public Vector carianFail(String noFail, String tajukFail,String tarikhTerima, String namaPemohon,String idNegeri,String idKementerian,String idAgensi) throws Exception ;

	public void hapusBayaran(String idBayaran);

	public BorangK carianFailPPT(String noFail, String idHakmilik) throws Exception ;

	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String ipPermohonan,String idHtpPermohonan)throws Exception;

}
