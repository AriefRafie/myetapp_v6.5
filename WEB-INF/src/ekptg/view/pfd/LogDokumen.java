package ekptg.view.pfd;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import ekptg.model.pfd.FrmDaftarLogDokumenData;
import ekptg.model.pfd.FrmLogDokumenData;

public class LogDokumen extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(ekptg.view.pfd.LogDokumen.class);
	
	public String doTemplate2() throws Exception {
		System.out.println("doTemplate2...");

		 String vm = "";
		 String idLogDokumen = getParam("idLogDokumen");
		 String idLampiran = getParam("idLampiran");
		 String no_Rujukan_Lain = getParam("no_Rujukan_Lain");
         String tajuk_Dokumen = getParam("tajuk_Dokumen");
         String tag_Dokumen = getParam("tag_dokumen");
		 String tarikh_Dokumen = getParam("tarikh_Dokumen");
         String tarikh_Dokumen_Diterima = getParam("tarikh_Dokumen_Diterima");
         String tarikh_Dokumen_Dihantar = getParam("tarikh_Dokumen_Dihantar");
         String pengirim_Dokumen = getParam("pengirim_Dokumen");
         String socPegawai = getParam("socPegawai");
         String socPT = getParam("socPT");
         String penerima_Dokumen = getParam("penerima_Dokumen");
         String socJenisPenghantaran = getParam("socJenisPenghantaran");
         String nama_Pegawai = getParam("nama_Pegawai");
         String catatan = getParam("catatan");
         String jenisDokumen = getParam("socJenisDokumen");
         String pagemode = getParam("pagemode");
         String status_logdokumen = "";
         String flag_logdokumen = "";
         String idFail = getParam("idFail");
         String idPergerakan = getParam("idPergerakanfail");
 		 context.put("idFail", idFail);
         String submit = getParam("command");
         String action1 = getParam("action1");
         String action = getParam("action");
			
		if ("doChangeItemPerPage".equals(action) 
				|| "getPage".equals(action)) {
			action1 = action;
		}
         String mode = getParam("mode");
         Vector senarai = new Vector();
         Vector listPegawai = new Vector();
         Vector paparPegawaiSeksyenLain = new Vector();
         Vector listPTFail = new Vector();
         Vector listLogDokumen = new Vector();
         Vector list = new Vector();
         Vector listLampiran = new Vector();
         Vector listLogDokumenMasuk = new Vector();
         Vector listDokumenMasuk = new Vector();
         Vector paparLampiranLogDokumenMasuk = new Vector();
         Vector paparLampiranLogDokumenKeluar = new Vector();
         Vector paparLampiranLogDokumenKeluarPapar = new Vector();
         Vector listLampiranLogDokumen = new Vector();
         Vector paparLogDokumen = null;
         Vector paparDokumen = null;
         Vector paparSeksyen = null;
         HttpSession session = this.request.getSession();
         Date now = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		 
		 listLogDokumen = FrmDaftarLogDokumenData.getListLogDokumen();
		 //(String) session.getAttribute("_ekptg_user_id")
		    //(String) session.getAttribute("_portal_role_")
		    //(String) session.getAttribute("_ekptg_user_negeri")
		 
			context.put("readonly","");
			context.put("disabled","");
			context.put("readonlyPT","");
			context.put("disabledPT","");
			context.put("jenis_Dokumen","");
			context.put("tarikh_Dokumen_Diterima","");
			context.put("tarikh_Dokumen","");
			context.put("no_Rujukan_Lain","");
			context.put("pengirim_Dokumen","");
			context.put("tajuk_Dokumen","");
		 
         myLogger.debug("action1:"+action1);
         
         if ("tabLogDokumenMasuk".equals(action1)){
        	vm = "app/pfd/frmLogDokumenMasuk.jsp";
        	
        	if("1".equalsIgnoreCase(pagemode)){            	
            	
    			if(jenisDokumen.equalsIgnoreCase("1") ){
    	         	context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("tarikh_Dokumen_Diterima",tarikh_Dokumen_Diterima);
    				context.put("tarikh_Dokumen",tarikh_Dokumen);
    				context.put("no_Rujukan_Lain",no_Rujukan_Lain);
    				context.put("pengirim_Dokumen",pengirim_Dokumen);
    				context.put("tajuk_Dokumen",tajuk_Dokumen);
    				context.put("modeJenisDokumen","1"); 
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("tag_Dokumen",tag_Dokumen);
    				context.put("nama_Pegawai",socPegawai);
    				context.put("nama_PTPA",socPT);
    				
    				
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	   
    	         	if ("minit" != null){
    					this.context.put("checkboxMinit", "1");
    				}
    				else{
    					this.context.put("checkboxMinit", "0");
    				}
    			
    				if ("laporan" != null){
    					this.context.put("checkboxLaporan", "1");
    				}
    				else{
    					this.context.put("checkboxLaporan", "0");
    				}
    				
    				if ("cd" != null){
    					this.context.put("checkboxCD", "1");
    				}
    				else{
    					this.context.put("checkboxCD", "0");
    				}
    				
    				
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    				
    			}
    			else if(jenisDokumen.equalsIgnoreCase("2")){
    				context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("tarikh_Dokumen_Diterima",tarikh_Dokumen_Diterima);
    				context.put("tarikh_Dokumen",tarikh_Dokumen);
    				context.put("no_Rujukan_Lain",no_Rujukan_Lain);
    				context.put("pengirim_Dokumen",pengirim_Dokumen);
    				context.put("tajuk_Dokumen",tajuk_Dokumen);
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("modeJenisDokumen","2"); 
    				context.put("tag_Dokumen",tag_Dokumen);
    				context.put("nama_Pegawai",socPegawai);
    				context.put("nama_PTPA",socPT);
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	         	
    	         	//this.context.put("tarikh_Dokumen", sdf.format(now));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	   
    	         	if ("minit" != null){
    					this.context.put("checkboxMinit", "1");
    				}
    				else{
    					this.context.put("checkboxMinit", "0");
    				}
    			
    				if ("laporan" != null){
    					this.context.put("checkboxLaporan", "1");
    				}
    				else{
    					this.context.put("checkboxLaporan", "0");
    				}
    				
    				if ("cd" != null){
    					this.context.put("checkboxCD", "1");
    				}
    				else{
    					this.context.put("checkboxCD", "0");
    				}
    				
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    			}
    			else if(jenisDokumen.equalsIgnoreCase("3")){
    				context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("tarikh_Dokumen_Diterima",tarikh_Dokumen_Diterima);
    				context.put("tarikh_Dokumen",tarikh_Dokumen);
    				context.put("no_Rujukan_Lain",no_Rujukan_Lain);
    				context.put("pengirim_Dokumen",pengirim_Dokumen);
    				context.put("tajuk_Dokumen",tajuk_Dokumen);
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("modeJenisDokumen","3");
    				context.put("tag_Dokumen",tag_Dokumen);
    				context.put("nama_Pegawai",socPegawai);
    				context.put("nama_PTPA",socPT);
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	   
    	         	if ("minit" != null){
    					this.context.put("checkboxMinit", "1");
    				}
    				else{
    					this.context.put("checkboxMinit", "0");
    				}
    			
    				if ("laporan" != null){
    					this.context.put("checkboxLaporan", "1");
    				}
    				else{
    					this.context.put("checkboxLaporan", "0");
    				}
    				
    				if ("cd" != null){
    					this.context.put("checkboxCD", "1");
    				}
    				else{
    					this.context.put("checkboxCD", "0");
    				}
    				
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    			}
    			
    			else if(jenisDokumen.equalsIgnoreCase("4")){
    				context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("tarikh_Dokumen_Diterima",tarikh_Dokumen_Diterima);
    				context.put("tarikh_Dokumen",tarikh_Dokumen);
    				context.put("no_Rujukan_Lain",no_Rujukan_Lain);
    				context.put("pengirim_Dokumen",pengirim_Dokumen);
    				context.put("tajuk_Dokumen",tajuk_Dokumen);
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("modeJenisDokumen","4");
    				context.put("tag_Dokumen",tag_Dokumen);
    				context.put("nama_Pegawai",socPegawai);
    				context.put("nama_PTPA",socPT);
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	   
    	         	if ("minit" != null){
    					this.context.put("checkboxMinit", "1");
    				}
    				else{
    					this.context.put("checkboxMinit", "0");
    				}
    			
    				if ("laporan" != null){
    					this.context.put("checkboxLaporan", "1");
    				}
    				else{
    					this.context.put("checkboxLaporan", "0");
    				}
    				
    				if ("cd" != null){
    					this.context.put("checkboxCD", "1");
    				}
    				else{
    					this.context.put("checkboxCD", "0");
    				}
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    			}
    			
    			else{
    	         	context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("jenis_Dokumen","");
    				context.put("tarikh_Dokumen_Diterima","");
    				context.put("tarikh_Dokumen","");
    				context.put("no_Rujukan_Lain","");
    				context.put("pengirim_Dokumen","");
    				context.put("tajuk_Dokumen","");
    				context.put("modeJenisDokumen","0");
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("tag_Dokumen","");
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	
    	         	this.context.put("checkedc1", "");
    	         	this.context.put("checkedc2", "");
    	         	this.context.put("checkedc3", "");
//    	         	if ("minit" != null){
//    					this.context.put("checkboxMinit", "1");
//    				}
//    				else{
//    					this.context.put("checkboxMinit", "0");
//    				}
//    			
//    				if ("laporan" != null){
//    					this.context.put("checkboxLaporan", "1");
//    				}
//    				else{
//    					this.context.put("checkboxLaporan", "0");
//    				}
//    				
//    				if ("cd" != null){
//    					this.context.put("checkboxCD", "1");
//    				}
//    				else{
//    					this.context.put("checkboxCD", "0");
//    				}
    				
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    			}	
        	}
        	else
        	{
        		
        	}
				
         }else if("tabLogDokumenKeluar".equals(action1)){
        	 vm = "app/pfd/frmLogDokumenKeluar.jsp";
          	
        	 if("1".equalsIgnoreCase(pagemode)){
        		 vm = "app/pfd/frmLogDokumenKeluar.jsp";
 				 context.put("mode","NewLog"); 
 				 context.put("readOnly","");
 				 context.put("disabled","");
 				 context.put("readOnlyTH","");
 				 context.put("disabledTH","");
 				 context.put("readonlych","");
 				 context.put("disabledch","");
  				
 				 view_Seksyen(session);
 				 paparSeksyen = FrmLogDokumenData.getDataSeksyen();
 				 Hashtable hB = (Hashtable) paparSeksyen.get(0);
 				 String id_seksyen = String.valueOf(hB.get("id_seksyen"));
 				 String id_negeri = String.valueOf(hB.get("id_negeri"));
 				 context.put("idSeksyen",(hB.get("id_seksyen")));
 				 context.put("idNegeri",(hB.get("id_negeri")));
  				
 				 paparDokumen = FrmLogDokumenData.getDataDokumen(idLogDokumen);
     			
 				 Hashtable hA = (Hashtable)paparDokumen.get(0);	
 				 context.put("idLogDokumen",hA.get("idLogDokumen"));
 				 if("1".equals(hA.get("idMinit"))){
 					 context.put("checkedc1","checked");
 				 }else{
 			    	context.put("checkedc1","");
 				 }
 				 if("1".equals(hA.get("idLaporan"))){
 					 context.put("checkedc2","checked");
 				 }else{
 					 context.put("checkedc2","");
 				 }
 				 if("1".equals(hA.get("idCD"))){
 			    	context.put("checkedc3","checked");
 				 }else{
 			    	context.put("checkedc3","");
 				 }
 			    
 				 context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
 				 context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
 				 context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
 				 context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
 				 context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
 				 context.put("id_tagdokumen",hA.get("id_tagdokumen"));
 				 context.put("tag_Dokumen",hA.get("tag_Dokumen"));
     			
 				 String idDokumen = getParam("idDokumen");
 				 context.put("idDokumen",idDokumen);
 				 paparLampiranLogDokumenKeluar = FrmLogDokumenData.getListLampiranLogDokumenKeluar(idDokumen);
 				 this.context.put("SenaraiListLampiranLogDokumenKeluar",paparLampiranLogDokumenKeluar);
     			
 				 Vector listPegawaiKeluar = FrmLogDokumenData.getDataPegawaiKeluarBaru(idDokumen);
 				 this.context.put("SenaraiPegawai",listPegawaiKeluar);
        		
//        		Vector listPegawaiKeluar = FrmLogDokumenData.getDataPegawaiKeluarBaru(idDokumen);
//        		Hashtable x = (Hashtable)listPegawaiKeluar.elementAt(0);
//     			this.context.put("selectidorang",x.get("user_id"));
        		    			
     			context.put("tarikh_Dokumen_Dihantar",sdf.format(now));
	        
        	 }else{
        		context.put("mode","PaparUpdate"); 
              	context.put("readonly","readonly");
    			context.put("disabled","disabled");
    			context.put("readonlyTH","readonly");
    			context.put("disabledTH","disabled");
    			context.put("readonlyCH","readonly");
    			context.put("disabledCH","disabled");
    			
        	 }
         
         }
         
         else if("tabLogDokumenDariSeksyenLain".equals(action1)){
         	
        	if(pagemode.equals("1"))
        	{
        		
        		vm = "app/pfd/frmLogDokumenDariSeksyenLain.jsp";
 				context.put("mode","New"); 
 				context.put("readOnly","readonly");
  				context.put("disabled","disabled");
  				context.put("readOnlyTPD","");
  				context.put("disabledTPD","");
  				context.put("readOnlyRujuk","");
  				context.put("disabledRujuk","");
  				context.put("readOnlyPT","");
  				context.put("disabledPT","");
  				
  				view_Seksyen(session);
  	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
  	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
  	         	String id_seksyen = String.valueOf(hB.get("id_seksyen"));
  	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
  	         	context.put("idSeksyen",(hB.get("id_seksyen")));
  	         	context.put("idNegeri",(hB.get("id_negeri")));
  				
  				
  				paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
     			
     			Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
     			context.put("idLogDokumen",hA.get("idLogDokumen"));
     			if(hA.get("idMinit").equals("1")){
 				    context.put("checkedc1","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc1","");
 			    }
 			    if(hA.get("idLaporan").equals("1")){
 			    	context.put("checkedc2","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc2","");
 			    }
 			    
 			    if(hA.get("idCD").equals("1")){
 			    	context.put("checkedc3","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc3","");
 			    }
     			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
     			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
     			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
     			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
     			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
     			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
     			context.put("catatan",hA.get("catatan"));
     			
     			String idDokumen = getParam("idDokumen");
     			context.put("idDokumen",idDokumen);
    			paparLampiranLogDokumenKeluar = FrmLogDokumenData.getListLampiranLogDokumenKeluar(idDokumen);
        		this.context.put("SenaraiListLampiranLogDokumenKeluar",paparLampiranLogDokumenKeluar);
     			
        		paparPegawaiSeksyenLain = FrmLogDokumenData.getDataPegawaiSeksyenLain(idLogDokumen);
        		Hashtable h = (Hashtable)paparPegawaiSeksyenLain.get(0);	
    			context.put("nama_Pegawai",h.get("user_name"));
    			
        		
        		listPegawai(session);
        		listPegawai = FrmLogDokumenData.getListPegawai();
        		this.context.put("SenaraiPegawai",listPegawai);
             	
             	listPTFail(session);
             	listPTFail = FrmLogDokumenData.getListPTFail();
        		this.context.put("SenaraiPTFail",listPTFail);

	        }
        	
        	else if(pagemode.equals("2"))
        	{
        		
        		vm = "app/pfd/frmLogDokumenDariSeksyenLain.jsp";
 				context.put("mode","New"); 
 				context.put("readOnly","readonly");
  				context.put("disabled","disabled");
  				context.put("readOnlyTPD","");
  				context.put("disabledTPD","");
  				context.put("readOnlyRujuk","");
  				context.put("disabledRujuk","");
  				context.put("readOnlyPT","");
  				context.put("disabledPT","");
  				
  				view_Seksyen(session);
  	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
  	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
  	         	String id_seksyen = String.valueOf(hB.get("id_seksyen"));
  	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
  	         	context.put("idSeksyen",(hB.get("id_seksyen")));
  	         	context.put("idNegeri",(hB.get("id_negeri")));
  				
  				
  				paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
     			
     			Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
     			context.put("idLogDokumen",hA.get("idLogDokumen"));
     			context.put("idLogDokumenKPTG",hA.get("idLogDokumenKPTG"));
     			if(hA.get("idMinit").equals("1")){
 				    context.put("checkedc1","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc1","");
 			    }
 			    if(hA.get("idLaporan").equals("1")){
 			    	context.put("checkedc2","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc2","");
 			    }
 			    
 			    if(hA.get("idCD").equals("1")){
 			    	context.put("checkedc3","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc3","");
 			    }
     			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
     			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
     			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
     			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
     			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
     			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
     			context.put("catatan",hA.get("catatan"));
     			context.put("id_tagdokumen",hA.get("id_tagdokumen"));
     		    context.put("tag_Dokumen",hA.get("tag_Dokumen"));
     			
     			String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
    			paparLampiranLogDokumenKeluar = FrmLogDokumenData.getListLampiranLogDokumenKPTG(idLogDokumenKPTG);
        		this.context.put("SenaraiListLampiranLogDokumenKeluar",paparLampiranLogDokumenKeluar);
     			
        		paparPegawaiSeksyenLain = FrmLogDokumenData.getDataPegawaiSeksyenLain(idLogDokumen);
        		Hashtable h = (Hashtable)paparPegawaiSeksyenLain.get(0);	
    			context.put("nama_Pegawai",h.get("user_name"));
    			
        		
        		listPegawai(session);
        		listPegawai = FrmLogDokumenData.getListPegawai();
        		this.context.put("SenaraiPegawai",listPegawai);
             	
             	listPTFail(session);
             	listPTFail = FrmLogDokumenData.getListPTFail();
        		this.context.put("SenaraiPTFail",listPTFail);

	        }
        	else
        	{
        		vm = "app/pfd/frmLogDokumenDariSeksyenLain.jsp";
        		context.put("mode","PaparUpdate"); 
              	context.put("readonly","readonly");
    			context.put("disabled","disabled");
  				context.put("readOnlyTPD","readonly");
  				context.put("disabledTPD","disabled");
  				context.put("readOnlyRujuk","readonly");
  				context.put("disabledRujuk","disabled");
  				context.put("readOnlyPT","readonly");
  				context.put("disabledPT","disabled");
  				
  				context.put("jenis_Dokumen","");
     			context.put("no_Rujukan_Lain","");
     			context.put("pengirim_Dokumen","");
     			context.put("tajuk_Dokumen","");
     			context.put("penerima_Dokumen","");
     			context.put("tarikh_Dokumen","");
     			context.put("catatan","");
     			context.put("nama_Pegawai","");
     			context.put("nama_Pengarah","");
    			
     			listPegawai(session);
        		listPegawai = FrmLogDokumenData.getListPegawai();
        		this.context.put("SenaraiPegawai",listPegawai);
             	
             	listPTFail(session);
             	listPTFail = FrmLogDokumenData.getListPTFail();
        		this.context.put("SenaraiPTFail",listPTFail);
    			
  				
    		
        	}
     		
          }
         else if("tabLogDokumenDalaman".equals(action1)){
         	
         	vm = "app/pfd/frmLogDokumenDalaman.jsp";
           	
         	if(pagemode.equals("1"))
         	{
         		
         		vm = "app/pfd/frmLogDokumenDalaman.jsp";
  				context.put("mode","NewLog"); 
  				context.put("readOnly","readonly");
   				context.put("disabled","disabled");
   				context.put("readOnlyTH","");
   				context.put("disabledTH","");
   				context.put("readonlych","");
   				context.put("disabledch","");
   				
   				view_Seksyen(session);
   	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
   	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
   	         	String id_seksyen = String.valueOf(hB.get("id_seksyen"));
   	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
   	         	context.put("idSeksyen",(hB.get("id_seksyen")));
   	         	context.put("idNegeri",(hB.get("id_negeri")));
   				
   				paparDokumen = FrmLogDokumenData.getDataDokumen(idLogDokumen);
      			
      			Hashtable hA = (Hashtable)paparDokumen.get(0);	
      			context.put("idLogDokumen",hA.get("idLogDokumen"));
      			if("1".equals(hA.get("idMinit"))){
  				    context.put("checkedc1","checked");
  			    }
  			    else
  			    {
  			    	context.put("checkedc1","");
  			    }
  			    if("1".equals(hA.get("idLaporan"))){
  			    	context.put("checkedc2","checked");
  			    }
  			    else
  			    {
  			    	context.put("checkedc2","");
  			    }
  			    if("1".equals(hA.get("idCD"))){
  			    	context.put("checkedc3","checked");
  			    }
  			    else
  			    {
  			    	context.put("checkedc3","");
  			    }
  			    
      			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
      			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
      			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
      			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
      			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
      			context.put("id_tagdokumen",hA.get("id_tagdokumen"));
     		    context.put("tag_Dokumen",hA.get("tag_Dokumen"));
      			
      			String idDokumen = getParam("idDokumen");
      			context.put("idDokumen",idDokumen);
     			paparLampiranLogDokumenKeluar = FrmLogDokumenData.getListLampiranLogDokumenKeluar(idDokumen);
         		this.context.put("SenaraiListLampiranLogDokumenKeluar",paparLampiranLogDokumenKeluar);
      			
         		
    
         		Vector listPegawaiKeluar = FrmLogDokumenData.getDataPegawaiKeluarBaru(idDokumen);
      			this.context.put("SenaraiPegawai",listPegawaiKeluar);
      			
      			Vector listPTFailKeluar = FrmLogDokumenData.getDataPTKeluar(idDokumen);
	    		
	    		if(listPTFailKeluar.size()>0)
	    		{
        		Hashtable x = (Hashtable)listPTFailKeluar.elementAt(0);
     			this.context.put("selectPTFail",x.get("user_id"));
	    		}
	    		else
	    		{
	    		this.context.put("selectPTFail","");	
	    		}
     			
      			
      			listPTFail(session);
             	listPTFail = FrmLogDokumenData.getListPTFail();
        		this.context.put("SenaraiPTFail",listPTFail);
         		
//         		Vector listPegawaiKeluar = FrmLogDokumenData.getDataPegawaiKeluarBaru(idDokumen);
//         		Hashtable x = (Hashtable)listPegawaiKeluar.elementAt(0);
//      			this.context.put("selectidorang",x.get("user_id"));
         		    			
      			context.put("tarikh_Dokumen_Dihantar",sdf.format(now));
 	        }
         	else
         	{
         		context.put("mode","PaparUpdate"); 
               	context.put("readonly","readonly");
     			context.put("disabled","disabled");
     			context.put("readonlyTH","readonly");
     			context.put("disabledTH","disabled");
     			context.put("readonlyCH","readonly");
     			context.put("disabledCH","disabled");
     			
     		
         	}
          }
          
         
         else if("simpanLogDokumenMasuk".equals(action1)){
          	vm = "app/pfd/frmLogDokumenMasuk.jsp";
            	//context.put("pagemode",0); 
            	context.put("mode","View"); 
             	context.put("readonly","readonly");
 				context.put("disabled","disabled");
 				context.put("readonlyPT","readonly");
 				context.put("disabledPT","disabled");
            	
            	String id_LogDokumen = simpanLogDokumenMasuk(session);
            	//view_LogDokumen(session);
    			paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(id_LogDokumen);
    			
    			Hashtable h = (Hashtable)paparLogDokumen.get(0);	
    			context.put("idLogDokumen",h.get("idLogDokumen"));
    			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
    			context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
    			context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
    			context.put("pengirim_Dokumen",h.get("pengirim_Dokumen"));
    			context.put("tarikh_Dokumen",h.get("tarikh_Dokumen"));
    			context.put("tarikh_Dokumen_Diterima",h.get("tarikh_Dokumen_Diterima"));
    			context.put("status_LogDokumen",h.get("status_LogDokumen"));
    			context.put("tag_Dokumen",h.get("tag_Dokumen"));
    			context.put("nama_Pegawai",h.get("id_penerima"));
				context.put("nama_PTPA",h.get("idPTFail"));
				
				
				
    			if(h.get("idMinit").equals(null) || h.get("idMinit").equals("0")){
 				    context.put("checkedc1","");
 			    }
 			    else
 			    {
 			    	context.put("checkedc1","checked");
 			    }
    			if(h.get("idLaporan").equals(null) || h.get("idLaporan").equals("0")){
 			    	context.put("checkedc2","");
 			    }
 			    else
 			    {
 			    	context.put("checkedc2","checked");
 			    }
    			if(h.get("idCD").equals(null) || h.get("idCD").equals("0")){
 			    	context.put("checkedc3","");
 			    }
 			    else
 			    {
 			    	context.put("checkedc3","checked");
 			    }
 			    
 			   tajuk_Dokumen = String.valueOf(h.get("tajuk_Dokumen"));
 			   tarikh_Dokumen_Diterima = String.valueOf(h.get("tarikh_Dokumen_Diterima"));
 			   String noRujukanLain = String.valueOf(h.get("no_Rujukan_Lain"));
 			   String idPTFail = String.valueOf(h.get("idPTFail"));
    			
    			downloadDraf(id_LogDokumen);
    			
    			listPegawai = FrmLogDokumenData.getDataPegawai(id_LogDokumen);
    			this.context.put("SenaraiPegawai",listPegawai);
    			
    			listPTFail = FrmLogDokumenData.getDataPTFail(id_LogDokumen);
    			this.context.put("SenaraiPTFail",listPTFail);
    			
    		
             	

    			//listLampiranLogDokumen(session);
    			paparLampiranLogDokumenMasuk = FrmLogDokumenData.getListLampiranLogDokumenMasuk(id_LogDokumen);
        		this.context.put("SenaraiListLampiranLogDokumenMasuk",paparLampiranLogDokumenMasuk);
        		this.context.put("paparLampiranLogDokumenMasuk_size", paparLampiranLogDokumenMasuk.size());
//        		String getSize = paparLampiranLogDokumenMasuk
//        		String emailRecipient = getEmail(idPTFail);
//        		
//        		EkptgEmailSender email = EkptgEmailSender.getInstance();
//        		email.FROM ="etapp_webmaster@ekptg.gov.my";
//        		email.SUBJECT ="Dokumen baru telah didaftarkan - "+noRujukanLain+".";
//        		email.MESSAGE ="TAJUK : <B>'"+tajuk_Dokumen+"'</B>."+
//        					   "<br><br>Tuan/Puan,"+
//        					   "<br><br>Permohonan : Dokumen <B>"+noRujukanLain+"</B> telah diterima pada : <B>"+tarikh_Dokumen_Diterima+"</B> dan telah "+
//        					   "sedia untuk semakan dan pengesahan dari pihak tuan/puan."+
//        					   "<br><br>Sila <i><b>login</b></i> masuk ke <b>www.etapp.gov.my</b> untuk semakan serta pengesahan dari pihak tuan/puan."+
//        					   "<br><br>Sekian,Terima Kasih."+
//        					   "<br><b>webmaster@etapp.gov.my</b>"+
//        					   "<br><br>Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.";
//
//        		email.MULTIPLE_RECIEPIENT = new String[1];
//        		email.MULTIPLE_RECIEPIENT[0]=emailRecipient;
//
//        		email.TO_CC = new String[3];
//        		email.TO_CC[0]  = "cipon.it@gmail.com";
//        		email.TO_CC[1]  = "rasul@hla-group.com";
//        		email.TO_CC[2]  = "rizuan@hla-group.com";
//        		email.sendEmail();
           }
         
         else if("simpanLogDokumenKeluar".equals(action1)){
        	vm = "app/pfd/frmLogDokumenKeluar.jsp";
         	context.put("mode","View"); 
  			context.put("readOnly","readonly");
   			context.put("disabled","disabled");
   			context.put("readOnlyTH","readonly");
   			context.put("disabledTH","disabled");
   			context.put("readonlych","readonly");
   			context.put("readonlych","disabled");

   			String idDokumen = getParam("idDokumen");
   			
   			simpanLogDokumenKeluar(session);
   	
          	paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
  			
  			Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
  			context.put("idLogDokumen",hA.get("idLogDokumen"));
  			if(hA.get("idMinit").equals("1")){
 				    context.put("checkedc1","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc1","");
 			    }
 			    if(hA.get("idLaporan").equals("1")){
 			    	context.put("checkedc2","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc2","");
 			    }
 			    
  			    if(hA.get("idCD").equals("1")){
  			    	context.put("checkedc3","checked");
  			    }
  			    else
  			    {
  			    	context.put("checkedc3","");
  			    }
  			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
  			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
  			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
  			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
  			context.put("tarikh_Dokumen_Dihantar",hA.get("tarikh_Dokumen_Dihantar"));
  			context.put("jenis_Penghantaran",hA.get("cara_Penghantaran"));
  			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
  			context.put("tag_Dokumen",hA.get("tag_Dokumen"));
  			
  			paparLampiranLogDokumenKeluarPapar = FrmLogDokumenData.getListLampiranLogDokumenKeluarPapar(idLogDokumen);
     		this.context.put("SenaraiListLampiranLogDokumenKeluarPapar",paparLampiranLogDokumenKeluarPapar);
     		
     		Vector listPegawaiKeluar = FrmLogDokumenData.getDataPegawaiKeluar(idLogDokumen);
 			this.context.put("SenaraiPegawai",listPegawaiKeluar);
     		
         } 
         
         else if("simpanLogDokumenDalaman".equals(action1)){
         	vm = "app/pfd/frmLogDokumenDalaman.jsp";
          	context.put("mode","View"); 
   			context.put("readOnly","readonly");
    			context.put("disabled","disabled");
    			context.put("readOnlyTH","readonly");
    			context.put("disabledTH","disabled");
    			context.put("readonlych","readonly");
    			context.put("readonlych","disabled");

    			String idDokumen = getParam("idDokumen");
    			
    			simpanLogDokumenDalaman(session);
    	
           	paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
   			
   			Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
   			context.put("idLogDokumen",hA.get("idLogDokumen"));
   			if(hA.get("idMinit").equals("1")){
  				    context.put("checkedc1","checked");
  			    }
  			    else
  			    {
  			    	context.put("checkedc1","");
  			    }
  			    if(hA.get("idLaporan").equals("1")){
  			    	context.put("checkedc2","checked");
  			    }
  			    else
  			    {
  			    	context.put("checkedc2","");
  			    }
  			    
   			    if(hA.get("idCD").equals("1")){
   			    	context.put("checkedc3","checked");
   			    }
   			    else
   			    {
   			    	context.put("checkedc3","");
   			    }
   			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
   			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
   			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
   			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
   			context.put("tarikh_Dokumen_Dihantar",hA.get("tarikh_Dokumen_Dihantar"));
   			context.put("jenis_Penghantaran",hA.get("cara_Penghantaran"));
   			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
   			context.put("tag_Dokumen",hA.get("tag_Dokumen"));
   			
   			paparLampiranLogDokumenKeluarPapar = FrmLogDokumenData.getListLampiranLogDokumenKeluarPapar(idLogDokumen);
      		this.context.put("SenaraiListLampiranLogDokumenKeluarPapar",paparLampiranLogDokumenKeluarPapar);
      		
      		Vector listPegawaiKeluar = FrmLogDokumenData.getDataPegawaiKeluar(idLogDokumen);
  			this.context.put("SenaraiPegawai",listPegawaiKeluar);
      		
          } 
          
         
         else if("kemaskiniLogDokumenMasuk".equals(action1)){
           	vm = "app/pfd/frmLogDokumenMasuk.jsp";
             	context.put("pagemode",0); 
             	context.put("mode","Update"); 
             	context.put("readonly","");
 				context.put("disabled","");
             	
 				
             	//view_LogDokumen(session);
 				paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
    			
    			Hashtable h = (Hashtable)paparLogDokumen.get(0);	
    			context.put("idLogDokumen",h.get("idLogDokumen"));
    			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
    			context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
    			context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
    			context.put("pengirim_Dokumen",h.get("pengirim_Dokumen"));
    			context.put("tarikh_Dokumen",h.get("tarikh_Dokumen"));
    			context.put("tarikh_Dokumen_Diterima",h.get("tarikh_Dokumen_Diterima"));
    			context.put("status_LogDokumen",h.get("status_LogDokumen"));
    			context.put("tag_Dokumen",h.get("tag_Dokumen"));
    			context.put("id_tagdokumen",h.get("id_tagdokumen"));
    			context.put("nama_Pegawai",h.get("id_penerima"));
				context.put("nama_PTPA",h.get("idPTFail"));
				

    			if(h.get("idMinit").equals("1")){
 				    context.put("checkedc1","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc1","");
 			    }
 			    if(h.get("idLaporan").equals("1")){
 			    	context.put("checkedc2","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc2","");
 			    }
 			    
 			    if(h.get("idCD").equals("1")){
 			    	context.put("checkedc3","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc3","");
 			    }
 			    
     		
             	listPegawai(session);
        		listPegawai = FrmLogDokumenData.getListPegawai();
        		this.context.put("SenaraiPegawai",listPegawai);
             	
             	listPTFail(session);
             	listPTFail = FrmLogDokumenData.getListPTFail();
        		this.context.put("SenaraiPTFail",listPTFail);
        		
        		
        		
        		Vector listPegawaiMasuk = FrmLogDokumenData.getDataPegawai(idLogDokumen);
        		if(listPegawaiMasuk.size()!=0){
        			Hashtable x = (Hashtable)listPegawaiMasuk.elementAt(0);
         			this.context.put("selectPegawai",x.get("user_id"));
        		}   
        		
     			
     			Vector listPT = FrmLogDokumenData.getDataPT(idLogDokumen);    			
          		if(listPT.size()!=0){
          			Hashtable temp =(Hashtable)listPT.elementAt(0);
         			this.context.put("selectidorang",temp.get("user_id"));
        		}  
     			
             	
             	
  	
             	
            }
         
         else if("kemaskiniLogDokumenKeluar".equals(action1)){
        	 vm = "app/pfd/frmLogDokumenKeluar.jsp";
          	context.put("mode","Update"); 
   			context.put("readOnly","");
    			context.put("disabled","");
    			context.put("readOnlyTH","");
    			context.put("disabledTH","");
    			context.put("readonlych","");
    			context.put("disabledch","");

    			String idDokumen = getParam("idDokumen");
    	
           	paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
   			
   			Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
   			context.put("idLogDokumen",hA.get("idLogDokumen"));
   			if(hA.get("idMinit").equals("1")){
  				    context.put("checkedc1","checked");
  			    }
  			    else
  			    {
  			    	context.put("checkedc1","");
  			    }
  			    if(hA.get("idLaporan").equals("1")){
  			    	context.put("checkedc2","checked");
  			    }
  			    else
  			    {
  			    	context.put("checkedc2","");
  			    }
  			    
   			    if(hA.get("idCD").equals("1")){
   			    	context.put("checkedc3","checked");
   			    }
   			    else
   			    {
   			    	context.put("checkedc3","");
   			    }
   			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
   			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
   			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
   			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
   			context.put("tarikh_Dokumen_Dihantar",hA.get("tarikh_Dokumen_Dihantar"));
   			context.put("jenis_Penghantaran",hA.get("cara_Penghantaran"));
   			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
   			context.put("tag_Dokumen",hA.get("tag_Dokumen"));
			context.put("id_tagdokumen",hA.get("id_tagdokumen"));
			
   			paparLampiranLogDokumenKeluarPapar = FrmLogDokumenData.getListLampiranLogDokumenKeluarPapar(idLogDokumen);
      		this.context.put("SenaraiListLampiranLogDokumenKeluarPapar",paparLampiranLogDokumenKeluarPapar);
      		
      		Vector listPegawaiKeluar = FrmLogDokumenData.getDataPegawaiKeluar(idLogDokumen);
  			this.context.put("SenaraiPegawai",listPegawaiKeluar);
              	
             }
         	else if("updateLogDokumenMasuk".equals(action1)){
         		vm = "app/pfd/frmLogDokumenMasuk.jsp";
             	context.put("pagemode",0); 
             	context.put("mode","PaparUpdate"); 
              	context.put("readonly","readonly");
  				context.put("disabled","disabled");
  				context.put("$readOnlyTH","readonly");
  				context.put("$disabledTH","disabled");
             	
             	updateLogDokumenMasuk(session);
             	
             	
             	//view_LogDokumen(session);
            	paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
    			
    			Hashtable h = (Hashtable)paparLogDokumen.get(0);	
    			context.put("idLogDokumen",h.get("idLogDokumen"));
    			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
    			context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
    			context.put("tajuk_Dokumen",h.get("tajuk_Dokumen"));
    			context.put("pengirim_Dokumen",h.get("pengirim_Dokumen"));
    			context.put("tarikh_Dokumen",h.get("tarikh_Dokumen"));
    			context.put("tarikh_Dokumen_Diterima",h.get("tarikh_Dokumen_Diterima"));
    			context.put("status_LogDokumen",h.get("status_LogDokumen"));
    			context.put("tag_Dokumen",h.get("tag_Dokumen"));
    			context.put("id_tagdokumen",h.get("id_tagdokumen"));
    			if(h.get("idMinit").equals("1")){
 				    context.put("checkedc1","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc1","");
 			    }
 			    if(h.get("idLaporan").equals("1")){
 			    	context.put("checkedc2","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc2","");
 			    }
 			    
 			    if(h.get("idCD").equals("1")){
 			    	context.put("checkedc3","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc3","");
 			    }
 			    
     			
     			listPegawai = FrmLogDokumenData.getDataPegawai(idLogDokumen);
     			this.context.put("SenaraiPegawai",listPegawai);
     			
     			listPTFail = FrmLogDokumenData.getDataPTFail(idLogDokumen);
     			this.context.put("SenaraiPTFail",listPTFail);

             	
             	
            }
         
         	else if("hapusLogDokumenMasuk".equals(action1)){
            	
         		vm = "app/pfd/frmLogDokumenMasuk.jsp";  
         		
         		hapusLogDokumenMasuk(session);
         		
         		
    			if(jenisDokumen.equalsIgnoreCase("1") ){
    	         	context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("jenis_Dokumen","");
    				context.put("tarikh_Dokumen_Diterima","");
    				context.put("tarikh_Dokumen","");
    				context.put("no_Rujukan_Lain","");
    				context.put("pengirim_Dokumen","");
    				context.put("tajuk_Dokumen","");
    				context.put("modeJenisDokumen","1"); 
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("action1","tabLogDokumenMasuk");
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	   
    	         	if ("minit" != null){
    					this.context.put("checkboxMinit", "1");
    				}
    				else{
    					this.context.put("checkboxMinit", "0");
    				}
    			
    				if ("laporan" != null){
    					this.context.put("checkboxLaporan", "1");
    				}
    				else{
    					this.context.put("checkboxLaporan", "0");
    				}
    				
    				
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    				
    			}
    			else if(jenisDokumen.equalsIgnoreCase("2")){
    				context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("jenis_Dokumen","");
    				context.put("tarikh_Dokumen_Diterima","");
    				context.put("tarikh_Dokumen","");
    				context.put("no_Rujukan_Lain","");
    				context.put("pengirim_Dokumen","");
    				context.put("tajuk_Dokumen","");
    				context.put("modeJenisDokumen","1"); 
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("modeJenisDokumen","2"); 
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("action1","tabLogDokumenMasuk");
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	         	this.context.put("tarikh_Dokumen", sdf.format(now));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	   
    	         	if ("minit" != null){
    					this.context.put("checkboxMinit", "1");
    				}
    				else{
    					this.context.put("checkboxMinit", "0");
    				}
    			
    				if ("laporan" != null){
    					this.context.put("checkboxLaporan", "1");
    				}
    				else{
    					this.context.put("checkboxLaporan", "0");
    				}
    				
    				
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    			}
    			else if(jenisDokumen.equalsIgnoreCase("3")){
    				context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("jenis_Dokumen","");
    				context.put("tarikh_Dokumen_Diterima","");
    				context.put("tarikh_Dokumen","");
    				context.put("no_Rujukan_Lain","");
    				context.put("pengirim_Dokumen","");
    				context.put("tajuk_Dokumen","");
    				context.put("modeJenisDokumen","1"); 
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("modeJenisDokumen","3");
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("action1","tabLogDokumenMasuk");
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	   
    	         	if ("minit" != null){
    					this.context.put("checkboxMinit", "1");
    				}
    				else{
    					this.context.put("checkboxMinit", "0");
    				}
    			
    				if ("laporan" != null){
    					this.context.put("checkboxLaporan", "1");
    				}
    				else{
    					this.context.put("checkboxLaporan", "0");
    				}
    				
    				
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    			}
    			
    			else if(jenisDokumen.equalsIgnoreCase("4")){
    				context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("jenis_Dokumen","");
    				context.put("tarikh_Dokumen_Diterima","");
    				context.put("tarikh_Dokumen","");
    				context.put("no_Rujukan_Lain","");
    				context.put("pengirim_Dokumen","");
    				context.put("tajuk_Dokumen","");
    				context.put("modeJenisDokumen","1"); 
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("modeJenisDokumen","4");
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("action1","tabLogDokumenMasuk");
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	   
    	         	if ("minit" != null){
    					this.context.put("checkboxMinit", "1");
    				}
    				else{
    					this.context.put("checkboxMinit", "0");
    				}
    			
    				if ("laporan" != null){
    					this.context.put("checkboxLaporan", "1");
    				}
    				else{
    					this.context.put("checkboxLaporan", "0");
    				}
    				
    				
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    			}
    			
    			else{
    	         	context.put("mode","New"); 
    	         	context.put("readonly","");
    				context.put("disabled","");
    				context.put("readonlyPT","");
    				context.put("disabledPT","");
    				context.put("jenis_Dokumen","");
    				context.put("tarikh_Dokumen_Diterima","");
    				context.put("tarikh_Dokumen","");
    				context.put("no_Rujukan_Lain","");
    				context.put("pengirim_Dokumen","");
    				context.put("tajuk_Dokumen","");
    				context.put("modeJenisDokumen","0");
    				context.put("jenis_Dokumen",jenisDokumen);
    				context.put("action1","tabLogDokumenMasuk");
    				
    				view_Seksyen(session);
    	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
    	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
    	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
    	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
    	         	context.put("idSeksyen",(hA.get("id_seksyen")));
    	         	context.put("idNegeri",(hA.get("id_negeri")));
    	
    	         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChangeSeksyen();\" "));
    	        	   
    	         	if ("minit" != null){
    					this.context.put("checkboxMinit", "1");
    				}
    				else{
    					this.context.put("checkboxMinit", "0");
    				}
    			
    				if ("laporan" != null){
    					this.context.put("checkboxLaporan", "1");
    				}
    				else{
    					this.context.put("checkboxLaporan", "0");
    				}
    				
    				
    	         	listPegawai(session);
    	    		listPegawai = FrmLogDokumenData.getListPegawai();
    	    		this.context.put("SenaraiPegawai",listPegawai);
    	         	
    	         	listPTFail(session);
    	         	listPTFail = FrmLogDokumenData.getListPTFail();
    	    		this.context.put("SenaraiPTFail",listPTFail);
    			}	
     	
             }
         
         	else if("hapusLogDokumenKeluar".equals(action1)){
         		vm = "app/pfd/frmLogDokumenKeluar.jsp";  
         		context.put("mode","New"); 
     			context.put("readOnly","readonly");
      			context.put("disabled","disabled");
      			context.put("readOnlyTH","readonly");
      			context.put("disabledTH","disabled");
      			context.put("readonlych","readonly");
      			context.put("disabledch","disabled");
      			context.put("action1","tabLogDokumenMasuk");
      			
      			
         		hapusLogDokumenKeluar(session);
         		
         		context.put("penerima_Dokumen","");
         		context.put("tarikh_Dokumen_Dihantar","");
         		context.put("jenis_Penghantaran","0");
         		context.put("checkedc1", "0");
         		context.put("checkedc2", "0");
         		context.put("checkedc3", "0");
         		
         		paparLampiranLogDokumenKeluarPapar = FrmLogDokumenData.getListLampiranLogDokumenKeluarPapar(idLogDokumen);
         		this.context.put("SenaraiListLampiranLogDokumenKeluarPapar",paparLampiranLogDokumenKeluarPapar);
         	}
         
         	else if("papar".equals(action1)){
            	
              	//context.put("pagemode",0); 
              	//context.put("mode","New"); 
              	//context.put("readonly","disabled");
  				//context.put("disabled","disabled");
              	
              	paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
     			Hashtable h = (Hashtable)paparLogDokumen.get(0);	

     			if(h.get("flag_logdokumen").equals("1")){
     				vm = "app/pfd/frmLogDokumenMasuk.jsp";
     				context.put("mode","View"); 
     				context.put("readonly","disabled");
      				context.put("disabled","disabled");
      				
					paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
         			
         			Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
         			context.put("idLogDokumen",hA.get("idLogDokumen"));
         			if(hA.get("idMinit").equals("1")){
     				    context.put("checkedc1","checked");
     			    }
     			    else
     			    {
     			    	context.put("checkedc1","");
     			    }
     			    if(hA.get("idLaporan").equals("1")){
     			    	context.put("checkedc2","checked");
     			    }
     			    else
     			    {
     			    	context.put("checkedc2","");
     			    }
     			    
     			    if(hA.get("idCD").equals("1")){
     			    	context.put("checkedc3","checked");
     			    }
     			    else
     			    {
     			    	context.put("checkedc3","");
     			    }
         			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
         			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
         			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
         			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
         			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
         			context.put("tarikh_Dokumen_Diterima",hA.get("tarikh_Dokumen_Diterima"));
         			context.put("tag_Dokumen",hA.get("tag_Dokumen"));
         			
         			
         			Vector listPegawaiMasuk = FrmLogDokumenData.getDataPegawai(idLogDokumen);
         			this.context.put("SenaraiPegawai",listPegawaiMasuk);
         			
         			Vector listPT = FrmLogDokumenData.getDataPT(idLogDokumen);
         			this.context.put("SenaraiPTFail",listPT);
      				
         			paparLampiranLogDokumenMasuk = FrmLogDokumenData.getListLampiranLogDokumenMasuk(idLogDokumen);
            		this.context.put("SenaraiListLampiranLogDokumenMasuk",paparLampiranLogDokumenMasuk);
            		this.context.put("paparLampiranLogDokumenMasuk_size", paparLampiranLogDokumenMasuk.size());

     			}
     			else{
     				vm = "app/pfd/frmLogDokumenKeluar.jsp";
     				context.put("mode","View"); 
     				context.put("readOnly","disabled");
      				context.put("disabled","disabled");
      				context.put("readOnlyTH","disabled");
      				context.put("disabledTH","disabled");
      				context.put("readonlych","disabled");
      				context.put("disabledch","disabled");
      				
      				view_Seksyen(session);
      	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
      	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
      	         	String id_seksyen = String.valueOf(hB.get("id_seksyen"));
      	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
      	         	context.put("idSeksyen",(hB.get("id_seksyen")));
      	         	context.put("idNegeri",(hB.get("id_negeri")));
      				
      				paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
         			
         			Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
         			context.put("idLogDokumen",hA.get("idLogDokumen"));
         			if(hA.get("idMinit").equals("1")){
     				    context.put("checkedc1","checked");
     			    }
     			    else
     			    {
     			    	context.put("checkedc1","");
     			    }
     			    if(hA.get("idLaporan").equals("1")){
     			    	context.put("checkedc2","checked");
     			    }
     			    else
     			    {
     			    	context.put("checkedc2","");
     			    }
     			    
     			    if(hA.get("idCD").equals("1")){
     			    	context.put("checkedc3","checked");
     			    }
     			    else
     			    {
     			    	context.put("checkedc3","");
     			    }
         			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
         			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
         			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
         			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
         			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
         			context.put("jenis_Penghantaran",hA.get("cara_Penghantaran"));
         			
         			//String idLogDokumen = getParam("idLogDokumen");
         			//context.put("idDokumen",idDokumen);
         			
        			paparLampiranLogDokumenKeluarPapar = FrmLogDokumenData.getListLampiranLogDokumenKeluarPapar(idLogDokumen);
            		this.context.put("SenaraiListLampiranLogDokumenKeluarPapar",paparLampiranLogDokumenKeluarPapar);
         			
         			Vector listPegawaiKeluar = FrmLogDokumenData.getDataPegawaiKeluar(idLogDokumen);
         			this.context.put("SenaraiPegawai",listPegawaiKeluar);
         			
         			context.put("tarikh_Dokumen_Dihantar",sdf.format(now));
      				
     			}
     		
              	
             }
         
         else if("updateLogDokumenKeluar".equals(action1)){
         	
        	 vm = "app/pfd/frmLogDokumenKeluar.jsp";
        	context.put("mode","PaparUpdate"); 
 			context.put("readOnly","readonly");
  			context.put("disabled","disabled");
  			context.put("readOnlyTH","readonly");
  			context.put("disabledTH","disabled");
  			context.put("readonlych","readonly");
  			context.put("disabledch","disabled");
           	
  			view_Seksyen(session);
	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
	         	String id_seksyen = String.valueOf(hB.get("id_seksyen"));
	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
	         	context.put("idSeksyen",(hB.get("id_seksyen")));
	         	context.put("idNegeri",(hB.get("id_negeri")));
  			
         	updateLogDokumenKeluar(session);
         	
         	
         	paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
 			
 			Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
 			context.put("idLogDokumen",hA.get("idLogDokumen"));
 			if(hA.get("idMinit").equals("1")){
				    context.put("checkedc1","checked");
			    }
			    else
			    {
			    	context.put("checkedc1","");
			    }
			    if(hA.get("idLaporan").equals("1")){
			    	context.put("checkedc2","checked");
			    }
			    else
			    {
			    	context.put("checkedc2","");
			    }
			    
 			    if(hA.get("idCD").equals("1")){
 			    	context.put("checkedc3","checked");
 			    }
 			    else
 			    {
 			    	context.put("checkedc3","");
 			    }
 			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
 			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
 			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
 			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
 			context.put("tarikh_Dokumen_Dihantar",hA.get("tarikh_Dokumen_Dihantar"));
 			context.put("jenis_Penghantaran",hA.get("cara_Penghantaran"));
 			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
 			context.put("tag_Dokumen",hA.get("tag_Dokumen"));
			context.put("id_tagdokumen",hA.get("id_tagdokumen"));
         		
 			paparLampiranLogDokumenKeluarPapar = FrmLogDokumenData.getListLampiranLogDokumenKeluarPapar(idLogDokumen);
    		this.context.put("SenaraiListLampiranLogDokumenKeluarPapar",paparLampiranLogDokumenKeluarPapar);

          }
         
         else if("updateLogDokumenSeksyenLain".equals(action1)){
          	
        	 vm = "app/pfd/frmLogDokumenDariSeksyenLain.jsp";
        	context.put("mode","View"); 
 			context.put("readOnly","readonly");
  			context.put("disabled","disabled");
  			context.put("readOnlyTPD","readonly");
  			context.put("disabledTPD","disabled");
  			context.put("readOnlyRujuk","readonly");
  			context.put("disabledRujuk","disabled");
  			context.put("readOnlyPT","readonly");
  			context.put("disabledPT","disabled");
           	
  			
  			if(pagemode.equals("1")){
  				
  				
  				updateLogDokumenSeksyenLain(session);
  	         	
  	         	String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
  	         	String idDokumen = getParam("idDokumen");
  	         	
  	         	paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
  	 			
  	        	Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
  	 			context.put("idLogDokumen",hA.get("idLogDokumen"));
  	 			if(hA.get("idMinit").equals("1")){
  					    context.put("checkedc1","checked");
  				    }
  				    else
  				    {
  				    	context.put("checkedc1","");
  				    }
  				    if(hA.get("idLaporan").equals("1")){
  				    	context.put("checkedc2","checked");
  				    }
  				    else
  				    {
  				    	context.put("checkedc2","");
  				    }
  				    
  	 			    if(hA.get("idCD").equals("1")){
  	 			    	context.put("checkedc3","checked");
  	 			    }
  	 			    else
  	 			    {
  	 			    	context.put("checkedc3","");
  	 			    }
  	 			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
  	 			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
  	 			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
  	 			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
  	 			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
  	 			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
  	 			context.put("tarikh_Dokumen_Diterima",hA.get("tarikh_Dokumen_Diterima"));
  	 			context.put("catatan",hA.get("catatan"));
  	 			
  	 			
  	    		paparPegawaiSeksyenLain = FrmLogDokumenData.getDataPegawaiSeksyenLain(idLogDokumen);
  	    		Hashtable h = (Hashtable)paparPegawaiSeksyenLain.get(0);	
  				context.put("nama_Pegawai",h.get("user_name"));
  				
  	         		
  	 			paparLampiranLogDokumenKeluarPapar = FrmLogDokumenData.getListLampiranLogDokumenKeluarPapar(idLogDokumen);
  	    		this.context.put("SenaraiListLampiranLogDokumenKeluarPapar",paparLampiranLogDokumenKeluarPapar);
  	    		
  	    		listPegawai = FrmLogDokumenData.getDataPegawai(idLogDokumen);
  	 			this.context.put("SenaraiPegawai",listPegawai);
  	 			
  	 			listPTFail = FrmLogDokumenData.getDataPTFail(idLogDokumen);
  	 			this.context.put("SenaraiPTFail",listPTFail);	
  	 			
  	 			tajuk_Dokumen = String.valueOf(hA.get("tajuk_Dokumen"));
  	 			tarikh_Dokumen_Diterima = String.valueOf(hA.get("tarikh_Dokumen_Diterima"));
  	 			no_Rujukan_Lain = String.valueOf(hA.get("no_Rujukan_Lain"));
  	 			String idPTFail = String.valueOf(hA.get("idPTFail"));
  	 			
//  	 			String emailRecipient = getEmail(idPTFail);
//        		
//        		EkptgEmailSender email = EkptgEmailSender.getInstance();
//        		email.FROM ="etapp_webmaster@ekptg.gov.my";
//        		email.SUBJECT ="Sila semak Minit Arahan bagi dokumen "+no_Rujukan_Lain+" untuk tindakan selanjutnya.";
//        		email.MESSAGE ="TAJUK : <B>'"+tajuk_Dokumen+"'</B>."+
//        					   "<br><br>Tuan/Puan,"+
//        					   "<br><br>Permohonan : Dokumen <B>"+no_Rujukan_Lain+"</B> telah diterima pada : <B>"+tarikh_Dokumen_Diterima+"</B> dan telah "+
//        					   "sedia untuk semakan dan pengesahan dari pihak tuan/puan."+
//        					   "<br><br>Sila <i><b>login</b></i> masuk ke <b>www.etapp.gov.my</b> untuk semakan serta pengesahan dari pihak tuan/puan."+
//        					   "<br><br>Sekian,Terima Kasih."+
//        					   "<br><b>webmaster@etapp.gov.my</b>"+
//        					   "<br><br>Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.";
//
//        		email.MULTIPLE_RECIEPIENT = new String[1];
//        		email.MULTIPLE_RECIEPIENT[0]=emailRecipient;
//
//        		email.TO_CC = new String[3];
//        		email.TO_CC[0]  = "cipon.it@gmail.com";
//        		email.TO_CC[1]  = "rasul@hla-group.com";
//        		email.TO_CC[2]  = "rizuan@hla-group.com";
//        		email.sendEmail();
  			}
  			
  			else{
  				updateLogDokumenSeksyenKPTG(session);
  	         	
  	         	String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
  	         	
  	         	paparLogDokumen = FrmLogDokumenData.getDataLogDokumenKPTG(idLogDokumenKPTG);
  	 			
  	        	Hashtable hA = (Hashtable)paparLogDokumen.get(0);	
  	 			context.put("idLogDokumenKPTG",hA.get("idLogDokumenKPTG"));
  	 			if(hA.get("idMinit").equals("1")){
  					    context.put("checkedc1","checked");
  				    }
  				    else
  				    {
  				    	context.put("checkedc1","");
  				    }
  				    if(hA.get("idLaporan").equals("1")){
  				    	context.put("checkedc2","checked");
  				    }
  				    else
  				    {
  				    	context.put("checkedc2","");
  				    }
  				    
  	 			    if(hA.get("idCD").equals("1")){
  	 			    	context.put("checkedc3","checked");
  	 			    }
  	 			    else
  	 			    {
  	 			    	context.put("checkedc3","");
  	 			    }
  	 			context.put("jenis_Dokumen",hA.get("jenis_Dokumen"));
  	 			context.put("no_Rujukan_Lain",hA.get("no_Rujukan_Lain"));
  	 			context.put("pengirim_Dokumen",hA.get("pengirim_Dokumen"));
  	 			context.put("tajuk_Dokumen",hA.get("tajuk_Dokumen"));
  	 			context.put("penerima_Dokumen",hA.get("penerima_Dokumen"));
  	 			context.put("tarikh_Dokumen",hA.get("tarikh_Dokumen"));
  	 			context.put("tarikh_Dokumen_Diterima",hA.get("tarikh_Dokumen_Diterima"));
  	 			context.put("catatan",hA.get("catatan"));
  	 			
  	 			
  	    		paparPegawaiSeksyenLain = FrmLogDokumenData.getDataPegawaiSeksyenKPTG(idLogDokumenKPTG);
  	    		Hashtable h = (Hashtable)paparPegawaiSeksyenLain.get(0);	
  				context.put("nama_Pegawai",h.get("user_name"));
  				
  	         		
  	 			paparLampiranLogDokumenKeluarPapar = FrmLogDokumenData.getListLampiranLogDokumenKeluarPapar(idLogDokumenKPTG);
  	    		this.context.put("SenaraiListLampiranLogDokumenKeluarPapar",paparLampiranLogDokumenKeluarPapar);
  	    		
  	    		listPegawai = FrmLogDokumenData.getDataPegawai(idLogDokumenKPTG);
  	 			this.context.put("SenaraiPegawai",listPegawai);
  	 			
  	 			listPTFail = FrmLogDokumenData.getDataPTFail(idLogDokumenKPTG);
  	 			this.context.put("SenaraiPTFail",listPTFail);
  			}

          }
         
         else if("cetakLogDokumen".equals(action1)){
        	 vm = "app/pfd/frmCetakLogDokumen.jsp";
        	 context.put("action1", "cetakLogDokumen");
         }
         
  		else if ("tambahLampiran".equals(action1)){
  				
  				vm = "app/pfd/frmPaparLampiranLogDokumen.jsp";
  					
  				context.put("idLogDokumen", idLogDokumen);
  
  				paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
    			
    			Hashtable h = (Hashtable)paparLogDokumen.get(0);	
    			context.put("idLogDokumen",h.get("idLogDokumen"));
    			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
    			if(h.get("jenis_Dokumen").equals("1")){
    				
    				context.put("jenis_Dokumen","SURAT");

    			}
    			if (h.get("jenis_Dokumen").equals("2")){
    				context.put("jenis_Dokumen","MEMO");

    			}
    			if(h.get("jenis_Dokumen").equals("3")){
    				context.put("jenis_Dokumen","LAPORAN");

    			}
    			if(h.get("jenis_Dokumen").equals("4")){
    				context.put("jenis_Dokumen","MINIT MESYUARAT");

    			}
    			if(h.get("jenis_Dokumen").equals("5")){
    				context.put("jenis_Dokumen","FAIL SUBJAKET");

    			}
    			context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
    			
   	    	 listLampiran(session);
   	    	 listLampiran = FrmLogDokumenData.getListLampiran();
        	 context.put("SenaraiLampiran",listLampiran);
        	 context.put("completed",false);
  		}
         
  		else if ("simpanLampiran".equals(action1)){
 	 		context.put("idLogDokumen", idLogDokumen);
 	 		
			uploadLampiran(idLogDokumen);

			vm = "app/pfd/frmPaparLampiranLogDokumen.jsp";

  
  			paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
    			
    			Hashtable h = (Hashtable)paparLogDokumen.get(0);	
    			context.put("idLogDokumen",h.get("idLogDokumen"));
    			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
    			if(h.get("jenis_Dokumen").equals("1")){
    				
    				context.put("jenis_Dokumen","SURAT");

    			}
    			if (h.get("jenis_Dokumen").equals("2")){
    				context.put("jenis_Dokumen","MEMO");

    			}
    			if(h.get("jenis_Dokumen").equals("3")){
    				context.put("jenis_Dokumen","LAPORAN");

    			}
    			if(h.get("jenis_Dokumen").equals("4")){
    				context.put("jenis_Dokumen","MINIT MESYUARAT");

    			}
    			if(h.get("jenis_Dokumen").equals("5")){
    				context.put("jenis_Dokumen","FAIL SUBJAKET");

    			}
    			context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
    			
   	    	 listLampiran(session);
   	    	 listLampiran = FrmLogDokumenData.getListLampiran();
        	 context.put("SenaraiLampiran",listLampiran);
        	 context.put("completed",false);
   		
		} 
			
		else if ("hapusLampiran".equals(action1)){
			
 	 		
 	 		context.put("idLampiran", idLampiran);
			hapusLampiran(session);
			
			vm = "app/pfd/frmPaparLampiranLogDokumen.jsp";

			  
  			paparLogDokumen = FrmLogDokumenData.getDataLogDokumen(idLogDokumen);
    			
    			Hashtable h = (Hashtable)paparLogDokumen.get(0);	
    			context.put("idLogDokumen",h.get("idLogDokumen"));
    			context.put("jenis_Dokumen",h.get("jenis_Dokumen"));
    			if(h.get("jenis_Dokumen").equals("1")){
    				
    				context.put("jenis_Dokumen","SURAT");

    			}
    			if (h.get("jenis_Dokumen").equals("2")){
    				context.put("jenis_Dokumen","MEMO");

    			}
    			if(h.get("jenis_Dokumen").equals("3")){
    				context.put("jenis_Dokumen","LAPORAN");

    			}
    			if(h.get("jenis_Dokumen").equals("4")){
    				context.put("jenis_Dokumen","MINIT MESYUARAT");

    			}
    			if(h.get("jenis_Dokumen").equals("5")){
    				context.put("jenis_Dokumen","FAIL SUBJAKET");

    			}
    			context.put("no_Rujukan_Lain",h.get("no_Rujukan_Lain"));
    			
   	    	 listLampiran(session);
   	    	 listLampiran = FrmLogDokumenData.getListLampiran();
        	 context.put("SenaraiLampiran",listLampiran);
        	 context.put("completed",false);
		}
         
  		else if ("tambahLampiranLogDokumen".equals(action1)){
				
			vm = "app/pfd/frmTambahLampiranLogDokumen.jsp";
			
		}
         
  		else if ("simpanTambahLampiranLogDokumen".equals(action1)){
 	 		context.put("idLogDokumen", idLogDokumen);
 	 		
			uploadLampiran(idLogDokumen);

			vm = "app/pfd/frmTambahLampiranLogDokumen.jsp";
    			
   	    	 listLampiran(session);
   	    	 listLampiran = FrmLogDokumenData.getListLampiran();
        	 context.put("SenaraiLampiran",listLampiran);
        	 context.put("completed",true);
  		} 
			
        else {
        	vm = "app/pfd/frmCariDokumen.jsp";
        	context.put("pagemode",0); 
        	

        	String user_name = (String)session.getAttribute("_portal_username");
        	String user_id = (String)session.getAttribute("_ekptg_user_id");
        	String portal_role = (String)session.getAttribute("_portal_role");
        	String myrole = (String)session.getAttribute("myrole");

        	context.put("user_Name", user_name);
        	context.put("user_Id", user_id);
        	
        	this.context.put("JumlahDokumen",FrmDaftarLogDokumenData.totalDokumen(session));
        	
			if (!"".equals(getParam("no_Rujukan_Lain")))
				no_Rujukan_Lain = getParam("no_Rujukan_Lain");
			if (!"".equals(getParam("tajuk_Dokumen")))
				tajuk_Dokumen = getParam("tajuk_Dokumen");
			if (!"".equals(getParam("tarikh_Dokumen")))
				tarikh_Dokumen = getParam("tarikh_Dokumen");
			if (!"".equals(getParam("tarikh_Dokumen_Diterima")))
				tarikh_Dokumen_Diterima = getParam("tarikh_Dokumen_Diterima");
			if (!"".equals(getParam("tarikh_Dokumen_Dihantar")))
				tarikh_Dokumen_Dihantar = getParam("tarikh_Dokumen_Dihantar");
			if (!"".equals(getParam("tag_dokumen")))
				tag_Dokumen = getParam("tag_dokumen");
			
			FrmDaftarLogDokumenData.setCarianLogDokumen(no_Rujukan_Lain,tajuk_Dokumen,tarikh_Dokumen,tarikh_Dokumen_Diterima,tarikh_Dokumen_Dihantar,status_logdokumen, flag_logdokumen, user_id, tag_Dokumen);
			listLogDokumen = FrmDaftarLogDokumenData.getListLogDokumen();
	    	this.context.put("SenaraiFail", listLogDokumen);
	    	prepareItemForDisplay(session,listLogDokumen,10,"first");
	    	this.context.put("myrole", myrole);
	    	this.context.put("no_Rujukan_Lain", no_Rujukan_Lain);
	    	this.context.put("tajuk_Dokumen", tajuk_Dokumen);
	    	this.context.put("tag_Dokumen", tag_Dokumen);
	    	this.context.put("tarikh_Dokumen", tarikh_Dokumen);
	    	this.context.put("tarikh_Dokumen_Diterima", tarikh_Dokumen_Diterima);
	    	this.context.put("tarikh_Dokumen_Dihantar", tarikh_Dokumen_Dihantar);
	    //	this.context.put("status_logdokumen", status_logdokumen);
	    	
	    	
	    	setupPage(session,action1,listLogDokumen);

         }
         
		return vm;
    }


	private void hapusLogDokumenKeluar(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idLogDokumen = getParam("idLogDokumen");

			Hashtable h = new Hashtable();
			h.put("idLogDokumen",idLogDokumen);
			h.put("id_Kemaskini",user_id);

		    FrmLogDokumenData.deleteLogDokumenKeluar(h);
		
		
		
	}


	private void hapusLampiran(HttpSession session) throws Exception {
		String idLampiran = getParam("idLampiran");
		FrmLogDokumenData.hapus(idLampiran);
	}


	private void uploadLampiran(String idLogDokumen) throws Exception {
		uploadFiles(idLogDokumen);
	}
	
	 private void uploadFiles(String idLogDokumen) throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData1(item, idLogDokumen);
		      }
		    }
		  }


	private void listLampiran(HttpSession session) throws Exception {
		String id = getParam("idLogDokumen");
		FrmLogDokumenData.setListLampiran(id);
		
	}


	private String getEmail(String idPTFail) throws Exception {
		return FrmLogDokumenData.getEmailPTFail(idPTFail);
	}


	private String checkPTFail(String idLogDokumen) throws Exception {
	
	    return FrmLogDokumenData.checkPTFail(idLogDokumen);
	}


	private void hapusLogDokumenMasuk(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idLogDokumen = getParam("idLogDokumen");

			Hashtable h = new Hashtable();
			h.put("idLogDokumen",idLogDokumen);
			h.put("id_Kemaskini",user_id);

		    FrmLogDokumenData.deleteLogDokumenMasuk(h);
		
		
	}


	private void updateLogDokumenSeksyenLain(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idLogDokumen = getParam("idLogDokumen");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
		String idSeksyen = getParam("idSeksyen");
		String idDokumen = getParam("idDokumen");
		String idNegeri = getParam("idNegeri");
		String idPenerima = getParam("socPegawai");
		String idPTfail = getParam("socPT");
			Hashtable h = new Hashtable();
			h.put("idLogDokumen",idLogDokumen);
			h.put("idLogDokumenKPTG",idLogDokumenKPTG);
		    h.put("tarikh_Dokumen_Diterima", getParam("tarikh_Dokumen_Diterima"));
			h.put("id_nama_penerima", getParam("socPegawai"));
			h.put("id_ptfail",idPTfail);
			h.put("idSeksyen",idSeksyen);
			h.put("idNegeri",idNegeri);
			h.put("idDokumen",idDokumen);
		    h.put("id_Kemaskini",user_id);
		    h.put("status_logdokumen","1");
		    
		    if(!idLogDokumenKPTG.equals(""))
		    {
		    	FrmLogDokumenData.updateLogDokumenSeksyenKPTG(h);
		    }
		    else
		    {
		    	FrmLogDokumenData.updateLogDokumenSeksyenLain(h);
		    }
	}
	
	private void updateLogDokumenSeksyenKPTG(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idLogDokumenKPTG = getParam("idLogDokumenKPTG");
		String idSeksyen = getParam("idSeksyen");
		String idDokumen = getParam("idDokumen");
		String idNegeri = getParam("idNegeri");
		String idPenerima = getParam("socPegawai");
		String idPTfail = getParam("socPT");
			Hashtable h = new Hashtable();
			h.put("idLogDokumenKPTG",idLogDokumenKPTG);
		    h.put("tarikh_Dokumen_Diterima", getParam("tarikh_Dokumen_Diterima"));
			h.put("id_nama_penerima", getParam("socPegawai"));
			h.put("id_ptfail",idPTfail);
			h.put("idSeksyen",idSeksyen);
			h.put("idNegeri",idNegeri);
			h.put("idDokumen",idDokumen);
		    h.put("id_Kemaskini",user_id);
		    h.put("status_logdokumen","1");
		    FrmLogDokumenData.updateLogDokumenSeksyenKPTG(h);
	}


	private void updateLogDokumenKeluar(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idLogDokumen = getParam("idLogDokumen");
		String idSeksyen = getParam("idSeksyen");
		String idDokumen = getParam("idDokumen");
		String idNegeri = getParam("idNegeri");
		String minit = getParam("c1");
		String laporan = getParam("c2");
		String cd = getParam("c3");
		String cara_Penghantaran = getParam("socJenisPenghantaran");
			
			Hashtable h = new Hashtable();
			h.put("idLogDokumen",idLogDokumen);
		    h.put("tarikh_Dokumen_Dihantar", getParam("tarikh_Dokumen_Dihantar"));
		    h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
		    h.put("id_JenisDokumen", getParam("socJenisDokumen"));
			h.put("id_PenghantarKeluar", getParam("socPegawai"));
			h.put("tajuk_Dokumen", getParam("tajuk_Dokumen"));
			h.put("no_Rujukan", getParam("no_Rujukan_Lain"));
			h.put("penerima_Dokumen", getParam("penerima_Dokumen"));
			h.put("cara_Penghantaran",cara_Penghantaran);
			h.put("idSeksyen",idSeksyen);
			h.put("idNegeri",idNegeri);
			//h.put("idDokumen",idDokumen);
		    h.put("id_Kemaskini",user_id);
		    h.put("status_logdokumen","0");
		    h.put("tag_dokumen", getParam("tag_dokumen"));
	 	    h.put("id_tagdokumen", getParam("id_tagdokumen"));
			if ("1".equalsIgnoreCase(minit)){
				h.put("idMinit", "1");
			}
			else{
				h.put("idMinit", "0");
			}
			if ("1".equalsIgnoreCase(laporan)){
				h.put("idLaporan", "1");
			}
			else{
				h.put("idLaporan", "0");
			}
			if ("1".equalsIgnoreCase(cd)){
				h.put("idCD", "1");
			}
			else{
				h.put("idCD", "0");
			}
		    FrmLogDokumenData.updateLogDokumenKeluar(h);
		
	}


	private void listLampiranLogDokumen(HttpSession session) throws Exception {
		String idLogDokumen = getParam("idLogDokumen");
		FrmLogDokumenData.setLampiranLogDokumen(idLogDokumen);
		
	}


	private void downloadDraf(String idLogDokumen) throws Exception {
		System.out.println("=======Download file==========" +idLogDokumen);
		uploadFiles1(idLogDokumen);
		
	}


	private void uploadFiles1(String idLogDokumen) throws Exception {
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);

		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      myLogger.info("ContentType :" + item.getContentType());
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData1(item, idLogDokumen);
		      }
		    }
		
	}
	
	 private void saveData1(FileItem item, String idLogDokumen) throws Exception {
			Db db = null;
		    HttpSession session = this.request.getSession();
		    Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String currentDate = "to_date('" +  sdf.format(now) + "','dd/MM/yyyy')";
		  		String id = idLogDokumen;
				String user_id = (String)session.getAttribute("_ekptg_user_id");
		        try {
		        	db = new Db();
		        	long id_Lampiran = DB.getNextID("TBLPFDRUJLAMPIRANLOGDOK_SEQ");
		        	Connection con = db.getConnection();
		        	con.setAutoCommit(false);
		        	PreparedStatement ps = con.prepareStatement("insert into TBLPFDRUJLAMPIRANLOGDOK " +
		        			"(id_Lampiran,id_LogDokumen,nama_Fail,jenis_Mime,content) " +
		        			"values(?,?,?,?,?)");
		        	ps.setLong(1, id_Lampiran);
		        	ps.setString(2, id);
		        	ps.setString(3,item.getName());
		        	ps.setString(4,item.getContentType());
		        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
		        	ps.executeUpdate();
		            con.commit();
			    } catch(Exception e){
			    	myLogger.error(e.getMessage());
			    	throw new Exception("simpan dokumen :"+e.getMessage());
			    	
			    }finally {
			    
				      if (db != null) db.close();
			    }
	  }


	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setSeksyen(user_id);
		
	}


	private void updateLogDokumenMasuk(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idLogDokumen = getParam("idLogDokumen");

		String idFail = getParam("idFail");
		String minit = getParam("c1");
		String laporan = getParam("c2");
		String cd = getParam("c3");
		String PTfail = getParam("socPT");
		String idNegeri = getParam("idNegeri");
		String idSeksyen = getParam("idSeksyen");
		
		String no_Rujukan_Lain = getParam("no_Rujukan_Lain");
		//String no_Rujukan_LainEncode = java.net.URLDecoder.decode(no_Rujukan_Lain);
		String pengirim_Dokumen = getParam("pengirim_Dokumen");
		//String pengirim_DokumenEncode = java.net.URLDecoder.decode(pengirim_Dokumen);
		String tajuk_Dokumen = getParam("tajuk_Dokumen");
		//String tajuk_DokumenEncode = java.net.URLDecoder.decode(tajuk_Dokumen);

			Hashtable h = new Hashtable();
			h.put("idLogDokumen", idLogDokumen);
			h.put("idLaporan", laporan);
			h.put("idMinit", minit);
			h.put("idCD", cd);
			h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
	 	    h.put("no_Rujukan_Lain", getParam("no_Rujukan_Lain"));
		    h.put("tajuk_Dokumen", getParam("tajuk_Dokumen"));
			h.put("pengirim_Dokumen", getParam("pengirim_Dokumen"));
			h.put("id_PenerimaDokumen", getParam("socPegawai"));
			//h.put("id_PengirimDokumen", getParam("socPegawai"));
			//h.put("penerima_Dokumen", getParam("penerima_Dokumen"));
			h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
			h.put("tarikh_Dokumen_Diterima", getParam("tarikh_Dokumen_Diterima"));
			h.put("id_PTFail",PTfail);
			h.put("idNegeri",idNegeri);
			h.put("idSeksyen",idSeksyen);
			h.put("status_LogDokumen","1");
	 	    h.put("flag_LogDokumen","1");
	 	    h.put("id_Kemaskini",user_id);
	 	    h.put("tag_dokumen", getParam("tag_dokumen"));
	 	    h.put("id_tagdokumen", getParam("id_tagdokumen"));
//	 	    if ("true".equalsIgnoreCase(minit)){
//				h.put("idMinit", "1");
//			}
//			else{
//				h.put("idMinit", "0");
//			}
//			if ("true".equalsIgnoreCase(laporan)){
//				h.put("idLaporan", "1");
//			}
//			else{
//				h.put("idLaporan", "0");
//			}
//			if ("true".equalsIgnoreCase(cd)){
//				h.put("idCD", "1");
//			}
//			else{
//				h.put("idCD", "0");
//			}
//			Hashtable h = new Hashtable();
//			h.put("idLogDokumen", idLogDokumen);
//			h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
//	 	    h.put("no_Rujukan_Lain", getParam("no_Rujukan_Lain"));
//		    h.put("tajuk_Dokumen", getParam("tajuk_Dokumen"));
//			h.put("pengirim_Dokumen", getParam("pengirim_Dokumen"));
//			h.put("id_PenerimaDokumen", getParam("socPegawai"));
//			//h.put("id_PengirimDokumen", getParam("socPegawai"));
//			//h.put("penerima_Dokumen", getParam("penerima_Dokumen"));
//			h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
//			h.put("tarikh_Dokumen_Diterima", getParam("tarikh_Dokumen_Diterima"));
//			h.put("id_PTFail", getParam("socPT"));
//	 	    h.put("flag_LogDokumen","1");
//	 	    h.put("id_Masuk",user_id);
	 	    
	 	    FrmLogDokumenData.updateMasuk(h);
	}


	private String simpanLogDokumenMasuk(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idFail = getParam("idFail");
		String minit = getParam("c1");
		String laporan = getParam("c2");
		String cd = getParam("c3");
		String PTfail = getParam("socPT");
		String idNegeri = getParam("idNegeri");
		String idSeksyen = getParam("idSeksyen");
		
		String no_Rujukan_Lain = getParam("no_Rujukan_Lain");
		//String no_Rujukan_LainEncode = java.net.URLDecoder.decode(no_Rujukan_Lain);
		String pengirim_Dokumen = getParam("pengirim_Dokumen");
		//String pengirim_DokumenEncode = java.net.URLDecoder.decode(pengirim_Dokumen);
		String tajuk_Dokumen = getParam("tajuk_Dokumen");
		//String tajuk_DokumenEncode = java.net.URLDecoder.decode(tajuk_Dokumen);

			Hashtable h = new Hashtable();
			h.put("id_Jenisdokumen", getParam("socJenisDokumen"));
	 	    h.put("no_Rujukan_Lain", getParam("no_Rujukan_Lain"));
		    h.put("tajuk_Dokumen", getParam("tajuk_Dokumen"));
			h.put("pengirim_Dokumen", getParam("pengirim_Dokumen"));
			h.put("id_PenerimaDokumen", getParam("socPegawai"));
			//h.put("id_PengirimDokumen", getParam("socPegawai"));
			//h.put("penerima_Dokumen", getParam("penerima_Dokumen"));
			h.put("tarikh_Dokumen", getParam("tarikh_Dokumen"));
			h.put("tarikh_Dokumen_Diterima", getParam("tarikh_Dokumen_Diterima"));
			h.put("id_PTFail",PTfail);
			h.put("idNegeri",idNegeri);
			h.put("idSeksyen",idSeksyen);
			h.put("status_LogDokumen","1");
	 	    h.put("flag_LogDokumen","1");
	 	    h.put("id_Masuk",user_id);
	 	    h.put("tag_dokumen", getParam("tag_dokumen"));
	 	   
	 	    if ("true".equalsIgnoreCase(minit)){
				h.put("idMinit", "1");
			}
			else{
				h.put("idMinit", "0");
			}
			if ("true".equalsIgnoreCase(laporan)){
				h.put("idLaporan", "1");
			}
			else{
				h.put("idLaporan", "0");
			}
			if ("true".equalsIgnoreCase(cd)){
				h.put("idCD", "1");
			}
			else{
				h.put("idCD", "0");
			}
	 	    
	 	    return FrmLogDokumenData.addMasuk(h);
	}
	
	private String simpanLogDokumenKeluar(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idNegeri = getParam("idNegeri");
		String idSeksyen = getParam("idSeksyen");
		
		
			Hashtable h = new Hashtable();
			h.put("idDokumen", getParam("idDokumen"));
			h.put("idLogDokumen", getParam("idLogDokumen"));
			h.put("idNegeri",idNegeri);
			h.put("idSeksyen",idSeksyen);		
			h.put("jenis_Penghantaran", getParam("socJenisPenghantaran"));
			h.put("tarikh_Dokumen_Dihantar", getParam("tarikh_Dokumen_Dihantar"));
			h.put("status_logdokumen","0");
	 	    h.put("flag_LogDokumen","2");
	 	    h.put("id_Masuk",user_id);
	 	    h.put("tag_dokumen", getParam("tag_dokumen"));
	 	    
	 	    return FrmLogDokumenData.addKeluar(h);
	}
	
	private String simpanLogDokumenDalaman(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idNegeri = getParam("idNegeri");
		String idSeksyen = getParam("idSeksyen");
		
		
			Hashtable h = new Hashtable();
			h.put("idDokumen", getParam("idDokumen"));
			h.put("idLogDokumen", getParam("idLogDokumen"));
			h.put("idNegeri",idNegeri);
			h.put("idSeksyen",idSeksyen);		
			h.put("jenis_Penghantaran", getParam("socJenisPenghantaran"));
			h.put("tarikh_Dokumen_Dihantar", getParam("tarikh_Dokumen_Dihantar"));
			h.put("status_logdokumen","0");
	 	    h.put("flag_LogDokumen","2");
	 	    h.put("id_Masuk",user_id);
	 	    h.put("tag_dokumen", getParam("tag_dokumen"));
	 	    
	 	    return FrmLogDokumenData.addDalaman(h);
	}

	private void listPegawai(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setListPegawai(user_id,(String) session.getAttribute("_ekptg_user_negeri"));
		
	}

	private void listPTFail(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setListPTFail(user_id);
			
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
		this.context.put("SenaraiFail",paging.getPage(page));
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
	
	 private void prepareItemForDisplay(HttpSession session, Vector objVector, int cntItemPage, String command)
	  {
	    int x;
	    int startno = 0;
	    if (command == null) command = "first";
	    if (session.getAttribute("_portal_startno") != null) startno = ((Integer)session.getAttribute("_portal_startno")).intValue();
	    if (command.equals("previous"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x > 0) {
	          startno -= x;
	          startno -= cntItemPage;
	        } else {
	          startno -= cntItemPage * 2;
	          if (startno < 0) startno = 0;
	        }
	      } else {
	        startno -= cntItemPage * 2;
	        if (startno < 0) startno = 0;
	      }
	    else if (command.equals("first"))
	      startno = 0;
	    	
	    else if (command.equals("last"))
	      x = cntItemPage;
	    else if (command.equals("back"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x == 0) x = cntItemPage;
	        startno -= x;
	      } else {
	        startno -= cntItemPage;
	        if (startno < 0) startno = 0;

	      }
	    
	    Vector moduleVector = new Vector();
	    int i = 0; int cnt = 0;
	    if (objVector.size() > 0) {
	      cnt = 0; for (i = startno; (cnt < cntItemPage) && (i < objVector.size()); ) {
	        moduleVector.addElement(objVector.elementAt(i));

	        ++i; ++cnt;
	      }

	    }

	    session.setAttribute("_portal_moduleVector", moduleVector);
	   
	    this.context.put("startnoInt", new Integer(startno));
	    this.context.put("totalInt", new Integer(objVector.size()));
	    
	   
	    startno = i;
	   
	    session.setAttribute("_portal_startno", new Integer(startno));
	    
	  }

}

