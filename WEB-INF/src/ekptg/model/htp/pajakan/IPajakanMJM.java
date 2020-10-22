package ekptg.model.htp.pajakan;

import ekptg.model.entities.Tblhtpjemaahmenteri;
import ekptg.model.htp.entity.PajakanUlasan;
import ekptg.model.htp.entity.PerakuanJawatankuasa;

public interface IPajakanMJM {

	public PajakanUlasan getMaklumatUlasan(String idUlasanKJP) throws Exception;
	public Tblhtpjemaahmenteri getMaklumatMemorandumJemaahMenteri(String idPermohonan) throws Exception;
	public PerakuanJawatankuasa getMaklumatPerakuanJawatankuasa(String idPermohonan) throws Exception;

}
