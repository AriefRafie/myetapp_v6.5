/**
 * 
 */
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
import ekptg.helpers.Utils;
import ekptg.view.php2.util.UtilHasil;

/**
 * @author mohd faizal
 * 
 */
public class FrmRevKutipanDataSewaData {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatPerjanjian = null;
	private Vector beanMaklumatTanah = null;

	public String daftarBaru(String noFail, String idUrusan,
			String idSuburusan, String perkara, String tujuan, String nama,
			String noPendaftaran, String alamat1, String alamat2,
			String alamat3, String poskod, String idBandar, String idNegeri,
			String emel, String noTel, String noFaks, String noSiri,
			String txtTarikhMula, String tempoh, String txtTarikhTamat, String flagKelulusanDasar,
			String kadarSewa, String cagaran, String modCajSewaan, String catatan, String idNegeriTanah,
			String idKementerian, String idAgensi, String maklumatLot,
			String idLuas, String luas, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idFailString = "";

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
			r.add("ID_URUSAN", "115"); // HASIL
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", perkara);
			r.add("NO_FAIL", noFail);
			r.add("NO_FAIL_ROOT", noFail);
			r.add("ID_LOKASIFAIL", "2"); // UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "3"); // DATA KUTIPAN ETAPP
			r.add("ID_NEGERI", idNegeriTanah);
			r.add("ID_KEMENTERIAN", idKementerian);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("NAMA", nama);
			r.add("NO_PENGENALAN", noPendaftaran);
			r.add("ALAMAT1_TETAP", alamat1);
			r.add("ALAMAT2_TETAP", alamat2);
			r.add("ALAMAT3_TETAP", alamat3);
			r.add("POSKOD_TETAP", poskod);
			r.add("ID_BANDARTETAP", idBandar);
			r.add("ID_NEGERITETAP", idNegeri);
			r.add("NO_TEL", noTel);
			r.add("NO_FAX", noFaks);
			r.add("EMEL", emel);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			// TBLPHPHASIL
			r = new SQLRenderer();
			long idHasil = DB.getNextID("TBLPHPHASIL_SEQ");
			r.add("ID_HASIL", idHasil);
			r.add("ID_FAIL", idFail);
			r.add("ID_PEMOHON", idPemohon);
			r.add("TUJUAN", tujuan);
			r.add("ID_AGENSI", idAgensi);
			r.add("MAKLUMAT_LOT", maklumatLot);
			r.add("ID_LUAS", idLuas);
			r.add("LUAS", luas);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPHASIL");
			stmt.executeUpdate(sql);

			// TBLPHPBAYARANPERLUDIBAYAR
			r = new SQLRenderer();
			long idBayaranPerluDibayar = DB.getNextID("TBLPHPBAYARANPERLUDIBAYAR_SEQ");
			r.add("ID_BAYARANPERLUDIBAYAR", idBayaranPerluDibayar);
			r.add("ID_HASIL", idHasil);
			r.add("ID_FAIL", idFail);
			if (noSiri != null && noSiri.length() > 0)
				r.add("NO_RUJUKAN", noSiri);

			if (!"".equals(txtTarikhMula)) {
				r.add("TARIKH_MULA",
						r.unquote("to_date('" + txtTarikhMula
								+ "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH", tempoh);
			if (!"".equals(txtTarikhTamat)) {
				r.add("TARIKH_TAMAT",
						r.unquote("to_date('" + txtTarikhTamat
								+ "','dd/MM/yyyy')"));
			}

			r.add("BAYARAN", Utils.RemoveComma(kadarSewa));
			r.add("DEPOSIT", Utils.RemoveComma(cagaran));
			r.add("FLAG_AKTIF", "Y");
			r.add("STATUS", "1");
			r.add("FLAG_LULUSDASAR", flagKelulusanDasar);
			r.add("FLAG_PERJANJIAN", "U");
			r.add("CATATAN", catatan);
			r.add("MOD_CAJ_SEWAAN", modCajSewaan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPBAYARANPERLUDIBAYAR");
			stmt.executeUpdate(sql);
			conn.commit();
			
			UtilHasil.kemaskiniRekodPerjanjianDanAkaun(String.valueOf(idHasil));

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
		session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
	}

	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FAIL.ID_FAIL, HASIL.ID_HASIL, FAIL.NO_FAIL, SUBURUSAN.ID_URUSAN, FAIL.ID_SUBURUSAN, FAIL.TAJUK_FAIL, HASIL.TUJUAN"
					+ " FROM TBLPFDFAIL FAIL, TBLPHPHASIL HASIL, TBLRUJSUBURUSAN SUBURUSAN"
					+ " WHERE FAIL.ID_FAIL = HASIL.ID_FAIL AND FAIL.ID_SUBURUSAN = SUBURUSAN.ID_SUBURUSAN(+)"
					+ " AND FAIL.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idHasil",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("idUrusan",
						rs.getString("ID_URUSAN") == null ? "" : rs
								.getString("ID_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("tujuan", rs.getString("TUJUAN") == null ? "" : rs
						.getString("TUJUAN").toUpperCase());
				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

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

			sql = "SELECT PEMOHON.ID_PEMOHON, PEMOHON.NAMA, PEMOHON.NO_PENGENALAN, PEMOHON.ALAMAT1_TETAP, PEMOHON.ALAMAT2_TETAP, PEMOHON.ALAMAT3_TETAP,"
					+ " PEMOHON.POSKOD_TETAP, PEMOHON.ID_BANDARTETAP, PEMOHON.ID_NEGERITETAP, PEMOHON.NO_TEL, PEMOHON.NO_FAX, PEMOHON.EMEL"
					+ " FROM TBLPFDFAIL FAIL, TBLPHPHASIL HASIL, TBLPHPPEMOHON PEMOHON"
					+ " WHERE FAIL.ID_FAIL = HASIL.ID_FAIL AND HASIL.ID_PEMOHON = PEMOHON.ID_PEMOHON AND FAIL.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("idPemohon",
						rs.getString("ID_PEMOHON") == null ? "" : rs
								.getString("ID_PEMOHON"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("noPendaftaran",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskod",
						rs.getString("POSKOD_TETAP") == null ? "" : rs
								.getString("POSKOD_TETAP"));
				h.put("idNegeri", rs.getString("ID_NEGERITETAP") == null ? ""
						: rs.getString("ID_NEGERITETAP"));
				h.put("idBandar", rs.getString("ID_BANDARTETAP") == null ? ""
						: rs.getString("ID_BANDARTETAP"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("noFaks",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));

				beanMaklumatPemohon.addElement(h);
				bil++;

			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPerjanjian(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPerjanjian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_RUJUKAN, TARIKH_MULA, TEMPOH, TARIKH_TAMAT, FLAG_LULUSDASAR, BAYARAN, DEPOSIT, MOD_CAJ_SEWAAN, CATATAN FROM TBLPHPBAYARANPERLUDIBAYAR"
					+ " WHERE ID_FAIL = '" + idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("noSiri",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MULA")));
				h.put("tempoh",
						rs.getString("TEMPOH") == null ? "" : rs
								.getString("TEMPOH"));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT")));
				h.put("flagKelulusanDasar",
						rs.getString("FLAG_LULUSDASAR") == null ? "" : rs
								.getString("FLAG_LULUSDASAR"));
				h.put("kadarSewa",
						rs.getString("BAYARAN") == null ? "" : Utils
								.format2Decimal(rs.getDouble("BAYARAN")));
				h.put("cagaran",
						rs.getString("DEPOSIT") == null ? "" : Utils
								.format2Decimal(rs.getDouble("DEPOSIT")));
				h.put("modCajSewaan",
						rs.getString("MOD_CAJ_SEWAAN") == null ? "" : rs
								.getString("MOD_CAJ_SEWAAN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatPerjanjian.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkingExistNoFail(String noFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_SEKSYEN = '4' AND ID_URUSAN = '115' AND UPPER(NO_FAIL) = UPPER('"
					+ noFail + "')";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkingExistNoFailUpdate(String noFail, String idFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_SEKSYEN = '4' AND ID_URUSAN = '115' AND UPPER(NO_FAIL) = UPPER('"
					+ noFail + "') AND ID_FAIL != '" + idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTanah(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FAIL.ID_NEGERI, FAIL.ID_KEMENTERIAN, HASIL.ID_AGENSI, HASIL.MAKLUMAT_LOT, HASIL.ID_LUAS, HASIL.LUAS"
					+ " FROM TBLPFDFAIL FAIL, TBLPHPHASIL HASIL, TBLPHPPEMOHON PEMOHON"
					+ " WHERE FAIL.ID_FAIL = HASIL.ID_FAIL AND HASIL.ID_PEMOHON = PEMOHON.ID_PEMOHON AND FAIL.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("maklumatLot", rs.getString("MAKLUMAT_LOT") == null ? ""
						: rs.getString("MAKLUMAT_LOT"));
				h.put("idLuas",
						rs.getString("ID_LUAS") == null ? "" : rs
								.getString("ID_LUAS"));
				h.put("luas",
						rs.getString("LUAS") == null ? "" : rs
								.getString("LUAS"));

				beanMaklumatTanah.addElement(h);
				bil++;

			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getBeanMaklumatPerjanjian() {
		return beanMaklumatPerjanjian;
	}

	public void setBeanMaklumatPerjanjian(Vector beanMaklumatPerjanjian) {
		this.beanMaklumatPerjanjian = beanMaklumatPerjanjian;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}
}
