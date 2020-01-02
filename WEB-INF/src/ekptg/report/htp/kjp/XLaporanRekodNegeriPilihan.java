package ekptg.report.htp.kjp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class XLaporanRekodNegeriPilihan extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(XLaporanRekodNegeriPilihan.class);
	
	public XLaporanRekodNegeriPilihan() {
		myLog.info("print LaporanRekodNegeriPilihan...");
		super.setReportName("LaporanMOFMengikutPilihanNegeri");
		super.setFolderName("htp/kjp");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
