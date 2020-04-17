package ekptg.report.php2;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmAPBPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class APBUlanganSuratMintaUlasanJAS extends EkptgReportServlet {

	public APBUlanganSuratMintaUlasanJAS() {
		super.setReportName("APBUlanganSuratMintaUlasanJAS");
		super.setFolderName("php2\\APB");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		FrmAPBPopupCetakLaporanData logic = new FrmAPBPopupCetakLaporanData();
		
		String idPegawai = "";
		if (parameters.get("ID_PEGAWAI") != null){
			idPegawai = (String)parameters.get("ID_PEGAWAI");
		}
		
		logic.setMaklumatPegawai(idPegawai);
		Vector list = logic.getBeanMaklumatPegawai();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			parameters.put("NAMA_PEGAWAI",(String)h.get("nama"));
			parameters.put("JAWATAN",(String)h.get("jawatan"));
			parameters.put("NO_TEL",(String)h.get("noTel"));
			parameters.put("NO_FAKS",(String)h.get("noFaks"));
			parameters.put("EMAIL",(String)h.get("emel"));
		}
		
	}
}
