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

public class FrmCRBPopupSenaraiBorangKData {

	private Vector senaraiBorangK = null;
	private Vector beanMaklumatBorangK = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String saveBorangK(String jenisHakmilik, String txtNoHakmilik,
			String jenisLot, String txtNoLot, String txtLuasBersamaan,
			String idNegeri, String idDaerah, String idMukim, String txtNoSyit,
			String txtNoPelan, String idKategori, String idSubKategori,
			String idKementerian, String idAgensi, String txtSyarat,
			String txtSekatan, String txtKegunaanTanah, String tarikhBorangK,
			String txtCatatan, String txtNoPerserahan, String tarikhCatatan,
			String tarikhTerima, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String idPHPBorangKString = "";
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPBORANGK
			r = new SQLRenderer();
			long idBorangK = DB.getNextID("TBLPHPBORANGK_SEQ");
			idPHPBorangKString = String.valueOf(idBorangK);
			r.add("ID_BORANGK", idBorangK);
			if (!"".equals(tarikhBorangK)) {
				r.add("TARIKH_BORANGK",
						r.unquote("to_date('" + tarikhBorangK
								+ "','dd/MM/yyyy')"));
			}
			r.add("CATATAN", txtCatatan);

			sql = r.getSQLInsert("TBLPHPBORANGK");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIK
			r = new SQLRenderer();
			long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
			String peganganHakmilik = "";
			peganganHakmilik = getKodNegeri(idNegeri) + getKodDaerah(idDaerah)
					+ getKodMukim(idMukim) + getKodJenisHakmilik(jenisHakmilik)
					+ Utils.digitLastFormatted(txtNoHakmilik, 8);
			r.add("PEGANGAN_HAKMILIK", peganganHakmilik);
			r.add("ID_HAKMILIK", idHakmilik);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_DAERAH", idDaerah);
			r.add("ID_MUKIM", idMukim);
			r.add("ID_JENISHAKMILIK", jenisHakmilik);
			r.add("NO_HAKMILIK", txtNoHakmilik);
			r.add("ID_LOT", jenisLot);
			r.add("NO_LOT", txtNoLot);
			r.add("ID_UNITLUASAMBIL", "2");
			r.add("LUAS_AMBIL", txtLuasBersamaan);
			r.add("ID_LUASBERSAMAAN", "2");
			r.add("LUAS_BERSAMAAN", txtLuasBersamaan);
			r.add("NO_SYIT", txtNoSyit);
			r.add("NO_PELAN", txtNoPelan);
			r.add("SYARAT_NYATA", txtSyarat);
			r.add("SEKATAN_KEPENTINGAN", txtSekatan);
			r.add("KEGUNAAN_TANAH", txtKegunaanTanah);
			r.add("ID_KATEGORI", idKategori);
			r.add("ID_SUBKATEGORI", idSubKategori);
			r.add("ID_KEMENTERIAN", idKementerian);
			r.add("ID_AGENSI", idAgensi);

			sql = r.getSQLInsert("TBLPHPHAKMILIK");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIKBORANGK
			r = new SQLRenderer();
			long idHakmilikBorangK = DB.getNextID("TBLPHPHAKMILIKBORANGK_SEQ");
			r.add("ID_HAKMILIKBORANGK", idHakmilikBorangK);
			r.add("ID_BORANGK", idBorangK);
			r.add("ID_HAKMILIK", idHakmilik);

			sql = r.getSQLInsert("TBLPHPHAKMILIKBORANGK");
			stmt.executeUpdate(sql);

			// TBLPHPENDOSANBORANGK
			r = new SQLRenderer();
			long idEndosanBorangK = DB.getNextID("TBLPHPENDOSANBORANGK_SEQ");
			r.add("ID_ENDOSANBORANGK", idEndosanBorangK);
			r.add("ID_BORANGK", idBorangK);
			r.add("NO_PERSERAHAN", txtNoPerserahan);
			if (!"".equals(tarikhCatatan)) {
				r.add("TARIKH_CATATAN",
						r.unquote("to_date('" + tarikhCatatan
								+ "','dd/MM/yyyy')"));
			}
			if (!"".equals(tarikhTerima)) {
				r.add("TARIKH_TERIMA",
						r.unquote("to_date('" + tarikhTerima
								+ "','dd/MM/yyyy')"));
			}

			sql = r.getSQLInsert("TBLPHPENDOSANBORANGK");
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
		return idPHPBorangKString;
	}

	public String getKodNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '"
					+ idNegeri + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_NEGERI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKodDaerah(String idDaerah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH = '"
					+ idDaerah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_DAERAH");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKodMukim(String idMukim) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_MUKIM FROM TBLRUJMUKIM WHERE ID_MUKIM = '"
					+ idMukim + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_MUKIM");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKodJenisHakmilik(String idJenisHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK = '"
					+ idJenisHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_JENIS_HAKMILIK");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatBorangK(String idPHPBorangK) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatBorangK = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BK.ID_BORANGK AS ID_PHPBORANGK, BK.TARIKH_BORANGK, BK.CATATAN, EBK.NO_PERSERAHAN, EBK.TARIKH_CATATAN, EBK.TARIKH_TERIMA,"
					+ " HM.PEGANGAN_HAKMILIK, UPPER(RLOT.KETERANGAN) AS KETERANGAN_LOT, HM.NO_LOT, HM.LUAS_AMBIL, UPPER(RLUAS.KETERANGAN) AS KETERANGAN_LUAS,"
					+ " RJHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, RMUKIM.NAMA_MUKIM, RDAERAH.NAMA_DAERAH, RNEGERI.NAMA_NEGERI, RAGENSI.NAMA_AGENSI, RKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " RKATEGORI.KETERANGAN AS KATEGORI, RSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.SYARAT_NYATA AS SYARAT, HM.SEKATAN_KEPENTINGAN AS SEKATAN, HM.KEGUNAAN_TANAH"
					+ " FROM TBLPHPBORANGK BK, TBLPHPENDOSANBORANGK EBK, TBLPHPHAKMILIKBORANGK HMBK, TBLPHPHAKMILIK HM, TBLRUJLOT RLOT, TBLRUJLUAS RLUAS, TBLRUJJENISHAKMILIK RJHM,"
					+ " TBLRUJMUKIM RMUKIM,  TBLRUJDAERAH RDAERAH, TBLRUJNEGERI RNEGERI, TBLRUJKEMENTERIAN RKEMENTERIAN, TBLRUJAGENSI RAGENSI, TBLRUJKATEGORI RKATEGORI, TBLRUJSUBKATEGORI RSUBKATEGORI"
					+ " WHERE BK.ID_BORANGK = EBK.ID_BORANGK(+) AND BK.ID_BORANGK = HMBK.ID_BORANGK(+) AND HMBK.ID_HAKMILIK = HM.ID_HAKMILIK(+) AND HM.ID_LOT = RLOT.ID_LOT(+)"
					+ " AND HM.ID_UNITLUASAMBIL = RLUAS.ID_LUAS(+) AND HM.ID_JENISHAKMILIK = RJHM.ID_JENISHAKMILIK(+) AND HM.ID_MUKIM = RMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RDAERAH.ID_DAERAH(+)"
					+ " AND HM.ID_NEGERI = RNEGERI.ID_NEGERI(+) AND HM.ID_AGENSI = RAGENSI.ID_AGENSI(+) AND HM.ID_KEMENTERIAN = RKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HM.ID_KATEGORI = RKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND BK.ID_BORANGK = '" + idPHPBorangK + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPHPBorangK",
						rs.getString("ID_PHPBORANGK") == null ? "" : rs
								.getString("ID_PHPBORANGK"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null
						|| rs.getString("NO_HAKMILIK") == null ? "" : rs
						.getString("KOD_JENIS_HAKMILIK").toUpperCase()
						+ " "
						+ rs.getString("NO_HAKMILIK"));
				h.put("noLot",
						rs.getString("KETERANGAN_LOT") == null
								|| rs.getString("NO_LOT") == null ? "" : rs
								.getString("KETERANGAN_LOT")
								+ " "
								+ rs.getString("NO_LOT"));
				h.put("luasLot",
						rs.getString("LUAS_AMBIL") == null
								|| rs.getString("KETERANGAN_LUAS") == null ? ""
								: Utils.formatLuas(rs.getDouble("LUAS_AMBIL"))
										+ " " + rs.getString("KETERANGAN_LUAS"));
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());

				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());

				h.put("tarikhBorangK",
						rs.getDate("TARIKH_BORANGK") == null ? "" : sdf
								.format(rs.getDate("TARIKH_BORANGK")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("noPerserahan",
						rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN").toUpperCase());
				h.put("tarikhCatatan",
						rs.getDate("TARIKH_CATATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_CATATAN")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				beanMaklumatBorangK.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void carianBorangK(String peganganHakmilik, String jenisHakmilik,
			String noHakmilik, String jenisLot, String lot, String idNegeri,
			String idDaerah, String idMukim, String idKementerian,
			String idAgensi) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiBorangK = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BK.ID_BORANGK AS ID_PHPBORANGK, BK.TARIKH_BORANGK, BK.CATATAN, EBK.NO_PERSERAHAN, EBK.TARIKH_CATATAN, EBK.TARIKH_TERIMA,"
					+ " HM.PEGANGAN_HAKMILIK, UPPER(RLOT.KETERANGAN) || ' ' || HM.NO_LOT AS NO_LOT, HM.LUAS_AMBIL, UPPER(RLUAS.KETERANGAN) AS KETERANGAN_LUAS,"
					+ " RJHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, RMUKIM.NAMA_MUKIM, RDAERAH.NAMA_DAERAH, RNEGERI.NAMA_NEGERI, RAGENSI.NAMA_AGENSI, RKEMENTERIAN.NAMA_KEMENTERIAN"
					+ " FROM TBLPHPBORANGK BK, TBLPHPENDOSANBORANGK EBK, TBLPHPHAKMILIKBORANGK HMBK, TBLPHPHAKMILIK HM, TBLRUJLOT RLOT, TBLRUJLUAS RLUAS, TBLRUJJENISHAKMILIK RJHM,"
					+ " TBLRUJMUKIM RMUKIM,  TBLRUJDAERAH RDAERAH, TBLRUJNEGERI RNEGERI, TBLRUJKEMENTERIAN RKEMENTERIAN, TBLRUJAGENSI RAGENSI"
					+ " WHERE BK.ID_BORANGK = EBK.ID_BORANGK(+) AND BK.ID_BORANGK = HMBK.ID_BORANGK(+) AND HMBK.ID_HAKMILIK = HM.ID_HAKMILIK(+) AND HM.ID_LOT = RLOT.ID_LOT(+)"
					+ " AND HM.ID_UNITLUASAMBIL = RLUAS.ID_LUAS(+) AND HM.ID_JENISHAKMILIK = RJHM.ID_JENISHAKMILIK(+) AND HM.ID_MUKIM = RMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RDAERAH.ID_DAERAH(+)"
					+ " AND HM.ID_NEGERI = RNEGERI.ID_NEGERI(+) AND HM.ID_AGENSI = RAGENSI.ID_AGENSI(+) AND HM.ID_KEMENTERIAN = RKEMENTERIAN.ID_KEMENTERIAN(+)";

			// peganganHakmilik
			if (peganganHakmilik != null) {
				if (!peganganHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(HM.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ peganganHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// jenisHakmilik
			if (jenisHakmilik != null) {
				if (!jenisHakmilik.trim().equals("")
						&& !jenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND HM.ID_JENISHAKMILIK = '"
							+ jenisHakmilik.trim().toUpperCase() + "'";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(HM.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// jenisLot
			if (jenisLot != null) {
				if (!jenisLot.trim().equals("")
						&& !jenisLot.trim().equals("99999")) {
					sql = sql + " AND HM.ID_LOT = '"
							+ jenisLot.trim().toUpperCase() + "'";
				}
			}

			// lot
			if (lot != null) {
				if (!lot.trim().equals("")) {
					sql = sql + " AND UPPER(HM.NO_LOT) LIKE '%' ||'"
							+ lot.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")
						&& !idNegeri.trim().equals("99999")) {
					sql = sql + " AND HM.ID_NEGERI = '"
							+ idNegeri.trim().toUpperCase() + "'";
				}
			}

			// idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")
						&& !idDaerah.trim().equals("99999")) {
					sql = sql + " AND HM.ID_DAERAH = '"
							+ idDaerah.trim().toUpperCase() + "'";
				}
			}

			// idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")
						&& !idMukim.trim().equals("99999")) {
					sql = sql + " AND HM.ID_MUKIM = '"
							+ idMukim.trim().toUpperCase() + "'";
				}
			}

			// idKementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")
						&& !idKementerian.trim().equals("99999")) {
					sql = sql + " AND HM.ID_KEMENTERIAN = '"
							+ idKementerian.trim().toUpperCase() + "'";
				}
			}

			// idAgensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")
						&& !idAgensi.trim().equals("99999")) {
					sql = sql + " AND HM.ID_AGENSI = '"
							+ idAgensi.trim().toUpperCase() + "'";
				}
			}

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPHPBorangK",
						rs.getString("ID_PHPBORANGK") == null ? "" : rs
								.getString("ID_PHPBORANGK"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null
						|| rs.getString("NO_HAKMILIK") == null ? "" : rs
						.getString("KOD_JENIS_HAKMILIK").toUpperCase()
						+ " "
						+ rs.getString("NO_HAKMILIK"));
				h.put("mukim",
						rs.getString("NAMA_MUKIM") == null ? "" : rs
								.getString("NAMA_MUKIM"));
				h.put("daerah",
						rs.getString("NAMA_DAERAH") == null ? "" : rs
								.getString("NAMA_DAERAH"));
				h.put("negeri",
						rs.getString("NAMA_NEGERI") == null ? "" : rs
								.getString("NAMA_NEGERI"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("agensi",
						rs.getString("NAMA_AGENSI") == null ? "" : rs
								.getString("NAMA_AGENSI"));
				senaraiBorangK.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSenaraiBorangK() {
		return senaraiBorangK;
	}

	public void setSenaraiBorangK(Vector senaraiBorangK) {
		this.senaraiBorangK = senaraiBorangK;
	}

	public Vector getBeanMaklumatBorangK() {
		return beanMaklumatBorangK;
	}

	public void setBeanMaklumatBorangK(Vector beanMaklumatBorangK) {
		this.beanMaklumatBorangK = beanMaklumatBorangK;
	}
}
