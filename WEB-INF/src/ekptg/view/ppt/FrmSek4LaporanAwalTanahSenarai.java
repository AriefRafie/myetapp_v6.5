package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek4LaporanAwalTanahData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmSek4LaporanAwalTanahSenarai extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek4LaporanAwalTanahSenarai.class);
	
	//model
	FrmSek4LaporanAwalTanahData model = new FrmSek4LaporanAwalTanahData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for paging
    	String action = getParam("action");
    	
    	String vm = "";
    	String noLOT = "";
    	String idpegawai = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector dataLaporanTanah = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
    	dataLaporanTanah.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSek4LaporanAwalTanahSenarai.jsp";
    	String mainScreen = "app/ppt/frmSek4LaporanAwalTanah.jsp";
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user);
    	
    	//default list
    	listPageDepan = model.getListPermohonan(userIdNeg);
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//method
    	context.put("Utils", new ekptg.helpers.Utils());
    	context.put("Util", new lebah.util.Util());
    	
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
    	
		//header
		String id_status = "";
		String id_fail = "";
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
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
    	}

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
		
		
		//paging
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "3");
    	}
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("tambahLaporan".equals(submit)){
    		
    		//form validation
    		context.put("mode","new");
    		
    		//list maklumat tanah *get total lot
    		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
     		Vector listMaklumatTanah = modelUPT.getListMaklumatTanah();
     		context.put("listMaklumatTanah", listMaklumatTanah);
     		context.put("totalHM", listMaklumatTanah.size());
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("simpanLaporan".equals(submit2)){
        	
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
        		
        		if (doPost.equals("true")) {
    				//simpan laporan
    				simpanLaporan(session);
    				if(id_status.equals("148")){
    					updateStatus(session);
    				}   				
    				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
    			}
        		
        		//data laporan tanah
        		dataLaporanTanah(session,idpermohonan);
        		
        	}//close simpanLaporan
        	
			//screen
    		vm = mainScreen;
		    
    	}//close  
    	
    	else 
    	if("semakLaporan".equals(submit)){
    			
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//resetValue
    		resetValue();
    		
    		//data laporan tanah
    		dataLaporanTanah(session,idpermohonan);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniLaporan".equals(submit2)){
        		
        		//form validation
        		context.put("isEdit","yes");
        		
        		//list maklumat tanah *get total lot
        		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
         		Vector listMaklumatTanah = modelUPT.getListMaklumatTanah();
         		context.put("totalHM", listMaklumatTanah.size());
         		
        		model.setDataLaporanTanah(idpermohonan);
        		dataLaporanTanah = model.getDataLaporanTanah();
        		

        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("updateLaporan".equals(submit3)){
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		if (doPost.equals("true")) {
        				//update laporan
        				updateLaporan(session);
        			}
            		
            		//data laporan tanah
            		dataLaporanTanah(session,idpermohonan);
            		
            	}//close updateLaporan
        		
        	}//close kemaskiniLaporan
    		
    		//screen
    		vm = mainScreen;
    		
    	}//close semakLaporan
    	
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
    		this.context.put("selectedTab",selectedTab);
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
	
	private void resetValue() throws Exception{
    	
		context.put("totalHM", "");
		
		context.put("txtKemudahan", "");
		context.put("txtSimpanMelayu", "");
		context.put("txtLainlain", "");
		context.put("txtKawasanMajlis", "");
		context.put("txtNoSyit", "");
		context.put("txdTarikhMula", "");
		context.put("txdTarikhTamat", "");
		context.put("txdTarikhLawat", "");
		context.put("txtBilKeseluruhan", "");
		context.put("txtLuasKeseluruhan", "");
		context.put("txtPerihal", "");
		context.put("txtLokasi", "");
		context.put("txtSyor", "");
		context.put("txtNoPeta", "");
		context.put("txtLaporanTanah", "");
		context.put("txtPendahuluan", "");
		context.put("sorDropdownUnitAsal", "");
		
	}//close resetValue
	
	private void ListCarian(HttpSession session, String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmSek4LaporanAwalTanahData.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	
	@SuppressWarnings("unchecked")
	private void simpanLaporan(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", getParam("id_permohonan"));
		
		h.put("txtNoPeta", getParam("txtNoPeta"));
		h.put("txtLaporanTanah", getParam("txtLaporanTanah"));
		
		h.put("txtNoSyit", getParam("txtNoSyit"));
		h.put("txtBilKeseluruhan", getParam("txtBilKeseluruhan"));
		h.put("socKategoriTanah", "");
		h.put("txtLuasKeseluruhan", getParam("txtLuasKeseluruhan"));
		h.put("txdTarikhMula", getParam("txdTarikhMula"));
		h.put("txdTarikhTamat", getParam("txdTarikhTamat"));
		h.put("txdTarikhLawat", getParam("txdTarikhLawat"));
		
		h.put("txtKawasanMajlis", getParam("txtKawasanMajlis"));
		h.put("txtSimpanMelayu", getParam("txtSimpanMelayu"));
		h.put("txtLainlain", getParam("txtLainlain"));
		h.put("txtUlasan", getParam("txtUlasan"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtRupabumi", getParam("txtRupabumi"));
		h.put("txtPerihal", getParam("txtPerihal"));
		h.put("txtKemudahan", getParam("txtKemudahan"));
		h.put("txtPendahuluan", getParam("txtPendahuluan"));
		
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek4LaporanAwalTanahData.simpanLaporan(h);
		
	}//close simpanLaporan
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1450");
		
	}//close updateSuburusanStatusFailPPT
	
	@SuppressWarnings("unchecked")
	private void updateStatus(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", getParam("id_permohonan"));	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek4LaporanAwalTanahData.updateStatus(h);
		
	}//close updateStatus
	
	@SuppressWarnings("unchecked")
	private void updateLaporan(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		//add/update validation for paging
		String idTanahUmum = getParam("id_tanahumum");
		
		h.put("id_tanahumum", getParam("id_tanahumum"));
		h.put("id_permohonan", getParam("id_permohonan"));
		
		h.put("txtNoPeta", getParam("txtNoPeta"));
		h.put("txtLaporanTanah", getParam("txtLaporanTanah"));
		
		h.put("txtNoSyit", getParam("txtNoSyit"));
		h.put("txtBilKeseluruhan", getParam("txtBilKeseluruhan"));
		h.put("socKategoriTanah", "");
		h.put("txtLuasKeseluruhan", getParam("txtLuasKeseluruhan"));
		h.put("txdTarikhMula", getParam("txdTarikhMula"));
		h.put("txdTarikhTamat", getParam("txdTarikhTamat"));
		h.put("txdTarikhLawat", getParam("txdTarikhLawat"));
		
		h.put("txtKawasanMajlis", getParam("txtKawasanMajlis"));
		h.put("txtSimpanMelayu", getParam("txtSimpanMelayu"));
		h.put("txtLainlain", getParam("txtLainlain"));
		h.put("txtUlasan", getParam("txtUlasan"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtRupabumi", getParam("txtRupabumi"));
		h.put("txtPerihal", getParam("txtPerihal"));
		h.put("txtKemudahan", getParam("txtKemudahan"));
		h.put("txtPendahuluan", getParam("txtPendahuluan"));
		
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		if(idTanahUmum!=""){
			FrmSek4LaporanAwalTanahData.updateLaporan(h);
		}else{
			FrmSek4LaporanAwalTanahData.simpanLaporan(h);
		}
		
	}//close updateLaporan
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataLaporanTanah(HttpSession session,String idpermohonan) throws Exception{
    	
		model.setDataLaporanTanah(idpermohonan);
		Vector dataLaporanTanah = model.getDataLaporanTanah();
		String id_tanahumum = "";
		//String id_jenistanah = "";
		if(dataLaporanTanah.size()!=0){
			Hashtable dlt = (Hashtable)dataLaporanTanah.get(0);
			id_tanahumum = (String)dlt.get("id_tanahumum").toString();
		}
		context.put("dataLaporanTanah", dataLaporanTanah);
		
		//id
		context.put("id_tanahumum", id_tanahumum);
		
	}//close dataLaporanTanah

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
