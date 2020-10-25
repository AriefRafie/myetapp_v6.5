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
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.utils.emel.EmailConfig;

public class FrmTKRKeputusanData {

	private Vector beanMaklumatKeputusan = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatAgensi = null;
	private Vector beanMaklumatPejabat = null;
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatPermohonan = null;
	private HakmilikInterface iHakmilik = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void simpanMaklumatKeputusan(String idPermohonan,
			String txtTarikhKeputusan, String socKeputusan, String txtPampasan,
			String socPampasan, String txtUlasan, String idStatus, HttpSession session)
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

			if (!"".equals(txtTarikhKeputusan)) {
				r.add("TARIKH_HANTARKEPUTUSAN",
						r.unquote("to_date('" + txtTarikhKeputusan
								+ "','dd/MM/yyyy')"));
			}
			r.add("KEPUTUSAN", socKeputusan);
			r.add("PAMPASAN", txtPampasan);
			r.add("CATATAN_KEPUTUSAN", txtUlasan);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

			if ("1610207".equals(idStatus) || "1610208".equals(idStatus) || "1617199".equals(idStatus)) {
				// UPDATE ID_STATUS TBLPERMOHONAN - ASNA
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN", idPermohonan);
				r.add("ID_STATUS", "1610206");

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPERMOHONAN");
				stmt.executeUpdate(sql);
			}

			conn.commit();
			
			AuditTrail.logActivity("1610206", "4", null, session, "INS",
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

	public void setMaklumatKeputusan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKeputusan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KEPUTUSAN, TARIKH_HANTARKEPUTUSAN, FLAG_PAMPASAN, PAMPASAN, JENIS_PAMPASAN, CATATAN_PAMPASAN, CATATAN_KEPUTUSAN " +
					"FROM TBLPHPPERMOHONANPELEPASAN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhKeputusan",
						rs.getDate("TARIKH_HANTARKEPUTUSAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_HANTARKEPUTUSAN")));
				h.put("keputusan",
						rs.getString("KEPUTUSAN") == null ? "" : rs
								.getString("KEPUTUSAN"));
				h.put("flagPampasan",
						rs.getString("FLAG_PAMPASAN") == null ? "" : rs
								.getString("FLAG_PAMPASAN"));
				h.put("pampasan",
						rs.getString("PAMPASAN") == null ? "" : Utils
								.format2Decimal(rs.getDouble("PAMPASAN")));
				h.put("jenis",
						rs.getString("JENIS_PAMPASAN") == null ? "" : rs
								.getString("JENIS_PAMPASAN"));
				h.put("catatan", rs.getString("CATATAN_PAMPASAN") == null ? ""
						: rs.getString("CATATAN_PAMPASAN"));
				h.put("ulasan", rs.getString("CATATAN_KEPUTUSAN") == null ? ""
						: rs.getString("CATATAN_KEPUTUSAN"));
				beanMaklumatKeputusan.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateStatus(String idFail, String idPermohonan,
			String idKeputusan, HttpSession session) throws Exception {
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
			if ("L".equals(idKeputusan)) {
				r.add("ID_STATUS", "1610207"); // LULUS
			} else if ("B".equals(idKeputusan)) {
				r.add("ID_STATUS", "1617199"); // LULUS BERSYARAT -AIN-
			}else if ("T".equals(idKeputusan)) {
				r.add("ID_STATUS", "1610208"); // TOLAK
			}

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
			if ("L".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("33", "1610207")); // LULUS
			} else if ("B".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("33", "1617102998")); // LULUS BERSYARAT -AIN-
			}else if ("T".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("33", "1610208")); // TOLAK
			}

			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// UPDATE TBLHTPHAKMILIKAGENSI WHEN LULUS
			if (("L".equals(idKeputusan) || "B".equals(idKeputusan)) && getFlagBorangK(idFail)) {

				String catatanHTP = "";
				catatanHTP = getTajukFail(idFail);
				String idHakmilikAgensi = getIdHakmilikAgensiByIdPermohonan(idPermohonan);
				String idHakmilik = getIdHakmilik(idHakmilikAgensi);
				Double luasMohon = getLuasMohon(idPermohonan);
				Double luasBaki = getLuasBaki(idPermohonan);

				// DEACTIVATE ROW TBLHTPHAKMILIKAGENSI
				deActivateHakmilikAgensi(idHakmilikAgensi, db, userId);

				// GET ROW HTPHAKMILIKAGENSI
				if (luasBaki != 0D) {
					Hashtable hashAgensi = getMaklumatAgensiByIdHakmilikAgensi(idHakmilikAgensi);
					if (hashAgensi != null) {
						// TBLHTPHAKMILIKAGENSI
						insertHakmilikAgensi(idHakmilik,
								(String) hashAgensi.get("idKementerian"),
								(String) hashAgensi.get("idAgensi"), luasBaki,
								db, userId);
					}
				}

				// GENERATE ROW FOR KJP BARU YANG DILULUSKAN (PEMOHON)
				String catatanAgensi = "";

				String idKementerian = getIdKementerian(idPermohonan);
				String idAgensi = getIdAgensi(idPermohonan);
				String idHakmilikAgensiExist = getIdAgensiExist(idHakmilik,
						idAgensi);

				if ("".equals(idHakmilikAgensiExist)) {
					insertHakmilikAgensi(idHakmilik, idKementerian, idAgensi,
							luasMohon, db, userId);
				} else {
					updateHakmilikAgensi(idHakmilikAgensiExist, luasMohon, db,
							userId);
				}

				catatanAgensi = getNamaKementerian(idKementerian) + " "
						+ getNamaAgensi(idAgensi) + " "
						+ Utils.formatLuas(luasMohon) + "H";

				// GET ROW TBLPHPTANAHGANTIPLPSN
				Vector listTG = null;
				listTG = getSenaraiTanahGanti(idPermohonan);
				if (listTG.size() != 0) {
					for (int i = 0; i < listTG.size(); i++) {
						Hashtable hashTG = (Hashtable) listTG.get(i);
						String idTanahGanti = "";
						if (hashTG.get("ID_TANAHGANTI") != null)
							idTanahGanti = (String) hashTG.get("ID_TANAHGANTI");
						String idHakmilikAgensiTG = "";
						if (hashTG.get("ID_HAKMILIKAGENSI") != null)
							idHakmilikAgensiTG = (String) hashTG
									.get("ID_HAKMILIKAGENSI");

						String idHakmilikTG = getIdHakmilik(idHakmilikAgensiTG);
						Double luasBeriTG = getLuasBeriTG(idTanahGanti);
						Double luasBakiTG = getLuasBakiTG(idTanahGanti);

						// DEACTIVATE ROW TBLHTPHAKMILIKAGENSI
						deActivateHakmilikAgensi(idHakmilikAgensiTG, db, userId);

						// GET ROW HTPHAKMILIKAGENSI
						if (luasBakiTG != 0D) {
							Hashtable hashAgensi = getMaklumatAgensiByIdHakmilikAgensi(idHakmilikAgensiTG);
							if (hashAgensi != null) {
								// TBLHTPHAKMILIKAGENSI
								insertHakmilikAgensi(idHakmilikTG,
										(String) hashAgensi
												.get("idKementerian"),
										(String) hashAgensi.get("idAgensi"),
										luasBakiTG, db, userId);
							}
						}

						String idKementerianKJP = getIdKementerianKJP(idPermohonan);
						String idAgensiKJP = getIdAgensiKJP(idPermohonan);
						String idHakmilikAgensiExistTG = getIdAgensiExist(
								idHakmilikTG, idAgensiKJP);

						if ("".equals(idHakmilikAgensiExistTG)) {
							insertHakmilikAgensi(idHakmilikTG,
									idKementerianKJP, idAgensiKJP, luasBeriTG,
									db, userId);
						} else {
							updateHakmilikAgensi(idHakmilikAgensiExistTG,
									luasBeriTG, db, userId);
						}
					}
				}

				catatanHTP = catatanHTP + " (" + catatanAgensi + ")";

				// TBLHTPHAKMILIK
				r = new SQLRenderer();
				r.update("ID_HAKMILIK", idHakmilik);

				r.add("TARIKH_MOHON_PELEPASAN", r.unquote("SYSDATE"));
				r.add("STATUS_PELEPASAN", "Y");
				// UPDATE CATATAN
				r.add("CATATAN", catatanHTP);

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPHAKMILIK");
				stmt.executeUpdate(sql);

				// getHakmilik().kemaskiniHakmilikCatatan(idHakmilik,
				// catatanHTP);

			}

			conn.commit();
			
			
			if ("L".equals(idKeputusan)) {
				AuditTrail.logActivity("1610207", "4", null, session, "INS",
						"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] DIDAFTARKAN"); // LULUS
			} else if ("B".equals(idKeputusan)) {
				AuditTrail.logActivity("1617199", "4", null, session, "INS",
						"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] DIDAFTARKAN"); // LULUS BERSYARAT 
			}else if ("T".equals(idKeputusan)) {
				AuditTrail.logActivity("1610208", "4", null, session, "INS",
						"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] DIDAFTARKAN"); // TOLAK
			}

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
	
	public void sendEmailtoPemohon(String idPermohonan, String idKeputusan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String namaUser = "";
		String emelUser = "nurulain.siprotech@gmail.com"; //UNTUK SEMENTARA
		String idhakmilikPermohonan = "";
		String noPermohonan = "";
		String idSuburusan = "";
		String keputusan = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
						
			sql = "SELECT B.ID_HAKMILIKPERMOHONAN, A.NO_PERMOHONAN, C.ID_SUBURUSAN, D.NAMA, D.EMEL " 
				+ " FROM TBLPERMOHONAN A,TBLPHPHAKMILIKPERMOHONAN B, TBLPFDFAIL C,TBLPHPPEMOHON D "
				+ " WHERE C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN "
				+ " AND A.ID_PEMOHON = D.ID_PEMOHON AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			
			
			ResultSet rsPermohonan = stmt.executeQuery(sql);
			if (rsPermohonan.next()){
				idhakmilikPermohonan = rsPermohonan.getString("ID_HAKMILIKPERMOHONAN");
				noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
				idSuburusan = rsPermohonan.getString("ID_SUBURUSAN");
				namaUser = rsPermohonan.getString("NAMA");
				//emelUser = rsPermohonan.getString("EMEL");
			}	
			
			if ("L".equals(idKeputusan)) {
				keputusan = "LULUS";
			} else if ("B".equals(idKeputusan)) {
				keputusan = "LULUS BERSYARAT";
			} else if ("T".equals(idKeputusan)) {
				keputusan = "TOLAK";
			}
			
			if (!"".equals(namaUser) && !"".equals(emelUser)){
				EmailConfig ef = new EmailConfig();
				
				String subject = "PERMOHONAN TUKARGUNA " + noPermohonan;
				String kandungan = namaUser.toUpperCase() + "."
						+ "<br><br>Permohonan anda telah "+ keputusan +".Sila gunakan nombor permohonan diatas sebagai rujukan.";
				
				ef.sendByOnlineUser(emelUser, subject, kandungan);
			}
		} finally {
			if (db != null)
				db.close();
		}
	} 

	private boolean getFlagBorangK(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = false;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.FLAG_BORANGK FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				if ("Y".equals(rs.getString("FLAG_BORANGK"))) {
					bool = false;
				} else {
					bool = true;
				}
			}

		} finally {
			if (db != null)
				db.close();
		}

		return bool;
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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("33", "1610212")); // BATAL
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

	private String getIdSuburusanstatus(String idSuburusan, String idStatus)
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
				return rs.getString("ID_SUBURUSANSTATUS").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getTajukFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TAJUK_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("TAJUK_FAIL");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdHakmilikAgensiByIdPermohonan(String idPermohonan)
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

	private String getIdHakmilik(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIK FROM TBLHTPHAKMILIKAGENSI WHERE ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_HAKMILIK");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Double getLuasMohon(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS_MHNBERSAMAAN FROM TBLPHPPERMOHONANPELEPASAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("LUAS_MHNBERSAMAAN");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Double getLuasBaki(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS_BAKI FROM TBLPHPPERMOHONANPELEPASAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("LUAS_BAKI");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Double getLuasBeriTG(String idTanahGanti) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS_BERSAMAAN FROM TBLPHPTANAHGANTIPLPSN WHERE ID_TANAHGANTI = '"
					+ idTanahGanti + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("LUAS_BERSAMAAN");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Double getLuasBakiTG(String idTanahGanti) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS_BAKI FROM TBLPHPTANAHGANTIPLPSN WHERE ID_TANAHGANTI = '"
					+ idTanahGanti + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("LUAS_BAKI");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Hashtable getMaklumatAgensiByIdHakmilikAgensi(
			String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable hash = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIK, ID_KEMENTERIAN, ID_AGENSI, ID_LUAS, LUAS, ID_LUAS_BERSAMAAN, LUAS_BERSAMAAN FROM TBLHTPHAKMILIKAGENSI WHERE ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 1;
			if (rs.next()) {
				hash = new Hashtable();
				hash.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				hash.put(
						"idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				hash.put("idAgensi", rs.getString("ID_AGENSI") == null ? ""
						: rs.getString("ID_AGENSI"));
				hash.put(
						"idLuas",
						rs.getString("ID_LUAS") == null ? "" : rs
								.getString("ID_LUAS"));
				hash.put(
						"luas",
						rs.getString("LUAS") == null ? "" : rs
								.getString("LUAS"));
				hash.put(
						"idLuasBersamaan",
						rs.getString("ID_LUAS_BERSAMAAN") == null ? "" : rs
								.getString("ID_LUAS_BERSAMAAN"));
				hash.put(
						"luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
			}

		} finally {
			if (db != null)
				db.close();
		}
		return hash;
	}

	private String getIdKategoriPemohon(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_KATEGORIPEMOHON FROM TBLPHPPEMOHON A, TBLPERMOHONAN B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_KATEGORIPEMOHON");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdKementerian(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_KEMENTERIAN FROM TBLPHPPEMOHON A, TBLPERMOHONAN B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdAgensi(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AGENSI FROM TBLPHPPEMOHON A, TBLPERMOHONAN B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_AGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdKementerianKJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_KEMENTERIAN FROM TBLHTPHAKMILIKAGENSI A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_HAKMILIKAGENSI = B.ID_HAKMILIKAGENSI AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdAgensiKJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_AGENSI FROM TBLHTPHAKMILIKAGENSI A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_HAKMILIKAGENSI = B.ID_HAKMILIKAGENSI AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_AGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private void deActivateHakmilikAgensi(String idHakmilikAgensi, Db db,
			String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLHTPHAKMILIKAGENSI
			r = new SQLRenderer();
			r.update("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPHAKMILIKAGENSI");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void insertHakmilikAgensi(String idHakmilik, String idKementerian,
			String idAgensi, Double luas, Db db, String userId)
			throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLHTPHAKMILIKAGENSI
			r = new SQLRenderer();
			long idHakmilikAgensiInsert = DB
					.getNextID("TBLHTPHAKMILIKAGENSI_SEQ");
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensiInsert);
			r.add("ID_HAKMILIK", idHakmilik);
			r.add("ID_KEMENTERIAN", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_LUAS", "2");
			r.add("LUAS", Utils.formatLuas(luas));
			r.add("ID_LUAS_BERSAMAAN", "2");
			r.add("LUAS_BERSAMAAN", Utils.formatLuas(luas));
			r.add("STATUS", "T");
			r.add("FLAG_AKTIF", "Y");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void updateHakmilikAgensi(String idHakmilikAgensi, Double luas,
			Db db, String userId) throws Exception {

		String sql = "";
		Double totalLuas = 0D + luas;

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT LUAS_BERSAMAAN FROM TBLHTPHAKMILIKAGENSI WHERE ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				totalLuas = totalLuas + rs.getDouble("LUAS_BERSAMAAN");
			}

			// TBLHTPHAKMILIKAGENSI
			r.update("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("LUAS", Utils.formatLuas(totalLuas));
			r.add("LUAS_BERSAMAAN", Utils.formatLuas(totalLuas));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPHAKMILIKAGENSI");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	public String getNamaKementerian(String idKementerian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '"
					+ idKementerian + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NAMA_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getNamaAgensi(String idAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_AGENSI FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
					+ idAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NAMA_AGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdAgensiExist(String idHakmilik, String idAgensi)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIKAGENSI WHERE FLAG_AKTIF = 'Y' AND ID_HAKMILIK = '"
					+ idHakmilik + "'" + " AND ID_AGENSI = '" + idAgensi + "'";

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

	private Vector getSenaraiTanahGanti(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		Vector listTanahGanti = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_TANAHGANTI, ID_HAKMILIKAGENSI FROM TBLPHPTANAHGANTIPLPSN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_TANAHGANTI",
						rs.getString("ID_TANAHGANTI") == null ? "" : rs
								.getString("ID_TANAHGANTI"));
				h.put("ID_HAKMILIKAGENSI",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				listTanahGanti.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return listTanahGanti;
	}

	public void setMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_KATEGORIPEMOHON, C.ID_AGENSI, C.ID_KEMENTERIAN, C.ID_PEJABAT FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON  C"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND A.ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs
						.getString("ID_AGENSI").toUpperCase());
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "" : rs
						.getString("ID_PEJABAT").toUpperCase());
				beanMaklumatPemohon.addElement(h);
				bil++;
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

	public String getidHakmilikAgensi(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.ID_HAKMILIKAGENSI FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTanah(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT L.ID_HAKMILIKAGENSI, A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK,"
					+ " B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.NO_WARTA, A.KEGUNAAN_TANAH, L.LUAS_BERSAMAAN, L.ID_LUAS_BERSAMAAN, A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI,"
					+ " G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KOD_LUAS AS KOD_LUAS, K.KETERANGAN AS JENIS_LUAS_BERSAMAAN"
					+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I,TBLRUJAGENSI J, TBLRUJLUAS K, TBLHTPHAKMILIKAGENSI L"
					+ " WHERE A.ID_HAKMILIK = L.ID_HAKMILIK AND A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+)"
					+ " AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND L.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND L.ID_AGENSI = J.ID_AGENSI(+) AND L.ID_LUAS_BERSAMAAN = K.ID_LUAS"
					+ " AND L.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null
						|| rs.getString("NO_HAKMILIK") == null ? "" : rs
						.getString("KOD_JENIS_HAKMILIK").toUpperCase()
						+ " "
						+ rs.getString("NO_HAKMILIK"));
				h.put("noLot",
						rs.getString("JENIS_LOT") == null
								|| rs.getString("NO_LOT") == null ? "" : rs
								.getString("JENIS_LOT")
								+ " "
								+ rs.getString("NO_LOT"));
				h.put("luasLot",
						rs.getString("LUAS_BERSAMAAN") == null
								|| rs.getString("JENIS_LUAS_BERSAMAAN") == null ? ""
								: Utils.formatLuas(rs
										.getDouble("LUAS_BERSAMAAN"))
										+ " "
										+ rs.getString("JENIS_LUAS_BERSAMAAN"));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());
				beanMaklumatTanah.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", "");
				h.put("daerah", "");
				h.put("negeri", "");
				h.put("kategoriTanah", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kementerian", "");
				h.put("agensi", "");
				h.put("kegunaanTanah", "");
				beanMaklumatTanah.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String daftarRayuan(String idFailLama, String idPermohonanLama,
			String tarikhTerima, String tarikhSurat, String txtNoRujukanSurat,
			String txtPerkara, String idHakmilikAgensi, HttpSession session)
			throws Exception {
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

			// DEACTIVATE PERMOHONAN LAMA
			deActivatePermohonanLama(idPermohonanLama, db, userId);

			// TBLPFDFAIL
			sql = "SELECT NO_FAIL, ID_NEGERI, ID_KEMENTERIAN FROM TBLPFDFAIL WHERE ID_FAIL = '"
					+ idFailLama + "'";
			ResultSet rsFail = stmt.executeQuery(sql);
			rsFail.next();

			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "6");
			r.add("ID_SUBURUSAN", "33");
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", txtPerkara);
			r.add("NO_FAIL",
					rsFail.getString("NO_FAIL") == null ? "" : rsFail
							.getString("NO_FAIL"));
			r.add("NO_FAIL_ROOT", rsFail.getString("NO_FAIL") == null ? ""
					: rsFail.getString("NO_FAIL"));
			r.add("ID_LOKASIFAIL", "2"); // UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "1"); // DATA BARU ETAPP
			r.add("ID_NEGERI", rsFail.getString("ID_NEGERI") == null ? ""
					: rsFail.getString("ID_NEGERI"));
			r.add("ID_KEMENTERIAN",
					rsFail.getString("ID_KEMENTERIAN") == null ? "" : rsFail
							.getString("ID_KEMENTERIAN"));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			sql = "SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_FAIL = '"
					+ idFailLama + "'";
			ResultSet rsPermohonan = stmt.executeQuery(sql);
			rsPermohonan.next();

			r = new SQLRenderer();
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON",
					rsPermohonan.getString("ID_PEMOHON") == null ? ""
							: rsPermohonan.getString("ID_PEMOHON"));
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS", "1610198");
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(tarikhSurat)) {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + tarikhSurat
						+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJ_SURAT", txtNoRujukanSurat);
			r.add("FLAG_AKTIF", "Y");
			r.add("NO_RAYUAN", "1");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIKPERMOHONAN
			sql = "SELECT ID_HAKMILIKPERMOHONAN, NILAIAN FROM TBLPHPHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonanLama + "'";
			ResultSet rsHakmilikPermohonan = stmt.executeQuery(sql);
			rsHakmilikPermohonan.next();

			String idHakmilikPermohonanLama = "";
			if (rsHakmilikPermohonan.getString("ID_HAKMILIKPERMOHONAN") != null)
				idHakmilikPermohonanLama = rsHakmilikPermohonan
						.getString("ID_HAKMILIKPERMOHONAN");

			r = new SQLRenderer();
			long idhakmilikPermohonan = DB
					.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("NILAIAN",
					rsHakmilikPermohonan.getString("NILAIAN") == null ? ""
							: rsHakmilikPermohonan.getString("NILAIAN"));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPLAPORANTANAH
			String idLaporanTanahLama = "";
			idLaporanTanahLama = getIdLaporanTanah(idPermohonanLama,
					idHakmilikPermohonanLama);

			copyTBLPHPLAPORANTANAH(idLaporanTanahLama, idPermohonan,
					idhakmilikPermohonan, db, userId);

			// GET ROW TBLPHPTANAHGANTIPLPSN
			Vector listTG = null;
			listTG = getSenaraiTanahGanti(idPermohonanLama);
			if (listTG.size() != 0) {
				for (int i = 0; i < listTG.size(); i++) {

					Hashtable hashTG = (Hashtable) listTG.get(i);
					String idTanahGantiLama = "";
					if (hashTG.get("ID_TANAHGANTI") != null)
						idTanahGantiLama = (String) hashTG.get("ID_TANAHGANTI");
					String idHakmilikAgensiTGLama = "";
					if (hashTG.get("ID_HAKMILIKAGENSI") != null)
						idHakmilikAgensiTGLama = (String) hashTG
								.get("ID_HAKMILIKAGENSI");

					copyTBLPHPTANAHGANTIPLPSN(idTanahGantiLama, idPermohonan,
							idPermohonanLama, db, userId);
				}
			}

			// TBLPHPPERMOHONANPELEPASAN
			String idPHPPermohonanPelepasanLama = "";
			idPHPPermohonanPelepasanLama = getIdPHPPermohonanPelepasan(idPermohonanLama);
			copyTBLPHPPERMOHONANPELEPASAN(idPHPPermohonanPelepasanLama,
					idPermohonan, db, userId);

			// GET ROW TBLPHPULASANTEKNIKAL
			Vector listUlasan = null;
			listUlasan = getSenaraiUlasan(idPermohonanLama);
			if (listUlasan.size() != 0) {
				for (int i = 0; i < listUlasan.size(); i++) {

					Hashtable hashUlasan = (Hashtable) listUlasan.get(i);
					String idUlasanTeknikalLama = "";
					if (hashUlasan.get("ID_ULASANTEKNIKAL") != null)
						idUlasanTeknikalLama = (String) hashUlasan
								.get("ID_ULASANTEKNIKAL");

					copyTBLPHPULASANTEKNIKAL(idUlasanTeknikalLama,
							idPermohonan, db, userId);
				}
			}

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("33", "1610198")); // MAKLUMAT
																				// PERMOHONAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
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
		session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
	}

	private Vector getSenaraiUlasan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		Vector listUlasan = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_ULASANTEKNIKAL FROM TBLPHPULASANTEKNIKAL WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_ULASANTEKNIKAL",
						rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs
								.getString("ID_ULASANTEKNIKAL"));
				listUlasan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return listUlasan;
	}

	private void copyTBLPHPULASANTEKNIKAL(String idUlasanTeknikalLama,
			long idPermohonan, Db db, String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPULASANTEKNIKAL
			r = new SQLRenderer();
			long idUlasanTeknikal = DB.getNextID("TBLPHPULASANTEKNIKAL_SEQ");

			sql = "INSERT INTO TBLPHPULASANTEKNIKAL (ID_ULASANTEKNIKAL, ID_PERMOHONAN, ID_MENTERI,"
					+ " ID_AGENSI, NO_RUJUKAN, ID_DOKUMEN, ULASAN, TARIKH_HANTAR, JANGKAMASA,"
					+ " TARIKH_JANGKA_TERIMA, TARIKH_SURAT, TARIKH_TERIMA,"
					+ " FLAG_STATUS, FLAG_KJP, ID_PEJABAT, ID_NEGERI, FLAG_AKTIF,"
					+ " ID_MASUK, TARIKH_MASUK)"

					+ " SELECT "
					+ idUlasanTeknikal
					+ ", "
					+ idPermohonan
					+ ", ID_MENTERI,"
					+ " ID_AGENSI, NO_RUJUKAN, ID_DOKUMEN, ULASAN, TARIKH_HANTAR, JANGKAMASA,"
					+ " TARIKH_JANGKA_TERIMA, TARIKH_SURAT, TARIKH_TERIMA,"
					+ " FLAG_STATUS, FLAG_KJP, ID_PEJABAT, ID_NEGERI, FLAG_AKTIF,"
					+ " "
					+ userId
					+ ", SYSDATE"
					+ " FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '"
					+ idUlasanTeknikalLama + "'";

			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void copyTBLPHPPERMOHONANPELEPASAN(
			String idPHPPermohonanPelepasanLama, long idPermohonan, Db db,
			String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPERMOHONANPELEPASAN
			r = new SQLRenderer();
			long idPHPPermohonanPelepasan = DB
					.getNextID("TBLPHPPERMOHONANPELEPASAN_SEQ");

			sql = "INSERT INTO TBLPHPPERMOHONANPELEPASAN ("
					+ " ID_PHPPERMOHONANPELEPASAN, ID_PERMOHONAN, NAMA_PROJEK,"
					+ " FLAG_JENIS_PROJEK, ID_UNITLUASMHNBERSAMAAN, LUAS_MHNBERSAMAAN,"
					+ " ID_UNITLUASBAKI, LUAS_BAKI, KEMAJUAN_TANAH,"
					+ " FLAG_GUNA, CADANGAN_KEGUNAAN, LUAS_ASAL, ID_LUASASAL, LUAS_MHN1,"
					+ " LUAS_MHN2, LUAS_MHN3, ID_LUASMHN, ID_MASUK, TARIKH_MASUK)"

					+ " SELECT "
					+ idPHPPermohonanPelepasan
					+ ", "
					+ idPermohonan
					+ ", NAMA_PROJEK,"
					+ " FLAG_JENIS_PROJEK, ID_UNITLUASMHNBERSAMAAN, LUAS_MHNBERSAMAAN,"
					+ " ID_UNITLUASBAKI, LUAS_BAKI, KEMAJUAN_TANAH,"
					+ " FLAG_GUNA, CADANGAN_KEGUNAAN, LUAS_ASAL, ID_LUASASAL, LUAS_MHN1,"
					+ " LUAS_MHN2, LUAS_MHN3, ID_LUASMHN, "
					+ userId
					+ ", SYSDATE"
					+ " FROM TBLPHPPERMOHONANPELEPASAN WHERE ID_PHPPERMOHONANPELEPASAN = '"
					+ idPHPPermohonanPelepasanLama + "'";

			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	public String getIdPHPPermohonanPelepasan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PHPPERMOHONANPELEPASAN FROM TBLPHPPERMOHONANPELEPASAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_PHPPERMOHONANPELEPASAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private void copyTBLPHPTANAHGANTIPLPSN(String idTanahGantiLama,
			long idPermohonan, String idPermohonanLama, Db db, String userId)
			throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPTANAHGANTIPLPSN
			r = new SQLRenderer();
			long idTanahGanti = DB.getNextID("TBLPHPTANAHGANTIPLPSN_SEQ");

			sql = "INSERT INTO TBLPHPTANAHGANTIPLPSN ("
					+ " ID_TANAHGANTI, ID_PERMOHONAN, PEGANGAN_HAKMILIK, ID_JENISHAKMILIK, NO_HAKMILIK, ID_LOT,"
					+ " NO_LOT, ID_LUAS, LUAS1, LUAS2, LUAS3, ID_LUAS_BERSAMAAN, LUAS_BERSAMAAN, NO_SYIT, NO_PELAN,"
					+ " SYARAT, SEKATAN, ID_KATEGORI, ID_SUBKATEGORI, KEGUNAAN_TANAH, ID_DAERAH,"
					+ " ID_NEGERI, ID_MUKIM, NILAIAN, NO_WARTA, TARIKH_WARTA, ID_KEMENTERIAN, ID_AGENSI, ID_LUASASAL,"
					+ " LUAS_ASAL, ID_LUASBAKI, LUAS_BAKI, FLAG_GUNA, ID_HAKMILIKAGENSI, ID_MASUK, TARIKH_MASUK)"

					+ " SELECT "
					+ idTanahGanti
					+ ", "
					+ idPermohonan
					+ ", PEGANGAN_HAKMILIK, ID_JENISHAKMILIK, NO_HAKMILIK, ID_LOT,"
					+ " NO_LOT, ID_LUAS, LUAS1, LUAS2, LUAS3, ID_LUAS_BERSAMAAN, LUAS_BERSAMAAN, NO_SYIT, NO_PELAN,"
					+ " SYARAT, SEKATAN, ID_KATEGORI, ID_SUBKATEGORI, KEGUNAAN_TANAH, ID_DAERAH,"
					+ " ID_NEGERI, ID_MUKIM, NILAIAN, NO_WARTA, TARIKH_WARTA, ID_KEMENTERIAN, ID_AGENSI, ID_LUASASAL,"
					+ " LUAS_ASAL, ID_LUASBAKI, LUAS_BAKI, FLAG_GUNA, ID_HAKMILIKAGENSI, "
					+ userId + ", SYSDATE"
					+ " FROM TBLPHPTANAHGANTIPLPSN WHERE ID_TANAHGANTI = '"
					+ idTanahGantiLama + "'";

			stmt.executeUpdate(sql);

			// TBLPHPLAPORANTANAH
			String idLaporanTanahLama = "";
			idLaporanTanahLama = getIdLaporanTanah(idPermohonanLama,
					idTanahGantiLama);

			copyTBLPHPLAPORANTANAH(idLaporanTanahLama, idPermohonan,
					idTanahGanti, db, userId);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void copyTBLPHPLAPORANTANAH(String idLaporanTanahLama,
			long idPermohonan, long idhakmilikPermohonan, Db db, String userId)
			throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");

			sql = "INSERT INTO TBLPHPLAPORANTANAH"
					+ " (ID_LAPORANTANAH, ID_PERMOHONAN, TARIKH_LAWATAN, JALAN_HUBUNGAN,"
					+ " KAWASAN_BERHAMPIRAN, KEADAAN_RUPABUMI, KEADAAN_TANAH,"
					+ " KEMUDAHAN_ASAS, SEMP_UTARA, SEMP_BARAT, SEMP_TIMUR, SEMP_SELATAN,"
					+ " ULASAN, FLAG_KEMUDAHANASAS_AIR, FLAG_KEMUDAHANASAS_ELEKTRIK,"
					+ " FLAG_KEMUDAHANASAS_TEL, FLAG_JENISTANAH, ID_HAKMILIK,"
					+ " TUJUAN_LAPORAN, LAPORAN_ATASTANAH, JARAK_DARIBANDAR,"
					+ " FLAG_LAWATAN, NAMA_PELAPOR, ID_JAWATANPELAPOR, ID_NEGERIPELAPOR, ID_MASUK, TARIKH_MASUK)"

					+ " SELECT "
					+ idLaporanTanah
					+ ", "
					+ idPermohonan
					+ ", TARIKH_LAWATAN, JALAN_HUBUNGAN,"
					+ " KAWASAN_BERHAMPIRAN, KEADAAN_RUPABUMI, KEADAAN_TANAH,"
					+ " KEMUDAHAN_ASAS, SEMP_UTARA, SEMP_BARAT, SEMP_TIMUR, SEMP_SELATAN,"
					+ " ULASAN, FLAG_KEMUDAHANASAS_AIR, FLAG_KEMUDAHANASAS_ELEKTRIK,"
					+ " FLAG_KEMUDAHANASAS_TEL, FLAG_JENISTANAH, "
					+ idhakmilikPermohonan
					+ ","
					+ " TUJUAN_LAPORAN, LAPORAN_ATASTANAH, JARAK_DARIBANDAR, FLAG_LAWATAN,"
					+ " NAMA_PELAPOR, ID_JAWATANPELAPOR, ID_NEGERIPELAPOR, "
					+ userId + ", SYSDATE"
					+ " FROM TBLPHPLAPORANTANAH WHERE ID_LAPORANTANAH = '"
					+ idLaporanTanahLama + "'";

			stmt.executeUpdate(sql);

			// GET ROW TBLPHPDOKUMEN
			Vector listDokumen = null;
			listDokumen = getSenaraiDokumen(idLaporanTanahLama);
			if (listDokumen.size() != 0) {
				for (int i = 0; i < listDokumen.size(); i++) {

					Hashtable hashDokumen = (Hashtable) listDokumen.get(i);
					String idDokumenLama = "";
					if (hashDokumen.get("ID_DOKUMEN") != null)
						idDokumenLama = (String) hashDokumen.get("ID_DOKUMEN");

					copyTBLPHPDOKUMEN(idDokumenLama, idLaporanTanah, db, userId);
				}
			}

			// GET ROW TBLPHPPEGAWAILAPORANTANAH
			Vector listPegawai = null;
			listPegawai = getSenaraiPegawai(idLaporanTanahLama);
			if (listPegawai.size() != 0) {
				for (int i = 0; i < listPegawai.size(); i++) {

					Hashtable hashPegawai = (Hashtable) listPegawai.get(i);
					String idPegawaiLama = "";
					if (hashPegawai.get("ID_PEGAWAILAPORANTANAH") != null)
						idPegawaiLama = (String) hashPegawai
								.get("ID_PEGAWAILAPORANTANAH");

					copyTBLPHPPEGAWAILAPORANTANAH(idPegawaiLama,
							idLaporanTanah, db, userId);
				}
			}

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private void copyTBLPHPPEGAWAILAPORANTANAH(String idPegawaiLama,
			long idLaporanTanah, Db db, String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEGAWAILAPORANTANAH
			r = new SQLRenderer();
			long idPegawai = DB.getNextID("TBLPHPPEGAWAILAPORANTANAH_SEQ");

			sql = "INSERT INTO TBLPHPPEGAWAILAPORANTANAH"
					+ " (ID_PEGAWAILAPORANTANAH, ID_LAPORANTANAH, NAMA_PEGAWAI,"
					+ " ID_JAWATAN, ID_NEGERIPEGAWAI, ID_MASUK, TARIKH_MASUK)"

					+ " SELECT "
					+ idPegawai
					+ ", "
					+ idLaporanTanah
					+ ", NAMA_PEGAWAI, ID_JAWATAN,"
					+ " ID_NEGERIPEGAWAI, "
					+ userId
					+ ", SYSDATE"
					+ " FROM TBLPHPPEGAWAILAPORANTANAH WHERE ID_PEGAWAILAPORANTANAH = '"
					+ idPegawaiLama + "'";

			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private Vector getSenaraiPegawai(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";
		Vector listPegawai = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEGAWAILAPORANTANAH FROM TBLPHPPEGAWAILAPORANTANAH WHERE ID_LAPORANTANAH = '"
					+ idLaporanTanah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PEGAWAILAPORANTANAH",
						rs.getString("ID_PEGAWAILAPORANTANAH") == null ? ""
								: rs.getString("ID_PEGAWAILAPORANTANAH"));
				listPegawai.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return listPegawai;
	}

	private void copyTBLPHPDOKUMEN(String idDokumenLama, long idLaporanTanah,
			Db db, String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPDOKUMEN
			r = new SQLRenderer();
			long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");

			sql = "INSERT INTO TBLPHPDOKUMEN"
					+ " (ID_DOKUMEN, CONTENT, NAMA_DOKUMEN, CATATAN, JENIS_MIME,"
					+ " NAMA_FAIL, ID_LAPORANTANAH, ID_MASUK, TARIKH_MASUK)"

					+ " SELECT " + idDokumen
					+ ", CONTENT, NAMA_DOKUMEN, CATATAN, JENIS_MIME,"
					+ " NAMA_FAIL, " + idLaporanTanah + ", " + userId
					+ ", SYSDATE" + " FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumenLama + "'";

			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private Vector getSenaraiDokumen(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";
		Vector listDokumen = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN FROM TBLPHPDOKUMEN WHERE ID_LAPORANTANAH = '"
					+ idLaporanTanah + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? ""
						: rs.getString("ID_DOKUMEN"));
				listDokumen.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return listDokumen;
	}

	public String getIdLaporanTanah(String idPermohonan, String idHakmilik)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_LAPORANTANAH FROM TBLPHPLAPORANTANAH WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "' AND ID_HAKMILIK = '" + idHakmilik + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_LAPORANTANAH");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private void deActivatePermohonanLama(String idPermohonan, Db db,
			String userId) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_AKTIF", "T");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, B.TARIKH_SURAT, B.TARIKH_TERIMA, B.NO_RUJ_SURAT, A.TAJUK_FAIL, B.TUJUAN, B.ID_PEMOHON"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B WHERE A.ID_FAIL = B.ID_FAIL AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("idPemohon",
						rs.getString("ID_PEMOHON") == null ? "" : rs
								.getString("ID_PEMOHON"));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("noRujukanSurat",
						rs.getString("NO_RUJ_SURAT") == null ? "" : rs
								.getString("NO_RUJ_SURAT").toUpperCase());
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void daftarEPU(String idFail, String idPermohonan,
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

			// TBLPHPMESYUARAT
			long idMesyuarat = DB.getNextID("TBLPHPMESYUARAT_SEQ");
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_MESYUARAT", "2");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610191"); // MESYUARAT EPU

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("33", "1610191")); // MESYUARAT
																				// EPU

			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
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

	public Vector getBeanMaklumatKeputusan() {
		return beanMaklumatKeputusan;
	}

	public void setBeanMaklumatKeputusan(Vector beanMaklumatKeputusan) {
		this.beanMaklumatKeputusan = beanMaklumatKeputusan;
	}

	public HakmilikInterface getHakmilik() {
		if (iHakmilik == null) {
			iHakmilik = new HakmilikBean();
		}
		return iHakmilik;
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getBeanMaklumatAgensi() {
		return beanMaklumatAgensi;
	}

	public void setBeanMaklumatAgensi(Vector beanMaklumatAgensi) {
		this.beanMaklumatAgensi = beanMaklumatAgensi;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}
}
