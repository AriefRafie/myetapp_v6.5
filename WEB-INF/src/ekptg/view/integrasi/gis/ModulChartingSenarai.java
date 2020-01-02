package ekptg.view.integrasi.gis;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.intergration.IIntegrasi;
import ekptg.intergration.IntegrasiGISBean;
import ekptg.model.htp.UtilHTML;
import ekptg.model.integrasi.entities.MaklumaTanahGIS;

/**
 * @author Mohamad Rosli
 * Sub Modul Charting bagi kegunaan Pengguna Unit Perolehan BHTP 
 *
 */
public class ModulChartingSenarai extends AjaxBasedModule{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2676269152441513897L;
	private final String PATH="app/integrasi/gis/";
	private static Logger myLog = Logger.getLogger(ekptg.view.integrasi.gis.ModulChartingSenarai.class);
	String socKementerian = "";
	String socAgensi = "";
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String socUrusan = "";
	String noLot = "";
	
 	String daftarFailSearch = "";
 	String txtTajukCarian = "";
 	String idKementerian = "";
 	String idAgensi = "";
 	String idNegeri = "";
 	String iDaerah = "";
 	String idMukim = "";
 	String idurusan = "";
 	String noFail = "";
	String idpermohonan ="";
	String idhakmilikurusan ="";
	String idfail = "";
	String userId = "";
	String tabmode = "";
	String readOnly = "";
	String disabled = "";
	String style = "";
	String idNotis = "";
	String Lokasi = "";
	//18/08/2010
	String flagAdvSearch = "";
	String button_ = ""; 
	//String idGIS = "";
	// INTEGRASI 2017/06
	IntegrasiGISBean IntegrasiGISBean = new IntegrasiGISBean();
	private IntegrasiGISBean integrasiGIS = null;
	Vector<MaklumaTanahGIS> senaraiTerimaPohon = null;
	String idGIS = "";
	

	//END INIT	
	
	@Override
	public String doTemplate2() throws Exception {				
		HttpSession session = this.request.getSession();
		String portal_role = String.valueOf(session.getAttribute("myrole"));
		context.put("portal_role_",portal_role);
		userId = String.valueOf(session.getAttribute("_ekptg_user_id"));
//		if(userId == null){
//			getIHTP().getErrorHTML("[HTP PERMOHONAN] SILA LOGIN SEMULA");
//		}
		//Parameters
		String action = getParam("action");
		String button = getParam("button");
		button_ = button;
		String doChange = getParam("doChange");
		String mode = getParam("mode");
		String pagemode = getParam("pagemode");
		String submit = getParam("command");
		idpermohonan = getParam("idpermohonan");
		idfail = getParam("idfail");
		noFail = getParam("txtNoFail").trim();
	 	//txtTajuk = getParam("txtTajuk");
	 	//18/08/2010
		txtTajukCarian = getParam("txtTajukFail");
	 	idKementerian = getParam("socKementerian");
	 	idAgensi = getParam("socAgensi");
	 	idNegeri = getParam("socNegeri");
	 	iDaerah = getParam("socDaerah");
	 	idMukim = getParam("socMukim");
	 	idGIS = getParam("idGIS");
	 	//statusGIS = getParam("");
	 	
		
		this.context.put("readOnly", "");
		this.context.put("disabled", "");			
		//DEFAULT template
		String template_name = PATH+"index.jsp";
		myLog.info("SUBMIT=" +submit+",MODE=" + mode+",BUTTON =" + button+",action="+action);
		//18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");
		idurusan = getParam("socUrusan");
		daftarFailSearch = getParam("txdtarikhdaftarfail");
		
		try{

			if (submit.equals("SahkanPermohonan")) {
				template_name = PATH+"FrmTerimaPohon.jsp";
				return template_name;		
				
			}else if (submit.equals("getGIS")) {
				ResourceBundle rb = (ResourceBundle)session.getAttribute("rbFile");
				myLog.info("rbFile="+rb.getString("gisurl"));
				//System.out.print("rbFile="+rb.getString("gisurl"));
				session.setAttribute("gisURL", rb.getString("gisurl"));

		    }else {
		    	//FIRST PAGE - SENARAI FAIL PERMOHONAN		    	
		    	if(flagAdvSearch.equals("open")){
		    		//myLog.info("Y");
		    		senaraiTerimaPohon = getIntegrasi().getSenarai(idNegeri, iDaerah, idMukim
		    				, noFail, "-1", "-1", "-1", "-1"
		    				, idAgensi, idKementerian
		    				,txtTajukCarian,idurusan,daftarFailSearch);
		
		    	}else{
		    		myLog.info("default page");
		    		senaraiTerimaPohon = getIntegrasi().getSenarai(idNegeri, iDaerah, idMukim
		    				, noFail, "-1", "", "-1", ""
		    				, idAgensi, idKementerian
		    				,txtTajukCarian,idurusan,daftarFailSearch);
		    		//if(action.contains("Page")){
			    	if(action.contains("get")){
			    		senaraiTerimaPohon = getIntegrasi().getSenarai(idNegeri, iDaerah, idMukim
			    				, noFail, "-1", "-1", "-1", "-1"
			    				, idAgensi, idKementerian
			    				,txtTajukCarian,idurusan,daftarFailSearch);
		    			
		    		}
			    	myLog.info("bilangan="+senaraiTerimaPohon.size());
		    		doListing(session,action,mode,senaraiTerimaPohon);
		    			    		
		    	}
	    		if(mode.equals("changeNegeri")){
	    			senaraiTerimaPohon = getIntegrasi().getSenarai(idNegeri, iDaerah, idMukim
		    				, noFail, "-1", "-1", "-1", "-1"
		    				, idAgensi, idKementerian
		    				,txtTajukCarian,idurusan,daftarFailSearch);
			    	 doListing(session,action,mode,senaraiTerimaPohon);
			    	 
	    		}else if(mode.equals("cancel")){
	    			idNegeri = "";
	    			noFail = "";
	        	    idMukim = "-1";
	        	    idKementerian = "";
	        	    idAgensi = "-1";
	        	    //idUrusan = "-1";
	        		senaraiTerimaPohon = getIntegrasi().getSenarai(idNegeri, iDaerah, idMukim
		    				, noFail, "-1", "-1", "-1", "-1"
		    				, idAgensi, idKementerian
		    				,txtTajukCarian,idurusan,daftarFailSearch);
			    	doListing(session,action,mode,senaraiTerimaPohon);
			    	
	    		}

				else if (submit.equals("hapus")){
					IntegrasiGISBean.deleteGIS(idGIS);
					senaraiTerimaPohon = getIntegrasi().getSenarai(idNegeri, iDaerah, idMukim
		    				, noFail, "-1", "-1", "-1", "-1"
		    				, idAgensi, idKementerian
		    				,txtTajukCarian,idurusan,daftarFailSearch);
			    	doListing(session,action,mode,senaraiTerimaPohon);
			    }
	    		
		    }
			this.context.put("txtTajukCarian",txtTajukCarian);
			    	//myLog.info("selectedtab:selectedTab="+selectedTab+",selectedTab2="+selectedTab2+",selectedTab3="+selectedTab3);
			
			this.context.put("tabmode", tabmode);
			this.context.put("button", button);
			this.context.put("mode", mode);
			this.context.put("submit", submit);
	    	this.context.put("style",style);
	    	this.context.put("idpermohonan",idpermohonan);
	    	this.context.put("idPermohonan",idpermohonan);//Utk frmTerimaPohon.jsp
	    	this.context.put("idhakmilikurusan",idhakmilikurusan);
	    	this.context.put("idfail",idfail);
	    	//myLog.info("selectedtab X:button="+button+",tabmode="+tabmode+",mode="+mode);
		    // 18/08/2010
	    	//myLog.info("flagAdvSearch="+flagAdvSearch);
	    	this.context.put("flagAdvSearch",flagAdvSearch);	
			if(session.getAttribute("rbFile") == null){
				ResourceBundle rb = ResourceBundle.getBundle("file");
				session.setAttribute("rbFile", rb);
			
			}
				
		}catch(Exception e){
			e.printStackTrace();
			myLog.info("812");

		}
		return template_name;
	
	}
	
	public void initContext() {
		this.context.put("socMukim","");
	}
	
	public void emptyContext() {
		this.context.put("txtNoFail","");
		this.context.put("txtTajukFail","");
		
	}
	
	public void doListing(HttpSession session,String action,String mode,Vector<MaklumaTanahGIS> v) 
		throws Exception {
		if (mode.indexOf("change") != -1) {
			myLog.debug("changes detected...");
	    	initContext();

		}
        this.context.put("txtNoFail",noFail);
	    
		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),null);//disabled class=disabled
		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), null,"onChange=\"doChangeKementerianX()\" style=\"width:400\"");
		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), ""," style=\"width:400\"");
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),null,"onChange=doChangeNegeriX();");
		socDaerah = HTML.SelectDaerahByNegeri(idNegeri, "socDaerah",Utils.parseLong(iDaerah),null, "onChange=\"doChangeDaerahX()\"");
		socMukim = HTML.SelectMukimByDaerah(iDaerah, "socMukim", Utils.parseLong(idMukim) , "");
		this.context.put("socUrusan", socUrusan);
	   	this.context.put("socKementerian", socKementerian);
	   	this.context.put("socAgensi", socAgensi);
	    this.context.put("socDaerah", socDaerah);
		this.context.put("socNegeri", socNegeri);
		this.context.put("socMukim", socMukim);		
		this.context.put("senaraiList1", v); 
		setupPage(session,action,v);
		
	}
	
	public void setPaging(boolean page1,boolean page2,boolean page3,boolean page4,boolean page5) {
		this.context.put("page1",page1);
		this.context.put("page2",page2);
		this.context.put("page3",page3);
		this.context.put("page4",page4);
		this.context.put("page5",page5);
	
	}
	
	private void getValues(){
		String tajuk = getParam("txtTajuk");		
		String failKJP = getParam("txtnoFailKJP");
		String tarikhSuratKJP = getParam("txdTarikhSuratKJP");
		String idPermohonan = getParam("idpermohonan");
		String noFailUPT = getParam("txtnoFailUPT");
		String noFailLain = getParam("txtnofailnegeri");
		String noFailPTG = getParam("txtnofailptg");
		String noFailPTD = getParam("txtnofailptd");
		String idDaerah = getParam("socdaerah2");			
		String idAgensi = getParam("lblidAgensi");			
		String idKeselamatan = getParam("lblTanahKeselamatan");

		//fail = new PfdFail();
		//permohonan = new Permohonan();
		//htpPermohonan = new HtpPermohonan();

//		fail.setIdTarafKeselamatan(idKeselamatan);
//		
//		permohonan.setTujuan(tajuk);		
//		permohonan.setTarikhSurat(tarikhSuratKJP);
//		htpPermohonan.setNoRujukanKJP(failKJP);
//		htpPermohonan.setNoRujukanLain(noFailLain);
//		htpPermohonan.setNoRujukanUPT(noFailUPT);
//		htpPermohonan.setNoRujukanPTG(noFailPTG);
//		htpPermohonan.setNoRujukanPTD(noFailPTD);
//		permohonan.setTarikhTerima(tarikhSuratKJP);
//		permohonan.setIdPermohonan(idPermohonan);
//		permohonan.setIdMasuk(Long.parseLong(userId));
//		permohonan.setPfdFail(fail);
//		htpPermohonan.setPermohonan(permohonan);
//		htpPermohonan.setIdAgensi(idAgensi);
//		htpPermohonan.setIdDaerah(idDaerah);
//
//		context.put("permohonan", htpPermohonan);
//		
//		tanahBean.getValues(context, tajuk, failKJP, tarikhSuratKJP, idPermohonan
//			, noFailUPT, noFailLain, noFailPTG, noFailPTD, idDaerah
//			, idAgensi, idKeselamatan, userId);
				
	}


	private IIntegrasi getIntegrasi(){
		if(integrasiGIS==null){
			integrasiGIS = new IntegrasiGISBean();
		}
		return integrasiGIS;
				
	}
	  
		  
}
	