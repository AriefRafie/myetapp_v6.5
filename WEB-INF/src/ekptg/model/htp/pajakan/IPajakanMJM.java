package ekptg.model.htp.pajakan;

import ekptg.model.entities.Tblhtpjemaahmenteri;
import ekptg.model.htp.entity.PajakanUlasan;

public interface IPajakanMJM {	
	
	public PajakanUlasan getMaklumatUlasan(String idUlasanKJP) throws Exception;
	public Tblhtpjemaahmenteri getMaklumatMemorandumJemaahMenteri(String idPermohonan) throws Exception;

}
