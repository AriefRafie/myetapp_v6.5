package ekptg.view.meps;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.PPT_modeldata;

public class PPT_LaporanStatusPengambilan extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(PPT_LaporanStatusPengambilan.class);
	
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
    	// SKRIN JSP  
    	String skrinDepanSenaraiLaporan = "app/meps/ppt/LaporanStatusPengambilan.jsp";
    	
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
    		//listPageDepan = model.getListStatusPengambilanAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		listPageDepan = model.getListStatusPengambilan_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("xml", model.generateXML_MSColumn2D(id_tahun,id_bulan,"Laporan Status Pengambilan Tanah"));

    		String tajukLaporan = "Laporan Status Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXML_MSColumn2D(id_tahun,id_bulan,tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		vm = skrinDepanSenaraiLaporan;
    		context.put("refence", refence);
    		
		}
		else if("LineChart2".equals(submit)){
			
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
    		//listPageDepan = model.getListStatusPengambilanAbbrev(id_tahun,id_bulan);
    		//listPageDepan = model.getListStatusPengambilanAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//context.put("xml", model.generateXML_Status_Pengambilan(id_tahun,id_bulan,"Laporan Status Pengambilan Tanah"));
    		listPageDepan = model.getListStatusPengambilan_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
			
    		String tajukLaporan = "Laporan Status Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXML_Status_Pengambilan(id_tahun,id_bulan,tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		
    		vm = skrinDepanSenaraiLaporan;
    		context.put("refence", refence);
    		
		}
	//	}else if("PieChart".equals(submit)){
			
//			id_tahun = getParam("id_tahun");
//			id_bulan = getParam("id_bulan");
//			
//			if(id_tahun.equals("")){
//				id_tahun = "";
//			}else{
//				id_tahun = getParam("socTahun");
//			}
//			
//			if(id_bulan.equals("")){
//				id_bulan = "";
//			}else{
//				id_bulan = getParam("socBulan");
//	    		if (id_bulan.length() < 2)
//	    			id_bulan = "0" + id_bulan;
//			}
//			
//    		//MAINTAIN SEARCHING VALUES
//    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
//    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
//    		
//    		//TAB
//    		this.context.put("selectedTab",selectedTab);
// 
//    		//DROPDOWN
//    		//listPageDepan = model.getListStatusPengambilan(id_tahun,id_bulan);
//    		
			//vm = skrinDepanSenaraiLaporan;
			
		else if("Laporan".equals(submit)){
			
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
    		
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListStatusPengambilan_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    	//istPageDepan = model.getListStatusPengambilanAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		context.put("refence", "");
    		
			vm = skrinDepanSenaraiLaporan;
						
    	}else if("search_data".equals(submit)){		
    		
			id_tahun = getParam("socTahun");
			id_bulan = getParam("socBulan");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			System.out.println(txdTarikhMula);
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
    		
			//listPageDepan = model.getListStatusPengambilanAbbrev(id_tahun,id_bulan);
			//listPageDepan = model.getListStatusPengambilanAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
			listPageDepan = model.getListStatusPengambilan_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else { 
			
    		id_tahun = "";
    		id_bulan = "";
    		//txdTarikhMula="";
        	//txdTarikhAkhir = "";	
    		
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		
    		//DROP DOWN
    		context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		
           	//GET LIST DATA
			//listPageDepan = model.getListStatusPengambilan(id_tahun,id_bulan);				
    		listPageDepan = model.getListStatusPengambilan_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		//context.put("txdTarikhMula", txdTarikhMula);
    		//context.put("txdTarikhAkhir", txdTarikhAkhir);
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		//context.put("xml", model.generateXML_MSColumn2D(id_tahun,id_bulan));
		//GET KOD - NAMA NEGERI
		listKod = model.getAbbrev();
		context.put("SenaraiKod", listKod);
		context.put("listKod_size", listKod.size());
		//TAB
		this.context.put("selectedTab",selectedTab);
		
    	return vm;
     
		
	}// close doTemplate2

	
// METHOD --------------


	 
}// close class
