package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class CRBLaporanPenguatkuasaanTerperinci extends EkptgReportServlet {

	FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();
	
	public CRBLaporanPenguatkuasaanTerperinci() {
		
		super.setFolderName("php2\\CRB\\LAPORAN");
		
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String status = "";
		
		if (parameters.get("STATUS") != null){
			status = (String)parameters.get("STATUS");
		}

		if(status.equals("1")){
				super.setReportName("CRBSenaraiFailMengikutStatusBelumSelesai");
		}else if(status.equals("2")){
			super.setReportName("CRBSenaraiFailMengikutStatusSelesai");
		}else {
			super.setReportName("CRBSenaraiFailMengikutStatusBatal");
		}
	}
}
