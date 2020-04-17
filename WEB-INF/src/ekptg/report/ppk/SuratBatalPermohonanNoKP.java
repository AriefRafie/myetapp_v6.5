package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SuratBatalPermohonanNoKP extends EkptgReportServlet {
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public SuratBatalPermohonanNoKP() {
		//super.setReportName("SuratBatalPermohonanLainKes");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		String jasper = (String)parameters.get("f");
		System.out.println("jasper:"+jasper);
		super.setReportName(jasper);
	}

	
}