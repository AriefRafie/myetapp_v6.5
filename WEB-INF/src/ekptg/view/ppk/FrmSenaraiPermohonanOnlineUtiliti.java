/**
 * 
 */
package ekptg.view.ppk;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmSenaraiFailOnlineData;



public class FrmSenaraiPermohonanOnlineUtiliti extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmSenaraiPermohonanOnlineUtiliti.class);

	FrmSenaraiFailOnlineData logic_E = new FrmSenaraiFailOnlineData();
	
	List list = null;

	@Override
	public String doTemplate2() throws Exception {
		
		
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		
		String vm = "app/ppk/frmSenaraiPermohonanOnlineUtiliti.jsp";
	
		String action = getParam("action"); 
		String command = getParam("command");
		myLogger.info("Command :"+command);
		
		list = Collections.synchronizedList(new ArrayList());
		
		this.context.put("usid", userId);
		this.context.put("no_fail", "");
		this.context.put("myid", "");
		this.context.put("seksyen", "");
		this.context.put("nama", "");
		
		this.context.put("tarikhMula", "");
		this.context.put("tarikhAkhir", "");
		
		String no_fail = "";
		String myid = "";
		String seksyen = "";
		String nama = "";
		String tarikhMula = "";
		String tarikhAkhir = "";
		
		if(command.equals("Cari"))
		{
			this.context.put("no_fail", getParam("no_fail"));
			this.context.put("myid", getParam("myid"));
			this.context.put("seksyen", getParam("seksyen"));
			this.context.put("nama", getParam("nama"));
			this.context.put("tarikhMula", getParam("tarikhMula"));
			this.context.put("tarikhAkhir", getParam("tarikhAkhir"));
			no_fail = getParam("no_fail");
			myid = getParam("myid");
			seksyen = getParam("seksyen");
			nama = getParam("nama");
			tarikhMula = getParam("tarikhMula");
			tarikhAkhir = getParam("tarikhAkhir");
		}
		
		list = logic_E.setListOnlineUtiliti(tarikhMula, tarikhAkhir,userId,no_fail,myid,seksyen,nama);	
		
		myLogger.info("PRINT LIST"+list);
		
		this.context.put("SenaraiFail", list);
		setupPage(session,action,list);
		
	
		
		
		
		return vm;
	}

}
/*
	public String doTemplate2() throws Exception{
		
		
			HttpSession session = request.getSession();
			String usid = (String) session.getAttribute("_ekptg_user_id");
			String action = getParam("action");
			list = Collections.synchronizedList(new ArrayList());			
			this.context.put("usid", usid);
			this.context.put("noFail", "");
			this.context.put("namapemohon", "");
			this.context.put("nokppemohon", "");
			list = logic_E.setListOnlineUtiliti(usid);
			this.context.put("Senaraifail", list);						
			this.context.put("JumlahFail",list.size());
			setupPage(session,action,list);
			vm = "app/ppk/frmSenaraiPermohonanOnlineUtiliti.jsp";

		

		return vm;

*/
