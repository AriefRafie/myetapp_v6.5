package ekptg.view.ppk;

import lebah.portal.AjaxBasedModule;

public class FrmCalculator extends AjaxBasedModule{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1538785745933794122L;

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		vm="app/ppk/Calculator.html";
		return vm;
	}
}