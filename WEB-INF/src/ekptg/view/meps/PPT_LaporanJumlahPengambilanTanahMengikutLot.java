package ekptg.view.meps;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.PPT_modeldata;
import ekptg.ppt.helpers.HTMLPPT;

public class PPT_LaporanJumlahPengambilanTanahMengikutLot extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(PPT_LaporanJumlahPengambilanTanahMengikutLot.class);
	
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
    	String skrinDepanSenaraiLaporan = "app/meps/ppt/LaporanJumlahPengambilanTanahMengikutLot.jsp";
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
			idNegeri = getParam("socNegeri");
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
			context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
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
			
	//add faresh
		}else if("BarGraph2".equals(submit)){
			idNegeri = getParam("socNegeri");
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
			context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//context.put("xml", model.generateXMLfaresh("Laporan Jumlah Permohonan dan Jumlah Lot Pengambilan Tanah"));
			
    		String tajukLaporan = "Laporan Jumlah Permohonan dan Jumlah Lot Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		context.put("xml", model.generateXMLfaresh(tajukLaporan));
    		//myLogger.info("XML ::::::::::::: "+model.generateXMLfaresh(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		
    		vm = skrinDepanSenaraiLaporan;
       		//DEFAULT
    		context.put("refence", refence);
			
}else if("PieChart".equals(submit)){
	idNegeri = getParam("socNegeri");
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
			context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
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
			
		}else if("PieChart2".equals(submit)){
			idNegeri = getParam("socNegeri");
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
			context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    	     listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		//context.put("xml", model.generateXMLJumlahHakmilik("Laporan Jumlah Permohonan dan Jumlah Lot Pengambilan Tanah"));
			
    		String tajukLaporan = "Laporan Jumlah Permohonan dan Jumlah Lot Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
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
	idNegeri = getParam("socNegeri");
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
			context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListTotalProjectKementerianAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
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

	}else if("LineChart2".equals(submit)){
		idNegeri = getParam("socNegeri");
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
		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
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
		
		String tajukLaporan = "Laporan Jumlah Permohonan dan Jumlah Lot Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
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
		idNegeri = getParam("socNegeri");
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
		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
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
			idNegeri = getParam("socNegeri");
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
			context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
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
			idNegeri = getParam("socNegeri");
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
			context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
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
    		String tajukLaporan = "Laporan Jumlah Pengambilan Tanah Mengikut Lot "+(id_tahun.equals("")?"":" ("+id_tahun);
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
        			listPageDepan = model.getListTotalProjectKementerian_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}       
    		}
			//GET LIST DATA

    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		context.put("idNegeri", idNegeri);
    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
    		context.put("refence", refence);
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else { 
			
    		id_tahun = "";
    		id_bulan = "";
    		txdTarikhMula="";
    	    txdTarikhAkhir="";
			idNegeri = "";

    		context.put("id_tahun",id_tahun);
    		context.put("id_bulan",id_bulan);
    		
    		//DROP DOWN
    		context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));

    		this.context.put("txdTarikhMula",  txdTarikhMula);
    		this.context.put("txdTarikhAkhir",  txdTarikhAkhir);
           	//GET LIST DATA
			//listPageDepan = model.getListTotalProjectKementerian(id_tahun,id_bulan);				
    		listPageDepan = model.getListTotalProjectKementerian_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		
    		String tajukLaporan = "Laporan Jumlah Pengambilan Tanah Mengikut Lot "+(id_tahun.equals("")?"":" ("+id_tahun);
    		if(!id_bulan.equals("")){			
    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
    		}
    		tajukLaporan += (id_tahun.equals("")?"":")");
    		//context.put("xml", model.generateXML(tajukLaporan));
    		this.context.put("tajukLaporan",tajukLaporan);
    		context.put("refence", "");  		
    		context.put("idNegeri", idNegeri);
    	
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		String tajukLaporan = "Laporan Jumlah Pengambilan Tanah Mengikut Lot "+(id_tahun.equals("")?"":" ("+id_tahun);
		if(!id_bulan.equals("")){			
			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
		}
		tajukLaporan += (id_tahun.equals("")?"":")");		
		//context.put("xml", model.generateXML());
		
		
		
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
