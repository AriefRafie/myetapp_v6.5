package ekptg.report.htp.kjp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class XLaporanRekodKementerianPilihan extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(XLaporanRekodKementerianPilihan.class);
	
	public XLaporanRekodKementerianPilihan() {
		myLog.info("print LaporanRekodKementerianPilihan...");
		super.setReportName("LaporanMOFMengikutPilihanKementerian");
		super.setFolderName("htp/kjp");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
