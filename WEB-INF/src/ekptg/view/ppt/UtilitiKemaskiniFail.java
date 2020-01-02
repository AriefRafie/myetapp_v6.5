package ekptg.view.ppt;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppt.MyInfoPPTData;

/**
 * @author Razman Md Zainal
 *
 */
public class UtilitiKemaskiniFail extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	
	MyInfoPPTData logic = null;
	static Logger myLogger = Logger.getLogger(UtilitiKemaskiniFail.class);
	

	@Override
	public String doTemplate2() throws Exception {
		logic = new MyInfoPPTData();
		HttpSession session = this.request.getSession();
		
		String vm = ""; 
		
		Vector list = new Vector();
		list.clear();
		
		vm = "app/ppt/UtilitiKemaskiniFail.jsp";
		
		
		list = logic.carianFailUtiliti(getParam("txtNoFail"), session);
		this.context.put("SenaraiFail", list);
	/*	
		String idPerbicaraan = "";
		idPerbicaraan = logic.getIdPerbicaraan(getParam("txtNoFail"));
		this.context.put("id_perbicaraan", idPerbicaraan);		*/
		this.context.put("txtNoFail", getParam("txtNoFail").trim());
		
		return vm;
	}

}
