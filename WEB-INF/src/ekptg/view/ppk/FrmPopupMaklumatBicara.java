package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmPrmhnnStatusPengunaOnlineData;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;

public class FrmPopupMaklumatBicara extends AjaxBasedModule {
private static final long serialVersionUID = 1L;
	
	static Logger myLog = Logger.getLogger(FrmPopupMaklumatBicara.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		Vector senaraiBicara = new Vector();
		String vm = "";
		
		senaraiBicara = FrmPrmhnnStatusPengunaOnlineData.getMaklumatPerbicaraan("", getParam("idBicara"),"no");
		this.context.put("senaraibicara", senaraiBicara);
				
		vm = "app/ppk/frmPopupMaklumatBicara.jsp";		
	
		return vm;
		
	}
	
}
