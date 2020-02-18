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

public class BorangF extends EkptgReportServlet {
	static Logger myLog = Logger.getLogger(BorangF.class);
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public BorangF() {
		super.setReportName("BorangF2");
		super.setFolderName("ppk");
		
	}
	
	public double nilaiHTA(String id_fail, Db db) throws Exception {
		myLog.info("nilaiHTA:id_fail="+id_fail);
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = db.getStatement();
			sql = " " +
//				01
				" SELECT " +
				" L.ID_HTA,NVL(L.NILAI_HTA_TARIKHMOHON,0.0)  NILAI_HTA_TARIKHMOHON"+
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
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN " +
				" AND I.FLAG_JENIS_KEPUTUSAN = 0  "+
				" AND A.ID_JENISPERINTAH = 1 "+
				" AND A.FLAG_HARTA = 'B' " +
				" AND C.STATUS_TADBIR = 'Y' " +
				" AND C.ID_OB IS NULL "+
				" AND E.ID_FAIL = "+id_fail+" " +
				" UNION       "+
//				02
				" SELECT " +
				" L.ID_HTA,NVL(L.NILAI_HTA_TARIKHMOHON,0.0)  NILAI_HTA_TARIKHMOHON"+
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
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN " +
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 "+
					" AND A.ID_JENISPERINTAH = 1 "+
					" AND A.FLAG_HARTA = 'B' "+
					" AND C.STATUS_TADBIR = 'Y' " +
					" AND C.ID_OB IS NULL  "+
					" AND E.ID_FAIL = "+id_fail+" " +
					" UNION "+
//					03
					" SELECT " +
					" L.ID_HTA,NVL(L.NILAI_HTA_TARIKHMOHON,0.0)  NILAI_HTA_TARIKHMOHON"+
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
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN " +
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 " +
					" AND A.ID_JENISPERINTAH = 1 "+
					" AND A.FLAG_HARTA = 'B' "+
					" AND C.STATUS_TADBIR = 'Y' " +
					" AND C.ID_OB IS NULL       "+
					" AND E.ID_FAIL = "+id_fail+" "+
					" UNION "+
//					04
					" SELECT " +
					" L.ID_HTA,NVL(L.NILAI_HTA_TARIKHMOHON,0.0)  NILAI_HTA_TARIKHMOHON"+
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
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 " +
					" AND A.ID_JENISPERINTAH = 1 " +
					" AND A.FLAG_HARTA = 'B' "+
					" AND C.STATUS_TADBIR = 'Y' "+
					" AND C.ID_OB IS NULL       "+
					" AND E.ID_FAIL = "+id_fail+" "+
					" UNION "+
//					05
					" SELECT " +
					" L.ID_HTA ,NVL(L.NILAI_HTA_TARIKHMOHON,0.0)  NILAI_HTA_TARIKHMOHON"+
					" FROM TBLPPKPERINTAHHTAOBMST A" +
					" ,TBLPPKPERINTAH B" +
					" ,TBLPPKPERINTAHHTAOBDTL C" +
					" ,TBLPPKOB D" +
					" ,TBLPFDFAIL E" +
					" ,TBLPPKPERMOHONAN F "+
					" ,TBLPPKKEPUTUSANPERMOHONAN G" +
					" ,TBLPPKPERBICARAAN H" +
					" ,TBLPPKPERINTAH I" +
					" ,TBLPPKHTA L" +
					" ,TBLRUJJENISHAKMILIK M" +
					" ,TBLRUJMUKIM N " +
					" ,TBLRUJDAERAH O" +
					" ,TBLPPKSIMATI P "+
					" ,TBLPPKPERMOHONANSIMATI Q "+
					" WHERE  "+
					" B.ID_PERINTAH = A.ID_PERINTAH AND E.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
					" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN AND I.ID_PERINTAH = A.ID_PERINTAH "+
					" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
					" AND D.ID_OB = C.ID_OB AND L.ID_HTA = A.ID_HTA "+
					" AND M.ID_JENISHAKMILIK = L.ID_JENISHM AND N.ID_MUKIM = L.ID_MUKIM "+
					" AND O.ID_DAERAH = L.ID_DAERAH AND P.ID_SIMATI = Q.ID_SIMATI "+
					" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN " +
					" AND I.FLAG_JENIS_KEPUTUSAN = 0 " +
					" AND A.ID_JENISPERINTAH = 2 "+
					" AND A.FLAG_HARTA = 'B' "+
					" AND E.ID_FAIL = "+id_fail+
					" ";
//				myLog.info("nilaiHTA:sql="+sql);
				rs = stmt.executeQuery(sql);
				double total = 0.0;
				while (rs.next()) {
					total += ((rs.getString("NILAI_HTA_TARIKHMOHON").equals("") || rs.getString("NILAI_HTA_TARIKHMOHON") == null) ? 0 : rs.getDouble("NILAI_HTA_TARIKHMOHON"));
//					myLog.info("nilaiHTA:total(Internal)="+(total + rs.getDouble("NILAI_HTA_TARIKHMOHON")));
				}			
//			myLog.info("nilaiHTA:total="+total);
			return total;
			
		} finally {	}
		
	}
	
	public double nilaiHutang(String id_fail, Db db) throws Exception {
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = db.getStatement();
	
			sql = " SELECT " +
			" NVL(A.NILAI_HUTANG,0) AS NILAI_HUTANG "+
			" FROM TBLPPKOB A, "+
			" TBLPPKPERMOHONAN B, "+
			" TBLPPKPERMOHONANSIMATI C, "+
			" TBLPPKSIMATI D "+
			" WHERE D.ID_SIMATI = A.ID_SIMATI "+
			" AND D.ID_SIMATI = C.ID_SIMATI "+
			" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+
			" AND A.ID_TARAFKPTG = 2 "+
			" AND B.ID_FAIL = "+id_fail;
	
			rs = stmt.executeQuery(sql);
			double total = 0.0;
			while (rs.next()) {
				total += ((rs.getString("NILAI_HUTANG").equals("") || rs.getString("NILAI_HUTANG") == null) ? 0 : rs.getDouble("NILAI_HUTANG"));
			}			
			return total;

		} finally { }

	}
		
	public double nilaiHA(String id_fail, Db db) throws Exception {
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = db.getStatement();		
			sql = ""+					
//			1	
				" SELECT "+
				" L.ID_HA,NVL(L.NILAI_HA_TARIKHMOHON,0.0) L.NILAI_HA_TARIKHMOHON "+
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
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN " +
				" AND I.FLAG_JENIS_KEPUTUSAN = 0  "+
				" AND A.ID_JENISPERINTAH = 1 "+
				" AND A.FLAG_HARTA = 'B' " +
				" AND C.STATUS_TADBIR = 'Y' " +
				" AND C.ID_OB IS NULL "+
				" AND E.ID_FAIL = "+id_fail+" " +
				" UNION       "+
//			2	
				" SELECT  "+
				" L.ID_HA,NVL(L.NILAI_HA_TARIKHMOHON,0.0) L.NILAI_HA_TARIKHMOHON "+
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
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN " +
				" AND I.FLAG_JENIS_KEPUTUSAN = 0 "+
				" AND A.ID_JENISPERINTAH = 1 "+
				" AND A.FLAG_HARTA = 'B' "+
				" AND C.STATUS_TADBIR = 'Y' " +
				" AND C.ID_OB IS NULL  "+
				" AND E.ID_FAIL = "+id_fail+" " +
				" UNION "+
//			3		
				" SELECT  "+
				" L.ID_HA,NVL(L.NILAI_HA_TARIKHMOHON,0.0) L.NILAI_HA_TARIKHMOHON "+
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
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN " +
				" AND I.FLAG_JENIS_KEPUTUSAN = 0 " +
				" AND A.ID_JENISPERINTAH = 1 "+
				" AND A.FLAG_HARTA = 'B' "+
				" AND C.STATUS_TADBIR = 'Y' " +
				" AND C.ID_OB IS NULL "+      
				" AND E.ID_FAIL = "+id_fail+" "+
				" UNION "+
//			4		
				" SELECT  "+
				" L.ID_HA,NVL(L.NILAI_HA_TARIKHMOHON,0.0) L.NILAI_HA_TARIKHMOHON "+
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
				" AND I.FLAG_JENIS_KEPUTUSAN = 0 " +
				" AND A.ID_JENISPERINTAH = 1 " +
				" AND A.FLAG_HARTA = 'B' "+
				" AND C.STATUS_TADBIR = 'Y' "+
				" AND C.ID_OB IS NULL    "+
				" AND E.ID_FAIL = "+id_fail+" "+
				" UNION "+
//			5	
				" SELECT  "+
				" L.ID_HA,NVL(L.NILAI_HA_TARIKHMOHON,0.0) L.NILAI_HA_TARIKHMOHON "+
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
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN " +
				" AND I.FLAG_JENIS_KEPUTUSAN = 0 " +
				" AND A.ID_JENISPERINTAH = 2 "+
				" AND A.FLAG_HARTA = 'B' "+
				" AND E.ID_FAIL = "+id_fail+" ";
			//myLog.info("nilaiHA:sql="+sql);
			rs = stmt.executeQuery(sql);
			double total = 0.0;
			while (rs.next()) {
				total += ((rs.getString("NILAI_HA_TARIKHMOHON").equals("") || rs.getString("NILAI_HA_TARIKHMOHON") == null) ? 0 : rs.getDouble("NILAI_HA_TARIKHMOHON"));
			}			
			return total;
		
		}finally {	}
		
	}
	
	public void doProcessing(HttpServletRequest request
		,HttpServletResponse response
		,ServletContext context
		,Map parameters) throws Exception {
		
		String idfail = "";
		Boolean flagLampiran = null;
		if (parameters.get("idfail") != null){
			idfail = parameters.get("idfail").toString();
		}
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("_ekptg_user_id");		
		//myLogger.info("masuk idfail e ::::::::::: " + idfail);
		String nilai_1 = "0";
		String nilai_2 = "00";
		double total = 0;
		//System.out.println("masuk idfail e ::::::::::: " + idfail);
		if(!idfail.equals("")){
			Db db = null;
			try {
				db = new Db();
				//System.out.println("TOTAL atas ::::::::::: "+total);
				double hta = nilaiHTA(idfail,db);
				double ha = nilaiHA(idfail,db);
				double hutang = nilaiHutang(idfail,db);
//				System.out.println("nilaiHTA  ::::::::::: "+hta);
//				System.out.println("nilaiHA  ::::::::::: "+ha);
//				System.out.println("nilaiHTA  ::::::::::: "+hutang);				
				total = (hta + ha) - hutang;
				System.out.println("TOTAL bawah ::::::::::: "+total);
				String total_curr = String.format("%,.2f", total);
				System.out.println(" total_curr : "+total_curr);
				//String s = total_curr;
				if (total_curr.contains(".")) {
					System.out.println(" ada titik : ");
					//String[] str_total_curr = total_curr.split(".");
					//System.out.println(" str_total_curr : "+str_total_curr[0]);
					nilai_1 = total_curr.substring(0, total_curr.indexOf('.'));
					System.out.println(" nilai_1 : "+nilai_1);
					nilai_2 = total_curr.substring(total_curr.lastIndexOf(".") + 1);
					System.out.println(" nilai_2 : "+nilai_2);
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
		
		String nofail = logic.getNoFailByIdFail(idfail);
		flagLampiran = logic.getFlagLampiran(logic.getIdPerintah(idfail));
		
//		System.out.println("flagLampiran==="+flagLampiran);
		parameters.put("flagLampiran",flagLampiran);

		Vector list = new Vector();
		Vector listPentadbir = new Vector();
		list.clear();
		listPentadbir.clear();
		
		//aishahlatip add
		boolean statusKebKemaskini = false;
		String flagKemaskini = "";
		// validate status hantar PNB
		statusKebKemaskini = logic.getFlagKebenaranEdit(idfail);
//		System.out.println("statusKebKemaskini==="+statusKebKemaskini);
		
		if (statusKebKemaskini) {
			parameters.put("flag_keb_kemaskini", "Y");
//			System.out.println("flag_keb_kemaskini===Y");
			flagKemaskini = "yes";
	
		} else {
			parameters.put("flag_keb_kemaskini", "T");
//			System.out.println("flag_keb_kemaskini===T");
			flagKemaskini = "no";
		
		}
		
		String statusFail = "";
		statusFail = logic.getStatusFail(idfail); 
//		System.out.println("statusFail==="+statusFail);
		if (statusFail.equals("41")) {
			parameters.put("statusFail", "Y");
//			System.out.println("statusFail===Y");
			
		} else {
			parameters.put("statusFail", "T");
//			System.out.println("statusFail===T");
			
		}
		
		String idPegawai = logic.getIdUnitPSKByIdFail(idfail);
		
		logic.setMaklumatPegawai(idPegawai, "B");
		list = logic.getBeanMaklumatPegawai();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			parameters.put("namaPegawai",h.get("nama").toString());
			parameters.put("jawatan",h.get("jawatan").toString());
		
		}

		parameters.put("daerahMohon",logic.getDaerahMohon(nofail, "B"));
		
		logic.setSenaraiOBPentadbir(idfail);
		listPentadbir = logic.getBeanSenaraiOBPentadbir();
		String namaPentadbir = "";
		for(int i = 0; i < listPentadbir.size(); i++){
			Hashtable h = (Hashtable)listPentadbir.get(i);
			namaPentadbir = h.get("maklumatPentadbir").toString();			
		
		}
		parameters.put("namaPentadbir", namaPentadbir);
		String namaPentadbirBorangF = "";
		namaPentadbirBorangF = logic.setMaklumatPentadbirString( idfail);
		parameters.put("namaPentadbirBorangF", namaPentadbirBorangF);
//		System.out.println("namaPentadbir==="+namaPentadbir);
		String flagVersion = (String)parameters.get("flagVersion");
//		System.out.println("flagVersion==="+flagVersion);
		//doVersioning("BorangF2",idfail,nofail,flagVersion);//aishahlatip close
		doVersioningPPK("BorangF2",idfail,nofail,flagVersion,flagKemaskini);
		
	}
	
	
}