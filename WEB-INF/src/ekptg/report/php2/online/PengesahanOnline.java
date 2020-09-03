package ekptg.report.php2.online;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class PengesahanOnline extends EkptgReportServlet {

	static Logger myLog = Logger.getLogger(PengesahanOnline.class);
	public PengesahanOnline() {
	}

	@Override
	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map parameters)
		throws Exception {
		String template = String.valueOf(parameters.get("template"));
		String folder = "php2//"+String.valueOf(parameters.get("folder"));
		super.setReportName(template);	
		if(folder!=null && !folder.equals("") ){
			super.setFolderName(folder);	
		}
		
	}
	
}
