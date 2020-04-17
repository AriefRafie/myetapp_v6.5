package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class laporanTanahSS8 extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public laporanTanahSS8() {
		
		
		
		
		
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String id_negeri = (String)parameters.get("id_negeri");		
		System.out.print("--ID NEGERI--"+id_negeri);
		
		if(id_negeri.equals("6"))
		{
			super.setReportName("laporan_awal_tanah_SS8_pahang");
		}
		else
		{
			super.setReportName("laporan_awal_tanah_SS8");
		}
		
		//super.setReportName("laporan_awal_tanah_SS8");
		super.setFolderName("ppt");
		
	}
}