/**
 * 
 */
package ekptg.view.htp;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.htp.FrmPopupIntergrasiCukaiEtanahData;

/**
 * @author mohd faizal
 * 
 */
public class FrmPopupIntergrasiCukaiEtanahView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmPopupIntergrasiCukaiEtanahData logic = new FrmPopupIntergrasiCukaiEtanahData();

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPopup = getParam("actionPopup");
		String hitButt = getParam("hitButt");
		String submit = getParam("command");
		
		String idHakmilik = getParam("txtIDHakmilik");
		String tahun = getParam("txtTahun");
		
		vm = "app/htp/frmPopupIntergrasiCukaiEtanah.jsp";
		
		if ("getMaklumatCukai".equals(hitButt)){
			logic.getMaklumatCukai(idHakmilik, tahun);
		} else if ("getMaklumatBayaran".equals(hitButt)){
			logic.getMaklumatBayaran(idHakmilik, tahun);
		}
		
		this.context.put("txtIDHakmilik", idHakmilik);
		this.context.put("txtTahun", tahun);
		return vm;
	}

}
