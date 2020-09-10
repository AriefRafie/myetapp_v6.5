
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
			r.add("ID_URUSAN", "4");
			r.add("STATUS_MESYUARAT", "B");
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


			sql = "select a.ID_MESYUARAT_PERMOHONAN, d.no_fail,e.nama,a.flag_jenis_permohonan,a.FLAG_SYOR ,a.CATATAN, c.id_permohonan from tblphpMesyuaratPermohonan a, tblphpmesyuarat b, tblpermohonan c, tblpfdfail d, tblphppemohon e "
				 +"where a.flag_jenis_permohonan = 'B' and a.id_mesyuarat = b.id_mesyuarat and a.id_permohonan=c.id_permohonan and c.id_fail=d.id_fail "
				 +"and c.id_pemohon=e.id_pemohon and a.id_mesyuarat='"+idMesyuarat+"'";	
			sql = sql + " ORDER BY a.ID_MESYUARAT_PERMOHONAN ASC";

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
				if(rs.getString("FLAG_JENIS_PERMOHONAN").equals("B")){
					h.put("jenisPermohonan","PERMOHONAN BARU");
				}else{
					h.put("jenisPermohonan","PERMOHONAN TANGGUH");
				}
				h.put("flagKeputusan",
						rs.getString("FLAG_SYOR") == null ? "" : rs
								.getString("FLAG_SYOR"));
				h.put("catatanKeputusan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				listPermohonanBaharu.addElement(h);
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
				
				if(status.equalsIgnoreCase("B")){
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
			String agensi, String jawatan, String noTel, String flagPengerusi,
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
}