package ekptg.ppk.kpi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
//import java.text.SimpleDateFormat;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmKPIUtamaModel {
	
	static Logger myLogger = Logger.getLogger(FrmKPIUtamaModel.class);
	//private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	
	@SuppressWarnings("unchecked")
	private static Vector dataSetup = null;
	@SuppressWarnings("unchecked")
	private static Vector dataKebersananKPI = null;
	@SuppressWarnings("unchecked")
	private static Vector totalTunggu = null;
	@SuppressWarnings("unchecked")
	private static Vector aktivitiDanMasa = null;
	
	@SuppressWarnings("unchecked")
	public static Vector getDataSetup(){
		return dataSetup;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getDataKebersananKPI(){
		return dataKebersananKPI;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getTotalTunggu(){
		return totalTunggu;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getAktivitiDanMasa(){
		return aktivitiDanMasa;
	}
	
	
	//Keterangan suburusan by id_suburusan
	@SuppressWarnings("unchecked")
	public static Vector getSuburusan(String id_suburusan)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		      db = new Db();
		      
		      Statement stmt = db.getStatement();
		     
		      sql =  "SELECT DISTINCT NAMA_SUBURUSAN ";
		      sql += "FROM TBLRUJSUBURUSAN ";
		      sql += "WHERE ID_SUBURUSAN = '"+id_suburusan+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector listus = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NAMA_SUBURUSAN", rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN").toUpperCase());
		    	  listus.addElement(h);
		    	  
		      }
		      return listus;
		    }finally {
		      if (db != null) db.close();
		    }
		   
	}//Keterangan suburusan by id_suburusan
	
	
	//Nama pejabat JKPTG by id_pejabatjkptg
	@SuppressWarnings("unchecked")
	public static Vector getPejabatJKPTG(String id_pejabatjkptg)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		      db = new Db();
		      
		      Statement stmt = db.getStatement();
		     
		      sql =  "SELECT DISTINCT NAMA_PEJABAT ";
		      sql += "FROM TBLRUJPEJABATJKPTG ";
		      sql += "WHERE ID_PEJABATJKPTG = '"+id_pejabatjkptg+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector listpj = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT").toUpperCase());
		    	  listpj.addElement(h);
		    	  
		      }
		      return listpj;
		    }finally {
		      if (db != null) db.close();
		    }
		   
	}//Nama pejabat JKPTG by id_pejabatjkptg
	 
	
	//Header
	@SuppressWarnings("unchecked")
	public static void setDataSetup(String id_pejabatjkptg,String id_suburusan)throws Exception {
	    
		dataSetup = new Vector();
		
		Db db = null;
		dataSetup.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  "SELECT DISTINCT A.ID_KPISASARAN, A.ID_PEJABATJKPTG, A.ID_JENISURUSAN, A.ID_SEKSYEN, ";
	    		sql += "A.F1, A.F2, A.F3, A.F4, A.F5, A.F6, A.F7, A.F8, A.F9, A.F10, A.F11, A.F12, A.F13, A.F14, A.F15, ";
	    		sql += "A.T1, A.T2, A.T3, A.T4, A.T5, A.T6, A.T7, A.T8, A.T9, A.T10, A.T11, A.T12, A.T13, A.T14, A.T15 ";
	    		sql += "FROM TBLPPKKPISASARAN A ";
	    		sql += "WHERE A.ID_PEJABATJKPTG = '"+id_pejabatjkptg+"'";
	    		sql += "AND A.ID_JENISURUSAN = '"+id_suburusan+"'";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();	    			
	    			h.put("ID_KPISASARAN", rs.getString("ID_KPISASARAN")== null?"":rs.getString("ID_KPISASARAN"));
	    			h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG")== null?"":rs.getString("ID_PEJABATJKPTG"));
	    			h.put("ID_JENISURUSAN", rs.getString("ID_JENISURUSAN")== null?"":rs.getString("ID_JENISURUSAN"));
	    			h.put("ID_SEKSYEN", rs.getString("ID_SEKSYEN")== null?"":rs.getString("ID_SEKSYEN"));
	    			h.put("F1", rs.getString("F1")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F1")));
	    			h.put("F2", rs.getString("F2")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F2")));
	    			h.put("F3", rs.getString("F3")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F3")));
	    			h.put("F4", rs.getString("F4")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F4")));
	    			h.put("F5", rs.getString("F5")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F5")));
	    			h.put("F6", rs.getString("F6")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F6")));
	    			h.put("F7", rs.getString("F7")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F7")));
	    			h.put("F8", rs.getString("F8")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F8")));
	    			h.put("F9", rs.getString("F9")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F9")));
	    			h.put("F10", rs.getString("F10")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F10")));
	    			h.put("F11", rs.getString("F11")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F11")));
	    			h.put("F12", rs.getString("F12")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F12")));
	    			h.put("F13", rs.getString("F13")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F13")));
	    			h.put("F14", rs.getString("F14")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F14")));
	    			h.put("F15", rs.getString("F15")== null?"0":Utils.formatAnyDecimal(rs.getDouble("F15")));
	    			h.put("T1", rs.getString("T1")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T1")));
	    			h.put("T2", rs.getString("T2")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T2")));
	    			h.put("T3", rs.getString("T3")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T3")));
	    			h.put("T4", rs.getString("T4")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T4")));
	    			h.put("T5", rs.getString("T5")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T5")));
	    			h.put("T6", rs.getString("T6")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T6")));
	    			h.put("T7", rs.getString("T7")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T7")));
	    			h.put("T8", rs.getString("T8")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T8")));
	    			h.put("T9", rs.getString("T9")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T9")));
	    			h.put("T10", rs.getString("T10")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T10")));
	    			h.put("T11", rs.getString("T11")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T11")));
	    			h.put("T12", rs.getString("T12")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T12")));
	    			h.put("T13", rs.getString("T13")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T13")));
	    			h.put("T14", rs.getString("T14")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T14")));
	    			h.put("T15", rs.getString("T15")== null?"0":Utils.formatAnyDecimal(rs.getDouble("T15")));
	    			dataSetup.addElement(h);		
	    	}
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataSetup
	
	//setDataKebersananKPI
	@SuppressWarnings("unchecked")
	public static void setDataKebersananKPI(String id_pejabatjkptg,String id_suburusan,String tarikhMula,String tarikhAkhir)throws Exception {
	    
		dataKebersananKPI = new Vector();
		
		Db db = null;
		dataKebersananKPI.clear();
	    String sql = "";

		String selesai = "";
		
		if(id_suburusan.equals("59")){
			selesai = "358";
		}else{
			selesai = "355";
		}
		
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  "SELECT (SELECT COUNT(B.ID_PERMOHONAN) ";
	    		sql += "FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, ";
				sql += "TBLRUJSTATUS G, TBLRUJNEGERI I, TBLRUJDAERAH J ";
				sql += "WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U WHERE U.ID_PEJABATJKPTG = '"+id_pejabatjkptg+"') ";
				sql += "AND A.ID_FAIL = B.ID_FAIL ";
				sql += "AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS ";
				sql += "AND G.ID_STATUS = F.ID_STATUS ";
				sql += "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "; 
				sql += "AND B.ID_NEGERIMHN = I.ID_NEGERI ";
				sql += "AND B.ID_DAERAHMHN = J.ID_DAERAH ";
				sql += "AND NVL(E.AKTIF,0) = 1 ";
				sql += "AND E.ID_SUBURUSANSTATUS <> '358' ";
				sql += "AND NVL(A.FLAG_JENIS_FAIL,0) IN (0,1) ";
				sql += "AND NVL(A.ID_SUBURUSAN,0) = '"+id_suburusan+"'";
				sql += "AND TO_DATE(B.TARIKH_MASUK) BETWEEN TO_DATE('"+tarikhMula+"', 'dd/mm/yyyy') AND TO_DATE('"+tarikhAkhir+"', 'dd/mm/yyyy') ";
				sql += "AND B.ID_STATUS NOT IN (999,56,155,169))AS TOTALPERMOHONAN, ";
				
				sql += "(SELECT COUNT(B.ID_PERMOHONAN) ";
				sql += "FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, ";
				sql += "TBLRUJSTATUS G, TBLRUJNEGERI I, TBLRUJDAERAH J ";
				sql += "WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U WHERE U.ID_PEJABATJKPTG = '"+id_pejabatjkptg+"') ";
				sql += "AND A.ID_FAIL = B.ID_FAIL ";
				sql += "AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS ";
				sql += "AND G.ID_STATUS = F.ID_STATUS ";
				sql += "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN ";
				sql += "AND B.ID_NEGERIMHN = I.ID_NEGERI ";
				sql += "AND B.ID_DAERAHMHN = J.ID_DAERAH ";
				sql += "AND NVL(E.ID_SUBURUSANSTATUS,0) = '358' ";
				sql += "AND NVL(A.FLAG_JENIS_FAIL,0) IN (0,1) ";
				sql += "AND NVL(A.ID_SUBURUSAN,0) = '"+id_suburusan+"'";
				sql += "AND TO_DATE(B.TARIKH_MASUK) BETWEEN TO_DATE('"+tarikhMula+"', 'dd/mm/yyyy') AND TO_DATE('"+tarikhAkhir+"', 'dd/mm/yyyy') ";
				sql += "AND B.ID_STATUS NOT IN (999,56,155,169))AS TOTALSELESAI ";
				sql += "FROM DUAL ";
	    	
				
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();	    			
	    			h.put("TOTALPERMOHONAN", rs.getString("TOTALPERMOHONAN")== null?"":rs.getString("TOTALPERMOHONAN"));
	    			h.put("TOTALSELESAI", rs.getString("TOTALSELESAI")== null?"":rs.getString("TOTALSELESAI"));
	    			dataKebersananKPI.addElement(h);		
	    	}
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataKebersananKPI
	
	//setTotalTungguJPPH
	@SuppressWarnings("unchecked")
	public static void setTotalTunggu(String id_pejabatjkptg,String id_suburusan,String tarikhMula,
									  String tarikhAkhir,String jenisProsesTunggu,String bilHari1,String bilHari2,String type,String level)throws Exception {
	    
		totalTunggu = new Vector();
		
		Db db = null;
		totalTunggu.clear();
	    String sql = "";
	    
		String selesaikolateral = "";
		
		if(id_suburusan.equals("59")){
			selesaikolateral = "776";
		}else{
			selesaikolateral = "780";
		}
	
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		if(type.equals("list")){
	    			
	    			if(jenisProsesTunggu.equals("jpph")){
	    				sql = "SELECT SMT.ID_SIMATI, B.SEKSYEN, B.ID_PERMOHONAN, A.ID_FAIL, A.NO_FAIL, G.ID_STATUS, G.KETERANGAN AS STATUS, (TO_DATE(K.TARIKH_TERIMA_NILAIAN)+1 - TO_DATE(K.TARIKH_HANTAR_NILAIAN)) AS BIL_HARI ";
	    			}
	    			
	    			if(jenisProsesTunggu.equals("borangc")){
	    				sql = "SELECT SMT.ID_SIMATI, B.SEKSYEN, B.ID_PERMOHONAN, A.ID_FAIL, A.NO_FAIL, G.ID_STATUS, G.KETERANGAN AS STATUS, (TO_DATE(K.TARIKH_TERIMA_BORANGC)+1 - TO_DATE(K.TARIKH_HANTAR_BORANGB)) AS BIL_HARI ";
	    			}
	    			
	    			if(jenisProsesTunggu.equals("kolateral")){
	    				sql = "SELECT SMT.ID_SIMATI, B.SEKSYEN, B.ID_PERMOHONAN, A.ID_FAIL, A.NO_FAIL, G.ID_STATUS, G.KETERANGAN AS STATUS, (NVL(TO_DATE(E.TARIKH_MASUK),TO_DATE(SYSDATE))+1 - TO_DATE(M.TARIKH_PERAKUAN)) AS BIL_HARI ";
	    			}
	    			
	    			if(jenisProsesTunggu.equals("mahkamahtinggi") || jenisProsesTunggu.equals("rots")){
	    				sql = "SELECT SMT.ID_SIMATI, B.SEKSYEN, B.ID_PERMOHONAN, A.ID_FAIL, A.NO_FAIL, G.ID_STATUS, G.KETERANGAN AS STATUS, (NVL(TO_DATE(M.TARIKH_TERIMA_BORANGJ),TO_DATE(SYSDATE))+1 - TO_DATE(M.TARIKH_HANTAR_BORANGJ)) AS BIL_HARI ";
	    			}
	    			
	    			
	    		}else{
	    			
	    			if(jenisProsesTunggu.equals("jpph")){
		    			sql =  "SELECT SUM((SELECT COUNT(*) FROM TBLPPKKEPUTUSANPERMOHONAN K1 WHERE K1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
		    			sql += "AND (TO_DATE(K1.TARIKH_TERIMA_NILAIAN) - TO_DATE(K1.TARIKH_HANTAR_NILAIAN))+1  <= '"+bilHari1+"' ))AS HIJAU, ";
		    			sql += "SUM((SELECT COUNT(*) FROM TBLPPKKEPUTUSANPERMOHONAN K1 WHERE K1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
		    			sql += "AND (TO_DATE(K1.TARIKH_TERIMA_NILAIAN) - TO_DATE(K1.TARIKH_HANTAR_NILAIAN))+1  BETWEEN ('"+bilHari1+"'+1) AND '"+bilHari2+"' ))AS KUNING, ";
		    			sql += "SUM((SELECT COUNT(*) FROM TBLPPKKEPUTUSANPERMOHONAN K1 WHERE K1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
		    			sql += "AND (TO_DATE(K1.TARIKH_TERIMA_NILAIAN) - TO_DATE(K1.TARIKH_HANTAR_NILAIAN))+1  >= ('"+bilHari2+"'+1)))AS MERAH ";
		    		}
		    		
		    		if(jenisProsesTunggu.equals("borangc")){
		    			sql =  "SELECT SUM((SELECT COUNT(*) FROM TBLPPKKEPUTUSANPERMOHONAN K1 WHERE K1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
		    			sql += "AND (TO_DATE(K1.TARIKH_TERIMA_BORANGC)+1 - TO_DATE(K1.TARIKH_HANTAR_BORANGB))  <= '"+bilHari1+"' ))AS HIJAU, ";
		    			sql += "SUM((SELECT COUNT(*) FROM TBLPPKKEPUTUSANPERMOHONAN K1 WHERE K1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
		    			sql += "AND (TO_DATE(K1.TARIKH_TERIMA_BORANGC)+1 - TO_DATE(K1.TARIKH_HANTAR_BORANGB))  BETWEEN ('"+bilHari1+"'+1) AND '"+bilHari2+"' ))AS KUNING, ";
		    			sql += "SUM((SELECT COUNT(*) FROM TBLPPKKEPUTUSANPERMOHONAN K1 WHERE K1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
		    			sql += "AND (TO_DATE(K1.TARIKH_TERIMA_BORANGC)+1 - TO_DATE(K1.TARIKH_HANTAR_BORANGB))  >= ('"+bilHari2+"'+1)))AS MERAH ";
		    		}
		    		
		    		if(jenisProsesTunggu.equals("kolateral")){
		    			sql =  "SELECT SUM((SELECT COUNT(*) FROM DUAL WHERE (NVL((SELECT TO_DATE(E1.TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL E1 WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
		    			sql += "AND E1.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 ";
		    			sql += "WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN AND E2.ID_SUBURUSANSTATUS = '"+selesaikolateral+"' AND E2.AKTIF = 1)),TO_DATE(SYSDATE))+1 ";
		    			sql += "- TO_DATE(M.TARIKH_PERAKUAN)) <= '"+bilHari1+"' ))AS HIJAU, ";
		    			sql += "SUM((SELECT COUNT(*) FROM DUAL WHERE (NVL((SELECT TO_DATE(E1.TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL E1 WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
		    			sql += "AND E1.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 ";
		    			sql += "WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN AND E2.ID_SUBURUSANSTATUS = '"+selesaikolateral+"' AND E2.AKTIF = 1)),TO_DATE(SYSDATE))+1 ";
		    			sql += "- TO_DATE(M.TARIKH_PERAKUAN)) BETWEEN ('"+bilHari1+"'+1) AND '"+bilHari2+"'))AS KUNING, ";
		    			sql += "SUM((SELECT COUNT(*) FROM DUAL WHERE (NVL((SELECT TO_DATE(E1.TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL E1 WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
		    			sql += "AND E1.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 ";
		    			sql += "WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN AND E2.ID_SUBURUSANSTATUS = '"+selesaikolateral+"' AND E2.AKTIF = 1)),TO_DATE(SYSDATE))+1 ";
		    			sql += "- TO_DATE(M.TARIKH_PERAKUAN)) >= ('"+bilHari2+"'+1) ))AS MERAH ";
		    		}
		    		
		    		if(jenisProsesTunggu.equals("mahkamahtinggi") || jenisProsesTunggu.equals("rots")){
		    			sql =  "SELECT SUM((SELECT COUNT(*) FROM TBLPPKBORANGJ M1 WHERE M1.ID_PERBICARAAN = L.ID_PERBICARAAN ";
		    			sql += "AND (NVL(TO_DATE(M1.TARIKH_TERIMA_BORANGJ),TO_DATE(SYSDATE))+1 - TO_DATE(M1.TARIKH_HANTAR_BORANGJ)) <= '"+bilHari1+"' ))AS HIJAU, ";
		    			sql += "SUM((SELECT COUNT(*) FROM TBLPPKBORANGJ M1 WHERE M1.ID_PERBICARAAN = L.ID_PERBICARAAN ";
		    			sql += "AND (NVL(TO_DATE(M1.TARIKH_TERIMA_BORANGJ),TO_DATE(SYSDATE))+1 - TO_DATE(M1.TARIKH_HANTAR_BORANGJ)) BETWEEN ('"+bilHari1+"'+1) AND '"+bilHari2+"' ))AS KUNING, ";
		    			sql += "SUM((SELECT COUNT(*) FROM TBLPPKBORANGJ M1 WHERE M1.ID_PERBICARAAN = L.ID_PERBICARAAN ";
		    			sql += "AND (NVL(TO_DATE(M1.TARIKH_TERIMA_BORANGJ),TO_DATE(SYSDATE))+1 - TO_DATE(M1.TARIKH_HANTAR_BORANGJ)) >= ('"+bilHari2+"'+1) ))AS MERAH ";
		    		}
		    		
	    		}
	    		
	    		
	    		sql += "FROM TBLPPKPERMOHONANSIMATI SMT, TBLPFDFAIL A, TBLPPKPERMOHONAN B, ";
	    		sql += "TBLRUJSTATUS G, TBLRUJNEGERI I, TBLRUJDAERAH J, TBLPPKKEPUTUSANPERMOHONAN K ";
	    		
	    		if(type.equals("list") && jenisProsesTunggu.equals("kolateral")){
	    			sql += ", TBLRUJSUBURUSANSTATUSFAIL E ";
	    		}
	    		
	    		if(jenisProsesTunggu.equals("kolateral")){
	    			sql += ", TBLPPKKOLATERALMST M ,TBLPPKPERBICARAAN L ";
	    		}
	    		
	    		if(jenisProsesTunggu.equals("mahkamahtinggi") || jenisProsesTunggu.equals("rots")){
	    			sql += ", TBLPPKBORANGJ M ,TBLPPKPERBICARAAN L ";
	    		}
	    		
	    		
	    		sql += "WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U WHERE U.ID_PEJABATJKPTG = '"+id_pejabatjkptg+"') ";
	    		sql += "AND TO_DATE(B.TARIKH_MASUK) BETWEEN TO_DATE('"+tarikhMula+"', 'dd/mm/yyyy') AND TO_DATE('"+tarikhAkhir+"', 'dd/mm/yyyy') ";
	    		sql += "AND SMT.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    		sql += "AND NVL(A.FLAG_JENIS_FAIL,0) IN (0,1) ";
	    		sql += "AND NVL(A.ID_SUBURUSAN,0) = '"+id_suburusan+"' ";
	    		sql += "AND B.ID_STATUS NOT IN (999,56,155,169) ";
	    		sql += "AND A.ID_FAIL = B.ID_FAIL ";
	    		sql += "AND G.ID_STATUS = B.ID_STATUS "; 
	    		sql += "AND B.ID_NEGERIMHN = I.ID_NEGERI "; 
	    		sql += "AND B.ID_DAERAHMHN = J.ID_DAERAH "; 
	    		sql += "AND K.ID_PERMOHONAN = B.ID_PERMOHONAN "; 
	    		
	    		if(jenisProsesTunggu.equals("jpph")){
	    			sql += "AND G.ID_STATUS IN (53,151) ";
	    			sql += "AND K.TARIKH_TERIMA_NILAIAN IS NOT NULL ";
	    			sql += "AND K.TARIKH_HANTAR_NILAIAN IS NOT NULL ";
	    		}
	
				if(jenisProsesTunggu.equals("borangc")){
					sql += "AND G.ID_STATUS IN (53,151) ";
					sql += "AND K.TARIKH_TERIMA_BORANGC IS NOT NULL ";
					sql += "AND K.TARIKH_HANTAR_BORANGB IS NOT NULL ";
				}
	    		
				if(jenisProsesTunggu.equals("kolateral")){
					sql += "AND G.ID_STATUS IN (172,173) ";
					sql += "AND L.ID_PERBICARAAN = (SELECT MAX(L1.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L1 WHERE L1.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) ";
					sql += "AND M.ID_PERBICARAAN = L.ID_PERBICARAAN ";
					sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
					sql += "AND M.TARIKH_PERAKUAN IS NOT NULL ";
				}
				
				if(jenisProsesTunggu.equals("mahkamahtinggi") || jenisProsesTunggu.equals("rots")){
					
					sql += "AND L.ID_PERBICARAAN = (SELECT MAX(L1.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L1 WHERE L1.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) ";
					sql += "AND M.ID_PERBICARAAN = L.ID_PERBICARAAN ";
					sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
					//sql += "AND M.TARIKH_TERIMA_BORANGJ IS NOT NULL ";
					sql += "AND M.TARIKH_HANTAR_BORANGJ IS NOT NULL ";
					
					if(jenisProsesTunggu.equals("mahkamahtinggi")){
						sql += "AND G.ID_STATUS IN (174,175) ";
						sql += "AND NVL(M.JENIS_RUJUKAN,0) = '1' ";
					}else{
						sql += "AND G.ID_STATUS IN (176,177) ";
						sql += "AND NVL(M.JENIS_RUJUKAN,0) = '2' ";
					}
					
				}
				
				if(type.equals("list")){
					
					if(jenisProsesTunggu.equals("jpph")){	
						sql += "AND (TO_DATE(K.TARIKH_TERIMA_NILAIAN)+1 - TO_DATE(K.TARIKH_HANTAR_NILAIAN)) ";
					}else if(jenisProsesTunggu.equals("borangc")){
						sql += "AND (TO_DATE(K.TARIKH_TERIMA_BORANGC)+1 - TO_DATE(K.TARIKH_HANTAR_BORANGB)) ";
					}else if(jenisProsesTunggu.equals("kolateral")){
						sql += "AND E.ID_PERMOHONAN = B.ID_PERMOHONAN ";
						sql += "AND E.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E1.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E1 ";
						sql += "WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN AND E1.ID_SUBURUSANSTATUS = '"+selesaikolateral+"' ";
						sql += "AND E1.AKTIF = 1) ";
						sql += "AND (NVL(TO_DATE(E.TARIKH_MASUK),TO_DATE(SYSDATE))+1 - TO_DATE(M.TARIKH_PERAKUAN)) ";
					}else if(jenisProsesTunggu.equals("mahkamahtinggi") || jenisProsesTunggu.equals("rots")){
						sql += "AND (NVL(TO_DATE(M.TARIKH_TERIMA_BORANGJ),TO_DATE(SYSDATE))+1 - TO_DATE(M.TARIKH_HANTAR_BORANGJ)) ";
					}
					
					if(level.equals("hijau")){
						sql += " <= '"+bilHari1+"' "; 
					}else if(level.equals("kuning")){
						sql += " BETWEEN ('"+bilHari1+"'+1) AND '"+bilHari2+"' "; 
					}else if(level.equals("merah")){
						sql += " >= ('"+bilHari2+"'+1) "; 
					}
					
				}
				
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {
	    			h = new Hashtable();	  
	    			
	    			if(type.equals("list")){
	    				h.put("bil", bil);
	    				h.put("id_simati", rs.getString("ID_SIMATI")== null?"":rs.getString("ID_SIMATI"));
	    				h.put("seksyen", rs.getString("SEKSYEN")== null?"":rs.getString("SEKSYEN"));
	    				h.put("id_status", rs.getString("ID_STATUS")== null?"":rs.getString("ID_STATUS"));
	    				h.put("id_permohonan", rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));
	    				h.put("id_fail", rs.getString("ID_FAIL")== null?"":rs.getString("ID_FAIL"));
	    				h.put("no_fail", rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
	    				h.put("status", rs.getString("STATUS")== null?"":rs.getString("STATUS"));
	    				h.put("bil_hari", rs.getString("BIL_HARI")== null?"0":rs.getString("BIL_HARI"));
	    			}else{
	    				h.put("HIJAU", rs.getString("HIJAU")== null?"0":rs.getString("HIJAU"));
		    			h.put("KUNING", rs.getString("KUNING")== null?"0":rs.getString("KUNING"));
		    			h.put("MERAH", rs.getString("MERAH")== null?"0":rs.getString("MERAH"));
	    			}
	    			totalTunggu.addElement(h);		
	    			if(type.equals("list")){bil++;}
	    	}
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setTotalTungguJPPH
	
	
	
//	//setTotalTungguJPPH BACKUP
//	@SuppressWarnings("unchecked")
//	public static void setTotalTunggu(String id_pejabatjkptg,String id_suburusan,String tarikhMula,
//										  String tarikhAkhir,String jenisProsesTunggu,String bilHari1,String bilHari2,String level)throws Exception {
//	    
//		totalTunggu = new Vector();
//		
//		Db db = null;
//		totalTunggu.clear();
//	    String sql = "";
//	    
//		String selesaikolateral = "";
//		
//		if(id_suburusan.equals("59")){
//			selesaikolateral = "776";
//		}else{
//			selesaikolateral = "780";
//		}
//		
//	    try{
//	    		db = new Db();
//	    		Statement stmt = db.getStatement();
//	      
//	    		sql =  "SELECT COUNT(B.ID_PERMOHONAN)AS TOTALTUNGGU "; 
//	    		sql += "FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, TBLRUJSTATUS G, ";
//	    		sql += "TBLRUJNEGERI I, TBLRUJDAERAH J, TBLPPKKEPUTUSANPERMOHONAN K "; 
//	    		
//	    		if(jenisProsesTunggu.equals("kolateral") || jenisProsesTunggu.equals("mahkamahtinggi")
//	    		   || jenisProsesTunggu.equals("rots")){
//	    			
//	    			if(jenisProsesTunggu.equals("kolateral")){
//	    				sql += ",TBLPPKKOLATERALMST M ";
//	    			}
//	    			else if(jenisProsesTunggu.equals("mahkamahtinggi") || jenisProsesTunggu.equals("rots")){
//	    				sql += ",TBLPPKBORANGJ M  ";
//	    			}
//	    			
//	    			sql += ",TBLPPKPERBICARAAN L ";
//	    			
//	    		}
//	    		
//	    		sql += "WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U WHERE U.ID_PEJABATJKPTG = '"+id_pejabatjkptg+"') "; 
//	    		sql += "AND NVL(A.FLAG_JENIS_FAIL,0) IN (0,1) "; 
//	    		sql += "AND NVL(A.ID_SUBURUSAN,0) = '"+id_suburusan+"' "; 
//	    		sql += "AND NVL(E.AKTIF,0) = 1 ";
//	    		sql += "AND TO_DATE(B.TARIKH_MASUK) BETWEEN TO_DATE('"+tarikhMula+"', 'dd/mm/yyyy') AND TO_DATE('"+tarikhAkhir+"', 'dd/mm/yyyy') "; 
//	    		sql += "AND B.ID_STATUS NOT IN (999,56,155,169) ";
//	    		sql += "AND K.ID_PERMOHONAN = B.ID_PERMOHONAN ";
//	    		sql += "AND A.ID_FAIL = B.ID_FAIL "; 
//				sql += "AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS "; 
//				sql += "AND G.ID_STATUS = F.ID_STATUS "; 
//				sql += "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "; 
//				sql += "AND B.ID_NEGERIMHN = I.ID_NEGERI ";  
//				sql += "AND B.ID_DAERAHMHN = J.ID_DAERAH ";  
//				
//				if(jenisProsesTunggu.equals("kolateral") || jenisProsesTunggu.equals("mahkamahtinggi")
//				   || jenisProsesTunggu.equals("rots")){
//	    			sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
//	    		}
//			
//				if(jenisProsesTunggu.equals("mahkamahtinggi") || jenisProsesTunggu.equals("rots")){
//					sql += "AND M.ID_PERBICARAAN = L.ID_PERBICARAAN ";
//					sql += "AND (SELECT TO_DATE(M1.TARIKH_TERIMA_BORANGJ) - TO_DATE(M1.TARIKH_HANTAR_BORANGJ) ";
//					sql += "FROM TBLPPKBORANGJ M1 ";
//					sql += "WHERE M1.ID_PERBICARAAN = L.ID_PERBICARAAN ";
//					sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
//					sql += "AND K.ID_PERMOHONAN = B.ID_PERMOHONAN ";
//					//sql += "AND L.ID_PERBICARAAN = (SELECT MAX(L1.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L1 WHERE L1.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) ";
//	    		}
//				
//				if(jenisProsesTunggu.equals("jpph")){					
//					sql += "AND TO_DATE(K.TARIKH_TERIMA_NILAIAN) - TO_DATE(K.TARIKH_HANTAR_NILAIAN) ";					
//				}
//				
//				if(jenisProsesTunggu.equals("borangc")){					
//					sql += "AND TO_DATE(K.TARIKH_TERIMA_BORANGC) - TO_DATE(K.TARIKH_HANTAR_BORANGB) ";
//				}
//				
//				if(jenisProsesTunggu.equals("kolateral")){
//					
//					sql += "AND M.ID_PERBICARAAN = L.ID_PERBICARAAN ";
//					sql += "AND M.ID_KOLATERALMST IS NOT NULL ";
//					sql += "AND (SELECT TO_DATE(M1.TARIKH_PERAKUAN) - TO_DATE(E1.TARIKH_MASUK) ";
//					sql += "FROM TBLRUJSUBURUSANSTATUSFAIL E1, TBLPPKKOLATERALMST M1 ";
//					sql += "WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN "; 
//					sql += "AND K.ID_PERMOHONAN= B.ID_PERMOHONAN ";
//					sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
//					sql += "AND M1.ID_PERBICARAAN = L.ID_PERBICARAAN ";
//					sql += "AND L.ID_PERBICARAAN = (SELECT MAX(L1.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L1 WHERE L1.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) "; 
//					sql += "AND E1.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E11.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E11 WHERE E11.ID_PERMOHONAN = B.ID_PERMOHONAN) "; 
//					sql += "AND E1.ID_SUBURUSANSTATUS = '"+selesaikolateral+"') ";
//
//				}
//				
//				if(jenisProsesTunggu.equals("mahkamahtinggi")){
//					sql += "AND NVL(M1.JENIS_RUJUKAN,0) = '1') ";
//				}
//				
//				if(jenisProsesTunggu.equals("rots")){				
//					sql += "AND NVL(M1.JENIS_RUJUKAN,0) = '2') ";	
//				}
//				
//				if(level.equals("hijau")){
//					sql += " <= '"+bilHari1+"' "; 
//				}else if(level.equals("kuning")){
//					sql += " BETWEEN ('"+bilHari1+"'+1) AND '"+bilHari2+"' "; 
//				}else if(level.equals("merah")){
//					sql += " >= ('"+bilHari2+"'+1) "; 
//				}
//				
//				if(jenisProsesTunggu.equals("rots")){
//					System.out.println("sql : "+sql);
//				}
//				
//	    		ResultSet rs = stmt.executeQuery(sql);
//	     
//	    		Hashtable h;
//
//	    		while (rs.next()) {
//	    			h = new Hashtable();	    			
//	    			h.put("TOTALTUNGGU", rs.getString("TOTALTUNGGU")== null?"0":rs.getString("TOTALTUNGGU"));
//	    			totalTunggu.addElement(h);		
//	    	}
//	     
//	    } finally {
//	      if (db != null) db.close();
//	    }
//	    
//	}//close setTotalTungguJPPH
	
	@SuppressWarnings("unchecked")
	public static void simpanSetupKPI(Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_kpisasaran = (String)data.get("id_kpisasaran");
	    		String id_suburusan = (String)data.get("socSuburusan");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		
	    		if(id_kpisasaran!=""){
	    			r.update("id_kpisasaran",id_kpisasaran);
	    			r.update("id_jenisurusan",id_suburusan);
	    			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",(String)data.get("id_user"));	
	    		}else{
	    			r.add("id_seksyen","2");
	    			r.add("id_jenisurusan",id_suburusan);
	    			r.add("id_pejabatjkptg",(String)data.get("socUnit"));
	    			r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",(String)data.get("id_user"));	
	    		}
	    		
	    		r.add("F1",(String)data.get("txtF1"));
	    		r.add("F2",(String)data.get("txtF2"));
	    		r.add("F3",(String)data.get("txtF3"));
	    		r.add("F4",(String)data.get("txtF4"));
	    		r.add("F5",(String)data.get("txtF5"));
	    		r.add("F6",(String)data.get("txtF6"));
	    		r.add("F7",(String)data.get("txtF7"));
	    		r.add("F8",(String)data.get("txtF8"));
	    		r.add("F9",(String)data.get("txtF9"));
	    		r.add("F10",(String)data.get("txtF10"));
	    		r.add("F11",(String)data.get("txtF11"));
	    		r.add("F12",(String)data.get("txtF12"));
	    		r.add("F13",(String)data.get("txtF13"));
	    		r.add("F14",(String)data.get("txtF14"));
	    		r.add("F15",(String)data.get("txtF15"));
	    		
	    		r.add("T1",(String)data.get("txtT1"));
	    		r.add("T2",(String)data.get("txtT2"));
	    		r.add("T3",(String)data.get("txtT3"));
	    		r.add("T4",(String)data.get("txtT4"));
	    		r.add("T5",(String)data.get("txtT5"));
	    		r.add("T6",(String)data.get("txtT6"));
	    		r.add("T7",(String)data.get("txtT7"));
	    		r.add("T8",(String)data.get("txtT8"));
	    		r.add("T9",(String)data.get("txtT9"));
	    		r.add("T10",(String)data.get("txtT10"));
	    		r.add("T11",(String)data.get("txtT11"));
	    		r.add("T12",(String)data.get("txtT12"));
	
	    		if(id_kpisasaran!=""){
	    			sql = r.getSQLUpdate("TBLPPKKPISASARAN");
	    		}else{
	    			sql = r.getSQLInsert("TBLPPKKPISASARAN");
	    		}
	    		
	    		stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanSetupKPI
	
	@SuppressWarnings("unchecked")
	public static void setAktivitiDanMasa(String id_pejabatjkptg,String id_suburusan,String tarikhMula,String tarikhAkhir,String jenisAktiviti)throws Exception {
	    
		aktivitiDanMasa = new Vector();
		
		Db db = null;
		aktivitiDanMasa.clear();
	    String sql = "";
	    
	    String tungguKeputusanKPTG = "";
		String selesaikolateral = "";
		String notisBicara = "";
		String selesai = "";
		String keputusanPermohonan = "";
		String tangguhKolateral = "";
		String tangguhMT = "";
		String selesaiROTS = "";
		String tangguhROTS = "";
		String prmhnSelesai = "";
		String pendaftaran = "";
		String pindahBKE = "";
		
		if(id_suburusan.equals("59")){
			tungguKeputusanKPTG = "455";
			selesaikolateral = "776";
			notisBicara = "354";
			selesai = "358";
			keputusanPermohonan = "353";
			tangguhKolateral = "775";
			tangguhMT = "777";
			selesaiROTS = "814";
			tangguhROTS = "813";
			prmhnSelesai = "359";
			pendaftaran = "340";
			pindahBKE = "408";
		}else{
			tungguKeputusanKPTG = "856";
			selesaikolateral = "780";
			notisBicara = "341";
			selesai = "355";
			keputusanPermohonan = "434";
			tangguhKolateral = "779";
			tangguhMT = "781";
			selesaiROTS = "854";
			tangguhROTS = "853";
			prmhnSelesai = "399";
			pendaftaran = "430";
			pindahBKE = "304";
		}
		
		
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		if(jenisAktiviti.equals("borangA")){
	    			sql =  "SELECT SUM(NVL((SELECT TO_DATE(E2.TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL E2 WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    			sql += "AND E2.ID_SUBURUSANSTATUSFAIL = (SELECT MIN(E3.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E3 ";
	    			sql += "WHERE E3.ID_PERMOHONAN = B.ID_PERMOHONAN AND E3.ID_SUBURUSANSTATUS = '"+keputusanPermohonan+"' )),TO_DATE(SYSDATE))+1 ";
	    			sql += "- TO_DATE(E.TARIKH_MASUK)) AS JUMLAH_HARI, ";
	    		}
	    		else if(jenisAktiviti.equals("borangC")){
	    			sql = "SELECT SUM(TO_DATE(K.TARIKH_MASUK)+1 - TO_DATE(K.TARIKH_TERIMA_BORANGC))AS JUMLAH_HARI, ";		
	    		}else if(jenisAktiviti.equals("nilaiJPPH")){
	    			sql = "SELECT SUM(TO_DATE(K.TARIKH_MASUK)+1 - TO_DATE(K.TARIKH_TERIMA_NILAIAN))AS JUMLAH_HARI, ";			
	    		}else if(jenisAktiviti.equals("sediaNotis")){	
	    			sql = "SELECT SUM(TO_DATE(N.TARIKH_SERAHAN)+1 - TO_DATE(L.TARIKH_NOTIS))AS JUMLAH_HARI,  ";
	    		}else if(jenisAktiviti.equals("bicaraPertama")){
	    			sql = "SELECT SUM(NVL(TO_DATE(PTH.TARIKH_MASUK),TO_DATE(SYSDATE))+1 - TO_DATE(L.TARIKH_BICARA))AS JUMLAH_HARI, ";
	    		}else if(jenisAktiviti.equals("mohonBKE")){
	    			sql =  "SELECT SUM(NVL((SELECT TO_DATE(E1.TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL E1 "; 
	    			sql += "WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN AND E1.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) ";
					sql += "FROM TBLRUJSUBURUSANSTATUSFAIL E2 WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN AND E2.ID_SUBURUSANSTATUS = '455' )),TO_DATE(SYSDATE))+1 ";
					sql += "- TO_DATE(BKE.TARIKH_MOHON))AS JUMLAH_HARI, ";
	    		}else if(jenisAktiviti.equals("borangQ")){
	    			sql =  "SELECT SUM(NVL(TO_DATE(BKE.TARIKH_LULUS),TO_DATE(SYSDATE))+1 - (SELECT TO_DATE(E1.TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL E1 WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    			sql += "AND E1.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    			sql += "AND E2.ID_SUBURUSANSTATUS = '"+tungguKeputusanKPTG+"' ))) "; 
	    			sql += "AS JUMLAH_HARI, ";
	    		}else if(jenisAktiviti.equals("borangL")){
	    			sql =  "SELECT SUM(NVL((SELECT TO_DATE(E1.TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL E1 WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    			sql += "AND E1.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E3.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E3 WHERE E3.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    			sql += "AND E3.ID_SUBURUSANSTATUS = '"+selesaikolateral+"' )),TO_DATE(SYSDATE))+1 ";
	    			sql += "- TO_DATE(M.TARIKH_PERAKUAN))AS JUMLAH_HARI, ";
	    		}else if(jenisAktiviti.equals("borangN")){
	    			sql = "SELECT SUM(NVL((SELECT TO_DATE(E1.TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL E1 WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    			sql += "AND E1.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E3.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E3 WHERE E3.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    			sql += "AND E3.ID_SUBURUSANSTATUS = '"+notisBicara+"' )),TO_DATE(SYSDATE))+1 ";
	    			sql += "- TO_DATE(E.TARIKH_MASUK)) JUMLAH_HARI, ";
	    		}else if(jenisAktiviti.equals("borangJ_MT")){
	    			sql =  "SELECT SUM(NVL(TO_DATE(BJ.TARIKH_HANTAR_BORANGJ),TO_DATE(SYSDATE))+1 - TO_DATE(E.TARIKH_MASUK))AS JUMLAH_HARI, ";
	    		}else if(jenisAktiviti.equals("keputusanMT") || jenisAktiviti.equals("keputusanROTS")){
	    			sql =  "SELECT SUM(NVL((SELECT TO_DATE(L2.TARIKH_NOTIS) FROM TBLPPKPERBICARAAN L2 ";
	    			sql += "WHERE L2.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
	    			sql += "AND L2.ID_PERBICARAAN = (SELECT MAX(L3.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L3 WHERE L3.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) ";
	    			sql += "AND TO_DATE(L2.TARIKH_NOTIS) > TO_DATE(BJ.TARIKH_TERIMA_BORANGJ)),TO_DATE(SYSDATE))+1 ";
	    			sql += "- TO_DATE(BJ.TARIKH_TERIMA_BORANGJ))AS JUMLAH_HARI, ";
	    		}else if(jenisAktiviti.equals("borangJ_ROTS")){
	    			sql =  "SELECT SUM(NVL(TO_DATE(BJ.TARIKH_HANTAR_BORANGJ),TO_DATE(SYSDATE))+1 - TO_DATE(E.TARIKH_MASUK))AS JUMLAH_HARI, ";
	    		}else if(jenisAktiviti.equals("sediaPerintah")){
	    			sql =  "SELECT SUM(NVL((SELECT TO_DATE(E1.TARIKH_MASUK) FROM TBLRUJSUBURUSANSTATUSFAIL E1 WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    			sql += "AND E1.ID_SUBURUSANSTATUS = '358' ),TO_DATE(SYSDATE))+1 ";
	    			sql += "- TO_DATE(E.TARIKH_MASUK)) JUMLAH_HARI, ";
	    		}
	    
	    		sql += "COUNT(*)AS JUMLAH_AKTIVITI ";
	    		sql += "FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B ";
	    		
	    		
	    		if(!jenisAktiviti.equals("sediaNotis")){
	    			sql += ", TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F ";
	    		}
	    		
	    		if(jenisAktiviti.equals("sediaNotis")){
	    			sql += ", TBLPPKPERBICARAAN L, TBLPPKNOTISPERBICARAAN M, TBLPPKNOTISOBMST N ";
	    		}
	    		
	    		if(jenisAktiviti.equals("bicaraPertama")){
	    			sql += ", TBLPPKPERBICARAAN L, TBLPPKPERINTAH PTH ";
	    		}
	    		
	    		if(!jenisAktiviti.equals("borangA") && !jenisAktiviti.equals("mohonBKE") && !jenisAktiviti.equals("borangQ")){
	    			sql += ", TBLPPKKEPUTUSANPERMOHONAN K ";
	    		}
	    		
	    		if(jenisAktiviti.equals("mohonBKE") || jenisAktiviti.equals("borangQ")){
	    			sql += ", TBLPPKBKE BKE ";
	    		}
	    		
	    		if(jenisAktiviti.equals("borangL") || jenisAktiviti.equals("borangN")){
	    			sql += ", TBLPPKPERBICARAAN L, TBLPPKKOLATERALMST M ";
	    		}
	    			
	    		if(jenisAktiviti.equals("borangJ_MT") || jenisAktiviti.equals("keputusanMT") || jenisAktiviti.equals("borangJ_ROTS")
	    		|| jenisAktiviti.equals("keputusanROTS")){
	    			sql += ", TBLPPKPERBICARAAN L, TBLPPKBORANGJ BJ ";
	    		}
	    		
	    		if(jenisAktiviti.equals("sediaPerintah")){
	    			sql += ", TBLPPKPERBICARAAN L, TBLPPKPERINTAH PTH ";
	    		}
	    		
	    		sql += ", TBLRUJSTATUS G, TBLRUJNEGERI I, TBLRUJDAERAH J ";
	    		sql += "WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U WHERE U.ID_PEJABATJKPTG = '"+id_pejabatjkptg+"') "; 
	    		sql += "AND TO_DATE(B.TARIKH_MASUK) BETWEEN TO_DATE('"+tarikhMula+"', 'dd/mm/yyyy') AND TO_DATE('"+tarikhAkhir+"', 'dd/mm/yyyy') ";
	    		sql += "AND A.ID_FAIL = B.ID_FAIL "; 
	    		
	    		if(!jenisAktiviti.equals("borangA") && !jenisAktiviti.equals("mohonBKE") && !jenisAktiviti.equals("borangQ")){
	    			sql += "AND K.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    		}
	    		
	    		if(!jenisAktiviti.equals("sediaNotis")){
	    			sql += "AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS ";  
	    			sql += "AND G.ID_STATUS = F.ID_STATUS "; 
	    			sql += "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "; 
	    		}
	    
				if(jenisAktiviti.equals("borangC") || jenisAktiviti.equals("nilaiJPPH")){

					sql += "AND E.ID_SUBURUSANSTATUS = '"+keputusanPermohonan+"' ";
					
					if(jenisAktiviti.equals("borangC")){
						sql += "AND K.TARIKH_TERIMA_BORANGC IS NOT NULL ";
					}else if(jenisAktiviti.equals("nilaiJPPH")){
						sql += "AND K.TARIKH_TERIMA_NILAIAN IS NOT NULL ";
					}
				}
				
				if(jenisAktiviti.equals("borangA")){
					sql += "AND E.ID_SUBURUSANSTATUSFAIL = (SELECT MIN(E1.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E1 ";
					sql += "WHERE E1.ID_PERMOHONAN = B.ID_PERMOHONAN AND E1.ID_SUBURUSANSTATUS = '"+pendaftaran+"' ) ";
				}
				
				if(jenisAktiviti.equals("sediaNotis")){
					sql += "AND M.ID_NOTISOBMST = N.ID_NOTISOBMST ";
					sql += "AND M.ID_PERBICARAAN = L.ID_PERBICARAAN ";
					sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
					sql += "AND B.ID_STATUS = G.ID_STATUS ";
					sql += "AND L.ID_PERBICARAAN = (SELECT MAX(L1.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L1 WHERE L1.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) ";
					sql += "AND M.ID_NOTISPERBICARAAN = (SELECT MAX(M1.ID_NOTISPERBICARAAN) FROM TBLPPKNOTISPERBICARAAN M1 WHERE M1.ID_PERBICARAAN = L.ID_PERBICARAAN) ";
					sql += "AND L.TARIKH_NOTIS IS NOT NULL ";
					sql += "AND N.TARIKH_SERAHAN IS NOT NULL ";
				}
			
				if(jenisAktiviti.equals("bicaraPertama")){		
					sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
					sql += "AND L.ID_PERBICARAAN = (SELECT MIN(L1.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L1 WHERE L1.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) ";
					sql += "AND L.TARIKH_BICARA IS NOT NULL ";
					sql += "AND E.ID_SUBURUSANSTATUSFAIL = (SELECT MIN(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN ";
					sql += "AND E2.ID_SUBURUSANSTATUS = '"+notisBicara+"' ) ";
					sql += "AND PTH.ID_PERBICARAAN = L.ID_PERBICARAAN ";
					sql += "AND PTH.ID_PERINTAH = (SELECT MIN(PTH1.ID_PERINTAH) FROM TBLPPKPERINTAH PTH1 WHERE PTH1.ID_PERBICARAAN = L.ID_PERBICARAAN) ";
				}
				
				if(jenisAktiviti.equals("mohonBKE") || jenisAktiviti.equals("borangQ")){
					sql += "AND BKE.ID_PERMOHONAN = B.ID_PERMOHONAN ";
					sql += "AND E.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 ";
					sql += "WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN AND E2.ID_SUBURUSANSTATUS = '"+pindahBKE+"' ) ";
				}
				
				if(jenisAktiviti.equals("borangL") || jenisAktiviti.equals("borangN")){
					sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
					//sql += "AND L.ID_PERBICARAAN = (SELECT MAX(L1.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L1 WHERE L1.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) ";
					sql += "AND M.ID_PERBICARAAN = L.ID_PERBICARAAN ";
					sql += "AND E.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN ";
					if(jenisAktiviti.equals("borangL")){
						sql += "AND E2.ID_SUBURUSANSTATUS = '"+tangguhKolateral+"' ) ";
					}else{
						sql += "AND E2.ID_SUBURUSANSTATUS = '"+selesaikolateral+"' ) ";
					}
					
				}
				
				
				if(jenisAktiviti.equals("borangJ_MT") || jenisAktiviti.equals("keputusanMT") || jenisAktiviti.equals("borangJ_ROTS") 
				|| jenisAktiviti.equals("keputusanROTS")){
					
					sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
					sql += "AND BJ.ID_PERBICARAAN = L.ID_PERBICARAAN ";
					
					if(jenisAktiviti.equals("borangJ_MT")){
						//sql += "AND L.ID_PERBICARAAN = (SELECT MAX(L1.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L1 WHERE L1.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) ";
						sql += "AND E.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN ";
						sql += "AND E2.ID_SUBURUSANSTATUS = '"+tangguhMT+"' ) ";
					}else if(jenisAktiviti.equals("keputusanMT") || jenisAktiviti.equals("keputusanROTS")){
						sql += "AND L.ID_PERBICARAAN = (SELECT MAX(BJ1.ID_PERBICARAAN) FROM TBLPPKBORANGJ BJ1 WHERE BJ1.ID_PERBICARAAN = L.ID_PERBICARAAN) ";
						sql += "AND E.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN ";
						sql += "AND E2.ID_SUBURUSANSTATUS = '"+selesaiROTS+"' ) ";
					}else if(jenisAktiviti.equals("borangJ_ROTS")){
						sql += "AND E.ID_SUBURUSANSTATUSFAIL = (SELECT MAX(E2.ID_SUBURUSANSTATUSFAIL) FROM TBLRUJSUBURUSANSTATUSFAIL E2 WHERE E2.ID_PERMOHONAN = B.ID_PERMOHONAN ";
						sql += "AND E2.ID_SUBURUSANSTATUS = '"+tangguhROTS+"' ) ";
					}
				}
				
				sql += "AND B.ID_NEGERIMHN = I.ID_NEGERI ";  
				sql += "AND B.ID_DAERAHMHN = J.ID_DAERAH ";   
				sql += "AND NVL(A.FLAG_JENIS_FAIL,0) IN (0,1) ";  
				sql += "AND NVL(A.ID_SUBURUSAN,0) = '"+id_suburusan+"' ";
				
				
				if(!jenisAktiviti.equals("mohonBKE") && !jenisAktiviti.equals("borangQ")){
					sql += "AND B.ID_STATUS NOT IN (999,56,155,169) "; 
				}else{
					sql += "AND B.ID_STATUS NOT IN (999) "; 
				}
				
				if(jenisAktiviti.equals("sediaPerintah")){
					sql += "AND L.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN ";
					sql += "AND L.ID_PERBICARAAN = (SELECT MAX(L1.ID_PERBICARAAN) FROM TBLPPKPERBICARAAN L1 WHERE L1.ID_KEPUTUSANPERMOHONAN = K.ID_KEPUTUSANPERMOHONAN) ";
					sql += "AND PTH.ID_PERBICARAAN = L.ID_PERBICARAAN ";
					sql += "AND E.ID_SUBURUSANSTATUS = '359' ";
				}

				
				if(jenisAktiviti.equals("borangQ")){
					sql += "AND BKE.TARIKH_LULUS IS NOT NULL ";
				}
				
	    		ResultSet rs = stmt.executeQuery(sql);
	   	     
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("jumlah_hari", rs.getDouble("JUMLAH_HARI")== 0?0:rs.getDouble("JUMLAH_HARI"));
	    			h.put("jumlah_aktiviti", rs.getInt("JUMLAH_AKTIVITI")== 0?0:rs.getInt("JUMLAH_AKTIVITI"));
	    			aktivitiDanMasa.addElement(h);		
	    	}
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setAktivitiDanMasa
	
}//close class
