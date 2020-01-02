package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class KPICetakTukarguna extends EkptgReportServlet {

	public KPICetakTukarguna() {
		super.setReportName("KPI_utama_Tukarguna");
		super.setFolderName("php2\\kpi");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
				
	}
}
