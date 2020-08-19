package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.model.entities.Tblrujdaerah;

//Updated on 17/8/2010
public class FrmPrmhnnSek8SecaraOnlineData {
	private static Logger myLogger = Logger
			.getLogger(FrmPrmhnnSek8SecaraOnlineData.class);
	Vector list = null;
	Vector listARB = null;
	Vector listBaitul = null;

	public static void main(String args[]) throws Exception {
		// Utk testing Run As Java Application
		Hashtable hWhere = new Hashtable();
		hWhere.put("P.NO_KP_BARU", "7612312");
		hWhere.put("P.NO_KP_LAMA", "AB12");
		// hWhere.put("P.NO_KP_LAIN","POCO POCO");
		String x = getSQLWhere("SELECT * FROM XX WHERE x=1 ", hWhere);
	}

	public static String getSQLWhere(String sql, Hashtable h) {
		String fieldname = "", value = "", output = "";
		int x = 0;
		for (Enumeration e = h.keys(); e.hasMoreElements();) {
			fieldname = (String) e.nextElement();
			value = (String) h.get(fieldname);
			if (value != null || value != "") {
				x++;
				output = output + " UPPER(" + fieldname + ") LIKE '%"
						+ value.toUpperCase() + "%'"
						+ (x < h.size() ? " OR " : "");
			}
		}
		if (output != "") {
			output = sql + " AND ( " + output + " )";
		} else {
			output = sql;
		}
		return output;
	}

	Vector list_getListEmptyField_simati = null;

	public void checkFieldEmpty_simati(String idpermohonan) throws Exception {
		Db db = null;

		try {
			db = new Db();
			String sql = "";
			list_getListEmptyField_simati = new Vector();

			sql = "SELECT C.ID_BUKTIMATI,C.TARIKH_MATI,C.SEBAB_MATI "
					+ "	FROM TBLPPKPERMOHONAN A,"
					+ "	TBLPPKPERMOHONANSIMATI B," + "	TBLPPKSIMATI C,"
					+ "	TBLPPKPEMOHON D "
					+ "	WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN"
					+ "	AND B.ID_SIMATI = C.ID_SIMATI"
					+ "	AND A.ID_PEMOHON = D.ID_PEMOHON"
					+ "	AND A.ID_PERMOHONAN = '" + idpermohonan + "'";

			myLogger.info("list_getListEmptyField_simati---" + sql);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_BUKTIMATI", rs.getString("ID_BUKTIMATI") == null ? ""
						: rs.getString("ID_BUKTIMATI"));
				h.put("TARIKH_MATI", rs.getString("TARIKH_MATI") == null ? ""
						: rs.getString("TARIKH_MATI"));
				h.put("SEBAB_MATI", rs.getString("SEBAB_MATI") == null ? ""
						: rs.getString("SEBAB_MATI"));
				
				list_getListEmptyField_simati.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListEmptyField_simati() {
		return list_getListEmptyField_simati;
	}

	Vector list_getListEmptyField_pemohon = null;

	public void checkFieldEmpty_pemohon(String idpermohonan) throws Exception {
		Db db = null;

		try {
			db = new Db();
			String sql = "";
			list_getListEmptyField_pemohon = new Vector();

			/*
			 * sql =
			 * "SELECT C.ID_BUKTIMATI,C.TARIKH_MATI,C.SEBAB_MATI,D.ALAMAT_1,D.ALAMAT1_SURAT,D.POSKOD,"
			 * +
			 * "	D.POSKOD_SURAT,D.ID_NEGERI,D.ID_NEGERISURAT,nvl(E.ID_HTA,'0') AS ID_HTA"
			 * +
			 * 
			 * 
			 * "	FROM TBLPPKPERMOHONAN A,"+ "	TBLPPKPERMOHONANSIMATI B,"+
			 * "	TBLPPKSIMATI C,"+ "	TBLPPKPEMOHON D,"+ "	TBLPPKHTA E"+
			 * 
			 * "	WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN"+
			 * "	AND B.ID_SIMATI = C.ID_SIMATI"+
			 * "	AND A.ID_PEMOHON = D.ID_PEMOHON"+
			 * "	AND C.ID_SIMATI = E.ID_SIMATI(+)"+
			 * "	AND A.ID_PERMOHONAN = '"+idpermohonan+"'";
			 */

			sql = "SELECT D.ALAMAT_1,D.ALAMAT1_SURAT,D.POSKOD,"
					+ "	D.POSKOD_SURAT,D.ID_NEGERI,D.ID_NEGERISURAT "
					+ "	FROM TBLPPKPERMOHONAN A,"
					+ "	TBLPPKPERMOHONANSIMATI B," + "	TBLPPKSIMATI C,"
					+ "	TBLPPKPEMOHON D "
					+ "	WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN"
					+ "	AND B.ID_SIMATI = C.ID_SIMATI"
					+ "	AND A.ID_PEMOHON = D.ID_PEMOHON"
					+ "	AND A.ID_PERMOHONAN = '" + idpermohonan + "'";

			myLogger.info("list_getListEmptyField_pemohon---" + sql);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				/*
				 * h.put("ID_BUKTIMATI",
				 * rs.getString("ID_BUKTIMATI")==null?"":rs
				 * .getString("ID_BUKTIMATI")); h.put("TARIKH_MATI",
				 * rs.getString
				 * ("TARIKH_MATI")==null?"":rs.getString("TARIKH_MATI"));
				 * h.put("SEBAB_MATI",
				 * rs.getString("SEBAB_MATI")==null?"":rs.getString
				 * ("SEBAB_MATI"));
				 */h.put("ALAMAT_1", rs.getString("ALAMAT_1") == null ? "" : rs
						.getString("ALAMAT_1"));
				h.put("ALAMAT1_SURAT",
						rs.getString("ALAMAT1_SURAT") == null ? "" : rs
								.getString("ALAMAT1_SURAT"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("POSKOD_SURAT", rs.getString("POSKOD_SURAT") == null ? ""
						: rs.getString("POSKOD_SURAT"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ID_NEGERISURAT",
						rs.getString("ID_NEGERISURAT") == null ? "" : rs
								.getString("ID_NEGERISURAT"));
				/*
				 * h.put("ID_HTA",rs.getString("ID_HTA")==null?"":rs.getString("ID_HTA"
				 * ));
				 */
				list_getListEmptyField_pemohon.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListEmptyField_pemohon() {
		return list_getListEmptyField_pemohon;
	}

	Vector list_getListEmptyField_hta = null;

	public void checkFieldEmpty_hta(String idpermohonan) throws Exception {
		Db db = null;

		try {
			db = new Db();
			String sql = "";
			list_getListEmptyField_hta = new Vector();

			sql = "SELECT D.ALAMAT_1,D.ALAMAT1_SURAT,D.POSKOD,"
					+ "	D.POSKOD_SURAT,D.ID_NEGERI,D.ID_NEGERISURAT,E.ID_HTA "
					+ "	FROM TBLPPKPERMOHONAN A,"
					+ "	TBLPPKPERMOHONANSIMATI B,"
					+ "	TBLPPKSIMATI C,"
					+ "	TBLPPKPEMOHON D,TBLPPKHTA E "
					+ "	WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN"
					+ "	AND B.ID_SIMATI = C.ID_SIMATI"
					+ "	AND A.ID_PEMOHON = D.ID_PEMOHON  AND B.ID_PERMOHONANSIMATI = E.ID_PERMOHONANSIMATI "
					+ "	AND A.ID_PERMOHONAN = '" + idpermohonan + "'";

			myLogger.info("list_getListEmptyField_hta---" + sql);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_HTA", rs.getString("ID_HTA") == null ? "" : rs
						.getString("ID_HTA"));
				list_getListEmptyField_hta.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListEmptyField_hta() {
		return list_getListEmptyField_hta;
	}

	public static int checkPemohon(String kpbaru, String kplama, String kplain)
			throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			String kpBaru = kpbaru.trim();
			String kpLama = kplama.trim();
			String kpLain = kplain.trim();
			String sql = "Select count(P.ID_PEMOHON) as cntId from TBLPPKPEMOHON P, TBLPPKPERMOHONAN PP where "
					+ "P.ID_PEMOHON = PP.ID_PEMOHON AND PP.SEKSYEN = 8";
			// Azam Add
			Hashtable hWhere = new Hashtable();
			hWhere.put("P.NO_KP_BARU", kpBaru);
			hWhere.put("P.NO_KP_LAMA", kpLama);
			hWhere.put("P.NO_KP_LAIN", kpLain);

			sql = getSQLWhere(sql, hWhere);

			// if (kpBaru != "") {
			// if (!kpBaru.trim().equals("")) {
			// sql = sql + " UPPER(P.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase()
			// + "%'";
			// }
			// }
			// if (kpLama != "") {
			// if (!kpLama.trim().equals("")) {
			// if (sqlwhere != "") {
			// sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAMA) LIKE '%" +
			// kpLama.toUpperCase() + "%'";
			// } else {
			// sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAMA) LIKE '%" +
			// kpLama.toUpperCase() + "%'";
			// }
			// }
			// }
			// if (kpLain != "") {
			// if (!kpLain.trim().equals("")) {
			// if (sqlwhere != "") {
			// sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAIN) LIKE '%" +
			// kpLain.toUpperCase() + "%'";
			// } else {
			// sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAIN) LIKE '%" +
			// kpLain.toUpperCase() + "%'";
			// }
			// }
			// }
			//			
			// if (sqlwhere != "") {
			// sql = sql + " AND ( "+sqlwhere+" )";
			// }

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			if (rs.next()) {
				return rs.getString("cntId") == null ? 0 : Integer.parseInt(rs
						.getString("cntId"));
			} else
				return 0;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int checkSimati(String kpbarusimati, String kplamasimati,
			String kplainsimati, String noPermohonansimati, String userid)
			throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector resultSimati = new Vector();
		try {
			db = new Db();
			String kpBaru = kpbarusimati.trim();
			String kpLama = kplamasimati.trim();
			String kpLain = kplainsimati.trim();
			String noPermohonan = noPermohonansimati.trim();
			String sql = "Select count(p.ID_SIMATI) as cntId from TBLPPKSIMATI P, TBLPPKPERMOHONAN PP, TBLPPKPERMOHONANSIMATI M where "
					+ "PP.ID_PERMOHONAN = M.ID_PERMOHONAN AND M.ID_SIMATI = P.ID_SIMATI AND PP.SEKSYEN = 8 and PP.ID_MASUK = '"
					+ userid + "'";

			String sqlwhere = "";

			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(P.NO_KP_BARU) LIKE '%"
							+ kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					}

				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					}
				}
			}
			if (noPermohonan != "") {
				if (!noPermohonan.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere
								+ " OR UPPER(PP.NO_PERMOHONAN_ONLINE) = '"
								+ noPermohonan.toUpperCase() + "'";
					} else {
						sqlwhere = sqlwhere
								+ " UPPER(PP.NO_PERMOHONAN_ONLINE) = '"
								+ noPermohonan.toUpperCase() + "'";
					}
				}
			}

			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere + " )";
			}

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			if (rs.next()) {
				return rs.getString("cntId") == null ? 0 : Integer.parseInt(rs
						.getString("cntId"));
			} else
				return 0;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int checkSimatiPermohonan(String kpbarusimati,
			String kplamasimati, String kplainsimati,
			String noPermohonansimati, int userid) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector resultSimati = new Vector();
		try {
			db = new Db();
			String kpBaru = kpbarusimati.trim();
			String kpLama = kplamasimati.trim();
			String kpLain = kplainsimati.trim();
			String noPermohonan = noPermohonansimati.trim();
			String sql = "Select count(p.ID_SIMATI) as cntId from TBLPPKSIMATI P, TBLPPKPERMOHONAN PP, TBLPPKPERMOHONANSIMATI M where "
					+ "PP.ID_PERMOHONAN = M.ID_PERMOHONAN AND M.ID_SIMATI = P.ID_SIMATI AND PP.SEKSYEN = 8 ";

			String sqlwhere = "";

			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(P.NO_KP_BARU) LIKE '%"
							+ kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					}

				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					}
				}
			}
			if (noPermohonan != "") {
				if (!noPermohonan.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere
								+ " OR UPPER(PP.NO_PERMOHONAN_ONLINE) = '"
								+ noPermohonan.toUpperCase() + "'";
					} else {
						sqlwhere = sqlwhere
								+ " UPPER(PP.NO_PERMOHONAN_ONLINE) = '"
								+ noPermohonan.toUpperCase() + "'";
					}
				}
			}

			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere + " )";
			}

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			// while (rs.next()){
			// h = new Hashtable();
			// h.put("idCnt",
			// rs.getString("cntId")==null?"":rs.getString("cntId"));
			// resultSimati.addElement(h);
			// }
			if (rs.next()) {
				return rs.getString("cntId") == null ? 0 : Integer.parseInt(rs
						.getString("cntId"));
			} else
				return 0;

			// return resultSimati;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void insertOnlinePermohonan(Hashtable data) throws Exception {
		// Azam add Transaction on 15.03.2010
		Connection conn = null;
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		int getYear = calendar.get(java.util.Calendar.YEAR);
		try {
			System.out.println("here 0");
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			long idPemohon = DB.getNextID(db, "TBLPPKPEMOHON_SEQ");
			long idPermohonan = DB.getNextID(db, "TBLPPKPERMOHONAN_SEQ");
			long idSimati = DB.getNextID(db, "TBLPPKSIMATI_SEQ");
			long idPermohonanSimati = DB.getNextID(db,
					"TBLPPKPERMOHONANSIMATI_SEQ");
			long idob = DB.getNextID(db, "TBLPPKOB_SEQ");

			String noFail = String.format("%06d", File.getSeqNo(db, 2, 01, 18,
					0, 0, false, false, 0, 0));

			String X = "JKPTG/PK/01/" + getYear + "/" + noFail;
			String userid = (String) data.get("userid");
			String idnegeri = (String) data.get("useridNegeri");
			String sorWaris = (String) data.get("SorWaris");
			String NoKPBaru = (String) data.get("noKPBaru");
			String NoKPLama = ((String) data.get("noKPLama")).toUpperCase();
			String JenisKPLain = (String) data.get("jenisKPLain");
			String NoKpLain = (String) data.get("noKpLain");
			String NamaPemohon = ((String) data.get("namaPemohon"))
					.toUpperCase();
			String socWaris = (String) data.get("SocWaris");
			String socOB = (String) data.get("SocOB");
			String NoKPBaruSimati = (String) data.get("noKPBaruSimati");
			String NoKPLamaSimati = ((String) data.get("noKPLamaSimati"))
					.toUpperCase();
			String JenisKPLainSimati = (String) data.get("jenisKPLainSimati");
			String NoKpLainSimati = (String) data.get("noKpLainSimati");
			String namaSimati = ((String) data.get("namaSimati")).toUpperCase();
			// int getAdd = (Integer)data.get("getAdd");
			int checkNewPemohon = (Integer) data.get("checkNewPemohon");
			String nokpbaru3 = (String) data.get("nokpbaru3");
			String idExistPemohon = (String) data.get("idExistPemohon");
			String icYEAR = "";
			String icMONTH = "";
			String icDAY = "";
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String id_negeri = (String) data.get("id_negeri");
			String noTelefon = (String) data.get("noTelefon");
			String idARB = (String) data.get("idARB");

			Statement stmtA = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_fail", idFail);
			r.add("id_seksyen", 2);
			r.add("id_urusan", 382);
			r.add("tarikh_daftar_fail", r.unquote("sysdate"));
			r.add("id_negeri", Integer.parseInt(idnegeri));
			r.add("id_suburusan", 59);
			r.add("flag_fail", 1);
			r.add("id_status", 7); // active
			r.add("id_masuk", userid);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", userid);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpfdfail");
			stmtA.executeUpdate(sql);

			Statement stmt = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.add("ID_PERMOHONAN", idPermohonan);
			r2.add("ID_FAIL", idFail);
			r2.add("FLAG_JENIS_PERMOHONAN", 0);
			r2.add("NO_PERMOHONAN_ONLINE", X);
			r2.add("FLAG_HUBUNGAN_PEMOHON", sorWaris);
			if (sorWaris.equals("1")) {
				r2.add("ID_HUBUNGANPEMOHON", socWaris);
			} else if (sorWaris.equals("2")) {
				r2.add("ID_HUBUNGANPEMOHON", socOB);
			}
			r2.add("ID_STATUS", 150);
			r2.add("SEKSYEN", 8);
			r2.add("TARIKH_MOHON", r.unquote("sysdate"));
			r2.add("ID_MASUK", userid);
			r2.add("TARIKH_MASUK", r.unquote("sysdate"));
			r2.add("ID_KEMASKINI", userid);
			r2.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r2.add("FLAG_PERMOHONAN", "1");
			r2.add("TARIKH_MOHON_ONLINE", r.unquote("sysdate"));
			if (checkNewPemohon == 0) {
				r2.add("ID_PEMOHON", idPemohon);
			} else {
				r2.add("ID_PEMOHON", Integer.parseInt(idExistPemohon));
			}
			sql4 = r2.getSQLInsert("TBLPPKPERMOHONAN");
			stmt.executeUpdate(sql4);

			Statement stmtT = db.getStatement();
			SQLRenderer r3 = new SQLRenderer();
			r3.add("ID_PERMOHONANSIMATI", idPermohonanSimati);
			r3.add("ID_PERMOHONAN", idPermohonan);
			r3.add("ID_SIMATI", idSimati);
			r3.add("ID_MASUK", userid);
			r3.add("TARIKH_MASUK", r.unquote("sysdate"));
			r3.add("ID_KEMASKINI", userid);
			r3.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql3 = r3.getSQLInsert("TBLPPKPERMOHONANSIMATI");
			stmtT.executeUpdate(sql3);

			Statement stmtV = db.getStatement();
			SQLRenderer r6 = new SQLRenderer();
			r6.add("ID_SIMATI", idSimati);
			r6.add("NO_KP_BARU", NoKPBaruSimati);
			r6.add("no_kp_lama", NoKPLamaSimati);
			r6.add("jenis_kp", JenisKPLainSimati);
			r6.add("no_kp_lain", NoKpLainSimati);
			r6.add("nama_simati", namaSimati);
			r6.add("id_masuk", userid);
			r6.add("TARIKH_MASUK", r.unquote("sysdate"));
			r6.add("ID_KEMASKINI", userid);
			r6.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			String sql6 = r6.getSQLInsert("TBLPPKSIMATI");
			stmtV.executeUpdate(sql6);

			System.out.println("insert simati---" + sql6);

			if (checkNewPemohon == 0) {

				Statement stmtB = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.add("id_pemohon", idPemohon);
				r1.add("no_kp_baru", NoKPBaru);
				r1.add("no_kp_lama", NoKPLama);
				r1.add("jenis_kp", JenisKPLain);
				r1.add("no_kp_lain", NoKpLain);
				if (sorWaris.equals("1")) {
					r1.add("nama_pemohon", NamaPemohon);
				} else if (sorWaris.equals("2")) {
					if (socOB.equals("6")) {
						r1.add("nama_pemohon", NamaPemohon);
						r1.add("alamat_1", alamat1);
						r1.add("alamat_2", alamat2);
						r1.add("alamat_3", alamat3);
						r1.add("poskod", poskod);
						r1.add("id_negeri", id_negeri);
						r1.add("no_tel", noTelefon);
						r1.add("alamat1_surat", alamat1);
						r1.add("alamat2_surat", alamat2);
						r1.add("alamat3_surat", alamat3);
						r1.add("poskod_surat", poskod);
						r1.add("id_negerisurat", id_negeri);
						r1.add("no_tel_surat", noTelefon);
						r1.add("id_arb", idARB);

					} else if (socOB.equals("8")) {
						r1.add("nama_pemohon", NamaPemohon);
						r1.add("alamat_1", alamat1);
						r1.add("alamat_2", alamat2);
						r1.add("alamat_3", alamat3);
						r1.add("poskod", poskod);
						r1.add("id_negeri", id_negeri);
						r1.add("no_tel", noTelefon);
						r1.add("alamat1_surat", alamat1);
						r1.add("alamat2_surat", alamat2);
						r1.add("alamat3_surat", alamat3);
						r1.add("poskod_surat", poskod);
						r1.add("id_negerisurat", id_negeri);
						r1.add("no_tel_surat", noTelefon);
						// r1.add("id_arb",idARB);
					}

				}

				if (sorWaris.equals("1")) {
					r1.add("ID_TARAFKPTG", 1);
					r1.add("ID_SAUDARA", socWaris);
				} else if (sorWaris.equals("2")) {
					if (socOB.equals("6")) {
						r1.add("ID_TARAFKPTG", 6);
						r1.add("ID_SAUDARA", 31);
					} else if (socOB.equals("8")) {
						r1.add("ID_TARAFKPTG", 8);
						r1.add("ID_SAUDARA", 0);
					} else {
						r1.add("ID_TARAFKPTG", socOB);
						r1.add("ID_SAUDARA", 0);
					}
				}
				if (!NoKPBaru.equals("")) {
					icYEAR = NoKPBaru.substring(0, 2);
					icMONTH = NoKPBaru.substring(2, 4);
					icDAY = NoKPBaru.substring(4, 6);

					String yearx = "19" + icYEAR;

					int y = Integer.parseInt(yearx);
					int m = Integer.parseInt(icMONTH) - 1;
					int d = Integer.parseInt(icDAY);

					Calendar cal = new GregorianCalendar(y, m, d);
					Calendar now = new GregorianCalendar();

					int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
					if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
							|| (cal.get(Calendar.MONTH) == now
									.get(Calendar.MONTH) && cal
									.get(Calendar.DAY_OF_MONTH) > now
									.get(Calendar.DAY_OF_MONTH))) {
						res--;
					}
					r1.add("umur", res);
				}

				if (!nokpbaru3.equals("")) {
					String lastDigit = nokpbaru3.substring(3, 4);
					int digitValue = Integer.parseInt(lastDigit);
					String gender = "";
					if (digitValue == 0 || digitValue == 2 || digitValue == 4
							|| digitValue == 6 || digitValue == 8) {
						gender = "2"; // female
					} else {
						gender = "1"; // male
					}
					r1.add("jantina", gender);
				} else {
					r1.add("jantina", 0);
				}
				// Azam remove
				// r1.add("ID_PERMOHONAN",idPermohonan);
				r1.add("ID_MASUK", userid);
				r1.add("TARIKH_MASUK", r.unquote("sysdate"));
				r1.add("ID_KEMASKINI", userid);
				r1.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql1 = r1.getSQLInsert("TBLPPKPEMOHON");
				System.out.println("insert tblppkpemohon--" + sql1);
				stmtB.executeUpdate(sql1);

				if (sorWaris.equals("1")) {

					Statement stmtG = db.getStatement();
					String sql8 = "Update tblppkpemohon "
							+ "set alamat_1 = (select distinct nvl(alamat1,'') from users_online where no_kp_baru = '"
							+ NoKPBaru
							+ "'), "
							+ "alamat_2 = (select distinct nvl(alamat2,'') from users_online where no_kp_baru = '"
							+ NoKPBaru
							+ "'),"
							+ "alamat_3 =(select distinct nvl(alamat3,'') from users_online where no_kp_baru = '"
							+ NoKPBaru
							+ "'), "
							+ "poskod = (select distinct nvl(poskod,'') from users_online where no_kp_baru = '"
							+ NoKPBaru
							+ "'), "
							+ "no_hp = (select distinct nvl(no_hp,'') from users_online where no_kp_baru = '"
							+ NoKPBaru
							+ "'), "
							+ "emel = (select distinct nvl(emel,'') from users_online where no_kp_baru = '"
							+ NoKPBaru
							+ "'), "
							+ "no_tel = (select distinct nvl(no_tel,'') from users_online where no_kp_baru = '"
							+ NoKPBaru
							+ "'), "
							+ "no_fax = (select distinct nvl(no_fax,'') from users_online where no_kp_baru = '"
							+ NoKPBaru
							+ "'), "
							+ "id_negeri = "
							+ idnegeri
							+ " " + "where id_pemohon = " + idPemohon + "";
					stmtG.executeUpdate(sql8);
				}

				String idtaraf = "";
				if (sorWaris.equals("1")) {
					idtaraf = socWaris;
				} else if (sorWaris.equals("2")) {
					idtaraf = socOB;
				}

				System.out.println("here test :" + sorWaris);
				System.out.println("kp baru :" + NoKPBaru);

				if (sorWaris.equals("1")) {
					Statement stmtC = db.getStatement();
					String sql7 = "insert into tblppkob (" + "ID_OB,"
							+ "ID_SIMATI," + "NAMA_OB," + "NO_KP_BARU,"
							+ "NO_KP_LAMA," + "JENIS_KP," + "NO_KP_LAIN,"
							+ "JANTINA," + "ID_TARAFKPTG," + "ID_NEGERI,"
							+ "ID_SAUDARA," + "STATUS_OB," + "LAPIS,"
							+ "ID_PEMOHON," + "ID_PERMOHONANSIMATI,"
							+ "ID_MASUK,"
							+ "TARIKH_MASUK,UMUR,STATUS_HIDUP,ID_KEMASKINI,"
							+ "TARIKH_KEMASKINI) values ("
							+ idob
							+ ","
							+ ""
							+ idSimati
							+ ","
							+ "'"
							+ NamaPemohon
							+ "','"
							+ NoKPBaru
							+ "','"
							+ NoKPLama
							+ "','"
							+ JenisKPLain
							+ "','"
							+ NoKpLain
							+ "',(select jantina from users_online where no_kp_baru = '"
							+ NoKPBaru
							+ "'),"
							+ "'"
							+ sorWaris
							+ "',"
							+ idnegeri
							+ ",'"
							+ idtaraf
							+ "',0,1,"
							+ idPemohon
							+ ","
							+ ""
							+ idPermohonanSimati
							+ ","
							+ userid
							+ ",sysdate,(select umur from tblppkpemohon where id_pemohon = "
							+ idPemohon + "),0," + userid + ",sysdate)";
					stmtC.executeUpdate(sql7);
				} else if (sorWaris.equals("2")) {

					if (socOB.equals("6")) {
						Statement stmtC = db.getStatement();
						String sql7 = "insert into tblppkob (" + "ID_OB,"
								+ "ID_SIMATI," + "NAMA_OB," + "ID_TARAFKPTG,"
								+ "ALAMAT_1," + "ALAMAT_2," + "ALAMAT_3,"
								+ "POSKOD," + "ID_NEGERI," + "NO_TEL,"
								+ "ALAMAT1_SURAT," + "ALAMAT2_SURAT,"
								+ "ALAMAT3_SURAT," + "POSKOD_SURAT,"
								+ "ID_NEGERISURAT," + "NO_TEL_SURAT,"
								+ "ID_ARB," + "ID_SAUDARA," + "LAPIS,"
								+ "ID_PEMOHON," + "ID_PERMOHONANSIMATI,"
								+ "ID_MASUK," + "TARIKH_MASUK,ID_KEMASKINI,"
								+ "TARIKH_KEMASKINI) values ("
								+ idob
								+ ","
								+ ""
								+ idSimati
								+ ","
								+ "'"
								+ NamaPemohon
								+ "',"
								+ "'"
								+ socOB
								+ "','"
								+ alamat1
								+ "','"
								+ alamat2
								+ "','"
								+ alamat3
								+ "','"
								+ poskod
								+ "','"
								+ id_negeri
								+ "','"
								+ noTelefon
								+ "','"
								+ alamat1
								+ "','"
								+ alamat2
								+ "','"
								+ alamat3
								+ "','"
								+ poskod
								+ "','"
								+ id_negeri
								+ "','"
								+ noTelefon
								+ "','"
								+ idARB
								+ "',31,1,'"
								+ idPemohon
								+ "','"
								+ ""
								+ idPermohonanSimati
								+ "','"
								+ userid
								+ "',sysdate,'" + userid + "',sysdate)";
						System.out.println("insert dlm ob arb--" + sql7);
						stmtC.executeUpdate(sql7);

					} else if (socOB.equals("8")) {
						Statement stmtC = db.getStatement();
						String sql7 = "insert into tblppkob (" + "ID_OB,"
								+ "ID_SIMATI," + "NAMA_OB," + "ID_TARAFKPTG,"
								+ "ALAMAT_1," + "ALAMAT_2," + "ALAMAT_3,"
								+ "POSKOD," + "ID_NEGERI," + "NO_TEL,"
								+ "ALAMAT1_SURAT," + "ALAMAT2_SURAT,"
								+ "ALAMAT3_SURAT," + "POSKOD_SURAT,"
								+ "ID_NEGERISURAT," + "NO_TEL_SURAT,"
								+ "LAPIS," + "ID_PEMOHON,"
								+ "ID_PERMOHONANSIMATI," + "ID_MASUK,"
								+ "TARIKH_MASUK,ID_KEMASKINI,"
								+ "TARIKH_KEMASKINI) values ("
								+ idob
								+ ","
								+ ""
								+ idSimati
								+ ","
								+ "'"
								+ NamaPemohon
								+ "',"
								+ "'"
								+ socOB
								+ "','"
								+ alamat1
								+ "','"
								+ alamat2
								+ "','"
								+ alamat3
								+ "','"
								+ poskod
								+ "','"
								+ id_negeri
								+ "','"
								+ noTelefon
								+ "','"
								+ alamat1
								+ "','"
								+ alamat2
								+ "','"
								+ alamat3
								+ "','"
								+ poskod
								+ "','"
								+ id_negeri
								+ "','"
								+ noTelefon
								+ "',1,'"
								+ idPemohon
								+ "','"
								+ ""
								+ idPermohonanSimati
								+ "','"
								+ userid
								+ "',sysdate,'"
								+ userid
								+ "',sysdate)";
						stmtC.executeUpdate(sql7);
					} else {
						Statement stmtC = db.getStatement();
						String sql7 = "insert into tblppkob ("
								+ "ID_OB,"
								+ "ID_SIMATI,"
								+ "NAMA_OB,"
								+ "NO_KP_BARU,"
								+ "NO_KP_LAMA,"
								+ "JENIS_KP,"
								+ "NO_KP_LAIN,"
								+ "JANTINA,"
								+ "ID_TARAFKPTG,"
								+ "ID_NEGERI,"
								+ "ID_SAUDARA,"
								+ "STATUS_OB,"
								+ "LAPIS,"
								+ "ID_PEMOHON,"
								+ "ID_PERMOHONANSIMATI,"
								+ "ID_MASUK,"
								+ "TARIKH_MASUK,UMUR,STATUS_HIDUP,ID_KEMASKINI,"
								+ "TARIKH_KEMASKINI) values ("
								+ idob
								+ ","
								+ ""
								+ idSimati
								+ ","
								+ "'"
								+ NamaPemohon
								+ "','"
								+ NoKPBaru
								+ "','"
								+ NoKPLama
								+ "','"
								+ JenisKPLain
								+ "','"
								+ NoKpLain
								+ "',(select jantina from users_online where no_kp_baru = '"
								+ NoKPBaru
								+ "'),"
								+ "'"
								+ sorWaris
								+ "',"
								+ idnegeri
								+ ",'"
								+ idtaraf
								+ "',0,1,"
								+ idPemohon
								+ ","
								+ ""
								+ idPermohonanSimati
								+ ","
								+ userid
								+ ",sysdate,(select umur from tblppkpemohon where id_pemohon = "
								+ idPemohon + "),0," + userid + ",sysdate)";
						stmtC.executeUpdate(sql7);
					}

				}

			}
			System.out.println("here 2");
			Statement stmtF = db.getStatement();
			SQLRenderer r5 = new SQLRenderer();
			r5.add("ID_SUBURUSANSTATUSFAIL", DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			r5.add("ID_PERMOHONAN", idPermohonan);
			r5.add("ID_SUBURUSANSTATUS", 436); // 436 status utk permohonan
												// online
			r5.add("AKTIF", 1);
			r5.add("ID_FAIL", idFail);
			r5.add("ID_MASUK", userid);
			r5.add("TARIKH_MASUK", r.unquote("sysdate"));
			r5.add("ID_KEMASKINI", userid);
			r5.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql2 = r5.getSQLInsert("tblrujsuburusanstatusfail");
			stmtF.executeUpdate(sql2);
			System.out.println("here 3");
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

	private static Vector detailPemohon = new Vector();

	public void semakDetailPemohon(String kpbaru, String kplama, String kplain)
			throws Exception {
		Db db = null;
		detailPemohon.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			String kpBaru = kpbaru.trim();
			String kpLama = kplama.trim();
			String kpLain = kplain.trim();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("P.NO_KP_BARU");
			r.add("P.NO_KP_LAMA");
			r.add("P.JENIS_KP");
			r.add("P.NO_KP_LAIN ");
			r.add("P.NAMA_PEMOHON");
			r.add("PP.NO_PERMOHONAN_ONLINE");
			r.add("PP.FLAG_HUBUNGAN_PEMOHON");
			r.add("PP.ID_HUBUNGANPEMOHON");
			r.add("PP.TARIKH_MOHON_ONLINE");
			r.add("PP.ID_PERMOHONAN");
			r.add("M1.ID_SIMATI");
			r.add("P.ID_TARAFKPTG");
			r.add("P.ID_SAUDARA");
			r.add("M.NO_KP_BARU"); // 14
			r.add("M.NO_KP_LAMA");
			r.add("M.JENIS_KP");
			r.add("M.NO_KP_LAIN ");
			r.add("M.NAMA_SIMATI");

			r.add("P.ID_PERMOHONAN", r.unquote("PP.ID_PERMOHONAN"));
			r.add("PP.ID_PERMOHONAN", r.unquote("M1.ID_PERMOHONAN"));
			r.add("M1.ID_SIMATI", r.unquote("M.ID_SIMATI"));

			sql = r
					.getSQLSelect("Tblppkpermohonan PP, Tblppkpemohon P, Tblppkpermohonansimati M1, Tblppksimati m");
			// sql = sql + " AND (P.NO_KP_BARU = '" + kpbaru +
			// "' or P.NO_KP_LAMA = '" + kplama + "' or P.NO_KP_LAIN = '" +
			// kplain + "')";
			String sqlwhere = "";

			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(m.NO_KP_BARU) LIKE '%"
							+ kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(m.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(m.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					}

				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(m.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(m.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					}
				}
			}
			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere + " )";
			}
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? ""
						: rs.getString("NAMA_PEMOHON"));
				h.put("noKpBaru", rs.getString(1) == null ? "" : rs
						.getString(1));
				h.put("noKpBaru1", rs.getString(1) == null ? "" : rs.getString(
						1).substring(0, 6));
				h.put("noKpBaru2", rs.getString(1) == null ? "" : rs.getString(
						1).substring(6, 8));
				h.put("noKpBaru3", rs.getString(1) == null ? "" : rs.getString(
						1).substring(8, 12));
				h.put("noKpLama", rs.getString(2) == null ? "" : rs
						.getString(2));
				h
						.put("jenisKp", rs.getString(3) == null ? "" : rs
								.getString(3));
				h.put("noKpLain", rs.getString(4) == null ? "" : rs
						.getString(4));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? ""
						: rs.getString("NAMA_PEMOHON"));
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				h.put("noKpBaruPemohon",
						rs.getString("NO_KP_BARU") == null ? "" : rs
								.getString("NO_KP_BARU"));
				h.put("idsimati", rs.getString("ID_SIMATI") == null ? "" : rs
						.getString("ID_SIMATI"));
				h.put("tarafkptg", rs.getString("ID_TARAFKPTG") == null ? ""
						: rs.getString("ID_TARAFKPTG"));
				h.put("saudara", rs.getString("ID_SAUDARA") == null ? "" : rs
						.getString("ID_SAUDARA"));
				h.put("nokpbarusimati", rs.getString(14) == null ? "" : rs
						.getString(14));
				h.put("nokpbarusimati1", rs.getString(14) == null ? "" : rs
						.getString(14).substring(0, 6));
				h.put("nokpbarusimati2", rs.getString(14) == null ? "" : rs
						.getString(14).substring(6, 8));
				h.put("nokpbarusimati3", rs.getString(14) == null ? "" : rs
						.getString(14).substring(8, 12));
				h.put("nokplamasimati", rs.getString(15) == null ? "" : rs
						.getString(15));
				h.put("nokplainsimati", rs.getString(17) == null ? "" : rs
						.getString(17));
				detailPemohon.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector semakDetailSimati(String kpbarusimati,
			String kplamasimati, String kplainsimati, String noPermohonansimati)
			throws Exception {
		Db db = null;
		detailPemohon.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			String kpBaru = kpbarusimati.trim();
			String kpLama = kplamasimati.trim();
			String kpLain = kplainsimati.trim();
			String noPermohonan = noPermohonansimati.trim();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("P.NO_KP_BARU");
			r.add("P.NO_KP_LAMA");
			r.add("P.JENIS_KP");
			r.add("P.NO_KP_LAIN ");
			r.add("P.NAMA_PEMOHON");
			r.add("PP.NO_PERMOHONAN_ONLINE");
			r.add("PP.FLAG_HUBUNGAN_PEMOHON");
			r.add("PP.ID_HUBUNGANPEMOHON");
			r.add("PP.TARIKH_MOHON_ONLINE");
			r.add("PP.ID_PERMOHONAN");
			r.add("M1.ID_SIMATI");
			r.add("P.ID_TARAFKPTG");
			r.add("P.ID_SAUDARA");
			r.add("M.NO_KP_BARU"); // 14
			r.add("M.NO_KP_LAMA");
			r.add("M.JENIS_KP");
			r.add("M.NO_KP_LAIN ");
			r.add("M.NAMA_SIMATI");

			r.add("P.ID_PEMOHON", r.unquote("PP.ID_PEMOHON"));
			r.add("PP.ID_PERMOHONAN", r.unquote("M1.ID_PERMOHONAN"));
			r.add("PP.ID_FAIL", r.unquote("FF.ID_FAIL"));
			r.add("M1.ID_SIMATI", r.unquote("M.ID_SIMATI"));

			sql = r
					.getSQLSelect("Tblppkpermohonan PP,TBLPFDFAIL FF, Tblppkpemohon P, Tblppkpermohonansimati M1, Tblppksimati m");
			// sql = sql + " AND (m.NO_KP_BARU = '" + kpbarusimati.trim() +
			// "' or m.NO_KP_LAMA = '" + kplamasimati.trim() +
			// "' or m.NO_KP_LAIN = '" + kplainsimati.trim() +
			// "' or pp.NO_PERMOHONAN_ONLINE = '"+ noPermohonansimati.trim()
			// +"')";

			String sqlwhere = "";

			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(m.NO_KP_BARU) LIKE '%"
							+ kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(m.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(m.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					}

				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(m.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(m.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					}
				}
			}
			if (noPermohonan != "") {
				if (!noPermohonan.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(FF.NO_FAIL) = '"
								+ noPermohonan.toUpperCase() + "'";
					} else {
						sqlwhere = sqlwhere + " UPPER(FF.NO_FAIL) = '"
								+ noPermohonan.toUpperCase() + "'";
					}
				}
			}
			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere + " )";
			}
			System.out.println("SQL 1 :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? ""
						: rs.getString("NAMA_PEMOHON"));
				h.put("noKpBaru", rs.getString(1) == null ? "" : rs
						.getString(1));
				h.put("noKpBaru1", rs.getString(1) == null ? "" : rs.getString(
						1).substring(0, 6));
				h.put("noKpBaru2", rs.getString(1) == null ? "" : rs.getString(
						1).substring(6, 8));
				h.put("noKpBaru3", rs.getString(1) == null ? "" : rs.getString(
						1).substring(8, 12));
				h.put("noKpLama", rs.getString(2) == null ? "" : rs
						.getString(2));
				h
						.put("jenisKp", rs.getString(3) == null ? "" : rs
								.getString(3));
				h.put("noKpLain", rs.getString(4) == null ? "" : rs
						.getString(4));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? ""
						: rs.getString("NAMA_PEMOHON"));
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				h.put("noKpBaruPemohon",
						rs.getString("NO_KP_BARU") == null ? "" : rs
								.getString("NO_KP_BARU"));
				h.put("idsimati", rs.getString("ID_SIMATI") == null ? "" : rs
						.getString("ID_SIMATI"));
				h.put("tarafkptg", rs.getString("ID_TARAFKPTG") == null ? ""
						: rs.getString("ID_TARAFKPTG"));
				h.put("saudara", rs.getString("ID_SAUDARA") == null ? "" : rs
						.getString("ID_SAUDARA"));
				h.put("nokpbarusimati", rs.getString(14) == null ? "" : rs
						.getString(14));
				h.put("nokpbarusimati1", rs.getString(14) == null ? "" : rs
						.getString(14).substring(0, 6));
				h.put("nokpbarusimati2", rs.getString(14) == null ? "" : rs
						.getString(14).substring(6, 8));
				h.put("nokpbarusimati3", rs.getString(14) == null ? "" : rs
						.getString(14).substring(8, 12));
				h.put("nokplamasimati", rs.getString(15) == null ? "" : rs
						.getString(15));
				h.put("nokplainsimati", rs.getString(17) == null ? "" : rs
						.getString(17));
				h.put("tarikhMohon",
						rs.getString("TARIKH_MOHON_ONLINE") == null ? "" : sdf
								.format(rs.getDate("TARIKH_MOHON_ONLINE")));
				detailPemohon.addElement(h);
			}
			return detailPemohon;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getDetailsPemohon() {
		return detailPemohon;
	}

	public static Vector getAgama() throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_AGAMA");
			r.add("KOD_AGAMA");
			r.add("KETERANGAN");
			sql = r.getSQLSelect("Tblrujagama");
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idAgama", rs.getString("ID_AGAMA"));
				h.put("kodAgama", rs.getString("KOD_AGAMA"));
				h.put("keteranganAgama", rs.getString("KETERANGAN"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getWarganegara() throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_WARGANEGARA");
			r.add("KOD_WARGA");
			r.add("KETERANGAN");
			sql = r.getSQLSelect("Tblrujwarganegara");
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idWarga", rs.getString("ID_WARGANEGARA"));
				h.put("kodWarga", rs.getString("KOD_WARGA"));
				h.put("keteranganWarga", rs.getString("KETERANGAN"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector semakDataPemohon(String id) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector listDataPemohon = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("P.NO_KP_BARU");
			r.add("P.NO_KP_LAMA");
			r.add("P.JENIS_KP");
			r.add("P.NO_KP_LAIN ");
			r.add("P.NAMA_PEMOHON");
			r.add("P.ID_PEMOHON");
			r.add("P.UMUR");
			r.add("P.JANTINA");
			r.add("P.JENIS_AGAMA");
			r.add("P.JENIS_WARGA");
			r.add("P.ALAMAT_1");
			r.add("P.ALAMAT_2");
			r.add("P.ALAMAT_3");
			r.add("P.BANDAR");
			r.add("P.POSKOD");
			r.add("P.NO_HP");
			r.add("P.NO_TEL");
			r.add("P.EMEL");
			r.add("P.NO_FAX");
			r.add("P.CATATAN");
			r.add("P.ID_TARAFKPTG");
			r.add("P.ID_SAUDARA");
			r.add("P.ID_NEGERI");
			r.add("P.STATUS_PEGUAM");
			r.add("P.ALAMAT1_SURAT");
			r.add("P.ALAMAT2_SURAT");
			r.add("P.ALAMAT3_SURAT");
			r.add("P.BANDAR_SURAT");
			r.add("P.POSKOD_SURAT");
			r.add("P.ID_NEGERISURAT");
			r.add("P.NO_TEL_SURAT");
			r.add("P.NO_HP_SURAT");
			r.add("P.ID_BANDARSURAT");
			r.add("P.ID_BANDAR");

			r.add("PP.NO_PERMOHONAN_ONLINE");
			r.add("PP.FLAG_HUBUNGAN_PEMOHON");
			r.add("PP.ID_HUBUNGANPEMOHON");
			r.add("PP.TARIKH_MOHON_ONLINE");
			r.add("PP.ID_PERMOHONAN");
			r.add("PP.ID_NEGERIMHN");
			r.add("PP.ID_DAERAHMHN");
			r.add("M1.ID_PERMOHONANSIMATI");
			r.add("PP.ID_STATUS");

			r.add("P.ID_PEMOHON", r.unquote("PP.ID_PEMOHON"));
			r.add("PP.ID_PERMOHONAN", r.unquote("M1.ID_PERMOHONAN"));

			r.add("PP.ID_PERMOHONAN", id);
			// r.add("PP.SEKSYEN",8);

			sql = r
					.getSQLSelect("Tblppkpermohonan PP, Tblppkpemohon P, Tblppkpermohonansimati M1");
			System.out.println("sql view pemohon--" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("noKpLama", rs.getString("NO_KP_LAMA") == null ? "" : rs
						.getString("NO_KP_LAMA"));
				h.put("jenisKp", rs.getString("JENIS_KP") == null ? "" : rs
						.getString("JENIS_KP"));
				h.put("noKpLain", rs.getString("NO_KP_LAIN") == null ? "" : rs
						.getString("NO_KP_LAIN"));
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs
						.getString("ID_PEMOHON"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? ""
						: rs.getString("NAMA_PEMOHON"));
				h.put("noPermohonan",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				if (rs.getString("NO_KP_BARU") != null) {
					h.put("noKpBaruPemohon1",
							rs.getString("NO_KP_BARU") == null ? "" : rs
									.getString("NO_KP_BARU").substring(0, 6));
					h.put("noKpBaruPemohon2",
							rs.getString("NO_KP_BARU") == null ? "" : rs
									.getString("NO_KP_BARU").substring(6, 8));
					h.put("noKpBaruPemohon3",
							rs.getString("NO_KP_BARU") == null ? "" : rs
									.getString("NO_KP_BARU").substring(8, 12));
				}
				h.put("NRIC", rs.getString("NO_KP_BARU") == null ? "" : rs
						.getString("NO_KP_BARU"));
				h.put("umurPemohon", rs.getString("UMUR") == null ? "" : rs
						.getString("UMUR"));
				h.put("jantinaPemohon", rs.getString("JANTINA") == null ? ""
						: rs.getString("JANTINA"));
				h.put("jenisagama", rs.getString("JENIS_AGAMA") == null ? ""
						: rs.getString("JENIS_AGAMA"));
				h.put("jeniswarga", rs.getString("JENIS_WARGA") == null ? ""
						: rs.getString("JENIS_WARGA"));
				h.put("alamat1", rs.getString("ALAMAT_1") == null ? "" : rs
						.getString("ALAMAT_1"));
				h.put("alamat2", rs.getString("ALAMAT_2") == null ? "" : rs
						.getString("ALAMAT_2"));
				h.put("alamat3", rs.getString("ALAMAT_3") == null ? "" : rs
						.getString("ALAMAT_3"));
				h.put("bandarpemohon", rs.getString("BANDAR") == null ? "" : rs
						.getString("BANDAR"));
				h.put("poskodpemohon", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("hppemohon", rs.getString("NO_HP") == null ? "" : rs
						.getString("NO_HP"));
				h.put("telpemohon", rs.getString("NO_TEL") == null ? "" : rs
						.getString("NO_TEL"));
				h.put("emelpemohon", rs.getString("EMEL") == null ? "" : rs
						.getString("EMEL"));
				h.put("faxpemohon", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN"));
				h.put("tarafkptg", rs.getString("ID_TARAFKPTG") == null ? ""
						: rs.getString("ID_TARAFKPTG"));
				h.put("saudara", rs.getString("ID_SAUDARA") == null ? "" : rs
						.getString("ID_SAUDARA"));
				h.put("idnegeri", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("statuspeguam",
						rs.getString("STATUS_PEGUAM") == null ? "" : rs
								.getString("STATUS_PEGUAM"));
				h.put("flaghubungan",
						rs.getString("FLAG_HUBUNGAN_PEMOHON") == null ? "" : rs
								.getString("FLAG_HUBUNGAN_PEMOHON"));
				h.put("idhubungan",
						rs.getString("ID_HUBUNGANPEMOHON") == null ? "" : rs
								.getString("ID_HUBUNGANPEMOHON"));
				h.put("nokp", rs.getString("NO_KP_BARU") == null ? "" : rs
						.getString("NO_KP_BARU").substring(0, 2));
				h.put("idnegerimhn", rs.getString("ID_NEGERIMHN") == null ? ""
						: rs.getString("ID_NEGERIMHN"));
				h.put("iddaerahmhn", rs.getString("ID_DAERAHMHN") == null ? ""
						: rs.getString("ID_DAERAHMHN"));
				h.put("idpermohonansimati",
						rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs
								.getString("ID_PERMOHONANSIMATI"));
				h.put("alamat1surat",
						rs.getString("ALAMAT1_SURAT") == null ? "" : rs
								.getString("ALAMAT1_SURAT"));
				h.put("alamat2surat",
						rs.getString("ALAMAT2_SURAT") == null ? "" : rs
								.getString("ALAMAT2_SURAT"));
				h.put("alamat3surat",
						rs.getString("ALAMAT3_SURAT") == null ? "" : rs
								.getString("ALAMAT3_SURAT"));
				h.put("bandarsurat", rs.getString("BANDAR_SURAT") == null ? ""
						: rs.getString("BANDAR_SURAT"));
				h.put("poskodsurat", rs.getString("POSKOD_SURAT") == null ? ""
						: rs.getString("POSKOD_SURAT"));
				h.put("idnegerisurat",
						rs.getString("ID_NEGERISURAT") == null ? "" : rs
								.getString("ID_NEGERISURAT"));
				h.put("notelsurat", rs.getString("NO_TEL_SURAT") == null ? ""
						: rs.getString("NO_TEL_SURAT"));
				h.put("nohpsurat", rs.getString("NO_HP_SURAT") == null ? ""
						: rs.getString("NO_HP_SURAT"));
				h.put("iddaerahtetap", rs.getString("ID_BANDAR") == null ? ""
						: rs.getString("ID_BANDAR"));
				h.put("iddaerahsurat",
						rs.getString("ID_BANDARSURAT") == null ? "" : rs
								.getString("ID_BANDARSURAT"));
				listDataPemohon.addElement(h);
			}
			return listDataPemohon;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void insertDataSimati(Hashtable data) throws Exception {
		Db db = null;
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		try {
			long idsimati = DB.getNextID("TBLPPKSIMATI_SEQ");
			long idob = DB.getNextID("TBLPPKOB_SEQ");
			String iduser = (String) data.get("iduser");
			String idPermohonan = (String) data.get("IdPermohonan");
			String noKPBaru = (String) data.get("noKPBaru");
			String noKPLama = ((String) data.get("noKPLama")).toUpperCase();
			String jenisKPLain = (String) data.get("jenisKPLain");
			String noKpLain = (String) data.get("noKpLain");
			String namaSimati = ((String) data.get("namaSimati")).toUpperCase();
			String namaLainSimati = ((String) data.get("namaLainSimati"))
					.toUpperCase();
			String SocJantina = (String) data.get("SocJantina");
			int SocAgama = Integer.parseInt((String) data.get("SocAgama"));
			int SocWarga = Integer.parseInt((String) data.get("SocWarga"));
			String umurSimati = (String) data.get("umurSimati");
			String buktiMati = (String) data.get("buktiMati");
			String noSijilMatiSimati = ((String) data.get("noSijilMatiSimati"))
					.toUpperCase();
			String tarikhMati = (String) data.get("tarikhMati");
			String tempatMati = ((String) data.get("tempatMati")).toUpperCase();
			String waktuKematianSimati = (String) data
					.get("waktuKematianSimati");
			String sebabKematianSimati = ((String) data
					.get("sebabKematianSimati")).toUpperCase();
			String alamatTerakhir1Simati = ((String) data
					.get("alamatTerakhir1Simati")).toUpperCase();
			String alamatTerakhir2Simati = ((String) data
					.get("alamatTerakhir2Simati")).toUpperCase();
			String alamatTerakhir3Simati = ((String) data
					.get("alamatTerakhir3Simati")).toUpperCase();
			String poskodSimati = (String) data.get("poskodSimati");
			String bandarSimati = ((String) data.get("bandarSimati"))
					.toUpperCase();
			int idnegeri = Integer.parseInt((String) data.get("negeri"));
			String catatanSimati = ((String) data.get("catatanSimati"))
					.toUpperCase();
			String idtarafkptg = (String) data.get("idtarafkptg");
			String idsaudara = (String) data.get("idsaudara");
			String tarikh_mati = "to_date('" + tarikhMati + "','dd/MM/yyyy')";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("id_simati", idsimati);
			r1.add("nama_simati", namaSimati);
			r1.add("nama_lain", namaLainSimati);
			r1.add("no_kp_baru", noKPBaru);
			r1.add("no_kp_lama", noKPLama);
			r1.add("jenis_kp", jenisKPLain);
			r1.add("no_kp_lain", noKpLain);
			r1.add("UMUR", umurSimati);
			r1.add("JANTINA", SocJantina);
			r1.add("NO_SIJIL_MATI", noSijilMatiSimati);
			r1.add("TEMPAT_MATI", tempatMati);
			r1.add("ALAMAT_1", alamatTerakhir1Simati);
			r1.add("ALAMAT_2", alamatTerakhir2Simati);
			r1.add("ALAMAT_3", alamatTerakhir3Simati);
			r1.add("BANDAR", bandarSimati);
			r1.add("POSKOD", poskodSimati);
			r1.add("TARIKH_MATI", r1.unquote(tarikh_mati));
			r1.add("WAKTU_KEMATIAN", waktuKematianSimati);
			r1.add("SEBAB_MATI", sebabKematianSimati);
			r1.add("CATATAN", catatanSimati);
			r1.add("ID_NEGERI", idnegeri);
			r1.add("ID_BUKTIMATI", buktiMati);
			r1.add("JENIS_AGAMA", SocAgama);
			r1.add("JENIS_WARGA", SocWarga);
			r1.add("ID_KEMASKINI", Integer.parseInt(iduser));
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLInsert("tblppksimati");
			stmt.executeUpdate(sql1);

			/*
			 * db = new Db(); Statement stmtA = db.getStatement(); SQLRenderer
			 * r2 = new SQLRenderer(); r2.update("ID_PERMOHONAN",idPermohonan);
			 * r2.add("ID_SIMATI",idsimati); sql2 =
			 * r2.getSQLUpdate("tblppkpermohonansimati");
			 * stmtA.executeUpdate(sql2);
			 */

			if (idtarafkptg.equals("1")) {
				// db = new Db();
				Statement stmtV = db.getStatement();
				sql3 = "insert into TBLPPKOB (ID_PERMOHONANSIMATI,ID_OB,ID_SIMATI,NAMA_OB,NO_KP_BARU,NO_KP_LAMA,JENIS_KP,NO_KP_LAIN,"
						+ "JANTINA,UMUR,ID_SAUDARA,STATUS_OB,LAPIS,ID_PEMOHON,ID_MASUK,TARIKH_MASUK,STATUS_HIDUP,id_tarafkptg) VALUES "
						+ "((SELECT distinct(ID_PERMOHONANSIMATI) FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = "
						+ idPermohonan
						+ "),"
						+ ""
						+ idob
						+ ","
						+ ""
						+ idsimati
						+ ","
						+ "(SELECT UPPER(NAMA_PEMOHON) FROM TBLPPKPEMOHON WHERE ID_PERMOHONAN = "
						+ idPermohonan
						+ "),"
						+ "(SELECT NO_KP_BARU FROM TBLPPKPEMOHON WHERE ID_PERMOHONAN = "
						+ idPermohonan
						+ "),"
						+ "(SELECT UPPER(NO_KP_LAMA) FROM TBLPPKPEMOHON WHERE ID_PERMOHONAN = "
						+ idPermohonan
						+ "),"
						+ "(SELECT UPPER(JENIS_KP) FROM TBLPPKPEMOHON WHERE ID_PERMOHONAN = "
						+ idPermohonan
						+ "),"
						+ "(SELECT UPPER(NO_KP_LAIN) FROM TBLPPKPEMOHON WHERE ID_PERMOHONAN = "
						+ idPermohonan
						+ "),"
						+ "(SELECT UPPER(JANTINA) FROM TBLPPKPEMOHON WHERE ID_PERMOHONAN = "
						+ idPermohonan
						+ "),"
						+ "(SELECT UMUR FROM TBLPPKPEMOHON WHERE ID_PERMOHONAN = "
						+ idPermohonan
						+ "),"
						+ ""
						+ idsaudara
						+ ","
						+ "0,"
						+ "1,"
						+ "(SELECT ID_PEMOHON FROM TBLPPKPEMOHON WHERE ID_PERMOHONAN = "
						+ idPermohonan
						+ "),"
						+ ""
						+ Integer.parseInt(iduser)
						+ "," + "sysdate,0," + idtarafkptg + ")";
				stmtV.executeUpdate(sql3);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateDataSimati(Hashtable data) throws Exception {
		Db db = null;
		String sql1 = "";
		String sql2 = "";
		try {
			String userid = (String) data.get("iduser");
			String idPermohonan = (String) data.get("idpermohonan");
			String idSimati = (String) data.get("idSimati");
			String noKPBaru = (String) data.get("noKPBaru");
			String noKPLama = ((String) data.get("noKPLama")).toUpperCase();
			String jenisKPLain = (String) data.get("jenisKPLain");
			String noKpLain = (String) data.get("noKpLain");
			String namaSimati = ((String) data.get("namaSimati")).toUpperCase();
			String namaLainSimati = ((String) data.get("namaLainSimati"))
					.toUpperCase();
			String SocJantina = (String) data.get("SocJantina");
			int SocAgama = Integer.parseInt((String) data.get("SocAgama"));
			int Socwarga = Integer.parseInt((String) data.get("SocWarga"));
			String umurSimati = (String) data.get("umurSimati");
			int buktiMati = Integer.parseInt((String) data.get("buktiMati"));
			String noSijilMatiSimati = ((String) data.get("noSijilMatiSimati"))
					.toUpperCase();
			String tarikhMati = (String) data.get("tarikhMati");
			String tempatMati = ((String) data.get("tempatMati")).toUpperCase();
			String waktuKematianSimati = (String) data
					.get("waktuKematianSimati");
			String sebabKematianSimati = ((String) data
					.get("sebabKematianSimati")).toUpperCase();
			String alamatTerakhir1Simati = ((String) data
					.get("alamatTerakhir1Simati")).toUpperCase();
			String alamatTerakhir2Simati = ((String) data
					.get("alamatTerakhir2Simati")).toUpperCase();
			String alamatTerakhir3Simati = ((String) data
					.get("alamatTerakhir3Simati")).toUpperCase();
			String poskodSimati = (String) data.get("poskodSimati");
			String bandarSimati = ((String) data.get("bandarSimati"))
					.toUpperCase();
			int bandar = (Integer) data.get("bandar");
			int idnegeri = (Integer) data.get("negeri");
			String catatanSimati = ((String) data.get("catatanSimati"))
					.toUpperCase();
			String tarikh_mati = "to_date('" + tarikhMati + "','dd/MM/yyyy')";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("id_simati", idSimati);
			r1.add("nama_simati", namaSimati);
			r1.add("NAMA_LAIN", namaLainSimati);
			r1.add("no_kp_baru", noKPBaru);
			r1.add("no_kp_lama", noKPLama);
			r1.add("jenis_kp", jenisKPLain);
			r1.add("no_kp_lain", noKpLain);
			r1.add("UMUR", umurSimati);
			r1.add("JANTINA", SocJantina);
			r1.add("NO_SIJIL_MATI", noSijilMatiSimati);
			r1.add("TEMPAT_MATI", tempatMati);
			r1.add("ALAMAT_1", alamatTerakhir1Simati);
			r1.add("ALAMAT_2", alamatTerakhir2Simati);
			r1.add("ALAMAT_3", alamatTerakhir3Simati);
			r1.add("BANDAR", bandarSimati);
			r1.add("POSKOD", poskodSimati);
			r1.add("TARIKH_MATI", r1.unquote(tarikh_mati));
			r1.add("WAKTU_KEMATIAN", waktuKematianSimati);
			r1.add("SEBAB_MATI", sebabKematianSimati);
			r1.add("CATATAN", catatanSimati);
			r1.add("ID_NEGERI", idnegeri);
			r1.add("ID_BANDAR", bandar);
			r1.add("ID_BUKTIMATI", buktiMati);
			r1.add("JENIS_AGAMA", SocAgama);
			r1.add("JENIS_WARGA", Socwarga);
			r1.add("ID_KEMASKINI", userid);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblppksimati");
			stmt.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int countDataSimati(String id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select count(id_simati) as CntId from tblppkpermohonansimati where id_permohonan = '"
					+ id + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v1 = new Vector();
			/*
			 * while (rs.next()) { Hashtable h = new Hashtable(); h.put("cntId",
			 * rs.getString("CntId")); v1.addElement(h); }
			 */
			if (rs.next()) {
				return rs.getString("CntId") == null ? 0 : Integer.parseInt(rs
						.getString("CntId"));
			} else
				return 0;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector semakDataSimati(String id) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector listDataSimati = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("DISTINCT(P.NO_KP_BARU)");
			r.add("P.NO_KP_LAMA");
			r.add("P.JENIS_KP");
			r.add("P.NO_KP_LAIN ");
			r.add("P.NAMA_PEMOHON");
			r.add("P.ID_PEMOHON");
			r.add("PP.NO_PERMOHONAN_ONLINE");
			r.add("PP.FLAG_HUBUNGAN_PEMOHON");
			r.add("PP.ID_HUBUNGANPEMOHON");
			r.add("PP.TARIKH_MOHON_ONLINE");
			r.add("PP.ID_PERMOHONAN");
			r.add("M.ID_SIMATI");
			r.add("M.NAMA_SIMATI");
			r.add("M.NAMA_LAIN");
			r.add("M.NO_KP_BARU");
			r.add("M.NO_KP_LAMA");
			r.add("M.JENIS_KP");
			r.add("M.NO_KP_LAIN");
			r.add("M.UMUR");
			r.add("M.JANTINA");
			r.add("M.NO_SIJIL_MATI");
			r.add("M.TEMPAT_MATI");
			r.add("M.ALAMAT_1");
			r.add("M.ALAMAT_2");
			r.add("M.ALAMAT_3");
			r.add("M.BANDAR");
			r.add("M.POSKOD");
			r.add("M.TARIKH_MATI");
			r.add("M.WAKTU_KEMATIAN");
			r.add("M.JENIS_WAKTU_MATI");
			r.add("M.SEBAB_MATI");
			r.add("M.CATATAN");
			r.add("M.ID_NEGERI");
			r.add("M.ID_BUKTIMATI");
			r.add("M.JENIS_AGAMA");
			r.add("M.JENIS_WARGA");
			r.add("M.TARIKH_KEMASKINI");
			r.add("M1.ID_PERMOHONANSIMATI");
			r.add("PP.ID_NEGERIMHN");
			r.add("PP.ID_DAERAHMHN");
			r.add("P.ID_TARAFKPTG");
			r.add("P.ID_SAUDARA");
			r.add("PP.ID_STATUS");
			r.add("M.ID_BANDAR");

			r.add("P.ID_PEMOHON", r.unquote("PP.ID_PEMOHON"));
			r.add("PP.ID_PERMOHONAN", r.unquote("M1.ID_PERMOHONAN"));
			r.add("M1.ID_SIMATI", r.unquote("M.ID_SIMATI(+)"));

			r.add("PP.ID_PERMOHONAN", id);
			// r.add("PP.SEKSYEN",8);

			sql = r
					.getSQLSelect("Tblppkpermohonan PP, Tblppkpemohon P, Tblppksimati M, Tblppkpermohonansimati M1");
			System.out.println("ONLINE SQL CHECK ::" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				System.out.println("id status " + rs.getString("ID_STATUS"));
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("idsimati", rs.getString("ID_SIMATI") == null ? "" : rs
						.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("namalainsimati", rs.getString("NAMA_LAIN") == null ? ""
						: rs.getString("NAMA_LAIN"));
				h.put("nokpbarusimati1", rs.getString(15) == null ? "" : rs
						.getString(15).substring(0, 6));
				h.put("nokpbarusimati2", rs.getString(15) == null ? "" : rs
						.getString(15).substring(6, 8));
				h.put("nokpbarusimati3", rs.getString(15) == null ? "" : rs
						.getString(15).substring(8, 12));
				h.put("nokplamasimati", rs.getString(16) == null ? "" : rs
						.getString(16));
				h.put("jeniskpsimati", rs.getString(17) == null ? "" : rs
						.getString(17));
				h.put("nokplainsimati", rs.getString(18) == null ? "" : rs
						.getString(18));
				h.put("umursimati", rs.getString("UMUR") == null ? "" : rs
						.getString("UMUR"));
				h.put("jantinasimati", rs.getString("JANTINA") == null ? ""
						: rs.getString("JANTINA"));
				h.put("nosijilsimati",
						rs.getString("NO_SIJIL_MATI") == null ? "" : rs
								.getString("NO_SIJIL_MATI"));
				h.put("tmptmatisimati",
						rs.getString("TEMPAT_MATI") == null ? "" : rs
								.getString("TEMPAT_MATI"));
				h.put("alamat1simati", rs.getString("ALAMAT_1") == null ? ""
						: rs.getString("ALAMAT_1"));
				h.put("alamat2simati", rs.getString("ALAMAT_2") == null ? ""
						: rs.getString("ALAMAT_2"));
				h.put("alamat3simati", rs.getString("ALAMAT_3") == null ? ""
						: rs.getString("ALAMAT_3"));
				h.put("bandarsimati", rs.getString("BANDAR") == null ? "" : rs
						.getString("BANDAR"));
				h.put("poskodsimati", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("tarikhmati", rs.getString("TARIKH_MATI") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MATI")));
				h.put("waktukematian",
						rs.getString("WAKTU_KEMATIAN") == null ? "" : rs
								.getString("WAKTU_KEMATIAN"));
				h.put("jeniswaktumati",
						rs.getString("JENIS_WAKTU_MATI") == null ? "" : rs
								.getString("JENIS_WAKTU_MATI"));
				h.put("sebabmati", rs.getString("SEBAB_MATI") == null ? "" : rs
						.getString("SEBAB_MATI"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("idBuktiMati", rs.getString("ID_BUKTIMATI") == null ? ""
						: rs.getString("ID_BUKTIMATI"));
				h.put("jenisAgama", rs.getString("JENIS_AGAMA") == null ? ""
						: rs.getString("JENIS_AGAMA"));
				h.put("jenisWarga", rs.getString("JENIS_WARGA") == null ? ""
						: rs.getString("JENIS_WARGA"));
				h.put("tarikhKkini",
						rs.getString("TARIKH_KEMASKINI") == null ? "" : rs
								.getString("TARIKH_KEMASKINI"));
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs
						.getString("ID_PEMOHON"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? ""
						: rs.getString("NAMA_PEMOHON"));
				h.put("flaghubungan",
						rs.getString("FLAG_HUBUNGAN_PEMOHON") == null ? "" : rs
								.getString("FLAG_HUBUNGAN_PEMOHON"));
				h.put("idpermohonansimati",
						rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs
								.getString("ID_PERMOHONANSIMATI"));
				h.put("idnegerimhn", rs.getString("ID_NEGERIMHN") == null ? ""
						: rs.getString("ID_NEGERIMHN"));
				h.put("iddaerahmhn", rs.getString("ID_DAERAHMHN") == null ? ""
						: rs.getString("ID_DAERAHMHN"));
				h.put("idtarafkptg", rs.getString("ID_TARAFKPTG") == null ? ""
						: rs.getString("ID_TARAFKPTG"));
				h.put("idsaudara", rs.getString("ID_SAUDARA") == null ? "" : rs
						.getString("ID_SAUDARA"));
				h.put("nopermohonanonline", rs
						.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
						.getString("NO_PERMOHONAN_ONLINE"));
				h.put("tarikhpermohonanonline", rs
						.getString("TARIKH_MOHON_ONLINE") == null ? "" : rs
						.getString("TARIKH_MOHON_ONLINE"));

				h.put("idstatus", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				h.put("idhubungan",
						rs.getString("ID_HUBUNGANPEMOHON") == null ? "" : rs
								.getString("ID_HUBUNGANPEMOHON"));
				h.put("idBandar", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				listDataSimati.addElement(h);
			}
			return listDataSimati;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateDataPemohon(Hashtable data) throws Exception {
		Db db = null;
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";

		try {
			long idob = DB.getNextID("TBLPPKOB_SEQ");
			String namaPemohonPemohon = ((String) data
					.get("namaPemohonPemohon")).toUpperCase();
			String idPermohonan = (String) data.get("IdPermohonan");
			String idPemohon = (String) data.get("idPemohon");
			String noKPLamaPemohon = ((String) data.get("noKPLamaPemohon"))
					.toUpperCase();
			String jenisKP = ((String) data.get("jenisKP")).toUpperCase();
			String noKpLain = (String) data.get("noKpLain");
			String jenisTaraf = (String) data.get("jenisTaraf");
			String talianSaudara = (String) data.get("talianSaudara");
			String jantina = (String) data.get("jantina");
			String agama = (String) data.get("agama");
			String warga = (String) data.get("warga");
			String umurPemohon = (String) data.get("umurPemohon");
			String alamatTerakhir1Pemohon = ((String) data
					.get("alamatTerakhir1Pemohon")).toUpperCase();
			String alamatTerakhir2Pemohon = ((String) data
					.get("alamatTerakhir2Pemohon")).toUpperCase();
			String alamatTerakhir3Pemohon = ((String) data
					.get("alamatTerakhir3Pemohon")).toUpperCase();
			String poskodPemohon = (String) data.get("poskodPemohon");
			String bandarPemohon = ((String) data.get("bandarPemohon"))
					.toUpperCase();
			String idnegeri = (String) data.get("negeri");
			String noTelefonPemohon = (String) data.get("noTelefonPemohon");
			String noTelefonBimbitPemohon = (String) data
					.get("noTelefonBimbitPemohon");
			String noFaksPemohon = (String) data.get("noFaksPemohon");
			String emelPemohon = (String) data.get("emelPemohon");
			String catatanPemohon = ((String) data.get("catatanPemohon"))
					.toUpperCase();
			String peguam = (String) data.get("peguam");
			String wujudWaris = (String) data.get("wujudWaris");
			String kp_Waris = (String) data.get("kp_Waris");
			String iduser = (String) data.get("iduser");
			String chcAlamat = (String) data.get("chcAlamat");
			String alamatSurat1Pemohon = ((String) data
					.get("alamatSurat1Pemohon")).toUpperCase();
			String alamatSurat2Pemohon = ((String) data
					.get("alamatSurat2Pemohon")).toUpperCase();
			String alamatSurat3Pemohon = ((String) data
					.get("alamatSurat3Pemohon")).toUpperCase();
			String poskodSuratPemohon = (String) data.get("poskodSuratPemohon");
			// String bandarSuratPemohon =
			// ((String)data.get("bandarSuratPemohon")).toUpperCase();
			String negeriSurat = (String) data.get("negeriSurat");
			int bandar = (Integer) data.get("id_bandar");
			int bandarsurat = (Integer) data.get("id_bandarsurat");
			String newICNo = (String) data.get("newIcNo");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("id_pemohon", idPemohon);
			r1.add("NO_KP_BARU", newICNo);
			r1.add("NO_KP_LAMA", noKPLamaPemohon);
			r1.add("JENIS_KP", jenisKP);
			r1.add("NO_KP_LAIN", noKpLain);
			r1.add("NAMA_PEMOHON", namaPemohonPemohon);
			r1.add("UMUR", umurPemohon);
			r1.add("JANTINA", jantina);
			r1.add("JENIS_AGAMA", agama);
			r1.add("JENIS_WARGA", warga);
			r1.add("ALAMAT_1", alamatTerakhir1Pemohon);
			r1.add("ALAMAT_2", alamatTerakhir2Pemohon);
			r1.add("ALAMAT_3", alamatTerakhir3Pemohon);
			r1.add("POSKOD", poskodPemohon);
			r1.add("NO_HP", noTelefonBimbitPemohon);
			r1.add("NO_TEL", noTelefonPemohon);
			r1.add("EMEL", emelPemohon);
			r1.add("NO_FAX", noFaksPemohon);
			r1.add("CATATAN", catatanPemohon);
			r1.add("ID_TARAFKPTG", jenisTaraf);
			r1.add("ID_SAUDARA", talianSaudara);
			if (idnegeri.equals("")) {
				r1.add("ID_NEGERI", 0);
			} else {
				r1.add("ID_NEGERI", idnegeri);
			}
			r1.add("ID_BANDAR", bandar);
			r1.add("STATUS_PEGUAM", peguam);
			if (chcAlamat.equals("1")) {
				r1.add("ALAMAT1_SURAT", alamatTerakhir1Pemohon);
				r1.add("ALAMAT2_SURAT", alamatTerakhir2Pemohon);
				r1.add("ALAMAT3_SURAT", alamatTerakhir3Pemohon);
				r1.add("ID_BANDARSURAT", bandar);
				r1.add("POSKOD_SURAT", poskodPemohon);
				if (idnegeri.equals("")) {
					r1.add("ID_NEGERISURAT", 0);
				} else {
					r1.add("ID_NEGERISURAT", idnegeri);
				}
			} else {
				r1.add("ALAMAT1_SURAT", alamatSurat1Pemohon);
				r1.add("ALAMAT2_SURAT", alamatSurat2Pemohon);
				r1.add("ALAMAT3_SURAT", alamatSurat3Pemohon);
				r1.add("ID_BANDARSURAT", bandarsurat);
				r1.add("POSKOD_SURAT", poskodSuratPemohon);
				if (negeriSurat.equals("")) {
					r1.add("ID_NEGERISURAT", 0);
				} else {
					r1.add("ID_NEGERISURAT", negeriSurat);
				}
			}
			sql1 = r1.getSQLUpdate("tblppkpemohon");
			stmt.executeUpdate(sql1);

			System.out.println("sql pemohon update" + sql1);

			Statement stmta = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.update("id_permohonan", idPermohonan);
			if (jenisTaraf.equals("1")) {
				r2.add("flag_hubungan_pemohon", jenisTaraf);
			} else {
				r2.add("flag_hubungan_pemohon", 2);
			}
			sql2 = r2.getSQLUpdate("tblppkpermohonan");
			stmta.executeUpdate(sql2);

			if (!wujudWaris.equals("0")) {
				String sqladd = "";
				if (jenisTaraf.equals("1")) { // waris
					System.out.println("test 3");
					Statement stmtB = db.getStatement();
					sql3 = "update tblppkob set nama_ob='"
							+ namaPemohonPemohon
							+ "',alamat_1='"
							+ alamatTerakhir1Pemohon
							+ "', alamat_2='"
							+ alamatTerakhir2Pemohon
							+ "', alamat_3='"
							+ alamatTerakhir3Pemohon
							+ "', "
							+ "poskod='"
							+ poskodPemohon
							+ "',id_negeri="
							+ idnegeri
							+ ",id_bandar="
							+ bandar
							+ ",no_hp='"
							+ noTelefonBimbitPemohon
							+ "', "
							+ "alamat1_surat='"
							+ alamatSurat1Pemohon
							+ "',alamat2_surat='"
							+ alamatSurat2Pemohon
							+ "',alamat3_surat='"
							+ alamatSurat3Pemohon
							+ "',"
							+ "poskod_surat='"
							+ poskodSuratPemohon
							+ "',id_negerisurat = '"
							+ negeriSurat
							+ "',id_bandarsurat="
							+ bandarsurat
							+ ","
							+ "no_tel='"
							+ noTelefonPemohon
							+ "',id_tarafkptg="
							+ jenisTaraf
							+ ",id_saudara="
							+ talianSaudara
							+ ", status_ob=0 "
							+ "where no_kp_baru = (select a.no_kp_baru from tblppkpemohon a, tblppkpermohonan b where a.id_pemohon = b.id_pemohon and b.id_permohonan = "
							+ idPermohonan + ")";

					System.out.println("sql3--" + sql3);

					sql3 = sql3 + sqladd;

					stmtB.executeUpdate(sql3);
				} else {
					db = new Db();
					Statement stmtD = db.getStatement();
					String sql5 = "update tblppkob set nama_ob='"
							+ namaPemohonPemohon
							+ "',alamat_1='"
							+ alamatTerakhir1Pemohon
							+ "', alamat_2='"
							+ alamatTerakhir2Pemohon
							+ "', alamat_3='"
							+ alamatTerakhir3Pemohon
							+ "', "
							+ "poskod='"
							+ poskodPemohon
							+ "',id_negeri="
							+ idnegeri
							+ ",id_bandar="
							+ bandar
							+ ",no_hp='"
							+ noTelefonBimbitPemohon
							+ "', "
							+ "alamat1_surat='"
							+ alamatSurat1Pemohon
							+ "',alamat2_surat='"
							+ alamatSurat2Pemohon
							+ "',alamat3_surat='"
							+ alamatSurat3Pemohon
							+ "',"
							+ "poskod_surat='"
							+ poskodSuratPemohon
							+ "',id_negerisurat = '"
							+ negeriSurat
							+ "',id_bandarsurat="
							+ bandarsurat
							+ ","
							+ "no_tel='"
							+ noTelefonPemohon
							+ "',id_tarafkptg="
							+ jenisTaraf
							+ ",id_saudara="
							+ talianSaudara
							+ ",status_ob=1,nilai_hutang=0,"
							+ "jenis_pemiutang=1 where no_kp_baru = (select a.no_kp_baru from tblppkpemohon a, tblppkpermohonan b where a.id_pemohon = b.id_pemohon and b.id_permohonan = "
							+ idPermohonan + ")";
					sql5 = sql5 + sqladd;
					stmtD.executeUpdate(sql5);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int countDataPeguam(String idPeguam) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select count(id_peguam) as CntId from tblppkpeguam where id_Peguam = '"
					+ idPeguam + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v1 = new Vector();
			if (rs.next()) {
				return rs.getString("CntId") == null ? 0 : Integer.parseInt(rs
						.getString("CntId"));
			} else
				return 0;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void insertpeguam(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			String id_Pemohon = (String) data.get("idPemohon");
			long id_Peguam = DB.getNextID("TBLPPKPEGUAM_SEQ");
			long id_Peguampemohon = DB.getNextID("TBLPPKPEGUAMPEMOHON_SEQ");

			String iduser = (String) data.get("iduser");
			String nama_Firma = ((String) data.get("firma")).toUpperCase();
			String alamat_1 = ((String) data.get("alamat1")).toUpperCase();
			String alamat_2 = ((String) data.get("alamat2")).toUpperCase();
			String alamat_3 = ((String) data.get("alamat3")).toUpperCase();
			String bandar = ((String) data.get("bandar")).toUpperCase();
			String poskod = (String) data.get("poskod");
			int id_Negeri = (Integer) data.get("idnegeri");
			int id_bandar = (Integer) data.get("idbandar");
			String no_Rujukan_Firma = ((String) data.get("rujfirma"))
					.toUpperCase();
			String no_Tel = (String) data.get("noTel");
			String no_Fax = (String) data.get("noFax");
			String emel = (String) data.get("emel");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Peguam", id_Peguam);
			r.add("nama_Firma", nama_Firma);
			r.add("alamat1", alamat_1);
			r.add("alamat2", alamat_2);
			r.add("alamat3", alamat_3);
			r.add("bandar", bandar);
			r.add("poskod", poskod);
			r.add("id_Negeri", id_Negeri);
			r.add("id_bandar", id_bandar);
			r.add("no_Rujukan_Firma", no_Rujukan_Firma);
			r.add("no_Tel", no_Tel);
			r.add("no_Fax", no_Fax);
			r.add("emel", emel);
			r.add("id_masuk", iduser);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", iduser);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppkpeguam");
			stmt.executeUpdate(sql);

			// db = new Db();
			Statement stmta = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("id_peguampemohon", id_Peguampemohon);
			r1.add("id_Peguam", id_Peguam);
			r1.add("id_Pemohon", id_Pemohon);
			r1.add("id_masuk", iduser);
			r1.add("tarikh_masuk", r1.unquote("sysdate"));
			r1.add("id_kemaskini", iduser);
			r1.add("tarikh_kemaskini", r1.unquote("sysdate"));
			sql1 = r1.getSQLInsert("tblppkpeguampemohon");
			stmta.executeUpdate(sql1);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void deletePeguam(String idPeguam) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			String id_Pemohon = "0";
			if (!idPeguam.equals("")) {
				id_Pemohon = idPeguam;
			} else {
				id_Pemohon = "0";
			}

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "delete from tblppkpeguampemohon where id_peguam = "
					+ id_Pemohon + "";
			stmt.executeUpdate(sql);

			Statement stmtx = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			sql1 = "delete from tblppkpeguam where id_peguam = " + id_Pemohon
					+ "";
			stmtx.executeUpdate(sql1);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector countDataOB(String id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select count(id_simati) as CntId from TblppkOB where id_simati = "
					+ id + "";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v1 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("cntId", rs.getString("CntId"));
				v1.addElement(h);
			}
			return v1;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getDataPenting(String id) throws Exception {
		Db db = null;
		Vector listPenting = new Vector();
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

			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.id_Simati", r.unquote("m.id_Simati"));

			// r.add("ob.id_simati",simati);

			r.add("m.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", id);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p");

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
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
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
				h.put("statusOB", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("idtarafkptg", rs.getString("id_Tarafkptg") == null ? ""
						: rs.getString("id_Tarafkptg"));
				h.put("butiranhutang",
						rs.getString("butiran_Hutang") == null ? "" : rs
								.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang") == null ? ""
						: rs.getString("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				listPenting.addElement(h);
				count++;
			}
			return listPenting;
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static Vector getNoFail() throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select * from (Select id_fail from tblpfdfail order by id_fail desc) where rownum < 2 ";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v1 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_fail", rs.getString("id_fail") == null ? "" : rs
						.getString("id_fail"));
				v1.addElement(h);
			}
			return v1;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addHtaam(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			int iduser = Integer.parseInt((String) data.get("iduser"));
			String idsimati = (String) data.get("idSimati");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			String noHakmilik = ((String) data.get("noHakmilik")).toUpperCase();
			String nopt = ((String) data.get("nopt")).toUpperCase();
			String basimati = (String) data.get("basimati");
			String bbsimati = (String) data.get("bbsimati");
			double nilai_Hta_memohon = (Double) data.get("nilai_Hta_memohon");
			String catatan = ((String) data.get("catatan")).toUpperCase();
			String idpermohonansimati = (String) data.get("idpermohonansimati");
			
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			String sekatan = (String) data.get("sekatan");
			String syaratNyata = (String) data.get("syaratNyata");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idsimati);
			r.add("no_Hakmilik", noHakmilik);
			r.add("no_Pt", nopt);
			r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			if (jenishakmilik != 0) {
				r.add("id_Jenishm", jenishakmilik);
			}
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}
			if (basimati != "") {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != "") {
				r.add("bb_Simati", bbsimati);
			}
			r.add("catatan", catatan);
			String jenishta = "Y";
			String flag = "T";
			r.add("jenis_Hta", jenishta);
			r.add("flag_PA", flag);
			r.add("flag_PT", flag);
			r.add("flag_selesai", flag);
			r.add("id_permohonansimati", Integer.parseInt(idpermohonansimati));
			r.add("ID_MASUK", iduser);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);
			
			sql = r.getSQLInsert("tblppkhta");
			System.out.println("sql add hta baru" + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateHtaam(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {

			String userid = (String) data.get("iduser");
			String noHakmilik = ((String) data.get("noHakmilik")).toUpperCase();
			String idsimati = (String) data.get("idSimati");
			String nopt = ((String) data.get("nopt")).toUpperCase();
			double nilai_Hta_memohon = (Double) data.get("nilai_Hta_memohon");
			int jenishakmilik = (Integer) data.get("jenishakmilik");
			int negeri = (Integer) data.get("negeri");
			int daerah = (Integer) data.get("daerah");
			int mukim = (Integer) data.get("mukim");
			int basimati = (Integer) data.get("basimati");
			int bbsimati = (Integer) data.get("bbsimati");
			String idhta = (String) data.get("idhta");
			String catatan = ((String) data.get("catatan")).toUpperCase();
			
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			String sekatan = (String) data.get("sekatan");
			String syaratNyata = (String) data.get("syaratNyata");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Hta", idhta);
			r.add("id_Simati", idsimati);
			r.add("no_Hakmilik", noHakmilik);
			r.add("no_Pt", nopt);
			r.add("nilai_Hta_Tarikhmohon", nilai_Hta_memohon);
			if (jenishakmilik != 0) {
				r.add("id_Jenishm", jenishakmilik);
			}
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			if (daerah != 0) {
				r.add("id_Daerah", daerah);
			}
			if (mukim != 0) {
				r.add("id_Mukim", mukim);
			}

			if (basimati != 0) {
				r.add("ba_Simati", basimati);
			}
			if (bbsimati != 0) {
				r.add("bb_Simati", bbsimati);
			}
			r.add("catatan", catatan);
			String jenishta = "Y";
			r.add("jenis_Hta", jenishta);
			r.add("ID_KEMASKINI", userid);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			
			// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
			r.add("SEKATAN", sekatan);
			r.add("SYARAT_NYATA", syaratNyata);
			
			sql = r.getSQLUpdate("tblppkhta");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getDataHTA(String idpermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector listHTA = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("h.id_Hta");
			r.add("h.no_Hakmilik");
			r.add("h.id_Simati");
			r.add("h.no_Pt");
			r.add("h.nilai_Hta_Tarikhmohon");
			r.add("h.nilai_Hta_Tarikhmati");
			r.add("h.id_Kategori");
			r.add("h.id_Jenishm");
			r.add("h.id_Jenispb");
			r.add("h.id_Negeri");
			r.add("h.id_Daerah");
			r.add("h.id_Luas");
			r.add("h.id_Mukim");
			r.add("h.luas_Hmp");
			r.add("h.luas");
			r.add("h.no_Cagaran");
			r.add("h.no_Pajakan");
			r.add("h.jenis_Tnh");
			r.add("h.ba_Simati");
			r.add("h.bb_Simati");
			r.add("h.jenis_Hta");
			r.add("h.tanggungan");
			r.add("h.no_Perserahan");
			r.add("h.catatan");
			r.add("h.status_Pemilikan");
			r.add("n.nama_negeri");
			r.add("d.nama_daerah");
			r.add("m.nama_mukim");
			r.add("h.id_permohonansimati");
			r.add("j.kod_jenis_hakmilik");

			String status = "Y";

			r.add("h.id_simati", r.unquote("m1.id_simati"));
			r.add("h.id_Negeri", r.unquote("n.id_Negeri"));
			r.add("h.id_Daerah", r.unquote("d.id_Daerah"));
			r.add("h.id_Mukim", r.unquote("m.id_Mukim"));
			r.add("m1.id_simati", r.unquote("s.id_simati(+)"));
			r.add("h.id_jenishm", r.unquote("j.id_jenishakmilik"));
			r.add("m1.id_permohonan", r.unquote("p.id_permohonan(+)"));

			r.add("h.jenis_Hta", status);
			r.add("m1.id_permohonan", idpermohonan);
			r.add("p.seksyen", 8);

			sql = r
					.getSQLSelect("Tblppkhta h, Tblppksimati s, tblrujnegeri n, tblrujdaerah d, tblrujmukim m, Tblppkpermohonansimati M1, Tblppkpermohonan p, tblrujjenishakmilik j");
			sql = sql + " order by id_hta desc";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
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
						.getString("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getString("nilai_Hta_Tarikhmati"));
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
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));
				h.put("namanegeri", rs.getString("nama_negeri") == null ? ""
						: rs.getString("nama_negeri"));
				h.put("namadaerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));
				h.put("namamukim", rs.getString("nama_mukim") == null ? "" : rs
						.getString("nama_mukim"));
				h.put("idpermohonansimati",
						rs.getString("id_permohonansimati") == null ? "" : rs
								.getString("id_permohonansimati"));
				h.put("kod_jenis_hakmilik",
						rs.getString("kod_jenis_hakmilik") == null ? "" : rs
								.getString("kod_jenis_hakmilik"));
				listHTA.addElement(h);
			}
			return listHTA;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getDataHTAX(String idpermohonansimati)
			throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector listHTAX = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("h.id_Hta");
			r.add("h.no_Hakmilik");
			r.add("h.id_Simati");
			r.add("h.no_Pt");
			r.add("h.nilai_Hta_Tarikhmohon");
			r.add("h.nilai_Hta_Tarikhmati");
			r.add("h.id_Kategori");
			r.add("h.id_Jenishm");
			r.add("h.id_Jenispb");
			r.add("h.id_Negeri");
			r.add("h.id_Daerah");
			r.add("h.id_Luas");
			r.add("h.id_Mukim");
			r.add("h.luas_Hmp");
			r.add("h.luas");
			r.add("h.no_Cagaran");
			r.add("h.no_Pajakan");
			r.add("h.jenis_Tnh");
			r.add("h.ba_Simati");
			r.add("h.bb_Simati");
			r.add("h.jenis_Hta");
			r.add("h.tanggungan");
			r.add("h.no_Perserahan");
			r.add("h.catatan");
			r.add("h.status_Pemilikan");
			r.add("h.no_Perjanjian");
			r.add("h.no_Roh");
			r.add("n.nama_negeri");
			r.add("d.nama_daerah");
			r.add("m.nama_mukim");
			String status = "T";

			r.add("h.id_simati", r.unquote("s.id_simati"));
			r.add("h.id_Negeri", r.unquote("n.id_negeri"));
			r.add("h.id_Daerah", r.unquote("d.id_daerah"));
			r.add("h.id_Mukim", r.unquote("m.id_mukim"));

			r.add("h.id_permohonansimati", idpermohonansimati);

			r.add("h.jenis_Hta", status);
			sql = r
					.getSQLSelect("Tblppkhta h, Tblppksimati s, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m");
			sql = sql + " order by h.id_Hta desc";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
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
						.getString("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati",
						rs.getString("nilai_Hta_Tarikhmati") == null ? "" : rs
								.getString("nilai_Hta_Tarikhmati"));
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
						rs.getString("no_Perserahan") == null ? "" : rs
								.getString("no_Perserahan"));
				h.put("noperjanjian",
						rs.getString("no_Perjanjian") == null ? "" : rs
								.getString("no_Perjanjian"));
				h.put("noroh", rs.getString("no_Roh") == null ? "" : rs
						.getString("no_Roh"));
				h.put("namanegeri", rs.getString("nama_negeri") == null ? ""
						: rs.getString("nama_negeri"));
				h.put("namadaerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));
				h.put("namamukim", rs.getString("nama_mukim") == null ? "" : rs
						.getString("nama_mukim"));
				listHTAX.addElement(h);
			}
			return listHTAX;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	//syafiqah add 1/7/2020
	public void insertPermohonanBantah(Hashtable data) throws Exception {
		Db db = null;
		Db db2 = null;
		String sql = "";
		try {
			String id_pembantah = (String) data.get("id_pembantah");
			String nama_pembantah = (String) data.get("nama_pembantah");
			String alamat1 = (String) data.get("alamat1");
			String alamat2 = (String) data.get("alamat2");
			String alamat3 = (String) data.get("alamat3");
			String poskod = (String) data.get("poskod");
			String bandar = (String) data.get("bandar");
			String negeri = (String) data.get("negeri");
			String emel = (String) data.get("emel");
			String no_hp = (String) data.get("noTel");
			String sebab = (String) data.get("sebab");
			String id_fail = (String) data.get("id_fail");
			String no_fail = (String) data.get("no_fail");
			String no_kp_baru = (String) data.get("no_kp_baru");
			String tarikh_hantar = (String) data.get("tarikh_hantar");
			String id_perbicaraan = (String) data.get("id_perbicaraan");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_PEMBANTAH", id_pembantah);
			r.add("NAMA_PEMBANTAH", nama_pembantah);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("BANDAR", bandar);
			r.add("NEGERI", negeri);
			r.add("EMEL", emel);
			r.add("NO_HP", no_hp);
			r.add("SEBAB", sebab);
			r.add("ID_FAIL", id_fail);
			r.add("NO_FAIL", no_fail);
			r.add("NO_KP_BARU", no_kp_baru);
			r.add("TARIKH_HANTAR", r.unquote("sysdate"));
			r.add("ID_PERBICARAAN", id_perbicaraan);
			
			
			myLogger.info("Step 3 SYAFIQAH");
			sql = r.getSQLInsert("TBLPPKBANTAHANONLINE");
			System.out.println("TBLPPKBANTAHANONLINE-->>"+sql);
			stmt.executeUpdate(sql);
			
			String sql2 = "";
			sql2 = "UPDATE TBLPPKPERBICARAAN SET FLAG_BANTAHAN = 'Y', KETERANGAN_BANTAHAN = '"+nama_pembantah+" yang bernombor KP "+no_kp_baru+" telah membuat bantahan berdasarkan "+sebab+"' \r\n" + 
					"WHERE ID_PERBICARAAN ='"+id_perbicaraan+"'";
//					+ " \r\n" + 
//					"(SELECT id_perbicaraan\r\n" + 
//					"  FROM tblppkperbicaraan\r\n" + 
//					" WHERE id_keputusanpermohonan IN (\r\n" + 
//					"          SELECT id_keputusanpermohonan\r\n" + 
//					"            FROM tblppkkeputusanpermohonan\r\n" + 
//					"           WHERE id_permohonan =\r\n" + 
//					"                    (SELECT id_permohonan\r\n" + 
//					"                       FROM tblppkpermohonan\r\n" + 
//					"                      WHERE id_fail =\r\n" + 
//					"                                 (SELECT id_fail\r\n" + 
//					"                                    FROM tblpfdfail\r\n" + 
//					"                                   WHERE no_fail = '"+no_fail+"'))) )";
			
			try {
				db2 = new Db();
				Statement stmt2  = db.getStatement();
				ResultSet rs2 = stmt2.executeQuery(sql2);
				
			} finally {
				if (db2 != null)
					db2.close();
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
		
	public void insertPertukaranPemohon(Hashtable data) throws Exception {
		Db db = null;
		Db db2 = null;
		Db db3 = null;
		
		String sql = "";
		try {
			String id_permohonan = (String) data.get("id_permohonan");
			String no_fail = (String) data.get("no_fail");
			String id_simati = (String) data.get("id_simati");
			String id_pemohonlama = (String) data.get("id_pemohonlama");
			String id_pemohonbaru = (String) data.get("id_pemohonbaru");
			String sebab_tukar = (String) data.get("sebab_tukar");
			String id_permohonansimati = (String) data.get("id_permohonansimati");
			String tarikhmati_pemohon = (String) data.get("tarikhmati_pemohon");
			String tarikh_hantar = (String) data.get("tarikh_hantar");
			String id_masuk = (String) data.get("id_masuk");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//r.add("ID_PERMOHONAN", id_permohonan);
			//r.add("NO_FAIL", no_fail);
			r.add("ID_SIMATI", id_simati);
			r.add("ID_PEMOHONLAMA", id_pemohonlama);
			r.add("ID_PEMOHONBARU", id_pemohonbaru);
			r.add("SEBAB_TUKAR", sebab_tukar);
			r.add("ID_PERMOHONANSIMATI", id_permohonansimati);
			//r.add("TARIKH_MATI_P", tarikhmati_pemohon);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_MASUK", id_masuk);
			
			
			myLogger.info("Step 3 SYAFIQAH");
			sql = r.getSQLInsert("TBLPPKTUKARPEMOHON");
			System.out.println("TBLPPKTUKARPEMOHONLINE-->>"+sql);
			stmt.executeUpdate(sql);
			
				
			String sql2 = "";
			sql2 ="UPDATE TBLPPKOB SET TARIKH_MATI = '"+ tarikhmati_pemohon +"'"
					+ "WHERE ID_PEMOHON = '"+ id_pemohonlama +"' AND ID_SIMATI = '"+id_simati+"'";
			
			try {
				db2 = new Db();
				Statement stmt2  = db.getStatement();
				ResultSet rs2 = stmt2.executeQuery(sql2);
				myLogger.info("Step 4 syafiqah:" + sql2);
				
			} finally {
				if (db2 != null)
					db2.close();
			}
			
			String sql3 = "";
			sql3 ="UPDATE TBLPPKOBPERMOHONAN SET TARIKH_MATI = '"+ tarikhmati_pemohon +"'"
					+ "WHERE ID_PEMOHON = '"+ id_pemohonlama +"' AND ID_SIMATI = '"+id_simati+"'";
			
			try {
				db2 = new Db();
				Statement stmt3  = db.getStatement();
				ResultSet rs3 = stmt3.executeQuery(sql3);
				myLogger.info("Step 5 syafiqah:" + sql3);
				
			} finally {
				if (db3 != null)
					db3.close();
			}
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	// syafiqah add ends

	public void kemaskiniHa(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String iduser = (String) data.get("iduser");
			String id1 = (String) data.get("id1");
			String id3 = (String) data.get("id3");
			// int socJenisHartaAlih =
			// Integer.parseInt((String)data.get("socJenisHartaAlih"));
			String txtBhgnSimati1 = (String) data.get("basimati");
			String txtBhgnSimati2 = (String) data.get("bbsimati");
			String txtNoRujukan = ((String) data.get("txtNoRujukan"))
					.toUpperCase();
			String txtNoSijil = ((String) data.get("txtNoSijil")).toUpperCase();
			double txtNilaiTarikhMohon = (Double) data
					.get("txtNilaiTarikhMohon");
			double txtNilaiTarikhMati = (Double) data.get("txtNilaiTarikhMati");
			String txtBilUnit = (String) data.get("txtBilUnit");
			String txtNilaiSeunit = (String) data.get("txtNilaiSeunit");
			String Agensi = ((String) data.get("Agensi")).toUpperCase();
			String txtCatatan = ((String) data.get("txtCatatan")).toUpperCase();
			String bil = (String) data.get("bil");
			Double valuenilaimohon = 0.00;
			Double valuenilaimati = 0.00;
			// valuenilaimohon =
			// Double.parseDouble((String)data.get("txtNilaiTarikhMohon"));
			String alamat1 = ((String) data.get("alamat1")).toUpperCase();
			String alamat2 = ((String) data.get("alamat2")).toUpperCase();
			String alamat3 = ((String) data.get("alamat3")).toUpperCase();
			String poskod = (String) data.get("poskod");
			String idnegeri = (String) data.get("idnegeri");
			String iddaerah = (String) data.get("iddaerah");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_simati", id1);
			r.update("id_ha", id3);
			r.add("bil", bil);
			// r.add("id_jenisha",socJenisHartaAlih);
			r.add("jenama", Agensi);
			r.add("no_daftar", txtNoRujukan);
			r.add("no_sijil", txtNoSijil);
			if (txtBilUnit != "") {
				r.add("bil_unit", Integer.parseInt(txtBilUnit));
			}
			r.add("nilai_ha_tarikhmati", txtNilaiTarikhMati);
			r.add("nilai_ha_tarikhmohon", txtNilaiTarikhMohon);
			r.add("ba_simati", Integer.parseInt(txtBhgnSimati1));
			r.add("bb_simati", Integer.parseInt(txtBhgnSimati2));
			// r.add("nilai_seunit",txtNilaiSeunit);
			r.add("catatan", txtCatatan);
			r.add("alamat_ha1", alamat1);
			r.add("alamat_ha2", alamat2);
			r.add("alamat_ha3", alamat3);
			r.add("poskod", poskod);
			r.add("id_daerah", iddaerah);
			r.add("id_negeri", idnegeri);
			r.add("id_kemaskini", iduser);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppkha");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addPenghutang(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try {
			String iduser = (String) data.get("iduser");
			long idpenghutang = DB.getNextID("TBLPPKPENGHUTANG_SEQ");
			String idsimati = (String) data.get("idSimati");
			String namaob = ((String) data.get("namaOB")).toUpperCase();
			String jenishutang = (String) data.get("jenishutang");
			double nilaihutang = (Double) data.get("nilaihutang");
			String butiranhutang = ((String) data.get("butiranhutang"))
					.toUpperCase();
			String noakaun = (String) data.get("noakaun");
			String nokpbaru = (String) data.get("nokpbaru");
			String nokppenting = (String) data.get("nokppenting");
			String jenisKp = (String) data.get("jenisKp");
			String nokplain = (String) data.get("nokplain");
			String alamat1 = ((String) data.get("alamat1")).toUpperCase();
			String alamat2 = ((String) data.get("alamat2")).toUpperCase();
			String alamat3 = ((String) data.get("alamat3")).toUpperCase();
			String poskod = (String) data.get("poskod");
			String bandar = ((String) data.get("bandar")).toUpperCase();
			int id_bandar = (Integer) data.get("id_bandar");
			int negeri = (Integer) data.get("negeri");
			String idpermohonansimati = (String) data.get("idpermohonansimati");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_penghutang", idpenghutang);
			r.add("id_Simati", idsimati);
			r.add("nama_Penghutang", namaob);
			// if (jenishutang == "2"){
			r.add("no_Kp_Baru", nokpbaru);
			// }else if (jenishutang == "1"){
			// r.add("no_Kp_Baru", "");
			// }
			r.add("no_Kp_Lain", nokplain);
			r.add("no_Kp_Lama", nokppenting);
			r.add("jenis_Kp", jenisKp);
			r.add("jenis_Penghutang", jenishutang);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_bandar", id_bandar);
			r.add("poskod", poskod);
			if (negeri != 0) {
				r.add("id_Negeri", negeri);
			}
			r.add("no_Akaun", noakaun);
			r.add("butiran_Hutang", butiranhutang);
			if (nilaihutang != 0) {
				r.add("jumlah_Hutang", nilaihutang);
			}
			r.add("id_permohonansimati", idpermohonansimati);
			r.add("id_masuk", iduser);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppkpenghutang");
			System.out.println("sql add penghutang---" + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getCheckDataPenghutang(String nokp) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select count(no_kp_baru) as cnt from tblppkpenghutang where no_kp_baru like '"
					+ nokp + "' or no_kp_lama like '" + nokp + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v1 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("cntid", rs.getString("cnt") == null ? "" : rs
						.getString("cnt"));
				v1.addElement(h);
			}
			return v1;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListNegeriByPpkUnit() throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select n.id_negeri,n.nama_negeri from tblrujpejabatjkptg p, tblppkrujunit u, "
					+ "tblrujnegeri n, tblrujdaerah d where p.id_pejabatjkptg = u.id_jkptg and u.id_negeri = n.id_negeri "
					+ "and p.id_daerah = d.id_daerah group by n.id_negeri,n.nama_negeri order by n.id_negeri";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v2 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idnegeri", rs.getString("id_negeri") == null ? "" : rs
						.getString("id_negeri"));
				h.put("namanegeri", rs.getString("nama_negeri") == null ? ""
						: rs.getString("nama_negeri"));
				v2.addElement(h);
			}
			return v2;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListNegeriByhta(String idpermohonansimati)
			throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "select distinct n.id_negeri, n.nama_negeri from tblppkhta a, tblrujnegeri n where a.id_negeri = n.id_negeri and a.id_permohonansimati = "
					+ idpermohonansimati + "";
			// System.out.println("Check SQL :"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Vector v2 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idnegeri", rs.getString("id_negeri") == null ? "" : rs
						.getString("id_negeri"));
				h.put("namanegeri", rs.getString("nama_negeri") == null ? ""
						: rs.getString("nama_negeri"));
				v2.addElement(h);
			}
			return v2;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListDaerahByhta(int idnegeri, String idsimati)
			throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "select distinct n.id_daerah, n.nama_daerah from tblppkhta a, tblrujdaerah n where a.id_daerah = n.id_daerah and a.id_simati = '"
					+ idsimati + "' and a.id_negeri = '" + idnegeri + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Vector v2 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getString("id_daerah") == null ? "" : rs
						.getString("id_daerah"));
				h.put("nama", rs.getString("nama_daerah") == null ? "" : rs
						.getString("nama_daerah"));
				v2.addElement(h);
			}
			return v2;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListDaerahByPpkUnit(int id) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "select distinct(d.id_daerah),d.kod_daerah,d.nama_daerah from tblrujnegeri n, tblrujdaerah d, tblppkrujunit u, "
					+ "tblrujpejabatjkptg p where n.id_negeri = d.id_negeri and n.id_negeri = u.id_negeri and d.id_daerah = p.id_daerah "
					+ "and n.id_negeri = " + id + " ";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v2 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("iddaerah", rs.getString("id_daerah") == null ? "" : rs
						.getString("id_daerah"));
				h.put("namadaerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));
				v2.addElement(h);
			}
			return v2;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getAddress(int idDaerah) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "select nama_pejabat,alamat1,alamat2,alamat3,poskod, no_tel, no_fax from tblRujPejabatJKPTG  where id_pejabatjkptg in "
					+ "(select distinct id_pejabatjkptg from TBLRUJPEJABATURUSAN where id_daerahurus = "
					+ idDaerah + " and id_jenispejabat=22 and  ID_SEKSYEN =2)";
			myLogger.info("SQL getAddress :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Vector v2 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("namapejabat", rs.getString("nama_pejabat") == null ? ""
						: rs.getString("nama_pejabat"));
				h.put("alamatOne", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamatTwo", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamatThree", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("notel", rs.getString("no_tel") == null ? "" : rs
						.getString("no_tel"));
				h.put("nofax", rs.getString("no_fax") == null ? "" : rs
						.getString("no_fax"));
				v2.addElement(h);
			}
			return v2;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector checkwaris(String kpBaru, String kpLama,
			String kpJenis, String kpLain, String idPermohonan)
			throws Exception {
		Db db = null;
		Vector listPenting = new Vector();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String checkKpBaru = kpBaru.trim();
			String checkKpLama = kpLama.trim();
			String checkKpJenis = kpJenis.trim();
			String checkKpLain = kpLain.trim();

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
			r.add("ob.tarikh_mati");
			r.add("ob.tarikh_mati");
			r.add("ob.waktu_kematian");
			r.add("ob.catatan");

			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.id_Simati", r.unquote("m.id_Simati"));

			// r.add("ob.id_simati",simati);

			r.add("m.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", idPermohonan);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p");

			String sqlwhere = "";
			// KP BARU
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + " UPPER(OB.NO_KP_BARU) LIKE '%"
							+ checkKpBaru.toUpperCase() + "%'";
				}
			}
			// KP LAMA
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere
								+ " OR UPPER(OB.NO_KP_LAMA) LIKE '%"
								+ checkKpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(OB.NO_KP_LAMA) LIKE '%"
								+ checkKpLama.toUpperCase() + "%'";
					}
				}
			}
			// KP JENIS
			if (kpJenis != "") {
				if (!kpBaru.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(OB.JENIS_KP) LIKE '%"
								+ checkKpJenis.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(OB.JENIS_KP) LIKE '%"
								+ checkKpJenis.toUpperCase() + "%'";
					}
				}
			}
			// KP LAIN
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere
								+ " OR UPPER(OB.NO_KP_LAIN) LIKE '%"
								+ checkKpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(OB.NO_KP_LAIN) LIKE '%"
								+ checkKpLain.toUpperCase() + "%'";
					}
				}
			}

			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere + " )";
			}
			sql = sql
					+ " and ob.id_tarafkptg <> 2 and ob.lapis = 1 and rownum < 2";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int count = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", count);
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
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
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
				h.put("statusOB", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("idtarafkptg", rs.getString("id_Tarafkptg") == null ? ""
						: rs.getString("id_Tarafkptg"));
				h.put("butiranhutang",
						rs.getString("butiran_Hutang") == null ? "" : rs
								.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang") == null ? ""
						: rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				h.put("tarikhmati", rs.getDate("tarikh_mati") == null ? ""
						: sdf.format(rs.getDate("tarikh_mati")));
				h.put("waktumati", rs.getString("waktu_kematian") == null ? ""
						: rs.getString("waktu_kematian"));
				listPenting.addElement(h);
				count++;
			}
			return listPenting;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int getCountWaris(String kpBaru, String kpLama,
			String kpJenis, String kpLain, String idPermohonan)
			throws Exception {
		Db db = null;
		Vector valWaris = new Vector();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String checkKpBaru = kpBaru.trim();
			String checkKpLama = kpLama.trim();
			String checkKpJenis = kpJenis.trim();
			String checkKpLain = kpLain.trim();

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select count(ob.id_Ob) as cntDataWaris from Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati m1 where ob.id_Simati= m1.id_Simati "
					+ "and m1.id_Permohonan = p.id_Permohonan(+) and m1.id_simati = m.id_simati and p.id_Permohonan = '"
					+ idPermohonan + "' ";

			String sqlwhere = "";
			// KP BARU
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + "AND UPPER(OB.NO_KP_BARU) LIKE '%"
							+ checkKpBaru.toUpperCase() + "%'";
				}
			}
			// KP LAMA
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere
								+ " OR UPPER(OB.NO_KP_LAMA) LIKE '%"
								+ checkKpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(OB.NO_KP_LAMA) LIKE '%"
								+ checkKpLama.toUpperCase() + "%'";
					}
				}
			}
			// KP JENIS
			if (kpJenis != "") {
				if (!kpBaru.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(OB.JENIS_KP) LIKE '%"
								+ checkKpJenis.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(OB.JENIS_KP) LIKE '%"
								+ checkKpJenis.toUpperCase() + "%'";
					}
				}
			}
			// KP LAIN
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere
								+ " OR UPPER(OB.NO_KP_LAIN) LIKE '%"
								+ checkKpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(OB.NO_KP_LAIN) LIKE '%"
								+ checkKpLain.toUpperCase() + "%'";
					}
				}
			}

			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere + " )";
			}
			sql = sql
					+ " and ob.id_tarafkptg <> 2 and ob.lapis = 1 and rownum < 2";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int count = 1;

			/*
			 * while(rs.next()) { h = new Hashtable(); h.put("cntWaris",
			 * rs.getString
			 * ("cntDataWaris")==null?"":rs.getString("cntDataWaris"));
			 * valWaris.addElement(h); count++; }
			 */

			if (rs.next()) {
				return rs.getString("cntDataWaris") == null ? 0 : Integer
						.parseInt(rs.getString("cntDataWaris"));
			} else
				return 0;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getCountLapisanWaris(String kpBaru, String kpLama,
			String kpJenis, String kpLain, String idPermohonan)
			throws Exception {
		Db db = null;
		Vector valLapisanWaris = new Vector();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String checkKpBaru = kpBaru.trim();
			String checkKpLama = kpLama.trim();
			String checkKpJenis = kpJenis.trim();
			String checkKpLain = kpLain.trim();

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select count(ob.id_Ob) as cntDataWaris from Tblppkob ob, Tblppksimati m, Tblppkpermohonan p where ob.id_Simati= m.id_Simati "
					+ "and m.id_Permohonan = p.id_Permohonan(+) and p.id_Permohonan = "
					+ idPermohonan + " ";
			// KP BARU
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + " AND UPPER(OB.NO_KP_BARU) LIKE '%"
							+ checkKpBaru.toUpperCase() + "%'";
				}
			}
			// KP LAMA
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					sql = sql + " AND UPPER(OB.NO_KP_LAMA) LIKE '%"
							+ checkKpLama.toUpperCase() + "%'";
				}
			}
			// KP JENIS
			if (kpJenis != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + " AND UPPER(OB.JENIS_KP) LIKE '%"
							+ checkKpJenis.toUpperCase() + "%'";
				}
			}
			// KP LAIN
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					sql = sql + " AND UPPER(OB.NO_KP_LAIN) LIKE '%"
							+ checkKpLain.toUpperCase() + "%'";
				}
			}
			sql = sql
					+ " and ob.id_tarafkptg <> 2 and ob.lapis <> 1 and rownum < 2";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int count = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("cntWaris", rs.getString("cntDataWaris") == null ? ""
						: rs.getString("cntDataWaris"));
				valLapisanWaris.addElement(h);
				count++;
			}
			return valLapisanWaris;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector checkLapisanwaris(String kpBaru, String kpLama,
			String kpJenis, String kpLain, String idPermohonan)
			throws Exception {
		Db db = null;
		Vector listPenting = new Vector();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String checkKpBaru = kpBaru.trim();
			String checkKpLama = kpLama.trim();
			String checkKpJenis = kpJenis.trim();
			String checkKpLain = kpLain.trim();

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
			r.add("ob.tarikh_mati");
			r.add("ob.tarikh_mati");
			r.add("ob.waktu_kematian");
			r.add("ob.catatan");

			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");
			r.add("ob.id_Simati", r.unquote("m.id_Simati"));

			// r.add("ob.id_simati",simati);

			r.add("m.id_Permohonan", r.unquote("p.id_Permohonan(+)"));

			r.add("p.id_Permohonan", idPermohonan);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p");
			// KP BARU
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + " AND UPPER(OB.NO_KP_BARU) LIKE '%"
							+ checkKpBaru.toUpperCase() + "%'";
				}
			}
			// KP LAMA
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					sql = sql + " AND UPPER(OB.NO_KP_LAMA) LIKE '%"
							+ checkKpLama.toUpperCase() + "%'";
				}
			}
			// KP JENIS
			if (kpJenis != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + " AND UPPER(OB.JENIS_KP) LIKE '%"
							+ checkKpJenis.toUpperCase() + "%'";
				}
			}
			// KP LAIN
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					sql = sql + " AND UPPER(OB.NO_KP_LAIN) LIKE '%"
							+ checkKpLain.toUpperCase() + "%'";
				}
			}
			sql = sql
					+ " and ob.id_tarafkptg <> 2 and ob.lapis <> 1 and rownum < 2";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int count = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", count);
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
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
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
				h.put("statusOB", rs.getString("status_Ob") == null ? "" : rs
						.getString("status_Ob"));
				h.put("idtarafkptg", rs.getString("id_Tarafkptg") == null ? ""
						: rs.getString("id_Tarafkptg"));
				h.put("butiranhutang",
						rs.getString("butiran_Hutang") == null ? "" : rs
								.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang") == null ? ""
						: rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				h.put("tarikhmati", rs.getDate("tarikh_mati") == null ? ""
						: sdf.format(rs.getDate("tarikh_mati")));
				h.put("waktumati", rs.getString("waktu_kematian") == null ? ""
						: rs.getString("waktu_kematian"));
				listPenting.addElement(h);
				count++;
			}
			return listPenting;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector setDataWarisSebelum(String id, String idsimati)
			throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector listWaris = new Vector();
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
			r.add("ob.jenis_Agama");
			r.add("ob.jenis_Warga");
			r.add("ob.catatan");
			r.add("ob.lapis");
			r.add("ob.tarikh_Mati");
			r.add("ob.no_Hp");
			r.add("ob.waktu_Kematian");
			r.add("h.id_parentob");

			r.add("ob.id_Simati", r.unquote("m1.id_Simati"));
			r.add("p.id_Permohonan", r.unquote("m1.id_Permohonan"));
			r.add("m1.id_simati", r.unquote("m.id_simati"));
			r.add("ob.id_ob", r.unquote("h.id_ob"));
			int k = 1;
			r.add("ob.id_Tarafkptg", k);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati m1, Tblppkhubunganob h");
			sql = sql + " and ob.id_ob = " + id + " and ob.id_simati = "
					+ idsimati + " and rownum < 2 ORDER BY ob.lapis";
			System.out.println("sql lapis sblm--" + sql);
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
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
				h.put("nokplama", rs.getString("no_Kp_Lama") == null ? "" : rs
						.getString("no_Kp_Lama"));
				h.put("jeniskp", rs.getString("jenis_Kp") == null ? "" : rs
						.getString("jenis_Kp"));
				h.put("nokplain", rs.getString("no_Kp_Lain") == null ? "" : rs
						.getString("no_Kp_Lain"));
				h.put("dob", rs.getDate("tarikh_Lahir") == null ? "" : sdf
						.format(rs.getDate("tarikh_Lahir")));
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
						: sdf.format(rs.getDate("tarikh_Mati")));
				h.put("nohp", rs.getString("no_Hp") == null ? "" : rs
						.getString("no_Hp"));
				h.put("lapis", rs.getString("lapis") == null ? "" : rs
						.getString("lapis"));
				h.put("idparentob", rs.getString("id_parentob") == null ? ""
						: rs.getString("id_parentob"));
				listWaris.addElement(h);
			}
			return listWaris;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector setDataPentingAgensi(String id) throws Exception {
		Db db = null;
		Vector listPenting = new Vector();
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
			r.add("ob.id_bandar");

			r.add("ob.butiran_Hutang");
			r.add("ob.nilai_Hutang");
			r.add("ob.no_Akaun");
			r.add("t.keterangan");

			r.add("ob.id_Simati", r.unquote("m1.id_Simati"));
			r.add("m1.id_Simati", r.unquote("m.id_Simati(+)"));
			r.add("m1.id_Permohonan", r.unquote("p.id_Permohonan(+)"));
			r.add("ob.id_Tarafkptg", r.unquote("t.id_Tarafkptg"));
			r.add("ob.id_Negeri", r.unquote("n.id_Negeri(+)"));

			// r.add("ob.id_jenispb",1);
			r.add("p.id_Permohonan", id);

			sql = r
					.getSQLSelect("Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati M1,Tblppkrujtarafkptg t, Tblrujnegeri n");
			System.out.println("sql papar ob--" + sql);
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
				h.put("nokpbaru1", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(0, 6));
				h.put("nokpbaru2", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(6, 8));
				h.put("nokpbaru3", rs.getString("no_Kp_Baru") == null ? "" : rs
						.getString("no_Kp_Baru").substring(8, 12));
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
				h.put("butiranhutang",
						rs.getString("butiran_Hutang") == null ? "" : rs
								.getString("butiran_Hutang"));
				h.put("nilaihutang", rs.getString("nilai_Hutang") == null ? ""
						: rs.getDouble("nilai_Hutang"));
				h.put("noakaun", rs.getString("no_Akaun") == null ? "" : rs
						.getString("no_Akaun"));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("idbandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				listPenting.addElement(h);
				count++;
			}
			return listPenting;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListsaudara(int gender) throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Saudara");
			r.add("kod");
			r.add("keterangan");
			r.add("jantina", gender);
			sql = r.getSQLSelect("Tblppkrujsaudara");
			sql = sql + " and id_saudara not in (61) order by keterangan";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Saudara", rs.getInt("id_Saudara"));
				if (rs.getString("kod") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod"));
				}
				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatARB() throws Exception {
		Db db = null;
		// String v="08";

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			listARB = new Vector();

			r.add("pej.nama_Pejabat");
			r.add("pej.id_Negeri");
			r.add("pej.id_Daerah");
			r.add("pej.id_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.kod_pejabat");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("pej.id_jenispejabat");
			r.add("pej.id_bandar");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
			r.add("b.Keterangan");
			// r.add("pej.id_jenispejabat",v);
			r.add("d.id_Daerah", r.unquote("pej.id_Daerah"));
			r.add("n.id_Negeri", r.unquote("pej.id_Negeri"));
			r.add("pej.id_Bandar", r.unquote("b.id_Bandar(+)"));
			r.add("pej.id_jenispejabat", "9");

			sql = r
					.getSQLSelect("Tblrujpejabat pej, Tblrujdaerah d, Tblrujnegeri n, Tblrujbandar b");// ,
																										// Tblrujdaerah
																										// d,
																										// Tblrujnegeri
																										// n");
			sql += "ORDER BY NAMA_NEGERI";

			System.out.println("SQL ARB :" + sql.toUpperCase()); // sql =
																	// "SELECT kp.id_Permohonan, pej.nama_Pejabat  FROM Tblppkkeputusanpermohonan kp, Tblrujpejabat pej, Tblppkpermohonan p, Tblrujdaerah d WHERE kp.id_Permohonan = p.id_Permohonan  AND p.id_Permohonan = 323  AND pej.jenis_pejabat = '08'  AND kp.id_Daerah_Mahkamah = d.id_Daerah  AND d.id_Daerah = pej.id_Daerah";
			// sql =
			// "select '123' as id_Permohonan,'AJAE TEST' as nama_Pejabat from DUAL";
			// System.out.println("MAHKLAMAH UUUUUU:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// //System.out.println("ada rekod...");
				Hashtable h = new Hashtable();
				// h.put("id_Permohonan",
				// rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				// h.put("id_Permohonan",
				// rs.getString(1)==null?"":rs.getString(1));

				h.put("nama_pejabat", rs.getString("nama_Pejabat") == null ? ""
						: rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabat") == null ? ""
						: rs.getString("id_Pejabat"));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));

				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("no_tel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax") == null ? "" : rs
						.getString("no_Fax"));

				h.put("daerah", rs.getString("nama_Daerah") == null ? "" : rs
						.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri") == null ? "" : rs
						.getString("nama_Negeri"));

				h.put("iddaerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));

				h.put("jenispejabat",
						rs.getString("id_jenispejabat") == null ? "" : rs
								.getString("id_jenispejabat"));

				h.put("kodpejabat", rs.getString("kod_pejabat") == null ? ""
						: rs.getString("kod_pejabat"));

				h.put("idbandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				h.put("namabandar", rs.getString("Keterangan") == null ? ""
						: rs.getString("Keterangan"));

				// r.add("pej.id_jenispejabat");
				/*
				 * r.add("kp.id_Permohonan"); r.add("pej.nama_Pejabat");
				 * r.add("pej.alamat1"); r.add("pej.alamat2");
				 * r.add("pej.alamat3"); r.add("pej.poskod");
				 * r.add("pej.no_Tel"); r.add("pej.no_Fax");
				 * r.add("d.nama_Daerah"); r.add("n.nama_Negeri");
				 */

				listARB.addElement(h);
			}
		}

		finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getMaklumatARB() {

		return listARB;
	}

	public void setMaklumatBaitulmal() throws Exception {
		Db db = null;
		// String v="08";

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			listBaitul = new Vector();

			r.add("pej.nama_Pejabat");
			r.add("pej.id_Negeri");
			r.add("pej.id_Daerah");
			r.add("pej.id_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.kod_pejabat");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("pej.id_jenispejabat");
			r.add("pej.id_bandar");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
			r.add("b.Keterangan");
			// r.add("pej.id_jenispejabat",v);
			r.add("d.id_Daerah", r.unquote("pej.id_Daerah"));
			r.add("n.id_Negeri", r.unquote("pej.id_Negeri"));
			r.add("pej.id_Bandar", r.unquote("b.id_Bandar(+)"));
			r.add("pej.id_jenispejabat", "61");

			sql = r
					.getSQLSelect("Tblrujpejabat pej, Tblrujdaerah d, Tblrujnegeri n, Tblrujbandar b");// ,
																										// Tblrujdaerah
																										// d,
																										// Tblrujnegeri
																										// n");
			sql += "ORDER BY NAMA_NEGERI";

			// System.out.println("SQL BAITULMAL :"+sql.toUpperCase()); //sql =
			// "SELECT kp.id_Permohonan, pej.nama_Pejabat  FROM Tblppkkeputusanpermohonan kp, Tblrujpejabat pej, Tblppkpermohonan p, Tblrujdaerah d WHERE kp.id_Permohonan = p.id_Permohonan  AND p.id_Permohonan = 323  AND pej.jenis_pejabat = '08'  AND kp.id_Daerah_Mahkamah = d.id_Daerah  AND d.id_Daerah = pej.id_Daerah";
			// sql =
			// "select '123' as id_Permohonan,'AJAE TEST' as nama_Pejabat from DUAL";
			// System.out.println("MAHKLAMAH UUUUUU:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// //System.out.println("ada rekod...");
				Hashtable h = new Hashtable();
				// h.put("id_Permohonan",
				// rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				// h.put("id_Permohonan",
				// rs.getString(1)==null?"":rs.getString(1));

				h.put("nama_pejabat", rs.getString("nama_Pejabat") == null ? ""
						: rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabat") == null ? ""
						: rs.getString("id_Pejabat"));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));

				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));

				h.put("no_tel", rs.getString("no_Tel") == null ? "" : rs
						.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax") == null ? "" : rs
						.getString("no_Fax"));

				h.put("daerah", rs.getString("nama_Daerah") == null ? "" : rs
						.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri") == null ? "" : rs
						.getString("nama_Negeri"));

				h.put("iddaerah", rs.getString("id_Daerah") == null ? "" : rs
						.getString("id_Daerah"));
				h.put("idnegeri", rs.getString("id_Negeri") == null ? "" : rs
						.getString("id_Negeri"));

				h.put("jenispejabat",
						rs.getString("id_jenispejabat") == null ? "" : rs
								.getString("id_jenispejabat"));

				h.put("kodpejabat", rs.getString("kod_pejabat") == null ? ""
						: rs.getString("kod_pejabat"));

				h.put("idbandar", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				h.put("namabandar", rs.getString("Keterangan") == null ? ""
						: rs.getString("Keterangan"));

				// r.add("pej.id_jenispejabat");
				/*
				 * r.add("kp.id_Permohonan"); r.add("pej.nama_Pejabat");
				 * r.add("pej.alamat1"); r.add("pej.alamat2");
				 * r.add("pej.alamat3"); r.add("pej.poskod");
				 * r.add("pej.no_Tel"); r.add("pej.no_Fax");
				 * r.add("d.nama_Daerah"); r.add("n.nama_Negeri");
				 */

				listBaitul.addElement(h);
			}
		} catch (DbException e) {

			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getMaklumatBaitulmal() {
		return listBaitul;
	}

	public void insertDaerahMohon(int idnegeri, int iddaerah,
			String idpermohonan, String userid, String idFail) throws Exception {
		Db db = null;
		String sql = "";
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		int getYear = calendar.get(java.util.Calendar.YEAR);
		try {

			db = new Db();

			String X = String.format("%06d", File.getSeqNo(db, 2, 01, 18, 0, 0,
					false, false, 0, 0));
			String no_fail_online = "JKPTG/PK/01/" + getYear + "/" + X;

			Statement stmtG = db.getStatement();
			String sql8 = "Update tblrujsuburusanstatusfail set AKTIF = '0',ID_KEMASKINI='"
					+ userid
					+ "',TARIKH_KEMASKINI=sysdate where ID_PERMOHONAN = '"
					+ idpermohonan
					+ "' and ID_FAIL = '"
					+ idFail
					+ "' and AKTIF ='1'";
			stmtG.executeUpdate(sql8);

			// db = new Db();
			Statement stmtF = db.getStatement();
			SQLRenderer r5 = new SQLRenderer();
			r5.add("ID_SUBURUSANSTATUSFAIL", DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			r5.add("ID_PERMOHONAN", idpermohonan);
			r5.add("ID_SUBURUSANSTATUS", 614); // 436 status utk permohonan
												// online
			r5.add("AKTIF", 1);
			r5.add("ID_FAIL", idFail);
			r5.add("ID_MASUK", userid);
			r5.add("TARIKH_MASUK", r5.unquote("sysdate"));
			r5.add("ID_KEMASKINI", userid);
			r5.add("TARIKH_KEMASKINI", r5.unquote("sysdate"));
			String sql2 = r5.getSQLInsert("tblrujsuburusanstatusfail");
			stmtF.executeUpdate(sql2);


			// db = new Db();
			Statement stmtT = db.getStatement();
			sql = "update tblppkpermohonan set "
					+ "NO_PERMOHONAN_ONLINE = '"+ no_fail_online+"'"
					+ ", TARIKH_MOHON_ONLINE = sysdate, id_status = 171, id_negerimhn = "
					+ idnegeri + ",id_daerahmhn = " + iddaerah
					+ ",ID_MASUK = '" + userid
					+ "', TARIKH_MASUK = sysdate,  ID_KEMASKINI = '" + userid
					+ "', TARIKH_KEMASKINI = sysdate where id_permohonan = '"
					+ idpermohonan + "'";
			// System.out.println("sql-->>"+sql);
			stmtT.executeUpdate(sql);
			
			Statement stmtFail = db.getStatement();
			String noFail = getNoFail(db,String.valueOf(idnegeri),String.valueOf(iddaerah),X,getYear);
			sql = "update tblpfdfail set "
					+ "NO_FAIL = '"+ noFail+"'"
					+ " where id_fail="+idFail
					+ "";
				stmtFail.executeUpdate(sql);


		} finally {
			if (db != null)
				db.close();
		}
	}

	// added by cipon for fixing bugs when submitted for pengesahan cetak button
	// was not apprear
	public String getIdStatus(String idPermohonan) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT pp.id_status from tblppkpermohonan pp where PP.ID_PERMOHONAN = "
					+ idPermohonan;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getString("id_status");
			}
			return "";
		}

		catch (Exception e) {
			myLogger.fatal(e.getMessage());
		} finally {
			db.close();
		}
		return "";
	}

	public static Vector getListDaerahByPpkUnitSelected(int idnegeri,
			int iddaerah) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select d.id_daerah,d.nama_daerah from tblrujpejabatjkptg p, tblppkrujunit u, "
					+ "tblrujnegeri n, tblrujdaerah d where p.id_pejabatjkptg = u.id_jkptg and u.id_negeri = n.id_negeri "
					+ "and p.id_daerah = d.id_daerah and u.id_negeri = "
					+ idnegeri
					+ " and p.id_daerah = "
					+ iddaerah
					+ " "
					+ "group by d.id_daerah,d.nama_daerah order by d.id_daerah";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v2 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("iddaerah", rs.getString("id_daerah") == null ? "" : rs
						.getString("id_daerah"));
				h.put("namadaerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));
				v2.addElement(h);
			}
			return v2;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector semakDataPeguam(String idpermohonan) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select pg.id_peguam, pg.nama_firma, pg.no_rujukan_firma From tblppkpemohon p, tblppkpeguampemohon pp, "
					+ "tblppkpeguam pg Where p.id_pemohon = pp.id_pemohon and pp.id_peguam = pg.id_peguam and "
					+ "p.id_permohonan = " + idpermohonan + "";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v2 = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idpeguam", rs.getString("id_peguam") == null ? "" : rs
						.getString("id_peguam"));
				h.put("namafirma", rs.getString("nama_firma") == null ? "" : rs
						.getString("nama_firma"));
				h.put("rujukanfirma",
						rs.getString("no_rujukan_firma") == null ? "" : rs
								.getString("no_rujukan_firma"));
				v2.addElement(h);
			}
			return v2;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getExistData(String norujukan) throws Exception {
		Db db = null;
		String sql = "Select count(no_daftar) from Tblppkha where no_daftar = '"
				+ norujukan + "'";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("cntNoDaftar", rs.getString(1) == null ? "" : Double
						.parseDouble(rs.getString(1)));
				v.addElement(h);
				bil++;
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getIdFail(String idpermohonan) throws Exception {
		Db db = null;
		String sql = "Select id_fail from tblppkpermohonan where id_permohonan = '"
				+ idpermohonan + "'";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("noidfail", rs.getString(1) == null ? "" : rs
						.getString(1));
				v.addElement(h);
				bil++;
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getUserNegeri(int users_id) throws Exception {
		Db db = null;
		// String sql =
		// "Select alamat1,alamat2,alamat3,id_negeri,poskod from users_online where user_id = "+
		// users_id +"";

		String sql = "Select id_negeri from users_online where user_id = "
				+ users_id + "";
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				// h.put("onlinealamat1",
				// rs.getString(1)==null?"":rs.getString(1));
				// h.put("onlinealamat2",
				// rs.getString(2)==null?"":rs.getString(3));
				// h.put("onlinealamat3",
				// rs.getString(3)==null?"":rs.getString(3));
				h.put("userState", rs.getString(1) == null ? "" : rs
						.getString(1));
				// h.put("onlineposkod",
				// rs.getString(5)==null?"":rs.getString(5));
				v.addElement(h);
				bil++;
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getUserParticular(String users_id) throws Exception {
		Db db = null;
		String sql = "Select o.no_kp_baru, o.jantina, u.user_name from users_online o, "
				+ "users u where o.user_id = u.user_id and u.user_id = "
				+ users_id + "";
		myLogger.debug(sql);
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("icno1", rs.getString(1) == null ? "" : rs.getString(1)
						.substring(0, 6));
				h.put("icno2", rs.getString(1) == null ? "" : rs.getString(1)
						.substring(6, 8));
				h.put("icno3", rs.getString(1) == null ? "" : rs.getString(1)
						.substring(8, 12));
				h.put("gender", rs.getString(2) == null ? "" : rs.getString(2));
				h.put("username", rs.getString(3) == null ? "" : rs
						.getString(3));
				v.addElement(h);
				bil++;
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int cntWujudWarisDariPemohon(String nokpbaru,
			String nokplama, String nokplain, String nopermohonan)
			throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String kpBaru = nokpbaru.trim();
			String kpLama = nokplama.trim();
			String kpLain = nokplain.trim();
			// int permohonan = nopermohonan;
			sql = "Select count(a.id_ob) as cntWaris from tblppkob a, tblppkpermohonansimati b where a.id_permohonansimati=b.id_permohonansimati ";

			String sqlwhere = "";
			// KP BARU
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + "AND UPPER(a.NO_KP_BARU) LIKE '%"
							+ kpBaru.toUpperCase() + "%'";
				}
			}
			// KP LAMA
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(a.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(a.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					}
				}
			}
			// KP LAIN
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(a.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(a.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					}
				}
			}

			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere + " ) and b.id_permohonan = '"
						+ nopermohonan + "'";
			}

			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("sql check waris: " + sql);

			Vector v = new Vector();
			if (rs.next()) {
				return rs.getString("cntWaris") == null ? 0 : Integer
						.parseInt(rs.getString("cntWaris"));
			} else
				return 0;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getBandarByNegeri(String idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "Select id_bandar,kod_bandar,upper(keterangan) as keterangan from tblrujbandar where id_negeri = '"
					+ idnegeri + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idbandarnegeri", rs.getString("id_bandar") == null ? ""
						: rs.getString("id_bandar"));
				h.put("kodbandar", rs.getString("kod_bandar") == null ? "" : rs
						.getString("kod_bandar"));
				h.put("nama", rs.getString("keterangan") == null ? "" : rs
						.getString("keterangan"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int checkAlamatPemohon(String nokpbaru) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			String sql = "Select count(alamat1) as cntId from users_online where no_kp_baru = '"
					+ nokpbaru + "'";
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			if (rs.next()) {
				return rs.getString("cntId") == null ? 0 : Integer.parseInt(rs
						.getString("cntId"));
			} else
				return 0;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListOB() throws Exception {
		Db db = null;
		String sql = "Select id_tarafkptg,kod,keterangan from tblppkrujtarafkptg where id_tarafkptg not in (1,13,14)";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector v = new Vector();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idtarafkptg", rs.getString(1) == null ? "" : rs
						.getString(1));
				h.put("kod", rs.getString(2) == null ? "" : rs.getString(2));
				h.put("keterangan", rs.getString(3) == null ? "" : rs
						.getString(3));
				v.addElement(h);
				bil++;
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int getPemohonId(String nokpbaru, String nokplama,
			String nokplain) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			String sql = "Select count(alamat1) as cntId from tblppkpemohonan a, tblppkpermohonansimati b, tblppkob c"
					+ " where a.id_permohonan = ";
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			if (rs.next()) {
				return rs.getString("cntId") == null ? 0 : Integer.parseInt(rs
						.getString("cntId"));
			} else
				return 0;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getBandar() throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "Select id_bandar,kod_bandar,keterangan,id_negeri from tblrujbandar";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id", rs.getString("id_bandar") == null ? "" : rs
						.getString("id_bandar"));
				h.put("kodbandar", rs.getString("kod_bandar") == null ? "" : rs
						.getString("kod_bandar"));
				h.put("nama", rs.getString("keterangan") == null ? "" : rs
						.getString("keterangan"));
				h.put("idbandarnegeri", rs.getString("id_negeri") == null ? ""
						: rs.getString("id_negeri"));
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getBandarfromID(int bandar) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "Select keterangan from tblrujbandar where ID_BANDAR="+bandar;
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("keterangan", rs.getString("keterangan") == null ? "" : rs
						.getString("keterangan"));
				
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static int getidBandar(String namadaerah, String idnegeri)
			throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			String sql = "select id_bandar from tblrujbandar where keterangan like '%"
					+ namadaerah + "%' and id_negeri = '" + idnegeri + "'";
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			if (rs.next()) {
				return rs.getString("id_bandar") == null ? 0 : Integer
						.parseInt(rs.getString("id_bandar"));
			} else
				return 0;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int checkSimatiSeksyen(String kpbarusimati,
			String kplamasimati, String kplainsimati,
			String noPermohonansimati, String userid) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		int cnt = 0;

		// Vector resultSimati = new Vector();
		try {
			db = new Db();
			String kpBaru = kpbarusimati.trim();
			String kpLama = kplamasimati.trim();
			String kpLain = kplainsimati.trim();
			String noPermohonan = noPermohonansimati.trim();
			String sql = "Select count(p.ID_SIMATI) as cntId from TBLPPKSIMATI P,TBLPFDFAIL FF, TBLPPKPERMOHONAN PP, TBLPPKPERMOHONANSIMATI M where "
					+ "PP.ID_PERMOHONAN = M.ID_PERMOHONAN AND M.ID_SIMATI = P.ID_SIMATI "
					+ "AND PP.ID_STATUS = 21 ";

			String sqlwhere = "";

			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(P.NO_KP_BARU) LIKE '%"
							+ kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					}

				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					}
				}
			}
			if (noPermohonan != "") {
				if (!noPermohonan.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(FF.NO_FAIL) = '"
								+ noPermohonan.toUpperCase() + "'";
					} else {
						sqlwhere = sqlwhere + " UPPER(FF.NO_FAIL) = '"
								+ noPermohonan.toUpperCase() + "'";
					}
				}
			}

			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere + " )";
			}
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("sql17---" + sql);
			myLogger.info("getNoFail :: " + sql);

			// Hashtable h;

			if (rs.next()) {

				cnt = rs.getString("cntId") == null ? 0 : Integer.parseInt(rs
						.getString("cntId"));

			} else
				cnt = 0;

			// return resultSimati;
		} finally {
			if (db != null)
				db.close();

		}
		return cnt;

	}

	public static int getSek8Status(String kpbarusimati, String kplamasimati,
			String kplainsimati, String noPermohonansimati, int userid)
			throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector resultSimati = new Vector();
		try {
			db = new Db();
			String kpBaru = kpbarusimati.trim();
			String kpLama = kplamasimati.trim();
			String kpLain = kplainsimati.trim();
			String noPermohonan = noPermohonansimati.trim();
			String sql = "select * from (Select PP.ID_STATUS as cntId from TBLPPKSIMATI P, TBLPPKPERMOHONAN PP, TBLPPKPERMOHONANSIMATI M where "
					+ "PP.ID_PERMOHONAN = M.ID_PERMOHONAN AND M.ID_SIMATI = P.ID_SIMATI";

			String sqlwhere = "";

			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(P.NO_KP_BARU) LIKE '%"
							+ kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAMA) LIKE '%"
								+ kpLama.toUpperCase() + "%'";
					}

				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_KP_LAIN) LIKE '%"
								+ kpLain.toUpperCase() + "%'";
					}
				}
			}

			if (noPermohonan != "") {
				if (!noPermohonan.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere
								+ " OR UPPER(PP.NO_PERMOHONAN_ONLINE) = '"
								+ noPermohonan.toUpperCase() + "'";
					} else {
						sqlwhere = sqlwhere
								+ " UPPER(PP.NO_PERMOHONAN_ONLINE) = '"
								+ noPermohonan.toUpperCase() + "'";
					}
				}
			}

			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere
						+ " ) order by pp.tarikh_masuk desc) where rownum < 2";
			}
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			if (rs.next()) {
				return rs.getString("cntId") == null ? 0 : Integer.parseInt(rs
						.getString("cntId"));
			} else
				return 0;

			// return resultSimati;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getListsaudara1() throws Exception {
		Db db = null;
		// String sql =
		// "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Saudara");
			r.add("kod");
			r.add("keterangan");
			sql = r.getSQLSelect("Tblppkrujsaudara");
			sql = sql
					+ " where  id_saudara not in (61) order by jantina, keterangan";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_Saudara", rs.getInt("id_Saudara"));
				if (rs.getString("kod") == null) {
					h.put("kod", "");
				} else {
					h.put("kod", rs.getString("kod"));
				}
				if (rs.getString("keterangan") == null) {
					h.put("keterangan", "");
				} else {
					h.put("keterangan", rs.getString("keterangan"));
				}
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int checksameuser(int userid) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			String sql = "SELECT COUNT(*) AS cntId FROM TBLPPKPERMOHONAN WHERE ";
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			if (rs.next()) {
				return rs.getString("cntId") == null ? 0 : Integer.parseInt(rs
						.getString("cntId"));
			} else
				return 0;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static int getCountWarisLapisan(String kpBaru, String kpLama,
			String kpJenis, String kpLain, String idPermohonan)
			throws Exception {
		Db db = null;
		Vector valWaris = new Vector();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String checkKpBaru = kpBaru.trim();
			String checkKpLama = kpLama.trim();
			String checkKpJenis = kpJenis.trim();
			String checkKpLain = kpLain.trim();

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String sql = "Select count(ob.id_Ob) as cntDataWaris from Tblppkob ob, Tblppksimati m, Tblppkpermohonan p, Tblppkpermohonansimati m1 where ob.id_Simati= m1.id_Simati "
					+ "and m1.id_Permohonan = p.id_Permohonan(+) and m1.id_simati = m.id_simati and p.id_Permohonan = "
					+ idPermohonan + " ";

			String sqlwhere = "";
			// KP BARU
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sql = sql + "AND UPPER(OB.NO_KP_BARU) LIKE '%"
							+ checkKpBaru.toUpperCase() + "%'";
				}
			}
			// KP LAMA
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere
								+ " OR UPPER(OB.NO_KP_LAMA) LIKE '%"
								+ checkKpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(OB.NO_KP_LAMA) LIKE '%"
								+ checkKpLama.toUpperCase() + "%'";
					}
				}
			}
			// KP JENIS
			if (kpJenis != "") {
				if (!kpBaru.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(OB.JENIS_KP) LIKE '%"
								+ checkKpJenis.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(OB.JENIS_KP) LIKE '%"
								+ checkKpJenis.toUpperCase() + "%'";
					}
				}
			}
			// KP LAIN
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere
								+ " OR UPPER(OB.NO_KP_LAIN) LIKE '%"
								+ checkKpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(OB.NO_KP_LAIN) LIKE '%"
								+ checkKpLain.toUpperCase() + "%'";
					}
				}
			}

			if (sqlwhere != "") {
				sql = sql + " AND ( " + sqlwhere + " )";
			}
			sql = sql + " and ob.id_tarafkptg <> 2";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int count = 1;

			/*
			 * while(rs.next()) { h = new Hashtable(); h.put("cntWaris",
			 * rs.getString
			 * ("cntDataWaris")==null?"":rs.getString("cntDataWaris"));
			 * valWaris.addElement(h); count++; }
			 */

			if (rs.next()) {
				return rs.getString("cntDataWaris") == null ? 0 : Integer
						.parseInt(rs.getString("cntDataWaris"));
			} else
				return 0;

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private String getNoFail(Db db,String idNegeri,String idDaerah,String XX,int getYear) throws Exception {
		
		String X = String.format("%04d",File.getSeqNo(db,2,382,0,Integer.parseInt(idNegeri),Integer.parseInt(idDaerah),false,false,getYear,0));
		
//		if (idDaerah.length() < 1){
//			idDaerah = "0"+idDaerah;
//		}else{
//			idDaerah = idDaerah;
//		}
		Vector<Tblrujdaerah> vecDaerah = DB.getDaerahByIdDaerah(idDaerah);
		Tblrujdaerah rd = vecDaerah.get(0);

		if (idNegeri.length() < 1){
			idNegeri = "0"+idNegeri;
		}else{
			idNegeri = idNegeri;
		}
//		if (negeri.equals("")){
//			negeri = "0";
//		}
		String getFile = "JKPTG/PK/"+ idNegeri + "/"+ rd.getKodDaerah() + "/"+X+"/"+getYear;				
		return getFile;
		
	}
	
	
}
