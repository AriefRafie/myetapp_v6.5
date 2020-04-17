package ekptg.model.integrasi.lhdn;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.str.FrmSTRSkimBgnnKhasData;

public class FrmSenaraiLHDNData {
	static Logger myLogger = Logger.getLogger(FrmSTRSkimBgnnKhasData.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
//	public Vector carian(String noFail, String jenisKp, String noKp, HttpSession session) throws Exception {
//
//		String sql = "";
//		boolean setLimit = true;
//		Db db = new Db();
//		Vector v = new Vector();
//		Hashtable h = null;
//
//		try {
//			String SQL_SEARCH = "";
//			Statement stmt = db.getStatement();
//			ResultSet rs = null;
//
//			int iCount = 1;
//			String RS_NOFAIL = "", RS_NAMASIMATI = "", RS_USERNAME = "";
//			Date RS_TARIKHCETAK=null;
//			//String RS_TARIKHCETAK = "";
//			
//			if ((!"".equalsIgnoreCase(noFail) && (!"-1".equalsIgnoreCase(noFail)))) {
//				SQL_SEARCH += " AND F.NO_FAIL   LIKE '%" + noFail.toUpperCase() + "%' ";
//			}
//			
//			sql = " SELECT DISTINCT F.ID_FAIL AS ID_FAIL, UPPER(F.NO_FAIL) AS NO_FAIL, UPPER(SM.NAMA_SIMATI) AS NAMA_SIMATI,P.TARIKH_MOHON " +
//					" FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI PSM, TBLRUJSTATUS S, " +
//					" TBLPPKBORANG_HISTORY HIS, USERS U WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_FAIL = HIS.ID_FAIL AND U.USER_ID = HIS.ID_MASUK" +
//					" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN" +
//					" AND F.NO_FAIL IS NOT NULL" +
//					" AND F.ID_SEKSYEN = 2" +
//					" AND F.ID_SUBURUSAN IN (59, 60)" +
//					" AND PSM.ID_SIMATI = SM.ID_SIMATI" +
//					" AND P.ID_STATUS = S.ID_STATUS" +
//					" AND F.NO_FAIL IS NOT NULL" +
//					" AND P.TARIKH_MOHON IS NOT NULL";  
//					//+ SQL_SEARCH +
////					" ORDER BY P.TARIKH_MOHON DESC ";
//			
////			//noFail
//			if (noFail != null) {
//				if (!noFail.trim().equals("")) {
//					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%' ||'"
//							+ noFail.trim().toUpperCase() + "'|| '%'";
//				}
//				
//			}
//			
//			//jenisKp & noKp
//			if (jenisKp != null) {
//					if (!jenisKp.trim().equals("")) {
//							if (noKp != null) {
//									if (!noKp.trim().equals("")) {
//											if (jenisKp.equals("1")){
//												setLimit = false;
//												sql = sql + " AND UPPER(SM.NO_KP_BARU) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
//											}
//											else if (jenisKp.equals("2")){
//												setLimit = false;
//												sql = sql + " AND UPPER(SM.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
//											}
//											else if (jenisKp.equals("3")){
//												setLimit = false;
//												sql = sql + " AND UPPER(SM.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
//											}
//									}
//							}
//					}
//				
//								
//			}
//			
//			sql = sql + " ORDER BY P.TARIKH_MOHON DESC";
////					
//			myLogger.info("sql ::" + sql);
//			rs = stmt.executeQuery(sql);
//			System.out.println("masuk sini");
//			int bil = 1;
//			while (rs.next()) {
//				h = new Hashtable();
//				h.put("bil", bil);
//				h.put("noFail",
//						rs.getString("NO_FAIL") == null ? "" : rs
//								.getString("NO_FAIL"));
//				h.put("namaSimati",
//						rs.getString("NAMA_SIMATI") == null ? "" : rs
//								.getString("NAMA_SIMATI"));
//				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs
//						.getString("ID_FAIL").toUpperCase());
//				
//				v.addElement(h);
//				bil++;
//			}
//
//		}catch (Exception re) {
//			myLogger.error("Error: ", re);
//			throw re;
//			} finally {
//			if (db != null) {
//				db.close();
//			}
//		}
//		return v;
//	}
	
	public Vector carian(String noFail, String jenisKp, String noKp, HttpSession session) throws Exception {
		
		String sql = "";
		boolean setLimit = true;
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

//			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, H.ID_PERMOHONANSIMATI, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI,"
//				+ " C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS"
//				+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSTATUS G, " +
//						" TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J" 
//				+ " WHERE A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI " +
//						" AND D.ID_PEMOHON = B.ID_PEMOHON "
//				//+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 AND A.FLAG_JENIS_FAIL = 1 AND B.ID_STATUS != 999";				
//				+ " AND B.ID_STATUS = '21' " +
//						" AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH  " +
//						"  AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 " +
//						"  AND B.ID_STATUS = G.ID_STATUS AND B.ID_STATUS != 999";
			
			
			sql = "SELECT DISTINCT b.id_fail, a.no_fail, a.tarikh_daftar_fail,"
                +"h.id_permohonansimati, b.id_permohonan, b.tarikh_mohon,"
                +"b.tarikh_masuk, c.id_simati, c.nama_simati, d.id_pemohon,"
                + "d.nama_pemohon, d.no_kp_baru, d.no_kp_lama, d.no_kp_lain,g.keterangan, g.id_status"
                + " FROM tblpfdfail a,tblppkpermohonan b,tblppksimati c,tblppkpemohon d,tblrujstatus g," 
                + "tblppkpermohonansimati h, tblrujnegeri i, tblrujdaerah j"
                +" WHERE a.id_fail = b.id_fail"
                +" AND h.id_permohonan = b.id_permohonan"
                +" AND h.id_simati = c.id_simati"
                +" AND d.id_pemohon = b.id_pemohon"
                +" AND b.id_negerimhn = i.id_negeri"
                +" AND b.id_daerahmhn = j.id_daerah"
                +" AND b.id_status = g.id_status"
                //BELLA INTEGRASI LHDN 28/9/2017
                //+" AND (b.seksyen = 17 OR b.seksyen =8) AND b.flag_jenis_permohonan = 1 AND b.id_status = '21' AND b.id_status != 999";
                +" AND b.seksyen =8 AND b.flag_jenis_permohonan = 1 AND b.id_status = '21' AND b.id_status != 999";
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			//jenisKp & noKp
			if (jenisKp != null) {
					if (!jenisKp.trim().equals("")) {
							if (noKp != null) {
									if (!noKp.trim().equals("")) {
											if (jenisKp.equals("1")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("2")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
											else if (jenisKp.equals("3")){
												setLimit = false;
												sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											}
									}
							}
					}
				
								
			}
						
			//sql = sql + " ORDER BY B.ID_PERMOHONAN";
			
			if (setLimit == true) 
			{
			sql = sql + " AND ROWNUM <= 50 ";
			}
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer count = 0;
			myLogger.info("sql ::" + sql);
			rs = stmt.executeQuery(sql);
			System.out.println("masuk sini");
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("noFail",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("namaSimati",
						rs.getString("NAMA_SIMATI") == null ? "" : rs
								.getString("NAMA_SIMATI"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				
				v.addElement(h);
				bil++;
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			} finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}

	
	public Vector list(String idFail) throws Exception {

		String sql = "";
		boolean setLimit = true;
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			int iCount = 1;
			String RS_NOFAIL = "", RS_NAMASIMATI = "", RS_USERNAME = "";
			Date RS_TARIKHCETAK=null;
			//String RS_TARIKHCETAK = "";
			
			if ((!"".equalsIgnoreCase(idFail) && (!"-1".equalsIgnoreCase(idFail)))) {
				SQL_SEARCH += " AND HIS.ID_FAIL ="+ idFail;
			}
			
			sql = " SELECT HIS.ID_FAIL AS ID_FAIL, U.USER_NAME AS NAMA_PENGGUNA, HIS.TARIKH_KEMASKINI " +
					" FROM TBLPPKBORANG_HISTORY HIS, USERS U WHERE U.USER_ID = HIS.ID_MASUK" + SQL_SEARCH +
					" ORDER BY HIS.TARIKH_KEMASKINI DESC";
//					
			myLogger.info("sql ::" + sql);
			rs = stmt.executeQuery(sql);
			System.out.println("masuk sini");
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NamaPengguna", rs.getString("NAMA_PENGGUNA") == null ? "" : rs
						.getString("NAMA_PENGGUNA").toUpperCase());
				h.put("TarikhCetak", rs.getDate("TARIKH_KEMASKINI") == null ? ""
						: sdf.format(rs.getDate("TARIKH_KEMASKINI")));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				
				v.addElement(h);
				bil++;
			}

		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			} finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
}
