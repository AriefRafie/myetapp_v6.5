package ekptg.model.php2.online;

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

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;

public class FrmMOFOnlineKJPSenaraiUlasanFailData {
	
	protected Db db;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
	
	static Logger myLogger = Logger.getLogger(FrmPLPOnlineKJPSenaraiFailData.class);

	public Vector getSenaraiFail(String findNoFail, String findTajukFail, String findPemohon, String findNoPengenalan, 
			String findTarikhTerima, String findNoHakmilik, String findNoWarta, String findNoPegangan, String findJenisHakmilik, 
			String findJenisLot, String findNoLot, String findNegeri, String findDaerah, String findMukim, String userId) {
		
		String sql = "";
		Vector listFail = null;
		Hashtable h;
		
		try {
			listFail = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLPFDFAIL.ID_FAIL, TBLPFDFAIL.NO_FAIL, TBLPFDFAIL.TAJUK_FAIL, TBLPHPKERTASKERJAPELEPASAN.TARIKH_HANTAR_KEWANGAN, TBLPERMOHONAN.ID_STATUS, "
				+ "TBLPHPKERTASKERJAPELEPASAN.TARIKH_TERIMA_KEWANGAN, TBLPERMOHONAN.ID_PERMOHONAN, TBLPHPKERTASKERJAPELEPASAN.ID_KERTASKERJA "
				+ "FROM TBLPHPKERTASKERJAPELEPASAN, TBLPERMOHONAN, TBLPHPPEMOHON, TBLPFDFAIL, TBLPHPHAKMILIKPERMOHONAN, TBLPHPHAKMILIK, USERS "
				+ "WHERE TBLPHPKERTASKERJAPELEPASAN.FLAG_KERTAS = 2 AND TBLPHPKERTASKERJAPELEPASAN.ID_PERMOHONAN = TBLPERMOHONAN.ID_PERMOHONAN "
				+ "AND TBLPERMOHONAN.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON AND TBLPERMOHONAN.ID_FAIL = TBLPFDFAIL.ID_FAIL "
				+ "AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN "
				+ "AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN = TBLPHPHAKMILIK.ID_HAKMILIKPERMOHONAN "
				+ "AND TBLPFDFAIL.ID_SEKSYEN = 4 AND TBLPFDFAIL.ID_URUSAN = '6' AND TBLPFDFAIL.ID_SUBURUSAN = '34' "
				+ "AND TBLPHPHAKMILIKPERMOHONAN.FLAG_HAKMILIK = 'U' AND TBLPERMOHONAN.ID_STATUS = '1610203' "
				+ "AND USERS.USER_ID = '" + userId + "'";
			
			if (findNoFail != null) {
				if (!findNoFail.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPFDFAIL.NO_FAIL) LIKE '%' ||'"
							+ findNoFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findTajukFail != null) {
				if (!findTajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPFDFAIL.TAJUK_FAIL) LIKE '%' ||'"
							+ findTajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findPemohon != null) {
				if (!findPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPPEMOHON.NAMA) LIKE '%' ||'"
							+ findPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findNoPengenalan != null) {
				if (!findNoPengenalan.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPPEMOHON.NO_PENGENALAN) LIKE '%' ||'"
							+ findNoPengenalan.trim().toUpperCase() + "'|| '%'";
				}
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhTerima
			if (findTarikhTerima != null) {
				if (!findTarikhTerima.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(TBLPHPKERTASKERJAPELEPASAN.TARIKH_TERIMA_KEWANGAN,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(findTarikhTerima))
									.toUpperCase() + "'";
				}
			}
			if (findNoHakmilik != null) {
				if (!findNoHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPHAKMILIK.NO_HAKMILIK) LIKE '%' ||'"
							+ findNoHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findNoWarta != null) {
				if (!findNoWarta.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPHAKMILIK.NO_WARTA) LIKE '%' ||'"
							+ findNoWarta.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findNoPegangan != null) {
				if (!findNoPegangan.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPHAKMILIK.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ findNoPegangan.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findJenisHakmilik != null) {
				if (!findJenisHakmilik.trim().equals("")
						&& !findJenisHakmilik.trim().equals("9999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_JENISHAKMILIK = '" + findJenisHakmilik.trim() + "'";
				}
			}
			if (findJenisLot != null) {
				if (!findJenisLot.trim().equals("")
						&& !findJenisLot.trim().equals("9999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_LOT = '" + findJenisLot.trim() + "'";
				}
			}
			if (findNoLot != null) {
				if (!findNoLot.trim().equals("")) {
					sql = sql + " AND UPPER(TBLPHPHAKMILIK.NO_LOT) LIKE '%' ||'"
							+ findNoLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			if (findNegeri != null) {
				if (!findNegeri.trim().equals("")
						&& !findNegeri.trim().equals("9999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_NEGERI = '" + findNegeri.trim() + "'";
				}
			}
			if (findDaerah != null) {
				if (!findDaerah.trim().equals("")
						&& !findDaerah.trim().equals("9999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_DAERAH = '" + findDaerah.trim() + "'";
				}
			}
			if (findMukim != null) {
				if (!findMukim.trim().equals("")
						&& !findMukim.trim().equals("9999")) {
					sql = sql + " AND TBLPHPHAKMILIK.ID_MUKIM = '" + findMukim.trim() + "'";
				}
			}
			
			sql = sql + " ORDER BY TBLPHPKERTASKERJAPELEPASAN.TARIKH_TERIMA_KEWANGAN DESC ";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_ULASANTEKNIKAL", rs.getString("ID_KERTASKERJA") == null ? "" : rs.getString("ID_KERTASKERJA"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				//h.put("ID_KEMENTERIAN", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("TAJUK_FAIL", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
				h.put("TARIKH_HANTAR", rs.getDate("TARIKH_TERIMA_KEWANGAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA_KEWANGAN")));
				h.put("TARIKH_JANGKA_TERIMA", rs.getDate("TARIKH_HANTAR_KEWANGAN") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_KEWANGAN")));
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
								
				listFail.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listFail;
	}

	public String simpanUlasan(String idKertasKerja, String txtTarikhTerima, String txtUlasan,
			String txtKeputusan,  String txtNamaPengulas, String txtNoTelPengulas, HttpSession session) {

		String flagStatus = "T";
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
			r.update("FLAG_KERTAS", "2");
			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_TERIMA_KEWANGAN",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("ULASAN_KEWANGAN", txtUlasan);
			r.add("KEPUTUSAN_KEWANGAN", txtKeputusan);
			r.add("NAMA_PEGAWAI", txtNamaPengulas);
			r.add("NO_TELEFON_PEGAWAI", txtNoTelPengulas);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);
			myLogger.info("TBLPHPKERTASKERJAPELEPASAN======="+sql);

			conn.commit();
			flagStatus = "Y";
			
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			if (db != null)
				db.close();
		}
		
		return flagStatus;
	}
	
	
	public String getIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERMOHONAN FROM TBLPHPKERTASKERJAPELEPASAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_PERMOHONAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	public String hantarUlasan(String idKertasKerja, String txtTarikhTerima,
			String txtNoRujukanSurat, String txtUlasan, String txtKeputusan,
			String txtNamaPengulas, String txtNoTelPengulas, String userId) {

		String flagStatus = "T";
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPKERTASKERJAPELEPASAN
			r.update("ID_KERTASKERJA", idKertasKerja);			
			if (!"".equals(txtTarikhTerima)) {
				r.add("TARIKH_TERIMA_KEWANGAN",
						r.unquote("to_date('" + txtTarikhTerima
								+ "','dd/MM/yyyy')"));
			}
			r.add("ULASAN_KEWANGAN", txtUlasan);
			r.add("KEPUTUSAN_KEWANGAN", txtKeputusan);
			r.add("NAMA_PEGAWAI", txtNamaPengulas);
			r.add("NO_TELEFON_PEGAWAI", txtNoTelPengulas);
			r.add("TARIKH_HANTAR_KEWANGAN", r.unquote("SYSDATE"));
			r.add("FLAG_KERTAS", "2");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			myLogger.info("TBLPHPKERTASKERJAPELEPASAN===="+sql);
			stmt.executeUpdate(sql);
			
			conn.commit();
			flagStatus = "Y";
			
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			if (db != null)
				db.close();
		}
		
		return flagStatus;
	}
	
	public void hapusDokumen(String idUlasanTeknikal) throws Exception {
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

	public String getIdPermohonanByIdUlasanTeknikal(String idKertasKerja) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERMOHONAN FROM TBLPHPKERTASKERJAPELEPASAN WHERE ID_KERTASKERJA = '" + idKertasKerja + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_PERMOHONAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Hashtable getMaklumatLampiran(String idUlasanTeknikal, String idPermohonan) {
		String sql = "";
		Hashtable lampiran = null;
		
		try {			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_DOKUMEN FROM TBLPHPDOKUMEN WHERE ID_PERMOHONAN = '" + idPermohonan + "' AND ID_ULASANTEKNIKAL = '" + idUlasanTeknikal + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				lampiran = new Hashtable();
				lampiran.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? "" : rs.getString("ID_DOKUMEN"));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return lampiran;
	}
	public Vector getIdNegeriKJPByUserId(String userId) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h;
		Vector listDetailKJP = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.USER_ID, A.USER_NAME, C.ID_NEGERI, B.ID_KEMENTERIAN, B.ID_AGENSI FROM USERS A, USERS_KEMENTERIAN B, TBLRUJAGENSI C, TBLRUJKEMENTERIAN D "
					+ " WHERE A.USER_ID = B.USER_ID AND B.ID_AGENSI = C.ID_AGENSI AND B.ID_KEMENTERIAN = D.ID_KEMENTERIAN AND A.USER_ID = '"
					+ userId + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("listDetailKJP:: "+sql);

			if (rs.next()) {
				h = new Hashtable();
				h.put("userId", rs.getString("USER_ID").toString());
				h.put("idNegeri", rs.getString("ID_NEGERI").toString());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN").toString());
				h.put("idAgensi", rs.getString("ID_AGENSI").toString());
				h.put("namaPemohon", rs.getString("USER_NAME").toString());
				listDetailKJP.addElement(h);

				return listDetailKJP;
			} else {
				return listDetailKJP;
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
}
