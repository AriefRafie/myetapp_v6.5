package ekptg.view.online;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.db.AuthenticateUser;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.DB;
import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.ComplaintType;

public class PermohonanIDAgensi implements IServlet2 {
	
	static Logger myLogger = Logger.getLogger(Portal.class);


	
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) 
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String contextPath = request.getContextPath();
		String submit = request.getParameter("command");
		myLogger.info("submit:"+submit);
		
		
		if ("doRegister".equals(submit)) {
			
			String script = "<script> resetSkrin(); </script>";
			
			try {
				
				
				String NOKP1 = request.getParameter("NOKP1");
				String NOKP2 = request.getParameter("NOKP2");
				String NOKP3 = request.getParameter("NOKP3");
				String fullid = NOKP1+NOKP2+NOKP3;
				String getCheckFlag = checkPermohonanID(session, fullid);
				
				if(getCheckFlag.equals(""))
				{
				//savePengguna(session,request);
				out.println("<font color='blue' class='blink'>Permohonan Anda Akan Dihantar Untuk Pengesahan. " +
						"Maklumbalas Akan Dihantar Kepada Emel Pemohon Untuk Tindakan Lanjut</a> </font>");
				out.println("<script>doEffect();</script>");
				out.println("<script>doHide();</script>"+script);
				}
				else
				{
					if(getCheckFlag.equals("1"))
					{
						out.println("<font color='blue' class='blink'>Permohonan ID ("+NOKP1+"-"+NOKP2+"-"+NOKP3+") Telah Wujud Dan Sedang Diproses!</font>");
						out.println("<script>doEffect();resetSkrin();</script>");
					}
					else if(getCheckFlag.equals("2"))
					{
						out.println("<font color='red' class='blink'>Permohonan ID ("+NOKP1+"-"+NOKP2+"-"+NOKP3+") Telah Ditolak, Notifikasi Telah Dihantar Ke Emel Anda!</font>");
						out.println("<script>doEffect();resetSkrin();</script>");
					}
					else if(getCheckFlag.equals("3"))
					{
						out.println("<font color='blue' class='blink'>Permohonan ID ("+NOKP1+"-"+NOKP2+"-"+NOKP3+") Telah Diluluskan, Notifikasi Telah Dihantar Ke Emel Anda!</font>");
						out.println("<script>doEffect();resetSkrin();</script>");
					}
				}
				
				
				
			} catch (Exception e) {
				out.println("Pendaftaran tidak berjaya:" + e.getMessage());
			}
		}
	
	}


public String checkPermohonanID(HttpSession session, String NOKP) throws Exception {
	Db db = null;
	String sql = "";
	ResultSet rs = null;
	Statement stmt = null;
	try {
		db = new Db();
		stmt = db.getStatement();
		String flag_status = "";
		
		sql = " SELECT FLAG_STATUS FROM PERMOHONANIDPENGGUNA WHERE NOKP = '"+NOKP+"'  ";
		
			rs = stmt.executeQuery(sql);				
			while (rs.next()) {
				flag_status = rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS");
			}
		
		return flag_status;
	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}
}

}
