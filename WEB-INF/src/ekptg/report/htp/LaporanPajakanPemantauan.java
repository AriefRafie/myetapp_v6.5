package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanPajakanPemantauan extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(ekptg.report.htp.LaporanPajakanPemantauan.class);
	public LaporanPajakanPemantauan() {
		//myLog.info("print LaporanPemantauanPajakan...");
		//super.setReportName("PEMANTAUAN KERJA UNIT PAJAKAN");
		super.setReportName("HTPajakanLaporanPemantauanKerja");
		super.setFolderName("htp");
		
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map<String, Object> parameters)
			throws Exception {
	}
	
}
