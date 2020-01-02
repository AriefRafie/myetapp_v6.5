package ekptg.report.htp.cukai;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class CukaiLaporanHakmilik extends EkptgReportServlet {

	public CukaiLaporanHakmilik(){
		super.setReportName("htp1_");
	}
	public void doProcessing(HttpServletRequest request, 
			HttpServletResponse response, ServletContext context,Map parameters) throws Exception {
		// TODO Auto-generated method stub
		parameters.put("","");
	}

}
