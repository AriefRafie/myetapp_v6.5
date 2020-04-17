package ekptg.view.online.aduan;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class ComplaintLaporanKumulatif extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String doTemplate2() throws Exception {
		
		String idBulanMula = getParam("socBulanMula");
		if (idBulanMula == null || idBulanMula.trim().length() == 0) {
			idBulanMula = "99999";
		}	
		String idBulanAkhir = getParam("socBulanAkhir");
		if (idBulanAkhir == null || idBulanAkhir.trim().length() == 0) {
			idBulanAkhir = "99999";
		}
		this.context.put("txtTahun", getParam("txtTahun"));
		
		context.put("selectBulanMula",HTML.SelectBulan("socBulanMula", Utils.parseLong(idBulanMula), null, "style=width:130px"));
		context.put("selectBulanAkhir",HTML.SelectBulan("socBulanAkhir", Utils.parseLong(idBulanAkhir), null, "style=width:130px"));
		
		return "app/online/aduan/report/frmAduanLaporanKumulatif.jsp";
	}
}
