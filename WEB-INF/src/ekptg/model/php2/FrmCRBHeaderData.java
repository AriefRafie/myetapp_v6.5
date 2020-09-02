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
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmCRBHeaderData {

	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatBorangK = null;
	private Vector beanMaklumatHakmilik = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatPermohonan(String idFail, HttpSession session)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;

			if ("".equals(idFail) && session.getAttribute("ID_FAIL") != null) {
				idFail = (String) session.getAttribute("ID_FAIL");
			}

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.NO_FAIL_NEGERI, A.TAJUK_FAIL, B.ID_PERMOHONAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, C.ID_PEMOHON, C.NAMA, C.ID_NEGERITETAP, C.ID_KATEGORIPEMOHON, C.ID_PEJABAT, I.ID_NEGERI AS ID_NEGERITANAH, I.ID_KEMENTERIAN AS ID_KEMENTERIANTANAH, I.ID_AGENSI AS ID_AGENSITANAH,"
					+ " C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, G.KETERANGAN AS NAMA_BANDAR, C.NO_TEL, C.NO_FAX, B.ID_STATUS, E.KETERANGAN, I.NO_HAKMILIK, I.NO_WARTA, I.ID_HAKMILIK, F.ID_HAKMILIKPERMOHONAN, F.ID_HAKMILIKAGENSI,"
					+ " CASE WHEN F.ID_BORANGK IS NOT NULL THEN 'Y' ELSE 'T' END AS FLAG_BORANGK, NULL AS ID_BORANGK"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPHAKMILIKPERMOHONAN F, TBLRUJBANDAR G, TBLPHPHAKMILIK I, TBLPHPPERMOHONANKUATKUASA J"
					+ " WHERE A.ID_SEKSYEN = 4 AND A.ID_SUBURUSAN = '56'  AND A.ID_URUSAN = '8' AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND C.ID_NEGERITETAP = D.ID_NEGERI(+) AND B.ID_STATUS = E.ID_STATUS AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND F.ID_HAKMILIKPERMOHONAN = I.ID_HAKMILIKPERMOHONAN(+)  AND C.ID_BANDARTETAP = G.ID_BANDAR(+) AND B.ID_PERMOHONAN = J.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("noFailNegeri", rs.getString("NO_FAIL_NEGERI") == null ? "" : rs
						.getString("NO_FAIL_NEGERI").toUpperCase());
				h.put("urusan", "PENGUATKUASAAAN");
				h.put("subUrusan", "PENGUATKUASAAAN");
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tajukFail", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs
						.getString("ID_PEMOHON").toUpperCase());

				if ("4".equals(rs.getString("ID_KATEGORIPEMOHON"))
						|| "5".equals(rs.getString("ID_KATEGORIPEMOHON"))) {

					Hashtable hashPejabat = getMaklumatPejabat(rs
							.getString("ID_PEJABAT"));

					h.put("namaPemohon", hashPejabat.get("namaPemohon"));
					h.put("alamat1", hashPejabat.get("alamat1"));
					h.put("alamat2", hashPejabat.get("alamat2"));
					h.put("alamat3", hashPejabat.get("alamat3"));
					h.put("poskod", hashPejabat.get("poskod"));
					h.put("idNegeriPemohon", hashPejabat.get("idNegeriPemohon"));
					h.put("negeri", hashPejabat.get("negeri"));
					h.put("bandar", hashPejabat.get("bandar"));
					h.put("noTel", hashPejabat.get("noTel"));
					h.put("noFax", hashPejabat.get("noFax"));

				} else if ("8".equals(rs.getString("ID_KATEGORIPEMOHON"))) {

					Hashtable hashPejabat = getMaklumatPejabatJKPTG(rs
							.getString("ID_PEJABAT"));

					h.put("namaPemohon", hashPejabat.get("namaPemohon"));
					h.put("alamat1", hashPejabat.get("alamat1"));
					h.put("alamat2", hashPejabat.get("alamat2"));
					h.put("alamat3", hashPejabat.get("alamat3"));
					h.put("poskod", hashPejabat.get("poskod"));
					h.put("idNegeriPemohon", hashPejabat.get("idNegeriPemohon"));
					h.put("negeri", hashPejabat.get("negeri"));
					h.put("bandar", hashPejabat.get("bandar"));
					h.put("noTel", hashPejabat.get("noTel"));
					h.put("noFax", hashPejabat.get("noFax"));

				} else {

					h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
							.getString("NAMA").toUpperCase());
					h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
							: rs.getString("ALAMAT1_TETAP").toUpperCase());
					h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
							: rs.getString("ALAMAT2_TETAP").toUpperCase());
					h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
							: rs.getString("ALAMAT3_TETAP").toUpperCase());
					h.put("poskod", rs.getString("POSKOD_TETAP") == null ? ""
							: rs.getString("POSKOD_TETAP").toUpperCase());
					h.put("idNegeriPemohon",
							rs.getString("ID_NEGERITETAP") == null ? "" : rs
									.getString("ID_NEGERITETAP"));
					h.put("negeri", rs.getString("NAMA_NEGERI") == null ? ""
							: rs.getString("NAMA_NEGERI").toUpperCase());
					h.put("bandar", rs.getString("NAMA_BANDAR") == null ? ""
							: rs.getString("NAMA_BANDAR").toUpperCase());
					h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs
							.getString("NO_TEL").toUpperCase());
					h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs
							.getString("NO_FAX").toUpperCase());
				}
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS").toUpperCase());
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs
						.getString("KETERANGAN").toUpperCase());
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("idHakmilikPermohonan",
						rs.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs
								.getString("ID_HAKMILIKPERMOHONAN"));
				h.put("idNegeriTanah",
						rs.getString("ID_NEGERITANAH") == null ? "" : rs
								.getString("ID_NEGERITANAH"));
				h.put("idKementerianTanah",
						rs.getString("ID_KEMENTERIANTANAH") == null ? "" : rs
								.getString("ID_KEMENTERIANTANAH"));
				h.put("idAgensiTanah",
						rs.getString("ID_AGENSITANAH") == null ? "" : rs
								.getString("ID_AGENSITANAH"));
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				if (rs.getString("NO_HAKMILIK") != null
						&& rs.getString("NO_WARTA") == null) {
					h.put("statusRizab", "MILIK");
				} else if (rs.getString("NO_HAKMILIK") == null
						&& rs.getString("NO_WARTA") != null) {
					h.put("statusRizab", "RIZAB");
				} else {
					h.put("statusRizab", "");
				}
				h.put("flagBorangK", rs.getString("FLAG_BORANGK") == null ? ""
						: rs.getString("FLAG_BORANGK"));
				h.put("idBorangK",
						rs.getString("ID_BORANGK") == null ? "" : rs
								.getString("ID_BORANGK"));

				beanMaklumatPermohonan.addElement(h);
				bil++;

				session.setAttribute("ID_FAIL", rs.getString("ID_FAIL"));
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idFail", "");
				h.put("noFail", "");
				h.put("noFailNegeri", "");
				h.put("subUrusan", "");
				h.put("idPermohonan", "");
				h.put("tarikhTerima", "");
				h.put("tarikhSurat", "");
				h.put("tajukFail", "");
				h.put("idPemohon", "");
				h.put("namaPemohon", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("idNegeriPemohon", "");
				h.put("negeri", "");
				h.put("daerah", "");
				h.put("noTel", "");
				h.put("noFax", "");
				h.put("idStatus", "");
				h.put("status", "");
				h.put("idHakmilik", "");
				h.put("idHakmilikPermohonan", "");
				h.put("idNegeriTanah", "");
				h.put("idKementerianTanah", "");
				h.put("idAgensiTanah", "");
				h.put("idHakmilikAgensi", "");
				h.put("statusRizab", "");
				h.put("flagBorangK", "");
				h.put("idBorangK", "");
				beanMaklumatPermohonan.addElement(h);
				session.removeAttribute("ID_FAIL");
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Hashtable getMaklumatPejabat(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX"
					+ " FROM TBLRUJPEJABAT A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABAT = '"
					+ idPejabat + "'";

			ResultSet rsPejabat = stmt.executeQuery(sql);

			int bil = 1;
			if (rsPejabat.next()) {
				h = new Hashtable();
				h.put("namaPemohon",
						rsPejabat.getString("NAMA_PEJABAT") == null ? ""
								: rsPejabat.getString("NAMA_PEJABAT")
										.toUpperCase());
				h.put("alamat1", rsPejabat.getString("ALAMAT1") == null ? ""
						: rsPejabat.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rsPejabat.getString("ALAMAT2") == null ? ""
						: rsPejabat.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rsPejabat.getString("ALAMAT3") == null ? ""
						: rsPejabat.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rsPejabat.getString("POSKOD") == null ? ""
						: rsPejabat.getString("POSKOD").toUpperCase());
				h.put("idNegeriPemohon",
						rsPejabat.getString("ID_NEGERI") == null ? ""
								: rsPejabat.getString("ID_NEGERI"));
				h.put("negeri", rsPejabat.getString("NAMA_NEGERI") == null ? ""
						: rsPejabat.getString("NAMA_NEGERI").toUpperCase());
				h.put("bandar", rsPejabat.getString("NAMA_BANDAR") == null ? ""
						: rsPejabat.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTel", rsPejabat.getString("NO_TEL") == null ? ""
						: rsPejabat.getString("NO_TEL").toUpperCase());
				h.put("noFax", rsPejabat.getString("NO_FAX") == null ? ""
						: rsPejabat.getString("NO_FAX").toUpperCase());
			}

		} finally {
			if (db != null)
				db.close();
		}
		return h;
	}

	public Hashtable getMaklumatPejabatJKPTG(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX"
					+ " FROM TBLRUJPEJABATJKPTG A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABATJKPTG = '"
					+ idPejabat + "'";

			ResultSet rsPejabat = stmt.executeQuery(sql);

			int bil = 1;
			if (rsPejabat.next()) {
				h = new Hashtable();
				h.put("namaPemohon",
						rsPejabat.getString("NAMA_PEJABAT") == null ? ""
								: rsPejabat.getString("NAMA_PEJABAT")
										.toUpperCase());
				h.put("alamat1", rsPejabat.getString("ALAMAT1") == null ? ""
						: rsPejabat.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rsPejabat.getString("ALAMAT2") == null ? ""
						: rsPejabat.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rsPejabat.getString("ALAMAT3") == null ? ""
						: rsPejabat.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rsPejabat.getString("POSKOD") == null ? ""
						: rsPejabat.getString("POSKOD").toUpperCase());
				h.put("idNegeriPemohon",
						rsPejabat.getString("ID_NEGERI") == null ? ""
								: rsPejabat.getString("ID_NEGERI"));
				h.put("negeri", rsPejabat.getString("NAMA_NEGERI") == null ? ""
						: rsPejabat.getString("NAMA_NEGERI").toUpperCase());
				h.put("bandar", rsPejabat.getString("NAMA_BANDAR") == null ? ""
						: rsPejabat.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTel", rsPejabat.getString("NO_TEL") == null ? ""
						: rsPejabat.getString("NO_TEL").toUpperCase());
				h.put("noFax", rsPejabat.getString("NO_FAX") == null ? ""
						: rsPejabat.getString("NO_FAX").toUpperCase());
			}

		} finally {
			if (db != null)
				db.close();
		}
		return h;
	}

	public void setMaklumatTanah(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK, B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.NO_WARTA, A.KEGUNAAN_TANAH, "
					+ " A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI, G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KETERANGAN AS JENIS_LUAS, K.KOD_LUAS AS KOD_LUAS, L.LUAS_BERSAMAAN"
					+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I, TBLRUJAGENSI J, TBLRUJLUAS K, TBLHTPHAKMILIKAGENSI L"
					+ " WHERE A.ID_HAKMILIK = L.ID_HAKMILIK AND A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+)"
					+ " AND A.ID_KATEGORI = H.ID_KATEGORI(+) AND L.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND L.ID_AGENSI = J.ID_AGENSI(+) AND L.ID_LUAS_BERSAMAAN = K.ID_LUAS AND L.ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
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
								.getString("JENIS_LOT").toUpperCase()
								+ " "
								+ rs.getString("NO_LOT"));
				h.put("luasLot",
						rs.getString("LUAS_BERSAMAAN") == null
								|| rs.getString("JENIS_LUAS") == null ? ""
								: Utils.formatLuas(rs
										.getDouble("LUAS_BERSAMAAN"))
										+ " "
										+ rs.getString("JENIS_LUAS"));
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
				beanMaklumatTanah.addElement(h);
				bil++;
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

			if (bil == 1) {
				h = new Hashtable();
				h.put("idPHPBorangK", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", "");
				h.put("daerah", "");
				h.put("negeri", "");
				h.put("kementerian", "");
				h.put("agensi", "");
				h.put("kategoriTanah", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kegunaanTanah", "");
				h.put("tarikhBorangK", "");
				h.put("catatan", "");
				h.put("noPerserahan", "");
				h.put("tarikhCatatan", "");
				h.put("tarikhTerima", "");
				beanMaklumatBorangK.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikPermohonanByIdFail(String idFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP"
					+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND MOHON.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKPERMOHONAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatHakmilik(String idHakmilikPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN, PHPHM.ID_HAKMILIK, PHPHM.PEGANGAN_HAKMILIK, PHPHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHM.NO_HAKMILIK,"
					+ " PHPHM.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHM.NO_LOT, PHPHM.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHM.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
					+ " PHPHM.NO_WARTA, PHPHM.TARIKH_WARTA, PHPHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PHPHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
					+ " PHPHM.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, PHPHM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHM.KEGUNAAN_TANAH, PHPHM.SYARAT, PHPHM.SEKATAN,"
					+ " PHPHM.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " PHPHM.TARIKH_BORANGK, PHPHM.CATATAN, PHPHM.NO_PERSERAHAN, PHPHM.TARIKH_CATATAN, PHPHM.TARIKH_TERIMA,"
					+ " PHPHMP.FLAG_BORANGK"

					+ " FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN AND PHPHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND PHPHM.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND PHPHM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHM.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND PHPHMP.ID_HAKMILIKPERMOHONAN = '"
					+ idHakmilikPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikPermohonan", rs
						.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs
						.getString("ID_HAKMILIKPERMOHONAN").toUpperCase());
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());

				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase())
								+ " "
								+ (rs.getString("NO_HAKMILIK") == null ? ""
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
				h.put("flagBorangK", rs.getString("FLAG_BORANGK") == null ? ""
						: rs.getString("FLAG_BORANGK").toUpperCase());
				beanMaklumatHakmilik.addElement(h);

			} else {

				h = new Hashtable();
				h.put("idHakmilikPermohonan", "");
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
				h.put("tarikhBorangK", "");
				h.put("catatan", "");
				h.put("noPerserahan", "");
				h.put("tarikhCatatan", "");
				h.put("tarikhTerima", "");
				h.put("flagBorangK", "");
				beanMaklumatHakmilik.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikByIdHakmilikPermohonan(
			String idHakmilikPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHAKMILIK.ID_HAKMILIK FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHAKMILIK"
					+ " WHERE PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHAKMILIK.ID_HAKMILIKPERMOHONAN AND PHPHMP.ID_HAKMILIKPERMOHONAN = '"
					+ idHakmilikPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIK");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean getFlagOpenDetail(String idStatus, int langkahSkrin)
			throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = false;
		int langkahStatus = 0;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT SS.LANGKAH FROM TBLRUJSTATUS S, TBLRUJSUBURUSANSTATUS SS WHERE S.ID_STATUS = SS.ID_STATUS AND SS.ID_SUBURUSAN = 56 AND S.ID_STATUS = '"
					+ idStatus + "' ORDER BY SS.LANGKAH ASC";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				langkahStatus = Integer.parseInt(rs.getString("LANGKAH"));
			}

			if (langkahSkrin <= langkahStatus)
				bool = true;

		} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}
	
	public void doSelesaiPermohonan(String idFail, String idPermohonan,
			String idStatusSebelum, String tarikhSelesai, String txtSebab,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idSuburusan = "56";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610218");
			r.add("ID_STATUS_SEBELUM", idStatusSebelum);
			r.add("TARIKH_SELESAI",
					r.unquote("to_date('" + tarikhSelesai + "','dd/MM/yyyy')"));
			r.add("CATATAN_SELESAI", txtSebab);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");

			r.add("AKTIF", "0");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS",
					getIdSuburusanstatus(idSuburusan, "1610218")); // SELESAI
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610218", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] SELESAI");

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
	
	public void doBatalPermohonan(String idFail, String idPermohonan,
			String idStatusSebelum, String tarikhBatal, String txtSebab,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idSuburusan = "56";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610212");
			r.add("ID_STATUS_SEBELUM", idStatusSebelum);
			r.add("TARIKH_BATAL",
					r.unquote("to_date('" + tarikhBatal + "','dd/MM/yyyy')"));
			r.add("CATATAN_BATAL", txtSebab);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");

			r.add("AKTIF", "0");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS",
					getIdSuburusanstatus(idSuburusan, "1610212")); // BATAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610212", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIBATALKAN");

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
	
	public String getIdSuburusanstatus(String idSuburusan, String idStatus)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '"
					+ idStatus + "' AND ID_SUBURUSAN = '" + idSuburusan + "'";

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
	
	public String getNoFailByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPFDFAIL.NO_FAIL FROM TBLPFDFAIL, TBLPERMOHONAN"
					+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL"
					+ " AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("NO_FAIL");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}

	public Vector getBeanMaklumatBorangK() {
		return beanMaklumatBorangK;
	}

	public void setBeanMaklumatBorangK(Vector beanMaklumatBorangK) {
		this.beanMaklumatBorangK = beanMaklumatBorangK;
	}

	public Vector getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}

	public void setBeanMaklumatHakmilik(Vector beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}
}
