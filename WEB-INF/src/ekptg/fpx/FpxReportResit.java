package ekptg.fpx;

import lebah.portal.AjaxBasedModule;

@SuppressWarnings("serial")
public class FpxReportResit extends AjaxBasedModule{
	
	private final String PATH="app/fpx/";
	private String vm = PATH +"frmReportResitFPX.jsp";

	@Override
	public String doTemplate2() throws Exception {
		return vm;
	}
	
}
