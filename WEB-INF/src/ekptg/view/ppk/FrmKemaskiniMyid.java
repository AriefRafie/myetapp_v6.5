package ekptg.view.ppk;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmTukaranStatus;

/*
 * @author
 * Razman
 */

public class FrmKemaskiniMyid extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmTukarStatus.class);	
	FrmTukaranStatus model = new FrmTukaranStatus();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	HttpSession session = null;
	String action = null;
	private final String PATH="app/ppk/";
	private String vm = PATH +"frmKemaskiniMyid.jsp";
	
	
	
	public String doTemplate2() throws Exception{
		session = request.getSession();
		String command = getParam("command");
		action = getParam("action");
    	myLogger.info("[submit] :: " +command);
		String doPost = (String) session.getAttribute("doPost");
		myLogger.info("check doPost :"+doPost);   
		String usid=(String)session.getAttribute("_ekptg_user_id");
		
		context.put("ScrollX",getParam("ScrollX"));
    	context.put("ScrollY",getParam("ScrollY")); 
		
		Vector papar_list_ob = null;
    	Vector papar_list_simati = null;
    	Vector papar_list_pemohon = null;
    	
    	this.context.put("alert_baru_simati_value","");
    	this.context.put("alert_lama_simati_value","");
    	this.context.put("alert_lain_simati_value","");
    	
    	default_data();
		
		if ("cariMainSub".equals(command)){
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());     		
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","","")); 
    		context.put("view_list_fail","yes");
    		}
		else if ("paparSub".equals(command)){
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		//context.put("txtNoFailSub", txtNoFailSub.trim());   
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}
    		context.put("txtNoFailSub", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session,id_permohonan,"Y","","T");
    		
    		model.setListSub(getParam("id_fail_carian"));    		
    		context.put("list_fail",model.search_nofail(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")+"",usid,"","",""));
    		context.put("view_list_fail","yes");
    		context.put("view_list_myid","yes");
    		
    		papar_list_ob = model.papar_list_ob(getParam("id_fail_carian"));
        	papar_list_simati = model.papar_list_simati(getParam("id_fail_carian"));
        	papar_list_pemohon = model.papar_list_pemohon(getParam("id_fail_carian"));
        	
        	context.put("papar_list_ob",papar_list_ob); 
    		context.put("papar_list_simati",papar_list_simati); 
    		context.put("papar_list_pemohon",papar_list_pemohon); 
    	}
		else if ("simpanSub".equals(command)){
			
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());    		
    		model.setListSub(getParam("id_fail_carian"));    		
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","",""));
    		context.put("view_list_fail","yes");
    		context.put("view_list_myid","yes");  
    		
    		papar_list_ob = model.papar_list_ob(getParam("id_fail_carian"));
        	papar_list_simati = model.papar_list_simati(getParam("id_fail_carian"));
        	papar_list_pemohon = model.papar_list_pemohon(getParam("id_fail_carian"));
    		
    		
        	
        	if(papar_list_simati.size()>0)
        	{
        		for (int i = 1; i < papar_list_simati.size()+1; i++) 
				{        			 
        		String id_simati = "id_simati"+i;
        		String no_kp_baru = "no_kp_baru_simati"+i;
        		String no_kp_lama = "no_kp_lama_simati"+i;
        		String no_kp_lain = "no_kp_lain_simati"+i; 
        		
        		String alert_baru_simati_value = "alert_baru_simati_value"+i;
        		String alert_lama_simati_value = "alert_lama_simati_value"+i;
        		String alert_lain_simati_value = "alert_lain_simati_value"+i;
        		
        		
        		if(checkKPSimatiBaru(getParam(id_simati),getParam(no_kp_baru)) == true)
		     	{
        			myLogger.info("Duplicate 2");
        			this.context.put("alert_baru_simati_value","<font color = 'red'>Myid ("+getParam(no_kp_baru)+") Sudah Wujud </font>");
		     	}
        		else
        		{
        			updateSimatiBaru(session,usid,getParam(id_simati),getParam(no_kp_baru),getParam(no_kp_lama),getParam(no_kp_lain));
        		}
        		
        		if(checkKPSimatiLama(getParam(id_simati),getParam(no_kp_lama)) == true)
		     	{
        			myLogger.info("Duplicate 3");
        			this.context.put("alert_lama_simati_value","<font color = 'red'>Myid ("+getParam(no_kp_lama)+") Sudah Wujud</font>");
       		    }
        		else
        		{
        			updateSimatiLama(session,usid,getParam(id_simati),getParam(no_kp_baru),getParam(no_kp_lama),getParam(no_kp_lain));
        		}
        		
        		if(checkKPSimatiLain(getParam(id_simati),getParam(no_kp_lain)) == true)
		     	{
        			myLogger.info("Duplicate 4");
        			this.context.put("alert_lain_simati_value","<font color = 'red'>Myid ("+getParam(no_kp_lain)+") Sudah Wujud</font>");
       		    }
        		else
        		{
        			updateSimatiLain(session,usid,getParam(id_simati),getParam(no_kp_baru),getParam(no_kp_lama),getParam(no_kp_lain));
        		}
        		
        		}        				
        	}
        	
        	if(papar_list_pemohon.size()>0)
        	{
        		for (int i = 1; i < papar_list_pemohon.size()+1; i++) 
				{        			 
        		String id_pemohon = "id_pemohon"+i;
        		String no_kp_baru = "no_kp_baru_pemohon"+i;
        		String no_kp_lama = "no_kp_lama_pemohon"+i;
        		String no_kp_lain = "no_kp_lain_pemohon"+i;
        		myLogger.info("param id_pemohon :"+id_pemohon);
        		myLogger.info("param no_kp_baru :"+no_kp_baru);
        		myLogger.info("param no_kp_lama :"+no_kp_lama);
        		myLogger.info("param no_kp_lain :"+no_kp_lain);        		
        		updatePemohon(session,usid,getParam(id_pemohon),getParam(no_kp_baru),getParam(no_kp_lama),getParam(no_kp_lain));
				}        				
        	}
        	
        	if(papar_list_ob.size()>0)
        	{
        		for (int i = 1; i < papar_list_ob.size()+1; i++) 
				{        			 
        		String id_ob = "id_ob"+i;
        		String id_pemohon_ob = "id_pemohon_ob"+i;        		
        		String no_kp_baru = "no_kp_baru_ob"+i;
        		String no_kp_lama = "no_kp_lama_ob"+i;
        		String no_kp_lain = "no_kp_lain_ob"+i;
        		myLogger.info("param id_pemohon_ob :"+id_pemohon_ob);
        		myLogger.info("param id_ob :"+id_ob);
        		myLogger.info("param no_kp_baru :"+no_kp_baru);
        		myLogger.info("param no_kp_lama :"+no_kp_lama);
        		myLogger.info("param no_kp_lain :"+no_kp_lain);        		
        		updateOb(session,usid,getParam(id_pemohon_ob),getParam(id_ob),getParam(no_kp_baru),getParam(no_kp_lama),getParam(no_kp_lain));                	
				}        				
        	}	
        	
        	papar_list_ob = null;
        	papar_list_simati = null;
        	papar_list_pemohon = null;        	
        	papar_list_ob = model.papar_list_ob(getParam("id_fail_carian"));
        	papar_list_simati = model.papar_list_simati(getParam("id_fail_carian"));
        	papar_list_pemohon = model.papar_list_pemohon(getParam("id_fail_carian"));
        	context.put("papar_list_ob",papar_list_ob); 
    		context.put("papar_list_simati",papar_list_simati); 
    		context.put("papar_list_pemohon",papar_list_pemohon); 
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session,id_permohonan,"Y","","T");
    	}
		else
		{
			default_data();	
		}
	
		
		
	return vm;
	}
	
	private void default_data()throws Exception{
		context.put("view_list_fail","");
		context.put("txtNoFailSub",""); 
		context.put("list_fail",""); 
		context.put("id_fail_carian",""); 
		context.put("view_list_myid","");  
		context.put("papar_list_ob",""); 
		context.put("papar_list_simati",""); 
		context.put("papar_list_pemohon",""); 
		
		}
	
	private void updateSimatiBaru(HttpSession session,String user_id,String id_simati,String no_kp_baru,String no_kp_lama,String no_kp_lain) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql1="";			
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
					r.update("ID_SIMATI",id_simati);
					r.add("NO_KP_BARU",no_kp_baru);
					//r.add("NO_KP_LAMA",no_kp_lama);
					//r.add("NO_KP_LAIN",no_kp_lain);
					r.add("ID_KEMASKINI",user_id);						
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKSIMATI");
				myLogger.info("UPDATE TBLPPKSIMATI :"+sql1);
					stmt.executeUpdate(sql1);								
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	private void updateSimatiLama(HttpSession session,String user_id,String id_simati,String no_kp_baru,String no_kp_lama,String no_kp_lain) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql1="";			
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
					r.update("ID_SIMATI",id_simati);
					//r.add("NO_KP_BARU",no_kp_baru);
					r.add("NO_KP_LAMA",no_kp_lama);
					//r.add("NO_KP_LAIN",no_kp_lain);
					r.add("ID_KEMASKINI",user_id);						
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKSIMATI");
				myLogger.info("UPDATE TBLPPKSIMATI :"+sql1);
					stmt.executeUpdate(sql1);								
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	private void updateSimatiLain(HttpSession session,String user_id,String id_simati,String no_kp_baru,String no_kp_lama,String no_kp_lain) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql1="";			
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
					r.update("ID_SIMATI",id_simati);
					//r.add("NO_KP_BARU",no_kp_baru);
					//r.add("NO_KP_LAMA",no_kp_lama);
					r.add("NO_KP_LAIN",no_kp_lain);
					r.add("ID_KEMASKINI",user_id);						
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKSIMATI");
				myLogger.info("UPDATE TBLPPKSIMATI :"+sql1);
					stmt.executeUpdate(sql1);								
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	private void updatePemohon(HttpSession session,String user_id,String id_pemohon,String no_kp_baru,String no_kp_lama,String no_kp_lain) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql1="";			
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
					r.update("ID_PEMOHON",id_pemohon);
					r.add("NO_KP_BARU",no_kp_baru);
					r.add("NO_KP_LAMA",no_kp_lama);
					r.add("NO_KP_LAIN",no_kp_lain);
					r.add("ID_KEMASKINI",user_id);						
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKPEMOHON");
				myLogger.info("UPDATE TBLPPKPEMOHON :"+sql1);
					stmt.executeUpdate(sql1);
					
					r.clear();
					r.update("ID_PEMOHON",id_pemohon);
					r.add("NO_KP_BARU",no_kp_baru);
					r.add("NO_KP_LAMA",no_kp_lama);
					r.add("NO_KP_LAIN",no_kp_lain);
					r.add("ID_KEMASKINI",user_id);						
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKOB");
				myLogger.info("UPDATE TBLPPKOB :"+sql1);
					stmt.executeUpdate(sql1);
					
					r.clear();
					r.update("ID_PEMOHON",id_pemohon);
					r.add("NO_KP_BARU",no_kp_baru);
					r.add("NO_KP_LAMA",no_kp_lama);
					r.add("NO_KP_LAIN",no_kp_lain);
					r.add("ID_KEMASKINI",user_id);						
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKOBPERMOHONAN");
				myLogger.info("UPDATE TBLPPKOBPERMOHONAN :"+sql1);
					stmt.executeUpdate(sql1);
					
					
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}

	
	private void updateOb(HttpSession session,String user_id,String id_pemohon,String id_ob,String no_kp_baru,String no_kp_lama,String no_kp_lain) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql1="";			
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
					
					r.update("ID_PEMOHON",id_pemohon);
					r.add("NO_KP_BARU",no_kp_baru);
					r.add("NO_KP_LAMA",no_kp_lama);
					r.add("NO_KP_LAIN",no_kp_lain);
					r.add("ID_KEMASKINI",user_id);						
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKPEMOHON");
				myLogger.info("UPDATE TBLPPKPEMOHON OB :"+sql1);
					stmt.executeUpdate(sql1);
					
					r.clear();
					r.update("ID_OB",id_ob);
					r.add("NO_KP_BARU",no_kp_baru);
					r.add("NO_KP_LAMA",no_kp_lama);
					r.add("NO_KP_LAIN",no_kp_lain);
					r.add("ID_KEMASKINI",user_id);						
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKOB");
				myLogger.info("UPDATE TBLPPKOB :"+sql1);
					stmt.executeUpdate(sql1);
					
					r.clear();
					r.update("ID_OB",id_ob);
					r.add("NO_KP_BARU",no_kp_baru);
					r.add("NO_KP_LAMA",no_kp_lama);
					r.add("NO_KP_LAIN",no_kp_lain);
					r.add("ID_KEMASKINI",user_id);						
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKOBPERMOHONAN");
				myLogger.info("UPDATE TBLPPKOB :"+sql1);
					stmt.executeUpdate(sql1);
					
					
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
public  boolean checkKPSimatiBaru(String id_simati,String kpbaru) throws Exception {
		Db db = null;
		boolean a = false;
		String jumlah_baru = "0";
		String jumlah_lama = "0";
		String jumlah_lain = "0";
		
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			
			if(kpbaru!="")
			{
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();		
			sql =" SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_BARU "
			+" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
			+" WHERE SM.ID_SIMATI = MS.ID_SIMATI"
			+" AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
			//+" AND P.ID_PERMOHONAN <> '"+idp+"'"
			+" AND SM.ID_SIMATI <> '"+id_simati+"'"
			+" AND SM.NO_KP_BARU = '"+kpbaru+"'"
			+" AND P.ID_STATUS <> '999'"
			+" AND P.ID_STATUS <> '169'";
			
			myLogger.info("CHECK SQL 1:"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			  if (rs.next()) {
                  if (rs.getInt("JUMLAH_BARU") > 0) {
                        a = true;
                  }
              }			  
			}  
					
			
		}finally {
			if(db != null) db.close();
			
		}
	    //return false;
		return a;
		}


public  boolean checkKPSimatiLama(String id_simati,String kplama) throws Exception {
	
	
	myLogger.info("NO KP LAMA SIMATI :"+kplama);
	
	
	Db db = null;
	boolean a = false;
	String jumlah_baru = "0";
	String jumlah_lama = "0";
	String jumlah_lain = "0";
	
	String sql = "";
	String sql1 = "";
	String sql2 = "";
	String sql3 = "";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	try {
		db = new Db();
		
		
		
		
		if(kplama!=""  && !kplama.equals("TDK"))
		{
		Statement stmt1 = db.getStatement();
		SQLRenderer r1 = new SQLRenderer();		
		sql1 =" SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAMA "
		+" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
		+" WHERE SM.ID_SIMATI = MS.ID_SIMATI"
		+" AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
		//+" AND P.ID_PERMOHONAN <> '"+idp+"'"
		+" AND SM.ID_SIMATI <> '"+id_simati+"'"
		+" AND SM.NO_KP_LAMA = '"+kplama+"'"
		+" AND P.ID_STATUS <> '999'"
		+" AND P.ID_STATUS <> '169'";
		
		myLogger.info("CHECK SQL 2:"+sql1.toUpperCase());
		
		ResultSet rs1 = stmt1.executeQuery(sql1);
		Hashtable h1;
		/*
		while (rs1.next())
		{	
			jumlah_lama = rs1.getString("JUMLAH_LAMA")==null?"":rs1.getString("JUMLAH_LAMA");	
		}
		*/
		 if (rs1.next()) {
             if (rs1.getInt("JUMLAH_LAMA") > 0) {
                   a = true;
             }
         }		
		
		}
		
		
		
	}finally {
		if(db != null) db.close();
		
	}
    //return false;
	return a;
	}


public  boolean checkKPSimatiLain(String id_simati,String kplain) throws Exception {
	
	
	myLogger.info("NO KP LAMA SIMATI :"+kplain);
	
	
	Db db = null;
	boolean a = false;
	String jumlah_baru = "0";
	String jumlah_lama = "0";
	String jumlah_lain = "0";
	
	String sql = "";
	String sql1 = "";
	String sql2 = "";
	String sql3 = "";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	try {
		db = new Db();
		
		
		if(kplain!="")
		{
		Statement stmt2 = db.getStatement();
		SQLRenderer r2 = new SQLRenderer();		
		sql2 =" SELECT COUNT(P.ID_PERMOHONAN) AS JUMLAH_LAIN "
		+" FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P"
		+" WHERE SM.ID_SIMATI = MS.ID_SIMATI"
		+" AND MS.ID_PERMOHONAN = P.ID_PERMOHONAN"
		//+" AND P.ID_PERMOHONAN <> '"+idp+"'"
		+" AND SM.ID_SIMATI <> '"+id_simati+"'"
		+" AND SM.NO_KP_LAIN = '"+kplain+"'"
		+" AND P.ID_STATUS <> '999'"
		+" AND P.ID_STATUS <> '169'";
		
		myLogger.info("CHECK SQL 3:"+sql2.toUpperCase());
		
		ResultSet rs2 = stmt2.executeQuery(sql2);
		Hashtable h2;
		
		if (rs2.next()) {
            if (rs2.getInt("JUMLAH_LAIN") > 0) {
                  a = true;
            }
        }	
		}		
		
	}finally {
		if(db != null) db.close();
		
	}
    //return false;
	return a;
	}
private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
	//hashtable maklumat header
	this.context.put("headerppk",mainheader.getHeaderData(session,id_permohonan,flag_permohonan,id_fail,flag_fail));
	Vector list_sub = null;
	list_sub = mainheader.carianFail(id_permohonan,flag_permohonan,id_fail,flag_fail);		
	this.context.put("list_sub_header",list_sub);
	this.context.put("flag_jenis_vm","ajax");
}
private void headerppk_baru_default()
{
	Hashtable headerppk = null;
	this.context.put("headerppk","");
	this.context.put("list_sub_header","");
	this.context.put("flag_jenis_vm","ajax");
}





}
