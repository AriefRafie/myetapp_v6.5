package ekptg.view.ppt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.StateLookup;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek8BorangFData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmUPTSek8BorangF extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmUPTSek8BorangF.class);
	
	//model
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUPTSek8BorangFData model = new FrmUPTSek8BorangFData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
	
    	//helper
    	context.put("Util", new lebah.util.Util());
    	context.put("Utils", new ekptg.helpers.Utils());
    	
    	String vm = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector dataMaklumatTanahTerperinci = new Vector();
    	Vector dataBorangE = new Vector();
    	Vector maxWarta = new Vector();
    	Vector listUserid = new Vector();
    	Vector dataNamaPengarah = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	Vector dataMaklumatTanah = new Vector();
    	
    	dataMaklumatTanah.clear();
    	dataSuburusanHakmilik.clear();
    	getIdSuburusanstatusfail.clear();
    	dataNamaPengarah.clear();
    	listUserid.clear();
    	maxWarta.clear();
    	dataBorangE.clear();
    	dataMaklumatTanahTerperinci.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/BorangEdanF/frmUPTSek8BorangFSenarai.jsp";
    	String listHMscreen = "app/ppt/BorangEdanF/frmUPTSek8BorangFListHM.jsp";
    	String mainScreenInBulk = "app/ppt/BorangEdanF/frmUPTSek8BorangEInBulk.jsp";
    	String screenBorangF = "app/ppt/BorangEdanF/frmUPTSek8BorangF.jsp";
    	/*
    	Db db = null;
		try {
		db = new Db();
	    	
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik_BorangE_F",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik_BorangE_F","Skrin Capaian Senarai Borang E & F", "EKPTG - PPT",db);
	    	}
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik_BorangF",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik_BorangF","Skrin Capaian Senarai F", "EKPTG - PPT",db);
	    	}
		}finally {
			if (db != null)
			db.close();
		}
		*/
		
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	myLogger.info("DOPOST :"+doPost);
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
		//paging
    	/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "13");
    	}
    	*/
    	context.put("paging", "13");
    	
    	/*
    	modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    */
	    
	    //id negeri user
	    context.put("userIdNeg", userIdNeg);
	    
		
	    
	    //default list
    	//listPageDepan = model.getListPermohonan(userIdNeg);
    	
		//header
		String id_status = "";
		//String tarikh_borangF = "";
		String id_fail = "";
		String flagSegera = "";
		String flag_subjaket = "";
    	String idpermohonan = getParam("id_permohonan");
    	String nama_pengarah = "";
    	if(!idpermohonan.equals(""))
    	{
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			//tarikh_borangF = (String)dh.get("tarikh_borangf");
			id_fail = (String)dh.get("id_fail");
			flagSegera = dh.get("flag_segera").toString();
			flag_subjaket = dh.get("flag_subjaket").toString();
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
			
		}
		
		//GET NAMA PENGARAH
	    
	    modelUPT.setNamaPengarah(userIdNeg);
	    dataNamaPengarah = modelUPT.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = np.get("nama_pengarah").toString();
	    }
    	}
	    
	    context.put("nama_pengarah",nama_pengarah);

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
		
		//id
		String idHakmilik = getParam("id_hakmilik");

		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchangeEdit","no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	String submit2 = getParam("command2");
    	myLogger.info("submit[2] : " + submit2);
    	
    	String submit3 = getParam("command3");
    	myLogger.info("submit[3] : " + submit3);
    	
    	String submit4 = getParam("command4");
    	myLogger.info("submit[4] : " + submit4);
    	
    	
    	String carianLotHakmilik = getParam("carianLotHakmilik");
    	context.put("carianLotHakmilik","");
    	
    	
    	if("viewListHM".equals(submit)){
    	    
    		//List Senarai Borang E Yang telah direkodkan
    		listBorangEInBulk(idpermohonan,carianLotHakmilik);
     		
        	//screen
    		vm = listHMscreen;
        	
    	}//close 
    	else if("deleteBorangEInBulk".equals(submit)){
    		String id_borange = "";
    		id_borange = getParam("id_borange");
    		
    		Db dbx = null;
    		try {
    		dbx = new Db();
    		FrmUPTSek8BorangFData.deleteListCB(id_borange,dbx); 
    		}finally {
    			if (dbx != null)
    			dbx.close();
    		}
    		
    		
    	     
    	    
    	    listBorangEInBulk(idpermohonan,carianLotHakmilik);
        	//screen
    		vm = listHMscreen;
        	
    	}//close 
    	
    	else if("daftarMaklumatBorangEInBulk".equals(submit) || "viewMaklumatBorangEInBulk".equals(submit)){
    		
    		String id_borange = "";
    		String id_hakmilik = "";
    		String mode = "";
    		String isEdit = getParam("isEdit");
    		
    		//form validation
        	if("daftarMaklumatBorangEInBulk".equals(submit)){
        		context.put("mode","new");
        		context.put("isEdit","yes");
        		
        		//reset value
        		resetValueBorangE(idpermohonan);
        		mode = "new";
        	}else if("viewMaklumatBorangEInBulk".equals(submit)){
        		id_borange = getParam("id_borange");
        		id_hakmilik = getParam("id_hakmilik");
        		context.put("mode","view");
        		context.put("isEdit","no");
        		//set value from db
        		getDataBorangEInBulk(id_borange,isEdit);
        		context.put("id_borange", id_borange);
        		context.put("id_hakmilik", id_hakmilik);
        		mode = "view";
        	}
    		
    		//List Hakmilik Borang E in Bulk
    		listHMBorangEInBulk(idpermohonan,id_borange,mode);
    		
    		//List Senarai Borang E Yang telah direkodkan
    		listBorangEInBulk(idpermohonan,carianLotHakmilik);
    		
    		
    		if("onchangeDropdown".equals(submit2)){
    			
    			//get and set data from screen
    			getAndSetDataBorangEInBulk();
    			
    		}//close onchangeDropdown
    		
    		else if("kemaskiniBorangEInBulk".equals(submit2)){
    			
    			context.put("isEdit","yes");
    			//set value from db
        		getDataBorangEInBulk(id_borange,isEdit);
        		
    			if("updateBorangEInBulk".equals(submit3)){
    				
    				if (doPost.equals("true")) {  
    					context.put("isEdit","no");
    					
    					Db dbx = null;
    		    		try {
    		    		dbx = new Db();
    		    		updateBorangEInBulk(session,id_borange,dbx,id_hakmilik);
    		    		 
    		    		}finally {
    		    			if (dbx != null)
    		    			dbx.close();
    		    		}
    				}
    				
    				//set value from db
            		getDataBorangEInBulk(id_borange,isEdit);
            		
    				//List Hakmilik Borang E in Bulk
    	    		listHMBorangEInBulk(idpermohonan,id_borange,mode);
    	    		
    	    		//List Senarai Borang E Yang telah direkodkan
    	    		listBorangEInBulk(idpermohonan,carianLotHakmilik);
    				
    			}//close updateBorangEInBulk
    			
    		}//close kemaskiniMaklumatBorangEInBulk
    		
    		else if("simpanMaklumatBorangEInBulk".equals(submit2)){ // penambahbaikan 4/9/2020
    			
//    			if (doPost.equals("true")){} // Kene ulangan?
//     				simpanBorangEInBulk(session,idpermohonan,id_status);
    				if (doPost.equals("true")){} 
    					context.put("isEdit","no");
    					
    					Db dbx = null;
    		    		try {
    		    		dbx = new Db();
    	    
    		    		simpanBorangEInBulk(session,idpermohonan,id_status,dbx);    		    		
    		    		//simpanBorangEHakmilikInBulk(session,idpermohonan,id_borangeString,dbx);
    		    		}finally {
    		    			if (dbx != null)
    		    			dbx.close();
    		    		}
    				
    		    		/*
     				if(id_status.equals("35") || id_status.equals("74")){
        				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
        			}
        			*/
    		    		if(modelUPT.cekStatusFailDahWujud(idpermohonan,"54","52")==false)	{
    		    			
    		    			modelUPT.updateStatus(idpermohonan,id_user, "54");
    		    			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
	            		}
    			
     			
    			//update status terkini
    			id_status = getLatestStatus(idpermohonan);
    			
    			resetValueBorangE(idpermohonan);
    			
    			//List Hakmilik Borang E in Bulk
	    		listHMBorangEInBulk(idpermohonan,id_borange,mode);
	    		
    			//List Senarai Borang E Yang telah direkodkan
        		listBorangEInBulk(idpermohonan,carianLotHakmilik);
        		
    		}//close simpanMaklumatBorangEInBulk
    		
    		
    		//screen
    		vm = mainScreenInBulk;
    		
    	}//close daftarMaklumatBorangEInBulk / viewMaklumatBorangEInBulk
    	
    	else if("maklumatBorangF".equals(submit)){
    		
    		context.put("showBorangF", "no");
    		
    		String id_borange = getParam("id_borange");
    		context.put("id_borange", id_borange);
    		
    		//list hakmilik borang e
    		listHakmilikBorangE(id_borange);
    		
    		if("showBorangF".equals(submit2) || "viewMaklumatBorangFInBulk".equals(submit2)){
    			
    			context.put("showBorangF", "yes");
    			String id_borangf = "";
        		
        		//form validation
            	if("showBorangF".equals(submit2)){
            		context.put("mode","new");
            		//reset value
            		resetValueBorangF();
            	}else if("viewMaklumatBorangFInBulk".equals(submit2)){
            		id_borangf = getParam("id_borangf");
            		context.put("mode","view");
            		context.put("isEdit","no");
            		//set value from db
            		getDataBorangFInBulk(id_borangf);
            		context.put("id_borangf", id_borangf);
            	}
    			
    			String id_hakmilik = getParam("id_hakmilik");
    			context.put("id_hakmilik", id_hakmilik);
    			
    			//maklumat hakmilik
    			maklumatHakmilik(id_hakmilik);
    			
    			//list PB
            	listPB(session,id_hakmilik,id_borangf);
            	
            	//list rekod borang f
            	listBorangFInBulk(id_hakmilik);
            	
            	if("simpanBorangFInBulk".equals(submit3)){
            	
            		if (doPost.equals("true")) {
            			simpanBorangFInBulk(session);
             		}
            		
            		//reset value
        			resetValueBorangF();
        			
            		//list PB
                	listPB(session,id_hakmilik,id_borangf);
                	
                	//list rekod borang f
                	listBorangFInBulk(id_hakmilik);
                	
            	}//close simpanBorangFInBulk
            	
            	else if("kemaskiniBorangFInBulk".equals(submit3)){
            		
            		context.put("isEdit","yes");
            		
            		if("updateBorangFInBulk".equals(submit4)){
            			
            			if (doPost.equals("true")) {  
        					context.put("isEdit","no");
        					updateBorangFInBulk(session,id_borangf);
        				}
        				
            			//set value from db
                		getDataBorangFInBulk(id_borangf);
                		
                		//list PB
                    	listPB(session,id_hakmilik,id_borangf);
                    	
                    	//list rekod borang f
                    	listBorangFInBulk(id_hakmilik);
        	    		
            		}//updateBorangFInBulk
            		
            	}//close kemaskiniBorangFInBulk
            	
            	else if("hapusBorangFInBulk".equals(submit3)){
            		
            		if (doPost.equals("true")) {   	
            			id_borangf = getParam("id_borangf");
            			deleteBorangFInBulk(session,id_borangf,id_hakmilik);
        			}
            		
            	}//close hapusBorangFInBulk
            	
    		}//close showBorangF
    		
    		//screen
    		vm = screenBorangF;
    		
    	}//close maklumatBorangF
    	
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
    		myLogger.info("vm :"+vm);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
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
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String getLatestStatus(String id_permohonan) throws Exception {
		//update header and status
		String id_status = "";
		header.setDataHeader(id_permohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
		}		
		return id_status;
	}
	
	@SuppressWarnings("unchecked")
	private void updateBorangFInBulk(HttpSession session,String id_borangf) throws Exception{
		
		Hashtable h = new Hashtable();
		
		h.put("txtTempoh", getParam("txtTempoh"));
		h.put("txtKeterangan", getParam("txtKeterangan"));	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUPTSek8BorangFData.updateBorangFInBulk(h,Utils.parseLong(id_borangf));
    
		//delete senarai borang f dalam attribute table
		//FrmUPTSek8BorangFData.deleteListBorangFCB(id_borangf);
		
    	String[] cbsemaks = request.getParameterValues("cbsemaks");
    	if(cbsemaks!=null){
			for (int i = 0; i < cbsemaks.length; i++) { 
				FrmUPTSek8BorangFData.simpanBorangFInBulk(h,cbsemaks[i],Utils.parseLong(id_borangf));
			}
		}
    	
	}//close updateBorangFInBulk
	
	@SuppressWarnings("static-access")
	private void deleteBorangFInBulk(HttpSession session,String id_borangf,String id_hakmilik) throws Exception{
		
		//reset value
		resetValueBorangF();
		
		//delete
		model.deleteBorangFInBulk(id_borangf);
		
		//list PB
    	listPB(session,id_hakmilik,id_borangf);
    	
    	//list rekod borang f
    	listBorangFInBulk(id_hakmilik);
		
	}//close deleteBorangFInBulk

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getDataBorangFInBulk(String id_borangf) throws Exception{
		
		model.setDataBorangFInBulk(id_borangf);
		Vector dataBorangFInBulk = model.getDataBorangFInBulk();
		context.put("dataBorangFInBulk", dataBorangFInBulk);
 		context.put("saiz_dataBorangFInBulk", dataBorangFInBulk.size());
		
	}//close getDataNotisInBulk
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listBorangFInBulk(String id_hakmilik) throws Exception{
		
	//	model.setListBorangFInBulk(id_hakmilik);
		//Vector listBorangFInBulk = model.getListBorangFInBulk();
		//context.put("listBorangFInBulk", listBorangFInBulk);
		context.put("saiz_listBorangFInBulk", model.getListBorangFInBulk_count(id_hakmilik));
 		
	}//close listBorangEInBulk
	
	@SuppressWarnings("unchecked")
	private void simpanBorangFInBulk(HttpSession session) throws Exception{
	
		Hashtable h = new Hashtable();
		
		h.put("txtTempoh", getParam("txtTempoh"));
		h.put("txtKeterangan", getParam("txtKeterangan"));	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		long id_borangf = DB.getNextID("TBLPPTBORANGF_SEQ");
    	
		FrmUPTSek8BorangFData.simpanBorangF(h,id_borangf);
    	
    	String[] cbsemaks = request.getParameterValues("cbsemaks");
    	if(cbsemaks!=null){
			for (int i = 0; i < cbsemaks.length; i++) { 
				myLogger.info("Insert Borang F :"+cbsemaks[i]);
				FrmUPTSek8BorangFData.simpanBorangFInBulk(h,cbsemaks[i],id_borangf);
			}
		}
			
	}//close simpanBorangFInBulk
	
	private void resetValueBorangF() throws Exception{
    	
		context.put("txtTempoh", "");
		context.put("txtKeterangan", "");
    	
	}//close resetValue

	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPB(HttpSession session,String idHakmilik,String id_borangf) throws Exception{
    	
		Vector listPB = new Vector();
		listPB.clear();
		
		model.setListPBInBulk(idHakmilik,id_borangf);
    	listPB = model.getListPBInBulk();
    	context.put("listMaklumatPB", listPB);
    	
	}//close listPB
	
	@SuppressWarnings("unchecked")
	private void maklumatHakmilik(String id_hakmilik) throws Exception{
		
		//data hakmilik
		modelUPT.setMaklumatTanah(id_hakmilik);
		Vector dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
    	
	}//close listHakmilikBorangE
	
	@SuppressWarnings("unchecked")
	private void listHakmilikBorangE(String id_borange) throws Exception{
		
		//Vector listHM = FrmUPTSek8BorangFData.getListHM(id_borange);
    	//context.put("listHM", listHM);
    	
	}//close listHakmilikBorangE
	
	@SuppressWarnings("unchecked")
	private void updateBorangEInBulk(HttpSession session,String id_borange, Db db,String id_hakmilik) throws Exception{
    
	    Hashtable h = new Hashtable();
	    String idUser = (String)session.getAttribute("_ekptg_user_id");
	    
		h.put("id_borange", id_borange);
		h.put("socBandar", getParam("socBandar"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("txdTarikhBorangE", getParam("txdTarikhBorangE"));
    	h.put("txdTarikhBorangF", getParam("txdTarikhBorangF"));
    	h.put("txdTarikhSiasatan", getParam("txdTarikhSiasatan"));
    	
//    	h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
//    	h.put("socJenisWaktu", getParam("socJenisWaktu"));
    	h.put("txtAlamat1", getParam("txtAlamat1"));
    	h.put("txtAlamat2", getParam("txtAlamat2"));
    	h.put("txtAlamat3", getParam("txtAlamat3"));
    	h.put("txtPoskod", getParam("txtPoskod"));  	
    	h.put("id_hakmilik", getParam("id_hakmilik"));   
    	h.put("txtAlamat1", getParam("txtAlamat1"));
    	h.put("id_user", idUser);
		
		FrmUPTSek8BorangFData.updateBorangE(h,db);
		myLogger.info("updateBorangEInBulk ID_BorangE = " +id_borange);
		
//		delete senarai notis dalam attribute table
		FrmUPTSek8BorangFData.deleteListCB(id_borange,db);
		

    	String[] cb_id_hakmilik = request.getParameterValues("cbsemaks");
    	myLogger.info("value id hakmilik : "+ request.getParameterValues("cbsemaks"));
		
		if(cb_id_hakmilik!=null){
			for (int i = 0; i < cb_id_hakmilik.length; i++) { 
				int bil = i+1;
				String txtMasaSiasatan = getParam("txtMasaSiasatan"+bil);
				String socJenisWaktu = getParam("socJenisWaktu"+bil);
				String txtCatatan= getParam("txtCatatan"+bil);
				String id_borangehakmilik = getParam("id_borangehakmilik"+bil);
				myLogger.info("txtMasaSiasatan :"+txtMasaSiasatan);
				
				if(id_borangehakmilik.equals("")){
					long id_borangehakmilik_seq = DB.getNextID("TBLPPTBORANGEHAKMILIK_SEQ");
					simpanBorangE(idUser,cb_id_hakmilik[i],id_borangehakmilik_seq,txtMasaSiasatan,socJenisWaktu,txtCatatan,id_borange,db);
				}else{
					updateBorangE(idUser,cb_id_hakmilik[i],id_borangehakmilik,txtMasaSiasatan,socJenisWaktu,txtCatatan,id_borange,db);
				}
			
			}
		}
		
	}//close updateBorangEInBulk
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getDataBorangEInBulk(String id_borange,String isEdit) throws Exception{
		
		String id_negeri = "", id_bandar = "";
		model.setDataBorangEInBulk(id_borange,"");
		Vector dataBorangEInBulk = model.getDataBorangEInBulk();
		if(dataBorangEInBulk.size()!=0){
			Hashtable dn = (Hashtable)dataBorangEInBulk.get(0);
			id_negeri = (String)dn.get("id_negeri");
			id_bandar = (String)dn.get("id_bandar");
		}
		
		context.put("dataBorangEInBulk", dataBorangEInBulk);
 		context.put("saiz_dataBorangEInBulk", dataBorangEInBulk.size());
		
 		String mode = "";
 		if(!isEdit.equals("yes")){
 			mode = "class=disabled disabled";
 		}
 		
 		//dropdown
 		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px "+mode+" onChange=\"onchangeDropdown();\""));
    	context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:300px "+mode+" "));

	}//close getDataNotisInBulk
	
	@SuppressWarnings("unchecked")
	private void simpanBorangEInBulk(HttpSession session,String idpermohonan,String idstatus,Db db) throws Exception{
			    
		Hashtable h = new Hashtable();
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		
		h.put("id_permohonan", idpermohonan);
		//h.put("id_borange", id_borange);
		h.put("socBandar", getParam("socBandar"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("txdTarikhBorangE", getParam("txdTarikhBorangE"));
    	h.put("txdTarikhBorangF", getParam("txdTarikhBorangF"));
    	h.put("txdTarikhSiasatan", getParam("txdTarikhSiasatan"));
    //	h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
    	//h.put("socJenisWaktu", getParam("socJenisWaktu"));
    	h.put("txtAlamat1", getParam("txtAlamat1"));
    	h.put("txtAlamat2", getParam("txtAlamat2"));
    	h.put("txtAlamat3", getParam("txtAlamat3"));
    	h.put("txtPoskod", getParam("txtPoskod"));  	
    	h.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	h.put("id_hakmilik", getParam("id_hakmilik"));
    	
    	h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
    		
		h.put("id_user", idUser);
		
		if(idstatus.equals("35") || idstatus.equals("74")){
			FrmUPTSek8BorangFData.updateStatus(h,idpermohonan);
    	}
		
		long id_borange = getNextID("TBLPPTBORANGE_SEQ",db);
		String id_borangeString = "";
		id_borangeString = String.valueOf(id_borange);
    	myLogger.info(" id_borange ::::"+id_borange);
    	FrmUPTSek8BorangFData.simpanBorangE(h,id_borange,db);

		myLogger.info("simpanBorangEInBulk simpanBorangE [hashtable]: " +h);    	
 	
    	String[] cb_id_hakmilik = request.getParameterValues("cbsemaks");
    	myLogger.info("value id hakmilik : "+ request.getParameterValues("cbsemaks"));
		
		if(cb_id_hakmilik!=null){
			for (int i = 0; i < cb_id_hakmilik.length; i++) { 
				int bil = i+1;
				String txtMasaSiasatan = getParam("txtMasaSiasatan"+bil);
				String socJenisWaktu = getParam("socJenisWaktu"+bil);
				String txtCatatan = getParam("txtCatatan"+bil);
				String id_borangehakmilik = getParam("id_borangehakmilik"+bil);
				myLogger.info("txtMasaSiasatan :"+txtMasaSiasatan);
				
				if(id_borangehakmilik.equals("")){
					long id_borangehakmilik_seq = DB.getNextID("TBLPPTBORANGEHAKMILIK_SEQ");
					simpanBorangE(idUser,cb_id_hakmilik[i],id_borangehakmilik_seq,txtMasaSiasatan,socJenisWaktu, txtCatatan, id_borangeString,db);
				}else{
					updateBorangE(idUser,cb_id_hakmilik[i],id_borangehakmilik,txtMasaSiasatan,socJenisWaktu, txtCatatan, id_borangeString,db);
				}
			
			}
		}
		
	}//close simpanBorangL
    	

	
	
	public synchronized static long getNextID(String seqName, Db db) throws Exception {
		//Db db = null;
		//original 
		//String sql = "select " + seqName + ".NEXTVAL FROM DUAL ";
		
		//Get State code from dbconnection.properties
		String statecode = StateLookup.getInstance().getTitle("StateCode");
		String sql = "select " + statecode + " || to_char(sysdate,'YY') || " +seqName + ".NEXTVAL  FROM DUAL ";
		try {
		//	db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getLong(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
		//	if (db != null)
		//		db.close();
		}
	}

	
	private void getAndSetDataBorangEInBulk() throws Exception{
		
		context.put("onchangeEdit","yes");
		context.put("isEdit","yes");
		
		String id_negeri = getParam("socNegeri");
		
		//dropdown
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px onChange=\"onchangeDropdown();\""));

 		if(id_negeri!=""){
 			context.put("selectBandar",HTML.SelectBandarByNegeri(id_negeri,"socBandar",null,"style=width:300px"));
 		}else{
 			context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:300px"));
 		}
    	
 		context.put("txdTarikhBorangE", getParam("txdTarikhBorangE"));
    	context.put("txdTarikhBorangF", getParam("txdTarikhBorangF"));
    	context.put("txdTarikhSiasatan", getParam("txdTarikhSiasatan"));
    	context.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
    	context.put("socJenisWaktu", getParam("socJenisWaktu"));
    	context.put("txtAlamat1", getParam("txtAlamat1"));
    	context.put("txtAlamat2", getParam("txtAlamat2"));
    	context.put("txtAlamat3", getParam("txtAlamat3"));
    	context.put("txtPoskod", getParam("txtPoskod"));  	
    	context.put("txdTarikhTampal", getParam("txdTarikhTampal"));
    	
    	String[] cbsemaks = request.getParameterValues("cbsemaks");
    	if(cbsemaks!=null){  		
    		Set<String> selectedItem = new HashSet<String>(Arrays.asList(cbsemaks));
    		context.put("selectedItem",selectedItem);  		
		}
		
	}//close getAndSetData
	

	@SuppressWarnings({ "unchecked", "static-access" })
	private void listHMBorangEInBulk(String id_permohonan,String id_borange,String mode) throws Exception{
		
		model.setListHakmilikBorangEInBulk(id_permohonan,id_borange,mode);
		Vector listHakmilikBorangEInBulk = model.getListHakmilikBorangEInBulk();		
		context.put("listHakmilikBorangEInBulk", listHakmilikBorangEInBulk);
 		context.put("saiz_listHakmilikBorangEInBulk", listHakmilikBorangEInBulk.size());
 		
	}//close listHMBorangEInBulk
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listBorangEInBulk(String id_permohonan,String carianLotHakmilik) throws Exception{
		//context.put("carianLotHakmilik", carianLotHakmilik);
		//model.setListBorangEInBulk(id_permohonan,carianLotHakmilik);
		//Vector listBorangEInBulk = model.getListBorangEInBulk();
		//context.put("listBorangEInBulk", listBorangEInBulk);
		
		
 		context.put("saiz_listBorangEInBulk", model.getListBorangEInBulk_count(id_permohonan,carianLotHakmilik));
 		
	}//close listBorangEInBulk
	
//	@SuppressWarnings({ "unchecked", "static-access" })
//	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
//    
//		Hashtable h = new Hashtable();
//		
//		h.put("id_permohonan", id_permohonan);
//		h.put("id_fail", id_fail);
//		h.put("id_hakmilik", id_hakmilik);
//		h.put("id_user", session.getAttribute("_ekptg_user_id"));
//		
//		modelUPT.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"1478");
//	
//	}//close addSuburusanHakmilik
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1478");
		
	}//close updateSuburusanStatusFailPPT
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmUPTSek8BorangFData.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void resetValueBorangE(String id_permohonan) throws Exception{
    	
		model.setListHakmilikBorangEInBulk(id_permohonan,"","new");
		Vector listHakmilikBorangEInBulk = model.getListHakmilikBorangEInBulk();		
		context.put("listHakmilikBorangEInBulk", listHakmilikBorangEInBulk);
 		context.put("saiz_listHakmilikBorangEInBulk", listHakmilikBorangEInBulk.size());
 		
 		String mode = "";
 		if(listHakmilikBorangEInBulk.size()==0){
 			mode = "class=disabled disabled";
 		}
 		
		//dropdown
 		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,null,"style=width:300px "+mode+" onChange=\"onchangeDropdown();\""));
    	context.put("selectBandar",HTML.SelectBandar("socBandar",null," "+mode+" style=width:300px"));
    	
		context.put("txdTarikhBorangE", "");
    	context.put("txdTarikhBorangF", "");
    	context.put("txdTarikhSiasatan", "");
    	context.put("txtMasaSiasatan", "");
    	context.put("socJenisWaktu", "");
    	context.put("txtAlamat1", "");
    	context.put("txtAlamat2", "");
    	context.put("txtAlamat3", "");
    	context.put("txtPoskod", "");
    	context.put("txdTarikhTampal", "");
    	context.put("selectedItem","");
    	
	}//close resetValueBorangE
	
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
	
	//penambahan-yati v7
	//functionbaru - 4/9/2020
		private void simpanBorangEHakmilikInBulk(HttpSession session,String idpermohonan,String id_borange,Db db) throws Exception{

			Hashtable h = new Hashtable();
			String idUser = (String)session.getAttribute("_ekptg_user_id");
				
			//h.put("txdTarikhBorangL", getParam("txdTarikhBorangL"));
			//h.put("txtTempoh", getParam("txtTempoh"));
			
			h.put("id_permohonan", idpermohonan);	
			h.put("id_borange", id_borange);	
	    	h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
	    	h.put("socJenisWaktu", getParam("socJenisWaktu"));
	   
	    	h.put("id_hakmilik", getParam("id_hakmilik"));
			
			h.put("id_user", idUser);
			
			String[] cb_id_hakmilik = request.getParameterValues("id_hakmilik");
			
			if(cb_id_hakmilik!=null){
				for (int i = 0; i < cb_id_hakmilik.length; i++) { 
					int bil = i+1;
					String txtMasaSiasatan = getParam("txtMasaSiasatan"+bil);
					String socJenisWaktu = getParam("socJenisWaktu"+bil);
					String txtCatatan = getParam("txtCatatan"+bil);
					String id_borangehakmilik = getParam("id_borangehakmilik"+bil);
					id_borange = getParam("id_borange"+bil);
				
					myLogger.info("id_borangehakmilik :"+id_borangehakmilik);

			
					if(id_borange.equals("")){
						long id_borangehakmilik_seq = DB.getNextID("TBLPPTBORANGEHAKMILIK_SEQ");
						simpanBorangE(idUser,cb_id_hakmilik[i],id_borangehakmilik_seq,txtMasaSiasatan,socJenisWaktu,txtCatatan,id_borange,db);
					}else{
						updateBorangE(idUser,cb_id_hakmilik[i],id_borangehakmilik,txtMasaSiasatan,socJenisWaktu,txtCatatan,id_borange,db);
					}
				
				}
			}
			
		}//close simpanBorangL
		
		//function borang e-4/9/2020
		public static void simpanBorangE(String idUser,String id_hakmilik,long id_borangehakmilik, String txtMasaSiasatan, String socJenisWaktu, String txtCatatan, String id_borange,Db db) throws Exception{
			
			myLogger.info("DATA SIMPAN BORANGE HAKMILIK");
			//   Db db = null;
			    String sql = "";

			    try{
			   // 	db = new Db();
			    	Statement stmt = db.getStatement();
			 	    SQLRenderer r = new SQLRenderer();
			 	    r.add("id_borangehakmilik", id_borangehakmilik);
			 	    r.add("id_hakmilik", id_hakmilik);
			 	    r.add("id_borange", id_borange);
			 	    r.add("masa_siasatan", txtMasaSiasatan);
			 	    r.add("jenis_waktu",socJenisWaktu);    
			 	    r.add("catatan", txtCatatan);
			 	    r.add("id_masuk",idUser);   
			 	    r.add("tarikh_masuk",r.unquote("sysdate"));
			 	    sql = r.getSQLInsert("tblpptborangehakmilik");
			 	    stmt.executeUpdate(sql);
			 	    myLogger.info("simpanBorangE tblpptborangEHAKMILIK: " +sql);
			 	    
			    }//close try 
			    finally {
			 //     if (db != null) db.close();
			    }//close finally
			   
			  }//close simpanBorangE
		
		public static void updateBorangE(String idUser,String id_hakmilik,String id_borangehakmilik, String txtMasaSiasatan, String socJenisWaktu, String txtCatatan,String id_borange,Db db) throws Exception{
			
			   // Db db = null;
			    String sql = "";

			    try{
			    //	db = new Db();
			    	Statement stmt = db.getStatement();
			 	    SQLRenderer r = new SQLRenderer();
			 	    r.update("id_borangehakmilik", id_borangehakmilik);
			 	    r.update("id_hakmilik", id_hakmilik);
			 	    r.add("masa_siasatan",txtMasaSiasatan);  
			 	    r.add("jenis_waktu",socJenisWaktu);  
			 	    r.add("catatan",txtCatatan);  
			 	    r.add("id_borange",id_borange); 
			 	    r.add("id_kemaskini",idUser);   
			 	    r.add("tarikh_kemaskini",r.unquote("sysdate"));
			 	    sql = r.getSQLUpdate("tblpptborangehakmilik");
			 	    stmt.executeUpdate(sql);
			 	    myLogger.info("UPDATE TBLPPTBORANGEHAKMILIK : "+sql);
			 	    
			    }//close try 
			    finally {
			      //if (db != null) db.close();
			    }//close finally
			   
			  }//close updateBorangE
		
	
}//close class
