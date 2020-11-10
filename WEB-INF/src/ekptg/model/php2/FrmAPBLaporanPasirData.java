package ekptg.model.php2;

/*
 * AUTHOR: NORZAILY JASMI
 * DATE: 22.06.2010
 */
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;

public class FrmAPBLaporanPasirData extends EkptgCache implements Serializable {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmAPBLaporanPasirData.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// DECLARATION
	public Vector list = null;
	public Vector listCarian = null;
	public Vector listCarianLaporan = null;
	public Vector getListLaporan = null;
	public Vector getListPasir = null;
	public Vector getListDokumen = null;
	private static Vector MaklumatPelesen = null;
	private static Vector MaklumatLaporan = null;
	private static Vector MaklumatPasir = null;
	private static Vector listGetUserId = null;
	private static Vector MaklumatKiraan = null;
	private static Vector MaklumatDokumen = null;

	// RETURN VALUES
	public Vector getListCarian() {
		return listCarian;
	}

	public Vector getListCarianLaporan() {
		return listCarianLaporan;
	}

	public static Vector getMaklumatPelesen() {
		return MaklumatPelesen;
	}

	public static Vector getMaklumatLaporan() {
		return MaklumatLaporan;
	}

	public static Vector getMaklumatPasir() {
		return MaklumatPasir;
	}

	public static Vector getUserIds() {
		return listGetUserId;
	}

	public static Vector getJumlahKiraan() {
		return MaklumatKiraan;
	} 
	
	public static Vector getMaklumatDokumen() {
		return MaklumatDokumen;
	}

	// getListPemohon
	public Vector getListPemohon() throws Exception {
		Db db = null;
		String sql = "";
		try {
			list = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_JADUALKEDUALESENAPB,B.NAMA,A.NO_SIRI_LESEN ";
			sql += " FROM TBLPHPJADUALKEDUALESENAPB A, ";
			sql += " TBLPHPPEMEGANG B ";
			sql += " WHERE A.ID_JADUALKEDUALESENAPB = B.ID_JADUALKEDUALESENAPB ";
			sql += " ORDER BY A.TARIKH_KEMASKINI DESC ";

			myLogger.info("SQL DEFAULT :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_jadualkedualesenAPB",
						rs.getString("id_jadualkedualesenAPB") == null ? "-"
								: rs.getString("id_jadualkedualesenAPB"));
				h.put("nama",
						rs.getString("nama") == null ? "-" : rs
								.getString("nama"));
				if (rs.getString("no_siri_lesen") == null) {
					h.put("no_siri_lesen", "No Lesen Tiada");
				} else {
					h.put("no_siri_lesen", rs.getString("no_siri_lesen"));
				}
				list.addElement(h);
				bil++;
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getListPemohon

	// setCarianFail
	public Vector setCarianFail(String txtNamaPelesen, String txtNoLesen)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			listCarian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			txtNamaPelesen = txtNamaPelesen.trim();
			txtNoLesen = txtNoLesen.trim();

			sql = " SELECT A.ID_JADUALKEDUALESENAPB,B.NAMA,A.NO_SIRI_LESEN ";
			sql += " FROM TBLPHPJADUALKEDUALESENAPB A, ";
			sql += " TBLPHPPEMEGANG B ";
			sql += " WHERE A.ID_JADUALKEDUALESENAPB = B.ID_JADUALKEDUALESENAPB ";

			// NAMA PELESEN
			if (txtNamaPelesen != null) {
				if (!txtNamaPelesen.trim().equals("")) {
					sql = sql + " AND UPPER(B.NAMA) LIKE '%"
							+ txtNamaPelesen.toUpperCase() + "%'";
				}
			}

			// NO LESEN
			if (txtNoLesen != null) {
				if (!txtNoLesen.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_SIRI_LESEN) LIKE '%"
							+ txtNoLesen.toUpperCase() + "%'";
				}
			}

			// SORTING
			sql += " ORDER BY A.TARIKH_KEMASKINI DESC ";

			myLogger.info("SQL CARIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_jadualkedualesenAPB",
						rs.getString("id_jadualkedualesenAPB") == null ? "-"
								: rs.getString("id_jadualkedualesenAPB"));
				h.put("nama",
						rs.getString("nama") == null ? "-" : rs
								.getString("nama"));
				if (rs.getString("no_siri_lesen") == null) {
					h.put("no_siri_lesen", "No Lesen Tiada");
				} else {
					h.put("no_siri_lesen", rs.getString("no_siri_lesen"));
				}
				listCarian.addElement(h);
				bil++;
			}
			return listCarian;
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE setCarianFail

	// getMaklumatPelesen
	public static void getMaklumatPelesen(String id_jadualkedualesenAPB)
			throws Exception {
		Db db = null;
		String sql = "";
		try {
			MaklumatPelesen = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_JADUALKEDUALESENAPB,B.NAMA,A.NO_SIRI_LESEN,B.NO_TEL_BIMBIT,B.NO_TEL_PEJABAT,B.NO_TEL_RUMAH, ";
			sql += " B.ALAMAT1_TETAP,B.ALAMAT2_TETAP,B.ALAMAT3_TETAP,B.POSKOD_TETAP,B.ID_BANDARTETAP,B.ID_NEGERITETAP,C1.KETERANGAN AS BANDARTETAP,D1.NAMA_NEGERI AS NEGERITETAP, ";
			sql += " B.ALAMAT1_SURAT,B.ALAMAT2_SURAT,B.ALAMAT3_SURAT,B.POSKOD_SURAT,B.ID_BANDARSURAT,B.ID_NEGERISURAT,C2.KETERANGAN AS BANDARSURAT,D2.NAMA_NEGERI AS NEGERISURAT, ";
			sql += " A.KADAR_ROYALTI ";
			sql += " FROM TBLPHPJADUALKEDUALESENAPB A, ";
			sql += " TBLPHPPEMEGANG B, ";
			sql += " TBLRUJBANDAR C1,TBLRUJNEGERI D1,TBLRUJBANDAR C2,TBLRUJNEGERI D2 ";
			sql += " WHERE A.ID_JADUALKEDUALESENAPB = B.ID_JADUALKEDUALESENAPB ";
			sql += " AND B.ID_BANDARTETAP = C1.ID_BANDAR(+) ";
			sql += " AND D1.ID_NEGERI = B.ID_NEGERITETAP(+) ";
			sql += " AND B.ID_BANDARSURAT = C2.ID_BANDAR(+) ";
			sql += " AND B.ID_NEGERISURAT = D2.ID_NEGERI(+) ";
			sql += " AND A.ID_JADUALKEDUALESENAPB = '" + id_jadualkedualesenAPB
					+ "' ";

			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("Pelesen :: " + sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_jadualkedualesenAPB",
						rs.getString("id_jadualkedualesenAPB") == null ? ""
								: rs.getString("id_jadualkedualesenAPB"));
				h.put("nama",
						rs.getString("nama") == null ? "" : rs
								.getString("nama"));
				h.put("no_siri_lesen",
						rs.getString("no_siri_lesen") == null ? "-" : rs
								.getString("no_siri_lesen"));
				h.put("no_tel_bimbit",
						rs.getString("no_tel_bimbit") == null ? "-" : rs
								.getString("no_tel_bimbit"));
				h.put("no_tel_pejabat",
						rs.getString("no_tel_pejabat") == null ? "-" : rs
								.getString("no_tel_pejabat"));
				h.put("no_tel_rumah",
						rs.getString("no_tel_rumah") == null ? "-" : rs
								.getString("no_tel_rumah"));
				h.put("alamat1_tetap",
						rs.getString("alamat1_tetap") == null ? "" : rs
								.getString("alamat1_tetap"));
				h.put("alamat2_tetap",
						rs.getString("alamat2_tetap") == null ? "" : rs
								.getString("alamat2_tetap"));
				h.put("alamat3_tetap",
						rs.getString("alamat3_tetap") == null ? "" : rs
								.getString("alamat3_tetap"));
				h.put("poskod_tetap", rs.getString("poskod_tetap") == null ? ""
						: rs.getString("poskod_tetap"));
				h.put("bandartetap", rs.getString("bandartetap") == null ? ""
						: rs.getString("bandartetap"));
				h.put("negeritetap", rs.getString("negeritetap") == null ? ""
						: rs.getString("negeritetap"));
				h.put("alamat1_surat",
						rs.getString("alamat1_surat") == null ? "" : rs
								.getString("alamat1_surat"));
				h.put("alamat2_surat",
						rs.getString("alamat2_surat") == null ? "" : rs
								.getString("alamat2_surat"));
				h.put("alamat3_surat",
						rs.getString("alamat3_surat") == null ? "" : rs
								.getString("alamat3_surat"));
				h.put("poskod_surat", rs.getString("poskod_surat") == null ? ""
						: rs.getString("poskod_surat"));
				h.put("bandarsurat", rs.getString("bandarsurat") == null ? ""
						: rs.getString("bandarsurat"));
				h.put("negerisurat", rs.getString("negerisurat") == null ? ""
						: rs.getString("negerisurat"));
				h.put("kadar_royalti",
						rs.getString("kadar_royalti") == null ? "0" : Double
								.parseDouble(rs.getString("kadar_royalti")));
				MaklumatPelesen.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getMaklumatPelesen

	// getListLaporan
	public Vector getListLaporan(String id_jadualkedualesenAPB)
			throws Exception {
		Db db = null;
		String sql = "";
		try {
			getListLaporan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_LAPORANPASIR,B.NAMA_BULAN,A.TAHUN_PENGAMBILAN,A.BULAN_PENGAMBILAN, A.ID_JADUALKEDUALESENAPB, ";
			sql += " A.JUMLAH_KUANTITI, A.JUMLAH_ROYALTI ";
			sql += " FROM TBLPHPLAPORANPASIR A, TBLRUJBULAN B ";
			sql += " WHERE A.BULAN_PENGAMBILAN = B.ID_BULAN ";
			sql += " AND A.ID_JADUALKEDUALESENAPB = '" + id_jadualkedualesenAPB
					+ "' ";
			sql += " ORDER BY A.TAHUN_PENGAMBILAN ASC, A.BULAN_PENGAMBILAN ASC ";

			myLogger.info("SQL getListLaporan :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_jadualkedualesenAPB",
						rs.getString("ID_JADUALKEDUALESENAPB") == null ? ""
								: rs.getString("ID_JADUALKEDUALESENAPB"));
				h.put("id_laporanpasir",
						rs.getString("ID_LAPORANPASIR") == null ? "" : rs
								.getString("ID_LAPORANPASIR"));
				h.put("nama_bulan", rs.getString("NAMA_BULAN") == null ? ""
						: rs.getString("NAMA_BULAN"));
				h.put("tahun_pengambilan",
						rs.getString("TAHUN_PENGAMBILAN") == null ? "" : rs
								.getString("TAHUN_PENGAMBILAN"));
				h.put("bulan_pengambilan",
						rs.getString("BULAN_PENGAMBILAN") == null ? "" : rs
								.getString("BULAN_PENGAMBILAN"));
				h.put("jumlah_kuantiti",
						rs.getString("JUMLAH_KUANTITI") == null ? "" : rs
								.getString("JUMLAH_KUANTITI"));
				h.put("jumlah_royalti",
						rs.getString("JUMLAH_ROYALTI") == null ? "" : rs
								.getString("JUMLAH_ROYALTI"));
				getListLaporan.addElement(h);
				bil++;
			}
			return getListLaporan;
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getListLaporan

	// setCarianLaporan
	public Vector setCarianLaporan(String socBulan, String txtTahun,
			String id_jadualkedualesenAPB) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listCarianLaporan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			txtTahun = txtTahun.trim();

			sql = " SELECT A.ID_LAPORANPASIR,B.NAMA_BULAN,A.TAHUN_PENGAMBILAN, A.JUMLAH_KUANTITI, A.JUMLAH_ROYALTI ";
			sql += " FROM TBLPHPLAPORANPASIR A, ";
			sql += " TBLRUJBULAN B ";
			sql += " WHERE A.BULAN_PENGAMBILAN = B.ID_BULAN ";
			sql += " AND A.ID_JADUALKEDUALESENAPB = '" + id_jadualkedualesenAPB
					+ "' ";

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("") && !socBulan.trim().equals("0")) {
					sql = sql + " AND A.BULAN_PENGAMBILAN = '" + socBulan
							+ "'  ";
				}
			}

			// TAHUN
			if (txtTahun != null) {
				if (!txtTahun.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAHUN_PENGAMBILAN) LIKE '%"
							+ txtTahun + "%'";
				}
			}

			// SORTING
			sql += " ORDER BY A.TAHUN_PENGAMBILAN ASC, A.BULAN_PENGAMBILAN ASC ";

			myLogger.info("SQL CARIAN LAPORAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_laporanpasir",
						rs.getString("id_laporanpasir") == null ? "-" : rs
								.getString("id_laporanpasir"));
				h.put("nama_bulan", rs.getString("nama_bulan") == null ? "-"
						: rs.getString("nama_bulan"));
				h.put("tahun_pengambilan",
						rs.getString("tahun_pengambilan") == null ? "-" : rs
								.getString("tahun_pengambilan"));
				h.put("jumlah_kuantiti", rs.getString("JUMLAH_KUANTITI") == null ? ""
						: rs.getString("JUMLAH_KUANTITI"));
				h.put("jumlah_royalti",
						rs.getString("JUMLAH_ROYALTI") == null ? "" : rs
								.getString("JUMLAH_ROYALTI"));
				listCarianLaporan.addElement(h);
				bil++;
			}
			return listCarianLaporan;
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE setCarianLaporan

	public static void setGetUserId(String userid) throws Exception {

		listGetUserId = new Vector();

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT DISTINCT B.ID_DAERAH,B.KOD_DAERAH,C.ID_NEGERI,A.ID_PEJABATJKPTG,C.KOD_MAMPU ";
			sql += " FROM USERS_INTERNAL A, TBLRUJDAERAH B, TBLRUJNEGERI C ";
			sql += " WHERE A.ID_NEGERI = C.ID_NEGERI(+) ";
			sql += " AND A.ID_DAERAH = B.ID_DAERAH(+) ";
			sql += " AND A.USER_ID = '" + userid + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idDaerah",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("kodDaerah",
						rs.getString("KOD_DAERAH") == null ? "" : rs
								.getString("KOD_DAERAH"));
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("idpejabat", rs.getString("ID_PEJABATJKPTG") == null ? ""
						: rs.getString("ID_PEJABATJKPTG"));
				h.put("kodnegeri",
						rs.getString("KOD_MAMPU") == null ? "" : rs
								.getString("KOD_MAMPU"));
				listGetUserId.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	// getMaklumatLaporan
	public static void getMaklumatLaporan(String id_laporanpasir)
			throws Exception {
		Db db = null;
		String sql = "";
		try {
			MaklumatLaporan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_LAPORANPASIR,A.BULAN_PENGAMBILAN,A.TAHUN_PENGAMBILAN,A.JUMLAH_KUANTITI, A.KONTRAKTOR, A.PEMBELI_PASIR,";
			sql += " A.JUMLAH_ROYALTI,A.ID_UNITISIPADU ";
			sql += " FROM TBLPHPLAPORANPASIR A ";
			sql += " WHERE A.ID_LAPORANPASIR = '" + id_laporanpasir + "' ";

			myLogger.info("SQL MaklumatLaporan :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_laporanpasir", rs.getString("id_laporanpasir") == null ? "" : rs.getString("id_laporanpasir"));
				h.put("bulan_pengambilan", rs.getString("bulan_pengambilan") == null ? "" : rs.getString("bulan_pengambilan"));
				h.put("tahun_pengambilan", rs.getString("tahun_pengambilan") == null ? "" : rs.getString("tahun_pengambilan"));
				h.put("jumlah_kuantiti", rs.getString("jumlah_kuantiti") == null ? "" : rs.getString("jumlah_kuantiti"));
				
				h.put("kontraktor", rs.getString("KONTRAKTOR") == null ? "" : rs.getString("KONTRAKTOR"));
				h.put("pembeli", rs.getString("PEMBELI_PASIR") == null ? "" : rs.getString("PEMBELI_PASIR"));
				h.put("jumlah_royalti", rs.getString("jumlah_royalti") == null ? "" : Double.parseDouble(rs.getString("jumlah_royalti")));
				h.put("id_unitisipadu", rs.getString("id_unitisipadu") == null ? "" : rs.getString("id_unitisipadu"));
				MaklumatLaporan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getMaklumatLaporan

	// getListLaporan
	public Vector getListPasir(String id_laporanpasir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getListPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_BORANGK2K3,A.ID_LAPORANPASIR,A.TARIKH_HANTAR,A.NAMA_BARGE,A.LOKASI_DIBEKALKAN,A.AKUAN_KASTAM, A.HARI_HANTAR, A.JAM_HANTAR, A.MINIT_HANTAR,";
			sql += " A.KUANTITI,A.ANGGARAN_ROYALTI,A.NO_KASTAM ";
			sql += " FROM TBLPHPBORANGK2K3 A ";
			sql += " WHERE A.ID_LAPORANPASIR = '" + id_laporanpasir + "' ";

			myLogger.info("SQL getListPasir :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_borangk2k3", rs.getString("ID_BORANGK2K3") == null ? "" : rs.getString("ID_BORANGK2K3"));
				h.put("id_laporanpasir", rs.getString("ID_LAPORANPASIR") == null ? "" : rs.getString("ID_LAPORANPASIR"));
				h.put("tarikh_hantar", rs.getString("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("nama_barge", rs.getString("NAMA_BARGE") == null ? "" : rs.getString("NAMA_BARGE"));
				h.put("lokasi_dibekalkan", rs.getString("LOKASI_DIBEKALKAN") == null ? "" : rs.getString("LOKASI_DIBEKALKAN"));
				h.put("akuan_kastam", rs.getString("AKUAN_KASTAM") == null ? "" : rs.getString("AKUAN_KASTAM"));
				h.put("kuantiti", rs.getString("KUANTITI") == null ? "" : rs.getString("KUANTITI"));
				h.put("anggaran_royalti", rs.getString("ANGGARAN_ROYALTI") == null ? "" : Double.parseDouble(rs.getString("ANGGARAN_ROYALTI")));
				h.put("no_kastam", rs.getString("NO_KASTAM") == null ? "" : rs.getString("NO_KASTAM"));
				
				h.put("lokasi_dibekalkan", rs.getString("LOKASI_DIBEKALKAN") == null ? "" : rs.getString("LOKASI_DIBEKALKAN"));
				h.put("akuan_kastam", rs.getString("AKUAN_KASTAM") == null ? "" : rs.getString("AKUAN_KASTAM"));
				getListPasir.addElement(h);
				bil++;
			}
			return getListPasir;
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getListPasir

	// getMaklumatPasir
	public static void getMaklumatPasir(String id_borangk2k3) throws Exception {
		Db db = null;
		String sql = "";
		try {
			MaklumatPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_BORANGK2K3, A.ID_LAPORANPASIR, A.TARIKH_HANTAR,A.NAMA_BARGE,A.LOKASI_DIBEKALKAN,A.AKUAN_KASTAM, A.HARI_HANTAR, A.JAM_HANTAR, A.MINIT_HANTAR,";
			sql += " A.KUANTITI,A.ANGGARAN_ROYALTI,A.NO_KASTAM ";
			sql += " FROM TBLPHPBORANGK2K3 A ";
			sql += " WHERE A.ID_BORANGK2K3 = '" + id_borangk2k3 + "' ";

			myLogger.info("SQL MaklumatPasir :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_borangk2k3", rs.getString("ID_BORANGK2K3") == null ? "" : rs.getString("ID_BORANGK2K3"));
				h.put("id_laporanpasir", rs.getString("ID_LAPORANPASIR") == null ? "" : rs.getString("ID_LAPORANPASIR"));
				h.put("tarikh_hantar", rs.getString("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("nama_barge", rs.getString("NAMA_BARGE") == null ? "" : rs.getString("NAMA_BARGE"));
				h.put("lokasi_dibekalkan", rs.getString("LOKASI_DIBEKALKAN") == null ? "" : rs.getString("LOKASI_DIBEKALKAN"));
				h.put("akuan_kastam", rs.getString("AKUAN_KASTAM") == null ? "" : rs.getString("AKUAN_KASTAM"));
				h.put("kuantiti", rs.getString("KUANTITI") == null ? "" : rs.getString("KUANTITI"));
				h.put("hari_hantar", rs.getString("HARI_HANTAR") == null ? "" : rs.getString("HARI_HANTAR"));
				h.put("jam_hantar", rs.getString("JAM_HANTAR") == null ? "" : rs.getString("JAM_HANTAR"));
				h.put("minit_hantar", rs.getString("MINIT_HANTAR") == null ? "" : rs.getString("MINIT_HANTAR"));
				h.put("anggaran_royalti", rs.getString("ANGGARAN_ROYALTI") == null ? "" : Double.parseDouble(rs.getString("ANGGARAN_ROYALTI")));
				h.put("no_kastam", rs.getString("NO_KASTAM") == null ? "" : rs.getString("NO_KASTAM"));
				MaklumatPasir.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getMaklumatPasir

	// getJumlahKiraan
	public static void getJumlahKiraan(String id_laporanpasir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			MaklumatKiraan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT SUM(B.KUANTITI) AS KUANTITI,SUM(B.ANGGARAN_ROYALTI) AS ANGGARAN_ROYALTI ";
			sql += " FROM TBLPHPLAPORANPASIR A,TBLPHPBORANGK2K3 B ";
			sql += " WHERE A.ID_LAPORANPASIR = B.ID_LAPORANPASIR ";
			sql += " AND A.ID_LAPORANPASIR = '" + id_laporanpasir + "' ";

			myLogger.info("SQL MaklumatKiraan :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("jumKuantiti", rs.getString("KUANTITI") == null ? "0"
						: rs.getString("KUANTITI"));
				h.put("jumAnggaran_royalti",
						rs.getString("ANGGARAN_ROYALTI") == null ? "0" : rs
								.getString("ANGGARAN_ROYALTI"));
				MaklumatKiraan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getJumlahKiraan

	// CHECKING EXISTING BULAN
	public static boolean isBulanExist(String id_jadualkedualesenAPB,
			String socBulan, String txtTahun) {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT bulan_pengambilan,tahun_pengambilan FROM Tblphplaporanpasir ";
			sql += " WHERE bulan_pengambilan = '" + socBulan + "' ";
			sql += " and tahun_pengambilan = '" + txtTahun + "' ";
			sql += " and id_jadualkedualesenapb = '" + id_jadualkedualesenAPB
					+ "' ";

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
	
	public static boolean isBulanExist(String id_jadualkedualesenAPB,
			String txtTarikh) {
		Db db = null;
		String sql = "";
		
		String bulan = "";
		String tahun = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT bulan_pengambilan,tahun_pengambilan FROM Tblphplaporanpasir ";
			sql += " WHERE bulan_pengambilan = '" + bulan + "' ";
			sql += " and tahun_pengambilan = '" + tahun + "' ";
			sql += " and id_jadualkedualesenapb = '" + id_jadualkedualesenAPB
					+ "' ";

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
	
	// getMaklumatDokumen
	public static void getMaklumatDokumen(String id_dokumen) throws Exception {
		Db db = null;
		String sql = "";
		try {
			MaklumatDokumen = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_DOKUMEN, A.NAMA_DOKUMEN, A.CATATAN, B.ID_LAPORANPASIR "
				+	" FROM TBLPHPDOKUMEN A, TBLPHPLAPORANPASIR B"
				+	" WHERE B.ID_LAPORANPASIR = A.ID_LAPORANPASIR"
				+	" AND A.ID_DOKUMEN = '"+ id_dokumen + "'"; 
				
			myLogger.info("SQL Dokumen :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_dokumen", rs.getString("ID_DOKUMEN") == null ? "" : rs.getString("ID_DOKUMEN"));
				h.put("id_laporanpasir", rs.getString("ID_LAPORANPASIR") == null ? "" : rs.getString("ID_LAPORANPASIR"));
				h.put("nama_dokumen", rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				MaklumatDokumen.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}// CLOSE getMaklumatDokumen
	
	// getListDokumen
	public Vector getListDokumen(String id_laporanpasir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getListDokumen = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_DOKUMEN, A.NAMA_DOKUMEN, A.CATATAN, B.ID_LAPORANPASIR "
					+	" FROM TBLPHPDOKUMEN A, TBLPHPLAPORANPASIR B "
					+	" WHERE B.ID_LAPORANPASIR = A.ID_LAPORANPASIR "
					+	" AND A.ID_LAPORANPASIR = '"+ id_laporanpasir + "'"; 
			myLogger.info("SQL getListDokumen :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_dokumen", rs.getString("ID_DOKUMEN") == null ? "" : rs.getString("ID_DOKUMEN"));
				h.put("id_laporanpasir", rs.getString("ID_LAPORANPASIR") == null ? "" : rs.getString("ID_LAPORANPASIR"));
				h.put("nama_dokumen", rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				getListDokumen.addElement(h);
				bil++;
			}
			return getListDokumen;
		} finally {
			if (db != null)
				db.close();
		}
	}

}
