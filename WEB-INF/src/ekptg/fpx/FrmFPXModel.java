package ekptg.fpx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */


public class FrmFPXModel {
	
	static Logger myLogger = Logger.getLogger(FrmFPXModel.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	private static Vector dataJenisBayaran = null;
	@SuppressWarnings("unchecked")
	private static Vector dataFPX = null;

	@SuppressWarnings("unchecked")
	public static  Vector getDataJenisBayaran(){
		return dataJenisBayaran;
	}
	
	@SuppressWarnings("unchecked")
	public static  Vector getDataFPX(){
		return dataFPX;
	}
	
	@SuppressWarnings("unchecked")
	public static void setDataJenisBayaran(String id_jenisbayaran)throws Exception {
		
		dataJenisBayaran = new Vector();
		
	    Db db = null;
	    dataJenisBayaran.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT KETERANGAN FROM TBLRUJJENISBAYARAN WHERE ID_JENISBAYARAN = '"+id_jenisbayaran+"'";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;

	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("jenis_bayaran", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));    	 
	    			dataJenisBayaran.addElement(h);
	    		}			    
	    		
	    	} finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataJenisBayaran
	
	
	@SuppressWarnings("unchecked")
	public static String simpanMaklumatPengesahan(Hashtable data) throws Exception
	  {
		
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    String output="";
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	
	    	
	    	String id_user = (String)data.get("id_user");
	    	String id_jenisbayaran = (String)data.get("id_jenisbayaran");	       
	    	String txtNoFail = (String)data.get("txtNoFail");
	    	String txtAmaun = (String)data.get("txtAmaun");
	    	String txtCatatan = (String)data.get("txtCatatan");
	    	String txtSysdate = (String)data.get("txtSysdate");
	    	
	    	String txtNama = (String)data.get("txtNama");
	    	String txtNoPengenalan = (String)data.get("txtNoPengenalan");
	    	String sorFlagJenisPembayar = (String)data.get("sorFlagJenisPembayar");
	    	String txtNoBil = (String)data.get("txtNoBil");
	    	
	    	//convert string to date
	    	String TS = "to_date('" + txtSysdate + "','dd/MM/yyyy')";
	
	    	//generate no permohonan "JKPTG/PPT/kod_suburusan/this_year-000001
	    	long id_datafpx = DB.getNextID("TBLDATAFPX_SEQ");    
	    	
	    	SQLRenderer r = new SQLRenderer();	
	     	r.add("id_datafpx",id_datafpx);
	     	r.add("id_jenisbayaran",id_jenisbayaran);
	     	
	     	r.add("nama",txtNama);
	     	r.add("no_kp",txtNoPengenalan);
	     	r.add("jenis_pembayar",sorFlagJenisPembayar);
	     	r.add("no_bil",txtNoBil);
	     	
	     	r.add("no_fail",txtNoFail);
	     	r.add("amaun_bayaran",txtAmaun);
	     	r.add("catatan",txtCatatan);
	     	r.add("tarikh_bayaran", r.unquote(TS));
	     	r.add("tarikh_masuk",r.unquote("sysdate"));
			r.add("id_masuk",id_user);
	     	sql = r.getSQLInsert("tbldatafpx");
	     	stmt.executeUpdate(sql);
	  
	     	output = ""+id_datafpx;
	    	
	     	conn.commit();	
		      
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data ");
	    }
	    finally {
	      if (db != null) db.close();
	    }
	    
	    return output;
	   
	  }//close add
	
	
	@SuppressWarnings("unchecked")
	public static void setDataFPX(String id_datafpx)throws Exception {
		
		dataFPX = new Vector();
		
	    Db db = null;
	    dataFPX.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  "SELECT DISTINCT A.NO_FAIL, A.AMAUN_BAYARAN, A.CATATAN, A.TARIKH_BAYARAN, B.KETERANGAN AS JENIS_BAYARAN, ";
	    		sql += " A.JENIS_PEMBAYAR, A.NAMA, A.NO_KP, A.NO_BIL ";
	    		sql += " FROM TBLDATAFPX A, TBLRUJJENISBAYARAN B ";
	    		sql += " WHERE A.ID_JENISBAYARAN = B.ID_JENISBAYARAN(+) ";
	    		sql += " AND A.ID_DATAFPX = '"+id_datafpx+"'";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;

	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("nama", rs.getString("NAMA")== null?"":rs.getString("NAMA"));    	 
	    			h.put("no_kp", rs.getString("NO_KP")== null?"":rs.getString("NO_KP"));   
	    			h.put("no_bil", rs.getString("NO_BIL")== null?"":rs.getString("NO_BIL"));  
	    			h.put("no_fail", rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));   
	    			h.put("catatan", rs.getString("CATATAN")== null?"":rs.getString("CATATAN"));    	 
	    			h.put("jenis_bayaran", rs.getString("JENIS_BAYARAN")== null?"":rs.getString("JENIS_BAYARAN"));   
	    			h.put("tarikh_bayaran", rs.getDate("TARIKH_BAYARAN")==null?"":Format.format(rs.getDate("TARIKH_BAYARAN")));
	    			h.put("amaun_bayaran", rs.getString("AMAUN_BAYARAN")== null?"":Utils.format2Decimal(rs.getDouble("AMAUN_BAYARAN"))); 
	    			
	    			if(rs.getString("JENIS_PEMBAYAR") != null && rs.getString("JENIS_PEMBAYAR") != ""){
	    				
	    				if(rs.getString("JENIS_PEMBAYAR").equals("2")){
	    					h.put("jenisPembayar","SYARIKAT");
	    				}else{
	    					h.put("jenisPembayar","INDIVIDU");
	    				}
	    				
	    			}else{
	    				h.put("jenisPembayar","INDIVIDU");
	    			}
	    			
	    			dataFPX.addElement(h);
	    		}			    
	    		
	    	} finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataFPX
	
}//close class
