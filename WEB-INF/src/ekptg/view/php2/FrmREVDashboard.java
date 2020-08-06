package ekptg.view.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.view.admin.Pengumuman;

public class FrmREVDashboard extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	static Logger myLogger = Logger.getLogger(FrmREVDashboard.class);
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

		context.put("perjanjianAktif", getPerjanjianAktif());
		context.put("perjanjianTamat", getBilPerjanjianTamat());
		context.put("perjanjianHampirTamat", getBilPerjanjianHampirTamat());
		context.put("kutipan", getJumlahKutipan());
		context.put("tunggakan", getJumlahTunggakan());
		context.put("check_notifikasi_aduan", getNotifikasiAduan("", user_negeri_login, userId, "", "NO"));
		context.put("bilTunggakanSewa", getBilTunggakanSewa());
		context.put("bilTidakDituntut", getBilTidakDituntutSewa());

		list_memo_aktif = logicPengumuman.getMemo("", "Aktif","1","","","","","0");
		context.put("list_memo_aktif", list_memo_aktif);

		context.put("defaultTab", "0");

		vm = "app/php2/REVDashboard.jsp";

		return vm;
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

	public Integer getPerjanjianAktif() throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT COUNT(*) AS BIL"
				+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPBAYARANPERLUDIBAYAR"
				+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+)"
				+ " AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+) AND TBLPFDFAIL.ID_URUSAN = 115"
				+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
				+ " AND TBLPHPBAYARANPERLUDIBAYAR.STATUS = '1'";

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt("BIL");

		} finally {
			if (db != null)
				db.close();
		}
	}
	public int getBilPerjanjianTamat() throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT COUNT(*) AS BIL"
					+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPBAYARANPERLUDIBAYAR"
					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+)"
					+ " AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+) AND TBLPFDFAIL.ID_URUSAN = 115"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.STATUS IN  ('2','3')";

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			return rs.getInt("BIL");

		} finally {
			if (db != null)
				db.close();
		}
	}

	public int getBilPerjanjianHampirTamat() throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT COUNT(*) AS BIL"
					+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPBAYARANPERLUDIBAYAR"
					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+)"
					+ " AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+) AND TBLPFDFAIL.ID_URUSAN = 115"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.STATUS = '1'"
					+ " AND (TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT - TO_DATE(SYSDATE))<= 90"
					+ " AND (TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT - TO_DATE(SYSDATE)) > 0";

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			return rs.getInt("BIL");

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getJumlahKutipan() throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT TO_CHAR(SUM(NVL(KREDIT,0)),'999,999,990.99') AS TOTAL FROM TBLPHPAKAUN WHERE FLAG_AKTIF = 'Y'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return Utils.RemoveComma(rs.getString("TOTAL"));

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getJumlahTunggakan() throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT TO_CHAR(SUM(NVL(DEBIT,0)-NVL(KREDIT,0)),'999,999,990.99') AS TOTAL FROM TBLPHPAKAUN WHERE FLAG_AKTIF = 'Y'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return Utils.RemoveComma(rs.getString("TOTAL"));

		} finally {
			if (db != null)
				db.close();
		}
	}

	public int getBilTunggakanSewa() throws Exception {

		Db db = null;
		String sql = "";
		int bilFail = 0;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT COUNT(*) AS BIL"
					+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPBAYARANPERLUDIBAYAR"
					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+)"
					+ " AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+) AND TBLPFDFAIL.ID_URUSAN = 115"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPHASIL.FLAG_TUNGGAKAN = 'Y'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilFail = rs.getInt("BIL");
			}
			return bilFail;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public int getBilTidakDituntutSewa() throws Exception {

		Db db = null;
		String sql = "";
		int bilFail = 0;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT COUNT(*) AS BIL"
					+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPBAYARANPERLUDIBAYAR"
					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+)"
					+ " AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+) AND TBLPFDFAIL.ID_URUSAN = 115"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U'"
					+ " AND TBLPHPHASIL.FLAG_TUNGGAKAND = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.STATUS = '2'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilFail = rs.getInt("BIL");
			}
			return bilFail;

		} finally {
			if (db != null)
				db.close();
		}
	}
}
