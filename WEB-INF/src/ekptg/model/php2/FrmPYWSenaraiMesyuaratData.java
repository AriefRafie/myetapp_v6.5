
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
import ekptg.helpers.File;
import ekptg.helpers.Utils;

/**
 * @author nurulain
 * 
 */
public class FrmPYWSenaraiMesyuaratData {

	private Vector beanMaklumatPemohon = null;
	private Vector senaraiMesyuarat = null;
	private Vector beanMaklumatMesyuarat = null;
	private Vector beanMaklumatKehadiran = null;
	private Vector listKehadiran = null;
	private Vector listPermohonanBaharu = null;
	private static final Log myLog = LogFactory.getLog(FrmPYWSenaraiMesyuaratData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianSenaraiMesyuarat(String tajukMesyuarat, String bilMesyuarat, String tarikhMesyuarat, 
			String userId, String userRole)
			throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT B.ID_PERMOHONAN, C.TAJUK AS TAJUK_MESYUARAT, C.BIL_MESYUARAT, C.TARIKH_MESYUARAT, C.ID_LOKASI, E.LOKASI "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPMESYUARAT C, TBLPHPMESYUARATPERMOHONAN D, TBLPFDRUJLOKASIMESYUARAT E "
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN AND C.ID_MESYUARAT = D.ID_MESYUARAT "
				+ " AND C.ID_LOKASI = E.ID_LOKASI AND C.ID_URUSAN = 4 ";

			// tajukMesyuarat
			if (tajukMesyuarat != null) {
				if (!tajukMesyuarat.trim().equals("")) {
					sql = sql + " AND UPPER(C.TAJUK) LIKE '%' ||'"
							+ tajukMesyuarat.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			// bilMesyuarat
			if (bilMesyuarat != null) {
				if (!bilMesyuarat.trim().equals("")) {
					sql = sql + " AND UPPER(C.BIL_MESYUARAT) LIKE '%' ||'"
							+ bilMesyuarat.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhMesyuarat
			if (tarikhMesyuarat != null) {
				if (!tarikhMesyuarat.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(C.TARIKH_MESYUARAT,'DD-MM-YYYY') = '"
							+ sdf1.format(sdf.parse(tarikhMesyuarat))
									.toUpperCase() + "'";
				}
			}
			
			sql = sql + " ORDER BY C.TARIKH_MESYUARAT DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("tajukMesyuarat", rs.getString("TAJUK_MESYUARAT") == null ? "" : rs
						.getString("TAJUK_MESYUARAT").toUpperCase());
				h.put("bilMesyuarat", rs.getString("BIL_MESYUARAT") == null ? "" : rs
						.getString("BIL_MESYUARAT").toUpperCase());
				h.put("tarikhMesyuarat", rs.getDate("TARIKH_MESYUARAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MESYUARAT")));
				senaraiMesyuarat.addElement(h);
				bil++;

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

			sql = "SELECT TAJUK, BIL_MESYUARAT, TARIKH_MESYUARAT, JAM_DARI, MINIT_DARI, JAM_HINGGA, MINIT_HINGGA, ID_LOKASI, CATATAN"
					+ " FROM TBLPHPMESYUARAT WHERE ID_MESYUARAT = '"
					+ idMesyuarat + "'";
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
				h.put("catatanMesyuarat",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatMesyuarat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String simpanMesyuarat(String tarikhMesyuarat, String bilMesyuarat, 
			String tajukMesyuarat, String idJamDari, String idMinitDari, String idJamHingga,
			String idMinitHingga, String catatan, String idLokasi,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
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
			r.add("TAJUK", tajukMesyuarat);
			r.add("BIL_MESYUARAT", bilMesyuarat);
			if (!"".equals(tarikhMesyuarat)) {
				r.add("TARIKH_MESYUARAT",
						r.unquote("to_date('" + tarikhMesyuarat
								+ "','dd/MM/yyyy')"));
			}
			r.add("JAM_DARI", idJamDari);
			r.add("MINIT_DARI", idMinitDari);
			r.add("JAM_HINGGA", idJamHingga);
			r.add("MINIT_HINGGA", idMinitHingga);
			r.add("ID_LOKASI", idLokasi);
			r.add("CATATAN", catatan);
			r.add("FLAG_MESYUARAT", "1");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"MESYUARAT [" + idMesyuarat
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
	
	public void simpanKemaskiniMesyuarat(String idMesyuarat, String tarikhMesyuarat,
			String bilMesyuarat, String tajuk, String idJamDari,
			String idMinitDari, String idJamHingga, String idMinitHingga,
			String catatan, String idLokasi, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPMESYUARAT
			r.update("ID_MESYUARAT", idMesyuarat);
			r.add("TAJUK", tajuk);
			r.add("BIL_MESYUARAT", bilMesyuarat);
			if (!"".equals(tarikhMesyuarat)) {
				r.add("TARIKH_MESYUARAT",
						r.unquote("to_date('" + tarikhMesyuarat
								+ "','dd/MM/yyyy')"));
			}
			r.add("JAM_DARI", idJamDari);
			r.add("MINIT_DARI", idMinitDari);
			r.add("JAM_HINGGA", idJamHingga);
			r.add("MINIT_HINGGA", idMinitHingga);
			r.add("ID_LOKASI", idLokasi);
			r.add("CATATAN", catatan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPMESYUARAT");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "UPD",
					"FAIL [" + idMesyuarat
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
	
	public void setMaklumatKehadiran(String idKehadiran) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKehadiran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KEHADIRAN, NAMA_PEGAWAI, NAMA_AGENSI, NO_TELEFON, EMAIL, NAMA_JAWATAN, FLAG_PENGERUSI"
					+ " FROM TBLPHPKEHADIRANMESY WHERE ID_KEHADIRAN = '"
					+ idKehadiran + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKehadiran", rs.getString("ID_KEHADIRAN") == null ? ""
						: rs.getString("ID_KEHADIRAN"));
				h.put("nama",
						rs.getString("NAMA_PEGAWAI") == null ? "" : rs
								.getString("NAMA_PEGAWAI"));
				h.put("agensi",
						rs.getString("NAMA_AGENSI") == null ? "" : rs
								.getString("NAMA_AGENSI"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				h.put("email",
						rs.getString("EMAIL") == null ? "" : rs
								.getString("EMAIL"));
				h.put("jawatan",
						rs.getString("NAMA_JAWATAN") == null ? "" : rs
								.getString("NAMA_JAWATAN"));
				h.put("flagPengerusi",
						rs.getString("FLAG_PENGERUSI") == null ? "" : rs
								.getString("FLAG_PENGERUSI"));
				beanMaklumatKehadiran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiKehadiran(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listKehadiran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_KEHADIRAN");
			r.add("NAMA_PEGAWAI");
			r.add("NAMA_AGENSI");
			r.add("NAMA_JAWATAN");
			r.add("NO_TELEFON");
			r.add("EMAIL");
			r.add("FLAG_PENGERUSI");
			r.add("ID_MESYUARAT", idMesyuarat);

			sql = r.getSQLSelect("TBLPHPKEHADIRANMESY", "FLAG_PENGERUSI DESC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idKehadiran", rs.getString("ID_KEHADIRAN") == null ? ""
						: rs.getString("ID_KEHADIRAN"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("namaAgensi", rs.getString("NAMA_AGENSI") == null ? ""
						: rs.getString("NAMA_AGENSI"));
				h.put("namaJawatan", rs.getString("NAMA_JAWATAN") == null ? ""
						: rs.getString("NAMA_JAWATAN"));
				h.put("noTelefon",
						rs.getString("NO_TELEFON") == null ? "" : rs
								.getString("NO_TELEFON"));
				h.put("email",
						rs.getString("EMAIL") == null ? "" : rs
								.getString("EMAIL"));
				h.put("flagPengerusi",
						rs.getString("FLAG_PENGERUSI") == null ? "" : rs
								.getString("FLAG_PENGERUSI"));
				listKehadiran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiPermohonanBaharu(String idMesyuarat) throws Exception {
		
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

	public Vector getSenaraiMesyuarat() {
		return senaraiMesyuarat;
	}

	public void setSenaraiMesyuarat(Vector senaraiMesyuarat) {
		this.senaraiMesyuarat = senaraiMesyuarat;
	}
	
	public Vector getBeanMaklumatMesyuarat() {
		return beanMaklumatMesyuarat;
	}

	public void setBeanMaklumatMesyuarat(Vector beanMaklumatMesyuarat) {
		this.beanMaklumatMesyuarat = beanMaklumatMesyuarat;
	}
	
	public Vector getBeanMaklumatKehadiran() {
		return beanMaklumatKehadiran;
	}
	
	public Vector getListKehadiran() {
		return listKehadiran;
	}
	
	public Vector getListPermohonanBaharu() {
		return listPermohonanBaharu;
	}
}