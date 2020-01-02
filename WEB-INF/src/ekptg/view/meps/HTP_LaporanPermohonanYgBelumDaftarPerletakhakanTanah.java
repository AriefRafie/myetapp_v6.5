package ekptg.view.meps;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.HTP_modeldata;

public class HTP_LaporanPermohonanYgBelumDaftarPerletakhakanTanah extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(HTP_LaporanPermohonanYgBelumDaftarPerletakhakanTanah.class);
	
	// MODEL
	HTP_modeldata model = new HTP_modeldata();	
	
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
    	String getJenisLaporan = "";
		String idNegeri = "";
//    	this.context.put("getJenisLaporan", getJenisLaporan);
//    	System.out.println("getJenisLaporan :"+getJenisLaporan);

       
    	// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/htp/LaporanPermohonanYgBelumDaftarPerletakhakanTanah.jsp";
    	
    	//TAB
    	String selectedTab = "";
		selectedTab = getParam("tabId");	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		myLogger.info("SUBMIT :: "+submit);
		myLogger.info("socTahun :: "+id_tahun);
		myLogger.info("socBulan :: "+id_bulan);
	
		if("BarGraph".equals(submit)){ 

			getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if(id_tahun.equals("")){
				id_tahun = "";
			}else{
				id_tahun = getParam("socTahun");
			}
			
			if(id_bulan.equals("")){
				id_bulan = "";
			}else{
				id_bulan = getParam("socBulan");
	    		if (id_bulan.length() < 2)
	    			id_bulan = "0" + id_bulan;
			}
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		context.put("selectNegeri",
					HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrevDaerah_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    	}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("LineChart".equals(submit)){
			
			getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if(id_tahun.equals("")){
				id_tahun = "";
			}else{
				id_tahun = getParam("socTahun");
			}
			
			if(id_bulan.equals("")){
				id_bulan = "";
			}else{
				id_bulan = getParam("socBulan");
	    		if (id_bulan.length() < 2)
	    			id_bulan = "0" + id_bulan;
			}
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
			context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		context.put("selectNegeri",
					HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrevDaerah_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    	}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("PieChart".equals(submit)){
			
			getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if(id_tahun.equals("")){
				id_tahun = "";
			}else{
				id_tahun = getParam("socTahun");
			}
			
			if(id_bulan.equals("")){
				id_bulan = "";
			}else{
				id_bulan = getParam("socBulan");
	    		if (id_bulan.length() < 2)
	    			id_bulan = "0" + id_bulan;
			}
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
			context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		context.put("selectNegeri",
					HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrevDaerah_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    	}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
	
		}else if("PieChart2".equals(submit)){
			
			getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if(id_tahun.equals("")){
				id_tahun = "";
			}else{
				id_tahun = getParam("socTahun");
			}
			
			if(id_bulan.equals("")){
				id_bulan = "";
			}else{
				id_bulan = getParam("socBulan");
	    		if (id_bulan.length() < 2)
	    			id_bulan = "0" + id_bulan;
			}
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
			context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		context.put("selectNegeri",
					HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrevDaerah_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    	}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
			
		}else if("Laporan".equals(submit)){
			
			getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			if(id_tahun.equals("")){
				id_tahun = "";
			}else{
				id_tahun = getParam("socTahun");
			}
			
			if(id_bulan.equals("")){
				id_bulan = "";
			}else{
				id_bulan = getParam("socBulan");
	    		if (id_bulan.length() < 2)
	    			id_bulan = "0" + id_bulan;
			}
			
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
			context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		context.put("selectNegeri",
					HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrevDaerah_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    	}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
						
    	}else if("search_data".equals(submit)){		
    		
    		getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("socTahun");
			id_bulan = getParam("socBulan");
			txdTarikhMula = getParam("txdTarikhMula");
		    txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
    		
			if(id_tahun.equals("")){
				id_tahun = "";
			}else{
				id_tahun = getParam("socTahun");
			}
			
			if(id_bulan.equals("")){
				id_bulan = "";
			}else{
				id_bulan = getParam("socBulan");
	    		if (id_bulan.length() < 2)
	    			id_bulan = "0" + id_bulan;
			}
    		
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
			
			//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrevDaerah_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    	}
			//listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev(id_tahun,id_bulan);

    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",
					HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		context.put("txdTarikhMula",txdTarikhMula);
    		context.put("txdTarikhAkhir",txdTarikhAkhir);
    		context.put("getJenisLaporan",getJenisLaporan);
			context.put("idNegeri", idNegeri);
			context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else { 
			
			idNegeri = "";
    		id_tahun = "";
    		id_bulan = "";
    		txdTarikhMula = "";
    	  	txdTarikhAkhir = "";
    		getJenisLaporan="negeri";
    	  	
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		context.put("idNegeri", idNegeri);
    		
    		//DROP DOWN
    		context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
			context.put("selectNegeri", HTML.SelectNegeri("socNegeri", null, null, "style=width:130px"));	
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
    		context.put("getJenisLaporan",getJenisLaporan);
    		
           	//GET LIST DATA
			//listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanah(id_tahun,id_bulan);				
			if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrevDaerah_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    	}
    		
			context.put("PermohonanList", listPageDepan);
			context.put("list_size", listPageDepan.size());
			
    		//DEFAULT
    		context.put("refence", "");
    		
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		//context.put("xml", model.generateXML("Laporan Permohonan Yang Belum Daftar Perletakhakan Tanah"));
		String tajukLaporan="";
		if (getJenisLaporan.equals("negeri") || getJenisLaporan.equals("")){
			if (idNegeri!=null && idNegeri.length()>0){
				tajukLaporan = "Laporan Permohonan Yang Belum Daftar Perletakhakan Tanah Mengikut Daerah Negeri ";
			} else {
				tajukLaporan = "Laporan Permohonan Yang Belum Daftar Perletakhakan Tanah Mengikut Negeri ";
			}
		} else if (getJenisLaporan.equals("kementerian")){
			tajukLaporan = "Laporan Permohonan Yang Telah Didaftarkan Perletakhakan Tanah ";
		}
		if (!txdTarikhMula.trim().equals("")&&!txdTarikhAkhir.trim().equals("")) {
			tajukLaporan += " (" + txdTarikhMula +" Hingga "+ txdTarikhAkhir +")";
		} else if (!id_tahun.equals("")){
			tajukLaporan += (id_tahun.equals("")?"":"("+id_tahun);
			if(!id_bulan.equals("")){			
				tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
			}
			tajukLaporan += (id_tahun.equals("")?"":")");
		}
		
		if (idNegeri!=null && idNegeri.length()>0){
			context.put("xml", model.generateXMLDaerah(tajukLaporan));
		} else {
			context.put("xml", model.generateXML(tajukLaporan));
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
