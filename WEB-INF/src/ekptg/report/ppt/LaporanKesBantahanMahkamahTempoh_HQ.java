package ekptg.report.ppt;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanKesBantahanMahkamahTempoh_HQ extends EkptgReportServlet{
	
	public LaporanKesBantahanMahkamahTempoh_HQ() {
		
		
		super.setReportName("Laporan Bulanan Kes Bantahan Ke Mahkamah(by tempoh HQ)");		
		super.setFolderName("ppt");
	}

	//@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
