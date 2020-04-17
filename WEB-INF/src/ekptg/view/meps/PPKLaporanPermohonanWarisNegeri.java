package ekptg.view.meps;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.InternalUserUtil;
import ekptg.model.entities.InternalUser;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.meps.IMEPPTNegeri;
import ekptg.model.meps.IMEPUmum;
import ekptg.model.meps.IMEPUtils;
import ekptg.model.meps.MEPPKNegeriWarisBean;
import ekptg.model.meps.MEPPTNegeriBean;
import ekptg.model.meps.MEPUtilBean;

public class PPKLaporanPermohonanWarisNegeri extends AjaxBasedModule{	
	/**
	 * 
	 */
 	private IMEPUmum iMep = null;  
 	private IMEPUtils iMepUtil = null;
 	private IMEPPTNegeri iMepPPT = null;
	private static final long serialVersionUID = -855108166857901260L;
	static Logger myLog = Logger.getLogger(ekptg.view.meps.PPKLaporanPermohonanWarisNegeri.class);
	private String idSubUrusan = "";			    	
	
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
		
		// MID
		String ID_NEGERI = "";
		String ID_PEJABATJKPTG = "";
		context.put("ID_NEGERI", "");
		context.put("ID_PEJABATJKPTG", "");
		
		List list_TBLRUJNEGERI = null;
		List listPejabatJKPTG = null;
		
		// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppk/LaporanJumlahWarisNegeri.jsp";
    	//TAB
    	String selectedTab = "";
		selectedTab = getParam("tabId");	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
			
			ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);
			
		}		
		myLog.info("SUBMIT :: "+submit);
		myLog.info("SUBMIT :: socSuburusan="+getParam("socSuburusan"));	
		
		//idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
		idSubUrusan = (getParam("socSuburusan").equals("") || getParam("socSuburusan").equals("-1")) ? null:getParam("socSuburusan");
		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null
				, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
		
		list_TBLRUJNEGERI = getMep().listTableRujukanV3(session,"TBLRUJNEGERI","","");
		this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
		listPejabatJKPTG = getMep().listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
		this.context.put("listPejabatJKPTG",listPejabatJKPTG);
		
		String role = String.valueOf(session.getAttribute("myrole"));
		//if(role.equals("adminppk") || role.equals("usernegeri_ppk"))
		String idNegeri = null;
		if(role.equals("user_ppk")||role.equals("usernegeri_ppk")){
			iu = InternalUserUtil.getSeksyenId(userId);
			idNegeri = iu.getIdNegeri();
			
		}
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
    		context.put("id_bulan", idTahunHingga);
    		
    		
    		ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);
    		
    		/*list_TBLRUJNEGERI = getMep().listTableRujukanV3(session,"TBLRUJNEGERI","","");
    		this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
    		listPejabatJKPTG = getMep().listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
    		this.context.put("listPejabatJKPTG",listPejabatJKPTG);*/
    		
    		//DROP DOWN
//    		context.put("selectTahun",getMepUtils().SelectTahun(this.getTahun(ID_NEGERI),"socTahun" , idTahun, null, "style=width:130px"));
//    		context.put("selectTahunHingga",getMepUtils().SelectTahun(this.getTahun(ID_NEGERI),"socTahunHingga", idTahunHingga, null, "style=width:130px"));
//           	
    		//GET LIST DATA
    		
    		System.out.println("ListPageDepan:::::::");   
    		listPageDepan = getMep().getJumlahPermohonan(ID_NEGERI, ID_PEJABATJKPTG, idTahun,idTahunHingga,idSubUrusan);
			
			Vector v = new Vector();
			Hashtable h = null;
			//Vector vecTahun = getMep().getJumlahPermohonan("","",idNegeri,idSubUrusan);
			//myLog.info("listPageDepan" + listPageDepan.size());
			for (int i = 0; i < listPageDepan.size(); i++) {
				Hashtable hTahun = (Hashtable) listPageDepan.get(i);
				h = new Hashtable();
				h.put("id",hTahun.get("tahun"));
				h.put("tahun",hTahun.get("tahun"));
				
				ID_NEGERI = getParam("ID_NEGERI");
				context.put("ID_NEGERI", ID_NEGERI);
				ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
				context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);
				
				v.addElement(h);				
			}
			context.put("selectTahun",getMepUtils().SelectTahun(v,"socTahun" , idTahun, null, "style=width:130px"));
			context.put("selectTahunHingga",getMepUtils().SelectTahun(v,"socTahunHingga", idTahunHingga, null, "style=width:130px"));
			
			
//    	}   
   		this.context.put("PermohonanList", listPageDepan);
		this.context.put("list_size", listPageDepan.size());  	
		
		//GENERATE BAR & PIE CHART
		//TAB
		this.context.put("selectedTab",selectedTab);		
		String tajukLaporan = "Laporan Jumlah Waris Mengikut Tahun ";
		if(idSubUrusan != null){	
			Tblrujsuburusan f = (Tblrujsuburusan) getMepPPT().getSubUrusan(idSubUrusan);
			tajukLaporan += "("+f.getNamaSuburusan()+") ";			

		}
		if (!idTahun.trim().equals("")) {
			if (!idTahunHingga.trim().equals("")) {
				tajukLaporan = tajukLaporan + "dari tahun " + idTahun +" sehingga "+idTahunHingga;
			}else{
				tajukLaporan = tajukLaporan + "bagi tahun " + idTahun;
			}
		}
		
		String pej = getMep().getNamaPejabat(session, ID_PEJABATJKPTG);
		if (ID_NEGERI.equals("") || !ID_NEGERI.equals("")) {
			if (!ID_PEJABATJKPTG.equals("")) {
				tajukLaporan += " BAGI "+ pej;					
			} 
		}

		//TAB
		this.context.put("tajukLaporan",tajukLaporan.toUpperCase());
		context.put("refence", refence);		
	    this.context.put("EkptgUtil", new ekptg.helpers.Utils());
   		return vm;
     
		
	}// close doTemplate2

	
// METHOD --------------
	private Vector getTahun(String ID_NEGERI) throws Exception {
		
		String ID_PEJABATJKPTG = "";
		context.put("ID_NEGERI", "");
		context.put("ID_PEJABATJKPTG", "");
		
		Vector v = new Vector();
		Hashtable h = null;
		Vector vecTahun = getMep().getJumlahPermohonan(ID_NEGERI,"","","",idSubUrusan);
		for (int i = 0; i < vecTahun.size(); i++) {
			Hashtable hTahun = (Hashtable) vecTahun.get(i);
			h = new Hashtable();
			h.put("id",hTahun.get("tahun"));
			h.put("tahun",hTahun.get("tahun"));
			
			ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);
			
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
	
}// close class
