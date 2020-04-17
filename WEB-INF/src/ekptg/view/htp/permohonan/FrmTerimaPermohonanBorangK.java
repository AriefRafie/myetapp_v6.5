package ekptg.view.htp.permohonan;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.intergration.BorangKIntergrationBean;
import ekptg.intergration.IBorangKIntergration;
import ekptg.intergration.entity.BorangK;
import ekptg.model.htp.FrmSenaraiFailTerimaPohonData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;


public class FrmTerimaPermohonanBorangK extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3744798063048260707L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.permohonan.FrmTerimaPermohonanBorangK.class);
	private final String PATH="app/htp/permohonan/";
	private final String JENISTANAH = "1,3,6";
	private final String IDSUBURUSANPERMOHONAN = "42";
	private IHtp iHtp = null;

	FrmSenaraiFailTerimaPohonData fData = null;
	//INIT
	String socKementerian = "";
	String socAgensi = "";
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String socUrusan = "";
	String socSubUrusan = "";
	String socStatustanah = "";
	String socjenisFail = "";	
	String readability = "";
	String disability = "";
	String noLot = "";
	
 	String txtTajuk = "";
 	String txtTajukCarian = "";
 	String id_kementerian = "";
 	String id_agensi = "";
 	String idnegeri = "";
 	String iddaerah = "";
 	String idmukim = "";
 	String idurusan = "";
 	String idsuburusan = "";
	String nofail = "";
	String idpermohonan ="";
	String idhakmilikurusan ="";
	String idfail = "";
	String idUser = "";
	String tabmode = "";
	String readOnly = "";
	String disabled = "";
	String style = "";
	String idNotis="";
	//ppt
	Vector listPPT = null;
	Vector listHakmilik = null;
	Vector listBorangK =  null;
	Vector senaraiTerimaPohon = null;
	//18/08/2010
	String flagAdvSearch = "";
	private IBorangKIntergration kIntergration = null;

	//END INIT	
	@Override
	public String doTemplate2() throws Exception {	
		System.out.println("this class");
		fData = FrmSenaraiFailTerimaPohonData.getInstance();
		HttpSession session = this.request.getSession();
		idUser = (String)session.getAttribute("_ekptg_user_id");		
		//Parameters
		String submit = getParam("command");
		String action = getParam("action");
		String mode = getParam("mode");
		String selectedTab = getParam("tabId");	
		String doChange = getParam("doChange");
		String pagemode = getParam("pagemode");
		String button = getParam("button");
		String idNotis = getParam("idNotis");
		tabmode = getParam("tabmode");
		idpermohonan = getParam("idpermohonan");
		idfail = getParam("idfail");
		idhakmilikurusan = getParam("idhakmilikurusan");
		//GET PARAM
		nofail = getParam("txtNoFail");
	 	txtTajuk = getParam("txtTajuk");
	 	//18/08/2010
		txtTajukCarian = getParam("txtTajukFail");
	 	id_kementerian = getParam("socKementerian");
	 	id_agensi = getParam("socAgensi");
	 	idnegeri = getParam("socNegeri");
	 	iddaerah = getParam("socDaerah");
	 	idmukim = getParam("socMukim");
	 	idurusan = getParam("socUrusan");
	 	if ("".equals(idurusan)) idurusan="-1";
	 	idsuburusan = getParam("socSubUrusan");
	 	
		if (selectedTab == null || "".equals(selectedTab) ) {
    		selectedTab="0";
    		tabmode = "0";
    	}
		String selectedTab2 = "";
		String selectedTab3 = "";		
		this.context.put("readOnly", "");
		this.context.put("disabled", "");			
		//DEFAULT template
		String template_name = PATH+"frmSenaraiBorangK.jsp";
		myLog.debug("SUBMIT=" +submit+",MODE :: " + mode+",BUTTON :: " + button);
		myLog.debug("selectedTab :: " +selectedTab+",tabmode :: " + tabmode+",PAGEMODE :: " +pagemode);

		this.context.put("showSahkanButton",false);
		this.context.put("sahkanresult","");
		//18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");
		String noFailPPT = "";
		try{
    		context.remove("SenaraiFail");
    		context.remove("borangKList");

			if(submit.equals("searchBorangK")||submit.equals("doChanges")){
		    	String idPermohonan = getParam("idpermohonan");
		    	noFailPPT = getParam("noFailPPT");
		    	String noLot = getParam("nolot");
		    	//Vector<BorangK> k = getKIntergration().getAvailableList(noFailPPT);
		    	Vector<BorangK> k = getKIntergration().getAvailableList(noFailPPT,null,noLot);
		    	context.put("borangKList", k);
		    	template_name = PATH +"frmSenaraiBorangK.jsp";
		    	context.put("idpermohonan", idPermohonan);
		    	context.put("noLot", noLot);
	    		doListing(session,action,mode,k);
		    
			}else {	//FIRST PAGE - SENARAI FAIL PERMOHONAN		    	
				context.remove("SenaraiFail");
				context.remove("totalRecords");
		    	if(flagAdvSearch.equals("Y")){
//					senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajukCarian,
//							id_kementerian,id_agensi,idnegeri,iddaerah,idmukim,idurusan);
//					doListing(session,action,mode,senaraiTerimaPohon);
					
		    	}else{
		    		Vector<BorangK> k = null;
		    		myLog.debug("default page");
			    	template_name = PATH +"frmSenaraiBorangK.jsp";
			    	noFailPPT = getParam("noFailPPT");
			    	if(!noFailPPT.equals("")){
			    		k = getKIntergration().getAvailableList(noFailPPT);
			    		//context.put("borangKList", k);
			    	}else{
			    		k = getKIntergration().getAvailableList();
			    		//context.put("borangKList", k);
			    	}
			    	doListing(session,action,mode,k);
			    	
//		    		if(mode.equals("changeNegeri")){
//				    	flagAdvSearch = "Y";
//		    			senaraiTerimaPohon = fData.TerimaPohongetList(null,nofail,txtTajukCarian,
//								id_kementerian,id_agensi,idnegeri,iddaerah,idmukim,idurusan);
//						doListing(session,action,mode,senaraiTerimaPohon);
//		    		}
		    	}
		    	
		    }
			selectedTab2 = (String)this.context.get("selectedTab2");
			if (selectedTab2 == null || "".equals(selectedTab2) ) 
			{
	    		selectedTab2="0";
	    	}
			selectedTab3 = (String)this.context.get("selectedTab3");
			if (selectedTab3 == null || "".equals(selectedTab3) ) 
			{
	    		selectedTab3="0";
	    	}
			this.context.put("selectedTab", selectedTab);
			this.context.put("selectedTab2", selectedTab2);
			this.context.put("selectedTab3", selectedTab3);
			
			this.context.put("tabmode", tabmode);
			this.context.put("button", button);
			this.context.put("mode", mode);
			this.context.put("submit", submit);
	    	this.context.put("style",style);
	    	this.context.put("idpermohonan",idpermohonan);
	    	this.context.put("idhakmilikurusan",idhakmilikurusan);
	    	this.context.put("idfail",idfail);
		    // 18/08/2010
	    	this.context.put("flagAdvSearch",flagAdvSearch);	
	    	this.context.put("noFailPPT",noFailPPT);	
	    	
		}catch(Exception e){
			e.printStackTrace();
			
		}
		//log.debug("** "+template_name);
		//System.out.println(template_name);
		return template_name;

	}
	
	public void initContext() {
		this.context.put("socMukim","");
	}
	
	public void doListing(HttpSession session,String action,String mode,Vector v) throws Exception {

		if (mode.indexOf("change") != -1) {
	    	myLog.debug("changes detected...");
	    	initContext();
	    	
	    }else if ("cancel".equals(mode)) {
    		myLog.debug("cancel mode");
    		//emptyContext();
    		nofail = "";
    		txtTajuk = "";
    	    iddaerah = "-1";
    	    idmukim = "-1";
    	    id_agensi = "-1";
    
		}else {
    		myLog.debug(" do listing :"+nofail);

    	}
        this.context.put("txtNoFail",nofail);
	    this.context.put("txtTajuk",txtTajuk);
	    
		socUrusan = UtilHTML.SelectUrusan("socUrusan",Utils.parseLong(idurusan),null);//disabled class=disabled
		socKementerian = HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), null,"onChange=\"doChangeKementerianX()\"");
		socAgensi = HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, Utils.parseLong(id_agensi), "","");
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),null,"onChange=doChangeNegeriX();");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");

		this.context.put("socUrusan", socUrusan);
	   	this.context.put("socKementerian", socKementerian);
	   	this.context.put("socAgensi", socAgensi);
	    this.context.put("socDaerah", socDaerah);
		this.context.put("socNegeri", socNegeri);
		this.context.put("socMukim", socMukim);
		
		//this.context.put("senaraiList1", v); 
		setupPage(session,action,v);
	}
		
	private IBorangKIntergration getKIntergration(){
		if(kIntergration == null)
			kIntergration = new BorangKIntergrationBean();
		return kIntergration;
	}
	
	private IHtp getHTP(){
		if(iHtp == null)
			iHtp = new HtpBean();
		return iHtp;
	}
	

}	