package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class APBLaporanPelesenPasirCerakinanLuas extends EkptgReportServlet {

	FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();
	
	public APBLaporanPelesenPasirCerakinanLuas() {
		
		super.setFolderName("php2\\APB\\LAPORAN");
		super.setReportName("APBLaporanPelesenPasirCerakinanLuas");
		
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
	}
}
