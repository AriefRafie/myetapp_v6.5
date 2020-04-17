package ekptg.engine.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.servlets.IServlet2;

public class DownloadFile implements IServlet2 {

	@Override
	public void doService(HttpServletRequest req, HttpServletResponse res,
			ServletContext ctx) throws IOException, ServletException {
		String tableName = req.getParameter("table");
		String pKey = req.getParameter("key");
		String pKField = req.getParameter("keyField");
		String mimeField = req.getParameter("mimeField");
		if(mimeField == null)
			mimeField ="JENIS_MIME";
		Db db = null;
		try{
			db = new Db();
			Connection conn = db.getConnection();
			String sql ="SELECT CONTENT,"+mimeField+" FROM "+tableName+" WHERE "+pKField+"=?";
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setLong(1,Long.parseLong(pKey));
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				System.out.println("found attachement");
				Blob blob = rs.getBlob("CONTENT");
				InputStream in = blob.getBinaryStream();
				byte buf[] = new byte[(int) blob.length()];
				in.read(buf);
				ServletOutputStream os = res.getOutputStream();
				System.out.println(rs.getString(mimeField));
	            res.setContentType(rs.getString(mimeField));
	            res.setContentLength( (int) blob.length());
	            os.write(buf);
	            os.flush();
	            os.close();
			}
			else{
				PrintWriter out = res.getWriter();
                String contextPath = req.getContextPath();
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/default.css\" />");
                out.println("<div style=\"width:150px\" class=\"error\">");
                out.println("Error!! No Data");
                out.println("</div>");
                out.close();
			}
		}
		catch(DbException dbe){
			System.err.println(dbe.getMessage());
		}
		catch(SQLException sqle){
			System.err.println(sqle.getMessage());
		}
		finally{
			db.close();
		}
	}

}
