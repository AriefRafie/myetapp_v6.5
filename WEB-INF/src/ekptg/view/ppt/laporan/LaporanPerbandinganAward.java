package ekptg.view.ppt.laporan;
import java.util.Date;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

@SuppressWarnings("serial")
public class LaporanPerbandinganAward extends AjaxBasedModule{	
	
	static Logger myLogger = Logger.getLogger(LaporanPerbandinganAward.class);
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		
		String vm = ""; 
		String mainpage = "app/ppt/LAPORAN/LaporanPerbandinganAward.jsp";
		String userIdNegeri = (String) session.getAttribute("_ekptg_user_negeri");
		String tahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
		
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(userIdNegeri),"style=width:325px"));
		context.put("selectTahun",HTML.SelectTahun("socTahun",tahun,null,"id='socTahun' style=width:200px"));
		
    	vm = mainpage;
    	return vm;
     
		
	}// close doTemplate2

	 
}// close class
