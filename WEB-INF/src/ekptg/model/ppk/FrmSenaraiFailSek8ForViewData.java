package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

/**
 * @author Razman b Md Zainal
 *
 */
public class FrmSenaraiFailSek8ForViewData {
	static Logger myLogger = Logger.getLogger(FrmSenaraiFailSek8ForViewData.class);
	
	private Vector senaraiFail = null;
	private Vector senaraiFail_by_id = null;
	
	
	
	public void carianFail(String noFail, HttpSession session) throws Exception {
		Db db = null;
		senaraiFail = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					
		sql = "SELECT  C.TARIKH_MASUK,E.KETERANGAN,H.ID_SIMATI, A.SEKSYEN, A.ID_PERMOHONAN, B.NO_FAIL, " +
				"max(C.ID_SUBURUSANSTATUSFAIL) AS id_SUBURUSANSTATUSFAIL, C.AKTIF, E.ID_STATUS," +
				"H.ID_PERMOHONANSIMATI, A.TARIKH_MOHON,B.FLAG_JENIS_FAIL  " +
				"FROM TBLPPKPERMOHONAN A, TBLPFDFAIL B, TBLRUJSUBURUSANSTATUSFAIL C, " +
				"TBLRUJSUBURUSANSTATUS D, TBLRUJSTATUS E, TBLRUJNEGERI F, TBLRUJDAERAH G, " +
				"TBLPPKPERMOHONANSIMATI H WHERE G.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS " +
				"FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG " +
				"AND UR.USER_ID = '" + userId + "' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
				
				sql += " ) AND A.ID_STATUS NOT IN (56,999) AND B.ID_FAIL = A.ID_FAIL " +
				"AND D.ID_SUBURUSANSTATUS = C.ID_SUBURUSANSTATUS AND E.ID_STATUS = D.ID_STATUS " +
				"AND A.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_PERMOHONAN = H.ID_PERMOHONAN " +
				"AND A.ID_NEGERIMHN = F.ID_NEGERI AND A.ID_DAERAHMHN = G.ID_DAERAH " +
				"AND TRIM(UPPER(B.NO_FAIL)) = '" + noFail.trim().toUpperCase() + "' " +
				"GROUP BY C.TARIKH_MASUK,E.KETERANGAN,H.ID_SIMATI, A.SEKSYEN, A.ID_PERMOHONAN, B.NO_FAIL, " +
				"C.AKTIF, E.ID_STATUS, H.ID_PERMOHONANSIMATI, A.TARIKH_MOHON,B.FLAG_JENIS_FAIL " +
				"ORDER BY  C.TARIKH_MASUK,ID_SUBURUSANSTATUSFAIL  ASC";					
				//TRIM(	
					
					
					
			/*		
					sql = "SELECT DISTINCT H.ID_SIMATI, A.SEKSYEN, A.ID_PERMOHONAN, B.NO_FAIL," +
					
							" C.AKTIF, E.ID_STATUS,E.KETERANGAN, H.ID_PERMOHONANSIMATI, A.TARIKH_MOHON,B.FLAG_JENIS_FAIL"
						+ " FROM TBLPPKPERMOHONAN A, TBLPFDFAIL B, TBLRUJSUBURUSANSTATUSFAIL C, TBLRUJSUBURUSANSTATUS D, TBLRUJSTATUS E, TBLRUJNEGERI F, TBLRUJDAERAH G, TBLPPKPERMOHONANSIMATI H"
						+ " WHERE G.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '" + userId + "') " +
						//		"AND C.AKTIF = 1 " +
								"AND A.ID_STATUS not in (56,999)"
						+ " AND B.ID_FAIL = A.ID_FAIL AND D.ID_SUBURUSANSTATUS = C.ID_SUBURUSANSTATUS AND E.ID_STATUS = D.ID_STATUS AND A.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_PERMOHONAN = H.ID_PERMOHONAN AND A.ID_NEGERIMHN = F.ID_NEGERI AND A.ID_DAERAHMHN = G.ID_DAERAH";
					
					sql = sql + " AND UPPER(B.NO_FAIL) = '" + noFail.trim().toUpperCase() + "'";
					
					sql = sql + " ORDER BY  C.ID_SUBURUSANSTATUSFAIL  ASC ";
			*/
					myLogger.info("SQL KEMAS:"+sql.toUpperCase());
					ResultSet rs = stmt.executeQuery(sql);		
					//int count = 1;
					while (rs.next()){
						
						count++;
						h = new Hashtable();
						h.put("bil", count);
						h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
						h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
						h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
						h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
						h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
						h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
						h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
						h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
						
						senaraiFail.addElement(h);
					

					}
					  
						myLogger.info("SENARAIFAIL 1:"+senaraiFail);
				}
				
			}		
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPermohonan", "");
		    	h.put("noFail", "");
		    	h.put("keterangan","Tiada Rekod");
		    	h.put("idStatus", "");
		    	h.put("tarikhMohon", "");
		    	h.put("idPermohonanSimati", "");
		    	h.put("flagjenisfail","");
		    	h.put("seksyen","");
		    	h.put("idSimati","");
				senaraiFail.addElement(h);
			}
			} finally {	if (db != null)	db.close();	}
	}
	
	
	public Vector carianFail_byID(String id_fail, HttpSession session) throws Exception {
		myLogger.info("id_fail XXXX :"+id_fail);
		Db db = null;
		senaraiFail_by_id = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		
       
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			
			myLogger.info("id_fail :"+id_fail);
			
			if (id_fail != null) {
				if (!id_fail.equals("")) {
					
		sql = "SELECT  C.TARIKH_MASUK,E.KETERANGAN,H.ID_SIMATI, A.SEKSYEN, A.ID_PERMOHONAN, B.NO_FAIL, " +
				"max(C.ID_SUBURUSANSTATUSFAIL) AS id_SUBURUSANSTATUSFAIL, C.AKTIF, E.ID_STATUS," +
				"H.ID_PERMOHONANSIMATI, A.TARIKH_MOHON,B.FLAG_JENIS_FAIL  " +
				"FROM TBLPPKPERMOHONAN A, TBLPFDFAIL B, TBLRUJSUBURUSANSTATUSFAIL C, " +
				"TBLRUJSUBURUSANSTATUS D, TBLRUJSTATUS E, TBLRUJNEGERI F, TBLRUJDAERAH G, " +
				"TBLPPKPERMOHONANSIMATI H WHERE G.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS " +
				"FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG " +
				"AND UR.USER_ID = '" + userId + "' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
				
				sql += " ) AND A.ID_STATUS NOT IN (56,999) AND B.ID_FAIL = A.ID_FAIL " +
				"AND D.ID_SUBURUSANSTATUS = C.ID_SUBURUSANSTATUS AND E.ID_STATUS = D.ID_STATUS " +
				"AND A.ID_PERMOHONAN = C.ID_PERMOHONAN " +
				"AND B.ID_FAIL = C.ID_FAIL " +
				"AND A.ID_PERMOHONAN = H.ID_PERMOHONAN " +
				"AND A.ID_NEGERIMHN = F.ID_NEGERI AND A.ID_DAERAHMHN = G.ID_DAERAH " +
				"AND TRIM(UPPER(B.ID_FAIL)) = '" + id_fail + "' " +
				"GROUP BY C.TARIKH_MASUK,E.KETERANGAN,H.ID_SIMATI, A.SEKSYEN, A.ID_PERMOHONAN, B.NO_FAIL, " +
				"C.AKTIF, E.ID_STATUS, H.ID_PERMOHONANSIMATI, A.TARIKH_MOHON,B.FLAG_JENIS_FAIL " +
				"ORDER BY  C.TARIKH_MASUK,ID_SUBURUSANSTATUSFAIL  ASC";					
			
					myLogger.info("SQL KEMAS BY ID:"+sql.toUpperCase());
					ResultSet rs = stmt.executeQuery(sql);		
					//int count = 1;
					while (rs.next()){
						
						count++;
						h = new Hashtable();
						h.put("bil", count);
						h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
						h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
						h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
						h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
						h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
						h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
						h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
						h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
						
						senaraiFail_by_id.addElement(h);
					

					}
					  
						myLogger.info("SENARAIFAIL 1:"+senaraiFail_by_id);
				}
				
			}		
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPermohonan", "");
		    	h.put("noFail", "");
		    	h.put("keterangan","Tiada Rekod");
		    	h.put("idStatus", "");
		    	h.put("tarikhMohon", "");
		    	h.put("idPermohonanSimati", "");
		    	h.put("flagjenisfail","");
		    	h.put("seksyen","");
		    	h.put("idSimati","");
		    	senaraiFail_by_id.addElement(h);
			}
			return senaraiFail_by_id;
			} finally {	if (db != null)	db.close();	}
	}
	public Vector get_carianFail_byID() {
		return senaraiFail_by_id;
	}
	
	public String getStatusBefore(String idPermohonan) throws Exception{
		Db db = null;
		try {
			db = new Db();
			String sql = "";
			
			sql = "SELECT C.ID_STATUS, A.ID_SUBURUSANSTATUSFAIL, A.ID_PERMOHONAN, A.AKTIF"
				+ " FROM TBLRUJSUBURUSANSTATUSFAIL A, TBLRUJSUBURUSANSTATUS B, TBLRUJSTATUS C"
				+ " WHERE B.ID_SUBURUSANSTATUS = A.ID_SUBURUSANSTATUS AND C.ID_STATUS = B.ID_STATUS"
				+ " AND A.AKTIF = 0 AND A.ID_PERMOHONAN = '" + idPermohonan + "' ORDER BY A.ID_SUBURUSANSTATUSFAIL DESC";
	
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return rs.getString("ID_STATUS").toString();
			} else {
				return null;
			}
		} finally {	if (db != null)	db.close();	}
	}
	
	public String getIdPerbicaraan(String noFail) throws Exception {
		Db db = null;
		try {
			db = new Db();
			String sql = "";
			
			sql = "SELECT A.NO_FAIL, D.ID_PERBICARAAN"
				+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKKEPUTUSANPERMOHONAN C, TBLPPKPERBICARAAN D"
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_KEPUTUSANPERMOHONAN = D.ID_KEPUTUSANPERMOHONAN"
				+ " AND TRIM(A.NO_FAIL) = '" + noFail + "'";
	
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return rs.getString("ID_PERBICARAAN").toString();
			} else {
				return "";
			}
		} 
		finally {	if (db != null)	db.close();	}
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}
	
	

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}
}
