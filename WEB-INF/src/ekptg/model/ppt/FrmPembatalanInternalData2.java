package ekptg.model.ppt;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.EkptgCache;

public class FrmPembatalanInternalData2 extends EkptgCache implements
		Serializable {

	static Logger myLogger = Logger.getLogger(FrmPembatalanInternalData2.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	public static final String SEQ_TABLE = "TBLRUJSEQFAIL";

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String cd = dateFormat.format(date);
	
	
	Vector header = null;
	
	public Vector content_header(String id_permohonan) throws Exception {
		header = new Vector();
		Db db = null;
		header.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT SF.ID_SUBURUSANSTATUSFAIL,SF.AKTIF,SF.ID_SUBURUSANSTATUS,P.ID_PERMOHONAN, F.ID_NEGERI, P.ID_NEGERI AS ID_NEGERI_PROJECT, P.NO_PERMOHONAN, "
					+ "P.ID_FAIL,  F.NO_FAIL, F.ID_SUBURUSAN, P.TARIKH_PERMOHONAN, P.ID_STATUS,  F.ID_KEMENTERIAN, "
					+ "P.ID_AGENSI, P.FLAG_PERUNTUKAN, P.FLAG_SEGERA,  P.ID_DAERAH,  Initcap(P.TUJUAN) AS TUJUAN, P.NO_RUJUKAN_SURAT, "
					+ "P.TARIKH_KEHENDAKI,  P.ALAMAT1, P.ALAMAT2, P.ALAMAT3, P.POSKOD, P.ID_MUKIM, "
					+ "Initcap(K.NAMA_KEMENTERIAN) as NAMA_KEMENTERIAN, Initcap(B.NAMA_DAERAH) as NAMA_DAERAH, P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_PTG, "
					+ "P.NO_RUJUKAN_UPT, SU.NAMA_SUBURUSAN, S.KETERANGAN, C.ID_NEGERI,C.NAMA_NEGERI,P.TARIKH_TERIMA,AG.NAMA_AGENSI "
					+ "FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLRUJKEMENTERIAN K,TBLRUJNEGERI C,  "
					+ "TBLRUJDAERAH B,TBLRUJSUBURUSAN SU,TBLRUJSUBURUSANSTATUSFAIL SF,TBLRUJSTATUS S, TBLRUJAGENSI AG  "
					+ "WHERE "
					+ "P.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' "
					+ "AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ "AND P.ID_FAIL = F.ID_FAIL(+) "
					+ "AND SF.ID_FAIL(+) = F.ID_FAIL "
					+ "AND P.ID_DAERAH = B.ID_DAERAH(+) "
					+ "AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN(+) "
					+ "AND P.ID_STATUS = S.ID_STATUS "
					+ "AND F.ID_NEGERI = C.ID_NEGERI(+) "
					+ "AND P.ID_AGENSI = AG.ID_AGENSI(+)";

			myLogger.info("SQL setHeader :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				//SF.ID_SUBURUSANSTATUSFAIL,SF.AKTIV,SF.ID_SUBURUSANSTATUS,
				h.put("ID_SUBURUSANSTATUSFAIL",
						rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? "" : rs
								.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("AKTIF",
						rs.getString("AKTIF") == null ? "" : rs
								.getString("AKTIF"));
				
				h.put("ID_SUBURUSANSTATUS",
						rs.getString("ID_SUBURUSANSTATUS") == null ? "" : rs
								.getString("ID_SUBURUSANSTATUS"));
				
				h.put("ID_NEGERI",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				
				h.put("id_permohonan",
						rs.getString("id_permohonan") == null ? "" : rs
								.getString("id_permohonan"));
				
				
				h.put("id_permohonan",
						rs.getString("id_permohonan") == null ? "" : rs
								.getString("id_permohonan"));
				h.put("no_permohonan",
						rs.getString("no_permohonan") == null ? "-" : rs
								.getString("no_permohonan").toUpperCase());
				h.put("id_fail", rs.getString("id_fail") == null ? "" : rs
						.getString("id_fail").toUpperCase());
				h.put("id_status", rs.getString("id_status"));
				h.put("no_fail", rs.getString("no_fail") == null ? "-" : rs
						.getString("no_fail").toUpperCase());
				h.put("nama_kementerian",
						rs.getString("nama_kementerian") == null ? "-" : rs
								.getString("nama_kementerian").toUpperCase());
				h.put("nama_kementerian1",
						rs.getString("nama_kementerian") == null ? "-" : rs
								.getString("nama_kementerian"));
				h.put("nama_daerah", rs.getString("nama_daerah") == null ? "-"
						: rs.getString("nama_daerah").toUpperCase());
				h.put("nama_daerah1", rs.getString("nama_daerah") == null ? "-"
						: rs.getString("nama_daerah"));
				h.put("no_rujukan_ptd",
						rs.getString("no_rujukan_ptd") == null ? "-" : rs
								.getString("no_rujukan_ptd").toUpperCase());
				h.put("no_rujukan_ptg",
						rs.getString("no_rujukan_ptg") == null ? "-" : rs
								.getString("no_rujukan_ptg").toUpperCase());
				h.put("no_rujukan_upt",
						rs.getString("no_rujukan_upt") == null ? "-" : rs
								.getString("no_rujukan_upt").toUpperCase());
				h.put("nama_suburusan",
						rs.getString("nama_suburusan") == null ? "-" : rs
								.getString("nama_suburusan").toUpperCase());
				h.put("keterangan", rs.getString("keterangan") == null ? "-"
						: rs.getString("keterangan").toUpperCase());
				h.put("nama_agensi", rs.getString("nama_agensi") == null ? "-"
						: rs.getString("nama_agensi").toUpperCase());
				h.put("projek_negeri",
						rs.getString("nama_negeri") == null ? "-" : rs
								.getString("nama_negeri").toUpperCase());
				if (rs.getString("id_suburusan") == null) {
					h.put("id_suburusan", "");
				} else {
					h.put("id_suburusan", rs.getString("id_suburusan"));
				}
				h
						.put("tarikh_permohonan",
								rs.getString("tarikh_permohonan") == null ? "-"
										: Format.format(rs
												.getDate("tarikh_permohonan")));
				h.put("tarikh_terima",
						rs.getString("tarikh_terima") == null ? "-" : Format
								.format(rs.getDate("tarikh_terima")));
				h.put("id_status", rs.getString("id_status") == null ? "" : rs
						.getString("id_status"));
				if (rs.getString("id_kementerian") == null) {
					h.put("id_kementerian", "");
				} else {
					h.put("id_kementerian", rs.getString("id_kementerian"));
				}
				if (rs.getString("id_agensi") == null) {
					h.put("id_agensi", "");
				} else {
					h.put("id_agensi", rs.getString("id_agensi"));
				}
				h.put("flag_peruntukan",
						rs.getString("flag_peruntukan") == null ? "" : rs
								.getString("flag_peruntukan"));
				h.put("flag_segera", rs.getString("flag_segera") == null ? ""
						: rs.getString("flag_segera"));
				if (rs.getString("ID_NEGERI_PROJECT") == null) {
					h.put("id_negeri_projek", "");
				} else {
					h
							.put("id_negeri_projek", rs
									.getString("ID_NEGERI_PROJECT"));
				}
				if (rs.getString("id_daerah") == null) {
					h.put("id_daerah", "");
				} else {
					h.put("id_daerah", rs.getString("id_daerah"));
				}
				h.put("tujuan", rs.getString("tujuan") == null ? "-" : rs
						.getString("tujuan").toUpperCase());
				h.put("tujuan_mmk", rs.getString("tujuan") == null ? "-" : rs
						.getString("tujuan"));
				h.put("no_rujukan_surat",
						rs.getString("no_rujukan_surat") == null ? "-" : rs
								.getString("no_rujukan_surat").toUpperCase());
				h.put("tarikh_kehendaki",
						rs.getString("tarikh_kehendaki") == null ? "-" : Format
								.format(rs.getDate("tarikh_kehendaki")));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1").toUpperCase());
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2").toUpperCase());
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3").toUpperCase());
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				if (rs.getString("id_negeri") == null) {
					h.put("id_negeri", "");
				} else {
					h.put("id_negeri", rs.getString("id_negeri"));
				}
				if (rs.getString("id_mukim") == null) {
					h.put("id_mukim", "");
				} else {
					h.put("id_mukim", rs.getString("id_mukim"));
				}
				header.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return header;
	}
	
	
	Vector senarai_pembatalan = null;

	public Vector senarai_pembatalan(String id_permohonan) throws Exception {
		senarai_pembatalan = new Vector();
		Db db = null;
		senarai_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT F.ID_FAIL,B.ID_PEMBATALAN, B.NO_RUJUKAN_SURAT, B.TARIKH_PEMBATALAN," +
				//	"B.TARIKH_TERIMA, " +
					"B.NO_PEMBATALAN,"
					+ " B.JENIS_PEMBATALAN, B.SEBAB_PEMBATALAN, B.ID_STATUS AS ID_BATAL_STATUS, B.TARIKH_SURAT,"
					+ "B.TARIKH_KUATKUASA, B.NO_RUJUKAN_SURAT, B.ID_PERMOHONAN,P.ID_STATUS "
					+ "FROM TBLPPTPEMBATALAN B, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL "
					+ "AND P.ID_PERMOHONAN = '"
					+ id_permohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("TARIKH_PEMBATALAN", rs
						.getString("TARIKH_PEMBATALAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PEMBATALAN")));
				h.put("NO_PEMBATALAN",
						rs.getString("NO_PEMBATALAN") == null ? "" : rs
								.getString("NO_PEMBATALAN").toUpperCase());
				h.put("JENIS_PEMBATALAN",
						rs.getString("JENIS_PEMBATALAN") == null ? "" : rs
								.getString("JENIS_PEMBATALAN")
								.toUpperCase());
				h.put("TARIKH_SURAT", rs.getString("TARIKH_SURAT") == null ? ""
						: Format.format(rs.getDate("TARIKH_SURAT")));
				h.put("TARIKH_KUATKUASA",
						rs.getString("TARIKH_KUATKUASA") == null ? "" : Format
								.format(rs.getDate("TARIKH_KUATKUASA")));
				h.put("TARIKH_TERIMA_SURAT",
						rs.getString("TARIKH_TERIMA") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("SEBAB_PEMBATALAN",
						rs.getString("SEBAB_PEMBATALAN") == null ? "" : rs
								.getString("SEBAB_PEMBATALAN")
								.toUpperCase());
				h.put("ID_BATAL_STATUS",
						rs.getString("ID_BATAL_STATUS") == null ? "" : rs
								.getString("ID_BATAL_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT").toUpperCase());
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT").toUpperCase());

				senarai_pembatalan.addElement(h);
			}
			return senarai_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector maklumat_pembatalan = null;

	public Vector maklumat_pembatalan(String id_penarikan) throws Exception {
		maklumat_pembatalan = new Vector();
		Db db = null;
		maklumat_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT F.ID_FAIL,B.ID_PEMBATALAN, B.NO_RUJUKAN_SURAT, B.TARIKH_PEMBATALAN,B.TARIKH_TERIMA, B.NO_PEMBATALAN,"
					+ " B.JENIS_PEMBATALAN, Initcap(B.SEBAB_PEMBATALAN) AS SEBAB_PEMBATALAN, B.ID_STATUS AS ID_BATAL_STATUS, B.TARIKH_SURAT,"
					+ "B.TARIKH_KUATKUASA, B.NO_RUJUKAN_SURAT, B.ID_PERMOHONAN,P.ID_STATUS "
					+ "FROM TBLPPTPEMBATALAN B, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL "
					+ "AND B.ID_PEMBATALAN = '" + id_penarikan + "'";

			myLogger.info("MAKLUMAT_PEMBATALAN:" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				h.put("TARIKH_PEMBATALAN", rs
						.getString("TARIKH_PEMBATALAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PEMBATALAN")));
				h.put("NO_PEMBATALAN",
						rs.getString("NO_PEMBATALAN") == null ? "" : rs
								.getString("NO_PEMBATALAN").toUpperCase());
				h.put("JENIS_PEMBATALAN",
						rs.getString("JENIS_PEMBATALAN") == null ? "" : rs
								.getString("JENIS_PEMBATALAN")
								.toUpperCase());
				h.put("TARIKH_SURAT", rs.getString("TARIKH_SURAT") == null ? ""
						: Format.format(rs.getDate("TARIKH_SURAT")));
				h.put("TARIKH_KUATKUASA",
						rs.getString("TARIKH_KUATKUASA") == null ? "" : Format
								.format(rs.getDate("TARIKH_KUATKUASA")));
				h.put("TARIKH_TERIMA_SURAT",
						rs.getString("TARIKH_TERIMA") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("SEBAB_PEMBATALAN",
						rs.getString("SEBAB_PEMBATALAN") == null ? "" : rs
								.getString("SEBAB_PEMBATALAN")
								.toUpperCase());
				h.put("SEBAB_PEMBATALAN1",
						rs.getString("SEBAB_PEMBATALAN") == null ? "" : rs
								.getString("SEBAB_PEMBATALAN").toLowerCase());
				h.put("ID_BATAL_STATUS",
						rs.getString("ID_BATAL_STATUS") == null ? "" : rs
								.getString("ID_BATAL_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT").toUpperCase());
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT").toUpperCase());

				maklumat_pembatalan.addElement(h);
			}
			return maklumat_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	

	Vector senarai_hakmilik_pembatalan = null;

	public Vector senarai_hakmilik_pembatalan(String id_permohonan,
			String id_penarikan) throws Exception {
		senarai_hakmilik_pembatalan = new Vector();
		Db db = null;
		senarai_hakmilik_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT H.ID_HAKMILIK,H.NO_PT,H.NO_LOT,JH.KETERANGAN AS JENIS_HAKMILIK,JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,"
					+ " M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.LUAS_AMBIL, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M, "
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "			
					+ " AND H.ID_HAKMILIK NOT IN "
					+ " (SELECT DISTINCT H.ID_HAKMILIK  "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPEMBATALAN PB,TBLPPTPEMBATALANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D  "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND PH.ID_PEMBATALAN = PB.ID_PEMBATALAN "
					+ " AND PB.ID_PEMBATALAN = '"
					+ id_penarikan
					+ "' )"
					+ " AND H.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' "
					+ " 	 ORDER BY H.NO_PT ASC 	";

			myLogger.info("SENARAI HAKMILIK PENARIKAN" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));			
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM").toUpperCase());

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH").toUpperCase());
				if (!rs.getString("ID_KATEGORITANAH").equals("")
						&& !rs.getString("ID_KATEGORITANAH").equals("0")) {
					if (!rs.getString("ID_KATEGORITANAH").equals("2")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						}
					if (rs.getString("ID_KATEGORITANAH").equals("2")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
				}
				h.put("LUAS_LOT_EDIT", rs.getString("LUAS_LOT") == null ? ""
						: rs.getDouble("LUAS_LOT"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));				
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());

				senarai_hakmilik_pembatalan.addElement(h);
			}
			return senarai_hakmilik_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector senarai_hakmilik_batal_pembatalan = null;

	public Vector senarai_hakmilik_batal_pembatalan(String id_penarikan)
			throws Exception {
		senarai_hakmilik_batal_pembatalan = new Vector();
		Db db = null;
		senarai_hakmilik_batal_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT PH.FLAG_WARTA,PH.ID_PEMBATALANHAKMILIK,PH.LUAS_LOT_TARIK,H.LUAS_AMBIL,PH.LUAS_AMBIL AS LUAS_AMBIL_ASAL,H.LUAS_LOT_TARIK,H.ID_HAKMILIK,PB.ID_PEMBATALAN,H.NO_PT,H.NO_LOT, "
					+ " JH.KETERANGAN AS JENIS_HAKMILIK, "
					+ " JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH  "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPEMBATALAN PB,TBLPPTPEMBATALANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D  "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND PH.ID_PEMBATALAN = PB.ID_PEMBATALAN "
					+ " AND PB.ID_PEMBATALAN = '"
					+ id_penarikan
					+ "' "
					+ " ORDER BY H.NO_PT ASC ";
			myLogger.info("SENARAI SUDAH PENARIKAN :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("FLAG_WARTA", rs.getString("FLAG_WARTA") == null ? ""
						: rs.getString("FLAG_WARTA"));
				h.put("ID_PENARIKANHAKMILIK", rs
						.getString("ID_PEMBATALANHAKMILIK") == null ? "" : rs
						.getString("ID_PEMBATALANHAKMILIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH").toUpperCase());
				if (!rs.getString("ID_KATEGORITANAH").equals("")
						&& !rs.getString("ID_KATEGORITANAH").equals("0")) {
					if (!rs.getString("ID_KATEGORITANAH").equals("2")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getString("LUAS_LOT") + " MP");
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getString("LUAS_AMBIL")
										+ " MP");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getString("LUAS_LOT_TARIK")
												+ " MP");
					}
					if (rs.getString("ID_KATEGORITANAH").equals("2")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getString("LUAS_LOT") + " HEKTAR");
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getString("LUAS_AMBIL")
										+ " HEKTAR");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getString("LUAS_LOT_TARIK")
												+ " HEKTAR");
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getString("LUAS_LOT"));
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getString("LUAS_AMBIL"));
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
									.getString("LUAS_LOT_TARIK"));
				}
				h.put("LUAS_LOT_TARIK_EDIT",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getDouble("LUAS_LOT_TARIK"));
				h.put("LUAS_LOT_TARIK_VALUE",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getString("LUAS_LOT_TARIK"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
				h.put("LUAS_AMBIL_ASAL",
						rs.getString("LUAS_AMBIL_ASAL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL_ASAL"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());
				senarai_hakmilik_batal_pembatalan.addElement(h);
			}
			return senarai_hakmilik_batal_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deleteDokumen(String id_dokumen) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPPTDOKUMEN WHERE ID_DOKUMEN = "
					+ id_dokumen;
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	public void hapus_update_pilih_penarikan(String id_penarikanhakmilik,
			String id_hakmilik, String lot_tarik, String lot_ambil,
			String lot_ambil_asal, String luas_lot, String id_Masuk,
			String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		if (lot_tarik.equals("") || lot_tarik.equals(null)) {
			lot_tarik = "0.0";
		}

		try {
			double jl = 0.0;
			double undo_luasbail = 0.0;
			
			
			db = new Db();
						
			Statement stmt2 = db.getStatement();
			sql2 = "SELECT NVL(LUAS_AMBIL,'0') AS LUAS_AMBIL FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"
					+ id_hakmilik + "'";
			ResultSet rs = stmt2.executeQuery(sql2);
			while (rs.next()) {
				jl = rs.getString("LUAS_AMBIL") == null ? 0.0 : rs
						.getDouble("LUAS_AMBIL");
			}

		
			undo_luasbail = Double.parseDouble(lot_tarik) + jl;

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_HAKMILIK", id_hakmilik);
			r1.add("LUAS_AMBIL", undo_luasbail);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblppthakmilik");
			myLogger.info("**UPDATE SQL UPDATE LOT PENARIKANHAKMILIK :"
					+ sql1.toUpperCase());
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}

	
	
	public void hapus_pilih_pembatalan(String id_penarikanhakmilik)
	throws Exception {
Db db = null;
String sql = "";

try {

	db = new Db();

	Statement stmt = db.getStatement();
	sql = "DELETE FROM TBLPPTPEMBATALANHAKMILIK WHERE ID_PEMBATALANHAKMILIK = "
			+ id_penarikanhakmilik;
	myLogger.info("**UPDATE SQL INSERT LOT PENARIKANHAKMILIK :"
			+ sql.toUpperCase());
	stmt.executeUpdate(sql);

} finally {
	if (db != null)
		db.close();
}

}
	
	
	public void deletePembatalanInternal(String id_pembatalan) throws Exception {
		Db db = null;

		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql6 = "";
		String sql7 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PEMBATALAN", id_pembatalan);
			sql = r.getSQLDelete("tblpptpembatalan");
			stmt.executeUpdate(sql);			

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("ID_PEMBATALAN", id_pembatalan);
			sql1 = r1.getSQLDelete("tblpptdokumen");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql1.toUpperCase());
			stmt1.executeUpdate(sql1);

			Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.update("ID_PEMBATALAN", id_pembatalan);
			r2.add("ID_PEMBATALAN", "");
			r2.add("FLAG_PEMBTALAN", "");
			r2.add("LUAS_LOT_TARIK", "");
			r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));
			sql2 = r2.getSQLUpdate("tblppthakmilik");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql2.toUpperCase());
			stmt2.executeUpdate(sql2);
		} finally {
			if (db != null)
				db.close();
		}
	}


	public void updatePembatalan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_pembatalan = (String) data.get("id_pembatalan");
		String txtNoPembatalan = (String) data.get("txtNoPembatalan");
		String txdTarikhPembatalan = (String) data.get("txdTarikhPembatalan");
		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String sorJenisPembatalan = (String) data.get("sorJenisPembatalan");
		String txtSebabPembatalan = (String) data.get("txtSebabPembatalan");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_PEMBATALAN", id_pembatalan);
			

			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTarikhSurat
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA", "");
			}

			r.add("NO_PEMBATALAN", txtNoPembatalan);
			if (sorJenisPembatalan.equals("sebahagian")) {
				r.add("JENIS_PEMBATALAN", 1);
			} else if (sorJenisPembatalan.equals("keseluruhan")) {
				r.add("JENIS_PEMBATALAN", 2);
			} else {
				r.add("JENIS_PEMBATALAN", "");
			}
			r.add("SEBAB_PEMBATALAN", txtSebabPembatalan);
			r.add("NO_RUJUKAN_SURAT", txtNoRujSurat);
			r.add("ID_PERMOHONAN", id_permohonan);
			r.add("ID_STATUS", "");
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLUpdate("tblpptpembatalan");

			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	public void addPembatalan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		String id_pembatalan = (String) data.get("id_pembatalan");
		String txtNoPembatalan = (String) data.get("txtNoPembatalan");
		String txdTarikhPembatalan = (String) data.get("txdTarikhPembatalan");
		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String sorJenisPembatalan = (String) data.get("sorJenisPembatalan");
		String txtSebabPembatalan = (String) data.get("txtSebabPembatalan");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_Masuk = (String) data.get("id_Masuk");

		int id_stat = 235;
		String kodSubUrusan = "21";
		String kodJabatan = "PB";
		
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String tahun = formatter.format(now);
		String no_penarikan = tahun + "-"
				+ String.format("%06d", getSeqNoAduanOnline(id_stat));

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PEMBATALAN", id_pembatalan);
			r.add("TARIKH_PEMBATALAN", r.unquote("sysdate"));

			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTarikhSurat
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA", "");
			}

			r.add("NO_PEMBATALAN", no_penarikan);
			if (sorJenisPembatalan.equals("sebahagian")) {
				r.add("JENIS_PEMBATALAN", 1);
			} else if (sorJenisPembatalan.equals("keseluruhan")) {
				r.add("JENIS_PEMBATALAN", 2);
			} else {
				r.add("JENIS_PEMBATALAN", "");
			}
			r.add("SEBAB_PEMBATALAN", txtSebabPembatalan);
			r.add("NO_RUJUKAN_SURAT", txtNoRujSurat);
			r.add("ID_PERMOHONAN", id_permohonan);
			r.add("ID_STATUS", "");
			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLInsert("tblpptpembatalan");

			myLogger.info("SQL INSERT PENARIKAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_PERMOHONAN", id_permohonan);
			r1.add("ID_STATUS", 74);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptpermohonan");

			myLogger.info("SQL UPDATE BATALKAN :" + sql.toUpperCase());
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public static synchronized int getSeqNoAduanOnline(int id_jenisaduan)
	throws DbException {
return getSeqNoAduan(id_jenisaduan);
}

public static synchronized int getSeqNoAduan(int id_jenisaduan)
	throws DbException {

Db db = null;
Connection conn = null;
// File f = null;
StringBuffer sb = new StringBuffer();
int seqno = 0;
try {
	db = new Db();
	conn = db.getConnection();
	conn.setAutoCommit(false);

	// f = new File();
	boolean found = false;

	sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQADUAN WHERE ");
	sb.append("ID_JENISADUAN = " + id_jenisaduan);

	ResultSet rs = db.getStatement().executeQuery(sb.toString());

	if (rs.next())
		found = true;
	if (found) {
		// f.increaseSeqAduan(id_jenisaduan);
		increaseSeqAduan(id_jenisaduan);
	} else {
		// f.addNewAduan(id_jenisaduan);
		addNewAduan(id_jenisaduan);
	}
	ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
	if (rs2.next()) {

		seqno = rs2.getInt("NO_TURUTAN");

	}
	conn.commit();

} catch (Exception ex) {
	try {
		conn.rollback();
	} catch (SQLException localSQLException1) {
	}
	throw new DbException(ex.getMessage() + ": " + sb.toString());
} finally {
	if (db != null)
		db.close();
}

return seqno;
}

public static void increaseSeqAduan(int id_jenisaduan) throws DbException {

Db db = null;

StringBuffer sb = new StringBuffer();
sb.append("UPDATE TBLRUJSEQADUAN  SET ");
sb.append("no_turutan = no_turutan + 1 ");
sb.append(" WHERE ");
sb.append("id_jenisaduan = '" + id_jenisaduan + "'");

try {
	db = new Db();
	try {
		db.getStatement().executeUpdate(sb.toString());
	} catch (SQLException x) {
		x.printStackTrace();
	}
} catch (Exception ex) {
	throw new DbException(ex.getMessage() + ": " + sb.toString());
} finally {
	if (db != null)
		db.close();
}
}

public static void addNewAduan(int id_jenisaduan) throws DbException {

Db db = null;
StringBuffer sb = new StringBuffer();
sb.append("INSERT INTO TBLRUJSEQADUAN (ID_JENISADUAN,NO_TURUTAN)");
sb.append(" VALUES (");
sb.append("'" + id_jenisaduan + "'");
sb.append(",1)"); // initial value

try {
	db = new Db();
	db.getStatement().executeUpdate(sb.toString());
} catch (Exception ex) {
	throw new DbException(ex.getMessage() + ": " + sb.toString());
} finally {
	if (db != null)
		db.close();
}
}

// TBLRUJSEQNO_PENARIKAN_BALIK

public static synchronized int getSeqNo_PB(int tahun) throws DbException {
return getSeqNoAduan(tahun);
}

public static synchronized int getSeqNoPB(int tahun) throws DbException {

Db db = null;
Connection conn = null;
// File f = null;
StringBuffer sb = new StringBuffer();
int seqno = 0;
try {
	db = new Db();
	conn = db.getConnection();
	conn.setAutoCommit(false);

	// f = new File();
	boolean found = false;

	sb
			.append("SELECT NO_TURUTAN FROM TBLRUJSEQNO_PENARIKAN_BALIK WHERE ");
	sb.append("TAHUN = " + tahun);

	ResultSet rs = db.getStatement().executeQuery(sb.toString());

	if (rs.next())
		found = true;
	if (found) {
		// f.increaseSeqPB(tahun);
		increaseSeqPB(tahun);
	} else {
		// f.addNewPB(tahun);
		addNewPB(tahun);
	}
	ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
	if (rs2.next()) {

		seqno = rs2.getInt("NO_TURUTAN");

	}
	conn.commit();

} catch (Exception ex) {
	try {
		conn.rollback();
	} catch (SQLException localSQLException1) {
	}
	throw new DbException(ex.getMessage() + ": " + sb.toString());
} finally {
	if (db != null)
		db.close();
}

return seqno;
}

public static void increaseSeqPB(int tahun) throws DbException {

Db db = null;

StringBuffer sb = new StringBuffer();
sb.append("UPDATE TBLRUJSEQNO_PENARIKAN_BALIK  SET ");
sb.append("no_turutan = no_turutan + 1 ");
sb.append(" WHERE ");
sb.append("tahun = '" + tahun + "'");

try {
	db = new Db();
	try {
		db.getStatement().executeUpdate(sb.toString());
	} catch (SQLException x) {
		x.printStackTrace();
	}
} catch (Exception ex) {
	throw new DbException(ex.getMessage() + ": " + sb.toString());
} finally {
	if (db != null)
		db.close();
}
}

public static void addNewPB(int tahun) throws DbException {

Db db = null;
StringBuffer sb = new StringBuffer();
sb.append("INSERT INTO TBLRUJSEQNO_PENARIKAN_BALIK (TAHUN,NO_TURUTAN)");
sb.append(" VALUES (");
sb.append("'" + tahun + "'");
sb.append(",1)"); // initial value

try {
	db = new Db();
	db.getStatement().executeUpdate(sb.toString());
} catch (Exception ex) {
	throw new DbException(ex.getMessage() + ": " + sb.toString());
} finally {
	if (db != null)
		db.close();
}
}


public void updateStatusPembatalan(Hashtable data) throws Exception {

    Connection conn = null;

    Db db = null;
    String sql = "";
    String sql5="";
    String sql6="";
    
    String idpermohonan=(String)data.get("id_permohonan"); 
	String id_Fail=(String)data.get("id_Fail");
	String userId = (String)data.get("userId");
	String id_Suburusanstatus=(String)data.get("id_suburusanstatus");
	String id_Suburusanstatusfail=(String)data.get("id_suburusanstatusfail");

    try
    {
     
 		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);

	    if(id_Suburusanstatusfail!="")
	    {
		Statement stmtX = db.getStatement();
		SQLRenderer r6 = new SQLRenderer();
		r6.update("id_Suburusanstatusfail",id_Suburusanstatusfail);
		r6.add("AKTIF",0);		
		r6.add("ID_KEMASKINI",userId);
		r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
		sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
		stmtX.executeUpdate(sql6);
	    }
	    
		Statement stmtF = db.getStatement();
		SQLRenderer r5 = new SQLRenderer();
		r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
		r5.add("ID_PERMOHONAN",idpermohonan);
		r5.add("ID_FAIL",id_Fail);
		r5.add("ID_SUBURUSANSTATUS",1506);
		r5.add("AKTIF",1);
		r5.add("ID_MASUK",userId);
		r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
		r5.add("ID_KEMASKINI",userId);
		r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
		sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
		stmtF.executeUpdate(sql5);


		conn.commit();
    }
    catch (SQLException se) { 
    	try 
    	{conn.rollback();
    	} 
    	catch (SQLException se2) 
    	{
    	throw new Exception("Rollback error:"+se2.getMessage());
    	}
    	se.printStackTrace();
    	throw new Exception("Ralat Jana Fail:"+se.getMessage());
    	}finally {
    	if (conn != null) conn.close(); 
    	if (db != null)	db.close();
    	}
  }


public void pilih_pembatalan(String id_kat,String id_hakmilik, String lot_tarik,
		String lot_ambil, String luas_lot, String id_Masuk,
		String id_pembatalan) throws Exception {
	Db db = null;
	String sql = "";
	String sql1 = "";
	try {
		double l_ambil = Double.parseDouble(lot_ambil);
		double l_tarik = Double.parseDouble(lot_tarik);
		double jumlah_luas = l_ambil - l_tarik;
	    String flag_warta = "";
	    
		if(id_kat.equals("2"))
		{
			if((l_tarik > 0.25) || (l_tarik/l_ambil > 0.01))
			{
				flag_warta = "Y";
			}				
			
		}
		else
		{
			if((l_tarik > 2500) || (l_tarik/l_ambil > 0.01))
			{
				flag_warta = "Y";
			}	
		}

		db = new Db();

		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		r.add("ID_PEMBATALAN", id_pembatalan);
		r.add("ID_HAKMILIK", id_hakmilik);
		r.add("LUAS_LOT_TARIK", lot_tarik);
		r.add("LUAS_AMBIL", l_ambil);
		r.add("LUAS_TINGGAL", "");
		r.add("ID_MASUK", id_Masuk);
		//r.add("FLAG_WARTA", flag_warta);
		r.add("TARIKH_MASUK", r.unquote("sysdate"));
		r.add("ID_KEMASKINI", id_Masuk);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql = r.getSQLInsert("tblpptpembatalanhakmilik");
		myLogger.info("SQL INSET LOT PENARIKANHAKMILIK :"
				+ sql.toUpperCase());
		stmt.executeUpdate(sql);

		Statement stmt1 = db.getStatement();
		SQLRenderer r1 = new SQLRenderer();
		r1.update("ID_HAKMILIK", id_hakmilik);
		r1.add("LUAS_AMBIL", jumlah_luas);
		r1.add("ID_KEMASKINI", id_Masuk);
		r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
		sql1 = r1.getSQLUpdate("tblppthakmilik");
		myLogger.info("SQL UPDATE LOT PENARIKANHAKMILIK :"
				+ sql1.toUpperCase());
		stmt1.executeUpdate(sql1);

	} finally {
		if (db != null)
			db.close();
	}

}



public void batalkan_pembatalan(String id_hakmilik, String lot_tarik,
		String id_Masuk, String id_pembatalan) throws Exception {
	Db db = null;
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		r.update("ID_HAKMILIK", id_hakmilik);
		r.add("FLAG_PEMBATALAN", "Y");
		r.add("LUAS_LOT_TARIK", lot_tarik);
		r.add("ID_PEMBATALAN", id_pembatalan);
		r.add("ID_KEMASKINI", id_Masuk);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql = r.getSQLUpdate("tblppthakmilik");

		myLogger.info("SQL UPDATE LOT BATALKAN :" + sql.toUpperCase());
		stmt.executeUpdate(sql);

	} finally {
		if (db != null)
			db.close();
	}

}

Vector senarai_pihak_penting = null;

public Vector senarai_pihak_penting(String id_permohonan) throws Exception {
	senarai_pihak_penting = new Vector();
	Db db = null;
	senarai_pihak_penting.clear();
	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();
		sql = "SELECT PB1.NAMA_PB,PB1.ID_JENISPB,H1.ID_HAKMILIK "
				+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB1,TBLPPTHAKMILIKPB B1,TBLPPTHAKMILIK H1 "
				+ " WHERE B1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "
				+ " AND B1.ID_HAKMILIK = H1.ID_HAKMILIK "
				+ " AND NVL(B1.ID_JENISPB,0) NOT IN ('40','41','42') "
				+

				" AND H1.ID_PERMOHONAN = '" + id_permohonan + "' "
				+ " ORDER BY NAMA_PB ASC ";

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		int bil = 0;
		while (rs.next()) {
			bil = bil + 1;
			h = new Hashtable();
			h.put("BIL", bil);
			h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
					: rs.getString("ID_HAKMILIK"));
			h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
					.getString("NAMA_PB").toUpperCase());
			senarai_pihak_penting.addElement(h);
		}
		return senarai_pihak_penting;
	} finally {
		if (db != null)
			db.close();
	}
}


Vector listDokumen_pembatalan = null;

public Vector senarai_dokumen_pembatalan(String id_pembatalan)
		throws Exception {
	listDokumen_pembatalan = new Vector();
	Db db = null;
	listDokumen_pembatalan.clear();
	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();
		sql = "SELECT A.ID_PEMBATALAN,A.ID_DOKUMEN, A.NAMA_FAIL, A.JENIS_MIME, A.TAJUK, A.KETERANGAN,"
				+ " A.CONTENT  FROM TBLPPTDOKUMEN A,TBLPPTPEMBATALAN P WHERE A.ID_PENARIKANBALIK = '"
				+ id_pembatalan
				+ "'  "
				+ " AND A.ID_PEMBATALAN = P.ID_PEMBATALAN";
		myLogger.info("SQL DOKUMEN :" + sql.toUpperCase());
		ResultSet rs = stmt.executeQuery(sql);

		Hashtable h;
		int bil = 0;

		while (rs.next()) {

			bil = bil + 1;
			h = new Hashtable();
			h.put("BIL", bil);
			h.put("ID_PEMBATALAN",
					rs.getString("ID_PEMBATALAN") == null ? "" : rs
							.getString("ID_PEMBATALAN"));
			h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? ""
					: rs.getString("ID_DOKUMEN"));
			h.put("NAMA_FAIL", rs.getString("NAMA_FAIL") == null ? "" : rs
					.getString("NAMA_FAIL"));
			h.put("JENIS_MIME", rs.getString("JENIS_MIME") == null ? ""
					: rs.getString("JENIS_MIME"));
			h.put("TAJUK", rs.getString("TAJUK") == null ? "" : rs
					.getString("TAJUK"));
			h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
					: rs.getString("KETERANGAN"));

			listDokumen_pembatalan.addElement(h);

		}

		return listDokumen_pembatalan;

	} finally {
		if (db != null)
			db.close();
	}

}


Vector senarai_hakmilik_overall = null;

public Vector senarai_hakmilik_overall(String id_permohonan)
		throws Exception {
	senarai_hakmilik_overall = new Vector();
	Db db = null;
	senarai_hakmilik_overall.clear();
	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();
		sql = "SELECT DISTINCT  H.LUAS_LOT_TARIK,H.ID_HAKMILIK,H.ID_PEMBATALAN,H.NO_PT,H.NO_LOT,JH.KETERANGAN AS JENIS_HAKMILIK,JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,"
				+ "M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.LUAS_AMBIL,H.FLAG_PEMBATALAN "
				+ "FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M "
				+ "WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
				+ "AND H.ID_MUKIM = M.ID_MUKIM "
			//	+ " AND NVL(H.LUAS_AMBIL,'0') > 0 "
				+ "AND H.ID_PERMOHONAN = '" + id_permohonan + "' ";

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		int bil = 0;
		while (rs.next()) {
			bil = bil + 1;
			h = new Hashtable();
			h.put("BIL", bil);
			h.put("BIL_DUM", bil + "abc");
			h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
					: rs.getString("ID_HAKMILIK"));
			h.put("ID_PEMBATALAN",
					rs.getString("ID_PEMBATALAN") == null ? "" : rs
							.getString("ID_PEMBATALAN"));
			// h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB") == null ?
			// "" : rs.getString("ID_HAKMILIKPB"));
			h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
					.getString("NO_LOT").toUpperCase());
			h.put("JENIS_HAKMILIK",
					rs.getString("JENIS_HAKMILIK") == null ? "" : rs
							.getString("JENIS_HAKMILIK"));
			h.put("KOD_JENIS_HAKMILIK",
					rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
							.getString("KOD_JENIS_HAKMILIK").toUpperCase());
			h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
					: rs.getString("NO_HAKMILIK").toUpperCase());
			// h.put("NAMA_PB",rs.getString("NAMA_PB") == null ? "" :
			// rs.getString("NAMA_PB").toUpperCase());
			h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
					: rs.getString("NAMA_MUKIM").toUpperCase());

			h.put("ID_KATEGORITANAH",
					rs.getString("ID_KATEGORITANAH") == null ? "" : rs
							.getString("ID_KATEGORITANAH").toUpperCase());
			if (!rs.getString("ID_KATEGORITANAH").equals("")
					&& !rs.getString("ID_KATEGORITANAH").equals("0")) {
				if (!rs.getString("ID_KATEGORITANAH").equals("2")) {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT") + " MP");
					h.put("LUAS_AMBIL",
							rs.getString("LUAS_AMBIL") == null ? "" : rs
									.getDouble("LUAS_AMBIL")
									+ " MP");
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? ""
									: rs.getDouble("LUAS_LOT_TARIK")
											+ " MP");
				}
				if (rs.getString("ID_KATEGORITANAH").equals("2")) {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT") + " HEKTAR");
					h.put("LUAS_AMBIL",
							rs.getString("LUAS_AMBIL") == null ? "" : rs
									.getDouble("LUAS_AMBIL")
									+ " HEKTAR");
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? ""
									: rs.getDouble("LUAS_LOT_TARIK")
											+ " HEKTAR");

				}

			} else {
				h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
						: rs.getDouble("LUAS_LOT"));
				h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
						: rs.getDouble("LUAS_AMBIL"));
				h.put("LUAS_LOT_TARIK",
						rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
								.getDouble("LUAS_LOT_TARIK"));
			}

			h.put("LUAS_LOT_TARIK_EDIT",
					rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
							.getDouble("LUAS_LOT_TARIK"));
			h.put("LUAS_AMBIL_EDIT",
					rs.getString("LUAS_AMBIL") == null ? "0" : rs
							.getDouble("LUAS_AMBIL"));
			h.put("FLAG_PEMBATALAN",
					rs.getString("FLAG_PEMBATALAN") == null ? "" : rs
							.getString("FLAG_PEMBATALAN"));
			senarai_hakmilik_overall.addElement(h);
		}
		return senarai_hakmilik_overall;
	} finally {
		if (db != null)
			db.close();
	}
}


public void update_pilih_pembatalan(String id_kat,String id_penarikanhakmilik,
		String id_hakmilik, String lot_tarik, String lot_ambil,
		String lot_ambil_asal, String luas_lot, String id_Masuk,
		String id_pembatalan) throws Exception {
	Db db = null;
	String sql = "";
	String sql1 = "";
	String sql2 = "";

	if (lot_tarik.equals("")) {
		lot_tarik = "0.0";
	}
	if (lot_ambil.equals("")) {
		lot_ambil = "0.0";
	}
	if (lot_ambil_asal.equals("")) {
		lot_ambil_asal = "0.0";
	}
	
	

	try {

		double jl = 0.0;
		db = new Db();

		Statement stmt2 = db.getStatement();
		sql2 = "SELECT NVL(LUAS_LOT_TARIK,'0') AS LUAS_LOT_TARIK FROM TBLPPTPEMBATALANHAKMILIK WHERE ID_PEMBATALANHAKMILIK = '"
				+ id_penarikanhakmilik + "'";
		ResultSet rs = stmt2.executeQuery(sql2);
		while (rs.next()) {
			jl = rs.getString("LUAS_LOT_TARIK") == null ? 0.0 : rs
					.getDouble("LUAS_LOT_TARIK");
		}

		double l_ambil = Double.parseDouble(lot_ambil);
		double lot_tarik1 = Double.parseDouble(lot_tarik);
		double l_ambil_asal = Double.parseDouble(lot_ambil_asal);
		double l_tarik = Double.parseDouble(lot_tarik) - jl;
		double jumlah_luas = l_ambil - l_tarik;
		String flag_warta = "";
		
		if(id_kat.equals("2"))
		{
			if((lot_tarik1 > 0.25) || (lot_tarik1/l_ambil_asal > 0.01))
			{
				flag_warta = "Y";
			}				
			
		}
		else
		{
			if((lot_tarik1 > 2500) || (lot_tarik1/l_ambil_asal > 0.01))
			{
				flag_warta = "Y";
			}	
		}

		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		r.update("ID_PEMBATALANHAKMILIK", id_penarikanhakmilik);
		r.add("LUAS_LOT_TARIK", lot_tarik);
		r.add("LUAS_AMBIL", lot_ambil_asal);
		//r.add("FLAG_WARTA", flag_warta);
		r.add("ID_KEMASKINI", id_Masuk);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql = r.getSQLUpdate("tblpptpembatalanhakmilik");
		myLogger.info("**UPDATE SQL INSERT LOT PENARIKANHAKMILIK :"
				+ sql.toUpperCase());
		stmt.executeUpdate(sql);

		Statement stmt1 = db.getStatement();
		SQLRenderer r1 = new SQLRenderer();
		r1.update("ID_HAKMILIK", id_hakmilik);
		r1.add("LUAS_AMBIL", jumlah_luas);
		r1.add("ID_KEMASKINI", id_Masuk);
		r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
		sql1 = r1.getSQLUpdate("tblppthakmilik");
		myLogger.info("**UPDATE SQL UPDATE LOT PENARIKANHAKMILIK :"
				+ sql1.toUpperCase());
		stmt1.executeUpdate(sql1);

	} finally {
		if (db != null)
			db.close();
	}

}


Vector list_jenisluas = null;

public Vector list_jenisluas() throws Exception {
	list_jenisluas = new Vector();
	list_jenisluas.clear();
	Db db = null;
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		sql="SELECT ID_LUAS,KETERANGAN,KOD_LUAS FROM TBLRUJLUAS ORDER BY KOD_LUAS ASC ";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			Hashtable h = new Hashtable();
			h.put("ID_LUAS", rs.getInt("ID_LUAS"));
			if (rs.getString("KETERANGAN") == null) {
				h.put("KETERANGAN", "");
			} else {
				h.put("KETERANGAN", rs.getString("KETERANGAN"));
			}
			if (rs.getString("KOD_LUAS") == null) {
				h.put("KOD_LUAS", "");
			} else {
				h.put("KOD_LUAS", rs.getString("KOD_LUAS"));
			}

			list_jenisluas.addElement(h);
		}
		return list_jenisluas;
	} finally {
		if (db != null)
			db.close();
	}
}

Vector view_details_dokumen = null;

public Vector view_details_dokumen(String id_dokumen) throws Exception {
	view_details_dokumen = new Vector();
	Db db = null;
	view_details_dokumen.clear();
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		sql = "SELECT ID_DOKUMEN,TAJUK,KETERANGAN,NAMA_FAIL,CONTENT,JENIS_MIME,NO_RUJUKAN,ID_PEMBATALAN FROM TBLPPTDOKUMEN "
				+ " WHERE ID_DOKUMEN = '" + id_dokumen + "'";
		ResultSet rs = stmt.executeQuery(sql);

		Hashtable h;
		int bil = 0;

		while (rs.next()) {
			bil = bil + 1;
			h = new Hashtable();
			h.put("BIL", bil);
			h.put("ID_PEMBATALAN",
					rs.getString("ID_PEMBATALAN") == null ? "" : rs
							.getString("ID_PEMBATALAN"));
			h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? ""
					: rs.getString("ID_DOKUMEN"));
			h.put("NAMA_FAIL", rs.getString("NAMA_FAIL") == null ? "" : rs
					.getString("NAMA_FAIL"));
			h.put("JENIS_MIME", rs.getString("JENIS_MIME") == null ? ""
					: rs.getString("JENIS_MIME"));
			h.put("TAJUK", rs.getString("TAJUK") == null ? "" : rs
					.getString("TAJUK"));
			h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
					: rs.getString("KETERANGAN"));

			view_details_dokumen.addElement(h);

		}
		return view_details_dokumen;
	} finally {
		if (db != null)
			db.close();
	}

}


public void updateFiles(Hashtable data) throws Exception {
	Db db = null;
	String sql = "";
	String id_dokumen = (String) data.get("id_dokumen");
	String txtnamadokumen = (String) data.get("txtnamadokumen");
	String txtketerangandokumen = (String) data.get("txtketerangandokumen");
	String id_Masuk = (String) data.get("id_Masuk");

	try {
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		r.update("ID_DOKUMEN", id_dokumen);
		r.add("TAJUK", txtnamadokumen);
		r.add("KETERANGAN", txtketerangandokumen);
		r.add("ID_KEMASKINI", id_Masuk);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

		sql = r.getSQLUpdate("tblpptdokumen");

		myLogger.info("SQL UPDATE DOKUMEN :" + sql.toUpperCase());
		stmt.executeUpdate(sql);

	} finally {
		if (db != null)
			db.close();
	}

}


Vector maklumat_tambahan_pembatalan = null;

public Vector maklumat_tambahan_pembatalan(String id_permohonan) throws Exception {
	maklumat_tambahan_pembatalan = new Vector();
	Db db = null;
	maklumat_tambahan_pembatalan.clear();
	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();
		sql = "SELECT P.ID_PERMOHONAN,F.NO_FAIL,B.NO_PEMBATALAN,K.NAMA_KEMENTERIAN,P.NO_RUJUKAN_SURAT "
				+ "FROM TBLPPTPERMOHONAN P,TBLPPTPEMBATALAN B,TBLPFDFAIL F,TBLRUJKEMENTERIAN K "
				+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
				+ "AND P.ID_FAIL = F.ID_FAIL "
				+ "AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+)"
				+ "AND P.ID_PERMOHONAN = '" + id_permohonan + "'";
		myLogger.info("SQL MALUMAT TAMBAHAN :" + sql.toUpperCase());

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;

		while (rs.next()) {
			h = new Hashtable();
			h.put("ID_PERMOHONAN",
					rs.getString("ID_PERMOHONAN") == null ? "" : rs
							.getString("ID_PERMOHONAN"));
			h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "-" : rs
					.getString("NO_FAIL"));
			h.put("NAMA_KEMENTERIAN",
					rs.getString("NAMA_KEMENTERIAN") == null ? "-" : rs
							.getString("NAMA_KEMENTERIAN").toUpperCase());
			h.put("NO_RUJUKAN_SURAT",
					rs.getString("NO_RUJUKAN_SURAT") == null ? "-" : rs
							.getString("NO_RUJUKAN_SURAT").toUpperCase());
			h.put("NO_PENARIKANBALIK",
					rs.getString("NO_PEMBATALAN") == null ? "-" : rs
							.getString("NO_PEMBATALAN").toUpperCase());
			h.put("TARIKH", cd);

			maklumat_tambahan_pembatalan.addElement(h);
		}
		return maklumat_tambahan_pembatalan;
	} finally {
		if (db != null)
			db.close();
	}
}

Vector list_kementerian = null;

public Vector list_kementerian() throws Exception {
	list_kementerian = new Vector();
	Vector key = list_kementerian;
	Element cachedObject = myCache.get(key);
	myLogger.info("CACHED KEMENTERIAN :" + myCache.get(key));
	if (cachedObject != null) {
		myLogger.info("CACHED L1");
		return (Vector) cachedObject.getObjectValue();
	} else {
		myLogger.info("CACHED L2");
		Db db = null;
		list_kementerian.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KEMENTERIAN, KOD_KEMENTERIAN, NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN ORDER BY KOD_KEMENTERIAN ASC ";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_KEMENTERIAN",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h
						.put("KOD_KEMENTERIAN", rs
								.getString("KOD_KEMENTERIAN") == null ? ""
								: rs.getString("KOD_KEMENTERIAN")
										.toUpperCase());
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN")
								.toUpperCase());
				list_kementerian.addElement(h);
			}

			myCache.put(new Element(key, list_kementerian));
			return list_kementerian;
		} finally {
			if (db != null)
				db.close();
		}
	}
}

Vector list_urusan = null;

public Vector list_urusan() throws Exception {
	list_urusan = new Vector();
	Db db = null;
	list_urusan.clear();
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();

		sql = "SELECT ID_SUBURUSAN,KOD_SUBURUSAN,NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SEKSYEN = 1";
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		while (rs.next()) {
			h = new Hashtable();
			h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
					: rs.getString("ID_SUBURUSAN"));
			h.put("KOD_SUBURUSAN",
					rs.getString("KOD_SUBURUSAN") == null ? "" : rs
							.getString("KOD_SUBURUSAN").toUpperCase());
			h.put("NAMA_SUBURUSAN",
					rs.getString("NAMA_SUBURUSAN") == null ? "" : rs
							.getString("NAMA_SUBURUSAN").toUpperCase());
			list_urusan.addElement(h);
		}
		return list_urusan;
	} finally {
		if (db != null)
			db.close();
	}
}



Vector list_pejabat = null;

public Vector list_pejabat() throws Exception {
	list_pejabat = new Vector();
	Db db = null;
	list_pejabat.clear();
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();

		sql = "SELECT ID_PEJABATJKPTG,NAMA_PEJABAT,"
        +"CASE "
        +" WHEN ID_JENISPEJABAT = '22' THEN 'UPT' "
      //  +" WHEN ID_JENISPEJABAT = '21' THEN 'SPT ' "      
        +" END AS JENIS_PEJABAT "
        +" FROM TBLRUJPEJABATJKPTG WHERE ID_JENISPEJABAT IN (22) "
        +" AND ID_SEKSYEN = 1";
		
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		while (rs.next()) {
			h = new Hashtable();
			h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG") == null ? ""
					: rs.getString("ID_PEJABATJKPTG"));
			h.put("NAMA_PEJABAT",
					rs.getString("NAMA_PEJABAT") == null ? "" : rs
							.getString("NAMA_PEJABAT").toUpperCase());
			h.put("JENIS_PEJABAT",
					rs.getString("JENIS_PEJABAT") == null ? "" : rs
							.getString("JENIS_PEJABAT").toUpperCase());
			list_pejabat.addElement(h);
		}
		return list_pejabat;
	} finally {
		if (db != null)
			db.close();
	}
}



Vector tarikh_akhir_tampal = null;
public Vector tarikh_akhir_tampal(String id_penarikan) throws Exception {
tarikh_akhir_tampal = new Vector();
tarikh_akhir_tampal.clear();
Db db = null;        			
String sql = "";
try {
	db = new Db();
	Statement stmt = db.getStatement();
	sql = "SELECT MAX(TARIKH_TAMPAL) AS TARIKH_TAMPAL FROM TBLPPTNOTISAWAM A, TBLPPTPENARIKANBALIK P WHERE P.ID_PENARIKANBALIK = '"+id_penarikan+"' "+
		  " AND A.ID_PERMOHONAN = P.ID_PERMOHONAN";
	myLogger.info("SQL TARIKH TAMPAL :"+sql.toUpperCase());
	ResultSet rs = stmt.executeQuery(sql);    	
	
	Hashtable h;
	while (rs.next()) {
		h = new Hashtable();
		h.put("TARIKH_TAMPAL", rs.getString("TARIKH_TAMPAL") == null ? "" : Format.format(rs.getDate("TARIKH_TAMPAL")));			
		tarikh_akhir_tampal.addElement(h);
	}
	return tarikh_akhir_tampal;
	//myLogger.info("TARIKH TAMPAL :"+tarikh_akhir_tampal);
	
}
finally {
	if (db != null)
		db.close();
}
}

Vector list_status = null;

public Vector list_status() throws Exception {
	list_status = new Vector();
	Db db = null;
	list_status.clear();
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();

		sql = "SELECT ID_STATUS,KOD_STATUS,KETERANGAN FROM TBLRUJSTATUS WHERE ID_SEKSYEN = 1 ORDER BY KOD_STATUS ASC";
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		while (rs.next()) {
			h = new Hashtable();
			h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
					.getString("ID_STATUS"));
			h.put("KOD_STATUS", rs.getString("KOD_STATUS") == null ? ""
					: rs.getString("KOD_STATUS").toUpperCase());
			h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
					: rs.getString("KETERANGAN").toUpperCase());
			list_status.addElement(h);
		}
		return list_status;
	} finally {
		if (db != null)
			db.close();
	}
}

Vector senarai_pembatalan_carian = null;

public Vector senarai_pembatalan_carian(String no_fail,
		String no_jkptg_negeri, String id_kementerian, String id_urusan,
		String id_status, String jenis_permohon,String role,String user_negeri) throws Exception {
	senarai_pembatalan_carian = new Vector();
	Db db = null;
	senarai_pembatalan_carian.clear();
	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();

		sql = "SELECT P.ID_PERMOHONAN,P.TARIKH_PERMOHONAN,"
				+ " F.NO_FAIL, P.NO_RUJUKAN_UPT,"
				+ " K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN,U.NAMA_SUBURUSAN  FROM TBLPPTPERMOHONAN P, "
				+ " TBLRUJSUBURUSAN U,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,"
				+ " TBLRUJSTATUS S "
				+ " WHERE  P.ID_FAIL = F.ID_FAIL(+) "
				+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
				+ " AND P.ID_DAERAH = D.ID_DAERAH(+) "
				+ " AND F.ID_SUBURUSAN = '52' "
				+ " AND P.FLAG_JENISPERMOHONAN = '"
				+ jenis_permohon
				+ "' "
				+ " AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "
				+ " AND NVL(S.ID_STATUS,0) IN ('31','35','38','43','46','48','52','54','58','59',"
					+ " '62','66','68','72','74','76','181')"
				//pembatalan
			/*	+ " AND NVL(S.ID_STATUS,0) IN ('5','11','16','20','22','82','113','127','128','132',"
				+ " '133','134','148','147','149','181','182')" */ // pembatalan
				+ " AND P.ID_STATUS = S.ID_STATUS " +
						"";
		    if(!role.equals("(PPT)Pengarah"))
		    {
			sql += "AND  F.ID_NEGERI = '"+user_negeri+"' ";
		    }
		    

		// kena filter by status (sudah diwartakan)

		if (no_fail != "") {
			if (!no_fail.trim().equals("")) {
				sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%"
						+ no_fail.toUpperCase().trim() + "%'";
			}
		}
		if (no_jkptg_negeri != "") {
			if (!no_jkptg_negeri.trim().equals("")) {
				sql = sql + " AND UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
						+ no_jkptg_negeri.toUpperCase().trim() + "%'";
			}
		}
		if (id_urusan != "") {
			if (!id_urusan.trim().equals("")) {
				sql = sql + " AND UPPER(F.ID_SUBURUSAN) LIKE '"
						+ id_urusan.toUpperCase() + "'";
			}
		}
		if (id_kementerian != "") {
			if (!id_kementerian.trim().equals("")) {
				sql = sql + " AND UPPER(F.ID_KEMENTERIAN) LIKE '"
						+ id_kementerian.toUpperCase() + "'";
			}
		}
		if (id_status != "") {
			if (!id_status.trim().equals("")) {
				sql = sql + " AND UPPER(P.ID_STATUS) LIKE '"
						+ id_status.toUpperCase() + "'";
			}
		}

		sql += " ORDER BY P.TARIKH_KEMASKINI DESC";

		myLogger.info("SQL PENARIKAN CARI :" + sql.toUpperCase());

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		int bil = 0;
		while (rs.next()) {
			bil = bil + 1;
			h = new Hashtable();
			h.put("BIL", bil);
			h.put("ID_PERMOHONAN",
					rs.getString("ID_PERMOHONAN") == null ? "" : rs
							.getString("ID_PERMOHONAN"));
			// h.put("ID_PEMBATALAN",rs.getString("ID_PENARIKANBALIK") ==
			// null ? "" : rs.getString("ID_PENARIKANBALIK"));
			h.put("TARIKH_PERMOHONAN",
					rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
							.format(rs.getDate("TARIKH_PERMOHONAN")));
			// h.put("NO_PEMBATALAN",rs.getString("NO_PENARIKANBALIK") ==
			// null ? "" : rs.getString("NO_PENARIKANBALIK").toUpperCase());
			h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "default"
					: rs.getString("NO_FAIL").toUpperCase());
			// h.put("TARIKH_PEMBATALAN",
			// rs.getString("TARIKH_PENARIKAN_BALIK")==null?"":Format.format(rs.getDate("TARIKH_PENARIKAN_BALIK")));
			h.put("NO_RUJUKAN_UPT",
					rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
							.getString("NO_RUJUKAN_UPT").toUpperCase());
			h.put("NAMA_KEMENTERIAN",
					rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
							.getString("NAMA_KEMENTERIAN").toUpperCase());
			h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
					: rs.getString("NAMA_DAERAH").toUpperCase());
			h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
					: rs.getString("KETERANGAN").toUpperCase());
			h.put("URUSAN", rs.getString("NAMA_SUBURUSAN") == null ? ""
					: rs.getString("NAMA_SUBURUSAN").toUpperCase());
			senarai_pembatalan_carian.addElement(h);
		}
		return senarai_pembatalan_carian;
	} finally {
		if (db != null)
			db.close();
	}
}

Vector nama_user = null;
public Vector nama_user(String id_user) throws Exception {
	nama_user = new Vector();
	nama_user.clear();
	Db db = null;
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		sql = " SELECT INITCAP(USER_NAME) AS USER_NAME FROM USERS WHERE USER_ID ='"+id_user+"'";
		myLogger.info("SQL NAMA USER"+sql.toUpperCase());
        
		ResultSet rs = stmt.executeQuery(sql);

        	
		while (rs.next()) {
			Hashtable h = new Hashtable();	
			
			if (rs.getString("USER_NAME") == null) {
				h.put("USER_NAME", "");
			} else {
				h.put("USER_NAME", rs.getString("USER_NAME"));
			}
			
			if (rs.getString("USER_NAME") == null) {
				h.put("USER_NAME_CAP", "");
			} else {
				h.put("USER_NAME_CAP", rs.getString("USER_NAME").toUpperCase());
			}
			
			
			nama_user.addElement(h);
					
		}
		
		return nama_user;
	} finally {
		if (db != null)
			db.close();
	}
}



}
