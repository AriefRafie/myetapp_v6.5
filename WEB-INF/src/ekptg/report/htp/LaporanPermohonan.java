package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;
import ekptg.report.Utils;

public class LaporanPermohonan extends EkptgReportServlet {
	
	static Logger myLog = Logger.getLogger(ekptg.report.htp.LaporanPermohonan.class);
    private static String OS = System.getProperty("os.name").toLowerCase();
	public LaporanPermohonan() {
		//super.setReportName("LaporanRekodPermohonanNegeri817");
		super.setFolderName("htp");
		//myLog.info("LaporanPermohonan");
	}

	public void doProcessing(HttpServletRequest request
			,HttpServletResponse response
			,ServletContext context
			,Map<String, Object> parameters) throws Exception {
		//ekptg.report.Utils utils = new ekptg.report.Utils(); 
		String tahun =(String)parameters.get("TAHUN");
		myLog.info("doProcessing:tahun="+tahun);
		String tahunTmt =(String)parameters.get("TAHUN_TAMAT");
		myLog.info("doProcessing:tahunTmt="+tahunTmt);
		int idnegeri = Integer.parseInt((String)parameters.get("ID_NEGERI"));
		myLog.info("doProcessing:idnegeri="+idnegeri);
		int idsuburusan = Integer.parseInt((String)parameters.get("IDSUBURUSAN"));
		myLog.info("doProcessing:idsuburusan="+idsuburusan);
		String template =(String)parameters.get("template");
		String tmula =(String)parameters.get("BULANTAHUN");
		String ttamat =(String)parameters.get("BULANTAHUNTMT");
		String bulan = (String)(parameters.get("bulan")==null?"1":parameters.get("bulan"));
//		myLog.info("doProcessing:idsuburusan="+idsuburusan);
		myLog.info("doProcessing:bulan="+bulan);
		//if (template.contains("Prestasi")) {
		//	template += bulan;
			//mylog.info("doProcessing:-"+template);
		//}
		myLog.info("doProcessing:template="+template);
		String bulantamat = (String)(parameters.get("bulantamat")==null?"0":parameters.get("bulantamat"));	
		myLog.info("doProcessing:bulantamat="+bulantamat);
		int iddaerah = Integer.parseInt((String)parameters.get("ID"));
		int idkementerian = Integer.parseInt((String)parameters.get("ID_KEMENTERIAN"));
		int idagensi = Integer.parseInt((String)parameters.get("ID_AGENSI"));
		if(!template.equals("TIADA")){
			super.setReportName(template);	
		}
		String status = (String)parameters.get("STATUS");
		myLog.info("doProcessing:status="+status);
		parameters.put("TAHUN",tahun);
		parameters.put("TAHUN_TAMAT",tahunTmt);
		parameters.put("ID_NEGERI",idnegeri);
		parameters.put("IDSUBURUSAN",idsuburusan);
		parameters.put("BULAN",bulan);
		parameters.put("BULANMM",Utils.getBulanNum(Integer.parseInt(bulan)));
		if(!bulantamat.equals("0")){
			parameters.put("BULANTAMAT",Utils.getBulanNum(Integer.parseInt(bulantamat)));
		}
		parameters.put("BULANSEBELUMMM",Utils.getBulanSebelum(Integer.parseInt(bulan)));
		parameters.put("BULANTAHUN",tmula);
		parameters.put("BULANTAHUNTMT",ttamat);
		//parameters.put("ID_PEJABAT",idunit);
		parameters.put("ID",iddaerah);		
		parameters.put("ID_KEMENTERIAN",idkementerian);		
		parameters.put("ID_AGENSI",idagensi);		
		parameters.put("BULANSEMASA",Utils.getBulan(Integer.parseInt(bulan)));
		parameters.put("BULANSEBELUM",Utils.getBulan(Integer.parseInt(bulan)));
		parameters.put("STATUS",status);
		parameters.put("IDSUBURUSAN1",(String)parameters.get("IDSUBURUSAN1"));
		parameters.put("os",OS.indexOf("win") >= 0?"1":"0");

		//myLog.info("doProcessing::BULANSEMASA-"+utils.getBulan(Integer.parseInt(bulan)));
		//myLog.info("doProcessing::BULANSEBELUM-"+utils.getBulan(Integer.parseInt(bulan)));

		myLog.info("Sebelum LAPORAN");
		parameters.put("LAPORAN",tahun  +" ( "+Utils.getBulan(Integer.parseInt(bulan))+ " )");
		if(!bulantamat.equals("0")){
			parameters.put("LAPORAN"," ( "+Utils.getBulan(Integer.parseInt(bulan))+" "+tahun+ " - "+Utils.getBulan(Integer.parseInt(bulantamat))+" "+tahunTmt+" )");
		}
		
	}
}
	
	