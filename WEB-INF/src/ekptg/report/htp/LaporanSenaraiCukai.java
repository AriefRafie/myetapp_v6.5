package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanSenaraiCukai extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(LaporanSenaraiCukai.class);
	public LaporanSenaraiCukai() {
		myLog.info("print LaporanSenaraiCukai...");
		super.setReportName("SENARAI_CUKAI_TANAH_MILIK_PTP");
		super.setFolderName("htp");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
