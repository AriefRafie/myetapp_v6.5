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
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek8PenandaanKawasanData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.SementaraPenandaanKawasan;
/*
 * @author
 * NORZAILY BINTI JASMI
 */

public class FrmSementaraPenandaanKawasan extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSementaraPenandaanKawasan.class);

	// MODEL SEKSYEN 8
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUPTSek8PenandaanKawasanData model = new FrmUPTSek8PenandaanKawasanData();
	PPTHeader header = new PPTHeader();

	// MODEL SEMENTARA
	SementaraPenandaanKawasan modelSementara = new SementaraPenandaanKawasan();


	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{

		HttpSession session = request.getSession();

    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user);

		//command for paging
    	String action = getParam("action");

    	//get user login detail
    	//String id_user = (String) session.getAttribute("_ekptg_user_id");

    	String vm = "";
    	String noLOT = "";
    	String idpegawai = "";


    	Vector listPageDepan = new Vector();
    	Vector dataTanda = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataMaklumatTanah = new Vector();

    	dataMaklumatTanah.clear();
    	getIdSuburusanstatusfail.clear();
    	dataTanda.clear();
    	listPageDepan.clear();

    	// SCREEN JSP SEKSYEN 8
//    	String listdepan = "app/ppt/frmUPTSek8PenandaanKawasanSenarai.jsp";
//    	String mainscreen = "app/ppt/frmUPTSek8PenandaanKawasan.jsp";
//    	String listHMscreen = "app/ppt/frmUPTSek8PenandaanKawasanListHM.jsp";

    	String listdepan = "app/ppt/frmSementaraPenandaanKawasanSenarai.jsp";
    	String mainscreen = "app/ppt/frmSementaraPenandaanKawasan.jsp";
    	String listHMscreen = "app/ppt/frmSementaraPenandaanKawasanListHM.jsp";
    	
    	Db dbx = null;
		try {
			dbx = new Db();
	    	
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik",dbx)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT",dbx);
	    	}
		}finally {
			if (dbx != null)
				dbx.close();
		}


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
    		context.put("paging", "12");
    	}

		//header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			flagSegera = dh.get("flag_segera").toString();
			id_fail = (String)dh.get("id_fail");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}

		context.put("flagSegera",flagSegera);

		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}

		//id Hakmilik
		String idHakmilik = getParam("id_hakmilik");

		//data hakmilik
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);

		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange","no");

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

    	else if("penandaanKawasan".equals(submit)){

    		model.setDataKawasanPenandaan(idpermohonan);
     		dataTanda = model.getDataKawasanPenandaan();

     		//NEW
     		if(dataTanda.size()==0){

     			//form validation
            	context.put("mode","new");

            	//reset value
            	resetValue(session);

            	//dropdown
            	context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,null,"style=width:300px onChange=\"onchangeNegeri();\""));
            	context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:300px"));

            	String submit2 = getParam("command2");
            	myLogger.info("submit[2] : " + submit2);

            	if("onchangeNegeri".equals(submit2)){

            		String idNegeri = getParam("socNegeri");

            		//dropdown by
            		if(idNegeri!=""){
            			context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:300px"));
            		}else{
            			context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:300px"));
            		}

            		//get and set back
            		getAndSetData(session,"new");

            	}//close onchangeNegeri

            	else if("simpanPenandaan".equals(submit2)){

            		if (doPost.equals("true")) {
            			//simpan data
            			myLogger.info("ID HAKMILIK :: "+idHakmilik);
            			simpanPenandaan(session,idHakmilik);
            			//updateStatus(session);
            			updateStatusPenandaan(session);
            			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
            		}

            		//form validation
                	context.put("mode","view");
                	context.put("isEdit","no");

                	//data header (get id_status)
    	    		dataHeaderStatus(session,idpermohonan);
    	    		id_status = dataHeaderStatus(session,idpermohonan);

    	    		model.setDataKawasanPenandaan(idpermohonan);
    	     		dataTanda = model.getDataKawasanPenandaan();
    	     		String id_negeri = "";
    	     		String id_bandar = "";
    	     		if(dataTanda.size()!=0){
    	     			Hashtable dt = (Hashtable)dataTanda.get(0);
    	     			id_negeri = (String)dt.get("id_negeri");
    	     			id_bandar = (String)dt.get("id_bandar");
    	     		}

    	     		//dropdown
    	        	context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px class=disabled disabled "));
    	        	context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:300px class=disabled disabled"));

            		//data kawasan penandaan
            		dataKawasanPenandaan(session,idpermohonan);

            	}//close simpanPenandaan


     		//VIEW
     		}else{

     			//form validation
            	context.put("mode","view");
            	context.put("isEdit","no");

            	model.setDataKawasanPenandaan(idpermohonan);
         		dataTanda = model.getDataKawasanPenandaan();
         		String id_negeri = "";
         		String id_bandar = "";
         		if(dataTanda.size()!=0){
         			Hashtable dt = (Hashtable)dataTanda.get(0);
         			id_negeri = (String)dt.get("id_negeri");
         			id_bandar = (String)dt.get("id_bandar");
         		}

         		//dropdown
            	context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px class=disabled disabled "));
            	context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:300px class=disabled disabled"));

            	//data kawasan penandaan
        		dataKawasanPenandaan(session,idpermohonan);

        		String submit2 = getParam("command2");
            	myLogger.info("submit[2] : " + submit2);

            	if("kemaskiniPenandaan".equals(submit2)){

            		//form validation
                	context.put("mode","view");
                	context.put("isEdit","yes");

                	//dropdown
                	context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px onChange=\"onchangeNegeriUpdate();\""));
                	if(id_negeri!=""){
                		context.put("selectBandar",HTML.SelectBandarByNegeri(id_negeri,"socBandar",Utils.parseLong(id_bandar),"style=width:300px"));
                	}else{
                		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:300px"));
                	}

                	String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);

                	if("onchangeNegeriUpdate".equals(submit3)){

                		//onchange validation
                		context.put("onchange","yes");

                		String idNegeri = getParam("socNegeri");

                		//dropdown by
                		if(idNegeri!=""){
                			context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:300px"));
                		}else{
                			context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:300px"));
                		}

                		//get and set back
                		getAndSetData(session,"view");

                	}//close onchangeNegeriUpdate

                	else if("updatePenandaan".equals(submit3)){

                		updatePenandaan(session);

                		//form validation
                    	context.put("mode","view");
                    	context.put("isEdit","no");

                    	model.setDataKawasanPenandaan(idpermohonan);
                 		dataTanda = model.getDataKawasanPenandaan();
                 		if(dataTanda.size()!=0){
                 			Hashtable dt = (Hashtable)dataTanda.get(0);
                 			id_negeri = (String)dt.get("id_negeri");
                 			id_bandar = (String)dt.get("id_bandar");
                 		}

                 		//dropdown
                    	context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px class=disabled disabled "));
                    	context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:300px class=disabled disabled"));

                		//data kawasan penandaan
                		dataKawasanPenandaan(session,idpermohonan);

                	}//close updatePenandaan

            	}//close kemaskiniPenandaan

     		}//close NEW n VIEW

    		//screen
    		vm = mainscreen;

    	}//close

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
	    	context.put("id_hakmilik", idHakmilik);

    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;

	    }//close public template




//--METHOD--//

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

	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1519");

	}//close updateSuburusanStatusFailPPT

	@SuppressWarnings("unchecked")
	private void listHakmilik(String idpermohonan,String noLOT,String idpegawai) throws Exception{
/*
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();

		modelSementara.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 		listMaklumatTanah = modelSementara.getListMaklumatTanah();
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 		
 		*/
		context.put("saiz_listTanah", modelUPT.setListMaklumatTanah_count(idpermohonan,noLOT,idpegawai));

	}//close listHakmilik

	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{

		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");

		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);

		SementaraPenandaanKawasan.setListCarian(nofail,tarikh,status,userIdNeg);

	}//close listcarian

	private void resetValue(HttpSession session) throws Exception{

		//context.put("txtStatusTanda","");
		context.put("socStatusTanda","");
		//context.put("txtStatusLaksana","");
		context.put("socStatusLaksana","");
		context.put("txtNoRujAgensi","");
		context.put("txtNamaPegawai","");
		context.put("txdTarikhTandaDari","");
		context.put("txdTarikhTandaHingga","");
		context.put("txdTarikhLawat","");
		context.put("txdTarikhLulus","");
		context.put("txtAlamat1","");
		context.put("txtAlamat2","");
		context.put("txtAlamat3","");
		context.put("txtPoskod","");

	}//close resetValue

	private void getAndSetData(HttpSession session,String mode) throws Exception{

		String idNegeri = getParam("socNegeri");

    	if(mode.equals("new")){
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:300px onChange=\"onchangeNegeri();\""));
    	}else{
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:300px onChange=\"onchangeNegeriUpdate();\""));
    	}

    	//set
    	//context.put("txtStatusTanda", getParam("txtStatusTanda"));
    	context.put("socStatusTanda", getParam("socStatusTanda"));
    	context.put("socStatusLaksana", getParam("socStatusLaksana"));
    	context.put("txtNoRujAgensi", getParam("txtNoRujAgensi"));
    	context.put("txtNamaPegawai", getParam("txtNamaPegawai"));
    	context.put("txdTarikhTandaDari", getParam("txdTarikhTandaDari"));
    	context.put("txdTarikhTandaHingga", getParam("txdTarikhTandaHingga"));
    	context.put("txdTarikhLawat", getParam("txdTarikhLawat"));
    	context.put("txdTarikhLulus", getParam("txdTarikhLulus"));
    	context.put("txtAlamat1", getParam("txtAlamat1"));
    	context.put("txtAlamat2", getParam("txtAlamat2"));
    	context.put("txtAlamat3", getParam("txtAlamat3"));
    	context.put("txtPoskod", getParam("txtPoskod"));

	}//close getAndSetData

	@SuppressWarnings("unchecked")
	private void updateStatus(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		FrmUPTSek8PenandaanKawasanData.updateStatus(h);

	}//close updateStatus
	
	@SuppressWarnings("unchecked")
	private void updateStatusPenandaan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		FrmUPTSek8PenandaanKawasanData.updateStatusPenandaan(h);

	}//close updateStatus

	@SuppressWarnings("unchecked")
	private void dataKawasanPenandaan(HttpSession session,String idpermohonan) throws Exception{

		//data & list maklumat tanah
		model.setDataKawasanPenandaan(idpermohonan);
 		Vector dataTanda = model.getDataKawasanPenandaan();
 		String id_tandakawasan = "";
 		if(dataTanda.size()!=0){
 			Hashtable dt = (Hashtable)dataTanda.get(0);
 			id_tandakawasan = (String)dt.get("id_tandakawasan");
 		}
 		context.put("id_tandakawasan", id_tandakawasan);
 		context.put("dataTanda", dataTanda);

	}//close dataKawasanPenandaan

	@SuppressWarnings({ "unchecked", "static-access" })
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
	private void simpanPenandaan(HttpSession session,String id_hakmilik) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_hakmilik", id_hakmilik);
		h.put("id_permohonan", getParam("id_permohonan"));

		//h.put("txtStatusTanda", getParam("txtStatusTanda"));
		h.put("socStatusTanda", getParam("socStatusTanda"));
		h.put("socStatusLaksana", getParam("socStatusLaksana"));
		h.put("txtNoRujAgensi", getParam("txtNoRujAgensi"));
		h.put("txtNamaPegawai", getParam("txtNamaPegawai"));
		h.put("txdTarikhTandaDari", getParam("txdTarikhTandaDari"));
		h.put("txdTarikhTandaHingga", getParam("txdTarikhTandaHingga"));
		h.put("txdTarikhLawat", getParam("txdTarikhLawat"));
		h.put("txdTarikhLulus", getParam("txdTarikhLulus"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		FrmUPTSek8PenandaanKawasanData.simpanPenandaan(h);
//		SementaraPenandaanKawasan.simpanPenandaan(h);


	}//close simpanPenandaan

	@SuppressWarnings("unchecked")
	private void updatePenandaan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_tandakawasan", getParam("id_tandakawasan"));
		//h.put("txtStatusTanda", getParam("txtStatusTanda"));
		h.put("socStatusTanda", getParam("socStatusTanda"));
		h.put("socStatusLaksana", getParam("socStatusLaksana"));
		h.put("txtNoRujAgensi", getParam("txtNoRujAgensi"));
		h.put("txtNamaPegawai", getParam("txtNamaPegawai"));
		h.put("txdTarikhTandaDari", getParam("txdTarikhTandaDari"));
		h.put("txdTarikhTandaHingga", getParam("txdTarikhTandaHingga"));
		h.put("txdTarikhLawat", getParam("txdTarikhLawat"));
		h.put("txdTarikhLulus", getParam("txdTarikhLulus"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		FrmUPTSek8PenandaanKawasanData.updatePenandaan(h);

	}//close simpanPenandaan

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

}//close class
