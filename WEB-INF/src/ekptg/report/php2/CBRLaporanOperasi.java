package ekptg.report.php2;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmPLPPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class CBRLaporanOperasi extends EkptgReportServlet {

	public CBRLaporanOperasi() {
		super.setReportName("CBRLaporanOperasi");
		super.setFolderName("php2\\CRB");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		FrmPLPPopupCetakLaporanData logic = new FrmPLPPopupCetakLaporanData();
		
		String idLaporanTanah = "";
		if (parameters.get("ID_LAPORANTANAH") != null){
			idLaporanTanah = (String)parameters.get("ID_LAPORANTANAH");
		}
		
		logic.getMesyuarat(idLaporanTanah);
		Vector listMesyuarat = logic.getBeanMesyuarat();
		if (listMesyuarat.size() != 0){
			Hashtable hMesyuarat = (Hashtable) listMesyuarat.get(0);
			parameters.put("TARIKH_MESYUARAT",(String)hMesyuarat.get("tarikhMesyuarat"));
			parameters.put("TAJUK",(String)hMesyuarat.get("tajuk"));
			parameters.put("NAMA_PENGERUSI",(String)hMesyuarat.get("namaPegawai"));
		}
	}
}