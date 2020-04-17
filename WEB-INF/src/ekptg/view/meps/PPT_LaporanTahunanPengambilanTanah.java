package ekptg.view.meps;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.PPT_modeldata;

public class PPT_LaporanTahunanPengambilanTanah extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(PPT_LaporanTahunanPengambilanTanah.class);
	
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
    	
    	System.out.println(getJenisLaporan);
    	// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppt/LaporanTahunanPengambilanTanah.jsp";
    	
    	//TAB
    	String selectedTab = "";
		selectedTab = getParam("tabId");	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		myLogger.info("SUBMIT :: "+submit);
	
	if("BarGraph".equals(submit)){ 
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
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
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//context.put("xml", model.generateXMLJumlahHakmilik("Laporan Jumlah Pengambilan Tanah Mengikut Lot"));
			
    		String tajukLaporan = "Laporan Penarikan Balik"+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXMLJumlahHakmilik(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		vm = skrinDepanSenaraiLaporan;
       		//DEFAULT
    		context.put("refence", refence);
			
	//add faresh
		}else if("BarGraph2".equals(submit)){
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
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
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//context.put("xml", model.generateXMLfaresh("Laporan Jumlah Permohonan dan Jumlah Lot Pengambilan Tanah"));
			
    		String tajukLaporan = "Laporan Penarikan Balik"+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXMLfaresh(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		
    		vm = skrinDepanSenaraiLaporan;
       		//DEFAULT
    		context.put("refence", refence);
			
}else if("PieChart".equals(submit)){
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
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
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//context.put("xml", model.generateXMLJumlahHakmilik("Laporan Jumlah Pengambilan Tanah Mengikut Lot"));
			
    		String tajukLaporan = "Laporan Penarikan Balik "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXMLJumlahHakmilik(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		
    		vm = skrinDepanSenaraiLaporan;
       		//DEFAULT
    		context.put("refence", refence);
			
		}else if("PieChart2".equals(submit)){
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
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
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    	     listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//context.put("xml", model.generateXMLJumlahHakmilik("Laporan Jumlah Permohonan dan Jumlah Lot Pengambilan Tanah"));
			
    		String tajukLaporan = "Laporan Penarikan Balik"+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXMLJumlahHakmilik(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		
    		vm = skrinDepanSenaraiLaporan;
       		//DEFAULT
    		context.put("refence", refence);
		
}else if("LineChart".equals(submit)){
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
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
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//context.put("xml", model.generateXMLJumlahHakmilik("Laporan Jumlah Pengambilan Tanah Mengikut Lot"));
			
    		String tajukLaporan = "Laporan Penarikan Balik "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXMLJumlahHakmilik(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		
    		vm = skrinDepanSenaraiLaporan;
       		//DEFAULT
    		context.put("refence", refence);

	}else if("LineChart2".equals(submit)){
	
		id_tahun = getParam("id_tahun");
		id_bulan = getParam("id_bulan");
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
		this.context.put("txdTarikhMula",  txdTarikhMula);
		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
		//TAB
		this.context.put("selectedTab",selectedTab);

		//DROPDOWN
		//listPageDepan = model.getListTotalProjectKementerianAbbrev(id_tahun,id_bulan);
		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
		//context.put("xml", model.generateXMLfaresh2("Laporan Jumlah Permohonan dan Jumlah Lot Pengambilan Tanah"));
		
		String tajukLaporan = "Laporan Tahunan Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
		if(!id_bulan.equals("")){			
			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
		}
		tajukLaporan += (id_tahun.equals("")?"":")");
		context.put("xml", model.generateXMLfaresh2(tajukLaporan));
		this.context.put("tajukLaporan",tajukLaporan);
		
		vm = skrinDepanSenaraiLaporan;
   		//DEFAULT
		context.put("refence", refence);

	}else if("AreaChart".equals(submit)){
		
		id_tahun = getParam("id_tahun");
		id_bulan = getParam("id_bulan");
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
		this.context.put("txdTarikhMula",  txdTarikhMula);
		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
		//TAB
		this.context.put("selectedTab",selectedTab);

		//DROPDOWN
		//listPageDepan = model.getListTotalProjectKementerianAbbrev(id_tahun,id_bulan);
		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
		context.put("xml", model.generateXMLfaresh3());
		vm = skrinDepanSenaraiLaporan;
   		//DEFAULT
		context.put("refence", refence);
		
			//end faresh
			
		}else if("PieChart".equals(submit)){
			
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
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
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    	
    		listPageDepan = model.getListTotalProjectKementerian_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//context.put("xml", model.generateXMLJumlahHakmilik("Laporan Jumlah Pengambilan Tanah Mengikut Lot"));
			
    		String tajukLaporan = "Laporan Jumlah Pengambilan Tanah Mengikut Lot "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXMLJumlahHakmilik(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		
    		vm = skrinDepanSenaraiLaporan;
       		//DEFAULT
    		context.put("refence", refence);
			
		}else if("Laporan".equals(submit)){
						
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
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
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListTotalProjectKementerian_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		String tajukLaporan = "Laporan Tahunan Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXML(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
			vm = skrinDepanSenaraiLaporan;
       		//DEFAULT
    		context.put("refence", "");
						
    	}else if("search_data".equals(submit)){		
    		
    		id_tahun = getParam("socTahun");
    		id_bulan = getParam("socBulan");
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
    		
			//GET LIST DATA
			listPageDepan = model.getListTotalProjectKementerian_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);

    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		String tajukLaporan = "Laporan Tahunan Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXML(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else { 
			
    		id_tahun = "";
    		id_bulan = "";
    		txdTarikhMula="";
    	    txdTarikhAkhir="";
    		context.put("id_tahun",id_tahun);
    		context.put("id_bulan",id_bulan);
    		
    		//DROP DOWN
    		context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
           	//GET LIST DATA
			//listPageDepan = model.getListTotalProjectKementerian(id_tahun,id_bulan);				
    		listPageDepan = model.getListTotalProjectKementerian_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		
    		String tajukLaporan = "Laporan Tahunan Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXML(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		
		//context.put("xml", model.generateXML());
		//TAB
		this.context.put("selectedTab",selectedTab);
		
		//GET KOD - NAMA NEGERI
		listKod = model.getAbbrev();
		context.put("SenaraiKod", listKod);
		context.put("listKod_size", listKod.size());
    	return vm;
     
		
	}// close doTemplate2
	
// METHOD --------------


	 
}// close class
