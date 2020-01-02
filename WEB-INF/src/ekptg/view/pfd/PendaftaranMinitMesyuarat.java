package ekptg.view.pfd;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.pfd.FrmDaftarMinitMesyuaratData;
import ekptg.model.pfd.FrmKemaskiniMinitMesyuaratData;


public class PendaftaranMinitMesyuarat extends AjaxBasedModule{

	
	public String doTemplate2() throws Exception{
		
		String vm = "";
		String tajukMesyuarat = "";
		String tarikhMesyuarat = "";
		String seksyen = "0";
		String lokasi = "0";
		String selectedTab = new String();
		selectedTab = getParam("tabId").toString();
        String command = getParam("command");
        String action = getParam("action");
        String mode = getParam("mode");

        HttpSession session = this.request.getSession();
        Vector list = new Vector();
        Vector listAgendaMesyuarat = new Vector();
        Vector paparMesyuarat = null;
       
       
        if ("tabMesyuaratAgenda".equals(action)){
        	   	
        	vm = "app/pfd/frmKemaskiniMesyuaratAgenda.jsp";
			context.put("mode","New");
			context.put("readOnly","");
			context.put("disabled","");
        	
        	view_Mesyuarat(session);
        	paparMesyuarat = FrmKemaskiniMinitMesyuaratData.getDataMesyuarat();

			Hashtable h = (Hashtable) paparMesyuarat.get(0);
			
			System.out.println(h);
			
	    	context.put("id_Mesyuarat",h.get("id_Mesyuarat"));
	    	context.put("bil_Mesyuarat",h.get("bil_Mesyuarat"));
	    	context.put("tajuk_Mesyuarat",h.get("tajuk_Mesyuarat"));
	    	context.put("tarikh_Mesyuarat",h.get("tarikh_Mesyuarat"));
	    	
	    	context.put("agenda_Mesyuarat","");
	    	
			listAgendaMesyuarat(session);
  	    	listAgendaMesyuarat = FrmKemaskiniMinitMesyuaratData.getListAgendaMesyuarat();
			context.put("SenaraiAgendaMesyuarat",listAgendaMesyuarat);
	    	
        }
        
        else if ("tabMesyuaratMinit".equals(action)){
        	
        	vm = "app/pfd/frmKemaskiniMesyuaratMinit.jsp";
        	view_Mesyuarat(session);
        	paparMesyuarat = FrmKemaskiniMinitMesyuaratData.getDataMesyuarat();
        
        	Hashtable h = (Hashtable) paparMesyuarat.get(0);
	    	context.put("id_Mesyuarat",h.get("id_Mesyuarat"));
	    	context.put("bil_Mesyuarat",h.get("bil_Mesyuarat"));
	    	context.put("tajuk_Mesyuarat",h.get("tajuk_Mesyuarat"));
	    	context.put("tarikh_Mesyuarat",h.get("tarikh_Mesyuarat"));
        }
        
        else if ("tabMesyuaratSubMinit".equals(action)){
        	
        	vm = "app/pfd/frmKemaskiniMesyuaratAgenda.jsp";
        	view_Mesyuarat(session);
        	paparMesyuarat = FrmKemaskiniMinitMesyuaratData.getDataMesyuarat();
        
        	Hashtable h = (Hashtable) paparMesyuarat.get(0);
	    	context.put("id_Mesyuarat",h.get("id_Mesyuarat"));
	    	context.put("bil_Mesyuarat",h.get("bil_Mesyuarat"));
	    	context.put("tajuk_Mesyuarat",h.get("tajuk_Mesyuarat"));
	    	context.put("tarikh_Mesyuarat",h.get("tarikh_Mesyuarat"));
        }
        
        else if ("simpanAgenda".equals(action)){
        	
        	String idAgenda = simpanAgenda(session);
        	
        	vm = "app/pfd/frmKemaskiniMesyuaratSubMinit.jsp";
        	context.put("mode","View");
			context.put("readOnly","readonly");
			context.put("disabled","disabled");
        	
        	view_Mesyuarat(session);
        	paparMesyuarat = FrmKemaskiniMinitMesyuaratData.getDataMesyuarat();

			Hashtable h = (Hashtable) paparMesyuarat.get(0);
			
	    	context.put("id_Mesyuarat",h.get("id_Mesyuarat"));
	    	context.put("bil_Mesyuarat",h.get("bil_Mesyuarat"));
	    	context.put("tajuk_Mesyuarat",h.get("tajuk_Mesyuarat"));
	    	context.put("tarikh_Mesyuarat",h.get("tarikh_Mesyuarat"));
	    	
	    	context.put("agenda_Mesyuarat","disabled");
	    	
	    	listAgendaMesyuarat(session);
  	    	listAgendaMesyuarat = FrmKemaskiniMinitMesyuaratData.getListAgendaMesyuarat();
			context.put("SenaraiAgendaMesyuarat",listAgendaMesyuarat);
	    	
	    	
        }
        
        else if ("simpanMinit".equals(action)){
        	
        }
        
        else if ("simpanSubminit".equals(action)){
        	
        }
        
        else if ("tambah".equals(action)){
        	       	 
        	vm = "app/pfd/frmKemaskiniMinitMesyuarat.jsp";
        	
        	view_Mesyuarat(session);
      	 	list = FrmKemaskiniMinitMesyuaratData.getDataMesyuarat();
      	 	this.context.put("Mesyuarat",list);
      	 	
      	 	this.context.put("selectAgendaA",HTML.SelectAgenda("socAgendaA"));
          	this.context.put("selectAgendaB",HTML.SelectAgenda("socAgendaB"));
          	this.context.put("selectPerkaraMinitB",HTML.SelectPerkaraMinit("socPerkaraMinitB"));
          	
          	FrmKemaskiniMinitMesyuaratData.setDataPerkaraMinit(0);
          	list = FrmKemaskiniMinitMesyuaratData.getDataPerkaraMinit();
          	this.context.put("PerkaraMinit",list);
          	
          	FrmKemaskiniMinitMesyuaratData.setDataPara(0);
          	list = FrmKemaskiniMinitMesyuaratData.getDataPara();
          	this.context.put("Para",list);
          	
          	
          	listPerkaraMinit(session);
    		list = FrmKemaskiniMinitMesyuaratData.getListPerkaraMinit();
    		this.context.put("SenaraiPerkaraMinit",list);
    		
    		listMinit(session);
    		list = FrmKemaskiniMinitMesyuaratData.getListMinit();
    		this.context.put("SenaraiMinit",list);
    		
    		this.context.put("mode","New");
    		this.context.put("readOnly1", "");
     		this.context.put("readOnly2", "");
     		selectedTab="0";
    		this.context.put("selectedTab",selectedTab);
    		
         }
        else if ("paparPerkaraMinit".equals(command)){
        	vm = "app/pfd/frmKemaskiniMinitMesyuarat.jsp";
        	this.context.put("mode","View");
    		this.context.put("readOnly1", "readonly");
    		this.context.put("readOnly2", "");

    		FrmKemaskiniMinitMesyuaratData.setDataPara(0);
        	list = FrmKemaskiniMinitMesyuaratData.getDataPara();
      	 	this.context.put("Para",list);
      	 	
    		view_Minit(session);
        	list = FrmKemaskiniMinitMesyuaratData.getDataPerkaraMinit();
      	 	this.context.put("PerkaraMinit",list);

      	 	Hashtable h = (Hashtable)list.get(0);
      	 	
        	this.context.put("selectAgendaA",HTML.SelectAgenda("socAgendaA",Long.parseLong(h.get("id_Agendamesyuarat").toString()),"disabled"));
        	this.context.put("selectAgendaB",HTML.SelectAgenda("socAgendaB"));
        	this.context.put("selectPerkaraMinitB",HTML.SelectPerkaraMinit("socPerkaraMinitB"));

      	 	listPerkaraMinit(session);
    		list = FrmKemaskiniMinitMesyuaratData.getListPerkaraMinit();
    		this.context.put("SenaraiPerkaraMinit",list);
    		
    		listMinit(session);
    		list = FrmKemaskiniMinitMesyuaratData.getListMinit();
    		this.context.put("SenaraiMinit",list);
    		
    		selectedTab="0";
    		this.context.put("selectedTab",selectedTab);

        }
        
        else if ("paparPara".equals(command)){
        	        	
        	vm = "app/pfd/frmKemaskiniMinitMesyuarat.jsp";
        
        	FrmKemaskiniMinitMesyuaratData.setDataPerkaraMinit(0);
        	list = FrmKemaskiniMinitMesyuaratData.getDataPerkaraMinit();
      	 	this.context.put("PerkaraMinit",list);
      	 	
        	view_Para(session);
        	list = FrmKemaskiniMinitMesyuaratData.getDataPara();
      	 	this.context.put("Para",list);

      	 	Hashtable h2 = (Hashtable)list.get(0);
      	 	this.context.put("selectAgendaA",HTML.SelectAgenda("socAgendaA"));
        	this.context.put("selectAgendaB",HTML.SelectAgenda("socAgendaB",Long.parseLong(h2.get("id_Agendamesyuarat").toString()),"disabled"));
        	this.context.put("selectPerkaraMinitB",HTML.SelectPerkaraMinit("socPerkaraMinitB",Long.parseLong(h2.get("id_Minitmesyuarat").toString()),"disabled"));

      	 	listPerkaraMinit(session);
    		list = FrmKemaskiniMinitMesyuaratData.getListPerkaraMinit();
    		this.context.put("SenaraiPerkaraMinit",list);
    		
    		listMinit(session);
    		list = FrmKemaskiniMinitMesyuaratData.getListMinit();
    		this.context.put("SenaraiMinit",list);
    		
    		listSubMinit(session);
    		list = FrmKemaskiniMinitMesyuaratData.getListSubMinit();
    		this.context.put("SenaraiSubMinit",list);
    		
    		this.context.put("mode","View");
    		this.context.put("readOnly1", "");

    		this.context.put("readOnly2", "readonly");
    		selectedTab="1";
    		this.context.put("selectedTab",selectedTab);
      	 	
        }
        else if ("kemaskini".equals(command)){
        	
        	vm = "app/pfd/frmKemaskiniMinitMesyuarat.jsp";
        	
        	if (getParam("txtPerkaraMinit") != ""){
    			view_Minit(session);
            	list = FrmKemaskiniMinitMesyuaratData.getDataPerkaraMinit();
          	 	this.context.put("PerkaraMinit",list);
          	 	
          	 	Hashtable h1 = (Hashtable)list.get(0);
          	 	this.context.put("selectAgendaA",HTML.SelectAgenda("socAgendaA",Long.parseLong(h1.get("id_Agendamesyuarat").toString()),""));
            	this.context.put("selectAgendaB",HTML.SelectAgenda("socAgendaB"));
            	this.context.put("selectPerkaraMinitB",HTML.SelectPerkaraMinit("socPerkaraMinitB"));
            	selectedTab="0";
            	this.context.put("selectedTab",selectedTab);
            	this.context.put("readOnly1", "");
            	this.context.put("readOnly2","");
    		}
    		else if (getParam("txtMinit") != ""){
    			view_Para(session);
            	list = FrmKemaskiniMinitMesyuaratData.getDataPara();
          	 	this.context.put("Para",list);
          	 	
          	 	Hashtable h2 = (Hashtable)list.get(0);
          	 	this.context.put("selectAgendaA",HTML.SelectAgenda("socAgendaA"));
            	this.context.put("selectAgendaB",HTML.SelectAgenda("socAgendaB",Long.parseLong(h2.get("id_Agendamesyuarat").toString()),""));
            	this.context.put("selectPerkaraMinitB",HTML.SelectPerkaraMinit("socPerkaraMinitB",Long.parseLong(h2.get("id_Minitmesyuarat").toString()),""));
            	selectedTab="1";
            	this.context.put("selectedTab",selectedTab);
            	this.context.put("readOnly1", "");
            	this.context.put("readOnly2","");
    		}
      	 	listPerkaraMinit(session);
    		list = FrmKemaskiniMinitMesyuaratData.getListPerkaraMinit();
    		this.context.put("SenaraiPerkaraMinit",list);
    		
    		this.context.put("mode","Update");
    		
        }
        else if ("simpan".equals(command)){
        	
        		vm = "app/pfd/frmKemaskiniMinitMesyuarat.jsp";
        		
        		if (Integer.parseInt(getParam("idMinitmesyuarat").toString()) == 0){
        			
        			Hashtable h1 = new Hashtable();
        		    h1.put("id_Agendamesyuarat", getParam("socAgendaA"));
        		    h1.put("tajuk_Minit", getParam("txtPerkaraMinit"));
        		    h1.put("idMesyuarat", getParam("idMesyuarat"));
        		    FrmKemaskiniMinitMesyuaratData.addPerkaraMinit(h1);

              	 	this.context.put("selectAgendaA",HTML.SelectAgenda("socAgendaA"));
                	this.context.put("selectAgendaB",HTML.SelectAgenda("socAgendaB"));
                	this.context.put("selectPerkaraMinitB",HTML.SelectPerkaraMinit("socPerkaraMinitB"));
                	selectedTab="0";
	                this.context.put("selectedTab",selectedTab);
                	this.context.put("readOnly1", "");
                	this.context.put("readOnly2","");
                	
                	listPerkaraMinit(session);
            		list = FrmKemaskiniMinitMesyuaratData.getListPerkaraMinit();
            		this.context.put("SenaraiPerkaraMinit",list);
            		this.context.put("mode","New");
        		}
        		
        		if (Integer.parseInt(getParam("idMinitmesyuaratpara").toString()) == 0){
        			 Hashtable h1 = new Hashtable();

        			 h1.put("para",getParam("txtMinit"));
        			 h1.put("id_Minitmesyuarat", getParam("socPerkaraMinitB"));
        			 h1.put("pihak_Bertindak",getParam("txtTindakan"));
        			 h1.put("maklumbalas",getParam("txtMaklumbalas"));
        			 h1.put("idMesyuarat", getParam("idMesyuarat"));
        			 FrmKemaskiniMinitMesyuaratData.addMinitPara(h1);
              	 	
	              	 this.context.put("selectAgendaA",HTML.SelectAgenda("socAgendaA"));
	                 this.context.put("selectAgendaB",HTML.SelectAgenda("socAgendaB"));
	                 this.context.put("selectPerkaraMinitB",HTML.SelectPerkaraMinit("socPerkaraMinitB"));
	                 selectedTab="1";
	                 this.context.put("selectedTab",selectedTab);
	                 this.context.put("readOnly1", "");
	                 this.context.put("readOnly2","");
	                 
	                 listMinit(session);
	         		 list = FrmKemaskiniMinitMesyuaratData.getListMinit();
	         		 this.context.put("SenaraiMinit",list);
	         		
	         		 listSubMinit(session);
	         		 list = FrmKemaskiniMinitMesyuaratData.getListSubMinit();
	         		 this.context.put("SenaraiSubMinit",list);
	         		 this.context.put("mode","New");
        		}
        	  if (getParam("idMinitmesyuarat") != "" && Integer.parseInt(getParam("idMinitmesyuarat").toString()) != 0){
            		
        			Hashtable h1 = new Hashtable();
        			h1.put("id_Minitmesyuarat", Integer.parseInt(getParam("idMinitmesyuarat")));
        		    h1.put("id_Agendamesyuarat", getParam("socAgendaA"));
        		    h1.put("tajuk_Minit", getParam("txtPerkaraMinit"));
        		    FrmKemaskiniMinitMesyuaratData.updatePerkaraMinit(h1);
        		    
        		    view_Minit(session);
                	list = FrmKemaskiniMinitMesyuaratData.getDataPerkaraMinit();
              	 	this.context.put("PerkaraMinit",list);
              	 	
              	 	Hashtable h2 = (Hashtable)list.get(0);
              	 	this.context.put("selectAgendaA",HTML.SelectAgenda("socAgendaA",Long.parseLong(h2.get("id_Agendamesyuarat").toString()),"disabled"));
                	this.context.put("selectAgendaB",HTML.SelectAgenda("socAgendaB"));
                	this.context.put("selectPerkaraMinitB",HTML.SelectPerkaraMinit("socPerkaraMinitB"));
                	selectedTab="0";
                	this.context.put("selectedTab",selectedTab);
                	this.context.put("readOnly1", "readonly");
                	this.context.put("readOnly2","");
                	
                	listPerkaraMinit(session);
            		list = FrmKemaskiniMinitMesyuaratData.getListPerkaraMinit();
            		this.context.put("SenaraiPerkaraMinit",list);
            		this.context.put("mode","View");
        		}
        		
        	   if (getParam("idMinitmesyuaratpara") != "" && Integer.parseInt(getParam("idMinitmesyuaratpara").toString()) != 0){

        			 Hashtable h1 = new Hashtable();
         			 h1.put("id_Minitmesyuaratpara", Integer.parseInt(getParam("idMinitmesyuaratpara")));
        			 h1.put("para",getParam("txtMinit"));
        			 h1.put("id_Minitmesyuarat", getParam("socPerkaraMinitB"));
        			 h1.put("pihak_Bertindak",getParam("txtTindakan"));
        			 h1.put("maklumbalas",getParam("txtMaklumbalas"));
        			 FrmKemaskiniMinitMesyuaratData.updateMinitPara(h1);
        			
        			 view_Para(session);
        			 list = FrmKemaskiniMinitMesyuaratData.getDataPara();
              	 	 this.context.put("Para",list);
              	 	
	              	 Hashtable h2 = (Hashtable)list.get(0);
	              	 this.context.put("selectAgendaA",HTML.SelectAgenda("socAgendaA"));
	                 this.context.put("selectAgendaB",HTML.SelectAgenda("socAgendaB",Long.parseLong(h2.get("id_Agendamesyuarat").toString()),"disabled"));
	                 this.context.put("selectPerkaraMinitB",HTML.SelectPerkaraMinit("socPerkaraMinitB",Long.parseLong(h2.get("id_Minitmesyuarat").toString()),"disabled"));
	                 selectedTab="1";
	                 this.context.put("selectedTab",selectedTab);
	                 this.context.put("readOnly1", "");
	                 this.context.put("readOnly2","readonly");
	                 
	                 listMinit(session);
	         		 list = FrmKemaskiniMinitMesyuaratData.getListMinit();
	         		 this.context.put("SenaraiMinit",list);
	         		
	         		 listSubMinit(session);
	         		 list = FrmKemaskiniMinitMesyuaratData.getListSubMinit();
	         		 this.context.put("SenaraiSubMinit",list);
	         		 this.context.put("mode","View");
        		}
        		
        	}
        else if ("SenaraiMesyuarat".equals(command)){
       	 vm = "app/pfd/frmDaftarMinitMesyuarat.jsp";
       	 
       	 FrmDaftarMinitMesyuaratData.setCarianMesyuarat(tajukMesyuarat, tarikhMesyuarat, seksyen, lokasi);
       	 list = FrmDaftarMinitMesyuaratData.getList();
       	 session.setAttribute("SenaraiMesyuarat",list);
       	prepareItemForDisplay(session,list,10,"first");
         this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(seksyen),""));
         this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(lokasi),""));
         this.context.put("txtTajukMsyrt", tajukMesyuarat);
         this.context.put("txdTarikhMsyrt", tarikhMesyuarat);

        }
        else if (("next".equals(command)) || ("previous".equals(command))){
        	vm = "app/pfd/frmDaftarMinitMesyuarat.jsp";
          	 if (!"".equals(getParam("txtTajukMsyrt")))
          		 tajukMesyuarat = getParam("txtTajukMsyrt");
          	 if (!"".equals(getParam("txdTarikhMsyrt")))
          		 tarikhMesyuarat = getParam("txdTarikhMsyrt");
          	 if (!"".equals(getParam("socSeksyen")))
          		 seksyen = getParam("socSeksyen");
          	 if (!"".equals(getParam("socLokasi")))
          		 lokasi = getParam("socLokasi");
   			
   			 
          	 FrmDaftarMinitMesyuaratData.setCarianMesyuarat(tajukMesyuarat, tarikhMesyuarat, seksyen, lokasi);
          	 list = FrmDaftarMinitMesyuaratData.getList();
          	 session.setAttribute("SenaraiMesyuarat",list);
          	 prepareItemForDisplay(session,list,10,command);
         	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(seksyen),""));
         	 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(lokasi),""));
         	 this.context.put("txtTajukMsyrt", tajukMesyuarat);
         	 this.context.put("txdTarikhMsyrt", tarikhMesyuarat);
        }
        else{
       	 vm = "app/pfd/frmDaftarMinitMesyuarat.jsp";
       	 
       	 if (!"".equals(getParam("txtTajukMsyrt")))
    		 tajukMesyuarat = getParam("txtTajukMsyrt");
		 if (!"".equals(getParam("txdTarikhMsyrt")))
			tarikhMesyuarat = getParam("txdTarikhMsyrt");
		 if (!"".equals(getParam("socSeksyen")))
			seksyen = getParam("socSeksyen");
		 if (!"".equals(getParam("socLokasi")))
			lokasi = getParam("socLokasi");
			
			 
    	 FrmDaftarMinitMesyuaratData.setCarianMesyuarat(tajukMesyuarat, tarikhMesyuarat, seksyen, lokasi);

    	 list = FrmDaftarMinitMesyuaratData.getList();
    	 setupPage(session,action,list);

      	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(seksyen),""));
      	 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(lokasi),""));
      	 this.context.put("txtTajukMsyrt", tajukMesyuarat);
      	 this.context.put("txdTarikhMsyrt", tarikhMesyuarat);
		 
      	 
        }
        
        return vm;

	}
	

	private String simpanAgenda(HttpSession session) throws Exception {
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String idMesyuarat = getParam("id_Mesyuarat");
			String agendaMesyuarat = getParam("agenda_Mesyuarat");
			String status = getParam("0");
		
			Hashtable h = new Hashtable();
		   
		    h.put("idMesyuarat",idMesyuarat);
		    h.put("agendaMesyuarat",agendaMesyuarat);
		    h.put("statusAgenda",status);
		    return FrmKemaskiniMinitMesyuaratData.addAgenda(h);
	
	}


	private void listAgendaMesyuarat(HttpSession session) throws Exception {
		
		int id = Integer.parseInt(getParam("idMesyuarat"));
    	FrmKemaskiniMinitMesyuaratData.setListAgendaMesyuarat(id);
		
	}

	private void view_Mesyuarat(HttpSession session) throws Exception {
		
    	int id = Integer.parseInt(getParam("idMesyuarat"));
    	FrmKemaskiniMinitMesyuaratData.setDataMesyuarat(id);
	   
	}
	private void listPerkaraMinit(HttpSession session) throws Exception {
		
    	int id = Integer.parseInt(getParam("idMesyuarat"));
    	FrmKemaskiniMinitMesyuaratData.setListPerkaraMinit(id);
	   
	}
	private void listMinit(HttpSession session) throws Exception {
		
    	int id = Integer.parseInt(getParam("idMesyuarat"));
    	FrmKemaskiniMinitMesyuaratData.setListMinit(id);
	   
	}	
	private void view_Minit(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("idMinitmesyuarat"));
    	FrmKemaskiniMinitMesyuaratData.setDataPerkaraMinit(id);
    	
	   
	}
	private void view_Para(HttpSession session) throws Exception {
		
    	int id = Integer.parseInt(getParam("idMinitmesyuaratpara"));
    	FrmKemaskiniMinitMesyuaratData.setDataPara(id);
	   
	}
	private void listSubMinit(HttpSession session) throws Exception {
		
    	int id = Integer.parseInt(getParam("idMinitmesyuaratpara"));
    	FrmKemaskiniMinitMesyuaratData.setListSubMinit(id);

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


}
