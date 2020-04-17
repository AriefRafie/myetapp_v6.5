package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPermohonanOnlineByUnit extends EkptgReportServlet{
	
	public LaporanPermohonanOnlineByUnit() {
		super.setReportName("LaporanPermohonanOnlineByUnit");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String noKP =(String)parameters.get("noKP");
		String nama =(String)parameters.get("nama");
		String noFail =(String)parameters.get("noFail");
		String seksyen =(String)parameters.get("seksyen");
		String idPejabat =(String)parameters.get("idPejabat");
		String userId =(String)parameters.get("userId");
	

		parameters.put("noKP",noKP);
		parameters.put("nama",nama.toUpperCase());
		parameters.put("noFail",noFail.toUpperCase());
		parameters.put("seksyen",seksyen);	
		parameters.put("idPejabat",idPejabat);	
		parameters.put("userId",userId);	
		
	}

}
