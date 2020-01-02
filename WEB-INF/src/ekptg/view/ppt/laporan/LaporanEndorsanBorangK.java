package ekptg.view.ppt.laporan;
import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

@SuppressWarnings("serial")
public class LaporanEndorsanBorangK extends AjaxBasedModule{	
	
	static Logger myLogger = Logger.getLogger(LaporanEndorsanBorangK.class);
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		
		String vm = ""; 
		String mainpage = "app/ppt/LAPORAN/LaporanEndorsanBorangK.jsp";
		String userIdNegeri = (String) session.getAttribute("_ekptg_user_negeri");
		
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(userIdNegeri),"style=width:325px"));
		
    	vm = mainpage;
    	return vm;
     
		
	}// close doTemplate2

	 
}// close class
