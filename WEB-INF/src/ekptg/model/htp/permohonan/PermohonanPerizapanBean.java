package ekptg.model.htp.permohonan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPPermohonanBean;
import ekptg.model.htp.HTPPermohonanTanahBean;
import ekptg.model.htp.IHTPPermohonan;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class PermohonanPerizapanBean implements IPermohonanPerizapan {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.permohonan.PermohonanPerizapanBean.class);
	private ekptg.model.htp.IHTPPermohonan iPermohonan = null;

	@Override
	public Vector<Hashtable<String, String>> TerimaPohongetList(
		String idUser,String nofail,String tajukfail
		,String id_kementerian,String id_agensi
		,String id_negeri,String id_daerah,String id_mukim
		,String id_urusan
		,String tarikhSurat,String tarikhBukaFail,String tarikhHantar,String tarikhKeputusan) throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    boolean isSearch = false;
	    Hashtable<String,String> h;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "SELECT " +
	    		"  F.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL,F.ID_URUSAN " + 
	    		"  ,F.TARIKH_DAFTAR_FAIL,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR " +
	    		"  ,F.ID_MASUK, F.ID_NEGERI" +
	    		//",F.TARIKH_MASUK,F.ID_KEMASKINI " +
	    		"  ,P.NO_PERMOHONAN, P.TUJUAN " +
	    		"  ,NVL(TO_CHAR(P.TARIKH_SURAT,'dd/MM/yyyy'),'TIADA') TARIKH_SURATF " +
	    		"  ,NVL(STA_.TARIKH_HANTAR,'TIADA') TARIKH_HANTARF " +
	    		"  ,NVL(TO_CHAR(TPKM.TARIKH_KEPUTUSAN,'dd/MM/yyyy'),'TIADA') TARIKH_KEPUTUSANF " +
	    		"  ,NVL(TPKM.STATUS,'0') KEPUTUSAN " +
	    		"  ,NVL(RN.NAMA_NEGERI,'TIADA') NEGERI " +
	    		"  ,S.KETERANGAN "+
	            "  ,CASE " +
	            " 	 WHEN TANAH.HAKMILIK != 0 THEN ''||TANAH.HAKMILIK||' Hakmilik/Warta' " +
	            " 	 ELSE '' " +
	            "  END BIL_TANAH " +
	            //"  ,NVL(GIS.BIL,0) ISGIS " +
	            "  ,GETGISTATUSPERMOHONAN(F.ID_URUSAN)  GIS_STATUS "+
	            "  ,NVL(GIS.STATUS_NOFAIL,'N') GIS_HANTAR "+ 
	            "  ,NVL(GIS.LATITUDE,'N') GIS_CHARTING "+
	            " FROM " + 
	            "  TBLPERMOHONAN P,TBLHTPPERMOHONAN PP " +
	            "  ,TBLPFDFAIL F,TBLRUJSUBURUSANSTATUSFAIL SF " +
	            "  ,TBLRUJSUBURUSANSTATUS US,TBLRUJSTATUS S " + 
	            "  ,TBLRUJNEGERI RN " +	
	            "  ,TBLHTPKEPUTUSANMOHON TPKM " +
	            "  ,(SELECT RSUSF.ID_PERMOHONAN " +
	            "      ,TO_CHAR(RSUSF.TARIKH_MASUK,'dd/MM/yyyy') TARIKH_HANTAR " +
	            "        FROM TBLRUJSUBURUSANSTATUS RSUS,TBLRUJSUBURUSANSTATUSFAIL RSUSF " +
	            "      WHERE RSUS.ID_SUBURUSANSTATUS = RSUSF.ID_SUBURUSANSTATUS " +
	            "      AND RSUS.LANGKAH=10 " +
	            "   ) STA_ " +  
	            "  , (SELECT TPH.ID_PERMOHONAN, COUNT(*) HAKMILIK FROM TBLPERMOHONAN PPI, TBLHTPHAKMILIK TPH " +
	            " 	    WHERE TPH.ID_PERMOHONAN = PPI.ID_PERMOHONAN " +
	            " 	    GROUP BY TPH.ID_PERMOHONAN " +
	            " 	) TANAH " +
//	            "  ,( " +
//	            "         SELECT F.NO_FAIL,COUNT(*) BIL FROM TBLINTGIS GIS,TBLPFDFAIL F " +
//	            "          WHERE GIS.NO_FAIL=F.NO_FAIL " +
//	            "          AND GIS.STATUS_TANAH = GETGISTATUSPERMOHONAN(F.ID_URUSAN) " +
//	            "         GROUP BY F.NO_FAIL " +
//	            "     ) GIS " +	 
				",( SELECT STATUS_TANAH||NO_FAIL STATUS_NOFAIL,LATITUDE FROM TBLINTGIS  " +
				"     WHERE STATUS_TANAH IN (2,3) " +
				"     ) GIS " +
	 			" WHERE  P.ID_FAIL = F.ID_FAIL " +
			    " AND F.ID_URUSAN IN (1,10) " +
			    " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) "+
			    " AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
			    " AND P.ID_PERMOHONAN = SF.ID_PERMOHONAN  "+
			    " AND SF.ID_SUBURUSANSTATUS = US.ID_SUBURUSANSTATUS "+
			    " AND SF.ID_FAIL = F.ID_FAIL "+
			    " AND SF.AKTIF = '1' " +
			    " AND US.ID_STATUS = S.ID_STATUS " +
			    " AND F.ID_NEGERI= RN.ID_NEGERI " +
			    " AND GETGISTATUSPERMOHONAN(F.ID_URUSAN)||F.NO_FAIL = GIS.STATUS_NOFAIL(+) " +
			    " AND P.ID_PERMOHONAN = TPKM.ID_PERMOHONAN(+) " +
			    " AND P.ID_PERMOHONAN = STA_.ID_PERMOHONAN(+) " +
			    " AND PP.ID_PERMOHONAN = TANAH.ID_PERMOHONAN(+) " +
			    "";
	      
	      if (idUser != null) {
	    	  sql = sql + " AND f.id_masuk='"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + " AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + "AND f.id_urusan = "+id_urusan+" ";
	      }	      
	      if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + " AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      }
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }
	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }
	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  //by rosli 24/02/2011
	    	  //sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	    	  sql = sql + " AND P.ID_PERMOHONAN IN ( "+
	    	  	" SELECT THMUI.ID_PERMOHONAN FROM Tblhtphakmilikurusan THMUI "+
	    		" WHERE THMUI.ID_MUKIM = '"+id_mukim+"')"; 
	      }
	      if (tarikhSurat != null && !"-1".equals(tarikhSurat) && !"".equals(tarikhSurat)) {
	    	  sql = sql + " AND TO_CHAR(P.TARIKH_SURAT,'dd/MM/yyyy') = '"+tarikhSurat+"' ";
	      }

	      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
	    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
	      }
	      if (tarikhHantar != null && !"-1".equals(tarikhHantar) && !"".equals(tarikhHantar)) {
	    	  sql = sql + " AND STA_.TARIKH_HANTAR = '"+tarikhHantar+"' ";
	      }

	      if (tarikhKeputusan != null && !"-1".equals(tarikhKeputusan) && !"".equals(tarikhKeputusan)) {
	    	  sql = sql + " AND TO_CHAR(TPKM.TARIKH_KEPUTUSAN,'dd/MM/yyyy') = '"+tarikhKeputusan+"' ";
	      }	      
	      if (!isSearch) {
	    	  //sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      //sql = sql + "ORDER BY F.TARIKH_DAFTAR_FAIL DESC";
	      sql = sql + "ORDER BY P.ID_FAIL DESC" +
	      "" ;
	      myLog.info("TerimaPohongetList-13:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String,String>>();
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("negeri")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("tarikhSurat", rs.getString("TARIKH_SURATF"));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  h.put("tarikhHantar", Utils.isNull(rs.getString("TARIKH_HANTARF")));
	    	  h.put("tarikhKeputusan", Utils.isNull(rs.getString("TARIKH_KEPUTUSANF")));
	    	  //myLog.info(id_negeri+"-"+rs.getString("KEPUTUSAN"));
	    	  if(rs.getString("ID_NEGERI").equals("12") || rs.getString("ID_NEGERI").equals("13")){
	    			  h.put("keputusan", getIPermohonan().getKeputusanPermohonan(
	    					  Integer.parseInt(rs.getString("KEPUTUSAN")), id_negeri)); 
	    	  }else{
	    		  h.put("keputusan"
	    			  , HTPPermohonanTanahBean.getKeputusanPermohonan(Integer.parseInt(rs.getString("KEPUTUSAN"))));
	    	  }
	    	  h.put("bilTanah", Utils.isNull(rs.getString("BIL_TANAH")));
	    	  h.put("idUrusan", Utils.isNull(rs.getString("ID_URUSAN")));
	    	  h.put("gisStatus", rs.getString("GIS_STATUS"));
	    	  h.put("gisHantar", rs.getString("GIS_HANTAR"));
	    	  h.put("gisCharting", rs.getString("GIS_CHARTING"));
	    	  list.addElement(h);
	    	  bil++;
	    	  
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	      
	    }
	    
	  }	
	@Override
	public  Vector<Hashtable<String, String>> TerimaPohongetList(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhBukaFail) throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    boolean isSearch = false;
	    Hashtable<String,String> h;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "SELECT " +
	    		"  F.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL,F.ID_URUSAN " + 
	    		"  ,F.TARIKH_DAFTAR_FAIL,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR " +
	    		"  ,NVL(TO_CHAR(P.TARIKH_SURAT,'dd/MM/yyyy'),'TIADA') TARIKH_SURATF " +
	    		"  ,F.ID_MASUK,F.ID_NEGERI" +
	    		//",F.TARIKH_MASUK,F.ID_KEMASKINI " +
	    		"  ,P.NO_PERMOHONAN, P.TUJUAN " +
	    		"  ,NVL(TO_CHAR(P.TARIKH_SURAT,'dd/MM/yyyy'),'TIADA') TARIKH_SURATF " +
	    		"  ,NVL(STA_.TARIKH_HANTAR,'TIADA') TARIKH_HANTARF " +
	    		"  ,NVL(TO_CHAR(TPKM.TARIKH_KEPUTUSAN,'dd/MM/yyyy'),'TIADA') TARIKH_KEPUTUSANF " +
	    		"  ,NVL(TPKM.STATUS,'0') KEPUTUSAN " +
	    		"  ,NVL(RN.NAMA_NEGERI,'TIADA') NEGERI " +
	    		"  ,S.KETERANGAN "+
	            "  ,CASE " +
	            " 	 WHEN TANAH.HAKMILIK != 0 THEN ''||TANAH.HAKMILIK||' Hakmilik/Warta' " +
	            " 	 ELSE '' " +
	            "  END BIL_TANAH " +
	            "  ,GETGISTATUSPERMOHONAN(F.ID_URUSAN)  GIS_STATUS "+
	            "  ,NVL(GIS.STATUS_NOFAIL,'N') GIS_HANTAR "+ 
	            "  ,NVL(GIS.LATITUDE,'N') GIS_CHARTING "+	            
	            " FROM " + 
	            "  TBLPERMOHONAN P,TBLHTPPERMOHONAN PP " +
	            "  ,TBLPFDFAIL F,TBLRUJSUBURUSANSTATUSFAIL SF " +
	            "  ,TBLRUJSUBURUSANSTATUS US,TBLRUJSTATUS S " + 
	            "  ,TBLRUJNEGERI RN " +	
	            "  ,TBLHTPKEPUTUSANMOHON TPKM " +
	            "  ,(SELECT RSUSF.ID_PERMOHONAN " +
	            "      ,TO_CHAR(RSUSF.TARIKH_MASUK,'dd/MM/yyyy') TARIKH_HANTAR " +
	            "        FROM TBLRUJSUBURUSANSTATUS RSUS,TBLRUJSUBURUSANSTATUSFAIL RSUSF " +
	            "      WHERE RSUS.ID_SUBURUSANSTATUS = RSUSF.ID_SUBURUSANSTATUS " +
	            "      AND RSUS.LANGKAH=10 " +
	            "   ) STA_ " +  
	            "  , (SELECT TPH.ID_PERMOHONAN, COUNT(*) HAKMILIK FROM TBLPERMOHONAN PPI, TBLHTPHAKMILIK TPH " +
	            " 	    WHERE TPH.ID_PERMOHONAN = PPI.ID_PERMOHONAN " +
	            " 	    GROUP BY TPH.ID_PERMOHONAN " +
	            " 	) TANAH " +
				",( SELECT STATUS_TANAH||NO_FAIL STATUS_NOFAIL,LATITUDE FROM TBLINTGIS  " +
				"     WHERE STATUS_TANAH IN (2,3) " +
				"     ) GIS " +        
	 			" WHERE  P.ID_FAIL = F.ID_FAIL " +
			    " AND F.ID_URUSAN IN (1,10) " +
			    " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) "+
			    " AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN "+
			    " AND P.ID_PERMOHONAN = SF.ID_PERMOHONAN  "+
			    " AND SF.ID_SUBURUSANSTATUS = US.ID_SUBURUSANSTATUS "+
			    " AND SF.ID_FAIL = F.ID_FAIL "+
			    " AND SF.AKTIF = '1' " +
			    " AND US.ID_STATUS = S.ID_STATUS " +
			    " AND F.ID_NEGERI= RN.ID_NEGERI " +
			    " AND GETGISTATUSPERMOHONAN(F.ID_URUSAN)||F.NO_FAIL = GIS.STATUS_NOFAIL(+) " +
			    " AND P.ID_PERMOHONAN = TPKM.ID_PERMOHONAN(+) " +
			    " AND P.ID_PERMOHONAN = STA_.ID_PERMOHONAN(+) " +
			    " AND PP.ID_PERMOHONAN = TANAH.ID_PERMOHONAN(+) " +
			    "";
	      
	      if (idUser != null) {
	    	  sql = sql + " AND f.id_masuk='"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + " AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + "AND f.id_urusan = "+id_urusan+" ";
	      }	      
	      if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + " AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      }
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }
	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }
	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  //by rosli 24/02/2011
	    	  //sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	    	  sql = sql + " AND P.ID_PERMOHONAN IN ( "+
	    	  	" SELECT THMUI.ID_PERMOHONAN FROM Tblhtphakmilikurusan THMUI "+
	    		" WHERE THMUI.ID_MUKIM = '"+id_mukim+"')"; 
	      }

	      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
	    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
	      }
	      
	      if (!isSearch) {
	    	  //sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      //sql = sql + "ORDER BY F.TARIKH_DAFTAR_FAIL DESC";
	      sql = sql + "ORDER BY P.ID_FAIL DESC" +
	      "" ;
	      //
	      myLog.info("TerimaPohongetList::sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String,String>>();
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("negeri")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("tarikhSurat", rs.getString("TARIKH_SURATF"));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  h.put("tarikhHantar", Utils.isNull(rs.getString("TARIKH_HANTARF")));
	    	  h.put("tarikhKeputusan", Utils.isNull(rs.getString("TARIKH_KEPUTUSANF")));
	    	  //myLog.info("negeri="+rs.getString("ID_NEGERI")+","+Integer.parseInt(rs.getString("KEPUTUSAN")));
			  //h.put("keputusan", rs.getString("KEPUTUSAN"));

	    	  if(rs.getString("ID_NEGERI").equals("12") || rs.getString("ID_NEGERI").equals("13")){
    			  h.put("keputusan", getIPermohonan().getKeputusanPermohonan(
    					  Integer.parseInt(rs.getString("KEPUTUSAN")), id_negeri)); 
	    	  }else{
	    		  h.put("keputusan"
	    				  , HTPPermohonanTanahBean.getKeputusanPermohonan(Integer.parseInt(rs.getString("KEPUTUSAN"))));
	    	  }
	    	  h.put("bilTanah", Utils.isNull(rs.getString("BIL_TANAH")));
	    	  h.put("idUrusan", Utils.isNull(rs.getString("ID_URUSAN")));
	    	  h.put("gisStatus", rs.getString("GIS_STATUS"));
	    	  h.put("gisHantar", rs.getString("GIS_HANTAR"));
	    	  h.put("gisCharting", rs.getString("GIS_CHARTING"));
	    	  list.addElement(h);
	    	  bil++;
	    	  
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	      
	    }
	    
	  }	
	
	@Override
	public  Vector<Hashtable<String, String>> TerimaPohongetList(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhBukaFail,boolean isSearch)throws Exception {
	    Db db = null;
	    String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    //boolean isSearch = false;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
            sql = "SELECT F.ID_MASUK,P.ID_FAIL, F.NO_FAIL, F.TAJUK_FAIL,F.TARIKH_DAFTAR_FAIL " +
            " ,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') TARIKH_DAFTAR,F.ID_KEMASKINI,F.TARIKH_MASUK " +
            " ,P.NO_PERMOHONAN, P.TUJUAN, " +
            " (SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = F.ID_NEGERI) NEGERI, " +
            " S.KETERANGAN " +
            " ,CASE " +
            " 	WHEN TANAH.HAKMILIK != 0 THEN ''||TANAH.HAKMILIK||' Hakmilik/Warta' " +
            " 	ELSE '' " +
            " END BIL_TANAH" +
            " FROM TBLPERMOHONAN P,TBLHTPPERMOHONAN PP, " +
            " TBLPFDFAIL F,TBLRUJSUBURUSANSTATUSFAIL SF,TBLHTPHAKMILIKURUSAN THMU, " +
            " TBLRUJSUBURUSANSTATUS US,TBLRUJSTATUS S " +
            " , (	    SELECT TPH.ID_PERMOHONAN, COUNT(*) HAKMILIK FROM TBLPERMOHONAN PPI, TBLHTPHAKMILIK TPH " +
            " 	    WHERE TPH.ID_PERMOHONAN = PPI.ID_PERMOHONAN " +
            " 	    GROUP BY TPH.ID_PERMOHONAN " +
            " 	) TANAH " +
            " WHERE P.ID_FAIL = F.ID_FAIl  " +
            " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) " +
            " AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN " +
            " AND P.ID_PERMOHONAN = SF.ID_PERMOHONAN  " +
            " AND P.ID_PERMOHONAN = THMU.ID_PERMOHONAN(+)  " +
            " AND SF.ID_SUBURUSANSTATUS = US.ID_SUBURUSANSTATUS  " +
            " AND SF.ID_FAIL = F.ID_FAIL " +
            " AND US.ID_STATUS = S.ID_STATUS  AND F.ID_URUSAN IN (1,10) AND SF.AKTIF = '1' " +
            " AND PP.ID_PERMOHONAN = TANAH.ID_PERMOHONAN(+)" +
            "";
	      
	      if (idUser != null) {
	    	  sql = sql + " AND f.id_masuk='"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + " AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  //isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + "AND f.id_urusan = "+id_urusan+" ";
	      }
	      
	      //if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + " AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      //}
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }
	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }
	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	      }

	      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
	    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
	      }
	      
	      if (!isSearch) {
	    	  sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      //sql = sql + "ORDER BY F.TARIKH_DAFTAR_FAIL DESC";
	      //sql = sql + "ORDER BY P.ID_FAIL DESC" +
	      
	      sql = sql + " ORDER BY F.TARIKH_KEMASKINI DESC,F.TARIKH_MASUK DESC"+	    	
	      "" ;

	      myLog.info("TerimaPohongetList:sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String,String>>();
	      int bil = 1;
	      Hashtable<String,String> h;

	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("negeri")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  h.put("bilTanah", Utils.isNull(rs.getString("BIL_TANAH")));
	    	  list.addElement(h);
	    	  bil++;
	      }
	      return list;
	    
	    } finally {
	      if (db != null) db.close();
	      
	    }
	  }		
	
	@Override
	public void kemaskiniMaklumatAsasTanah(Hashtable<String,String> data) throws Exception{			
		Connection conn = null;
		Db db = null;
	    String sql = "";
	    try {
	    	String idUser = (String)data.get("idUser");
	    	String idhakmilikurusan = (String)data.get("idhakmilikurusan");
	    	String idPermohonan = (String)data.get("idpermohonan");
			String id_negeri = (String)data.get("socNegeri");
			String id_daerah = (String)data.get("socDaerah");
			String id_mukim = (String)data.get("socMukim");
			String id_lot = (String)data.get("socLot");
			String infonolot = (String)data.get("txtNoLot");
			String infonosyit = (String)data.get("noSyit");
			String infoPelan = (String)data.get("noPelan");
			//String infoIdLot = (String)data.get("socLot");
			String id_luaslama = (String)data.get("socLuas");
			String Lokasi = (String)data.get("Lokasi");				
			String luas = (String)data.get("Luas");				
			String luasBersamaan = (String)data.get("LuasH");				
			//Date now = new Date();
		    //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		    //String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			//r.add("Luas",Luas);
			//r.add("Luas_bersamaan",Luas);

		    db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "UPDATE TBLHTPHAKMILIKURUSAN SET " +
				"id_negeri='"+id_negeri+"',id_daerah='"+id_daerah+"',id_mukim='"+id_mukim+"'," +
			  	"id_lot='"+id_lot+"',no_lot='"+infonolot+"',no_syit='"+infonosyit+"'," +
			  	"no_pelan='"+infoPelan+"',id_luas='"+id_luaslama+"',lokasi='"+Lokasi+"'," +
			  	"id_kemaskini='"+idUser+"',tarikh_kemaskini=sysdate "+
			  	",luas='"+luas+"',luas_bersamaan= "+luasBersamaan+""+
			  	" WHERE id_hakmilikurusan='"+idhakmilikurusan+"'";
			myLog.info(sql);
		      stmt.executeUpdate(sql);
		      
		      SQLRenderer r = new SQLRenderer();
		      String idDaerahTemp ="";
		      r.add("ID_PERMOHONAN",idPermohonan);				      
		      r.add("ID_DAERAH");
		      sql = r.getSQLSelect("TBLHTPPERMOHONAN");
		      rs = stmt.executeQuery(sql);
		      if(rs.next()){
		    	  idDaerahTemp = rs.getString("ID_DAERAH");
		    	  if(idDaerahTemp!=id_daerah){
						r = new SQLRenderer();
						r.update("ID_PERMOHONAN",idPermohonan);
						r.add("ID_DAERAH",id_daerah);
						r.add("ID_KEMASKINI", idUser);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
						sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
						stmt.executeUpdate(sql);    		  
		    	  }
		      }else{
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN",idPermohonan);
				r.add("ID_DAERAH",id_daerah);
				r.add("ID_KEMASKINI", idUser);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
				stmt.executeUpdate(sql);		    
				
		      }
		      conn.commit();
	      
		}catch (SQLException se) { 
	
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Update Hakmilik:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}	
	    
	}
	
	@Override
	public String kemaskiniMaklumatTanah(Hashtable<String,String> data) throws Exception{			
		Connection conn = null;
		Db db = null;
	    String sql = "";
	    String returnValue = "";
	    try {
	    	String idUser = (String)data.get("idUser");
	    	String idhakmilikurusan = (String)data.get("idhakmilikurusan");
	    	returnValue = idhakmilikurusan;
	    	String idPermohonan = (String)data.get("idpermohonan");
			String id_negeri = (String)data.get("socNegeri");
			String id_daerah = (String)data.get("socDaerah");
			String id_mukim = (String)data.get("socMukim");
			String id_lot = (String)data.get("socLot");
			String infonolot = (String)data.get("txtNoLot");
			String infonosyit = (String)data.get("noSyit");
			String infoPelan = (String)data.get("noPelan");
			//String infoIdLot = (String)data.get("socLot");
			String id_luaslama = (String)data.get("socLuas");
			String Lokasi = (String)data.get("Lokasi");				
			String luas = (String)data.get("Luas");				
			String luasBersamaan = (String)data.get("LuasH");				
			//Date now = new Date();
		    //SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
		    //String TBF = "to_date('" + formatter.format(now) + "','dd/MM/yyyy')";
			//r.add("Luas",Luas);
			//r.add("Luas_bersamaan",Luas);

		    db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			sql = "UPDATE TBLHTPHAKMILIKURUSAN SET " +
				"id_negeri='"+id_negeri+"',id_daerah='"+id_daerah+"',id_mukim='"+id_mukim+"'," +
			  	"id_lot='"+id_lot+"',no_lot='"+infonolot+"',no_syit='"+infonosyit+"'," +
			  	"no_pelan='"+infoPelan+"',id_luas='"+id_luaslama+"',lokasi='"+Lokasi+"'," +
			  	"id_kemaskini='"+idUser+"',tarikh_kemaskini=sysdate "+
			  	",luas='"+luas+"',luas_bersamaan= "+luasBersamaan+""+
			  	" WHERE id_hakmilikurusan='"+idhakmilikurusan+"'";
			myLog.info(sql);
		      stmt.executeUpdate(sql);
		      
		      SQLRenderer r = new SQLRenderer();
		      String idDaerahTemp ="";
		      r.add("ID_PERMOHONAN",idPermohonan);				      
		      r.add("ID_DAERAH");
		      sql = r.getSQLSelect("TBLHTPPERMOHONAN");
		      rs = stmt.executeQuery(sql);
		      if(rs.next()){
		    	  idDaerahTemp = rs.getString("ID_DAERAH");
		    	  if(idDaerahTemp!=id_daerah){
						r = new SQLRenderer();
						r.update("ID_PERMOHONAN",idPermohonan);
						r.add("ID_DAERAH",id_daerah);
						r.add("ID_KEMASKINI", idUser);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
						sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
						stmt.executeUpdate(sql);    		  
		    	  }
		      }else{
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN",idPermohonan);
				r.add("ID_DAERAH",id_daerah);
				r.add("ID_KEMASKINI", idUser);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
				stmt.executeUpdate(sql);		    
				
		      }
		      conn.commit();
	      
		}catch (SQLException se) { 
	
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Update Hakmilik:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}	
		return returnValue ;
	}
	
	@Override
	public void simpanMaklumatAsasTanah(Hashtable<String,String> data) throws Exception{	
		Connection conn = null;
		Db db = null;
	    String sql = "";
		ResultSet rs = null;
	    try{
	    	String peganganHakmilik = "";
	    	int idsubketegori = 96;
	    	int idketegori = 1;
	    	int idjenishakmilik = 0;
	    	long idPermohonan = Utils.parseLong((String)data.get("idpermohonan"));
	    	String idUser = (String)data.get("idUser");
			int idNegeri = Utils.parseInt((String)data.get("socNegeri"));
			int idDaerah = Utils.parseInt((String)data.get("socDaerah"));
			int idMukim = Utils.parseInt((String)data.get("socMukim"));
			int KodLot = Utils.parseInt((String)data.get("socLot"));
			String NoLot = (String)data.get("txtNoLot");
			String noSyit = (String)data.get("noSyit");
			String noPelan = (String)data.get("noPelan");
			if ("".equals(data.get("kodluas"))) {
				data.put("kodluas", "0");
			}
			int kodluas = Utils.parseInt((String)data.get("socLuas"));
			String Luas = (String)data.get("Luas");
			//String LuasH = (String)(data.get("LuasH"));	
			String Lokasi = (String)data.get("Lokasi");
			//int jenistanah = Utils.parseInt((String)data.get("jenistanah"));			
			//myLog.debug("simpanMaklumatAsasTanah :: " + data);
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_masuk", idUser);
			r.add("id_kemaskini",idUser);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Negeri",idNegeri);
			r.add("id_daerah",idDaerah);
			r.add("id_mukim",idMukim);
			r.add("id_lot",KodLot);
			r.add("no_lot",NoLot);
			r.add("no_syit",noSyit);
			r.add("no_pelan",noPelan);
			r.add("id_luas","2");
			r.add("Luas",Luas);
			r.add("id_luas_bersamaan",kodluas);
			r.add("Luas_bersamaan",Luas);
			r.add("Lokasi",Lokasi);
			r.add("tarikh_masuk",r.unquote("sysdate"));
			
			peganganHakmilik = FrmUtilData.getKodNegeri(String.valueOf(idNegeri));
			peganganHakmilik += FrmUtilData.getKodDaerah(String.valueOf(idDaerah));
			peganganHakmilik += FrmUtilData.getKodMukim(String.valueOf(idMukim));
			r.add("pegangan_hakmilik",peganganHakmilik);
			r.add("id_subkategori",idsubketegori);
			r.add("id_kategori",idketegori);
			r.add("id_jenishakmilik",idjenishakmilik);
			sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
			myLog.debug("Insert::TBLHTPHAKMILIKURUSAN = sql:"+sql);
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
		    String idDaerahTemp ="";
		    r.add("ID_PERMOHONAN",idPermohonan);				      
			r.add("ID_DAERAH");
		    sql = r.getSQLSelect("TBLHTPPERMOHONAN");
			myLog.debug("SELECT::TBLHTPHAKMILIKURUSAN = sql:"+sql);
		    rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	idDaerahTemp = rs.getString("ID_DAERAH");
		    	if(idDaerahTemp!=String.valueOf(idDaerah)){
		    		r = new SQLRenderer();
					r.update("ID_PERMOHONAN",idPermohonan);
					r.add("ID_DAERAH",idDaerah);
					r.add("ID_KEMASKINI", idUser);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
					sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
					myLog.debug("KEMASKINI X::TBLHTPHAKMILIKURUSAN = sql:"+sql);
					stmt.executeUpdate(sql);    		  
		    	}
		    }else{
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN",idPermohonan);
				r.add("ID_DAERAH",idDaerah);
				r.add("ID_KEMASKINI", idUser);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
				myLog.debug("KEMASKINI ELSE::TBLHTPHAKMILIKURUSAN = sql:"+sql);
				stmt.executeUpdate(sql);		    
				
		      }

			conn.commit();
		      
		}catch (SQLException se) { 
	
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Insert Hakmilik:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}
	}
	
	@Override
	public String simpanMaklumatTanah(Hashtable<String,String> data) throws Exception{	
		Connection conn = null;
		Db db = null;
	    String sql = "";
		ResultSet rs = null;
		String returnValue = "";
	    try{
	    	String peganganHakmilik = "";
	    	int idsubketegori = 96;
	    	int idkategori = 1;
	    	int idjenishakmilik = 0;
	    	long idPermohonan = Utils.parseLong((String)data.get("idpermohonan"));
	    	String idUser = (String)data.get("idUser");
			int idNegeri = Utils.parseInt((String)data.get("socNegeri"));
			int idDaerah = Utils.parseInt((String)data.get("socDaerah"));
			int idMukim = Utils.parseInt((String)data.get("socMukim"));
			int kodLot = Utils.parseInt((String)data.get("socLot"));
			String noLot = (String)data.get("txtNoLot");
			String noSyit = (String)data.get("noSyit");
			String noPelan = (String)data.get("noPelan");
			if ("".equals(data.get("kodluas"))) {
				data.put("kodluas", "0");
			}
			int kodLuas = Utils.parseInt((String)data.get("socLuas"));
			String Luas = (String)data.get("Luas");
			String LuasH = (String)(data.get("LuasH"));	
			String Lokasi = (String)data.get("Lokasi");
			//int jenistanah = Utils.parseInt((String)data.get("jenistanah"));			
			long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
			returnValue = String.valueOf(idHakmilikurusan);
			//myLog.debug("simpanMaklumatAsasTanah :: " + data);

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_hakmilikurusan", idHakmilikurusan);
			r.add("id_masuk", idUser);
			r.add("id_kemaskini",idUser);
			r.add("id_permohonan", idPermohonan);
			r.add("id_negeri",idNegeri);
			r.add("id_daerah",idDaerah);
			r.add("id_mukim",idMukim);
			r.add("id_lot",kodLot);
			r.add("no_lot",noLot);
			r.add("no_syit",noSyit);
			r.add("no_pelan",noPelan);
			r.add("id_luas",kodLuas);
			r.add("luas",Luas);
			r.add("id_luas_bersamaan","2");
			r.add("luas_bersamaan",LuasH);
			r.add("lokasi",Lokasi);
			r.add("tarikh_masuk",r.unquote("sysdate"));
			
			peganganHakmilik = FrmUtilData.getKodNegeri(String.valueOf(idNegeri));
			peganganHakmilik += FrmUtilData.getKodDaerah(String.valueOf(idDaerah));
			peganganHakmilik += FrmUtilData.getKodMukim(String.valueOf(idMukim));
			r.add("pegangan_hakmilik",peganganHakmilik);
			r.add("id_subkategori",idsubketegori);
			r.add("id_kategori",idkategori);
			r.add("id_jenishakmilik",idjenishakmilik);
			sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
			myLog.debug("Insert::TBLHTPHAKMILIKURUSAN = sql:"+sql);
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
		    String idDaerahTemp ="";
		    r.add("ID_PERMOHONAN",idPermohonan);				      
			r.add("ID_DAERAH");
		    sql = r.getSQLSelect("TBLHTPPERMOHONAN");
			myLog.debug("SELECT::TBLHTPHAKMILIKURUSAN = sql:"+sql);
		    rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	idDaerahTemp = rs.getString("ID_DAERAH");
		    	if(idDaerahTemp!=String.valueOf(idDaerah)){
		    		r = new SQLRenderer();
					r.update("ID_PERMOHONAN",idPermohonan);
					r.add("ID_DAERAH",idDaerah);
					r.add("ID_KEMASKINI", idUser);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
					sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
					myLog.debug("KEMASKINI X::TBLHTPHAKMILIKURUSAN = sql:"+sql);
					stmt.executeUpdate(sql);    		  
		    	}
		    }else{
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN",idPermohonan);
				r.add("ID_DAERAH",idDaerah);
				r.add("ID_KEMASKINI", idUser);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
				myLog.debug("KEMASKINI ELSE::TBLHTPHAKMILIKURUSAN = sql:"+sql);
				stmt.executeUpdate(sql);		    
				
		      }

			conn.commit();
		      
		}catch (SQLException se) { 
	
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Insert Hakmilik:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}
    	return returnValue ;

	}	
	
	@Override
	public Hashtable<String, String> getMaklumatAsasTanahKemaskini(String idhakmilikurusan)throws Exception {
		
		Db db = null;
		Hashtable<String, String> h = null;
		String sql = "";		
		try{
			//list = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			sql ="SELECT ID_HAKMILIKURUSAN,ID_NEGERI,ID_DAERAH,ID_MUKIM, " +
			" NO_LOT,NO_SYIT,NO_PELAN,ID_LOT,ID_LUAS,ID_LUAS_BERSAMAAN, " +
			" LUAS,TO_CHAR(LUAS_BERSAMAAN,'9,999,990.00000') LUAS_BERSAMAAN,LOKASI " +
			" FROM TBLHTPHAKMILIKURUSAN WHERE ID_HAKMILIKURUSAN = '"+idhakmilikurusan+"'";		  
			//myLog.info("getMaklumatAsasTanahKemaskini: sql::"+sql);
		  
		  ResultSet rs1 = stmt.executeQuery(sql);
		  int bil = 1;		  
		  while(rs1.next()){
			  h = new Hashtable<String, String>();
			  h.put("bil", String.valueOf(bil));
			  h.put("idhakmilikurusan", Utils.isNull(rs1.getString("ID_HAKMILIKURUSAN"))); 
			  h.put("idnegeri", Utils.isNull(rs1.getString("ID_NEGERI"))); 
			  h.put("iddaerah", Utils.isNull(rs1.getString("ID_DAERAH"))); 
			  h.put("idmukim", Utils.isNull(rs1.getString("ID_MUKIM"))); 
			  h.put("nolot", Utils.isNull(rs1.getString("NO_LOT"))); 
			  h.put("nosyit", Utils.isNull(rs1.getString("NO_SYIT"))); 
			  h.put("nopelan", Utils.isNull(rs1.getString("NO_PELAN"))); 
			  h.put("idlot", Utils.isNull(rs1.getString("ID_LOT"))); 
			  h.put("idluas", Utils.isNull(rs1.getString("ID_LUAS"))); 
			  h.put("idluasbersamaan", Utils.isNull(rs1.getString("ID_LUAS_BERSAMAAN"))); 
			  h.put("luas", Utils.isNull(rs1.getString("LUAS"))); 
			  h.put("luasLama", Utils.isNull(rs1.getString("LUAS_BERSAMAAN"))); 
			  h.put("luasH", Utils.isNull(rs1.getString("LUAS_BERSAMAAN"))); 
			  h.put("lokasi", Utils.isNull(rs1.getString("LOKASI"))); 
			  bil++;
			  //list.addElement(h);
		  }
		  return h;
			
		}finally{
			if (db != null) db.close();
		}	
		
	}
	
	@Override
	public void hapuskeputusan(String id_) {
		Db db = null;
		Connection conn = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            sql ="DELETE FROM TBLHTPKEPUTUSANMOHON WHERE ID_PERMOHONAN ="+id_;
            stmt.executeQuery(sql);
            conn.commit();
            
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
	}
	
	public void initLokasiKeputusanPermohonan(org.apache.velocity.VelocityContext context_
			,FrmSenaraiFailTerimaPohonData fData,String idPermohonan_Hakmilik, String tabmode, String readmode
			,String readOnly, String disabled, String style, String kondisi) throws Exception {	
		Vector<Hashtable<String, String>> vecHashdata = null;
		if(kondisi.equals("detail"))
			vecHashdata = fData.getPelanLakaranInfo(idPermohonan_Hakmilik);
		else if(kondisi.equals("lokasi"))
			vecHashdata = fData.getLokasiTanahInfo(idPermohonan_Hakmilik);
		else if(kondisi.equals("keputusan"))
			vecHashdata = fData.getKeputusanInfo(idPermohonan_Hakmilik);

		Hashtable<?, ?> detail = null;
		if (vecHashdata.size() > 0) {
			detail = vecHashdata.get(0);
			tabmode = "4";//Update
			context_.put("pagemode", "view");
				
		} else {
			tabmode = "3";//Insert
			context_.put("pagemode", "add");
				
		}
		/**
		 * Lokasi Permohonan = detail, Keputusan Permohonan = keputusan
		 */
		context_.put(kondisi, detail);
		if (readmode.equals("readonly")) {
			readOnly = "readonly";
			disabled = "disabled";
			style = readOnly + " class="+disabled+" ";

		} else {
			readOnly = "";
			disabled = "";
			style = "";
			
		}
				
	}
	
	public  Vector<Hashtable<String, String>> senaraiPermohonanOnline(
			String idUser,String nofail,String tajukfail
			,String id_kementerian,String id_agensi
			,String id_negeri,String id_daerah,String id_mukim
			,String id_urusan,String tarikhBukaFail)throws Exception {
	   
		Db db = null;
	    String sql = "";
	    Vector<Hashtable<String, String>> list = null;
	    boolean isSearch = false;
	    Hashtable<String,String> h;
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	sql = "" +
	    		" SELECT f.id_masuk,p.id_Fail, f.no_Fail, f.tajuk_Fail,F.TARIKH_DAFTAR_FAIL" +
	      		" ,TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR,f.id_kemaskini,F.TARIKH_MASUK  " +
	      		" ,P.NO_PERMOHONAN, P.TUJUAN, " +
	      		" (select nama_negeri from tblrujnegeri where id_negeri = f.id_negeri) negeri,"+
	      		" S.KETERANGAN "+
	            " ,CASE " +
	            " 	WHEN TANAH.HAKMILIK != 0 THEN ''||TANAH.HAKMILIK||' Hakmilik/Warta' " +
	            " 	ELSE '' " +
	            " END BIL_TANAH" +
	 	      	" FROM Tblpermohonan P,TblHTPPermohonan PP, "+
			    " Tblpfdfail F,Tblrujsuburusanstatusfail SF," +
			    //"Tblhtphakmilikurusan THMU," +
			    " Tblrujsuburusanstatus US,Tblrujstatus S "+
	            " , (	    SELECT TPH.ID_PERMOHONAN, COUNT(*) HAKMILIK FROM TBLPERMOHONAN PPI, TBLHTPHAKMILIK TPH " +
	            " 	    WHERE TPH.ID_PERMOHONAN = PPI.ID_PERMOHONAN " +
	            " 	    GROUP BY TPH.ID_PERMOHONAN " +
	            " 	) TANAH " +
	 			" WHERE P.id_Fail = F.id_Fail  " +
			    " AND ( F.ID_STATUS <> '999' OR F.ID_STATUS is null) " +
			    " AND P.id_Permohonan = PP.id_Permohonan "+
			    " AND P.id_Permohonan = SF.id_Permohonan  "+
			    //" AND P.id_Permohonan = THMU.id_Permohonan(+)  "+
			    " AND SF.id_Suburusanstatus = US.id_Suburusanstatus  "+
			    " AND SF.ID_FAIL = F.ID_FAIL "+
			    " AND US.id_Status = S.id_Status  " +
			    " AND F.id_Urusan IN (1,10)  " +
			    " AND sf.aktif = '1' " +
	            " AND PP.ID_PERMOHONAN = TANAH.ID_PERMOHONAN(+)" +
			    " AND us.LANGKAH = '-1' " +
			    "";
	      
	      if (idUser != null && !"".equals(idUser)) {
	    	  sql = sql + " AND f.id_masuk='"+idUser+"'";
	      }
	      
	      if (nofail != null && !"".equals(nofail)) {
	    	  sql = sql + " AND lower(f.no_Fail) like '%"+nofail.toLowerCase()+"%' ";
	    	  isSearch = true;
	      }
	      
	      if (id_urusan != null && !"-1".equals(id_urusan) && !"".equals(id_urusan)) {
	    	  sql = sql + "AND f.id_urusan = "+id_urusan+" ";
	      }	      
	      if (tajukfail != null && !"".equals(tajukfail)) {
	    	  sql = sql + " AND lower(f.tajuk_Fail) like '%"+tajukfail.toLowerCase()+"%' ";
	      }
	      
	      if (id_kementerian != null && !"-1".equals(id_kementerian) && !"".equals(id_kementerian)) {
	    	  sql = sql + "AND f.id_kementerian = '"+id_kementerian+"' ";
	      }
	      if (id_agensi!= null && !"-1".equals(id_agensi) && !"".equals(id_agensi)) {
	    	  sql = sql + "AND pp.id_agensi = '"+id_agensi+"' ";
	      }
	      if (id_negeri != null && !"-1".equals(id_negeri) && !"".equals(id_negeri)) {
	    	  sql = sql + "AND f.id_negeri = '"+id_negeri+"' ";
	      }
	      
	      if (id_daerah != null && !"-1".equals(id_daerah) && !"".equals(id_daerah)) {
	    	  sql = sql + "AND pp.id_daerah = '"+id_daerah+"' ";
	      }
	      
	      if (id_mukim != null && !"-1".equals(id_mukim) && !"".equals(id_mukim)) {
	    	  //by rosli 24/02/2011
	    	  //sql = sql + "AND thmu.id_mukim = '"+id_mukim+"' ";
	    	  sql = sql + " AND P.ID_PERMOHONAN IN ( "+
	    	  	" SELECT THMUI.ID_PERMOHONAN FROM Tblhtphakmilikurusan THMUI "+
	    		" WHERE THMUI.ID_MUKIM = '"+id_mukim+"')"; 
	      }

	      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
	    	  sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
	      }
	      
	      if (!isSearch) {
	    	  //sql = sql + " AND ROWNUM <= 50 ";
	      }	      
	      //sql = sql + "ORDER BY  f.id_kemaskini DESC";
	      sql = sql + "ORDER BY F.TARIKH_DAFTAR_FAIL DESC";
	      //sql = sql + "ORDER BY P.ID_FAIL DESC" +
	      //sql = sql + "ORDER BY F.TARIKH_MASUK DESC"+
	      //"" ;
	      myLog.info("senaraiPermohonanOnline::sql::: " + sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      list = new Vector<Hashtable<String,String>>();
	      int bil = 1;
	      while (rs.next()) {
	    	  h = new Hashtable<String,String>();
	    	  h.put("bil", String.valueOf(bil));
	    	  h.put("id", rs.getString("id_Fail"));
	    	  h.put("no", Utils.isNull(rs.getString("no_Fail")));
	    	  h.put("noP", Utils.isNull(rs.getString("NO_PERMOHONAN")));
	    	  h.put("negeri", Utils.isNull(rs.getString("negeri")));
	    	  h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
	    	  h.put("tujuan", Utils.isNull(rs.getString("tujuan")));
	    	  h.put("keterangan", Utils.isNull(rs.getString("keterangan")));
	    	  h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
	    	  h.put("bilTanah", Utils.isNull(rs.getString("BIL_TANAH")));
	    	  list.addElement(h);
	    	  bil++;
	    	  
	      }
	      return list;
	    
	    } finally {
			if (db != null){ 
				db.close();
			}
	    }
	  }
	private IHTPPermohonan getIPermohonan() {
		if (iPermohonan == null) {
			iPermohonan = new HTPPermohonanBean();
		}
		return iPermohonan;

	}

	
}
