package ekptg.view.pfd;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmDaftarFailData;
import ekptg.model.pfd.FrmDaftarTukarTarafKeselamatanData;
import ekptg.model.pfd.FrmKemaskiniTukarTarafKeselamatanData;
import ekptg.model.pfd.FrmLogDokumenData;
import ekptg.model.pfd.FrmPendaftaranFailData;

public class TukarTarafKeselamatan extends AjaxBasedModule {
	
	public String doTemplate2() throws Exception{
		 String vm = "";
		 String disability1 = "";
		 String disability2 = "";
		 String noFail = "";
		 String noFailLama = "";
         String tajukFail = "";
         String negeri = getParam("socNegeriD");
         String seksyen = getParam("socSeksyenD");
         String status = getParam("socStatusD");
         String taraf = getParam("socTarafD");
         String tarikhDaftar = "";
         String action1 = getParam("action1");
         String action = getParam("action");
         Vector paparSemuaFail = null;
         Vector paparFail = null;
         Vector paparDetailFailAsal = null;
         Vector paparTaraf = null;
         Vector paparStatus = null;	
         Vector paparLokasi = null;
         Vector paparUnit = null;
			
			if ("doChangeItemPerPage".equals(action) ||
					"getPage".equals(action)) {
				action1 = action;
			}
         String command = getParam("command");
         String mode = getParam("mode");
         HttpSession session = this.request.getSession();
         Vector list = new Vector();
         Vector paparSeksyen = null;
         
         if ("tambah".equals(action1)) {
      	 
        		String idFailAsal = getParam("idFail");
        		this.context.put("tukar","notsuccess");
        		
        		paparDetailFailAsal = FrmKemaskiniTukarTarafKeselamatanData.getPaparDetailFailAsal(idFailAsal);
             	Hashtable f = (Hashtable)paparDetailFailAsal.get(0);
        	   	this.context.put("idSeksyen", f.get("idSeksyen"));
        		this.context.put("idNegeri", f.get("idNegeri"));
        		this.context.put("idDaerah", f.get("idDaerah"));
        		this.context.put("idUrusan", f.get("idUrusan"));
        		this.context.put("idSuburusan", f.get("idSuburusan"));
        		this.context.put("idSubsuburusan", f.get("idSubsuburusan"));
        		this.context.put("idSubsubsuburusan", f.get("idSubsubsuburusan"));
        		this.context.put("idTaraf", f.get("idTaraf"));
        		this.context.put("idAktiviti", f.get("idAktiviti"));
        		this.context.put("noTurutan", f.get("noTurutan"));
        		
       	    	
           		
        		paparSemuaFail = FrmKemaskiniTukarTarafKeselamatanData.getPaparSemuaFail(idFailAsal);
           		Hashtable h = (Hashtable)paparSemuaFail.get(0);
           		    			
           		    			
           		if(h.get("idNegeri").equals("16"))
           		{
           		    				
           		    context.put("mode", "baru");
           		    context.put("readonly","readonly");
           		    context.put("disabled","disabled");
           		    				
           		    vm = "app/pfd/frmKemaskiniTukarTarafKeselamatanSeksyen.jsp";
           		            		
           		        			
           		    this.context.put("idFailAsal", h.get("idFailAsal"));
           		    this.context.put("idNegeri", h.get("idNegeri"));
           		    this.context.put("idSeksyen", h.get("idSeksyen"));
           		    this.context.put("noFailAsal", h.get("noFailAsal"));
           		    this.context.put("tajukFailB", h.get("tajukFail"));
           		    this.context.put("kabinetB", h.get("kabinet"));
           		    this.context.put("seksyen", h.get("KOD_SEKSYEN")+"-"+h.get("NAMA_SEKSYEN"));
           		    this.context.put("namaUrusan", h.get("KOD_URUSAN")+"-"+h.get("NAMA_URUSAN"));
	         	 	this.context.put("namaSuburusan", h.get("KOD_SUBURUSAN")+"-"+ h.get("NAMA_SUBURUSAN"));
	         	 	this.context.put("namaSubSuburusan",h.get("KOD_SUBSUBURUSAN")+"-"+ h.get("NAMA_SUBSUBURUSAN"));
	         	 	this.context.put("namaSubSubSuburusan", h.get("KOD_SUBSUBSUBURUSAN")+"-"+h.get("NAMA_SUBSUBSUBURUSAN")); 
	         	 	this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(h.get("idTaraf").toString()),""));
           		    this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(h.get("idStatus").toString()),"disabled"));
           		    this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(h.get("idLokasi").toString()),"disabled"));
           		    this.context.put("tarikhDaftarB", h.get("tarikhDaftar"));
           		    
	           		FrmPendaftaranFailData.getTaraf(h.get("idTaraf").toString());
	 	         	paparTaraf = FrmPendaftaranFailData.getListTaraf();
	 	         	Hashtable hC = (Hashtable) paparTaraf.get(0);
	 	         	
	 	         	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
	 	         		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
	 	         	}
	 	         	else{
	 	         		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
	 	         	}
 	         	
	 	         	FrmPendaftaranFailData.getStatusFail(h.get("idStatus").toString());
	         	 	paparStatus = FrmPendaftaranFailData.getListStatus();
	         	 	Hashtable hD = (Hashtable) paparStatus.get(0);
	         	 	
	         	 	this.context.put("status", hD.get("KETERANGAN"));
	         	 	
	         	 	if(!h.get("idLokasi").equals("")){
	         	 	
	         	 	FrmPendaftaranFailData.getLokasiFail(h.get("idLokasi").toString());
	         	 	paparLokasi = FrmPendaftaranFailData.getListLokasi();
	         	 	Hashtable hE = (Hashtable) paparStatus.get(0);
	         	 	
	         	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
	         	 	}
	         	 	else{
	         	 		this.context.put("lokasi", "");
	         	 	}
           		    				
           		}
           		    			
           		else{
           			
           		      	context.put("mode", "baru");
           		      	context.put("readonly","readonly");
           		      	context.put("disabled","disabled");
           		      	
           		      	
           		      	
           		      	view_unit(session);
           		      	paparUnit = FrmLogDokumenData.getDataUnit();
           		      	Hashtable hZ = (Hashtable)paparUnit.get(0);
     	         		String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
     	         		context.put("seksyen",h.get("NAMA_NEGERI") + "-"+ nama_pejabat);
   
           		    				
           		   		vm = "app/pfd/frmKemaskiniTukarTarafKeselamatanNegeri.jsp";
           		        			
           		   		this.context.put("idFailAsal", h.get("idFailAsal"));
               		    this.context.put("idNegeri", h.get("idNegeri"));
               		    this.context.put("idSeksyen", h.get("idSeksyen"));
               		    this.context.put("noFailAsal", h.get("noFailAsal"));
           		   		this.context.put("tajukFailB", h.get("tajukFail"));
           		   		this.context.put("kabinetB", h.get("kabinet"));
           		   		this.context.put("namaUrusan", h.get("KOD_URUSAN")+"-"+h.get("NAMA_URUSAN"));
           		   		this.context.put("namaSuburusan", h.get("KOD_SUBURUSAN")+"-"+ h.get("NAMA_SUBURUSAN"));
           		   		this.context.put("namaSubSuburusan",h.get("KOD_SUBSUBURUSAN")+"-"+ h.get("NAMA_SUBSUBURUSAN"));
           		   		this.context.put("namaSubSubSuburusan", h.get("KOD_SUBSUBSUBURUSAN")+"-"+h.get("NAMA_SUBSUBSUBURUSAN")); 
           		   		this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(h.get("idTaraf").toString()),""));
           		   	FrmPendaftaranFailData.getTaraf(h.get("idTaraf").toString());
	 	         	paparTaraf = FrmPendaftaranFailData.getListTaraf();
	 	         	Hashtable hC = (Hashtable) paparTaraf.get(0);
	 	         	
	 	         	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
	 	         		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
	 	         	}
	 	         	else{
	 	         		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
	 	         	}
 	         	
	 	         	FrmPendaftaranFailData.getStatusFail(h.get("idStatus").toString());
	         	 	paparStatus = FrmPendaftaranFailData.getListStatus();
	         	 	Hashtable hD = (Hashtable) paparStatus.get(0);
	         	 	
	         	 	this.context.put("status", hD.get("KETERANGAN"));
	         	 	
	         	 	if(!h.get("idLokasi").equals("")){
	         	 	
	         	 	FrmPendaftaranFailData.getLokasiFail(h.get("idLokasi").toString());
	         	 	paparLokasi = FrmPendaftaranFailData.getListLokasi();
	         	 	Hashtable hE = (Hashtable) paparStatus.get(0);
	         	 	
	         	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
	         	 	}
	         	 	else{
	         	 		this.context.put("lokasi", "");
	         	 	}
	         	 	
            		   	this.context.put("tarikhDaftarB", h.get("tarikhDaftar"));
                   			
           		    }
        	        	 
         }
         else if ("simpan".equals(action1)){
               	 
        	String idFailAsal = getParam("idFailAsal");
         	this.context.put("tukar","success");
	
         	String idFailBaru = simpanFailTukarTaraf(session);
         	idFailAsal = updateTaraf(session);
         	
         	
         	paparFail = FrmKemaskiniTukarTarafKeselamatanData.getPaparFail(idFailBaru);
         	Hashtable g = (Hashtable)paparFail.get(0);
    	   	this.context.put("idFailBaru", g.get("idFailBaru"));
        	this.context.put("noFailBaru", g.get("noFailBaru"));
         	
         	paparSemuaFail = FrmKemaskiniTukarTarafKeselamatanData.getPaparSemuaFail(idFailAsal);
        	Hashtable h = (Hashtable)paparSemuaFail.get(0);
        		
 		   	this.context.put("idFailBaru", h.get("idFailBaru"));
        	this.context.put("noFailBaru", h.get("noFailBaru"));
         	


         	if(h.get("idNegeri").equals("16"))
       		{
       		    				
       		    context.put("mode", "papar");
       		    context.put("readonly","readonly");
       		    context.put("disabled","disabled");
              	context.put("readonlyNoFail","readonly");
       		    context.put("disabledNoFail","disabled");
              	context.put("readonlyNoFailRoot","readonly");
       		    context.put("disabledNoFailRoot","disabled");
       		    context.put("readonlyTajukFail","readonly");
       		    context.put("disabledTajukFail","disabled");
				context.put("readonlyNoTurutan","readonly");
				context.put("disabledNoTurutan","disabled");
				context.put("readonlyNoJilid","readonly");
				context.put("disabledNoJilid","disabled");
				context.put("readonlyNoSubjaket","readonly");
				context.put("disabledNoSubjaket","disabled");
       		    context.put("readonlyK","readonly");
       		    context.put("disabledK","disabled");
       		    				
       		    vm = "app/pfd/frmKemaskiniTukarTarafKeselamatanSeksyen.jsp";
       		            		
       		        			
       		    this.context.put("idFailAsal", h.get("idFailAsal"));
       		    this.context.put("idNegeri", h.get("idNegeri"));
       		    this.context.put("idSeksyen", h.get("idSeksyen"));
       		    this.context.put("noFailAsal", h.get("noFailAsal"));
       		    this.context.put("noFailBaru", g.get("noFailBaru"));
       		    this.context.put("tajukFailB", h.get("tajukFail"));
       		    this.context.put("kabinetB", h.get("kabinet"));
       		    //this.context.put("selectNegeriA",HTML.SelectNegeri("socNegeriA",Utils.parseLong(h.get("idNegeri").toString()),"disabled"));
       		    this.context.put("seksyen", h.get("KOD_SEKSYEN")+"-"+h.get("NAMA_SEKSYEN"));
    		    this.context.put("namaUrusan", h.get("KOD_URUSAN")+"-"+h.get("NAMA_URUSAN"));
    		    this.context.put("namaSuburusan", h.get("KOD_SUBURUSAN")+"-"+ h.get("NAMA_SUBURUSAN"));
    		    this.context.put("namaSubSuburusan",h.get("KOD_SUBSUBURUSAN")+"-"+ h.get("NAMA_SUBSUBURUSAN"));
    		    this.context.put("namaSubSubSuburusan", h.get("KOD_SUBSUBSUBURUSAN")+"-"+h.get("NAMA_SUBSUBSUBURUSAN")); 
    		    FrmPendaftaranFailData.getTaraf(h.get("idTaraf").toString());
 	         	paparTaraf = FrmPendaftaranFailData.getListTaraf();
 	         	Hashtable hC = (Hashtable) paparTaraf.get(0);
 	         	
 	         	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
 	         		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
 	         	}
 	         	else{
 	         		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
 	         	}
	         	
 	         	FrmPendaftaranFailData.getStatusFail(h.get("idStatus").toString());
         	 	paparStatus = FrmPendaftaranFailData.getListStatus();
         	 	Hashtable hD = (Hashtable) paparStatus.get(0);
         	 	
         	 	this.context.put("status", hD.get("KETERANGAN"));
         	 	
         	 	if(!h.get("idLokasi").equals("")){
         	 	
         	 	FrmPendaftaranFailData.getLokasiFail(h.get("idLokasi").toString());
         	 	paparLokasi = FrmPendaftaranFailData.getListLokasi();
         	 	Hashtable hE = (Hashtable) paparStatus.get(0);
         	 	
         	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
         	 	}
         	 	else{
         	 		this.context.put("lokasi", "");
         	 	}
         	 	
         	 	this.context.put("tarikhDaftarB", h.get("tarikhDaftar"));
       		    				
       		}
       		    			
       		else{
       			
       		      	context.put("mode", "papar");
       		      	context.put("readonly","readonly");
       		      	context.put("disabled","disabled");
       		      	context.put("readonlyNoFail","readonly");
       		      	context.put("readonlyTajukFail","readonly");
       		      	context.put("disabledNoFail","disabled");
       		      	context.put("disabledTajukFail","disabled");
    				context.put("readonlyNoTurutan","readonly");
    				context.put("disabledNoTurutan","disabled");
    				context.put("readonlyNoJilid","readonly");
    				context.put("disabledNoJilid","disabled");
    				context.put("readonlyNoSubjaket","readonly");
    				context.put("disabledNoSubjaket","disabled");
       		      	context.put("readonlyK","readonly");
    			  	context.put("disabledK","disabled");
       		    				
       		   		vm = "app/pfd/frmKemaskiniTukarTarafKeselamatanNegeri.jsp";
       		   		
       		   		view_unit(session);
       		   		paparUnit = FrmLogDokumenData.getDataUnit();
       		   		Hashtable hZ = (Hashtable)paparUnit.get(0);
	         		String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
	         		context.put("seksyen",h.get("NAMA_NEGERI") + "-"+ nama_pejabat);

       		        			
       		   		this.context.put("idFailAsal", h.get("idFailAsal"));
           		    this.context.put("idNegeri", h.get("idNegeri"));
           		    this.context.put("idSeksyen", h.get("idSeksyen"));
           		    this.context.put("noFailAsal", h.get("noFailAsal"));
           		    this.context.put("noFailBaru", g.get("noFailBaru"));
       		   		this.context.put("tajukFailB", h.get("tajukFail"));
       		   		this.context.put("kabinetB", h.get("kabinet"));
       		   		this.context.put("namaUrusan", h.get("KOD_URUSAN")+"-"+h.get("NAMA_URUSAN"));
       		   		this.context.put("namaSuburusan", h.get("KOD_SUBURUSAN")+"-"+ h.get("NAMA_SUBURUSAN"));
       		   		this.context.put("namaSubSuburusan",h.get("KOD_SUBSUBURUSAN")+"-"+ h.get("NAMA_SUBSUBURUSAN"));
       		   		this.context.put("namaSubSubSuburusan", h.get("KOD_SUBSUBSUBURUSAN")+"-"+h.get("NAMA_SUBSUBSUBURUSAN")); 
       		   		this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(h.get("idTaraf").toString()),""));
       		   		
	       		   	FrmPendaftaranFailData.getTaraf(h.get("idTaraf").toString());
		         	paparTaraf = FrmPendaftaranFailData.getListTaraf();
		         	Hashtable hC = (Hashtable) paparTaraf.get(0);
		         	
		         	if (hC.get("KOD_TARAF_KESELAMATAN").equals("")){
		         		this.context.put("taraf", hC.get("TARAF_KESELAMATAN"));
		         	}
		         	else{
		         		this.context.put("taraf", hC.get("KOD_TARAF_KESELAMATAN")+"-"+hC.get("TARAF_KESELAMATAN"));
		         	}
	      	
		         	FrmPendaftaranFailData.getStatusFail(h.get("idStatus").toString());
		         	paparStatus = FrmPendaftaranFailData.getListStatus();
		         	Hashtable hD = (Hashtable) paparStatus.get(0);
	     	 	
		         	this.context.put("status", hD.get("KETERANGAN"));
	     	 	
		         	if(!h.get("idLokasi").equals("")){
	     	 	
		         		FrmPendaftaranFailData.getLokasiFail(h.get("idLokasi").toString());
	     	 		paparLokasi = FrmPendaftaranFailData.getListLokasi();
	     	 		Hashtable hE = (Hashtable) paparStatus.get(0);
	     	 		
	     	 		this.context.put("lokasi", hE.get("LOKASI_FAIL"));
		         	}
		         	else{
	     	 			this.context.put("lokasi", "");
	     	 		}
	     	 	
	         	
       		   		this.context.put("tarikhDaftarB", h.get("tarikhDaftar"));
               			
       		    }
     	 
         }

         else{
        	 
	    		String user_name = (String)session.getAttribute("_portal_username");
  	    		String user_id = (String)session.getAttribute("_ekptg_user_id");
  	    		String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
  	    		String portal_role = (String)session.getAttribute("_portal_role");
  	    		String myrole = (String)session.getAttribute("myrole");

  	    		view_Seksyen(session);
	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
	         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
	         	String id_negeri = String.valueOf(hA.get("id_negeri"));
	         	context.put("idSeksyen",(hA.get("id_seksyen")));
	         	context.put("idNegeri",(hA.get("id_negeri")));
  	    	
  	    		context.put("user_Name", user_name);
  	    		context.put("user_Id", user_id);
  	    		
  	    		this.context.put("JumlahFail",FrmDaftarFailData.totalFail(session));
        	 
        	vm = "app/pfd/frmDaftarTukarTarafKeselamatan.jsp";
        	if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
        	if (!"".equals(getParam("txtNoFailLama")))
	  	    		noFailLama = getParam("txtNoFailLama");
			if (!"".equals(getParam("txtTajukFail")))
				tajukFail = getParam("txtTajukFail");
			if (!"".equals(getParam("socNegeriD")))
				negeri = getParam("socNegeriD");
			if (!"".equals(getParam("socSeksyenD")))
				seksyen = getParam("socSeksyenD");
			if (!"".equals(getParam("socStatusD")))
				status = getParam("socStatusD");
			if (!"".equals(getParam("socTarafD")))
				taraf = getParam("socTarafD");
			if (!"".equals(getParam("txdTarikhDaftar")))
				tarikhDaftar = getParam("txdTarikhDaftar");
			
	    	FrmDaftarTukarTarafKeselamatanData.setCarianFail(noFail,noFailLama,tajukFail,negeri,seksyen,status,tarikhDaftar,taraf, user_id, myrole, user_negeri, id_seksyen);
	    	list = FrmDaftarTukarTarafKeselamatanData.getList();
	    	//session.setAttribute("Senaraifail", list);
	    	this.context.put("SenaraiFail", list);
	    	prepareItemForDisplay(session,list,10,"first");
	    	this.context.put("selectNegeriD",HTML.SelectNegeri("socNegeriD",Utils.parseLong(negeri),""));
	    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Utils.parseLong(seksyen),""));
	    	this.context.put("selectStatusD",HTML.SelectStatusFail("socStatusD",Utils.parseLong(status),""));
	    	this.context.put("selectTarafD",HTML.SelectTarafKeselamatan("socTarafD",Utils.parseLong(taraf),""));

	    	this.context.put("txtNoFail", noFail);
	    	this.context.put("txtNoFailLama", noFailLama);
	    	this.context.put("txtTajukFail", tajukFail);
	    	this.context.put("txdTarikhDaftar", tarikhDaftar);
	    	this.context.put("txdTarikhDaftar", tarikhDaftar);
	    	
	    	setupPage(session,action1,list);
         }
         
         return vm;
	
    }
	
	private String updateTaraf(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
	    String user_role = (String) session.getAttribute("_portal_role");
	    String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
		String idFailAsal = getParam("idFailAsal");

	
  		 Hashtable h = new Hashtable();
  		 h.put("idFailAsal", idFailAsal);
  		 h.put("id_Negeri", getParam("idNegeri"));
  		 h.put("id_Daerah", getParam("idDaerah"));
  		 h.put("id_Seksyen", getParam("idSeksyen"));
  		 h.put("id_Urusan", getParam("idUrusan"));
  		 h.put("id_Suburusan", getParam("idSuburusan"));
  		 h.put("id_SubSubUrusan", getParam("idSubsuburusan"));
  		 h.put("id_Subsubsuburusan", getParam("idSubsubsuburusan"));
  		 h.put("id_Aktiviti", getParam("idAktiviti"));
  		 h.put("id_Tarafkeselamatan", getParam("socTarafB"));
  		// h.put("no_Turutan", getParam("noTurutan"));
  		 h.put("id_Masuk",user_id);
 		 
  		 return FrmKemaskiniTukarTarafKeselamatanData.updateFail(h);
	}

	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setSeksyen(user_id);
		
	}

	public void setupPage(HttpSession session,String action1,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action1)) {
	    	page++;
	    } else if ("getPrevious".equals(action1)) {
	    	page--;
	    } else if ("getPage".equals(action1)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action1)) {
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
	
	private void view_fail(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("idFail"));
	    FrmKemaskiniTukarTarafKeselamatanData.setData(id);
	   
	  }
	 private String simpanFailTukarTaraf(HttpSession session) throws Exception
	  {	
		 
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idFailAsal = getParam("idFailAsal");
		
	  	Hashtable h = new Hashtable();
	  	h.put("idFailAsal",idFailAsal);
	  	h.put("id_Masuk",user_id);
	  		 
		 
		return FrmKemaskiniTukarTarafKeselamatanData.add(h);
		//FrmKemaskiniTukarTarafKeselamatanData.update(h);

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

	 private void view_unit(HttpSession session) throws Exception {
			
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			FrmLogDokumenData.setUnit(user_id);
		
	}

}
