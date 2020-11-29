package ekptg.model.php2.online;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.openjpa.lib.log.Log;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSemakan;
//import ekptg.intergration.EkptgEmailSender;
import ekptg.model.php2.online.FrmAPBOnlineSenaraiFailData;
import ekptg.view.esaduan.EkptgEmailSender;

public class FrmAPBOnlineSenaraiFailData {

	static FrmAPBOnlineSenaraiFailData logic = new FrmAPBOnlineSenaraiFailData();
	static Logger myLog = Logger.getLogger(FrmAPBOnlineSenaraiFailData.class);
	private Vector senaraiFailBorangA = null;
	private Vector senaraiFailBorangB = null;
	private Vector senaraiFail = null;
	private Vector beanMaklumatPengarah = null;
	private Vector listPengarah = null;
	private Vector listPembeliPasir = null;
	private Vector beanMaklumatPembeliPasir = null;
	private Vector listProjek = null;
	private Vector listKoordinat = null;
	private Vector listPakar = null;
	private Vector beanMaklumatAmbilPasir = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatProjek = null;
	private Vector beanMaklumatPakar = null;
	private Vector beanMaklumatKoordinat = null;
	private Vector beanMaklumatHeader = null;
	private Vector beanMaklumatKawasanMohon = null;
	private Vector beanMaklumatLampiran = null;
	private Vector listLampiran = null;
	private Vector beanMaklumatDokumen = null;
	private Vector beanMaklumatPermohonanBorangA = null;
	private Vector beanPelesen = null;
	private Vector beanMaklumatBarge = null;
	private Vector senaraiBarge = null;
	private Vector listCarianLaporan = null;
	private Vector getListLaporan = null;
	private static Vector MaklumatLaporan = null;
	private Vector getListPasir = null;
	private Vector listCarian = null;
	private static Vector MaklumatKiraan = null;
	public Vector getListDokumen = null;
	private static Vector MaklumatDokumen = null;
	private Vector listDokumen = null;

	private Vector<Hashtable<String, String>> beanMaklumatPejabat = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String daftarBaruLesen(String idJenisPermohonan, String idJenisLesen, String idKaitanTujuan, String tujuanPengambilan,
			String tempoh, String ringkasanPengalaman, String undangUndang, String jenisPerniagaan, String flagLuar,
			String idNegeriPerairan, String lokasi, String luas, String idLuas, String noRujukanSurat, String TarikhSurat,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";
		String idUrusan = "9";
		String namaUser = "";
		String emelUser = "";
		String idKategoriUser = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", idUrusan);
			r.add("ID_SUBURUSAN", "57");
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("ID_LOKASIFAIL", "2"); // UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "4"); // DATA BARU ETAPP
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			sql = "SELECT A.USER_NAME, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, B.ID_BANDAR, B.ID_NEGERI,"
					+ " B.NO_FAX, B.NO_HP, B.NO_KP_BARU, B.NO_TEL, B.EMEL, B.KATEGORI"
					+ " FROM USERS A, USERS_ONLINE B"
					+ " WHERE A.USER_ID = B.USER_ID AND A.USER_ID = '" + userId + "'";
			ResultSet rsUserOnline = stmt.executeQuery(sql);

			// TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("UNDANG_UNDANG_DIPERBADANKAN", undangUndang);
			r.add("PEKERJAAN", jenisPerniagaan);

			if (rsUserOnline.next()) {
				idKategoriUser = rsUserOnline.getString("KATEGORI");
				if (!"".equals(idKategoriUser) && "Individu".equals(idKategoriUser)) {
					r.add("ID_KATEGORIPEMOHON", "1");
				} else {
					r.add("ID_KATEGORIPEMOHON", "2");
				}
				if (rsUserOnline.getString("USER_NAME") != null) {
					namaUser = rsUserOnline.getString("USER_NAME");
				}
				if (rsUserOnline.getString("EMEL") != null) {
					emelUser = rsUserOnline.getString("EMEL");
				}
				r.add("NAMA", namaUser);
				r.add("EMEL", emelUser);
				r.add("ALAMAT1_TETAP",
						rsUserOnline.getString("ALAMAT1") == null ? "" : rsUserOnline.getString("ALAMAT1"));
				r.add("ALAMAT2_TETAP",
						rsUserOnline.getString("ALAMAT2") == null ? "" : rsUserOnline.getString("ALAMAT2"));
				r.add("ALAMAT3_TETAP",
						rsUserOnline.getString("ALAMAT3") == null ? "" : rsUserOnline.getString("ALAMAT3"));
				r.add("POSKOD_TETAP", rsUserOnline.getString("POSKOD") == null ? "" : rsUserOnline.getString("POSKOD"));
				r.add("ID_BANDARTETAP",
						rsUserOnline.getString("ID_BANDAR") == null ? "99999" : rsUserOnline.getString("ID_BANDAR"));
				r.add("ID_NEGERITETAP",
						rsUserOnline.getString("ID_NEGERI") == null ? "99999" : rsUserOnline.getString("ID_NEGERI"));
				r.add("NO_FAX", rsUserOnline.getString("NO_FAX") == null ? "" : rsUserOnline.getString("NO_FAX"));
				r.add("NO_PENGENALAN",
						rsUserOnline.getString("NO_KP_BARU") == null ? "" : rsUserOnline.getString("NO_KP_BARU"));
				r.add("NO_TEL", rsUserOnline.getString("NO_TEL") == null ? "" : rsUserOnline.getString("NO_TEL"));
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			String TS = "to_date('" + TarikhSurat + "','dd/MM/yyyy')";

			// TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");

			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_FAIL", idFail);
			r.add("ID_JKPTG", "1");
			r.add("ID_STATUS", "");
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			Calendar currentDate = new GregorianCalendar();
			String noPermohonan = "JKPTG/BPHP/04/" + getKodUrusanByIdUrusan(idUrusan) + "/"
					+ currentDate.get(Calendar.YEAR) + "/" + File.getSeqNo(db, 4, Integer.parseInt(idUrusan), 0, 0, 0,
							false, false, currentDate.get(Calendar.YEAR), 0);
			r.add("NO_PERMOHONAN", noPermohonan);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("NO_RUJ_SURAT", noRujukanSurat);
			r.add("FLAG_AKTIF", 1);
			r.add("NO_SUBJAKET", 0);
			r.add("NO_JILID", 0);
			r.add("NO_RAYUAN", 0);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPMOHONNJDUALPERTAMA
			r = new SQLRenderer();
			long id = DB.getNextID("TBLPHPPMOHONNJDUALPERTAMA_SEQ");
			r.add("ID_PHPPMOHONNJDUALPERTAMA", id);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_RAYUAN", 0);
			if ("2".equals(idJenisLesen)) {
				r.add("ID_JENISTUJUAN", "3");
			} else if ("3".equals(idJenisLesen) || "4".equals(idJenisLesen)) {
				r.add("ID_JENISTUJUAN", "2");
			} else {
				r.add("ID_JENISTUJUAN", "");
			}
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
			r.add("TUJUAN_PENGAMBILAN", tujuanPengambilan);
			r.add("TEMPOH_DIPOHON", tempoh);
			r.add("ID_TEMPOH", 2);
			r.add("PENGALAMAN", ringkasanPengalaman);
			r.add("FLAG_LUAR_PERAIRANNEGERI", flagLuar);
			r.add("LUAS_DIPOHON", luas);
			r.add("ID_UNITLUAS", idLuas);
			r.add("LOKASI_PERMOHONAN", lokasi);
			r.add("ID_JENISPERMOHONAN", idJenisPermohonan);
			r.add("ID_JENIS_LESEN", idJenisLesen);
			r.add("ID_NEGERI_PERAIRAN", idNegeriPerairan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPMOHONNJDUALPERTAMA");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		}  finally {
			if (db != null)
				db.close();
		}
		session.setAttribute("idFail", idFailString);
		return idFailString;
	}

	public String daftarBaru(String idKaitanTujuan, String idJenistujuan, String undangUndang, String idJenisLesen,
			String tujuanPengambilan, String tempoh, String pengalaman, String idNegeri, String lokasi, String luas,
			String idLuas, String idJenisPengenalanIndividu, String idKategoriIndividu, String idKategoriPemohon,
			String idJantina, String idBangsa, String idBandar, String idNegeriSykt, String idBandarSykt,
			String idJenisPermohonan, String idPermohonanLama, String idFlagLuar, String jenisPerniagaan,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";
		String idUrusan = "9";
		String namaUser = "";
		String emelUser = "";
		String idKategoriUser = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", idUrusan);
			r.add("ID_SUBURUSAN", "57");
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("ID_LOKASIFAIL", "2"); // UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "4"); // DATA BARU ETAPP
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			sql = "SELECT A.USER_NAME,B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, B.ID_NEGERI,B.NO_FAX,"
					+ " B.NO_HP, B.NO_KP_BARU, B.NO_TEL, B.EMEL, B.ID_BANDAR" + " FROM USERS A, USERS_ONLINE B"
					+ " WHERE A.USER_ID = B.USER_ID AND A.USER_ID = '" + userId + "'";
			ResultSet rsUserOnline = stmt.executeQuery(sql);

			// TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("UNDANG_UNDANG_DIPERBADANKAN", undangUndang);
			r.add("PEKERJAAN", jenisPerniagaan);

			if (rsUserOnline.next()) {
				idKategoriUser = rsUserOnline.getString("KATEGORI");
				if (!"".equals(idKategoriUser) && "Individu".equals(idKategoriUser)) {
					r.add("ID_KATEGORIPEMOHON", "1");
				} else {
					r.add("ID_KATEGORIPEMOHON", "2");
				}
				if (rsUserOnline.getString("USER_NAME") != null) {
					namaUser = rsUserOnline.getString("USER_NAME");
				}
				if (rsUserOnline.getString("EMEL") != null) {
					emelUser = rsUserOnline.getString("EMEL");
				}
				r.add("NAMA", namaUser);
				r.add("EMEL", emelUser);
				r.add("ALAMAT1_TETAP",
						rsUserOnline.getString("ALAMAT1") == null ? "" : rsUserOnline.getString("ALAMAT1"));
				r.add("ALAMAT2_TETAP",
						rsUserOnline.getString("ALAMAT2") == null ? "" : rsUserOnline.getString("ALAMAT2"));
				r.add("ALAMAT3_TETAP",
						rsUserOnline.getString("ALAMAT3") == null ? "" : rsUserOnline.getString("ALAMAT3"));
				r.add("POSKOD_TETAP", rsUserOnline.getString("POSKOD") == null ? "" : rsUserOnline.getString("POSKOD"));
				r.add("ID_BANDARTETAP",
						rsUserOnline.getString("ID_BANDAR") == null ? "99999" : rsUserOnline.getString("ID_BANDAR"));
				r.add("ID_NEGERITETAP",
						rsUserOnline.getString("ID_NEGERI") == null ? "99999" : rsUserOnline.getString("ID_NEGERI"));
				r.add("NO_FAX", rsUserOnline.getString("NO_FAX") == null ? "" : rsUserOnline.getString("NO_FAX"));
				r.add("NO_PENGENALAN",
						rsUserOnline.getString("NO_KP_BARU") == null ? "" : rsUserOnline.getString("NO_KP_BARU"));
				r.add("NO_TEL", rsUserOnline.getString("NO_TEL") == null ? "" : rsUserOnline.getString("NO_TEL"));
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");

			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS", "");
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));

			Calendar currentDate = new GregorianCalendar();
			String noPermohonan = "JKPTG/BPHP/04/" + getKodUrusanByIdUrusan(idUrusan) + "/"
					+ currentDate.get(Calendar.YEAR) + "/" + File.getSeqNo(db, 4, Integer.parseInt(idUrusan), 0, 0, 0,
							false, false, currentDate.get(Calendar.YEAR), 0);
			r.add("NO_PERMOHONAN", noPermohonan);

			r.add("FLAG_AKTIF", 1);
			r.add("NO_SUBJAKET", 0);
			r.add("NO_JILID", 0);
			r.add("NO_RAYUAN", 0);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPMOHONNJDUALPERTAMA
			r = new SQLRenderer();
			long id = DB.getNextID("TBLPHPPMOHONNJDUALPERTAMA_SEQ");
			r.add("ID_PHPPMOHONNJDUALPERTAMA", id);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_RAYUAN", 0);
			if ("2".equals(idJenisLesen)) {
				r.add("ID_JENISTUJUAN", "3");
			} else if ("3".equals(idJenisLesen) || "4".equals(idJenisLesen)) {
				r.add("ID_JENISTUJUAN", "2");
			} else {
				r.add("ID_JENISTUJUAN", "");
			}
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
			r.add("TUJUAN_PENGAMBILAN", tujuanPengambilan);
			r.add("TEMPOH_DIPOHON", tempoh);
			r.add("ID_TEMPOH", 2);
			r.add("PENGALAMAN", pengalaman);
			r.add("FLAG_LUAR_PERAIRANNEGERI", idFlagLuar);
			r.add("LUAS_DIPOHON", luas);
			r.add("ID_UNITLUAS", idLuas);
			r.add("LOKASI_PERMOHONAN", lokasi);
			r.add("ID_JENISPERMOHONAN", idJenisPermohonan);
			r.add("ID_JENIS_LESEN", idJenisLesen);
			r.add("ID_NEGERI_PERAIRAN", idNegeri);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPMOHONNJDUALPERTAMA");
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
		session.setAttribute("idFail", idFailString);
		return idFailString;
	}

	public String getIdSuburusanstatus(String idSuburusan, String idStatus) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '" + idStatus
					+ "' AND ID_SUBURUSAN = '" + idSuburusan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_SUBURUSANSTATUS").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKodUrusanByIdUrusan(String idUrusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_URUSAN FROM TBLRUJURUSAN WHERE ID_URUSAN = '" + idUrusan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_URUSAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPermohonanDaftar(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.NO_PERMOHONAN");
			r.add("B.ID_KAITANTUJUAN");
			r.add("B.ID_JENISTUJUAN");
			r.add("B.ID_JENIS_LESEN");
			r.add("B.ID_JENISPERMOHONAN");
			r.add("B.TUJUAN_PENGAMBILAN");
			r.add("B.TEMPOH_DIPOHON");
			r.add("B.ID_TEMPOH");
			r.add("B.PENGALAMAN");
			r.add("B.FLAG_LUAR_PERAIRANNEGERI");
			r.add("B.LUAS_DIPOHON");
			r.add("B.ID_UNITLUAS");
			r.add("B.LOKASI_PERMOHONAN");
			r.add("B.ID_NEGERI_PERAIRAN");
			r.add("C.ID_PERMOHONAN", r.unquote("B.ID_PERMOHONAN"));
			r.add("A.ID_FAIL", r.unquote("C.ID_FAIL"));
			r.add("A.ID_FAIL", idFail);

			sql = r.getSQLSelect("TBLPFDFAIL A, TBLPHPPMOHONNJDUALPERTAMA B, TBLPERMOHONAN C");
			myLog.info("ID PERMOHONAN :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("idKaitanTujuan",
						rs.getString("ID_KAITANTUJUAN") == null ? "99999" : rs.getString("ID_KAITANTUJUAN"));
				h.put("idJenistujuan",
						rs.getString("ID_JENISTUJUAN") == null ? "99999" : rs.getString("ID_JENISTUJUAN"));
				h.put("idJenisPermohonan",
						rs.getString("ID_JENISPERMOHONAN") == null ? "" : rs.getString("ID_JENISPERMOHONAN"));
				h.put("idJenisLesen", rs.getString("ID_JENIS_LESEN") == null ? "" : rs.getString("ID_JENIS_LESEN"));
				h.put("tujuanPengambilan",
						rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs.getString("TUJUAN_PENGAMBILAN"));
				h.put("tempoh", rs.getString("TEMPOH_DIPOHON") == null ? "" : rs.getString("TEMPOH_DIPOHON"));
				h.put("idTempoh", rs.getString("ID_TEMPOH") == null ? "99999" : rs.getString("ID_TEMPOH"));
				h.put("pengalaman", rs.getString("PENGALAMAN") == null ? "" : rs.getString("PENGALAMAN"));
				h.put("idFlagLuar", rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? "99999"
						: rs.getString("FLAG_LUAR_PERAIRANNEGERI"));
				h.put("luas", rs.getString("LUAS_DIPOHON") == null ? "" : rs.getString("LUAS_DIPOHON"));
				h.put("idLuas", rs.getString("ID_UNITLUAS") == null ? "99999" : rs.getString("ID_UNITLUAS"));
				h.put("idNegeri",
						rs.getString("ID_NEGERI_PERAIRAN") == null ? "99999" : rs.getString("ID_NEGERI_PERAIRAN"));
				h.put("lokasi", rs.getString("LOKASI_PERMOHONAN") == null ? "" : rs.getString("LOKASI_PERMOHONAN"));
				beanMaklumatPermohonan.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void carianFail(String noFail, String noPermohonan, String tarikhPermohonan, String userId)
			throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, F.TUJUAN_PENGAMBILAN, C.NAMA, D.KETERANGAN, B.ID_STATUS,B.NO_RAYUAN, B.NO_PERMOHONAN, C.ID_PEMOHON, "
					+ " CASE WHEN F.ID_JENISPERMOHONAN = '2' THEN 'PEMBAHARUAN LESEN' ELSE 'PERMOHONAN BARU' END AS JENISPERMOHONAN, F.ID_PERMOHONANLAMA "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPBYRNSYRTKLLSNLESENAPB E, TBLPHPPMOHONNJDUALPERTAMA F"
					+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.FLAG_JENIS_FAIL = '4' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS(+)"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) AND A.ID_MASUK = '" + userId + "'";

			// noPermohonan
			if (noPermohonan != null) {
				if (!noPermohonan.trim().equals("")) {
					sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%' ||'" + noPermohonan.trim().toUpperCase()
							+ "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			// tarikhPermohonan
			if (tarikhPermohonan != null) {
				if (!tarikhPermohonan.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhPermohonan)).toUpperCase() + "'";
				}
			}

			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			myLog.info("sql = " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idPermohonanLama",
						rs.getString("ID_PERMOHONANLAMA") == null ? "" : rs.getString("ID_PERMOHONANLAMA"));
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("jenispermohonan",
						rs.getString("JENISPERMOHONAN") == null ? "" : rs.getString("JENISPERMOHONAN").toUpperCase());
				h.put("tarikhPermohonan",
						rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("tujuanPengambilan",
						rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs.getString("TUJUAN_PENGAMBILAN"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))
						|| "1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS"))) {
					h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				} else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null) {
					h.put("status", " PENDAFTARAN");
				} else {
					h.put("status", " SEDANG DIPROSES");
				}
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatHeader(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHeader = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, B.TARIKH_TERIMA, B.NO_PERMOHONAN, B.TUJUAN,B.FLAG_AKTIF, C.ID_PEMOHON, C.ID_KATEGORIPEMOHON, C.NAMA,B.NO_RAYUAN, G.NO_SIRI_LESEN,"
				+ " C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, C.NO_TEL, C.NO_FAX, B.ID_STATUS, E.KETERANGAN, F.FLAG_SAMBUNGAN, F.TUJUAN_PENGAMBILAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPPMOHONNJDUALPERTAMA F, TBLPHPJADUALKEDUALESENAPB G, USERS U"
				+ " WHERE B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL"
				+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_MASUK = U.USER_ID AND B.ID_PERMOHONAN = G.ID_PERMOHONAN(+)"
				+ " AND C.ID_NEGERITETAP = D.ID_NEGERI AND B.ID_STATUS = E.ID_STATUS(+) AND A.ID_FAIL = '" + idFail+ "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noRayuan", rs.getString("NO_RAYUAN") == null ? "0" : rs.getString("NO_RAYUAN"));
				h.put("urusan", "AKTA PELANTAR BENUA");
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("noLesen",
						rs.getString("NO_SIRI_LESEN") == null ? "" : rs.getString("NO_SIRI_LESEN").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON").toUpperCase());
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tujuanPengambilan",
						rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs.getString("TUJUAN_PENGAMBILAN"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
				if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))
						|| "1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS"))) {
					h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				} else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null) {
					h.put("status", " PENDAFTARAN");
				} else {
					h.put("status", " SEDANG DIPROSES");
				}
				h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF").toUpperCase());
				h.put("idKategoriPemohon", rs.getString("ID_KATEGORIPEMOHON") == null ? ""
						: rs.getString("ID_KATEGORIPEMOHON").toUpperCase());
				h.put("flagSambung", rs.getString("FLAG_SAMBUNGAN") == null ? "" : rs.getString("FLAG_SAMBUNGAN"));

				beanMaklumatHeader.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idFail", "");
				h.put("noFail", "");
				h.put("urusan", "");
				h.put("noPermohonan", "");
				h.put("idPermohonan", "");
				h.put("tarikhTerima", "");
				h.put("idStatus", "");
				h.put("status", "");
				h.put("flagAktif", "");
				h.put("idKategoriPemohon", "");
				h.put("flagSambung", "");
				beanMaklumatHeader.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

//yati tambah
	Vector setnamaPemohon = null;

	@SuppressWarnings("unchecked")
	public Vector setnamaPemohon(String idFail) throws Exception {

		setnamaPemohon = new Vector();
		setnamaPemohon.clear();

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT U.USER_NAME NAMA_PEMOHON"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPPMOHONNJDUALPERTAMA F, USERS U"
					+ " WHERE B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' "
					+ " AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_MASUK = U.USER_ID AND "
					+ " C.ID_NEGERITETAP = D.ID_NEGERI AND B.ID_STATUS = E.ID_STATUS(+) AND A.ID_FAIL = '" + idFail
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql setMaklumat Pemohon = " + sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				setnamaPemohon.addElement(h);
			}
			return setnamaPemohon;

		} finally {
			if (db != null)
				db.close();
		}
	}

	//yati tambah
	Vector setnoLesen = null;

	@SuppressWarnings("unchecked")
	public Vector setnoLesen(String idFail) throws Exception {

		setnoLesen = new Vector();
		setnoLesen.clear();

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT NO_SIRI_LESEN FROM TBLPHPJADUALKEDUALESENAPB  " + " WHERE ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql setMaklumat Pemohon = " + sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("NO_SIRI_LESEN", rs.getString("NO_SIRI_LESEN") == null ? "" : rs.getString("NO_SIRI_LESEN"));
				setnoLesen.addElement(h);
			}
			return setnoLesen;

		} finally {
			if (db != null)
				db.close();
		}
	}

//yati tambah
	Vector setIdJadualKeduaLesen = null;

	@SuppressWarnings("unchecked")
	public Vector setIdJadualKeduaLesen(String idFail) throws Exception {

		setIdJadualKeduaLesen = new Vector();
		setIdJadualKeduaLesen.clear();

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_JADUALKEDUALESENAPB FROM TBLPHPJADUALKEDUALESENAPB  " + " WHERE ID_FAIL = '" + idFail
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql setIdJadualKeduaLesen = " + sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idJadualKeduaLesen",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? "" : rs.getString("ID_JADUALKEDUALESENAPB"));
				setIdJadualKeduaLesen.addElement(h);
			}
			return setIdJadualKeduaLesen;

		} finally {
			if (db != null)
				db.close();
		}
	}

//yati tambah borang A
	public void setMaklumatPermohonanBorangA(String idJadualKeduaLesen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonanBorangA = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;

			sql = "SELECT A.ID_JADUALKEDUALESENAPB, A.NO_SIRI_LESEN, B.NAMA, B.ALAMAT1_TETAP, B.ALAMAT2_TETAP, B.ALAMAT3_TETAP, B.NO_TEL_PEJABAT "
					+ " FROM TBLPHPJADUALKEDUALESENAPB A, TBLPHPPEMEGANG B "
					+ " WHERE A.ID_JADUALKEDUALESENAPB = B.ID_JADUALKEDUALESENAPB "
					+ " AND A.ID_JADUALKEDUALESENAPB = '" + idJadualKeduaLesen + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("SQL headaer pasir : " + sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idJadualKedua",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? "" : rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("noLesen",
						rs.getString("NO_SIRI_LESEN") == null ? "" : rs.getString("NO_SIRI_LESEN").toUpperCase());
				h.put("namaPelesen", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("alamat1",
						rs.getString("ALAMAT1_TETAP") == null ? "" : rs.getString("ALAMAT1_TETAP").toUpperCase());
				h.put("alamat2",
						rs.getString("ALAMAT2_TETAP") == null ? "" : rs.getString("ALAMAT2_TETAP").toUpperCase());
				h.put("alamat3",
						rs.getString("ALAMAT3_TETAP") == null ? "" : rs.getString("ALAMAT3_TETAP").toUpperCase());
				h.put("noTel",
						rs.getString("NO_TEL_PEJABAT") == null ? "" : rs.getString("NO_TEL_PEJABAT").toUpperCase());

				beanMaklumatPermohonanBorangA.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idJadualKedua", "");
				h.put("noLesen", "");
				h.put("namaPelesen", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("noTel", "");
				beanMaklumatPermohonanBorangA.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiProjek(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listProjek = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_PROJEKLESENAPB");
			r.add("NAMA_PROJEK");

			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPPROJEKLESENAPB", "ID_PROJEKLESENAPB ASC");
			myLog.info("sql = " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idProjek", rs.getString("ID_PROJEKLESENAPB"));
				h.put("namaProjek",
						rs.getString("NAMA_PROJEK") == null ? "" : rs.getString("NAMA_PROJEK").toUpperCase());
				listProjek.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiKoordinat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKoordinat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_KOORDINATPERMOHONAN");
			r.add("LABEL_TITIK");
			r.add("DARJAH_U");
			r.add("MINIT_U");
			r.add("SAAT_U");
			r.add("DARJAH_T");
			r.add("MINIT_T");
			r.add("SAAT_T");

			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPKOORDINATPERMOHONAN", "ID_KOORDINATPERMOHONAN ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idKoordinat", rs.getString("ID_KOORDINATPERMOHONAN"));
				h.put("labelTitik", rs.getString("LABEL_TITIK") == null ? "" : rs.getString("LABEL_TITIK"));
				h.put("darjahU", rs.getString("DARJAH_U") == null ? "" : rs.getString("DARJAH_U"));
				h.put("minitU", rs.getString("MINIT_U") == null ? "" : rs.getString("MINIT_U"));
				h.put("saatU", rs.getString("SAAT_U") == null ? "" : rs.getString("SAAT_U"));
				h.put("darjahT", rs.getString("DARJAH_T") == null ? "" : rs.getString("DARJAH_T"));
				h.put("minitT", rs.getString("MINIT_T") == null ? "" : rs.getString("MINIT_T"));
				h.put("saatT", rs.getString("SAAT_T") == null ? "" : rs.getString("SAAT_T"));

				listKoordinat.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiPakar(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPakar = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_MAKLUMATPAKAR");
			r.add("NAMA");
			r.add("KELAYAKAN");
			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPMAKLUMATPAKAR", "ID_MAKLUMATPAKAR ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPakar", rs.getString("ID_MAKLUMATPAKAR"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("kelayakan", rs.getString("KELAYAKAN") == null ? "" : rs.getString("KELAYAKAN"));

				listPakar.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiPengarah(String idPemohon) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT A.ID_PENGARAH, A.NAMA, ID_JENISPENGENALAN, A.NO_PENGENALAN, "
					+ " A.ID_WARGANEGARA, A.ID_BANGSA,B.KETERANGAN, A.PEGANGAN_SAHAM AS SAHAM, "
					+ " (SELECT SUM(PEGANGAN_SAHAM) FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '" + idPemohon
					+ "') AS TOTAL, "
					+ " ROUND(A.PEGANGAN_SAHAM/(SELECT SUM(PEGANGAN_SAHAM) FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '"
					+ idPemohon + "')*100) AS PERCENTAGE, A.WARGANEGARA_LAIN, A.BANGSA_LAIN "
					+ " FROM TBLPHPPENGARAH A, TBLRUJWARGANEGARA B " + " WHERE A.ID_PEMOHON = '" + idPemohon
					+ "' AND A.ID_WARGANEGARA = B.ID_WARGANEGARA ";

			myLog.info("setSenaraiPengarah : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPengarah", rs.getString("ID_PENGARAH"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999" : rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("idWarganegara",
						rs.getString("ID_WARGANEGARA") == null ? "99999" : rs.getString("ID_WARGANEGARA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999" : rs.getString("ID_BANGSA"));
				h.put("saham", rs.getString("PERCENTAGE") == null ? "" : rs.getString("PERCENTAGE"));
				h.put("peganganSaham", rs.getString("SAHAM") == null ? "" : Util
								.formatDecimal(Double.valueOf(rs.getString("SAHAM"))));
				h.put("warganegara",
						rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("warga",
						rs.getString("WARGANEGARA_LAIN") == null ? "" : rs.getString("WARGANEGARA_LAIN").toUpperCase());
				h.put("bangsa", rs.getString("BANGSA_LAIN") == null ? "" : rs.getString("BANGSA_LAIN").toUpperCase());

				listPengarah.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiPembeliPasir(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPembeliPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEMBELIPASIR, NAMA FROM TBLPHPPEMBELIPASIR WHERE ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPembeliPasir", rs.getString("ID_PEMBELIPASIR"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));

				listPembeliPasir.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPembeliPasir(String idPembeliPasir) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPembeliPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_PEMBELIPASIR");
			r.add("NAMA");
			r.add("ALAMAT1");
			r.add("ALAMAT2");
			r.add("ALAMAT3");
			r.add("POSKOD");
			r.add("ID_NEGERI");
			r.add("ID_BANDAR");
			r.add("NO_TEL_PEJABAT");
			r.add("NO_FAX");
			r.add("FLAG_JENIS_PERJANJIAN");

			r.add("ID_PEMBELIPASIR", idPembeliPasir);

			sql = r.getSQLSelect("TBLPHPPEMBELIPASIR");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPembeliPasir", rs.getString("ID_PEMBELIPASIR"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999" : rs.getString("ID_NEGERI"));
				h.put("idBandar", rs.getString("ID_BANDAR") == null ? "99999" : rs.getString("ID_BANDAR"));
				h.put("noTel", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs.getString("NO_TEL_PEJABAT"));
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				h.put("idJenisPerjanjian", rs.getString("FLAG_JENIS_PERJANJIAN") == null ? "99999"
						: rs.getString("FLAG_JENIS_PERJANJIAN"));

				beanMaklumatPembeliPasir.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPengarah(String idPengarah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_PENGARAH");
			r.add("NAMA");
			r.add("ID_JENISPENGENALAN");
			r.add("NO_PENGENALAN");
			r.add("ID_WARGANEGARA");
			r.add("ID_BANGSA");
			r.add("PEGANGAN_SAHAM");
			r.add("WARGANEGARA_LAIN");
			r.add("BANGSA_LAIN");

			r.add("ID_PENGARAH", idPengarah);

			sql = r.getSQLSelect("TBLPHPPENGARAH");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPengarah", rs.getString("ID_PENGARAH"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999" : rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("idWarganegara",
						rs.getString("ID_WARGANEGARA") == null ? "99999" : rs.getString("ID_WARGANEGARA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999" : rs.getString("ID_BANGSA"));
				h.put("saham", rs.getString("PEGANGAN_SAHAM") == null ? ""
						: Util.formatDecimal(Double.valueOf(rs.getString("PEGANGAN_SAHAM"))));
				h.put("warga", rs.getString("WARGANEGARA_LAIN") == null ? "" : rs.getString("WARGANEGARA_LAIN"));
				h.put("bangsa", rs.getString("BANGSA_LAIN") == null ? "" : rs.getString("BANGSA_LAIN"));

				beanMaklumatPengarah.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	// yati tambah baru
	public String getNoFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_SUBURUSAN = '57'" + "AND ID_URUSAN = '9' AND ID_FAIL  = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql no. fail lama: " + sql);
			if (rs.next()) {
				return rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// yati tambah baru
	public String getNoFailPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_PERMOHONAN FROM TBLPERMOHONAN WHERE " + "ID_FAIL  = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql no. permohonan lama: " + sql);
			if (rs.next()) {
				return rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdFailByNoFail(String noFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_SUBURUSAN = '57'"
					+ "AND ID_URUSAN = '9' AND UPPER(NO_FAIL) = '" + noFail.trim().toUpperCase() + "'";

			ResultSet rs = stmt.executeQuery(sql);
			// myLog.info("sql id. fail lama: "+sql);
			if (rs.next()) {
				return rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// yati tambah
	public String getIdPermohonanByNoFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE " + "ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatProjek(String idProjek) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatProjek = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NAMA_PROJEK");
			r.add("ID_PROJEKLESENAPB", idProjek);

			sql = r.getSQLSelect("TBLPHPPROJEKLESENAPB");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("namaProjek",
						rs.getString("NAMA_PROJEK") == null ? "" : rs.getString("NAMA_PROJEK").toUpperCase());
				beanMaklumatProjek.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void removeProjek(String idProjek, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPROJEKLESENAPB
			r.add("ID_PROJEKLESENAPB", idProjek);

			sql = r.getSQLDelete("TBLPHPPROJEKLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "DEL", "FAIL [" + idProjek + "] DIHAPUSKAN");

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

	public void setMaklumatPakar(String idPakar) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPakar = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NAMA");
			r.add("KELAYAKAN");
			r.add("ID_MAKLUMATPAKAR", idPakar);

			sql = r.getSQLSelect("TBLPHPMAKLUMATPAKAR");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("kelayakan", rs.getString("KELAYAKAN") == null ? "" : rs.getString("KELAYAKAN").toUpperCase());
				beanMaklumatPakar.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatKoordinat(String idKoordinat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKoordinat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("LABEL_TITIK");
			r.add("DARJAH_U");
			r.add("MINIT_U");
			r.add("SAAT_U");
			r.add("DARJAH_T");
			r.add("MINIT_T");
			r.add("SAAT_T");
			r.add("ID_KOORDINATPERMOHONAN", idKoordinat);

			sql = r.getSQLSelect("TBLPHPKOORDINATPERMOHONAN");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("labelTitik", rs.getString("LABEL_TITIK") == null ? "" : rs.getString("LABEL_TITIK"));
				h.put("darjahU", rs.getString("DARJAH_U") == null ? "" : rs.getString("DARJAH_U"));
				h.put("minitU", rs.getString("MINIT_U") == null ? "" : rs.getString("MINIT_U"));
				h.put("saatU", rs.getString("SAAT_U") == null ? "" : rs.getString("SAAT_U"));
				h.put("darjahT", rs.getString("DARJAH_T") == null ? "" : rs.getString("DARJAH_T"));
				h.put("minitT", rs.getString("MINIT_T") == null ? "" : rs.getString("MINIT_T"));
				h.put("saatT", rs.getString("SAAT_T") == null ? "" : rs.getString("SAAT_T"));
				beanMaklumatKoordinat.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String savePengarah(String idPemohon, String idWarganegara, String nama, String idJenisPengenalan,
			String noPengenalan, String idBangsa, String saham, String warga, String bangsa, HttpSession session)
			throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPengarahString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENGARAH
			long idPengarah = DB.getNextID("TBLPHPPENGARAH_SEQ");
			idPengarahString = String.valueOf(idPengarah);
			r.add("ID_PENGARAH", idPengarah);
			r.add("ID_PEMOHON", idPemohon);
			r.add("NAMA", nama);
			r.add("ID_JENISPENGENALAN", idJenisPengenalan);
			r.add("NO_PENGENALAN", noPengenalan);
			r.add("ID_WARGANEGARA", idWarganegara);
			r.add("WARGANEGARA_LAIN", warga);
			r.add("ID_BANGSA", idBangsa);
			r.add("BANGSA_LAIN", bangsa);
			r.add("PEGANGAN_SAHAM", saham);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPENGARAH");
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
		return idPengarahString;
	}

	public void updatePengarah(String idPengarah, String idWarganegara, String nama, String idJenisPengenalan,
			String idBangsa, String noPengenalan, String saham, String warga, String bangsa, HttpSession session)
			throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENGARAH
			r.update("ID_PENGARAH", idPengarah);
			r.add("NAMA", nama);
			r.add("ID_JENISPENGENALAN", idJenisPengenalan);
			r.add("NO_PENGENALAN", noPengenalan);
			r.add("ID_WARGANEGARA", idWarganegara);
			r.add("ID_BANGSA", idBangsa);
			r.add("PEGANGAN_SAHAM", Utils.RemoveSymbol((String) saham));
			r.add("WARGANEGARA_LAIN", warga);
			r.add("BANGSA_LAIN", bangsa);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPPENGARAH");
			myLog.info("sql = " + sql);

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

	public void removePengarah(String idPengarah) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENGARAH
			r.add("ID_PENGARAH", idPengarah);

			sql = r.getSQLDelete("TBLPHPPENGARAH");
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

	public boolean checkPercentagePengarah(String idPemohon, String saham) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		if (saham != null && saham.trim().length() != 0) {
			total = total + Double.valueOf(saham);
		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PEGANGAN_SAHAM FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '" + idPemohon + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				total = total + rs.getDouble("PEGANGAN_SAHAM");
			}

			if (total > 100) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkPercentagePengarahForUpdate(String idPemohon, String idPengarah, String saham)
			throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		if (saham != null && saham.trim().length() != 0) {
			total = total + Double.valueOf(saham);
		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PEGANGAN_SAHAM FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '" + idPemohon
					+ "' AND ID_PENGARAH != '" + idPengarah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				total = total + rs.getDouble("PEGANGAN_SAHAM");
			}

			if (total > 100) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String savePembeliPasir(String idPermohonan, String nama, String alamat1, String alamat2, String alamat3,
			String poskod, String idNegeri, String idBandar, String noTel, String noFax, String idJenisPerjanjian,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPembeliPasirString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEMBELIPASIR
			long idPembeliPasir = DB.getNextID("TBLPHPPEMBELIPASIR_SEQ");
			idPembeliPasirString = String.valueOf(idPembeliPasir);
			r.add("ID_PEMBELIPASIR", idPembeliPasir);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA", nama);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("NO_TEL_PEJABAT", noTel);
			r.add("NO_FAX", noFax);
			r.add("FLAG_JENIS_PERJANJIAN", idJenisPerjanjian);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMBELIPASIR");
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
		return idPembeliPasirString;
	}

	public void updatePembeliPasir(String idPembeliPasir, String nama, String alamat1, String alamat2, String alamat3,
			String poskod, String idNegeri, String idBandar, String noTel, String noFax, String idJenisPerjanjian,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEMBELIPASIR
			r.update("ID_PEMBELIPASIR", idPembeliPasir);

			r.add("NAMA", nama);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("NO_TEL_PEJABAT", noTel);
			r.add("NO_FAX", noFax);
			r.add("FLAG_JENIS_PERJANJIAN", idJenisPerjanjian);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMBELIPASIR");
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

	public void removePembeliPasir(String idPembeliPasir) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEMBELIPASIR
			r.add("ID_PEMBELIPASIR", idPembeliPasir);

			sql = r.getSQLDelete("TBLPHPPEMBELIPASIR");
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

	public String saveProjek(String idPermohonan, String nama, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idProjekString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPROJEKLESENAPB
			long idProjek = DB.getNextID("TBLPHPPROJEKLESENAPB_SEQ");
			idProjekString = String.valueOf(idProjek);
			r.add("ID_PROJEKLESENAPB", idProjek);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA_PROJEK", nama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPROJEKLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "INS", "FAIL [" + idPermohonan + "] DIDAFTARKAN");

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
		return idProjekString;
	}

	public void updateProjek(String idProjek, String nama, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPROJEKLESENAPB
			r.update("ID_PROJEKLESENAPB", idProjek);
			r.add("NAMA_PROJEK", nama);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPROJEKLESENAPB");
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

	public void removeProjek(String idProjek) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPROJEKLESENAPB
			r.add("ID_PROJEKLESENAPB", idProjek);

			sql = r.getSQLDelete("TBLPHPPROJEKLESENAPB");
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

	public String savePakar(String idPermohonan, String nama, String kelayakan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPakarString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMAKLUMATPAKAR
			long idPakar = DB.getNextID("TBLPHPMAKLUMATPAKAR_SEQ");
			idPakarString = String.valueOf(idPakar);
			r.add("ID_MAKLUMATPAKAR", idPakar);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA", nama);
			r.add("KELAYAKAN", kelayakan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMAKLUMATPAKAR");
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
		return idPakarString;
	}

	public void updatePakar(String idPakar, String nama, String kelayakan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMAKLUMATPAKAR
			r.update("ID_MAKLUMATPAKAR", idPakar);
			r.add("NAMA", nama);
			r.add("KELAYAKAN", kelayakan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPMAKLUMATPAKAR");
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

	public void removePakar(String idPakar) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMAKLUMATPAKAR
			r.add("ID_MAKLUMATPAKAR", idPakar);

			sql = r.getSQLDelete("TBLPHPMAKLUMATPAKAR");
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

	public String saveKoordinat(String idPermohonan, String labelTitik, String darjahU, String minitU, String saatU,
			String darjahT, String minitT, String saatT, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idKoordinatString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPKOORDINATPERMOHONAN
			long idKoordinat = DB.getNextID("TBLPHPKOORDINATPERMOHONAN_SEQ");
			idKoordinatString = String.valueOf(idKoordinat);
			r.add("ID_KOORDINATPERMOHONAN", idKoordinat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("LABEL_TITIK", labelTitik);
			r.add("DARJAH_U", darjahU);
			r.add("MINIT_U", minitU);
			r.add("SAAT_U", saatU);
			r.add("DARJAH_T", darjahT);
			r.add("MINIT_T", minitT);
			r.add("SAAT_T", saatT);
			r.add("FLAG_HISTORY", "N");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKOORDINATPERMOHONAN");
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
		return idKoordinatString;
	}

	public void updateKoordinat(String idKoordinat, String labelTitik, String darjahU, String minitU, String saatU,
			String darjahT, String minitT, String saatT, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPKOORDINATPERMOHONAN
			r.update("ID_KOORDINATPERMOHONAN", idKoordinat);
			r.add("LABEL_TITIK", labelTitik);
			r.add("DARJAH_U", darjahU);
			r.add("MINIT_U", minitU);
			r.add("SAAT_U", saatU);
			r.add("DARJAH_T", darjahT);
			r.add("MINIT_T", minitT);
			r.add("SAAT_T", saatT);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKOORDINATPERMOHONAN");
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

	public void removeKoordinat(String idKoordinat) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPKOORDINATPERMOHONAN
			r.add("ID_KOORDINATPERMOHONAN", idKoordinat);

			sql = r.getSQLDelete("TBLPHPKOORDINATPERMOHONAN");
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

	public void setMaklumatPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("B.ID_KAITANTUJUAN");
			r.add("B.ID_JENISTUJUAN");
			r.add("B.ID_JENIS_LESEN");
			r.add("B.ID_JENISPERMOHONAN");
			r.add("B.TUJUAN_PENGAMBILAN");
			r.add("B.TEMPOH_DIPOHON");
			r.add("B.ID_TEMPOH");
			r.add("B.PENGALAMAN");
			r.add("B.FLAG_LUAR_PERAIRANNEGERI");
			r.add("B.LUAS_DIPOHON");
			r.add("B.ID_UNITLUAS");
			r.add("B.LOKASI_PERMOHONAN");
			r.add("B.ID_NEGERI_PERAIRAN");
			r.add("C.TUJUAN");
			r.add("C.NO_RUJ_SURAT");
			r.add("C.TARIKH_SURAT");
			r.add("C.TARIKH_TERIMA");
			r.add("D.ID_PEMOHON");
			r.add("D.MODAL_DIBENARKAN");
			r.add("D.MODAL_JELAS");
			r.add("D.UNDANG_UNDANG_DIPERBADANKAN");
			r.add("D.PEKERJAAN");
			r.add("D.MODAL_OPERASI");
			r.add("D.MODAL_OPERASI_1");
			r.add("C.ID_PERMOHONAN", r.unquote("B.ID_PERMOHONAN"));
			r.add("A.ID_FAIL", r.unquote("C.ID_FAIL"));
			r.add("C.ID_PEMOHON", r.unquote("D.ID_PEMOHON"));
			r.add("B.ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPFDFAIL A,TBLPHPPMOHONNJDUALPERTAMA B, TBLPERMOHONAN C,TBLPHPPEMOHON D ");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idKaitanTujuan",
						rs.getString("ID_KAITANTUJUAN") == null ? "99999" : rs.getString("ID_KAITANTUJUAN"));
				h.put("idJenistujuan",
						rs.getString("ID_JENISTUJUAN") == null ? "99999" : rs.getString("ID_JENISTUJUAN"));
				h.put("idJenisPermohonan",
						rs.getString("ID_JENISPERMOHONAN") == null ? "" : rs.getString("ID_JENISPERMOHONAN"));
				h.put("idJenisLesen", rs.getString("ID_JENIS_LESEN") == null ? "" : rs.getString("ID_JENIS_LESEN"));
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("noRujSurat", rs.getString("NO_RUJ_SURAT") == null ? "" : rs.getString("NO_RUJ_SURAT"));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("perkara", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("tujuanPengambilan",
						rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs.getString("TUJUAN_PENGAMBILAN"));
				h.put("tempoh", rs.getString("TEMPOH_DIPOHON") == null ? "" : rs.getString("TEMPOH_DIPOHON"));
				h.put("idTempoh", rs.getString("ID_TEMPOH") == null ? "99999" : rs.getString("ID_TEMPOH"));
				h.put("pengalaman", rs.getString("PENGALAMAN") == null ? "" : rs.getString("PENGALAMAN"));
				h.put("idFlagLuar", rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? "99999"
						: rs.getString("FLAG_LUAR_PERAIRANNEGERI"));
				h.put("luas", rs.getString("LUAS_DIPOHON") == null ? "" : rs.getString("LUAS_DIPOHON"));
				h.put("idLuas", rs.getString("ID_UNITLUAS") == null ? "99999" : rs.getString("ID_UNITLUAS"));
				h.put("idNegeri",
						rs.getString("ID_NEGERI_PERAIRAN") == null ? "99999" : rs.getString("ID_NEGERI_PERAIRAN"));
				h.put("idPemohon", rs.getString("ID_PEMOHON"));
				h.put("lokasi", rs.getString("LOKASI_PERMOHONAN") == null ? "" : rs.getString("LOKASI_PERMOHONAN"));
				h.put("modalBenar", rs.getString("MODAL_DIBENARKAN") == null ? ""
						: Util.formatDecimal(Double.valueOf(rs.getString("MODAL_DIBENARKAN"))));
				h.put("modalJelas", rs.getString("MODAL_JELAS") == null ? ""
						: Util.formatDecimal(Double.valueOf(rs.getString("MODAL_JELAS"))));
				// baru tambah 29092020
				h.put("undangUndang", rs.getString("UNDANG_UNDANG_DIPERBADANKAN") == null ? ""
						: rs.getString("UNDANG_UNDANG_DIPERBADANKAN"));
				// ADD 13112020
				h.put("jenisPerniagaan", rs.getString("PEKERJAAN") == null ? "" : rs.getString("PEKERJAAN"));
				h.put("jumlahModal", rs.getString("MODAL_OPERASI") == null ? "" : rs.getString("MODAL_OPERASI"));
				h.put("jumlahModal1", rs.getString("MODAL_OPERASI_1") == null ? "" : rs.getString("MODAL_OPERASI_1"));
				beanMaklumatPermohonan.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setBeanMaklumatKawasanMohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKawasanMohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, C.ID_NEGERI_PERAIRAN, C.FLAG_LUAR_PERAIRANNEGERI, C.ID_UNITLUAS,"
					+ " C.LOKASI_PERMOHONAN, C.LUAS_DIPOHON"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPMOHONNJDUALPERTAMA C"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN" + " AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idFlagLuar", rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? ""
						: rs.getString("FLAG_LUAR_PERAIRANNEGERI").toUpperCase());
				h.put("idNegeriPerairan", rs.getString("ID_NEGERI_PERAIRAN") == null ? ""
						: rs.getString("ID_NEGERI_PERAIRAN").toUpperCase());
				h.put("lokasi", rs.getString("LOKASI_PERMOHONAN") == null ? "" : rs.getString("LOKASI_PERMOHONAN"));
				h.put("idLuas", rs.getString("ID_UNITLUAS") == null ? "" : rs.getString("ID_UNITLUAS"));
				h.put("luas", rs.getString("LUAS_DIPOHON") == null ? "" : rs.getString("LUAS_DIPOHON"));

				beanMaklumatKawasanMohon.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePermohonan(String idFail, String idPermohonan, String idPemohon, String idKaitanTujuan,
			String tujuanPengambilan, String tempoh, String pengalaman, String idNegeri, String lokasi, String luas,
			String idLuas, String modalBenar, String modalJelas, String idJenistujuan, String idJenisLesen,
			String undangUndang, String jenisPerniagaan, String jumlahModal, String jumlahModal1,
			String noRujukanSurat, String TarikhSurat, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEMOHON
			r.update("ID_PEMOHON", idPemohon);
			r.add("MODAL_DIBENARKAN", Utils.RemoveSymbol(modalBenar));
			r.add("MODAL_JELAS", Utils.RemoveSymbol(modalJelas));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("UNDANG_UNDANG_DIPERBADANKAN", undangUndang);
			// ADD 13/11/2020
			r.add("PEKERJAAN", jenisPerniagaan);
			r.add("MODAL_OPERASI", jumlahModal); // jumlah modal untuk operasi BG BORANG1 (i)
			r.add("MODAL_OPERASI_1", jumlahModal1); // jumlah modal untuk operasi BG BORANG1 (ii)

			sql = r.getSQLUpdate("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			// TBLPHPPMOHONNJDUALPERTAMA
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
			r.add("TUJUAN_PENGAMBILAN", tujuanPengambilan);
			r.add("TEMPOH_DIPOHON", tempoh);
			r.add("ID_TEMPOH", 2);
			r.add("PENGALAMAN", pengalaman);
			r.add("FLAG_LUAR_PERAIRANNEGERI", "1");
			r.add("LUAS_DIPOHON", luas);
			r.add("ID_UNITLUAS", idLuas);
			r.add("LOKASI_PERMOHONAN", lokasi);
			r.add("ID_NEGERI_PERAIRAN", idNegeri);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPPMOHONNJDUALPERTAMA");
			stmt.executeUpdate(sql);

			String TS = "to_date('" + TarikhSurat + "','dd/MM/yyyy')";

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("NO_RUJ_SURAT", noRujukanSurat);
			sql = r.getSQLUpdate("TBLPERMOHONAN");
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

	public void updatePermohonanEmel(String idFail, String idPermohonan, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String namaUser = "";
		String emelUser = "";
		String idhakmilikPermohonan = "";
		String noPermohonan = "";
		String idSuburusan = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT B.ID_HAKMILIKPERMOHONAN, A.NO_PERMOHONAN, C.ID_SUBURUSAN "
					+ " FROM TBLPERMOHONAN A,TBLPHPHAKMILIKPERMOHONAN B, TBLPFDFAIL C WHERE "
					+ " C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rsPermohonan = stmt.executeQuery(sql);
			if (rsPermohonan.next()) {
				idhakmilikPermohonan = rsPermohonan.getString("ID_HAKMILIKPERMOHONAN");
				noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
				idSuburusan = rsPermohonan.getString("ID_SUBURUSAN");
			}

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610197");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610197")); // MAKLUMAT PERMOHONAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			if (!"".equals(namaUser) && !"".equals(emelUser)) {
				EkptgEmailSender email = EkptgEmailSender.getInstance();
				email.FROM = "etapp_webmaster@kptg.gov.my";
				email.RECIEPIENT = emelUser;
				email.SUBJECT = "PERMOHONAN LESEN AKTA PELANTAR BENUA #" + noPermohonan;
				email.MESSAGE = namaUser.toUpperCase() + "."
						+ "<br><br>Permohonan anda telah diterima.Sila gunakan nombor permohonan diatas sebagai rujukan."
						+ "Anda akan dimaklumkan setelah permohonan ini telah didaftarkan." + "<br><br>Terima Kasih.";
				email.sendEmail();
			}

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

	public boolean checkMaklumatAPBLengkap(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = true;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FLAG_LUAR_PERAIRANNEGERI FROM TBLPHPPMOHONNJDUALPERTAMA WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				if (!"".equals(rs.getString("FLAG_LUAR_PERAIRANNEGERI"))
						|| rs.getString("FLAG_LUAR_PERAIRANNEGERI") != null) {

					sql = "SELECT ID_PERMOHONAN FROM TBLPHPPMOHONNJDUALPERTAMA WHERE "
							+ "(ID_NEGERI_PERAIRAN IS NOT NULL OR LOKASI_PERMOHONAN IS NULL OR LUAS_DIPOHON IS NULL OR ID_UNITLUAS IS NULL)"
							+ " AND ID_PERMOHONAN= '" + idPermohonan + "'";

					ResultSet rs2 = stmt.executeQuery(sql);
					if (rs2.next()) {
						bool = false;
					} else {
						bool = true;
					}

				} else {
					bool = true;
				}

			} else {
				bool = true;
			}

		} catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}

	public boolean checkSenaraiSemak(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = true;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SEMAKANHANTAR FROM TBLSEMAKANHANTAR WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				if ("1".equals(rs.getString("ID_SEMAKANHANTAR")) || "2".equals(rs.getString("ID_SEMAKANHANTAR"))) {

					sql = "SELECT ID_PERMOHONAN FROM TBLSEMAKANHANTAR WHERE "
							+ "(ID_SEMAKANHANTAR IS NULL OR ID_DOKUMEN IS NULL OR ID_SEMAKANSENARAI IS NULL)"
							+ " AND ID_PERMOHONAN= '" + idPermohonan + "'";

					ResultSet rs2 = stmt.executeQuery(sql);

					if (rs2.next()) {
						bool = false;
					} else {
						bool = true;
					}

				} else {
					bool = true;
				}

			} else {
				bool = true;
			}

		} catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}

	public void hapusPermohonan(String idFail) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPMOHONNJDUALPERTAMA
			sql = "DELETE FROM TBLPHPPMOHONNJDUALPERTAMA WHERE ID_PERMOHONAN IN"
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN  WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);

			// TBLPHPPENGARAH
			sql = "DELETE FROM TBLPHPPENGARAH  WHERE ID_PEMOHON IN "
					+ "(SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + ")))";
			stmt.executeUpdate(sql);

			// TBLPHPPEMBELIPASIR
			sql = "DELETE FROM TBLPHPPEMBELIPASIR WHERE ID_PERMOHONAN IN"
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);

			// TBLPHPPROJEKLESENAPB
			sql = "DELETE FROM TBLPHPPROJEKLESENAPB WHERE ID_PERMOHONAN IN"
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);

			// TBLPHPMAKLUMATPAKAR
			sql = "DELETE FROM TBLPHPMAKLUMATPAKAR WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);

			// TBLPHPKOORDINATPERMOHONAN
			sql = "DELETE FROM TBLPHPKOORDINATPERMOHONAN WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);

			// TBLPHPBYRNSYRTKLLSNLESENAPB
			sql = "DELETE FROM TBLPHPBYRNSYRTKLLSNLESENAPB WHERE ID_PERMOHONAN IN"
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);

			// TBLPHPPEMOHON
			sql = "DELETE FROM TBLPHPPEMOHON WHERE ID_PEMOHON IN "
					+ "(SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + ")))";
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			sql = "DELETE FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);

			// TBLPFDFAIL
			sql = "DELETE FROM TBLPFDFAIL WHERE ID_FAIL IN (" + idFail + ")";
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.ID_PEMOHON");
			r.add("C.FLAG_INDIVIDU");
			r.add("C.ID_KATEGORIPEMOHON");
			r.add("C.NAMA");
			r.add("C.ID_JENISPENGENALAN");
			r.add("C.NO_PENGENALAN");
			r.add("C.PEKERJAAN");
			r.add("C.ID_JANTINA");
			r.add("C.ID_BANGSA");
			r.add("C.ALAMAT1_TETAP");
			r.add("C.ALAMAT2_TETAP");
			r.add("C.ALAMAT3_TETAP");
			r.add("C.POSKOD_TETAP");
			r.add("C.ID_NEGERITETAP");
			r.add("C.ID_BANDARTETAP");
			r.add("C.NO_TEL_RUMAH");
			r.add("C.NO_TEL_PEJABAT");
			r.add("C.NO_TEL_BIMBIT");
			r.add("C.NO_FAX");
			r.add("C.EMEL");
			r.add("UNDANG_UNDANG_DIPERBADANKAN");

			r.add("A.ID_FAIL", r.unquote("B.ID_FAIL"));
			r.add("B.ID_PEMOHON", r.unquote("C.ID_PEMOHON"));
			r.add("A.ID_FAIL", idFail);

			sql = r.getSQLSelect("TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C");
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("getBeanMaklumatPemohon >> " + sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPemohon", rs.getString("ID_PEMOHON"));
				h.put("idKategoriIndividu", rs.getString("FLAG_INDIVIDU") == null ? "" : rs.getString("FLAG_INDIVIDU"));
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs.getString("ID_KATEGORIPEMOHON"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999" : rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("pekerjaan", rs.getString("PEKERJAAN") == null ? "" : rs.getString("PEKERJAAN"));
				h.put("idJantina", rs.getString("ID_JANTINA") == null ? "99999" : rs.getString("ID_JANTINA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999" : rs.getString("ID_BANGSA"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? "" : rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? "" : rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? "" : rs.getString("ALAMAT3_TETAP"));
				h.put("poskod", rs.getString("POSKOD_TETAP") == null ? "" : rs.getString("POSKOD_TETAP"));
				h.put("idNegeri", rs.getString("ID_NEGERITETAP") == null ? "99999" : rs.getString("ID_NEGERITETAP"));
				h.put("idBandar", rs.getString("ID_BANDARTETAP") == null ? "99999" : rs.getString("ID_BANDARTETAP"));
				h.put("noTel", rs.getString("NO_TEL_RUMAH") == null ? "" : rs.getString("NO_TEL_RUMAH"));
				h.put("noTelBim", rs.getString("NO_TEL_BIMBIT") == null ? "" : rs.getString("NO_TEL_BIMBIT"));
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("namaSykt", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalanSykt", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("pekerjaanSykt", rs.getString("PEKERJAAN") == null ? "" : rs.getString("PEKERJAAN"));
				h.put("alamat1Sykt", rs.getString("ALAMAT1_TETAP") == null ? "" : rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2Sykt", rs.getString("ALAMAT2_TETAP") == null ? "" : rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3Sykt", rs.getString("ALAMAT3_TETAP") == null ? "" : rs.getString("ALAMAT3_TETAP"));
				h.put("poskodSykt", rs.getString("POSKOD_TETAP") == null ? "" : rs.getString("POSKOD_TETAP"));
				h.put("idNegeriSykt",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs.getString("ID_NEGERITETAP"));
				h.put("idBandarSykt",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs.getString("ID_BANDARTETAP"));
				h.put("noTelSykt", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs.getString("NO_TEL_PEJABAT"));
				h.put("noFaxSykt", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				h.put("emelSykt", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("undangUndang", rs.getString("UNDANG_UNDANG_DIPERBADANKAN") == null ? ""
						: rs.getString("UNDANG_UNDANG_DIPERBADANKAN"));

				beanMaklumatPemohon.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	// BARU TAMBAH 21062020
	// SENARAI SEMAK
	public Vector getSenaraiSemak(String idPermohonan, String kategori) throws Exception {

		Db db = null;
		String sql = "";
		Vector senaraiSemak = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_RUJSENARAISEMAK, A.KETERANGAN,"
					+ " CASE WHEN A.ID_RUJSENARAISEMAK IN (SELECT ID_RUJSENARAISEMAK FROM TBLPHPSENARAISEMAK WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "')" + " THEN 'Y' END AS FLAG, "
					+ " CASE WHEN B.KETERANGAN = 'INDIVIDU' THEN '1' ELSE '2' END AS ID_KATEGORIPEMOHON "
					+ " FROM TBLPHPRUJSENARAISEMAK A, TBLRUJKATEGORIPEMOHON B "
					+ " WHERE A.ID_KATEGORIPEMOHON = B.ID_KATEGORIPEMOHON AND A.FLAG_AKTIF = 'Y' AND B.KETERANGAN = '"
					+ kategori + "' ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiSemak",
						rs.getString("ID_RUJSENARAISEMAK") == null ? "" : rs.getString("ID_RUJSENARAISEMAK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				senaraiSemak.addElement(h);
			}

		} catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return senaraiSemak;
	}

	public static void semakanTambah(String idsemakan, String idpermohonan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
			String idPermohonan = idpermohonan;
			String idSemakan = idsemakan;
			int idKementerian = 1;
			int idNegeri = 1;
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_semakanhantar", idSemakanhantar);
			r.add("id_permohonan", idPermohonan);
			r.add("id_semakansenarai", idSemakan);
			sql = r.getSQLInsert("tblsemakanhantar");
			myLog.info("semakanTambah : " + sql);
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	// UPDATE SENARAI SEMAK
	public void updateSenaraiSemak(String idPermohonan, String[] semaks, HttpSession session) throws Exception {

		String userId = (String) session.getAttribute("_ekptg_user_id");
		Db db = new Db();
		String sql = "";

		try {
			Connection conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r = new SQLRenderer();

			r.add("ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLDelete("TBLPHPSENARAISEMAK");
			stmt.executeUpdate(sql);

			for (int i = 0; i < semaks.length; i++) {
				r = new SQLRenderer();
				long ID_SENARAISEMAK = DB.getNextID("TBLPHPSENARAISEMAK_SEQ");
				r.add("ID_SENARAISEMAK", ID_SENARAISEMAK);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_RUJSENARAISEMAK", semaks[i]);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPHPSENARAISEMAK");
				stmt.executeUpdate(sql);
			}
			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD", "FAIL [" + idPermohonan + "] DIKEMASKINI");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// BARU TAMBAH 21062020
	// LAMPIRAN
	public void simpanKemaskiniLampiran(String idDokumen, String txtNamaLampiran, String txtCatatan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPDOKUMEN
			r.update("ID_DOKUMEN", idDokumen);
			r.add("NAMA_DOKUMEN", txtNamaLampiran);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			myLog.info("sql : " + sql);
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD", "FAIL [" + idDokumen + "] DIKEMASKINI");

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

	public void hapusLampiran(String idDokumen, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPDOKUMEN
			SQLRenderer r = new SQLRenderer();
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			myLog.info("sql : " + sql);
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "DEL", "FAIL [" + idDokumen + "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatLampiran(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, JENIS_MIME FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			myLog.info("sql : " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaLampiran",
						rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanLampiran", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("jenisMime", rs.getString("JENIS_MIME") == null ? ""
						: StringUtils.substringBefore(rs.getString("JENIS_MIME"), "/"));
				beanMaklumatLampiran.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiLampiran(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN" + " WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "' AND FLAG_DOKUMEN = 'L'"
					+ " AND ID_ULASANTEKNIKAL IS NULL AND ID_MESYUARAT IS NULL AND ID_PHPHAKMILIK IS NULL AND ID_PENAWARANKJP IS NULL"
					+ " ORDER BY ID_DOKUMEN DESC";

			myLog.info("sql : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				listLampiran.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateSenaraiSemak(String idPermohonanSewa, String txtTujuan, String socTempohSewa,
			String idLuasKegunaan, String idLuas, String txtLuasMohon1, String txtLuasMohon2, String txtLuasMohon3,
			String txtLuasBersamaan, String txtBakiLuas, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPERMOHONANSEWA
			r.update("ID_PHPPERMOHONANSEWA", idPermohonanSewa);
			r.add("TUJUAN", txtTujuan);
			r.add("FLAG_GUNA", idLuasKegunaan);
			r.add("ID_LUASMHN", idLuas);
			r.add("LUAS_MHN1", txtLuasMohon1);
			r.add("LUAS_MHN2", txtLuasMohon2);
			r.add("LUAS_MHN3", txtLuasMohon3);
			r.add("ID_LUASMHNBERSAMAAN", "2");
			r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
			r.add("ID_LUASBAKI", "2");
			r.add("LUAS_BAKI", txtBakiLuas);
			r.add("FLAG_TEMPOHSEWA", socTempohSewa);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			myLog.info("sql : " + sql);
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

	public Vector<Hashtable<String, String>> setMaklumatPejabatJKPTG() throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.NAMA_PEJABAT, B.NO_TEL, B.NO_FAX, B.EMEL, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, C.NAMA_NEGERI, B.KOD_JKPTG, D.NAMA_DAERAH, E.KETERANGAN AS NAMA_BANDAR, "
					+ "B.ID_PEJABATJKPTG FROM TBLPERMOHONAN A, TBLRUJPEJABATJKPTG B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJBANDAR E WHERE A.ID_JKPTG = B.ID_PEJABATJKPTG "
					+ "AND B.ID_NEGERI = C.ID_NEGERI(+) AND B.KOD_JKPTG = '16' AND B.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND B.ID_BANDAR = E.ID_BANDAR(+) ";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql 2 = " + sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabat", rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("namaPejabat",
						rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX").toUpperCase());
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				beanMaklumatPejabat.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return beanMaklumatPejabat;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatPermohonanBorangA() { // yati
		return beanMaklumatPermohonanBorangA;
	}

	public void setBeanMaklumatPermohonanBorangA(Vector beanMaklumatPermohonanBorangA) {
		this.beanMaklumatPermohonanBorangA = beanMaklumatPermohonanBorangA;
	}

	public Vector getBeanMaklumatHeader() {
		return beanMaklumatHeader;
	}

	public Vector getBeanMaklumatPengarah() {
		return beanMaklumatPengarah;
	}

	public void setBeanMaklumatPengarah(Vector beanMaklumatPengarah) {
		this.beanMaklumatPengarah = beanMaklumatPengarah;
	}

	public Vector getListPengarah() {
		return listPengarah;
	}

	public void setListPengarah(Vector listPengarah) {
		this.listPengarah = listPengarah;
	}

	public Vector getListPembeliPasir() {
		return listPembeliPasir;
	}

	public void setListPembeliPasir(Vector listPembeliPasir) {
		this.listPembeliPasir = listPembeliPasir;
	}

	public Vector getBeanMaklumatPembeliPasir() {
		return beanMaklumatPembeliPasir;
	}

	public void setBeanMaklumatPembeliPasir(Vector beanMaklumatPembeliPasir) {
		this.beanMaklumatPembeliPasir = beanMaklumatPembeliPasir;
	}

	public Vector getListProjek() {
		return listProjek;
	}

	public void setListProjek(Vector listProjek) {
		this.listProjek = listProjek;
	}

	public Vector getListKoordinat() {
		return listKoordinat;
	}

	public void setListKoordinat(Vector listKoordinat) {
		this.listKoordinat = listKoordinat;
	}

	public Vector getListPakar() {
		return listPakar;
	}

	public void setListPakar(Vector listPakar) {
		this.listPakar = listPakar;
	}

	public Vector getBeanMaklumatProjek() {
		return beanMaklumatProjek;
	}

	public void setBeanMaklumatProjek(Vector beanMaklumatProjek) {
		this.beanMaklumatProjek = beanMaklumatProjek;
	}

	public Vector getBeanMaklumatPakar() {
		return beanMaklumatPakar;
	}

	public void setBeanMaklumatPakar(Vector beanMaklumatPakar) {
		this.beanMaklumatPakar = beanMaklumatPakar;
	}

	public Vector getBeanMaklumatKoordinat() {
		return beanMaklumatKoordinat;
	}

	public void setBeanMaklumatKoordinat(Vector beanMaklumatKoordinat) {
		this.beanMaklumatKoordinat = beanMaklumatKoordinat;
	}

	public Vector getBeanMaklumatKawasanMohon() {
		return beanMaklumatKawasanMohon;
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getListLampiran() {
		return listLampiran;
	}

	public void setListLampiran(Vector listLampiran) {
		this.listLampiran = listLampiran;
	}

	public Vector getBeanMaklumatLampiran() {
		return beanMaklumatLampiran;
	}

	public void setBeanMaklumatLampiran(Vector beanMaklumatLampiran) {
		this.beanMaklumatLampiran = beanMaklumatLampiran;
	}

	// penambahan yati 18/8/2020
	public Vector getSenaraiFailBorangA() {
		return senaraiFailBorangA;
	}

	public Vector getBeanPelesen() {
		return beanPelesen;
	}

	public void setSenaraiFailBorangA(Vector senaraiFailBorangA) {
		this.senaraiFailBorangA = senaraiFailBorangA;
	}

	public Vector getBeanMaklumatAmbilPasir() {
		return beanMaklumatAmbilPasir;
	}

	public void setBeanMaklumatAmbilPasir(Vector beanMaklumatAmbilPasir) {
		this.beanMaklumatAmbilPasir = beanMaklumatAmbilPasir;
	}

	public Vector getBeanMaklumatBarge() {
		return beanMaklumatBarge;
	}

	public void setBeanMaklumatBarge(Vector beanMaklumatBarge) {
		this.beanMaklumatBarge = beanMaklumatBarge;
	}

	public Vector getSenaraiBarge() {
		return senaraiBarge;
	}

	public void setSenaraiBarge(Vector senaraiBarge) {
		this.senaraiBarge = senaraiBarge;
	}

	public static Vector getMaklumatLaporan() {
		return MaklumatLaporan;
	}

	// RETURN VALUES
	public Vector getListCarian() {
		return listCarian;
	}

	// yati tambah
	public void carianFailBorangA(String namaPelesen, String noLesen, String idJadualKeduaLesen) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFailBorangA = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_BORANGA, A.ID_JADUALKEDUALESENAPB, A.TAHUN, B.NAMA_BULAN "
					+ " FROM TBLPHPBORANGA A, TBLRUJBULAN B "
					+ " WHERE A.BULAN = B.ID_BULAN AND A.ID_JADUALKEDUALESENAPB = '" + idJadualKeduaLesen + "'";

			sql = sql + " ORDER BY ID_BORANGA DESC";
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql lesen senarai : " + sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idBorangA", rs.getString("ID_BORANGA") == null ? "" : rs.getString("ID_BORANGA"));
				h.put("idJadualKeduaLesen",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? "" : rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("bulan", rs.getString("NAMA_BULAN") == null ? "" : rs.getString("NAMA_BULAN"));
				h.put("tahun", rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN"));
				senaraiFailBorangA.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// penambahan yati 18/8/2020
	public Vector getSenaraiFailBorangB() {
		return senaraiFailBorangB;
	}

	public void setSenaraiFailBorangB(Vector senaraiFailBorangB) {
		this.senaraiFailBorangB = senaraiFailBorangB;
	}

	// yati tambah
	public void carianFailBorangB(String namaPelesen, String noLesen, String idJadualKeduaLesen) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFailBorangB = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_LAPORANPASIR,B.NAMA_BULAN,A.TAHUN_PENGAMBILAN,A.BULAN_PENGAMBILAN, A.ID_JADUALKEDUALESENAPB, ";
			sql += " A.JUMLAH_KUANTITI, A.JUMLAH_ROYALTI, A.TARIKH_PENGELUARAN ";
			sql += " FROM TBLPHPLAPORANPASIR A, TBLRUJBULAN B ";
			sql += " WHERE A.BULAN_PENGAMBILAN = B.ID_BULAN ";
			sql += " AND A.ID_JADUALKEDUALESENAPB = '" + idJadualKeduaLesen + "' ";
			sql += " ORDER BY A.TAHUN_PENGAMBILAN ASC, A.BULAN_PENGAMBILAN ASC ";

			myLog.info("SQL getListLaporan :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_jadualkedualesenAPB",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? "" : rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("id_laporanpasir",
						rs.getString("ID_LAPORANPASIR") == null ? "" : rs.getString("ID_LAPORANPASIR"));
				h.put("nama_bulan", rs.getString("NAMA_BULAN") == null ? "" : rs.getString("NAMA_BULAN"));
				h.put("tahun_pengambilan",
						rs.getString("TAHUN_PENGAMBILAN") == null ? "" : rs.getString("TAHUN_PENGAMBILAN"));
				h.put("bulan_pengambilan",
						rs.getString("BULAN_PENGAMBILAN") == null ? "" : rs.getString("BULAN_PENGAMBILAN"));
				h.put("jumlah_kuantiti",
						rs.getString("JUMLAH_KUANTITI") == null ? "" : rs.getString("JUMLAH_KUANTITI"));
				h.put("jumlah_royalti", rs.getString("JUMLAH_ROYALTI") == null ? "" : rs.getString("JUMLAH_ROYALTI"));
				h.put("tarikhPengeluaran",
						rs.getString("TARIKH_PENGELUARAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PENGELUARAN")));
				// namaPelesen
				/*
				 * if (namaPelesen != null) { if (!namaPelesen.trim().equals("")) { sql = sql +
				 * " AND UPPER(B.NAMA) LIKE '%' ||'" + namaPelesen.trim().toUpperCase() +
				 * "'|| '%'"; } }
				 */

				senaraiFailBorangB.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// yati tambah
	public void setMaklumatAmbilPasir(String idBorangA) throws Exception {
		Db db = null;
		String sql = "";

		try {

			beanMaklumatAmbilPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_BORANGA, A.ID_JADUALKEDUALESENAPB, A.TUJUAN, A.DESTINASI, A.ISIPADU, A.ANGGARAN_ROYALTI, A.BULAN, A.TAHUN, A.KONTRAKTOR,"
					+ " A.PEMBELI_PASIR, A.TARIKH_MULA_OPERASI, A.TARIKH_TAMAT_OPERASI, A.LALUAN_VESSEL, A.KAEDAH_PASIR, A.KAWASAN_PELUPUSAN,"
					+ " K.LABEL_TITIK, K.DARJAH_U, K.DARJAH_T, K.MINIT_U, K.MINIT_T, K.SAAT_U, K.SAAT_T"
					+ " FROM TBLPHPBORANGA A, TBLPHPKOORDINATPERMOHONAN K WHERE A.ID_BORANGA = K.ID_BORANGA(+) AND A.ID_BORANGA = '" + idBorangA + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql ambil pasir: " + sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idBorangA", rs.getString("ID_BORANGA") == null ? "" : rs.getString("ID_BORANGA"));
				h.put("idJadualKedua",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? "" : rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("tujuanAmbil", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("destinasiHantar", rs.getString("DESTINASI") == null ? "" : rs.getString("DESTINASI"));
				h.put("jumlahPasir", rs.getString("ISIPADU") == null ? "" : rs.getString("ISIPADU"));
				h.put("jumlahRoyalti", rs.getString("ANGGARAN_ROYALTI") == null ? ""
						: Util.formatDecimal(Double.valueOf(rs.getString("ANGGARAN_ROYALTI"))));
				h.put("bulan", rs.getString("BULAN") == null ? "" : rs.getString("BULAN"));
				h.put("tahun", rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN"));

				h.put("kontraktor", rs.getString("KONTRAKTOR") == null ? "" : rs.getString("KONTRAKTOR"));
				h.put("pembeli", rs.getString("PEMBELI_PASIR") == null ? "" : rs.getString("PEMBELI_PASIR"));
				h.put("tarikhMula",
						rs.getDate("TARIKH_MULA_OPERASI") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA_OPERASI")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT_OPERASI") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT_OPERASI")));
				h.put("laluan", rs.getString("LALUAN_VESSEL") == null ? "" : rs.getString("LALUAN_VESSEL"));
				h.put("kaedah", rs.getString("KAEDAH_PASIR") == null ? "" : rs.getString("KAEDAH_PASIR"));
				h.put("kawasan", rs.getString("KAWASAN_PELUPUSAN") == null ? "" : rs.getString("KAWASAN_PELUPUSAN"));

				h.put("labelTitik", rs.getString("LABEL_TITIK") == null ? "" : rs.getString("LABEL_TITIK"));
				h.put("darjahU", rs.getString("DARJAH_U") == null ? "" : rs.getString("DARJAH_U"));
				h.put("darjahT", rs.getString("DARJAH_T") == null ? "" : rs.getString("DARJAH_T"));
				h.put("minitU", rs.getString("MINIT_U") == null ? "" : rs.getString("MINIT_U"));
				h.put("minitT", rs.getString("MINIT_T") == null ? "" : rs.getString("MINIT_T"));
				h.put("saatU", rs.getString("SAAT_U") == null ? "" : rs.getString("SAAT_U"));
				h.put("saatT", rs.getString("SAAT_T") == null ? "" : rs.getString("SAAT_T"));

				beanMaklumatAmbilPasir.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// YATI TAMBAH
	public String simpanMaklumatAmbilPasir(String idJadualKedua, String idBulan, String tahun, String tujuanAmbil,
			String destinasiHantar, String jumlahPasir, String jumlahRoyalti, String kontraktor, String pembeli,
			String tarikhMula, String tarikhTamat, String laluan, String kaedah, String kawasan,
			String txtLabelTitik, String txtDarjahT, String txtDarjahU, String txtMinitT, String txtMinitU, String txtSaatT, String txtSaatU, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String sql2 = "";
		String idBorangAString = "";
		String idKoordinatString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			SQLRenderer r2 = new SQLRenderer();

			// TBLPHPBORANGA
			long idBorangA = DB.getNextID("TBLPHPBORANGA_SEQ");
			r.add("ID_BORANGA", idBorangA);
			idBorangAString = String.valueOf(idBorangA);
			r.add("ID_JADUALKEDUALESENAPB", idJadualKedua);

			r.add("TUJUAN", tujuanAmbil);
			r.add("BULAN", idBulan);
			r.add("TAHUN", tahun);
			r.add("DESTINASI", destinasiHantar);
			r.add("ISIPADU", jumlahPasir);
			r.add("ANGGARAN_ROYALTI", jumlahRoyalti);

			r.add("KONTRAKTOR", kontraktor);
			r.add("PEMBELI_PASIR", pembeli);

			String mula = "to_date('" + tarikhMula + "','dd/MM/yyyy')";
			String tamat = "to_date('" + tarikhTamat + "','dd/MM/yyyy')";

			r.add("TARIKH_MULA_OPERASI", r.unquote(mula));
			r.add("TARIKH_TAMAT_OPERASI", r.unquote(tamat));
			r.add("LALUAN_VESSEL", laluan);
			r.add("KAEDAH_PASIR", kaedah);
			r.add("KAWASAN_PELUPUSAN", kawasan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPBORANGA");
			myLog.info("sql simpan ambil pasir : " + sql);
			stmt.executeUpdate(sql);

			// TBLPHPKOORDINATPERMOHONAN
			r2.add("ID_BORANGA", idBorangA);

			r2.add("LABEL_TITIK", txtLabelTitik);
			r2.add("DARJAH_T", txtDarjahT);
			r2.add("DARJAH_U", txtDarjahU);
			r2.add("MINIT_T", txtMinitT);
			r2.add("MINIT_U", txtMinitU);
			r2.add("SAAT_T", txtSaatT);
			r2.add("SAAT_U", txtSaatU);

			r2.add("ID_MASUK", userId);
			r2.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql2 = r2.getSQLInsert("TBLPHPKOORDINATPERMOHONAN");
			myLog.info("sql simpan koordinat borang a : " + sql2);
			stmt.executeUpdate(sql2);

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
		return idBorangAString;
	}

	// yati tambah
	public void simpanKemaskiniMaklumatPasir(String idBorangA, String idBulan, String tahun, String tujuanAmbil,
			String destinasiHantar, String jumlahPasir, String jumlahRoyalti, String kontraktor, String pembeli,
			String tarikhMula, String tarikhTamat, String laluan, String kaedah, String kawasan,
			String txtLabelTitik, String txtDarjahT, String txtDarjahU, String txtMinitT, String txtMinitU, String txtSaatT, String txtSaatU, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String sql2 = "";
		String idKoordinatString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			SQLRenderer r2 = new SQLRenderer();

			// TBLPHPBORANGA
			r.update("ID_BORANGA", idBorangA);

			r.add("BULAN", idBulan);
			r.add("TAHUN", tahun);
			r.add("TUJUAN", tujuanAmbil);
			r.add("DESTINASI", destinasiHantar);
			r.add("ISIPADU", jumlahPasir);
			r.add("ANGGARAN_ROYALTI", jumlahRoyalti);

			r.add("KONTRAKTOR", kontraktor);
			r.add("PEMBELI_PASIR", pembeli);

			String mula = "to_date('" + tarikhMula + "','dd/MM/yyyy')";
			String tamat = "to_date('" + tarikhTamat + "','dd/MM/yyyy')";

			r.add("TARIKH_MULA_OPERASI", r.unquote(mula));
			r.add("TARIKH_TAMAT_OPERASI", r.unquote(tamat));
			r.add("LALUAN_VESSEL", laluan);
			r.add("KAEDAH_PASIR", kaedah);
			r.add("KAWASAN_PELUPUSAN", kawasan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPBORANGA");
			myLog.info("sql borang A : " + sql);
			stmt.executeUpdate(sql);

			// TBLPHPKOORDINATPERMOHONAN
			r2.update("ID_BORANGA", idBorangA);

			r2.add("LABEL_TITIK", txtLabelTitik);
			r2.add("DARJAH_T", txtDarjahT);
			r2.add("DARJAH_U", txtDarjahU);
			r2.add("MINIT_T", txtMinitT);
			r2.add("MINIT_U", txtMinitU);
			r2.add("SAAT_T", txtSaatT);
			r2.add("SAAT_U", txtSaatU);

			r2.add("ID_KEMASKINI", userId);
			r2.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql2 = r2.getSQLUpdate("TBLPHPKOORDINATPERMOHONAN");
			myLog.info("sql update koordinat borang a : " + sql2);
			stmt.executeUpdate(sql2);

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

	public void maklumatPelesen(String idJadualKedua) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			beanPelesen = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_JADUALKEDUALESENAPB, A.NO_SIRI_LESEN, B.NAMA"
					+ " FROM TBLPHPJADUALKEDUALESENAPB A, TBLPHPPEMEGANG B"
					+ " WHERE A.ID_JADUALKEDUALESENAPB = B.ID_JADUALKEDUALESENAPB AND A.ID_JADUALKEDUALESENAPB = '"
					+ idJadualKedua + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idJadualKedua",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? "" : rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("namaPelesen", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("noLesen", rs.getString("NO_SIRI_LESEN") == null ? "" : rs.getString("NO_SIRI_LESEN"));
				beanPelesen.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatBarge(String idBorangA, String namaDidaftarkan, String noPendaftaran, String kapasiti,
			String jenis, String noTel, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idBargeString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPBARGE
			long idBarge = DB.getNextID("TBLPHPBARGE_SEQ");
			r.add("ID_BARGE", idBarge);
			idBargeString = String.valueOf(idBarge);
			r.add("ID_BORANGA", idBorangA);

			r.add("NAMA_BARGE", namaDidaftarkan);
			r.add("NO_PENDAFTARAN", noPendaftaran);
			r.add("MUATAN", kapasiti);

			r.add("JENIS_BARGE", jenis);
			r.add("NO_TEL", noTel);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPBARGE");
			stmt.executeUpdate(sql);
			myLog.info("sql barge : " + sql);
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
		return idBargeString;
	}

	public void simpanKemaskiniMaklumatBarge(String idBarge, String namaDidaftarkan, String noPendaftaran,
			String kapasiti, String jenis, String noTel, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPBARGE
			r.update("ID_BARGE", idBarge);

			r.add("NAMA_BARGE", namaDidaftarkan);
			r.add("NO_PENDAFTARAN", noPendaftaran);
			r.add("MUATAN", kapasiti);
			r.add("JENIS_BARGE", jenis);
			r.add("NO_TEL", noTel);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPBARGE");
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

	public void hapusBarge(String idBarge, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPBARGE
			r.add("ID_BARGE", idBarge);

			sql = r.getSQLDelete("TBLPHPBARGE");
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

	public void carianBarge(String idBorangA) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiBarge = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_BARGE, A.ID_BORANGA, A.NAMA_BARGE" + " FROM TBLPHPBARGE A, TBLPHPBORANGA B"
					+ " WHERE A.ID_BORANGA = B.ID_BORANGA AND A.ID_BORANGA = '" + idBorangA + "'";

			sql = sql + " ORDER BY ID_BARGE DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idBarge", rs.getString("ID_BARGE") == null ? "" : rs.getString("ID_BARGE"));
				h.put("namaDidaftarkan",
						rs.getString("NAMA_BARGE") == null ? "" : rs.getString("NAMA_BARGE").toUpperCase());
				// ika add 5/11/2020
				h.put("idBorangA", rs.getString("ID_BORANGA") == null ? "" : rs.getString("ID_BORANGA"));
				senaraiBarge.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatBarge(String idBarge) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatBarge = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_BARGE,ID_BORANGA, NAMA_BARGE, NO_PENDAFTARAN, MUATAN, NO_TEL, JENIS_BARGE"
					+ " FROM TBLPHPBARGE WHERE ID_BARGE = '" + idBarge + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql Barge : " + sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idBarge", rs.getString("ID_BARGE") == null ? "" : rs.getString("ID_BARGE"));
				h.put("idBorangA", rs.getString("ID_BORANGA") == null ? "" : rs.getString("ID_BORANGA"));
				h.put("namaDidaftarkan", rs.getString("NAMA_BARGE") == null ? "" : rs.getString("NAMA_BARGE"));
				h.put("noPendaftaran", rs.getString("NO_PENDAFTARAN") == null ? "" : rs.getString("NO_PENDAFTARAN"));
				h.put("kapasiti", rs.getString("MUATAN") == null ? "" : rs.getString("MUATAN"));

				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("jenis", rs.getString("JENIS_BARGE") == null ? "" : rs.getString("JENIS_BARGE"));

				beanMaklumatBarge.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// getMaklumatLaporan
	public static void getMaklumatLaporan(String id_laporanpasir) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			MaklumatLaporan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_LAPORANPASIR,A.BULAN_PENGAMBILAN,A.TAHUN_PENGAMBILAN,A.JUMLAH_KUANTITI, A.KONTRAKTOR, A.PEMBELI_PASIR,";
			sql += " A.JUMLAH_ROYALTI, A.ID_UNITISIPADU, A.TARIKH_PENGELUARAN, A.HARI_OPERASI, A.MASA_OPERASI, A.NAMA_KAPAL ";
			sql += " FROM TBLPHPLAPORANPASIR A ";
			sql += " WHERE A.ID_LAPORANPASIR = '" + id_laporanpasir + "' ";

			myLog.info("SQL MaklumatLaporan :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_laporanpasir",
						rs.getString("id_laporanpasir") == null ? "" : rs.getString("id_laporanpasir"));
				h.put("bulan_pengambilan",
						rs.getString("bulan_pengambilan") == null ? "" : rs.getString("bulan_pengambilan"));
				h.put("tahun_pengambilan",
						rs.getString("tahun_pengambilan") == null ? "" : rs.getString("tahun_pengambilan"));
				h.put("jumlah_kuantiti",
						rs.getString("jumlah_kuantiti") == null ? "" : rs.getString("jumlah_kuantiti"));

				h.put("kontraktor", rs.getString("KONTRAKTOR") == null ? "" : rs.getString("KONTRAKTOR"));
				h.put("pembeli", rs.getString("PEMBELI_PASIR") == null ? "" : rs.getString("PEMBELI_PASIR"));
				h.put("jumlah_royalti", rs.getString("jumlah_royalti") == null ? ""
						: Double.parseDouble(rs.getString("jumlah_royalti")));
				h.put("id_unitisipadu", rs.getString("id_unitisipadu") == null ? "" : rs.getString("id_unitisipadu"));
				// h.put("tarikh_pengeluaran", rs.getString("tarikh_pengeluaran") == null ? "" :
				// rs.getString("tarikh_pengeluaran"));
				h.put("tarikh_pengeluaran",
						rs.getString("tarikh_pengeluaran") == null ? "" : sdf.format(rs.getDate("tarikh_pengeluaran")));
				h.put("nama_kapal", rs.getString("nama_kapal") == null ? "" : rs.getString("nama_kapal"));
				h.put("hari_operasi", rs.getString("hari_operasi") == null ? "" : rs.getString("hari_operasi"));
				h.put("masa_operasi", rs.getString("masa_operasi") == null ? "" : rs.getString("masa_operasi"));
				MaklumatLaporan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getMaklumatLaporan

	// getListLaporan
	public Vector getListPasir(String id_laporanpasir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getListPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_BORANGK2K3,A.ID_LAPORANPASIR,A.TARIKH_HANTAR,A.NAMA_BARGE,A.LOKASI_DIBEKALKAN,A.AKUAN_KASTAM, A.HARI_HANTAR, A.JAM_HANTAR, A.MINIT_HANTAR,";
			sql += " A.KUANTITI,A.ANGGARAN_ROYALTI,A.NO_KASTAM ";
			sql += " FROM TBLPHPBORANGK2K3 A ";
			sql += " WHERE A.ID_LAPORANPASIR = '" + id_laporanpasir + "' ";

			myLog.info("SQL getListPasir :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_borangk2k3", rs.getString("ID_BORANGK2K3") == null ? "" : rs.getString("ID_BORANGK2K3"));
				h.put("id_laporanpasir",
						rs.getString("ID_LAPORANPASIR") == null ? "" : rs.getString("ID_LAPORANPASIR"));
				h.put("tarikh_hantar",
						rs.getString("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("nama_barge", rs.getString("NAMA_BARGE") == null ? "" : rs.getString("NAMA_BARGE"));
				h.put("lokasi_dibekalkan",
						rs.getString("LOKASI_DIBEKALKAN") == null ? "" : rs.getString("LOKASI_DIBEKALKAN"));
				h.put("akuan_kastam", rs.getString("AKUAN_KASTAM") == null ? "" : rs.getString("AKUAN_KASTAM"));
				h.put("kuantiti", rs.getString("KUANTITI") == null ? "" : rs.getString("KUANTITI"));
				h.put("anggaran_royalti", rs.getString("ANGGARAN_ROYALTI") == null ? ""
						: Double.parseDouble(rs.getString("ANGGARAN_ROYALTI")));
				h.put("no_kastam", rs.getString("NO_KASTAM") == null ? "" : rs.getString("NO_KASTAM"));

				h.put("lokasi_dibekalkan",
						rs.getString("LOKASI_DIBEKALKAN") == null ? "" : rs.getString("LOKASI_DIBEKALKAN"));
				h.put("akuan_kastam", rs.getString("AKUAN_KASTAM") == null ? "" : rs.getString("AKUAN_KASTAM"));
				getListPasir.addElement(h);
				bil++;
			}
			return getListPasir;
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getListPasir

	public String simpanLaporan(String usid, String id_jadualkedualesenAPB, String txtJumKuantiti, String txtJumRoyalti,
			String txtTarikhPengeluaran, String socBulan, String txtTahun) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";
		String output = "";
		Date now = new Date();
		String id_laporanpasir = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			long id_laporanpasirLong = DB.getNextID("TBLPHPLAPORANPASIR_SEQ");

			// TBLPHPLAPORANPASIR
			r = new SQLRenderer();
			r.add("id_laporanpasir", id_laporanpasirLong);
			id_laporanpasir = String.valueOf(id_laporanpasirLong);
			r.add("id_jadualkedualesenAPB", id_jadualkedualesenAPB);
			r.add("bulan_pengambilan", socBulan);
			r.add("tahun_pengambilan", txtTahun);
			r.add("jumlah_kuantiti", txtJumKuantiti);
			r.add("id_unitisipadu", 3); // 3 = METER PERSEGI
			r.add("jumlah_royalti", txtJumRoyalti);
			r.add("tarikh_pengeluaran", r.unquote("sysdate"));
			/* r.add("kontraktor", txtKontraktor); */
			/* r.add("pembeli_pasir", txtPembeli); */
			r.add("id_masuk", usid);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", usid);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("Tblphplaporanpasir");
			myLog.info("INSERT Tblphplaporanpasir ::" + sql);

			stmt.executeUpdate(sql);

			myLog.info("sql laporan b : " + sql);
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
		return id_laporanpasir;
	}

	// ADD 9/11/2020
	public String simpanLaporan(String usid, String id_jadualkedualesenAPB, String txtJumKuantiti, String txtJumRoyalti,
			String txtTarikhPengeluaran, String txtBulan, String txtTahun, String txtMasa, String txtHari,
			String txtKapal) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";
		String output = "";
		Date now = new Date();
		String id_laporanpasir = "";
		String tarikhOperasi = "to_date('" + txtTarikhPengeluaran + "','dd/MM/yyyy')";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			long id_laporanpasirLong = DB.getNextID("TBLPHPLAPORANPASIR_SEQ");

			// TBLPHPLAPORANPASIR
			r = new SQLRenderer();
			r.add("id_laporanpasir", id_laporanpasirLong);
			id_laporanpasir = String.valueOf(id_laporanpasirLong);
			r.add("id_jadualkedualesenAPB", id_jadualkedualesenAPB);
			r.add("bulan_pengambilan", txtBulan);
			r.add("tahun_pengambilan", txtTahun);
			r.add("jumlah_kuantiti", txtJumKuantiti);
			r.add("id_unitisipadu", 3); // 3 = METER PERSEGI
			r.add("jumlah_royalti", txtJumRoyalti);
			r.add("tarikh_pengeluaran", r.unquote(tarikhOperasi));
			r.add("masa_operasi", txtMasa);
			r.add("hari_operasi", txtHari);
			r.add("nama_kapal", txtKapal);
			/* r.add("kontraktor", txtKontraktor); */
			/* r.add("pembeli_pasir", txtPembeli); */
			r.add("id_masuk", usid);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", usid);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));

			sql = r.getSQLInsert("Tblphplaporanpasir");
			myLog.info("INSERT Tblphplaporanpasir ::" + sql);

			stmt.executeUpdate(sql);

			myLog.info("sql laporan b : " + sql);
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
		return id_laporanpasir;
	}
	// END

	// ADD 10/11/2020
	public void hapusLaporan(String idLaporanPasir, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPBARGE
			r.add("ID_LAPORANPASIR", idLaporanPasir);

			sql = r.getSQLDelete("TBLPHPLAPORANPASIR");
			myLog.info("HAPUS BY ID LAPORAN PASIR: " + sql);
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
	// END

	// getListLaporan
	public Vector getListLaporan(String id_jadualkedualesenAPB) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getListLaporan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_LAPORANPASIR,B.NAMA_BULAN,A.TAHUN_PENGAMBILAN,A.BULAN_PENGAMBILAN, A.ID_JADUALKEDUALESENAPB, ";
			sql += " A.JUMLAH_KUANTITI, A.JUMLAH_ROYALTI, A.TARIKH_PENGELUARAN ";
			sql += " FROM TBLPHPLAPORANPASIR A, TBLRUJBULAN B ";
			sql += " WHERE A.BULAN_PENGAMBILAN = B.ID_BULAN ";
			sql += " AND A.ID_JADUALKEDUALESENAPB = '" + id_jadualkedualesenAPB + "' ";
			sql += " ORDER BY A.TAHUN_PENGAMBILAN ASC, A.BULAN_PENGAMBILAN ASC ";

			myLog.info("SQL getListLaporan :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_jadualkedualesenAPB",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? "" : rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("id_laporanpasir",
						rs.getString("ID_LAPORANPASIR") == null ? "" : rs.getString("ID_LAPORANPASIR"));
				h.put("nama_bulan", rs.getString("NAMA_BULAN") == null ? "" : rs.getString("NAMA_BULAN"));
				h.put("tahun_pengambilan",
						rs.getString("TAHUN_PENGAMBILAN") == null ? "" : rs.getString("TAHUN_PENGAMBILAN"));
				h.put("bulan_pengambilan",
						rs.getString("BULAN_PENGAMBILAN") == null ? "" : rs.getString("BULAN_PENGAMBILAN"));
				h.put("jumlah_kuantiti",
						rs.getString("JUMLAH_KUANTITI") == null ? "" : rs.getString("JUMLAH_KUANTITI"));
				h.put("jumlah_royalti", rs.getString("JUMLAH_ROYALTI") == null ? "" : rs.getString("JUMLAH_ROYALTI"));
				h.put("tarikhPengeluaran",
						rs.getString("TARIKH_PENGELUARAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PENGELUARAN")));
				getListLaporan.addElement(h);
				bil++;
			}
			return getListLaporan;
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getListLaporan

	// setCarianLaporan
	public Vector setCarianLaporan(
//				String socBulan, String txtTahun,
			String id_jadualkedualesenAPB, String tarikhPengeluaran) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listCarianLaporan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
//				txtTahun = txtTahun.trim();

			sql = " SELECT A.ID_LAPORANPASIR,B.NAMA_BULAN,A.TAHUN_PENGAMBILAN, A.JUMLAH_KUANTITI, A.JUMLAH_ROYALTI, A.TARIKH_PENGELUARAN ";
			sql += " FROM TBLPHPLAPORANPASIR A, ";
			sql += " TBLRUJBULAN B ";
			sql += " WHERE A.BULAN_PENGAMBILAN = B.ID_BULAN ";
			sql += " AND A.ID_JADUALKEDUALESENAPB = '" + id_jadualkedualesenAPB + "' ";

//				// BULAN
//				if (socBulan != null) {
//					if (!socBulan.trim().equals("") && !socBulan.trim().equals("0")) {
//						sql = sql + " AND A.BULAN_PENGAMBILAN = '" + socBulan
//								+ "'  ";
//					}
//				}
//
//				// TAHUN
//				if (txtTahun != null) {
//					if (!txtTahun.trim().equals("")) {
//						sql = sql + " AND UPPER(A.TAHUN_PENGAMBILAN) LIKE '%"
//								+ txtTahun + "%'";
//					}
//				}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			// TARIKH PENGELUARAN
			if (tarikhPengeluaran != null) {
				if (!tarikhPengeluaran.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_PENGELUARAN, 'dd-MON-YY') ='"
							+ sdf1.format(sdf.parse(tarikhPengeluaran)).toUpperCase() + "'";
				}
			}

			// SORTING
			sql += " ORDER BY A.TARIKH_PENGELUARAN ASC ";
//						+ "A.BULAN_PENGAMBILAN ASC ";

			myLog.info("SQL CARIAN LAPORAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_laporanpasir",
						rs.getString("id_laporanpasir") == null ? "-" : rs.getString("id_laporanpasir"));
//					h.put("nama_bulan", rs.getString("nama_bulan") == null ? "-"
//							: rs.getString("nama_bulan"));
//					h.put("tahun_pengambilan",
//							rs.getString("tahun_pengambilan") == null ? "-" : rs
//									.getString("tahun_pengambilan"));
				h.put("jumlah_kuantiti",
						rs.getString("JUMLAH_KUANTITI") == null ? "" : rs.getString("JUMLAH_KUANTITI"));
				h.put("jumlah_royalti", rs.getString("JUMLAH_ROYALTI") == null ? "" : rs.getString("JUMLAH_ROYALTI"));
				h.put("tarikhPengeluaran",
						rs.getString("TARIKH_PENGELUARAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PENGELUARAN")));
				listCarianLaporan.addElement(h);
				bil++;
			}
			return listCarianLaporan;
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE setCarianLaporan

	// setCarianFail
	public Vector setCarianFail(String txtNamaPelesen, String txtNoLesen, String idJadualKeduaLesen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listCarian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			txtNamaPelesen = txtNamaPelesen.trim();
			txtNoLesen = txtNoLesen.trim();

			sql = " SELECT A.ID_JADUALKEDUALESENAPB,B.NAMA,A.NO_SIRI_LESEN ";
			sql += " FROM TBLPHPJADUALKEDUALESENAPB A, ";
			sql += " TBLPHPPEMEGANG B ";
			sql += " WHERE A.ID_JADUALKEDUALESENAPB = B.ID_JADUALKEDUALESENAPB ";
			sql += " AND A.ID_JADUALKEDUALESENAPB = '" + idJadualKeduaLesen + "' ";

			// NAMA PELESEN
			if (txtNamaPelesen != null) {
				if (!txtNamaPelesen.trim().equals("")) {
					sql = sql + " AND UPPER(B.NAMA) LIKE '%" + txtNamaPelesen.toUpperCase() + "%'";
				}
			}

			// NO LESEN
			if (txtNoLesen != null) {
				if (!txtNoLesen.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_SIRI_LESEN) LIKE '%" + txtNoLesen.toUpperCase() + "%'";
				}
			}

			// SORTING
			sql += " ORDER BY A.TARIKH_KEMASKINI DESC ";

			myLog.info("SQL CARIAN SENARAI :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_jadualkedualesenAPB",
						rs.getString("id_jadualkedualesenAPB") == null ? "-" : rs.getString("id_jadualkedualesenAPB"));
				h.put("nama", rs.getString("nama") == null ? "-" : rs.getString("nama"));
				if (rs.getString("no_siri_lesen") == null) {
					h.put("no_siri_lesen", "No Lesen Tiada");
				} else {
					h.put("no_siri_lesen", rs.getString("no_siri_lesen"));
				}
				listCarian.addElement(h);
				bil++;
			}
			return listCarian;
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE setCarianFail

	// getJumlahKiraan ADD 14102020
	public static void getJumlahKiraan(String id_laporanpasir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			MaklumatKiraan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT SUM(B.KUANTITI) AS KUANTITI,SUM(B.ANGGARAN_ROYALTI) AS ANGGARAN_ROYALTI ";
			sql += " FROM TBLPHPLAPORANPASIR A,TBLPHPBORANGK2K3 B ";
			sql += " WHERE A.ID_LAPORANPASIR = B.ID_LAPORANPASIR ";
			sql += " AND A.ID_LAPORANPASIR = '" + id_laporanpasir + "' ";

			myLog.info("SQL MaklumatKiraan :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("jumKuantiti", rs.getString("KUANTITI") == null ? "0" : rs.getString("KUANTITI"));
				h.put("jumAnggaran_royalti",
						rs.getString("ANGGARAN_ROYALTI") == null ? "0" : rs.getString("ANGGARAN_ROYALTI"));
				MaklumatKiraan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getJumlahKiraan

	public static Vector getJumlahKiraan() {
		return MaklumatKiraan;
	}

	// EDIT LAPORAN PASIR
	public void simpanEditLaporan(String userId, String id_laporanpasir, String txtJumKuantiti, String txtJumRoyalti,
			String socBulan, String txtTahun, String txtKontraktor, String txtPembeli) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_laporanpasir", id_laporanpasir);
			r.add("bulan_pengambilan", socBulan);
			r.add("tahun_pengambilan", txtTahun);
			r.add("jumlah_kuantiti", txtJumKuantiti);
			r.add("jumlah_royalti", txtJumRoyalti);
			r.add("kontraktor", txtKontraktor);
			r.add("pembeli_pasir", txtPembeli);
			r.add("id_kemaskini", userId);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			// r.add("tarikhPengeluaran", r.unquote("sysdate"));
			sql = r.getSQLUpdate("Tblphplaporanpasir");
			myLog.info("SQL UPDATE LAPORAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	// ADD 9/11/2020
	public void simpanEditLaporan(String userId, String id_laporanpasir, String txtJumKuantiti, String txtJumRoyalti,
			String txtTarikhPengeluaran, String txtBulan, String txtTahun, String txtMasa, String txtHari,
			String txtKapal) throws Exception {

		Db db = null;
		String sql = "";

		String tarikhOperasi = "to_date('" + txtTarikhPengeluaran + "','dd/MM/yyyy')";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_laporanpasir", id_laporanpasir);
			r.add("bulan_pengambilan", txtBulan);
			r.add("tahun_pengambilan", txtTahun);
			r.add("jumlah_kuantiti", txtJumKuantiti);
			r.add("jumlah_royalti", txtJumRoyalti);
			r.add("tarikh_pengeluaran", r.unquote(tarikhOperasi));
			r.add("masa_operasi", txtMasa);
			r.add("hari_operasi", txtHari);
			r.add("nama_kapal", txtKapal);
//				r.add("kontraktor", txtKontraktor);
//				r.add("pembeli_pasir", txtPembeli);
			r.add("id_kemaskini", userId);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			// r.add("tarikhPengeluaran", r.unquote("sysdate"));
			sql = r.getSQLUpdate("Tblphplaporanpasir");
			myLog.info("SQL UPDATE LAPORAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}
	// END

	// CLOSE EDIT LAPORAN PASIR

	// SIMPAN PASIR
	public static String simpanPasir(String userId, String id_laporanpasir, String txdTarikhHantar, String txtNamaBarge,
			String txtLokasi, String txtKuantiti, String txtAnggaranRoyalti, String txtAkuanKastam, String txtNoKastam,
			String txdHariHantar, String idJamDari, String idMinitDari) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";
		String output = "";
		Date now = new Date();

		try {
			long id_borangk2k3 = DB.getNextID("TBLPHPBORANGK2K3_SEQ");

			String TH = "to_date('" + txdTarikhHantar + "','dd/MM/yyyy')";

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPBORANGK2K3
			r = new SQLRenderer();
			r.add("id_borangk2k3", id_borangk2k3);
			r.add("id_laporanpasir", id_laporanpasir);
			r.add("tarikh_hantar", r.unquote(TH));
			r.add("lokasi_dibekalkan", txtLokasi);
			r.add("akuan_kastam", txtAkuanKastam);
			r.add("kuantiti", txtKuantiti);
			r.add("anggaran_royalti", txtAnggaranRoyalti);
			r.add("nama_barge", txtNamaBarge);
			r.add("no_kastam", txtNoKastam);
			r.add("hari_hantar", txdHariHantar);
			r.add("jam_hantar", idJamDari);
			r.add("minit_hantar", idMinitDari);

			r.add("id_masuk", userId);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", userId);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("Tblphpborangk2k3");
			myLog.info("INSERT TBLPHPBORANGK2K3 ::" + sql);
			stmt.executeUpdate(sql);

			output = "" + id_borangk2k3;

			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat Pendaftaran Pengeluaran Pasir Laut:" + se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return output;
	} // CLOSE SIMPAN PASIR

	// setMaklumatImej ADD 14102020
	public void setMaklumatLaporanPasir(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatDokumen = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			myLog.info("DATA BY ID DOKUMEN: " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaFail", rs.getString("NAMA_FAIL") == null ? "" : rs.getString("NAMA_FAIL"));
				h.put("namaLampiran",
						rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanLampiran", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				beanMaklumatDokumen.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setbeanMaklumatDokumen(Vector beanMaklumatDokumen) {
		this.beanMaklumatDokumen = beanMaklumatDokumen;
	}

	public Vector getbeanMaklumatDokumen() {
		return beanMaklumatDokumen;
	}

	public void setSenaraiDokumen(String idLaporanPasir) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listDokumen = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN"
					+ " WHERE ID_LAPORANPASIR = '" + idLaporanPasir + "'";

			myLog.info("get senarai dokumen : " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("namaFail", rs.getString("NAMA_FAIL") == null ? "" : rs.getString("NAMA_FAIL"));
				listDokumen.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListDokumen() {
		return listDokumen;
	}

	public void setListDokumen(Vector listDokumen) {
		this.listDokumen = listDokumen;
	}

	// HAPUS DOKUMEN
	public void deleteDokumen(String id_dokumen) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '" + id_dokumen + "'";
			myLog.info("DELETE TBLPHPDOKUMEN :: " + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE HAPUS DOKUMEN
}
