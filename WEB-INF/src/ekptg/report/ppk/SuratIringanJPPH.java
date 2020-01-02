package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;



public class SuratIringanJPPH extends EkptgReportServlet{
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public SuratIringanJPPH (){
		super.setReportName("SuratIringanJPPH") ;
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
//		if (parameters.get("idfail") == null) {
//			throw 
//		}
		super.flagMaklumatPegawai(true);
		parameters.put("daerahMohon",logic.getDaerahMohonByIdFail(parameters.get("idfail").toString(),parameters.get("flagReport").toString()));
		//parameters.put("daerahMohon",logic.getDaerahMohon(parameters.get("nofail").toString(),parameters.get("flagReport").toString()));
	}

}
