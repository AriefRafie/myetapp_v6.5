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

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, B.TARIKH_SURAT, B.TARIKH_TERIMA, B.NO_RUJ_SURAT, A.TAJUK_FAIL, B.TUJUAN, B.ID_PEMOHON"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B WHERE A.ID_FAIL = B.ID_FAIL AND A.ID_FAIL = '"
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
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
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
}
