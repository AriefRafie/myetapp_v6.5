package ekptg.view.pdt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pdt.FrmDaftarPekelilingData;
import ekptg.model.pdt.FrmKemaskiniPekelilingData;

public class PendaftaranPekeliling extends AjaxBasedModule {
	private static Logger myLog = Logger.getLogger(ekptg.view.pdt.PendaftaranPekeliling.class);
	
	FrmDaftarPekelilingData pekeliling = new FrmDaftarPekelilingData();
	
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String vm = "";
		String submit = getParam("command");
		String kategoriPekeliling = "0";
		String perkaraPekeliling = "0";
		String kementerian = "0";
		String tajukPekeliling = "";
		String tahun = "";
		String tarikhKuatkuasa = "";
		String idPekeliling = getParam("idPekeliling");
		//String idPekeliling ="";
		String bilPekeliling = "";
		String tjkPekeliling = "";
		String tarikhPekeliling = "";
		String tkhKuatkuasa = "";
		String statusPekeliling = getParam("socStatus");
		String noFail = "";
		if ("".equals(statusPekeliling) || "0".equals(statusPekeliling)){
			 statusPekeliling = "7";
			
        }
		String idSeksyen = getParam("socSeksyen");
		String idFail = getParam("socFail");
		String catatan = "";
		String idKategoriPekeliling = getParam("socKategoriPekeliling");
		String idPerkaraPekeliling = getParam("socPerkaraPekeliling");
		String idKementerian = getParam("socKementerian");
		String tarikhDaftar = "";
		
		String current_role = (String)session.getAttribute("myrole");
		
		Vector list = new Vector();
		String action = getParam("action");		
		String mode = getParam("mode");
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//this.context.put("action",action); 
		String user_role = "";
		String userSeksyen = findUserSeksyen(session);
		this.context.put("Util",new Utils());		
		myLog.debug("Action = " +action);
		
		if (action.equals("tambahDataBaru")){			
			vm = "app/pdt/frmKemaskiniPekeliling.jsp";			
			user_role = (String)session.getAttribute("_portal_role");		
			this.context.put("selectKategoriPekeliling",HTML.SelectKategoriPekeliling(user_role,"socKategoriPekeliling",Utils.parseLong(idKategoriPekeliling),"",null));		
			this.context.put("selectPerkaraPekeliling",HTML.SelectPerkaraPekeliling("socPerkaraPekeliling",Utils.parseLong(idPerkaraPekeliling),"",null));
	    	this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(idKementerian),"",null));
	    	idPekeliling = getParam("idPekeliling");
	    	this.context.put("idPekeliling",idPekeliling);
	    	bilPekeliling = getParam("txtBilPekeliling");
	    	this.context.put("bilPekeliling", bilPekeliling);
	    	tjkPekeliling = getParam("txtTajukPekeliling");
	    	this.context.put("tjkPekeliling",tjkPekeliling);
	    	tahun = getParam("txtTahun");
	    	this.context.put("tahun",tahun);
	    	tarikhPekeliling = getParam("txdTarikhPekeliling");
	    	this.context.put("tarikhPekeliling",tarikhPekeliling);
	    	tkhKuatkuasa = getParam("txdTarikhKuatkuasa");
	    	this.context.put("tkhKuatkuasa",tkhKuatkuasa);
	    	this.context.put("selectSeksyen",HTML.SelectSeksyenByUser(userSeksyen,"socSeksyen",Utils.parseLong(userSeksyen),null,"disabled"));
	    	//this.context.put("selectFail",HTML.SelectFailByIdSeksyen(idSeksyen,"socFail",null,null));
        	this.context.put("noFail","");
	    	this.context.put("selectStatus",HTML.SelectStatusFail("socStatus",Utils.parseLong(statusPekeliling),"disabled"));
        	
        	catatan = getParam("txtCatatan");
        	this.context.put("catatan",catatan);
        	this.context.put("idStatuspekeliling", statusPekeliling);  
        	this.context.put("idSeksyen", userSeksyen);
	    	this.context.put("tarikhDaftar",sdf.format(now));
	    	this.context.put("readOnly","");
	    	this.context.put("mode","New");
	    	
		}else if (action.equals("tambahDataLain")){
			vm = "app/pdt/frmKemaskiniPekeliling.jsp";
			
			this.context.put("selectKategoriPekeliling",HTML.SelectKategoriPekeliling(user_role,"socKategoriPekeliling",Utils.parseLong(idKategoriPekeliling),"",null));
	    	this.context.put("selectPerkaraPekeliling",HTML.SelectPerkaraPekeliling("socPerkaraPekeliling",Utils.parseLong(idPerkaraPekeliling),"",null));
	    	idPekeliling = getParam("idPekeliling");
	    	this.context.put("idPekeliling",idPekeliling);
	    	bilPekeliling = getParam("txtBilPekeliling");
	    	this.context.put("bilPekeliling", bilPekeliling);
	    	tjkPekeliling = getParam("txtTajukPekeliling");
	    	this.context.put("tjkPekeliling",tjkPekeliling);
	    	tahun = getParam("txtTahun");
	    	this.context.put("tahun",tahun);
	    	tarikhPekeliling = getParam("txdTarikhPekeliling");
	    	this.context.put("tarikhPekeliling",tarikhPekeliling);
	    	tkhKuatkuasa = getParam("txdTarikhKuatkuasa");
	    	this.context.put("tkhKuatkuasa",tkhKuatkuasa);
	    	this.context.put("selectSeksyen",HTML.SelectSeksyenByUser(userSeksyen,"socSeksyen",Utils.parseLong(userSeksyen),null,"disabled"));
	    	//this.context.put("selectFail",HTML.SelectFailByIdSeksyen(idSeksyen,"socFail",null,null));
	    	this.context.put("selectStatus",HTML.SelectStatusFail("socStatus",Utils.parseLong(statusPekeliling),"disabled"));
	    	this.context.put("noFail","");
        	catatan = getParam("txtCatatan");
        	this.context.put("catatan",catatan);
        	this.context.put("idStatuspekeliling", statusPekeliling);
        	this.context.put("idSeksyen", userSeksyen);
			this.context.put("tarikhDaftar",sdf.format(now));
	    	this.context.put("readOnly","");
	    	this.context.put("mode","New");
		
		}else if (action.equals("simpan")){
			vm = "app/pdt/frmKemaskiniPekeliling.jsp";
	    	String tjkPekeliling_ = getParam("txtTajukPekeliling_");
	    	myLog.info("tjkPekeliling_="+tjkPekeliling_);
			simpan(session);
			this.context.put("selectKategoriPekeliling",HTML.SelectKategoriPekeliling(user_role,"socKategoriPekeliling",Utils.parseLong(idKategoriPekeliling),"disabled",null));
	    	this.context.put("selectPerkaraPekeliling",HTML.SelectPerkaraPekeliling("socPerkaraPekeliling",Utils.parseLong(idPerkaraPekeliling),"disabled",null));
	    	this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(idKementerian),"disbaled",null));
	    	//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(idFail),"disabled"));
	    	this.context.put("selectStatus",HTML.SelectStatusFail("socStatus",Utils.parseLong(statusPekeliling),"disabled"));
	    	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(userSeksyen),"disabled"));
	    	bilPekeliling = getParam("txtBilPekeliling");
	    	this.context.put("bilPekeliling", bilPekeliling);
	    	tjkPekeliling = getParam("txtTajukPekeliling_");
	    	this.context.put("tjkPekeliling",tjkPekeliling_);
	    	tahun = getParam("txtTahun");
	    	this.context.put("tahun",tahun);
	    	tarikhPekeliling = getParam("txdTarikhPekeliling");
	    	this.context.put("tarikhPekeliling",tarikhPekeliling);
	    	tkhKuatkuasa = getParam("txdTarikhKuatkuasa");
	    	this.context.put("tkhKuatkuasa",tkhKuatkuasa);
	    	catatan = getParam("txtCatatan");
        	this.context.put("catatan",catatan);
        	this.context.put("tarikhDaftar",sdf.format(now));
        	this.context.put("readOnly","readonly");
        	this.context.put("mode","afterSimpan");
			this.context.put("tag_Dokumen", getParam("tag_dokumen"));
			
			noFail = getParam("txtNoFail");
        	this.context.put("noFail",noFail);
			
		}else if (action.equals("papar")){		
			vm = "app/pdt/frmKemaskiniPekeliling.jsp";
			view(session);
			list = FrmKemaskiniPekelilingData.getData();
			this.context.put("mode","View");
			
			Hashtable h = (Hashtable) list.get(0);
			this.context.put("selectKategoriPekeliling",HTML.SelectKategoriPekeliling("socKategoriPekeliling",Utils.parseLong(h.get("idDokumenPekeliling").toString()),"disabled",null));
	    	this.context.put("selectPerkaraPekeliling",HTML.SelectPerkaraPekeliling("socPerkaraPekeliling",Utils.parseLong(h.get("idPerkaraPekeliling").toString()),"disabled",null));
	    	//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(h.get("idFail").toString()),"disabled"));
	    	this.context.put("selectStatus",HTML.SelectStatusFail("socStatus",Utils.parseLong(h.get("statusPekeliling").toString()),"disabled"));
	    	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(h.get("seksyen").toString()),"disabled"));

	    	this.context.put("idPekeliling",h.get("idPekeliling"));
	    	this.context.put("bilPekeliling",h.get("bilPekeliling"));
	    	this.context.put("tjkPekeliling",h.get("tjkPekeliling"));
	    	this.context.put("noFail",h.get("no_fail"));
	    	this.context.put("tahun",h.get("tahun"));
	    	this.context.put("tarikhPekeliling",h.get("tarikhPekeliling"));
	    	this.context.put("tkhKuatkuasa",h.get("tkhKuatkuasa"));
	    	this.context.put("catatan",h.get("catatan"));
	    	this.context.put("tarikhDaftar",h.get("tarikhDaftar"));
	    	this.context.put("readOnly","readonly");
			this.context.put("tag_Dokumen", h.get("tagging"));
			this.context.put("id_tagdokumen", h.get("idTagging"));
	    	myLog.debug("Id Pekeliling = " +getParam("idPekeliling"));
		
		}else if (action.equals("kemaskini")){
			vm = "app/pdt/frmKemaskiniPekeliling.jsp";
			view(session);
			list = FrmKemaskiniPekelilingData.getData();
			this.context.put("Pekeliling",list);
			this.context.put("mode","Update");
			
			Hashtable h = (Hashtable) list.get(0);
			this.context.put("selectKategoriPekeliling",HTML.SelectKategoriPekeliling("socKategoriPekeliling",Utils.parseLong(h.get("idDokumenPekeliling").toString()),"",null));
	    	this.context.put("selectPerkaraPekeliling",HTML.SelectPerkaraPekeliling("socPerkaraPekeliling",Utils.parseLong(h.get("idPerkaraPekeliling").toString()),"",null));
	    	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(h.get("seksyen").toString()),"disabled"));
	    	//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(h.get("idFail").toString()),""));
	    	this.context.put("selectStatus",HTML.SelectStatusFail("socStatus",Utils.parseLong(h.get("statusPekeliling").toString()),"disabled"));
	    	this.context.put("idPekeliling",h.get("idPekeliling"));
	    	this.context.put("bilPekeliling",h.get("bilPekeliling"));
	    	this.context.put("tjkPekeliling",h.get("tjkPekeliling"));
	    	this.context.put("tahun",h.get("tahun"));
	    	this.context.put("tarikhPekeliling",h.get("tarikhPekeliling"));
	    	this.context.put("tkhKuatkuasa",h.get("tkhKuatkuasa"));
	    	this.context.put("catatan",h.get("catatan"));
	    	this.context.put("tarikhDaftar",h.get("tarikhDaftar"));
	    	this.context.put("readOnly","");
			this.context.put("tag_Dokumen", h.get("tagging"));
			this.context.put("id_tagdokumen", h.get("idTagging"));
	    	myLog.debug("Id Pekeliling = " +getParam("idPekeliling"));
		
		}else if (action.equals("editData")){
			vm = "app/pdt/frmKemaskiniPekeliling.jsp";
			myLog.debug("Id Pekeliling = " +getParam("idPekeliling"));
			update(session);
			
			this.context.put("selectKategoriPekeliling",HTML.SelectKategoriPekeliling("socKategoriPekeliling",Utils.parseLong(idKategoriPekeliling),"disabled",null));
	    	this.context.put("selectPerkaraPekeliling",HTML.SelectPerkaraPekeliling("socPerkaraPekeliling",Utils.parseLong(idPerkaraPekeliling),"disabled",null));
	    	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(idSeksyen),"disabled"));	    	
	    	//this.context.put("selectFail",HTML.SelectFail("socFail",Utils.parseLong(idFail),"disabled"));
	    	this.context.put("selectStatus",HTML.SelectStatusFail("socStatus",Utils.parseLong(statusPekeliling),"disabled"));
	    	bilPekeliling = getParam("txtBilPekeliling");
	    	this.context.put("bilPekeliling", bilPekeliling);
	    	tjkPekeliling = getParam("txtTajukPekeliling_");
	    	this.context.put("tjkPekeliling",tjkPekeliling);
	    	tahun = getParam("txtTahun");
	    	this.context.put("tahun",tahun);
	    	tarikhPekeliling = getParam("txdTarikhPekeliling");
	    	this.context.put("tarikhPekeliling",tarikhPekeliling);
	    	tkhKuatkuasa = getParam("txdTarikhKuatkuasa");
	    	this.context.put("tkhKuatkuasa",tkhKuatkuasa);
	    	catatan = getParam("txtCatatan");
        	this.context.put("catatan",catatan);
        	tarikhDaftar = getParam("tarikhDaftar_");
        	this.context.put("tarikhDaftar",tarikhDaftar);
        	this.context.put("readOnly","readonly");
        	this.context.put("mode","View");
			this.context.put("tag_Dokumen", getParam("tag_dokumen"));
			this.context.put("id_tagdokumen", getParam("id_tagdokumen"));
    	
		}else if (action.equals("SenaraiPekeliling")){
			vm = "app/pdt/frmDaftarPekeliling.jsp";
			//FrmDaftarPekelilingData.setCarianPekeliling(kategoriPekeliling,perkaraPekeliling,tajukPekeliling,tahun,tarikhKuatkuasa);
			//list = FrmDaftarPekelilingData.getList();
			String tag = getParam("tag_dokumen");
			list = pekeliling.getCarianPekeliling(kategoriPekeliling,perkaraPekeliling
					,tajukPekeliling,tahun,tarikhKuatkuasa,tag, current_role);

			this.context.put("SenaraiPekeliling",list);
			this.context.put("selectKategoriPekeliling",HTML.SelectKategoriPekeliling(user_role,"socKategoriPekeliling",Long.parseLong(kategoriPekeliling),"",null));
	    	this.context.put("selectPerkaraPekeliling",HTML.SelectPerkaraPekeliling("socPerkaraPekeliling",Long.parseLong(perkaraPekeliling),"",null));
	    	this.context.put("tajukPekeliling",tajukPekeliling);
	    	this.context.put("tahun", tahun);
	    	this.context.put("tarikhKuatkuasa", tarikhKuatkuasa);	
	    	this.context.put("tag_Dokumen",tag);
			setupPage(session,action,list);
		
		} else if (action.equals("Delete")) {
			pekeliling.Delete(idPekeliling);
			vm = "app/pdt/frmDaftarPekeliling.jsp";
			
			pekeliling.setCarianPekeliling(kategoriPekeliling,perkaraPekeliling,tajukPekeliling,tahun,tarikhKuatkuasa);
			list = pekeliling.getList();	
			this.context.put("SenaraiPekeliling",list);
			this.context.put("selectKategoriPekeliling",HTML.SelectKategoriPekeliling(user_role,"socKategoriPekeliling",Long.parseLong(kategoriPekeliling),"",null));
	    	this.context.put("selectPerkaraPekeliling",HTML.SelectPerkaraPekeliling("socPerkaraPekeliling",Long.parseLong(perkaraPekeliling),"",null));
	    	this.context.put("tajukPekeliling",tajukPekeliling);
	    	this.context.put("tahun", tahun);
	    	this.context.put("tarikhKuatkuasa", tarikhKuatkuasa);
			
			setupPage(session,action,list);
		
		}else{	
			vm = "app/pdt/frmDaftarPekeliling.jsp";
			String tag = getParam("tag_dokumen");
			if (!"".equals(getParam("socKategoriPekeliling")))
				kategoriPekeliling = getParam("socKategoriPekeliling");
			if (!"".equals(getParam("socPerkaraPekeliling")))
				perkaraPekeliling = getParam("socPerkaraPekeliling");
			if (!"".equals(getParam("socKementerian")))
				kementerian = getParam("socKementerian");
			if (!"".equals(getParam("txtTajukPekeliling")))
				tajukPekeliling = getParam("txtTajukPekeliling");
			if (!"".equals(getParam("txtTahun")))
				tahun = getParam("txtTahun");
			if (!"".equals(getParam("txtTarikhKuatkuasa")))
				tarikhKuatkuasa = getParam("txtTarikhKuatkuasa");
			
			//FrmDaftarPekelilingData.setCarianPekeliling(kategoriPekeliling,perkaraPekeliling,tajukPekeliling,tahun,tarikhKuatkuasa);
			//list = FrmDaftarPekelilingData.getList();
				//this.context.put("SenaraiPekeliling",list);
			list = pekeliling.getCarianPekeliling(kategoriPekeliling,perkaraPekeliling
					,tajukPekeliling,tahun,tarikhKuatkuasa,tag, current_role);

			this.context.put("selectKategoriPekeliling",HTML.SelectKategoriPekeliling(user_role,"socKategoriPekeliling",Long.parseLong(kategoriPekeliling),"",null));
	    	this.context.put("selectPerkaraPekeliling",HTML.SelectPerkaraPekeliling("socPerkaraPekeliling",Long.parseLong(perkaraPekeliling),"",null));
	    	this.context.put("tajukPekeliling",tajukPekeliling);
	    	this.context.put("tahun", tahun);
	    	this.context.put("tarikhKuatkuasa", tarikhKuatkuasa);
	    	this.context.put("tag_Dokumen",tag);
    	
			setupPage(session,action,list);

		}
		//myLogger.debug(vm);
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
		this.context.put("SenaraiPekeliling",paging.getPage(page));
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
	
	private void simpan(HttpSession session) throws Exception {		
		String idPekeliling = "";
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		Hashtable h = new Hashtable();
 	    h.put("bil_Pekeliling", getParam("txtBilPekeliling"));
 	    h.put("id_Dokumenpekeliling",getParam("socKategoriPekeliling"));
 	    h.put("id_Perkarapekeliling", getParam("socPerkaraPekeliling"));
 	    h.put("tajuk_Pekeliling", getParam("txtTajukPekeliling_"));
 	    h.put("tahun",getParam("txtTahun"));
 	    h.put("tarikh_Pekeliling", getParam("txdTarikhPekeliling"));
 	    h.put("tarikh_Kuatkuasa", getParam("txdTarikhKuatkuasa"));
 	    h.put("id_Seksyen",getParam("idSeksyen"));
 	    h.put("id_Fail", getParam("socFail"));
 	    h.put("id_Status", getParam("idStatuspekeliling"));
 	    h.put("catatan", getParam("txtCatatan"));
 	    h.put("id_Masuk",user_id);
		h.put("tagDokumen", getParam("tag_dokumen"));
		h.put("noFail", getParam("txtNoFail"));
		
		myLog.info(" HHHHHHHHHHHHHHHHHHHHHHHHH :"+h);
		
 	    idPekeliling = FrmKemaskiniPekelilingData.add(h).toString();
 	    uploadFiles(idPekeliling);
 	    this.context.put("idPekeliling", idPekeliling);	    
		this.context.put("id_tagdokumen", FrmKemaskiniPekelilingData.idTag);
 	    
	}
	
	private void update(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		Hashtable h = new Hashtable();
		h.put("id_Pekeliling",getParam("idPekeliling"));
 	    h.put("bil_Pekeliling", getParam("txtBilPekeliling"));
 	    h.put("id_Dokumenpekeliling",getParam("socKategoriPekeliling"));
 	    h.put("id_Perkarapekeliling", getParam("socPerkaraPekeliling"));
 	    //h.put("tajuk_Pekeliling", getParam("txtTajukPekeliling"));
 	    h.put("tajuk_Pekeliling", getParam("txtTajukPekeliling_"));
 	    h.put("tahun",getParam("txtTahun"));
 	    h.put("tarikh_Pekeliling", getParam("txdTarikhPekeliling"));
 	    h.put("tarikh_Kuatkuasa", getParam("txdTarikhKuatkuasa"));
 	    h.put("id_Seksyen",getParam("idSeksyen"));
 	    h.put("id_Fail", getParam("socFail"));
 	    h.put("id_Status", getParam("idStatuspekeliling"));
 	    h.put("catatan", getParam("txtCatatan"));
 	    h.put("id_Kemaskini",user_id);
		h.put("tagDokumen", getParam("tag_dokumen"));
		h.put("idTagDokumen", getParam("id_tagdokumen"));

 	    FrmKemaskiniPekelilingData.update(h);
 	    uploadFiles(getParam("idPekeliling"));
 	    
	  }
	
	private void uploadFiles(String idPekeliling) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem)itr.next();
			    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	saveBlob(idPekeliling,item);
			    	//myLogger.debug(item);
			    }
		    }
		}
	}
	
	public void saveBlob(String idAkta,FileItem item) throws Exception {
		 Db db = null;
		 try {
			 db = new Db();
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = con.prepareStatement("UPDATE TBLPDTPEKELILING " +
			 		"SET Content=?,jenis_mime=? WHERE id_pekeliling=?");
			 ps.setBinaryStream(1,item.getInputStream(),(int)item.getSize());
			 ps.setString(2,item.getName());
			 ps.setString(3,idAkta);
			 ps.executeUpdate();
			 con.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			      if (db != null) db.close();
		 }
	}
	
	private void view(HttpSession session) throws Exception {
		//int id = Integer.parseInt(getParam("idPekeliling"));
		//long id = Long.parseLong(getParam("idPekeliling"));
		String id = getParam("idPekeliling");
		FrmKemaskiniPekelilingData.setData(id);
	
	}
	
	private String findUserSeksyen(HttpSession session) throws Exception {
		Db db = new Db();
		String userSeksyen = "";
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String sql = "SELECT A.ID_SEKSYEN FROM USERS_INTERNAL A WHERE A.USER_ID = " + user_id;
		
		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			userSeksyen = rs.getString("ID_SEKSYEN");
		}
		
		return userSeksyen;
		
	  }


}
