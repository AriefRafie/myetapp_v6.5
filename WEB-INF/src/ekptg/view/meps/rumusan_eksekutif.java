package ekptg.view.meps;

/* 	NORZAILY JASMI
 * 02 NOV 2010
*/

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.meps.PPT_modeldata;

public class rumusan_eksekutif extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4064587315444550295L;
	static Logger myLogger = Logger.getLogger(rumusan_eksekutif.class);
	
	// MODEL
	PPT_modeldata model = new PPT_modeldata();	
	
	public String doTemplate2() throws Exception{
		// DEFAULT
		HttpSession session = request.getSession();		
		String vm = ""; 
    	String doPost = (String)session.getAttribute("doPost");
    	String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
    	String submit = getParam("command"); 
    	
    	myLogger.info("SUBMIT :: "+submit);
    	
    	// VECTOR
    	Vector listPageDepan = new Vector();    
    	listPageDepan.clear();
    	
    	Vector listPageDepanHTP = new Vector();
    	listPageDepanHTP.clear();
    	
    	Vector listPageDepanRizab = new Vector();
    	listPageDepanRizab.clear();    	    	
    	
    	Vector listPageDepanPHP = new Vector();
    	listPageDepanPHP.clear();
    	
    	// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/Laporan_RumusanEksekutif.jsp";
    	
    	//TAB
    	String selectedTab = "";
		selectedTab = getParam("tabId");	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		
		myLogger.info("TAB :: "+selectedTab);
    	
		if("htp".equals(submit)){ 
			
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DATA TANAH MILIK
//    		listPageDepanHTP = model.getListRumusanHTP();    		
//    		context.put("PermohonanListHTP", listPageDepanHTP);
//    		context.put("list_sizeHTP", listPageDepanHTP.size());    		
    		//DATA TANAH RIZAB
//    		listPageDepanRizab = model.getListRumusanTanahRizab();    		
//    		context.put("PermohonanListRizab", listPageDepanRizab);
//    		context.put("list_sizeRizab", listPageDepanRizab.size()); 
       		listPageDepanHTP = model.getSenaraiRumusanTanah();    		
    		context.put("PermohonanListHTP", listPageDepanHTP);
    		context.put("list_sizeHTP", listPageDepanHTP.size()); 			
			vm = skrinDepanSenaraiLaporan;
			
		}else if("php".equals(submit)){
			
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepanPHP = model.getListRumusanPHP();
    		
    		context.put("PermohonanListPHP", listPageDepanPHP);
    		context.put("list_sizePHP", listPageDepanPHP.size());    		
    		
			vm = skrinDepanSenaraiLaporan;
			
		}else if("PengambilanTanah".equals(submit)){
			
    		//TAB
    		this.context.put("selectedTab",selectedTab);
 
    		//DROPDOWN
    		listPageDepan = model.getListRumusan();
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  
    		
			vm = skrinDepanSenaraiLaporan;				
    		
    	}else { 
			  		
           	//GET LIST DATA
			listPageDepan = model.getListRumusan();				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		
    		vm = skrinDepanSenaraiLaporan;    		
    	}   
		
		//TAB
		this.context.put("selectedTab",selectedTab);
		
    	return vm;
	}
	
	
}
