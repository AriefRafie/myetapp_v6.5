package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;



public class BorangD_ORI extends EkptgReportServlet{
	public BorangD_ORI (){
		super.setReportName("BorangD_ORI") ;
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map parameters)
		throws Exception {
		
	    
	}
	
	

}
