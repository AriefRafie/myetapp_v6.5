package ekptg.model.integrasi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import etapp.entity.integrasi.StatusSemasa;
//import ekptg.model.htp.HtpBean;

public class FrmIntegrasiStatusBean implements IIntegrasiStatus{
	
 	private IHtp iHTP = null;  
	private final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	private Hashtable h = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.integrasi.FrmLHDNBean.class);
	String sql = "";
	Db db = null;
	Connection conn = null;

	// TAMBAH MAKLUMAT  
	@Override
	public String simpan(StatusSemasa status,StatusSemasa statusBaru) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		long idStatusSemasa = 0;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_SUMBER",r.unquote(String.valueOf(status.getIdSumber())));
			r.update("ID_STATUSMODUL",r.unquote(String.valueOf(status.getIdStatusModul())));
			r.update("AKTIF", "1");
			r.add("AKTIF",status.getAktif());
			r.add("ID_KEMASKINI", statusBaru.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
			sql = r.getSQLUpdate("TBLINTRUJSTATUSMODULSEMASA");
			//myLog.info(sql);
			stmt.executeUpdate(sql);
			
			idStatusSemasa = DB.getNextID("INTRUJSTATUSMODULSEMASA_SEQ");		  
			r = new SQLRenderer();		  
			r.add("ID_STATUSMODULSEMASA", idStatusSemasa);
			r.add("ID_SUMBER", String.valueOf(status.getIdSumber()));
			r.add("ID_STATUSMODUL", r.unquote(String.valueOf(statusBaru.getIdStatusModul())));
			r.add("AKTIF",statusBaru.getAktif());
			r.add("CATATAN",statusBaru.getCatatan());
			r.add("ID_MASUK", String.valueOf(statusBaru.getIdMasuk()));
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", String.valueOf(statusBaru.getIdMasuk()));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLINTRUJSTATUSMODULSEMASA");
			//myLog.info(sql);
		    stmt.executeUpdate(sql);
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat simpan status:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}		
		return String.valueOf(idStatusSemasa);
		
	}

	// TAMBAH MAKLUMAT  
	@Override
	public String kemaskini(StatusSemasa status) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_SUMBER",r.unquote(String.valueOf(status.getIdSumber())));
			r.update("ID_STATUSMODUL",r.unquote(String.valueOf(status.getIdStatusModul())));
			r.update("AKTIF", "1");
			//r.add("AKTIF",status.getAktif());
			r.add("ID_KEMASKINI", status.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
			sql = r.getSQLUpdate("TBLINTRUJSTATUSMODULSEMASA");
			//myLog.info(sql);
			stmt.executeUpdate(sql);
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat simpan status:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}		
		return String.valueOf(status.getIdStatusModul());
		
	}
	
    public Long getIdStatusModulByLangkah (String langkah, String modul, String op) throws Exception {
		Long returnValue = 0L;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
		    SQLRenderer r = new SQLRenderer();
		    r.add("SM.ID_STATUSMODUL");
		    r.add("SM.MODUL",modul);
		    r.add("SM.LANGKAH",langkah,op);
		    sql = r.getSQLSelect("TBLINTRUJSTATUSMODUL SM");
		      
		    Statement stmt = db.getStatement();
		    myLog.info("getIdStatusModulByLangkah("+ langkah+","+modul+","+op+"):sql::"+sql);
		    ResultSet rs = stmt.executeQuery(sql);
		    while (rs.next()) {
		    	  returnValue = Long.parseLong(rs.getString("ID_STATUSMODUL"));
		    }
		    return returnValue;
		} finally {
			if (db != null) db.close();
		}
		
	}	
		  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
	
	
}
