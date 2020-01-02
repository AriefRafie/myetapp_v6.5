package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class PPKBorangB extends EkptgReportServlet{
	
	public PPKBorangB() {
		//default template
		super.setReportName("PPKBorangB");
		super.setFolderName("ppk");
		
	}

	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
		ServletContext context,Map<String, Object> parameters) throws Exception {
		/* Nama template laporan yang dihantar dari Skrin*/
		String template = String.valueOf(parameters.get("template"));
		if(!template.equals(""))
			super.setReportName(template);
		
		//PPKBorangB - parameter (idfail,kodpejabat,userlogin
		//						,hantar[0-Maklumat Permohonan Selepas Hantar| 1- Maklumat Permohonan])
		//BorangB2_A4 - parameter (idfail)

	}
	

}
