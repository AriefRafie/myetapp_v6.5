package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmPajakanKecilPendaftaranData;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;

public class XFrmPajakanKecilACarian extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6072748528781942098L;
	static Logger mylog = Logger.getLogger(ekptg.view.htp.XFrmPajakanKecilACarian.class);
	Long idHakmilikUrusan = 0L;
	private final String PATH="app/htp/";
	private final String IDURUSAN = "309";
	private final String IDSUBURUSAN = "44";
	
	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
	    String template_name = "app/htp/";
      	String disability = "disabled";
    	String readability = "";

	    String socAgensi = "";
	    String socKementerian = "";
	    String socNegeri = "";
	    String socUrusan = "";
	    String socSuburusan = "";
		Vector semakanSenarai = new Vector();

	    this.context.put("util", new lebah.util.Util());

	    Vector senaraiFail = null;	    
	    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");    
        String id_kementerian = getParam("sockementerian");
	    String submit = getParam("command");
	    String idFail = getParam("fail");
	    String pageMode = getParam("pagemode");
	    String langkah = getParam("langkah");
	    mylog.info("doTemplate2:submit::"+submit+"|pagemode::"+pageMode+"|langkah::"+langkah);
		String strNoFail = "";
		String strNamaFail = "";
		String socStatus = "";
	    String noFail = getParam("nofail");
	    String namaFail = getParam("namafail");
	    String idNegeri = getParam("socNegeri");
	    String idStatus = getParam("socStatus");
	    String action = getParam("action");
	
	    if (!("".equals(submit))) {
	    	this.context.put("idsuburusan",IDSUBURUSAN);
	    	this.context.put("idurusan",IDURUSAN);	
	    	template_name = PATH+"frmPajakanKecilSenaraiFailCarian.jsp";
	    	
	    	if("pksenaraifailcari".equals(submit)){ //carian

	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getListSearch(noFail,namaFail,idNegeri,idStatus);	    		
	    		if(!("".equals(idStatus)))
	    			socStatus = FrmSenaraiFailPajakanKecilData.SelectStatusDistinct("socStatus",idStatus,null,null,"309");
	    		else
	    			socStatus = FrmSenaraiFailPajakanKecilData.SelectStatusDistinct("socStatus",idStatus,null,null,"309");
	    					
	    		this.context.put("socStatus", socStatus);  
	    		
	    		if(!("".equals(idNegeri)))
	    			socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri",Long.parseLong(idNegeri),null);
	    		else
	    			socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
			
	    		this.context.put("socNegeri",socNegeri);
	    		if(!("".equals(noFail)))
	    			strNoFail = noFail;
	    			
	    		if(!("".equals(namaFail)))
	    			strNamaFail = namaFail;

	    		this.context.put("noFail",strNoFail);
	    		this.context.put("namaFail",strNamaFail);
	    		
	    		//this.context.put("senaraiList1", v); 
	    		this.context.put("senaraiCarian", senaraiFail);
	    		setupPage(session,action,senaraiFail);
	    	}
	    	
	    }else{ // !=submit
	    		template_name = PATH+"frmPajakanKecilSenaraiFailCariand.jsp";
	    		//senaraiFail = FrmSenaraiFailPajakanKecilData.getListSearch(noFail,namaFail,idNegeri,idStatus);
	    		senaraiFail = null;
	    		this.context.put("senaraiCarian", senaraiFail);  
	    		
	    		socStatus = FrmSenaraiFailPajakanKecilData.SelectStatusDistinct("socStatus",idStatus,null,null,"309");
	    		this.context.put("socStatus", socStatus);  

	    		if(!("".equals(idNegeri)))
	    			socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri",Long.parseLong(idNegeri),null);
	    		else
	    			socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
			
	    		this.context.put("socNegeri",socNegeri);
	    		if(!("".equals(noFail)))
	    			strNoFail = noFail;
	    			
	    		if(!("".equals(namaFail)))
	    			strNamaFail = namaFail;

	    		this.context.put("noFail",strNoFail);
	    		this.context.put("namaFail",strNamaFail);	

	    }
	    return template_name;
	    
	}
 
}
