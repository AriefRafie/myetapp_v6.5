/**
 * 
 */
package ekptg.view.ppk;

import integrasi.rest.etanah.wpkl.ppk.EtanahWPKLPPKManager;
import integrasi.ws.etanah.melaka_ns.ppk.EtanahN9MelPPKManager;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.ppk.PopupIntergrasiPerintahEtanahData;

/**
 * @author mohd faizal
 *
 */
public class PopupIntergrasiPerintahEtanahView extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	
	PopupIntergrasiPerintahEtanahData logic = new PopupIntergrasiPerintahEtanahData();
	
	EtanahN9MelPPKManager manager = new EtanahN9MelPPKManager();

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		 String idPengguna = (String)session.getAttribute("_ekptg_user_id");

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPopup = getParam("actionPopup");
		String hitButton = getParam("hitButton");
		String submit = getParam("command");
		String idFail = getParam("idFail");
		String idPerintah = getParam("idPerintah");
		
		//VECTOR
        Vector beanMaklumatPerintah = null;
        Vector senaraiHakmilik = null;
        
        this.context.put("flagMsg", "");
		this.context.put("outputMsg", "");
        
        vm = "app/ppk/frmPopupIntergrasiPerintahEtanah.jsp";	
        
        if ("hantar".equals(hitButton)){
        	String noFail = logic.getNoFailByIdFail(idFail);
			EtanahN9MelPPKManager.sendPerintahToEtanah(noFail);
			EtanahWPKLPPKManager.sendPerintahToEtanah(noFail, idPengguna);
        }
		
        beanMaklumatPerintah = new Vector();		
		logic.getMaklumatPerintah(idFail);
		beanMaklumatPerintah = logic.getBeanMaklumatPerintah();
		this.context.put("BeanMaklumatPerintah", beanMaklumatPerintah);
		
		senaraiHakmilik = new Vector();		
		logic.getSenaraiHakmilik(idFail);
		senaraiHakmilik = logic.getListHakmilik();
		this.context.put("SenaraiHakmilik", senaraiHakmilik);
		
		this.context.put("flagHantar", logic.getFlagHantar(idFail));
		
		return vm;
	}

}
