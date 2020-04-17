package latihan.sip;


import org.apache.log4j.Logger;

import lebah.portal.AjaxBasedModule;

public class LatihanShiqa extends AjaxBasedModule {
	
	private static final long serialVerionUID = 1L;
	Logger myLog = Logger.getLogger(latihan.sip.LatihanShiqa.class);	
	String submit = getParam("command");
	@Override
	public String doTemplate2() throws Exception {
		// TODO Auto-generated method stub
		 String namaScreen = "";

		 namaScreen = "app/tranningjava/202002/index.jsp";
		 
		 myLog.info("command="+submit);
		 if (submit.equals("upload")) {
			 myLog.info("Fungsi Upload");
		 }
		 this.context.put("commnad", submit);
		 		
		return namaScreen;
	}
	
}

	
