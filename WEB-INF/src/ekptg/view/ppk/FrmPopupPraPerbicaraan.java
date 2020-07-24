package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmPrmhnnStatusPengunaOnlineData;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;

public class FrmPopupPraPerbicaraan extends AjaxBasedModule {
private static final long serialVersionUID = 1L;
	
	static Logger myLog = Logger.getLogger(FrmPopupPraPerbicaraan.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		Vector senaraiPraBicara = new Vector();
		String vm = "";
		
		senaraiPraBicara = FrmPrmhnnStatusPengunaOnlineData.getPopupPraPerbicaraan("", getParam("idPraPerbicaraan"),"no");
		this.context.put("senaraiPraBicara", senaraiPraBicara);
				
		vm = "app/ppk/frmPopupPraPerbicaraan.jsp";		
	
		return vm;
		
	}
	
}
