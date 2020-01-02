package ekptg.view.integrasi;

import lebah.portal.AjaxBasedModule;
//import org.apache.log4j.Logger;

public class FrmViewSemakJPJ extends AjaxBasedModule{
	private static final long serialVersionUID = 2273575303862138590L;
	//private static Logger myLog = Logger.getLogger(ekptg.view.integrasi.FrmViewSemakJPJ.class);
	
	@Override
	public String doTemplate2() throws Exception {
		String  vm = "app/integrasi/jpj/index.jsp";
		return vm;
		
	}

	
}
