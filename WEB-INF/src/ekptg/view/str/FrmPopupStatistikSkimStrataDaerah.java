package ekptg.view.str;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class FrmPopupStatistikSkimStrataDaerah extends AjaxBasedModule{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8022441973420129708L;

	@Override
	public String doTemplate2() throws Exception {
		String vm = "";
		String paramNegeri = "";
		String report = "";
		this.context.put("selectNegeriD",HTML.SelectNegeri("paramNegeri", Utils.parseLong(paramNegeri), ""));
		paramNegeri = getParam("paramNegeri");
		report = getParam("report");
		
		vm = "app/str/frmStatistikSkimStrataDaerah.jsp";
		return vm;
	}

}

