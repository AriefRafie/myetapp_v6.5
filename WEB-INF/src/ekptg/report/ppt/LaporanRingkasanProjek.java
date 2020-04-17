
package ekptg.report.ppt;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanRingkasanProjek extends EkptgReportServlet{

	public LaporanRingkasanProjek() {
		super.setReportName("LaporanRingkasanProjekMain");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		String template =(String)parameters.get("template");
		super.setReportName(template);	
		if(String.valueOf(parameters.get("type")).equals("3")){			
//	    	String strTahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
	    	String strBulan = lebah.util.Util.getDateTime(new Date(), "MM");
//	    	System.out.println(strBulan+":"+strTahun);
//			parameters.put("TAHUN",strTahun);
//			parameters.put("TAHUNSE",strTahun);
			parameters.put("BULAN","01");
//			parameters.put("BULANSE",strBulan);

		}
		
	}
	
	
}
