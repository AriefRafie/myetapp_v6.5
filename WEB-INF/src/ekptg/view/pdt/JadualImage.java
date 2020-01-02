package ekptg.view.pdt;

import java.io.File;
import java.io.FileInputStream;
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

public class JadualImage implements IServlet2{
	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			//Security checking should be added later
			Db db = null;
			 try {
		            db = new Db();
		            Connection con = db.getConnection();        
		            
		            PreparedStatement ps = con.prepareStatement("select content,jenis_mime from " +
            		"tblpdtjaduallampiran_fail where id_jaduallampiran_fail = ?");
		            String id = request.getParameter("id");
		            ps.setString(1,id);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
			            Blob  b = rs.getBlob("content");
			            if (b == null) {
			            	String contextPath = request.getRealPath("");
			            	//File f1=new File("D:/eclipse-Version/MyWorkspace/eTapp_20052010/img/spacer.gif");
			            	File f1=new File(contextPath+"\\img\\spacer.gif");
			            	response.setContentType("image/gif");
			            	response.setHeader("Content-Disposition","inline;filename=x.gif");
			            	OutputStream os = response.getOutputStream();
			            	os.write(readFile(f1));
			            	os.close();
			            	//out.println("../../img/spacer.gif");
			            	//out.close();
			            } else {
				            String contentType = rs.getString("jenis_mime");
				            response.setContentType(contentType);
				            response.setContentLength( (int) b.length());
				        	response.setHeader("Content-Disposition", "inline; filename=jadual.jpg");
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
		             System.out.println(ex.getMessage());
		        }
		        finally {
				      if (db != null) db.close();
			    }
			
		}
	
	
	public byte[] readFile(File file){
		byte[] bytes =null;
		if(file!=null){
		try{
		InputStream is = new FileInputStream(file);


		// Get the size of the file
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
		System.out.println("File Size is more;");
		}

		// Create the byte array to hold the data
		bytes = new byte[(int)length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while ( (offset < bytes.length) && ((numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) ) {
		offset = numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
		throw new IOException("Could not completely read file ");
		}

		is.close();
		}catch(IOException e){
		e.printStackTrace();
		}
		}
		return bytes;
		}
	
	
}
