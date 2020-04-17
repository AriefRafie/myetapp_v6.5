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
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public class FrmPYWLawatanTapakData {

	private Vector beanMaklumatLaporanTanah = null;
	private Vector listKehadiran = null;
	private Vector beanMaklumatKehadiran = null;
	private Vector listImejan = null;
	private Vector beanMaklumatImejan = null;
	private Vector listPelan = null;
	private Vector beanMaklumatPelan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String getIdLaporanTanah(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_LAPORANTANAH FROM TBLPHPLAPORANTANAH WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_LAPORANTANAH");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikAgensiByPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIKAGENSI FROM TBLPHPHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatLaporanTanah(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatLaporanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_LAPORANTANAH, TARIKH_LAWATAN, FLAG_LAWATAN, TUJUAN_LAPORAN, LAPORAN_ATASTANAH, ULASAN,"
					+ " JALAN_HUBUNGAN, KAWASAN_BERHAMPIRAN, JARAK_DARIBANDAR, KEADAAN_RUPABUMI, KEADAAN_TANAH, FLAG_KEMUDAHANASAS_AIR,"
					+ " FLAG_KEMUDAHANASAS_ELEKTRIK, FLAG_KEMUDAHANASAS_TEL, KEMUDAHAN_ASAS, SEMP_UTARA, SEMP_SELATAN, SEMP_TIMUR, SEMP_BARAT,"
					+ " NAMA_PELAPOR, ID_JAWATANPELAPOR, ID_NEGERIPELAPOR, CATATAN, ID_SEJARAH, SEJARAH_TANAH, NAMA_BANDAR, "
					+ " NAMA_PENYEMAK, ID_NEGERIPENYEMAK, ID_JAWATANPENYEMAK, CATATAN_PELAPOR, CATATAN_PENYEMAK "
					+ " FROM TBLPHPLAPORANTANAH"
					+ " WHERE ID_LAPORANTANAH = '"
					+ idLaporanTanah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idLaporanTanah",
						rs.getString("ID_LAPORANTANAH") == null ? "" : rs
								.getString("ID_LAPORANTANAH"));
				h.put("flagLawatan", rs.getString("FLAG_LAWATAN") == null ? ""
						: rs.getString("FLAG_LAWATAN"));
				h.put("tarikhLawatan",
						rs.getString("TARIKH_LAWATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LAWATAN")));
				h.put("tujuanLaporan",
						rs.getString("TUJUAN_LAPORAN") == null ? "" : rs
								.getString("TUJUAN_LAPORAN"));
				h.put("laporanAtasTanah",
						rs.getString("LAPORAN_ATASTANAH") == null ? "" : rs
								.getString("LAPORAN_ATASTANAH"));
				h.put("ulasan",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));

				h.put("jalanHubungan",
						rs.getString("JALAN_HUBUNGAN") == null ? "" : rs
								.getString("JALAN_HUBUNGAN"));
				h.put("kawasanBerhampiran",
						rs.getString("KAWASAN_BERHAMPIRAN") == null ? "" : rs
								.getString("KAWASAN_BERHAMPIRAN"));
				h.put("jarakDariBandar",
						rs.getString("JARAK_DARIBANDAR") == null ? "" : rs
								.getString("JARAK_DARIBANDAR"));
				h.put("keadaanRupabumi",
						rs.getString("KEADAAN_RUPABUMI") == null ? "" : rs
								.getString("KEADAAN_RUPABUMI"));
				h.put("keadaanTanah",
						rs.getString("KEADAAN_TANAH") == null ? "" : rs
								.getString("KEADAAN_TANAH"));
				h.put("flagAir",
						rs.getString("FLAG_KEMUDAHANASAS_AIR") == null ? ""
								: rs.getString("FLAG_KEMUDAHANASAS_AIR"));
				h.put("flagElektrik", rs
						.getString("FLAG_KEMUDAHANASAS_ELEKTRIK") == null ? ""
						: rs.getString("FLAG_KEMUDAHANASAS_ELEKTRIK"));
				h.put("flagTel",
						rs.getString("FLAG_KEMUDAHANASAS_TEL") == null ? ""
								: rs.getString("FLAG_KEMUDAHANASAS_TEL"));
				h.put("kemudahanAsas",
						rs.getString("KEMUDAHAN_ASAS") == null ? "" : rs
								.getString("KEMUDAHAN_ASAS"));
				h.put("utara",
						rs.getString("SEMP_UTARA") == null ? "" : rs
								.getString("SEMP_UTARA"));
				h.put("selatan",
						rs.getString("SEMP_SELATAN") == null ? "" : rs
								.getString("SEMP_SELATAN"));
				h.put("timur",
						rs.getString("SEMP_TIMUR") == null ? "" : rs
								.getString("SEMP_TIMUR"));
				h.put("barat",
						rs.getString("SEMP_BARAT") == null ? "" : rs
								.getString("SEMP_BARAT"));

				h.put("namaPegawai", rs.getString("NAMA_PELAPOR") == null ? ""
						: rs.getString("NAMA_PELAPOR"));
				h.put("idJawatan",
						rs.getString("ID_JAWATANPELAPOR") == null ? "99999"
								: rs.getString("ID_JAWATANPELAPOR"));
				h.put("idNegeri",
						rs.getString("ID_NEGERIPELAPOR") == null ? "99999" : rs
								.getString("ID_NEGERIPELAPOR"));
				h.put("idSejarah", rs.getString("ID_SEJARAH") == null ? "99999"
						: rs.getString("ID_SEJARAH"));
				h.put("sejarahTanah",
						rs.getString("SEJARAH_TANAH") == null ? "" : rs
								.getString("SEJARAH_TANAH"));
				h.put("namaBandar", rs.getString("NAMA_BANDAR") == null ? ""
						: rs.getString("NAMA_BANDAR"));
				h.put("namaPenyemak",
						rs.getString("NAMA_PENYEMAK") == null ? "" : rs
								.getString("NAMA_PENYEMAK"));
				h.put("idNegeriPenyemak",
						rs.getString("ID_NEGERIPENYEMAK") == null ? "99999"
								: rs.getString("ID_NEGERIPENYEMAK"));
				h.put("idJawatanPenyemak",
						rs.getString("ID_JAWATANPENYEMAK") == null ? "99999"
								: rs.getString("ID_JAWATANPENYEMAK"));
				h.put("catatanPelapor",
						rs.getString("CATATAN_PELAPOR") == null ? "" : rs
								.getString("CATATAN_PELAPOR"));
				h.put("catatanPenyemak",
						rs.getString("CATATAN_PENYEMAK") == null ? "" : rs
								.getString("CATATAN_PENYEMAK"));
				beanMaklumatLaporanTanah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniMaklumatLaporanTanah(String idLaporanTanah,
			String txtTarikhLawatan, String socFlagLawatan,
			String txtTujuanLaporan, String txtLaporanAtasTanah,
			String txtIsuUlasan, String txtCatatan, HttpSession session)
			throws Exception {

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

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporanTanah);

			if (!"".equals(txtTarikhLawatan)) {
				r.add("TARIKH_LAWATAN",
						r.unquote("to_date('" + txtTarikhLawatan
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_LAWATAN", socFlagLawatan);
			r.add("TUJUAN_LAPORAN", txtTujuanLaporan);
			r.add("LAPORAN_ATASTANAH", txtLaporanAtasTanah);
			r.add("ULASAN", txtIsuUlasan);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");

			stmt.executeUpdate(sql);
			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idLaporanTanah + "] DIKEMASKINI");

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

	public void updateMaklumatLaporanTanah(String idPermohonan,
			String txtLaporanAtasTanah, HttpSession session) throws Exception {

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

			// TBLPHPKERTASKERJAPENYEWAAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ULASAN_JKPTGN", txtLaporanAtasTanah);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPENYEWAAN");
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

	public void simpanKemaskiniMaklumatLain(String idLaporanTanah,
			String txtJalanHubungan, String txtKawasanBerhampiran,
			String txtNamaBandar, String txtJarakDariBandar,
			String kemudahanAsasA, String kemudahanAsasL,
			String kemudahanAsasT, String txtKemudahanAsas,
			String txtKeadaanTanah, String txtKeadaanRupabumi, String txtUtara,
			String txtSelatan, String txtTimur, String txtBarat,
			String txtSejarahTanah, HttpSession session) throws Exception {

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

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporanTanah);

			r.add("JALAN_HUBUNGAN", txtJalanHubungan);
			r.add("KAWASAN_BERHAMPIRAN", txtKawasanBerhampiran);
			r.add("NAMA_BANDAR", txtNamaBandar);
			r.add("JARAK_DARIBANDAR", txtJarakDariBandar);
			r.add("FLAG_KEMUDAHANASAS_AIR", kemudahanAsasA);
			r.add("FLAG_KEMUDAHANASAS_ELEKTRIK", kemudahanAsasL);
			r.add("FLAG_KEMUDAHANASAS_TEL", kemudahanAsasT);
			r.add("KEMUDAHAN_ASAS", txtKemudahanAsas);
			r.add("KEADAAN_TANAH", txtKeadaanTanah);
			r.add("KEADAAN_RUPABUMI", txtKeadaanRupabumi);
			r.add("SEMP_UTARA", txtUtara);
			r.add("SEMP_SELATAN", txtSelatan);
			r.add("SEMP_TIMUR", txtTimur);
			r.add("SEMP_BARAT", txtBarat);
			r.add("SEJARAH_TANAH", txtSejarahTanah);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");

			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idLaporanTanah + "] DIKEMASKINI");

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

	public void setSenaraiKehadiran(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKehadiran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PEGAWAILAPORANTANAH, A.NAMA_PEGAWAI, B.KETERANGAN AS JAWATAN, C.NAMA_NEGERI"
					+ " FROM TBLPHPPEGAWAILAPORANTANAH A, TBLRUJJAWATAN B, TBLRUJNEGERI C WHERE A.ID_JAWATAN = B.ID_JAWATAN(+) AND A.ID_NEGERIPEGAWAI = C.ID_NEGERI(+)"
					+ " AND A.ID_LAPORANTANAH = '" + idLaporanTanah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPegawaiLaporanTanah",
						rs.getString("ID_PEGAWAILAPORANTANAH") == null ? ""
								: rs.getString("ID_PEGAWAILAPORANTANAH"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI").toUpperCase());
				h.put("jawatan", rs.getString("JAWATAN") == null ? "" : rs
						.getString("JAWATAN").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				listKehadiran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getFlagLawatanByIdLaporanTanah(String idLaporanTanah)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FLAG_LAWATAN FROM TBLPHPLAPORANTANAH WHERE ID_LAPORANTANAH = '"
					+ idLaporanTanah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("FLAG_LAWATAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdNegeriTanahPohon(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_NEGERI FROM TBLPHPHAKMILIKPERMOHONAN A, TBLHTPHAKMILIKAGENSI B, TBLHTPHAKMILIK C"
					+ " WHERE A.ID_HAKMILIKAGENSI = B.ID_HAKMILIKAGENSI AND B.ID_HAKMILIK = C.ID_HAKMILIK AND"
					+ " A.ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_NEGERI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKehadiran(String idLaporanTanah, String txtNamaKehadiran,
			String idNegeri, String idJawatan, HttpSession session)
			throws Exception {

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

			// TBLPHPPEGAWAILAPORANTANAH
			long idPegawaiLaporan = DB
					.getNextID("TBLPHPPEGAWAILAPORANTANAH_SEQ");
			r.add("ID_PEGAWAILAPORANTANAH", idPegawaiLaporan);
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("NAMA_PEGAWAI", txtNamaKehadiran);
			r.add("ID_JAWATAN", idJawatan);
			r.add("ID_NEGERIPEGAWAI", idNegeri);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + idLaporanTanah + "] DIDAFTARKAN");

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

	public void simpanKemaskiniKehadiran(String idPegawaiLaporanTanah,
			String txtNamaKehadiran, String idNegeri, String idJawatan,
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

			// TBLPHPPEGAWAILAPORANTANAH
			r.update("ID_PEGAWAILAPORANTANAH", idPegawaiLaporanTanah);
			r.add("NAMA_PEGAWAI", txtNamaKehadiran);
			r.add("ID_JAWATAN", idJawatan);
			r.add("ID_NEGERIPEGAWAI", idNegeri);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idPegawaiLaporanTanah + "] DIKEMASKINI");

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

	public void hapusKehadiran(String idPegawaiLaporanTanah, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEGAWAILAPORANTANAH
			r.add("ID_PEGAWAILAPORANTANAH", idPegawaiLaporanTanah);

			sql = r.getSQLDelete("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "DEL",
					"FAIL [" + idPegawaiLaporanTanah + "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatKehadiran(String idPegawaiLaporanTanah)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKehadiran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_PEGAWAI, ID_JAWATAN, ID_NEGERIPEGAWAI FROM TBLPHPPEGAWAILAPORANTANAH WHERE ID_PEGAWAILAPORANTANAH = '"
					+ idPegawaiLaporanTanah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("idJawatan", rs.getString("ID_JAWATAN") == null ? "99999"
						: rs.getString("ID_JAWATAN"));
				h.put("idNegeri",
						rs.getString("ID_NEGERIPEGAWAI") == null ? "99999" : rs
								.getString("ID_NEGERIPEGAWAI"));
				beanMaklumatKehadiran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniPelapor(String idLaporanTanah, String txtNama,
			String idNegeri, String idJawatan, String catatanPelapor,
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

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporanTanah);
			r.add("NAMA_PELAPOR", txtNama);
			r.add("ID_JAWATANPELAPOR", idJawatan);
			r.add("ID_NEGERIPELAPOR", idNegeri);
			r.add("CATATAN_PELAPOR", catatanPelapor);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idLaporanTanah + "] DIKEMASKINI");

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

	public void simpanKemaskiniPenyemak(String idLaporanTanah,
			String txtNamaPenyemak, String idNegeriPenyemak,
			String idJawatanPenyemak, String catatanPenyemak,
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

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporanTanah);
			r.add("NAMA_PENYEMAK", txtNamaPenyemak);
			r.add("ID_JAWATANPENYEMAK", idJawatanPenyemak);
			r.add("ID_NEGERIPENYEMAK", idNegeriPenyemak);
			r.add("CATATAN_PENYEMAK", catatanPenyemak);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idLaporanTanah + "] DIKEMASKINI");

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

	public void setSenaraiImejan(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_LAPORANTANAH = '" + idLaporanTanah
					+ "' AND FLAG_DOKUMEN = 'I'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				listImejan.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiPelan(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listPelan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_LAPORANTANAH = '" + idLaporanTanah
					+ "' AND FLAG_DOKUMEN = 'P'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				listPelan.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatImej(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaImej", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanImej",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatImejan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniDokumen(String idDokumen, String txtNamaImej,
			String txtCatatan, HttpSession session) throws Exception {
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
			r.add("NAMA_DOKUMEN", txtNamaImej);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + idDokumen + "] DIDAFTARKAN");

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

	public void hapusDokumen(String idDokumen, HttpSession session)
			throws Exception {
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
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "DEL",
					"FAIL [" + idDokumen + "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void gotoSemakanPPTKanan(String idFail, String idNegeriUser,
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "PenyemakNegeri");
			r.add("FLAG_BUKA", "T");
			r.add("CATATAN", "Perlu Semakan dan Pengesahan Daripada Penolong Pegawai Tanah(Kanan)");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610213", "4", null, session, "UPD",
					"FAIL [" + idFail + "] DIHANTAR KEPADA PP");

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

	public void doPembetulanMaklumat(String idFail, String idNegeriUser,
			String catatan, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idPegawai = "";
		String idNegeri = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT * FROM TBLPHPLOGTUGASAN WHERE ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri' AND ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idPegawai = rs.getString("ID_PEGAWAI");
				idNegeri = rs.getString("ID_NEGERI");
			}

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", idPegawai);
			r.add("ID_NEGERI", idNegeri);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_PEMBETULAN", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPegawaiTanahNegeri");
			r.add("CATATAN", catatan);
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610213", "4", null, session, "INS",
					"FAIL [" + idFail + "] TELAH DIHANTAR");

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

	public void updateStatus(String idFail, String idPermohonan,
			String idSuburusan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String idNegeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610213"); // CETAKAN KERTAS RINGKASAN

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
					getIdSuburusanstatus(idSuburusan, "1610213")); // CETAKAN
																	// KERTAS
																	// RINGKASAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			
			String idPegawai = "";
			String idNegeri = "";
			sql = "SELECT * FROM TBLPHPLOGTUGASAN WHERE ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri' AND ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idPegawai = rs.getString("ID_PEGAWAI");
				idNegeri = rs.getString("ID_NEGERI");
			}
			
			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", idPegawai);
			r.add("ID_NEGERI", idNegeri);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPegawaiTanahNegeri");
			r.add("CATATAN", "Sila lengkapkan maklumat di ruangan Cetakan Kertas Ringkasan");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610213", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] PROSES SETERUSNYA");

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

	public void doBatalPermohonan(String idFail, String idPermohonan,
			String idSuburusan, String tarikhBatal, String txtSebab,
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

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610212");
			r.add("TARIKH_BATAL",
					r.unquote("to_date('" + tarikhBatal + "','dd/MM/yyyy')"));
			r.add("CATATAN_BATAL", txtSebab);

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
					getIdSuburusanstatus(idSuburusan, "1610212")); // BATAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610212", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIBATALKAN");

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

	public String getNoFailByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPFDFAIL.NO_FAIL FROM TBLPFDFAIL, TBLPERMOHONAN"
					+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL"
					+ " AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("NO_FAIL");
			} else {
				return "";
			}

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
				return (String) rs.getString("ID_SUBURUSANSTATUS");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getFlagMT(String idFail, String idPegawai) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FLAG_MT FROM TBLPHPLOGTUGASAN WHERE ID_FAIL = '"
					+ idFail
					+ "' AND ID_PEGAWAI = '"
					+ idPegawai
					+ "' AND FLAG_AKTIF = 'Y' AND ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("FLAG_MT");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPelan(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatPelan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN WHERE FLAG_DOKUMEN = 'P' AND ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Pelan mana pelan? " + sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaPelan", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanPelan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatPelan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniPelan(String idDokumen, String txtNamaPelan,
			String txtCatatan, HttpSession session) throws Exception {
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
			r.add("NAMA_DOKUMEN", txtNamaPelan);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idDokumen + "] DIKEMASKINI");

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

	public void hapusPelan(String idDokumen, HttpSession session)
			throws Exception {
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
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610200", "4", null, session, "DEL",
					"FAIL [" + idDokumen + "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatLaporanTanah() {
		return beanMaklumatLaporanTanah;
	}

	public void setBeanMaklumatLaporanTanah(Vector beanMaklumatLaporanTanah) {
		this.beanMaklumatLaporanTanah = beanMaklumatLaporanTanah;
	}

	public Vector getListKehadiran() {
		return listKehadiran;
	}

	public void setListKehadiran(Vector listKehadiran) {
		this.listKehadiran = listKehadiran;
	}

	public Vector getBeanMaklumatKehadiran() {
		return beanMaklumatKehadiran;
	}

	public void setBeanMaklumatKehadiran(Vector beanMaklumatKehadiran) {
		this.beanMaklumatKehadiran = beanMaklumatKehadiran;
	}

	public Vector getListImejan() {
		return listImejan;
	}

	public void setListImejan(Vector listImejan) {
		this.listImejan = listImejan;
	}

	public Vector getBeanMaklumatImejan() {
		return beanMaklumatImejan;
	}

	public void setBeanMaklumatImejan(Vector beanMaklumatImejan) {
		this.beanMaklumatImejan = beanMaklumatImejan;
	}

	public Vector getListPelan() {
		return listPelan;
	}

	public void setListPelan(Vector listPelan) {
		this.listPelan = listPelan;
	}

	public Vector getBeanMaklumatPelan() {
		return beanMaklumatPelan;
	}

	public void setBeanMaklumatPelan(Vector beanMaklumatPelan) {
		this.beanMaklumatPelan = beanMaklumatPelan;
	}
}