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

public class FrmCRBLawatanTapakData {

	private Vector beanMaklumatPejabat = null;
	private Vector beanMaklumatKJT = null;
	private Vector listKJT = null;
	private Vector listLawatanTapak = null;
	private Vector beanMaklumatLaporanTanah = null;
	private Vector listPenceroboh = null;
	private Vector beanMaklumatPenceroboh = null;
	private Vector listKehadiran = null;
	private Vector beanMaklumatKehadiran = null;
	private Vector listImejan = null;
	private Vector beanMaklumatImejan = null;
	private Vector listLampiran = null;
	private Vector beanMaklumatLampiran = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatPejabatJKPTG(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX, A.ID_PEJABATJKPTG"
					+ " FROM TBLRUJPEJABATJKPTG A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABATJKPTG = '"
					+ idPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabat", rs.getString("ID_PEJABATJKPTG") == null ? ""
						: rs.getString("ID_PEJABATJKPTG"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3").toUpperCase());
				h.put("poskod",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs
						.getString("NAMA_BANDAR").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs
						.getString("NO_TEL").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX").toUpperCase());
				beanMaklumatPejabat.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idPejabat", "");
				h.put("namaPejabat", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("bandar", "");
				h.put("negeri", "");
				h.put("idNegeri", "");
				h.put("noTel", "");
				h.put("noFax", "");
				beanMaklumatPejabat.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatKJT(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKJT = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL, ID_MENTERI, ID_AGENSI, ID_DOKUMEN, ID_NEGERI, ID_PEJABAT, TARIKH_HANTAR,"
					+ " TARIKH_JANGKA_TERIMA, JANGKAMASA, FLAG_STATUS, FLAG_AKTIF,"
					+ " TARIKH_TERIMA, TARIKH_SURAT, NO_RUJUKAN, ULASAN"
					+ " FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("idKementerian",
						rs.getString("ID_MENTERI") == null ? "99999" : rs
								.getString("ID_MENTERI"));
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "99999"
						: rs.getString("ID_AGENSI"));
				h.put("idDokumen", rs.getString("ID_DOKUMEN") == null ? "99999"
						: rs.getString("ID_DOKUMEN"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999"
						: rs.getString("ID_NEGERI"));
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "99999"
						: rs.getString("ID_PEJABAT"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("jangkamasa", rs.getString("JANGKAMASA") == null ? ""
						: rs.getString("JANGKAMASA"));
				h.put("tarikhJangkaTerima",
						rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_JANGKA_TERIMA")));
				h.put("flagStatus",
						rs.getString("FLAG_STATUS") == null ? "99999" : rs
								.getString("FLAG_STATUS"));
				h.put("flagAktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("ulasan",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				beanMaklumatKJT.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String simpanMaklumatKJT(String idPermohonan, String idPejabat,
			String idNegeri, String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPULASANTEKNIKAL
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_DOKUMEN", "4"); //SURAT MINTA LAPORAN TANAH
			r.add("FLAG_KJP", "KJT");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", "0");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIDAFTARKAN");

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
		return idUlasanTeknikalString;
	}

	public String simpanMaklumatUlanganKJT(String idUlasanTeknikalLama,
			String idPermohonan, String idPejabat, String idNegeri,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idUlasanTeknikalString = "";
		int bilUlangan = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT BIL_ULANGAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bilUlangan = rs.getInt("BIL_ULANGAN");
				bilUlangan++;
			}

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikalLama);
			r.add("FLAG_STATUS", "3");
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			idUlasanTeknikalString = String.valueOf(idUlasanTeknikal);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_DOKUMEN", "4"); //SURAT MINTA LAPORAN TANAH
			r.add("FLAG_KJP", "KJT");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", "1");
			r.add("FLAG_AKTIF", "Y");
			r.add("BIL_ULANGAN", bilUlangan);
			r.add("ID_PARENT", idUlasanTeknikalLama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIDAFTARKAN");

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
		return idUlasanTeknikalString;
	}

	public void simpanKemaskiniMaklumatKJT(String idUlasanTeknikal,
			String idPejabat, String idNegeri, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String flagStatus, String txtTarikhTerima, String txtTarikhSurat,
			String txtNoRujukan, String txtUlasan, HttpSession session)
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

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_PEJABAT", idPejabat);
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkaMasa);
			if (!"".equals(txtTarikhJangkaTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhJangkaTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("FLAG_STATUS", flagStatus);
			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(txtTarikhSurat)) {
				r.add("TARIKH_SURAT",
						r.unquote("to_date('" + txtTarikhSurat
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", txtNoRujukan);
			r.add("ULASAN", txtUlasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idUlasanTeknikal
							+ "] DIKEMASKINI");

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
	
	public void setSenaraiKJT(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKJT = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, D.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, D.ID_DOKUMEN, C.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLRUJPEJABATJKPTG C, TBLPHPRUJDOKUMEN D WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_PEJABAT = C.ID_PEJABATJKPTG(+) AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) AND A.FLAG_KJP = 'KJT' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("idDokumen",
						rs.getString("ID_DOKUMEN") == null ? "" : rs
								.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				if ("1".equals(rs.getString("ID_DOKUMEN"))) {
					h.put("agensi", rs.getString("NAMA_AGENSI") == null ? ""
							: rs.getString("NAMA_AGENSI").toUpperCase());
				} else if ("4".equals(rs.getString("ID_DOKUMEN"))) {
					h.put("agensi", rs.getString("NAMA_PEJABAT") == null ? ""
							: rs.getString("NAMA_PEJABAT").toUpperCase());
				} else {
					h.put("agensi", "");
				}
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("tarikhJangkaTerima",
						rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_JANGKA_TERIMA")));
				h.put("flagStatus", rs.getString("FLAG_STATUS") == null ? ""
						: rs.getString("FLAG_STATUS"));
				if ("1".equals(rs.getString("FLAG_STATUS"))) {
					h.put("status", "TELAH DIHANTAR");
				} else if ("2".equals(rs.getString("FLAG_STATUS"))) {
					h.put("status", "DITERIMA");
				} else if ("3".equals(rs.getString("FLAG_STATUS"))) {
					h.put("status", "TIADA JAWAPAN");
				} else {
					h.put("status", "");
				}
				h.put("flagAktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("bilUlangan", rs.getString("BIL_ULANGAN") == null ? ""
						: rs.getString("BIL_ULANGAN"));
				listKJT.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusMaklumatKJPKJT(String idUlasanTeknikal, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";
		String idParent = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_PARENT FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("ID_PARENT") != null
						&& !"".equals(rs.getString("ID_PARENT"))) {
					// TBLPHPULASANTEKNIKAL
					idParent = rs.getString("ID_PARENT");
					r = new SQLRenderer();
					r.update("ID_ULASANTEKNIKAL", idParent);
					r.add("FLAG_STATUS", "1");
					r.add("FLAG_AKTIF", "Y");

					sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
					stmt.executeUpdate(sql);
				}
			}

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);

			sql = r.getSQLDelete("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "DEL",
					"FAIL [" + idUlasanTeknikal
							+ "] DIHAPUSKAN");

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
	
	public void setSenaraiLawatanTapak(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listLawatanTapak = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.TARIKH_LAWATAN");
			r.add("A.ID_LAPORANTANAH");
			r.add("A.TUJUAN_LAPORAN");
			r.add("A.ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_LAPORAN", "1");
			r.add("A.ID_PERMOHONAN", r.unquote("B.ID_PERMOHONAN"));

			sql = r.getSQLSelect("TBLPHPLAPORANTANAH A, TBLPERMOHONAN B",
					"A.TARIKH_LAWATAN ASC");

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("tarikhLawatan",
						rs.getDate("TARIKH_LAWATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LAWATAN")));
				h.put("idLaporanTanah",
						rs.getString("ID_LAPORANTANAH") == null ? "" : rs
								.getString("ID_LAPORANTANAH"));
				h.put("tujuanLawatan",
						rs.getString("TUJUAN_LAPORAN") == null ? "" : rs
								.getString("TUJUAN_LAPORAN"));
				listLawatanTapak.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanSiasatanLawatanTapak(String idPermohonan,
			String tarikhTerimaFail, String tarikhLawatan, String tarikhLaporan, String tujuanLaporan,
			String lokasi, String laporanSemasaTapak, String ulasan,
			String idhakmilikPermohonan, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idLaporanTanahString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TTF = "to_date('" + tarikhTerimaFail + "','dd/MM/yyyy')";
			String TLT = "to_date('" + tarikhLawatan + "','dd/MM/yyyy')";
			String TL = "to_date('" + tarikhLaporan + "','dd/MM/yyyy')";

			// TBLPHPLAPORANTANAH
			long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			idLaporanTanahString = String.valueOf(idLaporanTanah);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_LAPORAN", "1"); //LAWATAN TAPAK
			r.add("FLAG_JENISTANAH", "P");
			r.add("TARIKH_TERIMA_FAIL", r.unquote(TTF));
			r.add("TARIKH_LAWATAN", r.unquote(TLT));
			r.add("TARIKH_LAPORAN", r.unquote(TL));
			r.add("TUJUAN_LAPORAN", tujuanLaporan);
			r.add("LOKASI", lokasi);
			r.add("LAPORAN_ATASTANAH", laporanSemasaTapak);
			r.add("ULASAN", ulasan);
			r.add("ID_HAKMILIK", idhakmilikPermohonan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIDAFTARKAN");

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
		return idLaporanTanahString;
	}
	
	public void setMaklumatLaporanTanah(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatLaporanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPLAPORANTANAH"
					+ " WHERE FLAG_LAPORAN = '1' AND ID_LAPORANTANAH = '"
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
				h.put("tarikhTerimaFail",
						rs.getString("TARIKH_TERIMA_FAIL") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_FAIL")));
				h.put("tarikhLawatan",
						rs.getString("TARIKH_LAWATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LAWATAN")));
				h.put("tarikhLaporan",
						rs.getString("TARIKH_LAPORAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LAPORAN")));
				h.put("tujuanLaporan",
						rs.getString("TUJUAN_LAPORAN") == null ? "" : rs
								.getString("TUJUAN_LAPORAN"));
				h.put("lokasi",
						rs.getString("LOKASI") == null ? "" : rs
								.getString("LOKASI"));
				h.put("laporanSemasaTapak",
						rs.getString("LAPORAN_ATASTANAH") == null ? "" : rs
								.getString("LAPORAN_ATASTANAH"));
				h.put("ulasan",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));

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
				h.put("jawatanPelapor",
						rs.getString("NAMA_JAWATAN_PELAPOR") == null ? "" : rs
								.getString("NAMA_JAWATAN_PELAPOR"));
				h.put("idNegeri",
						rs.getString("ID_NEGERIPELAPOR") == null ? "99999" : rs
								.getString("ID_NEGERIPELAPOR"));
				h.put("syor",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("namaPenyemak", rs.getString("NAMA_PENYEMAK") == null ? ""
						: rs.getString("NAMA_PENYEMAK"));
				h.put("jawatanPenyemak",
						rs.getString("NAMA_JAWATAN_PENYEMAK") == null ? "" : rs
								.getString("NAMA_JAWATAN_PENYEMAK"));
				h.put("idNegeriPenyemak",
						rs.getString("ID_NEGERIPENYEMAK") == null ? "99999" : rs
								.getString("ID_NEGERIPENYEMAK"));
				beanMaklumatLaporanTanah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateSiasatanLawatanTapak(String idPermohonan, String idLaporanTanah,
			String tarikhTerimaFail, String tarikhLawatan, String tarikhLaporan, String tujuanLaporan,
			String lokasi, String laporanSemasaTapak, String ulasan, HttpSession session) throws Exception {

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

			String TTF = "to_date('" + tarikhTerimaFail + "','dd/MM/yyyy')";
			String TLT = "to_date('" + tarikhLawatan + "','dd/MM/yyyy')";
			String TL = "to_date('" + tarikhLaporan + "','dd/MM/yyyy')";

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporanTanah);
			r.update("FLAG_LAPORAN", "1");
			
			r.add("FLAG_JENISTANAH", "P");
			r.add("TARIKH_TERIMA_FAIL", r.unquote(TTF));
			r.add("TARIKH_LAWATAN", r.unquote(TLT));
			r.add("TARIKH_LAPORAN", r.unquote(TL));
			r.add("TUJUAN_LAPORAN", tujuanLaporan);
			r.add("LOKASI", lokasi);
			r.add("LAPORAN_ATASTANAH", laporanSemasaTapak);
			r.add("ULASAN", ulasan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIKEMASKINI");

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
	
	public void setSenaraiPenceroboh(String idPermohonan, String idLaporanTanah)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPenceroboh = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PENCEROBOH");
			r.add("A.JENIS_PENCEROBOHAN");
			r.add("A.NAMA");
			r.add("A.ALAMAT1");
			r.add("A.ALAMAT2");
			r.add("A.ALAMAT3");
			r.add("A.POSKOD");
			r.add("A.ID_NEGERI");
			r.add("A.ID_BANDAR");
			r.add("A.ID_PERMOHONAN", idPermohonan);
			r.add("A.ID_LAPORANTANAH", idLaporanTanah);

			sql = r.getSQLSelect("TBLPHPPENCEROBOH A ", "A.ID_PENCEROBOH ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPenceroboh",
						rs.getString("ID_PENCEROBOH") == null ? "" : rs
								.getString("ID_PENCEROBOH"));
				h.put("namaPenceroboh",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("alamatPenceroboh1", rs.getString("ALAMAT1") == null ? ""
						: rs.getString("ALAMAT1"));
				h.put("alamatPenceroboh2", rs.getString("ALAMAT2") == null ? ""
						: rs.getString("ALAMAT2"));
				h.put("alamatPenceroboh3", rs.getString("ALAMAT3") == null ? ""
						: rs.getString("ALAMAT3"));
				h.put("jenisPencerobohan", rs.getString("JENIS_PENCEROBOHAN") == null ? ""
						: rs.getString("JENIS_PENCEROBOHAN"));
				listPenceroboh.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusMaklumatLT(String idLaporanTanah, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPDOKUMEN
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			// TBLPHPPENCEROBOH
			r = new SQLRenderer();
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			sql = r.getSQLDelete("TBLPHPPENCEROBOH");
			stmt.executeUpdate(sql);

			// TBLPHPPEGAWAILAPORANTANAH
			r = new SQLRenderer();
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			sql = r.getSQLDelete("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			// TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			sql = r.getSQLDelete("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "DEL",
					"FAIL [" + idLaporanTanah
							+ "] DIHAPUSKAN");

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
	
	public void simpanKemaskiniMaklumatLain(String idLaporanTanah,
			String txtJalanHubungan, String txtKawasanBerhampiran,
			String txtJarakDariBandar, String kemudahanAsasA,
			String kemudahanAsasL, String kemudahanAsasT,
			String txtKemudahanAsas, String txtKeadaanTanah,
			String txtKeadaanRupabumi, String txtUtara, String txtSelatan,
			String txtTimur, String txtBarat, HttpSession session)
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
			r.update("FLAG_LAPORAN", "1");

			r.add("JALAN_HUBUNGAN", txtJalanHubungan);
			r.add("KAWASAN_BERHAMPIRAN", txtKawasanBerhampiran);
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

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");

			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idLaporanTanah
							+ "] DIKEMASKINI"); 

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
	
	public void setMaklumatPenceroboh(String idPenceroboh) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPenceroboh = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA, ID_BANGSA, NO_TEL_RUMAH, NO_TEL_BIMBIT, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD,"
					+ " ID_NEGERI,ID_BANDAR, EMEL, JENIS_PENCEROBOHAN"
					+ " FROM TBLPHPPENCEROBOH WHERE ID_PENCEROBOH = '"
					+ idPenceroboh + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("namaPenceroboh",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999"
						: rs.getString("ID_BANGSA"));
				h.put("noTelefon", rs.getString("NO_TEL_RUMAH") == null ? ""
						: rs.getString("NO_TEL_RUMAH"));
				h.put("noTelefonBimbit",
						rs.getString("NO_TEL_BIMBIT") == null ? "" : rs
								.getString("NO_TEL_BIMBIT"));
				h.put("alamat1",
						rs.getString("ALAMAT1") == null ? "" : rs
								.getString("ALAMAT1"));
				h.put("alamat2",
						rs.getString("ALAMAT2") == null ? "" : rs
								.getString("ALAMAT2"));
				h.put("alamat3",
						rs.getString("ALAMAT3") == null ? "" : rs
								.getString("ALAMAT3"));
				h.put("poskod",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				h.put("idNegeriPenceroboh",
						rs.getString("ID_NEGERI") == null ? "99999" : rs
								.getString("ID_NEGERI"));
				h.put("idBandarPenceroboh",
						rs.getString("ID_BANDAR") == null ? "99999" : rs
								.getString("ID_BANDAR"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("jenisPencerobohan",
						rs.getString("JENIS_PENCEROBOHAN") == null ? "" : rs
								.getString("JENIS_PENCEROBOHAN"));
				beanMaklumatPenceroboh.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String savePenceroboh(String idPermohonan, String idLaporanTanah,
			String namaPenceroboh, String noTelefon, String noTelefonBimbit,
			String idBangsa, String alamat1, String alamat2, String alamat3,
			String poskod, String idNegeri, String idBandar, String emel, String jenisPencerobohan,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPencerobohString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENCEROBOH
			long idPenceroboh = DB.getNextID("TBLPHPPENCEROBOH_SEQ");
			r.add("ID_PENCEROBOH", idPenceroboh);
			idPencerobohString = String.valueOf(idPenceroboh);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("NAMA", namaPenceroboh);
			r.add("ID_BANGSA", idBangsa);
			r.add("NO_TEL_RUMAH", noTelefon);
			r.add("NO_TEL_BIMBIT", noTelefonBimbit);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("EMEL", emel);
			r.add("JENIS_PENCEROBOHAN", jenisPencerobohan);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPENCEROBOH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIDAFTARKAN");

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
		return idPencerobohString;
	}
	
	public void updatePenceroboh(String idPenceroboh, String namaPenceroboh,
			String noTelefon, String noTelefonBimbit, String idBangsa,
			String alamat1, String alamat2, String alamat3, String poskod,
			String idNegeri, String idBandar, String emel, String jenisPencerobohan, HttpSession session)
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

			// TBLPHPPENCEROBOH
			r.update("ID_PENCEROBOH", idPenceroboh);
			r.add("NAMA", namaPenceroboh);
			r.add("ID_BANGSA", idBangsa);
			r.add("NO_TEL_RUMAH", noTelefon);
			r.add("NO_TEL_BIMBIT", noTelefonBimbit);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("EMEL", emel);
			r.add("JENIS_PENCEROBOHAN", jenisPencerobohan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPENCEROBOH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + idPenceroboh
							+ "] DIDAFTARKAN");

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

	public void hapusMaklumatPenceroboh(String idPenceroboh, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENCEROBOH
			r.add("ID_PENCEROBOH", idPenceroboh);
			sql = r.getSQLDelete("TBLPHPPENCEROBOH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "DEL",
					"FAIL [" + idPenceroboh
							+ "] DIHAPUSKAN");

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
	
	public void setSenaraiKehadiran(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKehadiran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PEGAWAILAPORANTANAH, A.NAMA_PEGAWAI, A.NAMA_JAWATAN FROM TBLPHPPEGAWAILAPORANTANAH A WHERE"
					+ " A.ID_LAPORANTANAH = '" + idLaporanTanah + "'";
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
				h.put("namaJawatan", rs.getString("NAMA_JAWATAN") == null ? ""
						: rs.getString("NAMA_JAWATAN"));
				listKehadiran.addElement(h);
				bil++;
			}

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

			sql = "SELECT ID_PEGAWAILAPORANTANAH, NAMA_PEGAWAI, NAMA_JAWATAN, ID_MENTERI, ID_AGENSI"
					+ " FROM TBLPHPPEGAWAILAPORANTANAH WHERE ID_PEGAWAILAPORANTANAH = '"
					+ idPegawaiLaporanTanah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPegawaiLaporanTanah",
						rs.getString("ID_PEGAWAILAPORANTANAH") == null ? ""
								: rs.getString("ID_PEGAWAILAPORANTANAH"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("idKementerian",
						rs.getString("ID_MENTERI") == null ? "99999" : rs
								.getString("ID_MENTERI"));
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "99999"
						: rs.getString("ID_AGENSI"));
				beanMaklumatKehadiran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String saveKehadiran(String idLaporanTanah, String namaPegawai,
			String txtJawatan, String idKementerian, String idAgensi,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPegawaiLaporanString = "";

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
			idPegawaiLaporanString = String.valueOf(idPegawaiLaporan);
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", txtJawatan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL [" + idLaporanTanah
							+ "] DIDAFTARKAN");

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
		return idPegawaiLaporanString;
	}

	public void updateKehadiran(String idPegawaiLaporanTanah,
			String namaPegawai, String txtJawatan, String idKementerian,
			String idAgensi, HttpSession session) throws Exception {

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

			// TBLPHPPEGAWAILAPORANTANAH
			r.update("ID_PEGAWAILAPORANTANAH", idPegawaiLaporanTanah);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_JAWATAN", txtJawatan);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idPegawaiLaporanTanah
							+ "] DIKEMASKINI");

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

	public void hapusMaklumatKehadiran(String idPegawaiLaporanTanah, HttpSession session)
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
					"FAIL [" + idPegawaiLaporanTanah
							+ "] DIHAPUSKAN");

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
	
	public void updatePelapor(String idLaporanTanah, String nama,
			String jawatanPelapor, String idNegeri, String namaPenyemak,
			String jawatanPenyemak, String idNegeriPenyemak,
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

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporanTanah);
			r.add("NAMA_PELAPOR", nama);
			r.add("NAMA_JAWATAN_PELAPOR", jawatanPelapor);
			r.add("ID_NEGERIPELAPOR", idNegeri);
			r.add("NAMA_PENYEMAK", namaPenyemak);
			r.add("NAMA_JAWATAN_PENYEMAK", jawatanPenyemak);
			r.add("ID_NEGERIPENYEMAK", idNegeriPenyemak);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idLaporanTanah
							+ "] DIKEMASKINI");

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
	
	public void setMaklumatImej(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaFail", rs.getString("NAMA_FAIL") == null ? ""
						: rs.getString("NAMA_FAIL"));
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
	
	public void setSenaraiImejan(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE FLAG_DOKUMEN = 'I' AND ID_LAPORANTANAH = '" + idLaporanTanah + "'";


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
			
			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL [" + idDokumen
							+ "] DIKEMASKINI");

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

	public void hapusDokumen(String idDokumen, HttpSession session) throws Exception {
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
					"FAIL [" + idDokumen
							+ "] DIHAPUSKAN");

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
	
	public void setMaklumatLampiran(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaFail", rs.getString("NAMA_FAIL") == null ? ""
						: rs.getString("NAMA_FAIL"));
				h.put("namaLampiran", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanLampiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatLampiran.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiLampiran(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE FLAG_DOKUMEN = 'L' AND ID_LAPORANTANAH = '" + idLaporanTanah + "'";

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
				listLampiran.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateStatus(String idFail, String idPermohonan,
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

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610199"); // JABATAN TEKNIKAL

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("56", "1610199")); // JABATAN TEKNIKAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIKEMASKINI");

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
				return (String) rs.getString("ID_SUBURUSANSTATUS");
			} else {
				return "";
			}

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
	
	public void gotoHantarHQ(String idFail, String idNegeriUser,
			String idPermohonan, HttpSession session) throws Exception {
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
			r.add("ID_NEGERI", "16"); // HQ
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPengarahHQ");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);
			
			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TARIKH_HANTAR_HQ", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "INS",
					"FAIL [" + idFail
							+ "] DIHANTAR KEPADA HQ");

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
	
	public void gotoHantarTugasanPP(String idFail, String idNegeriUser,
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
			r.add("ID_NEGERI", "16"); // HQ
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", "(PHP)PYWPenolongPengarahHQ");
			r.add("FLAG_BUKA", "T");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "UPD",
					"FAIL [" + idFail
							+ "] DIHANTAR KEPADA PP HQ");

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
	
	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}

	public Vector getBeanMaklumatKJT() {
		return beanMaklumatKJT;
	}

	public void setBeanMaklumatKJT(Vector beanMaklumatKJT) {
		this.beanMaklumatKJT = beanMaklumatKJT;
	}

	public Vector getListKJT() {
		return listKJT;
	}

	public void setListKJT(Vector listKJT) {
		this.listKJT = listKJT;
	}

	public Vector getListLawatanTapak() {
		return listLawatanTapak;
	}

	public void setListLawatanTapak(Vector listLawatanTapak) {
		this.listLawatanTapak = listLawatanTapak;
	}

	public Vector getBeanMaklumatLaporanTanah() {
		return beanMaklumatLaporanTanah;
	}

	public void setBeanMaklumatLaporanTanah(Vector beanMaklumatLaporanTanah) {
		this.beanMaklumatLaporanTanah = beanMaklumatLaporanTanah;
	}

	public Vector getListPenceroboh() {
		return listPenceroboh;
	}

	public void setListPenceroboh(Vector listPenceroboh) {
		this.listPenceroboh = listPenceroboh;
	}

	public Vector getBeanMaklumatPenceroboh() {
		return beanMaklumatPenceroboh;
	}

	public void setBeanMaklumatPenceroboh(Vector beanMaklumatPenceroboh) {
		this.beanMaklumatPenceroboh = beanMaklumatPenceroboh;
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

	public Vector getBeanMaklumatLampiran() {
		return beanMaklumatLampiran;
	}

	public void setBeanMaklumatLampiran(Vector beanMaklumatLampiran) {
		this.beanMaklumatLampiran = beanMaklumatLampiran;
	}

	public Vector getListLampiran() {
		return listLampiran;
	}

	public void setListLampiran(Vector listLampiran) {
		this.listLampiran = listLampiran;
	}
}
