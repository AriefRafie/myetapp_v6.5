package ekptg.view.meps;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.PPT_modeldata;
import ekptg.ppt.helpers.HTMLPPT;

public class PPT_CajBayaranLewat extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(PPT_CajBayaranLewat.class);
	
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
    	
    	myLogger.info("submit :: "+submit);
    	
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
    	String skrinDepanSenaraiLaporan = "app/meps/ppt/LaporanCajBayaranLewat.jsp";
    	String linkPapar = getParam("linkPapar");    
    	String idNegeri = getParam("idNegeri");
    	
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
			
			if(idNegeri.equals("")){
				idNegeri = "";
			}else{
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		
    		//TAB
    		this.context.put("selectedTab",selectedTab);
    		context.put("idNegeri", idNegeri);
 
    		//GET LIST DATA
    		if(linkPapar.equals("viewNegeri")){
    			listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		} else {
        		if(idNegeri!=null && idNegeri.length()>0){
            		listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        			refence = "no";
        		} else {
        			listPageDepan = model.getListCajBayaranLewatAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}       
    		}
    		//listPageDepan = model.getListCajBayaranLewatAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
			
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
			
			if(idNegeri.equals("")){
				idNegeri = "";
			}else{
				idNegeri = getParam("socNegeri");
			}
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
    		context.put("idNegeri", idNegeri);
 
    		//GET LIST DATA
    		if(linkPapar.equals("viewNegeri")){
    			listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		} else {
        		if(idNegeri!=null && idNegeri.length()>0){
            		listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        			refence = "no";
        		} else {
        			listPageDepan = model.getListCajBayaranLewatAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}       
    		}
    		//listPageDepan = model.getListCajBayaranLewatAbbrev(id_tahun,id_bulan);
    		//listPageDepan = model.getListCajBayaranLewatAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
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
			
			if(idNegeri.equals("")){
				idNegeri = "";
			}else{
				idNegeri = getParam("socNegeri");
			}
			
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
    		context.put("idNegeri", idNegeri);
 
    		//GET LIST DATA
    		if(linkPapar.equals("viewNegeri")){
    			listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		} else {
        		if(idNegeri!=null && idNegeri.length()>0){
            		listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        			refence = "no";
        		} else {
        			listPageDepan = model.getListCajBayaranLewatAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}       
    		}
    		//listPageDepan = model.getListCajBayaranLewatAbbrev(id_tahun,id_bulan);
    		
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
				
				if(idNegeri.equals("")){
					idNegeri = "";
				}else{
					idNegeri = getParam("socNegeri");
				}
	    		//MAINTAIN SEARCHING VALUES
	    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
	    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
	    		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
	    		this.context.put("txdTarikhMula",txdTarikhMula);
	    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
	    		//TAB
	    		this.context.put("selectedTab",selectedTab);
	    		context.put("idNegeri", idNegeri);
	 
	    		//GET LIST DATA
	    		if(linkPapar.equals("viewNegeri")){
	    			listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	    		} else {
	        		if(idNegeri!=null && idNegeri.length()>0){
	            		listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	        			refence = "no";
	        		} else {
	        			listPageDepan = model.getListCajBayaranLewatAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
	        		}       
	    		}
	    		//listPageDepan = model.getListCajBayaranLewatAbbrev(id_tahun,id_bulan);
	    		//listPageDepan = model.getListCajBayaranLewatAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
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
			
			if(idNegeri.equals("")){
				idNegeri = "";
			}else{
				idNegeri = getParam("socNegeri");
			}
    		//MAINTAIN SEARCHING VALUES
    		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
    		context.put("idNegeri", idNegeri);
 
    		//GET LIST DATA
    		if(linkPapar.equals("viewNegeri")){
    			listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		} else {
        		if(idNegeri!=null && idNegeri.length()>0){
            		listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        			refence = "no";
        		} else {
        			listPageDepan = model.getListCajBayaranLewatAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}       
    		}
    		//listPageDepan = model.getListCajBayaranLewat(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		context.put("refence", "");
    		
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
    			listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		} else {
        		if(idNegeri!=null && idNegeri.length()>0){
            		listPageDepan = model.getListDaerahByNegeriCajBayaranLewatAbbrev_add(idNegeri,id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        			refence = "no";
        		} else {
        			listPageDepan = model.getListCajBayaranLewatAbbrev_add(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
        		}       
    		}

			//GET LIST DATA
			//listPageDepan = model.getListCajBayaranLewatAbbrev(id_tahun,id_bulan);
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

    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else { 
			
    		id_tahun = "";
    		id_bulan = "";
			idNegeri = "";

    		txdTarikhMula="";
        	txdTarikhAkhir = "";	
    		context.put("id_tahun", id_tahun);
    		context.put("id_bulan", id_bulan);
    		
    		//DROP DOWN
    		context.put("selectTahun",HTML.SelectTahun("socTahun", id_tahun, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));

    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
           	//GET LIST DATA
			listPageDepan = model.getListCajBayaranLewat(id_tahun,id_bulan);				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		context.put("refence", "");  		
    		context.put("idNegeri", idNegeri);
    		
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		//context.put("xml", model.generateXML("Laporan Caj Bayaran Lewat Pengambilan Tanah"));
		
		String tajukLaporan = "Laporan Caj Bayaran Lewat Pengambilan Tanah "+(id_tahun.equals("")?"":" ("+id_tahun);
		if(!id_bulan.equals("")){			
			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
		}
		if (!txdTarikhMula.trim().equals("")&&!txdTarikhAkhir.trim().equals("") ) {
			tajukLaporan = tajukLaporan + "dari  " + txdTarikhMula +" sehingga "+ txdTarikhAkhir;
		}
		tajukLaporan += (id_tahun.equals("")?"":")");
//String modelXml = "";
		
//		if(linkPapar.equals("viewNegeri")){
//			modelXml = model.generateXMLDaerah_add(tajukLaporan);
//			listKod = model.getAbbrevDaerah_add(Long.parseLong(idNegeri));
//		} else {
//			modelXml = model.generateXML(tajukLaporan);
//			listKod = model.getAbbrev();
//		}
		if(idNegeri !=null && idNegeri.length()>0){
			context.put("xml", model.generateXMLDaerah_add(tajukLaporan));
		} else {
			context.put("xml", model.generateXML(tajukLaporan));
		}
		
		
//		context.put("xml", modelXml);
		listKod = model.getAbbrev();
		context.put("SenaraiKod", listKod);
		context.put("listKod_size", listKod.size());
		
		//TAB
		this.context.put("selectedTab",selectedTab);
		this.context.put("tajukLaporan",tajukLaporan);
//		this.context.put("linkPapar",linkPapar);
//		this.context.put("idNegeri",idNegeri);		
    	return vm;
     
		
	}// close doTemplate2

	
// METHOD --------------


	 
}// close class
