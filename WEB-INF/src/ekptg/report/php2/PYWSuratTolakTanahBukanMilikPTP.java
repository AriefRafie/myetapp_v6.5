package ekptg.report.php2;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmPYWPopupCetakUtilitiData;
import ekptg.report.EkptgReportServlet;

public class PYWSuratTolakTanahBukanMilikPTP extends EkptgReportServlet {

	public PYWSuratTolakTanahBukanMilikPTP() {
		super.setReportName("PYWSuratTolakTanahBukanMilikPTP");
		super.setFolderName("php2\\PYW");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		FrmPYWPopupCetakUtilitiData logic = new FrmPYWPopupCetakUtilitiData();
		
		String idPegawai = "";
		if (parameters.get("ID_PEGAWAI") != null){
			idPegawai = (String)parameters.get("ID_PEGAWAI");
		}
		
		String idNegeri = "";
		if (parameters.get("ID_NEGERI") != null){
			idNegeri = (String)parameters.get("ID_NEGERI");
		}
		String idBandar = "";
		if (parameters.get("ID_BANDAR") != null){
			idBandar = (String)parameters.get("ID_BANDAR");
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
		logic.setMaklumatNegeri(idNegeri);
		Vector list2 = logic.getBeanMaklumatNegeri();
		if (list2.size() != 0){
			Hashtable h = (Hashtable) list2.get(0);
			parameters.put("NEGERI_PEMOHON",(String)h.get("negeri"));
		}
		logic.setMaklumatBandar(idBandar);
		Vector list3 = logic.getBeanMaklumatBandar();
		if (list3.size() != 0){
			Hashtable h = (Hashtable) list3.get(0);
			parameters.put("BANDAR_PEMOHON",(String)h.get("bandar"));
		}
	}
}
