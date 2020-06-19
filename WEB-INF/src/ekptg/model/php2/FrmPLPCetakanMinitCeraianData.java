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

public class FrmPLPCetakanMinitCeraianData {

	private Vector beanKertasCeraian = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatKertasCeraian(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanKertasCeraian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KERTASKERJA, KEPADA, MELALUI, DARIPADA, YB, TARIKH_HANTAR_MENTERI, TAJUK, TUJUAN, PERIHAL_KEMAJUANTANAH,"
					+ " LAPORAN_NILAIAN, ULASAN_KJP, ULASAN_KEWANGAN, PERAKUAN_PTP, PERAKUAN_KSU, ULASAN_MENTERI"
					+ " FROM TBLPHPKERTASKERJAPELEPASAN WHERE FLAG_KERTAS = '3' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKertasKerja",
						rs.getString("ID_KERTASKERJA") == null ? "" : rs
								.getString("ID_KERTASKERJA"));
				h.put("txtKepada",
						rs.getString("KEPADA") == null ? "Y.B DATO' SERI MENTERI SUMBER ASLI DAN ALAM SEKITAR."
								: rs.getString("KEPADA"));
				h.put("txtMelalui",
						rs.getString("MELALUI") == null ? "Y.BHG DATO' KETUA SETIAUSAHA KEMENTERIAN SUMBER ASLI DAN ALAM SEKITAR"
								: rs.getString("MELALUI"));
				h.put("txtDaripada",
						rs.getString("DARIPADA") == null ? "PESURUHJAYA TANAH PERSEKUTUAN"
								: rs.getString("DARIPADA"));
				h.put("txtYangBerhormat",
						rs.getString("YB") == null ? "DATO' SERI" : rs
								.getString("YB"));
				h.put("txtTarikhHantar",
						rs.getDate("TARIKH_HANTAR_MENTERI") == null ? "" : sdf
								.format(rs.getDate("TARIKH_HANTAR_MENTERI")));
				h.put("txtTajukKertas",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("txtTujuan",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));
				h.put("txtPerihalKemajuan",
						rs.getString("PERIHAL_KEMAJUANTANAH") == null ? "" : rs
								.getString("PERIHAL_KEMAJUANTANAH"));
				h.put("txtLaporanNilaian",
						rs.getString("LAPORAN_NILAIAN") == null ? "" : rs
								.getString("LAPORAN_NILAIAN"));
				h.put("txtUlasanKJP", rs.getString("ULASAN_KJP") == null ? ""
						: rs.getString("ULASAN_KJP"));
				h.put("txtUlasanKewangan",
						rs.getString("ULASAN_KEWANGAN") == null ? "" : rs
								.getString("ULASAN_KEWANGAN"));
				h.put("txtPerakuanPTP",
						rs.getString("PERAKUAN_PTP") == null ? "" : rs
								.getString("PERAKUAN_PTP"));
				h.put("txtPerakuanKSU",
						rs.getString("PERAKUAN_KSU") == null ? "" : rs
								.getString("PERAKUAN_KSU"));
				h.put("txtKeputusanMenteri",
						rs.getString("ULASAN_MENTERI") == null ? "" : rs
								.getString("ULASAN_MENTERI"));
				beanKertasCeraian.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniCeraian(String idKertasKerja, String txtKepada,
			String txtMelalui, String txtDaripada, String txtYangBerhormat,
			String txtTajukKertas, String txtTujuan, String txtPerihalKemajuan,
			String txtLaporanNilaian, String txtUlasanKJP,
			String txtUlasanKewangan, String txtPerakuanPTP,
			String txtPerakuanKSU, String txtKeputusanMenteri,
			String txtTarikhHantar, HttpSession session) throws Exception {

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
			r.add("KEPADA", txtKepada);
			r.add("MELALUI", txtMelalui);
			r.add("DARIPADA", txtDaripada);
			r.add("YB", txtYangBerhormat);
			r.add("TAJUK", txtTajukKertas);
			r.add("TUJUAN", txtTujuan);
			r.add("PERIHAL_KEMAJUANTANAH", txtPerihalKemajuan);
			r.add("LAPORAN_NILAIAN", txtLaporanNilaian);
			r.add("ULASAN_KJP", txtUlasanKJP);
			r.add("ULASAN_KEWANGAN", txtUlasanKewangan);
			r.add("PERAKUAN_PTP", txtPerakuanPTP);
			r.add("PERAKUAN_KSU", txtPerakuanKSU);
			r.add("ULASAN_MENTERI", txtKeputusanMenteri);
			if (!"".equals(txtTarikhHantar)) {
				r.add("TARIKH_HANTAR_MENTERI",
						r.unquote("to_date('" + txtTarikhHantar
								+ "','dd/MM/yyyy')"));
			}

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610204", "4", null, session, "UPD",
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
			r.add("ID_STATUS", "1610205"); // KELULUSAN MENTERI

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610205")); // KELULUSAN
																				// MENTERI
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610205", "4", null, session, "UPD",
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
		EmailConfig conf = new EmailConfig();
		
		String sql = "";
		String emelUser = "nurulain.siprotech@gmail.com"; //untuk sementara
		String noFail = "";
		String tarikhAkhir = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT D.NO_FAIL, A.TARIKH_HANTAR_MENTERI "
				+ " FROM TBLPHPKERTASKERJAPELEPASAN A, TBLPERMOHONAN C, TBLPFDFAIL D "
				+ " WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.FLAG_KERTAS = 3 "
				+ " AND C.ID_FAIL = D.ID_FAIL AND C.ID_PERMOHONAN = '"+idPermohonan+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				noFail = rsEmel.getString("NO_FAIL");
				tarikhAkhir = rsEmel.getString("TARIKH_HANTAR_MENTERI");
			}	
			
			String tajuk = "PERMOHONAN ULASAN KERTAS PERTIMBANGAN URUSAN PELEPASAN BAGI NO. FAIL " + noFail;
			String kandungan = "Mohon pihak tuan memberikan ulasan dan keputusan bagi permohonan tersebut<br><br>"
							 + "Kerjasama daripada pihak tuan untuk mengemukakan keputusan tersebut kepada Jabatan ini "
							 + "sebelum " + tarikhAkhir + " amatlah dihargai."
							 + " <br><br>Sekian, terima kasih.<br><br><br>"			
							 + " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			
			conf.sendByKJPPenyedia(idKementerian, "453", emelUser, tajuk, kandungan);
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

	public Vector getBeanKertasCeraian() {
		return beanKertasCeraian;
	}

	public void setBeanKertasCeraian(Vector beanKertasCeraian) {
		this.beanKertasCeraian = beanKertasCeraian;
	}
}
