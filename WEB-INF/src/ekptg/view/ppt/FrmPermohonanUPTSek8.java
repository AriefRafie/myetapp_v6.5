package ekptg.view.ppt;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmPermohonanUPTSek8 extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPermohonanUPTSek8.class);
	
	//model
	FrmPermohonanUPTData model = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	MyInfoPPTData myInfo = new MyInfoPPTData();
	String negeriIntegrasi = "";
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		//command for pagings
    	String action = getParam("action");
    
    	//Utils helper
    	UtilsItem();
    	
    	//default
    	String vm = "";
    	String noLOT = "";
    	String idpegawai = "";
    	context.put("showJajahan","no");
    	context.put("onchange","no");
    	context.put("onchangeHM","no");
    	
    	//default hakmilik
    	context.put("showLuput","no");
    	context.put("showWarta","no");
    	context.put("showLainLain","no");
    	context.put("showBoxAsal2","no");
    	context.put("showBoxAsal3","no");
    	context.put("showBoxAmbil2","no");
    	context.put("showBoxAmbil3","no");
    	context.put("showButtonConvertAsal","no");
    	context.put("showButtonConvertAmbil","no");
    	context.put("showFieldAsalBeforeConvert", "no");
    	context.put("showFieldAmbilBeforeConvert", "no");
    	context.put("showDropdownUnitAsal", "no");
    	context.put("showDropdownUnitAmbil", "no");

		this.context.put("maklumat_PB_Salin", "");
		this.context.put("id_hakmilikpb_salin", "");
		this.context.put("id_hakmilik_salin", "");  
    	this.context.put("maklumat_Hakmilik_Salin", "");
    	
    	//get current date
		String now = "";
		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		context.put("currentDATE",now);
    	
    	
    	Vector listPageDepan = new Vector();
    	Vector senaraiSemak = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
    	senaraiSemak.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/DaftarS8/frmSenaraiPermohonanUPTSek8.jsp";
    	String screenUtama = "app/ppt/DaftarS8/frmUPTDaftarSek8.jsp";
    	String screenTanah = "app/ppt/DaftarS8/frmUPTHakmilikTambahSek8.jsp";
    	String screenDokumen = "app/ppt/DaftarS8/frmUPTDocTambahSek8.jsp";
    		
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    			
    	//anchor
    	anchor();
    	
		//paging
    	paging();
    	
    	//tabbed
    	selectedTab();
    	String selectedTab = selectedTab();
		
    	//user login id
    	//String id_user = (String) session.getAttribute("_ekptg_user_id");    	
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
    	//get nama pengarah dan id pengarah
    	//nameAndId(userIdNeg);
    	//String id_pengarah = nameAndId(userIdNeg);
    	
    	context.put("userIdNeg",userIdNeg);
    	
    	//default list
    	//listPageDepan = model.getListPemohonSeksyen8(userIdNeg);
    	setupPage(session,action,listPageDepan);
    	
		//header
    	//String id_masukPermohonan = "";
    	String id_projekDaerah = "";
    	String flagStatusOnline = "";
    	String catatan_status_online = "";
    	String nama_kementerian = "";
    	String tarikh_permohonan = "";
    	String tujuan = "";
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
		String flag_subjaket = "";
		String id_projekNegeri = "";
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
			flag_subjaket = dh.get("flag_subjaket").toString();
			id_projekNegeri = dh.get("id_projekNegeri").toString();
			tujuan = (String)dh.get("tujuan");
			tarikh_permohonan = (String)dh.get("tarikh_permohonan");
			nama_kementerian = (String)dh.get("nama_kementerian");
			//id_masukPermohonan = dh.get("id_kemaskini").toString();
			flagStatusOnline = (String)dh.get("flag_status_online");
			catatan_status_online = (String)dh.get("catatan_status_online");
			id_projekDaerah = dh.get("id_projekDaerah").toString();
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
    	}
		
		context.put("id_fail", id_fail);
		//System.out.println("id_fail :::----"+id_fail);
		myLogger.info("id_fail---*"+id_fail);

		//sebab online ditolak
		context.put("catatan_status_online",catatan_status_online);
		
		//id Hakmilik
		String idHakmilik = getParam("id_hakmilik");

		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("showAlertPaging","no");
    	context.put("showAlertDuplicate", "no");
    	context.put("showNoRujukan", "");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	/*
    	Db db = null;
		try {
		db = new Db();
    
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupHakmilik",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupHakmilik","Skrin Salin Hakmilik", "EKPTG - PPT",db);
	    	}
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT",db);
	    	}
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupUploadDokumen",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupUploadDokumen","Skrin Senarai Dokumen PPT", "EKPTG - PPT",db);
	    	}
	    	if(checkRegPopup("ekptg.view.ppt.SkrinPopupSalinPBHakmilik",db)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupSalinPBHakmilik","Skrin Salin Hakmilik dan Pihak Berkepentingan", "EKPTG - PPT",db);
	    	}
	    	
	    	 
	    	
	    	checkFieldWujud("TBLPPTDOKUMEN","JENIS_DOKUMEN","varchar2(50)",db);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_HAKMILIK","NUMBER",db);
		}
		finally {			
		if (db != null)
		db.close();
		}
    	*/
    	
    	
    	if("pendaftaran".equals(submit)){
    	
    		//form validation
    		context.put("mode","new");
    		context.put("isEdit","no");
    		
    		//reset value
    		resetValue();
    		
    		//get current date
    		String dateToday = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    		context.put("txdTarikhPermohonan",dateToday);
    		
    		//get jenis kod daerah
    		getJenisKodDaerah(dateToday);
    		String sorJenisKodDaerah = getJenisKodDaerah(dateToday);

    		//dropdown
    		context.put("selectKementerian",HTML.SelectKementerian("socKementerian","style=width:500px onChange=\"doChangeKementerian();\""));
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,"class=disabled disabled style=width:325px"));
    		context.put("selectAgensi",HTML.SelectAgensi("socAgensi",null,"id=socAgensi style=width:500px"));
    		
    		//20042011
    		if(userIdNeg.equals("14")){
    			context.put("selectNegeriProjek",HTML.SelectNegeriMampuKL("socNegeriProjek",null,null,"style=width:300px onChange=\"doChangeProjekNegeri();\""));
    		}else{
    			context.put("selectNegeriProjek",HTML.SelectNegeriMampu("socNegeriProjek",Utils.parseLong(userIdNeg),null,"style=width:300px class=disabled disabled onChange=\"doChangeProjekNegeri();\""));
    		}
    		
    		if(userIdNeg!="" && !userIdNeg.equals("14")){
    			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,userIdNeg,"socDaerah",null,null,"id=socDaerah style=width:300px"));
    		}else{
    			context.put("selectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"socDaerah",null,null,"id=socDaerah style=width:300px"));
    		}
    		
    		//validation jajahan
    		if(userIdNeg.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("doCheckExistNoFail".equals(submit2)){       		
        		//get and set data
        		getAndSetData(getParam("jenisNofail"),"new",idpermohonan,userIdNeg);
        		
        	}//close doCheckExistNoFail
        	
        	else if("doChangeKementerian".equals(submit2)){
        		
        		String id_kementerian = getParam("socKementerian");
        		
        		//get and set data
        		getAndSetDataOnchangeKementerian(id_kementerian,"new",userIdNeg);
        		
        	}//close doChangeKementerian
        	
        	else if("doChangeProjekNegeri".equals(submit2)){
        		
        		//String id_negeriprojek = getParam("socNegeriProjek");
        		
        		//get and set data
        		getAndSetDataOnchangeNegeri(userIdNeg,"new");
        		
        	}//close doChangeProjekNegeri

        	else if("getJenisKodDaerah".equals(submit2)){
        		
        		//get and set data
        		getAndSetDataOnchangeNegeri(userIdNeg,"new");
        		
        		//get jenis kod daerah
        		getJenisKodDaerah(getParam("txdTarikhPermohonan"));
        		sorJenisKodDaerah = getJenisKodDaerah(getParam("txdTarikhPermohonan"));
        		
        		String id_negeriprojek = "";
        		if(userIdNeg.equals("14")){
        			id_negeriprojek = getParam("socNegeriProjek");
        		}else{
        			id_negeriprojek = userIdNeg;
        		}
        		
        		if(id_negeriprojek!=""){
        			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,id_negeriprojek,"socDaerah",Utils.parseLong(getParam("socDaerah")),null,"id=socDaerah style=width:300px"));
        		}else{
        			context.put("selectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"socDaerah",Utils.parseLong(getParam("socDaerah")),null,"id=socDaerah style=width:300px"));
        		}
        		
        	}//close getJenisKodDaerah
        	
        	else if("onchangeJenisKodDaerah".equals(submit2)){
        		
        		//get and set data
        		getAndSetDataOnchangeNegeri(userIdNeg,"new");
        		
        	}//close onchangeJenisKodDaerah
        	
        	else if("simpanPendaftaran".equals(submit2)){
        		
        		String result = "";
            	if (doPost.equals("true")) {
            		//simpan data
                	result = simpanPendaftaran(session,userIdNeg);
             	}
                		
            	context.put("ResultAdd",result);
            	
            	idpermohonan = result;
           
            	//MAIN PAGE
            	if(idpermohonan!=""){
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//update status
            		dataHeader(idpermohonan);
            		id_status = dataHeader(idpermohonan);
            		
            		//senarai semak
            		ListCheckBox(idpermohonan);
            		
            		//data pendaftaran
            		DataPermohonan(idpermohonan,"disabled");
            		
            		//list dokumen
            		ListDokumen(idpermohonan);
             		
            		//list hakmilik
            		ListHakmilik(idpermohonan,noLOT,idpegawai);
            		
             		
            	//LIST DEPAN	
            	}else{
            		
            		//list depan
            		listPageDepan = model.getListPemohonSeksyen8(userIdNeg);
            		
            		myLogger.info("masuk sini");
            		context.put("nofail", "");
        			context.put("carianTarikh", "");
        			context.put("carianStatus", "");
        			
            		//screen
            		vm = listdepan;
            		return vm;
            		
            	}//validation after add
            	
        	}//close simpanPendaftaran
        	
    		//screen
	        vm = screenUtama;
    	//close pendaftaran	
    	}else if("semakPendaftaran".equals(submit)){   			
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
    		
        	//get current date  	
    		if(id_status.equals("127") && "".equals(submit2)){
    			context.put("showAlertPaging","yes");
    		//	JOptionPane.showMessageDialog (null, "Sila Klik 'Paging' No.2 Untuk Penambahan Hakmilik dan Pihak Berkepentingan", "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
    		}else{
    			context.put("showAlertPaging","no");
    		}
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//carian no lot dan pt
    		noLOT = getParam("carianNoLot"); 
    		context.put("carianNoLot", noLOT.trim());
    		
    		//list hakmilik
    		ListHakmilik(idpermohonan,noLOT,idpegawai);
    		
    		//senarai semak
    		ListCheckBox(idpermohonan);
    		
    		//data pendaftaran
    		DataPermohonan(idpermohonan,"disabled");
    		
    		//list dokumen
    		ListDokumen(idpermohonan);
    	
        	if("kemaskiniPendaftaran".equals(submit2)){
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
        		
        		//data pendaftaran
        		DataPermohonan(idpermohonan,"enabled");
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("doCheckExistNoFailUpdate".equals(submit3)){
            		
            		//onchange validation
            		context.put("onchange","yes");
            		
            		//get and set data
            		getAndSetData(getParam("jenisNofail"),"edit",idpermohonan,userIdNeg);
            		
            	}//close doCheckExistNoFailUpdate
            	
            	else if("doChangeKementerianUpdate".equals(submit3)){
            		
            		//onchange validation
            		context.put("onchange","yes");
            		
            		String id_kementerian = getParam("socKementerian");
            		
            		//get and set data
            		getAndSetDataOnchangeKementerian(id_kementerian,"view",userIdNeg);
            		
            	}//close doChangeKementerianUpdate
        		
            	else if("doChangeProjekNegeriUpdate".equals(submit3)){
            		
            		//onchange validation
            		context.put("onchange","yes");
            		
            		//get and set data
            		getAndSetDataOnchangeNegeri(userIdNeg,"view");
            		
            	}//close doChangeProjekNegeriUpdate
            	
            	else if("onchangeJenisKodDaerah".equals(submit3)){
            		
            		//onchange validation
            		context.put("onchange","yes");
            		
            		//get and set data
            		getAndSetDataOnchangeNegeri(userIdNeg,"view");
            		
            	}//close onchangeJenisKodDaerah
            	
            	else if("getJenisKodDaerah".equals(submit3)){
            		
            		//onchange validation
            		context.put("onchange","yes");
            		
            		//get and set data
            		getAndSetDataOnchangeNegeri(userIdNeg,"view");
            		
            		//get jenis kod daerah
            		getJenisKodDaerah(getParam("txdTarikhPermohonan"));
            		String sorJenisKodDaerah = getJenisKodDaerah(getParam("txdTarikhPermohonan"));
            		
            		if(userIdNeg!=""){
            			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,userIdNeg,"socDaerah",Utils.parseLong(getParam("socDaerah")),null,"id=socDaerah style=width:300px"));
            		}else{
            			context.put("selectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"socDaerah",Utils.parseLong(getParam("socDaerah")),null,"id=socDaerah style=width:300px"));
            		}
            		
            	}//close getJenisKodDaerah
            	
            	else if("updatePendaftaran".equals(submit3)){
            		myLogger.info("update pendaftaran");
            		updatePendaftaran(session,userIdNeg);
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//update data
                	header.setDataHeader(idpermohonan);
            		dataHeader = header.getDataHeader();
            		context.put("dataHeader", dataHeader);
            		if(dataHeader.size()!=0){
            			Hashtable dh = (Hashtable) dataHeader.get(0);
            			flagSegera = dh.get("flag_segera").toString();
            		}
            		
            		//senarai semak
            		ListCheckBox(idpermohonan);
            		
            		//data pendaftaran
            		DataPermohonan(idpermohonan,"disabled");
            		
            	}//close updatePendaftaran
            	
        	}//close kemaskiniPendaftaran
    		
    		//screen
	        vm = screenUtama;
    		
    	}//close semakPendaftaran
    	
    	else 
    	if("tambahDokumen".equals(submit)){
    			
    		//alert jsp
    		context.put("completed",false); 
    		
    		//list dokumen
    		ListDokumen(idpermohonan);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("uploadFile".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			//upload file
            		uploadFiles();
             	}

        		//list dokumen
        		ListDokumen(idpermohonan);
        		
             	//alert jsp
             	context.put("completed",true);
        		
        	}//close uploadFile
    		
    		//screen
            vm = screenDokumen;
            
    	}//close tambahDokumen
    	
    	else
        if("hapusDokumen".equals(submit)){
        		
        	hapusDokumen(session);
        	
        	//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//list hakmilik
    		ListHakmilik(idpermohonan,noLOT,idpegawai);
    		
    		//senarai semak
    		ListCheckBox(idpermohonan);
    		
    		//data pendaftaran
    		DataPermohonan(idpermohonan,"disabled");
    		
    		//list dokumen
    		ListDokumen(idpermohonan);
        	
        	//screen
	        vm = screenUtama;
        	
        }//close hapusDokumen
    	
        else
        if("hapusDokumenII".equals(submit)){
            	
        	hapusDokumen(session);
        	
        	//alert jsp
    		context.put("completed",false); 
    		
    		//list dokumen
    		ListDokumen(idpermohonan);
    		
        	//screen
            vm = screenDokumen;
            
        }//close hapusDokumenII
    	
    	
        else 
        	if("salinHakmilik".equals(submit)){
            	
        		//form validation
        		context.put("mode","new");        		
        		//get data from pendaftaran
        		newDataSetting(idpermohonan);
        		
        		String id_hakmilik_salin = getParam("id_hakmilik_salin");
            	this.context.put("id_hakmilik_salin", id_hakmilik_salin);  
            	this.context.put("maklumat_Hakmilik_Salin", "");
        		
        		if(!id_hakmilik_salin.equals("") && !id_hakmilik_salin.equals(null) && model.salin_maklumat_tanah(id_hakmilik_salin).size()>0)
            	{
            	this.context.put("maklumat_Hakmilik_Salin", model.salin_maklumat_tanah(id_hakmilik_salin));             	
            	dataHakmilik_copy(id_hakmilik_salin,"enabled");            	
            	}
            	vm = screenTanah;
        		    
            }//close tambahHM
        	
    	
    	else
        if("tambahHakmilik".equals(submit)){
            	
        	//form validation
    		context.put("mode","new");    		
    		//clear value
    		clearValueHM();
    		
    		noLOT = getParam("carianNoLot2");
    		context.put("carianNoLot2",noLOT.trim());
    		
    		//list hakmilik
    		ListHakmilik(idpermohonan,noLOT,idpegawai);
    		
    		//get data from pendaftaran
    		newDataSetting(idpermohonan);
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("doOnchange".equals(submit2)){

        		//check validation jenis hakmilik & jenis rizab
        		checkValOnChange();
        		
        		//check validation convert
        		checkValConvert();
        
        		//get and set data
        		getAndSetHM(idpermohonan,"new",id_projekNegeri);
        	
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("onchangeUnitLuasAsal".equals(submit3)){
            		
            		//validations for luas asal
            		validationConvertLuas();
            		
            		String submit4 = getParam("command4");
                	myLogger.info("submit[4] : " + submit4);
                	
                	if("convertNilaiAsal".equals(submit4)){
                		
                		calculateNilaiAsal();
                		
                	}//close convertNilaiAsal
                	
                	else if("clearConvertAsal".equals(submit4)){
                		
                		clearConvertAsal("new");
                		
                	}//close clearConvertAsal
                	
                	else if("onchangeUnitAsal".equals(submit4)){
                		
                		//convert nilai lain
                		changeUnitAsal();
                		
                	}//close onchangeUnitAsal
                	
            	}//close onchangeUnitLuasAsal
            	else if("onchangeUnitLuasAll".equals(submit3)){
            		//validations for luas asal
            		validationConvertLuas();
            		validationConvertLuasAmbil();
            		
            		String submit4 = getParam("command4");
                	myLogger.info("submit[4] : " + submit4);
                	
                	if("convertNilaiAll".equals(submit4)){
                		
                		calculateNilaiAsal();
                		calculateNilaiAmbil();
                		
                	}            		
            	}
            	
            	else if("onchangeUnitLuasAmbil".equals(submit3)){
            		
            		//validations for luas ambil
            		validationConvertLuasAmbil();
               		
            		String submit4 = getParam("command4");
                	myLogger.info("submit[4] : " + submit4);
                	
                	if("convertNilaiAmbil".equals(submit4)){
                		
                		calculateNilaiAmbil();
                		
                	}//close convertNilaiAmbil
                	
                	else if("clearConvertAmbil".equals(submit4)){
                		
                		clearConvertAmbil("new");
                		
                	}//close clearConvertAmbil
                	
                	else if("onchangeUnitAmbil".equals(submit4)){

                		//convert nilai lain
                		changeUnitAmbil();
                		
                	}//close onchangeUnitAmbil
                	
            	}//close onchangeUnitLuasAmbil
            	
        	}//close doOnchange
    		
        	else if("simpanHakmilik".equals(submit2)){
        		
        		if (doPost.equals("true")) {
        			//simpan hm
            		simpanHM(session);
             	}
        		
        		//form validation
        		context.put("mode","new");
        		
        		//clear value
        		clearValueHM();
 
        		//list hakmilik
        		ListHakmilik(idpermohonan,noLOT,idpegawai);
        		
        		//get data from pendaftaran
        		newDataSetting(idpermohonan);
        		
        		//update header
            	header.setDataHeader(idpermohonan);
        		dataHeader = header.getDataHeader();
        		context.put("dataHeader", dataHeader);
        		if(dataHeader.size()!=0){
        			Hashtable dh = (Hashtable) dataHeader.get(0);
        			flag_subjaket = dh.get("flag_subjaket").toString();
        		}
        		
        	}//close simpanHakmilik
        	
        	//screen
    		vm = screenTanah;
    		
        }//close tambahHakmilik
    	
    	
        else if("viewHM".equals(submit)){
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//check field validation
    		checkFieldValidation(idHakmilik);
    		
    		noLOT = getParam("carianNoLot2");
    		context.put("carianNoLot2",noLOT.trim());
    		
    		//list hakmilik
    		ListHakmilik(idpermohonan,noLOT,idpegawai);
    		
    		//data hakmilik
    		dataHakmilik(idHakmilik,"disabled");
    		
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
        	if("kemaskiniHM".equals(submit2)){
        		
        		//form validation
        		context.put("mode","view");
        		context.put("isEdit","yes");
        		
        		//data hakmilik
        		dataHakmilik(idHakmilik,"enabled");
        		
        		String submit3 = getParam("command3");
            	myLogger.info("submit[3] : " + submit3);
            	
            	if("doOnchangeUpdate".equals(submit3)){
            		
            		//onchange validation
            		context.put("onchangeHM","yes");
            		
            		//check validation jenis hakmilik & jenis rizab
            		checkValOnChange();
            		
            		//check validation convert
            		checkValConvert();
            
            		//get and set data
            		getAndSetHM(idpermohonan,"view",id_projekNegeri);
            		
            		
            		String submit4 = getParam("command4");
                	myLogger.info("submit[4] : " + submit4);
                	
                	if("onchangeUnitLuasAsalUpdate".equals(submit4)){
                		
                		//validations for luas asal
                		validationConvertLuas();
                		
                		String submit5 = getParam("command5");
                    	myLogger.info("submit[5] : " + submit5);
                    	
                    	if("convertNilaiAsalUpdate".equals(submit5)){
                    		
                    		calculateNilaiAsal();
                    		
                    	}//close convertNilaiAsalUpdate
                    	
                    	else if("clearConvertAsalUpdate".equals(submit5)){
                    		
                    		clearConvertAsal("view");
                    		
                    	}//close clearConvertAsalUpdate
                    	
                    	else if("onchangeUnitAsalUpdate".equals(submit5)){
                    		
                    		//convert nilai lain
                    		changeUnitAsal();
                    		
                    	}//close onchangeUnitAsalUpdate
                    	
                	}//close onchangeUnitLuasAsalUpdate
                	
                	else if("onchangeUnitLuasAmbilUpdate".equals(submit4)){
                		
                		//validations for luas ambil
                		validationConvertLuasAmbil();
                		
                		String submit5 = getParam("command5");
                    	myLogger.info("submit[5] : " + submit5);
                    	
                    	if("convertNilaiAmbilUpdate".equals(submit5)){
                    		
                    		calculateNilaiAmbil();
                    		
                    	}//close convertNilaiAmbilUpdate
                    	
                    	else if("clearConvertAmbilUpdate".equals(submit5)){
                    		
                    		clearConvertAmbil("view");
                    		
                    	}//close clearConvertAmbilUpdate
                    	
                    	else if("onchangeUnitAmbilUpdate".equals(submit5)){

                    		//convert nilai lain
                    		changeUnitAmbil();
                    		
                    	}//close onchangeUnitAmbilUpdate
                		
                	}//close onchangeUnitLuasAmbilUpdate
                	
            	}//close doOnchangeUpdate
        		
            	else if("updateHM".equals(submit3)){
            		
            		updateHM(session,idHakmilik,id_projekDaerah);
            		
            		//form validation
            		context.put("mode","view");
            		context.put("isEdit","no");
            		
            		//check field validation
            		checkFieldValidation(idHakmilik);
            	
            		//list hakmilik
            		ListHakmilik(idpermohonan,noLOT,idpegawai);
            		
            		//data hakmilik
            		dataHakmilik(idHakmilik,"disabled");
            		
            	}//close updateHM
            	
        	}//close kemaskiniHM
    		
    		//screen
    		vm = screenTanah;
    		
    	}//close viewHM
    	
    	else 
    	if("hapusHM".equals(submit)){
    	    		
    		hapusHM(session);
    		
    		//form validation
    		context.put("mode","new");
    		
    		//clear value
    		clearValueHM();

    		//list hakmilik
    		ListHakmilik(idpermohonan,noLOT,idpegawai);
    		
    		//get data from pendaftaran
    		newDataSetting(idpermohonan);
    		
    		//screen
    		vm = screenTanah;
    		
    	}//close hapusHM
    	
    	else 
    	if("updateFlagSah".equals(submit)){
    			
    		updateFlagSah(session,idpermohonan);
    		
    		if (doPost.equals("true")) {  

    			nameAndIdPengarah(id_fail);
    			String id_pengarah = nameAndIdPengarah(id_fail);
    			//System.out.println("ID PENGARAH-- : "+id_pengarah);
    			
    			sendEmail("content","Pengesahan Permohonan Pengambilan Tanah di Bawah Seksyen 8",id_pengarah, tujuan, tarikh_permohonan,nama_kementerian);
    			//sendEmail(id_pengarah,"ptTOpengarah",tujuan,tarikh_permohonan,nama_kementerian);   		
    		}
    		
    		//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//list hakmilik
    		ListHakmilik(idpermohonan,noLOT,idpegawai);
    		
    		//senarai semak
    		ListCheckBox(idpermohonan);
    		
    		//data pendaftaran
    		DataPermohonan(idpermohonan,"disabled");
    		
    		//list dokumen
    		ListDokumen(idpermohonan);
    		
    		//screen
	        vm = screenUtama;
	        
    	}//close updateFlagSah
    	
    	else 
        if("sah".equals(submit)){
        	
        	//get current idsuburusanstatusfail
    		String id_suburusanstatusfailppt = "";
    		model.setGetIdSuburusanstatusfail(idpermohonan);
    		getIdSuburusanstatusfail = model.getGetIdSuburusanstatusfail();
    		if(getIdSuburusanstatusfail.size()!=0){
    			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
    			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
    		}
    		
    		if (doPost.equals("true")) {  
    
    			sah(session,idpermohonan,id_suburusanstatusfailppt,userIdNeg);
    			
    		
    			nameAndIdPengarah(id_fail);
    			String id_pengarah = nameAndIdPengarah(id_fail);
    			
    			//System.out.println("ID PENGARAH-- : "+id_pengarah);
    			
    			sendEmail("content","Makluman Permohonan Pengambilan Tanah di Bawah Seksyen 8",id_pengarah, tujuan, tarikh_permohonan,nama_kementerian);	
    		}
    		
    		
    		//email notification untuk user kjp online
    		
    		
        	//form validation
    		context.put("mode","view");
    		context.put("isEdit","no");
    		
    		//update status
    		dataHeader(idpermohonan);
    		id_status = dataHeader(idpermohonan);
    		
    		//list hakmilik
    		ListHakmilik(idpermohonan,noLOT,idpegawai);
    		
    		//senarai semak
    		ListCheckBox(idpermohonan);
    		
    		//data pendaftaran
    		DataPermohonan(idpermohonan,"disabled");
    		
    		//list dokumen
    		ListDokumen(idpermohonan);
    		
    		//screen
	        vm = screenUtama;
	        
        }//close sah
    	
    	
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = FrmPermohonanUPTData.getListCarianSek8();
	
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
//		else 
//	    if("testemail".equals(submit)){
//	            	
//	    	
//	        EmailTester et = new EmailTester();
//	        et.setEmail("seksyen8","test","hantarUntukPengesahan","","",tarikh_permohonan,nama_kementerian);
//	        	
//	        listPageDepan = model.getListPemohonSeksyen8(userIdNeg);
//    		
//    		context.put("nofail", "");
//			context.put("carianTarikh", "");
//			context.put("carianStatus", "");
//			
//    		//screen
//    		vm = listdepan;
//    		
//	    }//close testemail
    	
		else if ("getSenaraiHakmilik".equals(submit)) {			
			String id_permohonan = "";
			String carianNoLot = "";
			if (!getParam("id_permohonan").equals("") && !getParam("id_permohonan").equals(null))
			{
				id_permohonan = getParam("id_permohonan");
			}
			if (!getParam("carianNoLot").equals("") && !getParam("carianNoLot").equals(null))
			{
				carianNoLot = getParam("carianNoLot");
			}		
			if (!id_permohonan.equals("") && !id_permohonan.equals(null))
			{
				getListHakmilik(id_permohonan, carianNoLot, idpegawai);
			}
			vm = "app/ppt/frmSeksyen8ListHM_load.jsp";
		}
		else if ("getSenaraiHakmilik_hidden".equals(submit)) {
			int input_to = 50;
			int input_from = 0;
			int setLimit = 750;
			context.put("input_to", input_to);
			context.put("input_from", input_from);
			context.put("setLimit", setLimit);
			vm = "app/ppt/frmSeksyen8ListHM_hidden.jsp";
		}
		else if ("getSenaraiHakmiliknext_hidden".equals(submit)) {
			int input_to = 50;
			int input_from = 0;
			int setLimit = 750;
			if (!getParam("input_to").equals("") && !getParam("input_to").equals(null)) {
				input_to = Integer.parseInt(getParam("input_to"));
			}
			if (!getParam("input_from").equals("") && !getParam("input_from").equals(null)) {
				input_from = Integer.parseInt(getParam("input_from"));
			}
			if (!getParam("setLimit").equals("") && !getParam("setLimit").equals(null)) {
				setLimit = Integer.parseInt(getParam("setLimit"));
			}
			context.put("input_to", input_to + 50);
			context.put("input_from", input_to + 1);
			context.put("setLimit", setLimit + 750);
			vm = "app/ppt/frmSeksyen8ListHM_hidden.jsp";
		}
		else if ("getSenaraiHakmilik_next".equals(submit)) {
			String id_permohonan = "";
			String carianNoLot = "";
			if (!getParam("id_permohonan").equals("") && !getParam("id_permohonan").equals(null));
			{
				id_permohonan = getParam("id_permohonan");
			}
			if (!getParam("carianNoLot").equals("") && !getParam("carianNoLot").equals(null));
			{
				carianNoLot = getParam("carianNoLot");
			}
			if (!id_permohonan.equals("") && !id_permohonan.equals(null))
			{
				getListHakmilikNext(id_permohonan, carianNoLot, idpegawai);
			}
			vm = "app/ppt/frmSeksyen8ListHMnext_load.jsp";
		}    	
    	else{	
    		
    		listPageDepan = model.getListPemohonSeksyen8(userIdNeg);
    		
    		context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");
			
    		//screen
    		vm = listdepan;
    		
		}//close else
    	//Kegunaan Integrasi
    	context.put("ID_NEGERIPROJEK", negeriIntegrasi);

    		//list permohonan
	    	context.put("listPermohonan", listPageDepan);
	    	context.put("list_size", listPageDepan.size());
	    
	    	//for paging 1 ~ 24
			context.put("flagSegera",flagSegera);
			
			//flag subjaket
			context.put("flag_subjaket",flag_subjaket);
			
			//flag status online
			context.put("flagStatusOnline", flagStatusOnline);
			
			//id negeri user
			context.put("userIdNeg",userIdNeg);
			
	    	//id
	    	context.put("id_permohonan", idpermohonan);
	    	context.put("id_hakmilik", idHakmilik);
	    	context.put("id_status", id_status);
	    	context.put("id_fail", id_fail);
	    	
    		
    		this.context.put("selectedTab",selectedTab);
    		setupPage(session,action,listPageDepan);
    		return vm;
    		
	}//close public template
	
	public void insertPopupReg(String nama_class,String tajuk_class, String group,Db db) throws Exception {
	//Db db = null;
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
	//	if (db != null)db.close();
		}
	}
	
	public void checkFieldWujud(String table_name,String column_name,String data_type,Db db)  throws Exception {
		  
		  	int total = 0;
		  	String sql="";
		  	ResultSet rs = null;
			try {
				sql = " SELECT COUNT(*) as total FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"+table_name+"' AND COLUMN_NAME = '"+column_name+"' ";	
				//System.out.println("SQL checkFieldWujud :"+sql);
				rs = db.getStatement().executeQuery(sql); 
			if ( rs.next() ) { 
				total = rs.getInt("total"); 
			} 
			} finally { 					
			} 
			
			if(total==0)
			{				
				sql = "ALTER TABLE "+table_name+" "+
						" ADD "+column_name+" "+data_type+" ";
				//System.out.println(" sql :"+sql);
				rs = db.getStatement().executeQuery(sql); 			
				//ALTER TABLE supplier ADD supplier_name varchar2(50);				
			}
			
	  }
		
	
	
	public int checkRegPopup(String class_name,Db db)  throws Exception {
	  
	 // 	Db db = null; 
	  	int total = 0;
	  	String sql="";
	  	ResultSet rs = null;
		try {
		//db = new Db(); 
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
	
	public String checkStatusRegisterPopup(String QUERY) throws Exception {
		String display_error="";
		String error_code = ""; 
		Db db = new Db();
		
		String sql = "";
		try {
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			myLogger.info("CHECK QUERY :"+QUERY);
			
			stmt.executeQuery(QUERY);
		}   
		catch (Exception e) {
			myLogger.info("e.getMessage().toUpperCase() :"+e.getMessage().toUpperCase());			
			try {
				error_code = e.getMessage().toUpperCase().substring(e.getMessage().toUpperCase().lastIndexOf('-')+1, e.getMessage().toUpperCase().lastIndexOf(':'));
			}
			catch (Exception f) {
				error_code = "";
				myLogger.info("ADA ERROR :"+f.getMessage().toUpperCase());
			}
			myLogger.info("TEMP ::::::::::"+error_code);	
			
			
				if(QUERY.equals(""))
				{
					display_error = " SILA PASTIKAN QUERY UNTUK SEMAKAN PAPARAN PERUBAHAN DATA DIISI PADA MODUL ADMIN.";	
				}
				else
				{
								
						display_error = " "+e.getMessage()+". SILA HUBUNGI PENTADBIR SISTEM.";	
					
				}
			
			
		} finally {
			if (db != null)	db.close();
		}
		
		
		
		myLogger.info("DISPLAY_ERROR :"+display_error);
		return display_error.replace( "\"", "" );
	}
	

	
	private void getListHakmilikNext(String idpermohonan, String noLOT,String idpegawai) throws Exception {
		int input_to = 50;
		int input_from = 0;
		int setLimit = 750;
		if (!getParam("input_to").equals("") && !getParam("input_to").equals(null)) {
			input_to = Integer.parseInt(getParam("input_to"));
		}
		if (!getParam("input_from").equals("") && !getParam("input_from").equals(null)) {
			input_from = Integer.parseInt(getParam("input_from"));
		}
		if (!getParam("setLimit").equals("") && !getParam("setLimit").equals(null)) {
			setLimit = Integer.parseInt(getParam("setLimit"));
		}
		context.put("input_to", input_to + 50);
		context.put("input_from", input_to + 1);
		context.put("setLimit", setLimit + 750);
		Vector listHakmilik = new Vector();
		listHakmilik.clear();
		String nama2Mukim = "";
		
		
		//model.setListMaklumatTanah_listHakmilik(idpermohonan, noLOT, idpegawai,input_to + 1, input_to + 50);
		//listHakmilik = model.getListMaklumatTanah();
		
		/*
		if (listHakmilik.size() != 0) {
			Hashtable lmt = (Hashtable) listHakmilik.get(0);
			nama2Mukim = (String) lmt.get("nama2Mukim");
		}
		*/
		
		
		Hashtable senarai_mukim_lot = model.getListMukimLot(idpermohonan, noLOT, idpegawai, input_from,input_to);
		if (senarai_mukim_lot.size() != 0) {
			nama2Mukim = senarai_mukim_lot.get("nama2Mukim").toString();
		}		
		//context.put("listMaklumatTanah", listHakmilik);
		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
		context.put("nama2Mukim", nama2Mukim);
	}

	private void getListHakmilik(String idpermohonan, String noLOT,	String idpegawai) throws Exception {
		int input_to = 50;
		int input_from = 0;
		int setLimit = 750;
		if (!getParam("input_to").equals("") && !getParam("input_to").equals(null)) {
			input_to = Integer.parseInt(getParam("input_to"));
		}
		if (!getParam("input_from").equals("") && !getParam("input_from").equals(null)) {
			input_from = Integer.parseInt(getParam("input_from"));
		}
		if (!getParam("setLimit").equals("") && !getParam("setLimit").equals(null)) {
			setLimit = Integer.parseInt(getParam("setLimit"));
		}
		context.put("input_to", input_to);
		context.put("input_from", input_from);
		context.put("setLimit", setLimit);
		Vector listHakmilik = new Vector();
		listHakmilik.clear();
		String nama2Mukim = "";
		
		
		
		/*
		model.setListMaklumatTanah_listHakmilik(idpermohonan, noLOT, idpegawai, input_from,input_to);
		listHakmilik = model.getListMaklumatTanah();
		
		if (listHakmilik.size() != 0) {
			Hashtable lmt = (Hashtable) listHakmilik.get(0);
			nama2Mukim = (String) lmt.get("nama2Mukim");
		}
		*/
		
		//setListMaklumatTanah_count
		
		
		Hashtable senarai_mukim_lot = model.getListMukimLot(idpermohonan, noLOT, idpegawai, input_from,input_to);
		if (senarai_mukim_lot.size() != 0) {
			nama2Mukim = senarai_mukim_lot.get("nama2Mukim").toString();
		}
		//context.put("listMaklumatTanah", listHakmilik);
		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
		context.put("nama2Mukim", nama2Mukim);
	}	
	
//--METHOD--//	
	
	
	private String getJenisKodDaerah(String dateToday) throws Exception{
		
		String sorJenisKodDaerah = "";
		SimpleDateFormat format =  new SimpleDateFormat("dd/MM/yyyy");
		Date dateKod = format.parse("01/10/2010"); 
		Date sysdate = format.parse(dateToday);

		//sebelum 01/10/2010
		if(sysdate.before(dateKod)){
			sorJenisKodDaerah = "1";	
		//selepas 01/10/2010	
		}else{
			sorJenisKodDaerah = "2";		
		}

		context.put("sorJenisKodDaerah", sorJenisKodDaerah);
		
		return sorJenisKodDaerah;
		
	}//close getJenisKodDaerah
	
	@SuppressWarnings({ "unchecked", "static-access" })
	/*private void sendEmail(String id_pengarah,String jenisSend,String nama_projek,String tarikh_permohonan,String nama_kementerian) throws Exception{
	
	String tiadaNoFail = "";
	
	//Vector checkEmail = new Vector();
	Vector checkEmailPengarah = new Vector();
	
	checkEmailPengarah.clear();
	//checkEmail.clear();
	
	//check email user login
	checkEmail = myInfo.checkEmail(id_masukPermohonan);
	String emelUser = "";
	if(checkEmail.size()!=0){
		Hashtable ce = (Hashtable)checkEmail.get(0);
		emelUser = (String)ce.get("EMEL");
	}
	
	//check email (pengarah)
	checkEmailPengarah = myInfo.checkEmail(id_pengarah);
	String emelPengarah = "";
	if(checkEmailPengarah.size()!=0){
		Hashtable ceP = (Hashtable)checkEmailPengarah.get(0);
		emelPengarah = (String)ceP.get("EMEL");
	}
	System.out.println("emelPengarah---"+emelPengarah);

	EmailTester et = new EmailTester();
	
	//if(emelPengarah!=""){

		//if(jenisSend.equals("ptTOpengarah")){
			et.setEmail("seksyen8",emelPengarah,"hantarUntukPengesahan",tiadaNoFail,nama_projek,tarikh_permohonan,nama_kementerian);
		}else{
			et.setEmail(emelUser,"telahDisahkan");
		}	
	//}
	
	//jenis email
	// - hantar pengesahan (pt - pengarah)
	// - hantar untuk diagihankan
	// - hantar pengesahan mmk (pt - pengarah)
	
}//close sendEmail
*/

//function email PENGARAH-penambahan yati 21/3/2017
	 public void sendEmail(String content,String subjek,String id_pengarah,String nama_projek,String tarikh_permohonan,String nama_kementerian) throws Exception {
		 
			Vector checkEmailPengarah = new Vector();
			checkEmailPengarah.clear();
			//System.out.println("id dataListPengarah :::"+id_pengarah);

			// check email (pengarah)
			checkEmailPengarah = myInfo.checkEmail(id_pengarah);
			String emelPengarah = "";
			if (checkEmailPengarah.size() != 0) {
				Hashtable ceP = (Hashtable) checkEmailPengarah.get(0);
				emelPengarah = (String) ceP.get("EMEL");
			}

			//System.out.println("emel ::"+emelPengarah);
			
			EmailProperty pro = EmailProperty.getInstance();
			//String EMAIL_HOST = pro.getHost_GM();
			EmailSender email = EmailSender.getInstance();
		
		//myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
		//myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
		email.FROM = pro.getFrom();
		email.SUBJECT = subjek;
		email.MESSAGE = "<html><table><tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
				"<tr><td>Tarikh Permohonan</td><td>:</td><td>"+tarikh_permohonan+"</td></tr>" +
				"<tr><td>Nama Kementerian</td><td>:</td><td>"+nama_kementerian+"</td></tr>" +
				"<tr><td>Telah berjaya disahkan</td></tr>" +
				"<tr><td>&nbsp;</td><td>&nbsp;</td><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
				"</table></html>" ;
		email.RECIEPIENT = emelPengarah;
		
		myLogger.info(" ---------- email :"+email);	
		//email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";	
		email.TO_CC = new String[1];		
		email.TO_CC[0] = "testingetapp@gmail.com";
		email.sendEmail();
		
	 } //close hantar emel
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetData(String jenisNofail,String mode,String idpermohonan,String userIdNeg) throws Exception{
    	
		Vector checkExistNoFail = new Vector();
		Vector dataPermohonan = new Vector();
		
		checkExistNoFail.clear();
		dataPermohonan.clear();
		
		String no_rujukan_ptg = "";
		String no_rujukan_ptd = "";
		String no_rujukan_upt = "";
		
		if(mode.equals("edit")){
			model.setListPohon(idpermohonan);
 			dataPermohonan = model.getListPohon();	
 			if(dataPermohonan.size()!=0){
 				Hashtable dp = (Hashtable)dataPermohonan.get(0);
 				no_rujukan_ptg = (String)dp.get("no_rujukan_ptg");
 				no_rujukan_ptd = (String)dp.get("no_rujukan_ptd");
 				no_rujukan_upt = (String)dp.get("no_rujukan_upt");
 			}
		}
		
		//validation jenis no fail
		if(!jenisNofail.equals("")){			
			
			model.setCheckExistNoFail(getParam("curNoFail"),jenisNofail,"8",no_rujukan_ptg,no_rujukan_ptd,no_rujukan_upt);
	 		checkExistNoFail = model.getCheckExistNoFail();	
	 		
	 		if(checkExistNoFail.size()!=0){
	 			context.put("showAlertDuplicate", "yes");
	 			context.put("showNoRujukan", getParam("curNoFail"));
	 			
	 			if(jenisNofail.equals("ptg")){
	 				context.put("valSimpanNoRujukanPTG", "no");
	 			}else if(jenisNofail.equals("ptd")){
	 				context.put("valSimpanNoRujukanPTD", "no");
	 			}else if(jenisNofail.equals("upt")){
	 				context.put("valSimpanNoRujukanUPT", "no");
	 			}
	 			
	 			
	 		}else{
	 			context.put("showAlertDuplicate", "no");
	 			if(jenisNofail.equals("ptg")){
	 				context.put("valSimpanNoRujukanPTG", "yes");
	 			}else if(jenisNofail.equals("ptd")){
	 				context.put("valSimpanNoRujukanPTD", "yes");
	 			}else if(jenisNofail.equals("upt")){
	 				context.put("valSimpanNoRujukanUPT", "yes");
	 			}
	 		}
	 		
	 		
	 	/*	if(jenisNofail.equals("ptg")){
	 			if(checkExistNoFail.size()!=0){
		 			context.put("showAlertDuplicate", "yes");
		 			context.put("showNoRujukan", getParam("curNoFail"));
		 			context.put("valSimpanNoRujukanPTG", "no");
		 		}else{
		 			context.put("showAlertDuplicate", "no");
		 			context.put("valSimpanNoRujukanPTG", "yes");
		 		}
	 		}else if(jenisNofail.equals("ptd")){
	 			if(checkExistNoFail.size()!=0){
		 			context.put("showAlertDuplicate", "yes");
		 			context.put("showNoRujukan", getParam("curNoFail"));
		 			context.put("valSimpanNoRujukanPTD", "no");
		 		}else{
		 			context.put("showAlertDuplicate", "no");
		 			context.put("valSimpanNoRujukanPTD", "yes");
		 		}
	 		}else if(jenisNofail.equals("upt")){
	 			
	 		}
	 	*/	
	 		
		}
		
		String id_negeriprojek = "";
		if(userIdNeg.equals("14")){
			id_negeriprojek = getParam("socNegeriProjek");
		}else{
			id_negeriprojek = userIdNeg;
		}
		
		String id_kementerian = getParam("socKementerian");
		
		if(mode.equals("new")){
			
			context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(id_kementerian),null,"style=width:500px onChange=\"doChangeKementerian();\" "));
			//20042011
			if(userIdNeg.equals("14") || userIdNeg.equals("15") || userIdNeg.equals("16")){
				context.put("selectNegeriProjek",HTML.SelectNegeriMampuKL("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px onChange=\"doChangeProjekNegeri();\" "));
			}else{
				context.put("selectNegeriProjek",HTML.SelectNegeriMampu("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px class=disabled disabled onChange=\"doChangeProjekNegeri();\" "));
			}
			
		}else{
			context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(id_kementerian),null,"style=width:500px onChange=\"doChangeKementerianUpdate();\" "));
			
			//20042011
			if(userIdNeg.equals("14") || userIdNeg.equals("15") || userIdNeg.equals("16")){
				context.put("selectNegeriProjek",HTML.SelectNegeriMampuKL("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px onChange=\"doChangeProjekNegeriUpdate();\" "));
			}else{
				context.put("selectNegeriProjek",HTML.SelectNegeriMampu("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px class=disabled disabled onChange=\"doChangeProjekNegeriUpdate();\" "));
			}
			
		}
		
		Vector dataKementerian = model.getAlamatKementerian(id_kementerian);    		
		String id_negeri = "";
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";   		
		if(dataKementerian.size()!=0){
			Hashtable AK = (Hashtable) dataKementerian.get(0);
			id_negeri = AK.get("id_negeri").toString();
			alamat1 = AK.get("alamat1").toString();
			alamat2 = AK.get("alamat2").toString();
			alamat3 = AK.get("alamat3").toString();
			poskod = AK.get("poskod").toString();
		}
		
		context.put("txtPoskod", poskod);
		context.put("txtAlamat1", alamat1);
		context.put("txtAlamat2", alamat2);
		context.put("txtAlamat3", alamat3);
		
		context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
		context.put("txdPermohonanLengkap", getParam("txdPermohonanLengkap"));
		context.put("txtNoRujukanPTG", getParam("txtNoRujukanPTG"));
		context.put("txtNoRujukanPTD", getParam("txtNoRujukanPTD"));
		context.put("txtNoRujukanUPT", getParam("txtNoRujukanUPT"));
		context.put("txtTujuan", getParam("txtTujuan"));
		context.put("txtRujukanKementerian", getParam("txtRujukanKementerian"));
		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		context.put("txdTarikhKehendaki", getParam("txdTarikhKehendaki"));
		context.put("sorFlagPeruntukan", getParam("sorFlagPeruntukan"));
		context.put("sorFlagSegera", getParam("sorFlagSegera"));
		context.put("sorJenisProjek", getParam("sorJenisProjek"));
		
		//untuk n9 [27042011]
		context.put("txtTujuanBI", getParam("txtTujuanBI"));
		
		String sorJenisKodDaerah = getParam("sorJenisKodDaerah");
		context.put("sorJenisKodDaerah", sorJenisKodDaerah);
		
		String id_daerah = getParam("socDaerah");
		
		//dropdown by
		if(id_kementerian!=""){
			context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Utils.parseLong(getParam("socAgensi")),"id=socAgensi style=width:500px"));
		}else{
			context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Utils.parseLong(getParam("socAgensi")),"id=socAgensi style=width:500px"));	
		}
		
		if(id_negeriprojek!=""){
			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,id_negeriprojek,"socDaerah",Utils.parseLong(id_daerah),null,"style=width:300px"));
    	}else{
    		context.put("selectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"socDaerah",Utils.parseLong(id_daerah),null,"style=width:300px"));
        }
		
		//dropdown
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),"class=disabled disabled style=width:325px"));
		
		//validation jajahan
		if(id_negeriprojek.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
	}//close getAndSetData
	
	@SuppressWarnings("unchecked")
	private void sah(HttpSession session,String idpermohonan,String id_suburusanstatusfailppt,String userIdNegeri) throws Exception{
	    
		Vector dataPermohonan = new Vector();
		dataPermohonan.clear();
		
		Hashtable h = new Hashtable();
	 		
		h.put("id_permohonan", idpermohonan);
	 	h.put("id_fail", getParam("id_fail"));
	 	
	 	model.setListPohon(idpermohonan);
 		dataPermohonan = model.getListPohon();		
 		String id_daerah = "";
 		String id_negeriprojek = "";
 		String id_kementerian = "";
 		String id_negeri = "";
 		String flag_jenis_kod_daerah = "";
		if(dataPermohonan.size()!=0){
			Hashtable dp = (Hashtable)dataPermohonan.get(0);
			id_negeri = dp.get("idNegeri").toString();
			id_kementerian = dp.get("idKementerian").toString();
			id_daerah = dp.get("idDaerah").toString();
			id_negeriprojek = dp.get("idProjekNegeri").toString();
			flag_jenis_kod_daerah = (String)dp.get("flag_jenis_kod_daerah");
		}
	
		h.put("id_projekNegeri", id_negeriprojek);
		h.put("idDaerah", id_daerah);
		h.put("id_kementerian", id_kementerian);
	 	h.put("id_negeri", id_negeri);
	 	
	 	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	 	h.put("id_negeriuser", userIdNegeri);
	 	
		FrmPermohonanUPTData.sahkan(h,id_suburusanstatusfailppt,"1491",flag_jenis_kod_daerah);  
			
	}//close sah
	
	@SuppressWarnings("unchecked")
	private void updateFlagSah(HttpSession session,String idpermohonan) throws Exception{
			 
		Hashtable h = new Hashtable();

		h.put("id_permohonan", idpermohonan);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
			    
		FrmPermohonanUPTData.updateFlagSah(h);
	
	}//close updateFlagSah
	
	private void validationConvertLuasAmbil() throws Exception{
		
		String socUnitLuasAmbil = getParam("socUnitLuasAmbil");
		String showButtonConvertAsal = getParam("showButtonConvertAsal");
		String showBoxAsal2 = getParam("showBoxAsal2");
		String showBoxAsal3 = getParam("showBoxAsal3");
		
		if(socUnitLuasAmbil!=""){
			if(socUnitLuasAmbil.equals("4") || socUnitLuasAmbil.equals("8")){
				context.put("showBoxAmbil2","yes");
				context.put("showBoxAmbil3","yes");
			}else if(socUnitLuasAmbil.equals("7")){
				context.put("showBoxAmbil2","yes");
				context.put("showBoxAmbil3","no");
			}else{
				context.put("showBoxAmbil2","no");
				context.put("showBoxAmbil3","no");
			}
		}else{
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}
		
		/* Validation button convert Ambil*/
		if(!socUnitLuasAmbil.isEmpty()){
			context.put("showButtonConvertAmbil","yes");
		}else{
			context.put("showButtonConvertAmbil","no");
		}
		
		/* Validation button convert Asal*/
		if(showButtonConvertAsal.equals("yes")){
			context.put("showButtonConvertAsal","yes");
		}else{
			context.put("showButtonConvertAsal","no");
		}
		
		if(showBoxAsal3.equals("yes")){
			context.put("showBoxAsal2","yes");
			context.put("showBoxAsal3","yes");
		}else if(showBoxAsal2.equals("yes")){
			context.put("showBoxAsal2","yes");
			context.put("showBoxAsal3","no");
		}else{
			context.put("showBoxAsal2","no");
			context.put("showBoxAsal3","no");
		}
		
		//validation convert
		String showFieldAsalBeforeConvert = getParam("showFieldAsalBeforeConvert");
		if(showFieldAsalBeforeConvert.equals("yes")){
			context.put("showFieldAsalBeforeConvert", "yes");
			context.put("showDropdownUnitAsal", "yes");
		}else{
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}
		
	}//close validationConvertLuasAmbil
	
	private void validationConvertLuas() throws Exception{
		
		String socUnitLuasLot = getParam("socUnitLuasLot");
		String showButtonConvertAmbil = getParam("showButtonConvertAmbil");
		String showBoxAmbil2 = getParam("showBoxAmbil2");
		String showBoxAmbil3 = getParam("showBoxAmbil3");
		
		if(socUnitLuasLot!=""){
			if(socUnitLuasLot.equals("4") || socUnitLuasLot.equals("8")){
				context.put("showBoxAsal2","yes");
				context.put("showBoxAsal3","yes");
			}else if(socUnitLuasLot.equals("7")){
				context.put("showBoxAsal2","yes");
				context.put("showBoxAsal3","no");
			}else{
				context.put("showBoxAsal2","no");
				context.put("showBoxAsal3","no");
			}
		}else{
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}
		
		/* Validation button convert Asal*/
		if(!socUnitLuasLot.isEmpty()){
			context.put("showButtonConvertAsal","yes");
		}else{
			context.put("showButtonConvertAsal","no");
		}
		
		/* Validation button convert Ambil*/
		if(showButtonConvertAmbil.equals("yes")){
			context.put("showButtonConvertAmbil","yes");
		}else{
			context.put("showButtonConvertAmbil","no");
		}
		
		if(showBoxAmbil3.equals("yes")){
			context.put("showBoxAmbil2","yes");
			context.put("showBoxAmbil3","yes");
		}else if(showBoxAmbil2.equals("yes")){
			context.put("showBoxAmbil2","yes");
			context.put("showBoxAmbil3","no");
		}else{
			context.put("showBoxAmbil2","no");
			context.put("showBoxAmbil3","no");
		}
		
		//validation convert
		String showFieldAmbilBeforeConvert = getParam("showFieldAmbilBeforeConvert");            		
		if(showFieldAmbilBeforeConvert.equals("yes")){
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		}else{
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}
		
	}//close validationConvertLuas
	
	private void checkValConvert() throws Exception{
		
		//validation convert
		String showFieldAsalBeforeConvert = getParam("showFieldAsalBeforeConvert");
		String showFieldAmbilBeforeConvert = getParam("showFieldAmbilBeforeConvert");
		String showButtonConvertAsal = getParam("showButtonConvertAsal");
		String showButtonConvertAmbil = getParam("showButtonConvertAmbil");
		String showBoxAsal2 = getParam("showBoxAsal2");
		String showBoxAsal3 = getParam("showBoxAsal3");
		String showBoxAmbil2 = getParam("showBoxAmbil2");
		String showBoxAmbil3 = getParam("showBoxAmbil3");
		
		if(showFieldAsalBeforeConvert.equals("yes")){
			context.put("showFieldAsalBeforeConvert", "yes");
			context.put("showDropdownUnitAsal", "yes");
		}else{
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}
		
		if(showFieldAmbilBeforeConvert.equals("yes")){
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		}else{
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}
		
		/* Validation button convert Asal*/
		if(showButtonConvertAsal.equals("yes")){
			context.put("showButtonConvertAsal","yes");
		}else{
			context.put("showButtonConvertAsal","no");
		}
		
		/* Validation button convert Ambil*/
		if(showButtonConvertAmbil.equals("yes")){
			context.put("showButtonConvertAmbil","yes");
		}else{
			context.put("showButtonConvertAmbil","no");
		}
		
		if(showBoxAsal3.equals("yes")){
			context.put("showBoxAsal2","yes");
			context.put("showBoxAsal3","yes");
		}else if(showBoxAsal2.equals("yes")){
			context.put("showBoxAsal2","yes");
			context.put("showBoxAsal3","no");
		}else{
			context.put("showBoxAsal2","no");
			context.put("showBoxAsal3","no");
		}
		
		if(showBoxAmbil3.equals("yes")){
			context.put("showBoxAmbil2","yes");
			context.put("showBoxAmbil3","yes");
		}else if(showBoxAmbil2.equals("yes")){
			context.put("showBoxAmbil2","yes");
			context.put("showBoxAmbil3","no");
		}else{
			context.put("showBoxAmbil2","no");
			context.put("showBoxAmbil3","no");
		}
		
	}//close checkValConvert
	
	private void checkValOnChange() throws Exception{
    	
		String resetRadio = getParam("resetRadio");
		
		//validation jenis hakmilik & jenis rizab
		String id_jenisHakmilik = getParam("socJenisHakmilik");
		String sorJenisRizab = getParam("sorJenisRizab");
		
		/* validation jenis hakmilik */       		
		if( id_jenisHakmilik.equals("2") || id_jenisHakmilik.equals("5") || id_jenisHakmilik.equals("28")
		|| id_jenisHakmilik.equals("73") || id_jenisHakmilik.equals("74") || id_jenisHakmilik.equals("75")
		|| id_jenisHakmilik.equals("80") || id_jenisHakmilik.equals("85") || id_jenisHakmilik.equals("113")){			
			context.put("showLuput","yes");       			  		
		}else{
			context.put("showLuput","no"); 
		}

		if(resetRadio.equals("1")){
			context.put("showWarta","no");
        	context.put("showLainLain","no");
		}else{
			/* validation jenis rizab */
			if(sorJenisRizab.equals("1")){
				context.put("showWarta","yes");
				context.put("showLainLain","no");
			}else if(sorJenisRizab.equals("5")){
				context.put("showWarta","no");
	        	context.put("showLainLain","yes");
			}else{
				context.put("showWarta","no");
	        	context.put("showLainLain","no");
			}
		}
		
		
	}//close checkValOnChange
	
	
	private void hapusHM(HttpSession session) throws Exception{
    	
		String idHakmilik = getParam("id_hakmilik");
		
		Db db = null;
		String no_hakmilik_temp = "";
		try {
		db = new Db();
		Statement stmt = db.getStatement();
		String sql = " SELECT NO_HAKMILIK FROM TBLPPTHAKMILIK " +
				"" +
				" WHERE ID_HAKMILIK = '"+idHakmilik+"'";			
		ResultSet rs = stmt.executeQuery(sql);	
		myLogger.info("SQL  :"+sql);
		while (rs.next()){				
			no_hakmilik_temp = rs.getString("NO_HAKMILIK");				
	    }
	    AuditTrail at = new AuditTrail();
		at.logActivity("","1",null,session,"DEL","HAKMILIK ["+no_hakmilik_temp+"] DELETE");
			
		} finally {
			if (db != null)	db.close();
		}
		
		FrmUPTSek8HakmilikData.hapusHM(idHakmilik);
  
	}//close hapusHM
	

	@SuppressWarnings("unchecked")
	private void checkFieldValidation(String idHakmilik) throws Exception{
		
		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();
		
		//data hakmilik
		model.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = model.getMaklumatTanah();
		String id_jenishakmilik = "";
		String sorJenisRizab = "";
		String luas_asal = "";
		String luas_ambil = "";
	
		if(dataMaklumatTanah.size()!=0){
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_jenishakmilik = h.get("id_jenishakmilik").toString();		
			sorJenisRizab = h.get("flag_jenis_rizab").toString();	
			luas_asal = h.get("luas_lot").toString();	
			luas_ambil = h.get("luas_ambil").toString();
			
		}
		
		//check date luput if jenis hakmilik = pajakan
		if( id_jenishakmilik.equals("2") || id_jenishakmilik.equals("5") || id_jenishakmilik.equals("28")
        	|| id_jenishakmilik.equals("73") || id_jenishakmilik.equals("74") || id_jenishakmilik.equals("75")
        	|| id_jenishakmilik.equals("80") || id_jenishakmilik.equals("85") || id_jenishakmilik.equals("113")){			
        	context.put("showLuput","yes");       			  		
        }else{
        	context.put("showLuput","no"); 
        }

		
		//check jenis rizab field validation
		if(sorJenisRizab.equals("1")){
			context.put("showWarta","yes");
			context.put("showLainLain","no");
		}else if(sorJenisRizab.equals("5")){
			context.put("showWarta","no");
        	context.put("showLainLain","yes");
		}else{
			context.put("showWarta","no");
        	context.put("showLainLain","no");
		}
		
		
		//show value before convert and dropdown unit
		if(luas_asal!=""){
			context.put("showFieldAsalBeforeConvert", "yes");
			context.put("showDropdownUnitAsal", "yes");
		}else{
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}
		
		if(luas_ambil!=""){
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		}else{
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}
		
	}//close checkJenisHakmilik
	
	@SuppressWarnings("unchecked")
	private void dataHakmilik(String idHakmilik,String disability) throws Exception{
	    
		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();
		
		//data hakmilik
		model.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = model.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		String id_negeriprojek = "";
		String id_daerah = "";
		String id_mukim = "";
		String id_jenishakmilik = "";
		String id_luaslot = "";
		String id_lot = "";
		String id_kategoritanah = "";
		String id_daerahpenggawa = "";
		String id_unitluasambil = "";
		String nama_mukim = "";  //penambahan yati
		if(dataMaklumatTanah.size()!=0){
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_mukim = h.get("id_mukim").toString();
			id_jenishakmilik = h.get("id_jenishakmilik").toString();
			id_luaslot = h.get("id_luasLot").toString();
			id_lot = h.get("id_lot").toString();
			id_kategoritanah = h.get("id_kategoritanah").toString();
			id_daerah = h.get("id_daerah").toString();
			id_negeriprojek = h.get("id_negeri").toString();
			id_daerahpenggawa = h.get("id_daerahpenggawa").toString();
			id_unitluasambil = h.get("id_unitluasambil").toString();
			nama_mukim = h.get("nama_mukim").toString();
		}
		
		//System.out.println("nama_mukim---"+nama_mukim);
		
		//validation jajahan
		if(id_negeriprojek.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
		String mode = "";
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		//untuk kelantan shj
		context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("socDaerahPenggawa",Utils.parseLong(id_daerahpenggawa),null," "+mode+" style=width:274px"));
		
		//dropdown
		if(id_negeriprojek.equals("10")){
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik "+mode+" style=width:auto onchange=doOnchangeUpdate()"));   	
		}else{
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik "+mode+" style=width:auto onchange=doOnchangeUpdate()"));   	
		}
		
		context.put("selectKategoriTanah",HTML.SelectKategoriTanah("socKategoriTanah",Utils.parseLong(id_kategoritanah),"id=socKategoriTanah "+mode+" style=width:auto",null));
		context.put("selectKodLot", HTML.SelectUnitPT("socKodLot",Utils.parseLong(id_lot),"style=width:auto "+mode+" "));
		
		//dropdown unit luas
		context.put("selectUnitLuasLot",HTML.SelectLuas("socUnitLuasLot",Utils.parseLong(id_luaslot),"style=width:250px "+mode+" id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
		context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil",Utils.parseLong(id_unitluasambil),"style=width:250px "+mode+" id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
		
		//dropdown by
		if(id_daerah!=""){
			context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(id_daerah,"socMukim",Utils.parseLong(id_mukim),"style=width:auto "+mode+""));
		}else{
			context.put("selectMukim",HTML.SelectMukimNoKod("socMukim",Utils.parseLong(id_mukim),"style=width:auto "+mode+""));
		}
		
	}//close dataHakmilik
	
	
	@SuppressWarnings("unchecked")
	private void dataHakmilik_copy(String idHakmilik,String disability) throws Exception{
	    
		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();
		
		//data hakmilik
		model.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = model.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		
		String id_negeriprojek = "";
		String id_daerah = "";
		String id_mukim = "";
		String id_jenishakmilik = "";
		String id_luaslot = "";
		String id_lot = "";
		String id_kategoritanah = "";
		String id_daerahpenggawa = "";
		String id_unitluasambil = "";
		if(dataMaklumatTanah.size()!=0){
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_mukim = h.get("id_mukim").toString();
			id_jenishakmilik = h.get("id_jenishakmilik").toString();
			id_luaslot = h.get("id_luasLot").toString();
			id_lot = h.get("id_lot").toString();
			id_kategoritanah = h.get("id_kategoritanah").toString();
			id_daerah = h.get("id_daerah").toString();
			id_negeriprojek = h.get("id_negeri").toString();
			id_daerahpenggawa = h.get("id_daerahpenggawa").toString();
			id_unitluasambil = h.get("id_unitluasambil").toString();			
		}
		
		//validation jajahan
		if(id_negeriprojek.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
		String mode = "";
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		//untuk kelantan shj
		context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("socDaerahPenggawa",Utils.parseLong(id_daerahpenggawa),null," "+mode+" style=width:274px"));
		
		//dropdown
		if(id_negeriprojek.equals("10")){
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik "+mode+" style=width:auto onchange=doOnchange()"));   	
		}else{
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik "+mode+" style=width:auto onchange=doOnchange()"));   	
		}
		
		context.put("selectKategoriTanah",HTML.SelectKategoriTanah("socKategoriTanah",Utils.parseLong(id_kategoritanah),"id=socKategoriTanah "+mode+" style=width:auto",null));
		context.put("selectKodLot", HTML.SelectUnitPT("socKodLot",Utils.parseLong(id_lot),"style=width:auto "+mode+" "));
		
		//dropdown unit luas
		context.put("selectUnitLuasLot",HTML.SelectLuas("socUnitLuasLot",Utils.parseLong(id_luaslot),"style=width:250px "+mode+" id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
		context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil",Utils.parseLong(id_unitluasambil),"style=width:250px "+mode+" id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
		
		//dropdown by
		if(id_daerah!=""){
			context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(id_daerah,"socMukim",Utils.parseLong(id_mukim),"style=width:auto "+mode+""));
		}else{
			context.put("selectMukim",HTML.SelectMukimNoKod("socMukim",Utils.parseLong(id_mukim),"style=width:auto "+mode+""));
		}
		
	}//close dataHakmilik
	
	
	
	@SuppressWarnings("unchecked")
	private void simpanHM(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
		
		String flagSubjaket = getParam("flag_subjaket");
		
		h.put("txtLokasi", "");		
		h.put("txtSyaratNyata", "");
		h.put("txtSyaratKhas", "");
		h.put("txtSekatanKepentingan", "");
		h.put("txtSekatanHak", "");
		
		h.put("socDaerahPenggawa", getParam("socDaerahPenggawa"));
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_negeriProjek", getParam("id_negeriprojek"));
		h.put("id_daerahProjek", getParam("id_daerah"));
		h.put("jenisHakMilik", getParam("socJenisHakmilik"));		
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));		
		h.put("txdTarikhLuput", getParam("txdTarikhLuput"));
		h.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));		
		h.put("txtBakiTempoh", getParam("txtBakiTempoh"));		
		h.put("socKategoriTanah", getParam("socKategoriTanah"));
		h.put("socMukim", getParam("socMukim"));
		h.put("txtNoSyit", getParam("txtNoSyit"));
		h.put("kodLot", getParam("socKodLot"));
		h.put("txtNoLot", getParam("txtNoLot"));
		h.put("txtNoPT", getParam("txtNoPT"));
		h.put("txtLuasAsal", Utils.RemoveSymbol(getParam("txtLuasLotAsal")));
		h.put("txtLuasAmbil",  Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));
		h.put("txtCatatan", getParam("txtCatatan"));
		h.put("txtseksyen", getParam("txtSeksyen"));
		
		h.put("unitLuas", getParam("socUnitLuasLot"));
		h.put("unitLuasAmbil", getParam("socUnitLuasAmbil"));		
		h.put("txtLuasLotAsalSebelumConvert", getParam("txtLuasLotAsalSebelumConvert"));
		h.put("txtLuasLotAmbilSebelumConvert", getParam("txtLuasLotAmbilSebelumConvert"));		
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));	
		
		//rizab
    	h.put("sorJenisRizab", getParam("sorJenisRizab"));
    	h.put("txtLain", getParam("txtLain"));
    	h.put("txtNoWartaRizab", getParam("txtNoWartaRizab"));
    	h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		
    	//pengambilan segera
    	h.put("socPSegera", getParam("socPSegera"));
    	
//    	// PPT-03 Strata
    	h.put("txtNoBangunan", getParam("txtNoBangunan"));
    	h.put("txtNoTingkat", getParam("txtNoTingkat"));
    	h.put("txtNoPetak", getParam("txtNoPetak"));
    	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUPTSek8HakmilikData.simpanHM(h,flagSubjaket);
		
		AuditTrail at = new AuditTrail();
		at.logActivity("","1",null,session,"INS","HAKMILIK ["+getParam("txtNoHakmilik")+"] INSERT");
		
		
	}//close simpanHM
	
	@SuppressWarnings("unchecked")
	private void updateHM(HttpSession session,String idHakmilik,String id_projekDaerah) throws Exception{
		
		Hashtable h = new Hashtable();
		
		h.put("txtLokasi", "");		
		h.put("txtSyaratNyata", "");
		h.put("txtSyaratKhas", "");
		h.put("txtSekatanKepentingan", "");
		h.put("txtSekatanHak", "");
		
		h.put("id_hakmilik", idHakmilik);
		h.put("id_daerah", id_projekDaerah);
		
		h.put("socDaerahPenggawa", getParam("socDaerahPenggawa"));	
		h.put("jenisHakMilik", getParam("socJenisHakmilik"));		
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));		
		h.put("txdTarikhLuput", getParam("txdTarikhLuput"));
		h.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));		
		h.put("txtBakiTempoh", getParam("txtBakiTempoh"));		
		h.put("socKategoriTanah", getParam("socKategoriTanah"));
		h.put("socMukim", getParam("socMukim"));
		h.put("txtNoSyit", getParam("txtNoSyit"));
		h.put("kodLot", getParam("socKodLot"));
		h.put("txtNoLot", getParam("txtNoLot"));
		h.put("txtNoPT", getParam("txtNoPT"));
		h.put("txtLuasAsal", Utils.RemoveSymbol(getParam("txtLuasLotAsal")));
		h.put("txtLuasAmbil",  Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));
		h.put("txtCatatan", getParam("txtCatatan"));
		h.put("txtseksyen", getParam("txtSeksyen"));
		
		h.put("unitLuas", getParam("socUnitLuasLot"));
		h.put("unitLuasAmbil", getParam("socUnitLuasAmbil"));		
		h.put("txtLuasLotAsalSebelumConvert", getParam("txtLuasLotAsalSebelumConvert"));
		h.put("txtLuasLotAmbilSebelumConvert", getParam("txtLuasLotAmbilSebelumConvert"));		
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));	
		
		//rizab
    	h.put("sorJenisRizab", getParam("sorJenisRizab"));
    	h.put("txtLain", getParam("txtLain"));
    	h.put("txtNoWartaRizab", getParam("txtNoWartaRizab"));
    	h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
    	
    	// PPT-03 Strata
    	h.put("no_bangunan", getParam("txtNoBangunan"));
    	h.put("no_tingkat", getParam("txtNoTingkat"));
    	h.put("no_petak", getParam("txtNoPetak"));
    	myLogger.info("View updateHM no bangunan: " +getParam("txtNoBangunan"));
		
    	//pengambilan segera
    	h.put("socPSegera", getParam("socPSegera"));
    	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUPTSek8HakmilikData.updateHM(h);
		
	}//close updateHM
	
	private void changeUnitAmbil() throws Exception{
		
		String showButtonConvertAmbil = getParam("showButtonConvertAmbil");
		
		if(showButtonConvertAmbil.equals("yes")){
			context.put("showButtonConvertAmbil","yes");
		}else{
			context.put("showButtonConvertAmbil","no");
		}
		
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
		context.put("showBoxAmbil2","no");
		context.put("showBoxAmbil3","no");
		context.put("showButtonConvertAmbil","no");
		
	}//close changeUnitAmbil
	
	private void changeUnitAsal() throws Exception{
		
		String showButtonConvertAsal = getParam("showButtonConvertAsal");
		
		if(showButtonConvertAsal.equals("yes")){
			context.put("showButtonConvertAsal","yes");
		}else{
			context.put("showButtonConvertAsal","no");
		}
		
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
		context.put("showBoxAsal2","no");
		context.put("showBoxAsal3","no");
		context.put("showButtonConvertAsal","no");
		
	}//close changeUnitAsal
	
	private void clearConvertAsal(String mode) throws Exception{
		
		//luas asal
		context.put("txtLuasLotAsal", "");
		context.put("showFieldAsalBeforeConvert", "no");
		context.put("showDropdownUnitAsal", "no");
		context.put("showButtonConvertAsal","no");
		context.put("showBoxAsal2","no");
		context.put("showBoxAsal3","no");
		
		//dropdown unit luas
		if(mode.equals("new")){
			context.put("selectUnitLuasLot",HTML.SelectLuas("socUnitLuasLot",null,"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
		}else{
			context.put("selectUnitLuasLot",HTML.SelectLuas("socUnitLuasLot",null,"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
		}
		
	}//close clearConvertAsal
	
	private void clearConvertAmbil(String mode) throws Exception{
		
		//luas ambil		
		context.put("txtLuasLotAmbil", "");
		context.put("showFieldAmbilBeforeConvert", "no");
		context.put("showDropdownUnitAmbil", "no");
		context.put("showButtonConvertAmbil","no");
		context.put("showBoxAmbil2","no");
		context.put("showBoxAmbil3","no");
		
		//dropdown unit luas
		if(mode.equals("new")){
			context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil",null,"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
		}else{
			context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil",null,"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
		}
		
	}//close clearConvertAmbil

	private void calculateNilaiAsal() throws Exception{
		
		String id_kategoritanah = getParam("socKategoriTanah");
		
		//data luas asal
		String unitLuasAsal = getParam("socUnitLuasLot");
		String luasAsal1 = Utils.RemoveSymbol(getParam("txtLuasLotAsal"));
		String luasAsal2 = Utils.RemoveSymbol(getParam("txtLuasLotAsal2"));
		String luasAsal3 = Utils.RemoveSymbol(getParam("txtLuasLotAsal3"));
		
		//field validation
		if(unitLuasAsal!=""){
			context.put("showFieldAsalBeforeConvert", "yes");
			context.put("showDropdownUnitAsal", "yes");
		}else{
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}
		
		double total = 0.0000;
		
		String unitSebelumConvert1 = "";
		String unitSebelumConvert2 = "";
		String unitSebelumConvert3 = "";
		String sorDropdownUnitAsal = "";
		
		// 1 = kilometer persegi
		if(unitLuasAsal.equals("1")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAsal1) * 100;
				sorDropdownUnitAsal = "1";
			}else{
				total =  Double.parseDouble(luasAsal1) * 1000000;
				sorDropdownUnitAsal = "2";
			}
			
			unitSebelumConvert1 = "KILOMETER PERSEGI";
			
		}//close kilometer persegi
		
		// 2 = hektar
		if(unitLuasAsal.equals("2")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAsal1) * 1;
				sorDropdownUnitAsal = "1";
			}else{
				total =  Double.parseDouble(luasAsal1) * 10000;
				sorDropdownUnitAsal = "2";
			}
			
			unitSebelumConvert1 = "HEKTAR";
			
		}//close hektar
		
		// 3 = meter persegi
		if(unitLuasAsal.equals("3")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAsal1) * 0.0001;
				sorDropdownUnitAsal = "1";
			}else{
				total =  Double.parseDouble(luasAsal1) * 1;
				sorDropdownUnitAsal = "2";
			}
			
			unitSebelumConvert1 = "METER PERSEGI";
			
		}//close meter persegi
		
		// 4 = ekar/rood/pole
		if(unitLuasAsal.equals("4")){
			if(id_kategoritanah.equals("2")){
				total =  (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2)/4) + (Double.parseDouble(luasAsal3)/160))*0.404686;
				sorDropdownUnitAsal = "1";
			}else{
				total =  (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2)/4) + (Double.parseDouble(luasAsal3)/160))*4046.86;
				sorDropdownUnitAsal = "2";
			}
			
			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "ROOD";
			unitSebelumConvert3 = "POLE";
			
		}//close ekar/rood/pole
		
		// 5 = kaki persegi
		if(unitLuasAsal.equals("5")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAsal1) * 0.000009290304;
				sorDropdownUnitAsal = "1";
			}else{
				total =  Double.parseDouble(luasAsal1) * 0.09290304;
				sorDropdownUnitAsal = "2";
			}
			
			unitSebelumConvert1 = "KAKI PERSEGI";
			
		}//close kaki persegi
		
		// 6 = ekar perpuluhan
		if(unitLuasAsal.equals("6")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAsal1) * 0.0001;
				sorDropdownUnitAsal = "1";
			}else{
				total =  Double.parseDouble(luasAsal1) * 1;
				sorDropdownUnitAsal = "2";
			}
			
			unitSebelumConvert1 = "EKAR PERPULUHAN";
			
		}//close ekar perpuluhan
		
		// 7 = ekar/depa
		if(unitLuasAsal.equals("7")){
			if(id_kategoritanah.equals("2")){ 
				total =  (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2)/1000))*0.404686;
				sorDropdownUnitAsal = "1";
			}else{
				total =  (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2)/1000))*4046.86;
				sorDropdownUnitAsal = "2";
			}
			
			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "DEPA";
			
		}//close ekar/depa
		
		
		// 8 = relong/jemba/kaki persegi
		if(unitLuasAsal.equals("8")){
			if(id_kategoritanah.equals("2")){
				total =  (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2)/484) + (Double.parseDouble(luasAsal3)/30976))*0.711111*0.404686;
				sorDropdownUnitAsal = "1";
			}else{
				total =  (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2)/484) + (Double.parseDouble(luasAsal3)/30976))*0.711111*4046.86;
				sorDropdownUnitAsal = "2";
			}
			
			unitSebelumConvert1 = "RELONG";
			unitSebelumConvert2 = "JEMBA";
			unitSebelumConvert3 = "KAKI PERSEGI";
			
		}//close relong/jemba/kaki persegi
		
		// 9 = batu nautika
		if(unitLuasAsal.equals("9")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAsal1) * 0.0001;
				sorDropdownUnitAsal = "1";
			}else{
				total =  Double.parseDouble(luasAsal1) * 1;
				sorDropdownUnitAsal = "2";
			}
			
			unitSebelumConvert1 = "BATU NAUTIKA";
			
		}//close batu nautika
		
		
		//put data luas asal
		context.put("txtLuasLotAsal", Utils.formatLuasHM(total));
		context.put("sorDropdownUnitAsal", sorDropdownUnitAsal);
		context.put("txtLuasLotAsalSebelumConvert", luasAsal1+" "+unitSebelumConvert1+" "+luasAsal2+" "+unitSebelumConvert2+" "+luasAsal3+" "+unitSebelumConvert3);
		context.put("showBoxAsal2","no");
		context.put("showBoxAsal3","no");
		context.put("showButtonConvertAsal","no");
		
	}//close calculateNilaiAsal
	
	
	private void calculateNilaiAmbil() throws Exception{
		
		String id_kategoritanah = getParam("socKategoriTanah");
		
		//data luas ambil
		String unitLuasAmbil = getParam("socUnitLuasAmbil");
		String luasAmbil1 = Utils.RemoveSymbol(getParam("txtLuasLotAmbil"));
		String luasAmbil2 = getParam("txtLuasLotAmbil2");
		String luasAmbil3 = getParam("txtLuasLotAmbil3");
		
		
		//field validation
		if(unitLuasAmbil!=""){
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		}else{
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}
		
		double total = 0.0000;
		
		String unitSebelumConvert1 = "";
		String unitSebelumConvert2 = "";
		String unitSebelumConvert3 = "";
		String sorDropdownUnitAmbil = "";
		
		// 1 = kilometer persegi
		if(unitLuasAmbil.equals("1")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAmbil1) * 100;
				sorDropdownUnitAmbil = "1";
			}else{
				total =  Double.parseDouble(luasAmbil1) * 1000000;
				sorDropdownUnitAmbil = "2";
			}
			
			unitSebelumConvert1 = "KILOMETER PERSEGI";
			
		}//close kilometer persegi
		
		// 2 = hektar
		if(unitLuasAmbil.equals("2")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "1";
			}else{
				total =  Double.parseDouble(luasAmbil1) * 10000;
				sorDropdownUnitAmbil = "2";
			}
			
			unitSebelumConvert1 = "HEKTAR";
			
		}//close hektar
		
		// 3 = meter persegi
		if(unitLuasAmbil.equals("3")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAmbil1) * 0.0001;
				sorDropdownUnitAmbil = "1";
			}else{
				total =  Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "2";
			}
			
			unitSebelumConvert1 = "METER PERSEGI";
			
		}//close meter persegi
		
		// 4 = ekar/rood/pole
		if(unitLuasAmbil.equals("4")){
			if(id_kategoritanah.equals("2")){
				total =  (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2)/4) + (Double.parseDouble(luasAmbil3)/160))*0.404686;
				sorDropdownUnitAmbil = "1";
			}else{
				total =  (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2)/4) + (Double.parseDouble(luasAmbil3)/160))*4046.86;
				sorDropdownUnitAmbil = "2";
			}
			
			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "ROOD";
			unitSebelumConvert3 = "POLE";
			
		}//close ekar/rood/pole
		
		// 5 = kaki persegi
		if(unitLuasAmbil.equals("5")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAmbil1) * 0.000009290304;
				sorDropdownUnitAmbil = "1";
			}else{
				total =  Double.parseDouble(luasAmbil1) * 0.09290304;
				sorDropdownUnitAmbil = "2";
			}
			
			unitSebelumConvert1 = "KAKI PERSEGI";
			
		}//close kaki persegi
		
		// 6 = ekar perpuluhan
		if(unitLuasAmbil.equals("6")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAmbil1) * 0.0001;
				sorDropdownUnitAmbil = "1";
			}else{
				total =  Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "2";
			}
			
			unitSebelumConvert1 = "EKAR PERPULUHAN";
			
		}//close ekar perpuluhan
		
		// 7 = ekar/depa
		if(unitLuasAmbil.equals("7")){
			if(id_kategoritanah.equals("2")){ 
				total =  (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2)/1000))*0.404686;
				sorDropdownUnitAmbil = "1";
			}else{
				total =  (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2)/1000))*4046.86;
				sorDropdownUnitAmbil = "2";
			}
			
			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "DEPA";
			
		}//close ekar/depa
		
		
		// 8 = relong/jemba/kaki persegi
		if(unitLuasAmbil.equals("8")){
			if(id_kategoritanah.equals("2")){
				total =  (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2)/484) + (Double.parseDouble(luasAmbil3)/30976))*0.711111*0.404686;
				sorDropdownUnitAmbil = "1";
			}else{
				total =  (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2)/484) + (Double.parseDouble(luasAmbil3)/30976))*0.711111*4046.86;
				sorDropdownUnitAmbil = "2";
			}
			
			unitSebelumConvert1 = "RELONG";
			unitSebelumConvert2 = "JEMBA";
			unitSebelumConvert3 = "KAKI PERSEGI";
			
		}//close relong/jemba/kaki persegi
		
		// 9 = batu nautika
		if(unitLuasAmbil.equals("9")){
			if(id_kategoritanah.equals("2")){
				total =  Double.parseDouble(luasAmbil1) * 0.0001;
				sorDropdownUnitAmbil = "1";
			}else{
				total =  Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "2";
			}
			
			unitSebelumConvert1 = "BATU NAUTIKA";
			
		}//close batu nautika
		
		//put data luas ambil
		context.put("txtLuasLotAmbil", Utils.formatLuasHM(total));
		context.put("sorDropdownUnitAmbil", sorDropdownUnitAmbil);
		context.put("txtLuasLotAmbilSebelumConvert", luasAmbil1+" "+unitSebelumConvert1+" "+luasAmbil2+" "+unitSebelumConvert2+" "+luasAmbil3+" "+unitSebelumConvert3);
		context.put("showBoxAmbil2","no");
		context.put("showBoxAmbil3","no");
		context.put("showButtonConvertAmbil","no");
		
	}//close calculateNilaiAmbil

	@SuppressWarnings("unchecked")
	private void getAndSetHM(String idpermohonan,String mode,String id_projekNegeri) throws Exception{
	    
		Vector dataPermohonan = new Vector();
		dataPermohonan.clear();
		
		if(mode.equals("new")){
			
			if(id_projekNegeri.equals("10")){
				context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")),"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));   	
			}else{
				context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")),"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));   	
			}
			
			//dropdown unit luas
			context.put("selectUnitLuasLot",HTML.SelectLuas("socUnitLuasLot",Utils.parseLong(getParam("socUnitLuasLot")),"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
			context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil",Utils.parseLong(getParam("socUnitLuasAmbil")),"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
		}else{
			
			if(id_projekNegeri.equals("10")){
				context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")),"id=socJenisHakmilik style=width:auto onchange=doOnchangeUpdate()"));   	
			}else{
				context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")),"id=socJenisHakmilik style=width:auto onchange=doOnchangeUpdate()"));   	
			}
			
			//dropdown unit luas
			context.put("selectUnitLuasLot",HTML.SelectLuas("socUnitLuasLot",Utils.parseLong(getParam("socUnitLuasLot")),"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
			context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil",Utils.parseLong(getParam("socUnitLuasAmbil")),"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
		}
		
		String id_daerah = "";		
		model.setListPohon(idpermohonan);
 		dataPermohonan = model.getListPohon();
		if(dataPermohonan.size()!=0){
			Hashtable dp = (Hashtable)dataPermohonan.get(0);
			id_daerah = dp.get("idDaerah").toString();
		}
		
		//untuk kelantan shj
		context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("socDaerahPenggawa",Utils.parseLong(getParam("socDaerahPenggawa")),null,"style=width:274px"));
		
		//dropdown		
		context.put("selectKategoriTanah",HTML.SelectKategoriTanah("socKategoriTanah",Utils.parseLong(getParam("socKategoriTanah")),"id=socKategoriTanah style=width:auto",null));
		context.put("selectKodLot", HTML.SelectUnitPT("socKodLot",Utils.parseLong(getParam("socKodLot")),"style=width:auto"));
		
		//dropdown by
		if(id_daerah!=""){
			context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(id_daerah,"socMukim",Utils.parseLong(getParam("socMukim")),"style=width:auto"));
		}else{
			context.put("selectMukim",HTML.SelectMukimNoKod("socMukim",Utils.parseLong(getParam("socMukim")),"style=width:auto"));
		}
		
		//data
		context.put("txtSeksyen", getParam("txtSeksyen"));
		if(getParam("socJenisHakmilik").equals("116")){
			context.put("txtNoHakmilik", "RH");
		}else{
			context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		}
		
		
		String resetRadio = getParam("resetRadio");
		
		//Pengambilan segera sebahagian
		context.put("socPSegera", getParam("socPSegera"));
		
		context.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
		context.put("txdTarikhLuput", getParam("txdTarikhLuput"));
		context.put("txtBakiTempoh", getParam("txtBakiTempoh"));
		context.put("txtNoSyit", getParam("txtNoSyit"));
		context.put("txtNoPT", getParam("txtNoPT"));
		context.put("txtNoLot", getParam("txtNoLot"));
		context.put("txtLuasLotAsal", getParam("txtLuasLotAsal"));
		context.put("txtLuasLotAsal2", getParam("txtLuasLotAsal2"));
		context.put("txtLuasLotAsal3", getParam("txtLuasLotAsal3"));
		context.put("txtLuasLotAmbil", getParam("txtLuasLotAmbil"));
		context.put("txtLuasLotAmbil", getParam("txtLuasLotAmbil"));
		context.put("txtLuasLotAmbil2", getParam("txtLuasLotAmbil2"));
		context.put("txtLuasLotAmbil3", getParam("txtLuasLotAmbil3"));
		
		//penambahan v7-yati
		context.put("txtNoBangunan", getParam("txtNoBangunan"));
		context.put("txtNoTingkat", getParam("txtNoTingkat"));
		context.put("txtNoPetak", getParam("txtNoPetak"));
		
		if(resetRadio.equals("1")){
			context.put("sorJenisRizab", "");
		}else{
			context.put("sorJenisRizab", getParam("sorJenisRizab"));
		}
		
		context.put("txtLain", getParam("txtLain"));
		context.put("txtNoWartaRizab", getParam("txtNoWartaRizab"));
		context.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		context.put("txtCatatan", getParam("txtCatatan"));
		
		context.put("txtLuasLotAsalSebelumConvert", getParam("txtLuasLotAsalSebelumConvert"));
		context.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		
		context.put("txtLuasLotAmbilSebelumConvert", getParam("txtLuasLotAmbilSebelumConvert"));
		context.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));
		
	}//close getAndSetHM
	
	private void clearValueHM() throws Exception{
	    
		context.put("socPSegera", "");
		
		context.put("txtSeksyen", "");
		context.put("txtNoHakmilik", "");
		context.put("txdTarikhDaftar", "");
		context.put("txdTarikhLuput", "");
		context.put("txtBakiTempoh", "");
		context.put("txtNoSyit", "");
		context.put("txtNoPT", "");
		context.put("txtNoLot", "");
		context.put("txtLuasLotAsal", "");
		context.put("txtLuasLotAsal2", "");
		context.put("txtLuasLotAsal3", "");
		context.put("txtLuasLotAmbil", "");
		context.put("txtLuasLotAmbil2", "");
		context.put("txtLuasLotAmbil3", "");
		context.put("sorJenisRizab", "");
		context.put("txtLain", "");
		context.put("txtNoWartaRizab", "");
		context.put("txdTarikhWarta", "");
		context.put("txtCatatan", "");
		
		context.put("txtLuasLotAsalSebelumConvert", "");
		context.put("sorDropdownUnitAsal", "");
		
		context.put("txtLuasLotAmbilSebelumConvert", "");
		context.put("sorDropdownUnitAmbil", "");
		
		//penambahan v7 -yati
		context.put("txtNoBangunan", "");
		context.put("txtNoTingkat", "");
		context.put("txtNoPetak", "");
	}//close clearValueHM
	
	@SuppressWarnings("unchecked")
	private void newDataSetting(String idpermohonan) throws Exception{
	    
		Vector dataPermohonan = new Vector();
		dataPermohonan.clear();
		
		String id_daerah = "";
		String id_negeriprojek = "";
		String nama_daerah = "";
		String nama_negeriprojek = "";
		
		model.setListPohon(idpermohonan);
 		dataPermohonan = model.getListPohon();
		if(dataPermohonan.size()!=0){
			Hashtable dp = (Hashtable)dataPermohonan.get(0);
			id_daerah = dp.get("idDaerah").toString();
			id_negeriprojek = dp.get("idProjekNegeri").toString();
			nama_daerah = dp.get("daerah").toString();
			nama_negeriprojek = dp.get("nama_negeriprojek").toString();	
		}
		
		context.put("id_daerah", id_daerah);
		context.put("id_negeriprojek", id_negeriprojek);
		context.put("nama_daerah", nama_daerah);
		context.put("nama_negeriprojek", nama_negeriprojek);
		
		//validation jajahan
		if(id_negeriprojek.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
		//untuk kelantan shj
		context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("socDaerahPenggawa",null,null,"style=width:274px"));
		
		//dropdown
		if(id_negeriprojek.equals("10")){
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",null,"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));   	
		}else{
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",null,"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));   	
		}
		
		context.put("selectKategoriTanah",HTML.SelectKategoriTanah("socKategoriTanah",null,"id=socKategoriTanah style=width:auto",null));
		context.put("selectKodLot", HTML.SelectUnitPT("socKodLot",null,"style=width:auto"));
		
		//dropdown unit luas
		context.put("selectUnitLuasLot",HTML.SelectLuas("socUnitLuasLot",null,"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
		context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil",null,"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
		
		//dropdown by
		if(id_daerah!=""){
			context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(id_daerah,"socMukim",null,"style=width:auto"));
		}else{
			context.put("selectMukim",HTML.SelectMukimNoKod("socMukim",null,"style=width:auto"));
		}
		
	}//close newDataSetting
	
	@SuppressWarnings("unchecked")
	private void hapusDokumen(HttpSession session) throws Exception{
	    
		Hashtable h = new Hashtable();		
		h.put("id_dokumen", getParam("id_dokumen"));		
		FrmPermohonanUPTData.hapusDokumen(h);
		
	}//close hapusdokumen
	
	
	@SuppressWarnings("unchecked")
	private void uploadFiles() throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData(item);
		      }
		    }
		  }
	 private void saveData(FileItem item) throws Exception {
	  		Db db = null;
	        try {
	        	db = new Db();
	        	long id_Dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN " +
	        			"(id_Dokumen,id_permohonan,nama_Fail,jenis_Mime,content,tajuk,keterangan) " +
	        			"values(?,?,?,?,?,?,?)");
	        	ps.setLong(1, id_Dokumen);
	        	ps.setString(2, getParam("id_permohonan"));
	        	ps.setString(3,item.getName());
	        	ps.setString(4,item.getContentType());
	        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
	        	ps.setString(6, getParam("nama_dokumen"));
	        	ps.setString(7, getParam("keterangan"));
	        	ps.executeUpdate();
	            con.commit();
	            
		    }catch (SQLException se) { 
		    	throw new Exception("Ralat : Masalah muatnaik fail");
		    }finally {
			      if (db != null) db.close();
		    }
	  }
	 
	@SuppressWarnings({"unchecked", "static-access"})
	private String dataHeader(String idpermohonan) throws Exception{
    	
		String id_status = "";
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
		}
 		
		return id_status;
		
	}//close dataHeader
	
	@SuppressWarnings({"unchecked"})
	private void ListHakmilik(String idpermohonan,String noLOT,String idpegawai) throws Exception{
    /*	
		Vector listHakmilik = new Vector();
		listHakmilik.clear();
		
		
		String nama2Mukim = "";
		model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai,"0","50");
 		listHakmilik = model.getListMaklumatTanah();
 		if(listHakmilik.size()!=0){
 			Hashtable lmt = (Hashtable)listHakmilik.get(0);
 			nama2Mukim = (String)lmt.get("nama2Mukim"); 			
 			
 		}
 		
 		
 		context.put("listMaklumatTanah", listHakmilik);
 		context.put("saiz_listTanah", listHakmilik.size());
 		context.put("nama2Mukim", nama2Mukim);
 		*/
		int input_to = 50;
		int input_from = 0;
		int setLimit = 750;
		String nama2Mukim = "";
		Hashtable senarai_mukim_lot = model.getListMukimLot(idpermohonan, noLOT, idpegawai, input_from,input_to);
		if (senarai_mukim_lot.size() != 0) {
			nama2Mukim = senarai_mukim_lot.get("nama2Mukim").toString();
		}
		//context.put("listMaklumatTanah", listHakmilik);
		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
		context.put("nama2Mukim", nama2Mukim);
	}//close ListHakmilik
	
	
		
	@SuppressWarnings({"unchecked", "static-access"})
	private void ListDokumen(String idpermohonan) throws Exception{    	
	/*	Vector listDokumen = new Vector();
		listDokumen.clear();		
		model.setListDokumen(idpermohonan);
 		listDokumen = model.getListDokumen();
		context.put("listDokumen", listDokumen);
		context.put("listD_size", listDokumen.size());	*/	
	}//close ListDokumen
	
	@SuppressWarnings({"unchecked"})
	private void DataPermohonan(String idpermohonan,String disability) throws Exception{
    	
		Vector dataPermohonan = new Vector();
		dataPermohonan.clear();
		
		String id_agensi = "";
		String id_negeri = "";
		String id_kementerian = "";
		String id_daerah = ""; 		
		String id_negeriprojek = "";
		String flag_jenis_kod_daerah = "";
		model.setListPohon(idpermohonan);
 		dataPermohonan = model.getListPohon();
 		
 		//data
 		context.put("dataPermohonan",dataPermohonan);
 		
		if(dataPermohonan.size()!=0){
			Hashtable dp = (Hashtable)dataPermohonan.get(0);
			id_agensi = dp.get("idAgensi").toString();
			id_negeri = dp.get("idNegeri").toString();
			id_kementerian = dp.get("idKementerian").toString();
			id_daerah = dp.get("idDaerah").toString();
			id_negeriprojek = dp.get("idProjekNegeri").toString();
			flag_jenis_kod_daerah = (String)dp.get("flag_jenis_kod_daerah");
			negeriIntegrasi = dp.get("idProjekNegeri").toString();
		}
		
		String mode = "";
		if(disability.equals("enabled")){
			mode = "";
		}else{
			mode = "disabled class=disabled";
		}
		
		//dropdown by
		if(id_kementerian!=""){
			context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Utils.parseLong(id_agensi),"id=socAgensi "+mode+" style=width:500px"));
		}else{
			context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Utils.parseLong(id_agensi),"id=socAgensi "+mode+" style=width:500px"));
		}
				
		if(id_negeriprojek!=""){
			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(flag_jenis_kod_daerah,id_negeriprojek,"socDaerah",Utils.parseLong(id_daerah),null,"id=socDaerah "+mode+" style=width:300px"));
		}else{
			context.put("selectDaerah",HTML.SelectDaerahKOD(flag_jenis_kod_daerah,"socDaerah",Utils.parseLong(id_daerah),null,"id=socDaerah "+mode+" style=width:300px"));
		}
		
		//dropdown
		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(id_kementerian),"","style=width:500px "+mode+" onChange=\"doChangeKementerianUpdate();\""));
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),"class=disabled disabled style=width:325px"));
		
 
		if(id_negeriprojek.equals("14") || id_negeriprojek.equals("15") || id_negeriprojek.equals("16")){
			context.put("selectNegeriProjek",HTML.SelectNegeriMampuKL("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px "+mode+" onChange=\"doChangeProjekNegeriUpdate();\""));
		}else{
			context.put("selectNegeriProjek",HTML.SelectNegeriMampu("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px class=disabled disabled onChange=\"doChangeProjekNegeriUpdate();\""));
		}
		
		//validation jajahan
		if(id_negeriprojek.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
 		
	}//close DataPermohonan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void ListCheckBox(String idpermohonan) throws Exception{
    	
		Vector senaraiSemak = new Vector();
		senaraiSemak.clear();
		
		String id_senaraisemak = "";
		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		if(senaraiSemak.size()!=0){
			Hashtable ss = (Hashtable)senaraiSemak.get(0);
			id_senaraisemak = (String)ss.get("id_senaraisemak");
		}
			
		//data and id
		context.put("id_senaraisemak", id_senaraisemak);
		context.put("senaraiSemakan", senaraiSemak);
		
	}//close ListCheckBox
	
	@SuppressWarnings("unchecked")
	private String simpanPendaftaran(HttpSession session,String userIdNeg) throws Exception{
		
	    	Hashtable h = new Hashtable();
	    
	    	h.put("suburusan", "52");	    	
	    	h.put("no_rujukan_ptg", getParam("txtNoRujukanPTG"));
	    	h.put("no_rujukan_ptd", getParam("txtNoRujukanPTD"));	
	    	h.put("no_rujukan_upt", getParam("txtNoRujukanUPT"));
		    h.put("kementerian", getParam("socKementerian"));
		    h.put("agensi",getParam("socAgensi"));
		    h.put("flag_peruntukan", getParam("sorFlagPeruntukan"));
		    h.put("flag_segera", getParam("sorFlagSegera"));		    
		    h.put("daerah", getParam("socDaerah"));
		    h.put("tujuan", getParam("txtTujuan"));
		    h.put("rujukan_kementerian", getParam("txtRujukanKementerian"));
		    h.put("tarikh_kehendaki", getParam("txdTarikhKehendaki"));
		    h.put("tarikh_surat", getParam("txdTarikhSurat"));
		    h.put("tarikh_lengkap", getParam("txdPermohonanLengkap"));
	    	
		    //untuk n9 shj [27042011]
		    h.put("txtTujuanBI", getParam("txtTujuanBI"));
		    
	    	String idNegeri = userIdNeg;
		    if(userIdNeg.equals("14")){
		    	idNegeri = getParam("socNegeriProjek");
		    }
		    h.put("projek_negeri", idNegeri);
		    
	    	h.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
	    	
	    	h.put("tarikh_lengkap", getParam("txdPermohonanLengkap"));
	    	
	    	h.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));
	    	
	    	h.put("jenis_projek", getParam("sorJenisProjek"));	
	    	
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	
	    	h.put("txdTarikhPermohonanKjp", getParam("txdTarikhPermohonanKjp"));
	    	
	    	h.put("txdTarikhPengesahan", getParam("txdTarikhPengesahan"));
	    	
	    	return FrmPermohonanUPTData.addSPT(h);
	  
	}//close simpanPendaftaran
	
	@SuppressWarnings("unchecked")
	private void updatePendaftaran(HttpSession session,String userIdNeg) throws Exception{
		//myLogger.info("update pendaftaran");
	    	Hashtable h = new Hashtable();
	        	
	    	String id_negeriprojek = userIdNeg;
		    if(userIdNeg.equals("14")){
		    	id_negeriprojek = getParam("socNegeriProjek");
		    }
	    	
	    	h.put("id_senaraisemak", getParam("id_senaraisemak"));	    
	    	h.put("id_urusan", "52");
	    	h.put("id_permohonan", getParam("id_permohonan"));
	    	h.put("id_fail", getParam("id_fail"));

	    	//checkbutton seksyen 4
	    	h.put("semak10", "");
	    	h.put("semak20", "");
	  
	    	//checkbutton seksyen 8
	    	if(getParam("cbsemaks1")==null){h.put("semak1", "0");}else{h.put("semak1", getParam("cbsemaks1"));}
	    	if(getParam("cbsemaks2")==null){h.put("semak2", "0");}else{h.put("semak2", getParam("cbsemaks2"));}
	    	if(getParam("cbsemaks3")==null){h.put("semak3", "0");}else{h.put("semak3", getParam("cbsemaks3"));}
	    	if(getParam("cbsemaks4")==null){h.put("semak4", "0");}else{h.put("semak4", getParam("cbsemaks4"));}
	    	if(getParam("cbsemaks5")==null){h.put("semak5", "0");}else{h.put("semak5", getParam("cbsemaks5"));}
	    	if(getParam("cbsemaks6")==null){h.put("semak6", "0");}else{h.put("semak6", getParam("cbsemaks6"));}
	    	if(getParam("cbsemaks7")==null){h.put("semak7", "0");}else{h.put("semak7", getParam("cbsemaks7"));}
	    	if(getParam("cbsemaks8")==null){h.put("semak8", "0");}else{h.put("semak8", getParam("cbsemaks8"));} //penambahan yati
 
	    	h.put("txdTarikhPermohonanKjp", getParam("txdTarikhPermohonanKjp"));
	    	h.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
	    	h.put("tujuan", getParam("txtTujuan"));
	    	h.put("rujukan_kementerian", getParam("txtRujukanKementerian"));
	    	h.put("tarikh_hendak", getParam("txdTarikhKehendaki"));
	    	h.put("tarikh_surat", getParam("txdTarikhSurat"));
	    	h.put("flag_peruntukan", getParam("sorFlagPeruntukan"));
	    	h.put("flag_segera", getParam("sorFlagSegera"));
	    	h.put("daerah", getParam("socDaerah"));
	    	h.put("projeknegeri", id_negeriprojek);
	    	h.put("agensi", getParam("socAgensi"));
	    	h.put("kementerian", getParam("socKementerian"));
	    	h.put("tarikh_lengkap", getParam("txdPermohonanLengkap"));
	    	h.put("tarikh_sahkan", getParam("txdTarikhPengesahan"));
	    	
	    	h.put("no_rujukan_ptg", getParam("txtNoRujukanPTG"));
	    	h.put("no_rujukan_ptd", getParam("txtNoRujukanPTD"));
	    	h.put("no_rujukan_upt", getParam("txtNoRujukanUPT"));
	    	
	    	h.put("jenis_projek", getParam("sorJenisProjek"));	
	    	
	    	h.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));
	    	h.put("jumlah_hakmilik", getParam("jumlahHakmilik"));
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	
	    	 //untuk n9 shj [27042011]
		    h.put("txtTujuanBI", getParam("txtTujuanBI"));
	    	
	    	//validation jajahan
    		if(id_negeriprojek.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		
	    	FrmPermohonanUPTData.update(h); 
	    	
	}//close updatePendaftaran
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetDataOnchangeNegeri(String userIdNeg,String mode) throws Exception{
    	
		String id_kementerian = getParam("socKementerian");
		
		String id_negeriprojek = "";
		if(userIdNeg.equals("14")){
			id_negeriprojek = getParam("socNegeriProjek");
		}else{
			id_negeriprojek = userIdNeg;
		}
		
		
		if(mode.equals("new")){
			
			context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(id_kementerian),null,"style=width:500px onChange=\"doChangeKementerian();\" "));
			//20042011
			if(userIdNeg.equals("14") || userIdNeg.equals("15") || userIdNeg.equals("16")){
				context.put("selectNegeriProjek",HTML.SelectNegeriMampuKL("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px onChange=\"doChangeProjekNegeri();\" "));
			}else{
				context.put("selectNegeriProjek",HTML.SelectNegeriMampu("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px class=disabled disabled onChange=\"doChangeProjekNegeri();\" "));
			}
			
		}else{
			context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(id_kementerian),null,"style=width:500px onChange=\"doChangeKementerianUpdate();\" "));
			
			//20042011
			if(userIdNeg.equals("14") || userIdNeg.equals("15") || userIdNeg.equals("16")){
				context.put("selectNegeriProjek",HTML.SelectNegeriMampuKL("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px onChange=\"doChangeProjekNegeriUpdate();\" "));
			}else{
				context.put("selectNegeriProjek",HTML.SelectNegeriMampu("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px class=disabled disabled onChange=\"doChangeProjekNegeriUpdate();\" "));
			}
			
		}

			
		Vector dataKementerian = model.getAlamatKementerian(id_kementerian);    		
		String id_negeri = "";
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";   		
		if(dataKementerian.size()!=0){
			Hashtable AK = (Hashtable) dataKementerian.get(0);
			id_negeri = AK.get("id_negeri").toString();
			alamat1 = AK.get("alamat1").toString();
			alamat2 = AK.get("alamat2").toString();
			alamat3 = AK.get("alamat3").toString();
			poskod = AK.get("poskod").toString();
		}
		
		context.put("txtPoskod", poskod);
		context.put("txtAlamat1", alamat1);
		context.put("txtAlamat2", alamat2);
		context.put("txtAlamat3", alamat3);
		
		context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
		context.put("txtNoRujukanPTG", getParam("txtNoRujukanPTG"));
		context.put("txtNoRujukanPTD", getParam("txtNoRujukanPTD"));
		context.put("txtNoRujukanUPT", getParam("txtNoRujukanUPT"));
		context.put("txtTujuan", getParam("txtTujuan"));
		context.put("txtRujukanKementerian", getParam("txtRujukanKementerian"));
		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		context.put("txdTarikhKehendaki", getParam("txdTarikhKehendaki"));
		context.put("sorFlagPeruntukan", getParam("sorFlagPeruntukan"));
		context.put("sorFlagSegera", getParam("sorFlagSegera"));
		context.put("sorJenisProjek", getParam("sorJenisProjek"));
		context.put("txdPermohonanLengkap", getParam("txdPermohonanLengkap"));
		//System.out.println("PERMOHONAN LENGKAP"+txdPermohonanLengkap);
		
		 //untuk n9 shj [27042011]
	    context.put("txtTujuanBI", getParam("txtTujuanBI"));
		
		String sorJenisKodDaerah = getParam("sorJenisKodDaerah");
		context.put("sorJenisKodDaerah", sorJenisKodDaerah);
		
		String id_daerah = getParam("socDaerah");
		String id_agensi = getParam("socAgensi");
		
		//dropdown by
		if(id_kementerian!=""){
			context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,Utils.parseLong(id_agensi),"id=socAgensi style=width:500px"));
		}else{
			context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Utils.parseLong(id_agensi),"id=socAgensi style=width:500px"));	
		}
		if(id_negeriprojek!=""){
			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,id_negeriprojek,"socDaerah",Utils.parseLong(id_daerah),null,"id=socDaerah style=width:300px"));
    	}else{
    		context.put("selectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"socDaerah",Utils.parseLong(id_daerah),null,"id=socDaerah style=width:300px"));
        }
		
		//dropdown
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),"class=disabled disabled style=width:325px"));
		
		
		//validation jajahan
		if(id_negeriprojek.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
	}//close getAndSetDataKementerian
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetDataOnchangeKementerian(String id_kementerian,String mode,String userIdNeg) throws Exception{
    	
		
		String id_negeriprojek = "";
		if(userIdNeg.equals("14")){
			id_negeriprojek = getParam("socNegeriProjek");
		}else{
			id_negeriprojek = userIdNeg;
		}

		
		if(mode.equals("new")){
			
			context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(id_kementerian),null,"style=width:500px onChange=\"doChangeKementerian();\" "));
			//20042011
			if(userIdNeg.equals("14") || userIdNeg.equals("15") || userIdNeg.equals("16")){
				context.put("selectNegeriProjek",HTML.SelectNegeriMampuKL("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px onChange=\"doChangeProjekNegeri();\" "));
			}else{
				context.put("selectNegeriProjek",HTML.SelectNegeriMampu("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px class=disabled disabled onChange=\"doChangeProjekNegeri();\" "));
			}
			
		}else{
			
			context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(id_kementerian),null,"style=width:500px onChange=\"doChangeKementerianUpdate();\" "));
			
			//20042011
			if(userIdNeg.equals("14") || userIdNeg.equals("15") || userIdNeg.equals("16")){
				context.put("selectNegeriProjek",HTML.SelectNegeriMampuKL("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px onChange=\"doChangeProjekNegeriUpdate();\" "));
			}else{
				context.put("selectNegeriProjek",HTML.SelectNegeriMampu("socNegeriProjek",Utils.parseLong(id_negeriprojek),null,"style=width:300px class=disabled disabled onChange=\"doChangeProjekNegeriUpdate();\" "));
			}
			
		}
		
		
		Vector dataKementerian = model.getAlamatKementerian(id_kementerian);    		
		String id_negeri = "";
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";   		
		if(dataKementerian.size()!=0){
			Hashtable AK = (Hashtable) dataKementerian.get(0);
			id_negeri = AK.get("id_negeri").toString();
			alamat1 = AK.get("alamat1").toString();
			alamat2 = AK.get("alamat2").toString();
			alamat3 = AK.get("alamat3").toString();
			poskod = AK.get("poskod").toString();
		}
		
		context.put("txtPoskod", poskod);
		context.put("txtAlamat1", alamat1);
		context.put("txtAlamat2", alamat2);
		context.put("txtAlamat3", alamat3);
		
		context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
		context.put("txtNoRujukanPTG", getParam("txtNoRujukanPTG"));
		context.put("txtNoRujukanPTD", getParam("txtNoRujukanPTD"));
		context.put("txtNoRujukanUPT", getParam("txtNoRujukanUPT"));
		context.put("txtTujuan", getParam("txtTujuan"));
		context.put("txtRujukanKementerian", getParam("txtRujukanKementerian"));
		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		context.put("txdTarikhKehendaki", getParam("txdTarikhKehendaki"));
		context.put("sorFlagPeruntukan", getParam("sorFlagPeruntukan"));
		context.put("sorFlagSegera", getParam("sorFlagSegera"));
		context.put("sorJenisProjek", getParam("sorJenisProjek"));
		context.put("txdPermohonanLengkap", getParam("txdPermohonanLengkap"));
		
		
		 //untuk n9 shj [27042011]
	    context.put("txtTujuanBI", getParam("txtTujuanBI"));
	    
		String sorJenisKodDaerah = getParam("sorJenisKodDaerah");
		context.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));
		
		String id_daerah = getParam("socDaerah");
		
		//dropdown by
		if(id_kementerian!=""){
			context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi",id_kementerian,null,"id=socAgensi style=width:500px"));
		}else{
			context.put("selectAgensi",HTML.SelectAgensi("socAgensi",null,"id=socAgensi style=width:500px"));	
		}
		if(id_negeriprojek!=""){
			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,id_negeriprojek,"socDaerah",Utils.parseLong(id_daerah),null,"style=width:300px"));
    	}else{
    		context.put("selectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"socDaerah",Utils.parseLong(id_daerah),null,"style=width:300px"));
        }
		
		//dropdown
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),"class=disabled disabled style=width:325px"));
		
		//validation jajahan
		if(id_negeriprojek.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
	}//close getAndSetDataKementerian
	
	private void resetValue() throws Exception{
    	
		context.put("txtNoRujukanPTG", "");
		context.put("txtNoRujukanPTD", "");
		context.put("txtNoRujukanUPT", "");
		context.put("txtAlamat1", "");
		context.put("txtAlamat2", "");
		context.put("txtAlamat3", "");
		context.put("txtPoskod", "");
		context.put("txtTujuan", "");
		context.put("txtRujukanKementerian", "");
		context.put("txdTarikhSurat", "");
		context.put("txdTarikhKehendaki", "");
		context.put("sorFlagPeruntukan", "");
		context.put("sorFlagSegera", "");
		context.put("sorJenisProjek", "");
		context.put("txdPermohonanLengkap","");
		
		context.put("sorJenisKodDaerah", "");
		
		//utk n9 shj [27042011]
		context.put("txtTujuanBI", "");
		
	}//close resetValue
	
	/*@SuppressWarnings({ "unchecked", "static-access" })
	private String nameAndId(String userIdNeg) throws Exception{
		
		Vector dataNamaPengarah = new Vector();
		
		dataNamaPengarah.clear();
		
	    //GET NAMA PENGARAH DAN ID PENGARAH
	    String nama_pengarah = "";
	    String id_pengarah = "";
	    
	   model.setNamaPengarah(userIdNeg);
	   // model.setListPengarah(id_fail);
	    dataNamaPengarah = model.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = (String)np.get("nama_pengarah");
	    	id_pengarah = (String)np.get("user_id");
	    }
	    
	    context.put("nama_pengarah",nama_pengarah);

	    return id_pengarah;
	    
	}//close nameAndId
	*/
	//penambahan yati
	@SuppressWarnings({ "unchecked", "static-access" })
	private String nameAndIdPengarah(String id_fail) throws Exception{
		
		Vector dataNamaPengarah = new Vector();
		
		dataNamaPengarah.clear();
		
	    //GET NAMA PENGARAH DAN ID PENGARAH
	    String nama_pengarah = "";
	    String id_pengarah = "";
	    
	    dataNamaPengarah =  model.setListPengarah(id_fail);
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = (String)np.get("nama_pengarah");
	    	id_pengarah = (String)np.get("user_id");
	    }
	    
	    context.put("nama_pengarah",nama_pengarah);

	    return id_pengarah;
	    
	}//close nameAndIdPengarah
	
	private String selectedTab() throws Exception{
		String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		return selectedTab;	
	}//close selectedTab
	
	private void paging() throws Exception{
		
	/*	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "1");
    	}
    	*/
    	context.put("paging", "1");
	}//close paging
	
	private void anchor() throws Exception{
		String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
	}//close anchor
	
	private void UtilsItem() throws Exception{
		context.put("Util", new lebah.util.Util());
    	context.put("Utils", new ekptg.helpers.Utils());
	}//close UtilsItem
	
	@SuppressWarnings("static-access")
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		model.setListCarianSek8(nofail,tarikh,status,userIdNeg);

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
	
}//close class
