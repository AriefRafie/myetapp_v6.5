package ekptg.view.ppk;


import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;



public class PaparanMaklumatSerahan extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(PaparanMaklumatSerahan.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppk/cetakLaporanSerahanNotis.jsp";
	private String vm = PATH;
	
	HttpSession session = null;
	String action = null;
	
	
	public String doTemplate2() throws Exception {		
		//session = request.getSession();
		//action = getParam("action");
		//String command =  getParam("command");
		//this.context.put("command",command);
		
		//myLogger.info("PaparanMaklumatSerahan : MASUK : "+getParam("test"));
		String cara_serahan = getParam("cara_serahan");
		String noFail = getParam("noFail");
		String tarikh_serahan = getParam("tarikh_serahan");
		String nama_penerima= getParam("nama_penerima");
		String no_kp_penerima= getParam("no_kp_penerima");
		String catatan= getParam("catatan");
		String penyerah = getParam("penyerah");
		this.context.put("cara_serahan",cara_serahan);
		this.context.put("tarikh_serahan",tarikh_serahan);
		this.context.put("nama_penerima",nama_penerima);
		this.context.put("no_kp_penerima",no_kp_penerima);
		this.context.put("catatan",catatan);
		this.context.put("noFail",noFail);
		this.context.put("penyerah",penyerah);
		return vm;
	}
	
}
