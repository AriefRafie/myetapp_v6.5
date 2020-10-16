
package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
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

	private Vector senaraiMesyuarat = null;
	private Vector beanMaklumatMesyuarat = null;
	private Vector beanMaklumatKehadiran = null;
	private Vector listKehadiran = null;
	private Vector listPermohonanBaharu = null;
	private Vector listPermohonanLanjut = null;
	private Vector listPermohonanLain = null;
	private Vector listImejan = null;
	private Vector beanMaklumatImejan = null;
	
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

			sql = "SELECT A.ID_MESYUARAT, A.TAJUK, A.BIL_MESYUARAT,A.TARIKH_MESYUARAT, B.LOKASI, A.STATUS_MESYUARAT "
				+ "FROM TBLPHPMESYUARAT A, TBLPFDRUJLOKASIMESYUARAT B "
				+ "WHERE A.ID_LOKASI = B.ID_LOKASI "
				+ "AND ID_URUSAN='4'";
			
			// tajukMesyuarat
			if (tajukMesyuarat != null) {
				if (!tajukMesyuarat.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK) LIKE '%' ||'"
							+ tajukMesyuarat.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			// bilMesyuarat
			if (bilMesyuarat != null) {
				if (!bilMesyuarat.trim().equals("")) {
					sql = sql + " AND UPPER(A.BIL_MESYUARAT) LIKE '%' ||'"
							+ bilMesyuarat.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

			// tarikhMesyuarat
			if (tarikhMesyuarat != null) {
				if (!tarikhMesyuarat.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(A.TARIKH_MESYUARAT,'dd-MM-yyyy') = '"
							+ sdf1.format(sdf.parse(tarikhMesyuarat))
									.toUpperCase() + "'";
				}
			}
			
			//sql = sql + " ORDER BY C.TARIKH_MESYUARAT DESC";
			sql = sql + " ORDER BY A.TARIKH_MESYUARAT DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idMesyuarat",
						rs.getString("ID_MESYUARAT") == null ? "" : rs
								.getString("ID_MESYUARAT"));
				h.put("tajukMesyuarat",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("bilMesyuarat", rs.getString("BIL_MESYUARAT") == null ? "" : rs
						.getString("BIL_MESYUARAT").toUpperCase());
				h.put("tarikhMesyuarat", rs.getDate("TARIKH_MESYUARAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MESYUARAT")));
				h.put("lokasiMesyuarat", rs.getString("LOKASI") == null ? "" : rs
						.getString("LOKASI").toUpperCase());
				
				String status=rs.getString("STATUS_MESYUARAT");
				String statusMesyuarat="";
				
				if(status.equalsIgnoreCase("1")){
					statusMesyuarat="BARU";
					h.put("statusMesyuarat", statusMesyuarat);
				}else{
					statusMesyuarat="SELESAI";
					h.put("statusMesyuarat", statusMesyuarat);
				}
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

			sql = "SELECT TAJUK, BIL_MESYUARAT, TARIKH_MESYUARAT, JAM_DARI, MINIT_DARI, JAM_HINGGA, MINIT_HINGGA, ID_LOKASI, CATATAN,STATUS_MESYUARAT"
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
				h.put("statusMesyuarat",
						rs.getString("STATUS_MESYUARAT") == null ? "" : rs
								.getString("STATUS_MESYUARAT"));
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
			r.add("ID_URUSAN", "4");
			r.add("STATUS_MESYUARAT", "1");
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
			r.add("ID_URUSAN", "4");
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
		Db db = null;
		String sql = "";

		try {
			listPermohonanBaharu = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();


			sql = "SELECT A.ID_MESYUARAT_PERMOHONAN, D.NO_FAIL,E.NAMA, A.FLAG_JENIS_PERMOHONAN, A.FLAG_SYOR , A.CATATAN, C.ID_PERMOHONAN, D.ID_FAIL, F.ID_JENIS_PERMOHONAN "
				+ "FROM TBLPHPMESYUARATPERMOHONAN A, TBLPHPMESYUARAT B, TBLPERMOHONAN C, TBLPFDFAIL D, TBLPHPPEMOHON E, TBLPHPPERMOHONANSEWA F "
				+ "WHERE A.FLAG_JENIS_PERMOHONAN = 'B' AND A.ID_MESYUARAT = B.ID_MESYUARAT AND A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_FAIL = D.ID_FAIL "
				+ "AND C.ID_PERMOHONAN = F.ID_PERMOHONAN AND F.ID_JENIS_PERMOHONAN = 1 AND C.ID_PEMOHON = E.ID_PEMOHON  AND A.ID_MESYUARAT = '"+idMesyuarat+"'"	
				+ " ORDER BY A.ID_MESYUARAT_PERMOHONAN ASC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id",
						rs.getString("ID_MESYUARAT_PERMOHONAN") == null ? "" : rs
								.getString("ID_MESYUARAT_PERMOHONAN"));
				h.put("noFailPermohonan",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("namaPemohon",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("jenisPermohonan","PERMOHONAN BAHARU");
				
				h.put("flagKeputusan",
						rs.getString("FLAG_SYOR") == null ? "" : rs
								.getString("FLAG_SYOR"));
				h.put("catatanKeputusan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				listPermohonanBaharu.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiPermohonanLanjut(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPermohonanLanjut = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();


			sql = "SELECT A.ID_MESYUARAT_PERMOHONAN, D.NO_FAIL,E.NAMA, A.FLAG_JENIS_PERMOHONAN, A.FLAG_SYOR , A.CATATAN, C.ID_PERMOHONAN, D.ID_FAIL, F.ID_JENIS_PERMOHONAN "
				+ "FROM TBLPHPMESYUARATPERMOHONAN A, TBLPHPMESYUARAT B, TBLPERMOHONAN C, TBLPFDFAIL D, TBLPHPPEMOHON E, TBLPHPPERMOHONANSEWA F "
				+ "WHERE A.FLAG_JENIS_PERMOHONAN = 'L' AND A.ID_MESYUARAT = B.ID_MESYUARAT AND A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_FAIL = D.ID_FAIL "
				+ "AND C.ID_PERMOHONAN = F.ID_PERMOHONAN AND F.ID_JENIS_PERMOHONAN = 2 AND C.ID_PEMOHON = E.ID_PEMOHON  AND A.ID_MESYUARAT = '"+idMesyuarat+"'"	
				+ " ORDER BY A.ID_MESYUARAT_PERMOHONAN ASC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id",
						rs.getString("ID_MESYUARAT_PERMOHONAN") == null ? "" : rs
								.getString("ID_MESYUARAT_PERMOHONAN"));
				h.put("noFailPermohonan",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("namaPemohon",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("jenisPermohonan","PERMOHONAN PERLANJUTAN");
				h.put("flagKeputusan",
						rs.getString("FLAG_SYOR") == null ? "" : rs
								.getString("FLAG_SYOR"));
				h.put("catatanKeputusan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				listPermohonanLanjut.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiPermohonanLain(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPermohonanLain = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_MESYUARAT_PERMOHONAN, D.NO_FAIL,E.NAMA, A.FLAG_JENIS_PERMOHONAN, A.FLAG_SYOR , A.CATATAN, C.ID_PERMOHONAN, D.ID_FAIL, F.ID_JENIS_PERMOHONAN "
				+ "FROM TBLPHPMESYUARATPERMOHONAN A, TBLPHPMESYUARAT B, TBLPERMOHONAN C, TBLPFDFAIL D, TBLPHPPEMOHON E, TBLPHPPERMOHONANSEWA F "
				+ "WHERE A.FLAG_JENIS_PERMOHONAN = 'LL' AND A.ID_MESYUARAT = B.ID_MESYUARAT AND A.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_FAIL = D.ID_FAIL "
				+ "AND C.ID_PERMOHONAN = F.ID_PERMOHONAN AND F.ID_JENIS_PERMOHONAN IN ('1','2') AND C.ID_PEMOHON = E.ID_PEMOHON  AND A.ID_MESYUARAT = '"+idMesyuarat+"' "	
				+ "AND C.ID_STATUS = '1610206'"
				+ " ORDER BY A.ID_MESYUARAT_PERMOHONAN ASC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id",
						rs.getString("ID_MESYUARAT_PERMOHONAN") == null ? "" : rs
								.getString("ID_MESYUARAT_PERMOHONAN"));
				h.put("noFailPermohonan",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("namaPemohon",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("jenisPermohonan","PERMOHONAN LAIN-LAIN");
				h.put("flagKeputusan",
						rs.getString("FLAG_SYOR") == null ? "" : rs
								.getString("FLAG_SYOR"));
				h.put("catatanKeputusan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				listPermohonanLain.addElement(h);
				bil++;
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
	
	public void listSenaraiMesyuarat()
			throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			//sql = "SELECT TAJUK, BIL_MESYUARAT,TARIKH_MESYUARAT, ID_LOKASI FROM TBLPHPMESYUARAT WHERE ID_URUSAN='4'";
			sql = "SELECT A.ID_MESYUARAT, A.TAJUK, A.BIL_MESYUARAT,A.TARIKH_MESYUARAT, B.LOKASI, A.STATUS_MESYUARAT " +
			      "FROM TBLPHPMESYUARAT A, TBLPFDRUJLOKASIMESYUARAT B "+
			      "WHERE A.ID_LOKASI = B.ID_LOKASI "+
			      "AND ID_URUSAN='4'";
		
			sql = sql + " ORDER BY TARIKH_MESYUARAT DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idMesyuarat",
						rs.getString("ID_MESYUARAT") == null ? "" : rs
								.getString("ID_MESYUARAT"));
				h.put("tajukMesyuarat",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("bilMesyuarat", rs.getString("BIL_MESYUARAT") == null ? "" : rs
						.getString("BIL_MESYUARAT").toUpperCase());
				h.put("tarikhMesyuarat", rs.getDate("TARIKH_MESYUARAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MESYUARAT")));
				h.put("lokasiMesyuarat", rs.getString("LOKASI") == null ? "" : rs
						.getString("LOKASI").toUpperCase());
				
				String status=rs.getString("STATUS_MESYUARAT");
				String statusMesyuarat="";
				
				if(status.equalsIgnoreCase("1")){
					statusMesyuarat="BARU";
					h.put("statusMesyuarat", statusMesyuarat);
				}else{
					statusMesyuarat="SELESAI";
					h.put("statusMesyuarat", statusMesyuarat);
				}
				senaraiMesyuarat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	public void saveKehadiran(int i, String idMesyuarat, String nama,
			String agensi, String jawatan, String noTel,String email, String flagPengerusi,
			HttpSession session) throws Exception {

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

			if (!"".equals(flagPengerusi)) {
				if (Integer.parseInt(flagPengerusi) == (i + 1)) {
					resetFlagPengerusi(idMesyuarat, db, userId);
				}
			}

			// TBLPHPKEHADIRANMESY
			long idKehadiran = DB.getNextID("TBLPHPKEHADIRANMESY_SEQ");
			r.add("ID_KEHADIRAN", idKehadiran);
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("NAMA_PEGAWAI", nama);
			r.add("NAMA_AGENSI", agensi);
			r.add("NAMA_JAWATAN", jawatan);
			r.add("NO_TELEFON", noTel);
			r.add("EMAIL", email);
			if (!"".equals(flagPengerusi)) {
				if (Integer.parseInt(flagPengerusi) == (i + 1)) {
					r.add("FLAG_PENGERUSI", "Y");
				} else {
					r.add("FLAG_PENGERUSI", "T");
				}
			} else {
				r.add("FLAG_PENGERUSI", "T");
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKEHADIRANMESY");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"FAIL [" + idKehadiran + "] DIDAFTARKAN");

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
	private void resetFlagPengerusi(String idMesyuarat, Db db, String userId)
			throws Exception {
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPKEHADIRANMESY
			r.update("ID_MESYUARAT", idMesyuarat);
			r.add("FLAG_PENGERUSI", "T");

			sql = r.getSQLUpdate("TBLPHPKEHADIRANMESY");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}
	
	public void updateKehadiran(String idMesyuarat, String idKehadiran,
			String namaPegawai, String agensi, String noTel, String txtJawatan,
			String flagPengerusi, HttpSession session) throws Exception {

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

			if ("Y".equals(flagPengerusi)) {
				resetFlagPengerusi(idMesyuarat, db, userId);
			}

			// TBLPHPKEHADIRANMESY
			r.update("ID_KEHADIRAN", idKehadiran);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NAMA_AGENSI", agensi);
			r.add("NO_TELEFON", noTel);
			r.add("NAMA_JAWATAN", txtJawatan);
			r.add("FLAG_PENGERUSI", flagPengerusi);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKEHADIRANMESY");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "UPD",
					"FAIL [" + idKehadiran + "] DIKEMASKINI");

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
	
	public void hapusKehadiranMesyuarat(String idKehadiran, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPKEHADIRANMESY
			r.add("ID_KEHADIRAN", idKehadiran);
			sql = r.getSQLDelete("TBLPHPKEHADIRANMESY");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "DEL",
					"FAIL [" + idKehadiran + "] DIHAPUSKAN");

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
	
	public void simpanKeputusanPermohonanBaru(String idMesyuaratPermohonan, String idKeputusan, HttpSession session) throws Exception {

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

			// TBLPHPMESYUARATPERMOHONAN
			r.update("ID_MESYUARAT_PERMOHONAN", idMesyuaratPermohonan);
			r.add("FLAG_SYOR", idKeputusan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPMESYUARATPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "UPD",
					"FAIL [" + idMesyuaratPermohonan + "] DIKEMASKINI");

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
	
	public void simpanCatatanPermohonanBaru(String idMesyuaratPermohonan, String catatan, HttpSession session) throws Exception {

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

			// TBLPHPMESYUARATPERMOHONAN
			r.update("ID_MESYUARAT_PERMOHONAN", idMesyuaratPermohonan);
			r.add("CATATAN", catatan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPMESYUARATPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "UPD",
					"FAIL [" + idMesyuaratPermohonan + "] DIKEMASKINI");

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
	
	public void hapusPermohonanMesyuarat(String idMesyuaratPermohonan,HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPMESYUARATPERMOHONAN
			SQLRenderer r = new SQLRenderer();
			r.add("ID_MESYUARAT_PERMOHONAN", idMesyuaratPermohonan);
			sql = r.getSQLDelete("TBLPHPMESYUARATPERMOHONAN");
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
	
	public void updateStatus(String idMesyuaratPermohonan,HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		
		String sql = "";
		String sqla = "";
		String flagSyor="";
		String flagMesyuaratSemula="";
		String flagSelesaiMesyuarat="";
		String flagAktif="";
		String idPermohonan="";
		String idSuburusan="";
		String idFail="";
		String userId = (String) session.getAttribute("_ekptg_user_id");

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_MESYUARAT_PERMOHONAN, D.NO_FAIL,E.NAMA,A.FLAG_JENIS_PERMOHONAN,A.FLAG_SYOR ,A.CATATAN, C.ID_PERMOHONAN, A.FLAG_MESYUARAT_SEMULA, A.FLAG_SELESAI_MESYUARAT, A.FLAG_AKTIF "+
			"FROM TBLPHPMESYUARATPERMOHONAN A, TBLPHPMESYUARAT B, TBLPERMOHONAN C, TBLPFDFAIL D, TBLPHPPEMOHON E WHERE A.FLAG_AKTIF = '1' "+
			"AND A.ID_MESYUARAT = B.ID_MESYUARAT AND A.ID_PERMOHONAN=C.ID_PERMOHONAN AND C.ID_FAIL=D.ID_FAIL AND C.ID_PEMOHON=E.ID_PEMOHON "+
			"AND A.ID_MESYUARAT='"+idMesyuaratPermohonan+"'";
			sql = sql + " ORDER BY TARIKH_MESYUARAT DESC";
	
			ResultSet rs = stmt.executeQuery(sql);
			List<String[]> list = new ArrayList<>();

			while (rs.next()) {			
				flagSyor=rs.getString("FLAG_SYOR");
				idPermohonan=rs.getString("ID_PERMOHONAN");
				flagMesyuaratSemula=rs.getString("FLAG_MESYUARAT_SEMULA");
				flagSelesaiMesyuarat=rs.getString("FLAG_SELESAI_MESYUARAT");
				flagAktif=rs.getString("FLAG_AKTIF");
				list.add(new String[]{flagSyor, idPermohonan, flagMesyuaratSemula, flagSelesaiMesyuarat, flagAktif});	
			}	
			
			for (int i = 0; i < list.size(); i++) {
				String[] myString= new String[5];
				myString=list.get(i);
				flagSyor=myString[0];
				idPermohonan=myString[1];
				flagMesyuaratSemula=myString[2];
				flagSelesaiMesyuarat=myString[3];
				flagAktif=myString[4];
				
				//query untuk dapatkan id suburusan dan no.fail
				sqla = "SELECT A.ID_FAIL,A.ID_SUBURUSAN FROM TBLPFDFAIL A, TBLPERMOHONAN B "+
						  "WHERE B.ID_FAIL=A.ID_FAIL AND B.ID_PERMOHONAN='"+idPermohonan+"'";
				ResultSet rsa = stmt.executeQuery(sqla);
				SQLRenderer r = new SQLRenderer();
				
				while (rsa.next()) {
					idSuburusan=rsa.getString("ID_SUBURUSAN");
					idFail=rsa.getString("ID_FAIL");
				}
				
				// TBLPHPMESYUARATPERMOHONAN
				r = new SQLRenderer();
				
				if(flagMesyuaratSemula.equals("1") && flagAktif.equals("1")  ){
					
					r.update("ID_PERMOHONAN", idPermohonan);
					r.update("FLAG_AKTIF","1");
					r.add("FLAG_MESYUARAT_SEMULA", "0");
					r.add("FLAG_SELESAI_MESYUARAT", "1");
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					sql = r.getSQLUpdate("TBLPHPMESYUARATPERMOHONAN");
					stmt.executeUpdate(sql);
					
				}else if(flagMesyuaratSemula.equals("0") && flagAktif.equals("1")  ){
					
					r.update("ID_PERMOHONAN", idPermohonan);
					r.update("FLAG_AKTIF","1");
					r.add("FLAG_MESYUARAT_SEMULA", "0");
					r.add("FLAG_SELESAI_MESYUARAT", "1");
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					sql = r.getSQLUpdate("TBLPHPMESYUARATPERMOHONAN");
					stmt.executeUpdate(sql);
				}
				
				// TBLPERMOHONAN
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN", idPermohonan);
				r.add("ID_STATUS", "1610206"); // CETAKAN SURAT KEPUTUSAN
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
						getIdSuburusanstatus(idSuburusan, "1610206")); // CETAKAN
																		// SURAT
																		// KEPUTUSAN
				r.add("AKTIF", "1");
				r.add("ID_FAIL", idFail);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
				stmt.executeUpdate(sql);

				// TBLPHPMESYUARAT
				r = new SQLRenderer();
				r.update("ID_MESYUARAT", idMesyuaratPermohonan);
				r.add("STATUS_MESYUARAT","2");
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPHPMESYUARAT");
				stmt.executeUpdate(sql);
			}
			conn.commit();
			AuditTrail.logActivity("1610206", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] TELAH DIAGIHKAN/PROSES SETERUSNYA");
			
		}catch (SQLException ex) {
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
	
	public void simpanKemaskiniDokumen(String idDokumen, String txtNamaImej,
			String txtCatatan, HttpSession session) throws Exception {
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

			// TBLPHPDOKUMEN
			r.update("ID_DOKUMEN", idDokumen);
			r.add("NAMA_DOKUMEN", txtNamaImej);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "UPD",
					"FAIL [" + idDokumen + "] DIKEMASKINI");

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
	
	public void hapusDokumen(String idDokumen, HttpSession session) throws Exception {
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
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "DEL",
					"FAIL [" + idDokumen
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

			//TBLPHPMESYUARATPERMOHONAN
			r = new SQLRenderer();
			r.add("ID_MESYUARAT", idMesyuarat);
			sql = r.getSQLDelete("TBLPHPMESYUARATPERMOHONAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "DEL",
					"FAIL [" + idMesyuarat
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
	
	public void setSenaraiImejan(String idMesyuarat) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_MESYUARAT = '" + idMesyuarat + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				listImejan.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatImej(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
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
				beanMaklumatImejan.addElement(h);
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
	
	public Vector getListPermohonanLanjut() {
		return listPermohonanLanjut;
	}
	
	public Vector getListPermohonanLain() {
		return listPermohonanLain;
	}
	
	public Vector getListImejan() {
		return listImejan;
	}
	
	public Vector getBeanMaklumatImejan() {
		return beanMaklumatImejan;
	}
}