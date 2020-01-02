package ekptg.view.ppk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

public class DisplayBlobKeputusanBorangC implements IServlet2{
	
static Logger myLogger = Logger.getLogger(DisplayBlobKeputusanBorangC.class);
	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			//Security checking should be added later
			Db db = null;
				 try {
			            db = new Db();
			            Connection con = db.getConnection();
			            PreparedStatement ps = con.prepareStatement("SELECT CONTENT,'application/pdf' AS jenis_mime from " +
			            		"TBLINTMTKEPUTUSAN WHERE IDKADBIRU = ? AND FLAG_AKTIF='Y' ");
			            String id = request.getParameter("id");
			            ps.setString(1,id);
			            ResultSet rs = ps.executeQuery();
			            if (rs.next()) {
				            Blob  b = rs.getBlob("content");
				            String contentType = rs.getString("jenis_mime");
				            response.setContentType(contentType);
				            response.setContentLength( (int) b.length());
				            InputStream is = b.getBinaryStream();
				            OutputStream os = response.getOutputStream();
				            byte buf[] = new byte[(int) b.length()];
				            is.read(buf);
				            os.write(buf);
				            os.close();
				            
				            
				            //aishah add 08022018 tambah logik untuk cater borang C telah dibuka.
				    		updateFlagBuka(id);
				          
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
	
	
	public void updateFlagBuka(String IDKADBIRU) throws Exception {
		
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("IDKADBIRU", IDKADBIRU);
			r.add("FLAG_BUKA", "Y");

			sql = r.getSQLUpdate("TBLINTMTKEPUTUSAN");
			System.out.println("sql=="+sql);
			
			stmt.executeUpdate(sql);
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}

			
		
}


