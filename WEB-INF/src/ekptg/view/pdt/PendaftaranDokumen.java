package ekptg.view.pdt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.pdt.FrmDaftarLainDokumenData;
import ekptg.model.pdt.FrmKemaskiniLainDokumenData;

public class PendaftaranDokumen extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7846266034917412011L;
	private static Logger myLog = Logger.getLogger(ekptg.view.pdt.PendaftaranDokumen.class);
	
	FrmDaftarLainDokumenData dokumen = new FrmDaftarLainDokumenData();
	FrmKemaskiniLainDokumenData kemaskiniDokumen = new FrmKemaskiniLainDokumenData();
	
	public String doTemplate2() throws Exception {
		//debugParams();
		String vm = "";
		String submit = getParam("command");
		String noRujukanDokumen = "";
		String tajukDokumen = "";
		String tarikhDokumen = "";
		String sksyn = "0";
		String seksyen = getParam("socSeksyen");
		String idFail = getParam("socFail");
		String idDokumen = getParam("idDokumen");
		String noRujDok = "";
		String tjkDok = "";
		String tkhDok = "";
		String noFail = getParam("txtNamaFail");
		String idJnsDok = getParam("socJenisDokumen");
		String catatan = "";
		String tkhDaftar = "";
		String tagDokumen = getParam("tagDokumen");
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		HttpSession session = this.request.getSession();		
			
		String action = getParam("command");
		myLog.info("action ---- "+action);
		String action_ = getParam("action");
		myLog.info("action_ ---- "+action_);
		Vector list = new Vector();
		this.context.put("action",action_); 		
		this.context.put("SenaraiLampiran","");
		
//		this.context.put("readOnly","");
//		this.context.put("disabled","");
		
		if (action.equals("tambahDataBaru")){	
			//myLog.info("tambahDataBaru="+action);
			vm = "app/pdt/frmKemaskiniLainDokumen.jsp";
			this.context.put("idDokumen",idDokumen);
			noRujDok = getParam("txtNoRujDokumen");
			this.context.put("noRujDok", noRujDok);
			tjkDok = getParam("txtTajukDokumen");
			this.context.put("tjkDok",tjkDok);
			tkhDok = getParam("txdTarikhDokumen");
			this.context.put("tkhDok", tkhDok);
			noFail = getParam("txtNamaFail");
			this.context.put("noFail",noFail);
			catatan = getParam("txtCatatan");
			this.context.put("catatan",catatan);
			this.context.put("tkhDaftar",sdf.format(now));
			tagDokumen = getParam("tagDokumen");
			this.context.put("tagDokumen", tagDokumen);
			System.out.println("tag dokumen--"+tagDokumen);
			
			
			this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(idJnsDok),""));			
			//this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(seksyen),null,"onChange=\"doChangeSeksyen();\" "));
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(seksyen),null,null));
      	  
    	    if ("doChangeSeksyen".equals(submit)) {
    	    	this.context.put("selectFail",HTML.SelectFailByIdSeksyen(seksyen,"socFail",null,null));
    		}else {
    		   this.context.put("selectFail",HTML.SelectFail("socFail"));
    		}
    	
	    	//this.context.put("readOnly","");
	    	this.context.put("mode","New");
			
		}else if ("tambahDataLain".equals(action)){
			vm = "app/pdt/frmKemaskiniLainDokumen.jsp";
			myLog.info("tambahDataLain="+action);
//			noRujDok = getParam("txtNoRujDokumen");
//			tjkDok = getParam("txtTajukDokumen");
//			tkhDok = getParam("txdTarikhDokumen");
//			catatan = getParam("txtCatatan");
			idJnsDok = "-1";
			seksyen = "-1";
			this.context.put("idDokumen","");
			this.context.put("noRujDok", noRujDok);
			this.context.put("tjkDok",tjkDok);
			this.context.put("tkhDok", tkhDok);
			this.context.put("catatan",catatan);
			this.context.put("tkhDaftar",sdf.format(now));
			
			this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(idJnsDok),""));
			
			//this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(seksyen),null,"onChange=\"doChangeSeksyen();\" "));
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(seksyen),null,null));
	      	  
//    	    if ("doChangeSeksyen".equals(submit)) {
//    	    	this.context.put("selectFail",HTML.SelectFailByIdSeksyen(seksyen,"socFail",null,null));
//    		} 
//    	    else {
//    		   this.context.put("selectFail",HTML.SelectFail("socFail"));
//    		}
    	
	    	//this.context.put("readOnly","");
	    	this.context.put("mode","New");
			
		}else if (action.equals("simpan")){
			vm = "app/pdt/frmKemaskiniLainDokumen.jsp";
			myLog.info("simpan="+action);
			simpan(session);
			
			noRujDok = getParam("txtNoRujDokumen");
			this.context.put("noRujDok", noRujDok);
			tjkDok = getParam("txtTajukDokumen");
			this.context.put("tjkDok",tjkDok);
			tkhDok = getParam("txdTarikhDokumen");
			this.context.put("tkhDok", tkhDok);
			noFail = getParam("txtNamaFail");
			this.context.put("noFail",noFail);
			catatan = getParam("txtCatatan");
			this.context.put("catatan",catatan);
			this.context.put("tkhDaftar",sdf.format(now));
			this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(idJnsDok),"disabled"));
			//this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(getParam("socSeksyen")),null,null));
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(seksyen),null,null));
			//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(idFail),"disabled"));
			//this.context.put("readOnly","readonly");
        	this.context.put("mode","View");
        	tagDokumen = getParam("tag_dokumen");
			this.context.put("tagDokumen", tagDokumen);
			System.out.println("tag_dokumen--------"+tagDokumen);
			
        	listLampiran(session);
        	list = kemaskiniDokumen.getListLampiran();
	    	this.context.put("SenaraiLampiran", list);
			
		}else if (action.equals("papar")){
			vm = "app/pdt/frmKemaskiniLainDokumen.jsp";
			view(session);
			list = kemaskiniDokumen.getData();
			this.context.put("mode","View");
			
			Hashtable h = (Hashtable) list.get(0);
			this.context.put("idDokumen",h.get("idDokumen"));
			this.context.put("noRujDok", h.get("noRujDok"));
			this.context.put("tjkDok",h.get("tjkDok"));
			this.context.put("tkhDok", h.get("tarikhDokumen"));
			this.context.put("catatan",h.get("catatan"));
			this.context.put("tkhDaftar",h.get("tarikhDaftar"));
			this.context.put("noFail",h.get("noFail"));
			//this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(h.get("idJenisdokumen").toString()),"disabled"));
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(h.get("idSeksyen").toString()),null,null));
			//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(h.get("idFail").toString()),"disabled"));
//			this.context.put("readOnly","readonly");
//			this.context.put("disabled","disabled");
			this.context.put("tag_Dokumen", h.get("tagging"));
			this.context.put("id_tagdokumen", h.get("idTagging"));
			this.context.put("idDokumen",idDokumen);
			
			listLampiran(session);
			list = kemaskiniDokumen.getListLampiran();
	    	this.context.put("SenaraiLampiran", list);
			
		}else if (action.equals("kemaskini")){
		
			myLog.info("idDokumen="+idDokumen);
			vm = "app/pdt/frmKemaskiniLainDokumen.jsp";
			
			view(session);
			list = kemaskiniDokumen.getData();
			this.context.put("mode","Update");
			
			Hashtable h = (Hashtable) list.get(0);
			this.context.put("idDokumen",h.get("idDokumen"));
			this.context.put("noRujDok", h.get("noRujDok"));
			this.context.put("tjkDok",h.get("tjkDok"));
			this.context.put("tkhDok", h.get("tarikhDokumen"));
			this.context.put("noFail",h.get("noFail"));
			this.context.put("catatan",h.get("catatan"));
			this.context.put("tkhDaftar",h.get("tarikhDaftar"));
			this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(h.get("idJenisDokumen").toString()),""));
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(h.get("idSeksyen").toString()),"",null));
			//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(h.get("idFail").toString()),""));
			//this.context.put("readOnly","");
			this.context.put("tag_Dokumen", h.get("tagging"));
			myLog.info("editData:tajuk="+getParam("tagDokumen"));
			this.context.put("id_tagdokumen", h.get("idTagging"));
			
			listLampiran(session);
			list = kemaskiniDokumen.getListLampiran();
	    	this.context.put("SenaraiLampiran", list);
		
		}else if ("editData".equals(action)){
			vm = "app/pdt/frmKemaskiniLainDokumen.jsp";
			myLog.info("editData:tajuk="+getParam("txtTajukDokumen"));
			myLog.info("editData:tajuk_="+getParam("txtTajukDokumen_"));
			update(session);
			idDokumen = getParam("idDokumen");
			this.context.put("idDokumen",idDokumen);
			noRujDok = getParam("txtNoRujDokumen");
			this.context.put("noRujDok", noRujDok);
			tjkDok = getParam("txtTajukDokumen");
			this.context.put("tjkDok",tjkDok);
			tkhDok = getParam("txdTarikhDokumen");
			this.context.put("tkhDok", tkhDok);
			catatan = getParam("txtCatatan");
			this.context.put("catatan",catatan);
			noFail = getParam("txtNamaFail");
			this.context.put("noFail",noFail);
			
			this.context.put("tkhDaftar",sdf.format(now));
			this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(idJnsDok),"disabled"));
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(seksyen),"disabled",null));
			//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(idFail),"disabled"));
//			this.context.put("readOnly","readonly");
        	this.context.put("mode","View");
        	tagDokumen = getParam("tagDokumen");
        	this.context.put("tagDokumen", tagDokumen);
			this.context.put("id_tagdokumen", getParam("id_tagdokumen"));
    	
			
			view(session);
			list = kemaskiniDokumen.getData();
			this.context.put("mode","View");
			
			Hashtable h = (Hashtable) list.get(0);
			this.context.put("idDokumen",h.get("idDokumen"));
			this.context.put("noRujDok", h.get("noRujDok"));
			this.context.put("tjkDok",h.get("tjkDok"));
			this.context.put("tkhDok", h.get("tarikhDokumen"));
			this.context.put("catatan",h.get("catatan"));
			this.context.put("tkhDaftar",h.get("tarikhDaftar"));
			this.context.put("noFail",h.get("noFail"));
			//this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(h.get("idJenisdokumen").toString()),"disabled"));
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(h.get("idSeksyen").toString()),null,null));
			//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(h.get("idFail").toString()),"disabled"));
//			this.context.put("readOnly","readonly");
//			this.context.put("disabled","disabled");
			this.context.put("tag_Dokumen", h.get("tagging"));
			this.context.put("id_tagdokumen", h.get("idTagging"));
			this.context.put("idDokumen",idDokumen);
			
			listLampiran(session);
			list = kemaskiniDokumen.getListLampiran();
	    	this.context.put("SenaraiLampiran", list);
			
			
			
			
        	listLampiran(session);
	    	list = kemaskiniDokumen.getListLampiran();
	    	this.context.put("SenaraiLampiran", list);
			
		}else if("hapus".equals(action)){
			
			vm = "app/pdt/frmKemaskiniLainDokumen.jsp";
			String id_Lampiran = getParam("idLampiran2");
			myLog.info("id_Lampiran from action -- "+id_Lampiran);
			hapusLampiran(session,id_Lampiran);
			
			myLog.info("id_dokumen from action -- "+idDokumen);
			
			view(session);
			list = kemaskiniDokumen.getData();
			this.context.put("mode","View");
			
			Hashtable h = (Hashtable) list.get(0);
			this.context.put("idDokumen",h.get("idDokumen"));
			this.context.put("noRujDok", h.get("noRujDok"));
			this.context.put("tjkDok",h.get("tjkDok"));
			this.context.put("tkhDok", h.get("tarikhDokumen"));
			this.context.put("catatan",h.get("catatan"));
			this.context.put("tkhDaftar",h.get("tarikhDaftar"));
			this.context.put("txtNamaFail",h.get("noFail"));
			//this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(h.get("idJenisdokumen").toString()),"disabled"));
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(h.get("idSeksyen").toString()),"disabled",null));
			//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(h.get("idFail").toString()),"disabled"));
//			this.context.put("readOnly","readonly");
//			this.context.put("disabled","disabled");
			this.context.put("tag_Dokumen", h.get("tagging"));
			this.context.put("id_tagdokumen", h.get("idTagging"));
			
			listLampiran(session);
			list = kemaskiniDokumen.getListLampiran();
	    	this.context.put("SenaraiLampiran", list);
	    	
	    	
	    	
		}else if("SenaraiDokumen".equals(action)){
			vm = "app/pdt/frmDaftarLainDokumen.jsp";
			
			dokumen.setCarianDokumen(noRujukanDokumen,tajukDokumen, tarikhDokumen, sksyn);
			list = dokumen.getList();
			setupPage(session,action,list);
			//this.context.put("SenaraiDokumen",list);
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(sksyn),""));
			this.context.put("noRujukanDokumen",noRujukanDokumen);
			this.context.put("tajukDokumen",tajukDokumen);
	    	this.context.put("tarikhDokumen", tarikhDokumen);
	    	
		}else if("Delete".equals(action)){
			vm = "app/pdt/frmDaftarLainDokumen.jsp";			
			dokumen.Delete(idDokumen);
			dokumen.setCarianDokumen(noRujukanDokumen,tajukDokumen, tarikhDokumen, sksyn);
			list = dokumen.getList();
			setupPage(session,action,list);
			//this.context.put("SenaraiDokumen",list);
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(sksyn),""));
			this.context.put("noRujukanDokumen",noRujukanDokumen);
			this.context.put("tajukDokumen",tajukDokumen);
	    	this.context.put("tarikhDokumen", tarikhDokumen);
		
		}else{		
			vm = "app/pdt/frmDaftarLainDokumen.jsp";
			String tag = getParam("tag_dokumen");
			if (!"".equals(getParam("socSeksyen")))
				sksyn = getParam("socSeksyen");
			if (!"".equals(getParam("txtNoRujDokuman")))
				noRujukanDokumen = getParam("txtNoRujDokuman");
			if (!"".equals(getParam("txtTajukDokumen")))
				tajukDokumen = getParam("txtTajukDokumen");
			if (!"".equals(getParam("txdTarikhDokumen")))
				tarikhDokumen = getParam("txdTarikhDokumen");
			
			//FrmDaftarLainDokumenData.setCarianDokumen(noRujukanDokumen,tajukDokumen, tarikhDokumen, sksyn);
			//list = FrmDaftarLainDokumenData.getList();
			list = dokumen.getCarianDokumen(noRujukanDokumen,tajukDokumen, tarikhDokumen, sksyn,tag);
			setupPage(session,action_,list);
			//this.context.put("SenaraiDokumen",list);
			this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(sksyn),""));
			this.context.put("noRujukanDokumen",noRujukanDokumen);
			this.context.put("tajukDokumen",tajukDokumen);
	    	this.context.put("tarikhDokumen", tarikhDokumen);
	    	this.context.put("tag_Dokumen",tag);
			
		}
		myLog.debug("NOFaillalalallala:"+noFail);
		//myLogger.debug(vm);
		return vm;
		
    }
	
	private void simpan(HttpSession session) throws Exception {
		String idDokumen = new String("0");
		Hashtable h = new Hashtable();
		h.put("no_Rujukan_Dokumen",getParam("txtNoRujDokumen"));
		h.put("tajuk_Dokumen",getParam("txtTajukDokumen"));
		h.put("id_Jenisdokumen",getParam("socJenisDokumen"));
		h.put("tarikh_Dokumen",getParam("txdTarikhDokumen"));
		h.put("id_Seksyen",getParam("socSeksyen"));
		h.put("id_Fail",getParam("socFail"));
		h.put("noFail",getParam("txtNamaFail"));
		h.put("catatan",getParam("txtCatatan"));
		h.put("tagDokumen", getParam("tag_dokumen"));
		h.put("idTagDokumen", getParam("id_tagdokumen"));
		
		idDokumen = kemaskiniDokumen.add(h).toString();
		this.context.put("idDokumen",idDokumen);
		this.context.put("id_tagdokumen", FrmKemaskiniLainDokumenData.idTag);
		
	  }
	
	private void update(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_Dokumen", getParam("idDokumen"));
		h.put("no_Rujukan_Dokumen",getParam("txtNoRujDokumen"));
		h.put("tajuk_Dokumen",getParam("txtTajukDokumen"));
		h.put("id_Jenisdokumen",getParam("socJenisDokumen"));
		h.put("tarikh_Dokumen",getParam("txdTarikhDokumen"));
		h.put("id_Seksyen",getParam("socSeksyen"));
		h.put("no_Fail",getParam("txtNamaFail"));
		h.put("catatan",getParam("txtCatatan"));
		h.put("tagDokumen", getParam("tag_dokumen"));
		h.put("idTagDokumen", getParam("id_tagdokumen"));
		
		kemaskiniDokumen.update(h);

	}
	
	private void view(HttpSession session) throws Exception {
		//int id = Integer.parseInt(getParam("idDokumen"));
		//long id = Long.parseLong(getParam("idDokumen"));
		String id = getParam("idDokumen");
		kemaskiniDokumen.setData(id);
	
	}
	
	private void listLampiran(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		kemaskiniDokumen.setListLampiran(id);
	
	}
	  
	private void hapusLampiran(HttpSession session,String id_Lampiran) throws Exception {
		//String id = getParam("id_Lampiran");
		System.out.println("id_Lampiran test -- "+id_Lampiran);
		kemaskiniDokumen.hapus(id_Lampiran);

	}
	
	public void debugParams() {
		Enumeration allparam = request.getParameterNames();
		String name="";String value="";
		for (; allparam.hasMoreElements(); ) {
	        // Get the name of the request parameter
	        name = (String)allparam.nextElement();
	        // Get the value of the request parameter
	        value = request.getParameter(name);
	        //System.out.println(name +"="+value);
		    myLog.debug(name +"="+value);

		}
	}



}
