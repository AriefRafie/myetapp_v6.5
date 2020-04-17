package ekptg.view.meps;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
/*
 * @author
 * @Muhamad Syazreen bin Yahaya
 */

public class PPK_FrmLaporanRingkasanPermohonanSek17 extends VTemplate {

	static Logger myLogger = Logger.getLogger(PPK_FrmLaporanRingkasanPermohonanSek17.class);

	public Template doTemplate() throws Exception {

		HttpSession session = request.getSession();

		String vm = "";

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

		context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChange();\""));
		context.put("selectUnit",HTML.SelectUnitPPKByNegeri("socUnit", Long.parseLong(idUnit), "", "onChange=\"doChange();\"", idNegeri));
		context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=\"width=80px\""));
		context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", Long.parseLong(daerah), "", "",idUnit));

		vm = "app/meps/ppk/frmLaporanRingkasanPermohonanSek17.jsp";

    	   Template template = engine.getTemplate(vm);
           return template;

	}//close template

}//close class
