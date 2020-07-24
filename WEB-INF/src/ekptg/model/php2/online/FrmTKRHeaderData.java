package ekptg.model.php2.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.Utils;

public class FrmTKRHeaderData {

	private Vector beanMaklumatPermohonan = null;
	private Vector<Hashtable<String,String>> beanMaklumatPemohon = null;
	private Vector beanMaklumatHakmilik = null;
	private Vector beanMaklumatTanahGanti = null;
	private static final Log log = LogFactory.getLog(FrmTKRHeaderData.class);

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

			sql = "SELECT FAIL.ID_FAIL, FAIL.NO_FAIL, FAIL.TAJUK_FAIL, MOHON.ID_PERMOHONAN, MOHON.TARIKH_TERIMA, MOHON.TARIKH_SURAT, MOHON.NO_RAYUAN,"
					+ " PEMOHON.ID_PEMOHON, PEMOHON.ID_KATEGORIPEMOHON, PEMOHON.ID_PEJABAT, PEMOHON.ID_AGENSI, PEMOHON.NAMA, PEMOHON.ALAMAT1_TETAP, PEMOHON.ALAMAT2_TETAP, PEMOHON.ALAMAT3_TETAP, PEMOHON.POSKOD_TETAP,"
					+ " RUJBANDAR.KETERANGAN AS NAMA_BANDAR, RUJNEGERI.NAMA_NEGERI, PEMOHON.NO_TEL, PEMOHON.NO_FAX, MOHON.ID_STATUS, RUJSTATUS.KETERANGAN AS STATUS,"
					+ " MOHONPLP.KEPUTUSAN AS ID_KEPUTUSAN, MOHONPLP.FLAG_GUNA, MOHON.CATATAN_BATAL, MOHON.TARIKH_BATAL, FAIL.TARIKH_DAFTAR_FAIL"

					+ " FROM TBLPFDFAIL FAIL, TBLPERMOHONAN MOHON, TBLRUJSTATUS RUJSTATUS, TBLPHPPEMOHON PEMOHON, TBLRUJNEGERI RUJNEGERI, TBLRUJBANDAR RUJBANDAR,"
					+ " TBLPHPPERMOHONANPELEPASAN MOHONPLP"

					+ " WHERE FAIL.ID_FAIL = MOHON.ID_FAIL(+) AND MOHON.ID_STATUS = RUJSTATUS.ID_STATUS(+) AND MOHON.ID_PEMOHON = PEMOHON.ID_PEMOHON(+)"
					+ " AND MOHON.ID_PERMOHONAN = MOHONPLP.ID_PERMOHONAN AND PEMOHON.ID_BANDARTETAP = RUJBANDAR.ID_BANDAR(+) AND PEMOHON.ID_NEGERITETAP = RUJNEGERI.ID_NEGERI(+)"
					+ " AND FAIL.ID_SEKSYEN = 4 AND FAIL.ID_URUSAN = 6 AND FAIL.ID_SUBURUSAN = 33"
					+ " AND FAIL.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("subUrusan", "TUKARGUNA");
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tarikhBukaFail",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tajukFail", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs
						.getString("ID_PEMOHON").toUpperCase());
				h.put("idKategoriPemohon", rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
						.getString("ID_KATEGORIPEMOHON").toUpperCase());

				if ("3".equals(rs.getString("ID_KATEGORIPEMOHON"))) {

					Hashtable hashPejabat = getMaklumatAgensi(rs
							.getString("ID_AGENSI"));

					h.put("namaPemohon", hashPejabat.get("namaPemohon"));
					h.put("alamat1", hashPejabat.get("alamat1"));
					h.put("alamat2", hashPejabat.get("alamat2"));
					h.put("alamat3", hashPejabat.get("alamat3"));
					h.put("poskod", hashPejabat.get("poskod"));
					h.put("idNegeriPemohon", hashPejabat.get("idNegeriPemohon"));
					h.put("idKementerianPemohon", hashPejabat.get("idKementerian"));
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
				h.put("status", rs.getString("STATUS") == null ? "" : rs
						.getString("STATUS").toUpperCase());
				h.put("idKeputusan", rs.getString("ID_KEPUTUSAN") == null ? ""
						: rs.getString("ID_KEPUTUSAN").toUpperCase());
				h.put("flagGuna", rs.getString("FLAG_GUNA") == null ? "" : rs
						.getString("FLAG_GUNA").toUpperCase());
				h.put("noRayuan", rs.getString("NO_RAYUAN") == null ? "" : rs
						.getString("NO_RAYUAN").toUpperCase());

				h.put("tarikhBatal", rs.getDate("TARIKH_BATAL") == null ? ""
						: sdf.format(rs.getDate("TARIKH_BATAL")));
				h.put("catatanBatal",
						rs.getString("CATATAN_BATAL") == null ? "" : rs
								.getString("CATATAN_BATAL").toUpperCase());

				beanMaklumatPermohonan.addElement(h);
				bil++;

				session.setAttribute("ID_FAIL", rs.getString("ID_FAIL"));
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idFail", "");
				h.put("noFail", "");
				h.put("subUrusan", "");
				h.put("idPermohonan", "");
				h.put("tarikhTerima", "");
				h.put("tarikhSurat", "");
				h.put("tarikhBukaFail", "");
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
				h.put("idKeputusan", "");
				h.put("flagGuna", "");
				h.put("noRayuan", "");

				h.put("tarikhBatal", "");
				h.put("catatanBatal", "");

				beanMaklumatPermohonan.addElement(h);
				session.removeAttribute("ID_FAIL");
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Hashtable getMaklumatAgensi(String idAgensi) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_KEMENTERIAN,A.NAMA_AGENSI, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, A.ID_AGENSI"
					+ " FROM TBLRUJAGENSI A, TBLRUJNEGERI B WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_AGENSI = '"
					+ idAgensi + "'";

			ResultSet rsAgensi = stmt.executeQuery(sql);

			int bil = 1;
			if (rsAgensi.next()) {
				h = new Hashtable();
				h.put("namaPemohon",
						rsAgensi.getString("NAMA_AGENSI") == null ? ""
								: rsAgensi.getString("NAMA_AGENSI")
										.toUpperCase());
				h.put("alamat1", rsAgensi.getString("ALAMAT1") == null ? ""
						: rsAgensi.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rsAgensi.getString("ALAMAT2") == null ? ""
						: rsAgensi.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rsAgensi.getString("ALAMAT3") == null ? ""
						: rsAgensi.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rsAgensi.getString("POSKOD") == null ? ""
						: rsAgensi.getString("POSKOD").toUpperCase());
				h.put("idNegeriPemohon",
						rsAgensi.getString("ID_NEGERI") == null ? "" : rsAgensi
								.getString("ID_NEGERI"));
				h.put("idKementerian", rsAgensi.getString("ID_KEMENTERIAN") == null ? ""
						: rsAgensi.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("negeri", rsAgensi.getString("NAMA_NEGERI") == null ? ""
						: rsAgensi.getString("NAMA_NEGERI").toUpperCase());
				h.put("bandar", "");
				h.put("noTel", "");
				h.put("noFax", "");
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

	public String getIdHakmilikAgensiByIdFail(String idFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKAGENSI FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP"
					+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND MOHON.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKAGENSI");
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

	public String getIdHakmilikByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT MTA.ID_HAKMILIK FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM"
					+",TBLHTPHAKMILIKAGENSI MTA"
					+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN"
                    + " AND PHPHMP.ID_HAKMILIKAGENSI = MTA.ID_HAKMILIKAGENSI "
					+ " AND MOHON.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIK");
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

			sql = "SELECT HTPAGENSI.ID_HAKMILIK FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLHTPHAKMILIKAGENSI HTPAGENSI"
					+ " WHERE PHPHMP.ID_HAKMILIKAGENSI = HTPAGENSI.ID_HAKMILIKAGENSI AND PHPHMP.ID_HAKMILIKPERMOHONAN = '"
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

	public void setMaklumatTanahGanti(String idTanahGanti) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanahGanti = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TG.ID_TANAHGANTI, TG.PEGANGAN_HAKMILIK, TG.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, TG.NO_HAKMILIK,"
					+ " TG.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, TG.NO_LOT, TG.ID_LUAS AS ID_LUAS_BERSAMAAN, TG.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
					+ " TG.NO_WARTA, TG.TARIKH_WARTA, TG.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, TG.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, TG.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
					+ " TG.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, TG.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, TG.KEGUNAAN_TANAH, TG.SYARAT, TG.SEKATAN,"
					+ " TG.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"

					+ " FROM TBLPHPTANAHGANTIPLPSN TG, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE TG.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND TG.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND TG.ID_LUAS = RUJLUAS.ID_LUAS(+) AND TG.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND TG.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND TG.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND TG.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND TG.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND TG.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND TG.ID_TANAHGANTI = '" + idTanahGanti + "'";
			System.out.println("cek= " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idTanahGanti",
						rs.getString("ID_TANAHGANTI") == null ? "" : rs
								.getString("ID_TANAHGANTI").toUpperCase());

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
				beanMaklumatTanahGanti.addElement(h);

			} else {

				h = new Hashtable();
				h.put("idTanahGanti", "");

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
				beanMaklumatTanahGanti.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector<Hashtable<String,String>> setMaklumatPemohon(String idUser) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector<Hashtable<String,String>> ();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT U.USER_ID, U.USER_NAME, UO.NO_KP_BARU, UO.EMEL FROM USERS U, " +
					  " USERS_KEMENTERIAN UO" +
					  " WHERE U.USER_ID = UO.USER_ID" +
					  " AND U.USER_ID = '" + idUser + "'";
			log.info("header ros:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				h = new Hashtable();
				h.put("iduser", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("namaPemohon", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				h.put("noPengenalan", rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU").toUpperCase());
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));


				beanMaklumatPemohon.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}

		return beanMaklumatPemohon;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}

	public void setBeanMaklumatHakmilik(Vector beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}

	public Vector getBeanMaklumatTanahGanti() {
		return beanMaklumatTanahGanti;
	}

	public void setBeanMaklumatTanahGanti(Vector beanMaklumatTanahGanti) {
		this.beanMaklumatTanahGanti = beanMaklumatTanahGanti;
	}
}
