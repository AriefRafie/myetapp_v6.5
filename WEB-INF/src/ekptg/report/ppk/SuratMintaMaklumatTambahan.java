package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SuratMintaMaklumatTambahan extends EkptgReportServlet {
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();

	public SuratMintaMaklumatTambahan() {
		super.setReportName("SuratMintaMaklumatTambahan");
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