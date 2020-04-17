package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek8BorangFData;
import ekptg.model.ppt.FrmUPTSek8NotisAwamData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmUPTSek8NotisAwam extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmUPTSek8NotisAwam.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUPTSek8BorangFData modelBorangE = new FrmUPTSek8BorangFData();
	FrmUPTSek8NotisAwamData model = new FrmUPTSek8NotisAwamData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	//helper
    	context.put("Util", new lebah.util.Util());
    	context.put("Utils", new ekptg.helpers.Utils());
    	
    	String vm = "", noLOT = "", idpegawai = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector dataMaklumatTanahTerperinci = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector dataBorangE = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataMaklumatTanah = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	
    	dataSuburusanHakmilik.clear();
    	dataMaklumatTanah.clear();
    	getIdSuburusanstatusfail.clear();
    	dataBorangE.clear();
    	listMaklumatTanah.clear();
    	dataMaklumatTanahTerperinci.clear();
    	listPageDepan.clear();
   	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user);
    	
    	//screen jsp
    	String listdepan = "app/ppt/frmUPTSek8NotisAwamSenarai.jsp";
    	String screenListNotis = "app/ppt/frmUPTSek8NotisAwamList.jsp";
    	//String mainscreen = "app/ppt/frmUPTSek8NotisAwam.jsp";
    	String mainscreenBulk = "app/ppt/frmUPTSek8NotisAwamBulk.jsp";
    	
    	
    	//razman comment, dah tak perlu menda ni
    	/*
    	Db dby = null;
		try {
		dby = new Db();	  
			
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik",dby)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT",dby);
	    	}
	    	
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupUploadDokumen",dby)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupUploadDokumen","Skrin Senarai Dokumen PPT", "EKPTG - PPT",dby);
	    	}
	    	
	    	
	    	
	    	checkFieldWujud("TBLPPTDOKUMEN","JENIS_DOKUMEN","varchar2(50)",dby);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_HAKMILIK","NUMBER",dby);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_NOTISAWAM","NUMBER",dby);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_BUKTIPENYAMPAIAN","NUMBER",dby);   
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_BORANGH","NUMBER",dby);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_BORANGK","NUMBER",dby);
	    	
	    	
		}finally {
			if (dby != null)
			dby.close();
		}
    	*/
    	
    	//default list fail
    	//listPageDepan = model.getListPermohonan(userIdNeg);

    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
    	//tabbed
    	String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {selectedTab = "0";}
    	
		//paging
		/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){context.put("paging", getParam("paging"));}else{context.put("paging", "14");}
    	*/
    	context.put("paging", "14");
    	
		//header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
		String flag_subjaket = "";
    	String idpermohonan = getParam("id_permohonan");
    	
    	if(!idpermohonan.equals(""))
    	{
	    	header.setDataHeader(idpermohonan);
			Vector dataHeader = header.getDataHeader();
			context.put("dataHeader", dataHeader);
			if(dataHeader.size()!=0){
				Hashtable dh = (Hashtable) dataHeader.get(0);
				id_status = (String)dh.get("id_status");
				flagSegera = dh.get("flag_segera").toString();
				id_fail = (String)dh.get("id_fail");
				flag_subjaket = dh.get("flag_subjaket").toString();
				
				Vector list_sub = null;
				list_sub = header.listPerjalananFail(idpermohonan);
				this.context.put("list_sub_header", list_sub);
			}
    	}

		context.put("flag_subjaket",flag_subjaket);
		context.put("flagSegera",flagSegera);
		
		
		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		if(!idpermohonan.equals(""))
    	{
			modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
			getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
			if(getIdSuburusanstatusfail.size()!=0){
				Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
				id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
			}
    	}
		
		
		//header hakmilik
		String idHakmilik = getParam("id_hakmilik");

		//get size suburusanhakmilik
		//String id_suburusanstatushakmilik = "";
		//String id_suburusanstatus = "";
//		modelUPT.setDataSuburusanHakmilik(idHakmilik);
//		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
//		if(dataSuburusanHakmilik.size()!=0){
//			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
//			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
//		}
		
		//data hakmilik
		if(!idHakmilik.equals(""))
		{
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		}
		
		String tarikh_cetak_borange = "";
		String tarikh_siasatan = "";
		String tarikh_akhir_tampal = "";
		if(!idHakmilik.equals(""))
		{
			modelBorangE.setDataBorangEInBulk("",idHakmilik);
	 		dataBorangE = modelBorangE.getDataBorangEInBulk();
			if(dataBorangE.size()!=0){
				Hashtable dbe = (Hashtable)dataBorangE.get(0);
				tarikh_cetak_borange = (String)dbe.get("tarikh_cetak");
				tarikh_siasatan = (String)dbe.get("tarikh_siasatan");
				tarikh_akhir_tampal = (String)dbe.get("tarikh_akhir_tampal");		
			}
		}
		
		//tarikh siasatan
		context.put("lblTarikhSiasatan",tarikh_siasatan);
		
		//default tarikh dikeluarkan = tarikh borang e
		context.put("txdTarikhKeluar",tarikh_cetak_borange);
		context.put("txdTarikhTampal",tarikh_akhir_tampal);
		
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchangeEdit","no");
		context.put("showAlertPaging","no");
		
		//onchange validation
		context.put("onchangeLocation","no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	String submit2 = getParam("command2");
    	myLogger.info("submit[2] : " + submit2);
    	
    	String submit3 = getParam("command3");
    	myLogger.info("submit[3] : " + submit3);
    	
    	if("viewListNotis".equals(submit)){
    
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//data & list maklumat tanah
    		model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
     		listMaklumatTanah = model.getListMaklumatTanah();
     		context.put("listMaklumatTanah", listMaklumatTanah);
     		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
     		//List Senarai Notis Yang telah direkodkan
    		listNotisInBulk(idpermohonan);
    		
    		//screen
    		vm = screenListNotis;
    		
    	}//close 
    	
    /*	
    	else 
    	if("tambahNotis".equals(submit)){
    		
    		//form validation
    		context.put("mode","new");
    		
    		//list notis
    		listNotis(session,idHakmilik);
     		
        	if("simpanNotis".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			simpanNotis(session,id_status);
        			if(id_status.equals("54") || id_status.equals("74")){
        				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
        			}
        			updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik);
         		}
        		
        		//form validation
        		context.put("mode","new");
      		
        		//list notis
        		listNotis(session,idHakmilik);
 
        		int sizeNotis = listNotis(session,idHakmilik);
        		
            	header.setDataHeader(idpermohonan);
        		dataHeader = header.getDataHeader();
        		if(dataHeader.size()!=0){
        			Hashtable dh = (Hashtable) dataHeader.get(0);
        			id_status = (String)dh.get("id_status");
        		}
        		
        		if(id_status.equals("52") && sizeNotis==1){
        			context.put("showAlertPaging","yes");
        		}else{
        			context.put("showAlertPaging","no");
        		}
        		
        	}//close simpanNotis
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close tambahNotis
    */
    	else 
        if("tambahNotisInBulk".equals(submit) || "viewNotisInBulk".equals(submit)){
        	
        	String id_notisawam = "";
        	String LocationType = "";
        	
        	//form validation
        	if("tambahNotisInBulk".equals(submit)){
        		context.put("mode","new");
        		//reset value
        		resetValue();
        	}else if("viewNotisInBulk".equals(submit)){
        		id_notisawam = getParam("id_notisawam");
        		LocationType = getDataNotisInBulk(id_notisawam);
        		context.put("mode","view");
        		context.put("isEdit","no");
        		//set value from db
        		getDataNotisInBulk(id_notisawam);
        		context.put("id_notisawam", id_notisawam);
        		//get and set checkbox listing
        	}
  		
    		//List Hakmilik Notis in Bulk
    		listHMNotisInBulk(idpermohonan,LocationType,id_notisawam);
    		
    		//List Senarai Notis Yang telah direkodkan
    		listNotisInBulk(idpermohonan);
 			
    		
    		if("onchangeLocation".equals(submit2)){
    			
    			LocationType = getParam("socTempatTampal");
    			
    			//onchange validation
    			if(!LocationType.equals("")){
    				context.put("onchangeLocation","yes");
    			}
    			
        		//get and set data from screen
        		getAndSetData(LocationType);
        		
        		//List Hakmilik Notis in Bulk
        		listHMNotisInBulk(idpermohonan,LocationType,"");
        		
    		}//close onchangeLocation
    		
    		else if("simpanNotisInBulk".equals(submit2)){
     			
     			if (doPost.equals("true")) {
     				simpanNotisInBulk(session,id_status);
     				/*
        			if(id_status.equals("54") || id_status.equals("74")){
        				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
        			}
        			*/
        			
        			if(modelUPT.cekStatusFailDahWujud(idpermohonan,"52","52")==false)
            		{
        			modelUPT.updateStatus(idpermohonan,id_user, "52");
        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
            		}
         		}
     			
     			//List Senarai Notis Yang telah direkodkan
        		listNotisInBulk(idpermohonan);
     			
     		}//close simpanNotisInBulk
     		
    		else if("kemaskiniNotisInBulk".equals(submit2)){
    			
    			context.put("isEdit","yes");
    			
    			if("updateNotisInBulk".equals(submit3)){
    				
    				if (doPost.equals("true")) {  
    					context.put("isEdit","no");
    					updateNotisInBulk(session,id_notisawam);
    				}

    				LocationType = getDataNotisInBulk(id_notisawam);
    				
    				getDataNotisInBulk(id_notisawam);
    				
    				//List Senarai Notis Yang telah direkodkan
    				listNotisInBulk(idpermohonan);
    				
    				//List Hakmilik Notis in Bulk
    	    		listHMNotisInBulk(idpermohonan,LocationType,id_notisawam);
    				
    			}//close updateNotisInBulk
    			
    		}//close kemaskiniNotisInBulk
    		
    		else if("hapusNotisInBulk".equals(submit2)){
    			
    			if (doPost.equals("true")) {   	
    				id_notisawam = getParam("id_notisawam");
    				deleteNotisInBulk(id_notisawam,idpermohonan);
    			}
    			
    		}//close hapusNotisInBulk
    		
        	//screen
    		vm = mainscreenBulk;
    		
        }//close tambahNotisInBulk
    /*	
    	else 
    	if("viewNotis".equals(submit)){
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		String id_notisawam = getParam("id_notisawam");
    		context.put("id_notisawam",id_notisawam);
    		
    		//data notis
    		dataNotis(session,id_notisawam);
    		
    		//list notis
    		listNotis(session,idHakmilik);
    		
        	if("kemaskiniNotis".equals(submit2)){
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
        		
            	if("updateNotis".equals(submit3)){
            		
            		updateNotis(session);
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		id_notisawam = getParam("id_notisawam");
            		context.put("id_notisawam",id_notisawam);
            		
            		//data notis
            		dataNotis(session,id_notisawam);
            		
            		//list notis
            		listNotis(session,idHakmilik);
            		
            	}//close updateNotis
        		
        	}//close kemaskiniNotis
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close viewNotis
    	
    	else 
    	if("hapusNotis".equals(submit)){
    			
    		if (doPost.equals("true")) {
    			hapusNotis(session);
     		}
    		
    		//clear data
    		context.put("txdTarikhKeluar","");
    		
    		//form validation
    		context.put("mode","new");
    		
    		//list notis
    		listNotis(session,idHakmilik);
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close hapusNotis
    */	
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = model.getListCarian();
	
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else{	
    		
    		listPageDepan = model.getListPermohonan(userIdNeg);
    		
    		context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");
			
    		//screen
    		vm = listdepan;
    		
		}//close else
   	
    		//list permohonan
	    	context.put("listPermohonan", listPageDepan);
	    	context.put("list_size", listPageDepan.size());
	    
	    	//id
	    	context.put("id_permohonan", idpermohonan);
	    	context.put("id_hakmilik", idHakmilik);
	    	context.put("id_status", id_status);
	    	context.put("id_fail", id_fail);
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
	@SuppressWarnings("unchecked")
	private void updateNotisInBulk(HttpSession session,String id_notisawam) throws Exception{
		Db db = null;
		
		try {
			db = new Db();
			
		Hashtable h = new Hashtable();
		
		h.put("id_notisawam", id_notisawam);
    	h.put("txdTarikhKeluar", getParam("txdTarikhKeluar"));   	
    	h.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	h.put("socTempatTampal", getParam("socTempatTampal"));
    	h.put("txtTempat", getParam("txtTempat"));  	
    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
    	
		FrmUPTSek8NotisAwamData.updateNotisInBulk(h,db);
		
		//delete senarai notis dalam attribute table
		FrmUPTSek8NotisAwamData.deleteListCB(id_notisawam,db);
		
		String[] cbsemaks = request.getParameterValues("cbsemaks");
    	if(cbsemaks!=null){
			for (int i = 0; i < cbsemaks.length; i++) { 
				FrmUPTSek8NotisAwamData.simpanNotisInBulk(h,cbsemaks[i],Utils.parseLong(id_notisawam),db);
			}
		}
    	
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}//close updateNotisInBulk
	
	private void deleteNotisInBulk(String id_notisawam,String idpermohonan) throws Exception{
		
		context.put("mode","new");
		
		//reset value
		resetValue();
		
		//delete
		FrmUPTSek8NotisAwamData.deleteNotisInBulk(id_notisawam);
		
		//List Hakmilik Notis in Bulk
		listHMNotisInBulk(idpermohonan,"","");
		
		//List Senarai Notis Yang telah direkodkan
		listNotisInBulk(idpermohonan);
		
	}//close listNotisInBulk
	
	public void insertPopupReg(String nama_class,String tajuk_class, String group,Db db) throws Exception {
		//	Db db = null;
			try {
			//	db = new Db();
				Statement stmt = db.getStatement();
				String sql = " INSERT INTO MODULE ( "+
						" MODULE_ID, MODULE_TITLE, MODULE_CLASS,  "+
						" MODULE_GROUP, MODULE_DESCRIPTION)  "+
						" VALUES ('"+nama_class+"','"+tajuk_class+"','"+nama_class+"','"+group+"','') ";					
				myLogger.info("REG CLASS :"+sql.toUpperCase());
				stmt.executeUpdate(sql);
				
				sql = " INSERT INTO ROLE_MODULE ( "+
						" MODULE_ID, USER_ROLE) "+
						" SELECT '"+nama_class+"' AS MODULE_ID,NAME AS USER_ROLE FROM ROLE WHERE UPPER(NAME) LIKE '%PPT%'";
				myLogger.info("REG ROLE CLASS :"+sql.toUpperCase());
				stmt.executeUpdate(sql);
						
			} finally {
			//	if (db != null)
			//		db.close();
			}
		}
		
		
		
		public int checkRegPopup(String class_name, Db db)  throws Exception {
		  
		  	//Db db = null; 
		  	int total = 0;
		  	String sql="";
		  	ResultSet rs = null;
			try {
			//	db = new Db(); 
				sql = " SELECT COUNT(*) AS CHECK_COUNT FROM ROLE_MODULE WHERE MODULE_ID = '"+class_name+"'";	
				rs = db.getStatement().executeQuery(sql); 
			if ( rs.next() ) { 
				total = rs.getInt(1); 
			} 
			} finally { 
			//Close the database connection 
			//if ( db != null ) db.close(); 
			//if (rs != null) rs.close();			
			} 
			return total;
	  }
		
		public void checkFieldWujud(String table_name,String column_name,String data_type,Db db)  throws Exception {
			  
		  	int total = 0;
		  	String sql="";
		  	ResultSet rs = null;
			try {
				sql = " SELECT COUNT(*) as total FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"+table_name+"' AND COLUMN_NAME = '"+column_name+"' ";	
				myLogger.info(" CEK FIELD WUJUD :"+sql);
				rs = db.getStatement().executeQuery(sql); 
			if ( rs.next() ) { 
				total = rs.getInt("total"); 
			} 
			} finally { 					
			} 
			
			if(total==0)
			{				
				sql = "ALTER TABLE "+table_name+" "+
						" ADD "+column_name+" "+data_type+" ";
				myLogger.info(" ADD FIELD WUJUD :"+sql);
				rs = db.getStatement().executeQuery(sql); 			
				//ALTER TABLE supplier ADD supplier_name varchar2(50);				
			}
			
			
			
	  }
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String getDataNotisInBulk(String id_notisawam) throws Exception{
		
		String id_tempattampal = "";
		model.setDataNotisInBulk(id_notisawam);
		Vector dataNotisInBulk = model.getDataNotisInBulk();
		if(dataNotisInBulk.size()!=0){
			Hashtable dn = (Hashtable)dataNotisInBulk.get(0);
			id_tempattampal = (String)dn.get("JENIS_TEMPAT_TAMPAL");
		}
		context.put("dataNotisInBulk", dataNotisInBulk);
 		context.put("saiz_dataNotisInBulk", dataNotisInBulk.size());
		
 		return id_tempattampal;
 		
	}//close getDataNotisInBulk
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listNotisInBulk(String id_permohonan) throws Exception{
		
		model.setListNotisInBulk(id_permohonan);
		Vector listNotisInBulk = model.getListNotisInBulk();
		context.put("listNotisInBulk", listNotisInBulk);
 		context.put("saiz_listNotisInBulk", listNotisInBulk.size());
 		
	}//close listNotisInBulk
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listHMNotisInBulk(String id_permohonan,String LocationType,String id_notisawam) throws Exception{
		
		model.setListHakmilikNotisInBulk(id_permohonan,LocationType,id_notisawam);
		Vector listHakmilikNotisInBulk = model.getListHakmilikNotisInBulk();		
		context.put("listHakmilikNotisInBulk", listHakmilikNotisInBulk);
 		context.put("saiz_listHakmilikNotisInBulk", listHakmilikNotisInBulk.size());
 		
	}//close listHMNotisInBulk
	
	private void getAndSetData(String LocationType) throws Exception{
		
		context.put("socTempatTampal", LocationType);
		context.put("txtTempat", getParam("txtTempat"));
		context.put("txdTarikhKeluar", getParam("txdTarikhKeluar"));
		context.put("txdTarikhTampal", getParam("txdTarikhTampal"));
		
	}//close getAndSetData
	
	private void resetValue() throws Exception{
		
		context.put("txdTarikhKeluar", "");
		context.put("txdTarikhTampal", "");
		context.put("socTempatTampal", "");
		context.put("txtTempat", "");
		context.put("id_notisawam", "");
		
	}//close resetValue
/*	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelUPT.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"1477");
	
	}//close addSuburusanHakmilik
*/	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String userData(String id_user) throws Exception{
		
		Vector listUserid = new Vector();
		listUserid.clear();
		
		modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    
	    return userIdNeg;
	    
	}//close userData
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1477");
		
	}//close updateSuburusanStatusFailPPT
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmUPTSek8NotisAwamData.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
/*	
	private void hapusNotis(HttpSession session) throws Exception{
    	
		String id_notisawam = getParam("id_notisawam");
//		model.setDataNotis(id_notisawam);
//		Vector dataNotis = model.getDataNotis();
//		String id_dokumen = "";
//		if(dataNotis.size()!=0){
//			Hashtable dn = (Hashtable)dataNotis.get(0);
//			id_dokumen = (String)dn.get("id_dokumen");
//		}
		
		//FrmUPTSek8NotisAwamData.hapusNotis(id_notisawam,id_dokumen);
		FrmUPTSek8NotisAwamData.hapusNotis(id_notisawam);
		
	}//close hapusNotis
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataNotis(HttpSession session,String idNotisAwam) throws Exception{
    	
		model.setDataNotis(idNotisAwam);
		Vector dataNotis = model.getDataNotis();
 		context.put("dataNotis", dataNotis);
      
	}//close dataNotis
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private int listNotis(HttpSession session,String idHakmilik) throws Exception{
    	
		model.setListNotis(idHakmilik);
		Vector listNotis = model.getListNotis();
 		context.put("listNotis", listNotis);
 		context.put("saiz_listNotis", listNotis.size());
      
 		return listNotis.size();
 		
	}//close listNotis
*/	
	@SuppressWarnings("unchecked")
	private void simpanNotisInBulk(HttpSession session,String idstatus) throws Exception {
		Db db = null;
		try {
			db = new Db();
		Hashtable h = new Hashtable();
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_hakmilik", getParam("id_hakmilik"));
    	h.put("txdTarikhKeluar", getParam("txdTarikhKeluar"));
    	h.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	h.put("socTempatTampal", getParam("socTempatTampal"));
    	h.put("txtTempat", getParam("txtTempat"));
    	
    	h.put("id_user", idUser);
    	
    	if(idstatus.equals("54")|| idstatus.equals("74") ){
    		FrmUPTSek8NotisAwamData.updateStatus(h,db);
    	}
    	
    	long id_notisawam = DB.getNextID_DB("TBLPPTNOTISAWAM_SEQ",db);
    	
    	FrmUPTSek8NotisAwamData.simpanNotisInBulkData(h,id_notisawam,db);
    	
    	String[] cbsemaks = request.getParameterValues("cbsemaks");
    	if(cbsemaks!=null){
			for (int i = 0; i < cbsemaks.length; i++) { 
				FrmUPTSek8NotisAwamData.simpanNotisInBulk(h,cbsemaks[i],id_notisawam,db);
			}
		}
    	
		}
		finally {
			if (db != null)
				db.close();
		}
 
	}
/*	
	@SuppressWarnings("unchecked")
	private void simpanNotis(HttpSession session,String idstatus) throws Exception {
		
		Hashtable h = new Hashtable();
		
		//long id_dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_hakmilik", getParam("id_hakmilik"));
		//h.put("id_dokumen", id_dokumen);
    	//h.put("txtNotis", getParam("txtNotis"));
    	h.put("txdTarikhKeluar", getParam("txdTarikhKeluar"));
    	//h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
    	
    	h.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	h.put("socTempatTampal", getParam("socTempatTampal"));
    	h.put("txtTempat", getParam("txtTempat"));
    	
    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
    	
    	if(idstatus.equals("54") || idstatus.equals("74") ){
    		FrmUPTSek8NotisAwamData.updateStatus(h);
    	}
    	
    	FrmUPTSek8NotisAwamData.simpanNotis(h);
    
    	
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		ServletFileUpload upload = new ServletFileUpload(factory);
//
//		List items = upload.parseRequest(request);
//		Iterator itr = items.iterator();
//		while (itr.hasNext()) {
//		    FileItem item = (FileItem)itr.next();
//		    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
//		    	saveData(item,id_dokumen);
//		    }
//		}
		
	}
*/	
//	private void saveData(FileItem item,long id_dokumen) throws Exception {
//	  		
//		Db db = null;
//		
//	    try {
//	    	
//	        	db = new Db();
//	        	
//	        	Connection con = db.getConnection();
//	        	con.setAutoCommit(false);
//	        	
//	        	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPPTDOKUMEN " +
//	        			"(id_dokumen,nama_fail,jenis_Mime,content) " +
//	        			"values(?,?,?,?)");
//	        	
//	        	ps.setLong(1, id_dokumen);
//	        	ps.setString(2,item.getName());
//	        	ps.setString(3,item.getContentType());
//	        	ps.setBinaryStream(4,item.getInputStream(),(int)item.getSize());
//	        	
//	        	ps.executeUpdate();
//	            con.commit();
//	            
//		    }catch (SQLException se) { 
//		    	throw new Exception("Ralat : Masalah Penyimpanan Data");
//		    }finally {
//			      if (db != null) db.close();
//		    }
//	  }
/*	
	@SuppressWarnings({ "unchecked" })
	private void updateNotis(HttpSession session) throws Exception {
		
		Hashtable h = new Hashtable();
		
		String id_notisawam = getParam("id_notisawam");
		
		h.put("id_notisawam", id_notisawam);
    	//h.put("txtNotis", getParam("txtNotis"));
    	h.put("txdTarikhKeluar", getParam("txdTarikhKeluar"));
    	//h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
    	
    	h.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	h.put("socTempatTampal", getParam("socTempatTampal"));
    	h.put("txtTempat", getParam("txtTempat"));
    	
    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
    	
    	FrmUPTSek8NotisAwamData.updateNotis(h);
	
	}
*/	
	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session,String action,Vector list) {
		
			try {
			
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer)this.context.get("itemsPerPage");
			}
		    
		    if ("getNext".equals(action)) {
		    	page++;
		    } else if ("getPrevious".equals(action)) {
		    	page--;
		    } else if ("getPage".equals(action)) {
		    	page = getParamAsInteger("value");
		    } else if ("doChangeItemPerPage".equals(action)) {
		       itemsPerPage = getParamAsInteger("itemsPerPage");
		    }
		    	
		    Paging paging = new Paging(session,list,itemsPerPage);
			
			if (page > paging.getTotalPages()) page = 1; //reset page number
			this.context.put("listPermohonan",paging.getPage(page));
		    this.context.put("page", new Integer(page));
		    this.context.put("itemsPerPage", new Integer(itemsPerPage));
		    this.context.put("totalPages", new Integer(paging.getTotalPages()));
		    this.context.put("startNumber", new Integer(paging.getTopNumber()));
		    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
		    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
		        
			} catch (Exception e) {
				e.printStackTrace();
				this.context.put("error",e.getMessage());
			}	
		}	
	
}//close class
