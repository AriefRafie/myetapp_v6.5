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
import ekptg.model.pdt.FrmKemaskiniLainDokumenData;
import ekptg.model.pdt.IPDT;
import ekptg.model.pdt.IPDTUtil;
import ekptg.model.pdt.PDTPerundanganBean;
import ekptg.model.pdt.PDTUtilBean;

public class MaklumatPerundangan extends AjaxBasedModule{
	private final String PATH="app/pdt/perundangan/";
	private static Logger myLog = Logger.getLogger(ekptg.view.pdt.MaklumatPerundangan.class);
 	private IPDTUtil iPDTUtil = null;  
 	private IPDT iPDT = null; 
 	
 	FrmKemaskiniLainDokumenData kemaskiniLainDokumen = new FrmKemaskiniLainDokumenData();

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
		String idJnsDok = getParam("socJenisDokumen");
		String catatan = "";
		String tkhDaftar = "";
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		HttpSession session = this.request.getSession();		
			
		String action = getParam("action");
		Vector list = new Vector();
		this.context.put("action",action); 		
		this.context.put("SenaraiLampiran","");
		myLog.info("sumbit="+submit+",action="+action);
		
		//if (action.equals("tambahDataBaru")){	
		if (submit.equals("tambahDataBaru")){	
			myLog.info("tambahDataBaru="+action);
			vm = PATH+"frmKemaskiniPerundangan.jsp";
			this.context.put("idDokumen","0");
			noRujDok = getParam("txtNoRujDokumen");
			this.context.put("noRujDok", noRujDok);
			tjkDok = getParam("txtTajukDokumen");
			this.context.put("tjkDok",tjkDok);
			tkhDok = getParam("txdTarikhDokumen");
			this.context.put("tkhDok", tkhDok);
			catatan = getParam("txtCatatan");
			this.context.put("catatan",catatan);
			this.context.put("tkhDaftar",sdf.format(now));
			
			//this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(idJnsDok),""));			
			String selectAkta = getPDTUtil().getAktaPilihan("", "socSeksyen", sksyn, "", "");
			this.context.put("selectSeksyen",selectAkta);
      	  
    	    if ("doChangeSeksyen".equals(submit)) {
    	    	this.context.put("selectFail",HTML.SelectFailByIdSeksyen(seksyen,"socFail",null,null));
    		}else {
    		   this.context.put("selectFail",HTML.SelectFail("socFail"));
    		}
    	
	    	this.context.put("readOnly","");
	    	this.context.put("mode","New");
			
		}
		else if (submit.equals("tambahDataLain")){
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
    	
	    	this.context.put("readOnly","");
	    	this.context.put("mode","New");
			
		}else if (submit.equals("simpan")){
			vm = PATH+"frmKemaskiniPerundangan.jsp";
			simpan(session);
			
			sksyn = seksyen;
			noRujDok = getParam("txtNoRujDokumen");
			this.context.put("noRujDok", noRujDok);
			tjkDok = getParam("txtTajukDokumen_");
			this.context.put("tjkDok",tjkDok);
			tkhDok = getParam("txdTarikhDokumen");
			this.context.put("tkhDok", tkhDok);
			catatan = getParam("txtCatatan");
			this.context.put("catatan",catatan);
			this.context.put("tkhDaftar",sdf.format(now));
			//this.context.put("selectJenisDokumen",HTML.SelectJenisDokumen("socJenisDokumen",Utils.parseLong(idJnsDok),"disabled"));
			String selectAkta = getPDTUtil().getAktaPilihan("", "socSeksyen", sksyn, "disabled", "");
			this.context.put("selectSeksyen",selectAkta);
			this.context.put("readOnly","readonly");
        	this.context.put("mode","View");
			this.context.put("tag_Dokumen", getParam("tag_dokumen"));
			
        	listLampiran(session);
        	
        	list = kemaskiniLainDokumen.getListLampiran();
	    	this.context.put("SenaraiLampiran", list);
			
		}else if (submit.equals("papar")){
			vm = PATH+"frmKemaskiniPerundangan.jsp";
			//view(session);
			//list = FrmKemaskiniLainDokumenData.getData();
			this.context.put("mode","View");
			
			//Hashtable h = (Hashtable) list.get(0);
			Hashtable h = view();
			this.context.put("idDokumen",h.get("idPerundangan"));
			this.context.put("tjkDok",h.get("maklumat"));
			this.context.put("noRujDok", h.get("tempatBicara"));
			this.context.put("tarikhBicara", h.get("tarikhBicara"));
			this.context.put("tkhDok", h.get("tarikhKeputusan"));
			this.context.put("catatan",h.get("catatan"));
			this.context.put("tkhDaftar",h.get("tarikhDaftar"));
			//this.context.put("txtNamaFail",h.get("noFail"));
			String selectAkta = getPDTUtil().getAktaPilihan("", "socSeksyen", String.valueOf(h.get("idAkta")), "disabled", null);
			this.context.put("selectSeksyen",selectAkta);
			this.context.put("readOnly","readonly");
			this.context.put("tag_Dokumen", h.get("tagging"));
			this.context.put("id_tagdokumen", h.get("idTagging"));
			
			//listLampiran(session);
			//list = FrmKemaskiniLainDokumenData.getListLampiran();
			list = listLampiran();
	    	this.context.put("SenaraiLampiran", list);
			
		}else if (submit.equals("kemaskini")){
			vm = PATH+"frmKemaskiniPerundangan.jsp";
			this.context.put("mode","Update");
			Hashtable h = view();
			this.context.put("idDokumen",h.get("idPerundangan"));
			this.context.put("tjkDok",h.get("maklumat"));
			this.context.put("noRujDok", h.get("tempatBicara"));
			this.context.put("tarikhBicara", h.get("tarikhBicara"));
			this.context.put("tkhDok", h.get("tarikhKeputusan"));
			this.context.put("catatan",h.get("catatan"));
			this.context.put("tkhDaftar",h.get("tarikhDaftar"));
			//this.context.put("txtNamaFail",h.get("noFail"));
			String selectAkta = getPDTUtil().getAktaPilihan("", "socSeksyen", String.valueOf(h.get("idAkta")), "", null);
			this.context.put("selectSeksyen",selectAkta);
			this.context.put("readOnly","");
			this.context.put("tag_Dokumen", h.get("tagging"));
			this.context.put("id_tagdokumen", h.get("idTagging"));
			
			//listLampiran(session);
			//list = FrmKemaskiniLainDokumenData.getListLampiran();
			list = listLampiran();
	    	this.context.put("SenaraiLampiran", list);
		}
		else if (submit.equals("editData")){
			vm = PATH+"frmKemaskiniPerundangan.jsp";
			myLog.info("editData=");
			myLog.info(getParam("txtTajukDokumen_"));
			myLog.info(getParam("txtTajukDokumen"));
			update(session);
			Hashtable h = view();
		
			idDokumen = getParam("idDokumen");
			this.context.put("idDokumen",idDokumen);
			noRujDok = getParam("txtNoRujDokumen");
			this.context.put("noRujDok", noRujDok);
			//tjkDok = getParam("txtTajukDokumen_");
			//this.context.put("tjkDok",tjkDok);
			this.context.put("tjkDok",h.get("maklumat"));
			this.context.put("tarikhBicara", getParam("tarikhBicara"));
			tkhDok = getParam("txdTarikhDokumen");
			this.context.put("tkhDok", tkhDok);
			catatan = getParam("txtCatatan");
			this.context.put("catatan",catatan);
			//this.context.put("txtNamaFail",getParam("txtNamaFail"));
			
			this.context.put("tkhDaftar",sdf.format(now));
			String selectAkta = getPDTUtil().getAktaPilihan("", "socSeksyen", String.valueOf(getParam("socSeksyen")), "disabled", null);
			this.context.put("selectSeksyen",selectAkta);
			this.context.put("readOnly","readonly");
        	this.context.put("mode","View");
			this.context.put("tag_Dokumen", getParam("tag_dokumen"));
			this.context.put("id_tagdokumen", getParam("id_tagdokumen"));
    	
			list = listLampiran();
	    	this.context.put("SenaraiLampiran", list);
						
		}else if(submit.equals("hapus")){			
			vm = PATH+"frmKemaskiniPerundangan.jsp";
			hapusLampiran();
			
			Hashtable h = view();
			
			this.context.put("idDokumen",h.get("idPerundangan"));
			this.context.put("tjkDok",h.get("maklumat"));
			this.context.put("noRujDok", h.get("tempatBicara"));
			this.context.put("tarikhBicara", h.get("tarikhBicara"));
			this.context.put("tkhDok", h.get("tarikhKeputusan"));
			this.context.put("catatan",h.get("catatan"));
			this.context.put("tkhDaftar",h.get("tarikhDaftar"));
			//this.context.put("txtNamaFail",h.get("noFail"));
			String selectAkta = getPDTUtil().getAktaPilihan("", "socSeksyen", String.valueOf(h.get("idAkta")), "", null);
			this.context.put("selectSeksyen",selectAkta);
			this.context.put("readOnly","");
			this.context.put("tag_Dokumen", h.get("tagging"));
			this.context.put("id_tagdokumen", h.get("idTagging"));
			this.context.put("mode","View");
			this.context.put("readOnly","readonly");
			list = listLampiran();

	    	this.context.put("SenaraiLampiran", list);
						
		}else if(submit.equals("SenaraiDokumen")){
			vm = PATH+"index.jsp";
			String tag = getParam("tag_dokumen");
			//FrmDaftarLainDokumenData.setCarianDokumen(noRujukanDokumen,tajukDokumen, tarikhDokumen, sksyn);
			//list = FrmDaftarLainDokumenData.getList();
			list = getPDTPerundangan().getCarian(sksyn, tajukDokumen, noRujukanDokumen, tarikhDokumen, tag);
			setupPage(session,action,list);
			String selectAkta = getPDTUtil().getAktaPilihan("", "socSeksyen", sksyn, "", "");
			this.context.put("selectSeksyen",selectAkta);
			this.context.put("noRujukanDokumen",noRujukanDokumen);
			this.context.put("tajukDokumen",tajukDokumen);
	    	this.context.put("tarikhDokumen", tarikhDokumen);
	    	
		}else if(submit.equals("Delete")){
			vm = PATH+"index.jsp";
			String tag = getParam("tag_dokumen");
			getPDTPerundangan().hapus(idDokumen);
			list = getPDTPerundangan().getCarian(sksyn, tajukDokumen, noRujukanDokumen, tarikhDokumen, tag);
			setupPage(session,action,list);
			String selectAkta = getPDTUtil().getAktaPilihan("", "socSeksyen", sksyn, "", "");
			this.context.put("selectSeksyen",selectAkta);
			this.context.put("noRujukanDokumen",noRujukanDokumen);
			this.context.put("tajukDokumen",tajukDokumen);
	    	this.context.put("tarikhDokumen", tarikhDokumen);
		
		}else{		
			vm = PATH+"index.jsp";
			String tag = getParam("tag_dokumen");
			if (!"".equals(getParam("socSeksyen")))
				sksyn = getParam("socSeksyen");
			if (!"".equals(getParam("txtNoRujDokuman")))
				noRujukanDokumen = getParam("txtNoRujDokuman");
			if (!"".equals(getParam("txtTajukDokumen")))
				tajukDokumen = getParam("txtTajukDokumen");
			if (!"".equals(getParam("txdTarikhDokumen")))
				tarikhDokumen = getParam("txdTarikhDokumen");

			//list = FrmDaftarLainDokumenData.getCarianDokumen(noRujukanDokumen,tajukDokumen, tarikhDokumen, sksyn,tag);
			list = getPDTPerundangan().getCarian(sksyn, tajukDokumen, noRujukanDokumen, tarikhDokumen, tag);
			setupPage(session,action,list);
			//this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(sksyn),""));
			String selectAkta = getPDTUtil().getAktaPilihan("", "socSeksyen", sksyn, "", "");
			this.context.put("selectSeksyen",selectAkta);
			this.context.put("noRujukanDokumen",noRujukanDokumen);
			this.context.put("tajukDokumen",tajukDokumen);
	    	this.context.put("tarikhDokumen", tarikhDokumen);
	    	this.context.put("tag_Dokumen",tag);
			
		}
		return vm;
		
    }
	
	private void simpan(HttpSession session) throws Exception {
		String idDokumen = new String("0");
		Hashtable h = new Hashtable();
//		myLog.info(getParam("socSeksyen"));
//		myLog.info(getParam("txtTajukDokumen_"));
//		myLog.info(getParam("txtNoRujDokumen"));
//		myLog.info(getParam("txdTarikhBicara"));
//		myLog.info(getParam("txtCatatan"));
//		myLog.info(getParam("txdTarikhDokumen"));
//		myLog.info(getParam("tag_dokumen"));
//		myLog.info(getParam("id_tagdokumen"));
		h.put("id_Seksyen",getParam("socSeksyen"));
		h.put("tajuk_Dokumen",getParam("txtTajukDokumen"));
		h.put("no_Rujukan_Dokumen",getParam("txtNoRujDokumen"));
		h.put("tarikhBicara",getParam("txdTarikhBicara"));
		h.put("catatan",getParam("txtCatatan"));		
		h.put("tarikh_Dokumen",getParam("txdTarikhDokumen"));
		h.put("idMasuk",(String)session.getAttribute("_ekptg_user_id"));
		//h.put("id_Jenisdokumen",getParam("socJenisDokumen"));
		//h.put("id_Fail",getParam("socFail"));
		h.put("tagDokumen", getParam("tag_dokumen"));
		h.put("idTagDokumen", getParam("id_tagdokumen"));		
		//idDokumen = FrmKemaskiniLainDokumenData.add(h).toString();
		
		idDokumen = getPDTPerundangan().tambah(h);
		this.context.put("idDokumen",idDokumen);
		this.context.put("id_tagdokumen", getPDTPerundangan().getIdTag());
		
	  }
	
	private void update(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_Dokumen", Integer.parseInt(getParam("idDokumen")));
		h.put("tajuk_Dokumen",getParam("txtTajukDokumen"));
		h.put("no_Rujukan_Dokumen",getParam("txtNoRujDokumen"));
		h.put("tarikh_Dokumen",getParam("txdTarikhDokumen"));
		h.put("tarikhBicara",getParam("txdTarikhBicara"));	
		h.put("id_Seksyen",getParam("socSeksyen"));
		h.put("catatan",getParam("txtCatatan"));
		h.put("idMasuk",(String)session.getAttribute("_ekptg_user_id"));
		h.put("tagDokumen", getParam("tag_dokumen"));
		h.put("idTagDokumen", getParam("id_tagdokumen"));
		//h.put("id_Jenisdokumen",getParam("socJenisDokumen"));
		//h.put("no_Fail",getParam("txtNamaFail"));
		
		//FrmKemaskiniLainDokumenData.update(h);
		getPDTPerundangan().kemaskini(h);

	}
	
	private void view(HttpSession session) throws Exception {
		//int id = Integer.parseInt(getParam("idDokumen"));
		String id = getParam("idDokumen");
		kemaskiniLainDokumen.setData(id);
		
	}
	
	private Hashtable view() throws Exception {
		return getPDTPerundangan().getMaklumat(getParam("idDokumen"));
	}	
	
	private void listLampiran(HttpSession session) throws Exception {
		String id = getParam("idDokumen");
		kemaskiniLainDokumen.setListLampiran(id);

	}
	
	private Vector listLampiran() throws Exception {
		//FrmKemaskiniLainDokumenData.setListLampiran(id);
		return getPDTPerundangan().getLampirans(getParam("idDokumen"));
	}
	
	private void hapusLampiran(HttpSession session) throws Exception {
		//int id = Integer.parseInt(getParam("idLampiran"));
		String id = getParam("idLampiran");
		kemaskiniLainDokumen.hapus(id);

	}
	
	private void hapusLampiran() throws Exception {
		getPDTPerundangan().hapusLampiran(getParam("idLampiran"));

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
	
	private IPDTUtil getPDTUtil(){
		if(iPDTUtil== null)
			iPDTUtil = new PDTUtilBean();
		return iPDTUtil;
	}
	
	private IPDT getPDTPerundangan(){
		if(iPDT== null)
			iPDT = new PDTPerundanganBean();
		return iPDT;
	}

	
}
