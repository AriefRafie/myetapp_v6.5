package ekptg.model.htp.rekod.kjp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmKjpRekodDB {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.kjp.FrmKjpRekodDB.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("rawtypes")
	private static Vector dbMaklumatPTP = null;
	private static Vector<Hashtable<String,String>> dbMaklumatPTPByNegeri = null;
	private static Vector<Hashtable<String,String>> dbMaklumatPTPByKementerian = null;
	private static Vector<Hashtable<String,String>> dbMaklumatPTPByNegeriDetail = null;
	private static Vector<Hashtable<String,String>> dbDataNegeri = null;
	@SuppressWarnings("rawtypes")
	private static Vector dbDataHakmilik = null;
	private static Vector<Hashtable<String,String>> dbDataKementerian = null;
	private static Vector<Hashtable<String,String>> dbDataAgensi = null;
	private static Vector<Hashtable<String,String>> dbMaklumatPTPByAgensiDetail = null;
	private static Vector<Hashtable<String,String>> listCarian = null;
	private String sql = "";
	private	Db db = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDBLuasPTP() throws Exception {
	    dbMaklumatPTP = new Vector();
	    try {    	
		    	db = new Db();
		    	Statement stmt = db.getStatement(); 
		    	
		    	sql = " SELECT SUM(NVL((" +
		    		  "	SELECT A.LUAS_BERSAMAAN FROM TBLHTPHAKMILIK A1 " +
		    		  "	WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK AND NVL (A1.no_hakmilik, ' ') <> ' '" +
		    		  "   AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
		    		  "),0)) AS LUAS_TANAH_MILIK "+
		    		  " ,SUM(NVL((" +
		    		  "		SELECT A.LUAS_BERSAMAAN FROM TBLHTPHAKMILIK A1 " +
		    		  "		WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK AND NVL (A1.no_warta, ' ') <> ' ' " +
			   			"   AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
		    		  "	),0)) AS LUAS_TANAH_RIZAB "+
		    		  " FROM tblhtphakmilik a, "+
		    		  " tblpermohonan b, "+
		    		  " tblpfdfail c, "+
		    		  " tblrujjenishakmilik e, "+
		    		  " tblrujkementerian rk, "+
		    	      " tblrujkementerianmapping rkme "+
		    		  " WHERE a.id_permohonan = b.id_permohonan "+
		    	      " AND b.id_fail = c.id_fail "+
		    		  " AND a.id_jenishakmilik = e.id_jenishakmilik "+
		    		  " AND rkme.id_kementerianlama = a.id_kementerian "+
		    	      " AND rkme.id_kementerianbaru = rk.id_kementerian "+
		    		  " ORDER BY c.no_fail DESC ";		    	
		    	//myLog.info("sql main = "+sql);
				ResultSet rs = stmt.executeQuery(sql);	 
				Hashtable h;
		      	
		      	while (rs.next()) {
		    	  h = new Hashtable();
		    	  Double dblTanahRizab = rs.getDouble("LUAS_TANAH_RIZAB");
		    	  Double dblTanahMilik = rs.getDouble("LUAS_TANAH_MILIK");
		    	  Double dblKeseluruhan = (dblTanahRizab + dblTanahMilik);
		    	  Double percentTanahMilik = (dblTanahMilik / dblKeseluruhan) * 100;
		    	  Double percentTanahRizab = (dblTanahRizab / dblKeseluruhan) * 100;
		    	  
		    	  h.put("luas_tanah_milik", dblTanahMilik== 0?0:dblTanahMilik);
		    	  h.put("luas_tanah_rizab", dblTanahRizab==0?0:dblTanahRizab);
		    	  h.put("luas_keseluruhan", dblKeseluruhan);
		    	  h.put("percentTanahMilik", percentTanahMilik);
		    	  h.put("percentTanahRizab", percentTanahRizab);
		    	  
		    	  dbMaklumatPTP.addElement(h);
		      	}
	    }finally {
	    	if (db != null) db.close();
	    }		
	    return dbMaklumatPTP;
	    
	}
	
	public Vector<Hashtable<String,String>> getDBLuasPTPByNegeri() throws Exception {
		dbMaklumatPTPByNegeri = new Vector<Hashtable<String,String>>();
	    try {    	
		    db = new Db();
		    Statement stmt = db.getStatement(); 
		    sql = " SELECT RN.NAMA_NEGERI, RN.ID_NEGERI,RN.KOD_MAMPU "+   
		    		" ,SUM (NVL ((SELECT A1.LUAS_BERSAMAAN "+
		   			" 	FROM TBLHTPHAKMILIK A1 "+
		   			" 	WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK "+
		   			" 	AND NVL (A1.NO_HAKMILIK, ' ') <> ' '" +
		   			"   AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
		   			" ),0)) AS LUAS_TANAH_MILIK "+ 
	    			" ,SUM (NVL ((SELECT A1.LUAS_BERSAMAAN "+
	    			" 	FROM TBLHTPHAKMILIK A1 "+
	    			" 	WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK "+
		    		" 	AND NVL (A1.NO_WARTA, ' ') <> ' '" +
		   			"   AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
		    		" ),0)) AS luas_tanah_rizab "+
		    		" ,SUM (NVL ((SELECT COUNT(*) BIL "+
		   			" 	FROM TBLHTPHAKMILIK A1 "+
		   			" 	WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK "+
		   			" 	AND NVL (A1.NO_HAKMILIK, ' ') <> ' '" +
		   			"   AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
		   			" ),0)) AS BIL_TANAH_MILIK "+ 
	    			" ,SUM (NVL ((SELECT COUNT(*) BIL "+
	    			" 	FROM TBLHTPHAKMILIK A1 "+
	    			" 	WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK "+
		    		" 	AND NVL (A1.NO_WARTA, ' ') <> ' '" +
		   			"   AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
		    		" ),0)) AS BIL_tanah_rizab "+                  
		   			
		    		" FROM TBLHTPHAKMILIK A, "+
		   			" TBLPERMOHONAN B, "+
		    		" TBLPFDFAIL C, "+
		   			" TBLRUJJENISHAKMILIK E, "+
		   			" TBLRUJKEMENTERIAN RK, "+
		   			" TBLRUJKEMENTERIANMAPPING RKME, "+
		   			" TBLRUJNEGERI RN "+
	    			" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
		    		" AND B.ID_FAIL = C.ID_FAIL "+
		    		" AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
		   			" AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
		   			" AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN" +
		   			" AND RKME.STATUS = 'A' "+
		   			" AND A.ID_NEGERI = RN.ID_NEGERI "+
		   			" GROUP BY RN.NAMA_NEGERI, RN.ID_NEGERI,RN.KOD_MAMPU " +
		   			" ORDER BY RN.KOD_MAMPU";		
		    myLog.info("sql negeri = "+sql);
		    ResultSet rs = stmt.executeQuery(sql);	 
		    Hashtable<String,String> h;
		    int bil = 1;
		    while (rs.next()) {
		    	h = new Hashtable<String,String>();
		    	Double dblTanahRizab = rs.getDouble("LUAS_TANAH_RIZAB");
		    	Double dblTanahMilik = rs.getDouble("LUAS_TANAH_MILIK");
		    	Double dblKeseluruhan = (dblTanahRizab + dblTanahMilik);
		    	int bilKeseluruhan = (rs.getInt("BIL_TANAH_MILIK") + rs.getInt("BIL_TANAH_RIZAB"));
		    	Double percentTanahMilik = (dblTanahMilik / dblKeseluruhan) * 100;
		    	Double percentTanahRizab = (dblTanahRizab / dblKeseluruhan) * 100;
		    	  
		    	h.put("bil", String.valueOf(bil));
		    	h.put("negeri", rs.getString("NAMA_NEGERI") == null?"":rs.getString("NAMA_NEGERI"));
		    	h.put("id_negeri", rs.getString("ID_NEGERI") == null?"":rs.getString("ID_NEGERI"));
		    	h.put("luas_tanah_milik", String.valueOf(dblTanahMilik== 0?0:dblTanahMilik));
		    	h.put("luas_tanah_rizab", String.valueOf(dblTanahRizab==0?0:dblTanahRizab));
		    	h.put("luasTanahMilikF", String.valueOf(dblTanahMilik== 0?"0.00000":Utils.formatLuas(dblTanahMilik)));
		    	h.put("luasTanahRizabF", String.valueOf(dblTanahRizab==0?"0.00000":Utils.formatLuas(dblTanahRizab)));
		    	h.put("luas_keseluruhan", String.valueOf(dblKeseluruhan));
		    	h.put("luasKeseluruhanF", String.valueOf(Utils.formatLuas(dblKeseluruhan)));
		    	h.put("percentTanahMilik", String.valueOf(percentTanahMilik));
		    	h.put("percentTanahRizab", String.valueOf(percentTanahRizab));	  
		    	h.put("bilTanahMilik", rs.getString("BIL_TANAH_MILIK"));
		    	h.put("bilTanahRizab", rs.getString("BIL_TANAH_RIZAB"));
		    	h.put("bilTanah", String.valueOf(bilKeseluruhan));

		    	dbMaklumatPTPByNegeri.addElement(h);
		    	bil++;
		      	
		    }
	    
	    }finally {
	    	if (db != null) db.close();
	    }		
	    return dbMaklumatPTPByNegeri;
	    
	}
	
	public Vector<Hashtable<String,String>> getDBLuasPTPByKementerian() throws Exception {
		dbMaklumatPTPByKementerian = new Vector<Hashtable<String,String>>();
	    try {    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
		    	
	    	sql = " SELECT RK.NAMA_KEMENTERIAN, RK.ID_KEMENTERIAN, "+   
	    		 " SUM (NVL ((SELECT A1.LUAS_BERSAMAAN "+
	    		 " 	FROM TBLHTPHAKMILIK A1 "+
	    		 " 	WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK "+
	    		 " 	AND NVL (A1.NO_HAKMILIK, ' ') <> ' '" +
	    		 "   AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
	    		 "	),0)) AS LUAS_TANAH_MILIK  "+
	    		 " ,SUM (NVL ((SELECT A1.LUAS_BERSAMAAN "+
	    		 " 	FROM TBLHTPHAKMILIK A1 "+
	    		 " 	WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK "+
	    		 " 	AND NVL (A1.NO_WARTA, ' ') <> ' '" +
	    		 " 	AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
	    		 "	),0)) AS LUAS_TANAH_RIZAB "+                
	    		 " ,SUM (NVL ((SELECT COUNT(*) "+
	    		 " 	FROM TBLHTPHAKMILIK A1 "+
	    		 " 	WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK "+
	    		 " 	AND NVL (A1.NO_HAKMILIK, ' ') <> ' '" +
	    		 "   AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
	    		 "	),0)) AS BIL_TANAH_MILIK  "+
	    		 " ,SUM (NVL ((SELECT COUNT(*)  "+
	    		 " 	FROM TBLHTPHAKMILIK A1 "+
	    		 " 	WHERE A1.ID_HAKMILIK = A.ID_HAKMILIK "+
	    		 " 	AND NVL (A1.NO_WARTA, ' ') <> ' '" +
	    		 " 	AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
	    		 "	),0)) AS BIL_TANAH_RIZAB "+                
	    		 " FROM TBLHTPHAKMILIK A, "+
	    		 " TBLPERMOHONAN B, "+
	    		 " TBLPFDFAIL C, "+
	    		 " TBLRUJJENISHAKMILIK E, "+
	    		 " TBLRUJKEMENTERIAN RK, "+
	    		 " TBLRUJKEMENTERIANMAPPING RKME "+
	    		 " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
	    		 " AND B.ID_FAIL = C.ID_FAIL "+
	    		 " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
	    		 " AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
	    		 " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN " +
	    		 " AND RKME.STATUS = 'A' "+
	    		 " GROUP BY RK.NAMA_KEMENTERIAN, RK.ID_KEMENTERIAN " +
	    		 " ORDER BY RK.NAMA_KEMENTERIAN ";		    	
		    	myLog.info("sql kementerian = "+sql);
		    ResultSet rs = stmt.executeQuery(sql);	 
		    Hashtable<String,String> h;
			int bil = 1;
				
			while (rs.next()) {
	     		h = new Hashtable<String,String>();
		   		Double dblTanahRizab = rs.getDouble("LUAS_TANAH_RIZAB");
	      		Double dblTanahMilik = rs.getDouble("LUAS_TANAH_MILIK");
	      		Double dblKeseluruhan = (dblTanahRizab + dblTanahMilik);
		     	Double percentTanahMilik = (dblTanahMilik / dblKeseluruhan) * 100;
		   		Double percentTanahRizab = (dblTanahRizab / dblKeseluruhan) * 100;
		    	int bilKeseluruhan = (rs.getInt("BIL_TANAH_MILIK") + rs.getInt("BIL_TANAH_RIZAB"));
		    	  
	    		h.put("bil", String.valueOf(bil));
	      		h.put("id_kementerian", rs.getString("ID_KEMENTERIAN") == null?"":rs.getString("ID_KEMENTERIAN"));
		      	h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null?"":rs.getString("NAMA_KEMENTERIAN"));
		   		h.put("luas_tanah_milik", String.valueOf(dblTanahMilik== 0?0:dblTanahMilik));
		      	h.put("luas_tanah_rizab", String.valueOf(dblTanahRizab==0?0:dblTanahRizab));
		     	h.put("luas_keseluruhan", String.valueOf(dblKeseluruhan));
		      	h.put("luasTanahMilikF", String.valueOf(dblTanahMilik== 0?"0.00000":Utils.formatLuas(dblTanahMilik)));
		      	h.put("luasTanahRizabF", String.valueOf(dblTanahRizab==0?"0.00000":Utils.formatLuas(dblTanahRizab)));
		    	h.put("luasKeseluruhanF", String.valueOf(Utils.formatLuas(dblTanahRizab)));
		      	h.put("percentTanahMilik", String.valueOf(percentTanahMilik));
		   		h.put("percentTanahRizab", String.valueOf(percentTanahRizab));
		    	h.put("bilTanahMilik", rs.getString("BIL_TANAH_MILIK"));
		    	h.put("bilTanahRizab", rs.getString("BIL_TANAH_RIZAB"));
		    	h.put("bilTanah", String.valueOf(bilKeseluruhan));
		   	  
		   		dbMaklumatPTPByKementerian.addElement(h);
		   		bil++;
		    	  
			}
		      	
	    }finally {
	   		if (db != null) db.close();
	   	}		
	   	return dbMaklumatPTPByKementerian;
	
	}
	
	public Vector<Hashtable<String,String>> getDBLuasPTPByNegeriDetail(String idNegeri) throws Exception {
		dbMaklumatPTPByNegeriDetail = new Vector<Hashtable<String,String>>();
	    try {    	
		    	db = new Db();
		    	Statement stmt = db.getStatement(); 
		    	
		    	sql = " SELECT  A.ID_HAKMILIK, RJH.KOD_JENIS_HAKMILIK" +
		    			//", A.NO_HAKMILIK" +
				    	",CASE "+
				    	" 	WHEN NVL(A.NO_WARTA, ' ') <> ' ' THEN A.NO_WARTA "+
				    	" 	WHEN NVL(A.NO_HAKMILIK, ' ') <> ' ' THEN RJH.KOD_JENIS_HAKMILIK ||' '|| A.NO_HAKMILIK  "+
				    	" 	ELSE '' "+
				   		" END AS NO_HAKMILIK" +
		    			", RL.KETERANGAN AS JENIS_LOT, A.NO_LOT, RM.NAMA_MUKIM, RD.NAMA_DAERAH, RK.NAMA_KEMENTERIAN, "+
		    			" CASE "+
		    			" WHEN NVL(A.NO_WARTA, ' ') <> ' ' THEN 'RIZAB' "+
		    			" WHEN NVL(A.NO_HAKMILIK, ' ') <> ' ' THEN 'MILIK'  "+
		    			" ELSE '' "+
		    			" END AS JENIS_TANAH, A.KEGUNAAN_TANAH, A.LUAS_BERSAMAAN    "+
		    			" FROM TBLHTPHAKMILIK A, "+
		    			" TBLPERMOHONAN B, "+
		    			" TBLPFDFAIL C, "+
		    			//" TBLRUJURUSAN D, "+
		    			" TBLRUJJENISHAKMILIK E, "+
		    			" TBLRUJKEMENTERIAN RK, "+
		    			" TBLRUJKEMENTERIANMAPPING RKME, "+
		    			" TBLRUJMUKIM RM, "+
		    			" TBLRUJDAERAH RD, "+
		    			" TBLRUJJENISHAKMILIK RJH, "+
		    			" TBLRUJLOT RL "+
		    			" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
		    			" AND B.ID_FAIL = C.ID_FAIL "+
		    			" AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
		    			//" AND C.ID_URUSAN = D.ID_URUSAN "+
		    			" AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
		    			" AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN "+
		    			" AND A.ID_MUKIM = RM.ID_MUKIM "+
		    			" AND A.ID_DAERAH = RD.ID_DAERAH "+
		    			" AND A.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK "+
		    			" AND RL.ID_LOT = A.ID_LOT "+
		    			" AND A.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
		    			" AND A.ID_NEGERI = '"+idNegeri+"'";
		    	
		    	myLog.info("sql negeri detail = "+sql);
				ResultSet rs = stmt.executeQuery(sql);	 
				Hashtable<String,String> h;
				int bil = 1;
				
		      	while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil", String.valueOf(bil));
		    	  h.put("id_hakmilik", rs.getString("ID_HAKMILIK") == null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null?"":rs.getString("KOD_JENIS_HAKMILIK"));
		    	  h.put("no_hakmilik", rs.getString("NO_HAKMILIK") == null?"":rs.getString("NO_HAKMILIK"));
		    	  h.put("jenis_lot", rs.getString("JENIS_LOT") == null?"":rs.getString("JENIS_LOT"));
		    	  h.put("no_lot", rs.getString("NO_LOT") == null?"":rs.getString("NO_LOT"));
		    	  h.put("nama_mukim", rs.getString("NAMA_MUKIM") == null?"":rs.getString("NAMA_MUKIM"));
		    	  h.put("nama_daerah", rs.getString("NAMA_DAERAH") == null?"":rs.getString("NAMA_DAERAH"));
		    	  h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN") == null?"":rs.getString("NAMA_KEMENTERIAN"));
		    	  h.put("jenis_tanah", rs.getString("JENIS_TANAH") == null?"":rs.getString("JENIS_TANAH"));
		    	  h.put("kegunaan_tanah", rs.getString("KEGUNAAN_TANAH") == null?"":rs.getString("KEGUNAAN_TANAH"));
		    	  h.put("luas", String.valueOf(rs.getDouble("LUAS_BERSAMAAN") == 0?0:rs.getDouble("LUAS_BERSAMAAN")));
		    	  h.put("luasF", String.valueOf(rs.getDouble("LUAS_BERSAMAAN") == 0?0:Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN"))));
		    	  dbMaklumatPTPByNegeriDetail.addElement(h);
		    	  bil++;
		      	}
		      	
	    }finally {
	    	if (db != null) db.close();
	    }		
	    return dbMaklumatPTPByNegeriDetail;
	    
	}
	
	public Vector<Hashtable<String,String>> getDBDataNegeri(String idNegeri) throws Exception {
		dbDataNegeri = new Vector<Hashtable<String,String>>();
	    try {    	
		    	db = new Db();
		    	Statement stmt = db.getStatement(); 		    	
		    	sql = " SELECT A.NAMA_NEGERI FROM TBLRUJNEGERI A "+
		    		  " WHERE A.ID_NEGERI = '"+idNegeri+"'";
		    	
				ResultSet rs = stmt.executeQuery(sql);	 
				Hashtable<String,String> h;
				
		      	while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("nama_negeri", rs.getString("NAMA_NEGERI") == null?"":rs.getString("NAMA_NEGERI"));
		    	  dbDataNegeri.addElement(h);
		    	  
		      	}
		      	
	    }finally {
	    	if (db != null) db.close();
	    }		
	    return dbDataNegeri;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getDBDataHakmilik(String idHakmilik) throws Exception {
		dbDataHakmilik = new Vector();
	    try {    	
		    	db = new Db();
		    	Statement stmt = db.getStatement(); 
		    	
		    	sql = " SELECT RK.NAMA_KEMENTERIAN, RAG.NAMA_AGENSI, C.NO_FAIL, TPM.NO_RUJUKAN_KJP, TPM.NO_RUJUKAN_PTG, C.TAJUK_FAIL, "+
		    			" RD.NAMA_DAERAH, RM.NAMA_MUKIM, RN.NAMA_NEGERI, RJH.KETERANGAN AS JENIS_HAKMILIK, RJH.KOD_JENIS_HAKMILIK, A.NO_HAKMILIK, "+
		    			" RL.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.TARIKH_TERIMA, A.TARIKH_DAFTAR, A.STATUS_SAH, A.HAKMILIK_ASAL, "+
		    			" A.CUKAI, A.CUKAI_TERKINI, A.KEGUNAAN_TANAH, A.LUAS_BERSAMAAN, A.NO_PELAN, A.NO_SYIT, A.NO_WARTA, A.TARIKH_WARTA, NO_PU, "+
		    			" RZB.KETERANGAN AS JENIS_RIZAB, RKT.KETERANGAN AS KATEGORI, A.SYARAT AS SYARAT_NYATA, A.SEKATAN, A.CATATAN "+
		    			" FROM TBLHTPHAKMILIK A, "+
		    			" TBLPERMOHONAN B, "+
		    			" TBLPFDFAIL C, "+
		    			" TBLRUJURUSAN D, "+
		    			" TBLRUJJENISHAKMILIK E, "+
		    			" TBLRUJKEMENTERIAN RK, "+
		    			" TBLRUJKEMENTERIANMAPPING RKME, "+ 
		    			" TBLRUJMUKIM RM, "+
		    			" TBLRUJDAERAH RD, "+
		    			" TBLRUJJENISHAKMILIK RJH, "+
		    			" TBLRUJLOT RL, "+
		    			" TBLRUJAGENSI RAG, "+
		    			" TBLHTPPERMOHONAN TPM, "+
		    			" TBLRUJNEGERI RN, "+
		    			" TBLRUJJENISRIZAB RZB, "+
		    			" TBLRUJKATEGORI RKT "+
		    			" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
		    			" AND B.ID_FAIL = C.ID_FAIL "+
		    			" AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
		    			" AND C.ID_URUSAN = D.ID_URUSAN "+
		    			" AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
		    			" AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN "+
		    			" AND A.ID_MUKIM = RM.ID_MUKIM "+
		    			" AND A.ID_DAERAH = RD.ID_DAERAH "+
		    			" AND A.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK "+
		    			" AND RL.ID_LOT = A.ID_LOT "+
		    			" AND RAG.ID_AGENSI(+) = A.ID_AGENSI "+
		    			" AND TPM.ID_PERMOHONAN = B.ID_PERMOHONAN "+
		    			" AND RN.ID_NEGERI = A.ID_NEGERI "+
		    			" AND A.ID_JENISRIZAB = RZB.ID_JENISRIZAB(+) "+
		    			" AND RKT.ID_KATEGORI = A.ID_KATEGORI "+
		    			" AND A.ID_HAKMILIK = '"+idHakmilik+"' ";
		    	
		    	System.out.println("sql by lot "+sql);
				ResultSet rs = stmt.executeQuery(sql);	 
				Hashtable h;
				
		      	while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN") == null?"TIADA":rs.getString("NAMA_KEMENTERIAN"));
		    	  h.put("nama_agensi", rs.getString("NAMA_AGENSI") == null?"TIADA":rs.getString("NAMA_AGENSI"));
		    	  h.put("no_fail", rs.getString("NO_FAIL") == null?"TIADA":rs.getString("NO_FAIL"));
		    	  h.put("no_rujukan_kjp", rs.getString("NO_RUJUKAN_KJP") == null?"TIADA":rs.getString("NO_RUJUKAN_KJP"));
		    	  h.put("no_rujukan_ptg", rs.getString("NO_RUJUKAN_PTG") == null?"TIADA":rs.getString("NO_RUJUKAN_PTG"));
		    	  h.put("tajuk_fail", rs.getString("TAJUK_FAIL") == null?"TIADA":rs.getString("TAJUK_FAIL"));
		    	  h.put("nama_daerah", rs.getString("NAMA_DAERAH") == null?"TIADA":rs.getString("NAMA_DAERAH"));
		    	  h.put("nama_mukim", rs.getString("NAMA_MUKIM") == null?"TIADA":rs.getString("NAMA_MUKIM"));
		    	  h.put("nama_negeri", rs.getString("NAMA_NEGERI") == null?"TIADA":rs.getString("NAMA_NEGERI"));
		    	  h.put("jenis_hakmilik", rs.getString("JENIS_HAKMILIK") == null?"TIADA":rs.getString("JENIS_HAKMILIK"));
		    	  h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null?"":rs.getString("KOD_JENIS_HAKMILIK"));
		    	  h.put("no_hakmilik", rs.getString("NO_HAKMILIK") == null?"":rs.getString("NO_HAKMILIK"));
		    	  h.put("jenis_lot", rs.getString("JENIS_LOT") == null?"":rs.getString("JENIS_LOT"));
		    	  h.put("no_lot", rs.getString("NO_LOT") == null?"":rs.getString("NO_LOT"));
		    	  h.put("status_sah", rs.getString("STATUS_SAH") == null?"TIADA":rs.getString("STATUS_SAH"));
		    	  h.put("hakmilik_asal", rs.getString("HAKMILIK_ASAL") == null?"TIADA":rs.getString("HAKMILIK_ASAL"));
		    	  h.put("cukai", rs.getDouble("CUKAI") == 0?0.00:rs.getDouble("CUKAI"));
		    	  h.put("cukai_terkini", rs.getDouble("CUKAI_TERKINI") == 0?0.00:rs.getDouble("CUKAI_TERKINI"));
		    	  h.put("kegunaan_tanah", rs.getString("KEGUNAAN_TANAH") == null?"TIADA":rs.getString("KEGUNAAN_TANAH"));
		    	  h.put("luas_bersamaan", rs.getDouble("LUAS_BERSAMAAN") == 0?0.00000:rs.getDouble("LUAS_BERSAMAAN"));
		    	  h.put("no_pelan", rs.getString("NO_PELAN") == null?"TIADA":rs.getString("NO_PELAN"));
		    	  h.put("no_syit", rs.getString("NO_SYIT") == null?"TIADA":rs.getString("NO_SYIT"));
		    	  h.put("no_warta", rs.getString("NO_WARTA") == null?"TIADA":rs.getString("NO_WARTA"));
		    	  h.put("no_pu", rs.getString("NO_PU") == null?"TIADA":rs.getString("NO_PU"));
		    	  h.put("jenis_rizab", rs.getString("JENIS_RIZAB") == null?"TIADA":rs.getString("JENIS_RIZAB"));
		    	  h.put("kategori", rs.getString("KATEGORI") == null?"TIADA":rs.getString("KATEGORI"));
		    	  h.put("syarat_nyata", rs.getString("SYARAT_NYATA") == null?"TIADA":rs.getString("SYARAT_NYATA"));
		    	  h.put("sekatan", rs.getString("SEKATAN") == null?"TIADA":rs.getString("SEKATAN"));
		    	  h.put("catatan", rs.getString("CATATAN") == null?"TIADA":rs.getString("CATATAN"));
		    	  h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"TIADA":Format.format(rs.getDate("TARIKH_TERIMA")));
		    	  h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR")==null?"TIADA":Format.format(rs.getDate("TARIKH_DAFTAR")));
		    	  h.put("tarikh_warta", rs.getDate("TARIKH_WARTA")==null?"TIADA":Format.format(rs.getDate("TARIKH_WARTA")));
		    	  
		    	  String status = "";
		    	  if(rs.getString("STATUS_SAH").equalsIgnoreCase("D")){status = "DAFTAR";}
		    	  else if(rs.getString("STATUS_SAH").equalsIgnoreCase("D")){status = "DAFTAR";}
		    	  else if(rs.getString("STATUS_SAH").equalsIgnoreCase("H")){status = "HAKMILIK ASAL TIADA";}
		    	  else if(rs.getString("STATUS_SAH").equalsIgnoreCase("T")){status = "TUKARGUNA";}
		    	  else if(rs.getString("STATUS_SAH").equalsIgnoreCase("P")){status = "BATAL (PELEPASAN)";}
		    	  else if(rs.getString("STATUS_SAH").equalsIgnoreCase("S")){status = "BATAL (SAMBUNGAN)";}
		    	  else if(rs.getString("STATUS_SAH").equalsIgnoreCase("M")){status = "BATAL (PINDAHMILIK)";}
		    	  else if(rs.getString("STATUS_SAH").equalsIgnoreCase("L")){status = "BATAL (PERLETAKHAKAN)";}
		    	  
		    	  h.put("status", status == ""?"TIADA":status);
		    	  
		    	  dbDataHakmilik.addElement(h);
		      	}
		      	
	    }finally {
	    	if (db != null) db.close();
	    }		
	    return dbDataHakmilik;
	    
	}
	
	public Vector<Hashtable<String,String>> getDBDataKementerian(String idKementerian) throws Exception {
		dbDataKementerian = new Vector<Hashtable<String,String>>();
	    try {    	
		    	db = new Db();
		    	Statement stmt = db.getStatement(); 
		    	
		    	sql = " SELECT A.NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN A "+
		    		  " WHERE A.ID_KEMENTERIAN = '"+idKementerian+"'";		    	
				ResultSet rs = stmt.executeQuery(sql);	 
				Hashtable<String,String> h;
				
		      	while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN") == null?"":rs.getString("NAMA_KEMENTERIAN"));
		    	  dbDataKementerian.addElement(h);
		      	}
		      	
	    }finally {
	    	if (db != null) db.close();
	    }		
	    return dbDataKementerian;
	    
	}
	
	public Vector<Hashtable<String,String>> getDBLuasPTPByKementerianDetail(String idKementerian) throws Exception {
		dbMaklumatPTPByKementerian = new Vector<Hashtable<String,String>>();
	    try {    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
		    	
		    	sql = " SELECT DISTINCT(RA.NAMA_AGENSI) NAMA_AGENSI, RA.ID_AGENSI "+
		    			" ,(SELECT SUM(NVL (A1.LUAS_BERSAMAAN,0 )) "+
		    			" 	FROM TBLRUJAGENSIMAPPING RAM,TBLHTPHAKMILIK A1 "+
		    			"  	WHERE A1.ID_AGENSI=RAM.ID_AGENSILAMA " +
		    			" 	AND RAM.STATUS = 'A' " +
		    			"  	AND NVL (A1.NO_HAKMILIK, ' ') <> ' '" +
			    		" 	AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
			    		"	AND RAM.ID_AGENSIBARU=RA.ID_AGENSI " +
		    			" ) AS LUAS_TANAH_MILIK "+
		    			" ,(SELECT SUM(NVL (A1.LUAS_BERSAMAAN,0 )) "+
		    			" 	FROM TBLRUJAGENSIMAPPING RAM,TBLHTPHAKMILIK A1 "+
		    			"  	WHERE A1.ID_AGENSI=RAM.ID_AGENSILAMA " +
		    			" 	AND RAM.STATUS = 'A' " +
		    			"  	AND NVL (A1.NO_WARTA, ' ') <> ' '" +
			    		" 	AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
			    		"	AND RAM.ID_AGENSIBARU=RA.ID_AGENSI " +
		    			" ) AS LUAS_TANAH_RIZAB "+	
		    			" ,(SELECT COUNT(*) "+
		    			" 	FROM TBLRUJAGENSIMAPPING RAM,TBLHTPHAKMILIK A1 "+
		    			"  	WHERE A1.ID_AGENSI=RAM.ID_AGENSILAMA " +
		    			" 	AND RAM.STATUS = 'A' " +
		    			"  	AND NVL (A1.NO_HAKMILIK, ' ') <> ' '" +
			    		" 	AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
			    		"	AND RAM.ID_AGENSIBARU=RA.ID_AGENSI " +
		    			" ) AS BIL_TANAH_MILIK "+
		    			" ,(SELECT COUNT(*) "+
		    			" 	FROM TBLRUJAGENSIMAPPING RAM,TBLHTPHAKMILIK A1 "+
		    			"  	WHERE A1.ID_AGENSI=RAM.ID_AGENSILAMA " +
		    			" 	AND RAM.STATUS = 'A' " +
		    			"  	AND NVL (A1.NO_WARTA, ' ') <> ' '" +
			    		" 	AND A1.STATUS_SAH IN (SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1) " +
			    		"	AND RAM.ID_AGENSIBARU=RA.ID_AGENSI " +
		    			" ) AS BIL_TANAH_RIZAB "+		    					    		
		    			" FROM TBLRUJAGENSI RA,TBLHTPHAKMILIK MT,TBLRUJKEMENTERIAN RK "+
		    			" WHERE MT.ID_AGENSI=RA.ID_AGENSI " +
		    			" AND RA.ID_KEMENTERIAN = RK.ID_KEMENTERIAN "+
		    			" AND RA.ID_KEMENTERIAN = '"+idKementerian+"' " +
		    			//" GROUP BY RA.ID_AGENSI,RA.NAMA_AGENSI "+
		    			" ORDER BY RA.NAMA_AGENSI" +
		    			"";
		    	myLog.info("sql kementerian detail = "+sql);
				ResultSet rs = stmt.executeQuery(sql);	 
				Hashtable<String,String> h;
				int bil = 1;
				
		      	while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  Double dblTanahRizab = rs.getDouble("LUAS_TANAH_RIZAB");
		    	  Double dblTanahMilik = rs.getDouble("LUAS_TANAH_MILIK");
		    	  Double dblKeseluruhan = (dblTanahRizab + dblTanahMilik);
		    	  Double percentTanahMilik = (dblTanahMilik / dblKeseluruhan) * 100;
		    	  Double percentTanahRizab = (dblTanahRizab / dblKeseluruhan) * 100;
		    	  int bilKeseluruhan = (rs.getInt("BIL_TANAH_MILIK") + rs.getInt("BIL_TANAH_RIZAB"));
		    	  
		    	  h.put("bil", String.valueOf(bil));
		    	  h.put("idAgensi", rs.getString("id_agensi") == null?"":rs.getString("id_agensi"));
		    	  h.put("namaAgensi", rs.getString("nama_agensi") == null?"":rs.getString("nama_agensi"));
		    	  h.put("luas_tanah_milik", String.valueOf(dblTanahMilik== 0?0:dblTanahMilik));
		    	  h.put("luas_tanah_rizab", String.valueOf(dblTanahRizab==0?0:dblTanahRizab));
		    	  h.put("luas_keseluruhan", String.valueOf(dblKeseluruhan));
		    	  h.put("luasTanahMilikF", String.valueOf(dblTanahMilik== 0?"0.00000":Utils.formatLuas(dblTanahMilik)));
		    	  h.put("luasTanahRizabF", String.valueOf(dblTanahRizab==0?"0.00000":Utils.formatLuas(dblTanahRizab)));
		    	  h.put("luasKeseluruhanF", String.valueOf(Utils.formatLuas(dblKeseluruhan)));		    	  
		    	  h.put("percentTanahMilik", String.valueOf(percentTanahMilik));
		    	  h.put("percentTanahRizab", String.valueOf(percentTanahRizab));  
			    	h.put("bilTanahMilik", rs.getString("BIL_TANAH_MILIK"));
			    	h.put("bilTanahRizab", rs.getString("BIL_TANAH_RIZAB"));
			    	h.put("bilTanah", String.valueOf(bilKeseluruhan));
		    	  dbMaklumatPTPByKementerian.addElement(h);
		    	  bil++;
		    	  
		      	}
	    }finally {
	    	if (db != null) db.close();
	    }		
	    return dbMaklumatPTPByKementerian;
	
	}
	
	public Vector<Hashtable<String,String>> getDBDataAgensi(String idAgensi) throws Exception {
		dbDataAgensi = new Vector<Hashtable<String,String>>();
	    try {    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
		    	
	    	sql = " SELECT A.NAMA_AGENSI, B.NAMA_KEMENTERIAN FROM TBLRUJAGENSI A, TBLRUJKEMENTERIAN B "+
	    		" WHERE A.ID_KEMENTERIAN = B.ID_KEMENTERIAN AND A.ID_AGENSI = '"+idAgensi+"'";
		    ResultSet rs = stmt.executeQuery(sql);	 
				
		    Hashtable<String,String> h;
		    while (rs.next()) {
		    	h = new Hashtable<String,String>();
		    	h.put("nama_agensi", rs.getString("NAMA_AGENSI") == null?"":rs.getString("NAMA_AGENSI"));
		    	h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN") == null?"":rs.getString("NAMA_KEMENTERIAN"));
		    	dbDataAgensi.addElement(h);
		    	  
		    }
		      	
	    }finally {
	    	if (db != null) db.close();
	    }		
	    return dbDataAgensi;
	    
	}
	
	public Vector<Hashtable<String,String>> getDBLuasPTPByAgensiDetail(String idAgensi) throws Exception {
		dbMaklumatPTPByAgensiDetail = new Vector<Hashtable<String,String>>();
	    try {    	
	    	db = new Db();
	    	Statement stmt = db.getStatement(); 
		    	
	    	sql = " SELECT  A.ID_HAKMILIK, RJH.KOD_JENIS_HAKMILIK" +
		    			//", A.NO_HAKMILIK" +
		    	",CASE "+
		    	" 	WHEN NVL(A.NO_WARTA, ' ') <> ' ' THEN A.NO_WARTA "+
		    	" 	WHEN NVL(A.NO_HAKMILIK, ' ') <> ' ' THEN RJH.KOD_JENIS_HAKMILIK ||' '|| A.NO_HAKMILIK  "+
		    	" 	ELSE '' "+
		   		" END AS NO_HAKMILIK" +
	 			", RL.KETERANGAN AS JENIS_LOT, A.NO_LOT, RM.NAMA_MUKIM, RD.NAMA_DAERAH, RK.NAMA_KEMENTERIAN "+
		   		",CASE "+
		   		" 	WHEN NVL(A.NO_WARTA, ' ') <> ' ' THEN 'RIZAB' "+
		   		" 	WHEN NVL(A.NO_HAKMILIK, ' ') <> ' ' THEN 'MILIK'  "+
		   		" 	ELSE '' "+
		   		" END AS JENIS_TANAH" +
		   		", A.KEGUNAAN_TANAH, A.LUAS_BERSAMAAN, A.CUKAI    "+
		   		" FROM TBLHTPHAKMILIK A, "+
		   		" TBLPERMOHONAN B, "+
		   		" TBLPFDFAIL C, "+
		    			//" TBLRUJURUSAN D, "+
		    	" TBLRUJJENISHAKMILIK E, "+
		    	" TBLRUJKEMENTERIAN RK, "+
		    	" TBLRUJKEMENTERIANMAPPING RKME, "+
		    	" TBLRUJMUKIM RM, "+
		    	" TBLRUJDAERAH RD, "+
		    	" TBLRUJJENISHAKMILIK RJH, "+
		    	" TBLRUJLOT RL "+
		    	" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
		    	" AND B.ID_FAIL = C.ID_FAIL "+
		    	" AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
		    			//" AND C.ID_URUSAN = D.ID_URUSAN "+
		    	" AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
		    	" AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN "+
		    	" AND A.ID_MUKIM = RM.ID_MUKIM "+
		    	" AND A.ID_DAERAH = RD.ID_DAERAH "+
		    	" AND A.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK "+
		    	" AND RL.ID_LOT = A.ID_LOT "+
		    	" AND A.ID_AGENSI = '"+idAgensi+"'";		    	
		    	myLog.info("sql agensi detail = "+sql);
				ResultSet rs = stmt.executeQuery(sql);	 
				
				Hashtable<String,String> h;
				int bil = 1;				
		      	while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil", String.valueOf(bil));
		    	  h.put("id_hakmilik", rs.getString("ID_HAKMILIK") == null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null?"":rs.getString("KOD_JENIS_HAKMILIK"));
		    	  h.put("no_hakmilik", rs.getString("NO_HAKMILIK") == null?"":rs.getString("NO_HAKMILIK"));
		    	  h.put("jenis_lot", rs.getString("JENIS_LOT") == null?"":rs.getString("JENIS_LOT"));
		    	  h.put("no_lot", rs.getString("NO_LOT") == null?"":rs.getString("NO_LOT"));
		    	  h.put("nama_mukim", rs.getString("NAMA_MUKIM") == null?"":rs.getString("NAMA_MUKIM"));
		    	  h.put("nama_daerah", rs.getString("NAMA_DAERAH") == null?"":rs.getString("NAMA_DAERAH"));
		    	  h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN") == null?"":rs.getString("NAMA_KEMENTERIAN"));
		    	  h.put("jenis_tanah", rs.getString("JENIS_TANAH") == null?"":rs.getString("JENIS_TANAH"));
		    	  h.put("kegunaan_tanah", rs.getString("KEGUNAAN_TANAH") == null?"":rs.getString("KEGUNAAN_TANAH"));
		    	  h.put("luas", String.valueOf(rs.getDouble("LUAS_BERSAMAAN") == 0?0:rs.getDouble("LUAS_BERSAMAAN")));
		    	  h.put("cukai", String.valueOf(rs.getDouble("CUKAI") == 0?0:rs.getDouble("CUKAI")));
		    	  h.put("luasF", String.valueOf(rs.getDouble("LUAS_BERSAMAAN") == 0?0:Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN"))));
		    	  h.put("cukaiF", String.valueOf(rs.getDouble("CUKAI") == 0?0:Utils.format2Decimal(rs.getDouble("CUKAI"))));
		    	  dbMaklumatPTPByAgensiDetail.addElement(h);
		    	  bil++;
		      	
		      	}
		      	
	    }finally {
	    	if (db != null) db.close();
	    }		
	    return dbMaklumatPTPByAgensiDetail;
	    
	}
	
	public static  Vector<Hashtable<String,String>> getListCarian(){
		return listCarian;
	} 
	
	public static void setListCarian(String idNegeri,String idDaerah,String idMukim
		,String idKementerian,String idAgensi
		,String noHakmilik,String noWarta,String noLot) throws Exception {	
		myLog.info("idNegeri="+idKementerian+",idDaerah="+idDaerah+",idMukim="+idMukim);
		myLog.info("setListCarian:idKementerian="+idKementerian+",idAgensi="+idAgensi);
		myLog.info("setListCarian:noHakmilik="+noHakmilik+",noWarta="+noWarta+",noLot="+noLot);
		listCarian = new Vector<Hashtable<String,String>>();
	    Db db = null;
	    listCarian.clear();
	    String sql = "";	    
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	boolean setLimit = true;  
	    		
    		sql = " SELECT  A.ID_HAKMILIK, RJH.KOD_JENIS_HAKMILIK, A.NO_HAKMILIK, RL.KETERANGAN AS JENIS_LOT, A.NO_LOT, RM.NAMA_MUKIM, RD.NAMA_DAERAH, RK.NAMA_KEMENTERIAN "+
	    		        " ,CASE "+
				        " 	WHEN NVL(A.NO_HAKMILIK,' ') <> ' ' THEN RJH.KOD_JENIS_HAKMILIK ||' '|| A.NO_HAKMILIK "+
				        " 	ELSE A.NO_WARTA "+
				        " END AS NO_HAKMILIKRIZAB "+
		    			" ,CASE "+
		    			" 	WHEN NVL(A.NO_WARTA, ' ') <> ' ' THEN 'RIZAB' "+
		    			" 	WHEN NVL(A.NO_HAKMILIK, ' ') <> ' ' THEN 'MILIK'  "+
		    			" 	ELSE '' "+
		    			" END AS JENIS_TANAH" +
		    			", A.KEGUNAAN_TANAH, A.LUAS_BERSAMAAN    "+
		    			" FROM TBLHTPHAKMILIK A, "+
		    			" TBLPERMOHONAN B, "+
		    			" TBLPFDFAIL C, "+
		    			" TBLRUJURUSAN D, "+
		    			" TBLRUJJENISHAKMILIK E, "+
		    			" TBLRUJKEMENTERIAN RK, "+
		    			" TBLRUJKEMENTERIANMAPPING RKME, "+
		    			" TBLRUJMUKIM RM, "+
		    			" TBLRUJDAERAH RD, "+
		    			" TBLRUJJENISHAKMILIK RJH, "+
		    			" TBLRUJLOT RL "+
		    			" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN "+
		    			" AND B.ID_FAIL = C.ID_FAIL "+
		    			" AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
		    			" AND C.ID_URUSAN = D.ID_URUSAN "+
		    			" AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN "+
		    			" AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN "+
		    			" AND A.ID_MUKIM = RM.ID_MUKIM "+
		    			" AND A.ID_DAERAH = RD.ID_DAERAH "+
		    			" AND A.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK "+
		    			" AND RL.ID_LOT = A.ID_LOT ";
	    		
			//Search by negeri
	    	if(idNegeri!=null && idNegeri!="" && idNegeri!="-1"){
	    		setLimit = false;
	   			sql += " AND A.ID_NEGERI = '"+idNegeri+"'";
		
	    	}
	    	//STATUS
	    	if(idDaerah!=null && idDaerah!="" && idDaerah!="-1"){
				setLimit = false;
				sql += " AND A.ID_DAERAH = '"+idDaerah+"'";
			
	    	}
	    	if(idMukim!=null && idMukim!="" && idMukim!="-1"){
	   			setLimit = false;
				sql += " AND A.ID_MUKIM = '"+idMukim+"'";
	
	    	}
	    	if(idKementerian!=null && idKementerian!="" && !idKementerian.equals("-1")){
	   			setLimit = false;
				sql += " AND A.ID_KEMENTERIAN = '"+idKementerian+"'";
	
	    	}
	    	if(idAgensi != null && idAgensi != "" && !idAgensi.equals("-1")){
	    		setLimit = false;
				sql += " AND A.ID_AGENSI = '"+idAgensi+"'";
	    	
	    	}	    		
	    	if(noHakmilik!=null && noHakmilik!=""){
	   			setLimit = false;
				sql += " AND UPPER(A.NO_HAKMILIK) LIKE '%" + noHakmilik.toUpperCase().trim() + "%' ";

	    	}
	    	if(noWarta!=null && noWarta!=""){
	    		setLimit = false;
				sql += " AND UPPER(A.NO_WARTA) LIKE '%" + noWarta.toUpperCase().trim() + "%' ";
	
	    	}	
	    	if(noLot!=null && noLot!=""){
	   			setLimit = false;
				sql += " AND UPPER(A.NO_LOT) LIKE '%" + noLot.toUpperCase().trim() + "%' ";
	
	    	}	
			if(setLimit){	
				sql += " AND ROWNUM <= 100 ";				
			}
			//myLog.info("sql list "+sql);
	    	ResultSet rs = stmt.executeQuery(sql);
	   		Hashtable<String,String> h;
	   		int bil = 1;
	   		while (rs.next()) {
	   			h = new Hashtable<String,String>();
			   	h.put("bil", String.valueOf(bil));
			   	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
			   	h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")== null?"":rs.getString("KOD_JENIS_HAKMILIK"));
			   	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));			    	
		    	h.put("no_hakmilikrizab", rs.getString("NO_HAKMILIKRIZAB")== null?"":rs.getString("NO_HAKMILIKRIZAB"));
		    	h.put("jenis_lot", rs.getString("JENIS_LOT")== null?"":rs.getString("JENIS_LOT"));
		    	h.put("no_lot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
		    	h.put("nama_mukim", rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));
			   	h.put("nama_daerah", rs.getString("NAMA_DAERAH")== null?"":rs.getString("NAMA_DAERAH"));
			   	h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")== null?"":rs.getString("NAMA_KEMENTERIAN"));
			   	h.put("jenis_tanah", rs.getString("JENIS_TANAH")== null?"":rs.getString("JENIS_TANAH"));
			   	h.put("kegunaan_tanah", rs.getString("KEGUNAAN_TANAH")== null?"":rs.getString("KEGUNAAN_TANAH"));
		    	h.put("luas", String.valueOf(rs.getDouble("LUAS_BERSAMAAN") == 0?0:rs.getDouble("LUAS_BERSAMAAN")));
		    	h.put("luasF", String.valueOf(rs.getDouble("LUAS_BERSAMAAN") == 0?0:Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN"))));
		    	listCarian.addElement(h);
		    	bil++;
			    	
	   		}
	    }finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	
}
