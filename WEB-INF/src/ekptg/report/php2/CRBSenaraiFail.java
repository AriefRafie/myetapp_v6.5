package ekptg.report.php2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.model.php2.FrmCRBSenaraiFailData;
import ekptg.report.EkptgReportServlet;

public class CRBSenaraiFail extends EkptgReportServlet {
	
	FrmCRBSenaraiFailData logic = new FrmCRBSenaraiFailData ();

	public CRBSenaraiFail () {
		super.setReportName("CRBSenaraiFail");
		super.setFolderName("php2\\CRB\\LAPORAN");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		
	}
}
