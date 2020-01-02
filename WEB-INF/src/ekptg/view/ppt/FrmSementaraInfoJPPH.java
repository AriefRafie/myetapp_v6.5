package ekptg.view.ppt;
/*
 * @author 
 * NORZAILY BINTI JASMI
 */
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmMMKSek8Data;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8EndosanMemorialDHDKData;
import ekptg.model.ppt.FrmSek8InfoJPBDData;
import ekptg.model.ppt.FrmSek8JabatanTeknikalData;
import ekptg.model.ppt.FrmSek8LaporanAwalTanahData;
import ekptg.model.ppt.FrmSek8WartaData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.FrmUPTSek8PenandaanKawasanData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.SementaraJPPH;

public class FrmSementaraInfoJPPH extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSementaraInfoJPPH.class);
	
	// MODEL SEKSYEN 8
	FrmSek8LaporanAwalTanahData modelLaporan = new FrmSek8LaporanAwalTanahData();
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmSek8JabatanTeknikalData modelJabatan = new FrmSek8JabatanTeknikalData();
	FrmMMKSek8Data modelMMK = new FrmMMKSek8Data();
	FrmSek8WartaData modelWarta = new FrmSek8WartaData();
	FrmSek8EndosanMemorialDHDKData modelEndos = new FrmSek8EndosanMemorialDHDKData();
	FrmUPTSek8PenandaanKawasanData modelTanda = new FrmUPTSek8PenandaanKawasanData();
	FrmSek8InfoJPBDData model = new FrmSek8InfoJPBDData();
	PPTHeader header = new PPTHeader();
	
	// MODEL SEMENTARA
	SementaraJPPH modelSementara = new SementaraJPPH();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user);   	
    	    		
    	String vm = "";
    	String noLOT = "";
    	String idpegawai = "";    	
    	
    	Vector listPageDepan = new Vector();
    	Vector dataMaklumatJPBD = new Vector();
    	Vector dataMaklumatJPPH = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	Vector listHakmilik = new Vector();
    	Vector dataMaklumatTanah = new Vector();    	
    	
    	dataMaklumatTanah.clear();
		listHakmilik.clear();
    	dataSuburusanHakmilik.clear();
    	getIdSuburusanstatusfail.clear();
    	dataMaklumatJPPH.clear();
    	dataMaklumatJPBD.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSementaraInfoJPPHSenarai.jsp";
    	String mainscreen = "app/ppt/frmSementaraInfoJPPH.jsp";
    	String listHMscreen = "app/ppt/frmSementaraInfoJPPHListHM.jsp";
    	
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
    	paging();
    	
		//header
    	String id_status = "";
//		String flagSegera = "";
		String id_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			id_fail = (String)dh.get("id_fail");
//			flagSegera = dh.get("flag_segera").toString();
			
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
		
		
		String idHakmilik = getParam("id_hakmilik");
		
		//data hakmilik
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		//get size suburusanhakmilik
		String id_suburusanstatushakmilik = "";
		modelUPT.setDataSuburusanHakmilik(idHakmilik);
		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
		if(dataSuburusanHakmilik.size()!=0){
			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
		}
		
		String nama2Mukim = "";
		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 		listHakmilik = modelUPT.getListMaklumatTanah();
 		if(listHakmilik.size()!=0){
 			Hashtable lmt = (Hashtable)listHakmilik.get(0);
 			nama2Mukim = (String)lmt.get("nama2Mukim");
 		}
 		
 		context.put("nama2Mukim", nama2Mukim);		
		
		
		
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("modeJPPH","");
		context.put("isEditJPPH","");
		
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

    	else if("viewMaklumat".equals(submit)){
    
    		//data maklumat JPBD
        	model.setDataMaklumatJPBD(idHakmilik,idpermohonan);
    		dataMaklumatJPBD = model.getDataMaklumatJPBD();
    		String id_jpbd = "";
    		if(dataMaklumatJPBD.size()!=0){
    			Hashtable dm = (Hashtable)dataMaklumatJPBD.get(0);
    			id_jpbd = (String)dm.get("id_ulasanteknikal");
    		}
    		context.put("dataMaklumatJPBD",dataMaklumatJPBD);
    		context.put("id_jpbd",id_jpbd);
    		
    		//data maklumat JPPH
        	model.setDataMaklumatJPPHSementara(idHakmilik);
    		dataMaklumatJPPH = model.getDataMaklumatJPPHSementara();
    		String id_jpph = "";
    		if(dataMaklumatJPPH.size()!=0){
    			Hashtable jpph = (Hashtable)dataMaklumatJPPH.get(0);
    			id_jpph = (String)jpph.get("id_ulasanteknikal");
    		}
    		context.put("dataMaklumatJPPH",dataMaklumatJPPH);
    		context.put("id_jpph",id_jpph);
    		
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
    		//NEW TAB 1
    		if(dataMaklumatJPBD.size()==0){
    			
    			//form validation
        		context.put("mode","new");
    			
        		if("simpanMaklumatJPBD".equals(submit2)){
            		
        			
            		if (doPost.equals("true")) {
            			
            			//simpan data
//            			simpanMaklumatJPBD(session,idHakmilik);
            			
            			if(dataSuburusanHakmilik.size()==0){
//            				updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik);
            			}
            			
            			if(id_status.equals("147")){
            				updateStatus(session);
//            				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
            			}
            			
            		}
            		
            		//form validation
                	context.put("mode","view");
                	context.put("isEdit","no");
                	
                	//data header (get id_status)
    	    		dataHeaderStatus(session,idpermohonan);
    	    		id_status = dataHeaderStatus(session,idpermohonan);
                	
            		//data maklumat JPBD / JPPH
//    	    		dataMaklumatJPBD(session,idHakmilik);
                	
            	}//close simpanMaklumat
        		
        		
    		//VIEW TAB 1
    		}else{
    			
    			//form validation
            	context.put("mode","view");
            	context.put("isEdit","no");
     
        		//data maklumat JPBD / JPPH
            	dataMaklumatJPBD(session,idHakmilik,idpermohonan);
        		
            	if("kemaskiniJPBD".equals(submit2)){
            		
            		//form validation
                	context.put("isEdit","yes");
            		
                	String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
                	if("updateJPBD".equals(submit3)){
                		
                		updateMaklumatJPBD(session);
                		
                		//form validation
                    	context.put("mode","view");
                    	context.put("isEdit","no");
                    	
                		//data header (get id_status)
        	    		dataHeaderStatus(session,idpermohonan);
        	    		id_status = dataHeaderStatus(session,idpermohonan);
        	    		
                		//data maklumat JPBD / JPPH
                    	dataMaklumatJPBD(session,idHakmilik,idpermohonan);
                		
                	}//close updateJPBD
                	
            	}//close kemaskiniJPBD
            	
    		}//close view tab1
    		
    		
    		
    		//NEW TAB 2
    		if(dataMaklumatJPPH.size()==0){
    			
    			//form validation
        		context.put("modeJPPH","new");
    			
        		if("simpanMaklumatJPPH".equals(submit2)){
            		
            		if (doPost.equals("true")) {
            			
            			//simpan data
            			simpanMaklumatJPPH(session,idHakmilik);
            			
            			if(dataSuburusanHakmilik.size()==0){				
            				updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik);
            			}
            			
            			if(id_status.equals("147") || (id_status.equals("58"))){
            				updateStatus(session);
            				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
            			}
            			
            		}
            		
            		//form validation
                	context.put("modeJPPH","view");
                	context.put("isEditJPPH","no");
                	
                	//data header (get id_status)
    	    		dataHeaderStatus(session,idpermohonan);
    	    		id_status = dataHeaderStatus(session,idpermohonan);
                	
            		//data maklumat JPPH
    	    		dataMaklumatJPPH(session,idHakmilik);
                	
            	}//close simpanMaklumat
    			
    		//VIEW TAB 2	
    		}else{
    			
    			//form validation
            	context.put("modeJPPH","view");
            	context.put("isEditJPPH","no");
    			
            	//data maklumat JPPH
	    		dataMaklumatJPPH(session,idHakmilik);
	    		
	    		if("kemaskiniJPPH".equals(submit2)){
            		
            		//form validation
                	context.put("isEditJPPH","yes");
            		
                	String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
                	if("updateJPPH".equals(submit3)){
                		
                		updateMaklumatJPPH(session);
                		
                		//form validation
                		context.put("modeJPPH","view");
                    	context.put("isEditJPPH","no");
                    	
                		//data header (get id_status)
        	    		dataHeaderStatus(session,idpermohonan);
        	    		id_status = dataHeaderStatus(session,idpermohonan);
        	    		
        	    		//data maklumat JPPH
        	    		dataMaklumatJPPH(session,idHakmilik);
                		
                	}//close updateJPPH
                	
            	}//close kemaskiniJPPH	
	    		
    		}//close view tab 2
    		
    		
        	//screen
        	vm = mainscreen;
        	
    	}//close 
    
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = modelSementara.getListCarian();
	
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
	
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelSementara.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1520");
		
	}//close updateSuburusanStatusFailPPT
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelSementara.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"1520");
	
	}//close addSuburusanHakmilik	
    	
	private void listHakmilik(String idpermohonan,String noLOT,String idpegawai) throws Exception{
    	/*
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();
		
		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 		listMaklumatTanah = modelUPT.getListMaklumatTanah();
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
			
		SementaraJPPH.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
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
	private void simpanMaklumatJPBD(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		
		//h.put("txtKodPejabatJPBD", getParam("txtKodPejabatJPBD"));
		h.put("txtBilSurat", getParam("txtBilSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhTerimaJT", getParam("txdTarikhTerimaJT"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJT"));
		h.put("txtUlasanJPBD", getParam("txtUlasanJPBD"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8InfoJPBDData.simpanMaklumatJPBD(h);
		
	}//close simpanMaklumat

	@SuppressWarnings("unchecked")
	private void simpanMaklumatJPPH(HttpSession session,String id_hakmilik) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_hakmilik", id_hakmilik);
		//h.put("txtKodPejabatJPPH", getParam("txtKodPejabatJPPH"));
		h.put("txtBilSurat", getParam("txtBilSuratH"));
		h.put("txdTarikhSurat", getParam("txdTarikhSuratH"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerimaH"));
		h.put("txtNoRujSurat", getParam("txtNoRujSuratH"));
		h.put("txdTarikhTerimaJT", getParam("txdTarikhTerimaJTH"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJTH"));
		h.put("txtUlasanJPPH", getParam("txtUlasanJPPH"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8InfoJPBDData.simpanMaklumatJPPHSementara(h);
		
	}//close simpanMaklumat
	
	@SuppressWarnings("unchecked")
	private void updateMaklumatJPBD(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_ulasanteknikal", getParam("id_jpbd"));
		
		//h.put("txtKodPejabatJPBD", getParam("txtKodPejabatJPBD"));
		h.put("txtBilSurat", getParam("txtBilSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhTerimaJT", getParam("txdTarikhTerimaJT"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJT"));
		h.put("txtUlasanJPBD", getParam("txtUlasanJPBD"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8InfoJPBDData.updateMaklumatJPBD(h);
		
	}//close updateMaklumatJPBD
	
	@SuppressWarnings("unchecked")
	private void updateMaklumatJPPH(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_ulasanteknikal", getParam("id_jpph"));
		
		//h.put("txtKodPejabatJPPH", getParam("txtKodPejabatJPPH"));
		h.put("txtBilSurat", getParam("txtBilSuratH"));
		h.put("txdTarikhSurat", getParam("txdTarikhSuratH"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerimaH"));
		h.put("txtNoRujSurat", getParam("txtNoRujSuratH"));
		h.put("txdTarikhTerimaJT", getParam("txdTarikhTerimaJTH"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJTH"));
		h.put("txtUlasanJPPH", getParam("txtUlasanJPPH"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmSek8InfoJPBDData.updateMaklumatJPPH(h);
		
	}//close updateMaklumatJPPH
	
	private void updateStatus(HttpSession session) throws Exception{
    	
		String idpermohonan = getParam("id_permohonan");
    	String idUser = (String) session.getAttribute("_ekptg_user_id");
    	
    	//status JPBD/JPPH
		String idstatus = "43";
    	
		modelSementara.updateStatus(idpermohonan,idUser,idstatus);
      
	}//close hantar

	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMaklumatJPBD(HttpSession session,String idpermohonan, String id_hakmilik) throws Exception{
    	
		//data maklumat jpbd/jpph
    	model.setDataMaklumatJPBD(idpermohonan, id_hakmilik);
		Vector dataMaklumatJPBD = model.getDataMaklumatJPBD();
		String id_jpbd = "";
		if(dataMaklumatJPBD.size()!=0){
			Hashtable dm = (Hashtable)dataMaklumatJPBD.get(0);
			id_jpbd = (String)dm.get("id_ulasanteknikal");
		}
		context.put("dataMaklumatJPBD",dataMaklumatJPBD);
		context.put("id_jpbd",id_jpbd);
		
	}//close dataMaklumat
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMaklumatJPPH(HttpSession session,String id_hakmilik) throws Exception{
    	
		//data maklumat JPPH
    	model.setDataMaklumatJPPHSementara(id_hakmilik);
		Vector dataMaklumatJPPH = model.getDataMaklumatJPPHSementara();
		String id_jpph = "";
		if(dataMaklumatJPPH.size()!=0){
			Hashtable jpph = (Hashtable)dataMaklumatJPPH.get(0);
			id_jpph = (String)jpph.get("id_ulasanteknikal");
		}
		context.put("dataMaklumatJPPH",dataMaklumatJPPH);
		context.put("id_jpph",id_jpph);
		
	}//close dataMaklumatJPPH
	
	private void paging() throws Exception{
		String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "11");
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
