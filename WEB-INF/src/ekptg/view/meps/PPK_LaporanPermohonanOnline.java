package ekptg.view.meps;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.PPK_modeldata;

public class PPK_LaporanPermohonanOnline extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(PPK_LaporanPermohonanOnline.class);
	
	// MODEL
	PPK_modeldata model = new PPK_modeldata();	
	
	@Override
	public String doTemplate2() throws Exception{
		
		// DEFAULT
		HttpSession session = request.getSession();		
		String vm = ""; 
    	String doPost = (String)session.getAttribute("doPost");
    	String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
    	String submit = getParam("command");   
    	String refence = "yes";
    	
    	myLogger.info("SUBMIT ::" +submit);
    	
    	// VECTOR
    	Vector listPageDepan = new Vector();
    	Vector listKod = new Vector();
    	
    	listPageDepan.clear();
    	
    	// DECLARE VARIABLES
    	String id_tahun = "";
    	String id_bulan = "";			
    	String idTahunHingga = "";
    	String idBulanHingga = "";		
    	
    	// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppk/LaporanPermohonanOnline.jsp";
    	
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
    		context.put("selectTahunHingga",HTML.SelectTahun("socTahunHingga",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulan), null, "style=width:130px"));
    	
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//GET LIST DATA
    		listPageDepan = model.getPermohonanOnline(id_tahun,id_bulan,idTahunHingga,idBulanHingga);
			
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("PieChart".equals(submit)){
			
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
    		context.put("selectTahunHingga",HTML.SelectTahun("socTahunHingga",id_tahun, null, "style=width:130px"));
    		context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//GET LIST DATA
    		listPageDepan = model.getPermohonanOnline(id_tahun,id_bulan,idTahunHingga,idBulanHingga);
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;

		}else if("LineChart".equals(submit)){
			
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
    		context.put("selectTahunHingga",HTML.SelectTahun("socTahunHingga",id_tahun, null, "style=width:130px"));
    		context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//GET LIST DATA
    		listPageDepan = model.getPermohonanOnline(id_tahun,id_bulan,idTahunHingga,idBulanHingga);
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("PieChart2".equals(submit)){
			
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
    		context.put("selectTahunHingga",HTML.SelectTahun("socTahunHingga",id_tahun, null, "style=width:130px"));
    		context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//GET LIST DATA
    		listPageDepan = model.getPermohonanOnline(id_tahun,id_bulan,idTahunHingga,idBulanHingga);
    		
    		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("Laporan".equals(submit)){
			
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
    		context.put("selectTahunHingga",HTML.SelectTahun("socTahunHingga",id_tahun, null, "style=width:130px"));
    		context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//GET LIST DATA
    		listPageDepan = model.getPermohonanOnline(id_tahun,id_bulan,idTahunHingga,idBulanHingga);
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
			vm = skrinDepanSenaraiLaporan;
			
    		//DEFAULT
    		context.put("refence", "");
						
    	}else if("search_data".equals(submit)){		
    		
    		id_tahun = getParam("socTahun");
    		id_bulan = getParam("socBulan");
    		idBulanHingga = getParam("socBulanHingga");
    		idTahunHingga = getParam("socTahunHingga");
    		
    		
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
			
			
			if(idBulanHingga.equals("")){
				idBulanHingga = "";
			}else{
				idBulanHingga = getParam("socBulanHingga");
	    		if (idBulanHingga.length() < 2)
	    			idBulanHingga = "0" + idBulanHingga;
			}
    		
			//GET LIST DATA
			listPageDepan = model.getPermohonanOnline(id_tahun,id_bulan,idTahunHingga,idBulanHingga);

    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectTahunHingga",HTML.SelectTahun("socTahunHingga",idTahunHingga, null, "style=width:130px"));
    		context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga", Utils.parseLong(idBulanHingga), null, "style=width:130px"));
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		context.put("idBulanHingga",idBulanHingga);
    		context.put("idTahunHingga", idBulanHingga);
    		
     		vm = skrinDepanSenaraiLaporan;
    		//DEFAULT
    		context.put("refence", refence);
    		
    	}else { 
			
    		id_tahun = "";
    		id_bulan = "";
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		
    		//DROP DOWN
    		context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectTahunHingga",HTML.SelectTahun("socTahunHingga",id_tahun, null, "style=width:130px"));
    		context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulan), null, "style=width:130px"));
           	//GET LIST DATA
    		listPageDepan = model.getPermohonanOnline(id_tahun,id_bulan,idTahunHingga,idBulanHingga);				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    	
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		//context.put("xml", model.generateXML("Laporan Jumlah Bayaran Baitulmal Pusaka Kecil"));
		
		String tajukLaporan = "Laporan Permohonan Online ";
//		if(!id_bulan.equals("")){			
//			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
//		}

//		tajukLaporan += (id_tahun.equals("")?"":")");
		
		if (!id_tahun.trim().equals("")) {
			if (!idTahunHingga.trim().equals("")) {
				tajukLaporan = tajukLaporan + "dari tahun " + id_tahun +" sehingga "+ idTahunHingga ;
			}else{
				tajukLaporan = tajukLaporan + "bagi tahun " + id_tahun;
			}
		}
		
		if (!id_bulan.trim().equals("")) {
			if (!idBulanHingga.trim().equals("")) {
				tajukLaporan = tajukLaporan + "dari bulan " + id_bulan +" sehingga "+ idBulanHingga ;
			}else{
				tajukLaporan = tajukLaporan + "bagi bulan " + id_bulan;
			}
		}
		context.put("xml", model.generateXML(tajukLaporan));
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
