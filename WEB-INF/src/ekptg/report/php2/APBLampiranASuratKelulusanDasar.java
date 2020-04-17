package ekptg.report.php2;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.helpers.NumberToWords;
import ekptg.model.php2.FrmAPBCetakSuratKeputusanData;
import ekptg.model.php2.FrmAPBPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class APBLampiranASuratKelulusanDasar extends EkptgReportServlet {

	public APBLampiranASuratKelulusanDasar() {
		super.setReportName("APBLampiranASuratKelulusanDasar");
		super.setFolderName("php2\\APB");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		FrmAPBPopupCetakLaporanData logic = new FrmAPBPopupCetakLaporanData();
		FrmAPBCetakSuratKeputusanData logicKelulusanDasar = new FrmAPBCetakSuratKeputusanData();
		
		String idPermohonan = "";
		if (parameters.get("ID_PERMOHONAN") != null){
			idPermohonan = (String)parameters.get("ID_PERMOHONAN");
		}
		
		logicKelulusanDasar.setDuitRM(idPermohonan);
		Vector listRM = logicKelulusanDasar.getBeanDuitRM();
		if (listRM.size() != 0){
			Hashtable hRM = (Hashtable) listRM.get(0);
			parameters.put("FEE",NumberToWords.convertTwoPart((String)hRM.get("txtFeeLesen")));
			parameters.put("WANG_CAGARAN",NumberToWords.convertTwoPart((String)hRM.get("txtWangCagaran")));
			parameters.put("WANG_NELAYAN",NumberToWords.convertTwoPart((String)hRM.get("txtWangAmanah")));
		}	
	}
}
