package ekptg.model.ppk;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmEditPerintah {
	static Logger myLogger = Logger.getLogger(FrmHeaderPpk.class);	
	

@SuppressWarnings("unchecked")
	public List listKemaskiniPerintah(HttpSession session, String nokp, String noFail)throws Exception {
	
	String user_id = (String)session.getAttribute("_ekptg_user_id");
	
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listModule = null;
		String sql = "";
		String condition = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
				condition += "AND ( trim(F.NO_FAIL) = LIKE '%"+noFail.toUpperCase()+"%' )" ;	
				}
			}	
			if (nokp != null) {
				if (!nokp.trim().equals("")) {
				condition += " AND trim(S.NO_KP_BARU) LIKE '%"+nokp.toUpperCase()+"%' " +
							 " OR trim(S.NO_KP_LAMA) LIKE '%"+nokp.toUpperCase()+"%' " +
							 " OR trim(S.NO_KP_LAIN) LIKE '%"+nokp.toUpperCase()+"%' " ;
				}
			}	
			
			sql = " SELECT ST.KETERANGAN AS NAMA_STATUS,F.ID_FAIL,F.NO_FAIL,P.ID_PERMOHONAN, " +
				  " TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON,S.NAMA_SIMATI,PM.NAMA_PEMOHON , " +
				  " P.USER_ID_KEBENARAN_EDIT, P.FLAG_KEBENARAN_EDIT " +
				  " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI S,TBLPPKPEMOHON PM," +
				  " TBLRUJSTATUS ST " +
				  " WHERE F.ID_FAIL = P.ID_FAIL  AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN  " +	
				  " AND P.FLAG_KEBENARAN_EDIT = 1" +
				  " AND S.ID_SIMATI = PS.ID_SIMATI  AND P.ID_PEMOHON = PM.ID_PEMOHON  " +
				  " AND P.ID_STATUS = ST.ID_STATUS(+)  " +
				  condition +
				  " AND P.ID_DAERAHMHN in (  select distinct u.id_daerahurus from  TBLRUJPEJABATURUSAN u, users_internal ur  " +
				  " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id= '"+user_id+"' ";
				  
				  sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_id+"  ";
				  
				 sql += " ) " +
				  " ORDER BY P.TARIKH_MOHON ";
			
			
			myLogger.info(" mm: SQL listGroupModule :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listModule = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
		
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
				h.put("NAMA_STATUS",rs.getString("NAMA_STATUS") == null ? "" : rs.getString("NAMA_STATUS"));	
				h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("TARIKH_MOHON",rs.getString("TARIKH_MOHON") == null ? "" : rs.getString("TARIKH_MOHON"));
				h.put("NAMA_SIMATI",rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("NAMA_PEMOHON",rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				h.put("USER_ID_KEBENARAN_EDIT",rs.getString("USER_ID_KEBENARAN_EDIT") == null ? "" : rs.getInt("USER_ID_KEBENARAN_EDIT"));
				listModule.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listModule;

	}
}