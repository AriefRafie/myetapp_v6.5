/**
 * 
 */
package ekptg.view.htp;

import integrasi.ws.etanah.melaka_ns.htp.EtanahHTPManager;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.htp.FrmPopupIntergrasiEtanahData;

/**
 * @author mohd faizal
 * 
 */
public class FrmPopupIntergrasiEtanahView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmPopupIntergrasiEtanahData logic = new FrmPopupIntergrasiEtanahData();

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPopup = getParam("actionPopup");
		String hitButt = getParam("hitButt");
		String submit = getParam("command");
		
		String idHakmilik = getParam("idHakmilik");
		String kodNegeri = logic.getKodNegeri(idHakmilik);
		
		vm = "app/htp/frmPopupIntergrasiEtanah.jsp";
		
		Vector beanMaklumatHakmilik = null;
		
		if ("hantar".equals(hitButt)){
			EtanahHTPManager.updateMaklumatAgensi(getParam("peganganHakmilik"), getParam("kodKementerian"), getParam("namaKementerian"), getParam("kodAgensi"), getParam("namaAgensi"), getParam("kegunaanTanah"), kodNegeri);
		}
		
		beanMaklumatHakmilik = new Vector();
		logic.setMaklumatHakmilik(idHakmilik);
		beanMaklumatHakmilik = logic.getBeanMaklumatHakmilik();
		this.context.put("BeanMaklumatHakmilik", beanMaklumatHakmilik);
		
		return vm;
	}

}
