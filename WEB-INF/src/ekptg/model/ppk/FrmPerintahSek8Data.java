package ekptg.model.ppk;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import EDU.oswego.cs.dl.util.concurrent.misc.Fraction;
import ekptg.faraid.EkptgEngine;
import ekptg.helpers.DB;
import ekptg.helpers.Pecahan;
import ekptg.helpers.Utils;

/**
 * 
 * 
 * CHANGES
 * 31/3/2010 - Azam change rs.getInt("BB_SIMATI") to rs.getLong("BB_SIMATI")
 */


/*

--BY ID HTA, NAK DAPAT MAKLUMAT OB
SELECT HTADTL.ID_PERINTAHHTAOBDTL, OB.ID_OB,OB.NAMA_OB FROM TBLPPKPERINTAHHTAOBDTL HTADTL,TBLPPKOB OB 
WHERE HTADTL.ID_OB = OB.ID_OB AND HTADTL.ID_PERINTAHHTAOBMST  IN
(SELECT HTA.ID_PERINTAHOBMST FROM TBLPPKHTA HTA
--FILTER BY ID_HTA, ID_PERMOHONANSIMATI
) AND STATUS_TADBIR IS NOT NULL

--BY OB,NAK DAPATKAN PT/PA
SELECT HTADTL.ID_PERINTAHHTAOBDTL, OB.ID_OB,OB.NAMA_OB FROM TBLPPKPERINTAHHTAOBDTL HTADTL,TBLPPKOB OB 
WHERE HTADTL.ID_PA1 = OB.ID_OB AND HTADTL.ID_PERINTAHHTAOBDTL  = 155744
UNION
SELECT HTADTL.ID_PERINTAHHTAOBDTL, OB.ID_OB,OB.NAMA_OB FROM TBLPPKPERINTAHHTAOBDTL HTADTL,TBLPPKOB OB 
WHERE HTADTL.ID_PA2 = OB.ID_OB AND HTADTL.ID_PERINTAHHTAOBDTL  = 155744
UNION
SELECT HTADTL.ID_PERINTAHHTAOBDTL, OB.ID_OB,OB.NAMA_OB FROM TBLPPKPERINTAHHTAOBDTL HTADTL,TBLPPKOB OB 
WHERE HTADTL.ID_PA3 = OB.ID_OB AND HTADTL.ID_PERINTAHHTAOBDTL  = 155744
UNION
SELECT HTADTL.ID_PERINTAHHTAOBDTL, OB.ID_OB,OB.NAMA_OB FROM TBLPPKPERINTAHHTAOBDTL HTADTL,TBLPPKOB OB 
WHERE HTADTL.ID_PA4 = OB.ID_OB AND HTADTL.ID_PERINTAHHTAOBDTL  = 155744

--letak 4 field baru dekat TBLPPKPERINTAHHTAOBDTL

*/

public class FrmPerintahSek8Data {	
	
	static Logger myLogger = Logger.getLogger(FrmPerintahSek8Data.class);
	
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy"); //arief add
	
	private Vector senaraiFail = new Vector();
	private Vector senaraiFail_semakanPerintahHQ = new Vector();
	private Vector beanMaklumatPermohonan = new Vector();
	private Vector beanHeaderDetail = new Vector();
	
	private Vector senaraiHTA = new Vector();
	private Vector senaraiHTAPopup = new Vector();
	private Vector beanMaklumatHTA = new Vector();
	private Vector senaraiHTATH = new Vector();
	private Vector beanMaklumatHTATH = new Vector();
	private Vector senaraiHA = new Vector();
	private Vector senaraiHAARB = new Vector();
	private Vector senaraiOBARB = new Vector();	
	private Vector senaraiPerintahHAARB = new Vector();		
	private Vector senaraiHAPopup = new Vector();
	private Vector beanMaklumatHA = new Vector();
	
	private Vector senaraiHTAPT = new Vector();
	private Vector senaraiHAPT = new Vector();
	private Vector senaraiHTAPKT = new Vector();
	private Vector senaraiHAPKT = new Vector();
	private Vector senaraiHTAPL = new Vector();
	private Vector senaraiHAPL = new Vector();
	private Vector senaraiHTAPF = new Vector();
	private Vector senaraiHAPF = new Vector();
	
	private Vector senaraiHTAPTDTL = new Vector();
	private Vector senaraiHAPTDTL = new Vector();
	private Vector senaraiHTAPKTDTL = new Vector();
	private Vector senaraiHAPKTDTL = new Vector();
	private Vector senaraiHTAPFDTL = new Vector();
	private Vector senaraiHAPFDTL = new Vector();
	
	private Vector senaraiHTAPTDTLHilang = new Vector();
	private Vector senaraiHAPTDTLHilang = new Vector();
	
	private Vector beanMaklumatHTAPL = new Vector();
	private Vector beanMaklumatHAPL = new Vector();
	
	private Vector senaraiPembahagianHTAPKTDTL = new Vector();
	
	//arief add
	private static Vector listSemak = new Vector();
	
	//arief add
	private static Vector listSemakWithData = new Vector();
	
	//arief add
	public static Vector getListSemak() {
		return listSemak;
	}
	
	//arief add
	public static Vector getListSemakWithData() {
		return listSemakWithData;
	}

	@SuppressWarnings("unchecked")
	public void carianFail(String noFail, String namaPemohon, String namaSimati, String jenisKp, String noKp, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		System.out.println("userId==="+userId);
		senaraiFail.clear();
		String sql = "";
		String sqlOLD = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sqlOLD = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, H.ID_PERMOHONANSIMATI, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI,"
				+ " C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS"
				+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J" 
				+ " WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = " + userId + " ";
				
				 sqlOLD += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
				
				sqlOLD+=" )"
				+ " AND A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI AND D.ID_PEMOHON = B.ID_PEMOHON AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS"
				//+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 AND A.FLAG_JENIS_FAIL = 1 AND B.ID_STATUS != 999";				
				+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 AND B.ID_STATUS != 999";
				
			/*
			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, H.ID_PERMOHONANSIMATI, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI, " +
					" C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS " +
					" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSUBURUSANSTATUSFAIL E, " +
					" TBLRUJSUBURUSANSTATUS F, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J" +
					" WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR " +
					" WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = " + userId + " ";
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					sql += " )" +
					" AND A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI AND D.ID_PEMOHON = B.ID_PEMOHON " +
					" AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS  " +
					" AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH " +
					" AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 AND B.ID_STATUS != 999 " +
					" AND B.ID_PERMOHONAN NOT IN (  SELECT  p.id_permohonan" +
					" FROM Tblppkperbicaraan b, Tblppkperintah pt, Tblppkkeputusanpermohonan kp, Tblppkpermohonan p" +
					" WHERE pt.id_perbicaraan = b.id_perbicaraan" +
					" AND b.id_keputusanpermohonan = kp.id_keputusanpermohonan" +
					" AND kp.id_permohonan = p.id_permohonan" +
					" AND pt.CHECK_KIV = 1)";
					*/
				
				sql =   " SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, H.ID_PERMOHONANSIMATI, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI, "+ 
						" C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS   "+
						" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, " +
						//" TBLRUJSUBURUSANSTATUSFAIL E,  TBLRUJSUBURUSANSTATUS F, " +
						" TBLRUJSTATUS G,  "+
						" TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J, "+
						" TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN BIC, TBLPPKPERINTAH PT "+
						" WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR  WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG  "+
						" AND UR.USER_ID = "+userId+"  UNION  SELECT DISTINCT PBU_U.ID_DAERAHURUS   FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U   "+
						" WHERE ID_STATUS = 2   AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY')  "+ 
						" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY')   "+
						" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG   AND PBU.ID_PEMOHON = "+userId+"   )  "+
						" AND A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI  "+
						" AND D.ID_PEMOHON = B.ID_PEMOHON  " +
						//" AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS    "+
						//" AND G.ID_STATUS = F.ID_STATUS " +
						//" AND B.ID_PERMOHONAN = E.ID_PERMOHONAN " +
						" AND b.id_status = g.id_status "+
						" AND B.ID_NEGERIMHN = I.ID_NEGERI  "+
						" AND B.ID_DAERAHMHN = J.ID_DAERAH  " +
						//" AND E.AKTIF = 1 " +
						//" AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 8  "+
						" AND B.ID_STATUS IN (41,21,25,107) AND B.SEKSYEN = 8  "+
						" AND B.FLAG_JENIS_PERMOHONAN = 1 " +
						//" AND B.ID_STATUS != 999   "+
						" AND PT.ID_PERBICARAAN = BIC.ID_PERBICARAAN AND  BIC.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN AND KP.ID_PERMOHONAN = B.ID_PERMOHONAN "+
						" AND NVL(PT.CHECK_KIV,0) <> 1 ";
			
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
			myLogger.info("sql------"+sql);
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
				senaraiFail.addElement(h);
				bil++;
				count++;
			}

			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPermohonanSimati", "");
		    	h.put("idPermohonan", "");
		    	h.put("idFail", "");
		    	h.put("noFail", "Tiada Rekod");
		    	h.put("tarikhMohon","");
		    	h.put("tarikhMasuk","");
		    	h.put("keterangan","");
		    	h.put("idSimati", "");
		    	h.put("namaSimati", "");
		    	h.put("idStatus", "");
				senaraiFail.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
public void carianFail_semakanPerintahHQ(String role,String noFail, String namaPemohon, String namaSimati, String jenisKp, String noKp, HttpSession session) throws Exception {
		
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
				+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSTATUS G, " +
						" TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J" 
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI " +
						" AND D.ID_PEMOHON = B.ID_PEMOHON "
				//+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 AND A.FLAG_JENIS_FAIL = 1 AND B.ID_STATUS != 999";				
				+ " AND B.ID_STATUS = '21' " +
						" AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH  " +
						"  AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 " +
						"  AND B.ID_STATUS = G.ID_STATUS AND B.ID_STATUS != 999";
			
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
			myLogger.info("SENARAI PERINTAH SEK8 HQ"+sql);
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

			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPermohonanSimati", "");
		    	h.put("idPermohonan", "");
		    	h.put("idFail", "");
		    	h.put("noFail", "Tiada Rekod");
		    	h.put("tarikhMohon","");
		    	h.put("tarikhMasuk","");
		    	h.put("keterangan","");
		    	h.put("idSimati", "");
		    	h.put("namaSimati", "");
		    	h.put("idStatus", "");
				senaraiFail_semakanPerintahHQ.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setMaklumatPermohonan(String idPermohonan, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		beanMaklumatPermohonan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql =  "SELECT distinct pm.id_negeri, n.id_Negeri, n.nama_Negeri,f.id_Fail, f.no_Fail, d.id_Daerah, p.id_Permohonan, "; 
			sql += "p.tarikh_Mohon, s.no_Kp_Baru, s.no_Kp_Lama, s.jenis_Kp, s.no_Kp_Lain, s.id_Simati, ";
			sql += "s.nama_Simati, s.tarikh_Mati, pm.id_Pemohon, pm.nama_Pemohon, pm.no_kp_baru, ";
			sql += "pm.no_kP_lama, pm.jenis_Kp, pm.no_Kp_lain, pm.alamat_1, pm.alamat_2, pm.alamat_3, "; 
			sql += "pm.poskod, pm.bandar, d.nama_Daerah, p.seksyen, st.keterangan, ";
			sql += "p.id_Status, u.nama_pejabat, mosi.id_Permohonansimati, s.umur, s.jantina, "; 
			sql += "pm.umur, pm.jantina,u.id_pejabatjkptg, p.no_subjaket, sstf.id_suburusanstatusfail, daerahPejabat.nama_daerah as daerah_pejabat,u.id_negeri as id_negeripejabat "; 
			sql += "FROM Tblpfdfail f,Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, "; 
			sql += "Tblppkpemohon pm, Tblrujstatus st, tblrujsuburusanstatusfail sstf, tblrujsuburusanstatus sst, "; 
			sql += "tblrujpejabatjkptg u, Tblppkpermohonansimati mosi, Users_Internal ur, Tblrujdaerah daerahPejabat ";
			sql += "WHERE f.id_Negeri = n.id_Negeri(+) ";
			sql += "AND sstf.id_permohonan = p.id_permohonan ";
			sql += "AND sstf.id_suburusanstatus = sst.id_suburusanstatus ";
			sql += "AND sst.id_status = st.id_status ";
			sql += "AND p.id_Daerahmhn = d.id_Daerah(+) ";  
			sql += "AND ur.user_id = "+userId ;
			sql += "And ur.ID_PEJABATJKPTG = u.ID_PEJABATJKPTG "; 
			sql += "AND p.id_Fail = f.id_Fail ";
			sql += "AND pm.id_pemohon = p.id_pemohon ";   
			sql += "AND s.id_Simati = mosi.id_Simati ";
			sql += "AND p.id_Permohonan = mosi.id_Permohonan ";   
			sql += "AND st.id_Status = p.id_Status ";
			sql += "AND d.id_daerah = p.id_daerahmhn "; 
			sql += "AND u.id_daerah = daerahPejabat.id_daerah "; 
		//	sql += "AND sstf.aktif = 1 ";
			sql += " AND p.id_Permohonan = " +idPermohonan ;
System.out.println("sini======="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
		    Hashtable h;
			
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
		    	  h.put("idFail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
		    	  h.put("idDaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
		    	  h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
		    	  h.put("tarikhMohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
		    	  h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
		    	  h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
		    	  h.put("tarikhMati",  rs.getDate("tarikh_Mati")==null?"": sdf.format(rs.getDate("tarikh_Mati")));
		    	  h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
		    	  h.put("namaPemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
		    	  h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
		    	  h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
		    	  h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
		    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
		    	  h.put("idnegeri", rs.getString(16)==null?"":rs.getString(16));
		    	  h.put("namanegeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
		    	  h.put("namadaerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
		    	  h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
		    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	  h.put("id_Status", rs.getString("id_Status")==null?"":rs.getString("id_Status"));
		    	  
		    	  if(rs.getString("id_negeripejabat").equals("7")){
		    		  h.put("namaPejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
		    	  }
		    	  else{
		    		  h.put("namaPejabat", rs.getString("nama_pejabat")==null && rs.getString("daerah_pejabat")==null?"":rs.getString("nama_pejabat") + ", " + rs.getString("daerah_pejabat"));
		    	  }
		    	  
		    	  
//		    	  h.put("namaPejabat", rs.getString("nama_pejabat")==null && rs.getString("daerah_pejabat")==null?"":rs.getString("nama_pejabat"));
		    	  //h.put("pmidnegeri", rs.getString(23)==null?"0":rs.getString(23));
		    	  h.put("id_pejabatjkptg", rs.getString("id_pejabatjkptg"));
		    	  //h.put("pmNama_negeri", rs.getString(24)==null?"":rs.getString(24));
		    	  if(rs.getString(3) == null || rs.getString(3) ==""){
			    		h.put("pmNama_negeri","");
			    	}else{
			    		h.put("pmNama_negeri",rs.getString(3));
			    	}
		    	  if(rs.getString(2) == null || rs.getString(2) ==""){
			    		h.put("pmidnegeri","0");
			    	}else{
			    		h.put("pmidnegeri",rs.getString(2));
			    	}
		    	  
		    	  beanMaklumatPermohonan.addElement(h);
		      	}      
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdPerintah(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERMOHONAN");
			r.add("C.ID_PERINTAH");

			r.add("A.ID_KEPUTUSANPERMOHONAN",r.unquote("B.ID_KEPUTUSANPERMOHONAN"));
			r.add("B.ID_PERBICARAAN",r.unquote("C.ID_PERBICARAAN"));
			
			r.add("A.ID_PERMOHONAN", idPermohonan);
			r.add("C.FLAG_JENIS_KEPUTUSAN", "0");

			sql = r.getSQLSelect("TBLPPKKEPUTUSANPERMOHONAN A, TBLPPKPERBICARAAN B, TBLPPKPERINTAH C");
			myLogger.info("getIdPerintah = "+sql);
			ResultSet rs = stmt.executeQuery(sql);


			if (rs.next()){
				return rs.getString("ID_PERINTAH").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdPerbicaraan(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.ID_PERBICARAAN");
			
			r.add("C.ID_PERINTAH", idPerintah);
			r.add("C.FLAG_JENIS_KEPUTUSAN", "0");

			sql = r.getSQLSelect("TBLPPKPERINTAH C");
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_PERBICARAAN").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTA(String idPermohonanSimati) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			//sql = "SELECT ID_HTA FROM TBLPPKHTA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' AND JENIS_HTA = 'Y'";
			sql = "SELECT A.ID_HTA FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A" +
					" WHERE A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'" +
					" AND A1.ID_HTA = A.ID_HTA " +
					" AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI AND A.JENIS_HTA = 'Y'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTATH(String idPermohonanSimati) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			//sql = "SELECT ID_HTA FROM TBLPPKHTA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' AND JENIS_HTA = 'T'";
			sql = "SELECT A.ID_HTA FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A " +
					" WHERE A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'" +
					" AND A1.ID_HTA = A.ID_HTA AND A.ID_PERMOHONANSIMATI =  A1.ID_PERMOHONANSIMATI  " +
					" AND A.JENIS_HTA = 'T'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHA(String idPermohonanSimati) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A1.ID_HA FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A " +
					" WHERE A1.ID_HA = A.ID_HA AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
					" AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
			myLogger.info("checkExistHA");
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void registerHTATHIntoPKT(String idPerintah, String idPermohonanSimati, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlInsert = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_HTA FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A" +
					" WHERE A1.ID_HTA = A.ID_HTA AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
							" AND A.JENIS_HTA = 'T' AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'" +
					" AND A1.ID_HTA NOT IN "
				+ "(SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "')";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){

				SQLRenderer r = new SQLRenderer();
								
				//TBLPPKPERINTAHHTAOBMST
				long idPerintahHTAOBMST = DB.getNextID("TBLPPKPERINTAHHTAOBMST_SEQ");
				r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
				r.add("ID_HTA", rs.getString("ID_HTA"));
				r.add("ID_PERINTAH", idPerintah);
				r.add("ID_JENISPERINTAH", "2");
				r.add("FLAG_HARTA", "B");
				
				r.add("ID_MASUK", userId);			
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHTAOBMST");
				myLogger.info("sqlInsert = "+sqlInsert);
				stmt.executeUpdate(sqlInsert);
				
				//INSERT PENTADBIR
				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHTAPerintahKuasaTadbir(idPerintahHTAOBMST,hash.get("idOB").toString(),session);
				}
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void insertDTLHTAPerintahKuasaTadbir(long idPerintahHTAOBMST, String idOB, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBDTL
			long id = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
			r.add("ID_PERINTAHHTAOBDTL", id);
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_OB", idOB);
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private Vector getPentadbir(String idPerintah) throws Exception{
		
		Db db = null;
		Vector list = new Vector();
		list.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT(A.ID_OB) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = '2' AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_OB) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = '2' AND B.ID_PERINTAH = '" + idPerintah + "'";
			
//			sql = "SELECT DISTINCT(A.ID_PA1) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAH = '" + idPerintah + "'"
//			+ " UNION" 
//			+ " SELECT DISTINCT(A.ID_PA2) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAH = '" + idPerintah + "'"
//			+ " UNION" 
//			+ " SELECT DISTINCT(A.ID_PA3) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAH = '" + idPerintah + "'"
//			+ " UNION" 
//			+ " SELECT DISTINCT(A.ID_PA4) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAH = '" + idPerintah + "'"
//			+ " UNION" 
//			+ " SELECT DISTINCT(A.ID_PA1) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAH = '" + idPerintah + "'"
//			+ " UNION" 
//			+ " SELECT DISTINCT(A.ID_PA2) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAH = '" + idPerintah + "'"
//			+ " UNION" 
//			+ " SELECT DISTINCT(A.ID_PA3) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAH = '" + idPerintah + "'"
//			+ " UNION" 
//			+ " SELECT DISTINCT(A.ID_PA4) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAH = '" + idPerintah + "'"
//			+ " UNION" 
//			+ " SELECT DISTINCT(A.ID_OB) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = '2' AND B.ID_PERINTAH = '" + idPerintah + "'"
//			+ " UNION" 
//			+ " SELECT DISTINCT(A.ID_OB) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = '2' AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){
				if (rs.getString("ID_OB") != null){
					if (rs.getString("ID_OB").trim().length() > 0){
						h = new Hashtable();
						h.put("idOB", rs.getString("ID_OB"));
						list.addElement(h);
					}
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return list;
	}
	
	private Vector getPemegangAmanah(String idOB) throws Exception{
		
		Db db = null;
		Vector list = new Vector();
		list.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_OB FROM TBLPPKPENJAGA WHERE ID_OBMINOR = '" + idOB + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){
				if (rs.getString("ID_OB") != null){
					if (rs.getString("ID_OB").trim().length() > 0){
						h = new Hashtable();
						h.put("idOB", rs.getString("ID_OB"));
						list.addElement(h);
					}
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTA(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("G.KETERANGAN");
			r.add("A.CATATAN");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");
			r.add("B.NO_PT");
			r.add("H.KOD_JENIS_PB");

			r.add("A.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("B.JENIS_HTA", "Y");

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, " +
					" E.NAMA_DAERAH, F.NAMA_MUKIM, G.KETERANGAN, A.CATATAN, B.BA_SIMATI, B.BB_SIMATI, " +
					" B.NO_PT, H.KOD_JENIS_PB  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJJENISHAKMILIK C, " +
					" TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, " +
					" TBLRUJJENISPB H " +
					" WHERE B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B1.ID_HTA = B.ID_HTA " +
							" AND A.ID_HTA = B1.ID_HTA  " +
							" AND B.ID_NEGERI = D.ID_NEGERI  " +
					" AND B.ID_DAERAH = E.ID_DAERAH  AND B.ID_MUKIM = F.ID_MUKIM  " +
					" AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH  AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+)  " +
					" AND B.STATUS_PEMILIKAN = H.ID_JENISPB(+)  AND A.ID_PERINTAH = '"+idPerintah+"'  " +
					" AND B.JENIS_HTA = 'Y'  ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			myLogger.info("PERINTAH HTA X ### setDataSenaraiHTA(String idPerintah) :"+sql);		
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("keteranganHakmilik", (rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : (rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " + rs.getString("NO_HAKMILIK").toUpperCase() + ", ")) 
						+ rs.getString("NO_PT") + ", " 
						+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
						+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("jenisPerintah",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				h.put("kodPB", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				senaraiHTA.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");
		    	h.put("jenisPerintah","");
		    	h.put("catatan","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("bahagianSimati","");
		    	h.put("kodPB","");
		    	senaraiHTA.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPopup(String idPermohonanSimati, String idPerintah) throws Exception {
		Db db = null;
		senaraiHTAPopup.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*
			sql = "SELECT A.ID_HTA, A.NO_HAKMILIK, B.KOD_JENIS_HAKMILIK, C.NAMA_NEGERI, D.NAMA_DAERAH, E.NAMA_MUKIM, A.ID_SIMATI, A.JENIS_HTA, F.KOD_JENIS_PB"
				+ " FROM TBLPPKHTA A, TBLRUJJENISHAKMILIK B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISPB F"
				+ " WHERE A.ID_JENISHM = B.ID_JENISHAKMILIK AND A.ID_NEGERI = C.ID_NEGERI AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_MUKIM = E.ID_MUKIM AND A.STATUS_PEMILIKAN = F.ID_JENISPB(+)" 
				+ " AND A.JENIS_HTA = 'Y' AND A.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
			*/	
			
			sql = "SELECT A.ID_HTA, A.NO_HAKMILIK, B.KOD_JENIS_HAKMILIK, " +
					" C.NAMA_NEGERI, D.NAMA_DAERAH, E.NAMA_MUKIM, A.ID_SIMATI, " +
					" A.JENIS_HTA, F.KOD_JENIS_PB"
				+ " FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A, " +
						" TBLRUJJENISHAKMILIK B, TBLRUJNEGERI C, " +
						" TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISPB F"
				+ " WHERE A.ID_JENISHM = B.ID_JENISHAKMILIK " +
						" AND A.ID_NEGERI = C.ID_NEGERI " +
						" AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_MUKIM = E.ID_MUKIM " +
						" AND A.STATUS_PEMILIKAN = F.ID_JENISPB(+)" 
				+ " AND A.JENIS_HTA = 'Y' " +
						"AND A1.ID_HTA = A.ID_HTA " +
						" AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
								"AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
				
			
			//idPerintah
			if (idPerintah != null) {
				if (!idPerintah.trim().equals("")) {
					sql = sql + " AND A.ID_HTA NOT IN (SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "')";
				}
			}
						
			sql = sql + " ORDER BY A.ID_HTA ASC";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;			

			while (rs.next()) {
				h = new Hashtable();	
				
				h.put("bil", bil);
				h.put("idHTA",rs.getString("ID_HTA"));
				h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
						rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
								+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
								+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("kodPB",rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				senaraiHTAPopup.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idHTA", "");
		    	h.put("keteranganHakmilik", "Tiada Rekod");
		    	h.put("kodPB", "");
		    	senaraiHTAPopup.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataMaklumatHTA(String idHTA,String id_permohonansimati) throws Exception {
		Db db = null;
		beanMaklumatHTA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("A.CATATAN");
			r.add("A.ID_JENISPERINTAH");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.NO_PT");
			r.add("G.KOD_JENIS_PB");
			r.add("G.KETERANGAN");
			
			r.add("A.ID_PERINTAHHTAOBMST", idHTA);
			r.add("B.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B.ID_JENISHM", r.unquote("C.ID_JENISHAKMILIK"));
			r.add("B.ID_NEGERI", r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH", r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM", r.unquote("F.ID_MUKIM"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("G.ID_JENISPB(+)"));

			
			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLRUJJENISPB G");
			sql = "SELECT A.ID_PERINTAHHTAOBMST, A.CATATAN, A.ID_JENISPERINTAH, " +
					"C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, E.NAMA_DAERAH, " +
					"F.NAMA_MUKIM, B.NO_PT, G.KOD_JENIS_PB, G.KETERANGAN, B1.JENIS_TNH  " +
					"FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1," +
					" TBLPPKHTAPERMOHONAN B, TBLRUJJENISHAKMILIK C, " +
					" TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, " +
					" TBLRUJJENISPB G " +
					"WHERE A.ID_PERINTAHHTAOBMST = '"+idHTA+"'  " +
					"AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B.ID_HTA = B1.ID_HTA " +
							" AND B1.ID_HTA = A.ID_HTA  AND B.ID_JENISHM = C.ID_JENISHAKMILIK  " +
					"AND B.ID_NEGERI = D.ID_NEGERI  AND B.ID_DAERAH = E.ID_DAERAH  " +
					"AND B.ID_MUKIM = F.ID_MUKIM  AND B.STATUS_PEMILIKAN = G.ID_JENISPB(+)";
			
			myLogger.info("PERINTAH HTA X ### setDataMaklumatHTA(String idHTA):"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			
			

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("keteranganHakmilik",rs.getString("NO_PT") + ", " + rs.getString("KOD_JENIS_HAKMILIK") + " " + rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM") 
						+ ", " + rs.getString("NAMA_DAERAH") + ", " + rs.getString("NAMA_NEGERI"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idJenisPerintah",rs.getString("ID_JENISPERINTAH") == null ? "0" : rs.getString("ID_JENISPERINTAH"));
				h.put("jenisPB", rs.getString("KOD_JENIS_PB") == null || rs.getString("KETERANGAN") == null ? "" : rs.getString("KOD_JENIS_PB") + " - " + rs.getString("KETERANGAN").toUpperCase());
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				
				beanMaklumatHTA.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveHTA(String idJenisPerintah, String catatan, String idHTA, String idPerintah, String idPermohonan, String idSimati, String idPermohonanSimati, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlOB = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
				
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBMST
			long idPerintahHTAOBMST = DB.getNextID("TBLPPKPERINTAHHTAOBMST_SEQ");
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_HTA", idHTA);
			r.add("ID_PERINTAH", idPerintah);
			r.add("CATATAN", catatan);
			r.add("ID_JENISPERINTAH", idJenisPerintah);
			r.add("FLAG_HARTA", "B");
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBMST");
			myLogger.info("saveHTAsql = " + sql);
			stmt.executeUpdate(sql);
			
			Fraction fracSimati = getFractionSimatiHTA(idHTA,idPermohonanSimati);
			
			if (idJenisPerintah.equals("7")){
				//GENERATE FARAID
				
				generateFaraid(idPermohonan,idSimati, idPermohonanSimati);
				
				//GET ALL WARIS
				sqlOB = "SELECT B.ID_OB, B1.BA_FARAID, B1.BB_FARAID, B.UMUR, B.STATUS_OB " +
						" FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B1.ID_OB = B.ID_OB " +
						" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND B.STATUS_HIDUP = 0 AND B.ID_TARAFKPTG = 1 " +
								" AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";

				ResultSet rs = stmt.executeQuery(sqlOB);
				
				while (rs.next()){					
					insertDTLHTA(idPerintahHTAOBMST, rs.getString("ID_OB"), rs.getString("BA_FARAID"), rs.getString("BB_FARAID"), getStatusWaris(rs.getInt("UMUR"), rs.getInt("STATUS_OB")), idPerintah, fracSimati, session);
				}
				
				//GET BAITULMAL IF EXIST
				sqlOB = "SELECT B.ID_OB, B1.BA_FARAID, B1.BB_FARAID " +
						" FROM TBLPPKOB B1, TBLPPKOBPERMOHONAN B " +
						" WHERE B.ID_OB = B1.ID_OB AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND B.STATUS_HIDUP = 0 AND B.ID_TARAFKPTG = 8 " +
								" AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				
				ResultSet rsBaitulmal = stmt.executeQuery(sqlOB);
				
				while (rsBaitulmal.next()){					
					insertDTLHTA(idPerintahHTAOBMST, rsBaitulmal.getString("ID_OB"), rsBaitulmal.getString("BA_FARAID"), rsBaitulmal.getString("BB_FARAID"), "", idPerintah, fracSimati, session);
				}
			
				updatePecahanWarisHTA(String.valueOf(idPerintahHTAOBMST));
				
			}  else if (idJenisPerintah.equals("2")){

				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHTAPerintahKuasaTadbir(idPerintahHTAOBMST,hash.get("idOB").toString(),session);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void generateFaraid(String idPermohonan, String idSimati, String idPermohonanSimati) throws Exception{
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//CHECK IF FARAID IS GENERATED
			sql = "SELECT B.ID_OB, B1.BA_FARAID, B1.BB_FARAID " +
					" FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
					" WHERE B.ID_OB = B1.ID_OB AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
					" AND B.ID_SIMATI = '" + idSimati + "' " +
					" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")"
					+ " AND (B1.BA_FARAID IS NULL OR B1.BB_FARAID IS NULL) AND B.STATUS_HIDUP = 0";

			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				//GENERATE FARAID
				EkptgEngine ekptgEngine = new EkptgEngine();
				ekptgEngine.doAllFaraidProcessing(idSimati, idPermohonan, idPermohonanSimati, ekptgEngine);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getStatusWaris(Integer umur, Integer statusOb){
		
		String statusWaris = "";
		
		if (umur != null){
			if (umur < 18){
				statusWaris = "T";
			} else {
				if (statusOb != null){
					if (statusOb == 3){
						statusWaris = "Y";
					} else if (statusOb == 2 || statusOb == 4){
						statusWaris = "T";
					}
				}
			}
		}
		return statusWaris;
	}
	
	public void insertDTLHTA(long idPerintahHTAOBMST, String idOB, String BA, String BB, String statusWaris, String idPerintah, Fraction fracSimati, HttpSession session) throws Exception{
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPAPT = new Vector();
		listPAPT.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			fracWaris = fracWaris.times(fracSimati);
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBDTL
			long id = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
			r.add("ID_PERINTAHHTAOBDTL", id);
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_OB", idOB);
			r.add("BA", BA);
			r.add("BB", BB);
			r.add("BA_WARIS", fracWaris.numerator());
			r.add("BB_WARIS", fracWaris.denominator());
			r.add("STATUS_TADBIR", statusWaris);
			
//			if (statusWaris.equals("Y")){
//				listPAPT = getPentadbir(idPerintah);
//				
//				if (listPAPT.size() != 0){
//					for (int i = 0; i < listPAPT.size(); i++) {
//						Hashtable hash = (Hashtable) listPAPT.get(i);
//						if (i == 0){
//							idPA1 = hash.get("idOB").toString();
//						} else if (i == 1){
//							idPA2 = hash.get("idOB").toString();
//						} else if (i == 2){
//							idPA3 = hash.get("idOB").toString();
//						} else if (i == 3){
//							idPA4 = hash.get("idOB").toString();
//						}
//					}
//				}
//				
//			} else 
			if (statusWaris.equals("T")){
				
				listPAPT = getPemegangAmanah(idOB);
				
				if (listPAPT.size() != 0){
					for (int i = 0; i < listPAPT.size(); i++) {
						Hashtable hash = (Hashtable) listPAPT.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
				}
			}
			
			r.add("ID_PA1", idPA1);
			r.add("ID_PA2", idPA2);
			r.add("ID_PA3", idPA3);
			r.add("ID_PA4", idPA4);
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusHTA(String idHTA) throws Exception {
		//Azam add transaction on 30/4/2010
		Connection conn = null;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
		    conn = db.getConnection();
		    conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBDTL
			r.add("ID_PERINTAHHTAOBMST", idHTA);
			sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBMST
			r.add("ID_PERINTAHHTAOBMST", idHTA);
			sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
			//TBLPPKHTA
			//Azam remark this part - no need to delete this data
//			r.add("ID_HTA",idHTA);
//			sql = r.getSQLDelete("TBLPPKHTA");
//			stmt.executeUpdate(sql);
			
			conn.commit();
		} catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
	    } finally {
	      if (db != null) db.close();
	    }	
	}
	
	public void updateCatatanHTA(String catatan, String idPerintahHTAOBMST, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBMST
			r.update("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			
			r.add("CATATAN", catatan);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateHTA(String idJenisPerintah, String catatan, String idPerintahHTAOBMST, String idPermohonan, String idSimati, String idPermohonanSimati, String idPerintah, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlOB = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBDTL
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
			//TBLPPKPERINTAHHTAOBMST
			r = new SQLRenderer();
			r.update("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			
			r.add("CATATAN", catatan);
			r.add("ID_JENISPERINTAH", idJenisPerintah);
			r.add("TARIKH_JUALAN", "");
			r.add("AMAUN", "");
			r.add("JENIS_LELONG", "");
			r.add("HARGA_RIZAB", "");
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
			sql = "SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsHTA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHTA.next()){
				fracSimati = getFractionSimatiHTA(rsHTA.getString("ID_HTA"),idPermohonanSimati);
			}

			if (idJenisPerintah.equals("7")){
				//GENERATE FARAID
				generateFaraid(idPermohonan,idSimati, idPermohonanSimati);
				
				//GET ALL WARIS
				sqlOB = "SELECT B.ID_OB, B1.BA_FARAID, B1.BB_FARAID, B.UMUR, B.STATUS_OB " +
						" FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B.ID_OB = B1.ID_OB " +
						" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"'" +
						" AND B.STATUS_HIDUP = 0 " +
						" AND B.ID_TARAFKPTG = 1 AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";

				ResultSet rs = stmt.executeQuery(sqlOB);
				
				while (rs.next()){
					insertDTLHTA(Long.parseLong(idPerintahHTAOBMST), rs.getString("ID_OB"), rs.getString("BA_FARAID"), rs.getString("BB_FARAID"), getStatusWaris(rs.getInt("UMUR"), rs.getInt("STATUS_OB")), idPerintah, fracSimati, session);
				}
				
				//GET BAITULMAL IF EXIST
				sqlOB = "SELECT B.ID_OB, B1.BA_FARAID, B1.BB_FARAID " +
						" FROM TBLPPKOB B1, TBLPPKOBPERMOHONAN B " +
						" WHERE B.ID_OB = B1.ID_OB " +
						" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' AND B.STATUS_HIDUP = 0  " +
						" AND B.ID_TARAFKPTG = 8 AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				
				ResultSet rsBaitulmal = stmt.executeQuery(sqlOB);
				
				while (rsBaitulmal.next()){					
					insertDTLHTA(Long.parseLong(idPerintahHTAOBMST), rsBaitulmal.getString("ID_OB"), rsBaitulmal.getString("BA_FARAID"), rsBaitulmal.getString("BB_FARAID"), "", idPerintah, fracSimati, session);
				}
				
				updatePecahanWarisHTA(idPerintahHTAOBMST);

			}  else if (idJenisPerintah.equals("2")){

				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHTAPerintahKuasaTadbir(Long.parseLong(idPerintahHTAOBMST),hash.get("idOB").toString(),session);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTATH(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTATH.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.NAMA_NEGERI");
			r.add("D.NAMA_DAERAH");
			r.add("E.NAMA_MUKIM");
			r.add("F.KETERANGAN");
			r.add("A.CATATAN");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");
			r.add("H.KOD_JENIS_PB");
			r.add("B.FLAG_KATEGORI_HTA");
			
			r.add("B.ALAMAT_HTA1");
			r.add("B.ALAMAT_HTA2");
			r.add("B.ALAMAT_HTA3");
			r.add("B.POSKOD_HTA");
			r.add("B.NO_ROH");
			r.add("B.JENIS_KEPENTINGAN");

			r.add("A.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("B.ID_NEGERI",r.unquote("C.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("D.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("E.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("F.ID_JENISPERINTAH"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("B.JENIS_HTA", "T");

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLPPKRUJJENISPERINTAH F, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.NAMA_NEGERI, D.NAMA_DAERAH, " +
					" E.NAMA_MUKIM, F.KETERANGAN, " +
					" A.CATATAN, B.BA_SIMATI, B.BB_SIMATI, " +
					" H.KOD_JENIS_PB, B.FLAG_KATEGORI_HTA, " +
					" B.ALAMAT_HTA1, B.ALAMAT_HTA2, B.ALAMAT_HTA3, B.POSKOD_HTA, B.NO_ROH, B.JENIS_KEPENTINGAN  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					"  TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, " +
					" TBLPPKRUJJENISPERINTAH F, TBLRUJJENISPB H " +
					" WHERE A.ID_HTA = B1.ID_HTA AND B1.ID_HTA = B.ID_HTA " +
					" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"'  " +
					" AND B.ID_NEGERI = C.ID_NEGERI  AND B.ID_DAERAH = D.ID_DAERAH  AND B.ID_MUKIM = E.ID_MUKIM  " +
					" AND A.ID_JENISPERINTAH = F.ID_JENISPERINTAH  AND B.STATUS_PEMILIKAN = H.ID_JENISPB(+)  " +
					" AND A.ID_PERINTAH = '"+idPerintah+"'  AND B.JENIS_HTA = 'T'  " +
					" ORDER BY A.ID_PERINTAHHTAOBMST ASC ";
 
			
			
			myLogger.info("PERINTAH HTA X ### setDataSenaraiHTATH(String idPerintah) :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
								+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("jenisPerintah",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				h.put("kodPB", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				if (rs.getString("FLAG_KATEGORI_HTA") != null) {
					if (rs.getString("FLAG_KATEGORI_HTA").equals("1")) {
						h.put("keterangan", " - " + (rs.getString("ALAMAT_HTA1") == null ? "" : rs.getString("ALAMAT_HTA1")) + " " + (rs.getString("ALAMAT_HTA2") == null ? "" : rs.getString("ALAMAT_HTA2")) + " " + (rs.getString("POSKOD_HTA") == null ? "" : rs.getString("POSKOD_HTA")));
					} else if (rs.getString("FLAG_KATEGORI_HTA").equals("2")) {
						h.put("keterangan", " - " + (rs.getString("NO_ROH") == null ? "" : rs.getString("NO_ROH")));
					} else if (rs.getString("FLAG_KATEGORI_HTA").equals("3")) {
						h.put("keterangan", " - " + (rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs.getString("JENIS_KEPENTINGAN")));
					} else {
						h.put("keterangan","");
					}
				} else {
					h.put("keterangan","");
				}
				senaraiHTATH.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");
		    	h.put("jenisPerintah","");
		    	h.put("catatan","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("bahagianSimati","");
		    	h.put("kodPB","");
		    	h.put("keterangan","");
		    	senaraiHTATH.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataMaklumatHTATH(String idHTATH,String id_permohonansimati) throws Exception {
		Db db = null;
		beanMaklumatHTATH.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("A.CATATAN");
			r.add("A.ID_JENISPERINTAH");
			r.add("C.NAMA_NEGERI");
			r.add("D.NAMA_DAERAH");
			r.add("E.NAMA_MUKIM");
			r.add("F.KETERANGAN");
			r.add("G.KOD_JENIS_PB");
			r.add("G.KETERANGAN AS KETERANGAN_PB");
			r.add("B.FLAG_KATEGORI_HTA");			
			r.add("B.ALAMAT_HTA1");
			r.add("B.ALAMAT_HTA2");
			r.add("B.ALAMAT_HTA3");
			r.add("B.POSKOD_HTA");
			r.add("B.NO_ROH");
			r.add("B.JENIS_KEPENTINGAN");			
			r.add("A.ID_PERINTAHHTAOBMST", idHTATH);
			r.add("A.ID_JENISPERINTAH", r.unquote("F.ID_JENISPERINTAH"));
			r.add("B.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B.ID_NEGERI", r.unquote("C.ID_NEGERI"));
			r.add("B.ID_DAERAH", r.unquote("D.ID_DAERAH"));
			r.add("B.ID_MUKIM", r.unquote("E.ID_MUKIM"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("G.ID_JENISPB(+)"));

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLPPKRUJJENISPERINTAH F, TBLRUJJENISPB G");
			sql = "SELECT A.ID_PERINTAHHTAOBMST, A.CATATAN, A.ID_JENISPERINTAH, C.NAMA_NEGERI, " +
					" D.NAMA_DAERAH, E.NAMA_MUKIM, F.KETERANGAN, G.KOD_JENIS_PB, G.KETERANGAN AS KETERANGAN_PB, " +
					" B.FLAG_KATEGORI_HTA, B.ALAMAT_HTA1, B.ALAMAT_HTA2, B.ALAMAT_HTA3, B.POSKOD_HTA, B.NO_ROH, " +
					" B.JENIS_KEPENTINGAN, B1.JENIS_TNH  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJNEGERI C, TBLRUJDAERAH D, " +
					" TBLRUJMUKIM E, TBLPPKRUJJENISPERINTAH F, TBLRUJJENISPB G " +
					" WHERE B.ID_HTA = B1.ID_HTA " +
					" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND A.ID_PERINTAHHTAOBMST = '"+idHTATH+"'  AND A.ID_JENISPERINTAH = F.ID_JENISPERINTAH  " +
					" AND B1.ID_HTA = A.ID_HTA  AND B.ID_NEGERI = C.ID_NEGERI  AND B.ID_DAERAH = D.ID_DAERAH  " +
					" AND B.ID_MUKIM = E.ID_MUKIM  AND B.STATUS_PEMILIKAN = G.ID_JENISPB(+) ";
			myLogger.info("PERINTAH HTA X ### setDataMaklumatHTATH(String idHTATH) :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("keteranganHakmilik", rs.getString("NAMA_MUKIM") + ", " + rs.getString("NAMA_DAERAH") + ", " + rs.getString("NAMA_NEGERI"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idJenisPerintah",rs.getString("ID_JENISPERINTAH") == null ? "0" : rs.getString("ID_JENISPERINTAH"));
				h.put("jenisPerintah",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenisPB", rs.getString("KOD_JENIS_PB") == null || rs.getString("KETERANGAN_PB") == null ? "" : rs.getString("KOD_JENIS_PB") + " - " + rs.getString("KETERANGAN_PB").toUpperCase());
				h.put("kategoriHarta",rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
				if (rs.getString("FLAG_KATEGORI_HTA") != null) {
					if (rs.getString("FLAG_KATEGORI_HTA").equals("1")) {
						h.put("keterangan", (rs.getString("ALAMAT_HTA1") == null ? "" : rs.getString("ALAMAT_HTA1")) + " " + (rs.getString("ALAMAT_HTA2") == null ? "" : rs.getString("ALAMAT_HTA2")) + " " + (rs.getString("POSKOD_HTA") == null ? "" : rs.getString("POSKOD_HTA")));
					} else if (rs.getString("FLAG_KATEGORI_HTA").equals("2")) {
						h.put("keterangan", (rs.getString("NO_ROH") == null ? "" : rs.getString("NO_ROH")));
					} else if (rs.getString("FLAG_KATEGORI_HTA").equals("3")) {
						h.put("keterangan", (rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs.getString("JENIS_KEPENTINGAN")));
					} else {
						h.put("keterangan","");
					}
				} else {
					h.put("keterangan","");
				}
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				beanMaklumatHTATH.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}
	}
	public void hapusHTATH(String idHTA) throws Exception {

        Db db = null;

        String sql = "";

        String sqlSelect = "";

        String idHartaTakAlih = "";



        try {

              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();

              sqlSelect = "SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idHTA + "'";
              ResultSet rsHTA = stmt.executeQuery(sqlSelect);
              if (rsHTA.next()){
                    idHartaTakAlih = rsHTA.getString("ID_HTA");
              }

              //TBLPPKPERINTAHHTAOBDTL
              r.add("ID_PERINTAHHTAOBMST", idHTA);
              sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
              stmt.executeUpdate(sql);              

              r = new SQLRenderer();
              //TBLPPKPERINTAHHTAOBMST
              r.add("ID_PERINTAHHTAOBMST", idHTA);
              sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBMST");
              stmt.executeUpdate(sql);              

              //TBLPPKHTA
              /*
              r = new SQLRenderer();              
              r.add("ID_HTA", idHartaTakAlih);   
              sql = r.getSQLDelete("TBLPPKHTA");
              stmt.executeUpdate(sql);*/

              

        } finally {

              if (db != null)

                    db.close();

        }

  }

	@SuppressWarnings("unchecked")
	public void setDataSenaraiHA(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHA.clear();
		String sql = "";
		String idJenisHA = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.KETERANGAN AS JENIS_HARTA_ALIH");
			r.add("D.KETERANGAN AS JENIS_PERINTAH");
			r.add("A.CATATAN");
			r.add("A.ID_PERINTAHHAOBMST");
			r.add("B.ID_SIMATI");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");
			r.add("B.NILAI_HA_TARIKHMOHON");
			
			r.add("B.ID_JENISHA");
			
			r.add("B.NAMA_SAHAM");
			r.add("B.JENAMA");
			r.add("B.BUTIRAN");
			r.add("B.NILAI_HA_TARIKHMOHON");

			
			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			r.add("D.ID_JENISPERINTAH",r.unquote("A.ID_JENISPERINTAH"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			
			
			r.add("B1.ID_HA",r.unquote("A.ID_HA"));
			r.add("B1.ID_HA",r.unquote("B.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C, TBLPPKRUJJENISPERINTAH D","B.ID_JENISHA ASC");
			myLogger.info("sql setDataSenaraiHA  = " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idSimati",rs.getString("ID_SIMATI"));
				h.put("jenisHarta",rs.getString("JENIS_HARTA_ALIH") == null ? "" : rs.getString("JENIS_HARTA_ALIH"));
				h.put("jenisPerintah",rs.getString("JENIS_PERINTAH") == null ? "" : rs.getString("JENIS_PERINTAH"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				h.put("nilaiTarikhMohon",rs.getString("NILAI_HA_TARIKHMOHON") == null ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("NILAI_HA_TARIKHMOHON"))));
				
				if (rs.getString("ID_JENISHA") != null && rs.getString("ID_JENISHA").trim().length() != 0){
					idJenisHA = rs.getString("ID_JENISHA");
					
					if ("1".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NAMA_SAHAM") == null ? "" : " - " + rs.getString("NAMA_SAHAM").toUpperCase());
					} else if ("2".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("3".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("4".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("5".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("6".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("7".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("8".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("9".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("10".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("11".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("12".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("98".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : " - RM" + Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HA_TARIKHMOHON"))));
					} else {
						h.put("keterangan", "");
					}
					
				} else {
					h.put("keterangan", "");
				}
				
				senaraiHA.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idSimati", "");
		    	h.put("jenisHarta", "Tiada Rekod");
		    	h.put("jenisPerintah","");
		    	h.put("catatan","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("bahagianSimati","");
		    	h.put("nilaiTarikhMohon","");
		    	h.put("keterangan", "");
		    	senaraiHA.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	/*public void setDataSenaraiHAARB(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAARB.clear();
		String sql = "";
		String idJenisHA = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.KETERANGAN AS JENIS_HARTA_ALIH");
			r.add("D.KETERANGAN AS JENIS_PERINTAH");
			r.add("A.CATATAN");
			r.add("A.ID_PERINTAHHAOBMST");
			r.add("B.ID_SIMATI");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");
			r.add("B.NILAI_HA_TARIKHMOHON");
			
			r.add("B.ID_JENISHA");
			
			r.add("B.NAMA_SAHAM");
			r.add("B.JENAMA");
			r.add("B.BUTIRAN");
			r.add("B.NILAI_HA_TARIKHMOHON");

			
			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			r.add("D.ID_JENISPERINTAH",r.unquote("A.ID_JENISPERINTAH"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			
			
			r.add("B1.ID_HA",r.unquote("A.ID_HA"));
			r.add("B1.ID_HA",r.unquote("B.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C, TBLPPKRUJJENISPERINTAH D","B.ID_JENISHA ASC");
			myLogger.info("sql setDataSenaraiHAARB  = " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idSimati",rs.getString("ID_SIMATI"));
				h.put("jenisHarta",rs.getString("JENIS_HARTA_ALIH") == null ? "" : rs.getString("JENIS_HARTA_ALIH"));
				h.put("jenisPerintah",rs.getString("JENIS_PERINTAH") == null ? "" : rs.getString("JENIS_PERINTAH"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				h.put("nilaiTarikhMohon",rs.getString("NILAI_HA_TARIKHMOHON") == null ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("NILAI_HA_TARIKHMOHON"))));
				
				if (rs.getString("ID_JENISHA") != null && rs.getString("ID_JENISHA").trim().length() != 0){
					idJenisHA = rs.getString("ID_JENISHA");
					
					if ("1".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NAMA_SAHAM") == null ? "" : " - " + rs.getString("NAMA_SAHAM").toUpperCase());
					} else if ("2".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("3".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("4".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("5".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("6".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("7".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("8".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("9".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("10".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("11".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("12".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("98".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : " - RM" + Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HA_TARIKHMOHON"))));
					} else {
						h.put("keterangan", "");
					}
					
				} else {
					h.put("keterangan", "");
				}
				
				senaraiHAARB.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idSimati", "");
		    	h.put("jenisHarta", "Tiada Rekod");
		    	h.put("jenisPerintah","");
		    	h.put("catatan","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("bahagianSimati","");
		    	h.put("nilaiTarikhMohon","");
		    	h.put("keterangan", "");
		    	senaraiHAARB.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	*/
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPopup(String idPermohonanSimati, String idPerintah) throws Exception {
		Db db = null;
		senaraiHAPopup.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HA, B.KETERANGAN, A.JENAMA, A.NO_DAFTAR, A.NILAI_HA_TARIKHMOHON"
				+ " FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A, TBLPPKRUJJENISHA B"
				+ " WHERE " +
						" A1.ID_HA = A.ID_HA AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
						" AND B.ID_JENISHA = A.ID_JENISHA" 
				+ " AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
			
			//idPerintah
			if (idPerintah != null) {
				if (!idPerintah.trim().equals("")) {
					sql = sql + " AND A1.ID_HA NOT IN " +
							" (SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST " +
							" WHERE ID_PERINTAH = '" + idPerintah + "')";
				}
			}
						
			sql = sql + " ORDER BY B.ID_JENISHA ASC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idHA",rs.getString("ID_HA"));
				h.put("jenisHartaAlih",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("no_daftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));
				h.put("nilai",rs.getString("NILAI_HA_TARIKHMOHON") == null || "0".equals(rs.getString("NILAI_HA_TARIKHMOHON").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("NILAI_HA_TARIKHMOHON"))));
				senaraiHAPopup.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idHA", "");
		    	h.put("jenisHartaAlih", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("no_daftar","");
		    	h.put("nilai","");
		    	senaraiHAPopup.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataMaklumatHA(String idHA,String id_permohonansimati) throws Exception {
		Db db = null;
		beanMaklumatHA.clear();
		String sql = "";
		String idJenisHA = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("A.ID_JENISPERINTAH");
			r.add("A.CATATAN");
			r.add("C.KETERANGAN");
			r.add("B.ID_JENISHA");
			
			r.add("B.NAMA_SAHAM");
			r.add("B.JENAMA");
			r.add("B.BUTIRAN");
			r.add("B.NILAI_HA_TARIKHMOHON");			
			
			r.add("ID_PERINTAHHAOBMST", idHA);
			r.add("C.ID_JENISHA", r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_HA", r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);
			r.add("B1.ID_HA", r.unquote("B.ID_HA"));

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1, TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("jenisHartaAlih",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idJenisPerintah",rs.getString("ID_JENISPERINTAH") == null ? "0" : rs.getString("ID_JENISPERINTAH"));
				h.put("idJenisHA",rs.getString("ID_JENISHA") == null ? "" : rs.getString("ID_JENISHA"));
				
				if (rs.getString("ID_JENISHA") != null && rs.getString("ID_JENISHA").trim().length() != 0){
					idJenisHA = rs.getString("ID_JENISHA");
					
					if ("1".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NAMA_SAHAM") == null ? "" : " - " + rs.getString("NAMA_SAHAM").toUpperCase());
					} else if ("2".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("3".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("4".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("5".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("6".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("7".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("8".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("9".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("10".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("11".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("12".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("98".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : " - RM" + Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HA_TARIKHMOHON"))));
					} else {
						h.put("keterangan", "");
					}
					
				} else {
					h.put("keterangan", "");
				}
				
				beanMaklumatHA.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}	
	}
	
	@SuppressWarnings("unchecked")
	public void saveHA(String idJenisPerintah, String catatan, String idHA, String idPerintah, String idPermohonan, String idSimati, String idPermohonanSimati, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlCheck = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBMST
			long idPerintahHAOBMST = DB.getNextID("TBLPPKPERINTAHHAOBMST_SEQ");
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			r.add("ID_HA", idHA);
			r.add("ID_PERINTAH", idPerintah);
			r.add("CATATAN", catatan);
			r.add("ID_JENISPERINTAH", idJenisPerintah);
			r.add("FLAG_HARTA", "B");
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHAOBMST");
			myLogger.info("sqlInsertHA = "+sql);
			stmt.executeUpdate(sql);
			
			Fraction fracSimati = getFractionSimatiHA(idHA);
			
			if (idJenisPerintah.equals("7")){
				//GENERATE FARAID
				generateFaraid(idPermohonan, idSimati, idPermohonanSimati);
				
				//GET ALL WARIS
				sql = "SELECT B.ID_OB, B1.BA_FARAID, B1.BB_FARAID, B.UMUR, B.STATUS_OB " +
						" FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B.ID_OB = B1.ID_OB " +
						" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"'" +
						" AND B.STATUS_HIDUP = 0 AND B.ID_TARAFKPTG = 1 " +
						" AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()){
					insertDTLHA(idPerintahHAOBMST, rs.getString("ID_OB"), rs.getString("BA_FARAID"), rs.getString("BB_FARAID"), getStatusWaris(rs.getInt("UMUR"), rs.getInt("STATUS_OB")), idPerintah, fracSimati, session);
				}
				
				//GET BAITULMAL IF EXIST
				sql = "SELECT B.ID_OB, B1.BA_FARAID, B1.BB_FARAID " +
						" FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B1.ID_OB = B.ID_OB AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND B.STATUS_HIDUP = 0 AND B.ID_TARAFKPTG = 8 " +
						" AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				
				ResultSet rsBaitulmal = stmt.executeQuery(sql);
				
				while (rsBaitulmal.next()){					
					insertDTLHA(idPerintahHAOBMST, rsBaitulmal.getString("ID_OB"), rsBaitulmal.getString("BA_FARAID"), rsBaitulmal.getString("BB_FARAID"), "", idPerintah, fracSimati, session);
				}
				
				updatePecahanWarisHA(String.valueOf(idPerintahHAOBMST));
		
			} else if (idJenisPerintah.equals("2")){

				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHAPerintahKuasaTadbir(idPerintahHAOBMST,hash.get("idOB").toString(),session);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void insertDTLHA(long idPerintahHAOBMST, String idOB, String BA, String BB, String statusWaris, String idPerintah, Fraction fracSimati, HttpSession session) throws Exception{
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPAPT = new Vector();
		listPAPT.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			fracWaris = fracWaris.times(fracSimati);
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBDTL
			long id = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
			r.add("ID_PERINTAHHAOBDTL", id);
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			r.add("ID_OB", idOB);
			r.add("BA", BA);
			r.add("BB", BB);
			r.add("BA_WARIS", fracWaris.numerator());
			r.add("BB_WARIS", fracWaris.denominator());
			r.add("STATUS_TADBIR", statusWaris);
			
//			if (statusWaris.equals("Y")){
//				listPAPT = getPentadbir(idPerintah);
//				
//				if (listPAPT.size() != 0){
//					for (int i = 0; i < listPAPT.size(); i++) {
//						Hashtable hash = (Hashtable) listPAPT.get(i);
//						if (i == 0){
//							idPA1 = hash.get("idOB").toString();
//						} else if (i == 1){
//							idPA2 = hash.get("idOB").toString();
//						} else if (i == 2){
//							idPA3 = hash.get("idOB").toString();
//						} else if (i == 3){
//							idPA4 = hash.get("idOB").toString();
//						}
//					}
//				}
//				
//			} else 
			if (statusWaris.equals("T")){
				
				listPAPT = getPemegangAmanah(idOB);
				
				if (listPAPT.size() != 0){
					for (int i = 0; i < listPAPT.size(); i++) {
						Hashtable hash = (Hashtable) listPAPT.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
				}
			}
			
			r.add("ID_PA1", idPA1);
			r.add("ID_PA2", idPA2);
			r.add("ID_PA3", idPA3);
			r.add("ID_PA4", idPA4);
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
			
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void insertDTLHAPerintahKuasaTadbir(long idPerintahHAOBMST, String idOB, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBDTL
			long id = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
			r.add("ID_PERINTAHHAOBDTL", id);
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			r.add("ID_OB", idOB);
			r.add("BA", "1");
			r.add("BB", "1");
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusHA(String idHA) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBDTL
			r.add("ID_PERINTAHHAOBMST", idHA);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBMST
			r.add("ID_PERINTAHHAOBMST", idHA);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateCatatanHA(String catatan, String idPerintahHAOBMST, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBMST
			r.update("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			
			r.add("CATATAN", catatan);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHA(String idJenisPerintah, String catatan, String idPerintahHAOBMST, String idPermohonan, String idSimati, String idPermohonanSimati, String idJenisHA, String idPerintah, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlOB = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBDTL
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);
			
			//TBLPPKPERINTAHHAOBMST
			r = new SQLRenderer();
			r.update("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			
			r.add("CATATAN", catatan);
			r.add("ID_JENISPERINTAH", idJenisPerintah);
			r.add("TARIKH_JUALAN", "");
			r.add("AMAUN", "");
			r.add("JENIS_LELONG", "");
			r.add("HARGA_RIZAB", "");
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
			sql = "SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			ResultSet rsHA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHA.next()){
				fracSimati = getFractionSimatiHA(rsHA.getString("ID_HA"));
			}
			
			if (idJenisPerintah.equals("7")){
				//GENERATE FARAID
				generateFaraid(idPermohonan, idSimati, idPermohonanSimati);
				
				//GET ALL WARIS
				sqlOB = "SELECT B.ID_OB, B1.BA_FARAID, B1.BB_FARAID, B.UMUR, B.STATUS_OB " +
						" FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B1.ID_OB = B.ID_OB " +
						" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND B.STATUS_HIDUP = 0 AND B.ID_TARAFKPTG = 1 " +
						" AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";

				ResultSet rs = stmt.executeQuery(sqlOB);
				
				while (rs.next()){
					insertDTLHA(Long.parseLong(idPerintahHAOBMST), rs.getString("ID_OB"), rs.getString("BA_FARAID"), rs.getString("BB_FARAID"), getStatusWaris(rs.getInt("UMUR"), rs.getInt("STATUS_OB")), idPerintah, fracSimati, session);
				}
				
				//GET BAITULMAL IF EXIST
				sql = "SELECT B.ID_OB, B1.BA_FARAID, B1.BB_FARAID " +
						" FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B.ID_OB = B1.ID_OB AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND B.STATUS_HIDUP = 0 AND B.ID_TARAFKPTG = 8 " +
						" AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				
				ResultSet rsBaitulmal = stmt.executeQuery(sql);
				
				while (rsBaitulmal.next()){					
					insertDTLHA(Long.parseLong(idPerintahHAOBMST), rsBaitulmal.getString("ID_OB"), rsBaitulmal.getString("BA_FARAID"), rsBaitulmal.getString("BB_FARAID"), "", idPerintah, fracSimati, session);
				}
				
				updatePecahanWarisHA(idPerintahHAOBMST);
				
			} else if (idJenisPerintah.equals("2")){

				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHAPerintahKuasaTadbir(Long.parseLong(idPerintahHAOBMST),hash.get("idOB").toString(),session);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTAPT(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '1'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHAPT(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '1'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTAPKT(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '2'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHAPKT(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '2'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHADIKEMBALIKAN(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '10'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTAPL(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '3'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHAPL(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '3'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTAPF(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '7'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHAPF(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '7'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPT(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			r.add("B.NO_PT");
			r.add("H.KOD_JENIS_PB");

			r.add("A.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "1");

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, " +
					" D.NAMA_NEGERI, E.NAMA_DAERAH, F.NAMA_MUKIM, B.JENIS_HTA, B.NO_PT, " +
					" H.KOD_JENIS_PB, B1.JENIS_TNH  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJJENISHAKMILIK C, " +
					" TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, " +
					" TBLRUJJENISPB H " +
					" WHERE B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B1.ID_HTA = B.ID_HTA AND A.ID_HTA = B1.ID_HTA  " +
							" AND B.ID_NEGERI = D.ID_NEGERI  " +
					" AND B.ID_DAERAH = E.ID_DAERAH  AND B.ID_MUKIM = F.ID_MUKIM  " +
					" AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH  AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+)  " +
					" AND B.STATUS_PEMILIKAN = H.ID_JENISPB(+)  AND A.ID_PERINTAH = '"+idPerintah+"'  " +
					" AND A.ID_JENISPERINTAH = '1'  ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			myLogger.info("PERINTAH HTA X ### setDataSenaraiHTAPT(String idPerintah) :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NO_PT") + ", "+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				h.put("kodPB", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else if ("4".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","BUKAN TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				senaraiHTAPT.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");	
		    	h.put("kodPB","");
		    	h.put("idJenisTanah","");
		    	h.put("jenisTanah","");
		    	senaraiHTAPT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPT(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPT.clear();
		String sql = "";
		String idJenisHA = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");
			r.add("B.ID_JENISHA");
			
			r.add("B.NAMA_SAHAM");
			r.add("B.JENAMA");
			r.add("B.BUTIRAN");
			r.add("B.NILAI_HA_TARIKHMOHON");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "1");
			
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B1.ID_HA",r.unquote("B.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,  TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));
				
				if (rs.getString("ID_JENISHA") != null && rs.getString("ID_JENISHA").trim().length() != 0){
					idJenisHA = rs.getString("ID_JENISHA");
					
					if ("1".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NAMA_SAHAM") == null ? "" : " - " + rs.getString("NAMA_SAHAM").toUpperCase());
					} else if ("2".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("3".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("4".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("5".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("6".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("7".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("8".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("9".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("10".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("11".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("12".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("98".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : " - RM" + Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HA_TARIKHMOHON"))));
					} else {
						h.put("keterangan", "");
					}
					
				} else {
					h.put("keterangan", "");
				}
				
				senaraiHAPT.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	h.put("keterangan", "");
		    	senaraiHAPT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPKT(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPKT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			r.add("B.NO_PT");
			r.add("H.KOD_JENIS_PB");
			r.add("B.FLAG_KATEGORI_HTA");
			
			r.add("B.ALAMAT_HTA1");
			r.add("B.ALAMAT_HTA2");
			r.add("B.ALAMAT_HTA3");
			r.add("B.POSKOD_HTA");
			r.add("B.NO_ROH");
			r.add("B.JENIS_KEPENTINGAN");

			r.add("A.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "2");

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK," +
					" D.NAMA_NEGERI, E.NAMA_DAERAH, F.NAMA_MUKIM, B.JENIS_HTA, B.NO_PT," +
					" H.KOD_JENIS_PB, B.FLAG_KATEGORI_HTA, B.ALAMAT_HTA1, B.ALAMAT_HTA2, B.ALAMAT_HTA3," +
					" B.POSKOD_HTA, B.NO_ROH, B.JENIS_KEPENTINGAN, B1.JENIS_TNH  " +
					"  FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJJENISHAKMILIK C," +
					" TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G," +
					" TBLRUJJENISPB H " +
					" WHERE B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B1.ID_HTA = B.ID_HTA " +
							" AND A.ID_HTA = B1.ID_HTA  " +
							" AND B.ID_NEGERI = D.ID_NEGERI" +
					"  AND B.ID_DAERAH = E.ID_DAERAH  AND B.ID_MUKIM = F.ID_MUKIM" +
					"  AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH  AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+)" +
					"  AND B.STATUS_PEMILIKAN = H.ID_JENISPB(+)  AND A.ID_PERINTAH = '"+idPerintah+"'" +
					"  AND A.ID_JENISPERINTAH = '2'  ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			
			myLogger.info("PERINTAH HTA X ### setDataSenaraiHTAPKT(String idPerintah) :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				h.put("kategoriHarta",rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NO_PT") + ", "+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
					h.put("keterangan","");
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
					h.put("kategoriHarta",rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
					if (rs.getString("FLAG_KATEGORI_HTA") != null) {
						if (rs.getString("FLAG_KATEGORI_HTA").equals("1")) {
							h.put("keterangan", " - " + (rs.getString("ALAMAT_HTA1") == null ? "" : rs.getString("ALAMAT_HTA1")) + " " + (rs.getString("ALAMAT_HTA2") == null ? "" : rs.getString("ALAMAT_HTA2")) + " " + (rs.getString("POSKOD_HTA") == null ? "" : rs.getString("POSKOD_HTA")));
						} else if (rs.getString("FLAG_KATEGORI_HTA").equals("2")) {
							h.put("keterangan", " - " + (rs.getString("NO_ROH") == null ? "" : rs.getString("NO_ROH")));
						} else if (rs.getString("FLAG_KATEGORI_HTA").equals("3")) {
							h.put("keterangan", " - " + (rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs.getString("JENIS_KEPENTINGAN")));
						} else {
							h.put("keterangan","");
						}
					} else {
						h.put("keterangan","");
					}
				} else {
					h.put("keteranganHakmilik", "");
					h.put("keterangan","");
				}
				h.put("kodPB", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				senaraiHTAPKT.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");		
		    	h.put("kodPB","");
		    	h.put("keterangan","");
		    	h.put("idJenisTanah","");
		    	h.put("jenisTanah","");
		    	senaraiHTAPKT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPKT(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPKT.clear();
		String sql = "";
		String idJenisHA = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");
			r.add("B.ID_JENISHA");
			
			r.add("B.NAMA_SAHAM");
			r.add("B.JENAMA");
			r.add("B.BUTIRAN");
			r.add("B.NILAI_HA_TARIKHMOHON");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "2");
			
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));		
				
				if (rs.getString("ID_JENISHA") != null && rs.getString("ID_JENISHA").trim().length() != 0){
					idJenisHA = rs.getString("ID_JENISHA");
					
					if ("1".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NAMA_SAHAM") == null ? "" : " - " + rs.getString("NAMA_SAHAM").toUpperCase());
					} else if ("2".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("3".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("4".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("5".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("6".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("7".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("8".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("9".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("10".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("11".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("12".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("98".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : " - RM" + Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HA_TARIKHMOHON"))));
					} else {
						h.put("keterangan", "");
					}
					
				} else {
					h.put("keterangan", "");
				}
				
				senaraiHAPKT.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	h.put("keterangan", "");
		    	senaraiHAPKT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPL(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		senaraiHTAPL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			r.add("A.JENIS_LELONG");
			r.add("A.TARIKH_JUALAN");
			r.add("A.HARGA_RIZAB");
			r.add("A.AMAUN");
			r.add("A.CATATAN");
			r.add("B.NO_PT");

			r.add("A.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "3");

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G","A.ID_PERINTAHHTAOBMST ASC");
			
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, " +
					" E.NAMA_DAERAH, F.NAMA_MUKIM, B.JENIS_HTA, A.JENIS_LELONG, A.TARIKH_JUALAN, A.HARGA_RIZAB, " +
					" A.AMAUN, A.CATATAN, B.NO_PT, B1.JENIS_TNH  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, " +
					" TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G " +
					" WHERE B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B1.ID_HTA = B.ID_HTA " +
							" AND A.ID_HTA = B1.ID_HTA  AND B.ID_NEGERI = D.ID_NEGERI  AND B.ID_DAERAH = E.ID_DAERAH  " +
					" AND B.ID_MUKIM = F.ID_MUKIM  AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH  " +
					" AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+)  AND A.ID_PERINTAH = '"+idPerintah+"'  " +
					" AND A.ID_JENISPERINTAH = '3'  ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			myLogger.info("PERINTAH HTA X ### setDataSenaraiHTAPL(String idPerintah) :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NO_PT") + ", "+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				if (rs.getString("JENIS_LELONG") != null){
					if (rs.getString("JENIS_LELONG").equals("A")){
						h.put("jenisLelong","AWAM");
					} else if (rs.getString("JENIS_LELONG").equals("T")) {
						h.put("jenisLelong","TENDER");
					} else {
						h.put("jenisLelong","");
					}
				} else {
					h.put("jenisLelong","");
				}
				h.put("tarikhJualan",rs.getString("TARIKH_JUALAN") == null ? "": sdf.format(rs.getDate("TARIKH_JUALAN")));
				h.put("hargaRizab",rs.getString("HARGA_RIZAB") == null || "0".equals(rs.getString("HARGA_RIZAB").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("HARGA_RIZAB"))));
				h.put("amaun",rs.getString("AMAUN") == null || "0".equals(rs.getString("AMAUN").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("AMAUN"))));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				senaraiHTAPL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");	
		    	h.put("jenisLelong","");
		    	h.put("tarikhJualan","");
		    	h.put("hargaRizab","");
		    	h.put("amaun","");
		    	h.put("catatan","");
		    	h.put("idJenisTanah","");
		    	h.put("jenisTanah","");
		    	senaraiHTAPL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPL(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		senaraiHAPL.clear();
		String sql = "";
		String idJenisHA = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");
			r.add("A.JENIS_LELONG");
			r.add("A.TARIKH_JUALAN");
			r.add("A.HARGA_RIZAB");
			r.add("A.AMAUN");
			r.add("A.CATATAN");
			r.add("A.ID_PERINTAHHAOBMST");
			
			r.add("B.ID_JENISHA");
			
			r.add("B.NAMA_SAHAM");
			r.add("B.JENAMA");
			r.add("B.BUTIRAN");
			r.add("B.NILAI_HA_TARIKHMOHON");
			
			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "3");
			
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B1.ID_HA",r.unquote("B.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				if (rs.getString("JENIS_LELONG") != null){
					if (rs.getString("JENIS_LELONG").equals("A")){
						h.put("jenisLelong","AWAM");
					} else if (rs.getString("JENIS_LELONG").equals("T")) {
						h.put("jenisLelong","TENDER");
					} else {
						h.put("jenisLelong","");
					}
				} else {
					h.put("jenisLelong","");
				}
				h.put("tarikhJualan",rs.getString("TARIKH_JUALAN") == null ? "": sdf.format(rs.getDate("TARIKH_JUALAN")));
				h.put("hargaRizab",rs.getString("HARGA_RIZAB") == null || "0".equals(rs.getString("HARGA_RIZAB").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("HARGA_RIZAB"))));
				h.put("amaun",rs.getString("AMAUN") == null || "0".equals(rs.getString("AMAUN").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("AMAUN"))));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				
				if (rs.getString("ID_JENISHA") != null && rs.getString("ID_JENISHA").trim().length() != 0){
					idJenisHA = rs.getString("ID_JENISHA");
					
					if ("1".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NAMA_SAHAM") == null ? "" : " - " + rs.getString("NAMA_SAHAM").toUpperCase());
					} else if ("2".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("3".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("4".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("5".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("6".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("7".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("8".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("9".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("10".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("11".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("12".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("98".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : " - RM" + Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HA_TARIKHMOHON"))));
					} else {
						h.put("keterangan", "");
					}
					
				} else {
					h.put("keterangan", "");
				}
				
				senaraiHAPL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	h.put("jenisLelong","");
		    	h.put("tarikhJualan","");
		    	h.put("hargaRizab","");
		    	h.put("amaun","");
		    	h.put("catatan","");
		    	h.put("keterangan", "");
		    	senaraiHAPL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPF(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPF.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			r.add("B.NO_PT");
			r.add("H.KOD_JENIS_PB");

			r.add("A.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "7");

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, E.NAMA_DAERAH, " +
					" F.NAMA_MUKIM, B.JENIS_HTA, B.NO_PT, H.KOD_JENIS_PB, B1.JENIS_TNH  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1, TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, " +
					" TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H " +
					" WHERE B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B1.ID_HTA = B.ID_HTA AND A.ID_HTA = B1.ID_HTA  AND B.ID_NEGERI = D.ID_NEGERI  AND B.ID_DAERAH = E.ID_DAERAH  " +
					" AND B.ID_MUKIM = F.ID_MUKIM  AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH  " +
					" AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+)  AND B.STATUS_PEMILIKAN = H.ID_JENISPB(+)  " +
					" AND A.ID_PERINTAH = '"+idPerintah+"'  AND A.ID_JENISPERINTAH = '7'  " +
					" ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			
			myLogger.info("PERINTAH HTA X ### setDataSenaraiHTAPF(String idPerintah) :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NO_PT") + ", "+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				
				senaraiHTAPF.addElement(h);
				h.put("kodPB", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");		
		    	h.put("kodPB","");
		    	h.put("idJenisTanah","");
		    	h.put("jenisTanah","");
		    	senaraiHTAPF.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPF(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPF.clear();
		String sql = "";
		String idJenisHA = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");
			r.add("B.ID_JENISHA");
			
			r.add("B.NAMA_SAHAM");
			r.add("B.JENAMA");
			r.add("B.BUTIRAN");
			r.add("B.NILAI_HA_TARIKHMOHON");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			r.add("A.ID_HA",r.unquote("B.ID_HA"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "7");
			
			
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));
				
				if (rs.getString("ID_JENISHA") != null && rs.getString("ID_JENISHA").trim().length() != 0){
					idJenisHA = rs.getString("ID_JENISHA");
					
					if ("1".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NAMA_SAHAM") == null ? "" : " - " + rs.getString("NAMA_SAHAM").toUpperCase());
					} else if ("2".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("3".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("4".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("5".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("6".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("7".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("8".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("9".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("10".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("11".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("12".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("98".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : " - RM" + Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HA_TARIKHMOHON"))));
					} else {
						h.put("keterangan", "");
					}
					
				} else {
					h.put("keterangan", "");
				}
				
				senaraiHAPF.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	h.put("keterangan", "");
		    	senaraiHAPF.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setMaklumatHeaderDetailHTA(String idHTA,String id_permohonansimati) throws Exception {
		Db db = null;
		beanHeaderDetail.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");
			r.add("A.CATATAN");
			r.add("B.NO_PT");
			r.add("H.KOD_JENIS_PB");
			r.add("H.KETERANGAN AS KETERANGAN_PB");
			r.add("B.FLAG_KATEGORI_HTA");
			
			r.add("B.ALAMAT_HTA1");
			r.add("B.ALAMAT_HTA2");
			r.add("B.ALAMAT_HTA3");
			r.add("B.POSKOD_HTA");
			r.add("B.NO_ROH");
			r.add("B.JENIS_KEPENTINGAN");

			r.add("A.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAHHTAOBMST", idHTA);

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, " +
					" E.NAMA_DAERAH, F.NAMA_MUKIM, B.JENIS_HTA, B.BA_SIMATI, B.BB_SIMATI, A.CATATAN, " +
					" B.NO_PT, H.KOD_JENIS_PB, H.KETERANGAN AS KETERANGAN_PB, B.FLAG_KATEGORI_HTA, " +
					" B.ALAMAT_HTA1, B.ALAMAT_HTA2, B.ALAMAT_HTA3, B.POSKOD_HTA, B.NO_ROH, B.JENIS_KEPENTINGAN, B1.JENIS_TNH  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, " +
					" TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H " +
					" WHERE B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B1.ID_HTA = B.ID_HTA AND A.ID_HTA = B1.ID_HTA  " +
							" AND B.ID_NEGERI = D.ID_NEGERI  " +
					" AND B.ID_DAERAH = E.ID_DAERAH  AND B.ID_MUKIM = F.ID_MUKIM  " +
					" AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH  AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+)  " +
					" AND B.STATUS_PEMILIKAN = H.ID_JENISPB(+)  AND A.ID_PERINTAHHTAOBMST = '"+idHTA+"'  " +
					" ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			
			myLogger.info("PERINTAH HTA X ### setMaklumatHeaderDetailHTA(String idHTA):"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				h.put("kategoriHarta",rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
				if (rs.getString("JENIS_HTA").equals("Y")){					
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NO_PT") + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
					h.put("keterangan","");
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_NEGERI") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase()
									+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
					if (rs.getString("FLAG_KATEGORI_HTA") != null) {
						if (rs.getString("FLAG_KATEGORI_HTA").equals("1")) {
							h.put("keterangan", (rs.getString("ALAMAT_HTA1") == null ? "" : rs.getString("ALAMAT_HTA1")) + " " + (rs.getString("ALAMAT_HTA2") == null ? "" : rs.getString("ALAMAT_HTA2")) + " " + (rs.getString("POSKOD_HTA") == null ? "" : rs.getString("POSKOD_HTA")));
						} else if (rs.getString("FLAG_KATEGORI_HTA").equals("2")) {
							h.put("keterangan", (rs.getString("NO_ROH") == null ? "" : rs.getString("NO_ROH")));
						} else if (rs.getString("FLAG_KATEGORI_HTA").equals("3")) {
							h.put("keterangan", (rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs.getString("JENIS_KEPENTINGAN")));
						} else {
							h.put("keterangan","");
						}
					} else {
						h.put("keterangan","");
					}
				} else {
					h.put("keteranganHakmilik", "");
					h.put("keterangan","");
				}
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				h.put("bahagianSimatiAtas",rs.getString("BA_SIMATI") == null ? "" : rs.getString("BA_SIMATI"));
				h.put("bahagianSimatiBawah",rs.getString("BB_SIMATI") == null ? "" : rs.getString("BB_SIMATI"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("jenisPB", rs.getString("KOD_JENIS_PB") == null || rs.getString("KETERANGAN_PB") == null ? "" : rs.getString("KOD_JENIS_PB") + " - " + rs.getString("KETERANGAN_PB").toUpperCase());
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				beanHeaderDetail.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPFDTL(String idPerintahHTAOBMST,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPFDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_OB, UPPER(B.NAMA_OB) AS NAMA_OB, A.BA, A.BB, B.STATUS_OB, B.UMUR " +
					" FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKOB B1,TBLPPKOBPERMOHONAN B"
					+ " WHERE B.ID_OB = B1.ID_OB " +
							" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
									" AND B1.ID_OB = A.ID_OB " +
									" AND A.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' " +
							" ORDER BY B.UMUR DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("bahagianWaris",rs.getString("BA") == null || rs.getString("BB") == null ? "" : rs.getString("BA") + " / " + rs.getString("BB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				senaraiHTAPFDTL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

				h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","0");
		    	h.put("BB","0");
		    	h.put("status","");
		    	senaraiHTAPFDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setMaklumatHeaderDetailHA(String idHA,String id_permohonansimati) throws Exception {
		Db db = null;
		beanHeaderDetail.clear();
		String sql = "";
		String idJenisHA = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");
			r.add("B.NILAI_HA_TARIKHMOHON");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");
			r.add("A.FLAG_JENISPEMBAHAGIAN");
			r.add("C.ID_JENISHA");
			r.add("A.CATATAN");
			
			r.add("B.ID_JENISHA");
			
			r.add("B.NAMA_SAHAM");
			r.add("B.JENAMA");
			r.add("B.BUTIRAN");
			r.add("B.NILAI_HA_TARIKHMOHON");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAHHAOBMST", idHA);
			
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B1.ID_HA",r.unquote("B.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","A.ID_PERINTAHHAOBMST ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				h.put("nilai",rs.getString("NILAI_HA_TARIKHMOHON") == null || "0".equals(rs.getString("NILAI_HA_TARIKHMOHON").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("NILAI_HA_TARIKHMOHON"))));
				h.put("flagJenisPembahagian",rs.getString("FLAG_JENISPEMBAHAGIAN") == null ? "" : rs.getString("FLAG_JENISPEMBAHAGIAN"));
				h.put("idJenisHA",rs.getString("ID_JENISHA") == null ? "" : rs.getString("ID_JENISHA"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				
				if (rs.getString("ID_JENISHA") != null && rs.getString("ID_JENISHA").trim().length() != 0){
					idJenisHA = rs.getString("ID_JENISHA");
					
					if ("1".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NAMA_SAHAM") == null ? "" : " - " + rs.getString("NAMA_SAHAM").toUpperCase());
					} else if ("2".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("3".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("4".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("5".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("6".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("7".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("8".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("9".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("10".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("11".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("12".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("98".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : " - RM" + Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HA_TARIKHMOHON"))));
					} else {
						h.put("keterangan", "");
					}
					
				} else {
					h.put("keterangan", "");
				}
				
				beanHeaderDetail.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPFDTL(String idPerintahHAOBMST,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPFDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_OB, UPPER(B.NAMA_OB) AS NAMA_OB, A.BA, A.BB, B.STATUS_OB, B.UMUR " +
					" FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKOB B1,TBLPPKOBPERMOHONAN B "
					+ " WHERE B.ID_OB = B1.ID_OB " +
							" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
									" AND B1.ID_OB = A.ID_OB " +
									" AND A.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' " +
											" ORDER BY B.UMUR DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("bahagianWaris",rs.getString("BA") == null || rs.getString("BB") == null ? "" : rs.getString("BA") + " / " + rs.getString("BB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				senaraiHAPFDTL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

				h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","0");
		    	h.put("BB","0");
		    	h.put("status","");
		    	senaraiHAPFDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataMaklumatHTAPL(String idHTAMST,String id_permohonansimati) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		beanMaklumatHTAPL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("B.NO_PT");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			
			r.add("F.NAMA_MUKIM");
			r.add("A.JENIS_LELONG");
			r.add("A.TARIKH_JUALAN");
			r.add("A.HARGA_RIZAB");
			r.add("A.AMAUN");
			r.add("A.CATATAN");
			r.add("B.JENIS_HTA");
			r.add("A.ID_PERINTAHHTAOBMST");
			
			r.add("B.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B.ID_JENISHM", r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.ID_NEGERI", r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH", r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM", r.unquote("F.ID_MUKIM"));
			
			r.add("A.ID_PERINTAHHTAOBMST", idHTAMST);

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F");
			
			sql = "SELECT C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, B.NO_PT, D.NAMA_NEGERI, " +
					" E.NAMA_DAERAH, F.NAMA_MUKIM, A.JENIS_LELONG, A.TARIKH_JUALAN, A.HARGA_RIZAB, " +
					" A.AMAUN, A.CATATAN, B.JENIS_HTA, A.ID_PERINTAHHTAOBMST  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJJENISHAKMILIK C, " +
					" TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F " +
					" WHERE B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B.ID_HTA = B1.ID_HTA " +
							" AND B1.ID_HTA = A.ID_HTA  AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+)  " +
					" AND B.ID_NEGERI = D.ID_NEGERI  AND B.ID_DAERAH = E.ID_DAERAH  " +
					" AND B.ID_MUKIM = F.ID_MUKIM  AND A.ID_PERINTAHHTAOBMST = '"+idHTAMST+"' ";
			myLogger.info("PERINTAH HTA X ### setDataMaklumatHTAPL(String idHTAMST):"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NO_PT").toUpperCase() + ", "+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_NEGERI") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase()
									+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				h.put("jenisLelong",rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));
				h.put("tarikhJualan",rs.getDate("TARIKH_JUALAN") == null ? "" : sdf.format(rs.getDate("TARIKH_JUALAN")));
				h.put("hargaRizab",rs.getString("HARGA_RIZAB") == null || "".equals(rs.getString("HARGA_RIZAB").toString()) ? "" : Util.formatDecimal(Double.parseDouble(rs.getString("HARGA_RIZAB"))));
				h.put("amaun",rs.getString("AMAUN") == null || "".equals(rs.getString("AMAUN").toString()) ? "" : Util.formatDecimal(Double.parseDouble(rs.getString("AMAUN"))));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));

				beanMaklumatHTAPL.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHTAPL(String idHTAMST, Hashtable h, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		
		try {
			
			String tarikhJualan = h.get("tarikhJualan").toString();
			String TJ = "to_date('" + tarikhJualan + "','dd/MM/yyyy')";
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBMST				
			r.update("ID_PERINTAHHTAOBMST", idHTAMST);
			
			r.add("JENIS_LELONG", h.get("jenisLelong"));
			r.add("TARIKH_JUALAN", r.unquote(TJ));
			r.add("HARGA_RIZAB", h.get("hargaRizab"));
			r.add("AMAUN", h.get("amaun"));
			r.add("CATATAN", h.get("catatan"));
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataMaklumatHAPL(String idHAMST,String id_permohonansimati) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		beanMaklumatHAPL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("A.JENIS_LELONG");
			r.add("A.TARIKH_JUALAN");
			r.add("A.HARGA_RIZAB");
			r.add("A.AMAUN");
			r.add("A.CATATAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");
			
			
			r.add("C.ID_JENISHA", r.unquote("B.ID_JENISHA"));
			
			r.add("A.ID_PERINTAHHAOBMST", idHAMST);
			
			r.add("A.ID_HA", r.unquote("B1.ID_HA"));
			r.add("B1.ID_HA", r.unquote("B.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenisLelong",rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));
				h.put("tarikhJualan",rs.getDate("TARIKH_JUALAN") == null ? "" : sdf.format(rs.getDate("TARIKH_JUALAN")));
				h.put("hargaRizab",rs.getString("HARGA_RIZAB") == null || "".equals(rs.getString("HARGA_RIZAB").toString()) ? "" : Util.formatDecimal(Double.parseDouble(rs.getString("HARGA_RIZAB"))));
				h.put("amaun",rs.getString("AMAUN") == null || "".equals(rs.getString("AMAUN").toString()) ? "" : Util.formatDecimal(Double.parseDouble(rs.getString("AMAUN"))));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));

				beanMaklumatHAPL.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHAPL(String idHA, Hashtable h, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			String tarikhJualan = h.get("tarikhJualan").toString();
			String TJ = "to_date('" + tarikhJualan + "','dd/MM/yyyy')";
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBMST				
			r.update("ID_PERINTAHHAOBMST", idHA);
			
			r.add("JENIS_LELONG", h.get("jenisLelong"));
			r.add("TARIKH_JUALAN", r.unquote(TJ));
			r.add("HARGA_RIZAB", h.get("hargaRizab"));
			r.add("AMAUN", h.get("amaun"));
			r.add("CATATAN", h.get("catatan"));
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHTAPKTDTL(String idPerintahHTAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiHTAPKTDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 * 
			sql = "SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0 AND (STATUS_OB = 1 OR STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			*/
			
			sql = "SELECT B1.ID_OB, B1.ID_TARAFKPTG, UPPER(B.NAMA_OB) AS NAMA_OB, B.UMUR, B.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN B.ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHTAOBDTL " +
						" WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"								
				+ " FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B1.ID_OB = B.ID_OB AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND B.ID_SIMATI = '" + idSimati + "' " +
								" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
						" AND B.ID_TARAFKPTG = 1 AND (B.STATUS_OB = 1 OR B.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT B1.ID_OB, B1.ID_TARAFKPTG, UPPER(B.NAMA_OB) AS NAMA_OB, B.UMUR, B.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN B.ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHTAOBDTL " +
						" WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B  " +
						" WHERE B1.ID_OB = B.ID_OB AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND B.ID_SIMATI = '" + idSimati + "' " +
						" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND B.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
		myLogger.info("GET LIST PENTADBIR"+sql.toUpperCase());
		ResultSet rs = stmt.executeQuery(sql);
		myLogger.info("GET LIST PENTADBIR"+sql.toUpperCase());
			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idOB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("ID_TARAFKPTG", rs.getString("ID_TARAFKPTG") == null ? "" : rs.getString("ID_TARAFKPTG"));
				h.put("namaOB", rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB").toUpperCase());
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				h.put("statusHidup", rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				senaraiHTAPKTDTL.addElement(h);
				bil++;
				count++;
			}

			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB", "");
		    	h.put("namaOB", "Tiada Rekod");
		    	h.put("flag", "");
		    	h.put("statusHidup", "");
		    	senaraiHTAPKTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHAPKTDTL(String idPerintahHAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiHAPKTDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 *
			sql = "SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0 AND (STATUS_OB = 1 OR STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			*/
			
			sql = "SELECT B1.ID_OB, UPPER(B.NAMA_OB) AS NAMA_OB, B.UMUR, B.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN B.ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHAOBDTL " +
						"WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B1.ID_OB = B.ID_OB " +
						" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND B.ID_SIMATI = '" + idSimati + "' " +
						" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND B.ID_TARAFKPTG = 1 AND (B.STATUS_OB = 1 OR B.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT B1.ID_OB, UPPER(B.NAMA_OB) AS NAMA_OB, B.UMUR, B.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN B.ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB B1,TBLPPKOBPERMOHONAN B " +
						" WHERE B1.ID_OB = B.ID_OB " +
						" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND B.ID_SIMATI = '" + idSimati + "' " +
						" AND B1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND B.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";

		ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idOB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaOB", rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB").toUpperCase());
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				h.put("statusHidup", rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				senaraiHAPKTDTL.addElement(h);
				bil++;
				count++;
			}

			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB", "");
		    	h.put("namaOB", "Tiada Rekod");
		    	h.put("flag", "");
		    	h.put("statusHidup", "");
		    	senaraiHAPKTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePentadbir(String idPA1, String idPA2, String idPA3, String idPA4, String idPerintah, HttpSession session) throws Exception{
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sqlCheckPerintahTerusHTA = "";
		String sqlCheckPerintahTerusHA = "";
		String sqlCheckPerintahKuasaTadbirHTA = "";
		String sqlCheckPerintahKuasaTadbirHA = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//COMMENT BY PEJE - PENTADBIR PERINTAH TERUS X SAMA DENGAN PENTADBIR PERINTAH KUASA TADBIR
//			//HTA PERINTAH TERUS / FARAID
//			sqlCheckPerintahTerusHTA = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B"
//					+ " WHERE B.ID_PERINTAH = '" + idPerintah + "' AND B.ID_JENISPERINTAH IN (1,7) AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST";
//			
//			ResultSet rsHTA = stmt.executeQuery(sqlCheckPerintahTerusHTA);
//			
//			while (rsHTA.next()){
//				updatePTHTAPTPF(rsHTA.getString("ID_PERINTAHHTAOBDTL"), idPA1, idPA2, idPA3, idPA4, idPerintah, session);
//			}
//			
//			//HA PERINTAH TERUS / FARAID
//			sqlCheckPerintahTerusHA = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B"
//					+ " WHERE B.ID_PERINTAH = '" + idPerintah + "' AND B.ID_JENISPERINTAH IN (1,7) AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAHHAOBMST = A.ID_PERINTAHHAOBMST";
//			
//			ResultSet rsHA = stmt.executeQuery(sqlCheckPerintahTerusHA);
//			
//			while (rsHA.next()){
//				updatePTHAPTPF(rsHA.getString("ID_PERINTAHHAOBDTL"), idPA1, idPA2, idPA3, idPA4, idPerintah, session);
//			}
			
			//HTA PERINTAH KUASA TADBIR
			sqlCheckPerintahKuasaTadbirHTA = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH = 2 AND FLAG_HARTA = 'B' AND ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rsKTHTA = stmt.executeQuery(sqlCheckPerintahKuasaTadbirHTA);
			
			while (rsKTHTA.next()){
				
				deletePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"));
				
				if (idPA1.trim().length() > 0){
					updatePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"), idPA1, session);
				}
				if (idPA2.trim().length() > 0){
					updatePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"), idPA2, session);
				}
				if (idPA3.trim().length() > 0){
					updatePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"), idPA3, session);
				}
				if (idPA4.trim().length() > 0){
					updatePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"), idPA4, session);
				}
			}
			
			//HA PERINTAH KUASA TADBIR
			sqlCheckPerintahKuasaTadbirHA = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_JENISPERINTAH = 2 AND ID_PERINTAH = '" + idPerintah + "'";
			myLogger.info("sql sqlCheckPerintahKuasaTadbirHA =" +sqlCheckPerintahKuasaTadbirHA );
			ResultSet rsKTHA = stmt.executeQuery(sqlCheckPerintahKuasaTadbirHA);
			
			while (rsKTHA.next()){
				
				deletePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"));
				
				if (idPA1.trim().length() > 0){
					updatePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"), idPA1, session);
				}
				if (idPA2.trim().length() > 0){
					updatePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"), idPA2, session);
				}
				if (idPA3.trim().length() > 0){
					updatePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"), idPA3, session);
				}
				if (idPA4.trim().length() > 0){
					updatePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"), idPA4, session);
				}
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deletePTHTAPKT(String idHTAMST) throws Exception {
		Db db = null;
		String sqlDelete = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBDTL				
			r.add("ID_PERINTAHHTAOBMST", idHTAMST);

			sqlDelete = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sqlDelete);			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deletePTHAPKT(String idHAMST) throws Exception {
		Db db = null;
		String sqlDelete = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBDTL				
			r.add("ID_PERINTAHHAOBMST", idHAMST);

			sqlDelete = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sqlDelete);			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePTHTAPTPF(String idHTADTL, String idPA1, String idPA2, String idPA3, String idPA4, String idPerintah, HttpSession session) throws Exception {
		
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBDTL				
			r.update("ID_PERINTAHHTAOBDTL", idHTADTL);
			
			r.add("ID_PA1", idPA1);
			r.add("ID_PA2", idPA2);
			r.add("ID_PA3", idPA3);
			r.add("ID_PA4", idPA4);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePTHAPTPF(String idHADTL, String idPA1, String idPA2, String idPA3, String idPA4, String idPerintah, HttpSession session) throws Exception {
		
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBDTL				
			r.update("ID_PERINTAHHAOBDTL", idHADTL);
			
			r.add("ID_PA1", idPA1);
			r.add("ID_PA2", idPA2);
			r.add("ID_PA3", idPA3);
			r.add("ID_PA4", idPA4);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePTHTAPKT(String idHTAMST, String idOB, HttpSession session) throws Exception {
		
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sqlInsert = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBDTL				
			long id = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
			r.add("ID_PERINTAHHTAOBDTL", id);
			r.add("ID_PERINTAHHTAOBMST", idHTAMST);
			r.add("ID_OB", idOB);
			r.add("BA", "1");
			r.add("BB", "1");
			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sqlInsert);
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePTHAPKT(String idHAMST, String idOB, HttpSession session) throws Exception {
		
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sqlInsert = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBDTL				
			long id = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
			r.add("ID_PERINTAHHAOBDTL", id);
			r.add("ID_PERINTAHHAOBMST", idHAMST);
			r.add("ID_OB", idOB);
			r.add("BA", "1");
			r.add("BB", "1");
			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sqlInsert);
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPTDTL(String idPerintahHTAOBMST, String idSimati, String idPermohonanSimati) throws Exception {
		Db db = null;
		senaraiHTAPTDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 *
			sql = "SELECT OB.ID_OB, OB.NAMA_OB, OB.STATUS_OB, OB.UMUR,"
					+ " (SELECT A.BA FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
					+ " (SELECT A.BB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
					+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0"
					+ " UNION"
					+ " SELECT OB.ID_OB, OB.NAMA_OB, OB.STATUS_OB, OB.UMUR,"
					+ " (SELECT A.BA FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
					+ " (SELECT A.BB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
					+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			*/
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " (SELECT A.BA FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B " +
						"WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
				+ " (SELECT A.BB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B " +
						" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG = 1"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " (SELECT A.BA FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B " +
						" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
				+ " (SELECT A.BB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B " +
						" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			System.out.println("###setDataSenaraiHTAPTDTL( :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				h.put("statusHidup",rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				senaraiHTAPTDTL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","0");
		    	h.put("BB","0");
		    	h.put("status","");
		    	h.put("statusHidup","");
		    	senaraiHTAPTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPTDTLHilang(String idHAMST) throws Exception {
		Db db = null;
		senaraiHAPTDTLHilang.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERINTAHHAOBDTL, BA, BB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_OB IS NULL AND STATUS_TADBIR = 'Y' AND ID_PERINTAHHAOBMST = '" + idHAMST + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("idPerintahHAOBDTL",rs.getString("ID_PERINTAHHAOBDTL"));
				h.put("bahagianWaris",rs.getString("BA") == null || rs.getString("BB") == null ? "" : rs.getString("BA") + " / " + rs.getString("BB"));
				h.put("BA",rs.getString("BA") == null ? "" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "" : rs.getString("BB"));
				
				senaraiHAPTDTLHilang.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPTDTLHilang(String idHTAMST) throws Exception {
		Db db = null;
		senaraiHTAPTDTLHilang.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERINTAHHTAOBDTL, BA, BB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_OB IS NULL AND STATUS_TADBIR = 'Y' AND ID_PERINTAHHTAOBMST = '" + idHTAMST + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("idPerintahHTAOBDTL",rs.getString("ID_PERINTAHHTAOBDTL"));
				h.put("bahagianWaris",rs.getString("BA") == null || rs.getString("BB") == null ? "" : rs.getString("BA") + " / " + rs.getString("BB"));
				h.put("BA",rs.getString("BA") == null ? "" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "" : rs.getString("BB"));
				
				senaraiHTAPTDTLHilang.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateHTAPT(String idPerintahHTAOBMST, String idOB, String status, String BA, String BB, String idPerintah, HttpSession session,String idPermohonanSimati) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		Vector listPemegangAmanah = new Vector();
		listPemegangAmanah.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//GET FRACTION SIMATI
			sql = "SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsHTA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHTA.next()){
				fracSimati = getFractionSimatiHTA(rsHTA.getString("ID_HTA"),idPermohonanSimati);
			}
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));

			}
			fracWaris = fracWaris.times(fracSimati);

			//GET ROW OB
			sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_OB = '" + idOB + "' AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsRowOB = stmt.executeQuery(sql);
			
			if (rsRowOB.next()){
				//UPDATE TBLPPKPERINTAHHTAOBDTL		
				r.update("ID_PERINTAHHTAOBDTL", rsRowOB.getString("ID_PERINTAHHTAOBDTL"));
				
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				//r.add("BA_WARIS_STR", fracWaris.numerator());
				//r.add("BB_WARIS_STR", fracWaris.denominator());
				
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");
					
					listPemegangAmanah = getPemegangAmanah(idOB);
					
					for (int i = 0; i < listPemegangAmanah.size(); i++) {
						Hashtable hash = (Hashtable) listPemegangAmanah.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				r.add("ID_PA1", idPA1);
				r.add("ID_PA2", idPA2);
				r.add("ID_PA3", idPA3);
				r.add("ID_PA4", idPA4);
		
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBDTL");
				stmt.executeUpdate(sql);
				
			} else {
				//NEW TBLPPKPERINTAHHTAOBDTL				
				long idPerintahHTAOBDTL = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
				r.add("ID_PERINTAHHTAOBDTL", idPerintahHTAOBDTL);
				r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
				r.add("ID_OB", idOB);
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");
					
					listPemegangAmanah = getPemegangAmanah(idOB);
					
					for (int i = 0; i < listPemegangAmanah.size(); i++) {
						Hashtable hash = (Hashtable) listPemegangAmanah.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				r.add("ID_PA1", idPA1);
				r.add("ID_PA2", idPA2);
				r.add("ID_PA3", idPA3);
				r.add("ID_PA4", idPA4);
				
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
				stmt.executeUpdate(sql);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPTDTL(String idPerintahHAOBMST, String idSimati, String idPermohonanSimati) throws Exception {
		Db db = null;
		senaraiHAPTDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 *
			sql = "SELECT OB.ID_OB, OB.NAMA_OB, OB.STATUS_OB, OB.UMUR,"
					+ " (SELECT A.BA FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BA,"
					+ " (SELECT A.BB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BB"
					+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0"
					+ " UNION"
					+ " SELECT OB.ID_OB, OB.NAMA_OB, OB.STATUS_OB, OB.UMUR,"
					+ " (SELECT A.BA FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BA,"
					+ " (SELECT A.BB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BB"
					+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			*/
			
			
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " (SELECT A.BA FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B " +
						" WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BA,"
				+ " (SELECT A.BB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B " +
						" WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BB"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB" +
						" WHERE OB.ID_OB = OB1.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG = 1"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " (SELECT A.BA FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B " +
						" WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BA,"
				+ " (SELECT A.BB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B " +
						" WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BB"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB" +
						" WHERE OB.ID_OB = OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"'" +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			System.out.println("xxxxxxx"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				h.put("statusHidup",rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				senaraiHAPTDTL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","0");
		    	h.put("BB","0");
		    	h.put("status","");
		    	h.put("statusHidup","");
		    	senaraiHAPTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateHAPT(String idPerintahHAOBMST, String idOB, String status, String BA, String BB, String idPerintah, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		Vector listPemegangAmanah = new Vector();
		listPemegangAmanah.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			myLogger.info("sqlxxxx ="+sql);
			ResultSet rsHA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHA.next()){
				fracSimati = getFractionSimatiHA(rsHA.getString("ID_HA"));
			}
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			fracWaris = fracWaris.times(fracSimati);
			
			//GET ROW OB
			sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_OB = '" + idOB + "' AND ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			myLogger.info("sqlyyyy ="+sql);
			ResultSet rsRowOB = stmt.executeQuery(sql);
			
			if (rsRowOB.next()){
				myLogger.info("aaaaaaaa");
				//UPDATE TBLPPKPERINTAHHAOBDTL		
				r.update("ID_PERINTAHHAOBDTL", rsRowOB.getString("ID_PERINTAHHAOBDTL"));
				
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");
					
					listPemegangAmanah = getPemegangAmanah(idOB);
					
					for (int i = 0; i < listPemegangAmanah.size(); i++) {
						Hashtable hash = (Hashtable) listPemegangAmanah.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				r.add("ID_PA1", idPA1);
				r.add("ID_PA2", idPA2);
				r.add("ID_PA3", idPA3);
				r.add("ID_PA4", idPA4);
		
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBDTL");
				myLogger.info("sqlzzzz ="+sql);
				stmt.executeUpdate(sql);
				
			} else {
				myLogger.info("elsexxxx");
				//NEW TBLPPKPERINTAHHAOBDTL				
				long idPerintahHAOBDTL = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
				r.add("ID_PERINTAHHAOBDTL", idPerintahHAOBDTL);
				r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
				r.add("ID_OB", idOB);
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");
					
					listPemegangAmanah = getPemegangAmanah(idOB);
					
					for (int i = 0; i < listPemegangAmanah.size(); i++) {
						Hashtable hash = (Hashtable) listPemegangAmanah.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				r.add("ID_PA1", idPA1);
				r.add("ID_PA2", idPA2);
				r.add("ID_PA3", idPA3);
				r.add("ID_PA4", idPA4);
				
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
				stmt.executeUpdate(sql);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void selesaiPermohonan(String idPermohonan, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLRUJSUBURUSANSTATUSFAIL
			/*
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");			
			r.add("AKTIF", "0");	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			*/
			
			//get id_Fail
			sql = "SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			//TBLRUJSUBURUSANSTATUSFAIL
			/*
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", "359"); //SELESAI PERMOHONAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", rs.getString("ID_FAIL"));			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			*/
			
			//TBLPPKPERMOHONAN
			/*
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);			
			r.add("ID_STATUS", "25");// SELESAI PERMOHONAN	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPPKPERMOHONAN");
			stmt.executeUpdate(sql);
			*/
			
			FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			logic_A.kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"25","359",rs.getString("ID_FAIL")+"");

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void selesai(String idPermohonan, String idPerintah, HttpSession session,String id_permohonansimati) throws Exception {

		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		
		myLogger.info("@@id_permohonansimati :"+id_permohonansimati);
		myLogger.info("@@idPerintah :"+idPerintah);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLRUJSUBURUSANSTATUSFAIL
			/*
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");			
			r.add("AKTIF", "0");	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			*/
			
			//get id_Fail
			sql = "SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			//TBLRUJSUBURUSANSTATUSFAIL
			/*
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", "358"); //SELESAI
			r.add("AKTIF", "1");
			r.add("ID_FAIL", rs.getString("ID_FAIL"));			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			*/

			//TBLPPKPERMOHONAN
			/*
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);			
			r.add("ID_STATUS", "21");// SELESAI	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPPKPERMOHONAN");
			stmt.executeUpdate(sql);
			*/
			

			updateHTAAfterSelesai(idPerintah, session,id_permohonansimati);
			updateHAAfterSelesai(idPerintah, session,id_permohonansimati);
			System.out.println("*************READ HERE selesai******************");
			updateTablePermohonanSelesai(id_permohonansimati);
			
			FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			logic_A.kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"21","358",rs.getString("ID_FAIL")+"");
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHTAAfterSelesai(String idPerintah, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHTAOBMST, ID_HTA, ID_JENISPERINTAH FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND FLAG_HARTA = 'B'";
              ResultSet rs = stmt.executeQuery(sql);
              
              while (rs.next()){
                    if (rs.getString("ID_JENISPERINTAH") != null){
                          updateFlagSelesaiHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);
                          if (rs.getString("ID_JENISPERINTAH").equals("1") || rs.getString("ID_JENISPERINTAH").equals("7")){
                                if (checkForPTHTA(rs.getString("ID_PERINTAHHTAOBMST"))){
                                      updateFlagPTHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);
                                }
                                if (checkForPAHTA(rs.getString("ID_PERINTAHHTAOBMST"))){
                                      updateFlagPAHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);
                                }                                   
                          } else if (rs.getString("ID_JENISPERINTAH").equals("2")){
                                updateFlagPTHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);
                          }
                    }                       
                    updateIdPerintahOBMSTForHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);                  
                    updateHTAPermohonan(rs.getString("ID_HTA"), session, id_permohonansimati);
              }
              
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
	
	public void updateHTAPermohonan(String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
		Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
        	db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
              
              sql = "SELECT ID_PERINTAHOBMST, FLAG_PT, FLAG_PA, FLAG_SELESAI FROM TBLPPKHTA WHERE ID_HTA = '" + idHTA + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
            	  r = new SQLRenderer();
                  r.update("ID_HTA", idHTA);
                  r.update("ID_PERMOHONANSIMATI",id_permohonansimati);
                  r.add("FLAG_PA", rs.getString("FLAG_PA"));
                  r.add("FLAG_PT", rs.getString("FLAG_PT"));
                  r.add("FLAG_SELESAI", rs.getString("FLAG_SELESAI"));
                  r.add("ID_PERINTAHOBMST", rs.getString("ID_PERINTAHOBMST"));
                  r.add("ID_KEMASKINI", userId);
                  r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
                  sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
                  stmt.executeUpdate(sql);
              }
              
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
	
	public void updateHAPermohonan(String idHA, HttpSession session,String id_permohonansimati) throws Exception {
		Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
        	db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
              
              sql = "SELECT ID_PERINTAHOBMST, FLAG_PT, FLAG_PA, FLAG_SELESAI FROM TBLPPKHA WHERE ID_HA = '" + idHA + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
            	  r = new SQLRenderer();
                  r.update("ID_HA", idHA);
                  r.update("ID_PERMOHONANSIMATI",id_permohonansimati);
                  r.add("FLAG_PA", rs.getString("FLAG_PA"));
                  r.add("FLAG_PT", rs.getString("FLAG_PT"));
                  r.add("FLAG_SELESAI", rs.getString("FLAG_SELESAI"));
                  r.add("ID_PERINTAHOBMST", rs.getString("ID_PERINTAHOBMST"));
                  r.add("ID_KEMASKINI", userId);
                  r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
                  sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
                  stmt.executeUpdate(sql);
              }
              
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  private boolean checkForPTHTA(String idPerintahHTAOBMST) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE STATUS_TADBIR = 'Y' AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
                    return true;
              } else {
                    return false;
              }
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  private boolean checkForPAHTA(String idPerintahHTAOBMST) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE STATUS_TADBIR = 'T' AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
                    return true;
              } else {
                    return false;
              }
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateFlagPAHTA(String idPerintahHTAOBMST, String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHTA
              r.update("ID_HTA", idHTA);              
              r.add("FLAG_PA", "Y");
              r.add("FLAG_SELESAI", "T");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHTA");
              stmt.executeUpdate(sql);
              
//              r = new SQLRenderer();
//              r.update("ID_HTA", idHTA);
//              r.update("ID_PERMOHONANSIMATI",id_permohonansimati);
//              r.add("FLAG_PA", "Y");
//              r.add("FLAG_SELESAI", "T");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }

  public void updateFlagPTHTA(String idPerintahHTAOBMST, String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHTA
              r.update("ID_HTA", idHTA);              
              r.add("FLAG_PT", "Y");
              r.add("FLAG_SELESAI", "T");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHTA");
              myLogger.info(sql);
              stmt.executeUpdate(sql);
              
//              r = new SQLRenderer();
//              r.update("ID_HTA", idHTA);
//              r.update("ID_PERMOHONANSIMATI", idHTA);
//              r.add("FLAG_PT", "Y");
//              r.add("FLAG_SELESAI", "T");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//              myLogger.info(sql);
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateFlagSelesaiHTA(String idPerintahHTAOBMST, String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHTA
              r.update("ID_HTA", idHTA);              
              r.add("FLAG_PT", "T");
              r.add("FLAG_PA", "T");
              r.add("FLAG_SELESAI", "Y");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHTA");
              myLogger.info("------------------------------------------------");
              myLogger.info(sql);
              stmt.executeUpdate(sql);
              
//              r = new SQLRenderer();
//              r.update("ID_HTA", idHTA);
//              r.update("ID_PERMOHONANSIMATI", id_permohonansimati);
//              r.add("FLAG_PT", "T");
//              r.add("FLAG_PA", "T");
//              r.add("FLAG_SELESAI", "Y");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//              myLogger.info(sql);
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateIdPerintahOBMSTForHTA(String idPerintahHTAOBMST, String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHTA
              r.update("ID_HTA", idHTA);              
              r.add("ID_PERINTAHOBMST", idPerintahHTAOBMST);  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHTA");
              stmt.executeUpdate(sql);
              
//              r = new SQLRenderer();
//              r.update("ID_HTA", idHTA);  
//              r.update("ID_PERMOHONANSIMATI", id_permohonansimati); 
//              r.add("ID_PERINTAHOBMST", idPerintahHTAOBMST);  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
    public void updateTablePermohonanSelesai(String id_permohonansimati) throws Exception {
      Db db = null;
      String sql = "";

      try {
            db = new Db();
            Statement stmt = db.getStatement();
            
            sql = "UPDATE TBLPPKPERMOHONAN SET TARIKH_SELESAI = SYSDATE WHERE ID_PERMOHONAN = (SELECT ID_PERMOHONAN FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = (SELECT ID_PERMOHONAN FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI = '" + id_permohonansimati + "'))";
            ResultSet rs = stmt.executeQuery(sql);
            myLogger.info("updateTablePermohonanSelesai "+sql);
            
      } finally {
            if (db != null)
                  db.close();
      }
}
  public void updateHAAfterSelesai(String idPerintah, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHAOBMST, ID_HA, ID_JENISPERINTAH FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND FLAG_HARTA = 'B'";
              ResultSet rs = stmt.executeQuery(sql);
              
              while (rs.next()){
                    if (rs.getString("ID_JENISPERINTAH") != null){
                          updateFlagSelesaiHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session,id_permohonansimati);
                          if (rs.getString("ID_JENISPERINTAH").equals("1") || rs.getString("ID_JENISPERINTAH").equals("7")){
                                if (checkForPTHA(rs.getString("ID_PERINTAHHAOBMST"))){
                                      updateFlagPTHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session,id_permohonansimati);
                                }
                                if (checkForPAHA(rs.getString("ID_PERINTAHHAOBMST"))){
                                      updateFlagPAHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session,id_permohonansimati);
                                }                                   
                          } else if (rs.getString("ID_JENISPERINTAH").equals("2")){
                                updateFlagPTHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session,id_permohonansimati);
                          }
                    }                       
                    updateIdPerintahOBMSTForHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session, id_permohonansimati);
                    updateHAPermohonan(rs.getString("ID_HA"), session, id_permohonansimati);
              }
              
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  private boolean checkForPTHA(String idPerintahHAOBMST) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE STATUS_TADBIR = 'Y' AND ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
                    return true;
              } else {
                    return false;
              }
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  private boolean checkForPAHA(String idPerintahHAOBMST) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE STATUS_TADBIR = 'T' AND ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
                    return true;
              } else {
                    return false;
              }
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateFlagPAHA(String idPerintahHAOBMST, String idHA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHA
              r.update("ID_HA", idHA);              
              r.add("FLAG_PA", "Y");
              r.add("FLAG_SELESAI", "T");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHA");
              stmt.executeUpdate(sql);
              
              
            //TBLPPKHA
//              r = new SQLRenderer();
//              r.update("ID_HA", idHA); 
//              r.update("ID_PERMOHONANSIMATI", id_permohonansimati); 
//              r.add("FLAG_PA", "Y");
//              r.add("FLAG_SELESAI", "T");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }

  public void updateFlagPTHA(String idPerintahHAOBMST, String idHA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHA
              r.update("ID_HA", idHA);              
              r.add("FLAG_PT", "Y");
              r.add("FLAG_SELESAI", "T");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHA");
              stmt.executeUpdate(sql);
              
              
            //TBLPPKHA
//              r = new SQLRenderer();
//              r.update("ID_HA", idHA);  
//              r.update("ID_PERMOHONANSIMATI", id_permohonansimati);
//              r.add("FLAG_PT", "Y");
//              r.add("FLAG_SELESAI", "T");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
//              stmt.executeUpdate(sql);
//              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateFlagSelesaiHA(String idPerintahHAOBMST, String idHA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHA
              r.update("ID_HA", idHA);              
              r.add("FLAG_PT", "T");
              r.add("FLAG_PA", "T");
              r.add("FLAG_SELESAI", "Y");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHA");
              stmt.executeUpdate(sql);
              
            //TBLPPKHA
              /*
              r = new SQLRenderer();
              r.update("ID_HA", idHA);   
              r.update("ID_PERMOHONANSIMATI", id_permohonansimati); 
              r.add("FLAG_PT", "T");
              r.add("FLAG_PA", "T");
              r.add("FLAG_SELESAI", "Y");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
              stmt.executeUpdate(sql);*/
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateIdPerintahOBMSTForHA(String idPerintahHAOBMST, String idHA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHA
              r.update("ID_HA", idHA);              
              r.add("ID_PERINTAHOBMST", idPerintahHAOBMST);  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHA");
              stmt.executeUpdate(sql);
              
//              r = new SQLRenderer();
//              r.update("ID_HA", idHA);   
//              r.update("ID_HA", id_permohonansimati);  
//              r.add("ID_PERINTAHOBMST", idPerintahHAOBMST);  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }

	
	public boolean checkHTAYangBelumDibahagikan(String idPermohonanSimati, String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_HTA FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A " +
					" WHERE A1.ID_HTA = A.ID_HTA " +
					" AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
							" AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'" +
							"  AND A.JENIS_HTA = 'Y' AND A1.ID_HTA NOT IN " +
					"(SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST " +
					"WHERE ID_PERINTAH = '" + idPerintah + "')";
	
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}  
	
	public boolean checkHAYangBelumDibahagikan(String idPermohonanSimati, String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A1.ID_HA FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A " +
					" WHERE A.ID_HA = A1.ID_HA AND  A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
					" AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' AND A1.ID_HA NOT IN " +
					"(SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "')";
	
			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("checkHAYangBelumDibahagikan = "+sql);
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkPerintahKIV(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT CHECK_KIV FROM TBLPPKPERINTAH WHERE ID_PERINTAH = '" + idPerintah + "'";
	
			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("checkKIV = "+sql);
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//find id perintahhaobmst
	public void findIDPerintahHAOBMST(String id_HAARB) throws Exception{
		Db db = null;
		String sql = "";
		Hashtable h = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = " SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_HA = "+id_HAARB;
	
			myLogger.info("findIDPerintahHAOBMST = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				h = new Hashtable();
		    	h.put("ID_PERINTAHHAOBMST", rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
		    	
		    	senaraiPerintahHAARB.addElement(h);
			
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//findIdObARB
	public void findIdObARB(String id_permohonansimati) throws Exception{
		Db db = null;
		String sql = "";
		Hashtable h = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_OB FROM TBLPPKOB WHERE ID_SIMATI in " +
			      "(SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati+"') " +
				  "AND NAMA_OB LIKE '%AMANAH RAYA%'";
			
			myLogger.info("findIdObARB = "+sql);
	
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				h = new Hashtable();
		    	h.put("ID_OB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
		    	
		    	senaraiOBARB.addElement(h);
			
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//
	
	public void setDataSenaraiHAARB(String idPerintah,String id_permohonansimati) throws Exception{
		Db db = null;
		String sql = "";
		Hashtable h = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A1.ID_HA FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A " +
					" WHERE A.ID_HA = A1.ID_HA AND  A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
					" AND A1.ID_PERMOHONANSIMATI = '" + id_permohonansimati + "' AND A1.ID_HA NOT IN " +
					"(SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "')";
	
			myLogger.info("sql xxxxx = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				h = new Hashtable();
		    	h.put("ID_HA", rs.getString("ID_HA") == null ? "" : rs.getString("ID_HA"));
		    	
		    	senaraiHAARB.addElement(h);
			
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	//
	
	public boolean checkHAYangDiselesaikanARBBelumDibahagikan(String idPermohonanSimati, String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A1.ID_HA FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A " +
					" WHERE A.ID_HA = A1.ID_HA AND  A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
					" AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' AND a1.id_jenisha = '98' AND A1.ID_HA NOT IN " +
					"(SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "')";
	
			myLogger.info("sql checkHAYangDiselesaikanARBBelumDibahagikan = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkPembahagianHTAPTLengkap(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		Boolean bool = false;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = 1";

			ResultSet rs = stmt.executeQuery(sql);
			
			int count = 0;
			while (rs.next()) {	
				if (calculateTotalPecahanHTAPT(rs.getString("ID_PERINTAHHTAOBMST")).equals("1")){
					bool = false;
				} else {
					bool = true;
				}
				count ++;
			}
			if (count == 0){
				bool = true;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
		return bool;
	}
	
	public String calculateTotalPecahanHTAPT(String idPerintahHTAOBMST) throws Exception{
		Db db = null;
		String sql = "";
		Fraction total = new Fraction(0, 1);
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BA, BB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getString("BA") != null && rs.getString("BB") != null){
					if (Long.parseLong(rs.getString("BB")) > 0){
						Fraction frac = new Fraction(Long.parseLong(rs.getString("BA")), Long.parseLong(rs.getString("BB")));
						total = total.plus(frac);
					}
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}

		return total.toString();
	}
	
	public boolean checkPembahagianHAPTLengkap(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		Boolean bool = false;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = 1";

			ResultSet rs = stmt.executeQuery(sql);
			
			int count = 0;
			while (rs.next()) {	
				if (calculateTotalPecahanHAPT(rs.getString("ID_PERINTAHHAOBMST")).equals("1")){
					bool = false;
				} else {
					bool = true;
				}
				count ++;
			}
			if (count == 0){
				bool = true;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
		return bool;
	}
	
	public String calculateTotalPecahanHAPT(String idPerintahHAOBMST) throws Exception{
		Db db = null;
		String sql = "";
		Fraction total = new Fraction(0, 1);
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BA, BB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			myLogger.info("sql calculateTotalPecahanHAPT = "+ sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getString("BA") != null && rs.getString("BB") != null){
					if (Long.parseLong(rs.getString("BB")) > 0){
						Fraction frac = new Fraction(Long.parseLong(rs.getString("BA")), Long.parseLong(rs.getString("BB")));
						total = total.plus(frac);
					}
				}
				System.out.println("total=="+total);
				System.out.println("idPerintahHAOBMST=="+idPerintahHAOBMST);
				System.out.println("rs.getString(BA)=="+rs.getString("BA"));
				System.out.println("rs.getString(BB)=="+rs.getString("BB"));
			}
			
		} finally {
			if (db != null)
				db.close();
		}

		return total.toString();
	}
	
	public boolean checkPembahagianHTAPKTLengkap(String idPerintah) throws Exception{
		Db db = null;
		boolean bool = false;
		String sql = "";
		String sqlDetail = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH = 2 AND ID_PERINTAH = '" + idPerintah + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				sqlDetail = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + rs.getString("ID_PERINTAHHTAOBMST") + "'";
				ResultSet rsDetail = stmt.executeQuery(sqlDetail);
				
				if (rsDetail.next()){
					bool = false;
				} else {
					return true;
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return bool;
	}
	
	public boolean checkPembahagianHAPKTLengkap(String idPerintah) throws Exception{
		Db db = null;
		boolean bool = false;
		String sql = "";
		String sqlDetail = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_JENISPERINTAH = 2 AND ID_PERINTAH = '" + idPerintah + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				sqlDetail = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + rs.getString("ID_PERINTAHHAOBMST") + "'";
				ResultSet rsDetail = stmt.executeQuery(sqlDetail);
				
				if (rsDetail.next()){
					bool = false;
				} else {
					return true;
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return bool;
	}
	
	public boolean checkPembahagianHTAPLLengkap(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH = 3 AND JENIS_LELONG IS NULL AND ID_PERINTAH = '" + idPerintah + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkPembahagianHAPLLengkap(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_JENISPERINTAH = 3 AND JENIS_LELONG IS NULL AND ID_PERINTAH = '" + idPerintah + "'";
	
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPAHTAPT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = 1"
					+ " AND A.STATUS_TADBIR = 'T' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			myLogger.info("sql checkWarisYangPerluAdaPAHTAPT = " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPAHAPT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = 1"
					+ " AND A.STATUS_TADBIR = 'T' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPTHTAPT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = 1"
					+ " AND A.STATUS_TADBIR = 'Y' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPTHAPT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = 1"
					+ " AND A.STATUS_TADBIR = 'Y' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkWarisYangPerluAdaPAHTAPF(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = 7"
					+ " AND A.STATUS_TADBIR = 'T' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPAHAPF(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = 7"
					+ " AND A.STATUS_TADBIR = 'T' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPTHTAPF(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = 7"
					+ " AND A.STATUS_TADBIR = 'Y' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPTHAPF(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = 7"
					+ " AND A.STATUS_TADBIR = 'Y' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHTAPTHilang(String idPerintahHTAOBMST, String idPerintah, String BA, String BB, HttpSession session,String idPermohonanSimati) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";
		
//		listPentadbir = getPentadbir(idPerintah);
//		
//		for (int i = 0; i < listPentadbir.size(); i++) {
//			Hashtable hash = (Hashtable) listPentadbir.get(i);
//			if (i == 0){
//				idPA1 = hash.get("idOB").toString();
//			} else if (i == 1){
//				idPA2 = hash.get("idOB").toString();
//			} else if (i == 2){
//				idPA3 = hash.get("idOB").toString();
//			} else if (i == 3){
//				idPA4 = hash.get("idOB").toString();
//			}
//		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//GET FRACTION SIMATI
			sql = "SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsHTA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHTA.next()){
				fracSimati = getFractionSimatiHTA(rsHTA.getString("ID_HTA"),idPermohonanSimati);
			}
						
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
				
				fracWaris = fracWaris.times(fracSimati);
				
				sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_OB IS NULL AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHTAOBDTL
					r.update("ID_PERINTAHHTAOBDTL", rs.getString("ID_PERINTAHHTAOBDTL"));
					
					r.add("BA", BA);
					r.add("BB", BB);
					r.add("BA_WARIS", fracWaris.numerator());
					r.add("BB_WARIS", fracWaris.denominator());
					r.add("STATUS_TADBIR", "Y");
					r.add("ID_PA1", idPA1);
					r.add("ID_PA2", idPA2);
					r.add("ID_PA3", idPA3);
					r.add("ID_PA4", idPA4);
			
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

					sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBDTL");
					stmt.executeUpdate(sql);
					
				} else {
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHTAOBDTL				
					long id = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
					r.add("ID_PERINTAHHTAOBDTL", id);
					r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
					r.add("BA", BA);
					r.add("BB", BB);
					r.add("BA_WARIS", fracWaris.numerator());
					r.add("BB_WARIS", fracWaris.denominator());
					r.add("STATUS_TADBIR", "Y");
					r.add("ID_PA1", idPA1);
					r.add("ID_PA2", idPA2);
					r.add("ID_PA3", idPA3);
					r.add("ID_PA4", idPA4);
					
					r.add("ID_MASUK", userId);			
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
					stmt.executeUpdate(sql);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removeHTAPTHilang(String idHTA) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_OB IS NULL AND ID_PERINTAHHTAOBMST = '" + idHTA + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				SQLRenderer r = new SQLRenderer();
				
				//TBLPPKPERINTAHHTAOBDTL
				r.add("ID_PERINTAHHTAOBDTL", rs.getString("ID_PERINTAHHTAOBDTL"));
				
				sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
				stmt.executeUpdate(sql);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHAPTHilang(String idPerintahHAOBMST, String idPerintah, String BA, String BB, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";
		
//		listPentadbir = getPentadbir(idPerintah);
//		
//		for (int i = 0; i < listPentadbir.size(); i++) {
//			Hashtable hash = (Hashtable) listPentadbir.get(i);
//			if (i == 0){
//				idPA1 = hash.get("idOB").toString();
//			} else if (i == 1){
//				idPA2 = hash.get("idOB").toString();
//			} else if (i == 2){
//				idPA3 = hash.get("idOB").toString();
//			} else if (i == 3){
//				idPA4 = hash.get("idOB").toString();
//			}
//		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//GET FRACTION SIMATI
			sql = "SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			ResultSet rsHA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHA.next()){
				fracSimati = getFractionSimatiHA(rsHA.getString("ID_HA"));
			}
						
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
				
				fracWaris = fracWaris.times(fracSimati);
				
				sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_OB IS NULL AND ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHAOBDTL
					r.update("ID_PERINTAHHAOBDTL", rs.getString("ID_PERINTAHHAOBDTL"));
					
					r.add("BA", BA);
					r.add("BB", BB);
					r.add("BA_WARIS", fracWaris.numerator());
					r.add("BB_WARIS", fracWaris.denominator());
					r.add("STATUS_TADBIR", "Y");
					r.add("ID_PA1", idPA1);
					r.add("ID_PA2", idPA2);
					r.add("ID_PA3", idPA3);
					r.add("ID_PA4", idPA4);
			
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

					sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBDTL");
					stmt.executeUpdate(sql);
					
				} else {
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHAOBDTL				
					long id = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
					r.add("ID_PERINTAHHAOBDTL", id);
					r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
					r.add("BA", BA);
					r.add("BB", BB);
					r.add("BA_WARIS", fracWaris.numerator());
					r.add("BB_WARIS", fracWaris.denominator());
					r.add("STATUS_TADBIR", "Y");
					r.add("ID_PA1", idPA1);
					r.add("ID_PA2", idPA2);
					r.add("ID_PA3", idPA3);
					r.add("ID_PA4", idPA4);
					
					r.add("ID_MASUK", userId);			
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
					stmt.executeUpdate(sql);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removeHAPTHilang(String idHA) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_OB IS NULL AND ID_PERINTAHHAOBMST = '" + idHA + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				SQLRenderer r = new SQLRenderer();
				
				//TBLPPKPERINTAHHAOBDTL
				r.add("ID_PERINTAHHAOBDTL", rs.getString("ID_PERINTAHHAOBDTL"));
				
				sql = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
				stmt.executeUpdate(sql);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Fraction getFractionSimatiHTA(String idHTA,String id_permohonansimati) throws Exception{
		
		Db db = null;
		String sql = "";
		Fraction fractionSimati =  new Fraction(1, 1);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.BA_SIMATI, A.BB_SIMATI " +
					" FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A" +
					" WHERE A.ID_HTA=A1.ID_HTA " +
					"AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI  " +
							"AND A1.ID_HTA = '" + idHTA + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				if (rs.getString("BA_SIMATI") != null && rs.getString("BB_SIMATI") != null){
					//if (rs.getInt("BB_SIMATI") > 0){
					//Azam change on 31/3/2010
					if (rs.getLong("BB_SIMATI") > 0){
						Fraction f2 = new Fraction(rs.getLong("BA_SIMATI"),rs.getLong("BB_SIMATI"));
						fractionSimati = f2;
					}
				}
			}

		} finally {
			if (db != null)
				db.close();
		}
		return fractionSimati;
	}
	
	public Fraction getFractionSimatiHA(String idHA) throws Exception{
		
		Db db = null;
		String sql = "";
		Fraction fractionSimati =  new Fraction(1, 1);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.BA_SIMATI, A.BB_SIMATI " +
					" FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A " +
					" WHERE A1.ID_HA = A.ID_HA AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
					" AND A1.ID_HA = '" + idHA + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				if (rs.getString("BA_SIMATI") != null && rs.getString("BB_SIMATI") != null){
					if (rs.getLong("BB_SIMATI") > 0){
						Fraction f2 = new Fraction(rs.getLong("BA_SIMATI"),rs.getLong("BB_SIMATI"));
						fractionSimati = fractionSimati.times(f2);
					}
				}
			}

		} finally {
			if (db != null)
				db.close();
		}
		return fractionSimati;
	}
	
	public void updatePecahanWarisHTA(String idPerintahHTAOBMST) throws Exception {
		Db db = null;
		String sql = "";
		Vector list = new Vector();
		Vector list2 = new Vector();
		
		list.clear();
		list2.clear();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHHTAOBDTL, ID_PERINTAHHTAOBMST, BB_WARIS, BA_WARIS FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){					
				h = new Hashtable();
				h.put("BA_WARIS", rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS", rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("ID_PERINTAHHTAOBDTL", rs.getString("ID_PERINTAHHTAOBDTL") == null ? "" : rs.getString("ID_PERINTAHHTAOBDTL"));
				list.addElement(h);				
			}
			   
			for (int i = 0; i < list.size(); i++) {
				Hashtable k = (Hashtable) list.get(i);
				
				Pecahan pchn = new Pecahan(BigInteger.valueOf(Long.parseLong(k.get("BA_WARIS").toString())), BigInteger.valueOf(Long.parseLong(k.get("BB_WARIS").toString())));

//				Pecahan pchn = new Pecahan(Long.parseLong(k.get("BA_WARIS").toString()), Long.parseLong(k.get("BB_WARIS").toString()));
//				Pecahan pchn1 = new Pecahan(frac1.getNumerator(), frac1.getDenominator());
//				listFrac = pchn1.addToList(listFrac);
				list2 = pchn.addToList(list2);							
			}
	
			Hashtable hash;
			for (int j = list.size()-1 ; j >= 0; j--) {
				hash = (Hashtable) list.get(j);
				Pecahan pchn1 = (Pecahan) list2.get(j);
				sql = "UPDATE  TBLPPKPERINTAHHTAOBDTL SET BA_WARIS = '" + pchn1.getPengangka() + "', BB_WARIS = '" + pchn1.getPenyebut() + "' WHERE ID_PERINTAHHTAOBDTL = '"+ hash.get("ID_PERINTAHHTAOBDTL").toString()+ "'";
				//sql = "UPDATE  TBLPPKPERINTAHHTAOBDTL SET BA_WARIS_STR = '" + pchn1.getPengangka() + "', BB_WARIS_STR = '" + pchn1.getPenyebut() + "' WHERE ID_PERINTAHHTAOBDTL = '"+ hash.get("ID_PERINTAHHTAOBDTL").toString()+ "'";
				stmt.executeQuery(sql);	
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePecahanWarisHA(String idPerintahHAOBMST) throws Exception {
		Db db = null;
		String sql = "";
		Vector list = new Vector();
		Vector list2 = new Vector();
		
		list.clear();
		list2.clear();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHHAOBDTL, ID_PERINTAHHAOBMST, BB_WARIS, BA_WARIS FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){					
				h = new Hashtable();
				h.put("BA_WARIS", rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS", rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("ID_PERINTAHHAOBDTL", rs.getString("ID_PERINTAHHAOBDTL") == null ? "" : rs.getString("ID_PERINTAHHAOBDTL"));
				list.addElement(h);				
			}
			    
			for (int i = 0; i < list.size(); i++) {
				Hashtable k = (Hashtable) list.get(i);
				Pecahan pchn = new Pecahan(BigInteger.valueOf(Long.parseLong(k.get("BA_WARIS").toString())), BigInteger.valueOf(Long.parseLong(k.get("BB_WARIS").toString())));
				//Pecahan pchn = new Pecahan(Long.parseLong(k.get("BA_WARIS").toString()), Long.parseLong(k.get("BB_WARIS").toString()));
				list2 = pchn.addToList(list2);							
			}
			
			Hashtable hash;
			for (int j = list.size()-1 ; j >= 0; j--) {
				hash = (Hashtable) list.get(j);
				Pecahan pchn1 = (Pecahan) list2.get(j);
				sql = "UPDATE  TBLPPKPERINTAHHAOBDTL SET BA_WARIS = '" + pchn1.getPengangka() + "', BB_WARIS = '" + pchn1.getPenyebut() + "' WHERE ID_PERINTAHHAOBDTL = '"+ hash.get("ID_PERINTAHHAOBDTL").toString()+ "'";
				stmt.executeQuery(sql);	
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removeHTAOBDTL(String idPerintahHTAOBMST, String idOB) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBDTL
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_OB", idOB);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removeHAOBDTL(String idPerintahHAOBMST, String idOB) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBDTL
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			r.add("ID_OB", idOB);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getSenaraiIdPermohonanSimati(String idSimati, String idPermohonanSimati) throws Exception{
		
		Db db = null;
		String sql = "";
		String temp = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONAN A, TBLPPKPERMOHONANSIMATI B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_SIMATI = '" + idSimati + "'"
					+ "AND NVL(A.NO_SUBJAKET,0) <= '" + getNoSubjaket(idSimati, idPermohonanSimati) + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){
				if ("".equals(temp)){
					temp = rs.getString("ID_PERMOHONANSIMATI");
				} else {
					temp = temp + "," + rs.getString("ID_PERMOHONANSIMATI");
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		if ("".equals(temp)){
			return "''";
		} else {
			return temp;
		}
	}
	
	private String getNoSubjaket(String idSimati, String idPermohonanSimati) throws Exception{
		
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NVL(A.NO_SUBJAKET,0) AS NO_SUBJAKET FROM TBLPPKPERMOHONAN A, TBLPPKPERMOHONANSIMATI B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' AND B.ID_SIMATI = '" + idSimati + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
		
			if (rs.next()){
				return rs.getString("NO_SUBJAKET");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiPembahagianHTAPKTDTL(String idPerintahHTAOBMST, String idSimati, String idPermohonanSimati) throws Exception {
		Db db = null;
		senaraiPembahagianHTAPKTDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " (SELECT A.BA FROM TBLPPKPERINTAHPEMBAHAGIAN A, TBLPPKPERINTAHHTAOBMST B " +
						"WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
				+ " (SELECT A.BB FROM TBLPPKPERINTAHPEMBAHAGIAN A, TBLPPKPERINTAHHTAOBMST B " +
						" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG = 1"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " (SELECT A.BA FROM TBLPPKPERINTAHPEMBAHAGIAN A, TBLPPKPERINTAHHTAOBMST B " +
						" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
				+ " (SELECT A.BB FROM TBLPPKPERINTAHPEMBAHAGIAN A, TBLPPKPERINTAHHTAOBMST B " +
						" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			myLogger.info("###setDataSenaraiHTAPTDTL( :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				h.put("statusHidup",rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				senaraiPembahagianHTAPKTDTL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","0");
		    	h.put("BB","0");
		    	h.put("status","");
		    	h.put("statusHidup","");
		    	senaraiPembahagianHTAPKTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePembahagianHTAPKT(String idPerintahHTAOBMST, String idOB, String status, String BA, String BB, String idPerintah, HttpSession session,String idPermohonanSimati) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		Vector listPemegangAmanah = new Vector();
		listPemegangAmanah.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//GET FRACTION SIMATI
			sql = "SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsHTA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHTA.next()){
				fracSimati = getFractionSimatiHTA(rsHTA.getString("ID_HTA"),idPermohonanSimati);
			}
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			fracWaris = fracWaris.times(fracSimati);
			
			//GET ROW OB
			sql = "SELECT ID_PERINTAHPEMBAHAGIAN FROM TBLPPKPERINTAHPEMBAHAGIAN WHERE ID_OB = '" + idOB + "' AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsRowOB = stmt.executeQuery(sql);
			
			if (rsRowOB.next()){
				//UPDATE TBLPPKPERINTAHPEMBAHAGIAN		
				r.update("ID_PERINTAHPEMBAHAGIAN", rsRowOB.getString("ID_PERINTAHPEMBAHAGIAN"));
				
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");					
				} else {
					r.add("STATUS_TADBIR", "");
				}
		
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPPKPERINTAHPEMBAHAGIAN");
				stmt.executeUpdate(sql);
				
			} else {
				//NEW TBLPPKPERINTAHPEMBAHAGIAN				
				long idPerintahPembahagian = DB.getNextID("TBLPPKPERINTAHPEMBAHAGIAN_SEQ");
				r.add("ID_PERINTAHPEMBAHAGIAN", idPerintahPembahagian);
				r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
				r.add("ID_OB", idOB);
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPPKPERINTAHPEMBAHAGIAN");
				stmt.executeUpdate(sql);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePecahanWarisHTAPKT(String idPerintahHTAOBMST) throws Exception {
		Db db = null;
		String sql = "";
		Vector list = new Vector();
		Vector list2 = new Vector();
		
		list.clear();
		list2.clear();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHPEMBAHAGIAN, ID_PERINTAHHTAOBMST, BB_WARIS, BA_WARIS FROM TBLPPKPERINTAHPEMBAHAGIAN WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){					
				h = new Hashtable();
				h.put("BA_WARIS", rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS", rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("ID_PERINTAHPEMBAHAGIAN", rs.getString("ID_PERINTAHPEMBAHAGIAN") == null ? "" : rs.getString("ID_PERINTAHPEMBAHAGIAN"));
				list.addElement(h);				
			}
			    
			for (int i = 0; i < list.size(); i++) {
				Hashtable k = (Hashtable) list.get(i);
				Pecahan pchn = new Pecahan(BigInteger.valueOf(Long.parseLong(k.get("BA_WARIS").toString())), BigInteger.valueOf(Long.parseLong(k.get("BB_WARIS").toString())));
				//Pecahan pchn = new Pecahan(Long.parseLong(k.get("BA_WARIS").toString()), Long.parseLong(k.get("BB_WARIS").toString()));
				list2 = pchn.addToList(list2);							
			}
			
			Hashtable hash;
			for (int j = list.size()-1 ; j >= 0; j--) {
				hash = (Hashtable) list.get(j);
				Pecahan pchn1 = (Pecahan) list2.get(j);
				sql = "UPDATE  TBLPPKPERINTAHPEMBAHAGIAN SET BA_WARIS = '" + pchn1.getPengangka() + "', BB_WARIS = '" + pchn1.getPenyebut() + "' WHERE ID_PERINTAHPEMBAHAGIAN = '"+ hash.get("ID_PERINTAHPEMBAHAGIAN").toString()+ "'";
				stmt.executeQuery(sql);	
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removePembahagianHTAPKT(String idPerintahHTAOBMST, String idOB) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHPEMBAHAGIAN
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_OB", idOB);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHPEMBAHAGIAN");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getSenaraiFail_semakanPerintahHQ() {
		return senaraiFail_semakanPerintahHQ;
	}
	
	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTA() {
		return senaraiHTA;
	}

	public void setSenaraiHTA(Vector senaraiHTA) {
		this.senaraiHTA = senaraiHTA;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPopup() {
		return senaraiHTAPopup;
	}

	public void setSenaraiHTAPopup(Vector senaraiHTAPopup) {
		this.senaraiHTAPopup = senaraiHTAPopup;
	}

	public Vector<Hashtable<String, String>> getBeanMaklumatHTA() {
		return beanMaklumatHTA;
	}

	public void setBeanMaklumatHTA(Vector beanMaklumatHTA) {
		this.beanMaklumatHTA = beanMaklumatHTA;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTATH() {
		return senaraiHTATH;
	}

	public void setSenaraiHTATH(Vector senaraiHTATH) {
		this.senaraiHTATH = senaraiHTATH;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatHTATH() {
		return beanMaklumatHTATH;
	}

	public void setBeanMaklumatHTATH(Vector beanMaklumatHTATH) {
		this.beanMaklumatHTATH = beanMaklumatHTATH;
	}

	public Vector<Hashtable<String,String>> getSenaraiHA() {
		return senaraiHA;
	}
	
	public Vector<Hashtable<String,String>> getSenaraiHAARB() {
		return senaraiHAARB;
	}
	
	public Vector<Hashtable<String,String>> getSenaraiOBARB() {
		return senaraiOBARB;
	}
	
	public Vector<Hashtable<String,String>> getSenaraiPerintahHAARB() {
		return senaraiPerintahHAARB;
	}
	

	public void setSenaraiHA(Vector senaraiHA) {
		this.senaraiHA = senaraiHA;
	}
	
	public void setSenaraiHAARB(Vector senaraiARB) {
		this.senaraiHAARB = senaraiHAARB;
	}
	
	public void setSenaraiOBARB(Vector senaraiOBARB) {
		this.senaraiOBARB = senaraiOBARB;
	}
	
	public void setSenaraiPerintahHAARB(Vector senaraiPerintahHAARB) {
		this.senaraiPerintahHAARB = senaraiPerintahHAARB;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPopup() {
		return senaraiHAPopup;
	}

	public void setSenaraiHAPopup(Vector senaraiHAPopup) {
		this.senaraiHAPopup = senaraiHAPopup;
	}

	public Vector<Hashtable<String, String>> getBeanMaklumatHA() {
		return beanMaklumatHA;
	}

	public void setBeanMaklumatHA(Vector beanMaklumatHA) {
		this.beanMaklumatHA = beanMaklumatHA;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPT() {
		return senaraiHTAPT;
	}

	public void setSenaraiHTAPT(Vector senaraiHTAPT) {
		this.senaraiHTAPT = senaraiHTAPT;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPT() {
		return senaraiHAPT;
	}

	public void setSenaraiHAPT(Vector senaraiHAPT) {
		this.senaraiHAPT = senaraiHAPT;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPKT() {
		return senaraiHTAPKT;
	}

	public void setSenaraiHTAPKT(Vector senaraiHTAPKT) {
		this.senaraiHTAPKT = senaraiHTAPKT;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPKT() {
		return senaraiHAPKT;
	}

	public void setSenaraiHAPKT(Vector senaraiHAPKT) {
		this.senaraiHAPKT = senaraiHAPKT;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPL() {
		return senaraiHTAPL;
	}

	public void setSenaraiHTAPL(Vector senaraiHTAPL) {
		this.senaraiHTAPL = senaraiHTAPL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPL() {
		return senaraiHAPL;
	}

	public void setSenaraiHAPL(Vector senaraiHAPL) {
		this.senaraiHAPL = senaraiHAPL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPF() {
		return senaraiHTAPF;
	}

	public void setSenaraiHTAPF(Vector senaraiHTAPF) {
		this.senaraiHTAPF = senaraiHTAPF;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPF() {
		return senaraiHAPF;
	}

	public void setSenaraiHAPF(Vector senaraiHAPF) {
		this.senaraiHAPF = senaraiHAPF;
	}

	public Vector<Hashtable<String,String>> getBeanHeaderDetail() {
		return beanHeaderDetail;
	}

	public void setBeanHeaderDetail(Vector beanHeaderDetail) {
		this.beanHeaderDetail = beanHeaderDetail;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPFDTL() {
		return senaraiHTAPFDTL;
	}

	public void setSenaraiHTAPFDTL(Vector senaraiHTAPFDTL) {
		this.senaraiHTAPFDTL = senaraiHTAPFDTL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPFDTL() {
		return senaraiHAPFDTL;
	}

	public void setSenaraiHAPFDTL(Vector senaraiHAPFDTL) {
		this.senaraiHAPFDTL = senaraiHAPFDTL;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatHTAPL() {
		return beanMaklumatHTAPL;
	}

	public void setBeanMaklumatHTAPL(Vector beanMaklumatHTAPL) {
		this.beanMaklumatHTAPL = beanMaklumatHTAPL;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatHAPL() {
		return beanMaklumatHAPL;
	}

	public void setBeanMaklumatHAPL(Vector beanMaklumatHAPL) {
		this.beanMaklumatHAPL = beanMaklumatHAPL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPTDTL() {
		return senaraiHTAPTDTL;
	}

	public void setSenaraiHTAPTDTL(Vector senaraiHTAPTDTL) {
		this.senaraiHTAPTDTL = senaraiHTAPTDTL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPTDTL() {
		return senaraiHAPTDTL;
	}

	public void setSenaraiHAPTDTL(Vector senaraiHAPTDTL) {
		this.senaraiHAPTDTL = senaraiHAPTDTL;
	}

	public Vector getSenaraiHTAPTDTLHilang() {
		return senaraiHTAPTDTLHilang;
	}

	public void setSenaraiHTAPTDTLHilang(Vector senaraiHTAPTDTLHilang) {
		this.senaraiHTAPTDTLHilang = senaraiHTAPTDTLHilang;
	}

	public Vector getSenaraiHAPTDTLHilang() {
		return senaraiHAPTDTLHilang;
	}

	public void setSenaraiHAPTDTLHilang(Vector senaraiHAPTDTLHilang) {
		this.senaraiHAPTDTLHilang = senaraiHAPTDTLHilang;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPKTDTL() {
		return senaraiHTAPKTDTL;
	}

	public void setSenaraiHTAPKTDTL(Vector senaraiHTAPKTDTL) {
		this.senaraiHTAPKTDTL = senaraiHTAPKTDTL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPKTDTL() {
		return senaraiHAPKTDTL;
	}

	public void setSenaraiHAPKTDTL(Vector senaraiHAPKTDTL) {
		this.senaraiHAPKTDTL = senaraiHAPKTDTL;
	}
	
	/*class Pecahan {

		private BigInteger pengangka;
		private BigInteger penyebut;

		public void setPengangka(BigInteger a) {
			this.pengangka = a;
		}

		public BigInteger getPengangka() {
			return pengangka;
		}

		public void setPenyebut(BigInteger b) {
			this.penyebut = b;
		}

		public BigInteger getPenyebut() {
			return penyebut;
		}

		public Pecahan(BigInteger a, BigInteger b) {

			if (b == BigInteger.ZERO){
				b = BigInteger.ONE;
			}
			setPengangka(a);
			setPenyebut(b);
		}
		
		private BigInteger gcd (BigInteger a, BigInteger b) {
			  
			if (b == BigInteger.ZERO){
				return a;
			}
			return gcd(b, a.remainder(b));
			
		  }

		public Vector addToList(Vector list){
			
			if (list.size() == 0){
				list.add(this);
				
			} else {
				BigInteger pembawahBaru = BigInteger.ONE;
				BigInteger pembawahLama = BigInteger.ONE;
				
				boolean update = false;
				
				
				Pecahan pchn0 = (Pecahan) list.get(0);
					if (pchn0.getPenyebut() != this.getPenyebut()){
						update = true;
						pembawahBaru = this.getPenyebut();
						pembawahLama = pchn0.getPenyebut();
					}
					
				if (update){
					
					//update pecahan dlm list
					for (int i = 0; i < list.size(); i++){
						Pecahan pchn1 = (Pecahan) list.get(i);
						pchn1.setPengangka(pchn1.getPengangka().multiply(pembawahBaru));
						pchn1.setPenyebut(pchn1.getPenyebut().multiply(pembawahBaru));
						list.set(i, pchn1);
					}
					
					//update pecahan yang terbaru
					this.setPengangka(this.getPengangka().multiply(pembawahLama));
					this.setPenyebut(this.getPenyebut().multiply(pembawahLama));	
				}
				list.add(this);
				
				//get gcd
				BigInteger gcd = BigInteger.ZERO;
				for (int j = 0; j < list.size(); j++) {
					Pecahan pchn2 = (Pecahan) list.get(j);
					if (pchn2.getPengangka().compareTo(BigInteger.ZERO) > 1){
						gcd = pchn2.getPenyebut().gcd(pchn2.getPengangka());
					}
				}

				//kecikkan
				if (gcd.compareTo(BigInteger.ZERO) == 0){
					gcd = BigInteger.ONE;
				}
				for (int k = 0; k < list.size(); k++) {
					Pecahan pchn3 = (Pecahan) list.get(k);
				
					pchn3.setPengangka(pchn3.getPengangka().divide(gcd));
					pchn3.setPenyebut(pchn3.getPenyebut().divide(gcd));
					list.set(k, pchn3);
				}
			}

			return list;
		}
	}*/
	
	

	public Vector<Hashtable<String,String>> getSenaraiPembahagianHTAPKTDTL() {
		return senaraiPembahagianHTAPKTDTL;
	}


	public void setSenaraiPembahagianHTAPKTDTL(Vector senaraiPembahagianHTAPKTDTL) {
		this.senaraiPembahagianHTAPKTDTL = senaraiPembahagianHTAPKTDTL;
	}


	//INTERGRASI ETANAH
	public void janaPerintah(String idFail, String idPermohonan, String idPermohonanSimati, String idPerbicaraan, String idPerintah) throws Exception {
		String sql = "";
		Db db = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
						
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "DELETE FROM INT_PPKBORANGH WHERE ID_PPKBORANGE IN (SELECT ID_PPKBORANGE FROM INT_PPKBORANGE WHERE ID_PPKHAKMILIKPERINTAH IN (SELECT ID_PPKHAKMILIKPERINTAH FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH IN (SELECT ID_PPKPERINTAH FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "'))))";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM INT_PPKBORANGE WHERE ID_PPKHAKMILIKPERINTAH IN (SELECT ID_PPKHAKMILIKPERINTAH FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH IN (SELECT ID_PPKPERINTAH FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "')))";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM INT_PPKBORANGF WHERE ID_PPKHAKMILIKPERINTAH IN (SELECT ID_PPKHAKMILIKPERINTAH FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH IN (SELECT ID_PPKPERINTAH FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "')))";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH IN (SELECT ID_PPKPERINTAH FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "'))";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "')";
			stmt.executeUpdate(sql);
			
			sql = "SELECT FAIL.NO_FAIL, SIMATI.NAMA_SIMATI,"
					+ " CASE"
					+ " WHEN SIMATI.NO_KP_BARU IS NOT NULL THEN SIMATI.NO_KP_BARU"
					+ " WHEN SIMATI.NO_KP_LAMA IS NOT NULL THEN SIMATI.NO_KP_LAMA"
					+ " WHEN SIMATI.NO_KP_LAIN IS NOT NULL THEN SIMATI.NO_KP_LAIN"
					+ " END AS NO_KP_SIMATI,"
					+ " SIMATI.TARIKH_MATI, SIMATI.NO_SIJIL_MATI,"
					+ " PEMOHON.NAMA_PEMOHON,"
					+ " CASE"
					+ " WHEN PEMOHON.NO_KP_BARU IS NOT NULL THEN PEMOHON.NO_KP_BARU"
					+ " WHEN PEMOHON.NO_KP_LAMA IS NOT NULL THEN PEMOHON.NO_KP_LAMA"
					+ " WHEN PEMOHON.NO_KP_LAIN IS NOT NULL THEN PEMOHON.NO_KP_LAIN"
					+ " END AS NO_KP_PEMOHON, PEMOHON.ALAMAT_1 AS ALAMAT_PEMOHON1, PEMOHON.ALAMAT_2 AS ALAMAT_PEMOHON2, PEMOHON.ALAMAT_3 AS ALAMAT_PEMOHON3, PEMOHON.POSKOD AS POSKOD_PEMOHON,"
					+ " BICARA.TEMPAT_BICARA, BICARA.ALAMAT_BICARA1, BICARA.ALAMAT_BICARA2, BICARA.ALAMAT_BICARA3, BICARA.POSKOD AS POSKOD_BICARA, BICARA.BANDAR AS BANDAR_BICARA, NEGERIBICARA.KOD_NEGERI AS NEGERI_BICARA, BICARA.PEG_PENGENDALI,"
					+ " PERINTAH.TARIKH_PERINTAH"
					+ " FROM TBLPFDFAIL FAIL, TBLPPKPERMOHONAN MOHON, TBLPPKPERMOHONANSIMATI MOHONSIMATI, TBLPPKSIMATI SIMATI, TBLPPKPEMOHON PEMOHON,TBLPPKKEPUTUSANPERMOHONAN KEPUTUSANMOHON, TBLPPKPERBICARAAN BICARA, TBLRUJNEGERI NEGERIBICARA, TBLPPKPERINTAH PERINTAH"
					+ " WHERE FAIL.ID_FAIL = MOHON.ID_FAIL"
					+ " AND MOHON.ID_PERMOHONAN = MOHONSIMATI.ID_PERMOHONAN"
					+ " AND MOHONSIMATI.ID_SIMATI = SIMATI.ID_SIMATI"
					+ " AND MOHON.ID_PEMOHON = PEMOHON.ID_PEMOHON"
					+ " AND MOHON.ID_PERMOHONAN = KEPUTUSANMOHON.ID_PERMOHONAN"
					+ " AND KEPUTUSANMOHON.ID_KEPUTUSANPERMOHONAN = BICARA.ID_KEPUTUSANPERMOHONAN"
					+ " AND NEGERIBICARA.ID_NEGERI = BICARA.ID_NEGERIBICARA(+)"
					+ " AND BICARA.ID_PERBICARAAN = PERINTAH.ID_PERBICARAAN"
					+ " AND FAIL.ID_FAIL = '" + idFail + "'"
					+ " AND BICARA.ID_PERBICARAAN = '" + idPerbicaraan + "'"
					+ " AND PERINTAH.ID_PERINTAH = '" + idPerintah + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				//INT_PPKPERINTAH				
				long idPPKPerintah = DB.getNextID("INT_PPKPERINTAH_SEQ");
				r.add("ID_PPKPERINTAH", idPPKPerintah);
				r.add("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				r.add("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI").toUpperCase());
				r.add("NO_KPSIMATI", rs.getString("NO_KP_SIMATI") == null ? "" : rs.getString("NO_KP_SIMATI").toUpperCase());
				r.add("TARIKH_MATI", sdf.format(rs.getDate("TARIKH_MATI")));
				r.add("NO_SIJILMATI", rs.getString("NO_SIJIL_MATI") == null ? "" : rs.getString("NO_SIJIL_MATI").toUpperCase());
				r.add("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON").toUpperCase());
				r.add("NO_KPPEMOHON", rs.getString("NO_KP_PEMOHON") == null ? "" : rs.getString("NO_KP_PEMOHON").toUpperCase());				
				r.add("ALAMAT_PEMOHON1", rs.getString("ALAMAT_PEMOHON1") == null ? "" : rs.getString("ALAMAT_PEMOHON1").toUpperCase());;
				r.add("ALAMAT_PEMOHON2", rs.getString("ALAMAT_PEMOHON2") == null ? "" : rs.getString("ALAMAT_PEMOHON2").toUpperCase());
				r.add("ALAMAT_PEMOHON3", rs.getString("ALAMAT_PEMOHON3") == null ? "" : rs.getString("ALAMAT_PEMOHON3").toUpperCase());
				r.add("POSKOD_PEMOHON", rs.getString("POSKOD_PEMOHON") == null ? "" : rs.getString("POSKOD_PEMOHON"));
				r.add("TEMPAT_BICARA", rs.getString("TEMPAT_BICARA") == null ? "" : rs.getString("TEMPAT_BICARA").toUpperCase());;
				r.add("ALAMAT_BICARA1", rs.getString("ALAMAT_BICARA1") == null ? "" : rs.getString("ALAMAT_BICARA1").toUpperCase());				
				r.add("ALAMAT_BICARA2", rs.getString("ALAMAT_BICARA2") == null ? "" : rs.getString("ALAMAT_BICARA2").toUpperCase());
				r.add("ALAMAT_BICARA3", rs.getString("ALAMAT_BICARA3") == null ? "" : rs.getString("ALAMAT_BICARA3").toUpperCase());;
				r.add("POSKOD_BICARA", rs.getString("POSKOD_BICARA") == null ? "" : rs.getString("POSKOD_BICARA"));
				r.add("BANDAR_BICARA", rs.getString("BANDAR_BICARA") == null ? "" : rs.getString("BANDAR_BICARA").toUpperCase());;
				r.add("NEGERI_BICARA", rs.getString("NEGERI_BICARA") == null ? "" : rs.getString("NEGERI_BICARA").toUpperCase());
				r.add("PEGAWAI_BICARA", rs.getString("PEG_PENGENDALI") == null ? "" : rs.getString("PEG_PENGENDALI").toUpperCase());
				r.add("TARIKH_PERINTAH", rs.getString("TARIKH_PERINTAH") == null ? "" : sdf.format(rs.getDate("TARIKH_PERINTAH")));

				sql = r.getSQLInsert("INT_PPKPERINTAH");
				stmt.executeUpdate(sql);
				
				sql = "SELECT OBMST.ID_PERINTAHHTAOBMST, OBMST.ID_JENISPERINTAH, HTA.ID_JENISHM, HTA.NO_HAKMILIK, NEGERI.KOD_NEGERI AS NEGERI, DAERAH.KOD_DAERAH AS DAERAH, MUKIM.KOD_MUKIM AS MUKIM, HTA.ID_LUAS, HTA.LUAS, HTA.NO_PT, HTA.BA_SIMATI, HTA.BB_SIMATI"
						+ " FROM TBLPPKPERINTAHHTAOBMST OBMST, TBLPPKHTA HTA, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, TBLRUJMUKIM MUKIM"
						+ " WHERE OBMST.ID_HTA = HTA.ID_HTA"
						+ " AND HTA.ID_NEGERI = NEGERI.ID_NEGERI(+)"
						+ " AND HTA.ID_DAERAH = DAERAH.ID_DAERAH(+)"
						+ " AND HTA.ID_MUKIM = MUKIM.ID_MUKIM(+)"
						+ " AND HTA.JENIS_HTA = 'Y'"
						+ " AND OBMST.FLAG_HARTA = 'B'"
						+ " AND OBMST.ID_PERINTAH = '" + idPerintah + "'";
				ResultSet rsHakmilik = stmt.executeQuery(sql);
				
				while (rsHakmilik.next()){
					inserHakmilikPerintah(idPPKPerintah, rsHakmilik.getString("ID_PERINTAHHTAOBMST"), rsHakmilik.getString("ID_JENISHM"), rsHakmilik.getString("NO_HAKMILIK"), rsHakmilik.getString("ID_LUAS"), rsHakmilik.getString("LUAS"), rsHakmilik.getString("NEGERI"), rsHakmilik.getString("DAERAH"), rsHakmilik.getString("MUKIM"), 
							rsHakmilik.getString("NO_PT"), rsHakmilik.getString("BA_SIMATI"), rsHakmilik.getString("BB_SIMATI"), rsHakmilik.getString("ID_JENISPERINTAH"));
				}
			}			
			
		}  finally {
			if (db != null)
				db.close();
		}	
	}


	private void inserHakmilikPerintah(long idPPKPerintah, String idPerintahHTAOBMST, String idJenisHakmilik,
			String noHakmilik, String idLuas, String luas, String negeri,
			String daerah, String mukim, String noPT, String BA,
			String BB, String idJenisPerintah) throws Exception {
		String sql = "";
		Db db = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Fraction fracSimati = new Fraction(0,1);
		if (Utils.parseLong(BB) > 0){
			fracSimati = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
		}

		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// INT_PPKHAKMILIKPERINTAH
			long idPPKHakmilikPerintah = DB
					.getNextID("INT_PPKHAKMILIKPERINTAH_SEQ");
			r.add("ID_PPKHAKMILIKPERINTAH", idPPKHakmilikPerintah);
			r.add("ID_PPKPERINTAH", idPPKPerintah);
			r.add("ID_HAKMILIK", negeri.trim()
					+ daerah.trim() + mukim.trim()
					+ getKodJenisHakmilik(idJenisHakmilik).trim()
					+ Utils.digitLastFormatted(noHakmilik, 8));
			r.add("ID_JENISHAKMILIK", idJenisHakmilik == null ? "" : idJenisHakmilik);
			r.add("NO_HAKMILIK", noHakmilik == null ? "" : noHakmilik);
			r.add("ID_LUAS", idLuas == null ? "" : idLuas);
			r.add("LUAS", luas == null ? "" : luas);
			r.add("NEGERI", negeri == null ? "" : negeri);
			r.add("DAERAH", daerah == null ? "" : daerah);
			r.add("MUKIM", mukim == null ? "" : mukim);
			r.add("NO_LOT", noPT == null ? "" : noPT);
			r.add("BA_SIMATI", BA == null ? "" : BA);
			r.add("BB_SIMATI", BB == null ? "" : BB);	
			r.add("ID_JENIS_PERINTAH", idJenisPerintah == null ? "" : idJenisPerintah);	
			if ("1".equals(idJenisPerintah)) {
				r.add("JENIS_PEMBAHAGIAN", "1");
			} else if ("2".equals(idJenisPerintah)) {
				r.add("JENIS_PEMBAHAGIAN", "3");
			}

			sql = r.getSQLInsert("INT_PPKHAKMILIKPERINTAH");
			stmt.executeUpdate(sql);
			
			if ("1".equals(idJenisPerintah)) {
				//GENERATE DATA BORANG E
				sql = "SELECT OBDTL.ID_PERINTAHHTAOBDTL, OB.NAMA_OB,"
						+ " CASE"
						+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
				        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
				        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
				        + " END AS JENIS_PENGENALAN,"						
						+ " CASE"
						+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
						+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
						+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
						+ " END AS NO_KP_OB,"
						+ " OB.STATUS_OB, OBDTL.BA, OBDTL.BB,"
						+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
						+ " CASE"
						+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
						+ " ELSE 'MALAYSIA'"
				        + " END AS WARGANEGARA,"
						+ " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
						+ " CASE"
						+ " WHEN OB.JANTINA = 1 THEN 'L'"
				        + " WHEN OB.JANTINA = 2 THEN 'P'"
				        + " ELSE ''"
				        + " END AS JANTINA"
						+ " FROM TBLPPKPERINTAHHTAOBDTL OBDTL, TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
						+ " WHERE OBDTL.ID_OB = OB.ID_OB AND OB.STATUS_OB IS NOT NULL AND OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OBDTL.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
				ResultSet rsOB = stmt.executeQuery(sql);
				
				while (rsOB.next()){
					insertBorangE(idPPKHakmilikPerintah, rsOB.getString("ID_PERINTAHHTAOBDTL"), rsOB.getString("NAMA_OB"), rsOB.getString("JENIS_PENGENALAN"), rsOB.getString("NO_KP_OB"), rsOB.getString("STATUS_OB"), rsOB.getString("BA"), rsOB.getString("BB"),
							rsOB.getString("ALAMAT1"), rsOB.getString("ALAMAT2"), rsOB.getString("ALAMAT3"), rsOB.getString("POSKOD"), rsOB.getString("BANDAR"), rsOB.getString("NEGERI"), rsOB.getString("WARGANEGARA"),
							rsOB.getString("UMUR"), rsOB.getString("ID_TARAFKPTG"), rsOB.getString("ID_SAUDARA"), rsOB.getString("JANTINA"), rsOB.getDate("TARIKH_LAHIR"), fracSimati);
				}
			} else if ("2".equals(idJenisPerintah)) {
				//GENERATE DATA BORANG F
				sql = "SELECT OB.NAMA_OB,"
						+ " CASE"
						+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
				        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
				        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
				        + " END AS JENIS_PENGENALAN,"						
						+ " CASE"
						+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
						+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
						+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
						+ " END AS NO_KP_OB,"
						+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
						+ " CASE"
						+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
						+ " ELSE 'MALAYSIA'"
				        + " END AS WARGANEGARA,"
				        + " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
						+ " CASE"
						+ " WHEN OB.JANTINA = 1 THEN 'L'"
				        + " WHEN OB.JANTINA = 2 THEN 'P'"
				        + " ELSE ''"
				        + " END AS JANTINA"
						+ " FROM TBLPPKPERINTAHHTAOBDTL OBDTL, TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
						+ " WHERE OBDTL.ID_OB = OB.ID_OB AND OB.STATUS_OB IS NOT NULL AND OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OBDTL.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
				ResultSet rsOB = stmt.executeQuery(sql);
				
				while (rsOB.next()){
					insertBorangF(idPPKHakmilikPerintah, rsOB.getString("NAMA_OB"), rsOB.getString("JENIS_PENGENALAN"), rsOB.getString("NO_KP_OB"),
							rsOB.getString("ALAMAT1"), rsOB.getString("ALAMAT2"), rsOB.getString("ALAMAT3"), rsOB.getString("POSKOD"), rsOB.getString("BANDAR"), rsOB.getString("NEGERI"), rsOB.getString("WARGANEGARA"),
							rsOB.getString("UMUR"), rsOB.getString("ID_TARAFKPTG"), rsOB.getString("ID_SAUDARA"), rsOB.getString("JANTINA"), rsOB.getDate("TARIKH_LAHIR"));
				}
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private void insertBorangE(long idPPKHakmilikPerintah, String idPerintahHTAOBDTL, String namaOB, String jenisKPOB, String noKPOB, String statusOB, String BA, String BB, String alamat1, String alamat2, String alamat3, String poskod, 
			String bandar, String negeri, String warganegara, String umur, String idTarafKPTG, String idSaudara, String jantina, Date tarikhLahir, Fraction fracSimati) throws Exception {
		String sql = "";
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Fraction fracWaris = new Fraction(0,1);
		if (Utils.parseLong(BB) > 0){
			fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
		}
		fracWaris = fracWaris.times(fracSimati);

		
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// INT_PPKBORANGE
			long idPPKBorangE = DB
					.getNextID("INT_PPKBORANGE_SEQ");
			r.add("ID_PPKBORANGE", idPPKBorangE);
			r.add("ID_PPKHAKMILIKPERINTAH", idPPKHakmilikPerintah);			
			
			r.add("NAMA_OB", namaOB == null ? "" : namaOB);
			r.add("JENIS_PENGENALAN", jenisKPOB == null ? "" : jenisKPOB);
			r.add("NO_KPOB", noKPOB == null ? "" : noKPOB);
			r.add("STATUS_OB", statusOB == null ? "" : statusOB);
			r.add("BA", BA == null ? "" : BA);
			r.add("BB", BB == null ? "" : BB);			
			r.add("BA_WARIS", fracWaris.numerator());
			r.add("BB_WARIS", fracWaris.denominator());			
			r.add("ALAMAT1", alamat1 == null ? "" : alamat1);
			r.add("ALAMAT2", alamat2 == null ? "" : alamat2);
			r.add("ALAMAT3", alamat3 == null ? "" : alamat3);
			r.add("POSKOD", poskod == null ? "" : poskod);
			r.add("BANDAR", bandar == null ? "" : bandar);
			r.add("NEGERI", negeri == null ? "" : negeri);
			r.add("WARGANEGARA", warganegara == null ? "" : warganegara);
			r.add("UMUR", umur == null ? "" : umur);
			r.add("TARIKH_LAHIR", tarikhLahir == null ? "" : sdf.format(tarikhLahir));
			r.add("ID_TARAFKPTG", idTarafKPTG == null ? "" : idTarafKPTG);
			r.add("ID_SAUDARA", idSaudara == null ? "" : idSaudara);
			r.add("JANTINA", jantina == null ? "" : jantina);
			
			sql = r.getSQLInsert("INT_PPKBORANGE");
			stmt.executeUpdate(sql);
			
			if (!"1".equals(statusOB)){
				sql = "SELECT ID_PA1, ID_PA2, ID_PA3, ID_PA4 FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBDTL = '" + idPerintahHTAOBDTL + "'";
				ResultSet rsPA = stmt.executeQuery(sql);
				
				if (rsPA.next()) {
					String idPA1 = rsPA.getString("ID_PA1");
					String idPA2 = rsPA.getString("ID_PA2");
					String idPA3 = rsPA.getString("ID_PA3");
					String idPA4 = rsPA.getString("ID_PA4");
					
					// START PA1
					if (idPA1 != null && idPA1.length() > 0){
						sql = "SELECT OB.NAMA_OB,"
								+ " CASE"
								+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
						        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
						        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
						        + " END AS JENIS_PENGENALAN,"						
								+ " CASE"
								+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
								+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
								+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
								+ " END AS NO_KP_OB,"
								+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
								+ " CASE"
								+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
								+ " ELSE 'MALAYSIA'"
						        + " END AS WARGANEGARA,"
								+ " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
								+ " CASE"
								+ " WHEN OB.JANTINA = 1 THEN 'L'"
						        + " WHEN OB.JANTINA = 2 THEN 'P'"
						        + " ELSE ''"
						        + " END AS JANTINA"
								+ " FROM TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
								+ " WHERE OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OB.ID_OB = '" + idPA1 + "'";
						ResultSet rsPA1 = stmt.executeQuery(sql);
						if (rsPA1.next()){
							insertBorangH(idPPKBorangE, rsPA1.getString("NAMA_OB"), rsPA1.getString("JENIS_PENGENALAN"), rsPA1.getString("NO_KP_OB"),
									rsPA1.getString("ALAMAT1"), rsPA1.getString("ALAMAT2"), rsPA1.getString("ALAMAT3"), rsPA1.getString("POSKOD"), rsPA1.getString("BANDAR"), rsPA1.getString("NEGERI"), rsPA1.getString("WARGANEGARA"),
									rsPA1.getString("UMUR"), rsPA1.getString("ID_TARAFKPTG"), rsPA1.getString("ID_SAUDARA"), rsPA1.getString("JANTINA"), rsPA1.getDate("TARIKH_LAHIR"));
						}
					}
					
					// START PA2
					if (idPA2 != null && idPA2.length() > 0){
						sql = "SELECT OB.NAMA_OB,"
								+ " CASE"
								+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
						        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
						        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
						        + " END AS JENIS_PENGENALAN,"						
								+ " CASE"
								+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
								+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
								+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
								+ " END AS NO_KP_OB,"
								+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
								+ " CASE"
								+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
								+ " ELSE 'MALAYSIA'"
						        + " END AS WARGANEGARA,"
						        + " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
								+ " CASE"
								+ " WHEN OB.JANTINA = 1 THEN 'L'"
						        + " WHEN OB.JANTINA = 2 THEN 'P'"
						        + " ELSE ''"
						        + " END AS JANTINA"
								+ " FROM TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
								+ " WHERE OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OB.ID_OB = '" + idPA2 + "'";
						ResultSet rsPA2 = stmt.executeQuery(sql);
						if (rsPA2.next()){
							insertBorangH(idPPKBorangE, rsPA2.getString("NAMA_OB"), rsPA2.getString("JENIS_PENGENALAN"), rsPA2.getString("NO_KP_OB"),
									rsPA2.getString("ALAMAT1"), rsPA2.getString("ALAMAT2"), rsPA2.getString("ALAMAT3"), rsPA2.getString("POSKOD"), rsPA2.getString("BANDAR"), rsPA2.getString("NEGERI"), rsPA2.getString("WARGANEGARA"),
									rsPA2.getString("UMUR"), rsPA2.getString("ID_TARAFKPTG"), rsPA2.getString("ID_SAUDARA"), rsPA2.getString("JANTINA"), rsPA2.getDate("TARIKH_LAHIR"));
						}
					}
					
					// START PA3
					if (idPA3 != null && idPA3.length() > 0){
						sql = "SELECT OB.NAMA_OB,"
								+ " CASE"
								+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
						        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
						        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
						        + " END AS JENIS_PENGENALAN,"						
								+ " CASE"
								+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
								+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
								+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
								+ " END AS NO_KP_OB,"
								+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
								+ " CASE"
								+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
								+ " ELSE 'MALAYSIA'"
						        + " END AS WARGANEGARA,"
						        + " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
								+ " CASE"
								+ " WHEN OB.JANTINA = 1 THEN 'L'"
						        + " WHEN OB.JANTINA = 2 THEN 'P'"
						        + " ELSE ''"
						        + " END AS JANTINA"
								+ " FROM TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
								+ " WHERE OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OB.ID_OB = '" + idPA3 + "'";
						ResultSet rsPA3 = stmt.executeQuery(sql);
						if (rsPA3.next()){
							insertBorangH(idPPKBorangE, rsPA3.getString("NAMA_OB"), rsPA3.getString("JENIS_PENGENALAN"), rsPA3.getString("NO_KP_OB"),
									rsPA3.getString("ALAMAT1"), rsPA3.getString("ALAMAT2"), rsPA3.getString("ALAMAT3"), rsPA3.getString("POSKOD"), rsPA3.getString("BANDAR"), rsPA3.getString("NEGERI"), rsPA3.getString("WARGANEGARA"),
									rsPA3.getString("UMUR"), rsPA3.getString("ID_TARAFKPTG"), rsPA3.getString("ID_SAUDARA"), rsPA3.getString("JANTINA"), rsPA3.getDate("TARIKH_LAHIR"));
						}
					}
					
					// START PA4
					if (idPA4 != null && idPA4.length() > 0){
						sql = "SELECT OB.NAMA_OB,"
								+ " CASE"
								+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
						        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
						        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
						        + " END AS JENIS_PENGENALAN,"						
								+ " CASE"
								+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
								+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
								+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
								+ " END AS NO_KP_OB,"
								+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
								+ " CASE"
								+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
								+ " ELSE 'MALAYSIA'"
						        + " END AS WARGANEGARA,"
								+ " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
								+ " CASE"
								+ " WHEN OB.JANTINA = 1 THEN 'L'"
						        + " WHEN OB.JANTINA = 2 THEN 'P'"
						        + " ELSE ''"
						        + " END AS JANTINA"
								+ " FROM TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
								+ " WHERE OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OB.ID_OB = '" + idPA4 + "'";
						ResultSet rsPA4 = stmt.executeQuery(sql);
						if (rsPA4.next()){
							insertBorangH(idPPKBorangE, rsPA4.getString("NAMA_OB"), rsPA4.getString("JENIS_PENGENALAN"), rsPA4.getString("NO_KP_OB"),
									rsPA4.getString("ALAMAT1"), rsPA4.getString("ALAMAT2"), rsPA4.getString("ALAMAT3"), rsPA4.getString("POSKOD"), rsPA4.getString("BANDAR"), rsPA4.getString("NEGERI"), rsPA4.getString("WARGANEGARA"),
									rsPA4.getString("UMUR"), rsPA4.getString("ID_TARAFKPTG"), rsPA4.getString("ID_SAUDARA"), rsPA4.getString("JANTINA"), rsPA4.getDate("TARIKH_LAHIR"));
						}
					}					
					
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private void insertBorangH(long idPPKBorangE, String namaOB, String jenisKPOB, String noKPOB, String alamat1, String alamat2, String alamat3, String poskod, 
			String bandar, String negeri, String warganegara, String umur, String idTarafKPTG, String idSaudara, String jantina, Date tarikhLahir) throws Exception {
		String sql = "";
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// INT_PPKBORANGH
			long idPPKBorangH = DB
					.getNextID("INT_PPKBORANGH_SEQ");
			r.add("ID_PPKBORANGH", idPPKBorangH);
			r.add("ID_PPKBORANGE", idPPKBorangE);			
			
			r.add("NAMA_OB", namaOB == null ? "" : namaOB);
			r.add("JENIS_PENGENALAN", jenisKPOB == null ? "" : jenisKPOB);
			r.add("NO_KPOB", noKPOB == null ? "" : noKPOB);
			r.add("ALAMAT1", alamat1 == null ? "" : alamat1);
			r.add("ALAMAT2", alamat2 == null ? "" : alamat2);
			r.add("ALAMAT3", alamat3 == null ? "" : alamat3);
			r.add("POSKOD", poskod == null ? "" : poskod);
			r.add("BANDAR", bandar == null ? "" : bandar);
			r.add("NEGERI", negeri == null ? "" : negeri);
			r.add("WARGANEGARA", warganegara == null ? "" : warganegara);
			r.add("UMUR", umur == null ? "" : umur);
			r.add("TARIKH_LAHIR", tarikhLahir == null ? "" : sdf.format(tarikhLahir));
			r.add("ID_TARAFKPTG", idTarafKPTG == null ? "" : idTarafKPTG);
			r.add("ID_SAUDARA", idSaudara == null ? "" : idSaudara);
			r.add("JANTINA", jantina == null ? "" : jantina);
			
			sql = r.getSQLInsert("INT_PPKBORANGH");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private void insertBorangF(long idPPKHakmilikPerintah, String namaOB, String jenisKPOB, String noKPOB, String alamat1, String alamat2, String alamat3, String poskod, 
			String bandar, String negeri, String warganegara, String umur, String idTarafKPTG, String idSaudara, String jantina, Date tarikhLahir) throws Exception {
		String sql = "";
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// INT_PPKBORANGF
			long idPPKBorangF = DB
					.getNextID("INT_PPKBORANGF_SEQ");
			r.add("ID_PPKBORANGF", idPPKBorangF);
			r.add("ID_PPKHAKMILIKPERINTAH", idPPKHakmilikPerintah);			
			
			r.add("NAMA_OB", namaOB == null ? "" : namaOB);
			r.add("JENIS_PENGENALAN", jenisKPOB == null ? "" : jenisKPOB);
			r.add("NO_KPOB", noKPOB == null ? "" : noKPOB);
			r.add("ALAMAT1", alamat1 == null ? "" : alamat1);
			r.add("ALAMAT2", alamat2 == null ? "" : alamat2);
			r.add("ALAMAT3", alamat3 == null ? "" : alamat3);
			r.add("POSKOD", poskod == null ? "" : poskod);
			r.add("BANDAR", bandar == null ? "" : bandar);
			r.add("NEGERI", negeri == null ? "" : negeri);
			r.add("WARGANEGARA", warganegara == null ? "" : warganegara);
			r.add("UMUR", umur == null ? "" : umur);
			r.add("TARIKH_LAHIR", tarikhLahir == null ? "" : sdf.format(tarikhLahir));
			r.add("ID_TARAFKPTG", idTarafKPTG == null ? "" : idTarafKPTG);
			r.add("ID_SAUDARA", idSaudara == null ? "" : idSaudara);
			r.add("JANTINA", jantina == null ? "" : jantina);
			
			sql = r.getSQLInsert("INT_PPKBORANGF");
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodJenisHakmilik(String idJenisHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK = '" + idJenisHakmilik + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("KOD_JENIS_HAKMILIK");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}


	public String getNoFailByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("NO_FAIL");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkGeneratedPerintah(String noFail) throws DbException, Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT H.NEGERI FROM INT_PPKPERINTAH P, INT_PPKHAKMILIKPERINTAH H WHERE P.ID_PPKPERINTAH = H.ID_PPKPERINTAH AND H.FLAG_HANTAR = 'Y' AND P.NO_FAIL = '" + noFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//aishah
	public String getFLAG_KEBENARAN_EDIT( String id_fail) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String flag_kebenaran_edit = "";
			String condition = "";
			
			if (id_fail != null) {
				if (!id_fail.equals("")) {
				condition += " AND F.ID_FAIL = "+id_fail ;	
				}
			}	
			
			
			sql = 	" SELECT P.FLAG_KEBENARAN_EDIT " +
					" FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P " +
					" WHERE F.ID_FAIL = P.ID_FAIL " +
					condition ;
			
					
	
			rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					flag_kebenaran_edit = rs.getString("FLAG_KEBENARAN_EDIT") == null ? "" : rs.getString("FLAG_KEBENARAN_EDIT");
				}
			
			return flag_kebenaran_edit;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	//aishah
		public String getCHECK_EDIT( String id_fail, String userId) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				String check_edit = "";
				
				sql = 	" SELECT COUNT(*) AS CHECK_EDIT " +
						" FROM TBLEDITAGIHAN A,USERS U WHERE A.USER_ID = U.USER_ID AND A.ID_FAIL = '" + id_fail + "' AND A.USER_ID = '" + userId + "' ";
				
		
				rs = stmt.executeQuery(sql);				
					while (rs.next()) {
						check_edit = rs.getString("CHECK_EDIT") == null ? "" : rs.getString("CHECK_EDIT");
					}
				
				return check_edit;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
		}
		
		//aishah
		public void updateFlagKemaskiniPerintah(String idFail) throws Exception {
			Db db = null;
			
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				//TBLPPKPERMOHONAN
				r.update("ID_FAIL", idFail);
				r.add("FLAG_EDIT_PERINTAH", "1");
				r.add("TARIKH_EDIT_PERINTAH", r.unquote("sysdate"));
				

				sql = r.getSQLUpdate("TBLPPKPERMOHONAN");
				stmt.executeUpdate(sql);
				
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		//arief add
		// Get idkeputusanpermohonan :: SEKSYEN 8 & SEKSYEN 17 ::
		public static Vector getKeputusanPermohonan(String id_permohonan)
				throws Exception {

			Db db = null;
			String sql = "";

			try {

				db = new Db();

				Statement stmt = db.getStatement();

				sql = "SELECT k.id_keputusanpermohonan, k.keputusan_permohonan ";
				sql += "FROM Tblppkkeputusanpermohonan k ";
				sql += "WHERE k.id_permohonan = '" + id_permohonan + "'";
				System.out.println("getKeputusanPermohonan :"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Vector list = new Vector();

				Hashtable h = null;

				while (rs.next()) {
					h = new Hashtable();
					h.put("id_keputusanpermohonan", rs
							.getString("id_keputusanpermohonan") == null ? "" : rs
							.getString("id_keputusanpermohonan"));
					h.put("keputusan_permohonan", rs
							.getString("keputusan_permohonan") == null ? "" : rs
							.getString("keputusan_permohonan"));

					list.addElement(h);

				}
				return list;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		//arief add
		// Data pemohon :: SEKSYEN 8 & SEKSYEN 17
		public static void setListSemak(String id_permohonan, String userId)
			throws Exception {
			Db db = null;

			listSemak.clear();
			String sql = "";

			try {
				db = new Db();

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = "SELECT distinct pm.id_negeri, n.id_Negeri, n.nama_Negeri,f.id_Fail, f.no_Fail, d.id_Daerah, p.id_Permohonan, ";
				sql += "p.tarikh_Mohon, s.no_Kp_Baru, s.no_Kp_Lama, s.jenis_Kp, s.no_Kp_Lain, s.id_Simati, ";
				sql += "s.nama_Simati, s.tarikh_Mati, pm.id_Pemohon, pm.nama_Pemohon, pm.no_kp_baru, ";
				sql += "pm.no_kP_lama, pm.jenis_Kp, pm.no_Kp_lain, pm.alamat_1, pm.alamat_2, pm.alamat_3, ";
				sql += "pm.poskod, pm.bandar, d.nama_Daerah, p.seksyen, st.keterangan, ";
				sql += "p.id_Status, mosi.id_Permohonansimati, s.umur, s.jantina, ur.id_negeri as id_negeri_jajahan, ";
				sql += "pm.umur, pm.jantina" +
						",u.id_pejabatjkptg,u.nama_pejabat, u.id_negeri as id_negeripejabat" +
						", p.no_subjaket" +
						//", dx.nama_daerah AS D_P" +
						", p.tarikh_rayuan ";
				sql += "FROM Tblpfdfail f,Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, ";
				sql += "Tblppkpemohon pm, Tblrujstatus st, tblrujsuburusanstatusfail sstf" +
						//", tblrujdaerah dx" +
						", tblrujsuburusanstatus sst ";
				sql += "" +
						//"tblrujpejabatjkptg u" +
						", Tblppkpermohonansimati mosi, Users_Internal ur ";
				sql += ",(SELECT RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT,RPU.ID_NEGERI,RPU.ID_DAERAHURUS ID_DAERAH FROM "+
						" TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU "+
						" WHERE RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG "+
						" AND RPU.ID_JENISPEJABAT = 22 "+
						" AND RP.ID_SEKSYEN = 2 "+
						//" AND RPU.ID_DAERAHURUS = 5 "+
						") U ";
				sql += "WHERE f.id_Negeri = n.id_Negeri(+) ";
				sql += "AND sstf.id_permohonan = p.id_permohonan ";
				sql += "AND sstf.id_suburusanstatus = sst.id_suburusanstatus ";
				sql += "AND sst.id_status = st.id_status ";
				sql += "AND p.id_Daerahmhn = d.id_Daerah(+) ";
				sql += "AND ur.user_id = '" + userId + "'";
				//sql += " And ur.ID_PEJABATJKPTG = u.ID_PEJABATJKPTG ";
				sql += " AND p.id_Fail = f.id_Fail ";
				sql += "AND p.ID_PEMOHON = pm.ID_PEMOHON ";
				sql += "AND s.id_Simati = mosi.id_Simati ";
				sql += "AND p.id_Permohonan = mosi.id_Permohonan ";
				sql += "AND st.id_Status = p.id_Status ";
				sql += "AND d.id_daerah = p.id_daerahmhn ";
				//sql += "AND u.id_daerah = dx.id_daerah(+) ";
				sql += "AND p.id_daerahmhn = u.id_daerah ";
				// sql += "AND sstf.aktif = '1' ";

				sql += " AND p.id_Permohonan = '" + id_permohonan + "'";
				System.out.println("sql*********** = " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;

				while (rs.next()) {
					h = new Hashtable();

					h.put("id_permohonansimati", rs
							.getString("id_Permohonansimati") == null ? "" : rs
							.getString("id_Permohonansimati"));
					// h.put("id_suburusanstatusfail",
					// rs.getString("id_suburusanstatusfail"));
					h.put("idFail", rs.getString("id_Fail") == null ? "" : rs
							.getString("id_Fail"));
					h.put("noFail", rs.getString("no_Fail") == null ? "" : rs
							.getString("no_Fail"));
					h.put("idDaerah", rs.getString("id_Daerah") == null ? "" : rs
							.getString("id_Daerah"));
					h.put("idPermohonan",
							rs.getString("id_Permohonan") == null ? "" : rs
									.getString("id_Permohonan"));
					h.put("tarikhMohon", rs.getDate("tarikh_Mohon") == null ? ""
							: Format.format(rs.getDate("tarikh_Mohon")));
					h.put("idSimati", rs.getString("id_Simati") == null ? "" : rs
							.getString("id_Simati"));
					h.put("namaSimati", rs.getString("nama_Simati") == null ? ""
							: rs.getString("nama_Simati"));
					h.put("tarikhMati", rs.getDate("tarikh_Mati") == null ? ""
							: Format.format(rs.getDate("tarikh_Mati")));
					h.put("idPemohon", rs.getString("id_Pemohon") == null ? "" : rs
							.getString("id_Pemohon"));
					h.put("namaPemohon", rs.getString("nama_Pemohon") == null ? ""
							: rs.getString("nama_Pemohon"));
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
					h.put("idnegeri", rs.getString(16) == null ? "" : rs
							.getString(16));
					h.put("namanegeri", rs.getString("nama_Negeri") == null ? ""
							: rs.getString("nama_Negeri"));
					h.put("namadaerah", rs.getString("nama_Daerah") == null ? ""
							: rs.getString("nama_Daerah"));
					h.put("seksyen", rs.getString("seksyen") == null ? "" : rs
							.getString("seksyen"));
					h.put("keterangan", rs.getString("keterangan") == null ? ""
							: rs.getString("keterangan"));
					h.put("id_Status", rs.getString("id_Status") == null ? "" : rs
							.getString("id_Status"));

					if (rs.getString("id_negeripejabat").equals("7")) {
						h.put("namaPejabat",
								rs.getString("nama_pejabat") == null ? "" : rs
										.getString("nama_pejabat"));
					} else {
//						h.put("namaPejabat", rs.getString("nama_pejabat") + ","
//								+ rs.getString("D_P") == null ? "" : rs
//								.getString("nama_pejabat")
//								+ "," + rs.getString("D_P"));
						h.put("namaPejabat"
								, rs.getString("nama_pejabat") + ","+ rs.getString("nama_Daerah") == null ? "" : rs.getString("nama_pejabat")+ "," + rs.getString("nama_Daerah"));
					}

					h.put("id_pejabatjkptg",
							rs.getString("id_pejabatjkptg") == null ? "" : rs
									.getString("id_pejabatjkptg"));
					h.put("tarikh_rayuan", rs.getDate("tarikh_rayuan") == null ? ""
							: Format.format(rs.getDate("tarikh_rayuan")));
					if (rs.getString(3) == null || rs.getString(3) == "") {
						h.put("pmNama_negeri", "");
					} else {
						h.put("pmNama_negeri", rs.getString(3));
					}

					h.put("pmidnegeri",
							rs.getString("id_negeri_jajahan") == null ? "" : rs
									.getString("id_negeri_jajahan"));

					listSemak.addElement(h);
				}
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		//arief add
		// GET DATA NOTIS :: SEKSYEN 8 & SEKSYEN 17 ::
		public static void setListSemakWithData(String id_keputusanpermohonan)
				throws Exception {

			Db db = null;

			listSemakWithData.clear();
			String sql = "";
			String sql2 = "";
			String id_perbicaraan = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = " SELECT MAX(id_perbicaraan) as id_perbicaraan FROM (";
				sql += " SELECT distinct a.id_perbicaraan FROM Tblppkperbicaraan a ";
				sql += " WHERE ";
				sql += " tarikh_masuk = (select max(tarikh_masuk) from tblppkperbicaraan b where b.id_keputusanpermohonan=a.id_keputusanpermohonan) ";
				sql += " and a.id_keputusanpermohonan = '" + id_keputusanpermohonan
						+ "'";
				sql += " ) ";
				System.out.println("setListSemakWithDataA :"+sql);
				ResultSet rsMT = stmt.executeQuery(sql);
				while (rsMT.next()) {
					id_perbicaraan = rsMT.getString("id_perbicaraan");
				}

				SQLRenderer r = new SQLRenderer();
				r.add("pb.id_perbicaraan");
				r.add("pb.id_unitpsk");
				r.add("pb.id_keputusanpermohonan");
				r.add("pb.tarikh_notis");
				r.add("pb.tarikh_bicara");
				r.add("pb.masa_bicara");

				r.add("pb.tempat_bicara");
				r.add("pb.bil_bicara");
				r.add("pb.alamat_bicara1");
				r.add("pb.alamat_bicara2");
				r.add("pb.alamat_bicara3");

				r.add("pb.poskod");
				r.add("pb.id_negeribicara");
				r.add("pb.peg_pengendali");
				r.add("pb.tarikh_masuk");

				r.add("pb.jenis_masa_bicara");

				r.add("pb.id_pejabat");
				r.add("pb.id_jenispejabat");
				r.add("pb.SIGNED_TEXT");
				r.add("pb.tarikh_bicara_dahulu");
				r.add("pb.sebab_pinda_tarikh");
				
				r.add("pb.id_keputusanpermohonan", id_keputusanpermohonan);
				r.add("pb.id_perbicaraan", id_perbicaraan);

				sql2 = r.getSQLSelect("Tblppkperbicaraan pb");
				System.out.println("setListSemakWithDataB :"+sql2);
				ResultSet rs = stmt.executeQuery(sql2);
				Hashtable h;

				while (rs.next()) {

					h = new Hashtable();
					h.put("id_perbicaraan",
							rs.getString("id_perbicaraan") == null ? "" : rs
									.getString("id_perbicaraan"));
					h.put("id_unitpsk", rs.getString("id_unitpsk") == null ? ""
							: rs.getString("id_unitpsk"));
					h.put("tarikh_notis", rs.getDate("tarikh_notis") == null ? ""
							: Format.format(rs.getDate("tarikh_notis")));
					h.put("tarikh_bicara", rs.getDate("tarikh_bicara") == null ? ""
							: Format.format(rs.getDate("tarikh_bicara")));

					h.put("masa_bicara", rs.getString("masa_bicara") == null ? ""
							: rs.getString("masa_bicara"));
					h.put("tempat_bicara",
							rs.getString("tempat_bicara") == null ? "" : rs
									.getString("tempat_bicara"));
					h.put("bil_bicara", rs.getString("bil_bicara") == null ? ""
							: rs.getString("bil_bicara"));
					h.put("alamat_bicara1",
							rs.getString("alamat_bicara1") == null ? "" : rs
									.getString("alamat_bicara1"));
					h.put("alamat_bicara2",
							rs.getString("alamat_bicara2") == null ? "" : rs
									.getString("alamat_bicara2"));
					h.put("alamat_bicara3",
							rs.getString("alamat_bicara3") == null ? "" : rs
									.getString("alamat_bicara3"));
					h.put("poskod", rs.getString("poskod") == null ? "" : rs
							.getString("poskod"));
					h.put("id_negeribicara",
							rs.getString("id_negeribicara") == null ? "" : rs
									.getString("id_negeribicara"));
					h.put("peg_pengendali",
							rs.getString("peg_pengendali") == null ? "" : rs
									.getString("peg_pengendali"));
					h.put("id_pejabat", rs.getString("id_pejabat") == null ? ""
							: rs.getString("id_pejabat"));
					h.put("id_jenispejabat",
							rs.getString("id_jenispejabat") == null ? "" : rs
									.getString("id_jenispejabat"));

					h.put("jenis_masa_bicara",rs.getString("jenis_masa_bicara") == null ? "" : rs.getString("jenis_masa_bicara"));
					
					h.put("SIGNED_TEXT",rs.getString("SIGNED_TEXT") == null ? "" : rs.getString("SIGNED_TEXT"));
					h.put("tarikh_bicara_dahulu", rs.getDate("tarikh_bicara_dahulu") == null ? ""
							: Format.format(rs.getDate("tarikh_bicara_dahulu")));
					h.put("sebab_pinda_tarikh",rs.getString("sebab_pinda_tarikh") == null ? "" : rs.getString("sebab_pinda_tarikh"));
					

					listSemakWithData.addElement(h);
				}
			} finally {
				if (db != null)
					db.close();
			}

		}
		//arief add
		public String getSignedData(String idPerintah) {
			String signedData = "";
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				
				String sql = " SELECT SIGNED_TEXT FROM TBLPPKPERINTAH WHERE ID_PERINTAH = '" + idPerintah + "'";
				rs = stmt.executeQuery(sql);
				System.out.println("data dah signed: "+sql);
				if (rs.next()) {
					signedData = rs.getString("SIGNED_TEXT");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return signedData;
		}
		
		public static void deleteTBLPPKNOTISOBDTL_ORI(String ID_NOTISOBMST, String id_perbicaraan) throws Exception {

			Db db = null;
			String sql = "";

			try {

				db = new Db();
				Statement stmt = db.getStatement();
				
				/*if(statusHantarPNB){
					sql = "DELETE FROM TBLPPKNOTISOBDTL WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
				}else{*/
					//sql = "DELETE FROM TBLPPKNOTISOBDTL_TEMP WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
				//}
				
				sql = "DELETE from TBLPPKNOTISOBDTL where id_notisobmst in (select id_notisobmst from tblppknotisperbicaraan where id_perbicaraan = "+id_perbicaraan+")";

				stmt.executeUpdate(sql);
				
				System.out.println("deleteTBLPPKNOTISOBDTL "+sql);

			} finally {
				if (db != null)
					db.close();
			}

		}// delete TBLPPKNOTISOBDTL
		
		//arief add
		public static void deleteTBLPPKNOTISPERBICARAAN_ORI(String ID_NOTISOBMST, String id_perbicaraan) throws Exception {

			Db db = null;
			String sql = "";

			try {

				db = new Db();
				Statement stmt = db.getStatement();

				/*if(statusHantarPNB){
					sql = "DELETE FROM TBLPPKNOTISPERBICARAAN WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
				}else{*/
					//sql = "DELETE FROM TBLPPKNOTISPERBICARAAN_TEMP WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
				//}
					
					sql = "DELETE from TBLPPKNOTISPERBICARAAN where id_perbicaraan = "+id_perbicaraan+"";

				stmt.executeUpdate(sql);
				
				System.out.println("deleteTBLPPKNOTISPERBICARAAN "+sql);

			} finally {
				if (db != null)
					db.close();
			}

		}// delete TBLPPKNOTISPERBICARAAN
		
		//arief add
		public static void deleteTBLPPKNOTISOBMST_ORI(String ID_NOTISOBMST, String id_perbicaraan) throws Exception {

			Db db = null;
			String sql = "";

			try {

				db = new Db();
				Statement stmt = db.getStatement();

				/*if(statusHantarPNB){
					sql = "DELETE FROM TBLPPKNOTISOBMST WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
				}else{*/
					//sql = "DELETE FROM TBLPPKNOTISOBMST_TEMP WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";
				//}
				
				sql = "DELETE FROM TBLPPKNOTISOBMST WHERE ID_NOTISOBMST = '"+ ID_NOTISOBMST + "'";

				stmt.executeUpdate(sql);
				
				System.out.println("deleteTBLPPKNOTISOBMST "+sql);

			} finally {
				if (db != null)
					db.close();
			}

		}// delete TBLPPKNOTISOBMST
		
		//arief add
		public static void Tblppknotisobmst_insert(String id_perbicaraan,String id_notisobmsttemp, String jenisSerah) throws Exception {
			Db db = null;
			//String userId = session.getAttribute("_ekptg_user_id").toString();
			String sql = "";
			
			String sql3 = "";
			String sql4 = "";
			String id_notisobmst = "";

			try {
				
				db = new Db();
				Statement stmt = db.getStatement();
				Statement stmtA = db.getStatement();
				Statement stmtS = db.getStatement();
				Statement stmtB = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "INSERT INTO TBLPPKNOTISOBMST (ID_NOTISOBMST,  BIL,  TARIKH_SERAHAN,  STATUS_SERAH   , " +
						 " JENIS_SERAH ,  STATUS_AKUAN_SUMPAH,  CATATAN,  NAMA_PENGHANTAR_NOTIS ,  NO_SURAT_DAFTAR     , ID_MASUK   , " +
						 " TARIKH_MASUK  ,  ID_KEMASKINI ,  TARIKH_KEMASKINI,  ID_DB ,  ID_NOTISOBMSTLAMA , ID_PENGHANTARNOTIS  ,TARIKH_EMEL  , " +
						 " EMEL , NAMA_PENGHANTAR_LAIN   )" +
						 " SELECT ID_NOTISOBMST  ,BIL       , TARIKH_SERAHAN    ,  STATUS_SERAH   , JENIS_SERAH , STATUS_AKUAN_SUMPAH   , " +
						 " CATATAN      , NAMA_PENGHANTAR_NOTIS , NO_SURAT_DAFTAR  , ID_MASUK   , TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI , ID_DB                  , " +
						 " ID_NOTISOBMSTLAMA , ID_PENGHANTARNOTIS     , TARIKH_EMEL , EMEL ,  NAMA_PENGHANTAR_LAIN " +
						" FROM TBLPPKNOTISOBMST_TEMP" +
						" WHERE ID_NOTISOBMST = "+id_notisobmsttemp;
				System.out.println("Tblppknotisobmst "+sql);
				stmtA.executeUpdate(sql);
				System.out.println("Tblppknotisobmst "+sql);
				
				
				// insert data into TBLPPKNOTISPERBICARAAN	
				
				sql3 = "INSERT INTO TBLPPKNOTISPERBICARAAN (ID_NOTISPERBICARAAN  ," +
						" ID_PERBICARAAN  , ID_NOTISOBMST , ID_MASUK , TARIKH_MASUK , ID_KEMASKINI , TARIKH_KEMASKINI,  ID_DB , FLAG_JENIS_NOTIS)" +
						" SELECT ID_NOTISPERBICARAAN  , ID_PERBICARAAN  , ID_NOTISOBMST , ID_MASUK , TARIKH_MASUK , ID_KEMASKINI , TARIKH_KEMASKINI , ID_DB , FLAG_JENIS_NOTIS" +
						" FROM TBLPPKNOTISPERBICARAAN_TEMP" +
						" WHERE ID_NOTISOBMST = "+id_notisobmsttemp;
				System.out.println("TBLPPKNOTISPERBICARAAN 1 "+sql3);
				stmtS.executeUpdate(sql3);
				System.out.println("TBLPPKNOTISPERBICARAAN 2 "+sql3);
			
				
				// insert data into TBLPPKNOTISOBDTL	
				
				if(jenisSerah.equals("byhand"))
				{
				sql4 = "INSERT INTO TBLPPKNOTISOBDTL (ID_NOTISOBDTL ,ID_NOTISOBMST ,ID_OB ,NAMA_PENERIMA , NO_KP_BARU  ,ID_MASUK ,TARIKH_MASUK ," +
						"ID_KEMASKINI, TARIKH_KEMASKINI , ID_DB, NO_KP_LAMA, NO_KP_LAIN)" +
						" SELECT ID_NOTISOBDTL ,ID_NOTISOBMST ,ID_OB ,NAMA_PENERIMA , NO_KP_BARU  ,ID_MASUK ,TARIKH_MASUK ," +
						" ID_KEMASKINI, TARIKH_KEMASKINI , ID_DB, NO_KP_LAMA, NO_KP_LAIN" +
						" FROM TBLPPKNOTISOBDTL_TEMP" +
						" WHERE ID_NOTISOBMST = "+id_notisobmsttemp;
					System.out.println("TBLPPKNOTISOBDTL 1 "+sql4);
					stmtB.executeUpdate(sql4);
					System.out.println("TBLPPKNOTISOBDTL 1 "+sql4);
				}
			
				if(jenisSerah.equals("byhand"))
				{
					stmtB.close();
				}
				stmtS.close();
				stmtA.close();
				stmt.close();
				
			} finally {
				
				
				
				if (db != null)
					db.close();
			}
		}

		//arief add
		static Vector listobmsttemp = new Vector();
		public static Vector<Hashtable<String,String>> getID_NOTISPERBICAAAN_TEMP(String id_perbicaraan, String serahan) throws Exception {
				Db db = null;
				listobmsttemp.clear();
				String sql = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					

					sql = " SELECT A.ID_NOTISOBMST , B.JENIS_SERAH, C.ID_OB FROM TBLPPKNOTISPERBICARAAN_TEMP A, TBLPPKNOTISOBMST_TEMP B, TBLPPKNOTISOBDTL_TEMP C" +
							" WHERE A.ID_NOTISOBMST = B.ID_NOTISOBMST " +
							" AND B.ID_NOTISOBMST = C.ID_NOTISOBMST ";
							if(serahan.equals("specific")){
								sql = sql + " AND B.JENIS_SERAH in (3,5) " ;
							}
							if(serahan.equals("byhand")){
								sql = sql + " AND B.JENIS_SERAH in (1) " ;
							}
							
				   sql = sql + " AND A.ID_PERBICARAAN = '" + id_perbicaraan + "' order by A.ID_NOTISOBMST asc ";

					ResultSet rs = stmt.executeQuery(sql);
					
					myLogger.info("get sql TBLPPKNOTISOBMST_TEMP === " +sql);
					Hashtable h;
					int bil = 1;

					while (rs.next()) {
						h = new Hashtable();
						h.put("ID_NOTISOBMST",rs.getString("ID_NOTISOBMST") == null ? "" : rs.getString("ID_NOTISOBMST"));	
						h.put("JENIS_SERAH",rs.getString("JENIS_SERAH") == null ? "" : rs.getString("JENIS_SERAH"));	
						h.put("ID_OB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));	
						listobmsttemp.addElement(h);
						
						
						myLogger.info("ID_NOTISOBMST====" +rs.getString("ID_NOTISOBMST"));
				
					}
					 return listobmsttemp;
				} finally {
					if (db != null)
						db.close();
				}
			}
		
		//arief add
		public static Vector getValidPegawaiPengendali() {
			return validPegPengendali;
		}
		
		//arief add
		
		//checking sama ada user yang login adalah pegawai pengendali
		private static Vector validPegPengendali = new Vector();
		
		public static void setValidPegawaiPengendali(String userId,String idPerbicaraan, String NamaPeg, String username) throws Exception {

			Db db = null;
			Hashtable h;
			validPegPengendali.clear();
			String sql = "";
			String sql2 = "";
			String idOBdtl = "";

			try {
				db = new Db();
				Statement stmtN = db.getStatement();
				sql = " SELECT A.USER_ID " +
						" FROM TBLPPKRUJUNIT A, TBLPPKPERBICARAAN B " +
						" WHERE A.ID_UNITPSK = B.ID_UNITPSK " +
						" AND A.USER_ID = '"+userId+"' " +
						" AND B.ID_PERBICARAAN ='"+idPerbicaraan+"'";
				ResultSet rs = stmtN.executeQuery(sql);
				int i = 0;
				if (rs.next()) {
					h = new Hashtable();
					h.put("USER_ID_PEG",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
					validPegPengendali.addElement(h);
				}else{		
					sql2 = " SELECT A.USER_ID " +
						" FROM TBLPPKRUJUNIT A, TBLPPKPERBICARAAN B " +
						" WHERE A.ID_UNITPSK = B.ID_UNITPSK " +
						" AND A.NAMA_PEGAWAI = '"+username+"' " +
						" AND B.ID_PERBICARAAN ='"+idPerbicaraan+"' ";
						rs = stmtN.executeQuery(sql2);
						System.out.println("sql2b==="+sql2);
						if (rs.next()) {
							h = new Hashtable();
							h.put("USER_ID_PEG",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
							validPegPengendali.addElement(h);
						}
				}
			} finally {
				if (db != null)
					db.close();
			}
		}
}