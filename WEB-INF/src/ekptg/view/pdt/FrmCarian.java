package ekptg.view.pdt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.pdt.IPDTUtil;
import ekptg.model.pdt.PDTUtilBean;

public class FrmCarian extends AjaxBasedModule {

	private final String PATH="app/pdt/carian/";
	private static Logger myLog = Logger.getLogger(ekptg.view.pdt.FrmCarian.class);
	//FrmModelAkta akta = new FrmModelAkta();
 	private IPDTUtil iPDTUtil = null;  

	Vector vCarian = null;
	//AKTA
	String Akta_NoAkta = "";
	String Akta_NamaAkta = "";
	String Akta_Seksyen = "";
	String Akta_NoFail = "";
	String Akta_Catatan = "";
	String Akta_Dokumen = "";
	//PENGGAL
	String Penggal_IDPenggal = "";
	String tag ="";
	//SOC
	String SOC_Seksyen = "";
	String action = "";
	String Cari_NoAkta = "";
	String Cari_NamaAkta = "";
	String Cari_Seksyen = "";
	
	public String doTemplate2() throws Exception {
		//debugParams();
		String vm = "";
		action = getParam("action");
		String command = getParam("command");
		myLog.debug("REAL ACTION:"+action);
		String selectedTab = getParam("selectedTab");
		if ("".equalsIgnoreCase(selectedTab)) {
			selectedTab = "0";
		}
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
		HttpSession session = this.request.getSession();
		String user_name = (String)session.getAttribute("_portal_username");
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		//----------------------------------------------
		// processing
		vCarian = new Vector();

		this.context.put("num_files",1);
		
		if (command.equalsIgnoreCase("cari")) {
			vm = PATH+"index.jsp";
			tag = getParam("tag_dokumen");
			myLog.debug("tag="+tag);
			vCarian = getPDTUtil().getMaklumat("","","",tag,"");
			context.put("txtNoAkta", "");
			context.put("txtNamaAkta", "");
			context.put("txdTarikhKuatkuasa", "");
			context.put("tag_Dokumen",tag);
			setupPage(session,action,vCarian);	
					
		}else {
			vm = PATH+"index.jsp";
			action = request.getParameter("action");
			doCarian(session);

		}
		myLog.debug("action:"+this.context.get("action")+",selectedTab:"+selectedTab);
		return vm;
	
	}
	
	public void doCarian(HttpSession session) throws Exception{
		tag = getParam("tag_dokumen");
		vCarian = getPDTUtil().getMaklumat("","","","","");
		context.put("txtNoAkta", "");
		context.put("txtNamaAkta", "");
		context.put("txdTarikhKuatkuasa", "");
		context.put("", "");
		context.put("tag_Dokumen",tag);
		setupPage(session,action,vCarian);	
		
	}	
			
	private IPDTUtil getPDTUtil(){
		if(iPDTUtil== null)
			iPDTUtil = new PDTUtilBean();
		return iPDTUtil;
	}	
	
	
}
