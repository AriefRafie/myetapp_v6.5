package ekptg.view.meps;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.PPT_modeldata;

public class PPT_LaporanPermohonanPengambilanUnitLot extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(PPT_LaporanPermohonanPengambilanUnitLot.class);
	
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
    	String skrinDepanSenaraiLaporan = "app/meps/ppt/LaporanPermohonanPengambilanUnitLot.jsp";
    	String linkPapar = getParam("linkPapar");    
    	String idNegeri = getParam("idNegeri");
    	
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
    		listPageDepan = model.getListTotalPermohonanUnitLotKod_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("refence", refence);
			
			vm = skrinDepanSenaraiLaporan;
			
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
    		//listPageDepan = model.getListTotalPermohonanUnitLotKod(id_tahun,id_bulan);
    		listPageDepan = model.getListTotalPermohonanUnitLotKod_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
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
    		//listPageDepan = model.getListTotalPermohonanUnitLotKod(id_tahun,id_bulan);
    		listPageDepan = model.getListTotalPermohonanUnitLotKod_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;

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
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//listPageDepan = model.getListTotalPermohonanUnitLotKod(id_tahun,id_bulan);
    		listPageDepan = model.getListTotalPermohonanUnitLotKod_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
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
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//listPageDepan = model.getListTotalPermohonanUnitLot(id_tahun,id_bulan);
    		listPageDepan = model.getListTotalPermohonanUnitLotKod_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("refence", "");
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
			vm = skrinDepanSenaraiLaporan;
						
    	}else if("search_data".equals(submit)){	
    		
    		id_tahun = getParam("socTahun");
    		id_bulan = getParam("socBulan");
			idNegeri = getParam("socNegeri");

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
			if(idNegeri.equals("")){
				idNegeri = "";
			}else{
				idNegeri = getParam("socNegeri");
			}
    		
    		if(linkPapar.equals("viewNegeri")){
    			listPageDepan = model.getListDaerahByNegeri(Long.parseLong(idNegeri),id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		} else {
        		if(idNegeri!=null && idNegeri.length()>0){
            		listPageDepan = model.getListDaerahByNegeri(Long.parseLong(idNegeri),id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        			refence = "no";
        		} else {
        			listPageDepan = model.getListTotalPermohonanUnitLotKod_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}       
    		}

			//GET LIST DATA
			//listPageDepan = model.getListTotalPermohonanUnitLotKod(id_tahun,id_bulan);
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));

    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		context.put("idNegeri", idNegeri);

    		context.put("refence", refence);
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else { 
			
    		id_tahun = "";
    		id_bulan = "";
			idNegeri = "";

			txdTarikhMula ="";
			txdTarikhAkhir ="";
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//DROP DOWN
    		context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));

    		context.put("refence", "");
           	//GET LIST DATA
			//listPageDepan = model.getListTotalPermohonanUnitLot(id_tahun,id_bulan);				
    		listPageDepan = model.getListTotalPermohonanUnitLotKod_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		context.put("idNegeri", idNegeri);

    		
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		//context.put("xml", model.generateXMLCawanganLot());
		
		String tajukLaporan = "Laporan Jumlah Pengambilan Tanah Mengikut Cawangan dan Lot "+(id_tahun.equals("")?"":" ("+id_tahun);
		if(!id_bulan.equals("")){			
			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
		}
		tajukLaporan += (id_tahun.equals("")?"":")");
		
String modelXml = "";
		
		if(linkPapar.equals("viewNegeri")){
			modelXml = model.generateXMLDaerah(tajukLaporan);
			listKod = model.getAbbrevDaerah(Long.parseLong(idNegeri));
		} else {
			modelXml = model.generateXML(tajukLaporan);
			listKod = model.getAbbrev();
		}
		
		context.put("xml", modelXml);
		context.put("SenaraiKod", listKod);
		context.put("listKod_size", listKod.size());
		
		//TAB
		this.context.put("selectedTab",selectedTab);
		this.context.put("tajukLaporan",tajukLaporan);
		this.context.put("linkPapar",linkPapar);
		this.context.put("idNegeri",idNegeri);
		
    	return vm;
     
		
	}// close doTemplate2

	
// METHOD --------------


	 
}// close class
