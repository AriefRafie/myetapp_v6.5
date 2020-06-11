package ekptg.model.htp.rekod;

import java.util.Hashtable;
import java.util.Vector;

public interface bak_IHakmilikPergerakan {
	public String addPergerakan(Hashtable<String,String> data) throws Exception;
	public void updatePergerakan(Hashtable<String,String> data) throws Exception;
	public void hapus(String id) throws Exception;
	public Hashtable<String,String> getMaklumatPergerakan(String id) throws Exception;
	public Vector<Hashtable<String,String>> getSenaraiPergerakanHakmilik(String id) throws Exception;


}	
