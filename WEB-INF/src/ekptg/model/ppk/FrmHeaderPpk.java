package ekptg.model.ppk;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class FrmHeaderPpk {

	static Logger myLogger = Logger.getLogger(FrmHeaderPpk.class);

	// get SUBUURSAN
	// /SELECT C.TARIKH_MASUK,E.KETERANGAN,H.ID_SIMATI, A.SEKSYEN,
	// A.ID_PERMOHONAN, B.NO_FAIL, MAX(C.ID_SUBURUSANSTATUSFAIL) AS
	// ID_SUBURUSANSTATUSFAIL, C.AKTIF, E.ID_STATUS,H.ID_PERMOHONANSIMATI,
	// A.TARIKH_MOHON,B.FLAG_JENIS_FAIL FROM TBLPPKPERMOHONAN A, TBLPFDFAIL B,
	// TBLRUJSUBURUSANSTATUSFAIL C, TBLRUJSUBURUSANSTATUS D, TBLRUJSTATUS E,
	// TBLRUJNEGERI F, TBLRUJDAERAH G, TBLPPKPERMOHONANSIMATI H WHERE
	// G.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN
	// U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND
	// UR.USER_ID = '293') AND A.ID_STATUS NOT IN (56,999) AND B.ID_FAIL =
	// A.ID_FAIL AND D.ID_SUBURUSANSTATUS = C.ID_SUBURUSANSTATUS AND E.ID_STATUS
	// = D.ID_STATUS AND A.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_PERMOHONAN =
	// H.ID_PERMOHONAN AND A.ID_NEGERIMHN = F.ID_NEGERI AND A.ID_DAERAHMHN =
	// G.ID_DAERAH AND TRIM(UPPER(B.NO_FAIL)) = 'JKPTG/PK/01/06/0182/2008' GROUP
	// BY C.TARIKH_MASUK,E.KETERANGAN,H.ID_SIMATI, A.SEKSYEN, A.ID_PERMOHONAN,
	// B.NO_FAIL, C.AKTIF, E.ID_STATUS, H.ID_PERMOHONANSIMATI,
	// A.TARIKH_MOHON,B.FLAG_JENIS_FAIL ORDER BY
	// C.TARIKH_MASUK,ID_SUBURUSANSTATUSFAIL ASC

	// fungsi untuk menanda permohonan yang telah dicreate history
	public static void updatePermohonan(String id_permohonan, Db db)
			throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}

			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_permohonan", id_permohonan);
			r.add("flag_create_history", "Y");
			sql = r.getSQLUpdate("tblppkpermohonan");
			stmt.executeUpdate(sql);

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}

		}

	}

	// razman rombak query header
	public Hashtable getHeaderData(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		Db db = null;
		String sql = "";
		String user_id = (String)session.getAttribute("_ekptg_user_id");	
		String role = (String)session.getAttribute("myrole");
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//String id_fail_temp = getIdFail(id_permohonan,db);	
			Hashtable getIDfailSimati = getIdFailNew(id_permohonan,db);	
			myLogger.info(" getIDfailSimati : "+getIDfailSimati.size());
			
			String id_fail_temp = "";
			String id_simati_temp = "";
			String check_expiry = "";
			String alert_expired = "";
			String current_flag_kebenaran = "";
			if(getIDfailSimati.size() > 0)
			{
				id_fail_temp = (String) getIDfailSimati.get("ID_FAIL");
				id_simati_temp = (String) getIDfailSimati.get("ID_SIMATI");
			}	
			
			if(!id_fail_temp.equals(""))
			{
				current_flag_kebenaran = getFlagKebenaran(id_permohonan,user_id,db);
				check_expiry = "";
				if(current_flag_kebenaran.equals("1"))
				{
					check_expiry = getDurationKebKemaskini(id_permohonan,user_id,id_fail_temp,db);
					if(check_expiry.equals("Y"))
					{
						updateKebKemaskini(id_permohonan,db);									
						deleteAgihan(id_fail_temp,db);
					}				
				}
			
				
				if(current_flag_kebenaran.equals("1") && check_expiry.equals("Y"))
				{
					alert_expired = "PERINGATAN: Tempoh kemaskini maklumat telah tamat. \n"+
				"Sekiranya anda ingin membuat pegemaskinian maklumat pada fail ini, sila mohon kebenaran.";
				}
				
				List listIdPermohonanSebelum = listIdPermohonanSebelum(session,id_permohonan,id_simati_temp,db);
				for(int i = 0; i < listIdPermohonanSebelum.size();i++)
			    {			   
				   Map m = (Map) listIdPermohonanSebelum.get(i);
				   String ID_PERMOHONAN_DAHULU = (String) m.get("ID_PERMOHONAN");
				   myLogger.info(" ID_PERMOHONAN TERDAHULU :"+(String) m.get("ID_PERMOHONAN"));	
				   if(!ID_PERMOHONAN_DAHULU.equals(""))
				   {
					   updatePermohonan(ID_PERMOHONAN_DAHULU,db);
					   //open get ob dlu
					   List checkOBPermohonanNew = checkOBPermohonanNew(id_permohonan, user_id, id_simati_temp,db);
					   for(int x = 0; x < checkOBPermohonanNew.size();x++)
					   {
						   Map k = (Map) checkOBPermohonanNew.get(x);
						   String temp_id_ob = (String) k.get("temp_id_ob");
						   String temp_TARIKH_LAHIR = (String) k.get("temp_TARIKH_LAHIR");
						   int temp_UMUR = (int) k.get("temp_UMUR");
						   int temp_NO_SUBJAKET = (int) k.get("temp_NO_SUBJAKET");
						   int temp_NO_SUBJAKET_MAX = (int) k.get("temp_NO_SUBJAKET_MAX");
						   myLogger.info("OB -->  temp_id_ob : "+temp_id_ob+" temp_TARIKH_LAHIR : "+temp_TARIKH_LAHIR
								   +" temp_UMUR : "+temp_UMUR+" temp_NO_SUBJAKET : "+temp_NO_SUBJAKET
								   +" temp_NO_SUBJAKET_MAX : "+temp_NO_SUBJAKET_MAX);						   
						   if (recordCount(temp_id_ob, id_permohonan, db) == 0) {
								updateOBPermohonan(id_permohonan, temp_id_ob, user_id, db);
						   }					   
					   }
					   //close get ob dlu
					   
					 //open get HA dlu
					   List checkHAPermohonanNew = checkHAPermohonanNew(id_permohonan, user_id, id_simati_temp, db);
					   for(int y = 0; y < checkHAPermohonanNew.size();y++)
					   {
						   Map u = (Map) checkHAPermohonanNew.get(y);
						   String temp_id_HA = (String) u.get("temp_id_HA");
						   int temp_NO_SUBJAKET = (int) u.get("temp_NO_SUBJAKET");
						   int temp_NO_SUBJAKET_MAX = (int) u.get("temp_NO_SUBJAKET_MAX");					  
						   
						   myLogger.info("HA -->  temp_id_HA : "+temp_id_HA+" temp_NO_SUBJAKET : "+temp_NO_SUBJAKET
								   +" temp_NO_SUBJAKET_MAX : "+temp_NO_SUBJAKET_MAX);  					   
						   if (recordCountHA(temp_id_HA, id_permohonan, db) == 0) {
								updateHAPermohonan(id_permohonan, temp_id_HA, user_id, db);
						   }			   
					   }
					   //close get HA dlu
					   
					   
					 //open get HTA dlu
					   List checkHTAPermohonanNew = checkHTAPermohonanNew(id_permohonan, user_id,id_simati_temp,db);
					   for(int q = 0; q < checkHTAPermohonanNew.size();q++)
					   {
						   Map z = (Map) checkHTAPermohonanNew.get(q);
						   String temp_id_HTA = (String) z.get("temp_id_HTA");
						   int temp_NO_SUBJAKET = (int) z.get("temp_NO_SUBJAKET");
						   int temp_NO_SUBJAKET_MAX = (int) z.get("temp_NO_SUBJAKET_MAX");					  
						   
						   myLogger.info("HTA --> temp_id_HTA : "+temp_id_HTA+" temp_NO_SUBJAKET : "+temp_NO_SUBJAKET
								   +" temp_NO_SUBJAKET_MAX : "+temp_NO_SUBJAKET_MAX);  					   
						   if (recordCountHTA(temp_id_HTA, id_permohonan, db) == 0) {
								updateHTAPermohonan(id_permohonan, temp_id_HTA, user_id, db);
						   }			   
					   }
					   //close get HTA dlu
					   
					 //open get hubungan OB dlu
					   List checkOBHubunganPermohonanNew =  checkOBHubunganPermohonanNew(id_permohonan, user_id, id_simati_temp,  db);
					   for(int b = 0; b < checkOBHubunganPermohonanNew.size();b++)
					   {
						   Map t = (Map) checkOBHubunganPermohonanNew.get(b);
						   String temp_id_ob = (String) t.get("temp_id_ob");
						   myLogger.info("HUBUNGAN --> temp_id_ob : "+temp_id_ob); 
						   if (recordCountHubungan(temp_id_ob, id_permohonan, db) == 0) {
								updateOBHubunganPermohonan(id_permohonan, temp_id_ob,user_id, db);
						   }					   
					   }
					 //close get hubungan OB dlu  
					   
				   }
			    }
			}
			
			sql = " SELECT DISTINCT NVL(P.NO_SUBJAKET,'0') AS NO_SUBJAKET," +
					"NVL((SELECT MAX(NVL(P.NO_SUBJAKET,'0')) FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P " +
			" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND SM.ID_SIMATI IN ( " +
			" SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"')) " +
			" ,'0') AS NO_SUBJAKET_MAX," +
			" U.USER_NAME,P.CATATAN_KEBENARAN_EDIT,P.FLAG_KEBENARAN_EDIT," +
			" F.ID_FAIL, F.NO_FAIL, UPPER(PJ.NAMA_PEJABAT) AS NAMA_PEJABAT, "+
			" TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, P.SEKSYEN, UPPER(PM.NAMA_PEMOHON) AS NAMA_PEMOHON," +
			" UPPER(ST.KETERANGAN) AS NAMA_STATUS, "+
			" UPPER(SM.NAMA_SIMATI) AS NAMA_SIMATI,UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI," +
			" UPPER(D.NAMA_DAERAH) AS DAERAH_MOHON, "+
			" UPPER(D1.NAMA_DAERAH) AS DAERAH_PEJABAT,PM.EMEL,ST.ID_STATUS,PSM.ID_PERMOHONANSIMATI," +
			" TO_CHAR(SM.TARIKH_MATI,'DD/MM/YYYY') AS TARIKH_MATI, "+
			" (SELECT COUNT(*) FROM TBLPPKHTA HTA,TBLPPKPERMOHONANSIMATI SM "+
			" WHERE HTA.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI AND SM.ID_PERMOHONAN = '"+id_permohonan+"') AS TOTAL_HTA, "+
			" (SELECT COUNT(*) FROM TBLPPKHA HTA,TBLPPKPERMOHONANSIMATI SM "+
			" WHERE HTA.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI AND SM.ID_PERMOHONAN = '"+id_permohonan+"') AS TOTAL_HA, "+
			" TO_CHAR(P.TARIKH_MOHON_BICARA_SEMULA ,'DD/MM/YYYY') AS TARIKH_MOHON_BICARA_SEMULA,P.CATATAN_BICARA_SEMULA,UB.USER_NAME AS USER_NAME_BICARA,P.USER_ID_BICARA_SEMULA,P.ID_PERMOHONAN_SEBELUM_BICARA," +
			" (SELECT NO_FAIL FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP  " +
			" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA) " +
			" AS NO_FAIL_SEBELUM_BICARA, " +
			" (SELECT PP.ID_STATUS FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP  " +
			" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA) " +
			" AS ID_STATUS_SEBELUM_BICARA, " +
			" (SELECT PP.SEKSYEN FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP  " +
			" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA) " +
			" AS SEKSYEN_SEBELUM_BICARA, " +
			" (SELECT PSM.ID_PERMOHONANSIMATI FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP,TBLPPKPERMOHONANSIMATI PSM  " +
			" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA AND PP.ID_PERMOHONAN = PSM.ID_PERMOHONAN) " +
			" AS ID_PERMOHONANSIMATI_SEBELUM, " +
			" P.ID_PERMOHONAN_SELEPAS_BICARA, " +
			" (SELECT NO_FAIL FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP " +
			" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA) " +
			" AS NO_FAIL_SELEPAS_BICARA, " +
			" (SELECT PP.ID_STATUS FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP " +
			" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA) " +
			" AS ID_STATUS_SELEPAS_BICARA, " +
			" (SELECT PP.SEKSYEN FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP " +
			" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA) " +
			" AS SEKSYEN_SELEPAS_BICARA, " +
			" (SELECT PSM.ID_PERMOHONANSIMATI FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP,TBLPPKPERMOHONANSIMATI PSM  " +
			" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA AND PP.ID_PERMOHONAN = PSM.ID_PERMOHONAN) " +
			" AS ID_PERMOHONANSIMATI_SELEPAS," +
			" PM.NAMA_PEMOHON_LAMA, PM.NO_KP_BARU_LAMA, PM.NO_KP_LAMA_LAMA, PM.NO_KP_LAIN_LAMA,PM.JENIS_KP_LAMA " +
			" FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P, TBLPPKPEMOHON PM,TBLRUJSTATUS ST,TBLPPKPERMOHONANSIMATI PSM, "+
			" TBLPPKSIMATI SM,TBLRUJDAERAH D,TBLRUJNEGERI N,TBLRUJPEJABATURUSAN PU,TBLRUJPEJABATJKPTG PJ,TBLRUJDAERAH D1" +
			",USERS U,USERS UB "+
			" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PEMOHON = PM.ID_PEMOHON AND P.USER_ID_BICARA_SEMULA = UB.USER_ID(+) "+
			" AND ST.ID_STATUS(+) = P.ID_STATUS AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND SM.ID_SIMATI = PSM.ID_SIMATI "+
			" AND P.ID_NEGERIMHN = N.ID_NEGERI AND P.ID_DAERAHMHN = D.ID_DAERAH "+
			" AND PU.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG "+
			" AND PJ.ID_SEKSYEN = 2 "+
			" AND PU.ID_DAERAHURUS = P.ID_DAERAHMHN "+
			" AND P.USER_ID_KEBENARAN_EDIT = U.USER_ID(+) "+
			" AND PU.ID_JENISPEJABAT = PJ.ID_JENISPEJABAT " +
			" AND PJ.ID_JENISPEJABAT = 22 "+
			" AND PJ.ID_DAERAH = D1.ID_DAERAH ";
			//AND F.NO_FAIL = 'JKPTG/PK/03/01/0074/2010'
			if(flag_permohonan.equals("Y"))
			{
			sql += " AND P.ID_PERMOHONAN = '"+id_permohonan+"' ";
			}
			if(flag_fail.equals("Y"))
			{
			sql += " AND F.ID_FAIL = '"+id_fail+"' ";
			}		
			myLogger.info(":::::::: GET HEADER" + sql.toUpperCase());
			
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = new Hashtable();		
						
			while (rs.next()) {					
				h.put("HEADER_NO_SUBJAKET",rs.getString("NO_SUBJAKET") == null ? "" : rs.getString("NO_SUBJAKET"));
				h.put("HEADER_NO_SUBJAKET_MAX",rs.getString("NO_SUBJAKET_MAX") == null ? "" : rs.getString("NO_SUBJAKET_MAX"));				
				h.put("TARIKH_MATI",rs.getString("TARIKH_MATI") == null ? "" : rs.getString("TARIKH_MATI"));
				h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				h.put("TARIKH_MOHON",rs.getString("TARIKH_MOHON") == null ? "" : rs.getString("TARIKH_MOHON"));
				h.put("SEKSYEN",rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
				h.put("NAMA_PEMOHON",rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				h.put("NAMA_STATUS",rs.getString("NAMA_STATUS") == null ? "" : rs.getString("NAMA_STATUS"));
				h.put("NAMA_SIMATI",rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("DAERAH_MOHON",rs.getString("DAERAH_MOHON") == null ? "" : rs.getString("DAERAH_MOHON"));
				h.put("DAERAH_PEJABAT",rs.getString("DAERAH_PEJABAT") == null ? "" : rs.getString("DAERAH_PEJABAT"));
				h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("FLAG_KEBENARAN_EDIT",rs.getString("FLAG_KEBENARAN_EDIT") == null ? "" : rs.getString("FLAG_KEBENARAN_EDIT"));
				h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("CATATAN_KEBENARAN_EDIT",rs.getString("CATATAN_KEBENARAN_EDIT") == null ? "" : rs.getString("CATATAN_KEBENARAN_EDIT"));
				h.put("USER_ROLE",role == null ? "" : role);
				h.put("ID_PERMOHONANSIMATI",rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));	
				h.put("TOTAL_HA",rs.getString("TOTAL_HA") == null ? "" : rs.getString("TOTAL_HA"));
				h.put("TOTAL_HTA",rs.getString("TOTAL_HTA") == null ? "" : rs.getString("TOTAL_HTA"));				
				h.put("CATATAN_BICARA_SEMULA",rs.getString("CATATAN_BICARA_SEMULA") == null ? "" : rs.getString("CATATAN_BICARA_SEMULA"));
				h.put("USER_NAME_BICARA",rs.getString("USER_NAME_BICARA") == null ? "" : rs.getString("USER_NAME_BICARA"));
				h.put("USER_ID_BICARA_SEMULA",rs.getString("USER_ID_BICARA_SEMULA") == null ? "" : rs.getString("USER_ID_BICARA_SEMULA"));
				h.put("ID_PERMOHONAN_SEBELUM_BICARA",rs.getString("ID_PERMOHONAN_SEBELUM_BICARA") == null ? "" : rs.getString("ID_PERMOHONAN_SEBELUM_BICARA"));
				h.put("NO_FAIL_SEBELUM_BICARA",rs.getString("NO_FAIL_SEBELUM_BICARA") == null ? "" : rs.getString("NO_FAIL_SEBELUM_BICARA"));
				h.put("ID_STATUS_SEBELUM_BICARA",rs.getString("ID_STATUS_SEBELUM_BICARA") == null ? "" : rs.getString("ID_STATUS_SEBELUM_BICARA"));
				h.put("SEKSYEN_SEBELUM_BICARA",rs.getString("SEKSYEN_SEBELUM_BICARA") == null ? "" : rs.getString("SEKSYEN_SEBELUM_BICARA"));
				h.put("ID_PERMOHONANSIMATI_SEBELUM",rs.getString("ID_PERMOHONANSIMATI_SEBELUM") == null ? "" : rs.getString("ID_PERMOHONANSIMATI_SEBELUM"));
				h.put("ID_PERMOHONAN_SELEPAS_BICARA",rs.getString("ID_PERMOHONAN_SELEPAS_BICARA") == null ? "" : rs.getString("ID_PERMOHONAN_SELEPAS_BICARA"));
				h.put("NO_FAIL_SELEPAS_BICARA",rs.getString("NO_FAIL_SELEPAS_BICARA") == null ? "" : rs.getString("NO_FAIL_SELEPAS_BICARA"));
				h.put("ID_STATUS_SELEPAS_BICARA",rs.getString("ID_STATUS_SELEPAS_BICARA") == null ? "" : rs.getString("ID_STATUS_SELEPAS_BICARA"));
				h.put("SEKSYEN_SELEPAS_BICARA",rs.getString("SEKSYEN_SELEPAS_BICARA") == null ? "" : rs.getString("SEKSYEN_SELEPAS_BICARA"));
				h.put("ID_PERMOHONANSIMATI_SELEPAS",rs.getString("ID_PERMOHONANSIMATI_SELEPAS") == null ? "" : rs.getString("ID_PERMOHONANSIMATI_SELEPAS"));
				h.put("NAMA_PEMOHON_LAMA",rs.getString("NAMA_PEMOHON_LAMA") == null ? "" : rs.getString("NAMA_PEMOHON_LAMA"));
				h.put("NO_KP_BARU_LAMA",rs.getString("NO_KP_BARU_LAMA") == null ? "" : rs.getString("NO_KP_BARU_LAMA"));
				h.put("NO_KP_LAMA_LAMA",rs.getString("NO_KP_LAMA_LAMA") == null ? "" : rs.getString("NO_KP_LAMA_LAMA"));
				h.put("NO_KP_LAIN_LAMA",rs.getString("NO_KP_LAIN_LAMA") == null ? "" : rs.getString("NO_KP_LAIN_LAMA"));
				h.put("JENIS_KP_LAMA",rs.getString("JENIS_KP_LAMA") == null ? "" : rs.getString("JENIS_KP_LAMA"));
				h.put("TARIKH_MOHON_BICARA_SEMULA",rs.getString("TARIKH_MOHON_BICARA_SEMULA") == null ? "" : rs.getString("TARIKH_MOHON_BICARA_SEMULA"));
				h.put("TEST",101);
				h.put("ALERT_LUPUT_KEMASKINI",alert_expired);
				h.put("FLAG_KEBENARAN",current_flag_kebenaran);
				h.put("CHECK_EXPIRY",check_expiry);h.put("STATUS_EDIT_USER",getStatusEdit(rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"),user_id,db));
				//myLogger.info("id_fail ::::::::::::: "id_fail);
				//open ::::::::::::::: aishah buat kat sini
					//set flag yg menunjukkan fail dibukak oleh unit luar
				if(getStatusUnitLuar(id_permohonan,user_id,db).equals("Y"))
				{
					h.put("CAPAIAN_FAIL_UNIT_LUAR","Y");
				}
				else
				{
					h.put("CAPAIAN_FAIL_UNIT_LUAR","N");
				}				
				//close ::::::::::::::: aishah buat kat sini				
				//CHECKING HANTAR LAPORAN PERINTAH
				if(getHantarLaporanPerintah(id_permohonan,user_id,db).equals("Y"))
				{
					h.put("HANTAR_LAP_PERINTAH","Y");
					//System.out.println("HANTAR_LAP_PERINTAH==Y");
				}
				else
				{
					h.put("HANTAR_LAP_PERINTAH","N");
					//System.out.println("HANTAR_LAP_PERINTAH==N");
				}
				
				
				if (getMaklumateHutangSimati(id_permohonan, db).equals("Y"))
				{
				h.put("ADA_MAKLUMAT_HUTANG_SIMATI", "Y");
				String id = getIdHutang(id_permohonan, db);
				id = id + 1;
				h.put("ID_Hutang", id);
				     
				}
				else
				{
				h.put("ADA_MAKLUMAT_HUTANG_SIMATI", "N");
				}
			}			    
			long stopTime = System.currentTimeMillis()/1000;
			//myLogger.info(" MASA HEADER PPK LODING :::: START : "+startTime+""+" STOP : "+stopTime+" TOTAL : "+ (stopTime- startTime));
			return h;			
		}
		finally {
			if (db != null)
				db.close();  
		}
	}

	
	@SuppressWarnings("unchecked")
	public List listIdPermohonanSebelum(HttpSession session,
			String id_permohonan,String id_simati, Db db) throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		List listIdPermohonanSebelum = null;
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT PSM.ID_PERMOHONANSIMATI,PSM.ID_PERMOHONAN  FROM TBLPPKPERMOHONANSIMATI PSM   "
					+ " WHERE "
					+ " PSM.ID_SIMATI = '"
					+ id_simati
					+ "' AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y "
					+ " WHERE Y.FLAG_CREATE_HISTORY IS NULL AND X.ID_PERMOHONAN = Y.ID_PERMOHONAN AND X.ID_SIMATI = '"+id_simati+"' AND Y.ID_STATUS <> '999'   "
					+ " AND NVL(Y.NO_SUBJAKET,'0') "
					+ " <= (SELECT NVL(NO_SUBJAKET,'0') "
					+ " FROM TBLPPKPERMOHONAN WHERE ID_STATUS <> '999' AND  ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";
//			myLogger.info(" HEADER SQL listIdPermohonanSebelum :" + sql);
			rs = stmt.executeQuery(sql);
			listIdPermohonanSebelum = Collections
					.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				listIdPermohonanSebelum.add(h);
			}

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
		return listIdPermohonanSebelum;
	}

	public Hashtable getHeaderData_LAMA(HttpSession session,
			String id_permohonan, String flag_permohonan, String id_fail,
			String flag_fail) throws Exception {

		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String user_id = (String) session.getAttribute("_ekptg_user_id");

		String IDFAIL = "";// aishahlatip

		try {
			long startTime = System.currentTimeMillis() / 1000;
			// TimeUnit.SECONDS.sleep(3);
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String current_flag_kebenaran = getFlagKebenaran(id_permohonan,
					user_id, db);
			String check_expiry = "";
			if (current_flag_kebenaran.equals("1")) {
				check_expiry = getDurationKebKemaskini(id_permohonan, user_id,
						IDFAIL, db);
				if (check_expiry.equals("Y")) {
					updateKebKemaskini(id_permohonan, db);
					String id_fail_temp = getIdFail(id_permohonan, db);
					deleteAgihan(id_fail_temp, db);
				}

			}
			String alert_expired = "";

			/*
			 * System.out.println("current_flag_kebenaran ::::::::::::"+
			 * current_flag_kebenaran);
			 * System.out.println("check_expiry ::::::::::::"+check_expiry);
			 */

			if (current_flag_kebenaran.equals("1") && check_expiry.equals("Y")) {
				alert_expired = "PERINGATAN: Tempoh kemaskini maklumat telah tamat. \n"
						+ "Sekiranya anda ingin membuat pegemaskinian maklumat pada fail ini, sila mohon kebenaran.";
			}

			// System.out.println("#############################header##############################");
			// /1-getcurrent flag_kebenaran
			// /2-check expired flag_kebenaran
			// /3-kalo dah expired update flag_kebenaran = 0
			// /4-hantar alert context.put sekali ngn header

			sql1 = " SELECT PSM.ID_PERMOHONANSIMATI,PSM.ID_PERMOHONAN  FROM TBLPPKPERMOHONANSIMATI PSM   "
					+ " WHERE "
					+ " PSM.ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "') AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y "
					+ " WHERE Y.FLAG_CREATE_HISTORY IS NULL AND X.ID_PERMOHONAN = Y.ID_PERMOHONAN AND Y.ID_STATUS <> '999'   "
					+ " AND NVL(Y.NO_SUBJAKET,'0') "
					+ " <= (SELECT NVL(NO_SUBJAKET,'0') "
					+ " FROM TBLPPKPERMOHONAN WHERE ID_STATUS <> '999' AND  ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";

			myLogger.info("CHECK BERAPA PERMOHONAN SEBELUM :" + sql1);
			ResultSet rs1 = stmt.executeQuery(sql1);
			Hashtable h1;
			h1 = new Hashtable();
			int tot = 0;
			while (rs1.next()) {
				tot++;
				String temp_id_permohonan = rs1.getString("ID_PERMOHONAN") == null ? ""
						: rs1.getString("ID_PERMOHONAN");
				myLogger.info("--------------------------------###no permohonan :"
						+ tot + ":" + temp_id_permohonan);
				updatePermohonan(temp_id_permohonan, db);
				checkOBPermohonan(temp_id_permohonan, user_id, db);
				checkHAPermohonan(temp_id_permohonan, user_id, db);
				checkHTAPermohonan(temp_id_permohonan, user_id, db);
				checkOBHubunganPermohonan(temp_id_permohonan, user_id, db);
			}

			/*
			 * sql2 =
			 * " SELECT HUP.ID_HUBUNGANOB,PSM.ID_PERMOHONANSIMATI  FROM TBLPPKHUBUNGANOB HUP,TBLPPKOB OB,TBLPPKPERMOHONANSIMATI PSM "
			 * +
			 * " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND HUP.ID_OB = OB.ID_OB "
			 * +
			 * " AND PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y "
			 * +
			 * " WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN  AND NVL(Y.NO_SUBJAKET,'0') <= (SELECT NVL(NO_SUBJAKET,'0') "
			 * +
			 * " FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan+"')  "
			 * +
			 * " AND ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
			 * +id_permohonan+"'))";
			 * 
			 * ResultSet rs2 = stmt.executeQuery(sql2); Hashtable h2; h2 = new
			 * Hashtable(); int tot1 = 0; while (rs2.next()) { tot1++;
			 * //myLogger.info("###BERAPA KALI BUAT HUBUNGAN:"+tot1); String
			 * temp_ID_HUBUNGANOB = rs2.getString("ID_HUBUNGANOB") == null ? ""
			 * : rs2.getString("ID_HUBUNGANOB"); String temp_ID_PERMOHONANSIMATI
			 * = rs2.getString("ID_PERMOHONANSIMATI") == null ? "" :
			 * rs2.getString("ID_PERMOHONANSIMATI");
			 * //myLogger.info("###temp_ID_HUBUNGANOB :"+temp_ID_HUBUNGANOB);
			 * //myLogger
			 * .info("###temp_ID_PERMOHONANSIMATI :"+temp_ID_PERMOHONANSIMATI);
			 * //myLogger.info("###recordCount(temp_id_ob,id_permohonan):"+
			 * recordCountHubungan
			 * (temp_ID_HUBUNGANOB,temp_ID_PERMOHONANSIMATI));
			 * if(recordCountHubungan
			 * (temp_ID_HUBUNGANOB,temp_ID_PERMOHONANSIMATI) == 0) {
			 * //updateOBPermohonan(id_permohonan,temp_id_ob,user_id);
			 * updateOBHubunganPermohonan
			 * (temp_ID_PERMOHONANSIMATI,temp_ID_HUBUNGANOB,user_id); } }
			 */

			sql = " SELECT DISTINCT NVL(P.NO_SUBJAKET,'0') AS NO_SUBJAKET,"
					+ "NVL((SELECT MAX(NVL(P.NO_SUBJAKET,'0')) FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND SM.ID_SIMATI IN ( "
					+ " SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "')) "
					+ " ,'0') AS NO_SUBJAKET_MAX,"
					+ " U.USER_NAME,P.CATATAN_KEBENARAN_EDIT,P.FLAG_KEBENARAN_EDIT,"
					+ " F.ID_FAIL, F.NO_FAIL, UPPER(PJ.NAMA_PEJABAT) AS NAMA_PEJABAT, "
					+ " TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, P.SEKSYEN, UPPER(PM.NAMA_PEMOHON) AS NAMA_PEMOHON,"
					+ " UPPER(ST.KETERANGAN) AS NAMA_STATUS, "
					+ " UPPER(SM.NAMA_SIMATI) AS NAMA_SIMATI,UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI,"
					+ " UPPER(D.NAMA_DAERAH) AS DAERAH_MOHON, "
					+ " UPPER(D1.NAMA_DAERAH) AS DAERAH_PEJABAT,PM.EMEL,ST.ID_STATUS,PSM.ID_PERMOHONANSIMATI,"
					+ " TO_CHAR(SM.TARIKH_MATI,'DD/MM/YYYY') AS TARIKH_MATI, "
					+ " (SELECT COUNT(*) FROM TBLPPKHTA HTA,TBLPPKPERMOHONANSIMATI SM "
					+ " WHERE HTA.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI AND SM.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "') AS TOTAL_HTA, "
					+ " (SELECT COUNT(*) FROM TBLPPKHA HTA,TBLPPKPERMOHONANSIMATI SM "
					+ " WHERE HTA.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI AND SM.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "') AS TOTAL_HA, "
					+

					" TO_CHAR(P.TARIKH_MOHON_BICARA_SEMULA ,'DD/MM/YYYY') AS TARIKH_MOHON_BICARA_SEMULA,P.CATATAN_BICARA_SEMULA,UB.USER_NAME AS USER_NAME_BICARA,P.USER_ID_BICARA_SEMULA,P.ID_PERMOHONAN_SEBELUM_BICARA,"
					+ " (SELECT NO_FAIL FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP  "
					+ " WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA) "
					+ " AS NO_FAIL_SEBELUM_BICARA, "
					+ " (SELECT PP.ID_STATUS FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP  "
					+ " WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA) "
					+ " AS ID_STATUS_SEBELUM_BICARA, "
					+ " (SELECT PP.SEKSYEN FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP  "
					+ " WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA) "
					+ " AS SEKSYEN_SEBELUM_BICARA, "
					+ " (SELECT PSM.ID_PERMOHONANSIMATI FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP,TBLPPKPERMOHONANSIMATI PSM  "
					+ " WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA AND PP.ID_PERMOHONAN = PSM.ID_PERMOHONAN) "
					+ " AS ID_PERMOHONANSIMATI_SEBELUM, "
					+ " P.ID_PERMOHONAN_SELEPAS_BICARA, "
					+ " (SELECT NO_FAIL FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP "
					+ " WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA) "
					+ " AS NO_FAIL_SELEPAS_BICARA, "
					+ " (SELECT PP.ID_STATUS FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP "
					+ " WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA) "
					+ " AS ID_STATUS_SELEPAS_BICARA, "
					+ " (SELECT PP.SEKSYEN FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP "
					+ " WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA) "
					+ " AS SEKSYEN_SELEPAS_BICARA, "
					+ " (SELECT PSM.ID_PERMOHONANSIMATI FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP,TBLPPKPERMOHONANSIMATI PSM  "
					+ " WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA AND PP.ID_PERMOHONAN = PSM.ID_PERMOHONAN) "
					+ " AS ID_PERMOHONANSIMATI_SELEPAS,"
					+ " PM.NAMA_PEMOHON_LAMA, PM.NO_KP_BARU_LAMA, PM.NO_KP_LAMA_LAMA, PM.NO_KP_LAIN_LAMA,PM.JENIS_KP_LAMA "
					+

					" FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P, TBLPPKPEMOHON PM,TBLRUJSTATUS ST,TBLPPKPERMOHONANSIMATI PSM, "
					+ " TBLPPKSIMATI SM,TBLRUJDAERAH D,TBLRUJNEGERI N,TBLRUJPEJABATURUSAN PU,TBLRUJPEJABATJKPTG PJ,TBLRUJDAERAH D1"
					+ ",USERS U,USERS UB "
					+ " WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PEMOHON = PM.ID_PEMOHON AND P.USER_ID_BICARA_SEMULA = UB.USER_ID(+) "
					+ " AND ST.ID_STATUS(+) = P.ID_STATUS AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND SM.ID_SIMATI = PSM.ID_SIMATI "
					+ " AND P.ID_NEGERIMHN = N.ID_NEGERI AND P.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND PU.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG "
					+ " AND PJ.ID_SEKSYEN = 2 "
					+ " AND PU.ID_DAERAHURUS = P.ID_DAERAHMHN "
					+ " AND P.USER_ID_KEBENARAN_EDIT = U.USER_ID(+) "
					+ " AND PJ.ID_JENISPEJABAT = 22 "
					+ " AND PJ.ID_DAERAH = D1.ID_DAERAH ";
			// AND F.NO_FAIL = 'JKPTG/PK/03/01/0074/2010'
			if (flag_permohonan.equals("Y")) {
				sql += " AND P.ID_PERMOHONAN = '" + id_permohonan + "' ";
			}
			if (flag_fail.equals("Y")) {
				sql += " AND F.ID_FAIL = '" + id_fail + "' ";
			}

			System.out.println(":::::::: GET HEADER" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			h = new Hashtable();

			// Salnizam edit starts

			// myLogger.info("CHECK BERAPA PERMOHONAN SEBELUM :"+sql1);
			// ResultSet rs1 = stmt.executeQuery(sql1);
			// Hashtable h1;
			// h1 = new Hashtable();

			// Salnizam edit ends
			String role = (String) session.getAttribute("myrole");

			while (rs.next()) {
				h.put("HEADER_NO_SUBJAKET",
						rs.getString("NO_SUBJAKET") == null ? "" : rs
								.getString("NO_SUBJAKET"));
				h.put("HEADER_NO_SUBJAKET_MAX",
						rs.getString("NO_SUBJAKET_MAX") == null ? "" : rs
								.getString("NO_SUBJAKET_MAX"));
				h.put("TARIKH_MATI", rs.getString("TARIKH_MATI") == null ? ""
						: rs.getString("TARIKH_MATI"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NO_FAIL",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT"));
				h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON") == null ? ""
						: rs.getString("TARIKH_MOHON"));
				h.put("SEKSYEN",
						rs.getString("SEKSYEN") == null ? "" : rs
								.getString("SEKSYEN"));
				h.put("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON") == null ? ""
						: rs.getString("NAMA_PEMOHON"));
				h.put("NAMA_STATUS", rs.getString("NAMA_STATUS") == null ? ""
						: rs.getString("NAMA_STATUS"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("DAERAH_MOHON", rs.getString("DAERAH_MOHON") == null ? ""
						: rs.getString("DAERAH_MOHON"));
				h.put("DAERAH_PEJABAT",
						rs.getString("DAERAH_PEJABAT") == null ? "" : rs
								.getString("DAERAH_PEJABAT"));
				h.put("EMEL",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("FLAG_KEBENARAN_EDIT",
						rs.getString("FLAG_KEBENARAN_EDIT") == null ? "" : rs
								.getString("FLAG_KEBENARAN_EDIT"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("CATATAN_KEBENARAN_EDIT",
						rs.getString("CATATAN_KEBENARAN_EDIT") == null ? ""
								: rs.getString("CATATAN_KEBENARAN_EDIT"));
				h.put("USER_ROLE", role == null ? "" : role);
				h.put("ID_PERMOHONANSIMATI",
						rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs
								.getString("ID_PERMOHONANSIMATI"));
				h.put("TOTAL_HA",
						rs.getString("TOTAL_HA") == null ? "" : rs
								.getString("TOTAL_HA"));
				h.put("TOTAL_HTA",
						rs.getString("TOTAL_HTA") == null ? "" : rs
								.getString("TOTAL_HTA"));

				h.put("CATATAN_BICARA_SEMULA",
						rs.getString("CATATAN_BICARA_SEMULA") == null ? "" : rs
								.getString("CATATAN_BICARA_SEMULA"));
				h.put("USER_NAME_BICARA",
						rs.getString("USER_NAME_BICARA") == null ? "" : rs
								.getString("USER_NAME_BICARA"));
				h.put("USER_ID_BICARA_SEMULA",
						rs.getString("USER_ID_BICARA_SEMULA") == null ? "" : rs
								.getString("USER_ID_BICARA_SEMULA"));
				h.put("ID_PERMOHONAN_SEBELUM_BICARA", rs
						.getString("ID_PERMOHONAN_SEBELUM_BICARA") == null ? ""
						: rs.getString("ID_PERMOHONAN_SEBELUM_BICARA"));
				h.put("NO_FAIL_SEBELUM_BICARA",
						rs.getString("NO_FAIL_SEBELUM_BICARA") == null ? ""
								: rs.getString("NO_FAIL_SEBELUM_BICARA"));
				h.put("ID_STATUS_SEBELUM_BICARA", rs
						.getString("ID_STATUS_SEBELUM_BICARA") == null ? ""
						: rs.getString("ID_STATUS_SEBELUM_BICARA"));
				h.put("SEKSYEN_SEBELUM_BICARA",
						rs.getString("SEKSYEN_SEBELUM_BICARA") == null ? ""
								: rs.getString("SEKSYEN_SEBELUM_BICARA"));
				h.put("ID_PERMOHONANSIMATI_SEBELUM", rs
						.getString("ID_PERMOHONANSIMATI_SEBELUM") == null ? ""
						: rs.getString("ID_PERMOHONANSIMATI_SEBELUM"));
				h.put("ID_PERMOHONAN_SELEPAS_BICARA", rs
						.getString("ID_PERMOHONAN_SELEPAS_BICARA") == null ? ""
						: rs.getString("ID_PERMOHONAN_SELEPAS_BICARA"));
				h.put("NO_FAIL_SELEPAS_BICARA",
						rs.getString("NO_FAIL_SELEPAS_BICARA") == null ? ""
								: rs.getString("NO_FAIL_SELEPAS_BICARA"));
				h.put("ID_STATUS_SELEPAS_BICARA", rs
						.getString("ID_STATUS_SELEPAS_BICARA") == null ? ""
						: rs.getString("ID_STATUS_SELEPAS_BICARA"));
				h.put("SEKSYEN_SELEPAS_BICARA",
						rs.getString("SEKSYEN_SELEPAS_BICARA") == null ? ""
								: rs.getString("SEKSYEN_SELEPAS_BICARA"));
				h.put("ID_PERMOHONANSIMATI_SELEPAS", rs
						.getString("ID_PERMOHONANSIMATI_SELEPAS") == null ? ""
						: rs.getString("ID_PERMOHONANSIMATI_SELEPAS"));

				h.put("NAMA_PEMOHON_LAMA",
						rs.getString("NAMA_PEMOHON_LAMA") == null ? "" : rs
								.getString("NAMA_PEMOHON_LAMA"));
				h.put("NO_KP_BARU_LAMA",
						rs.getString("NO_KP_BARU_LAMA") == null ? "" : rs
								.getString("NO_KP_BARU_LAMA"));
				h.put("NO_KP_LAMA_LAMA",
						rs.getString("NO_KP_LAMA_LAMA") == null ? "" : rs
								.getString("NO_KP_LAMA_LAMA"));
				h.put("NO_KP_LAIN_LAMA",
						rs.getString("NO_KP_LAIN_LAMA") == null ? "" : rs
								.getString("NO_KP_LAIN_LAMA"));
				h.put("JENIS_KP_LAMA",
						rs.getString("JENIS_KP_LAMA") == null ? "" : rs
								.getString("JENIS_KP_LAMA"));
				h.put("TARIKH_MOHON_BICARA_SEMULA", rs
						.getString("TARIKH_MOHON_BICARA_SEMULA") == null ? ""
						: rs.getString("TARIKH_MOHON_BICARA_SEMULA"));
				h.put("TEST", 101);

				h.put("ALERT_LUPUT_KEMASKINI", alert_expired);
				h.put("FLAG_KEBENARAN", current_flag_kebenaran);
				h.put("CHECK_EXPIRY", check_expiry);

				h.put("STATUS_EDIT_USER",
						getStatusEdit(
								rs.getString("ID_FAIL") == null ? "" : rs
										.getString("ID_FAIL"), user_id, db));
				// myLogger.info("id_fail ::::::::::::: "id_fail);

				// open ::::::::::::::: aishah buat kat sini
				// set flag yg menunjukkan fail dibukak oleh unit luar
				if (getStatusUnitLuar(id_permohonan, user_id, db).equals("Y")) {
					h.put("CAPAIAN_FAIL_UNIT_LUAR", "Y");
				} else {
					h.put("CAPAIAN_FAIL_UNIT_LUAR", "N");
				}

				// close ::::::::::::::: aishah buat kat sini

				// CHECKING HANTAR LAPORAN PERINTAH
				if (getHantarLaporanPerintah(id_permohonan, user_id, db)
						.equals("Y")) {
					h.put("HANTAR_LAP_PERINTAH", "Y");
					 System.out.println("HANTAR_LAP_PERINTAH==Y");
				} else {
					h.put("HANTAR_LAP_PERINTAH", "N");
					 System.out.println("HANTAR_LAP_PERINTAH==N");
				}
				
				if (getMaklumateHutangSimati(id_permohonan, db).equals("Y"))
					{
					System.out.println("*******getMaklumateHutangSimati00*******");
					h.put("ADA_MAKLUMAT_HUTANG_SIMATI", "Y");
					System.out.println("*******getMaklumateHutangSimati*******");
					String id = getIdHutang(id_permohonan, db);
					h.put("ID_Hutang", id);
					System.out.println("*******getMaklumateHutangSimati2*******");
					}
				else
				{
					System.out.println("*******getMaklumateHutangSimati222*******");
					h.put("ADA_MAKLUMAT_HUTANG_SIMATI", "N");
				}

			}

			long stopTime = System.currentTimeMillis() / 1000;
			// myLogger.info(" MASA HEADER PPK LODING :::: START : "+startTime+""+" STOP : "+stopTime+" TOTAL : "+
			// (stopTime- startTime));
			return h;

		} finally {
			if (db != null)
				db.close();
		}

	}

	public Hashtable getPemohonData2(HttpSession session, String id_permohonan)
			throws Exception {
		// System.out.println("****Baca dekat getPemohonData2******");
		Db db2 = null;
		String sql2 = "";
		try {
			db2 = new Db();
			Statement stmt2 = db2.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			sql2 = "SELECT P.ID_PERMOHONANSIMATI "
					+ " FROM TBLPPKPERMOHONANSIMATI P "
					+ " WHERE P.ID_PERMOHONAN = '" + id_permohonan + "'";

			myLogger.info("::::GET MAKLUMAT PEMOHON2 " + sql2.toUpperCase());
			ResultSet rs2 = stmt2.executeQuery(sql2);
			Hashtable h2;
			h2 = new Hashtable();

			while (rs2.next()) {
				h2.put("ID_PERMOHONANSIMATI",
						rs2.getString("ID_PERMOHONANSIMATI") == null ? "" : rs2
								.getString("ID_PERMOHONANSIMATI"));
				// h.put("NAMA_PEMOHON",ID_PERMOHONANSIMATI);

			}
			return h2;

		} finally {
			if (db2 != null)
				db2.close();
		}
	}

	// aishahlatip maklumat flag kebanaran Kemaskini

	public static void updateKebKemaskini(String id_permohonan, Db db)
			throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}

			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_permohonan", id_permohonan);
			r.add("FLAG_KEBENARAN_EDIT", "");
			r.add("user_id_kebenaran_edit", "");
			r.add("catatan_kebenaran_edit", "");
			r.add("TUJUAN_PINDAAN", "");
			r.add("ID_PEGAWAI_MOHON_EDIT", "");
			r.add("ID_PEMOHON_MOHON_EDIT", "");
			sql = r.getSQLUpdate("tblppkpermohonan");
			stmt.executeUpdate(sql);
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public static void deleteAgihan(String id_fail, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			sql = "DELETE FROM TBLEDITAGIHAN  WHERE ID_FAIL  = '" + id_fail
					+ "' ";
			myLogger.info("sql delete TBLEDITAGIHAN ::: " + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}

	}

	public String getStatusUnitLuar(String id_permohonan, String user_id, Db db)
			throws Exception {
		Db db1 = null;
		String sql2 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = " SELECT DISTINCT B.SEKSYEN, B.ID_STATUS, B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI,"
					+ " C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS AS ID_STATUS2, H.ID_PERMOHONANSIMATI, A.FLAG_JENIS_FAIL, K.USER_LOGIN, C.NO_SIJIL_MATI"
					+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J, USERS K"
					+ " WHERE "
					+ " A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI AND D.ID_PEMOHON = B.ID_PEMOHON AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS"
					+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND "
					+ "B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND B.ID_MASUK = K.USER_ID(+) "
					+ "AND E.AKTIF = 1 AND G.ID_STATUS NOT IN (155) "
					+ "AND B.ID_STATUS NOT IN (999) "
					+ "AND J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"
					+ user_id + "' ";

			sql2 += " UNION "
					+ " SELECT DISTINCT PBU_U.ID_DAERAHURUS  "
					+ " FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "
					+ " WHERE ID_STATUS = 2  "
					+ " AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "
					+ " AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "
					+ " AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "
					+ " AND PBU.ID_PEMOHON = '" + user_id + "'  ";

			sql2 += ") ";
			sql2 += " AND B.ID_PERMOHONAN = " + id_permohonan + " ";

			// myLogger.info("CHECK STATUS UNIT LUAR :"+sql2);

			Statement stmt2 = db1.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);

			String countUserLuar = "Y";
			while (rs2.next()) {
				countUserLuar = "N";
			}
			// myLogger.info("countUserLuar :"+countUserLuar);
			return countUserLuar;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	public String getMaklumateHutangSimati(String id_permohonan, Db db)
			throws Exception {
		Db db1 = null;
		String sql2 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			} 
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = " SELECT DISTINCT A.NO_PENGENALAN_BARU, B.ID_SENARAIHUTANG, B.FLAG_SALIN, A.ID_HUTANG "+
					" FROM TBLPPKSENARAIHUTANG B, TBLPPKHUTANG A, TBLPPKSIMATI C "+
					" WHERE B.ID_HUTANG = A.ID_HUTANG "+
					" AND A.NO_PENGENALAN_BARU = C.NO_KP_BARU "+
					" AND B.FLAG_SALIN = '0' "+
					" AND B.TARIKH_SELESAI_HUTANG IS NULL "+
					" AND C.NO_KP_BARU in "+
					" (SELECT SM.NO_KP_BARU FROM TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN P, TBLPPKSIMATI SM "+
					" WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN "+
					" AND PSM.ID_SIMATI = SM.ID_SIMATI "+
					" AND PSM.ID_PERMOHONAN = "+id_permohonan+") ";

			System.out.println("getMaklumateHutangSimati************************* :"+sql2);

			Statement stmt2 = db1.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);

			String MaklumateHutangSimati = "N";
			
			while (rs2.next()) {
				MaklumateHutangSimati = "Y";
				
			}
			System.out.println("MaklumateHutangSimati************************* :"+MaklumateHutangSimati);
			// myLogger.info("countUserLuar :"+countUserLuar);
				return MaklumateHutangSimati;
				
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	public String getIdHutang(String id_permohonan, Db db)
			throws Exception {
		Db db1 = null;
		String sql2 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = " SELECT DISTINCT A.NO_PENGENALAN_BARU, B.ID_SENARAIHUTANG, B.FLAG_SALIN, A.ID_HUTANG "+
					" FROM TBLPPKSENARAIHUTANG B, TBLPPKHUTANG A, TBLPPKSIMATI C "+
					" WHERE B.ID_HUTANG = A.ID_HUTANG "+
					" AND A.NO_PENGENALAN_BARU = C.NO_KP_BARU "+
					" AND B.FLAG_SALIN = '0' "+
					" AND C.NO_KP_BARU in "+
					" (SELECT SM.NO_KP_BARU FROM TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN P, TBLPPKSIMATI SM "+
					" WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN "+
					" AND PSM.ID_SIMATI = SM.ID_SIMATI "+
					" AND PSM.ID_PERMOHONAN = "+id_permohonan+") ";

			 System.out.println("getIdHutangSimati************************* :"+sql2);

			Statement stmt2 = db1.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);

			String id_Hutang = null;
			while (rs2.next()) {
				id_Hutang = rs2.getString("ID_HUTANG");
				
			}
			// myLogger.info("countUserLuar :"+countUserLuar);
				return id_Hutang;
				
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	public String getMaklumateHutangPemohon(String id_permohonan, Db db)
			throws Exception {
		Db db1 = null;
		String sql2 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 =  " SELECT A.NO_PENGENALAN_BARU, B.ID_SENARAIHUTANG "+
					" FROM TBLPPKSENARAIHUTANG B, TBLPPKHUTANG A, TBLPPKPEMOHON C "+
					" WHERE  "+
					" B.ID_HUTANG = A.ID_HUTANG "+
					" AND "+
					" A.NO_PENGENALAN_BARU = C.NO_KP_BARU "+
					" AND C.NO_KP_BARU in "+
					" (SELECT DISTINCT PM.NO_KP_BARU FROM TBLPPKPERMOHONAN P, TBLPPKPEMOHON PM "+
					" WHERE  "+
					" P.ID_PEMOHON = PM.ID_PEMOHON "+
					" AND P.ID_PERMOHONAN = "+id_permohonan+")";

			 myLogger.info("getMaklumateHutangPemohon************************* :"+sql2);

			Statement stmt2 = db1.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);

			String MaklumateHutangPemohon = "N";
			while (rs2.next()) {
				MaklumateHutangPemohon = "Y";
			}
			// myLogger.info("countUserLuar :"+countUserLuar);
			return MaklumateHutangPemohon;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public String getHantarLaporanPerintah(String id_permohonan,
			String user_id, Db db) throws Exception {
		Db db1 = null;
		String sql2 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			// Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = " SELECT F.NO_FAIL FROM  TBLPPKPERBICARAAN B,"
					+ " TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P,TBLPFDFAIL F , TBLPPKPERINTAH G, TBLPPKHANTARPERINTAH H"
					+ " WHERE B.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN "
					+ " AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND P.ID_FAIL = F.ID_FAIL "
					+ " AND B.ID_PERBICARAAN = G.ID_PERBICARAAN"
					+ " AND G.ID_PERINTAH = H.ID_PERINTAH"
					+ " AND P.ID_PERMOHONAN = '" + id_permohonan + "'  ";

			// myLogger.info("CHECK STATUS UNIT LUAR :"+sql2);

			Statement stmt2 = db1.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);
			String countFileExist = "N";
			while (rs2.next()) {
				countFileExist = "Y";
			}
			// myLogger.info("countFileExist :"+countFileExist);
			return countFileExist;

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public String getStatusKemaskini(String id_permohonan, String user_id,
			String IDFAIL) throws Exception {
		Db db = null;
		String sql2 = "";
		try {
			db = new Db();
			// Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = " SELECT USER_ID "
					+ " FROM TBLEDITAGIHAN "
					+ " WHERE  "
					+ " (SELECT TO_date(sysdate, 'dd/mm/yyyy')-TO_DATE(TARIKH_KEMASKINI, 'dd/mm/yyyy') DAYS FROM DUAL) > 8 "
					+ " AND ID_FAIL = " + IDFAIL + " AND USER_ID =  " + user_id;

			myLogger.info("CHECK tempoh fail dibuka :" + sql2);

			Statement stmt2 = db.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);

			String countFileLonger = "N";
			while (rs2.next()) {
				countFileLonger = "Y";
			}
			myLogger.info("countFileExist :" + countFileLonger);
			return countFileLonger;
		} finally {
			if (db != null)
				db.close();
		}
	}

	// CHECK tempoh fail dibuka adakah lebih dari 7 hari
	public String getDurationKebKemaskini(String id_permohonan, String user_id,
			String IDFAIL, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT B.USER_ID "
					+ " FROM TBLPPKPERMOHONAN A, TBLEDITAGIHAN B "
					+ " WHERE  "
					+ " (SELECT TO_date(sysdate, 'dd/mm/yyyy')-TO_DATE(B.TARIKH_KEMASKINI, 'dd/mm/yyyy') DAYS FROM DUAL) > 8 "
					+ " AND A.ID_FAIL = B.ID_FAIL "
					+ " AND A.ID_PERMOHONAN = '" + id_permohonan + "' "
					+ " AND B.USER_ID = '" + user_id + "' ";
			myLogger.info("CHECK tempoh fail dibuka :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			String countFileLonger = "N";
			while (rs.next()) {
				countFileLonger = "Y";
			}
			myLogger.info("countFileExist :" + countFileLonger);
			return countFileLonger;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public Hashtable getPemohonData(HttpSession session, String id_permohonan,
			Db db) throws Exception {
		Db db1 = null;
		// Db db2 = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String user_id = (String) session.getAttribute("_ekptg_user_id");

		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT UPPER(P.ID_PERMOHONAN) AS ID_PERMOHONAN,UPPER(PM.ID_PEMOHON) AS ID_PEMOHON, UPPER(PM.NAMA_PEMOHON) AS NAMA_PEMOHON, "
					+ " UPPER(PM.NO_KP_BARU) AS NO_KP_BARU, UPPER(PM.NO_KP_LAMA) AS NO_KP_LAMA,PM.UMUR, "
					+ " UPPER(CASE WHEN JANTINA = '1' THEN 'LELAKI' WHEN JANTINA = '2' THEN 'PEREMPUAN' ELSE '' END) AS JANTINA, "
					+ " UPPER(CASE WHEN JENIS_AGAMA = '1' THEN 'ISLAM' WHEN JENIS_AGAMA = '2' THEN 'BUKAN ISLAM' ELSE '' END) AS AGAMA, "
					+ " UPPER(PM.ALAMAT_1) AS ALAMAT_1,UPPER(PM.ALAMAT_2) AS ALAMAT_2,UPPER(PM.ALAMAT_3) AS ALAMAT_3, "
					+ " UPPER(PM.POSKOD) AS POSKOD,UPPER(N.NAMA_NEGERI) AS NEGERI,UPPER(B.KETERANGAN) AS BANDAR, "
					+ " UPPER(CASE WHEN JENIS_WARGA = '1' THEN 'WARGANEGARA' WHEN JENIS_WARGA = '2' THEN 'BUKAN WARGANEGARA' "
					+ " WHEN JENIS_WARGA = '3' THEN 'PEMASTAUTIN TETAP' ELSE '' END) AS WARGA "
					+ " FROM TBLPPKPEMOHON PM,TBLPPKPERMOHONAN P,TBLRUJBANDAR B,TBLRUJNEGERI N "
					+ " WHERE PM.ID_PEMOHON = P.ID_PEMOHON AND PM.ID_BANDAR = B.ID_BANDAR(+) AND PM.ID_NEGERI = N.ID_NEGERI(+)  "
					+ " AND P.ID_PERMOHONAN = '" + id_permohonan + "'";
			myLogger.info("::::GET MAKLUMAT PEMOHON" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();

			while (rs.next()) {
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_PEMOHON", rs.getString("ID_PEMOHON") == null ? ""
						: rs.getString("ID_PEMOHON"));
				h.put("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON") == null ? ""
						: rs.getString("NAMA_PEMOHON"));
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? ""
						: rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? ""
						: rs.getString("NO_KP_LAMA"));
				h.put("UMUR",
						rs.getString("UMUR") == null ? "" : rs
								.getString("UMUR"));
				h.put("JANTINA",
						rs.getString("JANTINA") == null ? "" : rs
								.getString("JANTINA"));
				h.put("WARGA",
						rs.getString("WARGA") == null ? "" : rs
								.getString("WARGA"));
				h.put("AGAMA",
						rs.getString("AGAMA") == null ? "" : rs
								.getString("AGAMA"));
				h.put("ALAMAT_1",
						rs.getString("ALAMAT_1") == null ? "" : rs
								.getString("ALAMAT_1"));
				h.put("ALAMAT_2",
						rs.getString("ALAMAT_2") == null ? "" : rs
								.getString("ALAMAT_2"));
				h.put("ALAMAT_3",
						rs.getString("ALAMAT_3") == null ? "" : rs
								.getString("ALAMAT_3"));
				h.put("POSKOD",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				h.put("BANDAR",
						rs.getString("BANDAR") == null ? "" : rs
								.getString("BANDAR"));
				h.put("NEGERI",
						rs.getString("NEGERI") == null ? "" : rs
								.getString("NEGERI"));

				// h.put("NAMA_PEMOHON",ID_PERMOHONANSIMATI);

			}

			return h;

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public Hashtable getStatusEdit(String id_fail, String user_id, Db db)
			throws Exception {

		Db db1 = null;
		String sql = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}

			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT COUNT(*) AS CHECK_EDIT "
					+ " FROM TBLEDITAGIHAN A,USERS U WHERE A.USER_ID = U.USER_ID AND A.ID_FAIL = '"
					+ id_fail + "' AND A.USER_ID = '" + user_id + "'  " + " ";
			// myLogger.info(" getStatusEdit :"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				/*
				 * int CHECK_EDIT = rs.getInt("CHECK_EDIT") == 0 ? 0 :
				 * rs.getInt("CHECK_EDIT");
				 * 
				 * if(CHECK_EDIT>0) { h.put("CHECK_EDIT", "yes"); } else {
				 * h.put("CHECK_EDIT", "no"); }
				 */
			}
			return h;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public Hashtable getWujudLot(String id_permohonansimati, String nolot,
			String id_negeri, String id_daerah, String id_mukim,
			String id_jenishakmilik, String no_hakmilik, String no_lot)
			throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT T.ID_HTA, T.NO_HAKMILIK, T.ID_SIMATI, "
					+ " T.NO_PT, T.NILAI_HTA_TARIKHMOHON, T.NILAI_HTA_TARIKHMATI, "
					+ " T.ID_KATEGORI, T.ID_JENISHM, T.ID_JENISPB, T.ID_NEGERI, T.ID_DAERAH, T.ID_MUKIM, "
					+ " T.ID_LUAS, T.LUAS, T.LUAS_HMP, T.NO_CAGARAN, T.NO_PAJAKAN, T.JENIS_TNH, "
					+ " T.ALAMAT_HTA1, T.ALAMAT_HTA2, T.ALAMAT_HTA3, T.BANDAR_HTA, T.POSKOD_HTA, T.TARIKH_PERJANJIAN, "
					+ " T.NAMA_PEMAJU, T.ALAMAT_PEMAJU1, T.ALAMAT_PEMAJU2, T.ALAMAT_PEMAJU3, T.BANDAR_PEMAJU, "
					+ " T.POSKOD_PEMAJU,T.ID_NEGERIPEMAJU, T.CATATAN, T.BA_SIMATI, T.BB_SIMATI, "
					+ " T.NO_BANGUNAN, T.NO_TINGKAT, "
					+ " T.NO_PETAK, T.NO_STRATA, T.MAKLUMAT_TANAH,  T.NO_PERJANJIAN, T.JENIS_HTA, T.STATUS_PEMILIKAN, "
					+ " T.TANGGUNGAN, T.NO_PERSERAHAN, T.NAMA_RANCANGAN, T.NO_ROH, T.NO_LOT_ID, T.FLAG_KATEGORI_HTA, "
					+ " T.JENIS_KEPENTINGAN, T.ID_PERMOHONANSIMATI, "
					+ " T.ID_MASUK, T.TARIKH_MASUK, T.ID_KEMASKINI, T.TARIKH_KEMASKINI, "
					+ " T.ID_DB, "
					+ "T.ID_BANDARPEMAJU, T.ID_BANDARHTA, T.FLAG_PA, T.FLAG_PT, T.FLAG_SELESAI, T.ID_PERINTAHOBMST, "
					+ " T.ID_HAKMILIK, T.FLAG_DAFTAR " + " FROM	"
					+ " (SELECT * FROM TBLPPKHTA Y "
					+ " WHERE Y.JENIS_HTA = 'Y' "
					+ " AND UPPER(TRIM(Y.NO_PT)) = UPPER(TRIM('" + nolot.trim()
					+ "'))" + " AND Y.ID_NEGERI = '" + id_negeri + "' "
					+ " AND Y.ID_DAERAH = '" + id_daerah + "' "
					+ " AND Y.ID_MUKIM = '" + id_mukim + "' "
					+ " AND Y.ID_JENISHM = '" + id_jenishakmilik + "' "
					+ " AND Y.NO_HAKMILIK = '" + no_hakmilik + "' "
					+ " ORDER BY TARIKH_MASUK) T WHERE ROWNUM = 1";
			myLogger.info(" getWujudLot :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("NO_PETAK",
						rs.getString("NO_PETAK") == null ? "" : rs
								.getString("NO_PETAK"));
				h.put("NO_STRATA",
						rs.getString("NO_STRATA") == null ? "" : rs
								.getString("NO_STRATA"));
				h.put("MAKLUMAT_TANAH",
						rs.getString("MAKLUMAT_TANAH") == null ? "" : rs
								.getString("MAKLUMAT_TANAH"));
				h.put("NO_PERJANJIAN",
						rs.getString("NO_PERJANJIAN") == null ? "" : rs
								.getString("NO_PERJANJIAN"));
				h.put("JENIS_HTA",
						rs.getString("JENIS_HTA") == null ? "" : rs
								.getString("JENIS_HTA"));
				h.put("STATUS_PEMILIKAN",
						rs.getString("STATUS_PEMILIKAN") == null ? "" : rs
								.getString("STATUS_PEMILIKAN"));
				h.put("TANGGUNGAN", rs.getString("TANGGUNGAN") == null ? ""
						: rs.getString("TANGGUNGAN"));
				h.put("NO_PERSERAHAN",
						rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN"));
				h.put("NAMA_RANCANGAN",
						rs.getString("NAMA_RANCANGAN") == null ? "" : rs
								.getString("NAMA_RANCANGAN"));
				h.put("NO_ROH",
						rs.getString("NO_ROH") == null ? "" : rs
								.getString("NO_ROH"));
				h.put("NO_LOT_ID",
						rs.getString("NO_LOT_ID") == null ? "" : rs
								.getString("NO_LOT_ID"));
				h.put("FLAG_KATEGORI_HTA",
						rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs
								.getString("FLAG_KATEGORI_HTA"));
				h.put("JENIS_KEPENTINGAN",
						rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs
								.getString("JENIS_KEPENTINGAN"));
				h.put("ID_PERMOHONANSIMATI",
						rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs
								.getString("ID_PERMOHONANSIMATI"));
				h.put("ID_BANDARPEMAJU",
						rs.getString("ID_BANDARPEMAJU") == null ? "" : rs
								.getString("ID_BANDARPEMAJU"));
				h.put("ID_BANDARHTA", rs.getString("ID_BANDARHTA") == null ? ""
						: rs.getString("ID_BANDARHTA"));
				h.put("FLAG_PA",
						rs.getString("FLAG_PA") == null ? "" : rs
								.getString("FLAG_PA"));
				h.put("FLAG_PT",
						rs.getString("FLAG_PT") == null ? "" : rs
								.getString("FLAG_PT"));
				h.put("FLAG_SELESAI", rs.getString("FLAG_SELESAI") == null ? ""
						: rs.getString("FLAG_SELESAI"));
				h.put("ID_PERINTAHOBMST",
						rs.getString("ID_PERINTAHOBMST") == null ? "" : rs
								.getString("ID_PERINTAHOBMST"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("FLAG_DAFTAR", rs.getString("FLAG_DAFTAR") == null ? ""
						: rs.getString("FLAG_DAFTAR"));
				h.put("NAMA_PEMAJU", rs.getString("NAMA_PEMAJU") == null ? ""
						: rs.getString("NAMA_PEMAJU"));
				h.put("ALAMAT_PEMAJU1",
						rs.getString("ALAMAT_PEMAJU1") == null ? "" : rs
								.getString("ALAMAT_PEMAJU1"));
				h.put("ALAMAT_PEMAJU2",
						rs.getString("ALAMAT_PEMAJU2") == null ? "" : rs
								.getString("ALAMAT_PEMAJU2"));
				h.put("ALAMAT_PEMAJU3",
						rs.getString("ALAMAT_PEMAJU3") == null ? "" : rs
								.getString("ALAMAT_PEMAJU3"));
				h.put("BANDAR_PEMAJU",
						rs.getString("BANDAR_PEMAJU") == null ? "" : rs
								.getString("BANDAR_PEMAJU"));
				h.put("POSKOD_PEMAJU",
						rs.getString("POSKOD_PEMAJU") == null ? "" : rs
								.getString("POSKOD_PEMAJU"));
				h.put("ID_NEGERIPEMAJU",
						rs.getString("ID_NEGERIPEMAJU") == null ? "" : rs
								.getString("ID_NEGERIPEMAJU"));
				h.put("CATATAN",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("BA_SIMATI",
						rs.getString("BA_SIMATI") == null ? "" : rs
								.getString("BA_SIMATI"));
				h.put("BB_SIMATI",
						rs.getString("BB_SIMATI") == null ? "" : rs
								.getString("BB_SIMATI"));
				h.put("NO_BANGUNAN", rs.getString("NO_BANGUNAN") == null ? ""
						: rs.getString("NO_BANGUNAN"));
				h.put("NO_TINGKAT", rs.getString("NO_TINGKAT") == null ? ""
						: rs.getString("NO_TINGKAT"));
				h.put("ID_LUAS",
						rs.getString("ID_LUAS") == null ? "" : rs
								.getString("ID_LUAS"));
				h.put("LUAS",
						rs.getString("LUAS") == null ? "" : rs
								.getString("LUAS"));
				h.put("LUAS_HMP",
						rs.getString("LUAS_HMP") == null ? "" : rs
								.getString("LUAS_HMP"));
				h.put("NO_CAGARAN", rs.getString("NO_CAGARAN") == null ? ""
						: rs.getString("NO_CAGARAN"));
				h.put("NO_PAJAKAN", rs.getString("NO_PAJAKAN") == null ? ""
						: rs.getString("NO_PAJAKAN"));
				h.put("JENIS_TNH",
						rs.getString("JENIS_TNH") == null ? "" : rs
								.getString("JENIS_TNH"));
				h.put("ALAMAT_HTA1", rs.getString("ALAMAT_HTA1") == null ? ""
						: rs.getString("ALAMAT_HTA1"));
				h.put("ALAMAT_HTA2", rs.getString("ALAMAT_HTA2") == null ? ""
						: rs.getString("ALAMAT_HTA2"));
				h.put("ALAMAT_HTA3", rs.getString("ALAMAT_HTA3") == null ? ""
						: rs.getString("ALAMAT_HTA3"));
				h.put("BANDAR_HTA", rs.getString("BANDAR_HTA") == null ? ""
						: rs.getString("BANDAR_HTA"));
				h.put("POSKOD_HTA", rs.getString("POSKOD_HTA") == null ? ""
						: rs.getString("POSKOD_HTA"));
				h.put("TARIKH_PERJANJIAN",
						rs.getString("TARIKH_PERJANJIAN") == null ? "" : rs
								.getString("TARIKH_PERJANJIAN"));
				h.put("ID_HTA",
						rs.getString("ID_HTA") == null ? "" : rs
								.getString("ID_HTA"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("ID_SIMATI",
						rs.getString("ID_SIMATI") == null ? "" : rs
								.getString("ID_SIMATI"));
				h.put("NO_PT",
						rs.getString("NO_PT") == null ? "" : rs
								.getString("NO_PT"));
				h.put("NILAI_HTA_TARIKHMOHON",
						rs.getString("NILAI_HTA_TARIKHMOHON") == null ? "" : rs
								.getString("NILAI_HTA_TARIKHMOHON"));
				h.put("NILAI_HTA_TARIKHMATI",
						rs.getString("NILAI_HTA_TARIKHMATI") == null ? "" : rs
								.getString("NILAI_HTA_TARIKHMATI"));
				h.put("ID_KATEGORI", rs.getString("ID_KATEGORI") == null ? ""
						: rs.getString("ID_KATEGORI"));
				h.put("ID_JENISHM", rs.getString("ID_JENISHM") == null ? ""
						: rs.getString("ID_JENISHM"));
				h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
						: rs.getString("ID_JENISPB"));
				h.put("ID_NEGERI",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("ID_DAERAH",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("ID_MUKIM",
						rs.getString("ID_MUKIM") == null ? "" : rs
								.getString("ID_MUKIM"));

			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public List checkOBPermohonanNew(String id_permohonan, String user_id,String id_simati, Db db)
			throws Exception {
		Db db1 = null;
		String sql = "";
		List checkOBPermohonanNew = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			sql = " SELECT NVL(P.NO_SUBJAKET,'0') AS NO_SUBJAKET,"
					+ "NVL((SELECT MAX(NVL(P.NO_SUBJAKET,'0')) FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND SM.ID_SIMATI = '"+id_simati+"') "
					+ " ,'0') AS NO_SUBJAKET_MAX,"
					+ "TO_CHAR(OB.TARIKH_LAHIR,'DD/MM/YYYY') AS TARIKH_LAHIR,NVL(OB.UMUR,'0') AS UMUR,OB.NAMA_OB,OB.ID_OB,PSM.ID_PERMOHONANSIMATI,OB.ID_SIMATI "
					+ " FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN P   "
					+ " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND "
					+ " PSM.ID_SIMATI = '"+id_simati+"' AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN  AND X.ID_SIMATI = '"+id_simati+"' "
					+ " AND NVL(Y.NO_SUBJAKET,'0') <= (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";
			myLogger.info("CHECK BERAPA OB BY SIMATI :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			int tot = 0;
			checkOBPermohonanNew = Collections
					.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());				
				String temp_id_ob = rs.getString("ID_OB") == null ? "" : rs
						.getString("ID_OB");
				String temp_TARIKH_LAHIR = rs.getString("TARIKH_LAHIR") == null ? ""
						: rs.getString("TARIKH_LAHIR");
				int temp_UMUR = rs.getInt("UMUR") == 0 ? 0 : rs
						.getInt("UMUR");
				int temp_NO_SUBJAKET = rs.getInt("NO_SUBJAKET") == 0 ? 0 : rs
						.getInt("NO_SUBJAKET");
				int temp_NO_SUBJAKET_MAX = rs.getInt("NO_SUBJAKET_MAX") == 0 ? 0
						: rs.getInt("NO_SUBJAKET_MAX");
				
				h.put("temp_id_ob",temp_id_ob);
				h.put("temp_TARIKH_LAHIR",temp_TARIKH_LAHIR);
				h.put("temp_UMUR",temp_UMUR);
				h.put("temp_NO_SUBJAKET",temp_NO_SUBJAKET);
				h.put("temp_NO_SUBJAKET_MAX",temp_NO_SUBJAKET_MAX);
				/*
				 * if(recordCount(temp_id_ob,id_permohonan,db1) == 0) {
				 * updateOBPermohonan(id_permohonan,temp_id_ob,user_id,db1); }
				 */
				checkOBPermohonanNew.add(h);
			}
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
		return checkOBPermohonanNew;
	}

	public void checkOBPermohonan(String id_permohonan, String user_id, Db db)
			throws Exception {
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();

			sql1 = " SELECT NVL(P.NO_SUBJAKET,'0') AS NO_SUBJAKET,"
					+ "NVL((SELECT MAX(NVL(P.NO_SUBJAKET,'0')) FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND SM.ID_SIMATI IN ( "
					+ " SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "')) "
					+ " ,'0') AS NO_SUBJAKET_MAX,"
					+ "TO_CHAR(OB.TARIKH_LAHIR,'DD/MM/YYYY') AS TARIKH_LAHIR,NVL(OB.UMUR,'0') AS UMUR,OB.NAMA_OB,OB.ID_OB,PSM.ID_PERMOHONANSIMATI,OB.ID_SIMATI "
					+ " FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN P   "
					+ " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND "
					+ " PSM.ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "') AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN  "
					+ " AND NVL(Y.NO_SUBJAKET,'0') <= (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";
			// myLogger.info("CHECK BERAPA OB BY SIMATI :"+sql1);

			ResultSet rs1 = stmt.executeQuery(sql1);
			Hashtable h1;
			h1 = new Hashtable();
			int tot = 0;

			while (rs1.next()) {
				tot++;
				// myLogger.info("###BERAPA KALI BUAT:"+tot);
				String temp_id_ob = rs1.getString("ID_OB") == null ? "" : rs1
						.getString("ID_OB");
				String temp_TARIKH_LAHIR = rs1.getString("TARIKH_LAHIR") == null ? ""
						: rs1.getString("TARIKH_LAHIR");
				int temp_UMUR = rs1.getInt("UMUR") == 0 ? 0 : rs1
						.getInt("UMUR");
				int temp_NO_SUBJAKET = rs1.getInt("NO_SUBJAKET") == 0 ? 0 : rs1
						.getInt("NO_SUBJAKET");
				int temp_NO_SUBJAKET_MAX = rs1.getInt("NO_SUBJAKET_MAX") == 0 ? 0
						: rs1.getInt("NO_SUBJAKET_MAX");
				// myLogger.info("###temp_id_ob :"+temp_id_ob);
				// myLogger.info("###id_permohonan :"+id_permohonan);

				// myLogger.info("###temp_TARIKH_LAHIR :"+temp_TARIKH_LAHIR);
				// myLogger.info("###temp_UMUR :"+temp_UMUR);
				// myLogger.info("###temp_NO_SUBJAKET :"+temp_NO_SUBJAKET);
				// myLogger.info("###temp_NO_SUBJAKET_MAX :"+temp_NO_SUBJAKET_MAX);

				// myLogger.info("###recordCount(temp_id_ob,temp_id_permohonansimati):"+recordCount(temp_id_ob,id_permohonan));
				if (recordCount(temp_id_ob, id_permohonan, db1) == 0) {
					updateOBPermohonan(id_permohonan, temp_id_ob, user_id, db1);
				}

				/*
				 * if(!temp_TARIKH_LAHIR.equals("")) { if(temp_NO_SUBJAKET ==
				 * temp_NO_SUBJAKET_MAX) { SimpleDateFormat dateFormat = new
				 * SimpleDateFormat("dd/MM/yyyy"); Date convertedDate =
				 * dateFormat.parse(temp_TARIKH_LAHIR); int current_age =
				 * getAge(convertedDate);
				 * //myLogger.info("########### ID_OB :"+temp_id_ob
				 * +"CURRENT UMUR :"+current_age); if(current_age != temp_UMUR)
				 * { //myLogger.info("UPDATE UMUR"); } } }
				 */

			}

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public void checkHTAPermohonan(String id_permohonan, String user_id, Db db)
			throws Exception {
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();

			sql1 = " SELECT NVL(P.NO_SUBJAKET,'0') AS NO_SUBJAKET,"
					+ "NVL((SELECT MAX(NVL(P.NO_SUBJAKET,'0')) FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND SM.ID_SIMATI IN ( "
					+ " SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "')) "
					+ " ,'0') AS NO_SUBJAKET_MAX,"
					+ " OB.ID_HTA,PSM.ID_PERMOHONANSIMATI,OB.ID_SIMATI "
					+ " FROM TBLPPKHTA OB,TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN P   "
					+ " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND "
					+ " PSM.ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "') AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN  "
					+ " AND NVL(Y.NO_SUBJAKET,'0') <= (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";
			// myLogger.info("CHECK BERAPA HTA BY SIMATI :"+sql1);

			ResultSet rs1 = stmt.executeQuery(sql1);
			Hashtable h1;
			h1 = new Hashtable();
			int tot = 0;

			while (rs1.next()) {
				tot++;
				// myLogger.info("###BERAPA KALI BUAT:"+tot);
				String temp_id_hta = rs1.getString("ID_HTA") == null ? "" : rs1
						.getString("ID_HTA");
				int temp_NO_SUBJAKET = rs1.getInt("NO_SUBJAKET") == 0 ? 0 : rs1
						.getInt("NO_SUBJAKET");
				int temp_NO_SUBJAKET_MAX = rs1.getInt("NO_SUBJAKET_MAX") == 0 ? 0
						: rs1.getInt("NO_SUBJAKET_MAX");
				// myLogger.info("###temp_id_hta :"+temp_id_hta);
				if (recordCountHTA(temp_id_hta, id_permohonan, db1) == 0) {
					updateHTAPermohonan(id_permohonan, temp_id_hta, user_id,
							db1);
				}

			}

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	
	public List checkHTAPermohonanNew(String id_permohonan, String user_id,String id_simati, Db db)
			throws Exception {
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		List checkHTAPermohonanNew = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();

			sql1 = " SELECT NVL(P.NO_SUBJAKET,'0') AS NO_SUBJAKET,"
					+ "NVL((SELECT MAX(NVL(P.NO_SUBJAKET,'0')) FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND SM.ID_SIMATI = '"+id_simati+"') "
					+ " ,'0') AS NO_SUBJAKET_MAX,"
					+ " OB.ID_HTA,PSM.ID_PERMOHONANSIMATI,OB.ID_SIMATI "
					+ " FROM TBLPPKHTA OB,TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN P   "
					+ " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND "
					+ " PSM.ID_SIMATI = '"+id_simati+"' AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN AND X.ID_SIMATI = '"+id_simati+"' "
					+ " AND NVL(Y.NO_SUBJAKET,'0') <= (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";
			myLogger.info("CHECK BERAPA HTA BY SIMATI :"+sql1);
			
			ResultSet rs1 = stmt.executeQuery(sql1);
			checkHTAPermohonanNew = Collections.synchronizedList(new ArrayList());
			Map h = null;
			
			while (rs1.next()) {
				h = Collections.synchronizedMap(new HashMap());
				String temp_id_HTA = rs1.getString("ID_HTA") == null ? "" : rs1
						.getString("ID_HTA");
				int temp_NO_SUBJAKET = rs1.getInt("NO_SUBJAKET") == 0 ? 0 : rs1
						.getInt("NO_SUBJAKET");
				int temp_NO_SUBJAKET_MAX = rs1.getInt("NO_SUBJAKET_MAX") == 0 ? 0
						: rs1.getInt("NO_SUBJAKET_MAX");
				// myLogger.info("###temp_id_HA :"+temp_id_HA);
				/*
				if (recordCountHA(temp_id_HA, id_permohonan, db1) == 0) {
					updateHAPermohonan(id_permohonan, temp_id_HA, user_id, db1);
				}
				*/
				h.put("temp_id_HTA",temp_id_HTA);
				h.put("temp_NO_SUBJAKET",temp_NO_SUBJAKET);
				h.put("temp_NO_SUBJAKET_MAX",temp_NO_SUBJAKET_MAX);
				
				checkHTAPermohonanNew.add(h);
			}

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
		return checkHTAPermohonanNew;
	}
	
	
	public List checkHAPermohonanNew(String id_permohonan, String user_id, String id_simati, Db db)
			throws Exception {
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		List checkHAPermohonanNew = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();

			sql1 = " SELECT NVL(P.NO_SUBJAKET,'0') AS NO_SUBJAKET,"
					+ "NVL((SELECT MAX(NVL(P.NO_SUBJAKET,'0')) FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND SM.ID_SIMATI = '"+id_simati+"' ) "
					+ " ,'0') AS NO_SUBJAKET_MAX,"
					+ " OB.ID_HA,PSM.ID_PERMOHONANSIMATI,OB.ID_SIMATI "
					+ " FROM TBLPPKHA OB,TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN P   "
					+ " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND "
					+ " PSM.ID_SIMATI = '"+id_simati+"' AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN AND X.ID_SIMATI = '"+id_simati+"'  "
					+ " AND NVL(Y.NO_SUBJAKET,'0') <= (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";
			myLogger.info("CHECK BERAPA HA BY SIMATI NEW :"+sql1);

			ResultSet rs1 = stmt.executeQuery(sql1);
			checkHAPermohonanNew = Collections.synchronizedList(new ArrayList());
			Map h = null;
			
			while (rs1.next()) {
				h = Collections.synchronizedMap(new HashMap());
				String temp_id_HA = rs1.getString("ID_HA") == null ? "" : rs1
						.getString("ID_HA");
				int temp_NO_SUBJAKET = rs1.getInt("NO_SUBJAKET") == 0 ? 0 : rs1
						.getInt("NO_SUBJAKET");
				int temp_NO_SUBJAKET_MAX = rs1.getInt("NO_SUBJAKET_MAX") == 0 ? 0
						: rs1.getInt("NO_SUBJAKET_MAX");
				// myLogger.info("###temp_id_HA :"+temp_id_HA);
				/*
				if (recordCountHA(temp_id_HA, id_permohonan, db1) == 0) {
					updateHAPermohonan(id_permohonan, temp_id_HA, user_id, db1);
				}
				*/
				h.put("temp_id_HA",temp_id_HA);
				h.put("temp_NO_SUBJAKET",temp_NO_SUBJAKET);
				h.put("temp_NO_SUBJAKET_MAX",temp_NO_SUBJAKET_MAX);
				
				checkHAPermohonanNew.add(h);
			}

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
		return checkHAPermohonanNew;
	}


	public void checkHAPermohonan(String id_permohonan, String user_id, Db db)
			throws Exception {
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();

			sql1 = " SELECT NVL(P.NO_SUBJAKET,'0') AS NO_SUBJAKET,"
					+ "NVL((SELECT MAX(NVL(P.NO_SUBJAKET,'0')) FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P "
					+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND SM.ID_SIMATI IN ( "
					+ " SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "')) "
					+ " ,'0') AS NO_SUBJAKET_MAX,"
					+ " OB.ID_HA,PSM.ID_PERMOHONANSIMATI,OB.ID_SIMATI "
					+ " FROM TBLPPKHA OB,TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN P   "
					+ " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND "
					+ " PSM.ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "') AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN  "
					+ " AND NVL(Y.NO_SUBJAKET,'0') <= (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";
			// myLogger.info("CHECK BERAPA HA BY SIMATI :"+sql1);

			ResultSet rs1 = stmt.executeQuery(sql1);
			Hashtable h1;
			h1 = new Hashtable();
			int tot = 0;

			while (rs1.next()) {
				tot++;
				// myLogger.info("###BERAPA KALI BUAT:"+tot);
				String temp_id_HA = rs1.getString("ID_HA") == null ? "" : rs1
						.getString("ID_HA");
				int temp_NO_SUBJAKET = rs1.getInt("NO_SUBJAKET") == 0 ? 0 : rs1
						.getInt("NO_SUBJAKET");
				int temp_NO_SUBJAKET_MAX = rs1.getInt("NO_SUBJAKET_MAX") == 0 ? 0
						: rs1.getInt("NO_SUBJAKET_MAX");
				// myLogger.info("###temp_id_HA :"+temp_id_HA);
				if (recordCountHA(temp_id_HA, id_permohonan, db1) == 0) {
					updateHAPermohonan(id_permohonan, temp_id_HA, user_id, db1);
				}

			}

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	
	public List checkOBHubunganPermohonanNew(String id_permohonan, String user_id, String id_simati, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		List checkOBHubunganPermohonanNew = null;
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();

			sql1 = " SELECT OB.NAMA_OB,OB.ID_OB,PSM.ID_PERMOHONANSIMATI,OB.ID_SIMATI "
					+ " FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI PSM,TBLPPKHUBUNGANOB HUB   "
					+ " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND HUB.ID_OB = OB.ID_OB AND "
					+ " PSM.ID_SIMATI = '"+id_simati+"' AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN AND X.ID_SIMATI = '"+id_simati+"' "
					+ " AND NVL(Y.NO_SUBJAKET,'0') <= (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";
			myLogger.info("NEW HB###CHECK BERAPA OB HUBUNGAN BY SIMATI :"+sql1);

			ResultSet rs1 = stmt.executeQuery(sql1);
			checkOBHubunganPermohonanNew = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs1.next()) {
				h = Collections.synchronizedMap(new HashMap());
				String temp_id_ob = rs1.getString("ID_OB") == null ? "" : rs1
						.getString("ID_OB");
				h.put("temp_id_ob",temp_id_ob);
				/*
				if (recordCountHubungan(temp_id_ob, id_permohonan, db1) == 0) {
					updateOBHubunganPermohonan(id_permohonan, temp_id_ob,
							user_id, db1);
				}
				*/
				checkOBHubunganPermohonanNew.add(h);
			}
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
		return checkOBHubunganPermohonanNew;
	}

	public void checkOBHubunganPermohonan(String id_permohonan, String user_id,
			Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();

			sql1 = " SELECT OB.NAMA_OB,OB.ID_OB,PSM.ID_PERMOHONANSIMATI,OB.ID_SIMATI "
					+ " FROM TBLPPKOB OB,TBLPPKPERMOHONANSIMATI PSM,TBLPPKHUBUNGANOB HUB   "
					+ " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND HUB.ID_OB = OB.ID_OB AND "
					+ " PSM.ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "') AND "
					+ " PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN  "
					+ " FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN  "
					+ " AND NVL(Y.NO_SUBJAKET,'0') <= (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "'))  ";
			// myLogger.info("HB###CHECK BERAPA OB HUBUNGAN BY SIMATI :"+sql1);

			ResultSet rs1 = stmt.executeQuery(sql1);
			Hashtable h1;
			h1 = new Hashtable();
			int tot = 0;
			while (rs1.next()) {
				tot++;
				// myLogger.info("------------HB###BERAPA KALI BUAT:"+tot);
				String temp_id_ob = rs1.getString("ID_OB") == null ? "" : rs1
						.getString("ID_OB");
				// String temp_id_permohonansimati =
				// rs1.getString("ID_PERMOHONANSIMATI") == null ? "" :
				// rs1.getString("ID_PERMOHONANSIMATI");
				// myLogger.info("HB###temp_id_ob :"+temp_id_ob);
				// myLogger.info("HB###id_permohonan :"+id_permohonan);
				// myLogger.info("HB###recordCount:"+recordCountHubungan(temp_id_ob,id_permohonan));
				if (recordCountHubungan(temp_id_ob, id_permohonan, db1) == 0) {
					updateOBHubunganPermohonan(id_permohonan, temp_id_ob,
							user_id, db1);
				}
			}

		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public void updateOBPermohonan(String id_permohonan, String temp_id_ob,
			String user_id, Db db) throws Exception {
		// Connection conn = null;
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		try {

			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			// conn = db1.getConnection();
			// conn.setAutoCommit(false);
			Statement stmt = db1.getStatement();

			/*
			 * sql1 =
			 * " SELECT OB.NAMA_OB,OB.ID_OB,OB.ID_PERMOHONANSIMATI FROM TBLPPKOBPERMOHONAN OB,TBLPPKPERMOHONANSIMATI PSM "
			 * +
			 * " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND  OB.ID_OB = '"
			 * +temp_id_ob+"' "+ " AND  PSM.ID_PERMOHONAN = "+ " ( "+
			 * " SELECT X.ID_PERMOHONAN  FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y "
			 * +
			 * " WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN  AND X.ID_SIMATI IN "+
			 * " (SELECT DISTINCT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
			 * +id_permohonan+"') "+ " AND NVL(Y.NO_SUBJAKET,'0') = "+
			 * " (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE  NVL(NO_SUBJAKET,'0') > 0 AND ID_PERMOHONAN = '"
			 * +id_permohonan+"')-1 "+ " )  "+ "";
			 * //myLogger.info("CHECK SEBELUM ADD OB PERMOHONAN :"+sql1);
			 * ResultSet rs1 = stmt.executeQuery(sql1);
			 * 
			 * String NAMA_OB = ""; String ID_OB = ""; String
			 * ID_PERMOHONANSIMATI = ""; int tot = 0; while (rs1.next()) {
			 * tot++; NAMA_OB = rs1.getString("NAMA_OB") == null ? "" :
			 * rs1.getString("NAMA_OB"); ID_OB = rs1.getString("ID_OB") == null
			 * ? "" : rs1.getString("ID_OB"); ID_PERMOHONANSIMATI =
			 * rs1.getString("ID_PERMOHONANSIMATI") == null ? "" :
			 * rs1.getString("ID_PERMOHONANSIMATI");
			 * //myLogger.info("CHECK NAMA_OB :"+NAMA_OB);
			 * //myLogger.info("CHECK ID_OB :"+ID_OB);
			 * //myLogger.info("CHECK ID_PERMOHONANSIMATI :"
			 * +ID_PERMOHONANSIMATI); }
			 * //myLogger.info("ADA KA? CHECK SEBELUM ADD OB PERMOHONAN :"+tot);
			 */
			sql = " "
					+ " INSERT INTO TBLPPKOBPERMOHONAN ( "
					+ " ID_PERMOHONANSIMATI, ID_OB,ID_SIMATI, NAMA_OB, NO_KP_BARU, "
					+ " NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,NO_SURAT_BERANAK, TARIKH_LAHIR, JANTINA, "
					+ " UMUR, ALAMAT_1, ALAMAT_2,ALAMAT_3, BANDAR, ID_BANDAR, "
					+ " POSKOD, NO_HP, NO_TEL,CATATAN, STATUS_HIDUP, ID_TARAFKPTG, "
					+ " ID_NEGERI, ID_SAUDARA, ID_JENISPB,JENIS_AGAMA, JENIS_WARGA, ID_BANK, "
					+ " NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN,JENIS_WAKTU_KEMATIAN, STATUS_OB, NILAI_HUTANG, "
					+ " BA_FARAID, BB_FARAID, LAPIS,BUTIRAN_HUTANG, JENIS_PEMIUTANG, ID_PEMOHON, "
					+ " ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT,ID_BANDARSURAT, POSKOD_SURAT, NO_HP_SURAT,  "
					+ " NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK,ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB,  "
					+ " BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU,ID_OBLAMA, ID_ARB, NO_FAX,FLAG_DAFTAR) "
					+ "";

			/*
			 * if(tot==0) {
			 */
			// myLogger.info("GET 1");
			sql += ""
					+ " SELECT  (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "'), "
					+ " T.ID_OB,  "
					+ " T.ID_SIMATI, T.NAMA_OB, T.NO_KP_BARU, T.NO_KP_LAMA, T.JENIS_KP, T.NO_KP_LAIN,  "
					+ " T.NO_SURAT_BERANAK, T.TARIKH_LAHIR, T.JANTINA,  "
					+ " T.UMUR, T.ALAMAT_1, T.ALAMAT_2, T.ALAMAT_3, T.BANDAR, T.ID_BANDAR,  "
					+ " T.POSKOD, T.NO_HP, T.NO_TEL, T.CATATAN, T.STATUS_HIDUP, T.ID_TARAFKPTG,  "
					+ " T.ID_NEGERI, T.ID_SAUDARA, T.ID_JENISPB, T.JENIS_AGAMA, T.JENIS_WARGA, T.ID_BANK,  "
					+ " T.NO_AKAUN, T.TARIKH_MATI, T.WAKTU_KEMATIAN,T.JENIS_WAKTU_KEMATIAN, T.STATUS_OB, T.NILAI_HUTANG,  "
					+ " T.BA_FARAID, T.BB_FARAID, T.LAPIS,T.BUTIRAN_HUTANG, T.JENIS_PEMIUTANG, T.ID_PEMOHON,  "
					+ " T.ALAMAT1_SURAT, T.ALAMAT2_SURAT, T.ALAMAT3_SURAT, T.ID_BANDARSURAT, T.POSKOD_SURAT, T.NO_HP_SURAT, "
					+ " T.NO_TEL_SURAT, '"
					+ user_id
					+ "' ,sysdate, '"
					+ user_id
					+ "', sysdate, T.ID_DB,  "
					+ " T.BANDAR_SURAT, T.ID_NEGERISURAT, T.ID_PERAYU,T.ID_OBLAMA, T.ID_ARB, T.NO_FAX, T.FLAG_DAFTAR "
					+ " FROM TBLPPKOB T WHERE T.ID_OB = '" + temp_id_ob + "' ";
			/*
			 * } else { //myLogger.info("GET 2"); sql += " "+
			 * " SELECT  (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
			 * +id_permohonan+"'), " + " T.ID_OB,  "+
			 * " T.ID_SIMATI, T.NAMA_OB, T.NO_KP_BARU, T.NO_KP_LAMA, T.JENIS_KP, T.NO_KP_LAIN,  "
			 * + " T.NO_SURAT_BERANAK, T.TARIKH_LAHIR, T.JANTINA,  "+
			 * " T.UMUR, T.ALAMAT_1, T.ALAMAT_2, T.ALAMAT_3, T.BANDAR, T.ID_BANDAR,  "
			 * +
			 * " T.POSKOD, T.NO_HP, T.NO_TEL, T.CATATAN, T.STATUS_HIDUP, T.ID_TARAFKPTG,  "
			 * +
			 * " T.ID_NEGERI, T.ID_SAUDARA, T.ID_JENISPB, T.JENIS_AGAMA, T.JENIS_WARGA, T.ID_BANK,  "
			 * +
			 * " T.NO_AKAUN, T.TARIKH_MATI, T.WAKTU_KEMATIAN,T.JENIS_WAKTU_KEMATIAN, T.STATUS_OB, T.NILAI_HUTANG,  "
			 * +
			 * " T.BA_FARAID, T.BB_FARAID, T.LAPIS,T.BUTIRAN_HUTANG, T.JENIS_PEMIUTANG, T.ID_PEMOHON,  "
			 * +
			 * " T.ALAMAT1_SURAT, T.ALAMAT2_SURAT, T.ALAMAT3_SURAT, T.ID_BANDARSURAT, T.POSKOD_SURAT, T.NO_HP_SURAT, "
			 * + " T.NO_TEL_SURAT, '"+user_id+"' ,sysdate, '"+user_id+
			 * "', sysdate, T.ID_DB,  "+
			 * " T.BANDAR_SURAT, T.ID_NEGERISURAT, T.ID_PERAYU,T.ID_OBLAMA, T.ID_ARB, T.NO_FAX "
			 * + " FROM TBLPPKOBPERMOHONAN T WHERE T.ID_OB = '"+ID_OB+
			 * "' AND T.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "; }
			 */
			myLogger.info("script insert from table(TBLPPKOB) to table(TBLPPKOBPERMOHONAN):"+sql);
			stmt.executeUpdate(sql);

			// conn.commit();
		} finally {
			// if (conn != null) conn.close();
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public void updateHTAPermohonan(String id_permohonan, String temp_id_hta,
			String user_id, Db db) throws Exception {
		// Connection conn = null;
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			// conn = db1.getConnection();
			// conn.setAutoCommit(false);
			Statement stmt = db1.getStatement();

			sql = " "
					+ " INSERT INTO TBLPPKHTAPERMOHONAN ( "
					+ " ID_PERMOHONANSIMATI,ID_HTA,NO_HAKMILIK,ID_SIMATI,NO_PT,NILAI_HTA_TARIKHMOHON,NILAI_HTA_TARIKHMATI,ID_KATEGORI,ID_JENISHM,"
					+ " ID_JENISPB,ID_NEGERI,ID_DAERAH,ID_MUKIM,ID_LUAS,LUAS,LUAS_HMP,NO_CAGARAN,NO_PAJAKAN,JENIS_TNH,ALAMAT_HTA1,ALAMAT_HTA2,"
					+ " ALAMAT_HTA3,BANDAR_HTA,POSKOD_HTA,TARIKH_PERJANJIAN,NAMA_PEMAJU,ALAMAT_PEMAJU1,ALAMAT_PEMAJU2,ALAMAT_PEMAJU3,"
					+ " BANDAR_PEMAJU,POSKOD_PEMAJU,ID_NEGERIPEMAJU,CATATAN,BA_SIMATI,BB_SIMATI,NO_BANGUNAN,NO_TINGKAT,"
					+ " NO_PETAK,NO_STRATA,MAKLUMAT_TANAH,NO_PERJANJIAN,JENIS_HTA,STATUS_PEMILIKAN,TANGGUNGAN,NO_PERSERAHAN,NAMA_RANCANGAN,"
					+ " NO_ROH,NO_LOT_ID,FLAG_KATEGORI_HTA,JENIS_KEPENTINGAN,ID_MASUK,TARIKH_MASUK,ID_KEMASKINI,TARIKH_KEMASKINI,"
					+ " ID_DB,ID_BANDARPEMAJU,ID_BANDARHTA,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,ID_HAKMILIK) "
					+ "";
			sql += ""
					+ " SELECT  (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "'), "
					+ " ID_HTA,NO_HAKMILIK,ID_SIMATI,NO_PT,NILAI_HTA_TARIKHMOHON,NILAI_HTA_TARIKHMATI,ID_KATEGORI,ID_JENISHM,"
					+ " ID_JENISPB,ID_NEGERI,ID_DAERAH,ID_MUKIM,ID_LUAS,LUAS,LUAS_HMP,NO_CAGARAN,NO_PAJAKAN,JENIS_TNH,ALAMAT_HTA1,ALAMAT_HTA2,"
					+ " ALAMAT_HTA3,BANDAR_HTA,POSKOD_HTA,TARIKH_PERJANJIAN,NAMA_PEMAJU,ALAMAT_PEMAJU1,ALAMAT_PEMAJU2,ALAMAT_PEMAJU3,"
					+ " BANDAR_PEMAJU,POSKOD_PEMAJU,ID_NEGERIPEMAJU,CATATAN,BA_SIMATI,BB_SIMATI,NO_BANGUNAN,NO_TINGKAT,"
					+ " NO_PETAK,NO_STRATA,MAKLUMAT_TANAH,NO_PERJANJIAN,JENIS_HTA,STATUS_PEMILIKAN,TANGGUNGAN,NO_PERSERAHAN,NAMA_RANCANGAN,"
					+ " NO_ROH,NO_LOT_ID,FLAG_KATEGORI_HTA,JENIS_KEPENTINGAN,'"
					+ user_id
					+ "',sysdate,'"
					+ user_id
					+ "',sysdate,"
					+ " ID_DB,ID_BANDARPEMAJU,ID_BANDARHTA,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,ID_HAKMILIK "
					+ " FROM TBLPPKHTA T WHERE T.ID_HTA = '" + temp_id_hta
					+ "' ";

			// myLogger.info("script insert from table(TBLPPKHTA) to table(TBLPPKHTAPERMOHONAN):"+sql);
			stmt.executeUpdate(sql);

			// conn.commit();
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}

		}
	}

	public void updateHAPermohonan(String id_permohonan, String temp_id_ha,
			String user_id, Db db) throws Exception {
		// Connection conn = null;
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			// conn = db1.getConnection();
			// conn.setAutoCommit(false);
			Statement stmt = db1.getStatement();

			sql = " "
					+ " INSERT INTO TBLPPKHAPERMOHONAN ( "
					+ " ID_PERMOHONANSIMATI,"
					+ " ID_HA,BIL,ID_SIMATI,ID_JENISHA,ID_NEGERI,ID_DAERAH,JENAMA,NO_DAFTAR, "
					+ " NO_SIJIL,BIL_UNIT,TARIKH_HARTA,ALAMAT_HA1,ALAMAT_HA2,ALAMAT_HA3,POSKOD,NILAI_HA_TARIKHMOHON,NILAI_HA_TARIKHMATI, "
					+ " BA_SIMATI,BB_SIMATI,CATATAN,NILAI_SEUNIT,ID_MASUK, TARIKH_MASUK,ID_KEMASKINI,TARIKH_KEMASKINI, "
					+ " ID_DB,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,NAMA_SAHAM,BUTIRAN,ID_HA_LAMA ) "
					+ "";
			sql += " SELECT (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "'),"
					+ " ID_HA,BIL,ID_SIMATI,ID_JENISHA,ID_NEGERI,ID_DAERAH,JENAMA,NO_DAFTAR,"
					+ " NO_SIJIL,BIL_UNIT,TARIKH_HARTA,ALAMAT_HA1,ALAMAT_HA2,ALAMAT_HA3,POSKOD,NILAI_HA_TARIKHMOHON,NILAI_HA_TARIKHMATI, "
					+ " BA_SIMATI,BB_SIMATI,CATATAN,NILAI_SEUNIT,'"
					+ user_id
					+ "', sysdate,'"
					+ user_id
					+ "',sysdate, "
					+ " ID_DB,FLAG_PA,FLAG_PT,FLAG_SELESAI,ID_PERINTAHOBMST,NAMA_SAHAM,BUTIRAN,ID_HA_LAMA "
					+ " FROM TBLPPKHA WHERE ID_HA = '" + temp_id_ha + "'";

			// myLogger.info("script insert from table(TBLPPKHA) to table(TBLPPKHAPERMOHONAN):"+sql);
			stmt.executeUpdate(sql);

			// conn.commit();
		} finally {
			// if (conn != null) conn.close();
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public void updateOBHubunganPermohonan(String id_permohonan, String id_ob,
			String user_id, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String sql1 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();

			sql1 = " SELECT OB.ID_OB,OB.ID_PERMOHONANSIMATI "
					+ " FROM TBLPPKHUBUNGANOBPERMOHONAN OB,TBLPPKPERMOHONANSIMATI PSM "
					+ " WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI AND  OB.ID_OB = '"
					+ id_ob
					+ "' "
					+ " AND  PSM.ID_PERMOHONAN = "
					+ " ( "
					+ " SELECT X.ID_PERMOHONAN  FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y "
					+ " WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN  AND X.ID_SIMATI IN "
					+ " (SELECT DISTINCT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "') "
					+ " AND NVL(Y.NO_SUBJAKET,'0') = "
					+ " (SELECT NVL(NO_SUBJAKET,'0') FROM TBLPPKPERMOHONAN WHERE  NVL(NO_SUBJAKET,'0') > 0 AND ID_PERMOHONAN = '"
					+ id_permohonan + "')-1 " + " )  " + "";
		    myLogger.info("CHECK SEBELUM ADD OB PERMOHONAN :"+sql1);
			ResultSet rs1 = stmt.executeQuery(sql1);

			String ID_OB = "";
			String ID_PERMOHONANSIMATI = "";
			int tot = 0;
			while (rs1.next()) {
				tot++;
				ID_OB = rs1.getString("ID_OB") == null ? "" : rs1
						.getString("ID_OB");
				ID_PERMOHONANSIMATI = rs1.getString("ID_PERMOHONANSIMATI") == null ? ""
						: rs1.getString("ID_PERMOHONANSIMATI");
				// myLogger.info("CHECK ID_OB :"+ID_OB);
				// myLogger.info("CHECK ID_PERMOHONANSIMATI :"+ID_PERMOHONANSIMATI);
			}
			// myLogger.info("ADA KA? CHECK SEBELUM ADD OB HUBUNGAN PERMOHONAN :"+tot);

			sql1 = "INSERT INTO TBLPPKHUBUNGANOBPERMOHONAN  "
					+ " (ID_HUBUNGANOB,ID_PERMOHONANSIMATI,ID_OB,ID_PARENTOB,ID_SAUDARA,  ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,  TARIKH_KEMASKINI, ID_DB) ";
			/*
			 * if(tot==0) {
			 */
			sql1 += "SELECT HOB.ID_HUBUNGANOB,(SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan
					+ "'),"
					+ " HOB.ID_OB,HOB.ID_PARENTOB,HOB.ID_SAUDARA,'"
					+ user_id
					+ "',sysdate,'"
					+ user_id
					+ "',sysdate,HOB.ID_DB "
					+ " FROM TBLPPKHUBUNGANOB HOB, TBLPPKOB OB  WHERE HOB.ID_OB = OB.ID_OB AND HOB.ID_OB = '"
					+ id_ob + "'";
			/*
			 * } else { sql1 +=
			 * " SELECT HOB.ID_HUBUNGANOB,(SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
			 * +id_permohonan+"'),"+
			 * " HOB.ID_OB,HOB.ID_PARENTOB,HOB.ID_SAUDARA,'"
			 * +user_id+"',sysdate,'"+user_id+"',sysdate,HOB.ID_DB "+
			 * " FROM TBLPPKHUBUNGANOBPERMOHONAN HOB WHERE  HOB.ID_OB = '"
			 * +ID_OB+
			 * "' AND HOB.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"'"; }
			 */
			myLogger.info("HB### INSERT HUBUNGAN OB BARU"+sql1);
			stmt.executeUpdate(sql1);
			
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public int recordCountHTA(String temp_hta, String id_permohonan, Db db)
			throws Exception {
		Db db1 = null;
		String sql2 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			// Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = " SELECT HTAP.ID_HTAPERMOHONAN,HTAP.ID_HTA,HTAP.ID_PERMOHONANSIMATI FROM TBLPPKHTAPERMOHONAN HTAP "
					+ " WHERE HTAP.ID_HTA = '"
					+ temp_hta
					+ "' "
					+ "AND HTAP.ID_PERMOHONANSIMATI = "
					+ " (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "') ";
			// myLogger.info("CHECK OB DALAM TBLOBPERMOHONAN :"+sql2);
			Statement stmt2 = db1.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);

			int recordCount = 0;
			while (rs2.next()) {
				recordCount++;
			}
			return recordCount;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public int recordCountHA(String temp_hta, String id_permohonan, Db db)
			throws Exception {
		Db db1 = null;
		String sql2 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			// Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = " SELECT HTAP.ID_HAPERMOHONAN,HTAP.ID_HA,"
					+ " HTAP.ID_PERMOHONANSIMATI FROM TBLPPKHAPERMOHONAN HTAP "
					+ " WHERE HTAP.ID_HA = '" + temp_hta + "' "
					+ "AND HTAP.ID_PERMOHONANSIMATI = "
					+ " (SELECT ID_PERMOHONANSIMATI "
					+ " FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "') ";
			// myLogger.info("CHECK HA DALAM TBLHAPERMOHONAN :"+sql2);
			Statement stmt2 = db1.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);

			int recordCount = 0;
			while (rs2.next()) {
				recordCount++;
			}
			return recordCount;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public int recordCount(String temp_id_ob, String id_permohonan, Db db)
			throws Exception {
		Db db1 = null;
		String sql2 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			// Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = " SELECT OP.ID_OBPERMOHONAN,OP.NAMA_OB,OP.ID_OB,OP.ID_PERMOHONANSIMATI FROM TBLPPKOBPERMOHONAN OP "
					+ " WHERE OP.ID_OB = '"
					+ temp_id_ob
					+ "' "
					+ "AND OP.ID_PERMOHONANSIMATI = "
					+ " (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "') ";
			myLogger.info("CHECK OB DALAM TBLOBPERMOHONAN :"+sql2);
			Statement stmt2 = db1.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);

			int recordCount = 0;
			while (rs2.next()) {
				recordCount++;
			}
			return recordCount;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public int recordCountHubungan(String temp_id_ob, String id_permohonan,
			Db db) throws Exception {
		Db db1 = null;
		String sql2 = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			// Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = " SELECT HUB.ID_HUBUNGANOBPERMOHONAN FROM TBLPPKHUBUNGANOBPERMOHONAN HUB "
					+ " WHERE HUB.ID_OB = '"
					+ temp_id_ob
					+ "' "
					+ "AND HUB.ID_PERMOHONANSIMATI = "
					+ " (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"
					+ id_permohonan + "') ";
			// myLogger.info("HB###CHECK HUBUNGANOB DALAM TBLOBPERMOHONAN :"+sql2);
			Statement stmt2 = db1.getStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);

			int recordCount = 0;
			while (rs2.next()) {
				recordCount++;
			}
			return recordCount;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	/*
	 * public int recordCountHubungan(String temp_id_HUBUNGANOB,String
	 * ID_PERMOHONANSIMATI) throws Exception { Db db = null; String sql2 = "";
	 * try { db = new Db(); Statement stmt = db.getStatement(); SQLRenderer r =
	 * new SQLRenderer(); sql2 =
	 * " SELECT * FROM TBLPPKHUBUNGANOBPERMOHONAN HUP, TBLPPKPERMOHONANSIMATI SM "
	 * +
	 * " WHERE HUP.ID_PERMOHONANSIMATI = SM.ID_PERMOHONANSIMATI AND  HUP.ID_PERMOHONANSIMATI = '"
	 * +ID_PERMOHONANSIMATI+"' " +
	 * " AND HUP.ID_HUBUNGANOB = '"+temp_id_HUBUNGANOB+"'";
	 * //myLogger.info("CHECK HUBUNGAN OB DALAM TBLOBPERMOHONAN :"+sql2);
	 * Statement stmt2 = db.getStatement(); ResultSet rs2 =
	 * stmt2.executeQuery(sql2);
	 * 
	 * int recordCount = 0; while (rs2.next()) { recordCount++; } return
	 * recordCount; } finally { if (db != null) db.close(); } }
	 */
	/*
	 * public int recordCountBasedOBPermohonan(String temp_id_ob,String
	 * id_permohonan) throws Exception { Db db = null; String sql2 = ""; try {
	 * db = new Db(); Statement stmt = db.getStatement(); SQLRenderer r = new
	 * SQLRenderer(); sql2 =
	 * " SELECT OP.ID_OBPERMOHONAN,OP.NAMA_OB,OP.ID_OB,OP.ID_PERMOHONANSIMATI FROM TBLPPKOBPERMOHONAN OP "
	 * + " WHERE OP.ID_OB = '"+temp_id_ob+"' " +
	 * "AND OP.ID_PERMOHONANSIMATI = (SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI "
	 * + "WHERE ID_PERMOHONAN = '"+id_permohonan+"') ";
	 * //myLogger.info("CHECK OB DALAM TBLOBPERMOHONAN :"+sql2); Statement stmt2
	 * = db.getStatement(); ResultSet rs2 = stmt2.executeQuery(sql2);
	 * 
	 * int recordCount = 0; while (rs2.next()) { recordCount++; } return
	 * recordCount; } finally { if (db != null) db.close(); } }
	 */

	/*
	 * sql =
	 * "SELECT SUS.TARIKH_MASUK,SUS.ID_SUBURUSANSTATUSFAIL,F.NO_FAIL,S.KETERANGAN,P.ID_PERMOHONAN,S.ID_STATUS,PS.ID_PERMOHONANSIMATI,P.TARIKH_MOHON, "
	 * + " F.FLAG_JENIS_FAIL,P.SEKSYEN,SM.ID_SIMATI  "+
	 * " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI SM, "
	 * +
	 * " TBLRUJSUBURUSANSTATUSFAIL SUS,TBLRUJSUBURUSANSTATUS SS,TBLRUJSTATUS S WHERE "
	 * + " P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = SUS.ID_FAIL  "+
	 * " AND SUS.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS "+
	 * " AND SS.ID_STATUS = S.ID_STATUS AND PS.ID_PERMOHONAN = P.ID_PERMOHONAN "
	 * + " AND PS.ID_SIMATI = SM.ID_SIMATI ";
	 * 
	 * if(flag_permohonan.equals("Y")) { sql +=
	 * " AND P.ID_PERMOHONAN = '"+id_permohonan+"' "; }
	 * if(flag_fail.equals("Y")) { sql += " AND F.ID_FAIL = '"+id_fail+"' "; }
	 * sql += " ORDER BY  SUS.TARIKH_MASUK,SUS.ID_SUBURUSANSTATUSFAIL  ASC ";
	 */

	private Vector senaraiSubFail = null;

	public Vector<Hashtable<String, String>> carianFail(String id_permohonan,
			String flag_permohonan, String id_fail, String flag_fail)
			throws Exception {

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

			sql = "SELECT DISTINCT "
					+ " (CASE "
					+ " WHEN S.ID_STATUS IN ('8','9','170','169') THEN 'PENDAFTARAN' "
					+ " WHEN S.ID_STATUS IN ('50','53','151','152','14','70') THEN 'KEPUTUSAN PERMOHONAN' "
					+ " WHEN S.ID_STATUS IN ('18','44','175','173','177') THEN 'NOTIS PERBICARAAN' "
					+ " WHEN S.ID_STATUS IN ('47') THEN 'KEPUTUSAN PERBICARAAN (BATAL PERBICARAAN)' "
					+ " WHEN S.ID_STATUS IN ('172') THEN 'KEPUTUSAN PERBICARAAN (TANGGUH KOLATERAL)' "
					+ " WHEN S.ID_STATUS IN ('176') THEN 'KEPUTUSAN PERBICARAAN (TANGGUH ROTS)' "
					+ " WHEN S.ID_STATUS IN ('174') THEN 'KEPUTUSAN PERBICARAAN (TANGGUH MAHKAMAH TINGGI)' "
					+ " WHEN S.ID_STATUS IN ('44') THEN 'KEPUTUSAN PERBICARAAN (TANGGUH PERBICARAAN)' "
					+ " WHEN S.ID_STATUS IN ('41') THEN 'KEPUTUSAN PERBICARAAN (SELESAI PERBICARAAN)' "
					+ " WHEN S.ID_STATUS IN ('25','21') THEN 'PERINTAH PERBICARAAN' "
					+ " WHEN S.ID_STATUS IN ('64','163') THEN 'PERMOHONAN RAYUAN' "
					+ " WHEN S.ID_STATUS IN ('166','167','180','164','165') THEN 'KEPUTUSAN RAYUAN' "
					+ " WHEN S.ID_STATUS IN ('999') THEN 'FAIL DIHAPUSKAN' "
					+ " ELSE "
					+ " 'LAIN - LAIN' "
					+ " END) AS KETERANGAN, "
					+ " (CASE "
					+ " WHEN S.ID_STATUS IN ('8','9','170','169') THEN '8' "
					+ " WHEN S.ID_STATUS IN ('50','53','151','152','14','70') THEN '14' "
					+ " WHEN S.ID_STATUS IN ('18','44','175','173','177') THEN '18' "
					+ " WHEN S.ID_STATUS IN ('47') THEN '47' "
					+ " WHEN S.ID_STATUS IN ('172') THEN '172' "
					+ " WHEN S.ID_STATUS IN ('176') THEN '176' "
					+ " WHEN S.ID_STATUS IN ('174') THEN '174' "
					+ " WHEN S.ID_STATUS IN ('44') THEN '44' "
					+ " WHEN S.ID_STATUS IN ('41') THEN '41' "
					+ " WHEN S.ID_STATUS IN ('25','21') THEN '25' "
					+ " WHEN S.ID_STATUS IN ('64','163') THEN '64' "
					+ " WHEN S.ID_STATUS IN ('166','167','180','164','165') THEN '164' "
					+ " WHEN S.ID_STATUS IN ('999') THEN '999' "
					+ " ELSE "
					+ " '8' "
					+ " END) AS ID_STATUS, "
					+ " (CASE  "
					+ " WHEN S.ID_STATUS IN ('8','9','170','169') THEN 'A' "
					+ " WHEN S.ID_STATUS IN ('50','53','151','152','14','70') THEN 'B' "
					+ " WHEN S.ID_STATUS IN ('18','44','175','173','177') THEN 'C' "
					+ " WHEN S.ID_STATUS IN ('47') THEN 'D' "
					+ " WHEN S.ID_STATUS IN ('172') THEN 'E' "
					+ " WHEN S.ID_STATUS IN ('176') THEN 'F' "
					+ " WHEN S.ID_STATUS IN ('174') THEN 'G' "
					+ " WHEN S.ID_STATUS IN ('44') THEN 'H' "
					+ " WHEN S.ID_STATUS IN ('41') THEN 'I' "
					+ " WHEN S.ID_STATUS IN ('25','21') THEN 'J' "
					+ " WHEN S.ID_STATUS IN ('64','163') THEN 'K' "
					+ " WHEN S.ID_STATUS IN ('166','167','180','164','165') THEN 'L' "
					+ " WHEN S.ID_STATUS IN ('999') THEN 'M' "
					+ " ELSE "
					+ " 'N' "
					+ " END) AS ID_ORDER, "
					+ " F.NO_FAIL, "
					+ " P.ID_PERMOHONAN, "
					+ " PS.ID_PERMOHONANSIMATI,P.TARIKH_MOHON,   "
					+ " F.FLAG_JENIS_FAIL,P.SEKSYEN,SM.ID_SIMATI   "
					+ " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI SM,  "
					+ " TBLRUJSUBURUSANSTATUSFAIL SUS,TBLRUJSUBURUSANSTATUS SS,TBLRUJSTATUS S  "
					+ " WHERE  P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = SUS.ID_FAIL   "
					+ " AND SUS.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS   "
					+ " AND SS.ID_STATUS = S.ID_STATUS AND PS.ID_PERMOHONAN = P.ID_PERMOHONAN  "
					+ " AND PS.ID_SIMATI = SM.ID_SIMATI  ";

			if (flag_permohonan.equals("Y")) {
				sql += " AND P.ID_PERMOHONAN = '" + id_permohonan + "' ";
			}
			if (flag_fail.equals("Y")) {
				sql += " AND F.ID_FAIL = '" + id_fail + "' ";
			}

			sql += "ORDER BY  ID_ORDER  ASC ";
			// myLogger.info("SQL LIST SUBURUSAN edited:"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count++;
				h = new Hashtable();
				h.put("bil", count);
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("noFail",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("idStatus",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("idPermohonanSimati",
						rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs
								.getString("ID_PERMOHONANSIMATI"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("flagjenisfail",
						rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs
								.getString("FLAG_JENIS_FAIL"));
				h.put("seksyen",
						rs.getString("SEKSYEN") == null ? "" : rs
								.getString("SEKSYEN"));
				h.put("idSimati",
						rs.getString("ID_SIMATI") == null ? "" : rs
								.getString("ID_SIMATI"));
				senaraiSubFail.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiSubFail;
	}

	private Vector kenegaraan = null;

	public Vector kenegaraan() throws Exception {

		Db db = null;
		kenegaraan = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		try {
			
			//if(db==null){
				db = new Db();
			//}
			Statement stmt = db.getStatement();
			Hashtable h;
			int bil = 1;
			Integer count = 0;

			sql = "SELECT KOD_WARGA,NAMA_WARGA FROM TBLRUJKENEGARAAN "
					+ "ORDER BY NAMA_WARGA  ASC ";

			myLogger.info("SQL LIST WARGA :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count++;
				h = new Hashtable();
				h.put("KOD_WARGA",
						rs.getString("KOD_WARGA") == null ? "" : rs
								.getString("KOD_WARGA"));
				h.put("NAMA_WARGA", rs.getString("NAMA_WARGA") == null ? ""
						: rs.getString("NAMA_WARGA").toUpperCase());
				kenegaraan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return kenegaraan;
	}

	public Vector kenegaraanDb(Db db) throws Exception {

		// Db db = null;
		kenegaraan = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			Hashtable h;
			int bil = 1;
			Integer count = 0;

			sql = "SELECT KOD_WARGA,NAMA_WARGA FROM TBLRUJKENEGARAAN "
					+ "ORDER BY NAMA_WARGA  ASC ";

			//myLogger.info("SQL LIST WARGA :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count++;
				h = new Hashtable();
				h.put("KOD_WARGA",
						rs.getString("KOD_WARGA") == null ? "" : rs
								.getString("KOD_WARGA"));
				h.put("NAMA_WARGA", rs.getString("NAMA_WARGA") == null ? ""
						: rs.getString("NAMA_WARGA").toUpperCase());
				kenegaraan.addElement(h);
			}

		} finally {
			// if (db != null) db.close();
		}
		return kenegaraan;
	}

	public int getAge(Date dateOfBirth) {
		int age = 0;
		Calendar born = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		if (dateOfBirth != null) {
			now.setTime(now.getTime());
			born.setTime(dateOfBirth);
			if (born.after(now)) {
				throw new IllegalArgumentException(
						"Can't be born in the future");
			}
			age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
		}
		return age;
	}

	public Vector getListBayaranPerintah(String id_permohonan,
			String id_jenisbayaran) throws Exception {
		String key = "getListBayaranPerintah" + id_permohonan;

		Db db = null;
		String sql = "";
		Vector lists = null;
		try {
			db = new Db();
			lists = new Vector();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_BAYARAN,ID_PERMOHONAN,ID_JENISBAYARAN,NO_RESIT,TO_CHAR(TARIKH_BAYARAN,'DD/MM/YYYY') AS TARIKH_BAYARAN,"
					+ "TO_CHAR(JUMLAH_BAYARAN,'999,999,990.99') AS JUMLAH_BAYARAN FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 24 "
					+ "AND ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC";

			myLogger.info("SQL getListBayaranPerintah :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("ID_BAYARAN", rs.getString("ID_BAYARAN") == null ? ""
						: rs.getString("ID_BAYARAN"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_JENISBAYARAN",
						rs.getString("ID_JENISBAYARAN") == null ? "" : rs
								.getString("ID_JENISBAYARAN"));
				h.put("NO_RESIT",
						rs.getString("NO_RESIT") == null ? "" : rs
								.getString("NO_RESIT"));
				h.put("TARIKH_BAYARAN",
						rs.getString("TARIKH_BAYARAN") == null ? "" : rs
								.getString("TARIKH_BAYARAN"));
				h.put("JUMLAH_BAYARAN",
						rs.getString("JUMLAH_BAYARAN") == null ? "" : rs
								.getString("JUMLAH_BAYARAN"));

				lists.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();

			return lists;
		}
	}

	public Vector getListBayaranCukai(String id_permohonan,
			String id_jenisbayaran) throws Exception {
		String key = "getListBayaranCukai" + id_permohonan;

		Db db = null;
		String sql = "";
		Vector lists = null;
		try {
			db = new Db();
			lists = new Vector();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_BAYARAN,ID_PERMOHONAN,ID_JENISBAYARAN,NO_RESIT,TO_CHAR(TARIKH_BAYARAN,'DD/MM/YYYY') AS TARIKH_BAYARAN,"
					+ "TO_CHAR(JUMLAH_BAYARAN,'999,999,990.99') AS JUMLAH_BAYARAN FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 26 "
					+ "AND ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC";

			myLogger.info("SQL getListBayaranCukai :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("ID_BAYARAN", rs.getString("ID_BAYARAN") == null ? ""
						: rs.getString("ID_BAYARAN"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_JENISBAYARAN",
						rs.getString("ID_JENISBAYARAN") == null ? "" : rs
								.getString("ID_JENISBAYARAN"));
				h.put("NO_RESIT",
						rs.getString("NO_RESIT") == null ? "" : rs
								.getString("NO_RESIT"));
				h.put("TARIKH_BAYARAN",
						rs.getString("TARIKH_BAYARAN") == null ? "" : rs
								.getString("TARIKH_BAYARAN"));
				h.put("JUMLAH_BAYARAN",
						rs.getString("JUMLAH_BAYARAN") == null ? "" : rs
								.getString("JUMLAH_BAYARAN"));

				lists.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();

			return lists;
		}
	}

	public Vector getListBayaranBaitulmal(String id_permohonan,
			String id_jenisbayaran) throws Exception {
		String key = "getListBayaranCukai" + id_permohonan;

		Db db = null;
		String sql = "";
		Vector lists = null;
		try {
			db = new Db();
			lists = new Vector();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_BAYARAN,ID_PERMOHONAN,ID_JENISBAYARAN,NO_RESIT,TO_CHAR(TARIKH_BAYARAN,'DD/MM/YYYY') AS TARIKH_BAYARAN,"
					+ "TO_CHAR(JUMLAH_BAYARAN,'999,999,990.99') AS JUMLAH_BAYARAN FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 29 "
					+ "AND ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC";

			myLogger.info("SQL getListBayaranBaitulmal :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("ID_BAYARAN", rs.getString("ID_BAYARAN") == null ? ""
						: rs.getString("ID_BAYARAN"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_JENISBAYARAN",
						rs.getString("ID_JENISBAYARAN") == null ? "" : rs
								.getString("ID_JENISBAYARAN"));
				h.put("NO_RESIT",
						rs.getString("NO_RESIT") == null ? "" : rs
								.getString("NO_RESIT"));
				h.put("TARIKH_BAYARAN",
						rs.getString("TARIKH_BAYARAN") == null ? "" : rs
								.getString("TARIKH_BAYARAN"));
				h.put("JUMLAH_BAYARAN",
						rs.getString("JUMLAH_BAYARAN") == null ? "" : rs
								.getString("JUMLAH_BAYARAN"));

				lists.addElement(h);
				bil++;
			}
			// return list;
		} finally {
			if (db != null)
				db.close();
		}

		return lists;

	}

	public String getFlagKebenaran(String id_permohonan, String user_id, Db db)
			throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT A.ID_FAIL "
					+ " FROM TBLPPKPERMOHONAN A, TBLEDITAGIHAN B "
					+ " WHERE A.ID_FAIL = B.ID_FAIL "
					+ " AND A.FLAG_KEBENARAN_EDIT = 1 " + " AND B.USER_ID = '"
					+ user_id + "' " + " AND A.ID_PERMOHONAN = '"
					+ id_permohonan + "' ";

			//myLogger.info("CHECK STATUS UNIT LUAR :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			String countFileExist = "0";
			while (rs.next()) {
				countFileExist = "1";
			}
			//myLogger.info("countFileExist :" + countFileExist);
			return countFileExist;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}

	public String getIdFail(String id_permohonan, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT A.ID_FAIL " + " FROM TBLPPKPERMOHONAN A "
					+ " WHERE A.ID_PERMOHONAN = '" + id_permohonan + "' ";
			myLogger.info("CHECK STATUS UNIT LUAR :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			String id_fail = "";
			while (rs.next()) {
				id_fail = rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL");
			}
			myLogger.info("id_fail :" + id_fail);
			return id_fail;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	public Hashtable getIdFailNew(String id_permohonan, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		Hashtable h = new Hashtable();
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT A.ID_FAIL, B.ID_SIMATI " + " FROM TBLPPKPERMOHONAN A, TBLPPKPERMOHONANSIMATI B "
					+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_PERMOHONAN = '" + id_permohonan + "' ";
			//myLogger.info("CHECK ID FAIL & SIMATI :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			String id_fail = "";
			while (rs.next()) {
				h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));	
				h.put("ID_SIMATI",rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				//id_fail = rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL");
			}
			//myLogger.info("id_fail :" + id_fail);
			//return id_fail;
		} finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
		return h;
	}

}
