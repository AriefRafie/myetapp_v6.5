/**
 *
 */
package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.intergration.XEkptgEmailSender;
import ekptg.view.ppk.PendaftaranCheck;

public class FrmPrmhnnSek8InternalData {
	static Logger myLogger = Logger.getLogger(PendaftaranCheck.class);
	private Vector list = new Vector();
	private Vector listSimati = new Vector();
	private Vector listSimati_check = new Vector();

	private Vector listPemohon = new Vector();
	private Vector listidPermohonansimati = new Vector();
	private Vector listPemohonOB = new Vector();
	private Vector listPeguam = new Vector();
	private Vector listCheckPeguam17 = new Vector();
	private Vector listcheckpemohonwaris = new Vector();
	private Vector listKeputusan = new Vector();
	private Vector listWaris = new Vector();
	private Vector listWarisDulu = new Vector();
	private Vector listHta = new Vector();
	private Vector listHtath = new Vector();
	private Vector listHa = new Vector();
	private Vector listRujJenisHa = new Vector();
	private Vector listPenting = new Vector();
	private Vector listPentingdulu = new Vector();
	private Vector listPentingbyIDOB = new Vector();
	private Vector listSaksi = new Vector();
	private Vector listPenghutang = new Vector();
	private Vector listPenghutangdulu = new Vector();
	private Vector listPemiutang = new Vector();
	private Vector listWarisParent = new Vector();
	private Vector listWarisLapisan = new Vector();
	private Vector listWarisUpdate = new Vector();
	private Vector listDaerah = new Vector();
	private Vector listLuas = new Vector();
	private Vector listHTA = new Vector();
	private Vector listHTAdulu = new Vector();
	private Vector listOBHTAdulu = new Vector();
	private Vector listOBHAdulu = new Vector();
	private Vector listHTAX = new Vector();
	private Vector listHTAXdulu = new Vector();
	private Vector listHTAbyIdHtaam = new Vector();
	private Vector listKehadiran = new Vector();
	private Vector listHTAXbyIdHtaam = new Vector();
	private Vector listWarisOB = new Vector();
	private Vector listWarisLapisanIdMati = new Vector();
	private Vector listWarisLapisanIdMatiDelete = new Vector();
	private Vector listCheckPertukaran = new Vector();
	private Vector listCheckPeguam = new Vector();
	private Vector listCheckPeguam_online = new Vector();
	private Vector listPenghutangbyIDOB = new Vector();
	private Vector listPeguamX = new Vector();

	private Vector listDaerahbyID = new Vector();
	private Vector listWarisLapisanIdMatiDulu = new Vector();

	private Vector listHTAXdulu_pilihan = new Vector();

	private Vector listCheckPeguam17_online = new Vector();

	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public Vector getData() {
		return list;
	}

	public Vector getcheckpemohonwaris() {
		return listcheckpemohonwaris;
	}

	// private Vector listcheckpemohonwaris = new Vector();
	public Vector getCheckPeguam() {
		return listCheckPeguam;
	}
	
	public Vector getCheckPertukaran() {
		return listCheckPertukaran;
	}

	public Vector getCheckPeguam_online() {
		return listCheckPeguam_online;
	}

	public Vector getDataHTAX() {
		return listHTAX;
	}

	public Vector getDataHTAXdulu() {
		return listHTAXdulu;
	}

	public Vector getDataHTAXdulu_Pilihan() {
		return listHTAXdulu_pilihan;
	}

	public Vector getDataHTAXbyIdHtaam() {
		return listHTAXbyIdHtaam;
	}

	public Vector getDataHTAbyIdHtaam() {
		return listHTAbyIdHtaam;
	}

	public Vector getDataHTA() {
		return listHTA;
	}

	public Vector getDataHTAdulu() {
		return listHTAdulu;
	}

	public Vector getDataOBHTAdulu() {
		return listOBHTAdulu;
	}

	public Vector getDataOBHAdulu() {
		return listOBHAdulu;
	}

	public Vector getDataWarisOB() {
		return listWarisOB;
	}

	public Vector getDataSimati() {
		return listSimati;
	}
	
	public Vector setDataKehadiranBicara_online() {
		return listKehadiran;
	}

	public Vector getDataSimati_check() {
		return listSimati_check;
	}

	public Vector getDataWarisLapisanIdMati() {
		return listWarisLapisanIdMati;
	}

	public Vector getDataWarisLapisanIdMatiDulu() {
		return listWarisLapisanIdMatiDulu;
	}

	public Vector getDataWarisLapisanIdMatiDelete() {
		return listWarisLapisanIdMatiDelete;
	}

	public Vector getDataPemohon() {
		return listPemohon;
	}

	public Vector getDataPemohonOB() {
		return listPemohonOB;
	}

	public Vector getDataPeguam() {
		return listPeguam;
	}

	public Vector getDataPeguamX() {
		return listPeguamX;
	}

	public Vector getCheckPeguam17() {
		return listCheckPeguam17;
	}

	public Vector getCheckPeguam17_online() {
		return listCheckPeguam17_online;
	}

	public Vector getDataPenting() {
		return listPenting;
	}

	public Vector getDataPentingDulu() {
		return listPentingdulu;
	}

	public Vector getDataPentingbyIDOB() {
		return listPentingbyIDOB;
	}

	public Vector getDataSaksi() {
		return listSaksi;
	}

	public Vector getDataPenghutang() {
		return listPenghutang;
	}

	public Vector getDataPenghutangDulu() {
		return listPenghutangdulu;
	}

	public Vector getDataPenghutangbyIDOB() {
		return listPenghutangbyIDOB;
	}

	public Vector getDataPemiutang() {
		return listPemiutang;
	}

	public Vector getDataWaris() {
		return listWaris;
	}

	public Vector getDataWarisDulu() {
		return listWarisDulu;
	}

	public Vector getDataWarisParent() {
		return listWarisParent;
	}

	public Vector getDataWarisLapisan() {
		return listWarisLapisan;
	}

	public Vector getDataWarisUpdate() {
		return listWarisUpdate;
	}

	public Vector getDataHta() {
		return listHta;
	}

	public Vector getDataHtath() {
		return listHtath;
	}

	public Vector getDataHa() {
		return listHa;
	}

	public Vector getDaerahbyID() {
		return listDaerahbyID;
	}


	 public Vector getIdPermohonanSimati(){
			return listidPermohonansimati;
		 }
	 

//IL
public Boolean sendNilaianHTA(String ID_PERMOHONAN, String ID_HTA, String JENIS_HTA, String USER_ID, Boolean isJPPHUser)
			throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Boolean returnValue = false;
		Db db = new Db();

		try {
			if (!"".equalsIgnoreCase(ID_HTA)) {
				Statement stmt = db.getStatement();
				String sql = "";

				String STATUS = "";
				if (isJPPHUser) {
					STATUS = "SELESAI";
				} else {
					STATUS = "BARU";
				}

				if ("0".equalsIgnoreCase(JENIS_HTA)) {
					sql = "UPDATE TBLINTJPPHAH SET STATUS_PROSES = '" + STATUS + "' WHERE ID_HTA = " + ID_HTA;
				} else {
					sql = "UPDATE TBLINTJPPHTH SET STATUS_PROSES = '" + STATUS + "' WHERE ID_HTA = " + ID_HTA;
				}
				stmt.execute(sql);

				// should send email to JKPTG user if nilaian is completed by
				// JPPH
				if (isJPPHUser) {
					// ****************************
					// sending e-mail codes
					ResultSet rs = null;

					// get NO_FAIL
					String NO_FAIL = "";
					if ("0".equalsIgnoreCase(JENIS_HTA)) {
						sql = "SELECT FA.NO_FAIL " + "FROM TBLINTJPPHAH M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FA "
								+ "WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FA.ID_FAIL " + "AND M.ID_PERMOHONAN = "
								+ ID_PERMOHONAN + " AND M.ID_HTA = " + ID_HTA;
					} else {
						sql = "SELECT FA.NO_FAIL " + "FROM TBLINTJPPHTH M, TBLPPKPERMOHONAN PRMHN, TBLPFDFAIL FA "
								+ "WHERE M.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND PRMHN.ID_FAIL = FA.ID_FAIL " + "AND M.ID_PERMOHONAN = "
								+ ID_PERMOHONAN + " AND M.ID_HTA = " + ID_HTA;
					}
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					}

					// get e-mail address of JKPTG user that saves this file
					String EMAIL_ADDR = "";
					sql = "SELECT EMEL FROM USERS_INTERNAL WHERE USER_ID = " + USER_ID;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						EMAIL_ADDR = rs.getString(1) == null ? "" : rs.getString(1);
					}

					// temporary hardcode
					EMAIL_ADDR = "zulkhaimi@hla-group.com";

					String EMAIL_FROM = "", EMAIL_HAVE_CC = "", EMAIL_CC_1 = "", EMAIL_CC_2 = "", EMAIL_SUBJECT = "", EMAIL_BODY = "";
					sql = "SELECT ALAMAT_DARI, TAJUK, KANDUNGAN, ADA_CC, ALAMAT_CC_1, ALAMAT_CC_2 FROM TBLINTEMAILMSG WHERE UPPER(KOD_AGENSI) = 'JPPH' AND STATUS = 1";
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						EMAIL_FROM = rs.getString(1) == null ? "" : rs.getString(1);
						EMAIL_SUBJECT = rs.getString(2) == null ? "" : rs.getString(2);
						EMAIL_BODY = rs.getString(3) == null ? "" : rs.getString(3);
						EMAIL_HAVE_CC = rs.getString(4) == null ? "" : rs.getString(4);
						EMAIL_CC_1 = rs.getString(5) == null ? "" : rs.getString(5);
						EMAIL_CC_2 = rs.getString(6) == null ? "" : rs.getString(6);
					}

					if (!"".equalsIgnoreCase(EMAIL_ADDR.trim()) && !"".equalsIgnoreCase(EMAIL_FROM.trim())
							&& !"".equalsIgnoreCase(EMAIL_SUBJECT.trim()) && !"".equalsIgnoreCase(EMAIL_BODY.trim())) {
						EMAIL_SUBJECT = EMAIL_SUBJECT.replace("[$NOFAIL]", NO_FAIL);
						if (isJPPHUser) {
							EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JPPH");
						} else {
							EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JKPTG");
						}
						Date date = new Date();
						if ("0".equalsIgnoreCase(JENIS_HTA)) {
							EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Ada Hakmilik)");
						} else {
							EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Harta Tak Alih (Tiada Hakmilik)");
						}
						EMAIL_BODY = EMAIL_BODY.replace("[$NOFAIL]", NO_FAIL);
						EMAIL_BODY = EMAIL_BODY.replace("[$STATUS_PROSES]", STATUS);
						EMAIL_BODY = EMAIL_BODY.replace("[$TARIKH_KEMASKINI]", sdf.format(date));
						XEkptgEmailSender email = XEkptgEmailSender.getInstance();
						email.FROM = EMAIL_FROM;
						email.MULTIPLE_RECIEPIENT = new String[1];
						email.MULTIPLE_RECIEPIENT[0] = EMAIL_ADDR;
						if ("1".equalsIgnoreCase(EMAIL_HAVE_CC)) {
							if (!"".equalsIgnoreCase(EMAIL_CC_1) && !"".equalsIgnoreCase(EMAIL_CC_2)) {
								email.TO_CC = new String[2];
								email.TO_CC[0] = EMAIL_CC_1;
								email.TO_CC[1] = EMAIL_CC_2;
							} else {
								email.TO_CC = new String[1];
								if (!"".equalsIgnoreCase(EMAIL_CC_1)) {
									email.TO_CC[0] = EMAIL_CC_1;
								} else {
									email.TO_CC[0] = EMAIL_CC_2;
								}
							}
						}
						email.RECIEPIENT = EMAIL_ADDR;
						email.SUBJECT = EMAIL_SUBJECT;
						email.MESSAGE = EMAIL_BODY;
						email.sendEmail();
					}
					// ****************************
				}

				returnValue = true;
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

		return returnValue;
	}

	public void setCheckPeguam(String id) throws Exception {
		Db db = null;
		listCheckPeguam.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("s.id_Permohonan");
			r.add("s.id_Semakansenarai");
			r.add("s.id_Semakanhantar");

			r.add("s.id_Permohonan", id);
			r.add("s.id_Semakansenarai", 10);

			sql = r.getSQLSelect("Tblsemakanhantar s");
			// System.out.println("CHECKLIST PEGUAM:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idSemakanhantar", rs.getString("id_Semakanhantar") == null ? "" : rs.getString("id_Semakanhantar"));
				listCheckPeguam.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	// syafiqah add
	// public boolean setCheckPertukaran(String id) throws Exception {
	public Vector setCheckPertukaran(String id) throws Exception {
		// boolean returnVal =false;
		Db db = null;
		Connection conn = null;
		listCheckPertukaran.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "select pt.id_permohonansimati, pt.id_pemohonbaru, pt.sebab_tukar, to_char(po.tarikh_mati, 'DD/MM/YYYY') as tarikh_mati from tblppktukarpemohon pt, tblppkob po "
					+ "where pt.id_pemohonlama = po.id_pemohon and pt.id_permohonansimati = " + id;
			System.out.println("syafiqah get borang aa :"+sql); 
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;

			while (rs.next()) {
				// myLogger.info("DIA X BACA KAT SINI"); 
				h = new Hashtable();
				h.put("id_permohonansimati", rs.getString("id_permohonansimati") == null ? "" : rs.getString("id_permohonansimati"));
				h.put("id_pemohonbaru", rs.getString("id_pemohonbaru") == null ? "" : rs.getString("id_pemohonbaru"));
				h.put("sebab_tukar", rs.getString("sebab_tukar") == null ? "" : rs.getString("sebab_tukar"));
				h.put("tarikh_mati", rs.getString("tarikh_mati") == null ? "" : rs.getString("tarikh_mati"));
				// h.put("id_permohonansimati", rs.getString("id_permohonansimati") == null ? "" : rs.getString("id_permohonansimati"));
				listCheckPertukaran.addElement(h);
				// returnVal = true; 
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		// return returnVal;
		return listCheckPertukaran;

	}
	
	// syafiqah add ends

	public void setCheckPeguam_online(String id) throws Exception {
		Db db = null;
		listCheckPeguam_online.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("s.id_Permohonan");
			r.add("s.id_Semakansenarai");
			r.add("s.id_Semakanhantar");

			r.add("s.id_Permohonan", id);
			r.add("s.id_Semakansenarai", 10);

			// sql = r.getSQLSelect("Tblsemakanhantar s");

			sql = "select pm.status_peguam from tblppkpermohonan p,tblppkpemohon pm "
					+ "where p.id_pemohon = pm.id_pemohon and pm.status_peguam = 'Y' and p.id_permohonan =" + id;
			// System.out.println("CHECKLIST PEGUAM:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("status_peguam", rs.getString("status_peguam") == null ? "" : rs.getString("status_peguam"));
				listCheckPeguam_online.addElement(h);
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setCheckPeguam17(String id) throws Exception {
		Db db = null;
		listCheckPeguam17.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("s.id_Permohonan");
			r.add("s.id_Semakansenarai");
			r.add("s.id_Semakanhantar");

			r.add("s.id_Permohonan", id);
			r.add("s.id_Semakansenarai", 19);//8

			sql = r.getSQLSelect("Tblsemakanhantar s");
			 System.out.println("CHECKLIST PEGUAM:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idSemakanhantar", rs.getString("id_Semakanhantar") == null ? "" : rs.getString("id_Semakanhantar"));
				listCheckPeguam17.addElement(h);
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setCheckPeguam17_online(String id) throws Exception {
		Db db = null;
		listCheckPeguam17_online.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("s.id_Permohonan");
			r.add("s.id_Semakansenarai");
			r.add("s.id_Semakanhantar");

			r.add("s.id_Permohonan", id);
			r.add("s.id_Semakansenarai", 8);

			// sql = r.getSQLSelect("Tblsemakanhantar s");
			// System.out.println("CHECKLIST PEGUAM:"+sql);
			sql = "select pm.status_peguam from tblppkpermohonan p,tblppkpemohon pm "
					+ "where p.id_pemohon = pm.id_pemohon and pm.status_peguam = 'Y' and p.id_permohonan =" + id;
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("status_peguam", rs.getString("status_peguam") == null ? "" : rs.getString("status_peguam"));
				listCheckPeguam17_online.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	// SELECT P.ID_PERMOHONAN, S.ID_SIMATI, S.NO_KP_BARU, S.NO_KP_LAMA,
	// S.JENIS_KP, S.NO_KP_LAIN, S.NAMA_SIMATI, S.TARIKH_MATI, S.NAMA_LAIN,
	// S.JANTINA, S.JENIS_WARGA, S.JENIS_AGAMA, S.UMUR, S.ID_BUKTIMATI,
	// S.TEMPAT_MATI, S.NO_SIJIL_MATI, S.WAKTU_KEMATIAN, S.JENIS_WAKTU_MATI,
	// S.ID_BUKTIMATI, S.SEBAB_MATI, S.ALAMAT_1, S.ALAMAT_2, S.ALAMAT_3,
	// S.POSKOD, S.BANDAR, S.ID_NEGERI, S.ID_BANDAR, S.CATATAN,
	// S.JENIS_WAKTU_MATI, PO.ID_PEMOHON FROM TBLPPKSIMATI S, TBLPPKPERMOHONAN
	// P, TBLPPKPEMOHON PO, TBLPPKPERMOHONANSIMATI MS WHERE S.ID_SIMATI =
	// MS.ID_SIMATI AND PO.ID_PEMOHON = P.ID_PEMOHON AND P.ID_PERMOHONAN =
	// MS.ID_PERMOHONAN AND P.ID_PERMOHONAN = '1611551810'

	public void setDataSimati_check(String kpbaru, String kplama, String kplain, String no_fail) throws Exception {
		Db db = null;
		listSimati_check.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT P.ID_PERMOHONAN, S.ID_SIMATI, S.NO_KP_BARU, S.NO_KP_LAMA, S.JENIS_KP, S.NO_KP_LAIN, "
					+ "S.NAMA_SIMATI, S.TARIKH_MATI, S.NAMA_LAIN, S.JANTINA, S.JENIS_WARGA, S.JENIS_AGAMA, S.UMUR, "
					+ "S.ID_BUKTIMATI, S.TEMPAT_MATI, S.NO_SIJIL_MATI, S.WAKTU_KEMATIAN, S.JENIS_WAKTU_MATI, S.ID_BUKTIMATI, "
					+ "S.SEBAB_MATI, S.ALAMAT_1, S.ALAMAT_2, S.ALAMAT_3, S.POSKOD, S.BANDAR, S.ID_NEGERI, "
					+ "S.ID_BANDAR, S.CATATAN, S.JENIS_WAKTU_MATI, PO.ID_PEMOHON  "
					+ "FROM TBLPPKSIMATI S, TBLPPKPERMOHONAN P, TBLPPKPEMOHON PO, TBLPPKPERMOHONANSIMATI MS,TBLPFDFAIL F "
					+ "WHERE S.ID_SIMATI = MS.ID_SIMATI  " + "AND PO.ID_PEMOHON = P.ID_PEMOHON AND F.ID_FAIL = P.ID_FAIL "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN  ";

			if (!kpbaru.equals("") && kpbaru.length() != 0) {
				sql += " AND UPPER(S.NO_KP_BARU) = UPPER('" + kpbaru + "')";
			} else if (!kplama.equals("") && kplama.length() != 0) {
				sql += " AND UPPER(S.NO_KP_LAMA) = UPPER('" + kplama + "')";
			} else if (!kplain.equals("") && kplain.length() != 0) {
				sql += " AND UPPER(S.NO_KP_LAIN) = UPPER('" + kplain + "')";
			} else if (!no_fail.equals("") && no_fail.length() != 0) {
				sql += " AND UPPER(F.NO_FAIL) = UPPER('" + no_fail + "')";
			}

			myLogger.info("SQL SI MATI OI::" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("noKpBaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("noKpLain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namaLain", rs.getString("nama_Lain") == null ? "" : rs
						.getString("nama_Lain"));
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				h.put("masamati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));
				h.put("tarikhMati", rs.getString("tarikh_Mati") == null ? ""
						: formatter.format(rs.getDate("tarikh_Mati")));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("jenisWarga", rs.getString("jenis_Warga") == null ? ""
						: rs.getString("jenis_Warga"));
				h.put("jenisAgama", rs.getString("jenis_Agama") == null ? ""
						: rs.getString("jenis_Agama"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("idBuktimati", rs.getString("id_Buktimati") == null ? ""
						: rs.getString("id_Buktimati"));
				h.put("tempatMati", rs.getString("tempat_Mati") == null ? ""
						: rs.getString("tempat_Mati"));
				h.put("noSijilMati", rs.getString("no_Sijil_Mati") == null ? ""
						: rs.getString("no_Sijil_Mati"));
				h.put("masaMati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));
				h.put("jeniswaktumati",
						rs.getString("jenis_Waktu_Mati") == null ? "" : rs
								.getString("jenis_Waktu_Mati"));
				h.put("sebabMati", rs.getString("sebab_Mati") == null ? "" : rs
						.getString("sebab_Mati"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("bandartetap", rs.getString("id_bandar") == null ? ""
						: rs.getString("id_bandar"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("jeniswaktu",
						rs.getString("JENIS_WAKTU_MATI") == null ? "" : rs
								.getString("JENIS_WAKTU_MATI"));

				// System.out.println(h);
				listSimati_check.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataSimati(String id) throws Exception {
		Db db = null;
		listSimati.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("s.nama_pelbagainegara");
			r.add("p.id_Permohonan");
			r.add("s.id_Simati");
			r.add("s.no_Kp_Baru");
			r.add("s.no_Kp_Lama");
			r.add("s.jenis_Kp");
			r.add("s.no_Kp_Lain");
			r.add("s.nama_Simati");
			r.add("s.tarikh_Mati");
			r.add("s.nama_Lain");
			r.add("s.jantina");
			r.add("s.jenis_Warga");
			r.add("s.jenis_Agama");
			r.add("s.umur");
			r.add("s.id_Buktimati");
			r.add("s.tempat_Mati");
			r.add("s.no_Sijil_Mati");
			r.add("s.waktu_Kematian");
			r.add("s.jenis_Waktu_Mati");

			r.add("s.id_Buktimati");

			r.add("s.sebab_Mati");
			r.add("s.alamat_1");
			r.add("s.alamat_2");
			r.add("s.alamat_3");
			r.add("s.poskod");
			r.add("s.bandar");
			r.add("s.id_Negeri");
			r.add("s.id_Bandar");
			r.add("s.catatan");
			r.add("s.JENIS_WAKTU_MATI");

			r.add("s.TARIKH_SURAT_AKUAN");
			
			
			r.add("s.tarikh_lahir as tarikh_lahir_simati");

			// r.add("m.keterangan");
			// r.add("n.nama_Negeri");
			r.add("po.id_Pemohon");
			// r.add("m.kod");
			// r.add("n.kod_Negeri");

			r.add("s.id_Simati", r.unquote("ms.id_Simati"));
			// r.add("p.id_Permohonan",r.unquote("po.id_Permohonan"));
			r.add("po.id_Pemohon", r.unquote("p.id_Pemohon"));

			r.add("p.id_Permohonan", r.unquote("ms.id_Permohonan"));

			r.add("p.id_Permohonan", id);

			sql = r.getSQLSelect("Tblppksimati s, Tblppkpermohonan p, Tblppkpemohon po, Tblppkpermohonansimati ms");

			// System.out.println("SQL SI MATI OI::"+sql.toUpperCase());

			myLogger.info("SQL SI MATI OI::" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("nama_pelbagainegara",rs.getString("nama_pelbagainegara") == null ? "" : rs.getString("nama_pelbagainegara"));
				h.put("idPermohonan",rs.getString("id_Permohonan") == null ? "" : rs.getString("id_Permohonan"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("noKpBaru", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru"));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama", rs.getString("no_Kp_Lama") == null ? "" : rs.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("jenis_Kp") == null ? "" : rs.getString("jenis_Kp"));
				h.put("noKpLain", rs.getString("no_Kp_Lain") == null ? "" : rs.getString("no_Kp_Lain"));
				h.put("tarikhLahirSimati",rs.getString("tarikh_lahir_simati") == null ? "" : formatter.format(rs.getDate("tarikh_lahir_simati")));
				h.put("namaSimati", rs.getString("nama_Simati") == null ? "" : rs.getString("nama_Simati"));
				h.put("namaLain", rs.getString("nama_Lain") == null ? "" : rs.getString("nama_Lain"));
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs.getString("id_Pemohon"));
				h.put("masamati", rs.getString("waktu_Kematian") == null ? "" : rs.getString("waktu_Kematian"));
				h.put("tarikhMati", rs.getString("tarikh_Mati") == null ? "" : formatter.format(rs.getDate("tarikh_Mati")));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs.getString("jantina"));
				h.put("jenisWarga", rs.getString("jenis_Warga") == null ? "" : rs.getString("jenis_Warga"));
				h.put("jenisAgama", rs.getString("jenis_Agama") == null ? "" : rs.getString("jenis_Agama"));
				h.put("umur", rs.getString("umur") == null ? "" : rs.getString("umur"));
				h.put("idBuktimati", rs.getString("id_Buktimati") == null ? "" : rs.getString("id_Buktimati"));
				h.put("tempatMati", rs.getString("tempat_Mati") == null ? "" : rs.getString("tempat_Mati"));
				h.put("noSijilMati", rs.getString("no_Sijil_Mati") == null ? "" : rs.getString("no_Sijil_Mati"));
				h.put("masaMati", rs.getString("waktu_Kematian") == null ? "" : rs.getString("waktu_Kematian"));
				h.put("jeniswaktumati",rs.getString("jenis_Waktu_Mati") == null ? "" : rs.getString("jenis_Waktu_Mati"));
				h.put("sebabMati", rs.getString("sebab_Mati") == null ? "" : rs.getString("sebab_Mati"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs.getString("bandar"));
				h.put("bandartetap", rs.getString("id_bandar") == null ? "" : rs.getString("id_bandar"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("jeniswaktu",rs.getString("JENIS_WAKTU_MATI") == null ? "" : rs.getString("JENIS_WAKTU_MATI"));				
				h.put("tarikhSuratAkuan", rs.getString("TARIKH_SURAT_AKUAN") == null ? "" : formatter.format(rs.getDate("TARIKH_SURAT_AKUAN")));
				h.put("txdTarikhSuratAkuan", rs.getString("TARIKH_SURAT_AKUAN") == null ? "" : formatter.format(rs.getDate("TARIKH_SURAT_AKUAN")));
				// System.out.println(h);
				listSimati.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHta(String id) throws Exception {
		Db db = null;
		listHta.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("hta.id_Hta");
			r.add("hta.id_Negeri");
			r.add("hta.id_Daerah");
			r.add("hta.id_Mukim");
			r.add("hta.no_Hakmilik");
			r.add("hta.no_Pt");
			r.add("hta.ba_Simati");
			r.add("hta.bb_Simati");
			r.add("n.nama_Negeri");
			r.add("d.nama_Daerah");
			r.add("m.nama_Mukim");
			r.add("sm.id_Simati");
			r.add("sm.id_Permohonan");
			r.add("p.id_Permohonan");

			r.add("hta.id_Negeri", r.unquote("n.id_Negeri(+)"));
			r.add("hta.id_Daerah", r.unquote("d.id_Daerah(+)"));
			r.add("hta.id_Mukim", r.unquote("m.id_Mukim(+)"));
			r.add("hta.id_Simati", r.unquote("sm.id_Simati(+)"));
			r.add("sm.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", id);

			sql = r
					.getSQLSelect("Tblppkpermohonan p, Tblppkhta hta, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblppksimati sm");
			ResultSet rs = stmt.executeQuery(sql);
			// Vector list = new Vector(;
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Hta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("id_Negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("id_Daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("id_Mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("no_Hakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("no_Pt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("ba_Simati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bb_Simati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("nama_Negeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("nama_Daerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("nama_Mukim", rs.getString("nama_Mukim") == null ? ""
						: rs.getString("nama_Mukim"));

				listHta.addElement(h);
				bil++;
			}
			// return list;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDaerahbyID(String id) throws Exception {
		Db db = null;
		listDaerahbyID.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_negeri");
			r.add("id_daerah");
			r.add("nama_daerah");
			r.add("kod_daerah");

			r.add("id_daerah", id);

			sql = r.getSQLSelect("Tblrujdaerah");
			ResultSet rs = stmt.executeQuery(sql);
			// Vector list = new Vector(;
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("id_Permohonan", rs.getString("id_Permohonan"));
				// h.put("id_Hta",
				// rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				r.add("id_negeri", rs.getString("id_negeri") == null ? "" : rs
						.getString("id_negeri"));
				r.add("id_daerah", rs.getString("id_daerah") == null ? "" : rs
						.getString("id_daerah"));
				r.add("nama_daerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));
				r.add("kod_daerah", rs.getString("kod_daerah") == null ? ""
						: rs.getString("kod_daerah"));

				listDaerahbyID.addElement(h);
				bil++;
			}
			// return list;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDataHtath(String id) throws Exception {
		Db db = null;
		listHtath.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("hta.id_Hta");
			r.add("hta.id_Negeri");
			r.add("hta.id_Daerah");
			r.add("hta.id_Mukim");
			r.add("hta.no_Perjanjian");
			r.add("hta.no_Pt");
			r.add("hta.ba_Simati");
			r.add("hta.bb_Simati");
			r.add("n.nama_Negeri");
			r.add("d.nama_Daerah");
			r.add("m.nama_Mukim");
			r.add("sm.id_Simati");
			r.add("sm.id_Permohonan");
			r.add("p.id_Permohonan");

			r.add("hta.id_Negeri", r.unquote("n.id_Negeri(+)"));
			r.add("hta.id_Daerah", r.unquote("d.id_Daerah(+)"));
			r.add("hta.id_Mukim", r.unquote("m.id_Mukim(+)"));
			r.add("hta.id_Simati", r.unquote("sm.id_Simati(+)"));
			r.add("sm.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", id);

			sql = r.getSQLSelect("Tblppkpermohonan p, Tblppkhta hta, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblppksimati sm");
			ResultSet rs = stmt.executeQuery(sql);
			// Vector list = new Vector(;
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Hta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("id_Negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("id_Daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("id_Mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("no_Perjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));
				h.put("no_Pt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("ba_Simati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bb_Simati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("nama_Negeri", rs.getString("nama_Negeri") == null ? ""
						: rs.getString("nama_Negeri"));
				h.put("nama_Daerah", rs.getString("nama_Daerah") == null ? ""
						: rs.getString("nama_Daerah"));
				h.put("nama_Mukim", rs.getString("nama_Mukim") == null ? ""
						: rs.getString("nama_Mukim"));

				listHtath.addElement(h);
				bil++;
			}
			// return list;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDataHa(String id) throws Exception {
		Db db = null;
		listHa.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ha.id_Ha");
			r.add("ha.id_Simati");
			r.add("ha.id_Jenisha");
			r.add("ha.no_Daftar");
			r.add("sm.id_Simati");
			r.add("sm.id_Permohonan");
			r.add("p.id_Permohonan");

			r.add("ha.id_Simati", r.unquote("sm.id_Simati(+)"));
			r.add("sm.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", id);

			sql = r
					.getSQLSelect("Tblppkpermohonan p, Tblppkha ha, Tblppksimati sm");
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Ha", rs.getString("id_Ha") == null ? "" : rs.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? "" : rs.getString("id_Jenisha"));
				h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("no_Daftar", rs.getString("no_Daftar") == null ? "" : rs.getString("no_Daftar"));
				listHa.addElement(h);
				bil++;
			}
			// return list;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatesimati(Hashtable data) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {

			// int id_Permohonan= (Integer)data.get("id_Permohonan");
			
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			String id_Simati = (String) data.get("id_Simati");
			String nama_Simati = (String) data.get("nama_Simati");
			String nama_Lain = (String) data.get("nama_Lain");
//IL
			if(nama_Lain == null){
				nama_Lain = "";
			}
//end IL
			String no_Kp_Baru = (String) data.get("no_Kp_Baru");
			String no_Kp_Lama = (String) data.get("no_Kp_Lama");
			String jeniswaktu = (String) data.get("jeniswaktu");

			String jenis_Kp = (String) data.get("jenis_Kp");
			String n0_Kp_Lain = (String) data.get("n0_Kp_Lain");

			String tarikh_lahir_simati_ = (String) data.get("tarikhLahirSimati");
			String tarikh_lahir_simati = "to_date('" + tarikh_lahir_simati_ + "','dd/MM/yyyy')";

			int umur = (Integer) data.get("umur");

			String jantina = (String) data.get("jantina");
			String no_Sijil_Mati = (String) data.get("no_Sijil_Mati");
			String tempat_Mati = (String) data.get("tempat_Mati");
			String alamat_1 = (String) data.get("alamat_1");
			String alamat_2 = (String) data.get("alamat_2");
			String alamat_3 = (String) data.get("alamat_3");
			String bandar = (String) data.get("bandar");

			int id_bandar = 0;
			if (bandar != "" && bandar != "0") {
				id_bandar = Integer.parseInt(bandar);
			} else {
				id_bandar = 0;
			}

			String poskod = (String) data.get("poskod");

			String tarikh_Mati_ = (String) data.get("tarikh_Mati");
			String tarikh_Mati = "to_date('" + tarikh_Mati_ + "','dd/MM/yyyy')";
			String waktu_Kematian = (String) data.get("waktu_Kematian");
			String jenis_Waktu_Mati = (String) data.get("jenis_Waktu_Mati");
			String sebab_Mati = (String) data.get("sebab_Mati");
			String catatan = (String) data.get("catatan");
			int id_Negeri = (Integer) data.get("id_Negeri");
			String id_Buktimati = (String) data.get("id_Buktimati");
			String jenis_Agama = (String) data.get("jenis_Agama");
			String jenis_Warga = (String) data.get("jenis_Warga");
			String tarikh_Kkini = (String) data.get("tarikh_Kkini");
			String id_Permohonan = (String) data.get("id_Permohonan");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String id_Kemaskini = (String) data.get("id_Kemaskini");
			String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");
			String id_Db = (String) data.get("id_Db");
			
			String TARIKH_SURAT_AKUAN_ = (String) data.get("TARIKH_SURAT_AKUAN");
			String TARIKH_SURAT_AKUAN = "to_date('" + TARIKH_SURAT_AKUAN_ + "','dd/MM/yyyy')";
System.out.println("TARIKH_SURAT_AKUAN=="+TARIKH_SURAT_AKUAN);
			db = new Db();

			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_simati", id_Simati);

			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_Simati", nama_Simati);
			r.add("nama_Lain", nama_Lain);
			r.add("no_Kp_Baru", no_Kp_Baru);
			r.add("no_Kp_Lama", no_Kp_Lama);
			r.add("jenis_Kp", jenis_Kp);

			r.add("no_Kp_Lain", n0_Kp_Lain);

			r.add("jantina", jantina);
			r.add("jenis_waktu_mati", jeniswaktu);
			r.add("no_Sijil_Mati", no_Sijil_Mati);
			r.add("tempat_Mati", tempat_Mati);
			r.add("alamat_1", alamat_1);
			r.add("alamat_2", alamat_2);
			r.add("alamat_3", alamat_3);
			// r.add("bandar",bandar);
			r.add("id_bandar", id_bandar);
			r.add("poskod", poskod);

			if (umur != 0) {
				r.add("umur", umur);
			}
			if (umur == 0) {
				r.add("umur", "");
			}
			r.add("tarikh_Mati", r.unquote(tarikh_Mati));
			if (tarikh_lahir_simati_ != null && tarikh_lahir_simati_.length() > 0) {
				r.add("tarikh_lahir", r.unquote(tarikh_lahir_simati));
			}
			r.add("waktu_Kematian", jenis_Waktu_Mati);

			r.add("sebab_Mati", sebab_Mati);
			r.add("catatan", catatan);
			if (id_Negeri != 0) {
				r.add("id_Negeri", id_Negeri);
			}
			if (id_Negeri == 0) {
				r.add("id_Negeri", "");
			}

			r.add("id_Buktimati", id_Buktimati);
			r.add("jenis_Agama", jenis_Agama);
			r.add("jenis_Warga", jenis_Warga);
			/*if(!TARIKH_SURAT_AKUAN.equals("")){
			r.add("TARIKH_SURAT_AKUAN", TARIKH_SURAT_AKUAN);
			}*/
			r.add("TARIKH_SURAT_AKUAN", r.unquote(TARIKH_SURAT_AKUAN));
			// r.add("id_Permohonan",id_Permohonan);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			// sysdate

			r.add("id_Kemaskini", id_Masuk);
			// r.add("tarikh_Kemaskini",currentDate);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			r.add("id_Db", id_Db);

			sql = r.getSQLUpdate("tblppksimati");
			stmt.executeUpdate(sql);

			// System.out.println("sql SIMATI"+sql);

			// db = new Db();
			Statement stmt1 = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			String sql1 = "delete from tblsemakanhantar where id_Permohonan = "
					+ id_Permohonan + " and id_Semakansenarai = 5 ";
			// sql = r.getSQLUpdate("tblppkha");
			stmt1.executeUpdate(sql1);

			// db = new Db();
			Statement stmt2 = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			String sql2 = "delete from tblsemakanhantar where id_Permohonan = "
					+ id_Permohonan + " and id_Semakansenarai = 6 ";
			// sql = r.getSQLUpdate("tblppkha");
			stmt2.executeUpdate(sql2);

			// db = new Db();
			Statement stmt3 = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			String sql3 = "delete from tblsemakanhantar where id_Permohonan = "
					+ id_Permohonan + " and id_Semakansenarai = 7 ";
			// sql = r.getSQLUpdate("tblppkha");
			stmt3.executeUpdate(sql3);

			// db = new Db();
			Statement stmt4 = db.getStatement();
			// SQLRenderer r = new SQLRenderer();
			String sql4 = "delete from tblsemakanhantar where id_Permohonan = "
					+ id_Permohonan + " and id_Semakansenarai = 8 ";
			// sql = r.getSQLUpdate("tblppkha");
			stmt4.executeUpdate(sql4);

			String idsemakan = "";

			if (id_Buktimati == "1") {
				idsemakan = "5";
			} else if (id_Buktimati == "2") {
				idsemakan = "6";
			} else if (id_Buktimati == "3") {
				idsemakan = "7";
			} else if (id_Buktimati == "4") {
				idsemakan = "8";
			}

			/*
			 * db = new Db(); Statement stmt5 = db.getStatement(); long
			 * idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ"); String
			 * sql5 = "INSERT INTO Tblsemakanhantar " +
			 * "(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_masuk)  "
			 * +
			 * "VALUES ("+idSemakanhantar+", "+idsemakan+", "+id_Permohonan+", '"
			 * +no_Sijil_Mati+"','"+currentDate+"')";
			 * ////System.out.println("SQL SEMAKKKK"+sql);
			 * 
			 * 
			 * stmt5.executeUpdate(sql5);
			 */

			int idbuk = Integer.parseInt(id_Buktimati);
			int idsem = idbuk + 4;

			// System.out.println("SID SEM::"+idsemakan);
			long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
			db = new Db();
			Statement stmt5 = db.getStatement();
			SQLRenderer r9 = new SQLRenderer();
			// r9.add("id_Semakanhantar", idSemakanhantar);
			r9.add("id_semakansenarai", idsem);
			r9.add("id_permohonan", id_Permohonan);
			r9.add("catatan", no_Sijil_Mati);
			// r9.add("tarikh_pelbagai", tarikhd_resit);
			// r9.add("tarikh_masuk",currentDate);
			r9.add("tarikh_masuk", r.unquote("sysdate"));

			r9.add("id_masuk", id_Masuk);
			String sql7 = r9.getSQLInsert("tblsemakanhantar");
			stmt5.executeUpdate(sql7);

			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}

	public void updatesimati_online(Hashtable data) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {

			// int id_Permohonan= (Integer)data.get("id_Permohonan");
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			
			String id_Simati = (String) data.get("id_Simati");
			String nama_Simati = (String) data.get("nama_Simati");
			String nama_Lain = (String) data.get("nama_Lain");
			//IL
			if(nama_Lain == null){
				nama_Lain = "";
			}
			//end IL
			String no_Kp_Baru = (String) data.get("no_Kp_Baru");
			String no_Kp_Lama = (String) data.get("no_Kp_Lama");
			String jeniswaktu = (String) data.get("jeniswaktu");

			String jenis_Kp = (String) data.get("jenis_Kp");
			String n0_Kp_Lain = (String) data.get("n0_Kp_Lain");

			// int umur=(Integer)data.get("umur");
			String umur = (String) data.get("umur");

			String jantina = (String) data.get("jantina");
			String no_Sijil_Mati = (String) data.get("no_Sijil_Mati");
			String tempat_Mati = (String) data.get("tempat_Mati");
			String alamat_1 = (String) data.get("alamat_1");
			String alamat_2 = (String) data.get("alamat_2");
			String alamat_3 = (String) data.get("alamat_3");
			String bandar = (String) data.get("bandar");

			int id_bandar = 0;
			if (bandar != "" && bandar != "0") {
				id_bandar = Integer.parseInt(bandar);
			} else {
				id_bandar = 0;
			}

			String poskod = (String) data.get("poskod");

			String tarikh_lahir_simati = (String) data.get("tarikhLahirSimati");
			String tarikhLahirSimati = "to_date('" + tarikh_lahir_simati + "','dd/MM/yyyy')";
			String tarikh_Mati_ = (String) data.get("tarikh_Mati");
			String tarikh_Mati = "to_date('" + tarikh_Mati_ + "','dd/MM/yyyy')";
			String waktu_Kematian = (String) data.get("waktu_Kematian");
			String jenis_Waktu_Mati = (String) data.get("jenis_Waktu_Mati");
			String sebab_Mati = (String) data.get("sebab_Mati");
			String catatan = (String) data.get("catatan");
			int id_Negeri = (Integer) data.get("id_Negeri");
			String id_Buktimati = (String) data.get("id_Buktimati");
			String jenis_Agama = (String) data.get("jenis_Agama");
			String jenis_Warga = (String) data.get("jenis_Warga");
			String tarikh_Kkini = (String) data.get("tarikh_Kkini");
			String id_Permohonan = (String) data.get("id_Permohonan");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String id_Kemaskini = (String) data.get("id_Kemaskini");
			String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");
			String id_Db = (String) data.get("id_Db");
			
			String tarikhSuratAkuan = (String) data.get("tarikhSuratAkuan");
			String tarikhAkuan = "";
			if(!tarikhSuratAkuan.equals("")){
				tarikhAkuan = "to_date('" + tarikhSuratAkuan + "','dd/MM/yyyy')";
			}
			db = new Db();

			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_simati", id_Simati);

			r.add("nama_Simati", nama_Simati);
			r.add("nama_Lain", nama_Lain);
			r.add("no_Kp_Baru", no_Kp_Baru);
			r.add("no_Kp_Lama", no_Kp_Lama);
			r.add("jenis_Kp", jenis_Kp);

			r.add("no_Kp_Lain", n0_Kp_Lain);

			r.add("jantina", jantina);
			r.add("jenis_waktu_mati", jeniswaktu);
			r.add("no_Sijil_Mati", no_Sijil_Mati);
			r.add("tempat_Mati", tempat_Mati);
			r.add("alamat_1", alamat_1);
			r.add("alamat_2", alamat_2);
			r.add("alamat_3", alamat_3);
			// r.add("bandar",bandar);
			r.add("id_bandar", id_bandar);
			r.add("poskod", poskod);

			// if(umur!=0)
			// {
			// r.add("umur",umur);
			// }
			// if(umur==0)
			// {
			// r.add("umur","");
			// }
			r.add("umur", umur);
			r.add("tarikh_Mati", r.unquote(tarikh_Mati));
			r.add("waktu_Kematian", jenis_Waktu_Mati);

			if (tarikh_lahir_simati != null && tarikh_lahir_simati.length() > 0) {
				r.add("tarikh_lahir", r.unquote(tarikhLahirSimati));
			}

			r.add("sebab_Mati", sebab_Mati);
			r.add("catatan", catatan);
			if (id_Negeri != 0) {
				r.add("id_Negeri", id_Negeri);
			}
			if (id_Negeri == 0) {
				r.add("id_Negeri", "");
			}

			r.add("id_Buktimati", id_Buktimati);
			r.add("jenis_Agama", jenis_Agama);
			r.add("jenis_Warga", jenis_Warga);

			r.add("nama_pelbagainegara", nama_pelbagainegara);

			// r.add("id_Permohonan",id_Permohonan);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			// sysdate

			r.add("id_Kemaskini", id_Masuk);
			// r.add("tarikh_Kemaskini",currentDate);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			r.add("id_Db", id_Db);
			
			if (tarikhSuratAkuan != null && tarikhSuratAkuan.length() > 0) {
				r.add("TARIKH_SURAT_AKUAN", r.unquote(tarikhAkuan));
			}

			sql = r.getSQLUpdate("tblppksimati");
			stmt.executeUpdate(sql);

			// System.out.println("sql SIMATI"+sql);

			/*
			 * // db = new Db(); Statement stmt1 = db.getStatement(); //
			 * SQLRenderer r = new SQLRenderer(); String sql1 =
			 * "delete from tblsemakanhantar where id_Permohonan = "+
			 * id_Permohonan +" and id_Semakansenarai = 5 "; //sql =
			 * r.getSQLUpdate("tblppkha"); stmt1.executeUpdate(sql1);
			 * 
			 * // db = new Db(); Statement stmt2 = db.getStatement(); //
			 * SQLRenderer r = new SQLRenderer(); String sql2 =
			 * "delete from tblsemakanhantar where id_Permohonan = "+
			 * id_Permohonan +" and id_Semakansenarai = 6 "; //sql =
			 * r.getSQLUpdate("tblppkha"); stmt2.executeUpdate(sql2);
			 * 
			 * // db = new Db(); Statement stmt3 = db.getStatement(); //
			 * SQLRenderer r = new SQLRenderer(); String sql3 =
			 * "delete from tblsemakanhantar where id_Permohonan = "+
			 * id_Permohonan +" and id_Semakansenarai = 7 "; //sql =
			 * r.getSQLUpdate("tblppkha"); stmt3.executeUpdate(sql3);
			 * 
			 * // db = new Db(); Statement stmt4 = db.getStatement(); //
			 * SQLRenderer r = new SQLRenderer(); String sql4 =
			 * "delete from tblsemakanhantar where id_Permohonan = "+
			 * id_Permohonan +" and id_Semakansenarai = 8 "; //sql =
			 * r.getSQLUpdate("tblppkha"); stmt4.executeUpdate(sql4);
			 * 
			 * String idsemakan="";
			 * 
			 * if(id_Buktimati=="1") {idsemakan="5";} else if(id_Buktimati=="2")
			 * {idsemakan="6";} else if(id_Buktimati=="3") {idsemakan="7";} else
			 * if(id_Buktimati=="4") {idsemakan="8";}
			 * 
			 * 
			 * int idbuk=Integer.parseInt(id_Buktimati); int idsem=idbuk+4;
			 * 
			 * 
			 * long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ"); db =
			 * new Db(); Statement stmt5 = db.getStatement(); SQLRenderer r9 =
			 * new SQLRenderer();
			 * 
			 * r9.add("id_semakansenarai", idsem); r9.add("id_permohonan",
			 * id_Permohonan); r9.add("catatan", no_Sijil_Mati);
			 * 
			 * r9.add("tarikh_masuk",r.unquote("sysdate"));
			 * 
			 * r9.add("id_masuk",id_Masuk); String sql7 =
			 * r9.getSQLInsert("tblsemakanhantar"); stmt5.executeUpdate(sql7);
			 */

			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}

	public void updatesimatisemak(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String id_Simati = (String) data.get("id_Simati");
			String no_Sijil_Mati = (String) data.get("no_Sijil_Mati");
			String id_Buktimati = (String) data.get("id_Buktimati");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String id_Kemaskini = (String) data.get("id_Kemaskini");
			String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");
			String id_Db = (String) data.get("id_Db");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_simati", id_Simati);
			r.add("no_Sijil_Mati", no_Sijil_Mati);
			r.add("id_Buktimati", id_Buktimati);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			r.add("id_Masuk", "");
			r.add("tarikh_Masuk", "");
			r.add("id_Kemaskini", id_Masuk);
			// r.add("tarikh_Kemaskini",currentDate);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("id_Db", id_Db);
			sql = r.getSQLUpdate("tblppksimati");
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatepemohon(Hashtable data) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sqlOB = "";
		try {

			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			String nama_pelbagainegara_surat = (String) data.get("nama_pelbagainegara_surat");

			String nama_warga = (String) data.get("nama_warga");

			int pemohonwaris = (Integer) data.get("pemohonwaris");

			String id_Pemohon = (String) data.get("idPemohon");
			// int id_Ob = (Integer)data.get("idOb");
			String id_Simati = (String) data.get("idSimati");
			String id_Permohonan = (String) data.get("idPermohonan");
			String adataraf = (String) data.get("adataraf");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");

			String nama_Pemohon = (String) data.get("namaPemohon");
			//IL
			String nama_Lain = (String) data.get("namaLain");
			if(nama_Lain == null){
				nama_Lain = "";
			}	
			//end IL
			String no_Kp_Baru = (String) data.get("noKpBaru");
			// System.out.println("UMURRR"+(Integer)data.get("umur"));
			String jenis_Kp = (String) data.get("jenisKp");
			String no_Kp_Lain = (String) data.get("noKpLain");
			String umur = (String) data.get("umur");
			String jantina = (String) data.get("jantina");
			//String taraf = data.get("taraf") == null ? "" : (String) data.get("taraf");
			int taraf = (Integer) data.get("taraf");
			String saudara = data.get("saudara") == null ? "" : (String) data.get("saudara");
			String jenisWarga = (String) data.get("jenisWarga");
			String jenisAgama = (String) data.get("jenisAgama");
			String alamat_1 = (String) data.get("alamat1");
			String alamat_2 = (String) data.get("alamat2");
			String alamat_3 = (String) data.get("alamat3");
			// String bandar = (String)data.get("bandar");

			// String peguam = (String)data.get("peguam");

			String bandartetap = (String) data.get("bandartetap");
			int bandar_tetap = 0;
			if (bandartetap != "" && bandartetap != "0") {
				bandar_tetap = Integer.parseInt(bandartetap);
			} else {
				bandar_tetap = 0;
			}

			String poskod = (String) data.get("poskod");
			int id_Negeri = (Integer) data.get("idnegeri");

			String alamat_1Surat = (String) data.get("alamat1Surat");
			String alamat_2Surat = (String) data.get("alamat2Surat");
			String alamat_3Surat = (String) data.get("alamat3Surat");
			String bandarsurat = (String) data.get("bandarsurat");
			int bandar_surat = 0;
			if (bandarsurat != "" && bandarsurat != "0") {
				bandar_surat = Integer.parseInt(bandarsurat);
			} else {
				bandar_surat = 0;
			}
			String poskodSurat = (String) data.get("poskodSurat");
			int id_NegeriSurat = (Integer) data.get("idnegeriSurat");

			String catatan = (String) data.get("catatan");
			String nofaks = (String) data.get("nofaks");
			String notelefon = (String) data.get("notelefon");
			String notelefonbimbit = (String) data.get("notelefonbimbit");
			String emel = (String) data.get("emel");
			String status_Peguam = (String) data.get("status_Peguam");
			String status_Pemohon = (String) data.get("status_Pemohon");

			String no_Kp_Lama = (String) data.get("no_Kp_Lama");

			String id_Kemaskini = (String) data.get("id_Kemaskini");

			// String status_Peguam = (String)data.get("status_Peguam");

			String txdTarikhLahirPemohon = (String) data.get("txdTarikhLahirPemohon");
			String tarikhpemohon_format = "to_date('" + txdTarikhLahirPemohon + "','dd/MM/yyyy')";

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Pemohon", id_Pemohon);

			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);

			r.add("nama_warga", nama_warga);

			r.add("nama_Pemohon", nama_Pemohon);
			r.add("nama_Lain", nama_Lain);//IL
			r.add("no_Kp_Baru", no_Kp_Baru);
			r.add("no_Kp_Lama", no_Kp_Lama);
			r.add("jenis_Kp", jenis_Kp);
			r.add("no_Kp_Lain", no_Kp_Lain);
			if (umur != "0") {
				r.add("umur", umur);
			}
			if (umur == "0") {
				r.add("umur", "");
			}
			r.add("jantina", jantina);
			r.add("jenis_Warga", jenisWarga);
			r.add("jenis_Agama", jenisAgama);

			r.add("alamat_1", alamat_1);
			r.add("alamat_2", alamat_2);
			r.add("alamat_3", alamat_3);
			// r.add("bandar",bandar);

			// System.out.println("TRY TGOK BANDAR :"+bandar_tetap);

			r.add("id_Bandar", bandar_tetap);

			r.add("poskod", poskod);
			r.add("no_Hp", notelefonbimbit);
			r.add("no_Tel", notelefon);
			//IL
			if (emel != "" && emel != null) {
				r.add("emel", emel);
			}
			//r.add("emel", emel);
			//end IL
			r.add("no_Fax", nofaks);
			r.add("catatan", catatan);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);

			r.add("id_Kemaskini", id_Kemaskini);

			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			r.add("id_Saudara", saudara);

			r.add("id_Negeri", id_Negeri);

			r.add("alamat1_Surat", alamat_1Surat);
			r.add("alamat2_Surat", alamat_2Surat);
			r.add("alamat3_Surat", alamat_3Surat);
			r.add("poskod_Surat", poskodSurat);
			r.add("id_Bandarsurat", bandar_surat);

			r.add("id_Negerisurat", id_NegeriSurat);

			/*
			 * 
			 * if(taraf!=0) { r.add("id_Tarafkptg",taraf); }
			 */

			r.add("id_Tarafkptg", taraf);

			// r.add("id_Permohonan",id_Permohonan);

			r.add("status_Peguam", status_Peguam);
			r.add("status_Pemohon", status_Pemohon);

			sql = r.getSQLUpdate("tblppkpemohon");
			stmt.executeUpdate(sql);

			// System.out.println("???????::"+adataraf);

			int chek = 0;
			if (adataraf.equals("ada")) {
				chek = 1;
				// System.out.println("???????:: (1) CHEK::"+chek);
			} else {
				chek = 0;
				// System.out.println("???????:: (0) CHEK::"+chek);
			}

			if (pemohonwaris == 0 && taraf != 0) {
				
				long id_ob = DB.getNextID("TBLPPKOB_SEQ");
				
				// db2 = new Db();
				Statement stmt2 = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				r2.add("id_ob", id_ob);
				r2.add("id_Simati", id_Simati);
				r2.add("nama_Ob", nama_Pemohon);
				r2.add("nama_Lain", nama_Lain);//IL
				r2.add("nama_warga", nama_warga);
				r2.add("no_Kp_Baru", no_Kp_Baru);
				r2.add("no_Kp_Lain", no_Kp_Lain);
				r2.add("no_Kp_Lama", no_Kp_Lama);
				r2.add("jenis_Kp", jenis_Kp);
				r2.add("id_Tarafkptg", taraf);
				r2.add("id_Saudara", saudara);
				r2.add("id_Permohonansimati", id_Permohonansimati);
				// r2.add("no_Kp_Lama",no_Kp_Lama);

				r2.add("jantina", jantina);
				r2.add("jenis_Agama", jenisAgama);
				r2.add("jenis_Warga", jenisWarga);
				r2.add("no_Hp", notelefonbimbit);
				//IL
				if (emel != "" && emel != null) {
					r2.add("emel", emel);
				}
				//r2.add("emel", emel);
				//end IL
				r2.add("no_Tel", notelefon);

				r2.add("umur", umur);

				r2.add("alamat_1", alamat_1);
				r2.add("alamat_2", alamat_2);
				r2.add("alamat_3", alamat_3);
				r2.add("id_Bandar", bandar_tetap);
				r2.add("poskod", poskod);
				r2.add("lapis", 1);
				r2.add("status_hidup", 0);
				r2.add("id_Pemohon", id_Pemohon);

				r2.add("id_Negeri", id_Negeri);

				r2.add("id_Kemaskini", id_Kemaskini);
				r2.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r2.add("id_Masuk", id_Kemaskini);
				r2.add("tarikh_Masuk", r.unquote("sysdate"));

				r2.add("alamat1_Surat", alamat_1Surat);
				r2.add("alamat2_Surat", alamat_2Surat);
				r2.add("alamat3_Surat", alamat_3Surat);
				r2.add("poskod_Surat", poskodSurat);
				r2.add("id_Bandarsurat", bandar_surat);

				r2.add("id_Negerisurat", id_NegeriSurat);

				if (txdTarikhLahirPemohon != "") {
					r2.add("tarikh_Lahir", r.unquote(tarikhpemohon_format));
				}
				
				r2.add("nama_pelbagainegara", nama_pelbagainegara);
				r2.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);

				sql2 = r2.getSQLInsert("tblppkob");
				stmt2.executeUpdate(sql2);
				// update pemohon xperlu update ob (penambahbaikkan)
				

				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", id_Simati);
				r.add("nama_Ob", nama_Pemohon.toUpperCase());
				
					r.add("no_Kp_Baru", no_Kp_Baru);
					r.add("no_Kp_Lain", no_Kp_Lain);
					r.add("no_Kp_Lama", no_Kp_Lama);
					r.add("jenis_Kp", jenis_Kp);
					r.add("jantina", jantina);
					r.add("umur", umur);
					r.add("no_Hp", notelefonbimbit);
				
				//IL
				if (emel != "" && emel != null) {
					r.add("emel", emel);
				}
				//r.add("emel", emel);
				//end IL

				r.add("id_Tarafkptg", taraf);
				//r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Tel", notelefon);
				r.add("alamat_1", alamat_1.toUpperCase());
				r.add("alamat_2", alamat_2.toUpperCase());
				r.add("alamat_3", alamat_3.toUpperCase());
				r.add("id_Bandar", bandar_tetap);
				r.add("poskod", poskod);
				r.add("id_Negeri", id_Negeri);				
				r.add("id_saudara", saudara);
				r.add("alamat1_Surat", alamat_1Surat.toUpperCase());
				r.add("alamat2_Surat", alamat_2Surat.toUpperCase());
				r.add("alamat3_Surat", alamat_3Surat.toUpperCase());
				r.add("poskod_Surat", poskodSurat);
				r.add("id_Bandarsurat", bandar_surat);
				r.add("id_Negerisurat", id_NegeriSurat);
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("id_Pemohon", id_Pemohon);
				//r.add("id_Arb", jenis_pej);
				r.add("id_Kemaskini", id_Kemaskini);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Kemaskini);
				r.add("tarikh_Masuk", r.unquote("sysdate"));				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);			
				sqlOB = r.getSQLInsert("tblppkobpermohonan");					
				stmt.executeUpdate(sqlOB);

			}

			// if(chek==1 && taraf!=0)
			if (chek == 1 && pemohonwaris > 0) {
				// db1 = new Db();
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.update("id_Pemohon", id_Pemohon);
				r1.add("nama_warga", nama_warga);
				r1.add("id_Simati", id_Simati);
				r1.add("nama_Ob", nama_Pemohon);
				r1.add("nama_Lain", nama_Lain);//IL
				r1.add("no_Kp_Baru", no_Kp_Baru);
				r1.add("no_Kp_Lain", no_Kp_Lain);
				// r2.add("no_Kp_Lama",no_Kp_Lama);
				r1.add("jenis_Kp", jenis_Kp);
				r1.add("id_Tarafkptg", taraf);
				r1.add("id_Saudara", saudara);
				r1.add("jantina", jantina);
				r1.add("jenis_Agama", jenisAgama);
				r1.add("jenis_Warga", jenisWarga);
				r1.add("no_Hp", notelefonbimbit);
				//IL
				if (emel != "" && emel != null) {
					r1.add("emel", emel);
				}
				//r1.add("emel", emel);
				//end IL
				r1.add("no_Tel", notelefon);
				r1.add("no_Kp_Lama", no_Kp_Lama);
				if (umur != "0" && umur != "0") {
					r1.add("umur", umur);
				}
				r1.add("alamat_1", alamat_1);
				r1.add("alamat_2", alamat_2);
				r1.add("alamat_3", alamat_3);
				r1.add("id_Bandar", bandar_tetap);
				r1.add("poskod", poskod);
				r1.add("lapis", 1);

				r1.add("id_Negeri", id_Negeri);

				r1.add("alamat1_Surat", alamat_1Surat);
				r1.add("alamat2_Surat", alamat_2Surat);
				r1.add("alamat3_Surat", alamat_3Surat);
				r1.add("poskod_Surat", poskodSurat);
				r1.add("id_Bandarsurat", bandar_surat);
				r1.add("id_Negerisurat", id_NegeriSurat);

				if (txdTarikhLahirPemohon != "") {
					r1.add("tarikh_Lahir", r.unquote(tarikhpemohon_format));
				}

				r1.add("id_Kemaskini", id_Kemaskini);
				r1.add("tarikh_Kemaskini", r.unquote("sysdate"));
				
				r1.add("nama_pelbagainegara", nama_pelbagainegara);
				r1.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);

				sql1 = r1.getSQLUpdate("tblppkob");
				stmt1.executeUpdate(sql1);
				// penambahbaikkan, update pemohon xperlu update O
				
				r.clear();
				r.update("id_Pemohon", id_Pemohon);
				r.update("id_Permohonansimati",id_Permohonansimati);
				r.add("id_Simati", id_Simati);
				r.add("nama_Ob", nama_Pemohon.toUpperCase());
				
					r.add("no_Kp_Baru", no_Kp_Baru);
					r.add("no_Kp_Lain", no_Kp_Lain);
					r.add("no_Kp_Lama", no_Kp_Lama);
					r.add("jenis_Kp", jenis_Kp);
					r.add("jantina", jantina);
					r.add("umur", umur);
					r.add("no_Hp", notelefonbimbit);
				//IL
				if (emel != "" && emel != null) {
					r.add("emel", emel);
				}
				//r.add("emel", emel);
				//end IL

				r.add("id_Tarafkptg", taraf);
				//r.add("jenis_pemiutang", jenis_pemohon);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Tel", notelefon);
				r.add("alamat_1", alamat_1.toUpperCase());
				r.add("alamat_2", alamat_2.toUpperCase());
				r.add("alamat_3", alamat_3.toUpperCase());
				r.add("id_Bandar", bandar_tetap);
				r.add("poskod", poskod);
				r.add("id_Negeri", id_Negeri);				
				r.add("id_saudara", saudara);
				r.add("alamat1_Surat", alamat_1Surat.toUpperCase());
				r.add("alamat2_Surat", alamat_2Surat.toUpperCase());
				r.add("alamat3_Surat", alamat_3Surat.toUpperCase());
				r.add("poskod_Surat", poskodSurat);
				r.add("id_Bandarsurat", bandar_surat);
				r.add("id_Negerisurat", id_NegeriSurat);
				//r.add("lapis", 1);
				//r.add("status_hidup", 0);
				r.add("id_Pemohon", id_Pemohon);
				// r.add("id_Arb", jenis_pej);
				r.add("id_Kemaskini", id_Kemaskini);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r.add("id_Masuk", id_Kemaskini);
				r.add("tarikh_Masuk", r.unquote("sysdate"));				
				r.add("nama_pelbagainegara", nama_pelbagainegara);
				r.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);			
				sqlOB = r.getSQLUpdate("tblppkobpermohonan");					
				stmt.executeUpdate(sqlOB);
				
				
			}
			conn.commit();
		}

		catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}

	/*
	 * 
	 * public static void addPenting(Hashtable data) throws Exception { Db db =
	 * null; String sql = ""; try { //String ids = (String)data.get("id"); //int
	 * id=Integer.parseInt(ids); int idsimati=(Integer)data.get("idSimati");
	 * String namaob= (String)data.get("namaOB");
	 * 
	 * 
	 * String nokpbaru= (String)data.get("nokpbaru"); String nokppenting=
	 * (String)data.get("nokppenting"); String jenisKp=
	 * (String)data.get("jenisKp"); String nokplain=
	 * (String)data.get("nokplain"); String statusOB=
	 * (String)data.get("statusOB"); int taraf=(Integer)data.get("taraf");
	 * String jantina= (String)data.get("jantina"); String agama=
	 * (String)data.get("agama"); String warga= (String)data.get("warga"); int
	 * umur=(Integer)data.get("umur"); String noberanak=
	 * (String)data.get("noberanak"); String alamat1=
	 * (String)data.get("alamat1"); String alamat2= (String)data.get("alamat2");
	 * String alamat3= (String)data.get("alamat3"); String poskod=
	 * (String)data.get("poskod"); String bandar= (String)data.get("bandar");
	 * int negeri=(Integer)data.get("negeri"); String catatan=
	 * (String)data.get("catatan");
	 * 
	 * String dob = (String)data.get("dob"); String dobpenting = "to_date('" +
	 * dob + "','dd/MM/yyyy')";
	 * 
	 * 
	 * 
	 * 
	 * db = new Db(); Statement stmt = db.getStatement(); SQLRenderer r = new
	 * SQLRenderer(); r.add("id_Simati", idsimati); r.add("nama_Ob", namaob);
	 * r.add("tarikh_Lahir",r.unquote(dobpenting)); r.add("no_Kp_Baru",
	 * nokpbaru); r.add("no_Kp_Lain", nokplain); r.add("no_Kp_Lama",
	 * nokppenting); r.add("jenis_Kp", jenisKp); r.add("status_Ob", statusOB);
	 * if(taraf!=0) { r.add("id_Tarafkptg",taraf); } r.add("jantina", jantina);
	 * r.add("jenis_Agama", agama); r.add("jenis_Warga", warga); if(umur!=0) {
	 * r.add("umur", umur); } r.add("no_Surat_Beranak", noberanak);
	 * r.add("alamat_1", alamat1); r.add("alamat_2", alamat2); r.add("alamat_3",
	 * alamat3);
	 * 
	 * r.add("bandar", bandar); r.add("poskod", poskod);
	 * 
	 * if(negeri!=0) { r.add("id_Negeri", negeri); } r.add("catatan", catatan);
	 * 
	 * 
	 * 
	 * sql = r.getSQLInsert("tblppkob"); stmt.executeUpdate(sql); } finally { if
	 * (db != null) db.close(); } }
	 */

	public void updatepeguam(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String id_Pemohon = (String) data.get("idPemohon");
			String id_Kemaskini = (String) data.get("id_Kemaskini");

			String id_Peguam = (String) data.get("idPeguam");
			String nama_Firma = (String) data.get("firma");
			String alamat_1 = (String) data.get("alamat1");
			String alamat_2 = (String) data.get("alamat2");
			String alamat_3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String poskod = (String) data.get("poskod");
			String id_Negeri = (String) data.get("idnegeri");
			String no_Rujukan_Firma = (String) data.get("rujfirma");
			String no_Tel = (String) data.get("noTel");
			String no_Fax = (String) data.get("noFax");
			String emel = (String) data.get("emel");
			String nama_Peguam = (String) data.get("");
			String no_Syarikat = (String) data.get("");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Peguam", id_Peguam);
			// r.add("id_Pemohon",id_Pemohon);
			r.add("nama_Firma", nama_Firma);
			r.add("alamat1", alamat_1);
			r.add("alamat2", alamat_2);
			r.add("alamat3", alamat_3);
			r.add("id_bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", id_Negeri);
			r.add("no_Rujukan_Firma", no_Rujukan_Firma);
			r.add("no_Tel", no_Tel);
			r.add("no_Fax", no_Fax);
			//IL
			if (emel != "" && emel != null) {
				r.add("emel", emel);
			}
			//r.add("emel", emel);
			//end IL
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("id_Kemaskini", id_Kemaskini);

			sql = r.getSQLUpdate("tblppkpeguam");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void tambahpeguam(Hashtable data) throws Exception {
		Db db = null;
		// Db db1 = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		try {

			long id_Peguam = DB.getNextID("TBLPPKPEGUAM_SEQ");
			String id_Pemohon = (String) data.get("idPemohon");
			String id_Permohonan = (String) data.get("idPermohonan");
			String nama_Firma = (String) data.get("firma");
			String alamat_1 = (String) data.get("alamat1");
			String alamat_2 = (String) data.get("alamat2");
			String alamat_3 = (String) data.get("alamat3");
			String bandar = (String) data.get("bandar");
			String poskod = (String) data.get("poskod");
			int id_Negeri = (Integer) data.get("idnegeri");
			String no_Rujukan_Firma = (String) data.get("rujfirma");
			String no_Tel = (String) data.get("noTel");
			String no_Fax = (String) data.get("noFax");
			String emel = (String) data.get("emel");
			String nama_Peguam = (String) data.get("");
			String no_Syarikat = (String) data.get("");
			String id_Masuk = (String) data.get("id_Masuk");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// r.add("id_Pemohon",id_Pemohon);
			r.add("id_Peguam", id_Peguam);
			r.add("nama_Firma", nama_Firma);
			r.add("alamat1", alamat_1);
			r.add("alamat2", alamat_2);
			r.add("alamat3", alamat_3);
			r.add("id_bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", id_Negeri);
			r.add("no_Rujukan_Firma", no_Rujukan_Firma);
			r.add("no_Tel", no_Tel);
			r.add("no_Fax", no_Fax);
			r.add("emel", emel);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Masuk", id_Masuk);
			r.add("id_Kemaskini", id_Masuk);

			sql = r.getSQLInsert("tblppkpeguam");

			stmt.executeUpdate(sql);

			// db = new Db();
			Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.add("id_Pemohon", id_Pemohon);
			r2.add("id_Peguam", id_Peguam);
			r2.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r2.add("tarikh_Masuk", r.unquote("sysdate"));
			r2.add("id_Masuk", id_Masuk);
			r2.add("id_Kemaskini", id_Masuk);
			sql2 = r2.getSQLInsert("tblppkpeguampemohon");
			stmt2.executeUpdate(sql2);

			// db1 = new Db();
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();

			r1.update("id_Permohonan", id_Permohonan);
			r1.add("flag_Status_Peguam", 1);

			r2.add("id_Kemaskini", id_Masuk);
			r2.add("tarikh_Kemaskini", r.unquote("sysdate"));
			// sql = r.getSQLI("tblppkpeguam");
			sql1 = r1.getSQLUpdate("tblppkpermohonan");
			stmt1.executeUpdate(sql1);

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePenghutang(Hashtable data) throws Exception {

		Db db = null;
		String sql = "";
		try {
			String idsimati = (String) data.get("idSimati");
			String idob = (String) data.get("idob");
			String namaob = (String) data.get("namaOB");
			String jenishutang = (String) data.get("jenishutang");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			int bandar = (Integer) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			double nilaihutang = (Double) data.get("nilaihutang");
			String butiranhutang = (String) data.get("butiranhutang");
			String noakaun = (String) data.get("noakaun");

			String warga = (String) data.get("warga");
			String agama = (String) data.get("agama");

			String nama_warga = (String) data.get("nama_warga");

			String id_Kemaskini = (String) data.get("id_Kemaskini");
			String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Penghutang", idob);
			// r.add("nama_warga", nama_warga);
			r.add("no_Akaun", noakaun);
			r.add("jenis_Penghutang", jenishutang);
			r.add("butiran_Hutang", butiranhutang);
			r.add("jumlah_Hutang", nilaihutang);
			r.add("id_Simati", idsimati);
			r.add("nama_Penghutang", namaob);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);

			r.add("jenis_Warga", warga);
			r.add("jenis_Agama", agama);

			r.add("poskod", poskod);

			r.add("id_bandar", bandar);
			r.add("id_Negeri", negeri);

			r.add("id_Kemaskini", id_Kemaskini);
			// r.add("tarikh_Kemaskini",tarikh_Kemaskini);

			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppkpenghutang");
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updatePenting(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		// Db db1 = null;
		String sql1 = "";
		try {

			String nama_warga = (String) data.get("nama_warga");

			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String idsimati = (String) data.get("idSimati");
			String idob = (String) data.get("idob");
			String namaob = (String) data.get("namaOB");
			String id_Pemohon = (String) data.get("id_Pemohon");
			// h.put("jenishutang", getParam("socJenisHutangPentingU"));
			String jenishutang = (String) data.get("jenishutang");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String statusOB = (String) data.get("statusOB");
			int taraf = (Integer) data.get("taraf");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			int umur = (Integer) data.get("umur");
			String noberanak = (String) data.get("noberanak");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			double nilaihutang = (Double) data.get("nilaihutang");
			String butiranhutang = (String) data.get("butiranhutang");
			String noakaun = (String) data.get("noakaun");
			String alamat1Surat = (String) data.get("alamat1Surat");
			String alamat2Surat = (String) data.get("alamat2Surat");
			String alamat3Surat = (String) data.get("alamat3Surat");
			String poskodSurat = (String) data.get("poskodSurat");
			String bandarSurat = (String) data.get("bandarSurat");
			int negeriSurat = (Integer) data.get("negeriSurat");
			String catatan = (String) data.get("catatan");
			String dob = (String) data.get("dob");
			String notel = (String) data.get("notel");
			String dobpenting = "to_date('" + dob + "','dd/MM/yyyy')";
			String id_Kemaskini = (String) data.get("id_Kemaskini");
			String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");
			String jenis_pej = (String) data.get("jenis_pej");
			String no_fax = (String) data.get("no_fax");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String no_hp = (String) data.get("no_hp");
			String emel = (String) data.get("emel");//IL
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			int idbandar = 0;
			int idbandarSurat = 0;
			if (taraf != 6 && taraf != 8) {
				idbandar = (Integer) data.get("idbandar");
				idbandarSurat = (Integer) data.get("idbandarSurat");
			} else {
				idbandar = (Integer) data.get("bandartetap_x");
				idbandarSurat = (Integer) data.get("bandarsurat_x");
			}
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Ob", idob);
			// r.add("nama_warga", nama_warga);
			r.add("no_Akaun", noakaun);
			r.add("butiran_Hutang", butiranhutang);
			r.add("nilai_Hutang", nilaihutang);
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob.toUpperCase());
			if (taraf != 0) {
				r.add("id_Tarafkptg", taraf);
			}
			if (jenis_pemohon.equals("2") || jenishutang.equals("2")) {
				r.add("tarikh_Lahir", r.unquote(dobpenting));
				r.add("no_Kp_Baru", nokpbaru);
				r.add("no_Kp_Lain", nokplain.toUpperCase());
				r.add("no_Kp_Lama", nokppenting.toUpperCase());
				r.add("jenis_Kp", jenisKp);
				r.add("status_Ob", statusOB);
				r.add("jantina", jantina);
				r.add("jenis_Agama", agama);
				r.add("jenis_Warga", warga);
				r.add("umur", umur);
				r.add("no_Surat_Beranak", noberanak);
				r.add("no_fax", "");
				r.add("no_hp", no_hp);
				//IL
				if (emel != "" && emel != null) {
					r.add("emel", emel);
				}
				//r.add("emel", emel);	
				//end IL		
			} else {

				if (jenishutang.equals("1")) {
					r.add("no_Kp_Lama", nokppenting.toUpperCase());
				} else {
					r.add("no_Kp_Lama", "");
				}

				r.add("tarikh_Lahir", "");
				r.add("no_Kp_Baru", "");
				r.add("no_Kp_Lain", "");
				// r.add("no_Kp_Lama","");
				r.add("jenis_Kp", "");
				r.add("status_Ob", "");
				r.add("jantina", "");
				r.add("jenis_Agama", "");
				r.add("jenis_Warga", "");
				r.add("umur", "");
				r.add("no_Surat_Beranak", "");
				r.add("no_fax", no_fax);
				r.add("no_hp", "");
				//IL
				if (emel != "" && emel != null) {
					r.add("emel", emel);
				}
				//r.add("emel", "");	
				//end IL	
			}

			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("bandar", bandar);
			r.add("id_bandar", idbandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", negeri);
			r.add("catatan", catatan);
			r.add("no_Tel", notel);
			r.add("id_Kemaskini", id_Kemaskini);
			r.add("alamat1_surat", alamat1Surat.toUpperCase());
			r.add("alamat2_surat", alamat2Surat.toUpperCase());
			r.add("alamat3_surat", alamat3Surat.toUpperCase());
			r.add("bandar_surat", bandarSurat);
			r.add("id_bandarsurat", idbandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			// System.out.println("PEMIUTANG TANG:"+taraf);
			if (taraf == 2) {
				// System.out.println("PEMIUTANG TANG:");
				r.add("jenis_Pemiutang", jenishutang);
			} else {
				r.add("jenis_Pemiutang", jenis_pemohon);
			}

			if (taraf == 6 || taraf == 8) {
				r.add("id_arb", jenis_pej);
			} else {
				r.add("id_arb", "");
			}

			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			sql = r.getSQLUpdate("tblppkob");
			myLogger.info("UPDATE PENTING OB" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			if (id_Pemohon != "0" && id_Pemohon != "") {
				// db1 = new Db();
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.update("id_Pemohon", id_Pemohon);
				r1.add("nama_Pemohon", namaob.toUpperCase());

				if (taraf != 0) {
					r1.add("id_Tarafkptg", taraf);
				}

				if (jenis_pemohon.equals("2") || jenishutang.equals("2")) {
					r1.add("no_Kp_Baru", nokpbaru);
					r1.add("jenis_Kp", jenisKp);
					r1.add("no_Kp_Lain", nokplain.toUpperCase());
					if (umur != 0) {
						r1.add("umur", umur);
					}
					r1.add("jantina", jantina);
					r1.add("jenis_Warga", warga);
					r1.add("jenis_Agama", agama);
					r1.add("no_fax", "");
					r1.add("no_hp", no_hp);
					//IL
					if (emel != "" && emel != null) {
						r1.add("emel", emel);
					}
					//r1.add("emel", emel);	
					//end IL		
					r1.add("no_Kp_Lama", nokppenting.toUpperCase());
				} else {
					r1.add("no_Kp_Baru", "");
					r1.add("jenis_Kp", "");
					r1.add("no_Kp_Lain", "");
					r1.add("umur", "");
					r1.add("jantina", "");
					r1.add("jenis_Warga", "");
					r1.add("jenis_Agama", "");
					r1.add("no_fax", no_fax);
					r1.add("no_hp", "");
					r1.add("emel", "");//IL
					r1.add("no_Kp_Lama", "");
				}

				r1.add("alamat_1", alamat1.toUpperCase());
				r1.add("alamat_2", alamat2.toUpperCase());
				r1.add("alamat_3", alamat3.toUpperCase());
				r1.add("bandar", bandar);
				r1.add("id_bandar", idbandar);
				r1.add("poskod", poskod);
				r1.add("no_Tel", notel);
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				String currentDate = dateFormat.format(date);
				r1.add("status_pemohon", jenis_pemohon);

				if (taraf == 6 || taraf == 8) {
					r1.add("id_arb", jenis_pej);
				} else {
					r1.add("id_arb", "");
				}

				r1.add("id_Negeri", negeri);
				r1.add("alamat1_surat", alamat1Surat.toUpperCase());
				r1.add("alamat2_surat", alamat2Surat.toUpperCase());
				r1.add("alamat3_surat", alamat3Surat.toUpperCase());
				r1.add("bandar_surat", bandarSurat);
				r1.add("id_bandarsurat", idbandarSurat);
				r1.add("poskod_surat", poskodSurat);
				r1.add("id_Negerisurat", negeriSurat);
				r1.add("id_Kemaskini", id_Kemaskini);
				r1.add("tarikh_Kemaskini", r.unquote("sysdate"));

				// sql1 = r1.getSQLUpdate("tblppkpemohon");
				// stmt1.executeUpdate(sql1);
				// update OB xperlu update pemohon //penambabaikkan
			}

			r.clear();
			r.update("id_Ob", idob);
			r.update("id_Permohonansimati", id_Permohonansimati);
			r.add("no_Akaun", noakaun);
			r.add("butiran_Hutang", butiranhutang);
			r.add("nilai_Hutang", nilaihutang);
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob.toUpperCase());
			if (taraf != 0) {
				r.add("id_Tarafkptg", taraf);
			}
			if (jenis_pemohon.equals("2") || jenishutang.equals("2")) {
				r.add("tarikh_Lahir", r.unquote(dobpenting));
				r.add("no_Kp_Baru", nokpbaru);
				r.add("no_Kp_Lain", nokplain.toUpperCase());
				r.add("no_Kp_Lama", nokppenting.toUpperCase());
				r.add("jenis_Kp", jenisKp);
				r.add("status_Ob", statusOB);
				r.add("jantina", jantina);
				r.add("jenis_Agama", agama);
				r.add("jenis_Warga", warga);
				r.add("umur", umur);
				r.add("no_Surat_Beranak", noberanak);
				r.add("no_fax", "");
				r.add("no_hp", no_hp);
				//IL
				if (emel != "" && emel != null) {
					r.add("emel", emel);
				}
				//r.add("emel", emel);		
				//end IL	
			} else {

				if (jenishutang.equals("1")) {
					r.add("no_Kp_Lama", nokppenting.toUpperCase());
				} else {
					r.add("no_Kp_Lama", "");
				}

				r.add("tarikh_Lahir", "");
				r.add("no_Kp_Baru", "");
				r.add("no_Kp_Lain", "");
				// r.add("no_Kp_Lama","");
				r.add("jenis_Kp", "");
				r.add("status_Ob", "");
				r.add("jantina", "");
				r.add("jenis_Agama", "");
				r.add("jenis_Warga", "");
				r.add("umur", "");
				r.add("no_Surat_Beranak", "");
				r.add("no_fax", no_fax);
				r.add("no_hp", "");
				r.add("emel", "");//IL
			}

			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("bandar", bandar);
			r.add("id_bandar", idbandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", negeri);
			r.add("catatan", catatan);
			r.add("no_Tel", notel);
			r.add("id_Kemaskini", id_Kemaskini);
			r.add("alamat1_surat", alamat1Surat.toUpperCase());
			r.add("alamat2_surat", alamat2Surat.toUpperCase());
			r.add("alamat3_surat", alamat3Surat.toUpperCase());
			r.add("bandar_surat", bandarSurat);
			r.add("id_bandarsurat", idbandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			// System.out.println("PEMIUTANG TANG:"+taraf);
			if (taraf == 2) {
				// System.out.println("PEMIUTANG TANG:");
				r.add("jenis_Pemiutang", jenishutang);
			} else {
				r.add("jenis_Pemiutang", jenis_pemohon);
			}

			if (taraf == 6 || taraf == 8) {
				r.add("id_arb", jenis_pej);
			} else {
				r.add("id_arb", "");
			}

			sql = r.getSQLUpdate("tblppkobpermohonan");
			myLogger.info("UPDATE PENTING OB PERMOHONAN" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (SQLException se) {
			/*
			 * try {conn.rollback(); } catch (SQLException se2) { throw new
			 * Exception("Rollback error:"+se2.getMessage()); }
			 * se.printStackTrace(); throw new
			 * Exception("Ralat Jana Fail:"+se.getMessage());
			 */
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public void addPenting(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {

			String nama_warga = (String) data.get("nama_warga");
			String idsimati = (String) data.get("idSimati");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String namaob = (String) data.get("namaOB");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String statusOB = (String) data.get("statusOB");
			int taraf = (Integer) data.get("taraf");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			int umur = (Integer) data.get("umur");
			String noberanak = (String) data.get("noberanak");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			String idbandar = (String) data.get("idbandar");
			int negeri = (Integer) data.get("negeri");
			String alamat1Surat = (String) data.get("alamat1Surat");
			String alamat2Surat = (String) data.get("alamat2Surat");
			String alamat3Surat = (String) data.get("alamat3Surat");
			String poskodSurat = (String) data.get("poskodSurat");
			String bandarSurat = (String) data.get("bandarSurat");
			String idbandarSurat = (String) data.get("idbandarSurat");
			String notel = (String) data.get("notel");
			String jenishutang = (String) data.get("jenishutang");
			int negeriSurat = (Integer) data.get("negeriSurat");
			String catatan = (String) data.get("catatan");
			String catatanhutang = (String) data.get("catatanhutang");
			String noakaun = (String) data.get("noakaun");
			double nilaihutang = (Double) data.get("nilaihutang");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String dob = (String) data.get("dob");
			String dobpenting = "to_date('" + dob + "','dd/MM/yyyy')";
			// baruOB
			String jenis_pej = (String) data.get("jenis_pej");
			String no_fax = (String) data.get("no_fax");
			String jenis_pemohon = (String) data.get("jenis_pemohon");
			String no_hp = (String) data.get("no_hp");
			String emel = (String) data.get("emel");//IL
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			long id_ob = DB.getNextID(db, "TBLPPKOB_SEQ");

			r.add("id_ob", id_ob);
			// r.add("nama_warga", nama_warga);
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob.toUpperCase());
			if (jenis_pemohon.equals("2") || jenishutang.equals("2")) {
				r.add("tarikh_Lahir", r.unquote(dobpenting));
				r.add("no_Kp_Baru", nokpbaru);
				r.add("no_Kp_Lain", nokplain.toUpperCase());
				r.add("no_Kp_Lama", nokppenting.toUpperCase());
				r.add("jenis_Kp", jenisKp);
				r.add("status_Ob", statusOB);

				r.add("jantina", jantina);
				r.add("jenis_Agama", agama);
				r.add("jenis_Warga", warga);
				if (umur != 0) {
					r.add("umur", umur);
				}
				r.add("no_Surat_Beranak", noberanak);
				r.add("no_fax", no_fax);
				r.add("no_hp", no_hp);
			} else {
				r.add("tarikh_Lahir", "");
				r.add("no_Kp_Baru", "");
				r.add("no_Kp_Lain", "");
				if (jenishutang.equals("1")) {
					r.add("no_Kp_Lama", nokppenting.toUpperCase());
				} else {
					r.add("no_Kp_Lama", "");
				}
				r.add("jenis_Kp", "");
				r.add("status_Ob", "");
				r.add("jantina", "");
				r.add("jenis_Agama", "");
				r.add("jenis_Warga", "");
				r.add("umur", "");
				r.add("no_Surat_Beranak", "");
				r.add("no_fax", no_fax);
				r.add("no_hp", "");
			}
			if (taraf != 0) {
				r.add("id_Tarafkptg", taraf);
			}
			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("id_bandar", idbandar);
			r.add("poskod", poskod);
			r.add("alamat1_surat", alamat1Surat.toUpperCase());
			r.add("alamat2_surat", alamat2Surat.toUpperCase());
			r.add("alamat3_surat", alamat3Surat.toUpperCase());
			r.add("id_bandarsurat", idbandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);
			r.add("no_Tel", notel);
			r.add("id_Negeri", negeri);
			r.add("catatan", catatan.toUpperCase());
			if (taraf == 2) {
				r.add("jenis_Pemiutang", jenishutang);
			} else {
				// baruOB
				r.add("jenis_Pemiutang", jenis_pemohon);
			}
			r.add("butiran_Hutang", catatanhutang);
			// baruOB
			if (taraf == 6 || taraf == 8) {
				r.add("id_arb", jenis_pej);
			} else {
				r.add("id_arb", "");
			}
			r.add("no_Akaun", noakaun);
			r.add("nilai_Hutang", nilaihutang);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));

			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			sql = r.getSQLInsert("tblppkob");
			myLogger.info("ADD PENTING :" + sql);
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_ob", id_ob);
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob.toUpperCase());
			if (jenis_pemohon.equals("2") || jenishutang.equals("2")) {
				r.add("tarikh_Lahir", r.unquote(dobpenting));
				r.add("no_Kp_Baru", nokpbaru);
				r.add("no_Kp_Lain", nokplain.toUpperCase());
				r.add("no_Kp_Lama", nokppenting.toUpperCase());
				r.add("jenis_Kp", jenisKp);
				r.add("status_Ob", statusOB);

				r.add("jantina", jantina);
				r.add("jenis_Agama", agama);
				r.add("jenis_Warga", warga);
				if (umur != 0) {
					r.add("umur", umur);
				}
				r.add("no_Surat_Beranak", noberanak);
				r.add("no_fax", "");
				r.add("no_hp", no_hp);
			} else {
				r.add("tarikh_Lahir", "");
				r.add("no_Kp_Baru", "");
				r.add("no_Kp_Lain", "");
				if (jenishutang.equals("1")) {
					r.add("no_Kp_Lama", nokppenting.toUpperCase());
				} else {
					r.add("no_Kp_Lama", "");
				}
				r.add("jenis_Kp", "");
				r.add("status_Ob", "");
				r.add("jantina", "");
				r.add("jenis_Agama", "");
				r.add("jenis_Warga", "");
				r.add("umur", "");
				r.add("no_Surat_Beranak", "");
				r.add("no_fax", no_fax);
				r.add("no_hp", "");
			}
			if (taraf != 0) {
				r.add("id_Tarafkptg", taraf);
			}
			r.add("alamat_1", alamat1.toUpperCase());
			r.add("alamat_2", alamat2.toUpperCase());
			r.add("alamat_3", alamat3.toUpperCase());
			r.add("id_bandar", idbandar);
			r.add("poskod", poskod);
			r.add("alamat1_surat", alamat1Surat.toUpperCase());
			r.add("alamat2_surat", alamat2Surat.toUpperCase());
			r.add("alamat3_surat", alamat3Surat.toUpperCase());
			r.add("id_bandarsurat", idbandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);
			r.add("no_Tel", notel);
			r.add("id_Negeri", negeri);
			r.add("catatan", catatan.toUpperCase());
			if (taraf == 2) {
				r.add("jenis_Pemiutang", jenishutang);
			} else {
				// baruOB
				r.add("jenis_Pemiutang", jenis_pemohon);
			}
			r.add("butiran_Hutang", catatanhutang);
			// baruOB
			if (taraf == 6 || taraf == 8) {
				r.add("id_arb", jenis_pej);
			} else {
				r.add("id_arb", "");
			}
			r.add("no_Akaun", noakaun);
			r.add("nilai_Hutang", nilaihutang);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			sql = r.getSQLInsert("tblppkobpermohonan");
			myLogger.info("ADD PENTING PERMOHON :" + sql);
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public void addSaksi(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			// String ids = (String)data.get("id");
			// int id=Integer.parseInt(ids);
			// id_Permohonansimati
			String idsimati = (String) data.get("idSimati");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String namaob = (String) data.get("namaOB");

			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");

			String jantina = (String) data.get("jantina");

			String warga = (String) data.get("warga");

			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			String notel = (String) data.get("notel");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			int negeri = (Integer) data.get("negeri");
			String catatan = (String) data.get("catatan");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idsimati);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("nama_Ob", namaob);

			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);

			r.add("jantina", jantina);

			r.add("jenis_Warga", warga);

			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("no_Tel", notel);

			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			r.add("catatan", catatan);
			int tarsaksi = 14;
			r.add("id_Tarafkptg", tarsaksi);

			r.add("id_Masuk", id_Masuk);

			r.add("tarikh_Masuk", r.unquote("sysdate"));
			// r.add("tarikh_Masuk",tarikh_Masuk);

			sql = r.getSQLInsert("tblppkob");
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addPemiutang(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			// String ids = (String)data.get("id");
			// int id=Integer.parseInt(ids);

			String idsimati = (String) data.get("idSimati");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String namaob = (String) data.get("namaOB");
			String jenishutang = (String) data.get("jenishutang");

			double nilaihutang = (Double) data.get("nilaihutang");
			String butiranhutang = (String) data.get("butiranhutang");
			String noakaun = (String) data.get("noakaun");

			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");

			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			// String notel= (String)data.get("notel");
			int negeri = (Integer) data.get("negeri");

			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_Simati", idsimati);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("nama_Ob", namaob);

			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);

			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);

			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}

			int tarsaksi = 2;
			r.add("id_Tarafkptg", tarsaksi);

			r.add("no_Akaun", noakaun);
			r.add("butiran_Hutang", butiranhutang);
			r.add("nilai_Hutang", nilaihutang);

			r.add("jenis_Pemiutang", jenishutang);

			r.add("id_Masuk", id_Masuk);
			// r.add("tarikh_Masuk",tarikh_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));

			sql = r.getSQLInsert("tblppkob");
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addPenghutang(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String id_Masuk = (String) data.get("id_Masuk");
			String nama_warga = (String) data.get("nama_warga");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String idsimati = (String) data.get("idSimati");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String namaob = (String) data.get("namaOB");
			String jenishutang = (String) data.get("jenishutang");
			double nilaihutang = (Double) data.get("nilaihutang");
			String butiranhutang = (String) data.get("butiranhutang");
			String noakaun = (String) data.get("noakaun");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			int bandar = (Integer) data.get("bandar");
			int negeri = (Integer) data.get("negeri");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idsimati);
			// r.add("nama_warga", nama_warga);
			r.add("nama_Penghutang", namaob);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);
			r.add("jenis_Penghutang", jenishutang);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_Bandar", bandar);
			r.add("poskod", poskod);
			r.add("jenis_Warga", warga);
			r.add("jenis_Agama", agama);
			r.add("id_Negeri", negeri);
			r.add("no_Akaun", noakaun);
			r.add("butiran_Hutang", butiranhutang);
			if (nilaihutang != 0) {
				r.add("jumlah_Hutang", nilaihutang);
			} else {
				r.add("jumlah_Hutang", 0.00);
			}
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppkpenghutang");
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateSaksi(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String idsimati = (String) data.get("idSimati");
			String idob = (String) data.get("idsaksi");
			String namaob = (String) data.get("namaOB");
			String notel = (String) data.get("notel");

			String nokpbaru = (String) data.get("nokpbaru");
			String nokpsaksi = (String) data.get("nokpsaksi");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");

			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");

			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			String catatan = (String) data.get("catatan");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Ob", idob);

			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob);
			r.add("no_Tel", notel);

			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpsaksi);
			r.add("jenis_Kp", jenisKp);

			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);

			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);

			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			r.add("catatan", catatan);

			sql = r.getSQLUpdate("tblppkob");
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListPenting() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id");
			r.add("name");
			r.add("description");

			sql = r.getSQLSelect("Tblppktest");
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getInt("id"));
				h.put("description", rs.getString("description"));

				if (rs.getString("name") == null) {
					h.put("name", "");
				} else {
					h.put("name", rs.getString("name"));
				}
				/*
				 * if(rs.getString("description") == null){ h.put("description",
				 * ""); }else{ h.put("description",
				 * rs.getString("description")); }
				 */
				v.addElement(h);
			}
			return v;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getUserType(String user_id) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT USER_TYPE FROM USERS WHERE USER_ID='" + user_id + "'";
			myLogger.info("JENIS_USER" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				if (rs.getString("USER_TYPE") == null) {
					h.put("USER_TYPE", "");
				} else {
					h.put("USER_TYPE", rs.getString("USER_TYPE"));
				}
				v.addElement(h);
			}
			return v;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateNilaiHartaBaruHta(String id, String NilaiMohon, String NilaiMati, String idSimati, String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			// int idHta = Integer.parseInt(id);
			// int IdPermohonan = Integer.parseInt(idPermohonan);
			// int idsimati = Integer.parseInt(idSimati);
			String nilaiHtaTarikhMohon = NilaiMohon;
			String nilaiHtaTarikhMati = NilaiMati;

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			// r.update("ID_HTA",idHta);
			r.update("ID_HTA", id);
			r.update("ID_PERMOHONANSIMATI", idSimati);

			if (nilaiHtaTarikhMohon != "") {
				r.add("NILAI_HTA_TARIKHMOHON", Double.parseDouble(nilaiHtaTarikhMohon));
			} else {
				r.add("NILAI_HTA_TARIKHMOHON", nilaiHtaTarikhMohon);
			}

			if (nilaiHtaTarikhMati != "") {
				r.add("NILAI_HTA_TARIKHMATI", Double.parseDouble(nilaiHtaTarikhMati));
			} else {
				r.add("NILAI_HTA_TARIKHMATI", nilaiHtaTarikhMati);
			}

			sql = r.getSQLUpdate("tblppkhta");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();

			r.update("ID_HTA", id);
			r.update("ID_PERMOHONANSIMATI", idSimati);
			if (nilaiHtaTarikhMohon != "") {
				r.add("NILAI_HTA_TARIKHMOHON", Double.parseDouble(nilaiHtaTarikhMohon));
			} else {
				r.add("NILAI_HTA_TARIKHMOHON", nilaiHtaTarikhMohon);
			}
			if (nilaiHtaTarikhMati != "") {
				r.add("NILAI_HTA_TARIKHMATI", Double.parseDouble(nilaiHtaTarikhMati));
			} else {
				r.add("NILAI_HTA_TARIKHMATI", nilaiHtaTarikhMati);
			}
			sql = r.getSQLUpdate("tblppkhtapermohonan");
			stmt.executeUpdate(sql);

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateNilaiHartaBaruHa(String id, String NilaiMohon, String NilaiMati, String idSimati, String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";
		String sql2 = "";
		try {
			// int idHa = Integer.parseInt(id);
			// int IdPermohonan = Integer.parseInt(idPermohonan);
			// int idsimati = Integer.parseInt(idSimati);
			String nilaiHtaTarikhMohon = NilaiMohon;
			String nilaiHtaTarikhMati = NilaiMati;

			db = new Db();
			Statement stmtA = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_HA", id);
			r1.update("ID_PERMOHONANSIMATI", idSimati);
			if (nilaiHtaTarikhMohon != "") {
				r1.add("NILAI_HA_TARIKHMOHON", Double.parseDouble(nilaiHtaTarikhMohon));
			} else {
				r1.add("NILAI_HA_TARIKHMOHON", nilaiHtaTarikhMohon);
			}

			if (nilaiHtaTarikhMati != "") {
				r1.add("NILAI_HA_TARIKHMATI", Double.parseDouble(nilaiHtaTarikhMati));
			} else {
				r1.add("NILAI_HA_TARIKHMATI", nilaiHtaTarikhMati);
			}
			sql = r1.getSQLUpdate("tblppkha");
			myLogger.info("UPDATE NILAIAN HA :" + sql.toUpperCase());

			stmtA.executeUpdate(sql);

			r1 = new SQLRenderer();
			r1.update("ID_HA", id);
			r1.update("ID_PERMOHONANSIMATI", idSimati);
			if (nilaiHtaTarikhMohon != "") {
				r1.add("NILAI_HA_TARIKHMOHON", Double.parseDouble(nilaiHtaTarikhMohon));
			} else {
				r1.add("NILAI_HA_TARIKHMOHON", nilaiHtaTarikhMohon);
			}

			if (nilaiHtaTarikhMati != "") {
				r1.add("NILAI_HA_TARIKHMATI", Double.parseDouble(nilaiHtaTarikhMati));
			} else {
				r1.add("NILAI_HA_TARIKHMATI", nilaiHtaTarikhMati);
			}
			sql = r1.getSQLUpdate("tblppkhapermohonan");
			myLogger.info("UPDATE NILAIAN HA :" + sql.toUpperCase());

			stmtA.executeUpdate(sql);

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateWaris(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		try {

			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			String nama_pelbagainegara_surat = (String) data.get("nama_pelbagainegara_surat");

			String nama_warga = (String) data.get("nama_warga");
			String idwaris = (String) data.get("idwaris");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String idsimati = (String) data.get("idSimati");
			String namaob = (String) data.get("namaOB");
			//IL
			String namalain = (String) data.get("namaLain");
			if(namalain==null){
				namalain = "";
			}
			//end IL
			String nokpbaru = (String) data.get("nokpbaru");
			String id_Pemohon = (String) data.get("id_Pemohon");
			String nokpwaris = (String) data.get("nokpwaris");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String nokplama = (String) data.get("nokplama");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			int bandar = (Integer) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			String alamat1Surat = (String) data.get("alamat1Surat");
			String alamat2Surat = (String) data.get("alamat2Surat");
			String alamat3Surat = (String) data.get("alamat3Surat");
			String poskodSurat = (String) data.get("poskodSurat");
			String bandarSurat = (String) data.get("bandarSurat");
			String negeriSurat = (String) data.get("negeriSurat");
			String saudara = data.get("saudara") == null ? "" : (String) data.get("saudara");
			String catatan = (String) data.get("catatan");
			String notel = (String) data.get("notel");
			String nohp = (String) data.get("hp");
			String emel = (String) data.get("emel");//IL
			//int statusWaris = (Integer) data.get("statusWaris");
			String statusWaris = data.get("statusWaris") == null ? "" : (String) data.get("statusWaris");
			String waktumatiwaris = (String) data.get("waktumatiwaris");
			String checkmati = (String) data.get("checkmati");
			String tarikhmati = (String) data.get("tarikhmati");
			String tarikhlahir = (String) data.get("tarikhlahir");
			String tarikhmati_format = "to_date('" + tarikhmati + "','dd/MM/yyyy')";
			String tarikhlahir_format = "to_date('" + tarikhlahir + "','dd/MM/yyyy')";
			//String umur = data.get("umur") == null ? "" : (String) data.get("umur");
			int umur = (Integer) data.get("umur");
			String noberanak = (String) data.get("noberanak");
			String id_Kemaskini = (String) data.get("id_Kemaskini");
			String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");
			String jeniswaktu = (String) data.get("jeniswaktu");
			String id_ob_list_getListOBUpdate = (String) data.get("id_ob_list_getListOBUpdate");

			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Ob", idwaris);
			// r.update("id_Permohonansimati",id_Permohonansimati);

			r.add("nama_warga", nama_warga);
			r.add("id_Simati", idsimati.toUpperCase());

			myLogger.info("id_ob_list_getListOBUpdate:" + id_ob_list_getListOBUpdate);
			if (id_ob_list_getListOBUpdate != "" && id_ob_list_getListOBUpdate != null) {
				Statement stmt_get = db.getStatement();
				SQLRenderer r_get = new SQLRenderer();
				sql = "select lapis from tblppkob where id_ob = '" + id_ob_list_getListOBUpdate + "'";
				String get_lapis = "";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					get_lapis = rs.getString("lapis");
				}
				if (!get_lapis.equals("")) {
					r.add("lapis", Integer.parseInt(get_lapis) + 1);
					myLogger.info("ADD LAPIS " + Integer.parseInt(get_lapis) + 1);
				}
			} else {
				myLogger.info("ADD LAPIS 1");
				r.add("lapis", 1);
			}
			r.add("JENIS_WAKTU_KEMATIAN", jeniswaktu);
			r.add("nama_Ob", namaob.toUpperCase());
			//IL
			if (namalain != "") {
				r.add("nama_Lain", namalain);
			}
			//end IL
			if (tarikhmati != "") {
				r.add("tarikh_Mati", r.unquote(tarikhmati_format));
			}
			r.add("status_Hidup", checkmati);
			r.add("waktu_Kematian", waktumatiwaris);

			r.add("no_Tel", notel);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpwaris);
			r.add("jenis_Kp", jenisKp);
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", negeri);
			r.add("alamat1_surat", alamat1Surat);
			r.add("alamat2_surat", alamat2Surat);
			r.add("alamat3_surat", alamat3Surat);
			r.add("id_bandarsurat", bandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);

			r.add("catatan", catatan);
			r.add("no_Hp", nohp);
			//IL
			if (emel != "" && emel != null) {
				r.add("emel", emel);
			}
			//end IL
			if (saudara != "" && saudara != null) {
				r.add("id_Saudara", saudara);
			}
			r.add("status_Ob", statusWaris);

			if (tarikhlahir != "") {
				r.add("tarikh_Lahir", r.unquote(tarikhlahir_format));
			}
			if (umur != 0) {
				r.add("umur", umur);
			}
			r.add("no_Surat_beranak", noberanak);

			r.add("id_Kemaskini", id_Kemaskini);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);

			sql = r.getSQLUpdate("tblppkob");
			myLogger.info("UPDATE OB MAIN :" + sql);
			stmt.executeUpdate(sql);

			r.clear();
			r.update("id_Ob", idwaris);
			r.update("id_Permohonansimati", id_Permohonansimati);
			r.add("nama_warga", nama_warga);
			r.add("JENIS_WAKTU_KEMATIAN", jeniswaktu);
			r.add("id_Simati", idsimati.toUpperCase());
			r.add("nama_Ob", namaob.toUpperCase());
			//IL
			if (namalain != "" && namalain !=null) {
				r.add("nama_Lain", namalain);
			}
			//end IL
			r.add("no_Tel", notel);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpwaris);
			r.add("jenis_Kp", jenisKp);
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", negeri);
			r.add("alamat1_surat", alamat1Surat);
			r.add("alamat2_surat", alamat2Surat);
			r.add("alamat3_surat", alamat3Surat);
			r.add("id_bandarsurat", bandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);
			myLogger.info("id_ob_list_getListOBUpdate:"
					+ id_ob_list_getListOBUpdate);
			if (id_ob_list_getListOBUpdate != ""
					&& id_ob_list_getListOBUpdate != null) {
				Statement stmt_get = db.getStatement();
				SQLRenderer r_get = new SQLRenderer();
				sql = "select lapis from tblppkob where id_ob = '"
						+ id_ob_list_getListOBUpdate + "'";
				String get_lapis = "";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					get_lapis = rs.getString("lapis");
				}
				if (!get_lapis.equals("")) {
					r.add("lapis", Integer.parseInt(get_lapis) + 1);
				}
			} else {
				r.add("lapis", 1);
			}
			r.add("catatan", catatan);
			r.add("no_Hp", nohp);
			//IL
			//r.add("emel", emel);
			if (emel != "" && emel != null) {
				r.add("emel", emel);
			}	
			//end IL

			if (saudara != "" && saudara != null) {
				r.add("id_Saudara", saudara);
			}
			r.add("status_Ob", statusWaris);
			r.add("status_Hidup", checkmati);
			r.add("waktu_Kematian", waktumatiwaris);
			if (tarikhmati != "") {
				r.add("tarikh_Mati", r.unquote(tarikhmati_format));
			}
			if (tarikhlahir != "") {
				r.add("tarikh_Lahir", r.unquote(tarikhlahir_format));
			}
			if (umur != 0) {
				r.add("umur", umur);
			}
			r.add("no_Surat_beranak", noberanak);
			r.add("id_Kemaskini", id_Kemaskini);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			
			
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);
			
			
			sql = r.getSQLUpdate("tblppkobpermohonan");
			myLogger.info("UPDATE OB SUB :" + sql);
			stmt.executeUpdate(sql);

			if (id_Pemohon != "0" && id_Pemohon != "") {
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.update("id_Pemohon", id_Pemohon);
				r1.add("nama_warga", nama_warga);
				r1.add("nama_Pemohon", namaob);
				r1.add("no_Kp_Baru", nokpbaru);
				r1.add("jenis_Kp", jenisKp);
				r1.add("no_Kp_Lain", nokplain);
				if (umur != 0) {
					r1.add("umur", umur);
				}
				/*if (umur == 0) {
					r1.add("umur", "");
				}*/
				r1.add("jantina", jantina);
				r1.add("jenis_Warga", warga);
				r1.add("jenis_Agama", agama);
				r1.add("alamat_1", alamat1);
				r1.add("alamat_2", alamat2);
				r1.add("alamat_3", alamat3);
				r1.add("id_bandar", bandar);
				r1.add("poskod", poskod);
				r1.add("id_Negeri", negeri);
				r1.add("alamat1_surat", alamat1Surat);
				r1.add("alamat2_surat", alamat2Surat);
				r1.add("alamat3_surat", alamat3Surat);
				r1.add("id_bandarsurat", bandarSurat);
				r1.add("poskod_surat", poskodSurat);
				r1.add("id_Negerisurat", negeriSurat);
				r1.add("no_Hp", nohp);
				//IL
				if (emel != "" && emel != null) {
					r1.add("emel", emel);
				}
				//r1.add("emel", emel);	
				//end IL			
				r1.add("no_Tel", notel);
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				String currentDate = dateFormat.format(date);
				r1.add("id_Kemaskini", id_Kemaskini);
				r1.add("tarikh_Kemaskini", r.unquote("sysdate"));
				r1.add("id_Saudara", saudara);
				r1.add("id_Tarafkptg", 1);
				r1.add("no_Kp_Lama", nokpwaris);
				
				
				r1.add("nama_pelbagainegara", nama_pelbagainegara);
				r1.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);
				
				//System.out.println("id_Pemohon==="+id_Pemohon);
				
				sql1 = r1.getSQLUpdate("tblppkpemohon");
				myLogger.info("update tblppkpemohon : "+sql1);
				stmt1.executeUpdate(sql1);
				// update waris xperlu update pemohon skali

			}
			long id_hubunganob = 0;
			if (id_ob_list_getListOBUpdate != ""
					&& id_ob_list_getListOBUpdate != null) {
				int check_hub = 0;
				Statement stmt_get = db.getStatement();
				SQLRenderer r_get = new SQLRenderer();
				sql = "select ob.id_ob,ob.id_parentob from TBLPPKHUBUNGANOB ob"
						+ " where ob.id_ob = '" + idwaris + "' ";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					check_hub++;
				}
				if (check_hub > 0) {
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.update("id_Ob", idwaris);
					r2.add("id_Parentob", id_ob_list_getListOBUpdate);
					r2.add("id_Saudara", saudara);
					r2.add("id_Masuk", id_Kemaskini);
					r2.add("tarikh_Masuk", r.unquote("sysdate"));
					sql1 = r2.getSQLUpdate("tblppkhubunganob");
					stmt2.executeUpdate(sql1);
				} else {
					id_hubunganob = DB.getNextID(db, "TBLPPKHUBUNGANOB_SEQ");
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_hubunganob", id_hubunganob);
					r2.add("id_Ob", idwaris);
					r2.add("id_Parentob", id_ob_list_getListOBUpdate);
					r2.add("id_Saudara", saudara);
					r2.add("id_Masuk", id_Kemaskini);
					r2.add("tarikh_Masuk", r.unquote("sysdate"));
					sql1 = r2.getSQLInsert("tblppkhubunganob");
					stmt2.executeUpdate(sql1);

				}
			} else {
				Statement stmt_get = db.getStatement();
				SQLRenderer r_get = new SQLRenderer();
				sql = "select hob.id_ob,hob.id_parentob from TBLPPKHUBUNGANOB ob,TBLPPKHUBUNGANOBPERMOHONAN hob" + " where "
						+ " ob.id_ob = hob.id_ob and  ob.id_ob = '" + idwaris + "' ";
				int total_row = 0;
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					total_row++;
				}

				if (total_row == 1) {
					r.clear();
					r.add("id_Ob", idwaris);
					sql = r.getSQLDelete("TBLPPKHUBUNGANOB");
					stmt.executeUpdate(sql);
				}

			}

			if (id_ob_list_getListOBUpdate != ""
					&& id_ob_list_getListOBUpdate != null) {
				int check_hub = 0;
				Statement stmt_get = db.getStatement();
				SQLRenderer r_get = new SQLRenderer();
				sql = "select ob.id_ob,ob.id_parentob from TBLPPKHUBUNGANOB ob,TBLPPKHUBUNGANOBPERMOHONAN hob"
						+ " where "
						+ " ob.id_ob = hob.id_ob and hob.id_permohonansimati "
						+ "  = '"
						+ id_Permohonansimati
						+ "' and ob.id_ob = '"
						+ idwaris + "' ";

				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					check_hub++;
				}
				if (check_hub > 0) {
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();

					r2.clear();
					r2.update("id_permohonansimati", id_Permohonansimati);
					r2.update("id_Ob", idwaris);
					r2.add("id_Parentob", id_ob_list_getListOBUpdate);
					r2.add("id_Saudara", saudara);
					r2.add("id_Masuk", id_Kemaskini);
					r2.add("tarikh_Masuk", r2.unquote("sysdate"));
					r2.add("id_Kemaskini", id_Kemaskini);
					r2.add("tarikh_Kemaskini", r2.unquote("sysdate"));
					sql1 = r2.getSQLUpdate("TBLPPKHUBUNGANOBPERMOHONAN");
					stmt2.executeUpdate(sql1);
				} else {

					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();

					r2.clear();
					r2.add("id_hubunganob", id_hubunganob);
					r2.add("id_permohonansimati", id_Permohonansimati);
					r2.add("id_Ob", idwaris);
					r2.add("id_Parentob", id_ob_list_getListOBUpdate);
					r2.add("id_Saudara", saudara);
					r2.add("id_Masuk", id_Kemaskini);
					r2.add("tarikh_Masuk", r2.unquote("sysdate"));
					r2.add("id_Kemaskini", id_Kemaskini);
					r2.add("tarikh_Kemaskini", r2.unquote("sysdate"));
					sql1 = r2.getSQLInsert("TBLPPKHUBUNGANOBPERMOHONAN");
					stmt2.executeUpdate(sql1);

				}
			} else {
				r.clear();
				r.add("id_Ob", idwaris);
				r.add("id_Permohonansimati", id_Permohonansimati);
				sql = r.getSQLDelete("TBLPPKHUBUNGANOBPERMOHONAN");
				stmt.executeUpdate(sql);
			}
			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}
	
	//salnizam check ARB dah wujud atau belum-start
	
	private static Vector semakId = new Vector();
	
	public static Vector getSemakARB(){
		return semakId;
	}
	
	public static void semakId(String id) throws Exception {
		Db db = null;
		semakId.clear();
		String sql = "Select count(id_permohonan) as cnt from tblppkkeputusanpermohonan where id_permohonan = "+ id +"";
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("cntid", rs.getString("cnt")==null?"":rs.getString("cnt"));
				semakId.addElement(h);
			}
		}finally {
			if(db != null)db.close();			
		}
}
	
	//check ARB dah wujud atau belum-end

	public void addWaris(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {

			String nama_pelbagainegara_surat = (String) data.get("nama_pelbagainegara_surat");
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");

			String nama_warga =  data.get("nama_warga") == null ? "" : (String) data.get("nama_warga");
			String idsimati = data.get("idSimati") == null ? "" : (String) data.get("idSimati");
			String id_Permohonansimati = data.get("id_Permohonansimati") == null ? "" : (String) data.get("id_Permohonansimati");
			String namaob = data.get("namaOB") == null ? "" : (String) data.get("namaOB");
			String nokpbaru = data.get("nokpbaru") == null ? "" : (String) data.get("nokpbaru");
			String nokpwaris = data.get("nokpwaris") == null ? "" : (String) data.get("nokpwaris");
			String jenisKp = data.get("jenisKp") == null ? "" : (String) data.get("jenisKp");
			String nokplain = data.get("nokplain") == null ? "" : (String) data.get("nokplain");
			String jantina = data.get("jantina") == null ? "" : (String) data.get("jantina");
			String agama = data.get("agama") == null ? "" : (String) data.get("agama");
			String warga = data.get("warga") == null ? "" : (String) data.get("warga");
			String alamat1 = data.get("alamat1") == null ? "" : (String) data.get("alamat1");
			String alamat2 = data.get("alamat2") == null ? "" : (String) data.get("alamat2");
			String alamat3 = data.get("alamat3") == null ? "" : (String) data.get("alamat3");
			String poskod = data.get("poskod") == null ? "" : (String) data.get("poskod");
			int bandar = (Integer) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			String alamat1Surat = data.get("alamat1Surat") == null ? "" : (String) data.get("alamat1Surat");
			String alamat2Surat = data.get("alamat2Surat") == null ? "" : (String) data.get("alamat2Surat");
			String alamat3Surat = data.get("alamat3Surat") == null ? "" : (String) data.get("alamat3Surat");
			String poskodSurat = data.get("poskodSurat") == null ? "" : (String) data.get("poskodSurat");
			int bandarSurat = (Integer) data.get("bandarSurat");
			int negeriSurat = (Integer) data.get("negeriSurat");
			int saudara = (Integer) data.get("saudara");
			//String saudara = data.get("saudara") == null ? "" : (String) data.get("saudara");
			String catatan = data.get("catatan") == null ? "" : (String) data.get("catatan");
			String notel = data.get("notel") == null ? "" :  (String) data.get("notel");
			String nohp = data.get("hp") == null ? "" : (String) data.get("hp");
			String emel = data.get("emel") == null ? "" : (String) data.get("emel");//IL
			int statusWaris = (Integer) data.get("statusWaris");
			//String statusWaris = (String) data.get("statusWaris");
			String waktumatiwaris = data.get("waktumatiwaris") == null ? "" : (String) data.get("waktumatiwaris");
			String checkmati = data.get("checkmati") == null ? "" : (String) data.get("checkmati");
			int umur = (Integer) data.get("umur");
			String noberanak = data.get("noberanak") == null ? "" : (String) data.get("noberanak");
			String tarikhmati = data.get("tarikhmati") == null ? "" : (String) data.get("tarikhmati");
			String tarikhlahir = data.get("tarikhlahir") == null ? "" : (String) data.get("tarikhlahir");
			String tarikhmati_format = tarikhmati == null ? "" : "to_date('" + tarikhmati + "','dd/MM/yyyy')";
			String tarikhlahir_format = tarikhlahir == null ? "" : "to_date('" + tarikhlahir + "','dd/MM/yyyy')";
			String id_Masuk = data.get("id_Masuk") == null ? "" : (String) data.get("id_Masuk");
			String tarikh_Masuk = data.get("tarikh_Masuk") == null ? "" : (String) data.get("tarikh_Masuk");
			String jeniswaktu = data.get("jeniswaktu") == null ? "" : (String) data.get("jeniswaktu");

			String FLAG_DAFTAR = data.get("FLAG_DAFTAR") == null ? "" : (String) data.get("FLAG_DAFTAR");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			long id_ob = DB.getNextID(db, "TBLPPKOB_SEQ");

			r.clear();

			r.add("id_ob", id_ob);
			r.add("nama_warga", nama_warga);
			r.add("id_Simati", idsimati);
			r.add("lapis", 1);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("id_Tarafkptg", 1);
			r.add("nama_Ob", namaob.toUpperCase());
			if (tarikhmati != "") {
				r.add("tarikh_Mati", r.unquote(tarikhmati_format));
			}
			r.add("JENIS_WAKTU_KEMATIAN", jeniswaktu);
			r.add("status_Hidup", checkmati);
			r.add("waktu_Kematian", waktumatiwaris);

			r.add("no_Tel", notel);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpwaris);
			r.add("jenis_Kp", jenisKp);
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_Bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", negeri);
			r.add("alamat1_surat", alamat1Surat);
			r.add("alamat2_surat", alamat2Surat);
			r.add("alamat3_surat", alamat3Surat);
			r.add("id_Bandarsurat", bandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);
			r.add("catatan", catatan);
			r.add("no_Hp", nohp);
			r.add("emel", emel);//IL
			if (saudara != 0) {
				r.add("id_Saudara", saudara);
			}
			r.add("status_Ob", statusWaris);

			r.add("no_Surat_beranak", noberanak);

			if (tarikhlahir != "") {
				r.add("tarikh_Lahir", r.unquote(tarikhlahir_format));
			}
			if (umur != 0) {
				r.add("umur", umur);
			}

			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", id_Masuk);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			
			
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);

			sql = r.getSQLInsert("tblppkob");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_ob", id_ob);
			r.add("nama_warga", nama_warga);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob.toUpperCase());
			r.add("no_Tel", notel);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpwaris);
			r.add("jenis_Kp", jenisKp);
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_Bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", negeri);
			r.add("alamat1_surat", alamat1Surat);
			r.add("alamat2_surat", alamat2Surat);
			r.add("alamat3_surat", alamat3Surat);
			r.add("id_Bandarsurat", bandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);
			r.add("catatan", catatan);
			r.add("no_Hp", nohp);
			r.add("emel", emel);//IL
			
			
			if (saudara != 0) {
				r.add("id_Saudara", saudara);
			}
			r.add("status_Ob", statusWaris);
			r.add("status_Hidup", checkmati);
			r.add("waktu_Kematian", waktumatiwaris);
			r.add("id_Tarafkptg", 1);
			r.add("no_Surat_beranak", noberanak);
			if (tarikhmati != "") {
				r.add("tarikh_Mati", r.unquote(tarikhmati_format));
			}
			if (tarikhlahir != "") {
				r.add("tarikh_Lahir", r.unquote(tarikhlahir_format));
			}
			if (umur != 0) {
				r.add("umur", umur);
			}
			r.add("lapis", 1);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", id_Masuk);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("JENIS_WAKTU_KEMATIAN", jeniswaktu);
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			
			
			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);
			
			
			sql = r.getSQLInsert("tblppkobpermohonan");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public void addWarisBerikut(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			
			String nama_pelbagainegara = (String) data.get("nama_pelbagainegara");
			String nama_pelbagainegara_surat = (String) data.get("nama_pelbagainegara_surat");
			
			String nama_warga = (String) data.get("nama_warga");
			String id_Permohonansimati = (String) data
					.get("id_Permohonansimati");
			long idOB_gabung = (Long) data.get("idOB_gabung");
			String idsimati = (String) data.get("idSimati");
			String namaob = (String) data.get("namaOB");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokpwaris = (String) data.get("nokpwaris");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			int bandar = (Integer) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			String alamat1Surat = (String) data.get("alamat1Surat");
			String alamat2Surat = (String) data.get("alamat2Surat");
			String alamat3Surat = (String) data.get("alamat3Surat");
			String poskodSurat = (String) data.get("poskodSurat");
			int bandarSurat = (Integer) data.get("bandarSurat");
			int negeriSurat = (Integer) data.get("negeriSurat");
			int saudara = (Integer) data.get("saudara");
			String catatan = (String) data.get("catatan");
			String notel = (String) data.get("notel");
			String nohp = (String) data.get("hp");
			int statusWaris = (Integer) data.get("statusWaris");
			String waktumatiwaris = (String) data.get("waktumatiwaris");
			String checkmati = (String) data.get("checkmati");
			int umur = (Integer) data.get("umur");
			int lapis = (Integer) data.get("lapis");
			String noberanak = (String) data.get("noberanak");
			String tarikhmati = (String) data.get("tarikhmati");
			String tarikhlahir = (String) data.get("tarikhlahir");
			String tarikhmati_format = "to_date('" + tarikhmati + "','dd/MM/yyyy')";
			String tarikhlahir_format = "to_date('" + tarikhlahir + "','dd/MM/yyyy')";
			String idparent = (String) data.get("idparent");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String jeniswaktu = (String) data.get("jeniswaktu");

			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_Ob", idOB_gabung);
			r.add("nama_warga", nama_warga);
			r.add("id_Simati", idsimati);
			if (lapis != 0) {
				r.add("lapis", lapis + 1);
			}
			r.add("id_Tarafkptg", 1);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("nama_Ob", namaob.toUpperCase());
			r.add("JENIS_WAKTU_KEMATIAN", jeniswaktu);
			r.add("status_Hidup", checkmati);
			r.add("waktu_Kematian", waktumatiwaris);
			if (tarikhmati != "") {
				r.add("tarikh_Mati", r.unquote(tarikhmati_format));
			}

			r.add("no_Tel", notel);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpwaris);
			r.add("jenis_Kp", jenisKp);
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", negeri);
			r.add("alamat1_surat", alamat1Surat);
			r.add("alamat2_surat", alamat2Surat);
			r.add("alamat3_surat", alamat3Surat);
			r.add("id_bandarsurat", bandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);
			r.add("catatan", catatan);
			r.add("no_Hp", nohp);
			if (saudara != 0) {
				r.add("id_Saudara", saudara);
			}
			r.add("status_Ob", statusWaris);
			r.add("no_Surat_beranak", noberanak);
			if (tarikhlahir != "") {
				r.add("tarikh_Lahir", r.unquote(tarikhlahir_format));
			}

			if (umur != 0) {
				r.add("umur", umur);
			}

			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));

			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);

			sql = r.getSQLInsert("tblppkob");
			stmt.executeUpdate(sql);

			r.clear();

			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("nama_warga", nama_warga);
			r.add("id_Ob", idOB_gabung);
			r.add("JENIS_WAKTU_KEMATIAN", jeniswaktu);
			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namaob.toUpperCase());
			r.add("no_Tel", notel);
			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokpwaris);
			r.add("jenis_Kp", jenisKp);
			r.add("jantina", jantina);
			r.add("jenis_Agama", agama);
			r.add("jenis_Warga", warga);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", negeri);
			r.add("alamat1_surat", alamat1Surat);
			r.add("alamat2_surat", alamat2Surat);
			r.add("alamat3_surat", alamat3Surat);
			r.add("id_bandarsurat", bandarSurat);
			r.add("poskod_surat", poskodSurat);
			r.add("id_Negerisurat", negeriSurat);
			r.add("catatan", catatan);
			if (lapis != 0) {
				r.add("lapis", lapis + 1);
			}
			r.add("no_Hp", nohp);
			if (saudara != 0) {
				r.add("id_Saudara", saudara);
			}
			r.add("status_Ob", statusWaris);
			r.add("status_Hidup", checkmati);
			r.add("waktu_Kematian", waktumatiwaris);
			r.add("id_Tarafkptg", 1);
			r.add("no_Surat_beranak", noberanak);
			if (tarikhmati != "") {
				r.add("tarikh_Mati", r.unquote(tarikhmati_format));
			}
			if (tarikhlahir != "") {
				r.add("tarikh_Lahir", r.unquote(tarikhlahir_format));
			}
			if (umur != 0) {
				r.add("umur", umur);
			}
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			r.add("nama_pelbagainegara", nama_pelbagainegara);
			r.add("nama_pelbagainegara_surat", nama_pelbagainegara_surat);

			sql = r.getSQLInsert("tblppkobpermohonan");

			stmt.executeUpdate(sql);

			long id_hubunganob = DB.getNextID(db, "TBLPPKHUBUNGANOB_SEQ");
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("id_hubunganob", id_hubunganob);
			r1.add("id_Ob", idOB_gabung);
			r1.add("id_Parentob", idparent);
			r1.add("id_Saudara", saudara);
			r1.add("id_Masuk", id_Masuk);
			r1.add("tarikh_Masuk", r.unquote("sysdate"));
			sql1 = r1.getSQLInsert("tblppkhubunganob");
			stmt1.executeUpdate(sql1);

			Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.add("id_hubunganob", id_hubunganob);
			r2.add("id_permohonansimati", id_Permohonansimati);
			r2.add("id_Ob", idOB_gabung);
			r2.add("id_Parentob", idparent);
			r2.add("id_Saudara", saudara);
			r2.add("id_Masuk", id_Masuk);
			r2.add("tarikh_Masuk", r2.unquote("sysdate"));
			r2.add("id_Kemaskini", id_Masuk);
			r2.add("tarikh_Kemaskini", r2.unquote("sysdate"));
			sql1 = r2.getSQLInsert("TBLPPKHUBUNGANOBPERMOHONAN");
			stmt2.executeUpdate(sql1);

			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Jana Fail:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}
	}

	public void setDataPemohon(String id) throws Exception {
		Db db = null;
		listPemohon.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("pm.nama_pelbagainegara");
			r.add("pm.nama_pelbagainegara_surat");
			
			r.add("p.id_Permohonan");
			r.add("pm.id_Pemohon");
			r.add("pm.no_Kp_Baru");
			r.add("pm.no_Kp_Lama");
			r.add("pm.jenis_Kp");
			r.add("pm.no_Kp_Lain");
			r.add("pm.nama_Pemohon");
			r.add("pm.nama_Lain");//IL
			r.add("pm.jantina");
			r.add("pm.jenis_Warga");
			r.add("pm.jenis_Agama");
			r.add("pm.id_Tarafkptg");
			r.add("pm.id_Saudara");
			r.add("pm.umur");
			r.add("pm.alamat_1");
			r.add("pm.alamat_2");
			r.add("pm.alamat_3");
			r.add("pm.poskod");
			r.add("pm.bandar");
			r.add("pm.id_bandar");
			r.add("pm.id_Negeri");
			r.add("pm.no_Hp");
			r.add("pm.no_Tel");
			r.add("pm.emel");
			r.add("pm.no_Fax");
			r.add("pm.catatan");
			// r.add("n.nama_Negeri");
			// r.add("tf.keterangan");
			r.add("pm.id_Saudara");
			// r.add("sa.keterangan");
			// r.add("tf.kod");

			r.add("pm.alamat1_Surat");
			r.add("pm.alamat2_Surat");
			r.add("pm.alamat3_Surat");
			r.add("pm.poskod_Surat");
			r.add("pm.bandar_Surat");
			r.add("pm.id_bandarsurat");
			r.add("pm.id_Negerisurat");
			r.add("pm.status_pemohon");
			r.add("pm.status_peguam");

			r.add("pm.nama_warga");

			// r.add("pm.id_Permohonan",r.unquote("p.id_Permohonan"));
			r.add("p.id_Pemohon", r.unquote("pm.id_Pemohon"));
			// r.add("pm.id_Negeri",r.unquote("n.id_Negeri"));
			// r.add("pm.id_Tarafkptg",r.unquote("tf.id_Tarafkptg"));
			// r.add("pm.id_Saudara",r.unquote("sa.id_Saudara"));

			r.add("p.id_Permohonan", id);

			// Tblrujnegeri n, Tblppkrujtarafkptg tf, Tblppkrujsaudara sa

			sql = r.getSQLSelect("Tblppkpemohon pm, Tblppkpermohonan p");

			myLogger.info("PEMOHON TEST::" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("nama_pelbagainegara",rs.getString("nama_pelbagainegara") == null ? "" : rs.getString("nama_pelbagainegara"));
				h.put("nama_pelbagainegara_surat",rs.getString("nama_pelbagainegara_surat") == null ? "" : rs.getString("nama_pelbagainegara_surat"));
				
				
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("noKpLama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("noKpLain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("namaLain", rs.getString("nama_Lain") == null ? "" : rs.getString("nama_Lain"));//IL
				h.put("idTarafkptg", rs.getString("id_Tarafkptg") == null ? ""
						: rs.getString("id_Tarafkptg"));

				// h.put("kodTarafkptg",
				// rs.getString(29)==null?"":rs.getString(29));

				h.put("idSaudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("jenisWarga", rs.getString("jenis_Warga") == null ? ""
						: rs.getString("jenis_Warga"));
				h.put("jenisAgama", rs.getString("jenis_Agama") == null ? ""
						: rs.getString("jenis_Agama"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("bandartetap", rs.getString("id_bandar") == null ? ""
						: rs.getString("id_bandar"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noHp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("emel", rs.getString("emel") == null ? "" : rs
						.getString("emel"));
				h.put("noFax", rs.getString("no_Fax") == null ? "" : rs
						.getString("no_Fax"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				// h.put("namanegeri",
				// rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				// h.put("keterangan",
				// rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("idsaudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				// h.put("saudara_keterangan",
				// rs.getString(28)==null?"":rs.getString(28));
				// h.put("kodtaraf",
				// rs.getString(29)==null?"":rs.getString(29));

				h.put("alamat1Surat",
						rs.getString("alamat1_surat") == null ? "" : rs
								.getString("alamat1_surat"));
				h.put("alamat2Surat",
						rs.getString("alamat2_surat") == null ? "" : rs
								.getString("alamat2_surat"));
				h.put("alamat3Surat",
						rs.getString("alamat3_surat") == null ? "" : rs
								.getString("alamat3_surat"));
				h.put("poskodSurat", rs.getString("poskod_surat") == null ? ""
						: rs.getString("poskod_surat"));
				h.put("bandarsurat",
						rs.getString("id_bandarsurat") == null ? "" : rs
								.getString("id_bandarsurat"));
				h.put("idnegeriSurat",
						rs.getString("id_Negerisurat") == null ? "" : rs
								.getString("id_Negerisurat"));

				h.put("status_pemohon",
						rs.getString("status_pemohon") == null ? "" : rs
								.getString("status_pemohon"));
				h.put("status_peguam",
						rs.getString("status_peguam") == null ? "" : rs
								.getString("status_peguam"));
				h.put("nama_warga", rs.getString("nama_warga") == null ? ""
						: rs.getString("nama_warga"));

				listPemohon.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setIdPermohonanSimati(String id) throws Exception {
		Db db = null;
		listidPermohonansimati.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();


			sql = "SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_SIMATI="+id;

			myLogger.info("sql::" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				
				
				h.put("id_permohonansimati", rs.getString("id_permohonansimati") == null ? ""
						: rs.getString("id_permohonansimati"));

				listidPermohonansimati.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	Vector listPemohonD17 = new Vector();

	public Vector getDataPemohonD17() {

		return listPemohonD17;
	}

	public void setDataPemohonD17(String id) throws Exception {
		Db db = null;
		listPemohonD17.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("p.id_Permohonan");
			r.add("pm.id_Pemohon");
			r.add("pm.no_Kp_Baru");
			r.add("pm.no_Kp_Lama");
			r.add("pm.jenis_Kp");
			r.add("pm.no_Kp_Lain");
			r.add("pm.nama_Pemohon");
			r.add("pm.nama_Lain");//IL
			r.add("pm.jantina");
			r.add("pm.jenis_Warga");
			r.add("pm.jenis_Agama");
			r.add("pm.id_Tarafkptg");
			r.add("pm.id_Saudara");
			r.add("pm.umur");
			r.add("pm.alamat_1");
			r.add("pm.alamat_2");
			r.add("pm.alamat_3");
			r.add("pm.poskod");
			r.add("pm.bandar");
			r.add("pm.id_bandar");
			r.add("pm.id_Negeri");
			r.add("pm.no_Hp");
			r.add("pm.no_Tel");
			r.add("pm.id_arb");
			r.add("pm.emel");
			r.add("pm.no_Fax");
			r.add("pm.catatan");
			// r.add("n.nama_Negeri");
			// r.add("tf.keterangan");
			r.add("pm.id_Saudara");
			// r.add("sa.keterangan");
			// r.add("tf.kod");

			r.add("pm.alamat1_Surat");
			r.add("pm.alamat2_Surat");
			r.add("pm.alamat3_Surat");
			r.add("pm.poskod_Surat");
			r.add("pm.bandar_Surat");
			r.add("pm.id_bandarsurat");
			r.add("pm.id_Negerisurat");
			r.add("pm.status_pemohon");

			// r.add("pm.id_Permohonan",r.unquote("p.id_Permohonan"));
			r.add("p.id_Pemohon", r.unquote("pm.id_Pemohon"));
			// r.add("pm.id_Negeri",r.unquote("n.id_Negeri"));
			// r.add("pm.id_Tarafkptg",r.unquote("tf.id_Tarafkptg"));
			// r.add("pm.id_Saudara",r.unquote("sa.id_Saudara"));

			r.add("pm.id_Pemohon", id);

			// sql = r.getSQLSelect("Tblppkpemohon pm, Tblppkpermohonan p");

			sql = "SELECT PM.nama_pelbagainegara,PM.nama_warga,PM.ID_OB,PM.ID_PEMOHON, PM.NO_KP_BARU, PM.NO_KP_LAMA, PM.JENIS_KP, "
					+ "PM.NO_KP_LAIN, PM.NAMA_OB,PM.NAMA_LAIN, PM.JANTINA, PM.JENIS_WARGA, PM.JENIS_AGAMA,"
					+ " PM.ID_TARAFKPTG, PM.ID_SAUDARA, PM.UMUR, PM.ALAMAT_1, PM.ALAMAT_2, "
					+ "PM.ALAMAT_3, PM.POSKOD, PM.BANDAR, PM.ID_BANDAR, PM.ID_NEGERI, "
					+ "PM.NO_HP, PM.NO_TEL, PM.ID_ARB, PM.NO_FAX, PM.CATATAN, PM.ID_SAUDARA,"
					+ " PM.ALAMAT1_SURAT, PM.ALAMAT2_SURAT, PM.ALAMAT3_SURAT, "
					+ "PM.POSKOD_SURAT, PM.BANDAR_SURAT, PM.ID_BANDARSURAT, PM.ID_NEGERISURAT,"
					+ " PM.JENIS_PEMIUTANG,PM.ID_SAUDARA FROM TBLPPKOB PM "
					+ "WHERE PM.ID_OB = '" + id + "' ";

			myLogger.info("TARIK PEMOHON OB DAHULU :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("nama_pelbagainegara", rs.getString("nama_pelbagainegara") == null ? "" : rs
						.getString("nama_pelbagainegara"));
				h.put("idob", rs.getString("id_ob") == null ? "" : rs
						.getString("id_ob"));
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				/*h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				h.put("noKpLama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("noKpLain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("namaPemohon", rs.getString("nama_ob") == null ? "" : rs
						.getString("nama_ob"));
				h.put("namaLain", rs.getString("nama_lain") == null ? "" : rs.getString("nama_lain"));//IL
				h.put("idTarafkptg", rs.getString("id_Tarafkptg") == null ? ""
						: rs.getString("id_Tarafkptg"));

				// h.put("kodTarafkptg",
				// rs.getString(29)==null?"":rs.getString(29));

				h.put("socSaudaraWaris", rs.getString("id_Saudara") == null ? "" : rs.getString("id_Saudara"));

				h.put("idSaudara", rs.getString("id_Saudara") == null ? "" : rs.getString("id_Saudara"));

				h.put("jantina", rs.getString("jantina") == null ? "" : rs.getString("jantina"));
				h.put("jenisWarga", rs.getString("jenis_Warga") == null ? "" : rs.getString("jenis_Warga"));
				h.put("jenisAgama", rs.getString("jenis_Agama") == null ? "" : rs.getString("jenis_Agama"));
				h.put("umur", rs.getString("umur") == null ? "" : rs.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs.getString("bandar"));
				h.put("bandartetap", rs.getString("id_bandar") == null ? "" : rs.getString("id_bandar"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("noHp", rs.getString("no_Hp") == null ? "" : rs.getString("no_Hp"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs.getString("no_Tel"));
				// h.put("emel",
				// rs.getString("emel")==null?"":rs.getString("emel"));
				h.put("noFax", rs.getString("no_Fax") == null ? "" : rs.getString("no_Fax"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				// h.put("namanegeri",
				// rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				// h.put("keterangan",
				// rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("idsaudara", rs.getString("id_Saudara") == null ? "" : rs.getString("id_Saudara"));
				// h.put("saudara_keterangan",
				// rs.getString(28)==null?"":rs.getString(28));
				// h.put("kodtaraf",
				// rs.getString(29)==null?"":rs.getString(29));

				h.put("alamat1Surat", rs.getString("alamat1_surat") == null ? "" : rs.getString("alamat1_surat"));
				h.put("alamat2Surat", rs.getString("alamat2_surat") == null ? "" : rs.getString("alamat2_surat"));
				h.put("alamat3Surat", rs.getString("alamat3_surat") == null ? "" : rs.getString("alamat3_surat"));
				h.put("poskodSurat", rs.getString("poskod_surat") == null ? "" : rs.getString("poskod_surat"));
				h.put("bandarsurat", rs.getString("id_bandarsurat") == null ? "" : rs.getString("id_bandarsurat"));
				h.put("idnegeriSurat", rs.getString("id_Negerisurat") == null ? "" : rs.getString("id_Negerisurat"));

				h.put("status_pemohon", rs.getString("jenis_pemiutang") == null ? "" : rs.getString("jenis_pemiutang"));

				h.put("jenis_pej", rs.getString("id_arb") == null ? "" : rs.getString("id_arb"));

				h.put("nama_warga", rs.getString("nama_warga") == null ? "" : rs.getString("nama_warga"));

				listPemohonD17.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPemohonOB(String id) throws Exception {
		Db db = null;
		listPemohonOB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("p.id_Permohonan");
			r.add("pm.id_Pemohon");
			r.add("pm.no_Kp_Baru");
			r.add("pm.no_Kp_Lama");
			r.add("pm.jenis_Kp");
			r.add("pm.no_Kp_Lain");
			r.add("pm.nama_Pemohon");
			r.add("pm.nama_Lain");//IL
			r.add("pm.jantina");
			r.add("pm.jenis_Warga");
			r.add("pm.jenis_Agama");
			r.add("pm.id_Tarafkptg");
			r.add("pm.id_Saudara");
			r.add("pm.umur");
			r.add("pm.alamat_1");
			r.add("pm.alamat_2");
			r.add("pm.alamat_3");
			r.add("pm.poskod");
			r.add("pm.bandar");
			r.add("pm.id_Negeri");
			r.add("pm.no_Hp");
			r.add("pm.no_Tel");
			r.add("pm.emel");
			r.add("pm.no_Fax");
			r.add("pm.catatan");
			// r.add("n.nama_Negeri");
			// r.add("tf.keterangan");
			r.add("pm.id_Saudara");
			// r.add("sa.keterangan");
			// r.add("tf.kod");

			// r.add("pm.id_Permohonan",r.unquote("p.id_Permohonan"));
			r.add("p.id_Pemohon", r.unquote("pm.id_Pemohon"));
			r.add("pm.id_Pemohon", r.unquote("ob.id_Pemohon"));
			// r.add("pm.id_Negeri",r.unquote("n.id_Negeri"));
			// r.add("pm.id_Tarafkptg",r.unquote("tf.id_Tarafkptg"));
			// r.add("pm.id_Saudara",r.unquote("sa.id_Saudara"));

			r.add("p.id_Permohonan", id);

			// Tblrujnegeri n, Tblppkrujtarafkptg tf, Tblppkrujsaudara sa

			sql = r
					.getSQLSelect("Tblppkpemohon pm, Tblppkpermohonan p, Tblppkob ob");
			// System.out.println("sql CHECK OB:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				/*h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				h.put("noKpLama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jenisKp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("noKpLain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("namaLain", rs.getString("nama_Lain") == null ? "" : rs.getString("nama_Lain"));//IL
				h.put("idTarafkptg", rs.getString("id_Tarafkptg") == null ? ""
						: rs.getString("id_Tarafkptg"));

				// h.put("kodTarafkptg",
				// rs.getString(29)==null?"":rs.getString(29));

				h.put("idSaudara", rs.getString("id_Saudara") == null ? "" : rs.getString("id_Saudara"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs.getString("jantina"));
				h.put("jenisWarga", rs.getString("jenis_Warga") == null ? "" : rs.getString("jenis_Warga"));
				h.put("jenisAgama", rs.getString("jenis_Agama") == null ? "" : rs.getString("jenis_Agama"));
				h.put("umur", rs.getString("umur") == null ? "" : rs.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs.getString("bandar"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("noHp", rs.getString("no_Hp") == null ? "" : rs.getString("no_Hp"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs.getString("no_Tel"));
				h.put("emel", rs.getString("emel") == null ? "" : rs.getString("emel"));
				h.put("noFax", rs.getString("no_Fax") == null ? "" : rs.getString("no_Fax"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				// h.put("namanegeri",
				// rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				// h.put("keterangan",
				// rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("idsaudara", rs.getString("id_Saudara") == null ? "" : rs.getString("id_Saudara"));
				// h.put("saudara_keterangan",
				// rs.getString(28)==null?"":rs.getString(28));
				// h.put("kodtaraf",
				// rs.getString(29)==null?"":rs.getString(29));

				listPemohonOB.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPeguamX(String idpeguam) throws Exception {
		Db db = null;
		listPeguamX.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT PM.ID_PEMOHON, PG.ID_PEGUAM, PG.NAMA_PEGUAM,"
					+ " PG.ALAMAT1, PG.ALAMAT2, PG.ALAMAT3, PG.BANDAR,PG.ID_BANDAR, PG.POSKOD, PG.ID_NEGERI,"
					+ " PG.NO_TEL, PG.NO_FAX, PG.NO_SYARIKAT, PG.NO_RUJUKAN_FIRMA, PG.NAMA_FIRMA,"
					+ " PG.EMEL"
					+ " FROM TBLPPKPEGUAM PG, TBLPPKPEMOHON PM , TBLPPKPEGUAMPEMOHON PP"
					+ " WHERE PP.ID_PEMOHON = PM.ID_PEMOHON"
					+ " AND PG.ID_PEGUAM = PP.ID_PEGUAM"
					+ " AND PG.ID_PEGUAM = '" + idpeguam + "'"
					+ " AND PG.NAMA_FIRMA IS NOT NULL" + "";

			//System.out.println("PEGUAM SIZEE XX" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			// Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs.getString("id_Pemohon"));
				h.put("idPeguam", rs.getString("id_Peguam") == null ? "" : rs.getString("id_Peguam"));
				h.put("namaPeguam", rs.getString("nama_Peguam") == null ? "" : rs.getString("nama_Peguam"));
				h.put("noRujukanFirma", rs.getString("no_Rujukan_Firma") == null ? "" : rs.getString("no_Rujukan_Firma"));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs.getString("alamat3"));
				h.put("bandar", rs.getString("id_bandar") == null ? "" : rs.getString("id_bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs.getString("no_Tel"));
				h.put("noFax", rs.getString("no_Fax") == null ? "" : rs.getString("no_Fax"));
				h.put("noSyarikat", rs.getString("no_Syarikat") == null ? "" : rs.getString("no_Syarikat"));
				h.put("namaFirma", rs.getString("nama_Firma") == null ? "" : rs.getString("nama_Firma"));
				h.put("emel", rs.getString("emel") == null ? "" : rs.getString("emel"));
				listPeguamX.addElement(h);
				// count++;
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPeguam(String id2) throws Exception {
		Db db = null;
		listPeguam.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("pm.id_Pemohon");
			r.add("pg.id_Pemohon");
			r.add("pg.id_Peguam");
			r.add("pg.nama_Peguam");
			r.add("pg.alamat1");
			r.add("pg.alamat2");
			r.add("pg.alamat3");
			r.add("pg.bandar");
			r.add("pg.poskod");
			r.add("pg.id_Negeri");
			r.add("pg.no_Tel");
			r.add("pg.no_Fax");
			r.add("pg.no_Syarikat");
			r.add("pg.no_Rujukan_Firma");
			r.add("pg.nama_Firma");
			r.add("pg.emel");

			r.add("pg.id_Pemohon", r.unquote("pm.id_Pemohon"));

			r.add("pm.id_Pemohon", id2);

			// sql = r.getSQLSelect("Tblppkpeguam pg, Tblppkpemohon pm");

			sql = "SELECT PM.ID_PEMOHON, PG.ID_PEGUAM, PG.NAMA_PEGUAM,"
					+ " PG.ALAMAT1, PG.ALAMAT2, PG.ALAMAT3, PG.BANDAR, PG.POSKOD, PG.ID_NEGERI,"
					+ " PG.NO_TEL, PG.NO_FAX, PG.NO_SYARIKAT, PG.NO_RUJUKAN_FIRMA, PG.NAMA_FIRMA,"
					+ " PG.EMEL"
					+ " FROM TBLPPKPEGUAM PG, TBLPPKPEMOHON PM , TBLPPKPEGUAMPEMOHON PP"
					+ " WHERE PP.ID_PEMOHON = PM.ID_PEMOHON"
					+ " AND PG.ID_PEGUAM = PP.ID_PEGUAM"
					+ " AND PM.ID_PEMOHON = '" + id2 + "'"
					+ " AND PG.NAMA_FIRMA IS NOT NULL" + "";

			// System.out.println("PEGUAM SIZEE"+sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			// Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				h.put("idPeguam", rs.getString("id_Peguam") == null ? "" : rs
						.getString("id_Peguam"));
				h.put("namaPeguam", rs.getString("nama_Peguam") == null ? ""
						: rs.getString("nama_Peguam"));
				h.put("noRujukanFirma",
						rs.getString("no_Rujukan_Firma") == null ? "" : rs
								.getString("no_Rujukan_Firma"));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("noFax", rs.getString("no_Fax") == null ? "" : rs
						.getString("no_Fax"));
				h.put("noSyarikat", rs.getString("no_Syarikat") == null ? ""
						: rs.getString("no_Syarikat"));
				h.put("namaFirma", rs.getString("nama_Firma") == null ? "" : rs
						.getString("nama_Firma"));
				h.put("emel", rs.getString("emel") == null ? "" : rs
						.getString("emel"));
				listPeguam.addElement(h);
				// count++;
			}
			/*
			 * if (count == 0){ h = new Hashtable(); h.put("idPemohon", "");
			 * h.put("idPeguam", ""); h.put("namaPeguam", "");
			 * h.put("noRujukanFirma", ""); h.put("alamat1", "");
			 * h.put("alamat2", ""); h.put("alamat3", ""); h.put("bandar", "");
			 * h.put("poskod", ""); h.put("idnegeri", ""); h.put("noTel", "");
			 * h.put("noFax", ""); h.put("noSyarikat", ""); h.put("namaFirma",
			 * ""); h.put("emel", ""); listPeguam.addElement(h); }
			 */
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void setDataPeguamDalamPerintah(String id2) throws Exception {
		Db db = null;
		listPeguam.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("pm.id_Pemohon");
			r.add("pg.id_Pemohon");
			r.add("pg.id_Peguam");
			r.add("pg.nama_Peguam");
			r.add("pg.alamat1");
			r.add("pg.alamat2");
			r.add("pg.alamat3");
			r.add("pg.bandar");
			r.add("pg.poskod");
			r.add("pg.id_Negeri");
			r.add("pg.no_Tel");
			r.add("pg.no_Fax");
			r.add("pg.no_Syarikat");
			r.add("pg.no_Rujukan_Firma");
			r.add("pg.nama_Firma");
			r.add("pg.emel");

			r.add("pg.id_Pemohon", r.unquote("pm.id_Pemohon"));

			//r.add("pm.id_Pemohon", id2);
			r.add("prmhn.id_Pemohon", id2);

			// sql = r.getSQLSelect("Tblppkpeguam pg, Tblppkpemohon pm");

			sql = "SELECT PM.ID_PEMOHON, PG.ID_PEGUAM, PG.NAMA_PEGUAM, PRMHN.ID_PERMOHONAN, "
					+ " PG.ALAMAT1, PG.ALAMAT2, PG.ALAMAT3, PG.BANDAR, PG.POSKOD, PG.ID_NEGERI,"
					+ " PG.NO_TEL, PG.NO_FAX, PG.NO_SYARIKAT, PG.NO_RUJUKAN_FIRMA, PG.NAMA_FIRMA,"
					+ " PG.EMEL"
					+ " FROM TBLPPKPEGUAM PG, TBLPPKPEMOHON PM , TBLPPKPEGUAMPEMOHON PP, TBLPPKPERMOHONAN PRMHN"
					+ " WHERE PP.ID_PEMOHON = PM.ID_PEMOHON"
					+ " AND PM.ID_PEMOHON = PRMHN.ID_PEMOHON"
					+ " AND PG.ID_PEGUAM = PP.ID_PEGUAM"
					+ " AND PRMHN.ID_PERMOHONAN = '" + id2 + "'"
					+ " AND PG.NAMA_FIRMA IS NOT NULL" + "";

			//System.out.println("PEGUAM SIZEE"+sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			// Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
						.getString("id_Pemohon"));
				h.put("idPeguam", rs.getString("id_Peguam") == null ? "" : rs
						.getString("id_Peguam"));
				h.put("namaPeguam", rs.getString("nama_Peguam") == null ? ""
						: rs.getString("nama_Peguam"));
				h.put("noRujukanFirma",
						rs.getString("no_Rujukan_Firma") == null ? "" : rs
								.getString("no_Rujukan_Firma"));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("noFax", rs.getString("no_Fax") == null ? "" : rs
						.getString("no_Fax"));
				h.put("noSyarikat", rs.getString("no_Syarikat") == null ? ""
						: rs.getString("no_Syarikat"));
				h.put("namaFirma", rs.getString("nama_Firma") == null ? "" : rs
						.getString("nama_Firma"));
				h.put("emel", rs.getString("emel") == null ? "" : rs
						.getString("emel"));
				listPeguam.addElement(h);
				// count++;
			}
			/*
			 * if (count == 0){ h = new Hashtable(); h.put("idPemohon", "");
			 * h.put("idPeguam", ""); h.put("namaPeguam", "");
			 * h.put("noRujukanFirma", ""); h.put("alamat1", "");
			 * h.put("alamat2", ""); h.put("alamat3", ""); h.put("bandar", "");
			 * h.put("poskod", ""); h.put("idnegeri", ""); h.put("noTel", "");
			 * h.put("noFax", ""); h.put("noSyarikat", ""); h.put("namaFirma",
			 * ""); h.put("emel", ""); listPeguam.addElement(h); }
			 */
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisUpdate(String id, String id_permohonansimati) throws Exception {
		Db db = null;
		listWarisUpdate.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.nama_Lain");//IL
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.id_bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.id_Pemohon");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.emel");//IL
			r.add("ob.waktu_Kematian");
			r.add("ob.JENIS_WAKTU_KEMATIAN");

			r.add("ob.alamat1_surat");
			r.add("ob.alamat2_surat");
			r.add("ob.alamat3_surat");
			r.add("ob.bandar_surat");
			r.add("ob.id_bandarsurat");
			r.add("ob.poskod_surat");
			r.add("ob.id_Negerisurat");

			r.add("ob.FLAG_DAFTAR");

			r.add("ob.id_Simati", r.unquote("m.id_Simati"));
			// r.add("p.id_Permohonan",r.unquote("m.id_Permohonan"));
			r.add("p.id_Permohonan", r.unquote("ms.id_Permohonan"));
			r.add("ob.id_Permohonansimati", r.unquote("ms.id_Permohonansimati"));
			r.add("ms.id_Simati", r.unquote("m.id_Simati"));

			r.add("ob.id_Ob", id);

			r.add("ob.id_Tarafkptg", 1);

			// r.add("ob.id_simati",simati);

			// sql =
			// r.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati ms");

			/*
			 * sql =
			 * " SELECT hbob.id_parentob,ob.id_Simati, ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, ob.no_Surat_Beranak, ob.tarikh_Lahir, "
			 * +
			 * " ob.jantina, ob.umur, ob.alamat_1, ob.alamat_2, ob.alamat_3, ob.bandar, ob.id_bandar, ob.poskod, ob.no_Tel, "
			 * +
			 * " ob.id_Pemohon, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri, ob.id_Saudara, "
			 * +
			 * " ob.jenis_Agama, ob.jenis_Warga, ob.catatan, ob.tarikh_Mati, ob.no_Hp, ob.waktu_Kematian, "
			 * +
			 * " ob.JENIS_WAKTU_KEMATIAN, ob.alamat1_surat, ob.alamat2_surat, ob.alamat3_surat, "
			 * +
			 * " ob.bandar_surat, ob.id_bandarsurat, ob.poskod_surat, ob.id_Negerisurat  "
			 * +
			 * " FROM Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati ms,TBLPPKHUBUNGANOB hbob "
			 * +
			 * " WHERE ob.id_Simati = m.id_Simati  AND p.id_Permohonan = ms.id_Permohonan  "
			 * + " AND ob.id_Permohonansimati = ms.id_Permohonansimati "+
			 * " AND hbob.id_ob(+) = ob.id_ob "+
			 * " AND ms.id_Simati = m.id_Simati  "+
			 * " AND ob.id_Ob = '"+id+"'  "+ " AND ob.id_Tarafkptg = 1 ";
			 */

			sql = " SELECT " +
					" ob.id_Pemohon, ob.emel, " +//razman tambah.. kalo tak xde, kuar error
					" HUP.id_parentob,ob.id_Simati, ob.id_Ob, ob.nama_Ob,ob1.nama_Lain, ob.no_Kp_Baru, ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, ob.no_Surat_Beranak, ob.tarikh_Lahir, "
					+ " ob.nama_warga,ob.jantina, ob.umur, ob.alamat_1, ob.alamat_2, ob.alamat_3, ob.bandar, ob.id_bandar, ob.poskod, ob.no_Tel, "
					+ " ob.id_Pemohon, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri, ob.id_Saudara, "
					+ " ob.jenis_Agama, ob.jenis_Warga, ob.catatan, ob.tarikh_Mati, ob.no_Hp, ob.waktu_Kematian, "
					+ " ob.JENIS_WAKTU_KEMATIAN, ob.alamat1_surat, ob.alamat2_surat, ob.alamat3_surat, "
					+ " ob.bandar_surat, ob.id_bandarsurat, ob.poskod_surat, ob.id_Negerisurat,ob.FLAG_DAFTAR,ob.nama_pelbagainegara,ob.nama_pelbagainegara_surat, ob.emel  "
					+ " FROM Tblppkob ob1,Tblppkobpermohonan ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati ms,"
					+ " TBLPPKHUBUNGANOB hbob,TBLPPKHUBUNGANOBPERMOHONAN HUP "
					+ " WHERE hbob.ID_OB = HUP.ID_OB(+) AND HUP.id_permohonansimati(+) = '"
					+ id_permohonansimati
					+ "' AND ob.id_Simati = m.id_Simati and ob.id_ob = ob1.id_ob  AND p.id_Permohonan = ms.id_Permohonan  "
					+ " AND ob.id_Permohonansimati = ms.id_Permohonansimati "
					+ " AND hbob.id_ob(+) = ob.id_ob "
					+ " AND ms.id_Simati = m.id_Simati  "
					+ " AND ob.id_Ob = '"
					+ id
					+ "'  "
					+ " AND ob.id_permohonansimati = '"
					+ id_permohonansimati + "'  " + " AND ob.id_Tarafkptg = 1 ";

			myLogger.info("OB LAPISAN SIWARIS STRUCTURE BARU" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("nama_pelbagainegara", rs.getString("nama_pelbagainegara") == null ? "" : rs.getString("nama_pelbagainegara"));
				h.put("nama_pelbagainegara_surat", rs.getString("nama_pelbagainegara_surat") == null ? "" : rs.getString("nama_pelbagainegara_surat"));

				h.put("id_ob_list_getListOBUpdate",
						rs.getString("id_parentob") == null ? "" : rs
								.getString("id_parentob"));
				
				//razman tambah.. kalo tak da, kuar error
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? "" : rs.getString("id_Pemohon"));
				
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("nama_Lain", rs.getString("nama_Lain") == null ? "" : rs.getString("nama_Lain"));//IL
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru"));
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("nokpbaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("nokpbaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("nokpbaru3","");
					}
				}
				//end IL

				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				*/				
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs.getString("no_Tel"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? "" : rs.getString("id_Pemohon"));
				//System.out.println("id_pemohon======================="+rs.getString("id_Pemohon"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs.getString("jantina"));
				h.put("umur", rs.getString("umur") == null ? "" : rs.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h.put("bandar", rs.getString("id_bandar") == null ? "" : rs.getString("id_bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? "" : rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs.getString("jenis_Warga"));

				h.put("alamat1Surat", rs.getString("alamat1_surat") == null ? "" : rs.getString("alamat1_surat"));
				h.put("alamat2Surat", rs.getString("alamat2_surat") == null ? "" : rs.getString("alamat2_surat"));
				h.put("alamat3Surat", rs.getString("alamat3_surat") == null ? "" : rs.getString("alamat3_surat"));
				h.put("bandarSurat", rs.getString("id_bandarsurat") == null ? "" : rs.getString("id_bandarsurat"));
				// h.put("bandarSurat",
				// rs.getString("bandar_surat")==null?"":rs.getString("bandar_surat"));

				h.put("poskodSurat", rs.getString("poskod_surat") == null ? "" : rs.getString("poskod_surat"));
				h.put("idnegeriSurat", rs.getString("id_Negerisurat") == null ? "" : rs.getString("id_Negerisurat"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? "" : rs.getString("waktu_Kematian"));
				h.put("jeniswaktu", rs.getString("JENIS_WAKTU_KEMATIAN") == null ? "" : rs.getString("JENIS_WAKTU_KEMATIAN"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-" : formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs.getString("no_Hp"));
				h.put("emel", rs.getString("emel") == null ? "" : rs.getString("emel"));//IL

				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? "" : rs.getString("FLAG_DAFTAR"));

				h.put("nama_warga", rs.getString("nama_warga") == null ? "" : rs.getString("nama_warga"));
				listWarisUpdate.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPenting(String idps) throws Exception {
		Db db = null;
		listPenting.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT OB.ID_SIMATI, OB.ID_OB, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.JENIS_KP, "
					+ " OB.NO_KP_LAIN, OB.NO_SURAT_BERANAK, OB.TARIKH_LAHIR, OB.JANTINA, OB.UMUR, OB.ALAMAT_1,"
					+ " OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR, OB.POSKOD, OB.NO_TEL,OB.NO_HP, OB.STATUS_HIDUP, OB.STATUS_OB,"
					+ " OB.ID_TARAFKPTG, OB.ID_NEGERI, OB.ID_SAUDARA, OB.JENIS_AGAMA, OB.JENIS_WARGA, OB.CATATAN,"
					+ " OB.BUTIRAN_HUTANG, OB.NILAI_HUTANG, OB.NO_AKAUN, OB.ID_PEMOHON,OB.ID_ARB "
					+ " FROM TBLPPKOB OB,TBLPPKOBPERMOHONAN OBP, TBLPPKSIMATI M, TBLPPKPERMOHONAN P,"
					+ " TBLPPKPERMOHONANSIMATI MS WHERE OBP.ID_OB = OB.ID_OB AND OB.ID_SIMATI = M.ID_SIMATI "
					+ " AND M.ID_SIMATI = MS.ID_SIMATI "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND MS.ID_PERMOHONANSIMATI = OB.ID_PERMOHONANSIMATI "
					+ " AND OBP.ID_PERMOHONANSIMATI = '"
					+ idps
					+ "' "
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idps
					+ "' "
					+ " ORDER BY OB.ID_OB DESC";

			// System.out.println("SQL PENTING 1 :"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob") == null ? "" : rs.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru"));
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				*/
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs.getString("no_Kp_Lain"));
				h.put("noberanak", rs.getString("no_Surat_Beranak") == null ? "" : rs.getString("no_Surat_Beranak"));
				h.put("dob", rs.getString("tarikh_Lahir") == null ? "" : rs.getString("tarikh_Lahir"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs.getString("jantina"));
				h.put("umur", rs.getString("umur") == null ? "" : rs.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup") == null ? "" : rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs.getString("jenis_Warga"));

				h.put("butiranhutang", rs.getString("butiran_Hutang") == null ? "" : rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang") == null ? "" : rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs.getString("no_Akaun"));

				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? "" : rs.getString("id_Pemohon"));
				h.put("no_hp", rs.getString("no_Hp") == null ? "" : rs.getString("no_Hp"));
				h.put("jenis_pej", rs.getString("id_arb") == null ? "" : rs.getString("id_arb"));

				listPenting.addElement(h);
				count++;
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPentingDulu(String idps) throws Exception {
		Db db = null;
		listPentingdulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql =
			 * " SELECT OB.ID_SIMATI, OB.ID_OB, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.JENIS_KP, "
			 * +
			 * " OB.NO_KP_LAIN, OB.NO_SURAT_BERANAK, OB.TARIKH_LAHIR, OB.JANTINA, OB.UMUR, OB.ALAMAT_1,"
			 * +
			 * " OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR, OB.POSKOD, OB.NO_TEL, OB.STATUS_HIDUP, OB.STATUS_OB,"
			 * +
			 * " OB.ID_TARAFKPTG, OB.ID_NEGERI, OB.ID_SAUDARA, OB.JENIS_AGAMA, OB.JENIS_WARGA, OB.CATATAN,"
			 * +
			 * " OB.BUTIRAN_HUTANG, OB.NILAI_HUTANG, OB.NO_AKAUN, OB.ID_PEMOHON "
			 * +" FROM TBLPPKOB OB, TBLPPKSIMATI M, TBLPPKPERMOHONAN P,"
			 * +" TBLPPKPERMOHONANSIMATI MS WHERE OB.ID_SIMATI = M.ID_SIMATI "
			 * +" AND M.ID_SIMATI = MS.ID_SIMATI "
			 * +" AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
			 * +" AND MS.ID_PERMOHONANSIMATI <> OB.ID_PERMOHONANSIMATI "
			 * +" AND MS.ID_PERMOHONANSIMATI = '"+idps+"' "
			 * +" ORDER BY OB.ID_OB DESC";
			 */
			sql = "SELECT OB.ID_SIMATI, OB.ID_OB, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, "
					+ "OB.JENIS_KP, OB.NO_KP_LAIN, OB.NO_SURAT_BERANAK, OB.TARIKH_LAHIR, OB.JANTINA, "
					+ "OB.UMUR, OB.ALAMAT_1, OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR, OB.POSKOD, "
					+ "OB.NO_TEL, OB.STATUS_HIDUP, OB.STATUS_OB, OB.ID_TARAFKPTG, OB.ID_NEGERI, "
					+ "OB.ID_SAUDARA, OB.JENIS_AGAMA, OB.JENIS_WARGA, OB.CATATAN, OB.BUTIRAN_HUTANG, "
					+ "OB.NILAI_HUTANG, OB.NO_AKAUN, OB.ID_PEMOHON  "
					+ "FROM TBLPPKOB OB,TBLPPKOBPERMOHONAN OBP, TBLPPKSIMATI M, TBLPPKPERMOHONAN P,TBLPPKPERMOHONAN P1,"
					+ "TBLPPKPERMOHONANSIMATI MS,TBLPPKPERMOHONANSIMATI MS1 "
					+ "WHERE OB.ID_OB = OBP.ID_OB AND OBP.ID_PERMOHONANSIMATI = '"
					+ idps
					+ "' AND OB.ID_SIMATI = M.ID_SIMATI  AND M.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN AND OB.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI  "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET   AND MS.ID_PERMOHONANSIMATI <> OB.ID_PERMOHONANSIMATI  "
					+ "AND MS.ID_PERMOHONANSIMATI = '"
					+ idps
					+ "'   ORDER BY OB.ID_OB DESC";

			// System.out.println("SQL PENTING 1 :"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob") == null ? "" : rs.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru"));
				/*
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				*/				
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs.getString("no_Kp_Lain"));
				h.put("noberanak", rs.getString("no_Surat_Beranak") == null ? "" : rs.getString("no_Surat_Beranak"));
				h.put("dob", rs.getString("tarikh_Lahir") == null ? "" : rs.getString("tarikh_Lahir"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs.getString("jantina"));
				h.put("umur", rs.getString("umur") == null ? "" : rs.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup") == null ? "" : rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs.getString("jenis_Warga"));

				h.put("butiranhutang", rs.getString("butiran_Hutang") == null ? "" : rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang") == null ? "" : rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs.getString("no_Akaun"));

				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? "" : rs.getString("id_Pemohon"));

				listPentingdulu.addElement(h);
				count++;
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPenghutang(String id) throws Exception {
		//System.out.println("setDataPenghutang");
		Db db = null;
		listPenghutang.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * r.add("ob.id_Penghutang"); r.add("ob.id_Simati");
			 * r.add("ob.id_Penghutang"); r.add("ob.nama_Penghutang");
			 * r.add("ob.no_Kp_Baru"); r.add("ob.no_Kp_Lama");
			 * r.add("ob.jenis_Kp"); r.add("ob.no_Kp_Lain");
			 * r.add("ob.alamat_1"); r.add("ob.alamat_2"); r.add("ob.alamat_3");
			 * r.add("ob.bandar"); r.add("ob.poskod"); r.add("ob.id_Negeri");
			 * r.add("ob.jenis_Agama"); r.add("ob.jenis_Warga");
			 * r.add("ob.jenis_Penghutang"); r.add("ob.butiran_Hutang");
			 * r.add("ob.jumlah_Hutang"); r.add("ob.no_Akaun");
			 * 
			 * r.add("ob.id_Simati",r.unquote("m.id_Simati"));
			 * r.add("ob.id_Simati",r.unquote("ms.id_Simati"));
			 * 
			 * 
			 * r.add("ms.id_Permohonansimati",id);
			 * 
			 * sql =r.getSQLSelect(
			 * "Tblppkpenghutang ob, Tblppksimati m, Tblppkpermohonansimati ms"
			 * ); sql = sql + " ORDER BY OB.ID_PENGHUTANG DESC";
			 */
			sql = "SELECT OB.ID_PENGHUTANG, OB.ID_SIMATI, OB.ID_PENGHUTANG, OB.NAMA_PENGHUTANG, OB.NO_KP_BARU,"
					+ " OB.NO_KP_LAMA, OB.JENIS_KP, OB.NO_KP_LAIN, OB.ALAMAT_1, OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR,"
					+ " OB.POSKOD, OB.ID_NEGERI, OB.JENIS_AGAMA, OB.JENIS_WARGA, OB.JENIS_PENGHUTANG,"
					+ " OB.BUTIRAN_HUTANG, OB.JUMLAH_HUTANG, OB.NO_AKAUN "
					+ " FROM TBLPPKPENGHUTANG OB, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P "
					+ " WHERE OB.ID_SIMATI = M.ID_SIMATI  "
					+ " AND OB.ID_SIMATI = MS.ID_SIMATI "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
					+ " AND MS.ID_PERMOHONANSIMATI = OB.ID_PERMOHONANSIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ id
					+ "' "
					+ " ORDER BY OB.ID_PENGHUTANG DESC	";

			//System.out.println("PENGHUTANG :"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 1;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Penghutang") == null ? "" : rs
						.getString("id_Penghutang"));
				h.put("nama_Ob", rs.getString("nama_Penghutang") == null ? ""
						: rs.getString("nama_Penghutang"));
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				*/
				//IL
				h.put("jenishutang", rs.getString("jenis_Penghutang") == null ? "" : rs.getString("jenis_Penghutang"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru"));
				
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs.getString("no_Kp_Lain"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs.getString("jenis_Agama"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs.getString("jenis_Warga"));

				h.put("butiranhutang", rs.getString("butiran_Hutang") == null ? "" : rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("jumlah_Hutang") == null ? "" : rs.getDouble("jumlah_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs.getString("no_Akaun"));
				h.put("count",count);
				listPenghutang.addElement(h);
				count++;
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		}finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPenghutangDulu(String id) throws Exception {
		Db db = null;
		listPenghutangdulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql =
			 * "SELECT OB.ID_PENGHUTANG, OB.ID_SIMATI, OB.ID_PENGHUTANG, OB.NAMA_PENGHUTANG, OB.NO_KP_BARU,"
			 * +
			 * " OB.NO_KP_LAMA, OB.JENIS_KP, OB.NO_KP_LAIN, OB.ALAMAT_1, OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR,"
			 * +
			 * " OB.POSKOD, OB.ID_NEGERI, OB.JENIS_AGAMA, OB.JENIS_WARGA, OB.JENIS_PENGHUTANG,"
			 * +" OB.BUTIRAN_HUTANG, OB.JUMLAH_HUTANG, OB.NO_AKAUN "+
			 * " FROM TBLPPKPENGHUTANG OB, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P "
			 * +" WHERE OB.ID_SIMATI = M.ID_SIMATI  "
			 * +" AND OB.ID_SIMATI = MS.ID_SIMATI "
			 * +" AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN "
			 * +" AND MS.ID_PERMOHONANSIMATI <> OB.ID_PERMOHONANSIMATI "
			 * +" AND MS.ID_PERMOHONANSIMATI = '"+id+"' "
			 * +" ORDER BY OB.ID_PENGHUTANG DESC	";
			 */
			sql = "SELECT OB.ID_PERMOHONANSIMATI, OB.ID_PENGHUTANG, OB.ID_SIMATI, "
					+ "OB.ID_PENGHUTANG, OB.NAMA_PENGHUTANG, OB.NO_KP_BARU, "
					+ "OB.NO_KP_LAMA, OB.JENIS_KP, OB.NO_KP_LAIN, OB.ALAMAT_1, "
					+ "OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR, OB.POSKOD, OB.ID_NEGERI, "
					+ "OB.JENIS_AGAMA, OB.JENIS_WARGA, OB.JENIS_PENGHUTANG, OB.BUTIRAN_HUTANG, "
					+ "OB.JUMLAH_HUTANG, OB.NO_AKAUN  "
					+ "FROM TBLPPKPENGHUTANG OB, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS, "
					+ "TBLPPKPERMOHONAN P ,TBLPPKPERMOHONAN P1, TBLPPKPERMOHONANSIMATI MS1  "
					+ "WHERE OB.ID_SIMATI = M.ID_SIMATI   "
					+ "AND OB.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN  "
					+ "AND MS.ID_PERMOHONANSIMATI <> OB.ID_PERMOHONANSIMATI  "
					+ "AND MS.ID_PERMOHONANSIMATI = '"
					+ id
					+ "' "
					+ "AND OB.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI  "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET    "
					+ "ORDER BY OB.ID_PENGHUTANG DESC	";

			// System.out.println("PENGHUTANG DULU :"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Penghutang") == null ? "" : rs.getString("id_Penghutang"));
				h.put("nama_Ob", rs.getString("nama_Penghutang") == null ? "" : rs.getString("nama_Penghutang"));

				h.put("jenishutang", rs.getString("jenis_Penghutang") == null ? "" : rs.getString("jenis_Penghutang"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru"));
				
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL	
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs.getString("no_Kp_Lain"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs.getString("jenis_Agama"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs.getString("jenis_Warga"));

				h.put("butiranhutang", rs.getString("butiran_Hutang") == null ? "" : rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("jumlah_Hutang") == null ? "" : rs.getDouble("jumlah_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs.getString("no_Akaun"));

				listPenghutangdulu.addElement(h);
				count++;
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPenghutangbyIDOB(String id) throws Exception {
		Db db = null;
		listPenghutangbyIDOB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Penghutang");
			r.add("ob.id_Simati");
			r.add("ob.id_Penghutang");
			r.add("ob.nama_Penghutang");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.id_Negeri");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.jenis_Penghutang");
			r.add("ob.butiran_Hutang");
			r.add("ob.jumlah_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.id_Bandar");

			r.add("ob.id_Simati", r.unquote("m.id_Simati"));
			r.add("ob.id_Penghutang", id);

			sql = r.getSQLSelect("Tblppkpenghutang ob, Tblppksimati m");

			// System.out.println("SQL PENTING :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Penghutang") == null ? "" : rs.getString("id_Penghutang"));
				h.put("nama_Ob", rs.getString("nama_Penghutang") == null ? "" : rs.getString("nama_Penghutang"));

				h.put("jenishutang", rs.getString("jenis_Penghutang") == null ? "" : rs.getString("jenis_Penghutang"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru"));
				
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				*/
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs.getString("no_Kp_Lain"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs.getString("jenis_Agama"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs.getString("jenis_Warga"));

				h.put("butiranhutang", rs.getString("butiran_Hutang") == null ? "" : rs.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("jumlah_Hutang") == null ? "" : rs.getDouble("jumlah_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs.getString("no_Akaun"));
				h.put("idbandar", rs.getString("id_Bandar") == null ? "" : rs.getString("id_Bandar"));

				listPenghutangbyIDOB.addElement(h);
				count++;
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataPentingbyIDOB(String id, String id_pemrohonansimati) throws Exception {
		Db db = null;
		listPentingbyIDOB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.id_bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.catatan");
			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");

			r.add("ob.id_Pemohon");
			r.add("ob.jenis_Pemiutang");

			r.add("ob.alamat1_Surat");
			r.add("ob.alamat2_Surat");
			r.add("ob.alamat3_Surat");
			r.add("ob.bandar_surat");
			r.add("ob.id_bandarsurat");
			r.add("ob.poskod_surat");
			r.add("ob.id_Negerisurat");

			// baruOB

			r.add("ob.no_fax");
			r.add("ob.no_hp");
			r.add("ob.id_arb");

			r.add("ob.id_Simati", r.unquote("m.id_Simati"));

			r.add("m.id_Simati", r.unquote("ms.id_Simati"));
			r.add("p.id_Permohonan", r.unquote("ms.id_Permohonan"));
			r.add("ob1.id_Permohonansimati", r.unquote("ms.id_Permohonansimati"));

			r.add("ob.id_ob", r.unquote("ob1.id_ob"));
			r.add("ob.id_permohonansimati", id_pemrohonansimati);

			r.add("ob.FLAG_DAFTAR");

			r.add("ob.id_Ob", id);

			sql = r.getSQLSelect("Tblppkob ob1,Tblppkobpermohonan ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati ms");

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				*/
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("idbandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));

				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("butiranhutang",
						rs.getString("butiran_Hutang") == null ? "" : rs
								.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang") == null ? ""
						: rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));
				h.put("jenishutang",
						rs.getString("jenis_Pemiutang") == null ? "" : rs
								.getString("jenis_Pemiutang"));

				h.put("idnegeriSurat",
						rs.getString("id_Negerisurat") == null ? "" : rs
								.getString("id_Negerisurat"));
				h.put("alamat1Surat",
						rs.getString("alamat1_Surat") == null ? "" : rs
								.getString("alamat1_Surat"));
				h.put("alamat2Surat",
						rs.getString("alamat2_Surat") == null ? "" : rs
								.getString("alamat2_Surat"));
				h.put("alamat3Surat",
						rs.getString("alamat3_Surat") == null ? "" : rs
								.getString("alamat3_Surat"));
				h.put("bandarSurat", rs.getString("bandar_surat") == null ? ""
						: rs.getString("bandar_surat"));
				h.put("poskodSurat", rs.getString("poskod_surat") == null ? ""
						: rs.getString("poskod_surat"));
				h.put("idbandarSurat",
						rs.getString("id_bandarsurat") == null ? "" : rs
								.getString("id_bandarsurat"));

				h.put("no_fax", rs.getString("no_fax") == null ? "" : rs
						.getString("no_fax"));
				h.put("no_hp", rs.getString("no_hp") == null ? "" : rs
						.getString("no_hp"));
				h.put("jenis_pej", rs.getString("id_arb") == null ? "" : rs
						.getString("id_arb"));

				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? ""
						: rs.getString("FLAG_DAFTAR"));

				// jenis_Pemiutang

				listPentingbyIDOB.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisParent(String id) throws Exception {
		Db db = null;
		listWarisParent.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.id_Pemohon");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");
			r.add("ob.lapis");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");

			r.add("ob.id_Simati", r.unquote("m.id_Simati"));
			r.add("m.id_Simati", r.unquote("ms.id_Simati"));

			r.add("ob.id_Permohonansimati", r.unquote("ms.id_Permohonansimati"));
			r.add("p.id_Permohonan", r.unquote("ms.id_Permohonan"));
			r.add("ob.id_Ob", id);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);

			// r.add("ob.id_simati",simati);

			// sql =
			// r.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p,Tblppkpermohonansimati ms");

			sql = "SELECT ob.id_Simati, ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, ob.no_Surat_Beranak, "
					+ " ob.tarikh_Lahir, ob.jantina, ob.umur, ob.alamat_1, ob.alamat_2, ob.alamat_3, ob.bandar, ob.poskod, ob.no_Tel, "
					+ " ob.id_Pemohon, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri, ob.id_Saudara, ob.jenis_Agama, ob.jenis_Warga, ob.catatan, "
					+ " ob.lapis, ob.tarikh_Mati, ob.no_Hp, ob.waktu_Kematian  "
					+ " FROM Tblppkobpermohonan ob, Tblppkob ob1, Tblppksimati m, Tblppkpermohonan p,Tblppkpermohonansimati ms "
					+ " WHERE ob1.id_Simati = m.id_Simati and ob.id_ob = ob1.id_ob  "
					+ " AND m.id_Simati = ms.id_Simati   "
					+ " AND ob1.id_Permohonansimati = ms.id_Permohonansimati "
					+ " AND ob.id_Permohonansimati = ms.id_Permohonansimati    "
					+ " AND p.id_Permohonan = ms.id_Permohonan "
					+ " AND ob.id_Ob = '"
					+ id
					+ "' "
					+ " AND ob.id_Tarafkptg = 1 ";
			// ###OBPERMOHONAN
			myLogger.info("GET PARENT DETAILS STRUCTURE BARU : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				*/
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));
				listWarisParent.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWaris(String id) throws Exception {
		Db db = null;
		listWaris.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.id_Pemohon");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");
			r.add("ob.lapis");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("ob.id_Simati", r.unquote("m.id_Simati"));
			r.add("p.id_Permohonan", r.unquote("ms.id_Permohonan"));
			r.add("m.id_Simati", r.unquote("ms.id_Simati"));
			r.add("ob.id_Permohonansimati", id);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);
			r.add("ob.lapis", k);
			/*
			 * sql =r.getSQLSelect(
			 * "Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati ms"
			 * ); sql = sql+"ORDER BY ob.lapis";
			 * 
			 * sql =
			 * "SELECT ob.id_Simati, ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, ob.no_Surat_Beranak, ob.tarikh_Lahir, ob.jantina, ob.umur, ob.alamat_1, "
			 * +
			 * " ob.alamat_2, ob.alamat_3, ob.bandar, ob.poskod, ob.no_Tel, ob.id_Pemohon, ob.status_Hidup, ob.status_Ob, "
			 * +
			 * " ob.id_Tarafkptg, ob.id_Negeri, ob.id_Saudara, ob.jenis_Agama, ob.jenis_Warga, ob.catatan, "
			 * + " ob.lapis, ob.tarikh_Mati, ob.no_Hp, ob.waktu_Kematian  " +
			 * " FROM Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati ms "
			 * + " WHERE ob.id_Simati = m.id_Simati  " + " AND p.id_status = 8 "
			 * + " AND p.id_Permohonan = ms.id_Permohonan  " +
			 * " AND m.id_Simati = ms.id_Simati  " +
			 * " AND ob.id_Permohonansimati = '"+id+"'  " +
			 * " AND ob.id_Tarafkptg = 1  " +
			 * " AND ob.lapis = 1 ORDER BY ob.lapis" + "";
			 */
			/*
			 * 
			 * sql=" SELECT ob.id_Simati, ms.id_permohonansimati ,ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, ob.no_Kp_Lama,"
			 * +
			 * " ob.jenis_Kp, ob.no_Kp_Lain, ob.no_Surat_Beranak, ob.tarikh_Lahir,"
			 * +
			 * " ob.jantina, ob.umur, ob.alamat_1,  ob.alamat_2, ob.alamat_3, ob.bandar,"
			 * +
			 * " ob.poskod, ob.no_Tel, ob.id_Pemohon, ob.status_Hidup, ob.status_Ob,"
			 * +
			 * " ob.id_Tarafkptg, ob.id_Negeri, ob.id_Saudara, ob.jenis_Agama, ob.jenis_Warga,"
			 * +
			 * " ob.catatan,  ob.lapis, ob.tarikh_Mati, ob.no_Hp, ob.waktu_Kematian"
			 * +
			 * " FROM Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati ms "
			 * +" WHERE ob.id_Simati = m.id_Simati" //
			 * +" AND p.id_status = ANY(8,9,170)"
			 * //+" AND ob.id_permohonansimati = '"+id+"'"
			 * +" AND ob.id_permohonansimati = ms.id_permohonansimati"
			 * +" AND ms.id_permohonansimati = '"+id+"'"
			 * +" AND p.id_Permohonan = ms.id_Permohonan"
			 * +" AND m.id_Simati = ms.id_Simati" +" AND ob.id_Tarafkptg = 1"
			 * +" AND ob.lapis = 1 ORDER BY ob.umur Desc" +"";
			 */

			// select call list + structure baru OB
			sql = " SELECT ob.id_Simati, ms.id_permohonansimati ,ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, ob.no_Kp_Lama,"
					+ " ob.jenis_Kp, ob.no_Kp_Lain, ob.no_Surat_Beranak, ob.tarikh_Lahir,"
					+ " ob.jantina, ob.umur, ob.alamat_1,  ob.alamat_2, ob.alamat_3, ob.bandar,"
					+ " ob.poskod, ob.no_Tel, ob.id_Pemohon, ob.status_Hidup, ob.status_Ob,"
					+ " ob.id_Tarafkptg, ob.id_Negeri, ob.id_Saudara, ob.jenis_Agama, ob.jenis_Warga,"
					+ " ob.catatan,  ob.lapis, ob.tarikh_Mati, ob.no_Hp, ob.waktu_Kematian"
					+ " FROM Tblppkob oba,Tblppkobpermohonan ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati ms "
					+ " WHERE ob.id_Simati = m.id_Simati and  ob.id_ob = oba.id_ob "
					+ " AND oba.id_permohonansimati = ms.id_permohonansimati "
					+ " AND ms.id_permohonansimati = '"
					+ id
					+ "'"
					+ " AND ob.id_permohonansimati = '"
					+ id
					+ "'"
					+ " AND p.id_Permohonan = ms.id_Permohonan"
					+ " AND m.id_Simati = ms.id_Simati"
					+ " AND ob.id_Tarafkptg = 1"
					+ " AND ob.lapis = 1 ORDER BY ob.umur Desc" + "";
			// ###OBPERMOHONAN
			myLogger.info("GET LIST OB STRUCTURE BARU :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));
				listWaris.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisDulu(String id) throws Exception {
		Db db = null;
		listWarisDulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			/*
			 * sql =
			 * " SELECT p.id_Permohonan,ob.id_Simati, ms.id_permohonansimati ,ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, "
			 * +
			 * " ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, ob.no_Surat_Beranak, ob.tarikh_Lahir,"
			 * +
			 * " ob.jantina, ob.umur, ob.alamat_1,  ob.alamat_2, ob.alamat_3, ob.bandar, ob.poskod, "
			 * +
			 * " ob.no_Tel, ob.id_Pemohon, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri, "
			 * +
			 * " ob.id_Saudara, ob.jenis_Agama, ob.jenis_Warga, ob.catatan,  ob.lapis, ob.tarikh_Mati, "
			 * +" ob.no_Hp, ob.waktu_Kematian "+
			 * " FROM Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati ms "
			 * +" WHERE ob.id_Simati = m.id_Simati "
			 * +" AND ms.id_permohonansimati = '"+id+"'"
			 * +" AND ob.id_permohonansimati <> ms.id_permohonansimati "
			 * +" AND p.id_Permohonan = ms.id_Permohonan "
			 * +" AND m.id_Simati = ms.id_Simati " +" AND ob.id_Tarafkptg = 1 "
			 * +" AND ob.lapis = 1 ORDER BY ob.lapis ";
			 */

			
			  sql =
			  "SELECT p1.no_subjaket,p.no_subjaket,p.id_Permohonan,ob.id_Simati, ms.id_permohonansimati ,ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, "
			  +
			  " ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, ob.no_Surat_Beranak, ob.tarikh_Lahir, "
			  +
			  " ob.jantina, ob.umur, ob.alamat_1,  ob.alamat_2, ob.alamat_3, ob.bandar, ob.poskod, "
			  +
			  " ob.no_Tel, ob.id_Pemohon, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri,  "
			  +
			  " ob.id_Saudara, ob.jenis_Agama, ob.jenis_Warga, ob.catatan,  ob.lapis, ob.tarikh_Mati,  ob.no_Hp, ob.waktu_Kematian  "
			  +
			  " FROM Tblppkob ob, Tblppksimati m, Tblppkpermohonan p,Tblppkpermohonan p1, Tblppkpermohonansimati ms,Tblppkpermohonansimati ms1 "
			  +" WHERE ob.id_Simati = m.id_Simati  "
			  +" AND ms.id_permohonansimati = '"+id+"' "
			  +" AND ob.id_permohonansimati <> ms.id_permohonansimati "
			  +" AND ob.id_permohonansimati = ms1.id_permohonansimati "
			  +" AND ms1.id_permohonan = p1.id_permohonan(+) "
			  +" AND p.id_Permohonan = ms.id_Permohonan(+) "
			  +" AND p1.no_subjaket < p.no_subjaket "
			  +" AND m.id_Simati = ms.id_Simati  " +" AND ob.id_Tarafkptg = 1 "
			  +" AND ob.lapis = 1 " +" ORDER BY ob.umur Desc ";
			 
			  /*
			sql = "SELECT p1.no_subjaket,p.no_subjaket,p.id_Permohonan,ob.id_Simati, ms.id_permohonansimati ,ob.id_Ob, ob.nama_Ob, ob.no_Kp_Baru, "
					+ " ob.no_Kp_Lama, ob.jenis_Kp, ob.no_Kp_Lain, ob.no_Surat_Beranak, ob.tarikh_Lahir,  "
					+ " ob.jantina, ob.umur, ob.alamat_1,  ob.alamat_2, ob.alamat_3, ob.bandar, ob.poskod,  "
					+ " ob.no_Tel, ob.id_Pemohon, ob.status_Hidup, ob.status_Ob, ob.id_Tarafkptg, ob.id_Negeri,  "
					+ " ob.id_Saudara, ob.jenis_Agama, ob.jenis_Warga, ob.catatan,  ob.lapis, ob.tarikh_Mati,  ob.no_Hp, ob.waktu_Kematian  "
					+ " FROM Tblppkob ob1, Tblppkobpermohonan ob,Tblppksimati m, Tblppkpermohonan p,Tblppkpermohonan p1, Tblppkpermohonansimati ms,Tblppkpermohonansimati ms1 "
					+ " WHERE ob.id_Simati = m.id_Simati and ob1.id_ob = ob.id_ob "
					+ " AND ms.id_permohonansimati = '"
					+ id
					+ "' and ob.id_permohonansimati = '"
					+ id
					+ "' "
					+ " AND ob1.id_permohonansimati <> ms.id_permohonansimati "
					+ " AND ob1.id_permohonansimati = ms1.id_permohonansimati "
					+ " AND ms1.id_permohonan = p1.id_permohonan(+) "
					+ " AND p.id_Permohonan = ms.id_Permohonan(+) "
					+ " AND p1.no_subjaket < p.no_subjaket "
					+ " AND m.id_Simati = ms.id_Simati "
					+ " AND ob.id_Tarafkptg = 1 "
					+ " AND ob.lapis = 1 "
					+ " ORDER BY ob.umur Desc ";
				*/	
			// ###OBPERMOHONAN

			myLogger
					.info("GET LIST WARIS LAPISAN PERTAMA DULU STRUCTURE BARU :"
							+ sql);

			// System.out.println("SQL WARIS 17 dulu"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs.getString("no_Hp"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs.getString("lapis"));
				listWarisDulu.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisLapisan(String idwaris) throws Exception {
		Db db = null;
		listWarisLapisan.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.id_Pemohon");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("hu.id_Parentob");
			r.add("ob.lapis");
			r.add("hu.id_hubunganob");
			r.add("hu.id_Ob", r.unquote("ob.id_Ob"));
			r.add("hu.id_Parentob", idwaris);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);

			// sql = r.getSQLSelect("Tblppkob ob, Tblppkhubunganob hu");
			// sql = sql + "order by ob.id_Ob desc";

			sql = "SELECT OB_NAME.NAMA_OB AS NAMA_PARENT,OB.ID_SIMATI, OB.ID_OB, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.JENIS_KP, "
					+ " OB.NO_KP_LAIN, OB.NO_SURAT_BERANAK, OB.TARIKH_LAHIR, OB.JANTINA, OB.UMUR, OB.ALAMAT_1, "
					+ " OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR, OB.POSKOD, OB.NO_TEL, OB.ID_PEMOHON, OB.STATUS_HIDUP, "
					+ " OB.STATUS_OB, OB.ID_TARAFKPTG, OB.ID_NEGERI, OB.ID_SAUDARA, OB.JENIS_AGAMA, "
					+ " OB.JENIS_WARGA, OB.CATATAN, OB.TARIKH_MATI, OB.NO_HP, OB.WAKTU_KEMATIAN, "
					+ " HUP.ID_PARENTOB, OB.LAPIS, HUP.ID_HUBUNGANOB  "
					+ " FROM TBLPPKOB OB_NAME,TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB, TBLPPKHUBUNGANOB HU,TBLPPKHUBUNGANOBPERMOHONAN HUP "
					+ " WHERE OB_NAME.ID_OB = HUP.ID_PARENTOB AND HU.ID_OB = OB1.ID_OB  AND OB.ID_OB = OB1.ID_OB AND HU.ID_HUBUNGANOB = HUP.ID_HUBUNGANOB "
					+ " AND OB1.ID_PERMOHONANSIMATI = HUP.ID_PERMOHONANSIMATI AND OB.ID_PERMOHONANSIMATI = OB1.ID_PERMOHONANSIMATI   "
					+ " AND HU.ID_PARENTOB = '"
					+ idwaris
					+ "'  "
					+ " AND OB.ID_TARAFKPTG = 1 ORDER BY OB1.ID_OB DESC";

			myLogger.info("GET WARIS LAPISAN BERIKUT STRUCTURE BARU :"
					+ sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("nama_parent", rs.getString("NAMA_PARENT") == null ? ""
						: rs.getString("NAMA_PARENT"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));

				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				/*
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				*/
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));
				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));
				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));
				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));
				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("idparent", rs.getString("id_Parentob") == null ? "" : rs
						.getString("id_Parentob"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));
				listWarisLapisan.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataSaksi(String simati) throws Exception {
		Db db = null;
		listSaksi.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");

			r.add("ob.id_Simati", r.unquote("m.id_Simati"));
			r.add("ob.id_Simati", r.unquote("ms.id_Simati"));

			r.add("ms.id_Permohonansimati", simati);

			int k = 14;
			r.add("ob.id_Tarafkptg", k);

			sql = r.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonansimati ms");

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idOb", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));
				h.put("dob", rs.getString("tarikh_Lahir") == null ? "" : rs
						.getString("tarikh_Lahir"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));
				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));
				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));
				listSaksi.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updatePemiutang(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String idpm = (String) data.get("idpm");
			String idsimati = (String) data.get("idSimati");
			String namapg = (String) data.get("namapg");

			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");

			double jumlah = (Double) data.get("jumlah");
			String noakaun = (String) data.get("noakaun");

			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			int negeri = (Integer) data.get("negeri");
			String catatan = (String) data.get("catatan");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Ob", idpm);

			r.add("id_Simati", idsimati);
			r.add("nama_Ob", namapg);

			r.add("no_Kp_Baru", nokpbaru);
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);

			if (jumlah != 0) {
				r.add("nilai_Hutang", jumlah);
			}
			r.add("no_Akaun", noakaun);

			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);

			r.add("bandar", bandar);
			r.add("poskod", poskod);

			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			r.add("butiran_Hutang", catatan);

			sql = r.getSQLUpdate("tblppkob");
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	/*
	 * public void setDataPenghutang(int simati) throws Exception { Db db =
	 * null; listPenghutang.clear(); String sql = "";
	 * 
	 * 
	 * try{ db = new Db(); Statement stmt = db.getStatement(); SQLRenderer r =
	 * new SQLRenderer(); r.add("ob.id_Simati"); r.add("ob.id_Penghutang");
	 * r.add("ob.nama_Penghutang"); r.add("ob.no_Kp_Baru");
	 * r.add("ob.no_Kp_Lama"); r.add("ob.jenis_Kp"); r.add("ob.no_Kp_Lain");
	 * r.add("ob.no_Akaun"); r.add("ob.jumlah_Hutang");
	 * r.add("ob.jenis_Penghutang"); r.add("ob.alamat_1"); r.add("ob.alamat_2");
	 * r.add("ob.alamat_3"); r.add("ob.bandar"); r.add("ob.poskod");
	 * r.add("ob.id_Negeri"); r.add("ob.butiran_Hutang");
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * r.add("ob.id_Simati",r.unquote("m.id_Simati"));
	 * 
	 * r.add("ob.id_Simati",simati);
	 * 
	 * sql = r.getSQLSelect("Tblppkpenghutang ob, Tblppksimati m");
	 * 
	 * ResultSet rs = stmt.executeQuery(sql); Hashtable h; Integer count = 0;
	 * 
	 * while(rs.next()) {
	 * 
	 * 
	 * h = new Hashtable();
	 * 
	 * 
	 * 
	 * 
	 * h.put("idSimati",
	 * rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
	 * h.put("idpg",
	 * rs.getString("id_Penghutang")==null?"":rs.getString("id_Penghutang"));
	 * h.put("nama_Pg",
	 * rs.getString("nama_Penghutang")==null?"":rs.getString("nama_Penghutang"
	 * )); h.put("jumlah",
	 * rs.getString("jumlah_Hutang")==null?"":rs.getString("jumlah_Hutang"));
	 * h.put("jenishutang",
	 * rs.getString("jenis_Penghutang")==null?"":rs.getString
	 * ("jenis_Penghutang")); h.put("noakaun",
	 * rs.getString("no_Akaun")==null?"":rs.getString("no_Akaun"));
	 * h.put("nokpbaru",
	 * rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
	 * h.put("nokpbaru1",
	 * rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"
	 * ).substring(0,6)); h.put("nokpbaru2",
	 * rs.getString("no_Kp_Baru")==null?"":
	 * rs.getString("no_Kp_Baru").substring(6,8)); h.put("nokpbaru3",
	 * rs.getString
	 * ("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
	 * h.put("nokplama",
	 * rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
	 * h.put("jeniskp",
	 * rs.getString("jenis_Kp")==null?"":rs.getString("jenis_Kp"));
	 * h.put("nokplain",
	 * rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
	 * h.put("idnegeri",
	 * rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
	 * h.put("alamat1",
	 * rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
	 * h.put("alamat2",
	 * rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
	 * h.put("alamat3",
	 * rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
	 * h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
	 * h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	 * h.put("catatan",
	 * rs.getString("butiran_Hutang")==null?"":rs.getString("butiran_Hutang"));
	 * listPenghutang.addElement(h);
	 * 
	 * 
	 * } } finally { if(db != null) db.close(); }
	 * 
	 * }
	 */
	public void setDataPemiutang(String simati) throws Exception {
		Db db = null;
		listPemiutang.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Akaun");
			r.add("ob.nilai_Hutang");

			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.id_Negeri");
			r.add("ob.butiran_Hutang");

			r.add("ob.id_Simati", r.unquote("m.id_Simati"));

			r.add("ob.id_Simati", simati);

			int h = 2;
			r.add("ob.id_Tarafkptg", h);

			sql = r.getSQLSelect("Tblppkob ob, Tblppksimati m");

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h1;
			Integer count = 0;

			while (rs.next()) {

				h1 = new Hashtable();

				h1.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h1.put("idpm", rs.getString("id_Ob") == null ? "" : rs.getString("id_Ob"));
				h1.put("nama_Pg", rs.getString("nama_Ob") == null ? "" : rs.getString("nama_Ob"));
				h1.put("jumlah", rs.getString("nilai_Hutang") == null ? "" : rs.getString("nilai_Hutang"));
				h1.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs.getString("no_Akaun"));
				h1.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru"));
				h1.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
				h1.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
				h1.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				h1.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs.getString("no_Kp_Lama"));
				h1.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs.getString("jenis_Kp"));
				h1.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs.getString("no_Kp_Lain"));
				h1.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h1.put("alamat1", rs.getString("alamat_1") == null ? "" : rs.getString("alamat_1"));
				h1.put("alamat2", rs.getString("alamat_2") == null ? "" : rs.getString("alamat_2"));
				h1.put("alamat3", rs.getString("alamat_3") == null ? "" : rs.getString("alamat_3"));
				h1.put("bandar", rs.getString("bandar") == null ? "" : rs.getString("bandar"));
				h1.put("poskod", rs.getString("poskod") == null ? "" : rs.getString("poskod"));
				h1.put("catatan", rs.getString("butiran_Hutang") == null ? "" : rs.getString("butiran_Hutang"));
				listPemiutang.addElement(h1);

			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteWarisHubungan(String uid, String id_permohonansimati) throws Exception {
		Db db = null;
		// int id= uid;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql =
			 * "select hob.id_ob,hob.id_parentob from TBLPPKHUBUNGANOB ob,TBLPPKHUBUNGANOBPERMOHONAN hob"
			 * + " where " + " ob.id_ob = hob.id_ob and  ob.id_ob = '"+uid+"' ";
			 * int total_row = 0; ResultSet rs = stmt.executeQuery(sql); while
			 * (rs.next()) { total_row++; }
			 * 
			 * r.clear(); r.add("id_Ob",uid);
			 * r.add("id_permohonansimati",id_permohonansimati); sql =
			 * r.getSQLDelete("TBLPPKHUBUNGANOBPERMOHONAN");
			 * stmt.executeUpdate(sql);
			 * 
			 * if(total_row==1) { r.clear(); r.add("id_Ob",uid); sql =
			 * r.getSQLDelete("TBLPPKHUBUNGANOB"); stmt.executeUpdate(sql); }
			 */
			r.clear();
			r.add("id_Ob", uid);
			sql = r.getSQLDelete("TBLPPKHUBUNGANOBPERMOHONAN");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_Ob", uid);
			sql = r.getSQLDelete("TBLPPKHUBUNGANOB");
			stmt.executeUpdate(sql);

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void deleteWaris(String uid, String id_permohonansimati) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql = "select hob.id_ob from TBLPPKOB ob,TBLPPKOBPERMOHONAN hob"
			 * + " where " + " ob.id_ob = hob.id_ob and  ob.id_ob = '"+uid+"' ";
			 * int total_row = 0; ResultSet rs = stmt.executeQuery(sql); while
			 * (rs.next()) { total_row++; }
			 * 
			 * r.clear(); r.add("id_Ob",uid);
			 * r.add("id_permohonansimati",id_permohonansimati); sql =
			 * r.getSQLDelete("TBLPPKOBPERMOHONAN"); stmt.executeUpdate(sql);
			 * 
			 * if(total_row==1) { r.clear(); r.add("id_Ob",uid); sql =
			 * r.getSQLDelete("TBLPPKOB"); stmt.executeUpdate(sql); }
			 */
			r.clear();
			r.add("id_Ob", uid);
			sql = r.getSQLDelete("TBLPPKOBPERMOHONAN");
			stmt.executeUpdate(sql);
			r.clear();
			r.add("id_Ob", uid);
			sql = r.getSQLDelete("TBLPPKOB");
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void deletePenting(String uid) throws Exception {
		Db db = null;
		// int id= uid;
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * r.add("id_Ob", uid); sql = r.getSQLDelete("tblppkob");
			 * stmt.executeUpdate(sql);
			 */
			r.clear();
			r.add("id_Ob", uid);
			sql = r.getSQLDelete("TBLPPKOBPERMOHONAN");
			stmt.executeUpdate(sql);
			r.clear();
			r.add("id_Ob", uid);
			sql = r.getSQLDelete("TBLPPKOB");
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void deletePenghutang(String uid) throws Exception {
		Db db = null;
		// int id= uid;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Penghutang", uid);
			sql = r.getSQLDelete("tblppkpenghutang");
			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}

	public void deletePeguam(String idpeguam) throws Exception {
		Db db = null;
		// int id= idpeguam;
		String sql = "";
		String sql4 = "";
		try {
			db = new Db();
			Statement stmt4 = db.getStatement();
			SQLRenderer r4 = new SQLRenderer();
			r4.add("id_Peguam", idpeguam);
			sql4 = r4.getSQLDelete("tblppkpeguampemohon");
			stmt4.executeUpdate(sql4);

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Peguam", idpeguam);
			sql = r.getSQLDelete("tblppkpeguam");
			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}

	public void addHtaamX(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {

			String idsimati = (String) data.get("idSimati");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String nopt = (String) data.get("nopt");
			String nilai_Hta_memohon = (String) data.get("nilai_Hta_memohon");
			String nilai_Hta_mati = (String) data.get("nilai_Hta_mati");
			int kategori = (Integer) data.get("kategori");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			String alamat_hta1 = (String) data.get("alamat_hta1");
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			String basimati = (String) data.get("basimati");
			String bbsimati = (String) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			int jenisluas = (Integer) data.get("jenisluas");
			String catatan = (String) data.get("catatan");
			String jeniskepentingan = (String) data.get("jeniskepentingan");
			String noperserahan = (String) data.get("noperserahan");
			String namapemaju = (String) data.get("namapemaju");
			String alamatpemaju1 = (String) data.get("alamatpemaju1");
			String alamatpemaju2 = (String) data.get("alamatpemaju2");
			String alamatpemaju3 = (String) data.get("alamatpemaju3");
			String poskodpemaju = (String) data.get("poskodpemaju");
			int bandarpemaju = (Integer) data.get("bandarpemaju");
			int negeripemaju = (Integer) data.get("negeripemaju");
			String alamathta1 = (String) data.get("alamathta1");
			String alamathta2 = (String) data.get("alamathta2");
			String alamathta3 = (String) data.get("alamathta3");
			String poskodhta = (String) data.get("poskodhta");
			int bandarhta = (Integer) data.get("bandarhta");
			String noperjanjian = (String) data.get("noperjanjian");
			String tarikhperja = (String) data.get("tarikhperjanjian");
			String tarikhperjanjian = "to_date('" + tarikhperja + "','dd/MM/yyyy')";
			String namarancangan = (String) data.get("namarancangan");
			String noroh = (String) data.get("noroh");
			String nolot = (String) data.get("nolot");
			String nocagaran = (String) data.get("nocagaran");
			String flag = (String) data.get("flag");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			long id_hta = DB.getNextID(db, "TBLPPKHTA_SEQ");

			r.add("id_hta", id_hta);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("no_Pt", nopt);
			r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			r.add("id_Kategori", kategori);
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			r.add("id_Negeri", negeri);
			r.add("id_Daerah", daerah);
			r.add("id_Mukim", mukim);
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			r.add("ba_Simati", basimati);
			r.add("bb_Simati", bbsimati);
			r.add("tanggungan", tanggungan);
			r.add("id_luas", jenisluas);
			r.add("catatan", catatan);
			r.add("no_perserahan", noperserahan);
			r.add("jenis_Hta", "T");
			r.add("nama_Pemaju", namapemaju);
			r.add("alamat_Pemaju1", alamatpemaju1);
			r.add("alamat_Pemaju2", alamatpemaju2);
			r.add("alamat_Pemaju3", alamatpemaju3);
			r.add("poskod_Pemaju", poskodpemaju);
			r.add("id_bandarpemaju", bandarpemaju);
			r.add("id_Negeripemaju", negeripemaju);
			r.add("alamat_Hta1", alamathta1);
			r.add("alamat_Hta2", alamathta2);
			r.add("alamat_Hta3", alamathta3);
			r.add("poskod_Hta", poskodhta);
			r.add("id_bandarhta", bandarhta);
			r.add("no_Perjanjian", noperjanjian);
			r.add("tarikh_Perjanjian", r.unquote(tarikhperjanjian));
			r.add("nama_Rancangan", namarancangan);
			r.add("no_Roh", noroh);
			r.add("no_Lot_Id", nolot);
			r.add("no_Cagaran", nocagaran);
			r.add("jenis_Kepentingan", jeniskepentingan);
			r.add("flag_Kategori_Hta", flag);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			sql = r.getSQLInsert("tblppkhta");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_hta", id_hta);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("no_Pt", nopt);
			r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			r.add("id_Kategori", kategori);
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			r.add("id_Negeri", negeri);
			r.add("id_Daerah", daerah);
			r.add("id_Mukim", mukim);
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			r.add("ba_Simati", basimati);
			r.add("bb_Simati", bbsimati);
			r.add("tanggungan", tanggungan);
			r.add("id_luas", jenisluas);
			r.add("catatan", catatan);
			r.add("no_perserahan", noperserahan);
			r.add("jenis_Hta", "T");
			r.add("nama_Pemaju", namapemaju);
			r.add("alamat_Pemaju1", alamatpemaju1);
			r.add("alamat_Pemaju2", alamatpemaju2);
			r.add("alamat_Pemaju3", alamatpemaju3);
			r.add("poskod_Pemaju", poskodpemaju);
			r.add("id_bandarpemaju", bandarpemaju);
			r.add("id_Negeripemaju", negeripemaju);
			r.add("alamat_Hta1", alamathta1);
			r.add("alamat_Hta2", alamathta2);
			r.add("alamat_Hta3", alamathta3);
			r.add("poskod_Hta", poskodhta);
			r.add("id_bandarhta", bandarhta);
			r.add("no_Perjanjian", noperjanjian);
			r.add("tarikh_Perjanjian", r.unquote(tarikhperjanjian));
			r.add("nama_Rancangan", namarancangan);
			r.add("no_Roh", noroh);
			r.add("no_Lot_Id", nolot);
			r.add("no_Cagaran", nocagaran);
			r.add("jenis_Kepentingan", jeniskepentingan);
			r.add("flag_Kategori_Hta", flag);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			sql = r.getSQLInsert("tblppkhtapermohonan");
			stmt.executeUpdate(sql);

			conn.commit();
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String addHtaamUploadX(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		String ret = "";

		try {

			String idsimati = (String) data.get("idSimati");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String nopt = (String) data.get("nopt");
			String nilai_Hta_memohon = (String) data.get("nilai_Hta_memohon");
			String nilai_Hta_mati = (String) data.get("nilai_Hta_mati");
			int kategori = (Integer) data.get("kategori");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			String basimati = (String) data.get("basimati");
			String bbsimati = (String) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			int jenisluas = (Integer) data.get("jenisluas");
			String catatan = (String) data.get("catatan");
			String jeniskepentingan = (String) data.get("jeniskepentingan");
			String noperserahan = (String) data.get("noperserahan");
			String namapemaju = (String) data.get("namapemaju");
			String alamatpemaju1 = (String) data.get("alamatpemaju1");
			String alamatpemaju2 = (String) data.get("alamatpemaju2");
			String alamatpemaju3 = (String) data.get("alamatpemaju3");
			String poskodpemaju = (String) data.get("poskodpemaju");
			int bandarpemaju = (Integer) data.get("bandarpemaju");
			int negeripemaju = (Integer) data.get("negeripemaju");
			String alamathta1 = (String) data.get("alamathta1");
			String alamathta2 = (String) data.get("alamathta2");
			String alamathta3 = (String) data.get("alamathta3");
			String poskodhta = (String) data.get("poskodhta");
			int bandarhta = (Integer) data.get("bandarhta");
			String noperjanjian = (String) data.get("noperjanjian");
			String tarikhperja = (String) data.get("tarikhperjanjian");
			String tarikhperjanjian = "to_date('" + tarikhperja + "','dd/MM/yyyy')";
			String namarancangan = (String) data.get("namarancangan");
			String noroh = (String) data.get("noroh");
			String nolot = (String) data.get("nolot");
			String nocagaran = (String) data.get("nocagaran");
			String flag = (String) data.get("flag");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			Long id_hta = DB.getNextID(db, "TBLPPKHTA_SEQ");

			r.add("id_hta", id_hta);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("no_Pt", nopt);
			r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			r.add("id_Kategori", kategori);
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			r.add("id_Negeri", negeri);
			r.add("id_Daerah", daerah);
			r.add("id_Mukim", mukim);
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			r.add("ba_Simati", basimati);
			r.add("bb_Simati", bbsimati);
			r.add("tanggungan", tanggungan);
			r.add("id_luas", jenisluas);
			r.add("catatan", catatan);
			r.add("no_Perserahan", noperserahan);
			r.add("jenis_Hta", "T");
			r.add("nama_Pemaju", namapemaju);
			r.add("alamat_Pemaju1", alamatpemaju1);
			r.add("alamat_Pemaju2", alamatpemaju2);
			r.add("alamat_Pemaju3", alamatpemaju3);
			r.add("poskod_Pemaju", poskodpemaju);
			r.add("id_bandarpemaju", bandarpemaju);
			r.add("id_Negeripemaju", negeripemaju);
			r.add("alamat_Hta1", alamathta1);
			r.add("alamat_Hta2", alamathta2);
			r.add("alamat_Hta3", alamathta3);
			r.add("poskod_Hta", poskodhta);
			r.add("id_bandarhta", bandarhta);
			r.add("no_Perjanjian", noperjanjian);
			r.add("tarikh_Perjanjian", r.unquote(tarikhperjanjian));
			r.add("nama_Rancangan", namarancangan);
			r.add("no_Roh", noroh);
			r.add("no_Lot_Id", nolot);
			r.add("no_Cagaran", nocagaran);
			r.add("jenis_Kepentingan", jeniskepentingan);
			r.add("flag_Kategori_Hta", flag);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			sql = r.getSQLInsert("tblppkhta");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_hta", id_hta);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("no_Pt", nopt);
			r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			r.add("id_Kategori", kategori);
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			r.add("id_Negeri", negeri);
			r.add("id_Daerah", daerah);
			r.add("id_Mukim", mukim);
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			r.add("ba_Simati", basimati);
			r.add("bb_Simati", bbsimati);
			r.add("tanggungan", tanggungan);
			r.add("id_luas", jenisluas);
			r.add("catatan", catatan);
			r.add("no_Perserahan", noperserahan);
			r.add("jenis_Hta", "T");
			r.add("nama_Pemaju", namapemaju);
			r.add("alamat_Pemaju1", alamatpemaju1);
			r.add("alamat_Pemaju2", alamatpemaju2);
			r.add("alamat_Pemaju3", alamatpemaju3);
			r.add("poskod_Pemaju", poskodpemaju);
			r.add("id_bandarpemaju", bandarpemaju);
			r.add("id_Negeripemaju", negeripemaju);
			r.add("alamat_Hta1", alamathta1);
			r.add("alamat_Hta2", alamathta2);
			r.add("alamat_Hta3", alamathta3);
			r.add("poskod_Hta", poskodhta);
			r.add("id_bandarhta", bandarhta);
			r.add("no_Perjanjian", noperjanjian);
			r.add("tarikh_Perjanjian", r.unquote(tarikhperjanjian));
			r.add("nama_Rancangan", namarancangan);
			r.add("no_Roh", noroh);
			r.add("no_Lot_Id", nolot);
			r.add("no_Cagaran", nocagaran);
			r.add("jenis_Kepentingan", jeniskepentingan);
			r.add("flag_Kategori_Hta", flag);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			sql = r.getSQLInsert("tblppkhtapermohonan");
			stmt.executeUpdate(sql);

			conn.commit();

			ret = id_hta.toString();
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return ret;
	}

	public void updateHtaamX(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		Connection conn = null;
		try {
			String idhta = (String) data.get("idhta");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String idsimati = (String) data.get("idSimati");
			String nopt = (String) data.get("nopt");
			String nilai_Hta_memohon = (String) data.get("nilai_Hta_memohon");
			String nilai_Hta_mati = (String) data.get("nilai_Hta_mati");

			int kategori = (Integer) data.get("kategori");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			String basimati = (String) data.get("basimati");
			String bbsimati = (String) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			int jenisluas = (Integer) data.get("jenisluas");
			String catatan = (String) data.get("catatan");
			String noperserahan = (String) data.get("noperserahan");

			String namapemaju = (String) data.get("namapemaju");
			String alamatpemaju1 = (String) data.get("alamatpemaju1");
			String alamatpemaju2 = (String) data.get("alamatpemaju2");
			String alamatpemaju3 = (String) data.get("alamatpemaju3");
			String poskodpemaju = (String) data.get("poskodpemaju");
			int bandarpemaju = (Integer) data.get("bandarpemaju");
			int negeripemaju = (Integer) data.get("negeripemaju");
			String alamathta1 = (String) data.get("alamathta1");
			String alamathta2 = (String) data.get("alamathta2");
			String alamathta3 = (String) data.get("alamathta3");
			String poskodhta = (String) data.get("poskodhta");
			int bandarhta = (Integer) data.get("bandarhta");
			// String bandarhta = (String) data.get("bandarhta");
			String noperjanjian = (String) data.get("noperjanjian");
			String tarikhperja = (String) data.get("tarikhperjanjian");
			String tarikhperjanjian = "to_date('" + tarikhperja + "','dd/MM/yyyy')";
			String namarancangan = (String) data.get("namarancangan");
			String noroh = (String) data.get("noroh");
			String nolot = (String) data.get("nolot");
			String nocagaran = (String) data.get("nocagaran");

			String jeniskepentingan = (String) data.get("jeniskepentingan");

			String flag = (String) data.get("flag");
			String jenis_Hta = (String) data.get("jenis_Hta");

			String id_Kemaskini = (String) data.get("id_Kemaskini");
			String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");

			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Hta", idhta);
			r.add("id_Simati", idsimati);
			if (nilai_Hta_memohon != "") {
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_hta_P));
			} else {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_hta_M));
			} else {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			r.add("id_Kategori", kategori);
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			r.add("id_Negeri", negeri);
			r.add("id_Daerah", daerah);
			r.add("id_Mukim", mukim);
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			r.add("ba_Simati", basimati);
			r.add("bb_Simati", bbsimati);
			r.add("tanggungan", tanggungan);
			r.add("id_luas", jenisluas);
			r.add("catatan", catatan);
			r.add("no_perserahan", noperserahan);
			// String jenishta="T";
			r.add("jenis_Hta", jenis_Hta);
			r.add("nama_Pemaju", namapemaju);
			r.add("alamat_Pemaju1", alamatpemaju1);
			r.add("alamat_Pemaju2", alamatpemaju2);
			r.add("alamat_Pemaju3", alamatpemaju3);
			r.add("poskod_Pemaju", poskodpemaju);
			r.add("id_bandarpemaju", bandarpemaju);
			r.add("id_Negeripemaju", negeripemaju);
			r.add("alamat_Hta1", alamathta1);
			r.add("alamat_Hta2", alamathta2);
			r.add("alamat_Hta3", alamathta3);
			r.add("poskod_Hta", poskodhta);
			r.add("id_bandarhta", bandarhta);
			r.add("no_Perjanjian", noperjanjian);
			r.add("tarikh_Perjanjian", r.unquote(tarikhperjanjian));
			r.add("nama_Rancangan", namarancangan);
			r.add("no_Roh", noroh);
			r.add("no_Lot_Id", nolot);
			r.add("no_Cagaran", nocagaran);
			r.add("jenis_Kepentingan", jeniskepentingan);
			r.add("id_Kemaskini", id_Kemaskini);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("flag_Kategori_Hta", flag);
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			sql = r.getSQLUpdate("tblppkhta");
			myLogger.info("FLAG simpan HTATH:" + sql);
			stmt.executeUpdate(sql);

			r.clear();
			r.update("id_Hta", idhta);
			r.update("id_Permohonansimati", id_Permohonansimati);
			r.add("id_Simati", idsimati);
			if (nilai_Hta_memohon != "") {
				
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_hta_P));
			} else {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_hta_M));
			} else {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			r.add("id_Kategori", kategori);
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			r.add("id_Negeri", negeri);
			r.add("id_Daerah", daerah);
			r.add("id_Mukim", mukim);
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			r.add("ba_Simati", basimati);
			r.add("bb_Simati", bbsimati);
			r.add("tanggungan", tanggungan);
			r.add("id_luas", jenisluas);
			r.add("catatan", catatan);
			r.add("no_perserahan", noperserahan);
			// String jenishta="T";
			r.add("jenis_Hta", jenis_Hta);
			r.add("nama_Pemaju", namapemaju);
			r.add("alamat_Pemaju1", alamatpemaju1);
			r.add("alamat_Pemaju2", alamatpemaju2);
			r.add("alamat_Pemaju3", alamatpemaju3);
			r.add("poskod_Pemaju", poskodpemaju);
			r.add("id_bandarpemaju", bandarpemaju);
			r.add("id_Negeripemaju", negeripemaju);
			r.add("alamat_Hta1", alamathta1);
			r.add("alamat_Hta2", alamathta2);
			r.add("alamat_Hta3", alamathta3);
			r.add("poskod_Hta", poskodhta);
			r.add("id_bandarhta", bandarhta);
			r.add("no_Perjanjian", noperjanjian);
			r.add("tarikh_Perjanjian", r.unquote(tarikhperjanjian));
			r.add("nama_Rancangan", namarancangan);
			r.add("no_Roh", noroh);
			r.add("no_Lot_Id", nolot);
			r.add("no_Cagaran", nocagaran);
			r.add("jenis_Kepentingan", jeniskepentingan);
			r.add("id_Kemaskini", id_Kemaskini);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("flag_Kategori_Hta", flag);
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			sql = r.getSQLUpdate("tblppkhtapermohonan");
			myLogger.info("FLAG simpan HTATH:" + sql);
			stmt.executeUpdate(sql);

			conn.commit();
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addHtaam(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {
			String noHakmilik = (String) data.get("noHakmilik");
			String idsimati = (String) data.get("idSimati");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String alamat_hta1 = (String) data.get("alamat_hta1");
			String alamat_hta2 = (String) data.get("alamat_hta2");
			String alamat_hta3 = (String) data.get("alamat_hta3");
			String poskod_hta = (String) data.get("poskod");
			String bandar_hta = (String) data.get("id_bandarhta");
			myLogger.info("alamat_hta1:sql= "+alamat_hta1);
			myLogger.info("alamat_hta2:sql= "+alamat_hta2);
			myLogger.info("alamat_hta3:sql= "+alamat_hta3);
			myLogger.info("poskod:sql= "+poskod_hta);
			myLogger.info("bandar_hta1:sql= "+bandar_hta);
			String nopt = (String) data.get("nopt");
			String nilai_Hta_memohon = (String) data.get("nilai_Hta_memohon");
			String nilai_Hta_mati = (String) data.get("nilai_Hta_mati");
			int kategori = (Integer) data.get("kategori");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			String basimati = (String) data.get("basimati");
			String bbsimati = (String) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			int jenisluas = (Integer) data.get("jenisluas");
			String catatan = (String) data.get("catatan");
			//String noperserahan = (String) data.get("noperserahan");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");

			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			String sekatan = (String) data.get("sekatan");
			String syaratNyata = (String) data.get("syaratNyata");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			long id_hta = DB.getNextID(db, "TBLPPKHTA_SEQ");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_hta", id_hta);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("no_Hakmilik", noHakmilik);
			r.add("alamat_hta1", alamat_hta1);
			r.add("alamat_hta2", alamat_hta2);
			r.add("alamat_hta3", alamat_hta3);
			r.add("poskod_hta", poskod_hta);
			r.add("id_bandarhta", bandar_hta);
			r.add("no_Pt", nopt);
			if (nilai_Hta_memohon != "") {
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_hta_P));
				//r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_Hta_memohon));
			} else {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_hta_M));
				//r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_Hta_mati));
			} else {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			if (basimati != "0") {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != "0") {
				r.add("bb_Simati", bbsimati);
			}
			r.add("tanggungan", tanggungan);
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}
			r.add("catatan", catatan);
			//r.add("", noperserahan);
			String jenishta = "Y";
			r.add("jenis_Hta", jenishta);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");

			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);

			sql = r.getSQLInsert("tblppkhta");
			myLogger.info("*******sql*********" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_hta", id_hta);
			r.add("id_Simati", idsimati);
			r.add("id_Permohonansimati", id_Permohonansimati);
			r.add("alamat_hta1", alamat_hta1);
			r.add("alamat_hta2", alamat_hta2);
			r.add("alamat_hta3", alamat_hta3);
			r.add("poskod_hta", poskod_hta);
			r.add("id_bandarhta", bandar_hta);
			r.add("no_Hakmilik", noHakmilik);
			r.add("no_Pt", nopt);
			if (nilai_Hta_memohon != "") {
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_hta_P));
				//r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_Hta_memohon));
			} else {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_hta_M));
				//r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_Hta_mati));
			} else {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			if (basimati != "0") {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != "0") {
				r.add("bb_Simati", bbsimati);
			}
			r.add("tanggungan", tanggungan);
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}
			r.add("catatan", catatan);
			//r.add("", noperserahan);

			r.add("jenis_Hta", "Y");
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);

			sql = r.getSQLInsert("tblppkhtapermohonan");
			myLogger.info("*******sql2*********" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			conn.commit();
		} finally {
			if (db != null)
				db.close();
		}

	}

	//IL start
	public String addHtaamUpload(Hashtable data) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		Long idHtaRet = null;

		try {
			int daerah = Integer.parseInt(String.valueOf(data.get("daerah")));
			int jenishakmilik = Integer.parseInt(String.valueOf(data.get("jenishakmilik")));
			int jenisluas = Integer.parseInt(String.valueOf(data.get("jenisluas")));
			int kategori = Integer.parseInt(String.valueOf(data.get("kategori")));
			int mukim = Integer.parseInt(String.valueOf(data.get("mukim")));
			int negeri = Integer.parseInt(String.valueOf(data.get("negeri")));
			
			String noHakmilik = (String) data.get("noHakmilik");
			String idsimati = (String) data.get("idSimati");
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String nopt = (String) data.get("nopt");
			String nilai_Hta_memohon = (String) data.get("nilai_Hta_memohon");
			String nilai_Hta_mati = (String) data.get("nilai_Hta_mati");
			String pemilikan = (String) data.get("pemilikan");
			String alamat_hta1 = (String) data.get("alamat_hta1");
			String alamat_hta2 = (String) data.get("alamat_hta2");
			String alamat_hta3 = (String) data.get("alamat_hta3");
			String poskod_hta = (String) data.get("poskod_hta");
			//System.out.println("txtBandarHartaHtaamX2----"+(String) data.get("txtBandarHartaHtaamX2"));
			String bandar_hta = (String) data.get("id_bandarhta");			
			//System.out.println("bandar_hta---"+bandar_hta);
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			String basimati = (String) data.get("basimati");
			String bbsimati = (String) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			String catatan = (String) data.get("catatan");
			String noperserahan = (String) data.get("noperserahan");
			String id_Masuk = (String) data.get("id_Masuk");
			String tarikh_Masuk = (String) data.get("tarikh_Masuk");
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			String sekatan = (String) data.get("sekatan");
			String syaratNyata = (String) data.get("syaratNyata");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			long id_hta = DB.getNextID(db, "TBLPPKHTA_SEQ");

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_hta", id_hta);
			r.add("id_Simati", idsimati);

			r.add("id_Permohonansimati", id_Permohonansimati);

			r.add("no_Hakmilik", noHakmilik);
			r.add("no_Pt", nopt);
			if (nilai_Hta_memohon != "") {
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_hta_P));
				//r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_Hta_memohon));
			} else {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_hta_M));
				//r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_Hta_mati));
			} else {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			if(alamat_hta1 != null && alamat_hta1.length()!=0){
				r.add("alamat_hta1", alamat_hta1);
			}
			if(alamat_hta2 != null && alamat_hta2.length()!=0){
				r.add("alamat_hta2", alamat_hta2);
			}
			if(alamat_hta3 != null && alamat_hta3.length()!=0){
				r.add("alamat_hta3", alamat_hta3);
			}
			if(poskod_hta != null && poskod_hta.length()!=0){
				r.add("poskod_hta", poskod_hta);
			}
			if(!bandar_hta.equals("")){
				r.add("bandar_hta", bandar_hta);
			}		
			
			r.add("id_bandarhta", bandar_hta);
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			if (basimati != "0") {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != "0") {
				r.add("bb_Simati", bbsimati);
			}
			r.add("tanggungan", tanggungan);
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}
			r.add("catatan", catatan);
			r.add("no_Perserahan", noperserahan);
			String jenishta = "Y";
			r.add("jenis_Hta", jenishta);
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);

			sql = r.getSQLInsert("tblppkhta");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_hta", id_hta);
			r.add("id_Simati", idsimati);
			if (id_Permohonansimati != "") {
				r.add("id_Permohonansimati", id_Permohonansimati);
			} else {
				r.add("id_Permohonansimati", "1110527978");
			}
			r.add("no_Hakmilik", noHakmilik);
			r.add("no_Pt", nopt);
			if (nilai_Hta_memohon != "") {
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_hta_P));
				//r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_Hta_memohon));
			} else {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_hta_M));
				//r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_Hta_mati));
			} else {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			if(alamat_hta1 != null && alamat_hta1.length()!=0){
				r.add("alamat_hta1", alamat_hta1);
			}
			if(alamat_hta2 != null && alamat_hta2.length()!=0){
				r.add("alamat_hta2", alamat_hta2);
			}
			if(alamat_hta3 != null && alamat_hta3.length()!=0){
				r.add("alamat_hta3", alamat_hta3);
			}
			if(poskod_hta != null && poskod_hta.length()!=0){
				r.add("poskod_hta", poskod_hta);
			}
			if(!bandar_hta.equals("")){
				r.add("bandar_hta", bandar_hta);
			}
			r.add("id_bandarhta", bandar_hta);
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			if (basimati != "0") {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != "0") {
				r.add("bb_Simati", bbsimati);
			}
			r.add("tanggungan", tanggungan);
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}
			r.add("catatan", catatan);
			r.add("no_Perserahan", noperserahan);

			r.add("jenis_Hta", "Y");
			r.add("id_Masuk", id_Masuk);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("flag_pa", "T");
			r.add("flag_pt", "T");
			r.add("flag_selesai", "T");
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);

			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);

			sql = r.getSQLInsert("tblppkhtapermohonan");
			myLogger.info("TEST : "+ sql);
			stmt.executeUpdate(sql);
			

			conn.commit();

			idHtaRet = id_hta;
			
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			
		}finally {
			if (db != null)
				db.close();
		}
		return idHtaRet.toString();
	}
	//end IL

	public void updateHtaam(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		Connection conn = null;
		try {
			String id_Permohonansimati = (String) data.get("id_Permohonansimati");
			String noHakmilik = (String) data.get("noHakmilik");
			String idsimati = (String) data.get("idSimati");
			String nopt = (String) data.get("nopt");
			String nilai_Hta_memohon = (String) data.get("nilai_Hta_memohon");
			String nilai_Hta_mati = (String) data.get("nilai_Hta_mati");
			int kategori = (Integer) data.get("kategori");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String pemilikan = (String) data.get("pemilikan");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			String alamat_hta1 = (String) data.get("alamat_hta1");
			String alamat_hta2 = (String) data.get("alamat_hta2");
			String alamat_hta3 = (String) data.get("alamat_hta3");
			String poskod_hta = (String) data.get("poskod");
			//String bandar_hta = (String) data.get("bandar_hta");
			String id_bandarhta = (String) data.get("id_bandarhta");
			myLogger.info("id_bandarhta:sql= "+id_bandarhta);
			String luashmp = (String) data.get("luashmp");
			String luasasal = (String) data.get("luasasal");
			String nopajakan = (String) data.get("nopajakan");
			String jenistanah = (String) data.get("jenistanah");
			String basimati = (String) data.get("basimati");
			String bbsimati = (String) data.get("bbsimati");
			String tanggungan = (String) data.get("tanggungan");
			int jenisluas = (Integer) data.get("jenisluas");
			String idhta = (String) data.get("idhta");
			String catatan = (String) data.get("catatan");
			String noperserahan = (String) data.get("noperserahan");
			String id_Kemaskini = (String) data.get("id_Kemaskini");
			String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");
			String jenis_Hta = (String) data.get("jenis_Hta");
		//	String alamat1 = (String) data.get("alamat1");
			
			String flag = (String) data.get("flag");
			String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");
			
			String alamathta1 = (String) data.get("alamathta1");
			String alamathta2 = (String) data.get("alamathta2");
			String alamathta3 = (String) data.get("alamathta3");

			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			String sekatan = (String) data.get("sekatan");
			String syaratNyata = (String) data.get("syaratNyata");

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("id_Hta", idhta);
			r.add("id_Simati", idsimati);
			r.add("no_Hakmilik", noHakmilik);
			r.add("no_Pt", nopt);
			if (nilai_Hta_memohon != "") {
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_hta_P));
				//r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_Hta_memohon));
			} else {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_hta_M));
				//r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_Hta_mati));
			} else {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("no_perserahan", noperserahan);
			
			r.add("jenis_Tnh", jenistanah);
			if (basimati != "0") {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != "0") {
				r.add("bb_Simati", bbsimati);
			}
			r.add("tanggungan", tanggungan);
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}
			r.add("catatan", catatan);
			//r.add("", noperserahan);
			
			r.add("alamat_hta1", alamat_hta1);
			r.add("alamat_hta2", alamat_hta2);
			r.add("alamat_hta3", alamat_hta3);
			r.add("poskod_hta", poskod_hta);
			r.add("id_bandarhta", id_bandarhta);
			
			r.add("jenis_Hta", jenis_Hta);
			r.add("flag_Kategori_Hta", flag);
			r.add("id_Kemaskini", id_Kemaskini);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));

			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);

			// ADD BY SALNIZAM - TAMBAH FIELD ALAMAT
			
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			sql = r.getSQLUpdate("tblppkhta");
			myLogger.info("7638:sql="+sql);
			stmt.executeUpdate(sql);

			r.clear();
			r.update("id_Hta", idhta);
			r.update("id_Permohonansimati", id_Permohonansimati);
			r.add("id_Simati", idsimati);
			r.add("no_Hakmilik", noHakmilik);
			r.add("no_Pt", nopt);
			if (nilai_Hta_memohon != "") {
				String nilai_hta_P = nilai_Hta_memohon.replace(",", "");
				r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_hta_P));
				//r.add("nilai_Hta_Tarikhmohon", Double.parseDouble(nilai_Hta_memohon));
			} else {
				r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			}
			if (nilai_Hta_mati != "") {
				String nilai_hta_M = nilai_Hta_mati.replace(",", "");
				r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_hta_M));
				//r.add("nilai_Hta_Tarikhmati", Double.parseDouble(nilai_Hta_mati));
			} else {
				r.add("nilai_Hta_Tarikhmati", nilai_Hta_mati);
			}
			if (kategori != 0) {
				r.add("id_Kategori", kategori);
			}
			r.add("id_Jenishm", jenishakmilik);
			r.add("status_Pemilikan", pemilikan);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			r.add("luas_Hmp", luashmp);
			r.add("luas", luasasal);
			r.add("no_Pajakan", nopajakan);
			r.add("jenis_Tnh", jenistanah);
			if (basimati != "0") {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != "0") {
				r.add("bb_Simati", bbsimati);
			}
			r.add("tanggungan", tanggungan);
			if (jenisluas != 0) {
				r.add("id_luas", jenisluas);
			}
			r.add("catatan", catatan);
			//r.add("", noperserahan);
			r.add("no_perserahan", noperserahan);
			r.add("jenis_Hta", jenis_Hta);
			r.add("flag_Kategori_Hta", flag);
			r.add("id_Kemaskini", id_Kemaskini);
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("FLAG_DAFTAR", FLAG_DAFTAR);
			r.add("alamat_hta1", alamat_hta1);
			r.add("alamat_hta2", alamat_hta2);
			r.add("alamat_hta3", alamat_hta3);
			r.add("poskod_hta", poskod_hta);
			r.add("id_bandarhta", id_bandarhta);
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);

			sql = r.getSQLUpdate("tblppkhtapermohonan");
			myLogger.info("7700:sql="+sql);
			stmt.executeUpdate(sql);

			conn.commit();

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

//IL start
public void deleteHtaamSenarai(String idDokumen, String uid, String id_permohonansimati) throws Exception {

		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql =
			 * "select hob.id_hta from TBLPPKHTA ob,TBLPPKHTAPERMOHONAN hob" +
			 * " where " +
			 * " ob.id_hta = hob.id_hta and  ob.id_hta = '"+uid+"' "; int
			 * total_row = 0; ResultSet rs = stmt.executeQuery(sql); while
			 * (rs.next()) { total_row++; }
			 * 
			 * r.clear(); r.add("id_hta",uid);
			 * r.add("id_permohonansimati",id_permohonansimati); sql =
			 * r.getSQLDelete("TBLPPKHTAPERMOHONAN"); stmt.executeUpdate(sql);
			 * 
			 * if(total_row==1) { r.clear(); r.add("id_hta",uid); sql =
			 * r.getSQLDelete("TBLPPKHTA"); stmt.executeUpdate(sql); }
			 */
			r.clear();
			r.add("id_hta", uid);
			sql = r.getSQLDelete("TBLPPKUPLOADPELAN");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_dokumen", uid);
			sql = r.getSQLDelete("TBLPPKDOKUMEN");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_hta", uid);
			sql = r.getSQLDelete("TBLPPKHTAPERMOHONAN");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_hta", uid);
			sql = r.getSQLDelete("TBLPPKHTA");
			stmt.executeUpdate(sql);

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}
//IL end

	public void deleteHtaam(HttpSession session, String uid, String id_permohonansimati) throws Exception {

		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql =
			 * "select hob.id_hta from TBLPPKHTA ob,TBLPPKHTAPERMOHONAN hob" +
			 * " where " +
			 * " ob.id_hta = hob.id_hta and  ob.id_hta = '"+uid+"' "; int
			 * total_row = 0; ResultSet rs = stmt.executeQuery(sql); while
			 * (rs.next()) { total_row++; }
			 * 
			 * r.clear(); r.add("id_hta",uid);
			 * r.add("id_permohonansimati",id_permohonansimati); sql =
			 * r.getSQLDelete("TBLPPKHTAPERMOHONAN"); stmt.executeUpdate(sql);
			 * 
			 * if(total_row==1) { r.clear(); r.add("id_hta",uid); sql =
			 * r.getSQLDelete("TBLPPKHTA"); stmt.executeUpdate(sql); }
			 */
			
			r.clear();
			r.add("id_hta", uid);
			sql = r.getSQLDelete("TBLPPKUPLOADPELAN");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_hta", uid);
			sql = r.getSQLDelete("TBLPPKHTAPERMOHONAN");
			stmt.executeUpdate(sql);

			r.clear();
			r.add("id_hta", uid);
			sql = r.getSQLDelete("TBLPPKHTA");
			stmt.executeUpdate(sql);
			
			AuditTrail.logActivity(null,session,"DEL","HARTA INI [" + uid + "] DIHAPUSKAN");

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}
	//IL start
	public void deleteHtaamInternal(String idDokumen, String uid, String id_permohonansimati) throws Exception {

		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql =
			 * "select hob.id_hta from TBLPPKHTA ob,TBLPPKHTAPERMOHONAN hob" +
			 * " where " +
			 * " ob.id_hta = hob.id_hta and  ob.id_hta = '"+uid+"' "; int
			 * total_row = 0; ResultSet rs = stmt.executeQuery(sql); while
			 * (rs.next()) { total_row++; }
			 * 
			 * r.clear(); r.add("id_hta",uid);
			 * r.add("id_permohonansimati",id_permohonansimati); sql =
			 * r.getSQLDelete("TBLPPKHTAPERMOHONAN"); stmt.executeUpdate(sql);
			 * 
			 * if(total_row==1) { r.clear(); r.add("id_hta",uid); sql =
			 * r.getSQLDelete("TBLPPKHTA"); stmt.executeUpdate(sql); }
			 */
			System.out.println("sini 1" +uid);
			r.clear();
			r.add("id_hta", uid);
			sql = r.getSQLDelete("TBLPPKUPLOADPELAN");
			stmt.executeUpdate(sql);
			
			System.out.println("sini 2");
			r.clear();
			r.add("id_dokumen", uid);
			sql = r.getSQLDelete("TBLPPKDOKUMEN");
			stmt.executeUpdate(sql);

			System.out.println("sini 3");
			r.clear();
			r.add("id_hta", uid);
			sql = r.getSQLDelete("TBLPPKHTAPERMOHONAN");
			stmt.executeUpdate(sql);
			System.out.println("TBLPPKHTAPERMOHONAN===" +sql);

			System.out.println("sini 4");
			r.clear();
			r.add("id_hta", uid);
			sql = r.getSQLDelete("TBLPPKHTA");
			stmt.executeUpdate(sql);
			System.out.println("TBLPPKHTA===" +sql);
			System.out.println("sini 5");

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	//IL end
	public void setDataWarisLapisanIdMati(String id_Permohonansimati)
			throws Exception {
		Db db = null;
		listWarisLapisanIdMati.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.id_Pemohon");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("hu.id_Parentob");
			r.add("ob.lapis");

			r.add("hu.id_hubunganob");

			r.add("hu.id_Ob", r.unquote("ob.id_Ob"));
			r.add("ob.id_Permohonansimati", r.unquote("ms.id_Permohonansimati"));
			r.add("ms.id_Permohonansimati", id_Permohonansimati);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);

			// sql =
			// r.getSQLSelect("Tblppkob ob, Tblppkpermohonansimati ms, Tblppkhubunganob hu","ob.lapis");

			sql = "SELECT OBNAME.NAMA_OB AS NAMA_PARENT,OB.ID_SIMATI, OB.ID_OB, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, "
					+ " OB.JENIS_KP, OB.NO_KP_LAIN, OB.NO_SURAT_BERANAK, OB.TARIKH_LAHIR, OB.JANTINA, "
					+ " OB.UMUR, OB.ALAMAT_1, OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR, OB.POSKOD, OB.NO_TEL, "
					+ " OB.ID_PEMOHON, OB.STATUS_HIDUP, OB.STATUS_OB, OB.ID_TARAFKPTG, OB.ID_NEGERI, "
					+ " OB.ID_SAUDARA, OB.JENIS_AGAMA, OB.JENIS_WARGA, OB.CATATAN, OB.TARIKH_MATI, "
					+ " OB.NO_HP, OB.WAKTU_KEMATIAN, HUP.ID_PARENTOB, OB.LAPIS, HUP.ID_HUBUNGANOB  "
					+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OBNAME,TBLPPKOB OB1, TBLPPKPERMOHONANSIMATI MS, TBLPPKHUBUNGANOB HU,TBLPPKHUBUNGANOBPERMOHONAN HUP "
					+ " WHERE OBNAME.ID_OB = HUP.ID_PARENTOB AND HU.ID_OB = OB1.ID_OB AND OB1.ID_OB = OB.ID_OB "
					+ " AND HU.ID_HUBUNGANOB = HUP.ID_HUBUNGANOB AND MS.ID_PERMOHONANSIMATI = HUP.ID_PERMOHONANSIMATI  "
					+ " AND OB1.ID_PERMOHONANSIMATI = MS.ID_PERMOHONANSIMATI  "
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ id_Permohonansimati
					+ "'  "
					+ " AND OB.ID_PERMOHONANSIMATI = '"
					+ id_Permohonansimati
					+ "'  "
					+ " AND OB.ID_TARAFKPTG = 1  ORDER BY OB.LAPIS";
			// ###OBPERMOHONAN

			myLogger.info("LAPISAN BERIKUT BARU :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("nama_parent", rs.getString("NAMA_PARENT") == null ? ""
						: rs.getString("NAMA_PARENT"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));

				h.put("idparent", rs.getString("id_Parentob") == null ? "" : rs
						.getString("id_Parentob"));

				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));

				// System.out.println(h);
				listWarisLapisanIdMati.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisLapisanIdMatiDulu(String id_Permohonansimati) throws Exception {
		Db db = null;
		listWarisLapisanIdMatiDulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT OB_NAME.NAMA_OB AS NAMA_PARENT,OB.ID_SIMATI, OB.ID_OB, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.JENIS_KP, "
					+ " OB.NO_KP_LAIN, OB.NO_SURAT_BERANAK, OB.TARIKH_LAHIR, OB.JANTINA, OB.UMUR, OB.ALAMAT_1,"
					+ " OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR, OB.POSKOD, OB.NO_TEL, OB.ID_PEMOHON, OB.STATUS_HIDUP, "
					+ " OB.STATUS_OB, OB.ID_TARAFKPTG, OB.ID_NEGERI, OB.ID_SAUDARA, OB.JENIS_AGAMA, OB.JENIS_WARGA, "
					+ " OB.CATATAN, OB.TARIKH_MATI, OB.NO_HP, OB.WAKTU_KEMATIAN, HUP.ID_PARENTOB, OB.LAPIS, HUP.ID_HUBUNGANOB "
					+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB_NAME,TBLPPKOB OB1, TBLPPKPERMOHONANSIMATI MS, TBLPPKHUBUNGANOB HU,TBLPPKHUBUNGANOBPERMOHONAN HUP,"
					+ "  TBLPPKSIMATI M "
					+ " WHERE OB_NAME.ID_OB = HUP.ID_PARENTOB AND HU.ID_OB = OB1.ID_OB AND OB.ID_OB = OB1.ID_OB AND HU.ID_HUBUNGANOB = HUP.ID_HUBUNGANOB "
					+ " AND OB1.ID_SIMATI = M.ID_SIMATI AND MS.ID_PERMOHONANSIMATI = HUP.ID_PERMOHONANSIMATI "
					+ " AND OB1.ID_SIMATI = MS.ID_SIMATI "
					+ " AND OB1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI "
					+ " AND OB.ID_PERMOHONANSIMATI = '"
					+ id_Permohonansimati
					+ "'"
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ id_Permohonansimati
					+ "' "
					+ " AND OB.ID_TARAFKPTG = 1  "
					+ " ORDER BY OB.LAPIS ";
			// ###OBPERMOHONAN
			myLogger
					.info("GET LIST WARIS BERIKUT DULU STRUCTURE BARU : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("nama_parent", rs.getString("NAMA_PARENT") == null ? ""
						: rs.getString("NAMA_PARENT"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));

				//IL start
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//IL end

				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-" : formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs.getString("no_Hp"));

				h.put("idparent", rs.getString("id_Parentob") == null ? "" : rs.getString("id_Parentob"));

				h.put("lapis", rs.getString("lapis") == null ? "" : rs.getString("lapis"));

				// System.out.println(h);
				listWarisLapisanIdMatiDulu.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisLapisanIdMatiDelete(String idwaris) throws Exception {
		Db db = null;
		listWarisLapisanIdMatiDelete.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");

			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("hu.id_Parentob");
			r.add("ob.lapis");

			r.add("hu.id_hubunganob");

			r.add("hu.id_Ob", r.unquote("ob.id_Ob"));

			r.add("hu.id_Parentob", idwaris);

			int k = 1;
			r.add("ob.id_Tarafkptg", k);

			sql = r.getSQLSelect("Tblppkob ob, Tblppkhubunganob hu", "ob.lapis");

			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				//IL
				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				//end IL
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-"
						: formatter.format(rs.getDate("tarikh_Mati")));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "-" : formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs.getString("no_Hp"));

				h.put("idparent", rs.getString("id_Parentob") == null ? "" : rs.getString("id_Parentob"));

				h.put("lapis", rs.getString("lapis") == null ? "" : rs.getString("lapis"));

				// System.out.println(h);
				listWarisLapisanIdMatiDelete.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataWarisOB(String id) throws Exception {
		Db db = null;
		listWarisOB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ob.id_Simati");
			r.add("ob.id_Ob");
			r.add("ob.nama_Ob");
			r.add("ob.no_Kp_Baru");
			r.add("ob.no_Kp_Lama");
			r.add("ob.jenis_Kp");
			r.add("ob.no_Kp_Lain");
			r.add("ob.no_Surat_Beranak");
			r.add("ob.tarikh_Lahir");
			r.add("ob.jantina");
			r.add("ob.umur");
			r.add("ob.alamat_1");
			r.add("ob.alamat_2");
			r.add("ob.alamat_3");
			r.add("ob.bandar");
			r.add("ob.poskod");
			r.add("ob.no_Tel");
			r.add("ob.id_Pemohon");

			r.add("ob.status_Hidup");
			r.add("ob.status_Ob");
			r.add("ob.id_Tarafkptg");
			r.add("ob.id_Negeri");
			r.add("ob.id_Saudara");
			// r.add("ob.id_Jenispb");
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			// r.add("ob.jenis_Bank");
			r.add("ob.catatan");
			r.add("ob.lapis");
			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("ob.id_Permohonansimati", id);

			// sql = r.getSQLSelect("Tblppkob ob");
			// sql = sql+"ORDER BY ob.lapis";
			sql = "SELECT OB.ID_SIMATI, OB.ID_OB, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.JENIS_KP, "
					+ " OB.NO_KP_LAIN, OB.NO_SURAT_BERANAK, OB.TARIKH_LAHIR, OB.JANTINA, OB.UMUR, "
					+ " OB.ALAMAT_1, OB.ALAMAT_2, OB.ALAMAT_3, OB.BANDAR, OB.POSKOD, OB.NO_TEL, "
					+ " OB.ID_PEMOHON, OB.STATUS_HIDUP, OB.STATUS_OB, OB.ID_TARAFKPTG, "
					+ " OB.ID_NEGERI, OB.ID_SAUDARA, OB.JENIS_AGAMA, OB.JENIS_WARGA, "
					+ " OB.CATATAN, OB.LAPIS, OB.TARIKH_MATI, OB.NO_HP, OB.WAKTU_KEMATIAN  "
					+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB WHERE OB1.ID_PERMOHONANSIMATI = OB.ID_PERMOHONANSIMATI "
					+ " AND OB1.ID_OB = OB.ID_OB AND "
					+ " OB.ID_PERMOHONANSIMATI = '" + id + "' ";
			// /###OBPERMOHONAN
			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("GET NAMA PARENT DALAM LIST : " + sql.toUpperCase());
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idwaris", rs.getString("id_Ob") == null ? "" : rs
						.getString("id_Ob"));
				h.put("nama_Ob", rs.getString("nama_Ob") == null ? "" : rs
						.getString("nama_Ob"));
				h.put("status_Ob", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("nokpbaru", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru"));
				//IL

				if((rs.getString("no_Kp_Baru")!=null) && (rs.getString("no_Kp_Baru").length()==12)){
					h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, 12));
				}else{
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=6){
						h.put("noKpBaru1", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(0, 6));
					}else{
						h.put("noKpBaru1","");
					}
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()>=8){
						h.put("noKpBaru2", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(6, 8));
					}else{
						h.put("noKpBaru2","");
					}
					
					if(rs.getString("no_Kp_Baru")!=null && rs.getString("no_Kp_Baru").length()<=12 && rs.getString("no_Kp_Baru").length()>=9){
						h.put("noKpBaru3", rs.getString("no_Kp_Baru") == null ? "" : rs.getString("no_Kp_Baru").substring(8, rs.getString("no_Kp_Baru").length()));
					}else{
						h.put("noKpBaru3","");
					}
				}
				
				//end IL
				/*h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));*/
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));

				h.put("dob", rs.getDate("tarikh_Lahir") == null ? ""
						: formatter.format(rs.getDate("tarikh_Lahir")));

				h.put("umur", rs.getString("umur") == null ? "" : rs
						.getString("umur"));

				h.put("noberanak",
						rs.getString("no_Surat_Beranak") == null ? "" : rs
								.getString("no_Surat_Beranak"));

				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("noTel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("jantina", rs.getString("jantina") == null ? "" : rs
						.getString("jantina"));

				h.put("alamat1", rs.getString("alamat_1") == null ? "" : rs
						.getString("alamat_1"));
				h.put("alamat2", rs.getString("alamat_2") == null ? "" : rs
						.getString("alamat_2"));
				h.put("alamat3", rs.getString("alamat_3") == null ? "" : rs
						.getString("alamat_3"));
				h.put("bandar", rs.getString("bandar") == null ? "" : rs
						.getString("bandar"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("statushidup", rs.getString("status_Hidup") == null ? ""
						: rs.getString("status_Hidup"));
				h.put("taraf", rs.getString("id_Tarafkptg") == null ? "" : rs
						.getString("id_Tarafkptg"));
				h.put("saudara", rs.getString("id_Saudara") == null ? "" : rs
						.getString("id_Saudara"));
				h.put("agama", rs.getString("jenis_Agama") == null ? "" : rs
						.getString("jenis_Agama"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("warga", rs.getString("jenis_Warga") == null ? "" : rs
						.getString("jenis_Warga"));

				h.put("waktumati", rs.getString("waktu_Kematian") == null ? ""
						: rs.getString("waktu_Kematian"));

				h.put("tarikhmati", rs.getDate("tarikh_Mati") == null ? "" : formatter.format(rs.getDate("tarikh_Mati")));

				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs.getString("no_Hp"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs.getString("lapis"));

				// System.out.println(h);
				listWarisOB.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	// TODO MAKLUMAT PELAN

	// TODO END MAKLUMAT PELAN

	public void setDataHTA(String idsimati) throws Exception {
		Db db = null;
		listHTA.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.ID_HTA, H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,"
					+ " H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_BANDARHTA, H.ID_MUKIM, H.LUAS_HMP, PELAN.ID_PELAN, "
					+ " H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
					+ " H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN,  "

					// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
					+ " H.SEKATAN, H.SYARAT_NYATA  , MS.ID_PERMOHONAN  "

					+ " FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, TBLRUJJENISHAKMILIK RUJ "
					+ ", TBLPPKUPLOADPELAN PELAN , TBLPPKDOKUMEN DOKUMEN  " //IL
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
					+ " AND HP.ID_HTA = H.ID_HTA AND HP.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "
					+ " AND PELAN.ID_HTA (+) = H.ID_HTA " //IL
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN " //IL
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.JENIS_HTA = 'Y'  ORDER BY H.ID_HTA DESC";

			// System.out.println("HTAAM :"+sql.toUpperCase());
			//System.out.println("******SQL :"+sql);
			System.out.println("######################HTAAM STRUCTURE BARU" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;
			int i = 0;
			while (rs.next()) {
			    
				h = new Hashtable();
				System.out.println("Try kat sini " + i);
				h.put("alamathta1", rs.getString("alamat_hta1") == null ? "" : rs
						//.getString("alamat_Hta1"));
						.getString("alamat_hta1"));
				h.put("alamathta2", rs.getString("alamat_hta2") == null ? "" : rs
						//.getString("alamat_Hta1"));
						.getString("alamat_hta2"));
				h.put("alamathta3", rs.getString("alamat_hta3") == null ? "" : rs
						//.getString("alamat_Hta1"));
						.getString("alamat_hta3"));
				System.out.println("Alamat1 " + rs.getString("alamat_hta1"));
				System.out.println("Alamat2 " + rs.getString("alamat_hta2"));
				//System.out.println("Try kat sini");
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));		
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));//IL
				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs.getString("id_pelan"));//IL		
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				
				h.put("noperserahan", rs.getString("no_perserahan") == null ? "" : rs
								.getString("NO_PERSERAHAN"));
				
				h.put("kod_hakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());

				//SYAFIQAH ADD UNTUK PAPAR LAMPIRAN 230720
				ekptg.model.ppk.util.LampiranBean l = new ekptg.model.ppk.util.LampiranBean();
				h.put("lampirans", l.getLampirans(rs.getString("ID_HTA")));
				
			    System.out.println(h);
			   // h.put("alamathta2","hta 2");
			    i = i+1;
				listHTA.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataOBHAdulu(String idsimati, String bkp, String lp,
			String bpa, String lpa, String jenis_hta) throws Exception {
		Db db = null;
		listOBHAdulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT OBT.ID_PERINTAHHAOBDTL,OBT.STATUS_TADBIR,TO_CHAR(OB.ID_OB) AS ID_OB,HP.ID_HA,OB.NAMA_OB,OBT.BA,OBT.BB, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA1,OBT.ID_PA1, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA2,OBT.ID_PA2, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA3,OBT.ID_PA3, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA4,OBT.ID_PA4 "
					+ " FROM TBLPPKHA HP,TBLPPKHAPERMOHONAN H, TBLPPKSIMATI S, "
					+ " TBLPPKPERMOHONANSIMATI MS, "
					+ " TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "
					+ " TBLPPKPERMOHONANSIMATI MS1, TBLPPKPERINTAHHAOBMST OBM,"
					+ " TBLPPKPERINTAHHAOBDTL OBT, "
					+ " TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB  "
					+ " WHERE "
					+
					// " OBM.ID_JENISPERINTAH IN (1,7) AND " +
					"H.ID_SIMATI = S.ID_SIMATI AND H.ID_HA = HP.ID_HA  "
					+ " AND H.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ " AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'   "
					+ " AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ " AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ " AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "
					+ " AND H.ID_PERINTAHOBMST = OBM.ID_PERINTAHHAOBMST "
					+ " AND OBT.ID_PERINTAHHAOBMST = OBM.ID_PERINTAHHAOBMST  "
					+ " AND OB.ID_OB =  OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"
					+ idsimati + "' " + " AND OBT.ID_OB = OB1.ID_OB " +
					// " AND OBT.STATUS_TADBIR IN ('Y','T') " +
					"";

			if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("T") && lpa.equals("T"))) {
				sql += " AND H.FLAG_PT = 'Y'";
			} else if ((bkp.equals("T") && lp.equals("T"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND H.FLAG_PA = 'Y'";
			} else if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			} else {
				sql += "";
			}

			// sql += " ORDER BY OB.ID_OB DESC";
			sql += " UNION ";

			// sql +=
			// " SELECT OBT.STATUS_TADBIR,OB.ID_OB,HP.ID_HA,OB.NAMA_OB,OBT.BA,OBT.BB, "+
			sql += " SELECT OBT.ID_PERINTAHHAOBDTL,OBT.STATUS_TADBIR,TO_CHAR(9999 || HP.ID_HA) AS ID_OB, HP.ID_HA, 'TIDAK DAPAT DIKESAN' AS NAMA_OB, OBT.BA, OBT.BB, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA1,OBT.ID_PA1, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA2,OBT.ID_PA2, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA3,OBT.ID_PA3, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA4,OBT.ID_PA4 "
					+ " FROM TBLPPKHA HP,TBLPPKHAPERMOHONAN H, TBLPPKSIMATI S, "
					+ " TBLPPKPERMOHONANSIMATI MS, "
					+ " TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "
					+ " TBLPPKPERMOHONANSIMATI MS1, TBLPPKPERINTAHHAOBMST OBM,"
					+ " TBLPPKPERINTAHHAOBDTL OBT"
					+
					// ", "+
					// " TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB  "+
					" WHERE "
					+
					// "OBM.ID_JENISPERINTAH IN (1,7) AND " +
					"  H.ID_SIMATI = S.ID_SIMATI AND H.ID_HA = HP.ID_HA  "
					+ " AND H.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ " AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'   "
					+ " AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ " AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ " AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "
					+ " AND H.ID_PERINTAHOBMST = OBM.ID_PERINTAHHAOBMST "
					+ " AND OBT.ID_PERINTAHHAOBMST = OBM.ID_PERINTAHHAOBMST AND OBT.ID_OB IS NULL "
					+
					// " AND OB.ID_OB =  OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idsimati+"' "+
					// " AND OBT.ID_OB = OB1.ID_OB "+
					// " AND OBT.STATUS_TADBIR IN ('Y','T') " +
					"";

			if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("T") && lpa.equals("T"))) {
				sql += " AND H.FLAG_PT = 'Y'";
			} else if ((bkp.equals("T") && lp.equals("T"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND H.FLAG_PA = 'Y'";
			} else if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			} else {
				sql += "";
			}

			sql += " ORDER BY ID_OB DESC";

			myLogger.info("LIST OB HA DULU :" + sql.toUpperCase());

			// System.out.println("HTAAM :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			// OB.NAMA_OB,OBT.BA,OBT.BB

			while (rs.next()) {
				
				h = new Hashtable();
				h.put("ID_HA", rs.getString("ID_HA") == null ? "" : rs
						.getString("ID_HA"));
				h.put("ID_OB", rs.getString("ID_OB") == null ? "" : rs
						.getString("ID_OB"));
				h.put("NAMA_OB", rs.getString("NAMA_OB") == null ? "" : rs
						.getString("NAMA_OB"));
				h.put("BA", rs.getString("BA") == null ? "" : rs
						.getString("BA"));
				h.put("BB", rs.getString("BB") == null ? "" : rs
						.getString("BB"));
				h.put("STATUS_TADBIR",
						rs.getString("STATUS_TADBIR") == null ? "" : rs
								.getString("STATUS_TADBIR"));

				h.put("ID_PA1", rs.getString("ID_PA1") == null ? "" : rs
						.getString("ID_PA1"));
				h.put("ID_PA2", rs.getString("ID_PA2") == null ? "" : rs
						.getString("ID_PA2"));
				h.put("ID_PA3", rs.getString("ID_PA3") == null ? "" : rs
						.getString("ID_PA3"));
				h.put("ID_PA4", rs.getString("ID_PA4") == null ? "" : rs
						.getString("ID_PA4"));

				h.put("NAMA_PA1", rs.getString("NAMA_PA1") == null ? "" : rs
						.getString("NAMA_PA1"));
				h.put("NAMA_PA2", rs.getString("NAMA_PA2") == null ? "" : rs
						.getString("NAMA_PA2"));
				h.put("NAMA_PA3", rs.getString("NAMA_PA3") == null ? "" : rs
						.getString("NAMA_PA3"));
				h.put("NAMA_PA4", rs.getString("NAMA_PA4") == null ? "" : rs
						.getString("NAMA_PA4"));

				h.put("ID_PERINTAHHAOBDTL",
						rs.getString("ID_PERINTAHHAOBDTL") == null ? "" : rs
								.getString("ID_PERINTAHHAOBDTL"));

				listOBHAdulu.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataOBHTAdulu(String idsimati, String bkp, String lp,
			String bpa, String lpa, String jenis_hta) throws Exception {
		Db db = null;
		listOBHTAdulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT OBT.ID_PERINTAHHTAOBDTL,OBT.STATUS_TADBIR,TO_CHAR(OB.ID_OB) AS ID_OB,HP.ID_HTA,OB.NAMA_OB,OBT.BA,OBT.BB, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA1,OBT.ID_PA1, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA2,OBT.ID_PA2, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA3,OBT.ID_PA3, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA4,OBT.ID_PA4 "
					+ " FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, "
					+ " TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "
					+ " TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ,"
					+ " TBLPPKPERINTAHHTAOBMST OBM,TBLPPKPERINTAHHTAOBDTL OBT, "
					+ " TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB  "
					+ " WHERE  H.ID_SIMATI = S.ID_SIMATI ";
			/*
			 * if(jenis_hta.equals("Y")) { sql +=
			 * " AND  OBM.ID_JENISPERINTAH IN (1,7) AND OBT.STATUS_TADBIR IN ('Y','T') "
			 * ; } else { sql += "AND  OBM.ID_JENISPERINTAH IN (2)  "; }
			 */
			sql += "AND H.ID_HTA = HP.ID_HTA  AND H.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI  AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = '" + idsimati + "'   ";
			if (!jenis_hta.equals("")) {
				sql += " AND H.JENIS_HTA = '" + jenis_hta + "' ";
			}

			sql += " AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ " AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ " AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "
					+ " AND H.ID_PERINTAHOBMST = OBM.ID_PERINTAHHTAOBMST "
					+ " AND OBT.ID_PERINTAHHTAOBMST = OBM.ID_PERINTAHHTAOBMST  "
					+ " AND OB.ID_OB =  OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"
					+ idsimati + "' "
					+ " AND OBT.ID_OB = OB1.ID_OB AND OBT.ID_OB IS NOT NULL "
					+ "  ";

			if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("T") && lpa.equals("T"))) {
				sql += " AND H.FLAG_PT = 'Y'";
			} else if ((bkp.equals("T") && lp.equals("T"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND H.FLAG_PA = 'Y'";
			} else if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			} else {
				sql += "";
			}

			// UNION
			sql += " UNION";

			sql += " SELECT OBT.ID_PERINTAHHTAOBDTL,OBT.STATUS_TADBIR,TO_CHAR(9999 || H.ID_HTA) AS ID_OB, HP.ID_HTA, 'TIDAK DAPAT DIKESAN' AS NAMA_OB, OBT.BA, OBT.BB, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA1,OBT.ID_PA1, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA2,OBT.ID_PA2, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA3,OBT.ID_PA3, "
					+ " (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "') "
					+ " AS NAMA_PA4,OBT.ID_PA4 "
					+ " FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, "
					+ " TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "
					+ " TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ,"
					+ " TBLPPKPERINTAHHTAOBMST OBM,TBLPPKPERINTAHHTAOBDTL OBT" +
					// ", "+
					// " TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB  "+
					" WHERE  H.ID_SIMATI = S.ID_SIMATI ";
			/*
			 * if(jenis_hta.equals("Y")) { sql +=
			 * " AND  OBM.ID_JENISPERINTAH IN (1,7) AND OBT.STATUS_TADBIR IN ('Y','T') "
			 * ; } else { sql += "AND  OBM.ID_JENISPERINTAH IN (2)  "; }
			 */

			sql += "AND H.ID_HTA = HP.ID_HTA  AND H.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI  AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = '" + idsimati + "'   ";
			if (!jenis_hta.equals("")) {
				sql += " AND H.JENIS_HTA = '" + jenis_hta + "' ";
			}
			sql += " AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ " AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ " AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "
					+ " AND H.ID_PERINTAHOBMST = OBM.ID_PERINTAHHTAOBMST "
					+ " AND OBT.ID_PERINTAHHTAOBMST = OBM.ID_PERINTAHHTAOBMST AND OBT.ID_OB IS NULL "
					+
					// " AND OB.ID_OB =  OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idsimati+"' "+
					// " AND OBT.ID_OB = OB1.ID_OB "+
					"  ";

			if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("T") && lpa.equals("T"))) {
				sql += " AND H.FLAG_PT = 'Y'";
			} else if ((bkp.equals("T") && lp.equals("T"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND H.FLAG_PA = 'Y'";
			} else if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			} else {
				sql += "";
			}

			sql += " ORDER BY ID_OB DESC";

			// sql += " and  rownum = 1 ";

			myLogger.info("LIST OB HTA DULU :" + sql.toUpperCase());

			// System.out.println("HTAAM :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			// OB.NAMA_OB,OBT.BA,OBT.BB

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_HTA", rs.getString("ID_HTA") == null ? "" : rs
						.getString("ID_HTA"));
				h.put("ID_OB", rs.getString("ID_OB") == null ? "" : rs
						.getString("ID_OB"));
				h.put("NAMA_OB", rs.getString("NAMA_OB") == null ? "" : rs
						.getString("NAMA_OB"));
				h.put("BA", rs.getString("BA") == null ? "" : rs
						.getString("BA"));
				h.put("BB", rs.getString("BB") == null ? "" : rs
						.getString("BB"));

				h.put("ID_PA1", rs.getString("ID_PA1") == null ? "" : rs
						.getString("ID_PA1"));
				h.put("ID_PA2", rs.getString("ID_PA2") == null ? "" : rs
						.getString("ID_PA2"));
				h.put("ID_PA3", rs.getString("ID_PA3") == null ? "" : rs
						.getString("ID_PA3"));
				h.put("ID_PA4", rs.getString("ID_PA4") == null ? "" : rs
						.getString("ID_PA4"));

				h.put("NAMA_PA1", rs.getString("NAMA_PA1") == null ? "" : rs
						.getString("NAMA_PA1"));
				h.put("NAMA_PA2", rs.getString("NAMA_PA2") == null ? "" : rs
						.getString("NAMA_PA2"));
				h.put("NAMA_PA3", rs.getString("NAMA_PA3") == null ? "" : rs
						.getString("NAMA_PA3"));
				h.put("NAMA_PA4", rs.getString("NAMA_PA4") == null ? "" : rs
						.getString("NAMA_PA4"));

				h.put("ID_PERINTAHHTAOBDTL", rs
						.getString("ID_PERINTAHHTAOBDTL") == null ? "" : rs
						.getString("ID_PERINTAHHTAOBDTL"));

				h.put("STATUS_TADBIR",
						rs.getString("STATUS_TADBIR") == null ? "" : rs
								.getString("STATUS_TADBIR"));

				listOBHTAdulu.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector listHartaAlihdulu = new Vector();

	public Vector setDataSemuaHartaAlihdulu(String idsimati, String bkp,
			String lp, String bpa, String lpa) throws Exception {
		Db db = null;
		listHartaAlihdulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT OBM.ID_JENISPERINTAH, "
					+ " (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "
					+ " TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "
					+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
					+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH,"
					+ " (SELECT RP.ID_JENISPERINTAH FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "
					+ " TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "
					+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
					+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS ID_JENISPERINTAH,"
					+ " H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, H.NILAI_HA_TARIKHMOHON, "
					+ "H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
					+ "H.ALAMAT_HA3, H.POSKOD,H.BUTIRAN,H.NAMA_SAHAM,H.JENAMA,  "
					+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI "
					+ "FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS,"
					+ "TBLPPKSIMATI S, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1,TBLPPKPERINTAHHAOBMST OBM  "
					+ "WHERE H1.ID_HA = H.ID_HA AND H.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "' "
					+ " AND OBM.ID_HA = H.ID_HA AND OBM.FLAG_HARTA = 'L' "
					+ " AND H.ID_SIMATI = S.ID_SIMATI "
					+ "AND H.ID_JENISHA = J.ID_JENISHA AND OBM.ID_JENISPERINTAH = '2' "
					+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND H1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI "
					+ "AND MS.ID_PERMOHONANSIMATI = '" + idsimati + "' "
					+ "AND H1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
					+ "AND S.ID_SIMATI = MS.ID_SIMATI ";
			if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("T") && lpa.equals("T"))) {
				sql += " AND H.FLAG_PT = 'Y'";
			} else if ((bkp.equals("T") && lp.equals("T"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND H.FLAG_PA = 'Y'";
			} else if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			} else {
				sql += "";
			}
			sql += "ORDER BY H.ID_HA DESC";

			myLogger.info("LIST SEMUA HA DULU :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("ID_JENISPERINTAH",
						rs.getString("ID_JENISPERINTAH") == null ? "" : rs
								.getString("ID_JENISPERINTAH"));
				h.put("JENIS_PERINTAH",
						rs.getString("JENIS_PERINTAH") == null ? "" : rs
								.getString("JENIS_PERINTAH"));
				h.put("id_Ha", rs.getString("id_Ha") == null ? "" : rs
						.getString("id_Ha"));
				h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
						: rs.getString("id_Jenisha"));
				h.put("id_Simati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nosijil", rs.getString("no_sijil") == null ? "" : rs
						.getString("no_sijil"));
				h.put("noDaftar", rs.getString("no_Daftar") == null ? "" : rs
						.getString("no_Daftar"));
				h.put("Kod", rs.getString("kod") == null ? "" : rs
						.getString("kod"));

				h.put("alamat1", rs.getString("alamat_ha1") == null ? "" : rs
						.getString("alamat_ha1"));
				h.put("alamat2", rs.getString("alamat_ha2") == null ? "" : rs
						.getString("alamat_ha2"));
				h.put("alamat3", rs.getString("alamat_ha3") == null ? "" : rs
						.getString("alamat_ha3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("Keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("nilai_tarikhmohon",
						rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmohon"));
				h.put("nilai_tarikhmati",
						rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
								.getDouble("nilai_ha_tarikhmati"));

				h.put("nama_saham", rs.getString("nama_saham") == null ? ""
						: rs.getString("nama_saham"));
				h.put("jenama", rs.getString("jenama") == null ? "" : rs
						.getString("jenama"));

				h.put("butiran", rs.getString("butiran") == null ? "" : rs
						.getString("butiran"));

				listHartaAlihdulu.addElement(h);
				bil++;
			}
			return listHartaAlihdulu;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector listHartaTakAlihdulu = new Vector();

	public Vector setDataSemuaHartaTakAlihdulu(String idsimati, String bkp,
			String lp, String bpa, String lpa) throws Exception {
		Db db = null;
		listHartaTakAlihdulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT "
					+ " OBM.ID_JENISPERINTAH,(SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "
					+ " TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "
					+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
					+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH,"
					+ " (SELECT RP.ID_JENISPERINTAH FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "
					+ " TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "
					+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
					+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS ID_JENISPERINTAH,"
					+ " H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, "
					+ "H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, "
					+ "H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, "
					+ "H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,  H.NO_PERSERAHAN, "
					+ "H.CATATAN, H.STATUS_PEMILIKAN, "
					+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN  "
					+ "FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, "
					+ "TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, "
					+ "TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ,TBLPPKPERINTAHHTAOBMST OBM   "
					+ "WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_HTA = HP.ID_HTA AND OBM.ID_HTA = H.ID_HTA AND OBM.FLAG_HARTA = 'L' "
					+ " AND H.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'   "
					+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ "AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "
					+ "AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'   "
					+
					// "AND H.JENIS_HTA = 'Y' " +
					"AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+) AND OBM.ID_JENISPERINTAH = '2' "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
					+ "AND S.ID_SIMATI = MS.ID_SIMATI ";

			if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("T") && lpa.equals("T"))) {
				sql += " AND H.FLAG_PT = 'Y'";
			} else if ((bkp.equals("T") && lp.equals("T"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND H.FLAG_PA = 'Y'";
			} else if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			} else {
				sql += "";
			}
			sql += " ORDER BY H.ID_HTA DESC";

			myLogger.info("LIST SEMUA HTA DULU :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				//h.put("alamat1", rs.getString("alamat_Hta1") == null ? "" : rs
						//.getString("alamat_Hta1"));
				h.put("ID_JENISPERINTAH",
						rs.getString("ID_JENISPERINTAH") == null ? "" : rs
								.getString("ID_JENISPERINTAH"));
				h.put("JENIS_PERINTAH",
						rs.getString("JENIS_PERINTAH") == null ? "" : rs
								.getString("JENIS_PERINTAH"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_perserahan") == null ? "" : rs
								.getString("no_perserahan"));
				h.put("flag_pt", rs.getString("FLAG_PT") == null ? "" : rs
						.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA") == null ? "" : rs
						.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA") == null ? "" : rs
						.getString("FLAG_PA"));
				h.put("kod_hakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				listHartaTakAlihdulu.addElement(h);
			}
			return listHartaTakAlihdulu;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTAdulu(String idsimati, String bkp, String lp,
			String bpa, String lpa) throws Exception {
		Db db = null;
		listHTAdulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//aishahidris comment on 2ndOct2017
			/*sql = "SELECT "
					+ " (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "
					+ " TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "
					+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
					+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH,"
					+ " (SELECT RP.ID_JENISPERINTAH FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "
					+ " TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "
					+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
					+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS ID_JENISPERINTAH,"
					+ " H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, "
					+ "H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, "
					+ "H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, "
					+ "H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,  H.NO_PERSERAHAN, "
					+ "H.CATATAN, H.STATUS_PEMILIKAN, "
					+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN  "
					+ "FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, "
					+ "TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, "
					+ "TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ   "
					+ "WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_HTA = HP.ID_HTA "
					+ " AND H.ID_PERMOHONANSIMATI = '" + idsimati + "'   "
					+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ "AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "
					+ "AND MS.ID_PERMOHONANSIMATI = '" + idsimati + "'   "
					+ "AND H.JENIS_HTA = 'Y' "
					+ "AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
					+ "AND S.ID_SIMATI = MS.ID_SIMATI ";*/
			
			//aishahidris tukar query on 2ndOct2017
			sql = " SELECT DISTINCT JP.ID_JENISPERINTAH,JP.JENIS_PERINTAH, "
					+ " H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, "
					+ " H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, "
					+ " H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, "
					+ " H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, "
					+ " H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
					+ " H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.FLAG_PA, H.FLAG_PT, "
					+ " H.FLAG_SELESAI, RUJ.KOD_JENIS_HAKMILIK, RUJ.KETERANGAN "
					+ " FROM TBLPPKHTA HP, TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, "
					+ " TBLPPKPERMOHONAN P1, TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ, "
					+ " (SELECT DISTINCT RP.ID_JENISPERINTAH, RP.KETERANGAN AS JENIS_PERINTAH, OBM.ID_HTA , SM.ID_PERMOHONANSIMATI "
					+ " FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM, TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN KB, "
					+ " TBLPPKPERINTAH PR, TBLPPKPERINTAHHTAOBMST OBM, TBLPPKRUJJENISPERINTAH RP "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN "
					+ " AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN "
					+ " AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN "
					+ " AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
					+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) JP "
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
					+ " AND H.ID_HTA = HP.ID_HTA "
					+ " AND H.ID_PERMOHONANSIMATI = '" + idsimati + "' "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI "
					+ " AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
					+ " AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "
					+ " AND MS.ID_PERMOHONANSIMATI = '" + idsimati + "' "
					+ " AND H.JENIS_HTA = 'Y' "
					+ " AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI "
					+ " AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+) "
					+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+) "
					+ " AND P1.NO_SUBJAKET < P.NO_SUBJAKET "
					+ " AND S.ID_SIMATI = MS.ID_SIMATI "
					+ " AND JP.ID_HTA(+) = HP.ID_HTA "
					+ " AND JP.ID_PERMOHONANSIMATI(+) = HP.ID_PERMOHONANSIMATI ";
			
			
			System.out.println("bkp==="+bkp);
			System.out.println("lp==="+lp);
			System.out.println("lpa==="+lpa);
			System.out.println("bpa==="+bpa);
			
			if ((bkp.equals("Y") || lp.equals("Y"))&& (bpa.equals("T") && lpa.equals("T"))) {
				sql += " AND H.FLAG_PT = 'Y'";
			} else if ((bkp.equals("T") && lp.equals("T"))&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND H.FLAG_PA = 'Y'";
			} else if ((bkp.equals("Y") || lp.equals("Y"))&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			} else {
				sql += "";
			}

			sql += " ORDER BY H.ID_HTA DESC";
			// sql += " and  rownum = 1 ";

			myLogger.info("LIST HTA DULU :" + sql.toUpperCase());

			// System.out.println("HTAAM :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
		//		h.put("alamat1", rs.getString("alamat_Hta1") == null ? "" : rs
			//			.getString("alamat_Hta1"));
				h.put("ID_JENISPERINTAH",
						rs.getString("ID_JENISPERINTAH") == null ? "" : rs
								.getString("ID_JENISPERINTAH"));
				h.put("JENIS_PERINTAH",
						rs.getString("JENIS_PERINTAH") == null ? "" : rs
								.getString("JENIS_PERINTAH"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				
				//h.put("noperserahan", rs.getString("noperserahan") == null ? "" : rs
				//				.getString("noperserahan"));
				
				h.put("flag_pt", rs.getString("FLAG_PT") == null ? "" : rs
						.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA") == null ? "" : rs
						.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA") == null ? "" : rs
						.getString("FLAG_PA"));

				h.put("kod_hakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				listHTAdulu.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector listHTAdulu_kp = new Vector();

	public void setDataHTAdulu_KP(String idsimati) throws Exception {
		Db db = null;
		listHTAdulu_kp.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql="SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI,"
			 * +
			 * " H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP,"
			 * +
			 * " H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
			 * +" H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN  "
			 * +" FROM TBLPPKHTA H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS "
			 * +" WHERE H.ID_SIMATI = S.ID_SIMATI  "
			 * +" AND H.ID_SIMATI = MS.ID_SIMATI "
			 * +" AND MS.ID_PERMOHONANSIMATI <> H.ID_PERMOHONANSIMATI "
			 * +" AND MS.ID_PERMOHONANSIMATI = '"+idsimati+"'  "
			 * +" AND H.JENIS_HTA = 'Y'  ORDER BY H.ID_HTA DESC";
			 */

			sql = "SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, "
					+ "H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, "
					+ "H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, "
					+ "H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,  H.NO_PERSERAHAN, "
					+ "H.CATATAN, H.STATUS_PEMILIKAN, "
					+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI "
					+ "FROM TBLPPKHTA H, TBLPPKSIMATI S, "
					+ "TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, "
					+ "TBLPPKPERMOHONANSIMATI MS1   "
					+ "WHERE H.ID_SIMATI = S.ID_SIMATI   "
					+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND MS.ID_PERMOHONANSIMATI <> H.ID_PERMOHONANSIMATI "
					+ "AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'   "
					+ "AND H.JENIS_HTA = 'Y' "
					+ "AND H.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
					+ "AND S.ID_SIMATI = MS.ID_SIMATI ";
			/*
			 * 
			 * if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("T") &&
			 * lpa.equals("T"))) { sql += " AND H.FLAG_PT = 'Y'"; } else
			 * if((bkp.equals("T") && lp.equals("T")) && (bpa.equals("Y") ||
			 * lpa.equals("Y"))) { sql += " AND H.FLAG_PA = 'Y'"; } else
			 * if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("Y") ||
			 * lpa.equals("Y"))) { sql +=
			 * " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') "; } else { sql += "";
			 * }
			 */
			sql += " ORDER BY H.ID_HTA DESC";

			// System.out.println("HTAAM :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
			//	h.put("alamat1", rs.getString("alamat_Hta1") == null ? "" : rs
				//		.getString("alamat_Hta1"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_perserahan") == null ? "" : rs
								.getString("no_perserahan"));

				h.put("flag_pt", rs.getString("FLAG_PT") == null ? "" : rs.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA") == null ? "" : rs.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA") == null ? "" : rs.getString("FLAG_PA"));

				// System.out.println(h);
				listHTAdulu_kp.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public Vector getDataHTAdulu_KP() {
		return listHTAdulu_kp;
	}

	/*
	 * public void setDataHTAbyIdHtaam(String idhtaam) throws Exception { Db db
	 * = null; listHTAbyIdHtaam.clear(); String sql = ""; SimpleDateFormat sdf =
	 * new SimpleDateFormat("dd/MM/yyyy");
	 * 
	 * try{ db = new Db(); Statement stmt = db.getStatement();
	 * 
	 * 
	 * sql =
	 * "SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, "
	 * +
	 * "H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, "
	 * +
	 * "H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, "
	 * +
	 * "H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
	 * + "H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN  " +
	 * "FROM TBLPPKHTA H, TBLPPKSIMATI S WHERE H.ID_SIMATI = S.ID_SIMATI  " +
	 * "AND H.JENIS_HTA = 'Y'  AND H.ID_HTA = '"+idhtaam+"'";
	 * 
	 * 
	 * 
	 * ResultSet rs = stmt.executeQuery(sql); //
	 * 
	 * myLogger.info("SQL setDataHTAbyIdHtaam  :" + sql.toUpperCase());
	 * 
	 * Hashtable h;
	 * 
	 * while(rs.next()) { h = new Hashtable(); h.put("idhta",
	 * rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
	 * h.put("noHakmilik",
	 * rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));
	 * 
	 * h.put("idSimati",
	 * rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
	 * h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
	 * h.put("nilai_Hta_memohon",
	 * rs.getString("nilai_Hta_Tarikhmohon")==null?"":
	 * rs.getDouble("nilai_Hta_Tarikhmohon")); h.put("nilai_Hta_mati",
	 * rs.getString
	 * ("nilai_Hta_Tarikhmati")==null?"":rs.getDouble("nilai_Hta_Tarikhmati"));
	 * h.put("kategori",
	 * rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
	 * h.put("jenishakmilik",
	 * rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
	 * h.put("pemilikan",
	 * rs.getString("status_Pemilikan")==null?"":rs.getString(
	 * "status_Pemilikan")); h.put("negeri",
	 * rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
	 * h.put("daerah",
	 * rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
	 * h.put("mukim",
	 * rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
	 * h.put("luashmp",
	 * rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
	 * h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
	 * h.put("nocagaran",
	 * rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
	 * h.put("nopajakan",
	 * rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
	 * h.put("jenistanah",
	 * rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
	 * h.put("basimati",
	 * rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
	 * h.put("bbsimati",
	 * rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
	 * h.put("jenishta",
	 * rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
	 * h.put("tanggungan",
	 * rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
	 * h.put("jenisluas",
	 * rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
	 * h.put("catatan",
	 * rs.getString("catatan")==null?"":rs.getString("catatan"));
	 * h.put("noperserahan",
	 * rs.getString("")==null?"":rs.getString(""));
	 * 
	 * 
	 * //System.out.println(h); listHTAbyIdHtaam.addElement(h); }
	 * 
	 * }
	 * 
	 * catch (SQLException se2) { throw new
	 * Exception("Error paparan:"+se2.getMessage());
	 * 
	 * }
	 * 
	 * finally { if(db != null) db.close(); }
	 * 
	 * }
	 */

	public void setDataHTAbyIdHtaam_online(String idhtaam) throws Exception {
		Db db = null;
		listHTAbyIdHtaam.clear();
		String sql = "";
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, "
					+ "H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, "
					+ "H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, "
					+ "H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
					+ "H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN  "
					+ "FROM TBLPPKHTA H, TBLPPKSIMATI S WHERE H.ID_SIMATI = S.ID_SIMATI  "
					+ "AND H.JENIS_HTA = 'Y'  AND H.ID_HTA = '" + idhtaam + "'";

			//System.out.println("HTAAM :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			while (rs.next()) {
				System.out.println("Baca sini3");
				h = new Hashtable();
			//	h.put("alamat1", rs.getString("alamat_Hta1") == null ? "" : rs
				//		.getString("alamat_Hta1"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));

				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan",
						rs.getString("status_Pemilikan") == null ? "" : rs
								.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_perserahan") == null ? "" : rs
								.getString("no_perserahan"));

				// System.out.println(h);
				listHTAbyIdHtaam.addElement(h);
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void setDataKehadiranBicara_online(String id_perbicaraan) throws Exception {
		Db db = null;
		listKehadiran.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT NAMA, HUBUNGAN, KEHADIRAN FROM TBLBICARAONLINEHADIR WHERE ID_PERBICARAAN = "+ id_perbicaraan;
			myLogger.info("sql : Kehadiran bicara "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				System.out.println("Baca senarai kehadiran");
				h = new Hashtable();
				h.put("nama", rs.getString("nama") == null ? "" : rs
						.getString("nama"));
				
				h.put("hubungan", rs.getString("hubungan") == null ? "" : rs
						.getString("hubungan"));
				
				h.put("kehadiran", rs.getString("kehadiran") == null ? "" : rs
						.getString("kehadiran"));
				

				// System.out.println(h);
				listKehadiran.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void setDataKehadiranBicara_online2(String id_perbicaraan, String nama, String hubungan, String kehadiran) throws Exception {
		Db db = null;
		listKehadiran.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "INSERT into TBLBICARAONLINEHADIR (nama, hubungan, kehadiran) VALUE ("+nama+","+hubungan+","+kehadiran+" ) WHERE ID_PERBICARAAN = "+ id_perbicaraan;
			
			myLogger.info("sql : Kehadiran bicara "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				System.out.println("Baca senarai kehadiran");
				h = new Hashtable();
				h.put("nama", rs.getString("nama") == null ? "" : rs
						.getString("nama"));
				
				h.put("hubungan", rs.getString("hubungan") == null ? "" : rs
						.getString("hubungan"));
				
				h.put("kehadiran", rs.getString("kehadiran") == null ? "" : rs
						.getString("kehadiran"));
				

				// System.out.println(h);
				listKehadiran.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTAbyIdHtaam(String idhtaam, String id_permohonansimati)
			throws Exception {

		Db db = null;
		listHTAbyIdHtaam.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.FLAG_DAFTAR,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, "
					+ "H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, "
					+ "H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, "
					+ "H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, H.ALAMAT_HTA1, H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.BANDAR_HTA, H.POSKOD_HTA, H.ID_BANDARHTA, "
					+ "H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN,DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,  "
					+
					// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
					" H.SEKATAN, H.SYARAT_NYATA  "
					+

					" FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S , TBLPPKUPLOADPELAN PELAN , TBLPPKDOKUMEN DOKUMEN "
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI"
					+ "  AND HP.ID_HTA = H.ID_HTA " + " AND PELAN.ID_HTA (+) = H.ID_HTA  "
					
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN  "
					// " AND H.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI " +
					+ " AND H.ID_PERMOHONANSIMATI = '"
					+ id_permohonansimati
					+ "'"
					+ " AND H.JENIS_HTA = 'Y'  AND H.ID_HTA = '"
					+ idhtaam
					+ "'";
			
			System.out.println("HTAAM :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			while (rs.next()) {
				System.out.println("Baca sini4");
				h = new Hashtable();
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? "" : rs.getString("FLAG_DAFTAR"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));//IL
				h.put("namaPelanUp", rs.getString("nama_dokumen") == null ? "" : rs.getString("nama_dokumen"));//IL
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "" : rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan",
						rs.getString("status_Pemilikan") == null ? "" : rs
								.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN"));
				
				// ADD BY SALNIZAM - ADD FIELD ALAMAT
				h.put("alamat1", rs.getString("alamat_Hta1") == null ? "" : rs
						.getString("alamat_Hta1"));
				h.put("alamat2", rs.getString("alamat_Hta2") == null ? "" : rs
						.getString("alamat_Hta2"));
				h.put("alamat2", rs.getString("alamat_Hta2") == null ? "" : rs
						.getString("alamat_Hta2"));
				h.put("alamat3", rs.getString("alamat_Hta3") == null ? "" : rs
						.getString("alamat_Hta3"));
				h.put("bandar", rs.getString("ID_BANDARHTA") == null ? "" : rs.getString("ID_BANDARHTA"));
				System.out.println("Baca sini4##### " + rs.getString("ID_BANDARHTA"));
				h.put("poskod", rs.getString("poskod_hta") == null ? "" : rs
						.getString("poskod_hta"));		
			//	h.put("id_bandarhta", rs.getString("id_bandarhta") == null ? "" : rs
				//		.getString("id_bandarhta"));	

				

				// ADD BY PEJE - ADD FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("syaratNyata", rs.getString("SYARAT_NYATA") == null ? ""
						: rs.getString("SYARAT_NYATA").toUpperCase());

				listHTAbyIdHtaam.addElement(h);
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTAXdulu(String idsimati) throws Exception {
		Db db = null;
		listHTAXdulu.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI,  "
					+ "H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP,  "
					+ "H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, "
					+ "H.TANGGUNGAN,  H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NO_PERJANJIAN, H.NO_ROH,   "
					+ " H.ALAMAT_HTA1,H.ALAMAT_HTA2,H.ALAMAT_HTA3, H.POSKOD_HTA ,H.JENIS_KEPENTINGAN,H.FLAG_KATEGORI_HTA  "
					+ "FROM TBLPPKHTA H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS,TBLPPKPERMOHONAN P1,"
					+ "TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1   "
					+ "WHERE H.ID_SIMATI = S.ID_SIMATI  "
					+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND MS.ID_PERMOHONANSIMATI <> H.ID_PERMOHONANSIMATI   "
					+ "AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ "AND H.JENIS_HTA = 'T' "
					+ "AND H.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
					+ "AND S.ID_SIMATI = MS.ID_SIMATI "
					+ "ORDER BY H.ID_HTA DESC";

			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			while (rs.next()) {
				System.out.println("Baca sini5");
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_perserahan") == null ? "" : rs
								.getString("no_perserahan"));
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));

				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));
				h.put("alamat1", rs.getString("ALAMAT_HTA1") == null ? "" : rs
						.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2") == null ? "" : rs
						.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3") == null ? "" : rs
						.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA") == null ? "" : rs
						.getString("POSKOD_HTA"));
				h.put("jenis_penting",
						rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs
								.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta",
						rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs
								.getString("FLAG_KATEGORI_HTA"));

				// System.out.println(h);
				listHTAXdulu.addElement(h);
			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		}finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTAXdulu_Pilihan(String idsimati, String bkp, String lp,
			String bpa, String lpa) throws Exception {
		Db db = null;
		listHTAXdulu_pilihan.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT "
					+ " (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "
					+ " TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "
					+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
					+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH,"
					+ " (SELECT RP.ID_JENISPERINTAH FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "
					+ " TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "
					+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
					+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS ID_JENISPERINTAH,"
					+ "H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI,  "
					+ "H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP,  "
					+ "H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, "
					+ "H.TANGGUNGAN,  H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NO_PERJANJIAN, H.NO_ROH,   "
					+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI, "
					+ " H.ALAMAT_HTA1,H.ALAMAT_HTA2,H.ALAMAT_HTA3, H.POSKOD_HTA ,H.JENIS_KEPENTINGAN,H.FLAG_KATEGORI_HTA  "
					+ "FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS,TBLPPKPERMOHONAN P1,"
					+ "TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1   "
					+ "WHERE H.ID_SIMATI = S.ID_SIMATI AND HP.ID_HTA = H.ID_HTA AND H.ID_PERMOHONANSIMATI = '"
					+ idsimati + "'" + "AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI   "
					+ "AND MS.ID_PERMOHONANSIMATI = '" + idsimati + "'  "
					+ "AND H.JENIS_HTA = 'T' "
					+ "AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
					+ "AND S.ID_SIMATI = MS.ID_SIMATI ";

			if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("T") && lpa.equals("T"))) {
				sql += " AND H.FLAG_PT = 'Y'";
			} else if ((bkp.equals("T") && lp.equals("T"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND H.FLAG_PA = 'Y'";
			} else if ((bkp.equals("Y") || lp.equals("Y"))
					&& (bpa.equals("Y") || lpa.equals("Y"))) {
				sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			} else {
				sql += "";
			}

			sql += "ORDER BY H.ID_HTA DESC";

			myLogger.info("HARTA tak alih tiada hakkmilik dulu"
					+ sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			while (rs.next()) {
				System.out.println("Baca sini6");
				h = new Hashtable();
				h.put("ID_JENISPERINTAH",
						rs.getString("ID_JENISPERINTAH") == null ? "" : rs
								.getString("ID_JENISPERINTAH"));
				h.put("JENIS_PERINTAH",
						rs.getString("JENIS_PERINTAH") == null ? "" : rs
								.getString("JENIS_PERINTAH"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs
						.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? ""
						: rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
						.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs
						.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs
						.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs
						.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs
						.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? ""
						: rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs
						.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs
						.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs
						.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs
						.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs
						.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs
						.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs
						.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs
						.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs
						.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs
						.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? ""
						: rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs
						.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs
						.getString("catatan"));
				h.put("noperserahan",
						rs.getString("no_perserahan") == null ? "" : rs
								.getString("no_perserahan"));
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));

				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));
				h.put("alamat1", rs.getString("ALAMAT_HTA1") == null ? "" : rs
						.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2") == null ? "" : rs
						.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3") == null ? "" : rs
						.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA") == null ? "" : rs
						.getString("POSKOD_HTA"));
				h.put("jenis_penting",
						rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs
								.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta",
						rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs
								.getString("FLAG_KATEGORI_HTA"));
				h.put("FLAG_PA", rs.getString("FLAG_PA") == null ? "" : rs
						.getString("FLAG_PA"));
				h.put("FLAG_PT", rs.getString("FLAG_PT") == null ? "" : rs
						.getString("FLAG_PT"));

				// System.out.println(h);
				listHTAXdulu_pilihan.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTAX(String idsimati) throws Exception {
		Db db = null;
		listHTAX.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, DOKUMEN.ID_DOKUMEN , PELAN.ID_PELAN ,"
					+ " H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, "
					+ " H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN, "
					+ " H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NO_PERJANJIAN, H.NO_ROH,"
					+ " H.ALAMAT_HTA1,H.ALAMAT_HTA2,H.ALAMAT_HTA3, H.POSKOD_HTA ,H.JENIS_KEPENTINGAN,H.FLAG_KATEGORI_HTA  "
					+ " FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS ,  TBLPPKDOKUMEN DOKUMEN,  TBLPPKUPLOADPELAN PELAN"
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
					+ " AND H.ID_HTA = HP.ID_HTA AND HP.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ " AND PELAN.ID_HTA (+) = H.ID_HTA " //IL
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN " //IL
					+ " AND MS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
					+ " AND MS.ID_PERMOHONANSIMATI = '"
					+ idsimati
					+ "'  "
					+ " AND H.JENIS_HTA = 'T'  " + " ORDER BY H.ID_HTA DESC ";
			myLogger.info("HTAAMX STRUCTURE BARU :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));//IL
				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs.getString("id_pelan"));//IL
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "" : rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? "" : rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb") == null ? "" : rs.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? "" : rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("noperserahan",rs.getString("no_perserahan") == null ? "" : rs.getString("no_perserahan"));
				h.put("noperjanjian",rs.getString("no_Perjanjian") == null ? "" : rs.getString("no_Perjanjian"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs.getString("no_Roh"));
				h.put("alamat1", rs.getString("ALAMAT_HTA1") == null ? "" : rs.getString("ALAMAT_HTA1"));
				h.put("alamat2", rs.getString("ALAMAT_HTA2") == null ? "" : rs.getString("ALAMAT_HTA2"));
				h.put("alamat3", rs.getString("ALAMAT_HTA3") == null ? "" : rs.getString("ALAMAT_HTA3"));
				h.put("poskod", rs.getString("POSKOD_HTA") == null ? "" : rs.getString("POSKOD_HTA"));
				h.put("jenis_penting",rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs.getString("JENIS_KEPENTINGAN"));
				h.put("ketegori_hta",rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
				
				//SYAFIQAH ADD UNTUK PAPAR LAMPIRAN 230720
				ekptg.model.ppk.util.LampiranBean l = new ekptg.model.ppk.util.LampiranBean();
				h.put("lampirans", l.getLampirans(rs.getString("ID_HTA")));

				listHTAX.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void setDataHTAXbyIdHtaam(String idhtaam, String id_permohonansimati)
			throws Exception {
		Db db = null;
		listHTAXbyIdHtaam.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.FLAG_DAFTAR,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, DOKUMEN.ID_DOKUMEN , DOKUMEN.NAMA_DOKUMEN ,PELAN.ID_PELAN ,"
					+ " H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, "
					+ " H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, "
					+ " H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, "
					+ " H.TANGGUNGAN, H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.NAMA_PEMAJU, "
					+ " H.ALAMAT_PEMAJU1, H.ALAMAT_PEMAJU2, H.ALAMAT_PEMAJU3, H.POSKOD_PEMAJU, "
					+ " H.BANDAR_PEMAJU, H.ID_BANDARPEMAJU, H.ID_NEGERIPEMAJU, H.ALAMAT_HTA1, "
					+ " H.ALAMAT_HTA2, H.ALAMAT_HTA3, H.POSKOD_HTA, H.BANDAR_HTA, H.ID_BANDARHTA, "
					+ " H.NO_PERJANJIAN, H.TARIKH_PERJANJIAN, H.NAMA_RANCANGAN, H.NO_ROH, H.NO_LOT_ID, "
					+ " H.FLAG_KATEGORI_HTA, H.JENIS_KEPENTINGAN  "
					+ " FROM TBLPPKHTA HP, TBLPPKHTAPERMOHONAN H,TBLPPKSIMATI S , TBLPPKDOKUMEN DOKUMEN,  TBLPPKUPLOADPELAN PELAN "
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI  "
					+ " AND PELAN.ID_HTA (+) = H.ID_HTA "
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN "
					+ " AND HP.ID_HTA = H.ID_HTA "
					+
					// " AND H.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI " +
					" AND H.ID_PERMOHONANSIMATI = '"
					+ id_permohonansimati
					+ "'"
					+ " AND H.JENIS_HTA = 'T'  AND H.ID_HTA = '"
					+ idhtaam + "' ";

			myLogger.info("***GET BY ID HTAAMX :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? "" : rs.getString("FLAG_DAFTAR"));
				h.put("idhta", rs.getString("id_Hta") == null ? "" : rs.getString("id_Hta"));
				h.put("idDokumen", rs.getString("id_dokumen") == null ? "" : rs.getString("id_dokumen"));
				h.put("idPelan", rs.getString("id_pelan") == null ? "" : rs.getString("id_pelan"));
				h.put("namaPelan", rs.getString("nama_dokumen") == null ? "" : rs.getString("nama_dokumen"));
				h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "" : rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt") == null ? "" : rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon") == null ? "" : rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori") == null ? "" : rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm") == null ? "" : rs.getString("id_Jenishm"));
				h.put("pemilikan",rs.getString("status_Pemilikan") == null ? "" : rs.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah") == null ? "" : rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim") == null ? "" : rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp") == null ? "" : rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas") == null ? "" : rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran") == null ? "" : rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan") == null ? "" : rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh") == null ? "" : rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati") == null ? "" : rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati") == null ? "" : rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta") == null ? "" : rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan") == null ? "" : rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas") == null ? "" : rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan") == null ? "" : rs.getString("catatan"));
				h.put("noperserahan",rs.getString("no_perserahan") == null ? "" : rs.getString("no_perserahan"));
				h.put("flag", rs.getString("flag_Kategori_Hta") == null ? "" : rs.getString("flag_Kategori_Hta"));
				h.put("namapemaju", rs.getString("nama_Pemaju") == null ? "" : rs.getString("nama_Pemaju"));
				h.put("alamatpemaju1",rs.getString("alamat_Pemaju1") == null ? "" : rs.getString("alamat_Pemaju1"));
				h.put("alamatpemaju2",
						rs.getString("alamat_Pemaju2") == null ? "" : rs
								.getString("alamat_Pemaju2"));
				h.put("alamatpemaju3",
						rs.getString("alamat_Pemaju3") == null ? "" : rs
								.getString("alamat_Pemaju3"));
				h.put("poskodpemaju",
						rs.getString("poskod_Pemaju") == null ? "" : rs
								.getString("poskod_Pemaju"));
				h.put("bandarpemaju",
						rs.getString("id_bandarpemaju") == null ? "" : rs
								.getString("id_bandarpemaju"));
				h.put("negeripemaju",
						rs.getString("id_Negeripemaju") == null ? "" : rs
								.getString("id_Negeripemaju"));
				h.put("alamathta1", rs.getString("alamat_Hta1") == null ? ""
						: rs.getString("alamat_Hta1"));
				h.put("alamathta2", rs.getString("alamat_Hta2") == null ? ""
						: rs.getString("alamat_Hta2"));
				h.put("alamathta3", rs.getString("alamat_Hta3") == null ? ""
						: rs.getString("alamat_Hta3"));
				h.put("poskodhta", rs.getString("poskod_Hta") == null ? "" : rs
						.getString("poskod_Hta"));
				h.put("bandarhta", rs.getString("id_bandarhta") == null ? ""
						: rs.getString("id_bandarhta"));
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));
				h.put("tarikhperjanjian",
						rs.getString("tarikh_Perjanjian") == null ? ""
								: formatter.format(rs
										.getDate("tarikh_Perjanjian")));
				h.put("namarancangan",
						rs.getString("nama_Rancangan") == null ? "" : rs
								.getString("nama_Rancangan"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));
				h.put("nolot", rs.getString("no_Lot_Id") == null ? "" : rs
						.getString("no_Lot_Id"));
				// h.put("flag",
				// rs.getString("flag_Kategori_Hta")==null?"":rs.getString("flag_Kategori_Hta"));
				h.put("jeniskepentingan",
						rs.getString("jenis_Kepentingan") == null ? "" : rs
								.getString("jenis_Kepentingan"));

				// System.out.println(h);
				listHTAXbyIdHtaam.addElement(h);
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateStatus(HttpSession session, Hashtable data) throws Exception {

		String idpermohonan = (String) data.get("idPermohonan");
		int idstatus = (Integer) data.get("idstatus");
		String id_Fail = (String) data.get("id_Fail");
		String userId = (String) data.get("userId");
		String id_Suburusanstatus = (String) data.get("id_Suburusanstatus");
		String id_Suburusanstatusfail = (String) data.get("id_Suburusanstatusfail");

		myLogger.info("SSF KEMASKINI 9");
		FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
		logic_A.kemaskiniSubUrusanStatusFail(session, idpermohonan + "",
				userId, "14", "353", id_Fail + "");

	}

	public void updateStatus17(HttpSession session, Hashtable data) throws Exception {

		String idpermohonan = (String) data.get("idPermohonan");
		int idstatus = (Integer) data.get("idstatus");
		String id_Fail = (String) data.get("id_Fail");
		String userId = (String) data.get("userId");
		String id_Suburusanstatus = (String) data.get("id_Suburusanstatus");
		String id_Suburusanstatusfail = (String) data
				.get("id_Suburusanstatusfail");

		FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
		logic_A.kemaskiniSubUrusanStatusFail(session, idpermohonan + "",
				userId, "14", "434", id_Fail + "");

	}

	public void checkpemohonwaris(String idpemohon) throws Exception {
		Db db = null;
		listcheckpemohonwaris.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "select id_ob,id_pemohon from tblppkob where id_pemohon is not null and  id_pemohon = '"
					+ idpemohon + "' ";

			// System.out.println("SQL PRINT PEMOHON WARIS:"+sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			Integer count = 0;

			while (rs.next()) {

				h = new Hashtable();

				h.put("id_ob", rs.getString("id_ob") == null ? "" : rs
						.getString("id_ob"));
				listcheckpemohonwaris.addElement(h);

			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector list_semak_hta = new Vector();

	public Vector setTujuanSemak_hta(String idpermohonanlama) throws Exception {
		Db db = null;
		list_semak_hta.clear();
		// EDITED BY HIDAYAH 10/05/2010
		// String sql =
		// "SELECT MS.ID_PERMOHONAN AS PX,P1.ID_PERMOHONAN,H.ID_PERMOHONANSIMATI,H.ID_HTA,H.FLAG_PA,H.FLAG_PT,"
		// +
		// "H.FLAG_SELESAI FROM TBLPPKHTA H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,"
		// +
		// "TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1 WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_SIMATI = MS.ID_SIMATI "
		// +
		// "AND MS.ID_PERMOHONANSIMATI >= H.ID_PERMOHONANSIMATI AND MS.ID_PERMOHONAN = '"+idpermohonanlama+"' "
		// +
		// "AND H.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+) "
		// +
		// "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+) AND P1.NO_SUBJAKET <= P.NO_SUBJAKET "
		// +
		// "AND S.ID_SIMATI = MS.ID_SIMATI";

		String sql = "SELECT D.ID_PERMOHONAN,E.ID_PERMOHONANSIMATI,F.ID_HTA,F.FLAG_PT,F.FLAG_PA,F.FLAG_SELESAI "
				+ " FROM TBLPPKPERMOHONAN D, TBLPPKPERMOHONANSIMATI E,TBLPPKHTA F"
				+ " WHERE D.ID_PERMOHONAN = E.ID_PERMOHONAN "
				+ " AND NVL(D.NO_SUBJAKET,0) <= ( SELECT  NVL(A.NO_SUBJAKET,0) "
				+ " FROM TBLPPKPERMOHONAN A WHERE A.ID_PERMOHONAN ='"
				+ idpermohonanlama
				+ "')"
				+ " AND E.ID_SIMATI = (SELECT C.ID_SIMATI FROM TBLPPKPERMOHONAN B, TBLPPKPERMOHONANSIMATI C"
				+ " WHERE B.ID_PERMOHONAN = C.ID_PERMOHONAN AND B.ID_PERMOHONAN = '"
				+ idpermohonanlama + "' ) " + " AND E.ID_SIMATI = F.ID_SIMATI";
		sql += " ";
		sql += " ORDER BY F.ID_HTA DESC";
		myLogger.info("SEK 17 HTA TERDAHULU :" + sql.toUpperCase());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("id_permohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("id_permohonansimati", rs
						.getString("ID_PERMOHONANSIMATI") == null ? "" : rs
						.getString("ID_PERMOHONANSIMATI"));
				h.put("id_hta", rs.getString("ID_HTA") == null ? "" : rs
						.getString("ID_HTA"));
				h.put("flag_pt", rs.getString("FLAG_PT") == null ? "" : rs
						.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA") == null ? "" : rs
						.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA") == null ? "" : rs
						.getString("FLAG_PA"));
				// h.put("PX", rs.getString("PX")==null?"":rs.getString("PX"));

				list_semak_hta.addElement(h);
			}
			return list_semak_hta;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_semak_ha = new Vector();

	public Vector setTujuanSemak_ha(String idpermohonanlama) throws Exception {
		Db db = null;
		list_semak_ha.clear();
		// EDITED BY HIDAYAH 10/05/2010
		// String sql =
		// "SELECT MS.ID_PERMOHONAN AS PX,P1.ID_PERMOHONAN,H.ID_PERMOHONANSIMATI,H.ID_HA,H.FLAG_PA,H.FLAG_PT,"
		// +
		// "H.FLAG_SELESAI FROM TBLPPKHA H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,"
		// +
		// "TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1 WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_SIMATI = MS.ID_SIMATI "
		// +
		// "AND MS.ID_PERMOHONANSIMATI >= H.ID_PERMOHONANSIMATI AND MS.ID_PERMOHONAN = '"+idpermohonanlama+"' "
		// +
		// "AND H.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+) "
		// +
		// "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+) AND P1.NO_SUBJAKET <= P.NO_SUBJAKET "
		// +
		// "AND S.ID_SIMATI = MS.ID_SIMATI";

		String sql = "SELECT D.ID_PERMOHONAN,E.ID_PERMOHONANSIMATI,F.ID_HA,F.FLAG_PT,F.FLAG_PA,F.FLAG_SELESAI FROM TBLPPKPERMOHONAN D, TBLPPKPERMOHONANSIMATI E,TBLPPKHA F"
				+ " WHERE D.ID_PERMOHONAN = E.ID_PERMOHONAN AND NVL(D.NO_SUBJAKET,0) <= ( SELECT  NVL(A.NO_SUBJAKET,0) FROM TBLPPKPERMOHONAN A WHERE A.ID_PERMOHONAN ='"
				+ idpermohonanlama
				+ "')"
				+ " AND E.ID_SIMATI = (SELECT C.ID_SIMATI FROM TBLPPKPERMOHONAN B, TBLPPKPERMOHONANSIMATI C"
				+ " WHERE B.ID_PERMOHONAN = C.ID_PERMOHONAN AND B.ID_PERMOHONAN = '"
				+ idpermohonanlama + "' ) " + " AND E.ID_SIMATI = F.ID_SIMATI";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql += " ";
			sql += " ORDER BY F.ID_HA DESC";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("id_permohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("id_ha", rs.getString("ID_HA") == null ? "" : rs.getString("ID_HA"));
				h.put("flag_pt", rs.getString("FLAG_PT") == null ? "" : rs.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA") == null ? "" : rs.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA") == null ? "" : rs.getString("FLAG_PA"));
				// h.put("PX", rs.getString("PX")==null?"":rs.getString("PX"));

				list_semak_ha.addElement(h);
			}
			return list_semak_ha;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}
//IL start
	
	Vector list_live_state = new Vector();
	
	public boolean checkLiveState(int idnegeri) throws Exception {
		Db db = null;
		list_live_state.clear();
		
		String sql = "SELECT ID_PPKPARAM FROM TBLPPKPARAM PARAM WHERE PARAM.ID_NEGERI = "+idnegeri;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			boolean flagExist = false;
			while (rs.next()) {
				flagExist = true;
			}
				
			return flagExist;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

//IL end
	
	
public boolean setIsPermohonanOnlineExist (String id_simati) throws Exception {
		
		boolean isExistPermohonan = false;
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			sql = " SELECT   a.id_simati, d.id_fail, d.no_fail, a.nama_simati, " +
					" a.no_kp_baru as NO_KP_BARU_SIMATI, " +
					"  a.no_kp_lama as NO_KP_LAMA_SIMATI, a.tarikh_mati, a.no_sijil_mati, " +
					"  a.tempat_mati, a.sebab_mati, e.id_pemohon, e.nama_pemohon, " +
					" e.no_kp_baru as NO_KP_BARU_PEMOHON , b.id_permohonansimati, " +
					" c.id_permohonan, d.tarikh_daftar_fail, a.id_negeri " +
					" FROM tblppksimati a, " +
					" tblppkpermohonansimati b, " +
					" tblppkpermohonan c, " +
					" tblpfdfail d, " +
					" tblppkpemohon e " +
					" WHERE a.id_simati = b.id_simati " +
					" AND b.id_permohonan = c.id_permohonan " +
					" AND c.id_pemohon = e.id_pemohon " +
					" AND c.id_fail = d.id_fail " +
					" and a.ID_SIMATI = "+id_simati +" and d.no_fail is null";


			myLogger.info("SQL setIsPermohonanOnlineExist :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			
				isExistPermohonan = true;
			
			}
			return isExistPermohonan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
}
