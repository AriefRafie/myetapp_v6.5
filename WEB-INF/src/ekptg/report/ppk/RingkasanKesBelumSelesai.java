package ekptg.report.ppk;

import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class RingkasanKesBelumSelesai extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public RingkasanKesBelumSelesai() {
		super.setReportName("RingkasanKesBelumSelesai");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		Vector list = new Vector();
		list.clear();
		
//		logic.setMaklumatPegawai(parameters.get("idPegawai").toString(),parameters.get("flagReport").toString());
//		list = logic.getBeanMaklumatPegawai();
//		if (list.size() != 0){
//			Hashtable h = (Hashtable) list.get(0);
//			parameters.put("namaPegawai",h.get("nama").toString());
//			parameters.put("jawatan",h.get("jawatan").toString());
//		}
		
		parameters.put("NO_NEGERI",parameters.get("NO_NEGERI").toString());
		parameters.put("NO_UNIT",parameters.get("NO_UNIT").toString());
		parameters.put("NO_TAHUN",parameters.get("NO_TAHUN").toString());
		parameters.put("NAMA_BULAN",getBulanName(Integer.parseInt(parameters.get("NO_BULAN").toString())));
		parameters.put("N0_BLMSELESAI",parameters.get("N0_BLMSELESAI").toString());
		parameters.put("NO_JUMPRMHNN",parameters.get("NO_JUMPRMHNN").toString());

		
		
		if(parameters.get("NO_TUNGGAKAN") == null){
			parameters.put("NO_TUNGGAKAN","0");
		}
		else{
			parameters.put("NO_TUNGGAKAN",parameters.get("NO_TUNGGAKAN").toString());

		}
//		parameters.put("daerahMohon",logic.getDaerahMohon(parameters.get("nofail").toString(),parameters.get("flagReport").toString()));
		
	
	}
	
	public String getBulanName(int i){
		String m="";
		String[]names = {"JANUARI", "FEBRUARI", "MAC", "APRIL", "MEI", "JUN", "JULAI", "OGOS", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DISEMBER"};
		m = names[i-1];
		
		return m;
	}
}