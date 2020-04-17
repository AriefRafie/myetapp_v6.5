package ekptg.view.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

public class Test extends AjaxBasedModule{

	static Logger myLogger = Logger.getLogger(RegisterUserModule.class);

	public String doTemplate2() throws Exception {
		String urlLinkImg = "http://localhost:8080/eTappv2/vtl";
		context.put("urlLinkImg", urlLinkImg);
		String tamplate = "vtl/users/test.jsp";
		return tamplate;
	}
    
	public static void main(String [] args) {
		try {
			System.out.println(Test.now("MM/dd/yyyy hh:mm:ss aaa"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());

    }

}
