package ekptg.view.ppk.util;

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

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.servlets.IServlet2;

public class LampiranByBlob implements IServlet2{
	//static Logger myLog = Logger.getLogger(ekptg.view.ppk.util.LampiranByBlob.class);

	public void doService(HttpServletRequest request
		, HttpServletResponse response
		,ServletContext context) throws IOException, ServletException {
		Db db = null;
		String tableName = "tblppkdokumenhta";
		System.out.println("LampiranByBlob:doService");
		try {
			db = new Db();
			Connection con = db.getConnection(); 
			String tableNameReq = request.getParameter("tablename");
			///myLog.info("request.getParameter(\"tablename\")="+request.getParameter("tablename"));
			if(tableNameReq.equals("ha"))
				tableName = "tblppkdokumenha";
			else if(tableNameReq.equals("simati"))
				tableName = "tblppkdokumensimati";
			else if(tableNameReq.equals("hahah"))
				tableName = "tblppkdokumenha";

			
			String sql ="select kandungan content,format jenis_mime from "+tableName+" where id_dokumen = ?";
			//myLog.info("sql="+sql);
			PreparedStatement ps = con.prepareStatement(sql);
			String id = request.getParameter("iDokumen");
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
				
			} else {
				PrintWriter out = response.getWriter();
				String contextPath = request.getContextPath();
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/default.css\" />");
				out.println("<div style=\"width:150px\" class=\"error\">");
				out.println("Error!! No Data");
				out.close();
				
			}
			
		}catch(Exception ex) {
        	//myLog.info(ex.getMessage());
		}finally {
			if (db != null) db.close();
		}

	}
	
	
}
