package  ekptg.model.php2.kpi;


import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.EkptgCache;

public class KPIData_PHP extends EkptgCache implements
		Serializable {

	static Logger myLogger = Logger.getLogger(KPIData_PHP.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	public static final String SEQ_TABLE = "TBLRUJSEQFAIL";
	DecimalFormat fourDForm = new DecimalFormat("#.##");
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String cd = dateFormat.format(date);


	
	
	Vector list_KPI_MENUNGGU = null;	
	public Vector list_KPI_MENUNGGU(String id_status,String id_suburusan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		list_KPI_MENUNGGU = new Vector();
			Db db = null;
			list_KPI_MENUNGGU.clear();
			String sql =  " SELECT DISTINCT PP.ID_PERMOHONAN, "+
			" MAX ((SELECT SUM((NVL((SELECT TO_DATE (TO_CHAR (SYSDATE, "+
			" 'DD/MM/YYYY'),'DD/MM/YYYY') "+
			" + 1 "+
			" - TO_DATE(TO_CHAR((SELECT MIN(TO_DATE(TO_CHAR(SHM.TARIKH_MASUK, "+
			" 'DD/MM/YYYY'),'DD/MM/YYYY')) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL SHM, "+
			" TBLRUJSTATUS ST,TBLPERMOHONAN PP,TBLPFDFAIL F, "+
			" TBLRUJSUBURUSANSTATUS SST "+
			" WHERE SHM.ID_FAIL = F.ID_FAIL "+
			" AND PP.TARIKH_TERIMA IS NOT NULL "+
			" AND SHM.TARIKH_MASUK BETWEEN TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"','DD/MM/YYYY') "+
			" AND ST.ID_STATUS = '"+id_status+"' /*MULA*/ "+
			" AND F.ID_FAIL = FF.ID_FAIL "+
			" AND SHM.AKTIF = '1' "+
			" AND PP.ID_FAIL = F.ID_FAIL "+
			" AND SHM.ID_SUBURUSANSTATUS = SST.ID_SUBURUSANSTATUS "+
			" AND SST.ID_STATUS = ST.ID_STATUS "+
			" AND SHM.ID_SUBURUSANSTATUS = '"+id_suburusan+"'  "+
			" AND FF.ID_SEKSYEN = 4 "+
			" ),'DD/MM/YYYY'),'DD/MM/YYYY') "+
			" FROM DUAL),0))) AS D1 "+
			" FROM DUAL) "+
			" ) AS HARI_TUNGGU, "+
			" FF.ID_NEGERI, FF.ID_FAIL, FF.NO_FAIL,PP.ID_STATUS, "+
			" FF.ID_SUBURUSAN, FF.FLAG_JENIS_FAIL, "+
			" TO_CHAR (PP.TARIKH_TERIMA,'DD.MM.YYYY') AS TARIKH_TERIMA "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL SHM,TBLPERMOHONAN PP, "+
			" TBLPFDFAIL FF,TBLRUJSTATUS ST,TBLRUJSUBURUSANSTATUS SST "+
			" WHERE SHM.ID_FAIL = FF.ID_FAIL "+
			" AND PP.TARIKH_TERIMA BETWEEN TO_DATE ('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE ('"+txdTarikhAkhir+"','DD/MM/YYYY') "+
			" AND PP.ID_FAIL = FF.ID_FAIL "+
			" AND SHM.ID_SUBURUSANSTATUS = SST.ID_SUBURUSANSTATUS "+
			" AND SST.ID_STATUS = ST.ID_STATUS " +
			" AND ST.ID_STATUS IN ('"+id_status+"') /*MULA*/ "+
			" AND SHM.ID_SUBURUSANSTATUS = '"+id_suburusan+"'  "+
			" AND FF.ID_SEKSYEN = '4' "+
			" GROUP BY PP.ID_PERMOHONAN, "+
			" FF.ID_NEGERI,FF.NO_FAIL, "+
			" PP.ID_STATUS,FF.ID_FAIL,PP.ID_PERMOHONAN, "+
			" FF.ID_SUBURUSAN,FF.FLAG_JENIS_FAIL,PP.TARIKH_TERIMA ";
			myLogger.info("list_KPI_MENUNGGU :"+sql.toUpperCase());
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("ID_NEGERI",
							rs.getString("ID_NEGERI") == null ? "" : rs
									.getString("ID_NEGERI"));
					h.put("HARI_TUNGGU",
							rs.getString("HARI_TUNGGU") == null ? 0 : rs
									.getInt("HARI_TUNGGU"));
					h.put("ID_PERMOHONAN",
							rs.getString("ID_PERMOHONAN") == null ? "" : rs
									.getString("ID_PERMOHONAN"));
					h.put("NO_FAIL",
							rs.getString("NO_FAIL") == null ? "" : rs
									.getString("NO_FAIL"));	
					h.put("ID_STATUS",
							rs.getString("ID_STATUS") == null ? "" : rs
									.getString("ID_STATUS"));	
					h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));				
					h.put("FLAG_JENIS_FAIL", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));				
					h.put("TARIKH_TERIMA", rs.getString("TARIKH_TERIMA") == null ? "" : rs.getString("TARIKH_TERIMA"));
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
					
					list_KPI_MENUNGGU.addElement(h);				
				}
				return list_KPI_MENUNGGU;
			} finally {
				if (db != null)
					db.close();
			}
		}
	
	
	

	public void addSasaran(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		
	
		String SS1 = (String) data.get("SS1");
		String SS2 = (String) data.get("SS2");
		String SS3 = (String) data.get("SS3");
		String SS4 = (String) data.get("SS4");
		String SS5 = (String) data.get("SS5");		
		String SS6 = (String) data.get("SS6");
		String SS7 = (String) data.get("SS7");
		String SS8 = (String) data.get("SS8");
		String SS9 = (String) data.get("SS9");
		String SS10 = (String) data.get("SS10");
		
		
		String SX1 = (String) data.get("SX1");
		String SX2 = (String) data.get("SX2");
		String SX3 = (String) data.get("SX3");
		String SX4 = (String) data.get("SX4");
		String SX5 = (String) data.get("SX5");
		String SX6 = (String) data.get("SX6");
		String SX7 = (String) data.get("SX7");
		String SX8 = (String) data.get("SX8");		
		String SX9 = (String) data.get("SX9");
		String SX10 = (String) data.get("SX10");
		String SX11 = (String) data.get("SX11");
		String SX12 = (String) data.get("SX12");
		
		
		String id_suburusan = (String) data.get("id_suburusan");
		String id_urusan = (String) data.get("id_urusan");
		String id_seksyen = (String) data.get("id_seksyen");
		String id_user = (String) data.get("id_user");	
		long id_kpisasaran = DB.getNextID("TBLPHPKPISASARAN_SEQ");
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_KPISASARAN", id_kpisasaran);
			r.add("ID_URUSAN", id_urusan);
			r.add("ID_SUBURUSAN", id_suburusan);
			r.add("ID_SEKSYEN", id_seksyen);
			
			r.add("F1", SS1);
			r.add("F2", SS2);
			r.add("F3", SS3);
			r.add("F4", SS4);
			r.add("F5", SS5);
			r.add("F6", SS6);
			r.add("F7", SS7);
			r.add("F8", SS8);
			r.add("F9", SS9);
			r.add("F10", SS10);

			r.add("F11", SX1);
			r.add("F12", SX2);
			r.add("F13", SX3);
			r.add("F14", SX4);
			r.add("F15", SX5);
			r.add("F16", SX6);
			r.add("F17", SX7);
			r.add("F18", SX8);			
			r.add("F19", SX9);
			r.add("F20", SX10);
			r.add("F21", SX11);
			r.add("F22", SX12);
			
			
			r.add("ID_MASUK", id_user);
			r.add("ID_KEMASKINI", id_user);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPHPKPISASARAN");
			myLogger.info("SQL INSERT SASARAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	public void updateSasaran(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		
	
		String SS1 = (String) data.get("SS1");
		String SS2 = (String) data.get("SS2");
		String SS3 = (String) data.get("SS3");
		String SS4 = (String) data.get("SS4");
		String SS5 = (String) data.get("SS5");		
		String SS6 = (String) data.get("SS6");
		String SS7 = (String) data.get("SS7");
		String SS8 = (String) data.get("SS8");
		String SS9 = (String) data.get("SS9");
		String SS10 = (String) data.get("SS10");
		
		
		String SX1 = (String) data.get("SX1");
		String SX2 = (String) data.get("SX2");
		String SX3 = (String) data.get("SX3");
		String SX4 = (String) data.get("SX4");
		String SX5 = (String) data.get("SX5");
		String SX6 = (String) data.get("SX6");
		String SX7 = (String) data.get("SX7");
		String SX8 = (String) data.get("SX8");		
		String SX9 = (String) data.get("SX9");
		String SX10 = (String) data.get("SX10");
		String SX11 = (String) data.get("SX11");
		String SX12 = (String) data.get("SX12");
		
		
		String id_suburusan = (String) data.get("id_suburusan");
		String id_urusan = (String) data.get("id_urusan");
		String id_seksyen = (String) data.get("id_seksyen");
		String id_user = (String) data.get("id_user");	
		String id_kpisasaran = (String) data.get("id_kpisasaran");	

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_KPISASARAN", id_kpisasaran);
			r.add("ID_URUSAN", id_urusan);
			r.add("ID_SUBURUSAN", id_suburusan);
			r.add("ID_SEKSYEN", id_seksyen);
			
			r.add("F1", SS1);
			r.add("F2", SS2);
			r.add("F3", SS3);
			r.add("F4", SS4);
			r.add("F5", SS5);
			r.add("F6", SS6);
			r.add("F7", SS7);
			r.add("F8", SS8);
			r.add("F9", SS9);
			r.add("F10", SS10);

			r.add("F11", SX1);
			r.add("F12", SX2);
			r.add("F13", SX3);
			r.add("F14", SX4);
			r.add("F15", SX5);
			r.add("F16", SX6);
			r.add("F17", SX7);
			r.add("F18", SX8);			
			r.add("F19", SX9);
			r.add("F20", SX10);
			r.add("F21", SX11);
			r.add("F22", SX12);
			
			
			r.add("ID_MASUK", id_user);
			r.add("ID_KEMASKINI", id_user);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPHPKPISASARAN");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
		
	
	
	
    Vector senarai_kpisasaran = null;	
	public Vector senarai_kpisasaran(String id_urusan,String id_suburusan,String id_seksyen)
		 throws Exception {
		senarai_kpisasaran = new Vector();
	Db db = null;
	senarai_kpisasaran.clear();
	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();
		sql = "SELECT ID_KPISASARAN,ID_NEGERI,ID_PEJABATJKPTG,ID_URUSAN,ID_SUBURUSAN,ID_SEKSYEN,TO_CHAR(F1,'990.99') AS F1,TO_CHAR(F2,'990.99') AS F2,TO_CHAR(F3,'990.99') AS F3,TO_CHAR(F4,'990.99') AS F4,TO_CHAR(F5,'990.99') AS F5, "
			+" TO_CHAR(F6,'990.99') AS F6,TO_CHAR(F7,'990.99') AS F7,TO_CHAR(F8,'990.99') AS F8,TO_CHAR(F9,'990.99') AS F9,TO_CHAR(F10,'990.99') AS F10,TO_CHAR(F11,'990.99') AS F11,TO_CHAR(F12,'990.99') AS F12," +
					"TO_CHAR(F13,'990.99') AS F13,TO_CHAR(F14,'990.99') AS F14,TO_CHAR(F15,'990.99') AS F15,TO_CHAR(F16,'990.99') AS F16,TO_CHAR(F17,'990.99') AS F17,TO_CHAR(F18,'990.99') AS F18,TO_CHAR(F19,'990.99') AS F19," +
					"TO_CHAR(F20,'990.99') AS F20,TO_CHAR(F21,'990.99') AS F21,TO_CHAR(F22,'990.99') AS F22,TO_CHAR(F23,'990.99') AS F23,TO_CHAR(F24,'990.99') AS F24,TO_CHAR(F25,'990.99') AS F25," +
					"TO_CHAR(F26,'990.99') AS F26,TO_CHAR(F27,'990.99') AS F27,TO_CHAR(F28,'990.99') AS F28,TO_CHAR(F29,'990.99') AS F29,TO_CHAR(F30,'990.99') AS F30"
			+" FROM TBLPHPKPISASARAN WHERE ID_SEKSYEN = '"+id_seksyen+"'  AND ID_URUSAN = '"+id_urusan+"' ";
					
		
		  sql += "AND ID_SUBURUSAN = '"+id_suburusan+"'";
			

		myLogger.info("SENARAI KPISASARAN :"+ sql);

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		int bil = 0;
		while (rs.next()) {
			bil = bil + 1;
			h = new Hashtable();
			h.put("BIL", bil);			
			h.put("ID_KPISASARAN", rs.getString("ID_KPISASARAN") == null ? ""
					: rs.getString("ID_KPISASARAN"));
			h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? ""
					: rs.getString("ID_NEGERI"));
			h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG") == null ? ""
					: rs.getString("ID_PEJABATJKPTG"));
			h.put("ID_URUSAN", rs.getString("ID_URUSAN") == null ? ""
					: rs.getString("ID_URUSAN"));
			h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
					: rs.getString("ID_SUBURUSAN"));
			h.put("ID_SEKSYEN", rs.getString("ID_SEKSYEN") == null ? ""
					: rs.getString("ID_SEKSYEN"));			
			h.put("F1", rs.getString("F1") == null ? 0
					: rs.getDouble("F1"));
			h.put("F2", rs.getString("F2") == null ? 0
					: rs.getDouble("F2"));
			h.put("F3", rs.getString("F3") == null ? 0
					: rs.getDouble("F3"));
			h.put("F4", rs.getString("F4") == null ? 0
					: rs.getDouble("F4"));
			h.put("F5", rs.getString("F5") == null ? 0
					: rs.getDouble("F5"));
			h.put("F6", rs.getString("F6") == null ? 0
					: rs.getDouble("F6"));
			h.put("F7", rs.getString("F7") == null ? 0
					: rs.getDouble("F7"));
			h.put("F8", rs.getString("F8") == null ? 0
					: rs.getDouble("F8"));
			h.put("F9", rs.getString("F9") == null ? 0
					: rs.getDouble("F9"));
			h.put("F10", rs.getString("F10") == null ? 0
					: rs.getDouble("F10"));			
			h.put("F11", rs.getString("F11") == null ? 0
					: rs.getDouble("F11"));
			h.put("F12", rs.getString("F12") == null ? 0
					: rs.getDouble("F12"));
			h.put("F13", rs.getString("F13") == null ? 0
					: rs.getDouble("F13"));
			h.put("F14", rs.getString("F14") == null ? 0
					: rs.getDouble("F14"));
			h.put("F15", rs.getString("F15") == null ? 0
					: rs.getDouble("F15"));
			h.put("F16", rs.getString("F16") == null ? 0
					: rs.getDouble("F16"));
			h.put("F17", rs.getString("F17") == null ? 0
					: rs.getDouble("F17"));
			h.put("F18", rs.getString("F18") == null ? 0
					: rs.getDouble("F18"));
			h.put("F19", rs.getString("F19") == null ? 0
					: rs.getDouble("F19"));
			h.put("F20", rs.getString("F20") == null ? 0
					: rs.getDouble("F20"));			
			h.put("F21", rs.getString("F21") == null ? 0
					: rs.getDouble("F21"));
			h.put("F22", rs.getString("F22") == null ? 0
					: rs.getDouble("F22"));
			h.put("F23", rs.getString("F23") == null ? 0
					: rs.getDouble("F23"));
			h.put("F24", rs.getString("F24") == null ? 0
					: rs.getDouble("F24"));
			h.put("F25", rs.getString("F25") == null ? 0
					: rs.getDouble("F25"));
			h.put("F26", rs.getString("F26") == null ? 0
					: rs.getDouble("F26"));
			h.put("F27", rs.getString("F27") == null ? 0
					: rs.getDouble("F27"));
			h.put("F28", rs.getString("F28") == null ? 0
					: rs.getDouble("F28"));
			h.put("F29", rs.getString("F29") == null ? 0
					: rs.getDouble("F29"));
			h.put("F30", rs.getString("F30") == null ? 0
					: rs.getDouble("F30"));
		
			
			
			senarai_kpisasaran.addElement(h);
		}
		return senarai_kpisasaran;
	} finally {
		if (db != null)
			db.close();
	}
	}
		
	
	
	Vector list_urusan_php = null;
	public Vector list_urusan_php(String role) throws Exception {
		list_urusan_php = new Vector();
		Db db = null;
		list_urusan_php.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

		//	sql = "SELECT ID_SUBURUSAN,KOD_SUBURUSAN,NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SEKSYEN = 4";
		sql = "  SELECT DISTINCT U.ID_URUSAN,U.KOD_URUSAN,U.NAMA_URUSAN "+
				" FROM TBLRUJSUBURUSAN SU,TBLRUJURUSAN U "+
				" WHERE SU.ID_URUSAN = U.ID_URUSAN "+
				" AND SU.ID_SEKSYEN = 4	";
		
		     if(role.equals("(PHP)UserPelepasan"))
		     {
		    	 sql += "AND U.ID_URUSAN = '6' "; 
		    	
		     }
		     else if(role.equals("(PHP)UserPenguatkuasaan"))
		     {
		    	// sql += "AND U.ID_URUSAN = '8' "; 
		    	 sql += "AND U.ID_URUSAN NOT IN (9,7,12,13,6,8) "; 
		     }
		     else if(role.equals("(PHP)UserPenyewaan"))
		     {
		    	// sql += "AND U.ID_URUSAN = '7'  "; 
		    	 sql += "AND U.ID_URUSAN NOT IN (9,12,13,6,8) "; 
		     }
		     else if(role.equals("(PHP)UserHasil"))
		     {
		    	// sql += "AND U.ID_URUSAN = '12' "; 
		    	 sql += "AND U.ID_URUSAN NOT IN (9,7,12,13,6,8) "; 
		     }
		     else if(role.equals("(PHP)UserAPB"))
		     {
		    	 sql += "AND U.ID_URUSAN = '9' "; 
		     }
		     else 
		     {
		    	 sql += "AND U.ID_URUSAN NOT IN (12,13,8) "; 
		     }
		
		
			myLogger.info("SQL URUSAN PHP:"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_URUSAN", rs.getString("ID_URUSAN") == null ? ""
						: rs.getString("ID_URUSAN"));
				h.put("KOD_URUSAN",
						rs.getString("KOD_URUSAN") == null ? "" : rs
								.getString("KOD_URUSAN").toUpperCase());
				h.put("NAMA_URUSAN",
						rs.getString("NAMA_URUSAN") == null ? "" : rs
								.getString("NAMA_URUSAN").toUpperCase());
				list_urusan_php.addElement(h);
			}
			return list_urusan_php;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector list_suburusan_php = null;
	public Vector list_suburusan_php(String id_urusan) throws Exception {
		list_suburusan_php = new Vector();
		Db db = null;
		list_suburusan_php.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

		/*	sql = "SELECT D.ID_DAERAH,D.KOD_DAERAH,D.NAMA_DAERAH"+					
					" FROM TBLRUJPEJABATJKPTG P,TBLRUJDAERAH D "+
					" WHERE P.ID_SEKSYEN = '1' AND P.ID_NEGERI = D.ID_NEGERI AND  P.ID_JENISPEJABAT IN ('22','21')";					
				if(!id_pejabat.equals("16"))	
				{
			sql +=		" AND P.ID_PEJABATJKPTG = '"+id_pejabat+"'";
				}
			
		    sql+= " AND D.KOD_DAERAH <> '0' ORDER BY P.ID_NEGERI,D.KOD_DAERAH ASC";*/
			
			sql = "SELECT DISTINCT SU.ID_SUBURUSAN,SU.KOD_SUBURUSAN,SU.NAMA_SUBURUSAN "+
						" FROM TBLRUJSUBURUSAN SU,TBLRUJURUSAN U  "+
						" WHERE SU.ID_URUSAN = U.ID_URUSAN "+
						" AND SU.ID_SEKSYEN = 4 "+
						" AND SU.ID_URUSAN = '"+id_urusan+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("KOD_SUBURUSAN",
						rs.getString("KOD_SUBURUSAN") == null ? "" : rs
								.getString("KOD_SUBURUSAN").toUpperCase());
				h.put("NAMA_SUBURUSAN",
						rs.getString("NAMA_SUBURUSAN") == null ? "" : rs
								.getString("NAMA_SUBURUSAN").toUpperCase());
				list_suburusan_php.addElement(h);
			}
			return list_suburusan_php;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector beanMaklumatKPIPenawaran = null;	
	public void setMaklumatKPIPenawaran(String idKPISasaran, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		String listFail = "''";
	

		try {
			beanMaklumatKPIPenawaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = " SELECT DISTINCT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL  AND TBLPFDFAIL.ID_SEKSYEN = 4 " 
				 + " AND TBLPFDFAIL.ID_SUBURUSAN = '32' AND TBLPFDFAIL.FLAG_JENIS_FAIL NOT IN (2,3)" 
				 + " AND TBLPERMOHONAN.TARIKH_TERIMA BETWEEN TO_DATE('" + txdTarikhMula + "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "','DD/MM/YYYY')"
                  +" ";
			ResultSet rsFail = stmt.executeQuery(sql);
			
			while (rsFail.next()){
				if ("''".equals(listFail)){
					listFail = rsFail.getString("ID_FAIL");
				} else {
					listFail = listFail + "," + rsFail.getString("ID_FAIL");
				}
			}
			
			
			sql = " SELECT "+
			" (SELECT COUNT (DISTINCT (TBLPFDFAIL.ID_FAIL)) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + "))   "+
			" AS DITERIMA, "+
			
			" (SELECT COUNT(DISTINCT TBLPFDFAIL.ID_FAIL) "+
			" FROM TBLPFDFAIL,TBLPERMOHONAN, "+
			" TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS, "+
			" TBLRUJSUBURUSAN,TBLRUJSTATUS "+
			" WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "+
			" AND TBLPFDFAIL.ID_FAIL = TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = TBLRUJSTATUS.ID_STATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS IN (1610207,1610208) "+
			" AND TBLPFDFAIL.ID_FAIL IN (" + listFail + ") "+
			" ) AS DISELESAI, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610198') AS C1, "+
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610200') AS C2, "+
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610201') AS C3, "+
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610206') AS C4, "+
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D1, "+
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610210 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610200 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610200 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D2, "+
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610201 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610201 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D3, "+
			" (SELECT "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610207) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610208) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D4 "+
			" FROM DUAL ";

				

			
			myLogger.info("SQL PENAWARAN ::"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
								
				h = new Hashtable();
				h.put("DITERIMA",
						rs.getString("DITERIMA") == null ? 0 : rs
								.getString("DITERIMA"));
				
				h.put("DISELESAI",
						rs.getString("DISELESAI") == null ? 0 : rs
								.getString("DISELESAI"));
				
				h.put("C1",
						rs.getString("C1") == null ? 0 : rs
								.getString("C1"));
				h.put("C2",
						rs.getString("C2") == null ? 0 : rs
								.getString("C2"));
				h.put("C3",
						rs.getString("C3") == null ? 0 : rs
								.getString("C3"));
				h.put("C4",
						rs.getString("C4") == null ? 0 : rs
								.getString("C4"));
				
				h.put("D1",
						rs.getString("D1") == null ? 0 : rs
								.getString("D1"));
				h.put("D2",
						rs.getString("D2") == null ? 0 : rs
								.getString("D2"));
				h.put("D3",
						rs.getString("D3") == null ? 0 : rs
								.getString("D3"));
				h.put("D4",
						rs.getString("D4") == null ? 0 : rs
								.getString("D4"));					
				
				beanMaklumatKPIPenawaran.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatKPIPenawaran() {
		return beanMaklumatKPIPenawaran;
	}
	
	
	
	Vector beanMaklumatKPITukarguna = null;	
	public void setMaklumatKPITukarguna(String idKPISasaran, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		String listFail = "''";
	

		try {
			beanMaklumatKPITukarguna = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = " SELECT DISTINCT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL  AND TBLPFDFAIL.ID_SEKSYEN = 4 " 
				 + " AND TBLPFDFAIL.ID_SUBURUSAN = '33' AND TBLPFDFAIL.FLAG_JENIS_FAIL NOT IN (2,3)" 
				 + " AND TBLPERMOHONAN.TARIKH_TERIMA BETWEEN TO_DATE('" + txdTarikhMula + "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "','DD/MM/YYYY')"
                  +" ";
			ResultSet rsFail = stmt.executeQuery(sql);
			
			while (rsFail.next()){
				if ("''".equals(listFail)){
					listFail = rsFail.getString("ID_FAIL");
				} else {
					listFail = listFail + "," + rsFail.getString("ID_FAIL");
				}
			}
			
			
			sql = " SELECT "+
			" (SELECT COUNT (DISTINCT (TBLPFDFAIL.ID_FAIL)) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + "))   "+
			" AS DITERIMA, "+
			
			" (SELECT COUNT(DISTINCT TBLPFDFAIL.ID_FAIL) "+
			" FROM TBLPFDFAIL,TBLPERMOHONAN, "+
			" TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS, "+
			" TBLRUJSUBURUSAN,TBLRUJSTATUS "+
			" WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "+
			" AND TBLPFDFAIL.ID_FAIL = TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = TBLRUJSTATUS.ID_STATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS IN (1610207,1610208) "+
			" AND TBLPFDFAIL.ID_FAIL IN (" + listFail + ") "+
			" ) AS DISELESAI, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610198') AS C1, "+
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610200') AS C2, "+
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610201') AS C3, "+
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610206') AS C4, "+
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D1, "+
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610210 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610200 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610200 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D2, "+
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610201 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610201 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D3, "+
			" (SELECT "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610207) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610208) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D4 "+
			" FROM DUAL ";

				

			
			myLogger.info("SQL Tukarguna ::"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
								
				h = new Hashtable();
				h.put("DITERIMA",
						rs.getString("DITERIMA") == null ? 0 : rs
								.getString("DITERIMA"));
				
				h.put("DISELESAI",
						rs.getString("DISELESAI") == null ? 0 : rs
								.getString("DISELESAI"));
				
				h.put("C1",
						rs.getString("C1") == null ? 0 : rs
								.getString("C1"));
				h.put("C2",
						rs.getString("C2") == null ? 0 : rs
								.getString("C2"));
				h.put("C3",
						rs.getString("C3") == null ? 0 : rs
								.getString("C3"));
				h.put("C4",
						rs.getString("C4") == null ? 0 : rs
								.getString("C4"));
				
				h.put("D1",
						rs.getString("D1") == null ? 0 : rs
								.getString("D1"));
				h.put("D2",
						rs.getString("D2") == null ? 0 : rs
								.getString("D2"));
				h.put("D3",
						rs.getString("D3") == null ? 0 : rs
								.getString("D3"));
				h.put("D4",
						rs.getString("D4") == null ? 0 : rs
								.getString("D4"));					
				
				beanMaklumatKPITukarguna.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatKPITukarguna() {
		return beanMaklumatKPITukarguna;
	}
	
	
	
	
	Vector beanMaklumatKPIPelepasan = null;	
	public void setMaklumatKPIPelepasan(String idKPISasaran, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		String listFail = "''";
		

		try {
			beanMaklumatKPIPelepasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = " SELECT DISTINCT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL  AND TBLPFDFAIL.ID_SEKSYEN = 4 " 
				 + " AND TBLPFDFAIL.ID_SUBURUSAN = '34' AND TBLPFDFAIL.FLAG_JENIS_FAIL NOT IN (2,3)" 
				 + " AND TBLPERMOHONAN.TARIKH_TERIMA BETWEEN TO_DATE('" + txdTarikhMula + "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "','DD/MM/YYYY')"
                  +" ";
			ResultSet rsFail = stmt.executeQuery(sql);
			
			while (rsFail.next()){
				if ("''".equals(listFail)){
					listFail = rsFail.getString("ID_FAIL");
				} else {
					listFail = listFail + "," + rsFail.getString("ID_FAIL");
				}
			}
			
			
			sql = " SELECT "+
			" (SELECT COUNT (DISTINCT (TBLPFDFAIL.ID_FAIL)) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + "))   "+
			" AS DITERIMA, "+
			
			" (SELECT COUNT(DISTINCT TBLPFDFAIL.ID_FAIL) "+
			" FROM TBLPFDFAIL,TBLPERMOHONAN, "+
			" TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS, "+
			" TBLRUJSUBURUSAN,TBLRUJSTATUS "+
			" WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "+
			" AND TBLPFDFAIL.ID_FAIL = TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = TBLRUJSTATUS.ID_STATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS IN (1610207,1610208) "+
			" AND TBLPFDFAIL.ID_FAIL IN (" + listFail + ") "+
			" ) AS DISELESAI, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610198') AS C1, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610200') AS C2, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610204') AS C3, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610205') AS C4, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610206') AS C5, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D1, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610202 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610200 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610200 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D2, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610203 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610204 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610204 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D3, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610205 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610205 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D4, "+
			
			" (SELECT "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610207) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610208) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D5 "+
			
			
			" FROM DUAL ";

				

			
			myLogger.info("SQL PELEPASAN ::"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
								
				h = new Hashtable();
				h.put("DITERIMA",
						rs.getString("DITERIMA") == null ? 0 : rs
								.getString("DITERIMA"));
				
				h.put("DISELESAI",
						rs.getString("DISELESAI") == null ? 0 : rs
								.getString("DISELESAI"));
				
				h.put("C1",
						rs.getString("C1") == null ? 0 : rs
								.getString("C1"));
				h.put("C2",
						rs.getString("C2") == null ? 0 : rs
								.getString("C2"));
				h.put("C3",
						rs.getString("C3") == null ? 0 : rs
								.getString("C3"));
				h.put("C4",
						rs.getString("C4") == null ? 0 : rs
								.getString("C4"));
				h.put("C5",
						rs.getString("C5") == null ? 0 : rs
								.getString("C5"));
				
				h.put("D1",
						rs.getString("D1") == null ? 0 : rs
								.getString("D1"));
				h.put("D2",
						rs.getString("D2") == null ? 0 : rs
								.getString("D2"));
				h.put("D3",
						rs.getString("D3") == null ? 0 : rs
								.getString("D3"));
				h.put("D4",
						rs.getString("D4") == null ? 0 : rs
								.getString("D4"));	
				h.put("D5",
						rs.getString("D5") == null ? 0 : rs
								.getString("D5"));	
				
				beanMaklumatKPIPelepasan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatKPIPelepasan() {
		return beanMaklumatKPIPelepasan;
	}
	
	
	Vector beanMaklumatKPIPenyewaan = null;	
	public void setMaklumatKPIPenyewaan(String idKPISasaran, String txdTarikhMula, String txdTarikhAkhir,String id_urusan,String id_suburusan) throws Exception {
		Db db = null;
		String sql = "";
		String listFail = "''";
		

		try {
			beanMaklumatKPIPenyewaan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = " SELECT DISTINCT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL  AND TBLPFDFAIL.ID_SEKSYEN = 4 " 
				 + " AND TBLPFDFAIL.ID_SUBURUSAN = '"+id_suburusan+"' AND TBLPFDFAIL.ID_URUSAN = '"+id_urusan+"' AND TBLPFDFAIL.FLAG_JENIS_FAIL NOT IN (2,3)" 
				 + " AND TBLPERMOHONAN.TARIKH_TERIMA BETWEEN TO_DATE('" + txdTarikhMula + "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "','DD/MM/YYYY')"
                  +" ";
			ResultSet rsFail = stmt.executeQuery(sql);
			
			while (rsFail.next()){
				if ("''".equals(listFail)){
					listFail = rsFail.getString("ID_FAIL");
				} else {
					listFail = listFail + "," + rsFail.getString("ID_FAIL");
				}
			}
			
			
			sql = " SELECT "+
			" (SELECT COUNT (DISTINCT (TBLPFDFAIL.ID_FAIL)) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + "))   "+
			" AS DITERIMA, "+
			
			" (SELECT COUNT(DISTINCT TBLPFDFAIL.ID_FAIL) "+
			" FROM TBLPFDFAIL,TBLPERMOHONAN, "+
			" TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS, "+
			" TBLRUJSUBURUSAN,TBLRUJSTATUS "+
			" WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "+
			" AND TBLPFDFAIL.ID_FAIL = TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = TBLRUJSTATUS.ID_STATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS IN (1610195,1610208) "+
			" AND TBLPFDFAIL.ID_FAIL IN (" + listFail + ") "+
			" ) AS DISELESAI, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610198') AS C1, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610199') AS C2, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610213') AS C3, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610206') AS C4, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610214') AS C5, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D1, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610213 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D2, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610201 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610213 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610213 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D3, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610214 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D4, "+
			
			" (SELECT "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610208) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610214 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610195) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610214 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610214 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D5 "+
			
			
			" FROM DUAL ";

				

			
			myLogger.info("SQL PENYEWAAN ::"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
								
				h = new Hashtable();
				h.put("DITERIMA",
						rs.getString("DITERIMA") == null ? 0 : rs
								.getString("DITERIMA"));
				
				h.put("DISELESAI",
						rs.getString("DISELESAI") == null ? 0 : rs
								.getString("DISELESAI"));
				
				h.put("C1",
						rs.getString("C1") == null ? 0 : rs
								.getString("C1"));
				h.put("C2",
						rs.getString("C2") == null ? 0 : rs
								.getString("C2"));
				h.put("C3",
						rs.getString("C3") == null ? 0 : rs
								.getString("C3"));
				h.put("C4",
						rs.getString("C4") == null ? 0 : rs
								.getString("C4"));
				h.put("C5",
						rs.getString("C5") == null ? 0 : rs
								.getString("C5"));
				
				h.put("D1",
						rs.getString("D1") == null ? 0 : rs
								.getString("D1"));
				h.put("D2",
						rs.getString("D2") == null ? 0 : rs
								.getString("D2"));
				h.put("D3",
						rs.getString("D3") == null ? 0 : rs
								.getString("D3"));
				h.put("D4",
						rs.getString("D4") == null ? 0 : rs
								.getString("D4"));	
				h.put("D5",
						rs.getString("D5") == null ? 0 : rs
								.getString("D5"));	
				
				beanMaklumatKPIPenyewaan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatKPIPenyewaan() {
		return beanMaklumatKPIPenyewaan;
	}

	
	
	Vector beanMaklumatKPIAPB = null;	
	public void setMaklumatKPIAPB(String idKPISasaran, String txdTarikhMula, String txdTarikhAkhir,String id_urusan,String id_suburusan) throws Exception {
		Db db = null;
		String sql = "";
		String listFail = "''";
		

		try {
			beanMaklumatKPIAPB = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = " SELECT DISTINCT(TBLPFDFAIL.ID_FAIL) FROM TBLPFDFAIL, TBLPERMOHONAN WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL  AND TBLPFDFAIL.ID_SEKSYEN = 4 " 
				 + " AND TBLPFDFAIL.ID_SUBURUSAN = '"+id_suburusan+"' AND TBLPFDFAIL.ID_URUSAN = '"+id_urusan+"' AND TBLPFDFAIL.FLAG_JENIS_FAIL NOT IN (2,3)" 
				 + " AND TBLPERMOHONAN.TARIKH_TERIMA BETWEEN TO_DATE('" + txdTarikhMula + "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "','DD/MM/YYYY')"
                  +" ";
			ResultSet rsFail = stmt.executeQuery(sql);
			
			while (rsFail.next()){
				if ("''".equals(listFail)){
					listFail = rsFail.getString("ID_FAIL");
				} else {
					listFail = listFail + "," + rsFail.getString("ID_FAIL");
				}
			}
			
			
			sql = " SELECT "+
			" (SELECT COUNT (DISTINCT (TBLPFDFAIL.ID_FAIL)) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + "))   "+
			" AS DITERIMA, "+
			
			" (SELECT COUNT(DISTINCT TBLPFDFAIL.ID_FAIL) "+
			" FROM TBLPFDFAIL,TBLPERMOHONAN, "+
			" TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS, "+
			" TBLRUJSUBURUSAN,TBLRUJSTATUS "+
			" WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "+
			" AND TBLPFDFAIL.ID_FAIL = TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_SUBURUSAN = TBLRUJSUBURUSAN.ID_SUBURUSAN "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = TBLRUJSTATUS.ID_STATUS "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS IN (1610207,1610208) "+
			" AND TBLPFDFAIL.ID_FAIL IN (" + listFail + ") "+
			" ) AS DISELESAI, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610198') AS C1, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610199') AS C2, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610201') AS C3, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610205') AS C4, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610236') AS C5, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610237') AS C6, "+
			
			" (SELECT COUNT (DISTINCT (TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL)) "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL, TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = "+
			" TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL IN (" + listFail + ") "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = '1610239') AS C7, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610198 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D1, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610235 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610199 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D2, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610213 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610201 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610201 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D3, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610206 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610205 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610205 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D4, "+
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610237 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610236 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610236 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D5, "+			
			
			" (SELECT   SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610238 /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610237 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610237 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D6, "+
			
			" (SELECT "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610208) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610239 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM(NVL(((SELECT TO_DATE(TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND (TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610207) /*FINISH*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF IN (0, 1))+ 1 "+
			" - (SELECT TO_DATE "+
			" (TO_CHAR(TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610239 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 0)),'0')) "+
			" + "+
			" SUM (NVL(((SELECT TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YY'),'DD/MM/YY') "+
			" FROM DUAL)+ 1 - (SELECT TO_DATE (TO_CHAR "+
			" (TBLRUJSUBURUSANSTATUSFAIL.TARIKH_MASUK,'DD/MM/YY'),'DD/MM/YY') "+
			" FROM TBLRUJSUBURUSANSTATUSFAIL,TBLRUJSUBURUSANSTATUS "+
			" WHERE TBLRUJSUBURUSANSTATUSFAIL.ID_SUBURUSANSTATUS = TBLRUJSUBURUSANSTATUS.ID_SUBURUSANSTATUS "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.ID_FAIL = TBLPFDFAIL.ID_FAIL "+
			" AND TBLRUJSUBURUSANSTATUS.ID_STATUS = 1610239 /*START*/ "+
			" AND TBLRUJSUBURUSANSTATUSFAIL.AKTIF = 1)),'0')) "+
			" FROM TBLPFDFAIL "+
			" WHERE TBLPFDFAIL.ID_FAIL IN (" + listFail + ")) AS D7 "+
			
			
			" FROM DUAL ";

				

			
			myLogger.info("SQL APB ::"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
								
				h = new Hashtable();
				h.put("DITERIMA",
						rs.getString("DITERIMA") == null ? 0 : rs
								.getString("DITERIMA"));
				
				h.put("DISELESAI",
						rs.getString("DISELESAI") == null ? 0 : rs
								.getString("DISELESAI"));
				
				h.put("C1",
						rs.getString("C1") == null ? 0 : rs
								.getString("C1"));
				h.put("C2",
						rs.getString("C2") == null ? 0 : rs
								.getString("C2"));
				h.put("C3",
						rs.getString("C3") == null ? 0 : rs
								.getString("C3"));
				h.put("C4",
						rs.getString("C4") == null ? 0 : rs
								.getString("C4"));
				h.put("C5",
						rs.getString("C5") == null ? 0 : rs
								.getString("C5"));
				
				h.put("D1",
						rs.getString("D1") == null ? 0 : rs
								.getString("D1"));
				h.put("D2",
						rs.getString("D2") == null ? 0 : rs
								.getString("D2"));
				h.put("D3",
						rs.getString("D3") == null ? 0 : rs
								.getString("D3"));
				h.put("D4",
						rs.getString("D4") == null ? 0 : rs
								.getString("D4"));	
				h.put("D5",
						rs.getString("D5") == null ? 0 : rs
								.getString("D5"));	
				
				beanMaklumatKPIAPB.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatKPIAPB() {
		return beanMaklumatKPIAPB;
	}

	
	Vector list_status = null;
	public Vector list_status() throws Exception {
		list_status = new Vector();
		Db db = null;
		list_status.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_STATUS,KOD_STATUS,KETERANGAN FROM TBLRUJSTATUS " +
					"WHERE ID_SEKSYEN = 4 " +
					" ORDER BY KOD_STATUS ASC";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				h.put("KOD_STATUS", rs.getString("KOD_STATUS") == null ? ""
						: rs.getString("KOD_STATUS").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				list_status.addElement(h);
			}
			return list_status;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	}
	
	
	
	
