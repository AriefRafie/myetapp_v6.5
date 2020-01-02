package ekptg.view.integrasi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

import ekptg.model.integrasi.FrmModelEKadaster;
import ekptg.model.integrasi.FrmModelJUPEM;
import ekptg.model.integrasi.FrmModelMaklumatKebangkrapan;
import ekptg.model.integrasi.FrmModelUtilitiesIntegration;

public class DisplayText implements IServlet2 {
	static Logger myLogger = Logger.getLogger(DisplayText.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {		
		FrmModelEKadaster modelEKadaster = new FrmModelEKadaster();
		FrmModelJUPEM modelJUPEM = new FrmModelJUPEM();
		
		String action2 = request.getParameter("action2");
		Db db = null;
		
		try {
			if ("generateXML".equalsIgnoreCase(action2)) {
				String IDFail = request.getParameter("IDFail");
				String xml = modelEKadaster.generateXML(IDFail);
	            response.setContentType("application/octet-stream");
	            response.setContentLength(xml.length());
	        	response.setHeader("Content-Disposition", "inline; filename=" + IDFail + ".xml");
				PrintWriter pw = response.getWriter();
				pw.write(xml);
				pw.close();
			} else if ("displayEKadaster".equalsIgnoreCase(action2)) {
				// display file content
				db = new Db();
				Connection con = db.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT CONTENT, CONTENT_MIME FROM " +
				"TBLINTEKADASTER WHERE ID_EKADASTER = ?");
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
			} else if ("generatePUB".equalsIgnoreCase(action2)) {
				Vector v = new Vector();
				Hashtable h = new Hashtable();
				String ID_PERMOHONAN = request.getParameter("ID_PERMOHONAN");
				String ID_HAKMILIK = request.getParameter("ID_HAKMILIK");
				h.put("ID_PERMOHONAN", ID_PERMOHONAN);
				h.put("ID_HAKMILIK", ID_HAKMILIK);
				h.put("GEN_TYPE", "PUB");
				v.add(h);
				String FILENAME = modelJUPEM.generateFileName(v);
				String rVal = modelJUPEM.generateText(v);
	            response.setContentType("application/octet-stream");
	            response.setContentLength(rVal.length());
	        	response.setHeader("Content-Disposition", "inline; filename=" + FILENAME + ".pub");
				PrintWriter pw = response.getWriter();
				pw.write(rVal);
				pw.close();
			} else if ("generatePUD".equalsIgnoreCase(action2)) {
				Vector v = new Vector();
				Hashtable h = new Hashtable();
				String ID_PERMOHONAN = request.getParameter("ID_PERMOHONAN");
				String ID_HAKMILIK = request.getParameter("ID_HAKMILIK");
				h.put("ID_PERMOHONAN", ID_PERMOHONAN);
				h.put("ID_HAKMILIK", ID_HAKMILIK);
				h.put("GEN_TYPE", "PUD");
				v.add(h);
				String FILENAME = modelJUPEM.generateFileName(v);
				String rVal = modelJUPEM.generateText(v);
	            response.setContentType("application/octet-stream");
	            response.setContentLength(rVal.length());
	        	response.setHeader("Content-Disposition", "inline; filename=" + FILENAME + ".pud");
				PrintWriter pw = response.getWriter();
				pw.write(rVal);
				pw.close();
			} else if ("generatePUL".equalsIgnoreCase(action2)) {
				Vector v = new Vector();
				Hashtable h = new Hashtable();
				String ID_PERMOHONAN = request.getParameter("ID_PERMOHONAN");
				String ID_HAKMILIK = request.getParameter("ID_HAKMILIK");
				h.put("ID_PERMOHONAN", ID_PERMOHONAN);
				h.put("ID_HAKMILIK", ID_HAKMILIK);
				h.put("GEN_TYPE", "PUL");
				v.add(h);
				String FILENAME = modelJUPEM.generateFileName(v);
				String rVal = modelJUPEM.generateText(v);
	            response.setContentType("application/octet-stream");
	            response.setContentLength(rVal.length());
	        	response.setHeader("Content-Disposition", "inline; filename=" + FILENAME + ".pul");
				PrintWriter pw = response.getWriter();
				pw.write(rVal);
				pw.close();
			} else if ("generateMDI".equalsIgnoreCase(action2)) {
				String NO_KP = FrmModelMaklumatKebangkrapan.generateText(false);
				if (!"".equalsIgnoreCase(NO_KP.trim())) {
		            response.setContentType("application/octet-stream");
		            response.setContentLength(NO_KP.length());
		        	response.setHeader("Content-Disposition", "inline; filename=" + FrmModelUtilitiesIntegration.getTimeStamp() + ".csv");
					PrintWriter pw = response.getWriter();
					pw.write(NO_KP);
					pw.close();
				}
			} else if ("generateMDIAll".equalsIgnoreCase(action2)) {
				String NO_KP = FrmModelMaklumatKebangkrapan.generateText(true);
				if (!"".equalsIgnoreCase(NO_KP.trim())) {
		            response.setContentType("application/octet-stream");
		            response.setContentLength(NO_KP.length());
		        	response.setHeader("Content-Disposition", "inline; filename=" + FrmModelUtilitiesIntegration.getTimeStamp() + ".csv");
					PrintWriter pw = response.getWriter();
					pw.write(NO_KP);
					pw.close();
				}
			}
		} catch(Exception ex) {
			myLogger.info(ex.getMessage());
		} finally {
			if (db != null) db.close();
		}
	}
}