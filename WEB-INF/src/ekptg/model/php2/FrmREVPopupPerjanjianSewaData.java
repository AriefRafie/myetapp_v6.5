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
public class FrmREVPopupPerjanjianSewaData {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Vector beanMaklumatPerjanjian = null;

	public void setMaklumatPerjanjian(String idPerjanjian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPerjanjian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_RUJUKAN, TARIKH_MULA, TEMPOH, TARIKH_TAMAT, TARIKH_MULA_DASAR, TEMPOH_DASAR, TARIKH_TAMAT_DASAR, BAYARAN, DEPOSIT, FLAG_AKTIF, FLAG_LULUSDASAR, FLAG_PERJANJIAN,"
					+ " CATATAN, MOD_CAJ_SEWAAN FROM TBLPHPBAYARANPERLUDIBAYAR"
					+ " WHERE ID_BAYARANPERLUDIBAYAR = '" + idPerjanjian + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("noSiri", rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA")));
				h.put("tempoh", rs.getString("TEMPOH") == null ? "" : rs.getString("TEMPOH"));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMAT") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMAT")));
				h.put("tarikhMulaDasar", rs.getDate("TARIKH_MULA_DASAR") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA_DASAR")));
				h.put("tempohDasar", rs.getString("TEMPOH_DASAR") == null ? "" : rs.getString("TEMPOH_DASAR"));
				h.put("tarikhTamatDasar", rs.getDate("TARIKH_TAMAT_DASAR") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMAT_DASAR")));
				h.put("kadarSewa", rs.getString("BAYARAN") == null ? "" : Utils.format2Decimal(rs.getDouble("BAYARAN")));
				h.put("cagaran", rs.getString("DEPOSIT") == null ? "" : Utils.format2Decimal(rs.getDouble("DEPOSIT")));
				h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
				h.put("flagKelulusanDasar", rs.getString("FLAG_LULUSDASAR") == null ? "T" : rs.getString("FLAG_LULUSDASAR"));
				h.put("flagPerjanjian", rs.getString("FLAG_PERJANJIAN") == null ? "T" : rs.getString("FLAG_PERJANJIAN"));
				h.put("modCajSewaan", rs.getString("MOD_CAJ_SEWAAN") == null ? "1" : rs.getString("MOD_CAJ_SEWAAN"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				beanMaklumatPerjanjian.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public int getBilPerjanjian(String idHasil) throws Exception {
		Db db = null;
		String sql = "";
		int bil = 0;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT COUNT(*) AS BIL FROM TBLPHPBAYARANPERLUDIBAYAR WHERE FLAG_PERJANJIAN = 'U' AND ID_HASIL = '" + idHasil + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bil = rs.getInt("BIL");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return bil;
	}

	public String savePerjanjian(String idFail, String idHasil, String noSiri,
			String txtTarikhMula, String tempoh, String txtTarikhTamat,
			String kadarSewa, String cagaran, String flagKelulusanDasar, String catatan,
			String modCajSewaan, String flagPerjanjian, String flagSkrin, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idBayaranPerluDibayarString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPBAYARANPERLUDIBAYAR
			r = new SQLRenderer();
			long idBayaranPerluDibayar = DB.getNextID("TBLPHPBAYARANPERLUDIBAYAR_SEQ");
			idBayaranPerluDibayarString = String.valueOf(idBayaranPerluDibayar);
			r.add("ID_BAYARANPERLUDIBAYAR", idBayaranPerluDibayar);
			r.add("ID_HASIL", idHasil);
			r.add("ID_FAIL", idFail);
			if (noSiri != null && noSiri.length() > 0)
				r.add("NO_RUJUKAN", noSiri);

			if ("U".equals(flagSkrin)) {
				r.add("FLAG_LULUSDASAR", flagKelulusanDasar);
				r.add("FLAG_PERJANJIAN", "U");
				if (!"Y".equals(flagKelulusanDasar)) {
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
					r.add("MOD_CAJ_SEWAAN", modCajSewaan);
				} else {
					r.add("TARIKH_MULA", r.unquote(null));
					r.add("TEMPOH", r.unquote(null));
					r.add("TARIKH_TAMAT", r.unquote(null));
					r.add("BAYARAN", Utils.RemoveComma(kadarSewa));
					r.add("DEPOSIT", Utils.RemoveComma(cagaran));
					r.add("MOD_CAJ_SEWAAN", r.unquote(null));
				}
			} else {
				r.add("FLAG_LULUSDASAR", "T");
				r.add("FLAG_PERJANJIAN", flagPerjanjian);
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
				if (!"3".equals(flagPerjanjian)) {
					r.add("BAYARAN", Utils.RemoveComma(kadarSewa));
				} else {
					r.add("BAYARAN", "0");
				}
				r.add("DEPOSIT", r.unquote(null));
				r.add("MOD_CAJ_SEWAAN", r.unquote(null));
			}

			r.add("FLAG_AKTIF", "Y");
			r.add("STATUS", "1");
			r.add("CATATAN", catatan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPBAYARANPERLUDIBAYAR");
			stmt.executeUpdate(sql);
			conn.commit();

			UtilHasil.kemaskiniRekodPerjanjianDanAkaun(idHasil);

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
		return idBayaranPerluDibayarString;
	}

	public void doHapus(String idPerjanjian, String idHasil, String idFail)
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

			// TBLPHPBAYARANPERLUDIBAYAR
			r = new SQLRenderer();
			r.add("ID_BAYARANPERLUDIBAYAR", idPerjanjian);
			sql = r.getSQLDelete("TBLPHPBAYARANPERLUDIBAYAR");
			stmt.executeUpdate(sql);
			conn.commit();

			UtilHasil.kemaskiniRekodPerjanjianDanAkaun(idHasil);

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

	public void kemaskiniPerjanjian(String idFail, String idHasil, String idPerjanjian, String noSiri,
			String txtTarikhMula, String tempoh, String txtTarikhTamat,
			String kadarSewa, String cagaran, String flagKelulusanDasar, String catatan,
			String modCajSewaan, String flagPerjanjian, String flagSkrin, HttpSession session) throws Exception {

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

			// TBLPHPBAYARANPERLUDIBAYAR
			r = new SQLRenderer();
			r.update("ID_BAYARANPERLUDIBAYAR", idPerjanjian);
			r.add("ID_HASIL", idHasil);
			r.add("ID_FAIL", idFail);
			if (noSiri != null && noSiri.length() > 0)
				r.add("NO_RUJUKAN", noSiri);

			if ("U".equals(flagSkrin)) {
				r.add("FLAG_LULUSDASAR", flagKelulusanDasar);
				r.add("FLAG_PERJANJIAN", "U");
				if (!"Y".equals(flagKelulusanDasar)) {
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
					r.add("MOD_CAJ_SEWAAN", modCajSewaan);
				} else {
					r.add("TARIKH_MULA", r.unquote(null));
					r.add("TEMPOH", r.unquote(null));
					r.add("TARIKH_TAMAT", r.unquote(null));
					r.add("BAYARAN", Utils.RemoveComma(kadarSewa));
					r.add("DEPOSIT", Utils.RemoveComma(cagaran));
					r.add("MOD_CAJ_SEWAAN", r.unquote(null));
				}
			} else {
				r.add("FLAG_LULUSDASAR", "T");
				r.add("FLAG_PERJANJIAN", flagPerjanjian);
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
				if (!"3".equals(flagPerjanjian)) {
					r.add("BAYARAN", Utils.RemoveComma(kadarSewa));
				} else {
					r.add("BAYARAN", "0");
				}
				r.add("DEPOSIT", r.unquote(null));
				r.add("MOD_CAJ_SEWAAN", r.unquote(null));
			}

			r.add("FLAG_AKTIF", "Y");
			r.add("STATUS", "1");
			r.add("CATATAN", catatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPBAYARANPERLUDIBAYAR");
			stmt.executeUpdate(sql);
			conn.commit();

			UtilHasil.kemaskiniRekodPerjanjianDanAkaun(idHasil);

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

	public Vector getBeanMaklumatPerjanjian() {
		return beanMaklumatPerjanjian;
	}

	public void setBeanMaklumatPerjanjian(Vector beanMaklumatPerjanjian) {
		this.beanMaklumatPerjanjian = beanMaklumatPerjanjian;
	}
}
