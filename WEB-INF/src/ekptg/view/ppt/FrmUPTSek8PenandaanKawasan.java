package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek8PenandaanKawasanData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmUPTSek8PenandaanKawasan extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmUPTSek8PenandaanKawasan.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUPTSek8PenandaanKawasanData model = new FrmUPTSek8PenandaanKawasanData();
	PPTHeader header = new PPTHeader();
	
	
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
    	String noLOT = "";
    	String idpegawai = "";
    	
    	
    	Vector listPageDepan = new Vector();
    	Vector dataTanda = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataMaklumatTanah = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	
    	dataSuburusanHakmilik.clear();
    	dataMaklumatTanah.clear();
    	getIdSuburusanstatusfail.clear();
    	dataTanda.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmUPTSek8PenandaanKawasanSenarai.jsp";
    	String mainscreen = "app/ppt/frmUPTSek8PenandaanKawasan.jsp";
    	//String listHMscreen = "app/ppt/frmUPTSek8PenandaanKawasanListHM.jsp";
    	
    	//default list
    	//listPageDepan = model.getListPermohonan(userIdNeg);
    	
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
		/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "9");
    	}
    	*/
    	context.put("paging", "9");
		
		//header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
		String tujuan = "";
		String nama_kementerian = "";
		String no_fail = "";
		String tarikh_permohonan = "";
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
			tujuan = (String) dh.get("tujuan");
			tarikh_permohonan = (String) dh.get("tarikh_permohonan");
			nama_kementerian = (String)dh.get("nama_kementerian");
			no_fail = (String) dh.get("no_fail");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
    	}

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
		
		//id Hakmilik
		//String idHakmilik = getParam("id_hakmilik");
		
		//data hakmilik
		/*modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		//get size suburusanhakmilik
		String id_suburusanstatushakmilik = "";
		String id_suburusanstatus = "";
		modelUPT.setDataSuburusanHakmilik(idHakmilik);
		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
		if(dataSuburusanHakmilik.size()!=0){
			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
			id_suburusanstatus = (String)dsh.get("id_suburusanstatus");
		}*/
		
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange","no");
		context.put("showAlertPaging","no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
		if("sendEmail".equals(submit)){
	    		
	    		//sendEmail(namaProjek,"","mintaBayaranPampasan");    
	     		
	    		//screen
	    		vm = listdepan;
	    		
    	}
    	
    	/*if("viewListHM".equals(submit)){
    	    
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//list maklumat tanah
    		listHakmilik(idpermohonan,noLOT,idpegawai);
     		
        	//screen
    		vm = listHMscreen;
        	
    	}//close viewListHM

    	else */if("penandaanKawasan".equals(submit)){
    
    		model.setDataKawasanPenandaan(idpermohonan);
     		dataTanda = model.getDataKawasanPenandaan();
     		
     		//penambahan yati
    		String id_permohonan = getParam("id_permohonan");
    		context.put("id_permohonan", id_permohonan);
    		
    		emelList(id_permohonan);
			String emel  = emelList(id_permohonan); 
			//System.out.println("EMEL CHECK :: "+emel);
			
			if(getParam("FlagHantarEmel").equals("Y"))
    		{
    			hantarEmel("content","Permohonan Maklumat Penandaan Kawasan" ,no_fail, tujuan, tarikh_permohonan,emel,nama_kementerian);												
    		}
    	
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
            			simpanPenandaan(session,idpermohonan);
            			updateStatus(session);
            			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
            			/*if(modelUPT.cekStatusFailDahWujud(idpermohonan,"38","52")==false)
                		{
            			updateStatus(session);
            			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
                		}*/
            			//updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik);
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
    	    		
    	     		
    	     		if(id_status.equals("38")){
    	     		//	JOptionPane.showMessageDialog (null, "Sila Klik 'Paging' No.16 Untuk Proses Siasatan dan Perintah", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
    	    			context.put("showAlertPaging","yes");
    	    		}else{
    	    			context.put("showAlertPaging","no");
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
                		//updateStatus(session);
            			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
                		
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
	    	//context.put("id_hakmilik", idHakmilik);
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
	
	
//--METHOD--//	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelUPT.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"1473");
	
	}//close addSuburusanHakmilik
	
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
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1473");
		
	}//close updateSuburusanStatusFailPPT
	
	@SuppressWarnings("unchecked")
	private void listHakmilik(String idpermohonan,String noLOT,String idpegawai) throws Exception{
    	
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();
		
		model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 		listMaklumatTanah = model.getListMaklumatTanah();
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 		
	}//close listHakmilik
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmUPTSek8PenandaanKawasanData.setListCarian(nofail,tarikh,status,userIdNeg);
      
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
	private void simpanPenandaan(HttpSession session,String idpermohonan) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", idpermohonan);
		
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
	//penambahan yati-jpbd
		@SuppressWarnings("static-access")
		private String emelList(String id_permohonan) throws Exception {

		Vector emelKementerian = new Vector();
		emelKementerian.clear();

		String emel = "";
		emelKementerian = model.setListEmel(id_permohonan);
		if (emelKementerian.size() != 0) {
			Hashtable npp = (Hashtable) emelKementerian.get(0);
			emel = (String) npp.get("emel");
			}
			context.put("emel", emel);

		return emel;

		}// close emel kementerian
		
		public void hantarEmel(String content,String subjek,String nofail,
				 String nama_projek, String tarikh_permohonan, String emel, String nama_kementerian) throws Exception {

				Vector checkEmail = new Vector();
				checkEmail.clear();
				//System.out.println("id checkEmailKJP :::"+emel);
			
				EmailProperty pro = EmailProperty.getInstance();
				//String EMAIL_HOST = pro.getHost_GM();
				EmailSender email = EmailSender.getInstance();
			
			//myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
			//myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
			email.FROM = pro.getFrom();
			email.SUBJECT = subjek;
			email.MESSAGE = "<html><table><tr><td>Mohon Maklumat Penandaan Kawasan</td></tr>" +
					"<tr><td>No.Fail</td><td>:</td><td>"+nofail+"</td></tr>" +
					"<tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
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
			
		 } //close hantar emel jpph
	
}//close class
