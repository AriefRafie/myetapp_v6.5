package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class BorangAOnlineDraf extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	static Logger myLogger = Logger.getLogger(BorangAOnlineDraf.class);
	
	public BorangAOnlineDraf() {
		myLogger.info("::::::::::::::: MASUK");
		super.setReportName("BorangAOnlineDraf_Malay");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idPermohonan = "";
		if (parameters.get("idPermohonan") != null){
			idPermohonan = parameters.get("idPermohonan").toString();
		}
		
		String id_negeri = "";
		if (parameters.get("id_negeri") != null){
			id_negeri = parameters.get("id_negeri").toString();
		}
		myLogger.info("::::::::::::::: id_negeri "+id_negeri);
		
		String id_daerah = "";
		if (parameters.get("id_daerah") != null){
			id_daerah = parameters.get("id_daerah").toString();
		}
		myLogger.info("::::::::::::::: id_daerah "+id_daerah);
		
		//Security Check
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String user_type = (String)session.getAttribute("_ekptg_user_type");
		
		parameters.put("baluDuda",logic.getBaluDuda(idPermohonan));
		
	}
}