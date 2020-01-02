package ekptg.view.ppt.bantahan;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmBantahan extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmBantahan.class);
	
	//model
	FrmBantahanModel modelBantahan = new FrmBantahanModel();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	String vm = "";
    	
    	Vector listPageDepan = new Vector();
    	listPageDepan.clear();

    	//screen jsp
    	String listdepan = "app/ppt/BantahanStandAlone/frmBantahanList.jsp";
    	String mainscreen = "app/ppt/BantahanStandAlone/frmBantahanMain.jsp";
    	String depositScreen = "app/ppt/BantahanStandAlone/frmDeposit.jsp";
    	String screenBorangO = "app/ppt/BantahanStandAlone/frmBorangO.jsp";
    	String screenLanjutanTempoh = "app/ppt/BantahanStandAlone/frmLanjutanTempoh.jsp";
    	String screenSusulanBantahan = "app/ppt/BantahanStandAlone/frmSusulanBantahan.jsp";
    	String screenPemulanganDeposit = "app/ppt/BantahanStandAlone/frmPemulanganDeposit.jsp";
    	
    	//utils
    	context.put("Utils", new ekptg.helpers.Utils());
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	anchor(getParam("ScreenLocation"),getParam("CursorPoint"));
    	
    	//tabbed
    	String selectedTab = selectedTab();
    	
		//paging
    	paging(getParam("paging"));
    	
		//user login id
    	//String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
	  	//default list
    	listPageDepan = modelBantahan.getListBantahanStandAlone(userIdNeg);
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange", "no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	String submit2 = getParam("command2");
    	myLogger.info("submit2 : " + submit2);
    	
    	String submit3 = getParam("command3");
    	myLogger.info("submit3 : " + submit3);
    	
    	String id_bantahan = getParam("id_bantahan");
		String id_borango = getParam("id_borango");
		context.put("id_bantahan",id_bantahan);
		context.put("id_borango",id_borango);
		myLogger.info("id_bantahan : " + id_bantahan);
		myLogger.info("id_borango : " + id_borango);
		
    	
    	if("registerBantahan".equals(submit) || "viewBantahan".equals(submit)){
    		
    		
    		if("registerBantahan".equals(submit)){
    			//form validation
        		context.put("mode","new");
        		//reset all field
        		resetFieldValue(userIdNeg);
    		}else{ 		
    			myLogger.info("viewBantahan");
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no"); 
        		context.put("paging", "1");
        		//data bantahan
        		dataMaklumatBantahan(id_bantahan,userIdNeg,"disabled");
    		}
    		
    		
    		if("doChangeNegeri".equals(submit2)){
    			
    			context.put("onchange", "yes");
    			context.put("isEdit","yes");
    			
    			getAndSetDataBantahan();
    			
    		}//close doChangeDaerah
    		
    		else if("simpanDataBantahan".equals(submit2)){
    			
    			String result = "";
    			if (doPost.equals("true")) {
    				result = simpanMaklumatBantahan(session,userIdNeg);
    			}
    			
    			id_bantahan = result;   			
    			
    			if(id_bantahan!=""){
    				//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");     
            		context.put("paging", "1");
            		//data bantahan
            		dataMaklumatBantahan(id_bantahan,userIdNeg,"disabled");
    			}else{
    				//list fail
    				listPageDepan = modelBantahan.getListBantahanStandAlone(userIdNeg);
    				//screen
            		vm = listdepan;
            		return vm;
    			}
    			
    		}//close simpanDataPU
    		
    		else if("kemaskiniDataBantahan".equals(submit2)){
    			//form validation
        		context.put("isEdit","yes");  
//        		id_bantahan = getParam("id_bantahan");
        		
        		//data bantahan
        		dataMaklumatBantahan(id_bantahan,userIdNeg,"enabled");
        		
        		if("updateDataBantahan".equals(submit3)){
        			if (doPost.equals("true")) {
        				updateDataBantahan(session,id_bantahan);
        			}
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");           		
            		//data pu
            		dataMaklumatBantahan(id_bantahan,userIdNeg,"disabled");
        		}//close updateDataPU
        		
    		}//close kemaskiniDataPU
    		
    		else if("hapusDataBantahan".equals(submit2)){
    			
    		
    			
    			if (doPost.equals("true")) {
    				hapusDataBantahan(id_bantahan);  				
    			}
    			
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");
        		
        		//reset all field
        		resetFieldValue(userIdNeg);
        		
    		}//close hapusDataPU
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close registerPU
    	
    	//Paging 2
    	else if("viewDeposit".equals(submit)){
    		
    		
    		context.put("id_bantahan", id_bantahan);
    		
    		//paging  
    		context.put("paging", "2");
    		
    		modelBantahan.setDataMaklumatBantahan(id_bantahan);
    		Vector dataBantahan = modelBantahan.getDataMaklumatBantahan();	
    		context.put("dataBantahan", dataBantahan);
    		context.put("sizeDataBantahan", dataBantahan.size());
    		String txtAmaunTuntutan="",txdTarikhTerimaResit="";
    		if(dataBantahan.size()!=0){
    			Hashtable dp = (Hashtable)dataBantahan.get(0);
    			txtAmaunTuntutan = (String)dp.get("AMAUN_TUNTUTAN");
    			txdTarikhTerimaResit = (String)dp.get("TARIKH_TERIMA_RESIT");
    			myLogger.info("testing123");
    			myLogger.info("txdTarikhTerimaResit---"+txdTarikhTerimaResit);
    		}
    		
    		//view page
    		if(dataBantahan.size()!=0 && txdTarikhTerimaResit!=""){
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");  
        		
        		myLogger.info("testing456");
        		
        		if("kemaskiniDataDeposit".equals(submit2)){
        			
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","yes"); 
        			
        		}//close kemaskiniDataDeposit
        		
    		//new page	
    		}else{
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");   
    			//reset all deposit field
        		resetDepositValue(txtAmaunTuntutan);
    		}//close view/new page
    		
    		if("simpanDataDeposit".equals(submit2)){
    			
    			if (doPost.equals("true")) {
    				updateDataDeposit(session,id_bantahan);
    			}
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");           
        		
        		//data bantahan
        		dataMaklumatBantahan(id_bantahan,userIdNeg,"disabled");
        		
    		}//close simpanDataDeposit
    		
    		//screen
    		vm = depositScreen;
    		
    	}//close
    	
    	//Paging 3
    	else if("viewBorangO".equals(submit)){
    		
    		
    		context.put("id_bantahan", id_bantahan);
    		
    		//paging  
    		context.put("paging", "3");
    		
    		modelBantahan.setDataMaklumatBantahan(id_bantahan);
    		Vector dataBantahan = modelBantahan.getDataMaklumatBantahan();	
    		context.put("dataBantahan", dataBantahan);
    		context.put("sizeDataBantahan", dataBantahan.size());
    		
    		modelBantahan.setDataBorangO(id_bantahan);
    		Vector dataBorangO = modelBantahan.getDataBorangO();	
    		context.put("dataBorangO", dataBorangO);
    		context.put("sizeDataBorangO", dataBorangO.size());

    		//new form
    		if(dataBorangO.size()==0){
    			
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");   
    			//reset all
    			resetBorangO(userIdNeg);
        		
    		//view form	
    		}else{
    			
    			//data borang O
    			dataBorangO(id_bantahan,userIdNeg,"disabled");

    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no"); 
    			
    		}//close new/view form
    		
    		if("doChangeAlamatMahkamah".equals(submit2)){
    			
    			context.put("isEdit","yes");
    			context.put("onchange", "yes");
    			String id_mahkamah = getParam("socMahkamahTinggi");
    			
    			//get and set data
    			getAndSetDataBorangO(userIdNeg,id_mahkamah);
    			
    		}//close doChangeAlamatMahkamah
    		
    		else if("simpanBorangO".equals(submit2)){
    			
    			if (doPost.equals("true")) {
    				simpanBorangO(session,id_bantahan);
    			}
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");           
        		
        		//data borang O
    			dataBorangO(id_bantahan,userIdNeg,"disabled");
    			
    		}//close simpanBorangO
    		
    		else if("kemaskiniBorangO".equals(submit2)){
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes"); 
        		
        		//data borang O
    			dataBorangO(id_bantahan,userIdNeg,"enabled");
    			
    			if("updateBorangO".equals(submit3)){
    				
    				if (doPost.equals("true")) {
    					updateBorangO(session,getParam("id_borango"));
        			}
    				
    				//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
    				
            		//data borang O
        			dataBorangO(id_bantahan,userIdNeg,"disabled");
        			
    			}//close updateBorangO
    			
    		}//close kemaskiniBorangO
    		
    		//screen
    		vm = screenBorangO;
    		
    	}//close viewBorangO
    	
    	//Paging 4
    	else if("lanjutanTempoh".equals(submit)){
    		
    		
    		context.put("id_bantahan", id_bantahan);
    		
    		//paging  
    		context.put("paging", "4");
    		
    		//data bantahan
    		modelBantahan.setDataMaklumatBantahan(id_bantahan);
    		Vector dataBantahan = modelBantahan.getDataMaklumatBantahan();	
    		context.put("dataBantahan", dataBantahan);
    		context.put("sizeDataBantahan", dataBantahan.size());
    		
    		//data borang O
    		modelBantahan.setDataBorangO(id_bantahan);
    		
    		Vector dataBorangO = modelBantahan.getDataBorangO();	
    		context.put("dataBorangO", dataBorangO);
    		context.put("sizeDataBorangO", dataBorangO.size());
    		String tarikh_lanjutan = "";
    		if(dataBorangO.size()!=0){
    			Hashtable db = (Hashtable)dataBorangO.get(0);
    			tarikh_lanjutan = (String)db.get("TARIKH_LANJUTAN_MAHKAMAH");
    			context.put("id_borango", db.get("ID_MAINBORANGO"));
    		}
    		
    		//new
    		if(dataBorangO.size()!=0 && tarikh_lanjutan.equals("")){
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");  			
        		//clear data
        		clearDataLanjutan();
        		
    		//view	
    		}else{
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");  
    		}
    		
    		if("updateLanjutan".equals(submit2)){
    			
    			if (doPost.equals("true")) {
    				updateLanjutan(session,id_bantahan);
    			}
				
				//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
				
        		//data borang O
    			dataBorangO(id_bantahan,userIdNeg,"disabled");
    			
    			//data bantahan
        		dataMaklumatBantahan(id_bantahan,userIdNeg,"disabled");
        		
    		}//close updateLanjutan
    		
    		else if("kemaskiniLanjutan".equals(submit2)){
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
    			
    		}//close kemaskiniLanjutan
    		
    		//screen
    		vm = screenLanjutanTempoh;
    		
    	}//close 
    	
    	//Paging 4
    	else if("susulanBantahan".equals(submit)){
    		
    		
    		context.put("id_bantahan", id_bantahan);
    		
    		//paging  
    		context.put("paging", "4");
    		
    		//data bantahan
    		modelBantahan.setDataMaklumatBantahan(id_bantahan);
    		Vector dataBantahan = modelBantahan.getDataMaklumatBantahan();	
    		context.put("dataBantahan", dataBantahan);
    		context.put("sizeDataBantahan", dataBantahan.size());
    		
    		//data borang O
    		modelBantahan.setDataBorangO(id_bantahan);
    		Vector dataBorangO = modelBantahan.getDataBorangO();	
    		context.put("dataBorangO", dataBorangO);
    		context.put("sizeDataBorangO", dataBorangO.size());
    		String no_prosiding = "";
    		if(dataBorangO.size()!=0){
    			Hashtable db = (Hashtable)dataBorangO.get(0);
    			no_prosiding = (String)db.get("NO_RUJUKAN_TANAH");
    			context.put("id_borango", db.get("ID_MAINBORANGO"));
    		}
    		
    		//new
    		if(dataBorangO.size()!=0 && no_prosiding.equals("")){
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");  			
        		//clear data
        		clearDataSusulan();
        		
    		//view	
    		}else{
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");  
    		}
    		
    		if("updateSusulan".equals(submit2)){
    			
    			if (doPost.equals("true")) {
    				updateSusulan(session,id_bantahan);
    			}
				
				//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
				
        		//data borang O
    			dataBorangO(id_bantahan,userIdNeg,"disabled");
    			
    			//data bantahan
        		dataMaklumatBantahan(id_bantahan,userIdNeg,"disabled");
        		
    		}//close updateSusulan
    		
    		else if("kemaskiniSusulan".equals(submit2)){
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
    			
    		}//close kemaskiniSusulan
    		
    		//screen
    		vm = screenSusulanBantahan;
    		
    	}//close 
    	
    	//Paging 5
    	else if("pemulanganDeposit".equals(submit)){
    		
    		
    		context.put("id_bantahan", id_bantahan);
    		
    		//paging  
    		context.put("paging", "5");
    		
    		//data bantahan
    		modelBantahan.setDataMaklumatBantahan(id_bantahan);
    		Vector dataBantahan = modelBantahan.getDataMaklumatBantahan();	
    		context.put("dataBantahan", dataBantahan);
    		context.put("sizeDataBantahan", dataBantahan.size());
    		
    		//data borang O
    		modelBantahan.setDataBorangO(id_bantahan);
    		Vector dataBorangO = modelBantahan.getDataBorangO();	
    		context.put("dataBorangO", dataBorangO);
    		context.put("sizeDataBorangO", dataBorangO.size());
    		String flag_pulang_deposit = "";
    		if(dataBorangO.size()!=0){
    			Hashtable db = (Hashtable)dataBorangO.get(0);
    			flag_pulang_deposit = (String)db.get("FLAG_PULANG_DEPOSIT");
    		}
    		
    		//new
    		if( flag_pulang_deposit.equals("")){
    			//form validation
        		context.put("mode","new");
        		context.put("isEdit","no");  			
        		//clear data
        		clearDataPemulanganDep();
        		
    		//view	
    		}else{
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");  
    		}
    		
    		if("updatePemulanganDep".equals(submit2)){
    			
    			if (doPost.equals("true")) {
    				updatePemulanganDep(session,id_bantahan);
    			}
				
				//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
				
        		//data borang O
    			dataBorangO(id_bantahan,userIdNeg,"disabled");
    			
    			//data bantahan
        		dataMaklumatBantahan(id_bantahan,userIdNeg,"disabled");
        		
    		}//close updateSusulan
    		
    		else if("kemaskiniPemulanganDep".equals(submit2)){
    			
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
    			
    		}//close kemaskiniSusulan
    		
    		//screen
    		vm = screenPemulanganDeposit;
    		
    	}//close 
    	else{
    		
    		//list fail
    		listPageDepan = modelBantahan.getListBantahanStandAlone(userIdNeg);
    		
    		//screen
    		vm = listdepan;
    		
    	}
    		
    		
    		//list Bantahan
	    	context.put("listBantahan", listPageDepan);
	    	
	    	myLogger.info("vm : "+vm);
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
	
	
	
	
	
	
	
	
//--METHOD--//	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSusulan(HttpSession session,String id_bantahan) throws Exception{

		Hashtable h = new Hashtable();

		h.put("txtNoProsiding", getParam("txtNoProsiding"));
		h.put("txtNoRujPemohon", getParam("txtNoRujPemohon"));
		h.put("sorKeputusanMahkamah", getParam("sorKeputusanMahkamah"));
		h.put("txdTarikhPerintah", getParam("txdTarikhPerintah"));
		h.put("txdTarikhTerimaPerintah", getParam("txdTarikhTerimaPerintah"));
		h.put("txtAmaunTambahan", getParam("txtAmaunTambahan"));
		h.put("txtKosPengapit", getParam("txtKosPengapit"));
		h.put("txtTempohBayaran", getParam("txtTempohBayaran"));
		h.put("socUnitTempoh", getParam("socUnitTempoh"));
		h.put("sorStatusPulangDep", getParam("sorStatusPulangDep"));
		h.put("txtKeteranganPampasan", getParam("txtKeteranganPampasan"));
		h.put("txtKosJPPH",getParam("txtKosJPPH"));
		h.put("txtNamaJPPH",getParam("txtNamaJPPH"));
		h.put("txtKosSwasta",getParam("txtKosSwasta"));
		h.put("txtNamaSwasta",getParam("txtNamaSwasta"));
		h.put("txtNamaSyarikat",getParam("txtNamaSyarikat"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelBantahan.updateSusulan(h,id_bantahan);
		
	}//close updateSusulan
	
	private void clearDataSusulan() throws Exception{  	
		
		context.put("txtNoProsiding", "");
		context.put("txtNoRujPemohon", "");
		context.put("txdTarikhLanjutanMahkamah", "");
		context.put("sorKeputusanMahkamah", "");
		context.put("txdTarikhPerintah", "");
		context.put("txdTarikhTerimaPerintah", "");
		context.put("txtAmaunTambahan", "");
		context.put("txtKosPengapit", "");
		context.put("txtTempohBayaran", "");
		context.put("socUnitTempoh", "");
		context.put("sorStatusPulangDep", "");
		context.put("txtKeteranganPampasan", "");
		context.put("txtKosJPPH","");
		context.put("txtNamaJPPH","");
		context.put("txtKosSwasta","");
		context.put("txtNamaSwasta","");
		context.put("txtNamaSyarikat","");
		
	}//close clearDataSusulan
	private void updateLanjutan(HttpSession session,String id_bantahan) throws Exception{

		Hashtable h = new Hashtable();

	
		h.put("txdTarikhLanjutanMahkamahOB", getParam("txdTarikhLanjutanMahkamahOB"));
		h.put("txdTarikhLanjutanMahkamahPT", getParam("txdTarikhLanjutanMahkamahPT"));
		
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelBantahan.updateLanjutan(h,id_bantahan);
		
	}//close updateSusulan
	private void clearDataLanjutan() throws Exception{  	
		
		
		context.put("txdTarikhLanjutanMahkamah", "");
		
		
	}//close clearDataLanjutan
	
	private void updatePemulanganDep(HttpSession session,String id_bantahan) throws Exception{

		Hashtable h = new Hashtable();

		
		h.put("sorStatusPulangDep", getParam("sorStatusPulangDep"));
				
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelBantahan.updatePemulanganDep(h,id_bantahan);
		
	}//close updatePemulanganDep
	
	private void clearDataPemulanganDep() throws Exception{  	
		
		
		context.put("sorStatusPulangDep", "");
		
	}//close clearDataPemulanganDep
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateBorangO(HttpSession session,String id_borango) throws Exception{

		Hashtable h = new Hashtable();

		h.put("txdTarikhBorangO", getParam("txdTarikhBorangO"));
		h.put("txdTarikhHantarBorangO", getParam("txdTarikhHantarBorangO"));
		h.put("txtNamaPenghantar", getParam("txtNamaPenghantar"));
		h.put("txtNamaPenerima", getParam("txtNamaPenerima"));
		h.put("socMahkamahTinggi", getParam("socMahkamahTinggi"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelBantahan.updateBorangO(h,id_borango);
		
	}//close updateBorangO
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanBorangO(HttpSession session,String id_bantahan) throws Exception{

		Hashtable h = new Hashtable();

		h.put("txdTarikhBorangO", getParam("txdTarikhBorangO"));
		h.put("txdTarikhHantarBorangO", getParam("txdTarikhHantarBorangO"));
		h.put("txtNamaPenghantar", getParam("txtNamaPenghantar"));
		h.put("txtNamaPenerima", getParam("txtNamaPenerima"));
		h.put("socMahkamahTinggi", getParam("socMahkamahTinggi"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelBantahan.simpanBorangO(h,id_bantahan);
		
	}//close simpanBorangO
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataBorangO(String id_bantahan,String userIdNeg,String mode) throws Exception{  
		
		modelBantahan.setDataBorangO(id_bantahan);
		Vector dataBorangO = modelBantahan.getDataBorangO();	
		context.put("dataBorangO", dataBorangO);
		context.put("sizeDataBorangO", dataBorangO.size());
		String id_borango="",id_negeri="",id_bandar="",id_mahkamah="";
		if(dataBorangO.size()!=0){
			Hashtable db = (Hashtable)dataBorangO.get(0);
			id_borango = (String)db.get("ID_MAINBORANGO");
			id_negeri = (String)db.get("ID_NEGERI");
			id_bandar = (String)db.get("ID_BANDAR");
			id_mahkamah = (String)db.get("ID_MAHKAMAH");
		}
		
		context.put("id_borango", id_borango);
		
		if(mode.equals("disabled")){
			mode = "disabled class=disabled";
		}else{
			mode = "";
		}
		
		//dropdown
		if (userIdNeg!=""){	
			context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(userIdNeg), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), " "+mode+" style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));					
		}else{	
			context.put("selectMahkamahTinggi", HTML.SelectMahkamah("socMahkamahTinggi", Utils.parseLong(id_mahkamah), " "+mode+" style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
		}	
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri)," class=disabled disabled style=width:325px"));
		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar)," class=disabled disabled style=width:325px"));
		
	}//close dataBorangO
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void getAndSetDataBorangO(String userIdNeg,String id_mahkamah) throws Exception{  	
		
		context.put("id_bantahan", getParam("id_bantahan"));
		context.put("id_borango", getParam("id_borango"));

		//data borang o
		context.put("txdTarikhBorangO", getParam("txdTarikhBorangO"));
		context.put("txdTarikhHantarBorangO", getParam("txdTarikhHantarBorangO"));
		context.put("txtNamaPenghantar", getParam("txtNamaPenghantar"));
		context.put("txtNamaPenerima", getParam("txtNamaPenerima"));
		
		//data mahkamah
		String alamat1="",alamat2="",alamat3="",poskod="",id_negeri="",id_bandar="";
		Vector dataMahkamah = modelBantahan.getDataBantahan(id_mahkamah);
		if(dataMahkamah.size()!=0){
			Hashtable dm = (Hashtable)dataMahkamah.get(0);
			alamat1 = (String)dm.get("ALAMAT1");
			alamat2 = (String)dm.get("ALAMAT2");
			alamat3 = (String)dm.get("ALAMAT3");
			poskod = (String)dm.get("POSKOD");
			id_negeri = (String)dm.get("ID_NEGERI");
			id_bandar = (String)dm.get("ID_BANDAR");
		}
		
		context.put("txtAlamat1", alamat1);
		context.put("txtAlamat2", alamat2);
		context.put("txtAlamat3", alamat3);
		context.put("txtPoskod", poskod);
	
		//dropdown
		if (userIdNeg!=""){	
			context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(userIdNeg), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));					
		}else{	
			context.put("selectMahkamahTinggi", HTML.SelectMahkamah("socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
		}	
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri)," class=disabled disabled style=width:325px"));
		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar)," class=disabled disabled style=width:325px"));
		
		
	}//close getAndSetDataBorangO
	
	private void resetBorangO(String userIdNeg) throws Exception{  	
		
		context.put("id_borango", "");
		context.put("sizeDataBorangO", 0);
		
		//data borang o
		context.put("txdTarikhBorangO", "");
		context.put("txtAlamat1", "");
		context.put("txtAlamat2", "");
		context.put("txtAlamat3", "");
		context.put("txtPoskod", "");
		context.put("txdTarikhHantarBorangO", "");
		context.put("txtNamaPenghantar", "");
		context.put("txtNamaPenerima", "");
		
		//dropdown
		if (userIdNeg!=""){	
			context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(userIdNeg), "socMahkamahTinggi", null, "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));					
		}else{	
			context.put("selectMahkamahTinggi", HTML.SelectMahkamah("socMahkamahTinggi", null, "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
		}	
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null," class=disabled disabled style=width:325px"));
		context.put("selectBandar",HTML.SelectBandar("socBandar",null," class=disabled disabled style=width:325px"));
		
	}//close resetFieldValue
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateDataDeposit(HttpSession session,String id_bantahan) throws Exception{

		Hashtable h = new Hashtable();

		//data bantahan
		h.put("txtAmaunPampasan", getParam("txtAmaunPampasan"));
		h.put("txtNoCek", getParam("txtNoCek"));
		h.put("txtNoAkaun", getParam("txtNoAkaun"));
		h.put("socCaraBayaran", getParam("socCaraBayaran"));
		h.put("txtNamaBank", getParam("txtNamaBank"));
		h.put("txdTarikhTerimaResit", getParam("txdTarikhTerimaResit"));
		h.put("txdTarikhResit", getParam("txdTarikhResit"));
		h.put("txtNoResit", getParam("txtNoResit"));
		h.put("txtDeposit", getParam("txtDeposit"));
		h.put("socCaraBayaranDeposit", getParam("socCaraBayaranDeposit"));
		h.put("txtNoCekDeposit", getParam("txtNoCekDeposit"));
		h.put("txtNoAkaunDeposit", getParam("txtNoAkaunDeposit"));
		h.put("txtNamaBankDeposit", getParam("txtNamaBankDeposit"));
		h.put("txdTarikhPulang", getParam("txdTarikhPulang"));
		h.put("socStatusPulang", getParam("socStatusPulang"));
		h.put("txtCatatanPulang", getParam("txtCatatanPulang"));
		h.put("txdTarikhHantarDeposit", getParam("txdTarikhHantarDeposit"));
		h.put("txtNamaPenghantar", getParam("txtNamaPenghantar"));
		h.put("txtAmaunTuntutan", getParam("txtAmaunTuntutan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelBantahan.updateDeposit(h,id_bantahan);
		
	}//close updateDataDeposit
	
	private void resetDepositValue(String txtAmaunTuntutan) throws Exception{ 
		
		double deposit = 0.00;
		if(txtAmaunTuntutan!="" && txtAmaunTuntutan!="0.00"){
			deposit = (Utils.parseDouble(Utils.RemoveSymbol(txtAmaunTuntutan)) * 10) / 100;
			if(deposit>=3000){
				deposit = 3000;
			}
		}
		
		//data deposit
		context.put("txtAmaunPampasan", "");
		context.put("txtNoCek", "");
		context.put("txtNoAkaun", "");
		context.put("socCaraBayaran", "");
		context.put("txtNamaBank", "");
		context.put("txdTarikhTerimaResit", "");
		context.put("txdTarikhResit", "");
		context.put("txtNoResit", "");
		context.put("txtDeposit", Utils.format2Decimal(deposit));
		context.put("socCaraBayaranDeposit", "");
		context.put("txtNoCekDeposit", "");
		context.put("txtNoAkaunDeposit", "");
		context.put("txtNamaBankDeposit", "");
		context.put("txdTarikhPulang", "");
		context.put("socStatusPulang", "");
		context.put("txtCatatanPulang", "");
		context.put("txdTarikhHantarDeposit", "");
		context.put("txtNamaPenghantar", "");
		
	}//close resetDepositValue
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateDataBantahan(HttpSession session,String id_bantahan) throws Exception{

		Hashtable h = new Hashtable();

		//data bantahan
		h.put("txtNoFail", getParam("txtNoFail"));
		h.put("txtNoBantahan", getParam("txtNoBantahan"));
		h.put("txdTarikhTerimaBorangN", getParam("txdTarikhTerimaBorangN"));
		h.put("txdTarikhBorangN", getParam("txdTarikhBorangN"));
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("txtNoLot", getParam("txtNoLot"));
		h.put("socStatusBantahan", getParam("socStatusBantahan"));
		h.put("txdTarikhTerimaBorangH", getParam("txdTarikhTerimaBorangH"));
		h.put("txdTarikhAward", getParam("txdTarikhAward"));
		h.put("socStatusHadir", getParam("socStatusHadir"));
		h.put("txtNamaPembantah", getParam("txtNamaPembantah"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("txtKepentingan", getParam("txtKepentingan"));
		
		h.put("cbUkuranLuas", getParam("cbUkuranLuas"));
		h.put("cbAmaunPampasan", getParam("cbAmaunPampasan"));
		h.put("cbTerimaPampasan", getParam("cbTerimaPampasan"));
		h.put("cbBahagianPampasan", getParam("cbBahagianPampasan"));
		
		h.put("txtAmaunTuntutan", getParam("txtAmaunTuntutan"));
		h.put("txtAlasanBantahan", getParam("txtAlasanBantahan"));
		h.put("txtMaklumatTamatTempoh", getParam("txtMaklumatTamatTempoh"));
		
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));
		h.put("socJenisPB", getParam("socJenisPB"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelBantahan.updateBantahanStandAlone(h,id_bantahan);
		
	}//close updateDataBantahan
	
	@SuppressWarnings("static-access")
	private void hapusDataBantahan(String id_bantahan) throws Exception{
		
		modelBantahan.hapusDataBantahan(id_bantahan);
		
	}//close hapusDataPU
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMaklumatBantahan(String id_bantahan,String userIdNeg,String mode) throws Exception{
		
		modelBantahan.setDataMaklumatBantahan(id_bantahan);
		Vector dataBantahan = modelBantahan.getDataMaklumatBantahan();	
		context.put("dataBantahan", dataBantahan);
		context.put("sizeDataBantahan", dataBantahan.size());
		
		String id_negeri="",id_bandar="",id_jenispb="", id_borango="";
		if(dataBantahan.size()!=0){
			Hashtable dp = (Hashtable)dataBantahan.get(0);
//			id_bantahan = (String)dp.get("ID_MAINBANTAHAN");
//			id_borango = (String)dp.get("ID_MAINBORANGO");
			id_negeri = (String)dp.get("ID_NEGERI");
			id_bandar = (String)dp.get("ID_BANDAR");
			id_jenispb = (String)dp.get("ID_JENISPB");
			
		}

		if(mode.equals("disabled")){
			mode = "disabled class=disabled";
		}else{
			mode = "";
		}
		
		//dropdown
		if(id_negeri!=""){
			context.put("selectBandar",HTML.SelectBandarByNegeri(id_negeri,"socBandar",Utils.parseLong(id_bandar)," "+mode+" style=width:325px"));
		}else{
			context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar)," "+mode+" style=width:325px"));
		}
		context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",Utils.parseLong(id_jenispb)," "+mode+" style=width:325px"));
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri)," "+mode+" style=width:325px onChange=\"doChangeNegeri();\" "));
		
	}//close dataMaklumatPU
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String simpanMaklumatBantahan(HttpSession session,String userIdNeg) throws Exception{

		Hashtable h = new Hashtable();

		//data bantahan
		h.put("txtNoFail", getParam("txtNoFail"));
		h.put("txtNoBantahan", getParam("txtNoBantahan"));
		h.put("txdTarikhTerimaBorangN", getParam("txdTarikhTerimaBorangN"));
		h.put("txdTarikhBorangN", getParam("txdTarikhBorangN"));
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("txtNoLot", getParam("txtNoLot"));
		h.put("socStatusBantahan", getParam("socStatusBantahan"));
		h.put("txdTarikhTerimaBorangH", getParam("txdTarikhTerimaBorangH"));
		h.put("txdTarikhAward", getParam("txdTarikhAward"));
		h.put("socStatusHadir", getParam("socStatusHadir"));
		h.put("txtNamaPembantah", getParam("txtNamaPembantah"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("txtKepentingan", getParam("txtKepentingan"));
		
		h.put("cbUkuranLuas", getParam("cbUkuranLuas"));
		h.put("cbAmaunPampasan", getParam("cbAmaunPampasan"));
		h.put("cbTerimaPampasan", getParam("cbTerimaPampasan"));
		h.put("cbBahagianPampasan", getParam("cbBahagianPampasan"));
		
		h.put("txtAmaunTuntutan", getParam("txtAmaunTuntutan"));
		h.put("txtAlasanBantahan", getParam("txtAlasanBantahan"));
		h.put("txtMaklumatTamatTempoh", getParam("txtMaklumatTamatTempoh"));
		
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));
		h.put("socJenisPB", getParam("socJenisPB"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		return modelBantahan.simpanBantahanStandAlone(h,userIdNeg);
		
	}//close simpanMaklumatBantahan
	
	private void getAndSetDataBantahan() throws Exception{  
		
		context.put("txtNoFail", getParam("txtNoFail"));
		context.put("txtNoBantahan", getParam("txtNoBantahan"));
		context.put("txdTarikhTerimaBorangN", getParam("txdTarikhTerimaBorangN"));
		context.put("txdTarikhBorangN", getParam("txdTarikhBorangN"));
		context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		context.put("txtNoLot", getParam("txtNoLot"));
		context.put("socStatusBantahan", getParam("socStatusBantahan"));
		context.put("txdTarikhTerimaBorangH", getParam("txdTarikhTerimaBorangH"));
		context.put("txdTarikhAward", getParam("txdTarikhAward"));
		context.put("socStatusHadir", getParam("socStatusHadir"));
		context.put("txtNamaPembantah", getParam("txtNamaPembantah"));
		context.put("txtAlamat1", getParam("txtAlamat1"));
		context.put("txtAlamat2", getParam("txtAlamat2"));
		context.put("txtAlamat3", getParam("txtAlamat3"));
		context.put("txtPoskod", getParam("txtPoskod"));
		context.put("txtKepentingan", getParam("txtKepentingan"));
		
		context.put("cbUkuranLuas", getParam("cbUkuranLuas"));
		context.put("cbAmaunPampasan", getParam("cbAmaunPampasan"));
		context.put("cbTerimaPampasan", getParam("cbTerimaPampasan"));
		context.put("cbBahagianPampasan", getParam("cbBahagianPampasan"));
		
		context.put("txtAmaunTuntutan", getParam("txtAmaunTuntutan"));
		context.put("txtAlasanBantahan", getParam("txtAlasanBantahan"));
		context.put("txtMaklumatTamatTempoh", getParam("txtMaklumatTamatTempoh"));
	
		String id_negeri=getParam("socNegeri"),id_bandar=getParam("socBandar"),id_jenispb=getParam("socJenisPB");
		
		//dropdown
		if(id_negeri!=""){
			context.put("selectBandar",HTML.SelectBandarByNegeri(id_negeri,"socBandar",Utils.parseLong(id_bandar),"style=width:325px"));
		}else{
			context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:325px"));
		}
		context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",Utils.parseLong(id_jenispb),"style=width:325px"));
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),"style=width:325px onChange=\"doChangeNegeri();\" "));
    	
	}//close getAndSetDataHM
	
	private void resetFieldValue(String userIdNeg) throws Exception{  	
		
		context.put("id_bantahan", "");
		context.put("sizeDataBantahan", 0);
		
		//data bantahan
		context.put("txtNoFail", "");
		context.put("txtNoBantahan", "");
		context.put("txdTarikhTerimaBorangN", "");
		context.put("txdTarikhBorangN", "");
		context.put("txtNoHakmilik", "");
		context.put("txtNoLot", "");
		context.put("socStatusBantahan", "");
		context.put("txdTarikhTerimaBorangH", "");
		context.put("txdTarikhAward", "");
		context.put("socStatusHadir", "");
		context.put("txtNamaPembantah", "");
		context.put("txtAlamat1", "");
		context.put("txtAlamat2", "");
		context.put("txtAlamat3", "");
		context.put("txtPoskod", "");
		context.put("txtKepentingan", "");
		
		context.put("cbUkuranLuas", "");
		context.put("cbAmaunPampasan", "");
		context.put("cbTerimaPampasan", "");
		context.put("cbBahagianPampasan", "");
		
		context.put("txtAmaunTuntutan", "");
		context.put("txtAlasanBantahan", "");
		context.put("txtMaklumatTamatTempoh", "");
		
		//dropdown
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,"style=width:325px onChange=\"doChangeNegeri();\" "));
		context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:325px"));
		context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",null,"style=width:325px"));
		
	}//close resetFieldValue
	
	private void anchor(String ScreenLocation, String CursorPoint) throws Exception{  	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
	}//close anchor
	
	private String selectedTab() throws Exception{
		String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		return selectedTab;	
	}//close selectedTab
	
	private void paging(String flagPaging) throws Exception{		
		if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "1");
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
			this.context.put("listBantahan",paging.getPage(page));
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
