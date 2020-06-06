package ekptg.model.htp.rekod;

import ekptg.model.htp.entity.HakMilik;

public interface ITanah{
	
	public HakMilik kemaskini(HakMilik hakmilik) throws Exception;
	public String getLuas(String jenisLuas,String luas
			,String luas2,String luas3,String luas4
			,String luas5,String luas6);
	
}	
