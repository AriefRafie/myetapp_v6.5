package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPrestasiByNegeriTahunan extends EkptgReportServlet {
	
	public LaporanPrestasiByNegeriTahunan() {
		super.setReportName("htpLaporanPrestasiNegeriTahunan");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		String negeri =(String)parameters.get("negeri");
		String tahun =(String)parameters.get("tahun");
		ekptg.report.Utils utils = new ekptg.report.Utils(); 
		parameters.put("TAHUN",tahun);
		parameters.put("TAHUNSEBELUM",utils.getTahunSebelum(Integer.parseInt("1"),Integer.parseInt(tahun)));
		parameters.put("LAPORAN","BAGI TAHUN "+tahun);
		parameters.put("ID_NEGERI",Integer.parseInt(negeri));

	}
}