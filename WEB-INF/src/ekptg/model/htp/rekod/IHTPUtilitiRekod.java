package ekptg.model.htp.rekod;

import ekptg.model.htp.entity.HakMilik;

public interface IHTPUtilitiRekod {	

	public void kemaskiniPerolehan(String idHakmilik, String idPermohonan, String noFail) 
		throws Exception;
	public HakMilik getTanah(String idHakmilik);

	
}
