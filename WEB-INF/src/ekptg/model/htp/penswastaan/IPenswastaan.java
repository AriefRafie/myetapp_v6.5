package ekptg.model.htp.penswastaan;

import java.util.Vector;

import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.entity.Bangunan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;

public interface IPenswastaan {
	public HtpPermohonan simpanPermohonan(HtpPermohonan htpPermohonan)throws Exception;
	
	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String ipPermohonan,String idHtpPermohonan)throws Exception;
	
	public HtpPermohonan findPermohonan(String idPermohonan, String idHtpPermohonan) throws Exception;
	
	public Vector findFail(String carian, String noFail, String idNegeri);
	
	public HakmilikUrusan simpanHakmilik(HakmilikUrusan urusan);
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
	//29/06/2011
	public boolean isMaklumatPemaju(String idPermohonan) throws Exception ;		
	public void hapusPemaju(String idPemaju) throws Exception ;

	
}
