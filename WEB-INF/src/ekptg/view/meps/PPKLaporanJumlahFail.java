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

public class PPKLaporanJumlahFail extends AjaxBasedModule{	
	/**
	 * 
	 */
	private IHtp iHTP = null;  
 	private IMEPUmum iMep = null;  
 	private IMEPUtils iMepUtil = null;
 	private IMEPPTNegeri iMepPPT = null;
	private static final long serialVersionUID = -855108166857901260L;
	static Logger myLog = Logger.getLogger(PPKLaporanJumlahFail.class);
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
       	String negeri="";
       	String daerah="";
       	String Tahun="";
       	String TahunSehingga="";
    	String idTahunHingga = "";			
      	String idBulan = "";
    	String idBulanHingga = "";	
    	String getLaporan="";
    	String reloadpage="no";
    	String tajukLaporan="";
		String userId = (String)session.getAttribute("_ekptg_user_id");
		if(userId == null){
			throw new Exception(getIHTP().getErrorHTML("[MODUL MEP] SILA LOGIN SEMULA"));
		}
		InternalUser iu = null;	
		// SKRIN JSP
    	String skrinDepanSenaraiLaporan = "app/meps/ppk/LaporanJumlahFail.jsp";
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

		if("BarGraph".equals(submit)){ 
		
			
			myLog.info("SUBMIT :: "+submit);
    		negeri = getParam("socNegeri");
    		daerah = getParam("socDaerah");
    	
    		//System.out.println(negeri);
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "99999";
    		}
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "99999";
    		}
    		//DROP DOWN
    		this.context.put("selectedTab",selectedTab);
   		    context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
   		    this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(negeri, "socDaerah", null, "", ""));
   			this.context.put("selectedTab",selectedTab);
   			listPageDepan = getMep().getJumlahLaporanFail(Tahun,TahunSehingga,negeri,daerah,idSubUrusan);
			getLaporan=getParam("jenis_laporan");
		
			if(getLaporan.equals("negeri")||getLaporan.equals("")){
				tajukLaporan = "Laporan Jumlah Fail Negeri ";
			}else if(getLaporan.equals("daerah")){
			    tajukLaporan = "Laporan Jumlah Fail Daerah ";
			}
			
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
		
				context.put("xml", getMepUtils().generateXMLFail(getMep().getSQL(),tajukLaporan.toUpperCase()));
			
			this.context.put("PermohonanList", listPageDepan);
			this.context.put("list_size", listPageDepan.size());
		}else if("PieChart".equals(submit)){
			
		}else if("search_data".equals(submit)){		
			negeri = getParam("socNegeri");
    		daerah = getParam("socDaerah");
    		 Tahun=getParam("socTahun");
             TahunSehingga=getParam("socTahunHingga");
    		System.out.println("Tahun"+Tahun);
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "99999";
    		}
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "99999";
    		}
    		//DROP DOWN
    		
   		     context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
   		     this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(negeri,"socDaerah", null, "", ""));
   		  listPageDepan = getMep().getJumlahLaporanFail(Tahun,TahunSehingga,negeri,daerah,idSubUrusan);
//			if(!idBulan.equals("")){
				//listPageDepan = getMep().getJumlahPermohonanxxx(idTahun,idTahunHingga,idBulan,idBulanHingga_,idNegeri,idSubUrusan);
//			}
			
			if(!negeri.equals("") && daerah.equals("")){
				tajukLaporan = "Laporan Jumlah Fail Negeri ";
			}else if(getLaporan.equals("daerah")){
			    tajukLaporan = "Laporan Jumlah Fail Daerah ";
			}
			
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
		         //System.out.println(daerah);
				context.put("xml", getMepUtils().generateXMLFailLine(getMep().getSQL(),tajukLaporan.toUpperCase()));
			    context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
	   		     this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(negeri, "socDaerah", null, "", ""));
	   			context.put("selectTahun",getMepUtils().SelectTahun(this.getTahun(idNegeri),"socTahun" , idTahun, null, "style=width:130px"));
	    		context.put("selectTahunHingga",getMepUtils().SelectTahun(this.getTahun(idNegeri),"socTahunHingga", idTahunHingga, null, "style=width:130px"));
	    		
			vm = skrinDepanSenaraiLaporan;
		}else if("LineChart".equals(submit)){
			
    		negeri = getParam("socNegeri");
    		daerah = getParam("socDaerah");
    		//System.out.println(negeri);
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "99999";
    		}
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "99999";
    		}
    		//DROP DOWN
   		     context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
   		     this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(negeri, "socDaerah", null, "", ""));
   		  listPageDepan = getMep().getJumlahLaporanFail(Tahun,TahunSehingga,negeri,daerah,idSubUrusan);
//			if(!idBulan.equals("")){
				//listPageDepan = getMep().getJumlahPermohonanxxx(idTahun,idTahunHingga,idBulan,idBulanHingga_,idNegeri,idSubUrusan);
//			}
			
			if(getLaporan.equals("negeri")||getLaporan.equals("")){
				tajukLaporan = "Laporan Jumlah Fail Negeri ";
			}else if(getLaporan.equals("daerah")){
			    tajukLaporan = "Laporan Jumlah Fail Daerah ";
			}
			
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
		
				context.put("xml", getMepUtils().generateXMLFailLine(getMep().getSQL(),tajukLaporan.toUpperCase()));
			
			this.context.put("PermohonanList", listPageDepan);
			this.context.put("list_size", listPageDepan.size());
			vm = skrinDepanSenaraiLaporan;
		}else if("AreaChart".equals(submit)){
		}else if("PieChart".equals(submit)){ 
			
		}else if("Laporan".equals(submit)){					
			myLog.info("Laporan");
			//listPageDepan = getMep().getJumlahHarta(id_tahun,idNegeri);
    		//    	}else if("search_data".equals(submit)){		
    	}else { 			
    		myLog.info("SUBMIT :: "+submit);
    		//System.out.println("else");
    		negeri = getParam("socNegeri");
    		daerah = getParam("socDaerah");
    		 Tahun=getParam("selectTahun");
            TahunSehingga=getParam("selectTahunHingga");
    		//System.out.println(negeri);
    		if (negeri == null || negeri.trim().length() == 0){
    			negeri = "99999";
    		}
    		if (daerah == null || daerah.trim().length() == 0){
    			daerah = "99999";
    		}
    		//DROP DOWN
    		context.put("selectTahun",getMepUtils().SelectTahun(this.getTahun(idNegeri),"socTahun" , idTahun, null, "style=width:130px"));
    		context.put("selectTahunHingga",getMepUtils().SelectTahun(this.getTahun(idNegeri),"socTahunHingga", idTahunHingga, null, "style=width:130px"));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:340 onChange=\"doChangeUnit();\""));
    		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(negeri, "socDaerah", null, "", ""));
           	//GET LIST DATA
    		if("doChangeUnit".equals(submit)){
   		     context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(negeri), "", " onChange=\"doChangeUnit()\""));
   		     this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(negeri, "socDaerah", null, "", ""));
   			//context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "","0"));
   		  }
    	
			listPageDepan = getMep().getJumlahLaporanFail(Tahun,TahunSehingga,negeri,daerah,idSubUrusan);
		
			
			if(getLaporan.equals("negeri")||getLaporan.equals("")){
				tajukLaporan = "Laporan Jumlah Fail Negeri ";
			}else if(getLaporan.equals("daerah")){
			    tajukLaporan = "Laporan Jumlah Fail Daerah ";
			}
			
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
		
				//context.put("xml", getMepUtils().generateXMLFail(getMep().getSQL(),tajukLaporan.toUpperCase()));
			
	         	this.context.put("tajukLaporan",tajukLaporan.toUpperCase());
		
    		
		
		vm = skrinDepanSenaraiLaporan;

   		this.context.put("PermohonanList", listPageDepan);
		this.context.put("list_size", listPageDepan.size());  
		//context.put("reloadpage", "yes");
    	}
		//GENERATE BAR & PIE CHART
		//TAB
		
		//TAB
		this.context.put("selectedTab",selectedTab);
		
		//this.context.put("tajukLaporan",tajukLaporan.toUpperCase());
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
