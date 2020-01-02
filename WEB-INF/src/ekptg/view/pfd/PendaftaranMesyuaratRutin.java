package ekptg.view.pfd;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmDaftarMesyuaratData;
import ekptg.model.pfd.FrmKemaskiniMesyuaratData;

public class PendaftaranMesyuaratRutin  extends AjaxBasedModule{
	
	public String doTemplate2() throws Exception{
		 String vm = "";
		 String disability1 = "";
		 String disability2 = "";
		 String readability1 = "";
		 String tajukMesyuarat = "";
		 String tarikhMesyuarat = "";
		 String seksyen = "0";
		 String lokasi = "0";
         String submit = getParam("command");
         String action = getParam("action");
         String mode = getParam("mode");
         HttpSession session = this.request.getSession();
         Vector list = new Vector();
         Vector paparMesyuarat = new Vector();
         this.context.put("Util",new Utils());
         String idMesyuarat = getParam("idMesyuarat");
         
         
         if ("tambah".equals(action)){
        	 
         	vm = "app/pfd/frmKemaskiniMesyuarat.jsp";
        	disability1 = "";
        	readability1 = "";
        	this.context.put("readOnly",readability1);
       	    this.context.put("disable",disability1);
       	    

        	
        	FrmKemaskiniMesyuaratData.setData("0","");
        	paparMesyuarat = FrmKemaskiniMesyuaratData.getData();
       	 	//list = null;
       	 	this.context.put("Mesyuarat",paparMesyuarat);
       	 
           	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen"));
          	this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi"));
          	this.context.put("selectStatus",HTML.SelectStatusFail("socStatus"));
          	this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai"));
          	this.context.put("selectFail",HTML.SelectFail("socFail"));
       	    this.context.put("mode","New");
	       	    
       	    FrmKemaskiniMesyuaratData.setListAhliMesyuarat(0);
       	   list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
	  	    this.context.put("SenaraiAhliMesyuarat",list);
	  	     
    		FrmKemaskiniMesyuaratData.setListAgendaMesyuarat(0);
   		    list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
   		    this.context.put("SenaraiAgendaMesyuarat",list);
         }
         else if ("papar".equals(action)){
        	 disability1 = "disabled";
        	 readability1 = "readonly";
        	 vm = "app/pfd/frmKemaskiniMesyuaratRutin.jsp";
        	 
        	 FrmKemaskiniMesyuaratData.setData(idMesyuarat,"");
        	 paparMesyuarat = FrmKemaskiniMesyuaratData.getData();
        	 this.context.put("Mesyuarat",paparMesyuarat);
        	 this.context.put("readOnly",readability1);
        	 this.context.put("disable",disability1);
        	 this.context.put("mode","View");
        	 
        	 Hashtable h = (Hashtable) paparMesyuarat.get(0);
      	     this.context.put("txtBilMsyrt",h.get("bil_Mesyuarat").toString());
        	 this.context.put("kategori_Mesyuarat",h.get("kategori_Mesyuarat").toString());
         	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),disability1));
         	 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(h.get("id_Lokasi").toString()),disability1));
      	     this.context.put("selectStatus",HTML.SelectStatusFail("socStatus",Long.parseLong(h.get("id_Status").toString()),disability1));
      	     this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),disability1));
      	     this.context.put("selectFail",HTML.SelectFail("socFail",Long.parseLong(h.get("id_Fail").toString()),disability1));
      	     
//      	     listAhliMesyuarat(session);
//      	     list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
//      	     this.context.put("SenaraiAhliMesyuarat",list);
//      	     
//      	     listAgendaMesyuarat(session);
//    	     list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
//    	     this.context.put("SenaraiAgendaMesyuarat",list);
     	     
         }
         else if ("kemaskini".equals(action)){
        	 
        	 disability1 = "";
        	 readability1 = "";
        	 vm = "app/pfd/frmKemaskiniMesyuaratRutin.jsp";
        	 
        	 FrmKemaskiniMesyuaratData.setData(idMesyuarat,"");
        	 list = FrmKemaskiniMesyuaratData.getData();
        	 this.context.put("Mesyuarat",list);
        	 this.context.put("readOnly",readability1);
        	 this.context.put("disable",disability1);
        	 this.context.put("mode","Update");
        	 
        	 Hashtable h = (Hashtable) list.get(0);
        	 
         	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),disability1));
         	 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(h.get("id_Lokasi").toString()),disability1));
      	     this.context.put("selectStatus",HTML.SelectUrusan("socStatus",Long.parseLong(h.get("id_Status").toString()),disability1));
      	     this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),disability1));
      	     this.context.put("selectFail",HTML.SelectFail("socFail",Long.parseLong(h.get("id_Fail").toString()),disability2));
      	     
      	     listAhliMesyuarat(session);
    	     list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
    	     this.context.put("SenaraiAhliMesyuarat",list);
    	     
    	     listAgendaMesyuarat(session);
	  	     list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
	  	     this.context.put("SenaraiAgendaMesyuarat",list);
        	 
         }
         else if ("simpan".equals(action)){
        	 	if ("tambahBaru".equals(mode)){
            	 vm = "app/pfd/frmKemaskiniMesyuaratRutin.jsp";
            	 idMesyuarat = simpanMesyuarat(session);
            	 
            	 FrmKemaskiniMesyuaratData.setData(idMesyuarat,"");
            	 list = FrmKemaskiniMesyuaratData.getData();
            	 this.context.put("Mesyuarat",list);
            	 this.context.put("readOnly","readonly");
            	 this.context.put("disable","disabled");
            	 this.context.put("mode","View");
            	 disability1 = "disabled";
            	 readability1 = "readonly";
            	 
            	 Hashtable h = (Hashtable) list.get(0);
            	 
//             	 this.context.put("selectSeksyen",HTML.SelectNegeri("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),"disabled"));
//             	 this.context.put("selectLokasi",HTML.SelectSeksyen("socLokasi",Long.parseLong(h.get("id_Lokasi").toString()),"disabled"));
//          	     this.context.put("selectStatus",HTML.SelectUrusan("socStatus",Long.parseLong(h.get("id_Status").toString()),"disabled"));
//          	     this.context.put("selectPegawai",HTML.SelectSuburusan("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),"disabled"));
//          	     this.context.put("selectFail",HTML.SelectFail("socFail",Long.parseLong(h.get("id_Fail").toString()),"disabled"));
            	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),disability1));
             	 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(h.get("id_Lokasi").toString()),disability1));
          	     this.context.put("selectStatus",HTML.SelectStatusFail("socStatus",Long.parseLong(h.get("id_Status").toString()),disability1));
          	     this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),disability1));
          	     this.context.put("selectFail",HTML.SelectFail("socFail",Long.parseLong(h.get("id_Fail").toString()),disability1));
          	     this.context.put("kategori_Mesyuarat",h.get("kategori_Mesyuarat").toString());
          	           	         
            	 listAhliMesyuarat(session);
        	     list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
        	     this.context.put("SenaraiAhliMesyuarat",list);
        	     
        	     listAgendaMesyuarat(session);
    	  	     list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
    	  	     this.context.put("SenaraiAgendaMesyuarat",list);
        	 	}
        	 	else if ("kemaskiniMesyuarat".equals(mode)){
        	 		 
	               	 vm = "app/pfd/frmKemaskiniMesyuaratRutin.jsp";
	               	 this.context.put("mode","Update");
	               	 simpanMesyuarat(session);
	               	 
	               	
	               	 FrmKemaskiniMesyuaratData.setData(idMesyuarat,"");
	            	 list = FrmKemaskiniMesyuaratData.getData();
	            	 this.context.put("Mesyuarat",list);
	            	 this.context.put("readOnly","readonly");
	            	 this.context.put("disable","disabled");
	            	 this.context.put("mode","View");
	            	 
	            	 Hashtable h = (Hashtable) list.get(0);
	            	          	     
	            	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),"disabled"));
	             	 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(h.get("id_Lokasi").toString()),"disabled"));
	          	     this.context.put("selectStatus",HTML.SelectStatusFail("socStatus",Long.parseLong(h.get("id_Status").toString()),"disabled"));
	          	     this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),"disabled"));
	          	     this.context.put("selectFail",HTML.SelectFail("socFail",Long.parseLong(h.get("id_Fail").toString()),"disabled"));

	               	 listAhliMesyuarat(session);
	           	     list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
	           	     this.context.put("SenaraiAhliMesyuarat",list);
	           	     
	           	     listAgendaMesyuarat(session);
	       	  	     list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
	       	  	     this.context.put("SenaraiAgendaMesyuarat",list);
        	 	}
    
         }
         else if ("SenaraiMesyuarat".equals(action)){
        	 vm = "app/pfd/frmDaftarMesyuaratRutin.jsp";
        	 
        	 FrmDaftarMesyuaratData.setCarianMesyuarat(tajukMesyuarat, tarikhMesyuarat, seksyen, lokasi,"");
        	 list = FrmDaftarMesyuaratData.getList();
        	 setupPage(session,action,list);
          	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(seksyen),""));
          	 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(lokasi),""));
          	 this.context.put("txtTajukMsyrt", tajukMesyuarat);
          	 this.context.put("txdTarikhMsyrt", tarikhMesyuarat);

         }
        
         else{

          	vm = "app/pfd/frmKemaskiniMesyuaratRutin.jsp";
        	disability1 = "";
        	readability1 = "";
        	this.context.put("readOnly",readability1);
       	    this.context.put("disable",disability1);
       	    

        	
        	FrmKemaskiniMesyuaratData.setData("0","");
        	paparMesyuarat = FrmKemaskiniMesyuaratData.getData();
       	 	//list = null;
       	 	this.context.put("Mesyuarat",paparMesyuarat);
           	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen"));
          	this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi"));
          	this.context.put("selectStatus",HTML.SelectStatusFail("socStatus"));
          	this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai"));
          	this.context.put("selectFail",HTML.SelectFail("socFail"));
       	    this.context.put("mode","New");
	       	    
       	    FrmKemaskiniMesyuaratData.setListAhliMesyuarat(0);
       	   list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
	  	    this.context.put("SenaraiAhliMesyuarat",list);
	  	     
    		FrmKemaskiniMesyuaratData.setListAgendaMesyuarat(0);
   		    list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
   		    this.context.put("SenaraiAgendaMesyuarat",list);
        	 
         }
		
        return vm;
		
	}
	
	private String simpanMesyuarat(HttpSession session) throws Exception {
		
		String idMesyuarat;
		System.out.println(getParam("idMesyuarat"));
		if (getParam("idMesyuarat") == "" || Integer.parseInt(getParam("idMesyuarat").toString())== 0){
		
			Hashtable h = new Hashtable();
		    h.put("bil_Mesyuarat", "1");
		    h.put("tajuk_Mesyuarat", getParam("txtTajukMsyrt"));
		    h.put("kategori_Mesyuarat", "2");
		    h.put("tarikh_Mesyuarat", "");
		    h.put("masa_Mesyuarat_Dari", "");
		    h.put("waktu_Mesyuarat_Dari", "");
		    h.put("masa_Mesyuarat_Hingga", "");
		    h.put("waktu_Mesyuarat_Hingga", "");
		    h.put("id_Seksyen",getParam("socSeksyen"));
		    h.put("id_Status",getParam("socStatus"));
			h.put("id_Pegawai", getParam("socPegawai"));
			h.put("id_Lokasi", getParam("socLokasi"));
 			h.put("id_Fail", getParam("socFail"));
			h.put("catatan", getParam("txtCatatan"));
			idMesyuarat = FrmKemaskiniMesyuaratData.add(h);
		    
		    return idMesyuarat;
		}
		else{
			Hashtable h = new Hashtable();
			//h.put("id_Mesyuarat", Integer.parseInt(getParam("idMesyuarat")));
			h.put("id_Mesyuarat",getParam("idMesyuarat"));
			h.put("bil_Mesyuarat", getParam("txtBilMsyrt"));
		    h.put("tajuk_Mesyuarat", getParam("txtTajukMsyrt"));
		    h.put("kategori_Mesyuarat", getParam("kategori_Mesyuarat"));
		    h.put("tarikh_Mesyuarat", getParam("txdTarikh"));
		    h.put("masa_Mesyuarat_Dari", getParam("txtDariJam"));
		    h.put("waktu_Mesyuarat_Dari", getParam("socWaktuDariJam"));
		    h.put("masa_Mesyuarat_Hingga", getParam("txtHinggaJam"));
		    h.put("waktu_Mesyuarat_Hingga", getParam("socWaktuHinggaJam"));
		    h.put("id_Seksyen",getParam("socSeksyen"));
		    h.put("id_Status",getParam("socStatus"));
			h.put("id_Pegawai", getParam("socPegawai"));
			h.put("id_Lokasi", getParam("socLokasi"));
			h.put("id_Fail", getParam("socFail"));
			h.put("catatan", getParam("txtCatatan"));
			idMesyuarat = FrmKemaskiniMesyuaratData.update(h);
				    
			return idMesyuarat;
		}
	   
	  }
	private void listAhliMesyuarat(HttpSession session) throws Exception {
		
			int id = Integer.parseInt(getParam("idMesyuarat"));
	    	FrmKemaskiniMesyuaratData.setListAhliMesyuarat(id);

	  }
	private void listAgendaMesyuarat(HttpSession session) throws Exception {
			int id = Integer.parseInt(getParam("idMesyuarat"));
	    	FrmKemaskiniMesyuaratData.setListAgendaMesyuarat(id);

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
		this.context.put("SenaraiMesyuarat",paging.getPage(page));
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

//	private void refreshAgenda(HttpSession session) throws Exception {
//		Vector list = new Vector();
//		listAgendaMesyuarat(session);
//	    list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
//	    this.context.put("SenaraiAgendaMesyuarat",list);
//	}

	
	
}


