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

public class FrmPLPKemasukanMinitKewanganData {

	private Vector beanMaklumatKewangan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatMinitKewangan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKewangan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KERTASKERJA, TARIKH_TERIMA_KEWANGAN, KEPUTUSAN_KEWANGAN, ULASAN_KEWANGAN, FLAG_KEPUTUSAN_PEMOHON, ULASAN_PEMOHON "
				+ "FROM TBLPHPKERTASKERJAPELEPASAN WHERE FLAG_KERTAS = '2' AND ID_PERMOHONAN = '"
				+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKertaskerja",
						rs.getString("ID_KERTASKERJA") == null ? "" : rs
								.getString("ID_KERTASKERJA"));
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA_KEWANGAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA_KEWANGAN")));
				h.put("keputusan",
						rs.getString("KEPUTUSAN_KEWANGAN") == null ? "" : rs
								.getString("KEPUTUSAN_KEWANGAN"));
				h.put("ulasan", rs.getString("ULASAN_KEWANGAN") == null ? ""
						: rs.getString("ULASAN_KEWANGAN"));
				h.put("flagKeputusanPemohon",
						rs.getString("FLAG_KEPUTUSAN_PEMOHON") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN_PEMOHON"));
				h.put("ulasanPemohon", rs.getString("ULASAN_PEMOHON") == null ? ""
						: rs.getString("ULASAN_PEMOHON"));
				beanMaklumatKewangan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniMinitKewangan(String idPermohonan,
			String txtTarikhTerima, String socKeputusan, String txtUlasan,
			String socKeputusanPemohon, String txtUlasanPemohon, HttpSession session) throws Exception {

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

			// TBLPHPKERTASKERJAPELEPASAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "2");

			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_TERIMA_KEWANGAN",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("KEPUTUSAN_KEWANGAN", socKeputusan);
			r.add("ULASAN_KEWANGAN", txtUlasan);
			r.add("FLAG_KEPUTUSAN_PEMOHON", socKeputusanPemohon);
			r.add("ULASAN_PEMOHON", txtUlasanPemohon);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);
			conn.commit();
			
			AuditTrail.logActivity("1610203", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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
	
	public Vector getBeanMaklumatLampiran(String idKertaskerja)
			throws Exception {
		Db db = null;
		String sql = "";
		Vector beanMaklumatLampiran = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPDOKUMEN WHERE ID_ULASANTEKNIKAL = '"
					+ idKertaskerja + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				beanMaklumatLampiran = new Vector();
				h = new Hashtable();
				h.put("idDokumen",
						rs.getString("ID_DOKUMEN") == null ? "" : rs
								.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("namaFail",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				h.put("idKertaskerja",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				beanMaklumatLampiran.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

		return beanMaklumatLampiran;
	}

	public void updateStatus(String idFail, String idPermohonan,
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
			r.add("ID_STATUS", "1610204"); // CETAKAN MINIT CERAIAN

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610204")); // CETAKAN
																				// MINIT
																				// CERAIAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// PERPARE DATA FOR KERTAS MINIT CERAIAN

			String Yb = "";
			String flagGuna = "";
			String kegunaanTanah = "";
			String lot = "";
			String noHak = "";
			String mukimTanah = "";
			String daerahTanah = "";
			String negeriTanah = "";
			String namaProjek = "";
			String perihalKemajuanTanah = "";
			String ulasanKJP = "";
			String ulasanKewangan = "";
			String perakuanPTP = "";
			String laporanNilaian = "";

			sql = "SELECT "
					+ "TBLPFDFAIL.ID_FAIL, "
					+ "CASE "
					+ "WHEN TBLPHPPERMOHONANPELEPASAN.FLAG_GUNA = '1' THEN 'keseluruhan' "
					+ "WHEN TBLPHPPERMOHONANPELEPASAN.FLAG_GUNA = '2' THEN 'sebahagian' "
					+ "END AS FLAG_GUNA, "
					+ "TBLPHPPERMOHONANPELEPASAN.NAMA_PROJEK, "
					+ "TBLPHPHAKMILIKPERMOHONAN.NILAIAN, "
					+ "REPLACE(INITCAP(REPLACE(TRIM(DAERAHHAKMILIK.NAMA_DAERAH),'&','&#38;')),',')  AS DAERAH_HAKMILIK, "
					+ "REPLACE(REPLACE(INITCAP(REPLACE(TRIM(NEGERIHAKMILIK.NAMA_NEGERI),'&','&#38;')),','),'Negeri','') AS NEGERI_HAKMILIK, "
					+ "REPLACE(INITCAP(REPLACE(TRIM(TBLRUJMUKIM.NAMA_MUKIM),'&','&#38;')),',')  AS MUKIM_HAKMILIK, "
					+ "CASE  "
					+ "WHEN SUBSTR(ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4),1,1) = '.' THEN '0'|| ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4) "
					+ "WHEN SUBSTR(ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4),1,1) != '.' THEN '' || ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4) "
					+ "END AS LUAS_MHN, "
					+ "CASE "
					+ "WHEN TBLHTPHAKMILIK.ID_LOT = '1' THEN 'No. Lot' "
					+ "WHEN TBLHTPHAKMILIK.ID_LOT = '3' THEN 'No. PT' "
					+ "END AS JENIS_LOT, "
					+ "INITCAP(TBLRUJLOT.KETERANGAN) || ' '||  TBLHTPHAKMILIK.NO_LOT AS LOT, "
					+ "CASE "
					+ "WHEN TBLHTPHAKMILIK.NO_WARTA IS NULL THEN TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK ||' '||TBLHTPHAKMILIK.NO_HAKMILIK  "
					+ "WHEN TBLHTPHAKMILIK.NO_HAKMILIK IS NULL THEN TBLHTPHAKMILIK.NO_WARTA  "
					+ "END AS NO_HAK, "
					+ "CASE "
					+ "WHEN TBLHTPHAKMILIK.NO_WARTA IS NULL THEN 'No. Hakmilik' "
					+ "WHEN TBLHTPHAKMILIK.NO_HAKMILIK IS NULL THEN 'No. Rizab' "
					+ "END AS JENIS_TANAH, "
					+ "INITCAP(TBLHTPHAKMILIK.KEGUNAAN_TANAH) AS KEGUNAAN_TANAH, "
					+ "INITCAP(TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN) AS KJP_TANAH, "
					+ "INITCAP(REPLACE(TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN,'KEMENTERIAN','')) AS ULASAN_MENTERI, "
					+ "TBLPHPKERTASKERJAPELEPASAN.ULASAN_KJP, "
					+ "TBLPHPKERTASKERJAPELEPASAN.ULASAN_KEWANGAN, "
					+ "TBLPHPKERTASKERJAPELEPASAN.PERAKUAN_PTP, "
					+ "TBLPHPKERTASKERJAPELEPASAN.PERIHAL_KEMAJUANTANAH, "
					+ "TBLPHPKERTASKERJAPELEPASAN.LAPORAN_NILAIAN "
					+ "FROM "
					+ "TBLPFDFAIL, "
					+ "TBLPERMOHONAN, "
					+ "TBLPHPHAKMILIKPERMOHONAN, "
					+ "TBLPHPPERMOHONANPELEPASAN, "
					+ "TBLHTPHAKMILIKAGENSI, "
					+ "TBLHTPHAKMILIK, "
					+ "TBLPHPKERTASKERJAPELEPASAN, "
					+ "TBLRUJDAERAH DAERAHHAKMILIK, "
					+ "TBLRUJNEGERI NEGERIHAKMILIK, "
					+ "TBLRUJKEMENTERIAN, "
					+ "TBLRUJMUKIM, "
					+ "TBLRUJLOT, "
					+ "TBLRUJJENISHAKMILIK "
					+ "WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN "
					+ "AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKAGENSI = TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI "
					+ "AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIK = TBLHTPHAKMILIK.ID_HAKMILIK "
					+ "AND TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN = TBLRUJKEMENTERIAN.ID_KEMENTERIAN "
					+ "AND TBLHTPHAKMILIK.ID_DAERAH = DAERAHHAKMILIK.ID_DAERAH(+) "
					+ "AND TBLHTPHAKMILIK.ID_NEGERI = NEGERIHAKMILIK.ID_NEGERI(+)  "
					+ "AND TBLHTPHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) "
					+ "AND TBLHTPHAKMILIK.ID_LOT = TBLRUJLOT.ID_LOT(+) "
					+ "AND TBLHTPHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPPERMOHONANPELEPASAN.ID_PERMOHONAN "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPKERTASKERJAPELEPASAN.ID_PERMOHONAN "
					+ "AND TBLPHPKERTASKERJAPELEPASAN.FLAG_KERTAS = 2 "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan
					+ "'";

			ResultSet rMinitCeraian = stmt.executeQuery(sql);
			if (rMinitCeraian.next()) {
				Yb = rMinitCeraian.getString("ULASAN_MENTERI") == null ? ""
						: rMinitCeraian.getString("ULASAN_MENTERI");
				flagGuna = rMinitCeraian.getString("FLAG_GUNA") == null ? ""
						: rMinitCeraian.getString("FLAG_GUNA");
				kegunaanTanah = rMinitCeraian.getString("KEGUNAAN_TANAH") == null ? ""
						: rMinitCeraian.getString("KEGUNAAN_TANAH");
				lot = rMinitCeraian.getString("LOT") == null ? ""
						: rMinitCeraian.getString("LOT");
				noHak = rMinitCeraian.getString("NO_HAK") == null ? ""
						: rMinitCeraian.getString("NO_HAK");
				mukimTanah = rMinitCeraian.getString("MUKIM_HAKMILIK") == null ? ""
						: rMinitCeraian.getString("MUKIM_HAKMILIK");
				daerahTanah = rMinitCeraian.getString("DAERAH_HAKMILIK") == null ? ""
						: rMinitCeraian.getString("DAERAH_HAKMILIK");
				negeriTanah = rMinitCeraian.getString("NEGERI_HAKMILIK") == null ? ""
						: rMinitCeraian.getString("NEGERI_HAKMILIK");
				namaProjek = rMinitCeraian.getString("NAMA_PROJEK") == null ? ""
						: rMinitCeraian.getString("NAMA_PROJEK");
				perihalKemajuanTanah = rMinitCeraian
						.getString("PERIHAL_KEMAJUANTANAH") == null ? ""
						: rMinitCeraian.getString("PERIHAL_KEMAJUANTANAH");
				ulasanKJP = rMinitCeraian.getString("ULASAN_KJP") == null ? ""
						: rMinitCeraian.getString("ULASAN_KJP");
				ulasanKewangan = rMinitCeraian.getString("ULASAN_KEWANGAN") == null ? ""
						: rMinitCeraian.getString("ULASAN_KEWANGAN");
				perakuanPTP = rMinitCeraian.getString("PERAKUAN_PTP") == null ? ""
						: rMinitCeraian.getString("PERAKUAN_PTP");
				laporanNilaian = rMinitCeraian.getString("LAPORAN_NILAIAN") == null ? ""
						: rMinitCeraian.getString("LAPORAN_NILAIAN");

			}
			String tujuan = "Minit Ceraian ini bertujuan memohon pertimbangan dan persetujuan Y.B. Dato Seri Menteri Sumber Asli dan Alam Sekitar ke atas permohonan penyerahan balik "
					+ flagGuna
					+ " tanah milik Persekutuan "
					+ lot
					+ ", "
					+ noHak
					+ ", "
					+ mukimTanah
					+ ", Daerah "
					+ daerahTanah
					+ ", Negeri "
					+ negeriTanah
					+ " ("
					+ kegunaanTanah
					+ ") untuk " + namaProjek + ".";

			// TBLPHPKERTASKERJAPELEPASAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "3");
			r.add("TUJUAN", tujuan);
			r.add("PERIHAL_KEMAJUANTANAH", perihalKemajuanTanah);
			r.add("LAPORAN_NILAIAN", laporanNilaian);
			r.add("ULASAN_KJP", ulasanKJP);
			r.add("ULASAN_KEWANGAN", ulasanKewangan);
			r.add("PERAKUAN_PTP", perakuanPTP);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610204", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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
			String tarikhBatal, String txtSebab, HttpSession session)
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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610212")); // BATAL
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

	public Vector getBeanMaklumatKewangan() {
		return beanMaklumatKewangan;
	}

	public void setBeanMaklumatKewangan(Vector beanMaklumatKewangan) {
		this.beanMaklumatKewangan = beanMaklumatKewangan;
	}
}