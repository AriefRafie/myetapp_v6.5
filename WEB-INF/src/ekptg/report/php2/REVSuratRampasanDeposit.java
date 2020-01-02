package ekptg.report.php2;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class REVSuratRampasanDeposit extends EkptgReportServlet {

	FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();

	public REVSuratRampasanDeposit() {
		super.setFolderName("php2\\REV");

	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {

		String idNotis = (String) parameters.get("ID_NOTIS");
		String idPegawai = (String) parameters.get("ID_PEGAWAI");
		
		Hashtable pegawai = logic.getMaklumatPegawai(idPegawai);
		if (pegawai != null) {
			parameters.put("NAMA_PEGAWAI", (String) pegawai.get("nama"));
		}

		Double jumlahTunggakan = logic.getJumlahTunggakan(idNotis);
		Double jumlahDeposit = logic.getJumlahDeposit(idNotis);
		Double bakiTunggakan = jumlahDeposit - jumlahTunggakan;
		
		if (bakiTunggakan < 0D) {
			parameters.put("JUMLAH_DEPOSIT_DIRAMPAS", String.valueOf(jumlahDeposit));
			parameters.put("BAKI_TUNGGAKAN", String.valueOf(bakiTunggakan * -1));
			super.setReportName("REVSuratRampasanDeposit2");
		} else {
			parameters.put("JUMLAH_DEPOSIT_DIRAMPAS", String.valueOf(jumlahTunggakan));
			super.setReportName("REVSuratRampasanDeposit1");
		}
	}
}
