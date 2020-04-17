package ekptg.report.php2;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class REVSuratPemulanganCek extends EkptgReportServlet {

	public REVSuratPemulanganCek() {
		super.setReportName("REVSuratPemulanganCek");
		super.setFolderName("php2\\REV");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();
		
		String idPegawai = "";
		String noTel = "";
		if (parameters.get("ID_PEGAWAI") != null){
			idPegawai = (String)parameters.get("ID_PEGAWAI");
		}
		
		Hashtable pegawai = logic.getMaklumatPegawai(idPegawai);
		if (pegawai != null){
			parameters.put("NAMA_PEGAWAI",(String)pegawai.get("nama"));
		}
	}
}
