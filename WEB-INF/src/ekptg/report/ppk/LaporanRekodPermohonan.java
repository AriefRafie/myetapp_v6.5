package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanRekodPermohonan extends EkptgReportServlet {
	
	static Logger myLog = Logger.getLogger(ekptg.report.ppk.LaporanRekodPermohonan.class);
	public LaporanRekodPermohonan() {
		super.setReportName("LaporanRekodPermohonanNegeri817");
		super.setFolderName("ppk");
		
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		ekptg.report.Utils utils = new ekptg.report.Utils(); 

		String tahun =(String)parameters.get("TAHUN");
		int idnegeri = Integer.parseInt((String)parameters.get("ID_NEGERI"));
		int idsuburusan = Integer.parseInt((String)parameters.get("IDSUBURUSAN"));
		String template =(String)parameters.get("template");
		String tmula =(String)parameters.get("BULANTAHUN");
		String ttamat =(String)parameters.get("BULANTAHUNTMT");
		String bulan = (String)(parameters.get("bulan")==null?"0":parameters.get("bulan"));
		String bulantamat = (String)(parameters.get("bulantamat")==null?"0":parameters.get("bulantamat"));	
		int idunit = Integer.parseInt((String)parameters.get("ID_PEJABAT"));
		int iddaerah = Integer.parseInt((String)parameters.get("ID"));
		//mylog.info("LaporanRekodPermohonan:doProcessing::idunit-"+idunit);
		//mylog.info("LaporanRekodPermohonan:doProcessing::iddaerah-"+iddaerah);
	
		if(!template.equals("TIADA")){
			super.setReportName(template);	
		}
		parameters.put("BULAN",bulan);
		parameters.put("TAHUN",tahun);
		parameters.put("ID_NEGERI",idnegeri);
		parameters.put("IDSUBURUSAN",idsuburusan);
		parameters.put("ID_SUBURUSAN",idsuburusan);
		parameters.put("BULANTAHUN",tmula);
		parameters.put("BULANTAHUNTMT",ttamat);
		parameters.put("ID_PEJABAT",idunit);
		parameters.put("ID",iddaerah);			
		
		if(!bulan.equals("0")){
			parameters.put("LAPORAN",tahun  +" ( "+getBulanName(Integer.parseInt(bulan))+ " )");

		}
		
		if(!bulantamat.equals("0")){
			parameters.put("LAPORAN"," ( "+getBulanName(Integer.parseInt(bulan))+ "-"+getBulanName(Integer.parseInt(bulantamat))+" )");
			parameters.put("BULANTAMAT",bulantamat);
			parameters.put("TAHUNTAMAT",ttamat.substring(3,7));
		
		}
					
	}
	
	public String getBulanName(int i){
		String m="";
		String[]names = {"JANUARI", "FEBRUARI", "MAC", "APRIL", "MEI", "JUN", "JULAI", "OGOS", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DISEMBER"};
		m = names[i-1];	
		return m;
	
	}
	
	
}
	
	