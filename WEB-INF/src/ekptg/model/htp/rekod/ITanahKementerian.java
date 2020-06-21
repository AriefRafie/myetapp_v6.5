package ekptg.model.htp.rekod;

import ekptg.model.htp.entity.HakmilikAgensi;

public interface ITanahKementerian{
//	public Vector<?> getMaklumat(String idHakmilik) throws Exception ;
	public void kemaskiniTanahAgensi(HakmilikAgensi ha) throws Exception;
	public void kemaskiniTanahAgensiLuas(HakmilikAgensi ha) throws Exception;
//	public void insertTanahAgensi(Db db,Hashtable<String,String> data,String idhakmilik) throws Exception;

}	
