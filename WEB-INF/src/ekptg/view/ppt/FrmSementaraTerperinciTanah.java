package ekptg.view.ppt;
/* 
 * @author 
 * NORZAILY JASMI
 */

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
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
import ekptg.model.ppt.FrmUPTSek8InfoTanahTerperinciTanahData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.SementaraLaporanTerperinciTanah;

public class FrmSementaraTerperinciTanah extends AjaxBasedModule {

	
	
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSementaraTerperinciTanah.class);
	
	// MODEL SEKSYEN 8
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUPTSek8InfoTanahTerperinciTanahData model = new FrmUPTSek8InfoTanahTerperinciTanahData();
	PPTHeader header = new PPTHeader();
	
	// MODEL SEMENTARA
	SementaraLaporanTerperinciTanah modelSementara = new SementaraLaporanTerperinciTanah();
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for pagings
    	String action = getParam("action");
    	
    	//get user login detail
//    	String user_id = (String) session.getAttribute("_ekptg_user_id");
//    	String portal_role = (String) session.getAttribute("_portal_role");
    	
    	//get user login detail
    	String portal_role = (String) session.getAttribute("_portal_role");
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user); 
    	
    
    	//helper
    	context.put("Util", new lebah.util.Util());
    	context.put("Utils", new ekptg.helpers.Utils());
    	
    	String vm = "";
    	String noLOT = "";
    	String idpegawai = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector dataMaklumatTanahTerperinci = new Vector();
    	Vector checkSizeTbltanah = new Vector();
    	
    	checkSizeTbltanah.clear();
    	dataMaklumatTanahTerperinci.clear();
    	listMaklumatTanah.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSementaraTerperinciTanahSenarai.jsp";
    	String listHMscreen = "app/ppt/frmSementaraTerperinciTanahListHM.jsp";
    	String screentanah = "app/ppt/frmSementaraTerperinciTanah.jsp";
    	
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
    		context.put("paging", "6");
    	}
    	
    	
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
			id_fail = dh.get("id_fail").toString();
			
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
		
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("onchange","no");
		
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewListHM".equals(submit)){
    /*
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//data & list maklumat tanah
    		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
     		listMaklumatTanah = modelUPT.getListMaklumatTanah();
     		context.put("listMaklumatTanah", listMaklumatTanah);
     		context.put("saiz_listTanah", listMaklumatTanah.size());
     		
     		*/
     		context.put("saiz_listTanah", modelUPT.setListMaklumatTanah_count(idpermohonan,noLOT,idpegawai));
     		
        	//screen
    		vm = listHMscreen;
        	
    	}//close 
    	
    	else 
        if("maklumatTanah".equals(submit)){  //PPT-42 Start
            
        	//reset value
        	resetValue();
        	
        	//validation by size
        	model.setCheckTbltanah(idHakmilik);
    		checkSizeTbltanah = model.getCheckTbltanah();
        	
        	//get current date
    		String sysdate = "";
    		sysdate = lebah.util.Util.getDateTime(new Date(),"dd/MM/yyyy");
    		context.put("sysdate",sysdate);
        	
    		//data maklumat tanah
    		String id_pegawai = "";
    		modelUPT.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = modelUPT.getMaklumatTanah();
    		if(dataMaklumatTanah.size()!=0){
    			Hashtable tt = (Hashtable)dataMaklumatTanah.get(0);
    			id_pegawai = (String)tt.get("id_pegawai");
    		}
    		
    		String mode = "";
    		if((id_pegawai=="") || (id_pegawai.equals(id_user)) || (portal_role.equals("(PPT)PengarahUnit") || portal_role.equals("(PPT)PenolongPengarahUnit") || portal_role.equals("adminppt"))){   			
    			mode = "enabled";
    		}else{
    			//mode = "disabled";
    			mode = "enabled";
    		}
    		
    		//data tblppttanah dan tblppthakmilik
    		DataTanahTerperinci(session,idHakmilik,mode);
    		
    		model.setMaklumatTanahTerperinci(idHakmilik);
    		dataMaklumatTanahTerperinci = model.getMaklumatTanahTerperinci();
    		String id_tanah = "";
    		if(dataMaklumatTanahTerperinci.size()!=0){
    			Hashtable tt = (Hashtable)dataMaklumatTanahTerperinci.get(0);
    			id_tanah = (String)tt.get("id_tanah");
    		}
    		
    		//list dokumen
    		listDokumen(id_tanah);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2]: " + submit2);
        	
    		//NEW
    		if(checkSizeTbltanah.size()==0){
    			
    			//form validation
        		context.put("mode","new");
    			
        		if("onchangeUnitAsal".equals(submit2)){
        			
        			//onchange validation
        			context.put("onchange","yes");
        			
        			//convert nilai lain
            		changeUnitAsal();
            		
            		//get and set data
            		getAndSetData("1");
        			
        		}//close onchangeUnitAsal
        		
        		else if("onchangeUnitAmbil".equals(submit2)){
        			
        			//onchange validation
        			context.put("onchange","yes");
        			
        			//convert nilai lain
        			changeUnitAmbil();
        			
        			//get and set data
            		getAndSetData("2");
        			
        		}//close onchangeUnitAsal

        		else if("simpanTanahTerperinci".equals(submit2)){  //PPT-42 Simpan
            		
            		if (doPost.equals("true")) {
            			//simpan data
            			simpanTanahTerperinci(session,id_status); 
            		}
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//data tblppttanah dan tblppthakmilik
            		DataTanahTerperinci(session,idHakmilik,"disabled");
            		
            		model.setMaklumatTanahTerperinci(idHakmilik);
            		dataMaklumatTanahTerperinci = model.getMaklumatTanahTerperinci();
            		if(dataMaklumatTanahTerperinci.size()!=0){
            			Hashtable tt = (Hashtable)dataMaklumatTanahTerperinci.get(0);
            			id_tanah = (String)tt.get("id_tanah");
            		}
            		
            		//list dokumen
            		listDokumen(id_tanah);
            		
            	}//close simpanTanahTerperinci
        		
            	
    		//VIEW	
    		}else{
    		
    			//form validation
        		context.put("mode","view");
        		context.put("isEdit","no");
    			
        		//data tblppttanah dan tblppthakmilik
        		DataTanahTerperinci(session,idHakmilik,"disabled");
        		
        		if("kemaskiniTanahTerperinci".equals(submit2)){  //PPT-42 One
        			
        			//form validation
            		context.put("mode","view");
            		context.put("isEdit","yes");
        			
            		//data tblppttanah dan tblppthakmilik
            		DataTanahTerperinci(session,idHakmilik,"enabled");
            		
            		String submit3 = getParam("command3");
                	myLogger.info("submit[3]: " + submit3);
            		
                	if("onchangeUnitAsalUpdate".equals(submit3)){
            			
            			//onchange validation
            			context.put("onchange","yes");
            			
            			//convert nilai lain
                		changeUnitAsal();
                		
                		//get and set data
                		getAndSetData("1");
            			
            		}//close onchangeUnitAsalUpdate
            		
            		else if("onchangeUnitAmbilUpdate".equals(submit3)){
            			
            			//onchange validation
            			context.put("onchange","yes");
            			
            			//convert nilai lain
            			changeUnitAmbil();
            			
            			//get and set data
                		getAndSetData("2");
            			
            		}//close onchangeUnitAmbilUpdate
                	
                	else if("updateTanahTerperinci".equals(submit3)){ //PPt-42 Two
                		
                		updateTanahTerperinci(session);
                		
                		//form validation
                		context.put("mode","view");
                		context.put("isEdit","no");
            			
                		//data tblppttanah dan tblppthakmilik
                		DataTanahTerperinci(session,idHakmilik,"disabled");
                		
                		//list dokumen
                		listDokumen(id_tanah);
                		
                	}//close updateTanahTerperinci
                	
        		}//close kemaskiniTanahTerperinci
    			
    		}//check size
         
            //screen
            vm = screentanah;
            	
        }//close maklumatTanah
    	
        else 
    	if("hapusDokumen".equals(submit)){
    			
    		if (doPost.equals("true")) {
    			//hapus data
    			hapusDokumen(); 
    		}
    		
    		//data maklumat tanah
    		String id_pegawai = "";
    		modelUPT.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = modelUPT.getMaklumatTanah();
    		if(dataMaklumatTanah.size()!=0){
    			Hashtable tt = (Hashtable)dataMaklumatTanah.get(0);
    			id_pegawai = (String)tt.get("id_pegawai");
    		}
    		
    		String mode = "";
    		if((id_pegawai=="") || (id_pegawai.equals(id_user)) || (portal_role.equals("(PPT)PengarahUnit") || portal_role.equals("(PPT)PenolongPengarahUnit") || portal_role.equals("adminppt"))){   			
    			mode = "enabled";
    		}else{
    			//mode = "disabled";
    			mode = "enabled";
    		}
    		
    		//data tblppttanah dan tblppthakmilik
    		DataTanahTerperinci(session,idHakmilik,mode);
    		
    		model.setMaklumatTanahTerperinci(idHakmilik);
    		dataMaklumatTanahTerperinci = model.getMaklumatTanahTerperinci();
    		String id_tanah = "";
    		if(dataMaklumatTanahTerperinci.size()!=0){
    			Hashtable tt = (Hashtable)dataMaklumatTanahTerperinci.get(0);
    			id_tanah = (String)tt.get("id_tanah");
    		}
    		
    		//list dokumen
    		listDokumen(id_tanah);
    		
    		 //screen
            vm = screentanah;
            
    	}//close hapusDokumen
    	
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
	    	context.put("id_hakmilik", idHakmilik);
	    	context.put("id_status", id_status);
	    	context.put("id_fail", id_fail);
	    	
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
	
	private void getAndSetData(String mode) throws Exception{
	
		if(mode.equals("1")){
			context.put("txtLuasLotAmbil", getParam("txtLuasLotAmbil"));
			context.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));
		}else{
			context.put("txtLuasLotAsal", getParam("txtLuasLotAsal"));
			context.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		}
		
		//dropdown
		context.put("selectKategoriTanah",HTML.SelectKategoriTanah("socKategoriTanah",Utils.parseLong(getParam("socKategoriTanah")),"id=socKategoriTanah style=width:auto",null));
		context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")),"id=socJenisHakmilik style=width:250"));   			
		
		context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		context.put("txtNoSyit", getParam("txtNoSyit"));
		
		context.put("txdTarikhLawatMula", getParam("txdTarikhLawatMula"));
		context.put("txdTarikhLawatAkhir", getParam("txdTarikhLawatAkhir"));
		context.put("txtStatusTanah", getParam("txtStatusTanah"));
		context.put("txtLokasi", getParam("txtLokasi"));
		context.put("txtJalanUtama", getParam("txtJalanUtama"));
		context.put("txtJalanMasuk", getParam("txtJalanMasuk"));
		context.put("txtNamaTempat", getParam("txtNamaTempat"));
		context.put("txtJarak", getParam("txtJarak"));
		context.put("txtPerumahan", getParam("txtPerumahan"));
		context.put("txtIndustri", getParam("txtIndustri"));
		context.put("txtNamaPBT", getParam("txtNamaPBT"));
		context.put("sorPBT", getParam("sorPBT"));
		context.put("sorRizab", getParam("sorRizab"));
		
	}//close getAndSetData
	
	private void resetValue() throws Exception{
		
		context.put("txdTarikhLawatMula","");
		context.put("txdTarikhLawatAkhir","");
		context.put("txtStatusTanah","");
		context.put("txtLokasi","");
		context.put("txtJalanUtama","");
		context.put("txtJalanMasuk","");
		context.put("txtNamaTempat","");
		context.put("txtJarak","");
		context.put("txtPerumahan","");
		context.put("txtIndustri","");
		context.put("txtNamaPBT","");
		context.put("sorPBT","");
		context.put("sorRizab","");
		
	}//close resetValue
	
	private void changeUnitAmbil() throws Exception{
		
		String unitConvert = getParam("sorDropdownUnitAmbil");
		context.put("sorDropdownUnitAmbil",unitConvert);
		
		String txtLuasLotAmbil = Utils.RemoveSymbol(getParam("txtLuasLotAmbil"));
		
		Double total = 0.0000;
		
		//hektar convert to meter persegi
		if(unitConvert.equals("1")){			
			total =  Double.parseDouble(txtLuasLotAmbil) * 0.0001;			
		//meter persegi	convert to hektar
		}else{			
			total =  Double.parseDouble(txtLuasLotAmbil) * 10000;			
		}
		
		//put data
		context.put("txtLuasLotAmbil", Utils.formatLuasHM(total));
		
	}//close changeUnitAmbil

	private void changeUnitAsal() throws Exception{
		
		String unitConvert = getParam("sorDropdownUnitAsal");
		context.put("sorDropdownUnitAsal",unitConvert);
		
		String txtLuasLotAsal = Utils.RemoveSymbol(getParam("txtLuasLotAsal"));
		
		Double total = 0.0000;
		
		//hektar convert to meter persegi
		if(unitConvert.equals("1")){			
			total =  Double.parseDouble(txtLuasLotAsal) * 0.0001;			
		//meter persegi	convert to hektar
		}else{			
			total =  Double.parseDouble(txtLuasLotAsal) * 10000;			
		}
		
		//put data
		context.put("txtLuasLotAsal", Utils.formatLuasHM(total));
		
	}//close changeUnitAsal

	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		SementaraLaporanTerperinciTanah.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings("unchecked")
	private void listDokumen(String id_tanah) throws Exception{
    	
		Vector listDokumen = new Vector();
		listDokumen.clear();

 		model.setListDokumen(id_tanah);
 		listDokumen = model.getListDokumen();
		context.put("listDokumen", listDokumen);
		context.put("saiz_listDokumen", listDokumen.size());
		
	}//close listDokumen
	
	
	@SuppressWarnings("static-access")
	private void hapusDokumen() throws Exception{
    	
		model.hapusDokumen(getParam("id_dokumen"));
		
	}//close hapusDokumen
	
	
	@SuppressWarnings("unchecked")
	private void simpanTanahTerperinci(HttpSession session,String status) throws Exception{
    	
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    
		Hashtable h = new Hashtable();
		
		h.put("id_hakmilik",getParam("id_hakmilik"));
		h.put("id_permohonan",getParam("id_permohonan"));
		
		//update tblppthakmilik
		h.put("txtNoHakmilik",getParam("txtNoHakmilik"));
		h.put("txtNoSyit",getParam("txtNoSyit"));
		h.put("socKategoriTanah",getParam("socKategoriTanah"));
		h.put("socJenisHakmilik",getParam("socJenisHakmilik"));
		
		h.put("txtLuasLotAsal", Utils.RemoveSymbol(getParam("txtLuasLotAsal")));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("txtLuasLotAmbil", Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));
		
		//insert tblppttanah
		h.put("txtJenisPisah",getParam("txtJenisPisah"));
		h.put("txtStatusTanah",getParam("txtStatusTanah"));
		h.put("txdTarikhLawatMula",getParam("txdTarikhLawatMula"));
		h.put("txdTarikhLawatAkhir",getParam("txdTarikhLawatAkhir"));
		h.put("txtJalanUtama",getParam("txtJalanUtama"));
		h.put("txtJalanMasuk",getParam("txtJalanMasuk"));
		h.put("txtNamaTempat",getParam("txtNamaTempat"));
		h.put("txtPerumahan",getParam("txtPerumahan"));
		h.put("txtIndustri",getParam("txtIndustri"));
		//h.put("socUnitLuasLot",getParam("socUnitLuasLot"));
		//h.put("socUnitLuasAmbil",getParam("socUnitLuasAmbil"));
		//h.put("txtLuasLot",getParam("txtLuasLot"));
		//h.put("txtLuasAmbil",getParam("txtLuasAmbil"));
		h.put("sorSaliran",getParam("sorSaliran"));
		h.put("txtLokasi",getParam("txtLokasi"));
		h.put("txtJarak",getParam("txtJarak"));
		h.put("sorPBT",getParam("sorPBT"));
		h.put("txtNamaPBT",getParam("txtNamaPBT"));
		h.put("sorRizab",getParam("sorRizab"));
		h.put("flagBukit",getParam("flagBukit"));
		h.put("flagLandai",getParam("flagLandai"));
		h.put("flagRata",getParam("flagRata"));
		h.put("flagRendah",getParam("flagRendah"));
		h.put("flagBerpaya",getParam("flagBerpaya"));
		h.put("flagLurah",getParam("flagLurah"));
		h.put("flagLembah",getParam("flagLembah"));
		h.put("txtPerihalRupabumi",getParam("txtPerihalRupabumi"));
		h.put("flagUsaha",getParam("flagUsaha"));
		h.put("flagLapang",getParam("flagLapang"));
		h.put("flagTerbiar",getParam("flagTerbiar"));
		h.put("flagHutan",getParam("flagHutan"));
		h.put("flagBelukar",getParam("flagBelukar"));
		h.put("flagSemak",getParam("flagSemak"));
		h.put("txtPerihalKeadaan",getParam("txtPerihalKeadaan"));
		h.put("txtHalangan",getParam("txtHalangan"));
		h.put("txtTanaman",getParam("txtTanaman"));
		h.put("txtKemudahan",getParam("txtKemudahan"));
		h.put("txtSempadanUtara",getParam("txtSempadanUtara"));
		h.put("txtSempadanSelatan",getParam("txtSempadanSelatan"));
		h.put("txtSempadanTimur",getParam("txtSempadanTimur"));
		h.put("txtSempadanBarat",getParam("txtSempadanBarat"));
		h.put("socUnitHargaSO",getParam("socUnitHargaSO"));
		h.put("txtHargaSeunitSO",getParam("txtHargaSeunitSO"));
		h.put("txtHargaPasaranSO",getParam("txtHargaPasaranSO"));
		h.put("txtPenjejasanSO",getParam("txtPenjejasanSO"));
		h.put("txtPecahSO",getParam("txtPecahSO"));
		h.put("txtKenaikanSO",getParam("txtKenaikanSO"));
		h.put("socUnitHargaJP",getParam("socUnitHargaJP"));
		h.put("txtHargaSeunitJP",getParam("txtHargaSeunitJP"));
		h.put("txtHargaPasaranJP",getParam("txtHargaPasaranJP"));
		h.put("txtPenjejasanJP",getParam("txtPenjejasanJP"));
		h.put("txtPecahJP",getParam("txtPecahJP"));
		h.put("txtKenaikanJP",getParam("txtKenaikanJP"));
		h.put("socUnitHargaG",getParam("socUnitHargaG"));
		h.put("txtHargaBorangG",getParam("txtHargaBorangG"));
		h.put("txtUlasanPegawai",getParam("txtUlasanPegawai"));
		//h.put("idPelapor", session.getAttribute("_ekptg_user_id"));
		h.put("txtPelapor",getParam("txtPelapor"));
		h.put("txdTarikhUlasan",getParam("txdTarikhUlasan"));
		h.put("txtPendahuluan", getParam("txtPendahuluan"));
		//PPT-42
		h.put("socUnitHargaNS",getParam("socUnitHargaNS"));
		h.put("txtHargaSeunitNS",getParam("txtHargaSeunitNS"));
		h.put("txtHargaPasaranNS",getParam("txtHargaPasaranNS"));
		h.put("txtPenjejasanNS",getParam("txtPenjejasanNS"));
		h.put("txtPecahNS",getParam("txtPecahNS"));
		h.put("txtKenaikanNS",getParam("txtKenaikanNS"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		h.put("txtStrukturBangunanSO",getParam("txtStrukturBangunanSO"));
		h.put("txtStrukturBangunan",getParam("txtStrukturBangunan"));
		
		
		// ALTER ELLY 11082010
		h.put("socPegawai",getParam("socPegawai"));
		
		//get id
		long id_tanah = DB.getNextID("TBLPPTTANAH_SEQ");
		
		//update into tblppthakmilik
		FrmUPTSek8InfoTanahTerperinciTanahData.updateHMtanah(h);
		
		//insert into tblppttanah
		FrmUPTSek8InfoTanahTerperinciTanahData.simpanTanahTerperinci(h,id_tanah);
		
		String valItem = getParam("txtTajuk");
		
		if(valItem!=""){
			List items = upload.parseRequest(request);
	    
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem)itr.next();
				if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
					saveData(item,id_tanah);
				}
			}
		}//close valItem
		
	}//close simpanTanahTerperinci
	
	@SuppressWarnings("unchecked")
	private void updateTanahTerperinci(HttpSession session) throws Exception{  //PPT-42 THREE
    	
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    
		Hashtable h = new Hashtable();
		
		String id_tanah = getParam("id_tanah");
		
		h.put("id_tanah", id_tanah);
		h.put("id_hakmilik",getParam("id_hakmilik"));
		h.put("id_permohonan",getParam("id_permohonan"));
		
		//update tblppthakmilik
		h.put("txtNoSyit",getParam("txtNoSyit"));
		h.put("socKategoriTanah",getParam("socKategoriTanah"));
		h.put("txtNoHakmilik",getParam("txtNoHakmilik"));
		h.put("socJenisHakmilik",getParam("socJenisHakmilik"));
		
		h.put("txtLuasLotAsal", Utils.RemoveSymbol(getParam("txtLuasLotAsal")));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("txtLuasLotAmbil", Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));
		
		//insert tblppttanah
		h.put("txtJenisPisah",getParam("txtJenisPisah"));
		h.put("txtStatusTanah",getParam("txtStatusTanah"));
		h.put("txdTarikhLawatMula",getParam("txdTarikhLawatMula"));
		h.put("txdTarikhLawatAkhir",getParam("txdTarikhLawatAkhir"));
		h.put("txtJalanUtama",getParam("txtJalanUtama"));
		h.put("txtJalanMasuk",getParam("txtJalanMasuk"));
		h.put("txtNamaTempat",getParam("txtNamaTempat"));
		h.put("txtPerumahan",getParam("txtPerumahan"));
		h.put("txtIndustri",getParam("txtIndustri"));
		h.put("sorSaliran",getParam("sorSaliran"));
		//h.put("socUnitLuasLot",getParam("socUnitLuasLot"));
		//h.put("socUnitLuasAmbil",getParam("socUnitLuasAmbil"));
		//h.put("txtLuasLot",getParam("txtLuasLot"));
		//h.put("txtLuasAmbil",getParam("txtLuasAmbil"));
		h.put("txtLokasi",getParam("txtLokasi"));
		h.put("txtJarak",getParam("txtJarak"));
		h.put("sorPBT",getParam("sorPBT"));
		h.put("txtNamaPBT",getParam("txtNamaPBT"));
		h.put("sorRizab",getParam("sorRizab"));
		h.put("flagBukit",getParam("flagBukit"));
		h.put("flagLandai",getParam("flagLandai"));
		h.put("flagRata",getParam("flagRata"));
		h.put("flagRendah",getParam("flagRendah"));
		h.put("flagBerpaya",getParam("flagBerpaya"));
		h.put("flagLurah",getParam("flagLurah"));
		h.put("flagLembah",getParam("flagLembah"));
		h.put("txtPerihalRupabumi",getParam("txtPerihalRupabumi"));
		h.put("flagUsaha",getParam("flagUsaha"));
		h.put("flagLapang",getParam("flagLapang"));
		h.put("flagTerbiar",getParam("flagTerbiar"));
		h.put("flagHutan",getParam("flagHutan"));
		h.put("flagBelukar",getParam("flagBelukar"));
		h.put("flagSemak",getParam("flagSemak"));
		h.put("txtPerihalKeadaan",getParam("txtPerihalKeadaan"));
		h.put("txtHalangan",getParam("txtHalangan"));
		h.put("txtTanaman",getParam("txtTanaman"));
		h.put("txtKemudahan",getParam("txtKemudahan"));
		h.put("txtSempadanUtara",getParam("txtSempadanUtara"));
		h.put("txtSempadanSelatan",getParam("txtSempadanSelatan"));
		h.put("txtSempadanTimur",getParam("txtSempadanTimur"));
		h.put("txtSempadanBarat",getParam("txtSempadanBarat"));
		h.put("socUnitHargaSO",getParam("socUnitHargaSO"));
		h.put("txtHargaSeunitSO",getParam("txtHargaSeunitSO"));
		h.put("txtHargaPasaranSO",getParam("txtHargaPasaranSO"));
		h.put("txtPenjejasanSO",getParam("txtPenjejasanSO"));
		h.put("txtPecahSO",getParam("txtPecahSO"));
		h.put("txtKenaikanSO",getParam("txtKenaikanSO"));
		h.put("socUnitHargaJP",getParam("socUnitHargaJP"));
		h.put("txtHargaSeunitJP",getParam("txtHargaSeunitJP"));
		h.put("txtHargaPasaranJP",getParam("txtHargaPasaranJP"));
		h.put("txtPenjejasanJP",getParam("txtPenjejasanJP"));
		h.put("txtPecahJP",getParam("txtPecahJP"));
		h.put("txtKenaikanJP",getParam("txtKenaikanJP"));
		h.put("socUnitHargaG",getParam("socUnitHargaG"));
		h.put("txtHargaBorangG",getParam("txtHargaBorangG"));
		h.put("txtUlasanPegawai",getParam("txtUlasanPegawai"));
		//h.put("idPelapor", session.getAttribute("_ekptg_user_id"));
		h.put("txtPelapor",getParam("txtPelapor"));
		h.put("txdTarikhUlasan",getParam("txdTarikhUlasan"));
		h.put("txtPendahuluan",getParam("txtPendahuluan"));
		//PPT-42
		h.put("socUnitHargaNS",getParam("socUnitHargaNS"));
		h.put("txtHargaSeunitNS",getParam("txtHargaSeunitNS"));
		h.put("txtHargaPasaranNS",getParam("txtHargaPasaranNS"));
		h.put("txtPenjejasanNS",getParam("txtPenjejasanNS"));
		h.put("txtPecahNS",getParam("txtPecahNS"));
		h.put("txtKenaikanNS",getParam("txtKenaikanNS"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		
		h.put("txtStrukturBangunanSO",getParam("txtStrukturBangunanSO"));
		h.put("txtStrukturBangunan",getParam("txtStrukturBangunan"));
		
		
		// ALTER ELLY 11082010
		h.put("socPegawai",getParam("socPegawai"));
		
		String valItem = getParam("txtTajuk");
		
		if(valItem!=""){
			List items = upload.parseRequest(request);
	    
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem)itr.next();
				if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
					saveData(item,Utils.parseLong(id_tanah));
				}
			}
		}else{
			//PPT-42 FOUR
			//update into tblppthakmilik
			FrmUPTSek8InfoTanahTerperinciTanahData.updateHMtanah(h);
			
			//update into tblppttanah
			FrmUPTSek8InfoTanahTerperinciTanahData.updateTanahTerperinci(h);
			
		}
	    
	}//close updateTanahTerperinci
	
	
	private void saveData(FileItem item,long id_tanah) throws Exception {
  		Db db = null;
        try {
        	db = new Db();
        	long id_dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN " +
        			"(id_dokumen,id_tanah,nama_fail,keterangan,tajuk,jenis_Mime,content) " +
        			"values(?,?,?,?,?,?,?)");
        	ps.setLong(1, id_dokumen);
        	ps.setLong(2, id_tanah);
        	ps.setString(3, item.getName());
        	ps.setString(4, getParam("txtKeterangan"));
        	ps.setString(5, getParam("txtTajuk"));
        	ps.setString(6, item.getContentType());
        	ps.setBinaryStream(7,item.getInputStream(),(int)item.getSize());
        	ps.executeUpdate();
            con.commit();
            
	    }catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah muatnaik fail");
	    }finally {
		      if (db != null) db.close();
	    }
	}
	
	@SuppressWarnings("unchecked")
	private void DataTanahTerperinci(HttpSession session,String idHakmilik,String disability) throws Exception{
    	
		model.setMaklumatTanahTerperinci(idHakmilik);
		Vector dataMaklumatTanahTerperinci = model.getMaklumatTanahTerperinci();
		String id_kategoritanah = "";
		String txtNoSyit = "";
		String id_tanah = "";
		String id_pegawai = "";
		String id_jenishakmilik = "";
		if(dataMaklumatTanahTerperinci.size()!=0){
			Hashtable tt = (Hashtable)dataMaklumatTanahTerperinci.get(0);
			id_kategoritanah = (String)tt.get("id_kategoritanah");
			txtNoSyit = (String)tt.get("no_syit");
			id_tanah = (String)tt.get("id_tanah");
			id_pegawai = (String)tt.get("id_pegawai");
			id_jenishakmilik = (String)tt.get("id_jenishakmilik");
		}
		
		//data and id
		context.put("dataMaklumatTanahTerperinci", dataMaklumatTanahTerperinci);
		context.put("txtNoSyit",txtNoSyit);
		context.put("id_tanah",id_tanah);
		context.put("id_pegawai",id_pegawai);
		
		String mode = "";
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		//dropdown
		context.put("selectKategoriTanah",HTML.SelectKategoriTanah("socKategoriTanah",Utils.parseLong(id_kategoritanah),"id=socKategoriTanah "+mode+" style=width:auto",null));
		context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik "+mode+" style=width:250"));   			
		
	}//close NewDataTanahTerperinci

	
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
