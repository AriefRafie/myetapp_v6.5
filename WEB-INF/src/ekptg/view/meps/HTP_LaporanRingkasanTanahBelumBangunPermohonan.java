package ekptg.view.meps;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.HTP_modeldata;

public class HTP_LaporanRingkasanTanahBelumBangunPermohonan extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(HTP_LaporanRingkasanTanahBelumBangunPermohonan.class);
	
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
    	String getJenisLaporan="";
    	String txdTarikhMula="";
    	String txdTarikhAkhir="";
    	 txdTarikhMula=getParam("txdTarikhMula");
      	txdTarikhAkhir=getParam("txdTarikhAkhir");
    	
    	// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/htp/LaporanRingkasanTanahBelumBangunPermohonan.jsp";
    	
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
			getJenisLaporan=getParam("jenis_laporan");
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
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
    		context.put("getJenisLaporan", getJenisLaporan);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan(id_tahun,id_bulan);
    		if(getJenisLaporan.equals("")||getJenisLaporan.equals("negeri")){
				listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}else if(getJenisLaporan.equals("kementerian")){
	    			System.out.println("kementerian");
	    			listPageDepan = model.getListTotalRingkasanTanahBelumBangunPenyewaan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}
    		//DEFAULT
    		context.put("refence", refence);
			
			vm = skrinDepanSenaraiLaporan;
			
		}else if("PieChart".equals(submit)){
			getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			
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
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
    		context.put("getJenisLaporan", getJenisLaporan);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan(id_tahun,id_bulan);
    		if(getJenisLaporan.equals("")||getJenisLaporan.equals("negeri")){
				listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}else if(getJenisLaporan.equals("kementerian")){
	    			System.out.println("kementerian");
	    			listPageDepan = model.getListTotalRingkasanTanahBelumBangunPenyewaan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("LineChart".equals(submit)){
			getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			
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
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
    		context.put("getJenisLaporan", getJenisLaporan);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan(id_tahun,id_bulan);
    		if(getJenisLaporan.equals("")||getJenisLaporan.equals("negeri")){
				listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}else if(getJenisLaporan.equals("kementerian")){
	    			System.out.println("kementerian");
	    			listPageDepan = model.getListTotalRingkasanTanahBelumBangunPenyewaan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
	}else if("PieChart2".equals(submit)){
		     getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			
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
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
    		context.put("getJenisLaporan", getJenisLaporan);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		//listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan(id_tahun,id_bulan);
    		if(getJenisLaporan.equals("")||getJenisLaporan.equals("negeri")){
				listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}else if(getJenisLaporan.equals("kementerian")){
	    			System.out.println("kementerian");
	    			listPageDepan = model.getListTotalRingkasanTanahBelumBangunPenyewaan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("Laporan".equals(submit)){
			getJenisLaporan=getParam("jenis_laporan");
			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");
			
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
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
    		context.put("getJenisLaporan", getJenisLaporan);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//GET LIST DATA
    		//listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan(id_tahun,id_bulan);
    		if(getJenisLaporan.equals("")||getJenisLaporan.equals("negeri")){
				listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}else if(getJenisLaporan.equals("kementerian")){
	    			System.out.println("kementerian");
	    			listPageDepan = model.getListTotalRingkasanTanahBelumBangunPenyewaan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		//DEFAULT
    		context.put("refence", "");
    		
			vm = skrinDepanSenaraiLaporan;
						
    	}else if("search_data".equals(submit)){		
    		
    		id_tahun = getParam("socTahun");
    		id_bulan = getParam("socBulan");
    		getJenisLaporan=getParam("jenis_laporan");
    		System.out.println(getJenisLaporan);
    		
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
    		
			//listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan(id_tahun,id_bulan);
			if(getJenisLaporan.equals("")||getJenisLaporan.equals("negeri")){
				listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}else if(getJenisLaporan.equals("kementerian")){
	    			System.out.println("kementerian");
	    			listPageDepan = model.getListTotalRingkasanTanahBelumBangunPenyewaan_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		}
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
    		context.put("getJenisLaporan", getJenisLaporan);
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else { 
			
    		id_tahun = "";
    		id_bulan = "";
    		getJenisLaporan="negeri";
    		txdTarikhMula="";
    		txdTarikhAkhir="";
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		
    		//DROP DOWN
    		context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("getJenisLaporan",getJenisLaporan);
    		context.put("txdTarikhMula", txdTarikhMula);
    		context.put("txdTarikhAkhir", txdTarikhAkhir);
           	//GET LIST DATA
			listPageDepan = model.getListTotalRingkasanTanahBelumBangunPermohonan(id_tahun,id_bulan);				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size()); 
    		
    		//DEFAULT
    		context.put("refence", "");
    		
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		//context.put("xml", model.generateXMLKementerian("Laporan Ringkasan Tanah Belum Bangun Permohonan"));
		if(getJenisLaporan.equals("")||getJenisLaporan.equals("negeri")){
			String tajukLaporan = "Laporan Ringkasan Tanah Belum Bangun - Permohonan "+(id_tahun.equals("")?"":" ("+id_tahun);
			if(!id_bulan.equals("")){			
				tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
			}
			tajukLaporan += (id_tahun.equals("")?"":")");
			context.put("xml", model.generateXMLKementerian(tajukLaporan));
			
			//GET KOD - NAMA KEMENTERIAN
			listKod = model.getKodKementerian();
			context.put("SenaraiKod", listKod);
			context.put("listKod_size", listKod.size());  
			
			//TAB
			this.context.put("selectedTab",selectedTab);
			this.context.put("tajukLaporan",tajukLaporan);
			
			
		}else if(getJenisLaporan.equals("kementerian")){
		String tajukLaporan = "Laporan Ringkasan Tanah Belum Bangun - Penyewaan "+(id_tahun.equals("")?"":" ("+id_tahun);
		if(!id_bulan.equals("")){			
			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
		}
		tajukLaporan += (id_tahun.equals("")?"":")");
		context.put("xml", model.generateXMLKementerian(tajukLaporan));
		
		//GET KOD - NAMA KEMENTERIAN
		listKod = model.getKodKementerian();
		context.put("SenaraiKod", listKod);
		context.put("listKod_size", listKod.size());  
		
		//TAB
		this.context.put("selectedTab",selectedTab);
		this.context.put("tajukLaporan",tajukLaporan);
		}
		
    	return vm;
     
		
	}// close doTemplate2

	
// METHOD --------------


	 
}// close class
