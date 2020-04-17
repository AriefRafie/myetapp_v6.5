package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class FrmPaparDokumenByPermohonan extends EkptgReportServlet {
	
	static Logger myLog = Logger.getLogger(ekptg.report.ppk.FrmPaparDokumenByPermohonan.class);
	public FrmPaparDokumenByPermohonan() {
		super.setReportName("PPKSemakanSek8");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		//super.setFolderName("ppk");
		//super.setReportName((String)parameters.get("template"));
		//super.setFolderName((String)parameters.get("dirfolder"));
		String idPermohonan =(String)parameters.get("idpermohonan");
//		myLog.info(parameters.get("template"));
//		myLog.info(parameters.get("idpermohonan"));
		
		parameters.put("ID_PERMOHONAN",idPermohonan);

	}
}