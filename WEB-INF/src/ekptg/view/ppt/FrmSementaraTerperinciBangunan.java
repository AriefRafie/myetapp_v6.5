package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.FrmUPTSek8InfoTanahTerperinciBangunanData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.SementaraLaporanTerperinciBangunan;
import ekptg.ppt.helpers.HTMLPPT;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmSementaraTerperinciBangunan extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSementaraTerperinciBangunan.class);
	
	
	
	//model
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUPTSek8InfoTanahTerperinciBangunanData model = new FrmUPTSek8InfoTanahTerperinciBangunanData();
	PPTHeader header = new PPTHeader();
	
	// MODEL SEMENTARA
	SementaraLaporanTerperinciBangunan modelSementara = new SementaraLaporanTerperinciBangunan();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		

    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user); 		

		//command for pagings
    	String action = getParam("action");
    	
    	//get user login detail
    	String user_id = (String) session.getAttribute("_ekptg_user_id");
    	String portal_role = (String) session.getAttribute("_portal_role");
    	
    	//helper
    	context.put("Util", new lebah.util.Util());
    	context.put("Utils", new ekptg.helpers.Utils());
    	
    	String vm = "";
    	String noLOT = "";
    	String id_bangunan = "";
    	String idExistPB = "";
    	String namaPB = "";
    	String id_bangunanpb = "";
    	String idpegawai = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector dataBangunan = new Vector();
    	Vector checkExistPBidHM = new Vector();
    	Vector listPB = new Vector();
    	Vector dataBangunanPB = new Vector();
    	
    	dataBangunanPB.clear();
    	listPB.clear();
    	checkExistPBidHM.clear();
    	dataBangunan.clear();
    	listMaklumatTanah.clear();
    	listPageDepan.clear();
    	
    	Db dbx = null;
		try {
			dbx = new Db();
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupBangunan",dbx)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupBangunan","Skrin Bangunan", "EKPTG - PPT",dbx);
	    	}
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik",dbx)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT",dbx);
	    	}
		}finally {
			if (dbx != null)
				dbx.close();
		}
   	
    	//screen jsp
//    	String listdepan = "app/ppt/frmUPTSek8InfoTanahTerperinciBangunanSenarai.jsp";
//    	String listHMscreen = "app/ppt/frmUPTSek8InfoTanahTerperinciListHM.jsp";
//    	String mainscreen = "app/ppt/frmUPTSek8InfoTanahTerperinciBangunan.jsp";
//    	String PBscreen = "app/ppt/frmUPTSek8InfoTanahTerperinciMaklumatPenduduk.jsp";
    	
    	String listdepan = "app/ppt/frmSementaraTerperinciBangunanSenarai.jsp";
    	String listHMscreen = "app/ppt/frmSementaraTerperinciListHM.jsp";
    	String mainscreen = "app/ppt/frmSementaraTerperinciBangunan.jsp";
    	String PBscreen = "app/ppt/frmSementaraTerperinciMaklumatPenduduk.jsp";    	
    	
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
    		context.put("paging", "5");
    	}
    	
		//header
    	String flagSegera = "";
		String id_status = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			flagSegera = dh.get("flag_segera").toString();
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}

		context.put("flagSegera",flagSegera);
		
		//header hakmilik
		String idHakmilik = getParam("id_hakmilik");
		modelUPT.setMaklumatTanah(idHakmilik);
		Vector dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		//list PB
    	listPB(session,idHakmilik,namaPB);
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("PBmode","");
		context.put("PBisEdit","");
		context.put("onchange", "no");
		context.put("PBexist",false);
		context.put("idExistPB", "");
		context.put("radioButton", "");
		context.put("onchangePBupdate", "no");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewListHM".equals(submit)){
    /*
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//data & list maklumat tanah
    		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
     		listMaklumatTanah = modelUPT.getListMaklumatTanah();
     		context.put("listMaklumatTanah", listMaklumatTanah);*/
     		context.put("saiz_listTanah", modelUPT.setListMaklumatTanah_count(idpermohonan,noLOT,idpegawai));
    		
        	//screen
        	vm = listHMscreen;
        	
    	}//close 
    	
    	else 
    	if("viewInfoTanah".equals(submit)){
    			
    		//form validation
    		context.put("mode","new");
    		
    		//data maklumat tanah
    		String id_pegawai = "";
    		modelUPT.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = modelUPT.getMaklumatTanah();
    		if(dataMaklumatTanah.size()!=0){
    			Hashtable tt = (Hashtable)dataMaklumatTanah.get(0);
    			id_pegawai = (String)tt.get("id_pegawai");
    		}
    		
    		//id pegawai agihan
    		context.put("id_pegawai", id_pegawai);
    		
    		String mode = "";
    		if((id_pegawai=="") || (id_pegawai.equals(user_id)) || (portal_role.equals("(PPT)PengarahUnit") || portal_role.equals("(PPT)PenolongPengarahUnit") || portal_role.equals("adminppt"))){   			
    			mode = "enabled";
    		}else{
    			//mode = "disabled class=disabled";
    			mode = "enabled";
    		}
    		
    		String idJenisBangunan = getParam("idJenisBangunan");      		  		
    		context.put("idJenisBangunan", idJenisBangunan);
    		
    		//reset value
    		resetValue(session);
    		
    		//dropdown
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,null,""+mode+" style=width:auto onChange=\"onchangeNegeri();\""));
    		context.put("selectBandar",HTML.SelectBandar("socBandar",null,""+mode+" style=width:auto"));
    		
    		//penambahan yati
    		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",null,null,""+mode+" style=width:auto id=socBangunan onchange=\"onchangeJenisBangunan(this.value,'socBangunan');\""));
    		
    		//list bangunan
    		listBangunan(session,idHakmilik);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("onchangeNegeri".equals(submit2)){
        		
        		String idNegeri = getParam("socNegeri");
        		
        		if(idNegeri!=""){
        			context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:auto"));
        		}else{
        			context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:auto"));
        		}
        		
        		idJenisBangunan = getParam("idJenisBangunan");
        		
        		if(idJenisBangunan!=""){
        		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),null,""+mode+" style=width:auto id=socBangunan onchange=\"onchangeJenisBangunan(this.value,'socBangunan');\""));
        		}
        		else{
        			context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",null,null,""+mode+" style=width:auto id=socBangunan onchange=\"onchangeJenisBangunan(idJenisBangunan);\""));	
        		}
        		
        		//get and set item
        		getAndSetItemMB(session,"new");
        		
        	}//close onchangeNegeri
        	
        	else if("onchangeJenisBangunan".equals(submit2)){
        		
        		//System.out.println("xxxxxxxxxxx");
        		String socBangunan = getParam("socBangunan");
        		String idNegeri = getParam("socNegeri");
        		String idBandar = getParam("socBandar");
        		//System.out.println("socBangunan----"+socBangunan);
        	
        		String mod = "valFirst";
        		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),mod,"style=width:auto"));

        		if(mod.equals("valFirst")){
        			
        			context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),null,""+mode+" style=width:auto id=socBangunan onchange=\"onchangeJenisBangunan(this.value,'socBangunan');\""));
        			mod = "valNext";    			
        		}
   		
        		if(mod.equals("valNext")){
        			
        			context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),null,""+mode+" style=width:auto id=socBangunan onchange=\"onchangeJenisBangunan(this.value,'socBangunan');\""));
        			mod = "valThen";		
        		}
        		else {
        		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",null,null,""+mode+" style=width:auto id=socBangunan onchange=\"onchangeJenisBangunan(idJenisBangunan);\""));
        		
        		}
        		
        		getAndSetItemMB(session,"view");
        		
        		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,null,""+mode+" style=width:auto onChange=\"onchangeNegeri();\""));
        		
        		if(idNegeri!=""){
         			context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",Utils.parseLong(idBandar),"style=width:auto"));
         		}else{
         			context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"style=width:auto"));
         		}
        		
        		context.put("idKategoriTanah","");
        	}
    		
        	else if("simpanInfoTanah".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			simpanInfoTanah(session,id_status,idpermohonan);
         		}
        		
        		//form validation
        		context.put("mode","new");
        		
        		//list bangunan
        		listBangunan(session,idHakmilik);
        		
        	}//close simpanInfoTanah
        	
    		//screen
        	vm = mainscreen;
        	
    	}//close viewInfoTanah
    	
    	else 
        if("viewBangunan".equals(submit)){
        		
        	//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//list bangunan
    		listBangunan(session,idHakmilik);
    		
    		id_bangunan = getParam("id_bangunan");
    		context.put("id_bangunan", id_bangunan);
    		String idJenisBangunan = getParam("idJenisBangunan");
    		context.put("idJenisBangunan", idJenisBangunan);
    		System.out.println("ID JENIS BANGUNAN--"+idJenisBangunan);
    		
    		//data & list maklumat tanah
     		model.setDataBangunan(id_bangunan);
			dataBangunan = model.getDataBangunan();
			String idNegeri = "";
			String idBandar = "";
     		if(dataBangunan.size()!=0){
     			Hashtable db = (Hashtable)dataBangunan.get(0);
     			idNegeri = (String)db.get("id_negeri");
     			idBandar = (String)db.get("id_bandar");
     			idJenisBangunan = (String)db.get("jenis_bangunan");
     		}
			
     		//dropdown
     		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:auto disabled class=disabled"));
    		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"style=width:auto disabled class=disabled"));
    		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),null,"style=width:auto disabled class=disabled"));
     		
    		//data bangunan
    		dataBangunan(session,id_bangunan);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniBangunan".equals(submit2)){
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
        		
        		//dropdown
         		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:auto onChange=\"onchangeNegeriUpdate();\""));
        		
         		if(idNegeri!=""){
         			context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",Utils.parseLong(idBandar),"style=width:auto"));
         		}else{
         			context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"style=width:auto"));
         		}
         		String socBangunan =getParam("socBangunan");
         		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan", Utils.parseLong(idJenisBangunan), null, "style=width:auto onChange=\"onchangeJenisBangunanUpdate(this.value,'socBangunan');\""));
         		
         		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("onchangeNegeriUpdate".equals(submit3)){
            		
            		//onchange validation
            		context.put("onchange", "yes");
            		context.put("mode", getParam("mode"));
            		
            		idNegeri = getParam("socNegeri");
            		
            		if(idNegeri!=""){
            			context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:auto"));
            		}else{
            			context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:auto"));
            		}
            		
            		//get and set item
            		getAndSetItemMB(session,"view");
            		
            	}//close onchangeNegeriUpdate
            	
            	//penambahan yati
            	if("onchangeJenisBangunanUpdate".equals(submit3)){
         
            		context.put("idJenisBangunan",idJenisBangunan);
            		socBangunan = getParam("socBangunan");
            		idJenisBangunan = getParam("idJenisBangunan");
            		idNegeri = getParam("socNegeri");
            		idBandar = getParam("socBandar");
            		String mod = "valFirst";
            		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),mod,"style=width:auto"));

            		if(mod.equals("valFirst")){
            			
            			context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),null," style=width:auto id=socBangunan onchange=\"onchangeJenisBangunan(this.value,'socBangunan');\""));
            			mod = "valNext";    			
            		}
       		
            		if(mod.equals("valNext")){
            			
            			context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),null," style=width:auto id=socBangunan onchange=\"onchangeJenisBangunan(this.value,'socBangunan');\""));
            			mod = "valThen";		
            		}
            		else {
            		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",null,null,"style=width:auto id=socBangunan onchange=\"onchangeJenisBangunan(idJenisBangunan);\""));
            		}
            		
            		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:auto onChange=\"onchangeNegeriUpdate();\""));
            		//get and set item
            		getAndSetItemMB(session,"view");
            		context.put("idJenisBangunan", idJenisBangunan);
            		
            	}//close onchangeJenisBangunanUpdate
        		
            	if("updateInfoTanah".equals(submit3)){
            		
            		updateInfoTanah(session);
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//list bangunan
            		listBangunan(session,idHakmilik);
            		
            		id_bangunan = getParam("id_bangunan");
            		context.put("id_bangunan", id_bangunan);
            		
            		//data & list maklumat tanah
             		model.setDataBangunan(id_bangunan);
        			dataBangunan = model.getDataBangunan();
             		if(dataBangunan.size()!=0){
             			Hashtable db = (Hashtable)dataBangunan.get(0);
             			idNegeri = (String)db.get("id_negeri");
             			idBandar = (String)db.get("id_bandar");
             		}
        			
             		//dropdown
             		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:auto disabled class=disabled"));
            		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"style=width:auto disabled class=disabled"));
             		
            		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),null,"style=width:auto disabled class=disabled"));
            		//data bangunan
            		dataBangunan(session,id_bangunan);
            		
            	}//close updateInfoTanah
            	
        	}//close kemaskiniBangunan
    		
    		//screen
        	vm = mainscreen;
        	
        }//close viewBangunan
    	
        else 
        if("hapusBangunan".equals(submit)){
            	
        	hapusBangunan(session);
     		
        	//form validation
    		context.put("mode","new");
        	
    		//reset value
    		resetValue(session);
    		
    		//dropdown
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,null,"style=width:auto onChange=\"onchangeNegeri();\""));
    		context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:auto"));
    		
    		//list bangunan
    		listBangunan(session,idHakmilik);
    		
        	//screen
        	vm = mainscreen;
        	
        }//close hapusBangunan
    	
        else 
    	if("tambahMaklumatBangunanPB".equals(submit)){
    			
    		id_bangunan = getParam("id_bangunan");
    		context.put("id_bangunan", id_bangunan);
    		
    		//form validation
    		context.put("PBmode","new");
    		
    		//check for radio button
    		context.put("NOpb", "");
    		modelHM.setListPB(idHakmilik,namaPB);
        	listPB = modelHM.getListPB();
    		if(listPB.size()==0){
        		context.put("radioButton", 2);
        		context.put("NOpb", "disabled");
    		}        
        	
    		//data bangunan
    		dataBangunan(session,id_bangunan);
    		
    		//reset data
    		resetValue();
    		
    		//data maklumat tanah
    		String id_pegawai = "";
    		modelUPT.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = modelUPT.getMaklumatTanah();
    		if(dataMaklumatTanah.size()!=0){
    			Hashtable tt = (Hashtable)dataMaklumatTanah.get(0);
    			id_pegawai = (String)tt.get("id_pegawai");
    		}
    		
    		//id pegawai agihan
    		context.put("id_pegawai", id_pegawai);
    		
    		String mode = "";
    		if((id_pegawai=="") || (id_pegawai.equals(user_id)) || (portal_role.equals("(PPT)PengarahUnit") || portal_role.equals("(PPT)PenolongPengarahUnit") || portal_role.equals("adminppt"))){   			
    			mode = "enabled";
    		}else{
    			//mode = "disabled class=disabled";
    			mode = "enabled";
    		}
    		
    		//dropdown
        	context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,""+mode+" style=width:auto"));
        	context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",null,""+mode+" style=width:300px"));
        	context.put("selectBangsa",HTML.SelectBangsa("socBangsa",null,""+mode+" style=width:300px"));
        	context.put("selectWarga",HTML.SelectWarganegara("socWarga",null,""+mode+" style=width:auto"));
        	context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,null,""+mode+" style=width:300px onChange=\"onchangeNegeri('"+id_bangunan+"')\""));
        	context.put("selectBandar",HTML.SelectBandar("socBandar",null,""+mode+" style=width:300px"));
    		
    		//list maklumat bangunan pb
        	listMaklumatBangunanPB(session,id_bangunan,idHakmilik);
    		
        	String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("pilihPB".equals(submit2)){
        		
        		id_bangunan = getParam("id_bangunan");
        		
        		String radioButton = getParam("typePB");
        		
        		//radio button validation
        		context.put("radioButton", radioButton);
        		
        		//dropdown
        		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",null,"id=socPB style=width:auto onChange=\"getPBfromDropdown();\""));
        		
        		//get and set item
            	getAndSetItemPB(session,"new");
            		
            	String idNegeri = getParam("socNegeri");
                String idBandar = getParam("socBandar");
            		
            	//dropdown by
            	if(idNegeri!=""){
            		context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",Utils.parseLong(idBandar),"style=width:300px"));
            	}else{
            		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"style=width:300px"));
            	}  
        		
            	if(radioButton.equals("2")){        			
        			resetValuePB(session,id_bangunan);       			
        		}	
        		
        	}//close pilihPB
        	
        	else if("getPBfromDropdown".equals(submit2)){
        		
        		String idPBfromDropdown = getParam("socPB");
        		
        		//get value of radiobutton and PB dropdown
        		getValueRadio(session,idHakmilik);
        		
        		//set exist pb detail
    			getDetailExistPB(session,idPBfromDropdown,idHakmilik);
    			context.put("idExistPB", idPBfromDropdown);
        		
        	}//close getPBfromDropdown
        	
        	else if("onchangeNegeri".equals(submit2)){
        		
        		String idNegeri = getParam("socNegeri");
        		
        		//get value of radiobutton and PB dropdown
        		getValueRadio(session,idHakmilik);
        		
        		if(idNegeri!=""){
        			context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:auto"));
        		}else{
        			context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:auto"));
        		}
        		
        		//get and set item
        		getAndSetItemPB(session,"new");
        		
        	}//close onchangeNegeri
        	
        	else if("checkExistPB".equals(submit2)){
        		
        		//get value of radiobutton and PB dropdown
        		getValueRadio(session,idHakmilik);
        		
        		//check exist pb
        		checkExistPB(session);
        		idExistPB = checkExistPB(session);
        		
        		//ID EXIST
        		if(idExistPB!=""){
        			
        			//set exist pb detail
        			getDetailExistPB(session,idExistPB,idHakmilik);
        			context.put("idExistPB", idExistPB);
        		
        		//ID NOT EXIST
        		}else{
        			
        			//get and set item
            		getAndSetItemPB(session,"new");
            		
            		String idNegeri = getParam("socNegeri");
                	String idBandar = getParam("socBandar");
            		
            		//dropdown by
            		if(idNegeri!=""){
            			context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",Utils.parseLong(idBandar),"style=width:300px"));
            		}else{
            			context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"style=width:300px"));
            		}           		
        		}
        		
        	}//close checkExistPB
        	
        	else if("simpanMaklumatBangunanPB".equals(submit2)){
        		
        		id_bangunan = getParam("id_bangunan");
        		context.put("id_bangunan", id_bangunan);
        		
        		idExistPB = getParam("idExistPB");
        		
        		String radioButton = getParam("typePB");
        		
        		//check exist id hakmilik
        		modelHM.setSizeExistPB(idHakmilik,idExistPB);
        		checkExistPBidHM = modelHM.getCheckSizeExistPB();

        		//Simpan data tblpptbangunanpb shj
        		if(radioButton.equals("1")){
        			
        			if (doPost.equals("true")) {
            			//simpan data maklumat bangunan pb
            			simpanMaklumatBangunanPBShj(session,idHakmilik); 
            		} 
        			
        		//Simpan data ke Tblpptbangunanpb & Tblppthakmilikpb	
        		}else{
        			
        			//EXIST
            		if(checkExistPBidHM.size()!=0){
            			
            			//alert jsp
                     	context.put("PBexist",true);
            			
            		//NOT EXIST	
            		}else{
            			
            			if (doPost.equals("true")) {
                			//simpan data
                			simpanBangunanPBWithNewPB(session,idHakmilik); 
                		}       			
            		}
        			
        		}//close radiobutton

        		//list maklumat bangunan pb
            	listMaklumatBangunanPB(session,id_bangunan,idHakmilik);
        		
            	//check for radio button
        		context.put("NOpb", "");
        		modelHM.setListPB(idHakmilik,namaPB);
            	listPB = modelHM.getListPB();
        		if(listPB.size()==0){
            		context.put("radioButton", 2);
            		context.put("NOpb", "disabled");
        		}else{
        			context.put("radioButton", "");
        		}
            	
        	}//close simpanMaklumatBangunanPB
        	
    		//screen
        	vm = PBscreen;
        	
    	}//close tambahMaklumatBangunanPB
    	
    	else 
    	if("viewMaklumatBangunanPB".equals(submit)){
    		
    		//form validation
    		context.put("PBmode","view");
    		context.put("PBisEdit","no");
    		
    		id_bangunanpb = getParam("id_bangunanpb");
    		context.put("id_bangunanpb", id_bangunanpb);
    		
    		id_bangunan = getParam("id_bangunan");
    		context.put("id_bangunan", id_bangunan);

    		model.setDataBangunanPB(id_bangunanpb,idHakmilik);
     		dataBangunanPB = model.getDataBangunanPB();
     		String idPB = "";
     		if(dataBangunanPB.size()!=0){
     			Hashtable dp = (Hashtable)dataBangunanPB.get(0);
     			idPB = (String)dp.get("id_pihakberkepentingan");
     		}
    		context.put("id_pihakberkepentingan", idPB);
     		
 			//data bangunan
			dataBangunan(session,id_bangunan);
 
			//list maklumat bangunan pb
        	listMaklumatBangunanPB(session,id_bangunan,idHakmilik);
        	
			//data bangunanpb (mode:disabled/enable)
			dataBangunanPB(session,id_bangunan,id_bangunanpb,idHakmilik,"disabled");
					
			String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniBangunanPB".equals(submit2)){
        		
        		//form validation
        		context.put("PBmode","view");
        		context.put("PBisEdit","yes");
        		
        		id_bangunanpb = getParam("id_bangunanpb");
        		context.put("id_bangunanpb", id_bangunanpb);
        		
        		//data bangunanpb (mode:disabled/enable)
    			dataBangunanPB(session,id_bangunan,id_bangunanpb,idHakmilik,"enable");
        		
    			
    			String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
    			
    			if("onchangeNegeriUpdate".equals(submit3)){
            		
    				//onchange validation
    				context.put("onchangePBupdate", "yes");
    				
            		String idNegeri = getParam("socNegeri");
            		
            		if(idNegeri!=""){
            			context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:auto"));
            		}else{
            			context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:auto"));
            		}
            		
            		//get and set item
            		getAndSetItemPB(session,"update");
            		
            	}//close onchangeNegeriUpdate
    			
    			if("updateMaklumatBangunanPB".equals(submit3)){
    				
    				updateMaklumatBangunanPB(session);
    				
    				//form validation
            		context.put("PBmode","view");
            		context.put("PBisEdit","no");
            		
            		id_bangunanpb = getParam("id_bangunanpb");
            		context.put("id_bangunanpb", id_bangunanpb);
            		
            		id_bangunan = getParam("id_bangunan");
            		context.put("id_bangunan", id_bangunan);

         			//data bangunan
        			dataBangunan(session,id_bangunan);
         
        			//list maklumat bangunan pb
                	listMaklumatBangunanPB(session,id_bangunan,idHakmilik);
                	
        			//data bangunanpb (mode:disabled/enable)
        			dataBangunanPB(session,id_bangunan,id_bangunanpb,idHakmilik,"disabled");
    				
    			}//close updateMaklumatBangunanPB
    			
    			
        	}//close kemaskiniBangunanPB
			
			//screen
        	vm = PBscreen;
        	
    	}//close viewMaklumatBangunanPB
    	
    	else 
    	if("hapusBangunanPB".equals(submit)){
    			
    		hapusBangunanPB(session);
     		
    		id_bangunan = getParam("id_bangunan");
    		context.put("id_bangunan", id_bangunan);
    		
        	//form validation
    		context.put("PBmode","new");
    		
    		//reset value
    		context.put("txtNoUnit", "");
    		context.put("txtKadarSewa", "");
    		context.put("txtSaizBangunan", "");
    		context.put("txtKegunaan", "");
    		resetValuePB(session,id_bangunan);
    		
    		//data bangunan
			dataBangunan(session,id_bangunan);
    		
    		//list maklumat bangunan pb
        	listMaklumatBangunanPB(session,id_bangunan,idHakmilik);
    		
        	//screen
    		vm = PBscreen;
    		
    	}//close hapusBangunanPB
    	
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
	    	context.put("id_hakmilik", idHakmilik);
	    	context.put("id_status", id_status);
	    	
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
	}

	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		SementaraLaporanTerperinciBangunan.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	private void hapusBangunan(HttpSession session) throws Exception{
    	
		String id_bangunan = getParam("id_bangunan");
		
		FrmUPTSek8InfoTanahTerperinciBangunanData.hapusBangunan(id_bangunan);
		
	}//close hapusBangunan

	private void hapusBangunanPB(HttpSession session) throws Exception{
    	
		String id_bangunanpb = getParam("id_bangunanpb");
		
		FrmUPTSek8InfoTanahTerperinciBangunanData.hapusBangunanPB(id_bangunanpb);
		
	}//close hapusBangunan
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void listBangunan(HttpSession session,String idHakmilik) throws Exception{
    	
		/*Vector listMaklumatBangunan = new Vector();
		listMaklumatBangunan.clear();
		
		//data & list maklumat tanah
 		model.setListBangunan(idHakmilik);
 		listMaklumatBangunan = model.getListBangunan();
 		context.put("listMaklumatBangunan", listMaklumatBangunan);*/
 		//context.put("saiz_bangunan", listMaklumatBangunan.size());
 		context.put("saiz_bangunan", model.setListBangunan_count(idHakmilik));
 		
	}//close listBangunan
	
	private void resetValue() throws Exception{
    	
		context.put("txtNoUnit", "");
		context.put("txtKadarSewa","");
		context.put("txtSaizBangunan", "");
		context.put("txtKegunaan", "");		
		context.put("txtNama", "");
		context.put("txtNoPB", "");
		context.put("txtJenisPB", "");
		context.put("txtAlamat1", "");
		context.put("txtAlamat2", "");
		context.put("txtAlamat3", "");
		context.put("txtPoskod", "");
		context.put("txtNoTelefon", "");
		context.put("txtNoBimbit", "");
		context.put("txtNoFaks", "");
		context.put("sorSaiz", "");
		
	}//close resetValue
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataBangunan(HttpSession session,String id_bangunan) throws Exception{
    	
		Vector dataBangunan = new Vector();
		dataBangunan.clear();
		
		//data & list maklumat tanah
 		model.setDataBangunan(id_bangunan);
 		dataBangunan = model.getDataBangunan();
 		context.put("dataBangunan", dataBangunan);
 		context.put("id_bangunan", id_bangunan);
 		
	}//close listBangunan
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void dataBangunanPB(HttpSession session,String id_bangunan,String id_bangunanpb,String id_hakmilik,String disability) throws Exception{
    	
		Vector dataBangunanPB = new Vector();
		dataBangunanPB.clear();
		
 		model.setDataBangunanPB(id_bangunanpb,id_hakmilik);
 		dataBangunanPB = model.getDataBangunanPB();
 		String idJenisNoPB = "";
 		String idJenisPB = "";
 		String idBangsa = "";
 		String idWarga = "";
 		String idNegeri = "";
 		String idBandar = "";
 		if(dataBangunanPB.size()!=0){
 			Hashtable dp = (Hashtable)dataBangunanPB.get(0);
 			idJenisNoPB = (String)dp.get("id_jenisnopb");
 			idJenisPB = (String)dp.get("id_jenispb");
 			idBangsa = (String)dp.get("id_bangsa");
 			idWarga = (String)dp.get("id_warganegara");
 			idNegeri = (String)dp.get("id_negeri");
 			idBandar = (String)dp.get("id_bandar");
 		}
 		context.put("dataBangunanPB", dataBangunanPB);
 		context.put("id_bangunanpb", id_bangunanpb);
 		
 		String dis = "";
 		if(disability.equals("disabled")){
 			dis = "class=disabled disabled";
 		}else{
 			dis = "";
 		}
 		
		//dropdown
    	context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(idJenisNoPB),"style=width:auto "+dis+" "));
    	context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",Utils.parseLong(idJenisPB),"style=width:300px "+dis+" "));
    	context.put("selectBangsa",HTML.SelectBangsa("socBangsa",Utils.parseLong(idBangsa),"style=width:300px "+dis+" "));
    	context.put("selectWarga",HTML.SelectWarganegara("socWarga",Utils.parseLong(idWarga),"style=width:auto "+dis+" "));
    	context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:300px "+dis+" onChange=\"onchangeNegeriUpdate('"+id_bangunan+"')\" "));
    	
    	if(idNegeri!=""){
    		context.put("selectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",Utils.parseLong(idBandar),"style=width:300px "+dis+" "));
    	}else{
    		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(idBandar),"style=width:300px "+dis+" "));
    	}
    	

 		
	}//close listBangunan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPB(HttpSession session,String idHakmilik,String namaPB) throws Exception{
    	
		Vector listPB = new Vector();
		listPB.clear();
		
		modelHM.setListPB(idHakmilik,namaPB);
    	listPB = modelHM.getListPB();
    	context.put("saiz_pb", listPB.size());
    	
	}//close listPB
	
	private void getValueRadio(HttpSession session,String idHakmilik) throws Exception{
		
		String radioButton = getParam("typePB");
		String idpb = getParam("socPB");
		
		//radio button validation
		context.put("radioButton", radioButton);
		
		//dropdown
		context.put("selectPB",HTML.SelectPBbyHakmilik(idHakmilik,"socPB",Utils.parseLong(idpb),"id=socPB style=width:auto onChange=\"getPBfromDropdown();\""));
	
	}//close getValueRadio
	
	private void getAndSetItemMB(HttpSession session,String mode) throws Exception{
    	
		String idNegeri = getParam("socNegeri");
		
		if(mode.equals("new")){
			context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:auto onChange=\"onchangeNegeri();\""));
		}else{
			context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:auto onChange=\"onchangeNegeriUpdate();\""));
		}
		
		context.put("sorSaiz",getParam("sorSaiz"));
		context.put("txtNoBangunan",getParam("txtNoBangunan"));
		context.put("txtJenisBangunan",getParam("txtJenisBangunan"));
		context.put("txtSaizBangunan",getParam("txtSaizBangunan"));
		context.put("txtNilai",getParam("txtNilai"));
		context.put("txtPerkara",getParam("txtPerkara"));
		context.put("txtAlamat1",getParam("txtAlamat1"));
		context.put("txtAlamat2",getParam("txtAlamat2"));
		context.put("txtAlamat3",getParam("txtAlamat3"));
		context.put("txtPoskod",getParam("txtPoskod"));
		context.put("txtUlasan",getParam("txtUlasan"));
		
	}//close getAndSetItemMB

	private void getAndSetItemPB(HttpSession session,String mode) throws Exception{
    	
		String idNegeri = getParam("socNegeri");
		String jenisNoPB = getParam("socJenisNoPB");
		String jenisPB = getParam("socJenisPB");
		String idbangsa = getParam("socBangsa");
		String idwarga = getParam("socWarga");
		String id_bangunan = getParam("id_bangunan");
		
		//dropdown
		if(mode.equals("new")){
			context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:auto onChange=\"onchangeNegeri('"+id_bangunan+"');\""));		
		}else{
			context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:auto onChange=\"onchangeNegeriUpdate('"+id_bangunan+"');\""));		
		}		
		context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(jenisNoPB),"style=width:auto"));
    	context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",Utils.parseLong(jenisPB),"style=width:300px"));
    	context.put("selectBangsa",HTML.SelectBangsa("socBangsa",Utils.parseLong(idbangsa),"style=width:300px"));
    	context.put("selectWarga",HTML.SelectWarganegara("socWarga",Utils.parseLong(idwarga),"style=width:auto"));

		context.put("txtNoUnit",getParam("txtNoUnit"));
		context.put("txtKadarSewa",getParam("txtKadarSewa"));
		context.put("txtSaizBangunan",getParam("txtSaizBangunan"));
		context.put("txtKegunaan",getParam("txtKegunaan"));		
		context.put("txtNama",getParam("txtNama"));
		context.put("txtNoPB",getParam("txtNoPB"));
		context.put("txtJenisPB",getParam("txtJenisPB"));
		context.put("txtAlamat1",getParam("txtAlamat1"));
		context.put("txtAlamat2",getParam("txtAlamat2"));
		context.put("txtAlamat3",getParam("txtAlamat3"));
		context.put("txtPoskod",getParam("txtPoskod"));
		context.put("txtNoTelefon",getParam("txtNoTelefon"));
		context.put("txtNoBimbit",getParam("txtNoBimbit"));
		context.put("txtNoFaks",getParam("txtNoFaks"));
		context.put("sorSaiz",getParam("sorSaiz"));
		
	}//close getAndSetItemPB

	private void resetValue(HttpSession session) throws Exception{
    	
		context.put("sorSaiz","");
		context.put("txtNoBangunan","");
		context.put("txtJenisBangunan","");
		context.put("txtSaizBangunan","");
		context.put("txtNilai","");
		context.put("txtPerkara","");
		context.put("txtAlamat1","");
		context.put("txtAlamat2","");
		context.put("txtAlamat3","");
		context.put("txtPoskod","");
		context.put("txtUlasan","");
		
	}//close resetValue
	
	private void resetValuePB(HttpSession session,String id_bangunan) throws Exception{
    	
		context.put("idExistPB", "");
		
		context.put("txtNama", "");
		context.put("txtNoPB", "");
		context.put("txtJenisPB", "");
		context.put("txtAlamat1", "");
		context.put("txtAlamat2", "");
		context.put("txtAlamat3", "");
		context.put("txtPoskod", "");
		context.put("txtNoTelefon", "");
		context.put("txtNoBimbit", "");
		context.put("txtNoFaks", "");
		
		//dropdown
    	context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",null,"style=width:auto"));
    	context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",null,"style=width:300px"));
    	context.put("selectBangsa",HTML.SelectBangsa("socBangsa",null,"style=width:300px"));
    	context.put("selectWarga",HTML.SelectWarganegara("socWarga",null,"style=width:auto"));
    	context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,null,"style=width:300px onChange=\"onchangeNegeri('"+id_bangunan+"')\""));
    	context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:300px"));
		
	}//close resetValuePB

	@SuppressWarnings("unchecked")
	private void simpanInfoTanah(HttpSession session,String idStatus,String idpermohonan) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_hakmilik",getParam("id_hakmilik"));
		
		h.put("sorSaiz",getParam("sorSaiz"));
		h.put("txtNoBangunan",getParam("txtNoBangunan"));
		h.put("socBangunan",getParam("socBangunan"));
		h.put("txtLainBangunan",getParam("txtLainBangunan"));
		h.put("txtSaizBangunan",getParam("txtSaizBangunan"));
		h.put("txtNilai",getParam("txtNilai"));
		h.put("txtPerkara",getParam("txtPerkara"));
		h.put("txtAlamat1",getParam("txtAlamat1"));
		h.put("txtAlamat2",getParam("txtAlamat2"));
		h.put("txtAlamat3",getParam("txtAlamat3"));
		h.put("txtPoskod",getParam("txtPoskod"));
		h.put("txtUlasan",getParam("txtUlasan"));
		h.put("socNegeri",getParam("socNegeri"));
		h.put("socBandar",getParam("socBandar"));
		h.put("socTanah",getParam("socTanah"));
		
		h.put("txtSaizBangunanDua",getParam("txtSaizBangunanDua"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUPTSek8InfoTanahTerperinciBangunanData.simpanInfoTanah(h,idStatus,idpermohonan);
		
	}//close simpanInfoTanah
	
	@SuppressWarnings("unchecked")
	private void updateInfoTanah(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_bangunan",getParam("id_bangunan"));
		
		h.put("sorSaiz",getParam("sorSaiz"));
		h.put("txtNoBangunan",getParam("txtNoBangunan"));
		h.put("txtLainBangunan",getParam("txtLainBangunan"));
		h.put("socBangunan",getParam("socBangunan"));
		h.put("txtSaizBangunan",getParam("txtSaizBangunan"));
		h.put("txtNilai",getParam("txtNilai"));
		h.put("txtPerkara",getParam("txtPerkara"));
		h.put("txtAlamat1",getParam("txtAlamat1"));
		h.put("txtAlamat2",getParam("txtAlamat2"));
		h.put("txtAlamat3",getParam("txtAlamat3"));
		h.put("txtPoskod",getParam("txtPoskod"));
		h.put("txtUlasan",getParam("txtUlasan"));
		h.put("socNegeri",getParam("socNegeri"));
		h.put("socBandar",getParam("socBandar"));
		h.put("txtSaizBangunanDua",getParam("txtSaizBangunanDua"));
		h.put("socTanah",getParam("socTanah"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUPTSek8InfoTanahTerperinciBangunanData.updateInfoTanah(h);
		
	}//close updateInfoTanah
	
	@SuppressWarnings({ "unchecked"})
	private void simpanBangunanPBWithNewPB(HttpSession session,String idHakmilik) throws Exception{

		Hashtable h = new Hashtable();
		
		Vector dataDetailExistPB = new Vector();
		dataDetailExistPB.clear();
		
		
		//data tblhakmilikpb & tblpptpihakberkepentingan
		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("id_hakmilikpb", "");
		h.put("txtNama", getParam("txtNama"));
		h.put("socJenisNoPB", getParam("socJenisNoPB"));
		h.put("socJenisPB", getParam("socJenisPB"));
		h.put("socBangsa", getParam("socBangsa"));
		h.put("socWarga", getParam("socWarga"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));
		h.put("txtNoPB", getParam("txtNoPB"));
		h.put("txtJenisPB", getParam("txtJenisPB"));
		h.put("txtSyorAtas", "");
		h.put("txtSyorBawah", "");
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("txtNoTelefon", getParam("txtNoTelefon"));
		h.put("txtNoBimbit", getParam("txtNoBimbit"));
		h.put("txtNoFaks", getParam("txtNoFaks"));
		
		//data tblpptbangunanpb
		h.put("id_bangunan",getParam("id_bangunan"));
		h.put("txtNoUnit",getParam("txtNoUnit"));
		h.put("txtKadarSewa",getParam("txtKadarSewa"));
		h.put("txtSaizBangunan",getParam("txtSaizBangunan"));
		h.put("txtKegunaan",getParam("txtKegunaan"));
		h.put("flag_pb",getParam("typePB"));
		h.put("sorSaiz",getParam("sorSaiz"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		String idExistPB = getParam("idExistPB");

		long id_pihakberkepentingan = 0;
		String id_hakmilikpb = DB.getNextID("TBLPPTHAKMILIKPB_SEQ")+"";

		if(idExistPB!=""){
			h.put("id_pihakberkepentingan", idExistPB);
			id_pihakberkepentingan = Long.parseLong(idExistPB);
			FrmUPTSek8HakmilikData.updatePB(h,"updateOLD","");
			FrmUPTSek8HakmilikData.simpanExistPB(h,id_hakmilikpb,"");
		}else{			
			id_pihakberkepentingan = DB.getNextID("TBLPPTPIHAKBERKEPENTINGAN_SEQ");
			FrmUPTSek8HakmilikData.simpanPB(h,id_pihakberkepentingan,id_hakmilikpb,"");
		}
		
		FrmUPTSek8InfoTanahTerperinciBangunanData.simpanBangunanPB(h,id_hakmilikpb);
		
	}//close simpanBangunanPB
	
	@SuppressWarnings("unchecked")
	private void updateMaklumatBangunanPB(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		//data tblhakmilikpb & tblpptpihakberkepentingan
		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("txtNama", getParam("txtNama"));
		h.put("socJenisNoPB", getParam("socJenisNoPB"));
		h.put("socJenisPB", getParam("socJenisPB"));
		h.put("socBangsa", getParam("socBangsa"));
		h.put("socWarga", getParam("socWarga"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));
		h.put("txtNoPB", getParam("txtNoPB"));
		h.put("txtJenisPB", getParam("txtJenisPB"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("txtNoTelefon", getParam("txtNoTelefon"));
		h.put("txtNoBimbit", getParam("txtNoBimbit"));
		h.put("txtNoFaks", getParam("txtNoFaks"));
		
		//data tblpptbangunanpb
		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
		h.put("id_bangunanpb",getParam("id_bangunanpb"));
		h.put("txtNoUnit",getParam("txtNoUnit"));
		h.put("txtKadarSewa",getParam("txtKadarSewa"));
		h.put("txtSaizBangunan",getParam("txtSaizBangunan"));
		h.put("txtKegunaan",getParam("txtKegunaan"));
		h.put("sorSaiz",getParam("sorSaiz"));
	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update data pb with idhakmilik n pb didlm tblppthakmilikpb		
		FrmUPTSek8HakmilikData.updatePBwithIdHakmilikAndIdPB(h,id_pihakberkepentingan);

		FrmUPTSek8InfoTanahTerperinciBangunanData.updateBangunanPB(h);
		
	}//close updateMaklumatBangunanPB
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanMaklumatBangunanPBShj(HttpSession session,String idHakmilik) throws Exception{

		Hashtable h = new Hashtable();
		
		Vector dataDetailExistPB = new Vector();
		dataDetailExistPB.clear();
		
		String id_hakmilikpb = "";
		modelHM.setDataDetailPB(getParam("idExistPB"),idHakmilik,1);
		dataDetailExistPB = modelHM.getDataDetailPB();	
		if(dataDetailExistPB.size()!=0){
			Hashtable dpb = (Hashtable)dataDetailExistPB.get(0);
			id_hakmilikpb = (String)dpb.get("id_hakmilikpb");
		}
		
		//data tblhakmilikpb & tblpptpihakberkepentingan
		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("txtNama", getParam("txtNama"));
		h.put("socJenisNoPB", getParam("socJenisNoPB"));
		h.put("socJenisPB", getParam("socJenisPB"));
		h.put("socBangsa", getParam("socBangsa"));
		h.put("socWarga", getParam("socWarga"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));
		h.put("txtNoPB", getParam("txtNoPB"));
		h.put("txtJenisPB", getParam("txtJenisPB"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("txtNoTelefon", getParam("txtNoTelefon"));
		h.put("txtNoBimbit", getParam("txtNoBimbit"));
		h.put("txtNoFaks", getParam("txtNoFaks"));
		h.put("txtNoFaks", getParam("txtNoFaks"));
		h.put("sorSaiz",getParam("sorSaiz"));
		
		//data tblpptbangunanpb
		h.put("id_bangunan",getParam("id_bangunan"));
		h.put("txtNoUnit",getParam("txtNoUnit"));
		h.put("txtKadarSewa",getParam("txtKadarSewa"));
		h.put("txtSaizBangunan",getParam("txtSaizBangunan"));
		h.put("txtSaizBangunanDua","");
		h.put("txtKegunaan",getParam("txtKegunaan"));
		h.put("flag_pb",getParam("typePB"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		String idExistPB = getParam("idExistPB");
		
		//simpan data bangunan pb
		FrmUPTSek8InfoTanahTerperinciBangunanData.simpanBangunanPB(h,id_hakmilikpb);
		
		//update data pb with idhakmilik n pb didlm tblppthakmilikpb		
		FrmUPTSek8HakmilikData.updatePBwithIdHakmilikAndIdPB(h,idExistPB);
		
	}//close simpanMaklumatBangunanPBShj
	
	
	@SuppressWarnings({ "static-access", "unchecked" })
	private void listMaklumatBangunanPB(HttpSession session,String idBangunan,String idHakmilik) throws Exception{
    	/*
		Vector listMaklumatPB = new Vector();
		listMaklumatPB.clear();
		
		//data & list maklumat tanah
 		model.setListMaklumatPB(idBangunan,idHakmilik);
 		listMaklumatPB = model.getListMaklumatPB();
 		context.put("listMaklumatPB", listMaklumatPB);*/
		context.put("saiz_maklumatpb", model.countPenduduk(idBangunan,idHakmilik));
		//context.put("saiz_maklumatpb", listMaklumatPB.size());
 		
	}//close listMaklumatBangunanPB
	
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
	private String checkExistPB(HttpSession session) throws Exception{
    	
		Vector checkExistPB = new Vector();
		checkExistPB.clear();
		
		String socJenisNoPB = getParam("socJenisNoPB");
		String noPB = getParam("txtNoPB");
		
		modelHM.setCheckExistPB(socJenisNoPB,noPB);
		checkExistPB = modelHM.getCheckExistPB();
		String idExistPB = "";
		if(checkExistPB.size()!=0){
			Hashtable ce = (Hashtable)checkExistPB.get(0);
			idExistPB = (String)ce.get("id_pihakberkepentingan");
		}
		
		return idExistPB;
    	
	}//close checkExistPB
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getDetailExistPB(HttpSession session,String idPB,String idHakmilik) throws Exception{
    	
		Vector dataDetailExistPB = new Vector();
		dataDetailExistPB.clear();
		
		modelHM.setDataDetailPB(idPB,idHakmilik,1);
		dataDetailExistPB = modelHM.getDataDetailPB();	
		String txtNama = "";
		String txtNoPB = "";
		String txtJenisPB = "";
		String txtAlamat1 = "";
		String txtAlamat2 = "";
		String txtAlamat3 = "";
		String txtPoskod = "";
		String txtNoTelefon = "";
		String txtNoBimbit = "";
		String txtNoFaks = "";		
    	String id_jenisNoPB = "";
    	String id_jenisPB = "";
    	String id_bangsa = "";
    	String id_warganegara = "";
    	String id_negeri = "";
    	String id_bandar = "";
    	if(dataDetailExistPB.size()!=0){
    		Hashtable pb = (Hashtable) dataDetailExistPB.get(0);
    		id_jenisNoPB = pb.get("id_jenisnopb").toString();
    		id_jenisPB = pb.get("id_jenispb").toString();
    		id_bangsa = pb.get("id_bangsa").toString();
    		id_warganegara = pb.get("id_warganegara").toString();
    		id_negeri = pb.get("id_negeri").toString();
    		id_bandar = pb.get("id_bandar").toString();   		
    		txtNama = pb.get("nama_pb").toString();
    		txtNoPB = pb.get("no_pb").toString();
    		txtJenisPB = pb.get("jenis_lain2_pb").toString();
    		txtAlamat1 = pb.get("alamat1").toString();
    		txtAlamat2 = pb.get("alamat2").toString();
    		txtAlamat3 = pb.get("alamat3").toString();
    		txtPoskod = pb.get("poskod").toString();
    		txtNoTelefon = pb.get("no_tel_rumah").toString();
    		txtNoBimbit = pb.get("no_hp").toString();
    		txtNoFaks = pb.get("no_fax").toString();
    	}

    	String id_bangunan = getParam("id_bangunan");
    	
    	//dropdown
    	context.put("selectJenisNoPB",HTML.SelectJenisNoPb("socJenisNoPB",Utils.parseLong(id_jenisNoPB),"style=width:auto"));
    	context.put("selectJenisPB",HTML.SelectJenisPb("socJenisPB",Utils.parseLong(id_jenisPB),"style=width:300px"));
    	context.put("selectBangsa",HTML.SelectBangsa("socBangsa",Utils.parseLong(id_bangsa),"style=width:300px"));
    	context.put("selectWarga",HTML.SelectWarganegara("socWarga",Utils.parseLong(id_warganegara),"style=width:auto"));
    	context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),"style=width:300px onChange=\"onchangeNegeri('"+id_bangunan+"')\""));
    	
    	if(id_negeri!=""){
    		context.put("selectBandar",HTML.SelectBandarByNegeri(id_negeri,"socBandar",Utils.parseLong(id_bandar),"style=width:300px"));
    	}else{
    		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:300px"));
    	}
    	
    	//set exist data pb
    	context.put("txtNama", txtNama);
    	context.put("txtNoPB", txtNoPB);
    	context.put("txtJenisPB", txtJenisPB);
    	context.put("txtAlamat1", txtAlamat1);
    	context.put("txtAlamat2", txtAlamat2);
    	context.put("txtAlamat3", txtAlamat3);
    	context.put("txtPoskod", txtPoskod);
    	context.put("txtNoTelefon", txtNoTelefon);
    	context.put("txtNoBimbit", txtNoBimbit);
    	context.put("txtNoFaks", txtNoFaks);
    	
    	context.put("txtNoUnit",getParam("txtNoUnit"));
		context.put("txtKadarSewa",getParam("txtKadarSewa"));
		context.put("txtSaizBangunan",getParam("txtSaizBangunan"));
		context.put("txtKegunaan",getParam("txtKegunaan"));		
		context.put("sorSaiz",getParam("sorSaiz"));
		
	}//close dataPBVIEW
	
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
