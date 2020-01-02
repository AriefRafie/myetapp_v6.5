package ekptg.view.ppk;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmPerintahPopupPAPTData;

public class PopupPerintahPembahagianSerentak extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	
	FrmPerintahPopupPAPTData logic = new FrmPerintahPopupPAPTData();
	
	static Logger myLogger = Logger.getLogger(PopupPerintahPembahagianSerentak.class);

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		String vm = "";
		String hitButt = getParam("hitButt");

		String idPerintah = getParam("idPerintah");
		String idPerintahHTAOBMST = getParam("idPerintahHTAOBMST");
		String idPerintahHAOBMST = getParam("idPerintahHAOBMST");
		
		System.out.println("idPerintah :: "+idPerintah);
		System.out.println("idPerintahHTAOBMST :: "+idPerintahHTAOBMST);
		System.out.println("idPerintahHAOBMST :: "+idPerintahHAOBMST);
		
		this.context.put("idPerintah", idPerintah);
		this.context.put("idPerintahHTAOBMST", idPerintahHTAOBMST);
		this.context.put("idPerintahHAOBMST", idPerintahHAOBMST);
		
		Vector senaraiHTA = new Vector();
		Vector senaraiHA = new Vector();
		senaraiHTA.clear();
		senaraiHA.clear();
		
		this.context.put("close_window", "");
		
		//aishah close
		senaraiHTA = logic.getSenaraiHTA(idPerintah, idPerintahHTAOBMST);
		this.context.put("SenaraiHTA", senaraiHTA);
		
		
		
		senaraiHA = logic.getSenaraiHA(idPerintah, idPerintahHAOBMST);
		this.context.put("SenaraiHA", senaraiHA);		
		
		vm = "app/ppk/frmPerintahPopupPembahagianSerentak.jsp";
		
		
		//SAVE
		if (hitButt.equals("simpan")){
			
			String idsHTA[] = request.getParameterValues("idsHTA");
			String idsHA[] = request.getParameterValues("idsHA");
			
			System.out.println("idsHTA==="+idsHTA);
			System.out.println("idsHA==="+idsHA);

			logic.applyToAllHarta(idPerintah, idPerintahHTAOBMST, idPerintahHAOBMST, idsHTA, idsHA ,session);
			this.context.put("close_window", "yes");
		}
		
		return vm;
	}

}
