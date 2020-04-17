package ekptg.view.pfd;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmDaftarFailData;
import ekptg.model.pfd.FrmDaftarFailJilidData;
import ekptg.model.pfd.FrmKemaskiniJilidData;
import ekptg.model.pfd.FrmLogDokumenData;
import ekptg.model.pfd.FrmPendaftaranFailData;

public class PendaftaranFailJilid extends AjaxBasedModule{
	
	public String doTemplate2() throws Exception

    {
		String vm = "";
        String submit = getParam("command");
        String action1 = getParam("action1");
        String action = getParam("action");
		
		if ("doChangeItemPerPage".equals(action) ||
				"getPage".equals(action)) {
			action1 = action;
		}
        HttpSession session = this.request.getSession();
        String disability1 = "";
        String disability2 = "";
        String readability= "";
        String noFail = "";
        String noFailLama = "";
        String tajukFail = "";
        String negeri = getParam("socNegeriD");
        String seksyen = getParam("socSeksyenD");
        String status = getParam("socStatusD");
        String kodSeksyen = "";
        String kodBaruSeksyen = "";
        String kod_negeri = "";
        String namaSeksyen = "";
        String idSeksyenC = "";
        String tarikhDaftar = "";
        Vector paparSeksyen = null;
        Vector paparSemuaFail = null;
        Vector paparFailLama = null;
        Vector paparFailBaru = null;
        Vector paparFailJilid = null;
        Vector paparUnit = null;
        Vector paparTaraf = null;
        Vector paparStatus = null;
        Vector list = new Vector();
        
        if("tambahFailJilid".equals(action1)){       	
        	
        	String idFailAsal = getParam("idFail");
        	this.context.put("jilid","notsuccess");
   	    	
       		paparSemuaFail = FrmKemaskiniJilidData.getPaparSemuaFail(idFailAsal);
       		Hashtable h = (Hashtable)paparSemuaFail.get(0);
       		    			
       		    			
       		if(h.get("idNegeri").equals("16"))
       		{
       		    				
       		    context.put("mode", "baru");
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
       		    				
       		    vm = "app/pfd/frmKemaskiniFailJilidSeksyen.jsp";
       		            		
       		        			
       		    this.context.put("idFailAsal", h.get("idFailAsal"));
       		    this.context.put("idNegeri", h.get("idNegeri"));
       		    this.context.put("idSeksyen", h.get("idSeksyen"));
       		    this.context.put("noFailAsal", h.get("noFailAsal"));
       		    this.context.put("tajukFailB", h.get("tajukFail"));
       		    this.context.put("kabinetB", h.get("kabinet"));
       		    //this.context.put("selectNegeriA",HTML.SelectNegeri("socNegeriA",Utils.parseLong(h.get("idNegeri").toString()),"disabled"));
       		    this.context.put("selectSeksyenB",HTML.SelectSeksyen("socSeksyenB",Utils.parseLong(h.get("idSeksyen").toString()),"disabled"));
       		    this.context.put("namaUrusan", h.get("KOD_URUSAN")+"-"+h.get("NAMA_URUSAN"));
       		    this.context.put("namaSuburusan", h.get("KOD_SUBURUSAN")+"-"+ h.get("NAMA_SUBURUSAN"));
       		    this.context.put("namaSubSuburusan",h.get("KOD_SUBSUBURUSAN")+"-"+ h.get("NAMA_SUBSUBURUSAN"));
       		    this.context.put("namaSubSubSuburusan", h.get("KOD_SUBSUBSUBURUSAN")+"-"+h.get("NAMA_SUBSUBSUBURUSAN"));
       		    this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(h.get("idTaraf").toString()),"disabled"));
       		    this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(h.get("idStatus").toString()),"disabled"));
       		    this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(h.get("idLokasi").toString()),"disabled"));
       		    //this.context.put("selectFahrasatB",HTML.SelectFahrasat("socFahrasatB",Utils.parseLong(h.get("idFahrasat").toString()),"disabled"));   			
       		    this.context.put("tarikhDaftarB", h.get("tarikhDaftar"));
       		    
       		    view_Seksyen(session);
	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
	         	idSeksyenC = String.valueOf(hB.get("id_seksyen"));
	         	kodSeksyen = String.valueOf(hB.get("kod_seksyen"));
	         	kod_negeri = String.valueOf(hB.get("kod_negeri"));
	         	namaSeksyen = String.valueOf(hB.get("nama_seksyen"));
	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
	         	String nama_negeri = String.valueOf(hB.get("nama_negeri"));
	         	
	         	context.put("idSeksyen",idSeksyenC);
	         	
	         	context.put("idNegeri",id_negeri);
	         	
      	 	
      	 	
      	 	
	         	view_unit(session);
	         	paparUnit = FrmLogDokumenData.getDataUnit();
	         	Hashtable hZ = (Hashtable)paparUnit.get(0);
	         	String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
	         	context.put("seksyen",nama_negeri + "-"+ nama_pejabat);
	         	
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
	         	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
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
       		    				
       		   		vm = "app/pfd/frmKemaskiniFailJilidNegeri.jsp";
       		        			
       		   		this.context.put("idFailAsal", h.get("idFailAsal"));
           		    this.context.put("idNegeri", h.get("idNegeri"));
           		    this.context.put("idSeksyen", h.get("idSeksyen"));
           		    this.context.put("noFailAsal", h.get("noFailAsal"));
       		   		this.context.put("tajukFailB", h.get("tajukFail"));
       		   		this.context.put("kabinetB", h.get("kabinet"));
       		   		this.context.put("selectNegeriB",HTML.SelectNegeriA("socNegeriB",Utils.parseLong(h.get("idNegeri").toString()),"disabled"));
       		   		this.context.put("namaUrusan", h.get("KOD_URUSAN")+"-"+h.get("NAMA_URUSAN"));
        		    this.context.put("namaSuburusan", h.get("KOD_SUBURUSAN")+"-"+ h.get("NAMA_SUBURUSAN"));
        		    this.context.put("namaSubSuburusan",h.get("KOD_SUBSUBURUSAN")+"-"+ h.get("NAMA_SUBSUBURUSAN"));
        		    this.context.put("namaSubSubSuburusan", h.get("KOD_SUBSUBSUBURUSAN")+"-"+h.get("NAMA_SUBSUBSUBURUSAN"));
       		   		this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(h.get("idTaraf").toString()),"disabled"));
       		   		this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(h.get("idStatus").toString()),"disabled"));
       		   		this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(h.get("idLokasi").toString()),"disabled"));
       		   		//this.context.put("selectFahrasatB",HTML.SelectFahrasat("socFahrasatB",Utils.parseLong(h.get("idFahrasat").toString()),"disabled"));
       		   		this.context.put("tarikhDaftarB", h.get("tarikhDaftar"));
       		   		
       		   	 view_Seksyen(session);
 	         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
 	         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
 	         	idSeksyenC = String.valueOf(hB.get("id_seksyen"));
 	         	kodSeksyen = String.valueOf(hB.get("kod_seksyen"));
 	         	kod_negeri = String.valueOf(hB.get("kod_negeri"));
 	         	namaSeksyen = String.valueOf(hB.get("nama_seksyen"));
 	         	String id_negeri = String.valueOf(hB.get("id_negeri"));
 	         	String nama_negeri = String.valueOf(hB.get("nama_negeri"));
 	         	
 	         	context.put("idSeksyen",idSeksyenC);
 	         	
 	         	context.put("idNegeri",id_negeri);
 	         	
       	 	
       	 	
       	 	
 	         	view_unit(session);
 	         	paparUnit = FrmLogDokumenData.getDataUnit();
 	         	Hashtable hZ = (Hashtable)paparUnit.get(0);
 	         	String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
 	         	context.put("seksyen",nama_negeri + "-"+ nama_pejabat);
 	         	
 	         	
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
	         	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
	         	 	Hashtable hE = (Hashtable) paparStatus.get(0);
	         	 	
	         	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
	         	 	}
	         	 	else{
	         	 		this.context.put("lokasi", "");
	         	 	}
               			
       		    }
	
        }
        else if ("simpan".equals(action1)){
        	
        	
        	String idFailAsal = getParam("idFailAsal");
        	this.context.put("jilid","success");
        	
        	String idFailJilid = simpan(session);
        	
       		paparSemuaFail = FrmKemaskiniJilidData.getPaparSemuaFail(idFailAsal);
       		Hashtable g = (Hashtable)paparSemuaFail.get(0);
       		
		   	this.context.put("idFailAsal", g.get("idFailAsal"));
       		this.context.put("noFailAsal", g.get("noFailAsal"));
        	
        	paparFailJilid = FrmKemaskiniJilidData.getPaparFailJilid(idFailJilid);
        	Hashtable h = (Hashtable)paparFailJilid.get(0);
        	
        	
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
       		    				
       		    vm = "app/pfd/frmKemaskiniFailJilidSeksyen.jsp";
       		           			
       		    this.context.put("idFailJilid", h.get("idFailJilid"));
       		    this.context.put("idNegeri", h.get("idNegeri"));
       		    this.context.put("idSeksyen", h.get("idSeksyen"));
       		    this.context.put("noFailJilid", h.get("noFailJilid"));
       		    this.context.put("tajukFailB", h.get("tajukFail"));
       		    this.context.put("kabinetB", h.get("kabinet"));
       		    this.context.put("namaUrusan", h.get("KOD_URUSAN")+"-"+h.get("NAMA_URUSAN"));
       		    this.context.put("namaSuburusan", h.get("KOD_SUBURUSAN")+"-"+ h.get("NAMA_SUBURUSAN"));
       		    this.context.put("namaSubSuburusan",h.get("KOD_SUBSUBURUSAN")+"-"+ h.get("NAMA_SUBSUBURUSAN"));
       		    this.context.put("namaSubSubSuburusan", h.get("KOD_SUBSUBSUBURUSAN")+"-"+h.get("NAMA_SUBSUBSUBURUSAN"));
		   		this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(h.get("idTaraf").toString()),"disabled"));
		   		this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(h.get("idStatus").toString()),"disabled"));
		   		this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(h.get("idLokasi").toString()),"disabled"));
		   		this.context.put("tarikhDaftarB", h.get("tarikhDaftar"));
		   		
			   	 view_Seksyen(session);
	       	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
	       	Hashtable hB = (Hashtable) paparSeksyen.get(0);
	       	idSeksyenC = String.valueOf(hB.get("id_seksyen"));
	       	kodSeksyen = String.valueOf(hB.get("kod_seksyen"));
	       	kod_negeri = String.valueOf(hB.get("kod_negeri"));
	       	namaSeksyen = String.valueOf(hB.get("nama_seksyen"));
	       	String id_negeri = String.valueOf(hB.get("id_negeri"));
	       	String nama_negeri = String.valueOf(hB.get("nama_negeri"));
	       	
	       	context.put("idSeksyen",idSeksyenC);
	       	
	       	context.put("idNegeri",id_negeri);
	       	
		 	
		 	
		 	
	       	view_unit(session);
	       	paparUnit = FrmLogDokumenData.getDataUnit();
	       	Hashtable hZ = (Hashtable)paparUnit.get(0);
	       	String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
	       	context.put("seksyen",nama_negeri + "-"+ nama_pejabat);
	       	
	       	
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
	      	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
	      	 	Hashtable hE = (Hashtable) paparStatus.get(0);
	      	 	
	      	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
	      	 	}
	      	 	else{
	      	 		this.context.put("lokasi", "");
	      	 	}
       		    				
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
       		    				
       		   		vm = "app/pfd/frmKemaskiniFailJilidNegeri.jsp";
       		   		
       		   		this.context.put("idFailJilid", h.get("idFailJilid"));
           		    this.context.put("idNegeri", h.get("idNegeri"));
           		    this.context.put("idSeksyen", h.get("idSeksyen"));
           		    this.context.put("noFailJilid", h.get("noFailJilid"));
       		   		this.context.put("tajukFailB", h.get("tajukFail"));
       		   		this.context.put("kabinetB", h.get("kabinet"));
	       		   	this.context.put("namaUrusan", h.get("KOD_URUSAN")+"-"+h.get("NAMA_URUSAN"));
	    		    this.context.put("namaSuburusan", h.get("KOD_SUBURUSAN")+"-"+ h.get("NAMA_SUBURUSAN"));
	    		    this.context.put("namaSubSuburusan",h.get("KOD_SUBSUBURUSAN")+"-"+ h.get("NAMA_SUBSUBURUSAN"));
	    		    this.context.put("namaSubSubSuburusan", h.get("KOD_SUBSUBSUBURUSAN")+"-"+h.get("NAMA_SUBSUBSUBURUSAN"));
	   		   		this.context.put("selectTarafB",HTML.SelectTarafKeselamatan("socTarafB",Utils.parseLong(h.get("idTaraf").toString()),"disabled"));
	   		   		this.context.put("selectStatusB",HTML.SelectStatusFailB("socStatusB",Utils.parseLong(h.get("idStatus").toString()),"disabled"));
	   		   		this.context.put("selectLokasiFailB",HTML.SelectLokasiFail("socLokasiFailB",Utils.parseLong(h.get("idLokasi").toString()),"disabled"));
	   		   		this.context.put("tarikhDaftarB", h.get("tarikhDaftar"));
   		   		
	   		   		view_Seksyen(session);
		         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
		         	Hashtable hB = (Hashtable) paparSeksyen.get(0);
		         	idSeksyenC = String.valueOf(hB.get("id_seksyen"));
		         	kodSeksyen = String.valueOf(hB.get("kod_seksyen"));
		         	kod_negeri = String.valueOf(hB.get("kod_negeri"));
		         	namaSeksyen = String.valueOf(hB.get("nama_seksyen"));
		         	String id_negeri = String.valueOf(hB.get("id_negeri"));
		         	String nama_negeri = String.valueOf(hB.get("nama_negeri"));
		         	
		         	context.put("idSeksyen",idSeksyenC);
		         	
		         	context.put("idNegeri",id_negeri);
	         	
   	 	
   	 	
   	 	
		         	view_unit(session);
		         	paparUnit = FrmLogDokumenData.getDataUnit();
		         	Hashtable hZ = (Hashtable)paparUnit.get(0);
		         	String nama_pejabat = String.valueOf(hZ.get("NAMA_PEJABAT"));
		         	context.put("seksyen",nama_negeri + "-"+ nama_pejabat);
	         	
	         	
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
		         	 	paparStatus = FrmPendaftaranFailData.getListLokasi();
		         	 	Hashtable hE = (Hashtable) paparStatus.get(0);
		         	 	
		         	 	this.context.put("lokasi", hE.get("LOKASI_FAIL"));
		         	 	}
		         	 	else{
		         	 		this.context.put("lokasi", "");
		         	 	}
		               			
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

        	vm = "app/pfd/frmDaftarFailJilid.jsp";
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
			if (!"".equals(getParam("txdTarikhDaftar")))
				tarikhDaftar = getParam("txdTarikhDaftar");
			
			FrmDaftarFailJilidData.setCarianFail(noFail,noFailLama,tajukFail,negeri,seksyen,status,tarikhDaftar, user_id, myrole, user_negeri, id_seksyen);
	    	list = FrmDaftarFailJilidData.getList();
	    	session.setAttribute("SenaraiFail", list);
	    	setupPage(session,action1,list);
	    	this.context.put("selectNegeriD",HTML.SelectNegeri("socNegeriD",Utils.parseLong(negeri),"",""));
	    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Utils.parseLong(seksyen),"",""));
	    	this.context.put("selectStatusD",HTML.SelectStatusFail("socStatusD",Utils.parseLong(status),""));
	    	this.context.put("txtNoFail", noFail);
	    	this.context.put("txtNoFailLama", noFailLama);
	    	this.context.put("txtTajukFail", tajukFail);
	    	this.context.put("txdTarikhDaftar", tarikhDaftar);
        }
        return vm;
	
    }


	private String simpan(HttpSession session) throws Exception {
		
    	String idFailAsal = getParam("idFailAsal");
    	String user_id = (String)session.getAttribute("_ekptg_user_id");
    	
    	Hashtable h = new Hashtable();
	  	h.put("idFailAsal",idFailAsal);
	  	h.put("id_Masuk",user_id);
	  	
	    return FrmKemaskiniJilidData.addJilid(h);
	}


	private void view_Seksyen(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setSeksyen(user_id);
		
	}
	
	private void view_fail(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("idFail"));
	    FrmKemaskiniJilidData.setDataFail(id);
	  }
	private String simpanFailJilid(HttpSession session) throws Exception {
		
		Hashtable h = new Hashtable();
	    h.put("no_Fail_Root", getParam("txtNoFailInduk"));
	    h.put("id_Negeri", Integer.parseInt(getParam("id_Negeri")));
	    h.put("id_Seksyen", Integer.parseInt(getParam("id_Seksyen")));
	    h.put("id_Urusan", Integer.parseInt(getParam("id_Urusan")));
	    h.put("id_Suburusan", getParam("id_Suburusan"));
	    h.put("id_Tarafkeselamatan", getParam("id_Tarafkeselamatan"));
	    h.put("tajuk_Fail", getParam("txtTajukFailJilid"));
	    h.put("id_Status", getParam("id_Status"));
	    h.put("id_Lokasifail", getParam("socLokasi"));
	    h.put("faharasat", getParam("faharasat"));
	    h.put("tarikh_Daftar_Fail", getParam("txtTarikhDaftar"));
	    h.put("flag_Fail",getParam("flagFail"));
	    h.put("id_FailAsal", getParam("idFail"));

	    String id = FrmKemaskiniJilidData.add(h);
	    FrmKemaskiniJilidData.update(h);
		
		return id;
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
	
	private void view_unit(HttpSession session) throws Exception {
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		FrmLogDokumenData.setUnit(user_id);
	
}
	
	
}
