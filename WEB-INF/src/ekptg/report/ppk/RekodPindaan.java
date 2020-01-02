package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class RekodPindaan extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public RekodPindaan() {
		super.setReportName("RekodCatatanPembetulanPindaan");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {
		
		
		
		String userId =(String)parameters.get("userId");
		String txtTarikhDari =(String)parameters.get("txtTarikhDari");
		String txtTarikhHingga =(String)parameters.get("txtTarikhHingga");
	
	
		parameters.put("userId",userId);
		parameters.put("tarikh_dari",txtTarikhDari);
		parameters.put("tarikh_hingga",txtTarikhHingga);
		
	}
}
