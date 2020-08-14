/**
 * 
 */
package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmSenaraiPermohonanSeksyen8Data;


/**
 * 
 *
 */

public class FrmSenaraiBorangB extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmSenaraiBorangB.class);

	FrmSenaraiPermohonanSeksyen8Data logic = null;
	//Vector list = null;
	List list = null;

	@Override
	public String doTemplate2() throws Exception {
		logic = new FrmSenaraiPermohonanSeksyen8Data();
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		
		String vm = ""; 
		String flagAdvSearch = getParam("flagAdvSearch");
		String action = getParam("action"); // Second Level
		
		list = Collections.synchronizedList(new ArrayList());
		
		String command = getParam("command");		
		String myIdSimati = getParam("myIdSimati");
		String noFail = getParam("noFail");
		
		vm = "app/ppk/frmSenaraiBorangB.jsp";
		
	
		list = getListBorangB(userId,noFail.trim(),myIdSimati.trim());		
		this.context.put("SenaraiFail", list);		
		
		this.context.put("myIdSimati", myIdSimati.trim());
		this.context.put("noFail", noFail.trim());

		
		setupPage(session,action,list);
		
		return vm;
	}
	
	Vector getListBorangB = new Vector();
	public Vector getListBorangB(String userId,String noFail,String myIdSimati) throws Exception {
		Db db = null;
		getListBorangB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			sql = " SELECT TO_CHAR(TO_DATE(PPP.TARIKH_MOHON,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_MOHON," +
					" SSM.ID_PERMOHONANSIMATI,M.NAMA_SIMATI,PPP.SEKSYEN,SSM.ID_SIMATI,PPP.ID_PERMOHONAN,FFF.ID_FAIL,FFF.NO_FAIL,TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') AS CURRENT_DATE, "+
				" (SELECT TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "+
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "+
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' "+
				" AND  FFF.ID_FAIL = FF.ID_FAIL"+
				" AND STA.TARIKH_MASUK = ( "+
				" SELECT MAX(STA.TARIKH_MASUK) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"+ 
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"+ 
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' AND F.ID_FAIL = FF.ID_FAIL"+
				" )) AS TARIKH_BORANGB,"+
				" (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -"+ 
				" (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"+ 
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"+ 
				" AND A.ID_STATUS <> '999'"+ 
				" AND S.ID_STATUS = '170' "+
				" AND  FFF.ID_FAIL = FF.ID_FAIL)) AS DIFF"+
				" FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF,TBLPPKPERMOHONANSIMATI SSM,TBLPPKSIMATI M"+
				" WHERE SSM.ID_SIMATI = M.ID_SIMATI AND PPP.ID_FAIL = FFF.ID_FAIL AND PPP.ID_PERMOHONAN = SSM.ID_PERMOHONAN AND"+
				" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"+ 
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"+ 
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND"+
				" ("+
				" SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"+ 
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"+ 
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL"+
				" ) > 0"+
				" AND"+
				" (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -"+ 
				" (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' "+
				" AND  FFF.ID_FAIL = FF.ID_FAIL))>30 ";
				*/
			
			//dapat flag
			boolean setLimit = true;
			String sql_filter = "";
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql_filter = sql_filter + " AND ( UPPER(FF.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%' ) " ;
				}
			}
			
			if (myIdSimati != null) {
				if (!myIdSimati.trim().equals("")) {
					setLimit = false;
					
					sql_filter = sql_filter + " AND ( UPPER(P.NO_KP_BARU) LIKE '%' ||'" + myIdSimati.toUpperCase() + "'|| '%' ";
				
					
					sql_filter = sql + " OR UPPER(P.NO_KP_LAMA) LIKE '%' ||'" + myIdSimati.toUpperCase() + "'|| '%' ";
					
					
					sql_filter = sql_filter + " OR UPPER(P.NO_KP_LAIN) LIKE '%' ||'" + myIdSimati.toUpperCase() + "'|| '%' )";
					
				}
			}
			
			if (setLimit) {	//RESERVED BY AZAM
				sql_filter = sql_filter + " AND ROWNUM <= 50 ";
			}
			
			
			sql = " SELECT DISTINCT TO_CHAR (A.TARIKH_MOHON, 'DD/MM/YYYY') AS TARIKH_MOHON, "+
					" MS.ID_PERMOHONANSIMATI, P.NAMA_SIMATI, A.SEKSYEN, MS.ID_SIMATI,A.ID_PERMOHONAN,  "+
					" FF.ID_FAIL, FF.NO_FAIL,TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'),'DD/MM/YYYY') AS CURRENT_DATE,  "+
					" TO_CHAR (MAX(STA.TARIKH_MASUK), 'DD/MM/YYYY') AS TARIKH_BORANGB "+
					" FROM TBLPPKPERMOHONAN A,TBLPFDFAIL FF,TBLPPKSIMATI P,TBLPPKPERMOHONANSIMATI MS,  "+
					" TBLRUJSUBURUSANSTATUS ST,TBLRUJSUBURUSANSTATUSFAIL STA, "+
					" (SELECT DISTINCT ID_FAIL FROM ( SELECT (CASE WHEN SUM(TABLE_BB.COUNT_LEBIH_30) > 0  "+
					" AND  SUM(TABLE_BB.COUNT_KEPUTUSAN_PERMOHONAN) = 0 THEN TABLE_BB.ID_FAIL END) AS ID_FAIL "+
					" FROM (SELECT DISTINCT FF.ID_FAIL, (CASE WHEN  ST.ID_STATUS = 170  "+
					" AND (SYSDATE -  MAX (TO_DATE (TO_CHAR (STA.TARIKH_MASUK,'DD/MM/YYYY'), 'DD/MM/YYYY')) > 30) "+
					" THEN 1 END) AS  COUNT_LEBIH_30, COUNT((CASE WHEN ST.ID_STATUS = 14 THEN 1 END )) AS COUNT_KEPUTUSAN_PERMOHONAN "+
					" FROM TBLPPKPERMOHONAN A,TBLPFDFAIL FF,TBLRUJSUBURUSANSTATUS ST,TBLRUJSUBURUSANSTATUSFAIL STA WHERE A.ID_DAERAHMHN IN ( "+
					" SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG "+
					" AND UR.USER_ID = '"+userId+"' ";
					
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					
					sql += " ) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS "+
					" AND A.ID_FAIL = FF.ID_FAIL AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN "+
					" AND A.ID_STATUS <> '999' GROUP BY FF.ID_FAIL,ST.ID_STATUS          "+            
					" ) TABLE_BB  GROUP BY TABLE_BB.ID_FAIL) "+
					" UTAMA WHERE UTAMA.ID_FAIL IS NOT NULL) TEMP_TABLE_B "+
					" WHERE A.ID_DAERAHMHN IN ( SELECT DISTINCT U.ID_DAERAHURUS "+
					" FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
					" WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG "+
					" AND UR.USER_ID = '"+userId+"' ";
					
					sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql += " ) AND A.ID_FAIL = FF.ID_FAIL "+
					" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
					" AND A.TARIKH_MOHON IS NOT NULL AND STA.ID_FAIL = FF.ID_FAIL AND ST.ID_SUBURUSANSTATUS = STA.ID_SUBURUSANSTATUS  "+
					" AND ST.ID_STATUS = 170 AND TEMP_TABLE_B.ID_FAIL = FF.ID_FAIL "+
					sql_filter +
					" GROUP BY A.TARIKH_MOHON,MS.ID_PERMOHONANSIMATI,P.NAMA_SIMATI, A.SEKSYEN, MS.ID_SIMATI, "+
					" A.ID_PERMOHONAN, FF.ID_FAIL, FF.NO_FAIL ";
			
			
			
			
			
			myLogger.info("GET LIST FAIL BORANG B"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("ID_SIMATI", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("NO_FAIL", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("SEKSYEN", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
				h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON")==null?"":rs.getString("TARIKH_MOHON"));
				getListBorangB.addElement(h);
			}
			return getListBorangB;
		}finally {
			if(db != null) db.close();
		}
		}
	



}
