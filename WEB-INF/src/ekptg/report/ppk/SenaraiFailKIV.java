package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class SenaraiFailKIV extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public SenaraiFailKIV() {
		super.setReportName("SenaraiFailKIV");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {
		
		
		
		String userId =(String)parameters.get("userId");
		String txtTarikhMatang =(String)parameters.get("txtTarikhMatang");
		String txtTarikhPerintah =(String)parameters.get("txtTarikhPerintah");
		String seksyen = (String)parameters.get("seksyen");
	
		parameters.put("userId",userId);
		parameters.put("tarikh_matang",txtTarikhMatang);
		parameters.put("tarikh_perintah",txtTarikhPerintah);
		parameters.put("tarikh_perintah",txtTarikhPerintah);
		parameters.put("seksyen",seksyen);
		
	}
}
