package latihan.sip;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.portal.AjaxBasedModule;


public class afifah extends AjaxBasedModule{
	String action = getParam("action");
	String submit = getParam("command");
	static Logger myLog = Logger.getLogger(afifah.class);
	
	@Override
	public String doTemplate2() throws Exception {
		//HttpSession session = this.request.getSession();
		String namaSkrin = ""; 
		
		namaSkrin = "app/trainingjava/20200228/fifahindex.jsp"; // path of jsp
		
		if(submit.equals("upload")){
			myLog.info("fungsi upload");
		}
		this.context.put("command", submit);
		return namaSkrin;
	}

}
