package ekptg.report.pdt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanBulanan extends EkptgReportServlet
{
	
	public LaporanBulanan() {
		super.setFolderName("pdt");
		
	}
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {
		//super.setReportName("lptrtest");
		super.setReportName(request.getParameter("jrxmlfile"));
		String var1 = request.getParameter("var1");
		String Tahun = request.getParameter("Tahun");
		System.out.println("Tahun:"+Tahun);
		System.out.println("Val1:"+var1);

	}

}