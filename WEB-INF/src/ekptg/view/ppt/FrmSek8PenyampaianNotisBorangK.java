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
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8PenyampaianNotisData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmSek8PenyampaianNotisBorangK extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek8PenyampaianNotis.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmSek8PenyampaianNotisData model = new FrmSek8PenyampaianNotisData();
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
    	
    	String vm = "";
    	String noLOT = "";
    	String idpegawai = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector dataMaklumatTanah = new Vector();
    	
    	dataMaklumatTanah.clear();
    	listPageDepan.clear();
   	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	//userData(id_user);
    	//String userIdNeg = userData(id_user);
    	
    	//screen jsp
    	String listdepan = "app/ppt/frmSek8PenyampaianNotisBorangKSenarai.jsp";
    	String mainscreen = "app/ppt/frmSek8PenyampaianNotisBorangK.jsp";
    	String listHMscreen = "app/ppt/frmSek8PenyampaianNotisBorangKListHM.jsp";
    	
    	//default list
    	//listPageDepan = model.getListPermohonan("3",userIdNeg);

    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
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
    		context.put("paging", "20");
    	}
    	*/
    	context.put("paging", "20");
    	/*
    	Db db = null;
		try {
		db = new Db();
	    	
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT",db);
	    	}
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianPBNotis",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianPBNotis","Skrin Capaian PB NOTIS", "EKPTG - PPT",db);
	    	}
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupNotisSecaraPukal",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupNotisSecaraPukal","Skrin Kemasukan Maklumat Notis Secara Pukal", "EKPTG - PPT",db);
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
		context.put("selesai_simpan_pukal","");
    	
		//header
		/*
		String id_status = "";
		String flagSegera = "";
		String flag_subjaket = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			flagSegera = dh.get("flag_segera").toString();
			flag_subjaket = dh.get("flag_subjaket").toString();
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
		*/
		this.context.put("listPenghantarNotis", "");
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
		String flag_subjaket = "";
		String id_projekNegeri = "";
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
			flagSegera = dh.get("flag_segera").toString();
			id_fail = (String)dh.get("id_fail");
			flag_subjaket = dh.get("flag_subjaket").toString();
			
			id_projekNegeri  = dh.get("id_projekNegeri").toString();
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
			this.context.put("listPenghantarNotis", listPenghantarNotis(id_projekNegeri));
		}
    	}
		
		

		context.put("flag_subjaket",flag_subjaket);
		
		//for paging 1 ~ 24
		context.put("flagSegera",flagSegera);
		
		//id Hakmilik
		String idHakmilik = getParam("id_hakmilik");

		//data hakmilik
		if(!idHakmilik.equals(""))
		{
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		}
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange", "no");
		context.put("hideItem", "no");
		context.put("showAlertPaging","no");
		context.put("showAst", "yes");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewListHM".equals(submit)){
    	    
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//list maklumat tanah
    		listHakmilik(idpermohonan,noLOT,idpegawai);
     		
        	//screen
    		vm = listHMscreen;
        	
    	}//close viewListHM

    	else if("viewListPenyampaianNotis".equals(submit)){
    
    		//form validation
    		context.put("mode","new");
    		
    		//clear data
    		clearData();
    		
    		//dropdown pihak berkepentingan
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"disabled class=disabled style=width:auto"));
    		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"onChange=\"onchangeGetname();\" style=width:310px"));
    		
    		//dropdown penerima
    		context.put("selectJenisNoKP",HTML.SelectJenisNoPb("socJenisNoKP",null,"style=width:auto"));
    		
    		//list penyampaian notis
    		listPenyampaianNotis(session,idHakmilik);
    		
    		//carian nama pb
        	//namaPB = getParam("carianPB");
        	//context.put("carianPB",namaPB.trim());
        	
    		//list PB
        	//listPB(idpermohonan,namaPB);
    		
        	
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("onchangeGetname".equals(submit2)){
        		
        		//get and set data
        		getAndSetData(idHakmilik,"new");
        		
        		//validation hideItem
        		valHideItem();
        		
        		//validation hideAst
        		valHideAst();
        		
        		//default nama penerima
        		defaultNamaPenerima();
        		
        	}//close onchangeGetname
        	
        	else if("onChangeStatus".equals(submit2)){
        		
        		//get and set data
        		getAndSetData(idHakmilik,"new");
        		
        		//validation hideItem
        		valHideItem();
        		
        		//validation hideAst
        		valHideAst();
        		
        	}//close onChangeStatus
        	
        	else if("simpanPenyampaianNotis".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			//simpan data
        			simpanPenyampaianNotis(session,idHakmilik,id_status); 
        		}
        		
        		//form validation
        		context.put("mode","new");
        		
        		//clear data
        		clearData();
        		
        		//list penyampaian notis
        		listPenyampaianNotis(session,idHakmilik);
        		
        		int list_size = listPenyampaianNotis(session,idHakmilik);
        		
        		header.setDataHeader(idpermohonan);
        		dataHeader = header.getDataHeader();
        		context.put("dataHeader", dataHeader);
        		if(dataHeader.size()!=0){
        			Hashtable dh = (Hashtable) dataHeader.get(0);
        			id_status = (String)dh.get("id_status");
        		}
        		
        		//alert
        		if(list_size==1){
        			context.put("showAlertPaging","yes");
        			//JOptionPane.showMessageDialog (null, "Sila Klik 'Paging' No.23 Untuk Proses Permintaan Ukur Sekiranya Tiada Tambahan Bukti Penyampaian", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
        		}
        		
        	
        	}//close simpanPenyampaianNotis
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close viewListPenyampaianNotis
    	
    	
    	else if("viewListPenyampaianNotis_displaySelesai".equals(submit)){
    	    
    		//form validation
    		context.put("mode","new");
    		
    		//clear data
    		clearData();
    		
    		//dropdown pihak berkepentingan
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"disabled class=disabled style=width:auto"));
    		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"onChange=\"onchangeGetname();\" style=width:310px"));
    		
    		//dropdown penerima
    		context.put("selectJenisNoKP",HTML.SelectJenisNoPb("socJenisNoKP",null,"style=width:auto"));
    		
    		context.put("selesai_simpan_pukal","yes");
    		
    		//list penyampaian notis
    		listPenyampaianNotis(session,idHakmilik);
    	
    		//screen
    		vm = mainscreen;
    		
    	}//close viewListPenyampaianNotis
    	
    	else 
    	if("viewPenyampaianNotis".equals(submit)){
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//get id
    		String id_buktipenyampaian = getParam("id_buktipenyampaian");
    		context.put("id_buktipenyampaian",id_buktipenyampaian);
    		
    		//data penyampaian notis
    		dataPenyampaianNotis(session,id_buktipenyampaian,idHakmilik,"disabled");
    		
    		//list penyampaian notis
    		listPenyampaianNotis(session,idHakmilik);
    		
    		//listpb
    		//listPBPilihan(id_buktipenyampaian);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniPenyampaianNotis".equals(submit2)){
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");

        		//get id
        		id_buktipenyampaian = getParam("id_buktipenyampaian");
        		context.put("id_buktipenyampaian",id_buktipenyampaian);
        		
        		//data penyampaian notis
        		dataPenyampaianNotis(session,id_buktipenyampaian,idHakmilik,"enabled");
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("onchangeGetnameUpdate".equals(submit3)){
            		
            		//onchhange validation
            		context.put("onchange", "yes");
            		
            		//get and set data
            		getAndSetData(idHakmilik,"view");
            		
            		//validation hideItem
            		valHideItem();
            		
            		//validation hideAst
            		valHideAst();
            		
            		//default nama penerima
            		defaultNamaPenerima();
            		
            	}//close onchangeGetnameUpdate
            	
            	else if("onChangeStatusUpdate".equals(submit3)){
            		
            		//onchhange validation
            		context.put("onchange", "yes");
            		
            		//get and set data
            		getAndSetData(idHakmilik,"view");
            		
            		//validation hideItem
            		valHideItem();
            		
            		//validation hideAst
            		valHideAst();
            		
            	}//close onChangeStatus
            	
            	else if("updatePenyampaianNotis".equals(submit3)){
            		
            		updatePenyampaianNotis(session);
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data penyampaian notis
            		dataPenyampaianNotis(session,id_buktipenyampaian,idHakmilik,"disabled");
            		
            		//list penyampaian notis
            		listPenyampaianNotis(session,idHakmilik);
            		
            		//listpb
            		//listPBPilihan(id_buktipenyampaian);
            		
            	}//close updatePenyampaianNotis
        		
        	}//close kemaskiniPenyampaianNotis
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close viewPenyampaianNotis
    	
    	else 
    	if("hapusPenyampaianNotis".equals(submit)){
    			  		
    		String id_buktipenyampaian = getParam("id_buktipenyampaian");
    		
    		hapusPenyampaianNotis(session,id_buktipenyampaian);
    		
    		//form validation
    		context.put("mode","new");
    		
    		//clear data
    		clearData();
    		
    		//dropdown pihak berkepentingan
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"disabled class=disabled style=width:auto"));
    		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,null,"onChange=\"onchangeGetname();\" style=width:310px"));
    		
    		//dropdown penerima
    		context.put("selectJenisNoKP",HTML.SelectJenisNoPb("socJenisNoKP",null,"style=width:auto"));
    		
    		//list penyampaian notis
    		listPenyampaianNotis(session,idHakmilik);
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close hapusPenyampaianNotis
    	
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarianK(session,userIdNeg);			
    		listPageDepan = model.getListCarian();
	
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else{	
    		
    		listPageDepan = model.getListPermohonanK("3",userIdNeg);
    		
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
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	

	private void valHideAst() throws Exception{
    	
		String socJenisSerah = getParam("socJenisSerah");
		
		//onchange validation
		if(socJenisSerah.equals("2")){
			context.put("showAst", "yes");
		}else{
    		context.put("showAst", "no");
		}
		
	}//close valHideAst

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
	
	@SuppressWarnings("unchecked")
	private void listHakmilik(String idpermohonan,String noLOT,String idpegawai) throws Exception{
    	/*
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();
		
		modelUPT.setListHMwithIdBorangK(idpermohonan,noLOT);
 		listMaklumatTanah = modelUPT.getListHMwithIdBorangK();
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 		*/
 		
 		context.put("saiz_listTanah", modelUPT.setListHMwithIdBorangK_count(idpermohonan,noLOT));
 		
	}//close listHakmilik
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmSek8PenyampaianNotisData.setListCarian(nofail,tarikh,status,"3",userIdNeg);
      
	}//close listcarian
	
	
	private void ListCarianK(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmSek8PenyampaianNotisData.setListCarianK(nofail,tarikh,status,"3",userIdNeg);
      
	}//close listcarian
	
	
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
	
	
	private void valHideItem() throws Exception{
    	
		String statusSerah = getParam("socStatusSerah");
		
		//onchange validation
		if(statusSerah.equals("2")){
			context.put("hideItem", "yes");
		}else{
    		context.put("hideItem", "no");
		}
		
	}//close valHideItem

	@SuppressWarnings({ "unchecked", "static-access", "unused" })
	private void listPB(String idpermohonan,String namaPB) throws Exception{
    	
		Vector listPB = new Vector();
		listPB.clear();
		
		model.setListPB(idpermohonan,namaPB);
    	listPB = model.getListPB();
    	context.put("saiz_pb", listPB.size());
    	context.put("listMaklumatPB", listPB);
    	
	}//close listPB
	
	@SuppressWarnings({ "unchecked", "static-access", "unused" })
	private void listPBPilihan(String id_buktipenyampaian) throws Exception{
    	
		Vector listPBPilihan = new Vector();
		listPBPilihan.clear();
		
		model.setListPBPilihan(id_buktipenyampaian);
		listPBPilihan = model.getListPBPilihan();
    	context.put("saiz_pb", listPBPilihan.size());
    	context.put("listMaklumatPB", listPBPilihan);
    	
	}//close listPB
	
	@SuppressWarnings("unchecked")
	private void defaultNamaPenerima() throws Exception{
		
		Vector getNamaPB = new Vector();
		getNamaPB.clear();
		
		String idpb = getParam("socPB");
		
		//get name
		model.setNamaPB(idpb);
		getNamaPB = model.getNamaPB();
		String namaPB = "";
		if(getNamaPB.size()!=0){
			Hashtable npb = (Hashtable)getNamaPB.get(0);
			namaPB = (String)npb.get("nama_pb");
		}
		
		context.put("txtNamaTerima", namaPB);
		
	}//close defaultNamaPenerima
	
	@SuppressWarnings("unchecked")
	private void getAndSetData(String idHakmilik,String mode) throws Exception{
    	
		Vector getNamaPB = new Vector();
		getNamaPB.clear();
		
		String idpb = getParam("socPB");
		
		//get name
		model.setNamaPB(idpb);
		getNamaPB = model.getNamaPB();
		String idJenisNoPB = "";
		String noPB = "";
		if(getNamaPB.size()!=0){
			Hashtable npb = (Hashtable)getNamaPB.get(0);
			idJenisNoPB = (String)npb.get("id_jenisnopb");
			noPB = (String)npb.get("no_pb");
		}
		
		//dropdown pihak berkepentingan
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(idJenisNoPB),"disabled class=disabled style=width:auto"));
		
		if(mode.equals("new")){
			context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(idpb),null,"onChange=\"onchangeGetname();\" style=width:310px"));
		}else{
			context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(idpb),null,"onChange=\"onchangeGetnameUpdate();\" style=width:310px"));
		}
		
		//dropdown penerima
		context.put("selectJenisNoKP",HTML.SelectJenisNoPb("socJenisNoKP",Utils.parseLong(getParam("socJenisNoKP")),"style=width:auto"));
		
		//set data
		context.put("txtNamaHantar", getParam("txtNamaHantar"));
		context.put("txtNoPB", noPB);
		context.put("txdTarikhSerah", getParam("txdTarikhSerah"));
		context.put("txtNamaTerima", getParam("txtNamaTerima"));
		context.put("txtCatatan", getParam("txtCatatan"));
		context.put("socJenisSerah", getParam("socJenisSerah"));
		context.put("socStatusSerah", getParam("socStatusSerah"));
		context.put("txtHubungan", getParam("txtHubungan"));
		context.put("txtNoKP", getParam("txtNoKP"));
		context.put("txtMasaTampal", getParam("txtMasaTampal"));
		context.put("socJenisWaktu", getParam("socJenisWaktu"));
		context.put("txtTempatTampal", getParam("txtTempatTampal"));
		
	}//close getAndSetData

	private void clearData() throws Exception{
    	
		context.put("txtNamaHantar", "");
		context.put("socJenisSerah", "");
		context.put("socStatusSerah", "");
		context.put("txdTarikhSerah", "");
		context.put("txtNamaTerima", "");
		context.put("txtNoPB", "");
		context.put("txtCatatan", "");
		context.put("txtHubungan", "");
		context.put("txtNoKP", "");
		context.put("txtMasaTampal", "");
		context.put("socJenisWaktu", "");
		context.put("txtTempatTampal", "");
		
	}//close clearData

	private void hapusPenyampaianNotis(HttpSession session,String idBuktiPenyampaian) throws Exception{
 
		FrmSek8PenyampaianNotisData.hapusPenyampaianNotis(idBuktiPenyampaian);
		
	}//close hapusPenyampaianNotis
	
	@SuppressWarnings("unchecked")
	private int listPenyampaianNotis(HttpSession session,String idHakmilik) throws Exception{
    	/*
		Vector listPenyampaianNotis = new Vector();
		listPenyampaianNotis.clear();
		
		model.setListPenyampaianNotis(idHakmilik,"3");
		listPenyampaianNotis = model.getListPenyampaianNotis();		
		context.put("listPenyampaianNotis",listPenyampaianNotis);
		context.put("saiz_PenyampaianNotis",listPenyampaianNotis.size());
		*/
		
		context.put("saiz_PenyampaianNotis",model.setListPenyampaianNotis_count(idHakmilik,"3"));
		
		return model.setListPenyampaianNotis_count(idHakmilik,"3");
		
	}//close listPenyampaianNotis

	@SuppressWarnings("unchecked")
	private void dataPenyampaianNotis(HttpSession session,String id_buktipenyampaian,String idHakmilik,String disability) throws Exception{
    	
		Vector dataPenyampaianNotis = new Vector();
		dataPenyampaianNotis.clear();
		
		model.setDataPenyampaianNotis(id_buktipenyampaian);
		dataPenyampaianNotis = model.getDataPenyampaianNotis();
		String id_jenisnopb = "";
		String statusSerah = "";
		String id_kodnopb = "";
		String id_pihakberkepentingan = "";
		String id_hakmilikpb = "";
		String nama_penghantar = "";
		String jenis_serahan = "";
		if(dataPenyampaianNotis.size()!=0){
			Hashtable dp = (Hashtable)dataPenyampaianNotis.get(0);
			id_jenisnopb = (String)dp.get("id_jenisnopb");
			statusSerah = (String)dp.get("status_serahan");
			id_pihakberkepentingan = (String)dp.get("id_pihakberkepentingan");
			id_kodnopb = (String)dp.get("id_kodnopb");
			id_hakmilikpb = (String)dp.get("id_hakmilikpb");
			nama_penghantar = (String)dp.get("nama_penghantar");
			jenis_serahan = (String)dp.get("jenis_serahan");
		}
		
		//data and id
		context.put("dataPenyampaianNotis",dataPenyampaianNotis);
		context.put("id_hakmilikpb",id_hakmilikpb);
		context.put("nama_penghantar",nama_penghantar);
		
		//onchange validation
		if(statusSerah.equals("2")){
			context.put("hideItem", "yes");
		}else{
    		context.put("hideItem", "no");
		}
		
		//onchange validation
		if(jenis_serahan.equals("2")){
			context.put("showAst", "yes");
		}else{
    		context.put("showAst", "no");
		}
		
		String mode = "";
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		//dropdown pihak berkepentingan
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(id_kodnopb),"style=width:auto disabled class=disabled "));
		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(id_pihakberkepentingan),null,""+mode+" onChange=\"onchangeGetnameUpdate();\" style=width:310px"));
		
		//data penerima
		context.put("selectJenisNoKP",HTML.SelectJenisNoPb("socJenisNoKP",Utils.parseLong(id_jenisnopb),"style=width:auto "+mode+""));
		
	}//close dataPenyampaianNotis
	
	
	@SuppressWarnings("unchecked")
	private void updatePenyampaianNotis(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		long id_buktipenyampaian = Long.parseLong(getParam("id_buktipenyampaian"));

		h.put("txtNamaHantar", getParam("txtNamaHantar"));
		h.put("socKodDokumen", getParam("socKodDokumen"));
		h.put("socJenisSerah", getParam("socJenisSerah"));
		h.put("socStatusSerah", getParam("socStatusSerah"));
		h.put("txdTarikhSerah", getParam("txdTarikhSerah"));
		h.put("txtCatatan", getParam("txtCatatan"));
		
		//data pihak berkepentingan
		h.put("id_pihakberkepentingan", getParam("socPB"));
		
		//data penerima
		h.put("txtNamaTerima", getParam("txtNamaTerima"));
		h.put("txtNoKP", getParam("txtNoKP"));
		h.put("socJenisNoKP", getParam("socJenisNoKP"));
		h.put("txtHubungan", getParam("txtHubungan"));
		
		//data tampal
		h.put("txtMasaTampal", getParam("txtMasaTampal"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		h.put("txtTempatTampal", getParam("txtTempatTampal"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
/*		String[] cbsemaks = request.getParameterValues("cbsemaks");
	 	  
		FrmSek8PenyampaianNotisData.deletePilihanPB(id_buktipenyampaian);
		
		if((cbsemaks!=null)){
			for (int i = 0; i < cbsemaks.length; i++) { 
				FrmSek8PenyampaianNotisData.simpanPilihanPB(h,cbsemaks[i],id_buktipenyampaian);
			}
		}
*/		
		FrmSek8PenyampaianNotisData.updatePenyampaianNotis(h,id_buktipenyampaian);
  
	}//close updatePenyampaianNotis
	
	@SuppressWarnings("unchecked")
	private void simpanPenyampaianNotis(HttpSession session,String id_hakmilik,String idstatus) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_hakmilik", id_hakmilik);
		h.put("txtNamaHantar", getParam("txtNamaHantar"));
		h.put("socKodDokumen", getParam("socKodDokumen"));
		h.put("socJenisSerah", getParam("socJenisSerah"));
		h.put("socStatusSerah", getParam("socStatusSerah"));
		h.put("txdTarikhSerah", getParam("txdTarikhSerah"));
		h.put("txtCatatan", getParam("txtCatatan"));

		//data pihak berkepentingan
		h.put("id_pihakberkepentingan", getParam("socPB"));
		
		//data penerima
		h.put("txtNamaTerima", getParam("txtNamaTerima"));
		h.put("txtNoKP", getParam("txtNoKP"));
		h.put("socJenisNoKP", getParam("socJenisNoKP"));
		h.put("txtHubungan", getParam("txtHubungan"));
		
		//data tampal
		h.put("txtMasaTampal", getParam("txtMasaTampal"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		h.put("txtTempatTampal", getParam("txtTempatTampal"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		
/*		String[] cbsemaks = request.getParameterValues("cbsemaks");

		if((cbsemaks!=null)){
			for (int i = 0; i < cbsemaks.length; i++) { 
				FrmSek8PenyampaianNotisData.simpanPilihanPB(h,cbsemaks[i],id_buktipenyampaian);
			}
		}
*/		
		
		//create id
		long id_buktipenyampaian = DB.getNextID("TBLPPTBUKTIPENYAMPAIAN_SEQ");
		FrmSek8PenyampaianNotisData.simpanPenyampaianNotis(h,id_buktipenyampaian,"3");
  
		
	}//close simpanPenyampaianNotis
	
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
	
	public Vector listPenghantarNotis(String id_negeri) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = " 	SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI FROM USERS U, USERS_INTERNAL UI "+
					" 	WHERE U.USER_ID = UI.USER_ID  "+
					//" 			AND UI.ID_SEKSYEN = '1' "+
					" 			AND ID_JAWATAN IN (26)  "+
					//"           AND U.USER_ROLE = '(PPT)PenghantarNotis'"+
					" 			AND UI.ID_NEGERI = '"+id_negeri+"' "+
					" 			ORDER BY LPAD(U.USER_NAME,10) ";		 					
			myLogger.info("LIST PENGHANTAR NOTIS :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			 ResultSet rs = stmt.executeQuery(sql);
		      Vector listPenghantarNotis = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_PEGAWAI", rs.getString("ID_PEGAWAI")==null?"":rs.getString("ID_PEGAWAI"));
		    	  h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI")==null?"":rs.getString("NAMA_PEGAWAI"));
		    	  listPenghantarNotis.addElement(h);
		      }
		      return listPenghantarNotis;
					
		} finally {
			if (db != null)
			db.close();
		}
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
		

}//close class
