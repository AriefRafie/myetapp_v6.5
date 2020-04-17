package ekptg.report.ppk;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SuratIringanPerintahTerengganuSek17 extends EkptgReportServlet{
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public SuratIringanPerintahTerengganuSek17() {
		super.setReportName("SuratIringanPerintahTerengganuSek17");
		super.setFolderName("ppk");
	}
	
	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		Vector list = new Vector();
		Vector listPejabatJKPTG = new Vector();
		list.clear();
		listPejabatJKPTG.clear();
		
		
		logic.setMaklumatPegawai(parameters.get("idPegawai").toString(),parameters.get("flagReport").toString());
		list = logic.getBeanMaklumatPegawai();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			parameters.put("namaPegawai",h.get("nama").toString());
			parameters.put("jawatan",h.get("jawatan").toString());
			parameters.put("unit",h.get("unit").toString());
			parameters.put("negeri",h.get("negeri").toString());
			
			
		}

		parameters.put("daerahMohon",logic.getDaerahMohon(parameters.get("nofail").toString(),parameters.get("flagReport").toString()));
	}

}
