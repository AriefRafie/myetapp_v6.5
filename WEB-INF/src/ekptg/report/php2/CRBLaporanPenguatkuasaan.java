package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmREVPopupCetakLaporanData;
import ekptg.report.EkptgReportServlet;

public class CRBLaporanPenguatkuasaan extends EkptgReportServlet {

	FrmREVPopupCetakLaporanData logic = new FrmREVPopupCetakLaporanData();
	
	public CRBLaporanPenguatkuasaan() {
		
		super.setFolderName("php2\\CRB\\LAPORAN");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String jenis = "";
		String status = "";

		if (parameters.get("JENIS") != null){
			jenis = (String)parameters.get("JENIS");
		}
		
		if (parameters.get("STATUS") != null){
			status = (String)parameters.get("STATUS");
		}
		System.out.println("jenis " + parameters.get("JENIS"));

		if(status.equals("1")){
			if(jenis.equals("1")){
				super.setReportName("CRBLaporanPenguatkuasaanKeseluruhan");
				System.out.println("dalam dah");
			}else if(jenis.equals("2")){
				super.setReportName("CRBLaporanPenguatkuasaanMengikutNegeri");
			}else if(jenis.equals("3")){
				super.setReportName("CRBLaporanPenguatkuasaanMengikutKem");
			}
		}else if(status.equals("2")){
			//kena buat report status yg telah selesai
			if(jenis.equals("1")){
				super.setReportName("CRBLaporanPenguatkuasaanKeseluruhanSelesai");
				System.out.println("dalam dah");
			}else if(jenis.equals("2")){
				super.setReportName("CRBLaporanPenguatkuasaanMengikutNegeriSelesai");
			}else if(jenis.equals("3")){
				super.setReportName("CRBLaporanPenguatkuasaanMengikutKemSelesai");
			}
		}else{
			//kena buat report status yg telah selesai
			if(jenis.equals("1")){
				super.setReportName("CRBLaporanPenguatkuasaanKeseluruhanBatal");
				System.out.println("dalam dah");
			}else if(jenis.equals("2")){
				super.setReportName("CRBLaporanPenguatkuasaanMengikutNegeriBatal");
			}else if(jenis.equals("3")){
				super.setReportName("CRBLaporanPenguatkuasaanMengikutKemBatal");
			}
		}
	}
}
