// Create By zulfazdliabuas@gmail.com Date 18-04-2017 for Paparkan maklumat audittrail selepas logout
// Source terlibat :   OnlineController.java, logout_audittrail.jsp, LogoutAuditTrail.java

package ekptg.view.online;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

public class LogoutAuditTrail implements IServlet2 {
	
	static Logger myLogger = Logger.getLogger(LogoutAuditTrail.class);
	
	
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		
//		out.println("XXXXXXXXXXXXXXXXXXXXXXX : " + user_id);
//		System.out.println("user_iduser_iduser_iduser_id ==== " + idUser);
		
		List listAuditTrail = null;
		try {
			listAuditTrail = listAuditTrail(idUser);
//			System.out.println(" listAuditTrail.size() ------- " + listAuditTrail.size());			
				out.println("<table class='table table-striped'>");	
				out.println("<thead>");
				out.println("<tr class='trtajuk'>");
	//			out.println("<td> rowCss </td>");
	//			out.println("<th> BIL </th>");
	//			out.println("<td> NO FAIL </td>");
	//			out.println("<th> MODULE NAME </th>");
	//			out.println("<th> JENIS AKTIVITI </th>");
	//			out.println("<th> KETERANGAN </th>");
	//			out.println("<th> MASA_AKTIVITI </th>");
	//			out.println("<th> MASA_AKTIVITI </th>");
				
	//			out.println("<th> ID_AUDITTRAIL </th>");	
	//			out.println("<th> NO_FAIL </th>");
				out.println("<th> KETERANGAN AKTIVITI </th>");
				out.println("<th> JENIS AKTIVITI </th>");
	//			out.println("<th> MODULE ID </th>");
	//			out.println("<th> NAMA MODUL </th>");
	//			out.println("<th> MODULE GROUP </th>");
				out.println("<th> MASA AKTIVITI </th>");
	//			out.println("<th> IP_ADDRESS </th>");
				out.println("<th> BAHAGIAN </th>");
				out.println("<th> STATUS AKTIVITI </th>");
				out.println("</tr>");
				out.println("</thead>");
				
				if(listAuditTrail.size() != 0){
	//				System.out.println("listAuditTrail.size()listAuditTrail.size()============== " + listAuditTrail.size());
					for (int i = 0; i < listAuditTrail.size(); i++) {
						Map at = (Map) listAuditTrail.get(i);
						out.println("<tr>");
		////				out.println("<td>" + at.get("rowCss") + "</td>");
		//				out.println("<td>" + at.get("BIL") + "</td>");
		//				out.println("<td>" + at.get("NO_FAIL") + "</td>");
		//				out.println("<td>" + at.get("NAMA_MODUL") + "</td>");
		//				out.println("<td>" + at.get("JENIS_AKTIVITI") + "</td>");
		//				out.println("<td>" + at.get("KETERANGAN_AKTIVITI") + "</td>");
		//				out.println("<td>" + at.get("MASA_AKTIVITI") + "</td>");
		//				out.println("<td>" + at.get("") + "</td>");
						
		//				out.println("<td>" + at.get("ID_AUDITTRAIL") + "</td>");			
		//				out.println("<td>" + at.get("NO_FAIL") + "</td>");
						out.println("<td>" + at.get("KETERANGAN_AKTIVITI") + "</td>");
						out.println("<td>" + at.get("JENIS_AKTIVITI") + "</td>");
		//				out.println("<td>" + at.get("MODULE_ID") + "</td>");
		//				out.println("<td>" + at.get("NAMA_MODUL") + "</td>");
		//				out.println("<td>" + at.get("MODULE_GROUP") + "</td>");
						out.println("<td>" + at.get("MASA_AKTIVITI") + "</td>");
		//				out.println("<td>" + at.get("IP_ADDRESS") + "</td>");
						out.println("<td>" + at.get("BAHAGIAN") + "</td>");
						out.println("<td>" + at.get("STATUS_AKTIVITI") + "</td>");						
						out.println("</tr>");
					}
				}else {
					out.println("<tr>");
					out.println("<td> TIADA REKOD </td>");
					out.println("</tr>");
				}
				out.println("</table>");
				cleanupVelocity(session, (VelocityContext) session.getAttribute("VELOCITY_CONTEXT"), request);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private List listAuditTrail(String user_id) throws Exception {
		// TODO Auto-generated method stub
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listAuditTrail = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
//			sql = "SELECT ID_AUDITTRAIL, MODULE_NAME, JENIS_AKTIVITI, TARIKH_AKTIVITI, KETERANGAN," +
//				  " IP_ADDRESS, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, ID_SEKSYEN, ID_STATUS, ID_SUBURUSAN" +
//				  " FROM TBLAUDITTRAIL WHERE ID_MASUK = '"+ idUser +"'";
			sql = " SELECT ID_AUDITTRAIL, NO_FAIL,KETERANGAN_AKTIVITI, JENIS_AKTIVITI, MODULE_ID, NAMA_MODUL, MODULE_GROUP, (TARIKH_AKTIVITI || ' ' ||  WAKTU_AKTIVITI) AS MASA_AKTIVITI, "+
					" IP_ADDRESS, BAHAGIAN,STATUS_AKTIVITI "+
					" FROM "+
					" (SELECT TO_CHAR(AT.ID_AUDITTRAIL) AS ID_AUDITTRAIL, "+
					" REPLACE(NVL( "+
					" (CASE WHEN (AT.KETERANGAN LIKE '%FAIL [%' OR AT.KETERANGAN LIKE '%FAIL PERMOHONAN [%' OR AT.KETERANGAN LIKE '% [%') THEN  SUBSTR(SUBSTR(AT.KETERANGAN, 1, INSTR(AT.KETERANGAN, ']') - 1) ,  "+
					" INSTR(SUBSTR(AT.KETERANGAN, 1, INSTR(AT.KETERANGAN, ']') - 1) ,	 '[') + 1) "+
					" ELSE '' END) "+
					" ,''),'null','') AS NO_FAIL, AT.KETERANGAN AS KETERANGAN_AKTIVITI, "+
					" (CASE WHEN AT.JENIS_AKTIVITI = 'INS' THEN 'INSERT' "+
					" WHEN AT.JENIS_AKTIVITI = 'UPD' THEN 'UPDATE' "+
					" WHEN AT.JENIS_AKTIVITI = 'DEL' THEN 'DELETE' "+
					" ELSE '' END) AS JENIS_AKTIVITI, AT.MODULE_NAME AS MODULE_ID, M.MODULE_TITLE AS NAMA_MODUL, "+
					" M.MODULE_GROUP, "+
					" TO_CHAR(AT.TARIKH_AKTIVITI,'DD/MM/YYYY') AS TARIKH_AKTIVITI, "+
					" TO_CHAR(AT.TARIKH_AKTIVITI,'HH:MM:SS AM') AS WAKTU_AKTIVITI, "+
					" REPLACE(NVL(AT.IP_ADDRESS,''),'null','') AS IP_ADDRESS, "+
					" S.KOD_SEKSYEN AS BAHAGIAN, ST.KETERANGAN AS STATUS_AKTIVITI "+
					" FROM TBLAUDITTRAIL AT, MODULE M, TBLRUJSEKSYEN S, TBLRUJSTATUS ST "+
					" WHERE AT.MODULE_NAME = M.MODULE_ID(+) "+
					" AND AT.ID_SEKSYEN = S.ID_SEKSYEN(+) "+
					" AND AT.ID_STATUS = ST.ID_STATUS(+) ";
			
					sql += " AND TO_CHAR(AT.TARIKH_AKTIVITI,'DD/MM/YYYY') = TO_CHAR(SYSDATE,'DD/MM/YYYY') " +
							"  AND (AT.ID_MASUK = "+user_id+" OR AT.ID_KEMASKINI = "+user_id+") ";
					
					sql += " ORDER BY AT.TARIKH_AKTIVITI DESC "+
					" ) ";
			
			myLogger.info(" listAuditTrail ====== :"+ sql);			
			rs = stmt.executeQuery(sql);
			listAuditTrail = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
		
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
//				h.put("ID_AUDITTRAIL",rs.getString("ID_AUDITTRAIL") == null ? "" : rs.getString("ID_AUDITTRAIL"));				
//				h.put("MODULE_NAME",rs.getString("MODULE_NAME") == null ? "" : rs.getString("MODULE_NAME"));
//				h.put("JENIS_AKTIVITI",rs.getString("JENIS_AKTIVITI") == null ? "" : rs.getString("JENIS_AKTIVITI"));
////				h.put("TARIKH_AKTIVITI",rs.getString("TARIKH_AKTIVITI") == null ? "" : rs.getString("TARIKH_AKTIVITI"));
//				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
//				h.put("IP_ADDRESS",rs.getString("IP_ADDRESS") == null ? "" : rs.getString("IP_ADDRESS"));
//				h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
//				h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
//				h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
//				h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
//				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
//				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
//				h.put("ID_SUBURUSAN",rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
								
				h.put("ID_AUDITTRAIL",rs.getString("ID_AUDITTRAIL") == null ? "" : rs.getString("ID_AUDITTRAIL"));				
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("KETERANGAN_AKTIVITI",rs.getString("KETERANGAN_AKTIVITI") == null ? "" : rs.getString("KETERANGAN_AKTIVITI"));
				h.put("JENIS_AKTIVITI",rs.getString("JENIS_AKTIVITI") == null ? "" : rs.getString("JENIS_AKTIVITI"));
				h.put("MODULE_ID",rs.getString("MODULE_ID") == null ? "" : rs.getString("MODULE_ID"));
				h.put("NAMA_MODUL",rs.getString("NAMA_MODUL") == null ? "" : rs.getString("NAMA_MODUL"));
				h.put("MODULE_GROUP",rs.getString("MODULE_GROUP") == null ? "" : rs.getString("MODULE_GROUP"));
				h.put("MASA_AKTIVITI",rs.getString("MASA_AKTIVITI") == null ? "" : rs.getString("MASA_AKTIVITI"));
				h.put("IP_ADDRESS",rs.getString("IP_ADDRESS") == null ? "" : rs.getString("IP_ADDRESS"));
				h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));
				h.put("STATUS_AKTIVITI",rs.getString("STATUS_AKTIVITI") == null ? "" : rs.getString("STATUS_AKTIVITI"));
				listAuditTrail.add(h);
			}
			
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		
		return listAuditTrail;
	}
	
	 public void cleanupVelocity(HttpSession session, VelocityContext context,HttpServletRequest request) {
	    	//HttpSession session = this.request.getSession();
	    		
	    	System.out.println("** LA cleanupVelocity **");
	    	context = (VelocityContext) session.getAttribute("VELOCITY_CONTEXT");
			if (context != null) {
				System.out.println("** LA cleanupVelocity **");
				Object objArray[] = context.getKeys();
				for (int i = 0; i < objArray.length; i++) {
					context.remove(objArray[i]);
					//myLogger.debug((new StringBuilder("removed:")).append(objArray[i]).toString());
					System.out.println((new StringBuilder("removed:")).append(objArray[i]).toString());
				}
			}
			
			removeSession(request,session);	
			
			
		}
	    
	    public void removeSession(HttpServletRequest request,HttpSession session) {
	    	//HttpSession session = request.getSession(false);
	    	//System.out.println("Session : "+session);	 
	    	/*
	    	HttpSession session_check = request.getSession();
	    	if (session_check != null) {
	    		System.out.println("CR Session : "+session_check);
	    		session_check.invalidate();
	    	}
	    	else 
	    	{
	    		System.out.println("CR Session : Tiada");
	    	}
	    	*/
	    	//HttpSession session = request.getSession(false);
	    	System.out.println("Session : "+session);	  
	    	/*
	    	HttpSession session_check = request.getSession(false);
	    	if (session_check != null) {
	    		System.out.println("CR Session : "+session_check);
	    		session_check.invalidate();
	    	}
	    	else 
	    	{
	    		System.out.println("CR Session : Tiada");
	    	}
	    	*/
	    	//session.removeAttribute("attribute name");
	    	Enumeration e = (Enumeration) (session.getAttributeNames());
	        while ( e.hasMoreElements())
	        {
	        	String name = (String) e.nextElement();
	        	System.out.println("AT Session Name : "+name);
	        	/*
	            Object tring;
	            if((tring = e.nextElement())!=null)
	            {
	                out.println(session.getValue((String) tring));
	                out.println("<br/>");
	            }
	            */
	        	session.removeAttribute(name);

	        }
	    	
	    }
	
}