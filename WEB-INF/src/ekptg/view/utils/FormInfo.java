
package ekptg.view.utils;

import lebah.portal.AjaxBasedModule;
import org.apache.log4j.Logger;

public class FormInfo extends AjaxBasedModule {

	private final String PATH="portal/";
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.utils.FormInfo.class);
	
	String readability = "";
	String disability = "";
	String idUser = "0"; 
    String idRujukan = "";

	@Override
	public String doTemplate2() throws Exception {		
		//GET DEFAULT PARAM
	    String vm = PATH+"myetappInfo.jsp";
	    String jenisInfo = getParam("jenis");
	    String namaImej = "";
		myLog.info("jenisInfo="+jenisInfo);
		
		if (jenisInfo.equals("tanah")){
			namaImej = "geran_mukim8.jpg";
		}
		this.context.put("namaInfo", namaImej);
	   	return vm;
		
	}

	
}
