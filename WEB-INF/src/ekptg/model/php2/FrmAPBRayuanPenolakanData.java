package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmAPBRayuanPenolakanData {

	private Vector beanMaklumatRayuan = null;
	private Vector listRayuan = null;
	private Vector beanMaklumatRayuanLama = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");

	public Vector simpanRayuan(String idFail, String idPemohon,
			String idPermohonan, String tarikhTerima, String tarikhSurat,
			String perkara, String socTukarKoordinat, String noRayuan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String noFailRoot = "";
		String idFailRayuanString = "";
		String idPermohonanRayuanString = "";
		Hashtable h;
		listRayuan = new Vector();
		String noSubJaket = "";
		String idJdualPertamaString = "";
		String noFail = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

			// ---------------- update fail lama ------------
			// TBLPFDFAIL
			sql = "SELECT NO_FAIL,NO_FAIL_ROOT FROM TBLPFDFAIL WHERE"
					+ " ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				noFail = rs.getString("NO_FAIL").toString();
				noFailRoot = rs.getString("NO_FAIL_ROOT").toString();
			} else {
				noFail = "";
				noFailRoot = "";
			}

			// TBLPERMOHONAN
			sql = "SELECT NO_SUBJAKET FROM TBLPERMOHONAN WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs1 = stmt.executeQuery(sql);
			if (rs1.next()) {
				noSubJaket = rs1.getString("NO_SUBJAKET") == null ? "0" : rs1
						.getString("NO_SUBJAKET").toString();

			}

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_AKTIF", 0);
			r.add("TARIKH_SURAT_RAYUAN", r.unquote(TS));
			r.add("TARIKH_TERIMA_RAYUAN", r.unquote(TT));
			r.add("TUJUAN_RAYUAN", perkara);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPMOHONNJDUALPERTAMA
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_KOORDINAT", socTukarKoordinat);
			r.add("FLAG_RAYUAN", "1");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPPMOHONNJDUALPERTAMA");
			stmt.executeUpdate(sql);

			int noRayuanBaru = Integer.parseInt(noRayuan);
			noRayuanBaru++;
			int subJaket = Integer.parseInt(noSubJaket);

			// -----------create new fail ---------------
			// TBLPFDFAIL
			r = new SQLRenderer();
			long idFailBaru = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailRayuanString = String.valueOf(idFailBaru);
			r.add("ID_FAIL", idFailBaru);
			r.add("ID_URUSAN", "9");
			r.add("ID_SUBURUSAN", "57");
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", perkara);
			r.add("NO_FAIL", noFail);
			r.add("NO_FAIL_ROOT", noFailRoot);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonanBaru = DB.getNextID("TBLPERMOHONAN_SEQ");
			idPermohonanRayuanString = String.valueOf(idPermohonanBaru);
			r.add("ID_PERMOHONAN", idPermohonanBaru);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFailBaru);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("TUJUAN", perkara);
			r.add("NO_SUBJAKET", subJaket);
			r.add("NO_RAYUAN", noRayuanBaru);
			r.add("FLAG_AKTIF", 1);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPMOHONNJDUALPERTAMA
			r = new SQLRenderer();
			long id = DB.getNextID("TBLPHPPMOHONNJDUALPERTAMA_SEQ");
			idJdualPertamaString = String.valueOf(id);
			r.add("ID_PHPPMOHONNJDUALPERTAMA", id);
			r.add("ID_PERMOHONAN", idPermohonanBaru);
			r.add("FLAG_RAYUAN", "0");
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPMOHONNJDUALPERTAMA");
			stmt.executeUpdate(sql);

			if ("2".equals(socTukarKoordinat)) {
				copyMaklumatPermohonan(idPermohonan, idJdualPertamaString,
						idPermohonanRayuanString, userId, db);
				copyPembeliPasir(idPermohonan, idPermohonanRayuanString, userId);
				copyProjek(idPermohonan, idPermohonanRayuanString, userId);
				copyPakar(idPermohonan, idPermohonanRayuanString, userId);
				copyKoordinat(idPermohonan, idPermohonanRayuanString, userId);
				copyMaklumatKJPKJT(idPermohonan, idPermohonanRayuanString,
						userId);
				copyMaklumatPertindihan(idPermohonan, idPermohonanRayuanString,
						userId);
				copyMaklumatKertasKerjaJwtnKuasa(idPermohonan,
						idPermohonanRayuanString, userId, db);
			}

			conn.commit();

			h = new Hashtable();
			h.put("idFailRayuan", idFailRayuanString);
			h.put("idPermohonanRayuan", idPermohonanRayuanString);

			listRayuan.addElement(h);

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
		return listRayuan;
	}

	private static void copyMaklumatPermohonan(String idPermohonanLama,
			String idJdualPertama, String idPermohonanBaru, String userId, Db db)
			throws Exception {
		String sql = "";
		String sqlInsert = "";

		try {

			Statement stmt = db.getStatement();

			sql = "SELECT ID_JENISTUJUAN,ID_KAITANTUJUAN,TUJUAN_PENGAMBILAN,TEMPOH_DIPOHON,ID_TEMPOH,PENGALAMAN,"
					+ "FLAG_LUAR_PERAIRANNEGERI,LUAS_DIPOHON,ID_UNITLUAS,LOKASI_PERMOHONAN,MODAL_SEMASA,MODAL_SEDIA,ID_NEGERI_PERAIRAN"
					+ " FROM TBLPHPPMOHONNJDUALPERTAMA WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPPMOHONNJDUALPERTAMA
				r.update("ID_PHPPMOHONNJDUALPERTAMA", idJdualPertama);
				r.add("ID_JENISTUJUAN",
						rs.getString("ID_JENISTUJUAN") == null ? "" : rs
								.getString("ID_JENISTUJUAN"));
				r.add("ID_KAITANTUJUAN",
						rs.getString("ID_KAITANTUJUAN") == null ? "" : rs
								.getString("ID_KAITANTUJUAN"));
				r.add("TUJUAN_PENGAMBILAN",
						rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs
								.getString("TUJUAN_PENGAMBILAN"));
				r.add("TEMPOH_DIPOHON",
						rs.getString("TEMPOH_DIPOHON") == null ? "" : rs
								.getString("TEMPOH_DIPOHON"));
				r.add("ID_TEMPOH",
						rs.getString("ID_TEMPOH") == null ? "" : rs
								.getString("ID_TEMPOH"));
				r.add("PENGALAMAN", rs.getString("PENGALAMAN") == null ? ""
						: rs.getString("PENGALAMAN"));
				r.add("FLAG_LUAR_PERAIRANNEGERI", rs
						.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? ""
						: rs.getString("FLAG_LUAR_PERAIRANNEGERI"));
				r.add("LUAS_DIPOHON", rs.getString("LUAS_DIPOHON") == null ? ""
						: rs.getString("LUAS_DIPOHON"));
				r.add("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
						: rs.getString("ID_UNITLUAS"));
				r.add("LOKASI_PERMOHONAN",
						rs.getString("LOKASI_PERMOHONAN") == null ? "" : rs
								.getString("LOKASI_PERMOHONAN"));
				r.add("MODAL_SEMASA", rs.getString("MODAL_SEMASA") == null ? ""
						: rs.getString("MODAL_SEMASA"));
				r.add("MODAL_SEDIA", rs.getString("MODAL_SEDIA") == null ? ""
						: rs.getString("MODAL_SEDIA"));
				r.add("ID_NEGERI_PERAIRAN",
						rs.getString("ID_NEGERI_PERAIRAN") == null ? "" : rs
								.getString("ID_NEGERI_PERAIRAN"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPHPPMOHONNJDUALPERTAMA");

				stmt.executeUpdate(sql);
			}

		} finally {
		}
	}

	private static void copyPembeliPasir(String idPermohonanLama,
			String idPermohonanBaru, String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEMBELIPASIR FROM TBLPHPPEMBELIPASIR WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				insertIntoTBLPHPPEMBELIPASIR(rs.getString("ID_PEMBELIPASIR")
						.toString(), idPermohonanBaru, userId);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void insertIntoTBLPHPPEMBELIPASIR(String idPembeliPasirOld,
			String idPermohonanBaru, String userId) throws Exception {
		Db db = null;
		String sql = "";
		String sqlInsert = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPPEMBELIPASIR WHERE ID_PEMBELIPASIR = '"
					+ idPembeliPasirOld + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPPEMBELIPASIR
				long idPembeliPasir = DB.getNextID("TBLPHPPEMBELIPASIR_SEQ");
				r.add("ID_PEMBELIPASIR", idPembeliPasir);
				r.add("ID_PERMOHONAN", idPermohonanBaru);
				r.add("NAMA",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				r.add("ALAMAT1",
						rs.getString("ALAMAT1") == null ? "" : rs
								.getString("ALAMAT1"));
				r.add("ALAMAT2",
						rs.getString("ALAMAT2") == null ? "" : rs
								.getString("ALAMAT2"));
				r.add("ALAMAT3",
						rs.getString("ALAMAT3") == null ? "" : rs
								.getString("ALAMAT3"));
				r.add("POSKOD",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				r.add("ID_NEGERI",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				r.add("ID_BANDAR",
						rs.getString("ID_BANDAR") == null ? "" : rs
								.getString("ID_BANDAR"));
				r.add("NO_TEL_PEJABAT",
						rs.getString("NO_TEL_PEJABAT") == null ? "" : rs
								.getString("NO_TEL_PEJABAT"));
				r.add("NO_FAX",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				r.add("FLAG_JENIS_PERJANJIAN",
						rs.getString("FLAG_JENIS_PERJANJIAN") == null ? "" : rs
								.getString("FLAG_JENIS_PERJANJIAN"));

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPHPPEMBELIPASIR");
				stmt.executeUpdate(sqlInsert);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void copyPakar(String idPermohonanLama,
			String idPermohonanBaru, String userId) throws Exception {
		String sql = "";
		Db db = null;

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_MAKLUMATPAKAR FROM TBLPHPMAKLUMATPAKAR WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				insertIntoTBLPHPMAKLUMATPAKAR(rs.getString("ID_MAKLUMATPAKAR")
						.toString(), idPermohonanBaru, userId);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void insertIntoTBLPHPMAKLUMATPAKAR(String idPakarOld,
			String idPermohonanBaru, String userId) throws Exception {
		String sql = "";
		String sqlInsert = "";
		Db db = null;

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPMAKLUMATPAKAR WHERE ID_MAKLUMATPAKAR = '"
					+ idPakarOld + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPMAKLUMATPAKAR
				long idPakar = DB.getNextID("TBLPHPMAKLUMATPAKAR_SEQ");
				r.add("ID_MAKLUMATPAKAR", idPakar);
				r.add("ID_PERMOHONAN", idPermohonanBaru);
				r.add("NAMA",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				r.add("KELAYAKAN",
						rs.getString("KELAYAKAN") == null ? "" : rs
								.getString("KELAYAKAN"));

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPHPMAKLUMATPAKAR");
				stmt.executeUpdate(sqlInsert);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void copyProjek(String idPermohonanLama,
			String idPermohonanBaru, String userId) throws Exception {
		String sql = "";
		Db db = null;

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PROJEKLESENAPB FROM TBLPHPPROJEKLESENAPB WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				insertIntoTBLPHPPROJEKLESENAPB(rs
						.getString("ID_PROJEKLESENAPB").toString(),
						idPermohonanBaru, userId);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void insertIntoTBLPHPPROJEKLESENAPB(
			String idProjekLesenAPBOld, String idPermohonanBaru, String userId)
			throws Exception {
		String sql = "";
		String sqlInsert = "";
		Db db = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPPROJEKLESENAPB WHERE ID_PROJEKLESENAPB = '"
					+ idProjekLesenAPBOld + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPPROJEKLESENAPB
				long idProjek = DB.getNextID("TBLPHPPROJEKLESENAPB_SEQ");
				r.add("ID_PROJEKLESENAPB", idProjek);
				r.add("ID_PERMOHONAN", idPermohonanBaru);
				r.add("NAMA_PROJEK", rs.getString("NAMA_PROJEK") == null ? ""
						: rs.getString("NAMA_PROJEK"));
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPHPPROJEKLESENAPB");
				stmt.executeUpdate(sqlInsert);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void copyKoordinat(String idPermohonanLama,
			String idPermohonanBaru, String userId) throws Exception {
		String sql = "";
		Db db = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KOORDINATPERMOHONAN FROM TBLPHPKOORDINATPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				insertIntoTBLPHPKOORDINATPERMOHONAN(
						rs.getString("ID_KOORDINATPERMOHONAN").toString(),
						idPermohonanBaru, userId);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void insertIntoTBLPHPKOORDINATPERMOHONAN(
			String idKoordinatOld, String idPermohonanBaru, String userId)
			throws Exception {
		Db db = null;
		String sql = "";
		String sqlInsert = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPKOORDINATPERMOHONAN WHERE ID_KOORDINATPERMOHONAN = '"
					+ idKoordinatOld + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPKOORDINATPERMOHONAN
				long idKoordinat = DB
						.getNextID("TBLPHPKOORDINATPERMOHONAN_SEQ");
				r.add("ID_KOORDINATPERMOHONAN", idKoordinat);
				r.add("ID_PERMOHONAN", idPermohonanBaru);
				r.add("LABEL_TITIK", rs.getString("LABEL_TITIK") == null ? ""
						: rs.getString("LABEL_TITIK"));
				r.add("DARJAH_U",
						rs.getString("DARJAH_U") == null ? "" : rs
								.getString("DARJAH_U"));
				r.add("MINIT_U",
						rs.getString("MINIT_U") == null ? "" : rs
								.getString("MINIT_U"));
				r.add("SAAT_U",
						rs.getString("SAAT_U") == null ? "" : rs
								.getString("SAAT_U"));
				r.add("DARJAH_T",
						rs.getString("DARJAH_T") == null ? "" : rs
								.getString("DARJAH_T"));
				r.add("MINIT_T",
						rs.getString("MINIT_T") == null ? "" : rs
								.getString("MINIT_T"));
				r.add("SAAT_T",
						rs.getString("SAAT_T") == null ? "" : rs
								.getString("SAAT_T"));

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPHPKOORDINATPERMOHONAN");
				stmt.executeUpdate(sqlInsert);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void copyMaklumatKJPKJT(String idPermohonanLama,
			String idPermohonanBaru, String userId) throws Exception {
		String sql = "";
		Db db = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL FROM TBLPHPULASANTEKNIKAL WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				insertIntoTBLPHPULASANTEKNIKAL(rs
						.getString("ID_ULASANTEKNIKAL").toString(),
						idPermohonanBaru, userId);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void insertIntoTBLPHPULASANTEKNIKAL(
			String idUlasanTeknikalOld, String idPermohonanBaru, String userId)
			throws Exception {
		String sql = "";
		String sqlInsert = "";
		Db db = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalOld + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPULASANTEKNIKAL
				long idUlasanTeknikal = DB
						.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
				r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
				r.add("ID_PERMOHONAN", idPermohonanBaru);

				r.add("ID_MENTERI", rs.getString("ID_MENTERI") == null ? ""
						: rs.getString("ID_MENTERI"));
				r.add("ID_AGENSI",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				r.add("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? ""
						: rs.getString("ID_DOKUMEN"));
				r.add("FLAG_RAYUSEWA",
						rs.getString("FLAG_RAYUSEWA") == null ? "" : rs
								.getString("FLAG_RAYUSEWA"));
				r.add("TARIKH_HANTAR", rs.getDate("TARIKH_HANTAR") == null ? ""
						: rs.getDate("TARIKH_HANTAR"));
				r.add("JANGKAMASA", rs.getString("JANGKAMASA") == null ? ""
						: rs.getString("JANGKAMASA"));
				r.add("TARIKH_JANGKA_TERIMA",
						rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : rs
								.getDate("TARIKH_JANGKA_TERIMA"));
				r.add("MAKLUMAT_TAMBAHAN",
						rs.getString("MAKLUMAT_TAMBAHAN") == null ? "" : rs
								.getString("MAKLUMAT_TAMBAHAN"));
				r.add("FLAG_STATUS", rs.getString("FLAG_STATUS") == null ? ""
						: rs.getString("FLAG_STATUS"));
				r.add("BIL_HANTAR", rs.getString("BIL_HANTAR") == null ? ""
						: rs.getString("BIL_HANTAR"));
				r.add("TARIKH_TERIMA", rs.getDate("TARIKH_TERIMA") == null ? ""
						: rs.getDate("TARIKH_TERIMA"));
				r.add("TARIKH_SURAT", rs.getDate("TARIKH_SURAT") == null ? ""
						: rs.getDate("TARIKH_SURAT"));
				r.add("NO_RUJUKAN", rs.getString("NO_RUJUKAN") == null ? ""
						: rs.getString("NO_RUJUKAN"));
				r.add("ULASAN",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				r.add("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				r.add("ID_JAWATAN", rs.getString("ID_JAWATAN") == null ? ""
						: rs.getString("ID_JAWATAN"));
				r.add("FLAG_BERTINDIH",
						rs.getString("FLAG_BERTINDIH") == null ? "" : rs
								.getString("FLAG_BERTINDIH"));

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
				stmt.executeUpdate(sqlInsert);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void copyMaklumatPertindihan(String idPermohonanLama,
			String idPermohonanBaru, String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_FAILAPBBERTINDIH FROM TBLPHPFAILAPBBERTINDIH WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				insertIntoTBLPHPFAILAPBBERTINDIH(
						rs.getString("ID_FAILAPBBERTINDIH").toString(),
						idPermohonanBaru, userId);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void insertIntoTBLPHPFAILAPBBERTINDIH(
			String idFailAPBBertindihOld, String idPermohonanBaru, String userId)
			throws Exception {
		String sql = "";
		String sqlInsert = "";
		Db db = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPFAILAPBBERTINDIH WHERE ID_FAILAPBBERTINDIH = '"
					+ idFailAPBBertindihOld + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPFAILAPBBERTINDIH
				long idFailTindih = DB.getNextID("TBLPHPFAILAPBBERTINDIH_SEQ");
				r.add("ID_FAILAPBBERTINDIH", idFailTindih);
				r.add("ID_PERMOHONAN", idPermohonanBaru);
				r.add("BERTINDIH_DENGAN_NOFAIL",
						rs.getString("BERTINDIH_DENGAN_NOFAIL") == null ? ""
								: rs.getString("BERTINDIH_DENGAN_NOFAIL"));
				r.add("NAMA_SYARIKAT_TINDIH",
						rs.getString("NAMA_SYARIKAT_TINDIH") == null ? "" : rs
								.getString("NAMA_SYARIKAT_TINDIH"));
				r.add("FLAG_JENIS_PERTINDIHAN",
						rs.getString("FLAG_JENIS_PERTINDIHAN") == null ? ""
								: rs.getString("FLAG_JENIS_PERTINDIHAN"));
				r.add("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				r.add("LAIN_LAIN",
						rs.getString("LAIN_LAIN") == null ? "" : rs
								.getString("LAIN_LAIN"));
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPHPFAILAPBBERTINDIH");
				stmt.executeUpdate(sqlInsert);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void copyMaklumatKertasKerjaJwtnKuasa(
			String idPermohonanLama, String idPermohonanBaru, String userId,
			Db db) throws Exception {
		String sql = "";
		String sqlInsert = "";

		try {

			Statement stmt = db.getStatement();

			sql = "SELECT A.NOTA, A.ULASAN_JAS, A.ULASAN_NAHRIM, A.ULASAN_JAB_LAUT,A.ULASAN_JPS,A.ULASAN_JAB_GEOSAINS, "
					+ " A.ULASAN_JAB_PERIKANAN, A.ULASAN_PUSAT_HIDROGRAFI, A.ULASAN_POLISMARIN, A.ULASAN_KEM_KEBUDAYAAN,B.LOKASI_PERMOHONAN "
					+ " FROM TBLPHPKERTASKERJAAPB A, TBLPHPPMOHONNJDUALPERTAMA B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPKERTASKERJAAPB
				long idKertasKerja = DB.getNextID("TBLPHPKERTASKERJAAPB_SEQ");
				r.add("ID_KERTASKERJAAPB", idKertasKerja);
				r.add("ID_PERMOHONAN", idPermohonanBaru);
				r.add("NOTA",
						rs.getString("NOTA") == null ? "" : rs
								.getString("NOTA"));
				r.add("ULASAN_JAS", rs.getString("ULASAN_JAS") == null ? ""
						: rs.getString("ULASAN_JAS"));
				r.add("ULASAN_NAHRIM",
						rs.getString("ULASAN_NAHRIM") == null ? "" : rs
								.getString("ULASAN_NAHRIM"));
				r.add("ULASAN_JAB_LAUT",
						rs.getString("ULASAN_JAB_LAUT") == null ? "" : rs
								.getString("ULASAN_JAB_LAUT"));
				r.add("ULASAN_JPS", rs.getString("ULASAN_JPS") == null ? ""
						: rs.getString("ULASAN_JPS"));
				r.add("ULASAN_JAB_GEOSAINS",
						rs.getString("ULASAN_JAB_GEOSAINS") == null ? "" : rs
								.getString("ULASAN_JAB_GEOSAINS"));
				r.add("ULASAN_JAB_PERIKANAN",
						rs.getString("ULASAN_JAB_PERIKANAN") == null ? "" : rs
								.getString("ULASAN_JAB_PERIKANAN"));
				r.add("ULASAN_PUSAT_HIDROGRAFI",
						rs.getString("ULASAN_PUSAT_HIDROGRAFI") == null ? ""
								: rs.getString("ULASAN_PUSAT_HIDROGRAFI"));
				r.add("ULASAN_POLISMARIN",
						rs.getString("ULASAN_POLISMARIN") == null ? "" : rs
								.getString("ULASAN_POLISMARIN"));
				r.add("ULASAN_KEM_KEBUDAYAAN",
						rs.getString("ULASAN_KEM_KEBUDAYAAN") == null ? "" : rs
								.getString("ULASAN_KEM_KEBUDAYAAN"));
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sqlInsert = r.getSQLInsert("TBLPHPKERTASKERJAAPB");

				stmt.executeUpdate(sqlInsert);

			}

		} finally {
		}
	}

	public void updateStatus(String idFail, String idPermohonan,
			String socTukarKoordinat, HttpSession session) throws Exception {
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

			// KOORDINAT YANG SAMA
			if (socTukarKoordinat.equals("2")) {
				// TBLPERMOHONAN
				r.update("ID_PERMOHONAN", idPermohonan);
				r.add("ID_STATUS", "1610201");
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPERMOHONAN");
				stmt.executeUpdate(sql);

				// TBLRUJSUBURUSANSTATUSFAIL
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN", idPermohonan);
				r.update("AKTIF", "1");

				r.add("AKTIF", "0");

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
				stmt.executeUpdate(sql);

				r = new SQLRenderer();
				long idSuburusanstatusfail = DB
						.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
				r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("57", "1610201")); // MESYUARAT
				r.add("AKTIF", "1");
				r.add("ID_FAIL", idFail);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
				stmt.executeUpdate(sql);
			}

			// TUKAR KOORDINAT
			else if (socTukarKoordinat.equals("1")) {
				// TBLPERMOHONAN
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN", idPermohonan);
				r.add("ID_STATUS", "1610198");
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPERMOHONAN");
				stmt.executeUpdate(sql);

				// TBLRUJSUBURUSANSTATUSFAIL
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN", idPermohonan);
				r.update("AKTIF", "1");

				r.add("AKTIF", "0");

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
				stmt.executeUpdate(sql);

				r = new SQLRenderer();
				long idSuburusanstatusfail = DB
						.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
				r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("57", "1610198")); // MAKLUMAT
																// PERMOHONAN
				r.add("AKTIF", "1");
				r.add("ID_FAIL", idFail);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
				stmt.executeUpdate(sql);
			}

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdSuburusanstatus(String idSuburusan, String idStatus)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '"
					+ idStatus + "' AND ID_SUBURUSAN = '" + idSuburusan + "'";

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

	public void setMaklumatRayuan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatRayuan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PERMOHONAN, A.TARIKH_TERIMA, A.TARIKH_SURAT, A.TUJUAN, B.FLAG_KOORDINAT"
					+ " FROM TBLPERMOHONAN A, TBLPHPPMOHONNJDUALPERTAMA B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "
					+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("perkara", rs.getString("TUJUAN") == null ? "" : rs
						.getString("TUJUAN").toUpperCase());
				h.put("socTukarKoordinat",
						rs.getString("FLAG_KOORDINAT") == null ? "" : rs
								.getString("FLAG_KOORDINAT"));
				beanMaklumatRayuan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatRayuanLama(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatRayuanLama = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			// sql =
			// "SELECT TARIKH_TERIMA_RAYUAN, TARIKH_SURAT_RAYUAN, TUJUAN_RAYUAN"
			// + " FROM TBLPERMOHONAN "
			// + " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";

			sql = "SELECT A.TARIKH_TERIMA_RAYUAN, A.TARIKH_SURAT_RAYUAN, A.TUJUAN_RAYUAN ,B.FLAG_KOORDINAT"
					+ " FROM TBLPERMOHONAN A, TBLPHPPMOHONNJDUALPERTAMA B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "
					+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhTerimaRayuan",
						rs.getDate("TARIKH_TERIMA_RAYUAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_RAYUAN")));
				h.put("tarikhSuratRayuan",
						rs.getDate("TARIKH_SURAT_RAYUAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_SURAT_RAYUAN")));
				h.put("perkaraRayuan",
						rs.getString("TUJUAN_RAYUAN") == null ? "" : rs
								.getString("TUJUAN_RAYUAN").toUpperCase());
				h.put("socTukarKoordinat",
						rs.getString("FLAG_KOORDINAT") == null ? "" : rs
								.getString("FLAG_KOORDINAT"));
				beanMaklumatRayuanLama.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatRayuanLama() {
		return beanMaklumatRayuanLama;
	}

	public Vector getBeanMaklumatRayuan() {
		return beanMaklumatRayuan;
	}
}
