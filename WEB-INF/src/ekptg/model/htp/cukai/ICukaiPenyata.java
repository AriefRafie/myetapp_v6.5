package ekptg.model.htp.cukai;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.htp.cukai.entity.CukaiPenyata;

public interface ICukaiPenyata {
	
	public Vector<CukaiPenyata> getPenyata(String idUrusan, String carian, String noFail, String idNegeri,String tahun) throws Exception;
	public Vector getList(String idUrusan, String carian, String noFail, Long idNegeri, String tahun) throws Exception;
	public Vector<Hashtable<String,String>> getSenaraiPenyata(String idNegeri,String tahun)throws Exception;
	public Vector getSenaraiFailXPenyata(String idUrusan, String carian, String noFail, String idNegeris)
		throws Exception;

}	
