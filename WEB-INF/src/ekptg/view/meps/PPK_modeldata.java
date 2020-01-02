package ekptg.view.meps;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;

public class PPK_modeldata extends EkptgCache implements Serializable {
	static Logger myLogger = Logger.getLogger(PPK_modeldata.class);
	
	// VECTOR
	public Vector getKeseluruhanPusaka = null;
	public Vector getTotalPenggunaaneTaPP = null;
	public Vector getTotalS8 = null;
	public Vector getTotalS17 = null;
	public Vector getTotalBelumSelesai = null;
	public Vector getTotalBayaranPerintah = null;
	public Vector getTotalBayaranCukai = null;
	public Vector getTotalBayaranBaitulmal = null;
	public Vector listUpk = null;
	
	private String SQL;
	
	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}
	
	public Vector getUpk() throws Exception
	{
		 Db db = null;
		 String sql = "";
		try{
			listUpk = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
				sql = "SELECT TBLRUJPEJABATJKPTG.NAMA_PEJABAT,TBLRUJPEJABATJKPTG.ID_NEGERI, "+
					  "( "+
					  "SELECT COUNT(TBLPFDFAIL.ID_FAIL) "+
					  "FROM TBLPFDFAIL, TBLPPKPERMOHONAN "+
					  "WHERE TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL "+
					  "AND TBLPFDFAIL.ID_STATUS NOT IN (999) "+
					  "AND TBLPFDFAIL.ID_SEKSYEN = 2 "+
					  "AND TBLPPKPERMOHONAN.ID_DAERAHMHN IN ( "+
					  "SELECT TBLRUJPEJABATURUSAN.ID_DAERAHURUS "+
					  "FROM TBLRUJPEJABATURUSAN "+
					  "WHERE TBLRUJPEJABATURUSAN.ID_SEKSYEN = 2 AND TBLRUJPEJABATURUSAN.ID_JENISPEJABAT = 22 "+
					  "AND TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG = TBLRUJPEJABATJKPTG.ID_PEJABATJKPTG "+
					  ") "+
					  ") AS BILANGAN_PERMOHONAN "+
					  "FROM TBLRUJPEJABATJKPTG "+
					  "WHERE TBLRUJPEJABATJKPTG.ID_JENISPEJABAT = 22 "+
					  "AND TBLRUJPEJABATJKPTG.ID_SEKSYEN = 2 ";
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
				 while (rs.next()) {
				    	h = new Hashtable();			    	
				    	h.put("bil", bil);		        
				    	h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
				    	h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT"));	
				    	h.put("BILANGAN_PERMOHONAN",rs.getString("BILANGAN_PERMOHONAN"));	
				    	listUpk.addElement(h);
				    	bil++;
				      	}      
					}
						finally{
							if(db != null)db.close();
						}
						
				return listUpk;
		
	}
	public Vector getListTotalKeseluruhanFail(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getKeseluruhanPusaka = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT C.ID_NEGERI,C.NAMA_NEGERI AS x, count(*) AS y ";					
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPKPERMOHONAN B, ";					
				sql += " TBLRUJNEGERI C ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
				sql += " AND A.ID_SEKSYEN = '2' ";
				sql += " AND A.ID_STATUS NOT IN ('999') ";
				sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT')";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}
					
				sql += " GROUP BY C.NAMA_NEGERI,C.ID_NEGERI ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPK :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
		    	getKeseluruhanPusaka.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getKeseluruhanPusaka;					
 }	
	
	public Vector getListTotalKeseluruhanFailAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getKeseluruhanPusaka = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT C.ID_NEGERI,C.ABBREV AS x, count(*) AS y ";					
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPKPERMOHONAN B, ";					
				sql += " TBLRUJNEGERI C ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
				sql += " AND A.ID_SEKSYEN = '2' ";
				sql += " AND A.ID_STATUS NOT IN ('999') ";
				sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT')";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}
					
				sql += " GROUP BY C.ABBREV,C.ID_NEGERI ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPK :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
		    	getKeseluruhanPusaka.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getKeseluruhanPusaka;					
}	
	
	public Vector getListTotalSeksyen8(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalS8 = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT C.ID_NEGERI,C.NAMA_NEGERI AS x, count(*) AS y ";					
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPKPERMOHONAN B, ";					
				sql += " TBLRUJNEGERI C ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
				sql += " AND A.ID_SEKSYEN = '2' ";
				sql += " AND B.SEKSYEN = '8' ";
				sql += " AND A.ID_STATUS NOT IN ('999') ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}
					
				sql += " GROUP BY C.NAMA_NEGERI,C.ID_NEGERI ";			
				
				setSQL(sql);
				myLogger.info("LAPORAN TOTAL SEKSYEN 8 :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));	
		    	
		    	h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
		    	
		    	myLogger.info(" NICE STATE NAME ::" +Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalS8.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalS8;					
}		

	public Vector getListTotalSeksyen8Abbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalS8 = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT C.ID_NEGERI,C.ABBREV AS x, count(*) AS y ";					
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPKPERMOHONAN B, ";					
				sql += " TBLRUJNEGERI C ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
				sql += " AND A.ID_SEKSYEN = '2' ";
				sql += " AND B.SEKSYEN = '8' ";
				sql += " AND A.ID_STATUS NOT IN ('999') ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}
					
				sql += " GROUP BY C.ABBREV,C.ID_NEGERI ";			
				
				setSQL(sql);
				myLogger.info("LAPORAN TOTAL SEKSYEN 8 :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));	
		    	
		    	h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
		    	
		    	myLogger.info(" NICE STATE NAME ::" +Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalS8.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalS8;					
}		
	

	
	
	public Vector getListTotalSeksyen17(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalS17 = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT C.NAMA_NEGERI AS x, count(*) AS y ";					
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPKPERMOHONAN B, ";					
				sql += " TBLRUJNEGERI C ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
				sql += " AND A.ID_SEKSYEN = '2' ";
				sql += " AND B.SEKSYEN = '17' ";
				sql += " AND A.ID_STATUS NOT IN ('999') ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}
					
				sql += " GROUP BY C.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL SEKSYEN 17 :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    	
		    	getTotalS17.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalS17;					
}	

	public Vector getListTotalSeksyen17Abbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalS17 = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT C.ABBREV AS x, count(*) AS y ";					
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPKPERMOHONAN B, ";					
				sql += " TBLRUJNEGERI C ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
				sql += " AND A.ID_SEKSYEN = '2' ";
				sql += " AND B.SEKSYEN = '17' ";
				sql += " AND A.ID_STATUS NOT IN ('999') ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}
					
				sql += " GROUP BY C.ABBREV ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL SEKSYEN 17 :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getTotalS17.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalS17;					
}	
	
	public Vector getListTotalKesBelumSelesai(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalBelumSelesai = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI, ";					
				sql += " NVL (MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),0) SELESAI, ";
				sql += " NVL (MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),0) XSELESAI ";					
				sql += " FROM ( ";					
				sql += " SELECT NAMA_NEGERI, SELESAI , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN ";		
				sql += " FROM ( ";
				sql += " SELECT N.NAMA_NEGERI,COUNT( P.ID_PERMOHONAN) AS SELESAI ";
				sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N ";
				sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";					
				sql += " AND P.ID_STATUS = '21' ";	
				sql += " AND F.ID_STATUS NOT IN ('999') ";
				sql += " AND P.SEKSYEN IN ('8','17') ";
				sql += " AND F.ID_SEKSYEN = '2' ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}								
				
				sql += " GROUP BY N.NAMA_NEGERI ";		
				sql += " UNION ";
				sql += " SELECT N.NAMA_NEGERI,COUNT( P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,tblrujnegeri N ";				
				sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";		
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND P.SEKSYEN IN ('8','17') ";
				sql += " AND P.ID_STATUS NOT IN ('21') ";
				sql += " AND F.ID_STATUS NOT IN ('999') ";
				sql += " AND F.ID_SEKSYEN = '2' ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}								
				
				sql += " GROUP BY N.NAMA_NEGERI) ";		
				sql += " )GROUP BY NAMA_NEGERI ";		
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PPK SELESAI & XSELESAI  :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("jumlah_permohonan_selesai", rs.getString("SELESAI")==null?"":rs.getString("SELESAI"));  
		    	h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI")==null?"":rs.getString("XSELESAI")); 
		    	
		    	getTotalBelumSelesai.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalBelumSelesai;					
}	
	
	public Vector getListTotalKesBelumSelesaiAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalBelumSelesai = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  ABBREV, ";					
				sql += " NVL (MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),0) SELESAI, ";
				sql += " NVL (MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),0) XSELESAI ";					
				sql += " FROM ( ";					
				sql += " SELECT ABBREV, SELESAI , ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN ";		
				sql += " FROM ( ";
				sql += " SELECT N.ABBREV,COUNT( P.ID_PERMOHONAN) AS SELESAI ";
				sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N ";
				sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";					
				sql += " AND P.ID_STATUS = '21' ";	
				sql += " AND F.ID_STATUS NOT IN ('999') ";
				sql += " AND P.SEKSYEN IN ('8','17') ";
				sql += " AND F.ID_SEKSYEN = '2' ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}								
				
				sql += " GROUP BY N.ABBREV ";		
				sql += " UNION ";
				sql += " SELECT N.ABBREV,COUNT( P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,tblrujnegeri N ";				
				sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";		
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND P.SEKSYEN IN ('8','17') ";
				sql += " AND P.ID_STATUS NOT IN ('21') ";
				sql += " AND F.ID_STATUS NOT IN ('999') ";
				sql += " AND F.ID_SEKSYEN = '2' ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulan +"' ";
					}
				}								
				
				sql += " GROUP BY N.ABBREV) ";		
				sql += " )GROUP BY ABBREV ";		
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PPK SELESAI & XSELESAI  :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("ABBREV")));
		    	h.put("jumlah_permohonan_selesai", rs.getString("SELESAI")==null?"":rs.getString("SELESAI"));  
		    	h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI")==null?"":rs.getString("XSELESAI")); 
		    	
		    	getTotalBelumSelesai.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalBelumSelesai;					
}	
	
	
	public Vector getTotalBayaranPerintah(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalBayaranPerintah = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT D.NAMA_NEGERI AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
				sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
				sql += " AND C.ID_JENISBAYARAN = '24' ";						
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" +id_bulan+"' ";
					}
				}					
				
				sql += " GROUP BY D.ID_NEGERI, D.NAMA_NEGERI ";	
				sql += " ORDER BY D.ID_NEGERI ";									
				
				setSQL(sql);
				
				myLogger.info("LAPORAN JUM BYRAN PERINTAH :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);	
		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalBayaranPerintah.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalBayaranPerintah;					
}			

	public Vector getTotalBayaranPerintahAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalBayaranPerintah = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT D.ABBREV AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
				sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
				sql += " AND C.ID_JENISBAYARAN = '24' ";						
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" +id_bulan+"' ";
					}
				}					
				
				sql += " GROUP BY D.ID_NEGERI, D.ABBREV ";	
				sql += " ORDER BY D.ID_NEGERI ";									
				
				setSQL(sql);
				
				myLogger.info("LAPORAN JUM BYRAN PERINTAH :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);	
		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalBayaranPerintah.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalBayaranPerintah;					
}			
	
	
	public Vector getTotalBayaranCukai(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalBayaranCukai = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT D.NAMA_NEGERI AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
				sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
				sql += " AND C.ID_JENISBAYARAN = '26' ";						
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" +id_bulan+"' ";
					}
				}					
				
				sql += " GROUP BY D.ID_NEGERI, D.NAMA_NEGERI ";	
				sql += " ORDER BY D.ID_NEGERI ";									
				
				setSQL(sql);
				
				myLogger.info("LAPORAN JUM BYRAN CUKAI :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);	
		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalBayaranCukai.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getTotalBayaranCukai;					
}				
	
	public Vector getTotalBayaranCukaiAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalBayaranCukai = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT D.ABBREV AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
				sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
				sql += " AND C.ID_JENISBAYARAN = '26' ";						
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" +id_bulan+"' ";
					}
				}					
				
				sql += " GROUP BY D.ID_NEGERI, D.ABBREV ";	
				sql += " ORDER BY D.ID_NEGERI ";									
				
				setSQL(sql);
				
				myLogger.info("LAPORAN JUM BYRAN CUKAI :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);	
		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalBayaranCukai.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getTotalBayaranCukai;					
}				
	
	public Vector getTotalBayaranBaitulmal(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			getTotalBayaranBaitulmal = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT D.NAMA_NEGERI AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
				sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
				sql += " AND C.ID_JENISBAYARAN = '29' ";						
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" +id_bulan+"' ";
					}
				}					
				
				sql += " GROUP BY D.ID_NEGERI, D.NAMA_NEGERI ";	
				sql += " ORDER BY D.ID_NEGERI ";									
				
				setSQL(sql);
				
				myLogger.info("LAPORAN JUM BYRAN CUKAI :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);	
		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalBayaranBaitulmal.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getTotalBayaranBaitulmal;					
}					
	
	public Vector getTotalBayaranBaitulmalAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			getTotalBayaranBaitulmal = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT D.ABBREV AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
				sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
				sql += " AND C.ID_JENISBAYARAN = '29' ";						
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" +id_bulan+"' ";
					}
				}					
				
				sql += " GROUP BY D.ID_NEGERI, D.ABBREV ";	
				sql += " ORDER BY D.ID_NEGERI ";									
				
				setSQL(sql);
				
				myLogger.info("LAPORAN JUM BYRAN CUKAI :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);	
		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getTotalBayaranBaitulmal.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getTotalBayaranBaitulmal;					
}					
	
	
	// GENERATE BAR/PIE CHART
	
	public  String generateXML(){
		
		String xml="<chart caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan' numberPrefix=''>";
		Db db = null;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){
				xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";
			}
			xml =xml+"</chart>";
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}

		
		return xml;
	}
	
	public String generateXML_MSColumn2D(String id_tahun,String id_bulan) {
		
		String xml="<chart palette='2' caption='Status Permohonan Pusaka' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0'>";
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			sql =  " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N ";
			sql += " WHERE N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND NVL(P.ID_STATUS,0) = '21' ";
			sql += " AND p.seksyen in ('8','17') ";
			
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" +id_bulan+"' ";
				}
			}		
	
			sql += " GROUP BY N.ABBREV ";	
			sql += " ORDER BY 1 ";

//			myLogger.info("SQL STATUS PENGAMBILAN(SELESAI) :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Selesai' color='AFD8F8' showValues='1'>";
			while(rs.next()){
				//xml =xml+"<set label='"+rs.getString("x")+"' value='"+rs.getString("y")+"' />";
				categories = categories+"<category label='"+rs.getString("abbrev")+"'/>";
				dataset = dataset+"<set value='"+rs.getString("SELESAI")+"'/>";
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			
			//TAK SELESAI
			
			sql  = " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS TAKSELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N ";
			sql += " WHERE N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_STATUS <> '21' ";
			sql += " AND p.seksyen in ('8','17') ";
			
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" +id_bulan+"' ";
				}
			}			
			
			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";
			
//			myLogger.info("SQL STATUS PPK(TAKSELESAI) :: "+sql);
			
			rs =stat.executeQuery(sql);
			dataset2 = "<dataset seriesName='Tidak Selesai' color='F6BD0F' showValues='1'>";
			while(rs.next()){
				dataset2 = dataset2+"<set value='"+rs.getString("TAKSELESAI")+"'/>";
			}	
			dataset2 = dataset2+"</dataset>";
			
			
			xml =xml+categories+dataset+dataset2+"</chart>";
						
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}
			
	
}// CLOSE MAIN CLASS
