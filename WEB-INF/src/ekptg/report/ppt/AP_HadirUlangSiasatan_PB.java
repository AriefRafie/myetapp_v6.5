package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class AP_HadirUlangSiasatan_PB extends EkptgReportServlet{

	public AP_HadirUlangSiasatan_PB() {
		super.setReportName("SURAT KEPADA PIHAK BERKEPENTINGAN ATAU AGENSI PEMOHON – MAKLUMAN SUPAYA HADIR UNTUK ULANG SIASATAN");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
