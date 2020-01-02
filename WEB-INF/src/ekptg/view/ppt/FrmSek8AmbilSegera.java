package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmMMKSek8Data;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8AmbilSegeraData;
import ekptg.model.ppt.FrmUPTSek8InfoTanahTerperinciBangunanData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmSek8AmbilSegera extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek8AmbilSegera.class);
	
	
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmMMKSek8Data modelMMK = new FrmMMKSek8Data();
	FrmSek8AmbilSegeraData model = new FrmSek8AmbilSegeraData();
	PPTHeader header = new PPTHeader();
	FrmUPTSek8InfoTanahTerperinciBangunanData modelBgn = new FrmUPTSek8InfoTanahTerperinciBangunanData();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	String vm = "";
    	String noLOT = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector dataMaklumatJPBD = new Vector();
    	Vector dataMaklumatJPPH = new Vector();
    	Vector dataMMK = new Vector();
    	Vector dataBorangI = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector dataBorangJ = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
    	dataBorangJ.clear();
    	listMaklumatTanah.clear();
    	dataBorangI.clear();
    	dataMMK.clear();
    	dataMaklumatJPPH.clear();
    	dataMaklumatJPBD.clear();
    	listPageDepan.clear();
    	
    	
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSek8AmbilSegeraSenarai.jsp";
    	String mainscreen = "app/ppt/frmSek8AmbilSegera.jsp";

    	//helper
    	context.put("Utils", new ekptg.helpers.Utils());
    	
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
    		context.put("paging", "22");
    	}
    	*/
    	context.put("paging", "22");
    	
    	//tabbed
    	String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
    	
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
	    
	    //default list
    	//listPageDepan = model.getListPermohonan(userIdNeg);
    	
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
	    
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("modeMMK","");
		context.put("isEditMMK","");
		context.put("showMMK","");
		context.put("onchange","no");
		context.put("showCB", "no");
		context.put("onchangeSegera","no");
		context.put("showx", "off");
		context.put("showListBangunan", "");
		context.put("showDataBangunan", "");
		context.put("showAlertPaging","no");
		
		model.setDataBorangI(idpermohonan);
	 	dataBorangI = model.getDataBorangI();
	 	String id_borangi = "";
	 	if(dataBorangI.size()!=0){
	 		Hashtable db = (Hashtable)dataBorangI.get(0);
	 		id_borangi = (String)db.get("id_borangi");
	 	}
		
	 	if(id_borangi!=""){
	 		context.put("showTab3", "yes");
	 	}else{
	 		context.put("showTab3", "no");
	 	}
	 	
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewSegera".equals(submit)){
    
    		//data mmk
    		dataMMK(session,idpermohonan);
    		
    		//validation
    		modelMMK.setDataMMK(idpermohonan);
        	dataMMK = modelMMK.getDataMMK();
        	String flagBorangI = "";
        	if(dataMMK.size()!=0){
        		Hashtable dm = (Hashtable)dataMMK.get(0);
        		flagBorangI = (String)dm.get("flag_borangi");
        	}
    		
        	
        	String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	//VIEW
        	if(flagBorangI!=""){
        		
        		//form validation
        		context.put("modeMMK", "view");
        		context.put("isEditMMK", "no");
        		
        		if("kemaskiniFlagBorangI".equals(submit2)){
            		
            		context.put("id_mmk",getParam("id_mmk"));
            		
            		//form validation
            		context.put("modeMMK", "view");
            		context.put("isEditMMK", "yes");
          
            		String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
            		if("onchangeSemakUpdate".equals(submit3)){

            			//onchange validation
                		context.put("onchange","yes");
                		
                		//get and set dropdown
                		String onchangeSemak = getParam("socSemakanBorangI");
                		context.put("onchangeSemak",onchangeSemak);
                		
                		//show/hide mmk
                		if(onchangeSemak.equals("1")){
                			context.put("showMMK","yes");
                		}
                	
                	}//close onchangeSemak
            		
            	}//close kemaskiniFlagBorangI
        		
        	//NEW	
        	}else{
        		
        		//form validation
        		context.put("modeMMK", "new");
        		
        		if("onchangeSemak".equals(submit2)){
            		
        			//onchange validation
            		context.put("onchange","yes");
            		
            		//form validation
            		context.put("modeMMK", "new");
            		
            		//get and set dropdown
            		String onchangeSemak = getParam("socSemakanBorangI");
            		context.put("onchangeSemak",onchangeSemak);
            		
            		//show/hide mmk
            		if(onchangeSemak.equals("1")){
            			context.put("showMMK","yes");
            		}
            	
            	}//close onchangeSemak
        		
        	}//close formVal
  
        	
        	if("simpanFlagBorangI".equals(submit2)){
        		
        		//update flag in tblpptmmk
        		updateFlagBorangI(session);
        		
        		//form validation
        		context.put("modeMMK", "view");
        		context.put("isEditMMK", "no");
        		
        		//data mmk
        		dataMMK(session,idpermohonan);
        		
        	}//close simpanFlagBorangI
        	
        	//screen
        	vm = mainscreen;
        	
    	}//close viewSegera
    
    	else 
    	if("viewMaklumatSegera".equals(submit)){
    			
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//list maklumat tanah
    		listMaklumatTanah(idpermohonan,noLOT);
    		
    		//validation by id_borangi
    		model.setDataBorangI(idpermohonan);
 	 		dataBorangI = model.getDataBorangI();
 	 		String jenis_ambilsegera = "";
 	 		if(dataBorangI.size()!=0){
 	 			Hashtable db = (Hashtable)dataBorangI.get(0);
 	 			jenis_ambilsegera = (String)db.get("jenis_ambilsegera");
 	 		}
     		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
     		//VIEW
     		if(dataBorangI.size()!=0){
     			
     			//form validation
     			context.put("mode","view");
     			context.put("isEdit","no");
     			
     			//data borangi + borang k
     			dataMaklumatSegera(idpermohonan);
     			
     			if(jenis_ambilsegera.equals("2")){
     	 			context.put("showCB", "yes");
     	 		}
     			
     			if("kemaskiniMaklumatSegera".equals(submit2)){
     				
     				//form validation
         			context.put("mode","view");
         			context.put("isEdit","yes");
     				
         			String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
         			
                	if("onchangePilihanHMUpdate".equals(submit3)){
                		
                		String jenisSegera = getParam("socSemakanBorangI");
         				
         				if(jenisSegera.equals("2")){
         					//checkbox validation
             				context.put("showx", "on");
         				}
         				
         				context.put("onchangeSegera","yes");
         				
         				//get and set value
         				getAndSetValue();
                		
                	}//close onchangePilihanHMUpdate
         			
                	else if("updateMaklumatSegera".equals(submit3)){
                		
                		//update
                		updateMaklumatSegera(session); 
                		
                		//form validation
             			context.put("mode","view");
             			context.put("isEdit","no");
             			
             			//list maklumat tanah
                		listMaklumatTanah(idpermohonan,noLOT);
             			
             			//data borangi + borang k
             			dataMaklumatSegera(idpermohonan);
         				
             			model.setDataBorangI(idpermohonan);
             	 		dataBorangI = model.getDataBorangI();
             	 		if(dataBorangI.size()!=0){
             	 			Hashtable db = (Hashtable)dataBorangI.get(0);
             	 			jenis_ambilsegera = (String)db.get("jenis_ambilsegera");
             	 		}
             	 		
             	 		if(jenis_ambilsegera.equals("2")){
             	 			context.put("showCB", "yes");
             	 		}else{
             	 			context.put("showCB", "no");
             	 		}
                		
                	}//close updateMaklumatSegera
                	
     			}//close kemaskiniMaklumatSegera
     			
     		//NEW	
     		}else{
     			
     			//form validation
     			context.put("mode","new");
     			
     			//reset value
 				resetValue();
 				
     			if("onchangePilihanHM".equals(submit2)){
     				
     				String jenisSegera = getParam("socSemakanBorangI");
     				
     				if(jenisSegera.equals("2")){
     					//checkbox validation
         				context.put("showCB", "yes");
     				}
     				
     				//get and set value
     				getAndSetValue();
     				
     			}//close onchangePilihanHM
     			
     			else if("simpanMaklumatSegera".equals(submit2)){
     				
     				if (doPost.equals("true")) {
            			//simpan data
     					simpanMaklumatSegera(session,id_status); 
     					/*
     					if(id_status.equals("58")){X
     						updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
     					}*/
     					if(modelUPT.cekStatusFailDahWujud(idpermohonan,"59","52")==false)
	            		{
	        			modelUPT.updateStatus(idpermohonan,id_user, "59");
	        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
	            		}
            		}
     				
     				//form validation
         			context.put("mode","view");
         			context.put("isEdit","no");
         			
         			//update status
         			header.setDataHeader(idpermohonan);
         			dataHeader = header.getDataHeader();
         			context.put("dataHeader", dataHeader);
         			if(dataHeader.size()!=0){
         				Hashtable dh = (Hashtable) dataHeader.get(0);
         				id_status = (String)dh.get("id_status");
         			}
         			
         			//list maklumat tanah
            		listMaklumatTanah(idpermohonan,noLOT);
         			
         			//data borangi + borang k
         			dataMaklumatSegera(idpermohonan);
     				
         			model.setDataBorangI(idpermohonan);
         	 		dataBorangI = model.getDataBorangI();
         	 		if(dataBorangI.size()!=0){
         	 			Hashtable db = (Hashtable)dataBorangI.get(0);
         	 			jenis_ambilsegera = (String)db.get("jenis_ambilsegera");
         	 			id_borangi = (String)db.get("id_borangi");
         	 		}
         	 		
         	 		if(id_borangi!=""){
         		 		context.put("showTab3", "yes");
         		 	}else{
         		 		context.put("showTab3", "no");
         		 	}
         	 		
         	 		if(jenis_ambilsegera.equals("2")){
         	 			context.put("showCB", "yes");
         	 		}
         	 		
         	 		if(id_status.equals("59")){
         	 	//		JOptionPane.showMessageDialog (null, "Sila Klik 'Paging' No.18 Untuk Teruskan ke Borang K", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
            			context.put("showAlertPaging","yes");
           		}else{
            			context.put("showAlertPaging","no");
            		}
         	 		
     			}//close simpanMaklumatSegera
     			
     		}
    		
        	//screen
        	vm = mainscreen;   	
        	
    	}//close viewMaklumatSegera 
    	
    	else 
        if("penjanaanBorangJ".equals(submit)){
        		
        	//form validation
        	context.put("mode", "new");

        	//reset value Borang J dan List Bangunan dan Data Bangunan
        	resetValueBorangJDanListBangunan();
        	
        	//dropdown
        	context.put("selectHakmilik",HTML.SelectHakmilikByPermohonan(idpermohonan,"socHakmilik",null,"style=width:auto onChange=\"getListBangunan('"+idpermohonan+"');\""));
        	
        	//list borangj
        	listBorangJ(idpermohonan);
        	
        	
        	String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
 			
        	if("getListBangunan".equals(submit2)){
        		
        		String idHakmilik = getParam("socHakmilik");
        		context.put("id_hakmilik",idHakmilik);
        		
        		if(idHakmilik!=""){
        			//list validation
            		context.put("showListBangunan", "yes");
        		}else{
        			//list validation
            		context.put("showListBangunan", "");
        		}

        		//dropdown
        		context.put("selectHakmilik",HTML.SelectHakmilikByPermohonan(idpermohonan,"socHakmilik",Utils.parseLong(idHakmilik),"style=width:auto onChange=\"getListBangunan('"+idpermohonan+"');\""));
            	
        		//set data to form
        		dataHakmilik(idHakmilik);
        		
        		//list bangunan
        		listBangunan(session,idHakmilik);
      
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
     			
            	if("viewBangunan".equals(submit3)){
            		
            		//data validation
            		context.put("showDataBangunan", "yes");
            		
            		String id_bangunan = getParam("id_bangunan");
            		context.put("id_bangunan", id_bangunan);
            		
            		idHakmilik = getParam("id_hakmilik");
            		
            		//data bangunan
            		dataBangunan(session,id_bangunan);
            		
            		//list maklumat bangunan pb
                	listMaklumatBangunanPB(session,id_bangunan,idHakmilik);
                	
                	String submit4 = getParam("command4");
                	myLogger.info("submit[4] : " + submit4);
         			
                	if("simpanBorangJ".equals(submit4)){
                	
                		if (doPost.equals("true")) {
                			//simpan data
         					simpanBorangJ(session); 
                		}
                		
                		//reset value Borang J dan List Bangunan dan Data Bangunan
                    	resetValueBorangJDanListBangunan();
                    	
                		//form validation
                    	context.put("mode", "new");
                    	context.put("showListBangunan", "");
                    	context.put("showDataBangunan", "");

                    	//dropdown
                    	context.put("selectHakmilik",HTML.SelectHakmilikByPermohonan(idpermohonan,"socHakmilik",null,"style=width:auto onChange=\"getListBangunan('"+idpermohonan+"');\""));
                    	
                    	//list borangj
                    	listBorangJ(idpermohonan);
                    	
                	}//close simpanBorangJ
                	
            	}//close viewBangunan
            	
        	}//close getListBangunan
    		
    		
        	//screen
        	vm = mainscreen;   	
        	
        }//close penjanaanBorangJ
    	
        else 
        if("viewDataBorangJ".equals(submit)){
            	
        	//form validation
        	context.put("mode", "view");
        	context.put("isEdit", "no");
        	
        	String id_borangj = getParam("id_borangj");
        	context.put("id_borangj", id_borangj);
        	
        	//data Borang J
        	model.setDataBorangJ(id_borangj);
    		dataBorangJ = model.getDataBorangJ();
    		String id_bangunan = "";
    		String id_hakmilik = "";
    		if(dataBorangJ.size()!=0){
    			Hashtable dbj = (Hashtable)dataBorangJ.get(0);
    			id_bangunan = (String)dbj.get("id_bangunan");
    			id_hakmilik = (String)dbj.get("id_hakmilik");
    		}
        	
    		context.put("dataBorangJ", dataBorangJ);
    		context.put("id_bangunan", id_bangunan);
    		context.put("id_hakmilik", id_hakmilik);
    		
    		//list maklumat bangunan pb
        	listMaklumatBangunanPB(session,id_bangunan,id_hakmilik);
        	
        	//list borangj
        	listBorangJ(idpermohonan);
        	
        	String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
 			
        	if("kemaskiniBorangJ".equals(submit2)){
        		
        		//form validation
            	context.put("mode", "view");
            	context.put("isEdit", "yes");
        		
            	id_borangj = getParam("id_borangj");
            	context.put("id_borangj", id_borangj);
            	
            	String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
     			
            	if("updateBorangJ".equals(submit3)){
            		
            		updateBorangJ(session);
            		
            		//form validation
                	context.put("mode", "view");
                	context.put("isEdit", "no");
            		
                	id_borangj = getParam("id_borangj");
                	context.put("id_borangj", id_borangj);
                	
                	//data Borang J
                	model.setDataBorangJ(id_borangj);
            		dataBorangJ = model.getDataBorangJ();
            		if(dataBorangJ.size()!=0){
            			Hashtable dbj = (Hashtable)dataBorangJ.get(0);
            			id_bangunan = (String)dbj.get("id_bangunan");
            			id_hakmilik = (String)dbj.get("id_hakmilik");
            		}
                	
            		context.put("dataBorangJ", dataBorangJ);
            		context.put("id_bangunan", id_bangunan);
            		context.put("id_hakmilik", id_hakmilik);
            		
            		//list maklumat bangunan pb
                	listMaklumatBangunanPB(session,id_bangunan,id_hakmilik);
                	
                	//list borangj
                	listBorangJ(idpermohonan);
                	
            	}//close updateBorangJ
        		
        	}//close kemaskiniBorangJ
        	
        	//screen
        	vm = mainscreen;
        	
        }//close viewDataBorangJ
    	
        else 
    	if("hapusBorangJ".equals(submit)){
    			
    		//hapus data
    		hapusBorangJ(session); 

    		//form validation
        	context.put("mode", "new");

        	//reset value Borang J dan List Bangunan dan Data Bangunan
        	resetValueBorangJDanListBangunan();
        	
        	//dropdown
        	context.put("selectHakmilik",HTML.SelectHakmilikByPermohonan(idpermohonan,"socHakmilik",null,"style=width:auto onChange=\"getListBangunan('"+idpermohonan+"');\""));
        	
        	//list borangj
        	listBorangJ(idpermohonan);
    		
    		//screen
        	vm = mainscreen;
    		
    	}//close hapusBorangJ
    	
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
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1480");
		
	}//close updateSuburusanStatusFailPPT
	
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmSek8AmbilSegeraData.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	private void resetValue() throws Exception{
    	
		context.put("txtNoRujSurat", "");
		context.put("txdTarikhSurat", "");
		context.put("txdTarikhTerima", "");
		context.put("txdTarikhBorangI", "");
		context.put("txdTarikhBorangK", "");
		context.put("socSemakanBorangI", "");
		
	}//close resetValue
	
	private void resetValueBorangJDanListBangunan() throws Exception{
    	
		context.put("lblNoLot", "");
		context.put("lblNamaMukim", "");
		context.put("listMaklumatBangunan", "");
 		context.put("saiz_bangunan", "0");
 		context.put("dataBangunan", "");
 		
	}//close resetValueBorangJ
	
	private void getAndSetValue() throws Exception{
    	
		context.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		context.put("txdTarikhBorangI", getParam("txdTarikhBorangI"));
		context.put("txdTarikhBorangK", getParam("txdTarikhBorangK"));
		context.put("socSemakanBorangI", getParam("socSemakanBorangI"));
		
	}//close getAndSetValue
	
	private void hapusBorangJ(HttpSession session) throws Exception{
    	
		String id_borangj = getParam("id_borangj");
	
		FrmSek8AmbilSegeraData.hapusBorangJ(id_borangj);
  
	}//close hapusHM
	
	@SuppressWarnings("unchecked")
	private void simpanMaklumatSegera(HttpSession session,String idstatus) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txdTarikhBorangI", getParam("txdTarikhBorangI"));
		h.put("txdTarikhBorangK", getParam("txdTarikhBorangK"));
		h.put("socSemakanBorangI", getParam("socSemakanBorangI"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		if(idstatus.equals("58")){
			FrmSek8AmbilSegeraData.updateStatus(h);
		}
		
		String typeVal = getParam("socSemakanBorangI");
		
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		String[] cbsemaks = request.getParameterValues("cbsemaks");
	 	  
		if(typeVal.equals("2") && (cbsemaks!=null)){
			for (int i = 0; i < cbsemaks.length; i++) { 
				FrmSek8AmbilSegeraData.simpanFlagPilihanHM(idUser,cbsemaks[i]);
			}
		}else{
			//simpan flag segera disemua hm
			FrmSek8AmbilSegeraData.simpanSemuaPilihanPTD(idUser,getParam("id_permohonan"));
		}

		FrmSek8AmbilSegeraData.simpanMaklumatSegera(h);
  
	}//close simpanMaklumatSegera
	
	@SuppressWarnings("unchecked")
	private void simpanBorangJ(HttpSession session) throws Exception{

		Vector dataBorangI = new Vector();
		dataBorangI.clear();
		
		String idpermohonan = getParam("id_permohonan");
		
		//get id borang i
		model.setDataBorangI(idpermohonan);
 		dataBorangI = model.getDataBorangI();
 		String id_borangi = "";
 		if(dataBorangI.size()!=0){
 			Hashtable db = (Hashtable)dataBorangI.get(0);
 			id_borangi = (String)db.get("id_borangi");
 		}
 		
 		
		Hashtable h = new Hashtable();
		
		h.put("id_bangunan", getParam("id_bangunan"));
		h.put("id_borangi", id_borangi);
		h.put("id_hakmilik", getParam("id_hakmilik"));
		
		//data bangunan
		h.put("txtNoBangunan", getParam("txtNoBangunan"));
		h.put("txtJenisBangunan", getParam("txtJenisBangunan"));
		h.put("txtNilai", getParam("txtNilai"));
		h.put("txtKos", getParam("txtKos"));
		
		//data borang j
		h.put("txdTarikhNotis", getParam("txdTarikhNotis"));
		h.put("txdTarikhTamatNotis", getParam("txdTarikhTamatNotis"));
		h.put("txtTempoh", getParam("txtTempoh"));
		h.put("txtTindakan", getParam("txtTindakan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//insert tblpptborangj
		FrmSek8AmbilSegeraData.simpanBorangJ(h);
		
		//update tblpptbangunan
		FrmSek8AmbilSegeraData.updateBangunan(h);
		
	}//close simpanBorangJ
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateBorangJ(HttpSession session) throws Exception{

		Vector dataBorangJ = new Vector();
		dataBorangJ.clear();
		
		Hashtable h = new Hashtable();
		
		String id_borangj = getParam("id_borangj");
		
		//data Borang J
    	model.setDataBorangJ(id_borangj);
    	String id_bangunan = "";
		dataBorangJ = model.getDataBorangJ();
		if(dataBorangJ.size()!=0){
			Hashtable dbj = (Hashtable)dataBorangJ.get(0);
			id_bangunan = (String)dbj.get("id_bangunan");
		}
		
		//id
		h.put("id_borangj", id_borangj);
		h.put("id_bangunan", id_bangunan);
		
		//data bangunan
		h.put("txtNoBangunan", getParam("txtNoBangunan"));
		h.put("txtJenisBangunan", getParam("txtJenisBangunan"));
		h.put("txtNilai", getParam("txtNilai"));
		h.put("txtKos", getParam("txtKos"));
		
		//data borang j
		h.put("txdTarikhNotis", getParam("txdTarikhNotis"));
		h.put("txdTarikhTamatNotis", getParam("txdTarikhTamatNotis"));
		h.put("txtTempoh", getParam("txtTempoh"));
		h.put("txtTindakan", getParam("txtTindakan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//insert tblpptborangj
		FrmSek8AmbilSegeraData.updateBorangJ(h);
		
		//update tblpptbangunan
		FrmSek8AmbilSegeraData.updateBangunan(h);
		
	}//close updateBorangJ
	
	@SuppressWarnings("unchecked")
	private void updateMaklumatSegera(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_borangi", getParam("id_borangi"));
		h.put("id_borangk", getParam("id_borangk"));
		
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txdTarikhBorangI", getParam("txdTarikhBorangI"));
		h.put("txdTarikhBorangK", getParam("txdTarikhBorangK"));
		h.put("socSemakanBorangI", getParam("socSemakanBorangI"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		String typeVal = getParam("socSemakanBorangI");
		
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		String[] cbsemaks = request.getParameterValues("cbsemaks");
	 	  
		//clearkan flag setiap hakmilik
		FrmSek8AmbilSegeraData.clearFlagPilihanPTD(idUser,getParam("id_permohonan"));
		
		if(typeVal.equals("2") && (cbsemaks!=null)){
			for (int i = 0; i < cbsemaks.length; i++) { 
				FrmSek8AmbilSegeraData.simpanFlagPilihanHM(idUser,cbsemaks[i]);
			}
		}else{
			//simpan flag segera disemua hm
			FrmSek8AmbilSegeraData.simpanSemuaPilihanPTD(idUser,getParam("id_permohonan"));
		}

		FrmSek8AmbilSegeraData.updateMaklumatSegera(h);
  
	}//close updateMaklumatSegera
	
	
	@SuppressWarnings("unchecked")
	private void listMaklumatTanah(String idpermohonan,String noLOT) throws Exception{
    	
		String idpegawai = "";
		
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();
		
		modelUPT.setListMaklumatTanah_SEGERA(idpermohonan,noLOT,idpegawai);
 		listMaklumatTanah = modelUPT.getListMaklumatTanah();
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
		
	}//close listcarian

	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMMK(HttpSession session,String idpermohonan) throws Exception{
    	
		modelMMK.setDataMMK(idpermohonan);
    	Vector dataMMK = modelMMK.getDataMMK();
    	String id_mmk = "";
    	if(dataMMK.size()!=0){
    		Hashtable dm = (Hashtable)dataMMK.get(0);
    		id_mmk = (String)dm.get("id_mmk");
    	}
    	context.put("id_mmk",id_mmk);
    	context.put("dataMMK",dataMMK);
      
	}//close dataMMK
	
	@SuppressWarnings({ "unchecked" })
	private void dataMaklumatSegera(String idpermohonan) throws Exception{
    	
		Vector dataBorangI = new Vector();
		dataBorangI.clear();
		
		model.setDataBorangI(idpermohonan);
 		dataBorangI = model.getDataBorangI();
 		String id_borangi = "";
 		String id_borangk = "";
 		if(dataBorangI.size()!=0){
 			Hashtable db = (Hashtable)dataBorangI.get(0);
 			id_borangi = (String)db.get("id_borangi");
 			id_borangk = (String)db.get("id_borangk");
 		}
 		context.put("dataBorangI", dataBorangI);
 		context.put("id_borangi", id_borangi);
 		context.put("id_borangk", id_borangk);
 		
	}//close dataMaklumatSegera
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void listBangunan(HttpSession session,String idHakmilik) throws Exception{
    	
		Vector listMaklumatBangunan = new Vector();
		listMaklumatBangunan.clear();
		
		//data & list maklumat tanah
		modelBgn.setListBangunan(idHakmilik);
 		listMaklumatBangunan = modelBgn.getListBangunan();
 		context.put("listMaklumatBangunan", listMaklumatBangunan);
 		context.put("saiz_bangunan", listMaklumatBangunan.size());
 		
	}//close listBangunan
	
	@SuppressWarnings({ "unchecked" })
	private void dataHakmilik(String idHakmilik) throws Exception{
    	
		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();
		
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		String noLot = "";
		String mukim = "";
		if(dataMaklumatTanah.size()!=0){
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			noLot = h.get("no_lot").toString();
			mukim = h.get("nama_mukim").toString();
		}
 		
		context.put("lblNoLot", noLot);
		context.put("lblNamaMukim", mukim);
		
	}//close dataHakmilik
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listBorangJ(String idpermohonan) throws Exception{
    	
		Vector listBorangJ = new Vector();
		listBorangJ.clear();
		
		model.setListBorangJ(idpermohonan);
		listBorangJ = model.getListBorangJ();
		context.put("listBorangJ", listBorangJ);
		context.put("saiz_listborangj", listBorangJ.size());
		
	}//close dataHakmilik
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataBangunan(HttpSession session,String id_bangunan) throws Exception{
    	
		Vector dataBangunan = new Vector();
		dataBangunan.clear();
		
		//data & list maklumat tanah
 		modelBgn.setDataBangunan(id_bangunan);
 		dataBangunan = modelBgn.getDataBangunan();
 		context.put("dataBangunan", dataBangunan);
 		
	}//close listBangunan
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void listMaklumatBangunanPB(HttpSession session,String idBangunan,String idHakmilik) throws Exception{
    	
		Vector listMaklumatPB = new Vector();
		listMaklumatPB.clear();
		
		//data & list maklumat tanah
 		modelBgn.setListMaklumatPBPemilik(idBangunan,idHakmilik);
 		listMaklumatPB = modelBgn.getListMaklumatPBPemilik();
 		context.put("listMaklumatPB", listMaklumatPB);
		context.put("saiz_maklumatpb", listMaklumatPB.size());
 		
	}//close listMaklumatBangunanPB
	
	@SuppressWarnings("unchecked")
	private void updateFlagBorangI(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_mmk", getParam("id_mmk"));		
		h.put("socSemakanBorangI", getParam("socSemakanBorangI"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8AmbilSegeraData.updateFlagBorangI(h);
      
	}//close updateFlagBorangI
	
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
