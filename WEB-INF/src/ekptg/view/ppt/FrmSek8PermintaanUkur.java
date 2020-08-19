package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8BorangKData;
import ekptg.model.ppt.FrmSek8PermintaanUkurData;
import ekptg.model.ppt.FrmSek8SiasatanData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmSek8PermintaanUkur extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek8PermintaanUkur.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmSek8PermintaanUkurData model = new FrmSek8PermintaanUkurData();
	FrmSek8BorangKData modelBorangK = new FrmSek8BorangKData();
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	FrmSek8SiasatanData logic = new FrmSek8SiasatanData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	String vm = "";
    	String noLOT = "";
    	String namaPB = "";
    	String bolehsimpan = "";
    	String showFieldAmbilBeforeConvert ="";
    	
    	
    	
    	Vector listPageDepan = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector dataHakmilikBorangK = new Vector();
    	Vector dataHakmilikSambungan = new Vector(); //penambahan yati
    	Vector dataPermintaanUkur = new Vector();
    	Vector dataDetailPB = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataMaklumatTanah = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	
    	Vector maklumat_kehadiran = null;
    	Vector list_bandar = null;
    	Vector list_bangsa = null;
		Vector list_warga = null;
		Vector checkSizeBahagianPB = null;
		Vector senarai_pihak_penting_bahagian = null;	
		Vector list_bahagian= null;
		Vector list_kehadiran_th = null;
		Vector list_check_kehadiran = null;
		Vector list_jenispb = null;
		Vector maklumat_siasatan = null;
		Vector list_jenisnopb = null;
		Vector list_bank = null;
		Vector list_negeri = null;
		
		
		
       	context.put("list_negeri","");
		context.put("maklumat_kehadiran","");
		context.put("list_bandar","");
		context.put("list_bangsa","");
		context.put("list_warga","");
		context.put("checkSizeBahagianPB","");
		context.put("senarai_pihak_penting_bahagian","");
		context.put("list_bahagian","");
		context.put("list_kehadiran_th","");
		context.put("list_check_kehadiran","");
		context.put("list_jenispb","");
		context.put("maklumat_siasatan","");
		context.put("list_jenisnopb","");
		context.put("list_bank","");	
		this.context.put("tambah_kehadiran_wakil", "");
		this.context.put("tambah_kehadiran_negeri_wakil", "");
		this.context.put("tambah_kehadiran", "");
		context.put("hideAdd", "no");
		this.context.put("id_hakmilik_salin", "");
		this.context.put("maklumat_PB_Salin", "");
		
		
		context.put("showFieldAmbilBeforeConvert", "no");
		context.put("showDropdownUnitAmbil", "no");
    	
    	dataSuburusanHakmilik.clear();
    	dataMaklumatTanah.clear();
    	getIdSuburusanstatusfail.clear();
    	dataDetailPB.clear();
    	dataPermintaanUkur.clear();
    	dataHakmilikBorangK.clear();
    	listPageDepan.clear();
    	listMaklumatTanah.clear();
    	
    	//screen jsp
    	String listdepan = "app/ppt/PermintaanUkur/frmSek8PermintaanUkurSenarai.jsp";
    	String listHMscreen = "app/ppt/PermintaanUkur/frmSek8PermintaanUkurListHM.jsp";
    	String mainscreen = "app/ppt/PermintaanUkur/frmSek8PermintaanUkur.jsp";
    	
    	context.put("jenisPU","internal");	
    	context.put("selectedTabPelarasan","0");
    	
    	//utils
    	context.put("Utils", new ekptg.helpers.Utils());
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	myLogger.info(" doPost :"+doPost);
    	if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		
    	
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
    		context.put("paging", "23");
    	}
    	*/
    	context.put("paging", "23");
    	
    	/*
    	Db db = null;
		try {
		db = new Db();
	    	
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT",db);
	    	}
	    	if (checkRegPopup("ekptg.view.ppt.SkrinPopupCarianPB", db) == 0) {
				// reg class
				insertPopupReg("ekptg.view.ppt.SkrinPopupCarianPB",
						"Skrin Capaian PB", "EKPTG - PPT", db);
			}
	    	
		}finally {
			if (db != null)
			db.close();
		}
		*/
    	
		//header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
		String no_fail = "";
		String no_rujukan_ptg = "";
		String id_projekNegeri = "";
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
			flagSegera = dh.get("flag_segera").toString();
			id_fail = dh.get("id_fail").toString();
			no_fail = dh.get("no_fail").toString();
			no_rujukan_ptg = (String)dh.get("no_rujukan_ptg");
			id_projekNegeri = (String)dh.get("id_projekNegeri");	
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
		
		//id
		String idHakmilik = getParam("id_hakmilik");
		
		//data hakmilik
		if(!idHakmilik.equals(""))
		{
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		}
		
		//get size suburusanhakmilik
		String id_suburusanstatushakmilik = "";
		//String id_suburusanstatus = "";
		if(!idHakmilik.equals(""))
		{
		modelUPT.setDataSuburusanHakmilik(idHakmilik);
		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
		if(dataSuburusanHakmilik.size()!=0){
			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
			//id_suburusanstatus = (String)dsh.get("id_suburusanstatus");
		}
		}
		
		//data permintaan ukur (validate tab)
		String jenis_pelarasan = "";
		String id_permintaanukur = "";
		if(!idHakmilik.equals(""))
		{
			
		model.setDataPermintaanUkur(idHakmilik);
 		dataPermintaanUkur = model.getDataPermintaanUkur();
 		if(dataPermintaanUkur.size()!=0){
 			Hashtable dpu = (Hashtable)dataPermintaanUkur.get(0);
 			jenis_pelarasan = (String)dpu.get("jenis_pelarasan");
 			id_permintaanukur = (String)dpu.get("id_permintaanukur");
 		}
 		
		}
		
		//data tblpptpermintaanukur
		
		if(!idHakmilik.equals(""))
		{
			/*
 		model.setDataPermintaanUkur(idHakmilik);
 		dataPermintaanUkur = model.getDataPermintaanUkur();
 		if(dataPermintaanUkur.size()!=0){
 			Hashtable dpu = (Hashtable)dataPermintaanUkur.get(0);
 			id_permintaanukur = (String)dpu.get("id_permintaanukur");
 		}
 		*/
		}
		
		
 		
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("modePB","");
		context.put("isEditPB","");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	context.put("currentcommand",getParam("command"));
    	
    	if("viewListHM".equals(submit)){
    
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//data & list maklumat tanah
    		listMaklumatTanah(idpermohonan,noLOT);
     		
        	//screen
    		vm = listHMscreen;
    		
    	}//close 
    	
    	
    	else if ("PB".equals(submit))
			{ 
    		
    		String subminor_command = getParam("command2");
    	//String idHakmilik = "";
    	idHakmilik = getParam("id_hakmilik");
    		
    	this.context.put("view_pb", "");	
    	this.context.put("tambah_kehadiran_negeri", "");		
    	if ("View".equals(subminor_command))   			
			{
    		getTotalSyer(idHakmilik,"");
    		
			} 
    	
    	else if ("Papar".equals(subminor_command))   			
			{
    		this.context.put("view_pb", "yes");	
    		this.context.put("readmode", "view");	
    		maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));	
    		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
				if(!h.get("ID_NEGERI").toString().equals(""))
			{	
			list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
			this.context.put("list_bandar",list_bandar);
			} 				
				
			} 
    	
    	else if ("tambah".equals(subminor_command))   			
			{
    		this.context.put("tambah_kehadiran", "yes");
    		this.context.put("tambah_kehadiran_negeri", "yes");
    		this.context.put("tambah_kehadiran_wakil", "");
    		this.context.put("tambah_kehadiran_negeri_wakil", "");
    		this.context.put("view_pb", "yes");	
    		this.context.put("readmode", "edit"); 
    		getTotalSyer(idHakmilik,"");
				
			} 
    	
    	else if ("tambah_wakil".equals(subminor_command))   			
			{
    		this.context.put("tambah_kehadiran_wakil", "yes");
    		this.context.put("tambah_kehadiran_negeri_wakil", "yes");
    		this.context.put("tambah_kehadiran", "");
    		this.context.put("tambah_kehadiran_negeri", "");
    		this.context.put("view_pb", "yes");	
    		this.context.put("readmode", "edit"); 
    		//this.context.put("nk_add","yes");
    		
    		getTotalSyer(idHakmilik,"");
				
			} 
    	
    	else if ("salin_pb".equals(subminor_command)) {
			String id_hakmilikpb_salin = getParam("id_hakmilikpb_salin");
			this.context.put("id_hakmilikpb_salin", id_hakmilikpb_salin);
			this.context.put("maklumat_PB_Salin", "");
			if (!id_hakmilikpb_salin.equals("")
					&& !id_hakmilikpb_salin.equals(null)
					&& logic.maklumat_PB_Salin(id_hakmilikpb_salin).size() > 0) {
				this.context.put("maklumat_PB_Salin",
						logic.maklumat_PB_Salin(id_hakmilikpb_salin));

				if (!logic.maklumat_PB_Salin(id_hakmilikpb_salin)
						.get("ID_NEGERI").equals("")) {
					list_bandar = logic.getListBandarByNegeri(logic
							.maklumat_PB_Salin(id_hakmilikpb_salin)
							.get("ID_NEGERI").toString());
					this.context.put("list_bandar", list_bandar);
				}

			}
			this.context.put("tambah_kehadiran_wakil", "yes");
			this.context.put("tambah_kehadiran_negeri_wakil", "yes");
			this.context.put("tambah_kehadiran", "");
			this.context.put("tambah_kehadiran_negeri", "");
			this.context.put("view_pb", "yes");
			this.context.put("readmode", "edit");
			getTotalSyer_Temp(idHakmilik, "");

		}

    	
    	else if ("Simpan".equals(subminor_command))   			
			{
    	
    		if(!getParam("id_hakmilikpb").equals(""))
    		{
    			String BC = this.request.getParameter("boxBorangC");
				String BE = this.request.getParameter("boxBorangE");
				String BG = this.request.getParameter("boxBorangG");
				String BK = this.request.getParameter("boxBorangK");
				
				String FC = "";
				String FE = "";
				String FG = "";
				String FK = "";
				
				myLogger.info("BC:"+BC+"BE:"+BE+"BG:"+BG+"BK:"+BK);
				
			if(BC != null)
			{
    		FC = "1";
			}
			if(BE != null)
			{
    		FE = "1";
			}
			if(BG != null)
			{
    		FG = "1";
			}
			if(BK != null)
			{
    		FK = "1";
			}
			
			logic.updatePBSiasatan(getParam("id_pb"),getParam("txtNamaPB"),getParam("txtNoPB"),getParam("socJenisNOPB"),
					getParam("txtNamaPBKP"),(String) session.getAttribute("_ekptg_user_id"),getParam("socWarga"),getParam("socBangsa"),getParam("txtPenamaPenyata"),"");									
			logic.updateSiasatanPB(getParam("id_hakmilikpb"),getParam("socJenisPB"),getParam("txtNoAkaun"),getParam("socJenisBank"),getParam("txtAlamat1PB"),getParam("txtAlamat2PB"),
					getParam("txtAlamat3PB"),getParam("txtPoskodPB"),getParam("socNegeri"),getParam("socBandar"),(String) session.getAttribute("_ekptg_user_id"),getParam("txtNoHP"),
					getParam("txtNoTel"),getParam("txtNamaBank"),getParam("txtKeteranganPB"),getParam("txtCatatan"),FC,FE,FG,FK,getParam("id_bahagianpb"),getParam("txtSyorAtas")
		    		,getParam("txtSyorBawah"));									
       		
    		maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));	
    		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
				if(!h.get("ID_NEGERI").toString().equals(""))
			{	
			list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
			this.context.put("list_bandar",list_bandar);
			} 
				this.context.put("view_pb", "yes");	
    		this.context.put("readmode", "view");      
    		}
    		else
    		{
    			if (bolehsimpan.equals("yes")) 
				{
    				
    				String BC = this.request.getParameter("boxBorangC");
    				String BE = this.request.getParameter("boxBorangE");
    				String BG = this.request.getParameter("boxBorangG");
    				String BK = this.request.getParameter("boxBorangK");
    				
    				String FC = "";
    				String FE = "";
    				String FG = "";
    				String FK = "";
    				
    				myLogger.info("BC:"+BC+"BE:"+BE+"BG:"+BG+"BK:"+BK);
    				
    			if(BC != null)
    			{
        		FC = "1";
    			}
    			if(BE != null)
    			{
        		FE = "1";
    			}
    			if(BG != null)
    			{
        		FG = "1";
    			}
    			if(BK != null)
    			{
        		FK = "1";
    			}		
    				
    				
    				
    				
    	logic.addSiasatanPB(getParam("id_pb"),getParam("txtNamaPB"),getParam("txtNoPB"),getParam("socJenisNOPB"),getParam("txtNamaPBKP"),(String) session.getAttribute("_ekptg_user_id"),
    	getParam("id_hakmilikpb"),getParam("socJenisPB"),getParam("txtNoAkaun"),getParam("socJenisBank"),getParam("txtAlamat1PB"),getParam("txtAlamat2PB"),getParam("txtAlamat3PB"),getParam("txtPoskodPB"),
    	getParam("socNegeri"),getParam("socBandar"),(String) session.getAttribute("_ekptg_user_id"),getParam("txtNoHP"),getParam("txtNoTel"),getParam("id_siasatan"),getParam("id_hakmilik"),getParam("txtPBhadir"),getParam("txtNamaBank"),getParam("txtKeteranganPB"),getParam("txtCatatan"),FC,FE,FG,FK,getParam("txtSyorAtas")
		,getParam("txtSyorBawah"),getParam("socWarga"),getParam("socBangsa"),getParam("id_bahagianpb"),getParam("txtPenamaPenyata"),"");
				}
    				
    		}
    		getTotalSyer(idHakmilik,"");		
			} 
    	else if ("DeleteTurutHadir".equals(subminor_command))   			
			{	this.context.put("view_pb", "");	
    		this.context.put("readmode", "");
    	
    		logic.deleteMaklumatPB(getParam("id_pb"));        	
    		logic.deleteHakmilikPB(getParam("id_hakmilikpb")); 
    	}
    	else if ("Kemaskini".equals(subminor_command))   			
			{
    		this.context.put("view_pb", "yes");	
    		this.context.put("readmode", "edit");	
    		maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));	
    		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
				if(!h.get("ID_NEGERI").toString().equals(""))
			{	
			list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
			this.context.put("list_bandar",list_bandar);
			} 	
			}
    	else if ("batal".equals(subminor_command))   			
			{
    		this.context.put("view_pb", "yes");	
    		this.context.put("readmode", "edit");   					
    		if(getParam("id_hakmilikpb")!="" && getParam("id_hakmilikpb")!= null)
    		{
    		maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));	
    		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
				if(!h.get("ID_NEGERI").toString().equals(""))
			{	
			list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
			this.context.put("list_bandar",list_bandar);
			} 
    		}
				
			}
    	
    	else if ("getBandar".equals(subminor_command))
		{
    	   		String key = "";
				String value = "";
				Enumeration allparam = request.getParameterNames();
				for (; allparam.hasMoreElements();) {
					key = (String) allparam.nextElement();
					value = request.getParameter(key);
					this.context.put(key, value);
				}   						
		this.context.put("readmode", "edit");
		this.context.put("view_pb", "yes");	
		if(!getParam("socNegeri").equals(""))
		{	
		list_bandar = logic.getListBandarByNegeri(getParam("socNegeri"));
		this.context.put("list_bandar",list_bandar);
		}	
		this.context.put("socBandar","");			
		if(getParam("id_hakmilikpb").equals(""))
		{
			this.context.put("tambah_kehadiran_negeri", "yes");	
		}
		
		
		String BC = this.request.getParameter("boxBorangC");
		String BE = this.request.getParameter("boxBorangE");
		String BG = this.request.getParameter("boxBorangG");
		String BK = this.request.getParameter("boxBorangK");
		
		String FC = "";
		String FE = "";
		String FG = "";
		String FK = "";
		
		myLogger.info("BC:"+BC+"BE:"+BE+"BG:"+BG+"BK:"+BK);
		
		if(BC != null)
		{
		FC = "checked";
		}
		if(BE != null)
		{
		FE = "checked";
		}
		if(BG != null)
		{
		FG = "checked";
		}
		if(BK != null)
		{
		FK = "checked";
		}				
		this.context.put("boxBorangC",FC);
		this.context.put("boxBorangE",FE);
		this.context.put("boxBorangG",FG);
		this.context.put("boxBorangK",FK);
		}
    	
			else if ("Simpan_Borang".equals(subminor_command))
		{     	 
					
			String[] ids1 = this.request.getParameterValues("ids1");
			String[] idPB = this.request.getParameterValues("idPB");				
			String[] borangE = this.request.getParameterValues("borangE");
			String[] borangG = this.request.getParameterValues("borangG");
			String[] borangK = this.request.getParameterValues("borangK");
			
			if (idPB != null) {
				
				for (int i = 0; i < idPB.length; i++) {						
						if (bolehsimpan.equals("yes")) 
						{
							logic.updateBorang(idPB[i],(String) session.getAttribute("_ekptg_user_id"),"clear");  									
						}
				}
			 }	
			
			if (ids1 != null) {
				
				
				for (int i = 0; i < ids1.length; i++) {						
						if (bolehsimpan.equals("yes")) 
						{
							logic.updateBorang(ids1[i],(String) session.getAttribute("_ekptg_user_id"),"borangC");  									
						}
				}
			 }	
			
			if (borangE != null) {
				
				
				for (int i = 0; i < borangE.length; i++) {						
						if (bolehsimpan.equals("yes")) 
						{
							logic.updateBorang(borangE[i],(String) session.getAttribute("_ekptg_user_id"),"borangE");  									
						}
				}
			 }	
			
			if (borangG != null) {
				
				
				for (int i = 0; i < borangG.length; i++) {						
						if (bolehsimpan.equals("yes")) 
						{
							logic.updateBorang(borangG[i],(String) session.getAttribute("_ekptg_user_id"),"borangG");  									
						}
				}
			 }	
			
			if (borangK != null) {
				
				
				for (int i = 0; i < borangK.length; i++) {						
						if (bolehsimpan.equals("yes")) 
						{
							logic.updateBorang(borangK[i],(String) session.getAttribute("_ekptg_user_id"),"borangK");  									
						}
				}
			 }	
			
							
			
			
			
			
				
		}  	
    	//check size bahagian pb
    	modelHM.setSizeBahagianPB(idHakmilik);
		checkSizeBahagianPB = modelHM.getSizeBahagianPB();	
    	context.put("checkSizeBahagianPB_size",checkSizeBahagianPB.size());
    	
    	getTotalSyer_ALL(idHakmilik,"");
    	
    	senarai_pihak_penting_bahagian = logic.senarai_pihak_penting_bahagian(getParam("id_hakmilik"));
		this.context.put("senarai_pihak_penting_bahagian",senarai_pihak_penting_bahagian);	
    	
			context.put("id_siasatan",getParam("id_siasatan"));
			//list_kehadiran = logic.list_PB(getParam("id_hakmilik"),getParam("CariPB"));
			//this.context.put("list_kehadiran",list_kehadiran);
			list_kehadiran_th = logic.list_siasatan_pb(getParam("id_siasatan"));
			this.context.put("list_kehadiran_th",list_kehadiran_th);
			list_check_kehadiran = logic.list_check_kehadiran(getParam("id_siasatan"));
			this.context.put("list_check_kehadiran",list_check_kehadiran);
			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
			this.context.put("maklumat_siasatan",maklumat_siasatan);	
			list_jenispb = logic.list_jenispb();
			this.context.put("list_jenispb",list_jenispb);
			list_jenisnopb = logic.list_jenisnopb();
			this.context.put("list_jenisnopb",list_jenisnopb);  
			list_bank = logic.list_bank();
			this.context.put("list_bank",list_bank);    			
			this.context.put("tajuk_header","MAKLUMAT PIHAK BERKEPENTINGAN");
			
			this.context.put("list_kehadiran_size", logic.count_list_PB(
					getParam("id_hakmilik"), getParam("CariPB")));
			
			
			list_bangsa = logic.getListbangsa();
		   	context.put("list_bangsa",list_bangsa);  
		   	list_negeri = logic.getListnegeri();
	       	context.put("list_negeri",list_negeri);					
		   	list_warga = logic.getListwarga();
		   	context.put("list_warga",list_warga); 

			
			
	   // vm = "app/ppt/frmSek8InfoBicaraPB.jsp";	
		   	vm = mainscreen;
			
			}
    	
    	else 
    	if("maklumatPermintaanUkur".equals(submit)){
    		//data hakmilik dan borang k
    		dataHMBorangK(idHakmilik);
    	    		
     		//validation new / view form
     		//data tblpptpermintaanukur
     		model.setDataPermintaanUkur(idHakmilik);
     		dataPermintaanUkur = model.getDataPermintaanUkur();

     		//data permintaan ukur
			dataPU(idHakmilik);
			//dataHakmilik(idHakmilik, "disabled");
			
			
			//list No.Pelan by permohonan
			ListNoPelan(idpermohonan,idHakmilik);
			String socUnitLuasAmbil =getParam("socUnitLuasAmbil");
		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
     		
     		//NEW
     		if(dataPermintaanUkur.size()==0){
     			//form validation
         		context.put("mode","new");
        		context.put("isEdit","no");
        		
        		context.put("showFieldAmbilBeforeConvert", "no");
				context.put("showDropdownUnitAmbil", "no");
				
    			context.put(
    					"selectUnitLuasAmbil",
    					HTML.SelectLuas("socUnitLuasAmbil", null,
    							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
    		
     			
        		if("simpanMaklumatPU".equals(submit2)){
        				
        			if (doPost.equals("true")) {
            			//simpan PU
        				simpanMaklumatPU(session,id_status,idpermohonan);
        				/*
        				if(id_status.equals("76") || id_status.equals("72")){
        					updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
        		    	}
        		    	*/
        				if(modelUPT.cekStatusFailDahWujud(idpermohonan,"82","52")==false)
	            		{
	        			modelUPT.updateStatus(idpermohonan,id_user, "82");
	        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
	            		}
        				updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik);
        				dataHakmilikSambungan(idHakmilik, "disabled");
        				
        				context.put("showFieldAmbilBeforeConvert", "yes");
        				context.put("showDropdownUnitAmbil", "yes");
        				
            		}
        			
        			//data permintaan ukur
        			dataPU(idHakmilik);
        			id_permintaanukur = dataPU(idHakmilik);
        			
        			//list No.Pelan by permohonan
        			ListNoPelan(idpermohonan,idHakmilik);
        			
        			//form validation
             		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		String luas_ambil = getParam("txtLuasLotAmbil");
            		
            		if(luas_ambil !=""){
            		context.put("showFieldAmbilBeforeConvert", "yes");
            		}
        			
        		}//close simpanMaklumatPU
        		
        		else if("hapusNoPelan".equals(submit2)){
        			
        			hapusNoPelan();
        			
        			//form validation
             		context.put("mode","new");
            		
        			//data hakmilik dan borang k
            		dataHMBorangK(idHakmilik);
            		
             		//validation new / view form
             		//data tblpptpermintaanukur
             		model.setDataPermintaanUkur(idHakmilik);
             		dataPermintaanUkur = model.getDataPermintaanUkur();

             		//data permintaan ukur
        			dataPU(idHakmilik);
             		
        			//list No.Pelan by permohonan
        			ListNoPelan(idpermohonan,idHakmilik);
        			
        		}//close hapusNoPelan
        		
        		else if("doOnchange".equals(submit2)){
        			
        		
        			//penambahan yati
             		context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil", Utils.parseLong(socUnitLuasAmbil), null, "style=width:auto onChange=\"onchangeUnitLuasAmbil(this.value,'socUnitLuasAmbil');\""));
    				// check validation convert
    				checkValConvert();
    				
    				String submit3 = getParam("command3");
    				myLogger.info("submit[3] : " + submit3);

    				if ("onchangeUnitLuasAmbil".equals(submit3)) {
    				
    					// validations for luas ambil
    					validationConvertLuasAmbil();

    					String submit4 = getParam("command4");
    					myLogger.info("submit[4] : " + submit4);

    					if ("convertNilaiAmbil".equals(submit4)) {
    						
    						calculateNilaiAmbil();

    					}// close convertNilaiAmbil

    					else if ("clearConvertAmbil".equals(submit4)) {

    						clearConvertAmbil("new");

    					}// close clearConvertAmbil

    					else if ("onchangeUnitAmbil".equals(submit4)) {

    						// convert nilai lain
    						changeUnitAmbil();

    					}// close onchangeUnitAmbil

    				}// close onchangeUnitLuasAmbil

        		
        		}
     		//VIEW	
     		}
     		else{  			
     			//form validation
         		context.put("mode","view");
        		context.put("isEdit","no");
        		
        		dataHakmilikSambungan(idHakmilik, "disabled");

				context.put("showFieldAmbilBeforeConvert", "yes");
				context.put("showDropdownUnitAmbil", "yes");
     
        		if("kemaskiniMaklumatPU".equals(submit2)){
   
        			//form validation
             		context.put("mode","view");
            		context.put("isEdit","yes");
            		
            		dataPU(idHakmilik);
            		dataHakmilikSambungan(idHakmilik, "enabled");

        			context.put("showFieldAmbilBeforeConvert", "yes");
    				context.put("showDropdownUnitAmbil", "yes");
    				
            		String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
            		if("updateMaklumatPU".equals(submit3)){
            			
            			updateMaklumatPU(session,idpermohonan);
            			
            			//form validation
                 		context.put("mode","view");
                		context.put("isEdit","no");
                		
                		//list No.Pelan by permohonan
            			ListNoPelan(idpermohonan,idHakmilik);
            			
            			//data permintaan ukur
            			dataPU(idHakmilik);
            			dataHakmilikSambungan(idHakmilik, "disabled");
            			
        				context.put("showFieldAmbilBeforeConvert", "yes");
        				context.put("showDropdownUnitAmbil", "yes");
            			
            		}//close updateMaklumatPU
        			
            		else if("hapusNoPelan".equals(submit3)){
            			
            			hapusNoPelan();
            			
            			//list No.Pelan by permohonan
            			ListNoPelan(idpermohonan,idHakmilik);
            			
            			//form validation
                 		context.put("mode","view");
                		context.put("isEdit","yes");
            			
            		}//close hapusNoPelan
            		
            		else if("doOnchangeUpdate".equals(submit3)){
                		
            			//penambahan yati
            			socUnitLuasAmbil =getParam("socUnitLuasAmbil"); 
               
            			context.put(
            					"selectUnitLuasAmbil",
            					HTML.SelectLuas("socUnitLuasAmbil",
            							Utils.parseLong(getParam("socUnitLuasAmbil")),
            							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
        				
            			// check validation convert
        				checkValConvert();
        				
        				String submit4 = getParam("command4");
        				myLogger.info("submit[4] : " + submit4);
        				
        				if ("onchangeUnitLuasAmbilUpdate".equals(submit4)) {
        		
        					// validations for luas ambil
        					validationConvertLuasAmbil();

        					String submit5 = getParam("command5");
        					myLogger.info("submit[5] : " + submit5);

        					if ("convertNilaiAmbilUpdate".equals(submit5)) {

        						calculateNilaiAmbil();

        					}// close convertNilaiAmbil

        					else if ("clearConvertAmbilUpdate".equals(submit5)) {

        						clearConvertAmbil("view");

        					}// close clearConvertAmbil

        					else if ("onchangeUnitAmbilUpdate".equals(submit5)) {

        						// convert nilai lain
        						changeUnitAmbil();

        					}// close onchangeUnitAmbil

        				}// close onchangeUnitLuasAmbil

            		}
            		
        		}//close kemaskiniMaklumatPU
        		      		
     		}//close new / view
     		
    		//screen
    		vm = mainscreen;
    		
    	}//close maklumatPermintaanUkur
    	
    	else 
        if("maklumatPenyelesaian".equals(submit)){
        		
        	String tarikh_terima_pa = "";
        	String no_pa = "";
        	String tarikh_terima_sa = "";
        	String tarikh_selesai = "";
        	String catatan = "";
        	String no_lot_baru = "";
        	model.setDataPermintaanUkur(idHakmilik);
     		dataPermintaanUkur = model.getDataPermintaanUkur();
     		context.put("dataPermintaanUkur", dataPermintaanUkur);
     		if(dataPermintaanUkur.size()!=0){
     			Hashtable dpu = (Hashtable)dataPermintaanUkur.get(0);
     			tarikh_terima_pa = (String)dpu.get("tarikh_terima_pa");
     			no_pa = (String)dpu.get("no_pa");
     			tarikh_terima_sa = (String)dpu.get("tarikh_terima_sa");
     			tarikh_selesai = (String)dpu.get("tarikh_selesai");
     			catatan = (String)dpu.get("catatan");
     			no_lot_baru = (String)dpu.get("no_lot_baru");
     			id_permintaanukur = (String)dpu.get("id_permintaanukur");
     			jenis_pelarasan = (String)dpu.get("jenis_pelarasan");
     		}
        	
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
     		//NEW FORM
     		if(tarikh_terima_pa=="" && no_pa=="" && tarikh_terima_sa=="" && tarikh_selesai=="" && catatan=="" && no_lot_baru==""){
     			
     			//form validation
         		context.put("mode","new");
        		context.put("isEdit","no");
     			
     		//VIEW FORM	
     		}else{
     			
     			//form validation
         		context.put("mode","view");
        		context.put("isEdit","no");
     			
        		if("kemaskiniPenyelesaian".equals(submit2)){
        			
        			//form validation
             		context.put("mode","view");
            		context.put("isEdit","yes");
        			
        		}//close kemaskiniPenyelesaian
        		
     		}//close new/view
     		
     		
     		if("updatePenyelesaian".equals(submit2)){
    			
    			if (doPost.equals("true")) {
    				updatePenyelesaian(session);
        		}
    			
    			//data permintaan ukur
    			dataPU(idHakmilik);
    			
    			//form validation
         		context.put("mode","view");
        		context.put("isEdit","no");
    			
    		}//close updatePenyelesaian
     		
        	//screen
    		vm = mainscreen;
        	
        }//close maklumatPenyelesaian
    	
        else 
    		if("getPelarasan".equals(submit)){ 
    			context.put("mode",getParam("mode"));
        		context.put("isEdit",getParam("isEdit"));
        		context.put("jenisPU","internal");	
        		
        		myLogger.info("MODE :"+getParam("mode"));
        		myLogger.info("ISEDIT :"+getParam("isEdit"));
        		
    			dataHMBorangK(idHakmilik);
    			dataPU(idHakmilik);
    			listPB(idHakmilik,"");
    			Hashtable getNilaiSeunit = null;
    			getNilaiSeunit = (Hashtable)getNilaiSeunit(idHakmilik);			
				context.put("getNilaiSeunit",(String)getNilaiSeunit.get("nilai"));
				context.put("NilaiSeunit",(String)getNilaiSeunit.get("nilaiseunit"));
    			
				myLogger.info("TEST :"+getParam("selectedTabPelarasan"));
				context.put("selectedTabPelarasan",getParam("selectedTabPelarasan"));
    			vm = "app/ppt/PermintaanUkur/skrin_pelarasan.jsp";	    			
    		}
    	
		else 
		if("pelarasanLuas".equals(submit)){
			myLogger.info("TEST :"+getParam("selectedTabPelarasan"));
			context.put("selectedTabPelarasan",getParam("selectedTabPelarasan"));	
			//data hakmilik dan borang k
			dataHMBorangK(idHakmilik);
			
			//data permintaan ukur
			dataPU(idHakmilik);
			
			String luas_pu = "";
        	model.setDataPermintaanUkur(idHakmilik);
     		dataPermintaanUkur = model.getDataPermintaanUkur();
     		context.put("dataPermintaanUkur", dataPermintaanUkur);
     		if(dataPermintaanUkur.size()!=0){
     			Hashtable dpu = (Hashtable)dataPermintaanUkur.get(0);
     			luas_pu = (String)dpu.get("luas_pu");
     			id_permintaanukur = (String)dpu.get("id_permintaanukur");
     			jenis_pelarasan = (String)dpu.get("jenis_pelarasan");
     		}
     		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
     		
     		//NEW
     		if(luas_pu==""){
     			
     			//form validation
         		context.put("mode","new");
        		context.put("isEdit","no");
        	
     		//VIEW	
     		}else{
     			
     			//form validation
         		context.put("mode","view");
        		context.put("isEdit","no");
     			
        		if("kemaskiniPelarasanLuas".equals(submit2)){
        			
        			//form validation
             		context.put("mode","view");
            		context.put("isEdit","yes");
            		
        		}//close kemaskiniPelarasanLuas
        		
     		}//close new /view
			
     		if("updatePelarasanLuas".equals(submit2)){
    			myLogger.info(" doPost :"+doPost);
    			if (doPost.equals("true")) {
    				myLogger.info(" MASUK :"+doPost);
    				updatePelarasanLuas(session);
    				
    				
    				Vector listPB = new Vector();
    				listPB.clear();		
    				modelHM.listPBwithAward(idHakmilik,namaPB);
    		    	listPB = modelHM.getListPBwithAward();
    				
    		    	if(getParam("hdJenisPelarasan").equals("1"))
    		    	{
	    				if(listPB.size()>0)
	    	        	{
	    					String id_award_x = "";
	    	        		String penama_x = "";
	    	        		String tempoh_lewat_x = "";
	    	        		String pampasan_tanah_x = "";
	    	        		String pampasan_lewat_x = "";
	    	        		String jumlah_pelarasan_x = "";
	    	        		
	    	        		for (int i = 1; i < (listPB.size()+1); i++) 
	    					{        			 
	    	        		id_award_x = getParam("id_award_all"+i);
	    	        		penama_x = getParam("text_penama_all"+i);
	    	        		tempoh_lewat_x = getParam("text_tempoh_lewat_all"+i);
	    	        		pampasan_tanah_x = getParam("text_pampasan_tanah_all"+i);
	    	        		pampasan_lewat_x = getParam("text_bhg_per_caj_all"+i);
	    	        		jumlah_pelarasan_x = getParam("text_total_all"+i);    	        		
	    	        		myLogger.info("id_award_x:"+getParam(id_award_x));
	    	        		myLogger.info("penama_x:"+getParam(penama_x));
	    	        		myLogger.info("tempoh_lewat_x:"+getParam(tempoh_lewat_x));
	    	        		myLogger.info("pampasan_tanah_x:"+getParam(pampasan_tanah_x));
	    	        		myLogger.info("pampasan_lewat_x:"+getParam(pampasan_lewat_x));
	    	        		myLogger.info("jumlah_pelarasan_x"+getParam(jumlah_pelarasan_x));
	    	        		model.updatePelarasanPB_auto(session.getAttribute("_ekptg_user_id").toString(),id_award_x,penama_x,pampasan_tanah_x,tempoh_lewat_x,jumlah_pelarasan_x);
	    					}        		
	    	        	} 
    		    	}
    				
        		}
    			
    			//data permintaan ukur
    			dataPU(idHakmilik);
    			
    			//update validation tab
    			model.setDataPermintaanUkur(idHakmilik);
    	 		dataPermintaanUkur = model.getDataPermintaanUkur();
    	 		if(dataPermintaanUkur.size()!=0){
    	 			Hashtable dpu = (Hashtable)dataPermintaanUkur.get(0);
    	 			jenis_pelarasan = (String)dpu.get("jenis_pelarasan");
    	 			id_permintaanukur = (String)dpu.get("id_permintaanukur");
    	 			jenis_pelarasan = (String)dpu.get("jenis_pelarasan");
    	 		}
    			
    			//form validation
         		context.put("mode","view");
        		context.put("isEdit","no");
    			
    		}//close updatePelarasanLuas
    		
    		//screen
    		vm = mainscreen;
    		
		}//close pelarasanLuas
    	
		else 
		if("pelarasanPB".equals(submit)){
				
			//validation pb
			context.put("showDetail", "no");
			
    		namaPB = getParam("carianPBPelarasan");
    		context.put("carianPBPelarasan", namaPB.trim());
    		
    		//list pb
    		listPB(idHakmilik,namaPB);
			
    		//data permintaan ukur
			dataPU(idHakmilik);
			
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
    		
        	if("selectPB".equals(submit2)){
        		
        		//validation pb
    			context.put("showDetail", "yes");
    			context.put("PampasanTanah", "");
    			
    			String id_hakmilikpb = getParam("id_hakmilikpb");
    			context.put("id_hakmilikpb",id_hakmilikpb);
    			dataDetailPB(id_hakmilikpb);
    			
        		String penama = "";
        		model.setDataDetailPB(id_hakmilikpb);
        		dataDetailPB = model.getDataDetailPB();
        		if(dataDetailPB.size()!=0){
        			Hashtable dpb = (Hashtable)dataDetailPB.get(0);
        			penama = (String)dpb.get("penama");
        		}
        		
        		//get jumlah pelarasan + faedah
        		getPelarasanValue(idHakmilik);
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
         		//NEW
         		if(penama==""){
         			
         			//form validation
             		context.put("mode","new");
            		context.put("isEdit","no");
         			
         		//VIEW	
         		}else{
         			
         			//form validation
             		context.put("mode","view");
            		context.put("isEdit","no");
         			
            		if("kemaskiniPelarasanPB".equals(submit3)){
            			
            			//form validation
                 		context.put("mode","view");
                		context.put("isEdit","yes");
            			
            		}//close kemaskiniPelarasanPB
            		
         		}
         		
         		if("updatePelarasanPB".equals(submit3)){
        			
        			if (doPost.equals("true")) {
        				updatePelarasanPB(session);
            		}
        			
        			//validation pb
        			context.put("showDetail", "yes");
        			
        			//detail pb
        			dataDetailPB(id_hakmilikpb);
        			
        			//form validation
             		context.put("mode","view");
            		context.put("isEdit","no");
        			
        		}//close updatePelarasanLuas
         		
        	}//close selectPB
        	
			//screen
    		vm = mainscreen;
			
		}//close pelarasanPB
    	
		else 
		if("susulan".equals(submit)){
				
			//validation pb
			context.put("showDetail", "no");
			
    		//data permintaan ukur
			dataPU(idHakmilik);
			
			//carian pb
			namaPB = getParam("carianPBSusulan");
    		context.put("carianPBSusulan", namaPB.trim());
    		
    		//get jumlah pelarasan untuk agensi bayar
    		jumlahPelarasanAP(idHakmilik);
    		
    		//list pb
    		listPB(idHakmilik,namaPB);
			
			String tarikh_surat_susulan = "";
			String no_subjaket = "";
        	model.setDataPermintaanUkur(idHakmilik);
     		dataPermintaanUkur = model.getDataPermintaanUkur();
     		context.put("dataPermintaanUkur", dataPermintaanUkur);
     		if(dataPermintaanUkur.size()!=0){
     			Hashtable dpu = (Hashtable)dataPermintaanUkur.get(0);
     			tarikh_surat_susulan = (String)dpu.get("tarikh_surat_susulan");
     			no_subjaket = (String)dpu.get("no_subjaket");
     			id_permintaanukur = (String)dpu.get("id_permintaanukur");
     			jenis_pelarasan = (String)dpu.get("jenis_pelarasan");
     		}
     		
     		//no rujukan default
     		if(id_projekNegeri.equals("10")){
     			context.put("no_subjaket", no_rujukan_ptg+" Sj."+no_subjaket);
     		}else{
     			context.put("no_subjaket", no_fail+" Sj."+no_subjaket);
     		}
     		
     		
     		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
     		//NEW
     		if(tarikh_surat_susulan==""){
     			
     			//form validation
         		context.put("mode","new");
        		context.put("isEdit","no");
     			
     		//VIEW	
     		}else{
     			
     			//form validation
         		context.put("mode","view");
        		context.put("isEdit","no");
     			
        		if("kemaskiniSusulan".equals(submit2)){
        			
        			//form validation
             		context.put("mode","view");
            		context.put("isEdit","yes");
        			
        		}//close kemaskiniSusulan

     		}//close new / view
			
     		if("selectPBSusulan".equals(submit2)){
    			
    			//validation pb
    			context.put("showDetail", "yes");
    			
    			//carian pb
    			namaPB = getParam("carianPBSusulan");
        		context.put("carianPBSusulan", namaPB.trim());
        		
        		//list pb
        		listPB(idHakmilik,namaPB);
    			
    			String id_hakmilikpb = getParam("id_hakmilikpb");
    			context.put("id_hakmilikpb", id_hakmilikpb);
    			dataDetailPB(id_hakmilikpb);
    			
        		String tarikh_surat = "";
        		model.setDataDetailPB(id_hakmilikpb);
        		dataDetailPB = model.getDataDetailPB();
        		if(dataDetailPB.size()!=0){
        			Hashtable dpb = (Hashtable)dataDetailPB.get(0);
        			tarikh_surat = (String)dpb.get("tarikh_surat");
        		}
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
        		//NEW
        		if(tarikh_surat==""){
        			
        			//form validation for pb susulan
        			context.put("modePB","new");
            		context.put("isEditPB","no");
        			
        		//VIEW	
        		}else{
        			
        			//form validation for pb susulan
        			context.put("modePB","view");
            		context.put("isEditPB","no");
        			
            		if("kemaskiniPBSusulan".equals(submit3)){
            			
            			//form validation for pb susulan
            			context.put("modePB","view");
                		context.put("isEditPB","yes");
                		
            		}//kemaskiniPBSusulan
            		
        		}//close new /view
        		
        		if("updatePBSusulan".equals(submit3)){
        			
        			if (doPost.equals("true")) {
        				updatePBSusulan(session);
            		}
        			
        			//form validation for pb susulan
        			context.put("modePB","view");
            		context.put("isEditPB","no");
            		
            		//validation pb
        			context.put("showDetail", "yes");
        			
            		//data pb
            		dataDetailPB(id_hakmilikpb);
        			
        		}//close updatePBSusulan
        		
    		}//close selectPBSusulan
     		
     		else if("updateSusulan".equals(submit2)){
     			
     			if (doPost.equals("true")) {
     				updateSusulan(session);
        		}
    			
    			//validation pb
    			context.put("showDetail", "no");
    			
    			//data permintaan ukur
    			dataPU(idHakmilik);
    			
    			//form validation
         		context.put("mode","view");
        		context.put("isEdit","no");
     			
     		}//close updateSusulan
     		
     		
			//screen
    		vm = mainscreen;
			
		}//close susulan
    	
		else 
		if("screenUpload".equals(submit)){
				
    		//list dokumen
    		ListDokumen(id_permintaanukur);
			
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("uploadFile".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			//upload file
            		uploadFiles(id_permintaanukur);
             	}

        		//list dokumen
        		ListDokumen(id_permintaanukur);
        		
        	}//close uploadFile
        	
			//screen
    		vm = mainscreen;
			
		}//close screenUpload
    	
		else
	    if("hapusDokumen".equals(submit)){
	        		
	    	hapusDokumen(session);

	    	//list dokumen
	    	ListDokumen(id_permintaanukur);
	    		
	        //screen
	        vm = mainscreen;
	        
	    }//close hapusDokumen
    	
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
	    	
	    	//tab
	    	if(id_permintaanukur!=""){
	    		context.put("showAllTab","yes");
	    	}else{
	    		context.put("showAllTab","no");
	    	}
	    	
	    	//id
	    	context.put("id_permohonan", idpermohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_fail", id_fail);
	    	context.put("id_hakmilik", idHakmilik);
	    	context.put("id_permintaanukur",id_permintaanukur);
	    	context.put("valTab",jenis_pelarasan);
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		myLogger.info("VM pu : "+vm);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
	Hashtable getNilaiSeunit = null;
	public Hashtable getNilaiSeunit(String id_hakmilik) throws Exception {
		getNilaiSeunit = new Hashtable();
		getNilaiSeunit.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT S.ID_HAKMILIK, "+
					" TO_CHAR(S.NILAI_SEUNIT) AS NILAI_SEUNIT, "+
					" TRIM(TO_CHAR(S.NILAI_SEUNIT,'999,999,999,990.99')) AS RM_NILAI_SEUNIT, "+
					" TO_CHAR(S.UNIT_TANAH) AS UNIT_TANAH_NILAI,  "+
					" TO_CHAR(S.LUAS_MUKTAMAD) AS LUAS_MUKTAMAD,  "+
					" TRIM(TO_CHAR(S.LUAS_MUKTAMAD,'999999999990.9999')) AS FIX_LUAS_MUKTAMAD,  "+
					" TO_CHAR(S.ID_UNIT_LUAS_MUKTAMAD) AS UNIT_TANAH_LUAS  "+
					" FROM TBLPPTSIASATAN S  "+
					" WHERE S.ID_HAKMILIK = '"+id_hakmilik+"'   "+
					" AND S.BIL_SIASATAN = (SELECT MAX(BIL_SIASATAN) FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+id_hakmilik+"' ) ";
			/*
			unit 1 - Hektar
			unit 2 - Meter Persegi
			unit 3 - Ekar
			unit 4 - Kaki Persegi
			*/
			
			myLogger.info("getNilaiSeunit ::::: "+sql.toUpperCase());	
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				
				
				double nilaiseunit = 0;
				String nilai = "0.00";
				//hektar - meter
				if(rs.getString("NILAI_SEUNIT") != null){					
					if(rs.getString("UNIT_TANAH_NILAI").equals("1")){
						nilaiseunit = rs.getDouble("NILAI_SEUNIT") / 10000;
					}
					else if(rs.getString("UNIT_TANAH_NILAI").equals("4")){
						nilaiseunit = rs.getDouble("NILAI_SEUNIT") / 0.0929034;
					}
					else if(rs.getString("UNIT_TANAH_NILAI").equals("2")){
						nilaiseunit = rs.getDouble("NILAI_SEUNIT");
					}
					else if(rs.getString("UNIT_TANAH_NILAI").equals("3")){
						nilaiseunit = rs.getDouble("NILAI_SEUNIT")/4046.8564;
					}				
					nilai = Utils.formatAnyDecimal(nilaiseunit);						
				}			
				h.put("nilai","RM "+nilai);
				h.put("nilaiseunit",nilaiseunit+"");
				
				
				
				
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void hapusDokumen(HttpSession session) throws Exception{
	    
		Hashtable h = new Hashtable();		
		h.put("id_dokumen", getParam("id_dokumen"));		
		modelUPT.hapusDokumen(h);
		
	}//close hapusdokumen
	
	@SuppressWarnings("unchecked")
	private void uploadFiles(String id_permintaanukur) throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData(item,id_permintaanukur);
		      }
		    }
		  }
	 private void saveData(FileItem item,String id_permintaanukur) throws Exception {
	  		Db db = null;
	        try {
	        	db = new Db();
	        	long id_dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN " +
	        			"(id_dokumen,id_permintaanukur,nama_Fail,jenis_Mime,content,tajuk,keterangan) " +
	        			"values(?,?,?,?,?,?,?)");
	        	ps.setLong(1, id_dokumen);
	        	ps.setString(2, id_permintaanukur);
	        	ps.setString(3,item.getName());
	        	ps.setString(4,item.getContentType());
	        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
	        	ps.setString(6, getParam("txtNamaDokumen"));
	        	ps.setString(7, getParam("txtKeterangan"));
	        	ps.executeUpdate();
	            con.commit();
	            
		    }catch (SQLException se) { 
		    	throw new Exception("Ralat : Masalah muatnaik fail");
		    }finally {
			      if (db != null) db.close();
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
		
	 
	@SuppressWarnings({"unchecked", "static-access"})
	private void ListDokumen(String idpermintaanukur) throws Exception{
    	/*
		Vector listDokumen = new Vector();
		listDokumen.clear();
		
		model.setListDokumen(idpermintaanukur);
 		listDokumen = model.getListDokumen();
		context.put("listDokumen", listDokumen);
		context.put("listD_size", listDokumen.size());
		*/
		
	}//close ListDokumen
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelUPT.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"1489");
	
	}//close addSuburusanHakmilik
	
	@SuppressWarnings("unchecked")
	private void getPelarasanValue(String idHakmilik) throws Exception{
		
		Vector dataSiasatan = new Vector();
		Vector dataHakmilikBorangK = new Vector();
		Vector dataPermintaanUkur = new Vector();
		
		dataPermintaanUkur.clear();
		dataHakmilikBorangK.clear();
		dataSiasatan.clear();
		
		//get harga semeter/hektar tanah
		double nilai_seunit = 0;
		String unit_tanah = "";
		model.setDataSiasatan(idHakmilik);
		dataSiasatan = model.getDataSiasatan(); 
		if(dataSiasatan.size()!=0){
			Hashtable ds = (Hashtable)dataSiasatan.get(0);
			nilai_seunit = (Double)ds.get("NILAI_SEUNIT");
			unit_tanah = (String)ds.get("UNIT_TANAH");
		}
		
		
		//get baki luas asal(hektar)
		String baki_luas_asal = "";
		modelBorangK.setDataBorangK(idHakmilik);
 		dataHakmilikBorangK = modelBorangK.getDataBorangK();
 		if(dataHakmilikBorangK.size()!=0){
			Hashtable dH = (Hashtable)dataHakmilikBorangK.get(0);
			baki_luas_asal = (String)dH.get("baki_luas_asal");
 		}
 		
 		double bakiLuasAsal = 0;
 		if(baki_luas_asal!=""){
 			if(unit_tanah.equals("2")){
 				bakiLuasAsal = Double.parseDouble(baki_luas_asal) * 10000 ;
 			}else{
 				bakiLuasAsal = Double.parseDouble(baki_luas_asal);
 			}
 			
 		}
 		
		
		//get luas pu(hektar)
 		String luas_pu = "";
 		String id_permintaanukur = "";
 		String jenis_pelarasan = "";
 		model.setDataPermintaanUkur(idHakmilik);
 		dataPermintaanUkur = model.getDataPermintaanUkur();
 		if(dataPermintaanUkur.size()!=0){
 			Hashtable dpu = (Hashtable)dataPermintaanUkur.get(0);
 			luas_pu = (String)dpu.get("luas_pu");
 			id_permintaanukur = (String)dpu.get("id_permintaanukur");
 			jenis_pelarasan = (String)dpu.get("jenis_pelarasan");
 		}
		
 		double luasPU = 0;
 		if(luas_pu!=""){
 			if(unit_tanah.equals("2")){
 				luasPU = Double.parseDouble(luas_pu) * 10000 ;
 			}else{
 				luasPU = Double.parseDouble(luas_pu);
 			}				
 		}
 		
		//baki luas asal - luas pu = luas baru(meter/hektar) * harga semeter tanah
		double hargaPelarasan = (bakiLuasAsal - luasPU) * nilai_seunit;
		
		context.put("PampasanTanah", Utils.format2Decimal(hargaPelarasan));
		
	}//close getPelarasanValue
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1489");
		
	}//close updateSuburusanStatusFailPPT
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmSek8PermintaanUkurData.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	
	@SuppressWarnings("static-access")
	private void hapusNoPelan() throws Exception{
		
		String id_nopelan = getParam("id_nopelan");
		
		model.hapusNoPelan(id_nopelan);
		
	}//close hapusNoPelan
	
	@SuppressWarnings("unchecked")
	private void ListNoPelan(String ipermohonan,String id_hakmilik) throws Exception{
		
		Vector listNoPelan = new Vector();
		listNoPelan.clear();
		
		model.setListNoPelan(ipermohonan,id_hakmilik);
 		listNoPelan = model.getListNoPelan();   		
		context.put("listNoPelan",listNoPelan);
		context.put("listNoPelan_size",listNoPelan.size());
		
	}//close ListNoPelan
	
	
	@SuppressWarnings("unchecked")
	private String dataPU(String idHakmilik) throws Exception{
		Vector dataPermintaanUkur = new Vector();
		dataPermintaanUkur.clear();
		
		String id_permintaanukur = "";
		
		model.setDataPermintaanUkur(idHakmilik);
 		dataPermintaanUkur = model.getDataPermintaanUkur();
 		context.put("dataPermintaanUkur", dataPermintaanUkur);
 		if(dataPermintaanUkur.size()!=0){
 			Hashtable dpu = (Hashtable)dataPermintaanUkur.get(0);
 			id_permintaanukur = (String)dpu.get("id_permintaanukur");
 		}
 		
 		context.put("id_permintaanukur",id_permintaanukur);
 		context.put("txdTarikhSuratPTG", getParam("txdTarikhSuratPTG"));
 		context.put("txdTarikhHantarJUPEM", getParam("txdTarikhHantarJUPEM"));
 		context.put("txtNoPU", getParam("txtNoPU"));
 		context.put("txdTarikhBorangPU", getParam("txdTarikhBorangPU"));
 		context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
 		context.put("txtLotBaru", getParam("txtLotBaru"));
 		
 		return id_permintaanukur;
 		
	}//close listMaklumatTanah
	
	
	//penambahan yati
	
	@SuppressWarnings("unchecked")
	private void dataHakmilikSambungan(String idHakmilik, String disability)
			throws Exception {

 		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();
		
		String id_unitluasambil ="";
 		modelUPT.setMaklumatTanah(idHakmilik);
 		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		if (dataMaklumatTanah.size() != 0) {
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_unitluasambil = (String)h.get("id_unitluasambil");		
		}
		
		String mode = "";
		if (disability.equals("enabled")) {
			mode = "";
		} else {
			mode = "disabled class=disabled";
		}

		context.put(
				"selectUnitLuasAmbil",
				HTML.SelectLuas(
						"socUnitLuasAmbil",
						Utils.parseLong(id_unitluasambil),
						"style=width:250px "
								+ mode
								+ " id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));

	}// close dataHakmilikSambungan

	@SuppressWarnings("unchecked")
	private void dataHMBorangK(String idHakmilik) throws Exception{
		
		Vector dataHakmilikBorangK = new Vector();
		dataHakmilikBorangK.clear();
		
		modelBorangK.setDataBorangK(idHakmilik);
 		dataHakmilikBorangK = modelBorangK.getDataBorangK();
 		context.put("dataHakmilikBorangK",dataHakmilikBorangK);
 		
	}//close dataHMBorangK
	
	@SuppressWarnings("unchecked")
	private void listMaklumatTanah(String idpermohonan,String noLOT) throws Exception{
    /*
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();
		
		modelUPT.setListHMwithIdBorangK(idpermohonan,noLOT);
 		listMaklumatTanah = modelUPT.getListHMwithIdBorangK();
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 		*/
 		context.put("saiz_listTanah", modelUPT.setListHMwithIdBorangK_count(idpermohonan,noLOT));
 		
	}//close listMaklumatTanah
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPB(String idHakmilik,String namaPB) throws Exception{    	
		Vector listPB = new Vector();
		listPB.clear();		
		modelHM.listPBwithAward(idHakmilik,namaPB);
    	listPB = modelHM.getListPBwithAward();
    	myLogger.info(" listPB.size() :"+listPB.size());
    	context.put("saiz_pb", listPB.size());
    	context.put("listMaklumatPB", listPB);   	
	}//close listPB
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanMaklumatPU(HttpSession session,String id_status,String id_permohonan) throws Exception{

		Hashtable h = new Hashtable();
		
		long id_permintaanukur = DB.getNextID("TBLPPTPERMINTAANUKUR_SEQ");
		
		h.put("id_permintaanukur", String.valueOf(id_permintaanukur));
		h.put("id_permohonan", id_permohonan);
		h.put("id_hakmilik", getParam("id_hakmilik"));		
		h.put("txdTarikhSuratPTG", getParam("txdTarikhSuratPTG"));
		h.put("txtNoPelan", getParam("txtNoPelan"));
		h.put("txtNoJUPEM", getParam("txtNoJUPEM"));
		h.put("txdTarikhHantarJUPEM", getParam("txdTarikhHantarJUPEM"));	
		h.put("txtNoPU", getParam("txtNoPU"));
		h.put("txdTarikhBorangPU", getParam("txdTarikhBorangPU"));
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("txtLotBaru", getParam("txtLotBaru"));
		h.put("unitLuasAmbil", getParam("socUnitLuasAmbil"));
		h.put("txtLuasLotAmbilSebelumConvert", getParam("txtLuasLotAmbilSebelumConvert"));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));
		h.put("txtLuasLotAmbil", Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		if(id_status.equals("76") || id_status.equals("72")){
			model.updateStatus(h,getParam("id_permohonan"));
    	}
		
		//insert no pelan sediaada
		String[] id_nopelan = request.getParameterValues("cbsemaks");
		
		if((id_nopelan!=null)){
			for (int i = 0; i < id_nopelan.length; i++) { 
				model.simpanNoPelanLain(h,id_nopelan[i]);
			}
		}
		
		//main insert
		model.simpanMaklumatPU(h);
		
	}//close simpanMaklumatPU
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateMaklumatPU(HttpSession session,String id_permohonan) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);	
		h.put("id_permintaanukur", getParam("id_permintaanukur"));		
		h.put("txdTarikhSuratPTG", getParam("txdTarikhSuratPTG"));
		h.put("txtNoPelan", getParam("txtNoPelan"));
		h.put("txtNoJUPEM", getParam("txtNoJUPEM"));
		h.put("txdTarikhHantarJUPEM", getParam("txdTarikhHantarJUPEM"));
		h.put("txtNoPU", getParam("txtNoPU"));
		h.put("txdTarikhBorangPU", getParam("txdTarikhBorangPU"));
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("txtLotBaru", getParam("txtLotBaru"));
		h.put("txtLuasAmbil", Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//delete and insert no pelan sediaada
		model.deleteNoPelanLain(h);
		
		String[] id_nopelan = request.getParameterValues("cbsemaks");
		
		if((id_nopelan!=null)){
			for (int i = 0; i < id_nopelan.length; i++) { 
				model.simpanNoPelanLain(h,id_nopelan[i]);
			}
		}
		
		//main update
		model.updateMaklumatPU(h);
		
	}//close updateMaklumatPU
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updatePenyelesaian(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permintaanukur", getParam("id_permintaanukur"));	
		
		h.put("txdTarikhTerimaPA", getParam("txdTarikhTerimaPA"));
		h.put("txtNoPA", getParam("txtNoPA"));
		h.put("txdTarikhTerimaSA", getParam("txdTarikhTerimaSA"));		
		h.put("txdTarikhPenyelesaian", getParam("txdTarikhPenyelesaian"));
		h.put("txtCatatan", getParam("txtCatatan"));
		h.put("txtLotBaru", getParam("txtLotBaru"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.updatePenyelesaian(h);
		
	}//close updatePenyelesaian
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updatePelarasanLuas(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permintaanukur", getParam("id_permintaanukur"));	
		
		h.put("txtKeluasanPU", Utils.RemoveSymbol(getParam("txtKeluasanPU")));
		h.put("sorJenisPelarasan", getParam("hdJenisPelarasan"));
		h.put("countBezaLuas", Utils.RemoveSymbol(getParam("countBezaLuas")));		
		h.put("tarikhBayarTambahan", getParam("tarikhBayarTambahan"));		

		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.updatePelarasanLuas(h);
		
	}//close updatePelarasanLuas
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updatePelarasanPB(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_award", getParam("id_award"));	
		
		h.put("txtPenama", getParam("txtPenama"));
		h.put("txtUlasan", getParam("txtUlasan"));
		h.put("txdTarikhSedia", getParam("txdTarikhSedia"));		
		h.put("txtPampasanTanah", Utils.RemoveSymbol(getParam("txtPampasanTanah")));
		h.put("txtFaedahSebelum", Utils.RemoveSymbol(getParam("txtFaedahSebelum")));
		h.put("txtFaedahSelepas", Utils.RemoveSymbol(getParam("txtFaedahSelepas")));
		h.put("txtJumlahPelarasan", Utils.RemoveSymbol(getParam("txtJumlahPelarasan")));
		h.put("txtTempoh", getParam("txtTempoh"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		model.updatePelarasanPB(h);
		
	}//close updatePelarasanPB
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSusulan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_permintaanukur", getParam("id_permintaanukur"));	
		
		h.put("txtBilSurat", getParam("txtBilSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerimaAgensi", getParam("txdTarikhTerimaAgensi"));		
		h.put("txtTempohCek", getParam("txtTempohCek"));
		h.put("txdTarikhBayaranTambahan", getParam("txdTarikhBayaranTambahan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.updateSusulan(h);
		
	}//close updateSusulan
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updatePBSusulan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_award", getParam("id_award"));	
		
		h.put("txtBilSuratPB", getParam("txtBilSuratPB"));
		h.put("txdTarikhSuratPB", getParam("txdTarikhSuratPB"));
		h.put("txtTempohBayar", getParam("txtTempohBayar"));		
		h.put("sorStatusBayaran", getParam("sorStatusBayaran"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		model.updatePBSusulan(h);
		
	}//close updatePBSusulan
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void jumlahPelarasanAP(String idHakmilik) throws Exception{
    	
		Vector jumlahBayaran = new Vector();
		jumlahBayaran.clear();
		
		String total_bayar = "";
		model.setJumlahPelarasanAP(idHakmilik);
		jumlahBayaran = model.getJumlahPelarasanAP();
		if(jumlahBayaran.size()!=0){
			Hashtable dpb = (Hashtable)jumlahBayaran.get(0);
			total_bayar = (String)dpb.get("total_bayar");
		}

    	context.put("total_bayar",total_bayar);
		
	}//close jumlahPelarasanAP
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataDetailPB(String idHakmilikPB) throws Exception{
    	
		Vector dataDetailPB = new Vector();
		dataDetailPB.clear();
		
		String id_award = "";
		model.setDataDetailPB(idHakmilikPB);
		dataDetailPB = model.getDataDetailPB();
		if(dataDetailPB.size()!=0){
			Hashtable dpb = (Hashtable)dataDetailPB.get(0);
			id_award = (String)dpb.get("id_award");
		}
		context.put("id_award", id_award);
    	context.put("dataDetailPB", dataDetailPB);
    	
	}//close dataDetailPB
	
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
	/*
	private void getTotalSyer(String idHakmilik,String idpb) throws Exception{
    	
		Vector totalSyer = new Vector();
		totalSyer.clear();
		
		String total = "";
		String hideAdd = "";
		modelHM.setTotalSyer(idHakmilik,idpb);
		totalSyer = modelHM.getTotalSyer();
		if(totalSyer.size()!=0){
			Hashtable ts = (Hashtable)totalSyer.get(0);
			total = (String)ts.get("total");
			hideAdd = (String)ts.get("hideAdd");
		}
		
		if(total!="")
		{
    	context.put("totalSyer", total);
		}
		else
		{
		context.put("totalSyer", 0);				
		}
    	//context.put("hideAdd", hideAdd);
    	
    	if(hideAdd=="yes")
    	{
    	//	JOptionPane.showMessageDialog(null, "Bahagian PB Telah Mencukupi.", "Etapp", 2);
    		
    	}
    	else if(hideAdd=="notcomplete")
    	{
    	//	JOptionPane.showMessageDialog(null, "Bahagian PB Masih Tidak Mencukupi.", "Etapp", 2);	    		
    	}
    	
    	
    	
	}//close getTotalSyer
 */
	
	private void getTotalSyer(String idHakmilik, String idpb) throws Exception {

		Vector totalSyer = new Vector();
		totalSyer.clear();

		String total = "";
		String hideAdd = "";
		modelHM.setTotalSyer(idHakmilik, idpb);
		totalSyer = modelHM.getTotalSyer();
		if (totalSyer.size() != 0) {
			Hashtable ts = (Hashtable) totalSyer.get(0);
			total = (String) ts.get("total");
			hideAdd = (String) ts.get("hideAdd");
		}
		context.put("totalSyer", total);
		context.put("hideAdd", hideAdd);

	}// close getTotalSyer
	
	private void getTotalSyer_ALL(String idHakmilik, String idpb)
			throws Exception {

		Vector totalSyer = new Vector();
		totalSyer.clear();

		String total = "";
		String hideAdd = "";
		modelHM.setTotalSyer(idHakmilik, idpb);
		totalSyer = modelHM.getTotalSyer();
		if (totalSyer.size() != 0) {
			Hashtable ts = (Hashtable) totalSyer.get(0);
			total = (String) ts.get("total");
			hideAdd = (String) ts.get("hideAdd");
		}
		context.put("totalSyer", total);

	}// close getTotalSyer
 
 
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getTotalSyer_Temp(String idHakmilik, String idpb)
			throws Exception {

		Vector totalSyer = new Vector();
		totalSyer.clear();

		String total = "";
		String hideAdd = "";
		modelHM.setTotalSyer(idHakmilik, idpb);
		totalSyer = modelHM.getTotalSyer();
		if (totalSyer.size() != 0) {
			Hashtable ts = (Hashtable) totalSyer.get(0);
			total = (String) ts.get("total");
			hideAdd = (String) ts.get("hideAdd");
		}

		if (total != "") {
			context.put("totalSyer", total);
		} else {
			context.put("totalSyer", 0);
		}

		//context.put("hideAdd", hideAdd);

		if (hideAdd == "yes") {
			// JOptionPane.showMessageDialog(null,
			// "Bahagian PB Telah Mencukupi.", "Etapp", 2);

		} else if (hideAdd == "notcomplete") {
			// JOptionPane.showMessageDialog(null,
			// "Bahagian PB Masih Tidak Mencukupi.", "Etapp", 2);
		}

	}// close getTotalSyer
	
	//penambahan yati
	@SuppressWarnings("unchecked")
	private void dataHakmilik(String idHakmilik, String disability)
			throws Exception {

		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();

		// data hakmilik
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);

		
		String id_kategoritanah = "";
		String id_unitluasambil = "";
		if (dataMaklumatTanah.size() != 0) {
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);		
			id_kategoritanah = h.get("id_kategoritanah").toString();			
			id_unitluasambil = h.get("id_unitluasambil").toString();
		}


		String mode = "";
		if (disability.equals("enabled")) {
			mode = "";
		} else {
			mode = "disabled class=disabled";
		}
		
		context.put(
				"selectUnitLuasAmbil",
				HTML.SelectLuas("socUnitLuasAmbil", null,
						"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));

	}// close dataHakmilik
	
	private void validationConvertLuasAmbil() throws Exception {
		
		String socUnitLuasAmbil = getParam("socUnitLuasAmbil");

		if (socUnitLuasAmbil != "") {
			if (socUnitLuasAmbil.equals("4") || socUnitLuasAmbil.equals("8")) {
				context.put("showBoxAmbil2", "yes");
				context.put("showBoxAmbil3", "yes");
			} else if (socUnitLuasAmbil.equals("7")) {
				context.put("showBoxAmbil2", "yes");
				context.put("showBoxAmbil3", "no");
			} else {
				context.put("showBoxAmbil2", "no");
				context.put("showBoxAmbil3", "no");
			}
		} 	
		else {
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}
		/* Validation button convert Ambil */
		if (!socUnitLuasAmbil.isEmpty()) {
			context.put("showButtonConvertAmbil", "yes");
		} else {
			context.put("showButtonConvertAmbil", "no");
		}

	}// close validationConvertLuasAmbil
	
	private void calculateNilaiAmbil() throws Exception {

		String id_kategoritanah = getParam("idUnitLuas");
		// data luas ambil
		String unitLuasAmbil = getParam("socUnitLuasAmbil");
		String luasAmbil1 = Utils.RemoveSymbol(getParam("txtLuasLotAmbil"));
		String luasAmbil2 = getParam("txtLuasLotAmbil2");
		String luasAmbil3 = getParam("txtLuasLotAmbil3");

		// field validation
		if (unitLuasAmbil != "") {
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		} else {
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}
		double total = 0.0000;

		String unitSebelumConvert1 = "";
		String unitSebelumConvert2 = "";
		String unitSebelumConvert3 = "";
		String sorDropdownUnitAmbil = "";

		// 1 = kilometer persegi
		if (unitLuasAmbil.equals("1")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 100;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 1000000;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "KILOMETER PERSEGI";

		}// close kilometer persegi

		// 2 = hektar
		if (unitLuasAmbil.equals("2")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 10000;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "HEKTAR";

		}// close hektar

		// 3 = meter persegi
		if (unitLuasAmbil.equals("3")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 0.0001;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "METER PERSEGI";

		}// close meter persegi

		// 4 = ekar/rood/pole
		if (unitLuasAmbil.equals("4")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAmbil1)
						+ (Double.parseDouble(luasAmbil2) / 4) + (Double
						.parseDouble(luasAmbil3) / 160)) * 0.404686;
				sorDropdownUnitAmbil = "1";
			} else {
				total = (Double.parseDouble(luasAmbil1)
						+ (Double.parseDouble(luasAmbil2) / 4) + (Double
						.parseDouble(luasAmbil3) / 160)) * 4046.86;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "ROOD";
			unitSebelumConvert3 = "POLE";

		}// close ekar/rood/pole

		// 5 = kaki persegi
		if (unitLuasAmbil.equals("5")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 0.000009290304;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 0.09290304;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "KAKI PERSEGI";

		}// close kaki persegi

		// 6 = ekar perpuluhan
		if (unitLuasAmbil.equals("6")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 0.0001;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "EKAR PERPULUHAN";

		}// close ekar perpuluhan

		// 7 = ekar/depa
		if (unitLuasAmbil.equals("7")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAmbil1) + (Double
						.parseDouble(luasAmbil2) / 1000)) * 0.404686;
				sorDropdownUnitAmbil = "1";
			} else {
				total = (Double.parseDouble(luasAmbil1) + (Double
						.parseDouble(luasAmbil2) / 1000)) * 4046.86;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "DEPA";

		}// close ekar/depa

		// 8 = relong/jemba/kaki persegi
		if (unitLuasAmbil.equals("8")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAmbil1)
						+ (Double.parseDouble(luasAmbil2) / 484) + (Double
						.parseDouble(luasAmbil3) / 30976)) * 0.711111 * 0.404686;
				sorDropdownUnitAmbil = "1";
			} else {
				total = (Double.parseDouble(luasAmbil1)
						+ (Double.parseDouble(luasAmbil2) / 484) + (Double
						.parseDouble(luasAmbil3) / 30976)) * 0.711111 * 4046.86;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "RELONG";
			unitSebelumConvert2 = "JEMBA";
			unitSebelumConvert3 = "KAKI PERSEGI";

		}// close relong/jemba/kaki persegi

		// 9 = batu nautika
		if (unitLuasAmbil.equals("9")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 0.0001;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "BATU NAUTIKA";

		}// close batu nautika

		// put data luas ambil
		context.put("txtLuasLotAmbil", Utils.formatLuasHM(total));
		context.put("sorDropdownUnitAmbil", sorDropdownUnitAmbil);
		context.put("txtLuasLotAmbilSebelumConvert", luasAmbil1 + " "
				+ unitSebelumConvert1 + " " + luasAmbil2 + " "
				+ unitSebelumConvert2 + " " + luasAmbil3 + " "
				+ unitSebelumConvert3);
		context.put("showBoxAmbil2", "no");
		context.put("showBoxAmbil3", "no");
		context.put("showButtonConvertAmbil", "no");
		
	}// close calculateNilaiAmbil
	
	private void clearConvertAmbil(String mode) throws Exception {

		// luas ambil
		context.put("txtLuasLotAmbil", "");
		context.put("showFieldAmbilBeforeConvert", "no");
		context.put("showDropdownUnitAmbil", "no");
		context.put("showButtonConvertAmbil", "no");
		context.put("showBoxAmbil2", "no");
		context.put("showBoxAmbil3", "no");

		// dropdown unit luas
		if (mode.equals("new")) {
			context.put(
					"selectUnitLuasAmbil",
					HTML.SelectLuas("socUnitLuasAmbil", null,
							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
		} else {
			context.put(
					"selectUnitLuasAmbil",
					HTML.SelectLuas("socUnitLuasAmbil", null,
							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
		}

	}// close clearConvertAmbil
	
	private void changeUnitAmbil() throws Exception {

		String showButtonConvertAmbil = getParam("showButtonConvertAmbil");

		if (showButtonConvertAmbil.equals("yes")) {
			context.put("showButtonConvertAmbil", "yes");
		} else {
			context.put("showButtonConvertAmbil", "no");
		}

		String unitConvert = getParam("sorDropdownUnitAmbil");
		context.put("sorDropdownUnitAmbil", unitConvert);

		String txtLuasLotAmbil = Utils
				.RemoveSymbol(getParam("txtLuasLotAmbil"));

		Double total = 0.0000;

		// hektar convert to meter persegi
		if (unitConvert.equals("1")) {
			total = Double.parseDouble(txtLuasLotAmbil) * 0.0001;
			// meter persegi convert to hektar
		} else {
			total = Double.parseDouble(txtLuasLotAmbil) * 10000;
		}

		// put data
		context.put("txtLuasLotAmbil", Utils.formatLuasHM(total));
		context.put("showBoxAmbil2", "no");
		context.put("showBoxAmbil3", "no");
		context.put("showButtonConvertAmbil", "no");

	}// close changeUnitAmbil
	
	private void checkValOnChange() throws Exception {

		String resetRadio = getParam("resetRadio");

		// validation jenis hakmilik & jenis rizab
		String id_jenisHakmilik = getParam("socJenisHakmilik");
		String sorJenisRizab = getParam("sorJenisRizab");

		/* validation jenis hakmilik */
		if (id_jenisHakmilik.equals("2") || id_jenisHakmilik.equals("5")
				|| id_jenisHakmilik.equals("28")
				|| id_jenisHakmilik.equals("73")
				|| id_jenisHakmilik.equals("74")
				|| id_jenisHakmilik.equals("75")
				|| id_jenisHakmilik.equals("80")
				|| id_jenisHakmilik.equals("85")
				|| id_jenisHakmilik.equals("113")) {
			context.put("showLuput", "yes");
		} else {
			context.put("showLuput", "no");
		}

		if (resetRadio.equals("1")) {
			context.put("showWarta", "no");
			context.put("showLainLain", "no");
		} else {
			/* validation jenis rizab */
			if (sorJenisRizab.equals("1")) {
				context.put("showWarta", "yes");
				context.put("showLainLain", "no");
			} else if (sorJenisRizab.equals("5")) {
				context.put("showWarta", "no");
				context.put("showLainLain", "yes");
			} else {
				context.put("showWarta", "no");
				context.put("showLainLain", "no");
			}
		}

	}// close checkValOnChange
	
	private void checkValConvert() throws Exception {

		// validation convert
		String showFieldAmbilBeforeConvert = getParam("showFieldAmbilBeforeConvert");
		String showButtonConvertAmbil = getParam("showButtonConvertAmbil");
		String showBoxAmbil2 = getParam("showBoxAmbil2");
		String showBoxAmbil3 = getParam("showBoxAmbil3");


		if (showFieldAmbilBeforeConvert.equals("yes")) {
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		} else {
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}

		/* Validation button convert Ambil */
		if (showButtonConvertAmbil.equals("yes")) {
			context.put("showButtonConvertAmbil", "yes");
		} else {
			context.put("showButtonConvertAmbil", "no");
		}

		if (showBoxAmbil3.equals("yes")) {
			context.put("showBoxAmbil2", "yes");
			context.put("showBoxAmbil3", "yes");
		} else if (showBoxAmbil2.equals("yes")) {
			context.put("showBoxAmbil2", "yes");
			context.put("showBoxAmbil3", "no");
		} else {
			context.put("showBoxAmbil2", "no");
			context.put("showBoxAmbil3", "no");
		}

	}// close checkValConvert
	
	@SuppressWarnings("unchecked")
	private void checkFieldValidation(String idHakmilik) throws Exception {

		Vector dataPermintaanUkur = new Vector();
		dataPermintaanUkur.clear();
		

		String id_hakmilik = "";	
		String luas_ambil_sambung = "";
		
		model.setDataPermintaanUkur(idHakmilik);
 		dataPermintaanUkur = model.getDataPermintaanUkur();
 		if(dataPermintaanUkur.size()!=0){
 			Hashtable h = (Hashtable)dataPermintaanUkur.get(0);
 			id_hakmilik = h.get("id_hakmilik").toString();
			luas_ambil_sambung = h.get("luas_ambil_sambung").toString();
 		}

		if (luas_ambil_sambung != "") {
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		} else {
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}

	}// close checkJenisHakmilik
	
	
}//close class
