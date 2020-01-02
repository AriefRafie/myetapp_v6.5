package ekptg.model.ppk.perintah;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

//import ekptg.model.htp.HtpBean;
//import ekptg.model.htp.IHtp;
/**
 * 
 * @author mohamadrosli
 * 2019/12/23, Sync dengan LIVE - Kondisi MyID ditukar kepada MyID Simati
 */
public class FrmPerintah8Bean implements IMaklumatPerintah{
	
	//private IHtp iHTP = null;  
	private static Logger myLog = Logger.getLogger(ekptg.model.ppk.perintah.FrmPerintah8Bean.class);
	private Vector senaraiFail_semakanPerintahHQ = new Vector();
	Connection conn = null;
	Db db = null;
	SQLRenderer r = null;
	String sql = "";

	public Vector carianFail_semakanPerintahHQ(String role
		,String noFail
		,String namaPemohon
		,String namaSimati
		,String jenisKp
		,String noKp
		,HttpSession session) 
		throws Exception {		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		senaraiFail_semakanPerintahHQ.clear();
		String sql = "";
		boolean setLimit = true;

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
                +" h.id_permohonansimati, b.id_permohonan, b.tarikh_mohon,"
                +" b.tarikh_masuk, c.id_simati, c.nama_simati, d.id_pemohon,"
                +" d.nama_pemohon, d.no_kp_baru, d.no_kp_lama, d.no_kp_lain,g.keterangan, g.id_status"
                +" FROM tblpfdfail a,tblppkpermohonan b,tblppksimati c,tblppkpemohon d,tblrujstatus g," 
                +" tblppkpermohonansimati h, tblrujnegeri i, tblrujdaerah j"
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
			//namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.trim().toUpperCase() + "'|| '%'";
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
//												sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
										
							}else if (jenisKp.equals("2")){
								setLimit = false;
								sql = sql + " AND UPPER(C.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
//												sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
											
							}else if (jenisKp.equals("3")){
								setLimit = false;
								sql = sql + " AND UPPER(C.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
//												sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
						
							}
									
						}
							
					}
					
				}
				
			}
						
			//sql = sql + " ORDER BY B.ID_PERMOHONAN";
			if (setLimit == true) {
				sql = sql + " AND ROWNUM <= 50 ";
			}
			//myLogger.info("SENARAI PERINTAH SEK8 HQ"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				senaraiFail_semakanPerintahHQ.addElement(h);
				bil++;
				count++;
			
			}
			return senaraiFail_semakanPerintahHQ;

		} finally {
			if (db != null)
				db.close();
		}
		
	}

	public Vector carianFail(String noFail, String namaPemohon, String namaSimati, String jenisKp, String noKp, HttpSession session) 
		throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		senaraiFail_semakanPerintahHQ.clear();
		String sql = "";
	
		try {
			db = new Db();
			Statement stmt = db.getStatement();
	
			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, H.ID_PERMOHONANSIMATI, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI,"
				+ " C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS"
				+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J" 
				+ " WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = " + userId + " ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
				
				sql += " )"
				+ " AND A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI AND D.ID_PEMOHON = B.ID_PEMOHON AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS"
				//+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 AND A.FLAG_JENIS_FAIL = 1 AND B.ID_STATUS != 999";				
				+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 AND B.ID_STATUS != 999";
				
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
				if (!jenisKp.trim().equals("")) {
					if (noKp != null) {
						if (!noKp.trim().equals("")) {
							if (jenisKp.equals("1")){
								sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("2")){
								sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("3")){
								sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.trim().toUpperCase() + "'|| '%'";
							}
						}
					}
				}
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("SENARAI PERINTAH SEK8"+sql);
	
			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				senaraiFail_semakanPerintahHQ.addElement(h);
				bil++;
				count++;
			}
			return senaraiFail_semakanPerintahHQ;

		} finally {
			if (db != null)
				db.close();
		}
		
	}

//	private IHtp getIHTP(){
//		if(iHTP== null)
//			iHTP = new HtpBean();
//		return iHTP;
//	}	
	
		
}
