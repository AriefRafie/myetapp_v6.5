package ekptg.model.htp.utiliti;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;


public class HTPSusulanBean implements IHTPSusulan{
	
 	private IHtp iHTP = null;  
	private final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	private Hashtable<String,String> h = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.HTPSusulanBean.class);
	String sql = "";
	Db db = null;
	Connection conn = null;
//	PfdFail fail = null;
//	Permohonan permohonan = null;
//	HtpPermohonan htpPermohonan = null;

	@Override
	// TAMBAH MAKLUMAT  
	public String simpan(Hashtable<String,String> data) throws Exception {
		Date date = new Date(); 
		String currentDate = SDF.format(date);
		
		String tarikh = "to_date('"+ currentDate + "','dd/MM/yyyy')";
	    if (!("".equals((String)data.get("txdTarikh")))) {
	    	tarikh = "to_date('" + data.get("txdTarikh") + "','dd/MM/yyyy')";
	    }

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//String idPermohonan,String idStatusFail,
		    long id = DB.getNextID("TBLHTPSUSULAN_SEQ"); 
		    r.add("ID_SUSULAN",id);
		    r.add("ID_PERMOHONAN",data.get("idPermohonan"));
		    r.add("TARIKH",r.unquote(tarikh));
		    r.add("KETERANGAN",data.get("catatan"));
		    r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));	
	    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));  
		    sql = r.getSQLInsert("TBLHTPSUSULAN");
		    myLog.info("sql insert baru :sql="+sql);	    	    
			stmt.executeUpdate(sql);
			
			conn.commit();
		    return String.valueOf(id);
		    
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception(getIHTP().getErrorHTML("Rollback error : " + e.getMessage()));
	    	}
	    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah penyimpanan data " + ex.getMessage()));

	    } finally {
			if (db != null)
				db.close();
		}


	}
	
	// TAMBAH MAKLUMAT  
	@Override
	public String kemaskini(Hashtable<String,String> data) throws Exception {
		Date date = new Date(); 
		String currentDate = SDF.format(date);	
		String tarikh = "to_date('"+ currentDate + "','dd/MM/yyyy')";
	    if (!("".equals((String)data.get("txdTarikh")))) {
	    	tarikh = "to_date('" + data.get("txdTarikh") + "','dd/MM/yyyy')";
	    }
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		    r.update("ID_SUSULAN",data.get("idSusulan"));
		    r.add("TARIKH",r.unquote(tarikh));
		    r.add("KETERANGAN",data.get("catatan"));
	    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));  
		    sql = r.getSQLUpdate("TBLHTPSUSULAN");
			stmt.executeUpdate(sql);
		    
			stmt = db.getStatement();
			r = new SQLRenderer();
		    r.update("ID_SUSULANSTATUSFAIL",data.get("idSusulanStatus"));
	    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));  
		    sql = r.getSQLUpdate("TBLHTPSUSULANSTATUSFAIL");
			stmt.executeUpdate(sql);
			
			stmt = db.getStatement();
			r = new SQLRenderer();
		    r.update("ID_SUBURUSANSTATUSFAIL",data.get("idSubUrusanStatusFail"));
	    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));  
		    sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
		    myLog.info("sql update :sql="+sql);	    	    
			stmt.executeUpdate(sql);
			conn.commit();
		    return String.valueOf(data.get("idSusulanStatus"));
		    
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception(getIHTP().getErrorHTML("Rollback error : " + e.getMessage()));
	    	}
	    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah penyimpanan data " + ex.getMessage()));
	    	
	    } finally {
			if (db != null)
				db.close();
		}

	}
	
	// TAMBAH MAKLUMAT  
	public String simpanSusulanStatusFail(Hashtable<String,String> data) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		    long id = DB.getNextID("TBLHTPSUSULANSTATUSFAIL_SEQ"); 
		    r.add("ID_SUSULANSTATUSFAIL",id);
		    r.add("ID_SUSULAN",data.get("idSusulan"));
		    r.add("ID_SUBURUSANSTATUSFAIL",data.get("idStatusFail"));
		    r.add("SUMBER","PKECIL_TINDAKAN");
		    r.add("TARIKH_MASUK", r.unquote("sysdate"));
		    r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));	
		    r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		    r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));  
		    sql = r.getSQLInsert("TBLHTPSUSULANSTATUSFAIL");
		    myLog.info("sql insert baru :sql="+sql);
			    	    
		    stmt.executeUpdate(sql);
		    conn.commit();
		    return String.valueOf(id);
			    
		}catch (SQLException ex) { 
		    try {
		   		conn.rollback();
		   	} catch (SQLException e) {
		   		throw new Exception(getIHTP().getErrorHTML("Rollback error : " + e.getMessage()));
		   	}
	    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah penyimpanan data " + ex.getMessage()));
		    	
	    } finally {
	    	if (db != null)
				db.close();
		}

	}
	/** 
	 * Paparan Tindakan 
	 */
	@Override
	public Vector<Hashtable<String,String>> getMaklumat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		Vector<Hashtable<String,String>> listMaklumatFail = null;
		try {
			db = new Db();
			listMaklumatFail = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();				
		    sql = getSQL(idPermohonan);
			myLog.info("getMaklumat:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idSusulanStatus", Utils.isNull(rs.getString("ID_SUSULANSTATUSFAIL")));
				h.put("idStatusFail", Utils.isNull(rs.getString("ID_SUBURUSANSTATUSFAIL")));
				h.put("idSusulan", Utils.isNull(rs.getString("ID_SUSULAN")));
				h.put("tarikh", Utils.isNull(rs.getString("TARIKH")));
				h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
				h.put("status", Utils.isNull(rs.getString("STATUS")));
				listMaklumatFail.addElement(h);
				bil++;
			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return listMaklumatFail;
	}
	
	@Override
	public Hashtable<String,String> getMaklumat(String idPermohonan,String idSusulan) throws Exception {
		Hashtable<String,String> h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();				
		    sql = getSQL(idPermohonan)+ " AND S.ID_SUSULAN = "+idSusulan;
			//myLog.info("getMaklumat:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idSusulanStatus", Utils.isNull(rs.getString("ID_SUSULANSTATUSFAIL")));
				h.put("idStatusFail", Utils.isNull(rs.getString("ID_SUBURUSANSTATUSFAIL")));
				h.put("idSusulan", Utils.isNull(rs.getString("ID_SUSULAN")));
				h.put("tarikh", Utils.isNull(rs.getString("TARIKH")));
				h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
				h.put("status", Utils.isNull(rs.getString("STATUS")));
				bil++;
				
			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return h;
	}
	/** 
	 * Paparan Tindakan 
	 */
	@Override
	public Vector<Hashtable<String,String>> getMaklumatMengikutLangkah(String idPermohonan,String langkah) throws Exception {
		System.out.println("mamsuk dlm susulan bean-------------");
		Db db = null;
		String sql = "";
		Vector<Hashtable<String,String>> listMaklumatFail = null;
		try {
			db = new Db();
			listMaklumatFail = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();				
		    sql = getSQL(idPermohonan,langkah)+ " ORDER BY S.TARIKH_MASUK DESC";
			//myLog.info("Vector getMaklumat:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idSusulanStatus", Utils.isNull(rs.getString("ID_SUSULANSTATUSFAIL")));
				h.put("idStatusFail", Utils.isNull(rs.getString("ID_SUBURUSANSTATUSFAIL")));
				h.put("idSusulan", Utils.isNull(rs.getString("ID_SUSULAN")));
				h.put("tarikh", Utils.isNull(rs.getString("TARIKH")));
				h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
				h.put("status", Utils.isNull(rs.getString("STATUS")));
				h.put("pautan", Utils.isNull(rs.getString("ISPAUTAN")));
				listMaklumatFail.addElement(h);
				bil++;
			}
		}catch (SQLException ex) { 
	    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah select data " + ex.getMessage()));
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return listMaklumatFail;
	}
		
	
	public String getSQL(String idPermohonan){
		sql = "" +
	    	" SELECT S.ID_SUSULAN "+
	    	" ,NVL(TO_CHAR(S.TARIKH,'dd/mm/yyyy'),'01/01/1900') TARIKH" +
	    	" ,S.KETERANGAN  "+
	    	" ,NVL(TO_CHAR(S.TARIKH_MASUK,'dd/mm/yyyy'),'01/01/1900') TARIKH_MASUK" +
	    	" , ( " +
	    	" 	    SELECT RSI.KETERANGAN " +
	    	" 	    FROM TBLRUJSTATUS RSI, TBLRUJSUBURUSANSTATUS RSUS,TBLRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF " +
	    	" 	    WHERE RSI.ID_STATUS = RSUS.ID_STATUS " +
	    	" 	    AND RSUS.ID_SUBURUSANSTATUS = RSUSF.ID_SUBURUSANSTATUS " +
	    	" 	    AND RSUSF.AKTIF='1' " +
	    	" 	    AND RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL " +
	    	" 		AND SSF.ID_SUSULAN = S.ID_SUSULAN "+
	    	" 	) STATUS " +
	    	" ,(SELECT SSF.ID_SUSULANSTATUSFAIL "+
	    	" 	    FROM TBLRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF "+
	    	" 	    WHERE RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL "+
	    	" 	    AND RSUSF.AKTIF='1' "+
	    	" 	    AND SSF.ID_SUSULAN = S.ID_SUSULAN " +
	    	" ) ID_SUSULANSTATUSFAIL " +
	    	" ,(SELECT RSUSF.ID_SUBURUSANSTATUSFAIL "+
	    	" 	    FROM TBLRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF "+
	    	" 	    WHERE RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL "+
	    	" 	    AND RSUSF.AKTIF='1' "+
	    	" 	    AND SSF.ID_SUSULAN = S.ID_SUSULAN " +
	    	" ) ID_SUBURUSANSTATUSFAIL " +
	    	" FROM TBLHTPSUSULAN S "+
	    	" WHERE S.ID_PERMOHONAN = "+idPermohonan+
	    	"";
		return sql;
	}
	
	public String getSQL(String idPermohonan,String langkah){
		sql = "" +
	    	" SELECT S.ID_SUSULAN "+
	    	" ,NVL(TO_CHAR(S.TARIKH,'dd/mm/yyyy'),'01/01/1900') TARIKH" +
	    	" ,S.KETERANGAN  "+
	    	" ,NVL(TO_CHAR(S.TARIKH_MASUK,'dd/mm/yyyy'),'01/01/1900') TARIKH_MASUK" +
	    	" , ( " +
	    	" 	    SELECT RSI.KETERANGAN " +
	    	" 	    FROM TBLRUJSTATUS RSI, TBLRUJSUBURUSANSTATUS RSUS,TBLRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF " +
	    	" 	    WHERE RSI.ID_STATUS = RSUS.ID_STATUS " +
	    	" 	    AND RSUS.ID_SUBURUSANSTATUS = RSUSF.ID_SUBURUSANSTATUS " +
	    	" 	    AND RSUSF.AKTIF='1' " +
	    	" 	    AND RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL " +
	    	" 		AND SSF.ID_SUSULAN = S.ID_SUSULAN "+
	    	" 	) STATUS " +
	    	" , ( " +
	    	" 	    SELECT RSI.KETERANGAN " +
	    	" 	    FROM TBLRUJSTATUS RSI, TBLRUJSUBURUSANSTATUS RSUS,TBLRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF " +
	    	" 	    WHERE RSI.ID_STATUS = RSUS.ID_STATUS " +
	    	" 	    AND RSUS.ID_SUBURUSANSTATUS = RSUSF.ID_SUBURUSANSTATUS " +
	    	" 	    AND RSUSF.AKTIF='1' " +
	    	" 	    AND RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL " +
	    	" 		AND SSF.ID_SUSULAN = S.ID_SUSULAN "+
	    	" 	) ISPAUTAN " +
	    	" ,(SELECT SSF.ID_SUSULANSTATUSFAIL "+
	    	" 	    FROM TBLRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF "+
	    	" 	    WHERE RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL "+
	    	" 	    AND RSUSF.AKTIF='1' "+
	    	" 	    AND SSF.ID_SUSULAN = S.ID_SUSULAN " +
	    	" ) ID_SUSULANSTATUSFAIL " +
	    	" ,(SELECT RSUSF.ID_SUBURUSANSTATUSFAIL "+
	    	" 	    FROM TBLRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF "+
	    	" 	    WHERE RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL "+
	    	" 	    AND RSUSF.AKTIF='1' "+
	    	" 	    AND SSF.ID_SUSULAN = S.ID_SUSULAN " +
	    	" ) ID_SUBURUSANSTATUSFAIL " +
	    	" FROM TBLHTPSUSULAN S "+
	    	" WHERE S.ID_PERMOHONAN = "+idPermohonan+
	    	"";
		return sql;
	}
	
	public String getSQLHTP(String idPermohonan){
		sql = "" +
	    	" SELECT S.ID_SUSULAN "+
	    	" ,NVL(TO_CHAR(S.TARIKH,'dd/mm/yyyy'),'01/01/1900') TARIKH" +
	    	" ,S.KETERANGAN  "+
	    	" ,NVL(TO_CHAR(S.TARIKH_MASUK,'dd/mm/yyyy'),'01/01/1900') TARIKH_MASUK" +
	    	" , ( " +
	    	" 	    SELECT RSI.KETERANGAN " +
	    	" 	    FROM TBLRUJSTATUS RSI, TBLRUJSUBURUSANSTATUS RSUS,TBLHTPRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF " +
	    	" 	    WHERE RSI.ID_STATUS = RSUS.ID_STATUS " +
	    	" 	    AND RSUS.ID_SUBURUSANSTATUS = RSUSF.ID_SUBURUSANSTATUS " +
	    	" 	    AND RSUSF.AKTIF='1' " +
	    	" 	    AND RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL " +
	    	" 		AND SSF.ID_SUSULAN = S.ID_SUSULAN "+
	    	" 	) STATUS " +
	    	" ,(SELECT SSF.ID_SUSULANSTATUSFAIL "+
	    	" 	    FROM TBLHTPRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF "+
	    	" 	    WHERE RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL "+
	    	" 	    AND RSUSF.AKTIF='1' "+
	    	" 	    AND SSF.ID_SUSULAN = S.ID_SUSULAN " +
	    	" ) ID_SUSULANSTATUSFAIL " +
	    	" ,(SELECT RSUSF.ID_SUBURUSANSTATUSFAIL "+
	    	" 	    FROM TBLHTPRUJSUBURUSANSTATUSFAIL RSUSF,TBLHTPSUSULANSTATUSFAIL SSF "+
	    	" 	    WHERE RSUSF.ID_SUBURUSANSTATUSFAIL = SSF.ID_SUBURUSANSTATUSFAIL "+
	    	" 	    AND RSUSF.AKTIF='1' "+
	    	" 	    AND SSF.ID_SUSULAN = S.ID_SUSULAN " +
	    	" ) ID_SUBURUSANSTATUSFAIL " +
	    	" FROM TBLHTPSUSULAN S "+
	    	" WHERE S.ID_PERMOHONAN = "+idPermohonan+
	    	"";
		return sql;
	}	
	@Override
	public void hapus(String id) throws Exception {
		Db db = null;
		String sql = "";
		Vector<Hashtable<String,String>> senarai = null;
		Hashtable<String,String> h = null;
		SQLRenderer r = new SQLRenderer();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			senarai = getUlasanStatus(id);
			for (int iii = 0; iii < senarai.size(); iii++) { 
  	    	  	h = (Hashtable<String,String>)senarai.get(iii);
  	    	  	r = new SQLRenderer();
  	    	  	r.add("ID_SUBURUSANSTATUSFAIL",h.get("id"));
  				sql = r.getSQLDelete("TBLRUJSUBURUSANSTATUSFAIL");
   	    	  	myLog.info("TBLRUJSUBURUSANSTATUSFAIL:"+sql);
 				stmt.executeUpdate(sql);
    	  	
			}
	    	r = new SQLRenderer();
			r.add("ID_SUSULAN",id);
			sql = r.getSQLDelete("TBLHTPSUSULANSTATUSFAIL");
			myLog.info("TBLHTPSUSULANSTATUSFAIL:"+sql);
			stmt.executeUpdate(sql);

	    	r = new SQLRenderer();
			r.add("ID_SUSULAN",id);
			sql = r.getSQLDelete("TBLHTPSUSULAN");
			myLog.info("TBLHTPSUSULAN:"+sql);
			stmt.executeUpdate(sql);
	  
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public Vector<Hashtable<String,String>> getUlasanStatus(String idUlasan) throws Exception {
		Db db = null;
		String sql = "";
		Vector<Hashtable<String,String>> senarai = null;
		try {
			db = new Db();
			senarai = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();				
		    sql = " SELECT ID_SUBURUSANSTATUSFAIL FROM TBLHTPSUSULANSTATUSFAIL "+
		    	" WHERE ID_SUSULAN="+idUlasan+"";
			//myLog.info("getMaklumat:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("id", Utils.isNull(rs.getString("ID_SUBURUSANSTATUSFAIL")));
				senarai.addElement(h);
			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return senarai;
		
	}
	
	/** Dibuat pada 11/10/2012 Oleh Mohamad Rosli
	// Bertujuan mendapatkan maklumat susulan mengikut permohonan dan sumber
	**/
	@Override
	public Hashtable<String,String> getMaklumat(String idPermohonan,String sumber,String idSumber) throws Exception {
		try {
			db = new Db();
			Statement stmt = db.getStatement();				
		    sql = getSQL(idPermohonan)+
		    " AND S.SUMBER = '"+sumber+"'"+
		    " AND S.ID_SUMBER = "+idSumber+
		    "";
			//myLog.info("getMaklumat:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idSusulanStatus", Utils.isNull(rs.getString("ID_SUSULANSTATUSFAIL")));
				h.put("idStatusFail", Utils.isNull(rs.getString("ID_SUBURUSANSTATUSFAIL")));
				h.put("idSusulan", Utils.isNull(rs.getString("ID_SUSULAN")));
				h.put("tarikh", Utils.isNull(rs.getString("TARIKH")));
				h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
				h.put("status", Utils.isNull(rs.getString("STATUS")));
				bil++;
				
			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return h;
	}
	
	@Override
	public Hashtable<String,String> getMaklumatSusulan(String idPermohonan,String sumber) throws Exception {
		try {
			db = new Db();
			Statement stmt = db.getStatement();				
		    sql = getSQLSusulan(idPermohonan)+
		    " AND S.SUMBER = '"+sumber+"'"+
		    "";
			//myLog.info("getMaklumat:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				//h.put("idSusulanStatus", Utils.isNull(rs.getString("ID_SUSULANSTATUSFAIL")));
				//h.put("idStatusFail", Utils.isNull(rs.getString("ID_SUBURUSANSTATUSFAIL")));
				h.put("idSusulan", Utils.isNull(rs.getString("ID_SUSULAN")));
				h.put("tarikh", Utils.isNull(rs.getString("TARIKH")));
				h.put("keterangan", Utils.isNull(rs.getString("KETERANGAN")));
				//h.put("status", Utils.isNull(rs.getString("STATUS")));
				bil++;
				
			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return h;
	}
	
	public String getSQLSusulan(String idPermohonan){
		sql = "" +
	    	" SELECT S.ID_SUSULAN "+
	    	" ,NVL(TO_CHAR(S.TARIKH,'dd/mm/yyyy'),'01/01/1900') TARIKH" +
	    	" ,S.KETERANGAN  "+
	    	" ,NVL(TO_CHAR(S.TARIKH_MASUK,'dd/mm/yyyy'),'01/01/1900') TARIKH_MASUK" +
	    	" FROM TBLHTPSUSULAN S "+
	    	" WHERE S.ID_PERMOHONAN = "+idPermohonan+
	    	"";
		return sql;
		
	}
	
	@Override
	public String getLangkahMengikutRole(String portal_role) throws Exception {
		String langkah = "0";
		if(portal_role.contains("PenggunaNegeri")){
  			langkah = "81";
		}else if(portal_role.contains("PegawaiNegeri")){
  			langkah = "82";
		}else if(portal_role.contains("PengarahNegeri")){
  			langkah = "83";
		}else if(portal_role.contains("HQPengguna")){
  			langkah = "84";
		}else if(portal_role.contains("HQPegawai")){
  			langkah = "85";
		}else if(portal_role.contains("HQPengarah")){
  			langkah = "86";
		}
		return langkah;
		
	}
	
	@Override
	// TAMBAH/KEMASKINI MAKLUMAT  
	public String simpanKemaskini(Hashtable<String,String> data) throws Exception {
		myLog.info("simpanKemaskini:data="+data);
		Date date = new Date(); 
		String currentDate = SDF.format(date);
		
		String tarikh = "to_date('"+ currentDate + "','dd/MM/yyyy')";
	    if (!("".equals((String)data.get("txdTarikh")))) {
	    	tarikh = "to_date('" + data.get("txdTarikh") + "','dd/MM/yyyy')";
	    }

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long id = 0L;
			myLog.info(data.get("idSusulan"));
			if(!data.get("idSusulan").equals("-1")){
				id =Long.parseLong(data.get("idSusulan"));
				r.update("ID_SUSULAN",data.get("idSusulan"));
			}else{
				id = DB.getNextID("TBLHTPSUSULAN_SEQ"); 
				r.add("ID_SUSULAN",id);
			}
		    r.add("ID_PERMOHONAN",data.get("idPermohonan"));
		    r.add("TARIKH",r.unquote(tarikh));
		    r.add("KETERANGAN",data.get("catatan"));
		    r.add("SUMBER",data.get("sumber"));
		    r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));	
	    	r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));  
			if(!data.get("idSusulan").equals("-1"))
		    	sql = r.getSQLUpdate("TBLHTPSUSULAN");
		    else
				sql = r.getSQLInsert("TBLHTPSUSULAN");

		    myLog.info("sql insert_kemaskini :sql="+sql);	    	    
			stmt.executeUpdate(sql);
			
			conn.commit();
		    return String.valueOf(id);
		    
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception(getIHTP().getErrorHTML("Rollback error : " + e.getMessage()));
	    	}
	    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah penyimpanan data " + ex.getMessage()));

	    } finally {
			if (db != null)
				db.close();
		}

	}
	@Override
	public Vector<Hashtable<String,String>> getListImage(String idHakmilikPerihal) throws Exception {
		Db db = null;
		String sql = "";
		Vector<Hashtable<String,String>> listImage = null;
		try {
			db = new Db();
			listImage = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();				
		    sql = " SELECT D.ID_GAMBAR,D.NAMA_FAIL,D.ID_HAKMILIKPERIHAL ,D.PERIHAL_IMEJ "+
					" FROM TBLHTPGAMBAR D, TBLHTPHAKMILIKPERIHAL A "+
					" WHERE A.ID_HAKMILIKPERIHAL = D.ID_HAKMILIKPERIHAL(+) "+
					" AND D.JENIS_GAMBAR = 'PEMBANGUNAN' "+
					" AND A.ID_HAKMILIKPERIHAL = "+idHakmilikPerihal+ " ORDER BY D.ID_GAMBAR DESC ";
				
		    myLog.info("Vector gambar========== = "+sql);
		    ResultSet rs = stmt.executeQuery(sql);
				
		    Hashtable<String,String> h;
		    int bil = 1;
		    while (rs.next()) {
		    	h = new Hashtable<String,String>();
				h.put("bil", bil+".");
				h.put("idGambar", rs.getString("ID_GAMBAR")==null ? "" :rs.getString("ID_GAMBAR"));
				h.put("namaFail", rs.getString("NAMA_FAIL")==null ? "" :rs.getString("NAMA_FAIL"));
				h.put("idHakmilikPerihal", rs.getString("ID_HAKMILIKPERIHAL"));
				h.put("perihalImej", rs.getString("PERIHAL_IMEJ")==null ? "" :rs.getString("PERIHAL_IMEJ"));
				listImage.addElement(h);
				bil++;
					
			}
		}catch (SQLException ex) { 
	    	throw new Exception(getIHTP().getErrorHTML("Ralat : Masalah select data " + ex.getMessage()));
		} finally {
			if (db != null)
				db.close();
			}
		return listImage;
		
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
	
	
}
