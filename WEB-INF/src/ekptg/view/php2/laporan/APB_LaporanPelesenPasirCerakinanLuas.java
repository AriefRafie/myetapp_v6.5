/**
 * 
 */
package ekptg.view.php2.laporan;

import lebah.portal.AjaxBasedModule;

public class APB_LaporanPelesenPasirCerakinanLuas extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String doTemplate2() throws Exception {
		
		this.context.put("luasDari", getParam("luasDari"));
		this.context.put("luasHingga", getParam("luasHingga"));
		this.context.put("txtTahun", getParam("txtTahun"));
		
		return "app/php2/laporan/APB_LaporanPelesenPasirCerakinanLuas.jsp";
	}
}
