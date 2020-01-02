package ekptg.view.meps;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.InternalUserUtil;
import ekptg.model.entities.InternalUser;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.meps.IMEPPTNegeri;
import ekptg.model.meps.IMEPUtils;
import ekptg.model.meps.MEPPTNegeriBean;
import ekptg.model.meps.MEPUtilBean;

public class PPTLaporanBulananProjekNegeri extends AjaxBasedModule{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -855108166857901260L;
	static Logger myLog = Logger.getLogger(ekptg.view.meps.PPTLaporanBulananProjekNegeri.class);
 	private IMEPPTNegeri iMep = null;  
 	private IMEPUtils iMepUtil = null;
	private String idSubUrusan = "";			    	
	// MODEL
	//PPT_modeldata model = new PPT_modeldata();	
	
	@Override
	public String doTemplate2() throws Exception{
		
		// DEFAULT
		HttpSession session = request.getSession();		
		String vm = ""; 
    	String doPost = (String)session.getAttribute("doPost");
    	String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
    	String submit = getParam("command"); 
    	String refence = "no";   	
    	// VECTOR
    	Vector listPageDepan = new Vector();
    	Vector listKod = new Vector();
    	listPageDepan.clear();
    	
    	// DECLARE VARIABLES
    	String idTahun = "";
    	String idTahunHingga = "";				
		String userId = (String)session.getAttribute("_ekptg_user_id");
		InternalUser iu = null;	
		// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppt/LaporanPermohonanPengambilanNegeri.jsp";
    	//TAB
    	String selectedTab = "";
		selectedTab = getParam("tabId");	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}		
		myLog.info("SUBMIT :: "+submit);
		myLog.info("SUBMIT :: socSuburusan="+getParam("socSuburusan"));	
		
		idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("17", null
				, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
		iu = InternalUserUtil.getSeksyenId(userId);
		String idNegeri = iu.getIdNegeri();
		vm = skrinDepanSenaraiLaporan;

  		idTahun = getParam("socTahun").equals("")?"":getParam("socTahun");
		idTahunHingga = getParam("socTahunHingga").equals("")?"":getParam("socTahunHingga");
		context.put("id_tahun", idTahun);
		context.put("id_bulan", idTahunHingga);   		
		//DROP DOWN
		context.put("selectTahun",getMepUtils().SelectTahun(this.getTahun(idNegeri),"socTahun" , idTahun, null, "style=width:130px"));
		context.put("selectTahunHingga",getMepUtils().SelectTahun(this.getTahun(idNegeri),"socTahunHingga", idTahunHingga, null, "style=width:130px"));

		if("BarGraph".equals(submit)){ 
			//TAB
    		this.context.put("selectedTab",selectedTab);
    		//DROPDOWN
			listPageDepan = getMep().getJumlahPermohonan(idTahun,idTahunHingga,idNegeri,idSubUrusan);
			//DEFAULT
    		context.put("refence", refence);		
			
		}else if("PieChart".equals(submit)){			
			//TAB
    		this.context.put("selectedTab",selectedTab);
    		//DROPDOWN
			listPageDepan = getMep().getJumlahPermohonan(idTahun,idTahunHingga,idNegeri,idSubUrusan);
			//DEFAULT
    		context.put("refence", refence);
			
		}else if("LineChart".equals(submit)){
    		//TAB
    		this.context.put("selectedTab",selectedTab);
    		//DROPDOWN
			listPageDepan = getMep().getJumlahPermohonan(idTahun,idTahunHingga,idNegeri,idSubUrusan);
       		//DEFAULT
    		context.put("refence", refence);    		
			
		//}else if("PieChart2".equals(submit)){			
			
		}else if("Laporan".equals(submit)){			
    		//TAB
    		this.context.put("selectedTab",selectedTab); 
    		//DROPDOWN
			listPageDepan = getMep().getJumlahPermohonan(idTahun,idTahunHingga,idNegeri,idSubUrusan);
    		//DEFAULT
    		context.put("refence", refence);    		
									
    	}else if("search_data".equals(submit)){		   		
			listPageDepan = getMep().getJumlahPermohonan(idTahun,idTahunHingga,idNegeri,idSubUrusan);
    		//DEFAULT
    		context.put("refence", refence);
     		    		
    	}else { 			
			listPageDepan = getMep().getJumlahPermohonan(idTahun,idTahunHingga,idNegeri,idSubUrusan);
    		//DEFAULT
    		context.put("refence", "");  		
    		  		
    	} 
 		
		//GENERATE BAR & PIE CHART
		String tajukLaporan = "Laporan Jumlah Permohonan Pengambilan Tanah ";
		if(idSubUrusan != null){	
			Tblrujsuburusan f = (Tblrujsuburusan) getMep().getSubUrusan(idSubUrusan);
			tajukLaporan += "("+f.getNamaSuburusan()+") ";			

		}
		if (!idTahun.trim().equals("")) {
			if (!idTahunHingga.trim().equals("")) {
				tajukLaporan = tajukLaporan + "dari tahun " + idTahun +" sehingga "+idTahunHingga;
			}else{
				tajukLaporan = tajukLaporan + "bagi tahun " + idTahun;
			}
		}
		
		context.put("xml", getMep().generateXML(tajukLaporan.toUpperCase()));
		//TAB
		this.context.put("selectedTab",selectedTab);
		this.context.put("tajukLaporan",tajukLaporan.toUpperCase());
		context.put("PermohonanList", listPageDepan);
		context.put("list_size", listPageDepan.size());  	
   		return vm;
     
		
	}// close doTemplate2

	
// METHOD --------------
	private Vector getTahun(String idNegeri) throws Exception {
		Vector v = new Vector();
		Hashtable h = null;
		Vector vecTahun = getMep().getJumlahPermohonan("","",idNegeri,idSubUrusan);
		for (int i = 0; i < vecTahun.size(); i++) {
			Hashtable hTahun = (Hashtable) vecTahun.get(i);
			h = new Hashtable();
			h.put("id",hTahun.get("tahun"));
			h.put("tahun",hTahun.get("tahun"));
			//myLog.info("Tahun="+hTahun.get("tahun"));
			v.addElement(h);
			
		}
		return v;
	
	}
	
	private IMEPPTNegeri getMep(){
		if(iMep==null){
			iMep = new MEPPTNegeriBean();
		}
		return iMep;
				
	}

	private IMEPUtils getMepUtils(){
		if(iMepUtil==null){
			iMepUtil = new MEPUtilBean();
		}
		return iMepUtil;
				
	}
	
	
}// close class
