package ekptg.model.ppk;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;
//import org.apache.xalan.xsltc.runtime.Hashtable;

public class FrmSenaraiFailOnlineData {
	private static Logger myLogger = Logger.getLogger(FrmSenaraiFailOnlineData.class);
	private  SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private  Vector list = new Vector();
	private  Vector list_online = new Vector();
	private  Vector listOnlineUtiliti = new Vector();
	
	public static Vector setListOnlineUtilitiByUnit(String userid,String no_fail,String myid,String seksyen,String cari_nama, String nama_pejabat) throws Exception {
		Db db = null;
		Vector listOnlineUtiliti = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			listOnlineUtiliti = new Vector();
			Statement stmt = db.getStatement();
			
			boolean setLimit = true;
			
			sql = "SELECT A.ID_PERMOHONANTERDAHULU,F.ID_FAIL, UPPER(F.NO_FAIL) AS NO_FAIL, A.ID_PERMOHONAN, " +
					" A.TARIKH_MOHON, A.TARIKH_MASUK, " +
				" F.TARIKH_DAFTAR_FAIL, S.KETERANGAN, P.ID_SIMATI, UPPER(P.NAMA_SIMATI) AS NAMA_SIMATI, "+
				" A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, UPPER(A.NO_PERMOHONAN_ONLINE) AS NO_PERMOHONAN_ONLINE," +
				" UPPER(PM.NAMA_PEMOHON) AS NAMA_PEMOHON, " +
				" PM.NO_KP_BARU, PM.ID_PEMOHON, "+
				" P.NO_KP_BARU, P.NO_KP_BARU,A.SEKSYEN,A.NO_SUBJAKET,E.NAMA_PEJABAT "+
				" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, " +
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJDAERAH D,TBLRUJPEJABATJKPTG E,TBLRUJPEJABATURUSAN F,TBLRUJNEGERI N "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR " +
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG  "+
				" AND UR.USER_ID='"+userid+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
				
				sql+=" ) "+
				" AND N.ID_NEGERI IN ( SELECT DISTINCT U.ID_NEGERIURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
				" WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG "+
                " AND UR.USER_ID = '"+userid+"') "+
                " AND E.ID_PEJABATJKPTG = F.ID_PEJABATJKPTG "+
                " AND D.ID_DAERAH = F.ID_DAERAHURUS "+
                " AND N.ID_NEGERI = F.ID_NEGERIURUS "+
                " AND E.ID_NEGERI = F.ID_NEGERIURUS "+
                " AND E.ID_JENISPEJABAT = 21 " +
				" AND A.ID_STATUS = S.ID_STATUS(+) "+				
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN  AND P.ID_SIMATI = MS.ID_SIMATI "+
				" AND A.ID_STATUS <> '999' "+
				" AND A.NO_PERMOHONAN_ONLINE IS NOT NULL ";
			
			if(!cari_nama.equals(""))
			{
				sql +=  " AND ( " +
						" UPPER(P.NAMA_SIMATI) LIKE '%' ||'"+cari_nama.toUpperCase()+"'|| '%'  OR" +
						" UPPER(PM.NAMA_PEMOHON) LIKE '%' ||'"+cari_nama.toUpperCase()+"'|| '%' " +
								")";
			
				setLimit = false;
			}
			
			if(!seksyen.equals(""))
			{
				sql +=  " AND A.SEKSYEN IN ('"+seksyen+"')";
				setLimit = false;
			}
			else
			{
				sql +=  " AND A.SEKSYEN IN ('8','17')";	
			}
			
					
			if(!no_fail.equals(""))
			{
				sql +=  " AND ( UPPER(TRIM(F.NO_FAIL)) LIKE '%' ||'"+no_fail.trim()+"'|| '%' OR UPPER(TRIM(A.NO_PERMOHONAN_ONLINE)) LIKE '%' ||'"+no_fail.trim()+"'|| '%')";
				setLimit = false;
			}
			
			if(!myid.equals(""))
			{
				sql +=  " AND ( UPPER(TRIM(P.NO_KP_BARU)) LIKE '%' ||'"+myid.trim()+"'|| '%' OR UPPER(TRIM(P.NO_KP_LAMA)) LIKE '%' ||'"+myid.trim()+"'|| '%' OR UPPER(TRIM(P.NO_KP_LAIN)) LIKE '%' ||'"+myid.trim()+"'|| '%'" +
						"OR UPPER(TRIM(PM.NO_KP_BARU)) LIKE '%' ||'"+myid.trim()+"'|| '%' OR UPPER(TRIM(PM.NO_KP_LAMA)) LIKE '%' ||'"+myid.trim()+"'|| '%' OR UPPER(TRIM(PM.NO_KP_LAIN)) LIKE '%' ||'"+myid.trim()+"'|| '%')";
				setLimit = false;
			}
			
			if(!nama_pejabat.equals(""))
			{
				sql +=  " AND E.ID_PEJABATJKPTG IN ('"+nama_pejabat+"')";
								
				setLimit = false;
			}
			
			
			
			
			//setLimit = false;			
			if (setLimit) {					  
				sql = sql + " AND ROWNUM <= 50 ";
		    }	
		    sql = sql + " ORDER BY A.TARIKH_MOHON DESC";
			
			//myLogger.info("SQL ONLINE UTILITI "+sql.toUpperCase());
			
			//stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("SQL ONLINE UTILITI "+sql);
			
			Hashtable h = null;
			int bil = 0;
			
			while (rs.next()){
				myLogger.info("bil--"+bil);
				h = new Hashtable();
				bil++;
				h.put("bil", bil);				
				h.put("id_Permohonan", rs.getString("ID_PERMOHONAN"));			
				h.put("id_Fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("id_Pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
				h.put("id_Simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("no_Fail", rs.getString("NO_FAIL")==null?"-":rs.getString("NO_FAIL"));
				h.put("noonline", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhmohononline", rs.getDate("TARIKH_MOHON_ONLINE")==null?"":sdf.format(rs.getDate("TARIKH_MOHON_ONLINE")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
			//	h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			//	h.put("id_simati", rs.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("namapemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nokppemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("daerahmohon", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));				
				h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
				h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
				h.put("id_FailDulu", rs.getString("ID_PERMOHONANTERDAHULU")==null?"":rs.getString("ID_PERMOHONANTERDAHULU"));
				h.put("nama_pejabat", rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT"));
				
				
				myLogger.info("list_online"+h);
				listOnlineUtiliti.addElement(h);
					
			}
			
			//System.out.println("BIL BIL::"+bil);
			return listOnlineUtiliti;
		}finally {
			if(db != null) db.close();
			
		}
		}
	public static Vector setListOnlineUtiliti(String tarikhMula, String tarikhAkhir,String userid,String no_fail,String myid,String seksyen,String cari_nama) throws Exception {
		Db db = null;
		Vector list_online = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			list_online = new Vector();
			Statement stmt = db.getStatement();
			
			boolean setLimit = true;
			
			sql = "SELECT A.ID_PERMOHONANTERDAHULU,F.ID_FAIL, UPPER(F.NO_FAIL) AS NO_FAIL, A.ID_PERMOHONAN, " +
					" A.TARIKH_MOHON, A.TARIKH_MASUK, " +
				" F.TARIKH_DAFTAR_FAIL, S.KETERANGAN, P.ID_SIMATI, UPPER(P.NAMA_SIMATI) AS NAMA_SIMATI, "+
				" A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, UPPER(A.NO_PERMOHONAN_ONLINE) AS NO_PERMOHONAN_ONLINE," +
				" UPPER(PM.NAMA_PEMOHON) AS NAMA_PEMOHON, " +
				" PM.NO_KP_BARU, PM.ID_PEMOHON, "+
				" P.NO_KP_BARU, P.NO_KP_BARU,A.SEKSYEN,A.NO_SUBJAKET, "+
				
				/** ADD BY PEJE */
				" A.ID_STATUS, MS.ID_PERMOHONANSIMATI, F.FLAG_JENIS_FAIL "+
				
				" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, " +
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJDAERAH D "+
				" ,USERS U, USERS_INTERNAL UI "+
				" WHERE " +
				" U.USER_ID = UI.USER_ID  AND U.USER_ID = '"+userid+"' AND (UI.ID_NEGERI = 16 OR (UI.ID_NEGERI != 16 AND "+
				" D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR " +
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG  "+
				" AND UR.USER_ID='"+userid+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
				
				sql += " ) "+
				"  )) "+
				" AND A.ID_STATUS = S.ID_STATUS(+) "+				
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN  AND P.ID_SIMATI = MS.ID_SIMATI "+
				" AND A.ID_STATUS <> '999' "+
				" AND A.NO_PERMOHONAN_ONLINE IS NOT NULL ";
				
				
				if(!tarikhMula.equals("") && !tarikhAkhir.equals(""))
				{
				sql += " AND A.TARIKH_MOHON_ONLINE BETWEEN TO_DATE('"+tarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+tarikhAkhir+"','DD/MM/YYYY') ";
				}
				if(!tarikhMula.equals("") && tarikhAkhir.equals(""))
				{
				sql += " AND A.TARIKH_MOHON_ONLINE > TO_DATE('"+tarikhAkhir+"','DD/MM/YYYY') ";
				}
				if(tarikhMula.equals("") && !tarikhAkhir.equals(""))
				{
				sql += " AND A.TARIKH_MOHON_ONLINE < TO_DATE('"+tarikhAkhir+"','DD/MM/YYYY') ";
				}
				
			
			if(!cari_nama.equals(""))
			{
				sql +=  " AND ( " +
						" UPPER(P.NAMA_SIMATI) LIKE '%' ||'"+cari_nama.toUpperCase()+"'|| '%'  OR" +
						" UPPER(PM.NAMA_PEMOHON) LIKE '%' ||'"+cari_nama.toUpperCase()+"'|| '%' " +
								")";
			
				setLimit = false;
			}
			
			if(!seksyen.equals(""))
			{
				sql +=  " AND A.SEKSYEN IN ('"+seksyen+"')";
				setLimit = false;
			}
			else
			{
				sql +=  " AND A.SEKSYEN IN ('8','17')";	
			}
			
			
			if(!no_fail.equals(""))
			{
				sql +=  " AND ( UPPER(TRIM(F.NO_FAIL)) LIKE '%' ||'"+no_fail.trim()+"'|| '%' OR UPPER(TRIM(A.NO_PERMOHONAN_ONLINE)) LIKE '%' ||'"+no_fail.trim()+"'|| '%')";
				setLimit = false;
			}
			
			if(!myid.equals(""))
			{
				sql +=  " AND ( UPPER(TRIM(P.NO_KP_BARU)) LIKE '%' ||'"+myid.trim()+"'|| '%' OR UPPER(TRIM(P.NO_KP_LAMA)) LIKE '%' ||'"+myid.trim()+"'|| '%' OR UPPER(TRIM(P.NO_KP_LAIN)) LIKE '%' ||'"+myid.trim()+"'|| '%'" +
						"OR UPPER(TRIM(PM.NO_KP_BARU)) LIKE '%' ||'"+myid.trim()+"'|| '%' OR UPPER(TRIM(PM.NO_KP_LAMA)) LIKE '%' ||'"+myid.trim()+"'|| '%' OR UPPER(TRIM(PM.NO_KP_LAIN)) LIKE '%' ||'"+myid.trim()+"'|| '%')";
				setLimit = false;
			}
			
			
			
			
			
			//setLimit = false;			
//			if (setLimit) {					  
//				sql = sql + " AND ROWNUM <= 50 ";
//		    }	
		    sql = sql + " ORDER BY A.TARIKH_MOHON DESC";
			
			myLogger.info("SQL XXXX ONLINE UTILITI"+sql.toUpperCase());
			System.out.println("SQL XXXX ONLINE UTILITI"+sql.toUpperCase());
			
			//stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h = null;
			int bil = 0;
			
			while (rs.next()){
				h = new Hashtable();
				bil++;
				h.put("bil", bil);				
				h.put("id_Permohonan", rs.getString("ID_PERMOHONAN"));			
				h.put("id_Fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("id_Pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
				h.put("id_Simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("no_Fail", rs.getString("NO_FAIL")==null?"-":rs.getString("NO_FAIL"));
				h.put("noonline", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhmohononline", rs.getDate("TARIKH_MOHON_ONLINE")==null?"":sdf.format(rs.getDate("TARIKH_MOHON_ONLINE")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
			//	h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			//	h.put("id_simati", rs.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("namapemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nokppemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("daerahmohon", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));				
				h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
				h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
				h.put("id_FailDulu", rs.getString("ID_PERMOHONANTERDAHULU")==null?"":rs.getString("ID_PERMOHONANTERDAHULU"));
				
				/** ADD BY PEJE */
				h.put("idStatus", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("idPermohonansimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
				h.put("flagJenisFail", rs.getString("FLAG_JENIS_FAIL")==null?"":rs.getString("FLAG_JENIS_FAIL"));
				
				
				
				list_online.addElement(h);
					
			}
			
			//System.out.println("BIL BIL::"+bil);
			return list_online;
		}finally {
			if(db != null) db.close();
			
		}
	
	}
	public static Vector setList(String userid) throws Exception {
		Db db = null;		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL," 
				+ " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON," 
                + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU,A.SEKSYEN,A.NO_SUBJAKET" 
                +" ,( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) HARI, docSimati.id_dokumen, docSimati.id_jenisdokumen"                 
                +" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
                +" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, tblppkdokumensimati docSimati, TBLRUJDAERAH D" 
                +" WHERE" 
                +" D.id_daerah in ( select distinct u.id_daerahurus from"
                +" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userid+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
				
				sql += " )"
                +" AND ST.ID_STATUS = S.ID_STATUS(+)"
                +" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 	
                +" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
                +" AND A.ID_FAIL = F.ID_FAIL(+)" 
                +" AND A.ID_DAERAHMHN = D.ID_DAERAH"
                +" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
                +" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
                +" AND P.ID_SIMATI = MS.ID_SIMATI" 
                +" AND p.id_simati = docSimati.id_simati(+) "
                +" AND A.ID_STATUS <> '999'"
                +" AND STA.ID_SUBURUSANSTATUS = 614" 
                +" AND A.SEKSYEN = 8"  
                +" AND STA.AKTIF = 1" 
                +" AND A.FLAG_JENIS_PERMOHONAN = 0" 
                +" AND (docsimati.id_jenisdokumen IS NULL OR docsimati.id_jenisdokumen = 99211)"
               // +" AND ( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) <= 21 " 
                //+" ORDER BY STA.ID_SUBURUSANSTATUSFAIL"
                +" ORDER BY A.TARIKH_MOHON_ONLINE DESC"
                +"";
			
			myLogger.info("SQL XXXX ONLINE kurang"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			
			Hashtable h = null;
			int bil = 0;
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);	
				h.put("id_Permohonan", rs.getString("ID_PERMOHONAN"));			
				h.put("id_Fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("id_Pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
				h.put("id_Simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("no_Fail", rs.getString("NO_FAIL")==null?"-":rs.getString("NO_FAIL"));
				h.put("noonline", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhmohononline", rs.getDate("TARIKH_MOHON_ONLINE")==null?"":sdf.format(rs.getDate("TARIKH_MOHON_ONLINE")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
			//	h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			//	h.put("id_simati", rs.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("namapemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nokppemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("daerahmohon", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));				
				h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
				h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
				h.put("id_dokumen", rs.getString("ID_DOKUMEN")==null?"":rs.getString("ID_DOKUMEN"));
				h.put("id_jenisdokumen", rs.getString("ID_JENISDOKUMEN")==null?"":rs.getString("ID_JENISDOKUMEN"));
				list.addElement(h);
				bil++;	
			
			}			
			//System.out.println("BIL BIL::"+bil);
			return list;

		}finally {
			if(db != null) db.close();
			
		}
	
	}
	/**
	 * Senarai permohonan online (keseluruhan)
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	
	
	public Vector getListxx(String userid, Integer rownum_mula, Integer rownum_akhir) throws Exception {
		Db db = null;		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector senarai = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql += " SELECT * FROM ( ";
			sql += "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL," 
				+ " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON," 
                + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU,A.SEKSYEN,A.NO_SUBJAKET" 
                +" ,( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) HARI"                 
                +" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
                +" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D" 
                +" WHERE" 
                +" D.id_daerah in ( select distinct u.id_daerahurus from"
                +" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userid+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
				
				sql += " )"
                +" AND ST.ID_STATUS = S.ID_STATUS(+)"
                +" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 	
                +" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
                +" AND A.ID_FAIL = F.ID_FAIL(+)" 
                +" AND A.ID_DAERAHMHN = D.ID_DAERAH"
                +" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
                +" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
                +" AND P.ID_SIMATI = MS.ID_SIMATI" 
                +" AND A.ID_STATUS <> '999'"
                +" AND STA.ID_SUBURUSANSTATUS = 614" 
                +" AND A.SEKSYEN = 8"  
                +" AND STA.AKTIF = 1" 
                +" AND A.FLAG_JENIS_PERMOHONAN = 0" 
               // +" AND ( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) <= 21 " 
                +" ORDER BY STA.ID_SUBURUSANSTATUSFAIL"
                +"";
				sql += ") MAIN ROWNUM >= "+rownum_mula+" AND ROWNUM <= "+rownum_akhir;
			
			
			
			myLogger.info("SENARAI ONLINE NEW : "+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			senarai = new Vector();
			
			Hashtable h = null;
			int bil = 0;
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);	
				h.put("id_Permohonan", rs.getString("ID_PERMOHONAN"));			
				h.put("id_Fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("id_Pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
				h.put("id_Simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("no_Fail", rs.getString("NO_FAIL")==null?"-":rs.getString("NO_FAIL"));
				h.put("noonline", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhmohononline", rs.getDate("TARIKH_MOHON_ONLINE")==null?"":sdf.format(rs.getDate("TARIKH_MOHON_ONLINE")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
			//	h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			//	h.put("id_simati", rs.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("namapemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nokppemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("daerahmohon", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));				
				h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
				h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
				senarai.addElement(h);
				bil++;	
			
			}			
			//System.out.println("BIL BIL::"+bil);
			return senarai;

		}finally {
			if(db != null) db.close();
			
		}
	
	}
	
	
	public Vector getList(String userid) throws Exception {
		Db db = null;		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector senarai = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL," 
				+ " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON," 
                + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU,A.SEKSYEN,A.NO_SUBJAKET" 
                +" ,( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) HARI"                 
                +" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
                +" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D" 
                +" WHERE" 
                +" D.id_daerah in ( select distinct u.id_daerahurus from"
                +" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userid+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
				
				sql += " )"
                +" AND ST.ID_STATUS = S.ID_STATUS(+)"
                +" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 	
                +" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
                +" AND A.ID_FAIL = F.ID_FAIL(+)" 
                +" AND A.ID_DAERAHMHN = D.ID_DAERAH"
                +" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
                +" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
                +" AND P.ID_SIMATI = MS.ID_SIMATI" 
                +" AND A.ID_STATUS <> '999'"
                +" AND STA.ID_SUBURUSANSTATUS = 614" 
                +" AND A.SEKSYEN = 8"  
                +" AND STA.AKTIF = 1" 
                +" AND A.FLAG_JENIS_PERMOHONAN = 0" 
               // +" AND ( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) <= 21 " 
                +" ORDER BY STA.ID_SUBURUSANSTATUSFAIL"
                +"";
			
			
			
			myLogger.info("SQL XXXX ONLINE"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			senarai = new Vector();
			
			Hashtable h = null;
			int bil = 0;
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);	
				h.put("id_Permohonan", rs.getString("ID_PERMOHONAN"));			
				h.put("id_Fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("id_Pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
				h.put("id_Simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("no_Fail", rs.getString("NO_FAIL")==null?"-":rs.getString("NO_FAIL"));
				h.put("noonline", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhmohononline", rs.getDate("TARIKH_MOHON_ONLINE")==null?"":sdf.format(rs.getDate("TARIKH_MOHON_ONLINE")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
			//	h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			//	h.put("id_simati", rs.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("namapemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nokppemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("daerahmohon", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));				
				h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
				h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
				senarai.addElement(h);
				bil++;	
			
			}			
			//System.out.println("BIL BIL::"+bil);
			return senarai;

		}finally {
			if(db != null) db.close();
			
		}
	
	}
 	
	public static Vector setDataPermohonanDulu(String id_dulu) throws Exception{
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT P.ID_PERMOHONAN, F.NO_FAIL, F.ID_FAIL, P.NO_SUBJAKET "
			+" FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F "
			+" WHERE P.ID_FAIL = F.ID_FAIL " 
			+" AND P.ID_FAIL IS NOT NULL "
			+" AND P.ID_STATUS <> '999'"
			+" AND P.ID_PERMOHONAN = '"+id_dulu+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Vector PermohonanDulu = new Vector();
			Hashtable h = null;
			while (rs.next()){
				h = new Hashtable();
				h.put("idPermohonanLama", rs.getString("ID_PERMOHONAN"));			
				h.put("idFailLama", rs.getString("ID_FAIL"));
				h.put("noFailLama", rs.getString("NO_FAIL"));
				h.put("no_subjaket", rs.getString("NO_SUBJAKET"));
				h.put("noFail", rs.getString("no_Fail") == null ? "" : rs
						.getString("no_Fail"));

				
				PermohonanDulu.addElement(h);
			}
		
			return PermohonanDulu;	
		}
		finally {
			if(db != null) db.close();
			
		}
		
		
	}
	
	
	public static Vector setList17(String userid) throws Exception {
		Db db = null;		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			
			sql="SELECT A.NO_PERMOHONAN_ONLINE,F.ID_FAIL,F_D.ID_FAIL AS ID_FAILDULU,A.ID_PERMOHONAN,A.ID_PERMOHONANTERDAHULU, F.NO_FAIL,  A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL, S.KETERANGAN, " 
				+" P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE,  PM.NAMA_PEMOHON, PM.NO_KP_BARU, "
				+" PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU,A.SEKSYEN,A.NO_SUBJAKET FROM TBLPPKPERMOHONAN A,TBLPPKPERMOHONAN A_D, TBLPFDFAIL F, TBLPFDFAIL F_D, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
				+" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
				+" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
				+" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userid+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
				
				sql+= " ) AND ST.ID_STATUS = S.ID_STATUS(+) "
				+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
				+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) "
				+" AND A.ID_FAIL = F.ID_FAIL(+) "
				+" AND A.ID_DAERAHMHN = D.ID_DAERAH "
				+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN "
				+" AND A.ID_PERMOHONANTERDAHULU = A_D.ID_PERMOHONAN "
				+" AND A_D.ID_FAIL = F_D.ID_FAIL(+) "
				+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN "
				+" AND P.ID_SIMATI = MS.ID_SIMATI AND A.ID_STATUS <> '999' "
				+" AND A.SEKSYEN = 17 "
				+" AND STA.AKTIF = 1 "
				 +" AND A.FLAG_JENIS_PERMOHONAN = 0" 
				// +" AND STA.ID_SUBURUSANSTATUS = 615" 
				+" AND A.ID_PERMOHONANTERDAHULU IS NOT NULL ORDER BY STA.ID_SUBURUSANSTATUSFAIL";
			
			
		//	System.out.print("SQL ONLINE 17::"+sql.toUpperCase());
			myLogger.info("SQL ONLINE 17::"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Vector list = new Vector();
			
			Hashtable h = null;
			int bil = 0;
			
			while (rs.next()){
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("noonline", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("id_Permohonan", rs.getString("ID_PERMOHONAN"));			
				h.put("id_Fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("id_FailDulu", rs.getString("ID_FAILDULU")==null?"":rs.getString("ID_FAILDULU"));

				h.put("id_Pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
				h.put("id_Simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("no_Fail", rs.getString("NO_FAIL")==null?"-":rs.getString("NO_FAIL"));
				h.put("noonline", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhmohononline", rs.getDate("TARIKH_MOHON_ONLINE")==null?"":sdf.format(rs.getDate("TARIKH_MOHON_ONLINE")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_MASUK")));
			//	h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			//	h.put("id_simati", rs.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("namapemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
				h.put("nokppemohon", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
				h.put("daerahmohon", rs.getString("ID_DAERAHMHN")==null?"":rs.getString("ID_DAERAHMHN"));
				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
				h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
								
				
				
				list.addElement(h);
				bil++;	
			}
			
			//System.out.println("BIL BIL::"+bil);
			return list;
		}finally {
			if(db != null) db.close();
			
		}
		}
	
	
	public  Vector getList(){
		return list;
	}

	public  boolean checkKPSimati(String idp,String kpbaru, String kplama, String kplain) throws Exception {
		Db db = null;
		boolean a = false;
		String jumlah_baru = "0";
		String jumlah_lama = "0";
		String jumlah_lain = "0";
		
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			
			if(kpbaru!="")
			{
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();		
			sql =" SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_BARU "
			+" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
			+" WHERE SM.ID_SIMATI = MS.ID_SIMATI"
			+" AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
			+" AND P.ID_PERMOHONAN <> '"+idp+"'"
			+" AND SM.NO_KP_BARU = '"+kpbaru+"'"
			+" AND P.ID_STATUS <> '999'"
			+" AND P.ID_STATUS <> '169'";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			/*
			while (rs.next())
			{	
				jumlah_baru = rs.getString("JUMLAH_BARU")==null?"":rs.getString("JUMLAH_BARU");	
			}
			*/				
			  if (rs.next()) {
                  if (rs.getInt("JUMLAH_BARU") > 0) {
                        a = true;
                  }
              }			  
			}  
			
			if(kplama!="" && !kplama.equals("TDK"))
			{
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();		
			sql1 =" SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAMA "
			+" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
			+" WHERE SM.ID_SIMATI = MS.ID_SIMATI"
			+" AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
			+" AND P.ID_PERMOHONAN <> '"+idp+"'"
			+" AND SM.NO_KP_LAMA = '"+kplama+"'"
			+" AND P.ID_STATUS <> '999'"
			+" AND P.ID_STATUS <> '169'";
			ResultSet rs1 = stmt1.executeQuery(sql1);
			Hashtable h1;
			/*
			while (rs1.next())
			{	
				jumlah_lama = rs1.getString("JUMLAH_LAMA")==null?"":rs1.getString("JUMLAH_LAMA");	
			}
			*/
			 if (rs1.next()) {
                 if (rs1.getInt("JUMLAH_LAMA") > 0) {
                       a = true;
                 }
             }		
			
			}
			
			if(kplain!="")
			{
			Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();		
			sql2 =" SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAIN "
			+" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
			+" WHERE SM.ID_SIMATI = MS.ID_SIMATI"
			+" AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
			+" AND P.ID_PERMOHONAN <> '"+idp+"'"
			+" AND SM.NO_KP_LAIN = '"+kplain+"'"
			+" AND P.ID_STATUS <> '999'"
			+" AND P.ID_STATUS <> '169'";
			ResultSet rs2 = stmt2.executeQuery(sql2);
			Hashtable h2;
			/*
			while (rs2.next())
			{	
				jumlah_lain = rs2.getString("JUMLAH_LAIN")==null?"":rs2.getString("JUMLAH_LAIN");	
			}	
			*/
			if (rs2.next()) {
                if (rs2.getInt("JUMLAH_LAIN") > 0) {
                      a = true;
                }
            }	
			}
			
			/*
			if(Integer.parseInt(jumlah_baru)>0 || Integer.parseInt(jumlah_lama)>0 || Integer.parseInt(jumlah_lain)>0 )
			{
				a = true;
			}
			*/
			
			
		}finally {
			if(db != null) db.close();
			
		}
	    return false;
		}

}
