package ekptg.report.integrasi;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.DbException;
import ekptg.report.EkptgReportServlet;

public class ReportNilaianJPPH extends EkptgReportServlet {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception, DbException {
		String REPORT_TYPE = (String) parameters.get("rt");
		
		if ("1".equalsIgnoreCase(REPORT_TYPE)) {
			// HTAAH
			super.setReportName("JPPH_HTAAH");
			super.setFolderName("integrasi");

			String ID_HTA = (String) parameters.get("ID_HTA");
			ID_HTA = sanitizeReportField(ID_HTA);
			parameters.put("ID_HTA", ID_HTA);
		} else if ("2".equalsIgnoreCase(REPORT_TYPE)) {
			// HTATH
			super.setReportName("JPPH_HTATH");
			super.setFolderName("integrasi");
			
			String ID_HTA = (String) parameters.get("ID_HTA");
			ID_HTA = sanitizeReportField(ID_HTA);
			parameters.put("ID_HTA", ID_HTA);
		} else {
			// HAK
			super.setReportName("JPPH_HAK");
			super.setFolderName("integrasi");
			
			String ID_HA = (String) parameters.get("ID_HA");
			ID_HA = sanitizeReportField(ID_HA);
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