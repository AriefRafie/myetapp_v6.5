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
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

/**
 * modified by hilda
 * 
 */
public class FrmPYWJabatanTeknikalData {
	
	private Vector listKJT = null;
	private Vector beanMaklumatKJT = null;
	private Vector listKJP = null;
	private Vector beanMaklumatKJP = null;
	private Vector beanMaklumatLampiranKJP = null;
	private Vector listJPPH = null;
	private Vector beanMaklumatJPPH = null;
	private Vector listJKT = null;
	private Vector beanMaklumatJKT = null;
	private Vector listJH = null;
	private Vector beanMaklumatJH = null;
	private Vector beanMaklumatPejabat = null;
	private Vector beanMaklumatAgensi = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setSenaraiJPPH(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJPPH = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABAT B, TBLPHPRUJDOKUMEN C WHERE A.ID_PEJABAT = B.ID_PEJABAT(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JPPH' AND A.ID_PERMOHONAN = '"
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
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
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
				h.put("aktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("bilUlangan", rs.getString("BIL_ULANGAN") == null ? ""
						: rs.getString("BIL_ULANGAN"));
				listJPPH.addElement(h);
				bil++;
			}

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
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("idDokumen",
						rs.getString("ID_DOKUMEN") == null ? "" : rs
								.getString("ID_DOKUMEN"));
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
				h.put("aktif",
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

	public void setSenaraiJKT(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		String agensi = "";

		try {
			listJKT = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, D.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, D.ID_DOKUMEN, C.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN, A.ID_PEJABAT, A.ID_MENTERI "
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLRUJPEJABAT C, TBLPHPRUJDOKUMEN D WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_PEJABAT = C.ID_PEJABAT(+) AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JKT' AND A.ID_PERMOHONAN = '"
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
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				if (rs.getString("NAMA_AGENSI") != null
						&& !"".equals(rs.getString("NAMA_AGENSI"))) {
					agensi = rs.getString("NAMA_AGENSI").toUpperCase();
				} else if (rs.getString("NAMA_PEJABAT") != null
						&& !"".equals(rs.getString("NAMA_PEJABAT"))) {
					agensi = rs.getString("NAMA_PEJABAT").toUpperCase();
				}
				h.put("agensi", agensi);
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
				h.put("aktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("bilUlangan", rs.getString("BIL_ULANGAN") == null ? ""
						: rs.getString("BIL_ULANGAN"));
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));
				h.put("idMenteri",
						rs.getString("ID_MENTERI") == null ? "" : rs
								.getString("ID_MENTERI"));
				listJKT.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiJH(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listJH = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, B.NAMA_PEJABAT, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJPEJABAT B, TBLPHPRUJDOKUMEN C WHERE A.ID_PEJABAT = B.ID_PEJABAT(+) AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'JH' AND A.ID_PERMOHONAN = '"
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
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT"));
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
				h.put("aktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("bilUlangan", rs.getString("BIL_ULANGAN") == null ? ""
						: rs.getString("BIL_ULANGAN"));
				listJH.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiKJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKJP = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, C.KETERANGAN AS NAMA_DOKUMEN, B.NAMA_AGENSI, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.FLAG_STATUS, C.ID_DOKUMEN, A.FLAG_AKTIF, A.BIL_ULANGAN"
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJAGENSI B, TBLPHPRUJDOKUMEN C WHERE A.ID_AGENSI = B.ID_AGENSI(+)"
					+ " AND A.ID_DOKUMEN = C.ID_DOKUMEN(+) AND A.FLAG_KJP = 'KJP' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_ULASANTEKNIKAL DESC";
			
			System.out.println("*** setSenaraiKJP --- "+sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
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
				h.put("aktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("bilUlangan", rs.getString("BIL_ULANGAN") == null ? ""
						: rs.getString("BIL_ULANGAN"));
				listKJP.addElement(h);
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "DEL",
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

	public String simpanMaklumatKJT(String idPermohonan, String idDokumen,
			String idKementerian, String idAgensi, String idPejabat,
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
			r.add("ID_DOKUMEN", idDokumen);
			r.add("FLAG_KJP", "KJT");
			if ("1".equals(idDokumen)) {
				r.add("ID_MENTERI", idKementerian);
				r.add("ID_AGENSI", idAgensi);
			} else if ("4".equals(idDokumen)) {
				r.add("ID_NEGERI", idNegeri);
				r.add("ID_PEJABAT", idPejabat);
			}
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
			String idPermohonan, String idDokumen, String idKementerian,
			String idAgensi, String idPejabat, String idNegeri,
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
			r.add("ID_DOKUMEN", idDokumen);
			r.add("FLAG_KJP", "KJT");
			if ("1".equals(idDokumen)) {
				r.add("ID_MENTERI", idKementerian);
				r.add("ID_AGENSI", idAgensi);
			} else if ("4".equals(idDokumen)) {
				r.add("ID_NEGERI", idNegeri);
				r.add("ID_PEJABAT", idPejabat);
			}
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
			String idDokumen, String idKementerian, String idAgensi,
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
			r.add("ID_DOKUMEN", idDokumen);
			if ("1".equals(idDokumen)) {
				r.add("ID_MENTERI", idKementerian);
				r.add("ID_AGENSI", idAgensi);
				r.add("ID_NEGERI", "");
				r.add("ID_PEJABAT", "");
			} else if ("4".equals(idDokumen)) {
				r.add("ID_MENTERI", "");
				r.add("ID_AGENSI", "");
				r.add("ID_NEGERI", idNegeri);
				r.add("ID_PEJABAT", idPejabat);
			}
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
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

	public void simpanMaklumatJKT(String idPermohonan, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		long idUlasanTeknikal = 0;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPULASANTEKNIKAL - KEMENTERIAN WILAYAH PERSEKUTUSAN
			idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_DOKUMEN", "1"); // SURAT MINTA ULASAN
			r.add("FLAG_KJP", "JKT");
			r.add("ID_MENTERI", "31");
			r.add("ID_AGENSI", "1610750");
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

			// TBLPHPULASANTEKNIKAL - KEMENTERIAN KEWANGAN
			r = new SQLRenderer();
			idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_DOKUMEN", "1"); // SURAT MINTA ULASAN
			r.add("FLAG_KJP", "JKT");
			r.add("ID_MENTERI", "13");
			r.add("ID_AGENSI", "397");
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

			// TBLPHPULASANTEKNIKAL - PTG WPKL
			r = new SQLRenderer();
			idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_DOKUMEN", "1"); // SURAT MINTA ULASAN
			r.add("FLAG_KJP", "JKT");
			r.add("ID_PEJABAT", "147"); // TBLRUJPEJABAT
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

			// TBLPHPULASANTEKNIKAL - DBKL
			r = new SQLRenderer();
			idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_DOKUMEN", "1"); // SURAT MINTA ULASAN
			r.add("FLAG_KJP", "JKT");
			r.add("ID_PEJABAT", "1610748"); // TBLRUJPEJABAT
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

			// TBLPHPULASANTEKNIKAL - BAHAGIAN PENGURUSAN HARTANAH
			r = new SQLRenderer();
			idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_DOKUMEN", "1"); // SURAT MINTA ULASAN
			r.add("FLAG_KJP", "JKT");
			r.add("ID_MENTERI", "12");
			r.add("ID_AGENSI", "328");
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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
	}

	public String simpanMaklumatUlanganJKT(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idAgensi,
			String idPejabat, String txtTarikhHantar, String txtJangkaMasa,
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
			r = new SQLRenderer();
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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_KJP", "JKT");
			r.add("ID_DOKUMEN", "1");
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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

	public void simpanKemaskiniMaklumatJKT(String idUlasanTeknikal,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, String flagStatus,
			String txtTarikhTerima, String txtTarikhSurat, String txtNoRujukan,
			String txtUlasan, HttpSession session) throws Exception {

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
			;

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
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

	public String simpanMaklumatJH(String idPermohonan, String idPejabat,
			String txtTarikhHantar, String txtJangkaMasa,
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
			r.add("ID_DOKUMEN", "1"); // SURAT MINTA ULASAN
			r.add("FLAG_KJP", "JH");
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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

	public String simpanMaklumatUlanganJH(String idUlasanTeknikalLama,
			String idPermohonan, String idPejabat, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			HttpSession session) throws Exception {

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
			r.add("ID_DOKUMEN", "1"); // SURAT MINTA ULASAN
			r.add("FLAG_KJP", "JH");
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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

	public void simpanKemaskiniMaklumatJH(String idUlasanTeknikal,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, String flagStatus,
			String txtTarikhTerima, String txtTarikhSurat, String txtNoRujukan,
			String txtUlasan, HttpSession session) throws Exception {

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
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
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

	public String simpanMaklumatKJP(String idPermohonan, String idKementerian,
			String idAgensi, String txtTarikhHantar, String txtJangkaMasa,
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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "KJP");
			r.add("ID_DOKUMEN", "1");
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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

	public String simpanMaklumatUlanganKJP(String idUlasanTeknikalLama,
			String idPermohonan, String idKementerian, String idAgensi,
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
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("FLAG_KJP", "KJP");
			r.add("ID_DOKUMEN", "1");
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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

	public void simpanKemaskiniMaklumatKJP(String idUlasanTeknikal,
			String idKementerian, String idAgensi, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			String flagStatus, String txtTarikhTerima, String txtTarikhSurat,
			String txtNoRujukan, String txtUlasan, String socKeputusan,
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

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_MENTERI", idKementerian);
			r.add("ID_AGENSI", idAgensi);
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
			r.add("FLAG_KEPUTUSAN", socKeputusan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
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

	public String simpanMaklumatJPPH(String idPermohonan, String idPejabat,
			String txtTarikhHantar, String txtJangkaMasa,
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
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_KJP", "JPPH");
			r.add("ID_DOKUMEN", "1");
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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

	public String simpanMaklumatUlanganJPPH(String idUlasanTeknikalLama,
			String idPermohonan, String idPejabat, String txtTarikhHantar,
			String txtJangkaMasa, String txtTarikhJangkaTerima,
			HttpSession session) throws Exception {

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
			r.add("ID_PEJABAT", idPejabat);
			r.add("FLAG_KJP", "JPPH");
			r.add("ID_DOKUMEN", "1");
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
			
			AuditTrail.logActivity("1610199", "4", null, session, "INS",
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

	public void simpanKemaskiniMaklumatJPPH(String idUlasanTeknikal,
			String txtTarikhHantar, String txtJangkaMasa,
			String txtTarikhJangkaTerima, String flagStatus,
			String txtTarikhTerima, String txtTarikhSurat, String txtNoRujukan,
			String txtUlasan, String txtKadarSewaBulan,
			String txtKadarSewaTahun, HttpSession session) throws Exception {

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
			r.add("KADAR_SEWA_BULANAN", Utils.RemoveSymbol(txtKadarSewaBulan));
			r.add("KADAR_SEWA_TAHUNAN", Utils.RemoveSymbol(txtKadarSewaTahun));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
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
				h.put("aktif",
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

	public void setMaklumatJKT(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatJKT = new Vector();
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
				h.put("aktif",
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
				beanMaklumatJKT.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatJH(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatJH = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL, ID_DOKUMEN, ID_PEJABAT, TARIKH_HANTAR,"
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
				h.put("idDokumen", rs.getString("ID_DOKUMEN") == null ? "99999"
						: rs.getString("ID_DOKUMEN"));
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
				h.put("aktif",
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
				beanMaklumatJH.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatKJP(String idUlasanTeknikal, String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKJP = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.NAMA_KEMENTERIAN, C.NAMA_AGENSI, A.ID_ULASANTEKNIKAL, A.ID_PERMOHONAN, A.TARIKH_HANTAR, A.TARIKH_JANGKA_TERIMA, A.JANGKAMASA, A.FLAG_STATUS, A.FLAG_AKTIF, A.ID_MENTERI, A.ID_AGENSI,"
					+ " A.TARIKH_TERIMA, A.TARIKH_SURAT, A.NO_RUJUKAN, A.ULASAN, A.FLAG_KEPUTUSAN, "

					//add by aishah 08062017 - untuk dapatkan nama pegawai yang memberi ulasan
					+ " A.NAMA_PEGAWAI, A.NO_TELEFON"
					
					+ " FROM TBLPHPULASANTEKNIKAL A, TBLRUJKEMENTERIAN B, TBLRUJAGENSI C WHERE A.ID_MENTERI = B.ID_KEMENTERIAN"
					+ " AND A.ID_AGENSI = C.ID_AGENSI AND A.ID_ULASANTEKNIKAL = '"+idUlasanTeknikal+"' "
					+ " AND A.ID_PERMOHONAN = '"+idPermohonan+"' ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("agensi",
						rs.getString("NAMA_AGENSI") == null ? "" : rs
								.getString("NAMA_AGENSI"));
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("idKementerianKJP",
						rs.getString("ID_MENTERI") == null ? "" : rs
								.getString("ID_MENTERI"));
				h.put("idAgensiKJP", rs.getString("ID_AGENSI") == null ? ""
						: rs.getString("ID_AGENSI"));
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
				h.put("aktif",
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
				h.put("flagKeputusan",
						rs.getString("FLAG_KEPUTUSAN") == null ? "" : rs
								.getString("FLAG_KEPUTUSAN"));
				//add by aishah 08062017 - untuk dapatkan nama pegawai yang memberi ulasan
				h.put("namaPengulas",
						rs.getString("NAMA_PEGAWAI") == null ? "" : rs
								.getString("NAMA_PEGAWAI"));
				h.put("noTelPengulas",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				beanMaklumatKJP.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setLampiranKJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			beanMaklumatLampiranKJP = new Vector();
			Statement stmt = db.getStatement();

			sql = " SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, CONTENT, ID_PERMOHONAN, FLAG_DOKUMEN, NAMA_FAIL FROM TBLPHPDOKUMEN "
					+ " WHERE FLAG_DOKUMEN = 'L' AND ID_ULASANTEKNIKAL IS NULL AND ID_MESYUARAT IS NULL AND ID_PHPHAKMILIK IS NULL "
					+ " AND ID_PENAWARANKJP IS NULL AND ID_PERMOHONAN = '"+idPermohonan+"' ";

			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen",
						rs.getString("NAMA_DOKUMEN") == null ? "" : rs
								.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("content",
						rs.getString("CONTENT") == null ? "" : rs
								.getString("CONTENT"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("flagDokumen",
						rs.getString("FLAG_DOKUMEN") == null ? "" : rs
								.getString("FLAG_DOKUMEN"));
				h.put("namaFail",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				beanMaklumatLampiranKJP.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getBeanMaklumatLampiranKJP() {
		return beanMaklumatLampiranKJP;
	}

	public void setMaklumatJPPH(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatJPPH = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL, TARIKH_HANTAR, TARIKH_JANGKA_TERIMA, JANGKAMASA, FLAG_STATUS, FLAG_AKTIF, ID_PEJABAT,"
					+ " TARIKH_TERIMA, TARIKH_SURAT, NO_RUJUKAN, ULASAN, KADAR_SEWA_BULANAN, KADAR_SEWA_TAHUNAN"
					+ " FROM TBLPHPULASANTEKNIKAL A WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idUlasanTeknikal",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));
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
				h.put("aktif",
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
				h.put("kadarSewaBulan",
						rs.getString("KADAR_SEWA_BULANAN") == null ? "" : Utils
								.format2Decimal(rs
										.getDouble("KADAR_SEWA_BULANAN")));
				h.put("kadarSewaTahun",
						rs.getString("KADAR_SEWA_TAHUNAN") == null ? "" : Utils
								.format2Decimal(rs
										.getDouble("KADAR_SEWA_TAHUNAN")));
				beanMaklumatJPPH.addElement(h);
				bil++;
			}

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
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610200");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// PREPARE DATA FOR LAPORAN TANAH ASAL
			String flagGuna = "";
			String lot = "";
			String noHak = "";
			String mukimTanah = "";
			String daerahTanah = "";
			String negeriTanah = "";
			String tujuanKegunaan = "";
			String pemohon = "";

			sql = "SELECT "
					+ "TBLPFDFAIL.ID_FAIL, "
					+ "CASE "
					+ "WHEN TBLPHPPERMOHONANSEWA.FLAG_GUNA = '1' THEN 'keseluruhan' "
					+ "WHEN TBLPHPPERMOHONANSEWA.FLAG_GUNA = '2' THEN 'sebahagian' "
					+ "END AS FLAG_GUNA, "
					+ "LOWER(TBLPHPPERMOHONANSEWA.TUJUAN) AS TUJUAN, "
					+ "INITCAP(TBLPHPPEMOHON.NAMA) AS NAMA_PEMOHON, "
					+ "REPLACE(INITCAP(REPLACE(TRIM(DAERAHHAKMILIK.NAMA_DAERAH),'&','&#38;')),',')  AS DAERAH_HAKMILIK, "
					+ "REPLACE(REPLACE(INITCAP(REPLACE(TRIM(NEGERIHAKMILIK.NAMA_NEGERI),'&','&#38;')),','),'Negeri','') AS NEGERI_HAKMILIK, "
					+ "REPLACE(INITCAP(REPLACE(TRIM(TBLRUJMUKIM.NAMA_MUKIM),'&','&#38;')),',')  AS MUKIM_HAKMILIK, "
					+ "CASE  "
					+ "WHEN SUBSTR(ROUND(TBLPHPPERMOHONANSEWA.LUAS_MHNBERSAMAAN,4),1,1) = '.' THEN '0'|| ROUND(TBLPHPPERMOHONANSEWA.LUAS_MHNBERSAMAAN,4) "
					+ "WHEN SUBSTR(ROUND(TBLPHPPERMOHONANSEWA.LUAS_MHNBERSAMAAN,4),1,1) != '.' THEN '' || ROUND(TBLPHPPERMOHONANSEWA.LUAS_MHNBERSAMAAN,4) "
					+ "END AS LUAS_MHN, "
					+ "TBLPHPHAKMILIK.NO_LOT AS LOT, "
					+ "CASE "
					+ "WHEN TBLPHPHAKMILIK.NO_WARTA IS NULL THEN TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK ||' '||TBLPHPHAKMILIK.NO_HAKMILIK  "
					+ "WHEN TBLPHPHAKMILIK.NO_HAKMILIK IS NULL THEN TBLPHPHAKMILIK.NO_WARTA  "
					+ "END AS NO_HAK "
					+ "FROM "
					+ "TBLPFDFAIL, "
					+ "TBLPERMOHONAN, "
					+ "TBLPHPPEMOHON, "
					+ "TBLPHPHAKMILIKPERMOHONAN, "
					+ "TBLPHPPERMOHONANSEWA, "
					+ "TBLPHPHAKMILIK, "
					+ "TBLRUJDAERAH DAERAHHAKMILIK, "
					+ "TBLRUJNEGERI NEGERIHAKMILIK, "
					+ "TBLRUJKEMENTERIAN, "
					+ "TBLRUJMUKIM, "
					+ "TBLRUJJENISHAKMILIK "
					+ "WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN "
					+ "AND TBLPERMOHONAN.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON "
					+ "AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN = TBLPHPHAKMILIK.ID_HAKMILIKPERMOHONAN "
					+ "AND TBLPHPHAKMILIK.ID_KEMENTERIAN = TBLRUJKEMENTERIAN.ID_KEMENTERIAN "
					+ "AND TBLPHPHAKMILIK.ID_DAERAH = DAERAHHAKMILIK.ID_DAERAH(+) "
					+ "AND TBLPHPHAKMILIK.ID_NEGERI = NEGERIHAKMILIK.ID_NEGERI(+)  "
					+ "AND TBLPHPHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) "
					+ "AND TBLPHPHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPPERMOHONANSEWA.ID_PERMOHONAN "
					+ "AND TBLPHPHAKMILIKPERMOHONAN.FLAG_HAKMILIK = 'U' "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan
					+ "'";
			ResultSet rsLawatan = stmt.executeQuery(sql);
			if (rsLawatan.next()) {
				flagGuna = rsLawatan.getString("FLAG_GUNA") == null ? ""
						: rsLawatan.getString("FLAG_GUNA");
				lot = rsLawatan.getString("LOT") == null ? "" : rsLawatan
						.getString("LOT");
				noHak = rsLawatan.getString("NO_HAK") == null ? "" : rsLawatan
						.getString("NO_HAK");
				mukimTanah = rsLawatan.getString("MUKIM_HAKMILIK") == null ? ""
						: rsLawatan.getString("MUKIM_HAKMILIK");
				daerahTanah = rsLawatan.getString("DAERAH_HAKMILIK") == null ? ""
						: rsLawatan.getString("DAERAH_HAKMILIK");
				negeriTanah = rsLawatan.getString("NEGERI_HAKMILIK") == null ? ""
						: rsLawatan.getString("NEGERI_HAKMILIK");
				tujuanKegunaan = rsLawatan.getString("TUJUAN") == null ? ""
						: rsLawatan.getString("TUJUAN");
				pemohon = rsLawatan.getString("NAMA_PEMOHON") == null ? ""
						: rsLawatan.getString("NAMA_PEMOHON");
			}
			String tujuan = "Laporan ini disediakan berhubung dengan permohonan penyewaan "
					+ flagGuna
					+ " tanah milik persekutuan "
					+ lot
					+ ", "
					+ noHak
					+ ", "
					+ mukimTanah
					+ ", Daerah "
					+ daerahTanah
					+ ", Negeri "
					+ negeriTanah
					+ " untuk disewakan kepada "
					+ pemohon + " bagi tujuan " + tujuanKegunaan;

			// TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_JENISTANAH", "P");
			r.add("TUJUAN_LAPORAN", tujuan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");
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
					getIdSuburusanstatus(idSuburusan, "1610200")); // LAWATAN
																	// TAPAK
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
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

	public void setMaklumatPejabat(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX, A.ID_PEJABAT"
					+ " FROM TBLRUJPEJABAT A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABAT = '"
					+ idPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));
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

	public void setMaklumatAgensi(String idAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatAgensi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_AGENSI, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, A.ID_AGENSI"
					+ " FROM TBLRUJAGENSI A, TBLRUJNEGERI B WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_AGENSI = '"
					+ idAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("namaAgensi", rs.getString("NAMA_AGENSI") == null ? ""
						: rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3").toUpperCase());
				h.put("poskod",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				beanMaklumatAgensi.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idPejabat", "");
				h.put("idAgensi", "");
				h.put("namaAgensi", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("negeri", "");
				h.put("idNegeri", "");
				beanMaklumatAgensi.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatLampiran(String idUlasanTeknikal)
			throws Exception {
		Db db = null;
		String sql = "";
		Vector beanMaklumatLampiran = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPDOKUMEN WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikal + "'";

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
				h.put("idUlasanTeknikal",
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

	public String getIdPejabatJPPH(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
		String idPejabat = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG"
					+ " FROM TBLPHPHAKMILIK, TBLRUJPEJABATURUSAN"
					+ " WHERE TBLPHPHAKMILIK.ID_NEGERI = TBLRUJPEJABATURUSAN.ID_NEGERIURUS"
					+ " AND TBLPHPHAKMILIK.ID_DAERAH = TBLRUJPEJABATURUSAN.ID_DAERAHURUS"
					+ " AND TBLRUJPEJABATURUSAN.ID_JENISPEJABAT = '3' AND TBLPHPHAKMILIK.ID_HAKMILIK = '"
					+ idHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				idPejabat = rs.getString("ID_PEJABATJKPTG");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return idPejabat;
	}

	public String getIdPejabatJH(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
		String idPejabat = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJPEJABAT.ID_PEJABAT FROM TBLHTPHAKMILIK, TBLRUJPEJABAT"
					+ " WHERE TBLHTPHAKMILIK.ID_NEGERI = TBLRUJPEJABAT.ID_NEGERI(+) AND TBLRUJPEJABAT.ID_JENISPEJABAT = 82"
					+ " AND TBLHTPHAKMILIK.ID_HAKMILIK = '" + idHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				idPejabat = rs.getString("ID_PEJABAT");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return idPejabat;
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

	public void hapusDokumen(String idUlasanTeknikal, HttpSession session) throws Exception {
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
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "DEL",
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
	
	public void updateUlasanKJT(String idPermohonan, String idDokumen, String ulasan, HttpSession session) throws Exception {

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
			r.update("FLAG_KERTAS", "1");
			if ("4".equals(idDokumen)) {
				r.add("ULASAN_JKPTGN", ulasan); 
			}
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
	
	public void updateUlasanKJP(String idPermohonan, String ulasan, HttpSession session) throws Exception {

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
			r.update("FLAG_KERTAS", "1");
			r.add("ULASAN_KJP", ulasan);

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
	
	public void updateUlasanJPPH(String idPermohonan, String ulasan, HttpSession session) throws Exception {

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
			r.update("FLAG_KERTAS", "1");
			r.add("ULASAN_JPPH", ulasan);

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
	
	public void updateUlasanJKT(String idPermohonan, String idKementerianJKT, String idAgensiJKT, 
			String idPejabatJKT, String ulasan, HttpSession session) throws Exception {

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
			
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			// KEMENTERIAN KEWANGAN MALAYSIA
			if (("13".equals(idKementerianJKT)) && ("397".equals(idAgensiJKT))) {
				r.add("ULASAN_KEM_KEWANGAN", ulasan);
			}
			// KEMENTERIAN WILAYAH PERSEKUTUAN
			else if (("31".equals(idKementerianJKT)) && ("1610750".equals(idAgensiJKT))) {
				r.add("ULASAN_KEM_WP", ulasan);
			}
			// PTG KUALA LUMPUR
			else if ("147".equals(idPejabatJKT)) {
				r.add("ULASAN_PTG", ulasan);
			}
			// DBKL
			else if ("1610748".equals(idPejabatJKT)) {
				r.add("ULASAN_DBKL", ulasan);
			}
			// BAHAGIAN PENGURUSAN HARTANAH
			else if (("12".equals(idKementerianJKT)) && ("328".equals(idAgensiJKT))) {
				r.add("ULASAN_BPH", ulasan);
			}

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
	
	public void updateUlasanJH(String idPermohonan, String ulasan, HttpSession session) throws Exception {

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
			r.update("FLAG_KERTAS", "1");
			r.add("ULASAN_JH", ulasan);

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
	
	

	public Vector getListKJT() {
		return listKJT;
	}

	public void setListKJT(Vector listKJT) {
		this.listKJT = listKJT;
	}

	public Vector getBeanMaklumatKJT() {
		return beanMaklumatKJT;
	}

	public void setBeanMaklumatKJT(Vector beanMaklumatKJT) {
		this.beanMaklumatKJT = beanMaklumatKJT;
	}

	public Vector getListKJP() {
		return listKJP;
	}

	public void setListKJP(Vector listKJP) {
		this.listKJP = listKJP;
	}
	
	public Vector getBeanMaklumatKJP() {
		return beanMaklumatKJP;
	}

	public void setBeanMaklumatKJP(Vector beanMaklumatKJP) {
		this.beanMaklumatKJP = beanMaklumatKJP;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}

	public Vector getListJPPH() {
		return listJPPH;
	}

	public void setListJPPH(Vector listJPPH) {
		this.listJPPH = listJPPH;
	}

	public Vector getBeanMaklumatJPPH() {
		return beanMaklumatJPPH;
	}

	public void setBeanMaklumatJPPH(Vector beanMaklumatJPPH) {
		this.beanMaklumatJPPH = beanMaklumatJPPH;
	}

	public Vector getListJKT() {
		return listJKT;
	}

	public void setListJKT(Vector listJKT) {
		this.listJKT = listJKT;
	}

	public Vector getBeanMaklumatJKT() {
		return beanMaklumatJKT;
	}

	public void setBeanMaklumatJKT(Vector beanMaklumatJKT) {
		this.beanMaklumatJKT = beanMaklumatJKT;
	}

	public Vector getBeanMaklumatAgensi() {
		return beanMaklumatAgensi;
	}

	public void setBeanMaklumatAgensi(Vector beanMaklumatAgensi) {
		this.beanMaklumatAgensi = beanMaklumatAgensi;
	}

	public Vector getListJH() {
		return listJH;
	}

	public void setListJH(Vector listJH) {
		this.listJH = listJH;
	}

	public Vector getBeanMaklumatJH() {
		return beanMaklumatJH;
	}

	public void setBeanMaklumatJH(Vector beanMaklumatJH) {
		this.beanMaklumatJH = beanMaklumatJH;
	}
}
