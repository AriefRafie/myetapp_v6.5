package ekptg.model.htp.rekod;

import java.util.Hashtable;

public interface ITanahDaftar{
	
	//void kemaskini(Hashtable<?, ?> data, IHtp iHTP) throws Exception;
	public void kemaskini(Hashtable<String, String> data) throws Exception;
	public String simpanTransaction(Hashtable<String,String> hashData) throws Exception;
	
}	
