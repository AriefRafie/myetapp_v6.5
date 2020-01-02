package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SuratPeringatan extends EkptgReportServlet{
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();

	public SuratPeringatan() {
		super.setReportName("SuratPeringatan");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {

		super.flagMaklumatPegawai(true);
		parameters.put("daerahMohon", logic.getDaerahMohonByIdFail(parameters
				.get("idfail").toString(), parameters.get("flagReport")
				.toString()));
		super.setIdPermohonan(parameters.get("idfail").toString(), parameters);
	}

}
