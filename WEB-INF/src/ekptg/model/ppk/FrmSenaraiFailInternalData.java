package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.view.ppk.PendaftaranCheck;

//import org.apache.xalan.xsltc.runtime.Hashtable;

public class FrmSenaraiFailInternalData {

	private final SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	private final Vector list = new Vector();

	private final Vector list17 = new Vector();
	private final Vector list17Senarai = new Vector();

	static Logger myLogger = Logger.getLogger(PendaftaranCheck.class);
	private final Vector listKPSimati = new Vector();

	public void setListKPSimati(String idp, String kpbaru, String kplama,
			String kplain) throws Exception {
		Db db = null;
		listKPSimati.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, SM.NO_KP_LAIN "
					+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
					+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
					+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
					+ " AND P.ID_PERMOHONAN <> '" + idp + "'";

			if (!kpbaru.equals("")) {
				sql = sql + " AND UPPER(SM.NO_KP_BARU) LIKE '%"
						+ kpbaru.toUpperCase() + "%'";
				// sql = sql + "  OR UPPER(SM.NO_KP_LAMA) LIKE '" +
				// kplama.toUpperCase() + "'";
				// sql = sql + " OR UPPER(SM.NO_KP_LAIN) LIKE '" +
				// kplain.toUpperCase() + "')";
			}

			if (!kplama.equals("")) {
				sql = sql + " AND  UPPER(SM.NO_KP_LAMA) LIKE '"
						+ kplama.toUpperCase() + "'";
				// sql = sql + " OR  UPPER(SM.NO_KP_BARU) LIKE '%" +
				// kpbaru.toUpperCase() + "%'";
				// sql = sql + " OR UPPER(SM.NO_KP_LAIN) LIKE '" +
				// kplain.toUpperCase() + "')";
			}

			if (!kplain.equals("")) {
				sql = sql + " AND  UPPER(SM.NO_KP_LAIN) LIKE '"
						+ kplain.toUpperCase() + "'";
				// sql = sql + "  OR UPPER(SM.NO_KP_LAMA) LIKE '" +
				// kplama.toUpperCase() + "'";
				// sql = sql + " OR  UPPER(SM.NO_KP_BARU) LIKE '%" +
				// kpbaru.toUpperCase() + "%')";

			}

			// System.out.print("SQL XXXX Simati"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("no_Kp_Baru", rs.getString("no_Kp_Baru") == null ? ""
						: rs.getString("no_Kp_Baru"));
				h.put("no_Kp_Lama", rs.getString("no_Kp_Lama") == null ? ""
						: rs.getString("no_Kp_Lama"));
				h.put("no_Kp_Lain", rs.getString("no_Kp_Lain") == null ? ""
						: rs.getString("no_Kp_Lain"));
				listKPSimati.addElement(h);

			}
			// return list;
		} finally {
			if (db != null)
				db.close();

		}
	}

	// PEJE
	
	public boolean checkKPSimatiFromSenaraiSemak(String idp, String kpbaru, String kplama,
			String kplain) throws Exception {

		//System.out.println("NO KP LAMA SIMATI :" + kplama);

		Db db = null;
		boolean a = false;
		String jumlah_baru = "0";
		String jumlah_lama = "0";
		String jumlah_lain = "0";

		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();

			Statement stmt4 = db.getStatement();
			sql3 = "SELECT SM.ID_SIMATI FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, "
					+ " TBLPPKPERMOHONAN P "
					+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI "
					+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PERMOHONAN = '"
					+ idp + "'";
			System.out.println("SQL CHECK NO KP GET SIMATI " + sql3);
			ResultSet rs4 = stmt4.executeQuery(sql3);

			String id_simati_temp = "";
			while (rs4.next()) {
				id_simati_temp = rs4.getString("ID_SIMATI");
			}

			if (kpbaru != "") {
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_BARU "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						+ " AND P.ID_PERMOHONAN <> '"+idp+"'"
						//+ " AND SM.ID_SIMATI <> '" + id_simati_temp + "'"
						+ " AND SM.NO_KP_BARU = '" + kpbaru + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '47'"
						+ " AND P.ID_STATUS <> '50'"
						+ " AND P.ID_STATUS <> '70'"
						+ " AND P.ID_STATUS <> '169'"
						+ " AND P.ID_STATUS <> '171'"
						+ " AND P.ID_STATUS <> '150'"
						+ " AND P.ID_STATUS <> '160'";

				System.out.println("CHECK SQL 1:" + sql.toUpperCase());

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;

				/*
				 * while (rs.next()) { jumlah_baru =
				 * rs.getString("JUMLAH_BARU")==
				 * null?"":rs.getString("JUMLAH_BARU"); }
				 */
				String bil = "";
				if (rs.next()) {
					bil = rs.getString("JUMLAH_BARU");
					System.out.println("JUMLAH BARU = "+bil);
					if (rs.getInt("JUMLAH_BARU") > 0) {
						a = true;
					}
				}
				System.out.println("a1 = "+a);
			}

			if (kplama != "" && !kplama.equals("TDK")) {
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				sql1 = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAMA "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						+ " AND P.ID_PERMOHONAN <> '"+idp+"'"
						//+ " AND SM.ID_SIMATI <> '" + id_simati_temp + "'"
						+ " AND SM.NO_KP_LAMA = '" + kplama + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '47'"
						+ " AND P.ID_STATUS <> '50'"
						+ " AND P.ID_STATUS <> '70'"
						+ " AND P.ID_STATUS <> '169'"
						+ " AND P.ID_STATUS <> '171'"
						+ " AND P.ID_STATUS <> '150'"
						+ " AND P.ID_STATUS <> '160'";

				System.out.println("CHECK SQL 2:" + sql1.toUpperCase());

				ResultSet rs1 = stmt1.executeQuery(sql1);
				Hashtable h1;
				/*
				 * while (rs1.next()) { jumlah_lama =
				 * rs1.getString("JUMLAH_LAMA"
				 * )==null?"":rs1.getString("JUMLAH_LAMA"); }
				 */
				if (rs1.next()) {
					if (rs1.getInt("JUMLAH_LAMA") > 0) {
						a = true;
					}
				}

			}

			if (kplain != "") {
				Statement stmt2 = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				sql2 = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAIN "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						+ " AND P.ID_PERMOHONAN <> '"+idp+"'"
						//+ " AND SM.ID_SIMATI <> '" + id_simati_temp + "'"
						+ " AND SM.NO_KP_LAIN = '" + kplain + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '47'"
						+ " AND P.ID_STATUS <> '50'"
						+ " AND P.ID_STATUS <> '70'"
						+ " AND P.ID_STATUS <> '169'";

				System.out.println("CHECK SQL 3:" + sql2.toUpperCase());

				ResultSet rs2 = stmt2.executeQuery(sql2);
				Hashtable h2;
				/*
				 * while (rs2.next()) { jumlah_lain =
				 * rs2.getString("JUMLAH_LAIN"
				 * )==null?"":rs2.getString("JUMLAH_LAIN"); }
				 */
				if (rs2.next()) {
					if (rs2.getInt("JUMLAH_LAIN") > 0) {
						a = true;
					}
				}
			}

			/*
			 * if(Integer.parseInt(jumlah_baru)>0 ||
			 * Integer.parseInt(jumlah_lama)>0 ||
			 * Integer.parseInt(jumlah_lain)>0 ) { a = true; }
			 */

		} finally {
			if (db != null)
				db.close();

		}
		// return false;
		System.out.println("a = "+a);
		return a;
	}

	public boolean checkKPSimati(String idp, String kpbaru, String kplama,
			String kplain) throws Exception {

		System.out.println("NO KP LAMA SIMATI :" + kplama);

		Db db = null;
		boolean a = false;
		String jumlah_baru = "0";
		String jumlah_lama = "0";
		String jumlah_lain = "0";

		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();

			Statement stmt4 = db.getStatement();
			sql3 = "SELECT SM.ID_SIMATI FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, "
					+ " TBLPPKPERMOHONAN P "
					+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI "
					+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PERMOHONAN = '"
					+ idp + "'";
			System.out.println("SQL CHECK NO KP GET SIMATI" + sql3);
			ResultSet rs4 = stmt4.executeQuery(sql3);

			String id_simati_temp = "";
			while (rs4.next()) {
				id_simati_temp = rs4.getString("ID_SIMATI");
			}

			if (kpbaru != "") {
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_BARU "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						// +" AND P.ID_PERMOHONAN <> '"+idp+"'"
						+ " AND SM.ID_SIMATI <> '" + id_simati_temp + "'"
						+ " AND SM.NO_KP_BARU = '" + kpbaru + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '47'"
						+ " AND P.ID_STATUS <> '50'"
						+ " AND P.ID_STATUS <> '70'"
						+ " AND P.ID_STATUS <> '169'"
						+ " AND P.ID_STATUS <> '152'"
						+ " AND P.ID_STATUS <> '171'"
						+ " AND P.ID_STATUS <> '150'"
						+ " AND P.ID_STATUS <> '160'";

				System.out.println("CHECK SQL 1:" + sql.toUpperCase());

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;

				/*
				 * while (rs.next()) { jumlah_baru =
				 * rs.getString("JUMLAH_BARU")==
				 * null?"":rs.getString("JUMLAH_BARU"); }
				 */
				if (rs.next()) {
					if (rs.getInt("JUMLAH_BARU") > 0) {
						a = true;
					}
				}
			}
 
			if (kplama != "" && !kplama.equals("TDK")) {
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				sql1 = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAMA "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						// +" AND P.ID_PERMOHONAN <> '"+idp+"'"
						+ " AND SM.ID_SIMATI <> '" + id_simati_temp + "'"
						+ " AND SM.NO_KP_LAMA = '" + kplama + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '47'"
						+ " AND P.ID_STATUS <> '50'"
						+ " AND P.ID_STATUS <> '70'"
						+ " AND P.ID_STATUS <> '169'"
						+ " AND P.ID_STATUS <> '152'";

				System.out.println("CHECK SQL 2:" + sql1.toUpperCase());

				ResultSet rs1 = stmt1.executeQuery(sql1);
				Hashtable h1;
				/*
				 * while (rs1.next()) { jumlah_lama =
				 * rs1.getString("JUMLAH_LAMA"
				 * )==null?"":rs1.getString("JUMLAH_LAMA"); }
				 */
				if (rs1.next()) {
					if (rs1.getInt("JUMLAH_LAMA") > 0) {
						a = true;
					}
				}

			}

			if (kplain != "") {
				Statement stmt2 = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				sql2 = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAIN "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						// +" AND P.ID_PERMOHONAN <> '"+idp+"'"
						+ " AND SM.ID_SIMATI <> '" + id_simati_temp + "'"
						+ " AND SM.NO_KP_LAIN = '" + kplain + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '47'"
						+ " AND P.ID_STATUS <> '50'"
						+ " AND P.ID_STATUS <> '70'"
						+ " AND P.ID_STATUS <> '169'"
						+ " AND P.ID_STATUS <> '152'";

				System.out.println("CHECK SQL 3:" + sql2.toUpperCase());

				ResultSet rs2 = stmt2.executeQuery(sql2);
				Hashtable h2;
				/*
				 * while (rs2.next()) { jumlah_lain =
				 * rs2.getString("JUMLAH_LAIN"
				 * )==null?"":rs2.getString("JUMLAH_LAIN"); }
				 */
				if (rs2.next()) {
					if (rs2.getInt("JUMLAH_LAIN") > 0) {
						a = true;
					}
				}
			}

			/*
			 * if(Integer.parseInt(jumlah_baru)>0 ||
			 * Integer.parseInt(jumlah_lama)>0 ||
			 * Integer.parseInt(jumlah_lain)>0 ) { a = true; }
			 */

		} finally {
			if (db != null)
				db.close();

		}
		// return false;
		System.out.println("A = "+a);
		return a;
	}
	
	public boolean checkKPSimati2(String idp) throws Exception {

		System.out.println("checkKPSimati2");

		Db db = null;
		boolean a = false;
		String jumlah_baru = "0";
		String jumlah_lama = "0";
		String jumlah_lain = "0";

		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();

			Statement stmt4 = db.getStatement();
			sql3 = "SELECT SM.ID_SIMATI, SM.NO_KP_BARU, SM.NO_KP_LAMA, SM.NO_KP_LAIN FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, "
					+ " TBLPPKPERMOHONAN P "
					+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI "
					+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PERMOHONAN = '"
					+ idp + "'";
			System.out.println("SQL CHECK NO KP GET SIMATI" + sql3);
			ResultSet rs4 = stmt4.executeQuery(sql3);

			String id_simati_temp = "";
			String ic_simati_baru_temp = "";
			String ic_simati_lama_temp = "";
			String ic_simati_lain_temp = "";
			while (rs4.next()) {
				id_simati_temp = rs4.getString("ID_SIMATI");
				ic_simati_baru_temp = rs4.getString("NO_KP_BARU") == null ? "" : rs4.getString("NO_KP_BARU");
				ic_simati_lama_temp = rs4.getString("NO_KP_LAMA") == null ? "" : rs4.getString("NO_KP_LAMA");
				ic_simati_lain_temp = rs4.getString("NO_KP_LAIN");
			}
			System.out.println("ic_simati_lama_temp" + ic_simati_lama_temp);
			System.out.println("ic_simati_baru_temp" + ic_simati_baru_temp);
			if (ic_simati_baru_temp != "") {
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_BARU "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						// +" AND P.ID_PERMOHONAN <> '"+idp+"'"
						+ " AND SM.ID_SIMATI <> '" + id_simati_temp + "'"
						+ " AND SM.NO_KP_BARU = '" + ic_simati_baru_temp + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '47'"
						+ " AND P.ID_STATUS <> '50'"
						+ " AND P.ID_STATUS <> '70'"
						+ " AND P.ID_STATUS <> '169'"
						+ " AND P.ID_STATUS <> '152'"
						+ " AND P.ID_STATUS <> '171'"
						+ " AND P.ID_STATUS <> '150'"
						+ " AND P.ID_STATUS <> '160'";

				System.out.println("CHECK SQL 1:" + sql.toUpperCase());

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;

				/*
				 * while (rs.next()) { jumlah_baru =
				 * rs.getString("JUMLAH_BARU")==
				 * null?"":rs.getString("JUMLAH_BARU"); }
				 */
				if (rs.next()) {
					if (rs.getInt("JUMLAH_BARU") > 0) {
						a = true;
					}
				}
			}

			else if (ic_simati_lama_temp != "" && !ic_simati_lama_temp.equals("TDK")) {
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				sql1 = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAMA "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						// +" AND P.ID_PERMOHONAN <> '"+idp+"'"
						+ " AND SM.ID_SIMATI <> '" + id_simati_temp + "'"
						+ " AND SM.NO_KP_LAMA = '" + ic_simati_lama_temp + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '47'"
						+ " AND P.ID_STATUS <> '50'"
						+ " AND P.ID_STATUS <> '70'"
						+ " AND P.ID_STATUS <> '169'"
						+ " AND P.ID_STATUS <> '152'"
						+ " AND P.ID_STATUS <> '171'"
						+ " AND P.ID_STATUS <> '150'"
						+ " AND P.ID_STATUS <> '160'";

				System.out.println("CHECK SQL 2:" + sql1.toUpperCase());

				ResultSet rs1 = stmt1.executeQuery(sql1);
				Hashtable h1;
				/*
				 * while (rs1.next()) { jumlah_lama =
				 * rs1.getString("JUMLAH_LAMA"
				 * )==null?"":rs1.getString("JUMLAH_LAMA"); }
				 */
				if (rs1.next()) {
					if (rs1.getInt("JUMLAH_LAMA") > 0) {
						a = true;
					}
				}

			}

			else if (ic_simati_lain_temp != "") {
				Statement stmt2 = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				sql2 = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAIN "
						+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
						+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
						+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
						// +" AND P.ID_PERMOHONAN <> '"+idp+"'"
						+ " AND SM.ID_SIMATI <> '" + id_simati_temp + "'"
						+ " AND SM.NO_KP_LAIN = '" + ic_simati_lain_temp + "'"
						+ " AND P.ID_STATUS <> '999'"
						+ " AND P.ID_STATUS <> '47'"
						+ " AND P.ID_STATUS <> '50'"
						+ " AND P.ID_STATUS <> '70'"
						+ " AND P.ID_STATUS <> '169'"
						+ " AND P.ID_STATUS <> '152'"
						+ " AND P.ID_STATUS <> '171'"
						+ " AND P.ID_STATUS <> '150'"
						+ " AND P.ID_STATUS <> '160'";

				System.out.println("CHECK SQL 3:" + sql2.toUpperCase());

				ResultSet rs2 = stmt2.executeQuery(sql2);
				Hashtable h2;
				/*
				 * while (rs2.next()) { jumlah_lain =
				 * rs2.getString("JUMLAH_LAIN"
				 * )==null?"":rs2.getString("JUMLAH_LAIN"); }
				 */
				if (rs2.next()) {
					if (rs2.getInt("JUMLAH_LAIN") > 0) {
						a = true;
					}
				}
			}

			/*
			 * if(Integer.parseInt(jumlah_baru)>0 ||
			 * Integer.parseInt(jumlah_lama)>0 ||
			 * Integer.parseInt(jumlah_lain)>0 ) { a = true; }
			 */

		} finally {
			if (db != null)
				db.close();

		}
		// return false;
		System.out.println("A di dalam checkKPSimati2 = "+a);
		return a;
	}

	// PEJE

	public Vector getListKPSimati() {
		return listKPSimati;
	}

	Vector listNOFAIL = new Vector();

	public void setListNofail(String idfail, String nofail) throws Exception {
		Db db = null;
		listNOFAIL.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT F.NO_FAIL, F.TAJUK_FAIL, F.ID_FAIL FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P "
					+ " WHERE F.ID_FAIL <> '"
					+ idfail
					+ "' AND F.ID_FAIL = P.ID_FAIL(+) AND P.ID_STATUS <> '999'  ";

			if (!nofail.equals("")) {
				sql = sql + " and ( UPPER(NO_FAIL) LIKE '"
						+ nofail.trim().toUpperCase()
						+ "' OR UPPER(TAJUK_FAIL) LIKE '"
						+ nofail.trim().toUpperCase() + "')";

			}

			// System.out.print("SQL XXXX Fail"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("no_fail",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("tajuk_fail", rs.getString("TAJUK_FAIL") == null ? ""
						: rs.getString("TAJUK_FAIL"));
				// h.put("no_Kp_Lain",
				// rs.getString("no_Kp_Lain")==null?"":rs.getString("no_Kp_Lain"));
				listNOFAIL.addElement(h);

			}
			// return list;
		} finally {
			if (db != null)
				db.close();

		}
	}

	public Vector getListNofail() {
		return listNOFAIL;
	}

	public static Vector setList(String userid) throws Exception {
		Db db = null;

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			/*
			 * sql =
			 * "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
			 * +
			 * " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
			 * + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU" +
			 * " FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
			 * +
			 * " TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
			 * +" WHERE"
			 * +" D.id_daerah in ( select distinct u.id_daerahurus from" +
			 * " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
			 * +userid+"')" +" AND ST.ID_STATUS = S.ID_STATUS(+)"
			 * +" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
			 * +" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"
			 * +" AND A.ID_FAIL = F.ID_FAIL(+)"
			 * +" AND A.ID_DAERAHMHN = D.ID_DAERAH"
			 * +" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
			 * +" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
			 * +" AND P.ID_SIMATI = MS.ID_SIMATI" +
			 * " AND (STA.ID_SUBURUSANSTATUS = 340 OR STA.ID_SUBURUSANSTATUS = 342 OR STA.ID_SUBURUSANSTATUS = 553)"
			 * +" AND A.SEKSYEN = 8" +" AND STA.AKTIF = 1"
			 * +" AND (F.FLAG_JENIS_FAIL = 1 OR F.FLAG_JENIS_FAIL = 2)"
			 * +" AND A.FLAG_JENIS_PERMOHONAN = 1 " +""; sql = sql +
			 * " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
			 */

			sql = "SELECT DISTINCT A.ID_PERMOHONAN,STA.ID_SUBURUSANSTATUSFAIL,STA.ID_SUBURUSANSTATUS,STA.AKTIF,F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL, S.KETERANGAN, P.ID_SIMATI,"
					+ " P.NAMA_SIMATI, P.TEMPAT_MATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON, PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU,"
					+ " P.NO_KP_BARU"
					+ " FROM TBLRUJSUBURUSANSTATUSFAIL STA,TBLPPKPERMOHONAN A, TBLPFDFAIL F,"
					+ " TBLPPKPERMOHONANSIMATI MS,TBLPPKSIMATI P,TBLRUJDAERAH D,TBLRUJSTATUS S,"
					+ " TBLRUJSUBURUSANSTATUS ST,TBLPPKPEMOHON PM"
					+ " WHERE A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND A.ID_FAIL = F.ID_FAIL"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH(+)"
					+ " AND ST.ID_STATUS = S.ID_STATUS(+)"
					+ " AND A.ID_STATUS <> '999'"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					// +" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"
					+ " AND A.ID_PEMOHON= PM.ID_PEMOHON(+)"
					+ " AND (STA.ID_SUBURUSANSTATUS = 340 OR STA.ID_SUBURUSANSTATUS = 342 OR STA.ID_SUBURUSANSTATUS = 553)"
					+ " AND A.SEKSYEN = 8 AND STA.AKTIF = 1 AND (F.FLAG_JENIS_FAIL = 1 OR F.FLAG_JENIS_FAIL = 2) AND A.FLAG_JENIS_PERMOHONAN = 1"
					+ " AND D.ID_DAERAH IN"
					+ " (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userid + "'";
					
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					
					sql +=" )" + " AND f.no_fail is not null " +
					// "ORDER BY A.ID_DAERAHMHN, A.TARIKH_MOHON DESC";

					"ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";

			myLogger.info("senarai internal : " + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Vector list = new Vector();

			Hashtable h = null;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("tempatmati",rs.getString("tempat_mati") == null ? "" : rs
						.getString("id_Fail"));
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("noonline",
						rs.getString("no_Permohonan_Online") == null ? "" : rs
								.getString("no_Permohonan_Online"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk") == null ? ""
						: sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar",
						rs.getDate("tarikh_daftar_fail") == null ? "" : sdf
								.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("nokppemohon", rs.getString("no_Kp_Baru") == null ? ""
						: rs.getString("no_Kp_Baru"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? ""
						: rs.getString("id_Daerahmhn"));

				list.addElement(h);
				bil++;
			}
			return list;
		} finally {
			if (db != null)
				db.close();

		}
	}

	public static Vector setListRPP(String userid) throws Exception {
		Db db = null;

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM ( SELECT DISTINCT " +
					//"A.ID_PERMOHONAN," +
					"STA.ID_SUBURUSANSTATUSFAIL,STA.ID_SUBURUSANSTATUS,STA.AKTIF,F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL, S.KETERANGAN, P.ID_SIMATI,"
					+ " P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON, PM.NO_KP_BARU, " +
					" PM.ID_PEMOHON" +
					//" , " +
					//"P.NO_KP_BARU,"
					//+ " P.NO_KP_BARU "
					" FROM TBLRUJSUBURUSANSTATUSFAIL STA,TBLPPKPERMOHONAN A, TBLPFDFAIL F,"
					+ " TBLPPKPERMOHONANSIMATI MS,TBLPPKSIMATI P,TBLRUJDAERAH D,TBLRUJSTATUS S,"
					+ " TBLRUJSUBURUSANSTATUS ST,TBLPPKPEMOHON PM"
					+ " WHERE A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND A.ID_FAIL = F.ID_FAIL"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH(+)"
					+ " AND ST.ID_STATUS = S.ID_STATUS(+)"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					// +" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					// +" AND (STA.ID_SUBURUSANSTATUS = 340 OR STA.ID_SUBURUSANSTATUS = 342 OR STA.ID_SUBURUSANSTATUS = 553)"
					+ " AND (A.SEKSYEN = 8 OR A.SEKSYEN = 17)"
					// +"  AND STA.AKTIF = 1 "
					// +" AND (F.FLAG_JENIS_FAIL = 1 OR F.FLAG_JENIS_FAIL = 2)"
					+ " AND A.FLAG_JENIS_PERMOHONAN = 1"
					+ " AND D.ID_DAERAH IN"
					+ " (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userid
					+ "' ";
					
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					
					sql += " )"
					+ " AND f.no_fail is not null ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC) where rownum < 2000";

			myLogger.info("SQL REKOD PUSAKA" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Vector listRPP = new Vector();

			Hashtable h = null;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("noonline",
						rs.getString("no_Permohonan_Online") == null ? "" : rs
								.getString("no_Permohonan_Online"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk") == null ? ""
						: sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar",
						rs.getDate("tarikh_daftar_fail") == null ? "" : sdf
								.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("nokppemohon", rs.getString("no_Kp_Baru") == null ? ""
						: rs.getString("no_Kp_Baru"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? ""
						: rs.getString("id_Daerahmhn"));

				listRPP.addElement(h);
				bil++;
			}
			return listRPP;
		} finally {
			if (db != null)
				db.close();

		}
	}

	public static Vector setListKutipan(String userid) throws Exception {
		Db db = null;

		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			/*
			 * sql =
			 * "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
			 * +
			 * " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
			 * + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU" +
			 * " FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
			 * +
			 * " TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
			 * +" WHERE"
			 * +" D.id_daerah in ( select distinct u.id_daerahurus from" +
			 * " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
			 * +userid+"')" +" AND ST.ID_STATUS = S.ID_STATUS(+)"
			 * +" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
			 * +" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"
			 * +" AND A.ID_FAIL = F.ID_FAIL(+)"
			 * +" AND A.ID_DAERAHMHN = D.ID_DAERAH"
			 * +" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
			 * +" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
			 * +" AND P.ID_SIMATI = MS.ID_SIMATI" +
			 * " AND (STA.ID_SUBURUSANSTATUS = 340 OR STA.ID_SUBURUSANSTATUS = 342 OR STA.ID_SUBURUSANSTATUS = 553)"
			 * +" AND A.SEKSYEN = 8" +" AND STA.AKTIF = 1"
			 * +" AND F.FLAG_JENIS_FAIL = 3" +" AND A.FLAG_JENIS_PERMOHONAN = 1"
			 * +""; sql = sql +
			 * " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
			 */

			sql = "SELECT DISTINCT A.ID_PERMOHONAN,STA.ID_SUBURUSANSTATUSFAIL,STA.ID_SUBURUSANSTATUS,STA.AKTIF,F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL, S.KETERANGAN, P.ID_SIMATI,"
					+ " P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON, PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU,"
					+ " P.NO_KP_BARU, A.NO_SUBJAKET"
					+ " FROM TBLRUJSUBURUSANSTATUSFAIL STA,TBLPPKPERMOHONAN A, TBLPFDFAIL F,"
					+ " TBLPPKPERMOHONANSIMATI MS,TBLPPKSIMATI P,TBLRUJDAERAH D,TBLRUJSTATUS S,"
					+ " TBLRUJSUBURUSANSTATUS ST,TBLPPKPEMOHON PM"
					+ " WHERE A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND A.ID_FAIL = F.ID_FAIL"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH(+)"
					+ " AND ST.ID_STATUS = S.ID_STATUS(+)"
					+ " AND A.ID_STATUS <> '999'"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					// +" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					+ " AND (STA.ID_SUBURUSANSTATUS = 340 OR STA.ID_SUBURUSANSTATUS = 342 OR STA.ID_SUBURUSANSTATUS = 553)"
					+ " AND A.SEKSYEN = 8 AND STA.AKTIF = 1 "
					+ " AND (F.FLAG_JENIS_FAIL = 3)"
					+ " AND A.FLAG_JENIS_PERMOHONAN = 1"
					+ " AND D.ID_DAERAH IN"
					+ " (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ userid
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					
					sql +=" )"
					+ " AND f.no_fail is not null ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";

			// System.out.print("SQL XXXX1"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Vector list = new Vector();

			Hashtable h = null;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("noonline",
						rs.getString("no_Permohonan_Online") == null ? "" : rs
								.getString("no_Permohonan_Online"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk") == null ? ""
						: sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar",
						rs.getDate("tarikh_daftar_fail") == null ? "" : sdf
								.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("nokppemohon", rs.getString("no_Kp_Baru") == null ? ""
						: rs.getString("no_Kp_Baru"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? ""
						: rs.getString("id_Daerahmhn"));
				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));

				list.addElement(h);
				bil++;
			}
			return list;
		} finally {
			if (db != null)
				db.close();

		}
	}

	public Vector getList() {
		return list;
	}

	public Vector setList17Senarai(String userid) throws Exception {
		Db db = null;
		list17Senarai.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL,A.NO_SUBJAKET, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
					+ " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
					+ " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"
					+ " FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
					+ " TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
					+ " WHERE"
					+ " D.id_daerah in ( select distinct u.id_daerahurus from"
					+ " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
					+ userid
					+ "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					
					sql+=" )"
					+ " AND ST.ID_STATUS = S.ID_STATUS(+)"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"

					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					+ " AND A.ID_FAIL = F.ID_FAIL(+)"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"
					+ " AND (STA.ID_SUBURUSANSTATUS = 430 OR STA.ID_SUBURUSANSTATUS = 432 OR STA.ID_SUBURUSANSTATUS = 534)"
					+ " AND A.SEKSYEN = 17"
					+ " AND STA.AKTIF = 1"
					+ " AND (F.FLAG_JENIS_FAIL = 1 OR F.FLAG_JENIS_FAIL = 2)"
					+ " AND A.FLAG_JENIS_PERMOHONAN = 1" + "";
			sql = sql
					+ " AND f.no_fail is not null ORDER BY  A.TARIKH_MOHON DESC, A.ID_DAERAHMHN";

			myLogger.info("SQL 17 SENARAI XXXX sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk") == null ? ""
						: sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar",
						rs.getDate("tarikh_daftar_fail") == null ? "" : sdf
								.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("nokppemohon", rs.getString("no_Kp_Baru") == null ? ""
						: rs.getString("no_Kp_Baru"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? ""
						: rs.getString("id_Daerahmhn"));
				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));

				list17Senarai.addElement(h);
				bil++;
			}
			return list17Senarai;
		} finally {
			if (db != null)
				db.close();

		}
	}

	public Vector setList17(String userid) throws Exception {
		Db db = null;
		list17.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			sql = " SELECT ID_FAIL, NO_FAIL, NO_SUBJAKET, ID_PERMOHONAN, "+
                " TARIKH_MOHON, TARIKH_MASUK, TARIKH_DAFTAR_FAIL,"+
                " KETERANGAN, ID_SIMATI, NAMA_SIMATI, ID_DAERAHMHN,"+
                " TARIKH_MOHON_ONLINE, NO_PERMOHONAN_ONLINE,"+
                " NAMA_PEMOHON, NO_KP_BARU, ID_PEMOHON, NO_KP_BARU FROM"+
                " (";	
			sql += "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL,A.NO_SUBJAKET, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
					+ " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
					+ " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"
					+ " FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
					+ " TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
					+ " WHERE"
					+ " D.id_daerah in ( select distinct u.id_daerahurus from"
					+ " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
					+ userid
					+ "')"
					+ " AND A.ID_STATUS = S.ID_STATUS"
					+ " AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS"

					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON"
					+ " AND A.ID_FAIL = F.ID_FAIL"
					+ " AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+ " AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+ " AND P.ID_SIMATI = MS.ID_SIMATI"

					+ " AND A.ID_STATUS IN('21','47','56','70')"

					//+ " AND A.ID_STATUS <> '999'"
					+ " AND STA.AKTIF = 1"

					+ " AND A.FLAG_JENIS_PERMOHONAN = 1"
					+ " AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL)"
					+ "";
			sql +=  " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
			sql +=  " ) WHERE ROWNUM < 300 ";
*/
			
			
			sql+= "	      SELECT ID_FAIL, NO_FAIL, NO_SUBJAKET, ID_PERMOHONAN, TARIKH_MOHON, "+
					"       TARIKH_MASUK, TARIKH_DAFTAR_FAIL, KETERANGAN, ID_SIMATI, NAMA_SIMATI, "+
					"       ID_DAERAHMHN, TARIKH_MOHON_ONLINE, NO_PERMOHONAN_ONLINE, NAMA_PEMOHON, "+
					"       NO_KP_BARU, ID_PEMOHON, NO_KP_BARU "+
					"  FROM (SELECT DISTINCT A.ID_FAIL, A.NO_FAIL, A.NO_SUBJAKET, A.ID_PERMOHONAN, "+
					"                        A.TARIKH_MOHON, A.TARIKH_MASUK, A.TARIKH_DAFTAR_FAIL, "+
					"                        S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, "+
					"                        A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, "+
					"                        A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON, "+
					"                        PM.NO_KP_BARU, PM.ID_PEMOHON "+
					"                   FROM  "+
					"                   (                                     "+  
					"                   SELECT ID_STATUS,ID_PEMOHON,ID_PERMOHONAN, ID_FAIL, NO_FAIL, NO_SUBJAKET,TARIKH_MOHON, TARIKH_MASUK, TARIKH_DAFTAR_FAIL, "+
					"                   ID_DAERAHMHN, TARIKH_MOHON_ONLINE, NO_PERMOHONAN_ONLINE  FROM "+
					"                   ( "+
					"                   SELECT PP.ID_STATUS,PP.ID_PEMOHON,PP.ID_PERMOHONAN, FF.ID_FAIL, FF.NO_FAIL, PP.NO_SUBJAKET,PP.TARIKH_MOHON, PP.TARIKH_MASUK, FF.TARIKH_DAFTAR_FAIL, "+
					"                   PP.ID_DAERAHMHN, PP.TARIKH_MOHON_ONLINE, PP.NO_PERMOHONAN_ONLINE  "+
					"                   FROM  TBLPPKPERMOHONAN PP, TBLPFDFAIL FF  "+
					"                   WHERE PP.ID_FAIL = FF.ID_FAIL  "+
					"                    AND PP.FLAG_JENIS_PERMOHONAN = 1 "+
					"                                       AND (PP.FLAG_PERMOHONAN <> '1' OR PP.FLAG_PERMOHONAN IS NULL) "+
					"                                       AND FF.NO_FAIL IS NOT NULL   "+
					"                                       AND PP.ID_STATUS IN ('21', '47', '56', '70')  "+
					"                   AND  PP.ID_DAERAHMHN IN ( "+
					"                           SELECT DISTINCT U.ID_DAERAHURUS "+
					"                                      FROM TBLRUJPEJABATURUSAN U, "+
					"                                           USERS_INTERNAL UR "+
					"                                     WHERE U.ID_PEJABATJKPTG = "+
					"                                                            UR.ID_PEJABATJKPTG "+
					"                                       AND UR.USER_ID = '"+userid+"' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					
					
					sql += "                                       ) "+
					"                                       ORDER BY FF.ID_FAIL DESC)  "+
					"                                       WHERE ROWNUM < 250       ";  
					
					sql += "                   ) A, "+
					"                        TBLRUJSTATUS S, "+
					"                        TBLPPKSIMATI P, "+
					"                        TBLPPKPEMOHON PM, "+
					"                        TBLPPKPERMOHONANSIMATI MS, "+
					"                        TBLRUJSUBURUSANSTATUS ST, "+
					"                        TBLRUJSUBURUSANSTATUSFAIL STA, "+
					"                        TBLRUJDAERAH D "+
					"                  WHERE A.ID_STATUS = S.ID_STATUS "+
					"                    AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS "+
					"                    AND A.ID_PEMOHON = PM.ID_PEMOHON "+
					"                    AND A.ID_DAERAHMHN = D.ID_DAERAH "+
					"                    AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN "+
					"                    AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN "+
					"                    AND P.ID_SIMATI = MS.ID_SIMATI "+
					"                    AND STA.AKTIF = 1 ";

							      //sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
							      sql += "               ORDER BY A.ID_FAIL DESC "+
							    		  "               ) ";
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			myLogger.info("LIST 17 :" + sql.toUpperCase());
			// System.out.print("SQL 17 XXXX :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail",
						rs.getString("id_Fail") == null ? "" : rs
								.getString("id_Fail"));
				h.put("id_Pemohon", rs.getString("id_Pemohon") == null ? ""
						: rs.getString("id_Pemohon"));
				h.put("no_Fail",
						rs.getString("no_Fail") == null ? "" : rs
								.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon") == null ? ""
						: sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk") == null ? ""
						: sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar",
						rs.getDate("tarikh_daftar_fail") == null ? "" : sdf
								.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan", rs.getString("keterangan") == null ? ""
						: rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati") == null ? ""
						: rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon") == null ? ""
						: rs.getString("nama_Pemohon"));
				h.put("nokppemohon", rs.getString("no_Kp_Baru") == null ? ""
						: rs.getString("no_Kp_Baru"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn") == null ? ""
						: rs.getString("id_Daerahmhn"));
				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));

				list17.addElement(h);
				bil++;
			}
			return list17;
		} finally {
			if (db != null)
				db.close();

		}
	}

	public Vector getList17() {
		return list17;
	}

}
