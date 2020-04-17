package ekptg.view.ppt;

import java.util.Date;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.ppt.FrmPembatalanInternalData;

@SuppressWarnings("serial")
public class LaporanPampasan extends AjaxBasedModule{	
	
	static Logger myLogger = Logger.getLogger(LaporanPampasan.class);
	
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	
	@Override
	public String doTemplate2() throws Exception{
		
		//HttpSession session = request.getSession();
		
		
		String vm = ""; 
		String mainpage = "app/ppt/LAPORAN/LaporanBulananPampasan.jsp";

		//param
		String tahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
		
		String submit = getParam("command");
    	myLogger.info("submit : " + submit);

    	context.put("selectTahun",HTML.SelectTahun("socTahun",tahun,null,"id='socTahun' style=width:200px"));
  
    	context.put("tahun",tahun);
    	vm = mainpage;
    	return vm;
  
	}// close doTemplate2

}// close class
