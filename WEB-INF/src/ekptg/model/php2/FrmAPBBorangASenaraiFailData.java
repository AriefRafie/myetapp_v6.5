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
import lebah.util.Util;
import ekptg.helpers.DB;

public class FrmAPBBorangASenaraiFailData {

	private Vector senaraiFail = null;
	private Vector senaraiBorangA = null;
	private Vector beanPelesen = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatAmbilPasir = null;
	private Vector listBorangA = null;
	private Vector beanMaklumatBarge = null;
	private Vector senaraiBarge = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianFail(String namaPelesen, String noLesen) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_JADUALKEDUALESENAPB, A.NO_SIRI_LESEN, B.NAMA"
					+ " FROM TBLPHPJADUALKEDUALESENAPB A, TBLPHPPEMEGANG B"
					+ " WHERE A.ID_JADUALKEDUALESENAPB = B.ID_JADUALKEDUALESENAPB ";

			// namaPelesen
			if (namaPelesen != null) {
				if (!namaPelesen.trim().equals("")) {
					sql = sql + " AND UPPER(B.NAMA) LIKE '%' ||'"
							+ namaPelesen.trim().toUpperCase() + "'|| '%'";
				}
			}

			// namaPemohon
			if (noLesen != null) {
				if (!noLesen.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_SIRI_LESEN) LIKE '%' ||'"
							+ noLesen.trim().toUpperCase() + "'|| '%'";
				}
			}

			sql = sql + " ORDER BY A.ID_JADUALKEDUALESENAPB DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idJadualKedua",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? ""
								: rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("namaPelesen", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("noLesen", rs.getString("NO_SIRI_LESEN") == null ? ""
						: rs.getString("NO_SIRI_LESEN"));
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void carian(String idJadualKedua, String idBulan, String tahun)
			throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiBorangA = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_BORANGA, A.ID_JADUALKEDUALESENAPB, A.TAHUN, B.NAMA_BULAN"
					+ " FROM TBLPHPBORANGA A, TBLRUJBULAN B "
					+ " WHERE A.BULAN = B.ID_BULAN AND A.ID_JADUALKEDUALESENAPB = '"
					+ idJadualKedua + "'";

			// bulan
			if (idBulan != null && idBulan != "99999") {
				if (!idBulan.trim().equals("BULAN")) {
					sql = sql + " AND UPPER(BULAN) = '"
							+ idBulan.trim().toUpperCase() + "'";
				}
			}

			// tahun
			if (tahun != null) {
				if (!tahun.trim().equals("")) {
					sql = sql + " AND UPPER(TAHUN) = '"
							+ tahun.trim().toUpperCase() + "'";
				}
			}

			sql = sql + " ORDER BY ID_BORANGA DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idBorangA",
						rs.getString("ID_BORANGA") == null ? "" : rs
								.getString("ID_BORANGA"));
				h.put("bulan",
						rs.getString("NAMA_BULAN") == null ? "" : rs
								.getString("NAMA_BULAN"));
				h.put("tahun",
						rs.getString("TAHUN") == null ? "" : rs
								.getString("TAHUN"));
				senaraiBorangA.addElement(h);
				bil++;
			}

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
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? ""
								: rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("namaPelesen", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("noLesen", rs.getString("NO_SIRI_LESEN") == null ? ""
						: rs.getString("NO_SIRI_LESEN"));
				beanPelesen.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPermohonan(String idJadualKedua) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;

			sql = "SELECT A.ID_JADUALKEDUALESENAPB, A.NO_SIRI_LESEN, B.NAMA, B.ALAMAT1_TETAP, B.ALAMAT2_TETAP, B.ALAMAT3_TETAP, B.NO_TEL_PEJABAT"
					+ " FROM TBLPHPJADUALKEDUALESENAPB A, TBLPHPPEMEGANG B"
					+ " WHERE A.ID_JADUALKEDUALESENAPB = B.ID_JADUALKEDUALESENAPB "
					+ " AND A.ID_JADUALKEDUALESENAPB = '" + idJadualKedua + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idJadualKedua",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? ""
								: rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("noLesen", rs.getString("NO_SIRI_LESEN") == null ? ""
						: rs.getString("NO_SIRI_LESEN").toUpperCase());
				h.put("namaPelesen", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs
						.getString("NO_TEL_PEJABAT").toUpperCase());

				beanMaklumatPermohonan.addElement(h);
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
				beanMaklumatPermohonan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMaklumatAmbilPasir(String idJadualKedua,
			String idBulan, String tahun, String tujuanAmbil,
			String destinasiHantar, String jumlahPasir, String jumlahRoyalti,
			String kontraktor, String pembeli, String tarikhMula, String tarikhTamat, String laluan, 
			String kaedah, String kawasan,
			String txtLabelTitik, String txtDarjahT, String txtDarjahU, String txtMinitT, String txtMinitU, String txtSaatT, String txtSaatU,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String sql2 = "";
		String idBorangAString = "";

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
			stmt.executeUpdate(sql2);

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
		return idBorangAString;
	}

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

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idBorangA", rs.getString("ID_BORANGA") == null ? "" : rs.getString("ID_BORANGA"));
				h.put("idJadualKedua", rs.getString("ID_JADUALKEDUALESENAPB") == null ? "" : rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("tujuanAmbil", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("destinasiHantar", rs.getString("DESTINASI") == null ? "" : rs.getString("DESTINASI"));
				h.put("jumlahPasir", rs.getString("ISIPADU") == null ? "" : rs.getString("ISIPADU"));
				h.put("jumlahRoyalti", rs.getString("ANGGARAN_ROYALTI") == null ? "" : Util.formatDecimal(Double.valueOf(rs.getString("ANGGARAN_ROYALTI"))));
				h.put("bulan", rs.getString("BULAN") == null ? "" : rs.getString("BULAN"));
				h.put("tahun", rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN"));
				
				h.put("kontraktor", rs.getString("KONTRAKTOR") == null ? "" : rs.getString("KONTRAKTOR"));
				h.put("pembeli", rs.getString("PEMBELI_PASIR") == null ? "" : rs.getString("PEMBELI_PASIR"));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA_OPERASI") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA_OPERASI")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT_OPERASI") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMAT_OPERASI")));
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

	public void simpanKemaskiniMaklumatPasir(String idBorangA, String idBulan,
			String tahun, String tujuanAmbil, String destinasiHantar,
			String jumlahPasir, String jumlahRoyalti, String kontraktor, String pembeli,
			String tarikhMula, String tarikhTamat, String laluan, String kaedah, String kawasan, 
			String txtLabelTitik, String txtDarjahT, String txtDarjahU, String txtMinitT, String txtMinitU, String txtSaatT, String txtSaatU,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String sql2 = "";

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
			stmt.executeUpdate(sql2);

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

	public void carianBarge(String idBorangA) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiBarge = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_BARGE, A.ID_BORANGA, A.NAMA_BARGE"
					+ " FROM TBLPHPBARGE A, TBLPHPBORANGA B"
					+ " WHERE A.ID_BORANGA = B.ID_BORANGA AND A.ID_BORANGA = '"
					+ idBorangA + "'";

			sql = sql + " ORDER BY ID_BARGE DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idBarge",
						rs.getString("ID_BARGE") == null ? "" : rs
								.getString("ID_BARGE"));
				h.put("namaDidaftarkan",
						rs.getString("NAMA_BARGE") == null ? "" : rs.getString(
								"NAMA_BARGE").toUpperCase());
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

	public String simpanMaklumatBarge(String idBorangA, String namaDidaftarkan,
			String noPendaftaran, String kapasiti, String jenis, String noTel, HttpSession session)
			throws Exception {

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
		return idBargeString;
	}

	public void simpanKemaskiniMaklumatBarge(String idBarge,
			String namaDidaftarkan, String noPendaftaran, String kapasiti, String jenis, String noTel,
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
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector getSenaraiBorangA() {
		return senaraiBorangA;
	}

	public void setSenaraiBorangA(Vector senaraiBorangA) {
		this.senaraiBorangA = senaraiBorangA;
	}

	public Vector getSenaraiBarge() {
		return senaraiBarge;
	}

	public void setSenaraiBarge(Vector senaraiBarge) {
		this.senaraiBarge = senaraiBarge;
	}

	public Vector getBeanPelesen() {
		return beanPelesen;
	}

	public void setBeanPelesen(Vector beanPelesen) {
		this.beanPelesen = beanPelesen;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
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
}
