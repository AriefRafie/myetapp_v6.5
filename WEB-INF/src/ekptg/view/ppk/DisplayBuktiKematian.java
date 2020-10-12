package ekptg.view.ppk;

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

public class DisplayBuktiKematian implements IServlet2{
	
static Logger myLogger = Logger.getLogger(DisplayBuktiKematian.class);
	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			//Security checking should be added later
			Db db = null;
				 try {
			            db = new Db();
			            Connection con = db.getConnection();
			            PreparedStatement ps = con.prepareStatement("select kandungan,format from (select * from tblppkdokumensimati  where id_simati = ? AND id_jenisdokumen = ? order by id_dokumen desc) where rownum = 1");
			            String id = request.getParameter("id");
			            String jenisDoc = request.getParameter("jenisDoc");
			            ps.setString(1,id);
			            ps.setString(2,jenisDoc);
			            ResultSet rs = ps.executeQuery();
			            if (rs.next()) {
				            Blob  b = rs.getBlob("kandungan");
				            String contentType = rs.getString("format");
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
				
			}// setKeputusanKM

			
		
}


