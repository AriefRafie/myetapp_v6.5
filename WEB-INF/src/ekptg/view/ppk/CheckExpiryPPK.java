package ekptg.view.ppk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

public class CheckExpiryPPK implements IServlet2 {
	static Logger myLogger = Logger.getLogger(CheckExpiryPPK.class);
	Hashtable viewPengguna = null;
	List listPenggunaMengikutRole = null;
	@Override
	public void doService(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String contextPath = request.getContextPath();
		String submit = request.getParameter("command");		
		myLogger.info("submit:"+submit);
		if ("checkExpired".equals(submit)) {
			//checking disini
			myLogger.info(" update flag kemaskini :"+submit);
			
			
			String id_permohonan = request.getParameter("id_permohonan");
			String id_fail = request.getParameter("id_fail_atheader");
			String user_id = session.getAttribute("_ekptg_user_id").toString();
			
			try {
			
			
			if(getDurationKebKemaskini(id_permohonan,user_id,id_fail).equals("Y"))
			{
				System.out.println("TEMPOH_KEB_KEMASKINI==Y");
				//updateKebKemaskini(id_permohonan);
				//deleteAgihan(id_fail);
				
				
			}
			else
			{
				System.out.println("TEMPOH_KEB_KEMASKINI==N");
			}
			
			//String script = "<script> location.reload(); alert('Dah Expired!!!'); </script>";
			String script = "<script> reloadPageAfterExpired(); </script>";
			out.println(script);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		out.close();
	}
	
	//CHECK tempoh fail dibuka adakah lebih dari 7 hari
			public String getDurationKebKemaskini(String id_permohonan,String user_id, String IDFAIL) throws Exception{
				Db db = null;
				String sql2 = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					sql2 = " SELECT USER_ID " +
							" FROM TBLEDITAGIHAN " +
							" WHERE  " +
							" (SELECT TO_date(sysdate, 'dd/mm/yyyy')-TO_DATE(TARIKH_KEMASKINI, 'dd/mm/yyyy') DAYS FROM DUAL) > 8 " +
							" AND ID_FAIL = "+IDFAIL+" AND USER_ID =  "+user_id;
					
					myLogger.info("CHECK tempoh fail dibuka :"+sql2);
					
					Statement stmt2 = db.getStatement();
					ResultSet rs2 = stmt2.executeQuery(sql2);
					
					String countFileLonger = "N";
					while (rs2.next()) {
						countFileLonger = "Y";			
					}
					myLogger.info("countFileExist :"+countFileLonger);
					return countFileLonger;
				} finally {
					if (db != null)
						db.close();
				}	
			}
			
			//aishahlatip maklumat flag kebanaran Kemaskini
			
			 public static void updateKebKemaskini(String id_permohonan) throws Exception
			  {
			    Db db = null;
			    String sql = "";
			    try
			    {
			    	db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.update("id_permohonan", id_permohonan);
					r.add("FLAG_KEBENARAN_EDIT", "");	
					r.add("user_id_kebenaran_edit", "");
					r.add("catatan_kebenaran_edit", "");
					sql = r.getSQLUpdate("tblppkpermohonan");
					stmt.executeUpdate(sql);
			    	} finally {
			    		if (db != null) db.close();
			    	}
			  	}
			 
			//aishahlatip delete agihan
			 public static void deleteAgihan(String id_fail) throws Exception {

					Db db = null;
					String sql = "";

					try {

						db = new Db();
						Statement stmt = db.getStatement();

						sql = "DELETE FROM TBLEDITAGIHAN  WHERE ID_FAIL  = '"+ id_fail +"' ";
						stmt.executeUpdate(sql);
						
						System.out.println("sql delete ======="+sql);

					} finally {
						if (db != null)
							db.close();
					}

				}

	
}
