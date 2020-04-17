package ekptg.view.pfd;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.pfd.FrmKemaskiniPergerakanFailData;
import ekptg.model.pfd.FrmSenaraiTugasanPFD;

public class SenaraiTugasan extends AjaxBasedModule {
	FrmSenaraiTugasanPFD logic = new FrmSenaraiTugasanPFD();
	
	public String doTemplate2() throws Exception

    {

		 String vm = "";
		 String noFail = "";
         String tajukFail = "";
         String tarikh = "";
         String idFail = getParam("idFail");
         String idPergerakan = getParam("idPergerakanfail");
 		 context.put("idFail", idFail);
         String submit = getParam("command");
         String action1 = getParam("action1");
         String action = getParam("action");
			
			if ("doChangeItemPerPage".equals(action) ||
					"getPage".equals(action)) {
				action1 = action;
			}
         String mode = getParam("mode");
         Vector senarai = new Vector();
         Vector senaraiSelesai = new Vector();
         Vector listLogDokumenByUserId = new Vector();
         Vector listAgihanTugasanByUserId = new Vector();
         Vector listPenerimaTugasanByUserId = new Vector();
         Vector listPenerimaTugasanByUserIdSelesai = new Vector();
         Vector listTugasanSelesai = new Vector();
         Vector listLogDokumenByUserIdSelesai = new Vector();
         Vector listAgihanTugasanByUserIdSelesai = new Vector();
         Vector listAgihanTugasanByUserIdTindakan = new Vector();
         Vector listPenerimaTugasanByUserIdTindakan = new Vector();
         Vector listAgihanTugasanByUserIdSelesaiSemua = new Vector();
         Vector listPenerimaTugasanByUserIdSelesaiSemua = new Vector();
         Vector listMesyuarat = new Vector();
         HttpSession session = this.request.getSession();
         
         
         if ("pengesahanFail".equals(action1)){
        	 
        	 idFail = getParam("idFail");
        	 String idPergerakanfail = getParam("idPergerakanfail");
        	// String status_pergerakan_fail = getParam("status_pergerakan_fail");
        	 String statusPergerakan = getParam("statusPergerakan");
        	 context.put("viewListMesyuarat","false"); 

        	 vm = "app/pfd/frmKemaskiniPengesahanPinjamanFail.jsp";
        	 
        	 if(statusPergerakan.equalsIgnoreCase("1") || statusPergerakan.equalsIgnoreCase("2") ){
	        	 context.put("mode","baru");
	        	 context.put("readOnly","");
				 context.put("disabled","");
				 context.put("readOnlySP","");
				 context.put("disabledSP","");
				 context.put("readOnlyTPM","");
				 context.put("disabledTPM","");
				 context.put("readOnlyTPG","");
				 context.put("disabledTPG","");
				 context.put("readOnlyC","");
				 context.put("disabledC","");
	        	 context.put("pagemode",1);
	        	 context.put("modeJenisStatus","sahPinjam");
	        	 context.put("status_pinjaman_fail",statusPergerakan);
	        	 
	        	 
	        	 Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				 Hashtable hA = (Hashtable)paparMaklumat.get(0);
						
				 context.put("idFail", hA.get("idFail"));
				 context.put("noFail", hA.get("noFail"));
				 context.put("tajukFail", hA.get("tajukFail"));
				 context.put("status", hA.get("status"));
				 
				 context.put("user_Name",(String)session.getAttribute("_portal_username"));
				 
	        	 Vector paparPeminjamFail = FrmKemaskiniPergerakanFailData.paparPeminjamFail(idPergerakan);
				 Hashtable hB = (Hashtable)paparPeminjamFail.get(0);
				 context.put("idPergerakanfail", hB.get("idPergerakanfail"));
				 context.put("nama_peminjam_fail", hB.get("nama_peminjam_fail"));
				 //context.put("status_pinjaman_fail", hB.get("status_pinjaman_fail"));
				 context.put("tarikhPinjamanDisahkan", hB.get("tarikhPinjamanDisahkan"));
				 context.put("tarikhPulangDisahkan", hB.get("tarikhPulangDisahkan"));
				 context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
				 context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));
				 context.put("catatan", hB.get("catatan"));
			 
        	 }
        	 
        	 else{
               	 context.put("mode","baru");
            	 context.put("readOnly","");
    			 context.put("disabled","");
    			 context.put("readOnlySP","");
    			 context.put("disabledSP","");
    			 context.put("readOnlyTPM","");
    			 context.put("disabledTPM","");
    			 context.put("readOnlyTPG","");
    			 context.put("disabledTPG","");
    			 context.put("readOnlyC","");
    			 context.put("disabledC","");
            	 context.put("pagemode",1);
            	 context.put("modeJenisStatus","sahPulang");
            	 context.put("status_pinjaman_fail",statusPergerakan);
            	 
            	 Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
    			 Hashtable hA = (Hashtable)paparMaklumat.get(0);
    					
    			 context.put("idFail", hA.get("idFail"));
    			 context.put("noFail", hA.get("noFail"));
    			 context.put("tajukFail", hA.get("tajukFail"));
    			 context.put("status", hA.get("status"));
    			 
    			 context.put("user_Name",(String)session.getAttribute("_portal_username"));
    			 
            	 Vector paparPeminjamFail = FrmKemaskiniPergerakanFailData.paparPeminjamFail(idPergerakan);
    			 Hashtable hB = (Hashtable)paparPeminjamFail.get(0);
    			 context.put("idPergerakanfail", hB.get("idPergerakanfail"));
    			 context.put("nama_peminjam_fail", hB.get("nama_peminjam_fail"));
    			// context.put("status_pinjaman_fail", hB.get("status_pinjaman_fail"));
    			 context.put("tarikhPinjamanDisahkan", hB.get("tarikhPinjamanDisahkan"));
    			 context.put("tarikhPulangDisahkan", hB.get("tarikhPulangDisahkan"));
    			 context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
    			 context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));
    			 context.put("catatan", hB.get("catatan"));
        		 
        	 }
         }
         
         else if ("simpanPengesahanFail".equals(action1)){
        	 
        	 updatePengesahanFail(session);
        	 vm = "app/pfd/frmKemaskiniPengesahanPinjamanFail.jsp";
        	 context.put("mode","papar");
        	 context.put("readOnly","readonly");
			 context.put("disabled","disabled");
			 context.put("readOnlySP","readonly");
			 context.put("disabledSP","disabled");
			 context.put("readOnlyTPM","readonly");
			 context.put("disabledTPM","disabled");
			 context.put("readOnlyTPG","readonly");
			 context.put("disabledTPG","disabled");
			 context.put("readOnlyC","readonly");
			 context.put("disabledC","disabled");
        	 context.put("pagemode",1);
        	 
        	 Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
			 Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
			 context.put("idFail", hA.get("idFail"));
			 context.put("noFail", hA.get("noFail"));
			 context.put("tajukFail", hA.get("tajukFail"));
			 context.put("status", hA.get("status"));
			
			 
			 
			 context.put("user_Name",(String)session.getAttribute("_portal_username"));
			 
        	 Vector paparPeminjamFail = FrmKemaskiniPergerakanFailData.paparPeminjamFail(idPergerakan);
			 Hashtable hB = (Hashtable)paparPeminjamFail.get(0);
			 context.put("id_pergerakanfail", hB.get("id_pergerakanfail"));
			 context.put("nama_peminjam_fail", hB.get("nama_peminjam_fail"));
			 context.put("status_pinjaman_fail", hB.get("status_pinjaman_fail"));
			 context.put("tarikhPinjamanDisahkan", hB.get("tarikhPinjamanDisahkan"));
			 context.put("tarikhPulangDisahkan", hB.get("tarikhPulangDisahkan"));
			 context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
			 context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));
			 context.put("catatan", hB.get("catatan"));
			 
		     listPergerakanPemohonFail(session);
		     senarai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFail();
		     this.context.put("senaraiPergerakanFail",senarai);
         }
         
         else if ("kemaskiniPengesahanFail".equals(action1)){

        	 vm = "app/pfd/frmKemaskiniPengesahanPinjamanFail.jsp";
        	 context.put("mode","kemaskini");
        	 context.put("readOnly","");
			 context.put("disabled","");
			 context.put("readOnlySP","");
			 context.put("disabledSP","");
			 context.put("readOnlyTPM","");
			 context.put("disabledTPM","");
			 context.put("readOnlyTPG","");
			 context.put("disabledTPG","");
			 context.put("readOnlyC","");
			 context.put("disabledC","");
        	 context.put("pagemode",1);
        	 
        	 idFail = getParam("idFail");
        	 String idPergerakanfail = getParam("idPergerakanfail");
        	 
        	 Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
			 Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
			 context.put("idFail", hA.get("idFail"));
			 context.put("noFail", hA.get("noFail"));
			 context.put("tajukFail", hA.get("tajukFail"));
			 context.put("status", hA.get("status"));
			
			 
			 
			 context.put("user_Name",(String)session.getAttribute("_portal_username"));
			 
        	 Vector paparPeminjamFail = FrmKemaskiniPergerakanFailData.paparPeminjamFail(idPergerakan);
			 Hashtable hB = (Hashtable)paparPeminjamFail.get(0);
			 context.put("id_pergerakanfail", hB.get("id_pergerakanfail"));
			 context.put("nama_peminjam_fail", hB.get("nama_peminjam_fail"));
			 context.put("status_pinjaman_fail", hB.get("status_pinjaman_fail"));
			 context.put("tarikhPinjamanDisahkan", hB.get("tarikhPinjamanDisahkan"));
			 context.put("tarikhPulangDisahkan", hB.get("tarikhPulangDisahkan"));
			 context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
			 context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));
			 context.put("catatan", hB.get("catatan"));
			 
		     listPergerakanPemohonFail(session);
		     senarai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFail();
		     this.context.put("senaraiPergerakanFail",senarai);
         }
         
         else if ("updatePengesahanFail".equals(action1)){
        	
        	 
        	 System.out.println("updatePengesahanFail=====");
        	 
        	 updatePengesahanFail(session);
        	 vm = "app/pfd/frmKemaskiniPengesahanPinjamanFail.jsp";
        	 context.put("mode","paparUpdate");
        	 context.put("readOnly","readonly");
			 context.put("disabled","disabled");
			 context.put("readOnlySP","readonly");
			 context.put("disabledSP","disabled");
			 context.put("readOnlyTPM","readonly");
			 context.put("disabledTPM","disabled");
			 context.put("readOnlyTPG","readonly");
			 context.put("disabledTPG","disabled");
			 context.put("readOnlyC","readonly");
			 context.put("disabledC","disabled");
        	 context.put("pagemode",1);
        	 
        	 Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
			 Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
			 context.put("idFail", hA.get("idFail"));
			 context.put("noFail", hA.get("noFail"));
			 context.put("tajukFail", hA.get("tajukFail"));
			 context.put("status", hA.get("status"));
			
			 
			 
			 context.put("user_Name",(String)session.getAttribute("_portal_username"));
			 
        	 Vector paparPeminjamFail = FrmKemaskiniPergerakanFailData.paparPeminjamFail(idPergerakan);
			 Hashtable hB = (Hashtable)paparPeminjamFail.get(0);
			 context.put("id_pergerakanfail", hB.get("id_pergerakanfail"));
			 context.put("nama_peminjam_fail", hB.get("nama_peminjam_fail"));
			 context.put("status_pinjaman_fail", hB.get("status_pinjaman_fail"));
			 context.put("tarikhPinjamanDisahkan", hB.get("tarikhPinjamanDisahkan"));
			 context.put("tarikhPulangDisahkan", hB.get("tarikhPulangDisahkan"));
			 context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
			 context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));
			 context.put("catatan", hB.get("catatan"));
			 
		     listPergerakanPemohonFail(session);
		     senarai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFail();
		     this.context.put("senaraiPergerakanFail",senarai);
         }
         
         else if ("paparPengesahanFail".equals(action1)){
        	 
        	 updatePengesahanFail(session);
        	 vm = "app/pfd/frmKemaskiniPengesahanPinjamanFail.jsp";
        	 context.put("mode","papar");
        	 context.put("readOnly","readonly");
			 context.put("disabled","disabled");
			 context.put("readOnlySP","readonly");
			 context.put("disabledSP","disabled");
			 context.put("readOnlyTPM","readonly");
			 context.put("disabledTPM","disabled");
			 context.put("readOnlyTPG","readonly");
			 context.put("disabledTPG","disabled");
			 context.put("readOnlyC","readonly");
			 context.put("disabledC","disabled");
        	 context.put("pagemode",1);
        	 
        	 Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
			 Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
			 context.put("idFail", hA.get("idFail"));
			 context.put("noFail", hA.get("noFail"));
			 context.put("tajukFail", hA.get("tajukFail"));
			 context.put("status", hA.get("status"));
			
			 
			 
			 context.put("user_Name",(String)session.getAttribute("_portal_username"));
			 
        	 Vector paparPeminjamFail = FrmKemaskiniPergerakanFailData.paparPeminjamFail(idPergerakan);
			 Hashtable hB = (Hashtable)paparPeminjamFail.get(0);
			 context.put("id_pergerakanfail", hB.get("id_pergerakanfail"));
			 context.put("nama_peminjam_fail", hB.get("nama_peminjam_fail"));
			 context.put("status_pinjaman_fail", hB.get("status_pinjaman_fail"));
			 context.put("tarikhPinjamanDisahkan", hB.get("tarikhPinjamanDisahkan"));
			 context.put("tarikhPulangDisahkan", hB.get("tarikhPulangDisahkan"));
			 context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
			 context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));
			 context.put("catatan", hB.get("catatan"));
			 
		     listPergerakanPemohonFail(session);
		     senarai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFail();
		     this.context.put("senaraiPergerakanFail",senarai);
         }
         
         else if("logDokumenSelesai".equals(action1)){
         	vm = "app/pfd/frmSenaraiTugasan.jsp";	
         	context.put("pagemode",2);
         	context.put("viewListMesyuarat","false"); 
         	context.put("carian","false"); 
         	
         	listLogDokumenByUserIdSelesai(session);
  	    	listLogDokumenByUserIdSelesai = logic.getListLogDokumenSelesai();
  	    	this.context.put("SenaraiDokumenByUserIdSelesai", listLogDokumenByUserIdSelesai);
          }
          
          else if("agihanTugasanTindakan".equals(action1)){
         	vm = "app/pfd/frmSenaraiTugasan.jsp";	
         	context.put("pagemode",3);
         	context.put("viewListMesyuarat","false"); 
         	context.put("carianfail","false"); 
         	
         	
         	listAgihanTugasanByUserIdTindakan(session);
         	listAgihanTugasanByUserIdTindakan = logic.getListAgihanTugasanTindakan();
 	    	this.context.put("SenaraiAgihanTugasanByUserIdTindakan",listAgihanTugasanByUserIdTindakan);
          }
         
          else if("penerimatugasanTindakan".equals(action1)){
        	  	System.out.println("testingnaseriyah");
             	vm = "app/pfd/frmSenaraiTugasan.jsp";	
             	context.put("pagemode",4);
             	context.put("viewListMesyuarat","false"); 
             	context.put("carianfail","false");  
             	
             	listPenerimaTugasanByUserIdTindakan(session);
             	listPenerimaTugasanByUserIdTindakan = logic.getListPenerimaTugasanTindakan();
    	    	this.context.put("SenaraiPenerimaTugasanByUserIdTindakan",listPenerimaTugasanByUserIdTindakan);
           }
         
          else if("agihanTugasanSelesai".equals(action1)){
           	vm = "app/pfd/frmSenaraiTugasan.jsp";	
           	context.put("pagemode",5);
           	context.put("viewListMesyuarat","false"); 
           	context.put("carianfail","false");  
           	
           	listTugasanSelesai(session);
           	listTugasanSelesai = logic.getListTugasanSelesai();
   	    	this.context.put("SenaraiTugasanSelesai",listTugasanSelesai);
            }
           
//            else if("penerimatugasanSelesai".equals(action1)){
//               	vm = "app/pfd/logic.jsp";	
//               	context.put("pagemode",4);
//               	context.put("viewListMesyuarat","false"); 
//               	context.put("carianfail","false"); 
//               	
//               	listPenerimaTugasanByUserIdSelesai(session);
//               	listPenerimaTugasanByUserIdSelesai = logic.getListPenerimaTugasanSelesai();
//      	    	this.context.put("SenaraiPenerimaTugasanByUserIdSelesai",listPenerimaTugasanByUserIdSelesai);
//             }
         
          else if("pengesahanFailSelesai".equals(action1)){
           	vm = "app/pfd/frmSenaraiTugasan.jsp";	
           	context.put("pagemode",5);
           	context.put("viewListPergerakanFail","true"); 
            context.put("carianfail","false"); 
           	
          	listPergerakanPemohonFailSelesai(session);
	    	senaraiSelesai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFailSelesai();
	    	this.context.put("senaraiPergerakanFailSelesai",senaraiSelesai);
         }
        
         else if("cariSenaraifail".equals(action1)){
	        	vm = "app/pfd/frmSenaraiTugasan.jsp";
	        	String jenisSenarai = getParam("jenisSenarai");
	        	
	        	String user_id = (String)session.getAttribute("_ekptg_user_id");
	        	
	    		if (!"".equals(getParam("txtNoFail")))
		  	    		noFail = getParam("txtNoFail");
				if (!"".equals(getParam("txtTajukFail")))
						tajukFail = getParam("txtTajukFail");
				if (!"".equals(getParam("txdTarikh")))
						tarikh = getParam("txdTarikh");
	        	context.put("carianfail","true"); 
	          	context.put("txtNoFail",noFail); 
	          	context.put("txtTajukFail",tajukFail); 
	            context.put("txdTarikh",tarikh);  
	        	 
	        	 if("1".equalsIgnoreCase(jenisSenarai)){
	        		 context.put("viewListLogDokumen","true"); 
	               	 context.put("viewListAgihanTugasan","false"); 
	               	 context.put("viewListPenerimaTugasan","false"); 
	               	 context.put("viewListPergerakanFail","false"); 
	               	 context.put("viewListMesyuarat","false"); 
	        		 context.put("jenis_Senarai",jenisSenarai);
	        		 
	 				listLogDokumenByUserId = logic.getListLogDokumenCarian(noFail,tajukFail,tarikh, user_id); 
		 	    	this.context.put("SenaraiDokumenByUserId", listLogDokumenByUserId);
	//	 	    	Hashtable hA = (Hashtable)listLogDokumenByUserId.get(0);
	//				 context.put("txtNoFail", hA.get("no_Rujukan_Lain"));
	//				 context.put("txtTajukFail", hA.get("tajuk_Dokumen"));
	//				 context.put("txdTarikh", hA.get("tarikh_Dokumen"));
	
	        	 }
	        	 
	        	 else if("2".equalsIgnoreCase(jenisSenarai)){
	           		 context.put("viewListLogDokumen","false"); 
	               	 context.put("viewListAgihanTugasan","true"); 
	               	 context.put("viewListPenerimaTugasan","false"); 
	               	 context.put("viewListPergerakanFail","false"); 
	               	 context.put("viewListMesyuarat","false");  
	        		 context.put("jenis_Senarai",jenisSenarai);
	        		 
	 				
	               	listAgihanTugasanByUserId = logic.getListAgihanTugasanCarian(noFail,tajukFail,tarikh, user_id);
	       	    	this.context.put("SenaraiAgihanTugasanByUserId",listAgihanTugasanByUserId);
	        	 }
	        	 
	        	 else if("3".equalsIgnoreCase(jenisSenarai)){
	           		 context.put("viewListLogDokumen","false"); 
	               	 context.put("viewListAgihanTugasan","false"); 
	               	 context.put("viewListPenerimaTugasan","true"); 
	               	 context.put("viewListPergerakanFail","false"); 
	               	 context.put("viewListMesyuarat","false"); 
	        		 context.put("jenis_Senarai",jenisSenarai);
	        		 
	       	    	listPenerimaTugasanByUserId = logic.getListPenerimaTugasanCarian(noFail,tajukFail,tarikh, user_id);
	       	    	this.context.put("SenaraiPenerimaTugasanByUserId",listPenerimaTugasanByUserId);
	 				
	        	 }
	        	 
	        	 else if("4".equalsIgnoreCase(jenisSenarai)){
	           		 context.put("viewListLogDokumen","false"); 
	               	 context.put("viewListAgihanTugasan","false"); 
	               	 context.put("viewListPenerimaTugasan","false"); 
	               	 context.put("viewListPergerakanFail","true"); 
	               	 context.put("viewListMesyuarat","false"); 
	        		 context.put("jenis_Senarai",jenisSenarai);
	        		 
	        		 senarai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFailCarian(noFail,tajukFail,tarikh, user_id);
	       	    	this.context.put("senaraiPergerakanFail",senarai);
	        	 }
	        	 
	        	 else if("5".equalsIgnoreCase(jenisSenarai)){
	           		 context.put("viewListLogDokumen","false"); 
	               	 context.put("viewListAgihanTugasan","false"); 
	               	 context.put("viewListPenerimaTugasan","false"); 
	               	 context.put("viewListPergerakanFail","false"); 
	               	 context.put("viewListMesyuarat","true"); 
	        		 context.put("jenis_Senarai",jenisSenarai);
	        		 			
	       	    	listMesyuarat = logic.getListMesyuaratMyInfoCarian(noFail,tajukFail,tarikh, user_id);
	       	    	this.context.put("ListMesyuarat", listMesyuarat);
	
	        	 }
	        	 
	        	 else{
	        	     vm = "app/pfd/frmSenaraiTugasan.jsp";
	               	 context.put("pagemode",0); 
	               	 context.put("viewListLogDokumen","true"); 
	               	 context.put("viewListAgihanTugasan","true"); 
	               	 context.put("viewListPenerimaTugasan","true"); 
	               	 context.put("viewListPergerakanFail","true"); 
	               	 context.put("viewListMesyuarat","true"); 
	               	 context.put("jenis_Senarai",0);
	               	 
	                String portal_role = (String) session.getAttribute("_portal_role");
	               	 
	               	listPergerakanPemohonFail(session);
	       	    	senarai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFail();
	       	    	this.context.put("senaraiPergerakanFail",senarai);
	
	               	listAgihanTugasanByUserId(session);
	               	listAgihanTugasanByUserId = logic.getListAgihanTugasan();
	       	    	this.context.put("SenaraiAgihanTugasanByUserId",listAgihanTugasanByUserId);
	       	    	
	       	    	listPenerimaTugasanByUserId(session);
	       	    	listPenerimaTugasanByUserId = logic.getListPenerimaTugasan();
	       	    	this.context.put("SenaraiPenerimaTugasanByUserId",listPenerimaTugasanByUserId);
	       	    	
	       	    	listLogDokumenByUserId(session);
	       	    	listLogDokumenByUserId = logic.getListLogDokumen();
	       	    	this.context.put("SenaraiDokumenByUserId", listLogDokumenByUserId);
	       	    	
	       	    	
	       	    	listMesyuarat = logic.getListMesyuaratMyInfo((String)session.getAttribute("_ekptg_user_id"));
	       	    	this.context.put("ListMesyuarat", listMesyuarat);
	        	 }	
        
        	 
           }
              
         else {
        	vm = "app/pfd/frmSenaraiTugasan.jsp";
        	 context.put("pagemode","0"); 
        	 context.put("viewListLogDokumen","true"); 
        	 context.put("viewListAgihanTugasan","true"); 
        	 context.put("viewListPenerimaTugasan","true"); 
        	 context.put("viewListPergerakanFail","true"); 
        	 context.put("viewListMesyuarat","true"); 
        	 context.put("carianfail","true"); 
        	 
        	 String user_id = (String)session.getAttribute("_ekptg_user_id");
        	 String portal_role = (String) session.getAttribute("_portal_role");
        	 
        	 System.out.println("SESSION : " + session);
        	 
        	listPergerakanPemohonFail(session);
	    	senarai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFail();
	    	this.context.put("senaraiPergerakanFail",senarai);

        	listAgihanTugasanByUserId(session);
        	listAgihanTugasanByUserId = logic.getListAgihanTugasan();
	    	this.context.put("SenaraiAgihanTugasanByUserId",listAgihanTugasanByUserId);
	    	
	    	listPenerimaTugasanByUserId(session);
	    	listPenerimaTugasanByUserId = logic.getListPenerimaTugasan();
	    	this.context.put("SenaraiPenerimaTugasanByUserId",listPenerimaTugasanByUserId);
	    	
	    	listLogDokumenByUserId(session);
	    	listLogDokumenByUserId = logic.getListLogDokumen();
	    	System.out.println("JUMLAH REKOD LOG DOKUMEN : " + listLogDokumenByUserId.size());
	    	this.context.put("SenaraiDokumenByUserId", listLogDokumenByUserId);
	    	
	    	
	    	listMesyuarat = logic.getListMesyuaratMyInfo((String)session.getAttribute("_ekptg_user_id"));
	    	this.context.put("ListMesyuarat", listMesyuarat);
         }
         
         
         
		return vm;
    }

	private void listTugasanSelesai(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		logic.setListTugasanSelesai(user_id);
		
	}

	private void listPenerimaTugasanByUserIdTindakan(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		logic.setListPenerimaTugasanTindakan(user_id);
		
	}

	private void listAgihanTugasanByUserIdTindakan(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		logic.setListAgihanTugasanTindakan(user_id);
		
	}

	private void listPenerimaTugasanByUserId(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		logic.setListPenerimaTugasan(user_id);
	}

	private void listAgihanTugasanByUserId(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		logic.setListAgihanTugasan(user_id);
	}

	private void listLogDokumenByUserId(HttpSession session) throws Exception {
	    String user_id = (String)session.getAttribute("_ekptg_user_id");
	    logic.setListPergerakanPemohonFail(user_id);
	}

	private void updatePengesahanFail(HttpSession session) throws Exception{
			
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String user_name = (String)session.getAttribute("_portal_username");
    		String idFail = getParam("idFail");
    		String idPergerakanfail = getParam("idPergerakanfail");
    		String nama_peminjam_fail = getParam("txtnama_peminjam_fail");
    		String disahkan_Oleh = getParam("txtuser_Name");
    		String status_pinjaman_fail = getParam("socStatusPergerakan");
    		String tarikhPinjamanDisahkan = getParam("txtTarikhPinjamanDisahkan");
    		String tarikhPulangDisahkan = getParam("txtTarikhPulangDisahkan");
    		String tempohPinjamDari = getParam("txtTempohPinjamDari");
    		String tempohPinjamHingga = getParam("txtTempohPinjamHingga");
    		String catatan = getParam("txtCatatan");
    		
    		Hashtable h = null;
    		h = new Hashtable();
    		
    		h.put("nama_peminjam_fail",nama_peminjam_fail);
    		h.put("disahkan_Oleh",disahkan_Oleh);
    		h.put("status_pinjaman_fail", status_pinjaman_fail);
    		//h.put("nama_pegawai_PTfail", getParam("namaPenghantar"));
			h.put("tarikhPinjamanDisahkan",tarikhPinjamanDisahkan);
			h.put("tarikhPulangDisahkan",tarikhPulangDisahkan);
			h.put("tempohPinjamDari",tempohPinjamDari);
			h.put("tempohPinjamHingga",tempohPinjamHingga);
			h.put("catatan",catatan);
			h.put("id_Fail",idFail);
			h.put("idPergerakanfail",idPergerakanfail);
			h.put("id_Masuk",user_id);
			
    		FrmKemaskiniPergerakanFailData.updatePengesahanFail(h);
			
	}

	private void listPergerakanPemohonFail(HttpSession session) throws Exception {
	    String user_id = (String)session.getAttribute("_ekptg_user_id");
	    //int id = Integer.parseInt(getParam("idFail"));
	    FrmKemaskiniPergerakanFailData.setListPergerakanPemohonFail(user_id);
	}
	
	private void listLogDokumenByUserIdSelesai(HttpSession session) throws Exception {
		 String user_id = (String)session.getAttribute("_ekptg_user_id");
		 logic.setListLogDokumenByUserIdSelesai(user_id);
		
	}

	private void listPergerakanPemohonFailSelesai(HttpSession session) throws Exception {
		
	    
	    String user_id = (String)session.getAttribute("_ekptg_user_id");
	    //int id = Integer.parseInt(getParam("idFail"));
	    FrmKemaskiniPergerakanFailData.setListPergerakanPemohonFailSelesai(user_id);
		
	}

}
