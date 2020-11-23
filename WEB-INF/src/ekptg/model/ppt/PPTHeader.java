package ekptg.model.ppt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class PPTHeader {
	static Logger myLogger = Logger.getLogger(PPTHeader.class);
	
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	private static Vector dataHeader = null;
	
	@SuppressWarnings("unchecked")
	public static  Vector getDataHeader(){
		return dataHeader;
	}
	
	private Vector senaraiSubFail = null;	
	public Vector listPerjalananFail(String id_permohonan) throws Exception {
	
		Db db = null;
		senaraiSubFail = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			Hashtable h;
			int bil = 1;
			Integer count = 0;			
			String id_suburusan = getIdSubUrusan(id_permohonan,db);
			
			if(id_suburusan.equals("52"))
			{
				
				sql = " SELECT DISTINCT (CASE "+
						" WHEN S.ID_STATUS IN ('11','138','127') "+
						" THEN 'PENDAFTARAN PERMOHONAN' "+
						" WHEN S.ID_STATUS IN ('16') "+
						" THEN 'MAKLUMAT HAKMILIK' "+
						" WHEN S.ID_STATUS IN ('1612198') "+
						" THEN 'AGIHAN TUGAS' "+
						" WHEN S.ID_STATUS IN ('22') "+
						" THEN 'MAKLUMAT JABATAN TEKNIKAL' "+
						" WHEN S.ID_STATUS IN ('148','147') "+
						" THEN 'LAPORAN AWAL TANAH' "+
						" WHEN S.ID_STATUS IN ('47','132','133','134','26') "+
						" THEN 'PENYEDIAAN KERTAS MMK' "+
						" WHEN S.ID_STATUS IN ('31') "+
						" THEN 'PEWARTAAN' "+	
						" WHEN S.ID_STATUS IN ('43') "+
						" THEN 'MAKLUMAT JPPH/JPBD'          "+   
						" WHEN S.ID_STATUS IN ('38') "+
						" THEN 'PENANDAAN KAWASAN'          "+   
						" WHEN S.ID_STATUS IN ('46') "+
						" THEN 'LAPORAN TANAH TERPERINCI'          "+
						" WHEN S.ID_STATUS IN ('35') "+
						" THEN 'MEMORIAL/ENDOSAN DHDK'          "+
						" WHEN S.ID_STATUS IN ('54') "+
						" THEN 'BORANG E & F'          "+
						" WHEN S.ID_STATUS IN ('52') "+
						" THEN 'NOTIS AWAM'          "+
						" WHEN S.ID_STATUS IN ('58') "+
						" THEN 'BUKTI PENYAMPAIAN NOTIS BORANG E'          "+
						" WHEN S.ID_STATUS IN ('62') "+
						" THEN 'SIASATAN'          "+
						" WHEN S.ID_STATUS IN ('68','72') "+
						" THEN 'PAMPASAN'          "+
						" WHEN S.ID_STATUS IN ('76') "+
						" THEN 'BORANG K'          "+
						" WHEN S.ID_STATUS IN ('1610242') "+
						" THEN 'ENDORSAN K'          "+
						" WHEN S.ID_STATUS IN ('59') "+
						" THEN 'PENGAMBILAN SEGERA'          "+
						" WHEN S.ID_STATUS IN ('82') "+
						" THEN 'PERMINTAAN UKUR'          "+
						" WHEN S.ID_STATUS IN ('235') "+
						" THEN 'PEMBATALAN PENGAMBILAN' "+
						" WHEN S.ID_STATUS IN ('74') "+
						" THEN 'PENARIKAN BALIK PENGAMBILAN' "+  
						" WHEN S.ID_STATUS IN ('181','183','184','185','1610248') "+
						" THEN 'BANTAHAN MAHKAMAH (PIHAK BERKEPENTINGAN)'  "+
						" WHEN S.ID_STATUS IN ('199','200','201','1610249','203') "+
						" THEN 'BANTAHAN MAHKAMAH (AGENSI)'  "+
						" WHEN S.ID_STATUS IN ('186','187','220') "+
						" THEN 'URUSAN BAYARAN BANTAHAN MAHKAMAH'    "+ 
						
						

						
						" ELSE 'LAIN-LAIN' "+
						" END "+
						" ) AS STATUS, "+
						//--------------------------------------------------					
						" (CASE "+
						" WHEN S.ID_STATUS IN ('11','138','127') "+
						" THEN '11' "+
						" WHEN S.ID_STATUS IN ('16') "+
						" THEN '127' "+
						" WHEN S.ID_STATUS IN ('1612198') "+
						" THEN '1612198' "+
						" WHEN S.ID_STATUS IN ('22') "+
						" THEN '22' "+
						" WHEN S.ID_STATUS IN ('148','147') "+
						" THEN '147' "+
						" WHEN S.ID_STATUS IN ('47','132','133','134','26') "+
						" THEN '26' "+
						" WHEN S.ID_STATUS IN ('31') "+
						" THEN '31' "+	
						" WHEN S.ID_STATUS IN ('43') "+
						" THEN '43'          "+ 
						" WHEN S.ID_STATUS IN ('38') "+
						" THEN '38'          "+ 
						" WHEN S.ID_STATUS IN ('46') "+
						" THEN '46'          "+   
						" WHEN S.ID_STATUS IN ('35') "+
						" THEN '35'          "+
						" WHEN S.ID_STATUS IN ('54') "+
						" THEN '54'          "+
						" WHEN S.ID_STATUS IN ('52') "+
						" THEN '52'          "+
						" WHEN S.ID_STATUS IN ('58') "+
						" THEN '58'          "+
						" WHEN S.ID_STATUS IN ('62') "+
						" THEN '62'          "+
						" WHEN S.ID_STATUS IN ('68','72') "+
						" THEN '68'          "+
						" WHEN S.ID_STATUS IN ('76') "+
						" THEN '76'          "+
						" WHEN S.ID_STATUS IN ('1610242') "+
						" THEN '1610242'          "+
						" WHEN S.ID_STATUS IN ('59') "+
						" THEN '59'          "+
						" WHEN S.ID_STATUS IN ('82') "+
						" THEN '82'          "+
						" WHEN S.ID_STATUS IN ('235') "+
						" THEN '235' "+
						" WHEN S.ID_STATUS IN ('74') "+
						" THEN '74' "+  
						" WHEN S.ID_STATUS IN ('181','183','184','185','1610248') "+
						" THEN '181'  "+
						" WHEN S.ID_STATUS IN ('199','200','201','1610249','203','') "+
						" THEN '199'  "+
						" WHEN S.ID_STATUS IN ('186','187','220') "+
						" THEN '186'  "+         
						
						
						" ELSE '11' "+
						" END "+
						" ) AS ID_STATUS, "+
						//-----------------------------------------------------------
						" TO_NUMBER (CASE "+
						" WHEN S.ID_STATUS IN ('11','138','127') "+
						" THEN 1 "+
						" WHEN S.ID_STATUS IN ('16') "+
						" THEN 2 "+
						" WHEN S.ID_STATUS IN ('1612198') "+
						" THEN 3 "+
						" WHEN S.ID_STATUS IN ('22') "+
						" THEN 4 "+
						" WHEN S.ID_STATUS IN ('148','147') "+
						" THEN 5 "+
						" WHEN S.ID_STATUS IN ('47','132','133','134','26') "+
						" THEN 6 "+
						" WHEN S.ID_STATUS IN ('31') "+
						" THEN 7 "+  
						" WHEN S.ID_STATUS IN ('43') "+
						" THEN 8          "+ 
						" WHEN S.ID_STATUS IN ('38') "+
						" THEN 9          "+ 
						" WHEN S.ID_STATUS IN ('46') "+
						" THEN 11 "+    
						" WHEN S.ID_STATUS IN ('35') "+
						" THEN 12 "+
						" WHEN S.ID_STATUS IN ('54') "+
						" THEN 13          "+
						" WHEN S.ID_STATUS IN ('52') "+
						" THEN 14          "+
						" WHEN S.ID_STATUS IN ('58') "+
						" THEN 15         "+
						" WHEN S.ID_STATUS IN ('62') "+
						" THEN 16         "+
						" WHEN S.ID_STATUS IN ('68','72') "+
						" THEN 17          "+
						" WHEN S.ID_STATUS IN ('76') "+
						" THEN 18         "+
						" WHEN S.ID_STATUS IN ('1610242') "+
						" THEN 21       "+
						" WHEN S.ID_STATUS IN ('59') "+
						" THEN 22          "+
						" WHEN S.ID_STATUS IN ('82') "+
						" THEN 23          "+
						" WHEN S.ID_STATUS IN ('235') "+
						" THEN 24 "+
						" WHEN S.ID_STATUS IN ('74') "+
						" THEN 25 "+ 
						" WHEN S.ID_STATUS IN ('181','183','184','185','1610248') "+
						" THEN 26  "+
						" WHEN S.ID_STATUS IN ('199','200','201','1610249','203','') "+
						" THEN 27  "+
						" WHEN S.ID_STATUS IN ('186','187','220') "+
						" THEN 28  "+         
						
						
						" ELSE 26 "+
						" END "+
						" ) AS ID_ORDER, "+
						//-------------------------------------------------------------
						" P.ID_PERMOHONAN,F.ID_FAIL, "+
						" P.FLAG_SEGERA,P.FLAG_JENISPERMOHONAN, F.NO_FAIL AS NO_JKPTG, P.NO_RUJUKAN_PTG, "+
						" P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_UPT,F.ID_SUBURUSAN,to_char(P.TARIKH_PERMOHONAN,'DD/MM/YYYY') as TARIKH_PERMOHONAN         "+
						" FROM TBLPFDFAIL F, "+
						" TBLPPTPERMOHONAN P, "+
						" TBLRUJSUBURUSANSTATUSFAILPPT SUS, "+
						" TBLRUJSUBURUSANSTATUS SS, "+
						" TBLRUJSTATUS S "+
						" WHERE P.ID_FAIL = F.ID_FAIL "+
						" AND F.ID_FAIL = SUS.ID_FAIL "+
						" AND SUS.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS "+
						" AND SS.ID_STATUS = S.ID_STATUS           "+
						" AND P.ID_PERMOHONAN = '"+id_permohonan+"' "+
						" AND F.ID_SUBURUSAN IN (51, 52, 53) "+
						" AND P.ID_STATUS NOT IN (8, 999) "+
						" AND SS.ID_STATUS NOT IN "+
						" (1610223, 1610224, 1610225, 1610226, 1610227, 1610228, 1610229, "+
						" 1610230, 1610231, 1610232, 1610233, 1610234) "+
						" ORDER BY ID_ORDER ASC ";
				
			}
			else
			{
				sql = " SELECT DISTINCT (CASE "+
						" WHEN S.ID_STATUS IN ('11','138','127') "+
						" THEN 'PENDAFTARAN PERMOHONAN' "+
						" WHEN S.ID_STATUS IN ('16') "+
						" THEN 'MAKLUMAT HAKMILIK' "+
						" WHEN S.ID_STATUS IN ('1612198') "+
						" THEN 'AGIHAN TUGAS' "+
						" WHEN S.ID_STATUS IN ('148','147') "+
						" THEN 'LAPORAN AWAL TANAH' "+
						" WHEN S.ID_STATUS IN ('43','22') "+
						" THEN 'MAKLUMAT JPPH/JPBD'          "+           
						" WHEN S.ID_STATUS IN ('47','132','133','134','26') "+
						" THEN 'PENYEDIAAN KERTAS MMK' "+
						" WHEN S.ID_STATUS IN ('235') "+
						" THEN 'PEMBATALAN PENGAMBILAN' "+
						" WHEN S.ID_STATUS IN ('31') "+
						" THEN 'PEWARTAAN' "+
						" WHEN S.ID_STATUS IN ('74') "+
						" THEN 'PENARIKAN BALIK PENGAMBILAN' "+  
						" WHEN S.ID_STATUS IN ('35') "+
						" THEN 'MAKLUMAT ENDORSAN/MEMORIAL DHDK' "+
						" WHEN S.ID_STATUS IN ('52') "+
						" THEN 'NOTIS AWAM' "+
						" WHEN S.ID_STATUS IN ('54') "+
						" THEN 'BORANG E & F' "+
						" WHEN S.ID_STATUS IN ('58') "+
						" THEN 'BUKTI PENYAMPAIAN NOTIS BORANG E' "+
						" WHEN S.ID_STATUS IN ('38') "+
						" THEN 'PENANDAAN KAWASAN' "+
						" WHEN S.ID_STATUS IN ('62') "+
						" THEN 'SIASATAN' "+
						" WHEN S.ID_STATUS IN ('1610192','1610194') "+
						" THEN 'PERUNDINGAN' "+
						" WHEN S.ID_STATUS IN ('68','72') "+
						" THEN 'PENYEDIAAN PAMPASAN'  "+
						" WHEN S.ID_STATUS IN ('1610193') "+
						" THEN 'RUJUKAN KE MAHKAMAH (PENDUDUKAN SEMENTARA)' "+   
						" WHEN S.ID_STATUS IN ('59') "+
						" THEN 'PENGAMBILAN SEGERA (BORANG I)' "+ 
						" WHEN S.ID_STATUS IN ('76') "+
						" THEN 'BORANG K'    "+
						" WHEN S.ID_STATUS IN ('82') "+
						" THEN 'PERMINTAAN UKUR'  "+
						
						
						" WHEN S.ID_STATUS IN ('181','183','184','185','1610248') "+
						" THEN 'BANTAHAN MAHKAMAH (PIHAK BERKEPENTINGAN)'  "+
						" WHEN S.ID_STATUS IN ('199','200','201','1610249','203') "+
						" THEN 'BANTAHAN MAHKAMAH (AGENSI)'  "+
						" WHEN S.ID_STATUS IN ('186','187','220') "+
						" THEN 'URUSAN BAYARAN BANTAHAN MAHKAMAH'    "+                                                                                    
						" ELSE 'LAIN-LAIN' "+
						" END "+
						" ) AS STATUS, "+
						" (CASE "+
						" WHEN S.ID_STATUS IN ('11','138','127') "+
						" THEN '11' "+
						" WHEN S.ID_STATUS IN ('16') "+
						" THEN '127' "+
						" WHEN S.ID_STATUS IN ('1612198') "+
						" THEN '148' "+
						" WHEN S.ID_STATUS IN ('148','147') "+
						" THEN '147' "+
						" WHEN S.ID_STATUS IN ('43','22') "+
						" THEN '43' "+
						" WHEN S.ID_STATUS IN ('47','132','133','134','26') "+
						" THEN '26' "+
						" WHEN S.ID_STATUS IN ('235') "+
						" THEN '235' "+
						" WHEN S.ID_STATUS IN ('31') "+
						" THEN '31' "+
						" WHEN S.ID_STATUS IN ('74') "+
						" THEN '74'   "+
						" WHEN S.ID_STATUS IN ('35') "+
						" THEN '35' "+
						" WHEN S.ID_STATUS IN ('52') "+
						" THEN '52' "+
						" WHEN S.ID_STATUS IN ('54') "+
						" THEN '54' "+
						" WHEN S.ID_STATUS IN ('58') "+
						" THEN '58' "+
						" WHEN S.ID_STATUS IN ('38') "+
						" THEN '38' "+
						" WHEN S.ID_STATUS IN ('62') "+
						" THEN '62' "+
						" WHEN S.ID_STATUS IN ('1610192','1610194') "+
						" THEN '62' "+
						" WHEN S.ID_STATUS IN ('68','72') "+
						" THEN '68'  "+
						" WHEN S.ID_STATUS IN ('1610193') "+
						" THEN '1610193'    "+
						" WHEN S.ID_STATUS IN ('59') "+
						" THEN '59'  "+
						" WHEN S.ID_STATUS IN ('76') "+
						" THEN '76'    "+
						" WHEN S.ID_STATUS IN ('82') "+
						" THEN '82'  "+
						" WHEN S.ID_STATUS IN ('181','183','184','185','1610248') "+
						" THEN '181'  "+
						" WHEN S.ID_STATUS IN ('199','200','201','1610249','203','') "+
						" THEN '199'  "+
						" WHEN S.ID_STATUS IN ('186','187','220') "+
						" THEN '186'  "+                                                                                      
						" ELSE '11' "+
						" END "+
						" ) AS ID_STATUS, "+
						" TO_NUMBER (CASE "+
						" WHEN S.ID_STATUS IN ('11','138','127') "+
						" THEN 1 "+
						" WHEN S.ID_STATUS IN ('16') "+
						" THEN 2 "+
						" WHEN S.ID_STATUS IN ('1612198') "+
						" THEN 3 "+
						" WHEN S.ID_STATUS IN ('148','147') "+
						" THEN 4 "+
						" WHEN S.ID_STATUS IN ('43','22') "+
						" THEN 5 "+
						" WHEN S.ID_STATUS IN ('47','132','133','134','26') "+
						" THEN 7 "+
						" WHEN S.ID_STATUS IN ('235') "+
						" THEN 8 "+
						" WHEN S.ID_STATUS IN ('31') "+
						" THEN 9 "+
						" WHEN S.ID_STATUS IN ('74') "+
						" THEN 10 "+
						" WHEN S.ID_STATUS IN ('35') "+
						" THEN 11 "+
						" WHEN S.ID_STATUS IN ('52') "+
						" THEN 12 "+
						" WHEN S.ID_STATUS IN ('54') "+
						" THEN 13 "+
						" WHEN S.ID_STATUS IN ('58') "+
						" THEN 14 "+
						" WHEN S.ID_STATUS IN ('38') "+
						" THEN 15 "+
						" WHEN S.ID_STATUS IN ('62') "+
						" THEN 16 "+
						" WHEN S.ID_STATUS IN ('1610192','1610194') "+
						" THEN 17 "+
						" WHEN S.ID_STATUS IN ('68','72') "+
						" THEN 18 "+
						" WHEN S.ID_STATUS IN ('1610193') "+
						" THEN 19    "+
						" WHEN S.ID_STATUS IN ('59') "+
						" THEN 20  "+
						" WHEN S.ID_STATUS IN ('76') "+
						" THEN 21    "+
						" WHEN S.ID_STATUS IN ('82') "+
						" THEN 22 "+
						" WHEN S.ID_STATUS IN ('181','183','184','185','1610248') "+
						" THEN 23                     "+
						" WHEN S.ID_STATUS IN ('199','200','201','1610249','203') "+
						" THEN 24 "+
						" WHEN S.ID_STATUS IN ('186','187','220') "+
						" THEN 25                                    "+                                                  
						" ELSE 26 "+
						" END "+
						" ) AS ID_ORDER, "+
						" P.ID_PERMOHONAN,F.ID_FAIL, "+
						" P.FLAG_SEGERA,P.FLAG_JENISPERMOHONAN, F.NO_FAIL AS NO_JKPTG, P.NO_RUJUKAN_PTG, "+
						" P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_UPT,F.ID_SUBURUSAN,to_char(P.TARIKH_PERMOHONAN,'DD/MM/YYYY') as TARIKH_PERMOHONAN         "+
						" FROM TBLPFDFAIL F, "+
						" TBLPPTPERMOHONAN P, "+
						" TBLRUJSUBURUSANSTATUSFAILPPT SUS, "+
						" TBLRUJSUBURUSANSTATUS SS, "+
						" TBLRUJSTATUS S "+
						" WHERE P.ID_FAIL = F.ID_FAIL "+
						" AND F.ID_FAIL = SUS.ID_FAIL "+
						" AND SUS.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS "+
						" AND SS.ID_STATUS = S.ID_STATUS           "+
						" AND P.ID_PERMOHONAN = '"+id_permohonan+"' "+
						" AND F.ID_SUBURUSAN IN (51, 52, 53) "+
						" AND P.ID_STATUS NOT IN (8, 999) "+
						" AND SS.ID_STATUS NOT IN "+
						" (1610223, 1610224, 1610225, 1610226, 1610227, 1610228, 1610229, "+
						" 1610230, 1610231, 1610232, 1610233, 1610234) "+
						" ORDER BY ID_ORDER ASC ";
			}
			
					myLogger.info("SQL LIST SUBURUSAN edited:"+sql.toUpperCase());
					
					ResultSet rs = stmt.executeQuery(sql);		
					while (rs.next()){						
						count++;
						h = new Hashtable();
						/*
						h.put("bil", count);
						h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
						h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
						h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
						h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
						h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
						h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
						h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
						h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
						*/
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
						//h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
						h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
						h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
						//h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
						h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
						h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));
						senaraiSubFail.addElement(h);
					}					  
							
			
			} 
		finally {	if (db != null)	db.close();	
		}
		return senaraiSubFail;
	}
	
	public static void setDataHeader(String id_permohonan)throws Exception {
		String id_suburusan = getIdSubUrusan(id_permohonan,null);
		if(id_suburusan.equals("52"))
		{
			//flow baru
			setDataHeaderBaru(id_permohonan);
		}
		else
		{
			//proses sek 4 & sementara remain
			setDataHeaderLama(id_permohonan);
		}
	}
	
	public static void setDataHeaderBaru(String id_permohonan)throws Exception {	    
		dataHeader = new Vector();	
		Db db = null;
	    dataHeader.clear();
	    String sql = "";
	    String flagLot = "";
	    Hashtable getFlagLot = null;
	    
	    try{
	    	db = new Db(); 
	    	if(!id_permohonan.equals("")){	    
	    	    myLogger.info("id_permohonan :::::::::"+id_permohonan);
	    	    getFlagLot = getFlagLot(id_permohonan,db);		
	    	    flagLot = getFlagLot.get("FLAG_REMOVE_LOG").toString();
	    	    myLogger.info("getFlagLot ::::::::: "+getFlagLot.get("FLAG_REMOVE_LOG").toString());
	    		
	    	}
    	    
	    	Statement stmt = db.getStatement();
	    	if(flagLot.equals("") && !id_permohonan.equals("")){
	    		updateLOT(id_permohonan,db);
	    	}
	      
	    		sql = "SELECT DISTINCT (select count(a.id_borangk) from tblpptborangk a where a.id_permohonan = p.id_permohonan)as flag_status_borang_k, ";
	    		sql += " (select count(b.id_permintaanukur) from tblpptpermintaanukur b, tblppthakmilik c where b.id_hakmilik(+) = c.id_hakmilik and c.id_permohonan(+) = p.id_permohonan)as flag_status_permintaanukur, ";
	    	
	    		sql += " (select count(*) from Tblppthakmilik a ";
	    		sql += " where nvl(a.flag_pembatalan_keseluruhan,0) <> 'Y' ";
	    		sql += " and nvl(a.flag_penarikan_keseluruhan,0) <> 'Y' ";
	    		sql += " and a.id_permohonan = p.id_permohonan)as totalHakmilik, ";
	    		
	    		sql += " (select count(*) from Tblppthakmilikborangk a, Tblppthakmilik b ";
	    		sql += " where a.id_hakmilik = b.id_hakmilik ";
	    		sql += " and nvl(b.flag_pembatalan_keseluruhan,0) <> 'Y' ";
	    		sql += " and nvl(b.flag_penarikan_keseluruhan,0) <> 'Y' ";
	    		sql += " and b.id_permohonan = p.id_permohonan ";
	    		sql += " and b.flag_endosan_borangk is not null )as totalHakmilikBorangK, ";

	    		sql += " (select count(c.id_award) "; 
	    		sql += " from tblpptaward c, tblpptsiasatan d, tblppthakmilik e ";
	    		sql += " where c.id_siasatan(+) = d.id_siasatan ";
	    		sql += " and d.id_hakmilik(+) = e.id_hakmilik ";
	    		sql += " and e.id_permohonan = p.id_permohonan ";
	    		sql += " AND d.id_siasatan = (SELECT MAX(d1.id_siasatan) FROM TBLPPTSIASATAN d1 WHERE d1.ID_HAKMILIK = e.ID_HAKMILIK))as flag_status_pampasan, ";
	    		
	    		/**QUERY UNTUK PAGING*/
	    		/**OPEN PAGING 2*/
	    		sql += " (select count(*) from Tblpptpermohonan px, Tblpfdfail fx ";
	    		sql += " where fx.id_fail = px.id_fail "; 
	    		sql += " and px.id_permohonan = p.id_permohonan ";
	    		sql += " AND (PX.FLAG_SEMAK = '2' ";
	    		sql += " OR FX.NO_FAIL IS NOT NULL)  ";
	    		sql += " and fx.no_fail is not null)as flag_open_paging2, ";
	    		
	    		/**OPEN PAGING 3*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='16' "; 
	    		sql += " OR px.id_status='148' OR px.id_status='1612198' "; 
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_pegawai is not null) ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
	    			   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
	    		sql += " OR px.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
	    		sql += " where tx.id_permohonan = p.id_permohonan)))as flag_open_paging3, ";
	    		
	    		/**OPEN PAGING 4*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='148' "; 
	    		sql += " OR " +*/
	    				" px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
 			   		   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
	    		sql += " OR px.id_permohonan in  (select distinct hx.id_permohonan from Tblpptulasanteknikal hx "; 
	    		sql += " where hx.id_jabatanteknikal is not null and hx.id_permohonan = p.id_permohonan)";
	    		sql +=	 " ))as flag_open_paging4, ";
	    		
	    		/**OPEN PAGING 5*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='148' "; 
	    		sql += " OR " +*/
	    				" px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
 			   		   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
	    		sql += " OR px.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
	    		sql += " where tx.id_permohonan = p.id_permohonan)";
	    		sql +=	 " ))as flag_open_paging5, ";
	    		
	    		/**OPEN PAGING 6*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='148' "; 
	    		sql += " OR " +*/
	    				" px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_pegawai is not null) ";      
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
 			   		   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
	    		sql += " OR px.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
	    		sql += " where tx.id_permohonan = p.id_permohonan)";
	    		sql += " OR px.id_permohonan in (select distinct tx.id_permohonan from TblpptMMK tx ";
	    		sql += " where tx.id_permohonan = p.id_permohonan)";
	    		sql +=	 " ))as flag_open_paging6, ";
	    		
	    		/**OPEN PAGING 7*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_permohonan IN (SELECT M.ID_PERMOHONAN FROM TBLPPTMMK M, TBLPPTMMKKEPUTUSAN K "+
	    				" WHERE M.ID_MMK = K.ID_MMK  AND K.STATUS_KEPUTUSAN = 1 AND M.ID_PERMOHONAN = p.id_permohonan) ";    
	    		sql += " OR px.id_permohonan in (select distinct wx.id_permohonan from Tblpptwarta wx ";
	    		sql += " where wx.id_permohonan = p.id_permohonan)))as flag_open_paging7, ";	    		
	    		
	    		/**OPEN PAGING 8*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		//sql += " and (px.id_status='31' ";   
	    		//sql += " OR px.id_status='35' "; 
	    		sql += " AND (px.id_permohonan in (select distinct hx.id_permohonan from Tblpptulasanteknikal hx "; 
	    		sql += " where hx.flag_jenis_ulasan in ('1','2') and hx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.id_permohonan in (select distinct wx.id_permohonan from Tblpptwarta wx ";
	    		sql += " where wx.id_permohonan = p.id_permohonan)))as flag_open_paging8, ";
	    		
	    		/**OPEN PAGING 9*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		//sql += " and (px.id_status='31' ";   
	    		//sql += " OR px.id_status='35' "; 
	    		sql += " AND (px.id_permohonan in (select distinct hx.id_permohonan from Tblppttandakawasan hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.id_permohonan in (select distinct wx.id_permohonan from Tblpptwarta wx ";
	    		sql += " where wx.id_permohonan = p.id_permohonan)))as flag_open_paging9, ";	    		
	    				  
	    		/**OPEN PAGING 5,6,7,8,9*/
	    		/*
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
	    		sql += " where tx.id_permohonan = p.id_permohonan)))as flag_open_paging56789, ";     
	    		*/
	    		
	    		/**OPEN PAGING 10*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " AND (px.id_permohonan in (select distinct hx.id_permohonan from Tblppttandakawasan hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.id_permohonan in (SELECT DISTINCT HM.ID_PERMOHONAN FROM TBLPPTTANAH TN, TBLPPTHAKMILIK HM " +
	    				" WHERE TN.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_PERMOHONAN = p.id_permohonan)))as flag_open_paging10, ";
	    		
	    		/**OPEN PAGING 11*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " AND (px.id_permohonan in (select distinct hx.id_permohonan from Tblppttandakawasan hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.id_permohonan in (SELECT DISTINCT HM.ID_PERMOHONAN FROM TBLPPTTANAH TN, TBLPPTHAKMILIK HM " +
	    				" WHERE TN.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_PERMOHONAN = p.id_permohonan)))as flag_open_paging11, ";	    		
	    		
	    		/**OPEN PAGING 12*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " AND (px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_endosan in ('1','2')) ";
	    		sql += " OR px.id_permohonan in (SELECT DISTINCT HM.ID_PERMOHONAN FROM TBLPPTTANAH TN, TBLPPTHAKMILIK HM " +
	    				" WHERE TN.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_PERMOHONAN = p.id_permohonan)))as flag_open_paging12, ";
	    		
	    		/**OPEN PAGING 13*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and " +
	    				"(" +
	    				/*"px.id_status='35' ";   
	    		sql += " OR px.id_status='54' "; 
	    		sql += " OR " +*/
	    				"px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_endosan in ('1','2')) ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptborange bx, Tblpptborangehakmilik beh "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = beh.id_hakmilik and beh.id_borange = bx.id_borange)))as flag_open_paging13, ";
	    		
	    		
	    		/**OPEN PAGING 14*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		
	    		
	    		sql += " and (";
	    		
	    		
	    		/*"px.id_status='54' ";   
	    		sql += " OR px.id_status='52' "; 
	    		sql += " OR " +*/
	    		sql += "px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx,tblpptborange e," +
	    				"tblpptborangehakmilik eb," +
	    				//"tblpptborangfhakmilikpb a, " +
	    				//"tblpptborangf b, " +
	    				"tblppthakmilikpb c "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan " +
	    				//"and a.id_borangf = b.id_borangf " +			
	    				//"and a.id_hakmilikpb = c.id_hakmilikpb "+
	    				"and c.id_hakmilik = hx.id_hakmilik "+
	    				"and e.id_borange = eb.id_borange " +
	    				//"and hx.id_hakmilik = nah.id_hakmilik and nah.id_notisawam = nx.id_notisawam" +
	    				") ";
	    		
	    		//open komen temp by razman
	    		//sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptborange bx, Tblpptborangehakmilik beh "; 
	    		//sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = beh.id_hakmilik and beh.id_borange = bx.id_borange )";
	    		//close komen temp by razman
	    		
	    		sql += " ))as flag_open_paging14, ";
	    		
	    		
	    		
	    
	    			    		
	    		/**OPEN PAGING 15*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='52' ";   
	    		sql += " OR px.id_status='58' "; 
	    		sql += " OR " +*/
	    				"px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptnotisawam nx, Tblpptnotisawamhakmilik nah "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = nah.id_hakmilik and nah.id_notisawam = nx.id_notisawam) ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
	    		sql += " and flag_jenis_bukti = '1')))as flag_open_paging15, ";
	    		
			      
	    		/**OPEN PAGING 16*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";	    		
	    		//open komen temp by razman
	    		//sql += " and " 
	    		//close komen temp by razman
	    				/*"px.id_status='38' ";   
	    		sql += " OR px.id_status='62' ";
	    		sql += " OR " +*/    		
	    				//"px.id_permohonan in (" +
	    				/*"select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptsiasatan sx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = sx.id_hakmilik) ";
	    		sql += " OR " +*/
	    		
	    		//open komen temp by razman
	    				sql += " and px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptborange bx, Tblpptborangehakmilik beh "; 
	    	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = beh.id_hakmilik and beh.id_borange = bx.id_borange) ";
	    	    //close komen temp by razman	
	    	    		
	    		/*sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
	    		sql += " and flag_jenis_bukti = '1'))" +*/
	    	    		sql += 	" ) as flag_open_paging16, "; 
	    
	    		/**OPEN PAGING 17*/                    
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='68' ";   
	    		sql += " OR " +*/
	    				"px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptsiasatan sx, Tblpptborangg bgx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = sx.id_hakmilik and bgx.id_siasatan = sx.id_siasatan ";
	    		sql += " and sx.id_siasatan = (select max(sx1.id_siasatan) from Tblpptsiasatan sx1 where sx1.id_hakmilik = hx.id_hakmilik) ) ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptsiasatan sx, Tblpptaward ax "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = sx.id_hakmilik and ax.id_siasatan = sx.id_siasatan ";
	    		sql += " and sx.id_siasatan = (select max(sx1.id_siasatan) from Tblpptsiasatan sx1 where sx1.id_hakmilik = hx.id_hakmilik))))as flag_open_paging17, ";                                                                              

	    		/**OPEN PAGING 18*/
	    		sql += " (select count(*) from Tblpptpermohonan px ,TBLPPTBORANGE E";
	    		sql += " where px.id_permohonan = p.id_permohonan AND E.ID_BORANGE IS NOT NULL ";
	    		sql += " and (" +
	    				/*"px.id_status='72' ";   
	    		sql += " OR px.id_status='76' "; 
	    		sql += " OR " +*/
	    				"px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblppthakmilikpb hpbx, Tblpptbayaran bx ";
	    		sql += " where hx.id_permohonan = p.id_permohonan and hpbx.id_hakmilik = hx.id_hakmilik and bx.id_hakmilikpb = hpbx.id_hakmilikpb ";
	    		sql += " and bx.cara_bayar in ('1','2')) ";
	    		sql += " OR px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.flag_segera = '1' AND px.id_permohonan in (select distinct bix.id_permohonan from Tblpptborangi bix "; 
	    		sql += " where bix.id_permohonan = p.id_permohonan) ";                        
	    		sql += " OR px.flag_segera = '3' AND px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_segera_sebahagian = 'Y') ";
	    		sql += " AND px.id_permohonan in (select distinct bix.id_permohonan from Tblpptborangi bix "; 
	    		sql += " where bix.id_permohonan = p.id_permohonan)) ";
	    		sql += " OR (P.FLAG_SEGERA = '1' AND P.ID_PERMOHONAN IN " ; 
	    		sql += " (SELECT P1.ID_PERMOHONAN FROM TBLPPTPERMOHONAN P1, TBLPPTHAKMILIK HM1, TBLPPTBORANGEHAKMILIK BE1, TBLPPTBORANGE E1 WHERE " ; 
	    		sql += " P1.ID_PERMOHONAN = HM1.ID_PERMOHONAN " ; 
	    		sql += " AND BE1.ID_HAKMILIK = HM1.ID_HAKMILIK " ;
	    		sql += " AND E1.ID_BORANGE = BE1.ID_BORANGE AND E1.TARIKH_BORANGE IS NOT NULL) " ; 
	    		sql += " )) as flag_open_paging18, ";

	    		
	    		/**OPEN PAGING 19*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='76' ";   
	    		sql += " OR " +*/
	    				"px.id_permohonan in (select distinct blx.id_permohonan from Tblpptborangl blx "; 
	    		sql += " where blx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan)))as flag_open_paging19, ";
	    		
	    		/**OPEN PAGING 20*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='76' ";   
	    		sql += " OR " +*/
	    				"px.id_permohonan in (select distinct blx.id_permohonan from Tblpptborangl blx "; 
	    		sql += " where blx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
	    		sql += " and flag_jenis_bukti = '3') ";
	    		sql += " OR px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan)))as flag_open_paging20, ";
	    		
	    		/**OPEN PAGING 21*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='76' ";   
	    		sql += " OR " +*/
	    				"px.id_permohonan in (select distinct blx.id_permohonan from Tblpptborangl blx "; 
	    		sql += " where blx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan)))as flag_open_paging21, ";
	    				                            
	    		
	    		/**OPEN PAGING 22*/                    
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='58' AND px.flag_segera = '1' ";
	    		sql += " OR px.id_status='59' ";
	    		sql += " " +
	    				"OR " +*/
	    				"px.flag_segera = '1' AND px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, tblppthakmilikborangk hbk, tblpptborangk kk, Tblpptendosanborangk bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = hbk.id_hakmilik and hbk.id_borangk = kk.id_borangk and  kk.id_borangk = bpx.id_borangk ";
	    		sql += " )) "; 
	    		sql += " OR px.id_permohonan in (select distinct bix.id_permohonan from Tblpptborangi bix "; 
	    		sql += " where bix.id_permohonan = p.id_permohonan) ";
	    		sql += " OR (px.flag_segera = '3' AND px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, tblppthakmilikborangk hbk, tblpptborangk kk, Tblpptendosanborangk bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = hbk.id_hakmilik and hbk.id_borangk = kk.id_borangk and  kk.id_borangk = bpx.id_borangk ";
	    		sql += " and hx.flag_segera_sebahagian = 'Y'))" +
	    				"   ) as flag_open_paging22, ";
	    				                            
	    		/**OPEN PAGING 23*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (" +
	    				/*"px.id_status='82' ";
	    		sql += " OR " +
	    				" px.id_status='72' AND px.flag_segera = '1' ";
	    		sql += " OR " +*/
	    				"px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblppthakmilikpb hpbx, Tblpptbayaran bx ";
	    		sql += " where hx.id_permohonan = p.id_permohonan and hpbx.id_hakmilik = hx.id_hakmilik and bx.id_hakmilikpb = hpbx.id_hakmilikpb ";
	    		sql += " and bx.cara_bayar in ('1','2')) AND px.flag_segera = '1' ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptpermintaanukur pux "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and pux.id_hakmilik = hx.id_hakmilik) ";                        
	    		sql += " OR px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan)))as flag_open_paging23, ";  	    				        		            	    	
	    		
	    		sql += " p.id_kemaskini, p.id_status,ag.nama_agensi,p.id_permohonan, p.tujuan, initcap(p.tujuan) as tujuanInit, p.tarikh_borangf, f.id_negeri as id_projeknegeri, p.id_negeri, initcap(pn.nama_negeri) as projek_negeri,p.no_permohonan, ";  
	    		sql += " p.id_fail, f.no_fail, f.id_suburusan,  p.tarikh_permohonan, p.id_status, f.id_kementerian, p.id_agensi, p.flag_peruntukan, p.flag_segera, "; 
	    		sql += " p.id_daerah, p.no_rujukan_surat, p.tarikh_kehendaki, k.alamat1, k.alamat2, k.alamat3,  k.poskod, p.id_mukim, ";
	    		sql += " initcap(k.nama_kementerian) as nama_kementerian, initcap(b.nama_daerah) as projek_daerah, p.no_rujukan_ptd, p.no_rujukan_ptg,  p.no_rujukan_upt, su.nama_suburusan, s.keterangan as status, "; 
	    		sql += " p.tarikh_terima, pnk.nama_negeri as negeri_kementerian, ";
				sql += " rp.id_pejabat as id_pejabat_ptg,rp.nama_pejabat as nama_pejabat_ptg, ";
				sql += " rp2.id_pejabat as id_pejabat_ptd,rp2.nama_pejabat as nama_pejabat_ptd, ";
				sql += " rk1.id_pejabat as id_endosan_borangk_ptg,rk1.nama_pejabat as nama_endosan_borangk_ptg, ";
				sql += " rk2.id_pejabat as id_endosan_borangk_ptd,rk2.nama_pejabat as nama_endosan_borangk_ptd, ";		
				sql += " p.tarikh_borangb, p.flag_subjaket, p.catatan_borangk_ptg, p.catatan_borangk_ptd, ";
				sql += " p.catatan_endosan_ptg, p.catatan_endosan_ptd, p.flag_status_online, p.catatan_status_online, p.tarikh_surat, initcap(ag.nama_agensi)as agensiKem, upper(pnk.nama_negeri) as negeriKem ";
				sql += " , p.flag_semakan_online ";
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujdaerah b, Tblrujsuburusan su, "; 
				sql += " Tblrujstatus s,  Tblrujnegeri pn, Tblrujagensi ag, Tblrujpejabat rp, Tblrujpejabat rp2, ";
				sql += " Tblrujpejabat rk1, Tblrujpejabat rk2, Tblrujnegeri pnk ";
				sql += " WHERE f.id_kementerian = k.id_kementerian(+) ";
				sql += " AND k.id_negeri = pnk.id_negeri(+)";
				sql += " AND f.id_negeri = pn.id_negeri "; 
				sql += " AND f.id_fail = p.id_fail "; 
				sql += " AND b.id_daerah = p.id_daerah ";
				sql += " AND f.id_suburusan = su.id_suburusan "; 
				sql += " AND p.id_status = s.id_status "; 
				sql += " AND p.id_agensi = ag.id_agensi(+) ";
				sql += " AND p.id_pejabat_ptg = rp.id_pejabat(+) ";
				sql += " AND p.id_pejabat_ptd = rp2.id_pejabat(+) ";
				sql += " AND p.id_endosan_borangk_ptg = rk1.id_pejabat(+) ";
				sql += " AND p.id_endosan_borangk_ptd = rk2.id_pejabat(+) ";
				sql += " AND p.id_permohonan = '"+id_permohonan+"'";
	    		
	    		
	    		///* FORMATTED ON 2017/09/28 15:35 (FORMATTER PLUS V4.8.8) */
				/*
	    		sql = " SELECT DISTINCT COUNT(DISTINCT BK.ID_BORANGK) AS FLAG_STATUS_BORANG_K," +
	    				"COUNT(DISTINCT PU.ID_PERMINTAANUKUR) AS FLAG_STATUS_PERMINTAANUKUR," +
	    				"COUNT(DISTINCT CASE WHEN NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') <> 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') <> 'Y' THEN HM.ID_HAKMILIK END) AS TOTALHAKMILIK," +
	    				"COUNT(DISTINCT CASE WHEN NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') <> 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') <> 'Y' AND HM.FLAG_ENDOSAN_BORANGK IS NOT NULL THEN HBK.ID_HAKMILIKBORANGK END) AS TOTALHAKMILIKBORANGK," +
	    				"COUNT(DISTINCT AW.ID_AWARD) AS FLAG_STATUS_PAMPASAN," +
	    				"COUNT(DISTINCT CASE WHEN P.FLAG_SEMAK = '2' OR F.NO_FAIL IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING2," +
	    				"COUNT(DISTINCT CASE WHEN P.ID_STATUS = '16' OR P.ID_STATUS = '148' OR P.ID_STATUS = '1612198' " +
	    				"OR HM.ID_PEGAWAI IS NOT NULL OR AG.BARIS = '2' OR TU.ID_PERMOHONAN IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING3," +
	    				"COUNT(DISTINCT CASE WHEN AG.BARIS = '2' OR UT.ID_JABATANTEKNIKAL IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING4," +
	    				"COUNT(DISTINCT CASE WHEN AG.BARIS = '2' OR TU.ID_PERMOHONAN IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING5," +
	    				"COUNT(DISTINCT CASE WHEN HM.ID_PEGAWAI IS NOT NULL OR AG.BARIS = '2' OR TU.ID_PERMOHONAN IS NOT NULL OR MMK.ID_PERMOHONAN IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING6," +
	    				"COUNT(DISTINCT CASE WHEN MMK_KEP.STATUS_KEPUTUSAN = 1 OR WARTA.ID_PERMOHONAN IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING7," +
	    				"COUNT(DISTINCT CASE WHEN UT.FLAG_JENIS_ULASAN IN ('1', '2') OR WARTA.ID_PERMOHONAN IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING8," +
	    				"COUNT(DISTINCT CASE WHEN TANDA.ID_PERMOHONAN IS NOT NULL OR WARTA.ID_PERMOHONAN IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING9," +
	    				"COUNT(DISTINCT CASE WHEN TANDA.ID_PERMOHONAN IS NOT NULL OR TANAH.ID_HAKMILIK IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING10," +
	    				"COUNT(DISTINCT CASE WHEN TANDA.ID_PERMOHONAN IS NOT NULL OR TANAH.ID_HAKMILIK IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING11," +
	    				"COUNT(DISTINCT CASE WHEN HM.FLAG_ENDOSAN IN ('1','2') OR TANAH.ID_HAKMILIK IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING12," +
	    				"COUNT(DISTINCT CASE WHEN HM.FLAG_ENDOSAN IN ('1','2') OR BE.ID_BORANGE IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING13," +
	    				"COUNT(DISTINCT CASE WHEN NOTIS.ID_NOTISAWAM IS NOT NULL OR BE.ID_BORANGE IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING14," +
	    				"COUNT(DISTINCT CASE WHEN NOTIS.ID_NOTISAWAM IS NOT NULL OR BUKTI.FLAG_JENIS_BUKTI = '1' THEN 1 END) AS FLAG_OPEN_PAGING15," +
	    				"COUNT(DISTINCT CASE WHEN BE.ID_BORANGE IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING16," +
	    				"COUNT(DISTINCT CASE WHEN BG.ID_SIASATAN IS NOT NULL OR AW.ID_AWARD IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING17," +
	    				"COUNT(DISTINCT CASE WHEN BAYAR.CARA_BAYAR IN ('1', '2') OR HBK.ID_BORANGK IS NOT NULL " +
	    				"OR P.FLAG_SEGERA = '1' OR BI.ID_BORANGI IS NOT NULL  OR (P.FLAG_SEGERA = '3' AND HM.FLAG_SEGERA_SEBAHAGIAN = 'Y') THEN 1 END) AS FLAG_OPEN_PAGING18," +
	    				"COUNT(DISTINCT CASE WHEN BK.ID_BORANGK IS NOT NULL OR BL.ID_BORANGL IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING19," +
	    				"COUNT(DISTINCT CASE WHEN BUKTI.FLAG_JENIS_BUKTI = '3' OR BK.ID_BORANGK IS NOT NULL OR BL.ID_BORANGL IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING20," +
	    				"COUNT(DISTINCT CASE WHEN BK.ID_BORANGK IS NOT NULL OR BL.ID_BORANGL IS NOT NULL THEN 1 END) AS FLAG_OPEN_PAGING21," +
	    				"COUNT(DISTINCT CASE WHEN (P.FLAG_SEGERA = '1' AND ENDOSK.ID_ENDOSANBORANGK IS NOT NULL) OR (BI.ID_BORANGI IS NOT NULL) " +
	    				"OR (P.FLAG_SEGERA = '3' AND HM.FLAG_SEGERA_SEBAHAGIAN = 'Y' AND ENDOSK.ID_ENDOSANBORANGK IS NOT NULL) THEN 1 END) AS FLAG_OPEN_PAGING22," +
	    				"COUNT(DISTINCT CASE WHEN (BAYAR.CARA_BAYAR IN ('1', '2') AND P.FLAG_SEGERA = '1') OR PU.ID_HAKMILIK IS NOT NULL " +
	    				"OR BK.ID_BORANGK IS NOT NULL  THEN 1 END) AS FLAG_OPEN_PAGING23," +
	    				"P.ID_KEMASKINI, P.ID_STATUS, AG.NAMA_AGENSI, P.ID_PERMOHONAN," +
	    				"P.TUJUAN, INITCAP (P.TUJUAN) AS TUJUANINIT, P.TARIKH_BORANGF," +
	    				"F.ID_NEGERI AS ID_PROJEKNEGERI, P.ID_NEGERI," +
	    				"INITCAP (PN.NAMA_NEGERI) AS PROJEK_NEGERI, P.NO_PERMOHONAN," +
	    				"P.ID_FAIL, F.NO_FAIL, F.ID_SUBURUSAN, P.TARIKH_PERMOHONAN," +
	    				"P.ID_STATUS, F.ID_KEMENTERIAN, P.ID_AGENSI, P.FLAG_PERUNTUKAN," +
	    				"P.FLAG_SEGERA, P.ID_DAERAH, P.NO_RUJUKAN_SURAT," +
	    				"P.TARIKH_KEHENDAKI, K.ALAMAT1, K.ALAMAT2, K.ALAMAT3, K.POSKOD," +
	    				"P.ID_MUKIM, INITCAP (K.NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN," +
	    				"INITCAP (B.NAMA_DAERAH) AS PROJEK_DAERAH, P.NO_RUJUKAN_PTD," +
	    				"P.NO_RUJUKAN_PTG, P.NO_RUJUKAN_UPT, SU.NAMA_SUBURUSAN," +
	    				"S.KETERANGAN AS STATUS, P.TARIKH_TERIMA," +
	    				"PNK.NAMA_NEGERI AS NEGERI_KEMENTERIAN," +
	    				"RP.ID_PEJABAT AS ID_PEJABAT_PTG," +
	    				"RP.NAMA_PEJABAT AS NAMA_PEJABAT_PTG," +
	    				"RP2.ID_PEJABAT AS ID_PEJABAT_PTD,RP2.NAMA_PEJABAT AS NAMA_PEJABAT_PTD," +
	    				"RK1.ID_PEJABAT AS ID_ENDOSAN_BORANGK_PTG,RK1.NAMA_PEJABAT AS NAMA_ENDOSAN_BORANGK_PTG," +
	    				"RK2.ID_PEJABAT AS ID_ENDOSAN_BORANGK_PTD,RK2.NAMA_PEJABAT AS NAMA_ENDOSAN_BORANGK_PTD," +
	    				"P.TARIKH_BORANGB, P.FLAG_SUBJAKET, P.CATATAN_BORANGK_PTG," +
	    				"P.CATATAN_BORANGK_PTD, P.CATATAN_ENDOSAN_PTG," +
	    				"P.CATATAN_ENDOSAN_PTD, P.FLAG_STATUS_ONLINE,P.CATATAN_STATUS_ONLINE, P.TARIKH_SURAT," +
	    				"INITCAP (AG.NAMA_AGENSI) AS AGENSIKEM," +
	    				"UPPER (PNK.NAMA_NEGERI) AS NEGERIKEM, P.FLAG_SEMAKAN_ONLINE " +
	    				"FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLRUJKEMENTERIAN K,TBLRUJDAERAH B,TBLRUJSUBURUSAN SU," +
	    				"TBLRUJSTATUS S,TBLRUJNEGERI PN,TBLRUJAGENSI AG,TBLRUJPEJABAT RP,TBLRUJPEJABAT RP2,TBLRUJPEJABAT RK1," +
	    				"TBLRUJPEJABAT RK2,TBLRUJNEGERI PNK,TBLPPTBORANGK BK,TBLPPTBORANGI BI,TBLPPTPERMINTAANUKUR PU," +
	    				"TBLPPTHAKMILIK HM, TBLPPTHAKMILIKBORANGK HBK," +
	    				"(SELECT * FROM (SELECT JMAX.*, MAX(JMAX.TARIKH_SIASATAN) OVER (PARTITION BY JMAX.ID_HAKMILIK) MAX_ID FROM TBLPPTSIASATAN JMAX)" +
	    				"WHERE TARIKH_SIASATAN = MAX_ID) SIA, TBLPPTAWARD AW,  TBLPPTAGIHANHM AG, TBLPPTTANAHUMUM TU, TBLPPTULASANTEKNIKAL UT, TBLPPTMMK MMK," +
	    				"(SELECT * FROM (SELECT JMAX.*, MAX(JMAX.TARIKH_WARTA) OVER (PARTITION BY JMAX.ID_WARTA) MAX_ID FROM TBLPPTWARTA JMAX)" +
	    				"WHERE TARIKH_WARTA = MAX_ID) WARTA, TBLPPTMMKKEPUTUSAN MMK_KEP, TBLPPTTANDAKAWASAN TANDA, TBLPPTTANAH TANAH," +
	    				"TBLPPTBORANGE BE, TBLPPTBORANGEHAKMILIK HBE, TBLPPTNOTISAWAM NOTIS, TBLPPTNOTISAWAMHAKMILIK NOTISHM, TBLPPTBUKTIPENYAMPAIAN BUKTI, TBLPPTBORANGG BG," +
	    				"TBLPPTHAKMILIKPB HPB, TBLPPTBAYARAN BAYAR, TBLPPTBORANGL BL, TBLPPTENDOSANBORANGK ENDOSK " +
	    				"WHERE F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) " +
	    				"AND K.ID_NEGERI = PNK.ID_NEGERI(+) " +
	    				"AND F.ID_NEGERI = PN.ID_NEGERI " +
	    				"AND F.ID_FAIL = P.ID_FAIL " +
	    				"AND B.ID_DAERAH = P.ID_DAERAH " +
	    				"AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN " +
	    				"AND P.ID_STATUS = S.ID_STATUS " +
	    				"AND P.ID_AGENSI = AG.ID_AGENSI(+) " +
	    				"AND P.ID_PEJABAT_PTG = RP.ID_PEJABAT(+) " +
	    				"AND P.ID_PEJABAT_PTD = RP2.ID_PEJABAT(+) " +
	    				"AND P.ID_ENDOSAN_BORANGK_PTG = RK1.ID_PEJABAT(+) " +
	    				"AND P.ID_ENDOSAN_BORANGK_PTD = RK2.ID_PEJABAT(+) " +
	    				"AND P.ID_PERMOHONAN = BK.ID_PERMOHONAN(+) " +
	    				"AND HM.ID_HAKMILIK = PU.ID_HAKMILIK(+)  AND P.ID_PERMOHONAN = HM.ID_PERMOHONAN(+) " +
	    				"AND HM.ID_HAKMILIK = HBK.ID_HAKMILIK(+) AND HM.ID_HAKMILIK = SIA.ID_HAKMILIK(+) " +
	    				"AND SIA.ID_SIASATAN = AW.ID_SIASATAN(+) AND HM.ID_HAKMILIK = AG.ID_HAKMILIK(+) AND P.ID_PERMOHONAN = TU.ID_PERMOHONAN(+) " +
	    				"AND P.ID_PERMOHONAN = UT.ID_PERMOHONAN(+) AND P.ID_PERMOHONAN = MMK.ID_PERMOHONAN(+) AND P.ID_PERMOHONAN = WARTA.ID_PERMOHONAN(+) " +
	    				"AND MMK.ID_MMK = MMK_KEP.ID_MMK(+) AND P.ID_PERMOHONAN = TANDA.ID_PERMOHONAN(+) AND HM.ID_HAKMILIK = TANAH.ID_HAKMILIK(+) " +
	    				"AND HM.ID_HAKMILIK = HBE.ID_HAKMILIK(+) AND HBE.ID_BORANGE = BE.ID_BORANGE(+) AND HM.ID_HAKMILIK = NOTISHM.ID_HAKMILIK(+) " +
	    				"AND NOTISHM.ID_NOTISAWAM = NOTIS.ID_NOTISAWAM(+) AND HM.ID_HAKMILIK = BUKTI.ID_HAKMILIK(+) AND SIA.ID_SIASATAN = BG.ID_SIASATAN(+) " +
	    				"AND HM.ID_HAKMILIK = HPB.ID_HAKMILIKPB(+) AND HPB.ID_HAKMILIKPB = BAYAR.ID_HAKMILIKPB(+) AND P.ID_PERMOHONAN = BI.ID_PERMOHONAN(+) " +
	    				"AND P.ID_PERMOHONAN = BL.ID_PERMOHONAN(+) AND BK.ID_BORANGK = ENDOSK.ID_BORANGK(+) " +
	    				"AND P.ID_PERMOHONAN = '"+id_permohonan+"' " +
	    				"GROUP BY " +
	    				"P.ID_KEMASKINI, P.ID_STATUS, AG.NAMA_AGENSI, P.ID_PERMOHONAN, P.TUJUAN, P.TUJUAN, P.TARIKH_BORANGF," +
	    				"F.ID_NEGERI, P.ID_NEGERI, PN.NAMA_NEGERI, P.NO_PERMOHONAN, P.ID_FAIL, F.NO_FAIL, F.ID_SUBURUSAN, P.TARIKH_PERMOHONAN," +
	    				"P.ID_STATUS, F.ID_KEMENTERIAN, P.ID_AGENSI, P.FLAG_PERUNTUKAN," +
	    				"P.FLAG_SEGERA, P.ID_DAERAH, P.NO_RUJUKAN_SURAT, P.TARIKH_KEHENDAKI, K.ALAMAT1, K.ALAMAT2, K.ALAMAT3, K.POSKOD," +
	    				"P.ID_MUKIM,K.NAMA_KEMENTERIAN, B.NAMA_DAERAH, P.NO_RUJUKAN_PTD," +
	    				"P.NO_RUJUKAN_PTG, P.NO_RUJUKAN_UPT, SU.NAMA_SUBURUSAN, S.KETERANGAN , P.TARIKH_TERIMA, PNK.NAMA_NEGERI,RP.ID_PEJABAT," +
	    				"RP.NAMA_PEJABAT,RP2.ID_PEJABAT,RP2.NAMA_PEJABAT, RK1.ID_PEJABAT,RK1.NAMA_PEJABAT,RK2.ID_PEJABAT,RK2.NAMA_PEJABAT," +
	    				"P.TARIKH_BORANGB, P.FLAG_SUBJAKET, P.CATATAN_BORANGK_PTG," +
	    				"P.CATATAN_BORANGK_PTD, P.CATATAN_ENDOSAN_PTG, P.CATATAN_ENDOSAN_PTD, P.FLAG_STATUS_ONLINE," +
	    				"P.CATATAN_STATUS_ONLINE, P.TARIKH_SURAT,AG.NAMA_AGENSI,PNK.NAMA_NEGERI, P.FLAG_SEMAKAN_ONLINE";
	    		*/
				myLogger.info("setDataHeaderBaru:sql="+sql.toUpperCase());				
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		while (rs.next()) {	    	  
	    			h = new Hashtable(); 			
	    			h.put("flag_open_paging2", rs.getInt("flag_open_paging2")== 0?0:rs.getInt("flag_open_paging2"));
	    			h.put("flag_open_paging3", rs.getInt("flag_open_paging3")== 0?0:rs.getInt("flag_open_paging3"));
	    			h.put("flag_open_paging4", rs.getInt("flag_open_paging4")== 0?0:rs.getInt("flag_open_paging4"));
	    			h.put("flag_open_paging5", rs.getInt("flag_open_paging5")== 0?0:rs.getInt("flag_open_paging5"));	    			
	    			h.put("flag_open_paging6", rs.getInt("flag_open_paging6")== 0?0:rs.getInt("flag_open_paging6"));	    			
	    			h.put("flag_open_paging7", rs.getInt("flag_open_paging7")== 0?0:rs.getInt("flag_open_paging7"));	    			
	    			h.put("flag_open_paging8", rs.getInt("flag_open_paging8")== 0?0:rs.getInt("flag_open_paging8"));	    			
	    			h.put("flag_open_paging9", rs.getInt("flag_open_paging9")== 0?0:rs.getInt("flag_open_paging9"));	    			
	    			h.put("flag_open_paging10", rs.getInt("flag_open_paging10")== 0?0:rs.getInt("flag_open_paging10"));
	    			h.put("flag_open_paging11", rs.getInt("flag_open_paging11")== 0?0:rs.getInt("flag_open_paging11"));
	    			h.put("flag_open_paging12", rs.getInt("flag_open_paging12")== 0?0:rs.getInt("flag_open_paging12"));
	    			h.put("flag_open_paging13", rs.getInt("flag_open_paging13")== 0?0:rs.getInt("flag_open_paging13"));
	    			h.put("flag_open_paging14", rs.getInt("flag_open_paging14")== 0?0:rs.getInt("flag_open_paging14"));	    			
	    			h.put("flag_open_paging15", rs.getInt("flag_open_paging15")== 0?0:rs.getInt("flag_open_paging15"));	    			
	    			h.put("flag_open_paging16", rs.getInt("flag_open_paging16")== 0?0:rs.getInt("flag_open_paging16"));
	    			h.put("flag_open_paging17", rs.getInt("flag_open_paging17")== 0?0:rs.getInt("flag_open_paging17"));
	    			h.put("flag_open_paging18", rs.getInt("flag_open_paging18")== 0?0:rs.getInt("flag_open_paging18"));
	    			h.put("flag_open_paging19", rs.getInt("flag_open_paging19")== 0?0:rs.getInt("flag_open_paging19"));
	    			h.put("flag_open_paging20", rs.getInt("flag_open_paging20")== 0?0:rs.getInt("flag_open_paging20"));
	    			h.put("flag_open_paging21", rs.getInt("flag_open_paging21")== 0?0:rs.getInt("flag_open_paging21"));
	    			h.put("flag_open_paging22", rs.getInt("flag_open_paging22")== 0?0:rs.getInt("flag_open_paging22"));
	    			h.put("flag_open_paging23", rs.getInt("flag_open_paging23")== 0?0:rs.getInt("flag_open_paging23"));	    			
	    			
	    			//add 08022012
	    			h.put("flag_semakan_online", rs.getString("flag_semakan_online")== null?"":rs.getString("flag_semakan_online"));
	    			
	    			h.put("negeriKem", rs.getString("negeriKem")== null?"":rs.getString("negeriKem"));
	    			h.put("agensiKem", rs.getString("agensiKem")== null?"":rs.getString("agensiKem"));
	    			h.put("id_kementerian", rs.getString("id_kementerian")== null?"":rs.getString("id_kementerian"));
	    			h.put("tujuanInit", rs.getString("tujuanInit")== null?"":rs.getString("tujuanInit"));
	    			
	    			//berkait dengan online
	    			h.put("flag_status_online", rs.getString("flag_status_online")== null?"":rs.getString("flag_status_online"));
	    			h.put("catatan_status_online", rs.getString("catatan_status_online")== null?"":rs.getString("catatan_status_online"));
	    			
	    			//untuk email
	    			h.put("id_kemaskini", rs.getString("id_kemaskini")== null?"":rs.getString("id_kemaskini"));
	    			
	    			//total hakmilik w/out borang k
	    			h.put("totalHakmilik", rs.getInt("totalHakmilik")== 0?0:rs.getInt("totalHakmilik"));
	    			h.put("totalHakmilikBorangK", rs.getInt("totalHakmilikBorangK")== 0?0:rs.getInt("totalHakmilikBorangK"));
	    			
	    			//flag status
	    			h.put("flag_status_borang_k", rs.getInt("flag_status_borang_k")== 0?0:rs.getInt("flag_status_borang_k"));
	    			h.put("flag_status_permintaanukur", rs.getInt("flag_status_permintaanukur")== 0?0:rs.getInt("flag_status_permintaanukur"));
	    			h.put("flag_status_pampasan", rs.getInt("flag_status_pampasan")== 0?0:rs.getInt("flag_status_pampasan"));
	    			
	    			h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
	    			h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));
	    			h.put("negeri_kementerian", rs.getString("negeri_kementerian")== null?"":rs.getString("negeri_kementerian"));
	    			h.put("poskod", rs.getString("poskod")== null?"":rs.getString("poskod"));
	    			h.put("alamat1", rs.getString("alamat1")== null?"":rs.getString("alamat1"));
	    			h.put("alamat2", rs.getString("alamat2")== null?"":rs.getString("alamat2"));
	    			h.put("alamat3", rs.getString("alamat3")== null?"":rs.getString("alamat3"));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("catatan_endosan_ptg", rs.getString("catatan_endosan_ptg")== null?"":rs.getString("catatan_endosan_ptg"));
	    			h.put("catatan_endosan_ptd", rs.getString("catatan_endosan_ptd")== null?"":rs.getString("catatan_endosan_ptd"));
	    			h.put("catatan_borangk_ptg", rs.getString("catatan_borangk_ptg")== null?"":rs.getString("catatan_borangk_ptg"));
	    			h.put("catatan_borangk_ptd", rs.getString("catatan_borangk_ptd")== null?"":rs.getString("catatan_borangk_ptd"));
	    			h.put("flag_subjaket", rs.getString("flag_subjaket")== null?"":rs.getString("flag_subjaket"));
	    			h.put("tarikh_borangb", rs.getDate("tarikh_borangb")==null?"":Format.format(rs.getDate("tarikh_borangb")));
	    			h.put("tarikh_borangf", rs.getDate("tarikh_borangf")==null?"":Format.format(rs.getDate("tarikh_borangf")));
	    			h.put("no_fail", rs.getString("no_fail")== null?"":rs.getString("no_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    			h.put("status", rs.getString("status")== null?"":rs.getString("status"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("nama_agensi", rs.getString("nama_agensi")== null?"":rs.getString("nama_agensi"));
	    			h.put("projek_negeri", rs.getString("projek_negeri")== null?"":rs.getString("projek_negeri"));
	    			h.put("projek_daerah", rs.getString("projek_daerah")== null?"":rs.getString("projek_daerah"));
	    			h.put("tujuan", rs.getString("tujuan")== null?"":rs.getString("tujuan"));
	    			h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":Format.format(rs.getDate("tarikh_kehendaki")));
	    			h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")== null?"":rs.getString("no_rujukan_surat"));
	    			h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			h.put("id_projekNegeri", rs.getString("id_projeknegeri")== null?"":rs.getString("id_projeknegeri"));
	    			h.put("id_projekDaerah", rs.getString("id_daerah")== null?"":rs.getString("id_daerah"));
	    			
	    			h.put("id_endosan_borangk_ptg", rs.getString("id_endosan_borangk_ptg")== null?"":rs.getString("id_endosan_borangk_ptg"));
	    			h.put("nama_endosan_borangk_ptg", rs.getString("nama_endosan_borangk_ptg")== null?"":rs.getString("nama_endosan_borangk_ptg"));
	    			h.put("id_endosan_borangk_ptd", rs.getString("id_endosan_borangk_ptd")== null?"":rs.getString("id_endosan_borangk_ptd"));
	    			h.put("nama_endosan_borangk_ptd", rs.getString("nama_endosan_borangk_ptd")== null?"":rs.getString("nama_endosan_borangk_ptd"));

	    			h.put("id_pejabat_ptg", rs.getString("id_pejabat_ptg")== null?"":rs.getString("id_pejabat_ptg"));
	    			h.put("nama_pejabat_ptg", rs.getString("nama_pejabat_ptg")== null?"":rs.getString("nama_pejabat_ptg"));
	    			h.put("id_pejabat_ptd", rs.getString("id_pejabat_ptd")== null?"":rs.getString("id_pejabat_ptd"));
	    			h.put("nama_pejabat_ptd", rs.getString("nama_pejabat_ptd")== null?"":rs.getString("nama_pejabat_ptd"));
	    			h.put("flag_segera", rs.getString("flag_segera")== null?"":rs.getString("flag_segera"));
	    			
	    			if(rs.getString("flag_peruntukan")!=null){
	    				if(rs.getString("flag_peruntukan").equals("1")){
	    					h.put("peruntukan","ADA");
	    				}else if(rs.getString("flag_peruntukan").equals("2")){
	    					h.put("peruntukan","TIADA");
	    				}else{
	    					h.put("peruntukan","");
	    				}
	    			}else{
	    				h.put("peruntukan","");	
	    			}
	    				
	    			
	    			if(rs.getString("flag_segera")!=null){
	    				
	    				if(rs.getString("flag_segera").equals("1")){
	    					h.put("segera","YA");
	    				}else if(rs.getString("flag_segera").equals("2")){
	    					h.put("segera","TIDAK");
	    				}else if(rs.getString("flag_segera").equals("3")){
	    					h.put("segera","SEBAHAGIAN");
	    				}else{
	    					h.put("segera","");
	    				}
	    			
	    			}else{
	    				h.put("segera","");	
	    			}
	    			
	    			dataHeader.addElement(h);
	    	}			    
	     
	    }catch (SQLException se) { 
	    	se.printStackTrace();
	    	throw new Exception("Ralat Jana Fail:"+se.getMessage());
	    
	    }finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataHeader
	
	public static void setDataHeaderLama(String id_permohonan)throws Exception {	    
		dataHeader = new Vector();		
		Db db = null;
	    dataHeader.clear();
	    String sql = "";
	    String flagLot = "";
	    Hashtable getFlagLot = null;
	   	    
	    try{
	    	db = new Db();
	    		
	    	Statement stmt = db.getStatement();
	    	if(!id_permohonan.equals("")){	    
	    	    myLogger.info("id_permohonan :::::::::"+id_permohonan);
	    	    getFlagLot = getFlagLot(id_permohonan,db);		
	    	    flagLot = getFlagLot.get("FLAG_REMOVE_LOG").toString();
	    	    myLogger.info("getFlagLot ::::::::: "+getFlagLot.get("FLAG_REMOVE_LOG").toString());
	    	}

	    	if(!id_permohonan.equals("")){	    
	    	    myLogger.info("id_permohonan :::::::::"+id_permohonan);
	    	    getFlagLot = getFlagLot(id_permohonan,db);		
	    	    flagLot = getFlagLot.get("FLAG_REMOVE_LOG").toString();
	    	    myLogger.info("getFlagLot ::::::::: "+getFlagLot.get("FLAG_REMOVE_LOG").toString());
	    		
	    	}
	    		
	    	if(flagLot.equals("") && !id_permohonan.equals("")){
	    		updateLOT(id_permohonan,db);
	    		
	    	}
	    		
	    	sql = "SELECT DISTINCT (select count(a.id_borangk) from tblpptborangk a where a.id_permohonan = p.id_permohonan)as flag_status_borang_k, ";
	    	sql += " (select count(b.id_permintaanukur) from tblpptpermintaanukur b, tblppthakmilik c where b.id_hakmilik(+) = c.id_hakmilik and c.id_permohonan(+) = p.id_permohonan)as flag_status_permintaanukur, ";
	    	
	    	sql += " (select count(*) from Tblppthakmilik a ";
	    	sql += " where nvl(a.flag_pembatalan_keseluruhan,0) <> 'Y' ";
	    	sql += " and nvl(a.flag_penarikan_keseluruhan,0) <> 'Y' ";
	    	sql += " and a.id_permohonan = p.id_permohonan)as totalHakmilik, ";
	    		
	    	sql += " (select count(*) from Tblppthakmilikborangk a, Tblppthakmilik b ";
	   		sql += " where a.id_hakmilik = b.id_hakmilik ";
	    	sql += " and nvl(b.flag_pembatalan_keseluruhan,0) <> 'Y' ";
	    	sql += " and nvl(b.flag_penarikan_keseluruhan,0) <> 'Y' ";
	    	sql += " and b.id_permohonan = p.id_permohonan ";
	    	sql += " and b.flag_endosan_borangk is not null )as totalHakmilikBorangK, ";

	    	sql += " (select count(c.id_award) "; 
	    	sql += " from tblpptaward c, tblpptsiasatan d, tblppthakmilik e ";
	    	sql += " where c.id_siasatan(+) = d.id_siasatan ";
	    	sql += " and d.id_hakmilik(+) = e.id_hakmilik ";
	    	sql += " and e.id_permohonan = p.id_permohonan ";
	    	sql += " AND d.id_siasatan = (SELECT MAX(d1.id_siasatan) FROM TBLPPTSIASATAN d1 WHERE d1.ID_HAKMILIK = e.ID_HAKMILIK))as flag_status_pampasan, ";
	    		
	    		/**QUERY UNTUK PAGING*/
	    		/**OPEN PAGING 2*/
	    		sql += " (select count(*) from Tblpptpermohonan px, Tblpfdfail fx ";
	    		sql += " where fx.id_fail = px.id_fail "; 
	    		sql += " and px.id_permohonan = p.id_permohonan ";
	    		sql += " AND (PX.FLAG_SEMAK = '2' ";
	    		sql += " OR FX.NO_FAIL IS NOT NULL)  ";
	    		sql += " and fx.no_fail is not null)as flag_open_paging2, ";
	    		
	    		/**OPEN PAGING 3*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='16' "; 
	    		sql += " OR px.id_status='148' OR px.id_status='1612198' "; 
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_pegawai is not null) ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
	    			   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
	    		sql += " OR px.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
	    		sql += " where tx.id_permohonan = p.id_permohonan)))as flag_open_paging3, ";
	    		
	    		/**OPEN PAGING 4*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='148' "; 
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_pegawai is not null) ";      
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
 			   		   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
	    		sql += " OR px.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
	    		sql += " where tx.id_permohonan = p.id_permohonan)))as flag_open_paging4, ";
	    				  
	    		/**OPEN PAGING 5,6,7,8,9*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
	    		sql += " where tx.id_permohonan = p.id_permohonan)))as flag_open_paging56789, ";                        
	    		
	    		/**OPEN PAGING 10*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='26' ";    
	    		sql += " OR px.id_permohonan in (select distinct wx.id_permohonan from Tblpptwarta wx ";
	    		sql += " where wx.id_permohonan = p.id_permohonan)))as flag_open_paging10, ";

	    		/**OPEN PAGING 11*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='31' ";   
	    		sql += " OR px.id_status='35' "; 
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_endosan in ('1','2')) ";
	    		sql += " OR px.id_permohonan in (select distinct wx.id_permohonan from Tblpptwarta wx ";
	    		sql += " where wx.id_permohonan = p.id_permohonan)))as flag_open_paging11, ";

	    		/**OPEN PAGING 12*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='35' ";   
	    		sql += " OR px.id_status='54' "; 
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_endosan in ('1','2')) ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptborange bx, Tblpptborangehakmilik beh "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = beh.id_hakmilik and beh.id_borange = bx.id_borange)))as flag_open_paging12, ";

	    		/**OPEN PAGING 13*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='54' ";   
	    		sql += " OR px.id_status='52' "; 
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptnotisawam nx, Tblpptnotisawamhakmilik nah "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = nah.id_hakmilik and nah.id_notisawam = nx.id_notisawam) ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptborange bx, Tblpptborangehakmilik beh "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = beh.id_hakmilik and beh.id_borange = bx.id_borange )))as flag_open_paging13, ";
	    		
	    		/**OPEN PAGING 14*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='52' ";   
	    		sql += " OR px.id_status='58' "; 
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptnotisawam nx, Tblpptnotisawamhakmilik nah "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = nah.id_hakmilik and nah.id_notisawam = nx.id_notisawam) ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
	    		sql += " and flag_jenis_bukti = '1')))as flag_open_paging14, ";
	    				         
	    		/**OPEN PAGING 15*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='38' ";   
	    		sql += " OR px.id_permohonan in (select distinct ptx.id_permohonan from Tblppttandakawasan ptx "; 
	    		sql += " where ptx.id_permohonan = p.id_permohonan) "; 
	    		sql += " OR px.flag_segera = '1' AND (px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan)) ";
	    		sql += " OR px.flag_segera = '2' AND (px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
	    		sql += " and flag_jenis_bukti = '1') OR px.id_status='58') ";
	    		sql += " OR px.flag_segera = '3' AND px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_segera_sebahagian = 'N' ";
	    		sql += " and hx.id_hakmilik = bpx.id_hakmilik) ";                          
	    		sql += " OR px.flag_segera = '3' AND px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx ";
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_segera_sebahagian = 'Y') ";  
	    		sql += " AND px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan)))as flag_open_paging15, ";
	    				      
	    		/**OPEN PAGING 16*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='38' ";   
	    		sql += " OR px.id_status='62' ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptsiasatan sx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = sx.id_hakmilik) ";
	    		sql += " OR px.id_permohonan in (select distinct ptx.id_permohonan from Tblppttandakawasan ptx "; 
	    		sql += " where ptx.id_permohonan = p.id_permohonan)))as flag_open_paging16, ";    

	    		/**OPEN PAGING 17*/                    
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='68' ";   
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptsiasatan sx, Tblpptborangg bgx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = sx.id_hakmilik and bgx.id_siasatan = sx.id_siasatan ";
	    		sql += " and sx.id_siasatan = (select max(sx1.id_siasatan) from Tblpptsiasatan sx1 where sx1.id_hakmilik = hx.id_hakmilik) ) ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptsiasatan sx, Tblpptaward ax "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = sx.id_hakmilik and ax.id_siasatan = sx.id_siasatan ";
	    		sql += " and sx.id_siasatan = (select max(sx1.id_siasatan) from Tblpptsiasatan sx1 where sx1.id_hakmilik = hx.id_hakmilik))))as flag_open_paging17, ";                                                                              

	    		/**OPEN PAGING 18*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='72' ";   
	    		sql += " OR px.id_status='76' "; 
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblppthakmilikpb hpbx, Tblpptbayaran bx ";
	    		sql += " where hx.id_permohonan = p.id_permohonan and hpbx.id_hakmilik = hx.id_hakmilik and bx.id_hakmilikpb = hpbx.id_hakmilikpb ";
	    		sql += " and bx.cara_bayar in ('1','2')) ";
	    		sql += " OR px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.flag_segera = '1' AND px.id_permohonan in (select distinct bix.id_permohonan from Tblpptborangi bix "; 
	    		sql += " where bix.id_permohonan = p.id_permohonan) ";                        
	    		sql += " OR px.flag_segera = '3' AND px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_segera_sebahagian = 'Y') ";
	    		sql += " AND px.id_permohonan in (select distinct bix.id_permohonan from Tblpptborangi bix "; 
	    		sql += " where bix.id_permohonan = p.id_permohonan)))as flag_open_paging18, ";
	    		
	    		/**OPEN PAGING 19,20,21*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='76' ";   
	    		sql += " OR px.id_permohonan in (select distinct blx.id_permohonan from Tblpptborangl blx "; 
	    		sql += " where blx.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan)))as flag_open_paging192021, ";
	    				                            
	    		/**OPEN PAGING 22*/                    
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='58' AND px.flag_segera = '1' ";
	    		sql += " OR px.id_status='59' ";
	    		sql += " OR px.flag_segera = '1' AND px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
	    		sql += " and flag_jenis_bukti = '1') "; 
	    		sql += " OR px.id_permohonan in (select distinct bix.id_permohonan from Tblpptborangi bix "; 
	    		sql += " where bix.id_permohonan = p.id_permohonan) ";
	    		sql += " OR px.flag_segera = '3' AND px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
	    		sql += " and flag_jenis_bukti = '1' and hx.flag_segera_sebahagian = 'Y')  ))as flag_open_paging22, ";
	    				                            
	    		/**OPEN PAGING 23*/
	    		sql += " (select count(*) from Tblpptpermohonan px ";
	    		sql += " where px.id_permohonan = p.id_permohonan ";
	    		sql += " and (px.id_status='82' ";
	    		sql += " OR px.id_status='72' AND px.flag_segera = '1' ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblppthakmilikpb hpbx, Tblpptbayaran bx ";
	    		sql += " where hx.id_permohonan = p.id_permohonan and hpbx.id_hakmilik = hx.id_hakmilik and bx.id_hakmilikpb = hpbx.id_hakmilikpb ";
	    		sql += " and bx.cara_bayar in ('1','2')) AND px.flag_segera = '1' ";
	    		sql += " OR px.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptpermintaanukur pux "; 
	    		sql += " where hx.id_permohonan = p.id_permohonan and pux.id_hakmilik = hx.id_hakmilik) ";                        
	    		sql += " OR px.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
	    		sql += " where bkx.id_permohonan = p.id_permohonan)))as flag_open_paging23, ";  
	    				        		            
	    		/**END PAGING*/
	    		
	    		sql += " p.id_kemaskini, p.id_status,ag.nama_agensi,p.id_permohonan, p.tujuan, initcap(p.tujuan) as tujuanInit, p.tarikh_borangf, f.id_negeri as id_projeknegeri, p.id_negeri, initcap(pn.nama_negeri) as projek_negeri,p.no_permohonan, ";  
	    		sql += " p.id_fail, f.no_fail, f.id_suburusan,  p.tarikh_permohonan, p.id_status, f.id_kementerian, p.id_agensi, p.flag_peruntukan, p.flag_segera, "; 
	    		sql += " p.id_daerah, p.no_rujukan_surat, p.tarikh_kehendaki, k.alamat1, k.alamat2, k.alamat3,  k.poskod, p.id_mukim, ";
	    		sql += " initcap(k.nama_kementerian) as nama_kementerian, initcap(b.nama_daerah) as projek_daerah, p.no_rujukan_ptd, p.no_rujukan_ptg,  p.no_rujukan_upt, su.nama_suburusan, s.keterangan as status, "; 
	    		sql += " p.tarikh_terima, pnk.nama_negeri as negeri_kementerian, ";
				sql += " rp.id_pejabat as id_pejabat_ptg,rp.nama_pejabat as nama_pejabat_ptg, ";
				sql += " rp2.id_pejabat as id_pejabat_ptd,rp2.nama_pejabat as nama_pejabat_ptd, ";
				sql += " rk1.id_pejabat as id_endosan_borangk_ptg,rk1.nama_pejabat as nama_endosan_borangk_ptg, ";
				sql += " rk2.id_pejabat as id_endosan_borangk_ptd,rk2.nama_pejabat as nama_endosan_borangk_ptd, ";		
				sql += " p.tarikh_borangb, p.flag_subjaket, p.catatan_borangk_ptg, p.catatan_borangk_ptd, ";
				sql += " p.catatan_endosan_ptg, p.catatan_endosan_ptd, p.flag_status_online, p.catatan_status_online, p.tarikh_surat, initcap(ag.nama_agensi)as agensiKem, upper(pnk.nama_negeri) as negeriKem ";
				sql += " , p.flag_semakan_online ";
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujkementerian k, Tblrujdaerah b, Tblrujsuburusan su, "; 
				sql += " Tblrujstatus s,  Tblrujnegeri pn, Tblrujagensi ag, Tblrujpejabat rp, Tblrujpejabat rp2, ";
				sql += " Tblrujpejabat rk1, Tblrujpejabat rk2, Tblrujnegeri pnk ";
				sql += " WHERE f.id_kementerian = k.id_kementerian(+) ";
				sql += " AND k.id_negeri = pnk.id_negeri(+)";
				sql += " AND f.id_negeri = pn.id_negeri "; 
				sql += " AND f.id_fail = p.id_fail "; 
				sql += " AND b.id_daerah(+) = p.id_daerah ";
				sql += " AND f.id_suburusan = su.id_suburusan "; 
				sql += " AND p.id_status = s.id_status "; 
				sql += " AND p.id_agensi = ag.id_agensi(+) ";
				sql += " AND p.id_pejabat_ptg = rp.id_pejabat(+) ";
				sql += " AND p.id_pejabat_ptd = rp2.id_pejabat(+) ";
				sql += " AND p.id_endosan_borangk_ptg = rk1.id_pejabat(+) ";
				sql += " AND p.id_endosan_borangk_ptd = rk2.id_pejabat(+) ";
				sql += " AND p.id_permohonan = '"+id_permohonan+"'";
	    	
			myLogger.info("setDataHeaderLama:sql="+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//Hashtable<String,String> h;	
			Hashtable h;	
			while (rs.next()) {
//				h = new Hashtable<String,String>();
				h = new Hashtable();
   			
	    			//paging 2 - 13
	    			h.put("flag_open_paging2", rs.getInt("flag_open_paging2")== 0?0:rs.getInt("flag_open_paging2"));
	    			h.put("flag_open_paging3", rs.getInt("flag_open_paging3")== 0?0:rs.getInt("flag_open_paging3"));
	    			h.put("flag_open_paging4", rs.getInt("flag_open_paging4")== 0?0:rs.getInt("flag_open_paging4"));
	    			h.put("flag_open_paging56789", rs.getInt("flag_open_paging56789")== 0?0:rs.getInt("flag_open_paging56789"));
	    			h.put("flag_open_paging10", rs.getInt("flag_open_paging10")== 0?0:rs.getInt("flag_open_paging10"));
	    			h.put("flag_open_paging11", rs.getInt("flag_open_paging11")== 0?0:rs.getInt("flag_open_paging11"));
	    			h.put("flag_open_paging12", rs.getInt("flag_open_paging12")== 0?0:rs.getInt("flag_open_paging12"));
	    			h.put("flag_open_paging13", rs.getInt("flag_open_paging13")== 0?0:rs.getInt("flag_open_paging13"));
	    			h.put("flag_open_paging14", rs.getInt("flag_open_paging14")== 0?0:rs.getInt("flag_open_paging14"));
	    			h.put("flag_open_paging15", rs.getInt("flag_open_paging15")== 0?0:rs.getInt("flag_open_paging15"));
	    			h.put("flag_open_paging16", rs.getInt("flag_open_paging16")== 0?0:rs.getInt("flag_open_paging16"));
	    			h.put("flag_open_paging17", rs.getInt("flag_open_paging17")== 0?0:rs.getInt("flag_open_paging17"));
	    			h.put("flag_open_paging18", rs.getInt("flag_open_paging18")== 0?0:rs.getInt("flag_open_paging18"));
	    			h.put("flag_open_paging192021", rs.getInt("flag_open_paging192021")== 0?0:rs.getInt("flag_open_paging192021"));
	    			h.put("flag_open_paging22", rs.getInt("flag_open_paging22")== 0?0:rs.getInt("flag_open_paging22"));
	    			h.put("flag_open_paging23", rs.getInt("flag_open_paging23")== 0?0:rs.getInt("flag_open_paging23"));
	    			
	    			//add 08022012
	    			h.put("flag_semakan_online", rs.getString("flag_semakan_online")== null?"":rs.getString("flag_semakan_online"));
	    			
	    			h.put("negeriKem", rs.getString("negeriKem")== null?"":rs.getString("negeriKem"));
	    			h.put("agensiKem", rs.getString("agensiKem")== null?"":rs.getString("agensiKem"));
	    			h.put("id_kementerian", rs.getString("id_kementerian")== null?"":rs.getString("id_kementerian"));
	    			h.put("tujuanInit", rs.getString("tujuanInit")== null?"":rs.getString("tujuanInit"));
	    			
	    			//berkait dengan online
	    			h.put("flag_status_online", rs.getString("flag_status_online")== null?"":rs.getString("flag_status_online"));
	    			h.put("catatan_status_online", rs.getString("catatan_status_online")== null?"":rs.getString("catatan_status_online"));
	    			
	    			//untuk email
	    			h.put("id_kemaskini", rs.getString("id_kemaskini")== null?"":rs.getString("id_kemaskini"));
	    			
	    			//total hakmilik w/out borang k
	    			h.put("totalHakmilik", rs.getInt("totalHakmilik")== 0?0:rs.getInt("totalHakmilik"));
	    			h.put("totalHakmilikBorangK", rs.getInt("totalHakmilikBorangK")== 0?0:rs.getInt("totalHakmilikBorangK"));
	    			
	    			//flag status
	    			h.put("flag_status_borang_k", rs.getInt("flag_status_borang_k")== 0?0:rs.getInt("flag_status_borang_k"));
	    			h.put("flag_status_permintaanukur", rs.getInt("flag_status_permintaanukur")== 0?0:rs.getInt("flag_status_permintaanukur"));
	    			h.put("flag_status_pampasan", rs.getInt("flag_status_pampasan")== 0?0:rs.getInt("flag_status_pampasan"));
	    			
	    			h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
	    			h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));
	    			h.put("negeri_kementerian", rs.getString("negeri_kementerian")== null?"":rs.getString("negeri_kementerian"));
	    			h.put("poskod", rs.getString("poskod")== null?"":rs.getString("poskod"));
	    			h.put("alamat1", rs.getString("alamat1")== null?"":rs.getString("alamat1"));
	    			h.put("alamat2", rs.getString("alamat2")== null?"":rs.getString("alamat2"));
	    			h.put("alamat3", rs.getString("alamat3")== null?"":rs.getString("alamat3"));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("catatan_endosan_ptg", rs.getString("catatan_endosan_ptg")== null?"":rs.getString("catatan_endosan_ptg"));
	    			h.put("catatan_endosan_ptd", rs.getString("catatan_endosan_ptd")== null?"":rs.getString("catatan_endosan_ptd"));
	    			h.put("catatan_borangk_ptg", rs.getString("catatan_borangk_ptg")== null?"":rs.getString("catatan_borangk_ptg"));
	    			h.put("catatan_borangk_ptd", rs.getString("catatan_borangk_ptd")== null?"":rs.getString("catatan_borangk_ptd"));
	    			h.put("flag_subjaket", rs.getString("flag_subjaket")== null?"":rs.getString("flag_subjaket"));
	    			h.put("tarikh_borangb", rs.getDate("tarikh_borangb")==null?"":Format.format(rs.getDate("tarikh_borangb")));
	    			h.put("tarikh_borangf", rs.getDate("tarikh_borangf")==null?"":Format.format(rs.getDate("tarikh_borangf")));
	    			h.put("no_fail", rs.getString("no_fail")== null?"":rs.getString("no_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    			h.put("status", rs.getString("status")== null?"":rs.getString("status"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("nama_agensi", rs.getString("nama_agensi")== null?"":rs.getString("nama_agensi"));
	    			h.put("projek_negeri", rs.getString("projek_negeri")== null?"":rs.getString("projek_negeri"));
	    			h.put("projek_daerah", rs.getString("projek_daerah")== null?"":rs.getString("projek_daerah"));
	    			h.put("tujuan", rs.getString("tujuan")== null?"":rs.getString("tujuan"));
	    			h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":Format.format(rs.getDate("tarikh_kehendaki")));
	    			h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")== null?"":rs.getString("no_rujukan_surat"));
	    			h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			h.put("id_projekNegeri", rs.getString("id_projeknegeri")== null?"":rs.getString("id_projeknegeri"));
	    			h.put("id_projekDaerah", rs.getString("id_daerah")== null?"":rs.getString("id_daerah"));
	    			
	    			h.put("id_endosan_borangk_ptg", rs.getString("id_endosan_borangk_ptg")== null?"":rs.getString("id_endosan_borangk_ptg"));
	    			h.put("nama_endosan_borangk_ptg", rs.getString("nama_endosan_borangk_ptg")== null?"":rs.getString("nama_endosan_borangk_ptg"));
	    			h.put("id_endosan_borangk_ptd", rs.getString("id_endosan_borangk_ptd")== null?"":rs.getString("id_endosan_borangk_ptd"));
	    			h.put("nama_endosan_borangk_ptd", rs.getString("nama_endosan_borangk_ptd")== null?"":rs.getString("nama_endosan_borangk_ptd"));

	    			h.put("id_pejabat_ptg", rs.getString("id_pejabat_ptg")== null?"":rs.getString("id_pejabat_ptg"));
	    			h.put("nama_pejabat_ptg", rs.getString("nama_pejabat_ptg")== null?"":rs.getString("nama_pejabat_ptg"));
	    			h.put("id_pejabat_ptd", rs.getString("id_pejabat_ptd")== null?"":rs.getString("id_pejabat_ptd"));
	    			h.put("nama_pejabat_ptd", rs.getString("nama_pejabat_ptd")== null?"":rs.getString("nama_pejabat_ptd"));
	    			h.put("flag_segera", rs.getString("flag_segera")== null?"":rs.getString("flag_segera"));
	    			
	    			if(rs.getString("flag_peruntukan")!=null){
	    				if(rs.getString("flag_peruntukan").equals("1")){
	    					h.put("peruntukan","ADA");
	    				}else if(rs.getString("flag_peruntukan").equals("2")){
	    					h.put("peruntukan","TIADA");
	    				}else{
	    					h.put("peruntukan","");
	    				}
	    			}else{
	    				h.put("peruntukan","");	
	    			}
	    				
	    			
	    			if(rs.getString("flag_segera")!=null){
	    				
	    				if(rs.getString("flag_segera").equals("1")){
	    					h.put("segera","YA");
	    				}else if(rs.getString("flag_segera").equals("2")){
	    					h.put("segera","TIDAK");
	    				}else if(rs.getString("flag_segera").equals("3")){
	    					h.put("segera","SEBAHAGIAN");
	    				}else{
	    					h.put("segera","");
	    				}
	    			
	    			}else{
	    				h.put("segera","");	
	    			}
	    			
	    			dataHeader.addElement(h);
	    	}			    
	     
	    } finally {
	     // if (db != null) db.close();
	    }
	    
	}//close setDataHeader
	
	
	

	public static Hashtable getFlagLot(String id_permohonan,Db db)throws Exception {
		//getFlagLot = new Hashtable();
		//getFlagLot.clear();
		//Db db = null;
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT FLAG_REMOVE_LOG FROM TBLPPTPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
			myLogger.info(" getFlagLot(String id_permohonan)  :"+sql);
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("FLAG_REMOVE_LOG") == null) {
					h.put("FLAG_REMOVE_LOG", "");
				} else {
					h.put("FLAG_REMOVE_LOG", rs.getString("FLAG_REMOVE_LOG"));
				}				
			}
			return h;
		} finally {
			//if (db != null) db.close();
		}
	}
	
	public static void updateFlagLot(String id_permohonan,Db db)throws Exception
	  {
	   //Db db = null;
	    String sql = "";
	    try
	    {
	    	//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_permohonan", id_permohonan);
			r.add("FLAG_REMOVE_LOG", "Y");			
			sql = r.getSQLUpdate("tblpptpermohonan");
			stmt.executeUpdate(sql);
	    	} finally {
	    		//if (db != null) db.close();
	    	}
	  	}
	
	

	public static void updateLOT(String id_permohonan,Db db)throws Exception
	  { 
		 Connection conn = null;
		   //Db db = null;
	    String sql = "";
	    String sql1 = "";
	    try
	    {	
	    	
	      updateFlagLot(id_permohonan,db);
	      
	     // db = new Db();
	      conn = db.getConnection();
	      conn.setAutoCommit(false);
	      Statement stmt = db.getStatement();
	      Statement stmt1 = db.getStatement();
	      
	      sql = " UPDATE TBLPPTHAKMILIK "+ 
	      " SET NO_LOT = REPLACE(UPPER(NO_LOT), 'LOT', '') "+
	      " WHERE UPPER(NO_LOT) LIKE '%LOT%' AND ID_PERMOHONAN = '"+id_permohonan+"' ";
	      myLogger.info("----BUANG LOT :"+sql);
	      stmt.executeUpdate(sql);

	      sql1 = " UPDATE TBLPPTHAKMILIK "+
	      " SET NO_LOT = REPLACE(UPPER(NO_LOT), UPPER(NO_LOT), UPPER(TRIM(NO_LOT))) "+
	      " WHERE UPPER(NO_LOT) LIKE '%LOT%' AND ID_PERMOHONAN = '"+id_permohonan+"' ";
	      myLogger.info("----BUANG SPACE :"+sql1);
	      stmt1.executeUpdate(sql1);	     
	      
	    //  conn.commit();
	    }/*
	    catch (SQLException se) { 
	    	se.printStackTrace();
	    	throw new Exception("Ralat Jana Fail:"+se.getMessage());
	    }
	    */finally {
	    	//if (conn != null) conn.close(); 
	    	//if (db != null)	db.close();
	    }
	    	
	  }
	
	public static String getIdSubUrusan(String id_permohonan,Db dbasal) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			if(dbasal==null)
			{
				db = new Db();
			}
			else
			{
				db = dbasal;
			}
			stmt = db.getStatement();
			String ID_SUBURUSAN="";
			sql = " SELECT F.ID_SUBURUSAN FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = '"+id_permohonan+"' ";	
//			myLogger.info(" getIdSubUrusan :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					ID_SUBURUSAN = (rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
				}			
			return ID_SUBURUSAN;
		} finally {
			/*if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();*/
			if(dbasal==null)
			{
				if (db != null)
					db.close();
			}
		}
	}
	
	
}//close class
