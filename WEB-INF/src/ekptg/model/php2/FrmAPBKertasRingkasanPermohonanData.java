package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public class FrmAPBKertasRingkasanPermohonanData {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Vector getMaklumatKertasRingkasPermohonan(String idPermohonan)
			throws Exception {
		Vector beanMaklumatKertasRingkasPermohonan = null;
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKertasRingkasPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPKERTASKERJAAPB.ID_KERTASKERJAAPB, TBLPHPKERTASKERJAAPB.TARIKH_KERTAS, TBLPHPKERTASKERJAAPB.NAMA_PTP, TBLPHPKERTASKERJAAPB.NAMA_KSU, TBLPHPKERTASKERJAAPB.NAMA_MENTERI,"
					+ " TBLPHPKERTASKERJAAPB.ULASAN_JUPEM, TBLPHPKERTASKERJAAPB.ULASAN_JPS, TBLPHPKERTASKERJAAPB.ULASAN_JAB_GEOSAINS, TBLPHPKERTASKERJAAPB.ULASAN_PUSAT_HIDROGRAFI,"
					+ " TBLPHPKERTASKERJAAPB.ULASAN_JAB_PERIKANAN, TBLPHPKERTASKERJAAPB.ULASAN_JAB_LAUT, TBLPHPKERTASKERJAAPB.ULASAN_JAS, TBLPHPKERTASKERJAAPB.ULASAN_PTG,"
					+ " TBLPHPKERTASKERJAAPB.ULASAN_JABATAN, TBLPHPKERTASKERJAAPB.SYOR_JABATAN, TBLPHPKERTASKERJAAPB.SYOR_PTP,"
					+ " TBLPHPKERTASKERJAAPB.TARIKH_MESYUARAT, TBLPHPKERTASKERJAAPB.BIL_MESYUARAT, TBLPHPKERTASKERJAAPB.CATATAN_BERSYARAT_JABATAN"
					+ " FROM TBLPFDFAIL, TBLPERMOHONAN, TBLPHPKERTASKERJAAPB"
					+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPKERTASKERJAAPB.ID_PERMOHONAN"
					+ " AND TBLPHPKERTASKERJAAPB.FLAG_KERTAS = 2 AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKertasKerjaApb",
						rs.getString("ID_KERTASKERJAAPB") == null ? "" : rs
								.getString("ID_KERTASKERJAAPB"));
				h.put("tarikhKertas", rs.getDate("TARIKH_KERTAS") == null ? ""
						: sdf.format(rs.getDate("TARIKH_KERTAS")));				
				h.put("namaPTP", rs.getString("NAMA_PTP") == null ? ""
						: rs.getString("NAMA_PTP"));
				h.put("namaKSU", rs.getString("NAMA_KSU") == null ? ""
						: rs.getString("NAMA_KSU"));
				h.put("namaMenteri", rs.getString("NAMA_MENTERI") == null ? ""
						: rs.getString("NAMA_MENTERI"));
				
				h.put("ulasanJUPEM", rs.getString("ULASAN_JUPEM") == null ? ""
						: rs.getString("ULASAN_JUPEM"));
				h.put("ulasanJPS",
						rs.getString("ULASAN_JPS") == null ? "" : rs
								.getString("ULASAN_JPS"));
				h.put("ulasanJMG",
						rs.getString("ULASAN_JAB_GEOSAINS") == null ? "" : rs
								.getString("ULASAN_JAB_GEOSAINS"));
				h.put("ulasanPHN",
						rs.getString("ULASAN_PUSAT_HIDROGRAFI") == null ? ""
								: rs.getString("ULASAN_PUSAT_HIDROGRAFI"));
				h.put("ulasanDOF",
						rs.getString("ULASAN_JAB_PERIKANAN") == null ? "" : rs
								.getString("ULASAN_JAB_PERIKANAN"));
				h.put("ulasanJLM",
						rs.getString("ULASAN_JAB_LAUT") == null ? "" : rs
								.getString("ULASAN_JAB_LAUT"));
				h.put("ulasanJAS",
						rs.getString("ULASAN_JAS") == null ? "" : rs
								.getString("ULASAN_JAS"));
				h.put("ulasanPTG", rs.getString("ULASAN_PTG") == null ? ""
						: rs.getString("ULASAN_PTG")); 
				h.put("ulasanJabatan", rs.getString("ULASAN_JABATAN") == null ? ""
						: rs.getString("ULASAN_JABATAN")); 
				h.put("syorJabatan", rs.getString("SYOR_JABATAN") == null ? ""
						: rs.getString("SYOR_JABATAN")); 
				h.put("catatanBersyarat", rs.getString("CATATAN_BERSYARAT_JABATAN") == null ? ""
						: rs.getString("CATATAN_BERSYARAT_JABATAN")); 
				h.put("tarikhMesyuarat", getBilMesyuaratByIdPermohonan(idPermohonan));				
				h.put("bilMesyuarat", getBilMesyuaratByIdPermohonan(idPermohonan));
				beanMaklumatKertasRingkasPermohonan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return beanMaklumatKertasRingkasPermohonan;
	}
	
	public String getBilMesyuaratByIdPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPMESYUARAT.TARIKH_MESYUARAT, TBLPHPMESYUARAT.BIL_MESYUARAT "
				+ "FROM TBLPHPMESYUARATPERMOHONAN, TBLPHPMESYUARAT "
				+ "WHERE TBLPHPMESYUARATPERMOHONAN.ID_MESYUARAT = TBLPHPMESYUARAT.ID_MESYUARAT "
				+ "AND ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (Date) rs.getDate("TARIKH_MESYUARAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MESYUARAT"));
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getTarikhMesyuaratByIdPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPMESYUARAT.TARIKH_MESYUARAT, TBLPHPMESYUARAT.BIL_MESYUARAT "
				+ "FROM TBLPHPMESYUARATPERMOHONAN, TBLPHPMESYUARAT "
				+ "WHERE TBLPHPMESYUARATPERMOHONAN.ID_MESYUARAT = TBLPHPMESYUARAT.ID_MESYUARAT "
				+ "AND ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("TARIKH_MESYUARAT");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateMaklumatKertasRingkasPermohonan(String idPermohonan, String tarikhKertas, String namaPTP, String namaKSU, String namaMenteri, 			
			String JUPEM, String JPS, String JMG, String PHN, String DOF, String JLM, 
			String JAS, String PTG, String txtUlasanJabatan,String txtUlasanLulusBersyarat, String syor, String bilMesyuarat, String tarikhMesyuarat, HttpSession session) throws Exception {

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
			r.update("FLAG_KERTAS", "2");
			
			if (!"".equals(tarikhKertas))
				r.add("TARIKH_KERTAS", r.unquote("to_date('" + tarikhKertas + "','dd/MM/yyyy')"));
			r.add("NAMA_PTP", namaPTP);
			r.add("NAMA_KSU", namaKSU);
			r.add("NAMA_MENTERI", namaMenteri);
			
			r.add("ULASAN_JABATAN", txtUlasanJabatan);
			r.add("CATATAN_BERSYARAT_JABATAN", txtUlasanLulusBersyarat);
			r.add("SYOR_JABATAN", syor);
			r.add("SYOR_PTP", syor);
			r.add("SYOR_JKPTG", syor);
			
			r.add("ULASAN_JUPEM", JUPEM);
			r.add("ULASAN_JPS", JPS);
			r.add("ULASAN_JAB_GEOSAINS", JMG);
			r.add("ULASAN_PUSAT_HIDROGRAFI", PHN);
			r.add("ULASAN_JAB_PERIKANAN", DOF);
			r.add("ULASAN_JAB_LAUT", JLM);
			r.add("ULASAN_JAS", JAS);
			r.add("ULASAN_PTG", PTG);
			
			if (!"".equals(tarikhMesyuarat))
				r.add("TARIKH_MESYUARAT", r.unquote("to_date('" + tarikhMesyuarat + "','dd/MM/yyyy')"));
			r.add("BIL_MESYUARAT", bilMesyuarat.toUpperCase());

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAAPB");

			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610213", "4", null, session, "UPD",
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
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610205"); //KELULUSAN MENTERI

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610205")); //KELULUSAN MENTERI
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
					"FAIL [" + idPermohonan + "] PROSES SETERUSNYA");

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
}
