package ekptg.model.meps;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class MEPPKNegeriWarisBean implements Serializable,IMEPUmum {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9061757195911601177L;

	static Logger myLog = Logger.getLogger(ekptg.model.meps.MEPPKNegeriWarisBean.class);
	
	// VECTOR
	
	private String SQL;
	
	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}
	
	//Hamidah add
	
	public String getNamaPejabat(HttpSession session, String ID_PEJABATJKPTG) throws Exception {

		String NamaPejabat = "";
		String sql = "";
		Db db = null;
		//Connection conn = db.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		try {			
			db = new Db();
			stat = db.getStatement();
			
			if (!ID_PEJABATJKPTG.equals("")){
			sql = " SELECT NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG = " + ID_PEJABATJKPTG;
		
			///myLogger.info("sql -- " + sql);
			rs = stat.executeQuery(sql);
			
			while (rs.next()){
				NamaPejabat = rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT");
			}
			}
			return NamaPejabat;
		} finally {
			if (db != null)
				db.close();
		}

		
	}
	
			public List listTableRujukanV3(HttpSession session, String tableRujukan, String id_filter,String USER_ID)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listTableRujukanV3 = null;
				String sql = "";
				
				try {
					db = new Db();
					stmt = db.getStatement();
					
					if(tableRujukan.equals("TBLINTRUJJENISPEJABAT"))
					{
						sql = " SELECT ID_JENISPEJABAT AS ID, UPPER(KOD_PEJABAT) AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLINTRUJJENISPEJABAT ORDER BY KOD_PEJABAT";
					}
					
					else if(tableRujukan.equals("TBLRUJNEGERI"))
					{
						sql = " SELECT ID_NEGERI AS ID, KOD_NEGERI AS KOD, UPPER(NAMA_NEGERI) AS KETERANGAN FROM TBLRUJNEGERI";
						sql += " WHERE ID_NEGERI IS NOT NULL";
						
						
					}
					
					myLog.info(" V3: SQL listTableRujukanV3 ("+tableRujukan+") :"+ sql);
					rs = stmt.executeQuery(sql);
					listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						h.put("BIL",bil);
						h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
						h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
						if(tableRujukan.equals("ROLE"))
						{
							h.put("ROLE_DETAILS",rs.getString("ROLE_DETAILS") == null ? "" : rs.getString("ROLE_DETAILS").toUpperCase());
						}
						
						listTableRujukanV3.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}

				return listTableRujukanV3;

			}
			
			@SuppressWarnings("unchecked")
			public List listPejabatJKPTG(HttpSession session, String ID_SEKSYEN, String ID_NEGERI)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listPejabatJKPTG = null;
				String sql = "";
				
				try {
					db = new Db();
					stmt = db.getStatement();
					sql = " SELECT PEJ.ID_PEJABATJKPTG, PEJ.KOD_JKPTG, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, "+
							" UPPER(PEJ.ALAMAT1) AS ALAMAT1, UPPER(PEJ.ALAMAT2) AS ALAMAT2, UPPER(PEJ.ALAMAT3) AS ALAMAT3, "+ 
							" PEJ.POSKOD, PEJ.ID_NEGERI, PEJ.ID_BANDAR, UPPER(BAN.KETERANGAN) AS BANDAR, UPPER(NEG.NAMA_NEGERI) AS NEGERI,  "+
							" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX  "+
							" FROM TBLRUJPEJABATJKPTG PEJ,TBLRUJBANDAR BAN, TBLRUJNEGERI NEG  "+
							" WHERE PEJ.ID_NEGERI = NEG.ID_NEGERI(+)  "+
							" AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) ";
							if(!ID_NEGERI.equals(""))
							{
								sql += " AND PEJ.ID_NEGERI = "+ID_NEGERI+" ";
							}
							if(!ID_SEKSYEN.equals(""))
							{
								sql += " AND PEJ.ID_SEKSYEN = "+ID_SEKSYEN+" ";
							}
							sql += " ORDER BY PEJ.KOD_JKPTG ";
					myLog.info(" V3: SQL listPejabatJKPTG :"+ sql);
					rs = stmt.executeQuery(sql);
					listPejabatJKPTG = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						h.put("BIL",bil);
						h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
						h.put("KOD_JKPTG",rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
						h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
						h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
						h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
						listPejabatJKPTG.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}

				return listPejabatJKPTG;

			}

	public Vector<Hashtable<String, Comparable>> getJumlahLaporanFail(String Tahun,String TahunHingga,String negeri,String daerah,String idSubUrusan) throws Exception {		 		 
		Db db = null;
		String sql = "";
		Vector<Hashtable<String, Comparable>> vecHarta = new Vector<Hashtable<String, Comparable>>();
		System.out.println("TAHUN"+Tahun);
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				System.out.println("hantu"+daerah);
				sql=" SELECT   x, COUNT (DISTINCT id_permohonan) y, COUNT (ISLAM) y1,  COUNT (BUKAN_ISLAM) y2,  COUNT (TIADA_MAKLUMAT) y3 ";
				sql += " FROM (SELECT NVL (TO_CHAR (p.tarikh_mohon, 'YYYY'), '0000') x, ";
				sql += " p.id_permohonan, "; 
			    sql += " CASE WHEN M.JENIS_AGAMA=1 THEN 'ISLAM' END ISLAM, "; 
			    sql += " CASE WHEN M.JENIS_AGAMA=2 THEN 'BUKAN ISLAM' END BUKAN_ISLAM, ";
			    sql += " CASE WHEN M.JENIS_AGAMA IS NULL THEN 'TIADA MAKLUMAT' END TIADA_MAKLUMAT ";
			    sql += " FROM tblpfdfail a, ";
			    sql += " tblppkpermohonan p, ";
			    sql += " tblrujnegeri c, ";
			    sql += " tblppkpermohonansimati ps, ";
			    sql += " tblppksimati m ";
			     sql += " WHERE a.id_fail = p.id_fail ";
			    sql += " AND a.id_negeri = c.id_negeri(+) ";
			    sql += " AND p.id_permohonan = ps.id_permohonan(+) ";
			    sql += " AND a.id_seksyen = '2' ";
			    sql += " AND p.id_status <> 999 ";
			    if (negeri != "99999") {
					if (!negeri.trim().equals("")) {
						sql = sql + " AND P.ID_NEGERIMHN = " + negeri +"";
					}
				}	
			    
			    if (daerah != "99999") {
					if (!daerah.trim().equals("")) {
						sql = sql + " AND p.id_daerahmhn = " + daerah +"";
					}
				}	
			    
			 
			    
			    if (idSubUrusan != null) {
					if (!idSubUrusan.trim().equals("")) {
						sql = sql + " AND A.ID_SUBURUSAN = " + idSubUrusan +"";
					}
				}
			        //filter negeri     AND p.id_negerimhn = 14
			       //daerah  and p.id_daerahmhn=
			    sql += " and ps.id_simati = m.id_simati)"; 
			    
			    if (Tahun != null ) {
					if (!Tahun.trim().equals("")) {
						if (!TahunHingga.trim().equals("")) {
							sql = sql + " WHERE X >= " + Tahun +" AND X <= "+TahunHingga;
						}else{
							sql = sql + " WHERE X = " + Tahun +"";
						}
					}
				}
			    
			  	 
			    sql += " GROUP BY x";
			     sql += " ORDER BY x ";


				
//						
				setSQL(sql);
				myLog.info("LAPORAN TOTAL JUMLAH FAIL :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String, Comparable> h;	
				int bil = 1;
				
			while (rs.next()) {
		    	h = new Hashtable<String, Comparable>();			    	
		    	h.put("bil", bil);		        
		    	h.put("tahun", Utils.isNull(rs.getString("X")));		    			
		    	h.put("jumlah1", rs.getInt("Y1"));
		    	h.put("jumlah2", rs.getInt("Y2"));
		    	vecHarta.addElement(h);
		    	bil++;

			}      
		
		}finally{
			if(db != null)db.close();
		}	
		return vecHarta;					
	
	}
	
	@Override
	public Vector<Hashtable<String, Comparable>> getJumlahPermohonan(String ID_NEGERI, String ID_PEJABATJKPTG, String socTahun,String socTahunHingga) throws Exception {		 		 
		Db db = null;
		String sql = "";
		Vector<Hashtable<String, Comparable>> vecHarta = new Vector<Hashtable<String, Comparable>>();
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql =  " SELECT X,NVL(COUNT(DISTINCT ID_PERMOHONAN),0) Y,NVL(COUNT(ID_OB),0) Y1 ";
				sql += " FROM ( ";
				sql += " 	SELECT NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') X, P.ID_PERMOHONAN, OB.ID_OB ";
				sql += " 	FROM TBLPFDFAIL A,TBLPPKPERMOHONAN P,TBLRUJNEGERI C,TBLPPKPERMOHONANSIMATI PS,TBLPPKOB OB ";                    
				sql += " 	WHERE A.ID_FAIL= P.ID_FAIL ";
				sql += " 	AND A.ID_NEGERI = C.ID_NEGERI(+) ";               
				sql += " 	AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN ";        
				sql += " 	AND PS.ID_SIMATI = OB.ID_SIMATI ";
				sql += "    AND A.ID_SEKSYEN = '2' AND A.ID_STATUS NOT IN ('999') ";               


				//NEGERI
				if (!ID_NEGERI.equals("")) {
					
					sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
				
				}
				
				sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
				sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
				sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
				//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 
				
				
				//UNIT/PEJABAT
				if (!ID_PEJABATJKPTG.equals("")) {
					
					sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
				}
				
				sql += ")";
									
					
				//sql += " ORDER BY x ";
				sql += " ) ";
				
	    		// TAHUN 
				if (socTahun != null ) {
					if (!socTahun.trim().equals("")) {
						if (!socTahunHingga.trim().equals("")) {
							sql = sql + " WHERE X >= " + socTahun +" AND X <= "+socTahunHingga;
						}else{
							sql = sql + " WHERE X = " + socTahun +"";
						}
					}
				}				
				sql += " GROUP BY X ORDER BY X ";			
				setSQL(sql);
				myLog.info("LAPORAN TOTAL KESELURUHAN HARTA3 :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String, Comparable> h;	
				int bil = 1;
				
			while (rs.next()) {
		    	h = new Hashtable<String, Comparable>();			    	
		    	h.put("bil", bil);		        
		    	h.put("tahun", Utils.isNull(rs.getString("X")));
//		    	h.put("jumlah1", rs.getString("Y"));
//		    	h.put("jumlah1", rs.getString("Y1"));
		    	h.put("jumlah1", Utils.isNull(rs.getString("Y")));
		    	h.put("jumlah2", Utils.isNull(rs.getString("Y1")));
		    	vecHarta.addElement(h);
		    	bil++;

			}      
		
		}finally{
			if(db != null)db.close();
		}	
		return vecHarta;					
	
	}
	
	@Override
	public Vector<Hashtable<String, Comparable>> getJumlahPermohonan(String ID_NEGERI, String ID_PEJABATJKPTG, String socTahun,String socTahunHingga,String idSubUrusan) throws Exception {		 		 
		Db db = null;
		String sql = "";
		Vector<Hashtable<String, Comparable>> vecHarta = new Vector<Hashtable<String, Comparable>>();
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql =  " SELECT X,COUNT(DISTINCT ID_PERMOHONAN) Y,COUNT(ID_OB) Y1 ";
				sql += " FROM ( ";
				sql += " 	SELECT NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') X, P.ID_PERMOHONAN, OB.ID_OB ";
				sql += " 	FROM TBLPFDFAIL A,TBLPPKPERMOHONAN P,TBLRUJNEGERI C,TBLPPKPERMOHONANSIMATI PS,TBLPPKOB OB,TBLPPKOB OBB, TBLPPKSIMATI SM ";                    
				sql += " 	WHERE A.ID_FAIL= P.ID_FAIL ";
				sql += " 	AND A.ID_NEGERI = C.ID_NEGERI(+) ";               
				sql += " 	AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN(+) "; 
				sql += "	AND PS.ID_PERMOHONANSIMATI = OB.ID_PERMOHONANSIMATI(+) ";
				sql += " 	AND PS.ID_SIMATI = SM.ID_SIMATI(+) ";
				sql += "	AND OB.ID_OB = OBB.ID_OB ";
				sql += "    AND A.ID_SEKSYEN = '2' AND P.ID_STATUS <> 999 ";     

				//NEGERI
				if (!ID_NEGERI.equals("")) {
					
					sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
				
				}
				
				sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
				sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
				sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
				//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 
				//myLog.info("ID_NEGERI :: "+ID_NEGERI);
				
				//UNIT/PEJABAT
				if (!ID_PEJABATJKPTG.equals("")) {
					
					sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
				}
				
				sql += ")";
				//myLog.info("ID_PEJABATJKPTG :: "+ID_PEJABATJKPTG);
									
					
				//sql += " ORDER BY x ";
				//sql += " ) ";
				
				// SUB URUSAN
				if (idSubUrusan != null) {
					if (!idSubUrusan.trim().equals("")) {
						sql = sql + " AND A.ID_SUBURUSAN = " + idSubUrusan +"";
					}
				}					
					
				//sql += " ORDER BY x ";
				sql += " ) ";
				
	    		// TAHUN 
				if (socTahun != null ) {
					if (!socTahun.trim().equals("")) {
						if (!socTahunHingga.trim().equals("")) {
							sql = sql + " WHERE X >= " + socTahun +" AND X <= "+socTahunHingga;
						}else{
							sql = sql + " WHERE X = " + socTahun +"";
						}
					}
				}				
				sql += " GROUP BY X ORDER BY X ";			
				setSQL(sql);
				myLog.info("JUMLAH WARIS MENGIKUT STATUS :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String, Comparable> h;	
				int bil = 1;
				
			while (rs.next()) {
		    	h = new Hashtable<String, Comparable>();			    	
		    	h.put("bil", bil);		        
		    	h.put("tahun", Utils.isNull(rs.getString("X")));		    			
		    	h.put("jumlah1", rs.getString("Y"));
		    	h.put("jumlah2", rs.getString("Y1"));
		    	vecHarta.addElement(h);
		    	bil++;

			}      
		
		}finally{
			if(db != null)db.close();
		}	
		return vecHarta;					
	
	}
	
	//
	public Vector<Hashtable<String, Comparable>> getJumlahPermohonanNegeriAgama(String ID_NEGERI, String ID_PEJABATJKPTG, String socTahun,String socTahunHingga,String socBulan,String socBulanHingga,String idSubUrusan) throws Exception {		 		 
		Db db = null;
		String sql = "";
		Vector<Hashtable<String, Comparable>> vecHarta = new Vector<Hashtable<String, Comparable>>();
		myLog.info("BULAN HINGGA::::" + socBulanHingga );
		try{
				
			db = new Db();
				Statement stmt = db.getStatement();
				
				/*sql =  " SELECT X,COUNT(DISTINCT ID_PERMOHONAN) Y,COUNT(ID_OB) Y1 ";
				sql += " FROM ( ";
				sql += " 	SELECT NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') X, P.ID_PERMOHONAN, OB.ID_OB ";
				sql += " 	FROM TBLPFDFAIL A,TBLPPKPERMOHONAN P,TBLRUJNEGERI C,TBLPPKPERMOHONANSIMATI PS,TBLPPKOB OB,TBLPPKOB OBB, TBLPPKSIMATI SM ";                    
				sql += " 	WHERE A.ID_FAIL= P.ID_FAIL ";
				sql += " 	AND A.ID_NEGERI = C.ID_NEGERI(+) ";               
				sql += " 	AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN(+) "; 
				sql += "	AND PS.ID_PERMOHONANSIMATI = OB.ID_PERMOHONANSIMATI(+) ";
				sql += " 	AND PS.ID_SIMATI = SM.ID_SIMATI(+) ";
				sql += "	AND OB.ID_OB = OBB.ID_OB ";
				sql += "    AND A.ID_SEKSYEN = '2' AND P.TARIKH_MOHON IS NOT NULL AND P.ID_STATUS <> 999 ";  */ 
				
				sql =  " select x , muslim, nonmuslim, tiada_maklumat1 + tiada_maklumat2 as tiada_maklumat , muslim+nonmuslim+tiada_maklumat1+tiada_maklumat2 as permohonan from ( ";
				sql += " select x,nvl(muslim,'0')muslim, nvl(nonmuslim,'0')nonmuslim, nvl(tiada_maklumat1,'0')tiada_maklumat1 , nvl(tiada_maklumat2,'0')tiada_maklumat2 ";
				sql += " from ( ";
				sql += " select    NVL (TO_CHAR (c.TARIKH_MOHON, 'YYYY'), '0000') X, a.jenis_agama  , count(*) bilangan ";
				sql += " from tblppksimati a, tblppkpermohonansimati b, tblppkpermohonan c, tblpfdfail d ";
				sql += " where a.id_simati=b.id_simati ";
				sql += " and b.id_permohonan=c.id_permohonan ";
				sql += " AND c.ID_FAIL = d.ID_FAIL ";
						//and TO_CHAR (c.TARIKH_MOHON, 'YYYY')='2017'
						
				if (socTahun != null ) {
					if (!socTahun.trim().equals("")) {
						if (!socTahunHingga.trim().equals("")) {
							sql = sql + " AND  TO_CHAR(c.TARIKH_MOHON,'YYYY') >= " + socTahun +" " +
									" AND TO_CHAR(c.TARIKH_MOHON,'YYYY') <= "+socTahunHingga;
							if ((!socBulanHingga.trim().equals("")) && (!socBulanHingga.trim().equals("0")) && (socBulanHingga != null)) {
								sql = sql + " AND  TO_CHAR(c.TARIKH_MOHON,'MM') >= " + socBulan +" " +
								" AND TO_CHAR(c.TARIKH_MOHON,'MM') < "+socBulanHingga;
								
							}
							
						}else{
							sql = sql + " AND  TO_CHAR(c.TARIKH_MOHON,'YYYY') = " + socTahun +"";
							if (socBulanHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(c.TARIKH_MOHON,'MM') = " + socBulan +"";	
							}else{
								sql = sql + " AND  TO_CHAR(c.TARIKH_MOHON,'MM') >= " + socBulan +" " +
								" AND TO_CHAR(c.TARIKH_MOHON,'MM') < "+socBulanHingga;
								
							}
						}
					}
				}
				//NEGERI
				if (!ID_NEGERI.equals("")) {
					
					sql = sql + " AND c.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
				
				}
				
				sql += " AND c.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
				sql += " AND c.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
				sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
				//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

				
				//UNIT/PEJABAT
				if (!ID_PEJABATJKPTG.equals("")) {
					
					sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
				}
				
				sql += ")";
				
				// SUB URUSAN
				if (idSubUrusan != null) {
					if (!idSubUrusan.trim().equals("")) {
						sql = sql + " AND d.ID_SUBURUSAN = " + idSubUrusan +"";
					}
				}					
					
				//sql += " ORDER BY x ";
				//sql += " ) ";
	    		// TAHUN 
				/*if (socTahun != null ) {
					if (!socTahun.trim().equals("")) {
						if (!socTahunHingga.trim().equals("")) {
							sql = sql + " WHERE x >= " + socTahun +" AND x <= "+socTahunHingga;
						}else{
							sql = sql + " WHERE x = " + socTahun +"";
						}
					}
				}*/
				
				
				
				sql += " group by TO_CHAR (c.TARIKH_MOHON, 'YYYY'), a.jenis_agama ";
				//sql += " ) ";
				sql += " ) pivot ( ";
				sql += " max(bilangan) ";
				sql += " for jenis_agama in (1 muslim,2 nonmuslim, 0 tiada_maklumat1, '' tiada_maklumat2  ))) ";
				sql += " order by x ";
				
				//sql += " GROUP BY X ORDER BY X ";			
				setSQL(sql);
				myLog.info("Jumlah Permohonan Negeri Agama ----- "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String, Comparable> h;	
				int bil = 1;
				
			while (rs.next()) {
		    	h = new Hashtable<String, Comparable>();			    	
		    	h.put("bil", bil);		        
		    	h.put("tahun", Utils.isNull(rs.getString("X")));
		    	h.put("permohonan", rs.getString("permohonan"));
		    	h.put("muslim", rs.getString("muslim"));
		    	h.put("nonmuslim", rs.getString("nonmuslim"));
		    	h.put("tiada_maklumat", rs.getString("tiada_maklumat"));
		    	
		    	vecHarta.addElement(h);
		    	bil++;

			}      
		
		}finally{
			if(db != null)db.close();
		}	
		return vecHarta;					
	
	}
	
	@Override
	public Vector<Hashtable<String, Comparable>> getJumlahPermohonan(String ID_NEGERI, String ID_PEJABATJKPTG,
			String socTahun,String socTahunHingga,String socBulan,String socBulanHingga
			,String idSubUrusan) throws Exception {		 		 
		Db db = null;
		String sql = "";
		Vector<Hashtable<String, Comparable>> vecHarta = new Vector<Hashtable<String, Comparable>>();
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				System.out.println("masuk");
				sql =  " SELECT X,COUNT(DISTINCT ID_PERMOHONAN) Y,COUNT(ID_OB) Y1 ";
				sql += " FROM ( ";
				sql += " 	SELECT NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') X, P.ID_PERMOHONAN, OB.ID_OB ";
				sql += " 	FROM TBLPFDFAIL A,TBLPPKPERMOHONAN P,TBLRUJNEGERI C,TBLPPKPERMOHONANSIMATI PS,TBLPPKOB OB ";                    
				sql += " 	WHERE A.ID_FAIL= P.ID_FAIL ";
				sql += " 	AND A.ID_NEGERI = C.ID_NEGERI(+) ";               
				sql += " 	AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN(+) ";        
				sql += " 	AND PS.ID_SIMATI = OB.ID_SIMATI(+) ";
				sql += "    AND A.ID_SEKSYEN = '2' AND P.ID_STATUS <> 999 ";               

				if (socTahun != null ) {
					if (!socTahun.trim().equals("")) {
						if (!socTahunHingga.trim().equals("")) {
							sql = sql + " AND  TO_CHAR(P.TARIKH_MOHON,'YYYY') >= " + socTahun +" " +
									" AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= "+socTahunHingga;
							if (!socBulanHingga.trim().equals("")) {
								sql = sql + " AND  TO_CHAR(P.TARIKH_MOHON,'MM') >= " + socBulan +" " +
								" AND TO_CHAR(P.TARIKH_MOHON,'MM') < "+socBulanHingga;
								
							}
							
						}else{
							sql = sql + " AND  TO_CHAR(P.TARIKH_MOHON,'YYYY') = " + socTahun +"";
							if (socBulanHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = " + socBulan +"";	
							}else{
								sql = sql + " AND  TO_CHAR(P.TARIKH_MOHON,'MM') >= " + socBulan +" " +
								" AND TO_CHAR(P.TARIKH_MOHON,'MM') < "+socBulanHingga;
								
							}
						}
					}
				}
				// NEGERI
				if (ID_NEGERI != null) {
					if (!ID_NEGERI.trim().equals("")) {
						sql = sql + " AND P.ID_NEGERIMHN = " + ID_NEGERI +"";
					}
				}					
				// SUB URUSAN
				if (idSubUrusan != null) {
					if (!idSubUrusan.trim().equals("")) {
						sql = sql + " AND A.ID_SUBURUSAN = " + idSubUrusan +"";
					}
				}					
					
				//sql += " ORDER BY x ";
				sql += " ) ";
	    		// TAHUN 
//				if (socTahun != null ) {
//					if (!socTahun.trim().equals("")) {
//						if (!socTahunHingga.trim().equals("")) {
//							sql = sql + " WHERE X >= " + socTahun +" AND X <= "+socTahunHingga;
//						}else{
//							sql = sql + " WHERE X = " + socTahun +"";
//							if (!socBulan.trim().equals("")) {
//								sql = sql + " WHERE X = " + socTahun +"";	
//							}
//						}
//					}
//				}				
				sql += " GROUP BY X ORDER BY X ";			
				setSQL(sql);
				myLog.info("LAPORAN TOTAL KESELURUHAN HARTA :: "+sql);				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String, Comparable> h;	
				int bil = 1;
				
			while (rs.next()) {
		    	h = new Hashtable<String, Comparable>();			    	
		    	h.put("bil", bil);		        
		    	h.put("tahun", Utils.isNull(rs.getString("X")));		    			
		    	h.put("jumlah1", rs.getInt("Y"));
		    	h.put("jumlah2", rs.getInt("Y1"));
		    	vecHarta.addElement(h);
		    	bil++;

			}      
		
		}finally{
			if(db != null)db.close();
		}	
		return vecHarta;					
	
	}

	@Override
	public Vector<Hashtable<String, Comparable>> getJumlahPermohonanNegeriAgama(String ID_NEGERI, String ID_PEJABATJKPTG, 
			String socTahun, String socTahunHingga, String idSubUrusan) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}	// CLOSE MAIN CLASS
