package ekptg.model.php2;

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
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmSenaraiLaporanTanahData {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

	public Vector getMaklumatTanah(String idHakmilikAgensi) throws Exception {
		Db db = null;
		Vector maklumatTanah = new Vector();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT L.ID_HAKMILIKAGENSI, A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK,"
					+ " B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.NO_WARTA, A.KEGUNAAN_TANAH, L.LUAS_BERSAMAAN, L.ID_LUAS_BERSAMAAN, A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI,"
					+ " G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KOD_LUAS AS KOD_LUAS, K.KETERANGAN AS JENIS_LUAS_BERSAMAAN, A.STATUS_RIZAB"
					+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I,TBLRUJAGENSI J, TBLRUJLUAS K, TBLHTPHAKMILIKAGENSI L"
					+ " WHERE A.ID_HAKMILIK = L.ID_HAKMILIK AND A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+)"
					+ " AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND J.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND L.ID_AGENSI = J.ID_AGENSI(+) AND L.ID_LUAS_BERSAMAAN = K.ID_LUAS"
					+ " AND L.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
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
						rs.getString("LUAS_BERSAMAAN") == null
								|| rs.getString("JENIS_LUAS_BERSAMAAN") == null ? ""
								: Utils.formatLuas(rs
										.getDouble("LUAS_BERSAMAAN"))
										+ " "
										+ rs.getString("JENIS_LUAS_BERSAMAAN"));
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

			sql = "SELECT * FROM TBLPHPRUJLAPORANTANAH WHERE ID_LAPORANTANAH = '"
					+ idLaporan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idLaporan", rs.getString("ID_LAPORANTANAH") == null ? ""
						: rs.getString("ID_LAPORANTANAH"));
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));

				h.put("tarikhLawatan",
						rs.getString("TARIKH_LAWATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_LAWATAN")));
				h.put("tujuanLaporan",
						rs.getString("TUJUAN_LAPORAN") == null ? "" : rs
								.getString("TUJUAN_LAPORAN"));
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
						rs.getString("KEMUDAHAN_ASAS_LAIN") == null ? "" : rs
								.getString("KEMUDAHAN_ASAS_LAIN"));
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
				h.put("jawatan", rs.getString("JAWATAN_PELAPOR") == null ? ""
						: rs.getString("JAWATAN_PELAPOR"));
				maklumatLaporan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return maklumatLaporan;
	}

	public void kemaskiniLaporan(String idLaporan, String tarikhLawatan,
			String tujuanLaporan, String catatan, String pelapor,
			String jawatan, String idNegeri, String jalanHubungan,
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

			// TBLPHPRUJLAPORANTANAH
			r.update("ID_LAPORANTANAH", idLaporan);
			r.add("TARIKH_LAWATAN",
					r.unquote("to_date('" + tarikhLawatan + "','dd/MM/yyyy')"));
			r.add("TUJUAN_LAPORAN", tujuanLaporan);
			r.add("CATATAN", catatan);
			r.add("NAMA_PELAPOR", pelapor);
			r.add("JAWATAN_PELAPOR", jawatan);
			r.add("ID_NEGERIPELAPOR", idNegeri);

			r.add("JALAN_HUBUNGAN", jalanHubungan);
			r.add("KAWASAN_BERHAMPIRAN", kawasanBerhampiran);
			r.add("JARAK_DARIBANDAR", jarakDariBandar);
			r.add("FLAG_KEMUDAHANASAS_AIR", flagAir);
			r.add("FLAG_KEMUDAHANASAS_ELEKTRIK", flagElektrik);
			r.add("FLAG_KEMUDAHANASAS_TEL", flagTelefon);
			r.add("KEMUDAHAN_ASAS_LAIN", kemudahanLain);
			r.add("KEADAAN_TANAH", keaadaanTanah);
			r.add("KEADAAN_RUPABUMI", keadaanRupaBumi);
			r.add("SEMP_UTARA", utara);
			r.add("SEMP_BARAT", barat);
			r.add("SEMP_TIMUR", timur);
			r.add("SEMP_SELATAN", selatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPRUJLAPORANTANAH");
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

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMENLAPORANTANAH"
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

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, NAMA_FAIL, CATATAN FROM TBLPHPDOKUMENLAPORANTANAH WHERE ID_DOKUMEN = '"
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

			sql = r.getSQLUpdate("TBLPHPDOKUMENLAPORANTANAH");
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

			sql = r.getSQLDelete("TBLPHPDOKUMENLAPORANTANAH");
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
}
