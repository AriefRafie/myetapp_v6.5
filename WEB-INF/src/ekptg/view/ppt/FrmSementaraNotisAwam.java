package ekptg.view.ppt;
/*
 * @author 
 * NORZAILY BINTI JASMI
 */
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.SementaraNotisAwam;

public class FrmSementaraNotisAwam extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSementaraNotisAwam.class);
	
	// MODEL SEKSYEN 8
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	
	// MODEL SEMENTARA
	SementaraNotisAwam model = new SementaraNotisAwam();	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user); 		

		//command for pagings
    	String action = getParam("action");
    	
    	//helper
    	context.put("Util", new lebah.util.Util());
    	context.put("Utils", new ekptg.helpers.Utils());
    	
    	String vm = "";
    	String noLOT = "";
    	String idpegawai = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector dataMaklumatTanahTerperinci = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
    	listMaklumatTanah.clear();
    	dataMaklumatTanahTerperinci.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSementaraNotisAwamSenarai.jsp";
    	String screenListNotis = "app/ppt/frmSementaraNotisAwamList.jsp";
    	String mainscreen = "app/ppt/frmSementaraNotisAwam.jsp";
    	
    	Db dbx = null;
		try {
			dbx = new Db();
	    	
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik",dbx)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT",dbx);
	    	}
	    	checkFieldWujud("TBLPPTDOKUMEN","JENIS_DOKUMEN","varchar2(50)",dbx);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_HAKMILIK","NUMBER",dbx);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_NOTISAWAM","NUMBER",dbx);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_BUKTIPENYAMPAIAN","NUMBER",dbx);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_BORANGH","NUMBER",dbx);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_BORANGK","NUMBER",dbx);
		}finally {
			if (dbx != null)
				dbx.close();
		}
    	
    	//default list
    	listPageDepan = model.getListPermohonan(userIdNeg);

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
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
    	
		//paging
    	paging();
    	
		//header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			id_fail = dh.get("id_fail").toString();
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}

		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}
		
		//header hakmilik
		String idHakmilik = getParam("id_hakmilik");
		modelUPT.setMaklumatTanah(idHakmilik);
		Vector dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		//default
		context.put("mode","");
		context.put("isEdit","");

    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewListNotis".equals(submit)){
    /*
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
     		listMaklumatTanah = modelUPT.getListMaklumatTanah();
     		context.put("listMaklumatTanah", listMaklumatTanah);
     		context.put("saiz_listTanah", listMaklumatTanah.size());
    	*/	
    		context.put("saiz_listTanah", modelUPT.setListMaklumatTanah_count(idpermohonan,noLOT,idpegawai));
    		//screen
    		vm = screenListNotis;
    		
    	}//close 
    	
    	
    	else 
    	if("tambahNotis".equals(submit)){
    		
    		//form validation
    		context.put("mode","new");
    		
    		//list notis
    		listNotis(session,idHakmilik);
     		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("simpanNotis".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			simpanNotis(session,id_status);
        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
         		}
        		
        		//form validation
        		context.put("mode","new");
      		
        		//list notis
        		listNotis(session,idHakmilik);
 
        		
        	}//close simpanNotis
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close tambahNotis
    	
    	
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
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniNotis".equals(submit2)){
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
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
//    		context.put("txdTarikhKeluar","");
    		
    		//form validation
    		context.put("mode","new");
    		
    		//list notis
    		listNotis(session,idHakmilik);
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close hapusNotis
    	
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = model.getListCarian(userIdNeg);
	
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
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"16103946");
		
	}//close updateSuburusanStatusFailPPT
	


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
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		SementaraNotisAwam.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
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
		SementaraNotisAwam.hapusNotis(id_notisawam);
		
	}//close hapusNotis
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataNotis(HttpSession session,String idNotisAwam) throws Exception{
    	
		model.setDataNotis(idNotisAwam);
		Vector dataNotis = model.getDataNotis();
 		context.put("dataNotis", dataNotis);
      
	}//close dataNotis
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void listNotis(HttpSession session,String idHakmilik) throws Exception{
    	
		model.setListNotis(idHakmilik);
		Vector listNotis = model.getListNotis();
 		context.put("listNotis", listNotis);
 		context.put("saiz_listNotis", listNotis.size());
      
	}//close listNotis
	
	@SuppressWarnings("unchecked")
	private void simpanNotis(HttpSession session,String idstatus) throws Exception {
		
		Hashtable h = new Hashtable();
		
		//long id_dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_hakmilik", getParam("id_hakmilik"));    	
    	h.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	h.put("socTempatTampal", getParam("socTempatTampal"));
    	h.put("txtTempat", getParam("txtTempat"));
    	
    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
    	
    	SementaraNotisAwam.updateStatus(h);
    	
    	SementaraNotisAwam.simpanNotis(h);		
	}
	
	@SuppressWarnings({ "unchecked" })
	private void updateNotis(HttpSession session) throws Exception {
		
		Hashtable h = new Hashtable();
		
		String id_notisawam = getParam("id_notisawam");
		
		h.put("id_notisawam", id_notisawam);
    	//h.put("txtNotis", getParam("txtNotis"));
//    	h.put("txdTarikhKeluar", getParam("txdTarikhKeluar"));
    	//h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
    	
    	h.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	h.put("socTempatTampal", getParam("socTempatTampal"));
    	h.put("txtTempat", getParam("txtTempat"));
    	
    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
    	
    	SementaraNotisAwam.updateNotis(h);
	
	}
	
	private void paging() throws Exception{
		String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "9");
    	}
	}//close paging
	
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
		
	public void checkFieldWujud(String table_name,String column_name,String data_type,Db db)  throws Exception {
		  
	  	int total = 0;
	  	String sql="";
	  	ResultSet rs = null;
		try {
			sql = " SELECT COUNT(*) as total FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"+table_name+"' AND COLUMN_NAME = '"+column_name+"' ";	
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
			rs = db.getStatement().executeQuery(sql); 			
			//ALTER TABLE supplier ADD supplier_name varchar2(50);				
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
	
}//close class
