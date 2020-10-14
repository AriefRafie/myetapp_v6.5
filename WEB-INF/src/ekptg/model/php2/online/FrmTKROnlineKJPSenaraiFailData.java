
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

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.model.utils.emel.EmailConfig;

public class FrmTKROnlineKJPSenaraiFailData {

	//test 13/8/2020
	private Vector<Hashtable<String, Object>> senaraiFail = null;
	private Vector<Hashtable<String, Object>> beanMaklumatAgensi = null;
	private Vector<Hashtable<String, Object>> beanMaklumatTanah = null;
	private Vector<Hashtable<String, Object>> beanMaklumatBorangK = null;
	private Vector<Hashtable<String, Object>> beanMaklumatPermohonan = null;
	private Vector<Hashtable<String, Object>> beanMaklumatPemohon = null;
	private Vector<Hashtable<String, Object>> beanMaklumatPejabat = null;
	private Vector<Hashtable<String, Object>> beanMaklumatHakmilik = null;
	private Vector<Hashtable<String, Object>> beanMaklumatHeader = null;
	private Vector<Hashtable<String, Object>> beanMaklumatLampiran = null;
	private Vector beanMaklumatTukarguna = null;

	protected Db db;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

	static Logger myLogger = Logger.getLogger(FrmPLPOnlineKJPSenaraiFailData.class);

	public void carianFail(String noFail, String noPermohonan, String tajukFail, String namaPemohon,
			String tarikhTerima, String idNegeri, String idDaerah,
			String idMukim, String idJenisHakmilik, String noHakmilik,
			String noWarta, String idLot, String noLot, String noPegangan,
			String idStatus, String idKementerian, String idAgensi,
			String checkTanah) throws Exception {

		Db db = null;
		Db db1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector<Hashtable<String, Object>>();
			db = new Db();
			db1 = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.NO_PERMOHONAN, A.TAJUK_FAIL, B.TARIKH_TERIMA, A.TARIKH_DAFTAR_FAIL, C.NAMA, D.KETERANGAN, B.ID_STATUS, H.USER_NAME, B.NO_RAYUAN, RUJNEGERI.NAMA_NEGERI "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLPHPHAKMILIK F, USERS H, TBLRUJNEGERI RUJNEGERI "
					+ " WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN = '6' AND A.ID_SUBURUSAN = '33' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS"
					+ " AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+)"
					+ " AND F.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) "
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND A.NO_FAIL IS NOT NULL"
					+ " AND A.ID_MASUK = H.USER_ID(+)"
					+ " AND B.ID_STATUS != '999'";

			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// tajukFail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhTerima))
									.toUpperCase() + "'";
				}
			}

			// idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")
						&& !idNegeri.trim().equals("99999")) {
					sql = sql + " AND F.ID_NEGERI = '" + idNegeri.trim() + "'";
				}
			}

			// idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")
						&& !idDaerah.trim().equals("99999")) {
					sql = sql + " AND F.ID_DAERAH = '" + idDaerah.trim() + "'";
				}
			}

			// idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")
						&& !idMukim.trim().equals("99999")) {
					sql = sql + " AND F.ID_MUKIM = '" + idMukim.trim() + "'";
				}
			}

			// idKementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")
						&& !idKementerian.trim().equals("99999")) {
					sql = sql + " AND F.ID_KEMENTERIAN = '"
							+ idKementerian.trim() + "'";
				}
			}

			// idAgensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")
						&& !idAgensi.trim().equals("99999")) {
					sql = sql + " AND F.ID_AGENSI = '" + idAgensi.trim() + "'";
				}
			}

			// idJenisHakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")
						&& !idJenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND F.ID_JENISHAKMILIK = '"
							+ idJenisHakmilik.trim() + "'";
				}
			}

			// jenis Tanah - Asna
			if (checkTanah != null) {
				if (!checkTanah.trim().equals("") && checkTanah.equals("1")) {
					sql = sql + " AND F.NO_HAKMILIK IS NOT NULL";
				} else if (!checkTanah.trim().equals("")
						&& checkTanah.equals("2")) {
					sql = sql + " AND F.NO_WARTA IS NOT NULL";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idLot
			if (idLot != null) {
				if (!idLot.trim().equals("") && !idLot.trim().equals("99999")) {
					sql = sql + " AND F.ID_LOT = '" + idLot.trim() + "'";
				}
			}

			// noLot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_LOT) LIKE '%' ||'"
							+ noLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			// noPegangan
			if (noPegangan != null) {
				if (!noPegangan.trim().equals("")) {
					sql = sql + " AND UPPER(F.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ noPegangan.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("")
						&& !idStatus.trim().equals("99999")) {
					sql = sql + " AND B.ID_STATUS = '" + idStatus.trim() + "'";
				}
			}

			sql = sql + " ORDER BY B.TARIKH_TERIMA DESC NULLS LAST ";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, Object> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, Object>();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				String idPermohonan = rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN");
				h.put("idPermohonan", idPermohonan);
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs
						.getString("NO_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhBukaFail",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("idStatus",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("status",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("userLogin",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("noRayuan",
						rs.getString("NO_RAYUAN") == null ? "" : rs
								.getString("NO_RAYUAN"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("tajukFail", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				//UNTUK NOTIFIKASI ULASAN DARIPADA KJP -08112017-
				h.put("flagTerimaUlasanKJP", getFlagTerimaUlasanKJP(idPermohonan, db1));
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db1 != null)
				db1.close();
		}
	}

	public Vector<Hashtable<String, Object>> getSenaraiFail(String findNoFail, String findNoPermohonan, String findTajukFail
		, String findPemohon, String findNoPengenalan
		, String findTarikhTerima
		, String findNoHakmilik, String findNoWarta, String findNoPegangan, String findJenisHakmilik, String findJenisLot, String findNoLot
		, String findNegeri, String findDaerah, String findMukim
		, String userId) {

		String sql = "";
		Vector<Hashtable<String, Object>> listFail = null;
		Hashtable<String, Object> h;

		try {
			listFail = new Vector<Hashtable<String, Object>>();

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT "
					+ " F.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL"
					+ " ,RS.KETERANGAN STATUS, P.TARIKH_TERIMA, P.NO_PERMOHONAN, P.ID_STATUS"
					+ " FROM TBLPFDFAIL F, TBLPERMOHONAN P"
					+ " ,USERS_KEMENTERIAN UK"
					+ " ,TBLRUJSTATUS RS"
					+ " WHERE F.ID_FAIL = P.ID_FAIL"
					+ " AND F.ID_SUBURUSAN = '33'"
					+ " AND P.ID_STATUS = RS.ID_STATUS "
					+ " AND P.ID_STATUS NOT IN (1610199)"
					/*+ " AND F.ID_KEMENTERIAN = UK.ID_KEMENTERIAN "*/
					+ " AND UK.USER_ID = '" + userId + "'";
			//noFail
			if (findNoFail != null) {
				if (!findNoFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%' ||'"
							+ findNoFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			//tajukFail
			if (findTajukFail != null) {
				if (!findTajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.TAJUK_FAIL) LIKE '%' ||'"
							+ findTajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			//SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			sql = sql + " ORDER BY F.TARIKH_MASUK DESC ";


			myLogger.info("getSenaraiFail :::: sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable<String, Object>();
				//h.put("ID_ULASANTEKNIKAL", rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs.getString("ID_ULASANTEKNIKAL"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NO_PERMOHONAN", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN"));
				h.put("TAJUK_FAIL", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("TARIKH_TERIMA", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				//h.put("TARIKH_JANGKA_TERIMA", rs.getDate("TARIKH_JANGKA_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_JANGKA_TERIMA")));
				listFail.addElement(h);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}

		return listFail;

	}
	public String getUserRole(String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT USER_ROLE  FROM USERS WHERE USER_ID = '" + userId + "'";

			ResultSet rs = stmt.executeQuery(sql);


			if (rs.next()){
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

			if (rs.next()){
				return (String)rs.getString("ID_JAWATAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
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

			sql = "SELECT A.USER_ID, A.USER_NAME, C.ID_NEGERI, B.ID_KEMENTERIAN, B.ID_AGENSI, B.NO_KP_BARU FROM USERS A, USERS_KEMENTERIAN B, TBLRUJAGENSI C, TBLRUJKEMENTERIAN D "
					+ " WHERE A.USER_ID = B.USER_ID AND B.ID_AGENSI = C.ID_AGENSI AND B.ID_KEMENTERIAN = D.ID_KEMENTERIAN AND A.USER_ID = '"
					+ userId + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("listDetailKJP:: "+sql);

			if (rs.next()) {
				h = new Hashtable();
				h.put("userId", rs.getString("USER_ID").toString());
				h.put("idNegeri", rs.getString("ID_NEGERI").toString());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN").toString());
				h.put("idAgensi", rs.getString("ID_AGENSI").toString());
				h.put("namaPemohon", rs.getString("USER_NAME").toString());
				h.put("kadPengenalanPemohon", rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
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
	/*public String getIdHakmilikAgensiByPeganganHakmilik(
			String peganganHakmilik, String idKategoriPemohon, String idAgensi)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			if ("3".equals(idKategoriPemohon)) {
				sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
						+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase()
						+ "' AND TBLHTPHAKMILIKAGENSI.ID_AGENSI != '"
						+ idAgensi + "'";
			} else {
				sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
						+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase() + "'";
			}

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
	}*/

	public String getIdHakmilikAgensiByPeganganHakmilik(String noLot, String noHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
				+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase() + "'";
			*/

			/*sql= " SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI " +
					" WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK " +
					" AND UPPER(TBLHTPHAKMILIK.NO_LOT) = '"+ noLot +"' AND TBLHTPHAKMILIK.NO_HAKMILIK LIKE '%"+ noHakmilik +"'";
					*/
			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT, A.NO_HAKMILIK, A.NO_WARTA, " +
					" E.NAMA_MUKIM, D.NAMA_DAERAH,C.NAMA_NEGERI, A.ID_JENISHAKMILIK, A.ID_LOT, A.TARIKH_WARTA, " +
					" A.ID_MUKIM, A.ID_DAERAH, A.ID_NEGERI,HM.kod_jenis_hakmilik"+
//				    "(SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH " +
//					" WHERE RJH.ID_JENISHAKMILIK=A.ID_JENISHAKMILIK) KOD_JENIS_HAKMILIK " +
					" FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK HM" +
					" WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) " +
					" AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = HM.ID_JENISHAKMILIK AND A.NO_LOT = '" + noLot + "' AND A.NO_HAKMILIK LIKE '%" + noHakmilik + "'";

			System.out.println("getIdHakmilikAgensiByPeganganHakmilik :: sql >>> "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIK");
			} else {
				return "";
			}

		}  catch (Exception re) {
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikAgensiByPeganganHakmilik(String peganganHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
				+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase() + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}

		}  catch (Exception re) {
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	public String getIdHakmilikSementaraByPeganganHakmilik(
			String peganganHakmilik, String idKategoriPemohon, String idAgensi)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			if ("3".equals(idKategoriPemohon)) {
				sql = "SELECT ID_HAKMILIKSEMENTARA AS ID_HAKMILIKAGENSI FROM TBLPHPHAKMILIKSEMENTARA WHERE "
						+ " UPPER(PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase()
						+ "' AND ID_AGENSI != '" + idAgensi + "'";
			} else {
				sql = "SELECT ID_HAKMILIKSEMENTARA AS ID_HAKMILIKAGENSI FROM TBLPHPHAKMILIKSEMENTARA WHERE "
						+ " UPPER(PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase() + "'";
			}

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
	public void setMaklumatTanah(String idHakmilikAgensi, String idHakmilikSementara) throws Exception {
		System.out.println("idHakmilikAgensi ros >>>> "+idHakmilikAgensi +" idHakmilikSementara ros >>>> "+idHakmilikSementara);
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector<Hashtable<String, Object>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HMA.ID_HAKMILIKAGENSI, HM.ID_HAKMILIK, NULL AS ID_HAKMILIKSEMENTARA, HM.PEGANGAN_HAKMILIK,"
					+ " HM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, HM.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HM.NO_LOT, HMA.ID_LUAS_BERSAMAAN, HMA.LUAS_BERSAMAAN,"
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HM.NO_WARTA, HM.TARIKH_WARTA, HM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HM.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.KEGUNAAN_TANAH,"
					+ " HM.SYARAT, HM.SEKATAN, HMA.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,HM.LUAS"

					+ " FROM TBLHTPHAKMILIKAGENSI HMA, TBLHTPHAKMILIK HM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE HMA.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMA.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND HM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMA.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HMA.ID_HAKMILIK = '" + idHakmilikAgensi + "'";
					//+ " AND HMA.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

			sql = sql + " UNION";

			sql = sql
					+ " SELECT NULL AS ID_HAKMILIKAGENSI, NULL AS ID_HAKMILIK, HMS.ID_HAKMILIKSEMENTARA, HMS.PEGANGAN_HAKMILIK,"
					+ " HMS.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HMS.NO_HAKMILIK, HMS.ID_LOT,"
					+ " RUJLOT.KETERANGAN AS JENIS_LOT, HMS.NO_LOT, HMS.ID_LUAS AS ID_LUAS_BERSAMAAN, HMS.LUAS AS LUAS_BERSAMAAN, "
					+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HMS.NO_WARTA, HMS.TARIKH_WARTA, HMS.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
					+ " HMS.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HMS.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HMS.ID_KATEGORI AS ID_KATEGORI,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HMS.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HMS.KEGUNAAN_TANAH,"
					+ " HMS.SYARAT, HMS.SEKATAN, HMS.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,HMS.LUAS"
					+ " FROM TBLPHPHAKMILIKSEMENTARA HMS, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
					+ " WHERE HMS.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HMS.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND HMS.ID_LUAS = RUJLUAS.ID_LUAS(+) AND HMS.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HMS.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
					+ " AND HMS.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HMS.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HMS.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
					+ " AND HMS.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND HMS.ID_HAKMILIKSEMENTARA = '" + idHakmilikSementara
					+ "'";
			myLogger.info("SQL setMaklumatTanah: "+sql);
			ResultSet rs = stmt.executeQuery(sql);


			Hashtable<String, Object> h;
			if (rs.next()) {
				h = new Hashtable<String, Object>();
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

				h.put("luasLot", rs.getString("LUAS") == null || rs.getString("JENIS_LUAS") == null ? "" : rs.getString("LUAS") + " " + rs.getString("JENIS_LUAS"));

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

			} else {
				h = new Hashtable<String, Object>();
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
				h.put("luasLot", "");
				beanMaklumatTanah.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	/*public void setMaklumatTanah(String idHakmilik) throws Exception {
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

				+ " FROM TBLHTPHAKMILIKAGENSI HMA, TBLHTPHAKMILIK HM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, "
				+ " TBLRUJLUAS RUJLUAS, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, "
				+ " TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, "
				+ " TBLRUJKEMENTERIAN RUJKEMENTERIAN"

				+ " WHERE HMA.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HM.ID_LOT = RUJLOT.ID_LOT(+)"
				+ " AND HMA.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND HM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
				+ " AND HM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
				+ " AND HMA.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
				+ " AND HMA.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";


			sql = "SELECT A.ID_HAKMILIK,A.PEGANGAN_HAKMILIK,A.NO_HAKMILIK,A.NO_LOT,A.LUAS, A.ID_LUAS, A.NO_WARTA, " +
					" A.TARIKH_WARTA, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, B.KOD_LOT, " +
					" B.KETERANGAN AS JENIS_LOT, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI, G.KETERANGAN AS SUBKATEGORI, " +
					" H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, " +
					" K.KETERANGAN AS JENIS_LUAS, K.KOD_LUAS AS KOD_LUAS, A.ID_KEMENTERIAN, A.ID_AGENSI" +
					" ,A.ID_NEGERI,HMA.LUAS_BERSAMAAN"+
					" FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, " +
					" TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I, TBLRUJAGENSI J, " +
					" TBLRUJLUAS K,TBLHTPHAKMILIKAGENSI HMA " +

					" WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) " +
					" AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) " +
					" AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) " +
					" AND A.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_LUAS = K.ID_LUAS " +
					" AND HMA.ID_HAKMILIK = A.ID_HAKMILIK AND A.ID_HAKMILIK = '" + idHakmilik + "'";

			System.out.println("setMaklumatTanah :: sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("jenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("JENIS_HAKMILIK") == null? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " - " + rs.getString("JENIS_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("jenisLot", rs.getString("KOD_LOT") == null || rs.getString("JENIS_LOT") == null? "" : rs.getString("KOD_LOT").toUpperCase() + " - " + rs.getString("JENIS_LOT").toUpperCase());
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("luasLot", rs.getString("LUAS") == null || rs.getString("JENIS_LUAS") == null ? "" : rs.getString("LUAS") + " " + rs.getString("JENIS_LUAS"));
				h.put("idLuas", rs.getString("ID_LUAS") == null ? "0" : rs.getString("ID_LUAS"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("idNegeriTanah", rs.getString("ID_NEGERI"));

				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				beanMaklumatTanah.addElement(h);
				bil++;
			}if (bil == 1){
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
				h.put("luasBersamaan", "");
				beanMaklumatTanah.addElement(h);
			}

		}  catch (Exception re) {
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}*/
	public String daftarBaru(String idUrusan, String idSuburusan, String idSubsuburusan,String idJenisTanah, String tarikhTerima,
			String tarikhSurat, String noRujukanSurat, String perkara,
			String idKategoriPemohon, String idKementerian, String idAgensi,
			String idHakmilikAgensi,
			String idLuasKegunaan, String txtTujuanKegunaan,
			String idKementerianTanah, String idNegeriTanah,
			String idLuasTanah, String luasTanah, String idHakmilikSementara,
			HttpSession session) throws Exception {


		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String idFailString = "";
		String idHakmilikHtp = "";
		String noRujukanOnline = "";
		idUrusan = "6";
		String idNegeriHakmilik = "";
		String idLuas = "";
		String luas = "";
		String namaUser = "";
		String IdKategoriUser = "";
		String emelUser = "";
		String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
		String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN, TBLHTPHAKMILIK.ID_NEGERI, "
					+ " TBLHTPHAKMILIKAGENSI.ID_LUAS_BERSAMAAN, TBLHTPHAKMILIKAGENSI.LUAS_BERSAMAAN, "
					+ " TBLHTPHAKMILIKAGENSI.ID_HAKMILIK FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI "
					+ " WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
					+ " AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";

				ResultSet rsTanah = stmt.executeQuery(sql);
				if (rsTanah.next()){
					idKementerian = rsTanah.getString("ID_KEMENTERIAN");
					idNegeriHakmilik = rsTanah.getString("ID_NEGERI");
					idLuas = rsTanah.getString("ID_LUAS_BERSAMAAN");
					luas = rsTanah.getString("LUAS_BERSAMAAN");
					idHakmilikHtp = rsTanah.getString("ID_HAKMILIK");
				}

			// TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "6");
			r.add("ID_SUBURUSAN", "33");
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", perkara);
			r.add("ID_LOKASIFAIL", "2"); // UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "4"); // DATA BARU ETAPP
			r.add("ID_NEGERI", idNegeriHakmilik);
			r.add("ID_KEMENTERIAN", idKementerian);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));


			sql = r.getSQLInsert("TBLPFDFAIL");
			myLogger.info("sql 1: "+sql);
			stmt.executeUpdate(sql);

			// TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", idKategoriPemohon);
			r.add("ID_KEMENTERIAN", idKementerian);
			r.add("ID_AGENSI", idAgensi);

			if ("3".equals(idKategoriPemohon)) {
				sql = "SELECT * FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
						+ idAgensi + "'";
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()) {
					r.add("NAMA", rs.getString("NAMA_AGENSI") == null ? "" : rs
							.getString("NAMA_AGENSI").toUpperCase());
					r.add("ALAMAT1_TETAP", rs.getString("ALAMAT1") == null ? ""
							: rs.getString("ALAMAT1").toUpperCase());
					r.add("ALAMAT2_TETAP", rs.getString("ALAMAT2") == null ? ""
							: rs.getString("ALAMAT2").toUpperCase());
					r.add("ALAMAT3_TETAP", rs.getString("ALAMAT3") == null ? ""
							: rs.getString("ALAMAT3").toUpperCase());
					r.add("POSKOD_TETAP", rs.getString("POSKOD") == null ? ""
							: rs.getString("POSKOD").toUpperCase());
					r.add("ID_NEGERITETAP",
							rs.getString("ID_NEGERI") == null ? "" : rs
									.getString("ID_NEGERI").toUpperCase());
				}

			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));


			sql = r.getSQLInsert("TBLPHPPEMOHON");
			myLogger.info("sql 2: "+sql);
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

			noRujukanOnline = "JKPTG/BPHP/04/" + kodUrusan + "/" + currentDate.get(Calendar.YEAR) + "/" + File.getSeqNo(db, 4, 6, 0, 0, 0, false, false, currentDate.get(Calendar.YEAR), 0);
//					generateNoFail(session, kodUrusan,
//					getKodKementerian(idKementerianTanah), idKementerianTanah,
//					getKodNegeri(idNegeriTanah), idNegeriTanah);

			r.add("NO_PERMOHONAN",noRujukanOnline);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));


			sql = r.getSQLInsert("TBLPERMOHONAN");
			myLogger.info("sql 3: "+sql);
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			long idhakmilikPermohonan = DB
					.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("ID_HAKMILIKSEMENTARA", idHakmilikSementara);
//			r.add("ID_PPTBORANGK", idPPTBorangK);
//			r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);
//			r.add("ID_PHPBORANGK", idPHPBorangK);
			if ("3".equals(idJenisTanah)) {
				r.add("FLAG_BORANGK", "Y");
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));


			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			myLogger.info("sql 4: "+sql);
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIK
			String peganganHakmilik = "";
			if ("3".equals(idJenisTanah)) {
//				setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan, idPHPBorangK);
				if (getBeanMaklumatBorangK().size() != 0) {
					Hashtable hashTanah = (Hashtable) getBeanMaklumatBorangK()
							.get(0);

					r = new SQLRenderer();
					long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
					r.add("ID_HAKMILIK", idHakmilik);
					r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
					if (hashTanah.get("peganganHakmilik").toString().trim()
							.length() > 0) {
						r.add("PEGANGAN_HAKMILIK",
								hashTanah.get("peganganHakmilik"));
					} else {
						peganganHakmilik = getKodNegeri((String) hashTanah
								.get("idNegeri"))
								+ getKodDaerah((String) hashTanah
										.get("idDaerah"))
								+ getKodMukim((String) hashTanah.get("idMukim"))
								+ getKodJenisHakmilik((String) hashTanah
										.get("idJenisHakmilik"))
								+ Utils.digitLastFormatted(
										(String) hashTanah.get("noHakmilik"), 8);
						r.add("PEGANGAN_HAKMILIK", peganganHakmilik);
					}
					r.add("ID_NEGERI", hashTanah.get("idNegeri"));
					r.add("ID_DAERAH", hashTanah.get("idDaerah"));
					r.add("ID_MUKIM", hashTanah.get("idMukim"));
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

					r.add("TARIKH_BORANGK",
							r.unquote("to_date('"
									+ hashTanah.get("tarikhBorangK")
									+ "','dd/MM/yyyy')"));
					r.add("CATATAN", hashTanah.get("catatan"));
					r.add("NO_PERSERAHAN", hashTanah.get("noPerserahan"));
					r.add("TARIKH_CATATAN",
							r.unquote("to_date('"
									+ hashTanah.get("tarikhCatatan")
									+ "','dd/MM/yyyy')"));
					r.add("TARIKH_TERIMA",
							r.unquote("to_date('"
									+ hashTanah.get("tarikhTerima")
									+ "','dd/MM/yyyy')"));


					sql = r.getSQLInsert("TBLPHPHAKMILIK");
					myLogger.info("sql 5: "+sql);
					stmt.executeUpdate(sql);

				}
			} else {
				setMaklumatTanah(idHakmilikAgensi,idHakmilikSementara);
				if (getBeanMaklumatTanah().size() != 0) {
					Hashtable hashTanah = (Hashtable) getBeanMaklumatTanah()
							.get(0);

					r = new SQLRenderer();
					long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
					r.add("ID_HAKMILIK", idHakmilik);
					r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
					r.add("PEGANGAN_HAKMILIK",
							hashTanah.get("peganganHakmilik"));
					r.add("ID_NEGERI", hashTanah.get("idNegeri"));
					r.add("ID_DAERAH", hashTanah.get("idDaerah"));
					r.add("ID_MUKIM", hashTanah.get("idMukim"));
					r.add("NO_WARTA", hashTanah.get("noWarta"));
					r.add("TARIKH_WARTA",
							r.unquote("to_date('"
									+ hashTanah.get("tarikhWarta")
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
					myLogger.info("sql 6: "+sql);
					stmt.executeUpdate(sql);

				}
			}

			// TBLPHPPERMOHONANPELEPASAN
			r = new SQLRenderer();
			long idPHPPermohonanPelepasan = DB
					.getNextID("TBLPHPPERMOHONANPELEPASAN_SEQ");
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
			myLogger.info("sql 7: "+sql);
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("33", "1610198")); // MAKLUMAT
																				// PERMOHONAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));


			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			myLogger.info("sql 8: "+sql);
//			stmt.executeUpdate(sql);

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
			myLogger.info("sql 9: "+sql);
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
		session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
	}
	public String getKodUrusanByIdUrusan(String idUrusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_URUSAN FROM TBLRUJURUSAN WHERE ID_URUSAN = '" + idUrusan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("KOD_URUSAN");
			} else {
				return "";
			}

		}  catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	public String generateNoFail(HttpSession session, String kodUrusan, String kodKementerian,
			String idKementerian, String kodNegeri, String idNegeri)
			throws Exception {
		Calendar currentDate = new GregorianCalendar();
		String noRujukanOnline = "";
		noRujukanOnline = "JKPTG/BPHP/04/"
				+ kodUrusan
				+ "/"
				+ currentDate.get(Calendar.YEAR)
				+ "/"
				+ kodKementerian
				+ "/"
				+ kodNegeri
				+ "-"
				+ File.getSeqNo(4, 6, 0, 0, 0, false, false, currentDate.get(Calendar.YEAR), 0);

		return noRujukanOnline;
	}
	public String getKodKementerian(String idKementerian) throws Exception {
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

	public String getKodNegeri(String idNegeri) throws Exception {
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
				return rs.getString("ID_SUBURUSANSTATUS").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String simpanUlasan(String idUlasanTeknikal, String txtTarikhSurat,
			String txtNoRujukanSurat, String txtUlasan, String txtKeputusan,
			String txtNamaPengulas, String txtNoTelPengulas, String userId) {

		String flagStatus = "T";
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			if (!"".equals(txtTarikhSurat)) {
				r.add("TARIKH_SURAT",
						r.unquote("to_date('" + txtTarikhSurat
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", txtNoRujukanSurat);
			r.add("ULASAN", txtUlasan);
			r.add("FLAG_KEPUTUSAN", txtKeputusan);
			r.add("NAMA_PEGAWAI", txtNamaPengulas);
			r.add("NO_TELEFON", txtNoTelPengulas);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			flagStatus = "Y";
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			if (db != null)
				db.close();
		}

		return flagStatus;
	}

	public String hantarUlasan(String idUlasanTeknikal, String txtTarikhSurat,
			String txtNoRujukanSurat, String txtUlasan, String txtKeputusan,
			String txtNamaPengulas, String txtNoTelPengulas, String userId) {

		String flagStatus = "T";
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			if (!"".equals(txtTarikhSurat)) {
				r.add("TARIKH_SURAT",
						r.unquote("to_date('" + txtTarikhSurat
								+ "','dd/MM/yyyy')"));
			}
			r.add("NO_RUJUKAN", txtNoRujukanSurat);
			r.add("ULASAN", txtUlasan);
			r.add("FLAG_KEPUTUSAN", txtKeputusan);
			r.add("NAMA_PEGAWAI", txtNamaPengulas);
			r.add("NO_TELEFON", txtNoTelPengulas);
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
			r.add("FLAG_STATUS", "2");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			conn.commit();
			flagStatus = "Y";
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			if (db != null)
				db.close();
		}

		return flagStatus;
	}

	public void hapusDokumen(String idUlasanTeknikal) throws Exception {
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
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);

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

	public String getIdPermohonanByIdUlasanTeknikal(String idUlasanTeknikal) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERMOHONAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = '" + idUlasanTeknikal + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_PERMOHONAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Hashtable<String, Object> getMaklumatLampiran(String idUlasanTeknikal, String idPermohonan) {
		String sql = "";
		Hashtable<String, Object> lampiran = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN FROM TBLPHPDOKUMEN WHERE ID_PERMOHONAN = '" + idPermohonan + "' AND ID_ULASANTEKNIKAL = '" + idUlasanTeknikal + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				lampiran = new Hashtable<String, Object>();
				lampiran.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? "" : rs.getString("ID_DOKUMEN"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}

		return lampiran;
	}
	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector<Hashtable<String, Object>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.NO_PERMOHONAN, B.ID_PERMOHONAN, B.TARIKH_SURAT, B.TARIKH_TERIMA, B.NO_RUJ_SURAT, A.TAJUK_FAIL, B.TUJUAN, B.ID_PEMOHON, C.FLAG_GUNA, C.CADANGAN_KEGUNAAN, D.KETERANGAN AS STATUS "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANPELEPASAN C, TBLRUJSTATUS D WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("setMaklumatPermohonan==="+sql);

			Hashtable<String, Object> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, Object>();
				h.put("idFail",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noRujukanOnline", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("idPermohonan",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idPemohon",rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? "": sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "": sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("noRujukanSurat",rs.getString("NO_RUJ_SURAT") == null ? "" : rs.getString("NO_RUJ_SURAT").toUpperCase());
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				h.put("flagGuna",rs.getString("FLAG_GUNA") == null ? "" : rs.getString("FLAG_GUNA"));
				h.put("tujuanKegunaan",rs.getString("CADANGAN_KEGUNAAN") == null ? "" : rs.getString("CADANGAN_KEGUNAAN"));
				h.put("status",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				//h.put("status",rs.getString("status") == null ? "" : rs.getString("status"));
				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getIdPHPBorangKByPeganganHakmilik(String peganganHakmilik,
			String idKategoriPemohon, String idAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			if ("3".equals(idKategoriPemohon)) {
				sql = "SELECT BK.ID_BORANGK AS ID_PHPBORANGK"
						+ " FROM TBLPHPBORANGK BK, TBLPHPHAKMILIKBORANGK HMBK"
						+ " WHERE BK.ID_BORANGK = HMBK.ID_BORANGK AND BK.ID_HAKMILIKURUSAN IS NULL"
						+ " AND UPPER(HMBK.PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase() + "'"
						+ " AND HMBK.ID_AGENSI != '" + idAgensi + "'";
			} else {
				sql = "SELECT BK.ID_BORANGK AS ID_PHPBORANGK"
						+ " FROM TBLPHPBORANGK BK, TBLPHPHAKMILIKBORANGK HMBK"
						+ " WHERE BK.ID_BORANGK = HMBK.ID_BORANGK AND BK.ID_HAKMILIKURUSAN IS NULL"
						+ " AND UPPER(HMBK.PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase() + "'";
			}

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_PHPBORANGK");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getIdHakmilikUrusanByPeganganHakmilik(
			String peganganHakmilik, String idKategoriPemohon, String idAgensi)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			if ("3".equals(idKategoriPemohon)) {
				sql = "SELECT HMU.ID_HAKMILIKURUSAN"
						+ " FROM TBLHTPHAKMILIKURUSAN HMU, TBLHTPPERMOHONAN MOHON"
						+ " WHERE HMU.ID_PERMOHONAN = MOHON.ID_PERMOHONAN AND MOHON.ID_JENISTANAH = 3"
						+ " AND UPPER(HMU.PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase() + "'"
						+ " AND MOHON.ID_AGENSI != '" + idAgensi + "'";
			} else {
				sql = "SELECT HMU.ID_HAKMILIKURUSAN"
						+ " FROM TBLHTPHAKMILIKURUSAN HMU, TBLHTPPERMOHONAN MOHON"
						+ " WHERE HMU.ID_PERMOHONAN = MOHON.ID_PERMOHONAN AND MOHON.ID_JENISTANAH = 3"
						+ " AND UPPER(HMU.PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase() + "'";
			}

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKURUSAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	public String getKategoriPemohonTukarguna() throws Exception {
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

			myLogger.info("sql getIdHakmilikPermohonanByIdFail >>>> "+sql);
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
	public void setMaklumatBorangK(String idPPTBorangK,
			String idHakmilikUrusan, String idPHPBorangK) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatBorangK = new Vector<Hashtable<String, Object>>();
			db = new Db();
			Statement stmt = db.getStatement();

			// SENARAI BORANG K PPT
			sql = "SELECT TO_CHAR(PPTBK.ID_BORANGK) AS ID_PPTBORANGK, '' AS " +
					"ID_HAKMILIKURUSAN, '' AS ID_PHPBORANGK, '' AS PEGANGAN_HAKMILIK, " +
					"PPTHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PPTHM.NO_HAKMILIK, " +
					"CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN 1 WHEN " +
					"PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL THEN PPTHM.ID_LOT " +
					"ELSE 1 END AS ID_LOT,/*(SELECT RUJLOT.KETERANGAN FROM TBLRUJLOT RUJLOT WHERE RUJLOT.ID_LOT = " +
					"(CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN 1 " +
					"WHEN PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL THEN " +
					"PPTHM.ID_LOT ELSE 1 END)) AS JENIS_LOT,*/ " +
					"CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN 'LOT' " +
					"WHEN PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL THEN RL.KETERANGAN ELSE '' END AS JENIS_LOT, " +
					"CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN " +
					"PPTHM.NO_LOT WHEN PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL " +
					"THEN  PPTHM.NO_PT ELSE PPTHM.NO_LOT END AS NO_LOT, " +
					"PPTHM.ID_UNITLUASAMBIL AS ID_LUAS_BERSAMAAN, PPTHM.LUAS_AMBIL AS " +
					"LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS, " +
					"PPTHM.NO_WARTA_RIZAB AS NO_WARTA, PPTHM.TARIKH_WARTA_RIZAB AS TARIKH_WARTA, " +
					"PPTHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PPTHM.ID_DAERAH, " +
					"RUJDAERAH.NAMA_DAERAH, PPTHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, " +
					"PPTHM.ID_KATEGORITANAH AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS " +
					"KATEGORI, 109 AS ID_SUBKATEGORI, 'TIADA MAKLUMAT' AS SUBKATEGORI, '' AS KEGUNAAN_TANAH, " +
					"PPTHM.SYARAT_NYATA AS SYARAT, PPTHM.SEKATAN_KEPENTINGAN AS SEKATAN, " +
					"PPTMOHON.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, " +
					"RUJKEMENTERIAN.NAMA_KEMENTERIAN, PPTBK.TARIKH_BORANGK, PPTBK.CATATAN, PPTENBK.NO_PERSERAHAN, " +
					"PPTENBK.TARIKH_CATATAN, PPTENBK.TARIKH_TERIMA " +
					"FROM TBLPPTBORANGK PPTBK, TBLPPTHAKMILIKBORANGK PPTHMBK, " +
					"TBLPPTHAKMILIK PPTHM, TBLPPTENDOSANBORANGK PPTENBK, TBLPPTPERMOHONAN PPTMOHON, " +
					"TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLUAS RUJLUAS, TBLRUJMUKIM " +
					"RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, " +
					"TBLRUJKATEGORI RUJKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN, TBLRUJLOT RL " +
					"WHERE PPTBK.ID_BORANGK = PPTHMBK.ID_BORANGK AND PPTHMBK.ID_HAKMILIK = PPTHM.ID_HAKMILIK AND PPTBK.ID_BORANGK = PPTENBK.ID_BORANGK " +
					"AND PPTHM.ID_PERMOHONAN = PPTMOHON.ID_PERMOHONAN AND " +
					"PPTHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND " +
					"PPTHM.ID_UNITLUASAMBIL = RUJLUAS.ID_LUAS(+) " +
					"AND PPTHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PPTHM.ID_DAERAH = " +
					"RUJDAERAH.ID_DAERAH(+) AND PPTHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) " +
					"AND PPTHM.ID_KATEGORITANAH = RUJKATEGORI.ID_KATEGORI(+) AND " +
					"PPTMOHON.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND " +
					"RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+) " +
					"AND PPTHM.ID_LOT = RL.ID_LOT(+) " +
					"AND PPTHM.FLAG_ENDOSAN_BORANGK IS NOT NULL AND PPTBK.ID_BORANGK NOT IN " +
					"(SELECT ID_BORANGK FROM TBLHTPINFOBORANGK) " +
					"AND PPTBK.ID_BORANGK = '" + idPPTBorangK + "'";

			sql = sql + " UNION";

			// SENARAI BORANG K HTP
			sql = sql
					+ " SELECT '' AS ID_PPTBORANGK, TO_CHAR(URUSAN.ID_HAKMILIKURUSAN) AS ID_HAKMILIKURUSAN, '' AS ID_PHPBORANGK, URUSAN.PEGANGAN_HAKMILIK, "
					+ " URUSAN.ID_JENISHAKMILIK, JENISHAKMILIK.KOD_JENIS_HAKMILIK, "
					+ " URUSAN.NO_HAKMILIK, URUSAN.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, URUSAN.NO_LOT, "
					+ " URUSAN.ID_LUAS_BERSAMAAN, URUSAN.LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS, URUSAN.NO_WARTA, URUSAN.TARIKH_WARTA, "
					+ " TO_NUMBER(URUSAN.ID_MUKIM) AS ID_MUKIM, RUJMUKIM.NAMA_MUKIM, URUSAN.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, URUSAN.ID_NEGERI, "
					+ " RUJNEGERI.NAMA_NEGERI, URUSAN.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, URUSAN.ID_SUBKATEGORI, "
					+ " RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, '' AS KEGUNAAN_TANAH, URUSAN.SYARAT, URUSAN.SEKATAN, MOHON.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, "
					+ " RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN, PPTBK.TARIKH_BORANGK, PPTBK.CATATAN, ENDOSAN.NO_PERSERAHAN, "
					+ " ENDOSAN.TARIKH_CATATAN, ENDOSAN.TARIKH_TERIMA "
					+ " FROM TBLHTPHAKMILIKURUSAN URUSAN, TBLHTPPERMOHONAN MOHON, "
					+ " TBLHTPINFOBORANGK INFO, TBLPPTBORANGK PPTBK, TBLPPTENDOSANBORANGK ENDOSAN, "
					+ " TBLRUJJENISHAKMILIK JENISHAKMILIK, TBLRUJLOT RUJLOT, TBLRUJLUAS "
					+ " RUJLUAS, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, "
					+ " TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, "
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN "
					+ " WHERE URUSAN.ID_PERMOHONAN = MOHON.ID_PERMOHONAN(+) AND "
					+ " URUSAN.ID_PERMOHONAN = INFO.ID_PERMOHONAN(+) AND INFO.ID_INFOBORANGK = PPTBK.ID_BORANGK(+) "
					+ " AND PPTBK.ID_BORANGK = ENDOSAN.ID_BORANGK(+) AND "
					+ " URUSAN.ID_JENISHAKMILIK = JENISHAKMILIK.ID_JENISHAKMILIK(+) AND "
					+ " URUSAN.ID_LOT = RUJLOT.ID_LOT "
					+ " AND URUSAN.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND URUSAN.ID_MUKIM "
					+ " = RUJMUKIM.ID_MUKIM(+) AND URUSAN.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) "
					+ " AND URUSAN.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND URUSAN.ID_KATEGORI = "
					+ " RUJKATEGORI.ID_KATEGORI(+) AND URUSAN.ID_SUBKATEGORI = "
					+ " RUJSUBKATEGORI.ID_SUBKATEGORI(+) "
					+ " AND MOHON.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND "
					+ " RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+) "
					+ " AND MOHON.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK) "
					+ " AND MOHON.ID_JENISTANAH = 3 "
					+ " AND URUSAN.ID_HAKMILIKURUSAN = '" + idHakmilikUrusan +"'";

			sql = sql + " UNION";

			// SENARAI BORANG K PHP
			sql = sql
					+ " SELECT '' AS ID_PPTBORANGK, '' AS ID_HAKMILIKURUSAN, TO_CHAR(PHPBK.ID_BORANGK) AS ID_PHPBORANGK, PHPHMBK.PEGANGAN_HAKMILIK, "
					+ " PHPHMBK.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHMBK.NO_HAKMILIK, PHPHMBK.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHMBK.NO_LOT, "
					+ " PHPHMBK.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHMBK.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS, "
					+ " PHPHMBK.NO_WARTA, PHPHMBK.TARIKH_WARTA, PHPHMBK.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHMBK.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, "
					+ " PHPHMBK.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, PHPHMBK.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS "
					+ " KATEGORI, PHPHMBK.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHMBK.KEGUNAAN_TANAH, PHPHMBK.SYARAT, PHPHMBK.SEKATAN, "
					+ " PHPHMBK.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN, "
					+ " PHPBK.TARIKH_BORANGK, PHPBK.CATATAN, PHPBK.NO_PERSERAHAN, PHPBK.TARIKH_CATATAN, PHPBK.TARIKH_TERIMA "
					+ " FROM TBLPHPBORANGK PHPBK, TBLPHPHAKMILIKBORANGK PHPHMBK, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS, "
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, "
					+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN "
					+ " WHERE PHPBK.ID_BORANGK = PHPHMBK.ID_BORANGK AND PHPHMBK.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) "
					+ " AND PHPHMBK.ID_LOT = RUJLOT.ID_LOT(+) "
					+ " AND PHPHMBK.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHMBK.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHMBK.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) "
					+ " AND PHPHMBK.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) "
					+ " AND PHPHMBK.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND "
					+ " PHPHMBK.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND "
					+ " PHPHMBK.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+) "
					+ " AND PHPBK.ID_HAKMILIKURUSAN IS NULL AND PHPBK.ID_BORANGK = '" + idPHPBorangK + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, Object> h;
			if (rs.next()) {
				h = new Hashtable<String, Object>();
				h.put("idPPTBorangK",
						rs.getString("ID_PPTBORANGK") == null ? "" : rs
								.getString("ID_PPTBORANGK").toUpperCase());
				h.put("idHakmilikUrusan",
						rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs
								.getString("ID_HAKMILIKURUSAN").toUpperCase());
				h.put("idPHPBorangK",
						rs.getString("ID_PHPBORANGK") == null ? "" : rs
								.getString("ID_PHPBORANGK").toUpperCase());

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
				beanMaklumatBorangK.addElement(h);

			} else {

				h = new Hashtable<String, Object>();
				h.put("idPPTBorangK", "");
				h.put("idHakmilikUrusan", "");
				h.put("idPHPBorangK", "");

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
				beanMaklumatBorangK.addElement(h);
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

			sql = "SELECT C.ID_KATEGORIPEMOHON, C.ID_AGENSI, C.ID_KEMENTERIAN, C.ID_PEJABAT FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON  C"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND A.ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("beanMaklumatPemohon sql >>> "+beanMaklumatPemohon);

			Hashtable<String, Object> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, Object>();
				h.put("idKategoriPemohon",rs.getString("ID_KATEGORIPEMOHON") == null ? "99999": rs.getString("ID_KATEGORIPEMOHON").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "99999": rs.getString("ID_AGENSI").toUpperCase());
				h.put("idKementerian",rs.getString("ID_KEMENTERIAN") == null ? "99999" : rs.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "99999": rs.getString("ID_PEJABAT").toUpperCase());
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

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL, B.ID_PERMOHONAN, L.ID_SUBURUSAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, C.ID_PEMOHON, C.NAMA, C.ID_NEGERITETAP, C.ID_KATEGORIPEMOHON, C.ID_PEJABAT, C.ID_AGENSI, I.ID_NEGERI AS ID_NEGERITANAH, H.ID_KEMENTERIAN AS ID_KEMENTERIANTANAH, H.ID_AGENSI AS ID_AGENSITANAH,"
				+ " C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, G.KETERANGAN AS NAMA_BANDAR, C.NO_TEL, C.NO_FAX, B.ID_STATUS, E.KETERANGAN, I.NO_HAKMILIK, I.NO_WARTA, H.ID_HAKMILIKAGENSI, H.ID_HAKMILIK, J.KEPUTUSAN, B.NO_PERMOHONAN, B.FLAG_LAYER_KJP, K.NAMA_AGENSI"
				+ ",K.ALAMAT1, K.ALAMAT2, K.ALAMAT3, K.POSKOD, D.NAMA_NEGERI, A.TAJUK_FAIL,N.ID_KEMENTERIAN,M.NAMA_KEMENTERIAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPHAKMILIKPERMOHONAN F, TBLRUJBANDAR G, TBLHTPHAKMILIKAGENSI H, TBLHTPHAKMILIK I, TBLPHPPERMOHONANPELEPASAN J, TBLRUJAGENSI K, TBLRUJSUBURUSAN L, TBLRUJKEMENTERIAN M,USERS_KEMENTERIAN N"
				+ " WHERE A.ID_SUBURUSAN = '33' AND A.ID_SUBURUSAN = L.ID_SUBURUSAN AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND C.ID_NEGERITETAP = D.ID_NEGERI(+) AND B.ID_STATUS = E.ID_STATUS(+) AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
				+ " AND N.ID_KEMENTERIAN = M.ID_KEMENTERIAN AND N.USER_ID = B.ID_MASUK(+)"
				+ " AND F.ID_HAKMILIKAGENSI = H.ID_HAKMILIKAGENSI AND H.ID_HAKMILIK = I.ID_HAKMILIK AND C.ID_BANDARTETAP = G.ID_BANDAR(+) AND B.ID_PERMOHONAN = J.ID_PERMOHONAN AND H.ID_AGENSI = K.ID_AGENSI AND A.ID_FAIL = '" + idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("beanMaklumatHeader=="+sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("namaAgensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("namaKementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI").toUpperCase());
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "TIADA" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON").toUpperCase());
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
				h.put("idSubUrusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());

				/*if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))||
						"1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS")))
				{
					h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				}else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null){
					h.put("status", " PENDAFTARAN");
				} else {
					h.put("status", " SEDANG DIPROSES");
				}*/
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("idNegeriPemohon", rs.getString("ID_NEGERITETAP") == null ? "" : rs.getString("ID_NEGERITETAP").toUpperCase());
				h.put("subUrusan", "PENAWARAN");
				h.put("idHakmilikAgensi", rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs.getString("ID_HAKMILIKAGENSI"));
				//h.put("flagLayerKJP", rs.getString("FLAG_LAYER_KJP") == null ? "" : rs.getString("FLAG_LAYER_KJP"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				beanMaklumatHeader.addElement(h);
				bil++;
			}

			if (bil == 1){
				h = new Hashtable();
				h.put("idFail", "");
				h.put("noFail", "");
				h.put("namaKementerian", "");
				h.put("idKementerian", "");
				h.put("noPermohonan","");
				h.put("idPermohonan", "");
				h.put("tarikhTerima", "");
				h.put("idPemohon", "");
				h.put("idStatus", "");
				h.put("idSubUrusan", "");
				h.put("status", "");
				h.put("idHakmilik", "");
				h.put("idNegeriPemohon","");
				h.put("subUrusan", "");
				h.put("idHakmilikAgensi","");
				//h.put("flagLayerKJP","");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("negeri", "");
				h.put("perkara", "");
				h.put("keterangan", "");
				beanMaklumatHeader.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPejabatJKPTG(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector<Hashtable<String, Object>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX, A.ID_PEJABATJKPTG"
					+ " FROM TBLRUJPEJABATJKPTG A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABATJKPTG = '"
					+ idPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, Object> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, Object>();
				h.put("idPejabat", rs.getString("ID_PEJABATJKPTG") == null ? "": rs.getString("ID_PEJABATJKPTG"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? "": rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX").toUpperCase());
				beanMaklumatPejabat.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable<String, Object>();
				h.put("idPejabat", "");
				h.put("namaPejabat", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("bandar", "");
				h.put("negeri", "");
				h.put("idNegeri", "");
				h.put("noTel", "");
				h.put("noFax", "");
				beanMaklumatPejabat.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	private String getFlagTerimaUlasanKJP(String idPermohonan, Db db) {
		String flagTerimaUlasanKJP = "T";
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			sql = "SELECT ID_ULASANTEKNIKAL FROM TBLPHPULASANTEKNIKAL WHERE FLAG_KJP = 'KJP' AND FLAG_AKTIF = 'Y' AND FLAG_STATUS = '2'"
					+ " AND ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				flagTerimaUlasanKJP = "Y";
			}

		} catch (Exception ex){
			ex.printStackTrace();
		}
		return flagTerimaUlasanKJP;
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

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, Object> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, Object>();
				h.put("idAgensi",rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				h.put("namaAgensi", rs.getString("NAMA_AGENSI") == null ? "": rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
				h.put("poskod",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
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
	public void setMaklumatLampiran(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, JENIS_MIME FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaLampiran", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanLampiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("jenisMime",
						rs.getString("JENIS_MIME") == null ? "" : StringUtils.substringBefore(rs.getString("JENIS_MIME"), "/"));
				beanMaklumatLampiran.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	public void simpanKemaskiniLampiran(String idDokumen, String txtNamaLampiran,
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
			r.add("NAMA_DOKUMEN", txtNamaLampiran);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idDokumen + "] DIKEMASKINI");

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
	public void setMaklumatHakmilik(String idHakmilikPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHakmilik = new Vector<Hashtable<String, Object>>();
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
			myLogger.info("sql setMaklumatHakmilik >>>> "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, Object> h;
			if (rs.next()) {
				h = new Hashtable<String, Object>();
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
				h.put("luasLot",
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

				h = new Hashtable<String, Object>();
				h.put("idPPTBorangK", "");
				h.put("idHakmilikUrusan", "");
				h.put("idPHPBorangK", "");
				h.put("idHakmilikPermohonan", "");
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
	//SENARAI SEMAK
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
				myLogger.error("Error: ", re);
				throw re;
				} finally {
				if (db != null)
					db.close();
			}
			return senaraiSemak;
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

		public void setMaklumatTukarguna(String idPermohonan) throws Exception {
			Db db = null;
			String sql = "";

			try {
				beanMaklumatTukarguna = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT C.ID_PHPPERMOHONANPELEPASAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, C.CADANGAN_KEGUNAAN, C.ID_LUASASAL, C.LUAS_ASAL,"
						+ " D.KETERANGAN, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_UNITLUASMHNBERSAMAAN, C.LUAS_MHNBERSAMAAN, C.ID_UNITLUASBAKI, C.LUAS_BAKI, C.KEMAJUAN_TANAH"
						+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANPELEPASAN C, TBLRUJLUAS D"
						+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_LUASASAL = D.ID_LUAS(+) AND B.ID_PERMOHONAN = '"
						+ idPermohonan + "'";
				myLogger.info("setMaklumatTukarguna :: sql >>>>"+sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable h;
//				int bil = 1;
				if (rs.next()) {
					h = new Hashtable();
					h.put("idPermohonanPelepasan", rs
							.getString("ID_PHPPERMOHONANPELEPASAN") == null ? ""
							: rs.getString("ID_PHPPERMOHONANPELEPASAN"));
					h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
							: sdf.format(rs.getDate("TARIKH_TERIMA")));
					h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
							: sdf.format(rs.getDate("TARIKH_SURAT")));
					h.put("noRujukanSurat",
							rs.getString("NO_RUJ_SURAT") == null ? "" : rs
									.getString("NO_RUJ_SURAT"));
					h.put("perkara",
							rs.getString("TAJUK_FAIL") == null ? "" : rs
									.getString("TAJUK_FAIL"));
					h.put("cadanganKegunaan",
							rs.getString("CADANGAN_KEGUNAAN") == null ? "" : rs
									.getString("CADANGAN_KEGUNAAN"));
					h.put("flagGuna",
							rs.getString("FLAG_GUNA") == null ? "" : rs
									.getString("FLAG_GUNA"));
					h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? ""
							: Utils.formatLuas(rs.getDouble("LUAS_ASAL")));
					h.put("idLuas", rs.getString("ID_LUASASAL") == null ? ""
							: rs.getString("ID_LUASASAL"));
					h.put("keteranganLuasAsal",
							rs.getString("KETERANGAN") == null ? "" : rs
									.getString("KETERANGAN"));
					h.put("idLuasMohon", rs.getString("ID_LUASMHN") == null ? ""
							: rs.getString("ID_LUASMHN"));
					h.put("luas1",
							rs.getString("LUAS_MHN1") == null ? "" : Utils
									.formatLuas(rs.getDouble("LUAS_MHN1")));
					h.put("luas2",
							rs.getString("LUAS_MHN2") == null ? "" : Utils
									.formatLuas(rs.getDouble("LUAS_MHN2")));
					h.put("luas3",
							rs.getString("LUAS_MHN3") == null ? "" : Utils
									.formatLuas(rs.getDouble("LUAS_MHN3")));
					h.put("luasBersamaan",
							rs.getString("LUAS_MHNBERSAMAAN") == null ? "" : Utils
									.formatLuas(rs.getDouble("LUAS_MHNBERSAMAAN")));
					h.put("luasBaki", rs.getString("LUAS_BAKI") == null ? ""
							: Utils.formatLuas(rs.getDouble("LUAS_BAKI")));
					h.put("kemajuanTanah",
							rs.getString("KEMAJUAN_TANAH") == null ? "" : rs
									.getString("KEMAJUAN_TANAH"));
					beanMaklumatTukarguna.addElement(h);
//					bil++;
				}else{

					h = new Hashtable();
					h.put("idPermohonanPelepasan","");
					h.put("tarikhTerima", "");
					h.put("tarikhSurat", "");
					h.put("noRujukanSurat","");
					h.put("perkara","");
					h.put("cadanganKegunaan","");
					h.put("flagGuna","");
					h.put("luasAsal","");
					h.put("idLuas","");
					h.put("keteranganLuasAsal","");
					h.put("idLuasMohon","");
					h.put("luas1","");
					h.put("luas2","");
					h.put("luas3","");
					h.put("luasBersamaan","");
					h.put("luasBaki", "");
					h.put("kemajuanTanah","");
					beanMaklumatTukarguna.addElement(h);

				}

			} catch (Exception re) {
				throw re;
				} finally {
				if (db != null)
					db.close();
			}
		}

		public boolean checkMaklumatPywLengkap(String idPermohonan) throws Exception{
			Db db = null;
			String sql = "";
			boolean bool = true;
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT FLAG_GUNA FROM TBLPHPPERMOHONANPELEPASAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()){

					//SEBAHAGIAN
					if("2".equals(rs.getString("FLAG_GUNA"))){

						sql = "SELECT ID_PERMOHONAN FROM TBLPHPPERMOHONANPELEPASAN WHERE "
							+ "(LUAS_MHN IS NULL OR ID_LUASMHN IS NULL OR LUAS_MHN1 IS NULL)"
							+ " AND ID_PERMOHONAN= '" +idPermohonan+ "'";
						ResultSet rs2 = stmt.executeQuery(sql);
						if (rs2.next()){
							bool = false;
						} else {
							bool = true;
						}
					} else if("1".equals(rs.getString("FLAG_GUNA"))){
						sql = "SELECT ID_PERMOHONAN FROM TBLPHPPERMOHONANPELEPASAN WHERE "
							+ " (LUAS_MHN IS NULL)"
							+ " AND ID_PERMOHONAN= '" +idPermohonan+ "'";
						ResultSet rs1 = stmt.executeQuery(sql);
						if (rs1.next()){
							bool = false;
						} else {
							bool = true;
						}
					}
					else{
						bool = true;
					}

				} else {
					bool = true;
				}

			} catch (Exception re) {
				throw re;
				} finally {
				if (db != null)
					db.close();
			}
			return bool;
		}

		public void updatePermohonanEmel(String idFail,String idPermohonan,HttpSession session) throws Exception {

			Db db = null;
			Connection conn = null;
			String userId = (String) session.getAttribute("_ekptg_user_id");
			String sql = "";
			String namaUser = "";
			String emelUser = "nurulain.siprotech@gmail.com";
			String idhakmilikPermohonan = "";
			String noPermohonan = "";
			String idSuburusan = "";

			try {
				db = new Db();
				conn = db.getConnection();
		    	conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = "SELECT B.ID_HAKMILIKPERMOHONAN, A.NO_PERMOHONAN, C.ID_SUBURUSAN, D.NAMA, D.EMEL "
					+ " FROM TBLPERMOHONAN A,TBLPHPHAKMILIKPERMOHONAN B, TBLPFDFAIL C,TBLPHPPEMOHON D "
					+ " WHERE C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN "
					+ " AND A.ID_PEMOHON = D.ID_PEMOHON AND A.ID_PERMOHONAN = '" + idPermohonan + "'";


				ResultSet rsPermohonan = stmt.executeQuery(sql);
				if (rsPermohonan.next()){
					idhakmilikPermohonan = rsPermohonan.getString("ID_HAKMILIKPERMOHONAN");
					noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
					idSuburusan = rsPermohonan.getString("ID_SUBURUSAN");
					namaUser = rsPermohonan.getString("NAMA");
					//emelUser = rsPermohonan.getString("EMEL");
				}

				//TBLPERMOHONAN
				r.update("ID_PERMOHONAN", idPermohonan);
				r.add("ID_STATUS", "1610197");
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPERMOHONAN");
				stmt.executeUpdate(sql);

				//TBLRUJSUBURUSANSTATUSFAIL
				r = new SQLRenderer();
				long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
				r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus(idSuburusan, "1610197")); //PERMOHONAN BARU
				r.add("AKTIF", "1");
				r.add("ID_FAIL", idFail);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");

				stmt.executeUpdate(sql);

				//TBLPHPLAPORANTANAH
				r = new SQLRenderer();
				long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
				r.add("ID_LAPORANTANAH", idLaporanTanah);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("FLAG_JENISTANAH", "P"); //TANAH DIPOHON
				r.add("FLAG_LAPORAN", "1"); //LAWATAN TAPAK
				r.add("ID_HAKMILIK", idhakmilikPermohonan);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPHPLAPORANTANAH");
				stmt.executeUpdate(sql);

				//TBLPHPKERTASKERJAPENYEWAAN
				/*r = new SQLRenderer();
				long idKertasKerja = DB.getNextID("TBLPHPKERTASKERJAPENYEWAAN_SEQ");
				r.add("ID_KERTASKERJA", idKertasKerja);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("FLAG_KERTAS", "1");

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPHPKERTASKERJAPENYEWAAN");
				stmt.executeUpdate(sql);

				conn.commit();*/

				if (!"".equals(namaUser) && !"".equals(emelUser)){
					EmailConfig ef = new EmailConfig();

					String subject = "PERMOHONAN PELEPASAN TUKAR GUNA HARTA TANAH PERSEKUTUAN #" + noPermohonan;
					String kandungan = namaUser.toUpperCase() + "."
							+ "<br><br>Permohonan anda telah diterima.Sila gunakan nombor permohonan diatas sebagai rujukan."
							+ "Anda akan dimaklumkan setelah permohonan ini telah didaftarkan.";

					ef.sendByOnlineUser(emelUser, subject, kandungan);
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



		public void updateTanah(String idPermohonan, String idHakmilikAgensi, String idHakmilik,String idFail,HttpSession session) throws Exception {

			Db db = null;
			Connection conn = null;
			String userId = session.getAttribute("_ekptg_user_id").toString();
			String sql = "";
			String idHakmilikSementara ="";
			String id_hakmilikpermohonan ="";

			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP"
						+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND MOHON.ID_FAIL = '"
						+ idFail + "'";

				myLogger.info("sql getIdHakmilikPermohonanByIdFail >>>> "+sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable<String, Object> h;
				int bil = 1;
				while (rs.next()) {
					id_hakmilikpermohonan = rs.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs.getString("ID_HAKMILIKPERMOHONAN");
				}

				setMaklumatTanah(idHakmilikAgensi,idHakmilikSementara);
				if (getBeanMaklumatTanah().size() != 0) {
					Hashtable hashTanah = (Hashtable) getBeanMaklumatTanah()
							.get(0);

					r = new SQLRenderer();
					r.update("ID_HAKMILIKPERMOHONAN", id_hakmilikpermohonan);
					r.add("PEGANGAN_HAKMILIK",
							hashTanah.get("peganganHakmilik"));
					r.add("ID_NEGERI", hashTanah.get("idNegeri"));
					r.add("ID_DAERAH", hashTanah.get("idDaerah"));
					r.add("ID_MUKIM", hashTanah.get("idMukim"));
					r.add("NO_WARTA", hashTanah.get("noWarta"));
					r.add("TARIKH_WARTA",
							r.unquote("to_date('"
									+ hashTanah.get("tarikhWarta")
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


					sql = r.getSQLUpdate("TBLPHPHAKMILIK");
					myLogger.info("sql updateTanah: "+sql);
					stmt.executeUpdate(sql);

				}

				// TBLPHPHAKMILIKPERMOHONAN
				/*r.update("ID_PERMOHONAN", idPermohonan);
				r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
				r.add("ID_HAKMILIK", idHakmilik);
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPHPHAKMILIKPERMOHONAN");
				stmt.executeUpdate(sql);*/

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

		public void updateMaklumatPermohonan(String idFail,String idPermohonan,String tarikhTerima,
				String tarikhSurat, String idLuasKegunaan, String txtTujuanKegunaan,
				String idLuasTanah, String luasTanah, String luasMohon, String bakiLuas, String idLuas, String txtLuasBersamaan) throws Exception {

			Db db = null;
			Connection conn = null;
			//String userId = session.getAttribute("_ekptg_user_id").toString();
			String sql = "";
			String sql2 = "";
			String idHakmilikSementara ="";
			String id_hakmilikpermohonan ="";

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				Statement stmt2 = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();

				r.update("ID_PERMOHONAN", idPermohonan);
				r.add("ID_LUASASAL", idLuasTanah);
				r.add("LUAS_ASAL", luasTanah);
				r.add("FLAG_GUNA", idLuasKegunaan);
				if ("1".equals(idLuasKegunaan)) {
					r.add("LUAS_MHNBERSAMAAN", luasTanah);
					r.add("ID_UNITLUASBAKI", idLuasTanah);
					r.add("LUAS_BAKI", 0);
				}
				if ("2".equals(idLuasKegunaan)) {
					r.add("LUAS_MHN1", luasMohon);
					r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
					r.add("ID_UNITLUASBAKI", idLuasTanah);
					r.add("ID_LUASMHN",idLuas);
					r.add("LUAS_BAKI", bakiLuas);
				}
				r.add("CADANGAN_KEGUNAAN", txtTujuanKegunaan);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
				myLogger.info("sql updateMaklumatPermohonan >>>> "+sql);
				stmt.executeUpdate(sql);

				r2.update("ID_PERMOHONAN", idPermohonan);
				r2.add("TARIKH_SURAT", r.unquote(TS));
				r2.add("TARIKH_TERIMA", r.unquote(TT));
				r2.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql2 = r2.getSQLUpdate("TBLPERMOHONAN");
				myLogger.info("sql updateMaklumatPermohonan 2 >>>> "+sql2);
				stmt2.executeUpdate(sql2);
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


		public String getKategoriPemohonTukarGuna() throws Exception {
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
	public Vector getBeanMaklumatLampiran() {
		return beanMaklumatLampiran;
	}

	public void setBeanMaklumatLampiran(Vector beanMaklumatLampiran) {
		this.beanMaklumatLampiran = beanMaklumatLampiran;
	}
	public Vector<Hashtable<String, Object>> getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector<Hashtable<String, Object>> senaraiFail) {
		this.senaraiFail = senaraiFail;
	}
	public Vector<Hashtable<String, Object>> getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector<Hashtable<String, Object>> beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}
	public Vector<Hashtable<String, Object>> getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector<Hashtable<String, Object>> beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}
	public Vector<Hashtable<String, Object>> getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector<Hashtable<String, Object>> beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}
	public Vector<Hashtable<String, Object>> getBeanMaklumatAgensi() {
		return beanMaklumatAgensi;
	}
	public Vector<Hashtable<String, Object>> getBeanMaklumatBorangK() {
		return beanMaklumatBorangK;
	}
	public Vector<Hashtable<String, Object>> getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}

	public void setBeanMaklumatHakmilik(Vector<Hashtable<String, Object>> beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}
	public void setBeanMaklumatBorangK(Vector<Hashtable<String, Object>> beanMaklumatBorangK) {
		this.beanMaklumatBorangK = beanMaklumatBorangK;
	}

	public void setBeanMaklumatAgensi(Vector<Hashtable<String, Object>> beanMaklumatAgensi) {
		this.beanMaklumatAgensi = beanMaklumatAgensi;
	}
	public Vector<Hashtable<String, Object>> getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}
	public void setBeanMaklumatPejabat(Vector<Hashtable<String, Object>> beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}
	public Vector<Hashtable<String, Object>> getBeanMaklumatHeader() {
		return beanMaklumatHeader;
	}
	public void setBeanMaklumatHeader(Vector<Hashtable<String, Object>> beanMaklumatHeader) {
		this.beanMaklumatHeader = beanMaklumatHeader;
	}

	public Vector getBeanMaklumatTukarguna() {
		return beanMaklumatTukarguna;
	}

	public void setBeanMaklumatTukarguna(Vector beanMaklumatTukarguna) {
		this.beanMaklumatTukarguna = beanMaklumatTukarguna;
	}


}
