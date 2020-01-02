
package ekptg.report.macgdi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.report.EkptgReportServlet;

public class RekodRingkasanRizabNegeri extends EkptgReportServlet{

	public RekodRingkasanRizabNegeri() {
		super.setReportName("HTPRekodRingkasanRizabNegeri");
		super.setFolderName("integrasi");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		//getParameter
		HttpSession session = request.getSession();
		String user_name = (String)session.getAttribute("_portal_username");
		String user_id = (String)session.getAttribute("_portal_login");
		String current_role = (String) session.getAttribute("myrole");
		System.out.println("session"+user_name);
		String id = String.valueOf(parameters.get("id"));
		System.out.println("iddd"+id);
		
		System.out.println("session"+user_name);
		System.out.println("parameters.get(\"id_tahunDari\")="+parameters.get("id_tahunDari"));
		if(String.valueOf(parameters.get("id_tahunDari")).equals("")){
			super.setFolderName("htp");

		}
		
		SimpanHistory(session,id);
	}
	//public void SimpanHistory(HttpSession session, String ids)throws Exception {
		public void SimpanHistory(HttpSession session, String ids)throws Exception {
			long id = 0;
			Db db = null;
			Connection conn = null;
			String userId = session.getAttribute("_portal_login").toString();
			String namaLaporan = "Ringkasan Tanah Rizab Persekutuan Mengikut Negeri";
			String sql = "";

			try {

				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "";
				id = DB.getNextID("TBLINTMACGDIHISTORY_SEQ");
				r.add("ID", id);
				r.add("NAMA_LAPORAN", namaLaporan);
				r.add("TARIKH_CETAK", r.unquote("SYSDATE"));
				r.add("USER_LOGIN", userId);
				r.add("ID_SENARAILAPORAN", ids);
				sql = r.getSQLInsert("TBLINTMACGDIHISTORY");
				stmt.executeUpdate(sql);
				
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
