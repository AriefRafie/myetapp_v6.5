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

public class SuratPindahMT extends EkptgReportServlet {
	
	//copy sini
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	private IPerintah iPerintah= null;  
	
	public SuratPindahMT() {
		super.setReportName("SuratPindahMT");
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

		System.out.print(parameters.get("nofail"));
		parameters.put("daerahMohon",logic.getDaerahMohon(parameters.get("nofail").toString(),parameters.get("flagReport").toString()));
		//hingga sini
		
		
		if(!String.valueOf(parameters.get("template")).equals("") || parameters.get("template") != null){
			//System.out.println("surat 1");
			super.setReportName(String.valueOf(parameters.get("template")));			
		}
		if(getPerintah().isPindahWasiat(String.valueOf(parameters.get("nofail")))){
			//System.out.println("surat 4");
			//System.out.println("surat pindah MT Wasiat");
			super.setReportName("SuratPindahMTWasiat");			
		}
		if(getPerintah().isPerintahBatalWasiatPermohonan(String.valueOf(parameters.get("nofail")))){
			//super.setReportName("SuratBatalPermohonan_MT(Wasiat)");			
			//System.out.println("surat 2");
			super.setReportName("SuratPindahMTII");			
		}
		if(getPerintah().isPerintahBatalWasiatPerbicaraan(String.valueOf(parameters.get("nofail")))){
			//System.out.println("surat 3");
			super.setReportName("SuratPindahMTWasiatPerbicaraan");			
		}
	
		
	}

	private IPerintah getPerintah(){
		if(iPerintah == null)
			iPerintah = new FrmPerintahBean();
		return iPerintah;
	}
	

}
