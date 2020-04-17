package ekptg.view.ppk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

public class UtilsServlet implements IServlet2 {
	static Logger myLogger = Logger.getLogger(UtilsServlet.class);
	 private String user_id;
	 public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context)
	  throws IOException, ServletException
	  {
		 HttpSession session = request.getSession();
		 PrintWriter out = response.getWriter();
		 String contextPath = request.getContextPath();
		 user_id = (String)session.getAttribute("_ekptg_user_id");
		 //Hashtable get_catatan = null;
		 //get_catatan = (Hashtable) getMesej(id_inboxmesej);
		 
		 String submit = request.getParameter("command");
		 if ("doMaklumatTambahan".equals(submit)) {
			String id_semakan = request.getParameter("id_semakan");
			String id_permohonan = request.getParameter("id_permohonan");
			String txtcatatan = request.getParameter("txtcatatan");
			myLogger.info("doMaklumatTambahan -----------------------------");
			myLogger.info("submit :"+submit);
			myLogger.info("id_semakan :"+id_semakan);
			myLogger.info("id_permohonan :"+id_permohonan);
			myLogger.info("txtcatatan :"+txtcatatan);
			doSaveMaklumatTambahan(id_semakan,id_permohonan,txtcatatan);
			//insertMaklumatCatatanTambahan(id_semakan,id_permohonan,txtcatatan);
		 }
		 else if ("doSimpanCatatanMaklumatTambahan".equals(submit)) {
			 
			 String id_permohonan = request.getParameter("id_permohonan");
			 deleteMaklumatTambahan("",id_permohonan,"");
			    String[] check_maklumat = request.getParameterValues("check_maklumat");
			  	if (check_maklumat != null) {
					for (int i = 0; i < check_maklumat.length; i++) {						
						myLogger.info("CHECK MAKLUMAT VALUE :"+check_maklumat[i]);
						myLogger.info("CHECK MAKLUMAT TEXT :"+request.getParameter("txtcatatan"+check_maklumat[i]));
						//doSaveMaklumatTambahan(check_maklumat[i],id_permohonan,request.getParameter("txtcatatan"+check_maklumat[i]));
						insertMaklumatTambahan(check_maklumat[i],id_permohonan,request.getParameter("txtcatatan"+check_maklumat[i]));
					}
			  	}
			    
			    
				String txtcatatan = request.getParameter("txtcatatan");
				myLogger.info("doMaklumatTambahan -----------------------------");
				myLogger.info("submit :"+submit);
				
				myLogger.info("id_permohonan :"+id_permohonan);
				myLogger.info("txtcatatan :"+txtcatatan);
				//doSaveMaklumatTambahan(id_semakan,id_permohonan,txtcatatan);
				//insertMaklumatCatatanTambahan("",id_permohonan,txtcatatan);
			 }
	  }
	 
	 public void doSaveMaklumatTambahan(String id_semakan,String id_permohonan,String txtcatatan) {
		 if (isMaklumatExist(id_semakan,id_permohonan)) {
			 deleteMaklumatTambahan(id_semakan,id_permohonan,txtcatatan);
		 } else {
			 insertMaklumatTambahan(id_semakan,id_permohonan,txtcatatan);
		 }
	 }
	 
	 public boolean isMaklumatExist(String id_semakan,String id_permohonan) {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 sql = "select id_semakan from TBLPPKSEMAKANTAMBAHAN where id_semakan='"+id_semakan+"' " +
			 		"and id_permohonan='"+id_permohonan+"' ";
			 ResultSet rs = stmt.executeQuery(sql);
			 if (rs.next()) {
				 return true;
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 if (db != null) db.close();
		 }
		 return false;
	 }
	 
	 public void deleteMaklumatTambahan(String id_semakan,String id_permohonan,String txtcatatan) {
		 Db db = null;
		 String sql = "";
		 String sql1 = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 /*sql = "delete from TBLPPKSEMAKANTAMBAHAN where id_semakan='"+id_semakan+"' " +
			 		"and id_permohonan='"+id_permohonan+"' ";
			 stmt.executeQuery(sql);*/
			 sql = "delete from TBLPPKSEMAKANTAMBAHAN where id_permohonan='"+id_permohonan+"' ";
		 stmt.executeQuery(sql);
			/* 
			 SQLRenderer r = new SQLRenderer();			 
			 r.clear();
			 r.update("id_Permohonan",id_permohonan);
			 r.add("CATATAN_MAKLUMAT_TAMBAHAN",txtcatatan);
     	     sql1 = r.getSQLUpdate("tblppkpermohonan");
		     stmt.executeUpdate(sql1);*/
			
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 if (db != null) db.close();
		 }
	 }
	 
	 public void insertMaklumatTambahan(String id_semakan,String id_permohonan,String txtcatatan) {
		 Db db = null;
		 String sql = "";
		 String sql1 = "";
		 try {
			 
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 r.add("ID_PERMOHONAN",id_permohonan);
		     r.add("ID_SEMAKAN",id_semakan);
		     r.add("ID_MASUK",user_id);
		     r.add("ID_KEMASKINI",user_id);
		     r.add("CATATAN",txtcatatan);
		     r.add("TARIKH_MASUK",r.unquote("sysdate"));
		     r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
		     sql = r.getSQLInsert("TBLPPKSEMAKANTAMBAHAN");
		     stmt.executeUpdate(sql);
		     
		    /* r.clear();
			 r.update("id_Permohonan",id_permohonan);
			 r.add("CATATAN_MAKLUMAT_TAMBAHAN",txtcatatan);
     	     sql1 = r.getSQLUpdate("tblppkpermohonan");
		     stmt.executeUpdate(sql1);*/
		     
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 if (db != null) db.close();
		 }
	 }
	 
	 
	 public void insertMaklumatCatatanTambahan(String id_semakan,String id_permohonan,String txtcatatan) {
		 Db db = null;
		 String sql = "";
		 String sql1 = "";
		 try {
			 
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 
		     r.clear();
			 r.update("id_Permohonan",id_permohonan);
			 r.add("CATATAN_MAKLUMAT_TAMBAHAN",txtcatatan);
     	     sql1 = r.getSQLUpdate("tblppkpermohonan");
     	     myLogger.info("SIMPAN MAKLUMAT TAMBAHAN :"+sql1.toUpperCase());
		     stmt.executeUpdate(sql1);
		     
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 if (db != null) db.close();
		 }
	 }
	 
	 

}
