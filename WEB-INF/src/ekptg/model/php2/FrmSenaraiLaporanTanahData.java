package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.php2.online.FrmPYWOnlineSenaraiFailData;

public class FrmSenaraiLaporanTanahData {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Logger log = Logger.getLogger(FrmSenaraiLaporanTanahData.class);

	public Vector getSenaraiLaporan(String tarikhLawatan, String pelapor,
			String idNegeri, String idDaerah, String idMukim,
			String peganganHakmilik, String idJenisHakmilik, String noHakmilik,
			String noWarta, String idLot, String noLot) throws Exception {

		Db db = null;
		Vector senaraiLaporan = new Vector();
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_LAPORANTANAH, A.TARIKH_LAWATAN, A.NAMA_PELAPOR,"

					+ " C.PEGANGAN_HAKMILIK, UPPER(D.KETERANGAN) AS JENIS_LOT, C.NO_LOT, B.LUAS_BERSAMAAN, I.KOD_LUAS, E.KOD_JENIS_HAKMILIK, C.NO_HAKMILIK,"
					+ " C.NO_WARTA, C.TARIKH_WARTA, F.NAMA_MUKIM, G.NAMA_DAERAH, H.NAMA_NEGERI, J.KETERANGAN AS KATEGORI, K.KETERANGAN AS SUBKATEGORI,"
					+ " C.SYARAT, C.SEKATAN, C.KEGUNAAN_TANAH, M.NAMA_AGENSI, L.NAMA_KEMENTERIAN"

					+ " FROM TBLPHPRUJLAPORANTANAH A, TBLHTPHAKMILIKAGENSI B, TBLHTPHAKMILIK C, TBLRUJLOT D, TBLRUJJENISHAKMILIK E,"
					+ " TBLRUJMUKIM F, TBLRUJDAERAH G, TBLRUJNEGERI H, TBLRUJLUAS I, TBLRUJKATEGORI J, TBLRUJSUBKATEGORI K,"
					+ " TBLRUJKEMENTERIAN L, TBLRUJAGENSI M"
					+ " WHERE A.ID_HAKMILIKAGENSI = B.ID_HAKMILIKAGENSI AND B.ID_HAKMILIK = C.ID_HAKMILIK"
					+ " AND C.ID_LOT = D.ID_LOT AND C.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK"
					+ " AND C.ID_MUKIM = F.ID_MUKIM AND C.ID_DAERAH = G.ID_DAERAH AND C.ID_NEGERI = H.ID_NEGERI"
					+ " AND B.ID_LUAS_BERSAMAAN = I.ID_LUAS AND C.ID_KATEGORI = J.ID_KATEGORI AND C.ID_SUBKATEGORI = K.ID_SUBKATEGORI"
					+ " AND B.ID_AGENSI = M.ID_AGENSI AND C.ID_KEMENTERIAN = L.ID_KEMENTERIAN";

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhLawatan
			if (tarikhLawatan != null) {
				if (!tarikhLawatan.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(A.TARIKH_LAWATAN,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhLawatan))
									.toUpperCase() + "'";
				}
			}

			// pelapor
			if (pelapor != null) {
				if (!pelapor.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA_PELAPOR) LIKE '%' ||'"
							+ pelapor.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")
						&& !idNegeri.trim().equals("99999")) {
					sql = sql + " AND C.ID_NEGERI = '" + idNegeri.trim() + "'";
				}
			}

			// idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")
						&& !idDaerah.trim().equals("99999")) {
					sql = sql + " AND C.ID_DAERAH = '" + idDaerah.trim() + "'";
				}
			}

			// idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")
						&& !idMukim.trim().equals("99999")) {
					sql = sql + " AND C.ID_MUKIM = '" + idMukim.trim() + "'";
				}
			}

			// idJenisHakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")
						&& !idJenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND C.ID_JENISHAKMILIK = '"
							+ idJenisHakmilik.trim() + "'";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idLot
			if (idLot != null) {
				if (!idLot.trim().equals("") && !idLot.trim().equals("99999")) {
					sql = sql + " AND C.ID_LOT = '" + idLot.trim() + "'";
				}
			}

			// noLot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_LOT) LIKE '%' ||'"
							+ noLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			// peganganHakmilik
			if (peganganHakmilik != null) {
				if (!peganganHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(C.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ peganganHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			sql = sql + " ORDER BY A.TARIKH_LAWATAN DESC NULLS LAST ";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idLaporan", rs.getString("ID_LAPORANTANAH") == null ? ""
						: rs.getString("ID_LAPORANTANAH"));
				h.put("tarikhLawatan",
						rs.getDate("TARIKH_LAWATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LAWATAN")));
				h.put("pelapor", rs.getString("NAMA_PELAPOR") == null ? "" : rs
						.getString("NAMA_PELAPOR").toUpperCase());
				h.put("hakmilik",
						rs.getString("JENIS_LOT") + " "
								+ rs.getString("NO_LOT") + ", "
								+ rs.getString("KOD_JENIS_HAKMILIK") + " "
								+ rs.getString("NO_HAKMILIK") + ", "
								+ rs.getString("NAMA_MUKIM") + ", "
								+ rs.getString("NAMA_DAERAH") + ", "
								+ rs.getString("NAMA_NEGERI"));

				senaraiLaporan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}

		return senaraiLaporan;
	}
	
	public Vector getSenaraiLaporanTapak(String noFail, String pelapor,
			String idNegeri, String idDaerah, String idMukim,
			String peganganHakmilik, String idJenisHakmilik, String noHakmilik,
			String noWarta, String idLot, String noLot) throws Exception {

		Db db = null;
		Vector senaraiLaporan = new Vector();
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_LAPORANTANAH, B.ID_PERMOHONAN, C.NO_FAIL, UPPER(F.KETERANGAN) AS JENIS_LOT, E.NO_LOT, M.ID_SUBURUSAN, L.NAMA_URUSAN, "
				+ "G.KOD_JENIS_HAKMILIK, E.NO_HAKMILIK, H.NAMA_MUKIM, I.NAMA_DAERAH, J.NAMA_NEGERI, A.NAMA_PELAPOR, K.ID_STATUS, M.NAMA_SUBURUSAN "
				+ "FROM TBLPHPLAPORANTANAH A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLPHPHAKMILIKPERMOHONAN D, TBLPHPHAKMILIK E, TBLRUJLOT F, "
				+ "TBLRUJJENISHAKMILIK G, TBLRUJMUKIM H, TBLRUJDAERAH I, TBLRUJNEGERI J, TBLRUJSTATUS K, TBLRUJURUSAN L, TBLRUJSUBURUSAN M "
				+ "WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN "
				+ "AND D.ID_HAKMILIKPERMOHONAN = E.ID_HAKMILIKPERMOHONAN AND E.ID_LOT = F.ID_LOT AND E.ID_JENISHAKMILIK = G.ID_JENISHAKMILIK "
				+ "AND E.ID_MUKIM = H.ID_MUKIM AND H.ID_DAERAH = I.ID_DAERAH AND I.ID_NEGERI = J.ID_NEGERI AND B.ID_STATUS = K.ID_STATUS "
				+ "AND K.ID_STATUS = '1610200' AND C.ID_URUSAN = L.ID_URUSAN AND C.ID_SUBURUSAN = M.ID_SUBURUSAN";

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhLawatan
			if (noFail != null) {
				if (!noFail.toString().trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// pelapor
			if (pelapor != null) {
				if (!pelapor.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA_PELAPOR) LIKE '%' ||'"
							+ pelapor.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")
						&& !idNegeri.trim().equals("99999")) {
					sql = sql + " AND J.ID_NEGERI = '" + idNegeri.trim() + "'";
				}
			}

			// idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")
						&& !idDaerah.trim().equals("99999")) {
					sql = sql + " AND I.ID_DAERAH = '" + idDaerah.trim() + "'";
				}
			}

			// idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")
						&& !idMukim.trim().equals("99999")) {
					sql = sql + " AND H.ID_MUKIM = '" + idMukim.trim() + "'";
				}
			}

			// idJenisHakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")
						&& !idJenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND E.ID_JENISHAKMILIK = '"
							+ idJenisHakmilik.trim() + "'";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(E.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(E.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idLot
			if (idLot != null) {
				if (!idLot.trim().equals("") && !idLot.trim().equals("99999")) {
					sql = sql + " AND E.ID_LOT = '" + idLot.trim() + "'";
				}
			}

			// noLot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql + " AND UPPER(E.NO_LOT) LIKE '%' ||'"
							+ noLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			// peganganHakmilik
			if (peganganHakmilik != null) {
				if (!peganganHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(E.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ peganganHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			sql = sql + " ORDER BY A.TARIKH_LAWATAN DESC NULLS LAST ";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idLaporan", rs.getString("ID_LAPORANTANAH") == null ? ""
						: rs.getString("ID_LAPORANTANAH"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("pelapor", rs.getString("NAMA_PELAPOR") == null ? "" : rs
						.getString("NAMA_PELAPOR").toUpperCase());
				h.put("hakmilik",
						rs.getString("JENIS_LOT") + " "
								+ rs.getString("NO_LOT") + ", "
								+ rs.getString("KOD_JENIS_HAKMILIK") + " "
								+ rs.getString("NO_HAKMILIK") + ", "
								+ rs.getString("NAMA_MUKIM") + ", "
								+ rs.getString("NAMA_DAERAH") + ", "
								+ rs.getString("NAMA_NEGERI"));
				
				String urusan = "";
				
				if("34".equals(rs.getString("ID_SUBURUSAN"))) {
					urusan = "PELEPASAN/PENYERAHAN BALIK";
				} else if("33".equals(rs.getString("ID_SUBURUSAN"))) {
					urusan = "TUKARGUNA";
				} else if("32".equals(rs.getString("ID_SUBURUSAN"))) {
					urusan = "PENAWARAN";
				} else {
					urusan = rs.getString("NAMA_URUSAN");
				}
				h.put("urusan", urusan);

				senaraiLaporan.addElement(h);
				bil++;
			} 

		} finally {
			if (db != null)
				db.close();
		}

		return senaraiLaporan;
	}

	public String getIdHakmilikAgensiByPeganganHakmilik(String peganganHakmilik)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
					+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '"
					+ peganganHakmilik.toUpperCase() + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getMaklumatTanah(String idHakmilik) throws Exception {
		Db db = null;
		Vector maklumatTanah = new Vector();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

//			sql = "SELECT L.ID_HAKMILIKAGENSI, A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK,"
//					+ " B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.NO_WARTA, A.KEGUNAAN_TANAH, L.LUAS_BERSAMAAN, L.ID_LUAS_BERSAMAAN, A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI,"
//					+ " G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KOD_LUAS AS KOD_LUAS, K.KETERANGAN AS JENIS_LUAS_BERSAMAAN, A.STATUS_RIZAB"
//					+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I,TBLRUJAGENSI J, TBLRUJLUAS K, TBLHTPHAKMILIKAGENSI L"
//					+ " WHERE A.ID_HAKMILIK = L.ID_HAKMILIK AND A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+)"
//					+ " AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND J.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND L.ID_AGENSI = J.ID_AGENSI(+) AND L.ID_LUAS_BERSAMAAN = K.ID_LUAS"
//					+ " AND L.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";
			
			sql = " SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, UPPER(B.KETERANGAN) AS JENIS_LOT, A.NO_LOT, C.KOD_JENIS_HAKMILIK, A.NO_HAKMILIK, NO_WARTA,"
				+ " A.PEGANGAN_HAKMILIK, A.TARIKH_WARTA, A.KEGUNAAN_TANAH, A.SYARAT, A.SEKATAN, D.KETERANGAN AS SUBKATEGORI, E.KETERANGAN AS KATEGORI, F.ID_LUAS,"
				+ " F.KETERANGAN AS UNIT_LUAS, A.LUAS, G.NAMA_MUKIM, H.NAMA_DAERAH, I.NAMA_NEGERI, UPPER(J.NAMA_AGENSI) AS NAMA_AGENSI, K.NAMA_KEMENTERIAN"
				+ " FROM TBLPHPHAKMILIK A, TBLRUJLOT B, TBLRUJJENISHAKMILIK C, TBLRUJSUBKATEGORI D, TBLRUJKATEGORI E, TBLRUJLUAS F, TBLRUJMUKIM G,"
				+ " TBLRUJDAERAH H, TBLRUJNEGERI I, TBLRUJAGENSI J, TBLRUJKEMENTERIAN K"
				+ " WHERE A.ID_LOT = B.ID_LOT AND A.ID_JENISHAKMILIK = C.ID_JENISHAKMILIK AND A.ID_SUBKATEGORI = D.ID_SUBKATEGORI(+)"
				+ " AND D.ID_KATEGORI = E.ID_KATEGORI(+) AND A.ID_LUAS = F.ID_LUAS AND A.ID_MUKIM = G.ID_MUKIM(+) AND G.ID_DAERAH = H.ID_DAERAH(+)"
				+ " AND H.ID_NEGERI = I.ID_NEGERI(+) AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) AND A.ID_HAKMILIK = '"+idHakmilik+"'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilik",
						rs.getString("ID_HAKMILIK") == null ? "" : rs
								.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null
						|| rs.getString("NO_HAKMILIK") == null ? "" : rs
						.getString("KOD_JENIS_HAKMILIK").toUpperCase()
						+ " "
						+ rs.getString("NO_HAKMILIK"));
				h.put("noLot",
						rs.getString("JENIS_LOT") == null
								|| rs.getString("NO_LOT") == null ? "" : rs
								.getString("JENIS_LOT")
								+ " "
								+ rs.getString("NO_LOT"));
				h.put("luasLot",
						rs.getString("LUAS") == null
								|| rs.getString("UNIT_LUAS") == null ? ""
								: Utils.formatLuas(rs
										.getDouble("LUAS"))
										+ " "
										+ rs.getString("UNIT_LUAS"));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());
				if (rs.getString("NO_HAKMILIK") != null
						&& rs.getString("NO_WARTA") == null) {
					h.put("statusRizab", "MILIK");
				} else if (rs.getString("NO_HAKMILIK") == null
						&& rs.getString("NO_WARTA") != null) {
					h.put("statusRizab", "RIZAB");
				} else {
					h.put("statusRizab", "");
				}
				maklumatTanah.addElement(h);
			} else {
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", "");
				h.put("daerah", "");
				h.put("negeri", "");
				h.put("kategoriTanah", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kementerian", "");
				h.put("agensi", "");
				h.put("kegunaanTanah", "");
				h.put("statusRizab", "");
				maklumatTanah.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return maklumatTanah;
	}

	public String daftarLaporan(String idHakmilikAgensi, String tarikhLawatan,
			String tujuanLaporan, String pelapor, String catatan,
			String jawatan, String idNegeri, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idLaporanString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPRUJLAPORANTANAH
			long idLaporan = DB.getNextID("TBLPHPRUJLAPORANTANAH_SEQ");
			idLaporanString = String.valueOf(idLaporan);
			r.add("ID_LAPORANTANAH", idLaporan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("TARIKH_LAWATAN",
					r.unquote("to_date('" + tarikhLawatan + "','dd/MM/yyyy')"));
			r.add("TUJUAN_LAPORAN", tujuanLaporan);
			r.add("CATATAN", catatan);
			r.add("NAMA_PELAPOR", pelapor);
			r.add("JAWATAN_PELAPOR", jawatan);
			r.add("ID_NEGERIPELAPOR", idNegeri);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPRUJLAPORANTANAH");
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
		return idLaporanString;
	}

	public Vector getMaklumatLaporan(String idLaporan) throws Exception {
		Db db = null;
		Vector maklumatLaporan = new Vector();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_LAPORANTANAH, ID_PERMOHONAN, ID_HAKMILIK, TARIKH_LAWATAN, FLAG_LAWATAN, TUJUAN_LAPORAN, LAPORAN_ATASTANAH, ULASAN,"
					+ " JALAN_HUBUNGAN, KAWASAN_BERHAMPIRAN, JARAK_DARIBANDAR, KEADAAN_RUPABUMI, KEADAAN_TANAH, FLAG_KEMUDAHANASAS_AIR,"
					+ " FLAG_KEMUDAHANASAS_ELEKTRIK, FLAG_KEMUDAHANASAS_TEL, KEMUDAHAN_ASAS, SEMP_UTARA, SEMP_SELATAN, "
					+ " SEMP_TIMUR, SEMP_BARAT, NAMA_JAWATAN_PELAPOR, NAMA_PELAPOR, ID_JAWATANPELAPOR, ID_NEGERIPELAPOR, CATATAN"
					+ " FROM TBLPHPLAPORANTANAH"
					+ " WHERE ID_LAPORANTANAH = '" + idLaporan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idLaporan", rs.getString("ID_LAPORANTANAH") == null ? ""
						: rs.getString("ID_LAPORANTANAH"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? ""
						: rs.getString("ID_PERMOHONAN"));
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIK") == null ? "" : rs
								.getString("ID_HAKMILIK"));
				h.put("flagLawatan", rs.getString("FLAG_LAWATAN") == null ? ""
						: rs.getString("FLAG_LAWATAN"));
				h.put("tarikhLawatan",
						rs.getString("TARIKH_LAWATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LAWATAN")));
				h.put("tujuanLaporan",
						rs.getString("TUJUAN_LAPORAN") == null ? "" : rs
								.getString("TUJUAN_LAPORAN"));
				h.put("laporanAtasTanah",
						rs.getString("LAPORAN_ATASTANAH") == null ? "" : rs
								.getString("LAPORAN_ATASTANAH"));
				h.put("ulasan",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("laporanAtasTanah",
						rs.getString("LAPORAN_ATASTANAH") == null ? "" : rs
								.getString("LAPORAN_ATASTANAH"));
				h.put("ulasan",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				h.put("jalanHubungan",
						rs.getString("JALAN_HUBUNGAN") == null ? "" : rs
								.getString("JALAN_HUBUNGAN"));
				h.put("kawasanBerhampiran",
						rs.getString("KAWASAN_BERHAMPIRAN") == null ? "" : rs
								.getString("KAWASAN_BERHAMPIRAN"));
				h.put("jarakDariBandar",
						rs.getString("JARAK_DARIBANDAR") == null ? "" : rs
								.getString("JARAK_DARIBANDAR"));
				h.put("keadaanRupabumi",
						rs.getString("KEADAAN_RUPABUMI") == null ? "" : rs
								.getString("KEADAAN_RUPABUMI"));
				h.put("keadaanTanah",
						rs.getString("KEADAAN_TANAH") == null ? "" : rs
								.getString("KEADAAN_TANAH"));
				h.put("flagAir",
						rs.getString("FLAG_KEMUDAHANASAS_AIR") == null ? ""
								: rs.getString("FLAG_KEMUDAHANASAS_AIR"));
				h.put("flagElektrik", rs
						.getString("FLAG_KEMUDAHANASAS_ELEKTRIK") == null ? ""
						: rs.getString("FLAG_KEMUDAHANASAS_ELEKTRIK"));
				h.put("flagTel",
						rs.getString("FLAG_KEMUDAHANASAS_TEL") == null ? ""
								: rs.getString("FLAG_KEMUDAHANASAS_TEL"));
				h.put("kemudahanAsasLain",
						rs.getString("KEMUDAHAN_ASAS") == null ? "" : rs
								.getString("KEMUDAHAN_ASAS"));
				h.put("utara",
						rs.getString("SEMP_UTARA") == null ? "" : rs
								.getString("SEMP_UTARA"));
				h.put("selatan",
						rs.getString("SEMP_SELATAN") == null ? "" : rs
								.getString("SEMP_SELATAN"));
				h.put("timur",
						rs.getString("SEMP_TIMUR") == null ? "" : rs
								.getString("SEMP_TIMUR"));
				h.put("barat",
						rs.getString("SEMP_BARAT") == null ? "" : rs
								.getString("SEMP_BARAT"));
				h.put("pelapor",
						rs.getString("NAMA_PELAPOR") == null ? "" : rs
								.getString("NAMA_PELAPOR"));
				h.put("idJawatan",
						rs.getString("ID_JAWATANPELAPOR") == null ? "99999"
								: rs.getString("ID_JAWATANPELAPOR"));
				h.put("idNegeri",
						rs.getString("ID_NEGERIPELAPOR") == null ? "99999" : rs
								.getString("ID_NEGERIPELAPOR"));
				h.put("jawatan", rs.getString("NAMA_JAWATAN_PELAPOR") == null ? ""
						: rs.getString("NAMA_JAWATAN_PELAPOR"));
				maklumatLaporan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return maklumatLaporan;
	}

	public void kemaskiniLaporan(String idLaporan, String tarikhLawatan, String socFlagLawatan,
			String tujuanLaporan, String txtLaporanAtasTanah, String txtIsuUlasan, String catatan,
			String pelapor, String idJawatanPelapor, String idNegeri, String jalanHubungan, 
			String kawasanBerhampiran, String jarakDariBandar, String flagAir,
			String flagElektrik, String flagTelefon, String kemudahanLain,
			String keaadaanTanah, String keadaanRupaBumi, String utara,
			String selatan, String timur, String barat, HttpSession session)
			throws Exception {

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

			// TBLPHPLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporan);
			r.add("TARIKH_LAWATAN",
					r.unquote("to_date('" + tarikhLawatan + "','dd/MM/yyyy')"));
			r.add("FLAG_LAWATAN", socFlagLawatan);
			r.add("TUJUAN_LAPORAN", tujuanLaporan);
			r.add("LAPORAN_ATASTANAH", txtLaporanAtasTanah);
			r.add("ULASAN", txtIsuUlasan);
			r.add("CATATAN", catatan);
			r.add("NAMA_PELAPOR", pelapor);
//			r.add("JAWATAN_PELAPOR", jawatan);
			r.add("ID_JAWATANPELAPOR", idJawatanPelapor);
			r.add("ID_NEGERIPELAPOR", idNegeri);
			r.add("JALAN_HUBUNGAN", jalanHubungan);
			r.add("KAWASAN_BERHAMPIRAN", kawasanBerhampiran);
			r.add("JARAK_DARIBANDAR", jarakDariBandar);
			r.add("FLAG_KEMUDAHANASAS_AIR", flagAir);
			r.add("FLAG_KEMUDAHANASAS_ELEKTRIK", flagElektrik);
			r.add("FLAG_KEMUDAHANASAS_TEL", flagTelefon);
			r.add("KEMUDAHAN_ASAS", kemudahanLain);
			r.add("KEADAAN_TANAH", keaadaanTanah);
			r.add("KEADAAN_RUPABUMI", keadaanRupaBumi);
			r.add("SEMP_UTARA", utara);
			r.add("SEMP_BARAT", barat);
			r.add("SEMP_TIMUR", timur);
			r.add("SEMP_SELATAN", selatan);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPLAPORANTANAH");
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

	public Vector getSenaraiImejan(String idLaporan) throws Exception {
		Db db = null;
		String sql = "";
		Vector senaraiImejan = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN"
					+ " WHERE ID_LAPORANTANAH = '" + idLaporan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaImej", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("namaFail",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				senaraiImejan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}

		return senaraiImejan;
	}

	public Vector getMaklumatImej(String idDokumen) throws Exception {
		Db db = null;
		Vector maklumatImejan = new Vector();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, NAMA_FAIL, CATATAN FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaImej", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanImej",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("namaFail",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				maklumatImejan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

		return maklumatImejan;
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

	public void hapusDokumen(String idDokumen) throws Exception {
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
	
	public Vector getSenaraiKehadiran(String idLaporanTanah) throws Exception {
		Db db = null;
		String sql = "";
		Vector senaraiKehadiran = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PEGAWAILAPORANTANAH, A.NAMA_PEGAWAI, B.KETERANGAN AS JAWATAN, C.NAMA_NEGERI"
					+ " FROM TBLPHPPEGAWAILAPORANTANAH A, TBLRUJJAWATAN B, TBLRUJNEGERI C WHERE A.ID_JAWATAN = B.ID_JAWATAN AND A.ID_NEGERIPEGAWAI = C.ID_NEGERI"
					+ " AND A.ID_LAPORANTANAH = '" + idLaporanTanah + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPegawaiLaporanTanah",
						rs.getString("ID_PEGAWAILAPORANTANAH") == null ? ""
								: rs.getString("ID_PEGAWAILAPORANTANAH"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI").toUpperCase());
				h.put("jawatan", rs.getString("JAWATAN") == null ? "" : rs
						.getString("JAWATAN").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				senaraiKehadiran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}

		return senaraiKehadiran;
	}
	
	public Vector getMaklumatKehadiran(String idPegawaiLaporanTanah)
			throws Exception {
		Db db = null;
		String sql = "";
		Vector maklumatKehadiran = new Vector();

		try {
			maklumatKehadiran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_PEGAWAI, ID_JAWATAN, ID_NEGERIPEGAWAI FROM TBLPHPPEGAWAILAPORANTANAH WHERE ID_PEGAWAILAPORANTANAH = '"
					+ idPegawaiLaporanTanah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("idJawatan", rs.getString("ID_JAWATAN") == null ? "99999"
						: rs.getString("ID_JAWATAN"));
				h.put("idNegeri",
						rs.getString("ID_NEGERIPEGAWAI") == null ? "99999" : rs
								.getString("ID_NEGERIPEGAWAI"));
				maklumatKehadiran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
		return maklumatKehadiran;
	}
	
	public void simpanKehadiran(String idLaporanTanah, String txtNamaPegawai,
			String idNegeri, String idJawatan, HttpSession session)
			throws Exception {

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

			// TBLPHPPEGAWAILAPORANTANAH
			long idPegawaiLaporan = DB
					.getNextID("TBLPHPPEGAWAILAPORANTANAH_SEQ");
			r.add("ID_PEGAWAILAPORANTANAH", idPegawaiLaporan);
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("NAMA_PEGAWAI", txtNamaPegawai);
			r.add("ID_JAWATAN", idJawatan);
			r.add("ID_NEGERIPEGAWAI", idNegeri);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "INS",
					"FAIL PENAWARAN [" + idLaporanTanah
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
	}
	
	public void simpanKemaskiniKehadiran(String idPegawaiLaporanTanah,
			String txtNamaPegawai, String idNegeri, String idJawatan,
			HttpSession session) throws Exception {

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

			// TBLPHPPEGAWAILAPORANTANAH
			r.update("ID_PEGAWAILAPORANTANAH", idPegawaiLaporanTanah);
			r.add("NAMA_PEGAWAI", txtNamaPegawai);
			r.add("ID_JAWATAN", idJawatan);
			r.add("ID_NEGERIPEGAWAI", idNegeri);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + idPegawaiLaporanTanah
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
	
	public void hapusKehadiran(String idPegawaiLaporanTanah, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPEGAWAILAPORANTANAH
			r.add("ID_PEGAWAILAPORANTANAH", idPegawaiLaporanTanah);

			sql = r.getSQLDelete("TBLPHPPEGAWAILAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610200", "4", null, session, "DEL",
					"FAIL PENAWARAN [" + idPegawaiLaporanTanah
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
	
	public void goToHantarNotifikasi(String idLaporanTanah, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		Vector beanMaklumatEmail = null;
		EmailSender email = EmailSender.getInstance();
		String sql = "";
		String noFail = "";
		String userName = "";
		String userEmail = "nurulain.siprotech@gmail.com"; //untuk sementara
		String namaJKPTG = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT A.ID_LAPORANTANAH, A.ID_PERMOHONAN, C.NO_FAIL, D.USER_NAME, E.EMEL, F.NAMA_PEJABAT "
				+ " FROM TBLPHPLAPORANTANAH A, TBLPERMOHONAN B, TBLPFDFAIL C, USERS D, USERS_INTERNAL E, TBLRUJPEJABATJKPTG F"
				+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_NEGERI = F.ID_NEGERI AND F.ID_SEKSYEN = '4'"
				+ " AND C.ID_MASUK = D.USER_ID AND D.USER_ID = E.USER_ID AND A.ID_LAPORANTANAH = '"+idLaporanTanah+"'";
			
			ResultSet rsEmel = stmt.executeQuery(sql);
			if (rsEmel.next()){
				noFail = rsEmel.getString("NO_FAIL");
				userName = rsEmel.getString("USER_NAME");
				//userEmail = rsEmel.getString("EMEL");
				namaJKPTG = rsEmel.getString("NAMA_PEJABAT");
			}	
			
			email.RECIEPIENT = userEmail;
			email.SUBJECT = "PERMOHONAN ULASAN URUSAN PELEPASAN BAGI NO. FAIL " + noFail;
			email.MESSAGE = "Tuan/ Puan,<br><br><br>"
							 + "Saya dengan hormatnya diarah merujuk kepada perkara di atas.\r\n"
							 + "<br>Adalah dimaklumkan bahawa " + namaJKPTG + "mengemukakan laporan berhubung keadaan terkini tapak\r\n"
							 + "tersebut."
							 + "<br><br>Sekian, terima kasih.<br><br><br>"			
							 + "Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			email.sendEmail();
			
			log.info("weh kudaa apa yang keluar : " + email.MESSAGE);
			
		} finally {
			if(db != null)
				db.close();
		}
		
	}
	
	public String getIdHakmilikByIdPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT D.ID_HAKMILIK FROM TBLPHPLAPORANTANAH A, TBLPERMOHONAN B, TBLPHPHAKMILIKPERMOHONAN C, TBLPHPHAKMILIK D"
				+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_HAKMILIKPERMOHONAN = D.ID_HAKMILIKPERMOHONAN "
				+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_HAKMILIK");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getFlagLawatanByIdLaporanTanah(String idLaporanTanah)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FLAG_LAWATAN FROM TBLPHPLAPORANTANAH WHERE ID_LAPORANTANAH = '"
					+ idLaporanTanah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("FLAG_LAWATAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdNegeriTanahPohon(String idLaporan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT D.ID_NEGERI FROM TBLPHPLAPORANTANAH A, TBLPERMOHONAN B, TBLPHPHAKMILIKPERMOHONAN C, TBLPHPHAKMILIK D"
				+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND"
				+ " C.ID_HAKMILIKPERMOHONAN = D.ID_HAKMILIKPERMOHONAN AND A.ID_LAPORANTANAH = '" + idLaporan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_NEGERI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
}
