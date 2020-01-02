package ekptg.view.ppk;


import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;



public class CetakSenaraiSemakRayuan extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(CetakSenaraiSemakRayuan.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppk/cetakSenaraiSemakRayuan.jsp";
	private String vm = PATH;
	
	HttpSession session = null;
	String action = null;
	
	
	public String doTemplate2() throws Exception {		
		//session = request.getSession();
		//action = getParam("action");
		//String command =  getParam("command");
		//this.context.put("command",command);
		
		//myLogger.info("PaparanMaklumatSerahan : MASUK : "+getParam("test"));
		String no_resit = getParam("no_resit");
		String EtxdTarikhByrnFee = getParam("EtxdTarikhByrnFee");
		String EtxtJumDeposit = getParam("EtxtJumDeposit");
		myLogger.info("EtxtJumDeposit : ****************"+EtxtJumDeposit);
		String EtxtNomborResitDeposit= getParam("EtxtNomborResitDeposit");
		String EtxdTarikhByrnDeposit= getParam("EtxdTarikhByrnDeposit");
		String EtxdTarikhTerimaRayuan= getParam("EtxdTarikhTerimaRayuan");
		String checkradiobutton = getParam("checkradiobutton");
		String noFail = getParam("noFail");

		this.context.put("no_resit",no_resit);
		this.context.put("EtxdTarikhByrnFee",EtxdTarikhByrnFee);
		this.context.put("EtxtJumDeposit",EtxtJumDeposit);
		this.context.put("EtxtNomborResitDeposit",EtxtNomborResitDeposit);
		this.context.put("EtxdTarikhByrnDeposit",EtxdTarikhByrnDeposit);
		this.context.put("EtxdTarikhTerimaRayuan",EtxdTarikhTerimaRayuan);
		this.context.put("checkradiobutton",checkradiobutton);
		this.context.put("noFail",noFail);
		return vm;
	}
	
}
