package ekptg.report.htp.kjp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanRekodAgensiPilihan extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(LaporanRekodAgensiPilihan.class);
    private static String OS = System.getProperty("os.name").toLowerCase();

	public LaporanRekodAgensiPilihan() {
		myLog.info("print LaporanRekodAgensiPilihan...");
		super.setReportName("LaporanMOFMengikutPilihanAgensi");
		super.setFolderName("htp/kjp");
		
	}

	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map<String, Object> parameters)
		throws Exception {
		parameters.put("os",OS.indexOf("win") >= 0?"1":"0");
	}
	
	
}
