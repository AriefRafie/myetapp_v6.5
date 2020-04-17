package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BorangK1 extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public BorangK1() {
		super.setReportName("BorangK1");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	
//		Vector list = new Vector();
//		list.clear();
//	
//		logic.setMaklumatPegawai(parameters.get("idPegawai").toString(),parameters.get("flagReport").toString());
//		list = logic.getBeanMaklumatPegawai();
//		
//		if (list.size() != 0){
//		Hashtable h = (Hashtable) list.get(0);
//		parameters.put("namaPegawai",h.get("nama").toString());
//		parameters.put("jawatan",h.get("jawatan").toString());
//	}
//
//		parameters.put("daerahMohon",logic.getDaerahMohon(parameters.get("nofail").toString(),parameters.get("flagReport").toString()));
//
	}
}