package ekptg.model.htp.rekod;

import java.util.Hashtable;
import java.util.Vector;

public interface ITanahDaftar{
	
	//void kemaskini(Hashtable<?, ?> data, IHtp iHTP) throws Exception;
	public Hashtable<String,String> getMaklumat(String idRujukan) throws Exception;
	public String simpan(Hashtable<String,String> hashData) throws Exception;
	public Vector <Hashtable<String,String>> getSenaraiMaklumat(String idRujukan) throws Exception;
	public void hapus(String idRujukan) throws Exception;
	public void kemaskini(Hashtable<String, String> data) throws Exception;
	
	
}	
