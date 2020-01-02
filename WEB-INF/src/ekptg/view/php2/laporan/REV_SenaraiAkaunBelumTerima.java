/**
 * 
 */
package ekptg.view.php2.laporan;

import java.text.SimpleDateFormat;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;

public class REV_SenaraiAkaunBelumTerima extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	

	@Override
	public String doTemplate2() throws Exception {
				
		String idUsiaABT = getParam("socUsiaABT");
		if (idUsiaABT == null || idUsiaABT.trim().length() == 0){
			idUsiaABT = "99999";
		}
		
		this.context.put("selectUsiaABT", HTML.SelectUsiaABT("socUsiaABT", Long.parseLong(idUsiaABT), "", ""));
		return "app/php2/laporan/REV_SenaraiAkaunBelumTerima.jsp";
	}
}
