/**
 * 
 */
package ekptg.view.integrasi.sid;

import integrasi.rest.sid.SIDManager;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

/**
 * @author Mohd Faizal
 *
 */
public class FrmPopupPaparArkibDokumen extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SIDManager sid = new SIDManager();
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String command = getParam("command");
		System.out.println(" command::::::::::: "+command);
		context.put("command", command);
		
		context.remove("flagMsg");
		context.remove("msg");
		
		Vector listArkib = null;
		
		String kodModul = getParam("kodModul"); //PPK;PPT;PHP;HTP
		String noFail = getParam("noFail").trim();
		
		if (!"".equals(noFail)) {
			if ("PPK".equals(kodModul)) {
				listArkib = sid.getArkibDokumenPPK(noFail);	
				context.put("flagMsg", sid.getFlagMsg());
				context.put("msg", sid.getOutputMsg());
			} else if ("PPT".equals(kodModul)) {
				listArkib = sid.getArkibDokumenPPT(noFail, "-", "-");	
				context.put("flagMsg", sid.getFlagMsg());
				context.put("msg", sid.getOutputMsg());
			} else if ("PHP".equals(kodModul)) {
				listArkib = sid.getArkibDokumenPHP(noFail, "-", "-", "-");	
				context.put("flagMsg", sid.getFlagMsg());
				context.put("msg", sid.getOutputMsg());
			} else if ("HTP".equals(kodModul)) {
				listArkib = sid.getArkibDokumenHTP(noFail, "-", "-");
				context.put("flagMsg", sid.getFlagMsg());
				context.put("msg", sid.getOutputMsg());
			} else {
				context.put("flagMsg", "T");
				context.put("msg", "MODUL TIDAK DIKENALPASTI");
			}
		} else {
			context.put("flagMsg", "T");
			context.put("msg", "NO. FAIL TIDAK DIKENALPASTI");
		}		
		
		
		context.put("listArkib", listArkib);
		setupPage(session, action, listArkib);
		
		vm = "app/integrasi/sid/frmPopupPaparArkibDokumen.jsp";
		return vm;
	}

}
