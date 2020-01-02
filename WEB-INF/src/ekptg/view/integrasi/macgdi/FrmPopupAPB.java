/**
 * 
 */
package ekptg.view.integrasi.macgdi;

import lebah.portal.AjaxBasedModule;

public class FrmPopupAPB extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String id = getParam("id");
		
		context.put("id", id);
		
	
		this.context.put("txtTahun", getParam("txtTahun"));
		
		
		return "app/integrasi/macgdi/APB_LaporanKeseluruhanPelesenPasir.jsp";
	}
}
