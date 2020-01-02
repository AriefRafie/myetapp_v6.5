package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8BorangKData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmSek8BorangK extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek8BorangK.class);
	
	
	
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
    	String radio = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector dataBorangK = new Vector();
    	Vector dataBorangL = new Vector();
    	Vector dataMaklumatTanah = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	
    	dataSuburusanHakmilik.clear();
    	getIdSuburusanstatusfail.clear();
    	dataMaklumatTanah.clear();
    	dataBorangL.clear();
		dataBorangK.clear();
    	listMaklumatTanah.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSek8BorangKSenarai.jsp";
    	String listHMscreen = "app/ppt/frmSek8BorangKListHM.jsp";
    	String screenBorangK = "app/ppt/frmSek8BorangK.jsp";
    	String screenEndosanBorangK = "app/ppt/frmSek8BorangKEndosan.jsp";
    	String screenBorangL = "app/ppt/frmSek8BorangL.jsp";
    	String screenMaklumatBorangL = "app/ppt/frmSek8MaklumatBorangL.jsp";
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
    	context.put("hash_maklumatEndorsan_from_etanah","");
    	
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
    	
    	//tabbed
    	String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
    	
		//user login id
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
    	/*
		modelUPT.setGetUserId(id_user);
	    Vector listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    */
	    
	    context.put("userIdNeg", userIdNeg);
	   
	    
	    
		//header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
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
	    Vector dataNamaPengarah = modelUPT.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = np.get("nama_pengarah").toString();
	    }	
    	}
	    context.put("nama_pengarah",nama_pengarah);
		
		//paging
		/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "18");
    	}
    	*/
    	context.put("paging", "18");
    	
    	
    	//paging borang K dan L
    	String flagPagingBorangK = getParam("flagPagingBorangK");
    	if(flagPagingBorangK!=""){
    		context.put("flagPagingBorangK", getParam("flagPagingBorangK"));
    	}else{
    		context.put("flagPagingBorangK", "1");
    	}
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("showAlertPaging","no");
		context.put("showAlertPagingSegera","no");
		
		//id
		String idHakmilik = getParam("id_hakmilik");
		String id_borangk = getParam("id_borangk");
		
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
		
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		}
		
		//data hakmilik
			
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		//data & list maklumat tanah
		if(!idpermohonan.equals(""))
		{
		//listMaklumatTanah(idpermohonan,noLOT,flagSegera);
		}
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewListHM".equals(submit)){
    
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//data & list maklumat tanah
    		listMaklumatTanah(idpermohonan,noLOT,flagSegera);
     		
        	//screen
    		vm = listHMscreen;
    		
    	}//close viewListHM
    	
    	else 
    	if("maklumatBorangK".equals(submit)){
    		
    		Db db_K = null;
			try {
				db_K = new Db();
    		hashMaklumatEndorsan_fromEtanah(id_fail, idpermohonan,	idHakmilik, "BorangK", db_K,1);			
			} finally {
				if (db_K != null)
					db_K.close();
			}
    			
    		//validation new / view
    		model.setDataBorangK(idHakmilik);
     		dataBorangK = model.getDataBorangK();
     		
     		//get tarikh borang k
     		getTarikhBorangK(idHakmilik);
     		
     		//data
     		context.put("dataBorangK", dataBorangK);
    		
     		String id_hakmilikborangk = "";
     		if(dataBorangK.size()!=0){
     			Hashtable dbk = (Hashtable)dataBorangK.get(0);
     			id_hakmilikborangk = (String)dbk.get("id_hakmilikborangk");
     			id_borangk = (String)dbk.get("id_borangk");
     		}
     		
     		//list endosan borang k
    		listEndosanBorangK(id_borangk);
     		
     		//id
     		context.put("id_hakmilikborangk", id_hakmilikborangk);
     		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
     		
     		//NEW
     		if(id_hakmilikborangk==""){
     			
     			//form validation
     			context.put("mode","new");
     			context.put("isEdit","no");
     			
     			if("simpanBorangK".equals(submit2)){
     				
     				if (doPost.equals("true")) {
            			//simpan borang k 
     					
     					

     					Db db = null;
     					String NO_HAKMILIK_temp = "";
     					try {
     					db = new Db();
     					Statement stmt = db.getStatement();
     					String sql = " SELECT NO_HAKMILIK FROM TBLPPTHAKMILIK " +
     							"" +
     							" WHERE ID_HAKMILIK = '"+getParam("id_hakmilik")+"'";			
     					ResultSet rs = stmt.executeQuery(sql);	
     					myLogger.info("SQL  :"+sql);
     					while (rs.next()){				
     						NO_HAKMILIK_temp = rs.getString("NO_HAKMILIK");				
     				    }
     				    AuditTrail at = new AuditTrail();
     					at.logActivity("","1",null,session,"INS","BORANG K [NO. HAKMILIK : "+NO_HAKMILIK_temp+"] INSERT");
     						
     					} finally {
     						if (db != null)
     							db.close();
     					}	
     					
     					
     					
     					simpanBorangK(session,id_status); 
     					/*
     					myLogger.info(" STATUS K : "+id_status);
     					if(id_status.equals("72") || id_status.equals("59") || id_status.equals("74") || id_status.equals("187") || id_status.equals("204")){
     						updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
     					}
     					updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik);
     					*/
     					
     					if(modelUPT.cekStatusFailDahWujud(idpermohonan,"76","52")==false)
	            		{
	        			modelUPT.updateStatus(idpermohonan,id_user, "76");
	        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
	            		}
	        			
	        			//SELAIN DARI STATUS PERMOHONAN LAPORAN TANAH TERPERINCI
	        			//if(!id_suburusanstatus.equals("1486"))
	        			//{//id_status 76
	        				updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik);
	        			//}
     					
     					
            		}
     				
     				//form validation
         			context.put("mode","view");
         			context.put("isEdit","no");
         			
         			//data borang k by hakmilik
            		dataBorangKByHM(idHakmilik);
     				
            		header.setDataHeader(idpermohonan);
            		dataHeader = header.getDataHeader();
            		context.put("dataHeader", dataHeader);
            		if(dataHeader.size()!=0){
            			Hashtable dh = (Hashtable) dataHeader.get(0);
            			id_status = (String)dh.get("id_status");
            			flagSegera = dh.get("flag_segera").toString();
            		}
            		
            		if(id_status.equals("76")){
            			if(flagSegera.equals("1")){
            		//		JOptionPane.showMessageDialog (null, "Sila Klik 'Paging' No.15/19 Untuk Meneruskan Proses Seksyen 8", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
            				context.put("showAlertPagingSegera","yes");
            				context.put("showAlertPaging","no");
            			}else{
         //   				JOptionPane.showMessageDialog (null, "Sila Pastikan Kesemua Hakmilik Telah Diisi Maklumat Borang K dan Klik 'Paging' No.19 Untuk Maklumat Borang L", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
            				context.put("showAlertPaging","yes");
           				context.put("showAlertPagingSegera","no");
            			}	
            		}else{
            			context.put("showAlertPaging","no");
            			context.put("showAlertPagingSegera","no");
            		}
            		
     			}//close simpanBorangK
     			
     		//VIEW	
     		}else{
     			
     			//form validation
     			context.put("mode","view");
     			context.put("isEdit","no");
     			  			
     			if("kemaskiniBorangK".equals(submit2)){
     				
     				//form validation
         			context.put("mode","view");
         			context.put("isEdit","yes");
     				
         			String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
                	if("updateBorangK".equals(submit3)){
                		
                		
                		Db db = null;
     					String NO_HAKMILIK_temp = "";
     					try {
     					db = new Db();
     					Statement stmt = db.getStatement();
     					String sql = " SELECT NO_HAKMILIK FROM TBLPPTHAKMILIK " +
     							"" +
     							" WHERE ID_HAKMILIK = '"+getParam("id_hakmilik")+"'";			
     					ResultSet rs = stmt.executeQuery(sql);	
     					myLogger.info("SQL  :"+sql);
     					while (rs.next()){				
     						NO_HAKMILIK_temp = rs.getString("NO_HAKMILIK");				
     				    }
     				    AuditTrail at = new AuditTrail();
     					at.logActivity("","1",null,session,"UPD","BORANG K [NO. HAKMILIK : "+NO_HAKMILIK_temp+"] KEMASKINI");
     						
     					} finally {
     						if (db != null)
     							db.close();
     					}	
     					
     					
                		//update
                		updateBorangK(session); 
                		
                		//form validation
             			context.put("mode","view");
             			context.put("isEdit","no");
                		
             			//data borang k by hakmilik
                		dataBorangKByHM(idHakmilik);
                		
                	}//close updateBorangK
         			
     			}//close kemaskiniBorangK
     			
     		}//close validation
     		
    		
    		//screen
    		vm = screenBorangK;
    		
    	}//close maklumatBorangK
    	
    	else 
    	if("tambahEndosanBorangK".equals(submit)){
        		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//list endosan borang k
    		//listEndosanBorangK(id_borangk);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("simpanEndosanBorangK".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			//simpan borang k 
 					simpanEndosanBorangK(session); 
        		}
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
        		
        		//data endosan
        		//dataEndosanBorangK(id_endosanborangk);
        		dataEndosanBorangK(id_borangk);
        		
        		//list endosan borang k
        		//listEndosanBorangK(id_borangk);
        		
        	}//close simpanEndosanBorangK
    		
        	//screen
    		vm = screenEndosanBorangK;
    		    
        }//close endosanBorangK
    	
    	else 
    	if("viewEndosanBorangK".equals(submit)){
    			
    		String id_endosanborangk = getParam("id_endosanborangk");
    		context.put("id_endosanborangk", id_endosanborangk);
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//list endosan borang k
    		//listEndosanBorangK(id_borangk);
    		
    		//data endosan
    		//dataEndosanBorangK(id_endosanborangk);
    		dataEndosanBorangK(id_borangk);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniEndosan".equals(submit2)){
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("updateEndosan".equals(submit3)){
            		
            		updateEndosan(session);
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//list endosan borang k
            		//listEndosanBorangK(id_borangk);
            		
            		//data endosan
            		//dataEndosanBorangK(id_endosanborangk);
            		dataEndosanBorangK(id_borangk);
            		
            	}//close updateEndosan
        		
        	}//close kemaskiniEndosan
    		
    		//screen
    		vm = screenEndosanBorangK;
    		
    	}//close viewEndosanBorangK
    	
    	else 
    	if("hapusEndosan".equals(submit)){
    			
    		hapusEndosan();
    		
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//list endosan borang k
    		//listEndosanBorangK(id_borangk);
    		
    		//screen
    		vm = screenEndosanBorangK;
    		
    	}//close hapusEndosan
    	
    	
    	else 
    	if("penjanaanBorangL".equals(submit)){
    		
    		//carian lot
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());

    		//data & list maklumat tanah
    		listMaklumatTanah(idpermohonan,noLOT,flagSegera);
    		
    		//data borang L
    		model.setDataBorangL(idpermohonan);
     		dataBorangL = model.getDataBorangL();
     		context.put("dataBorangL",dataBorangL);
     		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);

     		//NEW
     		if(dataBorangL.size()==0){
     			
     			//form validation by tblpptborangl
        		context.put("mode","new");
        		context.put("isEdit","no");
        		
        		if("simpanBorangL".equals(submit2)){
        			
        			if (doPost.equals("true")) {
            			//simpan borang L
        				simpanBorangL(session,idpermohonan);
            		}
        			
        			//form validation by tblpptborangl
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data borang L
            		DataBorangL(idpermohonan);
        			
            		//data & list maklumat tanah
            		listMaklumatTanah(idpermohonan,noLOT,flagSegera);
            		
            		//alert
            		context.put("showAlertPaging","yes");
            	//	JOptionPane.showMessageDialog (null, "Sila Klik 'Paging' No.20 Untuk Endorsan Borang K", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
            		
        		}//close simpanBorangL
        		
        	//VIEW	
     		}else{
     			
     			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
        		
        		if("kemaskiniBorangL".equals(submit2)){
        			
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","yes");
        			
            		String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
                	if("updateBorangL".equals(submit3)){
            			
                		//update borang L
        				updateBorangL(session,idpermohonan);
                		
                		//form validation by tblpptborangl
                		context.put("mode","view");
                		context.put("isEdit","no");
                		
                		//data borang L
                		DataBorangL(idpermohonan);
                		
                		//data & list maklumat tanah
                		listMaklumatTanah(idpermohonan,noLOT,flagSegera);
                		
                	}//close updateBorangL
            		
        		}//close kemaskiniBorangL
        		
     		}//close NEW / VIEW
     		
    		//screen
    		vm = screenBorangL;
    		
    	}//close penjanaanBorangL
    	
    	else 
    	if("viewPenerimaanHakmilik".equals(submit)){
    		
    		//carian lot
    		noLOT = getParam("carianNoLot2");
    		context.put("carianNoLot2", noLOT.trim());
    		
    		//carian by radio button
    		radio = getParam("sorPertanyaan");
    		context.put("radio", radio);
    		
    		//data & list maklumat tanah
    		listPenerimaanHakmilik(idpermohonan,noLOT,radio);
    		
    		//screen
    		vm = screenBorangL;
    		
    	}//close viewPenerimaanHakmilik
    	
    	else 
    	if("MaklumatPenerimaan".equals(submit)){
    		
    		//carian lot
    		noLOT = getParam("carianNoLot2");
    		context.put("carianNoLot2", noLOT.trim());
    		
    		//list maklumat tanah
    		listPenerimaanHakmilik(idpermohonan,noLOT,radio);
    		
    		//data maklumat tanah
    		dataMaklumatTanah(idHakmilik);
    		
    		//data tanah untuk validation new / view
    		String status_borangl = "";
    		modelUPT.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = modelUPT.getMaklumatTanah();
    		if(dataMaklumatTanah.size()!=0){
    			Hashtable dmt = (Hashtable)dataMaklumatTanah.get(0);
    			status_borangl = (String)dmt.get("status_borangl");
    		}
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
    		
    		//NEW
    		if(status_borangl==""){
    			
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
    			
        		if("updateHakmilik".equals(submit2)){
        			
        			updateHakmilik(session,idHakmilik);
        			
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//list maklumat tanah
            		listPenerimaanHakmilik(idpermohonan,noLOT,radio);
            		
            		//data maklumat tanah
            		dataMaklumatTanah(idHakmilik);
        			
        		}//updateHakmilik
        		
    		//VIEW	
    		}else{
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
    			
        		if("kemaskiniHakmilik".equals(submit2)){
        			
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","yes");
            		
            		String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
                	if("updateHakmilik".equals(submit3)){
                		
                		updateHakmilik(session,idHakmilik);
            			
            			//form validation
                		context.put("mode","view");
                		context.put("isEdit","no");
                		
                		//list maklumat tanah
                		listPenerimaanHakmilik(idpermohonan,noLOT,radio);
                		
                		//data maklumat tanah
                		dataMaklumatTanah(idHakmilik);
                		
                	}//close updateHakmilik
        			
        		}//close kemaskiniHakmilik
        		
    		}//close new / view
    		
    		//screen
    		vm = screenMaklumatBorangL;
    		
    	}//close MaklumatPenerimaan
    	
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
	    	context.put("id_hakmilik", idHakmilik);
	    	context.put("id_borangk", id_borangk);
	    	context.put("id_fail", id_fail);
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		myLogger.info(" VM : "+vm);
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
	
	
	
	
	//open get data from table temp etanah
	
	private void hashMaklumatEndorsan_fromEtanah(String id_fail,
			String id_permohonan, String id_hakmilik_etapp, String jenis_skrin,
			Db db,Integer turutan) throws Exception {
		String no_fail_jkptg = "";
		String id_hakmilik_etanah = "";
		Hashtable hash_maklumatprojek = null;
		Hashtable hash_maklumathakmilik = null;
		Hashtable hash_maklumatEndorsan = null;
		hash_maklumatprojek = getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? ""
				: hash_maklumatprojek.get("NO_FAIL").toString());
		
			hash_maklumathakmilik = getMaklumatHakmilik(id_fail,
					id_hakmilik_etapp, db);
			
			if(hash_maklumathakmilik.size()>0)
			{
			
			id_hakmilik_etanah = (hash_maklumathakmilik.get(
					"ID_HAKMILIK_ETANAH").toString() == null ? ""
					: hash_maklumathakmilik.get("ID_HAKMILIK_ETANAH")
							.toString());
			hash_maklumatEndorsan = getMaklumatEndorsanBK_fromEtanah(
					jenis_skrin, no_fail_jkptg, id_hakmilik_etanah, db,turutan);
			}
		context.put("hash_maklumatEndorsan_from_etanah", hash_maklumatEndorsan);
	}
	
	public static Hashtable getMaklumatProjek(String id_fail, Db db)
			throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT P.TUJUAN_BI AS TUJUAN_DALAM_ENGLISH, TUJUAN,NO_FAIL AS NO_FAIL_JKPTG,N.KOD_NEGERI AS KOD_NEGERI_PENGAMBILAN,N.NAMA_NEGERI AS NAMA_NEGERI_PENGAMBILAN, "+
					" D.KOD_DAERAH AS KOD_DAERAH_PENGAMBILAN,D.NAMA_DAERAH AS NAMA_DAERAH_PENGAMBILAN,U.NAMA_SUBURUSAN AS JENIS_PENGAMBILAN,(CASE WHEN  P.FLAG_JENISPERMOHONAN = '1' THEN 'JAJARAN' "+
					" WHEN  P.FLAG_JENISPERMOHONAN = '2' THEN 'TAPAK' ELSE '' END) AS JENIS_PROJEK_PENGAMBILAN, P.NO_RUJUKAN_SURAT AS NO_RUJUKAN_SURAT_KJP,TO_CHAR(P.TARIKH_SURAT,'DD/MM/YYYY') AS TARIKH_SURAT_KJP, "+
					" P.NO_RUJUKAN_PTG, P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_UPT,K.NAMA_KEMENTERIAN, K.ID_KEMENTERIAN AS ID_KEMENTERIAN_MYETAPP, A.NAMA_AGENSI, A.ID_AGENSI AS ID_AGENSI_MYETAPP, "+
					" TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,P.FLAG_SEGERA AS FLAG_PERMOHONAN_SEGERA,  "+
					" (CASE WHEN P.FLAG_SEGERA = '1' THEN 'PENGAMBILAN SEGERA KESELURUHAN LOT' WHEN P.FLAG_SEGERA = '2' THEN '' WHEN P.FLAG_SEGERA = '3' THEN 'PENGAMBILAN SEGERA SEBAHAGIAN LOT' ELSE '' END) AS JENIS_PENGMABILAN_SEGERA, "+
					" F.NO_FAIL,P.TUJUAN AS NAMA_PROJEK,N.ID_NEGERI,N.NAMA_NEGERI,F.ID_FAIL,A.KOD_AGENSI, K.KOD_KEMENTERIAN "+
					" FROM  TBLPFDFAIL F, TBLRUJDAERAH D,TBLRUJKEMENTERIAN K, TBLRUJSTATUS S,  TBLPPTPERMOHONAN P, TBLRUJNEGERI N, TBLRUJAGENSI A,TBLRUJSUBURUSAN U "+
					" WHERE F.ID_FAIL(+) = P.ID_FAIL  AND K.ID_KEMENTERIAN(+) = F.ID_KEMENTERIAN  AND P.ID_AGENSI = A.ID_AGENSI(+)  "+
					" AND N.ID_NEGERI(+) = F.ID_NEGERI  AND S.ID_STATUS(+) = P.ID_STATUS  AND D.ID_DAERAH(+) = P.ID_DAERAH  AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "+
					" AND F.ID_FAIL = '" + id_fail + "' ";
			

			myLogger.info("##################### PAPAR MAKLUMAT PROJEK:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("ID_FAIL", rs.getString("ID_FAIL"));
				if (rs.getString("NO_FAIL") == null) {h.put("NO_FAIL", "");} else {	h.put("NO_FAIL", rs.getString("NO_FAIL").toUpperCase());}
				if (rs.getString("TARIKH_PERMOHONAN") == null) {h.put("TARIKH_PERMOHONAN", "");} else {h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN").toUpperCase());}
				if (rs.getString("NAMA_PROJEK") == null) {h.put("NAMA_PROJEK", "");} else {h.put("NAMA_PROJEK", rs.getString("NAMA_PROJEK").toUpperCase());}
				if (rs.getString("NAMA_KEMENTERIAN") == null) {h.put("NAMA_KEMENTERIAN", "");} else {h.put("NAMA_KEMENTERIAN", rs.getString("NAMA_KEMENTERIAN").toUpperCase());}
				if (rs.getString("ID_NEGERI") == null) {h.put("ID_NEGERI", "");} else {h.put("ID_NEGERI", rs.getString("ID_NEGERI").toUpperCase());}
				if (rs.getString("NAMA_NEGERI") == null) {h.put("NAMA_NEGERI", "");} else {h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI").toUpperCase());}
				
				if (rs.getString("TUJUAN_DALAM_ENGLISH") == null) {h.put("TUJUAN_DALAM_ENGLISH", "");} else {h.put("TUJUAN_DALAM_ENGLISH", rs.getString("TUJUAN_DALAM_ENGLISH"));}
				if (rs.getString("TUJUAN") == null) {h.put("TUJUAN", "");} else {h.put("TUJUAN", rs.getString("TUJUAN"));}
				if (rs.getString("NO_FAIL_JKPTG") == null) {h.put("NO_FAIL_JKPTG", "");} else {h.put("NO_FAIL_JKPTG", rs.getString("NO_FAIL_JKPTG"));}
				if (rs.getString("KOD_NEGERI_PENGAMBILAN") == null) {h.put("KOD_NEGERI_PENGAMBILAN", "");} else {h.put("KOD_NEGERI_PENGAMBILAN", rs.getString("KOD_NEGERI_PENGAMBILAN"));}
				if (rs.getString("NAMA_NEGERI_PENGAMBILAN") == null) {h.put("NAMA_NEGERI_PENGAMBILAN", "");} else {h.put("NAMA_NEGERI_PENGAMBILAN", rs.getString("NAMA_NEGERI_PENGAMBILAN"));}
				if (rs.getString("KOD_DAERAH_PENGAMBILAN") == null) {h.put("KOD_DAERAH_PENGAMBILAN", "");} else {h.put("KOD_DAERAH_PENGAMBILAN", rs.getString("KOD_DAERAH_PENGAMBILAN"));}
				if (rs.getString("NAMA_DAERAH_PENGAMBILAN") == null) {h.put("NAMA_DAERAH_PENGAMBILAN", "");} else {h.put("NAMA_DAERAH_PENGAMBILAN", rs.getString("NAMA_DAERAH_PENGAMBILAN"));}
				if (rs.getString("JENIS_PENGAMBILAN") == null) {h.put("JENIS_PENGAMBILAN", "");} else {h.put("JENIS_PENGAMBILAN", rs.getString("JENIS_PENGAMBILAN"));}
				if (rs.getString("JENIS_PROJEK_PENGAMBILAN") == null) {h.put("JENIS_PROJEK_PENGAMBILAN", "");} else {h.put("JENIS_PROJEK_PENGAMBILAN", rs.getString("JENIS_PROJEK_PENGAMBILAN"));}
				if (rs.getString("NO_RUJUKAN_SURAT_KJP") == null) {h.put("NO_RUJUKAN_SURAT_KJP", "");} else {h.put("NO_RUJUKAN_SURAT_KJP", rs.getString("NO_RUJUKAN_SURAT_KJP"));}
				if (rs.getString("TARIKH_SURAT_KJP") == null) {h.put("TARIKH_SURAT_KJP", "");} else {h.put("TARIKH_SURAT_KJP", rs.getString("TARIKH_SURAT_KJP"));}
				if (rs.getString("NO_RUJUKAN_PTG") == null) {h.put("NO_RUJUKAN_PTG", "");} else {h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG"));}
				if (rs.getString("NO_RUJUKAN_PTD") == null) {h.put("NO_RUJUKAN_PTD", "");} else {h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD"));}
				if (rs.getString("ID_KEMENTERIAN_MYETAPP") == null) {h.put("ID_KEMENTERIAN_MYETAPP", "");} else {h.put("ID_KEMENTERIAN_MYETAPP", rs.getString("ID_KEMENTERIAN_MYETAPP"));}
				if (rs.getString("NAMA_AGENSI") == null) {h.put("NAMA_AGENSI", "");} else {h.put("NAMA_AGENSI", rs.getString("NAMA_AGENSI"));}
				if (rs.getString("ID_AGENSI_MYETAPP") == null) {h.put("ID_AGENSI_MYETAPP", "");} else {h.put("ID_AGENSI_MYETAPP", rs.getString("ID_AGENSI_MYETAPP"));}
				if (rs.getString("JENIS_PENGMABILAN_SEGERA") == null) {h.put("JENIS_PENGMABILAN_SEGERA", "");} else {h.put("JENIS_PENGMABILAN_SEGERA", rs.getString("JENIS_PENGMABILAN_SEGERA"));}
				if (rs.getString("FLAG_PERMOHONAN_SEGERA") == null) {h.put("FLAG_PERMOHONAN_SEGERA", "");} else {h.put("FLAG_PERMOHONAN_SEGERA", rs.getString("FLAG_PERMOHONAN_SEGERA"));}
				
				if (rs.getString("KOD_KEMENTERIAN") == null) {h.put("KOD_KEMENTERIAN", "");} else {h.put("KOD_KEMENTERIAN", rs.getString("KOD_KEMENTERIAN"));}
				if (rs.getString("KOD_AGENSI") == null) {h.put("KOD_AGENSI", "");} else {h.put("KOD_AGENSI", rs.getString("KOD_AGENSI"));}
				
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	public static Hashtable getMaklumatHakmilik(String id_fail,
			String id_hakmilik, Db db) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " "
					+ " SELECT DISTINCT ID_HAKMILIK_ETAPP,NO_SUBJAKET, ID_FAIL, NO_FAIL_JKPTG, ID_NEGERI, ID_DAERAH, ID_MUKIM, "
					+ " KOD_JENIS_HAKMILIK, TO_CHAR(NO_HAKMILIK,'0000000') AS NO_HAKMILIK, ID_JENISHAKMILIK, NO_LOT,TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL, JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, "
					+ " KOD_NEGERI,KOD_DAERAH,KOD_MUKIM, "
					+ " (TRIM(KOD_NEGERI) || TRIM(KOD_DAERAH) || TRIM(KOD_MUKIM) || TRIM(KOD_JENIS_HAKMILIK) || TRIM(TO_CHAR(NO_HAKMILIK,'0000000'))) AS ID_HAKMILIK, "
					+ " (CASE WHEN ID_UNIT_LUAS_ASAL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '9' THEN '' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_ASAL, "
					+ " (CASE WHEN ID_UNIT_LUAS_AMBIL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '9' THEN '' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_AMBIL "
					+ " FROM (SELECT DISTINCT C.NO_SUBJAKET, A.ID_FAIL, A.NO_FAIL AS NO_FAIL_JKPTG,A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, "
					+ " JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK,C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, C.NO_LOT,C.ID_HAKMILIK AS ID_HAKMILIK_ETAPP, "
					+ " B.TARIKH_PERMOHONAN,C.ID_UNITLUASAMBIL AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT AS ID_UNIT_LUAS_ASAL, "
					+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL,(JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, "
					+ " TRIM (TO_CHAR (C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM (TO_CHAR (C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL,D.KOD_NEGERI AS KOD_NEGERI,F.KOD_DAERAH AS KOD_DAERAH,"
					+ " E.KOD_MUKIM AS KOD_MUKIM "
					+ " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, TBLRUJNEGERI D, "
					+ " TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH,TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL "
					+ " WHERE  A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI "
					+ " AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT = JL_ASAL.ID_LUAS(+) AND C.ID_UNITLUASAMBIL = JL_AMBIL.ID_LUAS(+) ";

			sql += " AND A.ID_FAIL = '"
					+ id_fail
					+ "'  AND C.ID_HAKMILIK = '"
					+ id_hakmilik
					+ "' "
					+ " AND (TRANSLATE(C.NO_HAKMILIK,'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) ORDER BY C.NO_SUBJAKET, C.NO_LOT ASC) ";

			myLogger.info("##################### PAPAR MAKLUMAT HAKMILIK :"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("ID_FAIL", rs.getString("ID_FAIL"));
				if (rs.getString("ID_HAKMILIK") == null) {
					h.put("ID_HAKMILIK_ETANAH", "");
				} else {
					h.put("ID_HAKMILIK_ETANAH", rs.getString("ID_HAKMILIK")
							.toUpperCase());
				}

				if (rs.getString("KOD_NEGERI") == null) {
					h.put("KOD_NEGERI", "");
				} else {
					h.put("KOD_NEGERI", rs.getString("KOD_NEGERI")
							.toUpperCase());
				}

				if (rs.getString("KOD_DAERAH") == null) {
					h.put("KOD_DAERAH", "");
				} else {
					h.put("KOD_DAERAH", rs.getString("KOD_DAERAH")
							.toUpperCase());
				}

				if (rs.getString("KOD_MUKIM") == null) {
					h.put("KOD_MUKIM", "");
				} else {
					h.put("KOD_MUKIM", rs.getString("KOD_MUKIM").toUpperCase());
				}

				if (rs.getString("KOD_JENIS_HAKMILIK") == null) {
					h.put("KOD_JENIS_HAKMILIK", "");
				} else {
					h.put("KOD_JENIS_HAKMILIK",
							rs.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				}

				if (rs.getString("NO_HAKMILIK") == null) {
					h.put("NO_HAKMILIK", "");
				} else {
					h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")
							.toUpperCase());
				}

			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	public static Hashtable getMaklumatEndorsanBK_fromEtanah(
			String jenis_skrin, String no_fail_jkptg,
			String id_hakmilik_etanah, Db db,Integer turutan) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "  SELECT T.NO_FAIL_JKPTG, T.FLAG_PROSES, T.NO_JILID, T.ID_HAKMILIK, T.TARIKH_ENDORSAN, T.TARIKH_TERIMA_DATA FROM TBLPPTENDORSANFRMETANAH T "
					+ " WHERE T.NO_FAIL_JKPTG IS NOT NULL AND ROWNUM < 2 ";
			sql += " AND T.ID_HAKMILIK = '" + id_hakmilik_etanah + "' ";
			sql += " AND T.FLAG_PROSES = '" + jenis_skrin + "' ";
			sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "' AND T.TURUTAN = '"+turutan+"' ";

			myLogger.info(" getMaklumatEndorsan_fromEtanah ::::::::::::" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("NO_FAIL_JKPTG") == null) {
					h.put("NO_FAIL_JKPTG", "");
				} else {
					h.put("NO_FAIL_JKPTG", rs.getString("NO_FAIL_JKPTG")
							.toUpperCase());
				}
				if (rs.getString("FLAG_PROSES") == null) {
					h.put("FLAG_PROSES", "");
				} else {
					h.put("FLAG_PROSES", rs.getString("FLAG_PROSES")
							.toUpperCase());
				}
				if (rs.getString("NO_JILID") == null) {
					h.put("NO_JILID", "");
				} else {
					h.put("NO_JILID", rs.getString("NO_JILID").toUpperCase());
				}
				if (rs.getString("TARIKH_ENDORSAN") == null) {
					h.put("TARIKH_ENDORSAN", "");
				} else {
					h.put("TARIKH_ENDORSAN", rs.getString("TARIKH_ENDORSAN")
							.toUpperCase());
				}
				if (rs.getString("TARIKH_TERIMA_DATA") == null) {
					h.put("TARIKH_TERIMA_DATA", "");
				} else {
					h.put("TARIKH_TERIMA_DATA",
							rs.getString("TARIKH_TERIMA_DATA").toUpperCase());
				}

			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	//close get data from table temp etanah
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelUPT.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"1486");
	
	}//close addSuburusanHakmilik
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1486");
		
	}//close updateSuburusanStatusFailPPT
	
	
	@SuppressWarnings("unchecked")
	private void getTarikhBorangK(String idHakmilik) throws Exception{
		
		Vector tarikhBorangK = new Vector();
		tarikhBorangK.clear();
		
		String tarikh_borangk = "";
		model.setTarikhBorangK(idHakmilik);
		tarikhBorangK = model.getTarikhBorangK();
		if(tarikhBorangK.size()!=0){
			Hashtable tbk = (Hashtable)tarikhBorangK.get(0);
			tarikh_borangk = (String)tbk.get("tarikh_borangk");
		}
		
		context.put("tarikh_borangk",tarikh_borangk);
		
	}//close getTarikhBorangK
	
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
	private void DataBorangL(String idpermohonan) throws Exception{
    
		Vector dataBorangL = new Vector();
		dataBorangL.clear();
		
		String id_borangl = "";
		
		model.setDataBorangL(idpermohonan);
 		dataBorangL = model.getDataBorangL();
 		context.put("dataBorangL",dataBorangL);
 		if(dataBorangL.size()!=0){
 			Hashtable dbl = (Hashtable)dataBorangL.get(0);
 			id_borangl = (String)dbl.get("id_borangl");
 		}
		
 		context.put("id_borangl",id_borangl);
 		
	}//close DataBorangL
	
	@SuppressWarnings("unchecked")
	private void listMaklumatTanah(String idpermohonan,String noLOT,String flagSegera) throws Exception{
    /*
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();		
		model.setListHMBorangK(idpermohonan,noLOT,flagSegera);
 		listMaklumatTanah = model.getListHMBorangK();
 		context.put("listMaklumatTanah", listMaklumatTanah);*/
 		context.put("saiz_listTanah", model.setListHMBorangK_count(idpermohonan,noLOT,flagSegera));
 		model.setListHMBorangK(idpermohonan,noLOT,flagSegera);
 		
	}//close listMaklumatTanah
	
	@SuppressWarnings("unchecked")
	private void listPenerimaanHakmilik(String idpermohonan,String noLOT,String radio) throws Exception{
    
		Vector listPenerimaanHakmilik = new Vector();
		listPenerimaanHakmilik.clear();
		
		model.setListPenerimaanHakmilik(idpermohonan,noLOT,radio);
		listPenerimaanHakmilik = model.getListPenerimaanHakmilik();
 		context.put("listMaklumatTanah", listPenerimaanHakmilik);
 		context.put("saiz_listTanah", listPenerimaanHakmilik.size());
 		
	}//close listMaklumatTanah
	
	
	@SuppressWarnings("unchecked")
	private void dataMaklumatTanah(String idHakmilik) throws Exception{
    
		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();
		
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
 		
	}//close dataMaklumatTanah
	
	
	@SuppressWarnings("unchecked")
	private void listEndosanBorangK(String idBorangk) throws Exception{
    	
		Vector listEndosanBorangK = new Vector();
		listEndosanBorangK.clear();
		
		model.setListEndosanBorangK(idBorangk);
		listEndosanBorangK = model.getListEndosanBorangK();
 		context.put("listEndosanBorangK", listEndosanBorangK);
 		context.put("saiz_endosan", listEndosanBorangK.size());
 		
	}//close listEndosanBorangK
	
	@SuppressWarnings("unchecked")
	//private void dataEndosanBorangK(String idEndosanBorangk) throws Exception{
	private void dataEndosanBorangK(String idborangk) throws Exception{
			
		Vector dataEndosanBorangK = new Vector();
		dataEndosanBorangK.clear();
		
		//model.setDataEndosanBorangK(idEndosanBorangk);
		model.setDataEndosanBorangK(idborangk);
		dataEndosanBorangK = model.getDataEndosanBorangK();
 		context.put("dataEndosanBorangK", dataEndosanBorangK);
 		
	}//close dataEndosanBorangK
	
	@SuppressWarnings("unchecked")
	private void dataBorangKByHM(String idHakmilik) throws Exception{
    	
		Vector dataBorangK = new Vector();
		dataBorangK.clear();
		
		model.setDataBorangK(idHakmilik);
 		dataBorangK = model.getDataBorangK();
 		context.put("dataBorangK", dataBorangK);
		
	}//close dataBorangKByHM
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanEndosanBorangK(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_borangk", getParam("id_borangk"));
		
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));		
		h.put("txdTarikhCatatan", getParam("txdTarikhCatatan"));
		h.put("txtMasaCatatan", getParam("txtMasaCatatan"));
		h.put("txtPerserahan", getParam("txtPerserahan"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		//h.put("sorTerima", getParam("sorTerima"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.simpanEndosanBorangK(h);
  
	}//close simpanEndosanBorangK
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateEndosan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_endosanborangk", getParam("id_endosanborangk"));
		
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));		
		h.put("txdTarikhCatatan", getParam("txdTarikhCatatan"));
		h.put("txtMasaCatatan", getParam("txtMasaCatatan"));
		h.put("txtPerserahan", getParam("txtPerserahan"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		//h.put("sorTerima", getParam("sorTerima"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.updateEndosan(h);
  
	}//close updateEndosan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateHakmilik(HttpSession session,String idHakmilik) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_hakmilik", idHakmilik);
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));		
		h.put("sorStatusBorangL", getParam("sorStatusBorangL"));
	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.updateHakmilik(h);
  
	}//close updateEndosan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanBorangK(HttpSession session,String id_status) throws Exception{

		Hashtable h = new Hashtable();
		
		//borang k
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_hakmilik", getParam("id_hakmilik"));		
		h.put("txdTarikhBorangK", getParam("txdTarikhBorangK"));
		h.put("txtCatatan", getParam("txtCatatan"));
		
		//endosan borang k
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));		
		h.put("txdTarikhCatatan", getParam("txdTarikhCatatan"));
		h.put("txtMasaCatatan", getParam("txtMasaCatatan"));
		h.put("txtPerserahan", getParam("txtPerserahan"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.simpanBorangK(h);
  
		if(id_status.equals("72") || id_status.equals("59") || id_status.equals("74") || id_status.equals("187") || id_status.equals("204")){
			model.updateStatus(h);
		}
		
	}//close simpanBorangK
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateBorangK(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		//borang k
		h.put("id_borangk", getParam("id_borangk"));
		h.put("txdTarikhBorangK", getParam("txdTarikhBorangK"));
		h.put("txtCatatan", getParam("txtCatatan"));
		
		//endosan borang k
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));		
		h.put("txdTarikhCatatan", getParam("txdTarikhCatatan"));
		h.put("txtMasaCatatan", getParam("txtMasaCatatan"));
		h.put("txtPerserahan", getParam("txtPerserahan"));
		h.put("socJenisWaktu", getParam("socJenisWaktu"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.updateBorangK(h);
  
	}//close updateBorangK
	
	@SuppressWarnings({ "static-access" })
	private void hapusEndosan() throws Exception{

		String id_endosanborangk = getParam("id_endosanborangk");
		model.hapusEndosan(id_endosanborangk);
  
	}//close hapusEndosan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanBorangL(HttpSession session,String idpermohonan) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", idpermohonan);		

		h.put("txdTarikhBorangL", getParam("txdTarikhBorangL"));
		h.put("txtTempoh", getParam("txtTempoh"));
		//h.put("sorPilihHakmilik", getParam("sorPilihHakmilik"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		String[] cbsemaks = request.getParameterValues("cbsemaks");
	 	  
		if((cbsemaks!=null)){
			for (int i = 0; i < cbsemaks.length; i++) { 
				model.simpanBorangL(h,cbsemaks[i]);
			}
		}

	}//close simpanBorangL
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateBorangL(HttpSession session,String idpermohonan) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_borangl", getParam("id_borangl"));		
		h.put("id_permohonan", idpermohonan);
		
		h.put("txdTarikhBorangL", getParam("txdTarikhBorangL"));
		h.put("txtTempoh", getParam("txtTempoh"));
		//h.put("sorPilihHakmilik", getParam("sorPilihHakmilik"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		String[] cbsemaks = request.getParameterValues("cbsemaks");
	 	  
		if((cbsemaks!=null)){
			
			model.deleteFlag(h);
			
			for (int i = 0; i < cbsemaks.length; i++) { 
				
				model.updateBorangL(h,cbsemaks[i]);
			}
		}

	}//close simpanBorangL
	
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
	
}//close class
