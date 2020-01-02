package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanStatistikPembayaranCukai extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(LaporanStatistikPembayaranCukai.class);
	public LaporanStatistikPembayaranCukai() {
		myLog.info("print LaporanStatistikPembayaranCukai...");
		super.setReportName("STATISTIK_PEMBAYARAN_CUKAI_PTP");
		super.setFolderName("htp");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
