package ekptg.view.ppt;

import java.io.PrintWriter;
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
import ekptg.model.ppt.FrmHakmilikSementaraPermohonanUPTData;
import ekptg.model.ppt.FrmHakmilikSementaraPermohonanUPTMaklumatTanahData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiPermohonanUPTData;

public class SementaraUPT extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(SementaraUPT.class);

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");
		myLogger.info("SUBMIT >>>> "+submit);
        String action = getParam("action");
        context.put("action",action);
        String mode = getParam("mode");
        String checkedAda = "";
    	String checkedTiada = "";
    	String checkedTidak = "";
    	String checkedYa = "";
    	String nopermohonan = "";    
    	String tarikhmohon= "";
    	String cStatus = "0";
    	String AK_negeri = "";
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";
		String idPermohonan = getParam("id_permohonan");
		context.put("id_permohonan",idPermohonan);
		String idHakmilik = getParam("id_hakmilik");
		context.put("id_hakmilik", getParam("id_hakmilik"));
		String noLot = getParam("no_lot");
		String socDaerah = getParam("daerah");
		long currentStatus;
		long currentSuburusan;
		PrintWriter out = response.getWriter();
        HttpSession session = this.request.getSession();
        String userId = session.getAttribute("_ekptg_user_id").toString();
        context.put("userID",userId);
        
        FrmHakmilikSementaraSenaraiPermohonanUPTData senaraiPrmhnnUPT = new FrmHakmilikSementaraSenaraiPermohonanUPTData();
        FrmHakmilikSementaraPermohonanUPTData prmhnnUPT = new FrmHakmilikSementaraPermohonanUPTData();
        FrmHakmilikSementaraPermohonanUPTMaklumatTanahData prmnhnnSPTMaklumatTanah = new FrmHakmilikSementaraPermohonanUPTMaklumatTanahData();
        
        Vector listPermohonanUPT = null;
        Vector alamatKementerian = null;
        Vector listPohon = null;
    	Vector listPohon2 = null;
    	Vector listMaklumatTanah = null;
    	Vector dataMaklumatTanah = null;
    	Vector listDokumen = null;
        Vector negeriLogin = null;
      
        
        if ("baru".equals(action)){
        	 vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
        	String now = "";
      		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
      		
      		
      		
      		negeriLogin = prmhnnUPT.getIdNegeriByLogin(userId);
      		Hashtable hN = (Hashtable)negeriLogin.get(0);
      		
      		context.put("tarikhPohon", now);
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
     		context.put("mode","baru");
     		context.put("noPermohonan","");
     		context.put("noRujPTG", "");
     		context.put("noRujPTD","");
     		context.put("readonly", "");
    		context.put("disabled", "");
    		context.put("status","PERMOHONAN CAWANGAN");
        	
     		
        	context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(hN.get("ID_NEGERI").toString()),"style=width:300px onChange=\"doChangeidNegeri();\""));
     		context.put("SelectKementerian",HTML.SelectKementerian("kementerian","style=width:500px onChange=\"doChangeidKementerian();\""));
     		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",null,"style=width:325px"));
     		context.put("SelectDaerah",HTML.SelectDaerahByNegeri(hN.get("ID_NEGERI").toString(),"daerah",null,"style=width:300px onChange=\"doChangeidMukim();\""));
     		context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:500px"));
     		context.put("SelectBandar",HTML.SelectMukimByDaerah(socDaerah,"bandar",null,"style=width:300px"));
     	
        	
        }
        else if ("simpan".equals(action)){
        	
        	String id = addUPT(session);
        	
        	vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
        	
    		context.put("id_permohonan", id);
    		context.put("mode", "papar");
    		context.put("readonly", "readonly");
    		context.put("disabled", "disabled");
    		
    		prmhnnUPT.setListPohon(id);
    		listPohon = prmhnnUPT.getListPohon();
    		
    		prmhnnUPT.setListPohon2(id);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListDokumen(id);
    		listDokumen = prmhnnUPT.getListDokumen();
    		
		    context.put("listDokumen", listDokumen);
		    context.put("listD_size", listDokumen.size());
    		
    		prmhnnUPT.setListMaklumatTanah(id);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		Hashtable h = (Hashtable) listPohon.get(0);
    		
    		
    		currentStatus = Long.parseLong(h.get("id_status").toString());
    		currentSuburusan = Long.parseLong(h.get("idSuburusan").toString());
    		
    		context.put("currentSuburusan", currentSuburusan);
    		context.put("currentStatus", currentStatus);
    		
    		String idAGENSI = h.get("idAgensi").toString();
    		
        	if(idAGENSI!="")
        	{
        		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Long.parseLong(idAGENSI),"style=width:500px class=disabled disabled"));
        	}else{
        		context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:500px disabled"));
        	}
    		
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Long.parseLong(h.get("idNegeri").toString()),"style=width:300px class=disabled disabled"));
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Long.parseLong(h.get("idKementerian").toString()),"style=width:500px class=disabled disabled"));
    		context.put("SelectDaerah",HTML.SelectDaerah("daerah",Long.parseLong(h.get("idDaerah").toString()),"style=width:300px class=disabled disabled"));
//    		context.put("SelectBandar",HTML.SelectMukim("bandar",Long.parseLong(h.get("id_mukim").toString()),"style=width:300px class=disabled disabled"));
    		context.put("noPermohonan", h.get("noPermohonan"));
        	context.put("addTujuan", h.get("tujuan"));
     		context.put("addPoskod", h.get("poskod"));
     		context.put("addAlamat1", h.get("alamat1"));
     		context.put("addAlamat2", h.get("alamat2"));
     		context.put("addAlamat3", h.get("alamat3"));
     		context.put("addTarikhHendak", h.get("tarikh_kehendaki"));
     		context.put("addTarikhSurat", h.get("tarikh_surat"));
     		context.put("addRujukan_kementerian", h.get("no_rujukan_surat"));
     		context.put("idSuburusan", h.get("idSuburusan"));
     		context.put("idKementerianA", h.get("idKementerian"));
     		context.put("idNegeriA",h.get("idNegeri"));
     		context.put("projeknegeri",h.get("projek_negeri"));
     		context.put("id_daerah",h.get("idDaerah"));
     		context.put("status",h.get("status"));
     		context.put("noRujPTG", h.get("noRujPTG"));
     		context.put("noRujPTD",h.get("noRujPTD"));
     		context.put("tarikhPohon", h.get("tarikh_permohonan"));
    		
    		Hashtable i = (Hashtable) listPohon2.get(0);
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Long.parseLong(i.get("idProjekNegeri").toString()),"style=width:300px class=disabled disabled"));
    		context.put("idFail", i.get("id_fail"));
    		
    		Hashtable z = (Hashtable) listPohon.get(0);
			if (z.get("flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (z.get("flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
			context.put("flag_peruntukan", z.get("flag_peruntukan"));
			
    		
			Hashtable fg = (Hashtable) listPohon.get(0);
			if (fg.get("flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (fg.get("flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			context.put("flag_segera", fg.get("flag_segera"));
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
        	
        	
        }
        else if("batal".equals(action))
    	{
        	vm = "app/ppt/frmHakmilikSementaraSenaraiPermohonanUPT.jsp";
        	
        	context.put("mode", "simpan");
 	        
			senaraiPrmhnnUPT.setListPemohon(tarikhmohon,nopermohonan,cStatus);
 	        listPermohonanUPT = senaraiPrmhnnUPT.getList();
 	        context.put("PermohonanListSPT", listPermohonanUPT);
 	        context.put("carianPermohonan", "");
 			context.put("carianTarikh", "");
 	        setupPage(session,action,listPermohonanUPT);
    	}
        else if("kemaskini".equals(action))
    	{
        	vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
        	
    		context.put("mode", "kemaskini");
    		context.put("readonly", "");
    		context.put("disabled", "");
    		
    		prmhnnUPT.setListPohon(idPermohonan);
    		listPohon = prmhnnUPT.getListPohon();
    		
    		prmhnnUPT.setListPohon2(idPermohonan);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		prmhnnUPT.setListDokumen(idPermohonan);
    		listDokumen = prmhnnUPT.getListDokumen();
    		
		    context.put("listDokumen", listDokumen);
		    context.put("listD_size", listDokumen.size());
    		
    		Hashtable h = (Hashtable) listPohon.get(0);
        	
    		long idAGENSI = Long.parseLong(h.get("idAgensi").toString());
    		
    		//- Check if id_agensi != null -//
        	if(idAGENSI!=0)
        	{
        		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Long.parseLong(h.get("idAgensi").toString()),"style=width:470px"));
        	}else{
        		context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:470px"));
        	}
        	//------------------------------//

    		context.put("SelectDaerah",HTML.SelectDaerah("daerah",Long.parseLong(h.get("idDaerah").toString()),"style=width:300px onChange=\"doChangeidMukimUpdate();\""));
//    		context.put("SelectBandar",HTML.SelectMukim("bandar",Long.parseLong(h.get("id_mukim").toString()),"style=width:300px"));
    		context.put("noPermohonan", h.get("noPermohonan"));
    		context.put("no_fail",h.get("no_fail"));
    		context.put("tarikhPohon",h.get("tarikh_permohonan"));
        	context.put("addTujuan", h.get("tujuan"));
     		context.put("addPoskod", h.get("poskod"));
     		context.put("addAlamat1", h.get("alamat1"));
     		context.put("addAlamat2", h.get("alamat2"));
     		context.put("addAlamat3", h.get("alamat3"));
     		context.put("addTarikhHendak", h.get("tarikh_kehendaki"));
     		context.put("addTarikhSurat", h.get("tarikh_surat"));
     		context.put("addRujukan_kementerian", h.get("no_rujukan_surat"));
     		context.put("idSuburusan", h.get("idSuburusan"));
     		context.put("noRujPTG", h.get("noRujPTG"));
     		context.put("noRujPTD",h.get("noRujPTD"));
     		
    		Hashtable i = (Hashtable) listPohon2.get(0);
    		
    		//--Filter--//
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Long.parseLong(h.get("idKementerian").toString()),null,"style=width:470px onChange=\"doChangeidKementerianUpdate();\" "));
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Long.parseLong(h.get("idNegeri").toString()),"style=width:300px"));
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Long.parseLong(i.get("idProjekNegeri").toString()),null,"style=width:300px onChange=\"doChangeidProjekNegeriUpdate();\" "));
    		context.put("idFail", i.get("id_fail"));
    		//----------//
    		
    		Hashtable z = (Hashtable) listPohon.get(0);
			if (z.get("flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (z.get("flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
			context.put("flag_peruntukan", z.get("flag_peruntukan"));
			
    		
			Hashtable fg = (Hashtable) listPohon.get(0);
			if (fg.get("flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (fg.get("flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			context.put("flag_segera", fg.get("flag_segera"));
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    		
    	}

    	else if("semak".equals(action))
    	{
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
    		
    		context.put("mode", "papar");
    		context.put("readonly", "readonly");
    		context.put("disabled", "disabled");
    		
    		prmhnnUPT.setListPohon(idPermohonan);
    		listPohon = prmhnnUPT.getListPohon();
    		
    		prmhnnUPT.setListPohon2(idPermohonan);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListDokumen(idPermohonan);
    		listDokumen = prmhnnUPT.getListDokumen();
    		
		    context.put("listDokumen", listDokumen);
		    context.put("listD_size", listDokumen.size());
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		Hashtable h = (Hashtable) listPohon.get(0);
    		
    		currentStatus = Long.parseLong(h.get("id_status").toString());
    		currentSuburusan = Long.parseLong(h.get("idSuburusan").toString());
    		
    		context.put("currentSuburusan", currentSuburusan);
    		context.put("currentStatus", currentStatus);
    		
    		String idAGENSI = h.get("idAgensi").toString();
    		
        	if(idAGENSI!="")
        	{
        		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Long.parseLong(idAGENSI),"style=width:500px class=disabled disabled"));
        	}else{
        		context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:500px disabled"));
        	}
    		
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Long.parseLong(h.get("idNegeri").toString()),"style=width:300px class=disabled disabled"));
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Long.parseLong(h.get("idKementerian").toString()),"style=width:500px class=disabled disabled"));
    		context.put("SelectDaerah",HTML.SelectDaerah("daerah",Long.parseLong(h.get("idDaerah").toString()),"style=width:300px class=disabled disabled"));
//    		context.put("SelectBandar",HTML.SelectMukim("bandar",Long.parseLong(h.get("id_mukim").toString()),"style=width:300px class=disabled disabled"));
    		context.put("noPermohonan", h.get("noPermohonan"));
    		context.put("no_fail",h.get("no_fail"));
    		context.put("tarikhPohon",h.get("tarikh_permohonan"));
        	context.put("addTujuan", h.get("tujuan"));
     		context.put("addPoskod", h.get("poskod"));
     		context.put("addAlamat1", h.get("alamat1"));
     		context.put("addAlamat2", h.get("alamat2"));
     		context.put("addAlamat3", h.get("alamat3"));
     		context.put("addTarikhHendak", h.get("tarikh_kehendaki"));
     		context.put("addTarikhSurat", h.get("tarikh_surat"));
     		context.put("addRujukan_kementerian", h.get("no_rujukan_surat"));
     		context.put("idSuburusan", h.get("idSuburusan"));
     		context.put("idKementerianA", h.get("idKementerian"));
     		context.put("idNegeriA",h.get("idNegeri"));
     		context.put("projeknegeri",h.get("projek_negeri"));
     		context.put("id_daerah",h.get("idDaerah"));
     		context.put("status",h.get("status"));
     		context.put("noRujPTG", h.get("noRujPTG"));
     		context.put("noRujPTD",h.get("noRujPTD"));
    		
    		Hashtable i = (Hashtable) listPohon2.get(0);
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Long.parseLong(i.get("idProjekNegeri").toString()),"style=width:300px class=disabled disabled"));
    		context.put("idFail", i.get("id_fail"));
    		
    		Hashtable z = (Hashtable) listPohon.get(0);
			if (z.get("flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (z.get("flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
			context.put("flag_peruntukan", z.get("flag_peruntukan"));
			
    		
			Hashtable fg = (Hashtable) listPohon.get(0);
			if (fg.get("flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (fg.get("flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			context.put("flag_segera", fg.get("flag_segera"));
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());

    	}//close semak
        
    	else if("update".equals(action))
    	{
    		
    		update(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
    		
    		context.put("mode", "papar");
    		context.put("readonly", "readonly");
    		context.put("disabled", "disabled");

    		prmhnnUPT.setListPohon(idPermohonan);
    		listPohon = prmhnnUPT.getListPohon();
    		
    		prmhnnUPT.setListPohon2(idPermohonan);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		prmhnnUPT.setListDokumen(idPermohonan);
    		listDokumen = prmhnnUPT.getListDokumen();
    		
		    context.put("listDokumen", listDokumen);
		    context.put("listD_size", listDokumen.size());
    		
    		Hashtable h = (Hashtable) listPohon.get(0);
        	
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Long.parseLong(h.get("idNegeri").toString()),"style=width:300px class=disabled disabled"));
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Long.parseLong(h.get("idKementerian").toString()),"style=width:470px class=disabled disabled"));
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Long.parseLong(h.get("idAgensi").toString()),"style=width:470px class=disabled disabled"));
    		context.put("SelectDaerah",HTML.SelectDaerah("daerah",Long.parseLong(h.get("idDaerah").toString()),"style=width:300px class=disabled disabled"));
//    		context.put("SelectBandar",HTML.SelectMukim("bandar",Long.parseLong(h.get("id_mukim").toString()),"style=width:300px class=disabled disabled"));
    		context.put("noPermohonan", h.get("noPermohonan"));
    		context.put("no_fail",h.get("no_fail"));
    		context.put("tarikhPohon",h.get("tarikh_permohonan"));
        	context.put("addTujuan", h.get("tujuan"));
     		context.put("addPoskod", h.get("poskod"));
     		context.put("addAlamat1", h.get("alamat1"));
     		context.put("addAlamat2", h.get("alamat2"));
     		context.put("addAlamat3", h.get("alamat3"));
     		context.put("addTarikhHendak", h.get("tarikh_kehendaki"));
     		context.put("addTarikhSurat", h.get("tarikh_surat"));
     		context.put("addRujukan_kementerian", h.get("no_rujukan_surat"));
     		context.put("idSuburusan", h.get("idSuburusan"));
     		context.put("status",h.get("status"));
     		context.put("noRujPTG", h.get("noRujPTG"));
     		context.put("noRujPTD",h.get("noRujPTD"));

    		Hashtable i = (Hashtable) listPohon2.get(0);
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Long.parseLong(i.get("idProjekNegeri").toString()),"style=width:300px class=disabled disabled"));
    		context.put("idFail", i.get("id_fail"));
    		
		    Hashtable z = (Hashtable) listPohon.get(0);
			if (z.get("flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (z.get("flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			
			Hashtable fg = (Hashtable) listPohon.get(0);
			if (fg.get("flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (fg.get("flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			context.put("flag_segera", fg.get("flag_segera"));
			
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
			context.put("flag_peruntukan", z.get("flag_peruntukan"));
		    
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    		
    		
    		
    	}//close update  
    	else if("tambahDokumen".equals(action))
    	{
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPTMaklumatDokumen.jsp";

    		context.put("completed",false);  
    		
            int files = 1;
            try {
              files = Integer.parseInt(getParam("files")); } 
            catch (Exception localException) {
            }
            context.put("num_files", Integer.valueOf(files));
            
           
            
    	}//close tambah dokumen
    	
    	else if("uploadFile".equals(action))
    	{
    			uploadFiles();
         		vm = "app/ppt/frmHakmilikSementaraPermohonanUPTMaklumatDokumen.jsp";

         		context.put("completed",true);
       	
    	}//close upload file
    	else if("tambah".equals(action))
    	{
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPTMaklumatTanah.jsp";
    		
    		
    		context.put("mode", "tambahHakmilik");
    		context.put("readonly", "");
    		
    		prmhnnUPT.setListPohon(idPermohonan);
    		listPohon = prmhnnUPT.getListPohon();
    		
    		prmhnnUPT.setListPohon2(idPermohonan);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		Hashtable h = (Hashtable) listPohon2.get(0);
    		context.put("id_existNegeri", h.get("idProjekNegeri").toString());
    		context.put("existNegeri", h.get("projek_negeri").toString());
    		
    		Hashtable i = (Hashtable) listPohon.get(0);
    		context.put("existDaerah", i.get("daerah").toString());
    		context.put("id_existDaerah", i.get("idDaerah").toString());
//    		context.put("existMukim", i.get("mukim").toString());
//    		context.put("id_existMukim", i.get("id_mukim").toString());
    		
    		String idDaerah = i.get("idDaerah").toString();
    		context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("jenisHakMilik",null,"class=autoselect"));
    		context.put("SelectLuas",HTML.SelectLuas("luas",null,"style=width:250px"));
    		context.put("SelectLot", HTML.SelectLot("lot",null,"style=width:167px"));
     		context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"bandar",null,"style=width:300px"));
    		context.put("noHakmilik","");
    		context.put("noLot", "");
    		context.put("luasLot", "");
    		context.put("anggaranLuas","");
    		context.put("idTanah","");
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    		
    	}//close tambah maklumat tanah hakmilik
    	else if("addMaklumatTanah".equals(action))
    	{
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPTMaklumatTanah.jsp";
    		
    		context.put("mode", "tambahHakmilik");
    		context.put("readonly","");
    		
    		add_maklumat_tanah(session);
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		  		
    	}
    	else if("edit_maklumat".equals(action))
    	{
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPTMaklumatTanah.jsp";

    		context.put("mode","paparHakmilik");
    		context.put("readonly","readonly");
    		
    		
    		
    		
    		prmhnnUPT.setListPohon(idPermohonan);
    		listPohon = prmhnnUPT.getListPohon();
    		
    		prmhnnUPT.setListPohon2(idPermohonan);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		prmnhnnSPTMaklumatTanah.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = prmnhnnSPTMaklumatTanah.getMaklumatTanah();
    		    		
    		Hashtable g = (Hashtable) listPohon.get(0);
    		currentStatus = Long.parseLong(g.get("id_status").toString());
    		
    		context.put("currentStatus", currentStatus);
    		
    		Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
        	
    		
    		Hashtable j = (Hashtable) listPohon2.get(0);
    		context.put("id_existNegeri", j.get("idProjekNegeri").toString());
    		context.put("existNegeri", j.get("projek_negeri").toString());
    		
    		Hashtable i = (Hashtable) listPohon.get(0);
    		context.put("existDaerah", i.get("daerah").toString());
    		context.put("id_existDaerah", i.get("idDaerah").toString());
//    		context.put("existMukim", i.get("mukim").toString());
//    		context.put("id_existMukim", i.get("id_mukim").toString());
//  
    		String idDaerah = i.get("idDaerah").toString();
    		
    		context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("jenisHakmilik",Long.parseLong(h.get("id_jenishakmilik").toString()),"class=disabled style=width:auto disabled"));
    		context.put("SelectLuas",HTML.SelectLuas("luas",Long.parseLong(h.get("id_luasLot").toString())," class=disabled disabled style=width:250px"));
    		context.put("SelectLot", HTML.SelectLot("lot",Long.parseLong(h.get("id_lot").toString()),"class=disabled disabled style=width:167px"));
     		context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"bandar",Long.parseLong(h.get("id_mukim").toString()),"class=disabled disabled style=width:300px"));
    		context.put("noHakmilik",h.get("no_hakmilik"));
    		context.put("noLot", h.get("no_lot"));
    		context.put("luasLot", h.get("luas_lot"));
    		context.put("anggaranLuas",h.get("luas_ambil"));
    		context.put("idTanah",h.get("id_hakmilik"));
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    	}
    	else if("kemaskiniTanah".equals(action))
    	{
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPTMaklumatTanah.jsp";

    		context.put("mode","kemaskiniHakmilik");
    		context.put("readonly"," ");
    		
    		
    		
    		prmnhnnSPTMaklumatTanah.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = prmnhnnSPTMaklumatTanah.getMaklumatTanah();
    		
    		Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
    		
    		String lot = h.get("no_lot").toString();
    		String pt = h.get("no_pt").toString();
    		
    		if(lot==""){
    			context.put("checkA", "");
    			context.put("checkB", "checked");
    			context.put("disA", "disabled");
    			context.put("disB", "");
    		}else if(pt==""){
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
    		
    		prmhnnUPT.setListPohon(idPermohonan);
  			listPohon = prmhnnUPT.getListPohon();
  			
  			prmhnnUPT.setListPohon2(idPermohonan);
  			listPohon2 = prmhnnUPT.getListPohon2();
    		
  			prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
  			
    		Hashtable j = (Hashtable) listPohon2.get(0);
    		context.put("id_existNegeri", j.get("idProjekNegeri").toString());
    		context.put("existNegeri", j.get("projek_negeri").toString());
    		
    		Hashtable i = (Hashtable) listPohon.get(0);
    		context.put("existDaerah", i.get("daerah").toString());
    		context.put("id_existDaerah", i.get("idDaerah").toString());
//    		context.put("existMukim", i.get("mukim").toString());
//    		context.put("id_existMukim", i.get("id_mukim").toString());
//    	
    		String idDaerah = i.get("idDaerah").toString();
    		
    		
    		context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("jenisHakmilik",Long.parseLong(h.get("id_jenishakmilik").toString()),"class=autoselect"));
    		context.put("SelectLuas",HTML.SelectLuas("luas",Long.parseLong(h.get("id_luasLot").toString()),"style=width:250px"));
    		context.put("SelectLot", HTML.SelectLot("lot",Long.parseLong(h.get("id_lot").toString()),"style=width:167px"));
     		context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"bandar",Long.parseLong(h.get("id_mukim").toString()),"style=width:300px"));
    		context.put("noHakmilik",h.get("no_hakmilik"));
    		context.put("noLot", h.get("no_lot"));
    		context.put("luasLot", h.get("luas_lot"));
    		context.put("anggaranLuas",h.get("luas_ambil"));
    		context.put("idTanah",h.get("id_hakmilik"));
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    	}
    	else if("updateMaklumatTanah".equals(action))
    	{
    		
    		updateMaklumatTanah(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPTMaklumatTanah.jsp";
    		
    		context.put("mode","paparHakmilik");
    		context.put("readonly","readonly");
    		
    		
    		
    		prmhnnUPT.setListPohon(idPermohonan);
  			listPohon = prmhnnUPT.getListPohon();
  		
  			prmhnnUPT.setListPohon2(idPermohonan);
  			listPohon2 = prmhnnUPT.getListPohon2();
  			
  			prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		Hashtable j = (Hashtable) listPohon2.get(0);
    		context.put("id_existNegeri", j.get("idProjekNegeri").toString());
    		context.put("existNegeri", j.get("projek_negeri").toString());
    		
    		Hashtable i = (Hashtable) listPohon.get(0);
    		context.put("existDaerah", i.get("daerah").toString());
    		context.put("id_existDaerah", i.get("idDaerah").toString());
//    		context.put("existMukim", i.get("mukim").toString());
//    		context.put("id_existMukim", i.get("id_mukim").toString());
//    		
    		prmnhnnSPTMaklumatTanah.setMaklumatTanah(idHakmilik);
    		dataMaklumatTanah = prmnhnnSPTMaklumatTanah.getMaklumatTanah();
    		
    		
    		Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
        	
    		context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("lenisHakmilik",Long.parseLong(h.get("id_jenishakmilik").toString()),"style=width:210px class=disabled disabled"));
    		context.put("SelectLuas",HTML.SelectLuas("luas",Long.parseLong(h.get("id_luasLot").toString()),"style=width:210px class=disabled disabled"));
    		context.put("SelectLot", HTML.SelectLot("lot",Long.parseLong(h.get("id_lot").toString()),"style=width:167px class=disabled disabled"));
     		context.put("SelectBandar",HTML.SelectMukim("bandar",Long.parseLong(h.get("id_mukim").toString()),"class=disabled disabled style=width:300px"));
    		context.put("noHakmilik",h.get("no_hakmilik"));
    		context.put("noLot", h.get("no_lot"));
    		context.put("luasLot", h.get("luas_lot"));
    		context.put("anggaranLuas",h.get("luas_ambil"));
    		context.put("idTanah",h.get("id_hakmilik"));
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    		
    	}//close update maklumat tanah
        
    	else if ("deleteMaklumatTanah".equals(action)) {
    	      
    		deleteMaklumatTanah(session);
  	      	
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";

	  	    context.put("mode", "papar");
	  		context.put("readonly", "readonly");
	  		context.put("disabled", "disabled");
	  		
  	      	prmhnnUPT.setListPohon(idPermohonan);
  			listPohon = prmhnnUPT.getListPohon();
  			
  			prmhnnUPT.setListPohon2(idPermohonan);
  			listPohon2 = prmhnnUPT.getListPohon2();
  		
  			prmhnnUPT.setListMaklumatTanah(idPermohonan);
  			listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
  			
  			prmhnnUPT.setListDokumen(idPermohonan);
    		listDokumen = prmhnnUPT.getListDokumen();
    		
		    context.put("listDokumen", listDokumen);
		    context.put("listD_size", listDokumen.size());
  		
  			context.put("project_negeri",HTML.SelectNegeri("projek_negeri"));
  		
  			context.put("SelectDaerah",HTML.SelectDaerah("daerah"));
  		
  			Hashtable h = (Hashtable) listPohon.get(0);
      	
  			context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Long.parseLong(h.get("idNegeri").toString()),"class=disabled disabled"));
  			context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Long.parseLong(h.get("idKementerian").toString()),"class=disabled disabled"));
  			context.put("SelectAgensi",HTML.SelectAgensi("agensi",Long.parseLong(h.get("idAgensi").toString()),"class=disabled disabled"));
  			context.put("SelectDaerah",HTML.SelectDaerah("daerah",Long.parseLong(h.get("idDaerah").toString()),"class=disabled disabled"));
  			context.put("noPermohonan", h.get("noPermohonan"));
    		context.put("tarikhPohon",h.get("tarikh_permohonan"));
        	context.put("addTujuan", h.get("tujuan"));
     		context.put("addPoskod", h.get("poskod"));
     		context.put("addAlamat1", h.get("alamat1"));
     		context.put("addAlamat2", h.get("alamat2"));
     		context.put("addAlamat3", h.get("alamat3"));
     		context.put("addTarikhHendak", h.get("tarikh_kehendaki"));
     		context.put("addTarikhSurat", h.get("tarikh_surat"));
     		context.put("addRujukan_kementerian", h.get("no_rujukan_surat"));
     		context.put("idSuburusan", h.get("idSuburusan"));
     		context.put("noRujPTG", h.get("noRujPTG"));
     		context.put("noRujPTD",h.get("noRujPTD"));
     		
  			Hashtable i = (Hashtable) listPohon2.get(0);
  			context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Long.parseLong(i.get("idProjekNegeri").toString()),"class=disabled disabled"));
  		    
		    
  			context.put("listMaklumatTanah", listMaklumatTanah);
  			context.put("saiz_listTanah", listMaklumatTanah.size());
  		
  	    }
    	else if("sah".equals(action))
    	{
    		
    		
    		sah(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
    		
     		context.put("mode", "papar");
    		
    		prmhnnUPT.setListPohon(idPermohonan);
    		listPohon = prmhnnUPT.getListPohon();
    		
    		prmhnnUPT.setListPohon2(idPermohonan);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		prmhnnUPT.setListDokumen(idPermohonan);
    		listDokumen = prmhnnUPT.getListDokumen();
    		
		    context.put("listDokumen", listDokumen);
		    context.put("listD_size", listDokumen.size());
    		
    		Hashtable h = (Hashtable) listPohon.get(0);
    	
    		
    		currentStatus = Long.parseLong(h.get("id_status").toString());
    		currentSuburusan = Long.parseLong(h.get("idSuburusan").toString());
    		
    		context.put("currentSuburusan", currentSuburusan);
    		context.put("currentStatus", currentStatus);
    		
    		String idAGENSI = h.get("idAgensi").toString();
    		
    		
        	if(idAGENSI!=""){
        		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Long.parseLong(idAGENSI),"style=width:470px class=disabled disabled"));
        	}else{
        		context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:470px class=disabled disabled"));
        	}
    		
     		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Long.parseLong(h.get("idNegeri").toString()),"style=width:300px class=disabled disabled"));
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Long.parseLong(h.get("idKementerian").toString()),"style=width:470px class=disabled disabled"));
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Long.parseLong(h.get("idAgensi").toString()),"style=width:470px class=disabled disabled"));
    		context.put("SelectDaerah",HTML.SelectDaerah("daerah",Long.parseLong(h.get("idDaerah").toString()),"style=width:300px class=disabled disabled"));
//    		context.put("SelectBandar",HTML.SelectMukim("bandar",Long.parseLong(h.get("id_mukim").toString()),"style=width:300px class=disabled disabled"));
    		context.put("noPermohonan", h.get("noPermohonan"));
    		context.put("no_fail",h.get("no_fail"));
    		context.put("tarikhPohon",h.get("tarikh_permohonan"));
        	context.put("addTujuan", h.get("tujuan"));
     		context.put("addPoskod", h.get("poskod"));
     		context.put("addAlamat1", h.get("alamat1"));
     		context.put("addAlamat2", h.get("alamat2"));
     		context.put("addAlamat3", h.get("alamat3"));
     		context.put("addTarikhHendak", h.get("tarikh_kehendaki"));
     		context.put("addTarikhSurat", h.get("tarikh_surat"));
     		context.put("addRujukan_kementerian", h.get("no_rujukan_surat"));
     		context.put("idSuburusan", h.get("idSuburusan"));
     		context.put("status",h.get("status"));
     		context.put("noRujPTG", h.get("noRujPTG"));
     		context.put("noRujPTD",h.get("noRujPTD"));
     		
    		Hashtable i = (Hashtable) listPohon2.get(0);
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Long.parseLong(i.get("idProjekNegeri").toString()),"style=width:300px class=disabled disabled"));
    		
    		Hashtable z = (Hashtable) listPohon.get(0);
			if (z.get("flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (z.get("flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    		
			Hashtable fg = (Hashtable) listPohon.get(0);
			if (fg.get("flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (fg.get("flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    		
    		
    		
    		
    	}//close sah
    	
    	else if("lulus".equals(action))
    	{
    		lulus(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraSenaraiPermohonanUPT.jsp";
    		 
			senaraiPrmhnnUPT.setListPemohon(tarikhmohon,nopermohonan,cStatus);
    		listPermohonanUPT = senaraiPrmhnnUPT.getList();
    		
    		context.put("PermohonanListSPT", listPermohonanUPT);
    		context.put("list_size", listPermohonanUPT.size());
    		context.put("carianPermohonan", "");
			context.put("carianTarikh", "");
			setupPage(session,action,listPermohonanUPT);
		   
    	
    	}//close lulus

        else if ("doChangeidNegeri".equals(submit)) {
        	vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
        	context.put("action", "baru");
    		//----------------get and post all field content---------------------//
        	String now = "";
      		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
      		
    		context.put("noPermohonan", "");

      		
      		context.put("tarikhPohon", now);
        	context.put("addTujuan", getParam("tujuan"));
    		context.put("addPoskod", getParam("poskod"));
    		context.put("addAlamat1", getParam("alamat1"));
    		context.put("addAlamat2", getParam("alamat2"));
    		context.put("addAlamat3", getParam("alamat3"));
    		context.put("addTarikhSurat", getParam("tarikh_surat"));
    		context.put("addTarikhHendak", getParam("tarikh_kehendaki"));
    		context.put("addRujukan_kementerian", getParam("rujukan_kementerian"));
    		context.put("noRujPTG", getParam("noRujPTG"));
     		context.put("noRujPTD",getParam("noRujPTD"));
    		context.put("mode","baru");
    		
    		
    		String idKementerian = getParam("kementerian");
    		String idAgensi = getParam("agensi");
    		String idBandar = getParam("bandar");
    		String idNegeri = getParam("negeri");
    		
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),null,"style=width:500px"));	
    		context.put("SelectBandar",HTML.SelectMukim("bandar",Utils.parseLong(idBandar),"style=width:300px"));
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Utils.parseLong(idNegeri),"style=width:300px"));
    		
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doChangeidKementerian();\" "));
    		
    		if (getParam("new_flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (getParam("new_flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    		
			if (getParam("new_flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("new_flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			
			String idProjekNegeri = getParam("projek_negeri");
 
    		
//    		String now = "";
//    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
//    		context.put("currentDATE",getParam("tarikh_permohonan"));
//    		
    		context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		//context.put("saiz_listTanah", listMaklumatTanah.size());
    			
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidNegeri();\" "));
    		
    		if(idProjekNegeri!=""){
    			context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idProjekNegeri,"daerah",null,null,"style=width:300px onChange=\"doChangeidMukim();\""));
    		}else{
    			context.put("SelectDaerah",HTML.SelectDaerah("daerah",null,null,"style=width:300px onChange=\"doChangeidMukim();\""));
    		}
    		
    		context.put("listMaklumatTanah", "");
        }
        else if ("doChangeidKementerian".equals(submit)) {
        	vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
        	context.put("action", "baru");

    		String idKementerian = getParam("kementerian");
    		int idK = Integer.parseInt(idKementerian);
    		
    		alamatKementerian = prmhnnUPT.getAlamatKementerian(idKementerian);
    		
    		String id_negeriAgensi = "";
    		
    		if(alamatKementerian.size()!=0){
    			Hashtable AK = (Hashtable) alamatKementerian.get(0);
    			id_negeriAgensi = AK.get("id_negeri").toString();
    			alamat1 = AK.get("alamat1").toString();
    			alamat2 = AK.get("alamat2").toString();
    			alamat3 = AK.get("alamat3").toString();
    			poskod = AK.get("poskod").toString();
    		}
    		
    		//----------------get and post all field content---------------------//
    		String now = "";
      		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
      		
      		
    		context.put("noPermohonan", "");
      		
      		context.put("tarikhPohon", now);
    		context.put("addTujuan", getParam("tujuan"));
    		context.put("addPoskod", poskod);
    		context.put("addAlamat1", alamat1);
    		context.put("addAlamat2", alamat2);
    		context.put("addAlamat3", alamat3);
    		context.put("addTarikhSurat", getParam("tarikh_surat"));
    		context.put("addTarikhHendak", getParam("tarikh_kehendaki"));
    		context.put("addRujukan_kementerian", getParam("rujukan_kementerian"));
    		context.put("noRujPTG", getParam("noRujPTG"));
     		context.put("noRujPTD",getParam("noRujPTD"));
    		context.put("mode","baru");
    		context.put("status","PERMOHONAN CAWANGAN");
    		
    		String idBandar = getParam("bandar");
    		String idDaerah = getParam("daerah");
    		String idProjekNegeri = getParam("projek_negeri");
    		String idMukim = getParam("bandar");
    		
    		negeriLogin = prmhnnUPT.getIdNegeriByLogin(userId);
      		Hashtable hN = (Hashtable)negeriLogin.get(0);
    		
    		//dropdown maklumat negeri agensi
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Utils.parseLong(id_negeriAgensi),"style=width:325px"));
    		
    		//dropdown daerah projek
    		context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idProjekNegeri,"daerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukim();\""));
    		
    		//dropdown negeri projek
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(hN.get("ID_NEGERI").toString()),null,"style=width:300px onChange=\"doChangeidNegeri();\" "));
    		
    		//dropdown kementerian
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:500px onChange=\"doChangeidKementerian();\" "));
    		
    		//dropdown agensi by id kementerian
    		if(idKementerian!=""){
    			context.put("SelectAgensi",HTML.SelectAgensiByKementerian("agensi",idKementerian,null,"style=width:500px"));
    		}else{
    			context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:500px"));	
    		}
    		
    		//dropdown mukim projek
    		context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"bandar",Utils.parseLong(idMukim),"style=width:300px"));
    		
    		
    		//currentdate
//    		String now = "";
//    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
//    		context.put("currentDATE",getParam("tarikh_permohonan"));
//    		
    		context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		//context.put("saiz_listTanah", listMaklumatTanah.size());
    		
    		context.put("listMaklumatTanah", "");
		}
        else if ("doChangeidKementerianUpdate".equals(submit)) {
        	
			vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
    		
    		context.put("mode", "kemaskini");
    		
    		String idEditKementerian = getParam("kementerian");
    		int idK = Integer.parseInt(idEditKementerian);
    		
    		alamatKementerian = prmhnnUPT.getAlamatKementerian(idEditKementerian);
    		
    		Hashtable AK = (Hashtable) alamatKementerian.get(0);
    		
    		AK_negeri = AK.get("id_negeri").toString();
    		alamat1 = AK.get("alamat1").toString();
    		alamat2 = AK.get("alamat2").toString();
    		alamat3 = AK.get("alamat3").toString();
    		poskod = AK.get("poskod").toString();
    		int _poskod = Integer.parseInt(poskod);
    		//----------------get and post all field content---------------------//
    		
    		if(alamat1!="")
    		{
    			context.put("addPoskod", poskod);
        		context.put("addAlamat1", alamat1);
        		context.put("addAlamat2", alamat2);
        		context.put("addAlamat3", alamat3);	
    		}else{
    			context.put("addPoskod", getParam("poskod"));
        		context.put("addAlamat1", getParam("alamat1"));
        		context.put("addAlamat2", getParam("alamat2"));
        		context.put("addAlamat3", getParam("alamat3"));
    		}
     		
    		String idBandar = getParam("bandar");
     		String idNegeri = AK_negeri;
    		String idDaerah = getParam("daerah");
    		String idProjekNegeri = getParam("projek_negeri");
    		String idAgensi = getParam("agensi");
    		
    		negeriLogin = prmhnnUPT.getIdNegeriByLogin(userId);
      		Hashtable hN = (Hashtable)negeriLogin.get(0);
    		
    		prmhnnUPT.setListPohon(idPermohonan);
    		listPohon = prmhnnUPT.getListPohon();
    		Hashtable h = (Hashtable) listPohon.get(0);
    		
    		prmhnnUPT.setListPohon2(idPermohonan);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		prmhnnUPT.setListDokumen(idPermohonan);
    		listDokumen = prmhnnUPT.getListDokumen();
    		
		    context.put("listDokumen", listDokumen);
		    context.put("listD_size", listDokumen.size());
     		
    		if(idProjekNegeri!="")
    		{
    			context.put("SelectBandar",HTML.SelectMukimByNegeri(idProjekNegeri,"bandar",Long.parseLong(h.get("id_mukim").toString()),"style=width:300px"));
    		}else{
    			context.put("SelectBandar",HTML.SelectMukim("bandar",Long.parseLong(h.get("id_mukim").toString()),"style=width:300px"));
    		}
      		
    		context.put("noPermohonan", h.get("noPermohonan"));
    		context.put("tarikhPohon",h.get("tarikh_permohonan"));
        	context.put("addTujuan", h.get("tujuan"));
     		context.put("addTarikhHendak", h.get("tarikh_kehendaki"));
     		context.put("addTarikhSurat", h.get("tarikh_surat"));
     		context.put("addRujukan_kementerian", h.get("no_rujukan_surat"));
     		context.put("idSuburusan", h.get("idSuburusan"));
     		context.put("noRujPTG", h.get("noRujPTG"));
     		context.put("noRujPTD",h.get("noRujPTD"));
    		
    		context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idProjekNegeri,"daerah",Utils.parseLong(idDaerah),"style=width:300px"));
    		
    		Hashtable i = (Hashtable) listPohon2.get(0);
    		context.put("idFail", i.get("id_fail"));
    		
    		if (getParam("new_flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (getParam("new_flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    		
			if (getParam("new_flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("new_flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			
    		
    		context.put("SelectAgensi", getParam("agensi"));
    		if(idEditKementerian!="")
    		{
    			context.put("SelectAgensi",HTML.SelectAgensiByKementerian("agensi",idEditKementerian,null,"style=width:470px"));
    		}
    		else{
    			context.put("SelectAgensi",HTML.SelectAgensi("agensi",null,"style=width:470px"));	
    		}
    		
    		//--Filter--//
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idEditKementerian),null,"style=width:470px onChange=\"doChangeidKementerianUpdate();\" "));
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Utils.parseLong(idNegeri),"style=width:300px"));
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUpdate();\" "));
    		
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	
	        

   	    }//dochangeidkementerianupdate 
        else if ("doChangeidProjekNegeriUpdate".equals(submit)) {
        	
			vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";

    		context.put("mode", "kemaskini");
    		
    		
    		
    		String idEditProjekNegeri = getParam("projek_negeri");
    		int idPN = Integer.parseInt(idEditProjekNegeri);
    		
    		String idEditKementerian = getParam("kementerian");
    		int idK = Integer.parseInt(idEditKementerian);
    		
    		alamatKementerian = prmhnnUPT.getAlamatKementerian(idEditKementerian);
    		
    		Hashtable AK = (Hashtable) alamatKementerian.get(0);
    		
    		AK_negeri = AK.get("id_negeri").toString();
    		alamat1 = AK.get("alamat1").toString();
    		alamat2 = AK.get("alamat2").toString();
    		alamat3 = AK.get("alamat3").toString();
    		poskod = AK.get("poskod").toString();
    		
    		//----------------get and post all field content---------------------//
    		
    		context.put("addPoskod", poskod);
    		context.put("addAlamat1", alamat1);
    		context.put("addAlamat2", alamat2);
    		context.put("addAlamat3", alamat3);	
    		
    		String idBandar = getParam("bandar");
    		String idDaerah = getParam("daerah");
    		String idNegeri = getParam("negeri");
    		String idAgensi = getParam("agensi");
    		String idKementerian = getParam("kementerian");
    		
    		prmhnnUPT.setListPohon(idPermohonan);
    		listPohon = prmhnnUPT.getListPohon();
    		
    		prmhnnUPT.setListPohon2(idPermohonan);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		prmhnnUPT.setListDokumen(idPermohonan);
    		listDokumen = prmhnnUPT.getListDokumen();
    		
		    context.put("listDokumen", listDokumen);
		    context.put("listD_size", listDokumen.size());
    		
    		Hashtable i = (Hashtable) listPohon2.get(0);
    		context.put("idFail", i.get("id_fail"));
     		
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(idEditProjekNegeri),null,"style=width:300px onChange=\"doChangeidNegeriUpdate();\" "));

    		if(idNegeri!="")
    		{
    			context.put("SelectBandar",HTML.SelectMukimByNegeri(idNegeri,"bandar",null,"style=width:300px"));
    		}else{
    			context.put("SelectBandar",HTML.SelectMukim("bandar",null,"style=width:300px"));
    		}
    		if(idEditProjekNegeri!="")
    		{
    			context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idEditProjekNegeri,"daerah",null,null,"style=width:300px onChange=\"doChangeidMukimUpdate();\""));
    		}else{
    			context.put("SelectDaerah",HTML.SelectDaerah("daerah",null,null,"style=width:300px onChange=\"doChangeidMukimUpdate();\""));
    		}
      		
    		Hashtable h = (Hashtable)listPohon.get(0);
    		
    		context.put("noPermohonan", h.get("noPermohonan"));
    		context.put("tarikhPohon",h.get("tarikh_permohonan"));
        	context.put("addTujuan", h.get("tujuan"));
     		context.put("addTarikhHendak", h.get("tarikh_kehendaki"));
     		context.put("addTarikhSurat", h.get("tarikh_surat"));
     		context.put("addRujukan_kementerian", h.get("no_rujukan_surat"));
     		context.put("idSuburusan", h.get("idSuburusan"));
     		context.put("noRujPTG", h.get("noRujPTG"));
     		context.put("noRujPTD",h.get("noRujPTD"));
    		
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),null,"style=width:470px"));
    		
    		if (getParam("new_flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (getParam("new_flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    		
			if (getParam("new_flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("new_flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			
			
		   
		    //--Filter--//
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:470px onChange=\"doChangeidKementerianUpdate();\" "));
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Utils.parseLong(idNegeri),"style=width:300px"));
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(idEditProjekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUpdate();\" "));
    		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
   	    	
   	    }//dochangeidprojeknegeriupdate
        else if ("doChangeidMukim".equals(submit)) {
        	
        	vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
        	context.put("action", "baru");

    		//----------------get and post all field content---------------------//
        	String now = "";
      		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
      		
      		context.put("tarikhPohon", now);
        	context.put("addTujuan", getParam("tujuan"));
    		context.put("addPoskod", getParam("poskod"));
    		context.put("addAlamat1", getParam("alamat1"));
    		context.put("addAlamat2", getParam("alamat2"));
    		context.put("addAlamat3", getParam("alamat3"));
    		context.put("addTarikhSurat", getParam("tarikh_surat"));
    		context.put("addTarikhHendak", getParam("tarikh_kehendaki"));
    		context.put("addRujukan_kementerian", getParam("rujukan_kementerian"));
    		context.put("noRujPTG", getParam("noRujPTG"));
     		context.put("noRujPTD",getParam("noRujPTD"));
    		context.put("mode","baru");
    		context.put("noPermohonan","");
    		
    		String SuburusanUPT = getParam("socSuburusan");
    		String idKementerian = getParam("kementerian");
    		String idAgensi = getParam("agensi");
    		String idDaerah = getParam("daerah");
    		String idBandar = getParam("bandar");
    		String idNegeri = getParam("negeri");
    		String idProjekNegeri = getParam("projek_negeri");
    		
    		context.put("SelectSuburusanUPT",HTML.SelectSuburusanUPT("socSuburusan",Utils.parseLong(SuburusanUPT),"style=width:400px"));
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),null,"style=width:470px"));	
    		//context.put("SelectBandar",HTML.SelectMukim("bandar",Utils.parseLong(idBandar),"class=mediumselect"));
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Utils.parseLong(idNegeri),"style=width:300px",null));
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(idProjekNegeri),null,"style=width:300px onChange=\"doChangeidNegeri();\" "));
    		
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:470px onChange=\"doChangeidKementerian();\" "));
    		
    		if (getParam("new_flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (getParam("new_flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    		
			if (getParam("new_flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("new_flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
    		//--------------------------------------------------------------------//
    		
   	    
    		
//    		String now = "";
//    		
//    		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
//    		context.put("currentDATE",getParam("tarikh_permohonan"));
//    		
    		context.put("currentSuburusan", "");
    		context.put("currentStatus", "");
    		//context.put("saiz_listTanah", listMaklumatTanah.size());
    			
    		context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"bandar",null,"style=width:300px"));
    		context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idProjekNegeri,"daerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukim();\""));
    		
    		context.put("listMaklumatTanah", "");
		}
        else if ("doChangeidMukimUpdate".equals(submit)) {
        	vm = "app/ppt/frmHakmilikSementaraPermohonanUPT.jsp";
        	
    		context.put("mode","kemaskini");
    		
    		String idDaerah = getParam("daerah");
    		int idD = Integer.parseInt(idDaerah);
    		
    		String idEditKementerian = getParam("kementerian");
    		int idK = Integer.parseInt(idEditKementerian);
    		
    		alamatKementerian = prmhnnUPT.getAlamatKementerian(idEditKementerian);
    		
    		Hashtable AK = (Hashtable) alamatKementerian.get(0);
    		AK_negeri = AK.get("id_negeri").toString();
    		alamat1 = AK.get("alamat1").toString();
    		alamat2 = AK.get("alamat2").toString();
    		alamat3 = AK.get("alamat3").toString();
    		poskod = AK.get("poskod").toString();
    		
    		//----------------get and post all field content---------------------//
    		
    		context.put("addPoskod", poskod);
    		context.put("addAlamat1", alamat1);
    		context.put("addAlamat2", alamat2);
    		context.put("addAlamat3", alamat3);	
    		
        	String idEditProjekNegeri = getParam("projek_negeri");	
    		String idNegeri = getParam("negeri");
    		String idAgensi = getParam("agensi");
    		String idKementerian = getParam("kementerian");
    		
    		
    		prmhnnUPT.setListPohon(idPermohonan);
    		listPohon = prmhnnUPT.getListPohon();
    		
    		prmhnnUPT.setListPohon2(idPermohonan);
    		listPohon2 = prmhnnUPT.getListPohon2();
    		
    		prmhnnUPT.setListMaklumatTanah(idPermohonan);
    		listMaklumatTanah = prmhnnUPT.getListMaklumatTanah();
    		
    		prmhnnUPT.setListDokumen(idPermohonan);
    		listDokumen = prmhnnUPT.getListDokumen();
    		
		    context.put("listDokumen", listDokumen);
		    context.put("listD_size", listDokumen.size());
     		
    		Hashtable i = (Hashtable) listPohon2.get(0);
    		context.put("idFail", i.get("id_fail"));
    		
    		if(idDaerah!="")
    		{
    			context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"bandar",null,"style=width:300px"));
    		}else{
    			context.put("SelectBandar",HTML.SelectDaerah("bandar",null,"style=width:300px"));
    		}
      		
    		Hashtable h = (Hashtable)listPohon.get(0);
    		context.put("noPermohonan", h.get("noPermohonan"));
    		context.put("tarikhPohon",h.get("tarikh_permohonan"));
        	context.put("addTujuan", h.get("tujuan"));
     		context.put("addTarikhHendak", h.get("tarikh_kehendaki"));
     		context.put("addTarikhSurat", h.get("tarikh_surat"));
     		context.put("addRujukan_kementerian", h.get("no_rujukan_surat"));
     		context.put("idSuburusan", h.get("idSuburusan"));
     		context.put("noRujPTG", h.get("noRujPTG"));
     		context.put("noRujPTD",h.get("noRujPTD"));
    		
    		context.put("SelectAgensi",HTML.SelectAgensi("agensi",Utils.parseLong(idAgensi),null,"style=width:470px"));
    		
    		if (getParam("new_flag_peruntukan").equals("1")){
				checkedAda = "checked";
				checkedTiada = "";
			} else if (getParam("new_flag_peruntukan").equals("2")){
				checkedAda = "";
				checkedTiada = "checked";
			}
			context.put("TEMPcheckedAda",checkedAda);
			context.put("TEMPcheckedTiada",checkedTiada);
    		
			if (getParam("new_flag_segera").equals("1")){
				checkedYa = "checked";
				checkedTidak = "";
			} else if (getParam("new_flag_segera").equals("2")){
				checkedYa = "";
				checkedTidak = "checked";
			}
			
			context.put("TEMPcheckedYa",checkedYa);
			context.put("TEMPcheckedTidak",checkedTidak);
			
    		
    		//--------------------------------------------------------------------//
		   
    		//--Filter--//
    		context.put("SelectKementerian",HTML.SelectKementerian("kementerian",Utils.parseLong(idKementerian),null,"style=width:470px onChange=\"doChangeidKementerianUpdate();\" "));
    		context.put("SelectNegeri",HTML.SelectNegeriMampu("negeri",Utils.parseLong(idNegeri),null,"style=width:300px onChange=\"doChangeidNegeriUpdate();\" "));
    		context.put("project_negeri",HTML.SelectNegeriMampu("projek_negeri",Utils.parseLong(idEditProjekNegeri),null,"style=width:300px onChange=\"doChangeidProjekNegeriUpdate();\" "));
    		context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idEditProjekNegeri,"daerah",Utils.parseLong(idDaerah),null,"style=width:300px onChange=\"doChangeidMukimUpdate();\""));
    		
    		//----//
   		
    		context.put("listMaklumatTanah", listMaklumatTanah);
    		context.put("saiz_listTanah", listMaklumatTanah.size());
   	   
    		
	       
	        
   	    }//dochangeidmukimupdate
        else if("batal".equals(action))
    	{
        	vm = "app/ppt/frmHakmilikSementaraSenaraiPermohonanUPT.jsp";
			senaraiPrmhnnUPT.setListPemohon(tarikhmohon,nopermohonan,cStatus);
        	listPermohonanUPT = senaraiPrmhnnUPT.getList();
    		
    		context.put("PermohonanListSPT", listPermohonanUPT);
    		context.put("list_size", listPermohonanUPT.size());
			context.put("carianPermohonan", "");
			context.put("carianTarikh", "");
			setupPage(session,action,listPermohonanUPT);
		    
		    
    		
    		  		
    	}
        
        else{
	        vm = "app/ppt/frmHakmilikSementaraSenaraiPermohonanUPT.jsp";
	        
	        if (!"".equals(getParam("txdTarikh")));
			tarikhmohon = getParam("txdTarikh");
		
			if (!"".equals(getParam("txtNoFail")));
				nopermohonan = getParam("txtNoFail");
				
			if(!"".equals(getParam("socStatus")))
				cStatus = getParam("socStatus");
			
			senaraiPrmhnnUPT.setListPemohon(tarikhmohon,nopermohonan,cStatus);
			listPermohonanUPT = senaraiPrmhnnUPT.getList();
	        context.put("PermohonanListSPT", listPermohonanUPT);
	        context.put("carianPermohonan", nopermohonan);
			context.put("carianTarikh", tarikhmohon);
		    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
	        setupPage(session,action,listPermohonanUPT);
        }
        
        
        return vm;
	}
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
	private String addUPT(HttpSession session) throws Exception{
	    Hashtable h = new Hashtable();
	    String now = "";
  		now = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
  		
	    	h.put("suburusan", "53");
		    h.put("tarikh_permohonan", now);
		    h.put("noRujPTG", getParam("noRujPTG"));
		    h.put("noRujPTD",getParam("noRujPTD"));
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
//	    	h.put("bandar", getParam("bandar"));
	    	h.put("poskod", getParam("poskod"));
	    	h.put("projek_negeri", getParam("projek_negeri"));
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	return FrmHakmilikSementaraPermohonanUPTData.addUPT(h);
	  
	  }//close add
	
	private void update(HttpSession session) throws Exception
	  {
		
	    Hashtable h = new Hashtable();
	    
	    	h.put("id_urusan", getParam("suburusan"));
	    
	    	h.put("id_permohonan", getParam("id_permohonan"));
	    	h.put("noRujPTG", getParam("noRujPTG"));
		    h.put("noRujPTD",getParam("noRujPTD"));
	    	h.put("id_fail", getParam("id_fail"));
	    	h.put("poskod", getParam("poskod"));
	    	h.put("tujuan", getParam("tujuan"));
	    	h.put("rujukan_kementerian", getParam("rujukan_kementerian"));
	    	h.put("tarikh_hendak", getParam("tarikh_kehendaki"));
	    	h.put("tarikh_surat", getParam("tarikh_surat"));
	    	h.put("alamat1", getParam("alamat1"));
	    	h.put("alamat2", getParam("alamat2"));
	    	h.put("alamat3", getParam("alamat3"));
	    	h.put("flag_peruntukan", getParam("new_flag_peruntukan"));
	    	h.put("flag_segera", getParam("new_flag_segera"));
	    	h.put("daerah", getParam("daerah"));
	    	h.put("projeknegeri", getParam("projek_negeri"));
	    	h.put("negeri", getParam("negeri"));
//	    	h.put("bandar", getParam("bandar"));
	    	h.put("agensi", getParam("agensi"));
	    	h.put("kementerian", getParam("kementerian"));
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	FrmHakmilikSementaraPermohonanUPTData.update(h);
	    
	  }
	
	 private void sah(HttpSession session) throws Exception{
		    Hashtable h = new Hashtable();
		    h.put("id_permohonan", getParam("id_permohonan"));
		    h.put("id_fail", getParam("id_fail"));
			h.put("id_kementerian", getParam("idKementerianA"));
	    	h.put("projeknegeri", getParam("projeknegeri"));
	    	h.put("id_daerah",getParam("id_daerah"));
	    	h.put("daerah", getParam("daerah"));
			h.put("id_status", "127");
		    FrmHakmilikSementaraPermohonanUPTData.sahkan(h);  
	}
	 
	 private void lulus(HttpSession session) throws Exception{
		 
		 Hashtable h = new Hashtable();

		 h.put("id_permohonan", getParam("id_permohonan"));
		 h.put("id_fail", getParam("id_fail"));
		 h.put("id_kementerian", getParam("idKementerianA"));
		 h.put("id_negeri", getParam("idNegeriA"));
		 h.put("id_status", "127");
		 
		 FrmHakmilikSementaraPermohonanUPTData.lulus(h);
	  	}
	 private void add_maklumat_tanah(HttpSession session) throws Exception{
		    
			Hashtable h = new Hashtable();
		    
		    
		    	h.put("id_permohonan", getParam("id_permohonan"));
		    	h.put("negeri", getParam("id_existNegeri"));
		    	h.put("jenisHakMilik", getParam("jenisHakMilik"));
		    	h.put("daerah", getParam("id_existDaerah"));
		    	h.put("mukim", getParam("bandar"));
		    	h.put("luas", getParam("luas"));
		    	h.put("lot", getParam("lot"));
		   
		    	h.put("no_hakmilik", getParam("no_hakmilik"));
		    	h.put("no_lot", getParam("no_lot"));
		    	h.put("luas_lot", getParam("luas_lot"));
		    	//h.put("no_pt", getParam("no_pt"));
		    	h.put("anggaran_luas", getParam("anggaran_luas"));
		    
		    	FrmHakmilikSementaraPermohonanUPTMaklumatTanahData.add_maklumat_tanah(h);
		    
		   }//close add_maklumat_tanah
	 
	 private void updateMaklumatTanah(HttpSession session) throws Exception{
		 Hashtable i = new Hashtable();

		 String id_hakmilik = getParam("id_hakmilik");
		 
		    i.put("id_hakmilik", id_hakmilik);
		    i.put("editNegeri", getParam("id_existNegeri"));
		    i.put("editJenisHakmilik", getParam("jenisHakmilik"));
		    i.put("editDaerah", getParam("id_existDaerah"));
		    i.put("editMukim", getParam("id_existMukim"));
		    i.put("editLuas", getParam("luas"));
		    i.put("editLot", getParam("lot"));
		   
		    i.put("edit_no_hakmilik", getParam("no_hakmilik"));
		    i.put("edit_no_lot", getParam("no_lot"));
		    i.put("edit_luas_lot", getParam("luas_lot"));
		    //i.put("edit_no_pt", getParam("edit_no_pt"));
		    i.put("edit_anggaran_luas", getParam("anggaran_luas"));
		    
		    FrmHakmilikSementaraPermohonanUPTMaklumatTanahData.updateMaklumatTanah(i);
	  }
	 private void deleteMaklumatTanah(HttpSession session) throws Exception{
		    String id = getParam("id_hakmilik");	    
		    FrmHakmilikSementaraPermohonanUPTMaklumatTanahData.deleteMaklumatTanah(id);
		  }
	 
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
}
