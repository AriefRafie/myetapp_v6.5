package ekptg.view.ppk;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.servlets.IServlet2;
import oracle.sql.BLOB;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
//import ekptg.model.entities.Tblpfdrujlampiran;

public class DisplayBlobPNB implements IServlet2{
	static Logger myLogger = Logger.getLogger(DisplayBlobPNB.class);
	
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
			//Security checking should be added later
		BLOB blob = null;
		byte[] pdfStream = null;
		String contextPath = request.getContextPath();
		Db db = null;
		String sql = "";
		Document pdfDoc = new Document();
		try {
			db = new Db();
			Connection con = db.getConnection();
			//con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("SELECT content from TBLPPKBORANGPNB WHERE ID_BORANGPNB=? FOR UPDATE");
			String idborangpnb = request.getParameter("idborangpnb");
			ps.setString(1, idborangpnb);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pdfStream = rs.getBytes("content");
			}
			response.setContentType("application/pdf");
			response.setContentLength(pdfStream.length);			
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(pdfStream, 0, pdfStream.length);
			/*PdfReader reader = new PdfReader(pdfStream);
			System.out.println(" pageCount : "+reader.getNumberOfPages());*/
			ouputStream.flush();
			ouputStream.close();
			
			//updateCOUNT_PAGES(idborangpnb,reader.getNumberOfPages(),db);
			
			//con.commit();

		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (db != null)
				db.close();
			//
			
		}
			
		}
	
	
	public void updateCOUNT_PAGES(String ID_BORANGPNB,Integer COUNT_PAGES, Db db) throws Exception {
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
				r.update("ID_BORANGPNB", ID_BORANGPNB);
				r.add("COUNT_PAGES", COUNT_PAGES);
				sql = r.getSQLUpdate("TBLPPKBORANGPNB");
			
			myLogger.info("updateCOUNT_PAGES : "+sql);				
			stmt.executeUpdate(sql);
			} 
		finally {
			//if (db != null)
			//	db.close();
		}
	}
}
