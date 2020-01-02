package ekptg.model.meps;
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
import ekptg.model.entities.Tblrujsuburusan;

public class MEPPTNegeriBean extends EkptgCache implements Serializable,IMEPPTNegeri {
	static Logger myLog = Logger.getLogger(ekptg.model.meps.MEPPTNegeriBean.class);
	
	// VECTOR
	public Vector getHakmilik = null;
		
	private String SQL;
	
	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}

	@Override
	//public Vector getListTotalProjectKementerian(String socTahun,String socBulan)throws Exception {		 		 
	public Vector getJumlahPermohonan(String idSubUrusan,String idNegeri)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
				
			sql =  " SELECT X, COUNT(DISTINCT ID_PERMOHONAN) Y FROM ( ";
			sql += " SELECT TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') X, B.ID_PERMOHONAN, D.ID_HAKMILIK ";
			sql += " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B ";                    
			sql += " ,TBLRUJNEGERI C, TBLPPTHAKMILIK D ";               
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
			sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) ";         
			sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
			sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)";
				
			// SUB URUSAN
			if (idSubUrusan != null) {
				if (!idSubUrusan.trim().equals("")) {
					sql = sql + " AND A.ID_SUBURUSAN = " + idSubUrusan +"";
				}
			}					
    		// NEGERI
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")) {
					sql = sql + " AND A.ID_NEGERI = " + idNegeri +"";
				}
			}					
			sql += " ORDER BY X ";
			sql += " ) ";
			sql += " GROUP BY X ";						
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KESELURUHAN PPT :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			
			while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("Y")));
		    	h.put("jumlahPermohonanInt", rs.getInt("Y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("X")));		    			
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("X")));		    			
		    	//h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
		finally{
			if(db != null)db.close();
		}	
		return getHakmilik;					
	
	}
	
	@Override
	//public Vector getListTotalProjectKementerian(String socTahun,String socBulan)throws Exception {		 		 
	public Vector getJumlahPermohonan(String socTahun,String socTahunHingga
			,String idNegeri,String idSubUrusan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
			getHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
				
			sql =  " SELECT X, COUNT(DISTINCT ID_PERMOHONAN) Y FROM ( ";
			sql += " SELECT TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') X, B.ID_PERMOHONAN, D.ID_HAKMILIK ";
			sql += " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B ";                    
			sql += " ,TBLRUJNEGERI C, TBLPPTHAKMILIK D ";               
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";                     
			sql += " AND A.ID_NEGERI = C.ID_NEGERI(+) ";         
			sql += " AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN ";        
			sql += " AND A.ID_SUBURUSAN IN (51, 52, 53)";
				
			// SUB URUSAN
			if (idSubUrusan != null) {
				if (!idSubUrusan.trim().equals("")) {
					sql = sql + " AND A.ID_SUBURUSAN = " + idSubUrusan +"";
				}
			}					
    		// NEGERI
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")) {
					sql = sql + " AND A.ID_NEGERI = " + idNegeri +"";
				}
			}					
			sql += " ORDER BY X ";
			sql += " ) ";
    		// TAHUN 
			//if (socTahun != null ) {
				if (!socTahun.trim().equals("")) {
					if (!socTahunHingga.trim().equals("")) {
						sql = sql + " WHERE X >= " + socTahun +" AND X <= "+socTahunHingga;
					}else{
						sql = sql + " WHERE X = " + socTahun +"";
					}
				}
			//}				

			sql += " GROUP BY X ";						
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KESELURUHAN PPT :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			
			while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("Y")));
		    	h.put("jumlahPermohonanInt", rs.getInt("Y"));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("X")));		    			
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("X")));		    			
		    	//h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	h.put("tahun", Utils.NiceStateName(rs.getString("X")));		    			
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
		finally{
			if(db != null)db.close();
		}	
		return getHakmilik;					
	
	}
	
	@Override
	public Vector getJumlahPermohonan2(String socTahun,String socBulan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		try{
				getHakmilik = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();									
				
				sql =  " SELECT X,COUNT(JUMLAH_SEK4) Y,COUNT(JUMLAH_SEK8) Y1 FROM ( ";
				sql += " SELECT TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') X,SEK4.JUMLAH_SEK4,SEK8.JUMLAH_SEK8 ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLRUJNEGERI C ";                    
				sql += " ,( SELECT BI.ID_PERMOHONAN,COUNT(*) JUMLAH_SEK4 ";               
				sql += " FROM TBLPFDFAIL AI, TBLPPTPERMOHONAN BI,TBLRUJNEGERI CI ";                     
				sql += " WHERE AI.ID_FAIL = BI.ID_FAIL ";         
				sql += " AND AI.ID_NEGERI = CI.ID_NEGERI(+) ";        
				sql += " AND AI.ID_SUBURUSAN IN (51,53)	";
				sql += " GROUP BY BI.ID_PERMOHONAN	";
				sql += " ) SEK4	";
				sql += " ,( SELECT BI.ID_PERMOHONAN,COUNT(*) JUMLAH_SEK8 ";               
				sql += " FROM TBLPFDFAIL AI, TBLPPTPERMOHONAN BI,TBLRUJNEGERI CI ";                     
				sql += " WHERE AI.ID_FAIL = BI.ID_FAIL ";         
				sql += " AND AI.ID_NEGERI = CI.ID_NEGERI(+) ";        
				sql += " AND AI.ID_SUBURUSAN IN (52)	";
				sql += " GROUP BY BI.ID_PERMOHONAN	";
				sql += " ) SEK8	";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL " +
						" AND A.ID_NEGERI = C.ID_NEGERI(+) " +
						" AND A.ID_SUBURUSAN IN (51, 52, 53)" +
						" AND B.TARIKH_PERMOHONAN IS NOT NULL" +
						" AND B.ID_PERMOHONAN = SEK4.ID_PERMOHONAN(+)" +
						" AND B.ID_PERMOHONAN = SEK8.ID_PERMOHONAN(+)" +
						" AND A.ID_NEGERI=6 " +
						" ";
				
//	    		// TAHUN 
//				if (socTahun != null) {
//					if (!socTahun.trim().equals("")) {
//						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'YYYY') = '" + socTahun +"' ";
//					}
//				}				
//	    		// BULAN
//				if (socBulan != null) {
//					if (!socBulan.trim().equals("")) {
//						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
//					}
//				}
					
				sql += " ) ";
				sql += " GROUP BY X ";						
				sql += " ORDER BY X ";
				setSQL(sql);
				myLog.info("getJumlahPermohonan2 : sql = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
		    	h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));		    			
		    	h.put("abbrev", Utils.NiceStateName(rs.getString("x")));		    			
		    	//h.put("jumlah_hakmilik", Utils.isNull(rs.getString("z")));
		    	getHakmilik.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
		return getHakmilik;			
		
 }
	
	// GENERATE BAR/PIE CHART	
	public String generateXML(String nama_laporan){		
		//System.out.println(getSQL());
		String xml="<chart caption='"+nama_laporan+"' subcaption='' xAxisName='Tahun' yAxisName='Jumlah' numberPrefix='' showLegend='1'>";
		Db db = null;
		try{
			db =  new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =stat.executeQuery(getSQL());
			while(rs.next()){				
				xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("X"))+"'  value='"+rs.getString("Y")+"' />";				
			}
			xml =xml+"</chart>";
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (db!=null) db.close();
		}
		return xml;

	}		
	
	//public  String generateXMLfaresh(String nama_laporan){
	public String generateXMLPermohonan2(String nama_laporan){				
		//System.out.println(getSQL());
		//String xml="<chart palette='2' caption='Laporan' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan Dan Hakmilik/Lot' numberPrefix=''>" +
		//"useRoundEdges='1' legendBorderAlpha='0'>";
		
		String xml="<chart palette='2'  xAxisName='Negeri' caption='"+nama_laporan+"' " +
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
			dataset1 = "<dataset seriesName='Jumlah Permohonan' color='3813F0' showValues='1'>";
			dataset2 = "<dataset seriesName='Jumlah Hakmilik' color='BA1435' showValues='1'>";
			while(rs.next()){				
				//xname = rs.getString("y")+" ("+rs.getString("z")+" lot)";				
				//xml =xml+"<set label='"+Utils.NiceStateName(rs.getString("x"))+"' value='"+rs.getString("y")+"' />";				
			
				categories = categories+"<category label='"+rs.getString("X")+"'/>";
				dataset1 = dataset1+"<set label='"+Utils.NiceStateName(rs.getString("X"))+"' value='"+rs.getString("Y")+"'/>";
				dataset2 = dataset2+"<set value='"+rs.getString("Y1")+"'/>";
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
		//System.out.println(xml);
		return xml;
	}	
	
	public Tblrujsuburusan getSubUrusan(String idSuburusan) throws Exception {
		Db db = null;
		String sql = "";
		try {
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		    	SQLRenderer r = new SQLRenderer();
		    	r.add("id_Suburusan");
		    	r.add("kod_Suburusan");
		    	r.add("nama_Suburusan");
		    	r.add("ID_SUBURUSAN",Integer.parseInt(idSuburusan));
		    	sql = r.getSQLSelect("Tblrujsuburusan");
		    	ResultSet rs = stmt.executeQuery(sql);			
		    	Tblrujsuburusan s = null;
		    	while (rs.next()) {
		    		s = new Tblrujsuburusan();
		    		s.setIdSuburusan(rs.getLong("id_Suburusan")); 
		    		s.setKodSuburusan(rs.getString("kod_Suburusan")); 
		    		s.setNamaSuburusan(rs.getString("nama_Suburusan"));
		    	}
		    	return s;
	
		} finally {
			if (db != null)
		    db.close();
		}
		    	    	
	}	
	
	
}	// CLOSE MAIN CLASS
