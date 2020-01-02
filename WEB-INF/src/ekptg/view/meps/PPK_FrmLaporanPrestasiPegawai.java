package ekptg.view.meps;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
/**
 * 
 *
 */
public class PPK_FrmLaporanPrestasiPegawai extends AjaxBasedModule {

	static Logger myLogger = Logger.getLogger(PPK_FrmLaporanPrestasiPegawai.class);

	private static final long serialVersionUID = 1L;

	public String doTemplate2() throws Exception {

		System.out.println(".:FrmLaporanPrestasiPegawai:.");

		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "0";
		}
		String idUnit = getParam("socUnit");
		if (idUnit == null || idUnit.trim().length() == 0){
			idUnit = "0";
		}
		String tahun = getParam("socTahun");
		if (tahun == null || tahun.trim().length() == 0){
			tahun = "0";
		}

		this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
		this.context.put("selectUnit",HTML.SelectUnitPPKByNegeri("socUnit", Long.parseLong(idUnit), "", "", idNegeri));
		this.context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=\"width=80px\""));

		return "app/meps/ppk/frmLaporanPrestasiPegawai.jsp";
	}
}
