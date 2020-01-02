package ekptg.view.meps;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.meps.PPT_modeldata;
import ekptg.ppt.helpers.HTMLPPT;

public class PPT_LaporanPenarikanBalik extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(PPT_LaporanPenarikanBalik.class);
	
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
		String idNegeri = "";
    	
    	// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppt/LaporanPenarikanBalik.jsp";
    	
    	//TAB
    	String selectedTab = "";
		selectedTab = getParam("tabId");	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		myLogger.info("SUBMIT :: "+submit);
	
		if("BarGraph".equals(submit)){ 
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
    		//context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalPenarikanBalikDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir, idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalPenarikanBalikNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalPenarikanBalikKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
	    	}
			
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("PieChart".equals(submit)){
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
    		//context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put(" txdTarikhMula",  txdTarikhMula);
    		this.context.put(" txdTarikhAkhir",  txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalPenarikanBalikDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir, idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalPenarikanBalikNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalPenarikanBalikKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
	    	}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("LineChart".equals(submit)){
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
    		//context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalPenarikanBalikDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir, idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalPenarikanBalikNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalPenarikanBalikKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
	    	}
//    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
//    			listPageDepan = model.getListTotalPenarikanBalikNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
//	    		String tajukLaporan = "Laporan Penarikan Balik Mengikut Negeri "+(id_tahun.equals("")?"":" ("+id_tahun);
//	    		if(!id_bulan.equals("")){			
//	    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
//	    		}
//	    		tajukLaporan += (id_tahun.equals("")?"":")");
//	    		context.put("xml", model.generateXMLPenarikanBalikLineChartNegeri(tajukLaporan));
//	    		this.context.put("tajukLaporan",tajukLaporan);
//	    		}else if(getJenisLaporan.equals("kementerian")) {
//	    			listPageDepan = model.getListTotalPenarikanBalikKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
//	    		String tajukLaporan = "Laporan Penarikan Balik Mengikut Mengikut Kementerian "+(id_tahun.equals("")?"":" ("+id_tahun);
//	    		if(!id_bulan.equals("")){			
//	    			tajukLaporan += " - "+ekptg.report.Utils.getBulan(Integer.parseInt(id_bulan));			
//	    		}
//	    		tajukLaporan += (id_tahun.equals("")?"":")");
//	    		context.put("xml", model.generateXMLPenarikanBalikLineChartKementerian(tajukLaporan));
//	    		this.context.put("tajukLaporan",tajukLaporan);
//	    		}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
		}else if("PieChart2".equals(submit)){
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
    		//context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
//    		listPageDepan = model.getListTotalProjectKementerianAbbrev_baru(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalPenarikanBalikDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir, idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalPenarikanBalikNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalPenarikanBalikKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
	    	}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence", refence);
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("Laporan".equals(submit)){
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
    		//context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
    		//context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(id_bulan), null, "style=width:130px"));
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
			this.context.put("selectNegeri",
					HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			this.context.put("idNegeri", idNegeri);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalPenarikanBalikDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir, idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalPenarikanBalikNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalPenarikanBalikKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
	    	}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
//			context.put("xml", model.generateXML(tajukLaporan));
    		
       		//DEFAULT
    		context.put("refence", "");
    		
			vm = skrinDepanSenaraiLaporan;
						
    	}else if("search_data".equals(submit)){	
    		
    		txdTarikhMula = getParam("txdTarikhMula");
    		txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
    		getJenisLaporan=getParam("jenis_laporan");
    		
    		if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("socNegeri");
			}
    		
			//GET LIST DATA			
    		if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalPenarikanBalikDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir, idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalPenarikanBalikNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalPenarikanBalikKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
	    	}
    		
    		//DATA & SIZE DEFAULT LIST
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
    		//MAINTAIN SEARCHING VALUES
    		this.context.put("selectNegeri",HTMLPPT.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
    		this.context.put("idNegeri", idNegeri);
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
    		this.context.put("refence", refence);
    		
       		//DEFAULT
     		vm = skrinDepanSenaraiLaporan;
    		
    	}else {
    		
			idNegeri = "";
			getJenisLaporan="negeri";
			
    		//DROP DOWN
    		this.context.put("txdTarikhMula",txdTarikhMula);
    		this.context.put("txdTarikhAkhir",txdTarikhAkhir);
    		this.context.put("getJenisLaporan",getJenisLaporan);
			this.context.put("idNegeri", idNegeri);
			this.context.put("selectNegeri", HTMLPPT.SelectNegeri("socNegeri", null, null, "style=width:130px"));
    		
           	//GET LIST DATA
//			listPageDepan = model.getListTotalPenarikanBalikNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
			if(getJenisLaporan.equals("")||(getJenisLaporan.equals("negeri"))){
    			if(idNegeri != null && idNegeri.length() > 0) {
    				listPageDepan = model.getListTotalPenarikanBalikDaerah(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir, idNegeri);
    				refence = "no";
    			} else {
    				listPageDepan = model.getListTotalPenarikanBalikNegeri(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);
    			}
	    	}else if(getJenisLaporan.equals("kementerian")) {
	    		listPageDepan = model.getListTotalPenarikanBalikKementerian(id_tahun,id_bulan,txdTarikhMula,txdTarikhAkhir);	
	    	}
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());
    		
       		//DEFAULT
    		context.put("refence", "");  		
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//GENERATE BAR & PIE CHART
		String tajukLaporan="";
		if (getJenisLaporan.equals("negeri") || getJenisLaporan.equals("")){
			if (idNegeri!=null && idNegeri.length()>0){
    			tajukLaporan = "Laporan Penarikan Balik Mengikut Daerah ";
    		} else {
    			tajukLaporan = "Laporan Penarikan Balik Mengikut Negeri ";
    		}
		} else if (getJenisLaporan.equals("kementerian")){
			tajukLaporan = "Laporan Penarikan Balik Mengikut Kementerian ";
		}
		if (!txdTarikhMula.trim().equals("")&&!txdTarikhAkhir.trim().equals("")) {
			tajukLaporan += " (" + txdTarikhMula +" Hingga "+ txdTarikhAkhir +")";
		}
	
		if(getJenisLaporan.equals("negeri") || getJenisLaporan.equals("")) {
			if (idNegeri!=null && idNegeri.length()>0){
				context.put("xml", model.generateXMLPenarikanBalikDaerah(tajukLaporan));
    		} else {
    			context.put("xml", model.generateXMLPenarikanBalikNegeri(tajukLaporan));
    		}
		} else if(getJenisLaporan.equals("kementerian")) {
			context.put("xml", model.generateXMLPenarikanBalikKementerian(tajukLaporan));
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
