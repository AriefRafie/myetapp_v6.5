package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

/**
 * @author dat
 *
 */

public class CRBSenaraiEdaran extends EkptgReportServlet{
	
	public CRBSenaraiEdaran() {
		super.setReportName("CBRSenaraiEdaran");
		super.setFolderName("php2\\CRB");
	}
	
	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		
	}

}
