package ekptg.view.pdt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.servlets.IServlet2;

public class DownloadBlob implements IServlet2{
	
	// size of byte buffer to send file
    private static final int BUFFER_SIZE = 4096;   
	
    public void doService(HttpServletRequest request,
	            HttpServletResponse response, ServletContext context) throws ServletException, IOException {
		  Db db = null;
		  Connection conn = null;
	        try {
	        	 db = new Db();
	        	 conn = db.getConnection();
				PreparedStatement ps = conn
						.prepareStatement("SELECT CONTENT,NAMA_FAIL FROM"
								+ " TBLPDTRUJLAMPIRAN WHERE ID_LAMPIRAN = ?");
				String idLampiran = request.getParameter("idLampiran");
				ps.setString(1, idLampiran);
				ResultSet result = ps.executeQuery();
	            if (result.next()) {
	                // gets file name and file blob data
	                String fileName = result.getString("NAMA_FAIL");
	                Blob blob = result.getBlob("CONTENT");
	                InputStream inputStream = blob.getBinaryStream();
	                int fileLength = (int)blob.length();
	                 
	                System.out.println("fileLength = " + fileLength);
	 
	 
	                // sets MIME type for the file download
	                String mimeType = context.getMimeType(fileName);
	                if (mimeType == null) {        
	                    mimeType = "application/pdf";
	                }              
	                 
	                // set content properties and header attributes for the response
	                response.setContentType(mimeType);
	                response.setContentLength(fileLength);
	                String headerKey = "Content-Disposition";
	                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
	                response.setHeader(headerKey, headerValue);
	 
	                // writes the file to the client
	                OutputStream outStream = response.getOutputStream();
	                 
	                byte[] buffer = new byte[BUFFER_SIZE];
	                int bytesRead = -1;
	                 
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outStream.write(buffer, 0, bytesRead);
	                }
	                 
	                inputStream.close();
	                outStream.close();             
	            } else {
	                // no file found
	                response.getWriter().print("File not found for the id: " + idLampiran);  
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            response.getWriter().print("SQL Error: " + ex.getMessage());
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            response.getWriter().print("IO Error: " + ex.getMessage());
	        } catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            if (conn != null) {
	                // closes the database connection
	                try {
	                    conn.close();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }          
	        }
	    }
}
