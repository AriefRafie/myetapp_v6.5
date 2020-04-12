package latihan.sip.arief;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.portal.AjaxBasedModule;

public class muatnaikDokument extends AjaxBasedModule{
	String action = getParam("action");
	String submit = getParam("command");
	static Logger myLog = Logger.getLogger(muatnaikDokument.class);
	
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String screenName = "app/trainingjava/arief/uploadDoc.jsp"; // path of jsp
		if(submit.equals("upload")){
			myLog.info("fungsi upload");
		}
		this.context.put("command", submit);
		return screenName;
	}
}