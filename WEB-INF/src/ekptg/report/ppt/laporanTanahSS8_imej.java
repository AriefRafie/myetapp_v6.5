package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class laporanTanahSS8_imej extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public laporanTanahSS8_imej() {
		
		
		
		
		
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String id_negeri = (String)parameters.get("id_negeri");		
		System.out.print("--ID NEGERI--"+id_negeri);
		
		
			super.setReportName("laporan_tanah_SS8_imej");
		
		
		//super.setReportName("laporan_awal_tanah_SS8");
		super.setFolderName("ppt");
		
	}
}