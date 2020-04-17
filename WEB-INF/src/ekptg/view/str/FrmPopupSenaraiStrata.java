package ekptg.view.str;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class FrmPopupSenaraiStrata  extends AjaxBasedModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3576494755269810197L;

	@Override
	public String doTemplate2() throws Exception {
		String vm = "";
		String submit = getParam("command");
		String paramDaerah = getParam("paramDaerah");
		String paramNegeri = getParam("paramNegeri");
		String id_tahunDari = "";
		String id_tahunHingga = "";
		
		// String doPost = (String) session.getAttribute("doPost");
		// Maklumat Hakmilik Pembangunan Strata
		if("doFilter".equals(submit)){
			
			if (id_tahunDari.equals("")) {
				id_tahunDari = "";
			} else {
				id_tahunDari = getParam("socTahunDari");
			}
			if (id_tahunHingga.equals("")) {
				id_tahunHingga = "";
			} else {
				id_tahunHingga = getParam("socTahunHingga");
			}

			if (paramNegeri == null || paramNegeri.trim().length() == 0){
				paramNegeri = "99999";
			}
			
			if (paramDaerah == null || paramDaerah.trim().length() == 0){
				paramDaerah = "99999";
			}
			
			this.context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			this.context.put("selectTahunHingga", HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			this.context.put("selectNegeri", HTML.SelectNegeri("paramNegeri", Utils.parseLong(getParam("paramNegeri")), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(getParam("paramNegeri"), "paramDaerah", Long.parseLong(paramDaerah), ""));
			this.context.put("selectKodLot",HTML.SelectLot("paramKodLot", Utils.parseLong(getParam("paramKodLot")), "style=width:auto "));
			
			this.context.put("paramNoLot",getParam("paramNoLot"));
			this.context.put("paramNoCF",getParam("paramNoCF"));
			this.context.put("paramNamaPemaju",getParam("paramNamaPemaju"));
			this.context.put("paramNamaSkim",getParam("paramNamaSkim"));
			this.context.put("paramOrderBy",getParam("paramOrderBy"));
		}else{
			this.context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			this.context.put("selectTahunHingga", HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			this.context.put("selectNegeri", HTML.SelectNegeri("paramNegeri", Utils.parseLong(""), "","onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerah("paramDaerah", Utils.parseLong(""), ""));
			this.context.put("selectKodLot",HTML.SelectLot("paramKodLot", Utils.parseLong(getParam("paramKodLot")), "style=width:auto "));
		}
		
		vm = "app/str/frmSenaraiStrata.jsp";
		System.out.println("command"+submit);
		return vm;
	}
}