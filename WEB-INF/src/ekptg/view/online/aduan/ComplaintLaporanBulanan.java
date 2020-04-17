package ekptg.view.online.aduan;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class ComplaintLaporanBulanan extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String doTemplate2() throws Exception {
		
		String idBulan = getParam("socBulan");
		if (idBulan == null || idBulan.trim().length() == 0) {
			idBulan = "99999";
		}		
		this.context.put("txtTahun", getParam("txtTahun"));
		
		context.put("selectBulan",HTML.SelectBulan("socBulan", Utils.parseLong(idBulan), null, "style=width:130px"));
		
		return "app/online/aduan/report/frmAduanLaporanBulanan.jsp";
	}
}