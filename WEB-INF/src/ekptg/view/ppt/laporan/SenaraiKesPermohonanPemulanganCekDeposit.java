package ekptg.view.ppt.laporan;

import java.util.Date;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class SenaraiKesPermohonanPemulanganCekDeposit extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(SenaraiKesPermohonanPemulanganCekDeposit.class);

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = request.getSession();
		
		String vm = ""; 
		String submit = getParam("command");
		String mainpage = "app/ppt/LAPORAN/SenaraiKesPermohonanPemulanganCekDeposit.jsp";
		String socNegeri = getParam("socNegeri");
		String socDaerah = getParam("socDaerah");
		String socMukim = getParam("socMukim");
		String tahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
		
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(socNegeri), "", "onChange=\"doChangeNegeri();\" "));
		context.put("selectDaerah",HTML.SelectDaerahByIdNegeri(socNegeri, "socDaerah",  Utils.parseLong(socDaerah), "","onChange=\"doChangeDaerah();\" "));
		context.put("selectMukim",HTML.SelectMukimByDaerah(socDaerah,"socMukim" , Utils.parseLong(socMukim), ""));
		context.put("selectTahun",HTML.SelectTahun("socTahun",tahun,null,"id='socTahun' style=width:200px"));
		
		if("doChangeNegeri".equals(submit)){
			context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(socNegeri), "", ""));
			context.put("selectDaerah",HTML.SelectDaerahByIdNegeri(socNegeri, "socDaerah",  Utils.parseLong(socDaerah), "","onChange=\"doChangeDaerah();\" "));
			context.put("selectMukim",HTML.SelectMukimByDaerah(socDaerah,"socMukim" , Utils.parseLong(socMukim), ""));
		}
		
		if("doChangeDaerah".equals(submit)){
			myLogger.info("daerah--"+socDaerah);
			context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(socNegeri), "", ""));
			context.put("selectDaerah",HTML.SelectDaerahByIdNegeri(socNegeri, "socDaerah",  Utils.parseLong(socDaerah), "","onChange=\"doChangeDaerah();\" "));
			context.put("selectMukim",HTML.SelectMukimByDaerah(socDaerah,"socMukim" , Utils.parseLong(socMukim), ""));
		}
		
    	vm = mainpage;
    	return vm;
	}

	

}
