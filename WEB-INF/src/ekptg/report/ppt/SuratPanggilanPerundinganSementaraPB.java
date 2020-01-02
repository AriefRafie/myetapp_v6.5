package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SuratPanggilanPerundinganSementaraPB extends EkptgReportServlet{
	
	public SuratPanggilanPerundinganSementaraPB() {
		super.setReportName("suratPangilanPerundinganSementaraPB");
		super.setFolderName("ppt");
	}
	


	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
