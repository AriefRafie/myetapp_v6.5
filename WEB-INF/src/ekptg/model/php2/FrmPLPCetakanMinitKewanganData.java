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
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.model.utils.emel.EmailConfig;

public class FrmPLPCetakanMinitKewanganData {

	private Vector beanKertasKewangan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatKertasKewangan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanKertasKewangan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KERTASKERJA, TAJUK, TUJUAN, PERIHAL_KEMAJUANTANAH, PEMOHON, LAPORAN_NILAIAN, ULASAN_KJP, PERAKUAN_PTP, "
					+ "TARIKH_HANTAR_KEWANGAN, JANGKAMASA, TARIKH_JANGKA_TERIMA "
					+ " FROM TBLPHPKERTASKERJAPELEPASAN WHERE FLAG_KERTAS = '2' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKertasKerja",
						rs.getString("ID_KERTASKERJA") == null ? "" : rs
								.getString("ID_KERTASKERJA"));
				h.put("txtTajukKertas",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("txtTujuan",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));
				h.put("txtPerihalKemajuan",
						rs.getString("PERIHAL_KEMAJUANTANAH") == null ? "" : rs
								.getString("PERIHAL_KEMAJUANTANAH"));
				h.put("txtPemohon",
						rs.getString("PEMOHON") == null ? "" : rs
								.getString("PEMOHON"));
				h.put("txtLaporanNilaian",
						rs.getString("LAPORAN_NILAIAN") == null ? "" : rs
								.getString("LAPORAN_NILAIAN"));
				h.put("txtUlasanKJP", rs.getString("ULASAN_KJP") == null ? ""
						: rs.getString("ULASAN_KJP"));
				h.put("txtPerakuanPTP",
						rs.getString("PERAKUAN_PTP") == null ? "" : rs
								.getString("PERAKUAN_PTP"));
				h.put("txtTarikhHantar",
						rs.getDate("TARIKH_HANTAR_KEWANGAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_HANTAR_KEWANGAN")));
				h.put("txtJangkaMasa", rs.getString("JANGKAMASA") == null ? ""
						: rs.getString("JANGKAMASA"));
				h.put("txtTarikhJangkaTerima",
						rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_JANGKA_TERIMA")));
				beanKertasKewangan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniKewangan(String idKertasKerja,
			String txtTajukKertas, String txtTujuan, String txtPerihalKemajuan,
			String txtPemohon, String txtLaporanNilaian, String txtUlasanKJP,
			String txtPerakuanPTP, String txtTarikhHantar, String txtJangkamasa,
			String txtTarikhTerima, HttpSession session)
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

			// TBLPHPKERTASKERJAPELEPASAN
			r.update("ID_KERTASKERJA", idKertasKerja);
			r.add("TAJUK", txtTajukKertas);
			r.add("TUJUAN", txtTujuan);
			r.add("PERIHAL_KEMAJUANTANAH", txtPerihalKemajuan);
			r.add("PEMOHON", txtPemohon);
			r.add("LAPORAN_NILAIAN", txtLaporanNilaian);
			r.add("ULASAN_KJP", txtUlasanKJP);
			r.add("PERAKUAN_PTP", txtPerakuanPTP);
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR_KEWANGAN",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}
			r.add("JANGKAMASA", txtJangkamasa);
			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_JANGKA_TERIMA",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610202", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idKertasKerja
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
			r.add("ID_STATUS", "1610203"); // KEMASUKAN MINIT KEWANGAN

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610203")); // KEMASUKAN
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

			conn.commit();
			
			AuditTrail.logActivity("1610203", "4", null, session, "UPD",
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
	
	public void sendEmailtoKJP(String idPermohonan, String idKementerian, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		EmailConfig conf = new EmailConfig();
		
		String sql = "";
		String emelUser = "nurulain.siprotech@gmail.com"; //untuk sementara
		String noFail = "";
		String tajukFail = "";
		String tarikhHantar = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT A.TARIKH_HANTAR_KEWANGAN, D.NO_FAIL, D.TAJUK_FAIL "
				+ "FROM TBLPHPKERTASKERJAPELEPASAN A, TBLPERMOHONAN C, TBLPFDFAIL D "
				+ "WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_FAIL = D.ID_FAIL "
				+ "AND A.FLAG_KERTAS = 2 AND C.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				noFail = rsEmel.getString("NO_FAIL");
				tajukFail = rsEmel.getString("TAJUK_FAIL");
				//tempoh = rsEmel.getString("JANGKAMASA");
				tarikhHantar = sdf.format(rsEmel.getDate("TARIKH_HANTAR_KEWANGAN"));
			}	
			
			String tajuk = "PERMOHONAN MENGEMUKAKAN ULASAN KERTAS CADANGAN URUSAN PELEPASAN BAGI NO. FAIL " + noFail;
			String kandungan = "Tuan/ Puan,"
					 		 +"<br><br><u><b>"+tajuk+"</b></u>"
							 + "Adalah saya dengan hormatnya merujuk kepada perkara di atas."
							 + "<br><br>2.	Sukacita dimaklumkan bahawa pihak kami memerlukan kelulusan "
							 + "daripada Y.A.B Menteri Kewangan Malaysia sebelum " +tarikhHantar+ "."
							 + "<br>3.	Kerjasama dari pihak tuan amatlah diharapkan dan dihargai.";
			
			conf.sendByKJPPenyedia(idKementerian, "", emelUser, tajuk, kandungan);
			//email.sendEmail();
			
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

	public Vector getBeanKertasKewangan() {
		return beanKertasKewangan;
	}

	public void setBeanKertasKewangan(Vector beanKertasKewangan) {
		this.beanKertasKewangan = beanKertasKewangan;
	}
}
