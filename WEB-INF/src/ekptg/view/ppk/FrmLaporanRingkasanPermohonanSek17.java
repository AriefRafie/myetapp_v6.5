package ekptg.view.ppk;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
/*
 * @author 
 * @Muhamad Syazreen bin Yahaya
 */

public class FrmLaporanRingkasanPermohonanSek17 extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(FrmLaporanRingkasanPermohonanSek17.class);
	
	public String doTemplate2() throws Exception {
		
		HttpSession session = request.getSession();
		
		String vm = "";
		String submit = getParam("command");

		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "0";
		}
		String idUnit = getParam("socUnit");
		if (idUnit == null || idUnit.trim().length() == 0){
			idUnit = "0";
		}
		
		String daerah = getParam("socDaerah");
		if (daerah == null || daerah.trim().length() == 0){
			daerah = "0";
		}
		
		String tahun = getParam("socTahun");
		if (tahun == null || tahun.trim().length() == 0){
			tahun = "0";
		}
		
		context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeUnit();\""));
		context.put("selectUnit",HTML.SelectUnitPPKByNegeri("socUnit", Long.parseLong(idUnit), "", "onChange=\"doChangeDaerah();\"", idNegeri));
		context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=\"width=80px\""));
		context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",idUnit));
		
		if("doChangeUnit".equals(submit)){
			
			context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeUnit();\""));
			context.put("selectUnit",HTML.SelectUnitPPKByNegeri("socUnit", Long.parseLong(idUnit), "", "onChange=\"doChangeDaerah();\"", idNegeri));
			context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=\"width=80px\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",idUnit));
			System.out.println("idNegeri"+idNegeri);
		}
		else if ("doChangeDaerah".equals(submit)){
			
			context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeUnit();\""));
			context.put("selectUnit",HTML.SelectUnitPPKByNegeri("socUnit", Long.parseLong(idUnit), "", "onChange=\"doChangeDaerah();\"", idNegeri));
			context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=\"width=80px\""));
			context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",idUnit));
			
			
		}
		
		
		vm = "app/ppk/frmLaporanRingkasanPermohonanSek17.jsp";	
    	return vm;
           
	}//close template
	
}//close class
