package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanUtamaHTPPerolehan extends EkptgReportServlet {
	
	static Logger myLog = Logger.getLogger(LaporanUtamaHTPPerolehan.class);
	public LaporanUtamaHTPPerolehan() {
		//super.setReportName("LaporanRekodPermohonanNegeri817");
		super.setFolderName("htp");

	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		ekptg.report.Utils utils = new ekptg.report.Utils(); 
		String tahun =(String)parameters.get("TAHUN");
		String tahunTmt =(String)parameters.get("TAHUN_TAMAT");
		int idnegeri = Integer.parseInt((String)parameters.get("ID_NEGERI"));
		int idsuburusan = Integer.parseInt((String)parameters.get("IDSUBURUSAN"));
		String template =(String)parameters.get("template");
		String tmula =(String)parameters.get("BULANTAHUN");
		String ttamat =(String)parameters.get("BULANTAHUNTMT");
		String bulan = (String)(parameters.get("bulan")==null?"1":parameters.get("bulan"));
		//myLog.info("doProcessing:idsuburusan="+idsuburusan);
		//myLog.info("doProcessing:bulan="+bulan);
		if (template.contains("Prestasi")) {
			template += bulan;
			//mylog.info("doProcessing:-"+template);
		}
		//myLog.info("doProcessing:template="+template);
		String bulantamat = (String)(parameters.get("bulantamat")==null?"0":parameters.get("bulantamat"));	
		//myLog.info("doProcessing:bulantamat="+bulantamat);
		int iddaerah = Integer.parseInt((String)parameters.get("ID"));
		int idkementerian = Integer.parseInt((String)parameters.get("ID_KEMENTERIAN"));
		int idagensi = Integer.parseInt((String)parameters.get("ID_AGENSI"));
		if(!template.equals("TIADA")){
			super.setReportName(template);	
		}
		parameters.put("TAHUN",tahun);
		parameters.put("TAHUN_TAMAT",tahunTmt);
		parameters.put("ID_NEGERI",idnegeri);
		parameters.put("IDSUBURUSAN",idsuburusan);
		parameters.put("BULAN",bulan);
		parameters.put("BULANMM",utils.getBulanNum(Integer.parseInt(bulan)));
		if(!bulantamat.equals("0")){
			parameters.put("BULANTAMAT",utils.getBulanNum(Integer.parseInt(bulantamat)));
		}
		parameters.put("BULANSEBELUMMM",utils.getBulanSebelum(Integer.parseInt(bulan)));
		parameters.put("BULANTAHUN",tmula);
		parameters.put("BULANTAHUNTMT",ttamat);
		//parameters.put("ID_PEJABAT",idunit);
		parameters.put("ID",iddaerah);		
		parameters.put("ID_KEMENTERIAN",idkementerian);		
		parameters.put("ID_AGENSI",idagensi);		
		parameters.put("BULANSEMASA",utils.getBulan(Integer.parseInt(bulan)));
		parameters.put("BULANSEBELUM",utils.getBulan(Integer.parseInt(bulan)));
		//myLog.info("doProcessing::BULANSEMASA-"+utils.getBulan(Integer.parseInt(bulan)));
		myLog.info("doProcessing::PEROLEHAN="+parameters.get("PEROLEHAN"));
		
		parameters.put("PEROLEHAN",parameters.get("PEROLEHAN"));		
	
		parameters.put("LAPORAN",tahun  +" ( "+utils.getBulan(Integer.parseInt(bulan))+ " )");
		if(!bulantamat.equals("0")){
			parameters.put("LAPORAN"," ( "+utils.getBulan(Integer.parseInt(bulan))+" "+tahun+ " - "+utils.getBulan(Integer.parseInt(bulantamat))+" "+tahunTmt+" )");
		}
		
	}
}
	
	