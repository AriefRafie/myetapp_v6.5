/**
 * 
 */
package ekptg.view.php2.laporan;

import lebah.portal.AjaxBasedModule;

public class PLP_JumlahPermohonanPelepasanDanTukargunaMengikutTahun extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String doTemplate2() throws Exception {
		
		this.context.put("txtTahun", getParam("txtTahun"));
		
		return "app/php2/laporan/PLP_JumlahPermohonanPelepasanDanTukargunaMengikutTahun.jsp";
	}
}
