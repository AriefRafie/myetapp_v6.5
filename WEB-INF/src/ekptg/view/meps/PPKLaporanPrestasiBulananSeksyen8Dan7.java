package ekptg.view.meps;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
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

public class PPKLaporanPrestasiBulananSeksyen8Dan7 extends AjaxBasedModule{	
	/**
	 * 
	 */
	private IHtp iHTP = null;  
 	private IMEPUmum iMep = null;  
 	private IMEPUtils iMepUtil = null;
 	private IMEPPTNegeri iMepPPT = null;
	private static final long serialVersionUID = -855108166857901260L;
	static Logger myLog = Logger.getLogger(PPKLaporanPrestasiBulananSeksyen8Dan7.class);
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
    	String getselectDaerah="";
    	String id_tahun ="";
    	String id_Suburusan="";
		String id_bulan="";
       	String idTahun = "";
    	String idTahunHingga = "";			
      	String idBulan = "";
    	String idBulanHingga = "";
    	String txdTarikhMula="";
    	String txdTarikhAkhir="";
    	String negeri="";
		String unit="";
		String daerah ="";
    	
		String userId = (String)session.getAttribute("_ekptg_user_id");
		if(userId == null){
			throw new Exception(getIHTP().getErrorHTML("[MODUL MEP] SILA LOGIN SEMULA"));
		}
		InternalUser iu = null;	
		// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppk/LaporanPrestasiBulananSeksyen8Dan7.jsp";
    	//TAB
    	String selectedTab = "";
		selectedTab = getParam("tabId");	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}		
		myLog.info("SUBMIT :: "+submit);
		myLog.info("SUBMIT :: socSuburusan="+getParam("socSuburusan"));	
		myLog.info("SUBMIT :: selectedTab="+selectedTab);
		
		iu = InternalUserUtil.getSeksyenId(userId);
		String idNegeri = iu.getIdNegeri();
   if("BarGraph".equals(submit)){ 
			
	   id_tahun = getParam("socDaerah");
		idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
		getselectDaerah = getParam("socDaerah");
		
		
		//DROP DOWN
   	
		txdTarikhMula = getParam("txdTarikhMula");
		txdTarikhAkhir = getParam("txdTarikhAkhir");
		
		
		negeri = getParam("socNegeri");
		//System.out.println(negeri);
		if (negeri == null || negeri.trim().length() == 0){
			negeri = "0";
		}
		unit = getParam("socUnit");
		if (unit == null || unit.trim().length() == 0){
			unit = "0";
		}
	   daerah = getParam("socDaerah");
		if (daerah == null || daerah.trim().length() == 0){
			daerah = "0";
		}
		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
	    context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeDaerah()\""));
		context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));

       //Show field
		//Change Happen
		
		context.put("txdTarikhMula",txdTarikhMula);
		context.put("txdTarikhAkhir",txdTarikhAkhir);
		context.put("getselectDaerah",getselectDaerah);
      	//GET LIST DATA
		//listPageDepan = model.getLaporanPrestasiSeksyen8Dan17(negeri,unit,daerah,txdTarikhMula,txdTarikhAkhir);				
		
		context.put("PermohonanList", listPageDepan);
		context.put("list_size", listPageDepan.size());  	
		
		vm = skrinDepanSenaraiLaporan;    		
		//DEFAULT
		context.put("refence", "");
			
		}else if("PieChart".equals(submit)){
			
			
    		id_tahun = getParam("socDaerah");
    		idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
    		getselectDaerah = getParam("socDaerah");
			
    		
    		//DROP DOWN
        	
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			
			negeri = getParam("socNegeri");
    		//System.out.println(negeri);
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "0";
    		}
    		unit = getParam("socUnit");
    		if (unit == null || unit.trim().length() == 0){
    			unit = "0";
    		}
    	   daerah = getParam("socDaerah");
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "0";
    		}
    		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
		    context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeDaerah()\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));

	        //Show field
    		//Change Happen
    		
    		context.put("txdTarikhMula",txdTarikhMula);
    		context.put("txdTarikhAkhir",txdTarikhAkhir);
    		context.put("getselectDaerah",getselectDaerah);
           	//GET LIST DATA
    		//listPageDepan = model.getLaporanPrestasiSeksyen8Dan17(negeri,unit,daerah,txdTarikhMula,txdTarikhAkhir);				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		
    		vm = skrinDepanSenaraiLaporan;    		
    		//DEFAULT
    		context.put("refence", "");
		
		}else if("LineChart".equals(submit)){
			
    		id_tahun = getParam("socDaerah");
    		idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
    		getselectDaerah = getParam("socDaerah");
			
    		
    		//DROP DOWN
        	
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			
			negeri = getParam("socNegeri");
    		//System.out.println(negeri);
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "0";
    		}
    		unit = getParam("socUnit");
    		if (unit == null || unit.trim().length() == 0){
    			unit = "0";
    		}
    	   daerah = getParam("socDaerah");
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "0";
    		}
    		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
		    context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeDaerah()\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));

	        //Show field
    		//Change Happen
    		
    		context.put("txdTarikhMula",txdTarikhMula);
    		context.put("txdTarikhAkhir",txdTarikhAkhir);
    		context.put("getselectDaerah",getselectDaerah);
           	//GET LIST DATA
    		//listPageDepan = model.getLaporanPrestasiSeksyen8Dan17(negeri,unit,daerah,txdTarikhMula,txdTarikhAkhir);				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		
    		vm = skrinDepanSenaraiLaporan;    		
    		//DEFAULT
    		context.put("refence", "");
		}else if("PieChart2".equals(submit)){
			
			
    		id_tahun = getParam("socDaerah");
    		idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
    		getselectDaerah = getParam("socDaerah");
			
    		
    		//DROP DOWN
        	
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			
			negeri = getParam("socNegeri");
    		//System.out.println(negeri);
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "0";
    		}
    		unit = getParam("socUnit");
    		if (unit == null || unit.trim().length() == 0){
    			unit = "0";
    		}
    	   daerah = getParam("socDaerah");
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "0";
    		}
    		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
		    context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeDaerah()\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));

	        //Show field
    		//Change Happen
    		
    		context.put("txdTarikhMula",txdTarikhMula);
    		context.put("txdTarikhAkhir",txdTarikhAkhir);
    		context.put("getselectDaerah",getselectDaerah);
           	//GET LIST DATA
    		//listPageDepan = model.getLaporanPrestasiSeksyen8Dan17(negeri,unit,daerah,txdTarikhMula,txdTarikhAkhir);				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		
    		vm = skrinDepanSenaraiLaporan;    		
    		//DEFAULT
    		context.put("refence", "");
					
		}else if("Laporan".equals(submit)){
			
			
    		id_tahun = getParam("socDaerah");
    		idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
    		getselectDaerah = getParam("socDaerah");
			
    		
    		//DROP DOWN
        	
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			
			negeri = getParam("socNegeri");
    		//System.out.println(negeri);
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "0";
    		}
    		unit = getParam("socUnit");
    		if (unit == null || unit.trim().length() == 0){
    			unit = "0";
    		}
    	   daerah = getParam("socDaerah");
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "0";
    		}
    		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
		    context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeDaerah()\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));

	        //Show field
    		//Change Happen
    		
    		context.put("txdTarikhMula",txdTarikhMula);
    		context.put("txdTarikhAkhir",txdTarikhAkhir);
    		context.put("getselectDaerah",getselectDaerah);
           	//GET LIST DATA
    		//listPageDepan = model.getLaporanPrestasiSeksyen8Dan17(negeri,unit,daerah,txdTarikhMula,txdTarikhAkhir);				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		
    		vm = skrinDepanSenaraiLaporan;    		
    		//DEFAULT
    		context.put("refence", "");
    	}else if("search_data".equals(submit)){			
    
    		idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
    		getselectDaerah = getParam("socDaerah");
    		//DROP DOWN
        	
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			negeri = getParam("socNegeri");
    		//System.out.println(negeri);
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "0";
    		}
    		unit = getParam("socUnit");
    		if (unit == null || unit.trim().length() == 0){
    			unit = "0";
    		}
    	   daerah = getParam("socDaerah");
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "0";
    		}
    		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
		    context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeDaerah()\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));

	        //Show field
    		//Change Happen
    		
    		context.put("txdTarikhMula",txdTarikhMula);
    		context.put("txdTarikhAkhir",txdTarikhAkhir);
    		context.put("getselectDaerah",getselectDaerah);
           	//GET LIST DATA
    		//listPageDepan = model.getLaporanPrestasiSeksyen8Dan17(negeri,unit,daerah,txdTarikhMula,txdTarikhAkhir);				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		
    		vm = skrinDepanSenaraiLaporan;    		
    		//DEFAULT
    		context.put("refence", "");
    	}else {
    		System.out.println("else");
    		idSubUrusan = getParam("socSuburusan").equals("")?null:getParam("socSuburusan");
    	

    		txdTarikhMula="";
        	txdTarikhAkhir="";
    		
    		//DROP DOWN
        	
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			if(getselectDaerah.equals("")){
				getselectDaerah= "";
			}else{
				getselectDaerah = getParam("socDaerah");
			}
			
    		negeri = getParam("socNegeri");
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "0";
    		}
    		 unit = getParam("socUnit");
    		if (unit == null || unit.trim().length() == 0){
    			unit = "0";
    		}
    		 daerah = getParam("socDaerah");
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "0";
    		}
    	

	        //Show field 
    		context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null, "socSuburusan", idSubUrusan != null?Long.parseLong(idSubUrusan):null, ""));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:340 onChange=\"doChangeUnit();\""));
    		context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",null,"style=width:340"," onChange=\"doChangeDaerah()\""));
    		context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", null, "", "",unit));
    		//Change Happen
    		if("doChangeUnit".equals(submit)){
    		     context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
    			context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeDaerah()\""));
    			//context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "","0"));
    		}
    	    if("doChangeDaerah".equals(submit)){
    			
    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
    			context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(negeri,"socUnit",Long.parseLong(unit),"style=width:340"," onChange=\"doChangeDaerah()\""));
    			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",unit));
    		}
    		
    	
    		
    		context.put("txdTarikhMula",txdTarikhMula);
    		context.put("txdTarikhAkhir",txdTarikhAkhir);
    		context.put("getselectDaerah",getselectDaerah);
    		
           	//GET LIST DATA
    		//listPageDepan = model.getLaporanPrestasiSeksyen8Dan17(negeri,unit,daerah,txdTarikhMula,txdTarikhAkhir);				
    		
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  	
    		
    		vm = skrinDepanSenaraiLaporan;    		
    		//DEFAULT
    		context.put("refence", "");
    	}   
		
	
	
		
		
	
		
		
		
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
//			//listPageDepan = getMep().getJumlahHarta(id_tahun,idNegeri);
//    		//    	}else if("search_data".equals(submit)){		
//    	}else {
    		
    		
    	//}
    		//myLog.info("SUBMIT :: "+submit);
    			
		
		//GENERATE BAR & PIE CHART
		//TAB
		this.context.put("selectedTab",selectedTab);		
		String tajukLaporan = "Laporan Prestasi Bulanan";
		if(idSubUrusan != null){	
			Tblrujsuburusan f = (Tblrujsuburusan) getMepPPT().getSubUrusan(idSubUrusan);		
			tajukLaporan += " Bagi " + ""+f.getNamaSuburusan()+" ";			

		}
		if (!idTahun.trim().equals("")){
			if (!idTahunHingga.trim().equals("")) {
				tajukLaporan = tajukLaporan + "dari tahun " + idTahun +" sehingga "+idTahunHingga;
			}else{
				tajukLaporan = tajukLaporan + "bagi tahun " + idTahun;
			}
		}
		if (!negeri.equals("0")){
			if ((unit.trim().equals("0")) && (daerah.trim().equals("0")) && (txdTarikhMula.trim().equals(""))&& (txdTarikhAkhir.trim().equals(""))) {
				tajukLaporan = tajukLaporan + "Mengikut Unit";		
			
			}else if ((!unit.trim().equals("0")) && (daerah.trim().equals("0")) && (txdTarikhMula.trim().equals(""))&& (txdTarikhAkhir.trim().equals(""))){
				tajukLaporan = tajukLaporan + "Mengikut Daerah";
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
