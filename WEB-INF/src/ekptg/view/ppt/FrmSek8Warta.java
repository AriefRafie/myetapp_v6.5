package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmMMKSek8Data;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8WartaData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmSek8Warta extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek8Warta.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmMMKSek8Data modelMMK = new FrmMMKSek8Data();
	FrmSek8WartaData model = new FrmSek8WartaData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for paging
    	String action = getParam("action");
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String id_negeri_user = (String) session.getAttribute("_ekptg_user_negeri");
    	
	
    	String vm = "";
    	String noLOT = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSek8WartaSenarai.jsp";
    	String mainscreen = "app/ppt/frmSek8Warta.jsp";
    	String screenwarta = "app/ppt/frmSek8WartaTambah.jsp";
    	String screenViewHM = "app/ppt/frmSek8WartaViewHM.jsp";
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
		}finally {
			if (db != null)
			db.close();
		}
		*/
    	
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
		/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "7");
    	}
    	*/
    	context.put("paging", "7");
		
		//header
		String id_status = "";
		String id_fail = "";
		String flagSegera = "";
		String flag_subjaket = "";
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
				id_fail = (String)dh.get("id_fail");
				flagSegera = dh.get("flag_segera").toString();
				flag_subjaket = dh.get("flag_subjaket").toString();
				
				Vector list_sub = null;
				list_sub = header.listPerjalananFail(idpermohonan);
				this.context.put("list_sub_header", list_sub);
			}
    	}

		context.put("flagSegera",flagSegera);
		context.put("flag_subjaket",flag_subjaket);
		
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
	    Vector listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    */
		
		String userIdNeg = id_negeri_user;
	    
	   
	    //GET NAMA PENGARAH
	    String nama_pengarah = "";
	    if(!idpermohonan.equals(""))
    	{
		    modelUPT.setNamaPengarah(userIdNeg);
		    Vector dataNamaPengarah = modelUPT.getNamaPengarah();
		    if(dataNamaPengarah.size()!=0){
		    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
		    	nama_pengarah = np.get("nama_pengarah").toString();
		    }
    	}
	    
	    context.put("nama_pengarah",nama_pengarah);
		
	    //default list
    	//listPageDepan = model.getListPermohonan(userIdNeg);
	    
		//get tarikh hantar
	    String tarikh_mmk = "";
	    if(!idpermohonan.equals(""))
    	{
			modelMMK.setDataMMK(idpermohonan);
	    	Vector dataMMK = modelMMK.getDataMMK();    	
	    	if(dataMMK.size()!=0){
	    		Hashtable dm = (Hashtable)dataMMK.get(0);
	    		tarikh_mmk = (String)dm.get("tarikh_mmk");
	    	}
    	}
		
    	//date for validation
    	context.put("tarikh_mmk", tarikh_mmk);
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("showAlertPaging","no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewSenaraiWarta".equals(submit)){
    		
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		/*String id_endosanborangd = getParam("id_endosanborangd");
    		context.put("id_endosanborangd","");
    	*/	
    		//list Hakmilik
    		listHakmilik(session,idpermohonan,noLOT);
    		
    		//list Warta
    		listWarta(session,idpermohonan);
    		
    		//screen
    		vm = mainscreen;   		
    
    	}//close 
    	
    	else 
        if("viewHM".equals(submit)){
        		
        	//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//carian
    		noLOT = getParam("carianNoLot2");
    		context.put("carianNoLot2", noLOT.trim());
    		
    		String idHakmilik = getParam("id_hakmilik");
    		
    		//list Hakmilik
    		listHakmilik(session,idpermohonan,noLOT);
    		
    		//data hakmilik
    		dataHakmilik(session,idHakmilik);
    		
    		//screen
    		vm = screenViewHM;
    		
        }//close viewHM
    	
    	else 
    	if("tambahWarta".equals(submit)){
    		
    		//form validation
    		context.put("mode","new");
    		context.put("id_endosanborangd","");
    		context.put("id_warta","");
    		context.put("txdTarikhBorangD","");
    		context.put("txtCatatanBorangD","");
    		context.put("txtPerserahan","");
    		context.put("txdTarikhCatatan","");
    		context.put("txtMasaCatatan","");
    		context.put("txdTarikhTerima","");
    		
    		//list Warta
    		listWarta(session,idpermohonan);
    		
    	/*	String id_endosanborangd = getParam("id_endosanborangd");
    		context.put("id_endosanborangd","");
    	*/	
    		System.out.println("id_status---"+id_status);
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("simpanWarta".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			//simpan data
        			simpanWarta(session); 
        			
        			if(id_status.equals("134")){		
        				hantar(session);
        				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
        			}
        			//penambahan yati- utk update pengwartaan id status
        			else if(id_status.equals("26")){		
        				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
        			}
        		}
        		
        		//list Warta
        		listWarta(session,idpermohonan);
        		
        		
            	//header.setDataHeader(idpermohonan);
        		//dataHeader = header.getDataHeader();
        		
        		if(dataHeader.size()!=0){
        			Hashtable dh = (Hashtable) dataHeader.get(0);
        			id_status = (String)dh.get("id_status");
        		}
        		
        		if(id_status.equals("31")){
        	//		JOptionPane.showMessageDialog (null, "Sila Klik Butang Kembali Dan Klik 'Paging' No.11 Untuk Proses Endorsan", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
        			context.put("showAlertPaging","yes");
        		}else{
        			context.put("showAlertPaging","no");
        		}
        		
        	}//close simpanWarta
    		
    		//screen
    		vm = screenwarta; 
    		
    	}//close tambahWarta
    	
    	else 
        if("viewWarta".equals(submit)){
        		
        	//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		String id_warta = getParam("id_warta");
    		context.put("id_warta",id_warta);
    		
    		String id_endosanborangd = getParam("id_endosanborangd");
    		context.put("id_endosanborangd",id_endosanborangd);
    		
    		//list Warta
    		listWarta(session,idpermohonan);
    		
    		//data Warta
    		dataWarta(session,id_warta);			
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniWarta".equals(submit2)){
        		
        		//form validation
        		context.put("isEdit","yes");
        		
        		id_warta = getParam("id_warta");
        		context.put("id_warta",id_warta);
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("updateWarta".equals(submit3)){
            		
            		updateWarta(session);
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//list Warta
            		listWarta(session,idpermohonan);
            		
            		//data Warta
            		dataWarta(session,id_warta);
            		
            		
            	}//close updateWarta
            	
        	}//close kemaskiniWarta
        	
    		//screen
    		vm = screenwarta; 
    		
        }//close viewWarta
    	
        else 
    	if("hapusWarta".equals(submit)){
    			
    		hapusWarta(session);
    		
    		//list Hakmilik
    		listHakmilik(session,idpermohonan,noLOT);
    		
    		//list Warta
    		listWarta(session,idpermohonan);
    		
    		//screen
    		vm = mainscreen;  
    		
    	}//close hapus
    	
    	else 
    	if("hantar".equals(submit)){
        		
    		if (doPost.equals("true")) {
    			//hantar
    			hantar(session);
    		}
    		
    		listPageDepan = model.getListPermohonan(userIdNeg);
    		
    		context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");
    	
    		//screen
        	vm = listdepan;
    		    
        }//close hantar
    	
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
    		this.context.put("selectedTab",selectedTab);
    		myLogger.info("VM : "+vm);
    		return vm;
    		
	    }//close public template
	
	
	
	
//--METHOD--//	
	
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
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1471");
		
	}//close updateSuburusanStatusFailPPT
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmSek8WartaData.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings("unchecked")
	private void hantar(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8WartaData.hantar(h);
  
	}//close hantar
	
	@SuppressWarnings("unchecked")
	private void dataHakmilik(HttpSession session,String idHakmilik) throws Exception{
    	
		//data & list maklumat tanah
		modelUPT.setMaklumatTanah(idHakmilik);
		Vector dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		String id_mukim = "";
		String id_jenishakmilik = "";
		String id_luaslot = "";
		String id_lot = "";
		String id_kategoritanah = "";
		if(dataMaklumatTanah.size()!=0){
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_mukim = h.get("id_mukim").toString();
			id_jenishakmilik = h.get("id_jenishakmilik").toString();
			id_luaslot = h.get("id_luasLot").toString();
			id_lot = h.get("id_lot").toString();
			id_kategoritanah = h.get("id_kategoritanah").toString();
		}
		
    	//dropdown (view)
    	context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("jenisHakMilik",Utils.parseLong(id_jenishakmilik),"style=width:auto class=disabled disabled"));   		
    	context.put("selectUnitLuas",HTML.SelectLuas("unitLuas",Utils.parseLong(id_luaslot),"style=width:auto class=disabled disabled"));
    	context.put("selectKodLot", HTML.SelectLot("kodLot",Utils.parseLong(id_lot),"style=width:auto class=disabled disabled"));
    	context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(id_mukim),"style=width:auto class=disabled disabled"));
    	context.put("selectKategoriTanah",HTML.SelectKategoriTanah("socKategoriTanah",Utils.parseLong(id_kategoritanah),"style=width:auto class=disabled disabled",null));
    	
	}//close dataHakmilik

	private void hapusWarta(HttpSession session) throws Exception{
    	
		String idWarta = getParam("id_warta");	
		FrmSek8WartaData.hapusWarta(idWarta);
  
	}//close hapusHM

	@SuppressWarnings("unchecked")
	private void listHakmilik(HttpSession session,String idpermohonan,String noLOT) throws Exception{
    	
		String idpegawai = "";
			
		//list maklumat tanah
		/*
		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 		Vector listMaklumatTanah = modelUPT.getListMaklumatTanah();
 		String id_hakmilik = "";
 		if(listMaklumatTanah.size()!=0){
 			Hashtable lm = (Hashtable)listMaklumatTanah.get(0);
 			id_hakmilik = (String)lm.get("id_hakmilik");
 		}
 		context.put("id_hakmilik", id_hakmilik);
 		context.put("listMaklumatTanah", listMaklumatTanah);*/
 		//context.put("saiz_listTanah", listMaklumatTanah.size());
 		context.put("saiz_listTanah",  modelUPT.setListMaklumatTanah_count(idpermohonan, noLOT, ""));
		
	}//close listHakmilik

	@SuppressWarnings("unchecked")
	private void dataWarta(HttpSession session,String idWarta) throws Exception{
    	
		//data Warta
		model.setDataWarta(idWarta);
 		Vector dataWarta = model.getDataWarta();
 		String id_warta = "";
 		String id_endosanborangd = "";
 		if(dataWarta.size()!=0){
 			Hashtable dw = (Hashtable)dataWarta.get(0);
 			id_warta = (String)dw.get("id_warta");
 			id_endosanborangd = (String)dw.get("id_endosanborangd");
 		} 
 		context.put("id_warta", id_warta);
 		context.put("id_endosanborangd", id_endosanborangd);
 		context.put("dataWarta", dataWarta);
 		
		
	}//close dataWarta
	
// data Borang D
	/*private void dataBorangD(HttpSession session,String idEndosanBorangD) throws Exception{
    	
		//data Borang
		model.setDataBorangD(idEndosanBorangD);
 		Vector dataBorangD = model.getDataBorangD();
 		String id_endosanborangd = "";
 		if(dataBorangD.size()!=0){
 			Hashtable dw = (Hashtable)dataBorangD.get(0);
 			id_endosanborangd = (String)dw.get("id_endosanborangd");
 		}
 		context.put("id_endosanborangd", id_endosanborangd);
 		context.put("dataBorangD", dataBorangD);
		
	}//close databorang
*/	
	@SuppressWarnings("unchecked")
	private void listWarta(HttpSession session,String idpermohonan) throws Exception{
    	
		//list Warta
		model.setListWarta(idpermohonan);
 		Vector listWarta = model.getListWarta();
		
		//list warta
 		context.put("listWarta", listWarta);
 		context.put("saiz_listWarta", listWarta.size());
		
	}//close listHakmilik
	
	@SuppressWarnings("unchecked")
	private void simpanWarta(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("socWarta", getParam("socWarta"));
		h.put("txtNoWarta", getParam("txtNoWarta"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		h.put("txdTarikhHantarBorangD", getParam("txdTarikhHantarBorangD"));
		h.put("txtNoKenyataan", getParam("txtNoKenyataan"));
		h.put("txdTarikhTerimaWarta", getParam("txdTarikhTerimaWarta"));
		
		//tblpptendosanborangd
		h.put("id_endosanborangd", getParam("id_endosanborangd"));
		h.put("txdTarikhBorangD", getParam("txdTarikhBorangD"));	
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));		
		h.put("txdTarikhCatatan", getParam("txdTarikhCatatan"));
		h.put("txtMasaCatatan", getParam("txtMasaCatatan"));
		h.put("txtCatatanBorangD", getParam("txtCatatanBorangD"));
		h.put("txtPerserahan", getParam("txtPerserahan"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8WartaData.simpanWarta(h);
  
	}//close simpanWarta
	
	@SuppressWarnings("unchecked")
	private void updateWarta(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_warta", getParam("id_warta"));
		h.put("socWarta", getParam("socWarta"));
		h.put("txtNoWarta", getParam("txtNoWarta"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		h.put("txdTarikhHantarBorangD", getParam("txdTarikhHantarBorangD"));
		h.put("txtNoKenyataan", getParam("txtNoKenyataan"));
		h.put("txdTarikhTerimaWarta", getParam("txdTarikhTerimaWarta"));
		
		h.put("id_endosanborangd", getParam("id_endosanborangd"));
		h.put("txdTarikhBorangD", getParam("txdTarikhBorangD"));	
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));		
		h.put("txdTarikhCatatan", getParam("txdTarikhCatatan"));
		h.put("txtMasaCatatan", getParam("txtMasaCatatan"));
		h.put("txtCatatanBorangD", getParam("txtCatatanBorangD"));
		h.put("txtPerserahan", getParam("txtPerserahan"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		h.put("id_permohonan", getParam("id_permohonan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8WartaData.updateWarta(h);
  
	}//close updateWarta
	
	@SuppressWarnings({ "unchecked", "static-access", "unused" })
	private String dataHeaderStatus(HttpSession session,String idpermohonan) throws Exception{
    	
		//header
		String id_status = "";
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
		}
		
		return id_status;
		
	}//close dataHeaderStatus

	
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
