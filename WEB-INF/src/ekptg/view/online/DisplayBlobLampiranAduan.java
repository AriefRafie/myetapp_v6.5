package ekptg.view.online;

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

import sun.misc.BASE64Decoder;

public class DisplayBlobLampiranAduan implements IServlet2{
	
	static Logger myLogger = Logger.getLogger(DisplayBlobLampiranAduan.class);
	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			//Security checking should be added later
			Db db = null;
			 try {
				 myLogger.info("MASUK DOWNLOAD LAMPIRAN ADUAN");
		            db = new Db();
		            
		            Connection con = db.getConnection();
		            PreparedStatement ps = con.prepareStatement("SELECT CONTENT,CONTENT_BASE64,JENIS_MIME,NAMA_LAMPIRAN from " +
		            		" TBLADUANPUBLICLAMPIRAN WHERE ID_ADUANPUBLICLAMPIRAN = ?");
		            String id = request.getParameter("ID_ADUANPUBLICLAMPIRAN");
		            ps.setString(1,id);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
			            Blob  b = rs.getBlob("CONTENT");
			            
			            /*
			            String contentType = rs.getString("JENIS_MIME");
			            response.setContentType(contentType);
			            response.setContentLength( (int) b.length());
			            InputStream is = b.getBinaryStream();
			            OutputStream os = response.getOutputStream();
			            byte buf[] = new byte[(int) b.length()];
			            is.read(buf);
			            os.write(buf);
			            os.close();
			            */
			            
			            if(b==null)
		            	{
		            		myLogger.info("if time download");
		            		//if time download
			            	byte[] decodedBytes;
			            	if(rs.getString("CONTENT_BASE64").equals("") || rs.getString("CONTENT_BASE64")!=null)
			            	{			            		
			            		
			                decodedBytes = new BASE64Decoder().decodeBuffer(rs.getString("CONTENT_BASE64"));//tarik field yg simpan stringbase64			                
			                //kena convert to blob utk jimat ruang.. sbb bila dalam blob saiz lebih kecil
			                PreparedStatement ps2 = con.prepareStatement("UPDATE TBLADUANPUBLICLAMPIRAN SET CONTENT = ?, CONTENT_BASE64 = ? WHERE ID_ADUANPUBLICLAMPIRAN = ? ");
			                ps2.setBytes(1, decodedBytes);
				        	ps2.setString(2,""); 
				        	ps2.setString(3,id);      	
				        	ps2.executeUpdate();
			                
			                response.setContentType("image/png");
			                response.setHeader("Content-Disposition", "attachment;filename=" + rs.getString("NAMA_LAMPIRAN")+".png");//mesti assign file name yg releven
			                response.getOutputStream().write(decodedBytes);	
			            	}
		            	}
		            	else
		            	{
		            		//if da diconvert to blob.. download mcm biasa
		            		myLogger.info("if da diconvert to blob.. download mcm biasa");
		            		String contentType = rs.getString("jenis_mime");
				            response.setContentType(contentType);
				            response.setContentLength( (int) b.length());
				            InputStream is = b.getBinaryStream();
				            OutputStream os = response.getOutputStream();
				            byte buf[] = new byte[(int) b.length()];
				            is.read(buf);
				            os.write(buf);
				            os.close();
		            	}
			          
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
