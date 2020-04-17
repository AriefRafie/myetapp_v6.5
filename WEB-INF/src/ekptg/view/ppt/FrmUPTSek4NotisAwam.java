package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmKJPDaftarOnlineData;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek4NotisAwamData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmUPTSek4NotisAwam extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmUPTSek4NotisAwam.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUPTSek4NotisAwamData model = new FrmUPTSek4NotisAwamData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	String vm = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector dataNamaPengarah = new Vector();
    	Vector listUserid = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataMMK = new Vector();
    	
    	dataMMK.clear();
    	getIdSuburusanstatusfail.clear();
    	listUserid.clear();
    	dataNamaPengarah.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmUPTSek4NotisAwamSenarai.jsp";
    	String screenListNotis = "app/ppt/frmUPTSek4NotisAwamList.jsp";
    	String mainscreen = "app/ppt/frmUPTSek4NotisAwam.jsp";
    	
    	

    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
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
    	
		//header
		String id_status = "";
		String tarikhBorangB = "";
		String id_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	Vector dataHeader = null;
    	if(!idpermohonan.equals(""))
    	{
	    	header.setDataHeader(idpermohonan);
			dataHeader = header.getDataHeader();
			context.put("dataHeader", dataHeader);
			if(dataHeader.size()!=0){
				Hashtable dh = (Hashtable) dataHeader.get(0);
				id_status = (String)dh.get("id_status");
				tarikhBorangB = (String)dh.get("tarikh_borangb");
				id_fail = (String)dh.get("id_fail");
				
				Vector list_sub = null;
				list_sub = header.listPerjalananFail(idpermohonan);
				this.context.put("list_sub_header", list_sub);
			}
    	}

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
		
		/*
		modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    */
	    
		/*
	    Db db = null;
		try {
		db = new Db();
	    	
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT",db);
	    	}
	    	
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupUploadDokumen",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupUploadDokumen","Skrin Senarai Dokumen PPT", "EKPTG - PPT",db);
	    	}
	    	
	    	checkFieldWujud("TBLPPTDOKUMEN","JENIS_DOKUMEN","varchar2(50)",db);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_HAKMILIK","NUMBER",db);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_NOTISAWAM","NUMBER",db);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_BUKTIPENYAMPAIAN","NUMBER",db);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_BORANGH","NUMBER",db);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_BORANGK","NUMBER",db);
		}finally {
			if (db != null)
			db.close();
		}
	    */
		
		
	    //default list
		if(idpermohonan.equals(""))
    	{
    	listPageDepan = model.getListPermohonan(userIdNeg);
    	}
    	
		//GET NAMA PENGARAH
	    String nama_pengarah = "";
	    if(!idpermohonan.equals(""))
    	{
	    modelUPT.setNamaPengarah(userIdNeg);
	    dataNamaPengarah = modelUPT.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = np.get("nama_pengarah").toString();
	    }
    	}
	    
	    context.put("nama_pengarah",nama_pengarah);
	    context.put("userIdNeg", userIdNeg);
	    
		//paging
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "5");
    	}
    	
    	
    	//get tarikh mmk
    	String tarikh_mmk = "";
    	if(!idpermohonan.equals(""))
    	{
    	model.setDataMMK(idpermohonan);
		dataMMK = model.getDataMMK();
    	if(dataMMK.size()!=0){
    		Hashtable dm = (Hashtable)dataMMK.get(0);
    		tarikh_mmk = (String)dm.get("tarikh_mmk");
    	}
    	}
    	
    	context.put("tarikh_mmk",tarikh_mmk);
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("isEditWarta", "");
		
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewListNotis".equals(submit)){
    
    		context.put("isEditWarta", "no");
    		
     		//list notis
    		listNotis(session,idpermohonan);
     		
    		//data warta
    		dataWarta(session,idpermohonan);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniWarta".equals(submit2)){
        		
        		context.put("isEditWarta", "yes");
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("updateWarta".equals(submit3)){
            		
            		updateWarta(session);
            		
            		context.put("isEditWarta", "no");
            		
            		//data warta
            		dataWarta(session,idpermohonan);
            		
            	}//close updateWarta
        		
        	}//close kemaskiniWarta
    		
    		//screen
    		vm = screenListNotis;
    		
    	}//close 
    	
    	else 
        if("hantar".equals(submit)){
        		
        	if (doPost.equals("true")) {
        		hantar(session);
        		updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
     	    }
        	
        	
        	context.put("isEditWarta", "no");
    		
        	header.setDataHeader(idpermohonan);
    		dataHeader = header.getDataHeader();
    		context.put("dataHeader", dataHeader);
    		if(dataHeader.size()!=0){
    			Hashtable dh = (Hashtable) dataHeader.get(0);
    			id_status = (String)dh.get("id_status");
    		}
    		
     		//list notis
    		listNotis(session,idpermohonan);
     		
    		//data warta
    		dataWarta(session,idpermohonan);
    		
        	//screen
    		vm = screenListNotis;
    		
        }//close hantar
    	
        else
    	if("SimpanTarikhBorangB".equals(submit)){
    	    	
    	    if (doPost.equals("true")) {
    	    	SimpanTarikh(session);
    	    }
    	    	
    	    context.put("isEditWarta", "no");
    		
    	    header.setDataHeader(idpermohonan);
    		dataHeader = header.getDataHeader();
    		context.put("dataHeader", dataHeader);
    		if(dataHeader.size()!=0){
    			Hashtable dh = (Hashtable) dataHeader.get(0);
    			tarikhBorangB = (String)dh.get("tarikh_borangb");
    		}
    		
     		//list notis
    		listNotis(session,idpermohonan);
     		
    		//data warta
    		dataWarta(session,idpermohonan);
    		
        	//screen
    		vm = screenListNotis;
     	
    	}//simpan tarikh borang b
    	
    	else 
    	if("tambahNotis".equals(submit)){
    		
    		//form validation
    		context.put("mode","new");
    		
    		//list notis
    		listNotis(session,idpermohonan);
     		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("simpanNotis".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			simpanNotis(session,id_status);
         		}
        		
        		//form validation
        		context.put("mode","new");
      		
        		//list notis
        		listNotis(session,idpermohonan);
 
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
    		listNotis(session,idpermohonan);
    		
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
            		listNotis(session,idpermohonan);
            		
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
    		
    		//form validation
    		context.put("mode","new");
    		
    		//list notis
    		listNotis(session,idpermohonan);
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close hapusNotis
    	
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
   	
    		//data
    		context.put("tarikhBorangB", tarikhBorangB);
    	
    		//list permohonan
	    	context.put("listPermohonan", listPageDepan);
	    	context.put("list_size", listPageDepan.size());
	    
	    	//id
	    	context.put("id_permohonan", idpermohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_fail", id_fail);
	    	context.put("id_suburusanstatusfailppt", id_suburusanstatusfailppt);
	    	
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
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1431");
		
	}//close updateSuburusanStatusFailPPT
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmUPTSek4NotisAwamData.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	private void hapusNotis(HttpSession session) throws Exception{
    	
		String id_notisawam = getParam("id_notisawam");
		FrmUPTSek4NotisAwamData.hapusNotis(id_notisawam);
		
	}//close hapusNotis
	
	@SuppressWarnings("unchecked")
	private void SimpanTarikh(HttpSession session) throws Exception{
    	
		 Hashtable h = new Hashtable();
		 
		 h.put("id_permohonan", getParam("id_permohonan"));
		 h.put("id_user", session.getAttribute("_ekptg_user_id"));
		 FrmUPTSek4NotisAwamData.simpanTarikh(h);
		
	}//close SimpanTarikh
	
	@SuppressWarnings("unchecked")
	private void hantar(HttpSession session) throws Exception{
	    	
		Hashtable h = new Hashtable();
		    
		h.put("id_permohonan",getParam("id_permohonan"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		FrmKJPDaftarOnlineData.hantar(h);
	}
	 
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataNotis(HttpSession session,String idNotisAwam) throws Exception{
    	
		model.setDataNotis(idNotisAwam);
		Vector dataNotis = model.getDataNotis();
 		context.put("dataNotis", dataNotis);
      
	}//close dataNotis
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void listNotis(HttpSession session,String idpermohonan) throws Exception{
    	
		model.setListNotis(idpermohonan);
		Vector listNotis = model.getListNotis();
 		context.put("listNotis", listNotis);
 		context.put("saiz_listNotis", listNotis.size());
      
	}//close listNotis
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataWarta(HttpSession session,String idpermohonan) throws Exception{
    	
		Vector dataWarta = new Vector();
		dataWarta.clear();
		
		model.setDataWarta(idpermohonan);
		dataWarta = model.getDataWarta();
 		String id_warta = "";
 		if(dataWarta.size()!=0){
 			Hashtable i = (Hashtable) dataWarta.get(0);
 			id_warta = i.get("id_warta").toString();
 		}
 		
 		context.put("id_warta",id_warta);
 		context.put("dataWarta", dataWarta);
		
	}//close dataWarta
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateWarta(HttpSession session) throws Exception
	{  	
	    	Hashtable h = new Hashtable();
	    	
	    	h.put("id_warta", getParam("id_warta"));
	    	h.put("proses_warta", getParam("sorProses"));
	    	h.put("no_warta", getParam("txtNoWarta"));
	    	h.put("tarikh_warta", getParam("txdTarikhWarta"));

	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	 
	    	model.updateWarta(h);
	    	
	}//close updateWarta
	
	@SuppressWarnings("unchecked")
	private void simpanNotis(HttpSession session,String idstatus) throws Exception {
		
		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
    	
    	h.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	h.put("socTempatTampal", getParam("socTempatTampal"));
    	h.put("txtTempat", getParam("txtTempat"));
    	
    	h.put("id_user", session.getAttribute("_ekptg_user_id"));

    	FrmUPTSek4NotisAwamData.simpanNotis(h);
	
	}//close impan notis
    		
	@SuppressWarnings({ "unchecked" })
	private void updateNotis(HttpSession session) throws Exception {
		
		Hashtable h = new Hashtable();
		
		h.put("id_notisawam", getParam("id_notisawam"));
    	
    	h.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	h.put("socTempatTampal", getParam("socTempatTampal"));
    	h.put("txtTempat", getParam("txtTempat"));
    	
    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
    	
    	FrmUPTSek4NotisAwamData.updateNotis(h);	
	}
	
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
	
}//close class
