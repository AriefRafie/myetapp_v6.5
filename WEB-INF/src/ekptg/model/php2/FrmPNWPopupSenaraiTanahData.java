/**
 * 
 */
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmPNWPopupSenaraiTanahData {

	private Vector senaraiTanah = null;
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatTanah_pukal = null;
	private static final Log log = LogFactory.getLog(FrmPNWPopupSenaraiTanahData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setMaklumatTanah_pukal(String idHakmilikAgensi,
			String idHakmilikSementara, Db db) throws Exception {
		// Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah_pukal = new Vector();
			// db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HMA.ID_HAKMILIKAGENSI, HM.ID_HAKMILIK, NULL AS ID_HAKMILIKSEMENTARA, HM.PEGANGAN_HAKMILIK,"
					+ " HM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, HM.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HM.NO_LOT, HMA.ID_LUAS_BERSAMAAN, HMA.LUAS_BERSAMAAN,"
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HM.NO_WARTA, HM.TARIKH_WARTA, HM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HM.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.KEGUNAAN_TANAH,"
					+ " HM.SYARAT, HM.SEKATAN, HMA.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"

					+ " FROM TBLHTPHAKMILIKAGENSI HMA, TBLHTPHAKMILIK HM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE HMA.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMA.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND HM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMA.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HMA.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

			sql = sql + " UNION";

			sql = sql
					+ " SELECT NULL AS ID_HAKMILIKAGENSI, NULL AS ID_HAKMILIK, HMS.ID_HAKMILIKSEMENTARA, HMS.PEGANGAN_HAKMILIK,"
					+ " HMS.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HMS.NO_HAKMILIK, HMS.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HMS.NO_LOT, HMS.ID_LUAS AS ID_LUAS_BERSAMAAN, HMS.LUAS AS LUAS_BERSAMAAN, "
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HMS.NO_WARTA, HMS.TARIKH_WARTA, HMS.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HMS.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HMS.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HMS.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HMS.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HMS.KEGUNAAN_TANAH,"
					+ " HMS.SYARAT, HMS.SEKATAN, HMS.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"
					+ " FROM TBLPHPHAKMILIKSEMENTARA HMS, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
					+ " WHERE HMS.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HMS.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMS.ID_LUAS = RUJLUAS.ID_LUAS(+) AND HMS.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HMS.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HMS.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HMS.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HMS.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMS.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HMS.ID_HAKMILIKSEMENTARA = '" + idHakmilikSementara
					+ "'";
			System.out.println("cek senarai berkaitan = " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("idHakmilikSementara",
						rs.getString("ID_HAKMILIKSEMENTARA") == null ? "" : rs
								.getString("ID_HAKMILIKSEMENTARA"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null
								|| rs.getString("NO_HAKMILIK") == null ? ""
								: rs.getString("KOD_JENIS_HAKMILIK")
										.toUpperCase())
								+ " "
								+ (rs.getString("KOD_JENIS_HAKMILIK") == null
										|| rs.getString("NO_HAKMILIK") == null ? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("idLot",
						rs.getString("ID_LOT") == null ? "" : rs
								.getString("ID_LOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("lot",
						(rs.getString("JENIS_LOT") == null ? "" : rs.getString(
								"JENIS_LOT").toUpperCase())
								+ " "
								+ (rs.getString("NO_LOT") == null ? "" : rs
										.getString("NO_LOT")));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? ""
						: rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				h.put("luas",
						(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_BERSAMAAN")))
								+ " "
								+ (rs.getString("JENIS_LUAS") == null ? "" : rs
										.getString("JENIS_LUAS")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idMukim",
						rs.getString("ID_MUKIM") == null ? "" : rs
								.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? ""
						: rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori",
						rs.getString("ID_SUBKATEGORI") == null ? "" : rs
								.getString("ID_SUBKATEGORI"));
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
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				beanMaklumatTanah_pukal.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	public void simpan_pukal(String idPermohonan, String idHakmilikAgensi,
			String idHakmilikSementara, HttpSession session, Db db,
			Connection conn) throws Exception {

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		// TBLPHPHAKMILIKPERMOHONAN
		r = new SQLRenderer();
		long idhakmilikPermohonan = DB
				.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
		r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
		r.add("ID_PERMOHONAN", idPermohonan);
		r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
		r.add("ID_HAKMILIKSEMENTARA", idHakmilikSementara);
		r.add("FLAG_HAKMILIK", "T");

		r.add("ID_MASUK", userId);
		r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

		sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
		stmt.executeUpdate(sql);

		// TBLPHPHAKMILIK
		setMaklumatTanah_pukal(idHakmilikAgensi, idHakmilikSementara, db);
		if (getBeanMaklumatTanah_pukal().size() != 0) {
			Hashtable hashTanah = (Hashtable) getBeanMaklumatTanah_pukal().get(
					0);

			r = new SQLRenderer();
			long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
			r.add("ID_HAKMILIK", idHakmilik);
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("PEGANGAN_HAKMILIK", hashTanah.get("peganganHakmilik"));
			r.add("ID_NEGERI", hashTanah.get("idNegeri"));
			r.add("ID_DAERAH", hashTanah.get("idDaerah"));
			r.add("ID_MUKIM", hashTanah.get("idMukim"));
			r.add("NO_WARTA", hashTanah.get("noWarta"));
			r.add("TARIKH_WARTA",
					r.unquote("to_date('" + hashTanah.get("tarikhWarta")
							+ "','dd/MM/yyyy')"));
			r.add("ID_JENISHAKMILIK", hashTanah.get("idJenisHakmilik"));
			r.add("NO_HAKMILIK", hashTanah.get("noHakmilik"));
			r.add("ID_LOT", hashTanah.get("idLot"));
			r.add("NO_LOT", hashTanah.get("noLot"));
			r.add("ID_LUAS", hashTanah.get("idLuas"));
			r.add("LUAS", hashTanah.get("luasBersamaan"));
			r.add("SYARAT", hashTanah.get("syarat"));
			r.add("SEKATAN", hashTanah.get("sekatan"));
			r.add("KEGUNAAN_TANAH", hashTanah.get("kegunaanTanah"));
			r.add("ID_KATEGORI", hashTanah.get("idKategori"));
			r.add("ID_SUBKATEGORI", hashTanah.get("idSubKategori"));
			r.add("ID_KEMENTERIAN", hashTanah.get("idKementerian"));
			r.add("ID_AGENSI", hashTanah.get("idAgensi"));

			sql = r.getSQLInsert("TBLPHPHAKMILIK");
			stmt.executeUpdate(sql);
		}

	}

	public void carianTanah(String idJenisTanah, String peganganHakmilik,
			String jenisHakmilik, String noHakmilik, String jenisLot,
			String lot, String noWarta, String tarikhWarta, String idNegeri,
			String idDaerah, String idMukim, String idKementerian,
			String idAgensi) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HMA.ID_HAKMILIKAGENSI, HM.ID_HAKMILIK, NULL AS ID_HAKMILIKSEMENTARA, HM.PEGANGAN_HAKMILIK,"
					+ " HM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, HM.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HM.NO_LOT, HMA.ID_LUAS_BERSAMAAN, HMA.LUAS_BERSAMAAN,"
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HM.NO_WARTA, HM.TARIKH_WARTA, HM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HM.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.KEGUNAAN_TANAH,"
					+ " HM.SYARAT, HM.SEKATAN, HMA.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"

					+ " FROM TBLHTPHAKMILIKAGENSI HMA, TBLHTPHAKMILIK HM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE HMA.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMA.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND HM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMA.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND (HM.STATUS_SAH != 'B' OR HM.STATUS_SAH IS NULL) AND HMA.FLAG_AKTIF = 'Y'";

			// idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1) {
						sql = sql + " AND HM.STATUS_RIZAB IS NOT NULL";
					} else if (Integer.parseInt(idJenisTanah) == 2) {
						sql = sql + " AND HM.STATUS_RIZAB IS NULL";
					}
				}
			}

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

			// noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(HM.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhWarta
			if (tarikhWarta != null) {
				if (!tarikhWarta.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(HM.TARIKH_WARTA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhWarta)).toUpperCase()
							+ "'";
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
					sql = sql + " AND RUJAGENSI.ID_KEMENTERIAN = '"
							+ idKementerian.trim().toUpperCase() + "'";
				}
			}

			// idAgensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")
						&& !idAgensi.trim().equals("99999")) {
					sql = sql + " AND HMA.ID_AGENSI = '"
							+ idAgensi.trim().toUpperCase() + "'";
				}
			}

			sql = sql + " UNION";

			sql = sql
					+ " SELECT NULL AS ID_HAKMILIKAGENSI, NULL AS ID_HAKMILIK, HMS.ID_HAKMILIKSEMENTARA, HMS.PEGANGAN_HAKMILIK,"
					+ " HMS.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HMS.NO_HAKMILIK, HMS.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HMS.NO_LOT, HMS.ID_LUAS AS ID_LUAS_BERSAMAAN, HMS.LUAS AS LUAS_BERSAMAAN, "
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HMS.NO_WARTA, HMS.TARIKH_WARTA, HMS.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HMS.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HMS.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HMS.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HMS.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HMS.KEGUNAAN_TANAH,"
					+ " HMS.SYARAT, HMS.SEKATAN, HMS.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"
					+ " FROM TBLPHPHAKMILIKSEMENTARA HMS, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
					+ " WHERE HMS.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HMS.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMS.ID_LUAS = RUJLUAS.ID_LUAS(+) AND HMS.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HMS.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HMS.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HMS.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HMS.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMS.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)";

			// peganganHakmilik
			if (peganganHakmilik != null) {
				if (!peganganHakmilik.trim().equals("")) {
					sql = sql
							+ " AND UPPER(HMS.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ peganganHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// jenisHakmilik
			if (jenisHakmilik != null) {
				if (!jenisHakmilik.trim().equals("")
						&& !jenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND HMS.ID_JENISHAKMILIK = '"
							+ jenisHakmilik.trim().toUpperCase() + "'";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(HMS.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// jenisLot
			if (jenisLot != null) {
				if (!jenisLot.trim().equals("")
						&& !jenisLot.trim().equals("99999")) {
					sql = sql + " AND HMS.ID_LOT = '"
							+ jenisLot.trim().toUpperCase() + "'";
				}
			}

			// lot
			if (lot != null) {
				if (!lot.trim().equals("")) {
					sql = sql + " AND UPPER(HMS.NO_LOT) LIKE '%' ||'"
							+ lot.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(HMS.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			// tarikhWarta
			if (tarikhWarta != null) {
				if (!tarikhWarta.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(HMS.TARIKH_WARTA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhWarta)).toUpperCase()
							+ "'";
				}
			}

			// idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")
						&& !idNegeri.trim().equals("99999")) {
					sql = sql + " AND HMS.ID_NEGERI = '"
							+ idNegeri.trim().toUpperCase() + "'";
				}
			}

			// idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")
						&& !idDaerah.trim().equals("99999")) {
					sql = sql + " AND HMS.ID_DAERAH = '"
							+ idDaerah.trim().toUpperCase() + "'";
				}
			}

			// idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")
						&& !idMukim.trim().equals("99999")) {
					sql = sql + " AND HMS.ID_MUKIM = '"
							+ idMukim.trim().toUpperCase() + "'";
				}
			}

			// idKementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")
						&& !idKementerian.trim().equals("99999")) {
					sql = sql + " AND RUJAGENSI.ID_KEMENTERIAN = '"
							+ idKementerian.trim().toUpperCase() + "'";
				}
			}

			// idAgensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")
						&& !idAgensi.trim().equals("99999")) {
					sql = sql + " AND HMS.ID_AGENSI = '"
							+ idAgensi.trim().toUpperCase() + "'";
				}
			}

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("idHakmilikSementara",
						rs.getString("ID_HAKMILIKSEMENTARA") == null ? "" : rs
								.getString("ID_HAKMILIKSEMENTARA"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null
								|| rs.getString("NO_HAKMILIK") == null ? ""
								: rs.getString("KOD_JENIS_HAKMILIK")
										.toUpperCase())
								+ " "
								+ (rs.getString("KOD_JENIS_HAKMILIK") == null
										|| rs.getString("NO_HAKMILIK") == null ? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("idLot",
						rs.getString("ID_LOT") == null ? "" : rs
								.getString("ID_LOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("lot",
						(rs.getString("JENIS_LOT") == null ? "" : rs.getString(
								"JENIS_LOT").toUpperCase())
								+ " "
								+ (rs.getString("NO_LOT") == null ? "" : rs
										.getString("NO_LOT")));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? ""
						: rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				// h.put("luas", (rs.getString("LUAS_BERSAMAAN") == null ? "" :
				// Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN"))) + " " +
				// (rs.getString("JENIS_LUAS") == null ? "" :
				// rs.getString("JENIS_LUAS")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idMukim",
						rs.getString("ID_MUKIM") == null ? "" : rs
								.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? ""
						: rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori",
						rs.getString("ID_SUBKATEGORI") == null ? "" : rs
								.getString("ID_SUBKATEGORI"));
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
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				senaraiTanah.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpan(String idPermohonan, String idHakmilikAgensi,
			String idHakmilikSementara, HttpSession session) throws Exception {

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

			// TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			long idhakmilikPermohonan = DB
					.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("ID_HAKMILIKSEMENTARA", idHakmilikSementara);
			r.add("FLAG_HAKMILIK", "T");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIK
			setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
			if (getBeanMaklumatTanah().size() != 0) {
				Hashtable hashTanah = (Hashtable) getBeanMaklumatTanah().get(0);

				r = new SQLRenderer();
				long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
				r.add("ID_HAKMILIK", idHakmilik);
				r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
				r.add("PEGANGAN_HAKMILIK", hashTanah.get("peganganHakmilik"));
				r.add("ID_NEGERI", hashTanah.get("idNegeri"));
				r.add("ID_DAERAH", hashTanah.get("idDaerah"));
				r.add("ID_MUKIM", hashTanah.get("idMukim"));
				r.add("NO_WARTA", hashTanah.get("noWarta"));
				r.add("TARIKH_WARTA",
						r.unquote("to_date('" + hashTanah.get("tarikhWarta")
								+ "','dd/MM/yyyy')"));
				r.add("ID_JENISHAKMILIK", hashTanah.get("idJenisHakmilik"));
				r.add("NO_HAKMILIK", hashTanah.get("noHakmilik"));
				r.add("ID_LOT", hashTanah.get("idLot"));
				r.add("NO_LOT", hashTanah.get("noLot"));
				r.add("ID_LUAS", hashTanah.get("idLuas"));
				r.add("LUAS", hashTanah.get("luasBersamaan"));
				r.add("SYARAT", hashTanah.get("syarat"));
				r.add("SEKATAN", hashTanah.get("sekatan"));
				r.add("KEGUNAAN_TANAH", hashTanah.get("kegunaanTanah"));
				r.add("ID_KATEGORI", hashTanah.get("idKategori"));
				r.add("ID_SUBKATEGORI", hashTanah.get("idSubKategori"));
				r.add("ID_KEMENTERIAN", hashTanah.get("idKementerian"));
				r.add("ID_AGENSI", hashTanah.get("idAgensi"));

				sql = r.getSQLInsert("TBLPHPHAKMILIK");
				stmt.executeUpdate(sql);

			}

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

	public String saveHakmilikSementara(String jenisHakmilik,
			String txtNoHakmilik, String jenisLot, String txtNoLot,
			String txtLuasBersamaan, String idNegeri, String idDaerah,
			String idMukim, String idKategori, String idSubKategori,
			String idKementerian, String idAgensi, String txtSyarat,
			String txtSekatan, String txtKegunaanTanah, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String idPHPHakMilikSementaraString = "";
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPHAKMILIKSEMENTARA
			r = new SQLRenderer();
			long idHakmilikSementara = DB
					.getNextID("TBLPHPHAKMILIKSEMENTARA_SEQ");
			idPHPHakMilikSementaraString = String.valueOf(idHakmilikSementara);
			r.add("ID_HAKMILIKSEMENTARA", idHakmilikSementara);
			String peganganHakmilik = "";
			peganganHakmilik = getKodNegeri(idNegeri) + getKodDaerah(idDaerah)
					+ getKodMukim(idMukim) + getKodJenisHakmilik(jenisHakmilik)
					+ Utils.digitLastFormatted(txtNoHakmilik, 8);
			r.add("PEGANGAN_HAKMILIK", peganganHakmilik);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_DAERAH", idDaerah);
			r.add("ID_MUKIM", idMukim);
			r.add("ID_JENISHAKMILIK", jenisHakmilik);
			r.add("NO_HAKMILIK", txtNoHakmilik);
			r.add("ID_LOT", jenisLot);
			r.add("NO_LOT", txtNoLot);
			r.add("ID_LUAS", "2");
			r.add("LUAS", txtLuasBersamaan);
			r.add("SYARAT", txtSyarat);
			r.add("SEKATAN", txtSekatan);
			r.add("KEGUNAAN_TANAH", txtKegunaanTanah);
			r.add("ID_KATEGORI", idKategori);
			r.add("ID_SUBKATEGORI", idSubKategori);
			r.add("ID_KEMENTERIAN", idKementerian);
			r.add("ID_AGENSI", idAgensi);

			sql = r.getSQLInsert("TBLPHPHAKMILIKSEMENTARA");
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
		return idPHPHakMilikSementaraString;
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTanah(String idHakmilikAgensi,
			String idHakmilikSementara) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HMA.ID_HAKMILIKAGENSI, HM.ID_HAKMILIK, NULL AS ID_HAKMILIKSEMENTARA, HM.PEGANGAN_HAKMILIK,"
					+ " HM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, HM.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HM.NO_LOT, HMA.ID_LUAS_BERSAMAAN, HMA.LUAS_BERSAMAAN,"
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HM.NO_WARTA, HM.TARIKH_WARTA, HM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HM.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.KEGUNAAN_TANAH,"
					+ " HM.SYARAT, HM.SEKATAN, HMA.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"

					+ " FROM TBLHTPHAKMILIKAGENSI HMA, TBLHTPHAKMILIK HM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE HMA.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMA.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND HM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMA.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HMA.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

			sql = sql + " UNION";

			sql = sql
					+ " SELECT NULL AS ID_HAKMILIKAGENSI, NULL AS ID_HAKMILIK, HMS.ID_HAKMILIKSEMENTARA, HMS.PEGANGAN_HAKMILIK,"
					+ " HMS.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HMS.NO_HAKMILIK, HMS.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HMS.NO_LOT, HMS.ID_LUAS AS ID_LUAS_BERSAMAAN, HMS.LUAS AS LUAS_BERSAMAAN, "
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HMS.NO_WARTA, HMS.TARIKH_WARTA, HMS.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HMS.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HMS.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HMS.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HMS.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HMS.KEGUNAAN_TANAH,"
					+ " HMS.SYARAT, HMS.SEKATAN, HMS.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"
					+ " FROM TBLPHPHAKMILIKSEMENTARA HMS, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
					+ " WHERE HMS.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HMS.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMS.ID_LUAS = RUJLUAS.ID_LUAS(+) AND HMS.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HMS.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HMS.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HMS.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HMS.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMS.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HMS.ID_HAKMILIKSEMENTARA = '" + idHakmilikSementara
					+ "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("idHakmilikSementara",
						rs.getString("ID_HAKMILIKSEMENTARA") == null ? "" : rs
								.getString("ID_HAKMILIKSEMENTARA"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null
								|| rs.getString("NO_HAKMILIK") == null ? ""
								: rs.getString("KOD_JENIS_HAKMILIK")
										.toUpperCase())
								+ " "
								+ (rs.getString("KOD_JENIS_HAKMILIK") == null
										|| rs.getString("NO_HAKMILIK") == null ? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("idLot",
						rs.getString("ID_LOT") == null ? "" : rs
								.getString("ID_LOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("lot",
						(rs.getString("JENIS_LOT") == null ? "" : rs.getString(
								"JENIS_LOT").toUpperCase())
								+ " "
								+ (rs.getString("NO_LOT") == null ? "" : rs
										.getString("NO_LOT")));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? ""
						: rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				h.put("luas",
						(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_BERSAMAAN")))
								+ " "
								+ (rs.getString("JENIS_LUAS") == null ? "" : rs
										.getString("JENIS_LUAS")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idMukim",
						rs.getString("ID_MUKIM") == null ? "" : rs
								.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? ""
						: rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori",
						rs.getString("ID_SUBKATEGORI") == null ? "" : rs
								.getString("ID_SUBKATEGORI"));
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
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				beanMaklumatTanah.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdKementerianByIdAgensi(String idAgensi) throws Exception {
		Db db = null;
		String sql = "";
		String idKementerian = "99999";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KEMENTERIAN FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
					+ idAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				idKementerian = rs.getString("ID_KEMENTERIAN");
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}

		return idKementerian;
	}

	public Vector getSenaraiTanah() {
		return senaraiTanah;
	}

	public void setSenaraiTanah(Vector senaraiTanah) {
		this.senaraiTanah = senaraiTanah;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}

	public Vector getBeanMaklumatTanah_pukal() {
		return beanMaklumatTanah_pukal;
	}

	public void setBeanMaklumatTanah_pukal(Vector beanMaklumatTanah_pukal) {
		this.beanMaklumatTanah_pukal = beanMaklumatTanah_pukal;
	}
	


}
