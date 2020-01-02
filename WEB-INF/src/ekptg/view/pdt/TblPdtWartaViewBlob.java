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

public class TblPdtWartaViewBlob implements IServlet2 {
	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			//Security checking should be added later
			Db db = null;
			 try {
		            db = new Db();
		            Connection con = db.getConnection();
		            PreparedStatement ps = con.prepareStatement("select KANDUNGAN, NAMA_FAIL from " +
		            		"TBLPDTWARTA where ID_TBLPDTWARTA = ?");
		            String id = request.getParameter("id");
		            ps.setString(1,id);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
			            Blob  b = rs.getBlob("KANDUNGAN");
//			            String contentType = rs.getString("jenis_mime");
			            String namaFile = rs.getString("NAMA_FAIL");
//			            response.setContentType(contentType);
			            //IE Need this part.
			            response.setHeader("Content-Disposition", "inline; filename=\""+namaFile+"\"");
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
		             System.out.println(ex.getMessage());
		        }
		        finally {
				      if (db != null) db.close();
			    }

			
		}

}
