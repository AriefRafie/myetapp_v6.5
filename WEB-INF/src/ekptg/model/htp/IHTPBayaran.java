package ekptg.model.htp;

import ekptg.model.htp.entity.Bayaran;

public interface IHTPBayaran {	

	public String selectCaraBayaran(String selectName, Long selectedValue
			, String disability, String jsFunction, String idBayaran ) throws Exception ;	
		
	public Bayaran getPembayaranByPermohonan(String idPermohonan)throws Exception ;

	
}
