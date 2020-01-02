package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class KulitFail extends EkptgReportServlet{
	public KulitFail (){
		super.setReportName("KulitFail");
		super.setFolderName("ppk");
		//super.flagMaklumatPermohonan(true);
		//super.flagMaklumatSimati(true);
		//super.flagMaklumatPemohon(true);
	}
	
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {
	}
}
