package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class REVInvoisSewa extends EkptgReportServlet {

	public REVInvoisSewa() {
		super.setReportName("REVInvoisSewa");
		super.setFolderName("php2\\REV");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {	
		
		String idAkaun = (String)parameters.get("ID_AKAUN");
		FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();
		parameters.put("KADAR_SEWA", logic.getKadarSewaSebulan(idAkaun));
		parameters.put("TUNGGAKAN_SEWA", logic.getTunggakanSewa(idAkaun));
		parameters.put("SEWA_SEMASA", logic.getKadarSewaSemasa(idAkaun));
	}
}
