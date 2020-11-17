package ekptg.view.ppk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmTukaranStatus;

/*
 * @author
 * Razman
 */

public class FrmNotaBicara extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmTukarStatus.class);	
	FrmTukaranStatus model = new FrmTukaranStatus();
	
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	HttpSession session = null;
	String action = null;
	private final String PATH="app/ppk/";
	private String vm = PATH +"frmNotaBicara.jsp";
	
	public String doTemplate2()  throws Exception{
		
		String idDokumen = getParam("idDokumen");
		String id_notabicara = getParam("id_notabicara");
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String modeUploadDok = getParam("modeUploadDok");
			
		session = request.getSession();
		String command = getParam("command");
		action = getParam("action");
		myLogger.info("[submit] :: " +command);
		
		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
		myLogger.info("check doPost :"+doPost);   
		String usid=(String)session.getAttribute("_ekptg_user_id");
		
		context.put("ScrollX",getParam("ScrollX"));
		context.put("ScrollY",getParam("ScrollY")); 
		
		context.put("listHistoryJana",""); 
		
		
		Vector list_tukar_pemohon = null;
		Vector listNotaBicara = new Vector();
		//Vector listNotaBicara = new Vector();
		Vector dataNotaBicara = new Vector();
		Vector senaraiNotabicara = null;
		Vector beanMaklumatNota = null;
		List listHistoryjana = null;
		dataNotaBicara.clear();
		listNotaBicara.clear();
		   	
		this.context.put("alert_baru_simati_value","");
		this.context.put("alert_lama_simati_value","");
		this.context.put("alert_lain_simati_value","");
		
		default_data();
		
		//reset
		context.put("nofail","");
		context.put("showInput", "");
		context.put("list", "");
		context.put("list_size", 0);
		context.put("dataNotaBicara","");
		context.put("totalWordNotaBicara","0");
		context.put("bayaranNB", "0.00");
		
		String submit = getParam("command");
		String action = getParam("doaction");
		String action2 = getParam("doaction2");
		myLogger.info("[submit] :: " +submit);
		myLogger.info("[action] :: " +action);
		myLogger.info("[action 2] :: " +action2);
	         	
		if ("cariMainSub".equals(command)){
			
			String txtNoFailSub = getParam("txtNoFailSub");    		
			context.put("txtNoFailSub", txtNoFailSub.trim());     		
			context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","","")); 
			context.put("view_list_fail","yes");			
		} 	
		else if ("paparSub".equals(command)){
				
			String txtNoFailSub = getParam("txtNoFailSub");    		
			context.put("txtNoFailSub", txtNoFailSub.trim()); 
			listHistoryjana = listHistoryjana(session, getParam("id_fail_carian"), null);
			context.put("listHistoryJana",listHistoryjana);
			model.setListNota(getParam("id_fail_carian"),usid);
			listNotaBicara = model.getListNotaBicara();
    		String id_perbicaraan = "";
    		if(listNotaBicara.size()!=0){
    			Hashtable x = (Hashtable) listNotaBicara.get(0);
    			id_perbicaraan = x.get("id_perbicaraan").toString();
    		}//close if
    		myLogger.info("id_perbicaraan:"+id_perbicaraan);
    		context.put("list", listNotaBicara);
    		context.put("list_size", id_perbicaraan);
    		
    		    		
    		if(!id_perbicaraan.equals("0") && !id_perbicaraan.equals("")){
    			
    			model.setDataNotaBicara(id_perbicaraan);
	    		dataNotaBicara = model.getDataNotaBicara();
    			
	    		//String id_notabicara = "";
	    		
	    		if(dataNotaBicara.size()!=0){
	    			
	    			String notabicara = "";
	    			Hashtable n = (Hashtable) dataNotaBicara.get(0);
	    			id_notabicara = n.get("id_notabicara").toString();
	    			notabicara = n.get("nota_bicara").toString();
	    			long totalWordNotaBicara = 0;
	    			String kos = "0.00";
	    			double total = 0;
	    			if(notabicara!=""){
	    				totalWordNotaBicara = Utils.wordcount(notabicara);
	    				
	    				if(totalWordNotaBicara <= 100){
        					total = 30.00;
        				}else if(totalWordNotaBicara > 100 && totalWordNotaBicara <= 200){
        					total = 60.00;
        				}else if(totalWordNotaBicara > 200 && totalWordNotaBicara <= 300){
        					total = 90.00;
        				}else if(totalWordNotaBicara > 300 && totalWordNotaBicara <= 400){
        					total = 120.00;
        				}else if(totalWordNotaBicara > 400 && totalWordNotaBicara <= 500){
        					total = 150.00;
        				}else if(totalWordNotaBicara > 500 && totalWordNotaBicara <= 600){
        					total = 180.00;
        				}else if(totalWordNotaBicara > 600 && totalWordNotaBicara <= 700){
        					total = 210.00;
        				}else if(totalWordNotaBicara > 700 && totalWordNotaBicara <= 800){
        					total = 240.00;
        				}else if(totalWordNotaBicara > 800 && totalWordNotaBicara <= 900){
        					total = 270.00;
        				}else if(totalWordNotaBicara > 900 && totalWordNotaBicara <= 1000){
        					total = 300.00;
        				}
        				kos = Utils.format2Decimal(total);
	    			}else{
	    				totalWordNotaBicara = 0;
	    				kos = Utils.format2Decimal(total);
	    			}
	    			
	    			context.put("bayaranNB", kos);
	    			context.put("totalWordNotaBicara", totalWordNotaBicara);
	    			context.put("id_notabicara", id_notabicara);
	    			context.put("mode", "view");
	    			
	    			//*KEMASKINI NOTA BICARA*//
	    			if ("kemaskiniNotaBicara".equals(action)){
	    				context.put("mode", "edit");
	    				
	    				//*UPDATE NOTA BICARA*//
	    				if ("updateNotaBicara".equals(action2)){
		    				context.put("mode", "view");
		    				if (doPost.equals("true")) {
				    			updateNotaBicara(session);
				    		}
		    				model.setDataNotaBicara(id_perbicaraan);
				    		dataNotaBicara = model.getDataNotaBicara();
				    		Hashtable m = (Hashtable) dataNotaBicara.get(0);
				    		notabicara = m.get("nota_bicara").toString();
				    		double totalx = 0;
			    			if(notabicara!=""){
			    				totalWordNotaBicara = Utils.wordcount(notabicara);
			    				
			    				if(totalWordNotaBicara <= 100){
			    					totalx = 30.00;
	            				}else if(totalWordNotaBicara > 100 && totalWordNotaBicara <= 200){
	            					totalx = 60.00;
	            				}else if(totalWordNotaBicara > 200 && totalWordNotaBicara <= 300){
	            					totalx = 90.00;
	            				}else if(totalWordNotaBicara > 300 && totalWordNotaBicara <= 400){
	            					totalx = 120.00;
	            				}else if(totalWordNotaBicara > 400 && totalWordNotaBicara <= 500){
	            					totalx = 150.00;
	            				}else if(totalWordNotaBicara > 500 && totalWordNotaBicara <= 600){
	            					totalx = 180.00;
	            				}else if(totalWordNotaBicara > 600 && totalWordNotaBicara <= 700){
	            					totalx = 210.00;
	            				}else if(totalWordNotaBicara > 700 && totalWordNotaBicara <= 800){
	            					totalx = 240.00;
	            				}else if(totalWordNotaBicara > 800 && totalWordNotaBicara <= 900){
	            					totalx = 270.00;
	            				}else if(totalWordNotaBicara > 900 && totalWordNotaBicara <= 1000){
	            					totalx = 300.00;
	            				}
	            				kos = Utils.format2Decimal(totalx);
			    			}else{
			    				totalWordNotaBicara = 0;
			    				kos = Utils.format2Decimal(totalx);
			    			}
			    			context.put("bayaranNB", kos);
			    			context.put("totalWordNotaBicara", totalWordNotaBicara);
		    				context.put("dataNotaBicara",dataNotaBicara);
		    			}//close update
	    				
	    			}//close kemaskini
	    		}else{
	    			context.put("mode", "new");
	    		}//close else size datanotabicara
	    		
	    		//*SIMPAN NOTA BICARA*//
	    		if ("simpanNotaBicara".equals(action)){
		    		context.put("mode", "view");
	    			if (doPost.equals("true")) {
		    			simpanNotaBicara(session,id_perbicaraan);
		    		}
	    			String notabicara = "";
	    			long totalWordNotaBicara = 0;
	    			String kos = "0.00";
		    		model.setDataNotaBicara(id_perbicaraan);
		    		dataNotaBicara = model.getDataNotaBicara();
		    		if(dataNotaBicara.size()!=0){
		    		Hashtable m = (Hashtable) dataNotaBicara.get(0);
		    		notabicara = m.get("nota_bicara").toString();
		    		}
		    		double total = 0;
	    			if(notabicara!=""){
	    				totalWordNotaBicara = Utils.wordcount(notabicara);
	    				
	    				if(totalWordNotaBicara <= 100){
        					total = 30.00;
        				}else if(totalWordNotaBicara > 100 && totalWordNotaBicara <= 200){
        					total = 60.00;
        				}else if(totalWordNotaBicara > 200 && totalWordNotaBicara <= 300){
        					total = 90.00;
        				}else if(totalWordNotaBicara > 300 && totalWordNotaBicara <= 400){
        					total = 120.00;
        				}else if(totalWordNotaBicara > 400 && totalWordNotaBicara <= 500){
        					total = 150.00;
        				}else if(totalWordNotaBicara > 500 && totalWordNotaBicara <= 600){
        					total = 180.00;
        				}else if(totalWordNotaBicara > 600 && totalWordNotaBicara <= 700){
        					total = 210.00;
        				}else if(totalWordNotaBicara > 700 && totalWordNotaBicara <= 800){
        					total = 240.00;
        				}else if(totalWordNotaBicara > 800 && totalWordNotaBicara <= 900){
        					total = 270.00;
        				}else if(totalWordNotaBicara > 900 && totalWordNotaBicara <= 1000){
        					total = 300.00;
        				}
        				kos = Utils.format2Decimal(total);
	    			}else{
	    				totalWordNotaBicara = 0;
	    				kos = Utils.format2Decimal(total);
	    			}
	    			context.put("bayaranNB", kos);
	    			context.put("totalWordNotaBicara", totalWordNotaBicara);
    				context.put("dataNotaBicara",dataNotaBicara);
    			}//close simpanNotaBicara
	    		
	    		context.put("dataNotaBicara",dataNotaBicara);
	    		context.put("showInput", "yes");
	    		
    		}else{
    			
    				context.put("showInput", "no");
    			
    		}//close else
			
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		myLogger.info("id_fail_carian :"+getParam("id_fail_carian"));
    		
    		  
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
    		
		if(modeUploadDok.equals("upload")){
				muatnaik(hitButton, id_notabicara, idDokumen, flagPopup, modePopup, beanMaklumatNota, senaraiNotabicara);
		}    	

			context.put("modeUploadDok", modeUploadDok);
			
			//MAKLUMAT DOKUMEN
			senaraiNotabicara = new Vector();
			model.setSenaraiNota(id_notabicara);
			senaraiNotabicara = model.getListNota();
			this.context.put("SenaraiNotabicara", senaraiNotabicara);
    		
    		} else{
			default_data();	
		}
	
		this.context.put("id_notabicara", id_notabicara);
		this.context.put("idDokumen", idDokumen);
		this.context.put("command", command);
		this.context.put("hitButton", hitButton);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		
	return vm;
	}
	
	public List listHistoryjana(HttpSession session, String ID_FAIL,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listHistoryjana = null;
		String sql = "";	
		
		try{
			
		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		
		stmt = db1.getStatement();	
		
		/*
		sql += " SELECT J.*,  TO_CHAR(J.TARIKH_MASUK,'DD/MM/YYYY HH24:MI:SS') AS TARIKH_TRANSAKSI_FULL, U.USER_NAME AS PENJANA FROM TBLPPKHISTORYJANANOTA J, USERS U "+
				" WHERE J.ID_MASUK = U.USER_ID(+) AND ID_FAIL IN (SELECT DISTINCT P.ID_FAIL FROM TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN P " +
				" WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN  AND P.ID_FAIL = '"+ID_FAIL+"') ORDER BY J.TARIKH_MASUK DESC ";
		*/
		
		sql += " SELECT J.*,  TO_CHAR(J.TARIKH_MASUK,'DD/MM/YYYY HH24:MI:SS') AS TARIKH_TRANSAKSI_FULL, U.USER_NAME AS PENJANA FROM  "+
				" TBLPPKHISTORYJANANOTA J, USERS U, "+
				" (SELECT MAX(N.TARIKH_MASUK) AS MAX_CETAK, N.ID_PERBICARAAN  FROM TBLPPKHISTORYJANANOTA N GROUP BY N.ID_PERBICARAAN) GETDATE "+
				" WHERE GETDATE.MAX_CETAK = J.TARIKH_MASUK AND  J.ID_MASUK = U.USER_ID(+) AND ID_FAIL = "+ID_FAIL+" ORDER BY J.TARIKH_MASUK DESC ";
		
		myLogger.info(" NOTA BICARA : SQL listHistoryjana :"+ sql);
		
		rs = stmt.executeQuery(sql);
		listHistoryjana = Collections.synchronizedList(new ArrayList());
		
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }			
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("ID_HISTORYJANANOTA",rs == null ? "" :rs.getString("ID_HISTORYJANANOTA") == null ? "" : rs.getString("ID_HISTORYJANANOTA"));
			h.put("ID_PERBICARAAN",rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
			h.put("ID_PERINTAH",rs == null ? "" :rs.getString("ID_PERINTAH") == null ? "" : rs.getString("ID_PERINTAH"));
			h.put("NOTA",rs == null ? "" :rs.getString("NOTA") == null ? "" : rs.getString("NOTA"));
			h.put("ID_FAIL",rs == null ? "" :rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
			h.put("NO_FAIL",rs == null ? "" :rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
			h.put("WAKTU_BICARA",rs == null ? "" :rs.getString("WAKTU_BICARA") == null ? "" : rs.getString("WAKTU_BICARA"));
			h.put("NAMA_PEGAWAI",rs == null ? "" :rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI"));
			h.put("BIL_BICARA",rs == null ? "" :rs.getString("BIL_BICARA") == null ? "" : rs.getString("BIL_BICARA"));
			h.put("TARIKH_TRANSAKSI_FULL",rs == null ? "" :rs.getString("TARIKH_TRANSAKSI_FULL") == null ? "" : rs.getString("TARIKH_TRANSAKSI_FULL"));		
			h.put("PENJANA",rs == null ? "" :rs.getString("PENJANA") == null ? "" : rs.getString("PENJANA"));		
			h.put("NO_PINDAAN",rs == null ? "" :rs.getString("NO_PINDAAN") == null ? "" : rs.getString("NO_PINDAAN"));		
			listHistoryjana.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		
		
		return listHistoryjana;
	}
	
private void muatnaik(String hitButton, String id_notabicara, String idDokumen, String flagPopup, String modePopup,
			Vector beanMaklumatNota, Vector senaraiNotabicara) throws Exception{
		
		if ("simpanNota".equals(hitButton)) {
	   		 uploadFiles(id_notabicara, session);
	   	}
     	if ("simpanKemaskiniNota".equals(hitButton)) {
			model.simpanKemaskiniNota(idDokumen, getParam("txtNamaDokumen"), session);
		}
     	if ("hapusNota".equals(hitButton)) {
			model.hapusNota(idDokumen);
		}
   		
		 if("openDokumen".equals(flagPopup)){
    		
    		if ("new".equals(modePopup)){
    			
    			this.context.put("readonlyPopup", "");
	   			this.context.put("inputTextClassPopup", "");
	   			
	   			beanMaklumatNota = new Vector();    			
	   			Hashtable hashMaklumatNota = new Hashtable();
	   			hashMaklumatNota.put("namaDokumen", "");
	   			beanMaklumatNota.addElement(hashMaklumatNota);
				this.context.put("BeanMaklumatNota", beanMaklumatNota);
   			
    		} else if ("update".equals(modePopup)){
    			
    			this.context.put("readonlyPopup", "");
    			this.context.put("inputTextClassPopup", "");
   			
	   			//MAKLUMAT DOKUMEN
	   			beanMaklumatNota = new Vector();
				model.setMaklumatNota(idDokumen);
				beanMaklumatNota = model.getBeanMaklumatNota();
				this.context.put("BeanMaklumatNota", beanMaklumatNota);
   			
    		} else if ("view".equals(modePopup)){
    			
    			this.context.put("readonlyPopup", "readonly");
    			this.context.put("inputTextClassPopup", "disabled");
   			
	   			//MAKLUMAT DOKUMEN
	   			beanMaklumatNota = new Vector();
				model.setMaklumatNota(idDokumen);
				beanMaklumatNota = model.getBeanMaklumatNota();
				this.context.put("BeanMaklumatNota", beanMaklumatNota);
				
    		}
    	}
   		 

		
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
	this.context.put("headerppk", mainheader.getHeaderData(session,
			id_permohonan, flag_permohonan, id_fail, flag_fail));
	
	this.context.put("getPemohonData", mainheader.getPemohonData(session,id_permohonan, null));

	
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
	this.context.put("flag_jenis_vm", "utiliti_ajax");
}


	public void genaratePemohonBaru(String id_ob,String id_permohonansimati,String id_permohonan,HttpSession session,String ekptg_user_id)
	throws Exception{
	    Db db = null;
	    Connection conn = null;
	    String getNoFile;
	    String sql = "";
	    String getsql = "";
	    String getPemohon = "";
	    String sqlSelect = "";
	    String sqlInsert = "";
	   
	    try {
	    	
		      db = new Db();
	          conn = db.getConnection();
	          conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
		      String id_pemohon_asal = "";
		      String id_pemohon_permohonan = "";
		      
		      getPemohon = " SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan+"' ";
		      myLogger.info("-------1:"+getPemohon);  
		      ResultSet rs1 = stmt.executeQuery(getPemohon);
		        
				while (rs1.next()) {
					
					if (rs1.getString("ID_PEMOHON") == null) {
						id_pemohon_permohonan = "";
					} else {
						id_pemohon_permohonan = rs1.getString("ID_PEMOHON");						
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
		      
		      sql = "UPDATE TBLPPKPERMOHONAN SET ID_PEMOHON = '"+id_pemohon_baru+"' " +
		      		" WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
		      myLogger.info("-------7:"+sql);
			  stmt.executeUpdate(sql);
			  
			  sql = "UPDATE TBLPPKOBPERMOHONAN SET ID_PEMOHON = '"+id_pemohon_baru+"' " +
			  		" WHERE ID_OB = '"+id_ob+"' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"'";
			  myLogger.info("-------8:"+sql);
			  stmt.executeUpdate(sql);
			  
			  sql = "UPDATE TBLPPKOB SET ID_PEMOHON = '"+id_pemohon_baru+"' " +
		  		" WHERE ID_OB = '"+id_ob+"' ";
			  myLogger.info("-------9:"+sql);
			  stmt.executeUpdate(sql);
			  
			  		      
		      sqlInsert = " INSERT INTO TBLPPKPEMOHON ( "+
			    " ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU,NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,UMUR, JANTINA, JENIS_AGAMA, "+
			    " JENIS_WARGA, ALAMAT_1, ALAMAT_2,ALAMAT_3, BANDAR, ID_BANDAR,POSKOD, NO_HP, NO_TEL, "+
			    " EMEL, NO_FAX, CATATAN,ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI,STATUS_PEGUAM, STATUS_PEMOHON, ALAMAT1_SURAT, "+
			    " ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, "+
			    " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, "+
			    " ID_NEGERISURAT, ID_PEMOHONLAMA, ID_ARB) ";
			    sqlSelect = " SELECT '"+id_pemohon_baru+"',NAMA_OB,NO_KP_BARU,NO_KP_LAMA,JENIS_KP,NO_KP_LAIN,UMUR,JANTINA," +
			    		"JENIS_AGAMA,JENIS_WARGA,ALAMAT_1,ALAMAT_2,ALAMAT_3,BANDAR,ID_BANDAR,POSKOD," +
			    		"NO_HP,NO_TEL,'',NO_FAX,'',ID_TARAFKPTG,ID_SAUDARA,ID_NEGERI,'','',ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, " +
			    		"ID_BANDARSURAT, POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT,'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,''," +
			    				"BANDAR_SURAT,ID_NEGERISURAT,'',ID_ARB FROM TBLPPKOBPERMOHONAN " +
			    				" WHERE ID_OB = '"+id_ob+"' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' ";
			    myLogger.info("-------10:"+sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);
			  }    
	    conn.commit();
	}
	catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:"+se2.getMessage());
		}
		throw new Exception("Ralat Pindah Fail:"+se.getMessage());
	}
	finally {
	  if (db != null) db.close();
	}
	
	
	}

	
	private void simpanNotaBicara(HttpSession session,String id_perbicaraan) throws Exception{
		   
		Hashtable h = new Hashtable();
	
		String notaBicara = getParam("txtNotaBicara");
		
		h.put("notaBicara", notaBicara);
		h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
		h.put("id_perbicaraan", id_perbicaraan);
		
		model.simpanNotaBicara(h);
		
	}//close simpanNotaBicara
	
	private void updateNotaBicara(HttpSession session) throws Exception{
		   
		Hashtable h = new Hashtable();
	
		String notaBicara = getParam("txtNotaBicara");
		String id_notabicara = getParam("id_notabicara");
		
		h.put("notaBicara", notaBicara);
		h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
		h.put("id_notabicara", id_notabicara);
		
		model.updateNotaBicara(h);
		
	}//close simpanNotaBicara
	
	// UPLOAD FILE
				private void uploadFiles(String id_notabicara , HttpSession session) throws Exception {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					boolean isMultipart = ServletFileUpload.isMultipartContent(request);
					if (isMultipart != false) {
						List items = upload.parseRequest(request);
						Iterator itr = items.iterator();
						while (itr.hasNext()) {
							FileItem item = (FileItem) itr.next();
							if ((!(item.isFormField())) && (item.getName() != null)
									&& (!("".equals(item.getName())))) {
								if(item.getSize()<500000000L)
									saveData(item , id_notabicara , session);
								else
									this.context.put("limitExceed", true);						
							}
						}
					}
				}
				
				
				private void saveData(FileItem item,String id_notabicara , HttpSession session) throws Exception {

					Db db = null;
					String userId = (String) session.getAttribute("_ekptg_user_id");
					
					try {
						db = new Db();

						// TBLPPKDOKUMEN
						long idDokumen = DB.getNextID("TBLPPKDOKUMEN_SEQ");
						Connection con = db.getConnection();
						con.setAutoCommit(false);
						PreparedStatement ps = con
								.prepareStatement("INSERT INTO TBLPPKDOKUMEN "
										+ "(ID_DOKUMEN,NAMA_DOKUMEN,ID_MASUK,TARIKH_MASUK,KANDUNGAN,FORMAT) "
										+ "VALUES(?,?,?,SYSDATE,?,?)");
						ps.setLong(1, idDokumen);
						ps.setString(2, getParam("namaDokumen"));
						ps.setString(3, userId);
						ps.setBinaryStream(4, item.getInputStream(), (int) item.getSize());
						ps.setString(5, item.getContentType());
						ps.executeUpdate();
						
						// TBLPPKUPLOADDOKUMEN
						long idUploadnotabicara = DB.getNextID("TBLPPKUPLOADNOTABICARA_SEQ");
						PreparedStatement ps1 = con
								.prepareStatement("INSERT INTO TBLPPKUPLOADNOTABICARA"
										+ "(ID_UPLOADNOTABICARA,ID_DOKUMEN,ID_NOTABICARA,ID_MASUK,TARIKH_MASUK) "
										+ "VALUES(?,?,?,?,SYSDATE)");
						ps1.setLong(1, idUploadnotabicara);
						ps1.setLong(2, idDokumen);
						ps1.setString(3, id_notabicara);
						ps1.setString(4, userId);
						ps1.executeUpdate();


						con.commit();
						
					} finally {
						if (db != null)
							db.close();
					}
					this.context.put("completed", true);
				}
		
	

}
