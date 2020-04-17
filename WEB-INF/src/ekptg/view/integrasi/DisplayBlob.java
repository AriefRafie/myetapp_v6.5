package ekptg.view.integrasi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

public class DisplayBlob implements IServlet2 {
	static Logger myLogger = Logger.getLogger(DisplayBlob.class);

	public void doService(HttpServletRequest request, HttpServletResponse response,
	ServletContext context) throws IOException, ServletException {

		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			
			String PROC_TYPE = request.getParameter("ptype");
			
			if ("ETERIMAAN".equalsIgnoreCase(PROC_TYPE)) {
				PreparedStatement ps = con.prepareStatement("SELECT CONTENT, CONTENT_MIME FROM " +
				"TBLINTETERIMAAN WHERE ID_ETERIMAAN = ?");
				String IDFail = request.getParameter("IDFail");
				ps.setString(1, IDFail);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Blob b = rs.getBlob("CONTENT");
					String contentType = rs.getString("CONTENT_MIME");
					response.setContentType(contentType);
					response.setContentLength( (int) b.length());
					InputStream is = b.getBinaryStream();
					OutputStream os = response.getOutputStream();
					byte buf[] = new byte[(int) b.length()];
					is.read(buf);
					os.write(buf);
					os.close();
				} else {
					PrintWriter out = response.getWriter();
					String contextPath = request.getContextPath();
					out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/default.css\" />");
					out.println("<div style=\"width:150px\" class=\"error\">");
					out.println("Error: No Data.");
					out.println("</div>");
					out.close();
				}
			} else if ("JPBD".equalsIgnoreCase(PROC_TYPE)) {
				PreparedStatement ps = con.prepareStatement("SELECT CONTENT, CONTENT_MIME FROM " +
				"TBLINTDOKUMEN WHERE ID_DOKUMEN = ?");
				String IDFail = request.getParameter("IDFail");
				ps.setString(1, IDFail);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Blob b = rs.getBlob("CONTENT");
					String contentType = rs.getString("CONTENT_MIME");
					response.setContentType(contentType);
					response.setContentLength( (int) b.length());
					InputStream is = b.getBinaryStream();
					OutputStream os = response.getOutputStream();
					byte buf[] = new byte[(int) b.length()];
					is.read(buf);
					os.write(buf);
					os.close();
				} else {
					PrintWriter out = response.getWriter();
					String contextPath = request.getContextPath();
					out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/default.css\" />");
					out.println("<div style=\"width:150px\" class=\"error\">");
					out.println("Error: No Data.");
					out.println("</div>");
					out.close();
				}
			} else if ("APB".equalsIgnoreCase(PROC_TYPE)) {
				PreparedStatement ps = con.prepareStatement("SELECT AGENSI_IMEJ_KAWASAN, AGENSI_IMEJ_MIME FROM " +
				"TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = ?");
				String ID_ULASANTEKNIKAL = request.getParameter("ID_ULASANTEKNIKAL");
				ps.setString(1, ID_ULASANTEKNIKAL);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Blob b = rs.getBlob("AGENSI_IMEJ_KAWASAN");
					String contentType = rs.getString("AGENSI_IMEJ_MIME");
					response.setContentType(contentType);
					response.setContentLength( (int) b.length());
					InputStream is = b.getBinaryStream();
					OutputStream os = response.getOutputStream();
					byte buf[] = new byte[(int) b.length()];
					is.read(buf);
					os.write(buf);
					os.close();
				} else {
					PrintWriter out = response.getWriter();
					String contextPath = request.getContextPath();
					out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/default.css\" />");
					out.println("<div style=\"width:150px\" class=\"error\">");
					out.println("Error: No Data.");
					out.println("</div>");
					out.close();
				}
			}
		} catch(Exception ex) {
			myLogger.info(ex.getMessage());
		} finally {	
			if (db != null) db.close();
		}
	}
}