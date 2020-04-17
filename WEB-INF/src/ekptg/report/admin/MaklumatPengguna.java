package ekptg.report.admin;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class MaklumatPengguna extends EkptgReportServlet
{
	public MaklumatPengguna() 
	{
		super.setFolderName("admin");
		super.setReportName("MaklumatPengguna");
		super.setSQL("select * from users");
	}
	
	public void doProcessing(HttpServletRequest request, HttpServletResponse response, 
			ServletContext context,Map parameters) throws Exception {

		String id = (String)parameters.get("id");
		if ("".equals(id)) {
			parameters.put("USERID", "AXXXX");
		} else {
			parameters.put("USERID","JKPTG/"+id);
		}
		
	}

}
