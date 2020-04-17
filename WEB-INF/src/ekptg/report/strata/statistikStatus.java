package ekptg.report.strata;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class statistikStatus extends EkptgReportServlet{
	public statistikStatus() {
		super.setReportName("strSenaraiMengikutStatus");
		super.setFolderName("str");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
//		//getParameter
//		
//		String idNegeri = String.valueOf(parameters.get("idNegeri"));
//		System.out.println("Id Negeri= "+idNegeri);
//		parameters.put("idNegeri", idNegeri);
		
		
	}

}
