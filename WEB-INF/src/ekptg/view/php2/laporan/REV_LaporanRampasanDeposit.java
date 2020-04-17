/**
 * 
 */
package ekptg.view.php2.laporan;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class REV_LaporanRampasanDeposit extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String doTemplate2() throws Exception {
		
		String bulan = getParam("socBulan");
		
		this.context.put("tahun", getParam("tahun"));
		this.context.put("bulan", getParam("bulan"));
		
		this.context.put("selectBulan",HTML.SelectBulan("socBulan",Utils.parseLong(bulan), null, "style=width:auto"));
		
		return "app/php2/laporan/REV_LaporanRampasanDeposit.jsp";
	}
}
