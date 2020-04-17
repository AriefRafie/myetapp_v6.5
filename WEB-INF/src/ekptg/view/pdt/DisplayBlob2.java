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


	
	public class DisplayBlob2 implements IServlet2 {


		public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			//Security checking should be added later
			Db db = null;
			 try {
		            db = new Db();
		            Connection con = db.getConnection();
		            String type = request.getParameter("type");
		            PreparedStatement ps = null;
		            if ("akta".equals(type)) {
			            ps = con.prepareStatement("select content,jenis_mime from " +
	            		"tblpdtakta where id_akta = ?");
		            }  else if ("aktapindaan".equals(type)) {
			            ps = con.prepareStatement("select content,jenis_mime from " +
	            		"tblpdtaktapinda where id_aktapinda = ?");
		            } else if ("enakmen".equals(type)) {
		            	 ps = con.prepareStatement("select content,jenis_mime from " +
		            		"tblpdtenakmen where id_enakmen = ?");
		            } else if ("pekeliling".equals(type)) {
		            	 ps = con.prepareStatement("select content,jenis_mime from " +
		            		"TBLPDTPEKELILING where id_pekeliling = ?");
		            } else if ("enakmenpinda".equals(type)) {
		            	 ps = con.prepareStatement("select content,jenis_mime from " +
		            		"tblpdtenakmenpinda where id_enakmenpinda = ?");
		            } else if ("jaduallampiran".equals(type)) {
		            	 ps = con.prepareStatement("select content,jenis_mime from " +
		            		"TBLPDTJADUALLAMPIRAN_FAIL where id_jaduallampiran_fail = ?");
		            }
		            else {
			            ps = con.prepareStatement("select content,jenis_mime from " +
	            		"tblpdtenakmen where id_enakmen = ?");
		            }

		            String id = request.getParameter("id");
		            ps.setString(1,id);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
			            Blob  b = rs.getBlob("content");
			            if (b == null) {
			                PrintWriter out = response.getWriter();
			                String contextPath = request.getContextPath();
			                out.println("Dokumen tiada dalam simpanan!!");
			                out.close();
			            } else {
			            	//Blob  b = rs.getBlob("content");
				            String contentType = rs.getString("jenis_mime");
				            response.setContentType(contentType);
				            response.setContentLength( (int) b.length());
				            InputStream is = b.getBinaryStream();
				            OutputStream os = response.getOutputStream();
				            byte buf[] = new byte[(int) b.length()];
				            is.read(buf);
				            os.write(buf);
				            os.close();
			            	/*
				            String contentType = rs.getString("jenis_mime");
				            response.setContentType(contentType);
				            //IE Need this part.
				            response.setHeader("Content-Disposition", "inline; filename=\"myDoc.pdf\"");
				            response.setContentLength( (int) b.length());
				            InputStream is = b.getBinaryStream();
				            OutputStream os = response.getOutputStream();
				            byte buf[] = new byte[(int) b.length()];
				            is.read(buf);
				            os.write(buf);
				            os.close();
				            */
			            }
		            } else {
		                PrintWriter out = response.getWriter();
		                String contextPath = request.getContextPath();
		                out.println("Error!! No Data");
		                out.close();
		            }
		        }
		        catch(Exception ex) {
		             ex.printStackTrace();
		        }
		        finally {
				      if (db != null) db.close();
			    }

			
		}


}
