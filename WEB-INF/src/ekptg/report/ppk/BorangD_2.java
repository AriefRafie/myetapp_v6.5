package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;



public class BorangD_2 extends EkptgReportServlet{
	public BorangD_2 (){
		super.setReportName("BorangD_2") ;
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map parameters)
		throws Exception {
		
	    
	}
	
	

}
