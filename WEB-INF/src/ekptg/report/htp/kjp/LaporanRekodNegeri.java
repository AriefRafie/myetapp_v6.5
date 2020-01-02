package ekptg.report.htp.kjp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanRekodNegeri extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(LaporanRekodNegeri.class);
    private static String OS = System.getProperty("os.name").toLowerCase();

	public LaporanRekodNegeri() {
		myLog.info("print LaporanRekodNegeri...");
		super.setReportName("LaporanMOFMengikutNegeri");
		super.setFolderName("htp/kjp");
		
	}

	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map<String, Object> parameters)
		throws Exception {
		String template = String.valueOf(parameters.get("template"));
		myLog.info("print LaporanRekodNegeri:template="+template);
		parameters.put("os",OS.indexOf("win") >= 0?"1":"0");
		if(!template.equals("TIADA"))
			super.setReportName(template);	
		
	}
	
	
}
