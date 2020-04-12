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

import ekptg.helpers.Utils;

public class FrmPYWHeaderData {

	private Vector<Hashtable> beanMaklumatPermohonann = null;
	private Vector<Hashtable<String,String>> beanMaklumatPemohon = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatHakmilik = null;
	private static final Log log = LogFactory.getLog(FrmPYWHeaderData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatPermohonan(String idFail, String initiateFlagBuka,
			HttpSession session) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;
			SQLRenderer r = new SQLRenderer();

			if ("".equals(idFail) && session.getAttribute("ID_FAIL") != null) {
				idFail = (String) session.getAttribute("ID_FAIL");
			}
			if ("Y".equals(initiateFlagBuka)) {
				updateFlagBuka(idFail, session);
			}

			sql = "SELECT FAIL.ID_FAIL, FAIL.NO_FAIL, FAIL.NO_FAIL_NEGERI, FAIL.TAJUK_FAIL, MOHON.ID_PERMOHONAN, MOHON.TARIKH_TERIMA, MOHON.TARIKH_SURAT, MOHON.NO_RUJ_SURAT,"
					+ " PEMOHON.ID_PEMOHON, PEMOHON.ID_KATEGORIPEMOHON, PEMOHON.ID_PEJABAT, PEMOHON.NAMA, PEMOHON.NO_PENGENALAN, PEMOHON.ALAMAT1_TETAP, PEMOHON.ALAMAT2_TETAP, PEMOHON.ALAMAT3_TETAP, PEMOHON.POSKOD_TETAP,"
					+ " RUJBANDAR.KETERANGAN AS NAMA_BANDAR, RUJNEGERI.NAMA_NEGERI, PEMOHON.NO_TEL, PEMOHON.NO_FAX, PEMOHON.EMEL, MOHON.ID_STATUS, RUJSTATUS.KETERANGAN AS STATUS,"
					+ " MOHONSEWA.FLAG_PROSESFAIL, MOHON.NO_SAMBUNGAN, RUJURUSAN.NAMA_URUSAN, RUJSUBURUSAN.NAMA_SUBURUSAN, MOHONSEWA.FLAG_PERMOHONANDARI, FAIL.ID_URUSAN, FAIL.ID_SUBURUSAN,"
					+ " MOHONSEWA.KEPUTUSAN, MOHON.CATATAN_BATAL, MOHON.TARIKH_BATAL, MOHON.FLAG_AKTIF, FAIL.FLAG_JENIS_FAIL"
					+ ", MOHONSEWA.ID_PHPPERMOHONANSEWA, MOHONSEWA.FLAG_SEBAB_TAMAT, MOHONSEWA.FLAG_TEMPOHSEWA "

					+ " FROM TBLPFDFAIL FAIL, TBLPERMOHONAN MOHON, TBLRUJSTATUS RUJSTATUS, TBLPHPPEMOHON PEMOHON, TBLRUJNEGERI RUJNEGERI, TBLRUJBANDAR RUJBANDAR,"
					+ " TBLPHPPERMOHONANSEWA MOHONSEWA, TBLRUJURUSAN RUJURUSAN, TBLRUJSUBURUSAN RUJSUBURUSAN"

					+ " WHERE FAIL.ID_FAIL = MOHON.ID_FAIL(+) AND MOHON.ID_STATUS = RUJSTATUS.ID_STATUS(+) AND MOHON.ID_PEMOHON = PEMOHON.ID_PEMOHON(+)"
					+ " AND PEMOHON.ID_BANDARTETAP = RUJBANDAR.ID_BANDAR(+) AND PEMOHON.ID_NEGERITETAP = RUJNEGERI.ID_NEGERI(+)"
					+ " AND MOHON.ID_PERMOHONAN = MOHONSEWA.ID_PERMOHONAN"
					+ " AND FAIL.ID_URUSAN = RUJURUSAN.ID_URUSAN(+) AND FAIL.ID_SUBURUSAN = RUJSUBURUSAN.ID_SUBURUSAN(+)"
					+ " AND FAIL.ID_SEKSYEN = 4 AND FAIL.ID_URUSAN IN (7,12,13) AND MOHON.FLAG_PERJANJIAN = 'U'"
					+ " AND FAIL.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("noFailNegeri", rs.getString("NO_FAIL_NEGERI") == null ? "" : rs
						.getString("NO_FAIL_NEGERI").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujSurat", rs.getString("NO_RUJ_SURAT") == null ? ""
						: rs.getString("NO_RUJ_SURAT").toUpperCase());
				h.put("tajukFail", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs
						.getString("ID_PEMOHON"));
				h.put("idKategoriPemohon", rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
						.getString("ID_KATEGORIPEMOHON").toUpperCase());
				h.put("idUrusan",
						rs.getString("ID_URUSAN") == null ? "" : rs
								.getString("ID_URUSAN"));
				h.put("urusan",
						rs.getString("NAMA_URUSAN") == null ? "" : rs
								.getString("NAMA_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("subUrusan", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN"));
				h.put("noSambungan", rs.getString("NO_SAMBUNGAN") == null ? ""
						: rs.getString("NO_SAMBUNGAN"));

				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("noPengenalan",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP").toUpperCase());
				h.put("poskod", rs.getString("POSKOD_TETAP") == null ? "" : rs
						.getString("POSKOD_TETAP").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs
						.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs
						.getString("NO_TEL").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX").toUpperCase());
				h.put("emel", rs.getString("EMEL") == null ? "" : rs
						.getString("EMEL"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS").toUpperCase());
				
				h.put("idKeputusan", rs.getString("KEPUTUSAN") == null ? ""
						: rs.getString("KEPUTUSAN"));
				h.put("flagAktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));

				h.put("flagPermohonanDari",
						rs.getString("FLAG_PERMOHONANDARI") == null ? "" : rs
								.getString("FLAG_PERMOHONANDARI"));
				if (rs.getString("FLAG_PERMOHONANDARI") != null) {
					if ("0".equals(rs.getString("FLAG_PERMOHONANDARI"))) {
						h.put("permohonanDari", "KUALA LUMPUR");
					} else {
						h.put("permohonanDari", "LUAR KUALA LUMPUR");
					}
				} else {
					h.put("permohonanDari", "");
				}
				h.put("flagProsesFail",
						rs.getString("FLAG_PROSESFAIL") == null ? "" : rs
								.getString("FLAG_PROSESFAIL"));
				if (rs.getString("FLAG_PROSESFAIL") != null) {
					if ("L".equals(rs.getString("FLAG_PROSESFAIL"))) {
						h.put("prosesFail", "LAIN - LAIN");
					} else {
						h.put("prosesFail", "JKPTG");
					}
				} else {
					h.put("prosesFail", "");
				}

				h.put("tarikhBatal", rs.getDate("TARIKH_BATAL") == null ? ""
						: sdf.format(rs.getDate("TARIKH_BATAL")));
				h.put("catatanBatal",
						rs.getString("CATATAN_BATAL") == null ? "" : rs
								.getString("CATATAN_BATAL").toUpperCase());

				h.put("catatanMT", getCatatanMT(idFail));
				h.put("catatanPindaan", getCatatanPindaan(idFail));

				h.put("flagJenisFail",
						rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs
								.getString("FLAG_JENIS_FAIL").toUpperCase());
				h.put("idPermohonanSewa",
						rs.getString("ID_PHPPERMOHONANSEWA") == null ? "" : rs
								.getString("ID_PHPPERMOHONANSEWA").toUpperCase());
				h.put("flagSebabTamat",
						rs.getString("FLAG_SEBAB_TAMAT") == null ? "" : rs
								.getString("FLAG_SEBAB_TAMAT").toUpperCase());
				
				String status = rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase();
				if(rs.getString("FLAG_SEBAB_TAMAT")!=null && rs.getString("FLAG_SEBAB_TAMAT").length()>0){
					if(rs.getString("FLAG_SEBAB_TAMAT").equals("Y") || rs.getString("FLAG_SEBAB_TAMAT").equals("T")){
						status = status + " (Penamatan Penyewaan)";
					}
				}
				h.put("status", status);
				h.put("flagTempohsewa",
						rs.getString("FLAG_TEMPOHSEWA") == null ? "" : rs
								.getString("FLAG_TEMPOHSEWA").toUpperCase());
				
				beanMaklumatPermohonan.addElement(h);
				bil++;

				session.setAttribute("ID_FAIL", rs.getString("ID_FAIL"));

			} else {
				h = new Hashtable();
				h.put("idFail", "");
				h.put("idFail", "");
				h.put("noFail", "");
				h.put("idPermohonan", "");
				h.put("tarikhTerima", "");
				h.put("tarikhSurat", "");
				h.put("noRujSurat", "");
				h.put("tajukFail", "");
				h.put("idPemohon", "");
				h.put("idKategoriPemohon", "");
				h.put("idUrusan", "");
				h.put("urusan", "");
				h.put("idSuburusan", "");
				h.put("subUrusan", "");
				h.put("noSambungan", "");
				h.put("idKeputusan", "");
				h.put("flagAktif", "");

				h.put("namaPemohon", "");
				h.put("noPengenalan", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("negeri", "");
				h.put("bandar", "");
				h.put("noTel", "");
				h.put("noFax", "");
				h.put("idStatus", "");
				h.put("status", "");
				h.put("flagPermohonanDari", "");
				h.put("permohonanDari", "");
				h.put("flagProsesFail", "");
				h.put("prosesFail", "");

				h.put("tarikhBatal", "");
				h.put("catatanBatal", "");

				h.put("catatanMT", "");
				h.put("catatanPindaan", "");

				beanMaklumatPermohonan.addElement(h);
				session.removeAttribute("ID_FAIL");
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	private void updateFlagBuka(String idFail, HttpSession session)
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

			// TBLPERMOHONAN
			r.update("ID_FAIL", idFail);
			r.update("FLAG_BUKA", "T");
			r.update("FLAG_AKTIF", "Y");
			r.add("FLAG_BUKA", "Y");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
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

	public String getIdHakmilikPermohonanByIdFail(String idFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP"
					+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND MOHON.ID_FAIL = '"
					+ idFail + "' AND PHPHMP.FLAG_HAKMILIK = 'U'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKPERMOHONAN");
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdHTPHakmilikByIdHakmilikPermohonan(
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

	public String getCatatanMT(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT UPPER(CATATAN) AS CATATAN FROM TBLPHPLOGTUGASAN WHERE FLAG_AKTIF = 'Y' AND ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN");
			} else {
				return "TIADA";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getCatatanPindaan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT UPPER(CATATAN) AS CATATAN FROM TBLPHPLOGTUGASAN WHERE FLAG_AKTIF = 'Y' AND ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN");
			} else {
				return "TIADA";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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
			
			sql = " SELECT U.USER_ID, U.USER_NAME, UPPER(UO.KATEGORI) AS KATEGORI, UO.NO_KP_BARU, UO.ALAMAT1, UO.ALAMAT2, UO.ALAMAT3, " +
					  " UO.POSKOD, UO.NO_FAX, UO.NO_HP, UO.EMEL, RB.KETERANGAN AS NAMA_BANDAR, RN.NAMA_NEGERI FROM USERS U, " +
					  " USERS_ONLINE UO, TBLRUJNEGERI RN, TBLRUJBANDAR RB " +
					  " WHERE U.USER_ID = UO.USER_ID AND UO.ID_BANDAR = RB.ID_BANDAR AND UO.ID_NEGERI = RN.ID_NEGERI" +
					  " AND U.USER_ID = '" + idUser + "'";
			log.info("header:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
	
			if (rs.next()) {
				h = new Hashtable();
				h.put("iduser", rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("kategoriPemohon", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("namaPemohon", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
				h.put("noPengenalan", rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTel", rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX").toUpperCase());
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				//h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
	
				
				beanMaklumatPemohon.addElement(h);
				bil++;
			}
			//session.setAttribute("ID_FAIL", rs.getString("ID_FAIL"));
			
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	
		return beanMaklumatPemohon;
	}	
	
	public Vector<Hashtable> getMaklumatPermohonan(String idFail, HttpSession session) throws Exception {
		Db db = null;
		String sql = "";
	
		try {
			beanMaklumatPermohonann = new Vector<Hashtable> ();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT FAIL.ID_FAIL, FAIL.NO_FAIL, FAIL.NO_FAIL_NEGERI, FAIL.TAJUK_FAIL, MOHON.ID_PERMOHONAN, MOHON.TARIKH_TERIMA, MOHON.TARIKH_SURAT, MOHON.NO_RUJ_SURAT,"
					+ " PEMOHON.ID_PEMOHON, PEMOHON.ID_KATEGORIPEMOHON, PEMOHON.ID_PEJABAT, PEMOHON.NAMA, PEMOHON.NO_PENGENALAN, PEMOHON.ALAMAT1_TETAP, PEMOHON.ALAMAT2_TETAP, PEMOHON.ALAMAT3_TETAP, PEMOHON.POSKOD_TETAP,"
					+ " RUJBANDAR.KETERANGAN AS NAMA_BANDAR, RUJNEGERI.NAMA_NEGERI, PEMOHON.NO_TEL, PEMOHON.NO_FAX, PEMOHON.EMEL, MOHON.ID_STATUS, RUJSTATUS.KETERANGAN AS STATUS,"
					+ " MOHONSEWA.FLAG_PROSESFAIL, MOHON.NO_SAMBUNGAN, RUJURUSAN.NAMA_URUSAN, RUJSUBURUSAN.NAMA_SUBURUSAN, MOHONSEWA.FLAG_PERMOHONANDARI, FAIL.ID_URUSAN, FAIL.ID_SUBURUSAN,"
					+ " MOHONSEWA.KEPUTUSAN, MOHON.CATATAN_BATAL, MOHON.TARIKH_BATAL, MOHON.FLAG_AKTIF, FAIL.FLAG_JENIS_FAIL"
					+ ", MOHONSEWA.ID_PHPPERMOHONANSEWA, MOHONSEWA.FLAG_SEBAB_TAMAT, MOHONSEWA.FLAG_TEMPOHSEWA "

					+ " FROM TBLPFDFAIL FAIL, TBLPERMOHONAN MOHON, TBLRUJSTATUS RUJSTATUS, TBLPHPPEMOHON PEMOHON, TBLRUJNEGERI RUJNEGERI, TBLRUJBANDAR RUJBANDAR,"
					+ " TBLPHPPERMOHONANSEWA MOHONSEWA, TBLRUJURUSAN RUJURUSAN, TBLRUJSUBURUSAN RUJSUBURUSAN"

					+ " WHERE FAIL.ID_FAIL = MOHON.ID_FAIL(+) AND MOHON.ID_STATUS = RUJSTATUS.ID_STATUS(+) AND MOHON.ID_PEMOHON = PEMOHON.ID_PEMOHON(+)"
					+ " AND PEMOHON.ID_BANDARTETAP = RUJBANDAR.ID_BANDAR(+) AND PEMOHON.ID_NEGERITETAP = RUJNEGERI.ID_NEGERI(+)"
					+ " AND MOHON.ID_PERMOHONAN = MOHONSEWA.ID_PERMOHONAN"
					+ " AND FAIL.ID_URUSAN = RUJURUSAN.ID_URUSAN(+) AND FAIL.ID_SUBURUSAN = RUJSUBURUSAN.ID_SUBURUSAN(+)"
					+ " AND FAIL.ID_SEKSYEN = 4 AND FAIL.ID_URUSAN IN (7,12,13) AND MOHON.FLAG_PERJANJIAN = 'U'"
					+ " AND FAIL.ID_FAIL = '" + idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				h = new Hashtable();
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("noFailNegeri", rs.getString("NO_FAIL_NEGERI") == null ? "" : rs
						.getString("NO_FAIL_NEGERI").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujSurat", rs.getString("NO_RUJ_SURAT") == null ? ""
						: rs.getString("NO_RUJ_SURAT").toUpperCase());
				h.put("tajukFail", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("idUrusan",
						rs.getString("ID_URUSAN") == null ? "" : rs
								.getString("ID_URUSAN"));
				h.put("urusan",
						rs.getString("NAMA_URUSAN") == null ? "" : rs
								.getString("NAMA_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("subUrusan", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN"));
				h.put("noSambungan", rs.getString("NO_SAMBUNGAN") == null ? ""
						: rs.getString("NO_SAMBUNGAN"));
								h.put("flagAktif",
						rs.getString("FLAG_AKTIF") == null ? "" : rs
								.getString("FLAG_AKTIF"));
				h.put("tarikhBatal", rs.getDate("TARIKH_BATAL") == null ? ""
						: sdf.format(rs.getDate("TARIKH_BATAL")));
				h.put("catatanBatal",
						rs.getString("CATATAN_BATAL") == null ? "" : rs
								.getString("CATATAN_BATAL").toUpperCase());

				h.put("flagJenisFail",
						rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs
								.getString("FLAG_JENIS_FAIL").toUpperCase());
				h.put("idPermohonanSewa",
						rs.getString("ID_PHPPERMOHONANSEWA") == null ? "" : rs
								.getString("ID_PHPPERMOHONANSEWA").toUpperCase());
				h.put("flagSebabTamat",
						rs.getString("FLAG_SEBAB_TAMAT") == null ? "" : rs
								.getString("FLAG_SEBAB_TAMAT").toUpperCase());
				h.put("flagTempohsewa",
						rs.getString("FLAG_TEMPOHSEWA") == null ? "" : rs
								.getString("FLAG_TEMPOHSEWA").toUpperCase());
				
				beanMaklumatPermohonann.addElement(h);
				bil++;
				session.setAttribute("ID_FAIL", rs.getString("ID_FAIL"));
				
			}
			
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
			
		}
		return beanMaklumatPermohonann;
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
		
}