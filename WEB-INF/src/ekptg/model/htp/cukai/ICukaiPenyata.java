package ekptg.model.htp.cukai;

import java.util.Vector;

public interface ICukaiPenyata {
	
	public Vector getPenyata(String idUrusan, String carian, String noFail, String idNegeri,String tahun) throws Exception;
	public Vector getList(String idUrusan, String carian, String noFail, Long idNegeri, String tahun) throws Exception;
	public Vector getSenaraiPenyata(String idNegeri,String tahun)throws Exception;
	public Vector getSenaraiFailXPenyata(String idUrusan, String carian, String noFail, String idNegeris)
		throws Exception;

}	
