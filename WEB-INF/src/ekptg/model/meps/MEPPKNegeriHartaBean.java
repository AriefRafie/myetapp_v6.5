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
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class MEPPKNegeriHartaBean implements Serializable,IMEPPKNegeriHarta {
	private String SQL;
	static Logger myLog = Logger.getLogger(ekptg.model.meps.MEPPKNegeriHartaBean.class);
	
	// VECTOR	
	
	public String getSQL() {
		return SQL;
	}
	
	public void setSQL(String sql) {
		SQL = sql;
	}

//	@Override
//	public  String generateXMLJumlahHarta(String nama_laporan){		
//		//myLog.info(getSQL());
//		String xml="<chart palette='2'  xAxisName='Tahun' caption='"+nama_laporan+"' " +
//					"shownames='1' showvalues='1' decimals='0' numberPrefix='' " +
//					"useRoundEdges='1' legendBorderAlpha='0' formatNumberScale='0' >";
//		Db db = null;
//		String xname = "";
//		String categories = "";
//		String dataset1="" ;
//		String dataset2="" ;
//		try{
//			db =  new Db();
//			Connection conn = db.getConnection();
//			Statement stat = conn.createStatement();
//			ResultSet rs =stat.executeQuery(getSQL());
//			categories = "<categories>";
//			dataset1 = "<dataset seriesName='Jumlah HTA' color='3813F0' showValues='1'>";
//			dataset2 = "<dataset seriesName='Jumlah HA' color='BA1435' showValues='1'>";
//			while(rs.next()){				
//				categories = categories+"<category label='"+Utils.isNull(rs.getString("X"))+"'/>";
//				dataset1 = dataset1+"<set label='"+Utils.isNull(rs.getString("X"))+"' value='"+Utils.isNull(rs.getString("Y"))+"'/>";
//				dataset2 = dataset2+"<set value='"+Utils.isNull(rs.getString("Y1"))+"'/>";
//
//			}
//			categories = categories+"</categories>";
//			dataset1 = dataset1+"</dataset>";
//			dataset2 = dataset2+"</dataset>";
//			xml =xml+categories+dataset1+dataset2+"</chart>";
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		} finally {
//			if (db!=null) db.close();
//		}    
//		myLog.info(xml);
//		return xml;
//	
//	}	
	
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


		@Override
		public Vector getJumlahHarta(String ID_NEGERI, String ID_PEJABATJKPTG, String socTahun,String socTahunHingga,String idSubUrusan)throws Exception {		 		 
			Db db = null;
			String sql = "";
			Vector vecHarta = new Vector();
			System.out.println("ID NEGERI ---" +ID_NEGERI);
			try{
				System.out.println(ID_NEGERI);
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();	
				    
				    sql = " SELECT NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') AS X, ";
				    sql += " COUNT(P.ID_PERMOHONAN) AS JUMLAH_PERMOHONAN, ";
				    sql += " SUM(NVL(HTA.NILAI_HTA_TARIKHMOHON,0)) AS y1, ";
				    sql += " SUM(NVL(HA.NILAI_HA_TARIKHMOHON,0)) AS y, ";
				    sql += " SUM(NVL(CASE WHEN SM.JENIS_AGAMA = '1' THEN HTA.NILAI_HTA_TARIKHMOHON ELSE 0 END,0)) AS htaislam, ";
				    sql += " SUM(NVL(CASE WHEN SM.JENIS_AGAMA = '2' THEN HTA.NILAI_HTA_TARIKHMOHON ELSE 0 END,0)) AS htabukanislam, ";
				    sql += " SUM(NVL(CASE WHEN SM.JENIS_AGAMA = '1' THEN HA.NILAI_HA_TARIKHMOHON ELSE 0 END,0)) AS haislam, ";
				    sql += " SUM(NVL(CASE WHEN SM.JENIS_AGAMA = '2' THEN HA.NILAI_HA_TARIKHMOHON ELSE 0 END,0)) AS habukanislam, ";
				    sql += " SUM(NVL(CASE WHEN SM.JENIS_AGAMA IS NULL THEN HTA.NILAI_HTA_TARIKHMOHON ELSE 0 END,0)) AS htalain, ";
				    sql += " SUM(NVL(CASE WHEN SM.JENIS_AGAMA IS NULL THEN HA.NILAI_HA_TARIKHMOHON ELSE 0 END,0)) AS halain ";
				    sql += " FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLPPKSIMATI SM, ";
				    sql += " TBLPPKPERMOHONANSIMATI PSM, ";
				    sql += " (SELECT ID_PERMOHONANSIMATI, SUM(NILAI_HTA_TARIKHMOHON) AS NILAI_HTA_TARIKHMOHON FROM TBLPPKHTA GROUP BY ID_PERMOHONANSIMATI) HTA, "; 
				    sql += " (SELECT ID_PERMOHONANSIMATI, SUM(NILAI_HA_TARIKHMOHON) AS NILAI_HA_TARIKHMOHON FROM TBLPPKHA GROUP BY ID_PERMOHONANSIMATI) HA ";
				    sql += " WHERE ";
		    		sql += " F.ID_FAIL = P.ID_FAIL ";
		    		
    				sql += " AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI ";
					sql += " AND PSM.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI(+) ";
					sql += " AND PSM.ID_PERMOHONANSIMATI = HA.ID_PERMOHONANSIMATI(+) ";
					
				    
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
					
					//sql += ")";
				            
//				            if (idNegeri != null) {
//						if (!idNegeri.trim().equals("")) {
//									sql = sql + " AND P.ID_NEGERIMHN = " + idNegeri +"";
//								}						
//							}	
				            
				         // SUB URUSAN
						if (idSubUrusan != null) {
								if (!idSubUrusan.trim().equals("")) {
									sql = sql + " AND F.ID_SUBURUSAN = " + idSubUrusan +"";
							}
							}
				            
				            
				sql += ")";
				// TAHUN 
				if (socTahun != null ) {
					if (!socTahun.trim().equals("")) {
						if (!socTahunHingga.trim().equals("")) {
							sql = sql + " AND NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') >= " + socTahun +" AND NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') <= "+socTahunHingga;
						}else{
							sql = sql + " AND NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') = " + socTahun +"";
						}
					}
				}				

				
				//sql+=" GROUP BY x ORDER BY x ";
				sql += " GROUP BY TO_CHAR(P.TARIKH_MOHON,'YYYY') ";
				sql += " ORDER BY NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') ";
				setSQL(sql);
				//
				myLog.info("LAPORAN TOTAL KESELURUHAN HARTA :: "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			
		while (rs.next()) {
	    	h = new Hashtable();			    	
	    	h.put("bil", bil);		        
	    	h.put("jumlahHA", rs.getDouble("Y"));
	    	h.put("tahun", Utils.isNull(rs.getString("X")));		    			
	    	h.put("jumlahHTA", rs.getDouble("Y1"));
	    	h.put("jumlahHAIslam", rs.getDouble("HAISLAM"));
	    	h.put("jumlahHABukanIslam", rs.getDouble("HABUKANISLAM"));
	    	h.put("jumlahHTABukanIslam", rs.getDouble("HTABUKANISLAM"));
	    	h.put("jumlahHTAIslam", rs.getDouble("HTAISLAM"));
	    	// Untuk kegunaan Pilihan Tahun
	    	h.put("jumlahHarta", (rs.getDouble("Y")+rs.getDouble("Y1")));		    			
	    	vecHarta.addElement(h);
	    	bil++;
			}      
		
		}finally{
			if(db != null)db.close();
		}	
		return vecHarta;					
	
	}

	

	@Override
	public Vector getJumlahHartaSelesai(String ID_NEGERI, String ID_PEJABATJKPTG,String socTahun,String socTahunHingga,String idSubUrusan)throws Exception {		 		 
		Db db = null;
		String sql = "";
		Vector vecHarta = new Vector();
		System.out.println("ID NEGERI ---" +ID_NEGERI);
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();		
				
				sql =" SELECT NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') AS X, "+
						" COUNT(P.ID_PERMOHONAN) AS JUMLAH_PERMOHONAN, "+
						" SUM(NVL(HTA.NILAI_HTA_TARIKHMOHON,0)) AS JUMLAH_HTA, "+
						" SUM(NVL(HA.NILAI_HA_TARIKHMOHON,0)) AS JUMLAH_HA, "+
						" NVL (SUM (HTA.NILAI_HTA_TARIKHMOHON), '0') + NVL (SUM (HA.NILAI_HA_TARIKHMOHON), '0') AS JUMLAH_HARTA, "+
						" SUM(NVL(CASE WHEN P.ID_STATUS IN ('21','25','64','163','166','167','180','164','165') THEN HTA.NILAI_HTA_TARIKHMOHON ELSE 0 END,0)) AS JUMLAH_HTA_SELESAI, "+
						" SUM(NVL(CASE WHEN P.ID_STATUS IN ('21','25','64','163','166','167','180','164','165') THEN HA.NILAI_HA_TARIKHMOHON ELSE 0 END,0)) AS JUMLAH_HA_SELESAI, "+
						" SUM(NVL(CASE WHEN P.ID_STATUS NOT IN ('21','25','64','163','166','167','180','164','165') THEN HTA.NILAI_HTA_TARIKHMOHON ELSE 0 END,0)) AS JUMLAH_HTA_TAKSELESAI, "+
						" SUM(NVL(CASE WHEN P.ID_STATUS NOT IN ('21','25','64','163','166','167','180','164','165') THEN HA.NILAI_HA_TARIKHMOHON ELSE 0 END,0)) AS JUMLAH_HA_TAKSELESAI, "+
						" SUM(NVL(CASE WHEN P.ID_STATUS IN ('21','25','64','163','166','167','180','164','165') THEN HTA.NILAI_HTA_TARIKHMOHON ELSE 0 END,0)) "+
						" + SUM(NVL(CASE WHEN P.ID_STATUS IN ('21','25','64','163','166','167','180','164','165') THEN HA.NILAI_HA_TARIKHMOHON ELSE 0 END,0)) AS SELESAI, "+
						" SUM(NVL(CASE WHEN P.ID_STATUS NOT IN ('21','25','64','163','166','167','180','164','165') THEN HTA.NILAI_HTA_TARIKHMOHON ELSE 0 END,0)) "+
						" + SUM(NVL(CASE WHEN P.ID_STATUS NOT IN ('21','25','64','163','166','167','180','164','165') THEN HA.NILAI_HA_TARIKHMOHON ELSE 0 END,0)) AS BELUM_SELESAI "+
						" FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLPPKSIMATI SM, "+
						" TBLPPKPERMOHONANSIMATI PSM, "+
						" (SELECT ID_PERMOHONANSIMATI, SUM(NILAI_HTA_TARIKHMOHON) AS NILAI_HTA_TARIKHMOHON FROM TBLPPKHTA GROUP BY ID_PERMOHONANSIMATI) HTA, "+
						" (SELECT ID_PERMOHONANSIMATI, SUM(NILAI_HA_TARIKHMOHON) AS NILAI_HA_TARIKHMOHON FROM TBLPPKHA GROUP BY ID_PERMOHONANSIMATI) HA "+
						" WHERE "+
						" F.ID_FAIL = P.ID_FAIL "+
						" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI "+
						//AND P.ID_STATUS IN ('21','25','64','163','166','167','180','164','165')
						" AND PSM.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI(+) "+
						" AND PSM.ID_PERMOHONANSIMATI = HA.ID_PERMOHONANSIMATI(+) ";
				
				/*sql =" SELECT   X, NVL(SUM(JUMLAH_HTA),'0')JUMLAH_HTA, NVL(SUM(JUMLAH_HTA_SELESAI),'0') JUMLAH_HTA_SELESAI,  NVL(SUM(JUMLAH_HTA_TAKSELESAI),'0') JUMLAH_HTA_TAKSELESAI, "+  
				     " NVL(SUM(JUMLAH_HA),'0') JUMLAH_HA ,NVL(SUM(JUMLAH_HA_SELESAI),'0') JUMLAH_HA_SELESAI,NVL(SUM(JUMLAH_HA_TAKSELESAI),'0') JUMLAH_HA_TAKSELESAI, "+  
			         " NVL (SUM (JUMLAH_HTA), '0') + NVL (SUM (JUMLAH_HA), '0') JUMLAH_HARTA,"+
			         " NVL (SUM (JUMLAH_HTA_SELESAI), '0') + NVL (SUM (JUMLAH_HA_SELESAI), '0') SELESAI, "+
			         " NVL (SUM (JUMLAH_HA_TAKSELESAI), '0') + NVL (SUM (JUMLAH_HTA_TAKSELESAI), '0') BELUM_SELESAI "+
				     " FROM ( "+  
		             " SELECT NVL (TO_CHAR (P.TARIKH_MOHON, 'YYYY'), '0000') X, JUMLAH_HTA,  JUMLAH_HTA_SELESAI, JUMLAH_HTA_TAKSELESAI, JUMLAH_HA, JUMLAH_HA_SELESAI,JUMLAH_HA_TAKSELESAI  "+  
			         " FROM TBLPFDFAIL A,"+  
			         " TBLPPKPERMOHONAN P,"+  
			         " TBLRUJNEGERI C,"+  
			         " (SELECT X, ID_PERMOHONAN, SUM(JUMLAH_HTA)JUMLAH_HTA FROM ("+  
			         "  SELECT  NVL (TO_CHAR (PI.TARIKH_MOHON, 'YYYY'), '0000') X, PI.ID_PERMOHONAN, NVL (HTA.NILAI_HTA_TARIKHMOHON, 0) JUMLAH_HTA"+  
			         " FROM TBLPFDFAIL AI, TBLPPKPERMOHONAN PI, TBLPPKPERMOHONANSIMATI PS, TBLPPKHTA HTA"+  
			         " WHERE AI.ID_FAIL = PI.ID_FAIL"+  
			         " AND PI.ID_PERMOHONAN =PS.ID_PERMOHONAN"+  
			         " AND PS.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI)"+        
			         " GROUP BY X,ID_PERMOHONAN) HTA,"+  
			         " (SELECT X, ID_PERMOHONAN, SUM(JUMLAH_HTA)JUMLAH_HTA_SELESAI FROM ("+  
			         " SELECT  NVL (TO_CHAR (PI.TARIKH_MOHON, 'YYYY'), '0000') X, PI.ID_PERMOHONAN, NVL (HTA.NILAI_HTA_TARIKHMOHON, 0) JUMLAH_HTA"+  
			         " FROM TBLPFDFAIL AI, TBLPPKPERMOHONAN PI, TBLPPKPERMOHONANSIMATI PS, TBLPPKHTA HTA"+  
			         " WHERE AI.ID_FAIL = PI.ID_FAIL"+  
			         " AND PI.ID_PERMOHONAN =PS.ID_PERMOHONAN"+  
			         " AND PI.ID_STATUS IN ('21', '25')"+  
			         " AND PS.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI)"+        
			         " GROUP BY X,ID_PERMOHONAN) HTA_SELESAI,"+  
			         " (SELECT X, ID_PERMOHONAN, SUM(JUMLAH_HTA)JUMLAH_HTA_TAKSELESAI FROM ("+  
			         " SELECT  NVL (TO_CHAR (PI.TARIKH_MOHON, 'YYYY'), '0000') X, PI.ID_PERMOHONAN, NVL (HTA.NILAI_HTA_TARIKHMOHON, 0) JUMLAH_HTA"+  
			         " FROM TBLPFDFAIL AI, TBLPPKPERMOHONAN PI, TBLPPKPERMOHONANSIMATI PS, TBLPPKHTA HTA"+  
			         " WHERE AI.ID_FAIL = PI.ID_FAIL"+  
			          " AND PI.ID_PERMOHONAN =PS.ID_PERMOHONAN"+  
			         " AND PI.ID_STATUS NOT IN ('21', '25')"+  
			         " AND PS.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI)"+        
			         " GROUP BY X,ID_PERMOHONAN) HTA_TAKSELESAI,"+  
			         " (SELECT X, ID_PERMOHONAN, SUM(JUMLAH_HA)JUMLAH_HA FROM ("+  
			         " SELECT  NVL (TO_CHAR (PI.TARIKH_MOHON, 'YYYY'), '0000') X,PI.ID_PERMOHONAN, NVL (HA.NILAI_HA_TARIKHMOHON, 0) JUMLAH_HA"+  
			         " FROM TBLPFDFAIL AI, TBLPPKPERMOHONAN PI, TBLPPKPERMOHONANSIMATI PS, TBLPPKHA HA"+  
			         " WHERE AI.ID_FAIL = PI.ID_FAIL"+  
			         " AND PI.ID_PERMOHONAN =PS.ID_PERMOHONAN"+  
			        " AND PS.ID_PERMOHONANSIMATI = HA.ID_PERMOHONANSIMATI)"+        
			        " GROUP BY X,ID_PERMOHONAN ) HA,"+  
			        "(SELECT X, ID_PERMOHONAN, SUM(JUMLAH_HA)JUMLAH_HA_SELESAI FROM ("+  
			        " SELECT  NVL (TO_CHAR (PI.TARIKH_MOHON, 'YYYY'), '0000') X,PI.ID_PERMOHONAN, NVL (HA.NILAI_HA_TARIKHMOHON, 0) JUMLAH_HA"+  
			        " FROM TBLPFDFAIL AI, TBLPPKPERMOHONAN PI, TBLPPKPERMOHONANSIMATI PS, TBLPPKHA HA"+  
			        " WHERE AI.ID_FAIL = PI.ID_FAIL"+  
			        " AND PI.ID_PERMOHONAN =PS.ID_PERMOHONAN"+  
			        " AND PI.ID_STATUS IN ('21', '25')"+  
			        " AND PS.ID_PERMOHONANSIMATI = HA.ID_PERMOHONANSIMATI)"+        
			         " GROUP BY X,ID_PERMOHONAN ) HA_SELESAI,"+  
			         " (SELECT X, ID_PERMOHONAN, SUM(JUMLAH_HA)JUMLAH_HA_TAKSELESAI FROM ("+  
			         " SELECT  NVL (TO_CHAR (PI.TARIKH_MOHON, 'YYYY'), '0000') X,PI.ID_PERMOHONAN, NVL (HA.NILAI_HA_TARIKHMOHON, 0) JUMLAH_HA"+  
			        " FROM TBLPFDFAIL AI, TBLPPKPERMOHONAN PI, TBLPPKPERMOHONANSIMATI PS, TBLPPKHA HA"+  
			        " WHERE AI.ID_FAIL = PI.ID_FAIL"+  
			        " AND PI.ID_PERMOHONAN =PS.ID_PERMOHONAN"+  
			        " AND PI.ID_STATUS NOT IN ('21', '25')"+  
			        " AND PS.ID_PERMOHONANSIMATI = HA.ID_PERMOHONANSIMATI)"+        
			        " GROUP BY X,ID_PERMOHONAN ) HA_TAKSELESAI"+  
			        " ,TBLPPKPERMOHONANSIMATI M, TBLPPKSIMATI N"+  
			        " WHERE        A.ID_FAIL = P.ID_FAIL"+  
			       " AND A.ID_NEGERI = C.ID_NEGERI(+)"+  
			       " AND A.ID_SEKSYEN = '2'"+  
			       " AND A.ID_STATUS NOT IN ('999')"+  
			       " AND P.ID_PERMOHONAN = HTA.ID_PERMOHONAN(+)"+    
			       " AND P.ID_PERMOHONAN = HTA_SELESAI.ID_PERMOHONAN(+)"+    
			       " AND P.ID_PERMOHONAN = HTA_TAKSELESAI.ID_PERMOHONAN(+)"+   
			       " AND P.ID_PERMOHONAN = HA.ID_PERMOHONAN(+)"+  
			       " AND P.ID_PERMOHONAN = HA_SELESAI.ID_PERMOHONAN(+)"+         
			       " AND P.ID_PERMOHONAN = HA_TAKSELESAI.ID_PERMOHONAN(+)"+  
			       " AND M.ID_PERMOHONAN = P.ID_PERMOHONAN"+       
			       " AND M.ID_SIMATI = N.ID_SIMATI"+      
			       " AND (HTA.JUMLAH_HTA > 0 OR HA.JUMLAH_HA > 0"+    
			      " OR HTA_SELESAI.JUMLAH_HTA_SELESAI > 0 OR HA_TAKSELESAI.JUMLAH_HA_TAKSELESAI > 0"+  
			      " OR HA_SELESAI.JUMLAH_HA_SELESAI > 0 OR HTA_TAKSELESAI.JUMLAH_HTA_TAKSELESAI > 0 )"+  
			      //" AND P.ID_NEGERIMHN = 1 "+  
			      " and a.ID_SUBURUSAN in (59,60)"+  
			       " AND N.JENIS_AGAMA = 1";  
			     // " )";
*/				
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
				//sql += ")";
				
				// TAHUN 
				if (socTahun != null ) {
					if (!socTahun.trim().equals("")) {
						if (!socTahunHingga.trim().equals("")) {
							sql = sql + " AND NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') >= " + socTahun +" AND NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') <= "+socTahunHingga;
						}else{
							sql = sql + " AND NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') = " + socTahun +"";
						}
					}
				}
				
				sql+=" GROUP BY TO_CHAR(P.TARIKH_MOHON,'YYYY') "+
						" ORDER BY NVL(TO_CHAR(P.TARIKH_MOHON,'YYYY'),'0000') ";
				
							
	    		// BULAN
//				if (socBulan != null) {
//					if (!socBulan.trim().equals("")) {
//						sql = sql + " AND TO_CHAR(B.TARIKH_PERMOHONAN,'MM') = '" + socBulan +"' ";
//					}
//				}
	    		// NEGERI
//				if (idNegeri != null) {
//					if (!idNegeri.trim().equals("")) {
//						sql = sql + " AND P.ID_NEGERIMHN = " + idNegeri +"";
//					}
//				}	
//				// SUB URUSAN
//				if (idSubUrusan != null) {
//					if (!idSubUrusan.trim().equals("")) {
//						sql = sql + " AND A.ID_SUBURUSAN = " + idSubUrusan +"";
//					}
//				}					
//				
//				sql += " ) ";
//	    		// TAHUN 
//				if (socTahun != null ) {
//					if (!socTahun.trim().equals("")) {
//						if (!socTahunHingga.trim().equals("")) {
//							sql = sql + " WHERE X >= " + socTahun +" AND X <= "+socTahunHingga;
//						}else{
//							sql = sql + " WHERE X = " + socTahun +"";
//						}
//					}
//				}				
//				if (socTahun != null && socTahunHingga != null) {
//					if (!socTahun.trim().equals("") && !socTahunHingga.trim().equals("")) {
//						sql = sql + " WHERE X >= " + socTahun +" AND X <= "+socTahunHingga;
//					}
//				}				
				//sql += " WHERE X >= 2009 AND X <= 2010 ";  
						
				setSQL(sql);
				myLog.info("LAPORAN TOTAL KESELURUHAN HARTA SELESAI :: "+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				int bil = 1;
				
			while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("bil", bil);		        
		    	h.put("jumlahHA", rs.getDouble("JUMLAH_HA"));
		    	h.put("tahun", Utils.isNull(rs.getString("X")));		    			
		    	h.put("jumlahHA_SELESAI", rs.getDouble("JUMLAH_HA_SELESAI"));
		    	h.put("jumlahHA_TAKSELESAI", rs.getDouble("JUMLAH_HA_TAKSELESAI"));
		    	h.put("jumlahHTA", rs.getDouble("JUMLAH_HTA"));
		    	h.put("jumlahHTA_SELESAI", rs.getDouble("JUMLAH_HTA_SELESAI"));
		    	h.put("jumlahHTA_TAKSELESAI", rs.getDouble("JUMLAH_HTA_TAKSELESAI"));
		    	h.put("jumlahHarta", rs.getDouble("JUMLAH_HARTA"));
		    	h.put("jumlahHartaS", rs.getDouble("SELESAI"));
		    	h.put("jumlahHartaX", rs.getDouble("BELUM_SELESAI"));
		    	// Untuk kegunaan Pilihan Tahun
		    	//h.put("jumlahHarta", (rs.getDouble("Y")+rs.getDouble("Y1")));		    			
		    	//h.put("jumlahHAS", rs.getDouble("JUMLAH_SELESAIHA"));
		    	//h.put("jumlahHTAS", rs.getDouble("JUMLAH_SELESAIHTA"));
//		    	h.put("jumlahHartaS", (rs.getDouble("JUMLAH_SELESAIHA")+rs.getDouble("JUMLAH_SELESAIHTA")));		    			
//		    	h.put("jumlahHartaX", (
//		    			(rs.getDouble("Y")+rs.getDouble("Y1"))
//		    			-(rs.getDouble("JUMLAH_SELESAIHA")+rs.getDouble("JUMLAH_SELESAIHTA"))
//		    			));		    			
		    	vecHarta.addElement(h);
		    	bil++;
			}      
		
		}finally{
			if(db != null)db.close();
		}	
		return vecHarta;					
	
	}
	
}	// CLOSE MAIN CLASS
