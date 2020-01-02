package ekptg.model.htp;

import java.util.Hashtable;
import java.util.Vector;

public interface IHtpLaporan {	
	
	public Vector<Hashtable<String, Comparable>> getLaporanMengikutUrusan
	(String suburusan, String level, String jlaporan) throws Exception;
	public Vector getLaporanMengikutUrusanLike 
	(String suburusan, String level, String jlaporan,String templates) throws Exception;	
	public Vector getLaporanMengikutUrusan
	(String suburusan, String level, String jlaporan,String template,String notIn) throws Exception ;

	
	
	
}
