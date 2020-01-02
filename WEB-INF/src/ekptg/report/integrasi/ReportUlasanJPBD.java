package ekptg.report.integrasi;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.DbException;
import ekptg.report.EkptgReportServlet;

public class ReportUlasanJPBD extends EkptgReportServlet {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception, DbException {
		super.setReportName("JPBD_LAMPIRAN_A1");
		super.setFolderName("integrasi");

		String ID_HAKMILIK = (String) parameters.get("ID_HAKMILIK");
		ID_HAKMILIK = sanitizeReportField(ID_HAKMILIK);
		
		parameters.put("ID_HAKMILIK", ID_HAKMILIK);
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