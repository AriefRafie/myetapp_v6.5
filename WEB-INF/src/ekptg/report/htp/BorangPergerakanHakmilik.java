package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BorangPergerakanHakmilik  extends EkptgReportServlet {
	public BorangPergerakanHakmilik() {
		super.setReportName("BorangPergerakanDokumen");
		super.setFolderName("htp");
		
	}

	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map<String, Object> parameters)
		throws Exception {
		String template =String.valueOf(parameters.get("template"));
			if(!template.equals("TIADA") && !template.equals("")){
			super.setReportName(template);	
		}
		
	}
	

}

