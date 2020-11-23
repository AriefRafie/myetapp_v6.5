package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8PampasanData;
import ekptg.model.ppt.FrmSek8PenyampaianNotisData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.model.ppt.PPTHeader;
import ekptg.view.ppt.email.EmailOnline;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmSek8PenyediaanPampasan extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek8PenyediaanPampasan.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     
	
	//model
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	FrmSek8PampasanData model = new FrmSek8PampasanData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	FrmSek8PenyampaianNotisData modelNotis = new FrmSek8PenyampaianNotisData();
	MyInfoPPTData myInfo = new MyInfoPPTData(); //penambahan yati
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	String vm = "";
    	String noLOT = "";
		String id_hakmilikpb = getParam("id_hakmilikpb");

       // Date Date5 = new Date("12/01/2017");
       // String new'Date = sdf.format(currentDate);
    	String Date5 = "";
        context.put("Date5","01/12/2017");
        myLogger.info(" Date5 :"+Date5);
        
    	String Date8 = "";
        context.put("Date8","11/30/2017");
        myLogger.info(" Date8 :"+Date8);
    	
    	Vector listPageDepan = new Vector();
    	Vector listSediaPampasan = new Vector();
    	Vector listMaklumatTanahWithSiasatan = new Vector();
    	Vector dataIdSiasatan = new Vector();
    	Vector dataBorangG = new Vector();
    	Vector dataBorangH = new Vector();
    	Vector listDataBorangG = new Vector(); //penambahan yati
    	Vector imejG = new Vector(); //penambahan yati
    	Vector imejH = new Vector(); //penambahan yati
    	Vector dokumenPampasan = new Vector(); //penambahan yati
    	Vector dataDokumenBorangG = new Vector();
    	Vector totalPampasan = new Vector();
    	Vector dataByHM = new Vector();
    	Vector listBentukPampasan = new Vector();
    	Vector listMaklumatPenerimaanCek = new Vector();
    	Vector dataPenerimaanCek = new Vector();
    	Vector dataPBBorangH = new Vector();
    	Vector dataNamaPengarah = new Vector();
    	Vector listUserid = new Vector();
    	Vector dataAfidevit = new Vector();
    	Vector datalistHakmilik = new Vector();
    	Vector getIdAward = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataMaklumatTanah = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	
    	dataSuburusanHakmilik.clear();
    	dataMaklumatTanah.clear();
    	getIdSuburusanstatusfail.clear();
    	getIdAward.clear();
    	datalistHakmilik.clear();
    	dataAfidevit.clear();
    	listUserid.clear();
    	dataNamaPengarah.clear();
    	dataPBBorangH.clear();
    	dataPenerimaanCek.clear();
    	listMaklumatPenerimaanCek.clear();
    	listBentukPampasan.clear();
    	dataByHM.clear();
    	totalPampasan.clear();
    	dataBorangG.clear();
    	dataIdSiasatan.clear();
    	listMaklumatTanahWithSiasatan.clear();
    	listSediaPampasan.clear();
    	listPageDepan.clear();
    	
    	/*
    	Db dby = null;
		try {
		dby = new Db();	    	
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik",dby)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT",dby);
	    	}
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupNotisSecaraPukal",dby)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupNotisSecaraPukal","Skrin Kemasukan Maklumat Notis Secara Pukal", "EKPTG - PPT",dby);
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
		context.put("selesai_simpan_pukal","");
		
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
    	/*
    	modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    */    
	    context.put("userIdNeg", userIdNeg);
	    	
    	//screen jsp
    	String listdepan = "app/ppt/frmSek8PampasanSenarai.jsp";
    	String listHakmilik = "app/ppt/frmSek8PampasanListHM.jsp";
    	String mainscreen = "app/ppt/frmSek8PenyediaanPampasan.jsp";
    	String screenPage2 = "app/ppt/frmSek8BentukBayaranAwardList.jsp";
    	String screenMainPage2 = "app/ppt/frmSek8BentukBayaranAward.jsp";
    	String screenMainPage3 = "app/ppt/frmSek8BayaranPampasan.jsp";
    	String screenMainPage4 = "app/ppt/frmSek8PenerimaanCek.jsp";
    
    	//default list
    	//listPageDepan = model.getListPermohonan(userIdNeg);
    	
    	//utils
    	context.put("Utils", new ekptg.helpers.Utils());
    	
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
		this.context.put("listPenghantarNotis", "");
		
		//header
		String id_status = "";
		String nama_kementerian = "";
		String flagSegera = "";
		String id_fail = "";
		String id_negeriProjek = "";
		String namaProjek = "";
		String flag_subjaket = "";
		String no_fail = "";
		String tarikh_permohonan = "";
		String tujuan = "";
		//String no_lot = "";
		
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
			nama_kementerian = (String)dh.get("nama_kementerian");
			flagSegera = dh.get("flag_segera").toString();
			id_fail = (String)dh.get("id_fail");
			no_fail = (String) dh.get("no_fail");
			id_negeriProjek = (String)dh.get("id_projekNegeri");
			namaProjek = dh.get("tujuan").toString();
			flag_subjaket = dh.get("flag_subjaket").toString();
			tujuan = (String) dh.get("tujuan");
			tarikh_permohonan = (String) dh.get("tarikh_permohonan");
			//no_lot = (String) dh.get("no_lot");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
			this.context.put("listPenghantarNotis", listPenghantarNotis(id_negeriProjek));
		}
    	}

		context.put("flag_subjaket",flag_subjaket);
		context.put("flagSegera",flagSegera);
		context.put("kementerianEFT", nama_kementerian);
		
		
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
 		
		//paging ALL
		/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "17");
    	}
    	*/
    	context.put("paging", "17");
    	
		//paging pampasan
    	String flagPagingPampasan = getParam("pagingPampasan");
    	if(flagPagingPampasan!=""){
    		context.put("pagingPampasan", getParam("pagingPampasan"));
    	}else{
    		context.put("pagingPampasan", "1");
    	}
    	
    	myLogger.info("flagPagingPampasan--- "+flagPagingPampasan);
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange","no");
		context.put("onchangeCEK","no");
		context.put("isShow","");
		context.put("onchangeEFT", "no");
		context.put("onchangeAF","no");
		context.put("hideItem", "no");
		context.put("showAlert", "no");
		context.put("showAlertPaging","no");
		context.put("showAlertAgensi","no");
		context.put("showAlertPagingSegera","no");
		context.put("showAst", "yes");
		context.put("listMaklumatTanah", "");
		
		
		String idHakmilik = getParam("id_hakmilik");
		
		
		//get size suburusanhakmilik
		String id_suburusanstatushakmilik = "";
		String id_suburusanstatus = "";
		if(!idHakmilik.equals(""))
		{
		modelUPT.setDataSuburusanHakmilik(idHakmilik);
		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
		if(dataSuburusanHakmilik.size()!=0){
			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
			id_suburusanstatus = (String)dsh.get("id_suburusanstatus");
		}
		
		//for tab
		model.setlistPenyediaanPampasan(idHakmilik);
		listSediaPampasan = model.getlistPenyediaanPampasan();
		}
		//for tab
		//get id siasatan from idHakmilik
		String id_siasatan = "";
		if(!idHakmilik.equals(""))
		{
		model.setIdSiasatan(idHakmilik);
		dataIdSiasatan = model.getIdSiasatan();
		if(dataIdSiasatan.size()!=0){
			Hashtable dis = (Hashtable)dataIdSiasatan.get(0);
			id_siasatan = (String)dis.get("id_siasatan");
		}
		
		//for paging 3
		model.setlistBentukPampasan(idHakmilik);
		listBentukPampasan = model.getlistBentukPampasan();
		}
		
		//validation (id borang g)
		String id_borangg = "";
		String id_borangh = "";
		String idImejBorangG = "";
		String idImejBorangH = "";
		String idDokumen = "";
		String id_hakmilik = "";
		
		//validation (id borang g) //PENAMBAHAN YATI
		if(!id_siasatan.equals(""))
		{
		model.setDataBorangG(id_siasatan);
		dataBorangG = model.getDataBorangG();
		if(dataBorangG.size()!=0){
			Hashtable gh = (Hashtable)dataBorangG.get(0);
			id_borangg = (String)gh.get("id_borangg");
		}
		}

		if(!idHakmilik.equals(""))
		{
		model.setDataDokumenBorangG(idHakmilik);
		dataDokumenBorangG = model.getDataDokumenBorangG();
		}
		
		
		//nama2 mukim
		/*
		String nama2Mukim = "";
		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,"");
		datalistHakmilik = modelUPT.getListMaklumatTanah();
 		if(datalistHakmilik.size()!=0){
 			Hashtable lmt = (Hashtable)datalistHakmilik.get(0);
 			nama2Mukim = (String)lmt.get("nama2Mukim");
 		} 		
 		context.put("nama2Mukim", nama2Mukim);
 		*/
 		
 		//data hakmilik
		if(!idHakmilik.equals(""))
		{
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		
		}
		
		//GET NO_LOT
				
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);    	
    	if("sendEmail".equals(submit)){
   		
    		sendEmail(namaProjek,"","","",id_user,"mintaBayaranPampasan",session);    
     		
    		//screen
//    		vm = listHakmilik;
    		
    	}//close viewlistHM
    	
    	if("viewlistHM".equals(submit)){
    		/*f
    		
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//data & list maklumat tanah
    		model.setListMaklumatTanahWithSiasatan(idpermohonan,noLOT);
     		listMaklumatTanahWithSiasatan = model.getListMaklumatTanahWithSiasatan();
     		context.put("listMaklumatTanah", listMaklumatTanahWithSiasatan);
     		
     		context.put("saiz_listTanah", listMaklumatTanahWithSiasatan.size());
     		*/
     		context.put("saiz_listTanah",model.setListMaklumatTanahWithSiasatan_count(idpermohonan,noLOT));
     		
     		//list borang g mengikut tarikh
     		Vector listBorangG = model.getListBorangG(idpermohonan);
     		context.put("listBorangG", listBorangG);
     		
    		//screen
    		vm = listHakmilik;
    		
    	}//close viewlistHM
    	
    	
    	else 
    	if("viewJumlahAward".equals(submit)){
    		
    		String id_permohonan = getParam("id_permohonan");
    		context.put("id_permohonan", id_permohonan);
    		
    		emelListKJP(id_permohonan);
			String emel  = emelListKJP(id_permohonan); 
					
			String no_lot = getNoLot(idHakmilik);
			
    		if(getParam("FlagHantarEmel").equals("Y"))
    		{
    			hantarEmel("content","Pemakluman Permohonan Pembayaran Lain-Lain Kos Bagi Projek:  "+tujuan+" No.Lot:"+no_lot+" " ,no_fail, tujuan, tarikh_permohonan,emel,nama_kementerian,no_lot);												
    		}
    			
    		//list penyediaan pampasan
            listPenyediaanPampasan(idHakmilik);
           
           //dataWarta(idpermohonan);
           
    		//validation (id borang g) 		
            model.setDataBorangG(id_siasatan);
    		dataBorangG = model.getDataBorangG();
    	
    		if(dataBorangG.size()!=0){
    			Hashtable gh = (Hashtable)dataBorangG.get(0);
    			id_borangg = (String)gh.get("id_borangg");
    			id_borangh = (String)gh.get("id_borangh");
    			//String tarikh_borangg = (String)gh.get("tarikh_borangg");
    			//String tarikh_borangh = (String)gh.get("tarikh_borangh");
    		}
    	
    		//data and id
    		context.put("id_borangg",id_borangg);
    		context.put("id_borangh",id_borangh);
    		context.put("dataBorangG",dataBorangG);
    		context.put("idHakmilik",idHakmilik);

  
    		//get total pampasan
    		getTotalPampasan(id_siasatan);      
    	
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            String id_boranggx = ""; 
          
           
           
    		//NEW FORM
            if(id_borangg==""){
    			
    			//form validaiton
        		context.put("mode","new");
    			
                if("simpanBorangG".equals(submit2)){
                	
                	id_siasatan = getParam("id_siasatan");
                	idHakmilik = getParam("id_hakmilik");
                	//String id_hakmilikpb = getParam("id_hakmilikpb");
                	if (doPost.equals("true")) {
                    	//simpan data
                	
                		simpanDataBorangG(session);            
                		id_boranggx = getIdBorangG(id_siasatan);
                		//listPenerimaanBorangH(id_boranggx);
                		
                		uploadFiles(idpermohonan ,id_boranggx, id_borangh ,id_hakmilikpb,idHakmilik , session);
                		//uploadFilesNew(idpermohonan ,id_borangg, id_borangh ,idHakmilik , session);
                		updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik,"16102726");
                		//email alert 13022012
                		//sendEmail(namaProjek,"","mintaBayaranPampasan");  
                		//uploadFiles(idpermohonan ,id_borangg, id_borangh ,id_hakmilikpb,idHakmilik , session);
     		
                	}
                	
                	//form validaiton
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data
            		dataBorangG(id_siasatan);
            		
            		 model.setDataBorangG(id_siasatan);
             		dataBorangG = model.getDataBorangG();
             	             		
             		if(dataBorangG.size()!=0){
             			Hashtable gh = (Hashtable)dataBorangG.get(0);
             			id_borangg = (String)gh.get("id_borangg");
             			id_borangh = (String)gh.get("id_borangh");
             		}
             	
             		//data and id
             		context.put("id_borangg",id_borangg);
   
            		model.setDokumenImejG(id_borangg);
            		imejG = model.getImejBorangG();
            		context.put("imejG", imejG);
        		
        			model.setDokumenImejH(id_borangg);
            		imejH = model.getImejBorangH();
            		context.put("imejH", imejH);
     
            		
            		if(imejH.size()!=0){
            			Hashtable iG = (Hashtable)imejH.get(0);
            			String id_jenisdokumen = (String)iG.get("id_jenisdokumen");
            			context.put("id_jenisdokumen", id_jenisdokumen);
            		}
            		
            		model.setDokumenPampasan(idHakmilik);
            		dokumenPampasan = model.getDokumenPampasan();
            		context.put("dokumenPampasan", dokumenPampasan);
            		
            		if(dokumenPampasan.size()!=0){
            			Hashtable dP = (Hashtable)dokumenPampasan.get(0);
            			String id_Dokumen = (String)dP.get("id_Dokumen");
            			context.put("idDokumen", id_Dokumen);
            		}
            		
	
              		vm = mainscreen;
            		
            		
                }//close simpanBorangG
                
        		
    		//VIEW FORM	
    		}
            else{
    			
    			//form validaiton
        		context.put("mode","view");
        		context.put("isEdit","no");
        		
        		if("kemaskiniBorangG".equals(submit2)){
        			
        			//String id_hakmilikpb = getParam("id_hakmilikpb");
        			id_borangh = getParam("id_borangh");
        			id_borangg = getParam("id_borangg");
        			idImejBorangG = getParam("idImejBorangG");
        			context.put("id_borangg",id_borangg);
        			
        			//form validaiton
            		context.put("mode","view");
            		context.put("isEdit","yes");
            		
            		//get total pampasan
            		getTotalPampasan(id_siasatan);
        			String totalAward = getTotalPampasan(id_siasatan);
        			
            		String submit3 = getParam("command3");
                    myLogger.info("submit[3] : " + submit3);
                    
                    if("updateBorangG".equals(submit3)){
                    	
                    	id_siasatan = getParam("id_siasatan");
                    	
                    	if (doPost.equals("true")) {
                        	//update data
                    		updateDataBorangG(session,idImejBorangG,totalAward);
                    		id_borangg = getIdBorangG(id_siasatan);
              
                 		uploadFiles(idpermohonan ,id_borangg, id_borangh ,id_hakmilikpb,idHakmilik , session);
//                    		uploadFilesA(idHakmilik , session);
//                    	    long idImejBorangG = DB.getNextID("TBLPPTIMEJBORANG_SEQ");
//                    		updateDataBorangG(session,idImejBorangG,totalAward);
                 		
                        }
                    	
                    	//form validaiton
                		context.put("mode","view");
                		context.put("isEdit","no");
                		context.put("id_borangg",id_borangg);
                		
                		//data
                		dataBorangG(id_siasatan);
                		myLogger.info("id_borangg--- "+id_borangg);
                		
                		//validation (id borang g)
                		model.setDataBorangG(id_siasatan);
                		dataBorangG = model.getDataBorangG();
                
                		if(dataBorangG.size()!=0){
                			Hashtable gh = (Hashtable)dataBorangG.get(0);
                			id_borangg = (String)gh.get("id_borangg");
                			id_borangh = (String)gh.get("id_borangh");
                		}
                		
                		//data and id
                		context.put("id_borangg",id_borangg);
                		context.put("id_borangh",id_borangh);
                	
                    }//close updateBorangG
                      
                	
        		}//close kemaskiniBorangG
        		
        		model.setDataBorangG(id_siasatan);
        		dataBorangG = model.getDataBorangG();
        		
        		context.put("id_borangg",id_borangg);
        		context.put("id_borangh",id_borangh);
                
        		
        		
    		}//close new / view
            
          //validation (id borang g)
    		model.setDataBorangG(id_siasatan);
    		dataBorangG = model.getDataBorangG();
    		
    		context.put("id_borangg",id_borangg);
    		context.put("id_borangh",id_borangh);
    		
    		model.setDokumenImejG(id_borangg);
    		imejG = model.getImejBorangG();
    		context.put("imejG", imejG);
		
			model.setDokumenImejH(id_borangg);
    		imejH = model.getImejBorangH();
    		context.put("imejH", imejH);
    		
    		
    		if(imejH.size()!=0){
    			Hashtable iG = (Hashtable)imejH.get(0);
    			String id_jenisdokumen = (String)iG.get("id_jenisdokumen");
    			context.put("id_jenisdokumen", id_jenisdokumen);
  			
    		}
  		
    		model.setDokumenPampasan(idHakmilik);
    		dokumenPampasan = model.getDokumenPampasan();
    		context.put("dokumenPampasan", dokumenPampasan);
    		
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close viewJumlahAward
    	
    	else 
    	if("penerimaanBorangH".equals(submit)){
    		
			
    		//id
    		id_borangg = getParam("id_borangg");
    		context.put("id_borangg", id_borangg);
    		
    		//form validation
    		context.put("mode","new");
    		
    		//clear data
    		clearData();
    		
    		//dropdown pihak berkepentingan
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"disabled class=disabled style=width:auto"));
    		context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",null,null,"onChange=\"onchangeGetname();\" style=width:310px"));
    		
    		//dropdown penerima
    		context.put("selectJenisNoKP",HTML.SelectJenisNoPb("socJenisNoKP",null,"style=width:auto"));
    		
    		//list penerimaan borang h
    		listPenerimaanBorangH(id_borangg);
    		
    		

    		id_borangh = "";
        	context.put(id_borangh,"");       	

    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
            if("onchangeGetname".equals(submit2)){

            	
        		//get and set data
        		getAndSetData(idpermohonan,"new",idHakmilik);
        		
        		//validation hideItem
        		valHideItem();
        		
        		//validation hideAst
        		valHideAst();
        		
        		//default nama penerima
        		defaultNamaPenerima();
        		
        	 	
            	id_borangh = "";
            	context.put(id_borangh,"");       	
       
        		
        	}//close onchangeGetname
            
            else if("onChangeStatus".equals(submit2)){
        		
        		//get and set data
        		getAndSetData(idpermohonan,"new",idHakmilik);
        		
        		//validation hideItem
        		valHideItem();
        		
        		//validation hideAst
        		valHideAst();
        		
        	}//close onChangeStatus
            
            else if("simpanPenerimaanBorangH".equals(submit2)){
            
            	if (doPost.equals("true")) {
                	//update data
            		id_borangh = "";
            		context.put("id_borangh", id_borangh);
            	
            		simpanPenerimaanBorangH(session,id_status,idHakmilik); 
            		/*
            		if(id_status.equals("62") || id_status.equals("74")){
            			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt,"1483");
            		}
            		*/
            		if(modelUPT.cekStatusFailDahWujud(idpermohonan,"68","52")==false)
            		{
        			modelUPT.updateStatus(idpermohonan,id_user, "68");
        			//updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt,"1483");
            		}
					
                }
            	
            	//form validation
        		context.put("mode","new");
        		
        		//clear data
        		clearData();
        		
        		//list penerimaan borang h
        		listPenerimaanBorangH(id_borangg);
        		
        		header.setDataHeader(idpermohonan);
        		dataHeader = header.getDataHeader();
        		context.put("dataHeader", dataHeader);
        		if(dataHeader.size()!=0){
        			Hashtable dh = (Hashtable) dataHeader.get(0);
        			id_status = (String)dh.get("id_status");
        		}
            	
        		if(id_status.equals("68")){
        		//	JOptionPane.showMessageDialog (null, "Sila Klik 'Sub Paging' No.2 atau 3 Untuk Meneruskan Proses Bayaran Pampasan", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
       			context.put("showAlertPaging","yes");
        		}else{
       			context.put("showAlertPaging","no");
        		}
            	
        		//data hakmilik
        		modelUPT.setMaklumatTanah(idHakmilik);
        		dataMaklumatTanah = modelUPT.getMaklumatTanah();
        		
        		
            }//simpanPenerimaanBorangH
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close penerimaanBorangH
    	
    	else 
        	if("penerimaanBorangH_displaySelesai".equals(submit)){
        			
        		//id
        		id_borangg = getParam("id_borangg");
        		context.put("id_borangg", id_borangg);
        		
        		//form validation
        		context.put("mode","new");
        		
        		//clear data
        		clearData();
        		
        		//dropdown pihak berkepentingan
        		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"disabled class=disabled style=width:auto"));
        		context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",null,null,"onChange=\"onchangeGetname();\" style=width:310px"));
        		
        		//dropdown penerima
        		context.put("selectJenisNoKP",HTML.SelectJenisNoPb("socJenisNoKP",null,"style=width:auto"));
        		
        		//list penerimaan borang h
        		listPenerimaanBorangH(id_borangg);
        		
        		
        		context.put("selesai_simpan_pukal","yes");
        		
        		String submit2 = getParam("command2");
                myLogger.info("submit[2] : " + submit2);
                
                
        		//screen
        		vm = mainscreen;
        		
        	}//close penerimaanBorangH
    	else 
    	if("viewPenerimaanBorangH".equals(submit)){
    			
    		//id
    		id_borangg = getParam("id_borangg");
    		id_borangh = getParam("id_borangh");
    		context.put("id_borangg", id_borangg);
    		context.put("id_borangh", id_borangh);
 
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//data penerimaan borang h
    		dataPenerimaanBorangH(idHakmilik,id_borangh,"disabled");
    		
    		//list penerimaan borang h
    		listPenerimaanBorangH(id_borangg);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
            if("kemaskiniPenerimaanBorangH".equals(submit2)){
            	
            	//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
        		
        		//data penerimaan borang h
        		dataPenerimaanBorangH(idHakmilik,id_borangh,"enabled");
        		
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
                if("onchangeGetnameUpdate".equals(submit3)){
            		
            		//onchhange validation
            		context.put("onchange", "yes");
            		
            		//get and set data
            		getAndSetData(idpermohonan,"view",idHakmilik);
            		
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
            		getAndSetData(idpermohonan,"view",idHakmilik);
            		
            		//validation hideItem
            		valHideItem();
            		
            		//validation hideAst
            		valHideAst();
            		
            	}//close onChangeStatus

                else if("updatePenerimaanBorangH".equals(submit3)){
                	
                	updatePenerimaanBorangH(session,idHakmilik);
                	
                	//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
                	//id
            		id_borangg = getParam("id_borangg");
            		id_borangh = getParam("id_borangh");
            		context.put("id_borangh", id_borangh);
            		context.put("id_borangg", id_borangg);
            		
            		//list penerimaan borang h
            		listPenerimaanBorangH(id_borangg);
                	
            		//data penerimaan borang h
            		dataPenerimaanBorangH(idHakmilik,id_borangh,"disabled");
            		
                }//close updatePenerimaanBorangH
            	
            }//close kemaskiniPenerimaanBorangH
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close viewPenerimaanBorangH
    	
    	else 
        if("hapusBorangH".equals(submit)){
        			
        	//delete
        	hapusBorangH(session);
        		
        	//id
    		id_borangg = getParam("id_borangg");
    		context.put("id_borangg", id_borangg);
    		
    		//form validation
    		context.put("mode","new");
    		
    		//clear data
    		clearData();
    		
    		//dropdown pihak berkepentingan
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"disabled class=disabled style=width:auto"));
    		context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",null,null,"onChange=\"onchangeGetname();\" style=width:310px"));
    		
    		//dropdown penerima
    		context.put("selectJenisNoKP",HTML.SelectJenisNoPb("socJenisNoKP",null,"style=width:auto"));
    		
    		//list penerimaan borang h
    		listPenerimaanBorangH(id_borangg);
        	
            //screen
        	vm = mainscreen;
            	
        }//close hapusBorangH
    	
    	
    	//page 2
        else 
    	if("bentukBayaran".equals(submit)){
    			
    		//get data warta
    		dataWarta(idpermohonan);
    		
    		//data siasatan
    		dataBorangG(id_siasatan);
    		
    		//size and data
    		listBentukPampasan(idHakmilik);
    		
    		//screen
    		vm = screenPage2;
    		
    	}//close bentukBayaran
    	
    	else 
    	if("tambahBentukBayaran".equals(submit)){
    			
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValue();
    		
    		//get no lot
    		getNoLot(idHakmilik);
    		
    		//dropdown
    		context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetAward()\""));
    		
    		//size and data
    		listBentukPampasan(idHakmilik);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
            if("PBgetAward".equals(submit2)){
            	
            	String id_pihakberkepentingan = getParam("socPB");
            
            	//get and set data
            	getAndSetPBAward(id_siasatan,idHakmilik,id_pihakberkepentingan);           	
            	
            	String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
                if("simpanBentukBayaran".equals(submit3)){

                	if (doPost.equals("true")) {
                		//simpan data n file
                		simpanDataDanFail(session);
                	}
                	
                	//form validation
            		context.put("mode","new");
            		context.put("isEdit","no");
            		
            		//reset value
            		resetValue();
            		
            		//get no lot
            		getNoLot(idHakmilik);
            		
            		//dropdown
            		context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetAward()\""));
            		
            		//size and data
            		listBentukPampasan(idHakmilik);
                	
                }//close simpanBentukBayaran
            	
            }//close PBgetAward
            
    		//screen
    		vm = screenMainPage2;
    		
    	}//close tambahBentukBayaran
    	
    	else 
    	if("viewBentukBayaran".equals(submit)){
    			
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		String id_award = getParam("id_award");
    		context.put("id_award", id_award);
    		
    		//data
    		dataBentukBayaran(id_award);
    		
    		//list
    		listBentukPampasan(idHakmilik);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("kemaskiniBentukBayaran".equals(submit2)){
    	    		
    	    	//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
    	    	
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
        	    if("updateBentukBayaran".equals(submit3)){
        	    	
        	    	if (doPost.equals("true")) {
                    	//delete and add new dokumen
        	    		hapusDokumen();
        	    		updateBentukBayaran(session);
                    }
        	    	
        	    	//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
        	    	
            		id_award = getParam("id_award");
            		context.put("id_award", id_award);
            		
            		//data
            		dataBentukBayaran(id_award);
            		
            		//list
            		listBentukPampasan(idHakmilik);
            		
        	    }//close updateBentukBayaran
        		
    	    }//close kemaskiniBentukBayaran
    		
    		//screen
    		vm = screenMainPage2;
    		
    	}//close viewBentukBayaran
    	
    	
    	else 
    	if("hapusDokumen".equals(submit)){
    			
    		hapusDokumen();
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValue();
    		
    		//get no lot
    		getNoLot(idHakmilik);
    		
    		//dropdown
    		context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetAward()\""));
    		
    		//size and data
    		listBentukPampasan(idHakmilik);
    		
    		//screen
    		vm = screenMainPage2;
    		
    	}//close hapusDokumen
    	
    	
    	else 
    	if("maklumatSuratAgensi".equals(submit)){
    			
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//list senarai surat agensi
    		listMaklumatSuratAgensi(idHakmilik);
    		dataBorangG(id_siasatan);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("simpanMaklumatSuratAgensi".equals(submit2)){
    	    	
    	    	if (doPost.equals("true")) {
                	//simpan data
    	    		simpanMaklumatSuratAgensi(session,idHakmilik);
    	    		//simpanPenerimaanCek(session,id_hakmilikpb); 
                }
    	    	
    	    	//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
    	    	
        		//list senarai surat agensi
        		listMaklumatSuratAgensi(idHakmilik);
        		
        		//alert lpas simpan agensi
        		context.put("showAlertAgensi","yes");
        		//JOptionPane.showMessageDialog (null, "Sila Klik Pada Senarai Surat Agensi Yang Baru Sahaja Disimpan, dan Klik Butang Tambah Untuk Proses Penyerahan Cek", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
        		
    	    }//close simpanMaklumatSuratAgensi
    		
    		//screen
    		vm = screenMainPage3;
    		
    	}//close maklumatSuratAgensi
    	
    	else 
    	if("viewMaklumatSuratAgensi".equals(submit)){
    			
    		String id_terimabayaran = getParam("id_terimabayaran");
    		context.put("id_terimabayaran",id_terimabayaran);
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//data
    		dataMaklumatSuratAgensi(id_terimabayaran);
    		
    		//list senarai surat agensi
    		listMaklumatSuratAgensi(idHakmilik);
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"");
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("kemaskiniMaklumatSuratAgensi".equals(submit2)){
    	    	
    	    	//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
    	    	
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
        	    if("updateMaklumatSuratAgensi".equals(submit3)){
        	    	
        	    	updateMaklumatSuratAgensi(session);
        	    	
        	    	//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data
            		dataMaklumatSuratAgensi(id_terimabayaran);
            		
            		//list senarai surat agensi
            		listMaklumatSuratAgensi(idHakmilik);
        	    	
        	    }//close updateMaklumatSuratAgensi
        		
    	    }//close kemaskiniMaklumatSuratAgensi
    		
    		//screen
    		vm = screenMainPage3;
    		
    	}//close viewMaklumatSuratAgensi
    	
    	else 
    	if("hapusMaklumatSuratAgensi".equals(submit)){
    			
    		hapusMaklumatSuratAgensi();
    		//hapusPenerimaanCek();
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//list senarai surat agensi
    		listMaklumatSuratAgensi(idHakmilik);
    		
    		//screen
    		vm = screenMainPage3;
    		
    	}//close hapusMaklumatSuratAgensi
    	
    	//Tab screen
    	else 
    	if("tambahPenerimaanCek".equals(submit)){
    			
    		String id_terimabayaran = getParam("id_terimabayaran");
    		context.put("id_terimabayaran", id_terimabayaran);
    	
    		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
    		
    		getAndSetPBAward(id_siasatan,idHakmilik,id_pihakberkepentingan);      
   
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValuePenerimaan();
    		
    		//dropdown select pb by hakmilik filter by use or unused
    		context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"onchangeSelectPB()\""));
    		
    		//dropdown disabled for filter selectpb only
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"");
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("onchangeSelectPB".equals(submit2)){
  
    	    	//set data
    	    	getAndSetPenerimaan(id_siasatan,idHakmilik,"new",id_terimabayaran);
    	    	
    	    	
    	    	
    	    }//close onchangeSelectPB
    		   	    
    	    else if("simpanPenerimaanCek".equals(submit2)){
    	    	
    	    	if (doPost.equals("true")) {
                	//simpan data
    	    		simpanPenerimaanCek(session,id_hakmilikpb);   	    		
                }
    	    	
    	    	id_terimabayaran = getParam("id_terimabayaran");
    	    	
    	    	//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
        		
        		//dropdown select pb by hakmilik filter by use or unused
        		context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"onchangeSelectPB()\""));
        		
        		//dropdown disabled for filter selectpb only
        		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
        		
        		//list penerimaan cek
        		listPenerimaanCek(id_terimabayaran,"");
        		
    	    }//close simpanPenerimaanCek
    	    
    		//screen
    		vm = screenMainPage4;
    		
    	}//close tambahPenerimaanCek
    	
    	else 
    	if("viewPenerimaanCek".equals(submit)){
    			
    		String id_terimabayaran = getParam("id_terimabayaran");
    		String id_bayaran = getParam("id_bayaran");
    		//String id_hakmilikpb = getParam("id_hakmilikpb");
    		String flag_cara_bayar = getParam("cara_bayar");
    		
    		//id and flag
    		context.put("id_bayaran", id_bayaran);
    		context.put("id_terimabayaran", id_terimabayaran);
    		context.put("id_hakmilikpb", id_hakmilikpb);
    		context.put("cara_bayar", flag_cara_bayar);
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//data
    		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
    		getAndSetPenerimaan(id_siasatan,idHakmilik,"disabled",id_terimabayaran);
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"");
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    	    if("kemaskiniPenerimaanCek".equals(submit2)){
    	    	
    	    	//form validation
        		context.put("isEdit","yes");
        		
        		//data
        		dataPenerimaanCek(idHakmilik,id_bayaran,"enabled",id_terimabayaran);
        		
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
        	    if("onchangeSelectPBUpdate".equals(submit3)){
        	    	
        	    	//onchange validation
        	    	context.put("onchangeCEK","yes");
        	    	
        	    	//set data
        	    	getAndSetPenerimaan(id_siasatan,idHakmilik,"view",id_terimabayaran);
        	    	
        	    }//close onchangeSelectPBUpdate
        		
        	    else if("updatePenerimaanCek".equals(submit3)){
        	    	
        	    	updatePenerimaanCek(session);
                   
        	    	id_terimabayaran = getParam("id_terimabayaran");
            		id_bayaran = getParam("id_bayaran");
            	
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data
            		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
            		
            		//list penerimaan cek
            		listPenerimaanCek(id_terimabayaran,"");
        	    	
        	    }//close updatePenerimaanCek
        	    
    	    }//close kemaskiniPenerimaanCek
    	    
    		//screen
    		vm = screenMainPage4;
    		
    	}//close viewPenerimaanCek
    	
    	
    	else 
    	if("hapusPenerimaanCek".equals(submit)){
    			
    		hapusPenerimaanCek();
    		
    		String id_terimabayaran = getParam("id_terimabayaran");
    		context.put("id_terimabayaran", id_terimabayaran);
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValuePenerimaan();
    		
    		//dropdown select pb by hakmilik filter by use or unused
    		context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"onchangeSelectPB()\""));
    		
    		//dropdown disabled for filter selectpb only
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"");
    		
    		//screen
    		vm = screenMainPage4;
    		
    	}//close hapusMaklumatSuratAgensi
    	
    	else 
        if("viewListPenyerahanCek".equals(submit)){
        	
        	String id_terimabayaran = getParam("id_terimabayaran");
        	context.put("id_terimabayaran", id_terimabayaran);
        	
        	//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"1");
    		
        	//screen
    		vm = screenMainPage4;
    		
        }//close viewListPenyerahanCek
    	
        else 
        if("viewListAfidevit".equals(submit)){
            	
            String id_terimabayaran = getParam("id_terimabayaran");
            context.put("id_terimabayaran", id_terimabayaran);
            	
            //reset id award
            context.put("id_award", "");
            
            //list penerimaan cek
        	listPenerimaanCek(id_terimabayaran,"1");
        		
            //screen
        	vm = screenMainPage4;
        		
        }//close viewListAfidevit
    	
        else 
        if("viewAfidevit".equals(submit)){
        		
        	//form validation
    		context.put("isShow","yes");
        	
    		String id_terimabayaran = getParam("id_terimabayaran");
    		String id_bayaran = getParam("id_bayaran");
    		//String id_hakmilikpb = getParam("id_hakmilikpb");
    		String flag_cara_bayar = getParam("cara_bayar");
    		
    		//id and flag
    		context.put("id_bayaran", id_bayaran);
    		context.put("id_terimabayaran", id_terimabayaran);
    		context.put("id_hakmilikpb", id_hakmilikpb);
    		context.put("cara_bayar", flag_cara_bayar);
    		
    		//get id award by id hakmilikpb
    		getIdAwardByIdHakmilikPB(id_hakmilikpb);
    		
    		String id_award = "";
    		model.setDataIdAward(id_hakmilikpb);
    		getIdAward = model.getDataIdAward();
    		if(getIdAward.size()!=0){
    			Hashtable ia = (Hashtable)getIdAward.get(0);
    			id_award = (String)ia.get("id_award");
    		}
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"1");
    		   		
    		//model for validation new / view
    		model.setDataAfidevit(id_award);
    		dataAfidevit = model.getDataAfidevit();
    		context.put("dataAfidevit",dataAfidevit);
    		
    		//NEW
    		if(dataAfidevit.size()==0){
    			
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
    			
        		//reset value
        		resetValueAF();
        		
        		//dropdown
        		if(id_negeriProjek!=""){
    				context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriProjek),"socMahkamah",null, "onChange=\"doChangeMahkamah();\""));
    			}else{
    				context.put("selectMahkamah",HTML.SelectMahkamah("socMahkamah",null, "onChange=\"doChangeMahkamah();\""));
    			}
        		
        		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,null,"disabled class=disabled style=width:300px"));
        		context.put("selectBandar",HTML.SelectBandar("socBandar",null,"disabled class=disabled  style=width:300px"));
        		
        		String submit2 = getParam("command2");
                myLogger.info("submit[2] : " + submit2);
                
        	    if("doChangeMahkamah".equals(submit2)){
        	    	
        	    	String id_pejabat = getParam("socMahkamah");
        	    	
        	    	getAndSetAF(id_pejabat,id_negeriProjek,"new");
        	    	
        	    }//close doChangeMahkamah
        		
        	    else if("simpanAfidevit".equals(submit2)){
        	    	
        	    	if (doPost.equals("true")) {
                    	//simpan data
        	    		simpanAfidevit(session);
                    }
        	    	
        	    	//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data afidevit
            		dataAfidevit(id_award,id_negeriProjek,"disabled");
            		
        	    }//close simpanAfidevit
        	    
    		//VIEW	
    		}else{
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
    			
        		//data afidevit
        		dataAfidevit(id_award,id_negeriProjek,"disabled");
        		
        		String submit2 = getParam("command2");
                myLogger.info("submit[2] : " + submit2);
                
        	    if("kemaskiniAF".equals(submit2)){
        	    	
        	    	//form validation
            		context.put("mode","view");
            		context.put("isEdit","yes");
        	    	
            		//data afidevit
            		dataAfidevit(id_award,id_negeriProjek,"enabled");
            		
            		String submit3 = getParam("command3");
                    myLogger.info("submit[3] : " + submit3);
                    
            	    if("doChangeMahkamahUpdate".equals(submit3)){
            	    	
            	    	//onchange validation
            	    	context.put("onchangeAF","yes");
            	    	
            	    	String id_pejabat = getParam("socMahkamah");
            	    	
            	    	getAndSetAF(id_pejabat,id_negeriProjek,"view");
            	    	
            	    }//close doChangeMahkamahUpdate
            	    
            	    else if("updateAfidevit".equals(submit3)){

            	    	updateAfidevit(session);
            	    	
            	    	//form validation
                		context.put("mode","view");
                		context.put("isEdit","no");
                		
                		//data afidevit
                		dataAfidevit(id_award,id_negeriProjek,"disabled");
                		
            	    }//close updateAfidevit
            	    
        	    }//close kemaskiniAF
        		
    		}//close new / view afidevit
    		
    		
    		//screen
        	vm = screenMainPage4;
        	
        }//close viewAfidevit
    	
    	else 
    	if("viewPenyerahanCek".equals(submit)){
    			
    		//form validation
    		context.put("isShow","yes");
    		
    		String id_terimabayaran = getParam("id_terimabayaran");
    		String id_bayaran = getParam("id_bayaran");
    		//String id_hakmilikpb = getParam("id_hakmilikpb");
    		String flag_cara_bayar = getParam("cara_bayar");
    		
    		//id and flag
    		context.put("id_bayaran", id_bayaran);
    		context.put("id_terimabayaran", id_terimabayaran);
    		context.put("id_hakmilikpb", id_hakmilikpb);
    		context.put("cara_bayar", flag_cara_bayar);
    		
    		//data
    		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
    		
    		//list penerimaan cek
    		listPenerimaanCek(id_terimabayaran,"1");
    		
    		//model for validation new / view
    		String nama_wakil = "";
    		String no_wakil = "";
    		String id_jenisnowakil = "";
    		String tarikh_serah_cek = "";
    		model.setdataPenerimaanCek(id_bayaran);
    		dataPenerimaanCek = model.getdataPenerimaanCek();
    		if(dataPenerimaanCek.size()!=0){
    			Hashtable dp = (Hashtable)dataPenerimaanCek.get(0);
    			nama_wakil = (String)dp.get("nama_wakil");
    			no_wakil = (String)dp.get("no_wakil");
    			id_jenisnowakil = (String)dp.get("id_jenisnowakil");
    			tarikh_serah_cek = (String)dp.get("tarikh_serah_cek");
    		}
    		
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
            
    		//NEW
    		if(nama_wakil=="" && no_wakil=="" && id_jenisnowakil=="" && tarikh_serah_cek==""){
    			
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
    			
        		//dropdown
    			context.put("selectJenisNoWakil",HTML.SelectJenisNoPb("socJenisNoWakil",null,"style=width:auto"));
        		
    			if("updatePenyerahanCek".equals(submit2)){
        	    	
    				if (doPost.equals("true")) {
    					updatePenyerahanCek(session,id_status);
    					/*
    					if(id_status.equals("68")){
            				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt,"1484");
            			}
            			*/
    					if(modelUPT.cekStatusFailDahWujud(idpermohonan,"72","52")==false)
	            		{
	        			modelUPT.updateStatus(idpermohonan,id_user, "72");
	        			//updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
	        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt,"1484");
	            		}
						
    					
    					if(!id_suburusanstatus.equals("1484")){
                			updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik,"1484");
                		}
    					
    				}
    				//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
    				
    				//data
    	    		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
    				
    	    		//list penerimaan cek
    	    		listPenerimaanCek(id_terimabayaran,"1");
    	    		
    	    		System.out.println("id status : "+id_status);
    	    		
    	    		if(id_status.equals("68")){
            			if(flagSegera.equals("1")){
            				//JOptionPane.showMessageDialog (null, "Sila Kembali Pada Skrin Pemilihan Hakmilik dan Klik 'Paging' No.19/20/21/23 Untuk Proses Seterusnya Jika Belum Dilengkapkan Lagi", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
           				context.put("showAlertPagingSegera","yes");
            				context.put("showAlertPaging","no");
            			}else{
           // 				JOptionPane.showMessageDialog (null, "Sila Kembali Pada Skrin Pemilihan Hakmilik dan Klik 'Paging' No.18 Untuk Teruskan ke Borang K", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
            				context.put("showAlertPaging","yes");
            				context.put("showAlertPagingSegera","no");
            			}	
            		}else{
            			context.put("showAlertPaging","no");
            			context.put("showAlertPagingSegera","no");
            		}
    	    		
        	    }//close updatePenyerahanCek
    			
    		//VIEW	
    		}else{
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
    			
        		if("kemaskiniPenyerahanCek".equals(submit2)){
        			
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","yes");
        			
            		//data
    	    		dataPenerimaanCek(idHakmilik,id_bayaran,"enabled",id_terimabayaran);
            		
    	    		String submit3 = getParam("command3");
    	            myLogger.info("submit[3] : " + submit3);
    	            
    	            if("updatePenyerahanCek".equals(submit3)){
    	            	
    	            	updatePenyerahanCek(session,id_status);
        				
        				//form validation
                		context.put("mode","view");
                		context.put("isEdit","no");
        				
        				//data
        	    		dataPenerimaanCek(idHakmilik,id_bayaran,"disabled",id_terimabayaran);
        				
        	    		//list penerimaan cek
        	    		listPenerimaanCek(id_terimabayaran,"1");
    	            	
    	            }//close updatePenyerahanCek
    	    		
        		}//close kemaskiniPenyerahanCek
        		
    		}//new / view
    		
    		//screen
    		vm = screenMainPage4;
    		
    	}//close viewPenyerahanCek
    	
    	else 
        if("viewListEFT".equals(submit)){
           
        	//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValue();
    		
    		//list pb
    		listEFT(idHakmilik);
    		
    		//dropdown
    		context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"getPBEFT()\""));
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
    		
            if("getPBEFT".equals(submit2)){
            	
            	String idpb = getParam("socPB");
            	
            	dataDetailPB(id_siasatan,idHakmilik,idpb,"new");
            	
            	String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
                
                if("simpanPBEFT".equals(submit3)){
                	
                	if (doPost.equals("true")) {
                    	//simpan data
                		simpanPBEFT(session,id_status);
                		/*
                		if(!id_status.equals("82")){
                			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt,"1484");
                		}
                		*/
                		if(modelUPT.cekStatusFailDahWujud(idpermohonan,"72","52")==false)
	            		{
	        			modelUPT.updateStatus(idpermohonan,id_user, "72");
	        			//updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
	        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt,"1484");
	            		}
                		if(!id_suburusanstatus.equals("1484")){
                			updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik,"1484");
                		}
                		
                    }
                	
                	//reset value
                	resetValue();
                	
                	context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"getPBEFT()\""));
            		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
            		
                	//form validation
            		context.put("mode","new");
            		context.put("isEdit","no");
                	
                	//list pb
            		listEFT(idHakmilik);
                	
                	header.setDataHeader(idpermohonan);
            		dataHeader = header.getDataHeader();
            		context.put("dataHeader", dataHeader);
            		if(dataHeader.size()!=0){
            			Hashtable dh = (Hashtable) dataHeader.get(0);
            			id_status = (String)dh.get("id_status");
            		}
            		
            		if(id_status.equals("72")){
            			if(flagSegera.equals("1")){
            //				JOptionPane.showMessageDialog (null, "Sila Klik 'Paging' No.19/20/21 Untuk Proses Seterusnya Jika Belum Dilengkapkan Lagi", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
            				context.put("showAlertPagingSegera","yes");
            				context.put("showAlertPaging","no");
            			}else{
            //				JOptionPane.showMessageDialog (null, "Sila Kembali Pada Skrin Pemilihan Hakmilik dan Klik 'Paging' No.18 Untuk Teruskan ke Borang K", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
            				context.put("showAlertPaging","yes");
            				context.put("showAlertPagingSegera","no");
            			}	
            		}else{
            			context.put("showAlertPaging","no");
            			context.put("showAlertPagingSegera","no");
            		}
            		
            		
                }//close simpanPBEFT
            	
            }//close getPBEFT
    		
        	 //screen
        	vm = screenMainPage3;
        	
        }//close viewListEFT
    	
        else 
    	if("viewDataEFT".equals(submit)){
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		String id_bayaran = getParam("id_bayaran");
    		context.put("id_bayaran", id_bayaran);
    		
    		//String id_hakmilikpb = getParam("id_hakmilikpb");
    		context.put("id_hakmilikpb", id_hakmilikpb);
    		
    		//data eft
    		dataEFT(id_bayaran,idHakmilik,"disabled");
    		
    		//list pb
    		listEFT(idHakmilik);
    		
    		String submit2 = getParam("command2");
            myLogger.info("submit[2] : " + submit2);
    		
            if("kemaskiniPBEFT".equals(submit2)){
            	
            	//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
            	
        		//data eft
        		dataEFT(id_bayaran,idHakmilik,"enabled");
        		
        		String submit3 = getParam("command3");
                myLogger.info("submit[3] : " + submit3);
        		
                if("getPBEFTUpdate".equals(submit3)){
                	
                	context.put("onchangeEFT", "yes");
                	
                	String idpb = getParam("socPB");
                	
                	dataDetailPB(id_siasatan,idHakmilik,idpb,"view");
                	
                }//close getPBEFTUpdate
        		
                else if("updatePBEFT".equals(submit3)){
                	
                	//simpan data
                	updatePBEFT(session);
                    
                	//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		id_bayaran = getParam("id_bayaran");
            		context.put("id_bayaran", id_bayaran);
            		
            		id_hakmilikpb = getParam("id_hakmilikpb");
            		context.put("id_hakmilikpb", id_hakmilikpb);
            		
            		//data eft
            		dataEFT(id_bayaran,idHakmilik,"disabled");
            		
            		//list pb
            		listEFT(idHakmilik);
                	
                }//close simpanPBEFT
                
            }//close kemaskiniPBEFT
            
    		//screen
        	vm = screenMainPage3;
    		
    	}//close viewDataEFT
 	
    	else 
    	if("hapusEFT".equals(submit)){
    			
    		//delete
    		hapusEFT(session);
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValue();
    		
    		//list pb
    		listEFT(idHakmilik);
    		
    		//dropdown
    		context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"getPBEFT()\""));
    		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto disabled class=disabled"));
    		
    		//screen
        	vm = screenMainPage3;
        	
    	}//close hapusEFT
    	
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
   	
    		//for paging 3
//    		if(listBentukPampasan.size()!=0){
//    			context.put("open", "yes");
//    		}else{
//    			context.put("open", "no");
//    		}
    	
    		//list permohonan
	    	context.put("listPermohonan", listPageDepan);
	    	context.put("list_size", listPageDepan.size());
	    	    	
	    	//data 
	    	context.put("dataMaklumatTanah", dataMaklumatTanah);
	    	
	    	//id
	    	context.put("id_permohonan", idpermohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_hakmilik", idHakmilik);
	    	context.put("id_borangg", id_borangg);
	    	context.put("id_siasatan",id_siasatan);
	    	context.put("id_fail",id_fail);
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		System.out.println(vm);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
//	@SuppressWarnings({ "static-access" })
//	private void sendEmail(String nama_projek,String tarikh_permohonan,String userIdKementerian, String id_jawatan_user, String id_user, String purpose) throws Exception{
//
//		EmailOnline_bak20200613 et = new EmailOnline_bak20200613();
//		et.setEmail("","",purpose,"",nama_projek,tarikh_permohonan,"",userIdKementerian, id_jawatan_user, id_user);
//		
//	}//close sendEmail
	private void sendEmail(String nama_projek,String tarikh_permohonan,String userIdKementerian, String id_jawatan_user, String id_user
			, String purpose,HttpSession session) throws Exception{
		EmailOnline et = new EmailOnline();
		et.setEmail("","",purpose,"",nama_projek,tarikh_permohonan,"",userIdKementerian, id_jawatan_user, id_user
				,String.valueOf(session.getAttribute("portal_username")));
		
	}//close sendEmail

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
	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik,String idSubHM) throws Exception{
    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelUPT.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,idSubHM);
	
	}//close addSuburusanHakmilik
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt,String idsub) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,idsub);
		
	}//close updateSuburusanStatusFailPPT
	
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
	private void getIdAwardByIdHakmilikPB(String id_hakmilikpb) throws Exception{
		
		Vector getIdAward = new Vector();
		getIdAward.clear();
		
		String id_award = "";
		model.setDataIdAward(id_hakmilikpb);
		getIdAward = model.getDataIdAward();
		if(getIdAward.size()!=0){
			Hashtable ia = (Hashtable)getIdAward.get(0);
			id_award = (String)ia.get("id_award");
		}
		
		//id
		context.put("id_award",id_award);
		
//		if(id_award!=""){
//			JOptionPane.showMessageDialog (null, "Sila Pastikan Pihak Berkepentingan Ini Telah Tentukan Pampasannya", "Langkah Seterusnya", JOptionPane.WARNING_MESSAGE);
//			context.put("showAlert", "no");
//		}else{
//			context.put("showAlert", "yes");
//		}
		
	}//close getIdAwardByIdHakmilikPB
	
	private void valHideItem() throws Exception{
    	
		String statusSerah = getParam("socStatusSerah");
		
		//onchange validation
		if(statusSerah.equals("2")){
			context.put("hideItem", "yes");
		}else{
    		context.put("hideItem", "no");
		}
		
	}//close valHideItem

	@SuppressWarnings("unchecked")
	private void defaultNamaPenerima() throws Exception{
		
		Vector getNamaPB = new Vector();
		getNamaPB.clear();
		
		String idpb = getParam("socPB");
		
		//get name
		modelNotis.setNamaPB(idpb);
		getNamaPB = modelNotis.getNamaPB();
		String namaPB = "";
		if(getNamaPB.size()!=0){
			Hashtable npb = (Hashtable)getNamaPB.get(0);
			namaPB = (String)npb.get("nama_pb");
		}
		
		context.put("txtNamaTerima", namaPB);
		
	}//close defaultNamaPenerima
	
	@SuppressWarnings("unchecked")
	private void getAndSetData(String idpermohonan,String mode,String idHakmilik) throws Exception{
    	
		Vector getNamaPB = new Vector();
		getNamaPB.clear();
		
		String idpb = getParam("socPB");
		
		//get name
		modelNotis.setNamaPB(idpb);
		getNamaPB = modelNotis.getNamaPB();
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
			context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",Utils.parseLong(idpb),null,"onChange=\"onchangeGetname();\" style=width:310px"));
		}else{
			context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",Utils.parseLong(idpb),null,"onChange=\"onchangeGetnameUpdate();\" style=width:310px"));
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

	@SuppressWarnings({ "unchecked" })
	private void updateAfidevit(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		//id
		h.put("id_afidavit", getParam("id_afidavit"));
		
		h.put("txtTujuan", getParam("txtTujuan"));
		h.put("txtPerkara", getParam("txtPerkara"));
		h.put("socMahkamah", getParam("socMahkamah"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.updateAfidevit(h);
  
	}//close updateAfidevit
	
	@SuppressWarnings({ "unchecked" })
	private void simpanAfidevit(HttpSession session) throws Exception{
		Hashtable h = new Hashtable();		
		//id
		h.put("id_award", getParam("id_award"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));		
		h.put("txtTujuan", getParam("txtTujuan"));
		h.put("txtPerkara", getParam("txtPerkara"));
		h.put("socMahkamah", getParam("socMahkamah"));		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));		
		FrmSek8PampasanData.simpanAfidevit(h);  
	}//close simpanAfidevit
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataAfidevit(String id_award,String id_negeriProjek,String disability) throws Exception{
		
		Vector dataAfidevit = new Vector();
		dataAfidevit.clear();
		
		//model for validation new / view
		String id_pejabat = "";
		String id_bandar = "";
		String id_negeri = "";
		String id_afidavit = "";
		model.setDataAfidevit(id_award);
		dataAfidevit = model.getDataAfidevit();
		context.put("dataAfidevit",dataAfidevit);
		if(dataAfidevit.size()!=0){
			Hashtable da = (Hashtable)dataAfidevit.get(0);
			id_afidavit = (String)da.get("id_afidavit");
			id_pejabat = (String)da.get("id_pejabat");
			id_negeri = (String)da.get("id_negeri");
			id_bandar = (String)da.get("id_bandar");
		}
		
		//id
		context.put("id_afidavit",id_afidavit);
		
		String mode = "";
		
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		//dropdown
		if(id_negeriProjek!=""){
			context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriProjek),"socMahkamah",Utils.parseLong(id_pejabat), " "+mode+" onChange=\"doChangeMahkamahUpdate();\""));
		}else{
			context.put("selectMahkamah",HTML.SelectMahkamah("socMahkamah",Utils.parseLong(id_pejabat), " "+mode+" onChange=\"doChangeMahkamahUpdate();\""));
		}
		
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"disabled class=disabled style=width:300px"));
		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"disabled class=disabled  style=width:300px"));
		
	}//close dataAfidevit
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void getAndSetAF(String id_pejabat,String id_negeriProjek,String mode) throws Exception{
		
		if(mode.equals("new")){
			if(id_negeriProjek!=""){
				context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriProjek),"socMahkamah",Utils.parseLong(id_pejabat), "onChange=\"doChangeMahkamah();\""));
			}else{
				context.put("selectMahkamah",HTML.SelectMahkamah("socMahkamah",Utils.parseLong(id_pejabat), "onChange=\"doChangeMahkamah();\""));
			}			
		}else{
			if(id_negeriProjek!=""){
				context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriProjek),"socMahkamah",Utils.parseLong(id_pejabat), "onChange=\"doChangeMahkamahUpdate();\""));
			}else{
				context.put("selectMahkamah",HTML.SelectMahkamah("socMahkamah",Utils.parseLong(id_pejabat), "onChange=\"doChangeMahkamahUpdate();\""));
			}
		}
		
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";
		String id_negeri = "";
		String id_bandar = "";
		model.setDataPejabat(id_pejabat);
		Vector dataPejabat = model.getDataPejabat();
		if(dataPejabat.size()!=0){
			Hashtable dp = (Hashtable)dataPejabat.get(0);
			alamat1 = (String)dp.get("alamat1");
			alamat2 = (String)dp.get("alamat2");
			alamat3 = (String)dp.get("alamat3");
			poskod = (String)dp.get("poskod");
			id_negeri = (String)dp.get("id_negeri");
			id_bandar = (String)dp.get("id_bandar");
		}
		
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"disabled class=disabled style=width:300px"));
		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"disabled class=disabled  style=width:300px"));
		
		context.put("txtAlamat1",alamat1);
		context.put("txtAlamat2",alamat2);
		context.put("txtAlamat3",alamat3);
		context.put("txtPoskod",poskod);
		context.put("txtTujuan",getParam("txtTujuan"));
		context.put("txtPerkara",getParam("txtPerkara"));
		
	}//close getAndSetAF
	
	
	private void resetValueAF() throws Exception{
		
		context.put("txtAlamat1","");
		context.put("txtAlamat2","");
		context.put("txtAlamat3","");
		context.put("txtPoskod","");
		context.put("txtTujuan","");
		context.put("txtPerkara","");

	}//close resetValueAF
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmSek8PampasanData.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataDetailPB(String id_siasatan, String idHakmilik, String idpb,String mode) throws Exception{
    	
		Vector dataPBBorangH = new Vector();
		dataPBBorangH.clear();
		
		String jenisNoPB = "";
    	String no_pb = "";
    	String id_hakmilikpb = "";
    	
		double jumlah_award = 0;
		
		model.setDataPBAward(id_siasatan,idpb);
		Vector dataPBAward = model.getDataPBAward();
		if(dataPBAward.size()!=0){
			Hashtable pba = (Hashtable)dataPBAward.get(0);
			jumlah_award = (Double)pba.get("bayar_pampasan");
		}
		
		model.setDataPBBorangH(idHakmilik,idpb);
		dataPBBorangH = model.getDataPBBorangH();
		if(dataPBBorangH.size()!=0){
			Hashtable dpb = (Hashtable)dataPBBorangH.get(0);
			no_pb = (String)dpb.get("no_pb");      			
			jenisNoPB = (String)dpb.get("id_jenisnopb");
			id_hakmilikpb = (String)dpb.get("id_hakmilikpb");
		}
    	
		context.put("id_hakmilikpb",id_hakmilikpb);
		
		if(mode.equals("new")){
			context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",Utils.parseLong(idpb),null,"style=width:300px onChange=\"getPBEFT()\""));
		}else{
			context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",Utils.parseLong(idpb),null,"style=width:300px onChange=\"getPBEFTUpdate()\""));
		}
		
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(jenisNoPB),"style=width:auto disabled class=disabled"));
		
		
		//String total = "";
		//if(jumlah_award!=0){total = Utils.format2Decimal(jumlah_award);}else{total = "";}
		
		context.put("lblNoPB",no_pb);
		context.put("txtNoRujSuratEft", getParam("txtNoRujSuratEft"));
		context.put("txtNoEft", getParam("txtNoEft"));
		context.put("txtAmaun", jumlah_award);
		context.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));	
		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		context.put("txtNoBaucer", getParam("txtNoBaucer"));
		context.put("txtPerihal", getParam("txtPerihal"));
		
	}//close dataDetailPB
	
	@SuppressWarnings("unchecked")
	private void updateBentukBayaran(HttpSession session) throws Exception{
    
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
		
		//id
		String id_award = getParam("id_award");

	    List items = upload.parseRequest(request);
	    
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	    	FileItem item = (FileItem)itr.next();
	    	if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  saveDataTwice(item,id_award);
	    	}
	    }

	}//close simpanDataDanFail
	
	private void saveDataTwice(FileItem item,String id_award) throws Exception {
  		Db db = null;
        try {
        	db = new Db();
        	long id_dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN " +
        			"(id_dokumen,id_award,nama_fail,jenis_Mime,content) " +
        			"values(?,?,?,?,?)");
        	ps.setLong(1, id_dokumen);
        	ps.setString(2, id_award);
        	ps.setString(3,item.getName());
        	ps.setString(4,item.getContentType());
        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
        	ps.executeUpdate();
            con.commit();
            
	    }catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah muatnaik fail");
	    }finally {
		      if (db != null) db.close();
	    }
	}
	
	@SuppressWarnings("unchecked")
	private void simpanDataDanFail(HttpSession session) throws Exception{
    
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
		
		//id
		String id_award = getParam("id_award");
	
	    List items = upload.parseRequest(request);
	    
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	    	FileItem item = (FileItem)itr.next();
	    	if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  saveData(item,id_award,session);
	    	}
	    }

	}//close simpanDataDanFail
	private void saveData(FileItem item,String id_award,HttpSession session) throws Exception {
  		Db db = null;
  		String userId = (String) session.getAttribute("_ekptg_user_id");
        try {
        	db = new Db();
        	long id_dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN " +
        			"(id_dokumen,id_award,nama_fail,jenis_Mime,content,jenis_dokumen,id_jenisdokumen,id_masuk,tarikh_masuk) " +
        			"values(?,?,?,?,?,?,?,?,SYSDATE)");
        	ps.setLong(1, id_dokumen);
        	ps.setString(2, id_award);
        	ps.setString(3,item.getName());
        	ps.setString(4,item.getContentType());
        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
        	ps.setString(6, "surat_tawaran_award");
        	ps.setString(7, "1529");
        	ps.setString(8, userId);  
        	//ps.setString(6, getParam("nama_dokumen"));
        	ps.executeUpdate();
            con.commit();
            
	    }catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah muatnaik fail : "+se.getMessage());
	    }finally {
		      if (db != null) db.close();
	    }
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetPBAward(String idSiasatan,String idHakmilik,String id_pihakberkepentingan) throws Exception{
    	
		Vector dataPBAward = new Vector();
		dataPBAward.clear();
		
		String syer_atas = "";
		String syer_bawah = "";
		double luas_ambil = 0;
		String unit_luas = "";
		String tawaran = "";
		double jumlah_award = 0;
		String id_award = "";
		String tarikh_hantar = "";
		
		model.setDataPBAward(idSiasatan,id_pihakberkepentingan);
		dataPBAward = model.getDataPBAward();
		if(dataPBAward.size()!=0){
			Hashtable pba = (Hashtable)dataPBAward.get(0);
			id_award = (String)pba.get("id_award");
			syer_atas = (String)pba.get("syer_atas");
			syer_bawah = (String)pba.get("syer_bawah");
			luas_ambil = (Double)pba.get("luas_ambil");
			unit_luas = (String)pba.get("unit_luas");
			tawaran = (String)pba.get("tawaran");
			jumlah_award = (Double)pba.get("bayar_pampasan");
			tarikh_hantar = (String)pba.get("tarikh_hantar");
		}
		
		//id
		context.put("id_award",id_award);
		
		//dropdown
		context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",Utils.parseLong(id_pihakberkepentingan),null,"style=width:300px onChange=\"PBgetAward()\""));
		
		context.put("lblLuasAmbil", luas_ambil);
		context.put("lblKodLuas", unit_luas);
		context.put("lblSyorAtas", syer_atas);
		context.put("lblSyorBawah", syer_bawah);
		context.put("lblTawaran", tawaran);
		
		String total = "";
		if(jumlah_award!=0){total = Utils.format2Decimal(jumlah_award);}else{total = "";}
		
		String luas = "";
		if(luas_ambil!=0){luas = Utils.format2Decimal(luas_ambil);}else{luas = "";}
		
		context.put("lblLuasAmbil", luas);
		context.put("lblJumlahAward", total);
	
	}//close resetValue
	
	private void resetValuePenerimaan() throws Exception{
    	
		context.put("id_hakmilikpb", "");
		context.put("id_bayaran", "");
		
		context.put("txtNoPB", "");
		context.put("txdTarikhAkhirBayar", "");
		context.put("txtBilLewat", "");
		context.put("txtDendaLewat", "");
		context.put("sorJenisAward", "");
		context.put("sorFlagSerah", "");	
		context.put("txtPenerimaCek", "");
		context.put("txtNoCek", "");
		context.put("txtAmaunCek", "");
		context.put("txdTarikhCek", "");
		context.put("txdTarikhAmbilCek", "");
		context.put("txtMasaAmbil", "");
		context.put("socJenisWaktu", "");
		context.put("txtTempatAmbil", "");
		
	}//close resetValuePenerimaan
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetPenerimaan(String id_siasatan,String idHakmilik,String mode,String idTerimabayaran) throws Exception{
    	
		Vector dataPBBorangH = new Vector();
		dataPBBorangH.clear();
		
		Vector dataSuratAgensi = new Vector();
		dataSuratAgensi.clear();
		
		String idpb = getParam("socPB");
		
		//dropdown select pb by hakmilik filter by use or unused
		if(mode.equals("new")){
			context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",Utils.parseLong(idpb),null,"style=width:300px onChange=\"onchangeSelectPB()\""));
		}else{
			context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",Utils.parseLong(idpb),null,"style=width:300px onChange=\"onchangeSelectPBUpdate()\""));
		}
		
		String no_pb = "";
		String jenisNoPB = "";
		String id_hakmilikpb = "";
		String tarikh_akhir_bayar = "";
		String nama_penyata_bank = "";
		String tarikh_serah = "";
		model.setDataPBBorangH(idHakmilik,idpb);
		dataPBBorangH = model.getDataPBBorangH();
		if(dataPBBorangH.size()!=0){
			
			Hashtable dpb = (Hashtable)dataPBBorangH.get(0);
			no_pb = (String)dpb.get("no_pb");
			nama_penyata_bank = (String)dpb.get("nama_penyata_bank");
			jenisNoPB = (String)dpb.get("id_jenisnopb");
			id_hakmilikpb = (String)dpb.get("id_hakmilikpb");	
			tarikh_akhir_bayar = (String)dpb.get("tarikh_akhir_bayar");
			tarikh_serah = (String)dpb.get("tarikh_serahan");
	
		}
		//myLogger.info("tarikh_serah :"+tarikh_serah);
		
		String tarikh_bayaran = "";
		model.setdataSuratAgensi(idTerimabayaran);
		dataSuratAgensi = model.getdataSuratAgensi();
		if(dataSuratAgensi.size()!=0){
			Hashtable dsa = (Hashtable)dataSuratAgensi.get(0);
			tarikh_bayaran = (String)dsa.get("tarikh_bayaran");
		}
		
		
		double jumlah_award = 0;
		String tarikh_hantar = "";
		model.setDataPBAward(id_siasatan,idpb);
		Vector dataPBAward = model.getDataPBAward();
		if(dataPBAward.size()!=0){
			Hashtable pba = (Hashtable)dataPBAward.get(0);
			jumlah_award = (Double)pba.get("bayar_pampasan");
			tarikh_hantar = (String)pba.get("tarikh_hantar");			
		}
		
		
		//get tarikh genap (3 bulan lpas tarikh borang h)
		int dateLompat = 0;
		int year = 0;
		int day = 0;
		int month = 0;
		String tarikh_genap = "";
		Calendar c = Calendar.getInstance();
		if(tarikh_hantar!=""){
			c.setTime(sdf.parse(tarikh_hantar));
			c.add(Calendar.MONTH, 3);
		    tarikh_genap = sdf.format(c.getTime());
		    year = c.get(Calendar.YEAR);
		    month = c.get(Calendar.MONTH);
		    day = c.get(Calendar.DATE);
 		}

		String txd3bulan = tarikh_genap;
		context.put("txd3bulan",txd3bulan);
		
		myLogger.info("txd3bulan :"+txd3bulan);
		SimpleDateFormat fmt = new SimpleDateFormat("ddMMyyyy");
		
		//main date
		String maindate = day+""+month+""+year;
		Date tarikhMain = null;
		if(tarikh_hantar!=""){
			tarikhMain = fmt.parse(maindate);
		}
		
		myLogger.info("tarikhMain :"+tarikhMain);
		
		//Syarat 1
		String dt = "19011984";
		Date tarikhSyarat1 = null;
		tarikhSyarat1 = fmt.parse(dt);

		//Syarat 2
		String dt2 = "20011984";
		String dt2a = "28021998";
		Date tarikhSyarat2 = null;
		Date tarikhSyarat2a = null;
		tarikhSyarat2 = fmt.parse(dt2);
		tarikhSyarat2a = fmt.parse(dt2a);
		
		//Syarat 3
		String dt3 = "01031998";
		Date tarikhSyarat3 = null;
		tarikhSyarat3 = fmt.parse(dt3);
		
		
		double chargePercentage = 0.00;
		if(tarikh_hantar!=""){
			if(tarikhMain.compareTo(tarikhSyarat1)<=0){
				chargePercentage = 0.08;
			}else if((tarikhMain.compareTo(tarikhSyarat2)>=0) && (tarikhMain.compareTo(tarikhSyarat2a)<=0)){
				chargePercentage = 0.06;
			}else if(tarikhMain.compareTo(tarikhSyarat3)>=0){
				chargePercentage = 0.08;
			}else{
				chargePercentage = 0.08;
			}
		}
		
		//get tahun lompat
		if(year != 0 && (year / 4 == 0)){
			dateLompat = 366;
		}else{
			dateLompat = 365;
		}
		
		//get bilangan hari lewat
		long hariLewat = 0;
		if(tarikh_bayaran!="" && tarikh_genap!=""){
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(sdf.parse(tarikh_genap));
			cal2.setTime(sdf.parse(tarikh_bayaran));
			long milis1 = cal1.getTimeInMillis();
			long milis2 = cal2.getTimeInMillis();
			long diff = milis2 - milis1;
			hariLewat = diff / (24 * 60 * 60 * 1000);
		}
		
		
		
		
		double amaunCaj = 0.00;
		if(hariLewat > 0){
			amaunCaj = (jumlah_award * hariLewat * chargePercentage) / dateLompat ;
		}else{
			hariLewat = 0;
		}
		
		//auto kira caj bayarat lewat
		context.put("txtBilLewat", hariLewat);
		context.put("txtDendaLewat", Utils.format2Decimal(amaunCaj));
		
		//id
		context.put("id_hakmilikpb", id_hakmilikpb);
		
		//dropdown disabled for filter selectpb only
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(jenisNoPB),"style=width:auto disabled class=disabled"));
		
		context.put("txtNoPB", no_pb);
		context.put("txdTarikhAkhirBayar", tarikh_akhir_bayar);
		context.put("txtPenerimaCek", nama_penyata_bank);
		context.put("txdTarikhSerah", tarikh_serah);
		//System.out.println("tarikh serahan borang h--"+tarikh_serah);
		
		
		
		context.put("sorJenisAward", getParam("sorJenisAward"));
		context.put("sorFlagSerah", getParam("sorFlagSerah"));	
		context.put("txtNoCek", getParam("txtNoCek"));
		context.put("txtAmaunCek", jumlah_award);
		context.put("txdTarikhCek", getParam("txdTarikhCek"));
		context.put("txdTarikhAmbilCek", getParam("txdTarikhAmbilCek"));
		context.put("txtMasaAmbil", getParam("txtMasaAmbil"));
		context.put("socJenisWaktu", getParam("socJenisWaktu"));
		context.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		
		
	}//close getAndSetPenerimaan
	
	private void resetValue() throws Exception{
    	
		context.put("id_hakmilikpb","");
		context.put("id_bayaran","");
		
		context.put("lblLuasAmbil", "");
		context.put("lblKodLuas", "");
		context.put("lblSyorAtas", "");
		context.put("lblSyorBawah", "");
		context.put("lblTawaran", "");
		context.put("lblJumlahAward", "");
	
		context.put("lblNoPB","");
		context.put("txtNoRujSuratEft", "");
		context.put("txtNoEft", "");
		context.put("txtAmaun","");
		context.put("txdTarikhTerimaSurat", "");	
		context.put("txdTarikhSurat", "");
		context.put("txtNoBaucer", "");
		context.put("txtPerihal", "");
		
		context.put("txtBilLewat", "");
		context.put("txtDendaLewat", "");
		context.put("sorJenisAward", "");
		
	}//close resetValue
	
	@SuppressWarnings({ "unchecked" })
	private String getNoLot(String idHakmilik) throws Exception{
    	
		Vector dataByHM = new Vector();
		dataByHM.clear();
		
		String no_lot = "";
		model.setDataByHM(idHakmilik);
		dataByHM = model.getDataByHM();
		if(dataByHM.size()!=0){
			Hashtable db = (Hashtable)dataByHM.get(0);
			no_lot = (String)db.get("no_lot");
		}
		context.put("lblNoLot", no_lot);
		return no_lot;	
		
	}//close getNoLot
	
	@SuppressWarnings("unchecked")
	private void dataWarta(String idpermohonan) throws Exception{
    	
		Vector dataWarta = new Vector();
		dataWarta.clear();
		
		String id_warta = "";
		String no_warta = "";
		String tarikh_warta = "";
		
		model.setDataWarta(idpermohonan);
		dataWarta = model.getDataWarta();
		if(dataWarta.size()!=0){
			Hashtable dw = (Hashtable)dataWarta.get(0);
			id_warta = (String)dw.get("id_warta");
			no_warta = (String)dw.get("no_warta");
			tarikh_warta = (String)dw.get("tarikh_warta");
		}
		
		context.put("id_warta", id_warta);
		context.put("lblNoWarta", no_warta);
		context.put("lblTarikhWarta", tarikh_warta);
		
	}//close dataWarta
	
	//hapus borang h
	private void hapusBorangH(HttpSession session) throws Exception{
    	
		String id_borangh = getParam("id_borangh");
	
		FrmSek8PampasanData.hapusBorangH(id_borangh);
  
	}//close hapusBorangH
	
	private void hapusEFT(HttpSession session) throws Exception{
    	
		String id_bayaran = getParam("id_bayaran");
	
		FrmSek8PampasanData.hapusEFT(id_bayaran);
  
	}//close hapusEFT
	
	//hapus Penerimaan Cek
	private void hapusPenerimaanCek() throws Exception{
    	
		String id_bayaran = getParam("id_bayaran");
	
		FrmSek8PampasanData.hapusPenerimaanCek(id_bayaran);
  
	}//close hapusPenerimaanCek
	
	//hapus
	private void hapusMaklumatSuratAgensi() throws Exception{
    	
		String id_terimabayaran = getParam("id_terimabayaran");
	
		FrmSek8PampasanData.hapusMaklumatSuratAgensi(id_terimabayaran);
  
	}//close hapusMaklumatSuratAgensi
	
	//hapus
	private void hapusDokumen() throws Exception{
    	
		String id_award = getParam("id_award");
	
		FrmSek8PampasanData.hapusDokumen(id_award);
  
	}//close hapusBorangH
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updatePenerimaanBorangH(HttpSession session,String id_hakmilik) throws Exception{

		Vector getIdHakmilikpb = new Vector();
		getIdHakmilikpb.clear();
		
		Hashtable h = new Hashtable();
		
		//id
		h.put("id_borangh", getParam("id_borangh"));
		
		String id_hakmilikpb = "";
		model.setIdHakmilikpb(id_hakmilik,getParam("socPB"));
		getIdHakmilikpb = model.getIdHakmilikpb();
		if(getIdHakmilikpb.size()!=0){
			Hashtable idh = (Hashtable)getIdHakmilikpb.get(0);
			id_hakmilikpb = (String)idh.get("id_hakmilikpb");
		}
		
		h.put("id_hakmilikpb", id_hakmilikpb);
		
		h.put("txtNamaHantar", getParam("txtNamaHantar"));
		h.put("socJenisSerah", getParam("socJenisSerah"));
		h.put("socStatusSerah", getParam("socStatusSerah"));
		h.put("txdTarikhSerah", getParam("txdTarikhSerah"));
		h.put("txtCatatan", getParam("txtCatatan"));
		
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
		
		FrmSek8PampasanData.updatePenerimaanBorangH(h);
  
	}//close updatePenerimaanBorangH
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanPenerimaanBorangH(HttpSession session,String status,String id_hakmilik) throws Exception{

		Vector getIdHakmilikpb = new Vector();
		getIdHakmilikpb.clear();
		
		Hashtable h = new Hashtable();
		
		//id
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_borangg", getParam("id_borangg"));
		h.put("id_borangh", getParam("id_borangh"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		String id_hakmilikpb = "";
		model.setIdHakmilikpb(id_hakmilik,getParam("socPB"));
		getIdHakmilikpb = model.getIdHakmilikpb();
		if(getIdHakmilikpb.size()!=0){
			Hashtable idh = (Hashtable)getIdHakmilikpb.get(0);
			id_hakmilikpb = (String)idh.get("id_hakmilikpb");
		}
		
		h.put("id_hakmilikpb", id_hakmilikpb);
		
		//data bukti penyampaian
		h.put("txtNamaHantar", getParam("txtNamaHantar"));
		h.put("socJenisSerah", getParam("socJenisSerah"));
		h.put("socStatusSerah", getParam("socStatusSerah"));
		h.put("txdTarikhSerah", getParam("txdTarikhSerah"));
		h.put("txtCatatan", getParam("txtCatatan"));

		//data penerima
		h.put("txtNamaTerima", getParam("txtNamaTerima"));
		h.put("txtNoKP", getParam("txtNoKP"));
		h.put("socJenisNoKP", getParam("socJenisNoKP"));
		h.put("txtHubungan", getParam("txtHubungan"));
		
		//data tampal
		h.put("txtMasaTampal", getParam("txtMasaTampal"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		h.put("txtTempatTampal", getParam("txtTempatTampal"));
		
		FrmSek8PampasanData.simpanPenerimaanBorangH(h);
		
		if(status.equals("62") || status.equals("74")){
			FrmSek8PampasanData.updateStatus(h);
		}
		
	}//close simpanPenerimaanBorangH
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPenerimaanBorangH(String id_borangg) throws Exception{
    	/*
		Vector listBorangH = new Vector();
		listBorangH.clear();
		
		model.setListBorangH(id_borangg);
		listBorangH = model.getListBorangH();
		
		//data and size
		context.put("listBorangH",listBorangH);
		*/
		//String id_borangh = "";
		//context.put("id_borangh",id_borangh);
		context.put("list_borangh",model.setListBorangH_count(id_borangg));
		
	}//close listPenerimaanBorangH
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataPenerimaanBorangH(String idHakmilik,String id_borangh,String disability) throws Exception{
    	
		Vector dataBorangH = new Vector();
		dataBorangH.clear();
		
		String statusSerah = "";
		String id_kodnopb = "";
		String id_jenisnopb = "";
		String id_pihakberkepentingan = "";
		String id_hakmilikpb = "";
		String jenis_serahan = "";
		model.setDataBorangH(id_borangh);
		dataBorangH = model.getDataBorangH();
		if(dataBorangH.size()!=0){
	
			Hashtable dbh = (Hashtable)dataBorangH.get(0);
			id_pihakberkepentingan = (String)dbh.get("id_pihakberkepentingan");
			id_hakmilikpb = (String)dbh.get("id_hakmilikpb");
			id_kodnopb = (String)dbh.get("id_kodnopb");
			id_jenisnopb = (String)dbh.get("id_jenisnopb");
			statusSerah = (String)dbh.get("status_serahan");
			jenis_serahan = (String)dbh.get("jenis_serahan");

		}
		
		//id
		context.put("id_pihakberkepentingan",id_pihakberkepentingan);
		context.put("id_hakmilikpb",id_hakmilikpb);
		
		//data
		context.put("dataBorangH",dataBorangH);
		

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
		context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",Utils.parseLong(id_pihakberkepentingan),null,""+mode+" onChange=\"onchangeGetnameUpdate();\" style=width:310px"));
		
		//data penerima
		context.put("selectJenisNoKP",HTML.SelectJenisNoPb("socJenisNoKP",Utils.parseLong(id_jenisnopb),"style=width:auto "+mode+""));
		
		
	}//close listPenerimaanBorangH
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataBorangG(String id_siasatan) throws Exception{
    	
		Vector dataBorangG = new Vector();
		dataBorangG.clear();
		
		String id_borangg = "";
		String no_siasatan = "";
		String tarikh_borangh = "";
		double jumlah_award = 0 ;

		model.setDataBorangG(id_siasatan);
		
		dataBorangG = model.getDataBorangG();
		if(dataBorangG.size()!=0){
			Hashtable gh = (Hashtable)dataBorangG.get(0);
			id_borangg = (String)gh.get("id_borangg");
			no_siasatan = (String)gh.get("no_siasatan");
			tarikh_borangh = (String)gh.get("tarikh_borangh");
			//jumlah_award = (String)gh.get("jumlah_award");
			jumlah_award = (Double)gh.get("jumlah_award");

		}
		
		//data and id
		context.put("dataBorangG",dataBorangG);
		context.put("id_borangg",id_borangg);
		context.put("lblNoSiasatan", no_siasatan);
		context.put("tarikh_borangh", tarikh_borangh);
		
		context.put("jumlah_award", jumlah_award);
		myLogger.info("jumlah_award :"+jumlah_award);
		
	}//close dataBorangG
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataDokumenBorangG(String idHakmilik) throws Exception{
    	
		Vector dataDokumenBorangG = new Vector();
		dataDokumenBorangG.clear();
		
		model.setDataDokumenBorangG(idHakmilik);
		dataDokumenBorangG = model.getDataDokumenBorangG();
		
		//data and id
		context.put("idHakmilik",idHakmilik);
		context.put("dataDokumenBorangG",dataDokumenBorangG);
		
	}//close dataBorangG

	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPenyediaanPampasan(String idHakmilik) throws Exception{
    	
		Vector listSediaPampasan = new Vector();
		listSediaPampasan.clear();
		
		model.setlistPenyediaanPampasan(idHakmilik);
		listSediaPampasan = model.getlistPenyediaanPampasan();
		context.put("listSediaPampasan",listSediaPampasan);
		context.put("list_saiz",listSediaPampasan.size());
		
	}//close listPenyediaanPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataBentukBayaran(String idAward) throws Exception{
    	
		Vector dataBentukPampasan = new Vector();
		dataBentukPampasan.clear();
		
		model.setdataBentukPampasan(idAward);
		dataBentukPampasan = model.getDataBentukPampasan();
		context.put("dataBentukPampasan",dataBentukPampasan);
		
	}//close dataBentukBayaran
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listBentukPampasan(String idHakmilik) throws Exception{
    	
		Vector listBentukPampasan = new Vector();
		listBentukPampasan.clear();
		
		model.setlistBentukPampasan(idHakmilik);
		listBentukPampasan = model.getlistBentukPampasan();
		context.put("listBentukPampasan",listBentukPampasan);
		context.put("saiz_jadual",listBentukPampasan.size());
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPenerimaanCek(String id_terimabayaran,String modeCaraBayar) throws Exception{
    	
		Vector listMaklumatPenerimaanCek = new Vector();
		listMaklumatPenerimaanCek.clear();
		
		model.setListPenerimaanCek(id_terimabayaran,modeCaraBayar);
		listMaklumatPenerimaanCek = model.getListPenerimaanCek();
		context.put("listMaklumatPenerimaanCek",listMaklumatPenerimaanCek);
		context.put("saiz_list_penerimaan", listMaklumatPenerimaanCek.size());
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listEFT(String id_hakmilik) throws Exception{
    	
		Vector listEFT = new Vector();
		listEFT.clear();
		
		model.setListEFT(id_hakmilik);
		listEFT = model.getListEFT();
		context.put("listEFT",listEFT);
		context.put("saiz_list_eft", listEFT.size());
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataEFT(String id_bayaran,String idHakmilik,String disability) throws Exception{
    	
		Vector dataEFT = new Vector();
		dataEFT.clear();
		
		String id_pb = "";
		String id_jenisnopb = "";
		
		model.setDataEFT(id_bayaran);
		dataEFT = model.getDataEFT();
		context.put("dataEFT",dataEFT);
		if(dataEFT.size()!=0){
			Hashtable de = (Hashtable)dataEFT.get(0);
			id_pb = (String)de.get("id_pihakberkepentingan");
			id_jenisnopb = (String)de.get("id_jenisnopb");
		}
		
		
		String mode ="";
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",Utils.parseLong(id_pb),null,"style=width:300px "+mode+" onChange=\"getPBEFTUpdate()\""));
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(id_jenisnopb),"style=width:auto disabled class=disabled"));
		
	}//close dataEFT
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listMaklumatSuratAgensi(String idHakmilik) throws Exception{
    	
		Vector listSuratAgensi = new Vector();
		listSuratAgensi.clear();
		
		model.setlistSuratAgensi(idHakmilik);
		listSuratAgensi = model.getlistSuratAgensi();
		context.put("listSuratAgensi",listSuratAgensi);
		context.put("list_surat", listSuratAgensi.size());
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMaklumatSuratAgensi(String idTerimaBayaran) throws Exception{
    	
		Vector dataSuratAgensi = new Vector();
		dataSuratAgensi.clear();
		
		model.setdataSuratAgensi(idTerimaBayaran);
		dataSuratAgensi = model.getdataSuratAgensi();
		context.put("dataSuratAgensi",dataSuratAgensi);
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataPenerimaanCek(String idHakmilik,String id_bayaran,String disability,String id_terimabayaran) throws Exception{
    	
		Vector dataPenerimaanCek = new Vector();
		dataPenerimaanCek.clear();
		
		String id_jenisnopb = "";
		String id_pihakberkepentingan = "";
		String id_jenisnowakil = "";
		
		model.setdataPenerimaanCek(id_bayaran);
		dataPenerimaanCek = model.getdataPenerimaanCek();
		context.put("dataPenerimaanCek",dataPenerimaanCek);
		if(dataPenerimaanCek.size()!=0){
			Hashtable dpc = (Hashtable)dataPenerimaanCek.get(0);
			id_jenisnopb = (String)dpc.get("id_jenisnopb");
			id_pihakberkepentingan =(String)dpc.get("id_pihakberkepentingan"); 
			id_jenisnowakil =(String)dpc.get("id_jenisnowakil"); 
		}
		
		String mode = "";
		
		if(disability.equals("disabled")){
			mode = "disabled class=disabled";
		}else{
			mode = "";
		}
		
		//dropdown select pb by hakmilik filter by use or unused
		context.put("selectPB",HTML.SelectPBbyHakmilikWithPenerimaanBorangH(idHakmilik,"socPB",Utils.parseLong(id_pihakberkepentingan),null,"style=width:300px "+mode+" onChange=\"onchangeSelectPBUpdate()\""));
		
		//dropdown disabled for filter selectpb only
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(id_jenisnopb),"style=width:auto disabled class=disabled"));
		
		//dropdown di penyerahan cek
		context.put("selectJenisNoWakil",HTML.SelectJenisNoPb("socJenisNoWakil",Utils.parseLong(id_jenisnowakil)," "+mode+" style=width:auto"));
		
	}//close listBentukPampasan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String getTotalPampasan(String id_siasatan) throws Exception{
    	
		Vector totalPampasan = new Vector();
		totalPampasan.clear();
		
		String total_pampasan = "";
		model.setTotalPampasan(id_siasatan);
		totalPampasan = model.getTotalPampasan();
		if(totalPampasan.size()!=0){
			Hashtable tp = (Hashtable)totalPampasan.get(0);
			total_pampasan = (String)tp.get("total_pampasan");
		}
		
		//total pampasan
		context.put("total_pampasan", total_pampasan);
		context.put("testid", id_siasatan);
		
		return total_pampasan;
		
	}//close getTotalPampasan
	
	
	@SuppressWarnings("unchecked")
	private void simpanDataBorangG(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		//id
		h.put("id_borangg", getParam("id_borangg"));
		h.put("id_siasatan", getParam("id_siasatan"));
		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("txtJumlahAward", Utils.RemoveSymbol(getParam("txtJumlahAward")));
		h.put("txdTarikhBorangG", getParam("txdTarikhBorangG"));
		h.put("txdTarikhBorangH", getParam("txdTarikhBorangH"));
		h.put("idImejBorangH", getParam("idImejBorangH"));
		h.put("idImejBorangG", getParam("idImejBorangG"));
		h.put("idDokumen", getParam("idDokumen"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.simpanDataBorangG(h);
		
  
	}//close simpanDataBorangG
	
	@SuppressWarnings("unchecked")
	private void simpanMaklumatSuratAgensi(HttpSession session,String idHakmilik) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_hakmilik", idHakmilik);
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txtNamaBank", getParam("txtNamaBank"));
		h.put("txdTarikhAmbil", getParam("txdTarikhAmbil"));
		h.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		h.put("txdTarikhSedia", getParam("txdTarikhSedia"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.simpanMaklumatSuratAgensi(h);
  
	}//close simpanMaklumatSuratAgensi
	
	@SuppressWarnings("unchecked")
	private void simpanPenerimaanCek(HttpSession session,String id_hakmilikpb) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_terimabayaran", getParam("id_terimabayaran"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtBilLewat", getParam("txtBilLewat"));
		h.put("txdTarikhLewat", getParam("txdTarikhLewat"));
		h.put("txtDendaLewat", getParam("txtDendaLewat"));
		h.put("sorJenisAward", getParam("sorJenisAward"));
		h.put("sorFlagSerah", getParam("sorFlagSerah"));	
		h.put("txtPenerimaCek", getParam("txtPenerimaCek"));
		h.put("txtNoCek", getParam("txtNoCek"));
		h.put("txtAmaunCek", getParam("txtAmaunCek"));
		h.put("txdTarikhCek", getParam("txdTarikhCek"));
		h.put("txdTarikhAmbilCek", getParam("txdTarikhAmbilCek"));
		h.put("txtMasaAmbil", getParam("txtMasaAmbil"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		h.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.simpanPenerimaanCek(h);
  
	}//close simpanPenerimaanCek
	
	@SuppressWarnings("unchecked")
	private void updatePenerimaanCek(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_bayaran", getParam("id_bayaran"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtBilLewat", getParam("txtBilLewat"));
		h.put("txtDendaLewat", getParam("txtDendaLewat"));
		h.put("sorJenisAward", getParam("sorJenisAward"));
		h.put("sorFlagSerah", getParam("sorFlagSerah"));	
		h.put("txtPenerimaCek", getParam("txtPenerimaCek"));
		h.put("txtNoCek", getParam("txtNoCek"));
		h.put("txtAmaunCek", getParam("txtAmaunCek"));
		h.put("txdTarikhCek", getParam("txdTarikhCek"));
		h.put("txdTarikhAmbilCek", getParam("txdTarikhAmbilCek"));
		h.put("txtMasaAmbil", getParam("txtMasaAmbil"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		h.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		
		h.put("txdTarikhLewat", getParam("txdTarikhLewat"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.updatePenerimaanCek(h);
  
	}//close updatePenerimaanCek
	
	@SuppressWarnings("unchecked")
	private void updatePenyerahanCek(HttpSession session,String idstatus) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_bayaran", getParam("id_bayaran"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtNamaWakil", getParam("txtNamaWakil"));
		h.put("txtNoWakil", getParam("txtNoWakil"));
		h.put("txdTarikhSerahCek", getParam("txdTarikhSerahCek"));
		h.put("socJenisNoWakil", getParam("socJenisNoWakil"));	
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		if(idstatus.equals("68")){
			FrmSek8PampasanData.updateStatus(h,getParam("id_permohonan"));
    	}
		
		FrmSek8PampasanData.updatePenyerahanCek(h);
  
	}//close updatePenerimaanCek
	
	@SuppressWarnings("unchecked")
	private void simpanPBEFT(HttpSession session,String idstatus) throws Exception{

		Hashtable h = new Hashtable();
	
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtNoRujSuratEft", getParam("txtNoRujSuratEft"));
		h.put("txtNoEft", getParam("txtNoEft"));
		h.put("txtAmaun", Utils.RemoveSymbol(getParam("txtAmaun")));
		h.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));	
		h.put("txdTarikhSuratEFT", getParam("txdTarikhSuratEFT"));
		h.put("txtNoBaucer", getParam("txtNoBaucer"));
		h.put("txtPerihal", getParam("txtPerihal"));
		
		h.put("txtBilLewat", getParam("txtBilLewat"));
		h.put("txtDendaLewat", getParam("txtDendaLewat"));
		h.put("sorJenisAward", getParam("sorJenisAward"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		if(!idstatus.equals("82")){
			FrmSek8PampasanData.updateStatus(h,getParam("id_permohonan"));
    	}
		
		FrmSek8PampasanData.simpanPBEFT(h);
  
	}//close simpanPBEFT
	
	@SuppressWarnings("unchecked")
	private void updatePBEFT(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
	
		h.put("id_bayaran", getParam("id_bayaran"));
		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
		
		h.put("txtNoRujSuratEft", getParam("txtNoRujSuratEft"));
		h.put("txtNoEft", getParam("txtNoEft"));
		h.put("txtAmaun", Utils.RemoveSymbol(getParam("txtAmaun")));
		h.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));	
		h.put("txdTarikhSuratEFT", getParam("txdTarikhSuratEFT"));
		h.put("txtNoBaucer", getParam("txtNoBaucer"));
		h.put("txtPerihal", getParam("txtPerihal"));
		
		h.put("txtBilLewat", getParam("txtBilLewat"));
		h.put("txtDendaLewat", getParam("txtDendaLewat"));
		h.put("sorJenisAward", getParam("sorJenisAward"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.updatePBEFT(h);
  
	}//close updatePBEFT
	
	
	@SuppressWarnings("unchecked")
	private void updateMaklumatSuratAgensi(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_terimabayaran", getParam("id_terimabayaran"));
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txtNamaBank", getParam("txtNamaBank"));
		h.put("txdTarikhAmbil", getParam("txdTarikhAmbil"));
		h.put("txtTempatAmbil", getParam("txtTempatAmbil"));
		h.put("txdTarikhSedia", getParam("txdTarikhSedia"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8PampasanData.updateMaklumatSuratAgensi(h);
  
	}//close updateMaklumatSuratAgensi
	
	@SuppressWarnings("unchecked")
	private void updateDataBorangG(HttpSession session,String idImejBorangG , String totalAward) throws Exception{

		Hashtable h = new Hashtable();
		
		//id
		h.put("id_borangg", getParam("id_borangg"));
		h.put("idImejBorangG", getParam("idImejBorangG"));
		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("txtJumlahAward", Utils.RemoveSymbol(totalAward));
		h.put("txdTarikhBorangG", getParam("txdTarikhBorangG"));
		h.put("txdTarikhBorangH", getParam("txdTarikhBorangH"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
	
		FrmSek8PampasanData.updateDataBorangG(h);
  
	}//close updateDataBorangG
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void HMgetAndSetData(String idpermohonan,String idHakmilik,String mode) throws Exception{
    	
		Vector dataByHM = new Vector();
		dataByHM.clear();
	
		String id_tanah = "";
		String id_siasatan = "";
		
		double nilai_tanah = 0;
		//double bayar_fee = 0;
		double bayar_pecahpisah = 0;
		double bayar_penjejasan = 0;
		double naik_nilai_jpph = 0;
		double nilai_bangunan = 0;
		
		String no_kes = "";
		String tarikh_siasatan = "";
		
		String id_kategoritanah = "";
		String luas_ambil = "";
		
		model.setDataByHM(idHakmilik);
		dataByHM = model.getDataByHM();
		if(dataByHM.size()!=0){
			Hashtable db = (Hashtable)dataByHM.get(0);
			id_tanah = (String)db.get("id_tanah");
			id_siasatan = (String)db.get("id_siasatan");
			
			nilai_tanah = (Double)db.get("nilai_tanah");
			//bayar_fee = (Double)db.get("bayar_fee");
			bayar_pecahpisah = (Double)db.get("bayar_pecahpisah");
			bayar_penjejasan = (Double)db.get("bayar_penjejasan");
			naik_nilai_jpph = (Double)db.get("naik_nilai_jpph");
			nilai_bangunan = (Double)db.get("nilai_bangunan");
			
			no_kes = (String)db.get("no_kes");
			tarikh_siasatan = (String)db.get("tarikh_siasatan");
			
			id_kategoritanah = (String)db.get("id_kategoritanah");
			luas_ambil = (String)db.get("luas_ambil");
		}
		
		if(mode.equals("new")){
			context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetData()\""));	
		}else{
			context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetDataUpdate()\""));	
		}
		
		//dropdown
		context.put("selectUnitLuas",HTML.SelectLuas("unitLuas",null,"style=width:250px"));
		context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",null,"style=width:300px"));
		
		//set id
		context.put("id_tanah", id_tanah);
		context.put("id_siasatan", id_siasatan);
		
		//set data temp
		context.put("id_kategoritanah", id_kategoritanah);
		context.put("luas_ambil", luas_ambil);
		
		//set data
		context.put("txtNoKes", no_kes);
		context.put("txdTarikhBicara", tarikh_siasatan);
		//context.put("txtFeeAsal", bayar_fee);
		context.put("txtBangunanAsal", nilai_bangunan);
		context.put("txtPecahpisahAsal", bayar_pecahpisah);
		context.put("txtPenjejasanAsal", bayar_penjejasan);
		context.put("txtNaikAsal", naik_nilai_jpph);
		context.put("txtTanahAsal", nilai_tanah);
		
	}//close getAndSetData
	
	@SuppressWarnings("unused")
	private void dropdownNew(String idHakmilik) throws Exception{
    	
		//dropdown
		context.put("selectPB",HTML.SelectPBbyHakmilikWithIdAward(idHakmilik,"socPB",null,null,"style=width:300px onChange=\"PBgetData()\""));
		context.put("selectUnitLuas",HTML.SelectLuas("unitLuas",null,"style=width:250px"));
		context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",null,"style=width:300px"));
		
	}//close dropdownNew

	@SuppressWarnings("unused")
	private void resetValueAndId() throws Exception{
    	
		//id
		context.put("id_tanah", "");
		context.put("id_bangunan", "");
		context.put("id_pihakberkepentingan", "");
		context.put("id_hakmilikpb", "");
		
		//data
		context.put("txtNoPB", "");
		context.put("txtSyorAtas", "");
		context.put("txtSyorBawah", "");
		context.put("txtNoKes", "");
		context.put("txdTarikhBicara", "");
		context.put("txtLuasAmbil", "");
		context.put("socStatusPenerima", "");
		context.put("txtFeeAsal", "");
		context.put("txtFeeAward", "");
		context.put("txtTanahAsal", "");
		context.put("txtTanahAward", "");
		context.put("txtBangunanAsal", "");
		context.put("txtBangunanAward", "");
		context.put("txtPecahpisahAsal", "");
		context.put("txtPecahpisahAward", "");
		context.put("txtPenjejasanAsal", "");
		context.put("txtPenjejasanAward", "");
		context.put("txtNaikAsal", "");
		context.put("txtNaikAward", "");
		context.put("txtLainPampasan", "");
		context.put("txtJumlahPampasan", "");
		
	}//close resetValue

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
					//" 			AND ID_JAWATAN IN (26)  "+
					"			AND U.USER_ROLE = '(PPT)PenghantarNotis' "+
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
	
	// UPLOAD FILE
	private void uploadFiles(String idPermohonan ,String idBorangG,String idBorangh,String id_hakmilikpb,String idHakmilik , HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			
			//System.out.println("ID HAKMILIK DLM UPLOAD --- "+idHakmilik);
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
					if(item.getSize()<500000000L){
						if(item.getFieldName().equals("fileuploadG")){
							
							Long idImejBorangG = saveDataG(item , idBorangG, session);	
							saveKemaskiniBorangG(idBorangG, idImejBorangG,getParam("txdTarikhBorangG"),
									getParam("txdTarikhBorangH"), session);
							
						}else if(item.getFieldName().equals("fileuploadH")){
							
							Long idImejBorangH = saveDataH(item , idBorangG,  session);	
							saveKemaskiniBorangH( idBorangh, idBorangG, idImejBorangH, id_hakmilikpb ,getParam("txtNamaHantar"),
									getParam("txtHubungan"),getParam("txtMasaTampal"),getParam("socJenisWaktu"),getParam("txtTempatTampal"),
									getParam("socJenisSerah"),getParam("socStatusSerah"),getParam("txtNamaTerima"),getParam("txtNoKP"),
									getParam("txtCatatan"),getParam("socJenisNoKP"),getParam("txdTarikhSerah"), session);
						
						}
						else{
						Long idDokumen = saveDataA(item,idHakmilik,idPermohonan, session);
						}
					}
					
					else{
						this.context.put("limitExceed", true);	}
					
					
				}
			}
		}
	}
	
	// UPLOAD FILE
		private void uploadFilesNew(String idPermohonan ,String idBorangG,String idBorangh,String idHakmilik , HttpSession session) throws Exception {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart != false) {
				List items = upload.parseRequest(request);
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
						if(item.getSize()<500000000L){
							if(item.getFieldName().equals("fileuploadG")){
								
								 saveDataG(item ,idBorangG, session);	
//								saveKemaskiniBorangG(idBorangG, idImejBorangG,getParam("txdTarikhBorangG"),
//										getParam("txdTarikhBorangH"), session);
								
								
							}else if(item.getFieldName().equals("fileuploadH")){
								
								saveDataH(item , idBorangG,  session);	
//								saveKemaskiniBorangHNew( idBorangh, idBorangG, idImejBorangH, getParam("txtNamaHantar"),
//										getParam("txtHubungan"),getParam("txtMasaTampal"),getParam("socJenisWaktu"),getParam("txtTempatTampal"),
//										getParam("socJenisSerah"),getParam("socStatusSerah"),getParam("txtNamaTerima"),getParam("txtNoKP"),
//										getParam("txtCatatan"),getParam("socJenisNoKP"),getParam("txdTarikhSerah"), session);
							
							}
							
							Long idDokumen = saveDataA(item,idHakmilik,idPermohonan, session);
							
						}
						
						else{
							this.context.put("limitExceed", true);	}
						
						
					}
				}
			}
		}
	
	// UPLOAD FILE
		private long uploadFilesA(String idHakmilik,String idpermohonan,  HttpSession session) throws Exception {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			long idDokumen = 0L;
			ServletFileUpload upload = new ServletFileUpload(factory);
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart != false) {
				List items = upload.parseRequest(request);
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
						idDokumen = saveDataA(item,idHakmilik,idpermohonan,session);
						this.context.put("completed", true);
					}
				}
			}
			return idDokumen;
		}
		
	
	
	private void saveKemaskiniBorangG(String idBorangG, Long idImejBorangG,String txdTarikhBorangG , 
			String txdTarikhBorangH , HttpSession session) throws Exception {
	   
		Db db = null;
	    String sql = "";
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String TG = "to_date('" + txdTarikhBorangG + "','dd/MM/yyyy')";
		String TH = "to_date('" + txdTarikhBorangH + "','dd/MM/yyyy')";
	    
	    try{
    		db = new Db();
    		Statement stmt = db.getStatement();
    		
    		//update tblpptborangG
    		SQLRenderer rx = new SQLRenderer();	    		
    		rx.update("ID_BORANGG", idBorangG);
    		rx.add("ID_PPTIMEJBORANG",idImejBorangG);	    
    		rx.add("tarikh_borangg",rx.unquote(TG));
    		rx.add("tarikh_borangh",rx.unquote(TH));	    		
    		rx.add("tarikh_kemaskini",rx.unquote("sysdate"));
    		rx.add("id_kemaskini",userId);    		
    		sql = rx.getSQLUpdate("TBLPPTBORANGG");
    		stmt.executeUpdate(sql);
    		
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	}

	
	
	private void saveKemaskiniBorangH(String idBorangh,String idBorangG,Long idImejBorangH ,String id_hakmilikpb ,String txtNamaHantar,String txtHubungan, 
			String txtMasaTampal, String socJenisWaktu, String txtTempatTampal, String socJenisSerah,String socStatusSerah,
			String txtNamaTerima, String txtNoKP, String txtCatatan,String socJenisNoKP,String txdTarikhSerah , 
			 HttpSession session) throws Exception {
	   
		Db db = null;
	    String sql = "";
	    
	    try{
		      
    		db = new Db();
		    Statement stmt = db.getStatement();
			String userId = (String) session.getAttribute("_ekptg_user_id");
			//data tampal
		
	
		String TS = "to_date('" + txdTarikhSerah + "','dd/MM/yyyy')";
		
		SQLRenderer r = new SQLRenderer();
		r.update("id_borangh", idBorangh);	
		r.update("id_borangg", idBorangG);	
		r.add("id_hakmilikpb", id_hakmilikpb);
		r.add("nama_penghantar", txtNamaHantar);
		r.add("hubungan", txtHubungan);
		r.add("masa_tampal", txtMasaTampal);
		r.add("jenis_waktu", socJenisWaktu);
		r.add("tempat_tampal", txtTempatTampal);
		r.add("jenis_serahan", socJenisSerah);
		r.add("flag_serah", socStatusSerah);
		r.add("nama_penerima", txtNamaTerima);
		r.add("no_kp_penerima", txtNoKP);
		r.add("catatan", txtCatatan);
		r.add("id_jenisnopb", socJenisNoKP);	
		r.add("tarikh_hantar",r.unquote(TS));
		r.add("id_pptimejborang", idImejBorangH);
		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		r.add("id_kemaskini",userId);    		
		sql = r.getSQLUpdate("tblpptborangh");
		stmt.executeUpdate(sql);
	    		
		 }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	}
	
	private void saveKemaskiniBorangHNew(String idBorangh,String idBorangG,Long idImejBorangH ,String txtNamaHantar,String txtHubungan, 
			String txtMasaTampal, String socJenisWaktu, String txtTempatTampal, String socJenisSerah,String socStatusSerah,
			String txtNamaTerima, String txtNoKP, String txtCatatan,String socJenisNoKP,String txdTarikhSerah , 
			 HttpSession session) throws Exception {
	   
		Db db = null;
	    String sql = "";
	    
	    try{
		      
    		db = new Db();
		    Statement stmt = db.getStatement();
			String userId = (String) session.getAttribute("_ekptg_user_id");
			//data tampal
		
	
		String TS = "to_date('" + txdTarikhSerah + "','dd/MM/yyyy')";
		
		SQLRenderer r = new SQLRenderer();
		r.update("id_borangh", idBorangh);	
		r.update("id_borangg", idBorangG);	
//		r.add("id_hakmilikpb", id_hakmilikpb);
		r.add("nama_penghantar", txtNamaHantar);
		r.add("hubungan", txtHubungan);
		r.add("masa_tampal", txtMasaTampal);
		r.add("jenis_waktu", socJenisWaktu);
		r.add("tempat_tampal", txtTempatTampal);
		r.add("jenis_serahan", socJenisSerah);
		r.add("flag_serah", socStatusSerah);
		r.add("nama_penerima", txtNamaTerima);
		r.add("no_kp_penerima", txtNoKP);
		r.add("catatan", txtCatatan);
		r.add("id_jenisnopb", socJenisNoKP);	
		r.add("tarikh_hantar",r.unquote(TS));
		r.add("id_pptimejborang", idImejBorangH);
		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		r.add("id_kemaskini",userId);    		
		sql = r.getSQLUpdate("tblpptborangh");
		stmt.executeUpdate(sql);
	    		
		 }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	}
	

	public static long saveDataG(FileItem item,String idBorangG, HttpSession session) throws Exception {

		Db db = null;
		long idImejBorangG = 0L;
		String userId = (String) session.getAttribute("_ekptg_user_id");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		try {
			db = new Db();			

			// TBLPHPDOKUMEN
		    idImejBorangG = DB.getNextID("TBLPPTIMEJBORANG_SEQ");
			long idJenisDokumen = 1163 ;
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			
			PreparedStatement checkG = con.prepareStatement("SELECT A.ID_JENISDOKUMEN FROM TBLPPTIMEJBORANG A, TBLPPTBORANGG B " +
					"WHERE A.ID_PPTIMEJBORANG = B.ID_PPTIMEJBORANG " +
					"AND B.ID_BORANGG = '"+idBorangG+"' " +
					"AND A.ID_JENISDOKUMEN = '1163'");

			ResultSet rs = checkG.executeQuery();
			
			
			if(rs.equals(""))
			{
	
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPPTIMEJBORANG "
							+ "(ID_PPTIMEJBORANG,ID_JENISDOKUMEN,IMEJ,ID_MASUK,JENIS_MIME,TARIKH_MASUK) "
							+ "VALUES(?,?,?,?,?,SYSDATE)");
			ps.setLong(1, idImejBorangG);
			ps.setLong(2, idJenisDokumen);
			ps.setBinaryStream(3, item.getInputStream(), (int) item.getSize());
			ps.setString(4, userId);
			ps.setString(5, item.getContentType());
		
			ps.executeUpdate();
			
			PreparedStatement ps2 = con
					.prepareStatement("UPDATE TBLPPTBORANGG SET ID_PPTIMEJBORANG = '"+idImejBorangG+"' WHERE ID_BORANGG = '"+idBorangG+"'");
		
			ps2.executeUpdate();
			}
			
			else {
				PreparedStatement ps5 = con
						.prepareStatement(" DELETE TBLPPTIMEJBORANG WHERE ID_JENISDOKUMEN ='1163'  ");
			
				ps5.executeUpdate();
				
				
				PreparedStatement ps3 = con
						.prepareStatement("INSERT INTO TBLPPTIMEJBORANG "
								+ "(ID_PPTIMEJBORANG,ID_JENISDOKUMEN,IMEJ,ID_MASUK,JENIS_MIME,TARIKH_MASUK) "
								+ "VALUES(?,?,?,?,?,SYSDATE)");
				ps3.setLong(1, idImejBorangG);
				ps3.setLong(2, idJenisDokumen);
				ps3.setBinaryStream(3, item.getInputStream(), (int) item.getSize());
				ps3.setString(4, userId);
				ps3.setString(5, item.getContentType());
			
				ps3.executeUpdate();
				
				PreparedStatement ps4 = con
						.prepareStatement("UPDATE TBLPPTBORANGG SET ID_PPTIMEJBORANG = '"+idImejBorangG+"' WHERE ID_BORANGG = '"+idBorangG+"'");
			
				ps4.executeUpdate();
			}
			
			con.commit();
			
		} finally {
			if (db != null)
				db.close();
		}
		return idImejBorangG;

	}
		
		public static long saveDataH(FileItem item,String idBorangG, HttpSession session) throws Exception {

			Db db = null;
			long idImejBorangH = 0L;
			String userId = (String) session.getAttribute("_ekptg_user_id");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
			try {
				db = new Db();			

				// TBLPHPDOKUMEN
			    idImejBorangH = DB.getNextID("TBLPPTIMEJBORANG_SEQ");
				long idJenisDokumen = 1164 ;
				Connection con = db.getConnection();
				con.setAutoCommit(false);
			
				PreparedStatement checkH = con.prepareStatement("SELECT A.ID_JENISDOKUMEN FROM TBLPPTIMEJBORANG A, TBLPPTBORANGG B " +
						"WHERE A.ID_PPTIMEJBORANG = B.ID_PPTIMEJBORANG " +
						"AND B.ID_BORANGG = '"+idBorangG+"' " +
						"AND A.ID_JENISDOKUMEN = '1164'");

				ResultSet rs = checkH.executeQuery();
				
				if(rs.equals("")){
				
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO TBLPPTIMEJBORANG "
								+ "(ID_PPTIMEJBORANG,ID_JENISDOKUMEN,IMEJ,ID_MASUK,JENIS_MIME,TARIKH_MASUK) "
								+ "VALUES(?,?,?,?,?,SYSDATE)");
				ps.setLong(1, idImejBorangH);
				ps.setLong(2, idJenisDokumen);
				ps.setBinaryStream(3, item.getInputStream(), (int) item.getSize());
				ps.setString(4, userId);
				ps.setString(5, item.getContentType());
		
				ps.executeUpdate();
				
				PreparedStatement ps2 = con
						.prepareStatement("UPDATE TBLPPTBORANGH SET ID_PPTIMEJBORANG = '"+idImejBorangH+"' WHERE ID_BORANGG = '"+idBorangG+"'");
			
				//String xx = "UPDATE TBLPPTBORANGH SET ID_PPTIMEJBORANG = '"+idImejBorangH+"' WHERE ID_BORANGG = '"+idBorangG+"'";
				ps2.executeUpdate();
				}
				else {
					
					PreparedStatement ps5H = con
							.prepareStatement(" DELETE TBLPPTIMEJBORANG WHERE ID_JENISDOKUMEN ='1164'  ");
				
					ps5H.executeUpdate();
					
					PreparedStatement ps3H = con
							.prepareStatement("INSERT INTO TBLPPTIMEJBORANG "
									+ "(ID_PPTIMEJBORANG,ID_JENISDOKUMEN,IMEJ,ID_MASUK,JENIS_MIME,TARIKH_MASUK) "
									+ "VALUES(?,?,?,?,?,SYSDATE)");
					ps3H.setLong(1, idImejBorangH);
					ps3H.setLong(2, idJenisDokumen);
					ps3H.setBinaryStream(3, item.getInputStream(), (int) item.getSize());
					ps3H.setString(4, userId);
					ps3H.setString(5, item.getContentType());
			
					ps3H.executeUpdate();
					
					PreparedStatement ps2H = con
							.prepareStatement("UPDATE TBLPPTBORANGH SET ID_PPTIMEJBORANG = '"+idImejBorangH+"' WHERE ID_BORANGG = '"+idBorangG+"'");
					
					ps2H.executeUpdate();
				}
				

				con.commit();
				
			} finally {
				if (db != null)
					db.close();
			}
			return idImejBorangH;

		}
		
		public static long saveDataA(FileItem item, String idHakmilik ,String idpermohonan ,HttpSession session) throws Exception {

			Db db = null;
			long idDokumen = 0L;
			String userId = (String) session.getAttribute("_ekptg_user_id");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
			try {
				db = new Db();			

				// TBLPHPDOKUMEN
				long idJenisDokumen = 1525 ;
				idDokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
				Connection con = db.getConnection();
				con.setAutoCommit(false);
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO TBLPPTDOKUMEN "
								+ "(ID_DOKUMEN,ID_HAKMILIK,CONTENT,ID_MASUK,NAMA_FAIL,JENIS_MIME,TARIKH_MASUK ,ID_PERMOHONAN, ID_JENISDOKUMEN, JENIS_DOKUMEN) "
								+ "VALUES(?,?,?,?,?,?,SYSDATE,?,?,?)");
				ps.setLong(1, idDokumen);
				ps.setString(2, idHakmilik);
				ps.setBinaryStream(3, item.getInputStream(), (int) item.getSize());
				ps.setString(4, userId);
				ps.setString(5, item.getName());
				ps.setString(6, item.getContentType());
				ps.setString(7, idpermohonan);
				ps.setLong(8, idJenisDokumen);
				ps.setString(9, "bayaran_pampasan");
				
				ps.executeUpdate();
				
				con.commit();
				
			} finally {
				if (db != null)
					db.close();
			}
			return idDokumen;

		}
		
		//penambahan yati
		@SuppressWarnings({ "unchecked", "static-access" })
		private String emelListKJP(String id_permohonan) throws Exception {

			Vector emelKJP = new Vector();
			emelKJP.clear();

			// GET NAMA PENGARAH DAN ID PENGARAH
			String id_kementerianp = "";
			String id_permohonanp = "";
			String emel = "";
			emelKJP = model.setListEmelKJP(id_permohonan);
			if (emelKJP.size() != 0) {
				Hashtable npp = (Hashtable) emelKJP.get(0);
				id_kementerianp = (String) npp.get("id_kementerian");
				id_permohonanp = (String) npp.get("id_permohonan");		
				emel = (String) npp.get("emel");
			}
			context.put("emel", emel);

			return emel;

		}// close pengarah
		
		 public void hantarEmel(String content,String subjek,String nofail,
				 String nama_projek, String tarikh_permohonan, String emel, String nama_kementerian, String no_lot) throws Exception {

				Vector checkEmailKJP = new Vector();
				checkEmailKJP.clear();		
			
				EmailProperty pro = EmailProperty.getInstance();
				//String EMAIL_HOST = pro.getHost_GM();
				EmailSender email = EmailSender.getInstance();
			
			//myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
			//myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
			email.FROM = pro.getFrom();
			email.SUBJECT = subjek;
			email.MESSAGE = "<html><table><tr><td>Makluman permohonan pembayaran bagi</td></tr>" +
					"<tr><td>No.Fail</td><td>:</td><td>"+nofail+"</td></tr>" +
					"<tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
					"<tr><td>No.Lot</td><td>:</td><td>"+no_lot+"</td></tr>" +
					"<tr><td>Tarikh Permohonan</td><td>:</td><td>"+tarikh_permohonan+"</td></tr>" +
					"<tr><td>Nama Kementerian</td><td>:</td><td>"+nama_kementerian+"</td></tr>" +
					"<tr><td>&nbsp;</td><td>&nbsp;</td><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
					"</table></html>" ;
			email.RECIEPIENT = emel;
			
			myLogger.info(" ---------- email :"+email);	
			//email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";	
			email.TO_CC = new String[1];		
			email.TO_CC[0] = "testingetapp@gmail.com";
			email.sendEmail();
			
		 } //close hantar emel
		 
		 
		 ///get_dBorangG
		 private String getIdBorangG(String id_siasatan) throws Exception {

				Vector borangG = new Vector();
				borangG.clear();

				// GET NAMA PENGARAH DAN ID PENGARAH
				String id_kementerianp = "";
				String id_permohonanp = "";
				String id_BorangG = "";
				
				borangG = model.getID_BorangG(id_siasatan);
				if (borangG.size() != 0) {
					Hashtable npp = (Hashtable) borangG.get(0);
					id_BorangG = (String) npp.get("id_BorangG");
					
				}
				context.put("id_BorangG", id_BorangG);

				return id_BorangG;

			}
	
}//close class
