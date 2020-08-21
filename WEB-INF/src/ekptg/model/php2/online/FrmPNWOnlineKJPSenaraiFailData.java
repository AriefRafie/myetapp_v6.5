package ekptg.model.php2.online;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.view.php2.emel.EmailOnline;
import ekptg.view.php2.online.FrmPNWOnlineKJPSenaraiFailView;

public class FrmPNWOnlineKJPSenaraiFailData {

	private Vector senaraiFail = null;
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatHeader = null;
	private Vector beanMaklumatPenawaran = null;
	private Vector beanMaklumatKeputusan = null;
	private Vector<Hashtable<String, Object>> beanMaklumatAgensi = null;
	private Vector<Hashtable<String, Object>> beanMaklumatPemohon = null;
	private Vector listAgensi = null;
	private Vector beanMaklumatImejan = null;
	private Vector listImejan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLog = Logger.getLogger(FrmPNWOnlineKJPSenaraiFailData.class);

	public void carianFail(String userId, String userRole, String noFail, String tajukFail, String noPermohonan,
			String tarikhPermohonan, String idNegeriC, String idDaerahC, String idMukimC, String jenisHakmilik,
			String txtNoHakmilik, String txtNoWarta, String jenisLot, String txtNoLot, String txtNoPegangan,
			String idStatusC) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			// sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA,
			// D.KETERANGAN, B.ID_STATUS, H.USER_LOGIN,B.NO_PERMOHONAN "
			// + " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D,
			// TBLPHPHAKMILIKPERMOHONAN E, TBLHTPHAKMILIKAGENSI F, TBLHTPHAKMILIK G, USERS H
			// "
			// + " WHERE A.ID_URUSAN = '6' AND A.ID_SUBURUSAN = '32' AND A.FLAG_JENIS_FAIL =
			// '4' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS(+)"
			// + " AND E.ID_HAKMILIKAGENSI = F.ID_HAKMILIKAGENSI AND F.ID_HAKMILIK =
			// G.ID_HAKMILIK"
			// + " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "
			// + " AND A.ID_MASUK = H.USER_ID(+) AND A.ID_MASUK ='" + userId + "' ";

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, A.TAJUK_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS, H.USER_LOGIN,B.NO_PERMOHONAN "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLHTPHAKMILIKAGENSI F, TBLHTPHAKMILIK G, USERS H, USERS_KEMENTERIAN I "
					+ " WHERE A.ID_URUSAN = '6' AND A.ID_SUBURUSAN = '32' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS(+)"
					+ " AND E.ID_HAKMILIKAGENSI = F.ID_HAKMILIKAGENSI AND F.ID_HAKMILIK = G.ID_HAKMILIK"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND H.USER_ID = I.USER_ID"
					+ " AND C.ID_KEMENTERIAN = I.ID_KEMENTERIAN AND A.ID_MASUK = H.USER_ID(+)";

			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'" + noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			// tajukFail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'" + tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noPermohonan
			if (noPermohonan != null) {
				if (!noPermohonan.trim().equals("")) {
					sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%' ||'" + noPermohonan.trim().toUpperCase()
							+ "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhPermohonan
			if (tarikhPermohonan != null) {
				if (!tarikhPermohonan.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhPermohonan)).toUpperCase() + "'";
				}
			}

			// idNegeri
			if (idNegeriC != null) {
				if (!idNegeriC.trim().equals("") && !idNegeriC.trim().equals("99999")) {
					sql = sql + " AND G.ID_NEGERI = '" + idNegeriC.trim() + "'";
				}
			}

			// idDaerah
			if (idDaerahC != null) {
				if (!idDaerahC.trim().equals("") && !idDaerahC.trim().equals("99999")) {
					sql = sql + " AND G.ID_DAERAH = '" + idDaerahC.trim() + "'";
				}
			}

			// idMukim
			if (idMukimC != null) {
				if (!idMukimC.trim().equals("") && !idMukimC.trim().equals("99999")) {
					sql = sql + " AND G.ID_MUKIM = '" + idMukimC.trim() + "'";
				}
			}

			// jenisHakmilik
			if (jenisHakmilik != null) {
				if (!jenisHakmilik.trim().equals("") && !jenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND G.ID_JENISHAKMILIK = '" + jenisHakmilik.trim() + "'";
				}
			}

			// noHakmilik
			if (txtNoHakmilik != null) {
				if (!txtNoHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_HAKMILIK) LIKE '%' ||'" + txtNoHakmilik.trim().toUpperCase()
							+ "'|| '%'";
				}
			}

			// txtNoWarta
			if (txtNoWarta != null) {
				if (!txtNoWarta.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_WARTA) LIKE '%' ||'" + txtNoWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			// jenisLot
			if (jenisLot != null) {
				if (!jenisLot.trim().equals("") && !jenisLot.trim().equals("99999")) {
					sql = sql + " AND G.ID_LOT = '" + jenisLot.trim() + "'";
				}
			}

			// lot
			if (txtNoLot != null) {
				if (!txtNoLot.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_LOT) LIKE '%' ||'" + txtNoLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			// peganganHakmilik
			if (txtNoPegangan != null) {
				if (!txtNoPegangan.trim().equals("")) {
					sql = sql + " AND UPPER(G.PEGANGAN_HAKMILIK) LIKE '%' ||'" + txtNoPegangan.trim().toUpperCase()
							+ "'|| '%'";
				}
			}

			// idStatus
			if (idStatusC != null) {
				if (!idStatusC.trim().equals("") && !idStatusC.trim().equals("99999")) {
					sql = sql + " AND B.ID_STATUS = '" + idStatusC.trim() + "'";
				}
			}

			// IF LOGIN IS KJP AGENSI
			if (userRole != null) {
				if (userRole.trim().equals("online_kjpagensi")) {

					sql = sql + " AND I.ID_AGENSI = F.ID_AGENSI";
				}
			}
			myLog.info("carianFail: " + sql);
			myLog.info("senaraiFail: " + sql);

			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN") == null ? "TIADA" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				/*
				 * if ("1610197".equals(rs.getString("ID_STATUS")) ||
				 * "1610212".equals(rs.getString("ID_STATUS"))||
				 * "1610208".equals(rs.getString("ID_STATUS")) ||
				 * "1610207".equals(rs.getString("ID_STATUS"))){ h.put("status",
				 * rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN")); }else
				 * if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") ==
				 * null){ h.put("status", " PENDAFTARAN"); } else { h.put("status",
				 * " SEDANG DIPROSES"); }
				 */
				h.put("userLogin", rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaSuburusan", "PENAWARAN");
				h.put("tajukFail", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				// h.put("tajukFail", "TAJUK_FAIL");
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.NO_PERMOHONAN, A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, B.TARIKH_SURAT, B.TARIKH_TERIMA, B.NO_RUJ_SURAT, A.TAJUK_FAIL, B.TUJUAN, B.ID_PEMOHON, C.FLAG_GUNA, C.CADANGAN_KEGUNAAN "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANPELEPASAN C WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";
			myLog.info("setMaklumatPermohonan: " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("noRujukanOnline",
						rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("noRujukanSurat",
						rs.getString("NO_RUJ_SURAT") == null ? "" : rs.getString("NO_RUJ_SURAT").toUpperCase());
				h.put("flagGuna", rs.getString("FLAG_GUNA") == null ? "" : rs.getString("FLAG_GUNA"));
				h.put("tujuanKegunaan",
						rs.getString("CADANGAN_KEGUNAAN") == null ? "" : rs.getString("CADANGAN_KEGUNAAN"));
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatAgensi(String idAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatAgensi = new Vector<Hashtable<String, Object>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_AGENSI, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, A.ID_AGENSI"
					+ " FROM TBLRUJAGENSI A, TBLRUJNEGERI B WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_AGENSI = '"
					+ idAgensi + "'";

			myLog.info("sql setMaklumatAgensi: " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, Object> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, Object>();
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				h.put("namaAgensi",
						rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				beanMaklumatAgensi.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable<String, Object>();
				h.put("idPejabat", "");
				h.put("idAgensi", "");
				h.put("namaAgensi", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("negeri", "");
				h.put("idNegeri", "");
				beanMaklumatAgensi.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikAgensiByPeganganHakmilik(String peganganHakmilik, String idKategoriPemohon,
			String idAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			if ("3".equals(idKategoriPemohon)) {
				sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
						+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase()
						+ "' AND TBLHTPHAKMILIKAGENSI.ID_AGENSI = '" + idAgensi + "'";
			} else {
				sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
						+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase() + "'";
			}
			myLog.info("getIdHakmilikAgensiByPeganganHakmilik: " + sql);

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

	public void setMaklumatTanah(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT L.ID_HAKMILIKAGENSI, A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK,"
					+ " B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.NO_WARTA, A.KEGUNAAN_TANAH, L.LUAS_BERSAMAAN, L.ID_LUAS_BERSAMAAN, A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI, C.ID_NEGERI,"
					+ " G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, I.ID_KEMENTERIAN, J.NAMA_AGENSI, K.KOD_LUAS AS KOD_LUAS, K.KETERANGAN AS JENIS_LUAS_BERSAMAAN"
					+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I,TBLRUJAGENSI J, TBLRUJLUAS K, TBLHTPHAKMILIKAGENSI L"
					+ " WHERE A.ID_HAKMILIK = L.ID_HAKMILIK AND A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+)"
					+ " AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND L.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND L.ID_AGENSI = J.ID_AGENSI(+) AND L.ID_LUAS_BERSAMAAN = K.ID_LUAS"
					+ " AND L.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";
			myLog.info("sql=" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? ""
						: rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? ""
								: rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " + rs.getString("NO_HAKMILIK"));
				h.put("noLot", rs.getString("JENIS_LOT") == null || rs.getString("NO_LOT") == null ? ""
						: rs.getString("JENIS_LOT") + " " + rs.getString("NO_LOT"));
				h.put("luasLot",
						rs.getString("LUAS_BERSAMAAN") == null || rs.getString("JENIS_LUAS_BERSAMAAN") == null ? ""
								: Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN")) + " "
										+ rs.getString("JENIS_LUAS_BERSAMAAN"));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? "" : rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan", rs.getString("LUAS_BERSAMAAN") == null ? "" : rs.getString("LUAS_BERSAMAAN"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());
				String status = "";
				if (rs.getString("NO_HAKMILIK") == null)
					status = "RIZAB";
				else
					status = "MILIK";

				h.put("statusRizab", status);
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				beanMaklumatTanah.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("idLuas", "");
				h.put("luasBersamaan", "");
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
				beanMaklumatTanah.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//SHIQA daftarBaru
	public String daftarBaru(String idJenisTanah, String tarikhTerima, String tarikhSurat, String noRujukanSurat,
			String idKategori, String idKementerian, String idAgensi, String idLuasKegunaan, String txtTujuanKegunaan,
			String idHakmilikAgensi, String idPPTBorangK, String idHakmilikUrusan, String idPHPBorangK,
			String idKementerianTanah, String idNegeriTanah, String idLuasTanah, String luasTanah,
			String idHakmilikSementara, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idFailString = "";
		String noRujukanOnline = "";
		String noFail = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";
			// TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "6");
			r.add("ID_SUBURUSAN", "32");
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			// r.add("TAJUK_FAIL", perkara);
			// String kodUrusan = "879";
			/*
			 * noFail = generateNoFail(session, kodUrusan,
			 * getKodKementerian(idKementerianTanah), idKementerianTanah,
			 * getKodNegeri(idNegeriTanah), idNegeriTanah);
			 */
			// r.add("NO_FAIL", noFail);
			// r.add("NO_FAIL_ROOT", noFail);
			r.add("ID_LOKASIFAIL", "2"); // UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "4"); // DATA BARU ETAPP
			r.add("ID_NEGERI", idNegeriTanah);
			r.add("ID_KEMENTERIAN", idKementerianTanah);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			myLog.info("TBLPFDFAIL 1==" + sql);
			stmt.executeUpdate(sql);
			// TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", idKategori);
			r.add("ID_KEMENTERIAN", idKementerian);
			r.add("ID_AGENSI", idAgensi);

			sql = "SELECT * FROM TBLRUJAGENSI WHERE ID_AGENSI = '" + idAgensi + "'";
			myLog.info("TBLPHPPEMOHON 2==" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				r.add("NAMA", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				r.add("ALAMAT1_TETAP", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				r.add("ALAMAT2_TETAP", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				r.add("ALAMAT3_TETAP", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				r.add("POSKOD_TETAP", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase());
				r.add("ID_NEGERITETAP",
						rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);
			// TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS", "9920199");
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("NO_RUJ_SURAT", noRujukanSurat);
			r.add("FLAG_AKTIF", "Y");

			String kodUrusan = "879";
			Calendar currentDate = new GregorianCalendar();

			noRujukanOnline = "JKPTG/BPHP/04/" + kodUrusan + "/" + currentDate.get(Calendar.YEAR) + "/"
					+ File.getSeqNo(db, 4, 6, 0, 0, 0, false, false, currentDate.get(Calendar.YEAR), 0);

			r.add("NO_PERMOHONAN", noRujukanOnline);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			myLog.info("TBLPERMOHONAN 3==" + sql);
			stmt.executeUpdate(sql);
			// TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			long idhakmilikPermohonan = DB.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("ID_PPTBORANGK", idPPTBorangK);
			r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);
			r.add("ID_PHPBORANGK", idPHPBorangK);
			r.add("ID_HAKMILIKSEMENTARA", idHakmilikSementara);
			if ("3".equals(idJenisTanah)) {
				r.add("FLAG_BORANGK", "Y");
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			myLog.info("TBLPHPHAKMILIKPERMOHONAN 4==" + sql);
			stmt.executeUpdate(sql);
			// TBLPHPHAKMILIK
			String peganganHakmilik = "";
			if ("1".equals(idJenisTanah)) {
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
					r.add("TARIKH_WARTA", r.unquote("to_date('" + hashTanah.get("tarikhWarta") + "','dd/MM/yyyy')"));
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
					myLog.info("TBLPHPHAKMILIK 5==" + sql);
					stmt.executeUpdate(sql);

				}
			}
			// TBLPHPPERMOHONANPELEPASAN
			r = new SQLRenderer();
			long idPHPPermohonanPelepasan = DB.getNextID("TBLPHPPERMOHONANPELEPASAN_SEQ");
			r.add("ID_PHPPERMOHONANPELEPASAN", idPHPPermohonanPelepasan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_LUASASAL", idLuasTanah);
			r.add("LUAS_ASAL", luasTanah);
			r.add("FLAG_GUNA", idLuasKegunaan);
			if ("1".equals(idLuasKegunaan)) {
				r.add("LUAS_MHNBERSAMAAN", luasTanah);
				r.add("ID_UNITLUASBAKI", idLuasTanah);
				r.add("LUAS_BAKI", 0);
			}
			r.add("CADANGAN_KEGUNAAN", txtTujuanKegunaan);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPERMOHONANPELEPASAN");
			myLog.info("TBLPHPPERMOHONANPELEPASAN 6==" + sql);
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("32", "1610198")); // MAKLUMAT
																				// PERMOHONAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			myLog.info("TBLRUJSUBURUSANSTATUSFAIL 7==" + sql);
			stmt.executeUpdate(sql);
			// TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENISTANAH", "P"); // TANAH DIPOHON
			r.add("FLAG_LAPORAN", "1"); // LAWATAN TAPAK
			r.add("ID_HAKMILIK", idhakmilikPermohonan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPLAPORANTANAH");
			myLog.info("TBLPHPLAPORANTANAH 8==" + sql);
			stmt.executeUpdate(sql);
			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
		session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
	}

	/*
	 * public String daftarBaru(String userRole, String idKementerianPemohon, String
	 * idAgensiPemohon, String idHakmilikAgensi, HttpSession session) throws
	 * Exception { Db db = null; Connection conn = null; String userId = (String)
	 * session.getAttribute("_ekptg_user_id"); String sql = ""; String idFailString
	 * = ""; String idKementerian = ""; String idNegeriHakmilik = ""; String noFail
	 * = ""; String idLuas = ""; String luas = "";
	 * 
	 * try { db = new Db(); conn = db.getConnection(); conn.setAutoCommit(false);
	 * Statement stmt = db.getStatement(); SQLRenderer r = new SQLRenderer();
	 * 
	 * sql =
	 * "SELECT TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN, TBLHTPHAKMILIK.ID_NEGERI, TBLHTPHAKMILIKAGENSI.ID_LUAS_BERSAMAAN, TBLHTPHAKMILIKAGENSI.LUAS_BERSAMAAN"
	 * +
	 * " FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
	 * + " AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";
	 * 
	 * 
	 * ResultSet rsTanah = stmt.executeQuery(sql); if (rsTanah.next()){
	 * idKementerian = rsTanah.getString("ID_KEMENTERIAN"); idNegeriHakmilik =
	 * rsTanah.getString("ID_NEGERI"); idLuas =
	 * rsTanah.getString("ID_LUAS_BERSAMAAN"); luas =
	 * rsTanah.getString("LUAS_BERSAMAAN"); }
	 * 
	 * //TBLPFDFAIL long idFail = DB.getNextID("TBLPFDFAIL_SEQ"); idFailString =
	 * String.valueOf(idFail); r.add("ID_FAIL", idFail); r.add("ID_URUSAN", "6");
	 * r.add("ID_SUBURUSAN", "32"); r.add("ID_TARAFKESELAMATAN", "1");
	 * r.add("ID_SEKSYEN", "4"); r.add("FLAG_FAIL", "1"); r.add("FLAG_JENIS_FAIL",
	 * "4"); r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE")); r.add("ID_NEGERI",
	 * idNegeriHakmilik); r.add("ID_KEMENTERIAN", idKementerian);
	 * r.add("ID_LOKASIFAIL", "2"); //UNIT PHP DI TINGKAT 2 r.add("ID_MASUK",
	 * userId); r.add("TARIKH_MASUK", r.unquote("SYSDATE")); sql =
	 * r.getSQLInsert("TBLPFDFAIL"); stmt.executeUpdate(sql);
	 * 
	 * //TBLPHPPEMOHON r = new SQLRenderer(); long idPemohon =
	 * DB.getNextID("TBLPHPPEMOHON_SEQ"); r.add("ID_PEMOHON", idPemohon);
	 * r.add("ID_KEMENTERIAN", idKementerianPemohon); r.add("ID_AGENSI",
	 * idAgensiPemohon);
	 * 
	 * sql = "SELECT * FROM TBLRUJAGENSI WHERE ID_AGENSI = '" + idAgensiPemohon +
	 * "'"; ResultSet rs = stmt.executeQuery(sql);
	 * 
	 * if (rs.next()){ r.add("NAMA", rs.getString("NAMA_AGENSI") == null ? "" :
	 * rs.getString("NAMA_AGENSI").toUpperCase()); r.add("ALAMAT1_TETAP",
	 * rs.getString("ALAMAT1") == null ? "" :
	 * rs.getString("ALAMAT1").toUpperCase()); r.add("ALAMAT2_TETAP",
	 * rs.getString("ALAMAT2") == null ? "" :
	 * rs.getString("ALAMAT2").toUpperCase()); r.add("ALAMAT3_TETAP",
	 * rs.getString("ALAMAT3") == null ? "" :
	 * rs.getString("ALAMAT3").toUpperCase()); r.add("POSKOD_TETAP",
	 * rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase());
	 * r.add("ID_NEGERITETAP", rs.getString("ID_NEGERI") == null ? "" :
	 * rs.getString("ID_NEGERI").toUpperCase()); }
	 * 
	 * r.add("ID_KATEGORIPEMOHON", "3"); r.add("ID_MASUK", userId);
	 * r.add("TARIKH_MASUK", r.unquote("SYSDATE")); sql =
	 * r.getSQLInsert("TBLPHPPEMOHON"); stmt.executeUpdate(sql);
	 * 
	 * //TBLPERMOHONAN r = new SQLRenderer(); long idPermohonan =
	 * DB.getNextID("TBLPERMOHONAN_SEQ"); r.add("ID_PERMOHONAN", idPermohonan);
	 * r.add("ID_PEMOHON", idPemohon); r.add("ID_JKPTG", "1"); r.add("ID_FAIL",
	 * idFail); r.add("ID_STATUS", ""); r.add("FLAG_LAYER_KJP", "1");
	 * r.add("TARIKH_TERIMA", r.unquote("SYSDATE")); r.add("FLAG_AKTIF", "Y");
	 * 
	 * Calendar currentDate = new GregorianCalendar(); String noPermohonan =
	 * "JKPTG/SPHP/04/" + getKodUrusanByIdUrusan("6") + "/" +
	 * currentDate.get(Calendar.YEAR) + "/" + File.getSeqNo(db, 4,
	 * Integer.parseInt("6"), 0, 0, 0, false, false, currentDate.get(Calendar.YEAR),
	 * 0); r.add("NO_PERMOHONAN", noPermohonan); r.add("ID_MASUK", userId);
	 * r.add("TARIKH_MASUK", r.unquote("SYSDATE")); sql =
	 * r.getSQLInsert("TBLPERMOHONAN"); stmt.executeUpdate(sql);
	 * 
	 * //TBLPHPHAKMILIKPERMOHONAN r = new SQLRenderer(); long idhakmilikPermohonan =
	 * DB.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ"); r.add("ID_HAKMILIKPERMOHONAN",
	 * idhakmilikPermohonan); r.add("ID_PERMOHONAN", idPermohonan);
	 * r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
	 * 
	 * r.add("ID_MASUK", userId); r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
	 * 
	 * sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN"); stmt.executeUpdate(sql);
	 * 
	 * //TBLPHPPERMOHONANPENAWARAN r = new SQLRenderer(); long
	 * idPHPPermohonanPelepasan = DB.getNextID("TBLPHPPERMOHONANPELEPASAN_SEQ");
	 * r.add("ID_PHPPERMOHONANPELEPASAN", idPHPPermohonanPelepasan);
	 * r.add("ID_PERMOHONAN", idPermohonan); r.add("ID_LUASASAL", idLuas);
	 * r.add("LUAS_ASAL", luas); r.add("ID_MASUK", userId); r.add("TARIKH_MASUK",
	 * r.unquote("SYSDATE")); sql = r.getSQLInsert("TBLPHPPERMOHONANPELEPASAN");
	 * stmt.executeUpdate(sql);
	 * 
	 * conn.commit();
	 * 
	 * } catch (SQLException ex) { try { conn.rollback(); } catch (SQLException e) {
	 * throw new Exception("Rollback error : " + e.getMessage()); } throw new
	 * Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	 * 
	 * } finally { if (db != null) db.close(); } session.setAttribute("ID_FAIL",
	 * idFailString); return idFailString; }
	 */

	public String getidNegeriByIdHakmilik(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLHTPHAKMILIK.ID_NEGERI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("TBLHTPHAKMILIK.ID_NEGERI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getidKementerianByIdHakmilik(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN FROM TBLHTPHAKMILIK , TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKodUrusanByIdUrusan(String idUrusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_URUSAN FROM TBLRUJURUSAN WHERE ID_URUSAN = '" + idUrusan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_URUSAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdSuburusanstatus(String idSuburusan, String idStatus) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '" + idStatus
					+ "' AND ID_SUBURUSAN = '" + idSuburusan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_SUBURUSANSTATUS");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getUserRole(String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT USER_ROLE  FROM USERS WHERE USER_ID = '" + userId + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("USER_ROLE").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getUserJawatan(String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_JAWATAN FROM USERS_INTERNAL WHERE USER_ID = '" + userId + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_JAWATAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdNegeriByUserId(String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.ID_NEGERI FROM USERS A, USERS_ONLINE B" + " WHERE A.USER_ID = B.USER_ID AND A.USER_ID = '"
					+ userId + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_NEGERI").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	// shiqa 22072020
	// SENARAI SEMAK
	public Vector getSenaraiSemak(String idPermohonan, String kategori) throws Exception {

		Db db = null;
		String sql = "";
		Vector senaraiSemak = new Vector();
			
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_RUJSENARAISEMAK, A.KETERANGAN,"
					+ " CASE WHEN A.ID_RUJSENARAISEMAK IN (SELECT ID_RUJSENARAISEMAK FROM TBLPHPSENARAISEMAK WHERE ID_PERMOHONAN = '" + idPermohonan + "')"
					+ " THEN 'Y' END AS FLAG, "
					+ " CASE WHEN B.KETERANGAN = 'INDIVIDU' THEN '1' ELSE '2' END AS ID_KATEGORIPEMOHON "
					+ " FROM TBLPHPRUJSENARAISEMAK A, TBLRUJKATEGORIPEMOHON B "
					+ " WHERE A.ID_KATEGORIPEMOHON = B.ID_KATEGORIPEMOHON AND A.FLAG_AKTIF = 'Y' AND B.KETERANGAN = '" + kategori + "' ";
				
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiSemak", rs.getString("ID_RUJSENARAISEMAK") == null ? "" : rs.getString("ID_RUJSENARAISEMAK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				senaraiSemak.addElement(h);
			}

		} catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		return senaraiSemak;
	}
	
	public static void semakanTambah(String idsemakan, String idpermohonan) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	      String idPermohonan = idpermohonan;
	      String idSemakan = idsemakan;
	      int idKementerian = 1;
	      int idNegeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_semakanhantar", idSemakanhantar);
	      r.add("id_permohonan", idPermohonan);
	      r.add("id_semakansenarai", idSemakan);
	      sql = r.getSQLInsert("tblsemakanhantar");
	      myLog.info("semakanTambah : "+sql);
	      stmt.executeUpdate(sql);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	//UPDATE SENARAI SEMAK
	public void updateSenaraiSemak(String idPermohonan, String[] semaks, HttpSession session) throws Exception {
			
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Db db = new Db();
		String sql = "";		
			
		try {
			Connection conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r = new SQLRenderer();
				
			r.add("ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLDelete("TBLPHPSENARAISEMAK");
			stmt.executeUpdate(sql);
				
			for (int i = 0; i < semaks.length; i++) {
				r = new SQLRenderer();
				long ID_SENARAISEMAK = DB.getNextID("TBLPHPSENARAISEMAK_SEQ");
				r.add("ID_SENARAISEMAK", ID_SENARAISEMAK);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_RUJSENARAISEMAK", semaks[i]);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPHPSENARAISEMAK");
				stmt.executeUpdate(sql);
			}
			conn.commit();
				
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			myLog.info("listDetailKJP:: "+sql);

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

	public String getIdHakmilikAgensi(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.ID_HAKMILIKAGENSI FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

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

	public void setMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector<Hashtable<String, Object>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT D.USER_NAME, C.ID_KATEGORIPEMOHON, C.ID_AGENSI, C.ID_KEMENTERIAN, C.ID_PEJABAT FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON  C, USERS D"
					+ " WHERE C.ID_MASUK = D.USER_ID AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND A.ID_FAIL = '" + idFail + "'";
			myLog.info("beanMaklumatPemohon:: "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, Object> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, Object>();
				h.put("idKategoriPemohon", rs.getString("ID_KATEGORIPEMOHON") == null ? "99999"
						: rs.getString("ID_KATEGORIPEMOHON").toUpperCase());
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "99999" : rs.getString("ID_AGENSI").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "99999"
						: rs.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "99999" : rs.getString("ID_PEJABAT").toUpperCase());
				h.put("namaPemohon",
						rs.getString("USER_NAME") == null ? "99999" : rs.getString("USER_NAME").toUpperCase());
				beanMaklumatPemohon.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatHeader(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHeader = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;

			sql = "SELECT M.ID_KEMENTERIAN, A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL, B.ID_PERMOHONAN, L.ID_SUBURUSAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, C.ID_PEMOHON, C.NAMA, C.ID_NEGERITETAP, C.ID_KATEGORIPEMOHON, C.ID_PEJABAT, C.ID_AGENSI, I.ID_NEGERI AS ID_NEGERITANAH, H.ID_KEMENTERIAN AS ID_KEMENTERIANTANAH, H.ID_AGENSI AS ID_AGENSITANAH,"
					+ " C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, G.KETERANGAN AS NAMA_BANDAR, C.NO_TEL, C.NO_FAX, B.ID_STATUS, E.KETERANGAN, I.NO_HAKMILIK, I.NO_WARTA, H.ID_HAKMILIKAGENSI, H.ID_HAKMILIK, J.KEPUTUSAN, B.NO_PERMOHONAN, B.FLAG_LAYER_KJP, K.NAMA_AGENSI"
					+ ",K.ALAMAT1, K.ALAMAT2, K.ALAMAT3, K.POSKOD, D.NAMA_NEGERI, A.TAJUK_FAIL"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPHAKMILIKPERMOHONAN F, TBLRUJBANDAR G, TBLHTPHAKMILIKAGENSI H, TBLHTPHAKMILIK I, TBLPHPPERMOHONANPELEPASAN J, TBLRUJAGENSI K, TBLRUJSUBURUSAN L, TBLRUJKEMENTERIAN M"
					+ " WHERE A.ID_SUBURUSAN = '32' AND A.ID_SUBURUSAN = L.ID_SUBURUSAN AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND C.ID_NEGERITETAP = D.ID_NEGERI(+) AND B.ID_STATUS = E.ID_STATUS(+) AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND A.ID_KEMENTERIAN = M.ID_KEMENTERIAN "
					+ " AND F.ID_HAKMILIKAGENSI = H.ID_HAKMILIKAGENSI AND H.ID_HAKMILIK = I.ID_HAKMILIK AND C.ID_BANDARTETAP = G.ID_BANDAR(+) AND B.ID_PERMOHONAN = J.ID_PERMOHONAN AND H.ID_AGENSI = K.ID_AGENSI AND A.ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("beanMaklumatHeader==" + sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("namaAgensi",
						rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("namaKementerian", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN") == null ? "TIADA" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON").toUpperCase());
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
				h.put("idSubUrusan",
						rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());

				if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))
						|| "1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS"))) {
					h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				} else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null) {
					h.put("status", " PENDAFTARAN");
				} else {
					h.put("status", " SEDANG DIPROSES");
				}
				h.put("idHakmilik",
						rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("idNegeriPemohon",
						rs.getString("ID_NEGERITETAP") == null ? "" : rs.getString("ID_NEGERITETAP").toUpperCase());
				h.put("subUrusan", "PENAWARAN");
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs.getString("ID_HAKMILIKAGENSI"));
				// h.put("flagLayerKJP", rs.getString("FLAG_LAYER_KJP") == null ? "" :
				// rs.getString("FLAG_LAYER_KJP"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				beanMaklumatHeader.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idFail", "");
				h.put("noFail", "");
				h.put("namaKementerian", "");
				h.put("idKementerian", "");
				h.put("noPermohonan", "");
				h.put("idPermohonan", "");
				h.put("tarikhTerima", "");
				h.put("idPemohon", "");
				h.put("idStatus", "");
				h.put("idSubUrusan", "");
				h.put("status", "");
				h.put("idHakmilik", "");
				h.put("idNegeriPemohon", "");
				h.put("subUrusan", "");
				h.put("idHakmilikAgensi", "");
				// h.put("flagLayerKJP","");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("negeri", "");
				h.put("perkara", "");
				h.put("keterangan", "");
				beanMaklumatHeader.addElement(h);

				myLog.info("setMaklumatHeader==" + sql);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKategoriPemohonPenawaran() throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KATEGORIPEMOHON, KOD_KATEGORIPEMOHON, KETERANGAN FROM TBLRUJKATEGORIPEMOHON"
					+ " WHERE ID_KATEGORIPEMOHON IN (3) ORDER BY KOD_KATEGORIPEMOHON ASC";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPenawaran(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPenawaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANPELEPASAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, C.ID_LUASASAL, C.LUAS_ASAL,"
					+ " D.KETERANGAN, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_UNITLUASMHNBERSAMAAN, C.LUAS_MHNBERSAMAAN, C.ID_UNITLUASBAKI, C.LUAS_BAKI"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANPELEPASAN C, TBLRUJLUAS D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_LUASASAL = D.ID_LUAS(+) AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			myLog.info("setMaklumatPenawaran==" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanPelepasan", rs.getString("ID_PHPPERMOHONANPELEPASAN") == null ? ""
						: rs.getString("ID_PHPPERMOHONANPELEPASAN"));
				h.put("tarikhTerima",
						rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
				h.put("flagGuna", rs.getString("FLAG_GUNA") == null ? "" : rs.getString("FLAG_GUNA"));
				h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_ASAL")));
				h.put("keteranganLuasAsal", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idLuasMohon", rs.getString("ID_LUASMHN") == null ? "" : rs.getString("ID_LUASMHN"));
				h.put("luas1", rs.getString("LUAS_MHN1") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_MHN1")));
				h.put("luas2", rs.getString("LUAS_MHN2") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_MHN2")));
				h.put("luas3", rs.getString("LUAS_MHN3") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_MHN3")));
				h.put("luasBersamaan", rs.getString("LUAS_MHNBERSAMAAN") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_MHNBERSAMAAN")));
				h.put("luasBaki", rs.getString("LUAS_BAKI") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_BAKI")));
				beanMaklumatPenawaran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateTanah(String idPermohonan, String idHakmilikAgensi, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPHAKMILIKPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePermohonanPenawaran(String idPermohonanPelepasan, String idLuasKegunaan, String idLuas,
			String txtLuasMohon1, String txtLuasMohon2, String txtLuasMohon3, String txtLuasBersamaan,
			String txtBakiLuas, HttpSession session) throws Exception {

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

			// TBLPHPPERMOHONANPENAWARAN
			r.update("ID_PHPPERMOHONANPELEPASAN", idPermohonanPelepasan);
			r.add("FLAG_GUNA", idLuasKegunaan);
			r.add("ID_LUASMHN", idLuas);
			r.add("LUAS_MHN1", txtLuasMohon1);
			r.add("LUAS_MHN2", txtLuasMohon2);
			r.add("LUAS_MHN3", txtLuasMohon3);
			r.add("ID_UNITLUASMHNBERSAMAAN", "2");
			r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
			r.add("ID_UNITLUASBAKI", "2");
			r.add("LUAS_BAKI", txtBakiLuas);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkMaklumatPenawaranLengkap(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = true;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PHPPERMOHONANPELEPASAN, FLAG_GUNA FROM TBLPHPPERMOHONANPELEPASAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				// KESELURUHAN
				if ("1".equals(rs.getString("FLAG_GUNA"))) {
					bool = false;
				} else if ("2".equals(rs.getString("FLAG_GUNA"))) { // SEBAHAGIAN

					sql = "SELECT * FROM TBLPHPPERMOHONANPELEPASAN WHERE (LUAS_MHN IS NULL OR ID_LUASMHN IS NULL OR LUAS_MHN1 IS NULL)"
							+ " AND ID_PERMOHONAN= '" + idPermohonan + "'";
					ResultSet rs2 = stmt.executeQuery(sql);
					if (rs2.next()) {
						bool = false;
					} else {
						bool = true;
					}
				}
			}

		} finally {
			if (db != null)
				db.close();
		}

		return bool;
	}

	public void updatePermohonanSemakan(String idPermohonan, String idKementerianPmhn, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String emailTO = "";
		String noPermohonan = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_LAYER_KJP", "2");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();

			sql = "SELECT USERS_INTERNAL.EMEL FROM USERS, USERS_INTERNAL, USERS_KEMENTERIAN WHERE USERS.USER_ID = USERS_INTERNAL.USER_ID"
					+ " AND USERS.USER_ID = USERS_KEMENTERIAN.USER_ID AND USERS.USER_ROLE = 'online_kjp' AND USERS_INTERNAL.ID_JAWATAN = 9"
					+ " AND USERS_KEMENTERIAN.ID_KEMENTERIAN = '" + idKementerianPmhn + "'";

			ResultSet rsUsers = stmt.executeQuery(sql);
			if (rsUsers.next()) {
				emailTO = rsUsers.getString("EMEL");
			}

			sql = "SELECT NO_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rsPermohonan = stmt.executeQuery(sql);
			if (rsPermohonan.next()) {
				noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
			}

			// SEND EMEL
			if (!"".equals(emailTO)) {
				EmailOnline et = new EmailOnline();
				et.setEmail(emailTO, "hantarSemakanPenawaran", noPermohonan, "PENAWARAN");
			}

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePermohonanKelulusan(String idPermohonan, String idKementerianPmhn, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String emailTO = "";
		String noPermohonan = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_LAYER_KJP", "3");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();

			sql = "SELECT USERS_INTERNAL.EMEL FROM USERS, USERS_INTERNAL, USERS_KEMENTERIAN WHERE USERS.USER_ID = USERS_INTERNAL.USER_ID"
					+ " AND USERS.USER_ID = USERS_KEMENTERIAN.USER_ID AND USERS.USER_ROLE = 'online_kjp' AND USERS_INTERNAL.ID_JAWATAN = 4"
					+ " AND USERS_KEMENTERIAN.ID_KEMENTERIAN = '" + idKementerianPmhn + "'";

			ResultSet rsUsers = stmt.executeQuery(sql);
			if (rsUsers.next()) {
				emailTO = rsUsers.getString("EMEL");
			}

			sql = "SELECT NO_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rsPermohonan = stmt.executeQuery(sql);
			if (rsPermohonan.next()) {
				noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
			}

			// SEND EMEL
			if (!"".equals(emailTO)) {
				EmailOnline et = new EmailOnline();
				et.setEmail(emailTO, "hantarPengesahanPenawaran", noPermohonan, "PENAWARAN");
			}

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePermohonanEmel(String idFail, String idPermohonan, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String namaUser = "";
		String emelUser = "";
		String idhakmilikPermohonan = "";
		String noPermohonan = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT B.ID_HAKMILIKPERMOHONAN, A.NO_PERMOHONAN "
					+ " FROM TBLPERMOHONAN A,TBLPHPHAKMILIKPERMOHONAN B WHERE "
					+ " A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rsPermohonan = stmt.executeQuery(sql);
			if (rsPermohonan.next()) {
				idhakmilikPermohonan = rsPermohonan.getString("ID_HAKMILIKPERMOHONAN");
				noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
			}

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610197");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("32", "1610197")); // PERMOHONAN BARU
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENISTANAH", "P"); // TANAH DIPOHON
			r.add("FLAG_LAPORAN", "1"); // LAWATAN TAPAK
			r.add("ID_HAKMILIK", idhakmilikPermohonan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			// TODO EMAIL TO PEGAWAI INTERNAL
			// if (!"".equals(namaUser) && !"".equals(emelUser)){
			// EkptgEmailSender email = EkptgEmailSender.getInstance();
			// email.FROM = "etapp_webmaster@kptg.gov.my";
			// email.RECIEPIENT = emelUser;
			// email.SUBJECT = "PERMOHONAN PENAWARAN HARTA TANAH PERSEKUTUAN #" +
			// noPermohonan;
			// email.MESSAGE = namaUser.toUpperCase() + "."
			// + "<br><br>Permohonan anda telah diterima.Sila gunakan nombor permohonan
			// diatas sebagai rujukan."
			// + "Anda akan dimaklumkan setelah permohonan ini telah didaftarkan."
			// + "<br><br>Terima Kasih.";
			// email.sendEmail();
			// }

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void hapusPermohonan(String idFail) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPERMOHONANPELEPASAN
			sql = "DELETE FROM TBLPHPPERMOHONANPELEPASAN WHERE ID_PERMOHONAN IN"
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			myLog.info("padam TBLPHPPERMOHONANPELEPASAN: "+sql);
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIKPERMOHONAN
			sql = "DELETE FROM TBLPHPHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN IN"
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			myLog.info("padam TBLPHPHAKMILIKPERMOHONAN: "+sql);
			stmt.executeUpdate(sql);

			// TBLPHPPEMOHON
			sql = "DELETE FROM TBLPHPPEMOHON WHERE ID_PEMOHON IN "
					+ "(SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + ")))";
			myLog.info("padam TBLPHPPEMOHON: "+sql);
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			sql = "DELETE FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			myLog.info("padam TBLPERMOHONAN: "+sql);
			stmt.executeUpdate(sql);
			
			/*// TBLPHPHAKMILIK
			sql = "DELETE FROM TBLPHPHAKMILIK WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			myLog.info("padam TBLPHPHAKMILIK: " + sql);
			stmt.executeUpdate(sql);
			
			sql = "DELETE FROM TBLPHPHAKMILIK WHERE ID_FAIL IN (" + idFail + ")";
			myLog.info("padam TBLPHPHAKMILIK: "+sql);
			stmt.executeUpdate(sql);
			
			// TBLPHPLAPORANTANAH
			sql = "DELETE FROM TBLPHPLAPORANTANAH WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			myLog.info("padam TBLPHPLAPORANTANAH: " + sql);
			stmt.executeUpdate(sql);

			sql = "DELETE FROM TBLPHPLAPORANTANAH WHERE ID_FAIL IN (" + idFail + ")";
			myLog.info("padam TBLPHPLAPORANTANAH: "+sql);
			stmt.executeUpdate(sql);*/
			
			// TBLPFDFAIL
			sql = "DELETE FROM TBLPFDFAIL WHERE ID_FAIL IN (" + idFail + ")";
			myLog.info("padam TBLPFDFAIL: "+sql);
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatKeputusan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKeputusan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KEPUTUSAN, TARIKH_HANTARKEPUTUSAN FROM TBLPHPPERMOHONANPELEPASAN" + " WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhKeputusan", rs.getDate("TARIKH_HANTARKEPUTUSAN") == null ? ""
						: sdf.format(rs.getDate("TARIKH_HANTARKEPUTUSAN")));
				h.put("keputusan", rs.getString("KEPUTUSAN") == null ? "" : rs.getString("KEPUTUSAN"));
				beanMaklumatKeputusan.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiAgensi(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listAgensi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PENAWARANKJP, B.NAMA_KEMENTERIAN, C.NAMA_AGENSI, A.FLAG_LULUS, A.LUAS FROM TBLPHPPENAWARANKJP A, TBLRUJKEMENTERIAN B, TBLRUJAGENSI C"
					+ " WHERE A.ID_KEMENTERIAN = B.ID_KEMENTERIAN AND A.ID_AGENSI = C.ID_AGENSI AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_PENAWARANKJP";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPenawaranKJP", rs.getString("ID_PENAWARANKJP") == null ? "" : rs.getString("ID_PENAWARANKJP"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("flagLulus", rs.getString("FLAG_LULUS") == null ? "" : rs.getString("FLAG_LULUS"));
				h.put("luas", rs.getString("LUAS") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS")));
				listAgensi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Object getTotalLuas(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS FROM TBLPHPPENAWARANKJP WHERE ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("LUAS") != null) {
					if (rs.getDouble("LUAS") != 0D) {
						total = total + rs.getDouble("LUAS");
					}
				}
			}

		} finally {
			if (db != null)
				db.close();
		}
		return Utils.formatLuas(total);
	}

	public void setMaklumatImej(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, NAMA_FAIL, CATATAN FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaImej",
						rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanImej", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("namaFail", rs.getString("NAMA_FAIL") == null ? "" : rs.getString("NAMA_FAIL"));
				beanMaklumatImejan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiImejan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listImejan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, NAMA_FAIL FROM TBLPHPDOKUMEN" + " WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("namaFail", rs.getString("NAMA_FAIL") == null ? "" : rs.getString("NAMA_FAIL"));
				listImejan.addElement(h);
				bil++;
				count++;
			}
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
			throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniDokumen(String idDokumen, String txtNamaImej, String txtCatatan, HttpSession session)
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
			throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTanah(String idHakmilikAgensi, String idHakmilikSementara) throws Exception {
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
					+ " AND HMS.ID_HAKMILIKSEMENTARA = '" + idHakmilikSementara + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("idHakmilikSementara",
						rs.getString("ID_HAKMILIKSEMENTARA") == null ? "" : rs.getString("ID_HAKMILIKSEMENTARA"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? ""
						: rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? ""
								: rs.getString("KOD_JENIS_HAKMILIK").toUpperCase())
								+ " "
								+ (rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null
										? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("idLot", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("lot", (rs.getString("JENIS_LOT") == null ? "" : rs.getString("JENIS_LOT").toUpperCase()) + " "
						+ (rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT")));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? "" : rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan", rs.getString("LUAS_BERSAMAAN") == null ? "" : rs.getString("LUAS_BERSAMAAN"));
				h.put("luas",
						(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN")))
								+ " " + (rs.getString("JENIS_LUAS") == null ? "" : rs.getString("JENIS_LUAS")));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idMukim", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				if (rs.getString("NO_HAKMILIK") != null && rs.getString("NO_WARTA") == null) {
					h.put("statusRizab", "MILIK");
				} else if (rs.getString("NO_HAKMILIK") == null && rs.getString("NO_WARTA") != null) {
					h.put("statusRizab", "RIZAB");
				} else {
					h.put("statusRizab", "");
				}
				beanMaklumatTanah.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("idJenisHakmilik", "");
				h.put("noHakmilik", "");
				h.put("hakmilik", "");
				h.put("idLot", "");
				h.put("noLot", "");
				h.put("lot", "");
				h.put("idLuas", "");
				h.put("luasBersamaan", "");
				h.put("luas", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("idMukim", "");
				h.put("mukim", "");
				h.put("idDaerah", "");
				h.put("daerah", "");
				h.put("idNegeri", "");
				h.put("negeri", "");
				h.put("idKategori", "");
				h.put("kategoriTanah", "");
				h.put("idSubKategori", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kegunaanTanah", "");
				h.put("idKementerian", "");
				h.put("kementerian", "");
				h.put("idAgensi", "");
				h.put("agensi", "");
				h.put("statusRizab", "");
				beanMaklumatTanah.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String generateNoFail(HttpSession session, String kodUrusan, String kodKementerian, String idKementerian,
			String kodNegeri, String idNegeri) throws Exception {
		myLog.info("generateNoFail kodUrusan >>> " + kodUrusan);
		myLog.info("generateNoFail kodKementerian >>> " + kodKementerian);
		myLog.info("generateNoFail idKementerian >>> " + idKementerian);
		myLog.info("generateNoFail kodNegeri >>> " + kodNegeri);
		myLog.info("generateNoFail idNegeri >>> " + idNegeri);
		String noFail = "";
		noFail = "JKPTG/SPHP/" + kodUrusan + "/" + kodKementerian + "/" + kodNegeri + "-"
				+ File.getSeqNoP(session, 4, 6, Integer.parseInt(idKementerian), Integer.parseInt(idNegeri));

		myLog.info("generateNoFail noFail >>> " + noFail);
		return noFail;
	}

	public String getKodKementerian(String idKementerian) throws Exception {
		Db db = null;
		String sql = "";
		myLog.info("idKementerian >>> " + idKementerian);
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '" + idKementerian + "'";
			myLog.info("idKementerian  sql >>> " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKodNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		myLog.info("idnegeri >>> " + idNegeri);
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_MAMPU FROM TBLRUJNEGERI WHERE ID_NEGERI = '" + idNegeri + "'";
			myLog.info("idKementerian sql >>> " + idNegeri);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_MAMPU");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListAgensi() {
		return listAgensi;
	}

	public void setListAgensi(Vector listAgensi) {
		this.listAgensi = listAgensi;
	}

	public Vector getBeanMaklumatKeputusan() {
		return beanMaklumatKeputusan;
	}

	public void setBeanMaklumatKeputusan(Vector beanMaklumatKeputusan) {
		this.beanMaklumatKeputusan = beanMaklumatKeputusan;
	}

	public Vector getBeanMaklumatHeader() {
		return beanMaklumatHeader;
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatPenawaran() {
		return beanMaklumatPenawaran;
	}

	public void setBeanMaklumatPenawaran(Vector beanMaklumatPenawaran) {
		this.beanMaklumatPenawaran = beanMaklumatPenawaran;
	}

	public Vector getBeanMaklumatImejan() {
		return beanMaklumatImejan;
	}

	public void setBeanMaklumatImejan(Vector beanMaklumatImejan) {
		this.beanMaklumatImejan = beanMaklumatImejan;
	}

	public Vector getListImejan() {
		return listImejan;
	}

	public void setListImejan(Vector listImejan) {
		this.listImejan = listImejan;
	}

	public Vector<Hashtable<String, Object>> getBeanMaklumatAgensi() {
		return beanMaklumatAgensi;
	}

	public Vector<Hashtable<String, Object>> getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}
}
