package ekptg.model.htp.rekod;

import java.util.Hashtable;
import java.util.Vector;


public interface IHakmilikUrusan{
	public Vector<Hashtable<String,String>> getMaklumat(String idHakmilik)throws Exception ;
}	
