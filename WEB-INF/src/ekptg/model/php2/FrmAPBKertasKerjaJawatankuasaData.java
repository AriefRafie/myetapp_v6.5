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

public class FrmAPBKertasKerjaJawatankuasaData {

	private Vector beanMaklumatKertasRingkas = null;
	private Vector listKoordinat = null;
	private Vector listPertindihan = null;
	private Vector beanMaklumatKawasanPermohonan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatKertasRingkas(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKertasRingkas = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PERMOHONAN, A.ID_KERTASKERJAAPB , A.NOTA, A.ULASAN_JAS, A.ULASAN_NAHRIM, A.ULASAN_JAB_LAUT, A.ULASAN_JPS, A.ULASAN_PTG,"
					+ " A.ULASAN_JAB_GEOSAINS, A.ULASAN_JAB_PERIKANAN, A.ULASAN_PUSAT_HIDROGRAFI, A.ULASAN_POLISMARIN, A.ULASAN_KEM_KEBUDAYAAN,B.LOKASI_PERMOHONAN, A.ULASAN_JUPEM "
					+ " FROM TBLPHPKERTASKERJAAPB A, TBLPHPPMOHONNJDUALPERTAMA B "
					+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.FLAG_KERTAS = '1' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("idKertasKerjaApb",
						rs.getString("ID_KERTASKERJAAPB") == null ? "" : rs
								.getString("ID_KERTASKERJAAPB"));
				h.put("lokasi", rs.getString("LOKASI_PERMOHONAN") == null ? ""
						: rs.getString("LOKASI_PERMOHONAN"));
				h.put("nota",
						rs.getString("NOTA") == null ? "" : rs
								.getString("NOTA"));
				h.put("ulasanJAS",
						rs.getString("ULASAN_JAS") == null ? "" : rs
								.getString("ULASAN_JAS"));
				h.put("ulasanNAHRIM",
						rs.getString("ULASAN_NAHRIM") == null ? "" : rs
								.getString("ULASAN_NAHRIM"));
				h.put("ulasanJabLaut",
						rs.getString("ULASAN_JAB_LAUT") == null ? "" : rs
								.getString("ULASAN_JAB_LAUT"));
				h.put("ulasanJPS",
						rs.getString("ULASAN_JPS") == null ? "" : rs
								.getString("ULASAN_JPS"));
				h.put("ulasanJabGeoSains",
						rs.getString("ULASAN_JAB_GEOSAINS") == null ? "" : rs
								.getString("ULASAN_JAB_GEOSAINS"));
				h.put("ulasanJabPerikanan",
						rs.getString("ULASAN_JAB_PERIKANAN") == null ? "" : rs
								.getString("ULASAN_JAB_PERIKANAN"));
				h.put("ulasanPusatHidrografi",
						rs.getString("ULASAN_PUSAT_HIDROGRAFI") == null ? ""
								: rs.getString("ULASAN_PUSAT_HIDROGRAFI"));
				h.put("ulasanPolisMarin",
						rs.getString("ULASAN_POLISMARIN") == null ? "" : rs
								.getString("ULASAN_POLISMARIN"));
				h.put("ulasanKemBudaya",
						rs.getString("ULASAN_KEM_KEBUDAYAAN") == null ? "" : rs
								.getString("ULASAN_KEM_KEBUDAYAAN"));
				h.put("ulasanJUPEM", rs.getString("ULASAN_JUPEM") == null ? ""
						: rs.getString("ULASAN_JUPEM"));
				h.put("ulasanPTG", rs.getString("ULASAN_PTG") == null ? ""
						: rs.getString("ULASAN_PTG"));

				beanMaklumatKertasRingkas.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateMaklumatKertasRingkas(String idPermohonan,
			String txtUlasanJUPEM, String txtUlasanJPS,
			String txtUlasanJabGeoSains, String txtUlasanPusatHidrografi,
			String txtUlasanJabPerikanan, String txtJabLaut,
			String txtUlasanJAS, String txtUlasanPTG,
			String txtNotaTambahan,
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

			// TBLPHPKERTASKERJAAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			r.add("ULASAN_JUPEM", txtUlasanJUPEM);
			r.add("ULASAN_JPS", txtUlasanJPS);
			r.add("ULASAN_JAB_GEOSAINS", txtUlasanJabGeoSains);
			r.add("ULASAN_PUSAT_HIDROGRAFI", txtUlasanPusatHidrografi);
			r.add("ULASAN_JAB_PERIKANAN", txtUlasanJabPerikanan);
			r.add("ULASAN_JAB_LAUT", txtJabLaut);
			r.add("ULASAN_JAS", txtUlasanJAS);
			r.add("ULASAN_PTG", txtUlasanPTG);
			r.add("NOTA", txtNotaTambahan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");

			stmt.executeUpdate(sql);
			conn.commit();
			
			AuditTrail.logActivity("1610235", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");

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
			r.add("ID_STATUS", "1610201"); // MESYUARAT

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

			// GET DATA LAMA FROM TBLPFDFAIL
			sql = "SELECT A.ID_SUBURUSAN FROM TBLPFDFAIL A "
					+ "WHERE A.ID_FAIL = '" + idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS",
					getIdSuburusanstatus(rs.getString("ID_SUBURUSAN"),
							"1610201")); // MESYUARAT

			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");

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

	public void setSenaraiKoordinat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKoordinat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT TBLPHPKOORDINATPERMOHONAN.ID_KOORDINATPERMOHONAN, TBLPHPKOORDINATPERMOHONAN.LABEL_TITIK, TBLPHPKOORDINATPERMOHONAN.DARJAH_U,TBLPHPKOORDINATPERMOHONAN.DARJAH_T, "
					+ "TBLPHPKOORDINATPERMOHONAN.MINIT_U AS MINIT_U, " 
					+ "TBLPHPKOORDINATPERMOHONAN.MINIT_T AS MINIT_T, " 
					+ "TBLPHPKOORDINATPERMOHONAN.SAAT_U AS SAAT_U, " 
					+ "TBLPHPKOORDINATPERMOHONAN.SAAT_T AS SAAT_T " 
					+ "FROM TBLPHPKOORDINATPERMOHONAN "
					+ "WHERE "
					+ "TBLPHPKOORDINATPERMOHONAN.ID_ULASANTEKNIKAL IS NULL "
					+ "AND TBLPHPKOORDINATPERMOHONAN.FLAG_HISTORY = 'N' "
					+ "AND TBLPHPKOORDINATPERMOHONAN.ID_PERMOHONAN = '"
					+ idPermohonan + "'" + " ORDER BY ID_KOORDINATPERMOHONAN ";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idKoordinat", rs.getString("ID_KOORDINATPERMOHONAN"));
				h.put("labelTitik", rs.getString("LABEL_TITIK") == null ? ""
						: rs.getString("LABEL_TITIK"));
				h.put("darjahU",
						rs.getString("DARJAH_U") == null ? "" : rs
								.getString("DARJAH_U"));
				h.put("minitU",
						rs.getString("MINIT_U") == null ? "" : rs
								.getString("MINIT_U"));
				h.put("saatU",
						rs.getString("SAAT_U") == null ? "" : rs
								.getString("SAAT_U"));
				h.put("darjahT",
						rs.getString("DARJAH_T") == null ? "" : rs
								.getString("DARJAH_T"));
				h.put("minitT",
						rs.getString("MINIT_T") == null ? "" : rs
								.getString("MINIT_T"));
				h.put("saatT",
						rs.getString("SAAT_T") == null ? "" : rs
								.getString("SAAT_T"));

				listKoordinat.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiPertindihan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPertindihan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT TBLPHPFAILAPBBERTINDIH.ID_FAILAPBBERTINDIH, TBLPHPFAILAPBBERTINDIH.BERTINDIH_DENGAN_NOFAIL, TBLPHPFAILAPBBERTINDIH.NAMA_SYARIKAT_TINDIH,TBLPHPFAILAPBBERTINDIH.FLAG_JENIS_PERTINDIHAN, "
					+ "TBLPHPFAILAPBBERTINDIH.ID_STATUS "
					+ "FROM TBLPHPFAILAPBBERTINDIH "
					+ "WHERE "
					+ "TBLPHPFAILAPBBERTINDIH.ID_ULASANTEKNIKAL IS NULL "
					+ "AND TBLPHPFAILAPBBERTINDIH.ID_PERMOHONAN = '"
					+ idPermohonan + "'" + " ORDER BY ID_FAILAPBBERTINDIH ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPertindihan", rs.getString("ID_FAILAPBBERTINDIH"));
				h.put("noFail",
						rs.getString("BERTINDIH_DENGAN_NOFAIL") == null ? ""
								: rs.getString("BERTINDIH_DENGAN_NOFAIL"));
				h.put("namaSykt",
						rs.getString("NAMA_SYARIKAT_TINDIH") == null ? "" : rs
								.getString("NAMA_SYARIKAT_TINDIH"));
				if (rs.getString("FLAG_JENIS_PERTINDIHAN") != null
						&& rs.getString("FLAG_JENIS_PERTINDIHAN").trim()
								.length() != 0) {
					if ("1".equals(rs.getString("FLAG_JENIS_PERTINDIHAN"))) {
						h.put("jenisPertindihan", "SELURUH");
					} else if ("2".equals(rs
							.getString("FLAG_JENIS_PERTINDIHAN"))) {
						h.put("jenisPertindihan", "SEBAHAGIAN");
					} else {
						h.put("jenisPertindihan", "");
					}
				} else {
					h.put("jenisPertindihan", "");
				}
				if (rs.getString("ID_STATUS") != null
						&& rs.getString("ID_STATUS").trim().length() != 0) {
					if ("1".equals(rs.getString("ID_STATUS"))) {
						h.put("status", "LULUS SECARA DASAR");
					} else if ("2".equals(rs.getString("ID_STATUS"))) {
						h.put("status", "TELAH DIKELUARKAN LESEN");
					} else if ("3".equals(rs.getString("ID_STATUS"))) {
						h.put("status", "DITOLAK");
					} else if ("4".equals(rs.getString("ID_STATUS"))) {
						h.put("status", "LAIN-LAIN");
					} else {
						h.put("status", "");
					}
				} else {
					h.put("status", "");
				}

				listPertindihan.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatKawasanPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKawasanPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("B.NAMA_NEGERI");			
			r.add("A.LUAS_DIPOHON");
			r.add("C.KETERANGAN");
			r.add("A.ID_NEGERI_PERAIRAN", r.unquote("B.ID_NEGERI"));
			r.add("A.ID_UNITLUAS", r.unquote("C.ID_LUAS(+)"));
			r.add("A.ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPPMOHONNJDUALPERTAMA A, TBLRUJNEGERI B, TBLRUJLUAS C");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("negeriPerairan",
						rs.getString("NAMA_NEGERI") == null ? ""
								: rs.getString("NAMA_NEGERI"));
				h.put("luas",
						rs.getString("LUAS_DIPOHON") == null ? "" : rs
								.getString("LUAS_DIPOHON"));
				h.put("unitLuas", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				
				beanMaklumatKawasanPermohonan.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatKertasRingkas() {
		return beanMaklumatKertasRingkas;
	}

	public Vector getListKoordinat() {
		return listKoordinat;
	}

	public void setListKoordinat(Vector listKoordinat) {
		this.listKoordinat = listKoordinat;
	}

	public Vector getListPertindihan() {
		return listPertindihan;
	}

	public void setListPertindihan(Vector listPertindihan) {
		this.listPertindihan = listPertindihan;
	}

	public Vector getBeanMaklumatKawasanPermohonan() {
		return beanMaklumatKawasanPermohonan;
	}

	public void setBeanMaklumatKawasanPermohonan(
			Vector beanMaklumatKawasanPermohonan) {
		this.beanMaklumatKawasanPermohonan = beanMaklumatKawasanPermohonan;
	}
}
