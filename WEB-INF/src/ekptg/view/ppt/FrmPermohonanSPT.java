package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import ekptg.model.ppt.SPTDaftar;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmPermohonanSPT extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPermohonanSPT.class);
	
	//model
	SPTDaftar model = new SPTDaftar();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();

		//command for paging
    	String action = getParam("action");
    	
    	String vm = "";
    	
    	//reset data
    	String checkedAda = "";
    	String checkedTiada = "";
    	String checkedTidak = "";
    	String checkedYa = "";
    	context.put("semak", "");
		context.put("changeby","no");
    	
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
    	
    	//Vector clear
    	listDokumen.clear();
    	listPageDepan.clear();
    	listPohon.clear();
    	listPohon2.clear();
    	listAgensi.clear();
    	listMaklumatTanah.clear();
    	dataMaklumatTanah.clear();
    	senaraiSemak.clear();
    	
    	//screen jsp
    	String listdepan = "app/ppt/frmKJPSemakan.jsp";
    	String screenUtama = "app/ppt/frmSPTDaftar.jsp";
    	String screenTanah = "app/ppt/frmSPTHakmilikTambah.jsp";
    	String screenDokumen = "app/ppt/frmSPTDocTambah.jsp";
    	
    	//default list
    	listPageDepan = model.getListPemohon();
    	
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
    	
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if ("simpan".equals(submit)) {
		     
    		if (doPost.equals("true")) {
    			//simpan data
        		addSPT(session);
     		}
  
    		//form validation
    		context.put("semak", "yes");
    		
    		//default list
    		listPageDepan = model.getListPemohon();
    		defaultPage(listPageDepan);	
    		
    		//screen
		    vm = listdepan;
		    
		}//close simpan
    	
    	else 
    	if ("doChangeidMukimUPDATE".equals(submit)) {
    		
    		//onchange validation
    		context.put("changeby","yes");
    		
    		String idpermohonan = getParam("id_permohonan");
    		context.put("id_permohonan", idpermohonan);
    		
    		//info header
    		model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		
    		//data & list maklumat tanah
    		model.setListMaklumatTanah(idpermohonan);
    		listMaklumatTanah = model.getListMaklumatTanah();
    		
    		//senarai semak
    		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		    context.put("senaraiSemakan", senaraiSemak);
    		
    		//get and post all field content
    		context.put("poskod", getParam("edit_poskod"));
        	context.put("alamat1", getParam("edit_alamat1"));
        	context.put("alamat2", getParam("edit_alamat2"));
        	context.put("alamat3", getParam("edit_alamat3"));
        	context.put("tujuan", getParam("edit_tujuan"));
    		context.put("tarikhSurat", getParam("edit_tarikh_surat"));
    		context.put("tarikhHendak", getParam("edit_tarikh_kehendaki"));
    		context.put("rujKementerian", getParam("edit_rujukan_kementerian"));
    		context.put("tarikhPohon", getParam("edit_tarikh_permohonan"));
    		
    		//get id dropdown
        	String idEditProjekNegeri = getParam("editProjekNegeri");	
    		String idNegeri = getParam("editNegeri");
    		String idAgensi = getParam("editAgensi");
    		String idKementerian = getParam("editKementerian");
    		String idDaerah = getParam("editDaerah");
    		
    		//dropdown by
    		if(idDaerah!=""){
    			context.put("selectBandar",HTML.SelectMukimByDaerah(idDaerah,"editBandar",null,"id=editBandar style=width:300px"));
    		}else{
    			context.put("selectBandar",HTML.SelectMukim("editBandar",null,"id=editBandar style=width:300px"));
    		}
      		if(idKementerian!=""){
      			context.put("selectAgensi",HTML.SelectAgensiByKementerian("editAgensi",idKementerian,Utils.parseLong(idAgensi),"style=width:500px"));
        	}else{
        		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(idAgensi),null,"style=width:500px"));
      		}
    		if(idEditProjekNegeri!=""){
    			context.put("selectDaerah",HTML.SelectDaerahByNegeri(idEditProjekNegeri,"editDaerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
        	}else{
    			context.put("selectDaerah",HTML.SelectDaerah("editDaerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
        	}
    		
    		//dropdown
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doChangeidKementerianUPDATE();\" "));
    		context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(idNegeri),null,"style=width:300px onChange=\"doChangeidNegeriUPDATE();\" "));
    		context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(idEditProjekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUPDATE();\" "));
    		  		
    		//radio button peruntukan
    		if(getParam("flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			}
    		else 
			if(getParam("flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			else{
				checkedAda = "checked";
				checkedTiada = "";
			}
			context.put("checkedAda",checkedAda);
			context.put("checkedTiada",checkedTiada);
    		
			
			//radio button peruntukan segera
			if(getParam("flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			}
			else 
			if(getParam("flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			else{
				checkedYa = "checked";
				checkedTidak = "";
			}
			context.put("checkedYa",checkedYa);
			context.put("checkedTidak",checkedTidak);
			
			//get current date
    		String now = "";
    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    		context.put("currentDATE",now);
    		
    		//data
    		context.put("dataPermohonan2", "");
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	context.put("dataPermohonan", "");
   	    	
   	    	//form validation
    		context.put("semak", "yes");
    		context.put("edit", "yes");
    		
    		//screen
	        vm = screenUtama;
	        
   	    }//dochangeidmukimupdate
    	
    	else 
    	if("addMaklumatTanah".equals(submit))
    	{
    		
    		String id_permohonan = getParam("id_permohonan");
    		context.put("id_permohonan", id_permohonan);
    		
    		if (doPost.equals("true")) {
    			//simpan data
        		add_maklumat_tanah(session);
     		}

    		//data maklumat tanah
    		model.setListMaklumatTanah(id_permohonan);
    		listMaklumatTanah = model.getListMaklumatTanah();
    		
    		model.setListPohon2(id_permohonan);
    		listPohon2 = model.getListPohon2();
    		
    		model.setListPohon(id_permohonan);
    		listPohon = model.getListPohon();
    		
    		//dokumen (blob)
     		model.setListDokumen(id_permohonan);
     		listDokumen = model.getListDokumen();
    		context.put("listDokumen", listDokumen);
    		context.put("listD_size", listDokumen.size());
    		
    		String currentStatus = "";
    		String currentSuburusan = "";
    		String idAGENSI = "";
    		String idNegeri = "";
    		String idKementerian = "";
    		String idSuburusan = "";
    		String idDaerah = "";
    		String idMukim = "";   		
    		String flag_peruntukan = "";
    		String flag_segera = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			currentStatus = h.get("id_status").toString();
    			currentSuburusan = h.get("idSuburusan").toString();  
    			idAGENSI = h.get("idAgensi").toString();
    			idNegeri = h.get("idNegeri").toString();
    			idKementerian = h.get("idKementerian").toString();
    			idSuburusan = h.get("idSuburusan").toString();
    			idDaerah = h.get("idDaerah").toString();
    			idMukim = h.get("id_mukim").toString();
    			flag_peruntukan = h.get("flag_peruntukan").toString();
    			flag_segera = h.get("flag_segera").toString();
    		}   		
    		context.put("currentSuburusan", currentSuburusan);
    		context.put("currentStatus", currentStatus);
    		
    		//id
    		context.put("id_suburusan",idSuburusan);
    		
    		//dropdown by
        	if(idAGENSI!=""){
        		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Long.parseLong(idAGENSI),"style=width:500px class=disabled disabled"));
        	}else{
        		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",null,"style=width:500px class=disabled disabled"));
        	}
	
        	//dropdown
    		context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(idNegeri),"style=width:300px class=disabled disabled"));
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(idKementerian),"style=width:500px class=disabled disabled"));
    		context.put("selectDaerah",HTML.SelectDaerah("editDaerah",Utils.parseLong(idDaerah),"style=width:300px class=disabled disabled"));
    		context.put("selectBandar",HTML.SelectMukim("editBandar",Utils.parseLong(idMukim),"style=width:300px class=disabled disabled"));
    		
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px class=disabled disabled"));
    		
    		
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
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	context.put("dataPermohonan", listPohon);

    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "no");
    		
    		//screen
    		vm = screenUtama;
    		
    	}//close add maklumat tanah
    	
    	else 
    	if("doChangeidMukim".equals(submit)) {
    		
    		//Get and post all field content
    		context.put("addTujuan", getParam("tujuan"));
    		context.put("addPoskod", getParam("poskod"));
    		context.put("addAlamat1", getParam("alamat1"));
    		context.put("addAlamat2", getParam("alamat2"));
    		context.put("addAlamat3", getParam("alamat3"));
    		context.put("addTarikhSurat", getParam("tarikh_surat"));
    		context.put("addTarikhHendak", getParam("tarikh_kehendaki"));
    		context.put("addRujukan_kementerian", getParam("rujukan_kementerian"));
    		
    		//get id dropdown
    		//String SuburusanUPT = getParam("socSuburusan");
    		String idKementerian = getParam("kementerian");
    		String idAgensi = getParam("agensi");
    		String idDaerah = getParam("daerah");
    		String idNegeri = getParam("negeri");
    		String idProjekNegeri = getParam("projek_negeri");
    		
    		//dropdown by
    		if(idDaerah!=""){
    			context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"bandar",null,"style=width:300px id=bandar"));
    		}else{
    			context.put("SelectBandar",HTML.SelectMukim("bandar",null,"style=width:300px id=bandar"));
    		}
    		if(idKementerian!=""){
    			context.put("SelectAgensi",HTML.SelectAgensiByKementerian("agensi",idKementerian,Utils.parseLong(idAgensi),"style=width:500px"));
    		}else{
    			context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),"style=width:500px"));	
    		}
    		if(idNegeri!=""){
    			context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idProjekNegeri,"daerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukim();\""));
    		}else{
    			context.put("SelectDaerah",HTML.SelectDaerah("daerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukim();\""));
    		}
    		
    		//dropdown   		
    		//context.put("SelectSuburusanUPT",HTML.SelectSuburusanUPT("socSuburusan",Utils.parseLong(SuburusanUPT),"style=width:400px"));
    		context.put("SelectNegeri",HTML.SelectNegeri("negeri",Utils.parseLong(idNegeri),"style=width:325px",null));
    		context.put("project_negeri",HTML.SelectNegeri("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidNegeri();\" "));
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doChangeidKementerian();\" "));
    		
    		//radio button flag peruntukan
    		if(getParam("new_flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			}
    		else 
			if(getParam("new_flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			else{
				checkedAda = "checked";
				checkedTiada = "";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    		
			//radio button flag segera
			if(getParam("new_flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} 
			else if(getParam("new_flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			else{
				checkedYa = "checked";
				checkedTidak = "";
			}			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
    		
    		//reset
   	    	context.put("dataPermohonan", "");
   	    	context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		context.put("listMaklumatTanah", "");
    		
    		//currentdate
    		String now = "";
    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    		context.put("currentDATE", now);
    		
   	    	//form validation
    		context.put("semak", "no");
    		context.put("edit", "no");
    		
    		//data
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    		//screen
	        vm = screenUtama;
	        
   	    }//close do Change idMukim 
    	
    	else 
    	if("edit_maklumat".equals(submit)){
    		
    		String idHakmilik = getParam("id_hakmilik");
    		context.put("id_hakmilik", idHakmilik);
    		
    		String idpermohonan = getParam("id_permohonan");
    		context.put("id_permohonan", idpermohonan);
    		
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
    			context.put("selectMukim",HTML.SelectMukimByDaerah(id_daerah,"MKMukim",Utils.parseLong(id_mukim),"style=width:250px class=disabled disabled"));
    		}else{
    			context.put("selectMukim",HTML.SelectMukim("MKMukim",Utils.parseLong(id_mukim),"style=width:250px class=disabled disabled"));
        	}
    		
    		//dropdown
    		context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("MKJenisHakmilik",Utils.parseLong(id_jenishakmilik),"class=disabled style=width:auto disabled"));
    		context.put("selectLuas",HTML.SelectLuas("MKLuas",Utils.parseLong(id_luaslot),"style=width:250px class=disabled disabled"));
    		context.put("selectLot", HTML.SelectLot("MKLot",Utils.parseLong(id_lot),"class=disabled disabled style=width:167px"));
    		
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
  	      	
  	      	String idpermohonan = getParam("id_permohonan");
  	      	context.put("id_permohonan", idpermohonan);
  		
  	      	//data header
  			model.setListPohon(idpermohonan);
  			listPohon = model.getListPohon();
  		
  			//data header 2
  			model.setListPohon2(idpermohonan);
  			listPohon2 = model.getListPohon2();
  		
  			//data & list maklumat tanah
  			model.setListMaklumatTanah(idpermohonan);
  			listMaklumatTanah = model.getListMaklumatTanah();
  		
  			String id_negeri = "";
  			String id_kementerian = "";
  			String id_agensi = "";
  			//String id_suburusan = "";
  			String id_daerah = "";			
  			if(listPohon.size()!=0){
  				Hashtable h = (Hashtable) listPohon.get(0);
  				id_negeri = h.get("idNegeri").toString();
  				id_kementerian = h.get("idKementerian").toString();
  				id_agensi = h.get("idAgensi").toString();
  				//id_suburusan = h.get("idSuburusan").toString();
  				id_daerah = h.get("idDaerah").toString();
  			}
  			
  			//dropdown
  			context.put("project_negeri",HTML.SelectNegeri("projek_negeri")); 		
  			context.put("SelectDaerah",HTML.SelectDaerah("daerah"));
  			context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(id_negeri),"disabled class=disabled"));
  			context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),"disabled class=disabled"));
  			context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(id_agensi),"disabled class=disabled"));
  			//context.put("selectSuburusan",HTML.SelectSuburusanUPT("editSuburusan",Utils.parseLong(id_suburusan),"disabled  class=disabled style=width:400px",""));
  			context.put("selectDaerah",HTML.SelectDaerah("editDaerah",Utils.parseLong(id_daerah),"disabled class=disabled"));
  		
  			String id_projekNegeri = "";
  			if(listPohon2.size()!=0){
  				Hashtable i = (Hashtable) listPohon2.get(0);
  				id_projekNegeri = i.get("idProjekNegeri").toString();
  			}
  			
  			//dropdown
  			context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(id_projekNegeri),"disabled class=disabled"));
  		
  			//data
		    context.put("dataPermohonan2", listPohon2);
  			context.put("dataPermohonan", listPohon);
  			context.put("listMaklumatTanah", listMaklumatTanah);
  			context.put("saiz_listTanah", listMaklumatTanah.size());
  		
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
    		String idpermohonan = getParam("id_permohonan");
    		
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
    		}
    		
    		//dropdown by
    		if(_idDaerah!=""){
    			context.put("selectMukim",HTML.SelectMukimByDaerah(_idDaerah,"editMukim",Utils.parseLong(id_mukim),"id=editMukim style=width:250px"));
    		}else{
    			context.put("selectMukim",HTML.SelectMukim("editMukim",Utils.parseLong(id_mukim),"id=editMukim style=width:250px"));
    		}
    		
    		//dropdown
    		context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("editJenisHakmilik",Utils.parseLong(id_jenishakmilik),"style=width:auto"));
    		context.put("selectLuas",HTML.SelectLuas("editLuas",Utils.parseLong(id_luaslot),"style=width:250px"));
    		context.put("selectLot", HTML.SelectLot("editLot",Utils.parseLong(id_lot),"style=width:167px"));
    		
    		
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
    		
    		String idpermohonan = getParam("id_permohonan");
  	      	context.put("id_permohonan", idpermohonan);
  	      	
    		model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		
    		model.setListPohon2(idpermohonan);
    		listPohon2 = model.getListPohon2();
    		
    		model.setListMaklumatTanah(idpermohonan);
    		listMaklumatTanah = model.getListMaklumatTanah();
    		
    		String id_agensi = "";
    		//String id_suburusan = "";
    		String id_daerah = "";
    		String id_mukim = "";
    		String id_kementerian = "";
    		String id_negeri = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			id_agensi = h.get("idAgensi").toString();
    			//id_suburusan = h.get("idSuburusan").toString();
    			id_daerah = h.get("idDaerah").toString();
    			id_mukim = h.get("id_mukim").toString();
    			id_kementerian = h.get("idKementerian").toString();
    			id_negeri = h.get("idNegeri").toString();
    		}
    		
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		//dropdown by
    		if(id_kementerian!=""){
    			context.put("selectAgensi",HTML.SelectAgensiByKementerian("editAgensi",id_kementerian,Utils.parseLong(id_agensi),"style=width:500px"));	
    		}else{
    			context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(id_agensi),"style=width:500px"));	
    		}
    		if(id_projekNegeri!=""){
    			context.put("selectDaerah",HTML.SelectDaerahByNegeri(id_projekNegeri,"editDaerah",Utils.parseLong(id_daerah),"style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
        	}else{
    			context.put("selectDaerah",HTML.SelectDaerah("editDaerah",Utils.parseLong(id_daerah),"style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
        	}
    		if(id_daerah!=""){
    			context.put("selectBandar",HTML.SelectMukimByDaerah(id_daerah,"editBandar",Utils.parseLong(id_mukim),"style=width:300px"));
    		}else{
    			context.put("selectBandar",HTML.SelectMukim("editBandar",Utils.parseLong(id_mukim),"style=width:300px"));
    		}
    		//dropdown
    		//context.put("selectSuburusan",HTML.SelectSuburusanUPT("editSuburusan",Utils.parseLong(id_suburusan),"style=width:400px disabled",""));   		
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),null,"style=width:500px onChange=\"doChangeidKementerianUPDATE();\" "));
    		context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(id_negeri),"style=width:300px"));
    		context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(id_projekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUPDATE();\" "));
    		
    		
    		//data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("dataPermohonan", listPohon);
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
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
    		
    		String idpermohonan = getParam("id_permohonan");
  	      	context.put("id_permohonan", idpermohonan);
  	      
    		String idEditKementerian = getParam("editKementerian");
    		
    		//senarai semak
    		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		    context.put("senaraiSemakan", senaraiSemak);
    		
		    //data & list maklumat tanah
		    model.setListMaklumatTanah(idpermohonan);
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
    		//String idBandar = getParam("editBandar");
     		String idDaerah = getParam("editDaerah");
    		String idProjekNegeri = getParam("editProjekNegeri");
    		
    		model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		String id_mukim = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			id_mukim = h.get("id_mukim").toString();
    		}
    		
    		//get and set data
    		context.put("tujuan", getParam("edit_tujuan"));
    		context.put("tarikhSurat", getParam("edit_tarikh_surat"));
    		context.put("tarikhHendak", getParam("edit_tarikh_kehendaki"));
    		context.put("rujKementerian", getParam("edit_rujukan_kementerian"));
    		context.put("tarikhPohon", getParam("edit_tarikh_permohonan"));
    		
    		//dropdown by
    		if(idEditKementerian!=""){
    			context.put("selectAgensi",HTML.SelectAgensiByKementerian("editAgensi",idEditKementerian,null,"id=editAgensi style=width:500px"));
    		}
    		else{
    			context.put("selectAgensi",HTML.SelectAgensi("editAgensi",null,"id=editAgensi style=width:500px"));	
    		}
    		if(idProjekNegeri!=""){
    			context.put("selectDaerah",HTML.SelectDaerahByNegeri(idProjekNegeri,"editDaerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
    		}else{
    			context.put("selectDaerah",HTML.SelectDaerah("editDaerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
    		}
    		if(idDaerah!=""){
    			context.put("selectBandar",HTML.SelectMukimByDaerah(idDaerah,"editBandar",Utils.parseLong(id_mukim),"style=width:300px"));
    		}else{
    			context.put("selectBandar",HTML.SelectMukim("editBandar",Utils.parseLong(id_mukim),"style=width:300px"));
    		}
    		
    		//dropdown
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(idEditKementerian),null,"style=width:500px onChange=\"doChangeidKementerianUPDATE();\" "));
    		context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(AK_negeri),"style=width:300px"));
    		context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUPDATE();\" "));
    		
    		
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
    		
			//radio button peruntukan segera
			if (getParam("flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}			
			context.put("checkedYa",checkedYa);
			context.put("checkedTidak",checkedTidak);
			
    		//data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	context.put("dataPermohonan", listPohon);
   	    	
   	    	//form validation
    		context.put("semak", "yes");
    		context.put("edit", "yes");
    		
    		//screen
	        vm = screenUtama;

   	    }//dochangeidkementerianupdate 
 
    	else if ("doChangeidProjekNegeriUPDATE".equals(submit)) {
    		
    		//onchange validation
    		context.put("changeby","yes");
    		
    		String idpermohonan = getParam("id_permohonan");
    		context.put("id_permohonan", idpermohonan);
    		
    		String idEditProjekNegeri = getParam("editProjekNegeri");
    		
    		//get and set data
    		context.put("poskod", getParam("edit_poskod"));
        	context.put("alamat1", getParam("edit_alamat1"));
        	context.put("alamat2", getParam("edit_alamat2"));
        	context.put("alamat3", getParam("edit_alamat3"));
    		
        	//id dropdown	
    		//String idBandar = getParam("editBandar");
    		String idNegeri = getParam("editNegeri");
    		String idAgensi = getParam("editAgensi");
    		String idKementerian = getParam("editKementerian");
    		
    		//senarai semak
    		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		    context.put("senaraiSemakan", senaraiSemak);
		    
		    //data header
    		model.setListPohon(idpermohonan);
    		listPohon = model.getListPohon();
    		
    		//data & list maklumat tanah
    		model.setListMaklumatTanah(idpermohonan);
    		listMaklumatTanah = model.getListMaklumatTanah();
     		
    		 	
    		//dropdown by
    		if(idEditProjekNegeri!=""){
    			context.put("selectDaerah",HTML.SelectDaerahByNegeri(idEditProjekNegeri,"editDaerah",null,null,"id=editDaerah style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
    		}else{
    			context.put("selectDaerah",HTML.SelectDaerah("editDaerah",null,null,"id=editDaerah style=width:300px onChange=\"doChangeidMukimUPDATE();\""));
    		}
    		if(idKementerian!=""){
    			context.put("selectAgensi",HTML.SelectAgensiByKementerian("editAgensi",idKementerian,Utils.parseLong(idAgensi),"style=width:500px"));
    		}else{
    			context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(idAgensi),"style=width:500px"));	
    		}
    		
    		//dropdown
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doChangeidKementerianUPDATE();\" "));
    		context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(idNegeri),"style=width:300px"));
    		context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(idEditProjekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUPDATE();\" "));
    		context.put("selectBandar",HTML.SelectMukim("editBandar",null,"style=width:300px"));
    		
    		//get and set data
    		context.put("tujuan", getParam("edit_tujuan"));
    		context.put("tarikhSurat", getParam("edit_tarikh_surat"));
    		context.put("tarikhHendak", getParam("edit_tarikh_kehendaki"));
    		context.put("rujKementerian", getParam("edit_rujukan_kementerian"));
    		context.put("tarikhPohon", getParam("edit_tarikh_permohonan"));
    		
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
    		
			//radio button segera
			if (getParam("flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}			
			context.put("checkedYa",checkedYa);
			context.put("checkedTidak",checkedTidak);
			
			//data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	context.put("dataPermohonan", listPohon);
   	    	
   	    	//form validation
    		context.put("semak", "yes");
    		context.put("edit", "yes");
    		
    		//screen
	        vm = screenUtama;
	        
   	    }//dochangeidprojeknegeriupdate
    	
    	else 
    	if("semak".equals(submit)){
    		
    		String idpermohonan = getParam("id_permohonan");
    		context.put("id_permohonan", idpermohonan);
    		
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
     		model.setListDokumen(idpermohonan);
     		listDokumen = model.getListDokumen();
    		context.put("listDokumen", listDokumen);
    		context.put("listD_size", listDokumen.size());
     		
    		//data & list maklumat tanah
     		model.setListMaklumatTanah(idpermohonan);
     		listMaklumatTanah = model.getListMaklumatTanah();
     		context.put("listMaklumatTanah", listMaklumatTanah);
     		context.put("saiz_listTanah", listMaklumatTanah.size());
    				
    		String currentStatus = "";
    		String currentSuburusan = "";
    		String idAGENSI = "";
    		String idNegeri = "";
    		String idKementerian = "";
    		//String idSuburusan = "";
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
    			//idSuburusan = h.get("idSuburusan").toString();
    			idDaerah = h.get("idDaerah").toString();
    			idMukim = h.get("id_mukim").toString();
    			flag_peruntukan = h.get("flag_peruntukan").toString();
    			flag_segera = h.get("flag_segera").toString();
    			id_fail = h.get("idFail").toString();
    		}   		
    		context.put("currentSuburusan", currentSuburusan);
    		context.put("currentStatus", currentStatus);
    		
    		//id
    		context.put("id_fail",id_fail);
    		
    		//dropdown by
        	if(idAGENSI!=""){
        		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Long.parseLong(idAGENSI),"style=width:500px class=disabled disabled"));
        	}else{
        		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",null,"style=width:500px class=disabled disabled"));
        	}
	
        	//dropdown
    		context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(idNegeri),"style=width:300px class=disabled disabled"));
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(idKementerian),"style=width:500px class=disabled disabled"));
    		//context.put("selectSuburusan",HTML.SelectSuburusanUPT("editSuburusan",Utils.parseLong(idSuburusan),"style=width:400px class=disabled disabled",""));
    		context.put("selectDaerah",HTML.SelectDaerah("editDaerah",Utils.parseLong(idDaerah),"style=width:300px class=disabled disabled"));
    		context.put("selectBandar",HTML.SelectMukim("editBandar",Utils.parseLong(idMukim),"style=width:300px class=disabled disabled"));
    		
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px class=disabled disabled"));
    		
    		
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
    		
    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "no");
    		
    		//screen
    		vm = screenUtama;
         
    	}//close semak
    	
    	else 
    	if ("doChangeidKementerian".equals(submit)) {
    		
    		String idKementerian = getParam("kementerian");

    		alamatKementerian = model.getAlamatKementerian(idKementerian);    		
    		String id_negeriAgensi = "";
    		String alamat1 = "";
    		String alamat2 = "";
    		String alamat3 = "";
    		String poskod = "";   		
    		if(alamatKementerian.size()!=0){
    			Hashtable AK = (Hashtable) alamatKementerian.get(0);
    			id_negeriAgensi = AK.get("id_negeri").toString();
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
    		context.put("tarikhPohon", getParam("edit_tarikh_permohonan"));
    		
    		//String SuburusanUPT = getParam("socSuburusan");
    		//String idBandar = getParam("bandar");
    		String idDaerah = getParam("daerah");
    		String idProjekNegeri = getParam("projek_negeri");
    		String idMukim = getParam("bandar");
    		
    		//dropdown by
    		if(idKementerian!=""){
    			context.put("SelectAgensi",HTML.SelectAgensiByKementerian("agensi",idKementerian,null,"id=agensi style=width:500px"));
    		}else{
    			context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"id=agensi style=width:500px"));	
    		}
    		if(idProjekNegeri!=""){
    			context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idProjekNegeri,"daerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukim();\""));
        	}else{
        		context.put("SelectDaerah",HTML.SelectDaerah("daerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukim();\""));
            }
    		if(idDaerah!=""){
    			context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"bandar",Utils.parseLong(idMukim),"style=width:300px"));
    		}else{
    			context.put("SelectBandar",HTML.SelectMukim("bandar",Utils.parseLong(idMukim),"style=width:300px"));
    		}
    		
    		//dropdown
    		//context.put("SelectSuburusanUPT",HTML.SelectSuburusanUPT("socSuburusan",Utils.parseLong(SuburusanUPT),"style=width:400px"));
    		context.put("SelectNegeri",HTML.SelectNegeri("negeri",Utils.parseLong(id_negeriAgensi),"style=width:325px"));
    		context.put("project_negeri",HTML.SelectNegeri("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidNegeri();\" "));
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
    		
			//radio button pengambilan segera
			if (getParam("new_flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			}else 
			if(getParam("new_flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}else{
				checkedYa = "checked";
				checkedTidak = "";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			
			//form validation
    		context.put("dataPermohonan", "");
    		context.put("semak", "no");
    		context.put("edit", "no");
    		
    		//currentdate
    		String now = "";
    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    		context.put("currentDATE", now);
    		
    		//reset data
    		context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		context.put("listMaklumatTanah", "");
    		
    		//data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	context.put("dataPermohonan", listPohon);

    		//screen
	        vm = screenUtama;
	        
   	    }//close doChangeidKementerian 
    	
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
    		
    		//id dropdown
    		//String SuburusanUPT = getParam("socSuburusan");
    		String idKementerian = getParam("kementerian");
    		String idAgensi = getParam("agensi");
    		String idBandar = getParam("bandar");
    		String idNegeri = getParam("negeri");
    		String idProjekNegeri = getParam("projek_negeri");
    		
    		//dropdown by
    		if(idProjekNegeri!=""){
    			context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idProjekNegeri,"daerah",null,null,"id=daerah style=width:300px onChange=\"doChangeidMukim();\""));
    		}else{
    			context.put("SelectDaerah",HTML.SelectDaerah("daerah",null,null,"id=daerah style=width:300px onChange=\"doChangeidMukim();\""));
    		}
    		if(idKementerian!=""){
    			context.put("SelectAgensi",HTML.SelectAgensiByKementerian("agensi",idKementerian,Utils.parseLong(idAgensi),"style=width:500px"));	
    		}else{
    			context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),null,"style=width:500px"));	
    		}
    		
    		//dropdown
    		//context.put("SelectSuburusanUPT",HTML.SelectSuburusanUPT("socSuburusan",Utils.parseLong(SuburusanUPT),"style=width:400px"));		
    		context.put("SelectBandar",HTML.SelectMukim("bandar",Utils.parseLong(idBandar),"style=width:300px"));
    		context.put("SelectNegeri",HTML.SelectNegeri("negeri",Utils.parseLong(idNegeri),"style=width:325px"));    		
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doChangeidKementerian();\" "));
    		context.put("project_negeri",HTML.SelectNegeri("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidNegeri();\" "));
    		
    		
    		
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
    		
			//radio button peruntukan segera
			if (getParam("new_flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			}else 
			if (getParam("new_flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}else{
				checkedYa = "checked";
				checkedTidak = "";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			
    		//reset data
    		context.put("dataPermohonan", "");
    		context.put("listMaklumatTanah", "");
    		context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		
    		//validation data
    		context.put("semak", "no");
    		context.put("edit", "no");
    		
    		//get current date
    		String now = "";
    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    		context.put("currentDATE", now);
    		
    		//data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
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
    		
    		//form validation
    		context.put("semak", "no");
    		context.put("edit", "no");
    		
    		//get current date
    		String now = "";
    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    		context.put("currentDATE",now);
    		
    		//data
    		context.put("saiz_listTanah", listMaklumatTanah.size());

    		//dropdown
    		context.put("project_negeri",HTML.SelectNegeri("projek_negeri","style=width:300px onChange=\"doChangeidNegeri();\""));
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian","style=width:500px onChange=\"doChangeidKementerian();\""));
    		context.put("SelectNegeri",HTML.SelectNegeri("negeri",null,"style=width:325px"));
    		context.put("SelectDaerah",HTML.SelectDaerah("daerah",null,null,"style=width:300px onChange=\"doChangeidMukim();\""));
    		//context.put("SelectSuburusanUPT",HTML.SelectSuburusanUPT("socSuburusan",null,"style=width:400px"));
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:500px"));
    		context.put("SelectBandar",HTML.SelectMukim("bandar",null,"style=width:300px"));
    		
    		//screen
	        vm = screenUtama;
	        
		}//close baru
    	
    	else if("tambahDokumen".equals(submit))
    	{
    		
    		String idpermohonan = getParam("id_permohonan");
    		context.put("id_permohonan",idpermohonan);
    		
    		context.put("completed",false);     
            int files = 1;
            try {
              files = Integer.parseInt(getParam("files")); } 
            catch (Exception localException) {
            }
            context.put("num_files", Integer.valueOf(files));
            
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
    		
    		String idpermohonan = getParam("id_permohonan");    		
    		context.put("id_permohonan", idpermohonan);
    		
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
    		
    		//dropdown
    		context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("jenisHakMilik",null,"style=width:auto"));   		
    		context.put("SelectLuas",HTML.SelectLuas("luas",null,"style=width:250px"));
    		context.put("SelectLot", HTML.SelectLot("lot",null,"style=width:167px"));
    		
    		//dropdown by
    		if(id_daerah!=""){
    			context.put("SelectMukim",HTML.SelectMukimByDaerah(id_daerah,"mukim",null,"style=width:250px"));
    		}else{
    			context.put("SelectMukim",HTML.SelectMukim("mukim",null,"style=width:250px"));
    		}
    		
    		//form validation
    		context.put("semakTanah", "no");
    		
    		//screen
    		vm = screenTanah;
    		
    	}//close tambah maklumat tanah hakmilik
    	
    	else if("updateMaklumatTanah".equals(submit))
    	{
    		
    		//update data maklumat tanah
    		updateMaklumatTanah(session);
    		
    		String idpermohonan = getParam("id_permohonan");
    		String id_hakmilik = getParam("id_hakmilik");   		
    		context.put("id_permohonan", idpermohonan);
    		context.put("id_hakmilik", id_hakmilik);
    		
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
    		if(dataMaklumatTanah.size()!=0){
    			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
    			id_jenishakmilik = h.get("id_jenishakmilik").toString();
    			id_mukim = h.get("id_mukim").toString();
    			id_luaslot = h.get("id_luasLot").toString();
    			id_lot = h.get("id_lot").toString();
    		}
  
        	//dropdown
    		context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("MKJenisHakmilik",Utils.parseLong(id_jenishakmilik),"style=width:auto class=disabled disabled"));
    		context.put("selectMukim",HTML.SelectMukim("MKMukim",Utils.parseLong(id_mukim),"style=width:210px class=disabled disabled"));
    		context.put("selectLuas",HTML.SelectLuas("MKLuas",Utils.parseLong(id_luaslot),"style=width:210px class=disabled disabled"));
    		context.put("selectLot", HTML.SelectLot("MKLot",Utils.parseLong(id_lot),"style=width:167px class=disabled disabled"));
    		
    		//form validation
    		context.put("semakTanah", "yes");
    		context.put("wantedit", "no");
    	
    		//screen
    		vm = screenTanah;
    		
    	}//close update maklumat tanah
    	
    	else if("sah".equals(submit))
    	{
    		
    		if (doPost.equals("true")) {
    			//sahkan
        		sah(session);
     		}

    		String id_permohonan = getParam("id_permohonan");   		
    		context.put("id_permohonan", id_permohonan);
    		
    		//senarai semak
    		senaraiSemak = model.getSenaraiSemakan(id_permohonan);
		    context.put("senaraiSemakan", senaraiSemak);
		   
    		model.setListPohon(id_permohonan);
    		listPohon = model.getListPohon();
    		String current_status = "";
    		String curren_suburusan = "";
    		String idAGENSI = "";
    		String id_negeri = "";
    		String id_kementerian = "";
    		//String id_suburusan = "";
    		String id_daerah = "";
    		String id_mukim = "";
    		String flag_peruntukan = "";
    		String flag_segera = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			current_status = h.get("id_status").toString();
    			curren_suburusan = h.get("idSuburusan").toString();
    			idAGENSI = h.get("idAgensi").toString();
    			id_negeri = h.get("idNegeri").toString();
    			id_kementerian = h.get("idKementerian").toString();
    			//id_suburusan = h.get("idSuburusan").toString();
    			id_daerah = h.get("idDaerah").toString();
    			id_mukim = h.get("id_mukim").toString();
    			flag_peruntukan = h.get("flag_peruntukan").toString();
    			flag_segera = h.get("flag_segera").toString();
    		}
    		context.put("currentStatus", current_status);
    		context.put("currentSuburusan", curren_suburusan);
    		
    		//dropdown
    		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Long.parseLong(idAGENSI),"style=width:470px class=disabled disabled"));
    		context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(id_negeri),"style=width:300px class=disabled disabled"));
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),"style=width:470px class=disabled disabled"));
    		//context.put("selectSuburusan",HTML.SelectSuburusanUPT("editSuburusan",Utils.parseLong(id_suburusan),"style=width:400px class=disabled disabled",""));
    		context.put("selectDaerah",HTML.SelectDaerah("editDaerah",Utils.parseLong(id_daerah),"style=width:300px class=disabled disabled"));
    		context.put("selectBandar",HTML.SelectMukim("editBandar",Utils.parseLong(id_mukim),"style=width:300px class=disabled disabled"));
    			
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
    		
			if (flag_segera.equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			}else 
			if (flag_segera.equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}else{
				checkedYa = "checked";
				checkedTidak = "";
			}
			context.put("checkedYa",checkedYa);
			context.put("checkedTidak",checkedTidak);
    		
    		model.setListPohon2(id_permohonan);
    		listPohon2 = model.getListPohon2();
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		//dropdown
    		context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px class=disabled disabled"));
    		
    		//data & list maklumat tanah
    		model.setListMaklumatTanah(id_permohonan);
    		listMaklumatTanah = model.getListMaklumatTanah();
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
			//data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
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
    		update(session);	

    		String id_permohonan = getParam("id_permohonan");
    		context.put("id_permohonan", id_permohonan);
    		
    		model.setListPohon(id_permohonan);
    		listPohon = model.getListPohon();
    		String id_negeri = "";
    		String id_kementerian = "";
    		String id_agensi = "";
    		//String id_suburusan = "";
    		String id_daerah = "";
    		String id_mukim = "";
    		String flag_peruntukan = "";
    		String flag_segera = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			id_negeri = h.get("idNegeri").toString();
    			id_kementerian = h.get("idKementerian").toString();
    			id_agensi = h.get("idAgensi").toString();
    			//id_suburusan = h.get("idSuburusan").toString();
    			id_daerah = h.get("idDaerah").toString();
    			id_mukim = h.get("id_mukim").toString();
    			flag_peruntukan = h.get("flag_peruntukan").toString();
    			flag_segera = h.get("flag_segera").toString();
    		}
    		
    		//dropdown
    		context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(id_negeri),"style=width:300px disabled class=disabled"));
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),"style=width:470px disabled class=disabled"));
    		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Utils.parseLong(id_agensi),"style=width:470px disabled class=disabled"));
    		//context.put("selectSuburusan",HTML.SelectSuburusanUPT("editSuburusan",Utils.parseLong(id_suburusan),"disabled style=width:400px class=disabled", ""));
    		context.put("selectDaerah",HTML.SelectDaerah("editDaerah",Utils.parseLong(id_daerah),"style=width:300px disabled class=disabled"));
    		context.put("selectBandar",HTML.SelectMukim("editBandar",Utils.parseLong(id_mukim),"style=width:300px disabled class=disabled"));
    		   
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
 			
 			//radio button peruntukan segera
 			if (flag_segera.equals("1")){
 				checkedYa = "checked";
 				checkedTidak = "";
 			}else 
 			if(flag_segera.equals("2")){
 				checkedYa = "";
 				checkedTidak = "checked";
 			}else{
 				checkedYa = "checked";
 				checkedTidak = "";
 			}
 			context.put("checkedYa",checkedYa);
 			context.put("checkedTidak",checkedTidak);
 			
    		
    		model.setListPohon2(id_permohonan);
    		listPohon2 = model.getListPohon2();
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		//dropdown
    		context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px disabled class=disabled"));
  
    		model.setListMaklumatTanah(id_permohonan);
    		listMaklumatTanah = model.getListMaklumatTanah();

    		senaraiSemak = model.getSenaraiSemakan(id_permohonan);
		    context.put("senaraiSemakan", senaraiSemak);
		    
		    //data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("dataPermohonan", listPohon);
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "no");
    		
    		//screen
    		vm = screenUtama;
    		
    	}//close update  	
    	
    	else 
    	if("cari".equals(submit))
    	{
    		//carian
    		ListCarianSPT(session);			
    		listPageDepan = SPTDaftar.getList();
			
    		//data
		    context.put("PermohonanListSPT", listPageDepan);
		    context.put("list_size", listPageDepan.size());
 
		    //get & set data
		    //String urusan = getParam("suburusan");
		    String status = getParam("status");		    
		    //context.put("SelectSuburusanUPT",HTML.SelectSuburusanUPT("suburusan",Utils.parseLong(urusan),"style=width:auto"));
    		context.put("SelectStatusPPT",HTML.SelectStatusPPT("status",Utils.parseLong(status),"style=width:auto"));
    		context.put("carianPermohonan", getParam("no_permohonan").trim());
			context.put("carianTarikh", getParam("tarikh_permohonan").trim());
			
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else 
        if("lulus".equals(submit))
        {
        	
        	if (doPost.equals("true")) {
    			//luluskan
        		lulus(session);
     		}
    	
        	String id_permohonan = getParam("id_permohonan");   		
    		context.put("id_permohonan", id_permohonan);
    		
    		//senarai semak
    		senaraiSemak = model.getSenaraiSemakan(id_permohonan);
		    context.put("senaraiSemakan", senaraiSemak);
		   
    		model.setListPohon(id_permohonan);
    		listPohon = model.getListPohon();
    		String current_status = "";
    		String curren_suburusan = "";
    		String idAGENSI = "";
    		String id_negeri = "";
    		String id_kementerian = "";
    		//String id_suburusan = "";
    		String id_daerah = "";
    		String id_mukim = "";
    		String flag_peruntukan = "";
    		String flag_segera = "";
    		String id_fail = "";
    		if(listPohon.size()!=0){
    			Hashtable h = (Hashtable) listPohon.get(0);
    			current_status = h.get("id_status").toString();
    			curren_suburusan = h.get("idSuburusan").toString();
    			idAGENSI = h.get("idAgensi").toString();
    			id_negeri = h.get("idNegeri").toString();
    			id_kementerian = h.get("idKementerian").toString();
    			//id_suburusan = h.get("idSuburusan").toString();
    			id_daerah = h.get("idDaerah").toString();
    			id_mukim = h.get("id_mukim").toString();
    			flag_peruntukan = h.get("flag_peruntukan").toString();
    			flag_segera = h.get("flag_segera").toString();
    			id_fail = h.get("idFail").toString();
    		}
    		context.put("currentStatus", current_status);
    		context.put("currentSuburusan", curren_suburusan);
    		
    		//id
    		context.put("id_fail",id_fail);
    		
    		//dropdown
    		context.put("selectAgensi",HTML.SelectAgensi("editAgensi",Long.parseLong(idAGENSI),"style=width:470px class=disabled disabled"));
    		context.put("selectNegeri",HTML.SelectNegeri("editNegeri",Utils.parseLong(id_negeri),"style=width:300px class=disabled disabled"));
    		context.put("selectKementerian",HTML.SelectKementerian("editKementerian",Utils.parseLong(id_kementerian),"style=width:470px class=disabled disabled"));
    		//context.put("selectSuburusan",HTML.SelectSuburusanUPT("editSuburusan",Utils.parseLong(id_suburusan),"style=width:400px class=disabled disabled",""));
    		context.put("selectDaerah",HTML.SelectDaerah("editDaerah",Utils.parseLong(id_daerah),"style=width:300px class=disabled disabled"));
    		context.put("selectBandar",HTML.SelectMukim("editBandar",Utils.parseLong(id_mukim),"style=width:300px class=disabled disabled"));
    			
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
    		
			if (flag_segera.equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			}else 
			if (flag_segera.equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}else{
				checkedYa = "checked";
				checkedTidak = "";
			}
			context.put("checkedYa",checkedYa);
			context.put("checkedTidak",checkedTidak);
    		
    		model.setListPohon2(id_permohonan);
    		listPohon2 = model.getListPohon2();
    		String id_projekNegeri = "";
    		if(listPohon2.size()!=0){
    			Hashtable i = (Hashtable) listPohon2.get(0);
    			id_projekNegeri = i.get("idProjekNegeri").toString();
    		}
    		
    		//dropdown
    		context.put("selectProjekNegeri",HTML.SelectNegeri("editProjekNegeri",Utils.parseLong(id_projekNegeri),"style=width:300px class=disabled disabled"));
    		
    		//data & list maklumat tanah
    		model.setListMaklumatTanah(id_permohonan);
    		listMaklumatTanah = model.getListMaklumatTanah();
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
			//data
    		context.put("dataPermohonan2", listPohon2);
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	context.put("dataPermohonan", listPohon);
    		
    		//form validation
    		context.put("semak", "yes");
    		context.put("edit", "no");
    		
    		//screen
    		vm = screenUtama;
        	
        }//close lulus
    	
    	else{	
    		
    		listPageDepan = model.getListPemohon();
    		defaultPage(listPageDepan);	
    		vm = listdepan;
    		
		}//close else
   
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
	
	
//--METHOD--//	
	
	
	@SuppressWarnings("unchecked")
	private void addSPT(HttpSession session) throws Exception{
		
	    	Hashtable h = new Hashtable();
	    
	    	h.put("suburusan", "51");
		    //h.put("tarikh_permohonan", getParam("tarikh_permohonan"));
		    h.put("kementerian", getParam("kementerian"));
		    h.put("agensi",getParam("agensi"));
		    h.put("flag_peruntukan", getParam("new_flag_peruntukan"));
		    h.put("flag_segera", getParam("new_flag_segera"));
		    h.put("negeri", getParam("negeri"));
		    h.put("daerah", getParam("daerah"));
		    h.put("tujuan", getParam("tujuan"));
		    h.put("rujukan_kementerian", getParam("rujukan_kementerian"));
		    h.put("tarikh_kehendaki", getParam("tarikh_kehendaki"));
		    h.put("tarikh_surat", getParam("tarikh_surat"));
	    	h.put("alamat1", getParam("alamat1"));
	    	h.put("alamat2", getParam("alamat2"));
	    	h.put("alamat3", getParam("alamat3"));
	    	h.put("bandar", getParam("bandar"));
	    	h.put("poskod", getParam("poskod"));
	    	h.put("projek_negeri", getParam("projek_negeri"));
	   
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	
	    	SPTDaftar.addSPT(h);
	  
	}//close add

	@SuppressWarnings("unchecked")
	private void add_maklumat_tanah(HttpSession session) throws Exception{
	    
			Hashtable h = new Hashtable();
	    
	    	h.put("id_permohonan", getParam("id_permohonan"));
	    	h.put("negeri", getParam("id_existNegeri"));
	    	h.put("daerah", getParam("id_existDaerah"));
	    	h.put("catatan", getParam("txtCatatan"));
	    	
//	    	h.put("jenisHakMilik", getParam("jenisHakMilik"));
	    	h.put("mukim", getParam("mukim"));
//	    	h.put("luas", getParam("luas"));
//	    	h.put("lot", getParam("lot"));
//	    	h.put("no_hakmilik", getParam("no_hakmilik"));
//	    	h.put("no_lot", getParam("no_lot"));
//	    	h.put("luas_lot", getParam("luas_lot"));
//	    	h.put("anggaran_luas", getParam("anggaran_luas"));
	    	h.put("jenisHakMilik","");
//	    	h.put("mukim","");
	    	h.put("luas","");
	    	h.put("lot","");
	    	h.put("no_hakmilik","");
	    	h.put("no_lot","");
	    	h.put("luas_lot","");
	    	h.put("anggaran_luas","");
	    
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	
	    	SPTDaftar.add_maklumat_tanah(h);
	    
	   }//close add_maklumat_tanah
	
	 private void ListCarianSPT(HttpSession session) throws Exception{
    	
			String carianPermohonan = getParam("no_permohonan");
			String carianTarikh = getParam("tarikh_permohonan");
			//String carianSuburusan = getParam("suburusan");
			String carianStatus = getParam("status");
    	
			SPTDaftar.setList(carianPermohonan,carianTarikh,carianStatus);
      
	 }//close listcarian

	@SuppressWarnings("unchecked")
	private void update(HttpSession session) throws Exception{
		
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
 
	    	h.put("poskod", getParam("edit_poskod"));
	    	h.put("tujuan", getParam("edit_tujuan"));
	    	h.put("rujukan_kementerian", getParam("edit_rujukan_kementerian"));
	    	h.put("tarikh_hendak", getParam("edit_tarikh_kehendaki"));
	    	h.put("tarikh_surat", getParam("edit_tarikh_surat"));
	    	h.put("alamat1", getParam("edit_alamat1"));
	    	h.put("alamat2", getParam("edit_alamat2"));
	    	h.put("alamat3", getParam("edit_alamat3"));
	    	h.put("flag_peruntukan", getParam("flag_peruntukan"));
	    	h.put("flag_segera", getParam("flag_segera"));
	    	h.put("daerah", getParam("editDaerah"));
	    	h.put("projeknegeri", getParam("editProjekNegeri"));
	    	h.put("negeri", getParam("editNegeri"));
	    	h.put("bandar", getParam("editBandar"));
	    	h.put("agensi", getParam("editAgensi"));
	    	h.put("kementerian", getParam("editKementerian"));
	    	
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	
	    	SPTDaftar.update(h);
	    
	  }
	 
	@SuppressWarnings("unchecked")
	private void updateMaklumatTanah(HttpSession session) throws Exception{
		 
		 	Hashtable i = new Hashtable();

			i.put("catatan", getParam("txtCatatan"));
		 	
		    i.put("id_hakmilik", getParam("id_hakmilik"));
		    i.put("editNegeri", getParam("id_existNegeri"));
		    i.put("editDaerah", getParam("id_existDaerah"));
		    i.put("editMukim", getParam("editMukim"));
		    
//		    i.put("editJenisHakmilik", getParam("editJenisHakmilik")); 
//		    i.put("editLuas", getParam("editLuas"));
//		    i.put("editLot", getParam("editLot"));	   
//		    i.put("edit_no_hakmilik", getParam("edit_no_hakmilik"));
//		    i.put("edit_no_lot", getParam("edit_no_lot"));
//		    i.put("edit_luas_lot", getParam("edit_luas_lot"));
//		    i.put("edit_anggaran_luas", getParam("edit_anggaran_luas"));
		  
		    i.put("id_user", session.getAttribute("_ekptg_user_id"));
		    
		    SPTDaftar.updateMaklumatTanah(i);
	 }
	 
	 private void deleteMaklumatTanah(HttpSession session) throws Exception{
		 	int id_hakmilik = Integer.parseInt(getParam("id_hakmilik"));	    
		 	SPTDaftar.deleteMaklumatTanah(id_hakmilik);
	 }
	
	@SuppressWarnings("unchecked")
	private void sah(HttpSession session) throws Exception{
	    
		 	Hashtable h = new Hashtable();
		 	h.put("id_permohonan", getParam("id_permohonan"));
		 	h.put("id_user", session.getAttribute("_ekptg_user_id"));
		 	SPTDaftar.sahkan(h);  
	 }
	 
	@SuppressWarnings("unchecked")
	private void lulus(HttpSession session) throws Exception{
		 
		 	Hashtable h = new Hashtable();

		 	h.put("id_permohonan", getParam("id_permohonan"));
		 	h.put("id_fail", getParam("id_fail"));
		 	h.put("id_kementerian", getParam("idKementerianA"));
		 	h.put("id_negeri", getParam("idNegeriA"));
		 	h.put("id_status", "128");
		 	h.put("id_user", session.getAttribute("_ekptg_user_id"));
		 	
		 	SPTDaftar.lulus(h);
	 }

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
		    } finally {
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

 		//context.put("SelectSuburusanUPT",HTML.SelectSuburusanUPT("suburusan",null,"style=width:auto"));
 		context.put("SelectStatusPPT",HTML.SelectStatusPPT("status",null,"style=width:auto"));
 		
 		//reset textfield
		context.put("carianPermohonan", "");
		context.put("carianTarikh", "");
	
	 }//defaultpage	
	 
	 
}//close class
