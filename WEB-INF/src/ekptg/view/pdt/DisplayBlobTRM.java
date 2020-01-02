package ekptg.view.pdt;

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

public class DisplayBlobTRM implements IServlet2{
	
	static Logger myLogger = Logger.getLogger(DisplayBlobTRM.class);
	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			//Security checking should be added later
			Db db = null;
			 try {
				 myLogger.info("MASUK DOWNLOAD LAMPIRAN ADUAN");
		            db = new Db();
		            String ID_WARTATRM = request.getParameter("ID_WARTATRM");
		            String type = request.getParameter("type");		            
		            Connection con = db.getConnection();
		            
		            String sql = "SELECT CONTENT_WARTA, JENIS_MIME_WARTA, CONTENT_PELAN, JENIS_MIME_PELAN FROM TBLPDTWARTATRM  WHERE ID_WARTATRM = ? ";
		            PreparedStatement ps = con.prepareStatement(sql);
		            
		            ps.setString(1,ID_WARTATRM);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		            	String BLOB_CONTENT = "CONTENT_WARTA";
		            	String BLOB_TYPE = "JENIS_MIME_WARTA";
		            	if(type.equals("PELAN"))
		            	{
		            		BLOB_CONTENT = "CONTENT_PELAN";
		            		BLOB_TYPE = "JENIS_MIME_PELAN";
		            	}           	
		            	
			            Blob  b = rs.getBlob(BLOB_CONTENT);
			            String contentType = rs.getString(BLOB_TYPE);
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
