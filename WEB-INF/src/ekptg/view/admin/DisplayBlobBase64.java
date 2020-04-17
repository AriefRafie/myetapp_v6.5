package ekptg.view.admin;
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

public class DisplayBlobBase64 implements IServlet2{
	
	static Logger myLogger = Logger.getLogger(DisplayBlobBase64.class);
	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			Db db = null;
			 try {
				    myLogger.info("MASUK DOWNLOAD STRING BASE64");
		            db = new Db();
		            Connection con = db.getConnection();
		            PreparedStatement ps = con.prepareStatement("select ID_BASE64,BASE64,CONTENT,JENIS_MIME,NAMA_FAIL from TBLBASE64 where ID_BASE64 = ?");		            
		            
		            String id = request.getParameter("id");
		            String nama_fail = request.getParameter("nama_fail");
		            ps.setString(1,id);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {		  
		            	Blob  b = rs.getBlob("content");
		            	if(b==null)
		            	{
		            		myLogger.info("if time download");
		            		//if time download
			            	byte[] decodedBytes;
			            	if(rs.getString("BASE64").equals("") || rs.getString("BASE64")!=null)
			            	{
			            		
			            		
			                decodedBytes = new BASE64Decoder().decodeBuffer(rs.getString("BASE64"));//tarik field yg simpan stringbase64			                
			                //kena convert to blob utk jimat ruang.. sbb bila dalam blob saiz lebih kecil
			                PreparedStatement ps2 = con.prepareStatement("UPDATE TBLBASE64 SET CONTENT = ?, JENIS_MIME = ?, NAMA_FAIL = ?, BASE64 = ? WHERE ID_BASE64 = ? ");
			                ps2.setBytes(1, decodedBytes);
			                ps2.setString(2, "application/pdf");
				        	ps2.setString(3,nama_fail);
				        	
				        	
				        	
				        		
				        	ps2.setString(4,""); 
				        	ps2.setString(5,id);      	
				        	ps2.executeUpdate();
			                
			                response.setContentType("application/pdf");
			                response.setHeader("Content-Disposition", "attachment;filename=" + nama_fail+".pdf");//mesti assign file name yg releven
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
