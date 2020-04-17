/**
 * 
 */
package ekptg.view.integrasi.macgdi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.servlets.IServlet2;

public class FrmDisplayDokumen implements IServlet2 {

	public void doService(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws IOException, ServletException {
		
		String id = request.getParameter("id");
		String namaLaporan = request.getParameter("namaDokumen");
		HttpSession session = request.getSession();
		String user_name = (String)session.getAttribute("_portal_username");
		
				
		// Security checking should be added later
		Db db = null;
		try {
			SimpanHistory(session,id);
			db = new Db();
			Connection con = db.getConnection();

			PreparedStatement ps = con
					.prepareStatement("SELECT CONTENT,JENIS_MIME,NAMA_FAIL FROM"
							+ " TBLINTMACGDILAPORANDOKUMEN WHERE ID = ?");
			
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Blob b = rs.getBlob("content");
				String contentType = rs.getString("jenis_mime");
				response.setContentType(contentType);
				response.setContentLength((int) b.length());
				String namaFail = rs.getString("nama_fail");

				response.setHeader("Content-Disposition", "inline; filename=\""
						+ namaFail + "\"");
				InputStream is = b.getBinaryStream();
				OutputStream os = response.getOutputStream();
				byte buf[] = new byte[(int) b.length()];
				is.read(buf);
				os.write(buf);
				os.close();

			} else {
				PrintWriter out = response.getWriter();
				String contextPath = request.getContextPath();
				out
						.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""
								+ contextPath + "/default.css\" />");
				out.println("<div style=\"width:150px\" class=\"error\">");
				out.println("Error!! No Data");
				out.println("</div>");
				out.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//public void SimpanHistory(HttpSession session)throws Exception {
	public void SimpanHistory(HttpSession session, String ids)throws Exception {
	Db db = null;
	Connection conn = null;
	String userId = session.getAttribute("_portal_login").toString();
	String sql = "";

	try {

		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		
		sql = "";
		r.update("ID", ids);
		r.add("TARIKH_CETAK", r.unquote("SYSDATE"));
		r.add("USER_LOGIN", userId);
		sql = r.getSQLUpdate("TBLINTMACGDILAPORANDOKUMEN");
		stmt.executeUpdate(sql);
		System.out.println("bela bela update" + sql);
		
		conn.commit();
	} catch (SQLException ex) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new Exception("Rollback error : " + e.getMessage());
		}
		throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

	} finally {
		if (db != null)
			db.close();
	}
	
}

}
