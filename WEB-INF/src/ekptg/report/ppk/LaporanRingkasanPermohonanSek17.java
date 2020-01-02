package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanRingkasanPermohonanSek17 extends EkptgReportServlet {
	
	public LaporanRingkasanPermohonanSek17() {
		super.setReportName("LaporanRingkasanPermohonanSek17");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String tahun =(String)parameters.get("tahun");
		String idnegeri =(String)parameters.get("idnegeri");
		String idunit =(String)parameters.get("idunit");
		String iddaerah =(String)parameters.get("iddaerah");

		parameters.put("TAHUN",tahun);
		parameters.put("ID_NEGERI",idnegeri);
		parameters.put("ID_UNIT",idunit);
		parameters.put("ID_DAERAH",iddaerah);		
		
	}
}
	
	