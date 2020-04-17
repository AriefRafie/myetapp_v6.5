package ekptg.view.htp.pembelian;
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

public class DisplayBlobPerjanjianAttch implements IServlet2{
	
static Logger myLogger = Logger.getLogger(DisplayBlobPerjanjianAttch.class);
	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			//Security checking should be added later
			Db db = null;
			 try {
		            db = new Db();
		            Connection con = db.getConnection();
		            PreparedStatement ps = con.prepareStatement("select content_derafperjanjian,mimetype,nama_fail from TBLHTPDOKUMENPERJANJIANATTACH where id_DokumenPerjanjian =? and id_dokumenperjanjianattach = ?");
		            String idDokumenPerjanjian = request.getParameter("idDokumenPerjanjian");
		            String idDokumenPerjanjianAttach = request.getParameter("idDokumenPerjanjianAttach");
		            ps.setString(1,idDokumenPerjanjian);
		            ps.setString(2, idDokumenPerjanjianAttach);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
			            Blob  b = rs.getBlob("content_derafperjanjian");
			            String contentType = rs.getString("mimetype");
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
		                out.println("Error!! No Data");
		                out.println("</div>");
		                out.close();
		            }
		        }
		        catch(Exception ex) {
		        	myLogger.info(ex.getMessage());
		        }
		        finally {
				      if (db != null) db.close();
			    }

			
		}

}
