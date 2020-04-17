package ekptg.report.htp.kjp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanRekodKementerian extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(LaporanRekodKementerian.class);
    private static String OS = System.getProperty("os.name").toLowerCase();

	public LaporanRekodKementerian() {
		myLog.info("print LaporanRekodKementerian...");
		super.setReportName("LaporanMOFMengikutKementerian");
		super.setFolderName("htp/kjp");
	}

	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map<String, Object> parameters)
		throws Exception {
		String template =String.valueOf(parameters.get("template"));
		parameters.put("os",OS.indexOf("win") >= 0?"1":"0");
		if(!template.equals("TIADA"))
			super.setReportName(template);	

	}
	
	
}
