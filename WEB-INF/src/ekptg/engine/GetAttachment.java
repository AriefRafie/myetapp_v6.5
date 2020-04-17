package ekptg.engine;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

//open razman add new feature : attachment in bytes
//kena convert dlu fail jesper kedalam byte
public class GetAttachment extends EkptgReportServlet{	

	public byte[] getReportBytes(String namaFolder,String namaFail,HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		    return attachmentInbytes(namaFolder,namaFail, context, parameters);
		
	}
	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			Map<String, Object> parameters) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
