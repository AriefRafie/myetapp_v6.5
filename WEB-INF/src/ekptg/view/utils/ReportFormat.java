package ekptg.view.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

public class ReportFormat implements IServlet2 {
	
//extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(ReportFormat.class);
	
	//ekptg.view.utils.ReportFormat
	//My Report
//	public String doTemplate2() throws Exception {
//		HttpSession session = this.request.getSession();
//		
//		String submit = getParam("command"); // First Level - default by AjaxBasedModule Call
//		String format = getParam("format");
//		String sessionformat = (String)session.getAttribute("rFormat");
//		if ( sessionformat == null || "".equals(sessionformat) ) {
//			if (format == "") format = "PDF";
//			session.setAttribute("rFormat", format);
//		} else {
//			if (!format.equals(sessionformat)) {
//				if ("".equals(format)) format = sessionformat;
//				session.setAttribute("rFormat", format);
//			} else {
//				format = sessionformat;
//			}
//		}
//		this.context.put("rFormat",format);
//		return "app/utils/frmReportFormat.jsp";
//	}

	 public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context)
	  throws IOException, ServletException
	  {
		 HttpSession session = request.getSession();
		 PrintWriter out = response.getWriter();
		 String contextPath = request.getContextPath();
		 String format = request.getParameter("format");
		 session.setAttribute("rFormat", format);
		 
//		 if ("PDF".equals(format)) {
//			 out.println("PDF | ");
//			 out.println("<a href=\"#\" onClick=\"javascript:doChangeReport('RTF');\"><u>RTF</u></a>");
//		 } else {
//			 out.println("<a href=\"#\" onClick=\"javascript:doChangeReport('PDF');\"><u>PDF</u></a> | ");
//			 out.println("RTF");
// 		 }
		 
		 
		 String[] report = new String[]{"PDF","RTF","EXCEL"};
		 for (String s : report) {
			 if (s.equals(format)) {
				 out.println(s+" |");
			 } else {
				 out.println("<a href=\"#\" onClick=\"javascript:doChangeReport('"+s+"');\"><u>"+s+"</u></a> |");
			 }
		 }

		 
	  }
	
}
