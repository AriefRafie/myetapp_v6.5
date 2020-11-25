package ekptg.model.htp.pembelian;

import java.util.Vector;

import ekptg.model.htp.entity.HtpPermohonan;

public interface IPembelianOnline {
	public Vector findFailKJP(String carian, String noFail, String noFailOnline, String idNegeri
			,String idKementerian,String idAgensi,String idSubUrusan,String idStatus);
	
	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String ipPermohonan,String idHtpPermohonan)throws Exception;
	
}//close here
