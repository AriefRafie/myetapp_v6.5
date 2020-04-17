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
 * @author Hana
 *
 */
public class SuratTawaranKelulusan extends EkptgReportServlet {

	public SuratTawaranKelulusan() {
		super.setReportName("SuratTawaranKelulusan");
		super.setFolderName("php");
	}


	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
