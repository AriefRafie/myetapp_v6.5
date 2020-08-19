package ekptg.view.ppt;

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
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.model.ppt.PPTHeader;
import ekptg.view.ppt.email.EmailTester;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmPermohonanUPTSek4 extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPermohonanUPTSek4.class);
	
	//model
	FrmPermohonanUPTData model = new FrmPermohonanUPTData();
	
	PPTHeader header = new PPTHeader();
	MyInfoPPTData myInfo = new MyInfoPPTData();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for paging
    	String action = getParam("action");
    	
    	//get user login detail
    	//String id_user = (String) session.getAttribute("_ekptg_user_id");
    	//String userLogin = (String) session.getAttribute("_portal_username");
    	
    	String vm = "";
    	String noLOT = "";
    	String idpegawai = "";
    	
    	//reset data
    	String checkedAda = "";
    	String checkedTiada = "";
    	context.put("semak", "");
		context.put("changeby","no");
		context.put("carianNoLot", "");
		context.put("carianNoLot2", "");
		context.put("showJajahan","no");
		
    	//Vector list = new Vector();
    	Vector listPageDepan = new Vector();
    	Vector listDokumen = new Vector();
    	Vector listPohon = new Vector();
    	Vector listPohon2 = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector dataMaklumatTanah = new Vector();
    	Vector listAgensi = new Vector();
    	Vector alamatKementerian = new Vector();
    	Vector senaraiSemak = new Vector();
    	Vector dataNamaPengarah = new Vector();
    	Vector listUserid = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	//Vector clear
    	getIdSuburusanstatusfail.clear();
    	listDokumen.clear();
    	listPageDepan.clear();
    	listPohon.clear();
    	listPohon2.clear();
    	listAgensi.clear();
    	listMaklumatTanah.clear();
    	dataMaklumatTanah.clear();
    	senaraiSemak.clear();
    	listUserid.clear();
    	dataNamaPengarah.clear();
    	
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");    	
	    context.put("userIdNeg", userIdNeg);
	    
    	//screen jsp
    	String listdepan = "app/ppt/frmSenaraiPermohonanUPT.jsp";
    	String screenUtama = "app/ppt/frmUPTDaftar.jsp";
    	String screenTanah = "app/ppt/frmUPTHakmilikTambah.jsp";
    	String screenDokumen = "app/ppt/frmUPTDocTambah.jsp";
    	
    	this.context.put("maklumat_PB_Salin", "");
		this.context.put("id_hakmilikpb_salin", "");
		this.context.put("id_hakmilik_salin", "");
		this.context.put("maklumat_Hakmilik_Salin", "");

		
    	
    	
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
    		context.put("paging", "1");
    	}
    	
    	//tabbed
    	String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
    	
		//context.put("id_permohonan", "");
		String idpermohonan = getParam("id_permohonan");
		context.put("id_status", "");
		 String nama_pengarah = "";
		 String id_pengarah = "";
		if(!idpermohonan.equals(""))
		{
		Vector list_sub = null;
		list_sub = header.listPerjalananFail(idpermohonan);
		this.context.put("list_sub_header", list_sub);
		
		//get nama pengarah dan id pengarah
    	//nameAndId(userIdNeg);
    	//String id_pengarah = nameAndId(userIdNeg);
    	
	    //GET NAMA PENGARAH
	   
	    model.setNamaPengarah(userIdNeg);
	    dataNamaPengarah = model.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = np.get("nama_pengarah").toString();
	    	id_pengarah = np.get("user_id").toString();	    	
	    }
	    
	    
	    
		}
		else
		{
			//default list
	    	listPageDepan = model.getListPemohon(userIdNeg);
		}
		context.put("nama_pengarah",nama_pengarah);
		
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
	    	
	    	checkFieldWujud("TBLPPTDOKUMEN","JENIS_DOKUMEN","varchar2(50)",db);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_HAKMILIK","NUMBER",db);
		}
		finally {			
		if (db != null)
		db.close();
		}
		*/
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if ("simpan".equals(submit)) {
		     
    		String result = "";
    		if (doPost.equals("true")) {
    			//simpan data
        		result = addSPT(session,userIdNeg);
     		}

    		context.put("ResultAdd",result);
    		
    		idpermohonan = (String) context.get("ResultAdd");

    		
    		if(idpermohonan!=""){
    			
    			context.put("id_permohonan", idpermohonan);
        		
        		//senarai semak
        		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
        		context.put("senaraiSemakan", senaraiSemak);
        		   
        		//data 1
         		model.setListPohon(idpermohonan);
         		listPohon = model.getListPohon();
         		
         		//data 2
         		model.setListPohon2(idpermohonan);
         		listPohon2 = model.getListPohon2();
         		
         		//dokumen (blob)
         		/*
         		model.setListDokumen(idpermohonan);
         		listDokumen = model.getListDokumen();
        		context.put("listDokumen", listDokumen);
        		context.put("listD_size", listDokumen.size());
        		*/
         		
        		//data & list maklumat tanah
         	//	model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
         	//	listMaklumatTanah = model.getListMaklumatTanah();
         	//	context.put("listMaklumatTanah", listMaklumatTanah);
         		//context.put("saiz_listTanah", listMaklumatTanah.size());
         		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
        		
        				
         		String flag_jenis_kod_daerah = "";
        		String currentStatus = "";
        		String currentSuburusan = "";
        		String idAGENSI = "";
        		String idNegeri = "";
        		String idKementerian = "";
        		String idDaerah = "";
        		String idMukim = "";   		
        		String flag_peruntukan = "";
        		String id_fail = "";
        		if(listPohon.size()!=0){
        			Hashtable h = (Hashtable) listPohon.get(0);
        			currentStatus = h.get("id_status").toString();
        			currentSuburusan = h.get("idSuburusan").toString();  
        			idAGENSI = h.get("idAgensi").toString();
        			idNegeri = h.get("idNegeri").toString();
        			idKementerian = h.get("idKementerian").toString();
        			idDaerah = h.get("idDaerah").toString();
        			idMukim = h.get("id_mukim").toString();
        			flag_peruntukan = h.get("flag_peruntukan").toString();
        			id_fail = h.get("idFail").toString();
        			flag_jenis_kod_daerah = (String)h.get("flag_jenis_kod_daerah");
        		}   		
        		context.put("currentSuburusan", currentSuburusan);
        		context.put("currentStatus", currentStatus);
        		context.put("id_status", currentStatus);
        		
        		//id
        		context.put("id_fail",id_fail);
        		
        		//dropdown by
            	if(idAGENSI!=""){
            		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Long.parseLong(idAGENSI),"style=width:500px class=disabled disabled"));
            	}else{
            		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",null,"style=width:500px class=disabled disabled"));
            	}
    	
            	//dropdown
        		context.put("selectNegeri",HTML.SelectNegeriMampu("editNegeri",Utils.parseLong(idNegeri),"style=width:300px class=disabled disabled"));
        		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(idKementerian),"style=width:500px class=disabled disabled"));
        		context.put("selectDaerah",HTML.SelectDaerahKOD(flag_jenis_kod_daerah,"editDaerah",Utils.parseLong(idDaerah),"style=width:300px class=disabled disabled",null));
        		context.put("selectBandar",HTML.SelectMukimNoKod("editBandar",Utils.parseLong(idMukim),"style=width:auto class=disabled disabled"));
        		
        		String id_projekNegeri = "";
        		if(listPohon2.size()!=0){
        			Hashtable i = (Hashtable) listPohon2.get(0);
        			id_projekNegeri = i.get("idProjekNegeri").toString();
        		}
        		
        		//validation jajahan
        		if(id_projekNegeri.equals("3")){
        			context.put("showJajahan","yes");
        		}else{
        			context.put("showJajahan","no");
        		}
        		
        		context.put("selectProjekNegeri",HTML.SelectNegeriMampu("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px class=disabled disabled"));
    		
    			if (flag_peruntukan.equals("1")){
    				checkedAda = "checked";
    				checkedTiada = "";
    			}else if(flag_peruntukan.equals("2")){
    				checkedAda = "";
    				checkedTiada = "checked";
    			}else{
    				checkedAda = "checked";
    				checkedTiada = "";
    			}
    			context.put("checkedAda",checkedAda);
    			context.put("checkedTiada",checkedTiada);
  		
    			//data
        		context.put("dataPermohonan2", listPohon2);
        		context.put("dataPermohonan", listPohon);
        		
        		//form validation
        		context.put("semak", "yes");
        		context.put("edit", "no");
        		
        		//screen
        		vm = screenUtama;
        		
        	//if refresh after save	
    		}else{
    			
    			listPageDepan = model.getListPemohon(userIdNeg);
        		defaultPage(listPageDepan);	
        		vm = listdepan;
    			
    		}//close if(idPermohonanFromResult!="")

		}//close simpan
    	/*
    	else if ("salinHakmilik".equals(submit)) {

			// form validation
			context.put("mode", "new");

			// get data from pendaftaran
			newDataSetting(idpermohonan);

			String id_hakmilik_salin = getParam("id_hakmilik_salin");
			this.context.put("id_hakmilik_salin", id_hakmilik_salin);
			this.context.put("maklumat_Hakmilik_Salin", "");

			if (!id_hakmilik_salin.equals("")
					&& !id_hakmilik_salin.equals(null)
					&& model.salin_maklumat_tanah(id_hakmilik_salin).size() > 0) {
				this.context.put("maklumat_Hakmilik_Salin",
						model.salin_maklumat_tanah(id_hakmilik_salin));
				dataHakmilik_copy(id_hakmilik_salin, "enabled");
			}

			vm = screenTanah;

		}// close tambahHM
    	*/
    	else if("salinHakmilik".equals(submit))
    	{
    		
    		noLOT = getParam("carianNoLot2");
    		context.put("carianNoLot2", noLOT.trim());
    		
    		String id_hakmilik_salin = getParam("id_hakmilik_salin");
			this.context.put("id_hakmilik_salin", id_hakmilik_salin);
			this.context.put("maklumat_Hakmilik_Salin", "");

		
    		
    		
    		maklumatPageHakMilik(session,idpermohonan,noLOT);
       		context.put("semakTanah", "no");
       		
       		if (!id_hakmilik_salin.equals("")
					&& !id_hakmilik_salin.equals(null)
					&& model.salin_maklumat_tanah(id_hakmilik_salin).size() > 0) {
				this.context.put("maklumat_Hakmilik_Salin",
						model.salin_maklumat_tanah(id_hakmilik_salin));
				dataHakmilik_copy(id_hakmilik_salin, "enabled");			
			}
    		
    		//screen
    		vm = screenTanah;
    		
    	}//close tambah maklumat tanah hakmilik
    	
    	else 
    	if("addMaklumatTanah".equals(submit))
    	{
    		//myLogger.info("masukk");
    		//if (doPost.equals("true")) { //yati comment
    			myLogger.info("addMaklumatTanah: ------");
    			//simpan data
        		add_maklumat_tanah(session);
     		//}

    		maklumatPageHakMilik(session,idpermohonan,noLOT);
    		
    		//form validation
    		context.put("semakTanah", "no");
    		
    		//screen
    		vm = screenTanah;
    		
    	}//close add maklumat tanah
    	
    	else 
    	if("edit_maklumat".equals(submit)){
    		
    		noLOT = getParam("carianNoLot2");
    		context.put("carianNoLot2", noLOT.trim());
    		
    		String idHakmilik = getParam("id_hakmilik");
    		context.put("id_hakmilik", idHakmilik);
    		
    		//data & list maklumat tanah
  			//model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
  			//listMaklumatTanah = model.getListMaklumatTanah();
  			//context.put("listMaklumatTanah", listMaklumatTanah);
  			//context.put("saiz_listTanah", listMaklumatTanah.size());
  			context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
  			
  			
    		//data header
    		model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		
    		//data header 2
    		model.setListPohon2(idpermohonan);
    		listPohon2 = model.getListPohon2();
    		
    		//data & list maklumat tanah
    		model.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = model.getMaklumatTanah();
    		context.put("dataMaklumatTanah", dataMaklumatTanah);
    		context.put("saiz_dataTanah", dataMaklumatTanah.size());
    		
    		String currentStatus = "";
    		String namaDaerah = "";
    		String id_daerah = "";
    		if(listPohon.size()!=0){
    			Hashtable g = (Hashtable) listPohon.get(0);
    			currentStatus = g.get("id_status").toString();
    			namaDaerah = g.get("daerah").toString();
    			id_daerah = g.get("idDaerah").toString();
    		}
    		
    		//input data from listPohon
    		context.put("currentStatus", currentStatus);
    		context.put("existDaerah", namaDaerah);
    		context.put("id_existDaerah", id_daerah);
    		context.put("id_status", currentStatus);
    		
    		String id_daerahpenggawa = "";
    		String id_mukim = "";
    		String id_jenishakmilik = "";
    		String id_luaslot = "";
    		String id_lot = "";
    		if(dataMaklumatTanah.size()!=0){
    			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
    			id_mukim = h.get("id_mukim").toString();
    			id_jenishakmilik = h.get("id_jenishakmilik").toString();
    			id_luaslot = h.get("id_luasLot").toString();
    			id_lot = h.get("id_lot").toString();
    			id_daerahpenggawa = h.get("id_daerahpenggawa").toString();
    		}
    		
    		String id_projekNegeri = "";
    		String nama_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable j = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = j.get("idProjekNegeri").toString();
    			nama_projekNegeri = j.get("projek_negeri").toString();
    		}
    		context.put("id_existNegeri", id_projekNegeri);
			context.put("existNegeri", nama_projekNegeri);
			
    		//dropdown by
    		if(id_daerah!=""){
    			context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(id_daerah,"MKMukim",Utils.parseLong(id_mukim),"style=width:auto class=disabled disabled"));
    		}else{
    			context.put("selectMukim",HTML.SelectMukimNoKod("MKMukim",Utils.parseLong(id_mukim),"style=width:auto class=disabled disabled"));
        	}
    		
    		//validation jajahan
    		if(id_projekNegeri.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		
    		//untuk kelantan shj
    		context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("daerahpenggawa",Utils.parseLong(id_daerahpenggawa),null,"style=width:274px disabled class=disabled"));
    		
    		//dropdown
    		if(id_projekNegeri.equals("10")){
    			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"class=disabled disabled id=socJenisHakmilik style=width:auto"));   	
    		}else{
    			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"class=disabled disabled id=socJenisHakmilik style=width:auto"));   	
    		}
    		
    		//dropdown
    		//context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("MKJenisHakmilik",Utils.parseLong(id_jenishakmilik),"class=disabled style=width:auto disabled"));
    		context.put("selectLuas",HTML.SelectLuas("MKLuas",Utils.parseLong(id_luaslot),"style=width:250px class=disabled disabled"));
    		context.put("selectLot", HTML.SelectUnitPT("MKLot",Utils.parseLong(id_lot),"class=disabled disabled style=width:auto"));
    		
    		//form validation
    		context.put("semakTanah", "yes");
    		context.put("wantedit", "no");
    		
    		//screen
    		vm = screenTanah;
    		
    	}//close edit maklumat
    	
    	else 
    	if ("deleteMaklumatTanah".equals(submit)) {
  
    		//delete data
    		deleteMaklumatTanah(session);
  	      	
  	      	//data header
  			model.setListPohon(idpermohonan);
  			listPohon = model.getListPohon();
  		
  			//data header 2
  			model.setListPohon2(idpermohonan);
  			listPohon2 = model.getListPohon2();
  		
  			//data & list maklumat tanah
  			model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
  			listMaklumatTanah = model.getListMaklumatTanah();
  		
  			String id_negeri = "";
  			String id_kementerian = "";
  			String id_agensi = "";
  			String id_daerah = "";		
  			String flag_jenis_kod_daerah = "";
  			if(listPohon.size()!=0){
  				Hashtable h = (Hashtable) listPohon.get(0);
  				id_negeri = h.get("idNegeri").toString();
  				id_kementerian = h.get("idKementerian").toString();
  				id_agensi = h.get("idAgensi").toString();
  				id_daerah = h.get("idDaerah").toString();
  				flag_jenis_kod_daerah = (String)h.get("flag_jenis_kod_daerah");
  			}
  			
  			//dropdown
  			context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri")); 		
  			context.put("selectNegeri",HTML.SelectNegeriMampu("editNegeri",Utils.parseLong(id_negeri),"disabled class=disabled"));
  			context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),"disabled class=disabled"));
  			context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(id_agensi),"disabled class=disabled"));
  			context.put("selectDaerah",HTML.SelectDaerahKOD(flag_jenis_kod_daerah,"editDaerah",Utils.parseLong(id_daerah),"disabled class=disabled",null));
  		
  			String id_projekNegeri = "";
  			if(listPohon2.size()!=0){
  				Hashtable i = (Hashtable) listPohon2.get(0);
  				id_projekNegeri = i.get("idProjekNegeri").toString();
  			}
  			
  			//dropdown
  			context.put("selectProjekNegeri",HTML.SelectNegeriMampu("editProjekNegeri",Utils.parseLong(id_projekNegeri),"disabled class=disabled"));
  		
  			//data
		    context.put("dataPermohonan2", listPohon2);
  			context.put("dataPermohonan", listPohon);
  			//context.put("listMaklumatTanah", listMaklumatTanah);
  			//context.put("saiz_listTanah", listMaklumatTanah.size());
  			context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
  			
  		
  			//form validation
  			context.put("semak", "yes");
  			context.put("edit", "no");
  		
  			//screen
  			vm = screenUtama;
  	      	
  	    }//close delete Maklumat Tanah
    	
    	else 
    	if("kemaskiniTanah".equals(submit))
    	{
    		String idHakmilik = getParam("id_hakmilik");
    	
    		context.put("id_hakmilik", idHakmilik);
    		
    		model.setListPohon(idpermohonan);
  			listPohon = model.getListPohon();   		
  			String _idDaerah = "";
  			String namaDaerah = "";
  			if(listPohon.size()!=0){
  				Hashtable i = (Hashtable) listPohon.get(0);
  				namaDaerah = i.get("daerah").toString();
  				_idDaerah = i.get("idDaerah").toString();
  			}			
  			context.put("existDaerah", namaDaerah);
			context.put("id_existDaerah", _idDaerah);
    		
    		
    		model.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = model.getMaklumatTanah();   
    		String id_daerahpenggawa = "";
    		String lot = "";
    		String pt = "";
    		String id_mukim = "";
    		String id_jenishakmilik = "";
    		String id_luaslot = "";
    		String id_lot = "";
    		if(dataMaklumatTanah.size()!=0){
    			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
    			lot = h.get("no_lot").toString();
    			pt = h.get("no_pt").toString();
    			id_mukim = h.get("id_mukim").toString();
    			id_jenishakmilik = h.get("id_jenishakmilik").toString();
    			id_luaslot = h.get("id_luasLot").toString();
    			id_lot = h.get("id_lot").toString();
    			id_daerahpenggawa = h.get("id_daerahpenggawa").toString();
    		}
    		
    		model.setListPohon2(idpermohonan);
  			listPohon2 = model.getListPohon2();
    		String id_projekNegeri = "";
    		String nama_ProjekNegeri = "";
  			if(listPohon2.size()!=0){
  				Hashtable j = (Hashtable) listPohon2.get(0);
  				id_projekNegeri = j.get("idProjekNegeri").toString();
  				nama_ProjekNegeri = j.get("projek_negeri").toString();
  			}
  			context.put("id_existNegeri", id_projekNegeri);
			context.put("existNegeri", nama_ProjekNegeri); 
			
			
    		//dropdown by
    		if(_idDaerah!=""){
    			context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(_idDaerah,"editMukim",Utils.parseLong(id_mukim),"id=editMukim style=width:auto"));
    		}else{
    			context.put("selectMukim",HTML.SelectMukimNoKod("editMukim",Utils.parseLong(id_mukim),"id=editMukim style=width:auto"));
    		}
    		
    		//dropdown
    		if(id_projekNegeri.equals("10")){
    			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik style=width:auto"));   	
    		}else{
    			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik style=width:auto"));   	
    		}
    		
    		//dropdown
    		//context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("editJenisHakmilik",Utils.parseLong(id_jenishakmilik),"style=width:auto"));
    		context.put("selectLuas",HTML.SelectLuas("editLuas",Utils.parseLong(id_luaslot),"style=width:250px"));
    		context.put("selectLot", HTML.SelectUnitPT("editLot",Utils.parseLong(id_lot),"style=width:auto"));
    		
    		
    		if(lot.isEmpty()){
    			context.put("checkA", "");
    			context.put("checkB", "checked");
    			context.put("disA", "disabled");
    			context.put("disB", "");
    		}else if(pt.isEmpty()){
    			context.put("checkA", "checked");
    			context.put("checkB", "");
    			context.put("disA", "");
    			context.put("disB", "disabled");
    		}else{
    			context.put("checkA", "");
    			context.put("checkB", "");
    			context.put("disA", "");
    			context.put("disB", "");
    		}
    		
  			
			//validation jajahan
    		if(id_projekNegeri.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		
    		//untuk kelantan shj
    		context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("daerahpenggawa",Utils.parseLong(id_daerahpenggawa),null,"style=width:274px"));
    		
			
			//data
    		context.put("dataMaklumatTanah", dataMaklumatTanah);
    		context.put("saiz_dataTanah", dataMaklumatTanah.size());

    		//form validation
    		context.put("semakTanah", "yes");
    		context.put("wantedit", "yes");
    		
    		//screen
    		vm = screenTanah;
    		
    	}//close kemaskiniTanah
    	
    	else 
    	if("kemaskini".equals(submit)){
    		
    		model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		
    		model.setListPohon2(idpermohonan);
    		listPohon2 = model.getListPohon2();
    		
    		model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
    		listMaklumatTanah = model.getListMaklumatTanah();
    		
    		String flag_jenis_kod_daerah = "";
    		String id_agensi = "";
    		String id_daerah = "";
    		String id_mukim = "";
    		String id_kementerian = "";
    		String id_negeri = "";
    		String status = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			id_agensi = h.get("idAgensi").toString();
    			id_daerah = h.get("idDaerah").toString();
    			id_mukim = h.get("id_mukim").toString();
    			id_kementerian = h.get("idKementerian").toString();
    			id_negeri = h.get("idNegeri").toString();
    			status = h.get("id_status").toString();
    			flag_jenis_kod_daerah = (String)h.get("flag_jenis_kod_daerah");
    		}
    		
    		context.put("currentStatus", status);
    		context.put("id_status", status);
    		
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		//validation jajahan
    		if(id_projekNegeri.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		
    		//dropdown by
    		if(id_kementerian!=""){
    			context.put("selectAgensi",HTML.SelectAgensiByKementerian("editAgensi",id_kementerian,Utils.parseLong(id_agensi),"style=width:500px"));	
    		}else{
    			context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(id_agensi),"style=width:500px"));	
    		}
    		if(id_projekNegeri!=""){
    			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(flag_jenis_kod_daerah,id_projekNegeri,"editDaerah",Utils.parseLong(id_daerah),"style=width:300px",null));
        	}else{
    			context.put("selectDaerah",HTML.SelectDaerahKOD(flag_jenis_kod_daerah,"editDaerah",Utils.parseLong(id_daerah),"style=width:300px",null));
        	}
    		if(id_daerah!=""){
    			context.put("selectBandar",HTML.SelectMukimNoKodByDaerah(id_daerah,"editBandar",Utils.parseLong(id_mukim),"style=width:auto"));
    		}else{
    			context.put("selectBandar",HTML.SelectMukimNoKod("editBandar",Utils.parseLong(id_mukim),"style=width:auto"));
    		}
    		
    		//dropdown
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),null,"style=width:500px onChange=\"doChangeidKementerianUPDATE();\" "));
    		context.put("selectNegeri",HTML.SelectNegeriMampu("editNegeri",Utils.parseLong(id_negeri),"class=disabled disabled style=width:300px"));
 
    		if(userIdNeg.equals("14")){
    			context.put("selectProjekNegeri",HTML.SelectNegeriMampuKL("editProjekNegeri",Utils.parseLong(id_projekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUPDATE();\" "));
    		}else{
	    		context.put("selectProjekNegeri",HTML.SelectNegeriMampu("editProjekNegeri",Utils.parseLong(id_projekNegeri),null,"style=width:300px disabled class=disabled onChange=\"doChangeidProjekNegeriUPDATE();\" "));
	    	}	
    
    		
    		//data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("dataPermohonan", listPohon);
    		//context.put("listMaklumatTanah", listMaklumatTanah);
    		//context.put("saiz_listTanah", listMaklumatTanah.size());
    		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
    		
    		
    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "yes");
    		
    		//screen
    		vm = screenUtama;
    		
    	}//close kemaskini
    	
    	else 
    	if ("doChangeidKementerianUPDATE".equals(submit)) {
    		
    		//onchange validation
    		context.put("changeby","yes");
    		
    		doChangeidKementerianUPDATE(idpermohonan,userIdNeg);
    		
    		//screen
	        vm = screenUtama;

   	    }//dochangeidkementerianupdate 
 
    	else if ("doChangeidProjekNegeriUPDATE".equals(submit)) {
    		
    		//onchange validation
    		context.put("changeby","yes");
    		
    		String idEditProjekNegeri = getParam("editProjekNegeri");
    		
    		//get and set data
    		context.put("poskod", getParam("edit_poskod"));
        	context.put("alamat1", getParam("edit_alamat1"));
        	context.put("alamat2", getParam("edit_alamat2"));
        	context.put("alamat3", getParam("edit_alamat3"));
    		
        	String sorJenisKodDaerah = getParam("sorJenisKodDaerah");
    		context.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));
    		
        	//id dropdown	
    		String idAgensi = getParam("editAgensi");
    		String idKementerian = getParam("editKementerian");
    		
    		//senarai semak
    		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		    context.put("senaraiSemakan", senaraiSemak);
		    
		    //data header
		    model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		String status = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			status = h.get("id_status").toString();
    		}
    		
    		context.put("currentStatus", status);
    		context.put("id_status", status);
    		
    		//data & list maklumat tanah
    		model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
    		listMaklumatTanah = model.getListMaklumatTanah();
     		
    		//validation jajahan
    		if(idEditProjekNegeri.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		 	
    		//dropdown by
    		if(idEditProjekNegeri!=""){
    			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,idEditProjekNegeri,"editDaerah",null,null,"id=editDaerah style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
    		}else{
    			context.put("selectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"editDaerah",null,null,"id=editDaerah style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
    		}
    		if(idKementerian!=""){
    			context.put("selectAgensi",HTML.SelectAgensiByKementerian("editAgensi",idKementerian,Utils.parseLong(idAgensi),"style=width:500px"));
    		}else{
    			context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(idAgensi),"style=width:500px"));	
    		}
    		
    		alamatKementerian = model.getAlamatKementerian(idKementerian);
    		String idNegeri = "";
    		if(alamatKementerian.size()!=0){
    			Hashtable AK = (Hashtable) alamatKementerian.get(0);
    			idNegeri = AK.get("id_negeri").toString();
    		}	
    		
    		//dropdown
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doChangeidKementerianUPDATE();\" "));
    		context.put("selectNegeri",HTML.SelectNegeriMampu("editNegeri",Utils.parseLong(idNegeri),"class=disabled disabled style=width:300px"));
    		context.put("selectBandar",HTML.SelectMukimNoKod("editBandar",null,"style=width:auto"));
    		
    		if(userIdNeg.equals("14")){
    			context.put("selectProjekNegeri",HTML.SelectNegeriMampuKL("editProjekNegeri",Utils.parseLong(idEditProjekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUPDATE();\" "));
	    	}else{
	    		context.put("selectProjekNegeri",HTML.SelectNegeriMampu("editProjekNegeri",Utils.parseLong(idEditProjekNegeri),null,"style=width:300px class=disabled disabled onChange=\"doChangeidProjekNegeriUPDATE();\" "));
	    	}
    		
    		//get and set data
    		context.put("tujuan", getParam("edit_tujuan"));
    		context.put("tarikhSurat", getParam("edit_tarikh_surat"));
    		context.put("tarikhHendak", getParam("edit_tarikh_kehendaki"));
    		context.put("rujKementerian", getParam("edit_rujukan_kementerian"));
    		//context.put("txdTarikhPermohonanKJP", getParam("edit_tarikh_permohonan"));
    		context.put("no_rujukan_ptg", getParam("no_rujukan_ptg"));
    		context.put("no_rujukan_ptd", getParam("no_rujukan_ptd"));
    		context.put("no_rujukan_upt", getParam("no_rujukan_upt"));
    		
    		//radio button peruntukan
    		if (getParam("flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (getParam("flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			context.put("checkedAda",checkedAda);
			context.put("checkedTiada",checkedTiada);
			
			//data
    		context.put("dataPermohonan2", listPohon2);
    		//context.put("listMaklumatTanah", listMaklumatTanah);
    	//	context.put("saiz_listTanah", listMaklumatTanah.size());
    		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
    		
   	    	context.put("dataPermohonan", listPohon);
   	    	
   	    	//form validation
    		context.put("semak", "yes");
    		context.put("edit", "yes");
    		
    		//screen
	        vm = screenUtama;
	        
   	    }//dochangeidprojeknegeriupdate
    	
    	else
    	if("hapusDokumen".equals(submit)){
    		
    		hapusDokumen(session);
    		
    		dataSemak(session,idpermohonan,noLOT);
    		
    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "no");
    		
    		//screen
    		vm = screenUtama;
    		
    	}//close delete dokumen
    	
    	else 
    	if("semak".equals(submit)){
    		
    		noLOT = getParam("carianNoLot"); 
    		context.put("carianNoLot", noLOT.trim());
    		
    		dataSemak(session,idpermohonan,noLOT);
   
    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "no");
    		
    		//screen
    		vm = screenUtama;
         
    	}//close semak
    	
    	else 
    	if ("doChangeidKementerian".equals(submit)) {
    		
    		//get and set data
    		doChangeidKementerian(userIdNeg);
    		
    		//screen
	        vm = screenUtama;
	        
   	    }//close doChangeidKementerian 
    	
    	else 
        if ("onchangeJenisKodDaerah".equals(submit)) {
        		
        	//get and set data
    		doChangeidKementerian(userIdNeg);
    		
    		//screen
	        vm = screenUtama;
    	        
       	}//close onchangeJenisKodDaerah 
    	
        else 
        if ("onchangeJenisKodDaerahUpdate".equals(submit)) {
            	
        	//onchange validation
    		context.put("changeby","yes");
        	
    		//get and set data
    		doChangeidKementerianUPDATE(idpermohonan,userIdNeg);
    		
    		//screen
	        vm = screenUtama;
        	
        }//close onchangeJenisKodDaerahUpdate
    	
    	else 
        if ("getJenisKodDaerah".equals(submit)) {
            
        	//get and set data
    		doChangeidKementerian(userIdNeg);
    		
    		//get jenis kod daerah
    		getJenisKodDaerah(getParam("txdTarikhPermohonan"));
    		String sorJenisKodDaerah = getJenisKodDaerah(getParam("txdTarikhPermohonan"));
    		
    		if(userIdNeg!=""){
    			context.put("SelectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,userIdNeg,"daerah",Utils.parseLong(getParam("daerah")),null,"style=width:300px onChange=\"doChangeidMukim();\""));
        	}else{
        		context.put("SelectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"daerah",Utils.parseLong(getParam("daerah")),null,"style=width:300px onChange=\"doChangeidMukim();\""));
            }
    		
    		//screen
	        vm = screenUtama;
        	
        }//close getJenisKodDaerah
    	
        else 
        if ("getJenisKodDaerahUpdate".equals(submit)) {
            	
        	//onchange validation
    		context.put("changeby","yes");
        	
    		//get and set data
    		doChangeidKementerianUPDATE(idpermohonan,userIdNeg);
    		
    		//get jenis kod daerah
    		getJenisKodDaerah(getParam("edit_tarikh_permohonan"));
    		String sorJenisKodDaerah = getJenisKodDaerah(getParam("edit_tarikh_permohonan"));
    		
    		if(userIdNeg!=""){
    			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,userIdNeg,"editDaerah",Utils.parseLong(getParam("editDaerah")),null,"style=width:300px"));
    		}else{
    			context.put("selectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"editDaerah",Utils.parseLong(getParam("editDaerah")),null,"style=width:300px"));
    		}
    		
    		//screen
	        vm = screenUtama;
        	
        }//close getJenisKodDaerahUpdate
    	
    	else if ("doChangeidNegeri".equals(submit)) {
    		
    		//get and post all field content
    		context.put("addTujuan", getParam("tujuan"));
    		context.put("addPoskod", getParam("poskod"));
    		context.put("addAlamat1", getParam("alamat1"));
    		context.put("addAlamat2", getParam("alamat2"));
    		context.put("addAlamat3", getParam("alamat3"));
    		context.put("addTarikhSurat", getParam("tarikh_surat"));
    		context.put("addTarikhHendak", getParam("tarikh_kehendaki"));
    		context.put("addRujukan_kementerian", getParam("rujukan_kementerian"));
    		context.put("no_rujukan_ptg", getParam("no_rujukan_ptg"));
    		context.put("no_rujukan_ptd", getParam("no_rujukan_ptd"));
    		context.put("no_rujukan_upt", getParam("no_rujukan_upt"));
    		
    		String sorJenisKodDaerah = getParam("sorJenisKodDaerah");
    		context.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));
    		
    		//id dropdown
    		String idKementerian = getParam("kementerian");
    		String idAgensi = getParam("agensi");
    		String idBandar = getParam("bandar");
    		String idProjekNegeri = getParam("projek_negeri");

    		//dropdown by
/*13042011*/if(idProjekNegeri!=""){
    			context.put("SelectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,idProjekNegeri,"daerah",null,null,"id=daerah style=width:300px onChange=\"doChangeidMukim();\""));
    		}else{
    			context.put("SelectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"daerah",null,null,"id=daerah style=width:300px onChange=\"doChangeidMukim();\""));
    		}
    		if(idKementerian!=""){
    			context.put("SelectAgensi",HTML.SelectAgensiByKementerian("agensi",idKementerian,Utils.parseLong(idAgensi),"style=width:500px"));	
    		}else{
    			context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),null,"style=width:500px"));	
    		}
    		
    		//validation jajahan
    		if(idProjekNegeri.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		
    		alamatKementerian = model.getAlamatKementerian(idKementerian);    		
    		String id_negeriKementerian = "";	
    		if(alamatKementerian.size()!=0){
    			Hashtable AK = (Hashtable) alamatKementerian.get(0);
    			id_negeriKementerian = AK.get("id_negeri").toString();
    		}
    		
    		//dropdown
    		context.put("SelectBandar",HTML.SelectMukimNoKod("bandar",Utils.parseLong(idBandar),"style=width:auto"));
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Utils.parseLong(id_negeriKementerian),"style=width:325px class=disabled disabled"));    		
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doChangeidKementerian();\" "));
 
  			if(idProjekNegeri.equals("14") || idProjekNegeri.equals("15") || idProjekNegeri.equals("16")){
  				context.put("project_negeri",HTML.SelectNegeriMampuKL("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidNegeri();\" "));
	    	}else{
	    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidNegeri();\" "));
	    	}
    		
    		//radio button amaun peruntukan
    		if (getParam("new_flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			}else 
			if(getParam("new_flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}else{
				checkedAda = "checked";
				checkedTiada = "";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    
    		//reset data
    		context.put("dataPermohonan", "");
    		context.put("listMaklumatTanah", "");
    		context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		
    		//validation data
    		context.put("semak", "no");
    		context.put("edit", "no");

    		//tarikh permohonan--yati comment
    		//context.put("currentDATE", getParam("txdTarikhPermohonan"));
    		
    		//data
    		context.put("dataPermohonan2", listPohon2);
    		//context.put("listMaklumatTanah", listMaklumatTanah);
    	//context.put("saiz_listTanah", listMaklumatTanah.size());
    	context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
		
   	    	context.put("dataPermohonan", listPohon);

    		//screen
	        vm = screenUtama;
	        
   	    }//close doChangeidNegeri 
    	
    	else 
    	if("baru".equals(submit))
		{	
    		
    		//reset data
    		context.put("addTujuan", "");
    		context.put("addPoskod", "");
    		context.put("addAlamat1", "");
    		context.put("addAlamat2", "");
    		context.put("addAlamat3", "");
    		context.put("addTarikhHendak", "");
    		context.put("addTarikhSurat", "");
    		context.put("addRujukan_kementerian", "");
    		context.put("dataPermohonan", "");
    		context.put("TEMPcheckedYa","");
			context.put("TEMPcheckedTidak","");
    		context.put("TEMPcheckedAda","");
			context.put("TEMPcheckedTiada","");
    		context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		context.put("listMaklumatTanah", "");
    		context.put("no_rujukan_ptg", "");
    		context.put("no_rujukan_ptd", "");
    		context.put("no_rujukan_upt", "");
    	
    		context.put("sorJenisKodDaerah", "");
    		
    		//form validation
    		context.put("semak", "no");
    		context.put("edit", "no");
    		
    		//get current date
    		String now = "";
    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    		context.put("currentDATE",now);
    		
    		//get jenis kod daerah
    		getJenisKodDaerah(now);
    		String sorJenisKodDaerah = getJenisKodDaerah(now);
    		
    		//data
    		context.put("saiz_listTanah", listMaklumatTanah.size());

    		//dropdown
/*13042011*/if(userIdNeg.equals("14")){
    			context.put("project_negeri",HTML.SelectNegeriMampuKL("projek_negeri",null,null,"style=width:300px onChange=\"doChangeidNegeri();\""));
    		}else{
    			context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(userIdNeg),null,"style=width:300px disabled class=disabled onChange=\"doChangeidNegeri();\""));
    		}
   		
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian","style=width:500px onChange=\"doChangeidKementerian();\""));
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",null,"style=width:325px class=disabled disabled"));
    		
    		if(userIdNeg!="" && !userIdNeg.equals("14")){
    			context.put("SelectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,userIdNeg,"daerah",null,null,"style=width:300px onChange=\"doChangeidMukim();\""));
    		}else{
    			context.put("SelectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"daerah",null,null,"style=width:300px onChange=\"doChangeidMukim();\""));
    		}
    		
    		//validation jajahan
    		if(userIdNeg.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		
    		
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:500px"));
    		context.put("SelectBandar",HTML.SelectMukimNoKod("bandar",null,"style=width:auto"));
    		
    		//screen
	        vm = screenUtama;
	        
		}//close baru
    	
    	else if("tambahDokumen".equals(submit))
    	{
    		
    		context.put("completed",false);
    		
            //screen
            vm = screenDokumen;
            
    	}//close tambah dokumen
    	
    	else if("uploadFile".equals(submit))
    	{
    		
    		//upload file
    		uploadFiles();
    			
         	//alert jsp
         	context.put("completed",true);
         		
         	//screen
         	vm = screenDokumen;
       	
    	}//close upload file
    	
    	else if("tambah".equals(submit))
    	{
    		
    		noLOT = getParam("carianNoLot2");
    		context.put("carianNoLot2", noLOT.trim());
    		
    		maklumatPageHakMilik(session,idpermohonan,noLOT);
   
    		context.put("txtnolot","");
    		
    		//form validation
    		context.put("semakTanah", "no");
    		
    		//screen
    		vm = screenTanah;
    		
    	}//close tambah maklumat tanah hakmilik
    	
    	else if("updateMaklumatTanah".equals(submit))
    	{
    		
    		//update data maklumat tanah
    		updateMaklumatTanah(session);
    		
    		//String idpermohonan = getParam("id_permohonan");
    		String id_hakmilik = getParam("id_hakmilik");   		
    		context.put("id_hakmilik", id_hakmilik);
    		
    		//data & list maklumat tanah
     		model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
     		listMaklumatTanah = model.getListMaklumatTanah();
     		//context.put("listMaklumatTanah", listMaklumatTanah);
     		//context.put("saiz_listTanah", listMaklumatTanah.size());
     		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
    		
     		
    		model.setListPohon(idpermohonan);
  			listPohon = model.getListPohon();
  			String nama_daerah = "";
  			String id_daerah = "";
  			if(listPohon.size()!=0){
  				Hashtable i = (Hashtable) listPohon.get(0);
  				nama_daerah = i.get("daerah").toString();
  				id_daerah = i.get("idDaerah").toString();
  			}
  			context.put("existDaerah", nama_daerah);
	    	context.put("id_existDaerah", id_daerah);
	    	
  			model.setListPohon2(idpermohonan);
  			listPohon2 = model.getListPohon2();
  			String nama_projekNegeri = "";
  			String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable j = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = j.get("idProjekNegeri").toString();
    			nama_projekNegeri = j.get("projek_negeri").toString();
    		}   		
    		context.put("id_existNegeri", id_projekNegeri);
    		context.put("existNegeri", nama_projekNegeri);
    		
    		
    		//data & list maklumat tanah
    		model.setMaklumatTanah(id_hakmilik);
    		dataMaklumatTanah = model.getMaklumatTanah();  		
    		context.put("dataMaklumatTanah", dataMaklumatTanah);
    		context.put("saiz_dataTanah", dataMaklumatTanah.size());
    		String id_jenishakmilik = "";
    		String id_mukim = "";
    		String id_luaslot = "";
    		String id_lot = "";
    		String id_daerahpenggawa = "";
    		if(dataMaklumatTanah.size()!=0){
    			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
    			id_jenishakmilik = h.get("id_jenishakmilik").toString();
    			id_mukim = h.get("id_mukim").toString();
    			id_luaslot = h.get("id_luasLot").toString();
    			id_lot = h.get("id_lot").toString();
    			id_daerahpenggawa = h.get("id_daerahpenggawa").toString();
    		}
  
    		//validation jajahan
    		if(id_projekNegeri.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		
    		//untuk kelantan shj
    		context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("daerahpenggawa",Utils.parseLong(id_daerahpenggawa),null,"style=width:274px class=disabled disabled"));
    		
    		//dropdown
    		if(id_projekNegeri.equals("10")){
    			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"class=disabled disabled id=socJenisHakmilik style=width:auto"));   	
    		}else{
    			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"class=disabled disabled id=socJenisHakmilik style=width:auto"));   	
    		}
    		
        	//dropdown
    		//context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("MKJenisHakmilik",Utils.parseLong(id_jenishakmilik),"style=width:auto class=disabled disabled"));
    		context.put("selectMukim",HTML.SelectMukimNoKod("MKMukim",Utils.parseLong(id_mukim),"style=width:auto class=disabled disabled"));
    		context.put("selectLuas",HTML.SelectLuas("MKLuas",Utils.parseLong(id_luaslot),"style=width:210px class=disabled disabled"));
    		context.put("selectLot", HTML.SelectUnitPT("MKLot",Utils.parseLong(id_lot),"style=width:auto class=disabled disabled"));
    		
    		//form validation
    		context.put("semakTanah", "yes");
    		context.put("wantedit", "no");
    	
    		//screen
    		vm = screenTanah;
    		
    	}//close update maklumat tanah
    	
    	else if("sah".equals(submit))
    	{
    		
    		//get current idsuburusanstatusfail
    		String id_suburusanstatusfailppt = "";
    		model.setGetIdSuburusanstatusfail(idpermohonan);
    		getIdSuburusanstatusfail = model.getGetIdSuburusanstatusfail();
    		if(getIdSuburusanstatusfail.size()!=0){
    			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
    			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
    		}
    		
    		
    		if (doPost.equals("true")) {
    			//sahkan
        		sah(session,id_suburusanstatusfailppt,userIdNeg);
     		}

    		//senarai semak
    		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		    context.put("senaraiSemakan", senaraiSemak);
		   
    		model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		
    		String flag_jenis_kod_daerah = "";
    		String current_status = "";
    		String curren_suburusan = "";
    		String idAGENSI = "";
    		String id_negeri = "";
    		String id_kementerian = "";
    		String id_daerah = "";
    		String id_mukim = "";
    		String flag_peruntukan = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			current_status = h.get("id_status").toString();
    			curren_suburusan = h.get("idSuburusan").toString();
    			idAGENSI = h.get("idAgensi").toString();
    			id_negeri = h.get("idNegeri").toString();
    			id_kementerian = h.get("idKementerian").toString();
    			id_daerah = h.get("idDaerah").toString();
    			id_mukim = h.get("id_mukim").toString();
    			flag_peruntukan = h.get("flag_peruntukan").toString();
    			flag_jenis_kod_daerah = (String)h.get("flag_jenis_kod_daerah");
    		}
    		context.put("currentStatus", current_status);
    		context.put("currentSuburusan", curren_suburusan);
    		context.put("id_status", current_status);
    		
    		//dropdown
    		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Long.parseLong(idAGENSI),"style=width:470px class=disabled disabled"));
    		context.put("selectNegeri",HTML.SelectNegeriMampu("editNegeri",Utils.parseLong(id_negeri),"style=width:300px class=disabled disabled"));
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),"style=width:470px class=disabled disabled"));
    		context.put("selectDaerah",HTML.SelectDaerahKOD(flag_jenis_kod_daerah,"editDaerah",Utils.parseLong(id_daerah),"style=width:300px class=disabled disabled",null));
    		context.put("selectBandar",HTML.SelectMukimNoKod("editBandar",Utils.parseLong(id_mukim),"style=width:auto class=disabled disabled"));
    			
    		if (flag_peruntukan.equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			}else 
			if(flag_peruntukan.equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}else{
				checkedAda = "checked";
				checkedTiada = "";
			}
			context.put("checkedAda",checkedAda);
			context.put("checkedTiada",checkedTiada);
    
    		model.setListPohon2(idpermohonan);
    		listPohon2 = model.getListPohon2();
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		//dropdown
    		context.put("selectProjekNegeri",HTML.SelectNegeriMampu("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px class=disabled disabled"));
    		
    		//data & list maklumat tanah
    		//model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
    		//listMaklumatTanah = model.getListMaklumatTanah();
    		//context.put("listMaklumatTanah", listMaklumatTanah);
    		//context.put("saiz_listTanah", listMaklumatTanah.size());
    		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
    		
    		
			//data
    		context.put("dataPermohonan2", listPohon2);
    		//context.put("listMaklumatTanah", listMaklumatTanah);
    		//context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	context.put("dataPermohonan", listPohon);
    		
    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "no");
    		
    		//screen
    		vm = screenUtama;
    		
    	}//close sah
    	
    	else if("update".equals(submit))
    	{
    		
    		//update
    		update(session,userIdNeg);	

    		model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		String flag_jenis_kod_daerah = "";
    		String id_negeri = "";
    		String id_kementerian = "";
    		String id_agensi = "";
    		String id_daerah = "";
    		String id_mukim = "";
    		String flag_peruntukan = "";
    		String status = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			id_negeri = h.get("idNegeri").toString();
    			id_kementerian = h.get("idKementerian").toString();
    			id_agensi = h.get("idAgensi").toString();
    			id_daerah = h.get("idDaerah").toString();
    			id_mukim = h.get("id_mukim").toString();
    			flag_peruntukan = h.get("flag_peruntukan").toString();
    			status = h.get("id_status").toString();
    			flag_jenis_kod_daerah = (String)h.get("flag_jenis_kod_daerah");
    		}
    		
    		context.put("currentStatus", status);
    		context.put("id_status", status);
    		
    		//dropdown
    		context.put("selectNegeri",HTML.SelectNegeriMampu("editNegeri",Utils.parseLong(id_negeri),"style=width:300px disabled class=disabled"));
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),"style=width:470px disabled class=disabled"));
    		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(id_agensi),"style=width:470px disabled class=disabled"));
    		context.put("selectDaerah",HTML.SelectDaerahKOD(flag_jenis_kod_daerah,"editDaerah",Utils.parseLong(id_daerah),"style=width:300px disabled class=disabled",null));
    		context.put("selectBandar",HTML.SelectMukimNoKod("editBandar",Utils.parseLong(id_mukim),"style=width:auto disabled class=disabled"));
    		   
    		//radio button amaun peruntukan
    		if (flag_peruntukan.equals("1")){
 				checkedAda = "checked";
 				checkedTiada = "";
 			}else 
 			if(flag_peruntukan.equals("2")){
 				checkedAda = "";
 				checkedTiada = "checked";
 			}else{
 				checkedAda = "checked";
 				checkedTiada = "";
 			}
    		context.put("checkedAda",checkedAda);
 			context.put("checkedTiada",checkedTiada);
 
    		model.setListPohon2(idpermohonan);
    		listPohon2 = model.getListPohon2();
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		//dropdown
    		context.put("selectProjekNegeri",HTML.SelectNegeriMampu("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px disabled class=disabled"));
  
    		model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
    		listMaklumatTanah = model.getListMaklumatTanah();

    		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		    context.put("senaraiSemakan", senaraiSemak);
		    
		    //data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("dataPermohonan", listPohon);
    		//context.put("listMaklumatTanah", listMaklumatTanah);
    		//context.put("saiz_listTanah", listMaklumatTanah.size());
    		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
    		
    		
    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "no");
    		
    		//validation jajahan
    		if(id_projekNegeri.equals("3")){
    			context.put("showJajahan","yes");
    		}else{
    			context.put("showJajahan","no");
    		}
    		
    		//screen
    		vm = screenUtama;
    		
    	}//close update  	
    	
    	else 
        if("updateFlagSah".equals(submit)){
        		
        	if (doPost.equals("true")) {
    			//untuk disahkan
        		updateFlagSah(session);
     		}

    		//senarai semak
    		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		    context.put("senaraiSemakan", senaraiSemak);
		   
    		model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		
    		String flag_jenis_kod_daerah = "";
    		String nama_kementerian = "";
    		String current_status = "";
    		String curren_suburusan = "";
    		String idAGENSI = "";
    		String id_negeri = "";
    		String id_kementerian = "";
    		String id_daerah = "";
    		String id_mukim = "";
    		String flag_peruntukan = "";
    		String no_fail = "";
    		String tujuan = "";
    		String tarikh_permohonan = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			current_status = h.get("id_status").toString();
    			curren_suburusan = h.get("idSuburusan").toString();
    			idAGENSI = h.get("idAgensi").toString();
    			id_negeri = h.get("idNegeri").toString();
    			id_kementerian = h.get("idKementerian").toString();
    			id_daerah = h.get("idDaerah").toString();
    			id_mukim = h.get("id_mukim").toString();
    			flag_peruntukan = h.get("flag_peruntukan").toString();
    			no_fail = (String)h.get("no_fail");
    			tujuan = (String)h.get("tujuan");
    			tarikh_permohonan = (String)h.get("tarikh_permohonan");
    			nama_kementerian = (String)h.get("nama_kementerian");
    			flag_jenis_kod_daerah = (String)h.get("flag_jenis_kod_daerah");
    		}
    		context.put("currentStatus", current_status);
    		context.put("currentSuburusan", curren_suburusan);
    		context.put("id_status", current_status);
    		
    		
    		if (doPost.equals("true")) {
        		sendEmail(id_pengarah,"ptTOpengarah",no_fail,tujuan,tarikh_permohonan,nama_kementerian); 
     		}
    		
    		//dropdown
    		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Long.parseLong(idAGENSI),"style=width:470px class=disabled disabled"));
    		context.put("selectNegeri",HTML.SelectNegeriMampu("editNegeri",Utils.parseLong(id_negeri),"style=width:300px class=disabled disabled"));
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),"style=width:470px class=disabled disabled"));
    		context.put("selectDaerah",HTML.SelectDaerahKOD(flag_jenis_kod_daerah,"editDaerah",Utils.parseLong(id_daerah),"style=width:300px class=disabled disabled",null));
    		context.put("selectBandar",HTML.SelectMukimNoKod("editBandar",Utils.parseLong(id_mukim),"style=width:auto class=disabled disabled"));
    			
    		if (flag_peruntukan.equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			}else 
			if(flag_peruntukan.equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}else{
				checkedAda = "checked";
				checkedTiada = "";
			}
			context.put("checkedAda",checkedAda);
			context.put("checkedTiada",checkedTiada);

    		model.setListPohon2(idpermohonan);
    		listPohon2 = model.getListPohon2();
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		//dropdown
    		context.put("selectProjekNegeri",HTML.SelectNegeriMampu("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px class=disabled disabled"));
    		
    		//data & list maklumat tanah
    		//model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
    		//listMaklumatTanah = model.getListMaklumatTanah();
    		//context.put("listMaklumatTanah", listMaklumatTanah);
    		//context.put("saiz_listTanah", listMaklumatTanah.size());
    		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
    		
    		
			//data
    		context.put("dataPermohonan2", listPohon2);
    		//context.put("listMaklumatTanah", listMaklumatTanah);
    		//context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	context.put("dataPermohonan", listPohon);
    		
    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "no");
    		
    		//screen
    		vm = screenUtama;
        	
        }//close updateFlagSah
    	
    	else 
    	if("cari".equals(submit)){
    		
    		//carian
    		ListCarianSPT(session,userIdNeg);			
    		listPageDepan = FrmPermohonanUPTData.getList();
			
    		//data
		    context.put("PermohonanListSPT", listPageDepan);
		    context.put("list_size", listPageDepan.size());
 
		    //get & set data
		    String status = getParam("status");		    
		    context.put("SelectStatusPPT",HTML.SelectStatusPPT("status",Utils.parseLong(status),"style=width:auto"));
    		context.put("carianPermohonan", getParam("no_permohonan").trim());
			context.put("carianTarikh", getParam("tarikh_permohonan").trim());
			context.put("carianStatusFail", getParam("carianStatusFail"));
			
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else{	
    		
    		listPageDepan = model.getListPemohon(userIdNeg);
    		defaultPage(listPageDepan);	
    		vm = listdepan;
    		
		}//close else

    		context.put("id_permohonan", idpermohonan);
    		context.put("userIdNeg", userIdNeg);
    		
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void doChangeidKementerianUPDATE(String idpermohonan,String userIdNeg) throws Exception{
		
		Vector senaraiSemak = new Vector();
		Vector listMaklumatTanah = new Vector();
		Vector alamatKementerian = new Vector();
		Vector listPohon = new Vector();
		Vector listPohon2 = new Vector();
		
		listPohon2.clear();
		listPohon.clear();
		alamatKementerian.clear();
		listMaklumatTanah.clear();
		senaraiSemak.clear();
		
		String checkedAda = "";
		String checkedTiada = "";
		String noLOT = "";
		String idpegawai = "";
		
		//onchange validation
		context.put("changeby","yes");
		
		String idEditKementerian = getParam("editKementerian");
		
		//senarai semak
		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
	    context.put("senaraiSemakan", senaraiSemak);
		
	    //data & list maklumat tanah
	    model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
		listMaklumatTanah = model.getListMaklumatTanah();
		
		alamatKementerian = model.getAlamatKementerian(idEditKementerian);
		String AK_negeri = "";
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";
		if(alamatKementerian.size()!=0){
			Hashtable AK = (Hashtable) alamatKementerian.get(0);
			AK_negeri = AK.get("id_negeri").toString();
    		alamat1 = AK.get("alamat1").toString();
    		alamat2 = AK.get("alamat2").toString();
    		alamat3 = AK.get("alamat3").toString();
    		poskod = AK.get("poskod").toString();
		}

		context.put("poskod", poskod);
    	context.put("alamat1", alamat1);
    	context.put("alamat2", alamat2);
    	context.put("alamat3", alamat3);	

 		
		//id dropdown
 		String idDaerah = getParam("editDaerah");
		String idAgensi = getParam("editAgensi");

		String idProjekNegeri = "";
		if(userIdNeg.equals("14")){
			idProjekNegeri = getParam("editProjekNegeri");
		}else{
			idProjekNegeri = userIdNeg;
		}

		model.setListPohon(idpermohonan);
		listPohon = model.getListPohon();
		String id_mukim = "";
		String status = "";
		if(listPohon.size()!=0){
			Hashtable h = (Hashtable) listPohon.get(0);
			id_mukim = h.get("id_mukim").toString();
			status = h.get("id_status").toString();
		}
		
		context.put("currentStatus", status);
		context.put("id_status", status);
		
		//validation jajahan
		if(idProjekNegeri.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
		//get and set data
		context.put("tujuan", getParam("edit_tujuan"));
		context.put("tarikhSurat", getParam("edit_tarikh_surat"));
		context.put("tarikhHendak", getParam("edit_tarikh_kehendaki"));
		context.put("rujKementerian", getParam("edit_rujukan_kementerian"));
		//context.put("tarikhPohon", getParam("edit_tarikh_permohonan"));
		context.put("no_rujukan_ptg", getParam("no_rujukan_ptg"));
		context.put("no_rujukan_ptd", getParam("no_rujukan_ptd"));
		context.put("no_rujukan_upt", getParam("no_rujukan_upt"));
		
		String sorJenisKodDaerah = getParam("sorJenisKodDaerah");
		context.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));
		
		//dropdown by
		if(idEditKementerian!=""){
			context.put("selectAgensi",HTML.SelectAgensiByKementerian("editAgensi",idEditKementerian,Utils.parseLong(idAgensi),"id=editAgensi style=width:500px"));
		}
		else{
			context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(idAgensi),"id=editAgensi style=width:500px"));	
		}
		if(idProjekNegeri!=""){
			context.put("selectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,idProjekNegeri,"editDaerah",Utils.parseLong(idDaerah),null,"style=width:300px"));
		}else{
			context.put("selectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"editDaerah",Utils.parseLong(idDaerah),null,"style=width:300px"));
		}
		if(idDaerah!=""){
			context.put("selectBandar",HTML.SelectMukimNoKodByDaerah(idDaerah,"editBandar",Utils.parseLong(id_mukim),"style=width:auto"));
		}else{
			context.put("selectBandar",HTML.SelectMukimNoKod("editBandar",Utils.parseLong(id_mukim),"style=width:auto"));
		}
		
		//dropdown
		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(idEditKementerian),null,"style=width:500px onChange=\"doChangeidKementerianUPDATE();\" "));
		context.put("selectNegeri",HTML.SelectNegeriMampu("editNegeri",Utils.parseLong(AK_negeri),"class=disabled disabled style=width:300px"));
		
		if(userIdNeg.equals("14")){
			context.put("selectProjekNegeri",HTML.SelectNegeriMampuKL("editProjekNegeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUPDATE();\" "));
    	}else{
    		context.put("selectProjekNegeri",HTML.SelectNegeriMampu("editProjekNegeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px disabled class=disabled onChange=\"doChangeidProjekNegeriUPDATE();\" "));
    	}	
		
		//radio button peruntukan
		if (getParam("flag_peruntukan").equals("1")){
			checkedAda = "checked";
			checkedTiada = "";
		} else if (getParam("flag_peruntukan").equals("2")){
			checkedAda = "";
			checkedTiada = "checked";
		}
		context.put("checkedAda",checkedAda);
		context.put("checkedTiada",checkedTiada);
		
		//data
		context.put("dataPermohonan2", listPohon2);
		//context.put("listMaklumatTanah", listMaklumatTanah);
		//context.put("saiz_listTanah", listMaklumatTanah.size());
		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
		
	    context.put("dataPermohonan", listPohon);
	    	
	    	//form validation
		context.put("semak", "yes");
		context.put("edit", "yes");
		
	}//close doChangeidKementerianUPDATE
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void doChangeidKementerian(String userIdNeg) throws Exception{
		
		Vector alamatKementerian = new Vector();
		alamatKementerian.clear();
		
		String checkedAda = "";
    	String checkedTiada = "";
    	
		String idKementerian = getParam("kementerian");

		alamatKementerian = model.getAlamatKementerian(idKementerian);    		
		String id_negeriKementerian = "";
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";   		
		if(alamatKementerian.size()!=0){
			Hashtable AK = (Hashtable) alamatKementerian.get(0);
			id_negeriKementerian = AK.get("id_negeri").toString();
			alamat1 = AK.get("alamat1").toString();
			alamat2 = AK.get("alamat2").toString();
			alamat3 = AK.get("alamat3").toString();
			poskod = AK.get("poskod").toString();
		}
		
		context.put("addPoskod", poskod);
		context.put("addAlamat1", alamat1);
		context.put("addAlamat2", alamat2);
		context.put("addAlamat3", alamat3);
		
		context.put("addTujuan", getParam("tujuan"));
		context.put("addTarikhSurat", getParam("tarikh_surat"));
		context.put("addTarikhHendak", getParam("tarikh_kehendaki"));
		context.put("addRujukan_kementerian", getParam("rujukan_kementerian"));
		//context.put("tarikhPohon", getParam("edit_tarikh_permohonan"));
		context.put("no_rujukan_ptg", getParam("no_rujukan_ptg"));
		context.put("no_rujukan_ptd", getParam("no_rujukan_ptd"));
		context.put("no_rujukan_upt", getParam("no_rujukan_upt"));
		
		String sorJenisKodDaerah = getParam("sorJenisKodDaerah");
		context.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));
		
		String idDaerah = getParam("daerah");
		
		String idProjekNegeri = "";
		if(userIdNeg.equals("14")){
			idProjekNegeri = getParam("projek_negeri");
		}else{
			idProjekNegeri = userIdNeg;
		}

		String idMukim = getParam("bandar");
		String idAgensi = getParam("agensi");
		
		//dropdown by
		if(idKementerian!=""){
			context.put("SelectAgensi",HTML.SelectAgensiByKementerian("agensi",idKementerian,Utils.parseLong(idAgensi),"id=agensi style=width:500px"));
		}else{
			context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),"id=agensi style=width:500px"));	
		}
		if(idProjekNegeri!=""){
			context.put("SelectDaerah",HTML.SelectDaerahByNegeriKOD(sorJenisKodDaerah,idProjekNegeri,"daerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukim();\""));
    	}else{
    		context.put("SelectDaerah",HTML.SelectDaerahKOD(sorJenisKodDaerah,"daerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukim();\""));
        }
		if(idDaerah!=""){
			context.put("SelectBandar",HTML.SelectMukimNoKodByDaerah(idDaerah,"bandar",Utils.parseLong(idMukim),"style=width:auto"));
		}else{
			context.put("SelectBandar",HTML.SelectMukimNoKod("bandar",Utils.parseLong(idMukim),"style=width:auto"));
		}
		
		//validation jajahan
		if(idProjekNegeri.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
		
		//13042011
		if(userIdNeg.equals("14") || userIdNeg.equals("15") || userIdNeg.equals("16")){
			context.put("project_negeri",HTML.SelectNegeriMampuKL("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidNegeri();\" "));
		}else{
			context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px disabled class=disabled onChange=\"doChangeidNegeri();\" "));
		}
		
		//dropdown
		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Utils.parseLong(id_negeriKementerian),"class=disabled disabled style=width:325px"));		
		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doChangeidKementerian();\" "));
		
		
		//radio button amaun peruntukan
		if (getParam("new_flag_peruntukan").equals("1")){
			checkedAda = "checked";
			checkedTiada = "";
		}else 
		if(getParam("new_flag_peruntukan").equals("2")){
			checkedAda = "";
			checkedTiada = "checked";
		}else{
			checkedAda = "checked";
			checkedTiada = "";
		}
		context.put("TEMPcheckedAda",checkedAda);
		context.put("TEMPcheckedTiada",checkedTiada);

		//form validation
		context.put("dataPermohonan", "");
		context.put("semak", "no");
		context.put("edit", "no");
		
		//currentdate--yati comment
		//context.put("currentDATE", getParam("txdTarikhPermohonan"));
		
		//reset data
		context.put("currentSuburusan", "");
		context.put("currentStatus", "");
		context.put("listMaklumatTanah", "");
		
		Vector listPohon2 = new Vector();
		Vector listPohon = new Vector();
		Vector listMaklumatTanah = new Vector();
		
		//data
		context.put("dataPermohonan2", listPohon2);
		//context.put("listMaklumatTanah", listMaklumatTanah);
		context.put("saiz_listTanah", 0);
	    context.put("dataPermohonan", listPohon);
	    	
	}//close doChangeidKementerian
	
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
	private String nameAndId(String userIdNeg) throws Exception{
		
		Vector dataNamaPengarah = new Vector();
		
		dataNamaPengarah.clear();
		
	    //GET NAMA PENGARAH DAN ID PENGARAH
	    String nama_pengarah = "";
	    String id_pengarah = "";
	    model.setNamaPengarah(userIdNeg);
	    dataNamaPengarah = model.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = (String)np.get("nama_pengarah");
	    	id_pengarah = (String)np.get("user_id");
	    }
	    
	    context.put("nama_pengarah",nama_pengarah);

	    return id_pengarah;
	    
	}//close nameAndId
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void sendEmail(String id_pengarah,String jenisSend,String nofail,String nama_projek,String tarikh_permohonan,String nama_kementerian) throws Exception{
		
		String tiadaNoFail = "";
		
    	Vector checkEmailPengarah = new Vector();
    	checkEmailPengarah.clear();
    	
		//check email (pengarah)
		checkEmailPengarah = myInfo.checkEmail(id_pengarah);
		String emelPengarah = "";
		if(checkEmailPengarah.size()!=0){
			Hashtable ceP = (Hashtable)checkEmailPengarah.get(0);
			emelPengarah = (String)ceP.get("EMEL");
		}

		EmailTester et = new EmailTester();
		
		//if(emelPengarah!="" ){
			et.setEmail("seksyen4",emelPengarah,"hantarUntukPengesahanDanUntukAgihan",tiadaNoFail,nama_projek,tarikh_permohonan,nama_kementerian);
		//}
		
		//jenis email
		// - hantar pengesahan (pt - pengarah)
		// - hantar untuk diagihankan
		// - hantar pengesahan mmk (pt - pengarah)
		
	}//close sendEmail
	
	@SuppressWarnings("unchecked")
	private String addSPT(HttpSession session,String userIdNeg) throws Exception{
		
	    	Hashtable h = new Hashtable();
	    
	    	h.put("suburusan", "51");
	    	
	    	h.put("no_rujukan_ptg", getParam("no_rujukan_ptg"));
	    	h.put("no_rujukan_ptd", getParam("no_rujukan_ptd"));
	    	h.put("no_rujukan_upt", getParam("no_rujukan_upt"));
	    	
		    h.put("kementerian", getParam("kementerian"));
		    h.put("agensi",getParam("agensi"));
		    h.put("flag_peruntukan", getParam("new_flag_peruntukan"));
		    h.put("flag_segera", "");
		    
		    h.put("daerah", getParam("daerah"));
		    h.put("tujuan", getParam("tujuan"));
		    h.put("rujukan_kementerian", getParam("rujukan_kementerian"));
		    h.put("tarikh_kehendaki", getParam("tarikh_kehendaki"));
		    h.put("tarikh_surat", getParam("tarikh_surat"));
		    
		    String idNegeri = userIdNeg;
		    if(userIdNeg.equals("14")){
		    	idNegeri = getParam("projek_negeri");
		    }
		    
	    	h.put("projek_negeri", idNegeri);
	    	
	    	h.put("txdTarikhPermohonanKjp", getParam("txdTarikhPermohonanKjp"));
	    	
	    	h.put("txdTarikhPermohonan", getParam(""));
	    	
	    	h.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));
	    	
	    	h.put("jenis_projek", getParam("sorJenisProjek"));	
	    	
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	
	    	h.put("txtTujuanBI", "");
	    	
	    	h.put("tarikh_lengkap", "");
	    	
	    	return FrmPermohonanUPTData.addSPT(h);
	  
	}//close add

	@SuppressWarnings("unchecked")
	private void add_maklumat_tanah(HttpSession session) throws Exception{
	    
			Hashtable h = new Hashtable();
			
    		h.put("sorJenisRizab", "");
	    	h.put("txtLain", "");
	    	h.put("txtNoWartaRizab", "");	    	
	    	h.put("txdTarikhWarta", "");
    		
	    	h.put("id_permohonan", getParam("id_permohonan"));
	    	h.put("negeri", getParam("id_existNegeri"));
	    	h.put("daerah", getParam("id_existDaerah"));
	    	h.put("mukim", getParam("mukim"));
	    	h.put("txtseksyen", getParam("txtseksyen"));
	    	h.put("txtnolot", getParam("txtnolot"));
	    	h.put("txtnopt", getParam("txtnopt"));
	    	h.put("catatan", getParam("txtCatatan"));
	    	
	    	h.put("daerahpenggawa", getParam("daerahpenggawa"));
	    	
	    	h.put("jenisHakMilik", getParam("socJenisHakmilik"));
	    	h.put("no_hakmilik", getParam("txtNoHakmilik"));
	    	h.put("lot", getParam("lot"));	    	
	    	h.put("luas", "");
	    	h.put("luas_lot", "");
	    	h.put("anggaran_luas", "");
	    	h.put("socKategoriTanah", "");
	    	
//	    	PPT-03 Penambahan Strata
	    	myLogger.info("No. Bangunan = "+getParam("txtNoBangunan"));
	    	h.put("txtNoBangunan", getParam("txtNoBangunan"));
	    	h.put("txtNoTingkat", getParam("txtNoTingkat"));
	    	h.put("txtNoPetak", getParam("txtNoPetak"));
	    	
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	
	    	FrmPermohonanUPTData.add_maklumat_tanah(h);
	    	myLogger.info("NO_LOT--"+h.put("txtnolot", getParam("txtnolot")));
	    
	   }//close add_maklumat_tanah
	
	 private void ListCarianSPT(HttpSession session,String userIdNeg) throws Exception{
    	
			String carianPermohonan = getParam("no_permohonan");
			String carianTarikh = getParam("tarikh_permohonan");
			String carianStatus = getParam("status");
			String carianstatusFail = getParam("carianStatusFail");
			
			FrmPermohonanUPTData.setList(carianPermohonan,carianTarikh,carianStatus,carianstatusFail,userIdNeg);
      
	 }//close listcarian

	@SuppressWarnings("unchecked")
	private void update(HttpSession session,String userIdNeg) throws Exception{
		
		 	Hashtable h = new Hashtable();
	    
	    	h.put("id_senaraisemak", getParam("id_senaraiSemak"));	    
	    	h.put("id_urusan", "51");
	    	h.put("id_permohonan", getParam("id_permohonan"));
	    	h.put("id_fail", getParam("id_fail"));
	    	
	    	//checkbutton seksyen 4
	    	if(getParam("cbsemaks10")==null){h.put("semak10", "0");}else{h.put("semak10", getParam("cbsemaks10"));}
	    	if(getParam("cbsemaks20")==null){h.put("semak20", "0");}else{h.put("semak20", getParam("cbsemaks20"));}
	  
	    	//checkbutton seksyen 8
	    	if(getParam("cbsemaks1")==null){h.put("semak1", "0");}else{h.put("semak1", getParam("cbsemaks1"));}
	    	if(getParam("cbsemaks2")==null){h.put("semak2", "0");}else{h.put("semak2", getParam("cbsemaks2"));}
	    	if(getParam("cbsemaks3")==null){h.put("semak3", "0");}else{h.put("semak3", getParam("cbsemaks3"));}
	    	if(getParam("cbsemaks4")==null){h.put("semak4", "0");}else{h.put("semak4", getParam("cbsemaks4"));}
	    	if(getParam("cbsemaks5")==null){h.put("semak5", "0");}else{h.put("semak5", getParam("cbsemaks5"));}
	    	if(getParam("cbsemaks6")==null){h.put("semak6", "0");}else{h.put("semak6", getParam("cbsemaks6"));}
	    	if(getParam("cbsemaks7")==null){h.put("semak7", "0");}else{h.put("semak7", getParam("cbsemaks7"));}
	    	if(getParam("cbsemaks8")==null){h.put("semak8", "0");}else{h.put("semak8", getParam("cbsemaks8"));}
 
	    	h.put("txdTarikhPermohonan", getParam("edit_tarikh_permohonan"));
	    	h.put("txdTarikhPermohonanKjp", getParam("txdTarikhPermohonanKjp"));
	    	h.put("tujuan", getParam("edit_tujuan"));
	    	h.put("rujukan_kementerian", getParam("edit_rujukan_kementerian"));
	    	h.put("tarikh_hendak", getParam("edit_tarikh_kehendaki"));
	    	h.put("tarikh_surat", getParam("edit_tarikh_surat"));
	    	h.put("flag_peruntukan", getParam("flag_peruntukan"));
	    	h.put("flag_segera", getParam("flag_segera"));
	    	h.put("daerah", getParam("editDaerah"));
	    	
	    	String idNegeri = userIdNeg;
		    if(userIdNeg.equals("14")){
		    	idNegeri = getParam("editProjekNegeri");
		    }
		    
	    	h.put("projeknegeri", idNegeri);
	    	h.put("agensi", getParam("editAgensi"));
	    	h.put("kementerian", getParam("editKementerian"));
	    	
	    	h.put("no_rujukan_ptg", getParam("no_rujukan_ptg"));
	    	h.put("no_rujukan_ptd", getParam("no_rujukan_ptd"));
	    	h.put("no_rujukan_upt", getParam("no_rujukan_upt"));
	    	
	    	h.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));
	    	
	    	h.put("jenis_projek", getParam("sorJenisProjek"));	
	    	
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	h.put("jumlahHakmilik",  "");
	    	h.put("txtTujuanBI",  "");
	    	h.put("tarikh_lengkap",  "");
	    	
	    	FrmPermohonanUPTData.update(h);
	    
	  }
	 
	@SuppressWarnings("unchecked")
	private void updateMaklumatTanah(HttpSession session) throws Exception{
		 	
		 	Hashtable i = new Hashtable();
		 	
		    i.put("id_hakmilik", getParam("id_hakmilik"));
		    i.put("editNegeri", getParam("id_existNegeri"));
		    i.put("editDaerah", getParam("id_existDaerah"));
		    i.put("editMukim", getParam("editMukim"));
		    i.put("txtseksyen", getParam("txtseksyen"));
		    i.put("catatan", getParam("txtCatatan"));
		    i.put("txtnolot", getParam("txtnolot"));
		    i.put("txtnopt", getParam("txtnopt"));
		    
		    i.put("sorJenisRizab", "");
	    	i.put("txtLain", "");
	    	i.put("txtNoWartaRizab", "");	    	
	    	i.put("txdTarikhWarta", "");
	    	
		    i.put("daerahpenggawa", getParam("daerahpenggawa"));
		    
		    i.put("editJenisHakmilik", getParam("socJenisHakmilik"));
	    	i.put("edit_no_hakmilik", getParam("txtNoHakmilik"));
	    	
	    	// PPT-03 Penambahan Strata
	    	i.put("no_bangunan", getParam("txtNoBangunan"));
	    	i.put("no_petak", getParam("txtNoPetak"));
	    	i.put("no_tingkat", getParam("txtNoTingkat"));
//	    	myLogger.info("no_bangunan :" +getParam("txtNoBangunan"));
	    	
		    i.put("editLot", getParam("editLot"));	
		    i.put("editLuas", "");
		    i.put("edit_luas_lot", "");
		    i.put("edit_anggaran_luas", "");
		    i.put("socKategoriTanah", "");
		    
		    i.put("id_user", session.getAttribute("_ekptg_user_id"));
		    
		    FrmPermohonanUPTData.updateMaklumatTanah(i);
	 }
	 
	 private void deleteMaklumatTanah(HttpSession session) throws Exception{
		 	String id_hakmilik = getParam("id_hakmilik");	    
		 	FrmPermohonanUPTData.deleteMaklumatTanah(id_hakmilik);
	 }
	
	@SuppressWarnings("unchecked")
	private void updateFlagSah(HttpSession session) throws Exception{
			 
		Hashtable i = new Hashtable();

		i.put("id_permohonan", getParam("id_permohonan"));
		i.put("id_user", session.getAttribute("_ekptg_user_id"));
			    
		FrmPermohonanUPTData.updateFlagSah(i);
	}
	 
	@SuppressWarnings("unchecked")
	private void sah(HttpSession session,String id_suburusanstatusfailppt,String userIdNegeri) throws Exception{
	    
		 	Hashtable h = new Hashtable();
		 	h.put("id_permohonan", getParam("id_permohonan"));
		 	h.put("id_user", session.getAttribute("_ekptg_user_id"));
		 	h.put("id_fail", getParam("id_fail"));
		 	h.put("id_kementerian", getParam("idKementerianA"));
		 	h.put("id_negeri", getParam("idNegeriA"));

		 	//data header
     		model.setListPohon(getParam("id_permohonan"));
     		Vector listPohon = model.getListPohon();
     		String idDaerah = "";
     		String flag_jenis_kod_daerah = "";
    		if(listPohon.size()!=0){
    			Hashtable x = (Hashtable) listPohon.get(0);
    			idDaerah = x.get("idDaerah").toString();
    			flag_jenis_kod_daerah = (String)x.get("flag_jenis_kod_daerah");
    		}   	
    		
		 	//data header 2
     		model.setListPohon2(getParam("id_permohonan"));
     		Vector listPohon2 = model.getListPohon2();
     		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		h.put("id_projekNegeri", id_projekNegeri);
    		h.put("idDaerah", idDaerah);
    		h.put("id_negeriuser", userIdNegeri);
    		
 	    	FrmPermohonanUPTData.sahkan(h,id_suburusanstatusfailppt,"1445",flag_jenis_kod_daerah);  
 	    	
	 }//close sahkan
	 
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
	        			"(id_Dokumen,id_permohonan,nama_Fail,jenis_Mime,content,tajuk,keterangan) " + "values(?,?,?,?,?,?,?)");
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
			this.context.put("PermohonanListSPT",paging.getPage(page));
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
	 
	@SuppressWarnings("unchecked")
	public void defaultPage(Vector listPageDepan) throws Exception {
		 
		context.put("PermohonanListSPT", listPageDepan);
 		context.put("list_size", listPageDepan.size());

 		context.put("SelectStatusPPT",HTML.SelectStatusPPT("status",null,"style=width:auto"));
 		
 		//reset textfield
		context.put("carianPermohonan", "");
		context.put("carianTarikh", "");
		context.put("carianStatusFail", "");
		
	 }//defaultpage	
	 
	@SuppressWarnings("unchecked")
	public void maklumatPageHakMilik(HttpSession session,String idpermohonan,String noLOT) throws Exception {
		 
		Vector listPohon = null;
		Vector listPohon2 = null;
		Vector listMaklumatTanah = null;
		String idpegawai = "";
		
		//data & list maklumat tanah
 		//model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 		//listMaklumatTanah = model.getListMaklumatTanah();
 		//context.put("listMaklumatTanah", listMaklumatTanah);
 		//context.put("saiz_listTanah", listMaklumatTanah.size());
		
		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
		
 		
		model.setListPohon(idpermohonan);
		listPohon = model.getListPohon();
		String nama_daerah = "";
		String id_daerah = "";
		if(listPohon.size()!=0){
			Hashtable i = (Hashtable) listPohon.get(0);
			nama_daerah = i.get("daerah").toString();
			id_daerah = i.get("idDaerah").toString();
    	}
		context.put("existDaerah", nama_daerah);
		context.put("id_existDaerah", id_daerah);
		
		model.setListPohon2(idpermohonan);
		listPohon2 = model.getListPohon2();
		String id_projekNegeri = "";
		String nama_projekNegeri = "";
		if(listPohon2.size()!=0){
			Hashtable h = (Hashtable) listPohon2.get(0);
			id_projekNegeri = h.get("idProjekNegeri").toString();
			nama_projekNegeri = h.get("projek_negeri").toString();
		}  		
		context.put("id_existNegeri", id_projekNegeri);
		context.put("existNegeri", nama_projekNegeri);
		
		//validation jajahan
		if(id_projekNegeri.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
		//dropdown
		if(id_projekNegeri.equals("10")){
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",null,"id=socJenisHakmilik style=width:auto"));   	
		}else{
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",null,"id=socJenisHakmilik style=width:auto"));   	
		}
		
		context.put("SelectLuas",HTML.SelectLuas("luas",null,"style=width:250px"));
		context.put("SelectLot", HTML.SelectUnitPT("lot",null,"style=width:auto"));
		
		//untuk kelantan shj
		context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("daerahpenggawa",null,null,"style=width:274px"));
		
		//dropdown by
		if(id_daerah!=""){
			context.put("SelectMukim",HTML.SelectMukimNoKodByDaerah(id_daerah,"mukim",null,"style=width:auto"));
		}else{
			context.put("SelectMukim",HTML.SelectMukimNoKod("mukim",null,"style=width:auto"));
		}
	
	}//maklumatPageHakMilik	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void dataSemak(HttpSession session,String idpermohonan,String noLOT) throws Exception {
		 
		Vector senaraiSemak = null;
		Vector listPohon = null;
		Vector listPohon2 = null;
		Vector listDokumen = null;
		Vector listMaklumatTanah = null;
		
		String checkedAda = "";
    	String checkedTiada = "";
    	String checkedTidak = "";
    	String checkedYa = "";
    	String idpegawai = "";
    	
		//senarai semak
		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		context.put("senaraiSemakan", senaraiSemak);
		   
		//data header
 		model.setListPohon(idpermohonan);
 		listPohon = model.getListPohon();
 		
 		//data header 2
 		model.setListPohon2(idpermohonan);
 		listPohon2 = model.getListPohon2();
 		
 		//dokumen (blob)
 		/*
 		model.setListDokumen(idpermohonan);
 		listDokumen = model.getListDokumen();
		context.put("listDokumen", listDokumen);
		context.put("listD_size", listDokumen.size());	
		*/	
 		
		//data & list maklumat tanah
		/*
 		model.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 		listMaklumatTanah = model.getListMaklumatTanah();		
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 		String nama2Mukim = "";
 		if(listMaklumatTanah.size()!=0){
 			Hashtable lmt = (Hashtable)listMaklumatTanah.get(0);
 			nama2Mukim = (String)lmt.get("nama2Mukim");
 		}
*/
		String nama2Mukim = "";
		Hashtable senarai_mukim_lot = model.getListMukimLot(idpermohonan, noLOT, idpegawai, 0,0);
		if (senarai_mukim_lot.size() != 0) {
			nama2Mukim = senarai_mukim_lot.get("nama2Mukim").toString();
		}		
		//context.put("listMaklumatTanah", listHakmilik);
		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
		context.put("nama2Mukim", nama2Mukim);
 		
 		String flag_jenis_kod_daerah = "";
 		String flagStatusOnline = "";
 		String catatan_status_online = "";
		String currentStatus = "";
		String currentSuburusan = "";
		String idAGENSI = "";
		String idNegeri = "";
		String idKementerian = "";
		String idDaerah = "";
		String idMukim = "";   		
		String flag_peruntukan = "";
		String flag_segera = "";
		String id_fail = "";
		if(listPohon.size()!=0){
			Hashtable h = (Hashtable) listPohon.get(0);
			currentStatus = h.get("id_status").toString();
			currentSuburusan = h.get("idSuburusan").toString();  
			idAGENSI = h.get("idAgensi").toString();
			idNegeri = h.get("idNegeri").toString();
			idKementerian = h.get("idKementerian").toString();
			idDaerah = h.get("idDaerah").toString();
			idMukim = h.get("id_mukim").toString();
			flag_peruntukan = h.get("flag_peruntukan").toString();
			flag_segera = h.get("flag_segera").toString();
			id_fail = h.get("idFail").toString();
			flagStatusOnline = (String)h.get("flag_status_online");
			catatan_status_online = (String)h.get("catatan_status_online");
			flag_jenis_kod_daerah = (String)h.get("flag_jenis_kod_daerah");
		}   		
		context.put("currentSuburusan", currentSuburusan);
		context.put("currentStatus", currentStatus);
		context.put("id_status", currentStatus);
		context.put("flagStatusOnline", flagStatusOnline);
		context.put("catatan_status_online", catatan_status_online);
		
		//id
		context.put("id_fail",id_fail);
		
		//dropdown by
    	if(idAGENSI!=""){
    		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Long.parseLong(idAGENSI),"style=width:500px class=disabled disabled"));
    	}else{
    		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",null,"style=width:500px class=disabled disabled"));
    	}

    	//dropdown
		context.put("selectNegeri",HTML.SelectNegeriMampu("editNegeri",Utils.parseLong(idNegeri),"style=width:300px class=disabled disabled"));
		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(idKementerian),"style=width:500px class=disabled disabled"));
		context.put("selectDaerah",HTML.SelectDaerahKOD(flag_jenis_kod_daerah,"editDaerah",Utils.parseLong(idDaerah),"style=width:300px class=disabled disabled",null));
		context.put("selectBandar",HTML.SelectMukimNoKod("editBandar",Utils.parseLong(idMukim),"style=width:auto class=disabled disabled"));
		
		String id_projekNegeri = "";
		if(listPohon2.size()!=0){
			Hashtable i = (Hashtable) listPohon2.get(0);
			id_projekNegeri = i.get("idProjekNegeri").toString();
		}
		context.put("negeriIntegrasi",id_projekNegeri);
		//validation jajahan
		if(id_projekNegeri.equals("3")){
			context.put("showJajahan","yes");
		}else{
			context.put("showJajahan","no");
		}
		
		context.put("selectProjekNegeri",HTML.SelectNegeriMampu("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px class=disabled disabled"));
		
		
		if (flag_peruntukan.equals("1")){
			checkedAda = "checked";
			checkedTiada = "";
		}else if(flag_peruntukan.equals("2")){
			checkedAda = "";
			checkedTiada = "checked";
		}else{
			checkedAda = "checked";
			checkedTiada = "";
		}
		context.put("checkedAda",checkedAda);
		context.put("checkedTiada",checkedTiada);
		
		if (flag_segera.equals("1")){
			checkedYa = "checked";
			checkedTidak = "";
		}else if (flag_segera.equals("2")){
			checkedYa = "";
			checkedTidak = "checked";
		}else{
			checkedYa = "checked";
			checkedTidak = "";
		}			
		context.put("checkedYa",checkedYa);
		context.put("checkedTidak",checkedTidak);
		
		//data
		context.put("dataPermohonan2", listPohon2);
		context.put("dataPermohonan", listPohon);
		
	}//dataSemak	
	
	@SuppressWarnings("unchecked")
	private void hapusDokumen(HttpSession session) throws Exception{
	    
		Hashtable h = new Hashtable();
		
		String id_dokumen = getParam("iddokumen");
		
		h.put("id_dokumen", id_dokumen);
		
		FrmPermohonanUPTData.hapusDokumen(h);
		
	}//close hapusdokumen
	
	
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
			
			//dropdown
			if(id_negeriprojek.equals("10")){
				
				context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik style=width:auto"));   	
			}else{
				context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik style=width:auto"));   	
			}
			
			context.put("SelectLuas",HTML.SelectLuas("luas",null,"style=width:250px"));
			context.put("SelectLot", HTML.SelectUnitPT("lot",Utils.parseLong(id_lot),"style=width:auto"));
			
			//untuk kelantan shj
			context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("daerahpenggawa",Utils.parseLong(id_daerahpenggawa),null,"style=width:274px"));
			
			//dropdown by
			if(id_daerah!=""){
				context.put("SelectMukim",HTML.SelectMukimNoKodByDaerah(id_daerah,"mukim",Utils.parseLong(id_mukim),"style=width:auto"));
			}else{
				context.put("SelectMukim",HTML.SelectMukimNoKod("mukim",null,"style=width:auto"));
			}
			
			//untuk kelantan shj
			
			/*
			context.put("SelectDaerahPenggawa",HTML.SelectDaerahPenggawa("socDaerahPenggawa",Utils.parseLong(id_daerahpenggawa),null," "+mode+" style=width:274px"));
			
			//dropdown
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik "+mode+" style=width:auto onchange=doOnchange()"));   	
			context.put("selectKategoriTanah",HTML.SelectKategoriTanah("socKategoriTanah",Utils.parseLong(id_kategoritanah),"id=socKategoriTanah "+mode+" style=width:auto",null));
			context.put("SelectLot", HTML.SelectLot("socKodLot",Utils.parseLong(id_lot),"style=width:auto "+mode+" "));
			
			//dropdown unit luas
			context.put("selectUnitLuasLot",HTML.SelectLuas("socUnitLuasLot",Utils.parseLong(id_luaslot),"style=width:250px "+mode+" id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
			context.put("selectUnitLuasAmbil",HTML.SelectLuas("socUnitLuasAmbil",Utils.parseLong(id_unitluasambil),"style=width:250px "+mode+" id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
			
			//dropdown by
			if(id_daerah!=""){
				context.put("SelectMukim",HTML.SelectMukimByDaerah(id_daerah,"socMukim",Utils.parseLong(id_mukim),"style=width:auto "+mode+""));
			}else{
				context.put("SelectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(id_mukim),"style=width:auto "+mode+""));
			}*/
			
		}//close dataHakmilik
		
		 public void checkFieldWujud(String table_name,String column_name,String data_type,Db db)  throws Exception {
			  
			  	int total = 0;
			  	String sql="";
			  	ResultSet rs = null;
				try {
					sql = " SELECT COUNT(*) as total FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"+table_name+"' AND COLUMN_NAME = '"+column_name+"' ";	
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
					rs = db.getStatement().executeQuery(sql); 			
					//ALTER TABLE supplier ADD supplier_name varchar2(50);				
				}
				
		  }
	
}//close class
