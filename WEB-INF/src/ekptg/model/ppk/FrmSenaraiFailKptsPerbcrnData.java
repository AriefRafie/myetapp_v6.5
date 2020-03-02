/**
 * NORZAILY JASMI
 */
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmSenaraiFailKptsPerbcrnData {
	
	static Logger myLogger = Logger.getLogger(FrmSenaraiFailKptsPerbcrnData.class);
	
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static Vector list = new Vector();
	private static Vector list17 = new Vector();
	private static Vector checkingNilaian = new Vector();
	
	
	public static Vector getList(){
		return list;
	}
	
	public static Vector getList17(){
		return list17;
	}
	
	public static void setList(String usid) throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
		
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
			      + " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
			      + " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
			      + " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
			      + " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
			      + " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='" +usid+ "' ";
				  
				   sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
				  
				  sql += " ) " 
			      + " AND P.ID_STATUS = S.ID_STATUS "
			      + " AND P.ID_FAIL = F.ID_FAIL(+) "
			      + " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) " 
			      + " AND P.ID_DAERAHMHN = D.ID_DAERAH "
			      + " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN " 
			      + " AND M.ID_SIMATI = MS.ID_SIMATI " 
			      + " AND P.ID_PEMOHON = PP.ID_PEMOHON "
			      + " AND (P.ID_STATUS=18 OR P.ID_STATUS=41 OR P.ID_STATUS=44 OR P.ID_STATUS=47 OR P.ID_STATUS=172 OR P.ID_STATUS=173 OR P.ID_STATUS=174 OR P.ID_STATUS=175 OR P.ID_STATUS=176 OR P.ID_STATUS=177 ) "
			      + " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
			      + " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
			      + " AND STA.AKTIF = '1' "
			      + " AND P.ID_STATUS <> '999' " 
			      + " AND P.SEKSYEN = '8' "
			      + " AND P.FLAG_JENIS_PERMOHONAN = '1' "
			      + " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)"
			      + " AND F.NO_FAIL IS NOT NULL ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC, P.TARIKH_KEMASKINI DESC ";	
		      
		      ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable h;
		      int bil = 1;	
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
				h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
				
				list.addElement(h);
				bil++;	
			}
		}finally {
			if(db != null) db.close();			
		}
	}


	//List permohonan seksyen 17
	public static void setList17(String usid)throws Exception {	    
		Db db = null;
		list17.clear();
	    String sql = "";    
	    try {    	
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      //SYARAT
	      sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
	      + " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
	      + " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
	      + " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
	      + " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
	      + " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='" +usid+ "'";
		  
		   sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
		  
		  sql += " 	) " 
	      + " AND P.ID_STATUS = S.ID_STATUS "
	      + " AND P.ID_FAIL = F.ID_FAIL(+) "
	      + " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) " 
	      + " AND P.ID_DAERAHMHN = D.ID_DAERAH "
	      + " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN " 
	      + " AND M.ID_SIMATI = MS.ID_SIMATI " 
	      + " AND P.ID_PEMOHON = PP.ID_PEMOHON "
	      + " AND (P.ID_STATUS=18 OR P.ID_STATUS=41 OR P.ID_STATUS=44 OR P.ID_STATUS=47 OR P.ID_STATUS=172 OR P.ID_STATUS=173 OR P.ID_STATUS=174 OR P.ID_STATUS=175 OR P.ID_STATUS=176 OR P.ID_STATUS=177 ) "
	      + " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
	      + " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
	      + " AND STA.AKTIF = '1' "
	      + " AND P.ID_STATUS <> '999' " 
	      + " AND P.SEKSYEN = '17' "
	      + " AND P.FLAG_JENIS_PERMOHONAN = '1' "
	      + " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)"
	      + " AND F.NO_FAIL IS NOT NULL ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC, P.TARIKH_KEMASKINI DESC ";
	      
//	      myLogger.info("LIST KEPUTUSAN PERBICARAAN SEKSYEN 17 :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);      
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  	h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS"));
				h.put("id_Fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_Fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikhmohon", rs.getDate("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getDate("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI"));
				h.put("namasimati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				
				list17.addElement(h);
				bil++;	
	      }
	    } finally {
	      if (db != null) db.close();
	    }	    
	}

	 public static Vector checkingNilaianAmanahRaya(String id_permohonansimati)throws Exception {
			
		    Db db = null;
		    checkingNilaian.clear();
		    String sql = "";
		    try {
		    	
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("nilai_ha_tarikhmohon");		      

		      r.add("id_permohonansimati",id_permohonansimati);
		      r.add("id_jenisha",98);

		      sql = r.getSQLSelect("Tblppkha");
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	h = new Hashtable();
		        h.put("nilai_ha_tarikhmohon", rs.getString("nilai_ha_tarikhmohon")==null?"0":Double.parseDouble(rs.getString("nilai_ha_tarikhmohon")));
		        
		    	checkingNilaian.addElement(h);
		    	bil++;
		      }
		      return checkingNilaian;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}//close checkingNilaian	
	
	
}

