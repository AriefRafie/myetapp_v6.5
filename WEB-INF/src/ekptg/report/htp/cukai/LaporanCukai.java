package ekptg.report.htp.cukai;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanCukai extends EkptgReportServlet {
	
	static Logger mylog = Logger.getLogger(LaporanCukai.class);
	public LaporanCukai() {
		super.setReportName("HTPCukaiMemoSenarai");
		super.setFolderName("htp");
		mylog.info("LaporanUtamaHTP");
}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		ekptg.report.Utils utils = new ekptg.report.Utils(); 
		String tahun =(String)parameters.get("TAHUN");
		//String tahunTmt =(String)parameters.get("TAHUN_TAMAT");
		int idnegeri = Integer.parseInt((String)parameters.get("ID_NEGERI"));
		String template =(String)parameters.get("template");
		/*int idsuburusan = Integer.parseInt((String)parameters.get("IDSUBURUSAN"));
		String tmula =(String)parameters.get("BULANTAHUN");
		String ttamat =(String)parameters.get("BULANTAHUNTMT");
		String bulan = (String)(parameters.get("bulan")==null?"0":parameters.get("bulan"));
		mylog.info("doProcessing:idsuburusan-"+idsuburusan);
		mylog.info("doProcessing:bulan-"+bulan);
		if(idsuburusan==38){
			if(bulan.equals("01")){
				super.setReportName((String)parameters.get("template")+bulan);
				mylog.info("doProcessing:-"+parameters.get("template")+bulan);
			}
		}
		String bulantamat = (String)(parameters.get("bulantamat")==null?"0":parameters.get("bulantamat"));	
		//int idunit = Integer.parseInt((String)parameters.get("ID_PEJABAT"));
		int iddaerah = Integer.parseInt((String)parameters.get("ID"));
		int idkementerian = Integer.parseInt((String)parameters.get("ID_KEMENTERIAN"));
		int idagensi = Integer.parseInt((String)parameters.get("ID_AGENSI"));
	
		*/
		if(!template.equals("TIADA")){
			super.setReportName(template);	
		}
		parameters.put("TAHUN",tahun);
		//parameters.put("TAHUN_TAMAT",tahunTmt);
		parameters.put("ID_NEGERI",idnegeri);
		/*parameters.put("IDSUBURUSAN",idsuburusan);
		parameters.put("BULAN",bulan);
		parameters.put("BULANTAHUN",tmula);
		parameters.put("BULANTAHUNTMT",ttamat);
		//parameters.put("ID_PEJABAT",idunit);
		parameters.put("ID",iddaerah);		
		parameters.put("ID_KEMENTERIAN",idkementerian);		
		parameters.put("ID_AGENSI",idagensi);		
		parameters.put("BULANSEMASA",utils.getBulan(Integer.parseInt(bulan)));
		if(!bulan.equals("0")){
			parameters.put("BULANSEBELUM",utils.getBulan(Integer.parseInt(bulan)-1));
		}else{
			parameters.put("BULANSEBELUM",utils.getBulan(Integer.parseInt(bulan)));
		}
		mylog.info("doProcessing::BULANSEMASA-"+utils.getBulan(Integer.parseInt(bulan)));
		mylog.info("doProcessing::BULANSEBELUM-"+utils.getBulan(Integer.parseInt(bulan)));

		parameters.put("LAPORAN",tahun  +" ( "+utils.getBulan(Integer.parseInt(bulan))+ " )");
		if(!bulantamat.equals("0")){
			parameters.put("LAPORAN"," ( "+utils.getBulan(Integer.parseInt(bulan))+ "-"+utils.getBulan(Integer.parseInt(bulantamat))+" )");
		}*/
		
	}
}
	
	