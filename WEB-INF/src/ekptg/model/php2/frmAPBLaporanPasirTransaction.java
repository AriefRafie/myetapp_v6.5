package ekptg.model.php2;

/*
 * AUTHOR: NORZAILY JASMI
 * DATE: 24062010
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class frmAPBLaporanPasirTransaction {
	static Logger myLogger = Logger
			.getLogger(frmAPBLaporanPasirTransaction.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static String simpanLaporan(String usid,
			String id_jadualkedualesenAPB, String txtJumKuantiti,
			String txtJumRoyalti, String txtTarikhPengeluaran, String txtBulan, String txtTahun, String txtMasa, String txtHari,
			String txtKapal)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";
		String output = "";
		Date now = new Date();
		String tarikhOperasi = "to_date('" + txtTarikhPengeluaran + "','dd/MM/yyyy')";

		try {

			// if (isBulanExist(socBulan)) {
			// throw new Exception("Rekod Bulan "+ socBulan +" Telah Wujud.");
			// }

			long id_laporanpasir = DB.getNextID("TBLPHPLAPORANPASIR_SEQ");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPLAPORANPASIR
			r = new SQLRenderer();
			r.add("id_laporanpasir", id_laporanpasir);
			r.add("id_jadualkedualesenAPB", id_jadualkedualesenAPB);
			r.add("bulan_pengambilan", txtBulan);
			r.add("tahun_pengambilan", txtTahun);
			r.add("jumlah_kuantiti", txtJumKuantiti);
			r.add("id_unitisipadu", 3); // 3 = METER PERSEGI
			r.add("jumlah_royalti", txtJumRoyalti);
			r.add("tarikh_pengeluaran", r.unquote(tarikhOperasi));
			r.add("masa_operasi", txtMasa);
			r.add("hari_operasi", txtHari);
			r.add("nama_kapal", txtKapal);
			/*r.add("kontraktor", txtKontraktor);*/
			/*r.add("pembeli_pasir", txtPembeli);*/
			r.add("id_masuk", usid);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", usid);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("Tblphplaporanpasir");
			myLogger.info("INSERT Tblphplaporanpasir ::" + sql);
			stmt.executeUpdate(sql);

			output = "" + id_laporanpasir;

			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat Pendaftaran Pengeluaran Pasir Laut:"
					+ se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return output;
	}

	// CHECKING EXISTING BULAN
	public static boolean isBulanExist1(String bulan) {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("bulan_pengambilan");
			r.add("bulan_pengambilan", bulan);
			sql = r.getSQLSelect("Tblphplaporanpasir");

			myLogger.info("checking bulan :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return false;
	}

	// CLOSE CHECKING EXISTING BULAN

	// HAPUS LAPORAN
	public void deleteLaporanPasir(String id_laporanpasir) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPHPLAPORANPASIR WHERE ID_LAPORANPASIR = "
					+ id_laporanpasir;
			myLogger.info("DELETE TBLPHPLAPORANPASIR :: " + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE HAPUS LAPORAN

	// EDIT LAPORAN PASIR
	public void simpanEditLaporan(String usid, String id_laporanpasir,
			String txtJumKuantiti, String txtJumRoyalti, String txtTarikhPengeluaran, String txtBulan, String txtTahun, String txtMasa, String txtHari,
			String txtKapal) throws Exception {

		Db db = null;
		String sql = "";
		String tarikhOperasi = "to_date('" + txtTarikhPengeluaran + "','dd/MM/yyyy')";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_laporanpasir", id_laporanpasir);
			r.add("bulan_pengambilan", txtBulan);
			r.add("tahun_pengambilan", txtTahun);
			r.add("jumlah_kuantiti", txtJumKuantiti);
			r.add("jumlah_royalti", txtJumRoyalti);
			r.add("tarikh_pengeluaran", r.unquote(tarikhOperasi));
			r.add("masa_operasi", txtMasa);
			r.add("hari_operasi", txtHari);
			r.add("nama_kapal", txtKapal);
//			r.add("kontraktor", txtKontraktor);
//			r.add("pembeli_pasir", txtPembeli);
			r.add("id_kemaskini", usid);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("Tblphplaporanpasir");
			myLogger.info("SQL UPDATE LAPORAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	} // CLOSE EDIT LAPORAN PASIR

	// SIMPAN PASIR
	public static String simpanPasir(String usid, String id_laporanpasir,
			String txdTarikhHantar, String txtNamaBarge, String txtLokasi,
			String txtKuantiti, String txtAnggaranRoyalti,
			String txtAkuanKastam, String txtNoKastam, String txdHariHantar, String idJamDari, String idMinitDari) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";
		String output = "";
		Date now = new Date();

		try {
			long id_borangk2k3 = DB.getNextID("TBLPHPBORANGK2K3_SEQ");

			String TH = "to_date('" + txdTarikhHantar + "','dd/MM/yyyy')";

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPBORANGK2K3
			r = new SQLRenderer();
			r.add("id_borangk2k3", id_borangk2k3);
			r.add("id_laporanpasir", id_laporanpasir);
			r.add("tarikh_hantar", r.unquote(TH));
			r.add("lokasi_dibekalkan", txtLokasi);
			r.add("akuan_kastam", txtAkuanKastam);
			r.add("kuantiti", txtKuantiti);
			r.add("anggaran_royalti", txtAnggaranRoyalti);
			r.add("nama_barge", txtNamaBarge);
			r.add("no_kastam", txtNoKastam);
			r.add("hari_hantar", txdHariHantar);
			r.add("jam_hantar", idJamDari);
			r.add("minit_hantar", idMinitDari);
			
			r.add("id_masuk", usid);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", usid);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("Tblphpborangk2k3");
			myLogger.info("INSERT TBLPHPBORANGK2K3 ::" + sql);
			stmt.executeUpdate(sql);

			output = "" + id_borangk2k3;

			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat Pendaftaran Pengeluaran Pasir Laut:"
					+ se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return output;
	} // CLOSE SIMPAN PASIR

	// HAPUS BORANGK2K3
	public void deleteLaporanBorang(String id_borangk2k3) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPHPBORANGK2K3 WHERE ID_BORANGK2K3 = "
					+ id_borangk2k3;
			myLogger.info("DELETE TBLPHPBORANGK2K3 :: " + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE HAPUS BORANGK2K3

	// EDIT BORANG
	public void simpanEditBorang(String usid, String id_borangk2k3,
			String txdTarikhHantar, String txtNamaBarge, String txtLokasi,
			String txtKuantiti, String txtAnggaranRoyalti, String txtNoKastam, String txdHariHantar,String idJamDari,String idMinitDari)
			throws Exception {

		String TH = "to_date('" + txdTarikhHantar + "','dd/MM/yyyy')";

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_borangk2k3", id_borangk2k3);
			r.add("tarikh_hantar", r.unquote(TH));
			r.add("nama_barge", txtNamaBarge);
			r.add("lokasi_dibekalkan", txtLokasi);
			r.add("kuantiti", txtKuantiti);
			r.add("anggaran_royalti", txtAnggaranRoyalti);
			r.add("no_kastam", txtNoKastam);
			r.add("hari_hantar", txdHariHantar);
			r.add("jam_hantar", idJamDari);
			r.add("minit_hantar", idMinitDari);
			r.add("id_kemaskini", usid);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("Tblphpborangk2k3");
			myLogger.info("SQL UPDATE Tblphpborangk2k3 :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	} // CLOSE EDIT BORANG

	// EDIT LAPORAN PASIR
	public void updateJumlahKiraan(String usid, String id_laporanpasir,
			Double jumKuantiti, Double jumAnggaran_royalti) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_laporanpasir", id_laporanpasir);
			r.add("jumlah_kuantiti", jumKuantiti);
			r.add("jumlah_royalti", jumAnggaran_royalti);
			r.add("id_kemaskini", usid);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("Tblphplaporanpasir");
			myLogger.info("SQL UPDATE LAPORAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	} // CLOSE EDIT LAPORAN PASIR
	
	// SIMPAN DOKUMEN 
		public static String simpanDokumen(String usid, String id_laporanpasir,
				String txtNamaDokumen, String txtCatatan) throws Exception {

			Db db = null;
			Connection conn = null;
			String sql = "";
			String output = "";

			try {
				long id_dokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");

				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				// TBLPHPDOKUMEN
				r = new SQLRenderer();
				r.add("id_laporanpasir", id_laporanpasir);
				r.add("id_dokumen", id_dokumen);
				r.add("nama_dokumen", txtNamaDokumen);
				r.add("catatan", txtCatatan);
				
				r.add("id_masuk", usid);
				r.add("tarikh_masuk", r.unquote("sysdate"));
				r.add("id_kemaskini", usid);
				r.add("tarikh_kemaskini", r.unquote("sysdate")); 
				sql = r.getSQLInsert("Tblphpdokumen");
				myLogger.info("SQL SIMPAN DOKUMEN :" + sql.toUpperCase());
				stmt.executeUpdate(sql);

				output = "" + id_dokumen;

				conn.commit();
			} catch (SQLException se) {
				try {
					conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:" + se2.getMessage());
				}
				throw new Exception("Ralat Pendaftaran Pengeluaran Pasir Laut:"
						+ se.getMessage());
			} finally {
				if (db != null)
					db.close();
			}
			return output;
		} // CLOSE DOKUMEN
		
		// EDIT BORANG
		public void simpanKemaskiniDokumen(String usid, String id_dokumen, String txtNamaDokumen, String txtCatatan)throws Exception {

			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("id_dokumen", id_dokumen);
				r.add("nama_dokumen", txtNamaDokumen);
				r.add("catatan", txtCatatan);
				r.add("id_kemaskini", usid);
				r.add("tarikh_kemaskini", r.unquote("sysdate"));
				sql = r.getSQLUpdate("Tblphpdokumen");
				myLogger.info("SQL UPDATE Tblphpdokumen :" + sql.toUpperCase());
				stmt.executeUpdate(sql);
			} finally {
				if (db != null)
					db.close();
			}
		} // CLOSE EDIT BORANG
		
		// HAPUS DOKUMEN
		public void deleteDokumen(String id_dokumen) throws Exception {
			Db db = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				String sql = "DELETE FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"+ id_dokumen +"'";
				myLogger.info("DELETE TBLPHPDOKUMEN :: " + sql);
				stmt.executeUpdate(sql);
			} finally {
				if (db != null)
					db.close();
			}
		}// CLOSE HAPUS DOKUMEN

}
