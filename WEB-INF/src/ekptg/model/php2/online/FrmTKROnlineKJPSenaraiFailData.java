package ekptg.model.php2.online;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmTKROnlineKJPSenaraiFailData {
	
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
			
			sql = "SELECT TBLPHPULASANTEKNIKAL.ID_ULASANTEKNIKAL, TBLPFDFAIL.ID_FAIL, TBLPFDFAIL.NO_FAIL, TBLPFDFAIL.TAJUK_FAIL, TBLPHPULASANTEKNIKAL.TARIKH_HANTAR, TBLPHPULASANTEKNIKAL.TARIKH_JANGKA_TERIMA"
					
					+ " FROM TBLPHPULASANTEKNIKAL, TBLPERMOHONAN, TBLPHPPEMOHON, TBLPFDFAIL, TBLPHPHAKMILIKPERMOHONAN, TBLPHPHAKMILIK, USERS, USERS_KEMENTERIAN"
					
					+ " WHERE TBLPHPULASANTEKNIKAL.FLAG_STATUS = 1 AND TBLPHPULASANTEKNIKAL.FLAG_AKTIF = 'Y'"
					+ " AND TBLPHPULASANTEKNIKAL.ID_PERMOHONAN = TBLPERMOHONAN.ID_PERMOHONAN"
					+ " AND TBLPERMOHONAN.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON"
					+ " AND TBLPERMOHONAN.ID_FAIL = TBLPFDFAIL.ID_FAIL"
					+ " AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN"
					+ " AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKPERMOHONAN = TBLPHPHAKMILIK.ID_HAKMILIKPERMOHONAN"
					+ " AND TBLPFDFAIL.ID_SEKSYEN = 4 AND TBLPFDFAIL.ID_URUSAN = '6' AND TBLPFDFAIL.ID_SUBURUSAN = '33'"
					+ " AND TBLPHPHAKMILIKPERMOHONAN.FLAG_HAKMILIK = 'U'"
					+ " AND TBLPERMOHONAN.ID_STATUS NOT IN (1610212, 1610207, 1610208)"
					+ " AND USERS.USER_ID = USERS_KEMENTERIAN.USER_ID AND USERS_KEMENTERIAN.ID_AGENSI = TBLPHPULASANTEKNIKAL.ID_AGENSI"
					+ " AND USERS.USER_ID = '" + userId + "'";
			
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
							+ " AND TO_CHAR(TBLPHPULASANTEKNIKAL.TARIKH_TERIMA,'dd-MON-YY') = '"
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
			
			sql = sql + " ORDER BY TBLPHPULASANTEKNIKAL.TARIKH_HANTAR DESC ";
				
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("sql FrmTKROnlineKJPSenaraiFailView :::: "+sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_ULASANTEKNIKAL", rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs.getString("ID_ULASANTEKNIKAL"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("TAJUK_FAIL", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
				h.put("TARIKH_HANTAR", rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("TARIKH_JANGKA_TERIMA", rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_JANGKA_TERIMA")));
								
				listFail.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listFail;
	}
	
	public String simpanUlasan(String idUlasanTeknikal, String txtTarikhSurat,
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

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			if (!"".equals(txtTarikhSurat)) {
				r.add("TARIKH_SURAT",
						r.unquote("to_date('" + txtTarikhSurat
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", txtNoRujukanSurat);
			r.add("ULASAN", txtUlasan);
			r.add("FLAG_KEPUTUSAN", txtKeputusan);
			r.add("NAMA_PEGAWAI", txtNamaPengulas);
			r.add("NO_TELEFON", txtNoTelPengulas);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
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
	
	public String hantarUlasan(String idUlasanTeknikal, String txtTarikhSurat,
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

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);			
			if (!"".equals(txtTarikhSurat)) {
				r.add("TARIKH_SURAT",
						r.unquote("to_date('" + txtTarikhSurat
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", txtNoRujukanSurat);
			r.add("ULASAN", txtUlasan);
			r.add("FLAG_KEPUTUSAN", txtKeputusan);
			r.add("NAMA_PEGAWAI", txtNamaPengulas);
			r.add("NO_TELEFON", txtNoTelPengulas);
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
			r.add("FLAG_STATUS", "2");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
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
	
	public String getIdPermohonanByIdUlasanTeknikal(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERMOHONAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '" + idUlasanTeknikal + "'";

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
			System.out.println(sql);
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
}
