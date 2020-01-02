package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

public class FrmRekodTukargunaPenawaranPelepasan extends AjaxBasedModule {
	@Override
	public String doTemplate2() throws Exception {

		String vm = "";
		Vector list =null;
		HttpSession session = this.request.getSession();
		
		String action = getParam("action");
		this.context.put("action",action);
		
		//VM UNTUK LIST
		vm = "app/htp/frmRekodSenaraiTukargunaPenawaranPelepasan.jsp";
		
		// VM UNTUK VIEW
		//vm = "app/htp/frmRekodTukargunaPenawaranPelepasan.jsp";
		return vm;
	}
}
