package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SuratPanggilPBTerimaPampasan_LainKos extends EkptgReportServlet {
	
	public SuratPanggilPBTerimaPampasan_LainKos() {
		super.setReportName("suratUtkPanggilanTerimaPampasanKpdPB_LainKos");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}