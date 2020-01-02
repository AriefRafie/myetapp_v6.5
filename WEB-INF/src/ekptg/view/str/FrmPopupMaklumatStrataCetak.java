package ekptg.view.str;

import lebah.portal.AjaxBasedModule;

public class FrmPopupMaklumatStrataCetak extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6776676835649199512L;

	@Override
	public String doTemplate2() throws Exception {
		String vm = "";
		this.context.put("paramNegeri", getParam("paramNegeri"));
		this.context.put("paramKodLot", getParam("paramKodLot"));
		this.context.put("paramNoLot", getParam("paramNoLot"));
		this.context.put("paramNoCF", getParam("paramNoCF"));
		this.context.put("paramNamaPemaju", getParam("paramNamaPemaju"));
		this.context.put("paramNamaSkim", getParam("paramNamaSkim"));
		
		vm = "app/str/frmPopupCetakMaklumatStrata.jsp";
		return vm;
	}
}