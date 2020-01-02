package ekptg.view.meps;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.meps.IMEPPTNegeri;
import ekptg.model.meps.IMEPUmum;
import ekptg.model.meps.IMEPUtils;
import ekptg.model.meps.MEPPKNegeriWarisBean;
import ekptg.model.meps.MEPPTNegeriBean;
import ekptg.model.meps.MEPUtilBean;

public class PPKLaporanJumlahNilaianHartaselesai extends AjaxBasedModule{	
	/**
	 * 
	 */
	private IHtp iHTP = null;  
 	private IMEPUmum iMep = null;  
 	private IMEPUtils iMepUtil = null;
 	private IMEPPTNegeri iMepPPT = null;
	private static final long serialVersionUID = -855108166857901260L;
	static Logger myLog = Logger.getLogger(PPKLaporanJumlahNilaianHartaselesai.class);
	private String idSubUrusan = "";			    	
	
	@Override
	public String doTemplate2() throws Exception{	
		// DEFAULT
		HttpSession session = request.getSession();		
		String vm = ""; 
    	String submit = getParam("command"); 
    	String refence = "no";   	
    	// VECTOR
    	Vector listPageDepan = new Vector();
    	Vector listKod = new Vector();
    	listPageDepan.clear();    	
    	// DECLARE VARIABLES
       	String idTahun = "";
    	String idTahunHingga = "";			
      	String idBulan = "";
    	String idBulanHingga = "";
    	
    	String ID_NEGERI = "";
		String ID_PEJABATJKPTG = "";
		context.put("ID_NEGERI", "");
		context.put("ID_PEJABATJKPTG", "");
    	
		String userId = (String)session.getAttribute("_ekptg_user_id");
		if(userId == null){
			throw new Exception(getIHTP().getErrorHTML("[MODUL MEP] SILA LOGIN SEMULA"));
		}
		InternalUser iu = null;	
		// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppk/LaporanNilaianHarta(selesai).jsp";
    	//TAB
    	String selectedTab = "";
		selectedTab = getParam("tabId");	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}		
		myLog.info("SUBMIT :: "+submit);
		myLog.info("SUBMIT :: socSuburusan="+getParam("socSuburusan"));	
		myLog.info("SUBMIT :: selectedTab="+selectedTab);	
		
		idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
		iu = InternalUserUtil.getSeksyenId(userId);
		String idNegeri = iu.getIdNegeri();
		vm = skrinDepanSenaraiLaporan;

//		if("BarGraph".equals(submit)){ 
//		}else if("BarGraph2".equals(submit)){
//		}else if("PieChart".equals(submit)){
//		}else if("PieChart2".equals(submit)){
//		}else if("LineChart".equals(submit)){
//		}else if("LineChart2".equals(submit)){
//		}else if("AreaChart".equals(submit)){
//		}else if("PieChart".equals(submit)){
//		}else if("Laporan".equals(submit)){					
//			myLog.info("Laporan");
//			listPageDepan = getMep().getJumlahHarta(id_tahun,idNegeri);
//    		//    	}else if("search_data".equals(submit)){		
//    	}else { 			
    		//myLog.info("SUBMIT :: "+submit);
    		idTahun = getParam("socTahun").equals("")?"":getParam("socTahun");
    		idTahunHingga = getParam("socTahunHingga").equals("")?"":getParam("socTahunHingga");
    		context.put("id_tahun", idTahun);
    		context.put("idTahunHingga", idTahunHingga); 
			idBulan = getParam("socBulan");
			idBulanHingga = getParam("socBulanHingga");
			if(idBulan.equals("")){
				idBulan = "";
			}else{
				idBulan = getParam("socBulan");
	    		if (idBulan.length() < 2)
	    			idBulan = "0" + idBulan;
			}			
			String idBulanHingga_ = "";
			if(idBulanHingga.equals("")){
				idBulanHingga = "";
				idBulanHingga_ = idBulanHingga; 
			}else{
				idBulanHingga = getParam("socBulanHingga");
				idBulanHingga_ = idBulanHingga; 
	    		if (idBulanHingga.length() < 2){
	    			idBulanHingga = "0" + idBulanHingga;
					idBulanHingga_ = "0" + (Integer.parseInt(idBulanHingga)+1); 
	    		}
	    	}    		
			context.put("id_bulan", idBulan); 
    		context.put("idBulanHingga", idBulanHingga); 

    		//DROP DOWN
    		context.put("selectTahun",getMepUtils().SelectTahun(this.getTahun(idNegeri),"socTahun" , idTahun, null, "style=width:130px"));
    		context.put("selectTahunHingga",getMepUtils().SelectTahun(this.getTahun(idNegeri),"socTahunHingga", idTahunHingga, null, "style=width:130px"));
    		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(idBulan), null, "style=width:130px"));
    		context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga", Utils.parseLong(idBulanHingga), null, "style=width:130px"));
           	//GET LIST DATA
			listPageDepan = getMep().getJumlahPermohonan(idTahun,idTahunHingga,idNegeri,idSubUrusan);
			if(!idBulan.equals("")){
				listPageDepan = getMep().getJumlahPermohonan(ID_NEGERI, ID_PEJABATJKPTG,idTahun,idTahunHingga,idBulan,idBulanHingga_,idSubUrusan);
			}
			
//    	}   
   		this.context.put("PermohonanList", listPageDepan);
		this.context.put("list_size", listPageDepan.size());  	
		
		//GENERATE BAR & PIE CHART
		//TAB
		this.context.put("selectedTab",selectedTab);		
		String tajukLaporan = "Laporan Nialain Harta(Selesai) ";
		if(idSubUrusan != null){	
			Tblrujsuburusan f = (Tblrujsuburusan) getMepPPT().getSubUrusan(idSubUrusan);
			//tajukLaporan += "("+f.getNamaSuburusan()+") ";			
			tajukLaporan += ""+f.getNamaSuburusan()+" ";			

		}
		if (!idTahun.trim().equals("")) {
			if (!idTahunHingga.trim().equals("")) {
				tajukLaporan = tajukLaporan + "dari tahun " + idTahun +" sehingga "+idTahunHingga;
			}else{
				tajukLaporan = tajukLaporan + "bagi tahun " + idTahun;
			}
		}
		if(!submit.equals("")){
			context.put("xml", getMepUtils().generateXML(getMep().getSQL(),tajukLaporan.toUpperCase()));
		}
		//TAB
		this.context.put("selectedTab",selectedTab);
		this.context.put("tajukLaporan",tajukLaporan.toUpperCase());
		context.put("refence", refence);		
	    this.context.put("EkptgUtil_", new ekptg.helpers.Utils());
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
			v.addElement(h);
			
		}
		return v;
	
	}
	
	private IMEPUmum getMep(){
		if(iMep==null){
			iMep = new MEPPKNegeriWarisBean();
		}
		return iMep;
				
	}

	private IMEPUtils getMepUtils(){
		if(iMepUtil==null){
			iMepUtil = new MEPUtilBean();
		}
		return iMepUtil;
				
	}
	
	private IMEPPTNegeri getMepPPT(){
		if(iMepPPT==null){
			iMepPPT = new MEPPTNegeriBean();
		}
		return iMepPPT;
				
	} 
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}		  
	

}// close class
