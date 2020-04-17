package ekptg.view.ppt;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmAgihanTugasSek4Data;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmAgihanTugasSek4 extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmAgihanTugasSek4.class);
	
	//model
	FrmAgihanTugasSek4Data model = new FrmAgihanTugasSek4Data();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for paging
    	String action = getParam("action");
    
    	String vm = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmAgihanTugasSek4Senarai.jsp";
    	String mainScreen = "app/ppt/frmAgihanTugasSek4.jsp";
    	
    	
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
    	//paging
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "2");
    	}
    	
		//header
		String id_status = "";
		String id_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			id_fail = (String)dh.get("id_fail");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}

		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}
		
		//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user);
    	
		//get current date
		String now = "";
		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		context.put("currentDATE",now);
		
		//default list
    	listPageDepan = model.getListPermohonan(userIdNeg);
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange","no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("tambahAgihan".equals(submit)){
    		
    		//form validation
    		context.put("mode","new");
    		
    		//reset value
    		context.put("lblJawatanPegawai", "");
    		context.put("txtCatatan", "");
    		
    		//dropdown
    		context.put("selectPegawai",HTML.SelectPegawaiPPTByNegeri(userIdNeg,"socPegawai",null,null,"style=width:325px onChange=\"doChangePegawai()\""));
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("doChangePegawai".equals(submit2)){
        	
        		//get data by pegawai
        		getAndSetValue("new",userIdNeg);
        		
        	}//close doChangePegawai
        	
        	else if("simpanAgihan".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			simpanAgihan(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
        		}
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
        		
        		//update header and status
        		header.setDataHeader(idpermohonan);
        		dataHeader = header.getDataHeader();
        		context.put("dataHeader", dataHeader);
        		if(dataHeader.size()!=0){
        			Hashtable dh = (Hashtable) dataHeader.get(0);
        			id_status = (String)dh.get("id_status");
        		}
        		
        		//data agihan
        		dataAgihan(idpermohonan,"disabled",userIdNeg);
        		
        	}//close simpanAgihan
        	
    		//screen
    		vm = mainScreen;
    		
    	}//close tambahAgihan  
    	
    	else 
    	if("viewAgihan".equals(submit)){
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//data agihan
    		dataAgihan(idpermohonan,"disabled",userIdNeg);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniAgihan".equals(submit2)){
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
        		
        		//data agihan
        		dataAgihan(idpermohonan,"enabled",userIdNeg);
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("doChangePegawaiUpdate".equals(submit3)){
            	
            		//onchange validation
            		context.put("onchange","yes");
            		
            		//get data by pegawai
            		getAndSetValue("view",userIdNeg);
            		
            	}//close doChangePegawaiUpdate
            	
            	else if("updateAgihan".equals(submit3)){
            		
            		updateAgihan(session);
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data agihan
            		dataAgihan(idpermohonan,"disabled",userIdNeg);
            		
            	}//close updateAgihan
        		
            	
        	}//close kemaskiniAgihan
        	
        	
    		//screen
    		vm = mainScreen;
    		
    	}//close viewAgihan 

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
	    	context.put("id_suburusanstatusfailppt", id_suburusanstatusfailppt);
	    	
    		setupPage(session,action,listPageDepan);
    		return vm;
    		
	    }//close public template
	
	
	
	
//--METHOD--//	
	
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
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmAgihanTugasSek4Data.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanAgihan(HttpSession session,String idpermohonan,String idfail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", idpermohonan);
		h.put("id_fail", idfail);
		
		h.put("socPegawai", getParam("socPegawai"));
		h.put("txtCatatan", getParam("txtCatatan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1451");
		
		myLogger.info("socPegawai : "+getParam("socPegawai"));
		myLogger.info("id_user : "+session.getAttribute("_ekptg_user_id"));
		
		FrmAgihanTugasSek4Data.simpanAgihan(h);
		
	}//close simpanMaklumat
	
	@SuppressWarnings("unchecked")
	private void updateAgihan(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_tugas", getParam("id_tugas"));
	
		h.put("socPegawai", getParam("socPegawai"));
		h.put("txtCatatan", getParam("txtCatatan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmAgihanTugasSek4Data.updateAgihan(h);
		
	}//close simpanMaklumat
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataAgihan(String idpermohonan,String disability,String userIdNeg) throws Exception{
    	
		Vector dataAgihan = new Vector();
		dataAgihan.clear();
		
		//get jawatan
		model.setDataAgihan(idpermohonan);
		dataAgihan = model.getDataAgihan();
		String id_pegawaipenerima = "";
		String id_tugas = "";
		if(dataAgihan.size()!=0){
			Hashtable da = (Hashtable)dataAgihan.get(0);
			id_pegawaipenerima = (String)da.get("id_pegawaipenerima");
			id_tugas = (String)da.get("id_tugas");
		}
		
		//data and id
		context.put("id_tugas",id_tugas);
		context.put("dataAgihan",dataAgihan);
		
		String mode = "";
		if(disability.equals("disabled")){
			mode = "disabled class=disabled";
		}else{
			mode = "";
		}
		
		//dropdown
		context.put("selectPegawai",HTML.SelectPegawaiPPTByNegeri(userIdNeg,"socPegawai",Utils.parseLong(id_pegawaipenerima),null,"style=width:325px "+mode+" onChange=\"doChangePegawaiUpdate()\""));
		
		
	}//close dataAgihan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetValue(String mode,String userIdNeg) throws Exception{
    	
		Vector dataPegawai = new Vector();
		dataPegawai.clear();
		
		String idPegawai = getParam("socPegawai");
		
		//dropdown
		if(mode.equals("new")){
			context.put("selectPegawai",HTML.SelectPegawaiPPTByNegeri(userIdNeg,"socPegawai",Utils.parseLong(idPegawai),null,"style=width:325px onChange=\"doChangePegawai()\""));
		}else{
			context.put("selectPegawai",HTML.SelectPegawaiPPTByNegeri(userIdNeg,"socPegawai",Utils.parseLong(idPegawai),null,"style=width:325px onChange=\"doChangePegawaiUpdate()\""));
		}
		
		//get jawatan
		model.setDataPegawai(idPegawai);
		dataPegawai = model.getDataPegawai();
		String jawatan = "";
		if(dataPegawai.size()!=0){
			Hashtable dp = (Hashtable)dataPegawai.get(0);
			jawatan = (String)dp.get("jawatan");
		}
		
		//get and set
		context.put("txtCatatan", getParam("txtCatatan"));
		context.put("lblJawatanPegawai", jawatan);
		
	}//close getAndSetValue
	
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
