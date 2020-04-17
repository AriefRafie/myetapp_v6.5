package ekptg.view.meps;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.PPT_modeldata;
import ekptg.ppt.helpers.HTMLPPT;

public class PPT_LaporanStatusWarta extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(PPT_LaporanStatusWarta.class);
	
	// MODEL
	PPT_modeldata model = new PPT_modeldata();	
	
	@Override
	public String doTemplate2() throws Exception{
		
		// DEFAULT
		HttpSession session = request.getSession();		
		String vm = ""; 
    	String doPost = (String)session.getAttribute("doPost");
    	String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
    	String submit = getParam("command"); 
    	String refence = "yes"; 
    	
    	// VECTOR
    	Vector listPageDepan = new Vector();
    	Vector listKod = new Vector();
    	
    	listPageDepan.clear();
    	
    	// DECLARE VARIABLES
    	String id_tahun = "";
    	String id_bulan = "";			
    	String txdTarikhMula="";
    	String txdTarikhAkhir = "";	
    	String getJenisLaporan="";
    	String getJenisLaporans="";
    	String getkategorilaporan="";
		String idNegeri = "";
		
    	// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppt/LaporanStatusWarta.jsp";
    	
    	//TAB
    	String selectedTab = "";
		selectedTab = getParam("tabId");	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		myLogger.info("SUBMIT :: "+submit);
	
		if("BarGraph".equals(submit)){ 
			id_tahun = getParam("socTahun");
			getJenisLaporans =getParam("jenis_laporans");
			getJenisLaporan=getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");

			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
    		this.context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("getJenisLaporans",getJenisLaporans);
    		context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
			
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalStatusWartaDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalStatusWartaNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalStatusWartaKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);	
	    	}

    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence",refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("PieChart".equals(submit)){
			id_tahun = getParam("socTahun");
			getJenisLaporan=getParam("jenis_laporan");
			getJenisLaporans =getParam("jenis_laporans");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
    		this.context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("getJenisLaporans",getJenisLaporans);
    		context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
			
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalStatusWartaDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalStatusWartaNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalStatusWartaKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);	
	    	}	
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence",refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("LineChart".equals(submit)){
			id_tahun = getParam("socTahun");
			getJenisLaporans = getParam("jenis_laporans");
			getJenisLaporan = getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
    		this.context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("getJenisLaporans",getJenisLaporans);
    		context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
			
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalStatusWartaDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalStatusWartaNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalStatusWartaKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);	
	    	}
        	
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
		}else if("PieChart2".equals(submit)){
			id_tahun = getParam("socTahun");
			getJenisLaporans = getParam("jenis_laporans");
			getJenisLaporan = getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
    		this.context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
			this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("getJenisLaporans",getJenisLaporans);
    		context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
			
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalStatusWartaDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalStatusWartaNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalStatusWartaKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);	
	    	}
        	
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("Laporan".equals(submit)){
			id_tahun = getParam("socTahun");
			getJenisLaporans = getParam("jenis_laporans");
			getJenisLaporan = getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
    		this.context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("getJenisLaporans",getJenisLaporans);
    		context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
			
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalStatusWartaDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalStatusWartaNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalStatusWartaKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);	
	    	}
        	
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence", "");
    		
			vm = skrinDepanSenaraiLaporan;
						
    	}else if("search_data".equals(submit)){
    		id_tahun = getParam("socTahun");
    		getJenisLaporans = getParam("jenis_laporans");
			getJenisLaporan = getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
			//GET LIST DATA
			if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalStatusWartaDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalStatusWartaNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalStatusWartaKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);	
	    	}

    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
    		//MAINTAIN SEARCHING VALUES
    		this.context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		this.context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		this.context.put("idNegeri", idNegeri);
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("getJenisLaporans",getJenisLaporans);
    		
       		//DEFAULT
    		context.put("refence", refence);
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else { 
    		
    		idNegeri = "";
    		getJenisLaporan="negeri";
    		getJenisLaporans =getParam("jenis_laporans");
    		 
    		//DROP DOWN
    		this.context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("getJenisLaporans",getJenisLaporans);
    		this.context.put("idNegeri", idNegeri);
			this.context.put("selectNegeri", HTMLPPT.SelectNegeri("socNegeri", null, null, "style=width:130px"));
    		
           	//GET LIST DATA
			if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalStatusWartaDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalStatusWartaNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalStatusWartaKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,getJenisLaporans);	
	    	}				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence", "");  		
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		//context.put("xml", model.generateXML("Laporan Jumlah Permohonan Pengambilan Tanah"));
		String tajukLaporan="";
		if (getJenisLaporan.equals("negeri") || getJenisLaporan.equals("")){
			if (idNegeri!=null && idNegeri.length()>0){
				tajukLaporan = "Laporan Status Warta " + getJenisLaporan + " Mengikut Daerah ";
			} else {
				tajukLaporan = "Laporan Status Warta " + getJenisLaporan + " Mengikut Negeri ";
			}
		} else if (getJenisLaporan.equals("kementerian")){
			tajukLaporan = "Laporan Status Warta" + getJenisLaporan + " Mengikut Kementerian ";
		}
		if (!txdTarikhMula.trim().equals("")&&!txdTarikhAkhir.trim().equals("")) {
			tajukLaporan += " (" + txdTarikhMula +" Hingga "+ txdTarikhAkhir +")";
		}
	
		if(getJenisLaporan.equals("negeri") || getJenisLaporan.equals("")) {
			if (idNegeri!=null && idNegeri.length()>0){
				context.put("xml", model.generateXMLSistemWartaDaerah(tajukLaporan));
			} else {
				context.put("xml", model.generateXMLSistemWartaNegeri(tajukLaporan));
			}
		} else if(getJenisLaporan.equals("kementerian")) {
			context.put("xml", model.generateXMLSistemWartaKementerian(tajukLaporan));
		}
	   			
		//GET KOD - NAMA NEGERI
		listKod = model.getAbbrev();
		context.put("SenaraiKod", listKod);
		context.put("listKod_size", listKod.size());

		//TAB
		this.context.put("selectedTab",selectedTab);
		this.context.put("tajukLaporan",tajukLaporan);
		
    	return vm;
   		
    
		
	}// close doTemplate2

	
// METHOD --------------


	 
}// close class
