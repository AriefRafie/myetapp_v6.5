package ekptg.report.ppk;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SuratIringanNotisTerengganu extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public SuratIringanNotisTerengganu() {
		super.setReportName("SuratIringanNotisTerengganu");
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
		}

		parameters.put("daerahMohon",logic.getDaerahMohon(parameters.get("nofail").toString(),parameters.get("flagReport").toString()));
		
		//get Alamat Notis Akan Dihantar
//		logic.setMaklumatPejabatJKPTG(parameters.get("idPejabatJKPTG").toString());
//		listPejabatJKPTG = logic.getBeanMaklumatPejabatJKPTG();
//		if (listPejabatJKPTG.size() != 0){
//			Hashtable hPejabat = (Hashtable) listPejabatJKPTG.get(0);
//			parameters.put("jawatannotis",hPejabat.get("jawatan").toString()== null?"":hPejabat.get("jawatan").toString());
//			parameters.put("unitnotis",hPejabat.get("unit").toString());
//			parameters.put("alamat1notis",hPejabat.get("alamat1").toString());
//			parameters.put("alamat2notis",hPejabat.get("alamat2").toString());
//			parameters.put("alamat3notis",hPejabat.get("alamat3").toString()==null?"":hPejabat.get("alamat3").toString());
//			parameters.put("poskodnotis",hPejabat.get("poskod").toString());
//			parameters.put("daerahnotis",hPejabat.get("daerah").toString());
//			parameters.put("negerinotis",hPejabat.get("negeri").toString());
//		}
		
		
	
	}
}