package ekptg.report.htp.utiliti;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;


public class FrmPopupPilihPegawaiReportData {
	
	static Logger myLogger = Logger.getLogger(ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportData.class);

	private static Vector<Hashtable<String,String>> detailPegawai = null;

	public static Vector<Hashtable<String,String>> getDetailPegawai(){
		return detailPegawai;
	}
	
	public void setDetailPegawai(String id_pegawai)throws Exception {	    
		detailPegawai = new Vector<Hashtable<String,String>>();
		Db db = null;
		detailPegawai.clear();
	    String sql = "";
	    
	    try{
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	      
	   		sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI, J.KETERANGAN AS JAWATAN, UI.ID_JAWATAN, ";
	   		sql += " UI.EMEL ";
	   		sql += " FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN J ";
	   		sql += " WHERE U.USER_ID = UI.USER_ID ";
    		sql += " AND UI.ID_JAWATAN = J.ID_JAWATAN(+) ";
    		sql += " AND U.USER_ID = '"+id_pegawai+"'";
	    	
    		ResultSet rs = stmt.executeQuery(sql);	     
    		Hashtable<String,String> h;
    		while (rs.next()) {
    			h = new Hashtable<String,String>();
    			h.put("emel", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
    			h.put("id_pegawai", rs.getString("ID_PEGAWAI")== null?"":rs.getString("ID_PEGAWAI"));
    			h.put("id_jawatan", rs.getString("ID_JAWATAN")== null?"":rs.getString("ID_JAWATAN"));
    			h.put("nama_pegawai", rs.getString("NAMA_PEGAWAI")== null?"":rs.getString("NAMA_PEGAWAI").toUpperCase());
	    		h.put("jawatan", rs.getString("JAWATAN")== null?"":rs.getString("JAWATAN"));
	    		detailPegawai.addElement(h);
	    	
    		}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close detail pegawai

	public Vector<Hashtable<String,String>> getPegawaiTerperinci(String userId)throws Exception {	    
		detailPegawai = new Vector<Hashtable<String,String>>();
		Db db = null;
		detailPegawai.clear();
	    String sql = "";
	    
	    try{
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	      
	   		sql = " SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI, J.KETERANGAN AS JAWATAN, UI.ID_JAWATAN, ";
	   		sql += " UI.EMEL ";
	   		sql += " FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN J ";
	   		sql += " WHERE U.USER_ID = UI.USER_ID ";
    		sql += " AND UI.ID_JAWATAN = J.ID_JAWATAN(+) ";
    		sql += " AND U.USER_ID = '"+userId+"'";
	    	
    		ResultSet rs = stmt.executeQuery(sql);	     
    		Hashtable<String,String> h;
    		while (rs.next()) {
    			h = new Hashtable<String,String>();
    			h.put("emel", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
    			h.put("id_pegawai", rs.getString("ID_PEGAWAI")== null?"":rs.getString("ID_PEGAWAI"));
    			h.put("id_jawatan", rs.getString("ID_JAWATAN")== null?"":rs.getString("ID_JAWATAN"));
    			h.put("nama_pegawai", rs.getString("NAMA_PEGAWAI")== null?"":rs.getString("NAMA_PEGAWAI").toUpperCase());
	    		h.put("jawatan", rs.getString("JAWATAN")== null?"":rs.getString("JAWATAN"));
	    		detailPegawai.addElement(h);
	    	
    		}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    return detailPegawai;
	    
	}
	
	
}
