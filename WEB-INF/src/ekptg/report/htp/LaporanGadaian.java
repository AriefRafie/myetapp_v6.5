package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanGadaian extends EkptgReportServlet {
	
	static Logger myLog = Logger.getLogger(LaporanGadaian.class);
	public LaporanGadaian() {
		super.setFolderName("htp");
		//mylog.info("LaporanUtamaHTP");
	}

	public void doProcessing(HttpServletRequest request,
		HttpServletResponse response, ServletContext context, Map<String, Object>  parameters)
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
		//mylog.info("doProcessing:idsuburusan="+idsuburusan);
		//mylog.info("doProcessing:bulan="+bulan);
		if (template.contains("Prestasi")) {
			if(Integer.parseInt(bulan)==1){
				template += bulan;
			}
		}
		myLog.info("doProcessing:template="+template);
		String bulantamat = (String)(parameters.get("bulantamat")==null?"0":parameters.get("bulantamat"));	
		//mylog.info("doProcessing:bulantamat="+bulantamat);
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
		myLog.info("doProcessing::BULANSEMASA-"+utils.getBulan(Integer.parseInt(bulan)));
		if(Integer.parseInt(bulan)==1){
			parameters.put("BULANSEBELUM","");
			//parameters.put("BULANMM","");
		}else	
			parameters.put("BULANSEBELUM",utils.getBulan(Integer.parseInt(bulan)-1));

		myLog.info("doProcessing::BULANSEBELUM-"+utils.getBulan(Integer.parseInt(bulan)));

		parameters.put("LAPORAN",tahun  +" ( "+utils.getBulan(Integer.parseInt(bulan))+ " )");
		if(!bulantamat.equals("0")){
			parameters.put("LAPORAN"," ( "+utils.getBulan(Integer.parseInt(bulan))+" "+tahun+ " - "+utils.getBulan(Integer.parseInt(bulantamat))+" "+tahunTmt+" )");
		}
		
	}
}
	
	