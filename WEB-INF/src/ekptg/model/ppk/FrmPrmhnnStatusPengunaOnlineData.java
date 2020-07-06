package ekptg.model.ppk;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmPrmhnnStatusPengunaOnlineData {
	static Logger myLogger = Logger.getLogger(FrmPrmhnnStatusPengunaOnlineData.class);	
	public static Vector getSenaraiTugasan(String search,String idMasuk,String role,String kppemohon,String kpsimati, String USER_LOGIN_SYSTEM, String flag_draff)throws Exception {
		
		
	    Db db = null;
	    String sql = "";
	    Format formatter = new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      /*
	      SQLRenderer r = new SQLRenderer();
	      r.add("distinct(p.id_Fail)");
	      r.add("p.no_permohonan_online");
	      // r.add("to_char(p.tarikh_masuk,'dd/mm/yyyy - hh:mi:ss am') as tarikh_masuk");	     
	      r.add("p.tarikh_masuk");
	      //to_date('29-Oct-09', 'DD-Mon-YY HH:MI:SS') 
	      //'yyyy/mm/dd:hh:mi:ssam'
	      r.add("s.keterangan");
	      r.add("pm.no_kp_baru");
	      r.add("pm.no_kp_lama");
	      r.add("pm.no_kp_lain");
	      r.add("pm.nama_simati");
	      r.add("m.no_kp_baru");
	      r.add("m.no_kp_lama");
	      r.add("m.no_kp_lain");
	      r.add("m.nama_pemohon");
	      r.add("f.no_fail");
	      r.add("p.seksyen");
	      r.add("sm.id_permohonansimati");
	      r.add("p.id_Permohonan");
	      r.add("pm.id_simati");
	      r.add("p.seksyen");
	      r.add("m.id_pemohon");
	      r.add("no_subjaket");
	      r.add("to_char(p.tarikh_masuk, 'DD-Mon-YY HH:MI:SS AM')  as tarikh_masuk_d");	      
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("p.id_pemohon",r.unquote("m.id_pemohon"));
	      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
	      r.add("f.id_Fail",r.unquote("sf.id_fail"));
	      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
	      r.add("us.id_Status",r.unquote("s.id_Status"));
	      r.add("p.id_permohonan",r.unquote("sm.id_permohonan"));
	      r.add("sm.id_simati",r.unquote("pm.id_simati"));
	      r.add("sf.aktif","1");	      
	      r.add("p.id_Masuk",idMasuk);
	      //r.add("p.no_permohonan_online","%"+search+"%","like");	      
	      if(role.substring((role.length()-3), (role.length())).equalsIgnoreCase("PPK")){
	    	  sql = r.getSQLSelect(" Tblppkpermohonan p,Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s, Tblppkpemohon m,Tblppkpermohonansimati sm, Tblppksimati pm");
		  }else{
	    	  sql = r.getSQLSelect(" Tblppkpermohonan p,Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s, Tblppkpemohon m,Tblppkpermohonansimati sm, Tblppksimati pm");
		  }
	      */
	      
	      //kppemohon
	      //kpsimati
	      
	      //aishahidris comment ni sbb nak keluarkan semua permohonan termasuk yg sudah tak aktif 3rdOct2017
	      /*sql = "SELECT DISTINCT(P.ID_FAIL), P.NO_PERMOHONAN_ONLINE, P.TARIKH_MASUK, S.KETERANGAN, " +
	      		"PM.NO_KP_BARU, PM.NO_KP_LAMA, PM.NO_KP_LAIN, PM.NAMA_SIMATI, M.NO_KP_BARU, M.NO_KP_LAMA, " +
	      		"M.NO_KP_LAIN, M.NAMA_PEMOHON, F.NO_FAIL, P.SEKSYEN, SM.ID_PERMOHONANSIMATI, P.ID_PERMOHONAN, " +
	      		"PM.ID_SIMATI, P.SEKSYEN, M.ID_PEMOHON, NO_SUBJAKET, " +
	      		"TO_CHAR(P.TARIKH_MASUK, 'DD/MM/YYYY')  AS TARIKH_MASUK_D, TO_CHAR(P.TARIKH_MOHON_ONLINE, 'DD/MM/YYYY')  AS TARIKH_MOHON_ONLINE,PM.NAMA_SIMATI,M.NAMA_PEMOHON   " +
	      		"FROM  TBLPPKPERMOHONAN P,TBLPFDFAIL F,"+
	      		"TBLRUJSUBURUSANSTATUSFAIL SF,TBLRUJSUBURUSANSTATUS US," +
	      		"TBLRUJSTATUS S, TBLPPKPEMOHON M,TBLPPKPERMOHONANSIMATI SM, TBLPPKSIMATI PM " +
	      		"WHERE P.ID_FAIL = F.ID_FAIL  " +
	      		"AND P.ID_PEMOHON = M.ID_PEMOHON  " +
	      		"AND P.ID_PERMOHONAN = SF.ID_PERMOHONAN  " +
	      		"AND F.ID_FAIL = SF.ID_FAIL  " +
	      		"AND SF.ID_SUBURUSANSTATUS = US.ID_SUBURUSANSTATUS  " +
	      		"AND P.ID_STATUS = S.ID_STATUS  " +
	      		"AND P.ID_STATUS NOT IN ('150','160')  "+
	      		"AND P.ID_PERMOHONAN = SM.ID_PERMOHONAN  " +
	      		"AND SM.ID_SIMATI = PM.ID_SIMATI  " +
	      		"AND SF.AKTIF = '1'  " +
	      		"AND P.ID_MASUK = '"+idMasuk+"'  ";
	      		if(!kpsimati.equals(""))
	      		{// CHECK KP SIMATI
	      		sql = sql + " AND (PM.NO_KP_BARU = '"+kpsimati+"' OR PM.NO_KP_LAMA = '"+kpsimati+"' OR PM.NO_KP_LAIN = '"+kpsimati+"')";
           		}
	      		
	      		if(!kppemohon.equals(""))
	      		{// CHECK KP PEMOHON
	      		sql = sql + " AND (M.NO_KP_BARU = '"+kppemohon+"' OR M.NO_KP_LAMA = '"+kppemohon+"' OR M.NO_KP_LAIN = '"+kppemohon+"')";
           		}
	      		
	      		sql = sql + " ORDER BY P.TARIKH_MASUK DESC ";*/
		      		
		    /*sql = " SELECT DISTINCT TO_CHAR (TBLPPKPERMOHONAN.TARIKH_MASUK, 'DD/MM/YYY') AS TARIKH_MASUK, "+
		    	" TO_CHAR (TBLPPKPERMOHONAN.TARIKH_MOHON_ONLINE, 'DD/MM/YYYY') AS TARIKH_MOHON_ONLINE, "+
		    	" TBLPPKPERMOHONAN.NO_PERMOHONAN_ONLINE, TBLPPKPERMOHONAN.SEKSYEN, TBLPPKPERMOHONAN.ID_STATUS, TBLPFDFAIL.NO_FAIL, TBLPPKPERMOHONANSIMATI.ID_PERMOHONANSIMATI, "+
		    	" TBLPPKPERMOHONAN.ID_PERMOHONAN, "+
		    	" UPPER (TBLPPKPEMOHON.NAMA_PEMOHON) AS NAMA_PEMOHON, "+
		    	" UPPER(CASE WHEN TBLPPKPEMOHON.NO_KP_BARU IS NOT NULL THEN TBLPPKPEMOHON.NO_KP_BARU ELSE "+
		    	" CASE WHEN TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN TBLPPKPEMOHON.NO_KP_LAMA ELSE  "+
		    	" CASE WHEN TBLPPKPEMOHON.NO_KP_LAIN IS NOT NULL THEN  "+
		    	" (CASE WHEN TBLPPKPEMOHON.JENIS_KP = '5' THEN 'Tentera : ' "+
		    	" WHEN TBLPPKPEMOHON.JENIS_KP = '6' THEN 'Polis : ' "+
		    	" WHEN TBLPPKPEMOHON.JENIS_KP = '4' THEN 'Passport : ' "+
		    	" WHEN TBLPPKPEMOHON.JENIS_KP = '7' THEN 'Lain-lain : ' "+
		    	" ELSE '' END) ||  TBLPPKPEMOHON.NO_KP_LAIN ELSE "+
		    	" '' "+
		    	" END "+
		    	" END "+
		    	" END) AS MYID_PEMOHON, TBLPPKPEMOHON.ID_PEMOHON, "+
		    	" TBLPPKSIMATI.NAMA_SIMATI,UPPER(CASE WHEN TBLPPKSIMATI.NO_KP_BARU IS NOT NULL THEN TBLPPKSIMATI.NO_KP_BARU ELSE "+
		    	" CASE WHEN TBLPPKSIMATI.NO_KP_LAMA IS NOT NULL THEN TBLPPKSIMATI.NO_KP_LAMA ELSE "+
		    	" CASE WHEN TBLPPKSIMATI.NO_KP_LAIN IS NOT NULL THEN "+
		    	" (CASE WHEN TBLPPKSIMATI.JENIS_KP = '5' THEN 'Tentera : ' "+
		    	" WHEN TBLPPKSIMATI.JENIS_KP = '6' THEN 'Polis : ' "+
		    	" WHEN TBLPPKSIMATI.JENIS_KP = '4' THEN 'Passport : ' "+
		    	" WHEN TBLPPKSIMATI.JENIS_KP = '7' THEN 'Lain-lain : ' "+
		    	" ELSE '' END) ||  TBLPPKSIMATI.NO_KP_LAIN ELSE "+
		    	" '' "+
		    	" END "+
		    	" END "+
		    	" END) AS MYID_SIMATI, TBLPPKSIMATI.ID_SIMATI, "+
		    	" CASE WHEN TBLPPKPERMOHONAN.ID_STATUS = '171' "+
		    	" THEN    UPPER (TBLRUJSTATUS.KETERANGAN) "+
		    	" || ' DI ' "+
		    	" || UPPER (TBLRUJDAERAH.NAMA_DAERAH) "+
		    	" WHEN TBLPPKPERMOHONAN.ID_STATUS NOT IN (171) "+
		    	" THEN UPPER (TBLRUJSTATUS.KETERANGAN) "+
		    	" END AS STATUS, NO_SUBJAKET "+
		    	" FROM TBLPPKPERMOHONAN, TBLPPKPERMOHONANSIMATI, TBLPFDFAIL, TBLPPKOB, TBLPPKOBPERMOHONAN, TBLPPKPEMOHON, TBLPPKSIMATI, TBLRUJSTATUS, TBLRUJDAERAH "+
		    	" WHERE TBLPPKPERMOHONAN.ID_PERMOHONAN = TBLPPKPERMOHONANSIMATI.ID_PERMOHONAN "+
		    	" AND TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL "+
		    	" AND TBLPPKOB.ID_OB = TBLPPKOBPERMOHONAN.ID_OB "+
		    	" AND TBLPPKOBPERMOHONAN.ID_PERMOHONANSIMATI = TBLPPKPERMOHONANSIMATI.ID_PERMOHONANSIMATI "+
		    	" AND TBLPPKPEMOHON.ID_PEMOHON = TBLPPKPERMOHONAN.ID_PEMOHON "+
		    	" AND TBLPPKSIMATI.ID_SIMATI = TBLPPKPERMOHONANSIMATI.ID_SIMATI "+
		    	" AND TBLRUJSTATUS.ID_STATUS(+) = TBLPPKPERMOHONAN.ID_STATUS "+
		    	" AND TBLRUJDAERAH.ID_DAERAH(+) = TBLPPKPERMOHONAN.ID_DAERAHMHN "+
		    	" AND TBLPPKPERMOHONAN.NO_PERMOHONAN_ONLINE IS NOT NULL "+
		    	" AND TBLPPKPERMOHONAN.ID_STATUS NOT IN ('150', '160') "+
		    	" AND (TBLPPKPERMOHONAN.ID_MASUK = '"+idMasuk+"' OR TBLPPKOB.NO_KP_BARU = '"+USER_LOGIN_SYSTEM+"') ";*/
	      
	      /*sql = " SELECT TO_CHAR (P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, TO_CHAR (P.TARIKH_MOHON_ONLINE,'DD/MM/YYYY') AS TARIKH_MOHON_ONLINE," +
	      		" TO_CHAR (P.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK, F.ID_FAIL, "+
	    		  " TO_CHAR (F.NO_FAIL) AS NO_FAIL, P.NO_PERMOHONAN_ONLINE, SM.NAMA_SIMATI, "+
	    		  " UPPER (CASE WHEN SM.NO_KP_BARU IS NOT NULL THEN SM.NO_KP_BARU "+
	    		  " ELSE CASE WHEN SM.NO_KP_LAMA IS NOT NULL THEN SM.NO_KP_LAMA "+
	    		  " ELSE CASE WHEN SM.NO_KP_LAIN IS NOT NULL THEN "+
	    		  " (CASE WHEN SM.JENIS_KP = '5' THEN 'TENTERA : ' "+
	    		  " WHEN SM.JENIS_KP = '6' THEN 'POLIS : ' "+
	    		  " WHEN SM.JENIS_KP = '4' THEN 'PASSPORT : ' "+
	    		  " WHEN SM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' "+
	    		  " ELSE '' END ) || SM.NO_KP_LAIN "+
	    		  " ELSE '' END END END ) AS MYID_SIMATI, "+
	    		  " UPPER (PM.NAMA_PEMOHON) AS NAMA_PEMOHON, "+
	    		  " UPPER (CASE WHEN PM.NO_KP_BARU IS NOT NULL THEN PM.NO_KP_BARU "+
	    		  " ELSE CASE WHEN PM.NO_KP_LAMA IS NOT NULL THEN PM.NO_KP_LAMA "+
	    		  " ELSE CASE WHEN PM.NO_KP_LAIN IS NOT NULL THEN "+
	    		  " (CASE WHEN PM.JENIS_KP = '5' THEN 'TENTERA : ' "+
	    		  " WHEN PM.JENIS_KP = '6' THEN 'POLIS : ' "+
	    		  " WHEN PM.JENIS_KP = '4' THEN 'PASSPORT : ' "+
	    		  " WHEN PM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' "+
	    		  " ELSE '' END ) || PM.NO_KP_LAIN ELSE '' "+
	    		  " END END END ) AS MYID_PEMOHON, OBP.ID_PEMOHON, P.SEKSYEN, "+
	    		  " CASE WHEN P.ID_STATUS = '171' THEN UPPER (S.KETERANGAN) || ' DI ' || UPPER (D.NAMA_DAERAH) "+
	    		  " WHEN P.ID_STATUS NOT IN (171) THEN UPPER (S.KETERANGAN) END AS STATUS, P.ID_PERMOHONAN "+
	    		  " FROM TBLPPKPERMOHONAN P, TBLPPKOBPERMOHONAN OBP, TBLPPKPERMOHONANSIMATI PSM, "+
	    		  " TBLPFDFAIL F, USERS_ONLINE UO, TBLPPKSIMATI SM, TBLPPKPEMOHON PM, TBLRUJSTATUS S, TBLRUJDAERAH D "+
	    		  " WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI "+
	    		  " AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_FAIL = F.ID_FAIL AND F.ID_SEKSYEN = 2 "+
	    		  " AND OBP.NO_KP_BARU = UO.NO_KP_BARU AND PSM.ID_SIMATI = SM.ID_SIMATI "+
	    		  " AND S.ID_STATUS = P.ID_STATUS AND D.ID_DAERAH = P.ID_DAERAHMHN "+
	    		  " AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0') AND UO.USER_ID = '"+idMasuk+"' ";*/
	      
	      /*sql = " SELECT DISTINCT  F.ID_MASUK,TO_CHAR (P.TARIKH_MOHON, 'DD/MM/YYYY') AS TARIKH_MOHON, NO_SUBJAKET, " +
	      		" TO_CHAR (P.TARIKH_MOHON_ONLINE, 'DD/MM/YYYY') AS TARIKH_MOHON_ONLINE, " +
	      		" TO_CHAR (P.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK, F.ID_FAIL, " +
	      		" TO_CHAR (F.NO_FAIL) AS NO_FAIL, P.NO_PERMOHONAN_ONLINE, SM.NAMA_SIMATI, " +
	      		" UPPER (CASE WHEN SM.NO_KP_BARU IS NOT NULL THEN SM.NO_KP_BARU " +
	      		" ELSE CASE WHEN SM.NO_KP_LAMA IS NOT NULL THEN SM.NO_KP_LAMA ELSE CASE WHEN SM.NO_KP_LAIN IS NOT NULL THEN (CASE " +
	      		" WHEN SM.JENIS_KP = '5' THEN 'TENTERA : ' WHEN SM.JENIS_KP = '6' THEN 'POLIS : ' " +
	      		" WHEN SM.JENIS_KP = '4' THEN 'PASSPORT : ' WHEN SM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' " +
	      		" ELSE '' END) || SM.NO_KP_LAIN ELSE '' END END END ) AS MYID_SIMATI, UPPER (PM.NAMA_PEMOHON) AS NAMA_PEMOHON, " +
	      		" UPPER (CASE WHEN PM.NO_KP_BARU IS NOT NULL THEN PM.NO_KP_BARU ELSE CASE WHEN PM.NO_KP_LAMA IS NOT NULL THEN PM.NO_KP_LAMA " +
	      		" ELSE CASE WHEN PM.NO_KP_LAIN IS NOT NULL THEN (CASE WHEN PM.JENIS_KP = '5' THEN 'TENTERA : ' " +
	      		" WHEN PM.JENIS_KP = '6' THEN 'POLIS : ' WHEN PM.JENIS_KP = '4' THEN 'PASSPORT : ' WHEN PM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' " +
	      		" ELSE '' END) || PM.NO_KP_LAIN ELSE '' END END END ) AS MYID_PEMOHON, PM.ID_PEMOHON, P.SEKSYEN, " +
	      		" CASE WHEN P.ID_STATUS = '171' THEN    UPPER (S.KETERANGAN) || ' DI ' || UPPER (D.NAMA_DAERAH) " +
	      		" WHEN P.ID_STATUS = '8' THEN UPPER (S.KETERANGAN) WHEN P.ID_STATUS = '18' THEN UPPER (S.KETERANGAN) "+
	      		" WHEN P.ID_STATUS = '47' THEN UPPER (S.KETERANGAN) ELSE 'DALAM PROSES' END AS STATUS, P.ID_PERMOHONAN " +
	      		" FROM TBLPPKPERMOHONAN P, TBLPPKOBPERMOHONAN OBP, TBLPPKPERMOHONANSIMATI PSM, TBLPFDFAIL F, " +
	      		" USERS_ONLINE UO, TBLPPKSIMATI SM, TBLPPKPEMOHON PM, TBLRUJSTATUS S, TBLRUJDAERAH D " +
	      		" WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI " +
	      		" AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_FAIL = F.ID_FAIL AND F.ID_SEKSYEN = 2 " +
	      		" AND ((OBP.NO_KP_BARU = UO.NO_KP_BARU AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0')) OR F.ID_MASUK = '"+idMasuk+"') AND PSM.ID_SIMATI = SM.ID_SIMATI " +
	      		" AND S.ID_STATUS = P.ID_STATUS AND D.ID_DAERAH = P.ID_DAERAHMHN AND P.NO_PERMOHONAN_ONLINE IS NOT NULL AND P.ID_STATUS NOT IN ('150','160') " +
	      		//" AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0') " +
	      		" AND UO.USER_ID = '"+idMasuk+"' ";*/
	      
	      /*sql = " SELECT DISTINCT F.ID_MASUK, NO_SUBJAKET, TO_CHAR (P.TARIKH_MOHON, 'DD/MM/YYYY') AS TARIKH_MOHON, " +
	      		" TO_CHAR (P.TARIKH_MOHON_ONLINE,'DD/MM/YYYY') AS TARIKH_MOHON_ONLINE, P.ID_STATUS, " +
	      		" CASE WHEN P.NO_PERMOHONAN_ONLINE IS NOT NULL THEN TO_CHAR (P.TARIKH_MOHON_ONLINE, 'DD/MM/YYYY') " +
	      		" ELSE TO_CHAR (P.TARIKH_MOHON, 'DD/MM/YYYY') END AS TARIKH, " +
	      		" CASE WHEN P.ID_STATUS = '171' THEN UPPER (S.KETERANGAN) || ' DI ' || UPPER (D.NAMA_DAERAH) " +
	      		" WHEN P.ID_STATUS IN ('21', '8', '18') THEN UPPER (S.KETERANGAN) " +
	      		" WHEN P.ID_STATUS IN ('37', '47', '70', '152', '169') THEN 'BATAL' " +
	      		" ELSE 'DALAM PROSES' END AS STATUS, TO_CHAR (B.TARIKH_BICARA,'DD/MM/YYYY') AS TARIKH_BICARA, " +
	      		" CASE WHEN B.MASA_BICARA LIKE '%.%' THEN " +
	      		" (CASE WHEN NVL(length(SUBSTR(B.MASA_BICARA, 1, INSTR(B.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' || " +
	      		" CASE WHEN NVL(length(SUBSTR(B.MASA_BICARA, INSTR(B.MASA_BICARA, '.') + 1)),0) = 1 THEN B.MASA_BICARA || '0' ELSE B.MASA_BICARA END " +
	      		" WHEN NVL(length(SUBSTR(B.MASA_BICARA, INSTR(B.MASA_BICARA, '.') + 1)),0) = 1 THEN B.MASA_BICARA || '0' " +
	      		" ELSE B.MASA_BICARA END) " +
	      		" WHEN LENGTH(B.MASA_BICARA) = 4 THEN SUBSTR(B.MASA_BICARA, 1, 2) || '.' || SUBSTR(B.MASA_BICARA, 3) " +
	      		" ELSE '' END || (CASE WHEN B.JENIS_MASA_BICARA = '1' THEN ' PAGI' " +
	      		" WHEN B.JENIS_MASA_BICARA = '2' THEN ' TENGAH HARI' " +
	      		" WHEN B.JENIS_MASA_BICARA = '3' THEN ' PETANG' ELSE '' END) AS MASA_BICARA, B.TEMPAT_BICARA, " +
	      		" UPPER(B.ALAMAT_BICARA1) AS ALAMAT_BICARA1, UPPER(B.ALAMAT_BICARA2) AS ALAMAT_BICARA2, UPPER(B.ALAMAT_BICARA3) AS ALAMAT_BICARA3, " +
	      		" B.POSKOD, UPPER(B.BANDAR) AS BANDAR, B.ID_NEGERIBICARA, UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI, TO_CHAR (P.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK, " +
	      		" F.ID_FAIL, TO_CHAR (F.NO_FAIL) AS NO_FAIL, P.NO_PERMOHONAN_ONLINE, SM.NAMA_SIMATI, " +
	      		" UPPER (CASE WHEN SM.NO_KP_BARU IS NOT NULL THEN SM.NO_KP_BARU " +
	      		" ELSE CASE WHEN SM.NO_KP_LAMA IS NOT NULL THEN SM.NO_KP_LAMA " +
	      		" ELSE CASE WHEN SM.NO_KP_LAIN IS NOT NULL THEN    (CASE " +
	      		" WHEN SM.JENIS_KP = '5' THEN 'TENTERA : ' WHEN SM.JENIS_KP = '6' THEN 'POLIS : ' " +
	      		" WHEN SM.JENIS_KP = '4' THEN 'PASSPORT : ' WHEN SM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' " +
	      		" ELSE '' END) || SM.NO_KP_LAIN ELSE '' END END END) AS MYID_SIMATI, " +
	      		" UPPER (PM.NAMA_PEMOHON) AS NAMA_PEMOHON, UPPER " +
	      		" (CASE WHEN PM.NO_KP_BARU IS NOT NULL THEN PM.NO_KP_BARU " +
	      		" ELSE CASE WHEN PM.NO_KP_LAMA IS NOT NULL THEN PM.NO_KP_LAMA " +
	      		" ELSE CASE WHEN PM.NO_KP_LAIN IS NOT NULL THEN    (CASE " +
	      		" WHEN PM.JENIS_KP = '5' THEN 'TENTERA : ' WHEN PM.JENIS_KP = '6' THEN 'POLIS : ' " +
	      		" WHEN PM.JENIS_KP = '4' THEN 'PASSPORT : ' WHEN PM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' " +
	      		" ELSE '' END) || PM.NO_KP_LAIN ELSE '' END END END) AS MYID_PEMOHON, PM.ID_PEMOHON, P.SEKSYEN, P.ID_PERMOHONAN " +
	      		" FROM TBLPPKPERMOHONAN P, TBLPPKOBPERMOHONAN OBP, TBLPPKPERMOHONANSIMATI PSM, TBLPFDFAIL F, USERS_ONLINE UO, " +
	      		" TBLPPKSIMATI SM, TBLPPKPEMOHON PM, TBLRUJSTATUS S, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN B " +
	      		" WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI " +
	      		" AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_FAIL = F.ID_FAIL AND F.ID_SEKSYEN = 2 " +
	      		" AND ((OBP.NO_KP_BARU = UO.NO_KP_BARU AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0')) " +
	      		" OR F.ID_MASUK = '"+idMasuk+"') " +
	      		" AND PSM.ID_SIMATI = SM.ID_SIMATI AND S.ID_STATUS = P.ID_STATUS " +
	      		" AND D.ID_DAERAH = P.ID_DAERAHMHN AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN " +
	      		" AND B.ID_KEPUTUSANPERMOHONAN(+) = KP.ID_KEPUTUSANPERMOHONAN " +
	      		" AND N.ID_NEGERI(+) = B.ID_NEGERIBICARA " +
	      		//" AND P.NO_PERMOHONAN_ONLINE IS NOT NULL " +
	      		" AND P.ID_STATUS NOT IN ('150', '160') AND UO.USER_ID = '"+idMasuk+"' ";*/
	      	      
	      /*sql = " SELECT DISTINCT F.ID_MASUK, NO_SUBJAKET, TO_CHAR (P.TARIKH_MOHON, 'DD/MM/YYYY') AS TARIKH_MOHON, " +
	      		" TO_CHAR (P.TARIKH_MOHON_ONLINE, 'DD/MM/YYYY') AS TARIKH_MOHON_ONLINE, P.ID_STATUS, " +
	      		" CASE WHEN PERINTAH.FLAG_TANGGUH = '1' THEN 'Pemohon / Waris Tidak Hadir' " +
	      		" WHEN PERINTAH.FLAG_TANGGUH = '2' THEN 'Menunggu Keputusan Rujukan Mahkamah Syariah' " +
	      		" WHEN PERINTAH.FLAG_TANGGUH = '3' THEN 'Menunggu Keputusan Rujukan Kepada Ruler of The State atau Mahkamah Tinggi (BORANG J)' " +
	      		" WHEN PERINTAH.FLAG_TANGGUH = '4' THEN 'Menunggu Sijil Faraid' WHEN PERINTAH.FLAG_TANGGUH = '5' THEN 'Senarai Waris Tidak Lengkap' " +
	      		" WHEN PERINTAH.FLAG_TANGGUH = '6' THEN 'Bukti Kematian Tidak Lengkap' WHEN PERINTAH.FLAG_TANGGUH = '7' THEN 'Pertelingkahan Kolateral' " +
	      		" WHEN PERINTAH.FLAG_TANGGUH = '8' THEN 'Menunggu Surat Akuan Persetujuan' WHEN PERINTAH.FLAG_TANGGUH = '9' THEN 'Sebab-sebab Lain' ELSE 'TIADA' " +
	      		" END AS FLAG_TANGGUH, PERINTAH.SEBAB_TANGGUH, PERINTAH.TEMPOH_TUNGGU_FARAID, PERINTAH.CHECK_KIV, " +
	      		" PERINTAH.DATE_KIV, PERINTAH.CATATAN_KIV, " +
	      		" CASE WHEN P.NO_PERMOHONAN_ONLINE IS NOT NULL THEN TO_CHAR (P.TARIKH_MOHON_ONLINE,'DD/MM/YYYY') " +
	      		" ELSE TO_CHAR (P.TARIKH_MOHON, 'DD/MM/YYYY') END AS TARIKH, " +
	      		" CASE WHEN P.ID_STATUS = '171' THEN    UPPER (S.KETERANGAN) || ' DI ' || UPPER (D.NAMA_DAERAH) " +
	      		" WHEN P.ID_STATUS IN ('21', '8', '18','44') THEN UPPER (S.KETERANGAN) " +
	      		" WHEN P.ID_STATUS IN ('37', '47', '70', '152', '169') THEN 'BATAL' ELSE 'DALAM PROSES' " +
	  	      " END AS STATUS, KP.CATATAN, TO_CHAR (B.TARIKH_BICARA, 'DD/MM/YYYY') AS TARIKH_BICARA, " +
	  	      " CASE WHEN B.MASA_BICARA LIKE '%.%' THEN (CASE WHEN NVL (LENGTH (SUBSTR (B.MASA_BICARA, 1, " +
	  	      " INSTR (B.MASA_BICARA, '.') - 1)),0) = 1 THEN    '0' || CASE WHEN NVL (LENGTH (SUBSTR " +
	  	      " (B.MASA_BICARA, INSTR (B.MASA_BICARA, '.') + 1)),0) = 1 THEN B.MASA_BICARA || '0' " +
	  	      " ELSE B.MASA_BICARA END WHEN NVL (LENGTH (SUBSTR (B.MASA_BICARA,INSTR (B.MASA_BICARA,'.') + 1)), 0) = 1 " +
	  	      " THEN B.MASA_BICARA || '0' ELSE B.MASA_BICARA END) WHEN LENGTH (B.MASA_BICARA) = 4 " +
	  	      " THEN    SUBSTR (B.MASA_BICARA, 1, 2) || '.' || SUBSTR (B.MASA_BICARA, 3) ELSE '' END || (CASE " +
	  	      " WHEN B.JENIS_MASA_BICARA = '1' THEN ' PAGI' WHEN B.JENIS_MASA_BICARA = '2' THEN ' TENGAH HARI' " +
	  	      " WHEN B.JENIS_MASA_BICARA = '3' THEN ' PETANG' ELSE '' END) AS MASA_BICARA, " +
	  	      " B.TEMPAT_BICARA, UPPER (B.ALAMAT_BICARA1) AS ALAMAT_BICARA1, UPPER (B.ALAMAT_BICARA2) AS ALAMAT_BICARA2, " +
	  	      " UPPER (B.ALAMAT_BICARA3) AS ALAMAT_BICARA3, B.POSKOD, UPPER (B.BANDAR) AS BANDAR, B.ID_NEGERIBICARA, " +
	  	      " UPPER (N.NAMA_NEGERI) AS NAMA_NEGERI, TO_CHAR (P.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK, " +
	  	      " TO_CHAR (B.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK_BICARA, F.ID_FAIL, TO_CHAR (F.NO_FAIL) AS NO_FAIL, " +
	  	      " P.NO_PERMOHONAN_ONLINE, SM.NAMA_SIMATI, " +
	  	      " UPPER (CASE WHEN SM.NO_KP_BARU IS NOT NULL THEN SM.NO_KP_BARU ELSE CASE WHEN SM.NO_KP_LAMA IS NOT NULL " +
	  	      " THEN SM.NO_KP_LAMA ELSE CASE WHEN SM.NO_KP_LAIN IS NOT NULL THEN     " +
	  	      " (CASE WHEN SM.JENIS_KP = '5' THEN 'TENTERA : ' WHEN SM.JENIS_KP = '6' THEN 'POLIS : ' " +
	  	      " WHEN SM.JENIS_KP = '4' THEN 'PASSPORT : ' WHEN SM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' " +
	  	      " ELSE '' END) || SM.NO_KP_LAIN ELSE '' END END END) AS MYID_SIMATI, " +
	  	      " UPPER (PM.NAMA_PEMOHON) AS NAMA_PEMOHON, " +
	  	      " UPPER (CASE WHEN PM.NO_KP_BARU IS NOT NULL THEN PM.NO_KP_BARU ELSE CASE " +
	  	      " WHEN PM.NO_KP_LAMA IS NOT NULL THEN PM.NO_KP_LAMA ELSE CASE WHEN PM.NO_KP_LAIN IS NOT NULL " +
	  	      " THEN    (CASE WHEN PM.JENIS_KP = '5' THEN 'TENTERA : ' WHEN PM.JENIS_KP = '6' THEN 'POLIS : ' " +
	  	      " WHEN PM.JENIS_KP = '4' THEN 'PASSPORT : ' WHEN PM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' ELSE '' END) || PM.NO_KP_LAIN " +
	  	      " ELSE '' END END END ) AS MYID_PEMOHON, PM.ID_PEMOHON, P.SEKSYEN, P.ID_PERMOHONAN " +
	  	      " FROM TBLPPKPERMOHONAN P, TBLPPKOBPERMOHONAN OBP, TBLPPKPERMOHONANSIMATI PSM, TBLPFDFAIL F, USERS_ONLINE UO, TBLPPKSIMATI SM, " +
	  	      " TBLPPKPEMOHON PM, TBLRUJSTATUS S, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN B, TBLPPKPERINTAH PERINTAH " +
	  	      " WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI " +
	  	      " AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_FAIL = F.ID_FAIL AND F.ID_SEKSYEN = 2 " +
	  	      " AND (   (    OBP.NO_KP_BARU = UO.NO_KP_BARU AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0')) " +
	  	      " OR F.ID_MASUK = '"+idMasuk+"') AND PSM.ID_SIMATI = SM.ID_SIMATI AND S.ID_STATUS = P.ID_STATUS AND D.ID_DAERAH = P.ID_DAERAHMHN " +
	  	      " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) AND B.ID_KEPUTUSANPERMOHONAN(+) = KP.ID_KEPUTUSANPERMOHONAN AND N.ID_NEGERI(+) = B.ID_NEGERIBICARA " +
	  	      " AND PERINTAH.ID_PERBICARAAN(+) = B.ID_PERBICARAAN AND P.ID_STATUS NOT IN ('150', '160') AND UO.USER_ID = '"+idMasuk+"' ";*/
	      
	      
	      
	      sql = " SELECT DISTINCT F.ID_FAIL, TO_CHAR(F.NO_FAIL) AS NO_FAIL, P.ID_PERMOHONAN, P.SEKSYEN, "+
	    		  " CASE WHEN P.NO_PERMOHONAN_ONLINE IS NOT NULL THEN P.NO_PERMOHONAN_ONLINE ELSE CASE WHEN P.ID_STATUS IN (150,160) THEN 'DERAF' ELSE '' END END NO_PERMOHONAN_ONLINE, "+
	    		  " CASE WHEN P.NO_PERMOHONAN_ONLINE IS NOT NULL THEN TO_CHAR (P.TARIKH_MOHON_ONLINE,'DD/MM/YYYY') ELSE TO_CHAR (P.TARIKH_MOHON, 'DD/MM/YYYY') END AS TARIKH, P.TARIKH_MASUK, "+
	    		  " TO_CHAR (P.TARIKH_MOHON_ONLINE,'DD/MM/YYYY') as TARIKH_MOHON_ONLINE, P.TARIKH_MOHON, "+
	    		  " SM.NAMA_SIMATI,UPPER(CASE WHEN SM.NO_KP_BARU IS NOT NULL THEN SM.NO_KP_BARU ELSE CASE WHEN SM.NO_KP_LAMA IS NOT NULL THEN SM.NO_KP_LAMA ELSE "+
	    		  " CASE WHEN SM.NO_KP_LAIN IS NOT NULL THEN (CASE WHEN SM.JENIS_KP = '5' THEN 'Tentera : ' WHEN SM.JENIS_KP = '6' THEN 'Polis : ' "+
	    		  " WHEN SM.JENIS_KP = '4' THEN 'Passport : ' WHEN SM.JENIS_KP = '7' THEN 'Lain-lain : ' ELSE '' END) ||  SM.NO_KP_LAIN ELSE '' END END END) AS MYID_SIMATI,   "+
	    		  " UPPER (PM.NAMA_PEMOHON) AS NAMA_PEMOHON, UPPER(CASE WHEN PM.NO_KP_BARU IS NOT NULL THEN PM.NO_KP_BARU ELSE CASE WHEN PM.NO_KP_LAMA IS NOT NULL THEN PM.NO_KP_LAMA ELSE "+
	    		  " CASE WHEN PM.NO_KP_LAIN IS NOT NULL THEN (CASE WHEN PM.JENIS_KP = '5' THEN 'Tentera : ' WHEN PM.JENIS_KP = '6' THEN 'Polis : ' "+
	    		  " WHEN PM.JENIS_KP = '4' THEN 'Passport : ' WHEN PM.JENIS_KP = '7' THEN 'Lain-lain : ' ELSE '' END) ||  PM.NO_KP_LAIN ELSE '' END END END) AS MYID_PEMOHON, PM.ID_PEMOHON, P.ID_STATUS, "+
	    		  " CASE WHEN P.ID_STATUS = '171' THEN    UPPER (S.KETERANGAN) || ' DI ' || UPPER (D.NAMA_DAERAH) WHEN P.ID_STATUS IN ('21', '8', '18', '44') THEN UPPER (S.KETERANGAN) "+
	    		  " WHEN P.ID_STATUS IN ('37', '47', '70', '152', '169') THEN 'BATAL' ELSE 'DALAM PROSES' END AS STATUS, "+
	    		  " CASE WHEN PERINTAH.FLAG_TANGGUH = '1' THEN 'PEMOHON / WARIS TIDAK HADIR' WHEN PERINTAH.FLAG_TANGGUH = '2' THEN 'MENUNGGU KEPUTUSAN RUJUKAN MAHKAMAH SYARIAH' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '3' THEN 'MENUNGGU KEPUTUSAN RUJUKAN KEPADA RULER OF THE STATE ATAU MAHKAMAH TINGGI (BORANG J)' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '4' THEN 'MENUNGGU SIJIL FARAID' WHEN PERINTAH.FLAG_TANGGUH = '5' THEN 'SENARAI WARIS TIDAK LENGKAP' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '6' THEN 'BUKTI KEMATIAN TIDAK LENGKAP' WHEN PERINTAH.FLAG_TANGGUH = '7' THEN 'PERTELINGKAHAN KOLATERAL' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '8' THEN 'MENUNGGU SURAT AKUAN PERSETUJUAN' WHEN PERINTAH.FLAG_TANGGUH = '9' THEN 'SEBAB-SEBAB LAIN' ELSE 'TIADA' "+
	    		  " END AS FLAG_TANGGUH, PERINTAH.SEBAB_TANGGUH, PERINTAH.TEMPOH_TUNGGU_FARAID, PERINTAH.CHECK_KIV, PERINTAH.DATE_KIV, PERINTAH.CATATAN_KIV, KP.CATATAN, TO_CHAR (B.TARIKH_BICARA, 'DD/MM/YYYY') AS TARIKH_BICARA, "+
	    		  " CASE WHEN B.MASA_BICARA LIKE '%.%' THEN (CASE WHEN NVL (LENGTH (SUBSTR (B.MASA_BICARA, 1, INSTR (B.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' || CASE "+
	    		  " WHEN NVL (LENGTH (SUBSTR (B.MASA_BICARA, INSTR (B.MASA_BICARA, '.') + 1)), 0) = 1 THEN B.MASA_BICARA || '0' ELSE B.MASA_BICARA END WHEN NVL "+
	    		  " (LENGTH (SUBSTR (B.MASA_BICARA, INSTR (B.MASA_BICARA, '.' ) + 1 ) ), 0 ) = 1 THEN B.MASA_BICARA || '0' ELSE B.MASA_BICARA END ) "+
	    		  " WHEN LENGTH (B.MASA_BICARA) = 4 THEN    SUBSTR (B.MASA_BICARA, 1, 2) || '.' || SUBSTR (B.MASA_BICARA, 3) ELSE '' END || "+
	    		  " (CASE WHEN B.JENIS_MASA_BICARA = '1' THEN ' PAGI' WHEN B.JENIS_MASA_BICARA = '2' THEN ' TENGAH HARI' WHEN B.JENIS_MASA_BICARA = '3' THEN ' PETANG' ELSE '' END) AS MASA_BICARA, "+
	    		  " B.TEMPAT_BICARA, UPPER (B.ALAMAT_BICARA1) AS ALAMAT_BICARA1, UPPER (B.ALAMAT_BICARA2) AS ALAMAT_BICARA2, UPPER (B.ALAMAT_BICARA3) AS ALAMAT_BICARA3, B.POSKOD, UPPER (B.BANDAR) AS BANDAR, B.ID_NEGERIBICARA, "+
	    		  " UPPER (N.NAMA_NEGERI) AS NAMA_NEGERI, TO_CHAR (B.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK_BICARA, "+
	    		  " PJ.NAMA_PEJABAT, TO_CHAR (B.TARIKH_NOTIS, 'DD/MM/YYYY') AS TARIKH_NOTIS "
	    		  + ",B.ID_PERBICARAAN " +
	    		  " FROM TBLPPKPERMOHONAN P, TBLPPKOBPERMOHONAN OBP, TBLPPKPERMOHONANSIMATI PSM, TBLPFDFAIL F, USERS_ONLINE UO, TBLPPKSIMATI SM, TBLPPKPEMOHON PM,  "+
	    		  " TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN B, TBLPPKPERINTAH PERINTAH, TBLRUJSTATUS S, TBLRUJDAERAH D, TBLRUJNEGERI N, "+
	    		  " TBLRUJPEJABATJKPTG PJ, TBLRUJPEJABATURUSAN PU"+
	    		  " WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI  AND PM.ID_PEMOHON = P.ID_PEMOHON  "+
	    		  " AND P.ID_FAIL = F.ID_FAIL AND pu.id_daerahurus = p.id_daerahmhn(+) AND pu.id_jenispejabat = 22 AND pj.id_pejabatjkptg = pu.id_pejabatjkptg(+) AND pj.id_seksyen = 2 AND F.ID_SUBURUSAN IN (59,60) " +
	    		  //" AND OBP.NO_KP_BARU = UO.NO_KP_BARU   "+
	    		  " AND PSM.ID_SIMATI = SM.ID_SIMATI " +
	    		  " AND (   (    OBP.NO_KP_BARU = UO.NO_KP_BARU AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0')) OR F.ID_MASUK = '"+idMasuk+"')  " +
	    		  " AND NVL(OBP.NO_KP_BARU,' ') NOT IN ('-','TIADA',' ','0') "+
	    		  " AND P.ID_STATUS = S.ID_STATUS AND P.ID_DAERAHMHN = D.ID_DAERAH(+) AND P.ID_NEGERIMHN = N.ID_NEGERI "+
	    		  " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) AND KP.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN(+) "+
	    		  " AND B.ID_PERBICARAAN = PERINTAH.ID_PERBICARAAN(+) AND P.ID_STATUS NOT IN ('150', '160') AND UO.USER_ID = '"+idMasuk+"' ";
	      
	      
	      
		    if(!kpsimati.equals(""))
      		{// CHECK KP SIMATI
      		sql = sql + " AND MYID_SIMATI = '"+kpsimati+"' ";
       		}
      		
      		if(!kppemohon.equals(""))
      		{// CHECK KP PEMOHON
      		sql = sql + " AND MYID_PEMOHON = '"+kppemohon+"' ";
       		}
		    
		    	sql = sql + " ORDER BY TARIKH, P.TARIKH_MASUK DESC ";
	
	      myLogger.info("SQL LIST ONLINE >> "+sql.toUpperCase());
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("tarikhMohon", rs.getString("TARIKH_MOHON")==null?"":rs.getString("TARIKH_MOHON"));
	    	  h.put("tarikh_mohon_online", rs.getString("TARIKH_MOHON_ONLINE")==null?"":rs.getString("TARIKH_MOHON_ONLINE")); 
	    	  h.put("tarikh", rs.getString("TARIKH")==null?"":rs.getString("TARIKH")); 
	    	  h.put("tarikhmasuk", rs.getString("TARIKH_MASUK")==null?"":rs.getString("TARIKH_MASUK"));
	    	  h.put("idFail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
	    	  h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
	    	  h.put("no", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
	    	  h.put("id_Permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
	    	  h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
	    	  h.put("icSimati", rs.getString("MYID_SIMATI")==null?"":rs.getString("MYID_SIMATI"));
	    	  h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
	    	  h.put("icPemohon", rs.getString("MYID_PEMOHON")==null?"":rs.getString("MYID_PEMOHON"));
	    	  h.put("id_pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
	    	  h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
	    	  h.put("status", rs.getString("STATUS")==null?"":rs.getString("STATUS"));
	    	  h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
	    	  //h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
	    	  h.put("nama_pejabat", rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT"));
	    	  h.put("tarikh_bicara", rs.getString("TARIKH_BICARA")==null?"":rs.getString("TARIKH_BICARA"));
	    	  h.put("tarikh_notis", rs.getString("TARIKH_NOTIS")==null?"":rs.getString("TARIKH_NOTIS"));
	    	  h.put("masa_bicara", rs.getString("MASA_BICARA")==null?"":rs.getString("MASA_BICARA"));
	    	  h.put("tempat_bicara", rs.getString("TEMPAT_BICARA")==null?"":rs.getString("TEMPAT_BICARA"));
	    	  h.put("alamat1", rs.getString("ALAMAT_BICARA1")==null?"":rs.getString("ALAMAT_BICARA1"));
	    	  h.put("alamat2", rs.getString("ALAMAT_BICARA2")==null?"":rs.getString("ALAMAT_BICARA2"));
	    	  h.put("alamat3", rs.getString("ALAMAT_BICARA3")==null?"":rs.getString("ALAMAT_BICARA3"));
	    	  h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
	    	  h.put("bandar", rs.getString("BANDAR")==null?"":rs.getString("BANDAR"));
	    	  h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
	    	  h.put("idPerbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
	    	  h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));

	    	  /*
	    	  h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
	    	  h.put("id_Permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
	    	  h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
	    	  h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));*/
	    	  
	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	//syafiqah add untuk list permohonan bantahan online 21/6/2020
	public static Vector getSenaraiPermohonanBantahan(String search,String idMasuk,String role,String kppemohon,String kpsimati, String USER_LOGIN_SYSTEM, String flag_draff)throws Exception {
		Db db = null;
	    String sql = "";
	    Format formatter = new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = " SELECT DISTINCT F.ID_FAIL, TO_CHAR(F.NO_FAIL) AS NO_FAIL, P.ID_PERMOHONAN, P.SEKSYEN, "+
	    		  " CASE WHEN P.NO_PERMOHONAN_ONLINE IS NOT NULL THEN P.NO_PERMOHONAN_ONLINE ELSE CASE WHEN P.ID_STATUS IN (150,160) THEN 'DERAF' ELSE '' END END NO_PERMOHONAN_ONLINE, "+
	    		  " CASE WHEN P.NO_PERMOHONAN_ONLINE IS NOT NULL THEN TO_CHAR (P.TARIKH_MOHON_ONLINE,'DD/MM/YYYY') ELSE TO_CHAR (P.TARIKH_MOHON, 'DD/MM/YYYY') END AS TARIKH, P.TARIKH_MASUK, "+
	    		  " TO_CHAR (P.TARIKH_MOHON_ONLINE,'DD/MM/YYYY') as TARIKH_MOHON_ONLINE, P.TARIKH_MOHON, "+
	    		  " SM.NAMA_SIMATI,UPPER(CASE WHEN SM.NO_KP_BARU IS NOT NULL THEN SM.NO_KP_BARU ELSE CASE WHEN SM.NO_KP_LAMA IS NOT NULL THEN SM.NO_KP_LAMA ELSE "+
	    		  " CASE WHEN SM.NO_KP_LAIN IS NOT NULL THEN (CASE WHEN SM.JENIS_KP = '5' THEN 'Tentera : ' WHEN SM.JENIS_KP = '6' THEN 'Polis : ' "+
	    		  " WHEN SM.JENIS_KP = '4' THEN 'Passport : ' WHEN SM.JENIS_KP = '7' THEN 'Lain-lain : ' ELSE '' END) ||  SM.NO_KP_LAIN ELSE '' END END END) AS MYID_SIMATI,   "+
	    		  " UPPER (PM.NAMA_PEMOHON) AS NAMA_PEMOHON, UPPER(CASE WHEN PM.NO_KP_BARU IS NOT NULL THEN PM.NO_KP_BARU ELSE CASE WHEN PM.NO_KP_LAMA IS NOT NULL THEN PM.NO_KP_LAMA ELSE "+
	    		  " CASE WHEN PM.NO_KP_LAIN IS NOT NULL THEN (CASE WHEN PM.JENIS_KP = '5' THEN 'Tentera : ' WHEN PM.JENIS_KP = '6' THEN 'Polis : ' "+
	    		  " WHEN PM.JENIS_KP = '4' THEN 'Passport : ' WHEN PM.JENIS_KP = '7' THEN 'Lain-lain : ' ELSE '' END) ||  PM.NO_KP_LAIN ELSE '' END END END) AS MYID_PEMOHON, PM.ID_PEMOHON, P.ID_STATUS, "+
	    		  " CASE WHEN P.ID_STATUS = '171' THEN    UPPER (S.KETERANGAN) || ' DI ' || UPPER (D.NAMA_DAERAH) WHEN P.ID_STATUS IN ('21', '8', '18', '44') THEN UPPER (S.KETERANGAN) "+
	    		  " WHEN P.ID_STATUS IN ('37', '47', '70', '152', '169') THEN 'BATAL' ELSE 'DALAM PROSES' END AS STATUS, "+
	    		  " CASE WHEN PERINTAH.FLAG_TANGGUH = '1' THEN 'PEMOHON / WARIS TIDAK HADIR' WHEN PERINTAH.FLAG_TANGGUH = '2' THEN 'MENUNGGU KEPUTUSAN RUJUKAN MAHKAMAH SYARIAH' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '3' THEN 'MENUNGGU KEPUTUSAN RUJUKAN KEPADA RULER OF THE STATE ATAU MAHKAMAH TINGGI (BORANG J)' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '4' THEN 'MENUNGGU SIJIL FARAID' WHEN PERINTAH.FLAG_TANGGUH = '5' THEN 'SENARAI WARIS TIDAK LENGKAP' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '6' THEN 'BUKTI KEMATIAN TIDAK LENGKAP' WHEN PERINTAH.FLAG_TANGGUH = '7' THEN 'PERTELINGKAHAN KOLATERAL' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '8' THEN 'MENUNGGU SURAT AKUAN PERSETUJUAN' WHEN PERINTAH.FLAG_TANGGUH = '9' THEN 'SEBAB-SEBAB LAIN' ELSE 'TIADA' "+
	    		  " END AS FLAG_TANGGUH, PERINTAH.SEBAB_TANGGUH, PERINTAH.TEMPOH_TUNGGU_FARAID, PERINTAH.CHECK_KIV, PERINTAH.DATE_KIV, PERINTAH.CATATAN_KIV, KP.CATATAN, TO_CHAR (B.TARIKH_BICARA, 'DD/MM/YYYY') AS TARIKH_BICARA, "+
	    		  " CASE WHEN B.MASA_BICARA LIKE '%.%' THEN (CASE WHEN NVL (LENGTH (SUBSTR (B.MASA_BICARA, 1, INSTR (B.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' || CASE "+
	    		  " WHEN NVL (LENGTH (SUBSTR (B.MASA_BICARA, INSTR (B.MASA_BICARA, '.') + 1)), 0) = 1 THEN B.MASA_BICARA || '0' ELSE B.MASA_BICARA END WHEN NVL "+
	    		  " (LENGTH (SUBSTR (B.MASA_BICARA, INSTR (B.MASA_BICARA, '.' ) + 1 ) ), 0 ) = 1 THEN B.MASA_BICARA || '0' ELSE B.MASA_BICARA END ) "+
	    		  " WHEN LENGTH (B.MASA_BICARA) = 4 THEN    SUBSTR (B.MASA_BICARA, 1, 2) || '.' || SUBSTR (B.MASA_BICARA, 3) ELSE '' END || "+
	    		  " (CASE WHEN B.JENIS_MASA_BICARA = '1' THEN ' PAGI' WHEN B.JENIS_MASA_BICARA = '2' THEN ' TENGAH HARI' WHEN B.JENIS_MASA_BICARA = '3' THEN ' PETANG' ELSE '' END) AS MASA_BICARA, "+
	    		  " B.TEMPAT_BICARA, UPPER (B.ALAMAT_BICARA1) AS ALAMAT_BICARA1, UPPER (B.ALAMAT_BICARA2) AS ALAMAT_BICARA2, UPPER (B.ALAMAT_BICARA3) AS ALAMAT_BICARA3, B.POSKOD, UPPER (B.BANDAR) AS BANDAR, B.ID_NEGERIBICARA, "+
	    		  " UPPER (N.NAMA_NEGERI) AS NAMA_NEGERI, TO_CHAR (B.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK_BICARA, "+
	    		  " PJ.NAMA_PEJABAT, TO_CHAR (B.TARIKH_NOTIS, 'DD/MM/YYYY') AS TARIKH_NOTIS "
	    		  + ",B.ID_PERBICARAAN " +
	    		  " FROM TBLPPKPERMOHONAN P, TBLPPKOBPERMOHONAN OBP, TBLPPKPERMOHONANSIMATI PSM, TBLPFDFAIL F, USERS_ONLINE UO, TBLPPKSIMATI SM, TBLPPKPEMOHON PM,  "+
	    		  " TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN B, TBLPPKPERINTAH PERINTAH, TBLRUJSTATUS S, TBLRUJDAERAH D, TBLRUJNEGERI N, "+
	    		  " TBLRUJPEJABATJKPTG PJ, TBLRUJPEJABATURUSAN PU"+
	    		  " WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI  AND PM.ID_PEMOHON = P.ID_PEMOHON  "+
	    		  " AND P.ID_FAIL = F.ID_FAIL AND pu.id_daerahurus = p.id_daerahmhn(+) AND pu.id_jenispejabat = 22 AND pj.id_pejabatjkptg = pu.id_pejabatjkptg(+) AND pj.id_seksyen = 2 AND F.ID_SUBURUSAN IN (59,60) " +
	    		  //" AND OBP.NO_KP_BARU = UO.NO_KP_BARU   "+
	    		  " AND PSM.ID_SIMATI = SM.ID_SIMATI " +
	    		  " AND (   (    OBP.NO_KP_BARU = UO.NO_KP_BARU AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0')) OR F.ID_MASUK = '"+idMasuk+"')  " +
	    		  " AND NVL(OBP.NO_KP_BARU,' ') NOT IN ('-','TIADA',' ','0') "+
	    		  " AND P.ID_STATUS = S.ID_STATUS AND P.ID_DAERAHMHN = D.ID_DAERAH(+) AND P.ID_NEGERIMHN = N.ID_NEGERI "+
	    		  " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) AND KP.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN(+) "+
	    		  " AND B.ID_PERBICARAAN = PERINTAH.ID_PERBICARAAN(+) AND P.ID_STATUS NOT IN ('150', '160','21','169','47','70','152') AND UO.USER_ID = '"+idMasuk+"' ";
	      
	      // add pada id_status not in (21 - selesai, 169,47,70,152 - batal)
	    
	      if(!kpsimati.equals(""))
    		{// CHECK KP SIMATI
    		sql = sql + " AND MYID_SIMATI = '"+kpsimati+"' ";
     		}
    		
    		if(!kppemohon.equals(""))
    		{// CHECK KP PEMOHON
    		sql = sql + " AND MYID_PEMOHON = '"+kppemohon+"' ";
     		}
		    
		    	sql = sql + " ORDER BY TARIKH, P.TARIKH_MASUK DESC ";
	
	      myLogger.info("SQL LIST FOR BANTAHAN >> "+sql.toUpperCase());
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("tarikhMohon", rs.getString("TARIKH_MOHON")==null?"":rs.getString("TARIKH_MOHON"));
	    	  h.put("tarikh_mohon_online", rs.getString("TARIKH_MOHON_ONLINE")==null?"":rs.getString("TARIKH_MOHON_ONLINE")); 
	    	  h.put("tarikh", rs.getString("TARIKH")==null?"":rs.getString("TARIKH")); 
	    	  h.put("tarikhmasuk", rs.getString("TARIKH_MASUK")==null?"":rs.getString("TARIKH_MASUK"));
	    	  h.put("idFail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
	    	  h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
	    	  h.put("no", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
	    	  h.put("id_Permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
	    	  h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
	    	  h.put("icSimati", rs.getString("MYID_SIMATI")==null?"":rs.getString("MYID_SIMATI"));
	    	  h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
	    	  h.put("icPemohon", rs.getString("MYID_PEMOHON")==null?"":rs.getString("MYID_PEMOHON"));
	    	  h.put("id_pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
	    	  h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
	    	  h.put("status", rs.getString("STATUS")==null?"":rs.getString("STATUS"));
	    	  h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
	    	  //h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
	    	  h.put("nama_pejabat", rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT"));
	    	  h.put("tarikh_bicara", rs.getString("TARIKH_BICARA")==null?"":rs.getString("TARIKH_BICARA"));
	    	  h.put("tarikh_notis", rs.getString("TARIKH_NOTIS")==null?"":rs.getString("TARIKH_NOTIS"));
	    	  h.put("masa_bicara", rs.getString("MASA_BICARA")==null?"":rs.getString("MASA_BICARA"));
	    	  h.put("tempat_bicara", rs.getString("TEMPAT_BICARA")==null?"":rs.getString("TEMPAT_BICARA"));
	    	  h.put("alamat1", rs.getString("ALAMAT_BICARA1")==null?"":rs.getString("ALAMAT_BICARA1"));
	    	  h.put("alamat2", rs.getString("ALAMAT_BICARA2")==null?"":rs.getString("ALAMAT_BICARA2"));
	    	  h.put("alamat3", rs.getString("ALAMAT_BICARA3")==null?"":rs.getString("ALAMAT_BICARA3"));
	    	  h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
	    	  h.put("bandar", rs.getString("BANDAR")==null?"":rs.getString("BANDAR"));
	    	  h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
	    	  h.put("idPerbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
	    	  
	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public static Vector getSenaraiBantahan(String search,String idMasuk,String role,String kppemohon,String kpsimati, String USER_LOGIN_SYSTEM, String flag_draff)throws Exception {
		Db db = null;
	    String sql = "";
	    Format formatter = new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = " SELECT DISTINCT B.ID_PEMBANTAH, B.NAMA_PEMBANTAH, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, B.BANDAR, B.NEGERI, "
	      		+ "B.EMEL, B.NO_HP, B.SEBAB, B.ID_FAIL, TO_CHAR(B.NO_FAIL) AS NO_FAIL"+
	    		  " FROM TBLPPKBANTAHANONLINE B"+
	    		  " WHERE B.ID_PEMBANTAH = '"+idMasuk+"' ";
	      
	      // add pada id_status not in (21 - selesai, 169,47,70,152 - batal)
	    
	      if(!kpsimati.equals(""))
    		{// CHECK KP SIMATI
    		sql = sql + " AND MYID_SIMATI = '"+kpsimati+"' ";
     		}
    		
    		if(!kppemohon.equals(""))
    		{// CHECK KP PEMOHON
    		sql = sql + " AND MYID_PEMOHON = '"+kppemohon+"' ";
     		}
		    
		   // sql = sql + " ORDER BY TARIKH, P.TARIKH_MASUK DESC ";
	
	      myLogger.info("SQL LIST SYAFIQAH >> "+sql.toUpperCase());
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  // h.put("namaP", rs.getString("TARIKH_MOHON")==null?"":rs.getString("TARIKH_MOHON"));
	    	  // h.put("tarikh_mohon_online", rs.getString("TARIKH_MOHON_ONLINE")==null?"":rs.getString("TARIKH_MOHON_ONLINE")); 
	    	  // h.put("tarikh", rs.getString("TARIKH")==null?"":rs.getString("TARIKH")); 
	    	  
	    	  
	    	  h.put("id_pembantah", rs.getString("ID_PEMBANTAH")==null?"":rs.getString("ID_PEMBANTAH"));
	    	  h.put("nama_pembantah", rs.getString("NAMA_PEMBANTAH")==null?"":rs.getString("NAMA_PEMBANTAH"));
	    	  h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
	    	  h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
	    	  h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
	    	  h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
	    	  h.put("bandar", rs.getString("BANDAR")==null?"":rs.getString("BANDAR"));
	    	  h.put("negeri", rs.getString("NEGERI")==null?"":rs.getString("NEGERI"));
	    	  h.put("emel", rs.getString("EMEL")==null?"":rs.getString("EMEL"));
	    	  h.put("no_hp", rs.getString("NO_HP")==null?"":rs.getString("NO_HP"));
	    	  h.put("sebab", rs.getString("SEBAB")==null?"":rs.getString("SEBAB"));
	    	  h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
	    	  h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
	    	  
	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	// syafiqah add ends
	
	//aishahidris tambah untuk list baru deraf 3rdOct2017
	public static Vector getSenaraiTugasanDraf(String search,String idMasuk,String role,String kppemohon,String kpsimati,String flag_draff)throws Exception {
		
		
	    Db db = null;
	    String sql = "";
	    Format formatter = new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      /*sql = " SELECT DISTINCT(P.ID_FAIL), TO_CHAR (P.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK, P.TARIKH_MASUK, " +
	    		" PM.NAMA_SIMATI, M.NAMA_PEMOHON, P.SEKSYEN, S.KETERANGAN, " +
	    		" P.NO_PERMOHONAN_ONLINE, P.ID_STATUS, NO_SUBJAKET, " +
	    		" PM.NO_KP_BARU AS KPBARU_SIMATI, PM.NO_KP_LAMA AS KPLAMA_SIMATI, PM.NO_KP_LAIN AS KPLAIN_SIMATI, " +
	    		" M.NO_KP_BARU AS KPBARU_PEMOHON, M.NO_KP_LAMA AS KPLAMA_PEMOHON, M.NO_KP_LAIN AS KPLAIN_PEMOHON, " +
	    		" SM.ID_PERMOHONANSIMATI, P.ID_PERMOHONAN, PM.ID_SIMATI, M.ID_PEMOHON " +
	      		" FROM TBLPPKPERMOHONAN P, TBLPPKSIMATI PM, TBLPPKPERMOHONANSIMATI SM, TBLPPKPEMOHON M, TBLRUJSTATUS S " +
	      		" WHERE P.ID_PEMOHON = M.ID_PEMOHON " +
	      		" AND P.ID_STATUS = S.ID_STATUS " +
	      		" AND P.ID_PERMOHONAN = SM.ID_PERMOHONAN " +
	      		" AND SM.ID_SIMATI = PM.ID_SIMATI " +
	      		" AND P.ID_STATUS IN (150, 160) "+
	      		" AND P.ID_MASUK = '"+idMasuk+"' "+
	      		" ORDER BY P.TARIKH_MASUK DESC ";*/
	      
	      sql = " SELECT DISTINCT F.ID_MASUK, TO_CHAR (P.TARIKH_MOHON, 'DD/MM/YYYY') AS TARIKH_MOHON, " +
	      		" TO_CHAR (P.TARIKH_MOHON_ONLINE, 'DD/MM/YYYY') AS TARIKH_MOHON_ONLINE, " +
	      		" TO_CHAR (P.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK, F.ID_FAIL, NO_SUBJAKET, " +
	      		" TO_CHAR (F.NO_FAIL) AS NO_FAIL, P.NO_PERMOHONAN_ONLINE, SM.NAMA_SIMATI, " +
	      		" UPPER (CASE WHEN SM.NO_KP_BARU IS NOT NULL THEN SM.NO_KP_BARU " +
	      		" ELSE CASE WHEN SM.NO_KP_LAMA IS NOT NULL THEN SM.NO_KP_LAMA ELSE CASE WHEN SM.NO_KP_LAIN IS NOT NULL THEN " +
	      		" (CASE WHEN SM.JENIS_KP = '5' THEN 'TENTERA : ' WHEN SM.JENIS_KP = '6' THEN 'POLIS : ' " +
	      		" WHEN SM.JENIS_KP = '4' THEN 'PASSPORT : ' WHEN SM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' " +
	      		" ELSE ''  END) || SM.NO_KP_LAIN ELSE '' END END END) AS MYID_SIMATI, " +
	      		" UPPER (PM.NAMA_PEMOHON) AS NAMA_PEMOHON, UPPER (CASE WHEN PM.NO_KP_BARU IS NOT NULL THEN PM.NO_KP_BARU " +
	      		" ELSE CASE WHEN PM.NO_KP_LAMA IS NOT NULL THEN PM.NO_KP_LAMA ELSE CASE WHEN PM.NO_KP_LAIN IS NOT NULL THEN " +
	      		" (CASE WHEN PM.JENIS_KP = '5' THEN 'TENTERA : ' WHEN PM.JENIS_KP = '6' THEN 'POLIS : ' " +
	      		" WHEN PM.JENIS_KP = '4' THEN 'PASSPORT : ' WHEN PM.JENIS_KP = '7' THEN 'LAIN-LAIN : ' " +
	      		" ELSE '' END) || PM.NO_KP_LAIN ELSE '' END END END) AS MYID_PEMOHON, PM.ID_PEMOHON, P.SEKSYEN, " +
	      		" CASE WHEN P.ID_STATUS = '171' THEN    UPPER (S.KETERANGAN) || ' DI ' || UPPER (D.NAMA_DAERAH) " +
	      		" WHEN P.ID_STATUS NOT IN (171) THEN UPPER (S.KETERANGAN) END AS STATUS,P.ID_STATUS, P.ID_PERMOHONAN " +
	      		" FROM TBLPPKPERMOHONAN P,TBLPPKOBPERMOHONAN OBP,TBLPPKPERMOHONANSIMATI PSM,TBLPFDFAIL F, " +
	      		" USERS_ONLINE UO,TBLPPKSIMATI SM,TBLPPKPEMOHON PM,TBLRUJSTATUS S,TBLRUJDAERAH D " +
	      		" WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI " +
	      		" AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_FAIL = F.ID_FAIL AND F.ID_SEKSYEN = 2 " +
	      		" AND ((OBP.NO_KP_BARU = UO.NO_KP_BARU AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0')) OR F.ID_MASUK = '"+idMasuk+"') " +
	      		" AND PSM.ID_SIMATI = SM.ID_SIMATI AND S.ID_STATUS = P.ID_STATUS AND D.ID_DAERAH(+) = P.ID_DAERAHMHN " +
	      		" AND P.NO_PERMOHONAN_ONLINE IS NULL AND P.ID_STATUS IN ('150', '160') " +
	      		//" AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0') " +
	      		" AND UO.USER_ID = '"+idMasuk+"' " +
	      		" ORDER BY TARIKH_MOHON, TARIKH_MOHON_ONLINE, TARIKH_MASUK DESC ";
	      
	      
	      
	      /*sql = " SELECT DISTINCT F.ID_FAIL, TO_CHAR(F.NO_FAIL) AS NO_FAIL, "+
	    		  " CASE WHEN P.NO_PERMOHONAN_ONLINE IS NOT NULL THEN P.NO_PERMOHONAN_ONLINE ELSE CASE WHEN P.ID_STATUS IN (150,160) THEN 'DERAF' ELSE '' END END NO_PERMOHONAN_ONLINE, "+
	    		  " CASE WHEN P.NO_PERMOHONAN_ONLINE IS NOT NULL THEN TO_CHAR (P.TARIKH_MOHON_ONLINE,'DD/MM/YYYY') ELSE TO_CHAR (P.TARIKH_MOHON, 'DD/MM/YYYY') END AS TARIKH, P.TARIKH_MASUK, "+
	    		  " SM.NAMA_SIMATI,UPPER(CASE WHEN SM.NO_KP_BARU IS NOT NULL THEN SM.NO_KP_BARU ELSE CASE WHEN SM.NO_KP_LAMA IS NOT NULL THEN SM.NO_KP_LAMA ELSE "+
	    		  " CASE WHEN SM.NO_KP_LAIN IS NOT NULL THEN (CASE WHEN SM.JENIS_KP = '5' THEN 'Tentera : ' WHEN SM.JENIS_KP = '6' THEN 'Polis : ' "+
	    		  " WHEN SM.JENIS_KP = '4' THEN 'Passport : ' WHEN SM.JENIS_KP = '7' THEN 'Lain-lain : ' ELSE '' END) ||  SM.NO_KP_LAIN ELSE '' END END END) AS MYID,   "+
	    		  " UPPER (PM.NAMA_PEMOHON) AS NAMA_PEMOHON, UPPER(CASE WHEN PM.NO_KP_BARU IS NOT NULL THEN PM.NO_KP_BARU ELSE CASE WHEN PM.NO_KP_LAMA IS NOT NULL THEN PM.NO_KP_LAMA ELSE "+
	    		  " CASE WHEN PM.NO_KP_LAIN IS NOT NULL THEN (CASE WHEN PM.JENIS_KP = '5' THEN 'Tentera : ' WHEN PM.JENIS_KP = '6' THEN 'Polis : ' "+
	    		  " WHEN PM.JENIS_KP = '4' THEN 'Passport : ' WHEN PM.JENIS_KP = '7' THEN 'Lain-lain : ' ELSE '' END) ||  PM.NO_KP_LAIN ELSE '' END END END) AS MYID_PEMOHON, P.ID_STATUS, "+
	    		  " CASE WHEN P.ID_STATUS = '171' THEN    UPPER (S.KETERANGAN) || ' DI ' || UPPER (D.NAMA_DAERAH) WHEN P.ID_STATUS IN ('21', '8', '18', '44') THEN UPPER (S.KETERANGAN) "+
	    		  " WHEN P.ID_STATUS IN ('37', '47', '70', '152', '169') THEN 'BATAL' ELSE 'DALAM PROSES' END AS STATUS, "+
	    		  " CASE WHEN PERINTAH.FLAG_TANGGUH = '1' THEN 'PEMOHON / WARIS TIDAK HADIR' WHEN PERINTAH.FLAG_TANGGUH = '2' THEN 'MENUNGGU KEPUTUSAN RUJUKAN MAHKAMAH SYARIAH' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '3' THEN 'MENUNGGU KEPUTUSAN RUJUKAN KEPADA RULER OF THE STATE ATAU MAHKAMAH TINGGI (BORANG J)' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '4' THEN 'MENUNGGU SIJIL FARAID' WHEN PERINTAH.FLAG_TANGGUH = '5' THEN 'SENARAI WARIS TIDAK LENGKAP' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '6' THEN 'BUKTI KEMATIAN TIDAK LENGKAP' WHEN PERINTAH.FLAG_TANGGUH = '7' THEN 'PERTELINGKAHAN KOLATERAL' "+
	    		  " WHEN PERINTAH.FLAG_TANGGUH = '8' THEN 'MENUNGGU SURAT AKUAN PERSETUJUAN' WHEN PERINTAH.FLAG_TANGGUH = '9' THEN 'SEBAB-SEBAB LAIN' ELSE 'TIADA' "+
	    		  " END AS FLAG_TANGGUH, PERINTAH.SEBAB_TANGGUH, PERINTAH.TEMPOH_TUNGGU_FARAID, PERINTAH.CHECK_KIV, PERINTAH.DATE_KIV, PERINTAH.CATATAN_KIV, KP.CATATAN, TO_CHAR (B.TARIKH_BICARA, 'DD/MM/YYYY') AS TARIKH_BICARA, "+
	    		  " CASE WHEN B.MASA_BICARA LIKE '%.%' THEN (CASE WHEN NVL (LENGTH (SUBSTR (B.MASA_BICARA, 1, INSTR (B.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' || CASE "+
	    		  " WHEN NVL (LENGTH (SUBSTR (B.MASA_BICARA, INSTR (B.MASA_BICARA, '.') + 1)), 0) = 1 THEN B.MASA_BICARA || '0' ELSE B.MASA_BICARA END WHEN NVL "+
	    		  " (LENGTH (SUBSTR (B.MASA_BICARA, INSTR (B.MASA_BICARA, '.' ) + 1 ) ), 0 ) = 1 THEN B.MASA_BICARA || '0' ELSE B.MASA_BICARA END ) "+
	    		  " WHEN LENGTH (B.MASA_BICARA) = 4 THEN    SUBSTR (B.MASA_BICARA, 1, 2) || '.' || SUBSTR (B.MASA_BICARA, 3) ELSE '' END || "+
	    		  " (CASE WHEN B.JENIS_MASA_BICARA = '1' THEN ' PAGI' WHEN B.JENIS_MASA_BICARA = '2' THEN ' TENGAH HARI' WHEN B.JENIS_MASA_BICARA = '3' THEN ' PETANG' ELSE '' END) AS MASA_BICARA, "+
	    		  " B.TEMPAT_BICARA, UPPER (B.ALAMAT_BICARA1) AS ALAMAT_BICARA1, UPPER (B.ALAMAT_BICARA2) AS ALAMAT_BICARA2, UPPER (B.ALAMAT_BICARA3) AS ALAMAT_BICARA3, B.POSKOD, UPPER (B.BANDAR) AS BANDAR, B.ID_NEGERIBICARA, "+
	    		  " UPPER (N.NAMA_NEGERI) AS NAMA_NEGERI, TO_CHAR (B.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK_BICARA "+
	    		  " FROM TBLPPKPERMOHONAN P, TBLPPKOBPERMOHONAN OBP, TBLPPKPERMOHONANSIMATI PSM, TBLPFDFAIL F, USERS_ONLINE UO, TBLPPKSIMATI SM, TBLPPKPEMOHON PM,  "+
	    		  " TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN B, TBLPPKPERINTAH PERINTAH, TBLRUJSTATUS S, TBLRUJDAERAH D, TBLRUJNEGERI N "+
	    		  " WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI  AND PM.ID_PEMOHON = P.ID_PEMOHON  "+
	    		  " AND P.ID_FAIL = F.ID_FAIL AND F.ID_SEKSYEN = 2 AND F.ID_SUBURUSAN IN (59,60) AND OBP.NO_KP_BARU = UO.NO_KP_BARU   "+
	    		  " AND PSM.ID_SIMATI = SM.ID_SIMATI AND NVL(OBP.NO_KP_BARU,' ') NOT IN ('-','TIADA',' ','0') "+
	    		  " AND P.ID_STATUS = S.ID_STATUS AND P.ID_DAERAHMHN = D.ID_DAERAH(+) AND P.ID_NEGERIMHN = N.ID_NEGERI "+
	    		  " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) AND KP.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN(+) "+
	    		  " AND B.ID_PERBICARAAN = PERINTAH.ID_PERBICARAAN(+) AND P.ID_STATUS IN ('150', '160') AND UO.USER_ID = '"+idMasuk+"' "+
	    		  " ORDER BY TARIKH, P.TARIKH_MASUK DESC ";*/
	      
	      
	      
	      myLogger.info("SQL LIST DRAF >>>>>>>>>>>>> "+sql.toUpperCase());
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  /*h.put("tarikhmasuk", rs.getString("TARIKH_MASUK")==null?"":rs.getString("TARIKH_MASUK"));
	    	  h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
	    	  h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
	    	  h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
	    	  h.put("status", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
	    	  h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
	    	  h.put("nokpbarusimati", rs.getString("KPBARU_SIMATI")==null?"":rs.getString("KPBARU_SIMATI"));
	    	  h.put("nokplamasimati", rs.getString("KPLAMA_SIMATI")==null?"":rs.getString("KPLAMA_SIMATI"));
	    	  h.put("nokplainsimati", rs.getString("KPLAIN_SIMATI")==null?"":rs.getString("KPLAIN_SIMATI"));
	    	  h.put("nokpbarupemohon", rs.getString("KPBARU_PEMOHON")==null?"":rs.getString("KPBARU_PEMOHON"));
	    	  h.put("nokplamapemohon", rs.getString("KPLAMA_PEMOHON")==null?"":rs.getString("KPLAMA_PEMOHON"));
	    	  h.put("nokplainpemohon", rs.getString("KPLAIN_PEMOHON")==null?"":rs.getString("KPLAIN_PEMOHON"));
	    	  h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
	    	  h.put("id_Permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
	    	  h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
	    	  h.put("id_pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));*/
	    	  
	    	  h.put("tarikhMohon", rs.getString("TARIKH_MOHON")==null?"":rs.getString("TARIKH_MOHON"));
	    	  h.put("tarikh_mohon_online", rs.getString("TARIKH_MOHON_ONLINE")==null?"":rs.getString("TARIKH_MOHON_ONLINE"));
	    	  h.put("tarikhmasuk", rs.getString("TARIKH_MASUK")==null?"":rs.getString("TARIKH_MASUK"));
	    	  h.put("idFail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
	    	  h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
	    	  h.put("no", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
	    	  h.put("id_Permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
	    	  h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
	    	  h.put("icSimati", rs.getString("MYID_SIMATI")==null?"":rs.getString("MYID_SIMATI"));
	    	  h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
	    	  h.put("icPemohon", rs.getString("MYID_PEMOHON")==null?"":rs.getString("MYID_PEMOHON"));
	    	  h.put("id_pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
	    	  h.put("status", rs.getString("STATUS")==null?"":rs.getString("STATUS"));
	    	  h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
	    	  h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
	    	  
	    	  list.addElement(h);
	      }
	      
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
}
