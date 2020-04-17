package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class BorangJPN extends EkptgReportServlet {
	
	static Logger myLogger = Logger.getLogger(BorangJPN.class);
	
	public BorangJPN() {
		super.setReportName("BorangJPN");
		super.setFolderName("ppk");
		super.setGenerateTextFile(true);
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
	
	
}