package ekptg.view.php2.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class UtilHasil {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void kemaskiniRekodPerjanjianDanAkaun(String idHasil) {
		Db db = null;
		try {
			db = new Db();			
			updateStatusPerjanjian(idHasil, db);
			updateFlagAktifPerjanjianSemasa(idHasil, db);
			janaAkaunKeseluruhanFail(idHasil, db);
			updateFlagTunggakanD(idHasil, db);
			updateFlagTunggakan(idHasil, db);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}		

	private static void updateStatusPerjanjian(String idHasil, Db db) {
		String sql = "";
		Connection conn = null;
		try {
			Statement stmt = db.getStatement();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			sql = "UPDATE TBLPHPBAYARANPERLUDIBAYAR SET STATUS = '1' WHERE TARIKH_TAMAT >= SYSDATE AND STATUS != '3' AND ID_HASIL = '" + idHasil + "'";
			stmt.execute(sql);
			
			sql = "UPDATE TBLPHPBAYARANPERLUDIBAYAR SET STATUS = '2' WHERE TARIKH_TAMAT < SYSDATE AND STATUS != '3' AND ID_HASIL = '" + idHasil + "'";
			stmt.execute(sql);
			
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
	}
	
	private static void updateFlagAktifPerjanjianSemasa(String idHasil, Db db) {
		String sql = "";
		String idPerjanjian = "";
		Connection conn = null;
		try {
			Statement stmt = db.getStatement();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			//SET ALL PERJANJIAN AS TIDAK AKTIF
			sql = "UPDATE TBLPHPBAYARANPERLUDIBAYAR SET FLAG_AKTIF = 'T' WHERE ID_HASIL = '" + idHasil + "'";
			stmt.executeUpdate(sql);
			
			//SEARCH PERJANJIAN YANG MASIH AKTIF PADA SYSDATE
			sql = "SELECT ID_BAYARANPERLUDIBAYAR FROM TBLPHPBAYARANPERLUDIBAYAR WHERE SYSDATE BETWEEN TARIKH_MULA"
					+ " AND TARIKH_TAMAT AND FLAG_PERJANJIAN = 'U' AND ID_HASIL = '" + idHasil + "'";
			ResultSet rsAktif = stmt.executeQuery(sql);
			if (rsAktif.next()) {
				idPerjanjian = rsAktif.getString("ID_BAYARANPERLUDIBAYAR");
			} else {
				//SEARCH PERJANJIAN TERAKHIR YANG TELAH TAMAT PADA SYSDATE
				sql = "SELECT ID_BAYARANPERLUDIBAYAR FROM TBLPHPBAYARANPERLUDIBAYAR WHERE TARIKH_TAMAT < SYSDATE"
						+ " AND FLAG_PERJANJIAN = 'U' AND ID_HASIL = '" + idHasil + "' ORDER BY TARIKH_TAMAT DESC";
				ResultSet rsTamat = stmt.executeQuery(sql);
				if (rsTamat.next()) {
					idPerjanjian = rsTamat.getString("ID_BAYARANPERLUDIBAYAR");
				} else {
					//SEARCH PERJANJIAN YANG BELUM KUATKUASA PADA SYSDATE
					sql = "SELECT ID_BAYARANPERLUDIBAYAR FROM TBLPHPBAYARANPERLUDIBAYAR WHERE TARIKH_MULA >= SYSDATE"
							+ " AND FLAG_PERJANJIAN = 'U' AND ID_HASIL = '" + idHasil + "' ORDER BY TARIKH_MULA ASC";
					ResultSet rsAkanKuatkuasa = stmt.executeQuery(sql);
					if (rsAkanKuatkuasa.next()) {
						idPerjanjian = rsAkanKuatkuasa.getString("ID_BAYARANPERLUDIBAYAR");
					} else {
						//SEARCH PERJANJIAN KELULUSAN DASAR
						sql = "SELECT ID_BAYARANPERLUDIBAYAR FROM TBLPHPBAYARANPERLUDIBAYAR WHERE FLAG_LULUSDASAR = 'Y'"
								+ " AND FLAG_PERJANJIAN = 'U' AND ID_HASIL = '" + idHasil + "' ORDER BY ID_BAYARANPERLUDIBAYAR DESC";
						ResultSet rsKelulusanDasar = stmt.executeQuery(sql);
						if (rsKelulusanDasar.next()) {
							idPerjanjian = rsKelulusanDasar.getString("ID_BAYARANPERLUDIBAYAR");
						}
					}
				}
			}
			
			if (!"".equals(idPerjanjian)) {
				sql = "UPDATE TBLPHPBAYARANPERLUDIBAYAR SET FLAG_AKTIF = 'Y' WHERE ID_BAYARANPERLUDIBAYAR = '" + idPerjanjian + "'";
				stmt.executeUpdate(sql);			
			}
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
	}
	
	private static void janaAkaunKeseluruhanFail(String idHasil, Db db) {
		String sql = "";
		Connection conn = null;
		Db db1 = null;
		try {
			db1 = new Db();			
			Statement stmt = db.getStatement();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			//DELETE ALL CAJ UNTUK FAIL
			sql = "DELETE FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = '10' AND ID_JENISTRANSAKSI = '1' AND ID_HASIL = '" + idHasil + "'";
			stmt.executeUpdate(sql);		
			conn.commit();
			
			sql = "SELECT * FROM TBLPHPBAYARANPERLUDIBAYAR WHERE ID_HASIL IS NOT NULL AND TARIKH_MULA IS NOT NULL AND TARIKH_TAMAT IS NOT NULL"
					+ " AND BAYARAN IS NOT NULL AND FLAG_PERJANJIAN = 'U' AND ID_HASIL = '" + idHasil + "' ORDER BY TARIKH_MULA ASC";
			ResultSet rsPerjanjianUtama = stmt.executeQuery(sql);
			while (rsPerjanjianUtama.next()) {
				generateAkaunCaj(rsPerjanjianUtama.getString("ID_HASIL"),
						sdf.format(rsPerjanjianUtama.getDate("TARIKH_MULA")),
						sdf.format(rsPerjanjianUtama.getDate("TARIKH_TAMAT")),
						rsPerjanjianUtama.getString("BAYARAN"), rsPerjanjianUtama.getString("MOD_CAJ_SEWAAN"), db1);
			}
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			if (db1 != null)
				db1.close();
		}
	}

	private static void generateAkaunCaj(String idHasil, String tarikhMula, String tarikhTamat, String kadarSewa, String modCajSewaan, Db db) {
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			Calendar calCurrent = new GregorianCalendar();
			Date dateCurrent = new Date();
			calCurrent.setTime(dateCurrent);

			Calendar calMula = new GregorianCalendar();
			Date dateMula = sdf.parse(tarikhMula);
			calMula.setTime(dateMula);

			Calendar calTamat = new GregorianCalendar();
			Date dateTamat = sdf.parse(tarikhTamat);
			calTamat.setTime(dateTamat);
			
			sql = "DELETE FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 10 AND ID_JENISTRANSAKSI = 1 AND ID_HASIL = '"
					+ idHasil
					+ "' AND TO_DATE(TARIKH) BETWEEN TO_DATE('"
					+ tarikhMula
					+ "', 'dd/MM/yyyy') AND TO_DATE('"
					+ tarikhTamat + "', 'dd/MM/yyyy')";
			stmt.executeUpdate(sql);

			while (calMula.getTime().before(calTamat.getTime())) {

				if (calMula.getTime().before(calCurrent.getTime())) {
					// TBLPHPAKAUN
					r = new SQLRenderer();
					r.add("ID_HASIL", idHasil);
					r.add("TARIKH",
							r.unquote("to_date('"
									+ sdf.format(calMula.getTime())
									+ "','dd/MM/yyyy')"));
					r.add("DEBIT", getKadarSewa(idHasil, calMula.getTime(), kadarSewa, db));
					r.add("ID_JENISBAYARAN", "10"); // BAYARAN SEWA

					if (calMula.get(Calendar.MONTH) == 0) {
						r.add("CATATAN",
								"SEWA BULAN JANUARI "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 1) {
						r.add("CATATAN",
								"SEWA BULAN FEBRUARI "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 2) {
						r.add("CATATAN",
								"SEWA BULAN MAC " + calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 3) {
						r.add("CATATAN",
								"SEWA BULAN APRIL "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 4) {
						r.add("CATATAN",
								"SEWA BULAN MEI " + calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 5) {
						r.add("CATATAN",
								"SEWA BULAN JUN " + calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 6) {
						r.add("CATATAN",
								"SEWA BULAN JULAI "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 7) {
						r.add("CATATAN",
								"SEWA BULAN OGOS " + calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 8) {
						r.add("CATATAN",
								"SEWA BULAN SEPTEMBER "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 9) {
						r.add("CATATAN",
								"SEWA BULAN OKTOBER "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 10) {
						r.add("CATATAN",
								"SEWA BULAN NOVEMBER "
										+ calMula.get(Calendar.YEAR));
					} else if (calMula.get(Calendar.MONTH) == 11) {
						r.add("CATATAN",
								"SEWA BULAN DISEMBER "
										+ calMula.get(Calendar.YEAR));
					} else {
						r.add("CATATAN", "");
					}
					r.add("ID_JENISTRANSAKSI", "1"); // CAJ

					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPHPAKAUN");
					stmt.executeUpdate(sql);
					
					if ("0".equals(modCajSewaan)) {
						break;
					} else {
						calMula.add(Calendar.MONTH, Integer.parseInt(modCajSewaan));
					}
				} else {
					break;
				}
			}			
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}
	
	private static String getKadarSewa(String idHasil, Date tarikh, String kadarSewa, Db db) {
		String sql = "";
		try {

			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPBAYARANPERLUDIBAYAR WHERE ID_HASIL IS NOT NULL AND TARIKH_MULA IS NOT NULL AND TARIKH_TAMAT IS NOT NULL"
					+ " AND BAYARAN IS NOT NULL AND FLAG_PERJANJIAN != 'U' AND TO_DATE('" + sdf.format(tarikh) + "', 'dd/MM/yyyy') BETWEEN TARIKH_MULA AND TARIKH_TAMAT"
					+ " AND ID_HASIL = '" + idHasil + "' ORDER BY TARIKH_MULA DESC";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				kadarSewa = rs.getString("BAYARAN");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return kadarSewa;
	}
	
	public static void updateFlagTunggakan(String idHasil, Db db) {
		String sql = "";
		Double total = 0D;
		String flagTunggakan = "T";
		Connection conn = null;

		try {		
			Statement stmt = db.getStatement();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			sql = "SELECT SUM(NVL(DEBIT,0) - NVL(KREDIT,0)) AS TOTAL FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 10 AND FLAG_AKTIF = 'Y' AND ID_HASIL = '" + idHasil + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				total = Double.valueOf(rs.getDouble("TOTAL"));
			}

			if (total > 0D) {
				flagTunggakan = "Y";				
			} else {
				total = total * -1;
			}

			sql = "UPDATE TBLPHPHASIL SET FLAG_TUNGGAKAN = '" + flagTunggakan + "', NILAI_TUNGGAKAN = '" + total + "' WHERE ID_HASIL = '" + idHasil + "'";
			stmt.executeQuery(sql);

			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
	}

	public static void updateFlagTunggakanD(String idHasil, Db db) {
		String sql = "";
		Double total = 0D;
		String flagDeposit = "T";
		Connection conn = null;

		try {		
			Statement stmt = db.getStatement();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			sql = "SELECT SUM(NVL(KREDIT,0) - NVL(DEBIT,0)) AS TOTAL FROM TBLPHPAKAUN WHERE ID_JENISBAYARAN = 9 AND FLAG_AKTIF = 'Y' AND ID_HASIL = '" + idHasil + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				total = Double.valueOf(rs.getDouble("TOTAL"));
			}

			if (total > 0D) {
				flagDeposit = "Y";
			}

			sql = "UPDATE TBLPHPHASIL SET FLAG_TUNGGAKAND = '" + flagDeposit + "' WHERE ID_HASIL = '" + idHasil + "'";
			stmt.executeQuery(sql);

			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}		
	}
}
