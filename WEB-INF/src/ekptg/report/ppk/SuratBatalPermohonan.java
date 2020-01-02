package ekptg.report.ppk;

import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ekptg.report.EkptgReportServlet;

public class SuratBatalPermohonan extends EkptgReportServlet {
	FrmPopupPilihPegawaiReportData logic = null;
	
	public SuratBatalPermohonan() {		
//		super.setReportName("SuratBatalPermohonanKpBaru");
//		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,HttpServletResponse response
		, ServletContext context, Map parameters) throws Exception {	 
		super.setReportName((String)parameters.get("report"));
		super.setFolderName("ppk");
		logic = new FrmPopupPilihPegawaiReportData();
		super.flagMaklumatPegawai(true);
		String report = (String)parameters.get("report");

		if (report.equals("SuratBatalPermohonan1")){	
			HttpSession session = request.getSession();
			String user_id = (String)session.getAttribute("_ekptg_user_id");

			String noFail = (String)parameters.get("nofail");
			Vector list = new Vector();
			list.clear();
			System.out.println("noFail = "+noFail);
			logic.updateStatusBatal(noFail,user_id);
			//list = logic.getBeanMaklumatPegawai();
		}

	}
	
	
}