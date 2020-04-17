package ekptg.report.esaduan;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanByKategoriAduan extends EkptgReportServlet{

	public LaporanByKategoriAduan() {
		super.setReportName("LaporanByKategoriAduan");
		super.setFolderName("esaduan");
	}
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	

}

