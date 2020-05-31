package ekptg.report.php2;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class REVSuratTuntutanDeposit extends EkptgReportServlet {
	
	FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();

	public REVSuratTuntutanDeposit() {
		super.setFolderName("php2\\REV");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {

		String idFail = (String) parameters.get("ID_FAIL");
		String idPegawai = "";
		if (parameters.get("ID_PEGAWAI") != null){
			idPegawai = (String)parameters.get("ID_PEGAWAI");
		}
		String bakiLebihan = "";
		if (parameters.get("BAKI_LEBIHAN") != null){
			bakiLebihan = (String)parameters.get("BAKI_LEBIHAN");
		}
		
		Hashtable pegawai = logic.getMaklumatPegawai(idPegawai);
		if (pegawai != null) {
			parameters.put("NAMA_PEGAWAI", (String) pegawai.get("nama"));
			parameters.put("JAWATAN",(String)pegawai.get("jawatan"));
			parameters.put("NO_TEL",(String)pegawai.get("noTel"));
			parameters.put("NO_FAKS",(String)pegawai.get("noFaks"));
			parameters.put("EMAIL",(String)pegawai.get("emel"));
		}
		
		super.setReportName("REVSuratTuntutanDeposit");
		super.setFolderName("php2\\REV");
	}
}
