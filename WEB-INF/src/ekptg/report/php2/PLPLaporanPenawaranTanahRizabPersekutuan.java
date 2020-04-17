package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class PLPLaporanPenawaranTanahRizabPersekutuan extends EkptgReportServlet {

	FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();
	
	public PLPLaporanPenawaranTanahRizabPersekutuan() {
		
		super.setFolderName("php2\\PLP\\LAPORAN");
		
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String jenis = "";

		if (parameters.get("JENIS") != null){
			jenis = (String)parameters.get("JENIS");
		}
		System.out.println("jenis " + parameters.get("JENIS"));
		if(jenis.equals("1")){
			super.setReportName("PLPLaporanPenawaranTanahRizabPersekutuanKeseluruhan");
			System.out.println("dalam dah");
		}else if(jenis.equals("2")){
			super.setReportName("PLPLaporanPenawaranTanahRizabPersekutuanMengikutNegeri");
		}else if(jenis.equals("3")){
			super.setReportName("PLPLaporanPenawaranTanahRizabPersekutuanMengikutKem");
		}else{
			System.out.println("tak masuk if");
		}
	}
}
