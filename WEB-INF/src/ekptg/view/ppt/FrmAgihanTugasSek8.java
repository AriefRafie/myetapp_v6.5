package ekptg.view.ppt;
//yati ada tambah untuk emel ke pengarah lain-otw
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmAgihanTugasSek8Data;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmAgihanTugasSek8 extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmAgihanTugasSek8.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmAgihanTugasSek8Data model = new FrmAgihanTugasSek8Data();
	MyInfoPPTData myInfo = new MyInfoPPTData(); //penambahan yati
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
	
		//command for paging
    	String action = getParam("action");
    
    	String vm = "";

    	Vector listPageDepan = new Vector();
    	listPageDepan.clear();
   	
    	//get user login detail
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");	
    	String myrole = (String) session.getAttribute("myrole");
    	context.put("portal_user", myrole);
    	
    	//screen jsp
    	String listdepan = "app/ppt/AgihanS8/frmAgihanTugasSek8Senarai.jsp";
    	String mainScreen = "app/ppt/AgihanS8/frmAgihanSek8.jsp";
    	
    	//default list
    	//listPageDepan = model.getListPermohonan(userIdNeg);
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	anchor(getParam("ScreenLocation"),getParam("CursorPoint"));
    	
    	//paging
    	paging(getParam("paging"));
    	
    	
    	context.put("showListAuto","");

		//header
		String id_status = "";
		String id_fail = "";
		String flagSegera = "";
		String id_projeknegeri = "";
		String flag_subjaket = "";
		//penambahan yati
		String no_fail = "";
		String nama_kementerian = "";
		String tarikh_permohonan = "";
		String tujuan = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			id_fail = (String)dh.get("id_fail");
			flagSegera = dh.get("flag_segera").toString();
			id_projeknegeri = (String)dh.get("id_projekNegeri");
			flag_subjaket = dh.get("flag_subjaket").toString();
			//penambahan yati
			no_fail = (String) dh.get("no_fail");
			tujuan = (String) dh.get("tujuan");
			tarikh_permohonan = (String) dh.get("tarikh_permohonan");
			nama_kementerian = (String) dh.get("nama_kementerian");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}

		context.put("flag_subjaket",flag_subjaket);
		context.put("flagSegera",flagSegera);
		
		//Vector listEmel = null; // yati tmbh function
		
		//get id suburusan status fail
		String id_suburusanstatusfailppt = IdSubUrusanStatusFail(idpermohonan);
		
		//get current date
		currentDate();
		
		//default
		defaultValue();
		
		//tabbed
    	selectedTab();
    	String selectedTab = selectedTab();
    	
    	
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	String submit2 = getParam("command2");
    	myLogger.info("submit2 : " + submit2);
    	
    	if("screenAgihan".equals(submit)){
    		
    		selectedTab = "0";
    		
    		if(!myrole.equals("(PPT)PengarahUnit")){
    			selectedTab = "1";
    			
    		}
    		
    		
    		//list hakmilik yang telah diagih
    		listHMAgih(idpermohonan,"");
    		
    		//list hakmilik
    		listHM(idpermohonan,"","");
    		
    		//dropdown
    		dropdown(id_projeknegeri,"","");
    		
    		if("onChangePP".equals(submit2)){
    		
    			String id_ppengarah = getParam("socPegawaiPP");
    			
    			this.context.put("id_ppengarah", id_ppengarah);
    			myLogger.info("ID PENGARAH :::"+id_ppengarah);
   			
    			//listEmel = model.setListEmel(id_ppengarah);
    			
    			if(id_ppengarah.equals("")){
    				context.put("showCB","no");
    			}else{
    				context.put("showCB","yes");
    			}
    			
    			//dropdown
        		dropdown(id_projeknegeri,id_ppengarah,"");
        		
        		//get and set value
        		getAndSetValue();
        		
    			//list hakmilik
        		listHM(idpermohonan,id_ppengarah,"");
        		
        		context.put("showListAuto","yes");
    			
    		}//close onChangePP
    		
    		else if("simpanAgihan".equals(submit2)){
    			String id_ppengarah = getParam("socPegawaiPP");
    			
    			this.context.put("id_ppengarah", id_ppengarah);
    			//System.out.println("ID PENGARAHXXX ::"+id_ppengarah);
    
    			nameAndIdPengarah(id_ppengarah);
    			String id_pengarahP = nameAndIdPengarah(id_ppengarah);  
    			
    			//System.out.println("ID PENGARAH :::"+id_pengarahP);
    			
    			if (doPost.equals("true")) {
    				
        			simpanAgihan(session,idpermohonan,id_status,id_fail,id_suburusanstatusfailppt);
        			
        			//function yati
        			hantarEmel("content","Tindakan Pengarah untuk Agihan Tugas",no_fail, tujuan, tarikh_permohonan,nama_kementerian,id_pengarahP);						
        		}
    			
    			//list hakmilik
        		listHM(idpermohonan,"","");
        		
    		}//close simpanAgihan
    		
    
    		//screen
    		vm = mainScreen;
    		
    	}//close screenAgihan
    	
    	else if("AgihanLayer2".equals(submit)){
    		
    		//System.out.println("ID STATUS----"+id_status);
    		selectedTab = "1";
    		
    		//dropdown
    		dropdown(id_projeknegeri,"","");
    		
    		//list hakmilik yang telah diagih
    		listHMAgih(idpermohonan,"");
    		
    		if("onChangeLayer2".equals(submit2)){
        		
    			String id_penolong = getParam("socPegawai");
    			this.context.put("id_penolong", id_penolong);
    			
    			//System.out.println("ID PENOLONGGG :: "+id_penolong);
    			
    			if(id_penolong.equals("")){
    				context.put("showCB","no");
    			}else{
    				context.put("showCB","yes");
    			}
    			
    			//dropdown
        		dropdown(id_projeknegeri,"",id_penolong);
        		
        		//get and set value
        		getAndSetValueLayer2();
        		
        		//list hakmilik yang telah diagih
        		listHMAgih(idpermohonan,id_penolong);
        		
        		context.put("showListAuto","yes");
    			
    		}//close onChangePP
    		
    		else if("simpanAgihanLayer2".equals(submit2)){
    			
    			String id_penolong = getParam("socPegawai");
    			this.context.put("id_penolong", id_penolong);
    			
    			
    			nameAndIdPenolong(id_penolong);
    			String id_penolongP = nameAndIdPenolong(id_penolong);  
    			//System.out.println("ID PENOLONG PPP : "+id_penolongP);
    			
    			if (doPost.equals("true")) {
    				simpanAgihanLayer2(session,idpermohonan,id_status);
    				
    				if(id_status.equals("16")){
        				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt,"1467");
        			}
    				
    				hantarEmelPen("content","Tindakan Pentadbir Tanah",no_fail, tujuan, tarikh_permohonan,nama_kementerian,id_penolongP);
    				//update status terkini
        			id_status = getLatestStatus(idpermohonan);
        		}
    						
    			//list hakmilik yang telah diagih
        		listHMAgih(idpermohonan,"");
        		
    		}//close simpanAgihan
    		
    		//screen
    		vm = mainScreen;
    		
    	}//close AgihanLayer2
    	
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
    		
    		//reset value
    		defaultValue();
			
    		//screen
    		vm = listdepan;
    		
    		String id_ppengarah = getParam("socPegawaiPP");
			
			this.context.put("id_ppengarah", "");
    		
		}//close else
    	
    		//list permohonan
	    	context.put("listPermohonan", listPageDepan);
	    	context.put("list_size", listPageDepan.size());
	    
	    	//id
	    	context.put("id_permohonan", idpermohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_fail", id_fail);
	    	context.put("selectedTab",selectedTab);
	    	
	    	listHMAgih_INFO_PT(idpermohonan,"2",userIdNeg);
	    	listHMAgih_INFO_P(idpermohonan,"1",userIdNeg);
	    	listHMAgih_INFO_PT_ALL(idpermohonan,"2",userIdNeg);
	    	listHMAgih_INFO_P_ALL(idpermohonan,"1",userIdNeg);
	    	
	    	
    		setupPage(session,action,listPageDepan);
    		myLogger.info(" VM AGIHAN : " +vm);
    		
    		
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
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
	
	//penambahan yati
	@SuppressWarnings({ "unchecked", "static-access" })
	private String nameAndIdPengarah(String id_ppengarah) throws Exception {

		Vector dataNamaPengarahP = new Vector();
		dataNamaPengarahP.clear();

		// GET NAMA PENGARAH DAN ID PENGARAH
		String nama_pengarahp = "";
		String id_pengarahp = "";
		dataNamaPengarahP = model.setListEmel(id_ppengarah);
		if (dataNamaPengarahP.size() != 0) {
			Hashtable npp = (Hashtable) dataNamaPengarahP.get(0);
			nama_pengarahp = (String) npp.get("nama_pengarah");
			id_pengarahp = (String) npp.get("user_id");			
		}
		context.put("nama_pengarah", nama_pengarahp);

		return id_pengarahp;

	}// close pengarah
	
	//penambahan yati
	@SuppressWarnings({ "unchecked", "static-access" })
	private String nameAndIdPenolong(String id_penolong) throws Exception {

		Vector dataNamaPenolong = new Vector();
		dataNamaPenolong.clear();

		// GET NAMA PENGARAH DAN ID PENGARAH
		String nama_penolongp = "";
		String id_penolongp = "";
		dataNamaPenolong = model.setListEmelPenolong(id_penolong);
		if (dataNamaPenolong.size() != 0) {
			Hashtable np = (Hashtable) dataNamaPenolong.get(0);
			nama_penolongp = (String) np.get("nama_pengarah");
			id_penolongp = (String) np.get("user_id");			
		}
		context.put("nama_penolong", nama_penolongp);

		return id_penolongp;

	}// close penolong
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt,String newSubUrusan) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		//modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1497");
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,newSubUrusan);
		
	}//close updateSuburusanStatusFailPPT
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanAgihanLayer2(HttpSession session,String id_permohonan,String id_status) throws Exception {
		
		Hashtable h = new Hashtable();
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		
		h.put("socPegawaiPP", getParam("socPegawai"));
    	h.put("txdTarikhAgihanLayer1", getParam("txdTarikhAgihanLayer2"));  	
    	h.put("id_user", idUser);
    	
    	//delete checkbox
    	model.deleteAgihan(getParam("socPegawai"),"2");
    	
    	String[] cbsemaks = request.getParameterValues("cbsemaks");
    	if(cbsemaks!=null){
    		Db db = null;
			try {
					db = new Db();
			for (int i = 0; i < cbsemaks.length; i++) { 
				model.simpanAgihanNew(h,cbsemaks[i],"2",db);
			}	
			
			
				
			}catch (Exception re) {
		    	myLogger.error("Error: ", re);
		    	throw re;
		    	}finally {
				if(db != null) db.close();
			}
			
			if(id_status.equals("1612198")){
				//update status
				FrmAgihanTugasSek8Data.updatestatus(h,id_permohonan);
			}
		} else {
			
			Db db = null;
			try {
					db = new Db();
					Vector hashIdHakmiliks = model.getListHakmilikNew(id_permohonan,db);
					for (int i = 0; i < hashIdHakmiliks.size(); i++) { 
						Hashtable hash = (Hashtable) hashIdHakmiliks.get(i);
						String idHakmilik = (String)hash.get("ID_HAKMILIK");
						model.simpanAgihanNew(h,idHakmilik,"2",db);
					}
					
			}catch (Exception re) {
		    	myLogger.error("Error: ", re);
		    	throw re;
		    	}finally {
				if(db != null) db.close();
			}
			/*
			:: asal
			
			Vector hashIdHakmiliks = model.getListHakmilik(id_permohonan);
			for (int i = 0; i < hashIdHakmiliks.size(); i++) { 
				Hashtable hash = (Hashtable) hashIdHakmiliks.get(i);
				String idHakmilik = (String)hash.get("ID_HAKMILIK");
				model.simpanAgihan(h,idHakmilik,"2");
			}
			*/
			
		}
	}
	
	private String selectedTab() throws Exception{
		String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		return selectedTab;	
	}//close selectedTab
	
	@SuppressWarnings("unchecked")
	private void listHMAgih(String idpermohonan,String id_penolong) throws Exception{ 		
		model.setListAgihHM(idpermohonan,id_penolong);
 		Vector listHMAgih = model.getListAgihHM();
 		context.put("listHMAgih", listHMAgih);
 		context.put("saiz_listTanah", listHMAgih.size());
	}//close listHMAgih
	
	
	@SuppressWarnings("unchecked")
	private void listHMAgih_INFO_PT(String idpermohonan,String baris,String userIdNegeri) throws Exception{ 		
		model.setListAgihHM_INFO(idpermohonan,baris,userIdNegeri);
 		Vector listHMAgih_INFO = model.getListAgihHM_INFO();
 		context.put("listHMAgih_PT_INFO", listHMAgih_INFO);		
	}//close listHMAgih
	
	
	@SuppressWarnings("unchecked")
	private void listHMAgih_INFO_P(String idpermohonan,String baris,String userIdNegeri) throws Exception{ 		
		model.setListAgihHM_INFO(idpermohonan,baris,userIdNegeri);
 		Vector listHMAgih_INFO = model.getListAgihHM_INFO();
 		context.put("listHMAgih_P_INFO", listHMAgih_INFO);		
	}//close listHMAgih
	
	
	@SuppressWarnings("unchecked")
	private void listHMAgih_INFO_PT_ALL(String idpermohonan,String baris,String userIdNegeri) throws Exception{ 		
		model.setListAgihHM_INFO_ALL(idpermohonan,baris,userIdNegeri);
 		Vector listHMAgih_INFO_ALL = model.getListAgihHM_INFO_ALL();
 		context.put("listHMAgih_INFO_PT_ALL", listHMAgih_INFO_ALL);		
	}//close listHMAgih
	
	
	@SuppressWarnings("unchecked")
	private void listHMAgih_INFO_P_ALL(String idpermohonan,String baris,String userIdNegeri) throws Exception{ 		
		model.setListAgihHM_INFO_ALL(idpermohonan,baris,userIdNegeri);
 		Vector listHMAgih_INFO_ALL = model.getListAgihHM_INFO_ALL();
 		context.put("listHMAgih_INFO_P_ALL", listHMAgih_INFO_ALL);		
	}//close listHMAgih
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanAgihan(HttpSession session,String id_permohonan,String id_status,String id_fail,String id_suburusanstatusfailppt) throws Exception {
		
		Hashtable h = new Hashtable();
		String idUser = (String)session.getAttribute("_ekptg_user_id");
		
		h.put("socPegawaiPP", getParam("socPegawaiPP"));
    	h.put("txdTarikhAgihanLayer1", getParam("txdTarikhAgihanLayer1"));  	
    	h.put("id_user", idUser);
    	
    	//delete checkbox
    	model.deleteAgihan(getParam("socPegawaiPP"),"1");
    	
    	
    	String[] cbsemaks = request.getParameterValues("cbsemaksX");
    	if(cbsemaks!=null){
			//for (int i = 0; i < cbsemaks.length; i++) { 
				//model.simpanAgihan(h,cbsemaks[i],"1");
				
				Db db = null;
				try {
						db = new Db();
				for (int i = 0; i < cbsemaks.length; i++) { 
					model.simpanAgihanNew(h,cbsemaks[i],"1",db);
				}
					
				}catch (Exception re) {
			    	myLogger.error("Error: ", re);
			    	throw re;
			    	}finally {
					if(db != null) db.close();
			    	}
			//}
			
			if(id_status.equals("16")){
				//update status
				FrmAgihanTugasSek8Data.updatestatusAgihanPentadbir(h,id_permohonan);
				updateSuburusanStatusFailPPT(session, id_permohonan, id_fail,id_suburusanstatusfailppt,"161284882");
				//updateSuburusanStatusFailPPT(session,id_permohonan,id_fail,"161284882");
			}
		}
 
	}
	

	
	private void getAndSetValue() throws Exception{ 
		context.put("txdTarikhAgihanLayer1", getParam("txdTarikhAgihanLayer1"));
	}//close getAndSetValue
	
	private void getAndSetValueLayer2() throws Exception{ 
		context.put("txdTarikhAgihanLayer2", getParam("txdTarikhAgihanLayer1"));
	}//close getAndSetValue
	
	private void dropdown(String id_projeknegeri, String id_ppengarah,String id_penolong) throws Exception{ 
		context.put("selectPPengarah",HTML.SelectPPengarahPPTByNegeri(id_projeknegeri,"socPegawaiPP",Utils.parseLong(id_ppengarah),null,"style=width:325px onChange=\"onChangePP()\" "));
	    context.put("selectPegawai",HTML.SelectPegawaiPPTByNegeriExceptPPdanPgh(id_projeknegeri,"socPegawai",Utils.parseLong(id_penolong),null,"style=width:325px onChange=\"onChangeLayer2()\" "));
	}//close dropdown
	
	@SuppressWarnings("unchecked")
	private void listHM(String idpermohonan,String id_ppengarah, String y) throws Exception{ 
		//data & list maklumat tanah
		model.setListMaklumatTanah(idpermohonan,id_ppengarah,"");
 		Vector listMaklumatTanah = model.getListMaklumatTanah();
 		context.put("listHakmilik", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
	}//close listHM
	
	private void defaultValue() throws Exception{ 
		context.put("mode","");
		context.put("isEdit","");
		context.put("showCB","no");
		
		context.put("nofail", "");
		context.put("carianTarikh", "");
		context.put("carianStatus", "");
	}//closedefaultValue
	
	private void currentDate() throws Exception{ 
		String now = "";
		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		context.put("currentDATE",now);
		context.put("txdTarikhAgihanLayer1",now);
		context.put("txdTarikhAgihanLayer2",now);		
	}//close currentDate
	
	@SuppressWarnings("unchecked")
	private String IdSubUrusanStatusFail(String idpermohonan) throws Exception{  	
		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
		Vector getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}
		
		return id_suburusanstatusfailppt;
	}//close IdSubUrusanStatusFail
	
	private void anchor(String ScreenLocation, String CursorPoint) throws Exception{  	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
	}//close anchor
	
	private void paging(String flagPaging) throws Exception{
		/*
		if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "3");
    	}
    	*/	
		context.put("paging", "3");
	}//close paging
	
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmAgihanTugasSek8Data.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
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
	
	//function email PENGARAH-penambahan yati 17/1/2016
	 public void hantarEmel(String content,String subjek,String nofail,
			 String nama_projek, String tarikh_permohonan, String nama_kementerian,String id_pengarahP) throws Exception {
		 
			Vector checkEmailPengarah = new Vector();
			checkEmailPengarah.clear();
			System.out.println("id dataListPengarah :::"+id_pengarahP);

			// check email (pengarah)
			checkEmailPengarah = myInfo.checkEmail(id_pengarahP);
			String emelPengarah = "";
			if (checkEmailPengarah.size() != 0) {
				Hashtable ceP = (Hashtable) checkEmailPengarah.get(0);
				emelPengarah = (String) ceP.get("EMEL");
			}

			System.out.println("emel ::"+emelPengarah);
			
			EmailProperty pro = EmailProperty.getInstance();
			//String EMAIL_HOST = pro.getHost_GM();
			EmailSender email = EmailSender.getInstance();
		
		//myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
		//myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
		email.FROM = pro.getFrom();
		email.SUBJECT = subjek;
		email.MESSAGE = "<html><table><tr><td>No.Fail</td><td>:</td><td>"+nofail+"</td></tr>" +
				"<tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
				"<tr><td>Tarikh Permohonan</td><td>:</td><td>"+tarikh_permohonan+"</td></tr>" +
				"<tr><td>Nama Kementerian</td><td>:</td><td>"+nama_kementerian+"</td></tr>" +
				"<tr><td>&nbsp;</td><td>&nbsp;</td><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
				"</table></html>" ;
		email.RECIEPIENT = emelPengarah;
		
		myLogger.info(" ---------- email :"+email);	
		//email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";	
		email.TO_CC = new String[1];		
		email.TO_CC[0] = "testingetapp@gmail.com";
		email.sendEmail();
		
	 } //close hantar emel
	 
	 //function-pentadbir tanad
	 public void hantarEmelPen(String content,String subjek,String nofail,
			 String nama_projek, String tarikh_permohonan, String nama_kementerian,String id_penolongP) throws Exception {
		 
			Vector checkEmailPenolong = new Vector();
			checkEmailPenolong.clear();
			System.out.println("id dataListPenolong :::"+id_penolongP);

			// check email (pengarah)
			checkEmailPenolong = myInfo.checkEmail(id_penolongP);
			String emelPenolong = "";
			if (checkEmailPenolong.size() != 0) {
				Hashtable ceP = (Hashtable) checkEmailPenolong.get(0);
				emelPenolong = (String) ceP.get("EMEL");
			}

			System.out.println("emel ::"+emelPenolong);
			
			EmailProperty pro = EmailProperty.getInstance();
			//String EMAIL_HOST = pro.getHost_GM();
			EmailSender email = EmailSender.getInstance();
		
		//myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
		//myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
		email.FROM = pro.getFrom();
		email.SUBJECT = subjek;
		email.MESSAGE = "<html><table><tr><td>No.Fail</td><td>:</td><td>"+nofail+"</td></tr>" +
				"<tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
				"<tr><td>Tarikh Permohonan</td><td>:</td><td>"+tarikh_permohonan+"</td></tr>" +
				"<tr><td>Nama Kementerian</td><td>:</td><td>"+nama_kementerian+"</td></tr>" +
				"<tr><td>&nbsp;</td><td>&nbsp;</td><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
				"</table></html>" ;
		email.RECIEPIENT = emelPenolong;
		
		myLogger.info(" ---------- email :"+email);	
		//email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";	
		email.TO_CC = new String[1];		
		email.TO_CC[0] = "testingetapp@gmail.com";
		email.sendEmail();
		
	 } //close hantar emel

}//close class
