package ekptg.view.meps;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.HTP_modeldata;

public class HTP_LaporanPermohonanPerizabanMengikutNegeri extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(HTP_LaporanPermohonanPerizabanMengikutNegeri.class);
	
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
    	// DECLARE VARIABLES
    	String id_tahun = "";
    	String id_bulan = "";
    	String getJenisLaporan="";
    	String txdTarikhMula="";
    	String txdTarikhAkhir="";
    	String idNegeri = "";	
    	
    	// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/htp/LaporanPermohonanPerizabanMengikutNegeri.jsp";
    	
    	
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
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			getJenisLaporan = getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
	    	
			
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
			
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if (getJenisLaporan.equals("negeri")||getJenisLaporan.equals("") ){
    		listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeriAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		}else if (getJenisLaporan.equals("kementerian")){
    			listPageDepan = model.getListTotalPermohonanPerizabanMengikutKementerian_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
    		}
			vm = skrinDepanSenaraiLaporan;
			
    		//DEFAULT
    		context.put("refence", refence);
    		
		}else if("PieChart".equals(submit)){
			System.out.println("pieke");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			getJenisLaporan = getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
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
			
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("getJenisLaporan", getJenisLaporan);
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeriAbbrev(id_tahun,id_bulan,id_negeri,id_kementerian);
    		if (getJenisLaporan.equals("negeri")||getJenisLaporan.equals("") ){
        		listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeriAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}else if (getJenisLaporan.equals("kementerian")){
        			listPageDepan = model.getListTotalPermohonanPerizabanMengikutKementerian_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
        		}
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
            }else if("PieChart2".equals(submit)){
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			getJenisLaporan = getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
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
			
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("getJenisLaporan", getJenisLaporan);
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//istPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeriAbbrev(id_tahun,id_bulan,id_negeri,id_kementerian);
    		if (getJenisLaporan.equals("negeri")||getJenisLaporan.equals("") ){
        		listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeriAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}else if (getJenisLaporan.equals("kementerian")){
        			listPageDepan = model.getListTotalPermohonanPerizabanMengikutKementerian_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
        		}
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
			
		}else if("LineChart".equals(submit)){
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			getJenisLaporan = getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
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
			
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("getJenisLaporan", getJenisLaporan);
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeriAbbrev(id_tahun,id_bulan,id_negeri,id_kementerian);
    		if (getJenisLaporan.equals("negeri")||getJenisLaporan.equals("") ){
        		listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeriAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}else if (getJenisLaporan.equals("kementerian")){
        			listPageDepan = model.getListTotalPermohonanPerizabanMengikutKementerian_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
        		}
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
			
		}else if("Laporan".equals(submit)){
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			getJenisLaporan = getParam("jenis_laporan");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
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
			
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("getJenisLaporan", getJenisLaporan);
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeri(id_tahun,id_bulan);
    		if (getJenisLaporan.equals("negeri")||getJenisLaporan.equals("") ){
        		listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeriAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}else if (getJenisLaporan.equals("kementerian")){
        			listPageDepan = model.getListTotalPermohonanPerizabanMengikutKementerian_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
        		}
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
			vm = skrinDepanSenaraiLaporan;
						
    	}else if("search_data".equals(submit)){	
    		
    		id_tahun = getParam("socTahun");
    		id_bulan = getParam("socBulan");
    		idNegeri= getParam("socNegeri");
//    		id_kementerian= getParam("socKementerian");
    		txdTarikhMula = getParam("txdTarikhMula");
    	  	txdTarikhAkhir = getParam("txdTarikhAkhir");
    	  	getJenisLaporan = getParam("jenis_laporan");
    	  	System.out.println(txdTarikhAkhir);
			
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
			if(idNegeri.equals("")){
				idNegeri = "";
			}else{
				idNegeri = getParam("socNegeri");
			}
    		
			if (getJenisLaporan.equals("negeri")||getJenisLaporan.equals("") ){
	        		//DROPDOWN
	        		if(idNegeri!=null && idNegeri.length()>0){
	            		listPageDepan = model.getJumlahMengikutNegeriDaerahPerizaban(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir,idNegeri);
	        			refence = "no";
	        	} else {
				   listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeriAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	        	}
			}
	        else if((getJenisLaporan.equals("kementerian"))){
	    		listPageDepan = model.getListTotalPermohonanPerizabanMengikutKementerian_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
	    	}
			//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		context.put("idNegeri", idNegeri);
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
    		context.put("getJenisLaporan", getJenisLaporan);
    		context.put("refence", refence);
    		
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else { 
			
    		id_tahun = "";
    		id_bulan = "";
    		idNegeri = "";

    		getJenisLaporan = "negeri";
		
//    		id_negeri = "";
//    		id_kementerian="";
    		
    		
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		context.put("idNegeri", idNegeri);
//    		context.put("id_kementerian", id_kementerian);
    		
    		//DROP DOWN
    		context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("getJenisLaporan",getJenisLaporan);
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri", null, null, "style=width:130px"));
    		
           	//GET LIST DATA
			listPageDepan = model.getListTotalPermohonanPerizabanMengikutNegeri(id_tahun,id_bulan);		
			
			context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		context.put("refence", refence);  	
    		
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		//context.put("xml", model.generateXML("Laporan Permohonan Perizaban Mengikut Negeri"));
		String tajukLaporan="";
		if (getJenisLaporan.equals("negeri")){

		tajukLaporan = "Laporan Permohonan Perizaban Mengikut Negeri "+(id_tahun.equals("")?"":" ("+id_tahun);
		if(!id_bulan.equals("")){			
			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
		}
		if (!txdTarikhMula.trim().equals("")&&!txdTarikhAkhir.trim().equals("") ) {
			tajukLaporan = tajukLaporan + " dari  " + txdTarikhMula +" sehingga "+ txdTarikhAkhir;
		}
		tajukLaporan += (id_tahun.equals("")?"":")");
		context.put("xml", model.generateXML(tajukLaporan));
		
		//GET KOD - NAMA NEGERI
		listKod = model.getAbbrev();
		context.put("SenaraiKod", listKod);
		context.put("listKod_size", listKod.size());
		
		//TAB
		this.context.put("selectedTab",selectedTab);
		this.context.put("tajukLaporan",tajukLaporan);

		
	   }
		else if (getJenisLaporan.equals("kementerian")) {
		
			tajukLaporan = "Laporan Permohonan Perizaban Mengikut Kementerian "+(id_tahun.equals("")?"":" ("+id_tahun);
			if(!id_bulan.equals("")){			
				tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
			}
			if (!txdTarikhMula.trim().equals("")&&!txdTarikhAkhir.trim().equals("") ) {
				tajukLaporan = tajukLaporan + "dari  " + txdTarikhMula +" sehingga "+ txdTarikhAkhir;
			}
			tajukLaporan += (id_tahun.equals("")?"":")");
			context.put("xml", model.generateXMLKementerian(tajukLaporan));
			
			listKod = model.getKodKementerian();
			context.put("SenaraiKod", listKod);
			context.put("listKod_size", listKod.size());
			
			//TAB
			this.context.put("selectedTab",selectedTab);
			this.context.put("tajukLaporan",tajukLaporan);
		}
		
    	return vm;
    	}
}// close class

