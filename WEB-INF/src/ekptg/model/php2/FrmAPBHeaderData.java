
package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

/**
 *
 */
public class FrmAPBHeaderData {

	private Vector beanMaklumatPermohonan = null;
	private Vector<Hashtable<String,String>> beanMaklumatPemohon = null;
	private static final Log log = LogFactory.getLog(FrmAPBHeaderData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatPermohonan(String idFail, HttpSession session) throws Exception {
		Db db = null;
		String sql = "";
		try {
			
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			Hashtable h;

			if ("".equals(idFail) && session.getAttribute("ID_FAIL") != null) {
				idFail = (String) session.getAttribute("ID_FAIL");
			}

			sql = "SELECT D.ID_NEGERI,A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN,B.NO_PERMOHONAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.TUJUAN,B.FLAG_AKTIF, C.ID_PEMOHON, C.ID_KATEGORIPEMOHON, C.NAMA,B.NO_RAYUAN,"
					+ " C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, C.NO_TEL_RUMAH, C.NO_TEL_PEJABAT, C.NO_FAX, B.ID_STATUS, E.KETERANGAN, F.FLAG_SAMBUNGAN,"
					+ " G.KETERANGAN AS NAMA_BANDAR"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPPMOHONNJDUALPERTAMA F, TBLRUJBANDAR G"
					+ " WHERE B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND C.ID_NEGERITETAP = D.ID_NEGERI AND B.ID_STATUS = E.ID_STATUS"
					+ " AND C.ID_BANDARTETAP = G.ID_BANDAR(+)"
					+ " AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			log.info("sql maklumat : "+sql);
			if (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("noRayuan",
						rs.getString("NO_RAYUAN") == null ? "0" : rs
								.getString("NO_RAYUAN"));
				h.put("urusan", "AKTA PELANTAR BENUA");
				h.put("subUrusan", "AKTA PELANTAR BENUA");
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN") == null ? "" : rs
								.getString("NO_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("perkara", rs.getString("TUJUAN") == null ? "" : rs
						.getString("TUJUAN").toUpperCase());
				h.put("tajukFail", rs.getString("TUJUAN") == null ? "" : rs
						.getString("TUJUAN").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs
						.getString("ID_PEMOHON").toUpperCase());
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
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs
						.getString("NAMA_BANDAR").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				if ("1".equals(rs.getString("ID_KATEGORIPEMOHON"))) {
					h.put("noTel", rs.getString("NO_TEL_RUMAH") == null ? ""
							: rs.getString("NO_TEL_RUMAH").toUpperCase());
				} else if ("2".equals(rs.getString("ID_KATEGORIPEMOHON"))) {
					h.put("noTel", rs.getString("NO_TEL_PEJABAT") == null ? ""
							: rs.getString("NO_TEL_PEJABAT").toUpperCase());
				} else {
					h.put("noTel", "");
				}
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX").toUpperCase());
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS").toUpperCase());
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs
						.getString("KETERANGAN").toUpperCase());
				h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs
						.getString("FLAG_AKTIF").toUpperCase());
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON").toUpperCase());
				h.put("flagSambung",
						rs.getString("FLAG_SAMBUNGAN") == null ? "" : rs
								.getString("FLAG_SAMBUNGAN"));

				beanMaklumatPermohonan.addElement(h);

				session.setAttribute("ID_FAIL", rs.getString("ID_FAIL"));

			} else {
				h = new Hashtable();
				h.put("idFail", "");
				h.put("noFail", "");
				h.put("urusan", "");
				h.put("idPermohonan", "");
				h.put("tarikhTerima", "");
				h.put("tarikhSurat", "");
				h.put("perkara", "");
				h.put("idPemohon", "");
				h.put("namaPemohon", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("negeri", "");
				h.put("noTel", "");
				h.put("noFax", "");
				h.put("idStatus", "");
				h.put("status", "");
				beanMaklumatPermohonan.addElement(h);

				session.removeAttribute("ID_FAIL");
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector<Hashtable<String,String>> setMaklumatPemohon(String idUser) throws Exception {
		Db db = null;
		String sql = "";
	
		try {
			beanMaklumatPemohon = new Vector<Hashtable<String,String>> ();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT U.USER_ID, U.USER_NAME, UPPER(UO.KATEGORI) AS KATEGORI, UO.NO_KP_BARU, UO.ALAMAT1, UO.ALAMAT2, UO.ALAMAT3, " +
					  " UO.POSKOD, UO.NO_FAX, UO.NO_HP, UO.EMEL, RB.KETERANGAN AS NAMA_BANDAR, RN.NAMA_NEGERI FROM USERS U," +
					  " USERS_ONLINE UO, TBLRUJNEGERI RN, TBLRUJBANDAR RB" +
					  " WHERE U.USER_ID = UO.USER_ID AND UO.ID_BANDAR = RB.ID_BANDAR(+) AND UO.ID_NEGERI = RN.ID_NEGERI" +
					  " AND U.USER_ID = '" + idUser + "'";
			log.info("header:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
	
			if (rs.next()) {
				h = new Hashtable();
				h.put("iduser", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("kategoriPemohon", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("namaPemohon", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				h.put("noPengenalan", rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTel", rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX").toUpperCase());
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
	
				beanMaklumatPemohon.addElement(h);
				bil++;
			}
			//session.setAttribute("ID_FAIL", rs.getString("ID_FAIL"));
			
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	
		return beanMaklumatPemohon;
	}	
	
	public boolean getFlagOpenDetail(String idStatus, int langkahSkrin)
			throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = false;
		int langkahStatus = 0;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT SS.LANGKAH FROM TBLRUJSTATUS S, TBLRUJSUBURUSANSTATUS SS WHERE S.ID_STATUS = SS.ID_STATUS AND SS.ID_SUBURUSAN = 57 AND S.ID_STATUS = '"
					+ idStatus + "' ORDER BY SS.LANGKAH ASC";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				langkahStatus = Integer.parseInt(rs.getString("LANGKAH"));
			}

			if (langkahSkrin <= langkahStatus)
				bool = true;

		} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}
	
	public void doBatalPermohonan(String idFail, String idPermohonan,
			String idStatusSebelum, String tarikhBatal, String txtSebab,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idSuburusan = "57";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610212");
			r.add("ID_STATUS_SEBELUM", idStatusSebelum);
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

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}
	
	
}
