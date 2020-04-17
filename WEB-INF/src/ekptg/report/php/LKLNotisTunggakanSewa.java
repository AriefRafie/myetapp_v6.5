/**
 * 
 */
package ekptg.report.php;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

/**
 * @author man
 *
 */
public class LKLNotisTunggakanSewa extends EkptgReportServlet {

	public LKLNotisTunggakanSewa() {
		super.setReportName("LKLNotisTunggakanSewa");
		super.setFolderName("php");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
