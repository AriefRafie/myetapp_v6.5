package ekptg.view.ppk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;

public class FrmIntegrasiMTList extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6847557413823051020L;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String currentDate = dateFormat.format(date);

	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8Online.class);

	@SuppressWarnings("rawtypes")
	@Override
	public String doTemplate2() throws Exception {

		String vm = "";
		String usid = "";

		HttpSession session = this.request.getSession();
		// String action = getParam("action"); // Second Level
		// String doPost = (String) session.getAttribute("doPost");

		usid = (String) session.getAttribute("_ekptg_user_id");
		this.context.put("usid", usid);

		Vector list = FrmMTBorangC.MTKeputusanPenuh(usid);

		this.context.put("noFail", "");
		this.context.put("namapemohon", "");
		this.context.put("nokppemohon", "");
		int countList = list.size();
		this.context.put("Senaraifail", list);
		this.context.put("CountList", countList);
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
		vm = "app/ppk/integrasi/MTList.jsp";

		return vm;
	}
}