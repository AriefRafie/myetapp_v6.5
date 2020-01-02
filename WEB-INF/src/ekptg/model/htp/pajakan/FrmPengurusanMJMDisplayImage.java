package ekptg.model.htp.pajakan;

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

public class FrmPengurusanMJMDisplayImage implements IServlet2{
	public void doService(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws IOException, ServletException {
		// Security checking should be added later
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();

			PreparedStatement ps = con
					.prepareStatement("SELECT KANDUNGAN,NAMA_FAIL FROM"
							+ " TBLHTPDOKUMENMEMO WHERE ID_TBLHTPDOKUMENMEMO = ?");
			String id = request.getParameter("id");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Blob b = rs.getBlob("KANDUNGAN");
//				String contentType = rs.getString("jenis_mime");
				response.setContentType("");
				response.setContentLength((int) b.length());
				String namaFail = rs.getString("NAMA_FAIL");

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
}
