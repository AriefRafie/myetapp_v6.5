package ekptg.report.htp.permohonan;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanPermohonan extends EkptgReportServlet {
	
	static Logger myLog = Logger.getLogger(ekptg.report.htp.permohonan.LaporanPermohonan.class);
	public LaporanPermohonan() {
		//super.setReportName("LaporanRekodPermohonanNegeri817");
		super.setFolderName("htp");
		myLog.info("LaporanPermohonan");
}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map<String, Object> parameters)
			throws Exception {
		//ekptg.report.Utils utils = new ekptg.report.Utils(); 
		String idUrusan = String.valueOf(parameters.get("ID_URUSAN"));
		String idNegeri = String.valueOf(parameters.get("ID_NEGERI"));
		String idDaerah = String.valueOf(parameters.get("ID"));
		
		String tahun =(String)parameters.get("TAHUN");
		String tahunTmt =(String)parameters.get("TAHUN_TAMAT");
		//int idnegeri = Integer.parseInt((String)parameters.get("ID_NEGERI"));
		//int idsuburusan = Integer.parseInt((String)parameters.get("IDSUBURUSAN"));
		String template =(String)parameters.get("template");
		String tmula =(String)parameters.get("BULANTAHUN");
		String ttamat =(String)parameters.get("BULANTAHUNTMT");
		String bulan = (String)(parameters.get("bulan")==null?"1":parameters.get("bulan"));
		//myLog.info("doProcessing:idsuburusan="+idsuburusan);
		myLog.info("doProcessing:bulan="+bulan);
		//if (template.contains("Prestasi")) {
		//	template += bulan;
			//mylog.info("doProcessing:-"+template);
		//}
		myLog.info("doProcessing:template="+template);
		String bulantamat = (String)(parameters.get("bulantamat")==null?"0":parameters.get("bulantamat"));	
		myLog.info("doProcessing:bulantamat="+bulantamat);
		//int iddaerah = Integer.parseInt((String)parameters.get("ID"));
		//int idkementerian = Integer.parseInt((String)parameters.get("ID_KEMENTERIAN"));

		String idKementerian = String.valueOf(parameters.get("ID_KEMENTERIAN"));
		int idagensi = Integer.parseInt((String)parameters.get("ID_AGENSI"));
		if(!template.equals("TIADA")){
			super.setReportName(template);	
		}
		String status = (String)parameters.get("STATUS");
		
		parameters.put("ID_URUSAN",idUrusan);
		
		parameters.put("TAHUN",tahun);
		parameters.put("TAHUN_TAMAT",tahunTmt);
		parameters.put("ID_NEGERI",idNegeri);
		//parameters.put("IDSUBURUSAN",idsuburusan);
		//parameters.put("BULAN",bulan);			
		parameters.put("BULAN",bulan.equals("00")?bulan:ekptg.report.Utils.getBulanNum(Integer.parseInt(bulan)));
		parameters.put("BULANMM",bulan.equals("00")?bulan:ekptg.report.Utils.getBulanNum(Integer.parseInt(bulan)));
		if(bulan.equals("00")){
				bulan = "1";	}
		if(!bulantamat.equals("0")){
			parameters.put("BULANTAMAT",ekptg.report.Utils.getBulanNum(Integer.parseInt(bulantamat)));
			parameters.put("BULAN_TAMAT",ekptg.report.Utils.getBulanNum(Integer.parseInt(bulantamat)));
		}
		parameters.put("BULANSEBELUMMM",ekptg.report.Utils.getBulanSebelum(Integer.parseInt(bulan)));
		parameters.put("BULANTAHUN",tmula);
		parameters.put("BULANTAHUNTMT",ttamat);
		//parameters.put("ID_PEJABAT",idunit);
		parameters.put("ID",idDaerah);		
		parameters.put("ID_KEMENTERIAN",idKementerian);		
		parameters.put("ID_AGENSI",idagensi);		
		parameters.put("BULANSEMASA",ekptg.report.Utils.getBulan(Integer.parseInt(bulan)));
		parameters.put("BULANSEBELUM",ekptg.report.Utils.getBulan(Integer.parseInt(bulan)));
		parameters.put("STATUS",status);
		parameters.put("IDSUBURUSAN1",(String)parameters.get("IDSUBURUSAN1"));

		myLog.info("doProcessing::BULANSEMASA-"+ekptg.report.Utils.getBulan(Integer.parseInt(bulan)));
		myLog.info("doProcessing::BULANSEBELUM-"+ekptg.report.Utils.getBulan(Integer.parseInt(bulan)));

		parameters.put("LAPORAN",tahun  +" ( "+ekptg.report.Utils.getBulan(Integer.parseInt(bulan))+ " )");
		if(!bulantamat.equals("0")){
			parameters.put("LAPORAN"," ( "+ekptg.report.Utils.getBulan(Integer.parseInt(bulan))+" "
					+tahun+ " - "+ekptg.report.Utils.getBulan(Integer.parseInt(bulantamat))+" "+tahunTmt+" )");
		}
		
	}
}
	
	