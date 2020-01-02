package ekptg.model.htp.pembelian;

import java.util.Vector;

import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.entity.Bangunan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;

public interface IPembelian {
	public HtpPermohonan simpanPermohonan(HtpPermohonan htpPermohonan)throws Exception;
	public HtpPermohonan simpanPermohonanOnline(HtpPermohonan htpPermohonan)throws Exception;
	
	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String ipPermohonan,String idHtpPermohonan)throws Exception;
	
	public HtpPermohonan findPermohonan(String idPermohonan, String idHtpPermohonan) throws Exception;
	
	public Vector findFail(String carian, String noFail, String idNegeri);
	public Vector findFailKJP(String carian, String noFail, String idNegeri,String idKementerian);
	public Vector findFailOnline2(String carian, String noFail, String idNegeri,String idKementerian);
	public Vector findFailOnline(String carian, String noFail, String idNegeri,String idKementerian);

	public HakmilikUrusan simpanHakmilik(HakmilikUrusan urusan);
	public HakmilikUrusan simpanHakmilikOnline(HakmilikUrusan urusan);
	public HakmilikUrusan updateHakmilik(HakmilikUrusan urusan);
	public void deleteHakmilik(String idHakmilikUrusan) throws Exception;
	public HakmilikUrusan findByHakmilikUrusanId(String hakmilikUrusanId);
	public Vector<HakmilikUrusan> getHakmilikList(String idPermohonan);
	
	public Pemohon simpanPenjual(Pemohon pemohon);
	public Pemohon updatePenjual(Pemohon pemohon);
	public Pemohon findByPermohonan (String idPermohonan);
	public void hapusPenjual(Pemohon pemohon);

	public Bangunan simpanBangunan(Bangunan bangunan);
	public Bangunan updateBangunan(Bangunan bangunan);
	public Bangunan findBangunan(String idBangunan);
	public Vector<Bangunan> findBangunanByPermohonan(String idPermohonan);
	public void deleteBangunan(String idBangunan);
	
	
}
