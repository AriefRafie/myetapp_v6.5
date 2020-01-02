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
import ekptg.helpers.Utils;

public class FrmPLPMesyuaratData {

	private Vector listMesyuarat = null;
	private Vector beanMaklumatMesyuarat = null;
	private Vector beanMaklumatPampasan = null;
	private Vector beanMaklumatDokumen = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setSenaraiMesyuarat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_MESYUARAT");
			r.add("TAJUK");
			r.add("BIL_MESYUARAT");
			r.add("TARIKH_MESYUARAT");
			r.add("FLAG_SYOR");
			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPMESYUARAT", "ID_MESYUARAT ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idMesyuarat", rs.getString("ID_MESYUARAT") == null ? ""
						: rs.getString("ID_MESYUARAT"));
				h.put("tajukMesyuarat",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("bilMesyuarat",
						rs.getString("BIL_MESYUARAT") == null ? "" : rs
								.getString("BIL_MESYUARAT"));
				h.put("tarikhMesyuarat",
						rs.getDate("TARIKH_MESYUARAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MESYUARAT")));
				if (rs.getString("BIL_MESYUARAT") != null) {
					if ("L".equals(rs.getString("FLAG_SYOR"))) {
						h.put("syor", "LULUS");
					} else if ("T".equals(rs.getString("FLAG_SYOR"))) {
						h.put("syor", "TOLAK");
					} else if ("G".equals(rs.getString("FLAG_SYOR"))) {
						h.put("syor", "LULUS BERSYARAT");
					} else {
						h.put("syor", "");
					}
				} else {
					h.put("syor", "");
				}

				listMesyuarat.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanMesyuarat(String idPermohonan,
			String txtTajukMesyuarat, String txtBilMesyuarat,
			String txtTarikhMesyuarat, String idJamDari, String idMinitDari,
			String idJamHingga, String idMinitHingga, String idLokasi,
			String socSyor, String txtCatatan, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idMesyuaratString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMESYUARAT
			long idMesyuarat = DB.getNextID("TBLPHPMESYUARAT_SEQ");
			idMesyuaratString = String.valueOf(idMesyuarat);
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("TAJUK", txtTajukMesyuarat);
			r.add("BIL_MESYUARAT", txtBilMesyuarat);
			if (!"".equals(txtTarikhMesyuarat)) {
				r.add("TARIKH_MESYUARAT",
						r.unquote("to_date('" + txtTarikhMesyuarat
								+ "','dd/MM/yyyy')"));
			}
			r.add("JAM_DARI", idJamDari);
			r.add("MINIT_DARI", idMinitDari);
			r.add("JAM_HINGGA", idJamHingga);
			r.add("MINIT_HINGGA", idMinitHingga);
			r.add("ID_LOKASI", idLokasi);
			r.add("CATATAN", txtCatatan);
			r.add("FLAG_SYOR", socSyor);
			r.add("FLAG_MESYUARAT", "1");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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
		return idMesyuaratString;
	}

	public void simpanKemaskiniMesyuarat(String idMesyuarat,
			String txtTajukMesyuarat, String txtBilMesyuarat,
			String txtTarikhMesyuarat, String idJamDari, String idMinitDari,
			String idJamHingga, String idMinitHingga, String idLokasi,
			String socSyor, String txtCatatan, String socKeputusanPemohon, String txtUlasanPemohon, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idMesyuaratString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMESYUARAT
			r.update("ID_MESYUARAT", idMesyuarat);
			r.add("TAJUK", txtTajukMesyuarat);
			r.add("BIL_MESYUARAT", txtBilMesyuarat);
			if (!"".equals(txtTarikhMesyuarat)) {
				r.add("TARIKH_MESYUARAT",
						r.unquote("to_date('" + txtTarikhMesyuarat
								+ "','dd/MM/yyyy')"));
			}
			r.add("JAM_DARI", idJamDari);
			r.add("MINIT_DARI", idMinitDari);
			r.add("JAM_HINGGA", idJamHingga);
			r.add("MINIT_HINGGA", idMinitHingga);
			r.add("ID_LOKASI", idLokasi);
			r.add("CATATAN", txtCatatan);
			r.add("FLAG_SYOR", socSyor);
			r.add("FLAG_KEPUTUSAN_PEMOHON", socKeputusanPemohon);
			r.add("ULASAN_PEMOHON", txtUlasanPemohon);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idMesyuarat
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

	public void hapusMesyuarat(String idMesyuarat, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMESYUARAT
			r = new SQLRenderer();
			r.add("ID_MESYUARAT", idMesyuarat);

			sql = r.getSQLDelete("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "DEL",
					"FAIL PELEPASAN [" + idMesyuarat
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
			r.add("ID_STATUS", "1610202"); // CETAKAN MINIT KEWANGAN

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610202")); // CETAKAN
																				// MINIT
																				// KEWANGAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// TODO
			// //PERPARE DATA FOR KERTAS MINIT KEWANGAN
			//
			// String Yb = "";
			// String flagGuna = "";
			// String kegunaanTanah = "";
			// String lot = "";
			// String noHak = "";
			// String mukimTanah = "";
			// String daerahTanah = "";
			// String negeriTanah = "";
			// String namaProjek = "";
			// String nilaian = "";
			// String nilaianWords = "";
			// String tarikhSurat = "";
			// String luasMohonBersamaan = "";
			// String pemohon= "";
			// String kemajuanTanah ="";
			//
			// sql = "SELECT "
			// +"TBLPFDFAIL.ID_FAIL, "
			// +"CASE "
			// +"WHEN TBLPHPPERMOHONANPELEPASAN.FLAG_GUNA = '1' THEN 'keseluruhan' "
			// +"WHEN TBLPHPPERMOHONANPELEPASAN.FLAG_GUNA = '2' THEN 'sebahagian' "
			// +"END AS FLAG_GUNA, "
			// +"TBLPHPPERMOHONANPELEPASAN.NAMA_PROJEK, "
			// +"TBLPHPPERMOHONANPELEPASAN.KEMAJUAN_TANAH, "
			// +"TBLPHPHAKMILIKPERMOHONAN.NILAIAN, "
			// +"INITCAP(TBLPHPPEMOHON.NAMA) AS NAMA_PEMOHON, "
			// +"TO_CHAR(TBLPERMOHONAN.TARIKH_SURAT,'DD/MM/YYYY') AS TARIKH_SURAT, "
			// +"REPLACE(INITCAP(REPLACE(TRIM(DAERAHHAKMILIK.NAMA_DAERAH),'&','&#38;')),',')  AS DAERAH_HAKMILIK, "
			// +"REPLACE(REPLACE(INITCAP(REPLACE(TRIM(NEGERIHAKMILIK.NAMA_NEGERI),'&','&#38;')),','),'Negeri','') AS NEGERI_HAKMILIK, "
			// +"REPLACE(INITCAP(REPLACE(TRIM(TBLRUJMUKIM.NAMA_MUKIM),'&','&#38;')),',')  AS MUKIM_HAKMILIK, "
			// +"CASE  "
			// +"WHEN SUBSTR(ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4),1,1) = '.' THEN '0'|| ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4) "
			// +"WHEN SUBSTR(ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4),1,1) != '.' THEN '' || ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4) "
			// +"END AS LUAS_MHN, "
			// +"CASE  "
			// +"WHEN SUBSTR(ROUND(TBLHTPHAKMILIK.LUAS_BERSAMAAN,4),1,1) = '.' THEN '0'|| ROUND(TBLHTPHAKMILIK.LUAS_BERSAMAAN,4) "
			// +"WHEN SUBSTR(ROUND(TBLHTPHAKMILIK.LUAS_BERSAMAAN,4),1,1) != '.' THEN '' || ROUND(TBLHTPHAKMILIK.LUAS_BERSAMAAN,4) "
			// +"END AS LUAS_BERSAMAAN, "
			// +"CASE "
			// +"WHEN TBLHTPHAKMILIK.ID_LOT = '1' THEN 'No. Lot' "
			// +"WHEN TBLHTPHAKMILIK.ID_LOT = '3' THEN 'No. PT' "
			// +"END AS JENIS_LOT, "
			// +"INITCAP(TBLRUJLOT.KETERANGAN) || ' '||  TBLHTPHAKMILIK.NO_LOT AS LOT, "
			// +"CASE "
			// +"WHEN TBLHTPHAKMILIK.NO_WARTA IS NULL THEN TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK ||' '||TBLHTPHAKMILIK.NO_HAKMILIK  "
			// +"WHEN TBLHTPHAKMILIK.NO_HAKMILIK IS NULL THEN TBLHTPHAKMILIK.NO_WARTA  "
			// +"END AS NO_HAK, "
			// +"CASE "
			// +"WHEN TBLHTPHAKMILIK.NO_WARTA IS NULL THEN 'No. Hakmilik' "
			// +"WHEN TBLHTPHAKMILIK.NO_HAKMILIK IS NULL THEN 'No. Rizab' "
			// +"END AS JENIS_TANAH, "
			// +"INITCAP(TBLHTPHAKMILIK.KEGUNAAN_TANAH) AS KEGUNAAN_TANAH, "
			// +"INITCAP(TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN) AS KJP_TANAH, "
			// +"INITCAP(REPLACE(TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN,'KEMENTERIAN','')) AS ULASAN_MENTERI "
			// +"FROM "
			// +"TBLPFDFAIL, "
			// +"TBLPERMOHONAN, "
			// +"TBLPHPPEMOHON, "
			// +"TBLPHPHAKMILIKPERMOHONAN, "
			// +"TBLPHPPERMOHONANPELEPASAN, "
			// +"TBLHTPHAKMILIKAGENSI, "
			// +"TBLHTPHAKMILIK, "
			// +"TBLPHPKERTASKERJAPELEPASAN, "
			// +"TBLRUJDAERAH DAERAHHAKMILIK, "
			// +"TBLRUJNEGERI NEGERIHAKMILIK, "
			// +"TBLRUJKEMENTERIAN, "
			// +"TBLRUJMUKIM, "
			// +"TBLRUJLOT, "
			// +"TBLRUJJENISHAKMILIK "
			// +"WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "
			// +"AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN "
			// +"AND TBLPERMOHONAN.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON "
			// +"AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKAGENSI = TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI "
			// +"AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIK = TBLHTPHAKMILIK.ID_HAKMILIK "
			// +"AND TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN = TBLRUJKEMENTERIAN.ID_KEMENTERIAN "
			// +"AND TBLHTPHAKMILIK.ID_DAERAH = DAERAHHAKMILIK.ID_DAERAH(+) "
			// +"AND TBLHTPHAKMILIK.ID_NEGERI = NEGERIHAKMILIK.ID_NEGERI(+)  "
			// +"AND TBLHTPHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) "
			// +"AND TBLHTPHAKMILIK.ID_LOT = TBLRUJLOT.ID_LOT(+) "
			// +"AND TBLHTPHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) "
			// +"AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPPERMOHONANPELEPASAN.ID_PERMOHONAN "
			// +"AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPKERTASKERJAPELEPASAN.ID_PERMOHONAN "
			// +"AND TBLPHPKERTASKERJAPELEPASAN.FLAG_KERTAS = 1 "
			// +"AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan + "'";
			//
			// ResultSet rsKertasCadangan = stmt.executeQuery(sql);
			//
			// if (rsKertasCadangan.next()){
			// Yb = rsKertasCadangan.getString("ULASAN_MENTERI") == null ? "" :
			// rsKertasCadangan.getString("ULASAN_MENTERI");
			// flagGuna = rsKertasCadangan.getString("FLAG_GUNA") == null ? "" :
			// rsKertasCadangan.getString("FLAG_GUNA");
			// kegunaanTanah = rsKertasCadangan.getString("KEGUNAAN_TANAH") ==
			// null ? "" : rsKertasCadangan.getString("KEGUNAAN_TANAH");
			// lot = rsKertasCadangan.getString("LOT") == null ? "" :
			// rsKertasCadangan.getString("LOT");
			// noHak = rsKertasCadangan.getString("NO_HAK") == null ? "" :
			// rsKertasCadangan.getString("NO_HAK");
			// mukimTanah = rsKertasCadangan.getString("MUKIM_HAKMILIK") == null
			// ? "" : rsKertasCadangan.getString("MUKIM_HAKMILIK");
			// daerahTanah = rsKertasCadangan.getString("DAERAH_HAKMILIK") ==
			// null ? "" : rsKertasCadangan.getString("DAERAH_HAKMILIK");
			// negeriTanah = rsKertasCadangan.getString("NEGERI_HAKMILIK") ==
			// null ? "" : rsKertasCadangan.getString("NEGERI_HAKMILIK");
			// namaProjek = rsKertasCadangan.getString("NAMA_PROJEK") == null ?
			// "" : rsKertasCadangan.getString("NAMA_PROJEK");
			// tarikhSurat = rsKertasCadangan.getString("TARIKH_SURAT") == null
			// ? "" : rsKertasCadangan.getString("TARIKH_SURAT");
			// luasMohonBersamaan = rsKertasCadangan.getString("LUAS_MHN") ==
			// null ? "" : rsKertasCadangan.getString("LUAS_MHN");
			// pemohon = rsKertasCadangan.getString("NAMA_PEMOHON") == null ? ""
			// : rsKertasCadangan.getString("NAMA_PEMOHON");
			// kemajuanTanah = rsKertasCadangan.getString("KEMAJUAN_TANAH") ==
			// null ? "" : rsKertasCadangan.getString("KEMAJUAN_TANAH");
			// nilaian = (rsKertasCadangan.getString("NILAIAN") == null ? "0.00"
			// : rsKertasCadangan.getString("NILAIAN"));
			//
			// }
			// nilaianWords = NumberToWords.convertTwoPart(nilaian);
			// String tujuan =
			// "Kertas cadangan ini bertujuan untuk memohon persetujuan Y.B Menteri Kewangan Malaysia ke atas permohonan penyerahan balik "+flagGuna+" tanah milik Persekutuan tapak kegunaan "+kegunaanTanah+" di atas "+lot+", "+noHak+", "+mukimTanah+", Daerah "+daerahTanah+", Negeri "+negeriTanah+" kepada "+pemohon+" bagi tujuan "+namaProjek+".";
			// String latarBelakangTanah =
			// pemohon+" telah mengemukakan cadangan untuk mendapatkan tanah milik Persekutuan "+lot+", "+noHak+", "+mukimTanah+", Daerah "+daerahTanah+", Negeri "+negeriTanah+" seluas "+luasMohonBersamaan+" hektar bagi tujuan "+namaProjek+". Sesalinan hakmilik seperti di LAMPIRAN 'A'.";
			// String laporanNilaian =
			// "Jabatan Penilaian dan Perkhidmatan Harta Negeri "+negeriTanah+", melalui suratnya di LAMPIRAN 'B' memaklumkan nilaian bagi "+lot+", "+noHak+", "+mukimTanah+", Daerah "+daerahTanah+", Negeri "+negeriTanah+" memaklumkan bahawa nilaian bagi keluasan "+luasMohonBersamaan+" hektar dengan jumlah sebanyak RM "+nilaian+" (RINGGIT MALAYSIA : "+nilaianWords+").";
			// String pemohonSerahBalik =
			// "Permohonan daripada "+pemohon+" yang bertarikh "+tarikhSurat+" telah mengemukakan permohonan kepada Jabatan Ketua Pengarah Tanah dan Galian Persekutuan Putrajaya seperti sesalinan suratnya di LAMPIRAN 'D', mengenai penyerahan balik "+flagGuna+" tanah milik persekutuan di atas "+lot+", "+noHak+", "+mukimTanah+", Daerah "+daerahTanah+", Negeri "+negeriTanah+" bagi tujuan "+namaProjek+".";
			// String perakuanPTP =
			// "Pesuruhjaya Tanah Persekutuan mengangkat permohonan penyerahan balik "+flagGuna+" tanah milik Persekutuan bagi "+lot+", "+noHak+", "+mukimTanah+", Daerah "+daerahTanah+", Negeri "+negeriTanah+" ("+kegunaanTanah+") kepada "+pemohon+" bagi tujuan "+namaProjek+" untuk mendapat persetujuan Y.B Menteri"+Yb+" Malaysia.";
			// String perihalKemajuanTanah = kemajuanTanah;

			// TBLPHPKERTASKERJAPELEPASAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "2");
			// r.add("TUJUAN",tujuan);
			// r.add("PERIHAL_KEMAJUANTANAH",perihalKemajuanTanah);
			// r.add("LATAR_BELAKANGTANAH",latarBelakangTanah);
			// r.add("LAPORAN_NILAIAN",laporanNilaian);
			// r.add("PEMOHON",pemohonSerahBalik);
			// r.add("PERAKUAN_PTP",perakuanPTP);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610202", "4", null, session, "UPD",
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

	public void setMaklumatMesyuarat(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TAJUK, BIL_MESYUARAT, TARIKH_MESYUARAT, JAM_DARI, MINIT_DARI, JAM_HINGGA, MINIT_HINGGA, ID_LOKASI, CATATAN, FLAG_SYOR,"
					+ " ULASAN_PEMOHON, FLAG_KEPUTUSAN_PEMOHON"
					+ " FROM TBLPHPMESYUARAT WHERE ID_MESYUARAT = '" + idMesyuarat + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tajukMesyuarat",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("bilMesyuarat",
						rs.getString("BIL_MESYUARAT") == null ? "" : rs
								.getString("BIL_MESYUARAT"));
				h.put("tarikhMesyuarat",
						rs.getDate("TARIKH_MESYUARAT") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MESYUARAT")));
				h.put("idLokasi", rs.getString("ID_LOKASI") == null ? "99999"
						: rs.getString("ID_LOKASI"));
				h.put("idJamDari", rs.getString("JAM_DARI") == null ? "99999"
						: rs.getString("JAM_DARI"));
				h.put("idMinitDari",
						rs.getString("MINIT_DARI") == null ? "99999" : rs
								.getString("MINIT_DARI"));
				h.put("idJamHingga",
						rs.getString("JAM_HINGGA") == null ? "99999" : rs
								.getString("JAM_HINGGA"));
				h.put("idMinitHingga",
						rs.getString("MINIT_HINGGA") == null ? "99999" : rs
								.getString("MINIT_HINGGA"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("flagSyor",
						rs.getString("FLAG_SYOR") == null ? "" : rs
								.getString("FLAG_SYOR"));
				h.put("ulasanPemohon",
						rs.getString("ULASAN_PEMOHON") == null ? "" : rs
								.getString("ULASAN_PEMOHON"));
				h.put("flagKeputusanPemohon",
						rs.getString("FLAG_KEPUTUSAN_PEMOHON") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN_PEMOHON"));
				beanMaklumatMesyuarat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanMaklumatPampasan(String idPermohonan, String socGanti,
			String txtCatatan, String txtPampasan, HttpSession session)
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

			// TBLPHPPERMOHONANPELEPASAN
			r.update("ID_PERMOHONAN", idPermohonan);

			if ("1".equals(socGanti)) {
				r.add("JENIS_PAMPASAN", socGanti);
				// r.add("PAMPASAN", Utils.RemoveSymbol(txtPampasan));
			} else if ("2".equals(socGanti) || "3".equals(socGanti)) {
				r.add("JENIS_PAMPASAN", socGanti);
				r.add("PAMPASAN", Utils.RemoveSymbol(txtPampasan));
			} else if ("4".equals(socGanti)) {
				r.add("JENIS_PAMPASAN", socGanti);
				r.add("CATATAN_PAMPASAN", txtCatatan);
			} else {
				r.add("JENIS_PAMPASAN", "");
				r.add("CATATAN_PAMPASAN", "");
				r.add("PAMPASAN", "");
			}

			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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

	public void setMaklumatPampasan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPampasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PAMPASAN, JENIS_PAMPASAN, CATATAN_PAMPASAN FROM TBLPHPPERMOHONANPELEPASAN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("pampasan",
						rs.getString("PAMPASAN") == null ? "" : Utils
								.format2Decimal(rs.getDouble("PAMPASAN")));
				h.put("jenis", rs.getString("JENIS_PAMPASAN") == null ? "0"
						: rs.getString("JENIS_PAMPASAN"));
				h.put("catatan", rs.getString("CATATAN_PAMPASAN") == null ? ""
						: rs.getString("CATATAN_PAMPASAN"));
				beanMaklumatPampasan.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusDokumen(String idMesyuarat, HttpSession session) throws Exception {
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
			r.add("ID_MESYUARAT", idMesyuarat);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"FAIL PELEPASAN [" + idMesyuarat
							+ "] DIDAFTARKAN");

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
	
	public void setMaklumatDokumen(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatDokumen = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN WHERE ID_MESYUARAT = '"
					+ idMesyuarat + "'";
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
				beanMaklumatDokumen.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListMesyuarat() {
		return listMesyuarat;
	}

	public void setListMesyuarat(Vector listMesyuarat) {
		this.listMesyuarat = listMesyuarat;
	}

	public Vector getBeanMaklumatMesyuarat() {
		return beanMaklumatMesyuarat;
	}

	public void setBeanMaklumatMesyuarat(Vector beanMaklumatMesyuarat) {
		this.beanMaklumatMesyuarat = beanMaklumatMesyuarat;
	}

	public Vector getBeanMaklumatPampasan() {
		return beanMaklumatPampasan;
	}

	public void setBeanMaklumatPampasan(Vector beanMaklumatPampasan) {
		this.beanMaklumatPampasan = beanMaklumatPampasan;
	}
	
	public Vector getBeanMaklumatDokumen() {
		return beanMaklumatDokumen;
	}

	public void setBeanMaklumatDokumen(Vector beanMaklumatDokumen) {
		this.beanMaklumatDokumen = beanMaklumatDokumen;
	}

}
