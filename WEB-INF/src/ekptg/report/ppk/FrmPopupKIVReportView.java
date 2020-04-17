/**
 * 
 */
package ekptg.report.ppk;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.FrmRynSek8SemakPenerimaan;
/**
 * 
 *
 */
public class FrmPopupKIVReportView extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	FrmRynSek8SemakPenerimaan model = new FrmRynSek8SemakPenerimaan();
	FrmPrmhnnSek8Notis model2 = new FrmPrmhnnSek8Notis();
	static Logger myLog = Logger.getLogger(ekptg.report.ppk.FrmPopupKIVReportView.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		//System.out.println(".:FrmPopupPilihPegawaiReportView:.");
		HttpSession session = request.getSession();
		
		String vm = "";
		String report = getParam("report");

		//myLog.info("getParam(\"noFail\")="+getParam("noFail"));	
		String userId = (String)session.getAttribute("_ekptg_user_id");
		this.context.put("userId", userId);
		
		context.put("selectPegawai", HTML.SelectPegawaiPengendali(
				"socPegawai", null, "style=width:305"));
		
		vm = "app/ppk/frmPopupCetakKIV.jsp";	
		this.context.put("report", report);	
		
		return vm;
		
	}
	
		
	
}
