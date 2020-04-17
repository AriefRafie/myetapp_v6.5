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

import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;

public class FrmPerintah17Bean implements IMaklumatPerintah{
	
	private static Logger myLog = Logger.getLogger(ekptg.model.ppk.perintah.FrmPerintah17Bean.class);
	Connection conn = null;
	Db db = null;
	private IHtp iHTP = null;  
	private Vector senaraiFail_semakanPerintahHQ = new Vector();
	SQLRenderer r = null;
	String sql = "";

	public Vector carianFail_semakanPerintahHQ(String role,String noFail, String namaPemohon, String namaSimati
			, String jenisKp, String noKp, HttpSession session) throws Exception {
		
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
				+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J" 
				+ " WHERE  A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI " +
						" AND D.ID_PEMOHON = B.ID_PEMOHON "
				+ " AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH " +
						" AND B.ID_STATUS = G.ID_STATUS AND B.SEKSYEN = 17 AND B.FLAG_JENIS_PERMOHONAN = 1 AND B.ID_STATUS = '21' AND B.ID_STATUS != 999";
				
			boolean setLimit = true;
			
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
							+ namaPemohon.toUpperCase() + "'|| '%'";
				}
				setLimit = false;
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.toUpperCase() + "'|| '%'";
				}
				
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
				if (!jenisKp.trim().equals("")) {
					if (noKp != null) {
						if (!noKp.trim().equals("")) {
							if (jenisKp.equals("1")){
								setLimit = false;
								sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("2")){
								setLimit = false;
								sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("3")){
								setLimit = false;
								sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
						}
					}
				}
				
			}
				
			//myLog.info("setLimit :"+setLimit);
			if (setLimit == true) 
			{
			sql = sql + " AND ROWNUM <= 50 ";
			}
			//myLog.info("SENARAI PERINTAH SEK17 HQ"+sql);
			
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
	
	public Vector carianFail(String noFail, String namaPemohon, String namaSimati, String jenisKp, String noKp, HttpSession session) throws Exception {
		
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
				+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 17 AND B.FLAG_JENIS_PERMOHONAN = 1 AND B.ID_STATUS != 999";
				
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
							+ namaPemohon.toUpperCase() + "'|| '%'";
				}
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.toUpperCase() + "'|| '%'";
				}
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
				if (!jenisKp.trim().equals("")) {
					if (noKp != null) {
						if (!noKp.trim().equals("")) {
							if (jenisKp.equals("1")){
								sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("2")){
								sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("3")){
								sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
						}
					}
				}
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			
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


	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
	
		
}
