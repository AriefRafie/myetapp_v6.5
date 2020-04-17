package ekptg.model.htp.pajakan;

import java.util.Hashtable;
import java.util.Vector;


public interface IPajakanFungsi {	
	
	public String simpan(String idPermohonan, Hashtable<String,String> hash) throws Exception;
	public void kemaskini(String idDraf, Hashtable<String,String> hash) throws Exception;
	public Vector<Hashtable<String,String>> getMaklumat(String idDraf) throws Exception;
	public Vector<Hashtable <String,String>> getSenarai(String idPermohonan,String jenis) throws Exception;


}
