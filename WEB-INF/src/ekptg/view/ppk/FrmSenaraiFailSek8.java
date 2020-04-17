package ekptg.view.ppk;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.ppk.FrmSenaraiFailSek8Data;
/**
 * 
 *
 */
public class FrmSenaraiFailSek8 extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	
	FrmSenaraiFailSek8Data logic = new FrmSenaraiFailSek8Data();

	@Override
	public String doTemplate2() throws Exception {

		System.out.println(".:FrmSenaraiFailSek8:.");
		
		HttpSession session = this.request.getSession();
		
		String vm = ""; 
		
		Vector list = new Vector();
		list.clear();
		
		vm = "app/ppk/frmSenaraiFailSek8.jsp";
		
		//GET LIST 
		logic.carianFail(getParam("txtNoFail"), session);
		list = logic.getSenaraiFail();
		this.context.put("SenaraiFail", list);
		
		//GET ID_PERBICARAAN FOR KEPUTUSAN PERBICARAAN
		String idPerbicaraan = "";
		idPerbicaraan = logic.getIdPerbicaraan(getParam("txtNoFail"));
		this.context.put("id_perbicaraan", idPerbicaraan);
		
		this.context.put("txtNoFail", getParam("txtNoFail").trim());
		
		return vm;
	}

}
