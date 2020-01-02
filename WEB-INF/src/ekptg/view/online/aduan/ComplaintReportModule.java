package ekptg.view.online.aduan;

import java.util.Vector;

import lebah.portal.action.AjaxModule;
import ekptg.helpers.DB;
import ekptg.model.online.aduan.ComplainStatus;
import ekptg.model.online.aduan.EkptgProcessComplainHandler;
import ekptg.model.online.aduan.IEkptgManageComplaintHandler;

public class ComplaintReportModule extends AjaxModule {
	private final String PATH ="app/online/aduan/report/";
	private String vm = PATH+"index.jsp";
	private String noAduan;
	private String status;
	private String tarikh;
	private IEkptgManageComplaintHandler handler;
	@Override
	public String doAction() throws Exception {
		String command = getParam("command");
		getStatusList();
		getSeksyenList();
		if(command.equals("cari")){
			carian();
			vm = PATH+"index.jsp";
		}else{
			vm = PATH+"index.jsp";
		}
		return vm;
	}
	private void carian(){
		noAduan = getParam("noAduan");
		status = getParam("tindakan");
		tarikh = getParam("tarikhAduan");
		getHandler().search(noAduan, status, tarikh);
	}
	private IEkptgManageComplaintHandler getHandler(){
		if(handler == null)
			 handler = new EkptgProcessComplainHandler();
		return handler;
	}
	private void getStatusList(){
		Vector<String> v = new Vector<String>();
	
		context.put("statuses", ComplainStatus.getList());
	}
	
	private void getSeksyenList()throws Exception{
		Vector v = DB.getSeksyen();
		context.put("sections", v);
	}

}
