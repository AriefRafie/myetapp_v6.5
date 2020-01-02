package ekptg.view.ppt;

/*
 * @author 
 * NORZAILY BINTI JASMI
 */
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8JabatanTeknikalData;
import ekptg.model.ppt.FrmSek8LaporanAwalTanahData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.SementaraJabatanTeknikal;
import ekptg.ppt.helpers.HTMLPPT;

public class FrmSementaraJabatanTeknikal extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSementaraJabatanTeknikal.class);
	
	// MODEL SEKSYEN 8
	FrmSek8LaporanAwalTanahData modelLaporan = new FrmSek8LaporanAwalTanahData();
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmSek8JabatanTeknikalData model = new FrmSek8JabatanTeknikalData();
	PPTHeader header = new PPTHeader();
	
	// MODEL SEMENTARA
	SementaraJabatanTeknikal modelSementara = new SementaraJabatanTeknikal();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for paging
    	String action = getParam("action");
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user); 
	
    	String vm = "";
    	String namaPej = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector dataJabatan = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
    	dataJabatan.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSementaraLaporanAwalTanahSuratSenarai.jsp";
    	String mainscreen = "app/ppt/frmSementaraLaporanAwalTanahSurat.jsp";
    	String screenjabatan = "app/ppt/frmSementaraLaporanAwalTanahSuratTambah.jsp";
    	
    	//default list
    	listPageDepan = modelSementara.getListPermohonan(userIdNeg);
    	
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
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "7");
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
		
		//default
		context.put("mode","");
		context.put("isEdit","");		
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("semakSenaraiJabatan".equals(submit)){
    		
    		namaPej = getParam("carianPejabat");
    		context.put("carianPejabat",namaPej.trim());
    		
    		//list jabatan teknikal
    		listJabatan(session,idpermohonan,namaPej,userIdNeg);
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close  
    	
    	else 
    	if("tambahJabatan".equals(submit)){
    			
    		//form validation
    		context.put("mode","new");
    		
    		namaPej = getParam("carianPejabat2");
    		context.put("carianPejabat2",namaPej.trim());
    		
    		//list jabatan teknikal
    		listJabatan(session,idpermohonan,namaPej,userIdNeg);
    		
    		//dropdown
    		//context.put("selectJabatan",HTMLPPT.SelectJabatanTeknikalPPT("socJabatan",null,"id=socJabatan style=width:auto"));
    		context.put("selectJabatan",HTMLPPT.SelectJabatanTeknikalPPT(userIdNeg,"socJabatan",null,null,"style=width:auto"));
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("simpanJabatan".equals(submit2)){
        	
        		if (doPost.equals("true")) {
        			//simpan jabatan
        			simpanJabatan(session,id_status); 
        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
        		}
        		
        		//list jabatan teknikal
        		listJabatan(session,idpermohonan,namaPej,userIdNeg);
        		
        	}//close simpanJabatan
        	
    		//screen
    		vm = screenjabatan;
    		
    	}//close tambahJabatan
    	
    	else 
        if("viewJabatan".equals(submit)){
        		
        	//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		namaPej = getParam("carianPejabat2");
    		context.put("carianPejabat2",namaPej.trim());
    		
    		String id_ulasanteknikal = getParam("id_ulasanteknikal");
    		context.put("id_ulasanteknikal", id_ulasanteknikal);
    		
    		//list jabatan teknikal
    		listJabatan(session,idpermohonan,namaPej,userIdNeg);
    		
    		//data Pejabat Teknikal
    		model.setDataJabatan(id_ulasanteknikal);
        	dataJabatan = model.getDataJabatan();       	
        	String id_jabatanteknikal = "";
    		if(dataJabatan.size()!=0){
    			Hashtable dj = (Hashtable)dataJabatan.get(0);
    			id_jabatanteknikal = (String)dj.get("id_jabatanteknikal");
    		}
    		context.put("dataJabatan", dataJabatan);
    		
    		//dropdown
    		context.put("selectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(id_jabatanteknikal),"id=socJabatan style=width:auto disabled class=disabled"));
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniJabatan".equals(submit2)){
        		
        		//form validation
        		context.put("isEdit","yes");
        		
        		//dropdown
        		context.put("selectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(id_jabatanteknikal),"id=socJabatan style=width:auto"));
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("updateJabatan".equals(submit3)){
        		
            		updateJabatan(session);
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		id_ulasanteknikal = getParam("id_ulasanteknikal");
            		context.put("id_ulasanteknikal", id_ulasanteknikal);
            		
            		//data Pejabat Teknikal
            		dataPejabatTeknikal(session,id_ulasanteknikal);
            		
            		//list jabatan teknikal
            		listJabatan(session,idpermohonan,namaPej,userIdNeg);
            		
            	}//close updateJabatan
            	
        	}//close kemaskiniJabatan
    		
    		//screen
    		vm = screenjabatan;
    		
        }//close viewJabatan
    	
        else 
    	if("hapusJabatan".equals(submit)){
    			
    		hapusJabatan(session);
    		
    		//list jabatan teknikal
    		listJabatan(session,idpermohonan,namaPej,userIdNeg);
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close hapusJabatan
    	
    	else 
    	if("seterusnya".equals(submit)){
        		
    		if (doPost.equals("true")) {
    			//seterusnya (tukar status)
    			seterusnya(session); 
    		}
    		
    		listPageDepan = modelSementara.getListPermohonan(userIdNeg);
    		
    		context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");
			
    		//screen
    		vm = listdepan;
    		    
        }//close seterusnya
    	
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = modelSementara.getListCarian(userIdNeg);
	
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else{	
    		
    		listPageDepan = modelSementara.getListPermohonan(userIdNeg);
    		
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
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1515");
		
	}//close updateSuburusanStatusFailPPT



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
	
	private void seterusnya(HttpSession session) throws Exception{
    	
		String idpermohonan = getParam("id_permohonan");
		String id_user = (String) session.getAttribute("_ekptg_user_id");
			
		FrmSek8JabatanTeknikalData.seterusnya(idpermohonan,id_user);
      
	}//close seterusnya

	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		SementaraJabatanTeknikal.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void listJabatan(HttpSession session,String idpermohonan,String namaPej,String userIdNegeri) throws Exception{
    	
		model.setListJabatan(idpermohonan,namaPej,userIdNegeri);
    	Vector listJabatan = model.getListJabatan();
    	context.put("listJabatanTeknikal", listJabatan);
		context.put("saiz_jabatan", listJabatan.size());
      
	}//close listJabatan

	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataPejabatTeknikal(HttpSession session,String id_ulasanteknikal) throws Exception{
    	
		//data Pejabat Teknikal
		model.setDataJabatan(id_ulasanteknikal);
    	Vector dataJabatan = model.getDataJabatan();       	
    	String id_jabatanteknikal = "";
		if(dataJabatan.size()!=0){
			Hashtable dj = (Hashtable)dataJabatan.get(0);
			id_jabatanteknikal = (String)dj.get("id_jabatanteknikal");
		}
		context.put("dataJabatan", dataJabatan);
		
		//dropdown
		context.put("selectJabatan",HTML.SelectJabatanTeknikal("socJabatan",Utils.parseLong(id_jabatanteknikal),"id=socJabatan style=width:auto disabled class=disabled"));
	
	}//close listJabatan
	
	@SuppressWarnings("unchecked")
	private void simpanJabatan(HttpSession session,String idstatus) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		
		h.put("socJabatan", getParam("socJabatan"));
		h.put("txtBilSurat", getParam("txtBilSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txtTempoh", getParam("txtTempoh"));
		h.put("txtPerihal", getParam("txtPerihal"));
		h.put("txtBilSuratJT", getParam("txtBilSuratJT"));
		h.put("txtNoRujukan", getParam("txtNoRujukan"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJT"));
		h.put("socKeputusan", getParam("socKeputusan"));
		h.put("txtUlasan", getParam("txtUlasan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		if(idstatus.equals("147")){
			FrmSek8JabatanTeknikalData.updateStatus((String)session.getAttribute("_ekptg_user_id"),getParam("id_permohonan"),"22");
    	}
		
		FrmSek8JabatanTeknikalData.simpanJabatan(h);
  
	}//close simpanJabatan
	
	@SuppressWarnings("unchecked")
	private void updateJabatan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_ulasanteknikal", getParam("id_ulasanteknikal"));
		
		h.put("socJabatan", getParam("socJabatan"));
		h.put("txtBilSurat", getParam("txtBilSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txtTempoh", getParam("txtTempoh"));
		h.put("txtPerihal", getParam("txtPerihal"));
		h.put("txtBilSuratJT", getParam("txtBilSuratJT"));
		h.put("txtNoRujukan", getParam("txtNoRujukan"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJT"));
		h.put("socKeputusan", getParam("socKeputusan"));
		h.put("txtUlasan", getParam("txtUlasan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8JabatanTeknikalData.updateJabatan(h);
  
	}//close updateJabatan
	
	private void hapusJabatan(HttpSession session) throws Exception{
 
		String id_ulasanteknikal = getParam("id_ulasanteknikal");
			
		FrmSek8JabatanTeknikalData.hapusJabatan(id_ulasanteknikal);
      
	}//close hapusJabatan
	
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
