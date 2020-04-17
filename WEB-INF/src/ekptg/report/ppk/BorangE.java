package ekptg.report.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;
import ekptg.report.ppk.BorangF;
  
public class BorangE extends EkptgReportServlet{
	static Logger myLogger = Logger.getLogger(BorangE.class);
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public BorangE (){
		super.setReportName("BorangE2") ;
		super.setFolderName("ppk");
	}
	 
	public double nilaiHTA(String id_fail, Db db) throws Exception {
		//System.out.println("nilaiHTA--->");
		//Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//db = new Db();
			stmt = db.getStatement();
			//System.out.println("x--->");
			sql = " " +
					//" SELECT SUM(NVL(" +
					//"NILAI_HTA_TARIKHMOHON" +
					//" REGEXP_REPLACE(NILAI_HTA_TARIKHMOHON,'[^[:alnum:]'' '']', NULL) "+
					//",0)) AS NILAI_HTA FROM ( "+
					" SELECT " +
					" L.NILAI_HTA_TARIKHMOHON " +
					//" SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON "+
					" FROM TBLPPKPERINTAHHTAOBMST A, "+
					" TBLPPKPERINTAH B, TBLPPKPERINTAHHTAOBDTL C,  "+
					" TBLPPKOB D,TBLPFDFAIL E,TBLPPKPERMOHONAN F, "+
					" TBLPPKKEPUTUSANPERMOHONAN G,TBLPPKPERBICARAAN H, "+
					" TBLPPKPERINTAH I,TBLPPKHTA L,TBLRUJJENISHAKMILIK M, "+
					" TBLRUJMUKIM N,TBLRUJDAERAH O,TBLPPKSIMATI P,TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN AND I.ID_PERINTAH = A.ID_PERINTAH "+
					" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST AND D.ID_OB = C.ID_PA1 "+
					" AND L.ID_HTA = A.ID_HTA AND M.ID_JENISHAKMILIK = L.ID_JENISHM "+
					" AND N.ID_MUKIM = L.ID_MUKIM AND O.ID_DAERAH = L.ID_DAERAH AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN AND A.ID_JENISPERINTAH = 1 "+
					" AND C.STATUS_TADBIR = 'Y' AND E.ID_FAIL = "+id_fail+" AND I.FLAG_JENIS_KEPUTUSAN = 0  "+
					" AND A.FLAG_HARTA = 'B' AND C.ID_OB IS NULL "+
					" UNION       "+
					" SELECT " +
					" L.NILAI_HTA_TARIKHMOHON" +
					//" SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON "+
					"  FROM    "+   
					" TBLPPKPERINTAHHTAOBMST A, TBLPPKPERINTAH B,  "+
					" TBLPPKPERINTAHHTAOBDTL C, TBLPPKOB D, "+
					" TBLPFDFAIL E, TBLPPKPERMOHONAN F, TBLPPKKEPUTUSANPERMOHONAN G, "+
					" TBLPPKPERBICARAAN H, TBLPPKPERINTAH I,TBLPPKHTA L, "+
					" TBLRUJJENISHAKMILIK M,TBLRUJMUKIM N, TBLRUJDAERAH O, "+
					" TBLPPKSIMATI P, TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
					" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN AND I.ID_PERINTAH = A.ID_PERINTAH "+
					" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
					" AND D.ID_OB = C.ID_PA2 AND L.ID_HTA = A.ID_HTA "+
					" AND M.ID_JENISHAKMILIK = L.ID_JENISHM AND N.ID_MUKIM = L.ID_MUKIM "+
					" AND O.ID_DAERAH = L.ID_DAERAH AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN AND A.ID_JENISPERINTAH = 1 "+
					" AND C.STATUS_TADBIR = 'Y' AND I.FLAG_JENIS_KEPUTUSAN = 0 "+
					" AND E.ID_FAIL = "+id_fail+" AND A.FLAG_HARTA = 'B' "+
					" AND C.ID_OB IS NULL  "+
					" UNION "+
					" SELECT " +
					" L.NILAI_HTA_TARIKHMOHON "+
					//" SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON "+
					" FROM      "+
					" TBLPPKPERINTAHHTAOBMST A, TBLPPKPERINTAH B,  "+
					" TBLPPKPERINTAHHTAOBDTL C, TBLPPKOB D, "+
					" TBLPFDFAIL E, TBLPPKPERMOHONAN F, "+
					" TBLPPKKEPUTUSANPERMOHONAN G, TBLPPKPERBICARAAN H, "+
					" TBLPPKPERINTAH I, TBLPPKHTA L, "+
					" TBLRUJJENISHAKMILIK M, TBLRUJMUKIM N, "+
					" TBLRUJDAERAH O, TBLPPKSIMATI P, TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
					" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN AND I.ID_PERINTAH = A.ID_PERINTAH "+
					" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
					" AND D.ID_OB = C.ID_PA3 AND L.ID_HTA = A.ID_HTA "+
					" AND M.ID_JENISHAKMILIK = L.ID_JENISHM AND N.ID_MUKIM = L.ID_MUKIM "+
					" AND O.ID_DAERAH = L.ID_DAERAH AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN AND A.ID_JENISPERINTAH = 1 "+
					" AND C.STATUS_TADBIR = 'Y' AND E.ID_FAIL = "+id_fail+" "+
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 AND A.FLAG_HARTA = 'B' "+
					" AND C.ID_OB IS NULL       "+
					" UNION "+
					" SELECT " +
					" L.NILAI_HTA_TARIKHMOHON "+
					//" SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON "+
					" FROM       "+
					" TBLPPKPERINTAHHTAOBMST A, TBLPPKPERINTAH B,  "+
					" TBLPPKPERINTAHHTAOBDTL C, TBLPPKOB D, "+
					" TBLPFDFAIL E, TBLPPKPERMOHONAN F, "+
					" TBLPPKKEPUTUSANPERMOHONAN G, TBLPPKPERBICARAAN H, "+
					" TBLPPKPERINTAH I,TBLPPKHTA L,TBLRUJJENISHAKMILIK M, "+
					" TBLRUJMUKIM N,TBLRUJDAERAH O,TBLPPKSIMATI P, "+
					" TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
					" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN "+
					" AND I.ID_PERINTAH = A.ID_PERINTAH AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
					" AND D.ID_OB = C.ID_PA4 AND L.ID_HTA = A.ID_HTA "+
					" AND M.ID_JENISHAKMILIK = L.ID_JENISHM AND N.ID_MUKIM = L.ID_MUKIM "+
					" AND O.ID_DAERAH = L.ID_DAERAH AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN "+
					" AND A.ID_JENISPERINTAH = 1 AND C.STATUS_TADBIR = 'Y' "+
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 AND A.FLAG_HARTA = 'B' "+
					" AND E.ID_FAIL = "+id_fail+" "+
					" AND C.ID_OB IS NULL       "+
					" UNION "+
					" SELECT " +
					" L.NILAI_HTA_TARIKHMOHON "+
					//" SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON "+
					" FROM       "+
					" TBLPPKPERINTAHHTAOBMST A,TBLPPKPERINTAH B, TBLPPKPERINTAHHTAOBDTL C,  "+
					" TBLPPKOB D,TBLPFDFAIL E,TBLPPKPERMOHONAN F, "+
					" TBLPPKKEPUTUSANPERMOHONAN G,TBLPPKPERBICARAAN H, "+
					" TBLPPKPERINTAH I,TBLPPKHTA L, "+
					" TBLRUJJENISHAKMILIK M,TBLRUJMUKIM N, "+
					" TBLRUJDAERAH O,TBLPPKSIMATI P, "+
					" TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
					" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN AND I.ID_PERINTAH = A.ID_PERINTAH "+
					" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
					" AND D.ID_OB = C.ID_OB AND L.ID_HTA = A.ID_HTA "+
					" AND M.ID_JENISHAKMILIK = L.ID_JENISHM AND N.ID_MUKIM = L.ID_MUKIM "+
					" AND O.ID_DAERAH = L.ID_DAERAH AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN AND A.ID_JENISPERINTAH = 2 "+
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 AND A.FLAG_HARTA = 'B' "+
					" AND E.ID_FAIL = "+id_fail+"" +
							" ";
					//" )  ";
				//System.out.println(" ::: NILAI_HTA ::: "+sql);
				rs = stmt.executeQuery(sql);
				//System.out.println(" lepas-----------------------");
				double total = 0.0;
				while (rs.next()) {
					total += ((rs.getString("NILAI_HTA_TARIKHMOHON").equals("") || rs.getString("NILAI_HTA_TARIKHMOHON") == null) ? 0 : rs.getDouble("NILAI_HTA_TARIKHMOHON"));
				}		
				myLogger.info("nilaiHTA:total="+total);
			return total;
		} finally {
			/*
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
			*/
		}
	}
	
	
	public double nilaiHutang(String id_fail, Db db) throws Exception {
		//Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//db = new Db();
			stmt = db.getStatement();
	
	sql = " SELECT " +
			//"SUM(" +
			"NVL(A.NILAI_HUTANG,0)" +
			//")" +
			" AS NILAI_HUTANG "+
			" FROM TBLPPKOB A, "+
			" TBLPPKPERMOHONAN B, "+
			" TBLPPKPERMOHONANSIMATI C, "+
			" TBLPPKSIMATI D "+
			" WHERE D.ID_SIMATI = A.ID_SIMATI "+
			" AND D.ID_SIMATI = C.ID_SIMATI "+
			" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+
			" AND A.ID_TARAFKPTG = 2 "+
			" AND B.ID_FAIL = "+id_fail;
	
	//System.out.println(" ::: NILAI_HUTANG ::: "+sql);
	rs = stmt.executeQuery(sql);
	double total = 0.0;
	while (rs.next()) {
		total += ((rs.getString("NILAI_HUTANG").equals("") || rs.getString("NILAI_HUTANG") == null) ? 0 : rs.getDouble("NILAI_HUTANG"));
	}			
return total;
} finally {
/*
if (rs != null)
	rs.close();
if (stmt != null)
	stmt.close();
if (db != null)
	db.close();
*/
}
}
	
	
	public double nilaiHA(String id_fail, Db db) throws Exception {
		//Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//db = new Db();
			stmt = db.getStatement();
			
			sql = ""+					
					//" SELECT "+
					//" SUM(NVL( "+
					//" REGEXP_REPLACE(NILAI_HA_TARIKHMOHON,'[^[:alnum:]'' '']', NULL) "+
					//" NILAI_HA_TARIKHMOHON "+
					//" ,0)) AS NILAI_HA  "+
					//" FROM ( "+
					" SELECT  "+
					" L.NILAI_HA_TARIKHMOHON "+
					//" SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON "+
					" FROM TBLPPKPERINTAHHAOBMST A, "+
					" TBLPPKPERINTAH B, TBLPPKPERINTAHHAOBDTL C,  "+
					" TBLPPKOB D,TBLPFDFAIL E,TBLPPKPERMOHONAN F, "+
					" TBLPPKKEPUTUSANPERMOHONAN G,TBLPPKPERBICARAAN H, "+
					" TBLPPKPERINTAH I,TBLPPKHA L, "+
					" TBLPPKSIMATI P,TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN AND I.ID_PERINTAH = A.ID_PERINTAH "+
					" AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST AND D.ID_OB = C.ID_PA1 "+
					" AND L.ID_HA= A.ID_HA "+
					" AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN AND A.ID_JENISPERINTAH = 1 "+
					" AND C.STATUS_TADBIR = 'Y' AND E.ID_FAIL = "+id_fail+" AND I.FLAG_JENIS_KEPUTUSAN = 0  "+
					" AND A.FLAG_HARTA = 'B' AND C.ID_OB IS NULL "+
					" UNION       "+
					" SELECT  "+
					" L.NILAI_HA_TARIKHMOHON "+
					//" SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON "+
					" FROM       "+
					" TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAH B, "+ 
					" TBLPPKPERINTAHHAOBDTL C, TBLPPKOB D, "+
					" TBLPFDFAIL E, TBLPPKPERMOHONAN F, TBLPPKKEPUTUSANPERMOHONAN G, "+
					" TBLPPKPERBICARAAN H, TBLPPKPERINTAH I,TBLPPKHA L, "+
					" TBLPPKSIMATI P, TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
					" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN AND I.ID_PERINTAH = A.ID_PERINTAH "+
					" AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST "+
					" AND D.ID_OB = C.ID_PA2 AND L.ID_HA= A.ID_HA "+
					" AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN AND A.ID_JENISPERINTAH = 1 "+
					" AND C.STATUS_TADBIR = 'Y' AND I.FLAG_JENIS_KEPUTUSAN = 0 "+
					" AND E.ID_FAIL = "+id_fail+" AND A.FLAG_HARTA = 'B' "+
					" AND C.ID_OB IS NULL  "+
					" UNION "+
					" SELECT  "+
					" L.NILAI_HA_TARIKHMOHON "+
					//" SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON "+
					" FROM      "+
					" TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAH B,  "+
					" TBLPPKPERINTAHHAOBDTL C, TBLPPKOB D, "+
					" TBLPFDFAIL E, TBLPPKPERMOHONAN F, "+
					" TBLPPKKEPUTUSANPERMOHONAN G, TBLPPKPERBICARAAN H, "+
					" TBLPPKPERINTAH I, TBLPPKHA L,TBLPPKSIMATI P, TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
					" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN AND I.ID_PERINTAH = A.ID_PERINTAH "+
					" AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST "+
					" AND D.ID_OB = C.ID_PA3 AND L.ID_HA= A.ID_HA AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN AND A.ID_JENISPERINTAH = 1 "+
					" AND C.STATUS_TADBIR = 'Y' AND E.ID_FAIL = "+id_fail+" "+
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 AND A.FLAG_HARTA = 'B' "+
					" AND C.ID_OB IS NULL "+      
					" UNION "+
					" SELECT  "+
					" L.NILAI_HA_TARIKHMOHON "+
					//" SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON "+
					" FROM       "+
					" TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAH B,  "+
					" TBLPPKPERINTAHHAOBDTL C, TBLPPKOB D, "+
					" TBLPFDFAIL E, TBLPPKPERMOHONAN F, "+
					" TBLPPKKEPUTUSANPERMOHONAN G, TBLPPKPERBICARAAN H, "+
					" TBLPPKPERINTAH I,TBLPPKHA L,TBLPPKSIMATI P, "+
					" TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
					" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN "+
					" AND I.ID_PERINTAH = A.ID_PERINTAH AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST "+
					" AND D.ID_OB = C.ID_PA4 AND L.ID_HA= A.ID_HA "+
					" AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN "+
					" AND A.ID_JENISPERINTAH = 1 AND C.STATUS_TADBIR = 'Y' "+
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 AND A.FLAG_HARTA = 'B' "+
					" AND E.ID_FAIL = "+id_fail+" "+
					" AND C.ID_OB IS NULL    "+
					" UNION "+
					" SELECT  "+
					" L.NILAI_HA_TARIKHMOHON "+
					//" SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON "+
					" FROM   "+    
					" TBLPPKPERINTAHHAOBMST A,TBLPPKPERINTAH B, TBLPPKPERINTAHHAOBDTL C,  "+
					" TBLPPKOB D,TBLPFDFAIL E,TBLPPKPERMOHONAN F, "+
					" TBLPPKKEPUTUSANPERMOHONAN G,TBLPPKPERBICARAAN H, "+
					" TBLPPKPERINTAH I,TBLPPKHA L,TBLPPKSIMATI P, "+
					" TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
					" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN AND I.ID_PERINTAH = A.ID_PERINTAH "+
					" AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST "+
					" AND D.ID_OB = C.ID_OB AND L.ID_HA= A.ID_HA "+
					" AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN AND A.ID_JENISPERINTAH = 2 "+
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 AND A.FLAG_HARTA = 'B' "+
					" AND E.ID_FAIL = "+id_fail+" ";
					//" ) ";
			//System.out.println(" ::: NILAI_HA ::: "+sql);
			rs = stmt.executeQuery(sql);
			
			//System.out.println(" ----------------lepas HA-----------------------");
			double total = 0.0;
			//String 
			while (rs.next()) {
				total += ((rs.getString("NILAI_HA_TARIKHMOHON").equals("") || rs.getString("NILAI_HA_TARIKHMOHON") == null) ? 0 : rs.getDouble("NILAI_HA_TARIKHMOHON"));
			}			
		return total;
	} 
		finally {
		/*
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
		*/
	}
}

	public void doProcessing(HttpServletRequest request
		,HttpServletResponse response
		,ServletContext context
		,Map parameters) throws Exception {		
		String idfail = "";
		if (parameters.get("idfail") != null){
			idfail = (String)parameters.get("idfail");
		}
		
		String nofail = logic.getNoFailByIdFail(idfail);
		String nilai_1 = "0";
		String nilai_2 = "00";
		double total = 0;
		if(!idfail.equals("")){
			Db db = null;
			try {
				db = new Db();			
				BorangF bhta = new BorangF();
				double hta = bhta.nilaiHTA(idfail, db);
				double ha = nilaiHA(idfail,db);
				double hutang = nilaiHutang(idfail,db);
//				double hta = nilaiHTA(idfail,db);
//				double ha = nilaiHA(idfail,db);
//				double hutang = nilaiHutang(idfail,db);
				myLogger.info("nilaiHTA="+hta+",nilaiHA="+ha+"nilaiHTA Hutang="+hutang);			
				
				total = (hta + ha) - hutang;
				String total_curr = String.format("%,.2f", total);
				//String s = total_curr;
				if (total_curr.contains(".")) {
//					System.out.println(" ada titik : ");
					//String[] str_total_curr = total_curr.split(".");
					//System.out.println(" str_total_curr : "+str_total_curr[0]);
					nilai_1 = total_curr.substring(0, total_curr.indexOf('.'));
					nilai_2 = total_curr.substring(total_curr.lastIndexOf(".") + 1);
					//System.out.println(" nilai_2 : "+nilai_2);
					//nilai_1 = str_total_curr[0];
					//System.out.println(" nilai_1 : "+nilai_1);
					//nilai_2 = str_total_curr[1];
					//System.out.println(" total_curr : "+total_curr+" nilai_1 "+nilai_1+" nilai_2 "+nilai_2);
				
				}
				
			}catch(Exception e){
				System.out.println("ERROR KIRA ++++++++++++++++++++++++++++++++++++++ "+e.toString());
			}finally {
				if (db != null)
					db.close();
			}
			
		}
		parameters.put("NILAI_BERSIH_1", nilai_1);
		parameters.put("NILAI_BERSIH_2", nilai_2);
		
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		//System.out.println("**************** BorangE2 check ID SESSION : "+user_id);
		request.setAttribute("user_id", user_id);
		
		Vector list = new Vector();
		Vector listPentadbir = new Vector();
		Vector flagBorangF = new Vector();
		list.clear();
		listPentadbir.clear();
		flagBorangF.clear();
		
		String idPegawai = logic.getIdUnitPSKByIdFail(idfail);
		boolean statusKebKemaskini = false;
		String flagKemaskini = "";
		// validate status hantar PNB
		statusKebKemaskini = logic.getFlagKebenaranEdit(idfail);
		//System.out.println("statusKebKemaskini==="+statusKebKemaskini);
		
		if (statusKebKemaskini) {
			parameters.put("flag_keb_kemaskini", "Y");
		//	System.out.println("flag_keb_kemaskini===Y");
			flagKemaskini = "yes";
		} else {
			parameters.put("flag_keb_kemaskini", "T");
		//	System.out.println("flag_keb_kemaskini===T");
			flagKemaskini = "no";
		}
		
		
		String statusFail = "";
		statusFail = logic.getStatusFail(idfail); 
		//System.out.println("statusFail==="+statusFail);
		if (statusFail.equals("21")) {			
			parameters.put("statusFail", "T");
		//	System.out.println("statusFail===T");
		} else {
			parameters.put("statusFail", "Y");
		//	System.out.println("statusFail===Y");						
		}
		
		logic.setMaklumatPegawai(idPegawai, "B");
		list = logic.getBeanMaklumatPegawai();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			parameters.put("namaPegawai",(String)h.get("nama"));
			parameters.put("jawatan",(String)h.get("jawatan"));
		
		}

		parameters.put("daerahMohon",logic.getDaerahMohon(nofail, "B"));
		
		logic.setSenaraiOBPentadbir(idfail);
		listPentadbir = logic.getBeanSenaraiOBPentadbir();
		String namaPentadbir = "";
		String namaPentadbirBorangF = "";
		logic.setSenaraiOBPentadbir(idfail);
		listPentadbir = logic.getBeanSenaraiOBPentadbir();

		if (listPentadbir.size() != 0){
			for(int i = 0; i < listPentadbir.size(); i++){
				Hashtable h = (Hashtable)listPentadbir.get(i);
				namaPentadbir = (String)h.get("maklumatPentadbirUtkBorangE");
				parameters.put("namaPentadbir", namaPentadbir);
				namaPentadbirBorangF = h.get("maklumatPentadbir").toString();
				parameters.put("namaPentadbirBorangF", namaPentadbirBorangF);
			
			}
		
		}else{
			parameters.put("namaPentadbir", namaPentadbir);
			parameters.put("namaPentadbirBorangF", namaPentadbirBorangF);
		
		}
		
		//parameters.put("namaPentadbir", namaPentadbir);
		//namaPentadbirBorangF = logic.setMaklumatPentadbirString( idfail);
		//parameters.put("namaPentadbirBorangF", namaPentadbirBorangF);
		parameters.put("flagBorangF", logic.getFlagBorangF(idfail));		
		String flagVersion = (String)parameters.get("flagVersion");		
		/*if(flagVersion.equals("")){
			flagVersion = "no";
		}*/
		
		doVersioningPPK("BorangE",idfail,nofail,flagVersion,flagKemaskini);
		
	}
	
	
}
