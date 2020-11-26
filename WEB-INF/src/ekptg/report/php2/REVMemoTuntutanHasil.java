package ekptg.report.php2;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class REVMemoTuntutanHasil extends EkptgReportServlet {

	FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();

	public REVMemoTuntutanHasil() {
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

		Hashtable pegawai = logic.getMaklumatPegawai(idPegawai);
		if (pegawai != null) {
			parameters.put("NAMA_PEGAWAI", (String) pegawai.get("nama"));
			parameters.put("JAWATAN",(String)pegawai.get("jawatan"));
			parameters.put("NO_TEL",(String)pegawai.get("noTel"));
			parameters.put("NO_FAKS",(String)pegawai.get("noFaks"));
			parameters.put("EMAIL",(String)pegawai.get("emel"));
		}

		String jumahHasil = "";
		if (parameters.get("JUMLAH_HASIL") != null){
			jumahHasil = (String)parameters.get("JUMLAH_HASIL");
		}

		parameters.put("TARIKH_HASIL",(String)parameters.get("TARIKH_HASIL"));

		super.setReportName("REVMemoTuntutanHasil");
		super.setFolderName("php2\\REV");
	}
}
