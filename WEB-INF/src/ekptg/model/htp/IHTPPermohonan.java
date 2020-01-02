package ekptg.model.htp;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.KeputusanUlasan;

public interface IHTPPermohonan {
	public HtpPermohonan kemaskiniPermohonanTarikh(HtpPermohonan htpPermohonan
		,String ipPermohonan,String idHtpPermohonan) throws Exception;	
	public KeputusanUlasan keputusanPermohonanTanah(KeputusanUlasan ku) throws Exception;
	public Vector<Hashtable<String,String>> getPermohonanAktifLangkah(String idUrusan,String langkah) throws Exception;
	public Hashtable<String, String> getPermohonanInfo(String idpermohonan) throws Exception;
	public HtpPermohonan getPermohonanInfo(String idFail,String idPermohonan) throws Exception;
	public String getKeputusanPermohonan(int i,String idNegeri);

}
