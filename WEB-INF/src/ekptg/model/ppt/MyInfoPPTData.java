/**
 * 
 */
package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;

/**
 * @author Razman/Elly/Syazreen
  */

public class MyInfoPPTData extends EkptgCache {
	
	static Logger myLogger = Logger.getLogger(MyInfoPPTData.class);	
	
	private static Vector dataCollection = null;
	
	public Vector getCollectionData(){
		return dataCollection;
	}
	
	Vector listing_online_permohonan = null;
	@SuppressWarnings("unchecked")
	public Vector listing_online_permohonan(HttpSession session) throws Exception {
		listing_online_permohonan = new Vector();
		Db db = null;
		listing_online_permohonan.clear();
		
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			   sql = " SELECT "
					+" F.ID_SUBURUSAN,P.NO_PERMOHONAN_ONLINE,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN_KJP,'DD.MM.YYYY') AS TARIKH_PERMOHONAN_KJP,S.KETERANGAN AS STATUS, "
					+" U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH, P.FLAG_STATUS_ONLINE "
					+" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+" WHERE P.ID_FAIL = F.ID_FAIL" 
					+" AND S.ID_STATUS = P.ID_STATUS(+) " 
					+" AND P.ID_MASUK = U.USER_ID(+) "
					+" AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+" AND F.ID_SUBURUSAN IN (51,52,53) "
					+" AND P.ID_STATUS IN (138) "
					+" AND U.USER_ID = UI.USER_ID(+) ";
			   
			   sql = sql + " AND P.FLAG_JENISPERMOHONAN = '1' ";
									
		
						
			         
						
			   if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
	    			if(negeriUser.equals("14")){
	    				sql += "AND F.ID_NEGERI in (14,15,16) ";
	    			}else{
	    				sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
	    			}		
	    		}		
			   
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";		
			
			myLogger.debug("FAIL ONLINE PERMOHONAN:"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				
				h.put("FLAG_STATUS_ONLINE", rs.getString("FLAG_STATUS_ONLINE") == null ? "" : rs.getString("FLAG_STATUS_ONLINE"));	
				h.put("FLAG_SEMAKAN_PENGARAH", rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs.getString("FLAG_SEMAKAN_PENGARAH"));	
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? "" : rs.getString("FLAG_SEMAK"));	
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));	
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));				
				h.put("FLAG_JENISPERMOHONAN", rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs.getString("FLAG_JENISPERMOHONAN"));				
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("NO_JKPTG", rs.getString("NO_JKPTG") == null ? "" : rs.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN_KJP", rs.getString("TARIKH_PERMOHONAN_KJP") == null ? "" : rs.getString("TARIKH_PERMOHONAN_KJP"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
				h.put("NO_PERMOHONAN_ONLINE", rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs.getString("NO_PERMOHONAN_ONLINE"));
				
				if(rs.getString("ID_SUBURUSAN") != null){
					
					if(rs.getString("ID_SUBURUSAN").equals("51")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					}else if(rs.getString("ID_SUBURUSAN").equals("52")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					}else if(rs.getString("ID_SUBURUSAN").equals("53")){
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					}else{
						h.put("URUSAN", "");
					}
    				
    			}else{
    				h.put("URUSAN", "");
    			}
				
				listing_online_permohonan.addElement(h);
				
			}
			return listing_online_permohonan;
		} finally {
			if (db != null)	db.close();
		
		}
		
	}
	
	Vector listing_online_pembatalan = null;
	@SuppressWarnings("unchecked")
	public Vector listing_online_pembatalan(HttpSession session) throws Exception {
		listing_online_pembatalan = new Vector();
		Db db = null;
		listing_online_pembatalan.clear();
		
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			   sql = " SELECT "
					+" F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+" U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH "
					+" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+" WHERE P.ID_FAIL = F.ID_FAIL" 
					+" AND S.ID_STATUS = P.ID_STATUS(+) " 
					+" AND P.ID_MASUK = U.USER_ID(+) "
					+" AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+" AND F.ID_SUBURUSAN IN (51,52,53) "
					+" AND P.ID_STATUS NOT IN (8,999) "
					+" AND U.USER_ID = UI.USER_ID(+) ";
									
				sql = sql + " AND  P.ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
				+" FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTPEMBATALAN PNB,TBLPPTPEMBATALANHAKMILIK PH "
				+" WHERE F.ID_FAIL = P.ID_FAIL AND PH.ID_HAKMILIK = H.ID_HAKMILIK AND PH.ID_PEMBATALAN = PNB.ID_PEMBATALAN " +
						
			        	" AND H.ID_PERMOHONAN = P.ID_PERMOHONAN " +
			        	
			       	" AND PNB.FLAG_ONLINE = '2'"+
			        	
			        	
			 			" )";
					
						
			         
						
				if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
	    			if(negeriUser.equals("14")){
	    				sql += "AND F.ID_NEGERI in (14,15,16) ";
	    			}else{
	    				sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
	    			}		
	    		}		
				
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";		
			
			myLogger.debug("FAIL ONLINE PEMBATALAN:"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				
				
				h.put("FLAG_SEMAKAN_PENGARAH", rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs.getString("FLAG_SEMAKAN_PENGARAH"));	
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? "" : rs.getString("FLAG_SEMAK"));	
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));	
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));				
				h.put("FLAG_JENISPERMOHONAN", rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs.getString("FLAG_JENISPERMOHONAN"));				
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("NO_JKPTG", rs.getString("NO_JKPTG") == null ? "" : rs.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
				
				if(rs.getString("ID_SUBURUSAN") != null){
					
					if(rs.getString("ID_SUBURUSAN").equals("51")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					}else if(rs.getString("ID_SUBURUSAN").equals("52")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					}else if(rs.getString("ID_SUBURUSAN").equals("53")){
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					}else{
						h.put("URUSAN", "");
					}
    				
    			}else{
    				h.put("URUSAN", "");
    			}
				
				listing_online_pembatalan.addElement(h);
			}
			return listing_online_pembatalan;
		} finally {
			if (db != null)	db.close();
		}
	}
	
	
	
	Vector listing_online_penarikan = null;
	@SuppressWarnings("unchecked")
	public Vector listing_online_penarikan(HttpSession session) throws Exception {
		listing_online_penarikan = new Vector();
		Db db = null;
		listing_online_penarikan.clear();
		
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			   sql = " SELECT "
					+" F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+" U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH "
					+" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+" WHERE P.ID_FAIL = F.ID_FAIL" 
					+" AND S.ID_STATUS = P.ID_STATUS(+) " 
					+" AND P.ID_MASUK = U.USER_ID(+) "
					+" AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+" AND F.ID_SUBURUSAN IN (51,52,53) "
					+" AND P.ID_STATUS NOT IN (8,999) "
					+" AND U.USER_ID = UI.USER_ID(+) ";
									
				sql = sql + " AND  P.ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
				+" FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTPENARIKANBALIK PNB,TBLPPTPENARIKANHAKMILIK PH "
				+" WHERE F.ID_FAIL = P.ID_FAIL AND PH.ID_HAKMILIK = H.ID_HAKMILIK AND PH.ID_PENARIKANBALIK = PNB.ID_PENARIKANBALIK " +
						
			        	" AND H.ID_PERMOHONAN = P.ID_PERMOHONAN " +
			        	
			        	" AND PNB.FLAG_ONLINE = '2'"+
			        	
			        	
			 			" )";
					
						
			         
						
				if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
	    			if(negeriUser.equals("14")){
	    				sql += "AND F.ID_NEGERI in (14,15,16) ";
	    			}else{
	    				sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
	    			}		
	    		}		
				
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";		
			
			myLogger.debug("FAIL ONLINE PENARIKAN:"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				
				
				h.put("FLAG_SEMAKAN_PENGARAH", rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs.getString("FLAG_SEMAKAN_PENGARAH"));	
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? "" : rs.getString("FLAG_SEMAK"));	
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));	
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));				
				h.put("FLAG_JENISPERMOHONAN", rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs.getString("FLAG_JENISPERMOHONAN"));				
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("NO_JKPTG", rs.getString("NO_JKPTG") == null ? "" : rs.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
				
				if(rs.getString("ID_SUBURUSAN") != null){
					
					if(rs.getString("ID_SUBURUSAN").equals("51")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					}else if(rs.getString("ID_SUBURUSAN").equals("52")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					}else if(rs.getString("ID_SUBURUSAN").equals("53")){
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					}else{
						h.put("URUSAN", "");
					}
    				
    			}else{
    				h.put("URUSAN", "");
    			}
				
				listing_online_penarikan.addElement(h);
			}
			return listing_online_penarikan;
		} finally {
			if (db != null)	db.close();
		}
	}
	
	
	
	
	Vector listing_online_bantahan_pb = null;
	@SuppressWarnings("unchecked")
	public Vector listing_online_bantahan_pb(HttpSession session) throws Exception {
		listing_online_bantahan_pb = new Vector();
		Db db = null;
		listing_online_bantahan_pb.clear();
		
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			   sql = " SELECT "
					+" F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+" U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH "
					+" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+" WHERE P.ID_FAIL = F.ID_FAIL" 
					+" AND S.ID_STATUS = P.ID_STATUS(+) " 
					+" AND P.ID_MASUK = U.USER_ID(+) "
					+" AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+" AND F.ID_SUBURUSAN IN (51,52,53) "
					+" AND P.ID_STATUS NOT IN (8,999) "
					+" AND U.USER_ID = UI.USER_ID(+) ";
									
				sql = sql + " AND  P.ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
				+" FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTBANTAHAN BB,TBLPPTBORANGO O "
				+" WHERE F.ID_FAIL = P.ID_FAIL AND BB.ID_BANTAHAN = O.ID_BANTAHAN(+) AND O.ID_BORANGO IS NULL "
				+" AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_PERMOHONAN = P.ID_PERMOHONAN " +
				 " AND BB.FLAG_ONLINE = '2'"+
						"AND BB.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB )";
					
						
			         
						
				if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
	    			if(negeriUser.equals("14")){
	    				sql += "AND F.ID_NEGERI in (14,15,16) ";
	    			}else{
	    				sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
	    			}		
	    		}
				
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";		
			
			myLogger.debug("FAIL ONLINE BANTAHAN PB:"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				
				
				h.put("FLAG_SEMAKAN_PENGARAH", rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs.getString("FLAG_SEMAKAN_PENGARAH"));	
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? "" : rs.getString("FLAG_SEMAK"));	
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));	
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));				
				h.put("FLAG_JENISPERMOHONAN", rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs.getString("FLAG_JENISPERMOHONAN"));				
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("NO_JKPTG", rs.getString("NO_JKPTG") == null ? "" : rs.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
				
				if(rs.getString("ID_SUBURUSAN") != null){
					
					if(rs.getString("ID_SUBURUSAN").equals("51")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					}else if(rs.getString("ID_SUBURUSAN").equals("52")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					}else if(rs.getString("ID_SUBURUSAN").equals("53")){
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					}else{
						h.put("URUSAN", "");
					}
    				
    			}else{
    				h.put("URUSAN", "");
    			}
				
				listing_online_bantahan_pb.addElement(h);
			}
			return listing_online_bantahan_pb;
		} finally {
			if (db != null)	db.close();
		}
	}
	
	
	Vector listing_online_bantahan_agensi = null;
		@SuppressWarnings("unchecked")
		public Vector listing_online_bantahan_agensi(HttpSession session) throws Exception {
			listing_online_bantahan_agensi = new Vector();
			Db db = null;
			listing_online_bantahan_agensi.clear();
			
			String userId = (String)session.getAttribute("_ekptg_user_id");
			String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				   sql = " SELECT "
						+" F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
						+" U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH "
						+" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
						+" WHERE P.ID_FAIL = F.ID_FAIL" 
						+" AND S.ID_STATUS = P.ID_STATUS(+) " 
						+" AND P.ID_MASUK = U.USER_ID(+) "
						+" AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
						+" AND F.ID_SUBURUSAN IN (51,52,53) "
						+" AND P.ID_STATUS NOT IN (8,999) "
						+" AND U.USER_ID = UI.USER_ID(+) ";
										
						sql = sql + " AND P.ID_PERMOHONAN IN "						
						+" (SELECT P.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTBANTAHAN BB,TBLPPTBORANGO O "
						+" WHERE F.ID_FAIL = P.ID_FAIL AND H.ID_HAKMILIK = BB.ID_HAKMILIK AND BB.ID_BANTAHAN = O.ID_BANTAHAN(+) AND O.ID_BORANGO is null " +
					   
						" AND BB.FLAG_ONLINE = '2'"+
						
						
						"AND P.ID_PERMOHONAN = H.ID_PERMOHONAN) ";
							
				         
							
				if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
	    			if(negeriUser.equals("14")){
	    				sql += "AND F.ID_NEGERI in (14,15,16) ";
	    			}else{
	    				sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
	    			}		
	    		}
				
				sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";		
				
				myLogger.debug("FAIL ONLINE BANTAHAN AGENSI:"+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					
					
					h.put("FLAG_SEMAKAN_PENGARAH", rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs.getString("FLAG_SEMAKAN_PENGARAH"));	
					h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? "" : rs.getString("FLAG_SEMAK"));	
					h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));	
					h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));				
					h.put("FLAG_JENISPERMOHONAN", rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs.getString("FLAG_JENISPERMOHONAN"));				
					h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					h.put("NO_JKPTG", rs.getString("NO_JKPTG") == null ? "" : rs.getString("NO_JKPTG"));
					h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs.getString("NO_RUJUKAN_PTG"));
					h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs.getString("NO_RUJUKAN_PTD"));
					h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs.getString("NO_RUJUKAN_UPT"));
					h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs.getString("TARIKH_PERMOHONAN"));
					h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
					h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
					h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
					
					if(rs.getString("ID_SUBURUSAN") != null){
						
						if(rs.getString("ID_SUBURUSAN").equals("51")){
							h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
						}else if(rs.getString("ID_SUBURUSAN").equals("52")){
							h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
						}else if(rs.getString("ID_SUBURUSAN").equals("53")){
							h.put("URUSAN", "PENGAMBILAN SEMENTARA");
						}else{
							h.put("URUSAN", "");
						}
	    				
	    			}else{
	    				h.put("URUSAN", "");
	    			}
					
					listing_online_bantahan_agensi.addElement(h);
				}
				return listing_online_bantahan_agensi;
			} finally {
				if (db != null)db.close();
			}
		}
		
	
	
		
	@SuppressWarnings("unchecked")
	public List carianFail(HttpSession session,String no_fail,
			String no_hakmilik,String id_jenishakmilik,String no_lot_pt,String nama_pb,
			String no_pb,String no_akaun,String no_kes,String no_siasatan,String no_prosiding,
			String tarikh_permohonan,String id_status,String id_kementerian,String no_permohonan,
			String namaprojek,String id_negeri,String id_pegawai,String socJenisKeputusan, String flagJenisPampasan, String socTahun) 
	throws Exception {
		
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;
		
		
		List senaraiFail = null;
		
		String sql = "";
		Integer count = 0;

		try {
		
			db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

			   sql = " SELECT DISTINCT"
				    + " NVL(T_BIL_PEGAWAI_BERTUGAS.BIL_PEGAWAI_BERTUGAS,0) AS BIL_PEGAWAI_BERTUGAS,NVL(T_BIL_PEGAWAI_BERTUGAS_BARU.BIL_PEGAWAI_BERTUGAS_BARU,0) AS BIL_PEGAWAI_BERTUGAS_BARU, "
					   /*
				    +" (SELECT COUNT(DISTINCT HMX.ID_PEGAWAI) FROM TBLPPTHAKMILIK HMX " 
				   	+" WHERE HMX.ID_PEGAWAI IS NOT NULL AND HMX.ID_PERMOHONAN = P.ID_PERMOHONAN)AS BIL_PEGAWAI_BERTUGAS, "
				    
				   	+" (SELECT COUNT(DISTINCT AGHM.ID_AGIHANHM) " 
				   	+" FROM TBLPPTHAKMILIK HMX, TBLPPTAGIHANHM AGHM " 
				   	+" WHERE AGHM.ID_HAKMILIK = HMX.ID_HAKMILIK "
				   	+" AND HMX.ID_PERMOHONAN = P.ID_PERMOHONAN "
				   	+" AND AGHM.BARIS = '2' )AS BIL_PEGAWAI_BERTUGAS_BARU, "
				   	*/
				    
					+" F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN, P.TARIKH_PERMOHONAN AS TKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+" U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH,P.TARIKH_MASUK AS TARIKH_MASUK_P "
	/*22022011*/	+" ,MK2.FLAG_MMK AS FLAG_MMK_PENARIKAN, MKK.STATUS_KEPUTUSAN, JK.NAMA_KEMENTERIAN "
					+" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK,	 "
	
					+"   (SELECT NVL(COUNT(DISTINCT HMX.ID_PEGAWAI),0) AS BIL_PEGAWAI_BERTUGAS,HMX.ID_PERMOHONAN "
					+"   FROM TBLPPTHAKMILIK HMX "
                  +"   WHERE HMX.ID_PEGAWAI IS NOT NULL "
                    +"   GROUP BY HMX.ID_PERMOHONAN)  T_BIL_PEGAWAI_BERTUGAS, "
                    +"   (SELECT COUNT(DISTINCT AGHM.ID_AGIHANHM) AS BIL_PEGAWAI_BERTUGAS_BARU,HMX.ID_PERMOHONAN "
                   +"   FROM TBLPPTHAKMILIK HMX, TBLPPTAGIHANHM AGHM "
                  +"   WHERE AGHM.ID_HAKMILIK = HMX.ID_HAKMILIK "
                    +"   AND AGHM.BARIS = '2' GROUP BY HMX.ID_PERMOHONAN) T_BIL_PEGAWAI_BERTUGAS_BARU, "
	
	/*22022011*/	+" TBLPPTPENARIKANBALIK PB, TBLPPTMMK MK2, TBLPPTMMKKEPUTUSAN MKK, TBLRUJKEMENTERIAN JK "
					+" WHERE P.ID_FAIL = F.ID_FAIL" 
					+" AND P.ID_PERMOHONAN = T_BIL_PEGAWAI_BERTUGAS.ID_PERMOHONAN(+) AND P.ID_PERMOHONAN = T_BIL_PEGAWAI_BERTUGAS_BARU.ID_PERMOHONAN(+)      "
					+" AND JK.ID_KEMENTERIAN(+) = F.ID_KEMENTERIAN "
					+" AND S.ID_STATUS = P.ID_STATUS(+) " 
					+" AND P.ID_MASUK = U.USER_ID(+) "
					+" AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
	/*22022011*/	+" AND PB.ID_PERMOHONAN(+) = P.ID_PERMOHONAN"
	/*22022011*/    +" AND MK2.ID_PENARIKANBALIK(+) = PB.ID_PENARIKANBALIK "
	/*22022011*/    +" AND MK2.ID_MMK = MKK.ID_MMK(+) " 
					+" AND F.ID_SUBURUSAN IN (51,52,53) "
					+" AND P.ID_STATUS NOT IN (8,999) "
					+" AND U.USER_ID = UI.USER_ID(+) ";
			   
			   
			    //dapat flag
				boolean setLimit = true;  
				   
				    //no_fail,ptg,ptd
					if (no_fail != null) {
						if (!no_fail.trim().equals("")) {
							setLimit = false;
							sql += " AND (UPPER(f.no_fail) LIKE '%" + no_fail.toUpperCase().trim() + "%' " +
								" OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%" + no_fail.toUpperCase().trim() + "%' " +
								" OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%" + no_fail.toUpperCase().trim() + "%' " +
								" OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%" + no_fail.toUpperCase().trim() + "%')";
						}
					}
					
					if (socTahun != null) {
						if (!socTahun.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
						}
					}
					
					
					//tarikh_permohonan
					if (tarikh_permohonan != null) {
						if (!tarikh_permohonan.toString().trim().equals("")) {
							setLimit = false;
							sql = sql + " AND REPLACE(TO_CHAR(P.TARIKH_PERMOHONAN,'dd/MM/YYYY'),' ','') = '" + tarikh_permohonan +"' ";
						}
					}
					
					//id_status
					if (id_status != null) {
						if (!id_status.trim().equals("")) {
							setLimit = false;
							sql = sql + " AND P.ID_STATUS = '" + id_status +"' ";
						}
					}
					
					//id_pegawai pendaftar
					if (id_pegawai != null) {
						if (!id_pegawai.trim().equals("")) {
							setLimit = false;
							sql = sql + " AND P.ID_MASUK = '" + id_pegawai +"' ";
						}
					}
					
					
					//id_negeri
					if (id_negeri != null) {
						if (!id_negeri.trim().equals("")) {
							setLimit = false;
							sql = sql + " AND F.ID_NEGERI = '" + id_negeri +"' ";
						}
					}
					
					//id_kementerian
					if (id_kementerian != null) {
						if (!id_kementerian.trim().equals("")) {
							setLimit = false;
							sql = sql + " AND F.ID_KEMENTERIAN= '" + id_kementerian +"' ";
						}
					}

					
			 	  
			    	
			    	
			    	//no_hakmilik
			    	if (no_hakmilik != null) {
				    	if (!no_hakmilik.trim().equals("")) {
					    	setLimit = false;
					    	 sql = sql + "AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE trim(NO_HAKMILIK) = '"+no_hakmilik.trim()+"')";
						   	 }
				     }
			    	
			    	
			    	//no_permohonan
			    	if (no_permohonan != null) {
				    	if (!no_permohonan.trim().equals("")) {
					    	setLimit = false;
					    	 sql = sql + "AND P.NO_PERMOHONAN = '"+no_permohonan.trim()+"'";
						   	 }
				     }
			    	

			    	//id_jenishakmilik
			    	if (id_jenishakmilik != null) {
				    	if (!id_jenishakmilik.equals("")) {
					    	setLimit = false;
					    	 sql = sql + "AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_JENISHAKMILIK = '"+id_jenishakmilik+"')";
						   	 }
				     }
			    	
			    	//no_lot_pt
			    	if (no_lot_pt != null) {
				    	if (!no_lot_pt.equals("")) {
					    	setLimit = false;
					    	 sql = sql + "AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE UPPER(NO_LOT) LIKE UPPER('%' ||'"+no_lot_pt.trim()+"'|| '%') OR UPPER(NO_PT) LIKE UPPER('%' ||'"+no_lot_pt.trim()+"'|| '%'))";
						   
					    }
				     }
			    	
			    	//nama_pb
					if (nama_pb != null) {
						if (!nama_pb.trim().equals("")) {
							setLimit = false;
							
							sql = sql + " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "
				                      +  " WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
				                         " AND UPPER(PB.NAMA_PB)  LIKE UPPER('%' ||'"+nama_pb.trim()+"'|| '%') ) ";			
							
						}
					}
					
					//namaprojek
					if (namaprojek != null) {
						if (!namaprojek.trim().equals("")) {
							setLimit = false;
							
							sql = sql + " AND  lower(P.TUJUAN)  LIKE lower('%' ||'"+namaprojek.trim()+"'|| '%')  ";			
							
						}
					}
					
					
					//no_pb
					if (no_pb != null) {
						if (!no_pb.trim().equals("")) {
							setLimit = false;
							
							sql = sql + " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "
				                      +  " WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
				                         " AND PB.NO_PB  LIKE '%' ||'"+no_pb.trim()+"'|| '%' ) ";			
							
						}
					}
					
					//no_akaun
					if (no_akaun != null) {
						if (!no_akaun.trim().equals("")) {
							setLimit = false;
							
							sql = sql + " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "
				                      +  " WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
				                         " AND HPB.NO_AKAUN  LIKE '%' ||'"+no_akaun.trim()+"'|| '%' ) ";			
							
						}
					}
					
					
					//no_siasatan
					if (no_siasatan != null) {
						if (!no_siasatan.trim().equals("")) {
							setLimit = false;							
							sql = sql + " AND P.ID_PERMOHONAN IN (SELECT DISTINCT H.ID_PERMOHONAN " +
									"FROM TBLPPTHAKMILIK H,TBLPPTSIASATAN SIA " +
									"WHERE SIA.ID_HAKMILIK = H.ID_HAKMILIK AND UPPER(SIA.NO_SIASATAN)  LIKE UPPER('%' ||'"+no_siasatan.trim()+"'|| '%') ) ";			
						}
					}
					
					//pampasan amanah raya / amanah / pb
					if (flagJenisPampasan != null) {
						if (!flagJenisPampasan.trim().equals("")) {
							setLimit = false;							
							sql += " AND P.ID_PERMOHONAN IN (select distinct a.id_permohonan "+
									" from tblpptpermohonan a, tblpptsiasatan b, tblpptaward c "+
									" where b.id_permohonan = a.id_permohonan "+
									" and c.id_siasatan = b.id_siasatan "+
									" and b.id_penarikanbalik is null "+
									" and c.status_penerima = '"+flagJenisPampasan+"') " ;	
						}
					}
					
					//no_kes
					if (no_kes != null) {
						if (!no_kes.trim().equals("")) {
							setLimit = false;							
							sql = sql + " AND P.ID_PERMOHONAN IN (SELECT DISTINCT H.ID_PERMOHONAN " +
									"FROM TBLPPTHAKMILIK H,TBLPPTSIASATAN SIA " +
									"WHERE SIA.ID_HAKMILIK = H.ID_HAKMILIK AND UPPER(SIA.NO_KES)  LIKE UPPER('%' ||'"+no_kes.trim()+"'|| '%') ) ";			
						}
					}
					
					
					//no_ruj_prosiding
					if (no_prosiding != null) {
						if (!no_prosiding.trim().equals("")) {
							setLimit = false;							
							sql = sql + " AND P.ID_PERMOHONAN IN "+
					 " (SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN  WHERE ID_PERMOHONAN IN "
					+" (SELECT P.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTBANTAHAN BB,TBLPPTBORANGO O "
					+" WHERE F.ID_FAIL = P.ID_FAIL AND H.ID_HAKMILIK = BB.ID_HAKMILIK AND BB.ID_BANTAHAN = O.ID_BANTAHAN " +
							"AND P.ID_PERMOHONAN = H.ID_PERMOHONAN AND UPPER(O.NO_RUJUKAN_TANAH) LIKE UPPER('%' ||'"+no_prosiding.trim()+"'|| '%')) " 
					+" OR "
					+" ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
					+" FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTBANTAHAN BB,TBLPPTBORANGO O "
					+" WHERE F.ID_FAIL = P.ID_FAIL AND BB.ID_BANTAHAN = O.ID_BANTAHAN "
					+" AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_PERMOHONAN = P.ID_PERMOHONAN " +
							"AND BB.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND UPPER(O.NO_RUJUKAN_TANAH) LIKE UPPER('%' ||'"+no_prosiding.trim()+"'|| '%')))";
						
						
						}
					}
			    	  
					String pengarah_xsemak = "";
					//proses pengesahan pengarah
					if (socJenisKeputusan != null) {
						if (!socJenisKeputusan.trim().equals("")) {
							setLimit = false;
							
							//PENGESAHAN PERMOHONAN
							if(socJenisKeputusan.equals("1")){
								sql += " AND P.ID_STATUS = '11' ";
								sql += " AND P.FLAG_SEMAK = '1' ";
								
							//TUNGGU AGIHAN	
							}else if(socJenisKeputusan.equals("2")){
								sql += " AND P.ID_STATUS = '16' ";
								
							//TUNGGU SEMAKAN MMK
							}else if(socJenisKeputusan.equals("3")){
								sql += " AND P.ID_STATUS = '132' ";
								sql += " AND MK.FLAG_SEMAKAN_PENGARAH = '1' ";
							}							
						}
						else
						{
							pengarah_xsemak = "yes";
						//	sql += " AND P.ID_STATUS != '11' ";
						//	sql += " AND P.ID_STATUS != '16' ";
						//	sql += " AND P.ID_STATUS != '132' ";							
						}
					} 
					
			         
			 
//			if (setLimit) {	//RESERVED BY AZAM
//			  
//					sql = sql + " AND ROWNUM <= 10 ";				
//				
//			}
/*			
			if(!negeriUser.equals("16"))
			{
				sql = sql + "AND F.ID_NEGERI = '"+negeriUser+"' ";	
				
			}
*/			
			if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
    			if(negeriUser.equals("14")){
    				sql += "AND F.ID_NEGERI in (14,15,16) ";
    			}else{
    				sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
    			}		
    		}
			
			
			if(pengarah_xsemak.equals("yes"))
			{
			//sql = sql + " ORDER BY P.TARIKH_MASUK DESC";	
				
			}
			
			
			
			//sql = sql + " ORDER BY TARIKH_MASUK_P DESC";
			
			
			
			
			
			
			
/*sql = sql + " ORDER BY " 
			+ " CASE nvl(p.flag_semak,0)"
			+ " WHEN '1' THEN 1" 
			+ " END asc,"
			+ " CASE nvl(p.id_status,0)"
			+ " WHEN 16 THEN 1"		
			+ " END asc,"
			+ " CASE nvl(mk.flag_semakan_pengarah,0)"
			+ " WHEN '1' THEN 1" 	
			+ " END asc,"			

			//22022011
			+ " CASE nvl(FLAG_MMK_PENARIKAN,0)"
			+ " WHEN '1' THEN 1" 	
			+ " END asc,"
			
		+"" +
		"p.tarikh_masuk DESC";	
			
*/
//yati comment
			
			sql = sql + "  and p.tarikh_permohonan is not null ORDER BY " 
			+ "TKH_PERMOHONAN DESC,"
			+ " CASE nvl(p.flag_semak,0)"
			+ " WHEN '1' THEN 1" 
			+ " END ASC," 
			+ " CASE NVL (p.id_status, 0)"
            + " WHEN 16 THEN 1"
            + " END ASC" ;			
			
	//	+"" +
	//	"TKH_PERMOHONAN DESC";	
			
	
	
	myLogger.debug("FAIL TUGASAN PPT:"+sql);	
			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);

			int bil = 1;
			senaraiFail = Collections.synchronizedList(new ArrayList());
			Map h = null;
			
			while (rs.next()) {
				
				h = Collections.synchronizedMap(new HashMap());
				h.put("bil", bil);	
				h.put("BIL_PEGAWAI_BERTUGAS_BARU", rs.getString("BIL_PEGAWAI_BERTUGAS_BARU") == null ? "" : rs.getString("BIL_PEGAWAI_BERTUGAS_BARU"));
				h.put("NAMA_KEMENTERIAN", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN"));
				h.put("BIL_PEGAWAI_BERTUGAS", rs.getString("BIL_PEGAWAI_BERTUGAS") == null ? "" : rs.getString("BIL_PEGAWAI_BERTUGAS"));
				h.put("STATUS_KEPUTUSAN", rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs.getString("STATUS_KEPUTUSAN"));
				h.put("FLAG_MMK_PENARIKAN", rs.getString("FLAG_MMK_PENARIKAN") == null ? "" : rs.getString("FLAG_MMK_PENARIKAN"));	
				h.put("FLAG_SEMAKAN_PENGARAH", rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs.getString("FLAG_SEMAKAN_PENGARAH"));	
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? "" : rs.getString("FLAG_SEMAK"));	
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));	
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));				
				h.put("FLAG_JENISPERMOHONAN", rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs.getString("FLAG_JENISPERMOHONAN"));				
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("NO_JKPTG", rs.getString("NO_JKPTG") == null ? "" : rs.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
				h.put("TUJUAN", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				
				if(rs.getString("ID_SUBURUSAN") != null){
					
					if(rs.getString("ID_SUBURUSAN").equals("51")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					}else if(rs.getString("ID_SUBURUSAN").equals("52")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					}else if(rs.getString("ID_SUBURUSAN").equals("53")){
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					}else{
						h.put("URUSAN", "");
					}
    				
    			}else{
    				h.put("URUSAN", "");
    			}
				
				if ((setLimit && bil <= 50) || setLimit==false){
					senaraiFail.add(h);
				}
							
				bil++;
				count++;
			}
			
		} finally {
			if (rs != null) rs.close();	
			if (stmt != null) stmt.close();
			if (db != null) db.close();					
		}
		
		return senaraiFail;
		
	}


	  public int totalFail(HttpSession session)  throws Exception {
		  	String userId = (String)session.getAttribute("_ekptg_user_id");
		  	String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		  	Db db = null; 
		  	int total = 0;
		  	String sql="";
		  	ResultSet rs = null;
			try { 
				//Open the database connection
				db = new Db(); 
				  sql = " SELECT COUNT(*) FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI "
						+" WHERE P.ID_FAIL = F.ID_FAIL" 
						+" AND S.ID_STATUS = P.ID_STATUS(+) " 
						+" AND P.ID_MASUK = U.USER_ID(+) "
						+" AND F.ID_SUBURUSAN IN (51,52,53) "
						+" AND U.USER_ID = UI.USER_ID(+) ";	//get some data 
				  
				  if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
		    			if(negeriUser.equals("14")){
		    				sql += "AND F.ID_NEGERI in (14,15,16) ";
		    			}else{
		    				sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
		    			}		
		    		}
				  
				rs = db.getStatement().executeQuery(sql); 
//				System.out.println("SQL MY INFO :: "+sql);
			if ( rs.next() ) { 
				total = rs.getInt(1); 
			} 
			} finally { 
			//Close the database connection 
			if ( db != null ) db.close(); 
			if (rs != null) rs.close();			
			} 
			return total;
	  }
	  
	  
	  Vector list_status = null;
		@SuppressWarnings("unchecked")
		public Vector list_status() throws Exception {
			list_status = new Vector();
			Db db = null;
			list_status.clear();
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT ID_STATUS,KOD_STATUS,KETERANGAN FROM TBLRUJSTATUS WHERE ID_SEKSYEN = 1 " +
						" AND ID_STATUS NOT IN (188,189,1610223,1610224,1610225,1610226,1610227,1610228,1610229,1610230,1610231,1610232,1610233,1610234) " +
						"ORDER BY KOD_STATUS ASC";
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
							.getString("ID_STATUS"));
					h.put("KOD_STATUS", rs.getString("KOD_STATUS") == null ? ""
							: rs.getString("KOD_STATUS").toUpperCase());
					h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
							: rs.getString("KETERANGAN").toUpperCase());
					list_status.addElement(h);
				}
				return list_status;
			} finally {
				if (db != null)	db.close();
			}
		}
		
		
		
		 Vector list_negeri = null;
			@SuppressWarnings("unchecked")
			public Vector list_negeri() throws Exception {
				list_negeri = new Vector();
				Db db = null;
				list_negeri.clear();
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();

					sql = "SELECT ID_NEGERI,KOD_NEGERI,NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI NOT IN (12,13,15,16,17,0)";
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;
					while (rs.next()) {
						h = new Hashtable();
						h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
						h.put("KOD_NEGERI", rs.getString("KOD_NEGERI") == null ? ""
								: rs.getString("KOD_NEGERI").toUpperCase());
						h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
								: rs.getString("NAMA_NEGERI").toUpperCase());
						list_negeri.addElement(h);
					}
					return list_negeri;
				} finally {
					if (db != null)	db.close();
				}
			}
			
		
		
		
		
		private Vector senaraiFailUtiliti = null;		
		@SuppressWarnings("unchecked")
		public Vector carianFailUtiliti(String noFail, HttpSession session) throws Exception {
			Db db = null;
			senaraiFailUtiliti = new Vector();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String userId = session.getAttribute("_ekptg_user_id").toString();
			String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
			String UserRole = (String) session.getAttribute("_portal_role");
	
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				Hashtable h;
				int bil = 1;
				Integer count = 0;
				
				if (noFail != null) {
					if (!noFail.trim().equals("")) {
		
						
						sql = " SELECT 	MAX(SF.ID_SUBURUSANSTATUSFAILPPT) AS ID_SUBURUSANSTATUSFAILPPT, "
						+" P.FLAG_SEGERA,F.ID_SUBURUSAN,SF.ID_SUBURUSANSTATUS,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,"
						+" P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_PTD,P.NO_RUJUKAN_UPT,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,"
						+" S.KETERANGAN AS STATUS,  U.USER_NAME,P.ID_PERMOHONAN,S.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER  "
						+" FROM TBLPPTPERMOHONAN P,TBLRUJSUBURUSANSTATUSFAILPPT SF, "
						+" TBLRUJSUBURUSANSTATUS SS,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI  "
						+" WHERE P.ID_FAIL = F.ID_FAIL " 
						//"AND S.ID_STATUS = P.ID_STATUS(+)  "
						+" AND F.ID_SUBURUSAN IN (51,52,53) "
						+" AND P.ID_STATUS NOT IN (8,999)"
						+" AND P.ID_MASUK = U.USER_ID(+)  ";
						
						if (noFail != null) {
							if (!noFail.trim().equals("")) {
								
								sql = sql + " AND (( UPPER(F.NO_FAIL) LIKE '%' ||'"
										+ noFail.trim().toUpperCase().trim() + "'|| '%' ) " ;
								sql = sql + " OR ( UPPER(P.NO_RUJUKAN_PTG) LIKE '%' ||'"
							    + noFail.trim().toUpperCase().trim() + "'|| '%' ) " ;
								sql = sql + " OR ( UPPER(P.NO_RUJUKAN_UPT) LIKE '%' ||'"
							    + noFail.trim().toUpperCase().trim() + "'|| '%' ) " ;
								sql = sql + " OR ( UPPER(P.NO_RUJUKAN_PTD) LIKE '%' ||'"
							    + noFail.trim().toUpperCase().trim() + "'|| '%' )) " ;
							}
						}
						
						
						sql = sql +" AND F.ID_FAIL = SF.ID_FAIL AND SS.ID_SUBURUSANSTATUS = SF.ID_SUBURUSANSTATUS "
						+" AND S.ID_STATUS = SS.ID_STATUS " 
						+" AND S.ID_STATUS NOT IN (1610223,1610224,1610225,1610226,1610227,1610228,1610229,1610230,1610231,1610232,1610233,1610234)  "
						+" AND U.USER_ID = UI.USER_ID(+) ";
						
						
						//if(!UserRole.equals("adminppt") && !UserRole.equals("(PPT)PenolongPengarahUnit") && !UserRole.equals("(PPT)PengarahUnit") && !UserRole.equals("(PPT)KetuaPenolongPengarahUnit")){ 
						
						if(!UserRole.equals("adminppt") && !UserRole.equals("(PPT)PengarahUnit") && !UserRole.equals("(PPT)KetuaPenolongPengarahUnit")){
							sql += "AND S.ID_STATUS NOT IN (148)";
						}
						
						if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
			    			if(negeriUser.equals("14")){
			    				sql += "AND F.ID_NEGERI in (14,15,16) ";
			    			}else{
			    				sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
			    			}		
			    		}
						
						 
						sql = sql+ " GROUP BY "
						+" P.FLAG_SEGERA,F.ID_SUBURUSAN,SF.ID_SUBURUSANSTATUS,P.FLAG_JENISPERMOHONAN,F.NO_FAIL, "
						+" P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_PTD,P.NO_RUJUKAN_UPT,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY'), "
						+" S.KETERANGAN,  U.USER_NAME,P.ID_PERMOHONAN,S.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI "
						+" ORDER BY  ID_SUBURUSANSTATUSFAILPPT  ASC ";
										
						
						myLogger.info("SQL KEMAS:"+sql.toUpperCase());
						ResultSet rs = stmt.executeQuery(sql);		
						
						while (rs.next()){
							
							count++;
							h = new Hashtable();
							h.put("bil", count);				
							h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));				
							h.put("FLAG_JENISPERMOHONAN", rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs.getString("FLAG_JENISPERMOHONAN"));				
							h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
							h.put("NO_JKPTG", rs.getString("NO_JKPTG") == null ? "" : rs.getString("NO_JKPTG"));
							h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs.getString("NO_RUJUKAN_PTG"));
							h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs.getString("NO_RUJUKAN_PTD"));
							h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs.getString("NO_RUJUKAN_UPT"));
							h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs.getString("TARIKH_PERMOHONAN"));
							h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
							h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
							h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
							h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
							h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
							h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));
							
							senaraiFailUtiliti.addElement(h);
						

						}
						  
							myLogger.info("SENARAIFAIL 1:"+senaraiFailUtiliti);
					}
					
				}		
				if (count == 0) {
					h = new Hashtable();

					h.put("bil", "");				
					h.put("ID_SUBURUSAN", "");				
					h.put("FLAG_JENISPERMOHONAN","");				
					h.put("ID_STATUS","");
					h.put("NO_JKPTG","");
					h.put("NO_RUJUKAN_PTG","");
					h.put("NO_RUJUKAN_PTD","");
					h.put("TARIKH_PERMOHONAN","");
					h.put("STATUS","Tiada Rekod");
					h.put("USER_NAME","");
					h.put("ID_PERMOHONAN","");
					h.put("ID_FAIL","");
					h.put("NEGERI_USER","");
					
				
					senaraiFailUtiliti.addElement(h);
				}
				
				return senaraiFailUtiliti;
				
				
				} finally {	
					if (db != null)	db.close();	
					
				}
				
			
		}
		
		@SuppressWarnings("unchecked")
		public Vector utilitiStatusKJP(String id_kementerian, String id_agensi, String id_negeri, String id_daerah, String socKementerian, String socAgensi, String socNegeri, String socDaerah, String findNamaProjek, String findNoRujukan)throws Exception {
				
			Vector SenaraiFail = new Vector();
					
					Db db = null;
				    String sql = "";
				    
				    try {
				    	
				    	db = new Db();
			    		Statement stmt = db.getStatement();
				      
			    		sql = " SELECT DISTINCT P.ID_PERMOHONAN, P.TUJUAN, P.FLAG_SEMAK, P.FLAG_SEMAKAN_ONLINE, P.FLAG_STATUS_ONLINE, "+
			    				" P.NO_PERMOHONAN_ONLINE, F.ID_FAIL, F.NO_FAIL, K.NAMA_KEMENTERIAN, A.NAMA_AGENSI, N.NAMA_NEGERI, "+
			    				" D.NAMA_DAERAH, S.KETERANGAN, P.TARIKH_PERMOHONAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, "+
			    				" P.CATATAN_STATUS_ONLINE, K.ID_KEMENTERIAN, A.ID_AGENSI, N.ID_NEGERI, D.ID_DAERAH, "+
			    				" (CASE WHEN P.FLAG_SEMAKAN_ONLINE = '1' AND P.NO_PERMOHONAN_ONLINE IS NULL THEN 'SEMAKAN KJP' "+
			    				" WHEN P.FLAG_SEMAKAN_ONLINE = '2' AND P.NO_PERMOHONAN_ONLINE IS NULL THEN 'KELULUSAN KJP' "+
			    				" WHEN P.FLAG_SEMAKAN_ONLINE = '3' AND P.NO_PERMOHONAN_ONLINE IS NULL THEN 'KELULUSAN KJP' "+
			    				" WHEN P.FLAG_SEMAKAN_ONLINE = '2' AND P.NO_PERMOHONAN_ONLINE IS NOT NULL THEN 'JKPTG NEGERI' "+
			    				" WHEN P.FLAG_SEMAKAN_ONLINE = '3' AND NVL (P.FLAG_STATUS_ONLINE, ' ') <> '1' THEN 'JKPTG NEGERI' "+
			    				" WHEN P.FLAG_SEMAKAN_ONLINE = '3' AND P.FLAG_STATUS_ONLINE = '1' THEN 'PENYEDIAAN KJP' "+
			    				" WHEN P.FLAG_SEMAKAN_ONLINE = '4' AND P.FLAG_STATUS_ONLINE IS NULL THEN 'PENYEDIAAN KJP' "+
			    				" WHEN P.FLAG_SEMAKAN_ONLINE IS NULL AND P.FLAG_STATUS_ONLINE = '1' THEN 'PENYEDIAAN KJP' "+
			    				" WHEN F.NO_FAIL IS NOT NULL THEN 'JKPTG NEGERI' ELSE '' END ) AS ROLE "+
			    				" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLRUJSTATUS S, TBLRUJKEMENTERIAN K, "+
			    				" TBLRUJAGENSI A, TBLRUJNEGERI N, TBLRUJDAERAH D "+
			    				" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN AND P.ID_STATUS = S.ID_STATUS "+
			    				" AND P.ID_AGENSI = A.ID_AGENSI AND F.ID_NEGERI = N.ID_NEGERI AND P.ID_DAERAH = D.ID_DAERAH "+
			    				" AND ( ( P.FLAG_SEMAKAN_ONLINE IS NOT NULL AND P.NO_PERMOHONAN_ONLINE IS NULL) "+
			    				" OR ( P.FLAG_SEMAKAN_ONLINE IS NULL AND P.FLAG_STATUS_ONLINE IS NOT NULL AND P.NO_PERMOHONAN_ONLINE IS NULL) "+
			    				" OR ( P.NO_PERMOHONAN_ONLINE IS NOT NULL AND F.NO_FAIL IS NULL) "+
			    				" OR ( P.NO_PERMOHONAN_ONLINE IS NOT NULL AND F.NO_FAIL IS NOT NULL) ) "+
			    				" AND F.ID_SUBURUSAN IN ('51', '52', '53') ";
			
						if (socKementerian != null) {
							if (!socKementerian.trim().equals("")
									&& !socKementerian.trim().equals("9999")) {
								sql = sql + " AND K.ID_KEMENTERIAN = '" + socKementerian.trim() + "'";
							}
						}
						if (socAgensi != null) {
							if (!socAgensi.trim().equals("")
									&& !socAgensi.trim().equals("9999")) {
								sql = sql + " AND A.ID_AGENSI = '" + socAgensi.trim() + "'";
							}
						}
						if (socNegeri != null) {
							if (!socNegeri.trim().equals("")
									&& !socNegeri.trim().equals("9999")) {
								sql = sql + " AND N.ID_NEGERI = '" + socNegeri.trim() + "'";
							}
						}
						if (socDaerah != null) {
							if (!socDaerah.trim().equals("")
									&& !socDaerah.trim().equals("9999")) {
								sql = sql + " AND D.ID_DAERAH = '" + socDaerah.trim() + "'";
							}
						}
						if (findNamaProjek != null) {
							if (!findNamaProjek.trim().equals("")) {
								sql = sql + " AND P.TUJUAN LIKE '%' ||'"
										+ findNamaProjek.trim().toUpperCase() + "'|| '%'";
							}
						}
						if (findNoRujukan != null) {
							if (!findNoRujukan.trim().equals("")) {
								sql = sql + " AND P.NO_PERMOHONAN_ONLINE LIKE '%' ||'"
										+ findNoRujukan.trim().toUpperCase() + "'|| '%'";
							}
						}
						
						sql = sql + " ORDER BY P.TARIKH_PERMOHONAN DESC";
						
							myLogger.info(" utilitiStatusKJP >>> " + sql.toUpperCase());
							
							ResultSet rs = stmt.executeQuery(sql);
							
				    		Hashtable h;
				    		int bil = 1;
				    		
				    		while (rs.next()) {
				    			h = new Hashtable();
				    			h.put("bil", bil);
				    			h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "TIADA" : rs.getString("ID_PERMOHONAN"));
								h.put("TUJUAN",rs.getString("TUJUAN") == null ? "TIADA" : rs.getString("TUJUAN"));
								h.put("FLAG_SEMAK",rs.getString("FLAG_SEMAK") == null ? "TIADA" : rs.getString("FLAG_SEMAK"));
								h.put("FLAG_SEMAKAN_ONLINE",rs.getString("FLAG_SEMAKAN_ONLINE") == null ? "TIADA" : rs.getString("FLAG_SEMAKAN_ONLINE"));
								h.put("FLAG_STATUS_ONLINE",rs.getString("FLAG_STATUS_ONLINE") == null ? "TIADA" : rs.getString("FLAG_STATUS_ONLINE"));
								h.put("NO_PERMOHONAN_ONLINE",rs.getString("NO_PERMOHONAN_ONLINE") == null ? "TIADA" : rs.getString("NO_PERMOHONAN_ONLINE"));
								h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "TIADA" : rs.getString("ID_FAIL"));
								h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "TIADA" : rs.getString("NO_FAIL"));
								h.put("NAMA_KEMENTERIAN",rs.getString("NAMA_KEMENTERIAN") == null ? "TIADA" : rs.getString("NAMA_KEMENTERIAN"));
								h.put("NAMA_AGENSI",rs.getString("NAMA_AGENSI") == null ? "TIADA" : rs.getString("NAMA_AGENSI"));
								h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "TIADA" : rs.getString("NAMA_NEGERI"));
								h.put("NAMA_DAERAH",rs.getString("NAMA_DAERAH") == null ? "TIADA" : rs.getString("NAMA_DAERAH"));
								h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "TIADA" : rs.getString("KETERANGAN"));
								h.put("TARIKH_PERMOHONAN",rs.getString("TARIKH_PERMOHONAN") == null ? "TIADA" : rs.getString("TARIKH_PERMOHONAN"));
								h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "TIADA" : rs.getString("TARIKH_MASUK"));
								h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "TIADA" : rs.getString("TARIKH_KEMASKINI"));
								h.put("CATATAN_STATUS_ONLINE",rs.getString("CATATAN_STATUS_ONLINE") == null ? "TIADA" : rs.getString("CATATAN_STATUS_ONLINE"));
								h.put("ID_KEMENTERIAN",rs.getString("ID_KEMENTERIAN") == null ? "TIADA" : rs.getString("ID_KEMENTERIAN"));
								h.put("ID_AGENSI",rs.getString("ID_AGENSI") == null ? "TIADA" : rs.getString("ID_AGENSI"));
								h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "TIADA" : rs.getString("ID_NEGERI"));
								h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "TIADA" : rs.getString("ID_DAERAH"));
								h.put("ROLE",rs.getString("ROLE") == null ? "TIADA" : rs.getString("ROLE"));
				    			
				    			SenaraiFail.addElement(h);	
								bil++;
								
								//myLogger.info(" SenaraiFail >>> " +SenaraiFail);
							}
							
							return SenaraiFail;
						}catch (Exception re) {
							throw re;
						} finally {
							if (db != null)
								db.close();
						}
		}//close utilitiStatusKJP
		
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
			} finally {	
				if (db != null)	db.close();	
			}
		}
		
		
		//jenis hakmilik untuk selangor
		Vector jenis_hakmilik_selangor = null;
		public Vector jenis_hakmilik_selangor() throws Exception {
			jenis_hakmilik_selangor = new Vector();
			Db db = null;
			jenis_hakmilik_selangor.clear();
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT ID_JENISHAKMILIK,KOD_JENIS_HAKMILIK,KETERANGAN,STATUS_HAKMILIK,SIMPANAN FROM TBLRUJJENISHAKMILIK " +
					  "WHERE ID_JENISHAKMILIK IN (16,6,4,5,17,15,11,1,3,2,99) ORDER BY KOD_JENIS_HAKMILIK ASC";
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
							.getString("ID_JENISHAKMILIK"));
					h.put("KOD_JENIS_HAKMILIK", rs.getString("KOD_JENIS_HAKMILIK") == null ? ""
							: rs.getString("KOD_JENIS_HAKMILIK").toUpperCase());
					h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
							: rs.getString("KETERANGAN").toUpperCase());
					h.put("STATUS_HAKMILIK", rs.getString("STATUS_HAKMILIK") == null ? ""
							: rs.getString("STATUS_HAKMILIK").toUpperCase());
					h.put("SIMPANAN", rs.getString("SIMPANAN") == null ? ""
							: rs.getString("SIMPANAN").toUpperCase());
					jenis_hakmilik_selangor.addElement(h);
				}
				return jenis_hakmilik_selangor;
			} finally {
				if (db != null)	db.close();
			}
		}	
		
		
		@SuppressWarnings("unchecked")
		public static void setCollectionData(String no_fail, String userIdNegeri) throws Exception {
			
			dataCollection = new Vector();
			
			Db db = null;
			dataCollection.clear();
			String sql = "";
			
			String nofail = no_fail.trim();
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
				
					sql =  "SELECT DISTINCT A.ID_PERMOHONAN, B.ID_FAIL, C.KETERANGAN AS STATUS_SEMASA, D.NAMA_SUBURUSAN, ";
					sql += " (SELECT COUNT(*) FROM TBLPPTHAKMILIK H1 WHERE H1.ID_PERMOHONAN = A.ID_PERMOHONAN ";
					sql += " AND NVL(H1.flag_pembatalan_keseluruhan,0) <> 'Y' AND NVL(H1.flag_penarikan_keseluruhan,0) <> 'Y' )AS TOTALHM ";
					sql += " FROM TBLPPTPERMOHONAN A, TBLPFDFAIL B, TBLRUJSTATUS C, TBLRUJSUBURUSAN D  ";
					sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
					sql += " AND B.ID_SUBURUSAN = D.ID_SUBURUSAN(+) ";
					sql += " AND A.ID_STATUS = C.ID_STATUS(+) ";
					sql += " AND (UPPER(B.NO_FAIL) = UPPER('"+nofail+"') "; 
					sql += " OR UPPER(A.NO_RUJUKAN_PTG) = UPPER('"+nofail+"') ";
					sql += " OR UPPER(A.NO_RUJUKAN_PTD) = UPPER('"+nofail+"') ";
					sql += " OR UPPER(A.NO_RUJUKAN_UPT) = UPPER('"+nofail+"')) ";
					sql += " AND A.ID_STATUS <> '999' ";
					
					if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			sql += "AND B.ID_NEGERI = '"+userIdNegeri+"'";
		    		}
					
					ResultSet rs = stmt.executeQuery(sql);
					
					while (rs.next()) {
						Hashtable h = new Hashtable();
						h.put("id_permohonan", rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));
						h.put("id_fail", rs.getString("ID_FAIL")== null?"":rs.getString("ID_FAIL"));
						h.put("status_semasa", rs.getString("STATUS_SEMASA")== null?"":rs.getString("STATUS_SEMASA"));
						h.put("totalhm", rs.getString("TOTALHM")== null?"":rs.getString("TOTALHM"));
						h.put("nama_suburusan", rs.getString("NAMA_SUBURUSAN")== null?"":rs.getString("NAMA_SUBURUSAN"));
						dataCollection.addElement(h);
				}
			} finally {
				if (db != null)db.close();
			}
			
		}//close setCollectionData	
		
		
		@SuppressWarnings("unchecked")
		public static void updateStatus(Hashtable data) throws Exception{
				
			  Db db = null;
			  String sql = "";
		    
			  try{
		      
				  	db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");	    	
		    		String id_permohonan = (String)data.get("id_permohonan");
		    		
		    		//status hapus (data tidak padam)
		    		String status = "999";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_permohonan", id_permohonan);	   
		    		r.add("id_status",status);  
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("tblpptpermohonan");
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close updateStatus
		
		@SuppressWarnings("unchecked")
		Vector checkEmail = null;
		
		@SuppressWarnings("unchecked")
		public Vector checkEmail(String userId) throws Exception {
			
			checkEmail = new Vector();
			checkEmail.clear();
			
			Db db = null;
			String sql = "";
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT EMEL FROM USERS_INTERNAL WHERE USER_ID = '"+userId+"' AND EMEL IS NOT NULL";
		
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("*** EMAIL SIAPA NI : "+sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("EMEL", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
					checkEmail.addElement(h);
				}
				return checkEmail;
			} finally {
				if (db != null)	db.close();
			}
		}
		
		@SuppressWarnings("unchecked")
		Vector checkEmailKementerian = null;
		
		@SuppressWarnings("unchecked")
		public Vector checkEmailKementerian(String idKementerian, String idJawatan, String userID) throws Exception {
			
			checkEmailKementerian = new Vector();
			checkEmailKementerian.clear();
			
			Db db = null;
			String sql = "";
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = " SELECT DISTINCT UI.EMEL, UK.ID_KEMENTERIAN, UI.ID_JAWATAN FROM USERS_KEMENTERIAN UK, TBLPFDFAIL F, USERS U, USERS_INTERNAL UI "+
				  " WHERE UK.ID_KEMENTERIAN = F.ID_KEMENTERIAN  "+
				  " AND F.ID_KEMENTERIAN = '"+idKementerian+"' "+
				  " AND U.USER_ID = UK.USER_ID AND U.USER_ID = UI.USER_ID "+
				  " AND UI.EMEL IS NOT NULL "+
				  " AND UI.ID_JAWATAN = '"+idJawatan+"' " ;
				
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("*** EMAIL SIAPA NI KEMENTERIAN : "+sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("EMEL", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
					h.put("ID_KEMENTERIAN", rs.getString("ID_KEMENTERIAN")== null?"":rs.getString("ID_KEMENTERIAN"));
					h.put("ID_JAWATAN", rs.getString("ID_JAWATAN")== null?"":rs.getString("ID_JAWATAN"));
					checkEmailKementerian.addElement(h);
				}
				return checkEmailKementerian;
			} finally {
				if (db != null)	db.close();
			}
		}	
		
	/*	
		public String getIdPerbicaraan(String noFail) throws Exception {
			Db db = null;
			try {
				db = new Db();
				String sql = "";
				
				sql = "SELECT A.NO_FAIL, D.ID_PERBICARAAN"
					+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKKEPUTUSANPERMOHONAN C, TBLPPKPERBICARAAN D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_KEPUTUSANPERMOHONAN = D.ID_KEPUTUSANPERMOHONAN"
					+ " AND A.NO_FAIL = '" + noFail + "'";
		
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					return rs.getString("ID_PERBICARAAN").toString();
				} else {
					return "";
				}
			} 
			finally {	if (db != null)	db.close();	}
		}*/

	/*	public Vector getSenaraiFailUtiliti() {
			return senaraiFailUtiliti;
		}

		public void setSenaraiFailUtiliti(Vector senaraiFailUtiliti) {
			this.senaraiFailUtiliti = senaraiFailUtiliti;
		}*/
		
		
	
		
		
		 Vector list_pegawai = null;
			@SuppressWarnings("unchecked")
			public Vector list_pegawai(HttpSession session) throws Exception {
				String userId = (String)session.getAttribute("_ekptg_user_id");
				String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
				list_pegawai = new Vector();
				Db db = null;
				list_pegawai.clear();
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					sql = " SELECT DISTINCT "
						+" U.USER_NAME,TO_CHAR(P.ID_MASUK) AS ID_PEGAWAI "
						+" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
						+" WHERE P.ID_FAIL = F.ID_FAIL" 
						+" AND S.ID_STATUS = P.ID_STATUS(+) " 
						+" AND P.ID_MASUK = U.USER_ID(+) "
						+" AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
						+" AND F.ID_SUBURUSAN IN (51,52,53) "
						+" AND P.ID_STATUS NOT IN (8,999) "
						+" AND U.USER_ID = UI.USER_ID(+) ";
							
					if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
		    			if(negeriUser.equals("14")){
		    				sql += "AND F.ID_NEGERI in (14,15,16) ";
		    			}else{
		    				sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
		    			}		
		    		}
					
				sql = sql + " ORDER BY U.USER_NAME ASC";	
				
				myLogger.info("PEGAWAI :"+sql.toUpperCase());
				
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;
					while (rs.next()) {
						h = new Hashtable();
						h.put("ID_PEGAWAI", rs.getString("ID_PEGAWAI") == null ? "" : rs
								.getString("ID_PEGAWAI"));
						h.put("USER_NAME", rs.getString("USER_NAME") == null ? "TIDAK_DINYATAKAN"
								: rs.getString("USER_NAME").toUpperCase());						
						list_pegawai.addElement(h);
					}
					return list_pegawai;
				} finally {
					if (db != null) db.close();
				}
			}
			
			
			@SuppressWarnings("unchecked")
			public static Vector getListPegawaiBertugas(String id_permohonan)throws Exception {
				 
				    Db db = null;
				    String sql = "";
				    
				    try{
				    	
				    		db = new Db();
				    		Statement stmt = db.getStatement();
				     
				    		sql +=  "SELECT "+ 
				    		 "CASE "+
				    		 " WHEN D.KOD_JENIS_HAKMILIK IS NOT NULL THEN D.KOD_JENIS_HAKMILIK || CHR(32) || B.NO_HAKMILIK "+ 
				    		 " ELSE B.NO_HAKMILIK "+ 
				    		 " END AS NO_HAKMILIK, "+ 
				    		 " CASE "+   
				    		 " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NULL THEN B.NO_LOT "+   
						     " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || B.NO_PT "+    
							 " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NOT NULL THEN JL.KETERANGAN  || B.NO_PT || CHR(32) || CHR(40) || B.NO_LOT  || CHR(41) "+  
							 " ELSE  '' "+   
							 " END AS NO_LOT, "+ 
							 " UPPER(C.USER_NAME) AS NAMA_PEGAWAI "+     
							 " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, USERS C, TBLRUJJENISHAKMILIK D, TBLRUJLOT JL, TBLPPTAGIHANHM AGHM "+  
							 " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN  "+ 
							 " AND B.ID_HAKMILIK = AGHM.ID_HAKMILIK(+) "+
							 " AND (B.ID_PEGAWAI IS NOT NULL AND B.ID_PEGAWAI = C.USER_ID "+
							 " OR B.ID_PEGAWAI IS NULL AND AGHM.ID_HAKMILIK IS NOT NULL AND AGHM.USER_ID = C.USER_ID AND AGHM.BARIS = '2') "+
							 " AND B.ID_JENISHAKMILIK = D.ID_JENISHAKMILIK(+) "+  
							 " AND B.ID_LOT = JL.ID_LOT(+) "+ 
							 " AND A.ID_PERMOHONAN ='"+id_permohonan+"'";

				    		ResultSet rs = stmt.executeQuery(sql);
				    		Vector list = new Vector();
				      
				    		Hashtable h = null;
				    		int bil = 1;
			
				    		while (rs.next()) {
				    			h = new Hashtable();
				    			h.put("bil", bil);	
				    			h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));	
				    			h.put("NO_LOT", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
				    			h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI")== null?"":rs.getString("NAMA_PEGAWAI"));
				    			list.addElement(h);
				    			bil++;
				    	  
				    		}//close while
				    		return list;
				    	}//close try 
				    	finally{
				    		if (db != null) db.close();
				    	}//close finally
				    	
			}//close getListPegawaiBertugas

}
