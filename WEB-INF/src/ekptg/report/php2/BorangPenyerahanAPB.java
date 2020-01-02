package ekptg.report.php2;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class BorangPenyerahanAPB extends EkptgReportServlet {

	public BorangPenyerahanAPB() {
		super.setReportName("BorangPenyerahanAPB");
		super.setFolderName("php2\\REV");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();
		
		parameters.put("TARIKH_DARI",(String)parameters.get("TARIKH_DARI"));
		parameters.put("TARIKH_HINGGA",(String)parameters.get("TARIKH_HINGGA"));
		parameters.put("ID_PEGAWAI",(String)parameters.get("ID_PEGAWAI"));
		
		String idPegawaiPenyemak = "";
		if (parameters.get("ID_PEGAWAI_PENYEMAK") != null){
			idPegawaiPenyemak = (String)parameters.get("ID_PEGAWAI_PENYEMAK");
		}		
		Hashtable pegawaiPenyemak = logic.getMaklumatPegawai(idPegawaiPenyemak);
		if (pegawaiPenyemak != null){
			parameters.put("NAMA_PEGAWAI_PENYEMAK",(String)pegawaiPenyemak.get("nama"));
			parameters.put("JAWATAN_PEGAWAI_PENYEMAK",(String)pegawaiPenyemak.get("jawatan"));
		}
		
		String idPegawaiPengesah = "";
		if (parameters.get("ID_PEGAWAI_PENGESAH") != null){
			idPegawaiPengesah = (String)parameters.get("ID_PEGAWAI_PENGESAH");
		}		
		Hashtable pegawaiPengesah = logic.getMaklumatPegawai(idPegawaiPengesah);
		if (pegawaiPengesah != null){
			parameters.put("NAMA_PEGAWAI_PENGESAH",(String)pegawaiPengesah.get("nama"));
			parameters.put("JAWATAN_PEGAWAI_PENGESAH",(String)pegawaiPengesah.get("jawatan"));
		}
	}
}
