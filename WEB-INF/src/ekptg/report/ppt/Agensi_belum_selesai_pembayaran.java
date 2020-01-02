
package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class Agensi_belum_selesai_pembayaran extends EkptgReportServlet{

	public Agensi_belum_selesai_pembayaran() {
		super.setReportName("agensi_belum_selesai_buat_bayaran");
		super.setFolderName("ppt");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	}
	
}
