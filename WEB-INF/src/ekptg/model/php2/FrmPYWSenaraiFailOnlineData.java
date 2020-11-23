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
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.intergration.XEkptgEmailSender;

public class FrmPYWSenaraiFailOnlineData {

	private Vector senaraiFail = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatPemohon = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianFail(String noPermohonan, String tarikhPermohonan,
			String namaPemohon, String noPengenalan, String idNegeri) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA, B.NO_PERMOHONAN, C.ID_PEMOHON, A.ID_SUBURUSAN,"
				+ " A.ID_URUSAN, D.NAMA_URUSAN ,  E.NAMA_SUBURUSAN, E.ID_SUBURUSAN "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C,  TBLRUJURUSAN D, TBLRUJSUBURUSAN E, TBLPHPHAKMILIKPERMOHONAN F, TBLPHPHAKMILIK G, TBLPHPHAKMILIKSEMENTARA H"
				+ " WHERE A.ID_URUSAN IN (7,12,13) AND B.FLAG_PERJANJIAN = 'U' AND B.ID_PERMOHONAN = F.ID_PERMOHONAN AND F.ID_HAKMILIKPERMOHONAN = G.ID_HAKMILIKPERMOHONAN(+)"
				+ " AND F.ID_HAKMILIKSEMENTARA = H.ID_HAKMILIKSEMENTARA(+) AND A.FLAG_JENIS_FAIL = '4' AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON"
				+ " AND A.NO_FAIL IS NULL AND B.NO_PERMOHONAN IS NOT NULL"
				+ " AND A.ID_SEKSYEN = '4' AND A.ID_URUSAN = D.ID_URUSAN AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND B.ID_STATUS IS NOT NULL";

			// noPermohonan
			if (noPermohonan != null) {
				if (!noPermohonan.trim().equals("")) {
					sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%' ||'"
							+ noPermohonan.trim().toUpperCase() + "'|| '%'";
				}
			}

			// namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noPengenalan
			if (noPengenalan != null) {
				if (!noPengenalan.trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_PENGENALAN) LIKE '%' ||'"
							+ noPengenalan.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			// tarikhPermohonan
			if (tarikhPermohonan != null) {
				if (!tarikhPermohonan.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhPermohonan))
									.toUpperCase() + "'";
				}
			}
			
			//IdNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")
						&& !idNegeri.trim().equals("99999")) {
					sql = sql + " AND ((G.ID_NEGERI = '" + idNegeri.trim() + "')"
							  + " OR (H.ID_NEGERI = '" + idNegeri.trim() + "'))";
				}
			}
			
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN") == null ? "" : rs
								.getString("NO_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("tarikhPermohonan",
						rs.getDate("TARIKH_TERIMA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("idPemohon",
						rs.getString("ID_PEMOHON") == null ? "" : rs
								.getString("ID_PEMOHON"));
				h.put("namaUrusan", rs.getString("NAMA_URUSAN") == null ? ""
						: rs.getString("NAMA_URUSAN"));
				h.put("idUrusan",
						rs.getString("ID_URUSAN") == null ? "" : rs
								.getString("ID_URUSAN"));
				h.put("subUrusan", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN"));
				h.put("idSubUrusan", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
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

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN,B.TARIKH_TERIMA, A.TAJUK_FAIL, B.ID_PEMOHON, B.NO_PERMOHONAN, A.ID_URUSAN, A.ID_SUBURUSAN," 
					+ " A.ID_SUBSUBURUSAN, C.CATATAN, C.TUJUAN, A.NO_FAIL_NEGERI"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANSEWA C WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("noFailNegeri", rs.getString("NO_FAIL_NEGERI") == null ? "" : rs
						.getString("NO_FAIL_NEGERI").toUpperCase());
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN") == null ? "" : rs
								.getString("NO_PERMOHONAN").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs
						.getString("ID_PEMOHON").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("idUrusan",
						rs.getString("ID_URUSAN") == null ? "" : rs
								.getString("ID_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("idSubsuburusan", rs.getString("ID_SUBSUBURUSAN") == null ? "" 
						: rs.getString("ID_SUBSUBURUSAN"));
				h.put("namaTujuan", rs.getString("TUJUAN") == null ? "" : rs
						.getString("TUJUAN").toUpperCase());
				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateDaftarOnline(String idUrusan, String idFail,
			String idPermohonan, String txtNoFailNegeri, String txtPerkara,
			String txtCatatan, String idHakmilikSementara, String idSuburusan, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String idNegeriUser = (String) session
				.getAttribute("_ekptg_user_negeri");
		String userRole = (String) session.getAttribute("myrole");
		String sql = "";
		String idKementerian = "1";
		String idNegeriHakmilik = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT TBLPHPHAKMILIKSEMENTARA.ID_KEMENTERIAN, TBLPHPHAKMILIKSEMENTARA.ID_NEGERI, "
					+ "TBLPHPHAKMILIKSEMENTARA.ID_LUAS, TBLPHPHAKMILIKSEMENTARA.LUAS, TBLPHPHAKMILIKSEMENTARA.ID_HAKMILIKSEMENTARA "
					+ "FROM TBLPHPHAKMILIKSEMENTARA, TBLPHPHAKMILIKPERMOHONAN "
					+ "WHERE TBLPHPHAKMILIKSEMENTARA.ID_HAKMILIKSEMENTARA = TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKSEMENTARA "
					+ "AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKSEMENTARA = '"
					+ idHakmilikSementara + "'";

			ResultSet rsTanah = stmt.executeQuery(sql);
			if (rsTanah.next()) {
				//idKementerian = rsTanah.getString("ID_KEMENTERIAN");
				idNegeriHakmilik = rsTanah.getString("ID_NEGERI");

			}

			// TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			String noFail = "";
			String kodUrusan = getKodUrusan(idUrusan);
			
			if (idKementerian == "" || idKementerian == null) {
				idKementerian = "0";
			}
			
			noFail = generateNoFail(session, idUrusan, kodUrusan,
					getKodKementerianByIdKementerian(idKementerian),
					idKementerian, getKodNegeriByIdNegeri(idNegeriHakmilik),
					idNegeriHakmilik, idSuburusan);
			r.add("NO_FAIL", noFail);
			r.add("NO_FAIL_ROOT", noFail);
			r.add("NO_FAIL_NEGERI", txtNoFailNegeri);
			r.add("TAJUK_FAIL", txtPerkara);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPHPPERMOHONANSEWA
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("CATATAN", txtCatatan);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610198");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS",
					getIdSuburusanstatus(idSuburusan, "1610198")); // MAKLUMAT
																	// PERMOHONAN

			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			sql = "SELECT B.NO_PERMOHONAN, A.NAMA, A.EMEL FROM TBLPHPPEMOHON A, TBLPERMOHONAN B"
					+ " WHERE A.ID_PEMOHON = B.ID_PEMOHON AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rsUser = stmt.executeQuery(sql);

			if (rsUser.next()) {
				if (!"".equals(rsUser.getString("NAMA"))
						&& !"".equals(rsUser.getString("EMEL"))) {
					XEkptgEmailSender email = XEkptgEmailSender.getInstance();
					email.FROM = "etapp_webmaster@kptg.gov.my";
					email.RECIEPIENT = rsUser.getString("EMEL");
					email.SUBJECT = "PERMOHONAN PENYEWAAN HARTA TANAH PERSEKUTUAN #"
							+ rsUser.getString("NO_PERMOHONAN");
					email.MESSAGE = rsUser.getString("NAMA").toUpperCase()
							+ "."
							+ "<br><br>Merujuk kepada No. Permohonan diatas. Permohonan ini telah didaftarkan dan"
							+ "No Fail yang dijana ialah " + noFail + "."
							+ "<br><br>Terima Kasih.";
					email.sendEmail();
				}
			}

			//TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", userId);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", userRole);
			r.add("FLAG_BUKA", "Y");

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL PENYEWAAN ONLINE [" + noFail + "] DIDAFTARKAN");
			
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

	public String getKodKementerianByIdKementerian(String idKementerian)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '"
					+ idKementerian + "'";

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

	public String getKodNegeriByIdNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_MAMPU FROM TBLRUJNEGERI WHERE ID_NEGERI = '"
					+ idNegeri + "'";

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

	public String getIdHakmilik(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_HAKMILIKAGENSI FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPHAKMILIKPERMOHONAN C"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKAGENSI") == null ? "" : (String) rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdHakmilikSementara(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_HAKMILIKSEMENTARA FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPHAKMILIKPERMOHONAN C"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKSEMENTARA");
			} else {
				return "";
			}

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

//			sql = "SELECT L.ID_HAKMILIKAGENSI, A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK,"
//					+ " B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.NO_WARTA, A.KEGUNAAN_TANAH, L.LUAS_BERSAMAAN, L.ID_LUAS_BERSAMAAN, A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI,"
//					+ " G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KOD_LUAS AS KOD_LUAS, K.KETERANGAN AS JENIS_LUAS_BERSAMAAN"
//					+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I,TBLRUJAGENSI J, TBLRUJLUAS K, TBLHTPHAKMILIKAGENSI L"
//					+ " WHERE A.ID_HAKMILIK = L.ID_HAKMILIK AND A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+)"
//					+ " AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND L.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND L.ID_AGENSI = J.ID_AGENSI(+) AND L.ID_LUAS_BERSAMAAN = K.ID_LUAS"
//					+ " AND L.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";
//			
//			sql = sql + " UNION";

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
				if (rs.getString("NO_HAKMILIK") != null
						&& rs.getString("NO_WARTA") == null) {
					h.put("statusRizab", "MILIK");
				} else if (rs.getString("NO_HAKMILIK") == null
						&& rs.getString("NO_WARTA") != null) {
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
				beanMaklumatTanah.addElement(h);
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
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PEMOHON, C.NAMA, C.NAMA_PEGAWAI, C.NO_PENGENALAN, C.PEKERJAAN, C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP,"
					+ " C.POSKOD_TETAP, C.ID_BANDARTETAP, C.ID_NEGERITETAP, C.NO_TEL, C.NO_FAX,"
					+ " C.EMEL, C.ID_KATEGORIPEMOHON "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "99999"
								: rs.getString("ID_KATEGORIPEMOHON"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("noPendaftaran",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaan",
						rs.getString("PEKERJAAN") == null ? "" : rs
								.getString("PEKERJAAN"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskod",
						rs.getString("POSKOD_TETAP") == null ? "" : rs
								.getString("POSKOD_TETAP"));
				h.put("idNegeri",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs
								.getString("ID_NEGERITETAP"));
				h.put("idBandar",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs
								.getString("ID_BANDARTETAP"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("noFaks",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));

				beanMaklumatPemohon.addElement(h);
				bil++;

			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String generateNoFail(HttpSession session, String idUrusan, String kodUrusan,
			String kodKementerian, String idKementerian, String kodNegeri,
			String idNegeri, String idSuburusan) throws Exception {
		String noFail = "";
		String kodTanah = "";

		if ("35".equals(idSuburusan)) {
			kodTanah = "B";
		} else {
			kodTanah = "T";
		}

		noFail = "JKPTG/BPHP/"
				+ kodUrusan
				+ "/";
				if (kodKementerian == null || kodKementerian == "") {
					noFail = noFail + "0";
				} else {
					noFail = noFail + kodKementerian;
				}
				noFail = noFail + "/"
				+ kodNegeri
				+ "/"
				+ kodTanah
				+ "-"
				+ File.getSeqNoP(session, 4, Integer.parseInt(idUrusan),
						Integer.parseInt(idKementerian),
						Integer.parseInt(idNegeri));

		return noFail;		
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

	public String getKodUrusan(String idUrusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_URUSAN FROM TBLRUJURUSAN WHERE ID_URUSAN = '"
					+ idUrusan + "'";

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

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
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

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}
}
