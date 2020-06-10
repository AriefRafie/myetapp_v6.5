package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.view.ppk.PendaftaranCheck;

public class PendaftaranCheckModel {
	private static PendaftaranCheckModel instance = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLogger = Logger.getLogger(PendaftaranCheck.class);

	public static PendaftaranCheckModel getInstance() {
		if (instance == null)
			instance = new PendaftaranCheckModel();
		return instance;
	}
	String myaction = getParam("myaction");

	public boolean checkKP_Baru_Simati(String idp, String kpbaru,
			String kplama, String kplain) throws Exception {
		Db db = null;
		boolean a = false;
		String kp_baru = "";

		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*sql = " SELECT COUNT(P.ID_PERMOHONAN) AS C_SIMATI "
					+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
					+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
					+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
					+ " AND P.ID_PERMOHONAN <> '" + idp + "' ";*/
			
			//COMMENT BY PEJE - CHECKING PERMOHONAN WUJUD X JALAN B4 THIS
//			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
//					+ idp + "') <> '999' ";
//			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
//					+ idp + "') <> '169' ";
//			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
//					+ idp + "') <> '50' ";
//			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
//					+ idp + "') <> '47' ";
//			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
//					+ idp + "') <> '70' ";
//			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
//					+ idp + "') <> '152' ";
			
			/*sql = " SELECT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, " +
					" SM.NO_KP_LAMA, SM.NO_KP_LAIN, SM.JANTINA, SM.UMUR, SM.TARIKH_MATI, " +
					" SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI, " +
					" PEJ.NAMA_PEJABAT, PEJ.ID_DAERAH, PEJ.NAMA_DAERAH AS DAERAH_PEJABAT " +*/
			sql = " SELECT COUNT(P.ID_PERMOHONAN) AS C_SIMATI " +
					" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P, " +
					" TBLPFDFAIL F, TBLRUJDAERAH D, TBLRUJNEGERI N, " +
					" (SELECT DISTINCT PU.ID_DAERAHURUS,PEJ.ID_PEJABATJKPTG,PEJ.ID_DAERAH,D.NAMA_DAERAH,PEJ.NAMA_PEJABAT " +
					" FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJDAERAH D " +
					" WHERE PU.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_JENISPEJABAT = 22 " +
					" AND PEJ.ID_SEKSYEN = '2' AND PEJ.ID_DAERAH = D.ID_DAERAH) PEJ " +
					" WHERE SM.ID_SIMATI = MS.ID_SIMATI AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI(+) " +
					" AND PEJ.ID_DAERAHURUS(+) = P.ID_DAERAHMHN AND P.ID_DAERAHMHN = D.ID_DAERAH(+) " +
					" AND P.ID_STATUS NOT IN  (999,169,50,47,70,152) ";
						
			if (kpbaru != "") {
				sql += " AND SM.NO_KP_BARU = '" + kpbaru + "'";
			}
			//sql += " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND SM.FLAG_PERMOHONAN_ONLINE = '1'  AND P.ID_STATUS <> '152'";
			//sql += " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70'  AND P.ID_STATUS <> '152'";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;


			System.out.println("MATI 2:" + sql.toUpperCase());
			while (rs.next())
			//if (rs.next()) 
			{


			

				
				if (rs.getInt("C_SIMATI") > 1) {
					System.out.println("MATI 2xxx:"+ rs.getString("C_SIMATI"));
					a = true;
					System.out.println("A = "+a);
				}

			}
			/*
			 * 
			 * 
			 * while (rs.next()) { kp_baru
			 * =rs.getString("C_SIMATI")==null?"":rs.getString("C_SIMATI");
			 * 
			 * } if(Integer.parseInt(kp_baru)>0) { a = true; }
			 */
		} finally {
			if (db != null)
				db.close();

		}
		return a;
	}

	private String getParam(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkKP_Lama_Simati(String idp, String kpbaru,
			String kplama, String kplain) throws Exception {
		Db db = null;
		boolean a = false;
		String jumlah = "";

		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			/*sql = " SELECT COUNT(P.ID_PERMOHONAN)AS JUMLAH "
					+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
					+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
					+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
					+ " AND P.ID_PERMOHONAN <> '" + idp + "'";*/

			/*sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '999' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '169' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '50' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '47' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '70' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '152' ";*/

			/*sql = " SELECT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, " +
					" SM.NO_KP_LAMA, SM.NO_KP_LAIN, SM.JANTINA, SM.UMUR, SM.TARIKH_MATI, " +
					" SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI, " +
					" PEJ.NAMA_PEJABAT, PEJ.ID_DAERAH, PEJ.NAMA_DAERAH AS DAERAH_PEJABAT " +*/
			sql = " SELECT COUNT(P.ID_PERMOHONAN)AS JUMLAH "+
					" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P, " +
					" TBLPFDFAIL F, TBLRUJDAERAH D, TBLRUJNEGERI N, " +
					" (SELECT DISTINCT PU.ID_DAERAHURUS,PEJ.ID_PEJABATJKPTG,PEJ.ID_DAERAH,D.NAMA_DAERAH,PEJ.NAMA_PEJABAT " +
					" FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJDAERAH D " +
					" WHERE PU.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_JENISPEJABAT = 22 " +
					" AND PEJ.ID_SEKSYEN = '2' AND PEJ.ID_DAERAH = D.ID_DAERAH) PEJ " +
					" WHERE SM.ID_SIMATI = MS.ID_SIMATI AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI(+) " +
					" AND PEJ.ID_DAERAHURUS(+) = P.ID_DAERAHMHN AND P.ID_DAERAHMHN = D.ID_DAERAH(+) " +
					" AND P.ID_STATUS NOT IN  (999,169,50,47,70,152) ";
			
			if (kplama != "") {
				sql += " AND SM.NO_KP_LAMA = '" + kplama + "'";
			}

			//sql += " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND SM.FLAG_PERMOHONAN_ONLINE = '1' AND P.ID_STATUS <> '152'";
			//sql += " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";

			myLogger.info("MATI LAMA 2:" + sql.toUpperCase());

			// System.out.print("SQL CHECK MATI"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			if (rs.next()) {


				

				if (rs.getInt("JUMLAH") > 1) {
					myLogger.info("MATI LAMA 2xxx:"+ rs.getInt("JUMLAH"));

					a = true;

				}

			}

		} finally {
			if (db != null)
				db.close();
		}
		return a;
	}

	public boolean checkKP_Lain_Simati(String idp, String kpbaru,
			String kplama, String kplain) throws Exception {
		Db db = null;
		boolean a = false;
		String jumlah = "";

		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			/*sql = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH "
					+ " FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
					+ " WHERE SM.ID_SIMATI = MS.ID_SIMATI"
					+ " AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
					+ " AND P.ID_PERMOHONAN <> '" + idp + "'";*/

			/*sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '999' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '169' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '50' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '47' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '70' ";
			sql += " AND (SELECT ID_STATUS FROM TBLPPKPERMOHONAN  WHERE ID_PERMOHONAN = '"
					+ idp + "') <> '152' ";*/
			
			/*sql = " SELECT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, " +
					" SM.NO_KP_LAMA, SM.NO_KP_LAIN, SM.JANTINA, SM.UMUR, SM.TARIKH_MATI, " +
					" SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI, " +
					" PEJ.NAMA_PEJABAT, PEJ.ID_DAERAH, PEJ.NAMA_DAERAH AS DAERAH_PEJABAT " +*/
			sql = " SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH "+
					" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P, " +
					" TBLPFDFAIL F, TBLRUJDAERAH D, TBLRUJNEGERI N, " +
					" (SELECT PU.ID_DAERAHURUS,PEJ.ID_PEJABATJKPTG,PEJ.ID_DAERAH,D.NAMA_DAERAH,PEJ.NAMA_PEJABAT " +
					" FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJDAERAH D " +
					" WHERE PU.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_JENISPEJABAT = 22 " +
					" AND PEJ.ID_SEKSYEN = '2' AND PEJ.ID_DAERAH = D.ID_DAERAH) PEJ " +
					" WHERE SM.ID_SIMATI = MS.ID_SIMATI AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI(+) " +
					" AND PEJ.ID_DAERAHURUS(+) = P.ID_DAERAHMHN AND P.ID_DAERAHMHN = D.ID_DAERAH(+) " +
					" AND P.ID_STATUS NOT IN  (999,169,50,47,70,152) ";

			if (kplain != "") {
				sql += " AND SM.NO_KP_LAIN = '" + kplain + "'";
			}

			//sql += " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			if (rs.next()) {

				if (rs.getInt("JUMLAH") > 0) {

					a = true;

				}

			}

		} finally {
			if (db != null)
				db.close();

		}
		return a;
	}

	// ##
	Vector<Hashtable<String,String>> l1 = null;

	public Vector<Hashtable<String,String>> List_KP_Baru_Simati(String idp, String kpbaru, String kplama,
			String kplain) throws Exception {
		// Azam change on 02.02.2010
		// Check len first then check DB
		l1 = new Vector<Hashtable<String,String>>();
		if (kpbaru == null || "".equals(kpbaru) || kpbaru.length() < 12) {
			// Checking for valid new ic number
			// myLogger.debug("no need to check: "+kpbaru);
			return l1;
		} else {
			Db db = null;
			String sql = "";
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
//				SQLRenderer r = new SQLRenderer();

				/*sql = "SELECT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, "
						+ "SM.NO_KP_LAMA,SM.NO_KP_LAIN,SM.JANTINA,SM.UMUR ,SM.TARIKH_MATI, "
						+ "SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI,PEJ.NAMA_PEJABAT,PEJ.ID_DAERAH, "
						+ "DP.NAMA_DAERAH AS DAERAH_PEJABAT FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, "
						+ "TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PEJ, TBLPPKPERMOHONAN P,TBLPFDFAIL F,"
						+ "TBLRUJDAERAH D,TBLRUJDAERAH DP, TBLRUJNEGERI N WHERE SM.ID_SIMATI = MS.ID_SIMATI "
						+ "AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN "
						+ "AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI  "
						+ "AND UR.ID_DAERAHURUS = P.ID_DAERAHMHN "
						+ "AND UR.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG "
						+ "AND PEJ.ID_JENISPEJABAT <> '3' AND P.ID_DAERAHMHN = D.ID_DAERAH "
						+ " AND PEJ.ID_JENISPEJABAT = '22' AND PEJ.ID_SEKSYEN = '2' "
						+ " AND PEJ.ID_DAERAH = DP.ID_DAERAH "
						+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";*/

				sql = " SELECT DISTINCT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, " +
						" SM.NO_KP_LAMA, SM.NO_KP_LAIN, SM.JANTINA, SM.UMUR, SM.TARIKH_MATI, " +
						" SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI, " +
						" PEJ.NAMA_PEJABAT, PEJ.ID_DAERAH, PEJ.NAMA_DAERAH AS DAERAH_PEJABAT " +
						" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P, " +
						" TBLPFDFAIL F, TBLRUJDAERAH D, TBLRUJNEGERI N, " +
						" (SELECT PU.ID_DAERAHURUS,PEJ.ID_PEJABATJKPTG,PEJ.ID_DAERAH,D.NAMA_DAERAH,PEJ.NAMA_PEJABAT " +
						" FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJDAERAH D " +
						" WHERE PU.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_JENISPEJABAT = 22 " +
						" AND PEJ.ID_SEKSYEN = '2' AND PEJ.ID_DAERAH = D.ID_DAERAH) PEJ " +
						" WHERE SM.ID_SIMATI = MS.ID_SIMATI AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN " +
						" AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI(+) " +
						" AND PEJ.ID_DAERAHURUS(+) = P.ID_DAERAHMHN AND P.ID_DAERAHMHN = D.ID_DAERAH(+) " +
						" AND P.ID_STATUS NOT IN  (999,169,50,47,70) ";
				
				if (kpbaru != "") {
					sql += " AND SM.NO_KP_BARU = '" + kpbaru + "'";
				}	
				sql += " ORDER BY P.ID_PERMOHONAN ASC";
				myLogger.info("MATI CHECK 1 :" + sql.toUpperCase());
				System.out.println("test1 : " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String,String> h;
				while (rs.next()) {
					h = new Hashtable<String,String>();
					h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? "": rs.getString("NO_KP_BARU"));
					h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? "": rs.getString("NO_KP_LAMA"));
					h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? "": rs.getString("NO_KP_LAIN"));
					h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? "": rs.getString("JENIS_KP"));
					h.put("NAMA_SIMATI",rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
					h.put("JANTINA", rs.getString("JANTINA") == null ? "" : rs.getString("JANTINA"));
					h.put("UMUR", rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
					h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
					h.put("TARIKH_MATI",rs.getString("TARIKH_MATI") == null ? "" : sdf.format(rs.getDate("TARIKH_MATI")));
					h.put("NAMA_DAERAH",rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
					h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
					h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
					h.put("DAERAH_PEJABAT",rs.getString("DAERAH_PEJABAT") == null ? "" : rs.getString("DAERAH_PEJABAT"));
					h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					l1.addElement(h);
					
				}

			} finally {
				if (db != null)
					db.close();
			}
			return l1;
		}
		
	}

	Vector<Hashtable<String,String>>  l2 = null;

	public Vector<Hashtable<String,String>> List_KP_Lama_Simati(String idp, String kpbaru, String kplama,String kplain) 
		throws Exception {
		Db db = null;
		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			l2 = new Vector<Hashtable<String,String>> ();
			db = new Db();
			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();

			/*sql = "SELECT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, "
					+ "SM.NO_KP_LAMA,SM.NO_KP_LAIN,SM.JANTINA,SM.UMUR ,SM.TARIKH_MATI, "
					+ "SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI,PEJ.NAMA_PEJABAT,PEJ.ID_DAERAH, "
					+ "DP.NAMA_DAERAH AS DAERAH_PEJABAT FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, "
					+ "TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PEJ, TBLPPKPERMOHONAN P,TBLPFDFAIL F,"
					+ "TBLRUJDAERAH D,TBLRUJDAERAH DP, TBLRUJNEGERI N WHERE SM.ID_SIMATI = MS.ID_SIMATI "
					+ "AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI  "
					+ "AND UR.ID_DAERAHURUS = P.ID_DAERAHMHN "
					+ "AND UR.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG "
					+ "AND PEJ.ID_JENISPEJABAT <> '3' AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND PEJ.ID_JENISPEJABAT = '22' AND PEJ.ID_SEKSYEN = '2' "
					+ "AND PEJ.ID_DAERAH = DP.ID_DAERAH "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";*/
			
			sql = " SELECT DISTINCT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, " +
					" SM.NO_KP_LAMA, SM.NO_KP_LAIN, SM.JANTINA, SM.UMUR, SM.TARIKH_MATI, " +
					" SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI, " +
					" PEJ.NAMA_PEJABAT, PEJ.ID_DAERAH, PEJ.NAMA_DAERAH AS DAERAH_PEJABAT " +
					" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P, " +
					" TBLPFDFAIL F, TBLRUJDAERAH D, TBLRUJNEGERI N, " +
					" (SELECT PU.ID_DAERAHURUS,PEJ.ID_PEJABATJKPTG,PEJ.ID_DAERAH,D.NAMA_DAERAH,PEJ.NAMA_PEJABAT " +
					" FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJDAERAH D " +
					" WHERE PU.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_JENISPEJABAT = 22 " +
					" AND PEJ.ID_SEKSYEN = '2' AND PEJ.ID_DAERAH = D.ID_DAERAH) PEJ " +
					" WHERE SM.ID_SIMATI = MS.ID_SIMATI AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI(+) " +
					" AND PEJ.ID_DAERAHURUS(+) = P.ID_DAERAHMHN AND P.ID_DAERAHMHN = D.ID_DAERAH(+) " +
					" AND P.ID_STATUS NOT IN  (999,169,50,47,70) ";
			
			/*
			 * if(kpbaru != "") { sql += " AND SM.NO_KP_BARU = '"+kpbaru+"'"; }
			 */

			if (kplama != "") {
				sql += " AND SM.NO_KP_LAMA = '" + kplama + "'";
			}
			/*
			 * if(kplain != "") { sql += " AND SM.NO_KP_LAIN = '"+kplain+"'"; }
			 */
			myLogger.info("MATI LAMA CHECK :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String>  h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? "": rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? "": rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? "": rs.getString("NO_KP_LAIN"));
				h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? "" : rs.getString("JENIS_KP"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? "": rs.getString("NAMA_SIMATI"));
				h.put("JANTINA", rs.getString("JANTINA") == null ? "" : rs.getString("JANTINA"));
				h.put("UMUR", rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("TARIKH_MATI", rs.getString("TARIKH_MATI") == null ? "": sdf.format(rs.getDate("TARIKH_MATI")));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "": rs.getString("NAMA_DAERAH"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? "": rs.getString("NAMA_NEGERI"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "": rs.getString("NAMA_PEJABAT"));
				h.put("DAERAH_PEJABAT",rs.getString("DAERAH_PEJABAT") == null ? "" : rs.getString("DAERAH_PEJABAT"));
				l2.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();

		}
		return l2;
	
	}

	Vector<Hashtable<String,String>> l3 = new Vector<Hashtable<String,String>>();

	public Vector<Hashtable<String,String>> List_KP_Lain_Simati(String idp, String kpbaru, String kplama,String kplain) 
		throws Exception {
		Db db = null;
		l3.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();

			/*sql = "SELECT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, "
					+ "SM.NO_KP_LAMA,SM.NO_KP_LAIN,SM.JANTINA,SM.UMUR ,SM.TARIKH_MATI, "
					+ "SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI,PEJ.NAMA_PEJABAT,PEJ.ID_DAERAH, "
					+ "DP.NAMA_DAERAH AS DAERAH_PEJABAT FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, "
					+ "TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PEJ, TBLPPKPERMOHONAN P,TBLPFDFAIL F,"
					+ "TBLRUJDAERAH D,TBLRUJDAERAH DP, TBLRUJNEGERI N WHERE SM.ID_SIMATI = MS.ID_SIMATI "
					+ "AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI  "
					+ "AND UR.ID_DAERAHURUS = P.ID_DAERAHMHN "
					+ "AND UR.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG "
					+ "AND PEJ.ID_JENISPEJABAT <> '3' AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND PEJ.ID_JENISPEJABAT = '22' AND PEJ.ID_SEKSYEN = '2' "
					+ "AND PEJ.ID_DAERAH = DP.ID_DAERAH "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";*/
			
			sql = " SELECT DISTINCT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, " +
					" SM.NO_KP_LAMA, SM.NO_KP_LAIN, SM.JANTINA, SM.UMUR, SM.TARIKH_MATI, " +
					" SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI, " +
					" PEJ.NAMA_PEJABAT, PEJ.ID_DAERAH, PEJ.NAMA_DAERAH AS DAERAH_PEJABAT " +
					" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P, " +
					" TBLPFDFAIL F, TBLRUJDAERAH D, TBLRUJNEGERI N, " +
					" (SELECT PU.ID_DAERAHURUS,PEJ.ID_PEJABATJKPTG,PEJ.ID_DAERAH,D.NAMA_DAERAH,PEJ.NAMA_PEJABAT " +
					" FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU, TBLRUJDAERAH D " +
					" WHERE PU.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_JENISPEJABAT = 22 " +
					" AND PEJ.ID_SEKSYEN = '2' AND PEJ.ID_DAERAH = D.ID_DAERAH) PEJ " +
					" WHERE SM.ID_SIMATI = MS.ID_SIMATI AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					" AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI(+) " +
					" AND PEJ.ID_DAERAHURUS(+) = P.ID_DAERAHMHN AND P.ID_DAERAHMHN = D.ID_DAERAH(+) " +
					" AND P.ID_STATUS NOT IN  (999,169,50,47,70) ";
			
			/*
			 * if(kpbaru != "") { sql += " AND SM.NO_KP_BARU = '"+kpbaru+"'"; }
			 * if(kplama != "") { sql += " AND SM.NO_KP_LAMA = '"+kplama+"'"; }
			 */

			if (kplain != "") {
				sql += " AND SM.NO_KP_LAIN = '" + kplain + "'";
			}

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? "": rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? "": rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? "": rs.getString("NO_KP_LAIN"));
				h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? "" : rs.getString("JENIS_KP"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? "": rs.getString("NAMA_SIMATI"));
				h.put("JANTINA", rs.getString("JANTINA") == null ? "" : rs.getString("JANTINA"));
				h.put("UMUR", rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("TARIKH_MATI", rs.getString("TARIKH_MATI") == null ? "": sdf.format(rs.getDate("TARIKH_MATI")));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "": rs.getString("NAMA_DAERAH"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? "": rs.getString("NAMA_NEGERI"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "": rs.getString("NAMA_PEJABAT"));
				h.put("DAERAH_PEJABAT",rs.getString("DAERAH_PEJABAT") == null ? "" : rs.getString("DAERAH_PEJABAT"));
				l3.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();

		}
		return l3;
		
	}

	Vector listNOFAIL = new Vector();

	public void setListNofail(String idfail, String nofail) throws Exception {
		Db db = null;
		listNOFAIL.clear();
		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
				h.put("no_fail", rs.getString("NO_FAIL") == null ? "" : rs
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

	public boolean checkKP_Baru_Ob(String idp, String id_ob, String kpbaru,
			String kplama, String kplain) throws Exception {
		Db db = null;
		boolean a = false;
		String jumlah = "";
		// System.out.println("ID_OB:"+id_ob);
		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH "
					+ "FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ "WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND OB.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI "
					+ "AND SM.ID_SIMATI = '"
					+ idp
					+ "' "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";

			if (kpbaru != "" && kpbaru != null) {
				sql += "AND OB.NO_KP_BARU = '" + kpbaru + "'";
			}
			if (id_ob != "" && id_ob != null) {
				sql += "AND OB.ID_OB <> '" + id_ob + "'";
			}

			// System.out.print("SQL CHECK OB :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			/*
			 * while (rs.next()) {
			 * 
			 * jumlah =rs.getString("JUMLAH")==null?"":rs.getString("JUMLAH");
			 * 
			 * 
			 * 
			 * }
			 * 
			 * if (Integer.parseInt(jumlah)>0) {
			 * 
			 * a = true; }
			 */

			if (rs.next()) {

				if (rs.getInt("JUMLAH") > 0) {

					a = true;

				}

			}

		} finally {
			if (db != null)
				db.close();

		}
		return a;
	}

	public boolean checkKP_Lama_Ob(String idp, String id_ob, String kpbaru,
			String kplama, String kplain) throws Exception {
		Db db = null;
		boolean a = false;
		String jumlah = "";
		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH "
					+ "FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ "WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND OB.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI "
					+ "AND SM.ID_SIMATI = '"
					+ idp
					+ "' "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";

			if (!id_ob.equals("") && !id_ob.equals(null)) {
				sql += "AND OB.ID_OB <> '" + id_ob + "'";
			}
			if (!kplama.equals("") && !kplama.equals(null)) {
				sql += "AND OB.NO_KP_LAMA = '" + kplama + "'";
			}

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			/*
			 * 
			 * while (rs.next()) {
			 * 
			 * jumlah =rs.getString("JUMLAH")==null?"":rs.getString("JUMLAH");
			 * 
			 * }
			 * 
			 * if(Integer.parseInt(jumlah)>0) { a=true; }
			 */

			if (rs.next()) {

				if (rs.getInt("JUMLAH") > 0) {

					a = true;

				}

			}

		} finally {
			if (db != null)
				db.close();

		}
		return a;
	}

	public boolean checkKP_Lain_Ob(String idp, String id_ob, String kpbaru,String kplama, String kplain) 
		throws Exception {
		Db db = null;
		boolean a = false;
		String jumlah = "";
		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH "
					+ "FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ "WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND OB.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI "
					+ "AND SM.ID_SIMATI = '"
					+ idp
					+ "' "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";
			if (!id_ob.equals("") && !id_ob.equals(null)) {
				sql += "AND OB.ID_OB <> '" + id_ob + "'";
			}
			if (!kplain.equals("") && !kplain.equals(null)) {
				sql += "AND OB.NO_KP_LAIN = '" + kplain + "'";
			}

			// System.out.println("KPLAIN"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			/*
			 * 
			 * while (rs.next()) {
			 * 
			 * jumlah =rs.getString("JUMLAH")==null?"":rs.getString("JUMLAH");
			 * 
			 * 
			 * }
			 * 
			 * if(Integer.parseInt(jumlah)>0) { a=true; }
			 */

			if (rs.next()) {

				if (rs.getInt("JUMLAH") > 0) {

					a = true;

				}

			}

		} finally {
			if (db != null)
				db.close();

		}
		return a;
	}

	public boolean checkKP_Beranak_Ob(String idp, String id_ob,
			String no_beranak) throws Exception {
		Db db = null;
		boolean a = false;
		String jumlah = "";
		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH "
					+ "FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ "WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND OB.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI "
					+ "AND SM.ID_SIMATI = '"
					+ idp
					+ "' "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";

			if (!id_ob.equals("") && !id_ob.equals(null)) {
				sql += "AND OB.ID_OB <> '" + id_ob + "'";
			}
			if (!no_beranak.equals("") && !no_beranak.equals(null)) {
				sql += "AND OB.NO_SURAT_BERANAK = '" + no_beranak + "'";
			}

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			if (rs.next()) {

				if (rs.getInt("JUMLAH") > 0) {

					a = true;

				}

			}

		} finally {
			if (db != null)
				db.close();

		}
		return a;
	}

	Vector<Hashtable<String,String>> listCopyOb_baru = new Vector<Hashtable<String,String>>();

	public Vector<Hashtable<String,String>> checkKP_list_baru(String idp, String id_ob, String kpbaru,
			String kplama, String kplain) throws Exception {
		Db db = null;
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		listCopyOb_baru.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();

			sql = "SELECT P.ID_PERMOHONAN,SM.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.NO_KP_LAIN, "
					+ "OB.JENIS_KP,OB.NO_SURAT_BERANAK,OB.TARIKH_LAHIR,OB.JANTINA,OB.UMUR,OB.ALAMAT_1,OB.ALAMAT_2,OB.ALAMAT_3, OB.POSKOD, "
					+ "OB.ID_BANDAR,OB.NO_HP,OB.NO_TEL,OB.STATUS_HIDUP,OB.ID_TARAFKPTG,OB.ID_NEGERI,OB.JENIS_AGAMA,OB.JENIS_WARGA,"
					+ "OB.TARIKH_MATI, OB.WAKTU_KEMATIAN, OB.STATUS_OB,OB.ID_SAUDARA, OB.ALAMAT1_SURAT,OB.ALAMAT2_SURAT,OB.ALAMAT3_SURAT,"
					+ "OB.ID_BANDARSURAT, OB.POSKOD_SURAT,OB.ID_NEGERISURAT "
					+ "FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ "WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND OB.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI "
					+ "AND SM.ID_SIMATI <> '"
					+ idp
					+ "' "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";

			if (kpbaru != "") {
				sql += " AND OB.NO_KP_BARU = '" + kpbaru + "'";
			}
			/*
			 * if(kplama != "") { sql += " AND OB.NO_KP_LAMA = '"+kplama+"'"; }
			 * 
			 * if(kplain != "") { sql += " AND OB.NO_KP_LAIN = '"+kplain+"'"; }
			 */

			// System.out.print("SQL LIST OB :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("NAMA_OB", rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? "": rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? "": rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? "": rs.getString("NO_KP_LAIN"));
				h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? "" : rs.getString("JENIS_KP"));
				h.put("NO_SURAT_BERANAK",rs.getString("NO_SURAT_BERANAK") == null ? "" : rs.getString("NO_SURAT_BERANAK"));
				h.put("TARIKH_LAHIR", rs.getString("TARIKH_LAHIR") == null ? "": sdf.format(rs.getDate("TARIKH_LAHIR")));
				h.put("JANTINA", rs.getString("JANTINA") == null ? "" : rs.getString("JANTINA"));
				h.put("UMUR", rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
				h.put("ALAMAT_1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("ALAMAT_3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("NO_HP", rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP"));
				h.put("NO_TEL", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("STATUS_HIDUP", rs.getString("STATUS_HIDUP") == null ? "": rs.getString("STATUS_HIDUP"));
				h.put("ID_TARAFKPTG", rs.getString("ID_TARAFKPTG") == null ? "": rs.getString("ID_TARAFKPTG"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("JENIS_AGAMA", rs.getString("JENIS_AGAMA") == null ? "": rs.getString("JENIS_AGAMA"));
				h.put("JENIS_WARGA", rs.getString("JENIS_WARGA") == null ? "": rs.getString("JENIS_WARGA"));
				h.put("TARIKH_MATI", rs.getString("TARIKH_MATI") == null ? "": sdf.format(rs.getDate("TARIKH_MATI")));
				h.put("TARIKH_LAHIR", rs.getString("TARIKH_LAHIR") == null ? "": sdf.format(rs.getDate("TARIKH_LAHIR")));
				h.put("WAKTU_KEMATIAN",rs.getString("WAKTU_KEMATIAN") == null ? "" : rs.getString("WAKTU_KEMATIAN"));
				h.put("STATUS_OB", rs.getString("STATUS_OB") == null ? "" : rs.getString("STATUS_OB"));
				h.put("ALAMAT1_SURAT",rs.getString("ALAMAT1_SURAT") == null ? "" : rs.getString("ALAMAT1_SURAT"));
				h.put("ALAMAT2_SURAT",rs.getString("ALAMAT2_SURAT") == null ? "" : rs.getString("ALAMAT2_SURAT"));
				h.put("ALAMAT3_SURAT",rs.getString("ALAMAT3_SURAT") == null ? "" : rs.getString("ALAMAT3_SURAT"));
				h.put("ID_BANDARSURAT",rs.getString("ID_BANDARSURAT") == null ? "" : rs.getString("ID_BANDARSURAT"));
				h.put("POSKOD_SURAT", rs.getString("POSKOD_SURAT") == null ? "": rs.getString("POSKOD_SURAT"));
				h.put("ID_NEGERISURAT",rs.getString("ID_NEGERISURAT") == null ? "" : rs.getString("ID_NEGERISURAT"));
				h.put("ID_SAUDARA", rs.getString("ID_SAUDARA") == null ? "": rs.getString("ID_SAUDARA"));
				listCopyOb_baru.addElement(h);
				
			}
		} finally {
			if (db != null)
				db.close();

		}

		// if((kpbaru != "" && kpbaru.equals(rs.getString("NO_KP_BARU")))
		if (id_ob.equals("") && id_ob.equals(null)) {
			listCopyOb_baru = null;
		}
		return listCopyOb_baru;
	}

	Vector<Hashtable<String,String>> listCopyOb_lama = new Vector<Hashtable<String,String>>();

	public Vector<Hashtable<String,String>> checkKP_list_lama(String idp,String id_ob,String kpbaru,String kplama,String kplain) 
		throws Exception {
		Db db = null;
		listCopyOb_lama.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();

			sql = "SELECT P.ID_PERMOHONAN,SM.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.NO_KP_LAIN, "
					+ "OB.JENIS_KP,OB.NO_SURAT_BERANAK,OB.TARIKH_LAHIR,OB.JANTINA,OB.UMUR,OB.ALAMAT_1,OB.ALAMAT_2,OB.ALAMAT_3, OB.POSKOD, "
					+ "OB.ID_BANDAR,OB.NO_HP,OB.NO_TEL,OB.STATUS_HIDUP,OB.ID_TARAFKPTG,OB.ID_NEGERI,OB.JENIS_AGAMA,OB.JENIS_WARGA,"
					+ "OB.TARIKH_MATI, OB.WAKTU_KEMATIAN, OB.STATUS_OB,OB.ID_SAUDARA, OB.ALAMAT1_SURAT,OB.ALAMAT2_SURAT,OB.ALAMAT3_SURAT,"
					+ "OB.ID_BANDARSURAT, OB.POSKOD_SURAT,OB.ID_NEGERISURAT "
					+ "FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ "WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND OB.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI "
					+ "AND SM.ID_SIMATI <> '"
					+ idp
					+ "' "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";
			/*
			 * if(kpbaru != "") { sql += " AND OB.NO_KP_BARU = '"+kpbaru+"'"; }
			 */
			if (kplama != "") {
				sql += " AND OB.NO_KP_LAMA = '" + kplama.trim().toUpperCase()
						+ "'";
			}
			/*
			 * if(kplain != "") { sql += " AND OB.NO_KP_LAIN = '"+kplain+"'"; }
			 */

			// /System.out.print("SQL LIST LAMA OB :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("NAMA_OB", rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? "": rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? "": rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? "": rs.getString("NO_KP_LAIN"));
				h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? "" : rs.getString("JENIS_KP"));
				h.put("NO_SURAT_BERANAK",rs.getString("NO_SURAT_BERANAK") == null ? "" : rs.getString("NO_SURAT_BERANAK"));
				h.put("TARIKH_LAHIR", rs.getString("TARIKH_LAHIR") == null ? "": sdf.format(rs.getDate("TARIKH_LAHIR")));
				h.put("JANTINA", rs.getString("JANTINA") == null ? "" : rs.getString("JANTINA"));
				h.put("UMUR", rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
//				h.put("ALAMAT_1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("ALAMAT_3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("NO_HP", rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP"));
				h.put("NO_TEL", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("STATUS_HIDUP", rs.getString("STATUS_HIDUP") == null ? "": rs.getString("STATUS_HIDUP"));
				h.put("ID_TARAFKPTG", rs.getString("ID_TARAFKPTG") == null ? "": rs.getString("ID_TARAFKPTG"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("JENIS_AGAMA", rs.getString("JENIS_AGAMA") == null ? "": rs.getString("JENIS_AGAMA"));
				h.put("JENIS_WARGA", rs.getString("JENIS_WARGA") == null ? "": rs.getString("JENIS_WARGA"));
				h.put("TARIKH_MATI", rs.getString("TARIKH_MATI") == null ? "": sdf.format(rs.getDate("TARIKH_MATI")));
				h.put("TARIKH_LAHIR", rs.getString("TARIKH_LAHIR") == null ? "": sdf.format(rs.getDate("TARIKH_LAHIR")));
				h.put("WAKTU_KEMATIAN",rs.getString("WAKTU_KEMATIAN") == null ? "" : rs.getString("WAKTU_KEMATIAN"));
				h.put("STATUS_OB", rs.getString("STATUS_OB") == null ? "" : rs.getString("STATUS_OB"));
				h.put("ALAMAT1_SURAT",rs.getString("ALAMAT1_SURAT") == null ? "" : rs.getString("ALAMAT1_SURAT"));
				h.put("ALAMAT2_SURAT",rs.getString("ALAMAT2_SURAT") == null ? "" : rs.getString("ALAMAT2_SURAT"));
				h.put("ALAMAT3_SURAT",rs.getString("ALAMAT3_SURAT") == null ? "" : rs.getString("ALAMAT3_SURAT"));
				h.put("ID_BANDARSURAT",rs.getString("ID_BANDARSURAT") == null ? "" : rs.getString("ID_BANDARSURAT"));
				h.put("POSKOD_SURAT", rs.getString("POSKOD_SURAT") == null ? "": rs.getString("POSKOD_SURAT"));
				h.put("ID_NEGERISURAT",rs.getString("ID_NEGERISURAT") == null ? "" : rs.getString("ID_NEGERISURAT"));
				h.put("ID_SAUDARA", rs.getString("ID_SAUDARA") == null ? "": rs.getString("ID_SAUDARA"));
				listCopyOb_lama.addElement(h);
				
			}
		} finally {
			if (db != null)
				db.close();

		}

		// if((kpbaru != "" && kpbaru.equals(rs.getString("NO_KP_BARU")))
		if (id_ob.equals("") && id_ob.equals(null)) {
			listCopyOb_lama = null;
		}
		return listCopyOb_lama;
	
	}

	Vector<Hashtable<String,String>> listCopyOb_lain = new Vector<Hashtable<String,String>>();

	public Vector<Hashtable<String,String>> checkKP_list_lain(String idp, String id_ob, String kpbaru,String kplama, String kplain) 
		throws Exception {
		Db db = null;
		listCopyOb_lain.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();

			sql = "SELECT P.ID_PERMOHONAN,SM.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.NO_KP_LAIN, "
					+ "OB.JENIS_KP,OB.NO_SURAT_BERANAK,OB.TARIKH_LAHIR,OB.JANTINA,OB.UMUR,OB.ALAMAT_1,OB.ALAMAT_2,OB.ALAMAT_3, OB.POSKOD, "
					+ "OB.ID_BANDAR,OB.NO_HP,OB.NO_TEL,OB.STATUS_HIDUP,OB.ID_TARAFKPTG,OB.ID_NEGERI,OB.JENIS_AGAMA,OB.JENIS_WARGA,"
					+ "OB.TARIKH_MATI, OB.WAKTU_KEMATIAN, OB.STATUS_OB,OB.ID_SAUDARA, OB.ALAMAT1_SURAT,OB.ALAMAT2_SURAT,OB.ALAMAT3_SURAT,"
					+ "OB.ID_BANDARSURAT, OB.POSKOD_SURAT,OB.ID_NEGERISURAT "
					+ "FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ "WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND OB.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI "
					+ "AND SM.ID_SIMATI <> '"
					+ idp
					+ "'"
					+ "  AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";
			/*
			 * if(kpbaru != "") { sql += " AND OB.NO_KP_BARU = '"+kpbaru+"'"; }
			 */
			/*
			 * if(kplama != "") { sql += " AND OB.NO_KP_LAMA = '"+kplama+"'"; }
			 */

			if (kplain != "") {
				sql += " AND OB.NO_KP_LAIN = '" + kplain.trim().toUpperCase()
						+ "'";
			}

			// System.out.print("SQL LIST OB :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("NAMA_OB", rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? "": rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? "": rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? "": rs.getString("NO_KP_LAIN"));
				h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? "" : rs.getString("JENIS_KP"));
				h.put("NO_SURAT_BERANAK",rs.getString("NO_SURAT_BERANAK") == null ? "" : rs.getString("NO_SURAT_BERANAK"));
				h.put("TARIKH_LAHIR", rs.getString("TARIKH_LAHIR") == null ? "": sdf.format(rs.getDate("TARIKH_LAHIR")));
				h.put("JANTINA", rs.getString("JANTINA") == null ? "" : rs.getString("JANTINA"));
				h.put("UMUR", rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
//				h.put("ALAMAT_1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("ALAMAT_3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("NO_HP", rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP"));
				h.put("NO_TEL", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("STATUS_HIDUP", rs.getString("STATUS_HIDUP") == null ? "": rs.getString("STATUS_HIDUP"));
				h.put("ID_TARAFKPTG", rs.getString("ID_TARAFKPTG") == null ? "": rs.getString("ID_TARAFKPTG"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("JENIS_AGAMA", rs.getString("JENIS_AGAMA") == null ? "": rs.getString("JENIS_AGAMA"));
				h.put("JENIS_WARGA", rs.getString("JENIS_WARGA") == null ? "": rs.getString("JENIS_WARGA"));
				h.put("TARIKH_MATI", rs.getString("TARIKH_MATI") == null ? "": sdf.format(rs.getDate("TARIKH_MATI")));
				h.put("TARIKH_LAHIR", rs.getString("TARIKH_LAHIR") == null ? "": sdf.format(rs.getDate("TARIKH_LAHIR")));
				h.put("WAKTU_KEMATIAN",rs.getString("WAKTU_KEMATIAN") == null ? "" : rs.getString("WAKTU_KEMATIAN"));
				h.put("STATUS_OB", rs.getString("STATUS_OB") == null ? "" : rs.getString("STATUS_OB"));
				h.put("ALAMAT1_SURAT",rs.getString("ALAMAT1_SURAT") == null ? "" : rs.getString("ALAMAT1_SURAT"));
				h.put("ALAMAT2_SURAT",rs.getString("ALAMAT2_SURAT") == null ? "" : rs.getString("ALAMAT2_SURAT"));
				h.put("ALAMAT3_SURAT",rs.getString("ALAMAT3_SURAT") == null ? "" : rs.getString("ALAMAT3_SURAT"));
				h.put("ID_BANDARSURAT",rs.getString("ID_BANDARSURAT") == null ? "" : rs.getString("ID_BANDARSURAT"));
				h.put("POSKOD_SURAT", rs.getString("POSKOD_SURAT") == null ? "": rs.getString("POSKOD_SURAT"));
				h.put("ID_NEGERISURAT",rs.getString("ID_NEGERISURAT") == null ? "" : rs.getString("ID_NEGERISURAT"));
				h.put("ID_SAUDARA", rs.getString("ID_SAUDARA") == null ? "": rs.getString("ID_SAUDARA"));
				listCopyOb_lain.addElement(h);
				
			}
		} finally {
			if (db != null)
				db.close();

		}

		// if((kpbaru != "" && kpbaru.equals(rs.getString("NO_KP_BARU")))
		if (id_ob.equals("") && id_ob.equals(null)) {
			listCopyOb_lain = null;
		}
		return listCopyOb_lain;
	
	}

	Vector<Hashtable<String,String>> listCopyOb_beranak = new Vector<Hashtable<String,String>>();

	public Vector<Hashtable<String,String>> checkKP_list_beranak(String idp, String id_ob,String no_beranak) 
		throws Exception {
		Db db = null;
		listCopyOb_beranak.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();

			sql = "SELECT P.ID_PERMOHONAN,SM.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.NO_KP_LAIN, "
					+ "OB.JENIS_KP,OB.NO_SURAT_BERANAK,OB.TARIKH_LAHIR,OB.JANTINA,OB.UMUR,OB.ALAMAT_1,OB.ALAMAT_2,OB.ALAMAT_3, OB.POSKOD, "
					+ "OB.ID_BANDAR,OB.NO_HP,OB.NO_TEL,OB.STATUS_HIDUP,OB.ID_TARAFKPTG,OB.ID_NEGERI,OB.JENIS_AGAMA,OB.JENIS_WARGA,"
					+ "OB.TARIKH_MATI, OB.WAKTU_KEMATIAN, OB.STATUS_OB,OB.ID_SAUDARA, OB.ALAMAT1_SURAT,OB.ALAMAT2_SURAT,OB.ALAMAT3_SURAT,"
					+ "OB.ID_BANDARSURAT, OB.POSKOD_SURAT,OB.ID_NEGERISURAT "
					+ "FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ "WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND OB.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI "
					+ "AND SM.ID_SIMATI <> '"
					+ idp
					+ "' "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";

			if (no_beranak != "") {
				sql += " AND OB.NO_SURAT_BERANAK = '"
						+ no_beranak.trim().toUpperCase() + "'";
			}

			System.out.print("SQL LIST LAMA OB :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("NAMA_OB", rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? "": rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? "": rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? "": rs.getString("NO_KP_LAIN"));
				h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? "" : rs.getString("JENIS_KP"));
				h.put("NO_SURAT_BERANAK",rs.getString("NO_SURAT_BERANAK") == null ? "" : rs.getString("NO_SURAT_BERANAK"));
				h.put("TARIKH_LAHIR", rs.getString("TARIKH_LAHIR") == null ? "": sdf.format(rs.getDate("TARIKH_LAHIR")));
				h.put("JANTINA", rs.getString("JANTINA") == null ? "" : rs.getString("JANTINA"));
				h.put("UMUR", rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
				h.put("ALAMAT_1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("ALAMAT_3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("NO_HP", rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP"));
				h.put("NO_TEL", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("STATUS_HIDUP", rs.getString("STATUS_HIDUP") == null ? "": rs.getString("STATUS_HIDUP"));
				h.put("ID_TARAFKPTG", rs.getString("ID_TARAFKPTG") == null ? "": rs.getString("ID_TARAFKPTG"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("JENIS_AGAMA", rs.getString("JENIS_AGAMA") == null ? "": rs.getString("JENIS_AGAMA"));
				h.put("JENIS_WARGA", rs.getString("JENIS_WARGA") == null ? "": rs.getString("JENIS_WARGA"));
				h.put("TARIKH_MATI", rs.getString("TARIKH_MATI") == null ? "": sdf.format(rs.getDate("TARIKH_MATI")));
				h.put("TARIKH_LAHIR", rs.getString("TARIKH_LAHIR") == null ? "": sdf.format(rs.getDate("TARIKH_LAHIR")));
				h.put("WAKTU_KEMATIAN",rs.getString("WAKTU_KEMATIAN") == null ? "" : rs.getString("WAKTU_KEMATIAN"));
				h.put("STATUS_OB", rs.getString("STATUS_OB") == null ? "" : rs.getString("STATUS_OB"));
				h.put("ALAMAT1_SURAT",rs.getString("ALAMAT1_SURAT") == null ? "" : rs.getString("ALAMAT1_SURAT"));
				h.put("ALAMAT2_SURAT",rs.getString("ALAMAT2_SURAT") == null ? "" : rs.getString("ALAMAT2_SURAT"));
				h.put("ALAMAT3_SURAT",rs.getString("ALAMAT3_SURAT") == null ? "" : rs.getString("ALAMAT3_SURAT"));
				h.put("ID_BANDARSURAT",rs.getString("ID_BANDARSURAT") == null ? "" : rs.getString("ID_BANDARSURAT"));
				h.put("POSKOD_SURAT", rs.getString("POSKOD_SURAT") == null ? "": rs.getString("POSKOD_SURAT"));
				h.put("ID_NEGERISURAT",rs.getString("ID_NEGERISURAT") == null ? "" : rs.getString("ID_NEGERISURAT"));
				h.put("ID_SAUDARA", rs.getString("ID_SAUDARA") == null ? "": rs.getString("ID_SAUDARA"));
				listCopyOb_beranak.addElement(h);
				
			}
		} finally {
			if (db != null)
				db.close();

		}

		if (id_ob.equals("") && id_ob.equals(null)) {
			listCopyOb_beranak = null;
		}
		return listCopyOb_beranak;
	}

	public boolean check_nolot_pt(String no_lotpt, String id_harta)
			throws Exception {
		Db db = null;
		boolean a = false;
		String sql = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT COUNT(NO_PT) AS JUMLAH FROM TBLPPKHTA WHERE NO_PT = '"
					+ no_lotpt + "'";

			if (!id_harta.equals("") && !id_harta.equals(null)) {
				sql += "AND ID_HTA <> '" + id_harta + "'";
			}

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			if (rs.next()) {

				if (rs.getInt("JUMLAH") > 0) {

					a = true;

				}

			}

			/*
			 * 
			 * while (rs.next()) {
			 * 
			 * String no_pt
			 * =rs.getString("NO_PT")==null?"":rs.getString("NO_PT");
			 * 
			 * if ((no_pt!="" && no_pt!="" && no_pt.equals(no_lotpt)) ) {
			 * //System.out.println("DUPLICATE LA NO LOT"); a = true; }
			 * 
			 * 
			 * 
			 * }
			 */

		} finally {
			if (db != null)
				db.close();

		}
		return a;
	}

	public boolean getDaerahByNegeriUser(String userid, String idp,
			String id_daerah, String id_hta) throws Exception {
		Db db = null;
		// Db db1 = null;
		boolean a = false;
		String sql = "SELECT SM.ID_PERMOHONANSIMATI, HTA.ID_DAERAH, D.KOD_DAERAH,D.NAMA_DAERAH  FROM TBLPPKPERMOHONANSIMATI SM, "
				+ "TBLPPKPERMOHONAN P,TBLPPKHTA HTA, TBLPPKSIMATI M,TBLRUJDAERAH D  "
				+ "WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PERMOHONAN = '"
				+ idp
				+ "' "
				+ "AND SM.ID_SIMATI = M.ID_SIMATI "
				+ "AND HTA.ID_SIMATI = SM.ID_SIMATI "
				+ "AND HTA.ID_DAERAH = D.ID_DAERAH(+) "
				+ "AND HTA.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS "
				+ "FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
				+ "WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG "
				+ "AND U.ID_JENISPEJABAT != '3' AND UR.USER_ID='"
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
				
				sql += " )";

		if (!id_hta.equals("") && !id_hta.equals(null)) {
			sql += "AND HTA.ID_HTA <> '" + id_hta + "'";
		}

		 System.out.println("DAERAH LIST :" + sql.toUpperCase());

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("id_jenisnopb");
			r.add("keterangan");

			// sql = r.getSQLSelect(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Vector listDaerahByUser = new Vector();
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("iddaerah", rs.getString("id_daerah") == null ? "" : rs
						.getString("id_daerah"));
				h.put("koddaerah", rs.getString("kod_daerah") == null ? "" : rs
						.getString("kod_daerah"));
				h.put("namadaerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));

				listDaerahByUser.addElement(h);
			}

			if (listDaerahByUser.size() > 0) {
				a = true;
			} else {
				 System.out.println("XDAK LA DAERAH");

				if (id_daerah != "" && id_daerah != null) {
					// db1 = new Db();
					Statement stmt1 = db.getStatement();
					String sql1 = "SELECT ID_DAERAH,KOD_DAERAH,NAMA_DAERAH "
							+ " FROM TBLRUJDAERAH "
							+ " WHERE ID_DAERAH = '"
							+ id_daerah
							+ "' "
							+ " AND ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
							+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND U.ID_JENISPEJABAT != '3' AND UR.USER_ID='"
							+ userid + "' ";
							
							 sql1 += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
							
							sql1 += " )";

					 System.out.println("DAERAH LIST C :" +sql1);
					// sql1.toUpperCase());
					ResultSet rs1 = stmt.executeQuery(sql1);
					Vector listDaerahByUser1 = new Vector();

					Hashtable h1;

					while (rs1.next()) {
						h1 = new Hashtable();
						h1.put("iddaerah",
								rs1.getString("id_daerah") == null ? "" : rs1
										.getString("id_daerah"));
						h1.put("koddaerah",
								rs1.getString("kod_daerah") == null ? "" : rs1
										.getString("kod_daerah"));
						h1.put("namadaerah",
								rs1.getString("nama_daerah") == null ? "" : rs1
										.getString("nama_daerah"));

						listDaerahByUser1.addElement(h1);
					}

					if (listDaerahByUser1.size() > 0) {
						a = true;
					} else {
						//aishah add untuk cater mslh daerah jagaan tampin dan gemas
						if(id_daerah.equals("45") || id_daerah.equals("131") ){
							a = true;
						}else{
							a = false;
						}
					}

				}

			}
			return a;

		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector<Hashtable<String,String>> alamat_raya = new Vector<Hashtable<String,String>>();

	public Vector<Hashtable<String,String>> getAlamatRaya(String jenis_pej) throws Exception {
		Db db = null;
		// String v="08";
		alamat_raya.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();

			sql = "SELECT PEJ.ID_PEJABAT,PEJ.NAMA_PEJABAT, PEJ.ALAMAT1, PEJ.ALAMAT2, PEJ.ALAMAT3, "
					+ "PEJ.POSKOD, PEJ.ID_BANDAR, PEJ.ID_NEGERI, PEJ.NO_TEL, PEJ.NO_FAX, "
					+ "NEG.NAMA_NEGERI, NEG.KOD_NEGERI, BAN.KOD_BANDAR, "
					+ "BAN.KETERANGAN AS NAMA_BANDAR "
					+ "FROM TBLRUJPEJABAT PEJ, TBLRUJNEGERI NEG,TBLRUJBANDAR BAN "
					+ "WHERE (PEJ.ID_JENISPEJABAT = '9' OR PEJ.ID_JENISPEJABAT = '61' OR PEJ.ID_JENISPEJABAT = '62' OR PEJ.ID_JENISPEJABAT = '141782') "
					+ "AND PEJ.ID_NEGERI = NEG.ID_NEGERI(+) "
					+ "AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) "
					+ "AND ID_PEJABAT = '" + jenis_pej + "'";

			// System.out.println("SQL.ALAMAT RAYA"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable<String,String> h = new Hashtable<String,String>();
				h.put("nama_pejabat", rs.getString("NAMA_PEJABAT") == null ? "": rs.getString("NAMA_PEJABAT"));
				h.put("id_Pejabat", rs.getString("ID_PEJABAT") == null ? "": rs.getString("ID_PEJABAT"));
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("no_tel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("no_fax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI") == null ? "": rs.getString("NAMA_NEGERI"));
				h.put("id_negeri", rs.getString("id_Negeri") == null ? "" : rs.getString("id_Negeri"));
				h.put("kod_negeri", rs.getString("KOD_NEGERI") == null ? "": rs.getString("KOD_NEGERI"));
				h.put("nama_bandar", rs.getString("NAMA_BANDAR") == null ? "": rs.getString("NAMA_BANDAR"));
				h.put("id_bandar", rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("kod_bandar", rs.getString("KOD_BANDAR") == null ? "": rs.getString("KOD_BANDAR"));
				alamat_raya.addElement(h);
				
			}
		} catch (DbException e) {

			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return alamat_raya;
		
	}

	static Vector MT1 = null;

	public static Vector List_KP_Baru_Simati2(String idp, String kpbaru,
			String kplama, String kplain) throws Exception {
		// Azam change on 02.02.2010
		// Check len first then check DB
		MT1 = new Vector();
		if (kpbaru == null || "".equals(kpbaru) || kpbaru.length() < 12) {
			// Checking for valid new ic number
			// myLogger.debug("no need to check: "+kpbaru);
			return MT1;
		} else {
			Db db = null;
			String sql = "";
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {

				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = "SELECT F.NO_FAIL,P.ID_PERMOHONAN, SM.NAMA_SIMATI, SM.NO_KP_BARU, "
						+ "SM.NO_KP_LAMA,SM.NO_KP_LAIN,SM.JANTINA,SM.UMUR ,SM.TARIKH_MATI, "
						+ "SM.JENIS_KP, P.ID_DAERAHMHN, D.NAMA_DAERAH, N.NAMA_NEGERI,PEJ.NAMA_PEJABAT,PEJ.ID_DAERAH, "
						+ "DP.NAMA_DAERAH AS DAERAH_PEJABAT FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, "
						+ "TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PEJ, TBLPPKPERMOHONAN P,TBLPFDFAIL F,"
						+ "TBLRUJDAERAH D,TBLRUJDAERAH DP, TBLRUJNEGERI N WHERE SM.ID_SIMATI = MS.ID_SIMATI "
						+ "AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN "
						+ "AND P.ID_FAIL = F.ID_FAIL AND D.ID_NEGERI = N.ID_NEGERI  "
						+ "AND UR.ID_DAERAHURUS = P.ID_DAERAHMHN "
						+ "AND UR.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG "
						+ "AND PEJ.ID_JENISPEJABAT <> '3' AND P.ID_DAERAHMHN = D.ID_DAERAH "
						+ " AND PEJ.ID_JENISPEJABAT = '22' AND PEJ.ID_SEKSYEN = '2' "
						+ "AND PEJ.ID_DAERAH = DP.ID_DAERAH "
						+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";

				if (kpbaru != "") {
					sql += " AND SM.NO_KP_BARU = '" + kpbaru + "'";
				}
				myLogger.info("MATI CHECK 2 :" + sql.toUpperCase());
				System.out.println("test : " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? ""
							: rs.getString("NO_KP_BARU"));
					h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? ""
							: rs.getString("NO_KP_LAMA"));
					h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? ""
							: rs.getString("NO_KP_LAIN"));
					h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? ""
							: rs.getString("JENIS_KP"));

					h.put("NAMA_SIMATI",
							rs.getString("NAMA_SIMATI") == null ? "" : rs
									.getString("NAMA_SIMATI"));
					h.put("JANTINA", rs.getString("JANTINA") == null ? "" : rs
							.getString("JANTINA"));
					h.put("UMUR", rs.getString("UMUR") == null ? "" : rs
							.getString("UMUR"));
					h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
							.getString("NO_FAIL"));
					h.put("TARIKH_MATI",
							rs.getString("TARIKH_MATI") == null ? "" : sdf
									.format(rs.getDate("TARIKH_MATI")));
					h.put("NAMA_DAERAH",
							rs.getString("NAMA_DAERAH") == null ? "" : rs
									.getString("NAMA_DAERAH"));
					h.put("NAMA_NEGERI",
							rs.getString("NAMA_NEGERI") == null ? "" : rs
									.getString("NAMA_NEGERI"));
					h.put("NAMA_PEJABAT",
							rs.getString("NAMA_PEJABAT") == null ? "" : rs
									.getString("NAMA_PEJABAT"));
					h.put("DAERAH_PEJABAT",
							rs.getString("DAERAH_PEJABAT") == null ? "" : rs
									.getString("DAERAH_PEJABAT"));
					MT1.addElement(h);
				}

			} finally {
				if (db != null)
					db.close();
			}
			return MT1;
		}
	}

	private static Vector MaklumatSimati = null;

	public static Vector getmaklumatSimati() {
		return MaklumatSimati;
	}

	@SuppressWarnings("unchecked")
	public static void setmaklumatSimati(String kpbarux, String kplamax,
			String kplainx) throws Exception {

		MaklumatSimati = new Vector();

		Db db = null;
		MaklumatSimati.clear();
		String sql = "";

		String kpbaru = kpbarux.trim();
		String kplama = kplamax.trim();
		String kplain = kplainx.trim();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT P.NO_PERMOHONAN_ONLINE, PEJ.ID_SEKSYEN,PEJ.ID_JENISPEJABAT,UR.ID_DAERAHURUS,UR.ID_PEJABATURUSAN, PEJ.ID_PEJABATJKPTG, P.ID_PERMOHONAN, F.NO_FAIL, SM.NAMA_SIMATI, SM.NO_KP_BARU, "
					+ "SM.NO_KP_LAMA,SM.NO_KP_LAIN, SM.JENIS_KP, D.NAMA_DAERAH, N.NAMA_NEGERI, PEJ.NAMA_PEJABAT, "
					+ "DP.NAMA_DAERAH AS DAERAH_PEJABAT,P.ID_STATUS  "
					+ "FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS,TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PEJ,  "
					+ "TBLPPKPERMOHONAN P,TBLPFDFAIL F, TBLRUJDAERAH D,TBLRUJDAERAH DP, TBLRUJNEGERI N  "
					+ "WHERE SM.ID_SIMATI(+) = MS.ID_SIMATI  "
					+ "AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN(+)  "
					+ "AND P.ID_FAIL = F.ID_FAIL(+)  "
					+ "AND D.ID_NEGERI = N.ID_NEGERI(+)   "
					+ "AND UR.ID_DAERAHURUS(+) = P.ID_DAERAHMHN  "
					+ "AND UR.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+)  "
					+ "AND PEJ.ID_DAERAH = DP.ID_DAERAH(+)  "
					+ "AND P.ID_DAERAHMHN = D.ID_DAERAH(+)  "
					+ "AND PEJ.ID_JENISPEJABAT = '22'  "
					+ "AND PEJ.ID_SEKSYEN = '2'  "
					+ "  AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'";

			if (!kpbaru.equals("") && kpbaru.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_BARU) = UPPER('" + kpbaru + "')";
			} else if (!kplama.equals("") && kplama.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_LAMA) = UPPER('" + kplama + "')";
			} else if (!kplain.equals("") && kplain.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_LAIN) = UPPER('" + kplain + "')";
			}

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("NO_PERMOHONAN_ONLINE", rs
						.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
						.getString("NO_PERMOHONAN_ONLINE"));
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? ""
						: rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? ""
						: rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? ""
						: rs.getString("NO_KP_LAIN"));
				h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? "" : rs
						.getString("JENIS_KP"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT"));
				h.put("DAERAH_PEJABAT",
						rs.getString("DAERAH_PEJABAT") == null ? "" : rs
								.getString("DAERAH_PEJABAT"));
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				MaklumatSimati.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}// close

	private static Vector MaklumatSimati_17 = null;

	public static Vector getmaklumatSimati_17() {
		return MaklumatSimati_17;
	}

	@SuppressWarnings("unchecked")
	public static void setmaklumatSimati_17(String kpbarux, String kplamax,
			String kplainx, String no_failx) throws Exception {

		MaklumatSimati_17 = new Vector();

		Db db = null;
		MaklumatSimati_17.clear();
		String sql = "";

		String kpbaru = kpbarux.trim();
		String kplama = kplamax.trim();
		String kplain = kplainx.trim();
		String no_fail = no_failx.trim();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT P.NO_PERMOHONAN_ONLINE, PEJ.ID_SEKSYEN,PEJ.ID_JENISPEJABAT,UR.ID_DAERAHURUS,UR.ID_PEJABATURUSAN, PEJ.ID_PEJABATJKPTG, P.ID_PERMOHONAN, F.NO_FAIL, SM.NAMA_SIMATI, SM.NO_KP_BARU, "
					+ "SM.NO_KP_LAMA,SM.NO_KP_LAIN, SM.JENIS_KP, D.NAMA_DAERAH, N.NAMA_NEGERI, PEJ.NAMA_PEJABAT, "
					+ "DP.NAMA_DAERAH AS DAERAH_PEJABAT,P.ID_STATUS,P.NO_SUBJAKET  "
					+ "FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS,TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PEJ,  "
					+ "TBLPPKPERMOHONAN P,TBLPFDFAIL F, TBLRUJDAERAH D,TBLRUJDAERAH DP, TBLRUJNEGERI N  "
					+ "WHERE SM.ID_SIMATI(+) = MS.ID_SIMATI  "
					+ "AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN(+)  "
					+ "AND P.ID_FAIL = F.ID_FAIL(+)  "
					+ "AND D.ID_NEGERI = N.ID_NEGERI(+)   "
					+ "AND UR.ID_DAERAHURUS(+) = P.ID_DAERAHMHN  "
					+ "AND UR.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+)  "
					+ "AND PEJ.ID_DAERAH = DP.ID_DAERAH(+)  "
					+ "AND P.ID_DAERAHMHN = D.ID_DAERAH(+)  "
					+ "AND PEJ.ID_JENISPEJABAT = '22'  "
					+ "AND PEJ.ID_SEKSYEN = '2'  "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152' ";

			if (!kpbaru.equals("") && kpbaru.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_BARU) = UPPER('" + kpbaru + "')";
			} else if (!kplama.equals("") && kplama.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_LAMA) = UPPER('" + kplama + "')";
			} else if (!kplain.equals("") && kplain.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_LAIN) = UPPER('" + kplain + "')";
			} else if (!no_fail.equals("") && no_fail.length() != 0) {
				sql += " AND UPPER(F.NO_FAIL) = UPPER('" + no_fail + "')";
			}

			sql += " ORDER BY P.NO_SUBJAKET DESC ";

			myLogger.info("SEMAKAN DATA ONLINE ::" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("NO_PERMOHONAN_ONLINE", rs
						.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
						.getString("NO_PERMOHONAN_ONLINE"));
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? ""
						: rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? ""
						: rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? ""
						: rs.getString("NO_KP_LAIN"));
				h.put("JENIS_KP", rs.getString("JENIS_KP") == null ? "" : rs
						.getString("JENIS_KP"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT"));
				h.put("DAERAH_PEJABAT",
						rs.getString("DAERAH_PEJABAT") == null ? "" : rs
								.getString("DAERAH_PEJABAT"));
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				MaklumatSimati_17.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}// close

	private static Vector MaklumatSimati_WithoutNoFail = null;

	public static Vector getmaklumatSimati_WithoutNoFail() {
		return MaklumatSimati_WithoutNoFail;
	}

	@SuppressWarnings("unchecked")
	public static void setmaklumatSimati_WithoutNoFail(String kpbarux,
			String kplamax, String kplainx) throws Exception {

		MaklumatSimati_WithoutNoFail = new Vector();

		Db db = null;
		MaklumatSimati_WithoutNoFail.clear();
		String sql = "";

		String kpbaru = kpbarux.trim();
		String kplama = kplamax.trim();
		String kplain = kplainx.trim();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT P.NO_PERMOHONAN_ONLINE,P.ID_PERMOHONAN, F.NO_FAIL, SM.NAMA_SIMATI, SM.NO_KP_BARU, "
					+ "SM.NO_KP_LAMA,SM.NO_KP_LAIN, SM.JENIS_KP,P.ID_STATUS "
					+ "FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, "
					+ "TBLPPKPERMOHONAN P,TBLPFDFAIL F  "
					+ "WHERE SM.ID_SIMATI(+) = MS.ID_SIMATI   "
					+ "AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN(+)   "
					+ "AND P.ID_FAIL = F.ID_FAIL(+)     "
					+ " AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'  ";

			if (!kpbaru.equals("") && kpbaru.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_BARU) = UPPER('" + kpbaru + "')";
			} else if (!kplama.equals("") && kplama.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_LAMA) = UPPER('" + kplama + "')";
			} else if (!kplain.equals("") && kplain.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_LAIN) = UPPER('" + kplain + "')";
			}

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			System.out.println("sql check :" + sql);
			while (rs.next()) {

				h = new Hashtable();
				h.put("NO_PERMOHONAN_ONLINE", rs
						.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
						.getString("NO_PERMOHONAN_ONLINE"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL"));
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				MaklumatSimati_WithoutNoFail.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}// close

	private static Vector MaklumatSimati_WithoutNoFail_17 = null;

	public static Vector getmaklumatSimati_WithoutNoFail_17() {
		return MaklumatSimati_WithoutNoFail_17;
	}

	@SuppressWarnings("unchecked")
	public static void setmaklumatSimati_WithoutNoFail_17(String kpbarux,
			String kplamax, String kplainx, String nofailx) throws Exception {

		MaklumatSimati_WithoutNoFail_17 = new Vector();

		Db db = null;
		MaklumatSimati_WithoutNoFail_17.clear();
		String sql = "";

		String kpbaru = kpbarux.trim();
		String kplama = kplamax.trim();
		String kplain = kplainx.trim();
		String nofail = nofailx.trim();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT P.NO_PERMOHONAN_ONLINE,P.ID_PERMOHONAN, F.NO_FAIL, SM.NAMA_SIMATI, SM.NO_KP_BARU, "
					+ "SM.NO_KP_LAMA,SM.NO_KP_LAIN, SM.JENIS_KP,P.ID_STATUS,P.NO_SUBJAKET "
					+ "FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, "
					+ "TBLPPKPERMOHONAN P,TBLPFDFAIL F  "
					+ "WHERE SM.ID_SIMATI(+) = MS.ID_SIMATI   "
					+ "AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN(+)   "
					+ "AND P.ID_FAIL = F.ID_FAIL(+)     "
					+ "  AND P.ID_STATUS <> '999' AND P.ID_STATUS <> '169' AND P.ID_STATUS <> '50' AND P.ID_STATUS <> '47' AND P.ID_STATUS <> '70' AND P.ID_STATUS <> '152'  ";

			if (!kpbaru.equals("") && kpbaru.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_BARU) = UPPER('" + kpbaru + "')";
			} else if (!kplama.equals("") && kplama.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_LAMA) = UPPER('" + kplama + "')";
			} else if (!kplain.equals("") && kplain.length() != 0) {
				sql += " AND UPPER(SM.NO_KP_LAIN) = UPPER('" + kplain + "')";
			} else if (!nofail.equals("") && nofail.length() != 0) {
				sql += " AND UPPER(F.NO_FAIL) = UPPER('" + nofail + "')";
			}

			sql += " ORDER BY P.NO_SUBJAKET DESC ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			System.out.println("sql check :" + sql);
			while (rs.next()) {

				h = new Hashtable();
				h.put("NO_PERMOHONAN_ONLINE", rs
						.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
						.getString("NO_PERMOHONAN_ONLINE"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL"));
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				MaklumatSimati_WithoutNoFail_17.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}// close

}
