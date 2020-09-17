/**
 * 
 */
package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import ekptg.intergration.XEkptgEmailSender;

/**
 * 
 *
 */
public class FrmAPBSenaraiFailData {

	static Logger myLog = Logger.getLogger(FrmAPBSenaraiFailData.class);
	private Vector<Hashtable<String,String>> senaraiFail = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatKawasanMohon = null;
	//untuk sambungan
	private Vector beanMaklumatPendaftaranSambungan = null;
	private Vector listSambungan = null;
	private Vector beanMaklumatSambungan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Vector<Hashtable<String,String>> getCarianFail(String noFail
		, String namaPemohon,String noPengenalan, String tarikhTerima, String txtNoLesen,String idStatus) 
		throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		senaraiFail = new Vector<Hashtable<String,String>>();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS,B.NO_RAYUAN,"
					+ " E.TARIKH_MULA_LESEN, E.TARIKH_TAMAT_LESEN, E.NO_LESEN, F.NAMA_NEGERI"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPBYRNSYRTKLLSNLESENAPB E, TBLRUJNEGERI F"
					+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS AND B.ID_PEMOHON = C.ID_PEMOHON "
					+ " AND C.ID_NEGERITETAP = F.ID_NEGERI AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) AND E.FLAG_AKTIF(+) = 'Y' AND A.NO_FAIL IS NOT NULL ";
			
			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
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

			// tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhTerima))
									.toUpperCase() + "'";
				}
			}

			// noLesen
			if (txtNoLesen != null) {
				if (!txtNoLesen.trim().equals("")) {
					sql = sql + " AND UPPER(E.NO_LESEN) LIKE '%' ||'"
							+ txtNoLesen.trim().toUpperCase() + "'|| '%'";
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
			myLog.info("sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				String idPermohonan = rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN");
				String statusID = rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS");
				
				h.put("bil", String.valueOf(bil));
				h.put("idFail",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noRayuan",rs.getString("NO_RAYUAN") == null ? "0" : rs.getString("NO_RAYUAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "": sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("kawasanDipohon",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));				

				String statusLesen = "";
				int bilHari = 0;
				if (statusID != null && statusID.equals("1610207")) {
					if (rs.getDate("TARIKH_TAMAT_LESEN") != null
							&& rs.getDate("TARIKH_TAMAT_LESEN").toString().length() > 0) {
						Calendar calCurrent = new GregorianCalendar();
						Date dateCurrent = new Date();
						calCurrent.setTime(dateCurrent);

						Calendar calTamat = new GregorianCalendar();
						Date dateTamat = sdf.parse(sdf.format(rs.getDate("TARIKH_TAMAT_LESEN")));
						calTamat.setTime(dateTamat);

						bilHari = daysBetween(calTamat.getTime(),calCurrent.getTime());

						if (calCurrent.getTime().after(calTamat.getTime())) {
							statusLesen = "LESEN TAMAT TEMPOH";
						} else if (calCurrent.getTime().before(calTamat.getTime()) && bilHari <= 90) {
							statusLesen = bilHari + " HARI LAGI";
						}
					}
				}
				h.put("statusLesen", statusLesen);
				//CODING UNTUK CEK TARIKH TAMAT KELULUSAN DASAR
				h.put("statusKelulusanDasar", getStatusKelulusanDasar(statusID, idPermohonan));
				senaraiFail.addElement(h);
				myLog.info("bil="+bil);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFail;
		
	}

	public void carianFail(String noFail, String namaPemohon,
			String noPengenalan, String tarikhTerima, String txtNoLesen,
			String idStatus) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS,B.NO_RAYUAN,"
					+ " E.TARIKH_MULA_LESEN, E.TARIKH_TAMAT_LESEN, E.NO_LESEN"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPBYRNSYRTKLLSNLESENAPB E"
					+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS AND B.ID_PEMOHON = C.ID_PEMOHON "
					+ " AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) AND E.FLAG_AKTIF(+) = 'Y' AND A.NO_FAIL IS NOT NULL ";
			
			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
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

			// tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhTerima))
									.toUpperCase() + "'";
				}
			}

			// noLesen
			if (txtNoLesen != null) {
				if (!txtNoLesen.trim().equals("")) {
					sql = sql + " AND UPPER(E.NO_LESEN) LIKE '%' ||'"
							+ txtNoLesen.trim().toUpperCase() + "'|| '%'";
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
			myLog.info("sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				String idPermohonan = rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN");
				h.put("idPermohonan", idPermohonan);
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("noRayuan",
						rs.getString("NO_RAYUAN") == null ? "0" : rs
								.getString("NO_RAYUAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				String statusID = rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS");
				h.put("idStatus",statusID);
				h.put("status",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));

				String statusLesen = "";
				int bilHari = 0;
//				if (rs.getString("ID_STATUS") != null
//						&& rs.getString("ID_STATUS").equals("1610207")) {
//					if (rs.getDate("TARIKH_TAMAT_LESEN") != null
//							&& rs.getDate("TARIKH_TAMAT_LESEN").toString()
//									.length() > 0) {
//						Calendar calCurrent = new GregorianCalendar();
//						Date dateCurrent = new Date();
//						calCurrent.setTime(dateCurrent);
//
//						Calendar calTamat = new GregorianCalendar();
//						Date dateTamat = sdf.parse(sdf.format(rs
//								.getDate("TARIKH_TAMAT_LESEN")));
//						calTamat.setTime(dateTamat);
//
//						bilHari = daysBetween(calTamat.getTime(),
//								calCurrent.getTime());
//
//						if (calCurrent.getTime().after(calTamat.getTime())) {
//							statusLesen = "LESEN TAMAT TEMPOH";
//						} else if (calCurrent.getTime().before(
//								calTamat.getTime())
//								&& bilHari <= 90) {
//							statusLesen = bilHari + " HARI LAGI";
//						}
//					}
//				}
				h.put("statusLesen", statusLesen);
				//CODING UNTUK CEK TARIKH TAMAT KELULUSAN DASAR
//				h.put("statusKelulusanDasar", getStatusKelulusanDasar(statusID, idPermohonan, db));
				senaraiFail.addElement(h);
				myLog.info("bil="+bil);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getStatusKelulusanDasar(String idStatus, String idPermohonan) throws Exception {
		String statusKelulusanDasar = "";
	
		Db db = null;
		String sql = "";
		try {
			if ("1615198".equals(idStatus) || "1610236".equals(idStatus)) {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT TARIKH_TAMAT_KELULUSANDASAR FROM TBLPHPBYRNSYRTKLLSNLESENAPB WHERE FLAG_AKTIF = 'Y' "
						+ " AND TARIKH_TAMAT_KELULUSANDASAR IS NOT NULL" 
						+ " AND ID_PERMOHONAN = '" + idPermohonan + "'";	
			
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);
	
					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs.getDate("TARIKH_TAMAT_KELULUSANDASAR")));
					calTamat.setTime(dateTamat);
	
					int bilHari = daysBetween(calTamat.getTime(), calCurrent.getTime());
	
					//TUKA BIL HARI DEPENDS ON BILA NOTIFICATION NAK KELUAR
					if (calCurrent.getTime().after(calTamat.getTime())) {
						statusKelulusanDasar = "KELULUSAN DASAR TAMAT TEMPOH";
					} else if (calCurrent.getTime().before(calTamat.getTime()) && bilHari <= 120) {
						statusKelulusanDasar = "TAMAT KELULUSAN DASAR : " + bilHari + " HARI LAGI";
					}
	
				}

			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return statusKelulusanDasar;
	
	}

	private String getStatusKelulusanDasar(String idStatus, String idPermohonan, Db db) {
		String statusKelulusanDasar = "";
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			if ("1615198".equals(idStatus) || "1610236".equals(idStatus)) {
				sql = "SELECT TARIKH_TAMAT_KELULUSANDASAR FROM TBLPHPBYRNSYRTKLLSNLESENAPB WHERE FLAG_AKTIF = 'Y' "
						+ " AND TARIKH_TAMAT_KELULUSANDASAR IS NOT NULL" 
						+ " AND ID_PERMOHONAN = '" + idPermohonan + "'";	
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					Calendar calCurrent = new GregorianCalendar();
					Date dateCurrent = new Date();
					calCurrent.setTime(dateCurrent);

					Calendar calTamat = new GregorianCalendar();
					Date dateTamat = sdf.parse(sdf.format(rs.getDate("TARIKH_TAMAT_KELULUSANDASAR")));
					calTamat.setTime(dateTamat);

					int bilHari = daysBetween(calTamat.getTime(), calCurrent.getTime());

					//TUKA BIL HARI DEPENDS ON BILA NOTIFICATION NAK KELUAR
					if (calCurrent.getTime().after(calTamat.getTime())) {
						statusKelulusanDasar = "KELULUSAN DASAR TAMAT TEMPOH";
					} else if (calCurrent.getTime().before(calTamat.getTime()) && bilHari <= 120) {
						statusKelulusanDasar = "TAMAT KELULUSAN DASAR : " + bilHari + " HARI LAGI";
					}
				}
			}
				
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return statusKelulusanDasar;
	}

	private int daysBetween(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
	}

	public String daftarBaru(Hashtable hash, HttpSession session)
			throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TT = "to_date('" + hash.get("tarikhTerima")
					+ "','dd/MM/yyyy')";
			String TS = "to_date('" + hash.get("tarikhSurat")
					+ "','dd/MM/yyyy')";
			
			if("1".equals(hash.get("idJenisPermohonan"))){
				// TBLPFDFAIL
				long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
				idFailString = String.valueOf(idFail);
				r.add("ID_FAIL", idFail);
				r.add("ID_URUSAN", "9");
				r.add("ID_SUBURUSAN", "57");
				r.add("ID_TARAFKESELAMATAN", "1");
				r.add("ID_SEKSYEN", "4");
				r.add("FLAG_FAIL", "1");
				r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
				r.add("TAJUK_FAIL", hash.get("perkara"));

				String noFail = "";
				noFail = generateNoFail(session);
				r.add("NO_FAIL", noFail);
				r.add("NO_FAIL_ROOT", noFail);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPFDFAIL");
				stmt.executeUpdate(sql);

				// TBLPHPPEMOHON
				r = new SQLRenderer();
				long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
				r.add("ID_PEMOHON", idPemohon);
				if ("1".equals(hash.get("idKategoriIndividu"))) {
					r.add("FLAG_INDIVIDU", "1"); //INDIVIDU
				} else {
					r.add("FLAG_INDIVIDU", "2"); //BUKAN INDIVIDU
				}
				r.add("ID_KATEGORIPEMOHON", hash.get("idKategoriPemohon"));

				if ("1".equals(hash.get("idKategoriPemohon"))) {

					r.add("NAMA", hash.get("nama"));
					r.add("ID_JENISPENGENALAN",
							hash.get("idJenisPengenalanIndividu"));
					r.add("NO_PENGENALAN", hash.get("noPengenalan"));
					r.add("PEKERJAAN", hash.get("pekerjaan"));
					r.add("ID_JANTINA", hash.get("idJantina"));
					r.add("ID_BANGSA", hash.get("idBangsa"));
					r.add("ALAMAT1_TETAP", hash.get("alamat1"));
					r.add("ALAMAT2_TETAP", hash.get("alamat2"));
					r.add("ALAMAT3_TETAP", hash.get("alamat3"));
					r.add("POSKOD_TETAP", hash.get("poskod"));
					r.add("ID_BANDARTETAP", hash.get("idBandar"));
					r.add("ID_NEGERITETAP", hash.get("idNegeri"));
					r.add("NO_TEL_RUMAH", hash.get("noTel"));
					r.add("NO_TEL_BIMBIT", hash.get("noTelBim"));
					r.add("NO_FAX", hash.get("noFax"));
					r.add("EMEL", hash.get("emel"));

				} else if ("2".equals(hash.get("idKategoriPemohon"))
						|| "10".equals(hash.get("idKategoriPemohon"))) {

					r.add("NAMA", hash.get("namaSykt"));
					r.add("NO_PENGENALAN", hash.get("noPengenalanSykt"));
					r.add("PEKERJAAN", hash.get("pekerjaanSykt"));
					r.add("ALAMAT1_TETAP", hash.get("alamat1Sykt"));
					r.add("ALAMAT2_TETAP", hash.get("alamat2Sykt"));
					r.add("ALAMAT3_TETAP", hash.get("alamat3Sykt"));
					r.add("POSKOD_TETAP", hash.get("poskodSykt"));
					r.add("ID_BANDARTETAP", hash.get("idBandarSykt"));
					r.add("ID_NEGERITETAP", hash.get("idNegeriSykt"));
					r.add("NO_TEL_PEJABAT", hash.get("noTelSykt"));
					r.add("NO_FAX", hash.get("noFaxSykt"));
					r.add("EMEL", hash.get("emelSykt"));

				} else if ("9".equals(hash.get("idKategoriPemohon"))) {
					r.add("NAMA", hash.get("namaSykt"));
					r.add("PEKERJAAN", hash.get("pekerjaanSykt"));
					r.add("ALAMAT1_TETAP", hash.get("alamat1Sykt"));
					r.add("ALAMAT2_TETAP", hash.get("alamat2Sykt"));
					r.add("ALAMAT3_TETAP", hash.get("alamat3Sykt"));
					r.add("POSKOD_TETAP", hash.get("poskodSykt"));
					r.add("ID_BANDARTETAP", hash.get("idBandarSykt"));
					r.add("ID_NEGERITETAP", hash.get("idNegeriSykt"));
					r.add("NO_TEL_PEJABAT", hash.get("noTelSykt"));
					r.add("NO_FAX", hash.get("noFaxSykt"));
					r.add("EMEL", hash.get("emelSykt"));
					
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
				r.add("ID_STATUS", "1610198");
				r.add("TARIKH_SURAT", r.unquote(TS));
				r.add("TARIKH_TERIMA", r.unquote(TT));
				r.add("NO_RUJ_SURAT", hash.get("noRujSurat"));
				r.add("TUJUAN", hash.get("perkara"));

				r.add("FLAG_AKTIF", 1);
				r.add("NO_SUBJAKET", 0);
				r.add("NO_JILID", 0);
				r.add("NO_RAYUAN", 0);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPERMOHONAN");
				stmt.executeUpdate(sql);

				// TBLPHPPMOHONNJDUALPERTAMA
				r = new SQLRenderer();
				long idJadualPertama = DB.getNextID("TBLPHPPMOHONNJDUALPERTAMA_SEQ");
				r.add("ID_PHPPMOHONNJDUALPERTAMA", idJadualPertama);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("FLAG_RAYUAN", 0);
				r.add("FLAG_LUAR_PERAIRANNEGERI", hash.get("idFlagLuar"));
				r.add("ID_NEGERI_PERAIRAN", hash.get("idNegeriPerairan"));
				r.add("ID_JENISPERMOHONAN", hash.get("idJenisPermohonan"));
				r.add("ID_JENIS_LESEN", hash.get("idJenisLesen"));

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPHPPMOHONNJDUALPERTAMA");
				stmt.executeUpdate(sql);

				// TBLRUJSUBURUSANSTATUSFAIL
				r = new SQLRenderer();
				long idSuburusanstatusfail = DB
						.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
				r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610198")); // MAKLUMAT PERMOHONAN
				r.add("AKTIF", "1");
				r.add("ID_FAIL", idFail);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
				stmt.executeUpdate(sql);
				
				// TBLPHPAKAUN - FI PERMOHONAN
				r = new SQLRenderer();
				long idAkaun = DB.getNextID("TBLPHPAKAUN_SEQ");
				r.add("ID_AKAUN", idAkaun);
				r.add("ID_PHPPMOHONNJDUALPERTAMA", idJadualPertama);
				if (!"".equals(hash.get("tarikhTerima"))) {
					r.add("TARIKH", r.unquote(TT));
				}
				r.add("ID_JENISBAYARAN", "11");
				r.add("DEBIT", 1000);
				r.add("CATATAN", "FI PERMOHONAN");
				r.add("ID_JENISTRANSAKSI", "1"); // CAJ
				r.add("FLAG_DEPOSIT", "T");
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPHPAKAUN");
				stmt.executeUpdate(sql);			

				conn.commit();
				
				AuditTrail.logActivity("1610198", "4", null, session, "INS",
						"FAIL APB PERMOHONAN BARU [" + noFail + "] DIDAFTARKAN");
				
			} else {
				
			}

			

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
		session.setAttribute("idFail", idFailString);
		return idFailString;
	}

	public String generateNoFail(HttpSession session) throws Exception {
		String noFail = "";
		noFail = "JKPTG(S)/BPHP/8-2 SK " + File.getSeqNoP(session, 4, 9);

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
				return rs.getString("ID_SUBURUSANSTATUS").toString();
			} else {
				return "";
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

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN,B.NO_PERMOHONAN,C.ID_JENISPERMOHONAN,C.ID_PERMOHONANLAMA, B.TARIKH_SURAT, B.TARIKH_TERIMA, B.NO_RUJ_SURAT, A.TAJUK_FAIL, B.TUJUAN, B.ID_PEMOHON"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPMOHONNJDUALPERTAMA C WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			ResultSet rsi=null;
			ResultSet rsj=null;
					
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN") == null ? "" : rs
								.getString("NO_PERMOHONAN").toUpperCase());
				h.put("jenisPermohonan",
						rs.getString("ID_JENISPERMOHONAN") == null ? "" : rs
								.getString("ID_JENISPERMOHONAN").toUpperCase());
				h.put("idPermohonanLama",
						rs.getString("ID_PERMOHONANLAMA") == null ? " - " : rs
								.getString("ID_PERMOHONANLAMA").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs
						.getString("ID_PEMOHON").toUpperCase());
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("noRujSurat", rs.getString("NO_RUJ_SURAT") == null ? "" : rs
						.getString("NO_RUJ_SURAT").toUpperCase());
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("perkara2", rs.getString("TAJUK_FAIL") == null ? "" : "PEMBAHARUAN " + rs
						.getString("TAJUK_FAIL").toUpperCase());
				//dapatkan no permohonan lama if ada
				if(rs.getString("ID_PERMOHONANLAMA") != null){
					String idPermohonanLama=rs.getString("ID_PERMOHONANLAMA").toUpperCase();
					sql = "SELECT B.ID_FAIL, B.ID_PERMOHONAN, B.NO_PERMOHONAN"
						+ " FROM TBLPERMOHONAN B WHERE B.ID_PERMOHONAN = '"+ idPermohonanLama + "'";
					rsi = stmt.executeQuery(sql);
					while (rsi.next()) {
						h.put("noPermohonanLama", rsi.getString("NO_PERMOHONAN") == null ? " - " : rsi
								.getString("NO_PERMOHONAN").toUpperCase());
						sql = "SELECT B.ID_FAIL, B.NO_FAIL"
							+ " FROM TBLPFDFAIL B WHERE B.ID_FAIL = '"+ rsi.getString("ID_FAIL").toUpperCase() + "'";
						rsj = stmt.executeQuery(sql);
						while (rsj.next()) {
							h.put("noFailLama", rsj.getString("NO_FAIL") == null ? " - " : rsj
									.getString("NO_FAIL").toUpperCase());
						}
					}
				}
				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setBeanMaklumatKawasanMohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKawasanMohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, C.ID_NEGERI_PERAIRAN, C.FLAG_LUAR_PERAIRANNEGERI, C.ID_JENIS_LESEN"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPMOHONNJDUALPERTAMA C, TBLRUJNEGERI D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_NEGERI_PERAIRAN = D.ID_NEGERI"
					+ " AND A.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idFlagLuar", rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? "" : rs
						.getString("FLAG_LUAR_PERAIRANNEGERI").toUpperCase());
				h.put("idNegeriPerairan", rs.getString("ID_NEGERI_PERAIRAN") == null ? "" : rs
						.getString("ID_NEGERI_PERAIRAN").toUpperCase());
				h.put("idJenisLesen", rs.getString("ID_JENIS_LESEN") == null ? "" : rs
						.getString("ID_JENIS_LESEN").toUpperCase());
				beanMaklumatKawasanMohon.addElement(h);
				bil++;
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
			SQLRenderer r = new SQLRenderer();
			
			r.add("C.ID_PEMOHON");
			r.add("C.FLAG_INDIVIDU");
			r.add("C.ID_KATEGORIPEMOHON");
			r.add("C.NAMA");
			r.add("C.ID_JENISPENGENALAN");
			r.add("C.NO_PENGENALAN");
			r.add("C.PEKERJAAN");
			r.add("C.ID_JANTINA");
			r.add("C.ID_BANGSA");
			r.add("C.ALAMAT1_TETAP");
			r.add("C.ALAMAT2_TETAP");
			r.add("C.ALAMAT3_TETAP");
			r.add("C.POSKOD_TETAP");
			r.add("C.ID_NEGERITETAP");
			r.add("C.ID_BANDARTETAP");
			r.add("C.NO_TEL_RUMAH");
			r.add("C.NO_TEL_PEJABAT");
			r.add("C.NO_TEL_BIMBIT");
			r.add("C.NO_FAX");
			r.add("C.EMEL");

			r.add("A.ID_FAIL", r.unquote("B.ID_FAIL"));
			r.add("B.ID_PEMOHON", r.unquote("C.ID_PEMOHON"));
			r.add("A.ID_FAIL", idFail);

			sql = r.getSQLSelect("TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C");
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("getBeanMaklumatPemohon >> " + sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPemohon", rs.getString("ID_PEMOHON"));
				h.put("idKategoriIndividu",
						rs.getString("FLAG_INDIVIDU") == null ? "" : rs
								.getString("FLAG_INDIVIDU"));
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999"
								: rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaan",
						rs.getString("PEKERJAAN") == null ? "" : rs
								.getString("PEKERJAAN"));
				h.put("idJantina", rs.getString("ID_JANTINA") == null ? "99999"
						: rs.getString("ID_JANTINA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999"
						: rs.getString("ID_BANGSA"));
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
				h.put("noTel",
						rs.getString("NO_TEL_RUMAH") == null ? "" : rs
								.getString("NO_TEL_RUMAH"));
				h.put("noTelBim", rs.getString("NO_TEL_BIMBIT") == null ? ""
						: rs.getString("NO_TEL_BIMBIT"));
				h.put("noFax",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("namaSykt",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("noPengenalanSykt",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaanSykt", rs.getString("PEKERJAAN") == null ? ""
						: rs.getString("PEKERJAAN"));
				h.put("alamat1Sykt", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2Sykt", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3Sykt", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskodSykt", rs.getString("POSKOD_TETAP") == null ? ""
						: rs.getString("POSKOD_TETAP"));
				h.put("idNegeriSykt",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs
								.getString("ID_NEGERITETAP"));
				h.put("idBandarSykt",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs
								.getString("ID_BANDARTETAP"));
				h.put("noTelSykt", rs.getString("NO_TEL_PEJABAT") == null ? ""
						: rs.getString("NO_TEL_PEJABAT"));
				h.put("noFaxSykt",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("emelSykt",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));

				beanMaklumatPemohon.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdFailByNoFail(String noFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_SUBURUSAN = '57'" 
					+ "AND ID_URUSAN = '9' AND UPPER(NO_FAIL) = '"
					+ noFail.trim().toUpperCase() + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getNoFailByNoPendaftaran(String noPendaftaranSy)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT MAX(A.ID_FAIL) FROM TBLPERMOHONAN A, TBLPHPPEMOHON B, TBLPFDFAIL C"
					+ " WHERE A.ID_PEMOHON = B.ID_PEMOHON AND C.ID_URUSAN = '9' AND C.ID_SUBURUSAN = '57'"
					+ " AND A.ID_FAIL = C.ID_FAIL AND UPPER(B.NO_PENGENALAN) = '"
					+ noPendaftaranSy.trim().toUpperCase() + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("MAX(A.ID_FAIL)") == null ? "" : rs
						.getString("MAX(A.ID_FAIL)").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNamaNegeriByIdNegeri(String idNegeri)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '" + idNegeri + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toString();

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
	
	public Vector getBeanMaklumatKawasanMohon() {
		return beanMaklumatKawasanMohon;
	}

	public void setBeanMaklumatKawasanMohon(Vector beanMaklumatKawasanMohon) {
		this.beanMaklumatKawasanMohon = beanMaklumatKawasanMohon;
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}
	
	public Vector<Hashtable<String,String>> getCarianFailSyarikat(String noFail,String noLesen) 
			throws Exception {

			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String sql = "";
			senaraiFail = new Vector<Hashtable<String,String>>();

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS,B.NO_RAYUAN,"
						+ " E.TARIKH_MULA_LESEN, E.TARIKH_TAMAT_LESEN, E.NO_LESEN, F.NAMA_NEGERI"
						+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPBYRNSYRTKLLSNLESENAPB E, TBLRUJNEGERI F"
						+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS AND B.ID_PEMOHON = C.ID_PEMOHON "
						+ " AND C.ID_NEGERITETAP = F.ID_NEGERI AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) AND E.FLAG_AKTIF(+) = 'Y' AND A.NO_FAIL IS NOT NULL";
				
				// noFail
				if (noFail != null) {
					if (!noFail.trim().equals("")) {
						sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
								+ noFail.trim().toUpperCase() + "'|| '%'";
					}
				}

				// noLesen
				if (noLesen != null) {
					if (!noLesen.trim().equals("")) {
						sql = sql + " AND UPPER(E.NO_LESEN) LIKE '%' ||'"
								+ noLesen.trim().toUpperCase() + "'|| '%'";
					}
				}
				
				sql = sql + " ORDER BY B.TARIKH_TERIMA DESC NULLS LAST ";
				myLog.info("sql="+sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable<String,String> h;
				int bil = 1;
				while (rs.next()) {
					h = new Hashtable<String,String>();
					String idPermohonan = rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN");
					String statusID = rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS");

					h.put("bil", String.valueOf(bil));
					h.put("idFail",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
					h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
					h.put("noRayuan",rs.getString("NO_RAYUAN") == null ? "0" : rs.getString("NO_RAYUAN"));
					h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "": sdf.format(rs.getDate("TARIKH_TERIMA")));
					h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
					h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					h.put("status",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					h.put("kawasanDipohon",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
					h.put("noLesen", rs.getString("NO_LESEN") == null ? "" : rs.getString("NO_LESEN").toUpperCase());

					String statusLesen = "";
					int bilHari = 0;
					if (statusID != null && statusID.equals("1610207")) {
						if (rs.getDate("TARIKH_TAMAT_LESEN") != null
								&& rs.getDate("TARIKH_TAMAT_LESEN").toString().length() > 0) {
							Calendar calCurrent = new GregorianCalendar();
							Date dateCurrent = new Date();
							calCurrent.setTime(dateCurrent);

							Calendar calTamat = new GregorianCalendar();
							Date dateTamat = sdf.parse(sdf.format(rs.getDate("TARIKH_TAMAT_LESEN")));
							calTamat.setTime(dateTamat);

							bilHari = daysBetween(calTamat.getTime(),calCurrent.getTime());

							if (calCurrent.getTime().after(calTamat.getTime())) {
								statusLesen = "LESEN TAMAT TEMPOH";
							} else if (calCurrent.getTime().before(calTamat.getTime()) && bilHari <= 90) {
								statusLesen = bilHari + " HARI LAGI";
							}
						}
					}
					h.put("statusLesen", statusLesen);
					//CODING UNTUK CEK TARIKH TAMAT KELULUSAN DASAR
					h.put("statusKelulusanDasar", getStatusKelulusanDasar(statusID, idPermohonan));
					senaraiFail.addElement(h);
					myLog.info("bil="+bil);
					bil++;
				}

			} finally {
				if (db != null)
					db.close();
			}
			return senaraiFail;
			
		}
	
	public Vector<Hashtable<String,String>> getCarianFailOnline(String noPermohonan,String tarikhTerima) 
			throws Exception {

			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String sql = "";
			senaraiFail = new Vector<Hashtable<String,String>>();

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN,B.NO_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS,B.NO_RAYUAN,"
						+ " E.TARIKH_MULA_LESEN, E.TARIKH_TAMAT_LESEN, E.NO_LESEN, F.NAMA_NEGERI"
						+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPBYRNSYRTKLLSNLESENAPB E, TBLRUJNEGERI F"
						+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS AND B.ID_PEMOHON = C.ID_PEMOHON "
						+ " AND C.ID_NEGERITETAP = F.ID_NEGERI AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) AND E.FLAG_AKTIF(+) = 'Y' AND A.NO_FAIL IS NULL ";
				
				// noFail
				if (noPermohonan != null) {
					if (!noPermohonan.trim().equals("")) {
						sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%' ||'"
								+ noPermohonan.trim().toUpperCase() + "'|| '%'";
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

				sql = sql + " ORDER BY B.TARIKH_TERIMA DESC NULLS LAST ";
				myLog.info("sql="+sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable<String,String> h;
				int bil = 1;
				while (rs.next()) {
					h = new Hashtable<String,String>();
					String idPermohonan = rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN");
					String statusID = rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS");
					
					h.put("bil", String.valueOf(bil));
					h.put("idFail",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
					h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN"));
					h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
					h.put("noRayuan",rs.getString("NO_RAYUAN") == null ? "0" : rs.getString("NO_RAYUAN"));
					h.put("tarikhTerima", rs.getString("TARIKH_TERIMA") == null ? "": sdf.format(rs.getDate("TARIKH_TERIMA")));
					h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
					h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					h.put("status",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					h.put("kawasanDipohon",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));				

					String statusLesen = "";
					int bilHari = 0;
					if (statusID != null && statusID.equals("1610207")) {
						if (rs.getDate("TARIKH_TAMAT_LESEN") != null
								&& rs.getDate("TARIKH_TAMAT_LESEN").toString().length() > 0) {
							Calendar calCurrent = new GregorianCalendar();
							Date dateCurrent = new Date();
							calCurrent.setTime(dateCurrent);

							Calendar calTamat = new GregorianCalendar();
							Date dateTamat = sdf.parse(sdf.format(rs.getDate("TARIKH_TAMAT_LESEN")));
							calTamat.setTime(dateTamat);

							bilHari = daysBetween(calTamat.getTime(),calCurrent.getTime());

							if (calCurrent.getTime().after(calTamat.getTime())) {
								statusLesen = "LESEN TAMAT TEMPOH";
							} else if (calCurrent.getTime().before(calTamat.getTime()) && bilHari <= 90) {
								statusLesen = bilHari + " HARI LAGI";
							}
						}
					}
					h.put("statusLesen", statusLesen);
					//CODING UNTUK CEK TARIKH TAMAT KELULUSAN DASAR
					h.put("statusKelulusanDasar", getStatusKelulusanDasar(statusID, idPermohonan));
					senaraiFail.addElement(h);
					myLog.info("bil="+bil);
					bil++;
				}

			} finally {
				if (db != null)
					db.close();
			}
			return senaraiFail;
			
		}
	
	public void updateDaftarOnline(String idFail, String idPermohonan,
			String txtPerkara, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idSuburusan = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT A.ID_SUBURUSAN FROM TBLPFDFAIL A"
					+ " WHERE A.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idSuburusan = rs.getString("ID_SUBURUSAN").toString();
			} else {
				idSuburusan = "";
			}

			// TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			String noFail = "";
			noFail = generateNoFailAPB();
			r.add("NO_FAIL", noFail);
			r.add("NO_FAIL_ROOT", noFail);
			r.add("TAJUK_FAIL", txtPerkara);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TUJUAN", txtPerkara);
			r.add("ID_STATUS", "1610198");
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			// MAKLUMAT PERMOHONAN
			r.add("ID_SUBURUSANSTATUS",getIdSuburusanstatus(idSuburusan, "1610198")); 
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			String idJadualPertama="";
			Date tarikhTerima=null;
			
			//dapatkan idjadualpertama
			sql = "SELECT ID_PHPPMOHONNJDUALPERTAMA FROM TBLPHPPMOHONNJDUALPERTAMA WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rss = stmt.executeQuery(sql);
			if (rss.next()) {
				idJadualPertama=rss.getString("ID_PHPPMOHONNJDUALPERTAMA") == null ? "" : rss.getString("ID_PHPPMOHONNJDUALPERTAMA").toString();
			}
			
			//dapatkan tarikh terima
			sql = "SELECT TARIKH_TERIMA FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rsTarikh = stmt.executeQuery(sql);
			if (rsTarikh.next()) {
				//tarikhTerima=rsTarikh.getDate("TARIKH_TERIMA") == null ? "": sdf.format(rsTarikh.getDate("TARIKH_TERIMA"));
				tarikhTerima=rsTarikh.getDate("TARIKH_TERIMA");
			}
			
			// TBLPHPAKAUN - FI PERMOHONAN
			r = new SQLRenderer();
			long idAkaun = DB.getNextID("TBLPHPAKAUN_SEQ");
			r.add("ID_AKAUN", idAkaun);
			r.add("ID_PHPPMOHONNJDUALPERTAMA", idJadualPertama);
			//r.add("TARIKH", r.unquote(tarikhTerima));
			r.add("TARIKH", tarikhTerima);
			r.add("ID_JENISBAYARAN", "11");
			r.add("DEBIT", 1000);
			r.add("CATATAN", "FI PERMOHONAN");
			r.add("ID_JENISTRANSAKSI", "1"); // CAJ
			r.add("FLAG_DEPOSIT", "T");
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPAKAUN");
			stmt.executeUpdate(sql);
			
			conn.commit();

			sql = "SELECT B.NO_PERMOHONAN, A.NAMA, A.EMEL FROM TBLPHPPEMOHON A, TBLPERMOHONAN B"
					+ " WHERE A.ID_PEMOHON = B.ID_PEMOHON AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rsUser = stmt.executeQuery(sql);

			if (rsUser.next()) {
				if (!"".equals(rsUser.getString("NAMA"))
						&& !"".equals(rsUser.getString("EMEL"))) {
					XEkptgEmailSender email = XEkptgEmailSender.getInstance();
					email.FROM = "etapp_webmaster@kptg.gov.my";
					//email.RECIEPIENT = rsUser.getString("EMEL");
					email.RECIEPIENT = "jojai@yopmail.com";
					email.SUBJECT = "PERMOHONAN LESEN AKTA PELANTAR BENUA #"
							+ rsUser.getString("NO_PERMOHONAN");
					email.MESSAGE = rsUser.getString("NAMA").toUpperCase()
							+ "."
							+ "<br><br>Merujuk kepada No. Permohonan diatas. Permohonan ini telah didaftarkan dan"
							+ "No Fail yang dijana ialah " + noFail + "."
							+ "<br><br>Terima Kasih.";
					email.sendEmail();
				}
			}

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
	public String generateNoFailAPB() throws Exception {
		String noFail = "";
		noFail = "JKPTG(S)/SPHP/8-2 SK " + File.getSeqNo(4, 9);
		return noFail;
	}
	
	public void simpanSambungan(String idFail,
			String idPermohonan, String tarikhTerima,String tarikhSurat, String idFailLama, String idPermohonanLama, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();

		Hashtable h;
		listSambungan = new Vector();

		String sql = "";
		String noFail = "";
		String perkara = "";
		String idPemohon = "";
		String noFailRoot = "";
		String noFailSambungan = "";
		String idSuburusan = "";
		String idFailSambunganString = "";
		String idPermohonanBaruString = "";
		String noSubJaket = "";
		String idTempoh = "";
		String idKaitanTujuan = "";
		String tujuanPengambilan = "";
		String tempoh = "";
		String pengalaman = "";
		String idFlagLuar = "";
		String luas = "";
		String idLuas = "";
		String lokasi = "";
		String idNegeri = "";
		String nama = "";
		String idUrusan = "";
		String labelTitik = "";
		String darjahU = "";
		String minitU = "";
		String saatU = "";
		String darjahT = "";
		String minitT = "";
		String saatT = "";
		String namaPakar = "";
		String kelayakan = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";
			
			// UPDATE PERMOHONAN LAMA DI TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_AKTIF", "0");
			r.add("TARIKH_SURAT_SAMBUNG", r.unquote(TS));
			r.add("TARIKH_MULA_SAMBUNG", r.unquote(TT));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// GET DATA LAMA UNTUK CREATE REKOD BARU

			// TBLPFDFAIL
			sql = "SELECT NO_FAIL,ID_SUBURUSAN,ID_URUSAN FROM TBLPFDFAIL WHERE"
					+ " NO_FAIL = '" + idFailLama + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				noFailRoot = rs.getString("NO_FAIL") == null ? ""
						: (String) rs.getString("NO_FAIL");
				idSuburusan = rs.getString("ID_SUBURUSAN") == null ? ""
						: (String) rs.getString("ID_SUBURUSAN");
				idUrusan = rs.getString("ID_URUSAN") == null ? "" : (String) rs
						.getString("ID_URUSAN");
			}

			// TBLPHPPMOHONNJDUALPERTAMA
			sql = "SELECT ID_KAITANTUJUAN,TUJUAN_PENGAMBILAN,TEMPOH_DIPOHON,ID_TEMPOH,TEMPOH_DIPOHON,"
					+ "PENGALAMAN,FLAG_LUAR_PERAIRANNEGERI,LUAS_DIPOHON,ID_UNITLUAS,LOKASI_PERMOHONAN,ID_NEGERI_PERAIRAN FROM TBLPHPPMOHONNJDUALPERTAMA WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonanLama + "'";
			ResultSet rs1 = stmt.executeQuery(sql);
			if (rs1.next()) {
				idKaitanTujuan = rs1.getString("ID_KAITANTUJUAN") == null ? ""
						: (String) rs1.getString("ID_KAITANTUJUAN");
				tujuanPengambilan = rs1.getString("TUJUAN_PENGAMBILAN") == null ? ""
						: (String) rs1.getString("TUJUAN_PENGAMBILAN");
				tempoh = rs1.getString("TEMPOH_DIPOHON") == null ? ""
						: (String) rs1.getString("TEMPOH_DIPOHON");
				idTempoh = rs1.getString("ID_TEMPOH") == null ? ""
						: (String) rs1.getString("ID_TEMPOH");
				tempoh = rs1.getString("TEMPOH_DIPOHON") == null ? ""
						: (String) rs1.getString("TEMPOH_DIPOHON");
				pengalaman = rs1.getString("PENGALAMAN") == null ? ""
						: (String) rs1.getString("PENGALAMAN");
				idFlagLuar = rs1.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? ""
						: (String) rs1.getString("FLAG_LUAR_PERAIRANNEGERI");
				luas = rs1.getString("LUAS_DIPOHON") == null ? ""
						: (String) rs1.getString("LUAS_DIPOHON");
				idLuas = rs1.getString("ID_UNITLUAS") == null ? ""
						: (String) rs1.getString("ID_UNITLUAS");
				lokasi = rs1.getString("LOKASI_PERMOHONAN") == null ? ""
						: (String) rs1.getString("LOKASI_PERMOHONAN");
				idNegeri = rs1.getString("ID_NEGERI_PERAIRAN") == null ? ""
						: (String) rs1.getString("ID_NEGERI_PERAIRAN");
			}

			// TBLPERMOHONAN
			sql = "SELECT ID_PEMOHON, NO_SUBJAKET,TUJUAN FROM TBLPERMOHONAN WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonanLama + "'";
			ResultSet rs2 = stmt.executeQuery(sql);
			if (rs2.next()) {
				noSubJaket = rs2.getString("NO_SUBJAKET") == null ? "0"
						: (String) rs2.getString("NO_SUBJAKET");
				perkara = rs2.getString("TUJUAN") == null ? "0" : (String) rs2
						.getString("TUJUAN");
				idPemohon = rs2.getString("ID_PEMOHON") == null ? ""
						: (String) rs2.getString("ID_PEMOHON");
			}

			int subJaket = Integer.parseInt(noSubJaket);
			subJaket++;
			noFailSambungan = noFailRoot + "-SJ(" + subJaket + ")";

			// CREATE REKOD BARU UNTUK SAMBUNGAN

			// TBLPFDFAIL
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			//long idFailBaru = DB.getNextID("TBLPFDFAIL_SEQ");
			//idFailSambunganString = String.valueOf(idFailBaru);
			//r.add("ID_FAIL", idFailBaru);
			r.add("ID_URUSAN", idUrusan);
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", perkara);
			r.add("NO_FAIL", noFailSambungan);
			r.add("NO_FAIL_ROOT", noFailRoot);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			//long idPermohonanBaru = DB.getNextID("TBLPERMOHONAN_SEQ");
			//idPermohonanBaruString = String.valueOf(idPermohonanBaru);
			//r.add("ID_PERMOHONAN", idPermohonanBaru);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("TUJUAN", perkara);
			r.add("NO_SUBJAKET", subJaket);
			r.add("NO_RAYUAN", 0);
			r.add("FLAG_AKTIF", "1");
			r.add("ID_STATUS", "1610199"); // JABATAN TEKNIKAL
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPMOHONNJDUALPERTAMA
			//r = new SQLRenderer();
			//long id = DB.getNextID("TBLPHPPMOHONNJDUALPERTAMA_SEQ");
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			//r.add("ID_PHPPMOHONNJDUALPERTAMA", id);
			r.add("FLAG_SAMBUNGAN", "0");
			//r.add("ID_PERMOHONAN", idPermohonanBaru);
			r.add("ID_JENISTUJUAN", 3);
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
			r.add("TUJUAN_PENGAMBILAN", tujuanPengambilan);
			r.add("TEMPOH_DIPOHON", tempoh);
			r.add("ID_TEMPOH", idTempoh);
			r.add("PENGALAMAN", pengalaman);
			r.add("FLAG_LUAR_PERAIRANNEGERI", idFlagLuar);
			r.add("LUAS_DIPOHON", luas);
			r.add("ID_UNITLUAS", idLuas);
			r.add("LOKASI_PERMOHONAN", lokasi);
			r.add("ID_NEGERI_PERAIRAN", idNegeri);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPPMOHONNJDUALPERTAMA");
			System.out.println(sql);
			stmt.executeUpdate(sql);
			;

			// -----------COPY
			// LISTING--------------------------------------------------------------------------------------

			// TBLPHPPROJEKLESENAPB
			sql = "SELECT ID_PROJEKLESENAPB FROM TBLPHPPROJEKLESENAPB WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonanLama + "'";

			ResultSet rs3 = stmt.executeQuery(sql);
			while (rs3.next()) {
				insertIntoTBLPHPPROJEKLESENAPB(
						rs3.getString("ID_PROJEKLESENAPB").toString(),
						idPermohonan, userId);
			}

			// TBLPHPKOORDINATPERMOHONAN
			sql = "SELECT ID_KOORDINATPERMOHONAN FROM TBLPHPKOORDINATPERMOHONAN WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonanLama + "'";

			ResultSet rs4 = stmt.executeQuery(sql);
			while (rs4.next()) {
				insertIntoTBLPHPKOORDINATPERMOHONAN(
						rs4.getString("ID_KOORDINATPERMOHONAN").toString(),
						idPermohonan, userId);

			}

			// TBLPHPMAKLUMATPAKAR
			sql = "SELECT ID_MAKLUMATPAKAR FROM TBLPHPMAKLUMATPAKAR WHERE"
					+ " ID_PERMOHONAN = '" + idPermohonanLama + "'";
			ResultSet rs5 = stmt.executeQuery(sql);
			while (rs5.next()) {
				insertIntoTBLPHPMAKLUMATPAKAR(rs5.getString("ID_MAKLUMATPAKAR")
						.toString(), idPermohonan, userId);
			}

			// -----------END OF COPY
			// LISTING------------------------------------------------------------------------------------

			//TBLRUJSUBURUSANSTATUSFAIL
			//DEACTIVEKAN REKOD LAMA
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonanLama);
			r.update("AKTIF", "1");
			r.add("AKTIF", "0");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			//TBLRUJSUBURUSANSTATUSFAIL
			//CREATE REKOD BARU
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610199")); // JABATAN																// TEKNIKAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
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
	
	private static void insertIntoTBLPHPPROJEKLESENAPB(
			String idProjekLesenAPBOld, String idPermohonanBaru, String userId)
			throws Exception {
		String sql = "";
		String sqlInsert = "";
		Db db = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPPROJEKLESENAPB WHERE ID_PROJEKLESENAPB = '"
					+ idProjekLesenAPBOld + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPPROJEKLESENAPB
				long idProjek = DB.getNextID("TBLPHPPROJEKLESENAPB_SEQ");
				r.add("ID_PROJEKLESENAPB", idProjek);
				r.add("ID_PERMOHONAN", idPermohonanBaru);
				r.add("NAMA_PROJEK", rs.getString("NAMA_PROJEK") == null ? ""
						: rs.getString("NAMA_PROJEK"));
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPHPPROJEKLESENAPB");
				stmt.executeUpdate(sqlInsert);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static void insertIntoTBLPHPKOORDINATPERMOHONAN(
			String idKoordinatOld, String idPermohonanBaru, String userId)
			throws Exception {
		Db db = null;
		String sql = "";
		String sqlInsert = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPKOORDINATPERMOHONAN WHERE ID_KOORDINATPERMOHONAN = '"
					+ idKoordinatOld + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPKOORDINATPERMOHONAN
				long idKoordinat = DB
						.getNextID("TBLPHPKOORDINATPERMOHONAN_SEQ");
				r.add("ID_KOORDINATPERMOHONAN", idKoordinat);
				r.add("ID_PERMOHONAN", idPermohonanBaru);
				r.add("LABEL_TITIK", rs.getString("LABEL_TITIK") == null ? ""
						: rs.getString("LABEL_TITIK"));
				r.add("DARJAH_U",
						rs.getString("DARJAH_U") == null ? "" : rs
								.getString("DARJAH_U"));
				r.add("MINIT_U",
						rs.getString("MINIT_U") == null ? "" : rs
								.getString("MINIT_U"));
				r.add("SAAT_U",
						rs.getString("SAAT_U") == null ? "" : rs
								.getString("SAAT_U"));
				r.add("DARJAH_T",
						rs.getString("DARJAH_T") == null ? "" : rs
								.getString("DARJAH_T"));
				r.add("MINIT_T",
						rs.getString("MINIT_T") == null ? "" : rs
								.getString("MINIT_T"));
				r.add("SAAT_T",
						rs.getString("SAAT_T") == null ? "" : rs
								.getString("SAAT_T"));

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPHPKOORDINATPERMOHONAN");
				stmt.executeUpdate(sqlInsert);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private static void insertIntoTBLPHPMAKLUMATPAKAR(String idPakarOld,
			String idPermohonanBaru, String userId) throws Exception {
		String sql = "";
		String sqlInsert = "";
		Db db = null;

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPMAKLUMATPAKAR WHERE ID_MAKLUMATPAKAR = '"
					+ idPakarOld + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SQLRenderer r = new SQLRenderer();

				// TBLPHPMAKLUMATPAKAR
				long idPakar = DB.getNextID("TBLPHPMAKLUMATPAKAR_SEQ");
				r.add("ID_MAKLUMATPAKAR", idPakar);
				r.add("ID_PERMOHONAN", idPermohonanBaru);
				r.add("NAMA",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				r.add("KELAYAKAN",
						rs.getString("KELAYAKAN") == null ? "" : rs
								.getString("KELAYAKAN"));

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPHPMAKLUMATPAKAR");
				stmt.executeUpdate(sqlInsert);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

}
