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
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8BorangKData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmSek8BorangL extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek8BorangL.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmSek8BorangKData model = new FrmSek8BorangKData();
	PPTHeader header = new PPTHeader();
	
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	String vm = "";
    	String noLOT = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	
    	dataSuburusanHakmilik.clear();
    	getIdSuburusanstatusfail.clear();
    	listMaklumatTanah.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/BorangL/frmSek8BorangLSenarai.jsp";
    	String listHMscreen = "app/ppt/BorangL/frmSek8BorangLListHM.jsp";
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
		//user login id
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
	    String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");	    
	    context.put("userIdNeg", userIdNeg);
	    
	    /*
	    Db dbx = null;
		try {
			dbx = new Db();
			
			if (checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilikBorangL", dbx) == 0) {
				// reg class
				insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilikBorangL",
						"Skrin Capaian Hakmilik (Borang L)", "EKPTG - PPT", dbx);
			}
			
		} finally {
			if (dbx != null)
				dbx.close();
		}
		*/
		
	   
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
			id_fail = dh.get("id_fail").toString();
			flag_subjaket = dh.get("flag_subjaket").toString();
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
    	}

		context.put("flag_subjaket",flag_subjaket);
		context.put("flagSegera",flagSegera);
		
		//default list
    	//listPageDepan = model.getListPermohonan(userIdNeg);
    	
		//paging
    	/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "19");
    	}
    	*/
    	context.put("paging", "19");
    	
    	
		//default
		context.put("isEdit","no");
		
		//data & list maklumat tanah
		if(!idpermohonan.equals(""))
    	{
		//listMaklumatTanah(idpermohonan,noLOT,flagSegera);
    	}
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	String submit2 = getParam("command2");
    	myLogger.info("submit2 : " + submit2);
    	
    	String submit3 = getParam("command3");
    	myLogger.info("submit3 : " + submit3);
    	
    	if("viewListHM".equals(submit)){
    		
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//data & list maklumat tanah
    		listMaklumatTanah(idpermohonan,noLOT,flagSegera);
     		
    		if("kemaskiniBorangL".equals(submit2)){
    			
    			context.put("isEdit","yes");
    			
    			if("simpanBorangL".equals(submit3)){
    				
    				if (doPost.equals("true")) {
    					context.put("isEdit","no");
    					simpanBorangL(session,idpermohonan);
    				}
    				
    				//data & list maklumat tanah
    	    		listMaklumatTanah(idpermohonan,noLOT,flagSegera);
    	    		
    			}//close simpanBorangL
    			
    		}//close kemaskiniBorangL
    		
        	//screen
    		vm = listHMscreen;
    		
    	}//close viewListHM
    	
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
	    	context.put("id_status", id_status);
	    	context.put("id_fail", id_fail);
	    	
    		setupPage(session,action,listPageDepan);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanBorangL(HttpSession session,String idpermohonan) throws Exception{

		Hashtable h = new Hashtable();
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		h.put("id_permohonan", idpermohonan);		

		h.put("txdTarikhBorangL", getParam("txdTarikhBorangL"));
		h.put("txtTempoh", getParam("txtTempoh"));
		
		h.put("id_user", idUser);
		
		String[] cb_id_hakmilik = request.getParameterValues("id_hakmilik");
		
		if(cb_id_hakmilik!=null){
			for (int i = 0; i < cb_id_hakmilik.length; i++) { 
				int bil = i+1;
				String dateBorangL = getParam("txdTarikhBorangL"+bil);
				String socStatusBL = getParam("sorStatusBorangL"+bil);
				String id_borangl = getParam("id_borangl"+bil);
				String tempoh = getParam("txtTempoh"+bil);
		
				if(id_borangl.equals("")){
					long id_borangl_seq = DB.getNextID("TBLPPTBORANGL_SEQ");
					model.simpanBorangL(idUser,cb_id_hakmilik[i],id_borangl_seq,dateBorangL,socStatusBL,tempoh);
				}else{
					model.updateBorangL(idUser,cb_id_hakmilik[i],id_borangl,dateBorangL,socStatusBL,tempoh);
				}
			
			}
		}
		
	}//close simpanBorangL
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmSek8BorangKData.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings("unchecked")
	private void listMaklumatTanah(String idpermohonan,String noLOT,String flagSegera) throws Exception{
    /* */
	/*	Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();
		
		model.setListHMBorangK(idpermohonan,noLOT,flagSegera);
 		listMaklumatTanah = model.getListHMBorangK();
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 	*/	
 	
 		context.put("saiz_listTanah", model.setListHMBorangK_count(idpermohonan,noLOT,flagSegera));
 		
	}//close listMaklumatTanah
	
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
	
	public void insertPopupReg(String nama_class, String tajuk_class,
			String group, Db db) throws Exception {
		// Db db = null;
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			String sql = " INSERT INTO MODULE ( "
					+ " MODULE_ID, MODULE_TITLE, MODULE_CLASS,  "
					+ " MODULE_GROUP, MODULE_DESCRIPTION)  " + " VALUES ('"
					+ nama_class + "','" + tajuk_class + "','" + nama_class
					+ "','" + group + "','') ";
			myLogger.info("REG CLASS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			sql = " INSERT INTO ROLE_MODULE ( "
					+ " MODULE_ID, USER_ROLE) "
					+ " SELECT '"
					+ nama_class
					+ "' AS MODULE_ID,NAME AS USER_ROLE FROM ROLE WHERE UPPER(NAME) LIKE '%PPT%'";
			myLogger.info("REG ROLE CLASS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public int checkRegPopup(String class_name, Db db) throws Exception {

		// Db db = null;
		int total = 0;
		String sql = "";
		ResultSet rs = null;
		try {
			// db = new Db();
			sql = " SELECT COUNT(*) AS CHECK_COUNT FROM ROLE_MODULE WHERE MODULE_ID = '"
					+ class_name + "'";
			rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} finally {
			// Close the database connection
			// if ( db != null ) db.close();
			// if (rs != null) rs.close();
		}
		return total;
	}
	
}//close class
