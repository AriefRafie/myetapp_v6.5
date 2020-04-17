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

public class BorangI extends EkptgReportServlet {
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	private IPerintah iPerintah= null;  

	public BorangI() {
		super.setReportName("BorangI");
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
		//System.out.print(parameters.get("nofail"));
		parameters.put("daerahMohon",logic.getDaerahMohon(parameters.get("nofail").toString(),parameters.get("flagReport").toString()));
		//hingga sini
		if(!String.valueOf(parameters.get("template")).equals("") || parameters.get("template") != null){
			//System.out.print("Borang I");
			super.setReportName(String.valueOf(parameters.get("template")));			
		}
		if(getPerintah().isPindahWasiat(String.valueOf(parameters.get("nofail")))){
			//System.out.println("surat pindah MT Wasiat");
			super.setReportName("BorangIWasiatKepPmhnn");			
		}
		if(getPerintah().isPerintahBatalWasiatPermohonan(String.valueOf(parameters.get("nofail")))){
			//System.out.print("Borang I Wasiat");
			super.setReportName("BorangIWasiat");			
		}
		if(getPerintah().isPerintahBatalWasiatPerbicaraan(String.valueOf(parameters.get("nofail")))){
			//System.out.print("Borang I Wasiat Perbicaraan");
			super.setReportName("BorangIWasiatPerbicaraan");			
		}
		
	

	}
		
	private IPerintah getPerintah(){
		if(iPerintah == null)
			iPerintah = new FrmPerintahBean();
		return iPerintah;
	}
	
	
}