/**
 * 
 */
package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.Utils;

/**
 * 
 *
 */
public class FrmREVHeaderData {

	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatPemohon = null;
	private static final Log log = LogFactory.getLog(FrmREVHeaderData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.ID_PEMOHON");
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

			r.add("A.ID_FAIL", r.unquote("B.ID_FAIL"));
			r.add("B.ID_PEMOHON", r.unquote("C.ID_PEMOHON"));
			r.add("A.ID_FAIL", idFail);

			sql = r.getSQLSelect("TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPemohon", rs.getString("ID_PEMOHON"));
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));

				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999"
								: rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaan",
						rs.getString("PEKERJAAN") == null ? "" : rs
								.getString("PEKERJAAN"));
				h.put("idJantina", rs.getString("ID_JANTINA") == null ? "99999"
						: rs.getString("ID_JANTINA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999"
						: rs.getString("ID_BANGSA"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskod",
						rs.getString("POSKOD_TETAP") == null ? "" : rs
								.getString("POSKOD_TETAP"));
				h.put("idNegeri",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs
								.getString("ID_NEGERITETAP"));
				h.put("idBandar",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs
								.getString("ID_BANDARTETAP"));
				h.put("noTel",
						rs.getString("NO_TEL_RUMAH") == null ? "" : rs
								.getString("NO_TEL_RUMAH"));
				h.put("noTelBim", rs.getString("NO_TEL_BIMBIT") == null ? ""
						: rs.getString("NO_TEL_BIMBIT"));
				h.put("noFax",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));

				h.put("namaSykt",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("noPengenalanSykt",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaanSykt", rs.getString("PEKERJAAN") == null ? ""
						: rs.getString("PEKERJAAN"));
				h.put("alamat1Sykt", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2Sykt", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3Sykt", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskodSykt", rs.getString("POSKOD_TETAP") == null ? ""
						: rs.getString("POSKOD_TETAP"));
				h.put("idNegeriSykt",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs
								.getString("ID_NEGERITETAP"));
				h.put("idBandarSykt",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs
								.getString("ID_BANDARTETAP"));
				h.put("noTelSykt", rs.getString("NO_TEL_PEJABAT") == null ? ""
						: rs.getString("NO_TEL_PEJABAT"));
				h.put("noFaxSykt",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("emelSykt",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));

				beanMaklumatPemohon.addElement(h);
				count++;
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPermohonan(String idHasil) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;

			sql = "SELECT TBLPFDFAIL.ID_FAIL, TBLPHPHASIL.ID_HASIL, TBLPHPPEMOHON.NAMA, TBLPHPPEMOHON.ALAMAT1_TETAP, TBLPHPPEMOHON.ALAMAT2_TETAP, TBLPHPPEMOHON.ALAMAT3_TETAP, TBLPHPPEMOHON.POSKOD_TETAP,"
					+ " TBLPHPPEMOHON.NO_RUJUKAN AS NO_RUJUKAN_PENYEWA, TBLRUJBANDAR.KETERANGAN, TBLRUJNEGERI.NAMA_NEGERI, TBLPHPPEMOHON.NO_TEL, TBLPHPPEMOHON.NO_FAX,"
					+ " TBLRUJURUSAN.NAMA_URUSAN, TBLRUJSUBURUSAN.NAMA_SUBURUSAN, TBLPFDFAIL.NO_FAIL, TBLPFDFAIL.TAJUK_FAIL, TBLPHPHASIL.TUJUAN AS TUJUAN_SEWA,"
					+ " TBLPHPBAYARANPERLUDIBAYAR.NO_RUJUKAN, TBLPHPBAYARANPERLUDIBAYAR.TARIKH_MULA, TBLPHPBAYARANPERLUDIBAYAR.TARIKH_TAMAT, TBLPHPBAYARANPERLUDIBAYAR.DEPOSIT, TBLPHPBAYARANPERLUDIBAYAR.BAYARAN"

					+ " FROM TBLPHPHASIL, TBLPFDFAIL, TBLPHPPEMOHON, TBLRUJURUSAN, TBLRUJSUBURUSAN, TBLRUJBANDAR, TBLRUJNEGERI, TBLPHPBAYARANPERLUDIBAYAR"

					+ " WHERE TBLPHPHASIL.ID_FAIL = TBLPFDFAIL.ID_FAIL(+) AND TBLPHPHASIL.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON(+) AND TBLPHPHASIL.ID_HASIL = TBLPHPBAYARANPERLUDIBAYAR.ID_HASIL(+)"
					+ " AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_AKTIF = 'Y' AND TBLPHPBAYARANPERLUDIBAYAR.FLAG_PERJANJIAN = 'U' AND TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN(+)"
					+ " AND TBLRUJSUBURUSAN.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN(+) AND TBLPHPPEMOHON.ID_BANDARTETAP = TBLRUJBANDAR.ID_BANDAR(+)"
					+ " AND TBLPHPPEMOHON.ID_NEGERITETAP = TBLRUJNEGERI.ID_NEGERI(+) AND TBLPHPHASIL.ID_HASIL = '"
					+ idHasil + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idHasil",
						rs.getString("ID_HASIL") == null ? "" : rs
								.getString("ID_HASIL"));
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP").toUpperCase());
				h.put("poskod", rs.getString("POSKOD_TETAP") == null ? "" : rs
						.getString("POSKOD_TETAP").toUpperCase());
				h.put("bandar", rs.getString("KETERANGAN") == null ? "" : rs
						.getString("KETERANGAN").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("noRujukanPenyewa",
						rs.getString("NO_RUJUKAN_PENYEWA") == null ? "" : rs
								.getString("NO_RUJUKAN_PENYEWA").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX").toUpperCase());
				h.put("noFail",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("tajukFail",
						rs.getString("TAJUK_FAIL") == null ? "" : rs
								.getString("TAJUK_FAIL"));
				h.put("tujuan", rs.getString("TUJUAN_SEWA") == null ? "" : rs
						.getString("TUJUAN_SEWA").toUpperCase());
				h.put("urusan", rs.getString("NAMA_URUSAN") == null ? "" : rs
						.getString("NAMA_URUSAN").toUpperCase());
				h.put("subUrusan", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("noRujukan",
						rs.getString("NO_RUJUKAN") == null ? "" : rs
								.getString("NO_RUJUKAN"));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MULA")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TAMAT")));
				h.put("deposit",
						rs.getString("DEPOSIT") == null ? "" : Utils
								.format2Decimal(rs.getDouble("DEPOSIT")));
				h.put("sewa",
						rs.getString("BAYARAN") == null ? "" : Utils
								.format2Decimal(rs.getDouble("BAYARAN")));

				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idFail", "");
				h.put("idHasil", "");
				h.put("namaPemohon", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("bandar", "");
				h.put("negeri", "");
				h.put("noTel", "");
				h.put("noRujukanPenyewa", "");
				h.put("noFax", "");
				h.put("noFail", "");
				h.put("urusan", "");
				h.put("subUrusan", "");
				h.put("noRujukan", "");
				h.put("tarikhMula", "");
				h.put("tarikhTamat", "");
				h.put("deposit", "");

				beanMaklumatPermohonan.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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
}
