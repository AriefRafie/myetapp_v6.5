package ekptg.fpx;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;

@SuppressWarnings("serial")
public class FpxReport extends AjaxBasedModule{
	
	private final String PATH="app/fpx/";
	private String vm = PATH +"frmReportFPX.jsp";

	@Override
	public String doTemplate2() throws Exception {
		
		//dropdown
		context.put("selectJenisBayaran",HTML.SelectJenisBayaranFPX("socJenisBayaran",null,null,"style=width:325px"));
		
		return vm;
	}
	
}
