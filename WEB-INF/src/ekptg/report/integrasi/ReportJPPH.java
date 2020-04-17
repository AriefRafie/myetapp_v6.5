package ekptg.report.integrasi;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.DbException;
import ekptg.model.integrasi.FrmModelMyInfoNilaianHartaTakAlih;
import ekptg.report.EkptgReportServlet;

public class ReportJPPH extends EkptgReportServlet {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, ServletContext context, Map parameters) throws Exception, DbException {
		try {
			String PARAM_NAME = "", PARAM_VALUE = "";
			String RPT_NAME = "", RPT_FOLDER = "integrasi";
			String REPORT_TYPE = (String) parameters.get("rt");
			
			if ("1".equalsIgnoreCase(REPORT_TYPE)) {
				// HTAAH
				RPT_NAME = "JPPH_HTAAH";

				PARAM_VALUE = (String) parameters.get("ID_HTA");
				PARAM_NAME = "ID_HTA";
			} else if ("2".equalsIgnoreCase(REPORT_TYPE)) {
				// HTATH
				RPT_NAME = "JPPH_HTATH";
				
				PARAM_VALUE = (String) parameters.get("ID_HTA");
				PARAM_NAME = "ID_HTA";
			} else if ("3".equalsIgnoreCase(REPORT_TYPE)) {
				// HAK
				RPT_NAME = "JPPH_HAK";
				
				PARAM_VALUE = (String) parameters.get("ID_HA");
				PARAM_NAME = "ID_HA";
			}  else if ("5".equalsIgnoreCase(REPORT_TYPE)) {
				// SEWAAN
				RPT_NAME = "JPPH_SEWAAN";
				
				parameters.put("ID_PERMOHONAN", (String) parameters.get("ID_PERMOHONAN"));
				PARAM_VALUE = (String) parameters.get("ID_HM");
				PARAM_NAME = "ID_HM";
			} else {
				// carian maklumat permohonan
				RPT_NAME = "JPPH_CarianMaklumatPermohonan";
				String USER_ID = (String) parameters.get("uid");
				String NO_FAIL = (String) parameters.get("NO_FAIL");
				String NO_PERMOHONAN = (String) parameters.get("NO_PERMOHONAN");
				String TARIKH_HANTAR_DARI = (String) parameters.get("TARIKH_HANTAR_DARI");
				String TARIKH_HANTAR_KE = (String) parameters.get("TARIKH_HANTAR_KE");
				String TARIKH_SELESAI_DARI = (String) parameters.get("TARIKH_SELESAI_DARI");
				String TARIKH_SELESAI_KE = (String) parameters.get("TARIKH_SELESAI_KE");
				String STATUS_FAIL = (String) parameters.get("STATUS_FAIL");
				
				if (FrmModelMyInfoNilaianHartaTakAlih.populateNilaianHartaTerdahulu(USER_ID, NO_FAIL, NO_PERMOHONAN, TARIKH_HANTAR_DARI, TARIKH_HANTAR_KE, TARIKH_SELESAI_DARI, TARIKH_SELESAI_KE, STATUS_FAIL)) {
					PARAM_NAME = "uid";
					PARAM_VALUE = USER_ID;
				}
				
//				String TO_WRITE = "";
//				TO_WRITE = FrmModelMyInfoNilaianHartaTakAlih.printTextNilaianHartaTerdahulu(NO_FAIL, NO_PERMOHONAN, TARIKH_HANTAR_DARI, TARIKH_HANTAR_KE, TARIKH_SELESAI_DARI, TARIKH_SELESAI_KE, STATUS_FAIL);
//
//	            response.setContentType("application/octet-stream");
//	            response.setContentLength(TO_WRITE.length());
//	        	response.setHeader("Content-Disposition", "inline; filename=JPPH_" + FrmModelUtilitiesIntegration.getTimeStamp() + ".txt");
//				PrintWriter pw = response.getWriter();
//				pw.write(TO_WRITE);
//				pw.close();
				
//				parameters.put("NO_FAIL", NO_FAIL);
//				parameters.put("NO_PERMOHONAN", NO_PERMOHONAN);
//				parameters.put("TARIKH_HANTAR_DARI", TARIKH_HANTAR_DARI);
//				parameters.put("TARIKH_HANTAR_KE", TARIKH_HANTAR_KE);
//				parameters.put("TARIKH_SELESAI_DARI", TARIKH_SELESAI_DARI);
//				parameters.put("TARIKH_SELESAI_KE", TARIKH_SELESAI_KE);
//				parameters.put("STATUS_FAIL", STATUS_FAIL);
			}
			if (!"4".equalsIgnoreCase(REPORT_TYPE)) {
				PARAM_VALUE = sanitizeReportField(PARAM_VALUE);
				super.setReportName(RPT_NAME);
				super.setFolderName(RPT_FOLDER);
				parameters.put(PARAM_NAME, PARAM_VALUE);
			}
		} catch (Exception ex) {
			
		}
		
	}
	
	private static String sanitizeReportField(String REPORT_FIELD) throws Exception {
		String returnValue = "";
		
		try {
			returnValue = REPORT_FIELD;
			returnValue = returnValue == null ? "" : returnValue;
			returnValue = returnValue.replace("/", "/");
			returnValue = returnValue.replace("&", "&");
		} finally {
			
		}
		return returnValue;
	}
}