/**
 * 
 */
package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

/**
 * @author hilda
 *
 */
public class PYWKertasRingkasan extends EkptgReportServlet {

	public PYWKertasRingkasan() {
		super.setReportName("PYWKertasRingkasan");
		super.setFolderName("php2\\PYW");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}

