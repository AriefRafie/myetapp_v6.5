package ekptg.report.ppk;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.ppk.perintah.FrmPerintahBean;
import ekptg.model.ppk.perintah.IPerintah;
import ekptg.report.EkptgReportServlet;

public class suratBatalBicaraSek17 extends EkptgReportServlet{
	
FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
private IPerintah iPerintah= null;  
	
	public suratBatalBicaraSek17() {
		super.setReportName("suratBatalBicaraSek17");
		super.setFolderName("ppk");
	}
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		//copy sini
		Vector list = new Vector();
		list.clear();
		
		logic.setMaklumatPegawai(parameters.get("idPegawai").toString(),parameters.get("flagReport").toString());
		list = logic.getBeanMaklumatPegawai();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			parameters.put("namaPegawai",h.get("nama").toString());
			parameters.put("jawatan",h.get("jawatan").toString());
		}

		parameters.put("daerahMohon",logic.getDaerahMohon(parameters.get("nofail").toString(),parameters.get("flagReport").toString()));
		//hingga sini
		if(getPerintah().isPerintahBatalWasiatPerbicaraan(String.valueOf(parameters.get("nofail")))){
			super.setReportName("SuratBatalPermohonanMT(Wasiat)Perbicaraan");			
		}
		
	}	

	private IPerintah getPerintah(){
		if(iPerintah == null)
			iPerintah = new FrmPerintahBean();
		return iPerintah;
	}
	
	
}
