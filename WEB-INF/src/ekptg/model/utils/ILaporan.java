package ekptg.model.utils;

import java.util.Hashtable;
import java.util.Vector;

public interface ILaporan {	
	
	public Vector<Hashtable<String, String>> getLaporanMengikutSeksyen
		(String seksyen, String level, String jlaporan) throws Exception ;
	public void setReportRolePPK(org.apache.velocity.VelocityContext context 
			,String reportRole
			,String idNegeri,String idUnit) throws NumberFormatException, Exception;
	
}
