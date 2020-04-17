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

public class PPT_modeldata extends EkptgCache implements Serializable {
	static Logger myLogger = Logger.getLogger(PPT_modeldata.class);
	
	// VECTOR
	public Vector getHakmilik = null;
	public Vector getBantahan = null;
	public Vector getPermintaanUkur = null;
	public Vector getAfidavit = null;
	public Vector getPampasan = null;
	public Vector getStatus = null;
	public Vector getPampasanAmanahRaya = null;
	public Vector getCajBayaranLewat = null;
	public Vector getTotalTanahRizab = null;
	public Vector getStatusBantahan = null;
	public Vector getStatusPenggunaan = null;
	public Vector getStatusHTP = null;
	public Vector getStatusRizab = null;
	public Vector getStatusPHP = null;
	public Vector getTotalPenggunaaneTaPP = null;
	public Vector getKodCawangan= null;
	
	private String SQL;
	
	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}

	public Vector getListTotalProjectKementerian(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{ //EDIT BY YATI 18/12/2017
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(C.NAMA_NEGERI,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK, C.ID_NEGERI ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, ";        
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) ";         
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1)	";
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTX :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
 }
	
	public Vector getListTotalPermohonanUnit(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(E.NAMA_PEJABAT,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK, C.ID_NEGERI ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1) " ;
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTY :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_pejabat", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}

	public Vector getListTotalPermohonanUnitKod(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(C.KOD_NEGERI,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK, C.ID_NEGERI ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1) " ;
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTZ :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("kod_negeri", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	

	
	public Vector getListTotalPermohonanUnitLot(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(E.NAMA_PEJABAT,'TIADA MAKLUMAT') AS x, B.ID_PERMOHONAN, D.ID_HAKMILIK ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTA :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_pejabat", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	public Vector getListTotalPermohonanUnitLotKod(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(C.KOD_NEGERI,'TIADA MAKLUMAT') AS x, B.ID_PERMOHONAN, D.ID_HAKMILIK,C.ID_NEGERI ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, "; 
				sql += " TBLRUJPEJABATJKPTG E, "; 
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) "; 
				sql += " AND A.ID_NEGERI = E.ID_NEGERI"; 
				sql += " AND E.ID_SEKSYEN = 1"; 
				sql += " AND E.ID_JENISPEJABAT =22"; 
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1) " ;
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTB :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("kod_negeri", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	public Vector getKodCawangan() throws Exception {
		Db db = null;
		String sql = "";
		try{
			getKodCawangan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			
			sql =  " SELECT distinct(RKME.KOD_NEGERI) AS KOD, RK.NAMA_PEJABAT AS CAWANGAN " +
					" FROM TBLRUJPEJABATJKPTG RK,TBLRUJNEGERI RKME " +
					" WHERE " +
					" RK.ID_NEGERI = RKME.ID_NEGERI AND RK.ID_SEKSYEN = '1' AND RK.ID_JENISPEJABAT=22" +
					" ORDER BY RKME.KOD_NEGERI" +
					"";
			myLogger.info(sql);
//				setSQL(sql);				
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;				
			while (rs.next()) {
				h = new Hashtable();	        
		    	h.put("kod", Utils.isNull(rs.getString("KOD")));
		    	h.put("cawangan", Utils.isNull(rs.getString("CAWANGAN")));		    	
		    	getKodCawangan.addElement(h);
			}      
		}finally{
			if(db != null)db.close();
		}	
		return getKodCawangan;	
	
	}	
	
	
	public Vector getListTotalProjectKementerianAbbrev(String socTahun,String socBulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT x,COUNT(DISTINCT ID_PERMOHONAN)as y,COUNT(ID_HAKMILIK) as z FROM ( ";
				sql += " SELECT NVL(C.ABBREV,'TIADA MAKLUMAT') AS x,B.ID_PERMOHONAN, D.ID_HAKMILIK, C.ID_NEGERI ";
				sql += " FROM TBLPFDFAIL A, ";
				sql += " TBLPPTPERMOHONAN B, ";                    
				sql += " TBLRUJNEGERI C, ";
				sql += " TBLPPTHAKMILIK D, ";               
				sql += " TBLRUJSTATUS S, ";        
				sql += " USERS U, ";
				sql += " USERS_INTERNAL UI ";                 
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
				sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) ";         
				sql += " AND B.ID_MASUK = U.USER_ID(+) ";                
				sql += " AND U.USER_ID = UI.USER_ID(+) ";        
				sql += " AND S.ID_STATUS = B.ID_STATUS(+) ";  
				sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
				sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)	";
				sql += " AND C.ID_NEGERI NOT IN (1) ";
				
	    		// TAHUN 
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
					}
				}
				
	    		// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
					}
				}
					
				sql += " ORDER BY x ";
				sql += " ) ";
				sql += " GROUP BY x ";			
				
				setSQL(sql);
				//
				myLogger.info("LAPORAN TOTAL KESELURUHAN PPTS :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}
	
	public Vector getListTotalSiasatan(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(E.NAMA_NEGERI,'TIADA NEGERI') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTSIASATAN D,TBLRUJNEGERI E ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK ";
				sql += " AND D.ID_NEGERI = A.ID_NEGERI ";		
				sql += " AND D.ID_NEGERI = E.ID_NEGERI ";				
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + id_bulan +"' ";
					}
				}
					
				sql += " GROUP BY E.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;					
}	
	

	
	
	public Vector getListTotalBantahan(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(F.NAMA_NEGERI,'TIADA MAKLUMAT') AS x, COUNT (*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN D, ";					
				sql += " TBLPPTHAKMILIKPB E,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK(+) = D.ID_HAKMILIK ";
				sql += " AND E.ID_HAKMILIKPB(+) = D.ID_HAKMILIKPB ";
				sql += " AND D.ID_NEGERI = F.ID_NEGERI(+) ";	
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY F.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL BANTAHAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    	
		    	getBantahan.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getBantahan;					
}	

	public Vector getListTotalBantahanAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(F.ABBREV,'TIADA MAKLUMAT') AS x, COUNT (*) AS y";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN D, ";					
				sql += " TBLPPTHAKMILIKPB E,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK(+) = D.ID_HAKMILIK ";
				sql += " AND E.ID_HAKMILIKPB(+) = D.ID_HAKMILIKPB ";
				sql += " AND D.ID_NEGERI = F.ID_NEGERI(+) ";	
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY F.ABBREV ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL BANTAHAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getBantahan.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getBantahan;					
	}
	
	public Vector getListTotalPU(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPermintaanUkur = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(E.NAMA_NEGERI,'TIADA NEGERI') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, ";
				sql += " TBLPPTPERMINTAANUKUR D,TBLRUJNEGERI E, ";					
				sql += " TBLPPTHAKMILIKBORANGK F,TBLPPTBORANGK G ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND A.ID_NEGERI = E.ID_NEGERI(+) ";
				sql += " AND C.ID_HAKMILIK = F.ID_HAKMILIK AND F.ID_BORANGK = G.ID_BORANGK ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY E.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PU1 :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    	
		    	getPermintaanUkur.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getPermintaanUkur;					
}

	public Vector getListTotalPUAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPermintaanUkur = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(E.ABBREV,'TIADA NEGERI') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, ";
				sql += " TBLPPTPERMINTAANUKUR D,TBLRUJNEGERI E, ";					
				sql += " TBLPPTHAKMILIKBORANGK F,TBLPPTBORANGK G ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND A.ID_NEGERI = E.ID_NEGERI(+) ";
				sql += " AND C.ID_HAKMILIK = F.ID_HAKMILIK AND F.ID_BORANGK = G.ID_BORANGK ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY E.ABBREV ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PU2 :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getPermintaanUkur.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getPermintaanUkur;					
}
	

	public Vector getListTotalAfidavit(String id_tahun,String id_bulan) throws Exception {
		 Db db = null;
		 String sql = "";
		try{
				getAfidavit = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(F.NAMA_NEGERI,'TIADA NEGERI') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";					
				sql += " TBLPPTAWARD E,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIKPB ";
				sql += " AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
				sql += " AND A.ID_NEGERI = F.ID_NEGERI ";

	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY F.NAMA_NEGERI ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL AFIDAVIT :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    	
		    	getAfidavit.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getAfidavit;					
	}	
	
	public Vector getListTotalAfidavitAbbrev(String id_tahun,String id_bulan) throws Exception {
		 Db db = null;
		 String sql = "";
		try{
				getAfidavit = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT NVL(F.ABBREV,'TIADA NEGERI') AS x, COUNT (*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";					
				sql += " TBLPPTAWARD E,TBLRUJNEGERI F ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";		
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIKPB ";
				sql += " AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
				sql += " AND A.ID_NEGERI = F.ID_NEGERI ";

	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
					
				sql += " GROUP BY F.ABBREV ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL AFIDAVIT :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    	
		    	getAfidavit.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getAfidavit;					
	}	
	

	public Vector getListTotalJumlahPampasan(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.NAMA_NEGERI  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASAN :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getPampasan.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasan;					
}	
	

	public Vector getListTotalJumlahPampasanAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.ABBREV AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASAN :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getPampasan.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasan;					
}	
	

	public Vector getListStatusPengambilan(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatus = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI, ";					
				sql += " MAX(INITCAP(DECODE ( rn , 1, SELESAI ))) SELESAI, ";
				sql += " MAX(INITCAP(DECODE ( rn , 2, SELESAI ))) XSELESAI ";					
				sql += " FROM (   ";					
				sql += " SELECT NAMA_NEGERI, SELESAI , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN   ";		
				sql += " FROM ( ";
				sql += " SELECT N.NAMA_NEGERI,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";					
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";		
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";				
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";	
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";					
				sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";				
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL";				
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
						
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				sql += " GROUP BY N.NAMA_NEGERI ";
				sql += " UNION ";				
				sql += " SELECT N.NAMA_NEGERI,COUNT(DISTINCT P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,tblrujnegeri N ";
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";		
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";				
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";		
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
				
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				
				sql += " GROUP BY N.NAMA_NEGERI) ";		
				sql += " )GROUP BY NAMA_NEGERI ";		
				//sql += " GROUP BY N.NAMA_NEGERI ORDER BY 1 ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS PENGAMBILAN :: "+sql);
				
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
		    	
		    	getStatus.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatus;					
}	
	
	public Vector getListStatusPengambilanAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatus = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  ABBREV, ";					
				sql += " MAX(INITCAP(DECODE ( rn , 1, SELESAI ))) SELESAI, ";
				sql += " MAX(INITCAP(DECODE ( rn , 2, SELESAI ))) XSELESAI ";					
				sql += " FROM (   ";					
				sql += " SELECT ABBREV, SELESAI , ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN   ";		
				sql += " FROM ( ";
				sql += " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";					
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";		
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";				
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";	
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";					
				sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";				
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL";				
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
						
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				sql += " GROUP BY N.ABBREV ";
				sql += " UNION ";				
				sql += " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H,tblrujnegeri N ";
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";		
				sql += " AND N.ID_NEGERI = F.ID_NEGERI ";				
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
				sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";		
				sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
				
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}
				
				
				sql += " GROUP BY N.ABBREV) ";		
				sql += " )GROUP BY ABBREV ";		
				//sql += " GROUP BY N.NAMA_NEGERI ORDER BY 1 ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS PENGAMBILAN :: "+sql);
				
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
		    	
		    	getStatus.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatus;					
}	
	

	public Vector getListCajBayaranLewat(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getCajBayaranLewat = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI AS x, NVL(SUM (A.DENDA_LEWAT),0) AS y ";					
				sql += " FROM TBLPPTPERMOHONAN P,TBLPPTHAKMILIK H,  ";
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTBAYARAN A, ";					
				sql += " TBLPFDFAIL F,TBLRUJNEGERI N ";					
				sql += " WHERE F.ID_FAIL = P.ID_FAIL ";		
				sql += " AND P.ID_PERMOHONAN = H.ID_PERMOHONAN ";
				sql += " AND H.ID_HAKMILIK = HPB.ID_HAKMILIK ";					
				sql += " AND HPB.ID_HAKMILIKPB = A.ID_HAKMILIKPB ";					
				sql += " AND F.ID_NEGERI = N.ID_NEGERI ";	
							
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.NAMA_NEGERI  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN CAJ BYR LEWAT :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);			    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getCajBayaranLewat.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getCajBayaranLewat;					
}		
	
	public Vector getListCajBayaranLewatAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getCajBayaranLewat = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.ABBREV AS x, NVL(SUM (A.DENDA_LEWAT),0) AS y ";					
				sql += " FROM TBLPPTPERMOHONAN P,TBLPPTHAKMILIK H,  ";
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTBAYARAN A, ";					
				sql += " TBLPFDFAIL F,TBLRUJNEGERI N ";					
				sql += " WHERE F.ID_FAIL = P.ID_FAIL ";		
				sql += " AND P.ID_PERMOHONAN = H.ID_PERMOHONAN ";
				sql += " AND H.ID_HAKMILIK = HPB.ID_HAKMILIK ";					
				sql += " AND HPB.ID_HAKMILIKPB = A.ID_HAKMILIKPB ";					
				sql += " AND F.ID_NEGERI = N.ID_NEGERI ";	
							
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN CAJ BYR LEWAT :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);			    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getCajBayaranLewat.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getCajBayaranLewat;					
}		

	
	public Vector getListJumlahPampasanAmanahRayaBerhad(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasanAmanahRaya = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.NAMA_NEGERI AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				sql += " AND AW.STATUS_PENERIMA = '3' ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.NAMA_NEGERI  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASAN :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getPampasanAmanahRaya.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasanAmanahRaya;					
}			
	
	public Vector getListJumlahPampasanAmanahRayaBerhadAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getPampasanAmanahRaya = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT N.ABBREV AS x, ";					
				sql += " NVL(SUM(AW.BAYAR_PAMPASAN),0) AS y ";
				sql += " FROM TBLPPTPERMOHONAN P, ";					
				sql += " TBLPFDFAIL F,TBLPPTHAKMILIK H, ";					
				sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K, ";		
				sql += " TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB, ";
				sql += " TBLPPTSIASATAN S,TBLPPTAWARD AW,TBLRUJNEGERI N ";					
				sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";					
				sql += " AND S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = h.ID_hakmilik ";		
				sql += " AND S.ID_SIASATAN = aw.ID_SIASATAN AND S.ID_PENARIKANBALIK IS NULL ";
				sql += " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = pb.ID_PIHAKBERKEPENTINGAN ";					
				sql += " AND P.ID_FAIL = f.ID_FAIL AND F.ID_NEGERI = N.ID_NEGERI ";					
				sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK AND bk.ID_BORANGK = K.ID_BORANGK ";		
				sql += " AND bk.ID_HAKMILIKBORANGK IS NOT NULL ";
				sql += " AND AW.STATUS_PENERIMA = '3' ";
				sql += " AND N.ID_NEGERI NOT IN (1) ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}				
				
				
				sql += " GROUP BY N.ABBREV  ";			
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL PAMPASAN :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        		    	
//		    	h.put("jumlah_permohonan", rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
		    	h.put("jumlah_permohonan",rs.getDouble("y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
		    	
		    	getPampasanAmanahRaya.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getPampasanAmanahRaya;					
}			
	
	public Vector getListTanahRizab(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getTotalTanahRizab = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT D.NAMA_NEGERI AS x, count(*) AS y ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLRUJNEGERI D ";					
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";					
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";		
				sql += " AND A.ID_NEGERI = D.ID_NEGERI ";
				sql += " AND NVL(C.FLAG_REZAB,0)  = '1' ";			

	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + id_bulan +"' ";
					}
				}
					
				sql += " GROUP BY D.NAMA_NEGERI ";			
				sql += " ORDER BY 1 ";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN TOTAL TANAH RIZAB :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();	
		    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));	
		    	
		    	getTotalTanahRizab.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getTotalTanahRizab;					
}			
	
	
	public Vector getListStatusBantahanMahkamah(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, BANTAHAN ))),'0') BANTAHAN_PB, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, BANTAHAN ))),'0') BANTAHAN_AP ";					
				sql += " FROM (  ";					
				sql += " SELECT NAMA_NEGERI, REPLACE(REPLACE(BANTAHAN,'A'),'B') BANTAHAN  , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN ";		
				sql += " FROM (  ";
				sql += " SELECT N.NAMA_NEGERI, 'A' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN  ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";		
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";				
				sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N ";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";	
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";					
				sql += " AND A.ID_NEGERI = N.ID_NEGERI ";									
				sql += " GROUP BY N.NAMA_NEGERI ";
				sql += " UNION  ";				
				sql += " SELECT N.NAMA_NEGERI, 'B' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,  ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN E,TBLRUJNEGERI N ";		
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";				
				sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK AND A.ID_NEGERI = N.ID_NEGERI ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}		
				
				sql += " GROUP BY N.NAMA_NEGERI)  ) ";
				sql += " GROUP BY NAMA_NEGERI ";				
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS BANTAHAN AP :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("jumlah_permohonan_bantahanpb", rs.getString("BANTAHAN_PB")==null?"":rs.getString("BANTAHAN_PB"));  
		    	h.put("jumlah_permohonan_bantahanap", rs.getString("BANTAHAN_AP")==null?"":rs.getString("BANTAHAN_AP")); 
		    	
		    	getStatusBantahan.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusBantahan;					
}		
	
	public Vector getListStatusPenggunaaneTaPP(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
			getStatusPenggunaan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  NAMA_NEGERI, ";					
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 1, BIL ))),0) SPT,   ";
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 2, BIL ))),0) SPPK,  ";
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 3, BIL ))),0) SHTP,  ";	
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 4, BIL ))),0) SPHP,  ";	
				sql += " NVL (MAX(INITCAP(DECODE ( RN , 5, BIL ))),0) SPF    ";	    
				sql += " FROM (  ";					
				sql += " SELECT NAMA_NEGERI, BIL , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN  ";	
				sql += " FROM (  ";
				sql += " SELECT E.NAMA_NEGERI, COUNT(E.ID_NEGERI) AS BIL ";
				sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
				sql += " WHERE A.USER_NAME = B.USER_LOGIN ";
				sql += " AND B.USER_ID = C.USER_ID ";
				sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";
				sql += " AND C.ID_NEGERI = E.ID_NEGERI "; 
				sql += " AND D.ID_SEKSYEN=1 ";
				
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI ";
				sql += " UNION  ";
				sql += " SELECT E.NAMA_NEGERI, COUNT(E.ID_NEGERI) AS BIL ";
				sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
				sql += " WHERE A.USER_NAME = B.USER_LOGIN ";
				sql += " AND B.USER_ID = C.USER_ID ";
				sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";
				sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
				sql += " AND D.ID_SEKSYEN=2 ";
				
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI ";	
				sql += " UNION  ";	
				sql += " SELECT E.NAMA_NEGERI, COUNT(E.ID_NEGERI) AS BIL ";	
				sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";	
				sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
				sql += " AND B.USER_ID = C.USER_ID "; 	
				sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
				sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
				sql += " AND D.ID_SEKSYEN=3 ";
				
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI ";	
				sql += " UNION  ";	
				sql += " SELECT E.NAMA_NEGERI, COUNT(E.ID_NEGERI) AS BIL ";	
				sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
				sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
				sql += " AND B.USER_ID = C.USER_ID ";	
				sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
				sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
				sql += " AND D.ID_SEKSYEN=4 ";
				
				// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}
				
				sql += " GROUP BY E.NAMA_NEGERI ";	
				sql += " UNION  ";	
				sql += " SELECT E.NAMA_NEGERI, COUNT(E.ID_NEGERI) AS BIL "; 	
				sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";	
				sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
				sql += " AND B.USER_ID = C.USER_ID ";	
				sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
				sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
				sql += " AND D.ID_SEKSYEN=5 ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
					}
				}		
				
				sql += " GROUP BY E.NAMA_NEGERI)  ) ";
				sql += " GROUP BY NAMA_NEGERI ";				
				
				setSQL(sql);
				
				myLogger.info("LAPORAN PENGGUNAAN ETAPP :: "+sql);
				System.out.println("LAPORAN PENGGUNAAN ETAPP :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("dari_spt", rs.getString("SPT")==null?"":rs.getString("SPT"));  
		    	h.put("dari_sppk", rs.getString("SPPK")==null?"":rs.getString("SPPK")); 
		    	h.put("dari_shtp", rs.getString("SHTP")==null?"":rs.getString("SHTP")); 
		    	h.put("dari_sphp", rs.getString("SPHP")==null?"":rs.getString("SPHP")); 
		    	
		    	getStatusPenggunaan.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusPenggunaan;					
}		
	
	public Vector getListStatusBantahanMahkamahAbbrev(String id_tahun,String id_bulan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT  ABBREV, ";					
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, BANTAHAN ))),'0') BANTAHAN_PB, ";
				sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, BANTAHAN ))),'0') BANTAHAN_AP ";					
				sql += " FROM (  ";					
				sql += " SELECT ABBREV, REPLACE(REPLACE(BANTAHAN,'A'),'B') BANTAHAN  , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN ";		
				sql += " FROM (  ";
				sql += " SELECT N.ABBREV, 'A' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN  ";					
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";		
				sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";				
				sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N ";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";	
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";					
				sql += " AND A.ID_NEGERI = N.ID_NEGERI ";									
				sql += " GROUP BY N.ABBREV ";
				sql += " UNION  ";				
				sql += " SELECT N.ABBREV, 'B' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,  ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN E,TBLRUJNEGERI N ";		
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";				
				sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK AND A.ID_NEGERI = N.ID_NEGERI ";
				
	    		// TAHUN 
				if (id_tahun != null) {
					if (!id_tahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
					}
				}
				
	    		// BULAN
				if (id_bulan != null) {
					if (!id_bulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
					}
				}		
				
				sql += " GROUP BY N.ABBREV)  ) ";
				sql += " GROUP BY ABBREV ";				
				
				setSQL(sql);
				
				myLogger.info("LAPORAN STATUS BANTAHAN AP :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
//		    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("ABBREV")));
		    	h.put("jumlah_permohonan_bantahanpb", rs.getString("BANTAHAN_PB")==null?"":rs.getString("BANTAHAN_PB"));  
		    	h.put("jumlah_permohonan_bantahanap", rs.getString("BANTAHAN_AP")==null?"":rs.getString("BANTAHAN_AP")); 
		    	
		    	getStatusBantahan.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusBantahan;					
}		
	
	
	
	// GENERATE BAR/PIE CHART
	
	public  String generateXML(){
		
		System.out.println(getSQL());
		String xml="<chart caption='Laporan Jumlah Pengambilan Tanah' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Hakmilik/Lot' numberPrefix=''>";
		Db db = null;
		//String xname = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			}
			xml =xml+"</chart>";
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}

		System.out.println(xml);
		return xml;
	}	

	public  String generateXMLCawanganLot(){
		
		System.out.println(getSQL());
		String xml="<chart caption='Laporan Jumlah Pengambilan Tanah Mengikut Cawangan dan Lot' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan' numberPrefix=''>";
		Db db = null;
		//String xname = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("z")+"' />";				
			}
			xml =xml+"</chart>";
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}

		System.out.println(xml);
		return xml;
	}	

	
	//add by faresh
public  String generateXMLfaresh(){
		
		System.out.println(getSQL());
		//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
		//"useRoundEdges='1' legendBorderAlpha='0'>";
		
		String xml="<chart palette='2'  xAxisName='Negeri' caption='Status Jumlah Permohonan dan Jumlah Lot' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
		Db db = null;
		String xname = "";
		String categories = "";
		String dataset1="" ;
		String dataset2="" ;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			categories = "<categories>";
			dataset1 = "<dataset seriesName='Jumlah Permohonan' color='AFD8F8' showValues='1'>";
			dataset2 = "<dataset seriesName='Jumlah Hakmilik' color='F6BD0F' showValues='1'>";
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			
				categories = categories+"<category label='"+rs.getString("x")+"'/>";
				dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"'/>";
				dataset2 = dataset2+"<set value='"+rs.getString("z")+"'/>";
			}
			categories = categories+"</categories>";
			dataset1 = dataset1+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			xml =xml+categories+dataset1+dataset2+"</chart>";
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}
        
		System.out.println(xml);
		return xml;
	}	
public  String generateXMLfaresh2(){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart palette='2'  xAxisName='Negeri' caption='Status Jumlah Permohonan dan Jumlah Lot' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
				"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
				"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
				"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
	     
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Jumlah Permohonan' color='AFD8F8' showValues='1'>";
		dataset2 = "<dataset seriesName='Jumlah Hakmilik' color='F6BD0F' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("x")+"'/>";
			dataset1 = dataset1+"<set label='"+rs.getString("x")+"' value='"+rs.getString("y")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("z")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	}catch(Exception e){
		e.printStackTrace();
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	

public  String generateXMLfaresh3(){
	
	System.out.println(getSQL());
	//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
	//"useRoundEdges='1' legendBorderAlpha='0'>";
	
	String xml="<chart bgColor='E9E9E9' outCnvBaseFontColor='666666'  xAxisName='Negeri' caption='Status Jumlah Permohonan dan Jumlah Lot' " +
				"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
				"plotFillAlpha='50' numVDivLines='10' showAlternateVGridColor='1' AlternateVGridColor='e1f5ff'  " +
				"divLineColor='e1f5ff' vdivLineColor='e1f5ff' baseFontColor='666666' canvasBorderThickness='1'  " +
				"showPlotBorder='1' plotBorderThickness='0' formatNumberScale='0' >";
	  
	
	Db db = null;
	String xname = "";
	String categories = "";
	String dataset1="" ;
	String dataset2="" ;
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		categories = "<categories>";
		dataset1 = "<dataset seriesName='Jumlah Permohonan' color='C8A1D1'  plotBorderColor='C8A1D1' showValues='1'>";
		dataset2 = "<dataset seriesName='Jumlah Hakmilik' color='B1D1DC' plotBorderColor='B1D1DC' showValues='1'>";
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
		
			categories = categories+"<category label='"+rs.getString("x")+"'/>";
			dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"'/>";
			dataset2 = dataset2+"<set value='"+rs.getString("z")+"'/>";
		}
		categories = categories+"</categories>";
		dataset1 = dataset1+"</dataset>";
		dataset2 = dataset2+"</dataset>";
		xml =xml+categories+dataset1+dataset2+"</chart>";
		
	}catch(Exception e){
		e.printStackTrace();
	} finally {
		if (db!=null) db.close();
	}
    
	System.out.println(xml);
	return xml;
}	

public  String generateXMLJumlahHakmilik(){
	
	System.out.println(getSQL());
	String xml="<chart caption='Laporan Jumlah Hakmilik/Lot' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan' numberPrefix=''>";
	Db db = null;
	//String xname = "";
	try{
		db =  new Db();
		Connection conn = db.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs =stat.executeQuery(getSQL());
		while(rs.next()){				
			//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
			xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("z")+"' />";				
		}
		xml =xml+"</chart>";
		
	}catch(Exception e){
		e.printStackTrace();
	} finally {
		if (db!=null) db.close();
	}

	System.out.println(xml);
	return xml;
}	
	//end add


	public String generateXML_MSColumn2D(String id_tahun,String id_bulan) {
	
		String xml="<chart palette='2' caption='Status Pengambilan' " +
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
			sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H, ";
			sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";
			sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";
			sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";
			sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";
			sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";
			sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
			sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";
			sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
			
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";			

			myLogger.info("SQL STATUS PENGAMBILAN(SELESAI) :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Selesai' color='AFD8F8' showValues='0'>";
			while(rs.next()){
				//xml =xml+"<set label='"+rs.getString("x")+"' value='"+rs.getString("y")+"' />";
				categories = categories+"<category label='"+rs.getString("abbrev")+"'/>";
				dataset = dataset+"<set value='"+rs.getString("SELESAI")+"'/>";
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			
			//TAK SELESAI
			
			sql  = " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS TAKSELESAI ";
			sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H, ";
			sql += " tblrujnegeri N ";
			sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";
			sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
			sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";
			sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";

    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";
			
			myLogger.info("SQL STATUS PENGAMBILAN(TAKSELESAI) :: "+sql);
			
			rs =stat.executeQuery(sql);
			dataset2 = "<dataset seriesName='Tidak Selesai' color='F6BD0F' showValues='1'>";
			while(rs.next()){
				dataset2 = dataset2+"<set value='"+rs.getString("TAKSELESAI")+"'/>";
			}	
			dataset2 = dataset2+"</dataset>";
			
			
			xml =xml+categories+dataset+dataset2+"</chart>";
			
			System.out.println(xml);
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}
	
	public String generateXML_Status_Pengambilan(String id_tahun,String id_bulan) {
		
			
		String xml="<chart palette='2'  xAxisName='Negeri' caption='Pengambilan' " +
		"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
		"lineThickness='1' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1'  " +
		"showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40'  " +
		"labelStep='1' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' " +
		"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
		
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
			sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H, ";
			sql += " TBLPPTHAKMILIKBORANGK BK,TBLPPTBORANGK K,tblrujnegeri N ";
			sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";
			sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND H.ID_HAKMILIK = BK.ID_HAKMILIK ";
			sql += " AND BK.ID_BORANGK = K.ID_BORANGK ";
			sql += " AND BK.ID_HAKMILIKBORANGK IS NOT NULL ";
			sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
			sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";
			sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";
			
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";			

			myLogger.info("SQL STATUS PENGAMBILAN(SELESAI) :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Selesai' color='AFD8F8' showValues='0'>";
			while(rs.next()){
				//xml =xml+"<set label='"+rs.getString("x")+"' value='"+rs.getString("y")+"' />";
				categories = categories+"<category label='"+rs.getString("abbrev")+"'/>";
				dataset = dataset+"<set value='"+rs.getString("SELESAI")+"'/>";
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			
			//TAK SELESAI
			
			sql  = " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS TAKSELESAI ";
			sql += " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTHAKMILIK H, ";
			sql += " tblrujnegeri N ";
			sql += " WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN ";
			sql += " AND N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND H.FLAG_PEMBATALAN_KESELURUHAN IS NULL ";
			sql += " AND H.FLAG_PENARIKAN_KESELURUHAN IS NULL ";
			sql += " AND F.ID_NEGERI NOT IN (15,0,13,12,17,16) ";

    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";
			
			myLogger.info("SQL STATUS PENGAMBILAN(TAKSELESAI) :: "+sql);
			
			rs =stat.executeQuery(sql);
			dataset2 = "<dataset seriesName='Tidak Selesai' color='F6BD0F' showValues='1'>";
			while(rs.next()){
				dataset2 = dataset2+"<set value='"+rs.getString("TAKSELESAI")+"'/>";
			}	
			dataset2 = dataset2+"</dataset>";
			
			
			xml =xml+categories+dataset+dataset2+"</chart>";
			
			System.out.println(xml);
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}

	
	public String generateXML_MSColumn2D_Bantahan(String id_tahun,String id_bulan) {
		
		String xml="<chart palette='2' caption='Laporan Prestasi Bantahan Mahkamah ' " +
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
			
			//BANTAHAN 
			sql =  " SELECT ABBREV, ";
			sql += " NVL(MAX(INITCAP(DECODE ( rn , 1, BANTAHAN ))),'0') BANTAHAN_PB, ";
			sql += " NVL(MAX(INITCAP(DECODE ( rn , 2, BANTAHAN ))),'0') BANTAHAN_AP  ";
			sql += " FROM (  ";
			sql += " SELECT ABBREV, REPLACE(REPLACE(BANTAHAN,'A'),'B') BANTAHAN  , ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN ";
			sql += " FROM (  ";
			sql += " SELECT N.ABBREV, 'A' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
			sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
			sql += " TBLPPTHAKMILIK C,TBLPPTHAKMILIKPB D, ";
			sql += " TBLPPTBANTAHAN E,TBLRUJNEGERI N  ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK AND D.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
			sql += " AND A.ID_NEGERI = N.ID_NEGERI ";				
			sql += " GROUP BY N.ABBREV ";
			sql += " UNION ";
			sql += " SELECT N.ABBREV, 'B' || COUNT(DISTINCT B.ID_PERMOHONAN) AS BANTAHAN ";
			sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,  ";			
			sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN E,TBLRUJNEGERI N  ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK AND A.ID_NEGERI = N.ID_NEGERI ";
			
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" +id_bulan+"' ";
				}
			}			
			
			sql += " GROUP BY N.ABBREV)  ) ";
			sql += " GROUP BY ABBREV ";
			sql += " ORDER BY 1 ";			

			myLogger.info("SQL PRESTASI BANTAHAN :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Bantahan Pihak Berkepentingan' color='AFD8F8' showValues='0'>";
			dataset2 = "<dataset seriesName='Bantahan Agensi Pemohon' color='F6BD0F' showValues='0'>";
			
			while(rs.next()){
				
				categories = categories+"<category label='"+Utils.NiceStateName(rs.getString("abbrev"))+"'/>";
				dataset = dataset+"<set value='"+Utils.isNull(rs.getString("BANTAHAN_PB"))+"'/>";
				dataset2 = dataset2+"<set value='"+Utils.isNull(rs.getString("BANTAHAN_AP"))+"'/>";
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			dataset2 = dataset2+"</dataset>";

			xml = xml+categories+dataset+dataset2+"</chart>";
			
			System.out.println(xml);
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}

	public String generateXML_MSColumn2D_Penggunaan(String id_tahun,String id_bulan) {
		
		String xml="<chart palette='2' caption='Laporan Penggunaan eTaPP ' " +
					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
					"useRoundEdges='1' legendBorderAlpha='0'>";
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		String dataset3 = "";
		String dataset4 = "";
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			
			//BANTAHAN 
			sql =  " SELECT  ABBREV, ";					
			sql += " NVL (MAX(INITCAP(DECODE ( RN , 1, BIL ))),0) SPT,   ";
			sql += " NVL (MAX(INITCAP(DECODE ( RN , 2, BIL ))),0) SPPK,  ";
			sql += " NVL (MAX(INITCAP(DECODE ( RN , 3, BIL ))),0) SHTP,  ";	
			sql += " NVL (MAX(INITCAP(DECODE ( RN , 4, BIL ))),0) SPHP,  ";	
			sql += " NVL (MAX(INITCAP(DECODE ( RN , 5, BIL ))),0) SPF    ";	    
			sql += " FROM (  ";					
			sql += " SELECT ABBREV, BIL , ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN  ";	
			sql += " FROM (  ";
			sql += " SELECT E.ABBREV, COUNT(E.ID_NEGERI) AS BIL ";
			sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
			sql += " WHERE A.USER_NAME = B.USER_LOGIN ";
			sql += " AND B.USER_ID = C.USER_ID ";
			sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";
			sql += " AND C.ID_NEGERI = E.ID_NEGERI "; 
			sql += " AND D.ID_SEKSYEN=1 ";
			
			// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}
			sql += " GROUP BY E.ABBREV ";
			sql += " UNION  ";
			sql += " SELECT E.ABBREV, COUNT(E.ID_NEGERI) AS BIL ";
			sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
			sql += " WHERE A.USER_NAME = B.USER_LOGIN ";
			sql += " AND B.USER_ID = C.USER_ID ";
			sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";
			sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
			sql += " AND D.ID_SEKSYEN=2 ";	
			
			// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY E.ABBREV ";	
			sql += " UNION  ";	
			sql += " SELECT E.ABBREV, COUNT(E.ID_NEGERI) AS BIL ";	
			sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";	
			sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
			sql += " AND B.USER_ID = C.USER_ID "; 	
			sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
			sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
			sql += " AND D.ID_SEKSYEN=3 ";
			
			// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY E.ABBREV ";	
			sql += " UNION  ";	
			sql += " SELECT E.ABBREV, COUNT(E.ID_NEGERI) AS BIL ";	
			sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";
			sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
			sql += " AND B.USER_ID = C.USER_ID ";	
			sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
			sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
			sql += " AND D.ID_SEKSYEN=4 ";	
			
			// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}
			
			sql += " GROUP BY E.ABBREV ";	
			sql += " UNION  ";	
			sql += " SELECT E.ABBREV, COUNT(E.ID_NEGERI) AS BIL "; 	
			sql += " FROM WEB_LOGGER A, USERS B , USERS_INTERNAL C, TBLRUJSEKSYEN D, TBLRUJNEGERI E ";	
			sql += " WHERE A.USER_NAME = B.USER_LOGIN ";	
			sql += " AND B.USER_ID = C.USER_ID ";	
			sql += " AND C.ID_SEKSYEN =D.ID_SEKSYEN ";	
			sql += " AND C.ID_NEGERI = E.ID_NEGERI ";	
			sql += " AND D.ID_SEKSYEN=5 ";	
			
    		// TAHUN 
			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					sql = sql + " AND A.LOG_YEAR = '" + id_tahun +"' ";
				}
			}
			
    		// BULAN
			if (id_bulan != null) {
				if (!id_bulan.trim().equals("")) {
					sql = sql + " AND A.LOG_MONTH = '" +id_bulan+"' ";
				}
			}			
			
			sql += " GROUP BY e.ABBREV)  ) ";
			sql += " GROUP BY ABBREV ";
			sql += " ORDER BY 1 ";			

			myLogger.info("SQL PENGGUNAAN ETAPP :: "+sql);
			
			ResultSet rs =stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='SPT' color='AFD8F8' showValues='0'>";
			dataset2 = "<dataset seriesName='SPK' color='F6BD0F' showValues='0'>";
			dataset3 = "<dataset seriesName='SHTP' color='99CC33' showValues='0'>";
			dataset4 = "<dataset seriesName='SPHP' color='FF33CC' showValues='0'>";
			
			while(rs.next()){
				
				categories = categories+"<category label='"+Utils.NiceStateName(rs.getString("abbrev"))+"'/>";
				dataset = dataset+"<set value='"+Utils.isNull(rs.getString("SPT"))+"'/>";
				dataset2 = dataset2+"<set value='"+Utils.isNull(rs.getString("SPPK"))+"'/>";
				dataset3 = dataset3+"<set value='"+Utils.isNull(rs.getString("SHTP"))+"'/>";
				dataset4 = dataset4+"<set value='"+Utils.isNull(rs.getString("SHTP"))+"'/>";
			}
			categories = categories+"</categories>";
			dataset = dataset+"</dataset>";
			dataset2 = dataset2+"</dataset>";
			dataset3 = dataset3+"</dataset>";
			dataset4 = dataset4+"</dataset>";
			

			xml = xml+categories+dataset+dataset2+dataset3+dataset4+"</chart>";
			
			System.out.println(xml);
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}
		
		
		return xml;
	}


	public Vector getListRumusan()throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatus = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT   nama_negeri, NVL ( MAX (INITCAP (DECODE (rn, 1, kutipan_data))), 0 ) kutipan_data, ";					
				sql += " NVL ( MAX (INITCAP (DECODE (rn, 2, kutipan_data))), 0 ) permohonan_baru ";
				sql += " FROM (SELECT nama_negeri, kutipan_data, ";					
				sql += " ROW_NUMBER () OVER (PARTITION BY nama_negeri ORDER BY ROWNUM)rn ";					
				sql += " FROM (SELECT   c.nama_negeri,COUNT (DISTINCT NVL(b.id_permohonan,0)) AS kutipan_data ";		
				sql += " FROM tblpfdfail a,tblpptpermohonan b,tblrujnegeri c,tblrujstatus s,users u,users_internal ui ";
				sql += " WHERE a.id_fail = b.id_fail ";					
				sql += " AND a.id_negeri = c.id_negeri(+) ";		
				sql += " AND b.id_masuk = u.user_id(+) ";				
				sql += " AND u.user_id = ui.user_id(+) ";
				sql += " AND s.id_status = b.id_status(+) ";	
				sql += " AND a.id_suburusan IN (51, 52, 53) ";					
				sql += " AND TO_CHAR(b.TARIKH_PERMOHONAN,'MM/DD/YYYY') <= '08/01/2010' ";
				sql += " GROUP BY c.nama_negeri ";				
				sql += " UNION ";
				sql += " SELECT   c.nama_negeri,COUNT (DISTINCT NVL(b.id_permohonan,0)) AS permohonan_baru ";				
				sql += " FROM tblpfdfail a,tblpptpermohonan b,tblrujnegeri c,tblrujstatus s,users u,users_internal ui ";													
				sql += " WHERE a.id_fail = b.id_fail ";
				sql += " AND a.id_negeri = c.id_negeri(+) ";				
				sql += " AND b.id_masuk = u.user_id(+) ";
				sql += " AND u.user_id = ui.user_id(+) ";
				sql += " AND s.id_status = b.id_status(+) ";		
				sql += " AND a.id_suburusan IN (51, 52, 53)   ";				
				sql += " AND TO_CHAR(b.TARIKH_PERMOHONAN,'MM/DD/YYYY') >= '08/02/2010' ";
				sql += " GROUP BY c.nama_negeri)) ";
				sql += " GROUP BY nama_negeri ";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN RUMUSAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
		    	h.put("jumlah_kutipandata", rs.getString("KUTIPAN_DATA")==null?"":rs.getString("KUTIPAN_DATA"));  
		    	h.put("jumlah_permohonanbaru", rs.getString("PERMOHONAN_BARU")==null?"":rs.getString("PERMOHONAN_BARU")); 
		    	
		    	getStatus.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatus;					
	}		

	
	public Vector getListRumusanHTP()throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusHTP = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT COUNT (A.ID_PERMOHONAN) AS JUMLAH_TANAHMILIK ";					
				sql += " FROM TBLPERMOHONAN A, ";					
				sql += " TBLHTPHAKMILIK B ";		
				sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";				
				sql += " AND B.STATUS_SAH NOT IN ('B', 'P', 'S', 'M') ";
				sql += " AND B.NO_HAKMILIK IS NOT NULL ";	

				setSQL(sql);				
				myLogger.info("LAPORAN RUMUSAN :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		         
		    	h.put("jumlah_tanahmilik", rs.getString("JUMLAH_TANAHMILIK")==null?"":rs.getString("JUMLAH_TANAHMILIK")); 
		    	
		    	getStatusHTP.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusHTP;					
	}	
	
	public Vector getListRumusanTanahRizab()throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusRizab = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT COUNT (A.ID_PERMOHONAN) AS JUMLAH_TANAHRIZAB ";					
				sql += " FROM TBLPERMOHONAN A, ";					
				sql += " TBLHTPHAKMILIK B ";		
				sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";				
				sql += " AND B.NO_WARTA IS NOT NULL ";	

				setSQL(sql);				
				myLogger.info("LAPORAN RUMUSAN TANAH RIZAB :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		         
		    	h.put("jumlah_tanahrizab", rs.getString("JUMLAH_TANAHRIZAB")==null?"":rs.getString("JUMLAH_TANAHRIZAB")); 
		    	
		    	getStatusRizab.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusRizab;					
	}		
	
	
	public Vector getListRumusanPHP()throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		try{
				getStatusPHP = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				/* UPDATED BY PEJE
				 * 
				sql =  " SELECT TBLRUJSUBURUSAN.NAMA_SUBURUSAN AS NAMA_URUSAN, TBLRUJSUBURUSAN.ID_URUSAN AS ID_URUSAN, ";					
				sql += " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN ";					
				sql += " AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU, ";		
				sql += " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN ";				
				sql += " AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_KUTIPAN ";	
				sql += " FROM TBLRUJSUBURUSAN WHERE TBLRUJSUBURUSAN.ID_URUSAN = 6 ";					
				sql += " UNION ";		
				sql += " SELECT TBLRUJURUSAN.NAMA_URUSAN AS NAMA_URUSAN, TBLRUJURUSAN.ID_URUSAN AS ID_URUSAN, ";				
				sql += " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN ";				
				sql += " AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU, ";					
				sql += " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN ";		
				sql += " AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3)) AS FAIL_KUTIPAN ";				
				sql += " FROM TBLRUJURUSAN WHERE TBLRUJURUSAN.ID_URUSAN IN (7,8,9,12,13) ORDER BY ID_URUSAN ";
				*/
				
				sql = "SELECT TBLRUJSUBURUSAN.ID_URUSAN AS ID_URUSAN, TBLRUJSUBURUSAN.NAMA_SUBURUSAN AS NAMA_URUSAN,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_KUTIPAN"
					+ " FROM TBLRUJSUBURUSAN WHERE TBLRUJSUBURUSAN.ID_URUSAN = 6"  //PELEPASAN
					+ " UNION"
					+ " SELECT TBLRUJURUSAN.ID_URUSAN AS ID_URUSAN, TBLRUJURUSAN.NAMA_URUSAN AS NAMA_URUSAN,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL AND TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL AND TBLPERMOHONAN.FLAG_PERJANJIAN = 'U' AND TBLPERMOHONAN.FLAG_AKTIF = 'Y') AS FAIL_BARU,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL AND TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL AND TBLPERMOHONAN.FLAG_PERJANJIAN = 'U' AND TBLPERMOHONAN.FLAG_AKTIF = 'Y') AS FAIL_KUTIPAN"
					+ " FROM TBLRUJURUSAN WHERE TBLRUJURUSAN.ID_URUSAN IN (7,12,13)"  //PENYEWAAN + PENGELUARAN HASIL + BAHAN BATUAN
					+ " UNION"
					+ " SELECT TBLRUJURUSAN.ID_URUSAN AS ID_URUSAN, TBLRUJURUSAN.NAMA_URUSAN AS NAMA_URUSAN,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_JENIS_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_KUTIPAN"
					+ " FROM TBLRUJURUSAN WHERE TBLRUJURUSAN.ID_URUSAN = 8"  //PENGUATKUASAAN
					+ " UNION"
					+ " SELECT TBLRUJURUSAN.ID_URUSAN AS ID_URUSAN, TBLRUJURUSAN.NAMA_URUSAN AS NAMA_URUSAN,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_FAIL IN (1,4) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_BARU,"
					+ " (SELECT COUNT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL WHERE TBLPFDFAIL.ID_URUSAN = TBLRUJURUSAN.ID_URUSAN AND TBLPFDFAIL.FLAG_FAIL IN (3) AND TBLPFDFAIL.NO_FAIL IS NOT NULL) AS FAIL_KUTIPAN"
					+ " FROM TBLRUJURUSAN WHERE TBLRUJURUSAN.ID_URUSAN = 9"  //APB
					+ " ORDER BY ID_URUSAN";
				
				setSQL(sql);
				
				myLogger.info("LAPORAN RUMUSAN PHP :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		         
		    	h.put("nama_urusan", rs.getString("NAMA_URUSAN")==null?"":rs.getString("NAMA_URUSAN")); 
		    	h.put("id_urusan", rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
		    	h.put("fail_baru", rs.getString("FAIL_BARU")==null?"":rs.getString("FAIL_BARU"));
		    	h.put("fail_kutipan", rs.getString("FAIL_KUTIPAN")==null?"":rs.getString("FAIL_KUTIPAN"));
		    	getStatusPHP.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getStatusPHP;					
	}	
	
}	// CLOSE MAIN CLASS
