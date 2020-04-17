package ekptg.view.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.view.admin.Pengumuman;

public class FrmCRBDashboard extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	static Logger myLogger = Logger.getLogger(FrmCRBDashboard.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	Pengumuman logicPengumuman = new Pengumuman();
	String vm = "";
	
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String portal_role = (String) session.getAttribute("myrole");
		context.put("portal_role", portal_role);
		String command = getParam("command");
		
		role = (String) session.getAttribute("myrole");
		userId = (String) session.getAttribute("_ekptg_user_id");
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");

		Vector list_memo_aktif = null;	
		
		context.put("checkNotifikasiOnline", getNotifikasiOnline());
		context.put("failPenguatkuasaan", getFailPenguatkuasaan());
		context.put("failBelumSelesai", getFailBelumSelesai());
		context.put("failSelesai", getFailSelesai());
		context.put("check_notifikasi_aduan", getNotifikasiAduan("", user_negeri_login, userId, "", "NO"));
		
		//list_memo_aktif = logicPengumuman.getMemo("", "Aktif","1","0");
		list_memo_aktif = logicPengumuman.getMemo("", "Aktif","1","","","","","0");
		context.put("list_memo_aktif", list_memo_aktif);
		
		context.put("defaultTab", "0");

		vm = "app/php2/CRBDashboard.jsp";
		
		return vm;
	}

	public Integer getNotifikasiOnline() throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT COUNT(*) AS BIL "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C,  TBLRUJURUSAN D, TBLRUJSUBURUSAN E "
				+ " WHERE A.ID_URUSAN IN (8)"
				+ " AND A.FLAG_JENIS_FAIL = '4' AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND A.NO_FAIL IS NULL AND B.NO_PERMOHONAN IS NOT NULL"
				+ " AND A.ID_SEKSYEN = '4' AND A.ID_URUSAN = D.ID_URUSAN AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND B.ID_STATUS IS NOT NULL";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt("BIL");
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer getFailPenguatkuasaan() throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT COUNT(*) AS BIL"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLPHPHAKMILIK F, USERS H"
				+ " WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN IN (8) AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS"
				+ " AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+)"
				+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.FLAG_AKTIF = 'Y' AND A.NO_FAIL IS NOT NULL"
				+ " AND A.ID_MASUK = H.USER_ID(+)";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt("BIL");
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer getNotifikasiAduan(String id_esaduan, String id_negeri_user, String user_terima, String flag_notifikasi, String notread) throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT COUNT(*) AS BIL FROM TBLESADUAN A, TBLRUJSUMBERESADUAN S, TBLRUJJENISESADUAN J, USERS U, TBLRUJSTATUSESADUAN ST, USERS_INTERNAL UI,"
				+ " TBLRUJSEKSYEN SK, TBLESNOTIFIKASI X, TBLRUJNEGERI N, TBLRUJPEJABATJKPTG PEJ, TBLRUJDAERAH D, TBLRUJJENISMODULESADUAN JM"
				+ " WHERE A.ID_SUMBERADUAN = S.ID_SUMBERADUAN(+)"
				+ " AND A.ID_JENISADUAN = J.ID_JENISADUAN(+)"
				+ " AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN(+)"
				+ " AND A.USER_ID = U.USER_ID"
				+ " AND X.ID_ESADUAN = A.ID_ESADUAN"
				+ " AND A.ID_STATUS = ST.ID_STATUSESADUAN(+)"
				+ " AND U.USER_ID = UI.USER_ID"
				+ " AND UI.ID_SEKSYEN = SK.ID_SEKSYEN(+)"
				+ " AND UI.ID_NEGERI = N.ID_NEGERI(+)"
				+ " AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+)"
				+ " AND UI.ID_DAERAH = D.ID_DAERAH(+)" 
				+ " AND A.ID_STATUS NOT IN ('16125') AND A.ID_STATUS IS NOT NULL"
				+ " AND X.ID_ESNOTIFIKASI IS NOT NULL";
		
			if(!id_esaduan.equals("")){
				sql += " AND X.ID_ESADUAN = '" + id_esaduan + "'";
			}
			if(!id_negeri_user.equals("") && !role.equals("adminsuper_es")){
				sql += " AND A.ID_NEGERI_PENGADU = '" + id_negeri_user + "'";
			}
			if(!user_terima.equals("")){
				sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '" + user_terima + "'";
			}
			if(!flag_notifikasi.equals("")){
				sql += " AND X.FLAG_NOTIFIKASI = '" + flag_notifikasi + "'";
			}			
			if(!notread.equals("")){
				sql += " AND X.FLAG_READ = '"+notread+"'";
			}

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt("BIL");
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer getFailSelesai() throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT COUNT(*) AS BIL"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLPHPHAKMILIK F, USERS H"
				+ " WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN IN (7,12,13) AND B.FLAG_PERJANJIAN = 'U' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS"
				+ " AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+)"
				+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.FLAG_AKTIF = 'Y' AND A.NO_FAIL IS NOT NULL"
				+ " AND A.ID_MASUK = H.USER_ID(+) AND E.FLAG_HAKMILIK = 'U' AND B.ID_STATUS IN (1610222,1610208,1610212)";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt("BIL");
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Integer getFailBelumSelesai() throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT COUNT(*) AS BIL"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLPHPHAKMILIK F, USERS H"
				+ " WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN IN (7,12,13) AND B.FLAG_PERJANJIAN = 'U' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS"
				+ " AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+)"
				+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.FLAG_AKTIF = 'Y' AND A.NO_FAIL IS NOT NULL"
				+ " AND A.ID_MASUK = H.USER_ID(+) AND E.FLAG_HAKMILIK = 'U' AND B.ID_STATUS NOT IN (1610222,1610208,1610212)";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt("BIL");
			
		} finally {
			if (db != null)
				db.close();
		}
	}
}
