package ekptg.view.pdt;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

public class FrmTambahSenaraiDokumen extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PATH="app/pdt/";
	static Logger myLogger = Logger.getLogger(FrmTambahSenaraiDokumen.class);

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String hitButton = getParam("hitButton");
		myLogger.info("hitButton : "+hitButton);
		
		return PATH+"frmTambahSenaraiDokumen.jsp";
	}

}
