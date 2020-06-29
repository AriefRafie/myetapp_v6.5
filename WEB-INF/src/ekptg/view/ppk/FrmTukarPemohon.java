package ekptg.view.ppk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmTukaranStatus;
/*
 * @author
 * Razman
 */

@SuppressWarnings("serial")
public class FrmTukarPemohon extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmTukarStatus.class);	
	FrmTukaranStatus model = new FrmTukaranStatus();
	
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	HttpSession session = null;
	String action = null;
	private final String PATH="app/ppk/";
	private String vm = PATH +"frmTukarPemohon.jsp";

	 
	
	public String doTemplate2()  throws Exception{
		session = request.getSession();
		String command = getParam("command");
		String idatheader = getParam("id_permohonansimati_atheader");
		//String sebab = getParam("sebab");
		action = getParam("action");
    	myLogger.info("[submit] :: " +command);
		String doPost = (String) session.getAttribute("doPost");
		myLogger.info("check doPost :"+doPost);
		myLogger.info("idatheader :" + idatheader); 
		String usid=(String)session.getAttribute("_ekptg_user_id");
		
		context.put("ScrollX",getParam("ScrollX"));
    	context.put("ScrollY",getParam("ScrollY")); 
		
		Vector list_tukar_pemohon = null;
    	Vector IDSimati = null;
    	this.context.put("alert_baru_simati_value","");
    	this.context.put("alert_lama_simati_value","");
    	this.context.put("alert_lain_simati_value","");
    	
    	myLogger.info("idatheader :"+idatheader);  
    	default_data();
    	myLogger.info("idatheader4 :"+idatheader);  
    	if (!"".equals(idatheader))
    	{
    		IDSimati = model.IDpermohonanSimati(getParam("id_permohonansimati_atheader"));
    	}
    	
		if ("cariMainSub".equals(command)){
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());     		
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","","")); 
    		context.put("view_list_fail","yes");
    		}
		else if ("paparSub".equals(command)){
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		
    		myLogger.info("id_fail_carian :"+getParam("id_fail_carian"));
    		
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
    		
    		headerppk_baru(session, id_permohonan, "Y", "", "T");
    		
    		model.setListSub(getParam("id_fail_carian"));    		
    		context.put("list_fail",model.search_nofail(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")+"",usid,"","",""));
    		context.put("view_list_fail","yes");
    		context.put("view_list_myid","yes");
    		
    		list_tukar_pemohon = model.list_tukar_pemohon(getParam("id_fail_carian"));
        	
        	context.put("list_tukar_pemohon",list_tukar_pemohon); 
    		
    	}
		else if ("simpanSub".equals(command)){
			myLogger.info("Baca simpanSub0:----------------" + (getParam("id_fail_carian")));
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		myLogger.info("Baca simpanSub0aaaa:----------------");
    		String txtNoFailSub = getParam("txtNoFailSub");
    		myLogger.info("Baca simpanSub0bbbb:----------------");
    		context.put("txtNoFailSub", txtNoFailSub.trim());    		
    		myLogger.info("Baca simpanSub0cccc:----------------");
    		model.setListSub(getParam("id_fail_carian"));    		
    		myLogger.info("Baca simpanSub0dddd:----------------");
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","",""));
    		context.put("view_list_fail","yes");
    		context.put("view_list_myid","yes");  
    		myLogger.info("Baca simpanSub0eeeee:----------------");
    		list_tukar_pemohon = model.list_tukar_pemohon(getParam("id_fail_carian"));
          
           	String id_permohonan ="";
           	myLogger.info("Baca simpanSub1:----------------"); 
           	if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
           		myLogger.info("Baca simpanSub2:----------------"); 
           		id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    	
    		if(!getParam("id_ob_pemohon").equals(""))
    		{
    			myLogger.info("Baca simpanSub3:----------------"); 
    			myLogger.info("id_permohonansimati_atheader = " + getParam("id_permohonansimati_atheader")); 
    			genaratePemohonBaru(getParam("id_ob_pemohon"),getParam("id_permohonansimati_atheader"),id_permohonan,session,usid, getParam("idsimati"));
    		}
    		
           	headerppk_baru(session, id_permohonan, "Y", "", "T");
        	list_tukar_pemohon = null;        
        	list_tukar_pemohon = model.list_tukar_pemohon(getParam("id_fail_carian"));
        	context.put("list_tukar_pemohon",list_tukar_pemohon); 
        	context.put("IDSimati",IDSimati); 
    		
    	}
		// syafiqah add 24/6/2020
		else if("onlineSub".equals(command)){
			String id_permohonan ="";
			
			myLogger.info("Baca simpanSub3:----------------"); 
			myLogger.info("id_permohonansimati_atheader = " + getParam("id_permohonansimati_atheader")); 
			// genaratePemohonBaru(getParam("id_ob_pemohon"),getParam("id_permohonansimati_atheader"),id_permohonan,session,usid, getParam("idsimati"));
			headerppk_baru(session, id_permohonan, "Y", "", "T");
        	list_tukar_pemohon = null;        
        	list_tukar_pemohon = model.list_tukar_pemohon(getParam("id_fail_carian"));
        	context.put("list_tukar_pemohon",list_tukar_pemohon); 
        	context.put("IDSimati",IDSimati);
		}
		// syafiqah add ends
		else
		{
			default_data();	
		}
	
		
		//context.put("IDSimati",IDSimati); 	
	return vm;
	}
	
	private void default_data()throws Exception{
		context.put("view_list_fail","");
		context.put("txtNoFailSub",""); 
		context.put("list_fail",""); 
		context.put("id_fail_carian",""); 
		context.put("view_list_myid","");  
		context.put("list_tukar_pemohon",""); 
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
private void headerppk_baru(HttpSession session, String id_permohonan,
		String flag_permohonan, String id_fail, String flag_fail)
		throws Exception {
	// hashtable maklumat header
	myLogger.info("Read Here666************");
	this.context.put("headerppk", mainheader.getHeaderData(session,
			id_permohonan, flag_permohonan, id_fail, flag_fail));
	
	this.context.put("getPemohonData", mainheader.getPemohonData(session,id_permohonan, null));

	
	this.context.put("getPemohonData2", mainheader.getPemohonData2(session,
			id_permohonan));
	
	Vector list_sub = null;
	list_sub = mainheader.carianFail(id_permohonan, flag_permohonan,
			id_fail, flag_fail);
	this.context.put("list_sub_header", list_sub);
	this.context.put("flag_jenis_vm", "utiliti_ajax");
}

private void headerppk_baru_default() {
	Hashtable headerppk = null;
	this.context.put("headerppk", "");
	this.context.put("list_sub_header", "");
	this.context.put("getPemohonData", "");
	this.context.put("getPemohonData2", "");
	this.context.put("flag_jenis_vm", "utiliti_ajax");
}

// Edited by Salnizam Starts


private void uploadFiles(Db db,Connection conn, String nama_pemohon_lama) throws Exception {
	myLogger.info("Baca uploadFiles:--------------"); 
	String nama_pemohon_lama2 = nama_pemohon_lama;
	DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    List items = upload.parseRequest(this.request);
    Iterator itr = items.iterator();	   
    while (itr.hasNext()) {    	
      FileItem item = (FileItem)itr.next();
      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
    	  System.out.println("item.getName = "+ item.getName());
    	  saveData(item,db,conn,nama_pemohon_lama2);
      }
    }
  }


private void saveData(FileItem item,Db db,Connection conn, String nama_pemohon_lama2) throws Exception {
		//Db db = null;
	
    try {
    	db = new Db();

    	Connection con = db.getConnection();
    	con.setAutoCommit(false);
    	String nama_pemohon_lama3 = nama_pemohon_lama2;
    	String id_permohonansimati = getParam("id_permohonansimati_atheader");
    	PreparedStatement ps = con.prepareStatement("UPDATE TBLPPKTUKARPEMOHON SET bukti = ?, content = ?, jenis_Mime = ? WHERE (ID_PERMOHONANSIMATI = ? AND NAMA_PEMOHONLAMA = ?)");		
    	System.out.println("+nama_pemohon_lama3+ " + nama_pemohon_lama3);
    	System.out.println(con.prepareStatement("UPDATE TBLPPKTUKARPEMOHON SET bukti = ?, content = ?, jenis_Mime = ? WHERE ID_PERMOHONANSIMATI = ?"));
    	ps.setString(1,item.getName());
    	ps.setBinaryStream(2,item.getInputStream(),(int)item.getSize());
    	ps.setString(3,item.getContentType());
    	//System.out.println("item.getInputStream = "+ item.getInputStream());
    	//System.out.println("item.getSize = "+ item.getSize());
    	//System.out.println("item.getContentType = "+ item.getContentType());
    	ps.setString(4,id_permohonansimati);
    	ps.setString(5,nama_pemohon_lama3);
    	//ps.setString(4,getParam("id_permohonansimati_atheader"));
    	myLogger.info("Baca SaveData:---------------"); 
    	ps.executeUpdate();	
    	myLogger.info("Baca SaveData 2:---------------"); 
        con.commit();
    } finally {
	      if (db != null) db.close();
    }
}


//Edited by Salnizam Ends


	public void genaratePemohonBaru(String id_ob,String id_permohonansimati,String id_permohonan,HttpSession session,String ekptg_user_id, String idsimati)
	throws Exception{
	    Db db = null;
	    Connection conn = null;
	    String getNoFile;
	    String sql = "";
	    String getsql = "";
	    String getPemohon = "";
	    String sqlSelect = "";
	    String sqlInsert = "";
	    String sqlInsertTblTukarPemohon = "";
	    String sebab = getParam("sebab");
	    String fileupload = getParam("fileupload");
	    myLogger.info("[sebab] :: " +sebab);
	    String sqlSalnizam = "";
	    String sqlidPermohonanSimatitoidSimati ="";
	    System.out.println("fileupload = " + fileupload);	
	    try {
	    	
		      db = new Db();
	          conn = db.getConnection();
	          conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
		      String id_pemohon_asal = "";
		      String id_pemohon_permohonan = "";
		      
		      String nama_pemohon_lama = "";
		      String kp_baru_pemohon_lama = "";
		      String kp_lama_pemohon_lama = "";
		      String kp_lain_pemohon_lama = "";
		      String jeniskp_lain_pemohon_lama = "";
		      
		      //getPemohon = " SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan+"' ";
		      getPemohon = " SELECT P.ID_PEMOHON,PM.NAMA_PEMOHON,PM.NO_KP_BARU,PM.NO_KP_LAMA,PM.NO_KP_LAIN,PM.JENIS_KP  " +
		      		" FROM TBLPPKPERMOHONAN P,TBLPPKPEMOHON PM WHERE P.ID_PERMOHONAN = '"+id_permohonan+"' " +
		      		" AND P.ID_PEMOHON = PM.ID_PEMOHON" ;
		      myLogger.info("-------1:"+getPemohon);  
		      ResultSet rs1 = stmt.executeQuery(getPemohon);
		        
				while (rs1.next()) {
					
					if (rs1.getString("NAMA_PEMOHON") == null) {
						myLogger.info("NAMA_PEMOHON "); 
						nama_pemohon_lama = "";
					} else {
						nama_pemohon_lama = rs1.getString("NAMA_PEMOHON");	
						myLogger.info("NAMA_PEMOHON 2 = " + rs1.getString("NAMA_PEMOHON"));
					}
					
					if (rs1.getString("NO_KP_BARU") == null) {
						kp_baru_pemohon_lama = "";
					} else {
						kp_baru_pemohon_lama = rs1.getString("NO_KP_BARU");						
					}
					
					if (rs1.getString("NO_KP_LAMA") == null) {
						kp_lama_pemohon_lama = "";
					} else {
						kp_lama_pemohon_lama = rs1.getString("NO_KP_LAMA");						
					}
					
					if (rs1.getString("NO_KP_LAIN") == null) {
						kp_lain_pemohon_lama = "";
					} else {
						kp_lain_pemohon_lama = rs1.getString("NO_KP_LAIN");						
					}
					
					if (rs1.getString("JENIS_KP") == null) {
						jeniskp_lain_pemohon_lama = "";
					} else {
						jeniskp_lain_pemohon_lama = rs1.getString("JENIS_KP");						
					}
					
					if (rs1.getString("ID_PEMOHON") == null) {
						id_pemohon_permohonan = "";
						myLogger.info("ID_PEMOHON "); 
					} else {
						id_pemohon_permohonan = rs1.getString("ID_PEMOHON");	
						myLogger.info("ID_PEMOHON 2 = " + rs1.getString("ID_PEMOHON")); 
					}
					
				}
		     
				 sql = "UPDATE TBLPPKOBPERMOHONAN SET ID_PEMOHON = '' " +
			  		" WHERE ID_PEMOHON= '"+id_pemohon_permohonan+"' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"'";
				 myLogger.info("-------2:"+sql); 
				 stmt.executeUpdate(sql);
				  
				  sql = "UPDATE TBLPPKOB SET ID_PEMOHON = '' " +
			  		" WHERE ID_PEMOHON= '"+id_pemohon_permohonan+"'";
				  myLogger.info("-------3:"+sql); 
				  stmt.executeUpdate(sql);
				  
				 
				  

							  
		      getsql = " SELECT ID_PEMOHON,NAMA_OB,NO_KP_BARU,NO_KP_LAMA,JENIS_KP,NO_KP_LAIN,UMUR,JANTINA," +
	    		"JENIS_AGAMA,JENIS_WARGA,ALAMAT_1,ALAMAT_2,ALAMAT_3,BANDAR,ID_BANDAR,POSKOD," +
	    		"NO_HP,NO_TEL,NO_FAX,ID_TARAFKPTG,ID_SAUDARA,ID_NEGERI,ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, " +
	    		"ID_BANDARSURAT, POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT,'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,''," +
	    				"BANDAR_SURAT,ID_NEGERISURAT,'',ID_ARB FROM TBLPPKOBPERMOHONAN " +
	    				" WHERE ID_OB = '"+id_ob+"' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' ";
		      myLogger.info("-------3:"+getsql);   
		      ResultSet rs = stmt.executeQuery(getsql);
				while (rs.next()) {
					Hashtable h = new Hashtable();
					
					if (rs.getString("ID_PEMOHON") == null) {
						id_pemohon_asal = "";
					} else {
						id_pemohon_asal = rs.getString("ID_PEMOHON");						
					}
				}
		      
			  if(!id_pemohon_asal.equals(""))
			  {
				  
				  sql = "UPDATE TBLPPKOBPERMOHONAN SET ID_PEMOHON = '"+id_pemohon_asal+"' " +
			  		" WHERE ID_OB = '"+id_ob+"' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"'";
				  myLogger.info("-------4:"+sql); 
				  stmt.executeUpdate(sql);
				  
				  sql = "UPDATE TBLPPKOB SET ID_PEMOHON = '"+id_pemohon_asal+"' " +
			  		" WHERE ID_OB = '"+id_ob+"' ";
				  myLogger.info("-------5:"+sql);
				  stmt.executeUpdate(sql);
				  
				  			 			  
				  sql = "UPDATE TBLPPKPERMOHONAN SET ID_PEMOHON = '"+id_pemohon_asal+"' " +
				  		" WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
				  myLogger.info("-------6:"+sql);
				  stmt.executeUpdate(sql);				  
			  }
			  else
			  {
		      long id_pemohon_baru =  DB.getNextID(db,"TBLPPKPEMOHON_SEQ");
		      String pemohon_lama = null;
		      String pemohon_baru = "";
		      String idSimati = "";
			  
			  sql = "UPDATE TBLPPKOBPERMOHONAN SET ID_PEMOHON = '"+id_pemohon_baru+"' " +
			  		" WHERE ID_OB = '"+id_ob+"' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"'";
			  myLogger.info("-------8:"+sql);
			  stmt.executeUpdate(sql);
			  
			  sql = "UPDATE TBLPPKOB SET ID_PEMOHON = '"+id_pemohon_baru+"' " +
		  		" WHERE ID_OB = '"+id_ob+"' ";
			  myLogger.info("-------9:"+sql);
			  stmt.executeUpdate(sql);
			 
			  //Edit by Salnizam Starts
			  
			 
			  
			//  sqlNamaPemohonBaru = "SELECT "
			  sqlSalnizam = "SELECT NAMA_OB, ID_PEMOHON FROM TBLPPKOBPERMOHONAN WHERE ID_PERMOHONANSIMATI LIKE '"+id_permohonansimati+"'";
			  myLogger.info("-------18:"+sqlSalnizam);
			  ResultSet rs3 = stmt.executeQuery(sqlSalnizam);
			  
			  while (rs3.next()) {
					//Hashtable h3 = new Hashtable();
					
					if (rs3.getString("ID_PEMOHON") == null) {
						pemohon_lama = rs3.getString("NAMA_OB");
						System.out.println("Pemohon lama = " + pemohon_lama);
					} else {
						pemohon_baru = rs3.getString("NAMA_OB");
						System.out.println("Pemohon baru = " + pemohon_baru);
					}
				}
			  
			//  sqlNamaPemohonBaru = "SELECT "
			  sqlidPermohonanSimatitoidSimati = "SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI = "+id_permohonansimati;
			  myLogger.info("-------18A:"+sqlidPermohonanSimatitoidSimati);
			  ResultSet rs3A = stmt.executeQuery(sqlidPermohonanSimatitoidSimati);
			  
			  while (rs3A.next()) {
					//Hashtable h3 = new Hashtable();
					
					if (rs3A.getString("ID_SIMATI") != null) {
						idSimati = rs3A.getString("ID_SIMATI");
						System.out.println("ID_SIMATI = " + idSimati);
					} 
				}
			  
			 //
			  myLogger.info("id_permohonansimati = "+id_permohonansimati);
			  
			  //razman comment
			  /*
			  sqlInsertTblTukarPemohon = " INSERT INTO TBLPPKTUKARPEMOHON (ID_PERMOHONANSIMATI, ID_SIMATI, ID_PEMOHONLAMA, ID_PEMOHONBARU, " +
			  		"SEBAB_TUKAR, NAMA_PEMOHONLAMA, NAMA_PEMOHONBARU, TARIKH_PERTUKARAN) VALUES ("+id_permohonansimati+", "+idSimati+", "+id_pemohon_permohonan+", "+id_pemohon_baru+", '"+sebab+"', '"+nama_pemohon_lama+"', '"+pemohon_baru+"', sysdate)";
			  */
			  
			  //to avaid SQL injection
			  	SQLRenderer r = new SQLRenderer();
			    r.add("ID_PERMOHONANSIMATI", id_permohonansimati);
			    r.add("ID_SIMATI", idSimati);
			    r.add("ID_PEMOHONLAMA", id_pemohon_permohonan);
			    r.add("ID_PEMOHONBARU", id_pemohon_baru);
			    r.add("SEBAB_TUKAR", sebab);
			    r.add("NAMA_PEMOHONLAMA", nama_pemohon_lama);
			    r.add("NAMA_PEMOHONBARU", pemohon_baru);
			    r.add("TARIKH_PERTUKARAN", r.unquote("sysdate"));
				sqlInsertTblTukarPemohon = r.getSQLInsert("TBLPPKTUKARPEMOHON");	
			  
			  
		  //    sql2 = "Insert into tblsemakanhantar (ID_SEMAKANHANTAR, ID_SEMAKANSENARAI, ID_PERMOHONAN, CATATAN, ID_MASUK, TARIKH_MASUK) values ("+idsemakanhantar+","+cbAlasan+","+idPermohonan+"," +
			//      		"'"+alasanLain+"',"+idmasuk+",sysdate)";
						 myLogger.info("-------19:"+sqlInsertTblTukarPemohon); 
						 stmt.executeUpdate(sqlInsertTblTukarPemohon);
						  
						// if(!fileupload.equals("")){   
			              //System.out.println("fileupload = " + fileupload);	
						  //uploadFiles();
			            /*
						 db = null; 
			              db = new Db();
			               conn = db.getConnection();
							conn.setAutoCommit(false);
							try {
								System.out.println("conn.commit AAAAA");	
								conn = db.getConnection();
								System.out.println("conn.commit BBBBB"); */
						 		conn.commit();		
						 		uploadFiles(db,conn,nama_pemohon_lama);
							//	conn.commit();
								System.out.println("conn.commit CCCCC");	
						/*	  }catch (SQLException se) { 
							  	try {
							  		conn.rollback();
							  	} catch (SQLException se2) {
							  		throw new Exception("Rollback error:"+se2.getMessage());
							  	}
							  	throw new Exception("Ralat : Masalah penyimpanan data 2");
							  }
							context.put("completed",true);
						 
					 // }	
					  * */	  
	          // Edit by Salnizam Ends
			  
			  
			
		      sqlInsert = " INSERT INTO TBLPPKPEMOHON ( "+
			    " ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU,NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,UMUR, JANTINA, JENIS_AGAMA, "+
			    " JENIS_WARGA, ALAMAT_1, ALAMAT_2,ALAMAT_3, BANDAR, ID_BANDAR,POSKOD, NO_HP, NO_TEL, "+
			    " EMEL, NO_FAX, CATATAN,ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI,STATUS_PEGUAM, STATUS_PEMOHON, ALAMAT1_SURAT, "+
			    " ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, "+
			    " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, "+
			    " ID_NEGERISURAT, ID_PEMOHONLAMA, ID_ARB," +
			    " NAMA_PEMOHON_LAMA, NO_KP_BARU_LAMA, NO_KP_LAMA_LAMA, NO_KP_LAIN_LAMA,JENIS_KP_LAMA) ";
			    sqlSelect = " SELECT '"+id_pemohon_baru+"',NAMA_OB,NO_KP_BARU,NO_KP_LAMA,JENIS_KP,NO_KP_LAIN,UMUR,JANTINA," +
			    		"JENIS_AGAMA,JENIS_WARGA,ALAMAT_1,ALAMAT_2,ALAMAT_3,BANDAR,ID_BANDAR,POSKOD," +
			    		"NO_HP,NO_TEL,'',NO_FAX,'',ID_TARAFKPTG,ID_SAUDARA,ID_NEGERI,'','',ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, " +
			    		"ID_BANDARSURAT, POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT,'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,''," +
			    				"BANDAR_SURAT,ID_NEGERISURAT,'',ID_ARB," +
			    				" '"+nama_pemohon_lama+"','"+kp_baru_pemohon_lama+"','"+kp_lama_pemohon_lama+"','"+kp_lain_pemohon_lama+"','"+jeniskp_lain_pemohon_lama+"' " +
			    				" FROM TBLPPKOBPERMOHONAN " +
			    				" WHERE ID_OB = '"+id_ob+"' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' ";
			    myLogger.info("-------10:"+sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);
			    
			    
			    sql = "UPDATE TBLPPKPERMOHONAN SET ID_PEMOHON = '"+id_pemohon_baru+"' " +
	      		" WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
	      myLogger.info("-------7:"+sql);
		  stmt.executeUpdate(sql);
		  
		  
			  }    
	    conn.commit();
	}
	catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:"+se2.getMessage());
		}
		throw new Exception("Ralat Tukar Pemohon:"+se.getMessage());
	}
	finally {
	  if (db != null) db.close();
	}
	
	
	}


}
