package ekptg.model.ppk;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.pm.entity.ActivityEvent;
import lebah.pm.entity.UserActivityEvent;
import lebah.template.DbPersistence;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;

public class BicaraInteraktifData {

	static Logger myLogger = Logger.getLogger(ekptg.model.ppk.BicaraInteraktifData.class);
	private final String BASENAME = "dbconnection";
	private ResourceBundle rb = ResourceBundle.getBundle(BASENAME);
	public String skemaDB = rb.getString("user");
	public ResourceBundle rbCetakan = ResourceBundle.getBundle("cetakan");
	FrmPopupPilihPegawaiReportData modelReport = new FrmPopupPilihPegawaiReportData();
	public String fontSize = rbCetakan.getString("fontSizeCetakan");

	public List object = null;
    public void setCachedObject(List obj)
    {
      this.object = obj;

    }
    public List getCachedObject()
    {
      return this.object;
    }

	public String queryPerbicaraan(HttpSession session, String id_permohonan,String user_id, String id_jawatan, String id_negeri, String flagCari,Db db) throws Exception
	{
		//PEGAWAI BICARA
		//GET ID_PEGAWAI BY LOGIN
		user_id = (String)session.getAttribute("_ekptg_user_id");
		Map getDetailUsers =  getDetailUsers(session, "", user_id, "", db);
		String username = "";
		String id_pegawai = "";
		if(getDetailUsers != null)
		{
			username = (String)getDetailUsers.get("USER_NAME");
		}
		myLogger.info("queryPerbicaraan USER_NAME : "+username);
		String LIST_ID_UNITPSK = getDetailPegawaiList(session, username, db);

		String sql = " SELECT CHECK_TP.ID_PEGAWAIBARU, CHECK_TP.NAMA_PEGAWAI_BARU, F.ID_NEGERI,PR.FLAG_BANTAHAN, (CASE WHEN  PR.TARIKH_BICARA > TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY')  THEN 'Y' ELSE '' END) AS LEPASTARIKHHARINI,  ";
				if(!id_permohonan.equals(""))
				{
					sql += " (CASE WHEN CHECK_TP.ID_PEGAWAIBARU IS NOT NULL AND CHECK_TP.ID_PEGAWAIBARU IN ("+LIST_ID_UNITPSK+") THEN  'Y' " +
							" WHEN CHECK_TP.ID_PEGAWAIBARU IS NULL AND PR.ID_UNITPSK IN ("+LIST_ID_UNITPSK+") THEN 'Y' ELSE 'T' END) AS PEGAWAIBICARAASLOGIN, ";
				}
				else
				{
					sql += " '' AS PEGAWAIBICARAASLOGIN,";
				}
		        sql += " P.ID_STATUS,STP.KETERANGAN AS STATUS_PERMOHONAN, PRMAX.MAX_BIL_BICARA,PH.FLAG_JENIS_KEPUTUSAN,PH.FLAG_TANGGUH,PH.FLAG_BATAL,P.SEKSYEN, PR.ID_UNITPSK, PR.BIL_BICARA, SM.ID_SIMATI,PM.ID_PEMOHON, PM.NAMA_PEMOHON,PR.ID_PERBICARAAN, P.TARIKH_MOHON, P.ID_PERMOHONAN, P.ID_FAIL, F.NO_FAIL, SM.NAMA_SIMATI, "+
				" CASE WHEN PR.MASA_BICARA LIKE '%.%' THEN  "+
				" (CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, 1, INSTR(PR.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' ||  "+
				" CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0' ELSE PR.MASA_BICARA END "+
				" WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0' "+
				" ELSE PR.MASA_BICARA END) "+
				" WHEN LENGTH(PR.MASA_BICARA) = 4 THEN SUBSTR(PR.MASA_BICARA, 1, 2) || '.' || SUBSTR(PR.MASA_BICARA, 3) "+
				" ELSE '' END || (CASE WHEN PR.JENIS_MASA_BICARA = '1' THEN ' PAGI' "+
				" WHEN PR.JENIS_MASA_BICARA = '2' THEN ' TENGAH HARI' "+
				" WHEN PR.JENIS_MASA_BICARA = '3' THEN ' PETANG' ELSE '' END) AS MASA_BICARA, " +
				" TO_CHAR(PR.TARIKH_BICARA,'DD/MM/YYYY') AS TARIKH_BICARA, UP.NAMA_PEGAWAI AS PEG_PENGENDALI, PSM.ID_PERMOHONANSIMATI, " +
				" COUNT(DISTINCT MAXPH.ID_PERINTAH) AS TOTAL_PERINTAH, SUM(CASE WHEN TKP.STATUS_TUKARPEGAWAI = '1' THEN 1 ELSE 0 END) AS REKOD_TUKARPEGAWAI, " +
				" COUNT(JAGAAN.ID_DAERAHURUS) AS JAGAAN_MATCH ";
				if(!id_permohonan.equals(""))
				{
					sql +=
							" " +
							//", SEMAK.* " +
							"  ,SEMAK.ID_PERMOHONAN, SEMAK.LANTIK_PEGUAM, SEMAK.HARTA_TERTINGGAL, SEMAK.BATAL_PA,SEMAK.LANTIK_PA,SEMAK.LANTIK_KT,SEMAK.BATAL_KT,SEMAK.LAIN_TUJUAN,SEMAK.CATATAN_LAIN_TUJUAN " +
							"";
				}


				sql += " ,(CASE WHEN PR.JENIS_MASA_BICARA = '1' THEN ' AM' "+
						" WHEN PR.JENIS_MASA_BICARA = '2' THEN ' PM'  "+
						" WHEN PR.JENIS_MASA_BICARA = '3' THEN ' PM' ELSE 'AM' END) AS TEMP_AMPM, "+
						" CASE WHEN PR.MASA_BICARA LIKE '%.%' THEN   "+
						" (CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, 1, INSTR(PR.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' ||  "+
						" CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0' ELSE PR.MASA_BICARA END  "+
						" WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0'  "+
						" ELSE PR.MASA_BICARA END)  "+
						" WHEN LENGTH(PR.MASA_BICARA) = 4 THEN SUBSTR(PR.MASA_BICARA, 1, 2) || '.' || SUBSTR(PR.MASA_BICARA, 3)  "+
						" ELSE '' END || (CASE WHEN PR.JENIS_MASA_BICARA = '1' THEN ' AM'  "+
						" WHEN PR.JENIS_MASA_BICARA = '2' THEN ' PM'  "+
						" WHEN PR.JENIS_MASA_BICARA = '3' THEN ' PM' ELSE ' AM' END) AS TEMP_TIME ";


				sql += " FROM  "+
				" TBLPPKPERBICARAAN PR, TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKRUJUNIT UP,TBLPPKTUKARPEGAWAI TKP, "+
				" TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLPPKPEMOHON PM, TBLPPKPERINTAH PH, ";

				sql += " (SELECT TPM.ID_PERBICARAAN, TPM.ID_PEGAWAIBARU, UPK.NAMA_PEGAWAI AS NAMA_PEGAWAI_BARU  "+
						" FROM TBLPPKTUKARPEGAWAI TPM, TBLPPKRUJUNIT UPK, "+
						" (SELECT TP.ID_PERBICARAAN, MIN(TP.TARIKH_KEPUTUSAN) AS MIN_TARIKH_KEPUTUSAN "+
						" FROM TBLPPKTUKARPEGAWAI TP WHERE TP.STATUS_TUKARPEGAWAI = 2 "+
						" GROUP BY TP.ID_PERBICARAAN) CHK "+
						" WHERE TPM.ID_PERBICARAAN = CHK.ID_PERBICARAAN "+
						" AND TPM.TARIKH_KEPUTUSAN = CHK.MIN_TARIKH_KEPUTUSAN "+
						" AND TPM.ID_PEGAWAIBARU = UPK.ID_UNITPSK "+
						" AND TPM.STATUS_TUKARPEGAWAI = 2)  CHECK_TP, ";


				sql += " TBLPPKPERINTAH MAXPH, " +
				" (SELECT PRR.ID_KEPUTUSANPERMOHONAN, MAX(BIL_BICARA) AS MAX_BIL_BICARA FROM TBLPPKPERBICARAAN PRR GROUP BY PRR.ID_KEPUTUSANPERMOHONAN) PRMAX ";
				if(!id_permohonan.equals(""))
				{
					sql += ",  "+
					" (SELECT SMP.ID_PERMOHONAN, "+
					" NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN (PP.SEKSYEN = '17' AND SMP.ID_SEMAKANSENARAI = '8') OR (PP.SEKSYEN = '8' AND SMP.ID_SEMAKANSENARAI = '10') THEN  'Y' END)) )).extract ('//text()')),'T')  AS LANTIK_PEGUAM,  "+
					" NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN PP.SEKSYEN = '17' AND SMP.ID_SEMAKANSENARAI = '10' THEN  'Y' END)) )).extract ('//text()')),'T')  AS HARTA_TERTINGGAL,   "+
					" NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN PP.SEKSYEN = '17' AND SMP.ID_SEMAKANSENARAI = '12' THEN  'Y' END)) )).extract ('//text()')),'T')  AS BATAL_PA,    "+
					" NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN PP.SEKSYEN = '17' AND SMP.ID_SEMAKANSENARAI = '13' THEN  'Y' END)) )).extract ('//text()')),'T')  AS LANTIK_PA,  "+
					" NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN PP.SEKSYEN = '17' AND SMP.ID_SEMAKANSENARAI = '15' THEN  'Y' END)) )).extract ('//text()')),'T')  AS BATAL_KT,   "+
					" NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN PP.SEKSYEN = '17' AND SMP.ID_SEMAKANSENARAI = '16' THEN  'Y' END)) )).extract ('//text()')),'T')  AS LANTIK_KT,   "+
					" NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN PP.SEKSYEN = '17' AND SMP.ID_SEMAKANSENARAI = '17' THEN  'Y' END)) )).extract ('//text()')),'T')  AS LAIN_TUJUAN,  "+
					" NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN PP.SEKSYEN = '17' AND SMP.ID_SEMAKANSENARAI = '17' THEN  SMP.CATATAN END)) )).extract ('//text()')),'-')  AS CATATAN_LAIN_TUJUAN  "+
					" FROM TBLSEMAKANHANTAR SMP, TBLPPKPERMOHONAN PP  WHERE SMP.ID_PERMOHONAN = PP.ID_PERMOHONAN AND  SMP.ID_PERMOHONAN = '"+id_permohonan+"'   GROUP BY SMP.ID_PERMOHONAN)  "+
					" SEMAK ";
				}
				sql += " , TBLRUJSTATUS STP ";

				sql += " ,(SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"+user_id+"' "+
						" UNION  "+
						" SELECT DISTINCT PBU_U.ID_DAERAHURUS FROM TBLPERMOHONANBANTUUNIT PBU, TBLRUJPEJABATURUSAN PBU_U  "+
						" WHERE ID_STATUS = 2 AND TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE (TO_CHAR (PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY')  "+
						" AND TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE (TO_CHAR (PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY')  "+
						" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG AND PBU.ID_PEMOHON = '"+user_id+"') JAGAAN ";

				sql += " WHERE PR.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN AND P.ID_DAERAHMHN = JAGAAN.ID_DAERAHURUS(+)  " +
						" AND PR.ID_PERBICARAAN = MAXPH.ID_PERBICARAAN(+) " +
						" AND PR.ID_UNITPSK = UP.ID_UNITPSK(+) " +
						" AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_STATUS = STP.ID_STATUS " +
						" AND KP.ID_KEPUTUSANPERMOHONAN = PRMAX.ID_KEPUTUSANPERMOHONAN "+
				" AND PR.ID_PERBICARAAN = PH.ID_PERBICARAAN(+) " +
				" AND P.ID_FAIL = F.ID_FAIL AND PR.ID_PERBICARAAN = CHECK_TP.ID_PERBICARAAN(+) " +
				" AND PR.ID_PERBICARAAN = TKP.ID_PERBICARAAN(+) ";

				//temporary filter, sbb kat local slow untuk query suma rekod
				//filer tahun 2017 dlu
				//sql += " AND P.TARIKH_MOHON BETWEEN TO_DATE('01/01/2016','DD/MM/YYYY') AND TO_DATE('31/12/2017','DD/MM/YYYY') ";


				if(!id_permohonan.equals(""))
				{
					sql += " AND P.ID_PERMOHONAN = SEMAK.ID_PERMOHONAN(+) ";
				}


				sql += " AND F.ID_SEKSYEN = '2' AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI ";


				if(id_permohonan.equals(""))
				{
					myLogger.info(" flagCari :::: "+flagCari);
					if(flagCari.equals("Y"))
					{
						//sql += " AND P.ID_STATUS  IN (151,53,18,44,173,175,177,166,47) ";
						//sql += " AND PH.FLAG_JENIS_KEPUTUSAN IS NOT NULL ";
					}
					else
					{
						sql += " AND P.ID_STATUS = '18' ";
						sql += " AND PH.FLAG_JENIS_KEPUTUSAN IS NULL ";
					}

					/*
					String daerahJagaan =
						" AND P.ID_DAERAHMHN IN ( " +
						" SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"+user_id+"' " +
						" UNION  " +
						" SELECT DISTINCT PBU_U.ID_DAERAHURUS FROM TBLPERMOHONANBANTUUNIT PBU, TBLRUJPEJABATURUSAN PBU_U " +
						" WHERE ID_STATUS = 2 AND TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE (TO_CHAR (PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') " +
						" AND TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE (TO_CHAR (PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') " +
						" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG AND PBU.ID_PEMOHON = '"+user_id+"') ";
					*/



					//String mengikutNegeri = " AND (P.ID_NEGERIMHN = '"+id_negeri+"' OR R.ID_UNITPSK IN ("+LIST_ID_UNITPSK+")) ";

					//kkp & pengarah
					if((id_jawatan.equals("5") || id_jawatan.equals("4")) && flagCari.equals("Y"))
					{
						if(!id_negeri.equals("16"))
						{
							//sql += daerahJagaan;
							sql += " AND (P.ID_NEGERIMHN = '"+id_negeri+"' " +
									//" OR PR.ID_UNITPSK IN ("+LIST_ID_UNITPSK+")" +
									//" OR CHECK_TP.ID_PEGAWAIBARU IN ("+LIST_ID_UNITPSK+") "+
									" OR ( CASE WHEN  CHECK_TP.ID_PEGAWAIBARU IS NOT NULL THEN  CHECK_TP.ID_PEGAWAIBARU" +
									" WHEN  CHECK_TP.ID_PEGAWAIBARU IS NULL THEN  PR.ID_UNITPSK END) IN ("+LIST_ID_UNITPSK+")  "+
										" ) ";
						}
					}
					else
					{
						//sql += daerahJagaan;
						//comment sementara
						sql += " AND ( " +
								//" PR.ID_UNITPSK IN ("+LIST_ID_UNITPSK+") OR CHECK_TP.ID_PEGAWAIBARU IN ("+LIST_ID_UNITPSK+")" +
								" ( CASE WHEN  CHECK_TP.ID_PEGAWAIBARU IS NOT NULL THEN  CHECK_TP.ID_PEGAWAIBARU" +
								" WHEN  CHECK_TP.ID_PEGAWAIBARU IS NULL THEN  PR.ID_UNITPSK END) IN ("+LIST_ID_UNITPSK+")  "+
										") ";
					}
					//" AND P.ID_STATUS <> '999' " +
					sql += " AND P.FLAG_JENIS_PERMOHONAN = '1'";

				//check kkp

				}



		return sql;
	}


	@SuppressWarnings("unchecked")
	public String jenisTransaction(HttpSession session, String NAMA_TABLE, String NAMA_FIELD_PK,
			String SKRIN_NAME, String VALUE_FIELD_PK, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String aktiviti = "";
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT JENIS_AKTIVITI FROM TBLPPKSEJARAHBIMAIN WHERE NAMA_TABLE = '"+NAMA_TABLE+"' AND NAMA_FIELD_PK = '"+NAMA_FIELD_PK+"' " +
					" AND UPPER(SKRIN_NAME) = '"+SKRIN_NAME.toUpperCase()+"' ";
				if(!SKRIN_NAME.equals("simati") && !SKRIN_NAME.equals("pemohon"))
				{
					sql += " AND VALUE_FIELD_PK = '"+VALUE_FIELD_PK+"' ";
				}
			sql += " AND ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' AND ID_PERBICARAAN = '"+ID_PERBICARAAN+"' ";
			myLogger.info(" BICARA INTERAKTIF : SQL jenisTransaction :"+ sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				aktiviti = rs.getString("JENIS_AKTIVITI") == null ? "" : rs.getString("JENIS_AKTIVITI");
			}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return aktiviti;
	}



	@SuppressWarnings("unchecked")
	public Map viewPerbicaraan(HttpSession session, String id_perbicaraan, String id_permohonan, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += queryPerbicaraan(session,id_permohonan,"","","","",db1);
			sql += " AND PR.ID_PERBICARAAN  = '"+id_perbicaraan+"' ";
			sql += " GROUP BY   CHECK_TP.ID_PEGAWAIBARU, CHECK_TP.NAMA_PEGAWAI_BARU,F.ID_NEGERI,PR.FLAG_BANTAHAN,P.ID_STATUS, STP.KETERANGAN, PRMAX.MAX_BIL_BICARA, PH.FLAG_JENIS_KEPUTUSAN, PH.FLAG_TANGGUH, PH.FLAG_BATAL, P.SEKSYEN, "+
					" PR.BIL_BICARA, SM.ID_SIMATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PR.ID_PERBICARAAN, P.TARIKH_MOHON, P.ID_PERMOHONAN, "+
					" P.ID_FAIL, F.NO_FAIL, SM.NAMA_SIMATI,PR.MASA_BICARA, PR.JENIS_MASA_BICARA,PR.TARIKH_BICARA,UP.NAMA_PEGAWAI,PSM.ID_PERMOHONANSIMATI," +
					" PR.ID_UNITPSK,SEMAK.ID_PERMOHONAN, SEMAK.LANTIK_PEGUAM, SEMAK.HARTA_TERTINGGAL, SEMAK.BATAL_PA,SEMAK.LANTIK_PA,SEMAK.LANTIK_KT,SEMAK.BATAL_KT,SEMAK.LAIN_TUJUAN,SEMAK.CATATAN_LAIN_TUJUAN ";
			myLogger.info(" BICARA INTERAKTIF : SQL viewPerbicaraan :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());
			while (rs.next()) {
				h = getHashMapPerbicaraan(session,rs,"","",db,id_permohonan);
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public String getDetailPegawaiList(HttpSession session, String username, Db db)throws Exception {
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT LIST_ID_UNITPSK || (SELECT ',' || (rtrim (xmlagg (xmlelement (e, ID_UNITPSK || ','  )).extract ('//text()'))|| 0) FROM TBLPPKRUJUNIT  WHERE USER_ID = '"+USER_ID_SYSTEM+"'  GROUP BY USER_ID) AS LIST_ID_UNITPSK FROM ( " +
					" SELECT (rtrim (xmlagg (xmlelement (e, ID_UNITPSK || ','  )).extract ('//text()')) || 0) " +
				  // " || (SELECT ',' || (rtrim (xmlagg (xmlelement (e, ID_UNITPSK || ','  )).extract ('//text()'))|| 0) FROM TBLPPKRUJUNIT " +
				  // " WHERE USER_ID = '"+USER_ID_SYSTEM+"' " +
				  // " GROUP BY USER_ID) " +
				   " AS LIST_ID_UNITPSK FROM ( "+
				   " SELECT ID_UNITPSK,NAMA_PEGAWAI, UTL_MATCH.edit_distance(UPPER(NAMA_PEGAWAI),UPPER('"+username+"')) TOT_BEZA "+
				   " FROM TBLPPKRUJUNIT ) WHERE  TOT_BEZA < 4 ) ";
			myLogger.info(" BICARA INTERAKTIF : SQL getDetailPegawaiList :"+ sql);
			rs = stmt.executeQuery(sql);

			String LIST_ID_UNITPSK = "0";
			while (rs.next()) {
				LIST_ID_UNITPSK = rs == null ? "" :rs.getString("LIST_ID_UNITPSK") == null ? "" : rs.getString("LIST_ID_UNITPSK");
			}
			return LIST_ID_UNITPSK;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public Map getDetailPegawai(HttpSession session, String id_unitpsk, String user_id, String username, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();

			//rtrim (xmlagg (xmlelement (e, M.ID_STRATA || ','  )).extract ('//text()'))

			sql += " SELECT * FROM TBLPPKRUJUNIT "+
			" WHERE ID_UNITPSK IS NOT NULL ";
			if(!id_unitpsk.equals(""))
			{
				sql += " AND ID_UNITPSK = '"+id_unitpsk+"' ";
			}

			if(!user_id.equals(""))
			{
				sql += " AND USER_ID = '"+user_id+"' ";
			}

			if(!username.equals(""))
			{
				sql += " AND UPPER(NAMA_PEGAWAI) = '"+username.toUpperCase()+"' ";
			}

			myLogger.info(" BICARA INTERAKTIF : SQL getDetailPegawai :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_UNITPSK",rs == null ? "" :rs.getString("ID_UNITPSK") == null ? "" : rs.getString("ID_UNITPSK"));
				h.put("ID_NEGERI",rs == null ? "" :rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_JKPTG",rs == null ? "" :rs.getString("ID_JKPTG") == null ? "" : rs.getString("ID_JKPTG"));
				h.put("NAMA_PEGAWAI",rs == null ? "" :rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI"));
				h.put("NAMA_PEJABAT",rs == null ? "" :rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				h.put("ALAMAT1",rs == null ? "" :rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("ALAMAT2",rs == null ? "" :rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("ALAMAT3",rs == null ? "" :rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("POSKOD",rs == null ? "" :rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_BANDAR",rs == null ? "" :rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("NO_TEL",rs == null ? "" :rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("NO_TEL_SAMBUNGAN",rs == null ? "" :rs.getString("NO_TEL_SAMBUNGAN") == null ? "" : rs.getString("NO_TEL_SAMBUNGAN"));
				h.put("EMEL",rs == null ? "" :rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("USER_ID",rs == null ? "" :rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("JAWATAN",rs == null ? "" :rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
				h.put("STATUS_PEG",rs == null ? "" :rs.getString("STATUS_PEG") == null ? "" : rs.getString("STATUS_PEG"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public Map getMaklumatPindaan(HttpSession session, String ID_FAIL, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT NO_PINDAAN,TARIKH_MASUK,SYSDATE FROM (SELECT * FROM TBLPPKBORANG_HISTORY "+
					" WHERE NO_PINDAAN IS NOT NULL AND ID_FAIL = '"+ID_FAIL+"' " +
							" AND SYSDATE > TARIKH_MASUK " +
							" ORDER BY TARIKH_MASUK DESC "+
					" ) " +
					" WHERE ROWNUM = 1 " +
					" ";

			myLogger.info(" BICARA INTERAKTIF : SQL getMaklumatPindaan :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				myLogger.info(" BICARA INTERAKTIF : SQL getMaklumatPindaan HASH NO PINDAAN :"+ rs.getString("NO_PINDAAN"));
				h = Collections.synchronizedMap(new HashMap());
				h.put("NO_PINDAAN",rs == null ? "" :rs.getString("NO_PINDAAN") == null ? "" : rs.getString("NO_PINDAAN"));
			}
			myLogger.info(" BICARA INTERAKTIF : SQL getMaklumatPindaan HASH :"+ h);
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public Map getDetailUserEmel(HttpSession session, String NAMA_PEGAWAI, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();

			sql += "  SELECT ROWNUM, MM.* FROM ( "+
					"  SELECT U.USER_ID, U.USER_NAME, UI.EMEL,"+
					"  UTL_MATCH.EDIT_DISTANCE"+
					"  (UPPER (USER_NAME),"+
					"  UPPER ('"+NAMA_PEGAWAI.toUpperCase()+"')"+
					"  ) TOT_BEZA"+
					"  FROM USERS U, USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID AND UI.EMEL IS NOT NULL) MM "+
					"  WHERE tot_beza < 4 AND ROWNUM = 1"+
					"  ORDER BY tot_beza  ";


			myLogger.info(" BICARA INTERAKTIF : SQL getDetailUserEmel :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("USER_ID",rs == null ? "" :rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("USER_NAME",rs == null ? "" :rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("EMEL",rs == null ? "" :rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("TOT_BEZA",rs == null ? "" :rs.getString("TOT_BEZA") == null ? "" : rs.getString("TOT_BEZA"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}



	public Map getDetailUsers(HttpSession session, String nama, String user_id, String id_negeri, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT * FROM USERS U,USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID " +
					//" AND NVL(UI.FLAG_AKTIF,' ') IN (' ','1') " +
					//" AND UI.ID_SEKSYEN = '2' " +
					" ";
			if(!user_id.equals(""))
			{
				sql += " AND U.USER_ID = '"+user_id+"' ";
			}
			else
			{
				if(!nama.equals(""))
				{
					sql += " AND UPPER(U.USER_NAME) = '"+nama.toUpperCase()+"' ";
				}
			}

			if(!id_negeri.equals(""))
			{
				sql += " AND UI.ID_NEGERI = '"+id_negeri+"' ";
			}


			myLogger.info(" BICARA INTERAKTIF : SQL getDetailUsers :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("USER_ID",rs == null ? "" :rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("USER_NAME",rs == null ? "" :rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("ID_JAWATAN",rs == null ? "" :rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
				h.put("ID_NEGERI",rs == null ? "" :rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_SEKSYEN",rs == null ? "" :rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}



	public Map getDetailPSK(HttpSession session,String id_unitpsk, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT * FROM TBLPPKRUJUNIT WHERE ID_UNITPSK = '"+id_unitpsk+"' ";
			myLogger.info(" BICARA INTERAKTIF : SQL getDetailPSK :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_UNITPSK",rs == null ? "" :rs.getString("ID_UNITPSK") == null ? "" : rs.getString("ID_UNITPSK"));
				h.put("NAMA_PEGAWAI",rs == null ? "" :rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public Map mainID(HttpSession session, String id_perbicaraan, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT " +
					//" PH.FLAG_JENIS_KEPUTUSAN, PH.FLAG_TANGGUH, PH.FLAG_BATAL, PH.ID_JENISPERINTAH, " +
			" COUNT(DISTINCT PH.ID_PERINTAH) AS COUNT_PERINTAH, "+
			" P.ID_STATUS,P.ID_NEGERIMHN,P.FLAG_CREATE_HISTORY, P.SEKSYEN,TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON," +
			" F.ID_FAIL,F.NO_FAIL,P.BATAL_KUASA_PENTADBIR, P.BATAL_P_AMANAH, P.HARTA_TINGGAL, PB.ID_PERBICARAAN, P.ID_PERMOHONAN, " +
			" SM.ID_PERMOHONANSIMATI, P.ID_PEMOHON, SM.ID_SIMATI, P.SEKSYEN, "+
			" UN.NAMA_PEGAWAI, PB.ID_UNITPSK,PB.JENIS_MASA_BICARA, PB.MASA_BICARA,TO_CHAR(PB.TARIKH_BICARA,'DD/MM/YYYY') AS TARIKH_BICARA, PB.TEMPAT_BICARA, PB.ALAMAT_BICARA1, PB.ALAMAT_BICARA2, PB.ALAMAT_BICARA3, PB.POSKOD, PB.BANDAR, NP.NAMA_NEGERI AS NAMA_NEGERI_BICARA "+
			" FROM TBLPPKPERBICARAAN PB, " +
			" TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM, TBLPFDFAIL F, TBLPPKPERINTAH PH, TBLRUJNEGERI NP, TBLPPKRUJUNIT UN "+
			" WHERE F.ID_FAIL = P.ID_FAIL AND PB.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN " +
			" AND PB.ID_NEGERIBICARA = NP.ID_NEGERI(+) AND PB.ID_UNITPSK = UN.ID_UNITPSK(+)  " +
			" AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PERMOHONAN = SM.ID_PERMOHONAN " +
			" AND PB.ID_PERBICARAAN = PH.ID_PERBICARAAN(+) "+
			" AND PB.ID_PERBICARAAN = '"+id_perbicaraan+"' "+
			" GROUP BY P.ID_STATUS,P.ID_NEGERIMHN,P.FLAG_CREATE_HISTORY, P.SEKSYEN, "+
			" P.TARIKH_MOHON, F.ID_FAIL,F.NO_FAIL,P.BATAL_KUASA_PENTADBIR, P.BATAL_P_AMANAH, P.HARTA_TINGGAL, PB.ID_PERBICARAAN, "+
			" P.ID_PERMOHONAN, SM.ID_PERMOHONANSIMATI, P.ID_PEMOHON, SM.ID_SIMATI, P.SEKSYEN," +
			"  UN.NAMA_PEGAWAI, PB.ID_UNITPSK,PB.JENIS_MASA_BICARA, PB.MASA_BICARA,PB.TARIKH_BICARA, PB.TEMPAT_BICARA, PB.ALAMAT_BICARA1, PB.ALAMAT_BICARA2, PB.ALAMAT_BICARA3, PB.POSKOD, PB.BANDAR, NP.NAMA_NEGERI ";
			myLogger.info(" BICARA INTERAKTIF : SQL mainID :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());

			/*
			String FLAG_BATAL = "";
			String ID_JENISPERINTAH = "";
			String FLAG_TANGGUH = "";
			String FLAG_JENIS_KEPUTUSAN = "";
			*/
			String ID_NEGERIMHN = "";
			String FLAG_CREATE_HISTORY = "";
			String ID_FAIL = "";
			String SEKSYEN = "";
			String NO_FAIL = "";
			String TARIKH_MOHON = "";
			String ID_STATUS = "";
			String ID_PERBICARAAN = "";
			String ID_PERMOHONAN = "";
			String ID_PERMOHONANSIMATI = "";
			String ID_PEMOHON = "";
			String ID_SIMATI = "";
			String BATAL_KUASA_PENTADBIR = "";
			String BATAL_P_AMANAH = "";
			String HARTA_TINGGAL = "";
			int COUNT_PERINTAH = 0;

			String ID_UNITPSK = "";
			String MASA_BICARA = "";
			String TARIKH_BICARA = "";
			String TEMPAT_BICARA = "";
			String ALAMAT_BICARA1 = "";
			String ALAMAT_BICARA2 = "";
			String ALAMAT_BICARA3 = "";
			String POSKOD = "";
			String BANDAR = "";
			String NAMA_NEGERI_BICARA = "";
			String JENIS_MASA_BICARA = "";
			String NAMA_PEGAWAI = "";


			while (rs.next()) {
				/*
				FLAG_BATAL = (rs == null ? "" :rs.getString("FLAG_BATAL") == null ? "" : rs.getString("FLAG_BATAL"));
				ID_JENISPERINTAH = (rs == null ? "" :rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));
				FLAG_TANGGUH = (rs == null ? "" :rs.getString("FLAG_TANGGUH") == null ? "" : rs.getString("FLAG_TANGGUH"));
				FLAG_JENIS_KEPUTUSAN = (rs == null ? "" :rs.getString("FLAG_JENIS_KEPUTUSAN") == null ? "" : rs.getString("FLAG_JENIS_KEPUTUSAN"));
				*/
				ID_NEGERIMHN = (rs == null ? "" :rs.getString("ID_NEGERIMHN") == null ? "" : rs.getString("ID_NEGERIMHN"));
				FLAG_CREATE_HISTORY = (rs == null ? "" :rs.getString("FLAG_CREATE_HISTORY") == null ? "" : rs.getString("FLAG_CREATE_HISTORY"));
				ID_FAIL = (rs == null ? "" :rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				SEKSYEN = (rs == null ? "" :rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
				NO_FAIL = (rs == null ? "" :rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				TARIKH_MOHON = (rs == null ? "" :rs.getString("TARIKH_MOHON") == null ? "" : rs.getString("TARIKH_MOHON"));
				ID_STATUS = (rs == null ? "" :rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				ID_PERBICARAAN = (rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
				ID_PERMOHONAN = (rs == null ? "" :rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				ID_PERMOHONANSIMATI = (rs == null ? "" :rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				ID_PEMOHON = (rs == null ? "" :rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				ID_SIMATI = (rs == null ? "" :rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				BATAL_KUASA_PENTADBIR = (rs == null ? "" :rs.getString("BATAL_KUASA_PENTADBIR") == null ? "" : rs.getString("BATAL_KUASA_PENTADBIR"));
				BATAL_P_AMANAH = (rs == null ? "" :rs.getString("BATAL_P_AMANAH") == null ? "" : rs.getString("BATAL_P_AMANAH"));
				HARTA_TINGGAL = (rs == null ? "" :rs.getString("HARTA_TINGGAL") == null ? "" : rs.getString("HARTA_TINGGAL"));
				COUNT_PERINTAH = (rs == null ? 0 :rs.getString("COUNT_PERINTAH") == null ? 0 : rs.getInt("COUNT_PERINTAH"));

				ID_UNITPSK = (rs == null ? "" :rs.getString("ID_UNITPSK") == null ? "" : rs.getString("ID_UNITPSK"));
				MASA_BICARA = (rs == null ? "" :rs.getString("MASA_BICARA") == null ? "" : rs.getString("MASA_BICARA"));
				TARIKH_BICARA = (rs == null ? "" :rs.getString("TARIKH_BICARA") == null ? "" : rs.getString("TARIKH_BICARA").toUpperCase());
				TEMPAT_BICARA = (rs == null ? "" :rs.getString("TEMPAT_BICARA") == null ? "" : rs.getString("TEMPAT_BICARA"));
				ALAMAT_BICARA1 = (rs == null ? "" :rs.getString("ALAMAT_BICARA1") == null ? "" : rs.getString("ALAMAT_BICARA1").toUpperCase());
				ALAMAT_BICARA2 = (rs == null ? "" :rs.getString("ALAMAT_BICARA2") == null ? "" : rs.getString("ALAMAT_BICARA2").toUpperCase());
				ALAMAT_BICARA3 = (rs == null ? "" :rs.getString("ALAMAT_BICARA3") == null ? "" : rs.getString("ALAMAT_BICARA3").toUpperCase());
				POSKOD = (rs == null ? "" :rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				BANDAR = (rs == null ? "" :rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR").toUpperCase());
				NAMA_NEGERI_BICARA = (rs == null ? "" :rs.getString("NAMA_NEGERI_BICARA") == null ? "" : rs.getString("NAMA_NEGERI_BICARA").toUpperCase());
				NAMA_PEGAWAI = (rs == null ? "" :rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI").toUpperCase());
				JENIS_MASA_BICARA = (rs == null ? "" :rs.getString("JENIS_MASA_BICARA") == null ? "" : rs.getString("JENIS_MASA_BICARA"));


			}
			/*
			h.put("FLAG_BATAL",FLAG_BATAL);
			h.put("ID_JENISPERINTAH",ID_JENISPERINTAH);
			h.put("FLAG_TANGGUH",FLAG_TANGGUH);
			h.put("FLAG_JENIS_KEPUTUSAN",FLAG_JENIS_KEPUTUSAN);
			*/
			h.put("ID_NEGERIMHN",ID_NEGERIMHN);
			h.put("FLAG_CREATE_HISTORY",FLAG_CREATE_HISTORY);
			h.put("ID_FAIL",ID_FAIL);
			h.put("SEKSYEN",SEKSYEN);
			h.put("NO_FAIL",NO_FAIL);
			h.put("TARIKH_MOHON",TARIKH_MOHON);
			h.put("ID_STATUS",ID_STATUS);
			h.put("ID_PERBICARAAN",ID_PERBICARAAN);
			h.put("ID_PERMOHONAN",ID_PERMOHONAN);
			h.put("ID_PERMOHONANSIMATI",ID_PERMOHONANSIMATI);
			h.put("ID_PEMOHON",ID_PEMOHON);
			h.put("ID_SIMATI",ID_SIMATI);
			h.put("BATAL_KUASA_PENTADBIR",BATAL_KUASA_PENTADBIR);
			h.put("BATAL_P_AMANAH",BATAL_P_AMANAH);
			h.put("HARTA_TINGGAL",HARTA_TINGGAL);
			h.put("COUNT_PERINTAH",COUNT_PERINTAH);


			h.put("ID_UNITPSK",ID_UNITPSK);
			h.put("MASA_BICARA",MASA_BICARA);
			h.put("TARIKH_BICARA",TARIKH_BICARA);
			h.put("TEMPAT_BICARA",TEMPAT_BICARA);
			h.put("ALAMAT_BICARA1",ALAMAT_BICARA1);
			h.put("ALAMAT_BICARA2",ALAMAT_BICARA2);
			h.put("ALAMAT_BICARA3",ALAMAT_BICARA3);
			h.put("POSKOD",POSKOD);
			h.put("BANDAR",BANDAR);
			h.put("NAMA_NEGERI_BICARA",NAMA_NEGERI_BICARA);
			h.put("JENIS_MASA_BICARA",JENIS_MASA_BICARA);
			h.put("NAMA_PEGAWAI",NAMA_PEGAWAI);




			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}



	public Map perintahByFAIL(HttpSession session, String id_fail, String id_permohonansimati, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT DISTINCT PH.ID_PERINTAH, F.ID_FAIL FROM TBLPPKPERBICARAAN PB, TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM, TBLPFDFAIL F, TBLPPKPERINTAH PH "+
					" WHERE F.ID_FAIL = P.ID_FAIL AND PB.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN "+
					" AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PERMOHONAN = SM.ID_PERMOHONAN  "+
					" AND PB.ID_PERBICARAAN = PH.ID_PERBICARAAN ";

			if(id_fail.equals("") && id_permohonansimati.equals(""))
			{
				sql += " AND F.ID_FAIL = '' ";
			}
			else
			{
				if(!id_fail.equals(""))
				{
					sql += " AND F.ID_FAIL = '"+id_fail+"' ";
				}
				if(!id_permohonansimati.equals(""))
				{
					sql += " AND SM.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' ";
				}
			}
			myLogger.info(" BICARA INTERAKTIF : SQL perintahByFAIL :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());


			String ID_PERINTAH = "";

			while (rs.next()) {
				ID_PERINTAH = (rs == null ? "" :rs.getString("ID_PERINTAH") == null ? "" : rs.getString("ID_PERINTAH"));

			}

			h.put("ID_PERINTAH",ID_PERINTAH);

			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public Map permohonanSimatiByFAIL(HttpSession session, String id_fail, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT PSM.ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN P, TBLPFDFAIL F WHERE PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"+id_fail+"' ";
			myLogger.info(" BICARA INTERAKTIF : SQL permohonanSimatiByFAIL :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());


			String ID_PERMOHONANSIMATI = "";

			while (rs.next()) {
				ID_PERMOHONANSIMATI = (rs == null ? "" :rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));

			}

			h.put("ID_PERMOHONANSIMATI",ID_PERMOHONANSIMATI);

			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public Map permohonanSimatiBySIMATI_PERINTAH(HttpSession session, String id_simati, String id_perintah, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT D.ID_PERMOHONANSIMATI FROM TBLPPKPERINTAH A, TBLPPKPERBICARAAN B, TBLPPKKEPUTUSANPERMOHONAN C, TBLPPKPERMOHONANSIMATI D "
					+ "WHERE B.ID_PERBICARAAN = A.ID_PERBICARAAN AND C.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN AND C.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND A.ID_PERINTAH = '" + id_perintah + "' AND D.ID_SIMATI = '" + id_simati + "'";
			myLogger.info(" BICARA INTERAKTIF : SQL permohonanSimatiBySIMATI_PERINTAH :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());


			String ID_PERMOHONANSIMATI = "";

			while (rs.next()) {
				ID_PERMOHONANSIMATI = (rs == null ? "" :rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));

			}

			h.put("ID_PERMOHONANSIMATI",ID_PERMOHONANSIMATI);

			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public Map getMaklumatPerintahByIdPerbicaraan(HttpSession session, String id_perbicaraan, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT  PH.FLAG_JENIS_KEPUTUSAN, PH.FLAG_TANGGUH, PH.FLAG_BATAL, PH.ID_JENISPERINTAH, PH.CATATAN_PERINTAH_BI," +
					" PH.CATATAN_KEPUTUSAN_PERBICARAAN,PH.INTRO_CATATAN,PH.CATATAN_DOCKIV, PH.CATATAN, PH.ID_INTROPERINTAH, " +
					" PH.INTROFIELD1, PH.INTROFIELD2, PH.INTROFIELD3 " +
					" FROM  TBLPPKPERINTAH PH "+
			" WHERE PH.ID_PERBICARAAN = '"+id_perbicaraan+"' ";
			myLogger.info(" BICARA INTERAKTIF : SQL getMaklumatPerintahByIdPerbicaraan :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());


			String FLAG_BATAL = "";
			String ID_JENISPERINTAH = "";
			String FLAG_TANGGUH = "";
			String FLAG_JENIS_KEPUTUSAN = "";
			String CATATAN_PERINTAH_BI = "";
			String CATATAN = "";
			String CATATAN_KEPUTUSAN_PERBICARAAN = "";
			String ID_INTROPERINTAH = "";
			String INTROFIELD1 = "";
			String INTROFIELD2 = "";
			String INTROFIELD3 = "";
			String INTRO_CATATAN = "";
			String CATATAN_DOCKIV = "";




			while (rs.next()) {
				FLAG_BATAL = (rs == null ? "" :rs.getString("FLAG_BATAL") == null ? "" : rs.getString("FLAG_BATAL"));
				ID_JENISPERINTAH = (rs == null ? "" :rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));
				FLAG_TANGGUH = (rs == null ? "" :rs.getString("FLAG_TANGGUH") == null ? "" : rs.getString("FLAG_TANGGUH"));
				FLAG_JENIS_KEPUTUSAN = (rs == null ? "" :rs.getString("FLAG_JENIS_KEPUTUSAN") == null ? "" : rs.getString("FLAG_JENIS_KEPUTUSAN"));
				CATATAN_PERINTAH_BI = (rs == null ? "" :rs.getString("CATATAN_PERINTAH_BI") == null ? "" : rs.getString("CATATAN_PERINTAH_BI"));
				CATATAN = (rs == null ? "" :rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				CATATAN_KEPUTUSAN_PERBICARAAN = (rs == null ? "" :rs.getString("CATATAN_KEPUTUSAN_PERBICARAAN") == null ? "" : rs.getString("CATATAN_KEPUTUSAN_PERBICARAAN"));
				INTRO_CATATAN = (rs == null ? "" :rs.getString("INTRO_CATATAN") == null ? "" : rs.getString("INTRO_CATATAN"));
				CATATAN_DOCKIV = (rs == null ? "" :rs.getString("CATATAN_DOCKIV") == null ? "" : rs.getString("CATATAN_DOCKIV"));
				ID_INTROPERINTAH = (rs == null ? "" :rs.getString("ID_INTROPERINTAH") == null ? "" : rs.getString("ID_INTROPERINTAH"));
				INTROFIELD1 = (rs == null ? "" :rs.getString("INTROFIELD1") == null ? "" : rs.getString("INTROFIELD1"));
				INTROFIELD2 = (rs == null ? "" :rs.getString("INTROFIELD2") == null ? "" : rs.getString("INTROFIELD2"));
				INTROFIELD3 = (rs == null ? "" :rs.getString("INTROFIELD3") == null ? "" : rs.getString("INTROFIELD3"));
			}

			h.put("FLAG_BATAL",FLAG_BATAL);
			h.put("ID_JENISPERINTAH",ID_JENISPERINTAH);
			h.put("FLAG_TANGGUH",FLAG_TANGGUH);
			h.put("FLAG_JENIS_KEPUTUSAN",FLAG_JENIS_KEPUTUSAN);
			h.put("CATATAN_PERINTAH_BI",CATATAN_PERINTAH_BI);
			h.put("CATATAN_KEPUTUSAN_PERBICARAAN",CATATAN_KEPUTUSAN_PERBICARAAN);
			h.put("INTRO_CATATAN",INTRO_CATATAN);
			h.put("CATATAN_DOCKIV",CATATAN_DOCKIV);
			h.put("CATATAN",CATATAN);
			h.put("ID_INTROPERINTAH",ID_INTROPERINTAH);
			h.put("INTROFIELD1",INTROFIELD1);
			h.put("INTROFIELD2",INTROFIELD2);
			h.put("INTROFIELD3",INTROFIELD3);


			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	public Map getNotaHistoryJana(HttpSession session, String ID_HISTORYJANANOTA, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT NOTA FROM TBLPPKHISTORYJANANOTA WHERE ID_HISTORYJANANOTA = '"+ID_HISTORYJANANOTA+"' ";
			myLogger.info(" BICARA INTERAKTIF : SQL getNotaHistoryJana :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());


			String NOTA = "";





			while (rs.next()) {
				NOTA = (rs == null ? "" :rs.getString("NOTA") == null ? "" : rs.getString("NOTA"));
			}

			h.put("NOTA",NOTA);

			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	public Map jumlahHarta(HttpSession session, String id_permohonansimati, String id_perbicaraan, String flagNilaiAmamahraya, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql +=  " SELECT SUM(A) AS SUMHARTA, TRIM(TO_CHAR(SUM(A),'999,999,999,990.99')) AS SUMHARTA_CONVERT FROM ( ";
			if(flagNilaiAmamahraya.equals(""))
			{
					sql +=  " SELECT NVL(SUM((CASE WHEN HIS.NILAI_HTA_TARIKHMOHON IS NOT NULL THEN CASE WHEN HIS.NILAI_HTA_TARIKHMOHON = '-' THEN 0 ELSE NVL(TO_NUMBER(HIS.NILAI_HTA_TARIKHMOHON),0) END ELSE NVL(A.NILAI_HTA_TARIKHMOHON,0) END)),0) AS A  "+
							" FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A,  "+
							" (SELECT rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_HTAPERMOHONAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_HTAPERMOHONAN,	 "+
							" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NILAI_HTA_TARIKHMOHON' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS NILAI_HTA_TARIKHMOHON		 "+
							" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN   "+
							" AND M.NAMA_TABLE = 'TBLPPKHTAPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' AND ID_PERBICARAAN = '"+id_perbicaraan+"' "+
							" AND M.JENIS_AKTIVITI IN ('UPDATE')) HIS  "+
							" WHERE A1.ID_HTA = A.ID_HTA AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI "+
							" AND A.ID_HTAPERMOHONAN = HIS.ID_HTAPERMOHONAN(+) "+
							" AND A1.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' "+
							" AND A.ID_HTAPERMOHONAN NOT IN  "+
							" (SELECT NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_HTAPERMOHONAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')),0)  AS ID_HTAPERMOHONAN	 "+
							" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN   "+
							" AND M.NAMA_TABLE = 'TBLPPKHTAPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' AND ID_PERBICARAAN = '"+id_perbicaraan+"' "+
							" AND M.JENIS_AKTIVITI IN ('DELETE')) ";
					sql += " UNION ";

					sql += " SELECT "+
					" TO_NUMBER(NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NILAI_HTA_TARIKHMOHON' THEN  NVL(S.VALUE_SELEPAS,'0') END)) )).extract ('//text()')),'0'))  AS A		 "+
					" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN   "+
					" AND M.NAMA_TABLE = 'TBLPPKHTAPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' AND ID_PERBICARAAN = '"+id_perbicaraan+"' "+
					" AND M.JENIS_AKTIVITI IN ('INSERT')  GROUP BY m.id_sejarahbimain ";

					sql += " UNION ";
			}
			sql +=  " SELECT NVL(SUM((CASE WHEN HIS.NILAI_HA_TARIKHMOHON IS NOT NULL THEN CASE WHEN HIS.NILAI_HA_TARIKHMOHON = '-' THEN 0 ELSE NVL(TO_NUMBER(HIS.NILAI_HA_TARIKHMOHON),0) END ELSE NVL(A.NILAI_HA_TARIKHMOHON,0) END)),0) AS A  "+
					" FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A,  "+
					" (SELECT rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_HAPERMOHONAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_HAPERMOHONAN,    "+
					" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NILAI_HA_TARIKHMOHON' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS NILAI_HA_TARIKHMOHON,   "+
					" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_JENISHA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_JENISHA "+
					" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN "+
					" AND M.NAMA_TABLE = 'TBLPPKHAPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' AND ID_PERBICARAAN = '"+id_perbicaraan+"' "+
					" AND M.JENIS_AKTIVITI IN ('UPDATE')) HIS  "+
					" WHERE A1.ID_HA = A.ID_HA AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI "+
					" AND A.ID_HAPERMOHONAN = HIS.ID_HAPERMOHONAN(+) "+
					" AND A1.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' ";

			if(flagNilaiAmamahraya.equals("Y"))
			{
					sql += " AND (CASE WHEN HIS.ID_JENISHA IS NOT NULL THEN HIS.ID_JENISHA ELSE TO_CHAR(A.ID_JENISHA) END) = '98' ";
			}

			sql += 	" AND A.ID_HAPERMOHONAN NOT IN  "+
					" (SELECT NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_HAPERMOHONAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')),0)  AS ID_HAPERMOHONAN "+
					" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN   "+
					" AND M.NAMA_TABLE = 'TBLPPKHAPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' AND ID_PERBICARAAN = '"+id_perbicaraan+"' "+
					" AND M.JENIS_AKTIVITI IN ('DELETE')) ";
			sql += " UNION ";
			sql += " SELECT A FROM ( "+
					" SELECT  "+
					" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_JENISHA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_JENISHA, "+
					" TO_NUMBER(NVL(rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NILAI_HA_TARIKHMOHON' THEN  NVL(S.VALUE_SELEPAS,'0') END)) )).extract ('//text()')),'0'))  AS A "+
					" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN  "+
					" AND M.NAMA_TABLE = 'TBLPPKHAPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' AND ID_PERBICARAAN = '"+id_perbicaraan+"'  "+
					" AND M.JENIS_AKTIVITI IN ('INSERT')  GROUP BY m.id_sejarahbimain ) ";
			if(flagNilaiAmamahraya.equals("Y"))
			{
				sql += "WHERE  ID_JENISHA = '98'  ";
			}
			sql += 	" )";
			myLogger.info(" BICARA INTERAKTIF : SQL jumlahHarta :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());
			while (rs.next()) {
				h.put("SUMHARTA",rs == null ? "" :rs.getString("SUMHARTA") == null ? "" : rs.getString("SUMHARTA"));
				h.put("SUMHARTA_CONVERT",rs == null ? "" :rs.getString("SUMHARTA_CONVERT") == null ? "" : rs.getString("SUMHARTA_CONVERT"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Map viewKeterangan(HttpSession session, String ID_BIKEHADIRAN, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT * FROM TBLPPKBIKEHADIRAN WHERE ID_BIKEHADIRAN = '"+ID_BIKEHADIRAN+"' ";
			myLogger.info(" BICARA INTERAKTIF : SQL viewKeterangan :"+ sql);
			rs = stmt.executeQuery(sql);

			Map h = Collections.synchronizedMap(new HashMap());
			while (rs.next()) {
				h.put("ID_BIKEHADIRAN",rs == null ? "" :rs.getString("ID_BIKEHADIRAN") == null ? "" : rs.getString("ID_BIKEHADIRAN"));
				h.put("KETERANGAN",rs == null ? "" :rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("NOTA_PEGAWAI",rs == null ? "" :rs.getString("NOTA_PEGAWAI") == null ? "" : rs.getString("NOTA_PEGAWAI"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public String checkCurrentPemohonPadaOB_query(String ID_PERMOHONANSIMATI,String ID_PERBICARAAN, String aktiviti)
	{
		String sql =  " SELECT TH.* FROM ( "+
		" SELECT M.SKRIN_NAME,M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN,  "+
		" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_OBPERMOHONAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_OBPERMOHONAN, "+
		" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_OB' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_OB, "+
		" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PEMOHON' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_PEMOHON, "+
		" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PEMOHON' THEN  S.VALUE_SEBELUM END)) )).extract ('//text()')) AS ID_PEMOHON_SEBELUM, "+
		" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_SIMATI, "+
		" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PERMOHONANSIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_PERMOHONANSIMATI "+
		" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S "+
		" WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN  ";
		sql += " AND M.JENIS_AKTIVITI IN ('"+aktiviti+"') ";
		sql += " AND M.NAMA_TABLE = 'TBLPPKOBPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' AND ID_PERBICARAAN = '"+ID_PERBICARAAN+"'  "+
		" GROUP BY M.SKRIN_NAME,M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN ) TH ";
		return sql;
	}

	@SuppressWarnings("unchecked")
	public Map checkCurrentPemohonPadaOB(HttpSession session, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();

			sql += " SELECT * FROM ( ";
			sql += " SELECT HIS.SKRIN_NAME, TO_CHAR(OBMAIN.ID_TARAFKPTG) AS ID_TARAFKPTG,HIS.JENIS_AKTIVITI, HIS.ID_SEJARAHBIMAIN, TO_CHAR(OBMAIN.ID_OBPERMOHONAN) AS ID_OBPERMOHONAN, TO_CHAR(OBMAIN.ID_OB) AS ID_OB, TO_CHAR(OBMAIN.ID_SIMATI) AS ID_SIMATI, " +
			" (CASE WHEN HIS.ID_PEMOHON_SEBELUM IS NOT NULL THEN HIS.ID_PEMOHON_SEBELUM ELSE '' END) AS ID_PEMOHON_SEBELUM," +
			" (CASE WHEN HIS.ID_PEMOHON IS NOT NULL THEN HIS.ID_PEMOHON ELSE TO_CHAR(OBMAIN.ID_PEMOHON) END) AS ID_PEMOHON, TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI  " +
			" FROM TBLPPKOBPERMOHONAN OBMAIN, ("+checkCurrentPemohonPadaOB_query(ID_PERMOHONANSIMATI,ID_PERBICARAAN,"UPDATE")+") HIS " +
			" WHERE OBMAIN.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' AND OBMAIN.ID_OBPERMOHONAN = HIS.ID_OBPERMOHONAN(+)  ";
			sql += " UNION ALL ";
			sql += " SELECT OBMAIN.SKRIN_NAME, '' AS ID_TARAFKPTG, OBMAIN.JENIS_AKTIVITI,OBMAIN.ID_SEJARAHBIMAIN, OBMAIN.ID_OBPERMOHONAN, OBMAIN.ID_OB, OBMAIN.ID_SIMATI, " +
			" OBMAIN.ID_PEMOHON,OBMAIN.ID_PEMOHON_SEBELUM, OBMAIN.ID_PERMOHONANSIMATI  " +
			" FROM ("+checkCurrentPemohonPadaOB_query(ID_PERMOHONANSIMATI,ID_PERBICARAAN,"INSERT")+") OBMAIN " +
			" WHERE OBMAIN.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"'  ";
			sql += " ) THMAIN WHERE THMAIN.ID_PEMOHON IS NOT NULL  ";


			myLogger.info(" SQL checkCurrentPemohonPadaOB :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());
			while (rs.next()) {
				h.put("SKRIN_NAME",rs == null ? "" :rs.getString("SKRIN_NAME") == null ? "" : rs.getString("SKRIN_NAME"));
				h.put("ID_TARAFKPTG",rs == null ? "" :rs.getString("ID_TARAFKPTG") == null ? "" : rs.getString("ID_TARAFKPTG"));
				h.put("JENIS_AKTIVITI",rs == null ? "" :rs.getString("JENIS_AKTIVITI") == null ? "" : rs.getString("JENIS_AKTIVITI"));
				h.put("ID_SEJARAHBIMAIN",rs == null ? "" :rs.getString("ID_SEJARAHBIMAIN") == null ? "" : rs.getString("ID_SEJARAHBIMAIN"));
				h.put("ID_OBPERMOHONAN",rs == null ? "" :rs.getString("ID_OBPERMOHONAN") == null ? "" : rs.getString("ID_OBPERMOHONAN"));
				h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("ID_SIMATI",rs == null ? "" :rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI").toUpperCase());
				h.put("ID_PEMOHON",rs == null ? "" :rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON").toUpperCase());
				h.put("ID_PEMOHON_SEBELUM",rs == null ? "" :rs.getString("ID_PEMOHON_SEBELUM") == null ? "" : rs.getString("ID_PEMOHON_SEBELUM").toUpperCase());
				h.put("ID_SIMATI",rs == null ? "" :rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI").toUpperCase());
				h.put("ID_PERMOHONANSIMATI",rs == null ? "" :rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI").toUpperCase());
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	@SuppressWarnings("unchecked")
	public int checkFieldWujud(HttpSession session, String ID_SEJARAHBIMAIN, String NAMA_FIELD, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		int ada = 0;
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();

			sql += " SELECT COUNT(ID_SEJARAHBISUB) AS ADA FROM TBLPPKSEJARAHBISUB WHERE ID_SEJARAHBIMAIN = '"+ID_SEJARAHBIMAIN+"' AND NAMA_FIELD = '"+NAMA_FIELD+"'  ";
			//myLogger.info(" SQL checkFieldWujud :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());
			while (rs.next()) {
				ada = (rs == null ? 0 :rs.getString("ADA") == null ? 0 : rs.getInt("ADA"));
			}
			return ada;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	public String keteranganTangguh(String flag)
	{
		String keterangan = "";
		if(flag.equals("1"))
		{
			keterangan = "Pemohon / Waris Tidak Hadir";
		}
		else if(flag.equals("2"))
		{
			keterangan = "Senarai Waris Tidak Lengkap";
		}
		else if(flag.equals("3"))
		{
			keterangan = "Menunggu Keputusan Rujukan Mahkamah Syariah";
		}
		else if(flag.equals("4"))
		{
			keterangan = "Bukti Kematian Tidak Lengkap";
		}
		else if(flag.equals("5"))
		{
			keterangan = "Menunggu Keputusan Rujukan Kepada Ruler of The State atau Mahkamah Tinggi";
		}
		else if(flag.equals("6"))
		{
			keterangan = "Pertelingkahan Kolateral";
		}
		else if(flag.equals("7"))
		{
			keterangan = "Menunggu Sijil Faraid";
		}
		else if(flag.equals("8"))
		{
			keterangan = "Menunggu Surat Akuan Persetujuan";
		}
		else if(flag.equals("9"))
		{
			keterangan = "Sebab-sebab Lain";
		}
		return "<span class='blink blue'>"+keterangan+"</span>";
	}

	public String keteranganBatal(String flag)
	{
		String keterangan = "";
		if(flag.equals("1"))
		{
			keterangan = "Pindah ke Mahkamah Tinggi kerana ada wasiat";
		}
		else if(flag.equals("2"))
		{
			keterangan = "Tidak hadir setelah dipanggil 3 kali";
		}
		else if(flag.equals("3"))
		{
			keterangan = "Atas Permintaan Pemohon";
		}
		else if(flag.equals("4"))
		{
			keterangan = "Pindah ke Mahkamah Tinggi kerana harta melebihi RM5 juta";
		}
		else if(flag.equals("5"))
		{
			keterangan = "Sebab-sebab lain";
		}
		return "<span class='blink red'>"+keterangan+"</span>";
	}

	public String keteranganKeputusan(String flag)
	{
		String keterangan = "";
		if(flag.equals("1"))
		{
			keterangan = "<span class='blink blue'>Tangguh</span>";
		}
		else if(flag.equals("2"))
		{
			keterangan = "<span class='blink red'>Batal</span>";
		}
		else if(flag.equals("0"))
		{
			keterangan = "<span class='blink blue'>Selesai</span>";
		}
		else
		{
			keterangan = "Proses Perbicaraan";
		}
		return keterangan;
	}

	public Map getHashMapPerbicaraan(HttpSession session, ResultSet rs,String rowCss, String bil, Db db,String id_permohonan)
			throws Exception
	{
		Map h = Collections.synchronizedMap(new HashMap());
		h.put("rowCss",rowCss);
		h.put("BIL",bil);

		h.put("LEPASTARIKHHARINI",rs == null ? "" :rs.getString("LEPASTARIKHHARINI") == null ? "" : rs.getString("LEPASTARIKHHARINI"));
		h.put("ID_NEGERI",rs == null ? 0 :rs.getString("ID_NEGERI") == null ? 0 : rs.getInt("ID_NEGERI"));
		h.put("FLAG_BANTAHAN",rs == null ? "" :rs.getString("FLAG_BANTAHAN") == null ? "" : rs.getString("FLAG_BANTAHAN"));
		h.put("JAGAAN_MATCH",rs == null ? 0 :rs.getString("JAGAAN_MATCH") == null ? 0 : rs.getInt("JAGAAN_MATCH"));
		h.put("REKOD_TUKARPEGAWAI",rs == null ? 0 :rs.getString("REKOD_TUKARPEGAWAI") == null ? 0 : rs.getInt("REKOD_TUKARPEGAWAI"));
		h.put("TOTAL_PERINTAH",rs == null ? 0 :rs.getString("TOTAL_PERINTAH") == null ? 0 : rs.getInt("TOTAL_PERINTAH"));
		h.put("STATUS_PERMOHONAN",rs == null ? "" :rs.getString("STATUS_PERMOHONAN") == null ? "" : rs.getString("STATUS_PERMOHONAN"));
		h.put("ID_STATUS",rs == null ? "" :rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
		h.put("FLAG_JENIS_KEPUTUSAN",rs == null ? "" :rs.getString("FLAG_JENIS_KEPUTUSAN") == null ? "" : rs.getString("FLAG_JENIS_KEPUTUSAN"));
		h.put("KETERANGAN_KEPUTUSAN",rs == null ? "" :rs.getString("FLAG_JENIS_KEPUTUSAN") == null ? keteranganKeputusan("").toUpperCase() : keteranganKeputusan(rs.getString("FLAG_JENIS_KEPUTUSAN")).toUpperCase());
		h.put("FLAG_TANGGUH",rs == null ? "" :rs.getString("FLAG_TANGGUH") == null ? "" : rs.getString("FLAG_TANGGUH"));
		h.put("KETERANGAN_TANGGUH",rs == null ? "" :rs.getString("FLAG_TANGGUH") == null ? "" : keteranganTangguh(rs.getString("FLAG_TANGGUH")).toUpperCase());
		h.put("FLAG_BATAL",rs == null ? "" :rs.getString("FLAG_BATAL") == null ? "" : rs.getString("FLAG_BATAL"));
		h.put("KETERANGAN_BATAL",rs == null ? "" :rs.getString("FLAG_BATAL") == null ? "" : keteranganBatal(rs.getString("FLAG_BATAL")).toUpperCase());
		h.put("SEKSYEN",rs == null ? "" :rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
		h.put("ID_PERBICARAAN",rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
		h.put("ID_PERMOHONAN",rs == null ? "" :rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
		h.put("ID_FAIL",rs == null ? "" :rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
		h.put("NO_FAIL",rs == null ? "" :rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
		h.put("ID_PEMOHON",rs == null ? "" :rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON").toUpperCase());
		h.put("ID_SIMATI",rs == null ? "" :rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI").toUpperCase());
		h.put("NAMA_PEMOHON",rs == null ? "" :rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON").toUpperCase());
		h.put("BIL_BICARA",rs == null ? "" :rs.getString("BIL_BICARA") == null ? 0 : rs.getInt("BIL_BICARA"));
		h.put("MAX_BIL_BICARA",rs == null ? "" :rs.getString("MAX_BIL_BICARA") == null ? 0 : rs.getInt("MAX_BIL_BICARA"));
		h.put("NAMA_SIMATI",rs == null ? "" :rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI").toUpperCase());
		h.put("MASA_BICARA",rs == null ? "" :rs.getString("MASA_BICARA") == null ? "" : rs.getString("MASA_BICARA").toUpperCase());
		h.put("TARIKH_BICARA",rs == null ? "" :rs.getString("TARIKH_BICARA") == null ? "" : rs.getString("TARIKH_BICARA"));
		h.put("PEG_PENGENDALI",rs == null ? "" :rs.getString("PEG_PENGENDALI") == null ? "" : rs.getString("PEG_PENGENDALI").toUpperCase());
		h.put("ID_PERMOHONANSIMATI",rs == null ? "" :rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
		h.put("ID_UNITPSK",rs == null ? "" :rs.getString("ID_UNITPSK") == null ? "" : rs.getString("ID_UNITPSK"));
		h.put("PEGAWAIBICARAASLOGIN",rs == null ? "" :rs.getString("PEGAWAIBICARAASLOGIN") == null ? "" : rs.getString("PEGAWAIBICARAASLOGIN"));
		h.put("ID_PEGAWAIBARU",rs == null ? "" :rs.getString("ID_PEGAWAIBARU") == null ? "" : rs.getString("ID_PEGAWAIBARU"));
		h.put("NAMA_PEGAWAI_BARU",rs == null ? "" :rs.getString("NAMA_PEGAWAI_BARU") == null ? "" : rs.getString("NAMA_PEGAWAI_BARU"));

		if(!id_permohonan.equals(""))
		{
			h.put("LANTIK_PEGUAM",rs == null ? "" :rs.getString("LANTIK_PEGUAM") == null ? "" : rs.getString("LANTIK_PEGUAM").toUpperCase());
			h.put("HARTA_TERTINGGAL",rs == null ? "" :rs.getString("HARTA_TERTINGGAL") == null ? "" : rs.getString("HARTA_TERTINGGAL").toUpperCase());
			h.put("BATAL_PA",rs == null ? "" :rs.getString("BATAL_PA") == null ? "" : rs.getString("BATAL_PA").toUpperCase());
			h.put("LANTIK_PA",rs == null ? "" :rs.getString("LANTIK_PA") == null ? "" : rs.getString("LANTIK_PA").toUpperCase());
			h.put("BATAL_KT",rs == null ? "" :rs.getString("BATAL_KT") == null ? "" : rs.getString("BATAL_KT").toUpperCase());
			h.put("LANTIK_KT",rs == null ? "" :rs.getString("LANTIK_KT") == null ? "" : rs.getString("LANTIK_KT").toUpperCase());
			h.put("LAIN_TUJUAN",rs == null ? "" :rs.getString("LAIN_TUJUAN") == null ? "" : rs.getString("LAIN_TUJUAN").toUpperCase());
			h.put("CATATAN_LAIN_TUJUAN",rs == null ? "" :rs.getString("CATATAN_LAIN_TUJUAN") == null ? "" : rs.getString("CATATAN_LAIN_TUJUAN").toUpperCase());
		}
		return h;
	}

	public String queryListKehadiran(String ID_PERMOHONANSIMATI,String ID_PERMOHONAN, String ID_PERBICARAAN, String ID_PEMOHON)
	{
		myLogger.info("ID_PEMOHON :::: "+ID_PEMOHON);
		String sql = " SELECT CASE WHEN " +
				//"TO_NUMBER(NVL(A.UMUR,0)) < 18 AND TO_NUMBER(NVL(A.UMUR,0)) != 0 " +
				" A.STATUS_OB = '2' OR  A.STATUS_OB = '4' "+
				" THEN PENJAGA.LISTPENJAGA ELSE ''END AS PENJAGA, A.*, " +
				//"B.KETERANGAN," +
				//"B.NOTA_PEGAWAI " +
				"TRIM(REGEXP_REPLACE(B.KETERANGAN, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS KETERANGAN, " +
				"TRIM(REGEXP_REPLACE(B.NOTA_PEGAWAI, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS NOTA_PEGAWAI " +
				" FROM ( ";

		//modify sini untuk amik nama direct dari kehadiran

		sql += " SELECT SUPERMAIN.JENIS, " +
				" RTRIM (XMLAGG (XMLELEMENT (E, SUPERMAIN.HUBUNGAN || ' / ')).EXTRACT ('//text()'), ' / ') AS HUBUNGAN, "+
		" SUPERMAIN.STATUS, MIN(SUPERMAIN.ID_OBPERMOHONAN) AS ID_OBPERMOHONAN, MIN(SUPERMAIN.ID_OB) AS ID_OB, " +
		" MAX(CASE WHEN ID_PEMOHON = '"+ID_PEMOHON+"' THEN ID_PEMOHON END) AS ID_PEMOHON, " +
				" SUPERMAIN.NAMA," +
				" HADIR.NAMA AS NAMA_HADIR, HADIR.PENGENALAN AS PENGENALAN_HADIR, HADIR.HUBUNGAN AS HUBUNGAN_HADIR, HADIR.UMUR AS UMUR_HADIR, HADIR.STATUS AS STATUS_HADIR," +
				" SUPERMAIN.JENIS_OB," +
				" SUPERMAIN.PENGENALAN,MAX(SUPERMAIN.UMUR) AS UMUR, NVL(SUPERMAIN.STATUS_HIDUP,0) AS STATUS_HIDUP," +
		" SUPERMAIN.STATUS_OB, " +
		" MAX(HADIR.ID_BIKEHADIRAN) AS ID_BIKEHADIRAN," +
		" SUPERMAIN.NO_SURAT_BERANAK, SUPERMAIN.TARIKH_MATI FROM ( ";

		sql += " SELECT  TAR.KETERANGAN AS JENIS, SAU.KETERANGAN AS HUBUNGAN," +
		" CASE WHEN LISTOB.STATUS_OB = '1' THEN 'DEWASA / WARAS' " +
		" WHEN LISTOB.STATUS_OB = '2' THEN 'BELUM DEWASA' " +
		" WHEN LISTOB.STATUS_OB = '3' THEN 'HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN' " +
		" WHEN LISTOB.STATUS_OB = '4' THEN 'TIDAK SEMPURNA AKAL' " +
		" ELSE '' END AS STATUS, " +
		"  LISTOB.* FROM (  " +
		" SELECT 'OB' || OBMAIN.ID_OBPERMOHONAN AS ID_OBPERMOHONAN, " +
		" TO_CHAR(OBMAIN.ID_OB) AS ID_OB,  " +
		" (CASE WHEN HIS.ID_PEMOHON IS NOT NULL THEN TO_CHAR(HIS.ID_PEMOHON) ELSE TO_CHAR(OBMAIN.ID_PEMOHON) END) AS ID_PEMOHON, "+
		" (CASE WHEN HIS.NAMA_OB IS NOT NULL THEN HIS.NAMA_OB ELSE TO_CHAR(OBMAIN.NAMA_OB) END) AS NAMA, "+
		" (CASE WHEN HIS.JENIS_PEMIUTANG IS NOT NULL THEN HIS.NAMA_OB ELSE TO_CHAR(OBMAIN.JENIS_PEMIUTANG) END) AS JENIS_OB, "+



		" (CASE WHEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) "+
		" WHEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) "+
		" WHEN (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) IS NOT NULL THEN  "+
		" CASE " +
		" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '5' THEN 'TENTERA ' " +
		" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '6' THEN 'POLIS '  "+
		" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '4' THEN 'PASSPORT '  " +
		" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '7' THEN 'LAIN-LAIN ' " +
		" ELSE '' END "+
		" || (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) ELSE '' END) AS PENGENALAN, "+
		" (CASE WHEN HIS.ID_TARAFKPTG IS NOT NULL THEN HIS.ID_TARAFKPTG ELSE TO_CHAR(OBMAIN.ID_TARAFKPTG) END) AS ID_TARAFKPTG, "+
		" (CASE WHEN HIS.ID_SAUDARA IS NOT NULL THEN HIS.ID_SAUDARA ELSE TO_CHAR(OBMAIN.ID_SAUDARA) END) AS ID_SAUDARA, " +
		" (CASE WHEN HIS.STATUS_HIDUP IS NOT NULL THEN HIS.STATUS_HIDUP ELSE TO_CHAR(OBMAIN.STATUS_HIDUP) END) AS STATUS_HIDUP, " +
		" (CASE WHEN HIS.STATUS_OB IS NOT NULL THEN HIS.STATUS_OB ELSE TO_CHAR(OBMAIN.STATUS_OB) END) AS STATUS_OB, " +
		" (CASE WHEN HIS.UMUR IS NOT NULL THEN HIS.UMUR ELSE TO_CHAR(OBMAIN.UMUR) END) AS UMUR, " +
		" (CASE WHEN HIS.NO_SURAT_BERANAK IS NOT NULL THEN (CASE WHEN HIS.NO_SURAT_BERANAK != '-' THEN  HIS.NO_SURAT_BERANAK ELSE '' END) ELSE TO_CHAR(OBMAIN.NO_SURAT_BERANAK) END) AS NO_SURAT_BERANAK, " +
		" (CASE WHEN HIS.TARIKH_MATI IS NOT NULL THEN (CASE WHEN HIS.TARIKH_MATI != '-' THEN  HIS.TARIKH_MATI ELSE '' END) ELSE TO_CHAR(OBMAIN.TARIKH_MATI) END) AS TARIKH_MATI " +

		" FROM ( "+


		" SELECT OB.* FROM  "+
		" TBLPPKOBPERMOHONAN OB,  "+
		" TBLPPKPERMOHONANSIMATI PSM   "+
		" WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI  "+
		" AND PSM.ID_PERMOHONAN = '"+ID_PERMOHONAN+"'  " +
		" ) OBMAIN, " +
		"("+queryHistoryOB("",ID_PERMOHONANSIMATI,ID_PERBICARAAN,"UPDATE")+") HIS " +
				" WHERE OBMAIN.ID_OBPERMOHONAN = HIS.ID_OBPERMOHONAN(+) "+

		" AND OBMAIN.ID_OBPERMOHONAN  NOT IN (SELECT NVL(ID_OBPERMOHONAN,'') "+
		" FROM ("+queryHistoryOB("",ID_PERMOHONANSIMATI,ID_PERBICARAAN,"INSERT")+")) "+

		" UNION ALL "+

 		" SELECT  'OB' || TO_CHAR(OBMAIN.ID_OBPERMOHONAN) AS ID_OBPERMOHONAN, " +
 		" TO_CHAR(OBMAIN.ID_OB) AS ID_OB, " +
 		" OBMAIN.ID_PEMOHON AS ID_PEMOHON, "+
		" OBMAIN.NAMA_OB AS NAMA, "+
		" OBMAIN.JENIS_PEMIUTANG AS JENIS_OB, "+
		" (CASE WHEN OBMAIN.NO_KP_BARU IS NOT NULL THEN OBMAIN.NO_KP_BARU "+
		" WHEN OBMAIN.NO_KP_LAMA IS NOT NULL THEN OBMAIN.NO_KP_LAMA "+
		" WHEN OBMAIN.NO_KP_LAIN IS NOT NULL THEN  "+
		" CASE WHEN OBMAIN.JENIS_KP = '5' THEN 'TENTERA ' WHEN OBMAIN.JENIS_KP = '6' THEN 'POLIS '  "+
		" WHEN OBMAIN.JENIS_KP = '4' THEN 'PASSPORT '  WHEN OBMAIN.JENIS_KP = '7' THEN 'LAIN-LAIN ' ELSE '' END "+
		" || OBMAIN.NO_KP_LAIN ELSE '' END) AS PENGENALAN, "+
		" OBMAIN.ID_TARAFKPTG,  "+
		" OBMAIN.ID_SAUDARA,  "+
		" OBMAIN.STATUS_HIDUP,  "+
		" OBMAIN.STATUS_OB,  "+
		" OBMAIN.UMUR, OBMAIN.NO_SURAT_BERANAK, " +
		//"TO_CHAR(OBMAIN.TARIKH_MATI,'DD/MM/YYYY')" +
		"TO_CHAR(REPLACE(OBMAIN.TARIKH_MATI, '-', ''),'DD/MM/YYYY')" +
		"  AS TARIKH_MATI  "+
		" FROM (" + queryHistoryOB("",ID_PERMOHONANSIMATI,ID_PERBICARAAN,"INSERTDELETE") +") OBMAIN ";


		/*
		sql += " UNION ALL "+

		" SELECT 'H' || OBMAIN.ID_PENGHUTANG AS ID_OBPERMOHONAN, " +
		" (CASE WHEN HIS.NAMA_PENGHUTANG IS NOT NULL THEN HIS.NAMA_PENGHUTANG ELSE TO_CHAR(OBMAIN.NAMA_PENGHUTANG) END) AS NAMA, "+
		" (CASE WHEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) "+
		" WHEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) "+
		" WHEN (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) IS NOT NULL THEN  "+
		" CASE " +
		" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '5' THEN 'TENTERA ' " +
		" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '6' THEN 'POLIS '  "+
		" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '4' THEN 'PASSPORT '  " +
		" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '7' THEN 'LAIN-LAIN ' " +
		" ELSE '' END "+
		" || (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) ELSE '' END) AS PENGENALAN, "+
		" '' AS ID_TARAFKPTG, "+
		" '' AS ID_SAUDARA, " +
		" '' AS STATUS_HIDUP, " +
		" '' AS UMUR " +
		" FROM ( "+

		" SELECT * FROM  "+
		" TBLPPKPENGHUTANG OB,  "+
		" TBLPPKPERMOHONANSIMATI PSM   "+
		" WHERE OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI  "+
		" AND PSM.ID_PERMOHONAN = '"+ID_PERMOHONAN+"'  " +
		" ) OBMAIN, " +
		"("+queryHistoryPenghutang("",ID_PERMOHONANSIMATI,ID_PERBICARAAN,"UPDATE")+") HIS " +
				" WHERE OBMAIN.ID_PENGHUTANG = HIS.ID_PENGHUTANG(+) ";

		sql += " UNION ALL ";

		sql += " SELECT  'H' || TO_CHAR(OBMAIN.ID_PENGHUTANG) AS ID_OBPERMOHONAN, " +
		" OBMAIN.NAMA_PENGHUTANG AS NAMA, "+
		" (CASE WHEN OBMAIN.NO_KP_BARU IS NOT NULL THEN OBMAIN.NO_KP_BARU "+
		" WHEN OBMAIN.NO_KP_LAMA IS NOT NULL THEN OBMAIN.NO_KP_LAMA "+
		" WHEN OBMAIN.NO_KP_LAIN IS NOT NULL THEN  "+
		" CASE WHEN OBMAIN.JENIS_KP = '5' THEN 'TENTERA ' WHEN OBMAIN.JENIS_KP = '6' THEN 'POLIS '  "+
		" WHEN OBMAIN.JENIS_KP = '4' THEN 'PASSPORT '  WHEN OBMAIN.JENIS_KP = '7' THEN 'LAIN-LAIN ' ELSE '' END "+
		" || OBMAIN.NO_KP_LAIN ELSE '' END) AS PENGENALAN, "+
		" '' AS ID_TARAFKPTG,  "+
		" '' AS ID_SAUDARA,  "+
		" '' AS STATUS_HIDUP, " +
		" '' AS UMUR "+
		" FROM (" + queryHistoryPenghutang("",ID_PERMOHONANSIMATI,ID_PERBICARAAN,"INSERTDELETE") +") OBMAIN ";
		*/

		//PENGHUTANG HOLD DULU
		/*
		" UNION ALL  " +
		" SELECT 'H' || H.ID_PENGHUTANG AS ID_OB, H.NAMA_PENGHUTANG AS NAMA,   "+
		" (CASE 	WHEN H.NO_KP_BARU IS NOT NULL AND LENGTH(H.NO_KP_BARU) = 12   THEN SUBSTR(H.NO_KP_BARU,1,6) || '-' || SUBSTR(H.NO_KP_BARU,7,2) || '-' ||  "+
		" SUBSTR(H.NO_KP_BARU,9,4)  WHEN H.NO_KP_LAMA IS NOT NULL THEN UPPER(H.NO_KP_LAMA) WHEN H.NO_KP_LAIN IS NOT NULL THEN    (CASE WHEN H.JENIS_KP = '5' THEN 'Tentera : ' " +
		" WHEN H.JENIS_KP = '6' THEN 'Polis : '   WHEN H.JENIS_KP = '4' THEN 'Passport : ' WHEN H.JENIS_KP = '7' THEN 'Lain-lain : '   ELSE '' END) ||  UPPER(H.NO_KP_LAIN) ELSE '' END) AS PENGENALAN,  'PENGHUTANG' AS JENIS, 'PENGHUTANG' AS HUBUNGAN  "+
		" FROM TBLPPKPENGHUTANG H, TBLPPKPERMOHONANSIMATI PSM  WHERE H.ID_SIMATI = PSM.ID_SIMATI   AND PSM.ID_PERMOHONAN = '"+ID_PERMOHONAN+"' " +
		*/
		sql += " ) LISTOB,  " +
		/*
		" (SELECT TO_CHAR(BK.ID_HADIR) AS ID_HADIR, BK.ID_BIKEHADIRAN   "+
		" FROM TBLPPKBIKEHADIRAN BK WHERE BK.ID_PERBICARAAN = '"+ID_PERBICARAAN+"') HADIR, " +
		*/
		" TBLPPKRUJTARAFKPTG TAR, TBLPPKRUJSAUDARA SAU  " +
		" WHERE " +
		//" TO_CHAR(LISTOB.ID_OBPERMOHONAN) = HADIR.ID_HADIR(+) " +
		//" AND " +
		" LISTOB.ID_TARAFKPTG = TAR.ID_TARAFKPTG(+) " +
		" AND LISTOB.ID_SAUDARA = SAU.ID_SAUDARA(+) AND NVL(LISTOB.STATUS_HIDUP,' ') <> '1' " +
		" AND ID_OBPERMOHONAN NOT IN (SELECT NVL('OB' || ID_OBPERMOHONAN,'') "+
		" FROM ("+queryHistoryOB("",ID_PERMOHONANSIMATI,ID_PERBICARAAN,"DELETE")+")" +
		" )"+
		/*
		" AND ID_OBPERMOHONAN NOT IN (SELECT NVL('H' || ID_PENGHUTANG,'') "+
		" FROM ("+queryHistoryPenghutang("",ID_PERMOHONANSIMATI,ID_PERBICARAAN,"DELETE")+")" +
		" )"+
		*/
		"   ";
		sql += " ) SUPERMAIN, " +
				" (SELECT " +
				" TO_CHAR(BK.ID_HADIR) AS ID_HADIR, " +
				" BK.NAMA,BK.UMUR,BK.STATUS,BK.PENGENALAN,BK.HUBUNGAN,"+
				"BK.ID_BIKEHADIRAN, BK.KETERANGAN " +
				//" * "+
				"   "+
				" FROM TBLPPKBIKEHADIRAN BK WHERE BK.ID_PERBICARAAN = '"+ID_PERBICARAAN+"') HADIR " +
						" WHERE TO_CHAR(SUPERMAIN.ID_OBPERMOHONAN) = HADIR.ID_HADIR(+)  " +
				" GROUP BY SUPERMAIN.NAMA," +
				" HADIR.NAMA, HADIR.PENGENALAN, HADIR.HUBUNGAN, HADIR.UMUR, HADIR.STATUS," +

				" SUPERMAIN.JENIS_OB,SUPERMAIN.PENGENALAN, SUPERMAIN.STATUS, SUPERMAIN.PENGENALAN," +
				" SUPERMAIN.JENIS,SUPERMAIN.STATUS_HIDUP,SUPERMAIN.STATUS_OB,SUPERMAIN.NO_SURAT_BERANAK, SUPERMAIN.TARIKH_MATI " +
				" ORDER BY STATUS_HIDUP,UMUR DESC,NAMA ASC " +
				" ) A, TBLPPKBIKEHADIRAN B , " +
				" (SELECT PJ.ID_OBMINOR, " +
				" REGEXP_REPLACE(RTRIM (XMLAGG (XMLELEMENT (E, TRIM(UPPER(OB.NAMA_OB)) || ', ')).EXTRACT ('//text()'), ', '),'(,)([^,]*$)',' DAN\\2') AS LISTPENJAGA " +

				//" REGEXP_REPLACE(RTRIM (XMLAGG (XMLELEMENT (E, TRIM(UPPER(OB.NAMA_OB)) || ', ')).EXTRACT ('//text()'), ', '),'(,)([^,]*$)',' DAN\2')" +
				//" || REGEXP_SUBSTR(RTRIM (XMLAGG (XMLELEMENT (E, RTRIM(UPPER(OB.NAMA_OB)) || ', ')).EXTRACT ('//text()'), ', '),'([^,]*$)') AS LISTPENJAGA   "+


				" FROM TBLPPKPENJAGA PJ, TBLPPKOB OB " +
				" WHERE PJ.ID_OB = OB.ID_OB " +
				" GROUP BY PJ.ID_OBMINOR ) PENJAGA WHERE A.ID_BIKEHADIRAN = B.ID_BIKEHADIRAN(+) AND A.ID_OB = PENJAGA.ID_OBMINOR(+)  " +
				" ORDER BY A.ID_PEMOHON";
		return sql;
	}

	public Map getHashMapListKehadiran(HttpSession session, ResultSet rs,String rowCss, String bil, Db db)
			throws Exception
	{
		Map h = Collections.synchronizedMap(new HashMap());
		h.put("rowCss",rowCss);
		h.put("BIL",bil);
		h.put("ID_OBPERMOHONAN",rs == null ? "" :rs.getString("ID_OBPERMOHONAN") == null ? "" : rs.getString("ID_OBPERMOHONAN"));
		h.put("ID_OBPERMOHONAN_ASAL",(rs == null ? "" :rs.getString("ID_OBPERMOHONAN") == null ? "" : rs.getString("ID_OBPERMOHONAN")).replace("OB",""));
		h.put("ID_PEMOHON",rs == null ? "" :rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON").replace("-",""));
		h.put("NAMA",rs == null ? "" :rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
		h.put("JENIS_OB",rs == null ? "" :rs.getString("JENIS_OB") == null ? "" : rs.getString("JENIS_OB"));
		h.put("PENGENALAN",rs == null ? "" :rs.getString("PENGENALAN") == null ? "" : rs.getString("PENGENALAN").toUpperCase());
		h.put("UMUR",rs == null ? "" :rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
		h.put("UMUR_INT",rs == null ? "" :rs.getString("UMUR") == null ? 0 : rs.getInt("UMUR"));
		h.put("STATUS_OB",rs == null ? "" :rs.getString("STATUS_OB") == null ? "" : rs.getString("STATUS_OB"));
		h.put("STATUS",rs == null ? "" :rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
		h.put("JENIS",rs == null ? "" :rs.getString("JENIS") == null ? "" : rs.getString("JENIS").toUpperCase());
		h.put("HUBUNGAN",rs == null ? "" :rs.getString("HUBUNGAN") == null ? "" : rs.getString("HUBUNGAN").toUpperCase());
		h.put("ID_BIKEHADIRAN",rs == null ? "" :rs.getString("ID_BIKEHADIRAN") == null ? "" : rs.getString("ID_BIKEHADIRAN").toUpperCase());
		h.put("KETERANGAN",rs == null ? "" :rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		h.put("NOTA_PEGAWAI",rs == null ? "" :rs.getString("NOTA_PEGAWAI") == null ? "" : rs.getString("NOTA_PEGAWAI"));
		h.put("PENJAGA",rs == null ? "" :rs.getString("PENJAGA") == null ? "" : rs.getString("PENJAGA"));
		h.put("NAMA_HADIR",rs == null ? "" :rs.getString("NAMA_HADIR") == null ? "" : rs.getString("NAMA_HADIR"));
		h.put("PENGENALAN_HADIR",rs == null ? "" :rs.getString("PENGENALAN_HADIR") == null ? "" : rs.getString("PENGENALAN_HADIR"));
		h.put("HUBUNGAN_HADIR",rs == null ? "" :rs.getString("HUBUNGAN_HADIR") == null ? "" : rs.getString("HUBUNGAN_HADIR"));
		h.put("UMUR_HADIR",rs == null ? "" :rs.getString("UMUR_HADIR") == null ? "" : rs.getString("UMUR_HADIR"));
		h.put("STATUS_HADIR",rs == null ? "" :rs.getString("STATUS_HADIR") == null ? "" : rs.getString("STATUS_HADIR"));
		return h;
	}


	@SuppressWarnings("unchecked")
	public List checkCountKeteranganOB(HttpSession session,String id_permohonansimati,String id_permohonan, String id_perbicaraan,String id_pemohon, String Jenis, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List checkCountKeteranganOB = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}

		stmt = db1.getStatement();
		if(Jenis.equals("OB"))
		{
			sql += " SELECT " +
					" COUNT(DISTINCT ID_OB) AS TOTAL_WARIS,COUNT(DISTINCT CASE WHEN ID_BIKEHADIRAN IS NOT NULL THEN ID_OB END) AS TOTAL_HADIR, COUNT(CASE WHEN ID_BIKEHADIRAN IS NOT NULL AND KETERANGAN IS NOT NULL THEN ID_OB END) AS TOTAL_KETERANGAN  " +
					" FROM ( ";
			sql += queryListKehadiran(id_permohonansimati,id_permohonan, id_perbicaraan,id_pemohon);
			sql += ") ";
			myLogger.info(" BICARA INTERAKTIF : SQL checkCountKeteranganOB :"+ sql);
		}
		else if(Jenis.equals("TH"))
		{
			sql += " SELECT "+
					" 0 AS TOTAL_WARIS, "+
					" COUNT(DISTINCT ID_BIKEHADIRAN ) AS TOTAL_HADIR, "+
					" COUNT(CASE WHEN ID_BIKEHADIRAN IS NOT NULL AND KETERANGAN IS NOT NULL THEN ID_BIKEHADIRAN END) AS TOTAL_KETERANGAN   "+
					" FROM TBLPPKBIKEHADIRAN KP WHERE KP.ID_BIKEHADIRAN IS NOT NULL  AND KP.JENIS_HADIR = 'T'  "+
					" AND KP.ID_PERBICARAAN = '"+id_perbicaraan+"' ORDER BY NAMA  ";
		}
		rs = stmt.executeQuery(sql);
		checkCountKeteranganOB = Collections.synchronizedList(new ArrayList());

		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("TOTAL_WARIS",rs == null ? "" :rs.getString("TOTAL_WARIS") == null ? 0 : rs.getInt("TOTAL_WARIS"));
			h.put("TOTAL_HADIR",rs == null ? "" :rs.getString("TOTAL_HADIR") == null ? 0 : rs.getInt("TOTAL_HADIR"));
			h.put("TOTAL_KETERANGAN",rs == null ? "" :rs.getString("TOTAL_KETERANGAN") == null ? 0 : rs.getInt("TOTAL_KETERANGAN"));
			checkCountKeteranganOB.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return checkCountKeteranganOB;
	}

	@SuppressWarnings("unchecked")
	public List listKehadiran(HttpSession session,String id_permohonansimati,String id_permohonan, String id_perbicaraan,String id_pemohon, Db db)throws Exception {
		Db db1 = null;
		Statement stmt = null;
		ResultSet rs = null;
		List listKehadiran = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}

		stmt = db1.getStatement();
		sql += queryListKehadiran(id_permohonansimati,id_permohonan, id_perbicaraan,id_pemohon);
		myLogger.info(" BICARA INTERAKTIF : SQL listKehadiran :"+ sql);
		rs = stmt.executeQuery(sql);
		listKehadiran = Collections.synchronizedList(new ArrayList());

		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			listKehadiran.add(getHashMapListKehadiran(session,rs,rowCss,bil+"",db));
		}

		} finally {
			/*
			if (rs != null)
			{
				rs.close();
			}
			if (stmt != null)
			{
				stmt.close();
			}
			*/
			if (db == null)
			{
				stmt.close();
				db1.close();
			}
		}
		return listKehadiran;
	}



	@SuppressWarnings("unchecked")
	public List listKronologiStatus(HttpSession session,String id_permohonan, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listKronologiStatus = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}

		stmt = db1.getStatement();
		sql = sql = "SELECT DISTINCT "
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
		sql += " AND P.ID_PERMOHONAN = '" + id_permohonan + "' ";
		sql += "ORDER BY  ID_ORDER  ASC ";


		myLogger.info(" BICARA INTERAKTIF : SQL listKronologiStatus :"+ sql);
		rs = stmt.executeQuery(sql);
		listKronologiStatus = Collections.synchronizedList(new ArrayList());

		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			h.put("idPermohonan",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
			h.put("noFail",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
			h.put("idStatus",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
			h.put("idPermohonanSimati",rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
			h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "": sdf.format(rs.getDate("TARIKH_MOHON")));
			h.put("keterangan", rs.getString("KETERANGAN") == null ? "": rs.getString("KETERANGAN"));
			h.put("flagjenisfail",rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
			h.put("seksyen",rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
			h.put("idSimati",rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
			listKronologiStatus.add(h);
		}
		/*
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		*/
		return listKronologiStatus;
	}


	@SuppressWarnings("unchecked")
	public List listWarisBorangJKolateral(HttpSession session,String id_permohonansimati,String id_permohonan,
			String id_perbicaraan,String id_pemohon, String FLAG_TANGGUH, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listKehadiran = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}

		stmt = db1.getStatement();
		if(FLAG_TANGGUH.equals("5"))
		{
			sql += " SELECT BBJ.ID_BORANGJDTL, OBOP.* FROM (";
		}
		else if(FLAG_TANGGUH.equals("6"))
		{
			sql += " SELECT BBJ.ID_KOLATERALDTL,BBJ.JENIS_OB AS JENIS_OB_DTL, OBOP.* FROM (";
		}

		sql += queryListKehadiran(id_permohonansimati,id_permohonan, id_perbicaraan,id_pemohon);

		if(FLAG_TANGGUH.equals("5"))
		{
			sql += ") OBOP, (SELECT BJDTL.* FROM TBLPPKBORANGJ BJ, TBLPPKBORANGJDTL BJDTL ";
			sql += " WHERE BJ.ID_BORANGJ = BJDTL.ID_BORANGJ AND BJ.ID_PERBICARAAN = '"+id_perbicaraan+"') BBJ " +
					" WHERE OBOP.ID_OB = BBJ.ID_OB(+) AND OBOP.HUBUNGAN IS NOT NULL ";
		}
		else if(FLAG_TANGGUH.equals("6"))
		{
			sql += ") OBOP, (SELECT BJDTL.* FROM TBLPPKKOLATERALMST BJ, TBLPPKKOLATERALDTL BJDTL ";
			sql += " WHERE BJ.ID_KOLATERALMST = BJDTL.ID_KOLATERALMST AND BJ.ID_PERBICARAAN = '"+id_perbicaraan+"') BBJ " +
					" WHERE OBOP.ID_OB = BBJ.ID_OB(+) AND OBOP.HUBUNGAN IS NOT NULL ";
		}

		myLogger.info(" BICARA INTERAKTIF : SQL listWarisBorangJKolateral :"+ sql);
		rs = stmt.executeQuery(sql);
		listKehadiran = Collections.synchronizedList(new ArrayList());

		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }

			//Map h = Collections.synchronizedMap(new HashMap());
			h.put("rowCss",rowCss);
			h.put("BIL",bil+"");
			if(FLAG_TANGGUH.equals("5"))
			{
				h.put("ID_BORANGJDTL",rs == null ? "" :rs.getString("ID_BORANGJDTL") == null ? "" : rs.getString("ID_BORANGJDTL"));
			}
			else if(FLAG_TANGGUH.equals("6"))
			{
				h.put("ID_KOLATERALDTL",rs == null ? "" :rs.getString("ID_KOLATERALDTL") == null ? "" : rs.getString("ID_KOLATERALDTL"));
				h.put("JENIS_OB_DTL",rs == null ? "" :rs.getString("JENIS_OB_DTL") == null ? "" : rs.getString("JENIS_OB_DTL"));
			}
			h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
			h.put("ID_OBPERMOHONAN",rs == null ? "" :rs.getString("ID_OBPERMOHONAN") == null ? "" : rs.getString("ID_OBPERMOHONAN"));
			h.put("ID_OBPERMOHONAN_ASAL",(rs == null ? "" :rs.getString("ID_OBPERMOHONAN") == null ? "" : rs.getString("ID_OBPERMOHONAN")).replace("OB",""));
			h.put("ID_PEMOHON",rs == null ? "" :rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON").replace("-",""));
			h.put("NAMA",rs == null ? "" :rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
			h.put("PENGENALAN",rs == null ? "" :rs.getString("PENGENALAN") == null ? "" : rs.getString("PENGENALAN"));
			h.put("UMUR",rs == null ? "" :rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
			h.put("STATUS_OB",rs == null ? "" :rs.getString("STATUS_OB") == null ? "" : rs.getString("STATUS_OB"));
			h.put("STATUS",rs == null ? "" :rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
			h.put("JENIS",rs == null ? "" :rs.getString("JENIS") == null ? "" : rs.getString("JENIS").toUpperCase());
			h.put("HUBUNGAN",rs == null ? "" :rs.getString("HUBUNGAN") == null ? "" : rs.getString("HUBUNGAN").toUpperCase());
			h.put("ID_BIKEHADIRAN",rs == null ? "" :rs.getString("ID_BIKEHADIRAN") == null ? "" : rs.getString("ID_BIKEHADIRAN").toUpperCase());
			h.put("KETERANGAN",rs == null ? "" :rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
			//return h;
			//listKehadiran.add(getHashMapListKehadiran(session,rs,rowCss,bil+"",db));
			listKehadiran.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return listKehadiran;
	}


	@SuppressWarnings("unchecked")
	public List listMainPerubahan(HttpSession session,String id_perbicaraan,String skrinName, String tableName, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();
		sql += "SELECT U.USER_NAME AS NAMA_PEGAWAI, U.USER_ID AS ID_PEGAWAI, TO_CHAR(M.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_TRANSAKSI, TO_CHAR(M.TARIKH_MASUK,'DD/MM/YYYY HH24:MI') AS TARIKH_TRANSAKSI_FULL, " +
				" M.* FROM TBLPPKSEJARAHBIMAIN M, USERS U " +
				" WHERE M.ID_MASUK = U.USER_ID(+) AND M.ID_PERBICARAAN = '"+id_perbicaraan+"' AND NAMA_TABLE = '"+tableName+"' AND M.SKRIN_NAME = '"+skrinName+"' ORDER BY M.TARIKH_MASUK ";
		myLogger.info(" BICARA INTERAKTIF : SQL listMainPerubahan :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("ID_SEJARAHBIMAIN",rs == null ? "" :rs.getString("ID_SEJARAHBIMAIN") == null ? "" : rs.getString("ID_SEJARAHBIMAIN"));
			h.put("JENIS_AKTIVITI",rs == null ? "" :rs.getString("JENIS_AKTIVITI") == null ? "" : rs.getString("JENIS_AKTIVITI"));
			h.put("NAMA_TABLE",rs == null ? "" :rs.getString("NAMA_TABLE") == null ? "" : rs.getString("NAMA_TABLE"));
			h.put("SKRIN_NAME",rs == null ? "" :rs.getString("SKRIN_NAME") == null ? "" : rs.getString("SKRIN_NAME"));
			h.put("NAMA_FIELD_PK",rs == null ? "" :rs.getString("NAMA_FIELD_PK") == null ? "" : rs.getString("NAMA_FIELD_PK"));
			h.put("VALUE_FIELD_PK",rs == null ? "" :rs.getString("VALUE_FIELD_PK") == null ? "" : rs.getString("VALUE_FIELD_PK"));
			h.put("ID_PERMOHONANSIMATI",rs == null ? "" :rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
			h.put("ID_PERBICARAAN",rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
			h.put("TARIKH_TRANSAKSI",rs == null ? "" :rs.getString("TARIKH_TRANSAKSI") == null ? "" : rs.getString("TARIKH_TRANSAKSI"));
			h.put("TARIKH_TRANSAKSI_FULL",rs == null ? "" :rs.getString("TARIKH_TRANSAKSI_FULL") == null ? "" : rs.getString("TARIKH_TRANSAKSI_FULL"));
			h.put("ID_PEGAWAI",rs == null ? "" :rs.getString("ID_PEGAWAI") == null ? "" : rs.getString("ID_PEGAWAI"));
			h.put("NAMA_PEGAWAI",rs == null ? "" :rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}


	@SuppressWarnings("unchecked")
	public List listInfoPerintahByHarta(HttpSession session, String ID_PERMOHONAN, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();
		sql += " SELECT HAM.ID_PERINTAH, J.KETERANGAN, COUNT(DISTINCT HAM.ID_HTA) AS TOTAL_HARTA, 'HARTA TAK ALIH' AS JENISHARTA, 1 AS TURUTAN "+
				" FROM TBLPPKPERINTAHHTAOBMST HAM, TBLPPKRUJJENISPERINTAH J,TBLPPKPERINTAH PT, TBLPPKPERBICARAAN PR, TBLPPKKEPUTUSANPERMOHONAN KP  "+
				" WHERE HAM.ID_JENISPERINTAH = J.ID_JENISPERINTAH(+) "+
				" AND KP.ID_PERMOHONAN = '"+ID_PERMOHONAN+"' "+
				" AND PT.ID_PERINTAH = HAM.ID_PERINTAH AND PT.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN "+
				" GROUP BY HAM.ID_PERINTAH, J.KETERANGAN "+
				" UNION ALL "+
				" SELECT HAM.ID_PERINTAH, J.KETERANGAN, COUNT(DISTINCT HAM.ID_HA) AS TOTAL_HARTA, 'HARTA ALIH' AS JENISHARTA, 2 AS TURUTAN "+
				" FROM TBLPPKPERINTAHHAOBMST HAM, TBLPPKRUJJENISPERINTAH J,TBLPPKPERINTAH PT, TBLPPKPERBICARAAN PR, TBLPPKKEPUTUSANPERMOHONAN KP "+
				" WHERE HAM.ID_JENISPERINTAH = J.ID_JENISPERINTAH(+) "+
				" AND KP.ID_PERMOHONAN = '"+ID_PERMOHONAN+"' "+
				" AND PT.ID_PERINTAH = HAM.ID_PERINTAH AND PT.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN "+
				" GROUP BY HAM.ID_PERINTAH, J.KETERANGAN "+
				" ORDER BY TURUTAN ";
		myLogger.info(" BICARA INTERAKTIF : SQL listInfoPerintahByHarta :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("ID_PERINTAH",rs == null ? "" :rs.getString("ID_PERINTAH") == null ? "" : rs.getString("ID_PERINTAH"));
			h.put("KETERANGAN",rs == null ? "" :rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
			h.put("TOTAL_HARTA",rs == null ? "" :rs.getString("TOTAL_HARTA") == null ? "" : rs.getString("TOTAL_HARTA"));
			h.put("JENISHARTA",rs == null ? "" :rs.getString("JENISHARTA") == null ? "" : rs.getString("JENISHARTA"));
			h.put("TURUTAN",rs == null ? "" :rs.getString("TURUTAN") == null ? "" : rs.getString("TURUTAN"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}




	@SuppressWarnings("unchecked")
	public List listPerbicaraanLain(HttpSession session, String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();
		sql += " SELECT PR.ID_PERBICARAAN, PR.BIL_BICARA, PRMAX.MAX_BIL_BICARA, " +
				" SM.ID_PERMOHONANSIMATI, SM.ID_SIMATI, PM.ID_PEMOHON, P.ID_PERMOHONAN  "+
				" FROM TBLPPKPERMOHONAN P, TBLPPKKEPUTUSANPERMOHONAN KP,  "+
				" TBLPPKPERBICARAAN PR, TBLPPKPERMOHONANSIMATI SM, TBLPPKPEMOHON PM, "+
				" (SELECT PRR.ID_KEPUTUSANPERMOHONAN, MAX(BIL_BICARA) AS MAX_BIL_BICARA FROM TBLPPKPERBICARAAN PRR GROUP BY PRR.ID_KEPUTUSANPERMOHONAN) PRMAX "+
				" WHERE P.ID_PERMOHONAN = KP.ID_PERMOHONAN  "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = PR.ID_KEPUTUSANPERMOHONAN  "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = PRMAX.ID_KEPUTUSANPERMOHONAN "+
				" AND SM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PEMOHON = PM.ID_PEMOHON "+
				" AND P.ID_PERMOHONAN = '"+ID_PERMOHONAN+"' AND SM.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' ORDER BY PR.BIL_BICARA ";
		myLogger.info(" BICARA INTERAKTIF : SQL listPerbicaraanLain :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("ID_PERBICARAAN",rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
			h.put("BIL_BICARA",rs == null ? "" :rs.getString("BIL_BICARA") == null ? "" : rs.getString("BIL_BICARA"));
			h.put("MAX_BIL_BICARA",rs == null ? "" :rs.getString("MAX_BIL_BICARA") == null ? "" : rs.getString("MAX_BIL_BICARA"));
			h.put("ID_PERMOHONANSIMATI",rs == null ? "" :rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
			h.put("ID_SIMATI",rs == null ? "" :rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
			h.put("ID_PEMOHON",rs == null ? "" :rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
			h.put("ID_PERMOHONAN",rs == null ? "" :rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}


	@SuppressWarnings("unchecked")
	public List rekodHTABorangE(HttpSession session, String ID_PERINTAH, String ID_PERMOHONANSIMATI, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();

		//RAZMAN TAMBAH 23/10/2017
		sql += " SELECT * FROM ( ";

		sql += " SELECT " +
				" TRIM(REGEXP_REPLACE(MN.MAKLUMAT_HTA_1, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS MAKLUMAT_HTA, " +
				" TRIM(REGEXP_REPLACE(MN.CATATAN_HARTA_1, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN_HARTA, " +
				" TRIM(REGEXP_REPLACE(MN.CATATAN_1, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN, " +
				" MN.* FROM (" +
				" SELECT A.ID_PERINTAHHTAOBMST, "+
				" C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK,B.NO_PT, "+
				" B.BA_SIMATI, B.BB_SIMATI, D.NAMA_DAERAH, "+
				" E.NAMA_MUKIM,B.CATATAN AS CATATAN_1, "+
				" B.STATUS_PEMILIKAN,F.NAMA_SIMATI, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(UPPER(A.CATATAN),'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') AS CATATAN_HARTA_1, "+
				//" A.CATATAN AS CATATAN_HARTA, "+
				" CASE "+
				" WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||'' "+
				" WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4) "+
				" ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')' "+
				" END  AS KP_SIMATI, "+
				" CASE "+
				" WHEN B.CATATAN IS NOT NULL AND B.JENIS_HTA = 'Y' THEN C.KOD_JENIS_HAKMILIK || B.NO_HAKMILIK || '<br>' || B.NO_PT || '<br>' || B.BA_SIMATI || ' / ' || B.BB_SIMATI || ' bhg simati ' || '<br>' || '(' || B.CATATAN || ')' "+
				" WHEN B.CATATAN IS NOT NULL AND B.JENIS_HTA = 'T' THEN B.BA_SIMATI || ' / ' || B.BB_SIMATI || ' bhg simati' || '<br>' || '(' || B.CATATAN || ')' "+
				" WHEN B.CATATAN IS NULL AND B.JENIS_HTA = 'Y' THEN C.KOD_JENIS_HAKMILIK || B.NO_HAKMILIK || '<br>' || B.NO_PT || '<br>' || B.BA_SIMATI || ' / ' || B.BB_SIMATI || ' bhg simati' "+
				" WHEN B.CATATAN IS NULL AND B.JENIS_HTA = 'T' THEN B.BA_SIMATI || ' / ' || B.BB_SIMATI || ' bhg simati' "+
				" END AS MAKLUMAT_HTA_1, "+
				" (SELECT COUNT(*) "+
				" FROM TBLPPKPERINTAHHTAOBDTL G, TBLPPKOB H "+
				" WHERE H.ID_OB = G.ID_OB "+
				" AND G.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST "+
				" AND G.ID_PA1 IS NULL "+
				" AND G.BA_WARIS != 0) AS singleWaris, "+
				" (SELECT COUNT(COUNT(*)) "+
				" FROM TBLPPKPERINTAHHTAOBDTL G, TBLPPKOB H "+
				" WHERE H.ID_OB = G.ID_OB "+
				" AND G.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST "+
				" AND G.ID_PA1 IS NOT NULL "+
				" AND G.ID_PA2 IS NULL "+
				" AND G.BA_WARIS != 0 "+
				" GROUP BY G.STATUS_TADBIR) AS singlePA, "+
				" (SELECT COUNT(COUNT(*)) "+
				" FROM TBLPPKPERINTAHHTAOBDTL G, TBLPPKOB H "+
				" WHERE H.ID_OB = G.ID_OB "+
				" AND G.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST "+
				" AND G.ID_PA1 IS NOT NULL "+
				" AND G.ID_PA2 IS NOT NULL "+
				" AND G.ID_PA3 IS NULL "+
				" AND G.BA_WARIS != 0 "+
				" GROUP BY G.STATUS_TADBIR) AS doublePA, "+
				" (SELECT COUNT(COUNT(*)) "+
				" FROM TBLPPKPERINTAHHTAOBDTL G, TBLPPKOB H "+
				" WHERE H.ID_OB = G.ID_OB "+
				" AND G.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST "+
				" AND G.ID_PA1 IS NOT NULL "+
				" AND G.ID_PA2 IS NOT NULL "+
				" AND G.ID_PA3 IS NOT NULL "+
				" AND G.ID_PA4 IS NULL "+
				" AND G.BA_WARIS != 0 "+
				" GROUP BY G.STATUS_TADBIR ) AS triplePA, "+
				" (SELECT COUNT(COUNT(*)) "+
				" FROM TBLPPKPERINTAHHTAOBDTL G, TBLPPKOB H "+
				" WHERE H.ID_OB = G.ID_OB "+
				" AND G.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST "+
				" AND G.ID_PA1 IS NOT NULL "+
				" AND G.ID_PA2 IS NOT NULL "+
				" AND G.ID_PA3 IS NOT NULL "+
				" AND G.ID_PA4 IS NOT NULL "+
				" AND G.BA_WARIS != 0 "+
				" GROUP BY G.STATUS_TADBIR ) as fourPA, "+
				" (SELECT COUNT(COUNT(*)) "+
				" FROM TBLPPKPERINTAHHTAOBDTL G, TBLPPKOB H "+
				" WHERE H.ID_OB = G.ID_PA1 "+
				" AND G.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST "+
				" AND G.ID_PA1 IS NOT NULL "+
				" AND G.ID_PA2 IS NULL "+
				" AND G.ID_OB IS NULL "+
				" AND G.BA_WARIS != 0 "+
				" AND G.STATUS_TADBIR = 'Y' "+
				" GROUP BY G.STATUS_TADBIR) as singlePAOBHilang, "+
				" (SELECT COUNT(COUNT(*)) "+
				" FROM TBLPPKPERINTAHHTAOBDTL G, TBLPPKOB H "+
				" WHERE H.ID_OB = G.ID_PA1 "+
				" AND G.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST "+
				" AND G.ID_PA1 IS NOT NULL "+
				" AND G.ID_PA2 IS NOT NULL "+
				" AND G.ID_PA3 IS NULL "+
				" AND G.ID_OB IS NULL "+
				" AND G.BA_WARIS != 0 "+
				" AND G.STATUS_TADBIR = 'Y' "+
				" GROUP BY G.STATUS_TADBIR)as doublePAOBHilang, "+
				" (SELECT COUNT(COUNT(*)) "+
				" FROM TBLPPKPERINTAHHTAOBDTL G, TBLPPKOB H "+
				" WHERE H.ID_OB = G.ID_PA1 "+
				" AND G.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST "+
				" AND G.ID_PA1 IS NOT NULL "+
				" AND G.ID_PA2 IS NOT NULL "+
				" AND G.ID_PA3 IS NOT NULL "+
				" AND G.ID_PA4 IS NULL "+
				" AND G.ID_OB IS NULL "+
				" AND G.BA_WARIS != 0 "+
				" AND G.STATUS_TADBIR = 'Y' "+
				" GROUP BY G.STATUS_TADBIR) as triplePAOBHilang, "+
				" (SELECT COUNT(COUNT(*)) "+
				" FROM TBLPPKPERINTAHHTAOBDTL G, TBLPPKOB H "+
				" WHERE H.ID_OB = G.ID_PA1 "+
				" AND G.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST "+
				" AND G.ID_PA1 IS NOT NULL "+
				" AND G.ID_PA2 IS NOT NULL "+
				" AND G.ID_PA3 IS NOT NULL "+
				" AND G.ID_PA4 IS NOT NULL "+
				" AND G.ID_OB IS NULL "+
				" AND G.BA_WARIS != 0 "+
				" AND G.STATUS_TADBIR = 'Y' "+
				" GROUP BY G.STATUS_TADBIR) as fourPAOBHilang "+
				" FROM TBLPPKPERINTAHHTAOBMST A, "+
				" TBLPPKHTAPERMOHONAN B, "+
				" TBLRUJJENISHAKMILIK C, "+
				" TBLRUJDAERAH D, "+
				" TBLRUJMUKIM E, "+
				" TBLPPKSIMATI F, "+
				" (SELECT "+
				" CASE "+
				" WHEN TBLPPKSIMATI.NO_KP_BARU IS NULL AND TBLPPKSIMATI.NO_KP_LAMA IS NOT NULL THEN  TBLPPKSIMATI.NO_KP_LAMA "+
				" WHEN TBLPPKSIMATI.NO_KP_BARU IS NULL AND TBLPPKSIMATI.NO_KP_LAMA IS NULL THEN  TBLPPKSIMATI.NO_KP_LAIN "+
				" WHEN TBLPPKSIMATI.NO_KP_BARU IS NULL AND TBLPPKSIMATI.NO_KP_LAIN IS NULL THEN  TBLPPKSIMATI.NO_KP_LAMA "+
				" ELSE TBLPPKSIMATI.NO_KP_BARU "+
				" END || '' || "+
				" CASE "+
				" WHEN TBLPPKSIMATI.NO_KP_BARU IS NOT NULL AND TBLPPKSIMATI.NO_KP_LAMA IS NOT NULL THEN TBLPPKSIMATI.NO_KP_LAMA "+
				" END || '' || "+
				" CASE "+
				" WHEN TBLPPKSIMATI.NO_KP_BARU IS  NULL AND TBLPPKSIMATI.NO_KP_LAMA IS NOT NULL THEN TBLPPKSIMATI.NO_KP_LAIN "+
				" END AS NO_KP1, ID_SIMATI "+
				" FROM TBLPPKSIMATI ) AAA "+
				" WHERE A.ID_JENISPERINTAH IN (1,7) "+
				" AND B.ID_HTA = A.ID_HTA "+
				" AND B.ID_DAERAH = D.ID_DAERAH "+
				" AND B.ID_MUKIM = E.ID_MUKIM "+
				" AND F.ID_SIMATI = B.ID_SIMATI "+
				" AND F.ID_SIMATI = AAA.ID_SIMATI "+
				" AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+) "+
        		" AND A.ID_PERINTAH = '"+ID_PERINTAH+"' "+
				" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
				" ORDER BY A.ID_PERINTAHHTAOBMST ASC) MN ";

		//RAZMAN TAMBAH 23/10/2017
		sql += " ) ";
			//	"WHERE SINGLEWARIS != 0 ";

		myLogger.info(" BICARA INTERAKTIF : SQL rekodHartaTakAlihBorangE :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("ID_PERINTAHHTAOBMST",rs == null ? "" :rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
			h.put("KOD_JENIS_HAKMILIK",rs == null ? "" :rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK"));
			h.put("NO_HAKMILIK",rs == null ? "" :rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
			h.put("NO_PT",rs == null ? "" :rs.getString("NO_PT") == null ? "" : rs.getString("NO_PT"));
			h.put("BA_SIMATI",rs == null ? "" :rs.getString("BA_SIMATI") == null ? "" : rs.getString("BA_SIMATI"));
			h.put("BB_SIMATI",rs == null ? "" :rs.getString("BB_SIMATI") == null ? "" : rs.getString("BB_SIMATI"));
			h.put("NAMA_DAERAH",rs == null ? "" :rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
			h.put("NAMA_MUKIM",rs == null ? "" :rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
			h.put("CATATAN",rs == null ? "" :rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
			h.put("STATUS_PEMILIKAN",rs == null ? "" :rs.getString("STATUS_PEMILIKAN") == null ? "" : rs.getString("STATUS_PEMILIKAN"));
			h.put("NAMA_SIMATI",rs == null ? "" :rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
			h.put("CATATAN_HARTA",rs == null ? "" :rs.getString("CATATAN_HARTA") == null ? "" : rs.getString("CATATAN_HARTA"));
			h.put("KP_SIMATI",rs == null ? "" :rs.getString("KP_SIMATI") == null ? "" : rs.getString("KP_SIMATI"));
			h.put("MAKLUMAT_HTA",rs == null ? "" :rs.getString("MAKLUMAT_HTA") == null ? "" : rs.getString("MAKLUMAT_HTA"));

			h.put("SINGLEWARIS",rs == null ? 0 :rs.getString("SINGLEWARIS") == null ? 0 : rs.getInt("SINGLEWARIS"));
			h.put("SINGLEPA",rs == null ? 0 :rs.getString("SINGLEPA") == null ? 0 : rs.getInt("SINGLEPA"));
			h.put("DOUBLEPA",rs == null ? 0 :rs.getString("DOUBLEPA") == null ? 0 : rs.getInt("DOUBLEPA"));
			h.put("TRIPLEPA",rs == null ? 0 :rs.getString("TRIPLEPA") == null ? 0 : rs.getInt("TRIPLEPA"));
			h.put("FOURPA",rs == null ? 0 :rs.getString("FOURPA") == null ? 0 : rs.getInt("FOURPA"));
			h.put("SINGLEPAOBHILANG",rs == null ? 0 :rs.getString("SINGLEPAOBHILANG") == null ? 0 : rs.getInt("SINGLEPAOBHILANG"));
			h.put("DOUBLEPAOBHILANG",rs == null ? 0 :rs.getString("DOUBLEPAOBHILANG") == null ? 0 : rs.getInt("DOUBLEPAOBHILANG"));
			h.put("TRIPLEPAOBHILANG",rs == null ? 0 :rs.getString("TRIPLEPAOBHILANG") == null ? 0 : rs.getInt("TRIPLEPAOBHILANG"));
			h.put("FOURPAOBHILANG",rs == null ? 0 :rs.getString("FOURPAOBHILANG") == null ? 0 : rs.getInt("FOURPAOBHILANG"));

			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}


	@SuppressWarnings("unchecked")
	public List rekodHABorangE(HttpSession session, String ID_PERINTAH, String ID_PERMOHONANSIMATI, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();
		sql += " SELECT " +
				" TRIM(REGEXP_REPLACE(MN.MAKLUMAT_HA, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS MAKLUMAT_HA, " +
				" TRIM(REGEXP_REPLACE(MN.CATATAN_HARTA, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN_HARTA, " +
				"MN.* FROM (SELECT D.ID_PERINTAHHAOBMST, "+
				" E.ID_HA, "+
				" D.CATATAN AS CATATAN_HARTA, "+
				" CASE "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD = '02' THEN F.KETERANGAN ||' - '|| E.JENAMA || '<br>' || 'No Akaun: ' || E.NO_DAFTAR || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || '1 / 1 bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND E.NAMA_SAHAM IS NOT NULL AND F.KOD = '01' THEN F.KETERANGAN ||' - ' || NVL(E.NAMA_SAHAM,'') || '<br>' || 'No Ahli: ' || NVL(E.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(E.NO_SIJIL,' - ') || '<br>' || 'Bil. Unit: ' || NVL(TRIM(TO_CHAR(E.BIL_UNIT,'999,999,999')),' - ') || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND E.NAMA_SAHAM IS NULL AND F.KOD = '01' THEN F.KETERANGAN || '<br>' || 'No Ahli: ' || NVL(E.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(E.NO_SIJIL,' - ') || '<br>' ||  'Bil. Unit: ' || NVL(TRIM(TO_CHAR(E.BIL_UNIT,'999,999,999')),' - ') || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD  = '05' THEN F.KETERANGAN || ' - ' || E.JENAMA || '<br>' || 'No Polisi: ' || E.NO_DAFTAR || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD  = '03' THEN F.KETERANGAN || ' - ' || E.JENAMA || '<br>' || 'No Pendaftaran: ' || E.NO_DAFTAR || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' ||'1/1 bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD  = '06' THEN F.KETERANGAN || ' - ' || E.JENAMA || '<br>' || 'No Peti: ' || E.NO_DAFTAR || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD  = '07' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD = '08' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD = '09' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD = '10' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD = '11' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD = '99' THEN F.KETERANGAN || ' - ' || E.BUTIRAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD = '98' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NOT NULL AND E.CATATAN <> ' ' AND F.KOD  = '04' THEN F.KETERANGAN || ' - ' || E.NO_DAFTAR || '<br>' || 'No Lot: ' || E.JENAMA || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD = '02' THEN F.KETERANGAN ||' - '|| E.JENAMA || '<br>' || 'No Akaun: ' || E.NO_DAFTAR || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || '1/1 bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND E.NAMA_SAHAM IS NOT NULL AND F.KOD = '01' THEN F.KETERANGAN || ' - ' || E.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(E.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(E.NO_SIJIL,' - ') || '<br>' || 'Bil. Unit: ' || NVL(TRIM(TO_CHAR(E.BIL_UNIT,'999,999,999')),' - ') || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND E.NAMA_SAHAM IS NULL AND F.KOD = '01' THEN F.KETERANGAN || '<br>' || 'No Ahli: ' || NVL(E.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(E.NO_SIJIL,' - ') || '<br>' || 'Bil. Unit: ' || NVL(TRIM(TO_CHAR(E.BIL_UNIT,'999,999,999')),' - ') || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD  = '05' THEN F.KETERANGAN || ' - ' || E.JENAMA || '<br>' || 'No Polisi: ' || E.NO_DAFTAR || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD  = '03' THEN F.KETERANGAN || ' - ' || E.JENAMA || '<br>' || 'No Pendaftaran: ' || E.NO_DAFTAR || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || '1/1 bhg simati' "+
				" WHEN E.ID_JENISHA='03' or F.KOD  = '03' THEN F.KETERANGAN || ' - ' || E.JENAMA || '<br>' || 'No Pendaftaran: ' || E.NO_DAFTAR || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || '1/1 bhg simati' || '<br>' || '(' || E.CATATAN || ')' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD  = '06' THEN F.KETERANGAN || ' - ' || E.JENAMA || '<br>' ||'No Peti: ' || E.NO_DAFTAR || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD  = '07' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD = '08' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD = '09' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD = '10' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD = '11' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD = '99' THEN F.KETERANGAN || ' - ' || E.BUTIRAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD = '98' THEN F.KETERANGAN || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" WHEN REPLACE(REPLACE(REPLACE(REPLACE(E.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') IS NULL AND F.KOD  = '04' THEN F.KETERANGAN || ' - ' || E.NO_DAFTAR || '<br>' || 'No Lot: ' || E.JENAMA || '<br>' || 'RM ' || REPLACE(TO_CHAR(E.NILAI_HA_TARIKHMOHON,'999,999,999.99'),' ') || '<br>' || E.BA_SIMATI || ' / ' || E.BB_SIMATI || ' bhg simati' "+
				" END AS MAKLUMAT_HA "+
				" FROM    TBLPPKPERINTAHHAOBMST D, "+
				" TBLPPKHAPERMOHONAN E, "+
				" TBLPPKRUJJENISHA F "+
				" WHERE D.ID_JENISPERINTAH IN (1,7) "+
				" AND E.ID_HA       = D.ID_HA "+
				" AND F.ID_JENISHA  = E.ID_JENISHA "+
				" AND D.ID_PERINTAH = '"+ID_PERINTAH+"' "+
				" AND E.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
				" ORDER BY D.ID_PERINTAHHAOBMST ASC) MN ";
		myLogger.info(" BICARA INTERAKTIF : SQL rekodHABorangE :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("ID_PERINTAHHAOBMST",rs == null ? "" :rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
			h.put("ID_HA",rs == null ? "" :rs.getString("ID_HA") == null ? "" : rs.getString("ID_HA"));
			h.put("CATATAN_HARTA",rs == null ? "" :rs.getString("CATATAN_HARTA") == null ? "" : rs.getString("CATATAN_HARTA"));
			h.put("MAKLUMAT_HA",rs == null ? "" :rs.getString("MAKLUMAT_HA") == null ? "" : rs.getString("MAKLUMAT_HA"));


			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List rekodHTABorangESubReport1(HttpSession session,String type, String ID_PERINTAHHTAOBMST,
			String STATUS_PEMILIKAN,String NAMA_SIMATI,String KP_SIMATI,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();
		sql += " SELECT " +
				" A.BA AS BA_WARIS, A.BB AS BB_WARIS," +
				" B.JENIS_WARGA, "+
				" A.STATUS_TADBIR, A.ID_PA1, "+
				" A.ID_PA2, A.ID_PA3,B.ID_TARAFKPTG, "+
				" A.ID_PA4, B.NAMA_OB, B.NO_KP_BARU, "+
				" B.NO_KP_LAMA, B.NO_KP_LAIN, B.JENIS_WARGA, "+
				" A.ID_PERINTAH"+type+"OBDTL, "+
				" CASE "+
				" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NOT NULL THEN B.NO_KP_LAMA "+
				" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL THEN B.NO_KP_LAIN "+
				" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NOT NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
				" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
				" END  AS NO_KP, "+
				" CASE "+
				" WHEN B.JENIS_WARGA IS NULL THEN '' "+
				" WHEN B.JENIS_WARGA = 1 THEN 'MALAYSIA' "+
				" WHEN B.JENIS_WARGA = 2 AND B.NAMA_WARGA IS NOT NULL THEN 'BUKAN WARGANEGARA (' || B.NAMA_WARGA || ')' "+
				" WHEN B.JENIS_WARGA = 2 AND  B.NAMA_WARGA IS NULL THEN 'BUKAN WARGANEGARA' "+
				" WHEN B.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP' "+
				" END AS WARGANEGARA, "+
				" (SELECT COUNT(*) "+
				" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOB B  "+
				" WHERE B.ID_OB = A.ID_OB "+
				" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
				" AND A.ID_PA1 IS NULL) AS singleWaris "+
				" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOB B "+
				" WHERE B.ID_OB = A.ID_OB "+
				" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
				" AND A.BA_WARIS != 0 "+
				" AND A.ID_PA1 IS NULL ";
		myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport1 :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);

			h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
			h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
			h.put("JENIS_WARGA",rs == null ? "" :rs.getString("JENIS_WARGA") == null ? "" : rs.getString("JENIS_WARGA"));
			h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
			h.put("ID_PA1",rs == null ? "" :rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
			h.put("ID_PA2",rs == null ? "" :rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
			h.put("ID_PA3",rs == null ? "" :rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
			h.put("ID_PA4",rs == null ? "" :rs.getString("ID_PA4") == null ? "" : rs.getString("ID_PA4"));
			h.put("ID_TARAFKPTG",rs == null ? "" :rs.getString("ID_TARAFKPTG") == null ? "" : rs.getString("ID_TARAFKPTG"));
			h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
			h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
			h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
			h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));

			h.put("ID_PERINTAH"+type+"OBDTL",rs == null ? "" :rs.getString("ID_PERINTAH"+type+"OBDTL") == null ? "" : rs.getString("ID_PERINTAH"+type+"OBDTL"));
			h.put("NO_KP",rs == null ? "" :rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
			h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));

			h.put("STATUS_PEMILIKAN",STATUS_PEMILIKAN == null ? "" :STATUS_PEMILIKAN == null ? "" : STATUS_PEMILIKAN);
			h.put("NAMA_SIMATI",NAMA_SIMATI == null ? "" :NAMA_SIMATI == null ? "" : NAMA_SIMATI);
			h.put("KP_SIMATI",KP_SIMATI == null ? "" :KP_SIMATI == null ? "" : KP_SIMATI);
			h.put("ID_PERINTAH"+type+"OBMST",ID_PERINTAHHTAOBMST == null ? "" :ID_PERINTAHHTAOBMST == null ? "" : ID_PERINTAHHTAOBMST);
			if(type.equals("HTA"))
			{
				h.put("SINGLEWARIS",rs == null ? 0 :rs.getString("SINGLEWARIS") == null ? 0 : rs.getInt("SINGLEWARIS"));
			}
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}



	@SuppressWarnings("unchecked")
	public List rekodHTALantikPentadbir(HttpSession session,String ID_FAIL, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();


		sql += " SELECT  " +
				" TRIM(REGEXP_REPLACE(MN.MAKLUMAT_HTA, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS MAKLUMAT_HTA, " +
				" TRIM(REGEXP_REPLACE(MN.CATATAN, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN, " +
				" MN.* " +
				" FROM (SELECT DISTINCT A.ID_HTA,L.NO_HAKMILIK,SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON ,C.BA_WARIS, C.BB_WARIS, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','')AS CATATAN,L.FLAG_KATEGORI_HTA,L.BA_SIMATI,L.BB_SIMATI,A.ID_JENISPERINTAH, "+
				" CASE   WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HTA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.') || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.') "+
				" END AS SEN_HTA, CASE "+
				" WHEN L.JENIS_HTA = 'Y' THEN M.KOD_JENIS_HAKMILIK || L.NO_HAKMILIK || ' ' || L.NO_PT ||' '|| N.NAMA_MUKIM || ', ' ||  O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA1 IS NULL THEN 'Harta beralamat: -' "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL AND L.ALAMAT_HTA3 IS NULL THEN  'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || chr(10) || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',')|| '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NOT NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',')||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '2' THEN L.NO_ROH || ' ' || L.NO_LOT_ID || '<br>' || L.NAMA_RANCANGAN || ',' || N.NAMA_MUKIM || ',' || O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '3' THEN L.JENIS_KEPENTINGAN "+
				" END AS MAKLUMAT_HTA FROM  TBLPPKPERINTAHHTAOBMST A, TBLPPKPERINTAH B, TBLPPKPERINTAHHTAOBDTL C, TBLPPKOB D, "+
				" TBLPFDFAIL E, TBLPPKPERMOHONAN F, "+
				" TBLPPKKEPUTUSANPERMOHONAN G, "+
				" TBLPPKPERBICARAAN H, "+
				" TBLPPKPERINTAH I, "+
				" TBLRUJBANDAR J, "+
				" TBLRUJNEGERI K, "+
				" TBLPPKHTA L, "+
				" TBLRUJJENISHAKMILIK M, "+
				" TBLRUJMUKIM N, "+
				" TBLRUJDAERAH O, "+
				" TBLPPKSIMATI P, "+
				" TBLPPKPERMOHONANSIMATI Q, "+
				" TBLRUJNEGERI R, "+
				" TBLRUJBANDAR S "+
				" WHERE  "+
				" B.ID_PERINTAH = A.ID_PERINTAH "+
				" AND E.ID_FAIL = F.ID_FAIL "+
				" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
				" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
				" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN "+
				" AND I.ID_PERINTAH = A.ID_PERINTAH "+
				" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
				" AND D.ID_OB = C.ID_PA1 "+
				" AND D.ID_NEGERI = K.ID_NEGERI "+
				" AND D.ID_BANDAR = J.ID_BANDAR(+) "+
				" AND L.ID_HTA = A.ID_HTA "+
				" AND M.ID_JENISHAKMILIK = L.ID_JENISHM "+
				" AND N.ID_MUKIM = L.ID_MUKIM "+
				" AND O.ID_DAERAH = L.ID_DAERAH "+
				" AND P.ID_SIMATI = Q.ID_SIMATI "+
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN "+
				" AND A.ID_JENISPERINTAH = 1 "+
				" AND L.ID_NEGERI = R.ID_NEGERI(+) "+
				" AND L.ID_BANDARHTA = S.ID_BANDAR(+) "+
				" AND C.STATUS_TADBIR = 'Y' "+
				" AND E.ID_FAIL = '"+ID_FAIL+"' "+
				" AND I.FLAG_JENIS_KEPUTUSAN = 0  "+
				" AND A.FLAG_HARTA = 'B' "+
				" AND C.ID_OB IS NULL "+
				" UNION       "+
				" SELECT DISTINCT A.ID_HTA,L.NO_HAKMILIK,SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON ,C.BA_WARIS, C.BB_WARIS, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','')AS CATATAN,L.FLAG_KATEGORI_HTA,L.BA_SIMATI,L.BB_SIMATI,A.ID_JENISPERINTAH, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HTA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')  || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.') "+
				" END AS SEN_HTA, "+
				" CASE "+
				" WHEN L.JENIS_HTA = 'Y' THEN M.KOD_JENIS_HAKMILIK || L.NO_HAKMILIK || ' ' || L.NO_PT ||' '|| N.NAMA_MUKIM || ', ' ||  O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA1 IS NULL THEN 'Harta beralamat: -' "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL AND L.ALAMAT_HTA3 IS NULL THEN  'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',')|| '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NOT NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',')||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '2' THEN L.NO_ROH || ' ' || L.NO_LOT_ID || '<br>' || L.NAMA_RANCANGAN || ',' || N.NAMA_MUKIM || ',' || O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '3' THEN L.JENIS_KEPENTINGAN "+
				" END AS MAKLUMAT_HTA "+
				" FROM       "+
				" TBLPPKPERINTAHHTAOBMST A, "+
				" TBLPPKPERINTAH B,  "+
				" TBLPPKPERINTAHHTAOBDTL C, "+
				" TBLPPKOB D, "+
				" TBLPFDFAIL E, "+
				" TBLPPKPERMOHONAN F, "+
				" TBLPPKKEPUTUSANPERMOHONAN G, "+
				" TBLPPKPERBICARAAN H, "+
				" TBLPPKPERINTAH I, "+
				" TBLRUJBANDAR J, "+
				" TBLRUJNEGERI K, "+
				" TBLPPKHTA L, "+
				" TBLRUJJENISHAKMILIK M, "+
				" TBLRUJMUKIM N, "+
				" TBLRUJDAERAH O, "+
				" TBLPPKSIMATI P, "+
				" TBLPPKPERMOHONANSIMATI Q, "+
				" TBLRUJNEGERI R, "+
				" TBLRUJBANDAR S "+
				" WHERE  "+
				" B.ID_PERINTAH = A.ID_PERINTAH "+
				" AND E.ID_FAIL = F.ID_FAIL "+
				" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
				" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
				" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN "+
				" AND I.ID_PERINTAH = A.ID_PERINTAH "+
				" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
				" AND D.ID_OB = C.ID_PA2 "+
				" AND D.ID_NEGERI = K.ID_NEGERI "+
				" AND D.ID_BANDAR = J.ID_BANDAR(+) "+
				" AND L.ID_HTA = A.ID_HTA "+
				" AND M.ID_JENISHAKMILIK = L.ID_JENISHM "+
				" AND N.ID_MUKIM = L.ID_MUKIM "+
				" AND O.ID_DAERAH = L.ID_DAERAH "+
				" AND P.ID_SIMATI = Q.ID_SIMATI "+
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN "+
				" AND A.ID_JENISPERINTAH = 1 "+
				" AND L.ID_NEGERI = R.ID_NEGERI(+) "+
				" AND L.ID_BANDARHTA = S.ID_BANDAR(+) "+
				" AND C.STATUS_TADBIR = 'Y' "+
				" AND I.FLAG_JENIS_KEPUTUSAN = 0 "+
				" AND E.ID_FAIL = '"+ID_FAIL+"' "+
				" AND A.FLAG_HARTA = 'B' "+
				" AND C.ID_OB IS NULL       "+
				" UNION "+
				" SELECT DISTINCT A.ID_HTA,L.NO_HAKMILIK,SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON ,C.BA_WARIS, C.BB_WARIS, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','')AS CATATAN,L.FLAG_KATEGORI_HTA,L.BA_SIMATI,L.BB_SIMATI,A.ID_JENISPERINTAH, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HTA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.') || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.') "+
				" END AS SEN_HTA, "+
				" CASE "+
				" WHEN L.JENIS_HTA = 'Y' THEN M.KOD_JENIS_HAKMILIK || L.NO_HAKMILIK || ' ' || L.NO_PT ||' '|| N.NAMA_MUKIM || ', ' ||  O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA1 IS NULL THEN 'Harta beralamat: -' "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL AND L.ALAMAT_HTA3 IS NULL THEN  'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',')|| '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NOT NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',')||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '2' THEN L.NO_ROH || ' ' || L.NO_LOT_ID || '<br>' || L.NAMA_RANCANGAN || ',' || N.NAMA_MUKIM || ',' || O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '3' THEN L.JENIS_KEPENTINGAN "+
				" END AS MAKLUMAT_HTA "+
				" FROM "+
				" TBLPPKPERINTAHHTAOBMST A, "+
				" TBLPPKPERINTAH B,  "+
				" TBLPPKPERINTAHHTAOBDTL C, "+
				" TBLPPKOB D, "+
				" TBLPFDFAIL E, "+
				" TBLPPKPERMOHONAN F, "+
				" TBLPPKKEPUTUSANPERMOHONAN G, "+
				" TBLPPKPERBICARAAN H, "+
				" TBLPPKPERINTAH I, "+
				" TBLRUJBANDAR J, "+
				" TBLRUJNEGERI K, "+
				" TBLPPKHTA L, "+
				" TBLRUJJENISHAKMILIK M, "+
				" TBLRUJMUKIM N, "+
				" TBLRUJDAERAH O, "+
				" TBLPPKSIMATI P, "+
				" TBLPPKPERMOHONANSIMATI Q, "+
				" TBLRUJNEGERI R, "+
				" TBLRUJBANDAR S "+
				" WHERE  "+
				" B.ID_PERINTAH = A.ID_PERINTAH "+
				" AND E.ID_FAIL = F.ID_FAIL "+
				" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
				" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
				" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN "+
				" AND I.ID_PERINTAH = A.ID_PERINTAH "+
				" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
				" AND D.ID_OB = C.ID_PA3 "+
				" AND D.ID_NEGERI = K.ID_NEGERI "+
				" AND D.ID_BANDAR = J.ID_BANDAR(+) "+
				" AND L.ID_HTA = A.ID_HTA "+
				" AND M.ID_JENISHAKMILIK = L.ID_JENISHM "+
				" AND N.ID_MUKIM = L.ID_MUKIM "+
				" AND O.ID_DAERAH = L.ID_DAERAH "+
				" AND P.ID_SIMATI = Q.ID_SIMATI "+
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN "+
				" AND A.ID_JENISPERINTAH = 1 "+
				" AND L.ID_NEGERI = R.ID_NEGERI(+) "+
				" AND L.ID_BANDARHTA = S.ID_BANDAR(+) "+
				" AND C.STATUS_TADBIR = 'Y' "+
				" AND E.ID_FAIL = '"+ID_FAIL+"' "+
				" AND I.FLAG_JENIS_KEPUTUSAN = 0 "+
				" AND A.FLAG_HARTA = 'B' "+
				" AND C.ID_OB IS NULL      "+
				" UNION "+
				" SELECT DISTINCT A.ID_HTA,L.NO_HAKMILIK,SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON ,C.BA_WARIS, C.BB_WARIS, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','')AS CATATANN,L.FLAG_KATEGORI_HTA,L.BA_SIMATI,L.BB_SIMATI,A.ID_JENISPERINTAH, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HTA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.') || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.') "+
				" END AS SEN_HTA, "+
				" CASE "+
				" WHEN L.JENIS_HTA = 'Y' THEN M.KOD_JENIS_HAKMILIK || L.NO_HAKMILIK || ' ' || L.NO_PT ||' '|| N.NAMA_MUKIM || ', ' ||  O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA1 IS NULL THEN 'Harta beralamat: -' "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL AND L.ALAMAT_HTA3 IS NULL THEN  'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',')|| '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NOT NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',')||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '2' THEN L.NO_ROH || ' ' || L.NO_LOT_ID || '<br>' || L.NAMA_RANCANGAN || ',' || N.NAMA_MUKIM || ',' || O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '3' THEN L.JENIS_KEPENTINGAN "+
				" END AS MAKLUMAT_HTA "+
				" FROM       "+
				" TBLPPKPERINTAHHTAOBMST A, "+
				" TBLPPKPERINTAH B,  "+
				" TBLPPKPERINTAHHTAOBDTL C, "+
				" TBLPPKOB D, "+
				" TBLPFDFAIL E, "+
				" TBLPPKPERMOHONAN F, "+
				" TBLPPKKEPUTUSANPERMOHONAN G, "+
				" TBLPPKPERBICARAAN H, "+
				" TBLPPKPERINTAH I, "+
				" TBLRUJBANDAR J, "+
				" TBLRUJNEGERI K, "+
				" TBLPPKHTA L, "+
				" TBLRUJJENISHAKMILIK M, "+
				" TBLRUJMUKIM N, "+
				" TBLRUJDAERAH O, "+
				" TBLPPKSIMATI P, "+
				" TBLPPKPERMOHONANSIMATI Q, "+
				" TBLRUJNEGERI R, "+
				" TBLRUJBANDAR S "+
				" WHERE  "+
				" B.ID_PERINTAH = A.ID_PERINTAH "+
				" AND E.ID_FAIL = F.ID_FAIL "+
				" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
				" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
				" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN "+
				" AND I.ID_PERINTAH = A.ID_PERINTAH "+
				" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
				" AND D.ID_OB = C.ID_PA4 "+
				" AND D.ID_NEGERI = K.ID_NEGERI "+
				" AND D.ID_BANDAR = J.ID_BANDAR(+) "+
				" AND L.ID_HTA = A.ID_HTA "+
				" AND M.ID_JENISHAKMILIK = L.ID_JENISHM "+
				" AND N.ID_MUKIM = L.ID_MUKIM "+
				" AND O.ID_DAERAH = L.ID_DAERAH "+
				" AND P.ID_SIMATI = Q.ID_SIMATI "+
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN "+
				" AND L.ID_NEGERI = R.ID_NEGERI(+) "+
				" AND L.ID_BANDARHTA = S.ID_BANDAR(+) "+
				" AND A.ID_JENISPERINTAH = 1 "+
				" AND C.STATUS_TADBIR = 'Y' "+
				" AND I.FLAG_JENIS_KEPUTUSAN = 0 "+
				" AND A.FLAG_HARTA = 'B' "+
				" AND E.ID_FAIL = '"+ID_FAIL+"' "+
				" AND C.ID_OB IS NULL       "+
				" UNION "+
				" SELECT DISTINCT A.ID_HTA,L.NO_HAKMILIK,SUBSTR(TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HTA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HTA_TARIKHMOHON ,C.BA_WARIS, C.BB_WARIS, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','')AS CATATAN,L.FLAG_KATEGORI_HTA,L.BA_SIMATI,L.BB_SIMATI,A.ID_JENISPERINTAH, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HTA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.')  || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HTA_TARIKHMOHON,INSTR(L.NILAI_HTA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HTA_TARIKHMOHON)),'.') "+
				" END AS SEN_HTA, "+
				" CASE "+
				" WHEN L.JENIS_HTA = 'Y' THEN M.KOD_JENIS_HAKMILIK || L.NO_HAKMILIK || ' ' || L.NO_PT ||' '|| N.NAMA_MUKIM || ', ' ||  O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA1 IS NULL THEN 'Harta beralamat: -' "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL AND L.ALAMAT_HTA3 IS NULL THEN  'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA2 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',')|| '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '1' AND L.ALAMAT_HTA3 IS NOT NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(L.ALAMAT_HTA1),',')||', ' || REPLACE(UPPER(L.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(L.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(L.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(L.BANDAR_HTA),',') ||', '|| REPLACE(UPPER(R.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(L.NAMA_PEMAJU) || ' dengan ' || P.NAMA_SIMATI || ' bertarikh ' || TO_CHAR(L.TARIKH_PERJANJIAN,'dd/mm/yyyy') "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '2' THEN L.NO_ROH || ' ' || L.NO_LOT_ID || '<br>' || L.NAMA_RANCANGAN || ',' || N.NAMA_MUKIM || ',' || O.NAMA_DAERAH "+
				" WHEN L.JENIS_HTA = 'T' AND L.FLAG_KATEGORI_HTA = '3' THEN L.JENIS_KEPENTINGAN "+
				" END AS MAKLUMAT_HTA "+
				" FROM       "+
				" TBLPPKPERINTAHHTAOBMST A, "+
				" TBLPPKPERINTAH B,  "+
				" TBLPPKPERINTAHHTAOBDTL C, "+
				" TBLPPKOB D, "+
				" TBLPFDFAIL E, "+
				" TBLPPKPERMOHONAN F, "+
				" TBLPPKKEPUTUSANPERMOHONAN G, "+
				" TBLPPKPERBICARAAN H, "+
				" TBLPPKPERINTAH I, "+
				" TBLRUJBANDAR J, "+
				" TBLRUJNEGERI K, "+
				" TBLPPKHTA L, "+
				" TBLRUJJENISHAKMILIK M, "+
				" TBLRUJMUKIM N, "+
				" TBLRUJDAERAH O, "+
				" TBLPPKSIMATI P, "+
				" TBLPPKPERMOHONANSIMATI Q, "+
				" TBLRUJNEGERI R, "+
				" TBLRUJBANDAR S "+
				" WHERE  "+
				" B.ID_PERINTAH = A.ID_PERINTAH "+
				" AND E.ID_FAIL = F.ID_FAIL "+
				" AND F.ID_PERMOHONAN = G.ID_PERMOHONAN "+
				" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
				" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN "+
				" AND I.ID_PERINTAH = A.ID_PERINTAH "+
				" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST "+
				" AND D.ID_OB = C.ID_OB "+
				" AND D.ID_NEGERI = K.ID_NEGERI "+
				" AND D.ID_BANDAR = J.ID_BANDAR(+) "+
				" AND L.ID_HTA = A.ID_HTA "+
				" AND M.ID_JENISHAKMILIK(+) = L.ID_JENISHM "+
				" AND N.ID_MUKIM = L.ID_MUKIM "+
				" AND O.ID_DAERAH = L.ID_DAERAH "+
				" AND P.ID_SIMATI = Q.ID_SIMATI "+
				" AND F.ID_PERMOHONAN = Q.ID_PERMOHONAN "+
				" AND L.ID_NEGERI = R.ID_NEGERI(+) "+
				" AND L.ID_BANDARHTA = S.ID_BANDAR(+) "+
				" AND A.ID_JENISPERINTAH = 2 "+
				" AND I.FLAG_JENIS_KEPUTUSAN = 0 "+
				" AND A.FLAG_HARTA = 'B' "+
				" AND E.ID_FAIL = '"+ID_FAIL+"') MN ";
		myLogger.info(" BICARA INTERAKTIF : SQL rekodHTALantikPentadbir :"+ sql);



		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);

			h.put("ID_HTA",rs == null ? "" :rs.getString("ID_HTA") == null ? "" : rs.getString("ID_HTA"));
			h.put("NO_HAKMILIK",rs == null ? "" :rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
			h.put("NILAI_HTA_TARIKHMOHON",rs == null ? "" :rs.getString("NILAI_HTA_TARIKHMOHON") == null ? "" : rs.getString("NILAI_HTA_TARIKHMOHON"));
			h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
			h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
			h.put("CATATAN",rs == null ? "" :rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
			h.put("FLAG_KATEGORI_HTA",rs == null ? "" :rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
			h.put("BA_SIMATI",rs == null ? "" :rs.getString("BA_SIMATI") == null ? "" : rs.getString("BA_SIMATI"));
			h.put("BB_SIMATI",rs == null ? "" :rs.getString("BB_SIMATI") == null ? "" : rs.getString("BB_SIMATI"));
			h.put("ID_JENISPERINTAH",rs == null ? "" :rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));
			h.put("SEN_HTA",rs == null ? "" :rs.getString("SEN_HTA") == null ? "" : rs.getString("SEN_HTA"));
			h.put("MAKLUMAT_HTA",rs == null ? "" :rs.getString("MAKLUMAT_HTA") == null ? "" : rs.getString("MAKLUMAT_HTA"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}


	@SuppressWarnings("unchecked")
	public List rekodLiabilitiPentadbir(HttpSession session,String ID_FAIL, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();


		sql += " SELECT TRIM(SUBSTR(TO_CHAR(A.NILAI_HUTANG,'999,999,999.99'),1,LENGTH (TO_CHAR(A.NILAI_HUTANG,'999,999,999.99'))-3 )) AS NILAI_HUTANG, "+
				" CASE "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.BUTIRAN_HUTANG IS NOT NULL AND LENGTH(AAA.NO_KP1)<12 THEN A.NAMA_OB || ' ' || '(' ||RTRIM(AAA.NO_KP1)|| ')' || '<br>' || 'Butiran Hutang: ' || '<br>' || A.BUTIRAN_HUTANG  "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.BUTIRAN_HUTANG IS NOT NULL AND LENGTH(RTRIM(AAA.NO_KP1))=12 THEN A.NAMA_OB || ' ' || '(' || SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4) || ')' || '<br>' || 'Butiran Hutang: ' || '<br>' || A.BUTIRAN_HUTANG "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.BUTIRAN_HUTANG IS NOT NULL AND A.NO_AKAUN IS NOT NULL THEN A.NAMA_OB || '<br>'  || 'No Akaun : ' || REPLACE(REPLACE(A.NO_AKAUN,'/'),'NO. KAVEAT:') || '<br>' || 'Butiran Hutang: ' || '<br>' || A.BUTIRAN_HUTANG "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.NO_AKAUN IS NULL AND A.BUTIRAN_HUTANG IS NOT NULL THEN A.NAMA_OB || '<br>' || 'Butiran Hutang: ' || '<br>' || A.BUTIRAN_HUTANG "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.BUTIRAN_HUTANG IS NOT NULL AND LENGTH(AAA.NO_KP1)<12 AND AAA.NO_KP1 = 'TDK' THEN A.NAMA_OB || '<br>' || 'Butiran Hutang: ' || '<br>' || A.BUTIRAN_HUTANG  "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.BUTIRAN_HUTANG IS NULL AND LENGTH(AAA.NO_KP1)<12 THEN A.NAMA_OB || ' ' || '(' ||RTRIM(AAA.NO_KP1)|| ')'  "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.BUTIRAN_HUTANG IS NULL AND LENGTH(RTRIM(AAA.NO_KP1))=12 THEN A.NAMA_OB || ' ' || '(' || SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4) || ')'  "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.BUTIRAN_HUTANG IS NULL AND A.NO_AKAUN IS NOT NULL THEN A.NAMA_OB || '<br>'  || 'No Akaun : ' || REPLACE(REPLACE(A.NO_AKAUN,'/'),'NO. KAVEAT:') "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.NO_AKAUN IS NULL AND A.BUTIRAN_HUTANG IS NULL THEN A.NAMA_OB  "+
				" WHEN A.NAMA_OB IS NOT NULL AND A.BUTIRAN_HUTANG IS NULL AND LENGTH(AAA.NO_KP1)<12 AND AAA.NO_KP1 = 'TDK' THEN A.NAMA_OB  "+
				" END AS MAKLUMAT_PEMIUTANG, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(A.NILAI_HUTANG,INSTR(A.NILAI_HUTANG,'.'),LENGTH(A.NILAI_HUTANG)),'.')) =  LENGTH(A.NILAI_HUTANG) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(A.NILAI_HUTANG,INSTR(A.NILAI_HUTANG,'.'),LENGTH(A.NILAI_HUTANG)),'.')) = 1 THEN REPLACE(SUBSTR(A.NILAI_HUTANG,INSTR(A.NILAI_HUTANG,'.'),LENGTH(A.NILAI_HUTANG)),'.') ||'0' "+
				" ELSE REPLACE(SUBSTR(A.NILAI_HUTANG,INSTR(A.NILAI_HUTANG,'.'),LENGTH(A.NILAI_HUTANG)),'.') "+
				" END AS SEN_HUTANG "+
				" FROM TBLPPKOB A, "+
				" TBLPPKPERMOHONAN B, "+
				" TBLPPKPERMOHONANSIMATI C, "+
				" TBLPPKSIMATI D,      "+
				" (SELECT  "+
				" CASE  "+
				" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA "+
				" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN "+
				" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA  "+
				" ELSE TBLPPKOB.NO_KP_BARU "+
				" END || '' ||      "+
				" CASE  "+
				" WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA "+
				" END || '' ||      "+
				" CASE  "+
				" WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN    "+
				" END AS NO_KP1, ID_OB, NO_KP_LAMA      "+
				" FROM TBLPPKOB ) AAA           "+
				" WHERE D.ID_SIMATI = A.ID_SIMATI "+
				" AND D.ID_SIMATI = C.ID_SIMATI "+
				" AND A.ID_OB = AAA.ID_OB(+) "+
				" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "+
				" AND A.ID_TARAFKPTG = 2 "+
				" AND B.ID_FAIL = '"+ID_FAIL+"' ";

		myLogger.info(" BICARA INTERAKTIF : SQL rekodLiabilitiPentadbir :"+ sql);



		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());


			h.put("NILAI_HUTANG",rs == null ? "" :rs.getString("NILAI_HUTANG") == null ? "" : rs.getString("NILAI_HUTANG"));
			h.put("SEN_HUTANG",rs == null ? "" :rs.getString("SEN_HUTANG") == null ? "" : rs.getString("SEN_HUTANG"));
			h.put("MAKLUMAT_PEMIUTANG",rs == null ? "" :rs.getString("MAKLUMAT_PEMIUTANG") == null ? "" : rs.getString("MAKLUMAT_PEMIUTANG"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List rekodHALantikPentadbir(HttpSession session,String ID_FAIL, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();

		sql += " SELECT " +
				" TRIM(REGEXP_REPLACE(MN.MAKLUMAT_HA, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS MAKLUMAT_HA, " +
				" TRIM(REGEXP_REPLACE(MN.CATATAN, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN, " +
				" MN.* FROM ( " +
				" SELECT DISTINCT SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','') AS CATATAN, "+
				" TO_CHAR(C.BA_WARIS) BA_WARIS, TO_CHAR(C.BB_WARIS) BB_WARIS, "+
				" TO_CHAR(L.BA_SIMATI) BA_SIMATI, TO_CHAR(L.BB_SIMATI) BB_SIMATI, "+
				" TO_CHAR(A.ID_JENISPERINTAH) ID_JENISPERINTAH, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.') || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.') "+
				" END AS SEN_HA, "+
				" CASE "+
				" WHEN L.ID_JENISHA = '1' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '1' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '1' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '1' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '1' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '1' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NOT NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || L.NAMA_SAHAM || L.CATATAN "+
				" WHEN L.ID_JENISHA = '1' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '1' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '1' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN M.KOD  = '02' THEN M.KETERANGAN ||' - '|| L.JENAMA || '<br>' || 'No Akaun: ' || L.NO_DAFTAR  "+
				" WHEN M.KOD  = '03' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Pendaftaran: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '04' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Lot: ' || L.NO_DAFTAR           "+
				" WHEN M.KOD  = '05' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Polisi: ' || L.NO_DAFTAR || '<br>' || L.CATATAN "+
				" WHEN M.KOD  = '06' THEN M.KETERANGAN || ' - ' || L.JENAMA "+
				" WHEN M.KOD  = '07' OR M.KOD  = '11' THEN M.KETERANGAN || L.CATATAN "+
				" WHEN M.KOD  = '99' THEN L.BUTIRAN "+
				" ELSE L.BUTIRAN "+
				" END AS MAKLUMAT_HA "+
				" FROM    TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAH B, TBLPPKPERINTAHHAOBDTL C, TBLPPKOB D, "+
				" TBLPFDFAIL E, TBLPPKPERMOHONAN F, TBLPPKKEPUTUSANPERMOHONAN G, TBLPPKPERBICARAAN H, "+
				" TBLPPKPERINTAH I, TBLRUJBANDAR J, TBLRUJNEGERI K, TBLPPKHA L, TBLPPKRUJJENISHA M "+
				" WHERE   B.ID_PERINTAH            = A.ID_PERINTAH  AND E.ID_FAIL  = F.ID_FAIL "+
				" AND F.ID_PERMOHONAN          = G.ID_PERMOHONAN AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN "+
				" AND H.ID_PERBICARAAN         = I.ID_PERBICARAAN AND I.ID_PERINTAH            = A.ID_PERINTAH "+
				" AND A.ID_PERINTAHHAOBMST     = C.ID_PERINTAHHAOBMST  AND D.ID_OB                  = C.ID_PA1 "+
				" AND D.ID_NEGERI              = K.ID_NEGERI AND D.ID_BANDAR              = J.ID_BANDAR(+) "+
				" AND L.ID_HA                  = A.ID_HA AND M.ID_JENISHA             = L.ID_JENISHA "+
				" AND A.ID_JENISPERINTAH       = 1 AND C.STATUS_TADBIR          = 'Y' "+
				" AND E.ID_FAIL                = '"+ID_FAIL+"' AND I.FLAG_JENIS_KEPUTUSAN   = 0 "+
				" AND A.FLAG_HARTA             = 'B' "+
				" AND C.ID_OB IS NULL "+
				" UNION "+
				" SELECT DISTINCT SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','')AS CATATAN, "+
				" TO_CHAR(C.BA_WARIS) BA_WARIS,TO_CHAR(C.BB_WARIS) BB_WARIS, TO_CHAR(L.BA_SIMATI) BA_SIMATI, "+
				" TO_CHAR(L.BB_SIMATI) BB_SIMATI, "+
				" TO_CHAR(A.ID_JENISPERINTAH) ID_JENISPERINTAH, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')  || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.') "+
				" END AS SEN_HA, "+
				" CASE "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NOT NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || L.NAMA_SAHAM || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN M.KOD  = '02' THEN M.KETERANGAN ||' - '|| L.JENAMA || '<br>' || 'No Akaun: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '03' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Pendaftaran: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '04' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Lot: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '05' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Polisi: ' || L.NO_DAFTAR || '<br>' || L.CATATAN "+
				" WHEN M.KOD  = '06' THEN M.KETERANGAN || ' - ' || L.JENAMA "+
				" WHEN M.KOD  = '07' OR M.KOD  = '11' THEN M.KETERANGAN || L.CATATAN "+
				" WHEN M.KOD  = '99' THEN L.BUTIRAN "+
				" ELSE L.BUTIRAN "+
				" END AS MAKLUMAT_HA "+
				" FROM    TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAH B, TBLPPKPERINTAHHAOBDTL C, TBLPPKOB D, TBLPFDFAIL E, TBLPPKPERMOHONAN F, "+
				" TBLPPKKEPUTUSANPERMOHONAN G, TBLPPKPERBICARAAN H, TBLPPKPERINTAH I, TBLRUJBANDAR J, TBLRUJNEGERI K, "+
				" TBLPPKHA L,TBLPPKRUJJENISHA M "+
				" WHERE   B.ID_PERINTAH            = A.ID_PERINTAH AND E.ID_FAIL                = F.ID_FAIL "+
				" AND F.ID_PERMOHONAN          = G.ID_PERMOHONAN "+
				" AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN AND H.ID_PERBICARAAN         = I.ID_PERBICARAAN "+
				" AND I.ID_PERINTAH            = A.ID_PERINTAH AND A.ID_PERINTAHHAOBMST     = C.ID_PERINTAHHAOBMST "+
				" AND D.ID_OB                  = C.ID_PA2 AND D.ID_NEGERI              = K.ID_NEGERI "+
				" AND D.ID_BANDAR              = J.ID_BANDAR(+) AND L.ID_HA                  = A.ID_HA "+
				" AND M.ID_JENISHA             = L.ID_JENISHA AND A.ID_JENISPERINTAH       = 1 "+
				" AND C.STATUS_TADBIR          = 'Y' AND E.ID_FAIL                = '"+ID_FAIL+"' "+
				" AND I.FLAG_JENIS_KEPUTUSAN   = 0 AND A.FLAG_HARTA             = 'B' "+
				" AND C.ID_OB IS NULL "+
				" UNION "+
				" SELECT DISTINCT "+
				" SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','')AS CATATAN, "+
				" TO_CHAR(C.BA_WARIS) BA_WARIS, TO_CHAR(C.BB_WARIS) BB_WARIS, TO_CHAR(L.BA_SIMATI) BA_SIMATI, TO_CHAR(L.BB_SIMATI) BB_SIMATI, "+
				" TO_CHAR(A.ID_JENISPERINTAH) ID_JENISPERINTAH, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')  || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.') "+
				" END AS SEN_HA, "+
				" CASE "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NOT NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || L.NAMA_SAHAM || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN M.KOD  = '02' THEN M.KETERANGAN ||' - '|| L.JENAMA || '<br>' || 'No Akaun: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '03' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Pendaftaran: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '04' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Lot: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '05' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Polisi: ' || L.NO_DAFTAR || '<br>' || L.CATATAN "+
				" WHEN M.KOD  = '06' THEN M.KETERANGAN || ' - ' || L.JENAMA "+
				" WHEN M.KOD  = '07' OR M.KOD  = '11' THEN M.KETERANGAN || L.CATATAN "+
				" WHEN M.KOD  = '99' THEN L.BUTIRAN "+
				" ELSE L.BUTIRAN "+
				" END AS MAKLUMAT_HA "+
				" FROM    TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAH B, TBLPPKPERINTAHHAOBDTL C, TBLPPKOB D, "+
				" TBLPFDFAIL E,TBLPPKPERMOHONAN F,TBLPPKKEPUTUSANPERMOHONAN G,TBLPPKPERBICARAAN H,TBLPPKPERINTAH I, "+
				" TBLRUJBANDAR J,TBLRUJNEGERI K,TBLPPKHA L,TBLPPKRUJJENISHA M "+
				" WHERE   B.ID_PERINTAH           = A.ID_PERINTAH "+
				" AND E.ID_FAIL               = F.ID_FAIL AND F.ID_PERMOHONAN         = G.ID_PERMOHONAN "+
				" AND G.ID_KEPUTUSANPERMOHONAN= H.ID_KEPUTUSANPERMOHONAN AND H.ID_PERBICARAAN        = I.ID_PERBICARAAN "+
				" AND I.ID_PERINTAH           = A.ID_PERINTAH AND A.ID_PERINTAHHAOBMST    = C.ID_PERINTAHHAOBMST "+
				" AND D.ID_OB                 = C.ID_PA3 AND D.ID_NEGERI             = K.ID_NEGERI "+
				" AND D.ID_BANDAR             = J.ID_BANDAR(+) AND L.ID_HA                 = A.ID_HA "+
				" AND M.ID_JENISHA            = L.ID_JENISHA AND A.ID_JENISPERINTAH      = 1 "+
				" AND C.STATUS_TADBIR         = 'Y' AND E.ID_FAIL               = '"+ID_FAIL+"' "+
				" AND I.FLAG_JENIS_KEPUTUSAN  = 0 AND A.FLAG_HARTA            = 'B' "+
				" AND C.ID_OB IS NULL "+
				" UNION "+
				" SELECT DISTINCT "+
				" SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','')AS CATATAN, "+
				" TO_CHAR(C.BA_WARIS) BA_WARIS, TO_CHAR(C.BB_WARIS) BB_WARIS, TO_CHAR(L.BA_SIMATI) BA_SIMATI, "+
				" TO_CHAR(L.BB_SIMATI) BB_SIMATI, TO_CHAR(A.ID_JENISPERINTAH) ID_JENISPERINTAH, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')  || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.') "+
				" END AS SEN_HA, "+
				" CASE "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NOT NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || L.NAMA_SAHAM || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN M.KOD  = '02' THEN M.KETERANGAN ||' - '|| L.JENAMA || '<br>' || 'No Akaun: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '03' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Pendaftaran: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '04' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Lot: ' || L.NO_DAFTAR || L.CATATAN "+
				" WHEN M.KOD  = '05' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Polisi: ' || '<br>' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '06' THEN M.KETERANGAN || ' - ' || L.JENAMA "+
				" WHEN M.KOD  = '07' OR M.KOD  = '11' THEN M.KETERANGAN || L.CATATAN "+
				" WHEN M.KOD  = '99' THEN L.BUTIRAN "+
				" ELSE L.BUTIRAN END AS MAKLUMAT_HA "+
				" FROM    TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAH B, TBLPPKPERINTAHHAOBDTL C, "+
				" TBLPPKOB D, TBLPFDFAIL E, TBLPPKPERMOHONAN F, TBLPPKKEPUTUSANPERMOHONAN G, "+
				" TBLPPKPERBICARAAN H, TBLPPKPERINTAH I, TBLRUJBANDAR J, "+
				" TBLRUJNEGERI K, TBLPPKHA L,TBLPPKRUJJENISHA M  "+
				" WHERE   B.ID_PERINTAH           = A.ID_PERINTAH AND E.ID_FAIL               = F.ID_FAIL "+
				" AND F.ID_PERMOHONAN         = G.ID_PERMOHONAN AND G.ID_KEPUTUSANPERMOHONAN= H.ID_KEPUTUSANPERMOHONAN "+
				" AND H.ID_PERBICARAAN        = I.ID_PERBICARAAN AND I.ID_PERINTAH           = A.ID_PERINTAH "+
				" AND A.ID_PERINTAHHAOBMST    = C.ID_PERINTAHHAOBMST AND D.ID_OB                 = C.ID_PA4 "+
				" AND D.ID_NEGERI             = K.ID_NEGERI AND D.ID_BANDAR             = J.ID_BANDAR(+) "+
				" AND L.ID_HA                 = A.ID_HA AND M.ID_JENISHA            = L.ID_JENISHA "+
				" AND A.ID_JENISPERINTAH      = 1 AND C.STATUS_TADBIR         = 'Y' "+
				" AND I.FLAG_JENIS_KEPUTUSAN  = 0 AND A.FLAG_HARTA            = 'B' "+
				" AND E.ID_FAIL               = '"+ID_FAIL+"' "+
				" AND C.ID_OB IS NULL "+
				" UNION "+
				" SELECT DISTINCT "+
				" SUBSTR(TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'),1,LENGTH (TO_CHAR(L.NILAI_HA_TARIKHMOHON,'999,999,999.99'))-3 ) AS NILAI_HA_TARIKHMOHON, "+
				" REPLACE(REPLACE(REPLACE(REPLACE(A.CATATAN,'<br />',''),'&nbsp;',' '),'<p>',''),'</p>','')AS CATATAN, "+
				" TO_CHAR(C.BA_WARIS) BA_WARIS, TO_CHAR(C.BB_WARIS) BB_WARIS, "+
				" TO_CHAR(L.BA_SIMATI) BA_SIMATI, TO_CHAR(L.BB_SIMATI) BB_SIMATI, "+
				" TO_CHAR(A.ID_JENISPERINTAH) ID_JENISPERINTAH, "+
				" CASE "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) =  LENGTH(L.NILAI_HA_TARIKHMOHON) THEN '00' "+
				" WHEN LENGTH(REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')) = 1 THEN REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.')  || '0' "+
				" ELSE REPLACE(SUBSTR(L.NILAI_HA_TARIKHMOHON,INSTR(L.NILAI_HA_TARIKHMOHON,'.'),LENGTH(L.NILAI_HA_TARIKHMOHON)),'.') "+
				" END AS SEN_HA, "+
				" CASE "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.JENAMA IS NOT NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NULL AND L.NAMA_SAHAM IS NOT NULL THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || L.NAMA_SAHAM || L.CATATAN "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NOT NULL AND L.NO_SIJIL IS NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Ahli: ' || NVL(L.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '01' AND L.NAMA_SAHAM IS NULL AND L.NO_DAFTAR IS NULL AND L.NO_SIJIL IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.JENAMA || '<br>' || 'No Sijil: ' || NVL(L.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| L.BIL_UNIT "+
				" WHEN L.ID_JENISHA = '98' AND L.NAMA_SAHAM IS NULL AND L.JENAMA IS NULL AND L.NO_SIJIL IS NULL AND L.NO_DAFTAR IS NOT NULL  THEN M.KETERANGAN ||' - ' || L.NO_DAFTAR  "+
				" WHEN M.KOD  = '02' THEN M.KETERANGAN ||' - '|| L.JENAMA || '<br>' || 'No Akaun: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '03' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Pendaftaran: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '04' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Lot: ' || L.NO_DAFTAR "+
				" WHEN M.KOD  = '05' THEN M.KETERANGAN || ' - ' || L.JENAMA || '<br>' || 'No Polisi: ' || L.NO_DAFTAR || '<br>' || L.CATATAN "+
				" WHEN M.KOD  = '06' THEN M.KETERANGAN || ' - ' || L.JENAMA "+
				" WHEN M.KOD  = '07' OR M.KOD  = '11' THEN M.KETERANGAN || L.CATATAN "+
				" WHEN M.KOD  = '99' THEN L.BUTIRAN "+
				" ELSE L.BUTIRAN "+
				" END AS MAKLUMAT_HA "+
				" FROM    TBLPPKPERINTAHHAOBMST A, "+
				" TBLPPKPERINTAH B, TBLPPKPERINTAHHAOBDTL C, TBLPPKOB D, "+
				" TBLPFDFAIL E,TBLPPKPERMOHONAN F,TBLPPKKEPUTUSANPERMOHONAN G, "+
				" TBLPPKPERBICARAAN H,TBLPPKPERINTAH I,TBLRUJBANDAR J, "+
				" TBLRUJNEGERI K,TBLPPKHA L,TBLPPKRUJJENISHA M "+
				" WHERE   B.ID_PERINTAH               = A.ID_PERINTAH AND E.ID_FAIL                   = F.ID_FAIL "+
				" AND F.ID_PERMOHONAN             = G.ID_PERMOHONAN AND G.ID_KEPUTUSANPERMOHONAN    = H.ID_KEPUTUSANPERMOHONAN "+
				" AND H.ID_PERBICARAAN            = I.ID_PERBICARAAN AND I.ID_PERINTAH               = A.ID_PERINTAH "+
				" AND A.ID_PERINTAHHAOBMST        = C.ID_PERINTAHHAOBMST AND D.ID_OB                     = C.ID_OB "+
				" AND D.ID_NEGERI                 = K.ID_NEGERI AND D.ID_BANDAR                 = J.ID_BANDAR(+) "+
				" AND L.ID_HA                     = A.ID_HA AND M.ID_JENISHA                = L.ID_JENISHA "+
				" AND A.ID_JENISPERINTAH          = 2 AND I.FLAG_JENIS_KEPUTUSAN      = 0 "+
				" AND A.FLAG_HARTA                = 'B' AND E.ID_FAIL                   =  '"+ID_FAIL+"' ) " +
						" MN";

		myLogger.info(" BICARA INTERAKTIF : SQL rekodHALantikPentadbir :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);

			//h.put("ID_HA",rs == null ? "" :rs.getString("ID_HTA") == null ? "" : rs.getString("ID_HTA"));
			h.put("NILAI_HA_TARIKHMOHON",rs == null ? "" :rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : rs.getString("NILAI_HA_TARIKHMOHON"));
			h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
			h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
			h.put("CATATAN",rs == null ? "" :rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
			h.put("BA_SIMATI",rs == null ? "" :rs.getString("BA_SIMATI") == null ? "" : rs.getString("BA_SIMATI"));
			h.put("BB_SIMATI",rs == null ? "" :rs.getString("BB_SIMATI") == null ? "" : rs.getString("BB_SIMATI"));
			h.put("ID_JENISPERINTAH",rs == null ? "" :rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));
			h.put("SEN_HA",rs == null ? "" :rs.getString("SEN_HA") == null ? "" : rs.getString("SEN_HA"));
			h.put("MAKLUMAT_HA",rs == null ? "" :rs.getString("MAKLUMAT_HA") == null ? "" : rs.getString("MAKLUMAT_HA"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}



	//SINGLE_PA
	@SuppressWarnings("unchecked")
	public List rekodHTABorangESubReport2(HttpSession session, String type,String subtype,String ID_PERINTAHHTAOBMST,
			String ID_PERMOHONANSIMATI,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";


		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();
		sql += " SELECT DISTINCT A.ID_PA1, A.STATUS_TADBIR, B.NAMA_OB, B.NO_KP_BARU, "+
				" B.NO_KP_LAMA, B.NO_KP_LAIN, B.JENIS_WARGA,";
				if(!subtype.equals("PA"))
				{
					sql += " A.BA AS BA_WARIS,A.BB AS BB_WARIS,";//aishahlatip close 13/09/2018
				}
				sql += " B.ID_TARAFKPTG, "+
				" CASE "+
				" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NOT NULL THEN B.NO_KP_LAMA "+
				" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL THEN B.NO_KP_LAIN "+
				" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NOT NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
				" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
				" END  AS NO_KP, "+
				" CASE "+
				" WHEN B.JENIS_WARGA IS NULL THEN '' "+
				" WHEN B.JENIS_WARGA = 1 THEN 'MALAYSIA' "+
				" WHEN B.JENIS_WARGA = 2 AND B.NAMA_WARGA IS NOT NULL THEN 'BUKAN WARGANEGARA (' || B.NAMA_WARGA || ')' "+
				" WHEN B.JENIS_WARGA = 2 AND  B.NAMA_WARGA IS NULL THEN 'BUKAN WARGANEGARA' "+
				" WHEN B.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP' "+
				" END AS WARGANEGARA, "+
				" (SELECT COUNT(*) "+
				" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
				" WHERE B.ID_OB = A.ID_OB "+
				" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
				" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
				" AND A.ID_PA1 IS NULL "+
				" AND A.BA_WARIS != 0) AS singleWaris "+
				" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
				" WHERE B.ID_OB = A.ID_PA1 "+
				" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
				" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
				" AND A.ID_PA1 IS NOT NULL "+
				" AND A.ID_PA2 IS NULL ";
				if(subtype.equals("PA"))
				{
					sql += " AND A.ID_OB IS NOT NULL ";
				}
				else
				{
					sql += " AND A.ID_OB IS NULL AND A.STATUS_TADBIR = 'Y' ";
				}
				sql += " AND A.BA_WARIS != 0 ";
		myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport2 ("+subtype+") :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			if(!subtype.equals("PA"))
			{
				h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
			}
			h.put("ID_PA1",rs == null ? "" :rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
			h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
			h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
			h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
			h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
			h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
			h.put("JENIS_WARGA",rs == null ? "" :rs.getString("JENIS_WARGA") == null ? "" : rs.getString("JENIS_WARGA"));
			h.put("ID_TARAFKPTG",rs == null ? "" :rs.getString("ID_TARAFKPTG") == null ? "" : rs.getString("ID_TARAFKPTG"));
			h.put("NO_KP",rs == null ? "" :rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
			h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));
			h.put("SINGLEWARIS",rs == null ? 0 :rs.getString("SINGLEWARIS") == null ? 0 : rs.getInt("SINGLEWARIS"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}


	//2 PA

		@SuppressWarnings("unchecked")
		public List rekodHTABorangESubReport3(HttpSession session, String type,String subtype, String ID_PERINTAHHTAOBMST,
				String ID_PERMOHONANSIMATI,Db db)throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List list = null;
			String sql = "";

			try{

			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT distinct A.ID_PA1,A.ID_PA2, A.STATUS_TADBIR "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL "+
					" AND A.ID_PA2 IS NOT NULL "+
					" AND A.ID_PA3 IS NULL ";
			if(subtype.equals("PA"))
			{
				sql += " AND A.ID_OB IS NOT NULL ";
			}
			else
			{
				sql += " AND A.ID_OB IS NULL AND A.STATUS_TADBIR = 'Y' ";
			}

			sql += " AND (A.ID_PA1 = B.ID_OB OR A.ID_PA2 = B.ID_OB) ";
			myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport3 ("+subtype+") :"+ sql);
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
				h.put("rowCss",rowCss);
				h.put("BIL",bil);

				h.put("ID_PA1",rs == null ? "" :rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
				h.put("ID_PA2",rs == null ? "" :rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
				h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));

				list.add(h);
			}

			} finally {
				if (db == null)
				{
					db1.close();
				}
			}
			return list;
		}

		//3 PA

				@SuppressWarnings("unchecked")
				public List rekodHTABorangESubReport4(HttpSession session,String type, String subtype, String ID_PERINTAHHTAOBMST,
						String ID_PERMOHONANSIMATI,Db db)throws Exception {
					Db db1 = null;
					ResultSet rs = null;
					Statement stmt = null;
					List list = null;
					String sql = "";

					try{

					if(db != null)
					{
						db1 = db;
					}
					else
					{
						db1 = new Db();
					}
					stmt = db1.getStatement();
					sql += " SELECT distinct A.ID_PA1,A.ID_PA2,A.ID_PA3, A.STATUS_TADBIR "+
							" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
							" WHERE A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
							" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
							" AND A.ID_PA1 IS NOT NULL "+
							" AND A.ID_PA2 IS NOT NULL "+
							" AND A.ID_PA3 IS NOT NULL "+
							" AND A.ID_PA4 IS NULL ";

							if(subtype.equals("PA"))
							{
								sql += " AND A.ID_OB IS NOT NULL ";
							}
							else
							{
								sql += " AND A.ID_OB IS NULL AND A.STATUS_TADBIR = 'Y' ";
							}

							sql += " AND (A.ID_PA1 = B.ID_OB OR A.ID_PA2 = B.ID_OB OR A.ID_PA3 = B.ID_OB) ";
					myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport4 ("+subtype+") :"+ sql);
					rs = stmt.executeQuery(sql);
					list = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);

						h.put("ID_PA1",rs == null ? "" :rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
						h.put("ID_PA2",rs == null ? "" :rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
						h.put("ID_PA3",rs == null ? "" :rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
						h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));

						list.add(h);
					}

					} finally {
						if (db == null)
						{
							db1.close();
						}
					}
					return list;
				}


				//3 PA

				@SuppressWarnings("unchecked")
				public List rekodHTABorangESubReport5(HttpSession session,String type, String subtype, String ID_PERINTAHHTAOBMST,
						String ID_PERMOHONANSIMATI,Db db)throws Exception {
					Db db1 = null;
					ResultSet rs = null;
					Statement stmt = null;
					List list = null;
					String sql = "";

					try{

					if(db != null)
					{
						db1 = db;
					}
					else
					{
						db1 = new Db();
					}
					stmt = db1.getStatement();
					sql += " SELECT distinct A.ID_PA1,A.ID_PA2,A.ID_PA3,A.ID_PA4, A.STATUS_TADBIR "+
							" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
							" WHERE A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
							" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
							" AND A.ID_PA1 IS NOT NULL "+
							" AND A.ID_PA2 IS NOT NULL "+
							" AND A.ID_PA3 IS NOT NULL "+
							" AND A.ID_PA4 IS NOT NULL ";

							if(subtype.equals("PA"))
							{
								sql += " AND A.ID_OB IS NOT NULL ";
							}
							else
							{
								sql += " AND A.ID_OB IS NULL AND A.STATUS_TADBIR = 'Y' ";
							}

							sql +=" AND (A.ID_PA1 = B.ID_OB OR A.ID_PA2 = B.ID_OB OR A.ID_PA3 = B.ID_OB OR A.ID_PA4 = B.ID_OB) ";
					myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport5 ("+subtype+") :"+ sql);
					rs = stmt.executeQuery(sql);
					list = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);

						h.put("ID_PA1",rs == null ? "" :rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
						h.put("ID_PA2",rs == null ? "" :rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
						h.put("ID_PA3",rs == null ? "" :rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
						h.put("ID_PA4",rs == null ? "" :rs.getString("ID_PA4") == null ? "" : rs.getString("ID_PA4"));
						h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));

						list.add(h);
					}

					} finally {
						if (db == null)
						{
							db1.close();
						}
					}
					return list;
				}

	//SINGLE_PA
		@SuppressWarnings("unchecked")
		public List rekodHTABorangESubReport2_OnePA(HttpSession session,String type, String ID_PERINTAHHTAOBMST,
				String ID_PERMOHONANSIMATI,String ID_PA1,String STATUS_TADBIR,Db db)throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List list = null;
			String sql = "";

			try{

			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT B.NAMA_OB, B.NO_KP_BARU, B.NO_KP_LAMA,B.NO_KP_LAIN, B.JENIS_WARGA, " +
					" A.BA AS BA_WARIS,A.BB AS BB_WARIS, "+
					" CASE "+
					" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NOT NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || B.NO_KP_LAMA "+
					" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || B.NO_KP_LAIN "+
					" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NOT NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
					" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
					" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NOT NULL THEN '<br>' || 'No. Surat Beranak : ' || B.NO_SURAT_BERANAK "+
					" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NOT NULL THEN '<br>' || 'No. Surat Beranak : ' || B.NO_SURAT_BERANAK "+
					" END  AS NO_KP, "+
					" CASE "+
					" WHEN B.JENIS_WARGA IS NULL THEN '' "+
					" WHEN B.JENIS_WARGA = 1 THEN 'MALAYSIA' "+
					" WHEN B.JENIS_WARGA = 2 AND B.NAMA_WARGA IS NOT NULL THEN 'BUKAN WARGANEGARA (' || B.NAMA_WARGA || ')' "+
					" WHEN B.JENIS_WARGA = 2 AND  B.NAMA_WARGA IS NULL THEN 'BUKAN WARGANEGARA' "+
					" WHEN B.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP' "+
					" END AS WARGANEGARA "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE A.ID_OB = B.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 = '"+ID_PA1+"' "+
					" AND A.BA_WARIS != 0 "+
					" AND A.ID_PA2 IS NULL "+
					" AND A.STATUS_TADBIR = '"+STATUS_TADBIR+"' ";
			myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport2_OnePA :"+ sql);
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
				h.put("rowCss",rowCss);
				h.put("BIL",bil);

				h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
				h.put("JENIS_WARGA",rs == null ? "" :rs.getString("JENIS_WARGA") == null ? "" : rs.getString("JENIS_WARGA"));
				h.put("NO_KP",rs == null ? "" :rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
				h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));
				h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));

				list.add(h);
			}

			} finally {
				if (db == null)
				{
					db1.close();
				}
			}
			return list;
		}



		@SuppressWarnings("unchecked")
		public List rekodHTABorangESubReport3_2PA_MAIN(HttpSession session, String type, String subtype,String ID_PERINTAHHTAOBMST,
				String ID_PERMOHONANSIMATI,String ID_PA1,String ID_PA2,String STATUS_TADBIR,Db db)throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List list = null;
			String sql = "";

			try{

			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT ROWNUM,A.ID_PA1,A.ID_PA2,A.ID_PA3, A.STATUS_TADBIR,B.ID_OB, B.NAMA_OB, B.NO_KP_BARU, "+
					" B.NO_KP_LAMA, B.NO_KP_LAIN, B.JENIS_WARGA, " +
					" A.BA AS BA_WARIS,A.BB AS BB_WARIS," +
					"  (SELECT COUNT(*) "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B WHERE B.ID_OB = A.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NULL AND A.BA_WARIS != 0) AS singleWaris, "+
					" (SELECT COUNT(COUNT(*)) FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE B.ID_OB = A.ID_OB AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL AND A.ID_PA2 IS NULL "+
					" AND A.BA_WARIS != 0 GROUP BY A.STATUS_TADBIR) AS singlePA, "+
					" (SELECT COUNT(*) FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE B.ID_OB = A.ID_OB AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL AND A.ID_PA2 IS NOT NULL "+
					" AND A.ID_PA3 IS NULL) AS doublePA "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL AND A.ID_PA2 IS NOT NULL "+
					" AND A.ID_PA3 IS NULL AND A.ID_PA4 IS NULL "+
					" AND A.BA_WARIS != 0 ";

					if(subtype.equals("PA"))
					{
						sql += " AND A.ID_OB IS NOT NULL";
					}
					else
					{
						sql += " AND A.ID_OB IS NULL AND A.STATUS_TADBIR = 'Y' ";
					}

					sql += " AND (A.ID_PA1 = B.ID_OB OR A.ID_PA2 = B.ID_OB) "+
					" AND ROWNUM  = 1 "+
					" ORDER BY ROWNUM  ";
			myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport3_2PA_MAIN ("+subtype+") :"+ sql);
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
				h.put("ROWNUM",rs == null ? 0 :rs.getString("ROWNUM") == null ? 0 : rs.getInt("ROWNUM"));
				h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("ID_PA1",rs == null ? "" :rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
				h.put("ID_PA2",rs == null ? "" :rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
				h.put("ID_PA3",rs == null ? "" :rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
				h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
				h.put("JENIS_WARGA",rs == null ? "" :rs.getString("JENIS_WARGA") == null ? "" : rs.getString("JENIS_WARGA"));
				//h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));
				h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
				h.put("SINGLEWARIS",rs == null ? 0 :rs.getString("SINGLEWARIS") == null ? 0 : rs.getInt("SINGLEWARIS"));
				h.put("SINGLEPA",rs == null ? 0 :rs.getString("SINGLEPA") == null ? 0 : rs.getInt("SINGLEPA"));
				h.put("DOUBLEPA",rs == null ? 0 :rs.getString("DOUBLEPA") == null ? 0 : rs.getInt("DOUBLEPA"));
				list.add(h);
			}

			} finally {
				if (db == null)
				{
					db1.close();
				}
			}
			return list;
		}



		@SuppressWarnings("unchecked")
		public List rekodHTABorangESubReport4_3PA_MAIN(HttpSession session,String type, String subtype, String ID_PERINTAHHTAOBMST,
				String ID_PERMOHONANSIMATI,String ID_PA1,String ID_PA2,String STATUS_TADBIR,Db db)throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List list = null;
			String sql = "";

			try{

			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT ROWNUM,A.ID_PA1,A.ID_PA2,A.ID_PA3, A.STATUS_TADBIR,B.ID_OB, B.NAMA_OB, B.NO_KP_BARU, "+
					" B.NO_KP_LAMA, B.NO_KP_LAIN, B.JENIS_WARGA," +
					" A.BA AS BA_WARIS,A.BB AS BB_WARIS, " +
					" (SELECT COUNT(*) "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B    WHERE B.ID_OB = A.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"'  AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NULL) AS singleWaris, (SELECT COUNT(COUNT(*)) "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B    WHERE B.ID_OB = A.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL  AND A.ID_PA2 IS NULL AND A.BA_WARIS != 0 "+
					" GROUP BY A.STATUS_TADBIR) AS singlePA, (SELECT COUNT(COUNT(*)) "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B  WHERE B.ID_OB = A.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL  AND A.ID_PA2 IS NOT NULL  AND A.ID_PA3 IS NULL "+
					" AND A.BA_WARIS != 0 GROUP BY A.STATUS_TADBIR) AS doublePA, "+
					" (SELECT COUNT(*) FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE B.ID_OB = A.ID_OB AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' AND A.ID_PA1 IS NOT NULL "+
					" AND A.ID_PA2 IS NOT NULL AND A.ID_PA3 IS NOT NULL "+
					" AND A.ID_PA4 IS NULL ) AS triplePA FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL AND A.ID_PA2 IS NOT NULL "+
					" AND A.ID_PA3 IS NOT NULL  AND A.ID_PA4 IS NULL ";

					if(subtype.equals("PA"))
					{
						sql += " AND A.ID_OB IS NOT NULL ";
					}
					else
					{
						sql += " AND A.ID_OB IS NULL AND A.STATUS_TADBIR = 'Y' ";
					}

			sql += " AND A.BA_WARIS != 0 AND (A.ID_PA1 = B.ID_OB OR A.ID_PA2 = B.ID_OB OR A.ID_PA3 = B.ID_OB)" +
					" AND ROWNUM = 1 "+
					" ORDER BY ROWNUM  ";
			myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport4_3PA_MAIN ("+subtype+") :"+ sql);
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
				h.put("ROWNUM",rs == null ? 0 :rs.getString("ROWNUM") == null ? 0 : rs.getInt("ROWNUM"));
				h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("ID_PA1",rs == null ? "" :rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
				h.put("ID_PA2",rs == null ? "" :rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
				h.put("ID_PA3",rs == null ? "" :rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
				h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
				h.put("JENIS_WARGA",rs == null ? "" :rs.getString("JENIS_WARGA") == null ? "" : rs.getString("JENIS_WARGA"));
				h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
				h.put("SINGLEWARIS",rs == null ? 0 :rs.getString("SINGLEWARIS") == null ? 0 : rs.getInt("SINGLEWARIS"));
				h.put("SINGLEPA",rs == null ? 0 :rs.getString("SINGLEPA") == null ? 0 : rs.getInt("SINGLEPA"));
				h.put("DOUBLEPA",rs == null ? 0 :rs.getString("DOUBLEPA") == null ? 0 : rs.getInt("DOUBLEPA"));
				h.put("TRIPLEPA",rs == null ? 0 :rs.getString("TRIPLEPA") == null ? 0 : rs.getInt("TRIPLEPA"));
				list.add(h);
			}

			} finally {
				if (db == null)
				{
					db1.close();
				}
			}
			return list;
		}

		//4 pa
		@SuppressWarnings("unchecked")
		public List rekodHTABorangESubReport5_4PA_MAIN(HttpSession session,String type, String subtype, String ID_PERINTAHHTAOBMST,
				String ID_PERMOHONANSIMATI,String ID_PA1,String ID_PA2,String STATUS_TADBIR,Db db)throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List list = null;
			String sql = "";

			try{

			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT ROWNUM,A.ID_PA1,A.ID_PA2,A.ID_PA3,A.ID_PA4, A.STATUS_TADBIR,B.ID_OB, B.NAMA_OB, B.NO_KP_BARU, "+
					" B.NO_KP_LAMA, B.NO_KP_LAIN, B.JENIS_WARGA," +
					" A.BA AS BA_WARIS,A.BB AS BB_WARIS, "+
					" (SELECT COUNT(*) "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE B.ID_OB = A.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NULL "+
					" AND A.BA_WARIS != 0) AS singleWaris, "+
					" (SELECT COUNT(COUNT(*)) "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE B.ID_OB = A.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL "+
					" AND A.ID_PA2 IS NULL "+
					" AND A.BA_WARIS != 0 "+
					" GROUP BY A.STATUS_TADBIR) AS singlePA, "+
					" (SELECT COUNT(COUNT(*)) "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE B.ID_OB = A.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL "+
					" AND A.ID_PA2 IS NOT NULL "+
					" AND A.ID_PA3 IS NULL "+
					" AND A.BA_WARIS != 0 "+
					" GROUP BY A.STATUS_TADBIR) AS doublePA, "+
					" (SELECT COUNT(COUNT(*)) "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE B.ID_OB = A.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL "+
					" AND A.ID_PA2 IS NOT NULL "+
					" AND A.ID_PA3 IS NOT NULL "+
					" AND A.ID_PA4 IS NULL "+
					" AND A.BA_WARIS != 0 "+
					" GROUP BY A.STATUS_TADBIR ) AS triplePA, "+
					" (SELECT COUNT(*) "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE B.ID_OB = A.ID_OB "+
					" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL "+
					" AND A.ID_PA2 IS NOT NULL "+
					" AND A.ID_PA3 IS NOT NULL "+
					" AND A.ID_PA4 IS NOT NULL ) as fourPA "+
					" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
					" WHERE A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
					" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND A.ID_PA1 IS NOT NULL "+
					" AND A.ID_PA2 IS NOT NULL "+
					" AND A.ID_PA3 IS NOT NULL "+
					" AND A.ID_PA4 IS NOT NULL "+
					" AND A.BA_WARIS != 0 ";

					if(subtype.equals("PA"))
					{
						sql += " AND A.ID_OB IS NOT NULL ";
					}
					else
					{
						sql += " AND A.ID_OB IS	NULL AND A.STATUS_TADBIR = 'Y' ";
					}

					sql += " AND (A.ID_PA1 = B.ID_OB OR A.ID_PA2 = B.ID_OB OR A.ID_PA3 = B.ID_OB OR A.ID_PA4 = B.ID_OB) "+
					" AND ROWNUM = 1 "+
					" ORDER BY ROWNUM  ";
			myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport5_4PA_MAIN ("+subtype+") :"+ sql);
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
				h.put("ROWNUM",rs == null ? 0 :rs.getString("ROWNUM") == null ? 0 : rs.getInt("ROWNUM"));

				h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));

				h.put("ID_PA1",rs == null ? "" :rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
				h.put("ID_PA2",rs == null ? "" :rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
				h.put("ID_PA3",rs == null ? "" :rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
				h.put("ID_PA4",rs == null ? "" :rs.getString("ID_PA4") == null ? "" : rs.getString("ID_PA4"));
				h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
				h.put("JENIS_WARGA",rs == null ? "" :rs.getString("JENIS_WARGA") == null ? "" : rs.getString("JENIS_WARGA"));
				h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
				h.put("SINGLEWARIS",rs == null ? 0 :rs.getString("SINGLEWARIS") == null ? 0 : rs.getInt("SINGLEWARIS"));
				h.put("SINGLEPA",rs == null ? 0 :rs.getString("SINGLEPA") == null ? 0 : rs.getInt("SINGLEPA"));
				h.put("DOUBLEPA",rs == null ? 0 :rs.getString("DOUBLEPA") == null ? 0 : rs.getInt("DOUBLEPA"));
				h.put("TRIPLEPA",rs == null ? 0 :rs.getString("TRIPLEPA") == null ? 0 : rs.getInt("TRIPLEPA"));
				h.put("FOURPA",rs == null ? 0 :rs.getString("FOURPA") == null ? 0 : rs.getInt("FOURPA"));
				list.add(h);
			}

			} finally {
				if (db == null)
				{
					db1.close();
				}
			}
			return list;
		}


		//double_PA
				@SuppressWarnings("unchecked")
				public List rekodHTABorangESubReport3_2PA_1(HttpSession session, String type, String subtype,String ID_PERINTAHHTAOBMST,
						String ID_PERMOHONANSIMATI,String ID_PA1,String ID_PA2,String STATUS_TADBIR,Db db)throws Exception {
					Db db1 = null;
					ResultSet rs = null;
					Statement stmt = null;
					List list = null;
					String sql = "";

					try{

					if(db != null)
					{
						db1 = db;
					}
					else
					{
						db1 = new Db();
					}
					stmt = db1.getStatement();
					sql += " SELECT ROWNUM, MAIN.* FROM (" +
							"SELECT DISTINCT " +
							//" ROWNUM," +
							" A.STATUS_TADBIR," +
							//" A.ID_OB," +
							"  B.NAMA_OB, B.NO_KP_BARU, "+
							" B.NO_KP_LAMA, B.NO_KP_LAIN, B.JENIS_WARGA,B.ID_TARAFKPTG, "+
							" CASE "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NOT NULL THEN B.NO_KP_LAMA "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL THEN B.NO_KP_LAIN "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NOT NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" END  AS NO_KP, "+
							" CASE "+
							" WHEN B.JENIS_WARGA IS NULL THEN '' "+
							" WHEN B.JENIS_WARGA = 1 THEN 'MALAYSIA' "+
							" WHEN B.JENIS_WARGA = 2 AND B.NAMA_WARGA IS NOT NULL THEN 'BUKAN WARGANEGARA (' || B.NAMA_WARGA || ')' "+
							" WHEN B.JENIS_WARGA = 2 AND  B.NAMA_WARGA IS NULL THEN 'BUKAN WARGANEGARA' "+
							" WHEN B.JENIS_WARGA = 3 THEN 'PENDUDUK TETAP' "+
							" END AS WARGANEGARA "+
							" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
							" WHERE A.ID_PERINTAH"+type+"OBMST =  '"+ID_PERINTAHHTAOBMST+"' AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
							" AND (A.ID_PA1 = B.ID_OB OR A.ID_PA2 = B.ID_OB) AND (A.ID_PA1  = '"+ID_PA1+"' OR A.ID_PA2 = '"+ID_PA2+"') "+
							" AND A.ID_PA1 IS NOT NULL AND A.ID_PA2 IS NOT NULL ";

							if(subtype.equals("PA"))
							{
								sql += " AND A.ID_OB IS NOT NULL";
							}
							else
							{
								sql += " AND A.ID_OB IS NULL AND A.STATUS_TADBIR = 'Y'";
							}

							sql += " AND A.BA_WARIS != 0 " +
									" ) MAIN ";
					myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport3_2PA_1 ("+subtype+") :"+ sql);
					rs = stmt.executeQuery(sql);
					list = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						h.put("ROWNUM",rs == null ? 0 :rs.getString("ROWNUM") == null ? 0 : rs.getInt("ROWNUM"));
						//h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
						h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
						h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
						h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
						h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
						h.put("JENIS_WARGA",rs == null ? "" :rs.getString("JENIS_WARGA") == null ? "" : rs.getString("JENIS_WARGA"));
						h.put("ID_TARAFKPTG",rs == null ? "" :rs.getString("ID_TARAFKPTG") == null ? "" : rs.getString("ID_TARAFKPTG"));
						h.put("NO_KP",rs == null ? "" :rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
						h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));
						h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
						list.add(h);
					}

					} finally {
						if (db == null)
						{
							db1.close();
						}
					}
					return list;
				}

				//double_PA
				@SuppressWarnings("unchecked")
				public List rekodHTABorangESubReport4_3PA_1(HttpSession session, String type, String subtype, String ID_PERINTAHHTAOBMST,
						String ID_PERMOHONANSIMATI,String ID_PA1,String ID_PA2,String ID_PA3,String STATUS_TADBIR,Db db)throws Exception {
					Db db1 = null;
					ResultSet rs = null;
					Statement stmt = null;
					List list = null;
					String sql = "";

					try{

					if(db != null)
					{
						db1 = db;
					}
					else
					{
						db1 = new Db();
					}
					stmt = db1.getStatement();
					sql += " SELECT A.STATUS_TADBIR,A.ID_OB, B.NAMA_OB, B.NO_KP_BARU, "+
							" B.NO_KP_LAMA, B.NO_KP_LAIN, B.JENIS_WARGA, CASE "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NOT NULL THEN B.NO_KP_LAMA "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL THEN B.NO_KP_LAIN "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NOT NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" END  AS NO_KP, "+
							" CASE "+
							" WHEN B.JENIS_WARGA IS NULL THEN '' "+
							" WHEN B.JENIS_WARGA = 1 THEN 'MALAYSIA' "+
							" WHEN B.JENIS_WARGA = 2 AND B.NAMA_WARGA IS NOT NULL THEN 'BUKAN WARGANEGARA (' || B.NAMA_WARGA || ')' "+
							" WHEN B.JENIS_WARGA = 2 AND  B.NAMA_WARGA IS NULL THEN 'BUKAN WARGANEGARA' "+
							" WHEN B.JENIS_WARGA = 3 THEN 'PENDUDUK TETAP' "+
							" END AS WARGANEGARA  "+
							" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
							" WHERE A.ID_PERINTAH"+type+"OBMST =  '"+ID_PERINTAHHTAOBMST+"' AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
							" AND A.ID_PA1 IS NOT NULL AND A.ID_PA2 IS NOT NULL "+
							" AND A.ID_PA3 IS NOT NULL ";
							if(subtype.equals("PA"))
							{
								sql += " AND A.ID_OB IS NOT NULL ";
							}
							else
							{
								sql += " AND A.ID_OB IS NULL AND A.STATUS_TADBIR = 'Y' ";
							}

							sql += " AND A.BA_WARIS != 0 AND (A.ID_PA1 = B.ID_OB OR A.ID_PA2 = B.ID_OB OR A.ID_PA3 = B.ID_OB) "+
							" AND (A.ID_PA1 = '"+ID_PA1+"' OR A.ID_PA2 = '"+ID_PA2+"' OR A.ID_PA3 = '"+ID_PA3+"') ";
					myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport4_3PA_1 :"+ sql);
					rs = stmt.executeQuery(sql);
					list = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						//h.put("ROWNUM",rs == null ? 0 :rs.getString("ROWNUM") == null ? 0 : rs.getInt("ROWNUM"));
						h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
						h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
						h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
						h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
						h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
						h.put("JENIS_WARGA",rs == null ? "" :rs.getString("JENIS_WARGA") == null ? "" : rs.getString("JENIS_WARGA"));
						//h.put("ID_TARAFKPTG",rs == null ? "" :rs.getString("ID_TARAFKPTG") == null ? "" : rs.getString("ID_TARAFKPTG"));
						h.put("NO_KP",rs == null ? "" :rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
						h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));
						h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
						list.add(h);
					}

					} finally {
						if (db == null)
						{
							db1.close();
						}
					}
					return list;
				}




				@SuppressWarnings("unchecked")
				public List rekodHTABorangESubReport5_4PA_1(HttpSession session, String type,String subtype, String ID_PERINTAHHTAOBMST,
						String ID_PERMOHONANSIMATI,String ID_PA1,String ID_PA2,String ID_PA3,String ID_PA4,String STATUS_TADBIR,Db db)throws Exception {
					Db db1 = null;
					ResultSet rs = null;
					Statement stmt = null;
					List list = null;
					String sql = "";

					try{

					if(db != null)
					{
						db1 = db;
					}
					else
					{
						db1 = new Db();
					}
					stmt = db1.getStatement();
					sql += " SELECT A.STATUS_TADBIR,A.ID_OB, B.NAMA_OB, B.NO_KP_BARU, "+
							" B.NO_KP_LAMA, B.NO_KP_LAIN, B.JENIS_WARGA, "+
							" CASE "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NOT NULL THEN B.NO_KP_LAMA "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL THEN B.NO_KP_LAIN "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NOT NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL THEN SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" END  AS NO_KP, "+
							" CASE "+
							" WHEN B.JENIS_WARGA IS NULL THEN '' "+
							" WHEN B.JENIS_WARGA = 1 THEN 'MALAYSIA' "+
							" WHEN B.JENIS_WARGA = 2 AND B.NAMA_WARGA IS NOT NULL THEN 'BUKAN WARGANEGARA (' || B.NAMA_WARGA || ')' "+
							" WHEN B.JENIS_WARGA = 2 AND  B.NAMA_WARGA IS NULL THEN 'BUKAN WARGANEGARA' "+
							" WHEN B.JENIS_WARGA = 3 THEN 'PENDUDUK TETAP' "+
							" END AS WARGANEGARA "+
							" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
							" WHERE A.ID_PERINTAH"+type+"OBMST =  '"+ID_PERINTAHHTAOBMST+"' "+
							" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
							" AND (A.ID_PA1 = B.ID_OB OR A.ID_PA2 = B.ID_OB OR A.ID_PA3 = B.ID_OB OR A.ID_PA4 = B.ID_OB) "+
							" AND (A.ID_PA1  = '"+ID_PA1+"' OR A.ID_PA2 = '"+ID_PA2+"' OR A.ID_PA3 = '"+ID_PA3+"' OR A.ID_PA4 = '"+ID_PA4+"') "+
							" AND A.ID_PA1 IS NOT NULL "+
							" AND A.ID_PA2 IS NOT NULL "+
							" AND A.ID_PA3 IS NOT NULL "+
							" AND A.ID_PA4 IS NOT NULL ";
							if(subtype.equals("PA"))
							{
								sql += " AND A.ID_OB IS NOT NULL ";
							}
							else
							{
								sql += " AND A.ID_OB IS NULL AND A.STATUS_TADBIR = 'Y' ";
							}

							sql += " AND A.BA_WARIS != 0 ";
					myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport5_4PA_1 ("+subtype+") :"+ sql);
					rs = stmt.executeQuery(sql);
					list = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						//h.put("ROWNUM",rs == null ? 0 :rs.getString("ROWNUM") == null ? 0 : rs.getInt("ROWNUM"));
						h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
						h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
						h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
						h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
						h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
						h.put("JENIS_WARGA",rs == null ? "" :rs.getString("JENIS_WARGA") == null ? "" : rs.getString("JENIS_WARGA"));
						//h.put("ID_TARAFKPTG",rs == null ? "" :rs.getString("ID_TARAFKPTG") == null ? "" : rs.getString("ID_TARAFKPTG"));
						h.put("NO_KP",rs == null ? "" :rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
						h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));
						h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
						list.add(h);
					}

					} finally {
						if (db == null)
						{
							db1.close();
						}
					}
					return list;
				}



				public List rekodHTABorangESubReport3_2PA_2(HttpSession session, String type, String ID_PERINTAHHTAOBMST,
						String ID_PERMOHONANSIMATI,String ID_PA1,String ID_PA2,String STATUS_TADBIR,Db db)throws Exception {
					Db db1 = null;
					ResultSet rs = null;
					Statement stmt = null;
					List list = null;
					String sql = "";

					try{

					if(db != null)
					{
						db1 = db;
					}
					else
					{
						db1 = new Db();
					}
					stmt = db1.getStatement();
					sql += " SELECT B.NAMA_OB, B.NO_KP_BARU, B.NO_KP_LAMA, "+
							" B.NO_KP_LAIN, B.JENIS_WARGA, " +
							" A.BA AS BA_WARIS,A.BB AS BB_WARIS, "+
							" CASE "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NOT NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || B.NO_KP_LAMA "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || B.NO_KP_LAIN "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NOT NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NOT NULL THEN '<br>' || 'No. Surat Beranak : ' || B.NO_SURAT_BERANAK "+
							" END  AS NO_KP, "+
							" CASE "+
							" WHEN B.JENIS_WARGA IS NULL THEN '' "+
							" WHEN B.JENIS_WARGA = 1 THEN 'MALAYSIA' "+
							" WHEN B.JENIS_WARGA = 2 AND B.NAMA_WARGA IS NOT NULL THEN 'BUKAN WARGANEGARA (' || B.NAMA_WARGA || ')' "+
							" WHEN B.JENIS_WARGA = 2 AND  B.NAMA_WARGA IS NULL THEN 'BUKAN WARGANEGARA' "+
							" WHEN B.JENIS_WARGA = 3 THEN 'PENDUDUK TETAP' "+
							" END AS WARGANEGARA "+
							" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
							" WHERE A.ID_OB = B.ID_OB "+
							" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
							" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
							" AND A.ID_PA1 = '"+ID_PA1+"' "+
							" AND A.ID_PA2 = '"+ID_PA2+"' "+
							" AND A.BA_WARIS != 0 "+
							" AND A.ID_PA3 IS NULL "+
							" AND A.STATUS_TADBIR = '"+STATUS_TADBIR+"' ";
					myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport3_2PA_2 :"+ sql);
					rs = stmt.executeQuery(sql);
					list = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						//h.put("ROWNUM",rs == null ? 0 :rs.getString("ROWNUM") == null ? 0 : rs.getInt("ROWNUM"));
						//h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
						h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
						h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
						h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
						h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
						h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
						h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
						h.put("NO_KP",rs == null ? "" :rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
						h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));
						//h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
						list.add(h);
					}

					} finally {
						if (db == null)
						{
							db1.close();
						}
					}
					return list;
				}

				public List rekodHTABorangESubReport5_4PA_2(HttpSession session,String type, String ID_PERINTAHHTAOBMST,
						String ID_PERMOHONANSIMATI,String ID_PA1,String ID_PA2,String ID_PA3,String ID_PA4,String STATUS_TADBIR,Db db)throws Exception {
					Db db1 = null;
					ResultSet rs = null;
					Statement stmt = null;
					List list = null;
					String sql = "";

					try{

					if(db != null)
					{
						db1 = db;
					}
					else
					{
						db1 = new Db();
					}
					stmt = db1.getStatement();
					sql += " SELECT B.NAMA_OB, B.NO_KP_BARU, B.NO_KP_LAMA, "+
							" B.NO_KP_LAIN, B.JENIS_WARGA, " +
							" A.BA AS BA_WARIS, A.BB AS BB_WARIS, "+
							" CASE "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NOT NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || B.NO_KP_LAMA "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || B.NO_KP_LAIN "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NOT NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NOT NULL THEN '<br>' || 'No. Surat Beranak : ' || B.NO_SURAT_BERANAK "+
							" END  AS NO_KP, "+
							" CASE "+
							" WHEN B.JENIS_WARGA IS NULL THEN '' "+
							" WHEN B.JENIS_WARGA = 1 THEN 'MALAYSIA' "+
							" WHEN B.JENIS_WARGA = 2 AND B.NAMA_WARGA IS NOT NULL THEN 'BUKAN WARGANEGARA (' || B.NAMA_WARGA || ')' "+
							" WHEN B.JENIS_WARGA = 2 AND  B.NAMA_WARGA IS NULL THEN 'BUKAN WARGANEGARA' "+
							" WHEN B.JENIS_WARGA = 3 THEN 'PENDUDUK TETAP' "+
							" END AS WARGANEGARA "+
							" FROM TBLPPKPERINTAH"+type+"OBDTL A, TBLPPKOBPERMOHONAN B "+
							" WHERE A.ID_OB = B.ID_OB "+
							" AND A.ID_PERINTAH"+type+"OBMST = '"+ID_PERINTAHHTAOBMST+"' "+
							" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
							" AND A.ID_PA1 = '"+ID_PA1+"' "+
							" AND A.ID_PA2 = '"+ID_PA2+"' "+
							" AND A.ID_PA3 = '"+ID_PA3+"' "+
							" AND A.ID_PA4 = '"+ID_PA4+"' "+
							" AND A.BA_WARIS != 0 "+
							" AND A.STATUS_TADBIR = '"+STATUS_TADBIR+"' ";
					myLogger.info(" BICARA INTERAKTIF : SQL rekod"+type+"BorangESubReport5_4PA_2 :"+ sql);
					rs = stmt.executeQuery(sql);
					list = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						//h.put("ROWNUM",rs == null ? 0 :rs.getString("ROWNUM") == null ? 0 : rs.getInt("ROWNUM"));
						//h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
						h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
						h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
						h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
						h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
						h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
						h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
						h.put("NO_KP",rs == null ? "" :rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
						h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));
						//h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
						list.add(h);
					}

					} finally {
						if (db == null)
						{
							db1.close();
						}
					}
					return list;
				}


				public List rekodHTABorangESubReport4_3PA_2(HttpSession session, String ID_PERINTAHHTAOBMST,
						String ID_PERMOHONANSIMATI,String ID_PA1,String ID_PA2,String ID_PA3,String STATUS_TADBIR,Db db)throws Exception {
					Db db1 = null;
					ResultSet rs = null;
					Statement stmt = null;
					List list = null;
					String sql = "";

					try{

					if(db != null)
					{
						db1 = db;
					}
					else
					{
						db1 = new Db();
					}
					stmt = db1.getStatement();
					sql += " SELECT B.NAMA_OB, B.NO_KP_BARU, B.NO_KP_LAMA, "+
							" B.NO_KP_LAIN, B.JENIS_WARGA, " +
							" A.BA AS BA_WARIS, A.BB AS BB_WARIS, "+
							" CASE "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NOT NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || B.NO_KP_LAMA "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || B.NO_KP_LAIN "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NOT NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" WHEN B.NO_KP_BARU IS NOT NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NULL THEN '<br>' || 'No. KP : ' || SUBSTR(B.NO_KP_BARU,1,6) || '-' || SUBSTR(B.NO_KP_BARU,7,2) || '-' || SUBSTR(B.NO_KP_BARU,9,4) "+
							" WHEN B.NO_KP_BARU IS NULL AND B.NO_KP_LAMA IS NULL AND B.NO_SURAT_BERANAK IS NOT NULL THEN '<br>' || 'No. Surat Beranak : ' || B.NO_SURAT_BERANAK "+
							" END  AS NO_KP, "+
							" CASE "+
							" WHEN B.JENIS_WARGA IS NULL THEN '' "+
							" WHEN B.JENIS_WARGA = 1 THEN 'MALAYSIA' "+
							" WHEN B.JENIS_WARGA = 2 AND B.NAMA_WARGA IS NOT NULL THEN 'BUKAN WARGANEGARA (' || B.NAMA_WARGA || ')' "+
							" WHEN B.JENIS_WARGA = 2 AND  B.NAMA_WARGA IS NULL THEN 'BUKAN WARGANEGARA' "+
							" WHEN B.JENIS_WARGA = 3 THEN 'PENDUDUK TETAP' "+
							" END AS WARGANEGARA "+
							" FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKOBPERMOHONAN B "+
							" WHERE A.ID_OB = B.ID_OB "+
							" AND A.ID_PERINTAHHTAOBMST = '"+ID_PERINTAHHTAOBMST+"' "+
							" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
							" AND A.ID_PA1 = '"+ID_PA1+"' "+
							" AND A.ID_PA2 = '"+ID_PA2+"' "+
							" AND A.ID_PA3 = '"+ID_PA3+"' "+
							" AND A.BA_WARIS != 0 "+
							" AND A.ID_PA4 IS NULL "+
							" AND A.STATUS_TADBIR = '"+STATUS_TADBIR+"' ";
					myLogger.info(" BICARA INTERAKTIF : SQL rekodHTABorangESubReport4_3PA_2 :"+ sql);
					rs = stmt.executeQuery(sql);
					list = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						//h.put("ROWNUM",rs == null ? 0 :rs.getString("ROWNUM") == null ? 0 : rs.getInt("ROWNUM"));
						//h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
						h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
						h.put("NO_KP_BARU",rs == null ? "" :rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
						h.put("NO_KP_LAMA",rs == null ? "" :rs.getString("NO_KP_LAMA") == null ? "" : rs.getString("NO_KP_LAMA"));
						h.put("NO_KP_LAIN",rs == null ? "" :rs.getString("NO_KP_LAIN") == null ? "" : rs.getString("NO_KP_LAIN"));
						h.put("BA_WARIS",rs == null ? "" :rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
						h.put("BB_WARIS",rs == null ? "" :rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
						h.put("NO_KP",rs == null ? "" :rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
						h.put("WARGANEGARA",rs == null ? "" :rs.getString("WARGANEGARA") == null ? "" : rs.getString("WARGANEGARA"));
						//h.put("STATUS_TADBIR",rs == null ? "" :rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
						list.add(h);
					}

					} finally {
						if (db == null)
						{
							db1.close();
						}
					}
					return list;
				}

	@SuppressWarnings("unchecked")
	public List listSubPerubahan(HttpSession session,String id_sejarahbimain,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();
		sql += "SELECT * " +
				" FROM TBLPPKSEJARAHBISUB S WHERE S.ID_SEJARAHBIMAIN = '"+id_sejarahbimain+"' ORDER BY S.TURUTAN ";
		myLogger.info(" BICARA INTERAKTIF : SQL listMainPerubahan :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("ID_SEJARAHBISUB",rs == null ? "" :rs.getString("ID_SEJARAHBISUB") == null ? "" : rs.getString("ID_SEJARAHBISUB"));
			h.put("ID_SEJARAHBIMAIN",rs == null ? "" :rs.getString("ID_SEJARAHBIMAIN") == null ? "" : rs.getString("ID_SEJARAHBIMAIN"));
			h.put("REKOD_LABEL",rs == null ? "" :rs.getString("REKOD_LABEL") == null ? "" : rs.getString("REKOD_LABEL"));
			h.put("NAMA_FIELD",rs == null ? "" :rs.getString("NAMA_FIELD") == null ? "" : rs.getString("NAMA_FIELD"));
			h.put("VALUE_SEBELUM",rs == null ? "" :rs.getString("VALUE_SEBELUM") == null ? "" : rs.getString("VALUE_SEBELUM"));
			h.put("KETERANGAN_SEBELUM",rs == null ? "" :rs.getString("KETERANGAN_SEBELUM") == null ? "" : rs.getString("KETERANGAN_SEBELUM"));
			h.put("VALUE_SELEPAS",rs == null ? "" :rs.getString("VALUE_SELEPAS") == null ? "" : rs.getString("VALUE_SELEPAS"));
			h.put("KETERANGAN_SELEPAS",rs == null ? "" :rs.getString("KETERANGAN_SELEPAS") == null ? "" : rs.getString("KETERANGAN_SELEPAS"));
			h.put("TURUTAN",rs == null ? "" :rs.getString("TURUTAN") == null ? "" : rs.getString("TURUTAN"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}

	public void simpanIntroPerintah(HttpSession session, String ID_PERINTAH, String ID_INTROPERINTAH, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String return_id_bikehadiran = "";
			r.update("ID_PERINTAH", ID_PERINTAH);
			r.add("ID_INTROPERINTAH", ID_INTROPERINTAH);
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPPKPERINTAH",db1);
			myLogger.info("INSERT simpanIntroPerintah : "+sql);
			stmt.executeUpdate(sql);
		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}

	public void simpanBantahan(HttpSession session, String ID_PERBICARAAN, String VALUE_FLAG_BANTAHAN, String KETERANGAN_BANTAHAN, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PERBICARAAN", ID_PERBICARAAN);
			r.add("FLAG_BANTAHAN", VALUE_FLAG_BANTAHAN);
			r.add("KETERANGAN_BANTAHAN", KETERANGAN_BANTAHAN.toUpperCase());
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPPKPERBICARAAN",db1);
			myLogger.info("UPDATE simpanBantahan : "+sql);
			stmt.executeUpdate(sql);
		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}



	public List listHistoryjana(HttpSession session, String ID_FAIL,String ID_PERBICARAAN, String ID_SIMATI,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listHistoryjana = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}

		stmt = db1.getStatement();
		sql += " SELECT J.*,  TO_CHAR(J.TARIKH_MASUK,'DD/MM/YYYY HH24:MI:SS') AS TARIKH_TRANSAKSI_FULL, U.USER_NAME AS PENJANA FROM TBLPPKHISTORYJANANOTA J, USERS U "+
				" WHERE J.ID_MASUK = U.USER_ID(+) AND ID_FAIL IN (SELECT DISTINCT P.ID_FAIL FROM TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN P " +
				" WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN  AND PSM.ID_SIMATI = '"+ID_SIMATI+"') ORDER BY J.TARIKH_MASUK DESC ";
		myLogger.info(" BICARA INTERAKTIF : SQL listHistoryjana :"+ sql);

		rs = stmt.executeQuery(sql);
		listHistoryjana = Collections.synchronizedList(new ArrayList());

		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";

			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("ID_HISTORYJANANOTA",rs == null ? "" :rs.getString("ID_HISTORYJANANOTA") == null ? "" : rs.getString("ID_HISTORYJANANOTA"));
			h.put("ID_PERBICARAAN",rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
			h.put("ID_PERINTAH",rs == null ? "" :rs.getString("ID_PERINTAH") == null ? "" : rs.getString("ID_PERINTAH"));
			h.put("NOTA",rs == null ? "" :rs.getString("NOTA") == null ? "" : rs.getString("NOTA"));
			h.put("ID_FAIL",rs == null ? "" :rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
			h.put("NO_FAIL",rs == null ? "" :rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
			h.put("WAKTU_BICARA",rs == null ? "" :rs.getString("WAKTU_BICARA") == null ? "" : rs.getString("WAKTU_BICARA"));
			h.put("NAMA_PEGAWAI",rs == null ? "" :rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI"));
			h.put("BIL_BICARA",rs == null ? "" :rs.getString("BIL_BICARA") == null ? "" : rs.getString("BIL_BICARA"));
			h.put("TARIKH_TRANSAKSI_FULL",rs == null ? "" :rs.getString("TARIKH_TRANSAKSI_FULL") == null ? "" : rs.getString("TARIKH_TRANSAKSI_FULL"));
			h.put("PENJANA",rs == null ? "" :rs.getString("PENJANA") == null ? "" : rs.getString("PENJANA"));
			h.put("NO_PINDAAN",rs == null ? "" :rs.getString("NO_PINDAAN") == null ? "" : rs.getString("NO_PINDAAN"));
			listHistoryjana.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}


		return listHistoryjana;
	}


	public void simpanInsertBorangjDTL(HttpSession session, String ID_BORANGJ, String ID_OB, String id_perbicaraan, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String return_id_bikehadiran = "";
			long id_utama = DB.getNextID(db1, "TBLPPKBORANGJDTL_SEQ");
			r.add("ID_BORANGJDTL", id_utama);
			r.add("ID_BORANGJ", ID_BORANGJ);
			r.add("ID_OB", ID_OB);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPKBORANGJDTL",db1);
			myLogger.info("INSERT TBLPPKBORANGJDTL : "+sql);
			stmt.executeUpdate(sql);
		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}

	public void simpanInsertKolateralDTL(HttpSession session, String ID_KOLATERALMST, String ID_OB, String JENIS_OB, String id_perbicaraan, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		//try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String return_id_bikehadiran = "";
			long id_utama = DB.getNextID(db1, "TBLPPKKOLATERALDTL_SEQ");
			r.add("ID_KOLATERALDTL", id_utama);
			r.add("ID_KOLATERALMST", ID_KOLATERALMST);
			r.add("ID_OB", ID_OB);
			r.add("JENIS_OB", JENIS_OB);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPKKOLATERALDTL",db1);
			myLogger.info("INSERT TBLPPKKOLATERALDTL : "+sql);
			stmt.executeUpdate(sql);
			/*
		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
		*/
	}

	public void simpanKehadiran(HttpSession session, String keterangan, String nota_pegawai, String id_bikehadiran, String id_perbicaraan,
			String nama, String hubungan,
			String pengenalan, String status, String umur,
			String jenis_hadir, String id_hadir, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String return_id_bikehadiran = "";
			if(id_bikehadiran.equals(""))
			{
				long id_utama = DB.getNextID(db1, "TBLPPKBIKEHADIRAN_SEQ");
				return_id_bikehadiran = id_utama+"";
				r.add("ID_BIKEHADIRAN", return_id_bikehadiran);
			}
			else
			{
				return_id_bikehadiran = id_bikehadiran;
			}

			if(!id_bikehadiran.equals(""))
			{
				r.update("ID_BIKEHADIRAN", id_bikehadiran);
			}
			r.add("ID_PERBICARAAN", id_perbicaraan);
			r.add("NAMA", nama.toUpperCase());
			r.add("HUBUNGAN", hubungan.toUpperCase());
			r.add("STATUS", status.toUpperCase());
			r.add("UMUR", umur.toUpperCase());
			r.add("PENGENALAN", pengenalan.toUpperCase());
			r.add("JENIS_HADIR", jenis_hadir);
			r.add("ID_HADIR", id_hadir);

			if(!id_bikehadiran.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPPKBIKEHADIRAN",db1);
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPKBIKEHADIRAN",db1);
			}
			myLogger.info("INSERT TBLPPKBIKEHADIRAN : "+sql);
			stmt.executeUpdate(sql);


			if(!return_id_bikehadiran.equals(""))
			{
				simpanKeterangan(return_id_bikehadiran,keterangan,nota_pegawai,db1);
			}

		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}

	//arief add tidak hadir
	public void simpanKehadiran(HttpSession session, String id_bitidakhadir, String id_perbicaraan,
			String nama, String hubungan,
			String pengenalan, String status, String umur,
			String jenis_tidakhadir, String id_tidakhadir, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String return_id_bitidakhadir = "";
			if(id_bitidakhadir.equals(""))
			{
				long id_utama = DB.getNextID(db1, "TBLPPKBITIDAKHADIR_SEQ");
				return_id_bitidakhadir = id_utama+"";
				r.add("ID_BITIDAKHADIR", return_id_bitidakhadir);
			}
			else
			{
				return_id_bitidakhadir = id_bitidakhadir;
			}

			if(!id_bitidakhadir.equals(""))
			{
				r.update("ID_BITIDAKHADIR", id_bitidakhadir);
			}
			r.add("ID_PERBICARAAN", id_perbicaraan);
			r.add("NAMA", nama.toUpperCase());
			r.add("HUBUNGAN", hubungan.toUpperCase());
			r.add("STATUS", status.toUpperCase());
			r.add("UMUR", umur.toUpperCase());
			r.add("PENGENALAN", pengenalan.toUpperCase());
			r.add("JENIS_TIDAKHADIR", jenis_tidakhadir);
			r.add("ID_TIDAKHADIR", id_tidakhadir);

			if(!id_bitidakhadir.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPPKBITIDAKHADIR",db1);
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPKBITIDAKHADIR",db1);
			}
			myLogger.info("INSERT TBLPPKBITIDAKHADIR : "+sql);
			stmt.executeUpdate(sql);
		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}

	//arief add tidak hadir
	public Map viewTidakHadir(HttpSession session, String id_bitidakhadir, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += queryListTidakHadir("",id_bitidakhadir);
			myLogger.info(" BICARA INTERAKTIF : SQL viewTidakhadir :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());
			while (rs.next()) {
				h = getHashMapTidakHadir(session,rs,"","",db);
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	//arief add tidak hadir
	public String queryListTidakHadir(String id_perbicaraan,String id_bitidakhadir)
	{
		String sql = " SELECT * FROM TBLPPKBITIDAKHADIR KP WHERE KP.ID_BITIDAKHADIR IS NOT NULL ";
		if(!id_perbicaraan.equals(""))
		{
			sql +=" AND KP.JENIS_TIDAKHADIR = 'T' AND KP.ID_PERBICARAAN = '"+id_perbicaraan+"' ";
		}
		if(!id_bitidakhadir.equals(""))
		{
			sql +=" AND KP.ID_BITIDAKHADIR = '"+id_bitidakhadir+"' ";
		}
		sql += "ORDER BY NAMA ";
		return sql;
	}

	//arief add tidak hadir
	public Map getHashMapTidakHadir(HttpSession session, ResultSet rs,String rowCss, String bil, Db db)
			throws Exception
	{
		Map h = Collections.synchronizedMap(new HashMap());
		h.put("rowCss",rowCss);
		h.put("BIL",bil);
		h.put("ID_BITIDAKHADIR",rs == null ? "" :rs.getString("ID_BITIDAKHADIR") == null ? "" : rs.getString("ID_BITIDAKHADIR").toUpperCase());
		h.put("ID_PERBICARAAN",rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN").toUpperCase());
		h.put("NAMA",rs == null ? "" :rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
		h.put("HUBUNGAN",rs == null ? "" :rs.getString("HUBUNGAN") == null ? "" : rs.getString("HUBUNGAN").toUpperCase());
		h.put("STATUS",rs == null ? "" :rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
		h.put("UMUR",rs == null ? "" :rs.getString("UMUR") == null ? "" : rs.getString("UMUR").toUpperCase());
		h.put("PENGENALAN",rs == null ? "" :rs.getString("PENGENALAN") == null ? "" : rs.getString("PENGENALAN").toUpperCase());
		//h.put("CATATAN",rs == null ? "" :rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN").toUpperCase());
		//h.put("KETERANGAN",rs == null ? "" :rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		//h.put("NOTA_PEGAWAI",rs == null ? "" :rs.getString("NOTA_PEGAWAI") == null ? "" : rs.getString("NOTA_PEGAWAI"));
		h.put("JENIS_TIDAKHADIR",rs == null ? "" :rs.getString("JENIS_TIDAKHADIR") == null ? "" : rs.getString("JENIS_TIDAKHADIR").toUpperCase());
		h.put("ID_TIDAKHADIR",rs == null ? "" :rs.getString("ID_TIDAKHADIR") == null ? "" : rs.getString("ID_TIDAKHADIR").toUpperCase());
		return h;

	}

	//arief add
	public String setDataList(HttpSession session,String NamaDataList, String skrinName, String column_name,
			String TABLE_NAME,String PK_FIELD, String KOD_FIELD, Db db)throws Exception {
		String setDataList = "<datalist id = '"+NamaDataList+"' >";
		if(TABLE_NAME.equals("STATUS_OB"))
		{
			setDataList += "<option label='DEWASA / WARAS' value='DEWASA / WARAS' ></option>";
			setDataList += "<option label='TIDAK SEMPURNA AKAL' value='TIDAK SEMPURNA AKAL' ></option>";
		}
		setDataList += "</datalist>";
		return setDataList;
	}

	public void simpanKeterangan(String ID_BIKEHADIRAN,String keterangan,String nota_pegawai,Db db) throws Exception {
		myLogger.info(" ID_BIKEHADIRAN : "+ID_BIKEHADIRAN+"; keterangan : "+keterangan+"; nota_pegawai ");
		Db db1 = null;
  		 try {
  			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
        	Connection con = db1.getConnection();
        	con.setAutoCommit(false);
        	String sqlTBLPPKBIKEHADIRAN = "UPDATE TBLPPKBIKEHADIRAN SET KETERANGAN = ?,NOTA_PEGAWAI = ? WHERE ID_BIKEHADIRAN = ? ";
        	myLogger.info(" sqlTBLPPKBIKEHADIRAN : "+sqlTBLPPKBIKEHADIRAN);
        	PreparedStatement ps = con.prepareStatement(sqlTBLPPKBIKEHADIRAN);
        	ps.setString(1, keterangan);
        	ps.setString(2, nota_pegawai);
        	ps.setString(3, ID_BIKEHADIRAN);
        	ps.executeUpdate();
        	con.commit();
	    }finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}

		}
  }
	//arief add tidak hadir
	public void deleteTidakHadir(HttpSession session,String id_bitidakhadir,String id_perbicaraan, String jenis_tidakhadir,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.clear();
			if(!id_bitidakhadir.equals(""))
			{
				r.add("ID_BITIDAKHADIR",id_bitidakhadir);
			}
			r.add("ID_PERBICARAAN",id_perbicaraan);
			sql = r.getSQLDelete("TBLPPKBITIDAKHADIR");
			myLogger.info("DELETE TBLPPKBITIDAKHADIR : "+sql);
			stmt.executeUpdate(sql);

		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}

	//arief add tidak hadir
	public List listTidakHadir(HttpSession session,String id_permohonansimati,String id_permohonan, String id_perbicaraan,String id_pemohon, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTidakHadir = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}

		stmt = db1.getStatement();
		sql += queryListTidakHadir(id_perbicaraan,"");
		myLogger.info(" BICARA INTERAKTIF : SQL listTidakHadir :"+ sql);
		rs = stmt.executeQuery(sql);
		listTidakHadir = Collections.synchronizedList(new ArrayList());

		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			listTidakHadir.add(getHashMapTidakHadir(session,rs,rowCss,bil+"",db));
		}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return listTidakHadir;
	}

	//arief add tidak hadir
	public List listTidakHadir(HttpSession session,String id_perbicaraan, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTidakHadir = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}

		stmt = db1.getStatement();
		sql += queryListTidakHadir(id_perbicaraan,"");
		myLogger.info(" BICARA INTERAKTIF : SQL listTidakHadir :"+ sql);
		rs = stmt.executeQuery(sql);
		listTidakHadir = Collections.synchronizedList(new ArrayList());

		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			listTidakHadir.add(getHashMapTidakHadir(session,rs,rowCss,bil+"",db));
		}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return listTidakHadir;
	}

	public void simpanKeteranganPerintah(String ID_PERINTAH,String ID_PERBICARAAN,String CATATAN_KEPUTUSAN_PERBICARAAN,String CATATAN,String INTRO_CATATAN,String CATATAN_DOCKIV,Db db) throws Exception {
		myLogger.info(" CATATAN_KEPUTUSAN_PERBICARAAN : "+CATATAN_KEPUTUSAN_PERBICARAAN+"; CATATAN : "+CATATAN+"; simpanKeteranganPerintah ");
		Db db1 = null;
  		 try {
  			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
        	Connection con = db1.getConnection();
        	con.setAutoCommit(false);

        	if(ID_PERINTAH.equals(""))
        	{
        		String updTBLPPKPERBICARAAN =  "UPDATE TBLPPKPERBICARAAN " +/*
	        			" SET CATATAN_KP_TEMP = TRIM(REGEXP_REPLACE(?, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')), " +
	        			" CATATAN_PERINTAH_TEMP = TRIM(REGEXP_REPLACE(?, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')),  " +
	        			" INTRO_CATATAN_TEMP = TRIM(REGEXP_REPLACE(?, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')),  " +
	        			" CATATAN_DOCKIVT = TRIM(REGEXP_REPLACE(?, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' '))  " +
	        			*/
	        			" SET CATATAN_KP_TEMP = ?, " +
	        			" CATATAN_PERINTAH_TEMP = ?,  " +
	        			" INTRO_CATATAN_TEMP = ?,  " +
	        			" CATATAN_DOCKIVT = ?  " +
	        			" WHERE ID_PERBICARAAN = ? ";
	        	PreparedStatement ps = con.prepareStatement(updTBLPPKPERBICARAAN);
	        	ps.setString(1, CATATAN_KEPUTUSAN_PERBICARAAN);
	        	ps.setString(2, CATATAN);
	        	ps.setString(3, INTRO_CATATAN);
	        	ps.setString(4, CATATAN_DOCKIV);
	        	ps.setString(5, ID_PERBICARAAN);
	        	ps.executeUpdate();
        	}
        	else
        	{
        		String updTBLPPKPERINTAH = "UPDATE TBLPPKPERINTAH SET " +
        				"CATATAN_KEPUTUSAN_PERBICARAAN = ?, " +
        				"CATATAN = ?,  " +
        				"INTRO_CATATAN = ?,  " +
        				"CATATAN_DOCKIV = ?  " +
        				"WHERE ID_PERINTAH = ? ";
        		PreparedStatement ps = con.prepareStatement(updTBLPPKPERINTAH);
	        	ps.setString(1, CATATAN_KEPUTUSAN_PERBICARAAN);
	        	ps.setString(2, CATATAN);
	        	ps.setString(3, INTRO_CATATAN);
	        	ps.setString(4, CATATAN_DOCKIV);
	        	ps.setString(5, ID_PERINTAH);
	        	ps.executeUpdate();
        	}
        	con.commit();
	    }finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
  }


	public void updateFlagPemohonByON(HttpSession session, String ID_SEJARAHBIMAIN, String NAMA_FIELD,
			String VALUE_SEBELUM, String KETERANGAN_SEBELUM, String VALUE_SELEPAS, String KETERANGAN_SELEPAS, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_SEJARAHBISUB", r.unquote("(SELECT ID_SEJARAHBISUB FROM TBLPPKSEJARAHBISUB WHERE ID_SEJARAHBIMAIN = '"+ID_SEJARAHBIMAIN+"' AND NAMA_FIELD = '"+NAMA_FIELD+"')"));
			r.add("VALUE_SEBELUM", VALUE_SEBELUM);
			r.add("KETERANGAN_SEBELUM", KETERANGAN_SEBELUM);
			r.add("VALUE_SELEPAS", VALUE_SELEPAS);
			r.add("KETERANGAN_SELEPAS", KETERANGAN_SELEPAS);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPPKSEJARAHBISUB",db1);
			myLogger.info("SQL updateFlagPemohonByON : "+sql);
			stmt.executeUpdate(sql);
		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}

	public void deleteKehadiran(HttpSession session,String id_bikehadiran,String id_perbicaraan, String jenis_hadir,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.clear();
			if(!id_bikehadiran.equals(""))
			{
				r.add("ID_BIKEHADIRAN",id_bikehadiran);
			}
			r.add("ID_PERBICARAAN",id_perbicaraan);
			r.add("JENIS_HADIR",jenis_hadir);
			sql = r.getSQLDelete("TBLPPKBIKEHADIRAN");
			myLogger.info("DELETE TBLPPKBIKEHADIRAN : "+sql);
			stmt.executeUpdate(sql);

		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}


	public void saveKivHistory(HttpSession session,String ID_PERINTAH, String ID_PERBICARAAN, String CHECK_KIV, String DATE_KIV, String CATATAN_KIV, Db db) throws Exception {

		Db db1 = null;
		String sql = "";
		long ID = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");

		try {
			if(db!=null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}

			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			//CODING DI INTERNAL MENGGUNAKAN TBLPPKPERINTAH_SEQ TABLE PERINTAH, SO RAZMAN FOLLOW DLU WALAUPUN SALAH.. KENA BETULKAN INTERNAL DLU LA...
			ID = DB.getNextID(db, "TBLPPKKIV_HIST_SEQ");
			r.add("ID_KIVHISTORY", ID);
			r.add("ID_PERINTAH", ID_PERINTAH);
			r.add("ID_PERBICARAAN", ID_PERBICARAAN);
			DATE_KIV = "to_date('" + DATE_KIV + "','dd/MM/yyyy')";
			r.add("DATE_KIV", r.unquote(DATE_KIV));
			r.add("CATATAN_KIV", CATATAN_KIV);
			r.add("CHECK_KIV", CHECK_KIV);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPKKIV_HIST");
			myLogger.info("BICARA TBLPPKKIV_HIST OT : "+sql);
			stmt.executeUpdate(sql);
			/*
			if(!ID_PERMOHONANBANTUUNIT.equals(""))
			{
				hantarEmel(session,ID_PERMOHONANBANTUUNIT);
				AuditTrail.logActivity(this,session,"UPD","TBLPERMOHONANBANTUUNIT [ID_PERMOHONANBANTUUNIT : "+ID_PERMOHONANBANTUUNIT+"] Updated");

			}
			else
			{
				hantarEmel(session,idBU+"");
				AuditTrail.logActivity(this,session,"INS","TBLPERMOHONANBANTUUNIT [ID_PERMOHONANBANTUUNIT : "+idBU+"] Inserted");
			}
			*/
		}
		finally {
			if (db == null)
				db1.close();
		}
	}
	/*
	public void add_kivHistory(String id_perintah,String id_perbicaraan,String usid,
			String check_kiv,String date_kiv,String catatan_kiv) throws Exception {

			Connection conn = null;
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String sql="";


				try
				{

					db = new Db();
					conn = db.getConnection();

					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();


					String T_date_kiv = "to_date('" + date_kiv + "','dd/MM/yyyy')";

					myLogger.info(":::::::::: BUKA INSERT TBLPPKKIV_HIST");

					PreparedStatement ps = conn.prepareStatement("insert into TBLPPKKIV_HIST " +
		                    "(ID_KIVHISTORY,ID_PERINTAH,ID_PERBICARAAN,DATE_KIV,CHECK_KIV," +
		                    "CATATAN_KIV,ID_MASUK,TARIKH_MASUK) " +
		                    "values(TBLPPKPERINTAH_SEQ.nextval,?,?,"+r.unquote(T_date_kiv)+",?,?,?,sysdate)");
					        ps.setLong(1, id_perintah);
					        ps.setString(2, id_perbicaraan);
					        ps.setString(3, check_kiv);
					        ps.setString(4, catatan_kiv);
					        ps.setString(5, usid);

					        ps.executeUpdate();




			}finally {
				if(db != null) db.close();

			}
	}
	*/

	public void deleteMaklumatBayaranSelesai(HttpSession session,String id_permohonan,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.clear();
			sql = " DELETE FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN IN (24,26,29) AND ID_PERMOHONAN = '"+ id_permohonan +"' ";
			myLogger.info("DELETE TBLPPKBIKEHADIRAN : "+sql);
			stmt.executeUpdate(sql);

		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}

	public void deleteMaklumatBayaranKoleteral(HttpSession session,String id_permohonan,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.clear();
			sql = " DELETE FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN IN (17) AND ID_PERMOHONAN = '"+ id_permohonan +"' ";
			myLogger.info("DELETE TBLPPKBIKEHADIRAN : "+sql);
			stmt.executeUpdate(sql);

		}
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
	}

	public String queryListTurutHadir(String id_perbicaraan,String id_bikehadiran)
	{
		String sql = " SELECT " +
				//"B.KETERANGAN," +
				//"B.NOTA_PEGAWAI " +
				"TRIM(REGEXP_REPLACE(KP.KETERANGAN, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS KETERANGAN, " +
				"TRIM(REGEXP_REPLACE(KP.NOTA_PEGAWAI, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS NOTA_PEGAWAI,  " +
				"KP.* FROM TBLPPKBIKEHADIRAN KP WHERE KP.ID_BIKEHADIRAN IS NOT NULL ";
		if(!id_perbicaraan.equals(""))
		{
			sql +=" AND KP.JENIS_HADIR = 'T' AND KP.ID_PERBICARAAN = '"+id_perbicaraan+"' ";
		}
		if(!id_bikehadiran.equals(""))
		{
			sql +=" AND KP.ID_BIKEHADIRAN = '"+id_bikehadiran+"' ";
		}
		sql += "ORDER BY NAMA ";
		return sql;
	}

	//arief add query list saksi
	public String queryListSaksi(String id_perbicaraan,String id_bikehadiran)
	{
		String sql = " SELECT " +
				"TRIM(REGEXP_REPLACE(KP.KETERANGAN, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS KETERANGAN, " +
				"TRIM(REGEXP_REPLACE(KP.NOTA_PEGAWAI, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS NOTA_PEGAWAI,  " +
				"KP.* FROM TBLPPKBIKEHADIRAN KP WHERE KP.ID_BIKEHADIRAN IS NOT NULL ";
		if(!id_perbicaraan.equals(""))
		{
			sql +=" AND KP.JENIS_HADIR = 'T' AND KP.ID_PERBICARAAN = '"+id_perbicaraan+"' ";
		}
		if(!id_bikehadiran.equals(""))
		{
			sql +=" AND KP.ID_BIKEHADIRAN = '"+id_bikehadiran+"' ";
		}
		sql += "ORDER BY NAMA ";
		return sql;
	}



			@SuppressWarnings("unchecked")
	public Map viewPejagaWaris(HttpSession session, String ID_BIKEHADIRAN, String ID_OBPERMOHONAN,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			/*
			sql += " SELECT KH.ID_BIKEHADIRAN,  OBP.ID_OBPERMOHONAN, PEJAGA.LISTPENJAGA FROM TBLPPKOBPERMOHONAN OBP, "+
					" (SELECT PJ.ID_OBMINOR,  REGEXP_REPLACE(RTRIM (XMLAGG (XMLELEMENT (E, TRIM(UPPER(OB.NAMA_OB)) || ', ')).EXTRACT ('//text()'), ', '),'(,)([^,]*$)',' DAN\\2') AS LISTPENJAGA  "+
					" FROM TBLPPKPENJAGA PJ, TBLPPKOB OB, TBLPPKOBPERMOHONAN OBPP  " +
					" WHERE PJ.ID_OB = OB.ID_OB AND OBPP.ID_OB = OB.ID_OB AND OBPP.ID_OBPERMOHONAN = '"+ID_OBPERMOHONAN.replace("OB", "")+"'  GROUP BY PJ.ID_OBMINOR) PEJAGA, TBLPPKBIKEHADIRAN KH "+
					" WHERE OBP.ID_OB = PEJAGA.ID_OBMINOR(+) "+
					" AND 'OB' || OBP.ID_OBPERMOHONAN = KH.ID_HADIR "+
					" AND KH.ID_BIKEHADIRAN = '"+ID_BIKEHADIRAN+"' "+
					" AND NVL(OBP.UMUR,0) != 0 AND NVL(OBP.UMUR,0) < 18  ";
					*/

			sql +=  " SELECT OBPP.ID_OBPERMOHONAN, PJ.ID_OBMINOR,  " +
					/*" REGEXP_REPLACE(RTRIM (XMLAGG (XMLELEMENT (E, TRIM(UPPER(OB.NAMA_OB)) || ', ')).EXTRACT ('//text()'), ', '),'(,)([^,]*$)',' DAN\2 ')" +
					" || REGEXP_SUBSTR(TRIM (XMLAGG (XMLELEMENT (E, RTRIM(UPPER(OB.NAMA_OB)) || ', ')).EXTRACT ('//text()'), ', '),'([^,]*$)') AS LISTPENJAGA   " +*/
					" CASE WHEN COUNT(DISTINCT OB.ID_OB) > 1 THEN "+
					" REGEXP_REPLACE(RTRIM (XMLAGG (XMLELEMENT (E, TRIM(UPPER(OB.NAMA_OB)) || ', ')).EXTRACT ('//text()'), ', '),'(,)([^,]*$)',' DAN\2')" +
					" || REGEXP_SUBSTR(RTRIM (XMLAGG (XMLELEMENT (E, RTRIM(UPPER(OB.NAMA_OB)) || ', ')).EXTRACT ('//text()'), ', '),'([^,]*$)') " +
					" WHEN COUNT(DISTINCT OB.ID_OB) = 1 THEN MAX(OB.NAMA_OB) END "+
					" AS LISTPENJAGA   "+
					" FROM   " +
					" TBLPPKPENJAGA PJ, TBLPPKOB OB, TBLPPKOB OBP, TBLPPKOBPERMOHONAN OBPP    " +
					" WHERE PJ.ID_OB = OB.ID_OB AND OBP.ID_OB = PJ.ID_OBMINOR AND OBPP.ID_OB = OBP.ID_OB  " +
					" AND OBPP.ID_OBPERMOHONAN = '"+ID_OBPERMOHONAN.replace("OB", "")+"'   " +
					//" AND NVL(OBPP.UMUR,0) != 0 AND NVL(OBPP.UMUR,0) < 18    " +
					" AND (OBPP.STATUS_OB = '2' OR OBPP.STATUS_OB = '4')   " +
					" GROUP BY OBPP.ID_OBPERMOHONAN,PJ.ID_OBMINOR ";

			myLogger.info(" BICARA INTERAKTIF : SQL viewPejagaWaris :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_BIKEHADIRAN",ID_BIKEHADIRAN);
				h.put("ID_OBPERMOHONAN",ID_OBPERMOHONAN.replace("OB", ""));
				h.put("LISTPENJAGA",rs == null ? "" :rs.getString("LISTPENJAGA") == null ? "" : rs.getString("LISTPENJAGA").toUpperCase());
			}

			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	@SuppressWarnings("unchecked")
	public Map viewTuruthadir(HttpSession session, String id_bikehadiran, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += queryListTurutHadir("",id_bikehadiran);
			myLogger.info(" BICARA INTERAKTIF : SQL viewTuruthadir :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());
			while (rs.next()) {
				h = getHashMapTurutHadir(session,rs,"","",db);
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	//arief add view Saksi
	@SuppressWarnings("unchecked")
	public Map viewSaksi(HttpSession session, String id_bikehadiran, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += queryListSaksi("",id_bikehadiran);
			myLogger.info(" BICARA INTERAKTIF : SQL viewSaksi :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());
			while (rs.next()) {
				h = getHashMapSaksi(session,rs,"","",db);
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List listTurutHadir(HttpSession session,String id_perbicaraan, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listKehadiran = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}

		stmt = db1.getStatement();
		sql += queryListTurutHadir(id_perbicaraan,"");
		myLogger.info(" BICARA INTERAKTIF : SQL listTurutHadir :"+ sql);
		rs = stmt.executeQuery(sql);
		listKehadiran = Collections.synchronizedList(new ArrayList());

		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			listKehadiran.add(getHashMapTurutHadir(session,rs,rowCss,bil+"",db));
		}
		} finally {
			/*
			if (rs != null)
			{
				rs.close();
			}
			if (stmt != null)
			{
				stmt.close();
			}
			*/
			if (db == null)
			{
				db1.close();
			}
		}
		return listKehadiran;
	}

	//arief add Saksi
	@SuppressWarnings("unchecked")
	public List listSaksi(HttpSession session,String id_perbicaraan, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listSaksi = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}

		stmt = db1.getStatement();
		sql += queryListSaksi(id_perbicaraan,"");
		myLogger.info(" BICARA INTERAKTIF : SQL listSaksi :"+ sql);
		rs = stmt.executeQuery(sql);
		listSaksi = Collections.synchronizedList(new ArrayList());

		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			listSaksi.add(getHashMapSaksi(session,rs,rowCss,bil+"",db));
		}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return listSaksi;
	}


	public Map getHashMapTurutHadir(HttpSession session, ResultSet rs,String rowCss, String bil, Db db)
			throws Exception
	{
		Map h = Collections.synchronizedMap(new HashMap());
		h.put("rowCss",rowCss);
		h.put("BIL",bil);
		h.put("ID_BIKEHADIRAN",rs == null ? "" :rs.getString("ID_BIKEHADIRAN") == null ? "" : rs.getString("ID_BIKEHADIRAN").toUpperCase());
		h.put("ID_PERBICARAAN",rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN").toUpperCase());
		h.put("NAMA",rs == null ? "" :rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
		h.put("HUBUNGAN",rs == null ? "" :rs.getString("HUBUNGAN") == null ? "" : rs.getString("HUBUNGAN").toUpperCase());
		h.put("STATUS",rs == null ? "" :rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
		h.put("UMUR",rs == null ? "" :rs.getString("UMUR") == null ? "" : rs.getString("UMUR").toUpperCase());
		h.put("PENGENALAN",rs == null ? "" :rs.getString("PENGENALAN") == null ? "" : rs.getString("PENGENALAN").toUpperCase());
		h.put("CATATAN",rs == null ? "" :rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN").toUpperCase());
		h.put("KETERANGAN",rs == null ? "" :rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		h.put("NOTA_PEGAWAI",rs == null ? "" :rs.getString("NOTA_PEGAWAI") == null ? "" : rs.getString("NOTA_PEGAWAI"));
		h.put("JENIS_HADIR",rs == null ? "" :rs.getString("JENIS_HADIR") == null ? "" : rs.getString("JENIS_HADIR").toUpperCase());
		h.put("ID_HADIR",rs == null ? "" :rs.getString("ID_HADIR") == null ? "" : rs.getString("ID_HADIR").toUpperCase());
		return h;

	}

	//arief add saksi
	public Map getHashMapSaksi(HttpSession session, ResultSet rs,String rowCss, String bil, Db db)
			throws Exception
	{
		Map h = Collections.synchronizedMap(new HashMap());
		h.put("rowCss",rowCss);
		h.put("BIL",bil);
		h.put("ID_BIKEHADIRAN",rs == null ? "" :rs.getString("ID_BIKEHADIRAN") == null ? "" : rs.getString("ID_BIKEHADIRAN").toUpperCase());
		h.put("ID_PERBICARAAN",rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN").toUpperCase());
		h.put("NAMA",rs == null ? "" :rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
		h.put("HUBUNGAN",rs == null ? "" :rs.getString("HUBUNGAN") == null ? "" : rs.getString("HUBUNGAN").toUpperCase());
		h.put("STATUS",rs == null ? "" :rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
		h.put("UMUR",rs == null ? "" :rs.getString("UMUR") == null ? "" : rs.getString("UMUR").toUpperCase());
		h.put("PENGENALAN",rs == null ? "" :rs.getString("PENGENALAN") == null ? "" : rs.getString("PENGENALAN").toUpperCase());
		h.put("CATATAN",rs == null ? "" :rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN").toUpperCase());
		h.put("KETERANGAN",rs == null ? "" :rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		h.put("NOTA_PEGAWAI",rs == null ? "" :rs.getString("NOTA_PEGAWAI") == null ? "" : rs.getString("NOTA_PEGAWAI"));
		h.put("JENIS_HADIR",rs == null ? "" :rs.getString("JENIS_HADIR") == null ? "" : rs.getString("JENIS_HADIR").toUpperCase());
		h.put("ID_HADIR",rs == null ? "" :rs.getString("ID_HADIR") == null ? "" : rs.getString("ID_HADIR").toUpperCase());
		return h;

	}


	public String setDisplayHtaah(Map setup,String  flagCP, String currentPrevious,String SEKSYEN)
	{
		String html = "";
		String MAKLUMAT_HTA = (String)setup.get("MAKLUMAT_HTA");
		String NAMA_NEGERI = (String)setup.get("NAMA_NEGERI");
		String NAMA_DAERAH = (String)setup.get("NAMA_DAERAH");
		String NAMA_MUKIM = (String)setup.get("NAMA_MUKIM");
		String NO_HAKMILIK_FULL = (String)setup.get("NO_HAKMILIK_FULL");
		String NO_PT = (String)setup.get("NO_PT");
		String FLAG_PA = (String)setup.get("FLAG_PA");
		String FLAG_PT = (String)setup.get("FLAG_PT");
		String FLAG_SELESAI = (String)setup.get("FLAG_SELESAI");
		html += "<br>";
		if(!MAKLUMAT_HTA.equals(""))
		{
			html +=	""+MAKLUMAT_HTA+"; ";
		}
		if(flagCP.equals(""))
		{
			String tujuanPermohonanHarta = tujuanPermohonanHarta(FLAG_PA,FLAG_PT,FLAG_SELESAI,currentPrevious,SEKSYEN);
			if(!tujuanPermohonanHarta.equals(""))
			{
				html +=	"<br>"+tujuanPermohonanHarta+"; ";
			}
		}
		/*
		if(!NAMA_NEGERI.equals(""))
		{
			html +=	""+NAMA_NEGERI+"; ";
		}
		if(!NAMA_DAERAH.equals(""))
		{
			html +=	""+NAMA_DAERAH+"; ";
		}
		if(!NAMA_MUKIM.equals(""))
		{
			html +=	""+NAMA_MUKIM+"; ";
		}
		if(!NO_HAKMILIK_FULL.equals("") && !NO_HAKMILIK_FULL.equals("- -"))
		{
			html +=	""+NO_HAKMILIK_FULL+"; ";
		}
		if(!NO_PT.equals("") && !NO_PT.equals("-"))
		{
			html +=	""+NO_PT+"; ";
		}
		*/
		html += "";
        if(flagCP.equals(""))
        {
        	html += "<br><br>...<br>";
        }
        else
        {
        	html += "<br>...";
        }
		return html;
	}

	public String setDisplayHtaahx(Map setup,String flagCP, String currentPrevious,String SEKSYEN)
	{
		String html = "";
		String MAKLUMAT_HTA = (String)setup.get("MAKLUMAT_HTA");
		String NAMA_NEGERI = (String)setup.get("NAMA_NEGERI");
		String NAMA_DAERAH = (String)setup.get("NAMA_DAERAH");
		String NAMA_MUKIM = (String)setup.get("NAMA_MUKIM");
		String NO_ROH = (String)setup.get("NO_ROH");
		String NO_PERJANJIAN_FULL = (String)setup.get("NO_PERJANJIAN_FULL");
		String JENIS_KEPENTINGAN = (String)setup.get("JENIS_KEPENTINGAN");
		String FLAG_PA = (String)setup.get("FLAG_PA");
		String FLAG_PT = (String)setup.get("FLAG_PT");
		String FLAG_SELESAI = (String)setup.get("FLAG_SELESAI");
		html += "<br>";
		if(!MAKLUMAT_HTA.equals(""))
		{
			html +=	""+MAKLUMAT_HTA+"; ";
		}
		if(flagCP.equals(""))
		{
			String tujuanPermohonanHarta = tujuanPermohonanHarta(FLAG_PA,FLAG_PT,FLAG_SELESAI,currentPrevious,SEKSYEN);
			if(!tujuanPermohonanHarta.equals(""))
			{
				html +=	"<br>"+tujuanPermohonanHarta+"; ";
			}
		}
		/*
		if(!NAMA_NEGERI.equals(""))
		{
			html +=	""+NAMA_NEGERI+"; ";
		}
		if(!NAMA_DAERAH.equals(""))
		{
			html +=	""+NAMA_DAERAH+"; ";
		}
		if(!NAMA_MUKIM.equals(""))
		{
			html +=	""+NAMA_MUKIM+"; ";
		}
		if(!NO_PERJANJIAN_FULL.equals("") && !NO_PERJANJIAN_FULL.equals(" ") && !NO_PERJANJIAN_FULL.equals("- -"))
		{
			html +=	""+NO_PERJANJIAN_FULL+"; ";
		}
		if(!NO_ROH.equals("") && !NO_ROH.equals("-"))
		{
			html +=	""+NO_ROH+" ";
		}
		if(!JENIS_KEPENTINGAN.equals("") && !JENIS_KEPENTINGAN.equals("-"))
		{
			html +=	"<br>"+JENIS_KEPENTINGAN+"; ";
		}
		*/
		html += "";
		if(flagCP.equals(""))
        {
        	html += "<br><br>...<br>";
        }
        else
        {
        	html += "<br>...";
        }
		return html;
	}

	public String tujuanPermohonanHarta(String FLAG_PA,String FLAG_PT,String FLAG_SELESAI, String currentPrevious, String SEKSYEN)
	{
		String tujuanHarta = "";
		if(currentPrevious.equals("current"))
		{
			if(SEKSYEN.equals("17"))
			{
				tujuanHarta += "HARTA TERTINGGAL";
			}
		}
		else
		{
			if(FLAG_PA.equals("Y"))
			{
				tujuanHarta += "BATAL/LANTIK PEMEGANG AMANAH";
			}
			if(FLAG_PT.equals("Y"))
			{
				if(!tujuanHarta.equals(""))
				{
					tujuanHarta += " DAN ";
				}
				tujuanHarta += "BATAL/LANTIK PEMEGANG SURAT KUASA TADBIR";
			}
		}

		if(!tujuanHarta.equals(""))
		{
			tujuanHarta = "TUJUAN : "+tujuanHarta;
		}

		return tujuanHarta;
	}

	public String setDisplayHa(Map setup,String flagCP, String currentPrevious,String SEKSYEN)
	{
		String html = "";
		String MAKLUMAT_HA = (String)setup.get("MAKLUMAT_HA");
		String FLAG_PA = (String)setup.get("FLAG_PA");
		String FLAG_PT = (String)setup.get("FLAG_PT");
		String FLAG_SELESAI = (String)setup.get("FLAG_SELESAI");
		//String JENIS_HA = (String)setup.get("JENIS_HA");
		//String NO_DAFTAR = (String)setup.get("NO_DAFTAR");
		html += "<br>";
		if(!MAKLUMAT_HA.equals("") && !MAKLUMAT_HA.equals("-"))
		{
			html +=	""+MAKLUMAT_HA+"; ";
		}
		if(flagCP.equals(""))
		{
			String tujuanPermohonanHarta = tujuanPermohonanHarta(FLAG_PA,FLAG_PT,FLAG_SELESAI,currentPrevious,SEKSYEN);
			if(!tujuanPermohonanHarta.equals(""))
			{
				html +=	"<br>"+tujuanPermohonanHarta+"; ";
			}
		}

		/*
		if(!JENIS_HA.equals("") && !JENIS_HA.equals("-"))
		{
			html +=	""+JENIS_HA+"; ";
		}
		if(!NO_DAFTAR.equals("") && !NO_DAFTAR.equals("-"))
		{
			html +=	""+NO_DAFTAR+"; ";
		}
		*/
		html += "";
		if(flagCP.equals(""))
        {
        	html += "<br><br>...<br>";
        }
        else
        {
        	html += "<br>...";
        }
		return html;
	}

	public String defaultListHarta(HttpSession session,String ID_PEMOHON,String ID_SIMATI,String ID_PERBICARAAN,
			String ID_PERMOHONANSIMATI,String ID_PERMOHONAN, String flagCP, Db db, String SEKSYEN)
			throws Exception {
		String html = "";

		List listByRequest_htaah_current =  listByRequest(session,ID_PEMOHON,ID_SIMATI,ID_PERBICARAAN,"htaah", "current",ID_PERMOHONANSIMATI,ID_PERMOHONAN,"Y", db);
		List listByRequest_htaah_previous =  listByRequest(session,ID_PEMOHON,ID_SIMATI,ID_PERBICARAAN,"htaah", "previous",ID_PERMOHONANSIMATI,ID_PERMOHONAN,"Y", db);
		List listByRequest_htaahx_current =  listByRequest(session,ID_PEMOHON,ID_SIMATI,ID_PERBICARAAN,"htaahx", "current",ID_PERMOHONANSIMATI,ID_PERMOHONAN,"Y", db);
		List listByRequest_htaahx_previous =  listByRequest(session,ID_PEMOHON,ID_SIMATI,ID_PERBICARAAN,"htaahx", "previous",ID_PERMOHONANSIMATI,ID_PERMOHONAN,"Y", db);
		List listByRequest_ha_current =  listByRequest(session,ID_PEMOHON,ID_SIMATI,ID_PERBICARAAN,"ha", "current",ID_PERMOHONANSIMATI,ID_PERMOHONAN,"Y", db);
		List listByRequest_ha_previous =  listByRequest(session,ID_PEMOHON,ID_SIMATI,ID_PERBICARAAN,"ha", "previous",ID_PERMOHONANSIMATI,ID_PERMOHONAN,"Y", db);

		if(listByRequest_htaah_current.size()>0 || listByRequest_htaah_previous.size()>0 || listByRequest_htaahx_current.size()>0 || listByRequest_htaahx_previous.size()>0 || listByRequest_ha_current.size()>0 || listByRequest_ha_previous.size()>0)
		{
			//html += "<br>";
		}

		if((listByRequest_htaah_current.size()>0 || listByRequest_htaah_previous.size()>0) && flagCP.equals(""))
		{
			html += "<br><div style=\"border-bottom: 1px solid #000;width:100%;\" ><b><u>HARTA TAK ALIH (ADA HAKMILIK)</u></b></div>";
		}

		if(listByRequest_htaah_current.size()>0)
		{
			for(int i=0; i<listByRequest_htaah_current.size(); i++)
			{
				html += ""+setDisplayHtaah((Map)listByRequest_htaah_current.get(i),flagCP,"current",SEKSYEN)+"";
			}
		}
		if(listByRequest_htaah_previous.size()>0)
		{
			for(int i=0; i<listByRequest_htaah_previous.size(); i++)
			{
				html += ""+setDisplayHtaah((Map)listByRequest_htaah_previous.get(i),flagCP,"previous",SEKSYEN)+"";
			}
		}

		if((listByRequest_htaahx_current.size()>0 || listByRequest_htaahx_previous.size()>0) && flagCP.equals("") )
		{
			html += "<br><div style=\"border-bottom: 1px solid #000;width:100%;\" ><b><u>HARTA TAK ALIH (TIADA HAKMILIK)</u></b></div>";
		}

		if(listByRequest_htaahx_current.size()>0)
		{
			for(int i=0; i<listByRequest_htaahx_current.size(); i++)
			{
				html += ""+setDisplayHtaahx((Map)listByRequest_htaahx_current.get(i),flagCP,"current",SEKSYEN)+"";
			}
		}
		if(listByRequest_htaahx_previous.size()>0)
		{
			for(int i=0; i<listByRequest_htaahx_previous.size(); i++)
			{
				html += ""+setDisplayHtaahx((Map)listByRequest_htaahx_previous.get(i),flagCP,"previous",SEKSYEN)+"";
			}
		}

		if((listByRequest_ha_current.size()>0 || listByRequest_ha_previous.size()>0) && flagCP.equals(""))
		{
			html += "<br><div style=\"border-bottom: 1px solid #000;width:100%;\" ><b><u>HARTA ALIH</u></b></div>";
		}

		if(listByRequest_ha_current.size()>0)
		{
			for(int i=0; i<listByRequest_ha_current.size(); i++)
			{
				html += ""+setDisplayHa((Map)listByRequest_ha_current.get(i),flagCP,"current",SEKSYEN)+"";
			}
		}
		if(listByRequest_ha_previous.size()>0)
		{
			for(int i=0; i<listByRequest_ha_previous.size(); i++)
			{
				html += ""+setDisplayHa((Map)listByRequest_ha_previous.get(i),flagCP,"previous",SEKSYEN)+"";
			}
		}

		return html;
	}

	public List listByRequest(HttpSession session,String ID_PEMOHON,String ID_SIMATI,String ID_PERBICARAAN,String skrinName,
			String current_previous,String ID_PERMOHONANSIMATI,String ID_PERMOHONAN,
			String flagDefaultKeterangan,
			Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";


		List listColumnForSenarai = null;
		//untuk column waris ob saksi pemiutang penghutang
		if(!skrinName.equals("htaah") && !skrinName.equals("htaahx") && !skrinName.equals("ha") && !skrinName.equals("peguam"))
		{
			listColumnForSenarai = listColumnForSenaraiOB(session,skrinName,current_previous);
		}
		else
		{
			if(skrinName.equals("peguam"))
			{
				listColumnForSenarai = listColumnForSenaraiPeguam(session,skrinName,current_previous);
			}
			else if(skrinName.equals("ha"))
			{
				listColumnForSenarai = listColumnForSenaraiHartaAlih(session,skrinName,current_previous);
			}
			else
			{
				listColumnForSenarai = listColumnForSenaraiHarta(session,skrinName,current_previous);
			}
		}

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += queryGetList(session,ID_PEMOHON,ID_SIMATI,ID_PERBICARAAN,skrinName, current_previous, ID_PERMOHONANSIMATI,ID_PERMOHONAN,flagDefaultKeterangan);
			myLogger.info(" BICARA INTERAKTIF : SQL listByRequest :"+ sql);
			rs = stmt.executeQuery(sql);

			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());

				if (listColumnForSenarai.size() != 0) {
					for (int i = 0; i < listColumnForSenarai.size(); i++) {
						Map map_column_name = (Map) listColumnForSenarai.get(i);
						String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
						String ALIGN = (String) map_column_name.get("ALIGN");
						String VALUE = (rs.getString(COLUMN_NAME) == null ? "" : rs.getString(COLUMN_NAME).toUpperCase());
						h.put(COLUMN_NAME,rs == null ? "" :VALUE == null ? "" : VALUE.toUpperCase());
					}
				}
				list.add(h);
			}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}

	public String htmlList(HttpSession session,String ID_PEMOHON,String ID_SIMATI,String ID_PERBICARAAN,String ID_PERMOHONAN,
			String tajukList,String skrinName,String current_previous,String command, String ID_PERMOHONANSIMATI,String formName,Db db) throws Exception {
		String html = "";

		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		String namaList = "list"+skrinName+current_previous;

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}

			stmt = db1.getStatement();
			sql += queryGetList(session,ID_PEMOHON,ID_SIMATI,ID_PERBICARAAN,skrinName, current_previous, ID_PERMOHONANSIMATI,ID_PERMOHONAN,"");
			myLogger.info(" BICARA INTERAKTIF : SQL dynamicList :"+ sql);
			rs = stmt.executeQuery(sql);
			String paramStandard = "ID_PEMOHON="+ID_PEMOHON+"&ID_SIMATI="+ID_SIMATI+"&ID_PERMOHONAN="+ID_PERMOHONAN+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI;
			String paramsAdd = "";
			String divAdd = "div"+skrinName+ID_PERMOHONANSIMATI;
			if(skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("pemiutang") || skrinName.equals("saksi"))
			{
				paramsAdd = paramStandard+"&ID_OBPERMOHONAN=&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKOBPERMOHONAN&FIELD_PK=ID_OBPERMOHONAN&divId="+divAdd;
			}
			else if(skrinName.equals("penghutang"))
			{
				paramsAdd = paramStandard+"&ID_PENGHUTANG=&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKPENGHUTANG&FIELD_PK=ID_PENGHUTANG&divId="+divAdd;
			}
			else if(skrinName.equals("peguam"))
			{
				paramsAdd = paramStandard+"&ID_PEGUAM=&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKPEGUAM&FIELD_PK=ID_PEGUAM&divId="+divAdd;
			}
			else if(skrinName.equals("htaah") || skrinName.equals("htaahx"))
			{
				paramsAdd = paramStandard+"&ID_HTAPERMOHONAN=&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKHTAPERMOHONAN&FIELD_PK=ID_HTAPERMOHONAN&divId="+divAdd;
			}
			else if(skrinName.equals("ha"))
			{
				paramsAdd = paramStandard+"&ID_HAPERMOHONAN=&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKHAPERMOHONAN&FIELD_PK=ID_HAPERMOHONAN&divId="+divAdd;
			}


			tajukList = "";
			if(skrinName.equals("waris"))
			{
				if(current_previous.equals("current"))
				{
					tajukList = "SENARAI WARIS";
				}
				else
				{
					tajukList = "SENARAI WARIS TERDAHULU";
				}
			}
			else if(skrinName.equals("ob"))
			{
				if(current_previous.equals("current"))
				{
					tajukList = "SENARAI ORANG BERKEPENTINGAN";
				}
				else
				{
					tajukList = "SENARAI ORANG BERKEPENTINGAN TERDAHULU";
				}
			}
			else if(skrinName.equals("saksi"))
			{
				if(current_previous.equals("current"))
				{
					tajukList = "SENARAI SAKSI";
				}
				else
				{
					tajukList = "SENARAI SAKSI TERDAHULU";
				}
			}
			else if(skrinName.equals("pemiutang"))
			{
				if(current_previous.equals("current"))
				{
					tajukList = "SENARAI PEMIUTANG";
				}
				else
				{
					tajukList = "SENARAI PEMIUTANG TERDAHULU";
				}
			}
			else if(skrinName.equals("penghutang"))
			{
				if(current_previous.equals("current"))
				{
					tajukList = "SENARAI PENGHUTANG";
				}
				else
				{
					tajukList = "SENARAI PENGHUTANG TERDAHULU";
				}
			}
			else if(skrinName.equals("htaah"))
			{
				if(current_previous.equals("current"))
				{
					tajukList = "SENARAI HARTA TAK ALIH (ADA HAKMILIK)";
				}
				else
				{
					tajukList = "SENARAI HARTA TAK ALIH (ADA HAKMILIK) TERDAHULU";
				}
			}
			else if(skrinName.equals("ha"))
			{
				if(current_previous.equals("current"))
				{
					tajukList = "SENARAI HARTA ALIH";
				}
				else
				{
					tajukList = "SENARAI HARTA ALIH TERDAHULU";
				}
			}
			else if(skrinName.equals("htaahx"))
			{
				if(current_previous.equals("current"))
				{
					tajukList = "SENARAI HARTA TAK ALIH (TIADA HAKMILIK)";
				}
				else
				{
					tajukList = "SENARAI HARTA TAK ALIH (TIADA HAKMILIK) TERDAHULU";
				}
			}
			else if(skrinName.equals("peguam"))
			{
				if(current_previous.equals("current"))
				{
					tajukList = "SENARAI PEGUAM";
				}
			}

			html += "<a class=\"blue\" href=\"javascript:doDivAjaxCall"+formName+"('senarai_"+skrinName+current_previous+"','refreshList','"+paramsAdd+"&current_previous="+current_previous+"&scrolPosition='+getPageLocation());\">&nbsp;<u>'Refresh'</u></a>";

			html += "<fieldset>";
			html += "<div id=\"div"+skrinName+ID_PERMOHONANSIMATI+"\" ></div>";



			if(!tajukList.equals(""))
			{
				html += "" +
						"<table border=\"0\" cellspacing=\"0\" cellpadding=\"3\" width=\"100%\" class=\"tajukList\">" +
						"<tr>" +
						"<td align=\"left\" width=\"70%\"><div ><b>"+tajukList.toUpperCase()+"</b></div></td>" +
						"<td align=\"right\" width=\"30%\">";
				if(current_previous.equals("current"))
				{

					html += "<input type='button' id='cmdAdd"+skrinName+"' name='cmdAdd"+skrinName+"' value='Tambah' onClick=\"setingTrDiv('divId"+skrinName+"','"+divAdd+"');doDivAjaxCall"+formName+"('div"+skrinName+ID_PERMOHONANSIMATI+"','showMaklumat','"+paramsAdd+"&current_previous="+current_previous+"&scrolPosition='+getPageLocation());\" >";
					if(skrinName.equals("htaah") || skrinName.equals("htaahx") || skrinName.equals("ha"))
					{
						 html += "<script>checkHartaTertingal('cmdAdd"+skrinName+"');</script>";
					}
				}
				html += "</td>" +
								"</tr>" +
								"</table>";
			}
			html += "<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" width=\"100%\" class='classFade' > ";
			int totalColumn = 0;

			List listColumnForSenarai = null;
			//untuk column waris ob saksi pemiutang penghutang
			if(!skrinName.equals("htaah") && !skrinName.equals("htaahx") && !skrinName.equals("ha") && !skrinName.equals("peguam"))
			{
				listColumnForSenarai = listColumnForSenaraiOB(session,skrinName,current_previous);
			}
			else
			{
				if(skrinName.equals("peguam"))
				{
					listColumnForSenarai = listColumnForSenaraiPeguam(session,skrinName,current_previous);
				}
				else if(skrinName.equals("ha"))
				{
					listColumnForSenarai = listColumnForSenaraiHartaAlih(session,skrinName,current_previous);
				}
				else
				{
					listColumnForSenarai = listColumnForSenaraiHarta(session,skrinName,current_previous);
				}
			}
			myLogger.info(":: listColumnForSenarai :: "+listColumnForSenarai);

			if (listColumnForSenarai.size() != 0) {
				html += "<tr class=\"table_header\" > ";
				html += "<td   align=\"center\" valign=\"top\" width=\"5%\">Bil.</td> ";
				for (int i = 0; i < listColumnForSenarai.size(); i++) {
					Map map_column_name = (Map) listColumnForSenarai.get(i);
					String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
					String LABEL = (String) map_column_name.get("LABEL");
					String LIST_NAME = (String) map_column_name.get("LIST_NAME");
					String ALIGN = (String) map_column_name.get("ALIGN");
					//ni untuk setting header & column list
					if(!ALIGN.equals("hidden"))
					{
						totalColumn++;
						html += "<td align=\""+ALIGN+"\" valign=\"top\" >"+LABEL+"</td> ";
					}
				}
				html += "<td align=\"center\" valign=\"top\" width=\"10%\">Tindakan</td> ";
				html += "</tr>";
			}

			int bil = 0;
			while (rs.next()) {
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
				String jenis_aktiviti = "";
				if (listColumnForSenarai.size() != 0) {
					html += "<tr> ";
					html += "<td class=\""+rowCss+"\" align=\"center\" valign=\"top\" width=\"5%\">"+bil+"</td> ";
					for (int i = 0; i < listColumnForSenarai.size(); i++) {
						Map map_column_name = (Map) listColumnForSenarai.get(i);
						String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
						String ALIGN = (String) map_column_name.get("ALIGN");
						//myLogger.info("COLUMN_NAME : "+COLUMN_NAME);
						String valueByColumn = (rs.getString(COLUMN_NAME) == null ? "" : rs.getString(COLUMN_NAME).toUpperCase());
						if(!ALIGN.equals("hidden"))
						{
							String classByCondition = "";
							if(COLUMN_NAME.equals("JENIS_AKTIVITI"))
							{
								jenis_aktiviti = valueByColumn;
								classByCondition += "blink ";
								if(valueByColumn.equals("DELETE"))
								{
									classByCondition += " red ";
								}
								else
								{
									classByCondition += " blue ";
								}
							}
							html += "<td class=\""+rowCss+"\" align=\""+ALIGN+"\" valign=\"top\" ><div class=\""+classByCondition+"\">"+valueByColumn+"</div></td> ";
						}
					}


					String params = "";
					String divId = "div";
					if(namaList.equals("list"+skrinName+"current") || namaList.equals("list"+skrinName+"previous"))
					{
						if(skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("pemiutang") || skrinName.equals("saksi"))
						{
							//myLogger.info(skrinName+ " : ID_OBPERMOHONAN : "+rs.getString("ID_OBPERMOHONAN"));
							divId += ID_PERMOHONANSIMATI+rs.getString("ID_OBPERMOHONAN");
							params = paramStandard+"&ID_SEJARAHBIMAIN="+(rs.getString("ID_SEJARAHBIMAIN") == null ? "" : rs.getString("ID_SEJARAHBIMAIN"))+"&ID_OBPERMOHONAN="+rs.getString("ID_OBPERMOHONAN")+"&current_previous="+current_previous+"&skrinName="+skrinName+"&ID_PEMOHON="+ID_PEMOHON+"&NAMA_TABLE=TBLPPKOBPERMOHONAN&FIELD_PK=ID_OBPERMOHONAN";
						}
						else if(skrinName.equals("penghutang"))
						{
							//myLogger.info(skrinName+ " : ID_PENGHUTANG : "+rs.getString("ID_PENGHUTANG"));
							divId += ID_PERMOHONANSIMATI+rs.getString("ID_PENGHUTANG");
							params = paramStandard+"&ID_SEJARAHBIMAIN="+(rs.getString("ID_SEJARAHBIMAIN") == null ? "" : rs.getString("ID_SEJARAHBIMAIN"))+"&ID_PENGHUTANG="+rs.getString("ID_PENGHUTANG")+"&current_previous="+current_previous+"&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKPENGHUTANG&FIELD_PK=ID_PENGHUTANG";
						}
						else if(skrinName.equals("peguam"))
						{
							//myLogger.info(skrinName+ " : ID_PEGUAM : "+rs.getString("ID_PEGUAM"));
							divId += ID_PERMOHONANSIMATI+rs.getString("ID_PEGUAM");
							params = paramStandard+"&ID_SEJARAHBIMAIN="+(rs.getString("ID_SEJARAHBIMAIN") == null ? "" : rs.getString("ID_SEJARAHBIMAIN"))+"&ID_PEGUAM="+rs.getString("ID_PEGUAM")+"&current_previous="+current_previous+"&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKPEGUAM&FIELD_PK=ID_PEGUAM";
						}
						else if(skrinName.equals("htaah") || skrinName.equals("htaahx"))
						{
							//myLogger.info(skrinName+ " : ID_HTAPERMOHONAN : "+rs.getString("ID_HTAPERMOHONAN"));
							divId += ID_PERMOHONANSIMATI+rs.getString("ID_HTAPERMOHONAN");
							params = paramStandard+"&ID_SEJARAHBIMAIN="+(rs.getString("ID_SEJARAHBIMAIN") == null ? "" : rs.getString("ID_SEJARAHBIMAIN"))+"&ID_HTAPERMOHONAN="+rs.getString("ID_HTAPERMOHONAN")+"&current_previous="+current_previous+"&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKHTAPERMOHONAN&FIELD_PK=ID_HTAPERMOHONAN";
						}
						else if(skrinName.equals("ha"))
						{
							//myLogger.info(skrinName+ " : ID_HAPERMOHONAN : "+rs.getString("ID_HAPERMOHONAN"));
							divId += ID_PERMOHONANSIMATI+rs.getString("ID_HAPERMOHONAN");
							params = paramStandard+"&ID_SEJARAHBIMAIN="+(rs.getString("ID_SEJARAHBIMAIN") == null ? "" : rs.getString("ID_SEJARAHBIMAIN"))+"&ID_HAPERMOHONAN="+rs.getString("ID_HAPERMOHONAN")+"&current_previous="+current_previous+"&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKHAPERMOHONAN&FIELD_PK=ID_HAPERMOHONAN";
						}

						params += "&skrinName="+skrinName+"&divId="+divId+"&div="+divId;
					}
					/*
					else if(namaList.equals("list"+skrinName+"history"))
					{
						divId += ID_PERMOHONANSIMATI+rs.getString("ID_SEJARAHBIMAIN");
						params = paramStandard+"&ID_OBPERMOHONAN="+rs.getString("ID_OBPERMOHONAN")+"&ID_SEJARAHBIMAIN="+rs.getString("ID_SEJARAHBIMAIN")+"&current_previous="+current_previous+"&skrinName="+skrinName+"&NAMA_TABLE=TBLPPKOBPERMOHONAN&FIELD_PK=ID_OBPERMOHONAN&divId="+divId;
					}
					*/

					html += "<td class=\""+rowCss+"\" align=\"center\" valign=\"top\" width=\"5%\">";
					html += "<a href=\"javascript:setingTrDiv('divId"+skrinName+"','"+divId+"');doDivAjaxCall"+formName+"('"+divId+"','showMaklumat','"+params+"')\"><img title=\"Kemaskini\" src=\"../img/edit.gif\" border=\"0\"></a>";

					if(!jenis_aktiviti.equals(""))
					{
						html += "<a class=\"buttonHapus\" href=\"javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){doDivAjaxCall"+formName+"('senarai_"+skrinName+current_previous+"','deleteHistory','"+params+"&current_previous="+current_previous+"');}\"><img title=\"Hapus Maklumat Perubahan\" src=\"../img/delete.gif\" border=\"0\"></a>";
					}

					html += "</td> ";
					html += "</tr> ";
					html += "<tr> ";
					html += "<td colspan = \""+totalColumn+2+"\" align=\"left\" valign=\"top\"  >";
					html += "<div id=\""+divId+"\" ></div>";
					html += "</td>";
					html += "</tr> ";


				}
			}
			if(bil==0)
			{
				html += "<tr><td colspan = \""+totalColumn+2+"\" >Tiada Rekod</td></tr>";
			}
			html +="</table>";
			html +="<div id=\"senarai_"+skrinName+"add\" ></div>";
			html += "</fieldset>";

			html += "<script>$jquery(document).ready(function (){";
			if(current_previous.equals("previous"))
			{
				if(bil == 0)
				{
					//kalo tiada senarai terdahulu, kita blank kan terus div
					html += "document.getElementById(\"senarai_"+skrinName+"previous\").style.display = \"none\";";
					//html = "";
				}
				else
				{
					html += "document.getElementById(\"senarai_"+skrinName+"previous\").style.display = \"\";";
				}
				//reload tambahan data
				//html += " doDivAjaxCall"+formName+"('senarai_"+skrinName+"add','showSenaraiTambahan','NAMA_TABLE="+NAMA_TABLE+"&FIELD_PK="+FIELD_PK+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PERMOHONAN="+ID_PERMOHONAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&skrinName="+skrinName+"&current_previous=previous&tajukList=Senarai Waris Terdahulu (Asal)');" +
				html += " $jquery('#senarai_"+skrinName+"current').scrollView(); ";
			}
			if(current_previous.equals("history"))
			{
				if(bil == 0)
				{
					//kalo tiada senarai terdahulu, kita blank kan terus div
					html += "document.getElementById(\"senarai_"+skrinName+"history\").style.display = \"none\";";
					//html = "";
				}
				else
				{
					html += "document.getElementById(\"senarai_"+skrinName+"history\").style.display = \"\";";
				}
				//reload tambahan data
				//html += " doDivAjaxCall"+formName+"('senarai_"+skrinName+"add','showSenaraiTambahan','NAMA_TABLE="+NAMA_TABLE+"&FIELD_PK="+FIELD_PK+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PERMOHONAN="+ID_PERMOHONAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&skrinName="+skrinName+"&current_previous=previous&tajukList=Senarai Waris Terdahulu (Asal)');" +

			}



			html += " });</script>";



		} finally {
			if (rs != null)
			{
				rs.close();
			}
			if (stmt != null)
			{
				stmt.close();
			}
			if (db == null)
			{
				db1.close();
			}
		}


		return html;
	}


	public String htmlListKeterangan(HttpSession session,String formName,String ID_SIMATI,String ID_PERMOHONANSIMATI,String ID_PERBICARAAN,
			String ID_PERMOHONAN,String ID_PEMOHON,String flagPrint,String fontSize,Db db) throws Exception {

		String html = "";
		if(flagPrint.equals("N"))
		{
			html += "<a class=\"blue\" href=\"javascript:doDivAjaxCall"+formName+"('view_keteranganhadir','showMaklumatketeranganhadir','NAMA_TABLE=&FIELD_PK=&ID_SIMATI="+ID_SIMATI+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PERMOHONAN="+ID_PERMOHONAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&skrinName=perubahan&scrolPosition='+getPageLocation());\">&nbsp;<u>'Refresh'</u></a>";
		}
		html += "<input type=\"hidden\" id=\"checkRekodKeteranganhadir\" name=\"checkRekodKeteranganhadir\" value=\"N\" ><div id=\"divCheckRekodKeteranganhadir\"></div> ";

		List listKehadiran = listKehadiran(session,ID_PERMOHONANSIMATI,ID_PERMOHONAN,ID_PERBICARAAN,ID_PEMOHON,db);
		List listTurutHadir = listTurutHadir(session,ID_PERBICARAAN, db);
		List listSaksi = listSaksi(session,ID_PERBICARAAN, db);//arief add keterangan saksi

		if(listKehadiran.size()>0)
		{
			if(flagPrint.equals("N"))
			{
				html += htmlListPerubahanTajuk(session,ID_PERBICARAAN,"flagKeteranganOB",flagPrint,db);
			}

			for(int i=0; i<listKehadiran.size(); i++)
			{
				Map setupKehadiran = (Map) listKehadiran.get(i);
				String PENJAGA = (String)setupKehadiran.get("PENJAGA");
				String NAMA = (String)setupKehadiran.get("NAMA");
				int UMUR = (Integer)setupKehadiran.get("UMUR_INT");
				String PENGENALAN = (String)setupKehadiran.get("PENGENALAN");
				String HUBUNGAN = (String)setupKehadiran.get("HUBUNGAN");
				String KETERANGAN = (String)setupKehadiran.get("KETERANGAN");
				String NOTA_PEGAWAI = (String)setupKehadiran.get("NOTA_PEGAWAI");

				String NAMA_HADIR = (String)setupKehadiran.get("NAMA_HADIR");
				String PENGENALAN_HADIR = (String)setupKehadiran.get("PENGENALAN_HADIR");
				String HUBUNGAN_HADIR = (String)setupKehadiran.get("HUBUNGAN_HADIR");
				String UMUR_HADIR = (String)setupKehadiran.get("UMUR_HADIR");
				String STATUS_HADIR = (String)setupKehadiran.get("STATUS_HADIR");
				String JENIS_OB = (String)setupKehadiran.get("JENIS_OB");
				String STATUS_OB = (String)setupKehadiran.get("STATUS_OB");
				String CATATAN_WAKIL = "";
				if(!NAMA_HADIR.equals(""))
				{
					NAMA = NAMA_HADIR;

					if(JENIS_OB.equals("1"))
					{
						CATATAN_WAKIL = " "+"wakil kepada "+(String)setupKehadiran.get("NAMA");
					}

				}
				if(!PENGENALAN_HADIR.equals(""))
				{
					PENGENALAN = PENGENALAN_HADIR;
				}
				if(!UMUR_HADIR.equals(""))
				{
					UMUR = Integer.parseInt(UMUR_HADIR);
				}
				if(!HUBUNGAN_HADIR.equals(""))
				{
					HUBUNGAN = HUBUNGAN_HADIR;
				}

				if(!KETERANGAN.equals("") || !NOTA_PEGAWAI.equals(""))
				{
					if(flagPrint.equals("N"))
					{
						html += htmlListKeteranganBySrkin(session,NAMA,PENGENALAN,HUBUNGAN,KETERANGAN,NOTA_PEGAWAI,flagPrint,UMUR,STATUS_OB,PENJAGA,
								//NAMA_HADIR,PENGENALAN_HADIR,HUBUNGAN_HADIR,UMUR_HADIR,STATUS_HADIR,JENIS_OB
								CATATAN_WAKIL,db);
					}
					else
					{
						html += htmlListKeteranganBySrkinPrint(session,NAMA,PENGENALAN,HUBUNGAN,KETERANGAN,NOTA_PEGAWAI,flagPrint,UMUR,STATUS_OB,PENJAGA,fontSize,
								//NAMA_HADIR,PENGENALAN_HADIR,HUBUNGAN_HADIR,UMUR_HADIR,STATUS_HADIR,JENIS_OB
								CATATAN_WAKIL,db);
					}
				}
			}
		}

		if(listTurutHadir.size()>0)
		{
			if(flagPrint.equals("N"))
			{
				html += htmlListPerubahanTajuk(session,ID_PERBICARAAN,"flagKeteranganTurutHadir",flagPrint,db);
			}

			for(int x=0; x<listTurutHadir.size(); x++)
			{
				Map setupTurutHadir = (Map) listTurutHadir.get(x);
				String NAMA = (String)setupTurutHadir.get("NAMA");
				String PENGENALAN = (String)setupTurutHadir.get("PENGENALAN");
				String HUBUNGAN = (String)setupTurutHadir.get("HUBUNGAN");
				String KETERANGAN = (String)setupTurutHadir.get("KETERANGAN");
				String NOTA_PEGAWAI = (String)setupTurutHadir.get("NOTA_PEGAWAI");
				if(!KETERANGAN.equals("") || !NOTA_PEGAWAI.equals(""))
				{
					if(flagPrint.equals("N"))
					{
						html += htmlListKeteranganBySrkin(session,NAMA,PENGENALAN,HUBUNGAN,KETERANGAN,NOTA_PEGAWAI,flagPrint,0,"",
								//"","","","","",""
								"","",db);
					}
					else
					{
						html += htmlListKeteranganBySrkinPrint(session,NAMA,PENGENALAN,HUBUNGAN,KETERANGAN,NOTA_PEGAWAI,flagPrint,0,"","",fontSize,
								//"","","","","",""
								"",db);
					}
				}
			}

			//arief add keterangan saksi open
			if(listSaksi.size()>0)
			{
				if(flagPrint.equals("N"))
				{
					html += htmlListPerubahanTajuk(session,ID_PERBICARAAN,"flagKeteranganSaksi",flagPrint,db);
				}

				for(int x=0; x<listSaksi.size(); x++)
				{
					Map setupSaksi = (Map) listSaksi.get(x);
					String NAMA = (String)setupSaksi.get("NAMA");
					String PENGENALAN = (String)setupSaksi.get("PENGENALAN");
					String HUBUNGAN = (String)setupSaksi.get("HUBUNGAN");
					String KETERANGAN = (String)setupSaksi.get("KETERANGAN");
					String NOTA_PEGAWAI = (String)setupSaksi.get("NOTA_PEGAWAI");
					if(!KETERANGAN.equals("") || !NOTA_PEGAWAI.equals(""))
					{
						if(flagPrint.equals("N"))
						{
							html += htmlListKeteranganBySrkin (session,NAMA,PENGENALAN,HUBUNGAN,KETERANGAN,NOTA_PEGAWAI,flagPrint,0,"",
									"","",db);
						}
						else
						{
							html += htmlListKeteranganBySrkinPrint(session,NAMA,PENGENALAN,HUBUNGAN,KETERANGAN,NOTA_PEGAWAI,flagPrint,0,"","",fontSize,

									"",db);
						}
					}
				}
			}
			//arief add keterangan saksi close
		}

		if(flagPrint.equals("N"))
		{
			html += " <table style=\"border-collapse:collapse;\" id=\"tableCetakKeteranganhadir\" cellspacing=\"1\" cellpadding=\"2\"  width=\"100%\" > ";
			html += " <tr > ";
			html += " <td align=\"center\" valign=\"top\" style=\"font-size: 90%;\" > ";
			html += " <input type=\"button\" id=\"buttonKeteranganhadir\" name=\"buttonKeteranganhadir\" value=\"Cetak\"  onclick=\"printKeteranganhadir("+ID_PERBICARAAN+");\" /> ";
			html += " </td>	";
			html += " </tr> ";
			html += " </table> ";
			html += "<script>" +
					" var cr = document.getElementById(\"checkRekodKeteranganhadir\").value; " +
					" setDivRekodPerubahan(cr,'divCheckRekodKeteranganhadir','tableCetakKeteranganhadir','Tiada Rekod Keterangan'); "+
					" setDivTajuk(cr,'tajukLaporanflagKeteranganOB'); "+
					" setDivTajuk(cr,'tajukLaporanflagKeteranganTurutHadir'); "+
					" setDivTajuk(cr,'tajukLaporanflagKeteranganSaksi'); "+//arief add
					"</script>";
		}

		return html;
	}

	public String htmlListPerubahan(HttpSession session,String formName,String ID_SIMATI,String ID_PERMOHONANSIMATI,String ID_PERBICARAAN,String ID_PERMOHONAN,String ID_PEMOHON,String flagPrint,Db db) throws Exception {
		String html = "";
		if(flagPrint.equals("N"))
		{
			html += "<a class=\"blue\" href=\"javascript:doDivAjaxCall"+formName+"('view_perubahan','showMaklumatperubahan','NAMA_TABLE=&FIELD_PK=&ID_SIMATI="+ID_SIMATI+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PERMOHONAN="+ID_PERMOHONAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&skrinName=perubahan&scrolPosition='+getPageLocation());\">&nbsp;<u>'Refresh'</u></a>";
		}
		html += "<input type=\"hidden\" id=\"checkRekodPerubahan\" name=\"checkRekodPerubahan\" value=\"N\" ><div id=\"divCheckRekodPerubahan\"></div> ";
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"simati","TBLPPKSIMATI",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"pemohon","TBLPPKPEMOHON",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"peguam","TBLPPKPEGUAM",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"waris","TBLPPKOBPERMOHONAN",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"ob","TBLPPKOBPERMOHONAN",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"saksi","TBLPPKOBPERMOHONAN",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"pemiutang","TBLPPKOBPERMOHONAN",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"penghutang","TBLPPKPENGHUTANG",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"htaah","TBLPPKHTAPERMOHONAN",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"htaahx","TBLPPKHTAPERMOHONAN",flagPrint,db);
		html += htmlListPerubahanBySrkin(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"ha","TBLPPKHAPERMOHONAN",flagPrint,db);
		if(flagPrint.equals("N"))
		{
			html += " <table style=\"border-collapse:collapse;\" id=\"tableCetakPerubahan\" cellspacing=\"1\" cellpadding=\"2\"  width=\"100%\" > ";
			html += " <tr > ";
			html += " <td align=\"center\" valign=\"top\" style=\"font-size: 90%;\" > ";

			html += " <input type=\"button\" id=\"buttonPerubahan\" name=\"buttonPerubahan\" value=\"Cetak\"  onclick=\"printPerubahan("+ID_PERBICARAAN+");\" /> ";

			html += " </td>	";
			html += " </tr> ";
			html += " </table> ";
			html += "<script>" +
					" var cr = document.getElementById(\"checkRekodPerubahan\").value; " +
					" setDivRekodPerubahan(cr,'divCheckRekodPerubahan','tableCetakPerubahan','Tiada Rekod Perubahan'); "+
					"</script>";
		}

		return html;
	}



	public String htmlListPerubahanTajuk(HttpSession session,String ID_PERBICARAAN,String skrinName,String flagPrint,Db db) throws Exception {
		String namaMaklumat = "";
		if(skrinName.equals("simati"))
		{
			namaMaklumat = "SIMATI";
		}
		else if(skrinName.equals("pemohon"))
		{
			namaMaklumat = "PEMOHON";
		}
		else if(skrinName.equals("peguam"))
		{
			namaMaklumat = "PEGUAM";
		}
		else if(skrinName.equals("waris"))
		{
			namaMaklumat = "WARIS";
		}
		else if(skrinName.equals("ob"))
		{
			namaMaklumat = "ORANG BERKEPENTINGAN";
		}
		else if(skrinName.equals("saksi"))
		{
			namaMaklumat = "ORANG SAKSI";
		}
		else if(skrinName.equals("pemiutang"))
		{
			namaMaklumat = "ORANG PEMIUTANG";
		}
		else if(skrinName.equals("penghutang"))
		{
			namaMaklumat = "ORANG PENGHUTANG";
		}
		else if(skrinName.equals("htaah"))
		{
			namaMaklumat = "HARTA TAK ALIH (ADA HAKMILIK)";
		}
		else if(skrinName.equals("htaahx"))
		{
			namaMaklumat = "HARTA TAK ALIH (TIADA HAKMILIK)";
		}
		else if(skrinName.equals("ha"))
		{
			namaMaklumat = "HARTA ALIH";
		}
		else if(skrinName.equals("flagKeteranganOB"))
		{
			namaMaklumat = "KETERANGAN ORANG BERKEPENTINGAN";
		}
		else if(skrinName.equals("flagKeteranganTurutHadir"))
		{
			namaMaklumat = "KETERANGAN TURUT HADIR";
		}

		String html = "";
		String fontSize = "";
		if(flagPrint.equals("N"))
		{
			html += "<table border=\"0\" cellspacing=\"3\" cellpadding=\"3\" width=\"90%\" id=\"tajukLaporan"+skrinName+"\" >";
		}
		else
		{
			fontSize = " style=\"border-bottom: 1px solid #000;font-size: 70%;\" ";
			html += "<table style=\"border-collapse:collapse;\"  cellspacing=\"1\" cellpadding=\"2\"  width=\"100%\" >";
		}
		html += "<tr >";
		html += "<td align=\"left\" valign=\"top\" "+fontSize+" ><strong>MAKLUMAT "+namaMaklumat+"</strong></td>";
		html += "</tr>";
		html += "</table>";
		return html;
	}


	public String getValueFromDataLatest(HttpSession session,String column_name,String ID_SEJARAHBIMAIN,String ID_PERMOHONAN,String ID_PEMOHON,String ID_PERBICARAAN,
			String skrinName,String ID_PERMOHONANSIMATI,String NAMA_FIELD_PK, String VALUE_FIELD_PK, String NAMA_TABLE, Db db) throws Exception {
		String getValue = "";
		Map setupSkrinAsal = null;
		if(skrinName.equals("pemohon") || skrinName.equals("simati"))
		{
			setupSkrinAsal = getValueColumn(session,ID_PEMOHON,ID_PERBICARAAN,skrinName,ID_PERMOHONANSIMATI,"", ID_PERMOHONAN, NAMA_TABLE, db);
		}
		else
		{
			myLogger.info("ID_PEMOHON : "+ID_PEMOHON+"; ID_PERBICARAAN : "+ID_PERBICARAAN+"; skrinName : "+skrinName+"; ID_PERMOHONANSIMATI : "+ID_PERMOHONANSIMATI+"; NAMA_FIELD_PK : "+NAMA_FIELD_PK+"; VALUE_FIELD_PK : "+VALUE_FIELD_PK+"; NAMA_TABLE : "+NAMA_TABLE);
			setupSkrinAsal = getValueColumn(session,ID_PEMOHON,ID_PERBICARAAN,skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK,VALUE_FIELD_PK, NAMA_TABLE, db);
		}
		Map getChanges = null;
		if(setupSkrinAsal!=null)
		{
			//untuk kes kemaskini
			getValue = getValue(session,ID_PERMOHONANSIMATI, setupSkrinAsal,NAMA_TABLE,NAMA_FIELD_PK,VALUE_FIELD_PK,ID_PERBICARAAN,column_name,db);
			getChanges = getChanges(session,"",ID_PERMOHONANSIMATI, NAMA_TABLE,NAMA_FIELD_PK,VALUE_FIELD_PK,ID_PERBICARAAN,column_name, db);
		}
		else
		{
			//untuk kes tambah
			getChanges = getChanges(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI, NAMA_TABLE,NAMA_FIELD_PK,VALUE_FIELD_PK,ID_PERBICARAAN,column_name, db);
			if(getChanges==null)
			{
				getValue = "";
			}
		}

		String JENIS_AKTIVITI="";
		String VALUE_SEBELUM="";
		String KETERANGAN_SEBELUM="";
		String VALUE_SELEPAS="";
		String KETERANGAN_SELEPAS = "";
		boolean changes = false;
		if(getChanges!=null)
		{
			changes = true;
			JENIS_AKTIVITI = (String) getChanges.get("JENIS_AKTIVITI");
			VALUE_SEBELUM = (String) getChanges.get("VALUE_SEBELUM");
			KETERANGAN_SEBELUM = (String) getChanges.get("KETERANGAN_SEBELUM");
			VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
			KETERANGAN_SELEPAS = (String) getChanges.get("KETERANGAN_SELEPAS");
			getValue = VALUE_SELEPAS;
		}
		//myLogger.info(":::: skrinName : "+skrinName+":::: htmlListPerubahanBySrkin >> setupSkrinAsal : "+setupSkrinAsal);
		myLogger.info("getValueFromData ::: column_name : "+column_name+" getValue : "+getValue);
		return getValue;
	}


	public String getValueFromDataAsal(HttpSession session,String column_name,String ID_SEJARAHBIMAIN,String ID_PERMOHONAN,String ID_PEMOHON,String ID_PERBICARAAN,
			String skrinName,String ID_PERMOHONANSIMATI,String NAMA_FIELD_PK, String VALUE_FIELD_PK, String NAMA_TABLE, Db db) throws Exception {
		String getValue = "";
		Map setupSkrinAsal = null;
		if(skrinName.equals("pemohon") || skrinName.equals("simati"))
		{
			setupSkrinAsal = getValueColumn(session,ID_PEMOHON,ID_PERBICARAAN,skrinName,ID_PERMOHONANSIMATI,"", ID_PERMOHONAN, NAMA_TABLE, db);
		}
		else
		{
			myLogger.info("ID_PEMOHON : "+ID_PEMOHON+"; ID_PERBICARAAN : "+ID_PERBICARAAN+"; skrinName : "+skrinName+"; ID_PERMOHONANSIMATI : "+ID_PERMOHONANSIMATI+"; NAMA_FIELD_PK : "+NAMA_FIELD_PK+"; VALUE_FIELD_PK : "+VALUE_FIELD_PK+"; NAMA_TABLE : "+NAMA_TABLE);
			setupSkrinAsal = getValueColumn(session,ID_PEMOHON,ID_PERBICARAAN,skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK,VALUE_FIELD_PK, NAMA_TABLE, db);
		}
		if(setupSkrinAsal!=null)
		{
			//untuk kes kemaskini
			getValue = setupSkrinAsal == null ? "" :(String) setupSkrinAsal.get(column_name) == null ? "" : (String) setupSkrinAsal.get(column_name);
		}
		return getValue;
	}

	public void setPerubahanBySkrin(HttpSession session,String ID_PERBICARAAN,String skrinName,String tableName,Db db) throws Exception {
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		myLogger.info("setPerubahanBySkrin >>> skrinName : "+skrinName+" tableName : "+tableName);
		List listMainPerubahan = listMainPerubahan(session,ID_PERBICARAAN,skrinName,tableName,db);
		if (listMainPerubahan.size() != 0) {


			for (int i = 0; i < listMainPerubahan.size(); i++) {

				String NamaTable = "";
				String NamaTable_supliment = "";
				String JenisTransaksi = "";
				String namaFieldPK = "";
				String valueFieldPK = "";
				String namaFieldPK_supliment = "";
				String valueFieldPK_supliment = "";
				String sql_statement = "";
				String sql_statement_supliment = "";
				String sql_field_statement = "";
				String sql_field_statement_supliment = "";
				String sql_insertfieldcolumn_statement = "";
				String sql_insertfieldcolumn_statement_supliment = "";
				String sql_insertfieldvalue_statement = "";
				String sql_insertfieldvalue_statement_supliment = "";

				Map mapMain = (Map) listMainPerubahan.get(i);
				String ID_SEJARAHBIMAIN  = (String) mapMain.get("ID_SEJARAHBIMAIN");
				String JENIS_AKTIVITI  = (String) mapMain.get("JENIS_AKTIVITI");
				String NAMA_PEGAWAI  = (String) mapMain.get("NAMA_PEGAWAI");
				String TARIKH_TRANSAKSI_FULL  = (String) mapMain.get("TARIKH_TRANSAKSI_FULL");
				String TARIKH_TRANSAKSI  = (String) mapMain.get("TARIKH_TRANSAKSI");
				String ID_PERMOHONANSIMATI  = (String) mapMain.get("ID_PERMOHONANSIMATI");
				String NAMA_FIELD_PK  = (String) mapMain.get("NAMA_FIELD_PK");
				String VALUE_FIELD_PK  = (String) mapMain.get("VALUE_FIELD_PK");
				String NAMA_TABLE  = (String) mapMain.get("NAMA_TABLE");
				NamaTable = NAMA_TABLE;
				JenisTransaksi = JENIS_AKTIVITI;
				namaFieldPK = NAMA_FIELD_PK;
				valueFieldPK = VALUE_FIELD_PK;


				if(NamaTable.equals("TBLPPKOBPERMOHONAN"))
				{
					NamaTable_supliment = "TBLPPKOB";
					namaFieldPK_supliment = "ID_OB";
				}
				else if(NamaTable.equals("TBLPPKHTAPERMOHONAN"))
				{
					NamaTable_supliment = "TBLPPKHTA";
					namaFieldPK_supliment = "ID_HTA";
				}
				else if(NamaTable.equals("TBLPPKHAPERMOHONAN"))
				{
					NamaTable_supliment = "TBLPPKHA";
					namaFieldPK_supliment = "ID_HA";
				}
				else if(NamaTable.equals("TBLPPKHUBUNGANOBPERMOHONAN"))
				{
					NamaTable_supliment = "TBLPPKHUBUNGANOB";
					namaFieldPK_supliment = "ID_HUBUNGANOB";
				}

				//try {

					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					SQLRenderer r_supliment = new SQLRenderer();

					myLogger.info("setPerubahanBySkrin >>> JENIS_AKTIVITI : "+JENIS_AKTIVITI+" NAMA_FIELD_PK : "+NAMA_FIELD_PK+" VALUE_FIELD_PK : "+VALUE_FIELD_PK);
					List listSubPerubahan = listSubPerubahan(session,ID_SEJARAHBIMAIN,db);
					if (listSubPerubahan.size() != 0) {
						for (int x = 0; x < listSubPerubahan.size(); x++) {
							Map mapSub = (Map) listSubPerubahan.get(x);
							String REKOD_LABEL  = (String) mapSub.get("REKOD_LABEL");
							String NAMA_FIELD  = (String) mapSub.get("NAMA_FIELD");
							String VALUE_SEBELUM  = (String) mapSub.get("VALUE_SEBELUM");
							String KETERANGAN_SEBELUM  = (String) mapSub.get("KETERANGAN_SEBELUM");
							String VALUE_SELEPAS  = (String) mapSub.get("VALUE_SELEPAS");
							String KETERANGAN_SELEPAS  = (String) mapSub.get("KETERANGAN_SELEPAS");
							String TURUTAN  = (String) mapSub.get("TURUTAN");
							myLogger.info("setPerubahanBySkrin >>> NAMA_FIELD : "+NAMA_FIELD+" VALUE_SELEPAS : "+VALUE_SELEPAS+"");

							if(!NamaTable_supliment.equals("") && NAMA_FIELD.equals(namaFieldPK_supliment))
							{
								valueFieldPK_supliment = VALUE_SELEPAS;
							}


							if(JenisTransaksi.equals("DELETE"))
							{
								if(NAMA_FIELD.equals(namaFieldPK))
								{
									sql_statement += "DELETE FROM "+NamaTable+" WHERE "+namaFieldPK+" = '"+VALUE_SELEPAS+"'";
								}

								if(!NamaTable_supliment.equals(""))
								{
									if(NAMA_FIELD.equals(namaFieldPK_supliment))
									{
										sql_statement_supliment += "DELETE FROM "+NamaTable_supliment+" WHERE "+namaFieldPK_supliment+" = '"+VALUE_SELEPAS+"'";
									}
								}
							}
							else if(JenisTransaksi.equals("UPDATE"))
							{
								if(NAMA_FIELD.equals(namaFieldPK) && !checkColumnExist(session,NamaTable,NAMA_FIELD, db).equals(""))
								{
									r.update(NAMA_FIELD, VALUE_SELEPAS);
								}
								else if(!NAMA_FIELD.equals(namaFieldPK) && !checkColumnExist(session,NamaTable,NAMA_FIELD, db).equals(""))
								{
									if(checkColumnExist(session,NamaTable,NAMA_FIELD, db).equals("DATE"))
									{
										r.add(NAMA_FIELD, r.unquote(setDateFormat(VALUE_SELEPAS)));
									}
									else
									{
										r.add(NAMA_FIELD, VALUE_SELEPAS);
									}
								}

								if(!NamaTable_supliment.equals(""))
								{
									if(NAMA_FIELD.equals(namaFieldPK_supliment) && !checkColumnExist(session,NamaTable_supliment,NAMA_FIELD, db).equals(""))
									{
										r_supliment.update(NAMA_FIELD, VALUE_SELEPAS);
									}
									else if(!NAMA_FIELD.equals(namaFieldPK_supliment) && !checkColumnExist(session,NamaTable_supliment,NAMA_FIELD, db).equals(""))
									{
										if(checkColumnExist(session,NamaTable_supliment,NAMA_FIELD, db).equals("DATE"))
										{
											r_supliment.add(NAMA_FIELD, r_supliment.unquote(setDateFormat(VALUE_SELEPAS)));
										}
										else
										{
											if(!NAMA_FIELD.equals("ID_PERMOHONANSIMATI"))
											{
												r_supliment.add(NAMA_FIELD, VALUE_SELEPAS);
											}
										}
									}
								}
							}
							else if(JenisTransaksi.equals("INSERT"))
							{

								if(!checkColumnExist(session,NamaTable,NAMA_FIELD, db).equals(""))
								{
									if(checkColumnExist(session,NamaTable,NAMA_FIELD, db).equals("DATE"))
									{
										r.add(NAMA_FIELD, r.unquote(setDateFormat(VALUE_SELEPAS)));
									}
									else
									{
										r.add(NAMA_FIELD, VALUE_SELEPAS);
									}
								}
								if(!NamaTable_supliment.equals(""))
								{
									if(!checkColumnExist(session,NamaTable_supliment,NAMA_FIELD, db).equals(""))
									{
										if(checkColumnExist(session,NamaTable_supliment,NAMA_FIELD, db).equals("DATE"))
										{
											r_supliment.add(NAMA_FIELD,r_supliment.unquote(setDateFormat(VALUE_SELEPAS)));
										}
										else
										{
											r_supliment.add(NAMA_FIELD, VALUE_SELEPAS);
										}
									}
								}
							}

						}

					}

					if(JenisTransaksi.equals("UPDATE"))
					{
						if(!checkColumnExist(session,NamaTable,"ID_KEMASKINI", db).equals(""))
						{
							r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						}
						if(!checkColumnExist(session,NamaTable,"TARIKH_KEMASKINI", db).equals(""))
						{
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						}
						sql_statement = r.getSQLUpdate(NamaTable);
						if(!NamaTable_supliment.equals(""))
						{
							if(!checkColumnExist(session,NamaTable_supliment,"ID_KEMASKINI", db).equals(""))
							{
								r_supliment.add("ID_KEMASKINI", USER_ID_SYSTEM);
							}
							if(!checkColumnExist(session,NamaTable_supliment,"TARIKH_KEMASKINI", db).equals(""))
							{
								r_supliment.add("TARIKH_KEMASKINI", r_supliment.unquote("sysdate"));
							}
							sql_statement_supliment = r_supliment.getSQLUpdate(NamaTable_supliment);
						}
					}
					else if(JenisTransaksi.equals("INSERT"))
					{
						if(!checkColumnExist(session,NamaTable,"ID_MASUK", db).equals(""))
						{
							r.add("ID_MASUK", USER_ID_SYSTEM);
						}
						if(!checkColumnExist(session,NamaTable,"TARIKH_MASUK", db).equals(""))
						{
							r.add("TARIKH_MASUK", r.unquote("sysdate"));
						}
						sql_statement = r.getSQLInsert(NamaTable);
						if(!NamaTable_supliment.equals(""))
						{
							if(!checkColumnExist(session,NamaTable_supliment,"ID_MASUK", db).equals(""))
							{
								r_supliment.add("ID_MASUK", USER_ID_SYSTEM);
							}
							if(!checkColumnExist(session,NamaTable_supliment,"TARIKH_MASUK", db).equals(""))
							{
								r_supliment.add("TARIKH_MASUK", r_supliment.unquote("sysdate"));
							}
							sql_statement_supliment = r_supliment.getSQLInsert(NamaTable_supliment);
						}
					}


					if(JenisTransaksi.equals("DELETE") || JenisTransaksi.equals("UPDATE"))
					{
						if(!sql_statement.equals(""))
						{
							myLogger.info("setPerubahanBySkrin >>> FINAL sql_statement : "+sql_statement);
							stmt.executeUpdate(sql_statement);
						}

						if(!sql_statement_supliment.equals(""))
						{
							myLogger.info("setPerubahanBySkrin >>> FINAL sql_statement_supliment : "+sql_statement_supliment);
							stmt.executeUpdate(sql_statement_supliment);
						}

					}


					if(JenisTransaksi.equals("INSERT"))
					{

						//String NAMA_FIELD_PK  = (String) mapMain.get("NAMA_FIELD_PK");
						//String VALUE_FIELD_PK  = (String) mapMain.get("VALUE_FIELD_PK");
						String queryCheckData = "SELECT "+NAMA_FIELD_PK+" FROM "+NamaTable+" WHERE "+NAMA_FIELD_PK+" = '"+VALUE_FIELD_PK+"' ";
						myLogger.info(" QUERY CHECK DATA DAH WUJUD :  "+queryCheckData +" >>>> checkDataPernahDisimpan : "+checkDataPernahDisimpan(session, queryCheckData, db));

						if(checkDataPernahDisimpan(session, queryCheckData, db) == false)
						{

							if(!sql_statement_supliment.equals(""))
							{
								myLogger.info("setPerubahanBySkrin >>> FINAL sql_statement_supliment : "+sql_statement_supliment);
								stmt.executeUpdate(sql_statement_supliment);
							}

							if(!sql_statement.equals(""))
							{
								myLogger.info("setPerubahanBySkrin >>> FINAL sql_statement : "+sql_statement);
								stmt.executeUpdate(sql_statement);
							}

						}

					}



				/*
				}
				catch (Exception re) {
					myLogger.info("setPerubahanBySkrin >>> FINAL ERROR : "+re.toString());
				}
				*/
			}
		}
	}



	@SuppressWarnings("unchecked")
	public boolean checkDataPernahDisimpan(HttpSession session, String sql, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		boolean adaData = false;
		int ada = 0;
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();

			myLogger.info(" SQL checkDataPernahDisimpan :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = Collections.synchronizedMap(new HashMap());
			while (rs.next()) {
				adaData = true;
			}
			return adaData;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

    public String removeLastComma(String str) {

    	if(str!="")
    	{
    		str = str.substring(0, str.length()-1);
    	}

    	return str;
      }

    public String janaContentCatatanPerintah(HttpSession session,String ID_FAIL,String formName, String ID_SIMATI, String ID_PERBICARAAN,String ID_PERMOHONAN,String ID_PERMOHONANSIMATI,String ID_PEMOHON,String ID_PERINTAH,String NO_PINDAAN,String fontSize,Db db) throws Exception {
    	//String fontSize = "font-size: 130%;";
    	String htmlPageSetup = "";
    	Map setupKeputusan = getValueColumn(session,ID_PEMOHON,ID_PERBICARAAN,"keputusan",ID_PERMOHONANSIMATI,"",ID_PERBICARAAN, "TBLPPKPERINTAH", db);
    	myLogger.info(" janaContentCatatanPerintah setupKeputusan : "+setupKeputusan);
    	String FLAG_JENIS_KEPUTUSAN = setupKeputusan == null ? "" :(String) setupKeputusan.get("FLAG_JENIS_KEPUTUSAN") == null ? "" : (String) setupKeputusan.get("FLAG_JENIS_KEPUTUSAN");
    	String ID_INTROPERINTAH = setupKeputusan == null ? "" :(String) setupKeputusan.get("ID_INTROPERINTAH") == null ? "" : (String) setupKeputusan.get("ID_INTROPERINTAH");
    	String INTROFIELD1 = setupKeputusan == null ? "" :(String) setupKeputusan.get("INTROFIELD1") == null ? "" : (String) setupKeputusan.get("INTROFIELD1");
    	String INTROFIELD2 = setupKeputusan == null ? "" :(String) setupKeputusan.get("INTROFIELD2") == null ? "" : (String) setupKeputusan.get("INTROFIELD2");
    	String INTROFIELD3 = setupKeputusan == null ? "" :(String) setupKeputusan.get("INTROFIELD3") == null ? "" : (String) setupKeputusan.get("INTROFIELD3");
    	String CATATAN_SKRIN_PERINTAH = setupKeputusan == null ? "" :(String) setupKeputusan.get("CATATAN") == null ? "" : (String) setupKeputusan.get("CATATAN");
    	String INTRO_CATATAN = setupKeputusan == null ? "" :(String) setupKeputusan.get("INTRO_CATATAN") == null ? "" : (String) setupKeputusan.get("INTRO_CATATAN");
    	String CATATAN_DOCKIV = setupKeputusan == null ? "" :(String) setupKeputusan.get("CATATAN_DOCKIV") == null ? "" : (String) setupKeputusan.get("CATATAN_DOCKIV");
    	String CATATAN_KEPUTUSAN_PERBICARAAN = setupKeputusan == null ? "" :(String) setupKeputusan.get("CATATAN_KEPUTUSAN_PERBICARAAN") == null ? "" : (String) setupKeputusan.get("CATATAN_KEPUTUSAN_PERBICARAAN");


    	//htmlPageSetup += "<thead><div align=\"right\" class=\"onTT\" >NO_FAIL</div></thead>";

    	List listKehadiran = listKehadiran(session,ID_PERMOHONANSIMATI,ID_PERMOHONAN,ID_PERBICARAAN,ID_PEMOHON,db);
		List listTurutHadir = listTurutHadir(session,ID_PERBICARAAN, db);
		int totalKeterangan = 0;
		if(listKehadiran.size()>0)
		{
			for(int i=0; i<listKehadiran.size(); i++)
			{
				Map setupKehadiran = (Map) listKehadiran.get(i);
				String KETERANGAN = (String)setupKehadiran.get("KETERANGAN");
				String NOTA_PEGAWAI = (String)setupKehadiran.get("NOTA_PEGAWAI");
				if(!KETERANGAN.equals("") || !NOTA_PEGAWAI.equals(""))
				{
					totalKeterangan ++;
				}
			}
		}

		if(listTurutHadir.size()>0)
		{
			for(int x=0; x<listTurutHadir.size(); x++)
			{
				Map setupTurutHadir = (Map) listTurutHadir.get(x);
				String KETERANGAN = (String)setupTurutHadir.get("KETERANGAN");
				String NOTA_PEGAWAI = (String)setupTurutHadir.get("NOTA_PEGAWAI");
				if(!KETERANGAN.equals("") || !NOTA_PEGAWAI.equals(""))
				{
					totalKeterangan ++;
				}
			}
		}

		if(totalKeterangan>0)
		{
			htmlPageSetup += "<br><div style=\"border-bottom: 1px solid #000;width:100%;font-size: 140%;\" ><b>KETERANGAN KEHADIRAN</b></div>";
    		htmlPageSetup += htmlListKeterangan(session,formName,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,"Y",fontSize,db);

		}
    	myLogger.info("HTML KETERANGAN  ::::::::::::::: "+htmlPageSetup);

    	String CATATAN_INTRO = getValueRefTable(session, "TBLPPKRUJINTROPERINTAH","ID_INTROPERINTAH","","INTRO", ID_INTROPERINTAH, db);

    	myLogger.info("CATATAN_SKRIN_PERINTAH  ::::::::::::::: "+CATATAN_SKRIN_PERINTAH);
    	myLogger.info("CATATAN_INTRO  ::::::::::::::: "+CATATAN_INTRO);

    	if(!CATATAN_KEPUTUSAN_PERBICARAAN.equals(""))
    	{
	    	htmlPageSetup += "<div " +
	    			//"class=\"autoBreak\"" +
	    			" >";

	    	htmlPageSetup += "<br><div style=\"border-bottom: 1px solid #000;width:100%;font-size: 140%;\" ><b>KEPUTUSAN PERBICARAAN</b></div>";
	    	htmlPageSetup += "<div style=\"width:100%;"+fontSize+"\" ><br>"+CATATAN_KEPUTUSAN_PERBICARAAN+"</div>";
    		htmlPageSetup += "<div align=\"right\" class=\"onTT\"  style=\""+fontSize+"\" ><br><br>T.T....................................</div>";
	    	/*
			htmlPageSetup += openHTMLTableCatatanPerintah();
	    	htmlPageSetup += "<tr>";
	        htmlPageSetup += "<td valign=\"top\"   > ";
	        htmlPageSetup += "<div style=\"width:100%;"+fontSize+"\" ><br>"+CATATAN_KEPUTUSAN_PERBICARAAN+"</div>";
	        htmlPageSetup += "<div align=\"right\" class=\"onTT\"  style=\""+fontSize+"\" ><br><br>T.T....................................</div>";
	    	htmlPageSetup += "</td>";
	    	htmlPageSetup += "</tr>";
	    	htmlPageSetup += closeHTMLTableCatatanPerintah();
	    	*/


	    	htmlPageSetup += "</div>";
    	}

    	if(!CATATAN_DOCKIV.equals(""))
    	{
    		htmlPageSetup += "<div " +
    				//"class=\"autoBreak\"" +
    				" >";
    		htmlPageSetup += "<br><div style=\"100%;font-size: 120%;\" ><b>CATATAN</b></div>";
    		htmlPageSetup += "<div style=\"width:100%;"+fontSize+"\" ><br>"+CATATAN_DOCKIV+"</div>";
    		htmlPageSetup += "<div align=\"right\" class=\"onTT\"  style=\""+fontSize+"\" ><br><br>T.T....................................</div>";
    		/*
	    	htmlPageSetup += openHTMLTableCatatanPerintah();
	    	htmlPageSetup += "<tr>";
	        htmlPageSetup += "<td valign=\"top\"   > ";
	        htmlPageSetup += "<div style=\"width:100%;"+fontSize+"\" ><br>"+CATATAN_DOCKIV+"</div>";
	        htmlPageSetup += "<div align=\"right\" class=\"onTT\"  style=\""+fontSize+"\" ><br><br>T.T....................................</div>";
	    	htmlPageSetup += "</td>";
	    	htmlPageSetup += "</tr>";
	    	htmlPageSetup += closeHTMLTableCatatanPerintah();
	    	htmlPageSetup += "</div>";
	    	*/
    	}


    	if(!CATATAN_SKRIN_PERINTAH.equals("") || !CATATAN_INTRO.equals("") || !INTRO_CATATAN.equals(""))
    	{
	    	htmlPageSetup += "<div " +
	    			//"class=\"autoBreak\"" +
	    			" >";

	    	String tajukPerintah = "PERINTAH";

	    	if(!NO_PINDAAN.equals(""))
	    	{
	    		tajukPerintah = "PEMBETULAN PERINTAH ("+NO_PINDAAN+")";
	    	}

	    	// create new div for Nota section
	    	htmlPageSetup += "<br><div style=\"border-bottom: 1px solid #000;width:100%;font-size: 140%;\" ><b>"+tajukPerintah+"</b></div>";
			htmlPageSetup += openHTMLTableCatatanPerintah();
	    	htmlPageSetup += "<tr>";
	        htmlPageSetup += "<td valign=\"top\"   > ";

	        // update contents of Nota based on conditions
	        // if CATATAN_INTRO contents available
	        if(!CATATAN_INTRO.equals(""))
	    	{
	        	String specialKeterangan = CATATAN_INTRO;

	        	if(specialKeterangan.contains("[[FIELD1]]"))
				{
					if(INTROFIELD1.equals(""))
					{
						INTROFIELD1 = "..............................";
					}
					specialKeterangan = specialKeterangan.replace("[[FIELD1]]",INTROFIELD1);
				}

				if(specialKeterangan.contains("[[FIELD2]]"))
				{
					if(INTROFIELD2.equals(""))
					{
						INTROFIELD2 = "..............................";
					}
					specialKeterangan = specialKeterangan.replace("[[FIELD2]]",INTROFIELD2);
				}

				if(specialKeterangan.contains("[[FIELD3]]"))
				{
					if(INTROFIELD3.equals(""))
					{
						INTROFIELD3 = "..............................";
					}
					specialKeterangan = specialKeterangan.replace("[[FIELD3]]",INTROFIELD3);
				}
				CATATAN_INTRO = specialKeterangan;

	         	htmlPageSetup += "<div style=\"width:100%;"+fontSize+"\" align=\"justify\" ><br>"+CATATAN_INTRO+"</div>";
	    	}

	        // redundant line : already placed at line 7630
	        //htmlPageSetup += "<div style=\"width:100%;border-bottom: 1px solid #000;font-size: 100%;\" align=\"left\" ><b>PERINTAH</b></div>";

	        // if CATATAN_SKRIN_PERINTAH contents available
	        if(!CATATAN_SKRIN_PERINTAH.equals(""))
	    	{
	        	htmlPageSetup += "<div style=\"width:100%;"+fontSize+"\" ><br>"+CATATAN_SKRIN_PERINTAH+"</div>";
	    	}


	        if(!INTRO_CATATAN.equals(""))
	    	{
	        	htmlPageSetup += "<div style=\"width:100%;"+fontSize+"\" ><br>"+INTRO_CATATAN+"</div>";
	    	}

	        // redundant conditions
	        /*
	        if(!CATATAN_SKRIN_PERINTAH.equals("") || !CATATAN_INTRO.equals(""))
	    	{
	        	//htmlPageSetup += "<div align=\"right\" class=\"onTT\" ><br><br>T.T....................................</div>";
	    	}
	    	*/

	        // closing div sections
	    	htmlPageSetup += "</td>";
	    	htmlPageSetup += "</tr>";

	    	htmlPageSetup += closeHTMLTableCatatanPerintah();
	    	htmlPageSetup += "</div>";
    	}


    	if(totalKeterangan <= 0)
		{
    		//nak setting page brake
    		//htmlPageSetup += "</div>";
		}



    	if(FLAG_JENIS_KEPUTUSAN.equals("0"))
    	{
	    	htmlPageSetup += "<div " +
	    			//"class=\"autoBreak\"" +
	    			" >";
	    	String contentBE_HTA = contentBE_HTA(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PERMOHONANSIMATI,ID_PEMOHON,ID_PERINTAH,fontSize,db);
	    	htmlPageSetup += contentBE_HTA;
	    	htmlPageSetup += "</div>";
	    	htmlPageSetup += "<div " +
	    			//"class=\"autoBreak\"" +
	    			" >";
	    	String contentBE_HA = contentBE_HA(session,ID_PERBICARAAN,ID_PERMOHONAN,ID_PERMOHONANSIMATI,ID_PEMOHON,ID_PERINTAH,fontSize,db);
	    	htmlPageSetup += contentBE_HA;
	    	htmlPageSetup += "</div>";
	    	htmlPageSetup += "<div " +
	    			//"class=\"autoBreak\"" +
	    			" >";
	    	String contentBE_Pentadbir = contentBE_Pentadbir(session,ID_FAIL,ID_PERBICARAAN,ID_PERMOHONAN,ID_PERMOHONANSIMATI,ID_PEMOHON,ID_PERINTAH,fontSize,db);
	    	htmlPageSetup += contentBE_Pentadbir;
	    	htmlPageSetup += "</div>";
	    	htmlPageSetup += "<div " +
	    			//"class=\"autoBreak\"" +
	    			" >";
	    	String contentBE_Liabiliti = contentBE_Liabiliti(session,ID_FAIL,ID_PERBICARAAN,ID_PERMOHONAN,ID_PERMOHONANSIMATI,ID_PEMOHON,ID_PERINTAH,fontSize,db);
	    	htmlPageSetup += contentBE_Liabiliti;
	    	htmlPageSetup += "</div>";
	    	if(!contentBE_HTA.equals("") || !contentBE_HA.equals("") || !contentBE_Pentadbir.equals("") || !contentBE_Liabiliti.equals(""))
	    	{
	    		htmlPageSetup += "<div align=\"right\" class=\"onTT\"  style=\""+fontSize+"\" ><br><br>T.T....................................</div>";
	    	}
    	}


    	if(totalKeterangan>0)
		{
    		//nak setting page brake
    		//htmlPageSetup += "</div>";
		}



    	return htmlPageSetup;

    }


    public String contentBE_HTA(HttpSession session,String ID_PERBICARAAN,String ID_PERMOHONAN,String ID_PERMOHONANSIMATI,String ID_PEMOHON,String ID_PERINTAH,String fontSize,Db db) throws Exception {
    	String htmlPageSetup = "";
    	List list = rekodHTABorangE(session, ID_PERINTAH, ID_PERMOHONANSIMATI, db);
    	if(list.size()>0)
		{
    		/*
    		htmlPageSetup += openHTMLTableCatatanPerintah();
    		htmlPageSetup += "<tr>";
            htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border-bottom: 1px solid #000;" +
            		"font-size: 100%;\" > ";
        	htmlPageSetup += "<b>HARTA TAK ALIH YANG KENA DIBAHAGIKAN</b>";
        	htmlPageSetup += "</td>";
        	htmlPageSetup += "</tr>";
        	htmlPageSetup += closeHTMLTableCatatanPerintah()+"<br>";
        	*/

    		//htmlPageSetup += "<div style=\"border-bottom: 1px solid #000;width:100%\" ><b>HARTA TAK ALIH YANG KENA DIBAHAGIKAN</b><br></div>";

    		htmlPageSetup += "<br><div style=\"border-bottom: 1px solid #000;width:100%;font-size: 140%;\" ><b>HARTA TAK ALIH YANG KENA DIBAHAGIKAN</b></div>";
    		htmlPageSetup += "<br>";

    		//htmlPageSetup += "<table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\"   > ";
        	htmlPageSetup += openHTMLTableCatatanPerintah();

        	htmlPageSetup += "<thead ><tr>";
            htmlPageSetup += "<th valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" width=\"20%\" > ";
        	htmlPageSetup += "<b>No. Hakmilik dan Lot</b>";
        	htmlPageSetup += "</th>";
        	htmlPageSetup += "<th valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" width=\"20%\" > ";
        	htmlPageSetup += "<b>Mukim</b>";
        	htmlPageSetup += "</th>";
        	htmlPageSetup += "<th valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" width=\"20%\" > ";
        	htmlPageSetup += "<b>Daerah</b>";
        	htmlPageSetup += "</th>";
        	htmlPageSetup += "<th valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" width=\"40%\" > ";
        	htmlPageSetup += "<b>Nama benefisiari, No. K/P & Bahagian</b>";
        	htmlPageSetup += "</th>";
        	/*
        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;\" width=\"20%\"> ";
        	htmlPageSetup += "<b>Bahagian yang kena didaftarkan</b>";
        	htmlPageSetup += "</td>";
        	*/
        	htmlPageSetup += "</tr>";
        	htmlPageSetup += "</thead>";

        	htmlPageSetup += "<tfoot ><tr><td colspan=\"10\" style=\"border-top: 1px solid #000;\"></td></tr></tfoot>";


        	for(int i=0; i<list.size(); i++)
			{
				Map setup = (Map) list.get(i);
				if(setup!=null)
				{
					String MAKLUMAT_HTA = (String)setup.get("MAKLUMAT_HTA");
					String NAMA_MUKIM = (String)setup.get("NAMA_MUKIM");
					String NAMA_DAERAH = (String)setup.get("NAMA_DAERAH");
					String ID_PERINTAHHTAOBMST = (String)setup.get("ID_PERINTAHHTAOBMST");
					String STATUS_PEMILIKAN = (String)setup.get("STATUS_PEMILIKAN");
					String NAMA_SIMATI = (String)setup.get("NAMA_SIMATI");
					String KP_SIMATI = (String)setup.get("KP_SIMATI");
					String CATATAN_HARTA = (String)setup.get("CATATAN_HARTA");



					int SINGLEWARIS = (Integer)setup.get("SINGLEWARIS");
					int SINGLEPA = (Integer)setup.get("SINGLEPA");
					int DOUBLEPA = (Integer)setup.get("DOUBLEPA");
					int TRIPLEPA = (Integer)setup.get("TRIPLEPA");
					int FOURPA = (Integer)setup.get("FOURPA");
					int SINGLEPAOBHILANG = (Integer)setup.get("SINGLEPAOBHILANG");
					int DOUBLEPAOBHILANG = (Integer)setup.get("DOUBLEPAOBHILANG");
					int TRIPLEPAOBHILANG = (Integer)setup.get("TRIPLEPAOBHILANG");
					int FOURPAOBHILANG = (Integer)setup.get("FOURPAOBHILANG");


					htmlPageSetup += "<tr>";
		            htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" > ";
		        	htmlPageSetup += ""+MAKLUMAT_HTA;
		        	htmlPageSetup += "</td>";
		        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" > ";
		        	htmlPageSetup += ""+NAMA_MUKIM;
		        	htmlPageSetup += "</td>";
		        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" > ";
		        	htmlPageSetup += ""+NAMA_DAERAH;
		        	htmlPageSetup += "</td>";
		        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" > ";

		            htmlPageSetup += contentBE_HARTASubreport1(session,"HTA",ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,fontSize,db);


		            //Pemegang Amanah - PA
		            if(SINGLEPA > 0)
		            {
		            	htmlPageSetup += contentBE_HARTASubreport2(session,"HTA","PA",ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,fontSize,db);
		            }
		            if(DOUBLEPA > 0)
		            {
		            	htmlPageSetup += contentBE_HARTASubreport3(session,"HTA","PA",ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,fontSize,db);
		            }
		            if(TRIPLEPA > 0)
		            {
		            	htmlPageSetup += contentBE_HARTASubreport4(session,"HTA","PA",ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,fontSize,db);
		            }
		            if(FOURPA > 0)
		            {
		            	htmlPageSetup += contentBE_HARTASubreport5(session,"HTA","PA",ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,fontSize,db);
		            }

		        	//Pentadbir - PT
		            if(SINGLEPAOBHILANG > 0)
		            {
		            	htmlPageSetup += contentBE_HARTASubreport2(session,"HTA","PT",ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,fontSize,db);
		            }
		            if(DOUBLEPAOBHILANG > 0)
		            {
		            	htmlPageSetup += contentBE_HARTASubreport3(session,"HTA","PT",ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,fontSize,db);
		            }
		            if(TRIPLEPAOBHILANG > 0)
		            {
		            	htmlPageSetup += contentBE_HARTASubreport4(session,"HTA","PT",ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,fontSize,db);
		            }
		            if(FOURPAOBHILANG > 0)
		            {
		            	htmlPageSetup += contentBE_HARTASubreport5(session,"HTA","PT",ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,fontSize,db);
		            }


		        	if(!CATATAN_HARTA.equals(""))
		        	{
		        		htmlPageSetup += "<br><b>Catatan :</b> "+CATATAN_HARTA;
		        	}
		        	htmlPageSetup += "</td>";
		        	/*
		        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;\" > ";
		        	htmlPageSetup += "";
		        	htmlPageSetup += "</td>";
		        	*/

		        	htmlPageSetup += "</tr>";
				}
			}

			/*
			htmlPageSetup += "<tr>";
            htmlPageSetup += "<td colspan=\"3\" > ";
        	htmlPageSetup += "</td>";
        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" > ";
        	htmlPageSetup += "<div align=\"right\" class=\"onTT\" ><br><br>T.T....................................</div>";
        	htmlPageSetup += "</td>";
        	htmlPageSetup += "</tr>";
        	*/

			htmlPageSetup += closeHTMLTableCatatanPerintah();
			//htmlPageSetup += "<div align=\"right\" ><br><br>T.T....................................</div>";
			//htmlPageSetup += "<br>";
		}
    	return htmlPageSetup;
    }



    public String contentBE_Pentadbir(HttpSession session,String ID_FAIL,String ID_PERBICARAAN,String ID_PERMOHONAN,String ID_PERMOHONANSIMATI,
    		String ID_PEMOHON,String ID_PERINTAH,String fontSize,Db db) throws Exception {
    	String htmlPageSetup = "";

    	modelReport.setSenaraiOBPentadbir(ID_FAIL,"Y",db);
		List list = modelReport.getBeanSenaraiOBPentadbir();
		List listHTAPentadbir = rekodHTALantikPentadbir(session,ID_FAIL, db);
		List listHAPentadbir = rekodHALantikPentadbir(session,ID_FAIL, db);
		myLogger.info("listPentadbir ::::::::: "+list +" ID_FAIL : "+ID_FAIL);
		String namaPentadbir = "";
    	if(list.size()>0)
		{
    		htmlPageSetup += "<br><div style=\"border-bottom: 1px solid #000;width:100%;font-size: 140%;\" ><b>HARTA SIMATI YANG DITADBIRKAN</b></div>";

        	for(int i=0; i<list.size(); i++)
			{
        		Hashtable h = (Hashtable)list.get(i);
    			namaPentadbir = h.get("maklumatPentadbir").toString();
    			htmlPageSetup += "<div style=\"width:100%;"+fontSize+"\" >"+namaPentadbir.replace("\n", "<br>")+"</div>";

    			if(listHTAPentadbir.size()>0)
    			{
    				htmlPageSetup += "<br><div style=\"width:100%;"+fontSize+"\" ><b>HARTA TAK ALIH</b></div>";
    				for(int a=0; a<listHTAPentadbir.size(); a++)
    				{
    					Map h_hta = (Map)listHTAPentadbir.get(a);
    					myLogger.info(" >>>>>>>>>>>>>>>>>>>> h_hta ::::: "+h_hta);
    					String MAKLUMAT_HTA = (String)h_hta.get("MAKLUMAT_HTA");
    					String ID_JENISPERINTAH = (String)h_hta.get("ID_JENISPERINTAH");
    					String BA_WARIS = (String)h_hta.get("BA_WARIS");
    					String BB_WARIS = (String)h_hta.get("BB_WARIS");
    					String BA_SIMATI = (String)h_hta.get("BA_SIMATI");
    					String BB_SIMATI = (String)h_hta.get("BB_SIMATI");
    					String CATATAN = (String)h_hta.get("CATATAN");
    					String NILAI_HTA_TARIKHMOHON = (String)h_hta.get("NILAI_HTA_TARIKHMOHON");
    					String SEN_HTA = (String)h_hta.get("SEN_HTA");
    					htmlPageSetup += "<span style=\""+fontSize+"\" ><br>"+MAKLUMAT_HTA;
    					myLogger.info(" CATATAN >>>>>>>>>>> "+CATATAN);
    					htmlPageSetup += "<br>"+(ID_JENISPERINTAH.equals("1")?"(" + BA_WARIS + "/" + BB_WARIS + "bhg)" : "(" + BA_SIMATI + "/" + BB_SIMATI + "bhg)")
    					+""+(!CATATAN.equals("") ? "<br><br><b>Catatan : </b>" +CATATAN:"");
    					htmlPageSetup += "<br><span>";
    					myLogger.info("htmlPageSetup CATATAN ::::::::: "+htmlPageSetup);

    					//htmlPageSetup += "<br>Nilai Harta : RM "+(!NILAI_HTA_TARIKHMOHON.equals("") ? NILAI_HTA_TARIKHMOHON :"00") + "." + (!SEN_HTA.equals("") ? SEN_HTA:"00");

    				}
    			}
    			if(listHAPentadbir.size()>0)
    			{
    				htmlPageSetup += "<br><div style=\"width:100%;"+fontSize+"\" ><b>HARTA ALIH</b></div>";
    				for(int b=0; b<listHAPentadbir.size(); b++)
    				{
    					Map h_ha = (Map)listHAPentadbir.get(b);
    					myLogger.info(" >>>>>>>>>>>>>>>>>>>> h_ha ::::: "+h_ha+" MAKLUMAT_HA :: "+(String)h_ha.get("MAKLUMAT_HA"));
    					String MAKLUMAT_HA = (String)h_ha.get("MAKLUMAT_HA");
    					String ID_JENISPERINTAH = (String)h_ha.get("ID_JENISPERINTAH");
    					String BA_WARIS = (String)h_ha.get("BA_WARIS");
    					String BB_WARIS = (String)h_ha.get("BB_WARIS");
    					String BA_SIMATI = (String)h_ha.get("BA_SIMATI");
    					String BB_SIMATI = (String)h_ha.get("BB_SIMATI");
    					String CATATAN = (String)h_ha.get("CATATAN");
    					String NILAI_HA_TARIKHMOHON = (String)h_ha.get("NILAI_HA_TARIKHMOHON");
    					String SEN_HA = (String)h_ha.get("SEN_HA");
    					htmlPageSetup += "<span style=\""+fontSize+"\" ><br>"+MAKLUMAT_HA;
    					htmlPageSetup += "<br>"+(ID_JENISPERINTAH.equals("1")?"(" + (!BA_WARIS.equals("") ? BA_WARIS:"1") + "/" + (!BB_WARIS.equals("") ? BB_WARIS:"1") + " bhg)" : "(" + (!BA_SIMATI.equals("") ? BA_SIMATI:"1") + "/" + (!BB_SIMATI.equals("") ?BB_SIMATI:"1") + " bhg)")
    							+""+(!CATATAN.equals("") ? "<br><br><b>Catatan : </b>"+CATATAN:"");
    					htmlPageSetup += "<br></span>";
    					//htmlPageSetup += "<br>Nilai Harta : RM "+(!NILAI_HA_TARIKHMOHON.equals("") ? NILAI_HA_TARIKHMOHON :"00") + "." + (!SEN_HA.equals("") ? SEN_HA:"00");

    				}
    			}
    			//htmlPageSetup += "<div align=\"right\" class=\"onTT\" ><br><br>T.T....................................</div>";
			}




		}
    	return htmlPageSetup;
    }


    public String contentBE_Liabiliti(HttpSession session,String ID_FAIL,String ID_PERBICARAAN,String ID_PERMOHONAN,String ID_PERMOHONANSIMATI,
    		String ID_PEMOHON,String ID_PERINTAH,String fontSize,Db db) throws Exception {
    	String htmlPageSetup = "";

    	List rekodLiabilitiPentadbir = rekodLiabilitiPentadbir(session,ID_FAIL, db);
		myLogger.info("listPentadbir ::::::::: "+rekodLiabilitiPentadbir +" ID_FAIL : "+ID_FAIL);

		if(rekodLiabilitiPentadbir.size()>0)
		{
    		htmlPageSetup += "<br><div style=\"border-bottom: 1px solid #000;width:100%;font-size: 140%;\" ><b>LIABILITI</b></div>";

        	for(int i=0; i<rekodLiabilitiPentadbir.size(); i++)
			{
        		Map h = (Map)rekodLiabilitiPentadbir.get(i);
    			String MAKLUMAT_PEMIUTANG = h.get("MAKLUMAT_PEMIUTANG").toString();
    			String NILAI_HUTANG = h.get("NILAI_HUTANG").toString();
    			String SEN_HUTANG = h.get("SEN_HUTANG").toString();
    			htmlPageSetup += "<div style=\"width:100%;"+fontSize+"\" >";
    			htmlPageSetup += MAKLUMAT_PEMIUTANG.replace("\n", "<br>");
    			if(!NILAI_HUTANG.equals(""))
    			{
    				htmlPageSetup += "<br>"+NILAI_HUTANG+"."+SEN_HUTANG;
    			}
    			htmlPageSetup += "</div>";

    			//htmlPageSetup += "<div align=\"right\" class=\"onTT\" ><br><br>T.T....................................</div>";
			}
		}
    	return htmlPageSetup;
    }



    public String contentBE_HA(HttpSession session,String ID_PERBICARAAN,String ID_PERMOHONAN,String ID_PERMOHONANSIMATI,String ID_PEMOHON,String ID_PERINTAH,String fontSize,Db db) throws Exception {
    	String htmlPageSetup = "";
    	List list = rekodHABorangE(session, ID_PERINTAH, ID_PERMOHONANSIMATI, db);
    	if(list.size()>0)
		{
    		/*
    		htmlPageSetup += openHTMLTableCatatanPerintah();
    		htmlPageSetup += "<tr>";
            htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border-bottom: 1px solid #000;" +
            		"font-size: 100%;\" > ";
        	htmlPageSetup += "<b>HARTA ALIH YANG KENA DIBAHAGIKAN</b>";
        	htmlPageSetup += "</td>";
        	htmlPageSetup += "</tr>";
        	htmlPageSetup += closeHTMLTableCatatanPerintah()+"<br>";
        	*/

    		htmlPageSetup += "<br><div style=\"border-bottom: 1px solid #000;width:100%;font-size: 140%;\" ><b>HARTA ALIH YANG KENA DIBAHAGIKAN</b></div>";
    		htmlPageSetup += "<br>";
        	htmlPageSetup += openHTMLTableCatatanPerintah();
        	htmlPageSetup += "<thead ><tr>";
            htmlPageSetup += "<th valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" width=\"50%\" > ";
        	htmlPageSetup += "<b>Perihalan</b>";
        	htmlPageSetup += "</th>";
        	htmlPageSetup += "<th valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" width=\"50%\" > ";
        	htmlPageSetup += "<b>Nama benefisiari, No. K/P & Bahagian</b>";
        	htmlPageSetup += "</th>";
        	htmlPageSetup += "</tr></thead >";
        	htmlPageSetup += "<tfoot ><tr><td colspan=\"10\" style=\"border-top: 1px solid #000;\"></td></tr></tfoot>";

        	for(int i=0; i<list.size(); i++)
			{
				Map setup = (Map) list.get(i);
				if(setup!=null)
				{
					String MAKLUMAT_HA = (String)setup.get("MAKLUMAT_HA");
					String CATATAN_HARTA = (String)setup.get("CATATAN_HARTA");
					String ID_HA = (String)setup.get("ID_HA");
					String ID_PERINTAHHAOBMST = (String)setup.get("ID_PERINTAHHAOBMST");
					/*
					int SINGLEWARIS = (Integer)setup.get("SINGLEWARIS");
					int SINGLEPA = (Integer)setup.get("SINGLEPA");
					int DOUBLEPA = (Integer)setup.get("DOUBLEPA");
					int TRIPLEPA = (Integer)setup.get("TRIPLEPA");
					int FOURPA = (Integer)setup.get("FOURPA");
					int SINGLEPAOBHILANG = (Integer)setup.get("SINGLEPAOBHILANG");
					int DOUBLEPAOBHILANG = (Integer)setup.get("DOUBLEPAOBHILANG");
					int TRIPLEPAOBHILANG = (Integer)setup.get("TRIPLEPAOBHILANG");
					int FOURPAOBHILANG = (Integer)setup.get("FOURPAOBHILANG");
					*/

					htmlPageSetup += "<tr>";
		            htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" > ";
		        	htmlPageSetup += ""+MAKLUMAT_HA;
		        	htmlPageSetup += "</td>";
		        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;"+fontSize+"\" > ";

		            htmlPageSetup += contentBE_HARTASubreport1(session,"HA",ID_PERMOHONANSIMATI,ID_PERINTAHHAOBMST,"","","",fontSize,db);

		            //pemegang amanah - PA
		            htmlPageSetup += contentBE_HARTASubreport2(session,"HA","PA",ID_PERMOHONANSIMATI,ID_PERINTAHHAOBMST,"","","",fontSize,db);
		            htmlPageSetup += contentBE_HARTASubreport3(session,"HA","PA",ID_PERMOHONANSIMATI,ID_PERINTAHHAOBMST,"","","",fontSize,db);
		            htmlPageSetup += contentBE_HARTASubreport4(session,"HA","PA",ID_PERMOHONANSIMATI,ID_PERINTAHHAOBMST,"","","",fontSize,db);
		            htmlPageSetup += contentBE_HARTASubreport5(session,"HA","PA",ID_PERMOHONANSIMATI,ID_PERINTAHHAOBMST,"","","",fontSize,db);

		            //pentadbir - PT
		            htmlPageSetup += contentBE_HARTASubreport2(session,"HA","PT",ID_PERMOHONANSIMATI,ID_PERINTAHHAOBMST,"","","",fontSize,db);
		            htmlPageSetup += contentBE_HARTASubreport3(session,"HA","PT",ID_PERMOHONANSIMATI,ID_PERINTAHHAOBMST,"","","",fontSize,db);
		            htmlPageSetup += contentBE_HARTASubreport4(session,"HA","PT",ID_PERMOHONANSIMATI,ID_PERINTAHHAOBMST,"","","",fontSize,db);
		            htmlPageSetup += contentBE_HARTASubreport5(session,"HA","PT",ID_PERMOHONANSIMATI,ID_PERINTAHHAOBMST,"","","",fontSize,db);


		        	/*
		            htmlPageSetup += contentBE_HARTASubreport5(session,ID_PERMOHONANSIMATI,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,db);
		        	*/

		        	if(!CATATAN_HARTA.equals(""))
		        	{
		        		htmlPageSetup += "<br><b>Catatan :</b> "+CATATAN_HARTA+"</div>";
		        	}
		        	htmlPageSetup += "</td>";
		        	/*
		        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\"border: 1px solid #000;\" > ";
		        	htmlPageSetup += "";
		        	htmlPageSetup += "</td>";
		        	*/

		        	htmlPageSetup += "</tr>";
				}
			}

        	/*
        	htmlPageSetup += "<tr>";
            htmlPageSetup += "<td > ";
        	htmlPageSetup += "</td>";
        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" > ";
        	htmlPageSetup += "<div align=\"right\" class=\"onTT\" ><br><br>T.T....................................</div>";
        	htmlPageSetup += "</td>";
        	htmlPageSetup += "</tr>";
        	*/

			htmlPageSetup += closeHTMLTableCatatanPerintah();
			//htmlPageSetup += "<div align=\"right\" ><br><br>T.T....................................</div>";
		}
    	return htmlPageSetup;
    }

    public String contentBE_HARTASubreport1(HttpSession session,String type,String ID_PERMOHONANSIMATI, String ID_PERINTAHHTAOBMST,
    		String STATUS_PEMILIKAN,String NAMA_SIMATI,String KP_SIMATI,String fontSize,Db db) throws Exception {
    	String htmlPageSetup = "";
    	List list = rekodHTABorangESubReport1(session,type,ID_PERINTAHHTAOBMST,STATUS_PEMILIKAN,NAMA_SIMATI,KP_SIMATI,db);
    	if(list.size()>0)
		{
    		if(type.equals("HTA"))
    		{
	    		htmlPageSetup += "<div style=\""+fontSize+"\" >";
	    		if(STATUS_PEMILIKAN.equals("2"))
				{
					htmlPageSetup += "Dibatalkan "+NAMA_SIMATI+" ("+KP_SIMATI+")"+" sebagai Pemegang Amanah dan diturunmilik kepada:- <br>";
				}
	    		htmlPageSetup += "</div>";
    		}

    		htmlPageSetup += openHTMLTableCatatanPerintah();
			for(int i=0; i<list.size(); i++)
			{
				Map setup = (Map) list.get(i);
				if(setup!=null)
				{
					String NAMA_OB = (String)setup.get("NAMA_OB");
					String JENIS_WARGA = (String)setup.get("JENIS_WARGA");
					String NO_KP_BARU = (String)setup.get("NO_KP_BARU");
					String NO_KP_LAMA = (String)setup.get("NO_KP_LAMA");
					String NO_KP_LAIN = (String)setup.get("NO_KP_LAIN");
					String NO_KP = (String)setup.get("NO_KP");
					String ID_TARAFKPTG = (String)setup.get("ID_TARAFKPTG");
					String WARGANEGARA = (String)setup.get("WARGANEGARA");
					String BA_WARIS = (String)setup.get("BA_WARIS");
					String BB_WARIS = (String)setup.get("BB_WARIS");

					htmlPageSetup += "<tr>";
		            htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"5%\" style=\""+fontSize+"\" > ";
		        	htmlPageSetup += ""+(i+1)+".";
		        	htmlPageSetup += "</td>";
		        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"1%\" ></td> ";
		        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\""+fontSize+"\"   > ";

		        	if(type.equals("HTA"))
		        	{
		        		htmlPageSetup += (ID_TARAFKPTG.equals("6") || ID_TARAFKPTG.equals("8")?NAMA_OB:(NAMA_OB != null ? NAMA_OB + (JENIS_WARGA.equals("1") || JENIS_WARGA.equals("3")?(NO_KP_BARU!= null || NO_KP_LAMA != null || NO_KP_LAIN != null?"<br>No. KP: "+ NO_KP :" "):"<br>No. Pasport: "+NO_KP) :"TIADA")+(ID_TARAFKPTG.equals("6") || ID_TARAFKPTG.equals("8")?" ":"<br>Warganegara :"+WARGANEGARA));
		        	}
		        	else if(type.equals("HA"))
		        	{
		        		htmlPageSetup += ((ID_TARAFKPTG.equalsIgnoreCase("1") || ID_TARAFKPTG.equalsIgnoreCase("6")|| ID_TARAFKPTG.equalsIgnoreCase("8")|| ID_TARAFKPTG.equalsIgnoreCase("13")|| ID_TARAFKPTG.equalsIgnoreCase("16")) ? (NAMA_OB != null ? NAMA_OB : "TIADA") : "")
		        				+ "<br>"+(JENIS_WARGA.equalsIgnoreCase("1") || JENIS_WARGA.equalsIgnoreCase("3") ? (NO_KP_BARU!= null || NO_KP_LAMA != null || NO_KP_LAIN != null ? "No. KP : " + (NO_KP != null ? NO_KP : "") :"") : "No. Pasport : " + (NO_KP != null ? NO_KP : ""))
		        				+ (ID_TARAFKPTG.equalsIgnoreCase("6")  ? "No. KP : " + (NO_KP_BARU != null ? NO_KP_BARU : "-") : "")
		        				+ ""
		        				+ (ID_TARAFKPTG.equalsIgnoreCase("1") && (JENIS_WARGA.equalsIgnoreCase("1") || JENIS_WARGA.equalsIgnoreCase("3")) ? "<br>Warganegara : " + (WARGANEGARA != null ? WARGANEGARA : "") : "");
					}
		        	htmlPageSetup += "<br>"+BA_WARIS+" / "+BB_WARIS+" bhg";

		        	htmlPageSetup += "</td>";
		        	htmlPageSetup += "</tr>";

				}
			}
			htmlPageSetup += closeHTMLTableCatatanPerintah();

		}
    	return htmlPageSetup;
    }

	    public String b(String text){
	        return text.substring(0,text.length()-1);
	    }

        public String contentBE_HARTASubreport2(HttpSession session,String type, String subtype,String ID_PERMOHONANSIMATI, String ID_PERINTAHHTAOBMST,
    		String STATUS_PEMILIKAN,String NAMA_SIMATI,String KP_SIMATI,String fontSize, Db db) throws Exception {
    	String htmlPageSetup = "";
    	List list = rekodHTABorangESubReport2(session, type, subtype, ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI,db);
    	if(list.size()>0)
		{

    		htmlPageSetup += openHTMLTableCatatanPerintah();
			for(int i=0; i<list.size(); i++)
			{
				Map setup = (Map) list.get(i);
				if(setup!=null)
				{

					String NAMA_OB = (String)setup.get("NAMA_OB");
					String JENIS_WARGA = (String)setup.get("JENIS_WARGA");
					String NO_KP_BARU = (String)setup.get("NO_KP_BARU");
					String NO_KP_LAMA = (String)setup.get("NO_KP_LAMA");
					String NO_KP_LAIN = (String)setup.get("NO_KP_LAIN");
					String NO_KP = (String)setup.get("NO_KP");
					String ID_TARAFKPTG = (String)setup.get("ID_TARAFKPTG");
					String WARGANEGARA = (String)setup.get("WARGANEGARA");
					String BA_WARIS = (String)setup.get("BA_WARIS");
					String BB_WARIS = (String)setup.get("BB_WARIS");
					String STATUS_TADBIR = (String)setup.get("STATUS_TADBIR");
					String ID_PA1 = (String)setup.get("ID_PA1");
					int SINGLEWARIS = (Integer)setup.get("SINGLEWARIS");


					htmlPageSetup += "<tr>";
		            htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"5%\" style=\""+fontSize+"\"> ";
		        	htmlPageSetup += ""+(i+1+SINGLEWARIS)+".";
		        	htmlPageSetup += "</td>";
		        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"1%\" ></td> ";
		        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"   style=\""+fontSize+"\" > ";

		        	if(subtype.equals("PA"))
		        	{
			        	htmlPageSetup += (ID_TARAFKPTG.equals("1")?
			        			(NAMA_OB != null ? NAMA_OB:"TIADA")
			        			+ (JENIS_WARGA.equals("1") || JENIS_WARGA.equals("3")?(NO_KP_BARU!= null || NO_KP_LAMA != null
			        			|| NO_KP_LAIN != null?"<br>No. KP: "+ NO_KP :" "):"<br>No. Pasport: "+NO_KP):NAMA_OB)+
			        			(ID_TARAFKPTG.equals("6")?"":"<br>Warganegara :-"+WARGANEGARA)+
			        			(STATUS_TADBIR.equals("Y")?"<br>adalah wakil kepada:":"<br>s-");
		        	}
		        	else
		        	{
		        		htmlPageSetup += (NAMA_OB != null ? NAMA_OB:"TIADA")+ (NO_KP != null ? "<br>No. KP : "+ NO_KP:"")+(WARGANEGARA != null? "<br>Warganegara : "+WARGANEGARA: "")+"<br>adalah pentadbir";
		        		htmlPageSetup += "<br>"+BA_WARIS+" / "+BB_WARIS+" bhg";
		        	}

		        	if(subtype.equals("PA"))
		        	{
			        	List list2 = rekodHTABorangESubReport2_OnePA(session,type, ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI,ID_PA1,STATUS_TADBIR,db);
			        	if(list2.size()>0)
			    		{	htmlPageSetup += openHTMLTableCatatanPerintah();
			        		for(int x=0; x<list2.size(); x++)
			    			{
			    				Map setup2 = (Map) list2.get(x);
			    				if(setup2!=null)
			    				{
			    					String NAMA_OB2 = (String)setup2.get("NAMA_OB");
			    					String NO_KP2 = (String)setup2.get("NO_KP");
			    					String WARGANEGARA2 = (String)setup2.get("WARGANEGARA");
			    					String BA_WARIS2 = (String)setup2.get("BA_WARIS");
			    					String BB_WARIS2 = (String)setup2.get("BB_WARIS");

			    					htmlPageSetup += "<tr>";
			    		            htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"5%\" style=\""+fontSize+"\"> ";
			    		        	htmlPageSetup += ""+(x+1)+".";
			    		        	htmlPageSetup += "</td>";
			    		        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"1%\" ></td> ";
			    		        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"   style=\""+fontSize+"\"> ";
			    		        	htmlPageSetup += (NAMA_OB2 != null ? NAMA_OB2:"TIADA")+ (NO_KP2 != null ? NO_KP2:"")+(WARGANEGARA2 != null? "<br>Warganegara : "+WARGANEGARA2: "");
			    		        	htmlPageSetup += "<br>"+BA_WARIS2+" / "+BB_WARIS2+" bhg";
			    		        	htmlPageSetup += "</td>";
			    		        	htmlPageSetup += "</tr>";

			    				}
			    			}
			        		htmlPageSetup += closeHTMLTableCatatanPerintah();
			    		}
		        	}

		        	if(STATUS_TADBIR.equals("T") && type.equals("HTA") && subtype.equals("PA"))
		        	{
		        		htmlPageSetup += "<i><b>Kaveat Pendaftar dimasukkan</b></i>";
		        	}


		        	htmlPageSetup += "</td>";
		        	htmlPageSetup += "</tr>";

				}
			}
			htmlPageSetup += closeHTMLTableCatatanPerintah();

		}
    	return htmlPageSetup;
    }

    public String contentBE_HARTASubreport3(HttpSession session,String type,String subtype, String ID_PERMOHONANSIMATI, String ID_PERINTAHHTAOBMST,
    		String STATUS_PEMILIKAN,String NAMA_SIMATI,String KP_SIMATI,String fontSize, Db db) throws Exception {
    	String htmlPageSetup = "";
    	List list = rekodHTABorangESubReport3(session, type, subtype, ID_PERINTAHHTAOBMST,	ID_PERMOHONANSIMATI,db);
    	if(list.size()>0)
		{

    		for(int i=0; i<list.size(); i++)
			{
				Map setup = (Map) list.get(i);
				if(setup!=null)
				{

					String ID_PA1 = (String)setup.get("ID_PA1");
					String ID_PA2 = (String)setup.get("ID_PA2");
					String STATUS_TADBIR = (String)setup.get("STATUS_TADBIR");

					List list2 = rekodHTABorangESubReport3_2PA_MAIN(session,type, subtype, ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI,ID_PA1,ID_PA2,STATUS_TADBIR,db);
					if(list2.size()>0)
					{
						htmlPageSetup += openHTMLTableCatatanPerintah();
						for(int x=0; x<list2.size(); x++)
						{
							Map setup2 = (Map) list2.get(x);
							if(setup2!=null)
							{
								int ROWNUM_setup2 = (Integer)setup2.get("ROWNUM");
								String ID_PA1_setup2 = (String)setup2.get("ID_PA1");
								String ID_PA2_setup2 = (String)setup2.get("ID_PA2");
								String ID_PA3_setup2 = (String)setup2.get("ID_PA3");
								String BA_WARIS_setup2 = (String)setup2.get("BA_WARIS");
								String BB_WARIS_setup2 = (String)setup2.get("BB_WARIS");
								String STATUS_TADBIR_setup2 = (String)setup2.get("STATUS_TADBIR");
								String ID_OB_setup2 = (String)setup2.get("ID_OB");
								String NAMA_OB_setup2 = (String)setup2.get("NAMA_OB");
								String NO_KP_BARU_setup2 = (String)setup2.get("NO_KP_BARU");
								String NO_KP_LAMA_setup2 = (String)setup2.get("NO_KP_LAMA");
								String NO_KP_LAIN_setup2 = (String)setup2.get("NO_KP_LAIN");
								String JENIS_WARGA_setup2 = (String)setup2.get("JENIS_WARGA");
								int SINGLEWARIS_setup2 = (Integer)setup2.get("SINGLEWARIS");
								int SINGLEPA_setup2 = (Integer)setup2.get("SINGLEPA");
								int DOUBLEPA_setup2 = (Integer)setup2.get("DOUBLEPA");

								htmlPageSetup += "<tr>";
					            htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"5%\" style=\""+fontSize+"\"> ";
					        	htmlPageSetup += ""+(ROWNUM_setup2+SINGLEWARIS_setup2+SINGLEPA_setup2)+".";
					        	htmlPageSetup += "</td>";
					        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"1%\" ></td> ";
					        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"   style=\""+fontSize+"\" > ";
					        	//xxx

					        	List list3 = rekodHTABorangESubReport3_2PA_1(session,type,subtype,ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI, ID_PA1, ID_PA2, STATUS_TADBIR,db);
					        	if(list3.size()>0)
								{
					        		for(int y=0; y<list3.size(); y++)
									{
					        			Map setup3 = (Map) list3.get(y);
										if(setup3!=null)
										{
											String ID_TARAFKPTG_setup3 = (String)setup3.get("ID_TARAFKPTG");
											String NAMA_OB_setup3 = (String)setup3.get("NAMA_OB");
											String JENIS_WARGA_setup3 = (String)setup3.get("JENIS_WARGA");
											String NO_KP_BARU_setup3 = (String)setup3.get("NO_KP_BARU");
											String NO_KP_LAMA_setup3 = (String)setup3.get("NO_KP_LAMA");
											String NO_KP_LAIN_setup3 = (String)setup3.get("NO_KP_LAIN");
											String NO_KP_setup3 = (String)setup3.get("NO_KP");
											String WARGANEGARA_setup3 = (String)setup3.get("WARGANEGARA");
											int ROWNUM_setup3 = (Integer)setup3.get("ROWNUM");

											htmlPageSetup += (ID_TARAFKPTG_setup3.equals("1")?(NAMA_OB_setup3 != null ? NAMA_OB_setup3:"TIADA")+ (JENIS_WARGA_setup3.equals("1") ||
													JENIS_WARGA_setup3.equals("3")?(NO_KP_BARU_setup3!= null || NO_KP_LAMA_setup3 != null || NO_KP_LAIN_setup3 != null?"<br>No. KP: "+ NO_KP_setup3 :" "):"<br>No. Pasport: "+NO_KP_setup3):NAMA_OB_setup3)
													+(WARGANEGARA_setup3 != null? "<br>Warganegara : "+WARGANEGARA_setup3: "")+ (ROWNUM_setup3 == 1 ?"<br>dan ":"");


										}
									}
								}

					        	if(subtype.equals("PA"))
					        	{
						        	if(STATUS_TADBIR_setup2.equals("Y"))
						        	{
						        		htmlPageSetup += "<br>adalah wakil bersama kepada : ";
						        	}
						        	else if(STATUS_TADBIR_setup2.equals("T"))
						        	{
						        		htmlPageSetup += "<br>adalah pemegang amanah bersama kepada : ";
						        	}
					        	}
					        	else
					        	{
					        		htmlPageSetup += "<br>adalah wakil.";
					        		htmlPageSetup += "<br>"+BA_WARIS_setup2+" / "+BB_WARIS_setup2+" bhg";

					        	}

					        	if(subtype.equals("PA"))
					        	{
						        	List list4 = rekodHTABorangESubReport3_2PA_2(session, type, ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI, ID_PA1, ID_PA2, STATUS_TADBIR,db);
						        	if(list4.size()>0)
									{
						        		htmlPageSetup += openHTMLTableCatatanPerintah();
						        		for(int m=0; m<list4.size(); m++)
										{
						        			Map setup4 = (Map) list4.get(m);
											if(setup4!=null)
											{
												String NAMA_OB_setup4 = (String)setup4.get("NAMA_OB");
												String JENIS_WARGA_setup4 = (String)setup4.get("JENIS_WARGA");
												String NO_KP_BARU_setup4 = (String)setup4.get("NO_KP_BARU");
												String NO_KP_LAMA_setup4 = (String)setup4.get("NO_KP_LAMA");
												String NO_KP_LAIN_setup4 = (String)setup4.get("NO_KP_LAIN");
												String NO_KP_setup4 = (String)setup4.get("NO_KP");
												String WARGANEGARA_setup4 = (String)setup4.get("WARGANEGARA");
												String BA_WARIS_setup4 = (String)setup4.get("BA_WARIS");
												String BB_WARIS_setup4 = (String)setup4.get("BB_WARIS");

												htmlPageSetup += "<tr>";
									            htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"5%\" style=\""+fontSize+"\" > ";
									        	htmlPageSetup += ""+(m+1)+".";
									        	htmlPageSetup += "</td>";
									        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"1%\" ></td> ";
									        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\""+fontSize+"\"  > ";

												htmlPageSetup += (NAMA_OB_setup4 != null ? NAMA_OB_setup4:"TIADA")+ (NO_KP_setup4!= null ? NO_KP_setup4:"")+(WARGANEGARA_setup4 != null? "<br>Warganegara : "+WARGANEGARA_setup4: "");
												htmlPageSetup += "<br>"+BA_WARIS_setup4+" / "+BB_WARIS_setup4+" bhg";

												htmlPageSetup += "</td>";
						    		        	htmlPageSetup += "</tr>";
											}
										}
						        		htmlPageSetup += closeHTMLTableCatatanPerintah();
									}
					        	}

					        	if(STATUS_TADBIR.equals("T") && type.equals("HTA"))
					        	{
					        		htmlPageSetup += "<i><b>Kaveat Pendaftar dimasukkan</b></i>";
					        	}
					        	htmlPageSetup += "</td>";
		    		        	htmlPageSetup += "</tr>";
							}
						}
						htmlPageSetup += closeHTMLTableCatatanPerintah();
					}

				}
			}

		}
    	return htmlPageSetup;
    }


    public String contentBE_HARTASubreport4(HttpSession session,String type, String subtype, String ID_PERMOHONANSIMATI, String ID_PERINTAHHTAOBMST,
    		String STATUS_PEMILIKAN,String NAMA_SIMATI,String KP_SIMATI,String fontSize,Db db) throws Exception {
    	String htmlPageSetup = "";
    	List list = rekodHTABorangESubReport4(session, type, subtype, ID_PERINTAHHTAOBMST,	ID_PERMOHONANSIMATI,db);
    	if(list.size()>0)
		{

    		for(int i=0; i<list.size(); i++)
			{
				Map setup = (Map) list.get(i);
				if(setup!=null)
				{

					String ID_PA1 = (String)setup.get("ID_PA1");
					String ID_PA2 = (String)setup.get("ID_PA2");
					String ID_PA3 = (String)setup.get("ID_PA3");
					String STATUS_TADBIR = (String)setup.get("STATUS_TADBIR");

					List list2 = rekodHTABorangESubReport4_3PA_MAIN(session,type,subtype, ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI,ID_PA1,ID_PA2,STATUS_TADBIR,db);
					if(list2.size()>0)
					{
						htmlPageSetup += openHTMLTableCatatanPerintah();
						for(int x=0; x<list2.size(); x++)
						{
							Map setup2 = (Map) list2.get(x);
							if(setup2!=null)
							{
								int ROWNUM_setup2 = (Integer)setup2.get("ROWNUM");

								String BB_WARIS_setup2 = (String)setup2.get("BB_WARIS");
								String BA_WARIS_setup2 = (String)setup2.get("BA_WARIS");
								String ID_PA1_setup2 = (String)setup2.get("ID_PA1");
								String ID_PA2_setup2 = (String)setup2.get("ID_PA2");
								String ID_PA3_setup2 = (String)setup2.get("ID_PA3");
								String STATUS_TADBIR_setup2 = (String)setup2.get("STATUS_TADBIR");
								String ID_OB_setup2 = (String)setup2.get("ID_OB");
								String NAMA_OB_setup2 = (String)setup2.get("NAMA_OB");
								String NO_KP_BARU_setup2 = (String)setup2.get("NO_KP_BARU");
								String NO_KP_LAMA_setup2 = (String)setup2.get("NO_KP_LAMA");
								String NO_KP_LAIN_setup2 = (String)setup2.get("NO_KP_LAIN");
								String JENIS_WARGA_setup2 = (String)setup2.get("JENIS_WARGA");
								int SINGLEWARIS_setup2 = (Integer)setup2.get("SINGLEWARIS");
								int SINGLEPA_setup2 = (Integer)setup2.get("SINGLEPA");
								int DOUBLEPA_setup2 = (Integer)setup2.get("DOUBLEPA");
								int TRIPLEPA = (Integer)setup2.get("TRIPLEPA");

								htmlPageSetup += "<tr>";
					            htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"5%\" style=\""+fontSize+"\" > ";
					        	htmlPageSetup += ""+(ROWNUM_setup2+SINGLEWARIS_setup2+SINGLEPA_setup2)+".";
					        	htmlPageSetup += "</td>";
					        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"1%\" ></td> ";
					        	htmlPageSetup += "<td valign=\"top\"  align=\"left\" style=\""+fontSize+"\"   > ";
					        	//xxx

					        	List list3 = rekodHTABorangESubReport4_3PA_1(session, type, subtype, ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI, ID_PA1, ID_PA2, ID_PA3, STATUS_TADBIR,db);
					        	if(list3.size()>0)
								{
					        		int size_list3 = list3.size();
					        		for(int y=0; y<list3.size(); y++)
									{
					        			Map setup3 = (Map) list3.get(y);
										if(setup3!=null)
										{
											String seperatorList3 = "";

											if(((y+1) < size_list3) && (size_list3 > 1))
											{
												if((size_list3) - (y+1) == 1)
												{
													seperatorList3 = " dan<br>";
												}
												else
												{
													seperatorList3 = ",<br> ";
												}
											}

											//htmlPageSetup += " SIZE : "+list3.size();
											String ID_TARAFKPTG_setup3 = (String)setup3.get("ID_TARAFKPTG");
											String NAMA_OB_setup3 = (String)setup3.get("NAMA_OB");
											String JENIS_WARGA_setup3 = (String)setup3.get("JENIS_WARGA");
											String NO_KP_BARU_setup3 = (String)setup3.get("NO_KP_BARU");
											String NO_KP_LAMA_setup3 = (String)setup3.get("NO_KP_LAMA");
											String NO_KP_LAIN_setup3 = (String)setup3.get("NO_KP_LAIN");
											String NO_KP_setup3 = (String)setup3.get("NO_KP");
											String WARGANEGARA_setup3 = (String)setup3.get("WARGANEGARA");
											//int ROWNUM_setup3 = (Integer)setup3.get("ROWNUM");

											htmlPageSetup += (NAMA_OB_setup3 != null ? NAMA_OB_setup3:"TIADA")+ (NO_KP_setup3 != null ? "<br>No. KP : "
											+ NO_KP_setup3:"")+(WARGANEGARA_setup3 != null? "<br>Warganegara : "+WARGANEGARA_setup3: "");
											htmlPageSetup += seperatorList3;
										}
									}
								}

					        	if(subtype.equals("PA"))
					        	{
						        	if(STATUS_TADBIR_setup2.equals("Y"))
						        	{
						        		htmlPageSetup += "<br>adalah wakil bersama kepada : ";
						        	}
						        	else if(STATUS_TADBIR_setup2.equals("T"))
						        	{
						        		htmlPageSetup += "<br>adalah pemegang amanah bersama kepada : ";
						        	}
					        	}
					        	else
					        	{
					        		htmlPageSetup += "<br>adalah wakil.";
					        		htmlPageSetup += "<br>"+BA_WARIS_setup2+" / "+BB_WARIS_setup2+" bhg";
					        	}

					        	if(subtype.equals("PA"))
					        	{
						        	List list4 = rekodHTABorangESubReport4_3PA_2(session, ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI, ID_PA1, ID_PA2, ID_PA3, STATUS_TADBIR,db);
						        	if(list4.size()>0)
									{
						        		htmlPageSetup += openHTMLTableCatatanPerintah();
						        		for(int m=0; m<list4.size(); m++)
										{
						        			Map setup4 = (Map) list4.get(m);
											if(setup4!=null)
											{
												String NAMA_OB_setup4 = (String)setup4.get("NAMA_OB");
												String JENIS_WARGA_setup4 = (String)setup4.get("JENIS_WARGA");
												String NO_KP_BARU_setup4 = (String)setup4.get("NO_KP_BARU");
												String NO_KP_LAMA_setup4 = (String)setup4.get("NO_KP_LAMA");
												String NO_KP_LAIN_setup4 = (String)setup4.get("NO_KP_LAIN");
												String NO_KP_setup4 = (String)setup4.get("NO_KP");
												String WARGANEGARA_setup4 = (String)setup4.get("WARGANEGARA");
												String BA_WARIS_setup4 = (String)setup4.get("BA_WARIS");
												String BB_WARIS_setup4 = (String)setup4.get("BB_WARIS");

												htmlPageSetup += "<tr>";
									            htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"5%\" style=\""+fontSize+"\" > ";
									        	htmlPageSetup += ""+(m+1)+".";
									        	htmlPageSetup += "</td>";
									        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"1%\" ></td> ";
									        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\""+fontSize+"\"  > ";

												htmlPageSetup += (NAMA_OB_setup4 != null ? NAMA_OB_setup4:"TIADA")+ (NO_KP_setup4!= null ? NO_KP_setup4:"")+(WARGANEGARA_setup4 != null? "<br>Warganegara : "+WARGANEGARA_setup4: "");
												htmlPageSetup += "<br>"+BA_WARIS_setup4+" / "+BB_WARIS_setup4+" bhg";

												htmlPageSetup += "</td>";
						    		        	htmlPageSetup += "</tr>";
											}
										}
						        		htmlPageSetup += closeHTMLTableCatatanPerintah();
									}
					        	}

					        	if(STATUS_TADBIR.equals("T") && type.equals("HTA") && subtype.equals("PA"))
					        	{
					        		htmlPageSetup += "<i><b>Kaveat Pendaftar dimasukkan</b></i>";
					        	}
					        	htmlPageSetup += "</td>";
		    		        	htmlPageSetup += "</tr>";
							}
						}
						htmlPageSetup += closeHTMLTableCatatanPerintah();
					}

				}
			}

		}
    	return htmlPageSetup;
    }



    public String contentBE_HARTASubreport5(HttpSession session,String type,String subtype,String ID_PERMOHONANSIMATI, String ID_PERINTAHHTAOBMST,
    		String STATUS_PEMILIKAN,String NAMA_SIMATI,String KP_SIMATI,String fontSize, Db db) throws Exception {
    	String htmlPageSetup = "";
    	List list = rekodHTABorangESubReport5(session,type,subtype, ID_PERINTAHHTAOBMST,	ID_PERMOHONANSIMATI,db);
    	if(list.size()>0)
		{
    		for(int i=0; i<list.size(); i++)
			{
				Map setup = (Map) list.get(i);
				if(setup!=null)
				{
					String ID_PA1 = (String)setup.get("ID_PA1");
					String ID_PA2 = (String)setup.get("ID_PA2");
					String ID_PA3 = (String)setup.get("ID_PA3");
					String ID_PA4 = (String)setup.get("ID_PA4");
					String STATUS_TADBIR = (String)setup.get("STATUS_TADBIR");

					List list2 = rekodHTABorangESubReport5_4PA_MAIN(session,type,subtype,ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI,ID_PA1,ID_PA2,STATUS_TADBIR,db);
					if(list2.size()>0)
					{
						htmlPageSetup += openHTMLTableCatatanPerintah();
						for(int x=0; x<list2.size(); x++)
						{
							Map setup2 = (Map) list2.get(x);
							if(setup2!=null)
							{
								int ROWNUM_setup2 = (Integer)setup2.get("ROWNUM");
								String BA_WARIS_setup2 = (String)setup2.get("BA_WARIS");
								String BB_WARIS_setup2 = (String)setup2.get("BB_WARIS");
								String ID_PA1_setup2 = (String)setup2.get("ID_PA1");
								String ID_PA2_setup2 = (String)setup2.get("ID_PA2");
								String ID_PA3_setup2 = (String)setup2.get("ID_PA3");
								String ID_PA4_setup2 = (String)setup2.get("ID_PA4");
								String STATUS_TADBIR_setup2 = (String)setup2.get("STATUS_TADBIR");
								String ID_OB_setup2 = (String)setup2.get("ID_OB");
								String NAMA_OB_setup2 = (String)setup2.get("NAMA_OB");
								String NO_KP_BARU_setup2 = (String)setup2.get("NO_KP_BARU");
								String NO_KP_LAMA_setup2 = (String)setup2.get("NO_KP_LAMA");
								String NO_KP_LAIN_setup2 = (String)setup2.get("NO_KP_LAIN");
								String JENIS_WARGA_setup2 = (String)setup2.get("JENIS_WARGA");
								int SINGLEWARIS_setup2 = (Integer)setup2.get("SINGLEWARIS");
								int SINGLEPA_setup2 = (Integer)setup2.get("SINGLEPA");
								int DOUBLEPA_setup2 = (Integer)setup2.get("DOUBLEPA");
								int TRIPLEPA = (Integer)setup2.get("TRIPLEPA");
								int FOURPA = (Integer)setup2.get("FOURPA");

								htmlPageSetup += "<tr>";
					            htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"5%\" style=\""+fontSize+"\" > ";
					        	htmlPageSetup += ""+(ROWNUM_setup2+SINGLEWARIS_setup2+SINGLEPA_setup2)+".";
					        	htmlPageSetup += "</td>";
					        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"1%\" ></td> ";
					        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\""+fontSize+"\"   > ";
					        	//xxx

					        	List list3 = rekodHTABorangESubReport5_4PA_1(session,type, subtype, ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI, ID_PA1, ID_PA2, ID_PA3, ID_PA4,STATUS_TADBIR,db);
					        	if(list3.size()>0)
								{
					        		int size_list3 = list3.size();

					        		for(int y=0; y<list3.size(); y++)
									{
					        			Map setup3 = (Map) list3.get(y);
										if(setup3!=null)
										{
											String seperatorList3 = "";

											if(((y+1) < size_list3) && (size_list3 > 1))
											{
												if((size_list3) - (y+1) == 1)
												{
													seperatorList3 = " dan<br>";
												}
												else
												{
													seperatorList3 = ",<br> ";
												}
											}

											String ID_TARAFKPTG_setup3 = (String)setup3.get("ID_TARAFKPTG");
											String NAMA_OB_setup3 = (String)setup3.get("NAMA_OB");
											String JENIS_WARGA_setup3 = (String)setup3.get("JENIS_WARGA");
											String NO_KP_BARU_setup3 = (String)setup3.get("NO_KP_BARU");
											String NO_KP_LAMA_setup3 = (String)setup3.get("NO_KP_LAMA");
											String NO_KP_LAIN_setup3 = (String)setup3.get("NO_KP_LAIN");
											String NO_KP_setup3 = (String)setup3.get("NO_KP");
											String WARGANEGARA_setup3 = (String)setup3.get("WARGANEGARA");
											//int ROWNUM_setup3 = (Integer)setup3.get("ROWNUM");

											htmlPageSetup += (NAMA_OB_setup3 != null ? NAMA_OB_setup3:"TIADA")+ (NO_KP_setup3 != null ? "<br>No. KP : "
											+ NO_KP_setup3:"")+(WARGANEGARA_setup3 != null? "<br>Warganegara : "+WARGANEGARA_setup3: "");

											htmlPageSetup += seperatorList3;
										}
									}
								}

					        	if(subtype.equals("PA"))
					        	{
						        	if(STATUS_TADBIR_setup2.equals("Y"))
						        	{
						        		htmlPageSetup += "<br>adalah wakil bersama kepada : ";
						        	}
						        	else if(STATUS_TADBIR_setup2.equals("T"))
						        	{
						        		htmlPageSetup += "<br>adalah pemegang amanah bersama kepada : ";
						        	}
					        	}
					        	else
					        	{
					        		htmlPageSetup += "<br>adalah wakil.";
					        		htmlPageSetup += "<br>"+BA_WARIS_setup2+" / "+BB_WARIS_setup2+" bhg";
					        	}

					        	if(subtype.equals("PA"))
					        	{
						        	List list4 = rekodHTABorangESubReport5_4PA_2(session, type,ID_PERINTAHHTAOBMST,ID_PERMOHONANSIMATI, ID_PA1, ID_PA2, ID_PA3,ID_PA4, STATUS_TADBIR,db);
						        	if(list4.size()>0)
									{
						        		htmlPageSetup += openHTMLTableCatatanPerintah();
						        		for(int m=0; m<list4.size(); m++)
										{
						        			Map setup4 = (Map) list4.get(m);
											if(setup4!=null)
											{
												String NAMA_OB_setup4 = (String)setup4.get("NAMA_OB");
												String JENIS_WARGA_setup4 = (String)setup4.get("JENIS_WARGA");
												String NO_KP_BARU_setup4 = (String)setup4.get("NO_KP_BARU");
												String NO_KP_LAMA_setup4 = (String)setup4.get("NO_KP_LAMA");
												String NO_KP_LAIN_setup4 = (String)setup4.get("NO_KP_LAIN");
												String NO_KP_setup4 = (String)setup4.get("NO_KP");
												String WARGANEGARA_setup4 = (String)setup4.get("WARGANEGARA");
												String BA_WARIS_setup4 = (String)setup4.get("BA_WARIS");
												String BB_WARIS_setup4 = (String)setup4.get("BB_WARIS");

												htmlPageSetup += "<tr>";
									            htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"5%\" style=\""+fontSize+"\" > ";
									        	htmlPageSetup += ""+(m+1)+".";
									        	htmlPageSetup += "</td>";
									        	htmlPageSetup += "<td valign=\"top\"  align=\"right\" width=\"1%\" ></td> ";
									        	htmlPageSetup += "<td valign=\"top\"  align=\"left\"  style=\""+fontSize+"\"  > ";

												htmlPageSetup += (NAMA_OB_setup4 != null ? NAMA_OB_setup4:"TIADA")+ (NO_KP_setup4!= null ? NO_KP_setup4:"")+(WARGANEGARA_setup4 != null? "<br>Warganegara : "+WARGANEGARA_setup4: "");
												htmlPageSetup += "<br>"+BA_WARIS_setup4+" / "+BB_WARIS_setup4+" bhg";

												htmlPageSetup += "</td>";
						    		        	htmlPageSetup += "</tr>";
											}
										}
						        		htmlPageSetup += closeHTMLTableCatatanPerintah();
									}
					        	}


					        	if(STATUS_TADBIR.equals("T") && type.equals("HTA") && subtype.equals("PA"))
					        	{
					        		htmlPageSetup += "<i><b>Kaveat Pendaftar dimasukkan</b></i>";
					        	}
					        	htmlPageSetup += "</td>";
		    		        	htmlPageSetup += "</tr>";
							}
						}
						htmlPageSetup += closeHTMLTableCatatanPerintah();
					}

				}
			}

		}
    	return htmlPageSetup;
    }



	public String htmlListPerubahanBySrkin(HttpSession session,String ID_PERBICARAAN,String ID_PERMOHONAN,String ID_PEMOHON,String skrinName,String tableName,String flagPrint,Db db) throws Exception {
		//String html = "TEST <br><br>";

		String html = "";
		List listMainPerubahan = listMainPerubahan(session,ID_PERBICARAAN,skrinName,tableName,db);
		if (listMainPerubahan.size() != 0) {
			//html += "Nama Skrin : "+skrinName+"<br>";
			if(flagPrint.equals("N"))
			{
				html += htmlListPerubahanTajuk(session,ID_PERBICARAAN,skrinName,flagPrint,db);
			}
			for (int i = 0; i < listMainPerubahan.size(); i++) {
				Map mapMain = (Map) listMainPerubahan.get(i);
				String ID_SEJARAHBIMAIN  = (String) mapMain.get("ID_SEJARAHBIMAIN");
				String JENIS_AKTIVITI  = (String) mapMain.get("JENIS_AKTIVITI");
				String NAMA_PEGAWAI  = (String) mapMain.get("NAMA_PEGAWAI");
				String TARIKH_TRANSAKSI_FULL  = (String) mapMain.get("TARIKH_TRANSAKSI_FULL");
				String TARIKH_TRANSAKSI  = (String) mapMain.get("TARIKH_TRANSAKSI");
				String ID_PERMOHONANSIMATI  = (String) mapMain.get("ID_PERMOHONANSIMATI");
				String NAMA_FIELD_PK  = (String) mapMain.get("NAMA_FIELD_PK");
				String VALUE_FIELD_PK  = (String) mapMain.get("VALUE_FIELD_PK");
				String NAMA_TABLE  = (String) mapMain.get("NAMA_TABLE");


				String subject = "";
				if(skrinName.equals("simati"))
				{
					subject = getValueFromDataLatest(session,"NAMA_SIMATI",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
				}
				else if(skrinName.equals("pemohon"))
				{
					subject = getValueFromDataLatest(session,"NAMA_PEMOHON",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
				}
				else if(skrinName.equals("peguam"))
				{
					subject = getValueFromDataLatest(session,"NAMA_FIRMA",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
				}
				else if(skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi") || skrinName.equals("pemiutang"))
				{
					subject = getValueFromDataLatest(session,"NAMA_OB",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
				}
				else if(skrinName.equals("penghutang"))
				{
					subject = getValueFromDataLatest(session,"NAMA_PENGHUTANG",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
				}
				else if(skrinName.equals("htaah"))
				{
					String no_hakmilik = getValueFromDataLatest(session,"NO_HAKMILIK",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
					String no_lot = getValueFromDataLatest(session,"NO_PT",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
					String id_jenishm = getValueFromDataLatest(session,"ID_JENISHM",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
					String kod_hakmilik = getValueRefTable(session, "TBLRUJJENISHAKMILIK","ID_JENISHAKMILIK","","KOD_JENIS_HAKMILIK", id_jenishm, db);

					String id_negeri = getValueFromDataLatest(session,"ID_NEGERI",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
					String id_daerah = getValueFromDataLatest(session,"ID_DAERAH",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
					String id_mukim = getValueFromDataLatest(session,"ID_MUKIM",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
					String negeri = getValueRefTable(session, "TBLRUJNEGERI","ID_NEGERI","","NAMA_NEGERI", id_negeri, db);
					String daerah = getValueRefTable(session, "TBLRUJDAERAH","ID_DAERAH","","NAMA_DAERAH", id_daerah, db);
					String mukim = getValueRefTable(session, "TBLRUJMUKIM","ID_MUKIM","","NAMA_MUKIM", id_mukim, db);

					subject = kod_hakmilik + " " + no_hakmilik+ " ("+no_lot+") : "+ negeri+", "+daerah+", "+mukim;
				}
				else if(skrinName.equals("htaahx"))
				{
					String flag_kategori_hta = getValueFromDataLatest(session,"FLAG_KATEGORI_HTA",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE,db);
					String id_negeri = getValueFromDataLatest(session,"ID_NEGERI",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
					String id_daerah = getValueFromDataLatest(session,"ID_DAERAH",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
					String id_mukim = getValueFromDataLatest(session,"ID_MUKIM",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
					String kategori_harta = hardCodeDisplay(NAMA_TABLE,"FLAG_KATEGORI_HTA",flag_kategori_hta);
					String negeri = getValueRefTable(session, "TBLRUJNEGERI","ID_NEGERI","","NAMA_NEGERI", id_negeri, db);
					String daerah = getValueRefTable(session, "TBLRUJDAERAH","ID_DAERAH","","NAMA_DAERAH", id_daerah, db);
					String mukim = getValueRefTable(session, "TBLRUJMUKIM","ID_MUKIM","","NAMA_MUKIM", id_mukim, db);
					subject = kategori_harta + " : "+ negeri+", "+daerah+", "+mukim;
				}
				else if(skrinName.equals("ha"))
				{
					String id_jenisha = getValueFromDataLatest(session,"ID_JENISHA",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE,db);
					String jenisha = getValueRefTable(session, "TBLPPKRUJJENISHA","ID_JENISHA","","KETERANGAN", id_jenisha, db);
					String no_daftar = getValueFromDataLatest(session,"NO_DAFTAR",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE,db);
					String no_sijil = getValueFromDataLatest(session,"NO_DAFTAR",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
							skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE,db);
					subject = jenisha;
					if(!no_daftar.equals(""))
					{
						subject += " ("+ no_daftar+")";
					}
				}

				//hardCodeDisplay(column_name,value)



				myLogger.info("::: subject ::: "+subject);

				//html += "&nbsp;&nbsp;JENIS_AKTIVITI : "+JENIS_AKTIVITI+" TARIKH_TRANSAKSI_FULL : "+TARIKH_TRANSAKSI_FULL + " NAMA_PEGAWAI : "+NAMA_PEGAWAI+"<br>";

				if(flagPrint.equals("N"))
				{
					html += "<div class=\"viewMaklumatTR\" style=\"width:98%; float:right;\"  >" +
					"<table border=\"0\" cellspacing=\"1\" cellpadding=\"2\" width=\"100%\"  >";
				}
				else
				{
					html += "<div  style=\"width:100%;\" class=\"content-block\"  ><p>" +
					"<table style=\"border-collapse:collapse;\"  cellspacing=\"1\" cellpadding=\"2\"  width=\"100%\"  >";
				}


				if(flagPrint.equals("Y"))
				{
					html += "<thead ><tr>";
					html += "<th colspan=\"10\"><br></th>";
					html += "</tr></thead >";
				}



				String pb = "";
				/*
				if(flagPrint.equals("Y"))
				{
					//pb = "page-break";
					pb = "page-break";
				}
				*/

				html += "<tr class=\""+pb+"\"  >";
				html += "<td colspan=\"10\" >";

				if(flagPrint.equals("Y"))
				{
					html += htmlListPerubahanTajuk(session,ID_PERBICARAAN,skrinName,flagPrint,db);
				}

				if(flagPrint.equals("N"))
				{
					html += "<table  border=\"0\" cellspacing=\"1\" cellpadding=\"2\" width=\"100%\" >";
					html += "<tr >";
					html += "<td align=\"left\" valign=\"top\" width=\"65%\"><span class=\"blue\">"+subject+"</span></td>";

					String font_color = "blue";
					if(JENIS_AKTIVITI.equals("DELETE"))
					{
						font_color = "red";
					}

					html += "<td align=\"right\" valign=\"top\" width=\"35%\">Transaksi (Waktu) : <span class=\""+font_color+"\">"+JENIS_AKTIVITI+" ("+TARIKH_TRANSAKSI_FULL+")</span></td>";
					html += "</tr>";
					html += "</table>";
				}


				List listSubPerubahan = listSubPerubahan(session,ID_SEJARAHBIMAIN,db);
				if (listSubPerubahan.size() != 0) {
					if(flagPrint.equals("N"))
					{
						html += "<table border=\"0\" cellspacing=\"1\" cellpadding=\"2\" width=\"100%\" >";
						html += "<tr class=\"table_header\" >";
						html += "<td align=\"center\" valign=\"top\" width=\"5%\">Bil.</td>";
						html += "<td align=\"left\" valign=\"top\" width=\"25%\">Label</td>";
						html += "<td align=\"left\" valign=\"top\" width=\"35%\">Maklumat Asal</td>";
						html += "<td align=\"left\" valign=\"top\" width=\"35%\">Maklumat Baru</td>";
						html += "</tr>";
					}
					else
					{
						html += "<table " +
								" style=\"border-collapse:collapse;\" " +
								//" border=\"1\" " +
								" cellspacing=\"1\" cellpadding=\"2\" width=\"100%\" >";
						html += "<tr>";
						html += "<td style=\"font-size:70%;\" colspan = \"5\" align=\"left\" valign=\"top\" width=\"100%\">"+subject+"<br>Transaksi (Waktu) : "+JENIS_AKTIVITI+" ("+TARIKH_TRANSAKSI_FULL+")</td>";
						html += "</tr>";
						html += "<tr  >";
						html += "<td style=\"border: 1px solid black;font-size:70%;\" align=\"center\" valign=\"top\" width=\"5%\">Bil.</td>";
						html += "<td style=\"border: 1px solid black;font-size:70%;\" align=\"left\" valign=\"top\" width=\"25%\">Label</td>";
						html += "<td style=\"border: 1px solid black;font-size:70%;\" align=\"left\" valign=\"top\" width=\"35%\">Maklumat Asal</td>";
						html += "<td style=\"border: 1px solid black;font-size:70%;\" align=\"left\" valign=\"top\" width=\"35%\">Maklumat Baru</td>";
						html += "</tr>";
					}
					int bil = 0;

					String bahagian_atas_asal = "";
					String bahagian_bawah_asal = "";
					String bahagian_full_asal = "";
					String bahagian_atas_latest = "";
					String bahagian_bawah_latest = "";
					String bahagian_full_latest = "";
					boolean setDisplay = true;
					boolean setBahagian = false;

					if(skrinName.equals("htaah") || skrinName.equals("htaahx") || skrinName.equals("ha"))
					{
						bahagian_atas_asal = getValueFromDataAsal(session,"BA_SIMATI",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
								skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
						bahagian_bawah_asal = getValueFromDataAsal(session,"BB_SIMATI",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
								skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);

						if(!bahagian_atas_asal.equals("") || !bahagian_bawah_asal.equals(""))
						{
							bahagian_full_asal = bahagian_atas_asal + "/" + bahagian_bawah_asal;
						}

						myLogger.info("bahagian_full_asal : "+bahagian_full_asal);

						bahagian_atas_latest = getValueFromDataLatest(session,"BA_SIMATI",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
								skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);
						bahagian_bawah_latest = getValueFromDataLatest(session,"BB_SIMATI",ID_SEJARAHBIMAIN,ID_PERMOHONAN,ID_PEMOHON,ID_PERBICARAAN,
								skrinName,ID_PERMOHONANSIMATI,NAMA_FIELD_PK, VALUE_FIELD_PK, NAMA_TABLE, db);

						if(!bahagian_atas_latest.equals("") || !bahagian_bawah_latest.equals(""))
						{
							bahagian_full_latest = bahagian_atas_latest + "/" + bahagian_bawah_latest;
						}

						myLogger.info("bahagian_atas_latest : "+bahagian_atas_latest);
					}


					for (int x = 0; x < listSubPerubahan.size(); x++) {
						Map mapSub = (Map) listSubPerubahan.get(x);
						String REKOD_LABEL  = (String) mapSub.get("REKOD_LABEL");
						String NAMA_FIELD  = (String) mapSub.get("NAMA_FIELD");
						String VALUE_SEBELUM  = (String) mapSub.get("VALUE_SEBELUM");
						String KETERANGAN_SEBELUM  = (String) mapSub.get("KETERANGAN_SEBELUM");
						String VALUE_SELEPAS  = (String) mapSub.get("VALUE_SELEPAS");
						String KETERANGAN_SELEPAS  = (String) mapSub.get("KETERANGAN_SELEPAS");
						String TURUTAN  = (String) mapSub.get("TURUTAN");
						myLogger.info("JENIS_AKTIVITI : "+JENIS_AKTIVITI+" REKOD_LABEL : "+REKOD_LABEL+" VALUE_SEBELUM : "+VALUE_SEBELUM + " VALUE_SELEPAS : "+VALUE_SELEPAS+"");


						if(!REKOD_LABEL.equals("") && ((!VALUE_SEBELUM.equals(VALUE_SELEPAS) && !JENIS_AKTIVITI.equals("DELETE")) || JENIS_AKTIVITI.equals("DELETE"))
								&& (NAMA_FIELD.equals("BA_SIMATI") || NAMA_FIELD.equals("BB_SIMATI")))
						{
							myLogger.info("BAHAGIAN 1");
							if(setBahagian == false)
							{
								myLogger.info("BAHAGIAN 2");

								setBahagian = true;
								REKOD_LABEL = "Bahagian Simati";
								VALUE_SEBELUM = bahagian_full_asal;
								VALUE_SELEPAS =	bahagian_full_latest;
								myLogger.info("::: ADA PERUBAHAN BAHAGIAN ::: skrinName : "+skrinName+" setDisplay : "+setDisplay+" bahagian_full_asal : "+bahagian_full_asal+" bahagian_full_latest : "+bahagian_full_latest);
							}
							else
							{
								setDisplay = false;
							}
						}
						else
						{
							setDisplay = true;
						}

						myLogger.info("BAHAGIAN 3 REKOD_LABEL : "+REKOD_LABEL+"; VALUE_SEBELUM : "+VALUE_SEBELUM+"; VALUE_SELEPAS : "+VALUE_SELEPAS);

						if(!REKOD_LABEL.equals("") &&
								//!VALUE_SEBELUM.equals(VALUE_SELEPAS)
								(
										(!VALUE_SEBELUM.equals(VALUE_SELEPAS) && !JENIS_AKTIVITI.equals("DELETE"))
										||
										(JENIS_AKTIVITI.equals("DELETE") && !VALUE_SEBELUM.equals("0") && !VALUE_SEBELUM.equals(""))
								)
								&& setDisplay == true)
						{
							myLogger.info("BAHAGIAN 4");

							bil ++;

							String display_sebelum = "";
							String display_selepas = "";

							if(!KETERANGAN_SEBELUM.equals("") || !KETERANGAN_SELEPAS.equals(""))
							{
								display_sebelum = KETERANGAN_SEBELUM;
								display_selepas = KETERANGAN_SELEPAS;
							}
							else
							{
								display_sebelum = VALUE_SEBELUM;
								display_selepas = VALUE_SELEPAS;
							}

							if(JENIS_AKTIVITI.equals("DELETE"))
							{
								display_selepas = "";
							}


							String rowCss = "";
							if ( (bil % 2) == 0 )
							{
								rowCss = "row2";
							}
					        else
					        {
					        	rowCss = "row1";
					        }

							String styleTD = "  ";
							if(flagPrint.equals("Y"))
							{
								rowCss = "";
								styleTD = " style=\"border: 1px solid black;\" ";
							}

							html += "<tr class=\""+rowCss+"\" >";
							html += "<td "+styleTD+" align=\"center\" valign=\"top\" width=\"5%\" >"+bil+"</td>";
							html += "<td "+styleTD+" align=\"left\" valign=\"top\"  width=\"25%\">"+REKOD_LABEL+"</td>";
							if(display_sebelum.equals(""))
							{
								display_sebelum = "-";
							}
							if(display_selepas.equals(""))
							{
								display_selepas = "-";
							}
							html += "<td "+styleTD+" align=\"left\" valign=\"top\" width=\"35%\">"+display_sebelum+"</td>";
							html += "<td "+styleTD+" align=\"left\" valign=\"top\" width=\"35%\">"+display_selepas+"</td>";
							html += "</tr>";
							html += "<script> document.getElementById(\"checkRekodPerubahan\").value = \"Y\"; </script>  ";
						}
					}
					html += "</table>";
				}

				html += "</td></tr></table>";
				if(flagPrint.equals("Y"))
				{
					html +=	"</p>";
				}
				html +=	"</div>";
				//html += "<br>";
			}
			//html += "<br>";
		}
		return html;
	}


	public String htmlListKeteranganBySrkinPrint(
			HttpSession session,
			String NAMA,
			String PENGENALAN,
			String HUBUNGAN,
			String KETERANGAN,
			String NOTA_PEGAWAI,
			String flagPrint,
			int UMUR,
			String STATUS_OB,
			String PENJAGA,String fontSize,
			//String NAMA_HADIR,String PENGENALAN_HADIR,String HUBUNGAN_HADIR,String UMUR_HADIR,String STATUS_HADIR,String JENIS_OB,
			String CATATAN_WAKIL,Db db) throws Exception {
		String html = "";
		//String fontSize = "font-size: 130%;";
		//html += ">>>>>>>>>>>>>>>>>>>"+NAMA+"<br>";
		/*
		html += "<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-wysihtml5-master/lib/css/bootstrap.min.css\"></link>";
		html += "<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-wysihtml5-master/lib/css/prettify.css\"></link>";
		html += "<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.css\"></link>";
		*/
		//html += "<thead ><tr>";
		//html += "<th colspan=\"10\"><br></th>";
		//html += "</tr></thead >";

		//htmlPageSetup += "<br><div style=\"border-bottom: 1px solid #000;width:100%;font-size: 140%;\" ><b>HARTA TAK ALIH YANG KENA DIBAHAGIKAN</b></div>";

		html += "<br>" +
				//"<div " +
				//"class=\"autoBreak\"" +
				//" >" +
				"";

		String maklumatIndividu = "";


		//maklumatIndividu += "<div style=\"width:100%;font-size: 100%;\" >";

		//if(UMUR!= 0 && UMUR < 18)
		if(STATUS_OB.equals("2") || STATUS_OB.equals("4"))
		{
			if(!PENJAGA.equals(""))
			{
				maklumatIndividu += "<b>"+PENJAGA+"</b> <br>sebagai <b>PENJAGA</b> kepada <br>";
			}
		}
		maklumatIndividu += "<b>";
		maklumatIndividu += ""+NAMA;
		if(!PENGENALAN.equals(""))
		{
			maklumatIndividu += " ("+PENGENALAN+")";
		}
		if(!CATATAN_WAKIL.equals(""))
		{
			maklumatIndividu += CATATAN_WAKIL;
		}
		if(!HUBUNGAN.equals(""))
		{
			maklumatIndividu += "<br>HUBUNGAN : "+HUBUNGAN+"";
		}
		maklumatIndividu += "</b>";
		//maklumatIndividu += "</div> ";

		//tutup atas ni
		//html += maklumatIndividu;

		html +=	" <div align=\"right\" >";
		/*
			html += "<table " +
			" style=\"border-collapse:collapse;\" " +
			" cellspacing=\"1\" cellpadding=\"1\" width=\"100%\" >";

			html += "<tr  >";
			html += "<td style=\"font-size:70%;\" align=\"left\" valign=\"top\" ><b>";
			html += NAMA;
			if(!PENGENALAN.equals(""))
			{
				html += " ("+PENGENALAN+")";
			}
			if(!HUBUNGAN.equals(""))
			{
				html += "<br>"+HUBUNGAN+"";
			}
			html += "</b></td>";
			html += "</tr>";
			html += "</table> ";
	    */

			if(!KETERANGAN.equals("") || !NOTA_PEGAWAI.equals(""))
			{
				if(!KETERANGAN.equals(""))
				{
					/*
					html += "<table style=\"border-collapse:collapse;\" cellspacing=\"1\" cellpadding=\"1\" width=\"100%\" >";
					html += "<tr>";
					html += "<td width=\"1%\" >";
					html += "</td>";
					html += "<td width=\"1%\" >";
					html += "</td>";
					html += "<td width=\"98%\" valign=\"top\" >";
					*/


					html += "<div style=\"border-bottom: 1px solid #000;width:98%;"+fontSize+"\" align=\"left\" ><br><b>KETERANGAN "+maklumatIndividu+" </b></div>";
					html += "<div align=\"justify\" style=\"width:96%;"+fontSize+"\" ><br>"+KETERANGAN+"</div>";
					html += "<div align=\"right\" style=\"width:96%;"+fontSize+"\" class=\"onTT\" ><br><br>T.T....................................</div>";


					//html += "</td>";
					//html += "<td width=\"2%\" >";
					//html += "</td>";
					//html += "</tr>";
					//html += "</table>";
					//setting page brake
					//html += "</div>";
				}
				if(!NOTA_PEGAWAI.equals(""))
				{
					if(!KETERANGAN.equals(""))
					{
						  //html += "<div " +
						  		//" class=\"autoBreak\" " +
						  //		" >";
					}
					/*
					html += "<table style=\"border-collapse:collapse;\" cellspacing=\"1\" cellpadding=\"2\" width=\"100%\" >";
					html += "<tr>";
					html += "<td width=\"1%\" >";
					html += "</td>";
					html += "<td width=\"1%\" >";
					html += "</td>";
					html += "<td width=\"98%\" valign=\"top\" >";
					*/

					/*
					html += "<div style=\"border-bottom: 1px solid #000;width:100%\" ><br><b>Nota Pegawai</b></div>";
					html += "<div  align=\"justify\" ><br>"+NOTA_PEGAWAI+"</div>";
					html += "<div  align=\"right\" class=\"onTT\" ><br><br>T.T....................................</div>";
					*/

					html += "<div style=\"border-bottom: 1px solid #000;width:96%;"+fontSize+"\" align=\"left\"><br><b>NOTA PEGAWAI</b></div>";
					html += "<div align=\"justify\" style=\"width:96%;"+fontSize+"\" ><br>"+NOTA_PEGAWAI+"</div>";
					html += "<div align=\"right\" style=\"width:96%;"+fontSize+"\" class=\"onTT\" ><br><br>T.T....................................</div>";

					//html += "</td>";
					//html += "<td width=\"2%\" >";
					//html += "</td>";
					//html += "</tr>";
					//html += "</table>";

					//setting page brake
					if(!KETERANGAN.equals(""))
					{
						//html += "</div>";
					}
					if(KETERANGAN.equals(""))
					{
						//html += "</div>";
					}
				}

			}
			html += "</div>";
			return html;
	}


	public String htmlListKeteranganBySrkin(
			HttpSession session,
			String NAMA,
			String PENGENALAN,
			String HUBUNGAN,
			String KETERANGAN,
			String NOTA_PEGAWAI,
			String flagPrint,
			int UMUR,
			String STATUS_OB,
			String PENJAGA,
			//String NAMA_HADIR,String PENGENALAN_HADIR,String HUBUNGAN_HADIR,String UMUR_HADIR,String STATUS_HADIR,String JENIS_OB,
			String CATATAN_WAKIL,Db db) throws Exception {
		String html = "";

			html += "<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-wysihtml5-master/lib/css/bootstrap.min.css\"></link>";
			html += "<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-wysihtml5-master/lib/css/prettify.css\"></link>";
			html += "<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.css\"></link>";
			html += "<div class=\"viewMaklumatTR\" style=\"width:98%; float:right;\"  >";

			html +=	"<table border=\"0\" cellspacing=\"1\" cellpadding=\"2\" width=\"100%\"  >";

			html += "<tr >";
			html += "<td colspan=\"10\" >";

			html += "<table border=\"0\" cellspacing=\"1\" cellpadding=\"2\" width=\"100%\" >";
			html += "<tr class=\"table_header\" >";
			html += "<td align=\"left\" valign=\"top\" width=\"33%\">Nama</td>";
			html += "<td align=\"left\" valign=\"top\" width=\"33%\">Pengenalan</td>";
			html += "<td align=\"left\" valign=\"top\" width=\"33%\">Hubungan</td>";
			html += "</tr>";

			html += "<tr class=\"\" >";
			html += "<td  align=\"left\" valign=\"top\"  width=\"33%\">";


			//if(UMUR!= 0 && UMUR < 18)
			if(STATUS_OB.equals("2") || STATUS_OB.equals("4"))
			{
				if(!PENJAGA.equals(""))
				{
					html += ""+PENJAGA+" sebagai <b>PENJAGA</b> kepada ";
				}
			}


			html += NAMA;
			if(!CATATAN_WAKIL.equals(""))
			{
				html += CATATAN_WAKIL;
			}
			html += "</td>";
			html += "<td  align=\"left\" valign=\"top\" width=\"33%\">"+PENGENALAN+"</td>";
			html += "<td  align=\"left\" valign=\"top\" width=\"33%\">"+HUBUNGAN+"</td>";
			html += "</tr>";
			if(!KETERANGAN.equals(""))
			{
				html += "<tr class=\"\" >";
				html += "<td  align=\"left\" valign=\"top\" colspan=\"3\" class=\"table_header\" >Keterangan Yang Hadir</td>";
				html += "</tr>";
				html += "<tr class=\"\" >";
				html += "<td  align=\"left\" valign=\"top\" colspan=\"3\"><div class=\"divKeterangan\">"+KETERANGAN+"</div></td>";
				html += "</tr>";
			}

			if(!NOTA_PEGAWAI.equals(""))
			{
				html += "<tr class=\"\" >";
				html += "<td  align=\"left\" valign=\"top\" colspan=\"3\" class=\"table_header\" >Nota Pegawai </td>";
				html += "</tr>";
				html += "<tr class=\"\" >";
				html += "<td  align=\"left\" valign=\"top\" colspan=\"3\"><div class=\"divKeterangan\">"+NOTA_PEGAWAI+"</div></td>";
				html += "</tr>";
			}

			html += "<script> document.getElementById(\"checkRekodKeteranganhadir\").value = \"Y\"; </script>  ";
			html += "</table>";
			html += " </td></tr>";

			html += " </table>";
			html +=	"</div>";


		return html;
	}

	public String templateTRFilterLaporan(String label, String value, String content)
	{
		String styleFilter = " style=\"font-size:70%;\" ";
		String html = "";

		if(!content.equals("") && !value.equals(""))
		{
			value = content;
		}


		if(!value.equals(""))
		{
			html += "<tr><td valign=\"top\" align=\"left\" width=\"1%\" ></td><td valign=\"top\" width=\"28%\"  align=\"left\" "+styleFilter+" >"+label+"</td><td valign=\"top\"  align=\"center\" "+styleFilter+" width=\"1%\" >:</td>";
			html += "<td valign=\"top\"  align=\"left\" "+styleFilter+" width=\"70%\" >"+value+"</td></tr>";
		}
		return html;
	}

	public String getValueHash(Map h,String colName)
	{
		myLogger.info(" MAP : "+h+" colName : "+colName);
		String val = "";
		if(h!=null)
		{
			if(h.get(colName)!=null)
			{
				val = (String) h.get(colName);
				myLogger.info(" val : "+val+" colName : "+colName);
			}
		}
		return val.toUpperCase();
	}


	public String htmlListLaporanTukarPegawai(Map hFilter,HttpSession session,List list,Db db) throws Exception {
		String html = "";

		String styleHeader =" style=\"border: 1px solid black;font-size:70%;\" ";
		String styleContent =" style=\"border: 1px solid black;font-size:65%;\" ";

		html += "<table style=\"border-collapse:collapse;\"  cellspacing=\"1\" cellpadding=\"2\"  width=\"100%\" >";
		html += templateTRFilterLaporan("No. Tukar Pegawai", getValueHash(hFilter,"carianTukarPegawaiNO_TUKARPEGAWAI"),"");
		html += templateTRFilterLaporan("No. Fail", getValueHash(hFilter,"carianTukarPegawaiNO_FAIL"),"");
		html += templateTRFilterLaporan("Negeri Permohonan", getValueHash(hFilter,"carianTukarPegawaiID_NEGERIMHN"),getValueHash(hFilter,"carianTukarPegawaiID_NEGERIMHNCONTENT"));
		html += templateTRFilterLaporan("Negeri Pegawai Ganti", getValueHash(hFilter,"carianTukarPegawaiID_NEGERIPEGAWAIBARU"),getValueHash(hFilter,"carianTukarPegawaiID_NEGERIPEGAWAIBARUCONTENT"));
		html += templateTRFilterLaporan("Status Tukar Pegawai", getValueHash(hFilter,"carianTukarPegawaiSTATUS_TUKARPEGAWAI"),getValueHash(hFilter,"carianTukarPegawaiSTATUS_TUKARPEGAWAICONTENT"));

		html += templateTRFilterLaporan("Nama Pegawai Asal", getValueHash(hFilter,"carianTukarPegawaiNAMAPEGAWAIASAL"),"");
		html += templateTRFilterLaporan("Nama Pegawai Ganti", getValueHash(hFilter,"carianTukarPegawaiNAMAPEGAWAIBARU"),"");
		html += templateTRFilterLaporan("Tarikh Mohon Pertukaran (Mula)", getValueHash(hFilter,"carianTukarPegawaiTARIKH_MOHONMULA"),"");
		html += templateTRFilterLaporan("Tarikh Mohon Pertukaran (Akhir)", getValueHash(hFilter,"carianTukarPegawaiTARIKH_MOHONAKHIR"),"");
		html += templateTRFilterLaporan("Tarikh Perbicaraan (Mula)", getValueHash(hFilter,"carianTukarPegawaiTARIKH_BICARAMULA"),"");
		html += templateTRFilterLaporan("Tarikh Perbicaraan (Akhir)", getValueHash(hFilter,"carianTukarPegawaiTARIKH_BICARAAKHIR"),"");


		html += "</table>";




		html += "<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-wysihtml5-master/lib/css/bootstrap.min.css\"></link>";
		html += "<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-wysihtml5-master/lib/css/prettify.css\"></link>";
		html += "<link rel=\"stylesheet\" type=\"text/css\" href=\"../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.css\"></link>";

		html += "<div  style=\"width:100%;\" class=\"content-block\"  ><p>" +
			"<table style=\"border-collapse:collapse;\"  cellspacing=\"1\" cellpadding=\"2\"  width=\"100%\"  >";

		html += "<thead ><tr>";
		html += "<th colspan=\"10\"><br></th>";
		html += "</tr>" +
				"<tr>";
		html += "<th align=\"center\" valign=\"top\" "+styleHeader+" >Bil.</th>";
		html += "<th align=\"left\" valign=\"top\" "+styleHeader+" >No. Tukar Pegawai</th>";
		html += "<th align=\"left\" valign=\"top\" "+styleHeader+" >No. Fail (Bil. Bicara)</th>";
		html += "<th align=\"left\" valign=\"top\" "+styleHeader+" >Pegawai Asal</th>";
		html += "<th align=\"left\" valign=\"top\" "+styleHeader+" >Pegawai Ganti</th>";
		html += "<th align=\"left\" valign=\"top\" "+styleHeader+" >Keputusan</th>";
		html += "</tr></thead >";

		if(list.size()>0)
		{
			for(int i=0; i<list.size(); i++)
			{
				Map setup = (Map) list.get(i);
				String NO_TUKARPEGAWAI = (String)setup.get("NO_TUKARPEGAWAI");
				String NO_FAIL = (String)setup.get("NO_FAIL");
				String BIL_BICARA = (String)setup.get("BIL_BICARA");
				String NAMA_PEGAWAI_ASAL = (String)setup.get("NAMA_PEGAWAI_ASAL");
				String NAMA_PEGAWAI_BARU = (String)setup.get("NAMA_PEGAWAI_BARU");
				String KETERANGAN_STATUS_TUKARPEGAWAI = (String)setup.get("KETERANGAN_STATUS_TUKARPEGAWAI");

				html += "<tr >";
				html += "<td align=\"center\" valign=\"top\" "+styleContent+" >"+(i+1)+"</td>";
				html += "<td align=\"left\" valign=\"top\" "+styleContent+" >"+NO_TUKARPEGAWAI+"</td>";
				html += "<td align=\"left\" valign=\"top\" "+styleContent+" >"+NO_FAIL+" ("+BIL_BICARA+")"+"</td>";
				html += "<td align=\"left\" valign=\"top\" "+styleContent+" >"+NAMA_PEGAWAI_ASAL+"</td>";
				html += "<td align=\"left\" valign=\"top\" "+styleContent+" >"+NAMA_PEGAWAI_BARU+"</td>";
				html += "<td align=\"left\" valign=\"top\" "+styleContent+" >"+KETERANGAN_STATUS_TUKARPEGAWAI+"</td>";
				html += "</tr>";
			}
		}



		html += "</table>";
		html +=	"</p>";
		html +=	"</div>";

		return html;
	}



	public List listColumnForSenaraiOB(HttpSession session,String skrinName, String current_previous)throws Exception {
		List listColumnForSenarai = null;
		String namaList = "list"+skrinName+current_previous;
		myLogger.info(" listColumnForSenarai namaList : "+namaList);
		listColumnForSenarai = Collections.synchronizedList(new ArrayList());
		//susun ikot turutan
		if(skrinName.equals("waris") || skrinName.equals("ob")  || skrinName.equals("pemiutang") || skrinName.equals("saksi") || skrinName.equals("penghutang"))
		{
			String labelNama = "";
			if(skrinName.equals("waris"))
			{
				labelNama = "Nama Waris";
			}
			else if(skrinName.equals("ob"))
			{
				labelNama = "Nama Ob";
			}
			else if(skrinName.equals("saksi"))
			{
				labelNama = "Nama Saksi";
			}
			else if(skrinName.equals("pemiutang"))
			{
				labelNama = "Nama Pemiutang";
			}
			else if(skrinName.equals("penghutang"))
			{
				labelNama = "Nama Penghutang";
			}

			if(skrinName.equals("penghutang"))
			{
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_PENGHUTANG", labelNama, "left", namaList));
			}
			else if(skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi") || skrinName.equals("pemiutang"))
			{
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_OB", labelNama, "left", namaList));

			}

			String labelMyid = "MyID";
			if(skrinName.equals("pemiutang") || skrinName.equals("penghutang"))
			{
				labelMyid = "MyID / No. Pendaftaran";
			}

			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "PENGENALAN", labelMyid, "left", namaList));
			if(skrinName.equals("waris"))
			{
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "UMUR", "Umur", "center", namaList));
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "SAUDARA", "Talian Persaudaraan", "left", namaList));
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "STATUS", "Status", "left", namaList));
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "LAPIS", "Lapisan", "center", namaList));
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_PARENT", "Waris Yang Meninggal", "left", namaList));
			}
			else if(skrinName.equals("pemiutang"))
			{
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NILAI_HUTANG_KETERANGAN", "Nilai Hutang (RM)", "right", namaList));
			}
			else if(skrinName.equals("penghutang"))
			{
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "JENIS_PENGHUTANG_KETERANGAN", "Jenis Penghutang", "left", namaList));
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "JUMLAH_HUTANG_KETERANGAN", "Nilai Hutang (RM)", "right", namaList));
			}
			else if(skrinName.equals("ob"))
			{
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "STATUS_OB_KETERANGAN", "Status OB", "left", namaList));
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "STATUS_OB", "", "hidden", namaList));
			}
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "JENIS_AKTIVITI", "Aktiviti", "center", namaList));
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_PERMOHONANSIMATI", "", "hidden", namaList));
			if(skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi") || skrinName.equals("pemiutang"))
			{
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_OBPERMOHONAN", "", "hidden", namaList));
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_OB", "", "hidden", namaList));
			}
			else if(skrinName.equals("penghutang"))
			{
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_PENGHUTANG", "", "hidden", namaList));
			}
			if(skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi") || skrinName.equals("pemiutang"))
			{
				listColumnForSenarai.add(getColumnForSenarai(session,skrinName,"FLAG_PEMOHON", "Sebagai Pemohon", "center", namaList));

			}
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_SEJARAHBIMAIN", "", "hidden", namaList));
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_TABLE", "", "hidden", namaList));
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_FIELD_PK", "", "hidden", namaList));

		}
		return listColumnForSenarai;
	}


	public List listColumnForSenaraiHartaAlih(HttpSession session,String skrinName, String current_previous)throws Exception {
		List listColumnForSenarai = null;
		String namaList = "list"+skrinName+current_previous;
		myLogger.info(" listColumnForSenaraiHartaAlih namaList : "+namaList);
		listColumnForSenarai = Collections.synchronizedList(new ArrayList());
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "MAKLUMAT_HA", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "BAHAGIAN_SIMATI", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "JENIS_HA", "Jenis Harta Alih", "left", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NO_DAFTAR", "No. Rujukan UPT / No Daftar / No Akaun / No Ahli", "left", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "BAHAGIAN_SIMATI", "Bahagian Simati", "center", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "JENIS_AKTIVITI", "Aktiviti", "center", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_SEJARAHBIMAIN", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_TABLE", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_FIELD_PK", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_HAPERMOHONAN", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_HA", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "FLAG_PA", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "FLAG_PT", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "FLAG_SELESAI", "", "hidden", namaList));
		return listColumnForSenarai;
	}

	public List listColumnForSenaraiPeguam(HttpSession session,String skrinName, String current_previous)throws Exception {
		List listColumnForSenarai = null;
		String namaList = "list"+skrinName+current_previous;
		myLogger.info(" listColumnForSenaraiPeguam namaList : "+namaList);
		listColumnForSenarai = Collections.synchronizedList(new ArrayList());
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_FIRMA", "Nama Firma", "left", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NO_RUJUKAN_FIRMA", "No. Rujukan", "left", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "JENIS_AKTIVITI", "Aktiviti", "center", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_SEJARAHBIMAIN", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_TABLE", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_FIELD_PK", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_PEGUAM", "", "hidden", namaList));
		return listColumnForSenarai;
	}

	public List listColumnForSenaraiHarta(HttpSession session,String skrinName, String current_previous)throws Exception {
		List listColumnForSenarai = null;
		String namaList = "list"+skrinName+current_previous;
		myLogger.info(" listColumnForSenaraiHarta namaList : "+namaList);
		listColumnForSenarai = Collections.synchronizedList(new ArrayList());
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "MAKLUMAT_HTA", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "BAHAGIAN_SIMATI", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_NEGERI", "Negeri", "left", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_DAERAH", "Daerah", "left", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_MUKIM", "Mukim", "left", namaList));
		if(skrinName.equals("htaah"))
		{
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NO_HAKMILIK_FULL", "No. Hakmilik", "left", namaList));
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NO_PT", "No. Lot / PT", "left", namaList));
		}
		else if(skrinName.equals("htaahx"))
		{
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NO_PERJANJIAN_FULL", "No. Perjanjian / Tarikh", "left", namaList));
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NO_ROH", "No. Roh", "left", namaList));
			listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "JENIS_KEPENTINGAN", "Jenis Kepentingan", "left", namaList));
		}
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "BAHAGIAN_SIMATI", "Bahagian Simati", "center", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "JENIS_AKTIVITI", "Aktiviti", "center", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_SEJARAHBIMAIN", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_TABLE", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "NAMA_FIELD_PK", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_HTAPERMOHONAN", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "ID_HTA", "", "hidden", namaList));

		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "FLAG_PA", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "FLAG_PT", "", "hidden", namaList));
		listColumnForSenarai.add(getColumnForSenarai(session,skrinName, "FLAG_SELESAI", "", "hidden", namaList));

		return listColumnForSenarai;
	}

	public Map getColumnForSenarai(HttpSession session, String skrinName, String columnName, String label, String align, String listName)
			throws Exception
	{
		Map h = Collections.synchronizedMap(new HashMap());
		h.put("COLUMN_NAME",columnName);
		h.put("LABEL",label);
		h.put("SKRIN_NAME",skrinName);
		h.put("LIST_NAME",listName);
		h.put("ALIGN",align);
		//myLogger.info(" getColumnForSenarai : "+h);
		return h;
	}


	public String queryHistoryOBCheckPemohon(String ID_PERMOHONANSIMATI,String ID_PERBICARAAN,String checkPemohon)
	{
		String queryHistory = "";

			queryHistory +=  " SELECT TH.* FROM ( "+
				" SELECT M.JENIS_AKTIVITI,M.ID_SEJARAHBIMAIN, M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK, M.VALUE_FIELD_PK,  "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_OBPERMOHONAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_OBPERMOHONAN, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_OB' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_OB, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PEMOHON' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_PEMOHON, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_SIMATI, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ALAMAT_1' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ALAMAT_1,  "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ALAMAT_2' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ALAMAT_2,  "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ALAMAT_3' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ALAMAT_3,  "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'POSKOD' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS POSKOD,   "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_BANDAR' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_BANDAR,   "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_NEGERI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_NEGERI,  "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NAMA_PELBAGAINEGARA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NAMA_PELBAGAINEGARA  "+
				" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S "+
				" WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN  ";
			queryHistory += " AND M.JENIS_AKTIVITI IN ('INSERT','UPDATE') ";
			queryHistory += " AND M.NAMA_TABLE = 'TBLPPKOBPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' AND ID_PERBICARAAN = '"+ID_PERBICARAAN+"'  "+
			" GROUP BY M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN,M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK, M.VALUE_FIELD_PK ) TH ";
			if(checkPemohon.equals("Y"))
			{
				queryHistory += " WHERE TH.ID_PEMOHON IS NOT NULL ";
			}
		return queryHistory;
	}


	public String queryHistoryOB(String skrinName,String id,String ID_PERBICARAAN,String jenisAktiviti)
	{
		String queryHistory = "";

			queryHistory +=  " SELECT TH.*, SAU.KETERANGAN AS SAUDARA FROM ( "+
				" SELECT M.JENIS_AKTIVITI,M.ID_SEJARAHBIMAIN, M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK,  "+
				" NULL AS NAMA_PARENT, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_OBPERMOHONAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_OBPERMOHONAN, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_OB' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_OB, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'JENIS_PEMIUTANG' THEN  NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()')) AS JENIS_PEMIUTANG, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PEMOHON' THEN  NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()')) AS ID_PEMOHON, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'TARIKH_MATI' THEN  NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()')) AS TARIKH_MATI, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_SURAT_BERANAK' THEN  NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()')) AS NO_SURAT_BERANAK, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_SIMATI, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NAMA_OB' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NAMA_OB, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_KP_BARU' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NO_KP_BARU, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_KP_LAMA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NO_KP_LAMA, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'JENIS_KP' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS JENIS_KP, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_KP_LAIN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NO_KP_LAIN, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'UMUR' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS UMUR, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'STATUS_HIDUP' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS STATUS_HIDUP, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'STATUS_OB' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS STATUS_OB, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_TARAFKPTG' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_TARAFKPTG, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_SAUDARA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_SAUDARA, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'LAPIS' THEN S.VALUE_SELEPAS END)) )).extract ('//text()')) AS LAPIS, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PARENTOB' THEN  NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()')) AS ID_PARENTOB, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NILAI_HUTANG' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NILAI_HUTANG, "+
				" NULL AS ID_HUBUNGANOB "+
				" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S "+
				" WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN  ";

				if(!jenisAktiviti.equals(""))
				{
					if(jenisAktiviti.equals("INSERTDELETE"))
					{
						queryHistory += " AND M.JENIS_AKTIVITI IN ('INSERT','DELETE') ";
					}
					else
					{
						queryHistory += " AND M.JENIS_AKTIVITI = '"+jenisAktiviti+"' ";
					}
				}
				else
				{
					queryHistory += " AND M.JENIS_AKTIVITI = 'UPDATE' ";
				}

				if(!skrinName.equals(""))
				{
					queryHistory += " AND M.SKRIN_NAME = '"+skrinName+"' ";
				}


				queryHistory += " AND M.NAMA_TABLE = 'TBLPPKOBPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+id+"' AND ID_PERBICARAAN = '"+ID_PERBICARAAN+"'  "+
				" GROUP BY M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN,M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK) TH, TBLPPKRUJSAUDARA SAU " +
				" WHERE TH.ID_SAUDARA = SAU.ID_SAUDARA(+) ";
		return queryHistory;
	}

	public String queryHistoryPenghutang(String skrinName,String id,String ID_PERBICARAAN,String jenisAktiviti)
	{
		String queryHistory = "";

			queryHistory +=  " SELECT TH.* FROM ( "+
				" SELECT M.JENIS_AKTIVITI,M.ID_SEJARAHBIMAIN, M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK,  "+
				" NULL AS NAMA_PARENT, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PENGHUTANG' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_PENGHUTANG, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_SIMATI, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NAMA_PENGHUTANG' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NAMA_PENGHUTANG, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_KP_BARU' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NO_KP_BARU, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_KP_LAMA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NO_KP_LAMA, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'JENIS_KP' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS JENIS_KP, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_KP_LAIN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS NO_KP_LAIN, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'JENIS_PENGHUTANG' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS JENIS_PENGHUTANG, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'JUMLAH_HUTANG' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS JUMLAH_HUTANG "+
				" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S "+
				" WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN  ";

				if(!jenisAktiviti.equals(""))
				{
					if(jenisAktiviti.equals("INSERTDELETE"))
					{
						queryHistory += " AND M.JENIS_AKTIVITI IN ('INSERT','DELETE') ";
					}
					else
					{
						queryHistory += " AND M.JENIS_AKTIVITI = '"+jenisAktiviti+"' ";
					}
				}
				else
				{
					queryHistory += " AND M.JENIS_AKTIVITI = 'UPDATE' ";
				}

				if(!skrinName.equals(""))
				{
					queryHistory += " AND M.SKRIN_NAME = '"+skrinName+"' ";
				}


				queryHistory += " AND M.NAMA_TABLE = 'TBLPPKPENGHUTANG' AND ID_PERMOHONANSIMATI = '"+id+"' AND ID_PERBICARAAN = '"+ID_PERBICARAAN+"'  "+
				" GROUP BY M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN,M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK) TH ";
		return queryHistory;
	}

	public String queryHistoryHarta(String skrinName,String id,String ID_PERBICARAAN,String jenisAktiviti)
	{
		String queryHistory = "";

			queryHistory +=  " SELECT TH.*, N.NAMA_NEGERI, D.NAMA_DAERAH, M.NAMA_MUKIM, BH.KETERANGAN AS BANDARHTA, BP.KETERANGAN AS BANDARPEMAJU, JHM.KOD_JENIS_HAKMILIK, " +
					" NULL AS JENIS_PERINTAH  FROM ( "+
				" SELECT M.JENIS_AKTIVITI,M.ID_SEJARAHBIMAIN, M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK,  "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_HTAPERMOHONAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_HTAPERMOHONAN, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_HTA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_HTA, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_SIMATI, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_NEGERI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_NEGERI, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_DAERAH' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_DAERAH, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_MUKIM' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_MUKIM, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_JENISHM' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_JENISHM, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_HAKMILIK' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS NO_HAKMILIK, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_PT' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS NO_PT, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'BA_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS BA_SIMATI, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'BB_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS BB_SIMATI, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_PERJANJIAN' THEN  NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS NO_PERJANJIAN, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'TARIKH_PERJANJIAN' THEN NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS TARIKH_PERJANJIAN, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_ROH' THEN NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS NO_ROH, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_LOT_ID' THEN NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS NO_LOT_ID, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NAMA_RANCANGAN' THEN NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS NAMA_RANCANGAN, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'JENIS_KEPENTINGAN' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS JENIS_KEPENTINGAN, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'CATATAN' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS CATATAN, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'FLAG_KATEGORI_HTA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS FLAG_KATEGORI_HTA , "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ALAMAT_HTA1' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS ALAMAT_HTA1, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ALAMAT_HTA2' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS ALAMAT_HTA2, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ALAMAT_HTA3' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS ALAMAT_HTA3, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'POSKOD_HTA' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS POSKOD_HTA, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NAMA_PEMAJU' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS NAMA_PEMAJU, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ALAMAT_PEMAJU1' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS ALAMAT_PEMAJU1, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ALAMAT_PEMAJU2' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS ALAMAT_PEMAJU2, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ALAMAT_PEMAJU3' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS ALAMAT_PEMAJU3, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'POSKOD_PEMAJU' THEN   NVL(S.VALUE_SELEPAS,'-') END)) )).extract ('//text()'))  AS POSKOD_PEMAJU, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_BANDARPEMAJU' THEN   S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_BANDARPEMAJU, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_BANDARHTA' THEN   S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_BANDARHTA, " +

				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'JENIS_HTA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS JENIS_HTA " +
				" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S "+
				" WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN  ";

				if(!jenisAktiviti.equals(""))
				{
					if(jenisAktiviti.equals("INSERTDELETE"))
					{
						queryHistory += " AND M.JENIS_AKTIVITI IN ('INSERT','DELETE') ";
					}
					else
					{
						queryHistory += " AND M.JENIS_AKTIVITI = '"+jenisAktiviti+"' ";
					}
				}
				else
				{
					queryHistory += " AND M.JENIS_AKTIVITI = 'UPDATE' ";
				}

				if(!skrinName.equals(""))
				{
					//queryHistory += " AND M.SKRIN_NAME = '"+skrinName+"' ";
				}


				queryHistory += " AND M.NAMA_TABLE = 'TBLPPKHTAPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+id+"' AND ID_PERBICARAAN = '"+ID_PERBICARAAN+"'  "+
				" GROUP BY M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN,M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK) TH," +
				" TBLRUJNEGERI N, TBLRUJDAERAH D,TBLRUJMUKIM M, TBLRUJJENISHAKMILIK JHM,TBLRUJBANDAR BP,TBLRUJBANDAR BH  " +
				" WHERE TH.ID_NEGERI = N.ID_NEGERI(+) " +
				" AND TH.ID_BANDARHTA = BH.ID_BANDAR(+) "+
				" AND TH.ID_BANDARPEMAJU = BP.ID_BANDAR(+) "+
				" AND TH.ID_DAERAH = D.ID_DAERAH(+) AND TH.ID_MUKIM = M.ID_MUKIM(+) AND TH.ID_JENISHM = JHM.ID_JENISHAKMILIK(+) ";
				myLogger.info("queryHistory HARTA : "+queryHistory);

				return queryHistory;
	}


	public String queryHistoryHartaAlih(String skrinName,String id,String ID_PERBICARAAN,String jenisAktiviti)
	{
		String queryHistory = "";

			queryHistory +=  " SELECT TH.*, N.NAMA_NEGERI, D.NAMA_DAERAH, JHA.KETERANGAN AS JENIS_HA, NULL AS JENIS_PERINTAH  FROM ( "+
				" SELECT M.JENIS_AKTIVITI,M.ID_SEJARAHBIMAIN, M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK,  "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_HAPERMOHONAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_HAPERMOHONAN, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_HA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_HA, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_JENISHA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_JENISHA, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_SIMATI, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_NEGERI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_NEGERI, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_DAERAH' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_DAERAH, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_DAFTAR' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS NO_DAFTAR, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'BA_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS BA_SIMATI, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'BB_SIMATI' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS BB_SIMATI, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NAMA_SAHAM' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS NAMA_SAHAM, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_SIJIL' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS NO_SIJIL, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'BIL_UNIT' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS BIL_UNIT, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'CATATAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS CATATAN, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'JENAMA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS JENAMA, " +
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'BUTIRAN' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS BUTIRAN " +
				" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S "+
				" WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN  ";

				if(!jenisAktiviti.equals(""))
				{
					if(jenisAktiviti.equals("INSERTDELETE"))
					{
						queryHistory += " AND M.JENIS_AKTIVITI IN ('INSERT','DELETE') ";
					}
					else
					{
						queryHistory += " AND M.JENIS_AKTIVITI = '"+jenisAktiviti+"' ";
					}
				}
				else
				{
					queryHistory += " AND M.JENIS_AKTIVITI = 'UPDATE' ";
				}

				if(!skrinName.equals(""))
				{
					queryHistory += " AND M.SKRIN_NAME = '"+skrinName+"' ";
				}


				queryHistory += " AND M.NAMA_TABLE = 'TBLPPKHAPERMOHONAN' AND ID_PERMOHONANSIMATI = '"+id+"' AND ID_PERBICARAAN = '"+ID_PERBICARAAN+"'  "+
				" GROUP BY M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN,M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK) TH," +
				" TBLRUJNEGERI N, TBLRUJDAERAH D, TBLPPKRUJJENISHA JHA  " +
				" WHERE TH.ID_NEGERI = N.ID_NEGERI(+) AND TH.ID_DAERAH = D.ID_DAERAH(+) AND TH.ID_JENISHA = JHA.ID_JENISHA(+)   ";
				myLogger.info("queryHistory HARTA HA : "+queryHistory);

				return queryHistory;
	}

	public String queryHistoryPeguam(String skrinName,String id,String ID_PERBICARAAN,String jenisAktiviti)
	{
		String queryHistory = "";

			queryHistory +=  " SELECT TH.*  FROM ( "+
				" SELECT M.JENIS_AKTIVITI,M.ID_SEJARAHBIMAIN, M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK,  "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PEGUAM' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS ID_PEGUAM, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NAMA_FIRMA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS NAMA_FIRMA, "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'NO_RUJUKAN_FIRMA' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()'))  AS NO_RUJUKAN_FIRMA " +
				" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S "+
				" WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN  ";

				if(!jenisAktiviti.equals(""))
				{
					if(jenisAktiviti.equals("INSERTDELETE"))
					{
						queryHistory += " AND M.JENIS_AKTIVITI IN ('INSERT','DELETE') ";
					}
					else
					{
						queryHistory += " AND M.JENIS_AKTIVITI = '"+jenisAktiviti+"' ";
					}
				}
				else
				{
					queryHistory += " AND M.JENIS_AKTIVITI = 'UPDATE' ";
				}

				if(!skrinName.equals(""))
				{
					queryHistory += " AND M.SKRIN_NAME = '"+skrinName+"' ";
				}


				queryHistory += " AND M.NAMA_TABLE = 'TBLPPKPEGUAM' AND ID_PERMOHONANSIMATI = '"+id+"' AND ID_PERBICARAAN = '"+ID_PERBICARAAN+"'  "+
				" GROUP BY M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN,M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK) TH ";
				myLogger.info("queryHistory PEGUAM : "+queryHistory);

				return queryHistory;
	}


	public String queryGetList(HttpSession session,String ID_PEMOHON,String ID_SIMATI,String ID_PERBICARAAN,String skrinName,
			String current_previous, String id,String ID_PERMOHONAN,String flagDefaultKeterangan)
	{
		String namaList = "list"+skrinName+current_previous;
		myLogger.info("namaList : "+namaList);
		String sql = "";

		if(skrinName.equals("waris"))
		{
			//id = permohonansimati
			String sql_open_column = " (SELECT HIS.JENIS_AKTIVITI,NVL(HIS.ID_SEJARAHBIMAIN,'') AS ID_SEJARAHBIMAIN,HIS.NAMA_TABLE,HIS.NAMA_FIELD_PK,TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
					" TO_CHAR(OBMAIN.ID_OB) AS ID_OB,TO_CHAR(OBMAIN.ID_OBPERMOHONAN) AS ID_OBPERMOHONAN, " +
			" (CASE WHEN HIS.NAMA_OB IS NOT NULL THEN HIS.NAMA_OB ELSE OBMAIN.NAMA_OB END) AS NAMA_OB, " +
			" (CASE WHEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) "+
			" WHEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) "+
			" WHEN (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) IS NOT NULL THEN  "+
			" CASE " +
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '5' THEN 'TENTERA ' " +
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '6' THEN 'POLIS '  "+
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '4' THEN 'PASSPORT '  " +
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '7' THEN 'LAIN-LAIN ' " +
			" ELSE '' END "+
			" || (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) ELSE '' END) AS PENGENALAN, "+
			" (CASE WHEN HIS.SAUDARA IS NOT NULL THEN HIS.SAUDARA ELSE OBMAIN.SAUDARA END) AS SAUDARA, "+
			" CASE " +
			" WHEN (CASE WHEN HIS.STATUS_HIDUP IS NOT NULL THEN HIS.STATUS_HIDUP ELSE TO_CHAR(OBMAIN.STATUS_HIDUP) END) = '1' THEN 'MENINGGAL DUNIA' " +
			" ELSE 'MASIH HIDUP' END AS STATUS, " +
			" (CASE WHEN HIS.UMUR IS NOT NULL THEN HIS.UMUR ELSE TO_CHAR(OBMAIN.UMUR) END) AS UMUR, " +
			" (CASE WHEN HIS.ID_PARENTOB IS NOT NULL THEN (CASE WHEN HIS.ID_PARENTOB != '-' THEN  HIS.ID_PARENTOB ELSE '' END) ELSE TO_CHAR(OBMAIN.ID_PARENTOB) END) AS ID_PARENTOB, " +
			" (CASE WHEN HIS.LAPIS IS NOT NULL THEN HIS.LAPIS ELSE TO_CHAR(OBMAIN.LAPIS) END) AS LAPIS, " +
			" CASE WHEN (CASE WHEN HIS.ID_PEMOHON IS NOT NULL THEN HIS.ID_PEMOHON ELSE TO_CHAR(OBMAIN.ID_PEMOHON) END) IS NOT NULL AND (CASE WHEN HIS.ID_PEMOHON IS NOT NULL THEN HIS.ID_PEMOHON ELSE TO_CHAR(OBMAIN.ID_PEMOHON) END) != '-' AND (CASE WHEN HIS.ID_PEMOHON IS NOT NULL THEN HIS.ID_PEMOHON ELSE TO_CHAR(OBMAIN.ID_PEMOHON) END) = '"+ID_PEMOHON+"' THEN 'YA' ELSE '' END AS FLAG_PEMOHON ";

			String sql_close_column_L1  = " OBMAIN, ("+queryHistoryOB(skrinName,id,ID_PERBICARAAN,"UPDATE")+") HIS ";
		   	sql_close_column_L1 += " WHERE OBMAIN.ID_OBPERMOHONAN = HIS.ID_OBPERMOHONAN(+)) MAIN WHERE  MAIN.LAPIS = 1  ";
		   	myLogger.info(" ::::::::: sql_close_column_L1 :::::::::::::::: "+sql_close_column_L1);

		   	String sql_close_column_L2  = " OBMAIN, ("+queryHistoryOB(skrinName,id,ID_PERBICARAAN,"UPDATE")+") HIS ";
		   	sql_close_column_L2 += " WHERE OBMAIN.ID_OBPERMOHONAN = HIS.ID_OBPERMOHONAN(+) ) MAIN,   ";
		   	sql_close_column_L2 +=	"("+sqllistParentSimati(session,id,ID_PERBICARAAN,skrinName,"")+") PAR ";
		   	sql_close_column_L2 += " WHERE MAIN.ID_PARENTOB = PAR.ID_OB ";
		   	String sql_avoid_delete = " WHERE ID_OBPERMOHONAN NOT IN (SELECT NVL(ID_OBPERMOHONAN,'') ";
		   	sql_avoid_delete += " FROM ("+queryHistoryOB(skrinName,id,ID_PERBICARAAN,"INSERTDELETE")+"))";


		   	String sql_asal_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK,OB.ID_PERMOHONANSIMATI," +
			" OB.ID_OB,OB.ID_OBPERMOHONAN,OB.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA,  OB.JENIS_KP, OB.NO_KP_LAIN, " +
			" OB.UMUR, OB.STATUS_HIDUP,  OB.ID_SAUDARA,  OB.LAPIS,  NULL AS ID_PARENTOB,NULL AS ID_HUBUNGANOB,SAU.KETERANGAN AS SAUDARA, OB.ID_PEMOHON "+
			" FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1, TBLPPKRUJSAUDARA SAU "+
			" WHERE OB.ID_SAUDARA = SAU.ID_SAUDARA(+) AND OB.ID_OB = OB1.ID_OB "+
			" AND OB.ID_PERMOHONANSIMATI = OB1.ID_PERMOHONANSIMATI AND OB.ID_PERMOHONANSIMATI = '"+id+"'  "+
			" AND OB.ID_TARAFKPTG = 1 "+
			" AND OB.LAPIS = 1   ";

		   	String sql_asal_L2 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK,OB.ID_PERMOHONANSIMATI," +
			" OB.ID_OB,OB.ID_OBPERMOHONAN,OB.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA,  OB.JENIS_KP, OB.NO_KP_LAIN,  "+
			" OB.UMUR, OB.STATUS_HIDUP, OB.ID_SAUDARA,  OB.LAPIS,  HUP.ID_PARENTOB, HUP.ID_HUBUNGANOB,SAU.KETERANGAN AS SAUDARA, OB.ID_PEMOHON   "+
			" FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1, TBLPPKRUJSAUDARA SAU, TBLPPKHUBUNGANOBPERMOHONAN HUP  " +
			" WHERE OB.ID_SAUDARA = SAU.ID_SAUDARA(+) AND OB.ID_OB = OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = OB1.ID_PERMOHONANSIMATI " +
			" AND OB.ID_TARAFKPTG = 1   " +
			" AND OB.ID_OB = HUP.ID_OB " +
			" AND OB.ID_PERMOHONANSIMATI = '"+id+"'  " +
			" AND OB.ID_PERMOHONANSIMATI = HUP.ID_PERMOHONANSIMATI ";

		   	String sql_dulu_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK,MS.ID_PERMOHONANSIMATI," +
		   		" OB.ID_OB,OB.ID_OBPERMOHONAN,OB.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA,  OB.JENIS_KP, OB.NO_KP_LAIN,  "+
			" OB.UMUR,  OB.STATUS_HIDUP, OB.ID_SAUDARA,  OB.LAPIS,  NULL AS ID_PARENTOB,NULL AS ID_HUBUNGANOB, SAU.KETERANGAN AS SAUDARA, OB.ID_PEMOHON "+
			" FROM TBLPPKOBPERMOHONAN OB, TBLPPKOB OB1, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONANSIMATI MS1, " +
			" TBLPPKSIMATI M,TBLPPKRUJSAUDARA SAU, TBLPPKPERMOHONAN P, TBLPPKPERMOHONAN P1  "+
			" WHERE " +
			" OB.ID_OB = OB1.ID_OB AND OB.ID_SAUDARA = SAU.ID_SAUDARA(+)  "+
			" AND OB1.ID_SIMATI = M.ID_SIMATI " +
			" AND OB1.ID_SIMATI = MS.ID_SIMATI AND OB1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN " +
			" AND OB1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI   "+
			" AND OB.ID_PERMOHONANSIMATI = '"+id+"' " +
			" AND MS.ID_PERMOHONANSIMATI = '"+id+"' AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN  AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "+
			" AND OB.LAPIS = 1   "+
			" AND OB.ID_TARAFKPTG = 1    ";


		   	String sql_dulu_L2 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK,MS.ID_PERMOHONANSIMATI," +
		   		" OB.ID_OB,OB.ID_OBPERMOHONAN,OB.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.JENIS_KP, OB.NO_KP_LAIN, "+
			" OB.UMUR,  OB.STATUS_HIDUP,  OB.ID_SAUDARA, OB.LAPIS, HUP.ID_PARENTOB, HUP.ID_HUBUNGANOB, SAU.KETERANGAN AS SAUDARA, OB.ID_PEMOHON   "+
			" FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONANSIMATI MS1, TBLPPKPERMOHONAN P, TBLPPKPERMOHONAN P1, " +
			" TBLPPKHUBUNGANOB HU,TBLPPKHUBUNGANOBPERMOHONAN HUP,  TBLPPKSIMATI M,TBLPPKRUJSAUDARA SAU  "+
			" WHERE HU.ID_OB = OB1.ID_OB AND OB.ID_OB = OB1.ID_OB " +
			" AND HU.ID_HUBUNGANOB = HUP.ID_HUBUNGANOB  AND OB.ID_SAUDARA = SAU.ID_SAUDARA(+)  "+
			" AND OB1.ID_SIMATI = M.ID_SIMATI AND MS.ID_PERMOHONANSIMATI = HUP.ID_PERMOHONANSIMATI  " +
			" AND OB1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI  AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN  AND P1.NO_SUBJAKET < P.NO_SUBJAKET "+
			" AND OB1.ID_SIMATI = MS.ID_SIMATI  AND OB1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN   "+
			" AND OB.ID_PERMOHONANSIMATI = '"+id+"' " +
			" AND MS.ID_PERMOHONANSIMATI = '"+id+"'   "+
			" AND OB.ID_TARAFKPTG = 1 ";



		   	String sql_history = " (SELECT  OBMAIN.JENIS_AKTIVITI,OBMAIN.ID_SEJARAHBIMAIN,OBMAIN.NAMA_TABLE,OBMAIN.NAMA_FIELD_PK,TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
			" TO_CHAR(OBMAIN.ID_OB) AS ID_OB, TO_CHAR(OBMAIN.ID_OBPERMOHONAN) AS ID_OBPERMOHONAN, " +
			" OBMAIN.NAMA_OB, "+
			" (CASE WHEN OBMAIN.NO_KP_BARU IS NOT NULL THEN OBMAIN.NO_KP_BARU "+
			" WHEN OBMAIN.NO_KP_LAMA IS NOT NULL THEN OBMAIN.NO_KP_LAMA "+
			" WHEN OBMAIN.NO_KP_LAIN IS NOT NULL THEN  "+
			" CASE WHEN OBMAIN.JENIS_KP = '5' THEN 'TENTERA ' WHEN OBMAIN.JENIS_KP = '6' THEN 'POLIS '  "+
			" WHEN OBMAIN.JENIS_KP = '4' THEN 'PASSPORT '  WHEN OBMAIN.JENIS_KP = '7' THEN 'LAIN-LAIN ' ELSE '' END "+
			" || OBMAIN.NO_KP_LAIN ELSE '' END) AS PENGENALAN, "+
			" OBMAIN.SAUDARA,  "+
			" CASE WHEN OBMAIN.STATUS_HIDUP = '1' THEN 'MENINGGAL DUNIA' ELSE 'MASIH HIDUP' END AS STATUS, OBMAIN.UMUR,OBMAIN.ID_PARENTOB, OBMAIN.LAPIS, " +
			" CASE WHEN OBMAIN.ID_PEMOHON IS NOT NULL AND OBMAIN.ID_PEMOHON != '-' AND OBMAIN.ID_PEMOHON = '"+ID_PEMOHON+"'  THEN 'YA' ELSE '' END AS FLAG_PEMOHON " +
			" FROM (";
		   	sql_history += queryHistoryOB(skrinName,id,ID_PERBICARAAN,"INSERTDELETE");
			sql_history += " ) OBMAIN WHERE  OBMAIN.ID_TARAFKPTG = 1 ) MAIN, ";
			sql_history +=	"("+sqllistParentSimati(session,id,ID_PERBICARAAN,skrinName,"")+") PAR ";
			sql_history += " WHERE TO_CHAR(MAIN.ID_PARENTOB) = PAR.ID_OB(+) ";

			if(namaList.equals("listwariscurrent"))
			{
				sql += " SELECT * FROM (";
				sql += " SELECT * FROM (";
				sql += " SELECT MAIN.*,NULL AS NAMA_PARENT FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_asal_L1 + " UNION ALL " + sql_asal_L2;
				sql += ") "+sql_close_column_L1;
				sql += " UNION ALL ";
				sql += " SELECT MAIN.*,PAR.NAMA_OB AS NAMA_PARENT FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_asal_L1 + " UNION ALL " + sql_asal_L2;
				sql += ") "+sql_close_column_L2;
				sql += " ) ";
				sql += sql_avoid_delete;
				sql += " UNION ALL ";
				sql += " SELECT MAIN.*,PAR.NAMA_OB AS NAMA_PARENT FROM " + sql_history;
				sql += " ) ORDER BY LAPIS,UMUR ";
			}
			else if(namaList.equals("listwarisprevious"))
			{
				sql += " SELECT * FROM (";
				sql += " SELECT MAIN.*,NULL AS NAMA_PARENT FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_dulu_L1 + " UNION ALL " + sql_dulu_L2;
				sql += ") "+sql_close_column_L1;
				sql += " UNION ALL ";
				sql += " SELECT MAIN.*,PAR.NAMA_OB AS NAMA_PARENT FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_dulu_L1 + " UNION ALL " + sql_dulu_L2;
				sql += ") "+sql_close_column_L2;
				sql += " ) ";
				sql += sql_avoid_delete;
				sql += " ORDER BY LAPIS,UMUR ";
			}
		}
		else if(skrinName.equals("ob") || skrinName.equals("saksi") || skrinName.equals("pemiutang"))
		{
			//id = permohonansimati
			String sql_open_column = " (SELECT HIS.JENIS_AKTIVITI,NVL(HIS.ID_SEJARAHBIMAIN,'') AS ID_SEJARAHBIMAIN,HIS.NAMA_TABLE,HIS.NAMA_FIELD_PK,TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
					" TO_CHAR(OBMAIN.ID_OB) AS ID_OB,TO_CHAR(OBMAIN.ID_OBPERMOHONAN) AS ID_OBPERMOHONAN, " +
			" (CASE WHEN HIS.NAMA_OB IS NOT NULL THEN HIS.NAMA_OB ELSE OBMAIN.NAMA_OB END) AS NAMA_OB, " +
			" (CASE WHEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) "+
			" WHEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) "+
			" WHEN (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) IS NOT NULL THEN  "+
			" CASE " +
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '5' THEN 'TENTERA ' " +
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '6' THEN 'POLIS '  "+
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '4' THEN 'PASSPORT '  " +
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '7' THEN 'LAIN-LAIN ' " +
			" ELSE '' END "+
			" || (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) ELSE '' END) AS PENGENALAN, "+
			" CASE WHEN (CASE WHEN HIS.STATUS_OB IS NOT NULL THEN HIS.STATUS_OB ELSE TO_CHAR(OBMAIN.STATUS_OB) END) = '1' THEN 'DEWASA / WARAS' " +
			" WHEN (CASE WHEN HIS.STATUS_OB IS NOT NULL THEN HIS.STATUS_OB ELSE TO_CHAR(OBMAIN.STATUS_OB) END)  = '2' THEN 'BELUM DEWASA' " +
			" WHEN (CASE WHEN HIS.STATUS_OB IS NOT NULL THEN HIS.STATUS_OB ELSE TO_CHAR(OBMAIN.STATUS_OB) END)  = '3' THEN 'HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN' " +
			" WHEN (CASE WHEN HIS.STATUS_OB IS NOT NULL THEN HIS.STATUS_OB ELSE TO_CHAR(OBMAIN.STATUS_OB) END)  = '4' THEN 'TIDAK SEMPURNA AKAL' " +
			" ELSE '' END AS STATUS_OB_KETERANGAN, " +
			" (CASE WHEN HIS.STATUS_OB IS NOT NULL THEN HIS.STATUS_OB ELSE TO_CHAR(OBMAIN.STATUS_OB) END) AS STATUS_OB," +
			" TRIM(TO_CHAR((CASE WHEN HIS.NILAI_HUTANG IS NOT NULL THEN HIS.NILAI_HUTANG ELSE TO_CHAR(OBMAIN.NILAI_HUTANG) END),'999,999,990.99')) AS NILAI_HUTANG_KETERANGAN, " +
			" (CASE WHEN HIS.NILAI_HUTANG IS NOT NULL THEN HIS.NILAI_HUTANG ELSE TO_CHAR(OBMAIN.NILAI_HUTANG) END) AS NILAI_HUTANG, "+
			" CASE WHEN (CASE WHEN HIS.ID_PEMOHON IS NOT NULL THEN HIS.ID_PEMOHON ELSE TO_CHAR(OBMAIN.ID_PEMOHON) END) IS NOT NULL AND (CASE WHEN HIS.ID_PEMOHON IS NOT NULL THEN HIS.ID_PEMOHON ELSE TO_CHAR(OBMAIN.ID_PEMOHON) END) != '-' AND (CASE WHEN HIS.ID_PEMOHON IS NOT NULL THEN HIS.ID_PEMOHON ELSE TO_CHAR(OBMAIN.ID_PEMOHON) END) = '"+ID_PEMOHON+"' THEN 'YA' ELSE '' END AS FLAG_PEMOHON ";


			String sql_close_column_L1  = " OBMAIN, ("+queryHistoryOB(skrinName,id,ID_PERBICARAAN,"UPDATE")+") HIS ";
		   	sql_close_column_L1 += " WHERE OBMAIN.ID_OBPERMOHONAN = HIS.ID_OBPERMOHONAN(+)) MAIN ";

		   	String sql_avoid_delete = " WHERE ID_OBPERMOHONAN NOT IN (SELECT NVL(ID_OBPERMOHONAN,'') ";
		   	sql_avoid_delete += " FROM ("+queryHistoryOB(skrinName,id,ID_PERBICARAAN,"INSERTDELETE")+"))";


		   	String sql_asal_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK,OB.ID_PERMOHONANSIMATI," +
			" OB.ID_OB,OB.ID_OBPERMOHONAN,OB.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA,  OB.JENIS_KP, OB.NO_KP_LAIN, " +
			" OB.STATUS_OB, " +
			" OB.NILAI_HUTANG, OB.ID_PEMOHON  "+
			" FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1" +
			" WHERE " +
			" OB.ID_OB = OB1.ID_OB "+
			" AND OB.ID_PERMOHONANSIMATI = OB1.ID_PERMOHONANSIMATI AND OB.ID_PERMOHONANSIMATI = '"+id+"'  ";
		   	if(skrinName.equals("ob"))
		   	{
		   		sql_asal_L1 += " AND  OB.ID_TARAFKPTG NOT IN (1,2,14)  ";
		   	}
		   	else if(skrinName.equals("saksi"))
		   	{
		   		sql_asal_L1 += " AND  OB.ID_TARAFKPTG = 14  ";
		   	}
		   	else if(skrinName.equals("pemiutang"))
		   	{
		   		sql_asal_L1 += " AND  OB.ID_TARAFKPTG = 2  ";
		   	}

		   	String sql_dulu_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK,MS.ID_PERMOHONANSIMATI," +
	   		" OB.ID_OB,OB.ID_OBPERMOHONAN,OB.ID_SIMATI,OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA,  OB.JENIS_KP, OB.NO_KP_LAIN," +
	   		" OB.STATUS_OB, " +
			" OB.NILAI_HUTANG, OB.ID_PEMOHON "+
		   	" FROM TBLPPKOBPERMOHONAN OB, TBLPPKOB OB1, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONANSIMATI MS1," +
		   	" TBLPPKPERMOHONAN P, TBLPPKPERMOHONAN P1, TBLPPKSIMATI M" +
			" WHERE " +
			" OB.ID_OB = OB1.ID_OB " +
			" AND OB1.ID_SIMATI = M.ID_SIMATI AND OB1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN " +
			" AND OB1.ID_SIMATI = MS.ID_SIMATI  AND OB1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN   "+
			" AND OB.ID_PERMOHONANSIMATI = '"+id+"' AND P1.NO_SUBJAKET < P.NO_SUBJAKET " +
			" AND MS.ID_PERMOHONANSIMATI = '"+id+"' ";

		   	if(skrinName.equals("ob"))
		   	{
		   		sql_dulu_L1 += " AND  OB.ID_TARAFKPTG NOT IN (1,2,14)  ";
		   	}
		   	else if(skrinName.equals("saksi"))
		   	{
		   		sql_dulu_L1 += " AND  OB.ID_TARAFKPTG = 14  ";
		   	}
		   	else if(skrinName.equals("pemiutang"))
		   	{
		   		sql_dulu_L1 += " AND  OB.ID_TARAFKPTG = 2  ";
		   	}

		   	String sql_history = " (SELECT  OBMAIN.JENIS_AKTIVITI,OBMAIN.ID_SEJARAHBIMAIN,OBMAIN.NAMA_TABLE,OBMAIN.NAMA_FIELD_PK," +
		   			" TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
			" TO_CHAR(OBMAIN.ID_OB) AS ID_OB, TO_CHAR(OBMAIN.ID_OBPERMOHONAN) AS ID_OBPERMOHONAN, " +
			" OBMAIN.NAMA_OB, "+
			" (CASE WHEN OBMAIN.NO_KP_BARU IS NOT NULL THEN OBMAIN.NO_KP_BARU "+
			" WHEN OBMAIN.NO_KP_LAMA IS NOT NULL THEN OBMAIN.NO_KP_LAMA "+
			" WHEN OBMAIN.NO_KP_LAIN IS NOT NULL THEN  "+
			" CASE WHEN OBMAIN.JENIS_KP = '5' THEN 'TENTERA ' WHEN OBMAIN.JENIS_KP = '6' THEN 'POLIS '  "+
			" WHEN OBMAIN.JENIS_KP = '4' THEN 'PASSPORT '  WHEN OBMAIN.JENIS_KP = '7' THEN 'LAIN-LAIN ' ELSE '' END "+
			" || OBMAIN.NO_KP_LAIN ELSE '' END) AS PENGENALAN, " +
			" CASE WHEN OBMAIN.STATUS_OB = '1' THEN 'DEWASA / WARAS' " +
			" WHEN OBMAIN.STATUS_OB = '2' THEN 'BELUM DEWASA' " +
			" WHEN OBMAIN.STATUS_OB = '3' THEN 'HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN' " +
			" WHEN OBMAIN.STATUS_OB = '4' THEN 'TIDAK SEMPURNA AKAL' " +
			" ELSE '' END AS STATUS_OB_KETERANGAN, OBMAIN.STATUS_OB, " +
			" TRIM(TO_CHAR(OBMAIN.NILAI_HUTANG,'999,999,990.99')) AS NILAI_HUTANG_KETERANGAN, OBMAIN.NILAI_HUTANG, "+
			" CASE WHEN OBMAIN.ID_PEMOHON IS NOT NULL AND OBMAIN.ID_PEMOHON != '-' AND OBMAIN.ID_PEMOHON = '"+ID_PEMOHON+"' THEN 'YA' ELSE '' END AS FLAG_PEMOHON " +
			" FROM (";
		   	sql_history += queryHistoryOB(skrinName,id,ID_PERBICARAAN,"INSERTDELETE");
			sql_history += " ) OBMAIN  WHERE  OBMAIN.ID_TARAFKPTG IS NOT NULL ";

			if(skrinName.equals("ob"))
		   	{
				sql_history += " AND  OBMAIN.ID_TARAFKPTG NOT IN ('1','2','14')  ";
		   	}
		   	else if(skrinName.equals("saksi"))
		   	{
		   		sql_history += " AND  OBMAIN.ID_TARAFKPTG = '14'  ";
		   	}
		   	else if(skrinName.equals("pemiutang"))
		   	{
		   		sql_history += " AND  OBMAIN.ID_TARAFKPTG = '2'  ";
		   	}

			sql_history += ") MAIN";

			if(namaList.equals("list"+skrinName+"current"))
			{
				sql += " SELECT * FROM (";
				sql += " SELECT * FROM (";
				sql += " SELECT MAIN.* FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_asal_L1;
				sql += ") "+sql_close_column_L1;
				sql += " ) ";
				sql += sql_avoid_delete;
				sql += " UNION ALL ";
				sql += " SELECT MAIN.* FROM " + sql_history;
				sql += " ) ORDER BY NAMA_OB ";
			}
			else if(namaList.equals("list"+skrinName+"previous"))
			{
				sql += " SELECT * FROM (";
				sql += " SELECT MAIN.* FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_dulu_L1;
				sql += ") "+sql_close_column_L1;
				sql += " ) ";
				sql += sql_avoid_delete;
				sql += " ORDER BY NAMA_OB ";
			}
		}
		else if(skrinName.equals("penghutang"))
		{
			//id = permohonansimati
			String sql_open_column = " (SELECT HIS.JENIS_AKTIVITI,NVL(HIS.ID_SEJARAHBIMAIN,'') AS ID_SEJARAHBIMAIN,HIS.NAMA_TABLE,HIS.NAMA_FIELD_PK,TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
			" TO_CHAR(OBMAIN.ID_PENGHUTANG) AS ID_PENGHUTANG, " +
			" (CASE WHEN HIS.NAMA_PENGHUTANG IS NOT NULL THEN HIS.NAMA_PENGHUTANG ELSE OBMAIN.NAMA_PENGHUTANG END) AS NAMA_PENGHUTANG, " +
			" (CASE WHEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_BARU IS NOT NULL THEN HIS.NO_KP_BARU ELSE OBMAIN.NO_KP_BARU END) "+
			" WHEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) IS NOT NULL THEN (CASE WHEN HIS.NO_KP_LAMA IS NOT NULL THEN HIS.NO_KP_LAMA ELSE OBMAIN.NO_KP_LAMA END) "+
			" WHEN (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) IS NOT NULL THEN  "+
			" CASE " +
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '5' THEN 'TENTERA ' " +
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '6' THEN 'POLIS '  "+
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '4' THEN 'PASSPORT '  " +
			" WHEN (CASE WHEN HIS.JENIS_KP IS NOT NULL THEN HIS.JENIS_KP ELSE TO_CHAR(OBMAIN.JENIS_KP) END) = '7' THEN 'LAIN-LAIN ' " +
			" ELSE '' END "+
			" || (CASE WHEN HIS.NO_KP_LAIN IS NOT NULL THEN HIS.NO_KP_LAIN ELSE OBMAIN.NO_KP_LAIN END) ELSE '' END) AS PENGENALAN, "+
			" TRIM(TO_CHAR((CASE WHEN HIS.JUMLAH_HUTANG IS NOT NULL THEN HIS.JUMLAH_HUTANG ELSE TO_CHAR(OBMAIN.JUMLAH_HUTANG) END),'999,999,990.99')) AS JUMLAH_HUTANG_KETERANGAN, " +
			" (CASE WHEN HIS.JUMLAH_HUTANG IS NOT NULL THEN HIS.JUMLAH_HUTANG ELSE TO_CHAR(OBMAIN.JUMLAH_HUTANG) END) AS JUMLAH_HUTANG, "+
			" (CASE WHEN (CASE WHEN HIS.JENIS_PENGHUTANG IS NOT NULL THEN HIS.JENIS_PENGHUTANG ELSE OBMAIN.JENIS_PENGHUTANG END) = '1' THEN 'AGENSI'" +
			" WHEN (CASE WHEN HIS.JENIS_PENGHUTANG IS NOT NULL THEN HIS.JENIS_PENGHUTANG ELSE OBMAIN.JENIS_PENGHUTANG END) = '2' THEN 'INDIVIDU'" +
			" ELSE '' END) " +
			" AS JENIS_PENGHUTANG_KETERANGAN, " +
			" (CASE WHEN HIS.JENIS_PENGHUTANG IS NOT NULL THEN HIS.JENIS_PENGHUTANG ELSE TO_CHAR(OBMAIN.JENIS_PENGHUTANG) END) AS JENIS_PENGHUTANG ";

			String sql_close_column_L1  = " OBMAIN, ("+queryHistoryPenghutang(skrinName,id,ID_PERBICARAAN,"UPDATE")+") HIS ";
		   	sql_close_column_L1 += " WHERE OBMAIN.ID_PENGHUTANG = HIS.ID_PENGHUTANG(+)) MAIN ";

		   	String sql_avoid_delete = " WHERE ID_PENGHUTANG NOT IN (SELECT NVL(ID_PENGHUTANG,'') ";
		   	sql_avoid_delete += " FROM ("+queryHistoryPenghutang(skrinName,id,ID_PERBICARAAN,"INSERTDELETE")+"))";


		   	String sql_asal_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK,OB.ID_PERMOHONANSIMATI," +
			" OB.ID_PENGHUTANG," +
			" OB.ID_SIMATI," +
			" OB.NAMA_PENGHUTANG, " +
			" OB.NO_KP_BARU, OB.NO_KP_LAMA,  OB.JENIS_KP, OB.NO_KP_LAIN, " +
			" OB.JUMLAH_HUTANG, OB.JENIS_PENGHUTANG "+
			" FROM TBLPPKPENGHUTANG OB " +
			" WHERE " +
			" OB.ID_PERMOHONANSIMATI = '"+id+"'  ";


		   	String sql_dulu_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK,MS.ID_PERMOHONANSIMATI," +
		   	" OB.ID_PENGHUTANG," +
		   	" OB.ID_SIMATI," +
		   	" OB.NAMA_PENGHUTANG, " +
		   	" OB.NO_KP_BARU, OB.NO_KP_LAMA,  OB.JENIS_KP, OB.NO_KP_LAIN," +
		   	" OB.JUMLAH_HUTANG, OB.JENIS_PENGHUTANG "
		   	+ "FROM TBLPPKPENGHUTANG OB, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS, "
			+ "TBLPPKPERMOHONAN P ,TBLPPKPERMOHONAN P1, TBLPPKPERMOHONANSIMATI MS1  "
			+ "WHERE OB.ID_SIMATI = M.ID_SIMATI   "
			+ "AND OB.ID_SIMATI = MS.ID_SIMATI  "
			+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN  "
			+ "AND MS.ID_PERMOHONANSIMATI <> OB.ID_PERMOHONANSIMATI  "
			+ "AND MS.ID_PERMOHONANSIMATI = '"+ id + "' "
			+ "AND OB.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI  "
			+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN "
			+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN  "
			+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET    ";


		   	String sql_history = " (SELECT  OBMAIN.JENIS_AKTIVITI,OBMAIN.ID_SEJARAHBIMAIN,OBMAIN.NAMA_TABLE,OBMAIN.NAMA_FIELD_PK," +
		   			" TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
			" TO_CHAR(OBMAIN.ID_PENGHUTANG) AS ID_PENGHUTANG, " +
			" OBMAIN.NAMA_PENGHUTANG, "+
			" (CASE WHEN OBMAIN.NO_KP_BARU IS NOT NULL THEN OBMAIN.NO_KP_BARU "+
			" WHEN OBMAIN.NO_KP_LAMA IS NOT NULL THEN OBMAIN.NO_KP_LAMA "+
			" WHEN OBMAIN.NO_KP_LAIN IS NOT NULL THEN  "+
			" CASE WHEN OBMAIN.JENIS_KP = '5' THEN 'TENTERA ' WHEN OBMAIN.JENIS_KP = '6' THEN 'POLIS '  "+
			" WHEN OBMAIN.JENIS_KP = '4' THEN 'PASSPORT '  WHEN OBMAIN.JENIS_KP = '7' THEN 'LAIN-LAIN ' ELSE '' END "+
			" || OBMAIN.NO_KP_LAIN ELSE '' END) AS PENGENALAN, " +
			" TRIM(TO_CHAR(OBMAIN.JUMLAH_HUTANG,'999,999,990.99')) AS JUMLAH_HUTANG_KETERANGAN, OBMAIN.JUMLAH_HUTANG, "+
			" CASE WHEN OBMAIN.JENIS_PENGHUTANG = '1' THEN 'AGENSI' " +
			" WHEN OBMAIN.JENIS_PENGHUTANG = '2' THEN 'INDIVIDU' " +
			" ELSE '' END AS JENIS_PENGHUTANG_KETERANGAN, OBMAIN.JENIS_PENGHUTANG " +
			" FROM (";
		   	sql_history += queryHistoryPenghutang(skrinName,id,ID_PERBICARAAN,"INSERTDELETE");
			sql_history += " ) OBMAIN  WHERE  OBMAIN.ID_PENGHUTANG IS NOT NULL ";
			sql_history += ") MAIN";

			if(namaList.equals("list"+skrinName+"current"))
			{
				sql += " SELECT * FROM (";
				sql += " SELECT * FROM (";
				sql += " SELECT MAIN.* FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_asal_L1;
				sql += ") "+sql_close_column_L1;
				sql += " ) ";
				sql += sql_avoid_delete;
				sql += " UNION ALL ";
				sql += " SELECT MAIN.* FROM " + sql_history;
				sql += " ) ORDER BY NAMA_PENGHUTANG ";
			}
			else if(namaList.equals("list"+skrinName+"previous"))
			{
				sql += " SELECT * FROM (";
				sql += " SELECT MAIN.* FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_dulu_L1;
				sql += ") "+sql_close_column_L1;
				sql += " ) ";
				sql += sql_avoid_delete;
				sql += " ORDER BY NAMA_PENGHUTANG ";
			}
		}
		else if(skrinName.equals("peguam"))
		{
			String sql_open_column = " (SELECT HIS.JENIS_AKTIVITI,NVL(HIS.ID_SEJARAHBIMAIN,'') AS ID_SEJARAHBIMAIN,HIS.NAMA_TABLE,HIS.NAMA_FIELD_PK," +
					" TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
					" TO_CHAR(OBMAIN.ID_PEGUAM) AS ID_PEGUAM, " +
			"(CASE WHEN HIS.NAMA_FIRMA IS NOT NULL THEN HIS.NAMA_FIRMA ELSE OBMAIN.NAMA_FIRMA END)  AS NAMA_FIRMA,"+
			"(CASE WHEN HIS.NO_RUJUKAN_FIRMA IS NOT NULL THEN HIS.NO_RUJUKAN_FIRMA ELSE OBMAIN.NO_RUJUKAN_FIRMA END)  AS NO_RUJUKAN_FIRMA ";

			String sql_close_column_L1  = " OBMAIN, ("+queryHistoryPeguam(skrinName,id,ID_PERBICARAAN,"UPDATE")+") HIS ";
		   	sql_close_column_L1 += " WHERE OBMAIN.ID_PEGUAM = HIS.ID_PEGUAM(+)) MAIN ";

		   	String sql_avoid_delete = " WHERE ID_PEGUAM NOT IN (SELECT NVL(ID_PEGUAM,'') ";
		   	sql_avoid_delete += " FROM ("+queryHistoryPeguam(skrinName,id,ID_PERBICARAAN,"INSERTDELETE")+"))";

		   	String sql_asal_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK," +
		   	" PSM.ID_PERMOHONANSIMATI, PG.ID_PEGUAM, PG.NAMA_FIRMA, PG.NO_RUJUKAN_FIRMA FROM TBLPPKPEGUAM PG, TBLPPKPEMOHON PM , TBLPPKPEGUAMPEMOHON PP, " +
		   	" TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI PSM " +
		   	" WHERE PP.ID_PEMOHON = PM.ID_PEMOHON AND PG.ID_PEGUAM = PP.ID_PEGUAM  " +
		   	" AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN " +
		   	" AND PSM.ID_PERMOHONANSIMATI= '"+id+"' " +
		   	" AND PG.NAMA_FIRMA IS NOT NULL  ";

		   	String sql_history = " (SELECT  OBMAIN.JENIS_AKTIVITI,OBMAIN.ID_SEJARAHBIMAIN,OBMAIN.NAMA_TABLE,OBMAIN.NAMA_FIELD_PK," +
		   	" TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
			" TO_CHAR(OBMAIN.ID_PEGUAM) AS ID_PEGUAM, OBMAIN.NAMA_FIRMA,OBMAIN.NO_RUJUKAN_FIRMA FROM (";
		   	sql_history += queryHistoryPeguam(skrinName,id,ID_PERBICARAAN,"INSERTDELETE");
			sql_history += " ) OBMAIN  WHERE  OBMAIN.ID_PEGUAM IS NOT NULL ";
			sql_history += ") MAIN";

			if(namaList.equals("list"+skrinName+"current"))
			{
				sql += " SELECT * FROM (";
				sql += " SELECT * FROM (";
				sql += " SELECT MAIN.* FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_asal_L1;
				sql += ") "+sql_close_column_L1;
				sql += " ) ";
				sql += sql_avoid_delete;
				sql += " UNION ALL ";
				sql += " SELECT MAIN.* FROM " + sql_history;
				sql += " ) ";

				sql += " ORDER BY NAMA_FIRMA";
			}

		}

		else if(skrinName.equals("ha"))
		{



			String sql_open_column = " (SELECT HIS.JENIS_AKTIVITI,NVL(HIS.ID_SEJARAHBIMAIN,'') AS ID_SEJARAHBIMAIN,HIS.NAMA_TABLE,HIS.NAMA_FIELD_PK," +
					" TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
			"(CASE WHEN HIS.JENIS_PERINTAH IS NOT NULL THEN HIS.JENIS_PERINTAH ELSE OBMAIN.JENIS_PERINTAH END)  AS JENIS_PERINTAH,"+
			"(CASE WHEN HIS.NAMA_NEGERI IS NOT NULL THEN HIS.NAMA_NEGERI ELSE OBMAIN.NAMA_NEGERI END)  AS NAMA_NEGERI,"+
			"(CASE WHEN HIS.NAMA_DAERAH IS NOT NULL THEN HIS.NAMA_DAERAH ELSE OBMAIN.NAMA_DAERAH END)  AS NAMA_DAERAH,"+
			" OBMAIN.ID_HA, " +
			"(CASE WHEN HIS.ID_JENISHA IS NOT NULL THEN HIS.ID_JENISHA ELSE OBMAIN.ID_JENISHA END)  AS ID_JENISHA,"+
			" OBMAIN.ID_HAPERMOHONAN, " +
			"(CASE WHEN HIS.JENIS_HA IS NOT NULL THEN HIS.JENIS_HA ELSE OBMAIN.JENIS_HA END)  AS JENIS_HA,"+
			"(CASE WHEN HIS.NO_DAFTAR IS NOT NULL THEN HIS.NO_DAFTAR ELSE OBMAIN.NO_DAFTAR END)  AS NO_DAFTAR,"+
			" (" +
			"(CASE WHEN HIS.BA_SIMATI IS NOT NULL THEN HIS.BA_SIMATI ELSE OBMAIN.BA_SIMATI END)"+
			" || '/' ||  " +
			"(CASE WHEN HIS.BB_SIMATI IS NOT NULL THEN HIS.BB_SIMATI ELSE OBMAIN.BB_SIMATI END)"+
			" ) AS BAHAGIAN_SIMATI, "+

			"(CASE WHEN HIS.NAMA_SAHAM IS NOT NULL THEN HIS.NAMA_SAHAM ELSE OBMAIN.NAMA_SAHAM END)  AS NAMA_SAHAM,"+
			"(CASE WHEN HIS.NO_SIJIL IS NOT NULL THEN HIS.NO_SIJIL ELSE OBMAIN.NO_SIJIL END)  AS NO_SIJIL,"+
			"(CASE WHEN HIS.BIL_UNIT IS NOT NULL THEN HIS.BIL_UNIT ELSE OBMAIN.BIL_UNIT END)  AS BIL_UNIT,"+
			"(CASE WHEN HIS.CATATAN IS NOT NULL THEN HIS.CATATAN ELSE OBMAIN.CATATAN END)  AS CATATAN,"+
			"(CASE WHEN HIS.JENAMA IS NOT NULL THEN HIS.JENAMA ELSE OBMAIN.JENAMA END)  AS JENAMA,"+
			"(CASE WHEN HIS.BUTIRAN IS NOT NULL THEN HIS.BUTIRAN ELSE OBMAIN.BUTIRAN END)  AS BUTIRAN ";





			sql_open_column += ", OBMAIN.FLAG_PA, OBMAIN.FLAG_PT, OBMAIN.FLAG_SELESAI " +
					" ,OBMAIN.ID_PERINTAH, OBMAIN.ID_PERINTAHHAOBMST " +//
					" ";

			String sql_close_column_L1  = " OBMAIN, ("+queryHistoryHartaAlih(skrinName,id,ID_PERBICARAAN,"UPDATE")+") HIS ";
		   	sql_close_column_L1 += " WHERE OBMAIN.ID_HAPERMOHONAN = HIS.ID_HAPERMOHONAN(+)) MAIN ";

		   	String sql_avoid_delete = " WHERE ID_HAPERMOHONAN NOT IN (SELECT NVL(ID_HAPERMOHONAN,'') ";
		   	sql_avoid_delete += " FROM ("+queryHistoryHartaAlih(skrinName,id,ID_PERBICARAAN,"INSERTDELETE")+"))";

		   	String sql_asal_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK," +
		   	" TO_CHAR(H.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI," +
		   	" NULL AS ID_PERINTAH, NULL AS ID_PERINTAHHAOBMST, "+ //
		   	" NULL AS ID_JENISPERINTAH, NULL AS JENIS_PERINTAH, N.NAMA_NEGERI,D.NAMA_DAERAH," +
		   	" TO_CHAR(H.ID_HA) AS  ID_HA, " +
		   	" TO_CHAR(H.ID_JENISHA) AS  ID_JENISHA, " +
			" TO_CHAR(H.ID_HAPERMOHONAN) AS ID_HAPERMOHONAN, " +
			" RUJ.KETERANGAN AS JENIS_HA, H.NO_DAFTAR, "
			+ " TO_CHAR(H.BA_SIMATI) AS BA_SIMATI, TO_CHAR(H.BB_SIMATI) AS BB_SIMATI, "

			+" H.NAMA_SAHAM AS NAMA_SAHAM, "
		   	+" H.NO_SIJIL AS NO_SIJIL, "
			+" TO_CHAR(H.BIL_UNIT) AS BIL_UNIT, "
		   	+" H.CATATAN AS CATATAN, "
		   	+" H.JENAMA AS JENAMA, "
		   	+" H.BUTIRAN AS BUTIRAN ";

		   	sql_asal_L1 += ", H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI ";

		   	sql_asal_L1 += " FROM TBLPPKHA HP,TBLPPKHAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, TBLPPKRUJJENISHA RUJ,"
			+" TBLRUJNEGERI N, TBLRUJDAERAH D "
			+ " WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_NEGERI = N.ID_NEGERI(+) AND H.ID_DAERAH = D.ID_DAERAH(+)  "
			+ " AND HP.ID_HA = H.ID_HA AND HP.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
			+ " AND H.ID_JENISHA = RUJ.ID_JENISHA(+) "
			+ " AND H.ID_SIMATI = MS.ID_SIMATI "
			+ " AND MS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "
			+ " AND MS.ID_PERMOHONANSIMATI = '"+ id+ "'  ";

		   	String sql_dulu_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK," +
		   	" TO_CHAR(H.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
		   	" TO_CHAR(JP.ID_JENISPERINTAH) AS ID_JENISPERINTAH,JP.JENIS_PERINTAH, " +
		   	" JP.ID_PERINTAH, JP.ID_PERINTAHHAOBMST, "+//
		   	" N.NAMA_NEGERI,D.NAMA_DAERAH," +
		   	" TO_CHAR(H.ID_HA) AS ID_HA, TO_CHAR(H.ID_JENISHA) AS ID_JENISHA, TO_CHAR(H.ID_HAPERMOHONAN) AS ID_HAPERMOHONAN, " +
			" RUJ.KETERANGAN AS JENIS_HA, H.NO_DAFTAR, "
			+ " TO_CHAR(H.BA_SIMATI) AS BA_SIMATI, TO_CHAR(H.BB_SIMATI) AS BB_SIMATI, "
		   	+" H.NAMA_SAHAM AS NAMA_SAHAM, "
		   	+" H.NO_SIJIL AS NO_SIJIL, "
			+" TO_CHAR(H.BIL_UNIT) AS BIL_UNIT, "
		   	+" H.CATATAN AS CATATAN, "
		   	+" H.JENAMA AS JENAMA, "
		   	+" H.BUTIRAN AS BUTIRAN ";






		   	sql_dulu_L1 += ", H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI ";

		   	sql_dulu_L1 += " FROM TBLPPKHA HP, TBLPPKHAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, "
			+ " TBLPPKPERMOHONAN P1, TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1, TBLPPKRUJJENISHA RUJ, "
			+ " (SELECT DISTINCT RP.ID_JENISPERINTAH, RP.KETERANGAN AS JENIS_PERINTAH, OBM.ID_HA , SM.ID_PERMOHONANSIMATI "
			+" ,PR.ID_PERINTAH, OBM.ID_PERINTAHHAOBMST "//
			+ " FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM, TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN KB, "
			+ " TBLPPKPERINTAH PR, TBLPPKPERINTAHHAOBMST OBM, TBLPPKRUJJENISPERINTAH RP "
			+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN "
			+ " AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN "
			+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN "
			+ " AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN "
			+ " AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
			+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH "
			//+" AND P.ID_PERMOHONAN = '"+ID_PERMOHONAN+"'"
			+" AND KB.ID_PERBICARAAN = '"+ID_PERBICARAAN+"' "
			+" ) JP, " +
			" TBLRUJNEGERI N, TBLRUJDAERAH D "
			+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
			+ " AND H.ID_HA = HP.ID_HA AND H.ID_NEGERI = N.ID_NEGERI(+) AND H.ID_DAERAH = D.ID_DAERAH(+) "
			+ " AND H.ID_PERMOHONANSIMATI = '" + id + "' "
			+ " AND H.ID_SIMATI = MS.ID_SIMATI "
			+ " AND H.ID_JENISHA = RUJ.ID_JENISHA(+) "
			+ " AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "
			+ " AND MS.ID_PERMOHONANSIMATI = '" + id + "' ";

		   	sql_dulu_L1 += " AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI "
			+ " AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+) "
			+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+) "
			+ " AND P1.NO_SUBJAKET < P.NO_SUBJAKET "
			+ " AND S.ID_SIMATI = MS.ID_SIMATI "
			+ " AND JP.ID_HA(+) = HP.ID_HA "
			+ " AND JP.ID_PERMOHONANSIMATI(+) = HP.ID_PERMOHONANSIMATI ";

		   	String sql_history = " (SELECT  OBMAIN.JENIS_AKTIVITI,OBMAIN.ID_SEJARAHBIMAIN,OBMAIN.NAMA_TABLE,OBMAIN.NAMA_FIELD_PK," +
		   	" TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
			" OBMAIN.JENIS_PERINTAH, OBMAIN.NAMA_NEGERI,OBMAIN.NAMA_DAERAH,  "+
			" OBMAIN.ID_HA, OBMAIN.ID_JENISHA, OBMAIN.ID_HAPERMOHONAN, OBMAIN.JENIS_HA, " +
			" OBMAIN.NO_DAFTAR, "
			+ " (OBMAIN.BA_SIMATI || '/' ||  OBMAIN.BB_SIMATI) AS BAHAGIAN_SIMATI, "

		   	+" OBMAIN.NAMA_SAHAM AS NAMA_SAHAM, "
		   	+" OBMAIN.NO_SIJIL AS NO_SIJIL, "
		   	+" TO_CHAR(OBMAIN.BIL_UNIT) AS BIL_UNIT, "
		   	+" OBMAIN.CATATAN AS CATATAN, "
		   	+" OBMAIN.JENAMA AS JENAMA, "
		   	+" OBMAIN.BUTIRAN AS BUTIRAN ";


		   	sql_history += ", '' AS FLAG_PA, '' AS FLAG_PT, '' AS FLAG_SELESAI" +
		   			" ,'' AS ID_PERINTAH, '' AS ID_PERINTAHHAOBMST " +//
		   			"  ";

		   	sql_history += " FROM (";
		   	sql_history += queryHistoryHartaAlih(skrinName,id,ID_PERBICARAAN,"INSERTDELETE");
			sql_history += " ) OBMAIN  WHERE  OBMAIN.ID_HAPERMOHONAN IS NOT NULL ";
			sql_history += ") MAIN";


			String maklumat_ha = "	CASE "+
					" WHEN SUPERMAIN.ID_JENISHA = '1' AND SUPERMAIN.NAMA_SAHAM IS NOT NULL AND SUPERMAIN.NO_DAFTAR IS NOT NULL AND SUPERMAIN.NO_SIJIL IS NOT NULL  THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(SUPERMAIN.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(SUPERMAIN.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| SUPERMAIN.BIL_UNIT || SUPERMAIN.CATATAN "+
					" WHEN SUPERMAIN.ID_JENISHA = '1' AND SUPERMAIN.NAMA_SAHAM IS NOT NULL AND SUPERMAIN.NO_DAFTAR IS NOT NULL AND SUPERMAIN.NO_SIJIL IS NULL  THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.NAMA_SAHAM || '<br>' || 'No Ahli: ' || NVL(SUPERMAIN.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| SUPERMAIN.BIL_UNIT || SUPERMAIN.CATATAN "+
					" WHEN SUPERMAIN.ID_JENISHA = '1' AND SUPERMAIN.NAMA_SAHAM IS NOT NULL AND SUPERMAIN.NO_DAFTAR IS NULL AND SUPERMAIN.NO_SIJIL IS NOT NULL  THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(SUPERMAIN.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| SUPERMAIN.BIL_UNIT || SUPERMAIN.CATATAN "+
					" WHEN SUPERMAIN.ID_JENISHA = '1' AND SUPERMAIN.NAMA_SAHAM IS NOT NULL AND SUPERMAIN.NO_DAFTAR IS NULL AND SUPERMAIN.NO_SIJIL IS NOT NULL  THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.NAMA_SAHAM || '<br>' || 'No Sijil: ' || NVL(SUPERMAIN.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| SUPERMAIN.BIL_UNIT || SUPERMAIN.CATATAN "+
					" WHEN SUPERMAIN.ID_JENISHA = '1' AND SUPERMAIN.JENAMA IS NOT NULL AND SUPERMAIN.NO_DAFTAR IS NOT NULL AND SUPERMAIN.NO_SIJIL IS NULL AND SUPERMAIN.NAMA_SAHAM IS NULL THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.JENAMA || '<br>' || 'No Ahli: ' || NVL(SUPERMAIN.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(SUPERMAIN.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| SUPERMAIN.BIL_UNIT || SUPERMAIN.CATATAN "+
					" WHEN SUPERMAIN.ID_JENISHA = '1' AND SUPERMAIN.JENAMA IS NOT NULL AND SUPERMAIN.NO_DAFTAR IS NULL AND SUPERMAIN.NO_SIJIL IS NULL AND SUPERMAIN.NAMA_SAHAM IS NOT NULL THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.JENAMA || '<br>' || SUPERMAIN.NAMA_SAHAM || SUPERMAIN.CATATAN "+
					" WHEN SUPERMAIN.ID_JENISHA = '1' AND SUPERMAIN.NAMA_SAHAM IS NULL AND SUPERMAIN.NO_DAFTAR IS NOT NULL AND SUPERMAIN.NO_SIJIL IS NOT NULL  THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.JENAMA || '<br>' || 'No Ahli: ' || NVL(SUPERMAIN.NO_DAFTAR,' - ') || '<br>' || 'No Sijil: ' || NVL(SUPERMAIN.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| SUPERMAIN.BIL_UNIT "+
					" WHEN SUPERMAIN.ID_JENISHA = '1' AND SUPERMAIN.NAMA_SAHAM IS NULL AND SUPERMAIN.NO_DAFTAR IS NOT NULL AND SUPERMAIN.NO_SIJIL IS NULL  THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.JENAMA || '<br>' || 'No Ahli: ' || NVL(SUPERMAIN.NO_DAFTAR,' - ') || '<br>' ||'Bil. Unit: '|| SUPERMAIN.BIL_UNIT "+
					" WHEN SUPERMAIN.ID_JENISHA = '1' AND SUPERMAIN.NAMA_SAHAM IS NULL AND SUPERMAIN.NO_DAFTAR IS NULL AND SUPERMAIN.NO_SIJIL IS NOT NULL  THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.JENAMA || '<br>' || 'No Sijil: ' || NVL(SUPERMAIN.NO_SIJIL,' - ')|| '<br>'|| 'Bil. Unit: '|| SUPERMAIN.BIL_UNIT "+
					" WHEN SUPERMAIN.ID_JENISHA = '98' AND SUPERMAIN.NAMA_SAHAM IS NULL AND SUPERMAIN.JENAMA IS NULL AND SUPERMAIN.NO_SIJIL IS NULL AND SUPERMAIN.NO_DAFTAR IS NOT NULL  THEN SUPERMAIN.JENIS_HA ||' - ' || SUPERMAIN.NO_DAFTAR  "+
					" WHEN SUPERMAIN.ID_JENISHA  = '2' THEN SUPERMAIN.JENIS_HA ||' - '|| SUPERMAIN.JENAMA || '<br>' || 'No Akaun: ' || SUPERMAIN.NO_DAFTAR  "+
					" WHEN SUPERMAIN.ID_JENISHA  = '3' THEN SUPERMAIN.JENIS_HA || ' - ' || SUPERMAIN.JENAMA || '<br>' || 'No Pendaftaran: ' || SUPERMAIN.NO_DAFTAR "+
					" WHEN SUPERMAIN.ID_JENISHA  = '4' THEN SUPERMAIN.JENIS_HA || ' - ' || SUPERMAIN.JENAMA || '<br>' || 'No Lot: ' || SUPERMAIN.NO_DAFTAR "+
					" WHEN SUPERMAIN.ID_JENISHA  = '5' THEN SUPERMAIN.JENIS_HA || ' - ' || SUPERMAIN.JENAMA || '<br>' || 'No Polisi: ' || SUPERMAIN.NO_DAFTAR || '<br>' || SUPERMAIN.CATATAN "+
					" WHEN SUPERMAIN.ID_JENISHA  = '6' THEN SUPERMAIN.JENIS_HA || ' - ' || SUPERMAIN.JENAMA "+
					" WHEN SUPERMAIN.ID_JENISHA  = '7' OR SUPERMAIN.ID_JENISHA   = '11' THEN SUPERMAIN.JENIS_HA || SUPERMAIN.CATATAN "+
					" WHEN SUPERMAIN.ID_JENISHA  = '12' THEN SUPERMAIN.JENIS_HA || ' - ' || SUPERMAIN.BUTIRAN "+
					" ELSE SUPERMAIN.JENIS_HA || ' - ' || SUPERMAIN.BUTIRAN "+
					" END || '<br>BHGN SIMATI : ' || SUPERMAIN.BAHAGIAN_SIMATI AS MAKLUMAT_HA, ";

			if(namaList.equals("list"+skrinName+"current"))
			{
				sql += " SELECT 	SUPERMAIN.FLAG_PA,SUPERMAIN.FLAG_PT,SUPERMAIN.FLAG_SELESAI,"+maklumat_ha+" SUPERMAIN.* FROM (";
				sql += " SELECT * FROM (";
				sql += " SELECT MAIN.* FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_asal_L1;
				sql += ") "+sql_close_column_L1;
				sql += " ) ";
				sql += sql_avoid_delete;
				sql += " UNION ALL ";
				sql += " SELECT MAIN.* FROM " + sql_history;
				sql += " ) SUPERMAIN ";

				sql += " WHERE (CASE WHEN (ID_SEJARAHBIMAIN = ''  OR ID_SEJARAHBIMAIN IS NULL) " +
						//" AND FLAG_SELESAI != 'Y'" +
						" THEN 1" +
			    		" WHEN  ID_SEJARAHBIMAIN IS NOT NULL THEN 1 ELSE 0 END) = 1 ";

				sql += " ORDER BY ID_HA ";
			}
			else if(namaList.equals("list"+skrinName+"previous"))
			{
				sql += " SELECT 	SUPERMAIN.FLAG_PA,SUPERMAIN.FLAG_PT,SUPERMAIN.FLAG_SELESAI,"+maklumat_ha+" SUPERMAIN.* FROM (";
				sql += " SELECT MAIN.* FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_dulu_L1;
				sql += ") "+sql_close_column_L1;
				sql += " ) SUPERMAIN ";
				sql += sql_avoid_delete;


				sql += " AND (CASE WHEN (ID_SEJARAHBIMAIN = ''  OR ID_SEJARAHBIMAIN IS NULL) AND FLAG_SELESAI != 'Y' THEN 1" +
			    		" WHEN ID_SEJARAHBIMAIN IS NOT NULL THEN 1 " +
			    		" WHEN (ID_SEJARAHBIMAIN = ''  OR ID_SEJARAHBIMAIN IS NULL) AND FLAG_SELESAI = 'Y' AND ID_PERINTAH IS NOT NULL AND ID_PERINTAHHAOBMST IS NOT NULL THEN 1 " +//
			    		" ELSE 0 END) = 1 ";
				sql += " AND ID_HA IN (SELECT ID_HA  FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '"+id+"') ";

				sql += " ORDER BY ID_HA ";
			}
		}
		else if(skrinName.equals("htaah") || skrinName.equals("htaahx"))
		{
			//id = permohonansimati


			String sql_open_column = " (SELECT HIS.JENIS_AKTIVITI,NVL(HIS.ID_SEJARAHBIMAIN,'') AS ID_SEJARAHBIMAIN,HIS.NAMA_TABLE,HIS.NAMA_FIELD_PK," +
					" TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
			"(CASE WHEN HIS.JENIS_PERINTAH IS NOT NULL THEN HIS.JENIS_PERINTAH ELSE OBMAIN.JENIS_PERINTAH END)  AS JENIS_PERINTAH,"+
			"(CASE WHEN HIS.NAMA_NEGERI IS NOT NULL THEN HIS.NAMA_NEGERI ELSE OBMAIN.NAMA_NEGERI END)  AS NAMA_NEGERI,"+
			"(CASE WHEN HIS.NO_LOT_ID IS NOT NULL THEN HIS.NO_LOT_ID  ELSE OBMAIN.NO_LOT_ID END)  AS NO_LOT_ID,"+
			"(CASE WHEN HIS.NAMA_RANCANGAN IS NOT NULL THEN HIS.NAMA_RANCANGAN ELSE OBMAIN.NAMA_RANCANGAN  END)  AS NAMA_RANCANGAN,"+
			"(CASE WHEN HIS.CATATAN IS NOT NULL THEN HIS.CATATAN ELSE OBMAIN.CATATAN END)  AS CATATAN,"+
			"(CASE WHEN HIS.FLAG_KATEGORI_HTA IS NOT NULL THEN HIS.FLAG_KATEGORI_HTA ELSE OBMAIN.FLAG_KATEGORI_HTA END)  AS FLAG_KATEGORI_HTA,"+
			"(CASE WHEN HIS.ALAMAT_HTA1 IS NOT NULL THEN HIS.ALAMAT_HTA1 ELSE OBMAIN.ALAMAT_HTA1 END)  AS ALAMAT_HTA1,"+
			"(CASE WHEN HIS.ALAMAT_HTA2 IS NOT NULL THEN HIS.ALAMAT_HTA2 ELSE OBMAIN.ALAMAT_HTA2 END)  AS ALAMAT_HTA2,"+
			"(CASE WHEN HIS.ALAMAT_HTA3 IS NOT NULL THEN HIS.ALAMAT_HTA3 ELSE OBMAIN.ALAMAT_HTA3 END)  AS ALAMAT_HTA3,"+
			"(CASE WHEN HIS.POSKOD_HTA IS NOT NULL THEN HIS.POSKOD_HTA ELSE OBMAIN.POSKOD_HTA END)  AS POSKOD_HTA,"+
			"(CASE WHEN HIS.NAMA_PEMAJU IS NOT NULL THEN HIS.NAMA_PEMAJU ELSE OBMAIN.NAMA_PEMAJU END)  AS NAMA_PEMAJU,"+
			"(CASE WHEN HIS.ALAMAT_PEMAJU1 IS NOT NULL THEN HIS.ALAMAT_PEMAJU1 ELSE OBMAIN.ALAMAT_PEMAJU1 END)  AS ALAMAT_PEMAJU1,"+
			"(CASE WHEN HIS.ALAMAT_PEMAJU2 IS NOT NULL THEN HIS.ALAMAT_PEMAJU2 ELSE OBMAIN.ALAMAT_PEMAJU2 END)  AS ALAMAT_PEMAJU2,"+
			"(CASE WHEN HIS.ALAMAT_PEMAJU3 IS NOT NULL THEN HIS.ALAMAT_PEMAJU3 ELSE OBMAIN.ALAMAT_PEMAJU3 END)  AS ALAMAT_PEMAJU3,"+
			"(CASE WHEN HIS.POSKOD_PEMAJU IS NOT NULL THEN HIS.POSKOD_PEMAJU ELSE OBMAIN.POSKOD_PEMAJU END)  AS POSKOD_PEMAJU,"+
			"(CASE WHEN HIS.BANDARPEMAJU IS NOT NULL THEN HIS.BANDARPEMAJU ELSE OBMAIN.BANDARPEMAJU END)  AS BANDARPEMAJU,"+
			"(CASE WHEN HIS.BANDARHTA IS NOT NULL THEN HIS.BANDARHTA ELSE OBMAIN.BANDARHTA END)  AS BANDARHTA,"+


			"(CASE WHEN HIS.NAMA_DAERAH IS NOT NULL THEN HIS.NAMA_DAERAH ELSE OBMAIN.NAMA_DAERAH END)  AS NAMA_DAERAH,"+
			"(CASE WHEN HIS.NAMA_MUKIM IS NOT NULL THEN HIS.NAMA_MUKIM ELSE OBMAIN.NAMA_MUKIM END)  AS NAMA_MUKIM,"+
			" OBMAIN.ID_HTA, " +
			" OBMAIN.ID_HTAPERMOHONAN, " +
			" (" +
			"(CASE WHEN HIS.KOD_JENIS_HAKMILIK IS NOT NULL THEN HIS.KOD_JENIS_HAKMILIK ELSE OBMAIN.KOD_JENIS_HAKMILIK END)"+
			" || '' ||  " +
			"(CASE WHEN HIS.NO_HAKMILIK IS NOT NULL THEN HIS.NO_HAKMILIK ELSE OBMAIN.NO_HAKMILIK END)"+
			" ) AS NO_HAKMILIK_FULL, " +
			"(CASE WHEN HIS.NO_PT IS NOT NULL THEN HIS.NO_PT ELSE OBMAIN.NO_PT END)  AS NO_PT,"+
			" (" +
			"(CASE WHEN HIS.BA_SIMATI IS NOT NULL THEN HIS.BA_SIMATI ELSE OBMAIN.BA_SIMATI END)"+
			" || '/' ||  " +
			"(CASE WHEN HIS.BB_SIMATI IS NOT NULL THEN HIS.BB_SIMATI ELSE OBMAIN.BB_SIMATI END)"+
			" ) AS BAHAGIAN_SIMATI, "+
			"(CASE WHEN HIS.JENIS_HTA IS NOT NULL THEN HIS.JENIS_HTA ELSE OBMAIN.JENIS_HTA END)  AS JENIS_HTA, " +
			"(CASE WHEN HIS.NO_PERJANJIAN IS NOT NULL THEN HIS.NO_PERJANJIAN ELSE OBMAIN.NO_PERJANJIAN END)  AS NO_PERJANJIAN, " +
			"(CASE WHEN HIS.TARIKH_PERJANJIAN IS NOT NULL THEN HIS.TARIKH_PERJANJIAN ELSE OBMAIN.TARIKH_PERJANJIAN END)  AS TARIKH_PERJANJIAN, " +
			"(CASE WHEN HIS.NO_ROH IS NOT NULL THEN HIS.NO_ROH ELSE OBMAIN.NO_ROH END)  AS NO_ROH, " +
			"(CASE WHEN HIS.JENIS_KEPENTINGAN IS NOT NULL THEN HIS.JENIS_KEPENTINGAN ELSE OBMAIN.JENIS_KEPENTINGAN END)  AS JENIS_KEPENTINGAN, " +
			"(CASE WHEN HIS.NO_PERJANJIAN IS NOT NULL THEN HIS.NO_PERJANJIAN || ' ' ELSE OBMAIN.NO_PERJANJIAN || ' ' END) || (CASE WHEN HIS.TARIKH_PERJANJIAN IS NOT NULL THEN HIS.TARIKH_PERJANJIAN ELSE OBMAIN.TARIKH_PERJANJIAN END) AS NO_PERJANJIAN_FULL ";

			//"(CASE WHEN HIS.JENIS_HTA IS NOT NULL THEN HIS.JENIS_HTA ELSE OBMAIN.JENIS_HTA END)  AS JENIS_HTA, " +

			sql_open_column += ", OBMAIN.FLAG_PA, OBMAIN.FLAG_PT, OBMAIN.FLAG_SELESAI " +
					" ,  OBMAIN.ID_PERINTAH, OBMAIN.ID_PERINTAHHTAOBMST " + //
					"  ";

			String sql_close_column_L1  = " OBMAIN, ("+queryHistoryHarta(skrinName,id,ID_PERBICARAAN,"UPDATE")+") HIS ";
		   	sql_close_column_L1 += " WHERE OBMAIN.ID_HTAPERMOHONAN = HIS.ID_HTAPERMOHONAN(+)) MAIN ";

		   	String sql_avoid_delete = " WHERE ID_HTAPERMOHONAN NOT IN (SELECT NVL(ID_HTAPERMOHONAN,'') ";
		   	sql_avoid_delete += " FROM ("+queryHistoryHarta(skrinName,id,ID_PERBICARAAN,"INSERTDELETE")+"))";

		   	String sql_asal_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK," +
		   	" TO_CHAR(H.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
		   	" NULL AS ID_PERINTAH, NULL AS ID_PERINTAHHTAOBMST, "+ //
		   	" NULL AS ID_JENISPERINTAH, NULL AS JENIS_PERINTAH, N.NAMA_NEGERI," +
		   	" H.NO_LOT_ID,"+
			" H.NAMA_RANCANGAN,"+
		   	" H.CATATAN," +
		   	" H.FLAG_KATEGORI_HTA," +
		   	" H.ALAMAT_HTA1," +
		   	" H.ALAMAT_HTA2," +
		   	" H.ALAMAT_HTA3," +
		   	" H.POSKOD_HTA," +
		   	" H.NAMA_PEMAJU," +
		   	" H.ALAMAT_PEMAJU1," +
		   	" H.ALAMAT_PEMAJU2," +
		   	" H.ALAMAT_PEMAJU3," +
		   	" H.POSKOD_PEMAJU," +
			" BH.KETERANGAN AS BANDARHTA," +
			" BP.KETERANGAN AS BANDARPEMAJU," +

		   	" D.NAMA_DAERAH,M.NAMA_MUKIM,  "
			+ " TO_CHAR(H.ID_HTA) AS  ID_HTA, TO_CHAR(H.ID_HTAPERMOHONAN) AS ID_HTAPERMOHONAN, H.NO_HAKMILIK, RUJ.KOD_JENIS_HAKMILIK, H.NO_PT, "
			+ " TO_CHAR(H.BA_SIMATI) AS BA_SIMATI, TO_CHAR(H.BB_SIMATI) AS BB_SIMATI, "
			+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI,H.JENIS_HTA, H.NO_PERJANJIAN, TO_CHAR(H.TARIKH_PERJANJIAN,'DD/MM/YYYY') AS TARIKH_PERJANJIAN, H.NO_ROH, H.JENIS_KEPENTINGAN ";

			//sql_asal_L1 += ", H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI ";

			sql_asal_L1 += " FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, TBLRUJJENISHAKMILIK RUJ,"
			+" TBLRUJNEGERI N, TBLRUJDAERAH D,TBLRUJMUKIM M,TBLRUJBANDAR BP,TBLRUJBANDAR BH "
			+ " WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_NEGERI = N.ID_NEGERI AND H.ID_DAERAH = D.ID_DAERAH AND H.ID_MUKIM = M.ID_MUKIM  "
			+ " AND HP.ID_HTA = H.ID_HTA AND HP.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI  "
			+ " AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
			+ " AND H.ID_BANDARHTA = BH.ID_BANDAR(+) "
			+ " AND H.ID_BANDARPEMAJU = BP.ID_BANDAR(+) "
			+ " AND H.ID_SIMATI = MS.ID_SIMATI "
			+ " AND MS.ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI "
			+ " AND MS.ID_PERMOHONANSIMATI = '"+ id+ "'  ";

		   	String sql_dulu_L1 = " SELECT NULL AS ID_SEJARAHBIMAIN,NULL AS NAMA_TABLE,NULL AS NAMA_FIELD_PK," +
		   	" TO_CHAR(H.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
		   	" JP.ID_PERINTAH,JP.ID_PERINTAHHTAOBMST, "+//
		   	" TO_CHAR(JP.ID_JENISPERINTAH) AS ID_JENISPERINTAH,JP.JENIS_PERINTAH, N.NAMA_NEGERI," +
			" H.NO_LOT_ID,"+
			" H.NAMA_RANCANGAN,"+
		   	" H.CATATAN," +
		   	" H.FLAG_KATEGORI_HTA," +
		   	" H.ALAMAT_HTA1," +
		   	" H.ALAMAT_HTA2," +
		   	" H.ALAMAT_HTA3," +
		   	" H.POSKOD_HTA," +
		   	" H.NAMA_PEMAJU," +
		   	" H.ALAMAT_PEMAJU1," +
		   	" H.ALAMAT_PEMAJU2," +
		   	" H.ALAMAT_PEMAJU3," +
		   	" H.POSKOD_PEMAJU," +
			" BH.KETERANGAN AS BANDARHTA," +
			" BP.KETERANGAN AS BANDARPEMAJU," +
		   	" D.NAMA_DAERAH,M.NAMA_MUKIM,  "
			+ " TO_CHAR(H.ID_HTA) AS ID_HTA, TO_CHAR(H.ID_HTAPERMOHONAN) AS ID_HTAPERMOHONAN, H.NO_HAKMILIK, RUJ.KOD_JENIS_HAKMILIK, H.NO_PT, "
			+ " TO_CHAR(H.BA_SIMATI) AS BA_SIMATI, TO_CHAR(H.BB_SIMATI) AS BB_SIMATI, "
			+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI,H.JENIS_HTA, H.NO_PERJANJIAN, TO_CHAR(H.TARIKH_PERJANJIAN,'DD/MM/YYYY') AS TARIKH_PERJANJIAN, H.NO_ROH, H.JENIS_KEPENTINGAN  ";

			//sql_dulu_L1 += ", H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI ";

		   	sql_dulu_L1 += " FROM TBLPPKHTA HP, TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS, "
			+ " TBLPPKPERMOHONAN P1, TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ, TBLRUJBANDAR BP,TBLRUJBANDAR BH, "
			+ " (SELECT DISTINCT RP.ID_JENISPERINTAH, RP.KETERANGAN AS JENIS_PERINTAH, OBM.ID_HTA , SM.ID_PERMOHONANSIMATI" +
			" , PR.ID_PERINTAH, OBM.ID_PERINTAHHTAOBMST "//
			+ " FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM, TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN KB, "
			+ " TBLPPKPERINTAH PR, TBLPPKPERINTAHHTAOBMST OBM, TBLPPKRUJJENISPERINTAH RP "
			+ " WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN "
			+ " AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN "
			+ " AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN "
			+ " AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN "
			+ " AND PR.ID_PERINTAH = OBM.ID_PERINTAH "
			+ " AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH " +
			//" AND P.ID_PERMOHONAN = '"+ID_PERMOHONAN+"'" +
					" AND KB.ID_PERBICARAAN = '"+ID_PERBICARAAN+"' " +//
					" ) JP, " +
					" TBLRUJNEGERI N, TBLRUJDAERAH D,TBLRUJMUKIM M "
			+ " WHERE H.ID_SIMATI = S.ID_SIMATI "
			+ " AND H.ID_HTA = HP.ID_HTA AND H.ID_NEGERI = N.ID_NEGERI AND H.ID_DAERAH = D.ID_DAERAH AND H.ID_MUKIM = M.ID_MUKIM "
			+ " AND H.ID_BANDARHTA = BH.ID_BANDAR(+) "
			+ " AND H.ID_BANDARPEMAJU = BP.ID_BANDAR(+) "
			+ " AND H.ID_PERMOHONANSIMATI = '" + id + "' "
			+ " AND H.ID_SIMATI = MS.ID_SIMATI "
			+ " AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "
			+ " AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "
			+ " AND MS.ID_PERMOHONANSIMATI = '" + id + "' ";

		   	sql_dulu_L1 += " AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI "
			+ " AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+) "
			+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+) "
			+ " AND P1.NO_SUBJAKET < P.NO_SUBJAKET "
			+ " AND S.ID_SIMATI = MS.ID_SIMATI "
			+ " AND JP.ID_HTA(+) = HP.ID_HTA "
			+ " AND JP.ID_PERMOHONANSIMATI(+) = HP.ID_PERMOHONANSIMATI ";

		   	String sql_history = " (SELECT  OBMAIN.JENIS_AKTIVITI,OBMAIN.ID_SEJARAHBIMAIN,OBMAIN.NAMA_TABLE,OBMAIN.NAMA_FIELD_PK," +
		   	" TO_CHAR(OBMAIN.ID_PERMOHONANSIMATI) AS ID_PERMOHONANSIMATI, " +
			" OBMAIN.JENIS_PERINTAH, OBMAIN.NAMA_NEGERI, " +

			" OBMAIN.NO_LOT_ID,"+
			" OBMAIN.NAMA_RANCANGAN,"+
			" OBMAIN.CATATAN, " +

			" OBMAIN.FLAG_KATEGORI_HTA," +
				" OBMAIN.ALAMAT_HTA1," +
				" OBMAIN.ALAMAT_HTA2," +
				" OBMAIN.ALAMAT_HTA3," +
				" OBMAIN.POSKOD_HTA," +
				" OBMAIN.NAMA_PEMAJU," +
				" OBMAIN.ALAMAT_PEMAJU1," +
				" OBMAIN.ALAMAT_PEMAJU2," +
				" OBMAIN.ALAMAT_PEMAJU3," +
				" OBMAIN.POSKOD_PEMAJU," +
			" OBMAIN.BANDARHTA," +
			" OBMAIN.BANDARPEMAJU," +

			" OBMAIN.NAMA_DAERAH,OBMAIN.NAMA_MUKIM,  "+
			" OBMAIN.ID_HTA, OBMAIN.ID_HTAPERMOHONAN, " +
			" (OBMAIN.KOD_JENIS_HAKMILIK || '' ||  OBMAIN.NO_HAKMILIK) AS NO_HAKMILIK_FULL, " +
			" OBMAIN.NO_PT, "
			+ " (OBMAIN.BA_SIMATI || '/' ||  OBMAIN.BB_SIMATI) AS BAHAGIAN_SIMATI, OBMAIN.JENIS_HTA, "
			+"OBMAIN.NO_PERJANJIAN, OBMAIN.TARIKH_PERJANJIAN, OBMAIN.NO_ROH, OBMAIN.JENIS_KEPENTINGAN,  ";
			sql_history += "(CASE WHEN OBMAIN.NO_PERJANJIAN IS NOT NULL THEN OBMAIN.NO_PERJANJIAN || ' ' ELSE '' END) || (CASE WHEN OBMAIN.TARIKH_PERJANJIAN IS NOT NULL THEN OBMAIN.TARIKH_PERJANJIAN ELSE '' END) AS NO_PERJANJIAN_FULL ";
			sql_history += ", '' AS FLAG_PA, '' AS FLAG_PT, '' AS FLAG_SELESAI, '' AS ID_PERINTAH, '' AS ID_PERINTAHHTAOBMST   ";

		   	sql_history += " FROM (";
		   	sql_history += queryHistoryHarta(skrinName,id,ID_PERBICARAAN,"INSERTDELETE");
			sql_history += " ) OBMAIN  WHERE  OBMAIN.ID_HTAPERMOHONAN IS NOT NULL ";
			sql_history += ") MAIN";



			String maklumat_hta = "	CASE "+
					"	WHEN SUPERMAIN.JENIS_HTA = 'Y' THEN SUPERMAIN.NO_HAKMILIK_FULL || ' ' || SUPERMAIN.NO_PT ||' '|| SUPERMAIN.NAMA_MUKIM || ', ' ||  SUPERMAIN.NAMA_DAERAH "+
					"	WHEN SUPERMAIN.JENIS_HTA = 'T' AND SUPERMAIN.FLAG_KATEGORI_HTA = '1' AND SUPERMAIN.ALAMAT_HTA1 IS NULL THEN 'Harta beralamat: -' "+
					"	WHEN SUPERMAIN.JENIS_HTA = 'T' AND SUPERMAIN.FLAG_KATEGORI_HTA = '1' AND SUPERMAIN.ALAMAT_HTA2 IS NULL AND SUPERMAIN.ALAMAT_HTA3 IS NULL THEN  'Harta beralamat: ' || REPLACE(UPPER(SUPERMAIN.ALAMAT_HTA1),',') || ', ' || REPLACE(UPPER(SUPERMAIN.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(SUPERMAIN.BANDARHTA),',') ||', '|| REPLACE(UPPER(SUPERMAIN.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(SUPERMAIN.NAMA_PEMAJU) || ' dengan Simati bertarikh ' || SUPERMAIN.TARIKH_PERJANJIAN  "+
					"	WHEN SUPERMAIN.JENIS_HTA = 'T' AND SUPERMAIN.FLAG_KATEGORI_HTA = '1' AND SUPERMAIN.ALAMAT_HTA2 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(SUPERMAIN.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(SUPERMAIN.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(SUPERMAIN.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(SUPERMAIN.BANDARHTA),',') ||', '|| REPLACE(UPPER(SUPERMAIN.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(SUPERMAIN.NAMA_PEMAJU) || ' dengan Simati bertarikh ' || SUPERMAIN.TARIKH_PERJANJIAN "+
					"	WHEN SUPERMAIN.JENIS_HTA = 'T' AND SUPERMAIN.FLAG_KATEGORI_HTA = '1' AND SUPERMAIN.ALAMAT_HTA3 IS NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(SUPERMAIN.ALAMAT_HTA1),',') ||', ' || REPLACE(UPPER(SUPERMAIN.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(SUPERMAIN.POSKOD_HTA),',') || ' ' ||REPLACE(UPPER(SUPERMAIN.BANDARHTA),',') ||', '|| REPLACE(UPPER(SUPERMAIN.NAMA_NEGERI),',')|| '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(SUPERMAIN.NAMA_PEMAJU) || ' dengan Simati bertarikh ' || SUPERMAIN.TARIKH_PERJANJIAN "+
					"	WHEN SUPERMAIN.JENIS_HTA = 'T' AND SUPERMAIN.FLAG_KATEGORI_HTA = '1' AND SUPERMAIN.ALAMAT_HTA3 IS NOT NULL THEN 'Harta beralamat: ' || REPLACE(UPPER(SUPERMAIN.ALAMAT_HTA1),',')||', ' || REPLACE(UPPER(SUPERMAIN.ALAMAT_HTA2),',') || ', ' || REPLACE(UPPER(SUPERMAIN.ALAMAT_HTA3),',') || ', ' || REPLACE(UPPER(SUPERMAIN.POSKOD_HTA),',') || ' ' || REPLACE(UPPER(SUPERMAIN.BANDARHTA),',') ||', '|| REPLACE(UPPER(SUPERMAIN.NAMA_NEGERI),',') || '<br>' || 'Kepentingan si mati mengikut Surat Perjanjian Jualbeli - di antara ' || UPPER(SUPERMAIN.NAMA_PEMAJU) || ' dengan Simati bertarikh ' || SUPERMAIN.TARIKH_PERJANJIAN "+
					"	WHEN SUPERMAIN.JENIS_HTA = 'T' AND SUPERMAIN.FLAG_KATEGORI_HTA = '2' THEN SUPERMAIN.NO_ROH || ' ' || SUPERMAIN.NO_LOT_ID || '<br>' || SUPERMAIN.NAMA_RANCANGAN || ',' || SUPERMAIN.NAMA_MUKIM || ',' || SUPERMAIN.NAMA_DAERAH "+
					"	WHEN SUPERMAIN.JENIS_HTA = 'T' AND SUPERMAIN.FLAG_KATEGORI_HTA = '3' THEN SUPERMAIN.JENIS_KEPENTINGAN ELSE '' END " +
					" || '<br>BHGN SIMATI : ' || SUPERMAIN.BAHAGIAN_SIMATI AS MAKLUMAT_HTA, ";

			if(namaList.equals("list"+skrinName+"current"))
			{
				sql += " SELECT 	SUPERMAIN.FLAG_PA,SUPERMAIN.FLAG_PT,SUPERMAIN.FLAG_SELESAI, "+maklumat_hta+" SUPERMAIN.* FROM (";
				sql += " SELECT * FROM (";
				sql += " SELECT MAIN.* FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_asal_L1;
				sql += ") "+sql_close_column_L1;
				sql += " ) ";
				sql += sql_avoid_delete;
				sql += " UNION ALL ";
				sql += " SELECT MAIN.* FROM " + sql_history;
				sql += " ) SUPERMAIN ";

			   	if(skrinName.equals("htaah"))
			   	{
			   		sql += " WHERE JENIS_HTA = 'Y' ";
			   	}
			   	else if(skrinName.equals("htaahx"))
			   	{
			   		sql += " WHERE JENIS_HTA = 'T' ";
			   	}

			   	sql += " AND (CASE WHEN (ID_SEJARAHBIMAIN = ''  OR ID_SEJARAHBIMAIN IS NULL) " +
			   			//" AND FLAG_SELESAI != 'Y' " +
			   			" THEN 1" +
			    		" WHEN ID_SEJARAHBIMAIN IS NOT NULL THEN 1 ELSE 0 END) = 1 ";

				sql += " ORDER BY ID_HTA ";
			}
			else if(namaList.equals("list"+skrinName+"previous"))
			{
				sql += " SELECT 	SUPERMAIN.FLAG_PA,SUPERMAIN.FLAG_PT,SUPERMAIN.FLAG_SELESAI, "+maklumat_hta+" SUPERMAIN.* FROM (";
				sql += " SELECT MAIN.* FROM "+sql_open_column;
				sql += " FROM ( ";
				sql += sql_dulu_L1;
				sql += ") "+sql_close_column_L1;
				sql += " ) SUPERMAIN ";
				sql += sql_avoid_delete;
				if(skrinName.equals("htaah"))
			   	{
			   		sql += " AND JENIS_HTA = 'Y' ";
			   	}
			   	else if(skrinName.equals("htaahx"))
			   	{
			   		sql += " AND JENIS_HTA = 'T' ";
			   	}

				sql += " AND (CASE WHEN (ID_SEJARAHBIMAIN = ''  OR ID_SEJARAHBIMAIN IS NULL) AND FLAG_SELESAI != 'Y' THEN 1" +
			    		" WHEN ID_SEJARAHBIMAIN IS NOT NULL THEN 1 " +
			    		" WHEN (ID_SEJARAHBIMAIN = ''  OR ID_SEJARAHBIMAIN IS NULL) AND FLAG_SELESAI = 'Y' AND ID_PERINTAH IS NOT NULL AND ID_PERINTAHHTAOBMST IS NOT NULL THEN 1 " +//
			    		" ELSE 0 END) = 1 ";
				sql += " AND ID_HTA IN (SELECT ID_HTA  FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '"+id+"') ";
				sql += " ORDER BY ID_HTA ";
			}
		}
		return sql;
	}

	public String queryGetData(String skrinName,String table_name,String pk_field, String id,String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String ID_PEMOHON)
	{
		return queryGetData(skrinName,table_name,pk_field, id,ID_PERMOHONANSIMATI, ID_PERBICARAAN, ID_PEMOHON, "");
	}
	public String queryGetData(String skrinName,String table_name,String pk_field, String id,String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String ID_PEMOHON, String ID_JENISBAYARAN)
	{
		String sql = "";
		if(table_name.equals("TBLPPKPEMOHON"))
		{
			//sql = "SELECT PM.* FROM TBLPPKPEMOHON PM, TBLPPKPERMOHONAN P WHERE PM.ID_PEMOHON = P.ID_PEMOHON AND ID_PERMOHONAN = '"+id+"'";
			sql = "SELECT " +
					" (CASE WHEN HIS.ID_OBPERMOHONAN IS NOT NULL THEN HIS.ID_OBPERMOHONAN ELSE MAIN.ID_OBPERMOHONAN_CHECK END) AS ID_OBPERMOHONAN," +
					" (CASE WHEN HIS.ID_OB IS NOT NULL THEN HIS.ID_OB ELSE MAIN.ID_OB_CHECK END) AS ID_OB," +
					" (CASE WHEN HIS.ID_SIMATI IS NOT NULL THEN HIS.ID_SIMATI ELSE MAIN.ID_SIMATI_CHECK END) AS ID_SIMATI," +
					" (CASE WHEN HIS.ID_PERMOHONANSIMATI IS NOT NULL THEN HIS.ID_PERMOHONANSIMATI ELSE MAIN.ID_PERMOHONANSIMATI_CHECK END) AS ID_PERMOHONANSIMATI," +
					" (CASE WHEN HIS.ALAMAT_1 IS NOT NULL THEN HIS.ALAMAT_1 ELSE MAIN.ALAMAT_1 END) AS ALAMAT_1, "+
					" (CASE WHEN HIS.ALAMAT_2 IS NOT NULL THEN HIS.ALAMAT_2 ELSE MAIN.ALAMAT_2 END) AS ALAMAT_2, "+
					" (CASE WHEN HIS.ALAMAT_3 IS NOT NULL THEN HIS.ALAMAT_3 ELSE MAIN.ALAMAT_3 END) AS ALAMAT_3, "+
					" (CASE WHEN HIS.POSKOD IS NOT NULL THEN HIS.POSKOD ELSE MAIN.POSKOD END) AS POSKOD,  "+
					" (CASE WHEN HIS.ID_NEGERI IS NOT NULL THEN HIS.ID_NEGERI ELSE TO_CHAR(MAIN.ID_NEGERI) END) AS ID_NEGERI,  "+
					" (CASE WHEN HIS.ID_BANDAR IS NOT NULL THEN HIS.ID_BANDAR ELSE TO_CHAR(MAIN.ID_BANDAR) END) AS ID_BANDAR,  "+
					" (CASE WHEN HIS.NAMA_PELBAGAINEGARA IS NOT NULL THEN HIS.NAMA_PELBAGAINEGARA ELSE TO_CHAR(MAIN.NAMA_PELBAGAINEGARA) END) AS NAMA_PELBAGAINEGARA,  "+
					" MAIN.* FROM ( SELECT " +
					" TO_CHAR(OB.ID_OBPERMOHONAN) AS ID_OBPERMOHONAN_CHECK," +
					" TO_CHAR(OB.ID_OB) AS ID_OB_CHECK," +
					" TO_CHAR(OB.ID_SIMATI) AS ID_SIMATI_CHECK," +
					" OB.ID_PERMOHONANSIMATI AS ID_PERMOHONANSIMATI_CHECK," +
					" PM.* FROM TBLPPKPEMOHON PM, TBLPPKPERMOHONAN P, TBLPPKOBPERMOHONAN OB " +
					" WHERE PM.ID_PEMOHON = P.ID_PEMOHON AND PM.ID_PEMOHON = OB.ID_PEMOHON(+) AND ID_PERMOHONAN = '"+id+"') MAIN, " +
							" ("+queryHistoryOBCheckPemohon(ID_PERMOHONANSIMATI,ID_PERBICARAAN,"")+") HIS WHERE MAIN.ID_PEMOHON = HIS.ID_PEMOHON(+) ";
		}
		else if(table_name.equals("TBLPPKBAYARAN"))
		{
			sql = "SELECT  TO_CHAR(MN.TARIKH_BAYARAN,'DD/MM/YYYY') AS TARIKH_BAYARAN, MN.* FROM (SELECT B.* FROM TBLPPKBAYARAN B "+
					" WHERE B.ID_JENISBAYARAN = '"+ID_JENISBAYARAN+"' "+
					" AND B.ID_PERMOHONAN = '"+id+"' "+
					" ORDER BY B.TARIKH_BAYARAN,B.ID_BAYARAN ASC) MN WHERE ROWNUM = '1' ";
		}
		else if(table_name.equals("TBLPPKHUBUNGANOBPERMOHONAN"))
		{
			sql = "SELECT * FROM TBLPPKHUBUNGANOBPERMOHONAN WHERE ID_OB = '"+id+"' AND ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' ";
		}
		else if(table_name.equals("TBLPPKBORANGJ"))
		{
			sql = "SELECT TO_CHAR(BJ.TARIKH_BICARA,'DD/MM/YYYY') AS TARIKH_BICARA, TO_CHAR(BJ.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, TO_CHAR(BJ.TARIKH_TERIMA_BORANGJ,'DD/MM/YYYY') AS TARIKH_TERIMA_BORANGJ, "+
					" BJ.* FROM TBLPPKBORANGJ BJ, TBLPPKPERBICARAAN PR WHERE BJ.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERBICARAAN = '"+id+"' ";
		}
		else if(table_name.equals("TBLPPKTUKARPEGAWAI"))
		{
			sql = "SELECT TO_CHAR(TP.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, TO_CHAR(TP.TARIKH_KEPUTUSAN,'DD/MM/YYYY') AS TARIKH_KEPUTUSAN, TP.* "+
				" FROM TBLPPKTUKARPEGAWAI TP, TBLPPKPERBICARAAN PR WHERE TP.ID_PERBICARAAN = PR.ID_PERBICARAAN ";

				if(!pk_field.equals(""))
				{
					sql += " AND TP."+pk_field+" = '"+id+"' ";
				}
				else
				{
					sql += " AND PR.ID_PERBICARAAN = '"+id+"' ";
				}

		}
		else if(table_name.equals("TBLPPKKOLATERALMST"))
		{
			sql = "SELECT TO_CHAR(PR.TARIKH_PERAKUAN,'DD/MM/YYYY') AS TARIKH_PERAKUAN,TO_CHAR(PR.TARIKH_BICARA,'DD/MM/YYYY') AS TARIKH_BICARA,PR.* FROM TBLPPKKOLATERALMST PR WHERE PR.ID_PERBICARAAN = '"+id+"' ";
		}
		else if(table_name.equals("TBLPPKPERINTAH"))
		{
			sql = "SELECT TO_CHAR(PR.TARIKH_PERINTAH,'DD/MM/YYYY') AS TARIKH_PERINTAH,TO_CHAR(PR.DATE_KIV,'DD/MM/YYYY') AS DATE_KIV, " +
					"TRIM(REGEXP_REPLACE(PR.CATATAN, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN, " +
					"TRIM(REGEXP_REPLACE(PR.CATATAN_PERINTAH_BI, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN_PERINTAH_BI, " +
					"TRIM(REGEXP_REPLACE(PR.CATATAN_KEPUTUSAN_PERBICARAAN, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN_KEPUTUSAN_PERBICARAAN, " +
					"TRIM(REGEXP_REPLACE(PR.INTRO_CATATAN, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS INTRO_CATATAN, " +
					"TRIM(REGEXP_REPLACE(PR.CATATAN_DOCKIV, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN_DOCKIV, " +
					"PR.* FROM TBLPPKPERINTAH PR, TBLPPKPERBICARAAN PB WHERE PR.ID_PERBICARAAN = PB.ID_PERBICARAAN AND PR.ID_PERBICARAAN = '"+id+"' ";
		}
		else if(table_name.equals("TBLPPKPERBICARAAN"))
		{
			sql = "SELECT " +
					"TRIM(REGEXP_REPLACE(PR.CATATAN_PERINTAH_TEMP, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN_PERINTAH_TEMP, " +
					"TRIM(REGEXP_REPLACE(PR.CATATAN_KP_TEMP, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN_KP_TEMP, " +
					"TRIM(REGEXP_REPLACE(PR.INTRO_CATATAN_TEMP, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS INTRO_CATATAN_TEMP, " +
					"TRIM(REGEXP_REPLACE(PR.CATATAN_DOCKIVT, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS CATATAN_DOCKIVT, " +
					"PR.* FROM TBLPPKPERBICARAAN PR WHERE PR."+pk_field+" = '"+id+"' ";
		}
		else if(table_name.equals("TBLPPKSIMATI"))
		{
			sql = "SELECT " +
					"TRIM(REGEXP_REPLACE(SM.TEMPAT_MATI, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS TEMPAT_MATI, " +
					"TRIM(REGEXP_REPLACE(SM.SEBAB_MATI, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) AS SEBAB_MATI, " +
					" TO_CHAR(SM.TARIKH_MATI,'DD/MM/YYYY') AS TARIKH_MATI,TO_CHAR(SM.TARIKH_LAHIR,'DD/MM/YYYY') AS TARIKH_LAHIR,TO_CHAR(SM.TARIKH_SURAT_AKUAN,'DD/MM/YYYY') AS TARIKH_SURAT_AKUAN,SM.* FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN P WHERE SM.ID_SIMATI = PSM.ID_SIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_PERMOHONAN = '"+id+"'";
		}
		else if(table_name.equals("TBLPPKOBPERMOHONAN"))
		{
			/*
			sql = "SELECT HUP.id_parentob,TO_CHAR(T.TARIKH_MATI,'DD/MM/YYYY') AS TARIKH_MATI,TO_CHAR(T.TARIKH_LAHIR,'DD/MM/YYYY') AS TARIKH_LAHIR,T.* " +
					" FROM TBLPPKOB T WHERE T.ID_OB = '"+id+"'		";
			*/

			if(skrinName.equals("waris"))
			{
				sql = " SELECT (CASE WHEN OB.ID_PEMOHON = '"+ID_PEMOHON+"' THEN OB.ID_PEMOHON END) AS ID_PEMOHON, OBH.ID_PARENTOB, TO_CHAR(OB.TARIKH_MATI,'DD/MM/YYYY') AS TARIKH_MATI,TO_CHAR(OB.TARIKH_LAHIR,'DD/MM/YYYY') AS TARIKH_LAHIR,OB.* "
						+ " FROM TBLPPKOBPERMOHONAN OB, (SELECT * FROM TBLPPKHUBUNGANOBPERMOHONAN WHERE ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"') OBH "
						+ " WHERE "+pk_field+" = '"+id+"' AND OB.ID_OB = OBH.ID_OB(+) ";
			}
			else
			{
				sql = " SELECT (CASE WHEN OB.ID_PEMOHON = '"+ID_PEMOHON+"' THEN OB.ID_PEMOHON END) AS ID_PEMOHON, TO_CHAR(OB.TARIKH_MATI,'DD/MM/YYYY') AS TARIKH_MATI,TO_CHAR(OB.TARIKH_LAHIR,'DD/MM/YYYY') AS TARIKH_LAHIR,OB.* "
						+ " FROM TBLPPKOBPERMOHONAN OB "
						+ " WHERE "+pk_field+" = '"+id+"'  ";
			}
		}
		else if(table_name.equals("TBLPPKHTAPERMOHONAN"))
		{
			sql = "SELECT TO_CHAR(H.TARIKH_PERJANJIAN,'DD/MM/YYYY') AS TARIKH_PERJANJIAN, H.* FROM TBLPPKHTAPERMOHONAN H WHERE H.ID_HTAPERMOHONAN = '"+id+"' AND H.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' ";
		}
		else if(table_name.equals("TBLPPKHAPERMOHONAN"))
		{
			sql = "SELECT  H.* FROM TBLPPKHAPERMOHONAN H WHERE H.ID_HAPERMOHONAN = '"+id+"' AND H.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' ";
		}
		else if(table_name.equals("TBLPPKPEGUAM"))
		{
			sql = " SELECT PSM.ID_PERMOHONANSIMATI, PSM.ID_SIMATI, PSM.ID_PERMOHONAN, PG.* FROM TBLPPKPEGUAM PG, TBLPPKPEMOHON PM, "+
					" TBLPPKPEGUAMPEMOHON PP, TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI PSM "+
					" WHERE PP.ID_PEMOHON = PM.ID_PEMOHON AND PG.ID_PEGUAM = PP.ID_PEGUAM " +
					" AND PG.ID_PEGUAM = '"+id+"' AND PSM.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' "+
					" AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN ";
		}
		else
		{
			//ni untuk lain-lain standard table
			sql = "SELECT * FROM "+table_name+" WHERE "+pk_field+" = '"+id+"'";
		}
		return sql;
	}

	public String sqllistParentSimati(HttpSession session,String ID_PERMOHONANSIMATI,String ID_PERBICARAAN,String skrinName,String ID_OB)
	{
		String sqllistParentSimati = "";
		sqllistParentSimati += " SELECT * FROM (";
		sqllistParentSimati += "  SELECT OBMAIN.ID_OB,OBMAIN.ID_OBPERMOHONAN, (CASE WHEN HIS.STATUS_HIDUP IS NOT NULL THEN HIS.STATUS_HIDUP ELSE  OBMAIN.STATUS_HIDUP END) AS STATUS_HIDUP," +
				"  (CASE WHEN HIS.NAMA_OB IS NOT NULL THEN HIS.NAMA_OB ELSE  OBMAIN.NAMA_OB END) AS NAMA_OB," +
				" (CASE WHEN HIS.LAPIS IS NOT NULL THEN HIS.LAPIS ELSE  OBMAIN.LAPIS END) AS LAPIS " +
				" FROM (SELECT DISTINCT TO_CHAR(OB.ID_OB) AS ID_OB,TO_CHAR(OB.ID_OBPERMOHONAN) AS ID_OBPERMOHONAN,TO_CHAR(OB.STATUS_HIDUP) AS  STATUS_HIDUP,OB.NAMA_OB,TO_CHAR(OB.LAPIS) AS LAPIS "+
			   " FROM TBLPPKOBPERMOHONAN OB WHERE OB.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"') OBMAIN, ("+queryHistoryOB(skrinName,ID_PERMOHONANSIMATI,ID_PERBICARAAN,"UPDATE")+") HIS " +
			   		" WHERE OBMAIN.ID_OBPERMOHONAN = HIS.ID_OBPERMOHONAN(+)   ";
		sqllistParentSimati += " AND OBMAIN.ID_OBPERMOHONAN NOT IN (SELECT NVL(ID_OBPERMOHONAN,'') FROM ("+queryHistoryOB(skrinName,ID_PERMOHONANSIMATI,ID_PERBICARAAN,"INSERTDELETE")+")) ";
		sqllistParentSimati += " UNION ALL ";
		sqllistParentSimati += " SELECT HIS.ID_OB,HIS.ID_OBPERMOHONAN,HIS.STATUS_HIDUP,HIS.NAMA_OB,HIS.LAPIS " +
				" FROM ("+queryHistoryOB(skrinName,ID_PERMOHONANSIMATI,ID_PERBICARAAN,"INSERT")+") HIS  ";
		sqllistParentSimati += ") WHERE ID_OB IS NOT NULL AND STATUS_HIDUP = '1' ";
		if(!ID_OB.equals(""))
		{
			sqllistParentSimati += " AND ID_OB = '"+ID_OB+"' ";
		}
		sqllistParentSimati += " ORDER BY NAMA_OB ASC ";
		return sqllistParentSimati;
	}

	public List listParentSimati(HttpSession session,String ID_PERMOHONANSIMATI,String ID_PERBICARAAN,String skrinName,String ID_OB, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listParentSimati = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = sqllistParentSimati(session,ID_PERMOHONANSIMATI,ID_PERBICARAAN,skrinName,ID_OB);
			myLogger.info(" BICARA INTERAKTIF : SQL listParentSimati :"+ sql);
			rs = stmt.executeQuery(sql);

			listParentSimati = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB").toUpperCase());
				h.put("ID_OBPERMOHONAN",rs == null ? "" :rs.getString("ID_OBPERMOHONAN") == null ? "" : rs.getString("ID_OBPERMOHONAN").toUpperCase());
				h.put("NAMA_OB",rs == null ? "" :rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB").toUpperCase());
				h.put("LAPIS",rs == null ? "" :rs.getString("LAPIS") == null ? "" : rs.getString("LAPIS").toUpperCase());
				listParentSimati.add(h);
			}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return listParentSimati;
	}


	//method ni bahaya kalo perintah duplicate
	public void generateListHartaSkrinPerintahByID_FAIL(HttpSession session, String usid, String ID_FAIL, Db db) throws Exception {

		Db db1 = null;
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}



			Map mainFAIL =  perintahByFAIL(session, ID_FAIL, "", db1);
			String ID_PERINTAH = "";
			if(mainFAIL!=null)
			{
				ID_PERINTAH = (String)mainFAIL.get("ID_PERINTAH");
			}

			Map psmFail = permohonanSimatiByFAIL(session, ID_FAIL,db1);
			String ID_PERMOHONANSIMATI = "";
			if(psmFail!=null)
			{
				ID_PERMOHONANSIMATI = (String)psmFail.get("ID_PERMOHONANSIMATI");
			}

			generateListHartaSkrinPembahagian(session, usid, ID_PERINTAH, ID_PERMOHONANSIMATI, db1);

		}
		finally {
			if (db == null)
			{
				db1.close();
			}
		}

	}


	public void deleteUserActivityEvent(String idPerbicaraan, Db db) throws Exception {


		String sql = "";
		Db db1 = null;
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}

			Statement stmt = db1.getStatement();

			sql = "DELETE FROM TBLACTIVITYEVENT WHERE ID_PERBICARAAN = '" + idPerbicaraan + "'";
			myLogger.info(" SQL DELETE FROM TBLACTIVITYEVENT  "+sql);
			stmt.execute(sql);

		}
		finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	public void saveActivityEvent(String userLoginPegawai, Long idPerbicaraan, String description, String locationRemark, String tarikhMula, String masa_bicara,
			String jenisWaktu) throws Exception {
		System.out.println("sini lepas xxxxxxxxxxxxxxxx==="+idPerbicaraan);


		deleteUserActivityEvent(idPerbicaraan+"",null);

		DbPersistence db = new DbPersistence();
		System.out.println("idPerbicaraan==="+idPerbicaraan);
		System.out.println("userLoginPegawai==="+userLoginPegawai);

		System.out.println("----------------------done delete------------------");
		//find UserActivityEvent
		UserActivityEvent userActivityEvent = (UserActivityEvent) db.get("select u from UserActivityEvent u where u.userLogin = '" + userLoginPegawai + "'");

		//System.out.println("select u from UserActivityEvent u where u.userLogin = '" + userLoginPegawai + "'");

		if ( userActivityEvent == null ) {
			db.begin();
			userActivityEvent = new UserActivityEvent();
			userActivityEvent.setUserLogin(userLoginPegawai);
			db.persist(userActivityEvent);
			db.commit();
		}
		//System.out.println("----------------------1------------------");
		String displayColor = "#FFCCCC";

		String jamBicara  = "";
		String minitBicara = "";
		String startTime = "";
		String endTime = "";
		//System.out.println("----------------------2------------------");
		if (!"".equals(masa_bicara)){

			jamBicara = masa_bicara.substring(0, 2);
			minitBicara = masa_bicara.substring(2, 4);
			System.out.println("----------------------3------------------");
			if (Integer.valueOf(minitBicara) < 15){
				minitBicara = "00";
			} else if (Integer.valueOf(minitBicara) >= 15 && Integer.valueOf(minitBicara) < 30){
				minitBicara = "15";
			} else if (Integer.valueOf(minitBicara) >= 30 && Integer.valueOf(minitBicara) < 45){
				minitBicara = "30";
			} else if (Integer.valueOf(minitBicara) >= 45 && Integer.valueOf(minitBicara) < 60){
				minitBicara = "45";
			}
			//System.out.println("----------------------4------------------");
			int jamTamat = Integer.valueOf(jamBicara);
			jamTamat = jamTamat + 1;
			//System.out.println("----------------------5------------------");
			int minitTamat = Integer.valueOf(minitBicara);
			//System.out.println("----------------------6------------------");
			startTime = jamBicara + ":" + minitBicara;
			if ("1".equals(jenisWaktu)){
				startTime = startTime + " AM";

				if (jamTamat < 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}
					endTime = endTime + ":" + minitBicara + " AM";
				} else if (jamTamat == 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}
					endTime = endTime + ":" + minitBicara + " PM";
				} else {
					jamTamat = jamTamat - 12;
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}
					endTime = endTime + ":" + minitBicara + " PM";
				}

			} else {
				startTime = startTime + " PM";

				if (jamTamat < 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}
					endTime = endTime + ":" + minitBicara + " PM";
				} else if (jamTamat < 12) {
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}
					endTime = endTime + ":" + minitBicara + " PM";
				} else {
					jamTamat = jamTamat - 12;
					endTime = "" + jamTamat;
					while (endTime.length() <= 2){
						endTime = "0" + endTime;
					}
					endTime = endTime + ":" + minitBicara + " PM";
				}
			}
		}
		//System.out.println("----------------------7------------------");
		Date startDateTime = parseDateTime(tarikhMula + " " + startTime);
		//System.out.println("----------------------8------------------");
		String eventDateEnd_ = tarikhMula;
		Date endDateTime = parseDateTime(eventDateEnd_ + " " + endTime);
		//System.out.println("----------------------9------------------");
		db.begin();
		ActivityEvent activityEvent = (ActivityEvent) db.get("select a from ActivityEvent a where a.idPerbicaraan = '" + Long.valueOf(idPerbicaraan) + "'");
		//System.out.println("----------------------10------------------");
		if ( activityEvent == null ) {
			activityEvent = new ActivityEvent();
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark("");
			activityEvent.setDisplayColor(displayColor);
			activityEvent.setIdPerbicaraan(idPerbicaraan);
			activityEvent.setUserActivityEvent(userActivityEvent);
			activityEvent.setCreateDate(new Date());
			db.persist(activityEvent);
			if ( userActivityEvent.getEvents() == null ) userActivityEvent.setEvents(new ArrayList<ActivityEvent>());
			userActivityEvent.getEvents().add(activityEvent);

		} else {
			activityEvent.setDescription(description);
			activityEvent.setEventDate(parseDate(tarikhMula));
			activityEvent.setStartDateTime(startDateTime);
			activityEvent.setEndDateTime(endDateTime);
			activityEvent.setLocationRemark(locationRemark);
			activityEvent.setRemark("");
			activityEvent.setDisplayColor(displayColor);
			activityEvent.setIdPerbicaraan(idPerbicaraan);
		}

		try {
			db.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public Date parseDate(String dateTxt) {
		if (dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy").parse(dateTxt);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}


	public Date parseDateTime(String dateTxt) {
		if (dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy hh:mm a")
						.parse(dateTxt);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}


	//method ni bahaya kalo perintah duplicate
		public void generateListHartaSkrinPerintahBySimpanPilihanHarta(HttpSession session, String usid, String ID_PERMOHONANSIMATI, Db db) throws Exception {

			Db db1 = null;
			try {
				if(db == null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}



				Map mainFAIL =  perintahByFAIL(session, "", ID_PERMOHONANSIMATI, db1);
				String ID_PERINTAH = "";
				if(mainFAIL!=null)
				{
					ID_PERINTAH = (String)mainFAIL.get("ID_PERINTAH");
				}


				generateListHartaSkrinPembahagian(session, usid, ID_PERINTAH, ID_PERMOHONANSIMATI, db1);

			}
			finally {
				if (db == null)
				{
					db1.close();
				}
			}

		}



	public void generateListHartaSkrinPembahagian_BicaraInteraktif(HttpSession session,  String ID_PERMOHONANSIMATI,  Db db, String ID_PERINTAH) throws Exception {
		if(ID_PERINTAH.equals(""))
		{
			//method ni bahaya kalo perintah duplicate
		}
		generateListHartaSkrinPembahagian(session, "", ID_PERINTAH, ID_PERMOHONANSIMATI, db);
	}

	public void generateListHartaSkrinPembahagian_KeputusanPerbicaraan(HttpSession session, String usid, String ID_SIMATI, String ID_PERINTAH,  Db db) throws Exception {


		Db db1 = null;
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}

			Map psmFail = permohonanSimatiBySIMATI_PERINTAH(null, ID_SIMATI, ID_PERINTAH, db);
			String ID_PERMOHONANSIMATI = "";
			if(psmFail!=null)
			{
				ID_PERMOHONANSIMATI = (String)psmFail.get("ID_PERMOHONANSIMATI");
			}

			generateListHartaSkrinPembahagian(null, usid, ID_PERINTAH, ID_PERMOHONANSIMATI, db1);

		}
		finally {
			if (db == null)
			{
				db1.close();
			}
		}



	}


	public void generateListHartaSkrinPembahagian(HttpSession session, String usid, String ID_PERINTAH, String ID_PERMOHONANSIMATI, Db db) throws Exception {
		String idHTAMSTNew = "";
		String idPerintahHTAOBMST = "";
		List listPilihanHTA = null;


		String idHAMSTNew = "";
		String idPerintahHAOBMST = "";
		List listPilihanHA = null;


		System.out.println("ID_PERINTAH ID PERINTAH :: "+ID_PERINTAH);
		Db db1 = null;
		//try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}




			listPilihanHTA = listPilihanHTA(session,ID_PERMOHONANSIMATI,db1);
			String pilihanHTA = "";
			for (int i = 0; i < listPilihanHTA.size(); i++){
				Map m = (Map) listPilihanHTA.get(i);
				String ID_HTA = (String)m.get("ID_HTA");
				if(i == 0)
				{
					pilihanHTA += ID_HTA+"";
				}
				else
				{
					pilihanHTA += ","+ID_HTA+"";
				}

				idPerintahHTAOBMST = getIdPerintahHTAOBMST(ID_HTA,db1);

				if (idPerintahHTAOBMST != null && !"".equals(idPerintahHTAOBMST)){
					myLogger.info("idPerintahHTAOBMST current dari HTA :"+idPerintahHTAOBMST);
					Map mapPerintahHTAOBMST =  listPerintahHTAOBMST(idPerintahHTAOBMST, db1);
					String getIdPerintahHTAOBMST_CURRENT = "";
					if(!ID_PERINTAH.equals(""))
					{
						getIdPerintahHTAOBMST_CURRENT = getIdPerintahHTAOBMST_CURRENT(ID_HTA,ID_PERINTAH,"L", db1);
					}
					myLogger.info("DALAM CURRENT PERINTAH DAH DAFTAR BELUM 'L' >>> getIdPerintahHTAOBMST_CURRENT : "+getIdPerintahHTAOBMST_CURRENT);
					//CHECK DLU MAKLUMAT PERINTAH SEBELUM DAN TERKINI
					if(mapPerintahHTAOBMST!=null && getIdPerintahHTAOBMST_CURRENT.equals("") && !ID_PERINTAH.equals(""))
					{
						String ID_JENISPERINTAH = (String)mapPerintahHTAOBMST.get("ID_JENISPERINTAH");

						String ID_HTAOBMST_BARU = saveTblPPKPerintahHTAOBMST(session,usid,getIdPerintahHTAOBMST_CURRENT,ID_PERINTAH,mapPerintahHTAOBMST,db1);
						if(!ID_HTAOBMST_BARU.equals(""))
						{
							List listHTADTL = listHTADTL(session,idPerintahHTAOBMST, ID_HTAOBMST_BARU, ID_JENISPERINTAH, db1);
							for (int e = 0; e < listHTADTL.size(); e++){
								Map mDTL = (Map) listHTADTL.get(e);
								String ID_PERINTAHHTAOBDTL = (String)mDTL.get("ID_PERINTAHHTAOBDTL");
								Map mapPerintahHTAOBDTL =  mapPerintahHTAOBDTL(ID_PERINTAHHTAOBDTL, db1);
								saveTblPPKPerintahHTAOBDTL(session, usid, ID_HTAOBMST_BARU, mapPerintahHTAOBDTL,db1);
							}
						}
					}
				}
			}

			if(!ID_PERINTAH.equals(""))
			{
				deleteMSTDTL_HTA(session,ID_PERINTAH,pilihanHTA,db1);
			}



			listPilihanHA = listPilihanHA(session,ID_PERMOHONANSIMATI,db1);
			String pilihanHA = "";
			for (int i = 0; i < listPilihanHA.size(); i++){
				Map m = (Map) listPilihanHA.get(i);
				String ID_HA = (String)m.get("ID_HA");

				if(i == 0)
				{
					pilihanHA += ID_HA+"";
				}
				else
				{
					pilihanHA += ","+ID_HA+"";
				}

				idPerintahHAOBMST = getIdPerintahHAOBMST(ID_HA,db1);

				if (idPerintahHAOBMST != null && !"".equals(idPerintahHAOBMST)){
					myLogger.info("idPerintahHAOBMST current dari HA :"+idPerintahHAOBMST);
					Map mapPerintahHAOBMST =  listPerintahHAOBMST(idPerintahHAOBMST, db1);
					String getIdPerintahHAOBMST_CURRENT = "";
					if(!ID_PERINTAH.equals(""))
					{
						getIdPerintahHAOBMST_CURRENT = getIdPerintahHAOBMST_CURRENT(ID_HA,ID_PERINTAH,"L", db1);
					}
					myLogger.info("DALAM CURRENT PERINTAH DAH DAFTAR BELUM 'L' >>> getIdPerintahHAOBMST_CURRENT : "+getIdPerintahHAOBMST_CURRENT);
					//CHECK DLU MAKLUMAT PERINTAH SEBELUM DAN TERKINI
					if(mapPerintahHAOBMST!=null && getIdPerintahHAOBMST_CURRENT.equals("") && !ID_PERINTAH.equals(""))
					{
						String ID_JENISPERINTAH = (String)mapPerintahHAOBMST.get("ID_JENISPERINTAH");
						String ID_HAOBMST_BARU = saveTblPPKPerintahHAOBMST(session,usid,getIdPerintahHAOBMST_CURRENT,ID_PERINTAH,mapPerintahHAOBMST,db1);
						if(!ID_HAOBMST_BARU.equals(""))
						{
							List listHADTL = listHADTL(session,idPerintahHAOBMST, ID_HAOBMST_BARU, ID_JENISPERINTAH, db1);
							for (int e = 0; e < listHADTL.size(); e++){
								Map mDTL = (Map) listHADTL.get(e);
								String ID_PERINTAHHAOBDTL = (String)mDTL.get("ID_PERINTAHHAOBDTL");
								Map mapPerintahHAOBDTL =  mapPerintahHAOBDTL(ID_PERINTAHHAOBDTL, db1);
								saveTblPPKPerintahHAOBDTL(session, usid, ID_HAOBMST_BARU, mapPerintahHAOBDTL,db1);
							}
						}
					}
				}
			}

			if(!ID_PERINTAH.equals(""))
			{
				deleteMSTDTL_HA(session,ID_PERINTAH,pilihanHA,db1);
			}

		/*
		}
		finally {
			if (db == null)
			{
				db1.close();
			}
		}
		*/
	}

	public Hashtable current_status(String id_fail, Db db) throws Exception {


			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String sql = " SELECT ID_STATUS FROM TBLPPKPERMOHONAN WHERE ID_FAIL = '"+id_fail+"' ";
			System.out.println(" SQL GET ID STATUS :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}
			}
			return h;

	}

	public void kemaskiniSubUrusanStatusFail(HttpSession session,
			String id_permohonan, String user_id, String id_status,
			String id_suburusanstatus, String id_fail, Db db) throws Exception {
		Vector list_substatus = null;
		Hashtable hash_status = null;

		String check_sub = "";
		String current_status = "";
		String jenis_patah_balik = "";
		String status_patah_balik = "no";
		String update_audit = "";

		current_status = current_status(id_fail, db).get("ID_STATUS") + "";

		/*
		 * no itu ada step... 1--PENDAFTARAN SELECT * FROM TBLRUJSTATUS WHERE
		 * ID_STATUS IN ('8','9','170','169') 2--KEPUTUSANPERMOHONAN SELECT *
		 * FROM TBLRUJSTATUS WHERE ID_STATUS IN
		 * ('50','53','151','152','14','70','56') 3--NOTIS PERBICARAAN SELECT *
		 * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('18','44','175','173','177')
		 * 3--KEPUTUSANPERBICARAAN(BATAL PERBICARAAN)SELECT * FROM TBLRUJSTATUS
		 * WHERE ID_STATUS IN ('47') 3--KEPUTUSANPERBICARAAN(TANGGUH
		 * KOLATERAL)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('172')
		 * 3--KEPUTUSANPERBICARAAN(TANGGUH ROTS)SELECT * FROM TBLRUJSTATUS WHERE
		 * ID_STATUS IN ('176') 3--KEPUTUSANPERBICARAAN(TANGGUH MT)SELECT * FROM
		 * TBLRUJSTATUS WHERE ID_STATUS IN ('174')
		 * 3--KEPUTUSANPERBICARAAN(TANGGUH PERBICARAAN)SELECT * FROM
		 * TBLRUJSTATUS WHERE ID_STATUS IN ('44')
		 * 4--KEPUTUSANPERBICARAAN(SELESAI PERBICARAAN)SELECT * FROM
		 * TBLRUJSTATUS WHERE ID_STATUS IN ('41') 5--PERINTAH (PERMOHONAN
		 * SELASAI)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('25')
		 * 6--PERINTAH (SELASAI)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN
		 * ('21') 7--RAYUAN (PERMOHONAN RAYUAN)SELECT * FROM TBLRUJSTATUS WHERE
		 * ID_STATUS IN ('64','163','166','167','180') 8--RAYUAN (KEPUTUSAN
		 * RAYUAN)SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('164','165')
		 */

		Integer current_step = 0;
		Integer new_step = 0;

		// pecahkan id_status mengikut turutan urusan
		// STEP 1 PERNDAFTARAN
		HashSet<String> location_id_status_PENDAFTRAN = new HashSet<String>();
		location_id_status_PENDAFTRAN.add("8");
		location_id_status_PENDAFTRAN.add("9");
		location_id_status_PENDAFTRAN.add("170");
		location_id_status_PENDAFTRAN.add("169");

		if (location_id_status_PENDAFTRAN.contains(current_status) == true) {
			current_step = 1;
		}
		if (location_id_status_PENDAFTRAN.contains(id_status) == true) {
			new_step = 1;
		}
		location_id_status_PENDAFTRAN.clear();

		// STEP 2 KEPUTUSAN PERMOHONAN
		HashSet<String> location_id_status_KEPUTUSANPERMOHONAN = new HashSet<String>();
		location_id_status_KEPUTUSANPERMOHONAN.add("50");
		location_id_status_KEPUTUSANPERMOHONAN.add("53");
		location_id_status_KEPUTUSANPERMOHONAN.add("151");
		location_id_status_KEPUTUSANPERMOHONAN.add("152");
		location_id_status_KEPUTUSANPERMOHONAN.add("14");
		location_id_status_KEPUTUSANPERMOHONAN.add("70");
		if (location_id_status_KEPUTUSANPERMOHONAN.contains(current_status) == true) {
			current_step = 2;
		}
		if (location_id_status_KEPUTUSANPERMOHONAN.contains(id_status) == true) {
			new_step = 2;
		}
		location_id_status_KEPUTUSANPERMOHONAN.clear();

		// STEP 3 NOTIS PERBICARAAN + KEPUTUSAN PERBICARAAN
		HashSet<String> location_id_status_NOTISPERBICARAAN = new HashSet<String>();
		location_id_status_NOTISPERBICARAAN.add("18");
		location_id_status_NOTISPERBICARAAN.add("44");
		location_id_status_NOTISPERBICARAAN.add("175");
		location_id_status_NOTISPERBICARAAN.add("173");
		location_id_status_NOTISPERBICARAAN.add("177");
		location_id_status_NOTISPERBICARAAN.add("47");
		location_id_status_NOTISPERBICARAAN.add("172");
		location_id_status_NOTISPERBICARAAN.add("176");
		location_id_status_NOTISPERBICARAAN.add("174");
		location_id_status_NOTISPERBICARAAN.add("44");
		if (location_id_status_NOTISPERBICARAAN.contains(current_status) == true) {
			current_step = 3;
		}
		if (location_id_status_NOTISPERBICARAAN.contains(id_status) == true) {
			new_step = 3;
		}
		location_id_status_NOTISPERBICARAAN.clear();

		// STEP 4 KEPUTUSAN PERBICARAAN (SELESAI)
		HashSet<String> location_id_status_KEPUTUSANPERBICARAAN = new HashSet<String>();
		location_id_status_KEPUTUSANPERBICARAAN.add("41");
		if (location_id_status_KEPUTUSANPERBICARAAN.contains(current_status) == true) {
			current_step = 4;
		}
		if (location_id_status_KEPUTUSANPERBICARAAN.contains(id_status) == true) {
			new_step = 4;
		}
		location_id_status_KEPUTUSANPERBICARAAN.clear();

		// STEP 5 PERINTAH (PERMOHONAN SELESAI)
		HashSet<String> location_id_status_PERINTAHPERMOHONAN = new HashSet<String>();
		location_id_status_PERINTAHPERMOHONAN.add("25");
		if (location_id_status_PERINTAHPERMOHONAN.contains(current_status) == true) {
			current_step = 5;
		}
		if (location_id_status_PERINTAHPERMOHONAN.contains(id_status) == true) {
			new_step = 5;
		}
		location_id_status_PERINTAHPERMOHONAN.clear();

		// STEP 6 PERINTAH (PERINTAH SELESAI)
		HashSet<String> location_id_status_PERINTAHSELESAI = new HashSet<String>();
		location_id_status_PERINTAHSELESAI.add("21");
		if (location_id_status_PERINTAHSELESAI.contains(current_status) == true) {
			current_step = 6;
		}
		if (location_id_status_PERINTAHSELESAI.contains(id_status) == true) {
			new_step = 6;
		}
		location_id_status_PERINTAHSELESAI.clear();

		// STEP 7 RAYUAN PERMOHONAN
		HashSet<String> location_id_status_RAYUANPERMOHONAN = new HashSet<String>();
		location_id_status_RAYUANPERMOHONAN.add("64");
		location_id_status_RAYUANPERMOHONAN.add("163");
		if (location_id_status_RAYUANPERMOHONAN.contains(current_status) == true) {
			current_step = 7;
		}
		if (location_id_status_RAYUANPERMOHONAN.contains(id_status) == true) {
			new_step = 7;
		}
		location_id_status_RAYUANPERMOHONAN.clear();

		HashSet<String> location_id_status_RAYUANKEPUTUSAN = new HashSet<String>();
		location_id_status_RAYUANPERMOHONAN.add("166");
		location_id_status_RAYUANPERMOHONAN.add("167");
		location_id_status_RAYUANPERMOHONAN.add("180");
		location_id_status_RAYUANPERMOHONAN.add("164");
		location_id_status_RAYUANPERMOHONAN.add("165");
		if (location_id_status_RAYUANKEPUTUSAN.contains(current_status) == true) {
			current_step = 8;
		}
		if (location_id_status_RAYUANKEPUTUSAN.contains(id_status) == true) {
			new_step = 8;
		}
		location_id_status_RAYUANKEPUTUSAN.clear();

		// STEP 9 BKE PERMOHONAN
		HashSet<String> location_id_status_BKEPERMOHONAN = new HashSet<String>();
		location_id_status_BKEPERMOHONAN.add("61");
		location_id_status_BKEPERMOHONAN.add("154");
		location_id_status_BKEPERMOHONAN.add("155");
		if (location_id_status_BKEPERMOHONAN.contains(current_status) == true) {
			current_step = 9;
		}
		if (location_id_status_BKEPERMOHONAN.contains(id_status) == true) {
			new_step = 9;
		}
		location_id_status_BKEPERMOHONAN.clear();

		// STEP 10 BKE PINDAH
		HashSet<String> location_id_status_BKEPINDAH = new HashSet<String>();
		location_id_status_BKEPINDAH.add("56");
		if (location_id_status_BKEPINDAH.contains(current_status) == true) {
			current_step = 10;
		}
		if (location_id_status_BKEPINDAH.contains(id_status) == true) {
			new_step = 10;
		}

		location_id_status_BKEPINDAH.clear();

		// STEP 11 HAPUS
		HashSet<String> location_id_status_HAPUS = new HashSet<String>();
		location_id_status_HAPUS.add("999");
		if (location_id_status_HAPUS.contains(current_status) == true) {
			current_step = 11;
		}
		if (location_id_status_HAPUS.contains(id_status) == true) {
			new_step = 11;
		}
		System.out.println("location_id_status_HAPUS(current_status) "
				+ location_id_status_HAPUS.contains(current_status));
		location_id_status_HAPUS.clear();

		String update_status = "no";
		if (new_step > current_step || new_step == current_step) {
			update_status = "yes";
		} else {
			if (current_status.equals("166") && id_status.equals("18")) {
				update_status = "special";
			}
		}

		if (update_status.equals("yes") || update_status.equals("special")) {

			HashSet<String> location_id_status_kp = new HashSet<String>();
			location_id_status_kp.add("151");
			location_id_status_kp.add("152");
			location_id_status_kp.add("53");
			location_id_status_kp.add("50");
			location_id_status_kp.add("70");
			location_id_status_kp.add("56");
			if (location_id_status_kp.contains(id_status) == true) {
				jenis_patah_balik = "kp";
			}

			HashSet<String> location_id_status_np = new HashSet<String>();
			location_id_status_np.add("177");
			location_id_status_np.add("176");
			location_id_status_np.add("175");
			location_id_status_np.add("174");
			location_id_status_np.add("173");
			location_id_status_np.add("172");
			location_id_status_np.add("18");
			location_id_status_np.add("44");
			location_id_status_np.add("47");
			if (location_id_status_np.contains(id_status) == true) {
				jenis_patah_balik = "np";
			}

			HashSet<String> location_id_status_ryn = new HashSet<String>();
			location_id_status_ryn.add("166");
			location_id_status_ryn.add("167");
			location_id_status_ryn.add("180");
			location_id_status_ryn.add("164");
			location_id_status_ryn.add("165");
			if (location_id_status_ryn.contains(id_status) == true) {
				jenis_patah_balik = "ryn";
			}

			String jenis_update_add = "";

			if (jenis_patah_balik.equals("kp")) {
				list_substatus = list_substatus(id_fail, db);
				if (list_substatus.size() > 0) {
					for (int i = 0; i < list_substatus.size(); i++) {
						hash_status = (Hashtable) list_substatus.get(i);

						if (location_id_status_kp.contains(hash_status
								.get("ID_STATUS"))
								&& hash_status.get("AKTIF").equals("1")) {
							jenis_update_add = "set1";

						}

					}
				}

			} else if (jenis_patah_balik.equals("np")) {
				list_substatus = list_substatus(id_fail, db);
				if (list_substatus.size() > 0) {
					for (int i = 0; i < list_substatus.size(); i++) {
						hash_status = (Hashtable) list_substatus.get(i);

						if (location_id_status_np.contains(hash_status
								.get("ID_STATUS"))
								&& hash_status.get("AKTIF").equals("1")) {
							jenis_update_add = "set2";

						}

					}
				}

			} else if (jenis_patah_balik.equals("ryn")) {
				list_substatus = list_substatus(id_fail, db);
				if (list_substatus.size() > 0) {
					for (int i = 0; i < list_substatus.size(); i++) {
						hash_status = (Hashtable) list_substatus.get(i);

						if (location_id_status_ryn.contains(hash_status
								.get("ID_STATUS"))
								&& hash_status.get("AKTIF").equals("1")) {
							jenis_update_add = "set3";

						}

					}
				}

			}

			location_id_status_kp = null;
			location_id_status_np = null;
			location_id_status_ryn = null;

			list_substatus = list_substatus(id_fail, db);
			if (list_substatus.size() > 0) {
				for (int i = 0; i < list_substatus.size(); i++) {
					hash_status = (Hashtable) list_substatus.get(i);
					if (hash_status.get("ID_SUBURUSANSTATUS").equals(
							id_suburusanstatus)) {
						System.out.println("SFF ID_SUBURUSAN INI TELAH WUJUD :"
								+ id_suburusanstatus);
						check_sub = "wujud";
					}
					String display_sub = "";
					display_sub = "SSF ID_SUBURUSANSTATUSFAIL ::"
							+ hash_status.get("ID_SUBURUSANSTATUSFAIL");
					display_sub += ",SSF ID_PERMOHONAN ::"
							+ hash_status.get("ID_PERMOHONAN");
					display_sub += ",SSF ID_FAIL ::"
							+ hash_status.get("ID_FAIL");
					display_sub += ",SSF ID_SUBURUSANSTATUS ::"
							+ hash_status.get("ID_SUBURUSANSTATUS");
					display_sub += ",SSF AKTIF ::" + hash_status.get("AKTIF");

				}
			}

			if (check_sub.equals("wujud")) {
				if (jenis_update_add.equals("set1")
						|| jenis_update_add.equals("set3")) {

					SST_update_aktif_permohonan(session, id_fail,
							id_permohonan, id_status, id_suburusanstatus,
							user_id, db);

					update_audit = "yes";

				} else if (jenis_update_add.equals("set2")) {
					SST_insert_aktif_permohonan(session, id_fail,
							id_permohonan, id_status, id_suburusanstatus,
							user_id, db);
					update_audit = "yes";

				} else {

					if (update_status.equals("special")) {
						SST_insert_aktif_permohonan(session, id_fail,
								id_permohonan, id_status, id_suburusanstatus,
								user_id, db);

						update_audit = "yes";
					} else {
						SST_update_kosong(session, id_fail, id_permohonan,
								id_status, id_suburusanstatus, user_id, db);

					}
				}
			} else {
				SST_insert_aktif_permohonan(session, id_fail, id_permohonan,
						id_status, id_suburusanstatus, user_id, db);
				update_audit = "yes";

			}

			if (update_audit.equals("yes")) {
				AuditTrail at = new AuditTrail();
				at.logActivity(id_status, "2", null, session, "INS", "FAIL ["
						+ getNoFail(id_fail, db) + "] DI DALAM PROSES "
						+ getNamaStatus(id_status, db));
			}
		}

	}


	public Vector list_substatus(String id_fail, Db db) throws Exception {
		Vector list_substatus = new Vector();
		list_substatus.clear();
		String sql = "";

		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		sql = " SELECT S.ID_STATUS,S.KETERANGAN AS NAMA_STATUS,SSF.ID_SUBURUSANSTATUSFAIL,SSF.ID_PERMOHONAN,SSF.ID_SUBURUSANSTATUS,SSF.AKTIF,SSF.ID_FAIL,SSF.TARIKH_KEMASKINI "
				+ " FROM TBLRUJSUBURUSANSTATUSFAIL SSF,TBLPFDFAIL  F,TBLRUJSUBURUSANSTATUS SS,TBLRUJSTATUS S "
				+ " WHERE F.ID_FAIL = '"
				+ id_fail
				+ "' AND SSF.ID_FAIL = F.ID_FAIL "
				+ " AND SSF.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS AND SS.ID_STATUS = S.ID_STATUS ORDER BY SSF.ID_SUBURUSANSTATUSFAIL ";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Hashtable h = new Hashtable();

			if (rs.getString("ID_STATUS") == null) {
				h.put("ID_STATUS", "");
			} else {
				h.put("ID_STATUS", rs.getString("ID_STATUS"));
			}

			if (rs.getString("ID_SUBURUSANSTATUSFAIL") == null) {
				h.put("ID_SUBURUSANSTATUSFAIL", "");
			} else {
				h.put("ID_SUBURUSANSTATUSFAIL",
						rs.getString("ID_SUBURUSANSTATUSFAIL"));
			}

			if (rs.getString("ID_PERMOHONAN") == null) {
				h.put("ID_PERMOHONAN", "");
			} else {
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));
			}

			if (rs.getString("ID_SUBURUSANSTATUS") == null) {
				h.put("ID_SUBURUSANSTATUS", "");
			} else {
				h.put("ID_SUBURUSANSTATUS", rs.getString("ID_SUBURUSANSTATUS"));
			}

			if (rs.getString("AKTIF") == null) {
				h.put("AKTIF", "");
			} else {
				h.put("AKTIF", rs.getString("AKTIF"));
			}

			if (rs.getString("ID_FAIL") == null) {
				h.put("ID_FAIL", "");
			} else {
				h.put("ID_FAIL", rs.getString("ID_FAIL"));
			}

			list_substatus.addElement(h);
		}
		return list_substatus;

	}

	public String getNoFail(String id_fail, Db db) throws Exception {

		String sql = "";
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		String no_fail_temp = "";
		sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + id_fail
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			no_fail_temp = rs.getString("NO_FAIL");
		}
		return no_fail_temp;

	}

	public String getNamaStatus(String id_status, Db db) throws Exception {

		String sql = "";
		String nama_status_temp = "";

		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		sql = "SELECT KETERANGAN FROM TBLRUJSTATUS WHERE ID_STATUS = '"
				+ id_status + "'";
		ResultSet rs1 = stmt.executeQuery(sql);
		while (rs1.next()) {
			nama_status_temp = rs1.getString("KETERANGAN").toUpperCase();
		}
		return nama_status_temp;

	}


	public void SST_update_aktif_permohonan(HttpSession session,
			String id_fail, String id_permohonan, String id_status,
			String id_suburusanstatus, String user_id, Db db) throws Exception {
		String sql = "";
		String sql1 = "";
		String sql2 = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		r.clear();
		r.update("ID_FAIL", id_fail);
		r.update("AKTIF", 1);
		r.add("AKTIF", 0);
		r.add("ID_KEMASKINI", user_id);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql = r.getSQLUpdate("tblrujsuburusanstatusfail");
		stmt.executeUpdate(sql);

		r.clear();
		r.update("id_fail", id_fail);
		r.update("id_suburusanstatus", id_suburusanstatus);
		r.add("AKTIF", 1);
		r.add("ID_KEMASKINI", user_id);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql1 = r.getSQLUpdate("tblrujsuburusanstatusfail");
		stmt.executeUpdate(sql1);

		r.clear();
		r.update("ID_PERMOHONAN", id_permohonan);
		r.add("ID_STATUS", id_status);
		r.add("ID_KEMASKINI", user_id);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql2 = r.getSQLUpdate("tblppkpermohonan");
		stmt.executeUpdate(sql2);

	}

	public void SST_insert_aktif_permohonan(HttpSession session,
			String id_fail, String id_permohonan, String id_status,
			String id_suburusanstatus, String user_id, Db db) throws Exception {
		String sql = "";
		String sql1 = "";
		String sql2 = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		r.clear();
		r.update("ID_FAIL", id_fail);
		r.update("AKTIF", 1);
		r.add("AKTIF", 0);
		r.add("ID_KEMASKINI", user_id);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql = r.getSQLUpdate("tblrujsuburusanstatusfail");
		System.out.println("CHECK ADD NEW 1:" + sql);
		stmt.executeUpdate(sql);

		r.clear();
		r.add("ID_SUBURUSANSTATUSFAIL",
				DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
		r.add("ID_PERMOHONAN", id_permohonan);
		r.add("ID_SUBURUSANSTATUS", id_suburusanstatus);
		r.add("AKTIF", 1);
		r.add("ID_MASUK", user_id);
		r.add("TARIKH_MASUK", r.unquote("sysdate"));
		r.add("ID_KEMASKINI", user_id);
		r.add("id_Fail", id_fail);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql1 = r.getSQLInsert("tblrujsuburusanstatusfail");
		stmt.executeUpdate(sql1);

		r.clear();
		r.update("ID_PERMOHONAN", id_permohonan);
		r.add("ID_STATUS", id_status);
		r.add("ID_KEMASKINI", user_id);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql2 = r.getSQLUpdate("tblppkpermohonan");
		System.out.println("CHECK ADD NEW 3:" + sql2);
		stmt.executeUpdate(sql2);

	}

	public void SST_update_kosong(HttpSession session, String id_fail,
			String id_permohonan, String id_status, String id_suburusanstatus,
			String user_id, Db db) throws Exception {
		String sql = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		r.clear();
		r.update("id_fail", id_fail);
		r.update("id_suburusanstatus", id_suburusanstatus);
		r.add("ID_KEMASKINI", user_id);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql = r.getSQLUpdate("tblrujsuburusanstatusfail");
		stmt.executeUpdate(sql);

	}


	public void deleteMSTDTL_HTA(HttpSession session,String ID_PERINTAH,String IN_HARTA,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String sql_condition = "";
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String condition = "";

			if(IN_HARTA.equals(""))
			{
				IN_HARTA = "0";
			}

			sql = "DELETE FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST IN (SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '"+ID_PERINTAH+"' " +
					" AND ID_HTA NOT IN ("+IN_HARTA+") AND FLAG_HARTA = 'L')";
			myLogger.info("DELETE  TBLPPKPERINTAHHTAOBDTL : "+sql);
			stmt.executeUpdate(sql);

			if(session!=null)
			{
				AuditTrail.logActivity(null,session,"DEL","TBLPPKPERINTAHHTAOBDTL [ID_PERINTAH : '"+ID_PERINTAH+"', ID_HTA NOT IN ("+IN_HARTA+")]",db1);
			}


			sql = "DELETE FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '"+ID_PERINTAH+"' AND ID_HTA NOT IN ("+IN_HARTA+") AND FLAG_HARTA = 'L'";
			myLogger.info("DELETE  TBLPPKPERINTAHHTAOBMST : "+sql);
		    stmt.executeUpdate(sql);
		    if(session!=null)
			{
		    	AuditTrail.logActivity(null,session,"DEL","TBLPPKPERINTAHHTAOBMST [ID_PERINTAH : '"+ID_PERINTAH+"', ID_HTA NOT IN ("+IN_HARTA+")]",db1);
			}


		} catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public void deleteMSTDTL_HA(HttpSession session,String ID_PERINTAH,String IN_HARTA,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String sql_condition = "";
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String condition = "";

			if(IN_HARTA.equals(""))
			{
				IN_HARTA = "0";
			}


			sql = "DELETE FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST IN (SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '"+ID_PERINTAH+"' " +
					" AND ID_HA NOT IN ("+IN_HARTA+") AND FLAG_HARTA = 'L')";
			myLogger.info("DELETE  TBLPPKPERINTAHHAOBDTL : "+sql);
			stmt.executeUpdate(sql);

			if(session!=null)
			{
			AuditTrail.logActivity(null,session,"DEL","TBLPPKPERINTAHHAOBDTL [ID_PERINTAH : '"+ID_PERINTAH+"', ID_HTA NOT IN ("+IN_HARTA+")]",db1);
			}

			sql = "DELETE FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '"+ID_PERINTAH+"' AND ID_HA NOT IN ("+IN_HARTA+") AND FLAG_HARTA = 'L'";
			myLogger.info("DELETE  TBLPPKPERINTAHHAOBMST : "+sql);
		    stmt.executeUpdate(sql);

		    if(session!=null)
			{
		    AuditTrail.logActivity(null,session,"DEL","TBLPPKPERINTAHHAOBMST [ID_PERINTAH : '"+ID_PERINTAH+"', ID_HTA NOT IN ("+IN_HARTA+")]",db1);
			}


		} catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}




	public String saveTblPPKPerintahHTAOBDTL(HttpSession session, String usid, String idHTAMSTNew, Map k,Db db) throws Exception {
		Db db1 = null;
		String return_ID_PerintahHTAOBDTL= "";
		String sql = "";
		long ID = 0;
		String USER_ID_SYSTEM = "";
		if(session!=null)
		{
			USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		}
		else
		{
			USER_ID_SYSTEM = usid;
		}

		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			ID = DB.getNextID(db1, "TBLPPKPERINTAHHTAOBDTL_SEQ");
			return_ID_PerintahHTAOBDTL = ID+"";
			r.add("ID_PERINTAHHTAOBDTL", ID);
			r.add("ID_PERINTAHHTAOBMST", idHTAMSTNew);
			r.add("ID_OB", (String)k.get("ID_OB"));
			r.add("BA", (String)k.get("BA"));
			r.add("BB", (String)k.get("BB"));
			r.add("STATUS_TADBIR", (String)k.get("STATUS_TADBIR"));
			r.add("CATATAN", (String)k.get("CATATAN"));
			r.add("ID_PA1", (String)k.get("ID_PA1"));
			r.add("ID_PA2", (String)k.get("ID_PA2"));
			r.add("ID_PA3", (String)k.get("ID_PA3"));
			r.add("ID_PA4", (String)k.get("ID_PA4"));
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");

			myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE saveTblPPKPerintahHTAOBDTL : "+sql);
			stmt.executeUpdate(sql);

		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return return_ID_PerintahHTAOBDTL;
	}


	public String saveTblPPKPerintahHAOBDTL(HttpSession session, String usid, String idHAMSTNew, Map k,Db db) throws Exception {
		Db db1 = null;
		String return_ID_PerintahHAOBDTL= "";
		String sql = "";
		long ID = 0;
		String USER_ID_SYSTEM = "";
		if(session!=null)
		{
			USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		}
		else
		{
			USER_ID_SYSTEM = usid;
		}
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			ID = DB.getNextID(db1, "TBLPPKPERINTAHHAOBDTL_SEQ");
			return_ID_PerintahHAOBDTL = ID+"";
			r.add("ID_PERINTAHHAOBDTL", ID);
			r.add("ID_PERINTAHHAOBMST", idHAMSTNew);
			r.add("ID_OB", (String)k.get("ID_OB"));
			r.add("BA", (String)k.get("BA"));
			r.add("BB", (String)k.get("BB"));
			r.add("STATUS_TADBIR", (String)k.get("STATUS_TADBIR"));
			r.add("CATATAN", (String)k.get("CATATAN"));
			r.add("ID_PA1", (String)k.get("ID_PA1"));
			r.add("ID_PA2", (String)k.get("ID_PA2"));
			r.add("ID_PA3", (String)k.get("ID_PA3"));
			r.add("ID_PA4", (String)k.get("ID_PA4"));
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");

			myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE saveTblPPKPerintahHAOBDTL : "+sql);
			stmt.executeUpdate(sql);

		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return return_ID_PerintahHAOBDTL;
	}




	public String saveTblPPKPerintahHTAOBMST(HttpSession session, String usid,String idPerintahHTAOBMST_current,String ID_PERINTAH,Map k,Db db) throws Exception {
		myLogger.info(" MAP HTAOBMST :::: "+k);
		Db db1 = null;
		String return_ID_PerintahHTAOBMST= "";
		String sql = "";
		long ID = 0;

		String USER_ID_SYSTEM = "";
		if(session!=null)
		{
			USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		}
		else
		{
			USER_ID_SYSTEM = usid;
		}


		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			if(idPerintahHTAOBMST_current.equals(""))
			{
				ID = DB.getNextID(db1, "TBLPPKPERINTAHHTAOBMST_SEQ");
				return_ID_PerintahHTAOBMST = ID+"";
			}
			else
			{
				return_ID_PerintahHTAOBMST = idPerintahHTAOBMST_current;
			}

			if(!idPerintahHTAOBMST_current.equals(""))
			{
				r.update("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST_current);
			}
			else
			{
				r.add("ID_PERINTAHHTAOBMST", ID);
			}

			r.add("ID_HTA", (String)k.get("ID_HTA"));
			r.add("ID_PERINTAH", ID_PERINTAH);
			r.add("CATATAN", (String)k.get("CATATAN"));

			String TARIKH_JUALAN = "to_date('" + (String)k.get("TARIKH_JUALAN") + "','dd/MM/yyyy')";
			myLogger.info("TARIKH_JUALAN : " + TARIKH_JUALAN);

			r.add("TARIKH_JUALAN", r.unquote(TARIKH_JUALAN));
			r.add("AMAUN", (String)k.get("AMAUN"));
			r.add("JENIS_LELONG",(String)k.get("JENIS_LELONG"));
			r.add("HARGA_RIZAB", (String)k.get("HARGA_RIZAB"));
			r.add("ID_JENISPERINTAH", (String)k.get("ID_JENISPERINTAH"));
			r.add("FLAG_HARTA", "L");

			if(!idPerintahHTAOBMST_current.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBMST");

			}
			myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE saveTblPPKPerintahHTAOBMST : "+sql);
			stmt.executeUpdate(sql);

		} /*
		catch (Exception re) {
			throw re;
		}*/finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return return_ID_PerintahHTAOBMST;
	}


	public String saveTblPPKPerintahHAOBMST(HttpSession session,String usid, String idPerintahHAOBMST_current,String ID_PERINTAH,Map k,Db db) throws Exception {
		myLogger.info(" MAP HTAOBMST :::: "+k);
		Db db1 = null;
		String return_ID_PerintahHAOBMST= "";
		String sql = "";
		long ID = 0;
		String USER_ID_SYSTEM = "";
		if(session!=null)
		{
			USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		}
		else
		{
			USER_ID_SYSTEM = usid;
		}

		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			if(idPerintahHAOBMST_current.equals(""))
			{
				ID = DB.getNextID(db1, "TBLPPKPERINTAHHAOBMST_SEQ");
				return_ID_PerintahHAOBMST = ID+"";
			}
			else
			{
				return_ID_PerintahHAOBMST = idPerintahHAOBMST_current;
			}

			if(!idPerintahHAOBMST_current.equals(""))
			{
				r.update("ID_PERINTAHHAOBMST", idPerintahHAOBMST_current);
			}
			else
			{
				r.add("ID_PERINTAHHAOBMST", ID);
			}

			r.add("ID_HA", (String)k.get("ID_HA"));
			r.add("ID_PERINTAH", ID_PERINTAH);
			r.add("CATATAN", (String)k.get("CATATAN") == null ? "" : (String)k.get("CATATAN"));
			String TARIKH_JUALAN = "to_date('" + (String)k.get("TARIKH_JUALAN") + "','dd/MM/yyyy')";
			r.add("TARIKH_JUALAN", r.unquote(TARIKH_JUALAN));
			r.add("AMAUN", (String)k.get("AMAUN") == null ? "" : (String)k.get("AMAUN"));
			r.add("JENIS_LELONG",(String)k.get("JENIS_LELONG") == null ? "" : (String)k.get("JENIS_LELONG"));
			r.add("HARGA_RIZAB", (String)k.get("HARGA_RIZAB") == null ? "" : (String)k.get("HARGA_RIZAB"));
			r.add("ID_JENISPERINTAH", (String)k.get("ID_JENISPERINTAH") == null ? "" : (String)k.get("ID_JENISPERINTAH"));
			r.add("FLAG_HARTA", "L");

			if(!idPerintahHAOBMST_current.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPKPERINTAHHAOBMST");

			}
			myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE saveTblPPKPerintahHAOBMST : "+sql);
			stmt.executeUpdate(sql);

		} /*
		catch (Exception re) {
			throw re;
		}*/finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return return_ID_PerintahHAOBMST;
	}

	public String getIdPerintahHTAOBMST(String ID_HTA,Db db) throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String ID_PERINTAHOBMST = "";
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT ID_PERINTAHOBMST FROM TBLPPKHTA WHERE ID_HTA = '" + ID_HTA + "'";
			myLogger.info("getIdPerintahHTAOBMST :: "+sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				ID_PERINTAHOBMST = rs == null ? "" : rs.getString("ID_PERINTAHOBMST") == null ? "" : rs.getString("ID_PERINTAHOBMST");
			}
			return ID_PERINTAHOBMST;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	public String getIdPerintahHTAOBMST_CURRENT(String ID_HTA,String ID_PERINTAH,String FLAG_HARTA, Db db) throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String ID_PERINTAHHTAOBMST = "";
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST " +
					" WHERE ID_HTA = '" + ID_HTA + "' AND ID_PERINTAH = '" + ID_PERINTAH + "'";
			sql +=	" AND FLAG_HARTA = '"+FLAG_HARTA+"' ";
			myLogger.info("getIdPerintahHTAOBMST_CURRENT :: "+sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				ID_PERINTAHHTAOBMST = rs == null ? "" : rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST");
			}
			return ID_PERINTAHHTAOBMST;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public String getIdPerintahHAOBMST_CURRENT(String ID_HA,String ID_PERINTAH,String FLAG_HARTA, Db db) throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String ID_PERINTAHHAOBMST = "";
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST " +
					" WHERE ID_HA = '" + ID_HA + "' AND ID_PERINTAH = '" + ID_PERINTAH + "'";
			sql +=	" AND FLAG_HARTA = '"+FLAG_HARTA+"' ";
			myLogger.info("getIdPerintahHAOBMST_CURRENT :: "+sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				ID_PERINTAHHAOBMST = rs == null ? "" : rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST");
			}
			return ID_PERINTAHHAOBMST;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public String getIdPerintahHAOBMST(String ID_HA,Db db) throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String ID_PERINTAHOBMST = "";
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT ID_PERINTAHOBMST FROM TBLPPKHA WHERE ID_HA = '" + ID_HA + "'";
			myLogger.info("getIdPerintahHAOBMST :: "+sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				ID_PERINTAHOBMST = rs == null ? "" : rs.getString("ID_PERINTAHOBMST") == null ? "" : rs.getString("ID_PERINTAHOBMST");
			}
			return ID_PERINTAHOBMST;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	/*
	private static void copyHTADTL(String idHTAMSTOld, String idHTAMSTNew, String userId, String idJenisPerintah) throws Exception {
        Db db = null;
        String sql = "";

        try {

              db = new Db();
              Statement stmt = db.getStatement();

              sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idHTAMSTOld + "'";

              if (!"2".equals(idJenisPerintah)){
                    sql = sql + " AND STATUS_TADBIR IS NOT NULL";
              }

              ResultSet rs = stmt.executeQuery(sql);

              while (rs.next()){
                    insertIntoTblPPKPerintahHTAOBDTL((String)rs.getString("ID_PERINTAHHTAOBDTL"), idHTAMSTNew, userId);
              }

        } finally {
              if (db != null)
                    db.close();
        }
  }
  */

	public List listHTADTL(HttpSession session,String idHTAMSTOld, String idHTAMSTNew, String idJenisPerintah, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();

			sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idHTAMSTOld + "'";

			if (!"2".equals(idJenisPerintah)){
				sql = sql + " AND STATUS_TADBIR IS NOT NULL";
			}

			myLogger.info("listHTADTL :: "+sql);
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_PERINTAHHTAOBDTL",rs == null ? "" : rs.getString("ID_PERINTAHHTAOBDTL") == null ? "" : rs.getString("ID_PERINTAHHTAOBDTL"));
				list.add(h);
			}
			return list;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	public List listHADTL(HttpSession session,String idHAMSTOld, String idHAMSTNew, String idJenisPerintah, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();

			sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idHAMSTOld + "'";

			if (!"2".equals(idJenisPerintah)){
				sql = sql + " AND STATUS_TADBIR IS NOT NULL";
			}

			myLogger.info("listHADTL :: "+sql);
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_PERINTAHHAOBDTL",rs == null ? "" : rs.getString("ID_PERINTAHHAOBDTL") == null ? "" : rs.getString("ID_PERINTAHHAOBDTL"));
				list.add(h);
			}
			return list;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	public List listPilihanHTA(HttpSession session,String ID_PERMOHONANSIMATI, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPilihanHTA = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT ID_HTA FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '" + ID_PERMOHONANSIMATI + "'";
			myLogger.info("getListPilihanHTA :: "+sql);
			rs = stmt.executeQuery(sql);
			listPilihanHTA = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_HTA",rs == null ? "" : rs.getString("ID_HTA") == null ? "" : rs.getString("ID_HTA"));
				listPilihanHTA.add(h);
			}
			return listPilihanHTA;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}

	}


	public Map mapPerintahHTAOBDTL(String idHTADTLOld, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT * FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBDTL = '" + idHTADTLOld + "'";
			myLogger.info("mapPerintahHTAOBDTL :: "+sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_OB",rs == null ? "" : rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("BA",rs == null ? "" : rs.getString("BA") == null ? "" : rs.getString("BA"));
				h.put("BB",rs == null ? "" : rs.getString("BB") == null ? "" : rs.getString("BB"));
				h.put("STATUS_TADBIR",rs == null ? "" : rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
				h.put("CATATAN",rs == null ? "" : rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("ID_PA1",rs == null ? "" : rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
				h.put("ID_PA2",rs == null ? "" : rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
				h.put("ID_PA3",rs == null ? "" : rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
				h.put("ID_PA4",rs == null ? "" : rs.getString("ID_PA4") == null ? "" : rs.getString("ID_PA4"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}

	}

	public Map mapPerintahHAOBDTL(String idHADTLOld, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT * FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBDTL = '" + idHADTLOld + "'";
			myLogger.info("mapPerintahHAOBDTL :: "+sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_OB",rs == null ? "" : rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("BA",rs == null ? "" : rs.getString("BA") == null ? "" : rs.getString("BA"));
				h.put("BB",rs == null ? "" : rs.getString("BB") == null ? "" : rs.getString("BB"));
				h.put("STATUS_TADBIR",rs == null ? "" : rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
				h.put("CATATAN",rs == null ? "" : rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("ID_PA1",rs == null ? "" : rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
				h.put("ID_PA2",rs == null ? "" : rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
				h.put("ID_PA3",rs == null ? "" : rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
				h.put("ID_PA4",rs == null ? "" : rs.getString("ID_PA4") == null ? "" : rs.getString("ID_PA4"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}

	}

	public Map listPerintahHTAOBMST(String idHTAOBMST, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		//List list = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT * FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idHTAOBMST + "'";
			myLogger.info("listPerintahHTAOBMSTSebelumInsert :: "+sql);
			rs = stmt.executeQuery(sql);
			//list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());

				h.put("ID_HTA",rs == null ? "" : rs.getString("ID_HTA") == null ? "" : rs.getString("ID_HTA"));
				h.put("CATATAN",rs == null ? "" : rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("TARIKH_JUALAN",rs == null ? "" : rs.getString("TARIKH_JUALAN") == null ? "" : rs.getString("TARIKH_JUALAN"));
				h.put("AMAUN",rs == null ? "" : rs.getString("AMAUN") == null ? "" : rs.getString("AMAUN"));
				h.put("HARGA_RIZAB",rs == null ? "" : rs.getString("HARGA_RIZAB") == null ? "" : rs.getString("HARGA_RIZAB"));
				h.put("ID_JENISPERINTAH",rs == null ? "" : rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));
				h.put("JENIS_LELONG",rs == null ? "" : rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));

				//list.add(h);
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}

	}


	public Map listPerintahHAOBMST(String idHAOBMST, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT * FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idHAOBMST + "'";
			myLogger.info("listPerintahHAOBMSTSebelumInsert :: "+sql);
			rs = stmt.executeQuery(sql);
			//list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_HA",rs == null ? "" : rs.getString("ID_HA") == null ? "" : rs.getString("ID_HA"));
				h.put("CATATAN",rs == null ? "" : rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("TARIKH_JUALAN",rs == null ? "" : rs.getString("TARIKH_JUALAN") == null ? "" : rs.getString("TARIKH_JUALAN"));
				h.put("AMAUN",rs == null ? "" : rs.getString("AMAUN") == null ? "" : rs.getString("AMAUN"));
				h.put("JENIS_LELONG",rs == null ? "" : rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));
				h.put("HARGA_RIZAB",rs == null ? "" : rs.getString("HARGA_RIZAB") == null ? "" : rs.getString("HARGA_RIZAB"));
				h.put("ID_JENISPERINTAH",rs == null ? "" : rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));
				//list.add(h);
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}

	}

	public List listPilihanHA(HttpSession session,String ID_PERMOHONANSIMATI, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPilihanHA = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT ID_HA FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '" + ID_PERMOHONANSIMATI + "'";
			myLogger.info("getListPilihanHA :: "+sql);
			rs = stmt.executeQuery(sql);
			listPilihanHA = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_HA",rs == null ? "" : rs.getString("ID_HA") == null ? "" : rs.getString("ID_HA"));
				listPilihanHA.add(h);
			}
			return listPilihanHA;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}

	}




	public List listHubungan(HttpSession session,String ID_PERMOHONANSIMATI,String ID_PERBICARAAN,String skrinName,String ID_OB, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listHubungan = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql += " SELECT * FROM TBLPPKHUBUNGANOBPERMOHONAN WHERE ID_HUBUNGANOBPERMOHONAN IS NOT NULL ";

			if(!ID_OB.equals(""))
			{
				sql += " AND ID_OB = '"+ID_OB+"' ";
			}
			if(!ID_OB.equals(""))
			{
				sql += " AND ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' ";
			}

			myLogger.info(" BICARA INTERAKTIF : SQL listHubungan :"+ sql);
			rs = stmt.executeQuery(sql);
			//List listColumnByTable = listColumnByTable(session,skrinName,"TBLPPKHUBUNGANOBPERMOHONAN",db1);
			listHubungan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_HUBUNGANOBPERMOHONAN",rs == null ? "" : rs.getString("ID_HUBUNGANOBPERMOHONAN") == null ? "" : rs.getString("ID_HUBUNGANOBPERMOHONAN"));
				h.put("ID_PERMOHONANSIMATI",rs == null ? "" : rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("ID_HUBUNGANOB",rs == null ? "" : rs.getString("ID_HUBUNGANOB") == null ? "" : rs.getString("ID_HUBUNGANOB"));
				h.put("ID_OB",rs == null ? "" : rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("ID_PARENTOB",rs == null ? "" : rs.getString("ID_PARENTOB") == null ? "" : rs.getString("ID_PARENTOB"));
				h.put("ID_SAUDARA",rs == null ? "" : rs.getString("ID_SAUDARA") == null ? "" : rs.getString("ID_SAUDARA"));

				/*
				if (listColumnByTable.size() != 0) {
					for (int i = 0; i < listColumnByTable.size(); i++) {
						Map map_column_name = (Map) listColumnByTable.get(i);
						String column_name = (String) map_column_name.get("COLUMN_NAME");
						String valueColumn = rs.getString(column_name) == null ? "" : rs.getString(column_name);
						h.put(column_name,rs == null ? "" :valueColumn == null ? "" : valueColumn);
					}
				}
				*/

				myLogger.info(" BICARA INTERAKTIF : SQL listHubungan.add(h) :"+ h);
				listHubungan.add(h);
			}
			return listHubungan;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}

	}


	@SuppressWarnings("unchecked")
	public List listColumnByTable(HttpSession session,String skrinName,String table_name,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listColumnByTable = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE OWNER='"+skemaDB.toUpperCase()+"' AND TABLE_NAME='"+table_name+"'";
			if(table_name.equals("TBLPPKOBPERMOHONAN") && skrinName.equals("waris"))
			{
				sql += " UNION ALL " +
						" SELECT 'ID_PARENTOB' AS COLUMN_NAME FROM DUAL ";
			}
			else if(table_name.equals("TBLPPKPEMOHON"))
			{
				sql += " UNION ALL " +
						" SELECT 'ID_OBPERMOHONAN' AS COLUMN_NAME FROM DUAL ";
				sql += " UNION ALL " +
						" SELECT 'ID_OB' AS COLUMN_NAME FROM DUAL ";
				sql += " UNION ALL " +
						" SELECT 'ID_SIMATI' AS COLUMN_NAME FROM DUAL ";
				sql += " UNION ALL " +
						" SELECT 'ID_PERMOHONANSIMATI' AS COLUMN_NAME FROM DUAL ";
			}
			myLogger.info(" BICARA INTERAKTIF : SQL listColumnByTable :"+ sql);
			rs = stmt.executeQuery(sql);

			listColumnByTable = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("COLUMN_NAME",rs == null ? "" :rs.getString("COLUMN_NAME") == null ? "" : rs.getString("COLUMN_NAME").toUpperCase());
				listColumnByTable.add(h);
			}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return listColumnByTable;
	}


	@SuppressWarnings("unchecked")
	public String setRowEditButtonBantahan(HttpSession session,String flagShowLantik,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName, String mode, Map map_data, String formName, String table_name,String divLoad, String command, String params, String retainScrollPosition, String ID_PEMOHON_MAIN,Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px'  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		html += "<input type='button' id='cmdKemaskini"+skrinName+"' name='cmdKemaskini"+skrinName+"' value='Kemaskini' onClick=\"doDivAjaxCall"+formName+"('"+divLoad+"','"+command+"','"+params+"&scrolPosition='+getPageLocation());\" >";
		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}


	@SuppressWarnings("unchecked")
	public String setRowEditButton(HttpSession session,String flagShowLantik,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName, String mode, Map map_data, String formName, String table_name,String divLoad, String command, String params, String retainScrollPosition, String ID_PEMOHON_MAIN,Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px'  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";

		if(!jenis_transaction.equals("DELETE"))
		{
			html += "<input type='button' id='cmdKemaskini"+skrinName+"' name='cmdKemaskini"+skrinName+"' value='Kemaskini' onClick=\"doDivAjaxCall"+formName+"('"+divLoad+"','"+command+"','"+params+"&scrolPosition='+getPageLocation());\" >";
		}

		if(!skrinName.equals("pemohon") && !skrinName.equals("simati"))
		{
			if(!jenis_transaction.equals("INSERT") && !jenis_transaction.equals("DELETE") && !current_previous.equals("previous"))
			{
				html += "<input type='button' id='cmdHapus"+skrinName+"' name='cmdHapus"+skrinName+"' value='Hapus' onClick=\"if(confirm('Adakah Anda Pasti?')){" +
						//"setingTrDiv('divId"+skrinName+"','');" +
								"doDivAjaxCall"+formName+"('"+divLoad+"','deleteMaklumat','"+params+"&scrolPosition='+getPageLocation());" +
										"}" +
										"\" >";
			}
			html += "<input type='button' id='cmdTutup"+skrinName+"' name='cmdTutup"+skrinName+"' value='Tutup' onClick=\"doDivAjaxCall"+formName+"('senarai_"+skrinName+current_previous+"','showSenarai','"+params+"&scrolPosition='+getPageLocation());\" >";
		}
		else
		{
			if(jenis_transaction.equals("UPDATE"))
			{
				html += "<input type='button' id='cmdHapusPerubahan"+skrinName+"' name='cmdHapusPerubahan"+skrinName+"' value='Hapus Perubahan Maklumat' onClick=\"if(confirm('Perubahan Maklumat akan dipadam. Adakah Anda Pasti?')){doDivAjaxCall"+formName+"('view_"+skrinName+"','hapusPerubahanMaklumat','"+params+"&scrolPosition='+getPageLocation());}\" >";
			}
		}

		if(table_name.equals("TBLPPKOBPERMOHONAN"))
		{
			if(flagShowLantik.equals("Y") && !jenis_transaction.equals("DELETE"))
			{
				//en seh kata, off dlu.. tak perlu sampai nak tukar pemohon
				//html += "<input type='button' id='cmdLantikPemohon"+skrinName+"' name='cmdLantikPemohon"+skrinName+"' value='Lantik Pemohon' onClick=\"if(confirm('Adakah Anda Pasti Untuk Mengganti Pemohon?')){setIdPemohon('"+skrinName+"','"+ID_PEMOHON_MAIN+"');doDivAjaxCall"+formName+"('"+divLoad+"','simpanMaklumat','"+params+"&flag_gantiPemohon=Y&scrolPosition='+getPageLocation());}\" >";
			}
		}

		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}

	public String setRowSaveButtonBantahan(HttpSession session,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px'  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		html += "<input type='button' id='cmdSimpan"+skrinName+"' name='cmdSimpan"+skrinName+"' value='Simpan' onClick=\"if(valSimpan"+skrinName+"('"+skrinName+"') == true) {doDivAjaxCall"+formName+"('"+divLoad+"','"+commandSimpan+"','"+params+"&scrolPosition='+getPageLocation());}\" >";
		html += "<input type='button' id='cmdKemaskini"+skrinName+"' name='cmdKemaskini"+skrinName+"' value='Batal' onClick=\"doDivAjaxCall"+formName+"('"+divLoad+"','"+commandBatal+"','"+params+"&scrolPosition='+getPageLocation())\" >";
		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}

	public String setRowSaveButton(HttpSession session,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px'  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		if(retainScrollPosition.equals("Y"))
		{
			html += "<input type='button' id='cmdSimpan"+skrinName+"' name='cmdSimpan"+skrinName+"' value='Simpan' onClick=\"if(valSimpan"+skrinName+"('"+skrinName+"') == true) {doDivAjaxCall"+formName+"('"+divLoad+"','"+commandSimpan+"','"+params+"&scrolPosition='+getPageLocation());}\" >";
			html += "<input type='button' id='cmdKemaskini"+skrinName+"' name='cmdKemaskini"+skrinName+"' value='Batal' onClick=\"doDivAjaxCall"+formName+"('"+divLoad+"','"+commandBatal+"','"+params+"&scrolPosition='+getPageLocation())\" >";
		}
		else
		{
			html += "<input type='button' id='cmdKemaskini"+skrinName+"' name='cmdKemaskini"+skrinName+"' value='Batal' onClick=\"doDivAjaxCall"+formName+"('"+divLoad+"','"+commandBatal+"','"+params+"')\" >";
		}
		if(!skrinName.equals("pemohon") && !skrinName.equals("simati"))
		{
			html += "<input type='button' id='cmdTutup"+skrinName+"' name='cmdTutup"+skrinName+"' value='Tutup' onClick=\"doDivAjaxCall"+formName+"('senarai_"+skrinName+current_previous+"','showSenarai','"+params+"&scrolPosition='+getPageLocation());\" >";
		}

		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}
	public String setRowSaveButtonKeputusan(HttpSession session,String keputusanBicara,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		html += " <input type=\"button\" id=\"cmdSimpan"+skrinName+"\" name=\"cmdSimpan"+skrinName+"\" value=\"Simpan Keputusan\" onClick=\"if(valSimpanKeputusan('"+skrinName+"') == true){" +
				//"addClassToDiv('loadWholePage','loading');" +
				" " +
				"doDivAjaxCall"+formName+"('div_viewPerbicaraan','simpanKeputusanPerintah','"+params+"&scrolPosition='+getPageLocation());" +
						"}\" >";
		html += " <input type=\"button\" id=\"cmdKemaskini"+skrinName+"\" name=\"cmdKemaskini"+skrinName+"\" value=\"Batal\" onClick=\"doDivAjaxCall"+formName+"('view_keputusan','resetKeputusanPerintah','"+params+"&scrolPosition='+getPageLocation());\" >";


		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}
	public String setRowEditButtonKeputusan(HttpSession session,String keputusanBicara,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		html += " <input type=\"button\" id=\"cmdSimpan"+skrinName+"\" name=\"cmdSimpan"+skrinName+"\" value=\"Kemaskini\" onClick=\"doDivAjaxCall"+formName+"('view_keputusan','editKeputusanPerintah','"+params+"&scrolPosition='+getPageLocation());\" >";

		if(keputusanBicara.equals("0"))
		{
			html += "<input type=\"button\" id=\"cmdKePerintah"+skrinName+"\" name=\"cmdKePerintah"+skrinName+"\" value=\"Skrin Perintah Pembahagian\" onClick=\"goToPerintah()\" >";

		}

		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}




	public String setRowSaveButtonTukarPegawai(HttpSession session,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, String pemohonOrKPP,String openFrom, Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";

		if(skrinName.equals("tukarpegawaiKPP"))
		{
			html += " <input type=\"button\" id=\"cmdLulus"+skrinName+"\" name=\"cmdLulus"+skrinName+"\" value=\"Lulus\" onClick=\"if(valSimpanTukarPegawai('"+skrinName+"') == true){" +
					"doDivAjaxCall"+formName+"('view_"+skrinName+"','lulusTukarPegawai','"+params+"&scrolPosition='+getPageLocation());" +
							"}\" >";
			html += " <input type=\"button\" id=\"cmdTolak"+skrinName+"\" name=\"cmdTolak"+skrinName+"\" value=\"Tolak\" onClick=\"if(valSimpanTukarPegawai('"+skrinName+"') == true){" +
					"doDivAjaxCall"+formName+"('view_"+skrinName+"','tolakTukarPegawai','"+params+"&scrolPosition='+getPageLocation());" +
							"}\" >";
		}
		else
		{
			html += " <input type=\"button\" id=\"cmdSimpan"+skrinName+"\" name=\"cmdSimpan"+skrinName+"\" value=\"Hantar\" " +
					"onClick=\"if(valSimpanTukarPegawai('"+skrinName+"') == true){" +
					"doDivAjaxCall"+formName+"('view_"+skrinName+"','saveTukarPegawai','"+params+"&scrolPosition='+getPageLocation());" +
							"}\" >";
		}


		html += " <input type=\"button\" id=\"cmdKemaskini"+skrinName+"\" name=\"cmdKemaskini"+skrinName+"\" value=\"Batal\" onClick=\"doDivAjaxCall"+formName+"('view_"+skrinName+"','resetTukarPegawai','"+params+"&scrolPosition='+getPageLocation());\" >";
		//html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" onClick=\"$jquery('#view_"+skrinName+"').html('');\" >";

		if(skrinName.equals("tukarpegawaiKPP"))
		{
			html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" " +
					"onClick=\"" +
					"$jquery('#view_"+skrinName+"').html('');" +
					"document.getElementById('listPermohonanTukarPegawai').style.display = '';" +
					"document.getElementById('cmdKembaliSenaraiTukarPegawai').style.display = 'none';" +
					"doDivAjaxCall"+formName+"('listPermohonanTukarPegawai','showPermohonanTukarPegawai','');" +
					"\" >";
		}
		else
		{
			html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" onClick=\"$jquery('#view_"+skrinName+"').html('');\" >";
		}

		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}


	public String setRowSaveButtonTukarPegawaiMultiple(HttpSession session,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, String pemohonOrKPP,String openFrom, Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";

		//tukarpegawai_multipleID_NEGERIPEGAWAIBARU
		//tukarpegawai_multipleID_PEGAWAIBARU

		if(skrinName.equals("tukarpegawaiKPP_multiple"))
		{
			html += " <input type=\"button\" id=\"cmdLulus"+skrinName+"\" name=\"cmdLulus"+skrinName+"\" value=\"Lulus\" onClick=\"if(valSimpanTukarPegawaiMultiple('"+skrinName+"') == true){" +
					"doDivAjaxCall"+formName+"('view_"+skrinName+"','lulusTukarPegawaiMultiple','"+params+"" +
							"&SELECTED_NAMA_NEGERI='+getValueFromDrop('"+skrinName+"ID_NEGERIPEGAWAIBARU')+'" +
							"&SELECTED_NAMA_PEGAWAI='+getValueFromDrop('"+skrinName+"ID_PEGAWAIBARU')+'" +
									"&scrolPosition='+getPageLocation());" +
							"}\" >";
			html += " <input type=\"button\" id=\"cmdTolak"+skrinName+"\" name=\"cmdTolak"+skrinName+"\" value=\"Tolak\" onClick=\"if(valSimpanTukarPegawaiMultipleTolak('"+skrinName+"') == true){" +
					"doDivAjaxCall"+formName+"('view_"+skrinName+"','tolakTukarPegawaiMultiple','"+params+"" +
							"&SELECTED_NAMA_NEGERI='+getValueFromDrop('"+skrinName+"ID_NEGERIPEGAWAIBARU')+'" +
							"&SELECTED_NAMA_PEGAWAI='+getValueFromDrop('"+skrinName+"ID_PEGAWAIBARU')+'" +
							"&scrolPosition='+getPageLocation());" +
							"}\" >";
		}
		else
		{
			html += " <input type=\"button\" id=\"cmdSimpan"+skrinName+"\" name=\"cmdSimpan"+skrinName+"\" value=\"Hantar\" " +
					"onClick=\"if(valSimpanTukarPegawaiMultiple('"+skrinName+"') == true){" +
					"doDivAjaxCall"+formName+"('view_"+skrinName+"','saveTukarPegawaiMultiple','"+params+"" +
							"&SELECTED_NAMA_NEGERI='+getValueFromDrop('"+skrinName+"ID_NEGERIPEGAWAIBARU')+'" +
							"&SELECTED_NAMA_PEGAWAI='+getValueFromDrop('"+skrinName+"ID_PEGAWAIBARU')+'" +
							"&scrolPosition='+getPageLocation());" +
							"}\" >";
		}


		html += " <input type=\"button\" id=\"cmdKemaskini"+skrinName+"\" name=\"cmdKemaskini"+skrinName+"\" value=\"Batal\" onClick=\"doDivAjaxCall"+formName+"('view_"+skrinName+"','resetTukarPegawaiMultiple','"+params+"&scrolPosition='+getPageLocation());\" >";

		/*
		if(skrinName.equals("tukarpegawaiKPP"))
		{
			html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" " +
					"onClick=\"" +
					"$jquery('#view_"+skrinName+"').html('');" +
					"document.getElementById('listPermohonanTukarPegawai').style.display = '';" +
					"document.getElementById('cmdKembaliSenaraiTukarPegawai').style.display = 'none';" +
					"doDivAjaxCall"+formName+"('listPermohonanTukarPegawai','showPermohonanTukarPegawai','');" +
					"\" >";
		}
		*/
		//else
		//{
			html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" onClick=\"tutupSkrinPegawaiMultiple('"+skrinName+"');\" >";
		//}

		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}

	//arief add tukar pegawai 2
	public String setRowSaveButtonTukarPegawai2(HttpSession session,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, String pemohonOrKPP,String openFrom, Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		if(skrinName.equals("tukarpegawaiKPP2"))
		{
			html += " <input type=\"button\" id=\"cmdLulus"+skrinName+"\" name=\"cmdLulus"+skrinName+"\" value=\"Lulus\" onClick=\"if(valSimpanTukarPegawaiMultiple('"+skrinName+"') == true){" +
					"doDivAjaxCall"+formName+"('view_"+skrinName+"','lulusTukarPegawaiMultiple','"+params+"" +
							"&SELECTED_NAMA_NEGERI='+getValueFromDrop('"+skrinName+"ID_NEGERIPEGAWAIBARU')+'" +
							"&SELECTED_NAMA_PEGAWAI='+getValueFromDrop('"+skrinName+"ID_PEGAWAIBARU')+'" +
									"&scrolPosition='+getPageLocation());" +
							"}\" >";
			html += " <input type=\"button\" id=\"cmdTolak"+skrinName+"\" name=\"cmdTolak"+skrinName+"\" value=\"Tolak\" onClick=\"if(valSimpanTukarPegawaiMultipleTolak('"+skrinName+"') == true){" +
					"doDivAjaxCall"+formName+"('view_"+skrinName+"','tolakTukarPegawaiMultiple','"+params+"" +
							"&SELECTED_NAMA_NEGERI='+getValueFromDrop('"+skrinName+"ID_NEGERIPEGAWAIBARU')+'" +
							"&SELECTED_NAMA_PEGAWAI='+getValueFromDrop('"+skrinName+"ID_PEGAWAIBARU')+'" +
							"&scrolPosition='+getPageLocation());" +
							"}\" >";
		}
		else
		{
			html += " <input type=\"button\" id=\"cmdSimpan"+skrinName+"\" name=\"cmdSimpan"+skrinName+"\" value=\"Hantar\" " +
					"onClick=\"if(valSimpanTukarPegawaiMultiple('"+skrinName+"') == true){" +
					"doDivAjaxCall"+formName+"('view_"+skrinName+"','saveTukarPegawaiMultiple','"+params+"" +
							"&SELECTED_NAMA_NEGERI='+getValueFromDrop('"+skrinName+"ID_NEGERIPEGAWAIBARU')+'" +
							"&SELECTED_NAMA_PEGAWAI='+getValueFromDrop('"+skrinName+"ID_PEGAWAIBARU')+'" +
							"&scrolPosition='+getPageLocation());" +
							"}\" >";
		}
		html += " <input type=\"button\" id=\"cmdKemaskini"+skrinName+"\" name=\"cmdKemaskini"+skrinName+"\" value=\"Batal\" onClick=\"doDivAjaxCall"+formName+"('view_"+skrinName+"','resetTukarPegawaiMultiple','"+params+"&scrolPosition='+getPageLocation());\" >";
		html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" onClick=\"tutupSkrinPegawaiMultiple('"+skrinName+"');\" >";
		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}

	public String setRowEditButtonTukarPegawai(HttpSession session,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, String pemohonOrKPP, String openFrom,Db db) throws Exception {

		String STATUS_TUKARPEGAWAI = map_data == null ? "" :(String) map_data.get("STATUS_TUKARPEGAWAI") == null ? "" : (String) map_data.get("STATUS_TUKARPEGAWAI");

		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		if((STATUS_TUKARPEGAWAI.equals("") || STATUS_TUKARPEGAWAI.equals("1")) && pemohonOrKPP.equals("pemohon"))
		{
			//html += " <input type=\"button\" id=\"cmdSimpan"+skrinName+"\" name=\"cmdSimpan"+skrinName+"\" value=\"Kemaskini\" onClick=\"doDivAjaxCall"+formName+"('view_"+skrinName+"','editTukarPegawai','"+params+"&scrolPosition='+getPageLocation());\" >";
		}

		if(skrinName.equals("tukarpegawaiKPP"))
		{
			html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" onClick=\"" +
					"$jquery('#view_"+skrinName+"').html('');" +
							"document.getElementById('listPermohonanTukarPegawai').style.display = '';" +
							"document.getElementById('cmdKembaliSenaraiTukarPegawai').style.display = 'none';"+
							"doDivAjaxCall"+formName+"('listPermohonanTukarPegawai','showPermohonanTukarPegawai','');\" >";
		}
		else
		{
			html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" onClick=\"$jquery('#view_"+skrinName+"').html('');\" >";
		}
		//doDivAjaxCall$formname('listPermohonanTukarPegawai','showPermohonanTukarPegawai','')
		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}


	public String setRowEditButtonTukarPegawaiMultiple(HttpSession session,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, String pemohonOrKPP, String openFrom,Db db) throws Exception {

		String STATUS_TUKARPEGAWAI = map_data == null ? "" :(String) map_data.get("STATUS_TUKARPEGAWAI") == null ? "" : (String) map_data.get("STATUS_TUKARPEGAWAI");

		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		/*
		if(skrinName.equals("tukarpegawaiKPP"))
		{
			html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" onClick=\"" +
					"$jquery('#view_"+skrinName+"').html('');" +
							"document.getElementById('listPermohonanTukarPegawai').style.display = '';" +
							"document.getElementById('cmdKembaliSenaraiTukarPegawai').style.display = 'none';"+
							"doDivAjaxCall"+formName+"('listPermohonanTukarPegawai','showPermohonanTukarPegawai','');\" >";
		}
		*/
		//else
		//{
			html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" onClick=\"$jquery('#view_"+skrinName+"').html('');\" >";
		//}

		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}

	//arief add tukar pegawai 2
	public String setRowEditButtonTukarPegawai2(HttpSession session,String jenis_transaction,String AKTIVITI,String FIELD_PK,String id,String current_previous,String skrinName,String mode, Map map_data, String formName, String table_name,String divLoad, String commandSimpan,String commandBatal, String params, String retainScrollPosition, String pemohonOrKPP, String openFrom,Db db) throws Exception {

		String STATUS_TUKARPEGAWAI = map_data == null ? "" :(String) map_data.get("STATUS_TUKARPEGAWAI") == null ? "" : (String) map_data.get("STATUS_TUKARPEGAWAI");

		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		html += " <input type=\"button\" id=\"cmdTutup"+skrinName+"\" name=\"cmdTutup"+skrinName+"\" value=\"Tutup\" onClick=\"$jquery('#view_"+skrinName+"').html('');\" >";
		html +=	"</td>";
		html += "</tr></table>";
		return html;
	}


	public String setDataList(HttpSession session,String NamaDataList, String skrinName, String column_name,
			String TABLE_NAME,String PK_FIELD, String KOD_FIELD, String KETERANGAN_FIELD, Db db)throws Exception {
		String setDataList = "<datalist id = '"+NamaDataList+"' >";
		if(TABLE_NAME.equals("STATUS_OB"))
		{
			setDataList += "<option label='DEWASA / WARAS' value='DEWASA / WARAS' ></option>";
			setDataList += "<option label='TIDAK SEMPURNA AKAL' value='TIDAK SEMPURNA AKAL' ></option>";
		}
		else
		{
			List listRefTable = listRefTable(session,skrinName,column_name,TABLE_NAME,PK_FIELD,KOD_FIELD,KETERANGAN_FIELD,"","", db);
			String optionDatalistNegara = "";
			if (listRefTable.size() != 0) {
				for (int i = 0; i < listRefTable.size(); i++) {
					Map map_column_name = (Map) listRefTable.get(i);
					String keterangan = (String) map_column_name.get(KETERANGAN_FIELD);
					setDataList += "<option label='"+keterangan+"' value = '"+keterangan+"' ></option>";
				}
			}
		}
		setDataList += "</datalist>";
		return setDataList;
	}

	public String setRowText(HttpSession session,String noTRTD,String ID_SEJARAHBIMAIN,String ID_PERMOHONANSIMATI,String skrinName,String mode, Map map_data, String label, String table_name,
			String field_main_PK,String value_main_PK,String id_perbicaraan,
			String column_name, String mandatory, String jenis_field, String showTitik,String maxLength,String flagUppercase,String defaultValue,
			double TURUTAN,Db db) throws Exception {
		column_name = column_name.toUpperCase().trim();
		String getValue = "";
		Map getChanges = null;
		if(map_data!=null)
		{
			//untuk kes kemaskini
			getValue = getValue(session,ID_PERMOHONANSIMATI, map_data,table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name,db);
			if(!skrinName.equals("keputusan") && !skrinName.equals("tukarpegawai") && !skrinName.equals("carianBicara"))
			{
				getChanges = getChanges(session,"",ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			}
		}
		else
		{
			//untuk kes tambah
			if(!skrinName.equals("keputusan") && !skrinName.equals("tukarpegawai") && !skrinName.equals("carianBicara"))
			{
				getChanges = getChanges(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			}

			if(getChanges==null)
			{
				getValue = defaultValue;
			}
		}

		String JENIS_AKTIVITI="";
		String VALUE_SEBELUM="";
		String KETERANGAN_SEBELUM="";
		String VALUE_SELEPAS="";
		String KETERANGAN_SELEPAS = "";
		boolean changes = false;
		if(getChanges!=null)
		{
			changes = true;
			JENIS_AKTIVITI = (String) getChanges.get("JENIS_AKTIVITI");
			VALUE_SEBELUM = (String) getChanges.get("VALUE_SEBELUM");
			KETERANGAN_SEBELUM = (String) getChanges.get("KETERANGAN_SEBELUM");
			VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
			KETERANGAN_SELEPAS = (String) getChanges.get("KETERANGAN_SELEPAS");
			getValue = VALUE_SELEPAS;
		}


		//myLogger.info("::::::::::::: ADA CHANGES : "+changes+" JENIS AKTIVITI : "+JENIS_AKTIVITI+" getValue ::::::::::: "+getValue);

		column_name = column_name.toUpperCase().trim();
		String html = "";
		if(!jenis_field.equals("hidden"))
		{
			if(!noTRTD.equals("Y"))
			{
				html += "<tr id='row"+skrinName+column_name+"' >";
				html += "<td align='center' valign='top' >";
				if(mandatory.equals("Y") && mode.equals("edit"))
				{
					html += "<font color='red'>*</font>";
				}
				html +=	"</td>";
				html += "<td align='left' valign='top' ><span id='label"+skrinName+column_name+"' >";
				if(!label.equals(""))
				{
					html += label;
				}
				html +=	"</span></td>";
				html += "<td align='center' valign='top' >";
				if(showTitik.equals("Y"))
				{
					html += ":";
				}
				html +=	"</td>";
				html += "<td align='left' valign='top' >";
			}
			//open special kes, cater no kp baru
			String kp1 = "";
			String kp2 = "";
			String kp3 = "";
			String valueDisplay = getValue;
			if(column_name.equals("NO_KP_BARU"))
			{
				if(getValue.length()==12)
				{
					kp1 = getValue.substring(0, 6);
					kp2 = getValue.substring(6, 8);
					kp3 = getValue.substring(8, 12);
					valueDisplay = kp1 +" - "+kp2+" - "+kp3;
				}
			}
			//close special kes, cater no kp baru

			if(mode.equals("view"))
			{
				if(flagUppercase.equals("Y"))
				{
					getValue = getValue.toUpperCase();
				}

				if(jenis_field.equals("currencyOnly"))
				{
					if(!getValue.equals(""))
					{
						try
						{
							DecimalFormat df = new DecimalFormat("###,###,##0.00");
							df.setRoundingMode(RoundingMode.CEILING);
							String getValueCurrency = df.format(Double.parseDouble(getValue));
							html += getValueCurrency;
						}
						catch(NumberFormatException e)
						{
							html += getValue;
						}
					}
					else
					{
						html += "-";
					}
				}
				else if(jenis_field.equals("areaOnly"))
				{
					if(!getValue.equals(""))
					{
						try
						{
							DecimalFormat df = new DecimalFormat("########0.0000");
							df.setRoundingMode(RoundingMode.CEILING);
							String getValueArea = df.format(Double.parseDouble(getValue));
							String jenis_luas_display = "";
							if(column_name.equals("LUAS_HMP") && (skrinName.equals("htaah") || skrinName.equals("htaahx")))
							{
								String ID_KATEGORI_HTA = getValue(session,ID_PERMOHONANSIMATI,map_data,table_name,field_main_PK,value_main_PK,id_perbicaraan,"ID_KATEGORI",db);
								myLogger.info("ID_KATEGORI_HTA : "+ID_KATEGORI_HTA);

								if(!ID_KATEGORI_HTA.equals(""))
								{
									if(ID_KATEGORI_HTA.equals("2"))
									{
										jenis_luas_display = " Hektar";
									}
									else
									{
										jenis_luas_display = " Meter Persegi";
									}
								}
							}
							html += getValueArea+jenis_luas_display;
						}
						catch(NumberFormatException e)
						{
							html += getValue;
						}


					}
				}
				else
				{
					if(column_name.equals("ID_PEMOHON") && table_name.equals("TBLPPKOBPERMOHONAN"))
					{
						if(!getValue.equals(""))
						{
							html += "YA";
						}
						else
						{
							html += "TIDAK";
						}
					}
					else
					{
						html += valueDisplay;
						if(valueDisplay.equals(""))
						{
							html += "-";
						}
					}
				}

				if(column_name.equals("NO_KP_BARU"))//special kes untuk no kp baru
				{
					html += "<input type='hidden' size='6' name='"+skrinName+column_name+"1' id='"+skrinName+column_name+"1'  value = '"+kp1+"'  />";
					html += "<input type='hidden' size='2' name='"+skrinName+column_name+"2' id='"+skrinName+column_name+"2'  value = '"+kp2+"'  />";
					html += "<input type='hidden' size='4' name='"+skrinName+column_name+"3' id='"+skrinName+column_name+"3'  value = '"+kp3+"'  />";
				}
				else if(column_name.equals("LUAS_HMP") && (skrinName.equals("htaah") || skrinName.equals("htaahx")))
				{
					html += "<input type='hidden' name='meterhektar"+skrinName+"' id='meterhektar"+skrinName+"' value = ''  />";
				}
				html += " <input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+getValue+"'  /> ";


			}
			else
			{
				String showText= "Y";
				if(column_name.equals("LAPIS") && skrinName.equals("waris"))
				{
					html += "<span id='span"+skrinName+column_name+"' >"+getValue.toUpperCase()+"</span>";
					html += " <input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+getValue+"'  /> ";
					showText = "N";
				}
				else if(column_name.equals("ID_PEMOHON") && table_name.equals("TBLPPKOBPERMOHONAN"))
				{
					if(!getValue.equals(""))
					{
						html += "<span id='span"+skrinName+column_name+"' >YA</span>";
					}
					else
					{
						html += "<span id='span"+skrinName+column_name+"' >TIDAK</span>";
					}
					html += " <input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+getValue+"'  /> ";
					showText = "N";
				}

				if(showText.equals("Y"))
				{

					String strMaxLength = "";
					if(!maxLength.equals(""))
					{
						strMaxLength = "maxlength='"+maxLength+"'";
					}

					String styleUppercase = "";
					if(flagUppercase.equals("Y"))
					{
						styleUppercase = "text-transform:uppercase";
					}

					String onBlurFunction = "";
					if(column_name.equals("UMUR") && (skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("pemiutang")))
					{
						onBlurFunction = "getStatusByAge(this,this.value,'"+skrinName+"STATUS_OB');";
					}

					if(jenis_field.equals("numbersOnly"))
					{
						if(column_name.equals("NO_KP_BARU"))//special kes untuk no kp baru
						{
							if(skrinName.equals("pemohon") || skrinName.equals("waris") || skrinName.equals("ob")  || skrinName.equals("pemiutang") || skrinName.equals("saksi"))//untuk simati tak perlu kira umur
							{
								onBlurFunction += "getAgeByIC(this,this.value,'"+skrinName+"UMUR');";
								if(skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi")  || skrinName.equals("pemiutang"))
								{
									//lepas da dpt umr, get status ob plak
									onBlurFunction += "setTarikhLahirIC(this.value,'"+skrinName+"TARIKH_LAHIR');";
									onBlurFunction += "getStatusByAge(this,document.getElementById('"+skrinName+"UMUR').value,'"+skrinName+"STATUS_OB');";
								}
							}


							html += "<input type='text' size='6' onKeyUp=\"validateIC_V3(event,this,this.value, '"+skrinName+column_name+"2');\" onBlur=\""+onBlurFunction+"\" maxlength='6' onkeydown=\"validateNumber(event);\" name='"+skrinName+column_name+"1' id='"+skrinName+column_name+"1'  value = '"+kp1+"'  /> - ";
							html += "<input type='text' size='2' onKeyUp=\"validateIC_V3(event,this,this.value, '"+skrinName+column_name+"3');\" maxlength='2' onkeydown=\"validateNumber(event);\" name='"+skrinName+column_name+"2' id='"+skrinName+column_name+"2'  value = '"+kp2+"'  /> - ";
							html += "<input type='text' size='4' maxlength='4' onkeydown=\"validateNumber(event);\" name='"+skrinName+column_name+"3' id='"+skrinName+column_name+"3'  value = '"+kp3+"'  />";
						}
						else
						{
							html += "<input type='text' "+strMaxLength+" onkeydown=\"validateNumber(event);\" name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' onBlur=\""+onBlurFunction+"\" class='fullwidth_input' value = '"+getValue+"'  />";
						}
					}
					else if(jenis_field.equals("currencyOnly"))
					{
						html += "<input type='text' "+strMaxLength+" style='"+styleUppercase+"'  onBlur=\"validateCurrencyBlur(event,this,this.value);\" onKeyUp=\"validateCurrency(event,this,this.value);\" name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' onBlur=\""+onBlurFunction+"\" class='fullwidth_input' value = '"+getValue+"'  />";
					}
					else if(jenis_field.equals("areaOnly"))
					{
						if(column_name.equals("LUAS_HMP") && (skrinName.equals("htaah") || skrinName.equals("htaahx")))
						{
							html += "<input type='text' "+strMaxLength+" size=\"15\" onBlur=\"validateAreaBlur(event,this,this.value);\" onKeyUp=\"validateCurrency(event,this,this.value);\" name='"+skrinName+column_name+"' id='"+skrinName+column_name+"'  value = '"+getValue+"'  />";
							html += "&nbsp;<input type='text' size=\"15\" readonly class=\"disabled\" name='meterhektar"+skrinName+"' id='meterhektar"+skrinName+"' value = ''  />";
						}
						else
						{
							html += "<input type='text' "+strMaxLength+" style='"+styleUppercase+"'  onBlur=\"validateAreaBlur(event,this,this.value);\" onKeyUp=\"validateCurrency(event,this,this.value);\" name='"+skrinName+column_name+"' id='"+skrinName+column_name+"'  class='fullwidth_input' value = '"+getValue+"'  />";
						}
					}
					else
					{
						if(column_name.equals("LUAS") && (skrinName.equals("htaah") || skrinName.equals("htaahx")))
						{
							html += "<input type='text' readonly class=\"disabled fullwidth_input\" name='"+skrinName+"LUAS' id='"+skrinName+"LUAS' value = '"+getValue+"' />";
						}
						else
						{
							String setDataList = "";
							String callDataList = "";
							if(column_name.equals("NAMA_PELBAGAINEGARA")
									|| column_name.equals("NAMA_PELBAGAINEGARA_SURAT")
									|| column_name.equals("NAMA_PEGAWAIBICARA")
									|| column_name.equals("NAMAPEGAWAIASAL")
									|| column_name.equals("NAMAPEGAWAIBARU"))
							{
								setDataList += "<datalist id = 'datalist"+skrinName+column_name+"' >";
								if(column_name.equals("NAMA_PELBAGAINEGARA") || column_name.equals("NAMA_PELBAGAINEGARA_SURAT"))
								{
									List listRefTable = listRefTable(session,skrinName,column_name,"TBLRUJKENEGARAAN","KOD_WARGA","","NAMA_WARGA","","", db);
									String optionDatalistNegara = "";
									if (listRefTable.size() != 0) {
										for (int i = 0; i < listRefTable.size(); i++) {
											Map map_column_name = (Map) listRefTable.get(i);
											String kod_negara = (String) map_column_name.get("KOD_WARGA");
											String keterangan_negara = (String) map_column_name.get("NAMA_WARGA");
											setDataList += "<option label='"+keterangan_negara+"' value = '"+keterangan_negara+"' ></option>";
										}
									}
								}
								else if(column_name.equals("NAMA_PEGAWAIBICARA") || column_name.equals("NAMAPEGAWAIASAL")
										|| column_name.equals("NAMAPEGAWAIBARU"))
								{
									List listRefTable = listRefTable(session,skrinName,column_name,"TBLPPKRUJUNIT","ID_UNITPSK","","NAMA_PEGAWAI","","", db);
									if (listRefTable.size() != 0) {
										for (int i = 0; i < listRefTable.size(); i++) {
											Map map_column_name = (Map) listRefTable.get(i);
											String pegawai = (String) map_column_name.get("NAMA_PEGAWAI");
											setDataList += "<option label='"+pegawai+"' value = '"+pegawai+"' ></option>";
										}
									}

								}
								setDataList += "</datalist>";
								callDataList = " list = 'datalist"+skrinName+column_name+"' ";

							}

							html += "<input type='text'   "+callDataList+"    "+strMaxLength+" style='"+styleUppercase+"' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' onBlur=\""+onBlurFunction+"\" class='fullwidth_input' value = \""+getValue+"\"  />";
							html += setDataList;
						}
					}
				}
			}

			if(changes == true && JENIS_AKTIVITI.equals("UPDATE"))
			{
				//open special kes, cater no kp baru
				String kp1VALUE_SEBELUM = "";
				String kp2VALUE_SEBELUM = "";
				String kp3VALUE_SEBELUM = "";
				//if ada changes
				if(column_name.equals("NO_KP_BARU"))
				{
					if(VALUE_SEBELUM.length()==12)
					{
						kp1VALUE_SEBELUM = VALUE_SEBELUM.substring(0, 6);
						kp2VALUE_SEBELUM = VALUE_SEBELUM.substring(6, 8);
						kp3VALUE_SEBELUM = VALUE_SEBELUM.substring(8, 12);
						VALUE_SEBELUM = kp1VALUE_SEBELUM +" - "+kp2VALUE_SEBELUM+" - "+kp3VALUE_SEBELUM;
					}
				}


				//untuk display jenis luas
				String changes_tambahan = "";
				if(column_name.equals("LUAS_HMP"))
				{
					if(map_data!=null)
					{
						getChanges = getChanges(session,"",ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,"ID_KATEGORI", db);
					}
					else
					{
						//untuk kes tambah
						getChanges = getChanges(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,"ID_KATEGORI", db);
						if(getChanges==null)
						{
							getValue = defaultValue;
						}
					}
					String value_asal = "";
					if(getChanges!=null)
					{
						value_asal = (String) getChanges.get("VALUE_SEBELUM");
					}

					if(!value_asal.equals(""))
					{
						if(value_asal.equals("2"))
						{
							changes_tambahan = " Hektar";
						}
						else
						{
							changes_tambahan = " Meter Persegi";
						}
					}
				}

				if(column_name.equals("ID_PEMOHON") && table_name.equals("TBLPPKOBPERMOHONAN"))
				{
					if(VALUE_SEBELUM.equals("") && !VALUE_SELEPAS.equals(""))
					{
						html += showHTMLChanges(label, "TIDAK");
					}
					else if(!VALUE_SEBELUM.equals("") && VALUE_SELEPAS.equals(""))
					{
						html += showHTMLChanges(label, "YA");
					}
				}
				else
				{
					html += showHTMLChanges(label, VALUE_SEBELUM+changes_tambahan);
				}
			}
			if(!noTRTD.equals("Y"))
			{
				html +=	"</td>";
				html += "</tr>";
			}
		}
		else
		{
			html += "<input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+getValue+"'  />";
		}
		html += " <input type='hidden' name='"+skrinName+column_name+"_turutan' id='"+skrinName+column_name+"_turutan' value = '"+TURUTAN+"'  /> ";
		html += " <input type='hidden' name='"+skrinName+column_name+"_label' id='"+skrinName+column_name+"_label' value = '"+label+"'  /> ";
		return html;
	}

	public String showHTMLChanges(String label, String VALUE_SEBELUM)throws Exception {
		if(VALUE_SEBELUM.equals(""))
		{
			VALUE_SEBELUM = "Tiada Rekod Asal";
		}
		String html = "";
		html += "<div title='Maklumat Asal Semasa Pendaftaran' class='div_history'>";
		html += "<table width='100%' align='center' border='0' cellpadding='3' cellspacing='1'  > ";
		//html += "<tr><td width='1%' valign='top'></td><td width='28%' valign='top' align='left'><span class='blink'><font color = 'blue'>"+label+"</font></span></td><td width='1%' valign='top' align='center'><font color = 'blue'>:</font></td><td width='70%' valign='top' align='left'><font color = 'blue'>"+VALUE_SEBELUM+"</font></td></tr>";
		html += "<tr><td width='100%' valign='top' align='left'><font color = 'blue'>"+VALUE_SEBELUM+"</font></td></tr>";
		html += "</table>";
		html += "</div>";
		return html;
	}

	public String setRowTextTarikh(HttpSession session,String ID_SEJARAHBIMAIN,String ID_PERMOHONANSIMATI,String skrinName,String mode, Map map_data, String label, String table_name,
			String field_main_PK,String value_main_PK,String id_perbicaraan,
			String column_name, String mandatory, String jenis_field, String showTitik,String maxLength,String flagUppercase,String defaultValue,double TURUTAN,Db db) throws Exception {
		column_name = column_name.toUpperCase().trim();
		String getValue = "";
		Map getChanges = null;
		if(map_data!=null)
		{
			//untuk kes kemaskini
			getValue = getValue(session,ID_PERMOHONANSIMATI, map_data,table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name,db);
			if(!skrinName.equals("keputusan") && !skrinName.equals("tukarpegawai") && !skrinName.equals("carianBicara"))
			{
				getChanges = getChanges(session,"",ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			}
		}
		else
		{
			//untuk kes tambah
			if(!skrinName.equals("keputusan") && !skrinName.equals("tukarpegawai") && !skrinName.equals("carianBicara"))
			{
				getChanges = getChanges(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			}
			if(getChanges==null)
			{
				getValue = defaultValue;
			}
		}


		String JENIS_AKTIVITI="";
		String VALUE_SEBELUM="";
		String KETERANGAN_SEBELUM="";
		String VALUE_SELEPAS="";
		String KETERANGAN_SELEPAS = "";
		boolean changes = false;
		if(getChanges!=null)
		{
			changes = true;
			JENIS_AKTIVITI = (String) getChanges.get("JENIS_AKTIVITI");
			VALUE_SEBELUM = (String) getChanges.get("VALUE_SEBELUM");
			KETERANGAN_SEBELUM = (String) getChanges.get("KETERANGAN_SEBELUM");
			VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
			KETERANGAN_SELEPAS = (String) getChanges.get("KETERANGAN_SELEPAS");
			getValue = VALUE_SELEPAS;
		}

		column_name = column_name.toUpperCase().trim();
		String html = "";
		if(!jenis_field.equals("hidden"))
		{
			html += "<tr id='row"+skrinName+column_name+"'>";
			html += "<td align='center' valign='top' >";
			if(mandatory.equals("Y") && mode.equals("edit"))
			{
				html += "<font color='red'>*</font>";
			}
			html +=	"</td>";
			html += "<td align='left' valign='top' ><span id='label"+skrinName+column_name+"' >";
			if(!label.equals(""))
			{
				html += label;
			}
			html +=	"</span></td>";
			html += "<td align='center' valign='top' >";
			if(showTitik.equals("Y"))
			{
				html += ":";
			}
			html +=	"</td>";
			html += "<td align='left' valign='top' >";

			if(mode.equals("view"))
			{
				if(flagUppercase.equals("Y"))
				{
					getValue = getValue.toUpperCase();
				}
				if(!getValue.equals(""))
				{
					html += getValue;
				}
				else
				{
					html += "-";
				}
				html += " <input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+getValue+"'  /> ";
			}
			else
			{
				String onBlurFunction = "";
				String strMaxLength = "";
				if(!maxLength.equals(""))
				{
					strMaxLength = "maxlength='"+maxLength+"'";
				}

				String styleUppercase = "";
				if(flagUppercase.equals("Y"))
				{
					styleUppercase = "text-transform:uppercase";
				}

				if(column_name.equals("TARIKH_LAHIR") && skrinName.equals("simati"))
				{
					onBlurFunction += "calculateUmurSimati('"+skrinName+"');";
				}
				else if(column_name.equals("TARIKH_MATI") && skrinName.equals("simati"))
				{
					onBlurFunction += "calculateUmurSimati('"+skrinName+"');";
				}
				else if(column_name.equals("TARIKH_LAHIR") && (skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi") || skrinName.equals("pemiutang")))
				{
					onBlurFunction += "calculateUmurByTarikh(this.value,'"+skrinName+"UMUR');";
				}

				html += "<input type='text' "+strMaxLength+" size = '10' style='"+styleUppercase+"' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' onBlur=\""+onBlurFunction+"\" value = '"+getValue+"'  />";
				html += " <a href=\"javascript:displayDatePicker('"+skrinName+column_name+"',false,'dmy');\"><img border=\"0\" src=\"../img/calendar.gif\"/></a>";
			}

			if(changes == true && JENIS_AKTIVITI.equals("UPDATE"))
			{
				html += showHTMLChanges(label, VALUE_SEBELUM);
			}

			html +=	"</td>";
			html += "</tr>";
		}
		else
		{
			html += "<input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+getValue+"'  />";
		}
		html += " <input type='hidden' name='"+skrinName+column_name+"_turutan' id='"+skrinName+column_name+"_turutan' value = '"+TURUTAN+"'  /> ";
		html += " <input type='hidden' name='"+skrinName+column_name+"_label' id='"+skrinName+column_name+"_label' value = '"+label+"'  /> ";
		return html;
	}


	public String setRowTextArea(HttpSession session,String ID_SEJARAHBIMAIN,String ID_PERMOHONANSIMATI,String skrinName,String mode, Map map_data, String label, String table_name,
			String field_main_PK,String value_main_PK,String id_perbicaraan,
			String column_name, String mandatory, String jenis_field, String showTitik,String maxLength,String flagUppercase,String defaultValue,double TURUTAN,Db db) throws Exception {
		column_name = column_name.toUpperCase().trim();

		myLogger.info("setRowTextArea >>> column_name : "+column_name+" table_name : "+table_name);

		String getValue = "";
		Map getChanges = null;
		if(map_data!=null)
		{
			//untuk kes kemaskini
			getValue = getValue(session,ID_PERMOHONANSIMATI, map_data,table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name,db);
			if(!skrinName.equals("keputusan") && !skrinName.equals("tukarpegawai") && !skrinName.equals("carianBicara"))
			{
				getChanges = getChanges(session,"",ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			}
		}
		else
		{
			//untuk kes tambah
			if(!skrinName.equals("keputusan") && !skrinName.equals("tukarpegawai") && !skrinName.equals("carianBicara"))
			{
				getChanges = getChanges(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			}

			if(getChanges==null)
			{
				getValue = defaultValue;
			}
		}

		String JENIS_AKTIVITI="";
		String VALUE_SEBELUM="";
		String KETERANGAN_SEBELUM="";
		String VALUE_SELEPAS="";
		String KETERANGAN_SELEPAS = "";
		boolean changes = false;
		if(getChanges!=null)
		{
			changes = true;
			JENIS_AKTIVITI = (String) getChanges.get("JENIS_AKTIVITI");
			VALUE_SEBELUM = (String) getChanges.get("VALUE_SEBELUM");
			KETERANGAN_SEBELUM = (String) getChanges.get("KETERANGAN_SEBELUM");
			VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
			KETERANGAN_SELEPAS = (String) getChanges.get("KETERANGAN_SELEPAS");
			getValue = VALUE_SELEPAS;
		}

		String syleTR = "";
		if(jenis_field.equals("hidden"))
		{
			syleTR = " style='display:none;' ";
		}

		String html = "";
		html += "<tr id='row"+skrinName+column_name+"' "+syleTR+" >";
		html += "<td align='center' valign='top' >";
		if(mandatory.equals("Y") && mode.equals("edit"))
		{
			html += "<font color='red'>*</font>";
		}
		html +=	"</td>";
		html += "<td align='left' valign='top' ><span id='label"+skrinName+column_name+"' >";
		if(!label.equals(""))
		{
			html += label;
		}
		html +=	"</span></td>";
		html += "<td align='center' valign='top' >";
		if(showTitik.equals("Y"))
		{
			html += ":";
		}
		html +=	"</td>";
		html += "<td align='left' valign='top' >";

		if(mode.equals("view"))
		{
			if((table_name.equals("TBLPPKPERINTAH")
					&& (column_name.equals("CATATAN")
							|| column_name.equals("CATATAN_KEPUTUSAN_PERBICARAAN")
							|| column_name.equals("INTRO_CATATAN")
							|| column_name.equals("CATATAN_DOCKIV")
							|| column_name.equals("CATATAN_PERINTAH_BI") || column_name.equals("SEBAB_TANGGUH")
							|| column_name.equals("KEPUTUSAN_MAHKAMAH") || column_name.equals("SEBAB_BATAL")))
							||
							(table_name.equals("TBLPPKBORANGJ")
									&& (column_name.equals("CATATAN1"))
									)
									||
							(table_name.equals("TBLPPKKOLATERALMST")
									&& (column_name.equals("SEBAB_PERTELINGKAHAN") || column_name.equals("CATATAN_KEPUTUSAN"))
									)				&& !getValue.equals("")
					)
			{
				html += "<div style=\"background-color:white;padding:10px;\"  id='divViewEditor"+skrinName+column_name+"' >";
			}


			if(flagUppercase.equals("Y"))
			{
				getValue = getValue.toUpperCase();
			}
			html += getValue.replace(fontSize, "");

			if(getValue.equals(""))
			{
				html += "<div id='divViewDefault"+skrinName+column_name+"'>-</div>";
			}


			if((table_name.equals("TBLPPKPERINTAH")
					&& (column_name.equals("CATATAN")
							|| column_name.equals("CATATAN_KEPUTUSAN_PERBICARAAN")
							|| column_name.equals("INTRO_CATATAN")
							|| column_name.equals("CATATAN_DOCKIV")
							|| column_name.equals("CATATAN_PERINTAH_BI") || column_name.equals("SEBAB_TANGGUH")
							|| column_name.equals("KEPUTUSAN_MAHKAMAH") || column_name.equals("SEBAB_BATAL")))
							||
							(table_name.equals("TBLPPKBORANGJ")
									&& (column_name.equals("CATATAN1"))
									)
									||
							(table_name.equals("TBLPPKKOLATERALMST")
									&& (column_name.equals("SEBAB_PERTELINGKAHAN") || column_name.equals("CATATAN_KEPUTUSAN"))
									&& !getValue.equals("")
									)
					)
			{
				html += "</div>";
			}


			//html += " <input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+getValue+"'  /> ";

			html += "<textarea name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' style='display:none;' >"+getValue+"</textarea>";
		}
		else
		{


			String styleUppercase = "";
			if(flagUppercase.equals("Y"))
			{
				styleUppercase = "text-transform:uppercase";
			}
			String strMaxLength = "";
			if(!maxLength.equals(""))
			{
				strMaxLength = "maxlength='"+maxLength+"'";
			}

			html += "<textarea rows='4' style='"+styleUppercase+"' "+strMaxLength+" spellcheck=\"false\" name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' ";
			if((table_name.equals("TBLPPKPERINTAH") && (column_name.equals("CATATAN")
					|| column_name.equals("CATATAN_KEPUTUSAN_PERBICARAAN")
					|| column_name.equals("INTRO_CATATAN")
					|| column_name.equals("CATATAN_DOCKIV")
					|| column_name.equals("CATATAN_PERINTAH_BI") || column_name.equals("SEBAB_TANGGUH") || column_name.equals("KEPUTUSAN_MAHKAMAH") || column_name.equals("SEBAB_BATAL")))
				|| (table_name.equals("TBLPPKBORANGJ")
						&& (column_name.equals("CATATAN1"))
						)
						|| (table_name.equals("TBLPPKKOLATERALMST")
						&& (column_name.equals("SEBAB_PERTELINGKAHAN") || column_name.equals("CATATAN_KEPUTUSAN"))
						)
			    )
			{
				html += "  placeholder=\"Masukkan Keterangan...\" style=\"width:100%;\"  " ;
			}
			else
			{
				html += " class='fullwidth_input'  ";
			}
			html += " >"+getValue
			//getValue.replace("<","&lt;").replace(">","&gt;").replace("\"","$quot;")
			+"</textarea>  " +
			//" <textarea>" +getValue+"</textarea>" +
					" ";
		}

		//special kes
		html += "<div id=\"locationreSetup"+skrinName+column_name+"\" ></div>";
		html += "<div id=\"dummyDivResetup"+skrinName+column_name+"\" style=\"display:none;\" >"+getValue+"</div>";
		html += "<div id=\"timer_"+skrinName+column_name+"\" align=\"right\" ></div>";

		if(changes == true && JENIS_AKTIVITI.equals("UPDATE"))
		{
			//if ada changes
			html += showHTMLChanges(label, VALUE_SEBELUM);
		}

		html +=	"</td>";
		html += "</tr>";
		html += " <input type='hidden' name='"+skrinName+column_name+"_turutan' id='"+skrinName+column_name+"_turutan' value = '"+TURUTAN+"'  /> ";
		html += " <input type='hidden' name='"+skrinName+column_name+"_label' id='"+skrinName+column_name+"_label' value = '"+label+"'  /> ";

		String onLoadFunction = "";
		if(
				mode.equals("edit") &&
				(table_name.equals("TBLPPKPERINTAH")
				&& (column_name.equals("CATATAN")
						|| column_name.equals("CATATAN_KEPUTUSAN_PERBICARAAN")
						|| column_name.equals("INTRO_CATATAN")
						|| column_name.equals("CATATAN_DOCKIV")
						|| column_name.equals("CATATAN_PERINTAH_BI") || column_name.equals("SEBAB_TANGGUH")
						|| column_name.equals("KEPUTUSAN_MAHKAMAH") || column_name.equals("SEBAB_BATAL")))
						||
						(table_name.equals("TBLPPKBORANGJ")
								&& (column_name.equals("CATATAN1"))
								)
								||
						(table_name.equals("TBLPPKKOLATERALMST")
								&& (column_name.equals("SEBAB_PERTELINGKAHAN") || column_name.equals("CATATAN_KEPUTUSAN"))
								)
				)
		{
			onLoadFunction += "editorKeputusan('"+skrinName+"','"+column_name+"','"+getValue+"'); reAssignFieldEditorContent('"+skrinName+column_name+"'); ";
			//onLoadFunction +' "$jquery("#"+skrinName+column_name+).data("wysihtml5").editor.setValue(document.getElementById("dummyDivResetup$FIELD_NAME").innerHTML);
			//if(table_name.equals("TBLPPKPERINTAH") && column_name.equals("CATATAN_PERINTAH_BI"))
			//{
				//onLoadFunction += "ajaxAutoReSetup('"+getValue+"','"+skrinName+column_name+"','"+id_perbicaraan+"'); ";
			//}
		}
		//else
		//{
			//special untuk view only tp boleh auto jana
			/*
			if(column_name.equals("CATATAN_PERINTAH_BI"))
			{
				//onLoadFunction += "editorKeputusan('"+skrinName+"','"+column_name+"'); ";
				onLoadFunction += "ajaxAutoReSetup('"+getValue+"','"+skrinName+column_name+"','"+id_perbicaraan+"'); ";
			}
			*/
		//}


		if(!onLoadFunction.equals(""))
		{
			html += "<script>"+onLoadFunction+"</script>";
		}
		return html;
	}

	//special kes
	public String setRowSelectParentWaris(HttpSession session,
			String value_main_PK,
			String ID_SEJARAHBIMAIN,
			String ID_PERMOHONANSIMATI,
			String skrinName,
			String command,
			String mode, Map map_data,
			String label,
			String ID_PERBICARAAN,
			String mandatory,
			String showTitik,
			String formName,
			String defaultValue,
			double TURUTAN,
			Db db) throws Exception {
		List listParentSimati = listParentSimati(session,ID_PERMOHONANSIMATI,ID_PERBICARAAN,skrinName,"",db);
		String ID_PARENTOB = "";
		String ID_OB = "";

		Map getChangesID_OB = null;
		Map getChangesID_PARENTOB = null;
		if(map_data!=null)
		{
			//untuk kes kemaskini
			ID_PARENTOB = getValue(session,ID_PERMOHONANSIMATI, map_data,"TBLPPKOBPERMOHONAN","ID_OBPERMOHONAN",value_main_PK,ID_PERBICARAAN,"ID_PARENTOB",db);
			getChangesID_PARENTOB = getChanges(session,"",ID_PERMOHONANSIMATI, "TBLPPKOBPERMOHONAN","ID_OBPERMOHONAN",value_main_PK,ID_PERBICARAAN,"ID_PARENTOB",db);
			ID_OB = getValue(session,ID_PERMOHONANSIMATI, map_data,"TBLPPKOBPERMOHONAN","ID_OBPERMOHONAN",value_main_PK,ID_PERBICARAAN,"ID_OB",db);
			getChangesID_OB = getChanges(session,"",ID_PERMOHONANSIMATI, "TBLPPKOBPERMOHONAN","ID_OBPERMOHONAN",value_main_PK,ID_PERBICARAAN,"ID_OB",db);
		}
		else
		{
			//untuk kes tambah
			getChangesID_PARENTOB = getChanges(session,"",ID_PERMOHONANSIMATI, "TBLPPKOBPERMOHONAN","ID_OBPERMOHONAN",value_main_PK,ID_PERBICARAAN,"ID_PARENTOB",db);
			if(getChangesID_PARENTOB==null)
			{
				ID_PARENTOB = "";
			}
			getChangesID_OB = getChanges(session,"",ID_PERMOHONANSIMATI, "TBLPPKOBPERMOHONAN","ID_OBPERMOHONAN",value_main_PK,ID_PERBICARAAN,"ID_OB",db);
			if(getChangesID_OB==null)
			{
				ID_OB = "";
			}
		}

		boolean changes = false;
		String JENIS_AKTIVITI_ID_PARENTOB="";
		String VALUE_SEBELUM_ID_PARENTOB="";
		String KETERANGAN_SEBELUM_ID_PARENTOB="";
		String VALUE_SELEPAS_ID_PARENTOB="";
		String KETERANGAN_SELEPAS_ID_PARENTOB = "";
		if(getChangesID_PARENTOB!=null)
		{
			JENIS_AKTIVITI_ID_PARENTOB = (String) getChangesID_PARENTOB.get("JENIS_AKTIVITI");
			VALUE_SEBELUM_ID_PARENTOB = (String) getChangesID_PARENTOB.get("VALUE_SEBELUM");
			KETERANGAN_SEBELUM_ID_PARENTOB = (String) getChangesID_PARENTOB.get("KETERANGAN_SEBELUM");
			VALUE_SELEPAS_ID_PARENTOB = (String) getChangesID_PARENTOB.get("VALUE_SELEPAS");
			KETERANGAN_SELEPAS_ID_PARENTOB = (String) getChangesID_PARENTOB.get("KETERANGAN_SELEPAS");
			VALUE_SELEPAS_ID_PARENTOB = (String) getChangesID_PARENTOB.get("VALUE_SELEPAS");
			ID_PARENTOB = VALUE_SELEPAS_ID_PARENTOB;
			changes = true;
		}
		String VALUE_SELEPAS_ID_OB="";
		if(getChangesID_OB!=null)
		{
			VALUE_SELEPAS_ID_OB = (String) getChangesID_OB.get("VALUE_SELEPAS");
			ID_OB = VALUE_SELEPAS_ID_OB;
		}


		/*
		Map getChangesHubungan = getChangesHubungan(session,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_OB, db);
		if(getChangesHubungan!=null && !ID_OB.equals(""))
		{
			String column_ID_PARENTOB = (String) getChangesHubungan.get("ID_PARENTOB");
			if(!column_ID_PARENTOB.equals(""))
			{
				myLogger.info(">>>>>> ADA PERUBAHAN ID_PAFRENTOB ");
				ID_PARENTOB = column_ID_PARENTOB;
			}
		}
		*/


		String html = "";
		if(listParentSimati.size()>0)
		{
			html += "<tr id='row"+skrinName+"ID_PARENTOB' >";
			html += "<td align='center' valign='top' >";
			if(mandatory.equals("Y") && mode.equals("edit"))
			{
				html += "<font color='red'>*</font>";
			}
			html +=	"</td>";
			html += "<td align='left' valign='top' ><span id='label"+skrinName+"ID_PARENTOB' >";
			if(!label.equals(""))
			{
				html += label;
			}
			html +=	"</span></td>";
			html += "<td align='center' valign='top' >";
			if(showTitik.equals("Y"))
			{
				html += ":";
			}
			html +=	"</td>";
			html += "<td align='left' valign='top' >";
			html += "<div id='div"+skrinName+"ID_PARENTOB' >";

			if(mode.equals("view"))
			{
				String namaParent = "-";
				if(!ID_PARENTOB.equals(""))
				{
					listParentSimati = listParentSimati(session,ID_PERMOHONANSIMATI,ID_PERBICARAAN,skrinName,ID_PARENTOB,db);
					if(listParentSimati.size()>0)
					{
						Map setupOB = (Map) listParentSimati.get(0);
						if(setupOB!=null)
						{
							namaParent = (String) setupOB.get("NAMA_OB");
						}
					}
				}
				html += namaParent;
				html += " <input type='hidden' name='"+skrinName+"ID_PARENTOB' id='"+skrinName+"ID_PARENTOB' value = '"+ID_PARENTOB+"'  /> ";
			}
			else
			{
				myLogger.info("listParentSimati : "+listParentSimati);
				String specialFunctionOnChange = "getSetLapis('divSetLapis"+skrinName+"','"+ID_PERMOHONANSIMATI+"','"+ID_PERBICARAAN+"',this.value,'"+skrinName+"');";
				html += "<div id=\"divSetLapis"+skrinName+"\" ></div>";
				html += "<select id='"+skrinName+"ID_PARENTOB' name='"+skrinName+"ID_PARENTOB' class='fullwidth_input'  onChange = \""+specialFunctionOnChange+"\"> ";
				html += "<option value='' >SILA PILIH</option>";
				if (listParentSimati.size() != 0) {
					for (int i = 0; i < listParentSimati.size(); i++) {
						Map map_column_name = (Map) listParentSimati.get(i);
						String id = (String) map_column_name.get("ID_OB");
						String keterangan = (String) map_column_name.get("NAMA_OB");
						myLogger.info("id :::::::::: "+id+" value : "+keterangan);
						String selected = "";
						//condition ni ada berkait dengan hubungan
						if(ID_PARENTOB.equals(id))
						{
							selected = "selected";
						}
						if(!ID_OB.equals(id))
						{
							html += "<option value='"+id+"' "+selected+" >"+keterangan+"</option>";
						}
					}
				}
				html += "</select>";
			}

			if(changes==true)
			{
				if(changes == true && JENIS_AKTIVITI_ID_PARENTOB.equals("UPDATE"))
				{
					html += showHTMLChanges(label, KETERANGAN_SEBELUM_ID_PARENTOB);
				}
			}

			html +=	"</td>";
			html += "</tr>";
		}
		else
		{
			html += " <input type='hidden' name='"+skrinName+"ID_PARENTOB' id='"+skrinName+"ID_PARENTOB' value = '"+ID_PARENTOB+"'  /> ";
		}
		html += " <input type='hidden' name='"+skrinName+"ID_PARENTOB_turutan' id='"+skrinName+"ID_PARENTOB_turutan' value = '"+TURUTAN+"'  /> ";
		html += " <input type='hidden' name='"+skrinName+"ID_PARENTOB_label' id='"+skrinName+"ID_PARENTOB_label' value = '"+label+"'  /> ";
		return html;
	}



	public String setRowSelect(HttpSession session,String ID_SEJARAHBIMAIN,String ID_PERMOHONANSIMATI,String skrinName,String command,String mode, Map map_data, String label, String table_name,
			String field_main_PK,String value_main_PK,String id_perbicaraan,
			String column_name, String mandatory, String jenis_field, String showTitik, String refTable, String field_PK_refTable,
			String field_KOD_refTable, String field_VALUE_refTable, String field_FK_refTable,String VALUE_FK_refTable,
			String divCall,String tableCall, String fieldtableCall, String field_FK_TableCall, String field_PK_TableCall, String field_KOD_TableCall, String field_VALUE_TableCall,
			String formName,String defaultValue,double TURUTAN,
			Db db) throws Exception {
		myLogger.info("setRowSelect");
		column_name = column_name.toUpperCase().trim();
		Map getChanges = null;
		String getValue = "";
		//special case
		String getID_BANDARHTA = "";
		Map getChangesID_BANDARHTA  = null;
		if(map_data!=null)
		{
			//untuk kes kemaskini
			getValue = getValue(session,ID_PERMOHONANSIMATI, map_data,table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			if(!skrinName.equals("keputusan") && !skrinName.equals("tukarpegawai") && !skrinName.equals("carianBicara"))
			{
				getChanges = getChanges(session,"",ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			}

			if(column_name.equals("ID_DAERAH") && (skrinName.equals("htaah") || skrinName.equals("htaahx")))
			{
				myLogger.info("MASUK >>>>>>>>>>>>>>>>>>> getID_BANDARHTA");
				getID_BANDARHTA  = getValue(session,ID_PERMOHONANSIMATI, map_data,table_name,field_main_PK,value_main_PK,id_perbicaraan,"ID_BANDARHTA", db);
				getChangesID_BANDARHTA = getChanges(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,"ID_BANDARHTA", db);
				if(getChangesID_BANDARHTA!=null)
				{
					getID_BANDARHTA = (String) getChangesID_BANDARHTA.get("VALUE_SELEPAS");
				}
				myLogger.info(getChangesID_BANDARHTA + ">>>>>>>>>>>>>>>>>>> getID_BANDARHTA : "+getID_BANDARHTA);
			}

		}
		else
		{
			//untuk kes tambah
			if(!skrinName.equals("keputusan") && !skrinName.equals("tukarpegawai") && !skrinName.equals("carianBicara"))
			{
				getChanges = getChanges(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			}
			if(getChanges==null)
			{
				getValue = defaultValue;
			}
		}

		String JENIS_AKTIVITI="";
		String VALUE_SEBELUM="";
		String KETERANGAN_SEBELUM="";
		String VALUE_SELEPAS="";
		String KETERANGAN_SELEPAS = "";
		boolean changes = false;
		myLogger.info(column_name +" getValue asal ::: "+getValue);
		if(getChanges!=null)
		{
			changes = true;
			JENIS_AKTIVITI = (String) getChanges.get("JENIS_AKTIVITI");
			VALUE_SEBELUM = (String) getChanges.get("VALUE_SEBELUM");
			KETERANGAN_SEBELUM = (String) getChanges.get("KETERANGAN_SEBELUM");
			VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
			KETERANGAN_SELEPAS = (String) getChanges.get("KETERANGAN_SELEPAS");
			getValue = VALUE_SELEPAS;
		}
		myLogger.info(column_name +" getValue lepas ::: "+getValue);

		String html = "";

		String styleTR = "";
		if(jenis_field.equals("hidden"))
		{
			styleTR = "display:none;";
		}

		html += "<tr id='row"+skrinName+column_name+"' style='"+styleTR+"' >";
		html += "<td align='center' valign='top' >";

		//html += mode + " -- " + mandatory;

		if(mandatory.equals("Y") && mode.equals("edit"))
		{
			html += "<font color='red'>*</font>";
		}
		html +=	"</td>";
		html += "<td align='left' valign='top' ><span id='label"+skrinName+column_name+"' >";
		if(!label.equals(""))
		{
			html += label;
		}
		html +=	"</span></td>";
		html += "<td align='center' valign='top' >";
		if(showTitik.equals("Y"))
		{
			html += ":";
		}
		html +=	"</td>";
		html += "<td align='left' valign='top' >";
		html += "<div id='div"+skrinName+column_name+"' >";
		if(refTable.equals(""))
		{
			html += hardCoderefDropDown(session,jenis_field,id_perbicaraan,skrinName,command,table_name,refTable,field_PK_refTable,field_KOD_refTable,field_VALUE_refTable,field_FK_refTable,VALUE_FK_refTable,column_name,mode,getValue,divCall,
					tableCall, fieldtableCall, field_FK_TableCall, field_PK_TableCall, field_KOD_TableCall, field_VALUE_TableCall,
					formName,db);
		}
		else
		{
			myLogger.info("refDropDown >>>>>>>> "+column_name +" getValue lepas ::: "+getValue);
			html += refDropDown(session,jenis_field,id_perbicaraan,skrinName,command,table_name,refTable,field_PK_refTable,field_KOD_refTable,field_VALUE_refTable,field_FK_refTable,VALUE_FK_refTable,column_name,mode,getValue,divCall,
					tableCall, fieldtableCall, field_FK_TableCall, field_PK_TableCall, field_KOD_TableCall, field_VALUE_TableCall,
					formName,map_data,getID_BANDARHTA,db);
		}
		html += "</div>";
		if(changes == true && JENIS_AKTIVITI.equals("UPDATE"))
		{
			//if ada changes
			boolean showHistory = true;
			if(column_name.equals("JENIS_HTA") && VALUE_SEBELUM.equals(VALUE_SELEPAS))
			{
				showHistory = false;
			}

			if(showHistory == true)
			{
				html += showHTMLChanges(label, KETERANGAN_SEBELUM);
			}
		}
		html +=	"</td>";
		html += "</tr>";
		html += " <input type='hidden' name='"+skrinName+column_name+"_turutan' id='"+skrinName+column_name+"_turutan' value = '"+TURUTAN+"'  /> ";
		html += " <input type='hidden' name='"+skrinName+column_name+"_label' id='"+skrinName+column_name+"_label' value = '"+label+"'  /> ";

		return html;
	}

	public String hardCodeDisplay(String table_name,String column_name,String value)
	{
		String valueDisplay = "";
		if(column_name.equals("JANTINA"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "LELAKI";
			}
			else if(value.equals("2"))
			{
				valueDisplay += "PEREMPUAN";
			}

		}
		else if(column_name.equals("STATUS_TUKARPEGAWAI"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "PERMOHONAN BARU";
			}
			else if(value.equals("2"))
			{
				valueDisplay += "LULUS";
			}
			else if(value.equals("3"))
			{
				valueDisplay += "TOLAK";
			}
		}
		else if(column_name.equals("JENIS_HTA"))
		{
			if(value.equals("Y"))
			{
				valueDisplay += "HARTA TAK ALIH (ADA HAKMILIK)";
			}
			else if(value.equals("T"))
			{
				valueDisplay += "HARTA TAK ALIH (TIADA HAKMILIK)";
			}
		}
		else if(column_name.equals("FLAG_KATEGORI_HTA"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "PERJANJIAN JUAL BELI";
			}
			else if(value.equals("2"))
			{
				valueDisplay += "PEGANGAN DI BAWAH AKTA TANAH (KAWASAN PENEMPATAN BERKELOMPOK 1960)";
			}
			else if(value.equals("3"))
			{
				valueDisplay += "KEPENTINGAN LAIN - LAIN";
			}
		}
		else if(column_name.equals("JENIS_KP"))
		{
			if(value.equals("5"))
			{
				valueDisplay += "TENTERA";
			}
			else if(value.equals("6"))
			{
				valueDisplay += "POLIS";
			}
			else if(value.equals("4"))
			{
				valueDisplay += "PASSPORT";
			}
			else if(value.equals("7"))
			{
				valueDisplay += "LAIN-LAIN";
			}
		}
		else if(column_name.equals("JENIS_TNH"))
		{
			if(value.equals("0"))
			{
				valueDisplay += "TIDAK DINYATAKAN";
			}
			else if(value.equals("1"))
			{
				valueDisplay += "TANAH RIZAB";
			}
			else if(value.equals("2"))
			{
				valueDisplay += "TANAH ADAT";
			}
			else if(value.equals("3"))
			{
				valueDisplay += "TANAH GSA";
			}
			else if(value.equals("4"))
			{
				valueDisplay += "BUKAN TANAH GSA";
			}
		}
		else if(column_name.equals("JENIS_WAKTU_MATI") || column_name.equals("JENIS_WAKTU_KEMATIAN"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "PAGI";
			}
			else if(value.equals("2"))
			{
				valueDisplay += "TENGAHARI";
			}
			else if(value.equals("3"))
			{
				valueDisplay += "PETANG";
			}
			else if(value.equals("3"))
			{
				valueDisplay += "MALAM";
			}
		}
		else if(column_name.equals("STATUS_PEMOHON") || column_name.equals("JENIS_PEMIUTANG") || column_name.equals("JENIS_PENGHUTANG"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "AGENSI";
			}
			else if(value.equals("2"))
			{
				valueDisplay += "INDIVIDU";
			}
		}
		else if(column_name.equals("STATUS_OB"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "DEWASA / WARAS";
			}
			else if(value.equals("2"))
			{
				valueDisplay += "BELUM DEWASA";
			}
			else if(value.equals("3"))
			{
				valueDisplay += "HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN";
			}
			else if(value.equals("4"))
			{
				valueDisplay += "TIDAK SEMPURNA AKAL";
			}
		}
		else if(column_name.equals("STATUS_HIDUP"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "YA";
			}
			else
			{
				valueDisplay += "TIDAK";
			}
		}
		else if(column_name.equals("FLAG_JENIS_KEPUTUSAN"))
		{
			if(value.equals("0"))
			{
				valueDisplay += "SELESAI";
			}
			else if(value.equals("1"))
			{
				valueDisplay += "TANGGUH";
			}
			else if(value.equals("2"))
			{
				valueDisplay += "BATAL";
			}
		}
		else if(column_name.equals("JENIS_KELUAR_PERINTAH"))
		{
			if(value.equals("PT")){
				valueDisplay += "PENTADBIR TANAH";
			}
			else if(value.equals("PD")){
				valueDisplay += "PEGAWAI DAERAH";
			}
			else if(value.equals("PDS")){
				valueDisplay += "PENTADBIR DAERAH SARAWAK";
			}
		}
		else if(column_name.equals("FLAG_TANGGUH"))
		{
			if(value.equals("1")){
				valueDisplay += "PEMOHON / WARIS TIDAK HADIR";
			}
			else if(value.equals("2")){
				valueDisplay += "SENARAI WARIS TIDAK LENGKAP";
			}
			else if(value.equals("3")){
				valueDisplay += "MENUNGGU KEPUTUSAN RUJUKAN MAHKAMAH SYARIAH";
			}
			else if(value.equals("4")){
				valueDisplay += "BUKTI KEMATIAN TIDAK LENGKAP";
			}
			else if(value.equals("5")){
				valueDisplay += "MENUNGGU KEPUTUSAN RUJUKAN KEPADA RULER OF THE STATES ATAU MAHKAMAH TINGGI";
			}
			else if(value.equals("6")){
				valueDisplay += "PERTELINGKAHAN KOLATERAL";
			}
			else if(value.equals("7")){
				valueDisplay += "MENUNGGU SIJIL FARAID";
			}
			else if(value.equals("8")){
				valueDisplay += "MENUNGGU SURAT AKUAN PERSETUJUAN";
			}
			else if(value.equals("9")){
				valueDisplay += "SEBAB-SEBAB LAIN";
			}

		}
		else if(column_name.equals("FLAG_BATAL"))
		{
			if(value.equals("1")){
				valueDisplay += "PINDAH KE MAHKAMAH TINGGI KERANA ADA WASIAT";
			}
			else if(value.equals("2")){
				valueDisplay += "TIDAK HADIR SETELAH DIPANGGIL 3 KALI";
			}
			else if(value.equals("3")){
				valueDisplay += "ATAS PERMINTAAN PEMOHON";
			}
			else if(value.equals("4")){
				valueDisplay += "PINDAH KE MAHKAMAH TINGGI KERANA HARTA MELEBIHI RM 2 JUTA";
			}
			else if(value.equals("5")){
				valueDisplay += "SEBAB-SEBAB LAIN";
			}

		}
		else if(column_name.equals("JENIS_RUJUKAN") && table_name.equals("TBLPPKBORANGJ"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "MAHKAMAH TINGGI";

			}
			else if(value.equals("2"))
			{
				valueDisplay += "RULER OF THE STATE";
			}
		}
		else if(column_name.equals("FLAG_RUJUKAN") && table_name.equals("TBLPPKBORANGJ"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "MAHKAMAH SYARIAH";

			}
			else if(value.equals("2"))
			{
				valueDisplay += "PEJABAT MUFTI";
			}
		}
		else if(column_name.equals("CHECK_KIV"))
		{
			myLogger.info(" valueCHECK_KIV :"+ value);
			if(value.equals("1"))
			{
				valueDisplay = "KIV";
			}
			else if(value.equals("0"))
			{
				valueDisplay = "DOKUMEN TELAH DIKEMUKAKAN";
			}
			else if(value.equals("3")) //arief add
			{
				valueDisplay = "DOKUMEN GAGAL DIKEMUKAKAN";
			}
		}
		else if(column_name.equals("FLAG_DAFTAR"))
		{
			if(value.equals("2"))
			{
				valueDisplay += "PERBICARAAN";
			}
			else if(value.equals("1"))
			{
				valueDisplay += "PENDAFTARAN";
			}
		}
		else if(column_name.equals("JENIS_WARGA"))
		{
			if(value.equals("1"))
			{
				valueDisplay += "WARGANEGARA";
			}
			else if(value.equals("2"))
			{
				valueDisplay += "BUKAN WARGANEGARA";
			}
			else if(value.equals("3"))
			{
				valueDisplay += "PEMASTAUTIN TETAP";
			}

		}
		else if(column_name.equals("FLAG_BANTAHAN"))
		{
			if(value.equals("Y"))
			{
				valueDisplay += "ADA";
			}
			else
			{
				valueDisplay += "TIADA";
			}
		}
		return valueDisplay;
	}



	public String hardCoderefDropDown(HttpSession session,String jenis_field,String ID_PERBICARAAN,String skrinName,String command,String table_name,String refTable,String PK_refTable,
			String field_KOD_refTable, String field_VALUE_refTable, String field_FK_refTable,String VALUE_FK_refTable,
			String column_name,String mode,
			String value, String divCall,String tableCall, String fieldtableCall, String field_FK_TableCall, String field_PK_TableCall, String field_KOD_TableCall, String field_VALUE_TableCall,
			String formName, Db db) throws Exception {
		column_name = column_name.toUpperCase().trim();
		String html = "";
		String valueDisplay = "";

		String ID_PERMOHONANSIMATI = "";
		String ID_PERMOHONAN = "";
		String ID_PEMOHON = "";
		String ID_SIMATI = "";

		Map mainID = mainID(session, ID_PERBICARAAN, db);
		if(mainID != null)
		{
			ID_PERMOHONANSIMATI = (String)mainID.get("ID_PERMOHONANSIMATI");
			ID_PERMOHONAN = (String)mainID.get("ID_PERMOHONAN");
			ID_PEMOHON = (String)mainID.get("ID_PEMOHON");
			ID_SIMATI = (String)mainID.get("ID_SIMATI");
		}

		if(jenis_field.equals("hidden"))
		{

			html += " <input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+value+"'  /> ";
		}
		else
		{
			if(mode.equals("view")
					|| (!value.equals("") && column_name.equals("FLAG_JENIS_KEPUTUSAN") && table_name.equals("TBLPPKPERINTAH"))
					//special condition, bila keputusan perintah da ada, dah tak bleh edit
					)
			{
				valueDisplay = hardCodeDisplay(table_name,column_name,value);
				html += valueDisplay;

				if(valueDisplay.equals(""))
				{

						html += "-";
				}

				html += "<input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+value+"'  /> ";


				//custom, bila load tr status_pemohon
				//hide tr related to dengan agensi or individu
				//untuk view/edit
				if(column_name.equals("STATUS_PEMOHON") || column_name.equals("JENIS_PEMIUTANG") || column_name.equals("JENIS_PENGHUTANG"))
				{
					html += "<script>$jquery(document).ready(function (){showTrIndividuAgensi('"+skrinName+"','"+mode+"');});</script>";
				}
			}
			else
			{

				String specialFunctionOnChange = "";
				if(column_name.equals("STATUS_PEMOHON") || column_name.equals("JENIS_PEMIUTANG") || column_name.equals("JENIS_PENGHUTANG"))
				{
					//custom, bila onchange status_pemohon
					//hide tr related to dengan agensi or individu
					//untuk edit
					specialFunctionOnChange += "showTrIndividuAgensi('"+skrinName+"','"+mode+"');";
				}
				else if(column_name.equals("STATUS_OB"))
				{
					//custom, bila onchange status_pemohon
					//hide tr related to dengan agensi or individu
					//untuk edit
					specialFunctionOnChange += "showTDK('"+skrinName+"','"+mode+"',this.value);";
				}
				else if(column_name.equals("STATUS_HIDUP"))
				{
					specialFunctionOnChange += "showMaklumatMatiWaris(this.value,'"+skrinName+"','"+mode+"');";
				}
				else if(column_name.equals("FLAG_BANTAHAN"))
				{
					specialFunctionOnChange += "showKeteranganBantahan(this.value,'"+skrinName+"','"+mode+"');";
				}
				else if(column_name.equals("JENIS_HTA"))
				{
					specialFunctionOnChange += "pilihJenisHTA(this.value,'"+skrinName+"','"+mode+"','onChange');";
				}
				else if(column_name.equals("FLAG_KATEGORI_HTA"))
				{
					specialFunctionOnChange += "pilihJenisKategoriHTA(this.value,'"+skrinName+"','"+mode+"');";
				}
				else if(column_name.equals("FLAG_JENIS_KEPUTUSAN") && table_name.equals("TBLPPKPERINTAH"))
				{
					specialFunctionOnChange += "selectKeputusanPerintah(this.value,'"+mode+"','"+skrinName+"'," +
							"'"+ID_SIMATI+"','"+ID_PERMOHONAN+"','"+ID_PERBICARAAN+"','"+ID_PERMOHONANSIMATI+"');";
				}
				else if(column_name.equals("FLAG_TANGGUH") && table_name.equals("TBLPPKPERINTAH"))
				{
					specialFunctionOnChange += "selectJenisTangguh(this.value,'"+mode+"','"+skrinName+"'," +
							"'"+ID_SIMATI+"','"+ID_PERMOHONAN+"','"+ID_PERBICARAAN+"','"+ID_PERMOHONANSIMATI+"');";
				}
				else if(column_name.equals("FLAG_BATAL") && table_name.equals("TBLPPKPERINTAH"))
				{
					specialFunctionOnChange += "selectJenisBatal(this.value,'"+mode+"','"+skrinName+"'," +
							"'"+ID_SIMATI+"','"+ID_PERMOHONAN+"','"+ID_PERBICARAAN+"','"+ID_PERMOHONANSIMATI+"');";
				}
				else if(column_name.equals("CHECK_KIV"))
				{
					specialFunctionOnChange += "showMaklumatKIVPerintah(this.value,'"+skrinName+"','"+mode+"');";
					specialFunctionOnChange += "showHideTextDocKembali(this.value,'"+skrinName+"','CATATAN_DOCKIV');";
				}
				else if(column_name.equals("JENIS_RUJUKAN") && table_name.equals("TBLPPKBORANGJ"))
				{
					specialFunctionOnChange += "selectJenisRujukan(this.value,'"+skrinName+"','"+mode+"');";
					specialFunctionOnChange += "resetMapPejabatMahkamahByJenisRujukan(this.value,'"+skrinName+"','"+mode+"')";
				}
				else if(column_name.equals("FLAG_RUJUKAN") && table_name.equals("TBLPPKBORANGJ"))
				{
					specialFunctionOnChange += "selectFlagRujukan(this.value,'"+skrinName+"','"+mode+"');";
					specialFunctionOnChange += "kosongFieldPejabat('"+skrinName+"','"+mode+"');";
				}

				//showMaklumatMatiWaris(status,skrinName,mode)

				String ajaxCall = "";
				if(!divCall.equals(""))
				{
					ajaxCall = "doDivAjaxCall"+formName+"('"+divCall+"','ajaxCallDropDown'," +
							"'refTable="+tableCall+"&PK_refTable="+field_PK_TableCall+"&field_KOD_refTable="+field_KOD_TableCall+
							"&field_VALUE_refTable="+field_VALUE_TableCall+"&field_FK_refTable="+field_FK_TableCall+
							"&VALUE_FK_refTable="+skrinName+column_name+"&column_name="+fieldtableCall+"&mainTable="+table_name+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&skrinName="+skrinName+"');";
				}

				String seletect_SilaPilih = "";
				String seletect0 = "";
				String seletect1 = "";
				String seletect2 = "";
				String seletect3 = "";
				String seletect4 = "";
				String seletect5 = "";
				String seletect6 = "";
				String seletect7 = "";
				String seletect8 = "";
				String seletect9 = "";
				String seletect10 = "";
				String showDropDown = "Y";
				//special kes untuk jenis pemohon bila pilih taraf kepentingan
				if(column_name.equals("STATUS_PEMOHON") || column_name.equals("JENIS_PEMIUTANG"))
				{
					if(VALUE_FK_refTable.equals("1")||VALUE_FK_refTable.equals("3")||VALUE_FK_refTable.equals("5")||VALUE_FK_refTable.equals("6")
							||VALUE_FK_refTable.equals("7")
							||VALUE_FK_refTable.equals("8")||VALUE_FK_refTable.equals("9")||VALUE_FK_refTable.equals("10")||VALUE_FK_refTable.equals("11")||VALUE_FK_refTable.equals("12")
							||VALUE_FK_refTable.equals("13")||VALUE_FK_refTable.equals("14")||VALUE_FK_refTable.equals("19")||VALUE_FK_refTable.equals("16")
							||VALUE_FK_refTable.equals("17")||VALUE_FK_refTable.equals("18"))
					{
						if(VALUE_FK_refTable.equals("6") || VALUE_FK_refTable.equals("8"))
						{
							html += "AGENSI";
							value = "1";
						}
						else
						{
							html += "INDIVIDU";
							value = "2";
						}
						html += "<input type='hidden' id='"+skrinName+column_name+"' name='"+skrinName+column_name+"' value='"+value+"'>";
						showDropDown = "N";
					}
				}
				else if(column_name.equals("FLAG_DAFTAR"))
				{
					if(value.equals("2"))
					{
						html += "PERBICARAAN";
					}
					else if(value.equals("1"))
					{
						html += "PENDAFTARAN";
					}
					html += "<input type='hidden' id='"+skrinName+column_name+"' name='"+skrinName+column_name+"' value='"+value+"'>";
					showDropDown = "N";
				}
				if(showDropDown.equals("Y"))
				{
					html +=
							//value + " -- "+column_name+ " :::::::::: " +
									" <select id='"+skrinName+column_name+"' name='"+skrinName+column_name+"' class='fullwidth_input' onChange = \""+specialFunctionOnChange+ajaxCall+"\" >";
					if(column_name.equals("JANTINA"))
					{
						if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("2"))
						{
							seletect2 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >LELAKI</option>";
						html += "<option value='2' "+seletect2+" >PEREMPUAN</option>";
					}
					else if(column_name.equals("JENIS_KP"))
					{
						if(value.equals("4"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("5"))
						{
							seletect2 = "selected";
						}
						else if(value.equals("6"))
						{
							seletect3 = "selected";
						}
						else if(value.equals("7"))
						{
							seletect4 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='0' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='4' "+seletect1+" >PASSPORT</option>";
						html += "<option value='5' "+seletect2+" >TENTERA</option>";
						html += "<option value='6' "+seletect3+" >POLIS</option>";
						html += "<option value='7' "+seletect4+" >LAIN-LAIN</option>";
					}
					else if(column_name.equals("JENIS_WARGA"))
					{
						if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("2"))
						{
							seletect2 = "selected";
						}
						else if(value.equals("3"))
						{
							seletect3 = "selected";
						}
						else
						{
							seletect1 = "selected";
						}
						html += "<option value='1' "+seletect1+" >WARGANEGARA</option>";
						html += "<option value='2' "+seletect2+" >BUKAN WARGANEGARA</option>";
						html += "<option value='3' "+seletect3+" >PEMASTAUTIN TETAP</option>";
					}
					else if(column_name.equals("JENIS_TNH"))
					{
						if(value.equals("0"))
						{
							seletect0 = "selected";
						}
						else if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("2"))
						{
							seletect2 = "selected";
						}
						else if(value.equals("3"))
						{
							seletect3 = "selected";
						}
						else if(value.equals("4"))
						{
							seletect4 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='0' "+seletect0+" >TIDAK DINYATAKAN</option>";
						if(value.equals("1") || value.equals("2"))
						{
							html += "<option value='1' "+seletect1+" >TANAH RIZAB</option>";
							html += "<option value='2' "+seletect2+" >TANAH ADAT</option>";
						}
						html += "<option value='3' "+seletect3+" >TANAH GSA</option>";
						html += "<option value='4' "+seletect4+" >BUKAN TANAH GSA</option>";
					}
					else if(column_name.equals("JENIS_WAKTU_MATI") || column_name.equals("JENIS_WAKTU_KEMATIAN"))
					{
						if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("2"))
						{
							seletect2 = "selected";
						}
						else if(value.equals("3"))
						{
							seletect3 = "selected";
						}
						else if(value.equals("4"))
						{
							seletect4 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >PAGI</option>";
						html += "<option value='2' "+seletect2+" >TENGAHARI</option>";
						html += "<option value='3' "+seletect3+" >PETANG</option>";
						html += "<option value='4' "+seletect4+" >MALAM</option>";
					}
					else if(column_name.equals("FLAG_BANTAHAN"))
					{
						if(value.equals("Y"))
						{
							seletect1 = "selected";
						}
						else if(value.equals(""))
						{
							seletect2 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='Y' "+seletect1+" >ADA</option>";
						html += "<option value='' "+seletect2+" >TIADA</option>";
					}
					else if(column_name.equals("STATUS_PEMOHON") || column_name.equals("JENIS_PEMIUTANG")  || column_name.equals("JENIS_PENGHUTANG"))
					{
						if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("2"))
						{
							seletect2 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >AGENSI</option>";
						html += "<option value='2' "+seletect2+" >INDIVIDU</option>";
					}
					else if(column_name.equals("STATUS_TUKARPEGAWAI"))
					{
						if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("2"))
						{
							seletect2 = "selected";
						}
						else if(value.equals("3"))
						{
							seletect3 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >PERMOHONAN BARU</option>";
						html += "<option value='2' "+seletect2+" >LULUS</option>";
						html += "<option value='3' "+seletect3+" >TOLAK</option>";
					}
					//STATUS_TUKARPEGAWAI
					else if(column_name.equals("JENIS_KELUAR_PERINTAH") && table_name.equals("TBLPPKPERINTAH"))
					{
						if(value.equals("PT")){seletect1 = "selected";}
						else if(value.equals("PD")){seletect2 = "selected";}
						else if(value.equals("PDS")){seletect3 = "selected";}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='PT' "+seletect1+" >PENTADBIR TANAH</option>";
						html += "<option value='PD' "+seletect2+" >PEGAWAI DAERAH</option>";
						html += "<option value='PDS' "+seletect3+" >PENTADBIR DAERAH SARAWAK</option>";
					}
					else if(column_name.equals("FLAG_TANGGUH") && table_name.equals("TBLPPKPERINTAH"))
					{
						if(value.equals("1")){seletect1 = "selected";}
						else if(value.equals("2")){seletect2 = "selected";}
						else if(value.equals("3")){seletect3 = "selected";}
						else if(value.equals("4")){seletect4 = "selected";}
						else if(value.equals("5")){seletect5 = "selected";}
						else if(value.equals("6")){seletect6 = "selected";}
						else if(value.equals("7")){seletect7 = "selected";}
						else if(value.equals("8")){seletect8 = "selected";}
						else if(value.equals("9")){seletect9 = "selected";}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >PEMOHON / WARIS TIDAK HADIR</option>";
						html += "<option value='2' "+seletect2+" >SENARAI WARIS TIDAK LENGKAP</option>";
						html += "<option value='3' "+seletect3+" >MENUNGGU KEPUTUSAN RUJUKAN MAHKAMAH SYARIAH</option>";
						html += "<option value='4' "+seletect4+" >BUKTI KEMATIAN TIDAK LENGKAP</option>";
						html += "<option value='5' "+seletect5+" >MENUNGGU KEPUTUSAN RUJUKAN KEPADA RULER OF THE STATES ATAU MAHKAMAH TINGGI</option>";
						html += "<option value='6' "+seletect6+" >PERTELINGKAHAN KOLATERAL</option>";
						html += "<option value='7' "+seletect7+" >MENUNGGU SIJIL FARAID</option>";
						html += "<option value='8' "+seletect8+" >MENUNGGU SURAT AKUAN PERSETUJUAN</option>";
						html += "<option value='9' "+seletect9+" >SEBAB-SEBAB LAIN</option>";
					}
					else if(column_name.equals("JENIS_RUJUKAN") && table_name.equals("TBLPPKBORANGJ"))
					{
						if(value.equals("1")){seletect1 = "selected";}
						else if(value.equals("2")){seletect2 = "selected";}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >MAHKAMAH TINGGI</option>";
						html += "<option value='2' "+seletect2+" >RULER OF THE STATE</option>";
					}
					else if(column_name.equals("FLAG_RUJUKAN") && table_name.equals("TBLPPKBORANGJ"))
					{
						if(value.equals("1")){seletect1 = "selected";}
						else if(value.equals("2")){seletect2 = "selected";}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >MAHKAMAH SYARIAH</option>";
						html += "<option value='2' "+seletect2+" >PEJABAT MUFTI</option>";
					}
					else if(column_name.equals("FLAG_BATAL") && table_name.equals("TBLPPKPERINTAH"))
					{
						if(value.equals("1")){seletect1 = "selected";}
						else if(value.equals("2")){seletect2 = "selected";}
						else if(value.equals("3")){seletect3 = "selected";}
						else if(value.equals("4")){seletect4 = "selected";}
						else if(value.equals("5")){seletect5 = "selected";}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >PINDAH KE MAHKAMAH TINGGI KERANA ADA WASIAT</option>";
						html += "<option value='2' "+seletect2+" >TIDAK HADIR SETELAH DIPANGGIL 3 KALI</option>";
						html += "<option value='3' "+seletect3+" >ATAS PERMINTAAN PEMOHON</option>";
						html += "<option value='4' "+seletect4+" >PINDAH KE MAHKAMAH TINGGI KERANA HARTA MELEBIHI RM 2 JUTA</option>";
						html += "<option value='5' "+seletect5+" >SEBAB-SEBAB LAIN</option>";
					}
					else if(column_name.equals("STATUS_PERBICARAAN") || (column_name.equals("FLAG_JENIS_KEPUTUSAN") && table_name.equals("TBLPPKPERINTAH")))
					{
						if(value.equals("0"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("1"))
						{
							seletect2 = "selected";
						}
						else if(value.equals("2"))
						{
							seletect3 = "selected";
						}
						else if(value.equals("99") && column_name.equals("STATUS_PERBICARAAN"))
						{
							seletect4 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='0' "+seletect1+" >SELESAI</option>";
						html += "<option value='1' "+seletect2+" >TANGGUH</option>";
						html += "<option value='2' "+seletect3+" >BATAL</option>";
						if(column_name.equals("STATUS_PERBICARAAN"))
						{
							html += "<option value='99' "+seletect4+" >PROSES PERBICARAAN</option>";
						}
					}
					else if(column_name.equals("CHECK_KIV"))
					{
						myLogger.info(" valueCHECK_KIV :"+ value);
						if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("0"))
						{
							seletect2 = "selected";
						}
						else if(value.equals("3")) //arief add
						{
							seletect3 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >KIV</option>";
						html += "<option value='0' "+seletect2+" >DOKUMEN TELAH DIKEMUKAKAN</option>";
						html += "<option value='3' "+seletect3+" >DOKUMEN GAGAL DIKEMUKAKAN</option>"; //arief add
					}
					else if(column_name.equals("STATUS_OB"))
					{
						if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("2"))
						{
							seletect2 = "selected";
						}
						else if(value.equals("3"))
						{
							seletect3 = "selected";
						}
						else if(value.equals("4"))
						{
							seletect4 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >DEWASA / WARAS</option>";
						html += "<option value='2' "+seletect2+" >BELUM DEWASA</option>";
						html += "<option value='3' "+seletect3+" >HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN</option>";
						html += "<option value='4' "+seletect4+" >TIDAK SEMPURNA AKAL</option>";
					}
					else if(column_name.equals("STATUS_HIDUP"))
					{
						if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='0' "+seletect_SilaPilih+" >TIDAK</option>";
						html += "<option value='1' "+seletect1+" >YA</option>";
					}
					else if(column_name.equals("FLAG_DAFTAR"))
					{
						if(value.equals("2"))
						{
							seletect1 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='1' "+seletect_SilaPilih+" >PENDAFTARAN</option>";
						html += "<option value='2' "+seletect2+" >PERBICARAAN</option>";
					}
					else if(column_name.equals("JENIS_HTA"))
					{
						if(value.equals("Y"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("T"))
						{
							seletect2 = "selected";
						}
						html += "<option value='Y' "+seletect1+" >HARTA TAK ALIH (ADA HAKMILIK)</option>";
						html += "<option value='T' "+seletect2+" >HARTA TAK ALIH (TIADA HAKMILIK)</option>";
					}
					else if(column_name.equals("FLAG_KATEGORI_HTA"))
					{
						if(value.equals("1"))
						{
							seletect1 = "selected";
						}
						else if(value.equals("2"))
						{
							seletect2 = "selected";
						}
						else if(value.equals("3"))
						{
							seletect3 = "selected";
						}
						else
						{
							seletect_SilaPilih = "selected";
						}
						html += "<option value='' "+seletect_SilaPilih+" >SILA PILIH</option>";
						html += "<option value='1' "+seletect1+" >PERJANJIAN JUAL BELI</option>";
						html += "<option value='2' "+seletect2+" >PEGANGAN DI BAWAH AKTA TANAH (KAWASAN PENEMPATAN BERKELOMPOK 1960)</option>";
						html += "<option value='3' "+seletect3+" >KEPENTINGAN LAIN - LAIN</option>";
					}
					html += "</select>";
				}

			}
		}

		//call function javascript after load field


		//custom, bila load tr status_pemohon
		//ajax call dropdown pejabat base on ID_TARAFKPTG
		//untuk view
		html += "<script>$jquery(document).ready(function (){ ";
		if(column_name.equals("STATUS_HIDUP"))
		{
			html += "showMaklumatMatiWaris('"+value+"','"+skrinName+"','"+mode+"');";
		}
		else if(column_name.equals("STATUS_PEMOHON") || column_name.equals("JENIS_PEMIUTANG") || column_name.equals("JENIS_PENGHUTANG"))
		{
			if(command.equals("ajaxCallDropDown"))
			{
				html += "reloadPejabat('"+table_name+"','"+skrinName+"','"+mode+"','"+ID_PERBICARAAN+"');";
			}
			else
			{
				html += "showTrIndividuAgensi('"+skrinName+"','"+mode+"');";
			}
		}
		else if(column_name.equals("JENIS_HTA"))
		{
			html += "pilihJenisHTA('"+value+"','"+skrinName+"','"+mode+"','onLoad');";
		}
		else if(column_name.equals("FLAG_KATEGORI_HTA"))
		{
			html += "pilihJenisKategoriHTA('"+value+"','"+skrinName+"','"+mode+"');";
		}
		else if(column_name.equals("FLAG_BANTAHAN"))
		{
			html += "showKeteranganBantahan('"+value+"','"+skrinName+"','"+mode+"');";
		}
		else if(column_name.equals("FLAG_JENIS_KEPUTUSAN") && table_name.equals("TBLPPKPERINTAH"))
		{

			html += "selectKeputusanPerintah('"+value+"','"+mode+"','"+skrinName+"'," +
					"'"+ID_SIMATI+"','"+ID_PERMOHONAN+"','"+ID_PERBICARAAN+"','"+ID_PERMOHONANSIMATI+"','"+command+"');";
			if(value.equals(""))
			{
				html += " $jquery('#view_keputusan').scrollView(); ";
			}

		}
		else if(column_name.equals("FLAG_TANGGUH") && table_name.equals("TBLPPKPERINTAH"))
		{
			html += "selectJenisTangguh('"+value+"','"+mode+"','"+skrinName+"'," +
					"'"+ID_SIMATI+"','"+ID_PERMOHONAN+"','"+ID_PERBICARAAN+"','"+ID_PERMOHONANSIMATI+"');";
		}
		else if(column_name.equals("FLAG_BATAL") && table_name.equals("TBLPPKPERINTAH"))
		{
			html += "selectJenisBatal('"+value+"','"+mode+"','"+skrinName+"'," +
					"'"+ID_SIMATI+"','"+ID_PERMOHONAN+"','"+ID_PERBICARAAN+"','"+ID_PERMOHONANSIMATI+"');";
		}
		else if(column_name.equals("CHECK_KIV"))
		{
			html += "showMaklumatKIVPerintah('"+value+"','"+skrinName+"','"+mode+"');";

			if(value.equals("") && mode.equals("view"))
			{
				html += "hideElementByID('trMaklumatKiv');";
				html += "hideElementByID('row"+skrinName+column_name+"');";
			}
			html += "showHideTextDocKembali('"+value+"','"+skrinName+"','CATATAN_DOCKIV');";
		}
		else if(column_name.equals("JENIS_RUJUKAN") && table_name.equals("TBLPPKBORANGJ"))
		{
			html += "selectJenisRujukan('"+value+"','"+skrinName+"','"+mode+"');";
		}
		else if(column_name.equals("FLAG_RUJUKAN") && table_name.equals("TBLPPKBORANGJ"))
		{
			html += "selectFlagRujukan('"+value+"','"+skrinName+"','"+mode+"');";
		}



		html += " });</script>";

		return html;
	}

	public String refDropDown(HttpSession session,String jenis_field,String ID_PERBICARAAN,String skrinName,String command,String table_name,String refTable,String PK_refTable,
			String field_KOD_refTable, String field_VALUE_refTable, String field_FK_refTable,String VALUE_FK_refTable,
			String column_name,String mode,
			String value, String divCall,String tableCall, String fieldtableCall, String field_FK_TableCall, String field_PK_TableCall, String field_KOD_TableCall, String field_VALUE_TableCall,
			String formName, Map map_data,String getID_BANDARHTA, Db db) throws Exception {

		String html = "";

		myLogger.info("refDropDown >>> column_name : "+column_name+" table_name : "+table_name+" value : "+value);

		String ID_PERMOHONANSIMATI = "";
		String ID_PERMOHONAN = "";
		String ID_PEMOHON = "";
		String ID_SIMATI = "";

		Map mainID = mainID(session, ID_PERBICARAAN, db);
		if(mainID != null)
		{
			ID_PERMOHONANSIMATI = (String)mainID.get("ID_PERMOHONANSIMATI");
			ID_PERMOHONAN = (String)mainID.get("ID_PERMOHONAN");
			ID_PEMOHON = (String)mainID.get("ID_PEMOHON");
			ID_SIMATI = (String)mainID.get("ID_SIMATI");
		}

		if(jenis_field.equals("hidden"))
		{
			html += "<input type='hidden'  name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value='"+value+"' >";
		}
		else
		{
			if(mode.equals("view"))
			{
				String valueDisplay = getValueRefTable(session, refTable,PK_refTable,field_KOD_refTable,field_VALUE_refTable, value, db);
				html += valueDisplay;
				if(valueDisplay.equals(""))
				{
					html += "-";
				}
				html += " <input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+value+"'  /> ";
				//special kes untuk penjanaan perintah auto, pening wei!
				if(column_name.equals("ID_INTROPERINTAH"))
				{
					Map perintahInfo = getMaklumatPerintahByIdPerbicaraan(session, ID_PERBICARAAN, db);
					myLogger.info("perintahInfo : "+perintahInfo);
					//getmaklumatperintah
					String ID_INTROPERINTAH = (String)perintahInfo.get("ID_INTROPERINTAH");
					String INTROFIELD1 = (String)perintahInfo.get("INTROFIELD1");
					String INTROFIELD2 = (String)perintahInfo.get("INTROFIELD2");
					String INTROFIELD3 = (String)perintahInfo.get("INTROFIELD3");
					html += " <input type='hidden' name='"+value+skrinName+column_name+"FIELD1' id='"+skrinName+column_name+"FIELD1' value = '"+INTROFIELD1+"'  /> ";
					html += " <input type='hidden' name='"+value+skrinName+column_name+"FIELD2' id='"+skrinName+column_name+"FIELD2' value = '"+INTROFIELD2+"'  /> ";
					html += " <input type='hidden' name='"+value+skrinName+column_name+"FIELD3' id='"+skrinName+column_name+"FIELD3' value = '"+INTROFIELD3+"'  /> ";
				}

			}
			else
			{
				String showDropDown = "Y";
				if(column_name.equals("ID_TARAFKPTG") && (skrinName.equals("waris") || skrinName.equals("saksi")  || skrinName.equals("pemiutang")))
				{
					html += getValueRefTable(session, refTable,PK_refTable,field_KOD_refTable,field_VALUE_refTable, value, db);
					html += " <input type='hidden' name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value = '"+value+"'  /> ";
					showDropDown = "N";
				}

				if(showDropDown.equals("Y"))
				{
					String specialFunctionOnChange = "";
					if(column_name.equals("ID_BUKTIMATI"))
					{
						specialFunctionOnChange += "checkBuktiMati('"+skrinName+"',this.value);";
					}
					else if(column_name.equals("ID_NEGERI") && (skrinName.equals("htaah") || skrinName.equals("htaahx")))
					{
						String divMukim = "div"+skrinName+"ID_MUKIM";
						String columnNameMukim = skrinName+"ID_MUKIM";
						specialFunctionOnChange += "resetMukim('"+divMukim+"','"+columnNameMukim+"');";
					}
					else if(column_name.equals("ID_ARB"))
					{
						specialFunctionOnChange += "getMapPejabat('divSetupAlamatPejabat"+skrinName+"',this.value,'"+skrinName+"','"+ID_PERBICARAAN+"');";
						html += "<div id=\"divSetupAlamatPejabat"+skrinName+"\" ></div>";
					}
					else if(column_name.equals("ID_NEGERIMAHKAMAH"))
					{
						specialFunctionOnChange += "resetMapPejabatMahkamah(this.value,'"+skrinName+"','"+ID_PERBICARAAN+"');";
					}
					else if(column_name.equals("ID_MAHKAMAH") || column_name.equals("ID_PEJABATMAHKAMAH"))
					{
						specialFunctionOnChange += "getMapPejabatMahkamah('divSetupAlamatPejabat"+skrinName+"',this.value,'"+skrinName+"','"+ID_PERBICARAAN+"','"+column_name+"');";
						html += "<div id=\"divSetupAlamatPejabat"+skrinName+"\" ></div>";
					}
					else if((column_name.equals("ID_KATEGORI") || column_name.equals("ID_LUAS")) && (skrinName.equals("htaah") || skrinName.equals("htaahx")))
					{
						specialFunctionOnChange += "pilihKetegoriTanah('"+skrinName+"ID_LUAS','tr_luasharta"+skrinName+"','tr_luasharta_b"+skrinName+"','luas1"+skrinName+"','luas2"+skrinName+"','luas3"+skrinName+"','txtLuasAsalHtaam1"+skrinName+"','txtLuasAsalHtaam2"+skrinName+"','txtLuasAsalHtaam3"+skrinName+"','"+skrinName+"LUAS','"+skrinName+"LUAS_HMP','meterhektar"+skrinName+"');";
					}
					else if(column_name.equals("ID_JENISHA"))
					{
						specialFunctionOnChange += "pilihJenisHA(this.value,'"+skrinName+"');";
					}
					else if(table_name.equals("TBLPPKPEMOHON") || table_name.equals("TBLPPKSIMATI") || table_name.equals("TBLPPKOBPERMOHONAN"))
					{
						if(column_name.equals("ID_NEGERI") || column_name.equals("ID_NEGERISURAT"))
						{
							specialFunctionOnChange += "setTRNegera(this.value,'"+skrinName+"','"+column_name+"');";
						}
					}


					/*
					else if(table_name.equals("TBLRUJPEJABATJKPTG") && column_name.equals("ID_PEJABATMAHKAMAH"))
					{
						specialFunctionOnChange += "setPejabatKoleteral(this.value,'"+skrinName+"','"+column_name+"');";
						html += "<div id=\"divSetupAlamatPejabat"+skrinName+"\" ></div>";
					}
					*/

					String ajaxCall = "";
					if(!divCall.equals(""))
					{
						ajaxCall = "doDivAjaxCall"+formName+"('"+divCall+"','ajaxCallDropDown'," +
								"'refTable="+tableCall+"&PK_refTable="+field_PK_TableCall+"&field_KOD_refTable="+field_KOD_TableCall+
								"&field_VALUE_refTable="+field_VALUE_TableCall+"&field_FK_refTable="+field_FK_TableCall+
								"&VALUE_FK_refTable="+skrinName+column_name+"&column_name="+fieldtableCall+"&mainTable="+table_name+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&skrinName="+skrinName+"');";
					}

						List listRefTable = listRefTable(session,skrinName,column_name,refTable,PK_refTable,field_KOD_refTable,field_VALUE_refTable,field_FK_refTable,VALUE_FK_refTable, db);

						//html += "XXXXXXXXXXXX : "+jenis_field;

						if(jenis_field.equals("select"))
						{
							myLogger.info("DALAM SELECT >>> column_name : "+column_name+" table_name : "+table_name+" value : "+value);

							html += "<select id='"+skrinName+column_name+"' name='"+skrinName+column_name+"' class='fullwidth_input'  onChange = \""+specialFunctionOnChange+ajaxCall+"\"> ";
							myLogger.info("listRefTable : "+listRefTable);
							html += "<option value='' >SILA PILIH</option>";
							if (listRefTable.size() != 0) {
								for (int i = 0; i < listRefTable.size(); i++) {
									Map map_column_name = (Map) listRefTable.get(i);
									String id = (String) map_column_name.get(PK_refTable);
									String keterangan = (String) map_column_name.get(field_VALUE_refTable);
									//myLogger.info("id :::::::::: "+id+" value : "+value);
									String selected = "";
									if(value.equals(id))
									{
										selected = "selected";
									}
									html += "<option value='"+id+"' "+selected+" >"+keterangan+"</option>";
								}
							}
							html += "</select>";
						}
						else if(jenis_field.equals("radio"))
						{
							if (listRefTable.size() != 0) {
								html += "<table width='75%' cellspacing='0' cellpadding='0' border='0' >";
								for (int i = 0; i < listRefTable.size(); i++) {
									Map map_column_name = (Map) listRefTable.get(i);
									String id = (String) map_column_name.get(PK_refTable);
									String keterangan = (String) map_column_name.get(field_VALUE_refTable);
									String checked = "";
									if(value.equals(id))
									{
										checked = "checked";
									}
									html += "<tr>";
									html += "<td align='center' width='5%' valign='top'>";

									String onClickRadio = "";
									if(column_name.equals("ID_INTROPERINTAH"))
									{
										//onClickRadio = "saveIntro('loadOnclik"+skrinName+column_name+"',this.value);";
										onClickRadio += "setFieldIntro('"+id+"','"+skrinName+column_name+"',this.value,"+listRefTable.size()+");";
										onClickRadio += "showHideTextIntro(this.value,'"+skrinName+"','INTRO_CATATAN');";
									}


									html += "<input type='radio' onclick=\"document.getElementById('"+skrinName+column_name+"').value=this.value;"+onClickRadio+"\" "+checked+" " +
											"name='"+skrinName+column_name+"Radio' id='"+skrinName+column_name+"Radio' value='"+id+"' > ";
									html += "</td>";
									String alignRadioKeterangan = "left";
									String styleRadioKeterangan = "";
									String specialKeterangan = "";




									//special kes untuk penjanaan perintah auto, pening wei!
									if(column_name.equals("ID_INTROPERINTAH"))
									{
										Map perintahInfo = getMaklumatPerintahByIdPerbicaraan(session, ID_PERBICARAAN, db);
										myLogger.info("perintahInfo : "+perintahInfo);
										//getmaklumatperintah
										String ID_INTROPERINTAH = (String)perintahInfo.get("ID_INTROPERINTAH");
										String INTROFIELD1 = (String)perintahInfo.get("INTROFIELD1");
										String INTROFIELD2 = (String)perintahInfo.get("INTROFIELD2");
										String INTROFIELD3 = (String)perintahInfo.get("INTROFIELD3");
										String styleDisplayField1 = "display:none;";
										String styleDisplayField2 = "display:none;";
										String styleDisplayField3 = "display:none;";
										String spanDisplayField1 = "";
										String spanDisplayField2 = "";
										String spanDisplayField3 = "";
										String introValueField1 = "";
										String introValueField2 = "";
										String introValueField3 = "";


										if(value.equals(id))
										{
											styleDisplayField1 = "";
											styleDisplayField2 = "";
											styleDisplayField3 = "";
											introValueField1 = INTROFIELD1;
											introValueField2 = INTROFIELD2;
											introValueField3 = INTROFIELD3;

											spanDisplayField1 = "display:none;";
											spanDisplayField2 = "display:none;";
											spanDisplayField3 = "display:none;";
										}

										alignRadioKeterangan = "justify";
										styleRadioKeterangan = "margin-bottom:5px;border-bottom: 1px dotted #000; ";
										specialKeterangan = keterangan;
										if(specialKeterangan.contains("[[FIELD1]]"))
										{
											specialKeterangan = specialKeterangan.replace("[[FIELD1]]","<span id='span"+id+skrinName+column_name+"FIELD1' style=\""+spanDisplayField1+"\" >....................</span><div id='div"+id+skrinName+column_name+"FIELD1' style=\""+styleDisplayField1+"\" ><input type=\"text\" spellcheck=\"false\" name='"+id+skrinName+column_name+"FIELD1' style=\"width:100%;"+styleDisplayField1+"\" id='"+id+skrinName+column_name+"FIELD1' value=\""+introValueField1+"\" ><br></div>");
										}

										if(specialKeterangan.contains("[[FIELD2]]"))
										{
											specialKeterangan = specialKeterangan.replace("[[FIELD2]]","<span id='span"+id+skrinName+column_name+"FIELD2' style=\""+spanDisplayField2+"\" >....................</span><div id='div"+id+skrinName+column_name+"FIELD2' style=\""+styleDisplayField2+"\" ><input type=\"text\" spellcheck=\"false\" name='"+id+skrinName+column_name+"FIELD2' style=\"width:100%;"+styleDisplayField2+"\" id='"+id+skrinName+column_name+"FIELD2' value=\""+introValueField2+"\" ><br></div>");
										}

										if(specialKeterangan.contains("[[FIELD3]]"))
										{
											specialKeterangan = specialKeterangan.replace("[[FIELD3]]","<span id='span"+id+skrinName+column_name+"FIELD3' style=\""+spanDisplayField3+"\" >....................</span><div id='div"+id+skrinName+column_name+"FIELD3' style=\""+styleDisplayField3+"\" ><input type=\"text\" spellcheck=\"false\" name='"+id+skrinName+column_name+"FIELD3' style=\"width:100%;"+styleDisplayField3+"\" id='"+id+skrinName+column_name+"FIELD3' value=\""+introValueField3+"\" ><br></div>");
										}
										keterangan = specialKeterangan;
									}

									html += "<td align='"+alignRadioKeterangan+"' width='95%' valign='top'   >";
									html += "<div style=\""+styleRadioKeterangan+"\" >"+keterangan+"</div>";
									html += "</td>";
									html += "</tr>";
								}



								html += "</table>";
							}



							html += "<input type='hidden'  name='"+skrinName+column_name+"' id='"+skrinName+column_name+"' value='"+value+"' >";
							if(column_name.equals("ID_INTROPERINTAH"))
							{
								html += " <div  id='loadOnclik"+skrinName+column_name+"'  ></div>";
							}




						}
					}


					if(column_name.equals("ID_DAERAH") && (skrinName.equals("htaah") || skrinName.equals("htaahx")))
					{

						//load bandar harta
						String ajaxCallBandar = "doDivAjaxCall"+formName+"('div"+skrinName+"ID_BANDARHTA','ajaxCallDropDown'," +
								"'refTable=TBLRUJBANDAR&PK_refTable=ID_BANDAR" +
								"&field_KOD_refTable=KOD_BANDAR&field_VALUE_refTable=KETERANGAN&field_FK_refTable=ID_NEGERI&VALUE_FK_refTable="+skrinName+"ID_NEGERI" +
										"&column_name=ID_BANDARHTA&mainTable="+table_name+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&skrinName="+skrinName+"&currentValue="+getID_BANDARHTA+"');";
						myLogger.info(":::: ajaxCallBandar ::::"+ajaxCallBandar+" getChangesID_BANDARHTA "+getID_BANDARHTA);
						html += "<script>$jquery(document).ready(function (){ "+ajaxCallBandar+" });</script>";
					}
					divCall = "div"+skrinName+"ID_MUKIM";
					tableCall  = "TBLRUJMUKIM";
					fieldtableCall = "ID_MUKIM";
					field_FK_TableCall  = "ID_DAERAH";
					field_PK_TableCall  = "ID_MUKIM";
					field_KOD_TableCall  = "KOD_MUKIM";
					field_VALUE_TableCall  = "NAMA_MUKIM";


				}
		}

		String onLoadFunction = "";


		if(column_name.equals("ID_INTROPERINTAH"))
		{
			onLoadFunction += "showHideTextIntro('"+value+"','"+skrinName+"','INTRO_CATATAN');";
		}/*
		else if(column_name.equals("CHECK_KIV"))
		{
			onLoadFunction += "showHideTextDocKembali('"+value+"','"+skrinName+"','CATATAN_DOCKIV');";
		}
		*/
		else if(column_name.equals("ID_BUKTIMATI"))
		{
			onLoadFunction += "checkBuktiMati('"+skrinName+"','"+value+"');";
		}
		else if(column_name.equals("ID_ARB") && command.equals("showArbBaitulmal"))
		{
			onLoadFunction += "showTrIndividuAgensi('"+skrinName+"','"+mode+"');";
		}
		else if(column_name.equals("ID_KATEGORI") && (skrinName.equals("htaah") || skrinName.equals("htaahx")))
		{
			onLoadFunction += "setJenisLuas('"+value+"','meterhektar"+skrinName+"');";
		}
		else if(column_name.equals("ID_JENISHA"))
		{
			onLoadFunction += "pilihJenisHA('"+value+"','"+skrinName+"');";
		}

		else if(table_name.equals("TBLRUJPEJABATJKPTG") || column_name.equals("ID_PEJABATMAHKAMAH"))
		{
			//onLoadFunction += "setPejabatKoleteral('"+value+"','"+skrinName+"','"+column_name+"');";
			onLoadFunction += "pejabatAddReadonly('"+skrinName+"');";
		}

		else if(table_name.equals("TBLPPKPEMOHON") || table_name.equals("TBLPPKSIMATI") || table_name.equals("TBLPPKOBPERMOHONAN"))
		{
			if(column_name.equals("ID_NEGERI") || column_name.equals("ID_NEGERISURAT"))
			{
				onLoadFunction += "setTRNegera('"+value+"','"+skrinName+"','"+column_name+"');";
			}
		}
		/*
		else if(column_name.equals("ID_NEGERIMAHKAMAH"))
		{
			onLoadFunction += "resetMapPejabatMahkamah('"+value+"','"+skrinName+"','"+ID_PERBICARAAN+"');";
		}
		*/
		if(!onLoadFunction.equals(""))
		{
			html += "<script>$jquery(document).ready(function (){ "+onLoadFunction+" });</script>";
		}
		return html;
	}

	public String openHTMLTableDivided_top(Map setupSkrinHistory,String jenis_transaction,String skrinName,String mode) throws Exception {
		String html = "";
		if(!mode.equals("view"))
		{
		html += "<div><i><font color='red'>Perhatian</font> : Pastikan label bertanda <font color='red'>*</font> diisi.</i></div>";
		}

		if(!skrinName.equals("keputusan") && !skrinName.equals("tukarpegawai") && !skrinName.equals("carianBicara"))
		{
			if(!mode.equals("view"))
			{
				html += "<div><i><font color='red'>Perhatian</font> : Sila masukkan salah satu nombor MyID.</i></div>";
			}
			html += "<div><i><font color='red'>Perhatian</font> : Maklumat yang di<font color='blue'>kemaskini</font>, di<font color='blue'>tambah</font> atau di<font color='blue'>hapus</font> adalah bersifat sementara sehingga kelulusan keputusan perbicaraan dikeluarkan.</i></div>";
			html += "<div><i><font color='blue'>Info</font> : Label <span ><font color='blue'>'Biru'</font></span> menunjukkan maklumat asal semasa pendaftaran jika berlakunya perubahan maklumat semasa perbicaraan.</i></div>";
		}
		html += "<fieldset>";

		if(!jenis_transaction.equals(""))
		{
			if(jenis_transaction.equals("INSERT"))
			{
				html += "<div class=\"blue textmarq\"><h3><i>REKOD INI DI<b>TAMBAH</b> SEMASA PERBICARAAN</i></h3></div>";
			}
			else if(jenis_transaction.equals("UPDATE"))
			{
				html += "<div class=\"blue textmarq\"><h3><i>REKOD INI DI<b>KEMASKINI</b> SEMASA PERBICARAAN</i></h3></div>";
			}
			else if(jenis_transaction.equals("DELETE"))
			{
				html += "<div class=\"red textmarq\"><h3><i>REKOD INI DI<b>HAPUS</b> SEMASA PERBICARAAN</i></h3></div>";
			}
		}

		html += "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1'  class='classFade' > ";
		html += "<tr><td width='50%' valign=top>";
		return html;
	}
	public String openHTMLTableDivided_center() throws Exception {
		String html = "";
		html += "</td><td width='50%' valign=top>";
		return html;
	}
	public String openHTMLTableDivided_bottom() throws Exception {
		String html = "";
		html += "</td></tr></table></fieldset>";
		return html;
	}


	/*
	h.put("FLAG_BATAL",rs == null ? "" :rs.getString("FLAG_BATAL") == null ? "" : rs.getString("FLAG_BATAL"));
				h.put("ID_JENISPERINTAH",rs == null ? "" :rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));
				h.put("FLAG_TANGGUH",rs == null ? "" :rs.getString("FLAG_TANGGUH") == null ? "" : rs.getString("FLAG_TANGGUH"));
				h.put("FLAG_JENIS_KEPUTUSAN",rs == null ? "" :rs.getString("FLAG_JENIS_KEPUTUSAN") == null ? "" : rs.getString("FLAG_JENIS_KEPUTUSAN"));
	*/

	public String openHTMLSenaraiCetakan(HttpSession session, Map viewPerbicaraan,String formName,String ID_SIMATI,String ID_PERBICARAAN,String ID_PERMOHONAN,String ID_PERMOHONANSIMATI, Db db) throws Exception {

		String SEKSYEN = "";
		String ID_FAIL = "";
		String TARIKH_MOHON = "";
		String NO_FAIL = "";
		String ID_STATUS = "";
		String FLAG_BATAL = "";
		String FLAG_TANGGUH = "";
		String ID_JENISPERINTAH = "";
		String FLAG_JENIS_KEPUTUSAN = "";
		String ID_PERINTAH = "";
		int COUNT_PERINTAH = 0;

		Map mainInfo = mainID(session, ID_PERBICARAAN, db);
		if(mainInfo!=null)
		{
			SEKSYEN = (String)mainInfo.get("SEKSYEN");
			ID_FAIL = (String)mainInfo.get("ID_FAIL");
			TARIKH_MOHON = (String)mainInfo.get("TARIKH_MOHON");
			NO_FAIL = (String)mainInfo.get("NO_FAIL");
			ID_STATUS = (String)mainInfo.get("ID_STATUS");
			COUNT_PERINTAH  = (int)mainInfo.get("COUNT_PERINTAH");
		}

		if(COUNT_PERINTAH>1)
		{
			//data perintah bertindih
		}
		else
		{
			Map perintahInfo = getMaklumatPerintahByIdPerbicaraan(session, ID_PERBICARAAN, db);
			myLogger.info("perintahInfo : "+perintahInfo);
			//getmaklumatperintah
			FLAG_BATAL = (String)perintahInfo.get("FLAG_BATAL");
			ID_JENISPERINTAH = (String)perintahInfo.get("ID_JENISPERINTAH");
			FLAG_TANGGUH = (String)perintahInfo.get("FLAG_TANGGUH");
			FLAG_JENIS_KEPUTUSAN = (String)perintahInfo.get("FLAG_JENIS_KEPUTUSAN");
			ID_PERINTAH = (String)perintahInfo.get("ID_PERINTAH");
		}

		String html = "";
		html += "<a class=\"blue\" href=\"javascript:doDivAjaxCall"+formName+"('view_cetakan','showCetakan','NAMA_TABLE=&FIELD_PK=&ID_SIMATI="+ID_SIMATI+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PERMOHONAN="+ID_PERMOHONAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&skrinName=cetakan&scrolPosition='+getPageLocation());\">&nbsp;<u>'Refresh'</u></a>";
		html += "<fieldset>";
		html += "<table width='100%' align='center' border='0' cellspacing='1' cellpadding='1'  class='classFade' > ";
		html += "<tr><td width='100%' align='left'><a class=\"blue\" href=\"javascript:printPerubahan('"+ID_PERBICARAAN+"');\">&nbsp;<u>Lampiran Perubahan Maklumat</u></td></tr>";
		html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:printKeteranganhadir('"+ID_PERBICARAAN+"');\">&nbsp;<u>Lampiran Keterangan Kehadiran</u></td></tr>";
		html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:cetakNotaPerbicaraan('"+NO_FAIL+"','"+ID_PERBICARAAN+"','"+ID_FAIL+"','"+ID_SIMATI+"','"+SEKSYEN+"','"+TARIKH_MOHON+"','Y');\">&nbsp;<u>Nota Perbicaraan (Bicara Interaktif - Senarai Kehadiran Yang Dijana Daripada Skrin Kehadiran Perbicaraan Interaktif)</u></td></tr>";
		html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:cetakNotaPerbicaraan('"+NO_FAIL+"','"+ID_PERBICARAAN+"','"+ID_FAIL+"','"+ID_SIMATI+"','"+SEKSYEN+"','"+TARIKH_MOHON+"','N');\">&nbsp;<u>Nota Perbicaraan (Asal - Nota Bicara Seperti Di Skrin Notis Perbicaraan)</u></td></tr>";
		html += "<tr><td width='100%' align='left'><a class=\"blue\" href=\"javascript:printCatatanPerintah('"+ID_PERBICARAAN+"');\">&nbsp;<u>Nota Keterangan & Perintah</u></td></tr>";

		if(ID_JENISPERINTAH.equals("6")
				&& FLAG_TANGGUH.equals("5")
				&& FLAG_JENIS_KEPUTUSAN.equals("1"))
		{
			html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:cetakBorangJ('"+NO_FAIL+"','"+ID_FAIL+"','"+ID_PERBICARAAN+"');\">&nbsp;<u>Borang J</u></td></tr>";
		}
		else if(ID_JENISPERINTAH.equals("6")
				&& FLAG_TANGGUH.equals("6")
				&& FLAG_JENIS_KEPUTUSAN.equals("1"))
		{
			html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:cetakBorangL('"+NO_FAIL+"','"+ID_FAIL+"','"+ID_PERBICARAAN+"');\">&nbsp;<u>Borang L</u></td></tr>";
			html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:cetakBorangM('"+NO_FAIL+"','"+ID_FAIL+"','"+ID_PERBICARAAN+"');\">&nbsp;<u>Borang M</u></td></tr>";
			html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:cetakBorangN('"+NO_FAIL+"','"+ID_FAIL+"','"+ID_PERBICARAAN+"');\">&nbsp;<u>Borang N</u></td></tr>";
		}
		else if(ID_JENISPERINTAH.equals("6")
				&& FLAG_BATAL.equals("1")
				&& FLAG_JENIS_KEPUTUSAN.equals("2"))
		{
			html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:cetakSuratPindahMTII('"+NO_FAIL+"');\">&nbsp;<u>Surat Pindah Mahkamah Tinggi</u></td></tr>";
			html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:cetakBorangI('"+NO_FAIL+"','"+ID_PERBICARAAN+"');\">&nbsp;<u>Borang I</u></td></tr>";
			html += "<tr><td align='left'><a class=\"blue\" href=\"javascript:buktiPenyampaian('"+NO_FAIL+"','"+ID_FAIL+"');\">&nbsp;<u>Bukti Penyampaian (Mahkamah Tinggi)</u></td></tr>";

		}

		html += "</fieldset>";
		html += "</table>";
		return html;
	}

	public String openHTMLTableCatatanPerintah() throws Exception {
		String html = "";
		html += "<table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\"   > ";
		//html += "<tr><td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td></tr>";
		return html;
	}
	public String closeHTMLTableCatatanPerintah() throws Exception {
		String html = "";
		html += "</table>";
		return html;
	}


	public String openHTMLTable() throws Exception {
		String html = "";
		html += "<table width='100%' align='center' border='0' cellspacing='1' cellpadding='2'  class='classFade' > ";
		html += "<tr><td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td></tr>";
		return html;
	}

	public String closeHTMLTable() throws Exception {
		String html = "";
		html += "</table>" +
				//"</fieldset>" +
				"";
		return html;
	}

	@SuppressWarnings("unchecked")
	public String getValue(HttpSession session,String ID_PERMOHONANSIMATI, Map map_data,
			String table_name,String field_main_PK,String value_main_PK,String id_perbicaraan,
			String column_name, Db db)throws Exception {
		String value = map_data == null ? "" :(String) map_data.get(column_name) == null ? "" : (String) map_data.get(column_name);
		//myLogger.info("getValue :::::: "+value+" value_main_PK ::::: "+value_main_PK+" ::::: "+column_name);
		if(!value_main_PK.equals(""))
		{
			Map getChanges = getChanges(session,"",ID_PERMOHONANSIMATI, table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name, db);
			String VALUE_SELEPAS="";
			//myLogger.info("MAP getChanges :::::: "+getChanges);

			if(getChanges!=null)
			{
				VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
			}

			if(!VALUE_SELEPAS.equals(""))
			{
				value = VALUE_SELEPAS;
			}
		}
		//myLogger.info("getChanges :::::: "+value);
		return value;
	}

	public Map getValueColumn(HttpSession session,String ID_PEMOHON,String ID_PERBICARAAN,String skrinName,String ID_PERMOHONANSIMATI, String pk_field, String id,String table_name, Db db)throws Exception {
		return getValueColumn(session,ID_PEMOHON,ID_PERBICARAAN,skrinName,ID_PERMOHONANSIMATI, pk_field, id,table_name,"", db);
	}

	@SuppressWarnings("unchecked")
	public Map getValueColumn(HttpSession session,String ID_PEMOHON,String ID_PERBICARAAN,String skrinName,String ID_PERMOHONANSIMATI, String pk_field, String id,String table_name, String ID_JENISBAYARAN, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		List listColumnByTable = null;
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			listColumnByTable = listColumnByTable(session,skrinName,table_name,db1);

			stmt = db1.getStatement();
			sql = queryGetData(skrinName,table_name,pk_field,id,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_PEMOHON,ID_JENISBAYARAN);
			myLogger.info(" BICARA INTERAKTIF : SQL getValueColumn "+table_name+" :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				if (listColumnByTable.size() != 0) {
					for (int i = 0; i < listColumnByTable.size(); i++) {
						Map map_column_name = (Map) listColumnByTable.get(i);
						String column_name = (String) map_column_name.get("COLUMN_NAME");
						//myLogger.info("column_name :::::::::: "+column_name);
						//myLogger.info("column_name :::::::::: "+column_name+" value : "+rs.getString(column_name));
						//extra condition
						String valueColumn = rs.getString(column_name) == null ? "" : rs.getString(column_name);

						//special untuk kes ic
						if(
						(
								(table_name.equals("TBLPPKSIMATI") && column_name.equals("JENIS_KP"))
								|| (table_name.equals("TBLPPKPEMOHON") && column_name.equals("JENIS_KP"))
								|| (table_name.equals("TBLPPKPENGHUTANG") && column_name.equals("JENIS_KP"))
								|| (table_name.equals("TBLPPKOBPERMOHONAN") && column_name.equals("JENIS_KP"))
								|| (table_name.equals("TBLPPKHTAPERMOHONAN")
										&& (column_name.equals("ID_KATEGORI") || column_name.equals("ID_BANDARPEMAJU") || column_name.equals("ID_BANDARHTA") || column_name.equals("ID_JENISHM")))
								)
						&& valueColumn.equals("")
						)
						{
							valueColumn = "0";
						}

						if(table_name.equals("TBLPPKBAYARAN"))
						{
							column_name = column_name+ID_JENISBAYARAN;
						}

						h.put(column_name,rs == null ? "" :valueColumn == null ? "" : valueColumn);
					}
				}
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	@SuppressWarnings("unchecked")
	public List listData(HttpSession session, String NAMA_TABLE,
			String FIELD_REFER1,String ID_REFER1,
			String FIELD_REFER2,String ID_REFER2,
			Db db) throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listColumnByTable = null;
		List list = null;
		String sql = "";

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			listColumnByTable = listColumnByTable(session,"",NAMA_TABLE,db1);
			stmt = db1.getStatement();
			sql += "SELECT ";
			sql += " MT.* FROM "+NAMA_TABLE+" MT WHERE MT."+FIELD_REFER1+" = '"+ID_REFER1+"' ";
			if(!FIELD_REFER2.equals(""))
			{
				sql += " AND MT."+FIELD_REFER2+" = '"+ID_REFER2+"' ";
			}
			myLogger.info(" ("+NAMA_TABLE+") BICARA INTERAKTIF : SQL listData :"+ sql);
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				if (listColumnByTable.size() != 0) {
					for (int i = 0; i < listColumnByTable.size(); i++) {
						Map map_column_name = (Map) listColumnByTable.get(i);
						String column_name = (String) map_column_name.get("COLUMN_NAME");
						String valueColumn = rs.getString(column_name) == null ? "" : rs.getString(column_name);
						h.put(column_name,rs == null ? "" :valueColumn == null ? "" : valueColumn);
					}
				}
				list.add(h);
			}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}
	/*
	public String autoJanaCatatan(HttpSession session,String ID_PERINTAH,String ID_PERMOHONANSIMATI, Db db)throws Exception {
		String html = "";


		//HTA
		List MAKLUMATHTA =  listData(session,"TBLPPKHTAPERMOHONAN","ID_PERMOHONANSIMATI",ID_PERMOHONANSIMATI,"","",db);
		//OB
		List MAKLUMATOB =  listData(session,"TBLPPKOBPERMOHONAN","ID_PERMOHONANSIMATI",ID_PERMOHONANSIMATI,"","",db);

		if (MAKLUMATHTA.size() != 0) {
			for (int i_MAKLUMATHTA = 0; i_MAKLUMATHTA < MAKLUMATHTA.size(); i_MAKLUMATHTA++) {
				Map MAKLUMATHTA_map_column = (Map) MAKLUMATHTA.get(i_MAKLUMATHTA);
				String MAKLUMATHTA_ID_HTA = (String) MAKLUMATHTA_map_column.get("ID_HTA");
				//HTA MST
				List PERINTAHMSTHTA =  listData(session,"TBLPPKPERINTAHHTAOBMST","ID_PERINTAH",ID_PERINTAH,"ID_HTA",MAKLUMATHTA_ID_HTA,db);
				if (PERINTAHMSTHTA.size() != 0) {
					for (int i_PERINTAHMSTHTA = 0; i_PERINTAHMSTHTA < PERINTAHMSTHTA.size(); i_PERINTAHMSTHTA++) {
						Map PERINTAHMSTHTA_map_column = (Map) PERINTAHMSTHTA.get(i_PERINTAHMSTHTA);
						String PERINTAHMSTHTA_ID_PERINTAHHTAOBMST = (String) PERINTAHMSTHTA_map_column.get("ID_PERINTAHHTAOBMST");
						String PERINTAHMSTHTA_ID_HTA = (String) PERINTAHMSTHTA_map_column.get("ID_HTA");
						//MALUMAT OB
						if (MAKLUMATOB.size() != 0) {
							for (int i_MAKLUMATOB = 0; i_MAKLUMATOB < MAKLUMATOB.size(); i_MAKLUMATOB++) {
								Map MAKLUMATOB_map_column = (Map) MAKLUMATOB.get(i_MAKLUMATOB);
								String MAKLUMATOB_ID_OB = (String) PERINTAHMSTHTA_map_column.get("ID_OB");

								//HTA DTL
								List PERINTAHSUBHTA =  listData(session,"TBLPPKPERINTAHHTAOBDTL","ID_PERINTAHHTAOBMST",PERINTAHMSTHTA_ID_PERINTAHHTAOBMST,"ID_OB",MAKLUMATOB_ID_OB,db);
								if (PERINTAHSUBHTA.size() != 0) {
									for (int i_PERINTAHSUBHTA = 0; i_PERINTAHSUBHTA < PERINTAHSUBHTA.size(); i_PERINTAHSUBHTA++) {
										Map PERINTAHSUBHTA_map_column = (Map) PERINTAHSUBHTA.get(i_PERINTAHMSTHTA);
										String PERINTAHSUBHTA_ID_PERINTAHHTAOBDTL = (String) PERINTAHMSTHTA_map_column.get("ID_PERINTAHHTAOBDTL");
										String PERINTAHSUBHTA_ID_OB = (String) PERINTAHMSTHTA_map_column.get("ID_OB");
									}
								}
							}
						}
					}
				}
			}
		}


		return html;
	}
	*/

	@SuppressWarnings("unchecked")
	public String getValueRefTable(HttpSession session, String refTable,String PK_refTable,String field_KOD_refTable, String field_VALUE_refTable, String value, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		String returValue = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}

			stmt = db1.getStatement();
			sql = "SELECT ";
			if(!field_KOD_refTable.equals(""))
			{
				sql += field_KOD_refTable + " || ' - ' || ";
			}
			sql += " "+field_VALUE_refTable+" AS "+field_VALUE_refTable+" FROM "+refTable+" WHERE "+PK_refTable+" = '"+value+"' ";
			myLogger.info(" BICARA INTERAKTIF : SQL getValueRefTable :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;

			while (rs.next()) {
				returValue = rs.getString(field_VALUE_refTable) == null ? "" : rs.getString(field_VALUE_refTable);
				if(!field_VALUE_refTable.equals("INTRO") && !refTable.equals("TBLPPKRUJINTROPERINTAH"))
				{
					returValue = returValue.toUpperCase();
				}


			}
			return returValue;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List listRefTable(HttpSession session, String skrinName, String column_name,
			String refTable,String field_PK_refTable,
			String field_KOD_refTable,String field_VALUE_refTable,
			String field_FK_refTable,
			String VALUE_FK_refTable, Db db) throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRefTable = null;
		String sql = "";

		myLogger.info("listRefTable - VALUE_FK_refTable : "+VALUE_FK_refTable);

		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();


			//extra field utk letak kat depan
			String extraField = "";
			if(column_name.equals("ID_ARB"))
			{
				extraField += "UPPER(PEJ.NAMA_PEJABAT || ', ' || B.KETERANGAN) AS NAMA_PEJABAT, ";
			}



			sql = "SELECT ";

			if(column_name.equals("NAMA_PEGAWAIBICARA") || column_name.equals("NAMAPEGAWAIASAL")
					|| column_name.equals("NAMAPEGAWAIBARU"))
			{
				sql += " DISTINCT ";
			}
			else
			{
				sql += extraField+field_PK_refTable+"," ;
			}

			if(!field_KOD_refTable.equals(""))
			{
				sql += field_KOD_refTable+" || ' - ' ||  ";
			}


			if(refTable.equals("TBLPPKRUJUNIT"))
			{
				if(column_name.equals("NAMA_PEGAWAIBICARA") || column_name.equals("NAMAPEGAWAIASAL")
						|| column_name.equals("NAMAPEGAWAIBARU"))
				{
					sql += "TRIM("+field_VALUE_refTable + ") AS "+field_VALUE_refTable+" ";
				}
				else
				{

					sql += field_VALUE_refTable + " || (CASE WHEN STATUS_PEG=1 THEN ' (Aktif)' ELSE ' (Tidak Aktif)' END) AS "+field_VALUE_refTable+" ";
				}
			}
			else
			{
				sql += field_VALUE_refTable + " AS "+field_VALUE_refTable+" ";
			}


			//custom
			if(refTable.equals("TBLRUJPEJABAT") && (column_name.equals("ID_ARB") || column_name.equals("ID_MAHKAMAH") || column_name.equals("ID_PEJABATMAHKAMAH")))
			{
				sql += " FROM TBLRUJPEJABAT PEJ, TBLRUJBANDAR B ";
			}

			else if(refTable.equals("TBLRUJPEJABATJKPTG") && (column_name.equals("ID_PEJABATMAHKAMAH")))
			{
				sql += " FROM TBLPPKPERMOHONAN P, "+
						" (SELECT RP.ID_PEJABATJKPTG,RP.KOD_JKPTG,RP.NAMA_PEJABAT,RPU.ID_NEGERI,RPU.ID_DAERAHURUS ID_DAERAH FROM  "+
						" TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU "+
						" WHERE RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG "+
						" AND RPU.ID_JENISPEJABAT = 22 "+
						" AND RP.ID_SEKSYEN = 2) PEJ ";
			}
			else
			{
				sql += " FROM "+refTable;
			}

			sql += " WHERE "+field_PK_refTable+" IS NOT NULL ";

			if(refTable.equals("TBLPPKRUJTARAFKPTG"))
			{
				if(skrinName.equals("ob"))
				{
					sql += " AND ID_TARAFKPTG NOT IN (1,2,14) ";
				}
				else if(skrinName.equals("waris"))
				{
					sql += " AND ID_TARAFKPTG = 1 ";
				}
				else if(skrinName.equals("saksi"))
				{
					sql += " AND ID_TARAFKPTG = 14 ";
				}
				else if(skrinName.equals("pemiutang"))
				{
					sql += " AND ID_TARAFKPTG = 2 ";
				}
			}
			else if(refTable.equals("TBLRUJSTATUS"))
			{
				sql += " AND ID_SEKSYEN = '2' ";
			}
			else if(refTable.equals("TBLRUJKATEGORI"))
			{
				sql += " AND ID_KATEGORI NOT IN (16137,16138,16149) ";
			}
			else if(refTable.equals("TBLRUJPEJABAT") && column_name.equals("ID_ARB"))
			{
				sql += " AND PEJ.ID_JENISPEJABAT IN (9,61) AND PEJ.ID_BANDAR = B.ID_BANDAR ";
			}
			else if(refTable.equals("TBLRUJPEJABAT") && (column_name.equals("ID_MAHKAMAH") || column_name.equals("ID_PEJABATMAHKAMAH")))
			{
				sql += " AND PEJ.ID_JENISPEJABAT IN (8,41) AND PEJ.ID_BANDAR = B.ID_BANDAR(+) ";
			}
			else if(refTable.equals("TBLRUJJENISPB") && column_name.equals("STATUS_PEMILIKAN"))
			{
				sql += " AND JENIS_DAFTAR_PB = 'Y' ";
			}
			else if(refTable.equals("TBLRUJPEJABATJKPTG") && (column_name.equals("ID_PEJABATMAHKAMAH")))
			{
				sql += " AND P.ID_DAERAHMHN = PEJ.ID_DAERAH ";
			}

			if(!VALUE_FK_refTable.equals(""))
			{
				if(refTable.equals("TBLPPKRUJSAUDARA"))
				{
					sql += " AND "+field_FK_refTable+" = '0"+VALUE_FK_refTable+"'  ";
				}
				else if(refTable.equals("TBLRUJPEJABAT") && (column_name.equals("ID_MAHKAMAH") || column_name.equals("ID_PEJABATMAHKAMAH")))//custom
				{
					//sql += " AND PEJ."+field_FK_refTable+" = '"+VALUE_FK_refTable+"'  ";
					sql += " AND (PEJ.ID_JENISPEJABAT || PEJ."+field_FK_refTable+") = '"+VALUE_FK_refTable+"'  ";
				}
				else if(refTable.equals("TBLRUJPEJABAT") && column_name.equals("ID_ARB"))//custom
				{
					if(VALUE_FK_refTable.equals("6"))
					{
						VALUE_FK_refTable = "9";
					}
					else if(VALUE_FK_refTable.equals("8"))
					{
						VALUE_FK_refTable = "61";
					}
					sql += " AND PEJ."+field_FK_refTable+" = '"+VALUE_FK_refTable+"'  ";
				}
				else
				{
					sql += " AND "+field_FK_refTable+" = '"+VALUE_FK_refTable+"'  ";
				}
			}

			if(!field_KOD_refTable.equals(""))
			{
				sql += " ORDER BY "+field_KOD_refTable;
			}
			else
			{
				if(refTable.equals("TBLPPKRUJUNIT"))
				{
					if(column_name.equals("NAMA_PEGAWAIBICARA") || column_name.equals("NAMAPEGAWAIASAL")
							|| column_name.equals("NAMAPEGAWAIBARU"))
					{
						sql += " ORDER BY NAMA_PEGAWAI ASC ";
					}
					else
					{
						sql += " ORDER BY STATUS_PEG DESC,NAMA_PEGAWAI ASC ";
					}
				}
				else if(refTable.equals("TBLRUJSTATUS"))
				{
					sql += " ORDER BY KOD_STATUS ";;
				}
				else
				{
					if(!column_name.equals("ID_INTROPERINTAH"))
					{
						sql += " ORDER BY "+field_VALUE_refTable;
					}
				}

			}

			if(column_name.equals("ID_INTROPERINTAH"))
			{
				sql += " UNION ALL "+
							" SELECT NULL AS ID_INTROPERINTAH, 'Lain-lain ayat Perintah' AS INTRO FROM DUAL ORDER BY ID_INTROPERINTAH";
			}

			myLogger.info(" ("+refTable+") BICARA INTERAKTIF : SQL listRefTable :"+ sql);
			rs = stmt.executeQuery(sql);


			listRefTable = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				if(!column_name.equals("NAMA_PEGAWAIBICARA") && !column_name.equals("NAMAPEGAWAIASAL")
						&& !column_name.equals("NAMAPEGAWAIBARU"))
				{
					h.put(field_PK_refTable,rs == null ? "" :rs.getString(field_PK_refTable) == null ? "" : rs.getString(field_PK_refTable).toUpperCase());
				}


				String valueKeterangan = rs == null ? "" :rs.getString(field_VALUE_refTable) == null ? "" : rs.getString(field_VALUE_refTable);

				if(!column_name.equals("ID_INTROPERINTAH"))
				{
					valueKeterangan = valueKeterangan.toUpperCase();
				}

				h.put(field_VALUE_refTable,valueKeterangan);
				listRefTable.add(h);
			}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return listRefTable;
	}
	//::::::::::::::::::::::::::::::::::::: close function setup page



	public String getValueKeterangan(HttpSession session, String table_name, String column_name, String value, Db db) throws Exception {
		String keterangan = "";

		if(column_name.equals("ID_PEMOHON") && table_name.equals("TBLPPKOBPERMOHONAN"))
		{
			if(value.equals(""))
			{
				keterangan = "TIDAK";
			}
			else
			{
				keterangan = "YA";
			}
		}
		else if(column_name.equals("FLAG_BANTAHAN"))
		{
			if(value.equals("Y"))
			{
				keterangan = "ADA"+" --- "+value;
			}
			else
			{
				keterangan = "TIADA"+" --- "+value;
			}
		}
		else if(column_name.equals("JANTINA"))
		{
			if(value.equals("1"))
			{
				keterangan = "LELAKI";
			}
			else if(value.equals("2"))
			{
				keterangan = "PEREMPUAN";
			}
		}
		else if(column_name.equals("FLAG_KATEGORI_HTA"))
		{
			if(value.equals("1"))
			{
				keterangan = "PERJANJIAN JUAL BELI";
			}
			else if(value.equals("2"))
			{
				keterangan = "PEGANGAN DI BAWAH AKTA TANAH (KAWASAN PENEMPATAN BERKELOMPOK 1960)";
			}
			else if(value.equals("3"))
			{
				keterangan = "KEPENTINGAN LAIN - LAIN";
			}
		}
		else if(column_name.equals("JENIS_HTA"))
		{
			if(value.equals("Y"))
			{
				keterangan = "HARTA TAK ALIH (ADA HAKMILIK)";
			}
			else if(value.equals("T"))
			{
				keterangan = "HARTA TAK ALIH (TIADA HAKMILIK)";
			}
		}
		else if(column_name.equals("JENIS_TNH"))
		{
			if(value.equals("0"))
			{
				keterangan = "TIDAK DINYATAKAN";
			}
			else if(value.equals("1"))
			{
				keterangan = "TANAH RIZAB";
			}
			else if(value.equals("2"))
			{
				keterangan = "TANAH ADAT";
			}
			else if(value.equals("3"))
			{
				keterangan = "TANAH GSA";
			}
			else if(value.equals("4"))
			{
				keterangan = "BUKAN TANAH GSA";
			}
		}
		else if(column_name.equals("JENIS_KP"))
		{
			if(value.equals("5"))
			{
				keterangan = "TENTERA";
			}
			else if(value.equals("6"))
			{
				keterangan = "POLIS";
			}
			else if(value.equals("4"))
			{
				keterangan = "PASSPORT";
			}
			else if(value.equals("7"))
			{
				keterangan = "LAIN-LAIN";
			}
		}
		else if(column_name.equals("JENIS_WARGA"))
		{
			if(value.equals("1"))
			{
				keterangan = "WARGANEGARA";
			}
			else if(value.equals("2"))
			{
				keterangan = "BUKAN WARGANEGARA";
			}
			else if(value.equals("3"))
			{
				keterangan = "PEMASTAUTIN TETAP";
			}

		}
		else if(column_name.equals("JENIS_WAKTU_MATI") || column_name.equals("JENIS_WAKTU_KEMATIAN"))
		{
			if(value.equals("1"))
			{
				keterangan = "PAGI";
			}
			else if(value.equals("2"))
			{
				keterangan = "TENGAHARI";
			}
			else if(value.equals("3"))
			{
				keterangan = "PETANG";
			}
			else if(value.equals("3"))
			{
				keterangan = "MALAM";
			}
		}
		else if(column_name.equals("STATUS_PEMOHON") || column_name.equals("JENIS_PEMIUTANG"))
		{
			if(value.equals("1"))
			{
				keterangan = "AGENSI";
			}
			else if(value.equals("2"))
			{
				keterangan = "INDIVIDU";
			}
		}
		else if(column_name.equals("STATUS_TUKARPEGAWAI"))
		{
			if(value.equals("1"))
			{
				keterangan += "PERMOHONAN BARU";
			}
			else if(value.equals("2"))
			{
				keterangan += "LULUS";
			}
			else if(value.equals("3"))
			{
				keterangan += "TOLAK";
			}
		}
		else if(column_name.equals("STATUS_OB"))
		{
			if(value.equals("1"))
			{
				keterangan = "DEWASA / WARAS";
			}
			else if(value.equals("2"))
			{
				keterangan = "BELUM DEWASA";
			}
			else if(value.equals("3"))
			{
				keterangan = "HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN";
			}
			else if(value.equals("4"))
			{
				keterangan = "TIDAK SEMPURNA AKAL";
			}
		}
		else if(column_name.equals("STATUS_HIDUP"))
		{
			if(value.equals("1"))
			{
				keterangan = "YA";
			}
			else
			{
				keterangan = "TIDAK";
			}
		}
		else if(column_name.equals("FLAG_DAFTAR"))
		{
			if(value.equals("2"))
			{
				keterangan = "PERBICARAAN";
			}
			else if(value.equals("1"))
			{
				keterangan = "PENDAFTARAN";
			}
		}
		else if(column_name.toUpperCase().contains("ID_SAUDARA"))
		{
			keterangan = getValueRefTable(session, "TBLPPKRUJSAUDARA","ID_SAUDARA","KOD","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_TARAFKPTG"))
		{
			keterangan = getValueRefTable(session, "TBLPPKRUJTARAFKPTG","ID_TARAFKPTG","KOD","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("JENIS_AGAMA") || column_name.toUpperCase().contains("ID_AGAMA"))
		{
			keterangan = getValueRefTable(session, "TBLRUJAGAMA","ID_AGAMA","KOD_AGAMA","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("JENIS_WARGA") || column_name.toUpperCase().contains("ID_WARGANEGARA"))
		{
			keterangan = getValueRefTable(session, "TBLRUJWARGANEGARA","ID_WARGANEGARA","KOD_WARGA","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_NEGERI"))
		{
			keterangan = getValueRefTable(session, "TBLRUJNEGERI","ID_NEGERI","KOD_NEGERI","NAMA_NEGERI", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_DAERAH"))
		{
			keterangan = getValueRefTable(session, "TBLRUJDAERAH","ID_DAERAH","KOD_DAERAH","NAMA_DAERAH", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_MUKIM"))
		{
			keterangan = getValueRefTable(session, "TBLRUJMUKIM","ID_MUKIM","KOD_MUKIM","NAMA_MUKIM", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_JENISHM"))
		{
			keterangan = getValueRefTable(session, "TBLRUJJENISHAKMILIK","ID_JENISHAKMILIK","KOD_JENIS_HAKMILIK","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_JENISHA"))
		{
			keterangan = getValueRefTable(session, "TBLPPKRUJJENISHA","ID_JENISHA","KOD","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_KATEGORI"))
		{
			keterangan = getValueRefTable(session, "TBLRUJKATEGORI","ID_KATEGORI","KOD_KATEGORI","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("STATUS_PEMILIKAN"))
		{
			keterangan = getValueRefTable(session, "TBLRUJJENISPB","ID_JENISPB","KOD_JENIS_PB","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_LUAS"))
		{
			keterangan = getValueRefTable(session, "TBLRUJLUAS","ID_LUAS","KOD_LUAS","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("STATUS_PEMILIKAN"))
		{
			keterangan = getValueRefTable(session, "TBLRUJJENISPB","ID_JENISPB","KOD_JENIS_PB","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_BANDAR"))
		{
			keterangan = getValueRefTable(session, "TBLRUJBANDAR","ID_BANDAR","KOD_BANDAR","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_BUKTIMATI"))
		{
			keterangan = getValueRefTable(session, "TBLPPKRUJBUKTIMATI","ID_BUKTIMATI","KOD","KETERANGAN", value, db);
		}
		else if(column_name.toUpperCase().contains("ID_ARB"))
		{
			keterangan = getValueRefTable(session, "TBLRUJPEJABAT","ID_PEJABAT","KOD_PEJABAT","NAMA_PEJABAT", value, db);
		}

		return keterangan;
	}


	public String updateJenisSkrinMain(HttpSession session,String ID_SEJARAHBIMAIN,String skrinName,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		long id_historyutama = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_SEJARAHBIMAIN", ID_SEJARAHBIMAIN);
			r.add("SKRIN_NAME", skrinName);
			sql = r.getSQLUpdate("TBLPPKSEJARAHBIMAIN");
			myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE updateJenisSkrinMain : "+sql);
			stmt.executeUpdate(sql);
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return ID_SEJARAHBIMAIN;
	}






		public void nilaiHartaTakAliByPermohonan(HttpSession session,String ID_PERMOHONANSIMATI,String ID_PERMOHONAN,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " UPDATE TBLPPKPERMOHONAN SET ID_KEMASKINI = '"+USER_ID_SYSTEM+"' , TARIKH_KEMASKINI = SYSDATE, " +

			" JUMLAH_HTA_TARIKHMOHON = (SELECT SUM(A.NILAI_HTA_TARIKHMOHON) "+
			" FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A WHERE  A1.ID_HTA = A.ID_HTA  " +
			" AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI  AND A1.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"'),  "+

			" JUMLAH_HTA_TARIKHMATI = (SELECT SUM(A.NILAI_HTA_TARIKHMATI)  FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A   "+
			" WHERE A1.ID_HTA = A.ID_HTA  AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI " +
			" AND A1.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"'),  "+

			" JUMLAH_HA_TARIKHMOHON = (SELECT SUM(B.NILAI_HA_TARIKHMOHON)  " +
			" FROM TBLPPKHA B1,TBLPPKHAPERMOHONAN B  WHERE B1.ID_HA = B.ID_HA AND B1.ID_PERMOHONANSIMATI = B.ID_PERMOHONANSIMATI  " +
			" AND B1.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"'), "+

			" JUMLAH_HA_TARIKHMATI = (SELECT SUM(B.NILAI_HA_TARIKHMATI)  FROM TBLPPKHA B1,TBLPPKHAPERMOHONAN B  "+
			" WHERE  B1.ID_HA = B.ID_HA AND B1.ID_PERMOHONANSIMATI = B.ID_PERMOHONANSIMATI  " +
			" AND B.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"') " +

			" WHERE ID_PERMOHONAN = '"+ID_PERMOHONAN+"' ";

			myLogger.info("BICARA INTERAKTIF : nilaiHartaTakAliByPermohonan 1: "+sql);
			stmt.executeUpdate(sql);


			sql = " UPDATE TBLPPKPERMOHONAN SET ID_KEMASKINI = '"+USER_ID_SYSTEM+"' , TARIKH_KEMASKINI = SYSDATE, " +
			" JUMLAH_HARTA_TARIKHMOHON = NVL(JUMLAH_HTA_TARIKHMOHON,0) + NVL(JUMLAH_HA_TARIKHMOHON,0), " +
			" JUMLAH_HARTA_TARIKHMATI = NVL(JUMLAH_HTA_TARIKHMATI,0) + NVL(JUMLAH_HA_TARIKHMATI,0)  " +
			" WHERE ID_PERMOHONAN = '"+ID_PERMOHONAN+"' ";
			myLogger.info("BICARA INTERAKTIF : nilaiHartaTakAliByPermohonan 2: "+sql);
			stmt.executeUpdate(sql);
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public String saveHistoryUtama(HttpSession session,String ID_SEJARAHBIMAIN,String skrinName,String tableName,String field_PK,String value_PK,String AKTIVITI,String ID_PERBICARAAN,String ID_PERMOHONANSIMATI,Db db) throws Exception {
		Db db1 = null;
		String return_ID_SEJARAHBIMAIN = "";
		String sql = "";
		long id_historyutama = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			if(ID_SEJARAHBIMAIN.equals(""))
			{
				id_historyutama = DB.getNextID(db1, "TBLPPKSEJARAHBIMAIN_SEQ");
				return_ID_SEJARAHBIMAIN = id_historyutama+"";
			}
			else
			{
				return_ID_SEJARAHBIMAIN = ID_SEJARAHBIMAIN;
			}

			if(!ID_SEJARAHBIMAIN.equals(""))
			{
				r.update("ID_SEJARAHBIMAIN", return_ID_SEJARAHBIMAIN);
			}
			else
			{
				r.add("ID_SEJARAHBIMAIN", id_historyutama);
			}
			r.add("JENIS_AKTIVITI", AKTIVITI);
			r.add("NAMA_TABLE", tableName);
			r.add("SKRIN_NAME", skrinName);
			r.add("NAMA_FIELD_PK", field_PK);
			r.add("VALUE_FIELD_PK", value_PK);
			r.add("ID_PERBICARAAN", ID_PERBICARAAN);
			r.add("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			if(!ID_SEJARAHBIMAIN.equals(""))
			{
				sql = r.getSQLUpdate("TBLPPKSEJARAHBIMAIN");
			}
			else
			{
				sql = r.getSQLInsert("TBLPPKSEJARAHBIMAIN");
			}
			myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE TBLPPKSEJARAHBIMAIN : "+sql);
			stmt.executeUpdate(sql);


		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}



		return return_ID_SEJARAHBIMAIN;
	}

	public String saveHistorySub(HttpSession session,
			String ID_SEJARAHBIMAIN,String REKOD_LABEL,String NAMA_FIELD,
			String VALUE_SEBELUM,String KETERANGAN_SEBELUM,
			String VALUE_SELEPAS,String KETERANGAN_SELEPAS,
			double TURUTAN,String skrinName,
			Db db) throws Exception {
		Db db1 = null;
		String ID_SEJARAHBISUB = "";
		String sql = "";
		long id_historysub = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			id_historysub = DB.getNextID(db1, "TBLPPKSEJARAHBISUB_SEQ");
			ID_SEJARAHBISUB = id_historysub+"";
			r.add("ID_SEJARAHBISUB", id_historysub);
			r.add("ID_SEJARAHBIMAIN", ID_SEJARAHBIMAIN);
			r.add("REKOD_LABEL", REKOD_LABEL);
			r.add("NAMA_FIELD", NAMA_FIELD);
			r.add("VALUE_SEBELUM", VALUE_SEBELUM);
			r.add("KETERANGAN_SEBELUM", KETERANGAN_SEBELUM);
			r.add("VALUE_SELEPAS", VALUE_SELEPAS);
			r.add("KETERANGAN_SELEPAS", KETERANGAN_SELEPAS);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TURUTAN", TURUTAN);
			sql = r.getSQLInsert("TBLPPKSEJARAHBISUB");
			myLogger.info("BICARA INTERAKTIF : INSERT TBLPPKSEJARAHBISUB : "+sql);
			stmt.executeUpdate(sql);


			if(skrinName.equals("htaah") && NAMA_FIELD.equals("JENIS_HTA") && VALUE_SELEPAS.equals("T"))
			{
				updateJenisSkrinMain(session,ID_SEJARAHBIMAIN,"htaahx",db1);
			}
			else if(skrinName.equals("htaahx") && NAMA_FIELD.equals("JENIS_HTA") && VALUE_SELEPAS.equals("Y"))
			{
				updateJenisSkrinMain(session,ID_SEJARAHBIMAIN,"htaah",db1);
			}
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return ID_SEJARAHBISUB;
	}






	public void deleteHISTORY(String command,String ID_SEJARAHBIMAIN,String NAMA_TABLE,String NAMA_FIELD_PK,String VALUE_FIELD_PK,String ID_PERBICARAAN,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String sql_condition = "";
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String condition = "";
			if(!ID_SEJARAHBIMAIN.equals(""))
			{
				condition = ID_SEJARAHBIMAIN;
			}
			else
			{
				condition = "SELECT ID_SEJARAHBIMAIN FROM TBLPPKSEJARAHBIMAIN WHERE ID_PERBICARAAN = '"+ID_PERBICARAAN+"' " +
					" AND UPPER(NAMA_TABLE) = '"+NAMA_TABLE.toUpperCase()+"' " +
					" AND UPPER(NAMA_FIELD_PK) = '"+NAMA_FIELD_PK.toUpperCase()+"' " +
							" AND VALUE_FIELD_PK = '"+VALUE_FIELD_PK+"' ";
			}

			sql = "DELETE FROM TBLPPKSEJARAHBISUB WHERE ID_SEJARAHBIMAIN IN ("+condition+")";
			myLogger.info("DELETE  TBLSEJARAHPENGGUNASUB : "+sql);
			stmt.executeUpdate(sql);

			if(ID_SEJARAHBIMAIN.equals("") || command.equals("deleteHistory"))
			{
				sql = "DELETE FROM TBLPPKSEJARAHBIMAIN WHERE ID_SEJARAHBIMAIN IN ("+condition+")";
				myLogger.info("DELETE  TBLPPKSEJARAHBIMAIN : "+sql);
				stmt.executeUpdate(sql);
			}

		} catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	public void deleteTableByPerbicaraan(String ID_PERBICARAAN,String NAMA_TABLE,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String sql_condition = "";
		//try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();

			if(NAMA_TABLE.equals("TBLPPKBORANGJ"))
			{
				sql = "DELETE FROM TBLPPKBORANGJ WHERE ID_PERBICARAAN IN ("+ID_PERBICARAAN+")";
			}
			else if(NAMA_TABLE.equals("TBLPPKBORANGJDTL"))
			{
				sql = "DELETE FROM TBLPPKBORANGJDTL DTL WHERE ID_BORANGJ IN (SELECT ID_BORANGJ FROM TBLPPKBORANGJ WHERE ID_PERBICARAAN IN ("+ID_PERBICARAAN+"))";
			}
			else if(NAMA_TABLE.equals("TBLPPKKOLATERALMST"))
			{
				sql = "DELETE FROM TBLPPKKOLATERALMST WHERE ID_PERBICARAAN IN ("+ID_PERBICARAAN+")";
			}
			else if(NAMA_TABLE.equals("TBLPPKKOLATERALDTL"))
			{
				sql = "DELETE FROM TBLPPKKOLATERALDTL DTL WHERE ID_KOLATERALMST IN (SELECT ID_KOLATERALMST FROM TBLPPKKOLATERALMST WHERE ID_PERBICARAAN IN ("+ID_PERBICARAAN+"))";
			}

			myLogger.info("DELETE deleteTableByPerbicaraan : "+sql);
			stmt.executeUpdate(sql);

		/*
		} catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
		*/
	}

	public String sqlChangesHubungan(HttpSession session,String ID_PERMOHONANSIMATI,String ID_PERBICARAAN,String ID_OB){
		String sqlChangesHubungan = " SELECT ID_SEJARAHBIMAIN,ID_OB,ID_PARENTOB,ID_PERMOHONANSIMATI FROM ( "+
				" SELECT M.JENIS_AKTIVITI,M.ID_SEJARAHBIMAIN, M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK,   "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_OB' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_OB,  "+
				" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PARENTOB' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_PARENTOB "+
				" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S  "+
				" WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN AND M.NAMA_TABLE = 'TBLPPKHUBUNGANOBPERMOHONAN' "+
				" AND M.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' " +
						" AND M.ID_PERBICARAAN = '"+ID_PERBICARAAN+"' "+
				" GROUP BY M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN,M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK "+
				" ) WHERE ID_SEJARAHBIMAIN IS NOT NULL";
		if(!ID_OB.equals(""))
		{
			sqlChangesHubungan += " AND  ID_OB = '"+ID_OB+"' ";
		}
		return sqlChangesHubungan;
	}

	public Map getChangesHubungan(HttpSession session,String ID_PERMOHONANSIMATI,String ID_PERBICARAAN,String ID_OB, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		Map h = null;
		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = sqlChangesHubungan(session,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_OB);
			myLogger.info(" BICARA INTERAKTIF : getChangesHubungan :"+ sql);
			rs = stmt.executeQuery(sql);


			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_PARENTOB",rs == null ? "" :rs.getString("ID_PARENTOB") == null ? "" : rs.getString("ID_PARENTOB").toUpperCase());
				h.put("ID_OB",rs == null ? "" :rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB").toUpperCase());
				h.put("ID_PERMOHONANSIMATI",rs == null ? "" :rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI").toUpperCase());
			}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return h;
	}

	public String checkColumnExist(HttpSession session,String NAMA_TABLE,String NAMA_COLUMN, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		String dataType = "";
		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT DATA_TYPE FROM USER_TAB_COLS WHERE TABLE_NAME = '"+NAMA_TABLE+"' AND COLUMN_NAME = '"+NAMA_COLUMN+"' ";
			//myLogger.info(" BICARA INTERAKTIF : checkColumnExist :"+ sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dataType = (rs.getString("DATA_TYPE") == null ? "" : rs.getString("DATA_TYPE"));
			}
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return dataType;
	}

	public void deleteHISTORYHubungan(String ID_PERMOHONANSIMATI,String ID_OB,String ID_PARENTOB,String ID_PERBICARAAN,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		String sql_condition = "";
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();

			String condition = " SELECT ID_SEJARAHBIMAIN FROM ( "+
					" SELECT M.JENIS_AKTIVITI,M.ID_SEJARAHBIMAIN, M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK,   "+
					" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_OB' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_OB,  "+
					" rtrim (xmlagg (xmlelement (e, ((CASE WHEN S.NAMA_FIELD = 'ID_PARENTOB' THEN  S.VALUE_SELEPAS END)) )).extract ('//text()')) AS ID_PARENTOB "+
					" FROM TBLPPKSEJARAHBIMAIN M,TBLPPKSEJARAHBISUB S  "+
					" WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN AND M.NAMA_TABLE = 'TBLPPKHUBUNGANOBPERMOHONAN' "+
					" AND M.ID_PERMOHONANSIMATI = '"+ID_PERMOHONANSIMATI+"' AND M.ID_PERBICARAAN = '"+ID_PERBICARAAN+"' "+
					" GROUP BY M.JENIS_AKTIVITI, M.ID_SEJARAHBIMAIN,M.ID_PERMOHONANSIMATI, M.NAMA_TABLE, M.NAMA_FIELD_PK "+
					" ) WHERE ID_OB = '"+ID_OB+"' AND ID_PARENTOB = '"+ID_PARENTOB+"' ";
			myLogger.info(" condition : "+sql);

			sql = "DELETE FROM TBLPPKSEJARAHBISUB WHERE ID_SEJARAHBIMAIN IN ("+condition+")";
			myLogger.info("DELETE  TBLPPKSEJARAHBIMAIN : "+sql);
			stmt.executeUpdate(sql);

			sql = "DELETE FROM TBLPPKSEJARAHBIMAIN WHERE ID_SEJARAHBIMAIN IN ("+condition+")";
			myLogger.info("DELETE  TBLPPKSEJARAHBIMAIN : "+sql);
			stmt.executeUpdate(sql);

		} catch (Exception re) {
			throw re;
		}finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	@SuppressWarnings("unchecked")
	public Map getChanges(HttpSession session,String ID_SEJARAHBIMAIN, String ID_PERMOHONANSIMATI,String NAMA_TABLE,String NAMA_FIELD_PK,String VALUE_FIELD_PK,String ID_PERBICARAAN,String NAMA_FIELD, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		List listColumnByTable = null;
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT * FROM TBLPPKSEJARAHBIMAIN M, TBLPPKSEJARAHBISUB S WHERE M.ID_SEJARAHBIMAIN = S.ID_SEJARAHBIMAIN "+
			" AND M.NAMA_TABLE = '"+NAMA_TABLE+"' AND M.NAMA_FIELD_PK = '"+NAMA_FIELD_PK+"' ";
			if(!ID_SEJARAHBIMAIN.equals(""))
			{
				sql += " AND M.ID_SEJARAHBIMAIN = '"+ID_SEJARAHBIMAIN+"' ";
			}
			else
			{
				sql += " AND M.VALUE_FIELD_PK = '"+VALUE_FIELD_PK+"' ";
			}
			sql += " AND M.ID_PERBICARAAN = '"+ID_PERBICARAAN+"' AND S.NAMA_FIELD = '"+NAMA_FIELD+"' ";
			if(NAMA_FIELD.equals("ID_BANDARHTA"))
			{
				myLogger.info(" BICARA INTERAKTIF : SQL getChanges :"+ sql);
			}
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("JENIS_AKTIVITI",rs == null ? "" :rs.getString("JENIS_AKTIVITI") == null ? "" : rs.getString("JENIS_AKTIVITI"));
				h.put("NAMA_FIELD",rs == null ? "" :rs.getString("NAMA_FIELD") == null ? "" : rs.getString("NAMA_FIELD"));
				h.put("VALUE_SEBELUM",rs == null ? "" :rs.getString("VALUE_SEBELUM") == null ? "" : rs.getString("VALUE_SEBELUM"));
				h.put("KETERANGAN_SEBELUM",rs == null ? "" :rs.getString("KETERANGAN_SEBELUM") == null ? "" : rs.getString("KETERANGAN_SEBELUM"));
				h.put("VALUE_SELEPAS",rs == null ? "" :rs.getString("VALUE_SELEPAS") == null ? "" : rs.getString("VALUE_SELEPAS"));
				h.put("KETERANGAN_SELEPAS",rs == null ? "" :rs.getString("KETERANGAN_SELEPAS") == null ? "" : rs.getString("KETERANGAN_SELEPAS"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}


	@SuppressWarnings("unchecked")
	public Map getFail(HttpSession session,String ID_PERMOHONAN, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		List listColumnByTable = null;
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = "SELECT F.NO_FAIL, F.ID_NEGERI FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = '"+ID_PERMOHONAN+"' ";
			//myLogger.info(" BICARA INTERAKTIF : SQL getChanges :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("NO_FAIL",rs == null ? "" :rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("ID_NEGERI",rs == null ? "" :rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Map getUrusanStatusAktif(HttpSession session,String ID_PERMOHONAN, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		List listColumnByTable = null;
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();
			sql = " SELECT * FROM ( "+
					" SELECT STF.*, SS.ID_STATUS FROM TBLRUJSUBURUSANSTATUSFAIL STF, TBLRUJSUBURUSANSTATUS SS WHERE STF.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS  "+
					" AND STF.ID_PERMOHONAN = '"+ID_PERMOHONAN+"'  "+
					" AND STF.AKTIF = '1' ORDER BY STF.TARIKH_MASUK DESC) WHERE ROWNUM = 1 ";
			myLogger.info(" BICARA INTERAKTIF : SQL getUrusanStatusAktif :"+ sql);
			rs = stmt.executeQuery(sql);
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_STATUS",rs == null ? "" :rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}

	public String setupButton(HttpSession session,String flagShowLantik,String jenis_transaction,Map setupSkrinHistory,String FIELD_PK,String id,String current_previous,String skrinName,String formName,String flag_editable,String mode,Map setupSkrin,String table_name,String divViewMaklumat,String paramsButton,String ID_PEMOHON_MAIN, Db db) throws Exception {
		String html = "";
		String AKTIVITI = "";
		if(setupSkrinHistory!=null)
		{
			AKTIVITI = (String)setupSkrinHistory.get("JENIS_AKTIVITI");
		}
		myLogger.info("divViewMaklumat : "+divViewMaklumat);
		if(flag_editable.equals("Y"))
		{
			if(mode.equals("view"))
			{
				html += setRowEditButton(session,flagShowLantik,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "editMaklumat", paramsButton, "Y", ID_PEMOHON_MAIN, db);
			}
			else
			{
				html += setRowSaveButton(session,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "simpanMaklumat","resetMaklumat", paramsButton, "Y", db);
			}
		}
		return html;
	}

	public String setupButtonBantahan(HttpSession session,String flagShowLantik,String jenis_transaction,Map setupSkrinHistory,String FIELD_PK,String id,String current_previous,String skrinName,String formName,String flag_editable,String mode,Map setupSkrin,String table_name,String divViewMaklumat,String paramsButton,String ID_PEMOHON_MAIN, Db db) throws Exception {
		String html = "";
		String AKTIVITI = "";
		if(setupSkrinHistory!=null)
		{
			AKTIVITI = (String)setupSkrinHistory.get("JENIS_AKTIVITI");
		}
		myLogger.info("divViewMaklumat : "+divViewMaklumat);
		if(flag_editable.equals("Y"))
		{
			if(mode.equals("view"))
			{
				html += setRowEditButtonBantahan(session,flagShowLantik,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "edit_bantahan", paramsButton, "Y", ID_PEMOHON_MAIN, db);
			}
			else
			{
				html += setRowSaveButtonBantahan(session,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "save_bantahan","edit_bantahan", paramsButton, "Y", db);
			}
		}
		return html;
	}


	public String setupButtonKeputusan(HttpSession session,String keputusanBicara,String flagShowLantik,String jenis_transaction,Map setupSkrinHistory,String FIELD_PK,String id,String current_previous,String skrinName,String formName,String flag_editable,String mode,Map setupSkrin,String table_name,String divViewMaklumat,String paramsButton,String ID_PEMOHON_MAIN, Db db) throws Exception {
		String html = "";
		String AKTIVITI = "";
		if(flag_editable.equals("Y"))
		{
			if(mode.equals("view"))
			{
				html += setRowEditButtonKeputusan(session,keputusanBicara,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "simpanMaklumat","resetMaklumat", paramsButton, "Y", db);
			}
			else
			{
				html += setRowSaveButtonKeputusan(session,keputusanBicara,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "simpanMaklumat","resetMaklumat", paramsButton, "Y", db);
			}
		}
		return html;
	}


	public String setRowPeringatanMesra(HttpSession session, String skrinName,String mode,String formName,String params,
			String retainScrollPosition,
			int totalHM, int totalKeterangan,
			int totalTH, int totalKeteranganTH,
			Db db) throws Exception {
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='left' valign='top' width='100%'>";
		html += "<div ><h5><span class=\"blink red\" ><i>Peringatan : </i></span>" +
				"<span class=\"blue\" >Pastikan maklumat perbicaraan adalah lengkap dan tepat sebelum menekan butang 'Simpan Keputusan'</span>" +
				"</h5></div>";
		html += "<div ><h5><span class=\"blink red\" ><i>Peringatan : </i></span>" +
				"<span class=\"blue\" >Setelah 'Keputusan Perbicaraan' disimpan, maklumat keterangan kehadiran waris dan perubahan maklumat tidak boleh dikemaskini</span>" +
				"</h5></div>";
		html += "<div ><h5><span class=\"blink red\" ><i>Peringatan : </i></span>" +
				"<span class=\"blue\" >Untuk semakan rumusan maklumat perbicaraan, sila klik pada menu 'LAMPIRAN PERUBAHAN MAKLUMAT' dan 'LAMPIRAN KETERANGAN YANG HADIR'</span>" +
				"</h5></div>";
		html += "<div id=\"divPeringatanMesra"+skrinName+"\" >";
		String checkXLengkap = "";
		if(totalHM > 0 && totalKeterangan < totalHM)
		{
			html += "<div ><h5><span class=\"blink red\" ><i>Peringatan : </i></span>" +
					"<span class=\"red\" >Masih terdapat "+(totalHM-totalKeterangan)+" daripada "+totalHM+" keterangan kehadiran 'waris' yang belum dicatat</span>" +
					"</h5></div>";
			checkXLengkap = "Y";
		}
		if(totalTH > 0 && totalKeteranganTH < totalTH)
		{
			html += "<div ><h5><span class=\"blink red\" ><i>Peringatan : </i></span>" +
					"<span class=\"red\" >Masih terdapat "+(totalTH-totalKeteranganTH)+" daripada "+totalTH+" keterangan kehadiran 'turut hadir' yang belum dicatat</span>" +
					"</h5></div>";
			checkXLengkap = "Y";
		}
		html +=	"<input type=\"hidden\" id=\"checkCatatan"+skrinName+"\" name=\"checkCatatan"+skrinName+"\" value = \""+checkXLengkap+"\" >";
		html += "</div>";
		html += "</td>";
		html += "</tr></table>";

		return html;
	}

	/*
	public String setupButtonCarianPegawaiStats(HttpSession session,String skrinName,String formName) throws Exception {

		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		html += " <input type=\"button\" id=\"cmdCari"+skrinName+"\" name=\"cmdCari"+skrinName+"\" value=\"Cari\" onClick=\"doAjaxCall$"+formName+"('cariLaporanStatsPegawai');\" >";
		html += " <input type=\"button\" id=\"cmdReset"+skrinName+"\" name=\"cmdReset"+skrinName+"\" value=\"Reset\" onClick=\"doAjaxCall$"+formName+"('cariLaporanStatsPegawai');\" >";
		html +=	"</td>";
		html += "</tr></table>";

		return html;
	}
	*/
	public String setupButtonCarianBicara(HttpSession session,String skrinName,String formName) throws Exception {

		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		html += " <input type=\"button\" id=\"cmdCari"+skrinName+"\" name=\"cmdCari"+skrinName+"\" value=\"Cari\" onClick=\"doDivAjaxCall"+formName+"('listPerbicaraan','cariListPerbicaraan','');\" >";
		html += " <input type=\"button\" id=\"cmdReset"+skrinName+"\" name=\"cmdReset"+skrinName+"\" value=\"Reset\" onClick=\"resetListPerbicaraan();\" >";
		html += " <input type=\"button\" id=\"cmdStatPegawai"+skrinName+"\" name=\"cmdStatPegawai"+skrinName+"\" value=\"Semak Statistik Pegawai\"  onclick=\"printLaporanStatPegawai();\" /> ";

		html +=	"</td>";
		html += "</tr></table>";

		return html;
	}

	public String setupButtonCarianTukarPegawai(HttpSession session,String skrinName,String formName) throws Exception {

		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<td align='center' valign='top' width='100%'>";
		html += " <input type=\"button\" id=\"cmdCari"+skrinName+"\" name=\"cmdCari"+skrinName+"\" value=\"Cari\" onClick=\"doDivAjaxCall"+formName+"('listPermohonanTukarPegawai','cariPermohonanTukarPegawai','');\" >";
		html += " <input type=\"button\" id=\"cmdReset"+skrinName+"\" name=\"cmdReset"+skrinName+"\" value=\"Reset\" onClick=\"resetListTukarPegawai();\" >";
		html += " <input type=\"button\" id=\"cmdCetak"+skrinName+"\" name=\"cmdCetak"+skrinName+"\" value=\"Cetak\"  onclick=\"printLaporanTukarpegawai();\" /> ";
		html += " <input type=\"button\" id=\"cmdStatPegawai"+skrinName+"\" name=\"cmdStatPegawai"+skrinName+"\" value=\"Semak Statistik Pegawai\"  onclick=\"printLaporanStatPegawai();\" /> ";

		html +=	"</td>";
		html += "</tr></table>";

		return html;
	}

	public String setupButtonTukarPegawai(HttpSession session,String flagShowLantik,String jenis_transaction,Map setupSkrinHistory,String FIELD_PK,String id,String current_previous,String skrinName,String formName,String flag_editable,String mode,Map setupSkrin,String table_name,String divViewMaklumat,String paramsButton,String ID_PEMOHON_MAIN,String pemohonOrKPP,String openFrom,Db db) throws Exception {
		String html = "";
		String AKTIVITI = "";
		if(flag_editable.equals("Y"))
		{
			if(mode.equals("view"))
			{
				html += setRowEditButtonTukarPegawai(session,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "simpanMaklumat","resetMaklumat", paramsButton, "Y", pemohonOrKPP,openFrom, db);
			}
			else
			{
				html += setRowSaveButtonTukarPegawai(session,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "simpanMaklumat","resetMaklumat", paramsButton, "Y", pemohonOrKPP,openFrom, db);
			}
		}
		return html;
	}

	public String setupButtonTukarPegawaiMultiple(HttpSession session,String flagShowLantik,String jenis_transaction,Map setupSkrinHistory,String FIELD_PK,String id,String current_previous,String skrinName,String formName,String flag_editable,String mode,Map setupSkrin,String table_name,String divViewMaklumat,String paramsButton,String ID_PEMOHON_MAIN,String pemohonOrKPP,String openFrom,Db db) throws Exception {
		String html = "";
		String AKTIVITI = "";
		if(flag_editable.equals("Y"))
		{
			if(mode.equals("view"))
			{
				html += setRowEditButtonTukarPegawaiMultiple(session,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "simpanMaklumat","resetMaklumat", paramsButton, "Y", pemohonOrKPP,openFrom, db);
			}
			else
			{
				html += setRowSaveButtonTukarPegawaiMultiple(session,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "simpanMaklumat","resetMaklumat", paramsButton, "Y", pemohonOrKPP,openFrom, db);
			}
		}
		return html;
	}

	//arief add tukar pegawai 2
	public String setupButtonTukarPegawai2(HttpSession session,String flagShowLantik,String jenis_transaction,Map setupSkrinHistory,String FIELD_PK,String id,String current_previous,String skrinName,String formName,String flag_editable,String mode,Map setupSkrin,String table_name,String divViewMaklumat,String paramsButton,String ID_PEMOHON_MAIN,String pemohonOrKPP,String openFrom,Db db) throws Exception {
		String html = "";
		String AKTIVITI = "";
		if(flag_editable.equals("Y"))
		{
			if(mode.equals("view"))
			{
				html += setRowEditButtonTukarPegawai2(session,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "simpanMaklumat","resetMaklumat", paramsButton, "Y", pemohonOrKPP,openFrom, db);
			}
			else
			{
				html += setRowSaveButtonTukarPegawai2(session,jenis_transaction,AKTIVITI,FIELD_PK,id,current_previous,skrinName,mode,setupSkrin,formName,table_name,divViewMaklumat, "simpanMaklumat","resetMaklumat", paramsButton, "Y", pemohonOrKPP,openFrom, db);
			}
		}
		return html;
	}


	public Double getBundaranBayaran(double bayaran) {
		myLogger.info("::::::::::: asal bayaran ::::::::::: "+bayaran);
		bayaran = (double) Math.round(bayaran * 100) / 100;
		myLogger.info("::::::::::: round bayaran ::::::::::: "+bayaran);
		String sen = String.valueOf(bayaran);
		sen = sen.substring(sen.length()-1, sen.length());
		myLogger.info("::::::::::: sen bayaran ::::::::::: "+sen);
		if ("1".equals(sen) || "6".equals(sen)){
			myLogger.info("conditon 1");
			bayaran = bayaran - 0.01D;
		} else if ("2".equals(sen) || "7".equals(sen)){
			myLogger.info("conditon 2");
			bayaran = bayaran - 0.02D;
		} else if ("3".equals(sen) || "8".equals(sen)){
			myLogger.info("conditon 3 > "+(bayaran + 0.02)+" + ");
			bayaran = bayaran + 0.02D;
		} else if ("4".equals(sen) || "9".equals(sen)){
			myLogger.info("conditon 4");
			bayaran = bayaran + 0.01D;
		}
		bayaran = (double) Math.round(bayaran * 100) / 100;
		myLogger.info("::::::::::: bayaran ::::::::::: "+bayaran);
		//return Utils.parseDouble(Utils.format2Decimal(bayaran));
		//return Utils.parseDouble(String.valueOf(bayaran));
		return bayaran;

	}

	public String setDateFormat(String dateStr)
	{
		String set = "";
		if(!dateStr.equals(""))
		{
			set = "to_date('" +dateStr+ "','dd/MM/yyyy')";
		}
		else
		{
			set = "''";
		}
		return set;
	}

	public String initCap(String st) {
	    if (st == null || st.trim().length() == 0)
	        return "";

	    if (st.substring(0, 1).equals(st.substring(0, 1).toUpperCase())) {
	        // Already initially capitalized.
	        return st;
	    } else {
	        // Capitalize first character
	        return st.substring(0, 1).toUpperCase() + st.substring(1);
	    }
	}

	public void saveTableBU(HttpSession session,String ID_PEMOHON, String ID_PELULUS, String ID_UNIT, String ID_NEGERI,
			String TARIKH_BICARA, Db db) throws Exception {

		Db db1 = null;
		String sql = "";
		long idBU = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");

		try {
			if(db!=null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}

			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			idBU = DB.getNextID(db1, "TBLPERMOHONANBANTUUNIT_SEQ");
			myLogger.info("idBU===="+idBU);
			r.add("ID_PERMOHONANBANTUUNIT", idBU);
			r.add("TARIKH_MOHON", r.unquote("sysdate"));
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			String tahun = formatter.format(now);
			String kod_negeri = getKodNegeri(session, ID_NEGERI, db1);
			String no_rujukan_ot = "BU/"+tahun + "/"+ kod_negeri + "/PPK/"+ String.format("%06d", getSeqNoTP(tahun,ID_NEGERI,"PPK","TBLRUJSEQESNOBU",db1));
			r.add("NO_BANTUUNIT", no_rujukan_ot);
			r.add("ID_STATUS",2);//LULUS
			r.add("ID_PEMOHON", ID_PEMOHON);
			r.add("ID_PELULUS", USER_ID_SYSTEM);
			r.add("TARIKH_LULUS", r.unquote("sysdate"));
			r.add("CATATAN_PEMOHON", "PERMOHONAN TUKAR PEGAWAI BICARA");
			r.add("CATATAN_PELULUS", "DILULUSKAN UNTUK PERMOHONAN TUKAR PEGAWAI BICARA");
			r.add("PERMOHONAN_NEGERI", "Y");
			String TARIKH_BU_MULA = "to_date('" + TARIKH_BICARA + "','dd/MM/yyyy')";
			String TARIKH_BU_AKHIR = "to_date('" + TARIKH_BICARA + "','dd/MM/yyyy')";
			r.add("TARIKH_MULA", r.unquote(TARIKH_BU_MULA));
			r.add("TARIKH_AKHIR", r.unquote(TARIKH_BU_AKHIR));
			r.add("ID_UNIT", ID_UNIT);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPERMOHONANBANTUUNIT");
			myLogger.info("BICARA ONLINE OT : SAVE BU : "+sql);
			stmt.executeUpdate(sql);

			AuditTrail.logActivity(null,session,"INS","TBLPERMOHONANBANTUUNIT [ID_PERMOHONANBANTUUNIT : "+idBU+"] Inserted",db1);
			//AuditTrail.logActivity(this,session,"INS","TBLPERMOHONANBANTUUNIT [ID_PERMOHONANBANTUUNIT : "+idBU+"] Updated",db1);
			//AuditTrail.logActivity(this,session,"UPD","TBLPPKPERBICARAAN [TUKAR PEGAWAI BICARAI : ID_UNITPSK ASAL (), ID_UNITPSK BARU ()]",db1);

			/*
			if(!ID_PERMOHONANBANTUUNIT.equals(""))
			{
				//hantarEmel(session,ID_PERMOHONANBANTUUNIT);
				AuditTrail.logActivity(this,session,"UPD","TBLPERMOHONANBANTUUNIT [ID_PERMOHONANBANTUUNIT : "+ID_PERMOHONANBANTUUNIT+"] Updated",db1);

			}
			else
			{
				//hantarEmel(session,idBU+"");
				AuditTrail.logActivity(this,session,"INS","TBLPERMOHONANBANTUUNIT [ID_PERMOHONANBANTUUNIT : "+idBU+"] Inserted",db1);
			}
			*/

		}
		finally {
			if (db == null)
				db1.close();
		}
	}

	public String getKodNegeri(HttpSession session, String ID_NEGERI, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			if (db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			String KOD_NEGERI="";
			sql = " SELECT KOD_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '"+ID_NEGERI+"' ";
			myLogger.info(" BICARA INTERAKTIF : getKodNegeri :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				while (rs.next()) {

					KOD_NEGERI = (rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI"));
				}
			return KOD_NEGERI;
		} finally {
			if (db == null)
				db1.close();
		}
	}


	public String getUserLoginPSK(HttpSession session, String NAMA_PEGAWAI, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			if (db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			String USER_LOGIN="";
			sql = " SELECT USER_LOGIN FROM (  SELECT  U.USER_NAME, U.USER_LOGIN, UTL_MATCH.EDIT_DISTANCE(UPPER(USER_NAME),UPPER('"+NAMA_PEGAWAI+"')) TOT_BEZA " +
					" FROM USERS U, USERS_INTERNAL UI  WHERE  U.USER_ID = UI.USER_ID AND UTL_MATCH.EDIT_DISTANCE(UPPER(USER_NAME),UPPER('"+NAMA_PEGAWAI+"'))  < 4  " +
					" AND  (UPPER(UI.FLAG_AKTIF) IS NULL OR UPPER(UI.FLAG_AKTIF) = '1' OR UPPER(UI.FLAG_AKTIF) = '' )  AND  LENGTH(U.USER_LOGIN) = 12  " +
					" ORDER BY UTL_MATCH.EDIT_DISTANCE(UPPER(USER_NAME),UPPER('"+NAMA_PEGAWAI+"')) ASC, U.TARIKH_MASUK DESC  ) WHERE ROWNUM = 1  ";
			myLogger.info(" BICARA INTERAKTIF : getUserLoginPSK :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				while (rs.next()) {

					USER_LOGIN = (rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
				}
			return USER_LOGIN;
		} finally {
			if (db == null)
				db1.close();
		}
	}

	public static synchronized int getSeqNoTP(String tahun,String id_negeri,String kod_modul, String namaTable,Db db)
			throws Exception {

		Db db1 = null;
		Connection conn = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			boolean found = false;
			sb.append("SELECT NO_TURUTAN FROM "+namaTable+" WHERE ");
			sb.append(" TAHUN = '" + tahun + "'");
			sb.append(" AND ID_NEGERI = '" + id_negeri + "'");
			sb.append(" AND KOD_MODUL = '" + kod_modul + "'");
			ResultSet rs = db1.getStatement().executeQuery(sb.toString());
			if (rs.next())
				found = true;
			myLogger.info("found :"+found);

			if (found) {
				increaseNoTP(tahun,id_negeri,kod_modul,namaTable,db1);
			} else {
				addNoTP(tahun,id_negeri,kod_modul,namaTable,db1);
			}
			ResultSet rs2 = db1.getStatement().executeQuery(sb.toString());
			if (rs2.next()) {

				seqno = rs2.getInt("NO_TURUTAN");

			}
		}finally {
			if (db == null)
				db1.close();
		}
		return seqno;
	}

	public static void increaseNoTP(String tahun,String id_negeri,String kod_modul,String namaTable,Db db) throws Exception {

		Db db1 = null;

		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+namaTable+"  SET ");
		sb.append("no_turutan = no_turutan + 1 ");
		sb.append(" WHERE ");
		sb.append(" TAHUN = '" + tahun + "'");
		sb.append(" AND id_negeri = '" + id_negeri + "'");
		sb.append(" AND kod_modul = '" + kod_modul + "'");

		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}

			db1.getStatement().executeUpdate(sb.toString());
		}
		finally {
			if (db == null)
				db1.close();
		}
	}


	public static void addNoTP(String tahun,String id_negeri,String kod_modul,String namaTable,Db db) throws Exception {

		Db db1 = null;

		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO "+namaTable+" (TAHUN,ID_NEGERI,KOD_MODUL,NO_TURUTAN)");
		sb.append(" VALUES (");
		sb.append("'" + tahun + "',");
		sb.append("'" + id_negeri + "',");
		sb.append("'" + kod_modul + "'");
		sb.append(",1)"); // initial value

		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}

			db1.getStatement().executeUpdate(sb.toString());
		}
		finally {
			if (db == null)
				db1.close();
		}
	}

	public String idUnitTukarPegawai(String id_negeri)
	{
		String id_unit = "";
		if(id_negeri.equals("1"))
		{
			id_unit = "82";
		}
		else if(id_negeri.equals("2"))
		{
			id_unit = "86";
		}
		else if(id_negeri.equals("3"))
		{
			id_unit = "88";
		}
		else if(id_negeri.equals("4"))
		{
			id_unit = "90";
		}
		else if(id_negeri.equals("5"))
		{
			id_unit = "92";
		}
		else if(id_negeri.equals("6"))
		{
			id_unit = "93";
		}
		else if(id_negeri.equals("7"))
		{
			id_unit = "27";
		}
		else if(id_negeri.equals("8"))
		{
			id_unit = "96";
		}
		else if(id_negeri.equals("9"))
		{
			id_unit = "28";
		}
		else if(id_negeri.equals("10"))
		{
			id_unit = "77";
		}
		else if(id_negeri.equals("11"))
		{
			id_unit = "99";
		}
		else if(id_negeri.equals("14"))
		{
			id_unit = "17";
		}
		else if(id_negeri.equals("15"))
		{
			id_unit = "1617158";
		}
		else if(id_negeri.equals("16"))
		{
			id_unit = "26";
		}
		return id_unit;
	}


	public void hantarEmelTukarPegawai(HttpSession session,String ID_PERBICARAAN, String ID_TUKARPEGAWAI, String ID_PERMOHONAN, String ID_PERMOHONANSIMATI,
			String FLAG_DAFTAR_TERUS,String ID_NEGERIPEGAWAIASAL, String ID_PEGAWAIASAL,String ID_NEGERIPEGAWAIBARU, String ID_PEGAWAIBARU,String STATUS_TUKARPEGAWAI,String FLAG_MULTiPLE,
			String SELECTED_NAMA_NEGERI,String SELECTED_NAMA_PEGAWAI,List senaraiSelected,
			Db db) throws Exception {
			myLogger.info("MASUK FUNCTION EMEL hantarEmelTukarPegawai");

			EmailProperty pro = EmailProperty.getInstance();
			EmailSender email = EmailSender.getInstance();
			email.FROM = pro.getFrom();
			email.MULTIPLE_RECIEPIENT = new String[1];
			String subject = "";
			String content = "";

			myLogger.info("STATUS_TUKARPEGAWAI : "+STATUS_TUKARPEGAWAI);
			myLogger.info("FLAG_DAFTAR_TERUS : "+FLAG_DAFTAR_TERUS);
			myLogger.info("ID_NEGERIPEGAWAIASAL : "+ID_NEGERIPEGAWAIASAL);
			myLogger.info("ID_PEGAWAIASAL : "+ID_PEGAWAIASAL);
			myLogger.info("ID_NEGERIPEGAWAIBARU : "+ID_NEGERIPEGAWAIBARU);
			myLogger.info("ID_PEGAWAIBARU : "+ID_PEGAWAIBARU);
			myLogger.info("FLAG_MULTiPLE : "+FLAG_MULTiPLE);
			myLogger.info("SELECTED_NAMA_NEGERI : "+SELECTED_NAMA_NEGERI);
			myLogger.info("SELECTED_NAMA_PEGAWAI : "+SELECTED_NAMA_PEGAWAI);

			Map viewPerbicaraan = null;
			Map viewTukarPegawai = null;
			if(FLAG_MULTiPLE.equals("N"))
			{
				viewPerbicaraan = viewPerbicaraan(session,ID_PERBICARAAN,ID_PERMOHONAN,db);
				viewTukarPegawai = getValueColumn(session,"",ID_PERBICARAAN,"tukarpegawai",ID_PERMOHONANSIMATI,"ID_TUKARPEGAWAI",ID_TUKARPEGAWAI,"TBLPPKTUKARPEGAWAI",db);
			}

			myLogger.info("hantarEmelTukarPegawai >>> viewPerbicaraan : "+viewPerbicaraan);
			myLogger.info("hantarEmelTukarPegawai >>> viewTukarPegawai : "+viewTukarPegawai);


			String NO_TUKARPEGAWAI = "";
			String INFO_TARIKH_MOHON = "";
			String INFO_TARIKH_KEPUTUSAN = "";
			String INFO_ID_PEGAWAIASAL = "";
			String INFO_ID_NEGERIPEGAWAIASAL = "";
			String INFO_ID_PEGAWAIBARU = "";
			String INFO_ID_NEGERIPEGAWAIBARU = "";
			String INFO_CATATAN_PEMOHON= "";
			String INFO_CATATAN_PELULUS= "";
			String INFO_ID_PELULUS= "";

			if(viewTukarPegawai!=null)
			{
				NO_TUKARPEGAWAI = (String)viewTukarPegawai.get("NO_TUKARPEGAWAI");

				INFO_TARIKH_MOHON = (String)viewTukarPegawai.get("TARIKH_MOHON");
				INFO_TARIKH_KEPUTUSAN = (String)viewTukarPegawai.get("TARIKH_KEPUTUSAN");
				INFO_ID_PEGAWAIASAL = (String)viewTukarPegawai.get("ID_PEGAWAIASAL");
				INFO_ID_NEGERIPEGAWAIASAL = (String)viewTukarPegawai.get("ID_NEGERIPEGAWAIASAL");
				INFO_ID_PEGAWAIBARU = (String)viewTukarPegawai.get("ID_PEGAWAIBARU");
				INFO_ID_NEGERIPEGAWAIBARU = (String)viewTukarPegawai.get("ID_NEGERIPEGAWAIBARU");
				INFO_CATATAN_PEMOHON = (String)viewTukarPegawai.get("CATATAN_PEMOHON");
				INFO_CATATAN_PELULUS = (String)viewTukarPegawai.get("CATATAN_PELULUS");
				INFO_ID_PELULUS = (String)viewTukarPegawai.get("ID_PELULUS");

			}


			//razman test
			if(STATUS_TUKARPEGAWAI.equals("1") && FLAG_MULTiPLE.equals("Y"))
			{
				subject += " PERMOHONAN PERTUKARAN PEGAWAI PERBICARAAN (PERMOHONAN BARU)";
			}
			else if(STATUS_TUKARPEGAWAI.equals("2") && FLAG_MULTiPLE.equals("Y"))
			{
				subject += " KEPUTUSAN PERTUKARAN PEGAWAI PERBICARAAN (LULUS)";
			}
			else if( STATUS_TUKARPEGAWAI.equals("3") && FLAG_MULTiPLE.equals("Y"))
			{
				subject += " KEPUTUSAN PERTUKARAN PEGAWAI PERBICARAAN (TOLAK)";
			}
			else if(STATUS_TUKARPEGAWAI.equals("1") && FLAG_MULTiPLE.equals("N"))
			{
				subject += " PERMOHONAN PERTUKARAN PEGAWAI PERBICARAAN (PERMOHONAN BARU) : "+NO_TUKARPEGAWAI;
			}
			else if(STATUS_TUKARPEGAWAI.equals("2") && FLAG_MULTiPLE.equals("N"))
			{
				subject += " KEPUTUSAN PERTUKARAN PEGAWAI PERBICARAAN (LULUS) : "+NO_TUKARPEGAWAI;
			}
			else if(STATUS_TUKARPEGAWAI.equals("3") && FLAG_MULTiPLE.equals("N"))
			{
				subject += " KEPUTUSAN PERTUKARAN PEGAWAI PERBICARAAN (TOLAK) : "+NO_TUKARPEGAWAI;
			}
			//subject += " (PENGUJIAN SISTEM MYETAPP) ";

			if(FLAG_MULTiPLE.equals("N"))
			{
				if(STATUS_TUKARPEGAWAI.equals("1"))
				{
					content += "Permohonan baru menukar pegawai perbicaraan. ";
				}
				else if(STATUS_TUKARPEGAWAI.equals("2"))
				{
					content += "Permohonan menukar pegawai perbicaraan kepada <b>"+SELECTED_NAMA_PEGAWAI+"</b> adalah lulus. ";
				}
				else if(STATUS_TUKARPEGAWAI.equals("2"))
				{
					content += "Permohonan menukar pegawai perbicaraan kepada <b>"+SELECTED_NAMA_PEGAWAI+"</b> adalah ditolak. ";
				}
				content += "Maklumat permohonan adalah seperti dibawah : <br><br>";


				if(viewPerbicaraan!=null)
				{

					String NO_FAIL = (String)viewPerbicaraan.get("NO_FAIL");
					String NAMA_SIMATI = (String)viewPerbicaraan.get("NAMA_SIMATI");
					String NAMA_PEMOHON = (String)viewPerbicaraan.get("NAMA_PEMOHON");
					String WAKTU_BICARA = (String)viewPerbicaraan.get("TARIKH_BICARA") + " " + (String)viewPerbicaraan.get("MASA_BICARA");
					String BIL_BICARA = ((Integer)viewPerbicaraan.get("BIL_BICARA")).toString();
					content += "<div style=\"border-bottom: 1px solid black;\" >MAKLUMAT PERBICARAAN</div><br>";
					content += "<table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"   > ";
					content += "<tr><td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td></tr>";
					content += setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"No. Fail","","","",ID_PERBICARAAN,"NO_FAIL","","text","Y","","",NO_FAIL,0,db);
					content += setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"Nama Simati","","","",ID_PERBICARAAN,"NAMA_SIMATI","","text","Y","","",NAMA_SIMATI,0,db);
					content += setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"Nama Pemohon","","","",ID_PERBICARAAN,"NAMA_PEMOHON","","text","Y","","",NAMA_PEMOHON,0,db);
					content += setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"Waktu Bicara","","","",ID_PERBICARAAN,"WAKTU_BICARA","","text","Y","","",WAKTU_BICARA,0,db);
					content += setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"Bil. Bicara","","","",ID_PERBICARAAN,"BIL_BICARA","","text","Y","","",BIL_BICARA,0,db);
					content += "</table>";
					content += " <br> ";

				}
				if(viewTukarPegawai!=null)
				{
					content += "<div style=\"border-bottom: 1px solid black;\" >MAKLUMAT PERMOHONAN TUKAR PEGAWAI PERBICARAAN</div><br>";

					content += "<table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"   > ";
					content += "<tr><td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td></tr>";
					content += setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"No. Permohonan Tukar Pegawai","","","",ID_PERBICARAAN,"NO_TUKARPEGAWAI","","text","Y","","",NO_TUKARPEGAWAI,0,db);
					content += setRowTextTarikh(session,"",ID_PERMOHONANSIMATI,"","view",null,"Tarikh Permohonan Tukar Pegawai","","","",ID_PERBICARAAN,"TARIKH_MOHON","Y","text","Y","10","Y",INFO_TARIKH_MOHON,0,db);
					content += setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Pegawai Asal","","","",ID_PERBICARAAN,"ID_PEGAWAIASAL","Y","select","Y","TBLPPKRUJUNIT","ID_UNITPSK","","NAMA_PEGAWAI","","","","","","","","","","",INFO_ID_PEGAWAIASAL,0,db);
					content += setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Negeri Pegawai Ganti","","","",ID_PERBICARAAN,"ID_NEGERIPEGAWAIBARU","Y","select","Y","TBLRUJNEGERI","ID_NEGERI","KOD_NEGERI","NAMA_NEGERI","","","","","","","","","","",INFO_ID_NEGERIPEGAWAIBARU,0,db);//dynamic ajax call
					content += setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Pegawai Ganti","","","",ID_PERBICARAAN,"ID_PEGAWAIBARU","Y","select","Y","TBLPPKRUJUNIT","ID_UNITPSK","","NAMA_PEGAWAI","","","","","","","","","","",INFO_ID_PEGAWAIBARU,0,db);
					content += setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Status Tukar Pegawai","","","",ID_PERBICARAAN,"STATUS_TUKARPEGAWAI","Y","select","Y","","","","","","","","","","","","","","",STATUS_TUKARPEGAWAI,0,db);
					if(!INFO_CATATAN_PEMOHON.equals(""))
					{
						content += setRowTextArea(session,"",ID_PERMOHONANSIMATI,"","view",null,"Catatan Pemohon","","","",ID_PERBICARAAN,"CATATAN_PEMOHON","","text","Y","4000","Y",INFO_CATATAN_PEMOHON,0,db);
					}
					if(!INFO_ID_PELULUS.equals(""))
					{
						content += setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Pegawai Pelulus","","","",ID_PERBICARAAN,"ID_PELULUS","Y","select","Y","USERS","USER_ID","","USER_NAME","","","","","","","","","","",INFO_ID_PELULUS,0,db);//dynamic ajax call
					}
					if(!INFO_TARIKH_KEPUTUSAN.equals(""))
					{
						content += setRowTextTarikh(session,"",ID_PERMOHONANSIMATI,"","view",null,"Tarikh Kelulusan Tukar Pegawai","","","",ID_PERBICARAAN,"TARIKH_KEPUTUSAN","Y","text","Y","10","Y",INFO_TARIKH_KEPUTUSAN,0,db);
					}
					if(!INFO_CATATAN_PELULUS.equals(""))
					{
						content += setRowTextArea(session,"",ID_PERMOHONANSIMATI,"","view",null,"Catatan Pelulus","","","",ID_PERBICARAAN,"CATATAN_PELULUS","","text","Y","4000","Y",INFO_CATATAN_PELULUS,0,db);
					}

					content += "</table>";
				}
				/*
				content += "<table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\"   > ";
				content += "<tr>" +
						"<td valign=\"top\" width=\"1%\" ></td>" +
						"<td valign=\"top\" width=\"28%\" align=\"left\" >No. Permohonan</td>" +
						"<td valign=\"top\" width=\"1%\" align=\"center\" >:</td>" +
						"<td valign=\"top\" width=\"70%\" align=\"left\" ></td>" +
						"</tr>";
				content += "</table>";
				*/

			}
			else if(FLAG_MULTiPLE.equals("Y"))
			{
				if(senaraiSelected.size() > 0 )
				{
					if(STATUS_TUKARPEGAWAI.equals("1"))
					{
						content += "Permohonan menukar pegawai perbicaraan kepada <b>"+SELECTED_NAMA_PEGAWAI+"</b>. ";
					}
					else if(STATUS_TUKARPEGAWAI.equals("2"))
					{
						content += "Permohonan menukar pegawai perbicaraan kepada <b>"+SELECTED_NAMA_PEGAWAI+"</b> adalah lulus. ";
					}
					else if(STATUS_TUKARPEGAWAI.equals("3"))
					{
						content += "Permohonan menukar pegawai perbicaraan adalah ditolak. ";
					}
					content += "Senarai perbicaraan yang terlibat adalah seperti dibawah : <br><br>";

					content += "<table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"   > ";
					content += "<tr>" +
							"<td valign=\"top\" align=\"center\" width=\"5%\" style=\"border: 1px solid black;\" ><b>No.</b></td>" +
							"<td valign=\"top\" align=\"left\"  style=\"border: 1px solid black;\"><b>No Fail (Bil. Bicara)</b></td>" +
							"<td valign=\"top\" align=\"center\"  style=\"border: 1px solid black;\"><b>Tarikh Bicara</b></td>" +
							"<td valign=\"top\" align=\"left\"  style=\"border: 1px solid black;\"><b>Masa Bicara</b></td>" +
							"<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\"><b>Pegawai Asal</b></td>" +
							"<td valign=\"top\" align=\"left\"  style=\"border: 1px solid black;\"><b>Pegawai Ganti</b></td>" +
							"</tr> ";

					for (int i = 0; i < senaraiSelected.size(); i++)
					{
						Map itemSelected = (Map) senaraiSelected.get(i);
						String NO = (String) itemSelected.get("NO");
						String NO_FAIL = (String) itemSelected.get("NO_FAIL");
						String TARIKH_BICARA = (String) itemSelected.get("TARIKH_BICARA");
						String MASA_BICARA = (String) itemSelected.get("MASA_BICARA");
						String BIL_BICARA = (String) itemSelected.get("BIL_BICARA");
						String PEG_PENGENDALI = (String) itemSelected.get("PEG_PENGENDALI");
						String NAMA_PEGAWAI_BARU = (String) itemSelected.get("NAMA_PEGAWAI_BARU");

						content += "<tr>" +
								"<td valign=\"top\" align=\"center\" style=\"border: 1px solid black;\">"+NO+"</td>" +
								"<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+NO_FAIL+" ("+BIL_BICARA+")</td>" +
								"<td valign=\"top\" align=\"center\" style=\"border: 1px solid black;\">"+TARIKH_BICARA +"</td>" +
								"<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+MASA_BICARA+"</td>" +
								"<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+PEG_PENGENDALI+"</td>";

						if(STATUS_TUKARPEGAWAI.equals("1"))
						{
							content += "<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+SELECTED_NAMA_PEGAWAI+"</td>";
						}
						else if(STATUS_TUKARPEGAWAI.equals("2") || STATUS_TUKARPEGAWAI.equals("3"))
						{
							content += "<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+NAMA_PEGAWAI_BARU+"</td>";
						}

						content += "</tr> ";

					}
					content += "</table>";
				}
			}



			content += "<br><br>Sila semak permohonan ini pada skrin 'Bicara Interaktif' untuk tindakan lanjut. Sekian, terima kasih.";
			content += "<br><br>Nota : Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas.";


			List collectionSendTo = Collections.synchronizedList(new ArrayList());
			//Map h_collectionSendTo = Collections.synchronizedMap(new HashMap());
			//h_collectionSendTo.put("EMEL","etappsupport@jkptg.gov.my");
			collectionSendTo.add(setHashEmel("etappsupport@jkptg.gov.my"));

			//capture reciepient
			if(FLAG_MULTiPLE.equals("N"))
			{
				//single
				if(FLAG_DAFTAR_TERUS.equals("Y"))
				{
					if(STATUS_TUKARPEGAWAI.equals("2"))
					{
						//inform pegawai baru
						//myLogger.info("situation 1 >> INFO_ID_PEGAWAIBARU : "+INFO_ID_PEGAWAIBARU);
						//myLogger.info("situation 1 >> INFO_ID_PEGAWAIASAL : "+INFO_ID_PEGAWAIASAL);
						//myLogger.info("situation 1 >>  emel pegawai baru : "+emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db));
						//myLogger.info("situation 1 >>  emel pegawai asal : "+emelPegawaiPusaka(session,INFO_ID_PEGAWAIASAL,db));
						String emelPegawaiBaru = emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db);
						String emelPegawaiAsal = emelPegawaiPusaka(session,INFO_ID_PEGAWAIASAL,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiBaru));
						}
						if(!emelPegawaiAsal.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiAsal));
						}

					}
				}
				else
				{
					if(STATUS_TUKARPEGAWAI.equals("1"))
					{
						myLogger.info("situation 2 >> INFO_ID_NEGERIPEGAWAIASAL : "+INFO_ID_NEGERIPEGAWAIASAL);
						myLogger.info("situation 2 >> INFO_ID_NEGERIPEGAWAIBARU : "+INFO_ID_NEGERIPEGAWAIBARU);
						if(INFO_ID_NEGERIPEGAWAIASAL.equals(INFO_ID_NEGERIPEGAWAIBARU))
						{
							//kalo tidak melangkau negeri, inform kkp negeri
							List listKPP = listKPP(session,INFO_ID_NEGERIPEGAWAIBARU,"NEGERI",db);
							if(listKPP.size() > 0)
							{
								//email.MULTIPLE_RECIEPIENT = new String[listKPP.size()];
								for(int i = 0; i < listKPP.size();i++)
								{
									Map m = (Map) listKPP.get(i);
									String EMEL = (String) m.get("EMEL");
									//email.MULTIPLE_RECIEPIENT[i] = EMEL;
									if(!EMEL.equals(""))
									{
										collectionSendTo.add(setHashEmel(EMEL));
									}
								}
							}
						}
						else
						{
							//kalo melangkau negeri, inform kkp hq & pengarah
							List listKPP = listKPP(session,"","HQ",db);
							if(listKPP.size() > 0)
							{
								//email.MULTIPLE_RECIEPIENT = new String[listKPP.size()];
								for(int i = 0; i < listKPP.size();i++)
								{
									Map m = (Map) listKPP.get(i);
									String EMEL = (String) m.get("EMEL");
									//email.MULTIPLE_RECIEPIENT[i] = EMEL;
									if(!EMEL.equals(""))
									{
										collectionSendTo.add(setHashEmel(EMEL));
									}
								}
							}
						}

						String emelPegawaiBaru = emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiBaru));
						}

					}
					else if(STATUS_TUKARPEGAWAI.equals("2") || STATUS_TUKARPEGAWAI.equals("3"))
					{
						//inform pegawai asal & asal
						//myLogger.info("situation 3 >> INFO_ID_PEGAWAIBARU : "+INFO_ID_PEGAWAIBARU);
						//myLogger.info("situation 3 >> INFO_ID_PEGAWAIASAL : "+INFO_ID_PEGAWAIASAL);
						//myLogger.info("situation 3 >>  emel pegawai baru : "+emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db));
						//myLogger.info("situation 3 >>  emel pegawai asal : "+emelPegawaiPusaka(session,INFO_ID_PEGAWAIASAL,db));
						String emelPegawaiBaru = emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db);
						String emelPegawaiAsal = emelPegawaiPusaka(session,INFO_ID_PEGAWAIASAL,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiBaru));
						}
						if(!emelPegawaiAsal.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiAsal));
						}
					}

				}
			}
			else if(FLAG_MULTiPLE.equals("Y"))
			{
				//multiple
				if(FLAG_DAFTAR_TERUS.equals("Y"))
				{
					if(STATUS_TUKARPEGAWAI.equals("2"))
					{
						//myLogger.info("situation 4 >> ID_PEGAWAIBARU : "+ID_PEGAWAIBARU);
						//myLogger.info("situation 4 >> ID_PEGAWAIASAL : "+ID_PEGAWAIASAL);
						//inform pegawai baru
						//myLogger.info("situation 4 >>  emel pegawai baru : "+emelPegawaiPusaka(session,ID_PEGAWAIBARU,db));
						//myLogger.info("situation 4 >>  emel pegawai asal : "+emelPegawaiPusaka(session,ID_PEGAWAIASAL,db));
						String emelPegawaiBaru = emelPegawaiPusaka(session,ID_PEGAWAIBARU,db);
						String emelPegawaiAsal = emelPegawaiPusaka(session,ID_PEGAWAIASAL,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiBaru));
						}
						if(!emelPegawaiAsal.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiAsal));
						}
					}
				}
				else
				{
					if(STATUS_TUKARPEGAWAI.equals("1"))
					{
						//kalo tidak melangkau negeri, inform kkp negeri
						//kalo melangkau negeri, inform kkp hq & pengarah
						myLogger.info("situation 5 >> ID_NEGERIPEGAWAIASAL : "+ID_NEGERIPEGAWAIASAL);
						myLogger.info("situation 5 >> ID_NEGERIPEGAWAIBARU : "+ID_NEGERIPEGAWAIBARU);

						if(ID_NEGERIPEGAWAIASAL.equals(ID_NEGERIPEGAWAIBARU))
						{
							//kalo tidak melangkau negeri, inform kkp negeri
							List listKPP = listKPP(session,ID_NEGERIPEGAWAIBARU,"NEGERI",db);
							if(listKPP.size() > 0)
							{
								//email.MULTIPLE_RECIEPIENT = new String[listKPP.size()];
								for(int i = 0; i < listKPP.size();i++)
								{
									Map m = (Map) listKPP.get(i);
									String EMEL = (String) m.get("EMEL");
									//email.MULTIPLE_RECIEPIENT[i] = EMEL;
									if(!EMEL.equals(""))
									{
										collectionSendTo.add(setHashEmel(EMEL));
									}
								}
							}
						}
						else
						{
							//kalo melangkau negeri, inform kkp hq & pengarah
							List listKPP = listKPP(session,"","HQ",db);
							if(listKPP.size() > 0)
							{
								//email.MULTIPLE_RECIEPIENT = new String[listKPP.size()];
								for(int i = 0; i < listKPP.size();i++)
								{
									Map m = (Map) listKPP.get(i);
									String EMEL = (String) m.get("EMEL");
									//email.MULTIPLE_RECIEPIENT[i] = EMEL;
									if(!EMEL.equals(""))
									{
										collectionSendTo.add(setHashEmel(EMEL));
									}
								}
							}
						}

						String emelPegawaiBaru = emelPegawaiPusaka(session,ID_PEGAWAIBARU,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiBaru));
						}

					}
					else if(STATUS_TUKARPEGAWAI.equals("2") || STATUS_TUKARPEGAWAI.equals("3"))
					{
						//inform pegawai asal
						//myLogger.info("situation 6 >> ID_PEGAWAIBARU : "+ID_PEGAWAIBARU);
						//myLogger.info("situation 6 >> ID_PEGAWAIASAL : "+ID_PEGAWAIASAL);
						//myLogger.info("situation 6 >>  emel pegawai baru : "+emelPegawaiPusaka(session,ID_PEGAWAIBARU,db));
						//myLogger.info("situation 6 >>  emel pegawai asal : "+emelPegawaiPusaka(session,ID_PEGAWAIASAL,db));
						String emelPegawaiBaru = emelPegawaiPusaka(session,ID_PEGAWAIBARU,db);
						String emelPegawaiAsal = emelPegawaiPusaka(session,ID_PEGAWAIASAL,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiBaru));
						}
						if(!emelPegawaiAsal.equals(""))
						{
							collectionSendTo.add(setHashEmel(emelPegawaiAsal));
						}
					}
				}
			}
			/*
			else
			{
				email.MULTIPLE_RECIEPIENT = new String[1];
				email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";
			}
			*/
			//sementara, nnti komen balik

			email.MULTIPLE_RECIEPIENT = new String[collectionSendTo.size()];
			for(int i = 0; i < collectionSendTo.size();i++)
			{
				Map m = (Map) collectionSendTo.get(i);
				String EMEL = (String) m.get("EMEL");
				myLogger.info(">>>>>>>>>>>>>>>>>>> SENARAI PENERIMA EMEL "+EMEL);
				//SEMENTARA
				email.MULTIPLE_RECIEPIENT[i] = EMEL;
				//email.MULTIPLE_RECIEPIENT[i] = "razman.zainal@gmail.com";
			}


			email.TO_CC = new String[1];
			email.TO_CC[0] = "razman.zainal@gmail.com";




			/*
			Hashtable viewBU = viewBU(session, ID_PERMOHONANBANTUUNIT);
			String ID_STATUS = (String)viewBU.get("ID_STATUS");
			String CATATAN_PELULUS = (String)viewBU.get("CATATAN_PELULUS");
			String CATATAN_PEMOHON = (String)viewBU.get("CATATAN_PEMOHON");
			String NO_BANTUUNIT = (String)viewBU.get("NO_BANTUUNIT");
			String NAMA_PEMOHON = (String)viewBU.get("NAMA_PEMOHON");
			String NAMA_PELULUS = (String)viewBU.get("NAMA_PELULUS");
			String EMEL_PEMOHON = (String)viewBU.get("EMEL_PEMOHON");
			String ID_UNIT = (String)viewBU.get("ID_UNIT");
			String PEJABAT_PEMOHON = (String)viewBU.get("PEJABAT_PEMOHON");
			String NAMA_UNIT = (String)viewBU.get("NAMA_UNIT");
			//String EMEL_PELULUS = (String)maklumatOT_USER.get("EMEL_PELULUS");
			myLogger.info("ID_STATUS===="+ID_STATUS);
			if(ID_STATUS.equals("2") || ID_STATUS.equals("3"))//permohonan lulus or tolak
			{
				subject = " KEPUTUSAN PERMOHONAN BANTU UTILITI NEGERI : "+NO_BANTUUNIT;
				content = " Tujuan : Pemakluman status Permohonan Bantu Utiliti Negeri. ";
				content += "<br><br>";
				if(ID_STATUS.equals("2"))
				{
					content = " Permohonan bantu utiliti negeri anda adalah diluluskan oleh "+NAMA_PELULUS+". Sila semak permohonan ini di MyeTaPP.";
				}else
				{
					content = " Permohonan bantu utiliti negeri anda adalah ditolak oleh "+NAMA_PELULUS+". Sila semak permohonan ini di MyeTaPP.";
				}

				content += "<br><br>Catatan Pelulus: "+CATATAN_PELULUS;
				content += "<br><br>";
				content += "<br><br><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em> ";

				email.MULTIPLE_RECIEPIENT[0] = EMEL_PEMOHON;
			}
			else if(ID_STATUS.equals("1"))//pemohonan baru
			{
				subject = " PERMOHONAN BARU BANTU UTILITI NEGERI : "+NO_BANTUUNIT;
				content = " Tujuan : Pemakluman bahawa anda menerima permohonan Bantu Utiliti Negeri.";

				content += "<br> Permohonan telah dibuat oleh "+NAMA_PEMOHON+" daripada "+PEJABAT_PEMOHON+" pada unit "+NAMA_UNIT+"";
				content += "<br> Catatan Pemohon : "+CATATAN_PEMOHON;
				content += "<br><br> Tindakan : Sila buat kelulusan bagi permohonan di MyeTaPP.";
				content += "<br> Menu : Login ke MyeTaPP >> Utility >> BANTU UTILITI NEGERI";

				content += "<br><br><br>";
				content += "<br><br><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em> ";
				//email.MULTIPLE_RECIEPIENT[0] = EMEL_PELULUS;

				List listEmelPelulus = listEmelPelulus(session, ID_UNIT);
				email.MULTIPLE_RECIEPIENT = new String[listEmelPelulus.size()];
				for(int i = 0; i < listEmelPelulus.size();i++)
				{
					Map m = (Map) listEmelPelulus.get(i);
					String EMEL = (String) m.get("EMEL");
					//int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
					email.MULTIPLE_RECIEPIENT[i] = EMEL;
				}
			}
			*/



			email.SUBJECT = subject;
			email.MESSAGE = content;
			//sementara
			email.sendEmail();

		 }



/*
	 public List listEmelPelulus(HttpSession session, String ID_UNIT)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listEmelPelulus = null;
				String sql = "";
				try {
					db = new Db();
					stmt = db.getStatement();
					myLogger.info("------------masuk dlm emel UPP bantu unit-----------");
					String checkPermohonanNegeri = "";
					checkPermohonanNegeri = checkIfPermohonanNegeri(session,ID_UNIT);

					sql = " SELECT DISTINCT UI.EMEL FROM USERS U, USERS_INTERNAL UI, (  "+
							" SELECT ROLE_ID, USER_ID FROM USER_ROLE WHERE ROLE_ID = 'adminppk' "+
							" ) UR "+
							" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID(+)  "+
							" AND (U.USER_ROLE = 'adminppk' OR UR.ROLE_ID = 'adminppk' )  ";
							if(checkPermohonanNegeri.equals("Y")){
								sql+=" AND UI.ID_PEJABATJKPTG = 26";
							}else{
								sql+=" AND UI.ID_PEJABATJKPTG = "+ID_UNIT;
							}
					sql+=" AND UI.ID_JAWATAN = 5  "+ //KPP
						" AND UI.EMEL IS NOT NULL ";

					myLogger.info(" SQL : listEmelPelulus :"+ sql);
					rs = stmt.executeQuery(sql);
					listEmelPelulus = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
						listEmelPelulus.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
				return listEmelPelulus;
			}




*/


	public String emelPegawaiPusaka(HttpSession session,String ID_PEGAWAI,Db db) throws Exception {
		String returnemel = "";
		if(!ID_PEGAWAI.equals(""))
		{
			Map infoPegawai = getValueColumn(session,"","","","","ID_UNITPSK",ID_PEGAWAI,"TBLPPKRUJUNIT", "", db);
			String USER_ID = (String)infoPegawai.get("USER_ID");
			String NAMA_PEGAWAI = (String)infoPegawai.get("NAMA_PEGAWAI");

			if(!USER_ID.equals(""))
			{
				Map infoUsers = getValueColumn(session,"","","","","USER_ID",USER_ID,"USERS_INTERNAL", "", db);
				String emelinfoUsers = (String)infoUsers.get("EMEL");
				if(!emelinfoUsers.equals(""))
				{
					returnemel = emelinfoUsers;
				}
				else
				{
					Map getDetailUserEmel = getDetailUserEmel(session, NAMA_PEGAWAI, db);
					String emelgetDetailUserEmel = (String)getDetailUserEmel.get("EMEL");
					if(!emelgetDetailUserEmel.equals(""))
					{
						returnemel = emelgetDetailUserEmel;
					}
				}
			}
			else
			{
				Map getDetailUserEmel = getDetailUserEmel(session, NAMA_PEGAWAI, db);
				String emelgetDetailUserEmel = (String)getDetailUserEmel.get("EMEL");
				if(!emelgetDetailUserEmel.equals(""))
				{
					returnemel = emelgetDetailUserEmel;
				}
			}
		}
		return returnemel;
	}

	public Map setHashEmel(String emel)
	{
		Map h = Collections.synchronizedMap(new HashMap());
		h.put("EMEL",emel);
		return h;
	}

	@SuppressWarnings("unchecked")
	public List listKPP(HttpSession session,String id_negeri,String jenis, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();

		sql = " SELECT DISTINCT UI.EMEL FROM USERS U, USERS_INTERNAL UI, (  "+
				" SELECT ROLE_ID, USER_ID FROM USER_ROLE WHERE ROLE_ID = 'adminppk' "+
				" ) UR "+
				" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID(+)  "+
				" AND (U.USER_ROLE = 'adminppk' OR UR.ROLE_ID = 'adminppk' )  ";
		if(jenis.equals("HQ"))
		{
			sql+= " AND UI.ID_NEGERI = '16' ";
			sql+= " AND (UI.ID_JAWATAN = 5 OR UI.ID_JAWATAN = 4)   "; //KPP & PENGARAH
		}
		else if(jenis.equals("NEGERI") && !id_negeri.equals(""))
		{
			sql += " AND UI.ID_NEGERI = '"+id_negeri+"' ";
			sql += " AND UI.ID_JAWATAN = 5  "; //KPP
		}
		sql += "  AND UI.EMEL IS NOT NULL AND UI.ID_SEKSYEN = '2'  " +
				" AND (UPPER(UI.FLAG_AKTIF) IS NULL OR UPPER(UI.FLAG_AKTIF) = '1' OR UPPER(UI.FLAG_AKTIF) = '' ) ";


		myLogger.info(" BICARA INTERAKTIF : SQL listKPP :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("EMEL",rs == null ? "" :rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}

	//Map infoPegawaiBaru = getValueColumn(session,"","","","","ID_UNITPSK",INFO_ID_PEGAWAIBARU,"TBLPPKRUJUNIT", "", db);
	//Map infoPegawaiAsal = getValueColumn(session,"","","","","ID_UNITPSK",INFO_ID_PEGAWAIASAL,"TBLPPKRUJUNIT", "", db);

	/*
	public String getValueFormHash(Map obj,String ColumnName, String type)
	{
		String returnValue = "";
		if(type.equals("String"))
		{
			returnValue= (String)obj.get(ColumnName);
		}
		else if(type.equals("Int"))
		{
			returnValue= (Integer)obj.get(ColumnName)+"";
		}
		return returnValue;
	}
	*/


	@SuppressWarnings("unchecked")
	public List listStatsJumlahBicara(HttpSession session,String id_negeri,String tarikhMula,String tarikhAkhir, String namaPegawai, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";

		try{

		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		stmt = db1.getStatement();

		sql = " SELECT (CASE "+
				" WHEN TRIM(TO_CHAR(TO_DATE(TO_CHAR(SUPERMAIN.TARIKH_BICARA_DISPLAY,'DD/MM/YYYY'),'dd/mm/yyyy'), 'DAY')) = 'MONDAY' THEN 'ISNIN'  "+
				" WHEN TRIM(TO_CHAR(TO_DATE(TO_CHAR(SUPERMAIN.TARIKH_BICARA_DISPLAY,'DD/MM/YYYY'),'dd/mm/yyyy'), 'DAY')) = 'TUESDAY' THEN 'SELASA'  "+
				" WHEN TRIM(TO_CHAR(TO_DATE(TO_CHAR(SUPERMAIN.TARIKH_BICARA_DISPLAY,'DD/MM/YYYY'),'dd/mm/yyyy'), 'DAY')) = 'WEDNESDAY' THEN 'RABU'  "+
				" WHEN TRIM(TO_CHAR(TO_DATE(TO_CHAR(SUPERMAIN.TARIKH_BICARA_DISPLAY,'DD/MM/YYYY'),'dd/mm/yyyy'), 'DAY')) = 'THURSDAY' THEN 'KHAMIS'  "+
				" WHEN TRIM(TO_CHAR(TO_DATE(TO_CHAR(SUPERMAIN.TARIKH_BICARA_DISPLAY,'DD/MM/YYYY'),'dd/mm/yyyy'), 'DAY')) = 'FRIDAY' THEN 'JUMAAT' "+
				" WHEN TRIM(TO_CHAR(TO_DATE(TO_CHAR(SUPERMAIN.TARIKH_BICARA_DISPLAY,'DD/MM/YYYY'),'dd/mm/yyyy'), 'DAY')) = 'SATURDAY' THEN 'SABTU'  "+
				" WHEN TRIM(TO_CHAR(TO_DATE(TO_CHAR(SUPERMAIN.TARIKH_BICARA_DISPLAY,'DD/MM/YYYY'),'dd/mm/yyyy'), 'DAY')) = 'SUNDAY' THEN 'AHAD'  "+
				" ELSE '' END) AS NAMA_HARI,TO_CHAR(SUPERMAIN.TARIKH_BICARA_DISPLAY,'DD/MM/YYYY') AS TARIKH_BICARA_DISPLAY, SUPERMAIN.* FROM ( "+
				" SELECT * FROM (  "+
				" SELECT 3 AS LAYER1, 2 AS LAYER2, DATE_REF_TBL.DR AS TARIKH_BICARA_DISPLAY,  "+
				" DATE_REF_TBL.ID_UNITPSK,DATE_REF_TBL.NAMA_PEGAWAI,NULL AS NAMA_NEGERI,DATE_REF_TBL.ID_NEGERI, MAIN1.TARIKH_BICARA, NVL(MAIN1.JUMLAH_PERBICARAAN,0) AS JUMLAH_PERBICARAAN,  "+
				" NVL(MAIN1.T0830AM_0859AM,0) AS T0830AM_0859AM,  NVL(MAIN1.T0900AM_0929AM,0) AS T0900AM_0929AM, NVL(MAIN1.T0930AM_0959AM,0)  AS T0930AM_0959AM,    "+
				" NVL(MAIN1.T1000AM_1029AM,0) AS T1000AM_1029AM, NVL(MAIN1.T1030AM_1059AM,0) AS T1030AM_1059AM,  NVL(MAIN1.T1100AM_1129AM,0) AS T1100AM_1129AM,   "+
				" NVL(MAIN1.T1130AM_1159AM,0) AS T1130AM_1159AM,    "+
				" NVL(MAIN1.T1200PM_1229PM,0) AS T1200PM_1229PM, NVL(MAIN1.T1230PM_1259PM,0) AS T1230PM_1259PM,  NVL(MAIN1.T0100PM_0129PM,0) AS T0100PM_0129PM,  "+
				" NVL(MAIN1.T0130PM_0159PM,0) AS T0130PM_0159PM,  NVL(MAIN1.T0200PM_0229PM,0) AS T0200PM_0229PM, NVL(MAIN1.T0230PM_0259PM,0) AS T0230PM_0259PM,    "+
				" NVL(MAIN1.T0300PM_0329PM,0) AS T0300PM_0329PM,   "+
				" NVL(MAIN1.T0330PM_0359PM,0) AS T0330PM_0359PM,  NVL(MAIN1.T0400PM_0429PM,0) AS T0400PM_0429PM, NVL(MAIN1.T0430PM_0459PM,0) AS T0430PM_0459PM,  "+
				" NVL(MAIN1.T0500PM_0529PM,0) AS T0500PM_0529PM, NVL(MAIN1.T0530PM_0559PM,0) AS T0530PM_0559PM  "+
				" FROM (    "+
				" SELECT DISTINCT  UPSK.ID_UNITPSK, UPPER(UPSK.NAMA_PEGAWAI) AS NAMA_PEGAWAI,NULL AS NAMA_NEGERI,UPSK.ID_NEGERI,PERB.TARIKH_BICARA,  "+
				" COUNT(DISTINCT PERB.ID_PERBICARAAN) AS JUMLAH_PERBICARAAN,    "+
				" NVL(SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('08:30 AM','HH:MI AM') AND TO_DATE('08:59 AM','HH:MI AM') THEN 1 ELSE 0 END),0) AS T0830AM_0859AM,  "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('09:00 AM','HH:MI AM') AND TO_DATE('09:29 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T0900AM_0929AM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('09:30 AM','HH:MI AM') AND TO_DATE('09:59 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T0930AM_0959AM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('10:00 AM','HH:MI AM') AND TO_DATE('10:29 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T1000AM_1029AM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('10:30 AM','HH:MI AM') AND TO_DATE('10:59 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T1030AM_1059AM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('11:00 AM','HH:MI AM') AND TO_DATE('11:29 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T1100AM_1129AM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('11:30 AM','HH:MI AM') AND TO_DATE('11:59 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T1130AM_1159AM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('12:00 PM','HH:MI AM') AND TO_DATE('12:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T1200PM_1229PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('12:30 PM','HH:MI AM') AND TO_DATE('12:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T1230PM_1259PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('01:00 PM','HH:MI AM') AND TO_DATE('01:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0100PM_0129PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('01:30 PM','HH:MI AM') AND TO_DATE('01:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0130PM_0159PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('02:00 PM','HH:MI AM') AND TO_DATE('02:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0200PM_0229PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('02:30 PM','HH:MI AM') AND TO_DATE('02:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0230PM_0259PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('03:00 PM','HH:MI AM') AND TO_DATE('03:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0300PM_0329PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('03:30 PM','HH:MI AM') AND TO_DATE('03:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0330PM_0359PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('04:00 PM','HH:MI AM') AND TO_DATE('04:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0400PM_0429PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('04:30 PM','HH:MI AM') AND TO_DATE('04:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0430PM_0459PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('05:00 PM','HH:MI AM') AND TO_DATE('05:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0500PM_0529PM,    "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('05:30 PM','HH:MI AM') AND TO_DATE('05:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0530PM_0559PM ,  "+
				" (TO_CHAR(PERB.TARIKH_BICARA,'DD/MM/YYYY') || '_' || UPSK.ID_UNITPSK) AS DRPG  "+
				" FROM TBLPPKRUJUNIT UPSK,     "+
				" (SELECT DISTINCT PR.ID_PERBICARAAN, RK.NAMA_PEGAWAI, " +
				//" PR.ID_UNITPSK, " +
				/*
					" CASE " +
					" WHEN CHECK_TP.ID_PEGAWAIBARU IS NOT NULL " +
					" THEN CHECK_TP.ID_PEGAWAIBARU " +
					" WHEN CHECK_TP.ID_PEGAWAIBARU IS NULL " +
					" THEN PR.ID_UNITPSK " +
					" END AS ID_UNITPSK,   " +
					*/

					" (CASE WHEN ID_UNITPSK_NEW IS NOT NULL THEN PR.ID_UNITPSK_NEW ELSE PR.ID_UNITPSK END) AS ID_UNITPSK, "+

				" P.ID_DAERAHMHN, F.ID_NEGERI, F.NO_FAIL, PR.TARIKH_BICARA, SYSDATE,  "+
				" PR.MASA_BICARA,TO_DATE(  CASE WHEN PR.MASA_BICARA LIKE '%.%' THEN  (CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, 1, INSTR(PR.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' ||  "+
				" CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0' ELSE PR.MASA_BICARA END    "+
				" WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0'   ELSE PR.MASA_BICARA END)     "+
				" WHEN LENGTH(PR.MASA_BICARA) = 4 THEN SUBSTR(PR.MASA_BICARA, 1, 2) || '.' || SUBSTR(PR.MASA_BICARA, 3)  ELSE '' END || (CASE WHEN PR.JENIS_MASA_BICARA = '1' THEN ' AM'  "+
				" WHEN PR.JENIS_MASA_BICARA = '2' THEN ' PM'   WHEN PR.JENIS_MASA_BICARA = '3' THEN ' PM' ELSE '' END),'HH:MI AM') AS WAKTU_BICARA_CONVERT    "+
				" FROM TBLPPKPERBICARAAN PR, TBLPPKPERINTAH PH,   TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERMOHONAN P,   "+
				" TBLPFDFAIL F, TBLPPKRUJUNIT RK  " +

				/*
			    " ,(SELECT TPM.ID_PERBICARAAN, TPM.ID_PEGAWAIBARU, UPK.NAMA_PEGAWAI AS NAMA_PEGAWAI_BARU  "+
						" FROM TBLPPKTUKARPEGAWAI TPM, TBLPPKRUJUNIT UPK, "+
						" (SELECT TP.ID_PERBICARAAN, MIN(TP.TARIKH_KEPUTUSAN) AS MIN_TARIKH_KEPUTUSAN "+
						" FROM TBLPPKTUKARPEGAWAI TP WHERE TP.STATUS_TUKARPEGAWAI = 2 "+
						" GROUP BY TP.ID_PERBICARAAN) CHK "+
						" WHERE TPM.ID_PERBICARAAN = CHK.ID_PERBICARAAN "+
						" AND TPM.TARIKH_KEPUTUSAN = CHK.MIN_TARIKH_KEPUTUSAN "+
						" AND TPM.ID_PEGAWAIBARU = UPK.ID_UNITPSK "+
						" AND TPM.STATUS_TUKARPEGAWAI = 2)  CHECK_TP "+
				*/

				" WHERE PR.ID_PERBICARAAN = PH.ID_PERBICARAAN(+)  " +
				//" AND PR.ID_PERBICARAAN = CHECK_TP.ID_PERBICARAAN(+)   " +
				" "+

				//" AND PR.ID_UNITPSK = RK.ID_UNITPSK " +


				" AND ( " +
				//" ( CASE WHEN  CHECK_TP.ID_PEGAWAIBARU IS NOT NULL THEN CHECK_TP.ID_PEGAWAIBARU" +
				//" WHEN  CHECK_TP.ID_PEGAWAIBARU IS NULL THEN  PR.ID_UNITPSK END) = RK.ID_UNITPSK "+
				" (CASE WHEN PR.ID_UNITPSK_NEW IS NOT NULL THEN PR.ID_UNITPSK_NEW ELSE PR.ID_UNITPSK END)  = RK.ID_UNITPSK "+
				") "+



				" AND  RK.STATUS_PEG=1  AND KP.ID_KEPUTUSANPERMOHONAN = PR.ID_KEPUTUSANPERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
				" AND P.ID_FAIL = F.ID_FAIL  ";

				if(!id_negeri.equals(""))
				{
					sql += " AND RK.ID_NEGERI = '"+id_negeri+"' ";
				}
				if(!namaPegawai.equals(""))
				{
					sql += " AND  UPPER(RK.NAMA_PEGAWAI) LIKE '%"+namaPegawai.toUpperCase()+"%' ";
				}

				sql += " AND RK.STATUS_PEG='1'  "+
				" AND PR.TARIKH_BICARA   "+
				" BETWEEN TO_DATE('"+tarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+tarikhAkhir+"','DD/MM/YYYY')  ORDER BY PR.TARIKH_BICARA, PR.MASA_BICARA) PERB  "+
				" WHERE  UPSK.ID_UNITPSK = PERB.ID_UNITPSK    "+
				" AND UPSK.STATUS_PEG=1    ";

				if(!id_negeri.equals(""))
				{
					sql += " AND UPSK.ID_NEGERI = '"+id_negeri+"' ";
				}

				sql += " GROUP BY  UPSK.ID_UNITPSK, UPSK.NAMA_PEGAWAI,UPSK.ID_NEGERI,PERB.TARIKH_BICARA) MAIN1,  "+
				" (SELECT (TO_CHAR(FD.DR,'DD/MM/YYYY') || '_' || N.ID_UNITPSK) AS DRPG, FD.*,N.*   FROM (SELECT TO_DATE('"+tarikhMula+"', 'DD/MM/YYYY') + ROWNUM -1 AS DR  " +
				" FROM DUAL  CONNECT BY LEVEL <= TO_DATE('"+tarikhAkhir+"', 'DD/MM/YYYY') - TO_DATE('"+tarikhMula+"', 'DD/MM/YYYY') + 1) FD,  "+
				" TBLPPKRUJUNIT N   "+
				" WHERE N.STATUS_PEG='1'  ";
				if(!id_negeri.equals(""))
				{
					sql += " AND N.ID_NEGERI = '"+id_negeri+"' ";
				}
				if(!namaPegawai.equals(""))
				{
					sql += " AND  UPPER(N.NAMA_PEGAWAI) LIKE '%"+namaPegawai.toUpperCase()+"%' ";
				}
				sql += " ) DATE_REF_TBL  "+
				" WHERE  DATE_REF_TBL.DRPG  = MAIN1.DRPG (+)  "+
				" ORDER BY DATE_REF_TBL.ID_NEGERI ASC,MAIN1.TARIKH_BICARA ASC,MAIN1.JUMLAH_PERBICARAAN DESC  "+
				" )    ";


				/*
				" SELECT * FROM ( "+
				" SELECT 3 AS LAYER1, 2 AS LAYER2, DATE_REF.DR AS TARIKH_BICARA_DISPLAY, MAIN1.* FROM ( "+
				" SELECT DISTINCT " +
				" UPSK.ID_UNITPSK," +
				" UPPER(UPSK.NAMA_PEGAWAI) AS NAMA_PEGAWAI,NULL AS NAMA_NEGERI,UPSK.ID_NEGERI,PERB.TARIKH_BICARA, "+
				" COUNT(DISTINCT PERB.ID_PERBICARAAN) AS JUMLAH_PERBICARAAN, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('08:30 AM','HH:MI AM') AND TO_DATE('08:59 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T0830AM_0859AM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('09:00 AM','HH:MI AM') AND TO_DATE('09:29 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T0900AM_0929AM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('09:30 AM','HH:MI AM') AND TO_DATE('09:59 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T0930AM_0959AM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('10:00 AM','HH:MI AM') AND TO_DATE('10:29 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T1000AM_1029AM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('10:30 AM','HH:MI AM') AND TO_DATE('10:59 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T1030AM_1059AM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('11:00 AM','HH:MI AM') AND TO_DATE('11:29 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T1100AM_1129AM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('11:30 AM','HH:MI AM') AND TO_DATE('11:59 AM','HH:MI AM') THEN 1 ELSE 0 END) AS T1130AM_1159AM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('12:00 PM','HH:MI AM') AND TO_DATE('12:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T1200PM_1229PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('12:30 PM','HH:MI AM') AND TO_DATE('12:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T1230PM_1259PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('01:00 PM','HH:MI AM') AND TO_DATE('01:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0100PM_0129PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('01:30 PM','HH:MI AM') AND TO_DATE('01:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0130PM_0159PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('02:00 PM','HH:MI AM') AND TO_DATE('02:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0200PM_0229PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('02:30 PM','HH:MI AM') AND TO_DATE('02:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0230PM_0259PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('03:00 PM','HH:MI AM') AND TO_DATE('03:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0300PM_0329PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('03:30 PM','HH:MI AM') AND TO_DATE('03:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0330PM_0359PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('04:00 PM','HH:MI AM') AND TO_DATE('04:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0400PM_0429PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('04:30 PM','HH:MI AM') AND TO_DATE('04:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0430PM_0459PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('05:00 PM','HH:MI AM') AND TO_DATE('05:29 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0500PM_0529PM, "+
				" SUM(CASE WHEN PERB.WAKTU_BICARA_CONVERT BETWEEN  TO_DATE('05:30 PM','HH:MI AM') AND TO_DATE('05:59 PM','HH:MI PM') THEN 1 ELSE 0 END) AS T0530PM_0559PM "+
				" FROM TBLPPKRUJUNIT UPSK,  "+
				" (SELECT PR.ID_PERBICARAAN, RK.NAMA_PEGAWAI, PR.ID_UNITPSK, P.ID_DAERAHMHN, F.ID_NEGERI, F.NO_FAIL, PR.TARIKH_BICARA, SYSDATE, "+
				" PR.MASA_BICARA,TO_DATE( "+
				" CASE WHEN PR.MASA_BICARA LIKE '%.%' THEN "+
				" (CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, 1, INSTR(PR.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' || "+
				" CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0' ELSE PR.MASA_BICARA END "+
				" WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0'  "+
				" ELSE PR.MASA_BICARA END)  "+
				" WHEN LENGTH(PR.MASA_BICARA) = 4 THEN SUBSTR(PR.MASA_BICARA, 1, 2) || '.' || SUBSTR(PR.MASA_BICARA, 3) "+
				" ELSE '' END || (CASE WHEN PR.JENIS_MASA_BICARA = '1' THEN ' AM'  "+
				" WHEN PR.JENIS_MASA_BICARA = '2' THEN ' PM'  "+
				" WHEN PR.JENIS_MASA_BICARA = '3' THEN ' PM' ELSE '' END),'HH:MI AM') AS WAKTU_BICARA_CONVERT "+
				" FROM TBLPPKPERBICARAAN PR, TBLPPKPERINTAH PH,  "+
				" TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKRUJUNIT RK "+
				" WHERE PR.ID_PERBICARAAN = PH.ID_PERBICARAAN(+) AND PR.ID_UNITPSK = RK.ID_UNITPSK AND  RK.STATUS_PEG=1 "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = PR.ID_KEPUTUSANPERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL ";
				if(!id_negeri.equals(""))
				{
					sql += " AND RK.ID_NEGERI = '"+id_negeri+"' ";
				}
				sql += " AND PR.TARIKH_BICARA BETWEEN TO_DATE('"+tarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+tarikhAkhir+"','DD/MM/YYYY') "+
				" ORDER BY PR.TARIKH_BICARA, PR.MASA_BICARA) PERB "+
				" WHERE ";
				//sql += " UPSK.NAMA_PEGAWAI = PERB.NAMA_PEGAWAI(+) ";
				sql += " UPSK.ID_UNITPSK = PERB.ID_UNITPSK(+) ";

				if(!namaPegawai.equals(""))
				{
					sql += " AND  UPPER(UPSK.NAMA_PEGAWAI) LIKE '%"+namaPegawai.toUpperCase()+"%' ";
				}

				sql += " AND UPSK.STATUS_PEG=1 ";

				if(!id_negeri.equals(""))
				{
					sql += " AND UPSK.ID_NEGERI = '"+id_negeri+"' ";
				}

				sql += " GROUP BY " +
						" UPSK.ID_UNITPSK," +
						" UPSK.NAMA_PEGAWAI,UPSK.ID_NEGERI,PERB.TARIKH_BICARA "+
				" ) MAIN1, (SELECT TO_DATE('"+tarikhMula+"', 'DD/MM/YYYY') + ROWNUM -1 AS DR "+
				" FROM DUAL  CONNECT BY LEVEL <= TO_DATE('"+tarikhAkhir+"', 'DD/MM/YYYY') - TO_DATE('"+tarikhMula+"', 'DD/MM/YYYY') + 1) DATE_REF ";
				//sql += " WHERE  (DATE_REF.DR = MAIN1.TARIKH_BICARA OR MAIN1.TARIKH_BICARA IS NULL) ";

				sql += " ORDER BY ID_NEGERI ASC,TARIKH_BICARA ASC,JUMLAH_PERBICARAAN DESC) "+
				*/




			sql += " UNION ALL ";

			 sql += " SELECT 1 AS layer1, 1 AS layer2, "+
						" date_ref.dr AS tarikh_bicara_display, " +
						" NULL AS id_unitpsk, "+
						" NULL AS NAMA_PEGAWAI, NAMA_NEGERI AS NAMA_NEGERI, "+
						" id_negeri AS id_negeri, NULL AS tarikh_bicara, "+
						" NULL AS JUMLAH_PERBICARAAN, NULL AS T0830AM_0859AM, "+
						" NULL AS t0900am_0929am, NULL AS t0930am_0959am, "+
						" NULL AS T1000AM_1029AM, NULL AS T1030AM_1059AM, "+
						" NULL AS t1100am_1129am, NULL AS t1130am_1159am, "+
						" NULL AS T1200PM_1229PM, NULL AS T1230PM_1259PM, "+
						" NULL AS t0100pm_0129pm, NULL AS t0130pm_0159pm, "+
						" NULL AS T0200PM_0229PM, NULL AS T0230PM_0259PM, "+
						" NULL AS t0300pm_0329pm, NULL AS t0330pm_0359pm, "+
						" NULL AS T0400PM_0429PM, NULL AS T0430PM_0459PM, "+
						" NULL AS t0500pm_0529pm, NULL AS t0530pm_0559pm "+
						"  FROM TBLRUJNEGERI, "+
						" (SELECT       TO_DATE ('"+tarikhMula+"', 'DD/MM/YYYY') "+
						"      + ROWNUM "+
						"      - 1 AS dr "+
						" FROM DUAL "+
						" CONNECT BY LEVEL <= "+
						" TO_DATE ('"+tarikhAkhir+"', 'DD/MM/YYYY') "+
						" - TO_DATE ('"+tarikhMula+"', 'DD/MM/YYYY') "+
						" + 1) DATE_REF "+
						" WHERE id_negeri NOT IN (0, 17, 12, 13) ";

						if(!id_negeri.equals(""))
						{
							sql += " AND ID_NEGERI = '"+id_negeri+"' ";
						}


				sql += " UNION ALL "+
				" SELECT 2 AS LAYER1, 2 AS LAYER2,  DATE_REF.DR AS TARIKH_BICARA_DISPLAY, " +
				" NULL AS ID_UNITPSK, " +
				" NULL AS NAMA_PEGAWAI, NAMA_NEGERI AS NAMA_NEGERI, ID_NEGERI AS ID_NEGERI, NULL AS TARIKH_BICARA, "+
				" NULL AS JUMLAH_PERBICARAAN,  "+
				" NULL AS T0830AM_0859AM, "+
				" NULL AS T0900AM_0929AM, "+
				" NULL AS T0930AM_0959AM, "+
				" NULL AS T1000AM_1029AM, "+
				" NULL AS T1030AM_1059AM, "+
				" NULL AS T1100AM_1129AM, "+
				" NULL AS T1130AM_1159AM, "+
				" NULL AS T1200PM_1229PM, "+
				" NULL AS T1230PM_1259PM, "+
				" NULL AS T0100PM_0129PM, "+
				" NULL AS T0130PM_0159PM, "+
				" NULL AS T0200PM_0229PM, "+
				" NULL AS T0230PM_0259PM, "+
				" NULL AS T0300PM_0329PM, "+
				" NULL AS T0330PM_0359PM, "+
				" NULL AS T0400PM_0429PM, "+
				" NULL AS T0430PM_0459PM, "+
				" NULL AS T0500PM_0529PM, "+
				" NULL AS T0530PM_0559PM FROM TBLRUJNEGERI, "+
				" (SELECT TO_DATE('"+tarikhMula+"', 'DD/MM/YYYY') + ROWNUM -1 AS DR "+
				" FROM DUAL  CONNECT BY LEVEL <= TO_DATE('"+tarikhAkhir+"', 'DD/MM/YYYY') - TO_DATE('"+tarikhMula+"', 'DD/MM/YYYY') + 1) DATE_REF "+
				" WHERE ID_NEGERI NOT IN (0,17,12,13) ";

				if(!id_negeri.equals(""))
				{
					sql += " AND ID_NEGERI = '"+id_negeri+"' ";
				}

				sql += " ) SUPERMAIN "+
				" ORDER BY SUPERMAIN.TARIKH_BICARA_DISPLAY ASC,  ID_NEGERI ASC, LAYER1 ASC, JUMLAH_PERBICARAAN DESC, NAMA_PEGAWAI ASC ";
		myLogger.info(" BICARA INTERAKTIF : SQL listStatsJumlahBicara :"+ sql);
		rs = stmt.executeQuery(sql);
		list = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("LAYER1",rs == null ? "" :rs.getString("LAYER1") == null ? "" : rs.getString("LAYER1"));
			h.put("LAYER2",rs == null ? "" :rs.getString("LAYER2") == null ? "" : rs.getString("LAYER2"));
			h.put("NAMA_HARI",rs == null ? "" :rs.getString("NAMA_HARI") == null ? "" : rs.getString("NAMA_HARI").trim());
			h.put("TARIKH_BICARA_DISPLAY",rs == null ? "" :rs.getString("TARIKH_BICARA_DISPLAY") == null ? "" : rs.getString("TARIKH_BICARA_DISPLAY"));
			h.put("ID_UNITPSK",rs == null ? "" :rs.getString("ID_UNITPSK") == null ? "" : rs.getString("ID_UNITPSK"));
			h.put("NAMA_PEGAWAI",rs == null ? "" :rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI").toUpperCase());
			h.put("NAMA_NEGERI",rs == null ? "" :rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
			h.put("ID_NEGERI",rs == null ? "" :rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
			h.put("TARIKH_BICARA",rs == null ? "" :rs.getString("TARIKH_BICARA") == null ? "" : rs.getString("TARIKH_BICARA"));
			h.put("JUMLAH_PERBICARAAN",rs == null ? "" :rs.getString("JUMLAH_PERBICARAAN") == null ? "" : rs.getString("JUMLAH_PERBICARAAN"));
			h.put("T0830AM_0859AM",rs == null ? "" :rs.getString("T0830AM_0859AM") == null ? "" : rs.getString("T0830AM_0859AM"));
			h.put("T0900AM_0929AM",rs == null ? "" :rs.getString("T0900AM_0929AM") == null ? "" : rs.getString("T0900AM_0929AM"));
			h.put("T0930AM_0959AM",rs == null ? "" :rs.getString("T0930AM_0959AM") == null ? "" : rs.getString("T0930AM_0959AM"));
			h.put("T1000AM_1029AM",rs == null ? "" :rs.getString("T1000AM_1029AM") == null ? "" : rs.getString("T1000AM_1029AM"));
			h.put("T1030AM_1059AM",rs == null ? "" :rs.getString("T1030AM_1059AM") == null ? "" : rs.getString("T1030AM_1059AM"));
			h.put("T1100AM_1129AM",rs == null ? "" :rs.getString("T1100AM_1129AM") == null ? "" : rs.getString("T1100AM_1129AM"));
			h.put("T1130AM_1159AM",rs == null ? "" :rs.getString("T1130AM_1159AM") == null ? "" : rs.getString("T1130AM_1159AM"));
			h.put("T1200PM_1229PM",rs == null ? "" :rs.getString("T1200PM_1229PM") == null ? "" : rs.getString("T1200PM_1229PM"));
			h.put("T1230PM_1259PM",rs == null ? "" :rs.getString("T1230PM_1259PM") == null ? "" : rs.getString("T1230PM_1259PM"));
			h.put("T0100PM_0129PM",rs == null ? "" :rs.getString("T0100PM_0129PM") == null ? "" : rs.getString("T0100PM_0129PM"));
			h.put("T0130PM_0159PM",rs == null ? "" :rs.getString("T0130PM_0159PM") == null ? "" : rs.getString("T0130PM_0159PM"));
			h.put("T0200PM_0229PM",rs == null ? "" :rs.getString("T0200PM_0229PM") == null ? "" : rs.getString("T0200PM_0229PM"));
			h.put("T0230PM_0259PM",rs == null ? "" :rs.getString("T0230PM_0259PM") == null ? "" : rs.getString("T0230PM_0259PM"));
			h.put("T0300PM_0329PM",rs == null ? "" :rs.getString("T0300PM_0329PM") == null ? "" : rs.getString("T0300PM_0329PM"));
			h.put("T0330PM_0359PM",rs == null ? "" :rs.getString("T0330PM_0359PM") == null ? "" : rs.getString("T0330PM_0359PM"));
			h.put("T0400PM_0429PM",rs == null ? "" :rs.getString("T0400PM_0429PM") == null ? "" : rs.getString("T0400PM_0429PM"));
			h.put("T0430PM_0459PM",rs == null ? "" :rs.getString("T0430PM_0459PM") == null ? "" : rs.getString("T0430PM_0459PM"));
			h.put("T0500PM_0529PM",rs == null ? "" :rs.getString("T0500PM_0529PM") == null ? "" : rs.getString("T0500PM_0529PM"));
			h.put("T0530PM_0559PM",rs == null ? "" :rs.getString("T0530PM_0559PM") == null ? "" : rs.getString("T0530PM_0559PM"));
			list.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return list;
	}

	public static String removeLastKoma(String value) {
		value = value.trim();
		myLogger.info("removeLastKoma["+value+"]"+"CHAR LAST : "+value.charAt(value.length() - 1));
		String lastChar = value.charAt(value.length() - 1)+"";
		if(lastChar.equals(","))
		{
			value = value.substring(0,value.length()-1);
			myLogger.info("LEPAS BUANG KOMA : "+value);
		}
		return value;
	}

	public static String dotdotIfBlank(String value) {
		String dotdot = ".............";
		if(!value.equals("") && value != null)
		{
			dotdot = value;
		}
		return dotdot;
	}

	public static String upperCaseAllFirst(String value) {
		value = value.toLowerCase();
        char[] array = value.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }
		return new String(array);
    }

}
