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

public class FrmAPBTamatTempohLesenData {

	private Vector beanMaklumatPendaftaranSambungan = null;
	private Vector listSambungan = null;
	private Vector beanMaklumatSambungan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void simpanSambungan(String idFail, String idPemohon,
			String idPermohonan, String tarikhTerima, String tarikhSurat,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();

		Hashtable h;
		listSambungan = new Vector();

		String sql = "";
		String noFail = "";
		String perkara = "";
		String noFailRoot = "";
		String noFailSambungan = "";
		String idSuburusan = "";
		String idFailSambunganString = "";
		String idPermohonanBaruString = "";
		String noSubJaket = "";
		String idTempoh = "";
		String idKaitanTujuan = "";
		String tujuanPengambilan = "";
		String tempoh = "";
		String pengalaman = "";
		String idFlagLuar = "";
		String luas = "";
		String idLuas = "";
		String lokasi = "";
		String idNegeri = "";
		String nama = "";
		String idUrusan = "";
		String labelTitik = "";
		String darjahU = "";
		String minitU = "";
		String saatU = "";
		String darjahT = "";
		String minitT = "";
		String saatT = "";
		String namaPakar = "";
		String kelayakan = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

			// UPDATE PERMOHONAN LAMA DI TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_AKTIF", "0");
			r.add("TARIKH_SURAT_SAMBUNG", r.unquote(TS));
			r.add("TARIKH_MULA_SAMBUNG", r.unquote(TT));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// GET DATA LAMA UNTUK CREATE REKOD BARU

			// TBLPFDFAIL
			sql = "SELECT NO_FAIL_ROOT,ID_SUBURUSAN,ID_URUSAN FROM TBLPFDFAIL WHERE"
					+ " ID_FAIL = '" + idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				noFailRoot = rs.getString("NO_FAIL_ROOT") == null ? ""
						: (String) rs.getString("NO_FAIL_ROOT");
				idSuburusan = rs.getString("ID_SUBURUSAN") == null ? ""
						: (String) rs.getString("ID_SUBURUSAN");
				idUrusan = rs.getString("ID_URUSAN") == null ? "" : (String) rs
						.getString("ID_URUSAN");
			}

			// TBLPHPPMOHONNJDUALPERTAMA
			sql = "SELECT ID_KAITANTUJUAN,TUJUAN_PENGAMBILAN,TEMPOH_DIPOHON,ID_TEMPOH,TEMPOH_DIPOHON,"
					+ "PENGALAMAN,FLAG_LUAR_PERAIRANNEGERI,LUAS_DIPOHON,ID_UNITLUAS,LOKASI_PERMOHONAN,ID_NEGERI_PERAIRAN FROM TBLPHPPMOHONNJDUALPERTAMA WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs1 = stmt.executeQuery(sql);
			if (rs1.next()) {
				idKaitanTujuan = rs1.getString("ID_KAITANTUJUAN") == null ? ""
						: (String) rs1.getString("ID_KAITANTUJUAN");
				tujuanPengambilan = rs1.getString("TUJUAN_PENGAMBILAN") == null ? ""
						: (String) rs1.getString("TUJUAN_PENGAMBILAN");
				tempoh = rs1.getString("TEMPOH_DIPOHON") == null ? ""
						: (String) rs1.getString("TEMPOH_DIPOHON");
				idTempoh = rs1.getString("ID_TEMPOH") == null ? ""
						: (String) rs1.getString("ID_TEMPOH");
				tempoh = rs1.getString("TEMPOH_DIPOHON") == null ? ""
						: (String) rs1.getString("TEMPOH_DIPOHON");
				pengalaman = rs1.getString("PENGALAMAN") == null ? ""
						: (String) rs1.getString("PENGALAMAN");
				idFlagLuar = rs1.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? ""
						: (String) rs1.getString("FLAG_LUAR_PERAIRANNEGERI");
				luas = rs1.getString("LUAS_DIPOHON") == null ? ""
						: (String) rs1.getString("LUAS_DIPOHON");
				idLuas = rs1.getString("ID_UNITLUAS") == null ? ""
						: (String) rs1.getString("ID_UNITLUAS");
				lokasi = rs1.getString("LOKASI_PERMOHONAN") == null ? ""
						: (String) rs1.getString("LOKASI_PERMOHONAN");
				idNegeri = rs1.getString("ID_NEGERI_PERAIRAN") == null ? ""
						: (String) rs1.getString("ID_NEGERI_PERAIRAN");
			}

			// TBLPERMOHONAN
			sql = "SELECT NO_SUBJAKET,TUJUAN FROM TBLPERMOHONAN WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs2 = stmt.executeQuery(sql);
			if (rs2.next()) {
				noSubJaket = rs2.getString("NO_SUBJAKET") == null ? "0"
						: (String) rs2.getString("NO_SUBJAKET");
				perkara = rs2.getString("TUJUAN") == null ? "0" : (String) rs2
						.getString("TUJUAN");
			}

			int subJaket = Integer.parseInt(noSubJaket);
			subJaket++;
			noFailSambungan = noFailRoot + "-SJ(" + subJaket + ")";

			// CREATE REKOD BARU UNTUK SAMBUNGAN

			// TBLPFDFAIL
			r = new SQLRenderer();
			long idFailBaru = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailSambunganString = String.valueOf(idFailBaru);
			r.add("ID_FAIL", idFailBaru);
			r.add("ID_URUSAN", idUrusan);
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", perkara);
			r.add("NO_FAIL", noFailSambungan);
			r.add("NO_FAIL_ROOT", noFailRoot);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonanBaru = DB.getNextID("TBLPERMOHONAN_SEQ");
			idPermohonanBaruString = String.valueOf(idPermohonanBaru);
			r.add("ID_PERMOHONAN", idPermohonanBaru);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFailBaru);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("TUJUAN", perkara);
			r.add("NO_SUBJAKET", subJaket);
			r.add("NO_RAYUAN", 0);
			r.add("FLAG_AKTIF", "1");
			r.add("ID_STATUS", "1610199"); // JABATAN TEKNIKAL
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPMOHONNJDUALPERTAMA
			r = new SQLRenderer();
			long id = DB.getNextID("TBLPHPPMOHONNJDUALPERTAMA_SEQ");
			r.add("ID_PHPPMOHONNJDUALPERTAMA", id);
			r.add("FLAG_SAMBUNGAN", "0");
			r.add("ID_PERMOHONAN", idPermohonanBaru);
			r.add("ID_JENISTUJUAN", 3);
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
			r.add("TUJUAN_PENGAMBILAN", tujuanPengambilan);
			r.add("TEMPOH_DIPOHON", tempoh);
			r.add("ID_TEMPOH", idTempoh);
			r.add("PENGALAMAN", pengalaman);
			r.add("FLAG_LUAR_PERAIRANNEGERI", idFlagLuar);
			r.add("LUAS_DIPOHON", luas);
			r.add("ID_UNITLUAS", idLuas);
			r.add("LOKASI_PERMOHONAN", lokasi);
			r.add("ID_NEGERI_PERAIRAN", idNegeri);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPMOHONNJDUALPERTAMA");
			System.out.println(sql);
			stmt.executeUpdate(sql);
			;

			// -----------COPY
			// LISTING--------------------------------------------------------------------------------------

			// TBLPHPPROJEKLESENAPB
			sql = "SELECT ID_PROJEKLESENAPB FROM TBLPHPPROJEKLESENAPB WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs3 = stmt.executeQuery(sql);
			while (rs3.next()) {
				insertIntoTBLPHPPROJEKLESENAPB(
						rs3.getString("ID_PROJEKLESENAPB").toString(),
						idPermohonanBaruString, userId);
			}

			// TBLPHPKOORDINATPERMOHONAN
			sql = "SELECT ID_KOORDINATPERMOHONAN FROM TBLPHPKOORDINATPERMOHONAN WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs4 = stmt.executeQuery(sql);
			while (rs4.next()) {
				insertIntoTBLPHPKOORDINATPERMOHONAN(
						rs4.getString("ID_KOORDINATPERMOHONAN").toString(),
						idPermohonanBaruString, userId);

			}

			// TBLPHPMAKLUMATPAKAR
			sql = "SELECT ID_MAKLUMATPAKAR FROM TBLPHPMAKLUMATPAKAR WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs5 = stmt.executeQuery(sql);
			while (rs5.next()) {
				insertIntoTBLPHPMAKLUMATPAKAR(rs5.getString("ID_MAKLUMATPAKAR")
						.toString(), idPermohonanBaruString, userId);
			}

			// -----------END OF COPY
			// LISTING------------------------------------------------------------------------------------

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");

			r.add("AKTIF", "0");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonanBaru);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610199")); // JABATAN
																				// TEKNIKAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

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

	public void setMaklumatSambungan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatSambungan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERMOHONAN, TARIKH_MULA_SAMBUNG, TARIKH_SURAT_SAMBUNG "
					+ " FROM TBLPERMOHONAN "
					+ " WHERE ID_FAIL = '"
					+ idFail
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("tarikhMula",
						rs.getDate("TARIKH_MULA_SAMBUNG") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MULA_SAMBUNG")));
				h.put("tarikhSurat",
						rs.getDate("TARIKH_SURAT_SAMBUNG") == null ? "" : sdf
								.format(rs.getDate("TARIKH_SURAT_SAMBUNG")));
				beanMaklumatSambungan.addElement(h);
				bil++;
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

	public Vector getBeanMaklumatSambunganPendaftaran() {
		return beanMaklumatPendaftaranSambungan;
	}

	public Vector getBeanMaklumatSambungan() {
		return beanMaklumatSambungan;
	}

}
