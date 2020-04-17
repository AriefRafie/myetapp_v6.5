package ekptg.view.pdt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;


public class FrmEnakmenPindaan extends AjaxBasedModule {
	private static Logger myLogger = Logger.getLogger(FrmEnakmenPindaan.class);

	String noEnakmen = "";
	String namaEnakmen = "";
	String tarikhKuatkuasa = "";
	String noWarta = "";
	String tarikhWarta = "";
	String catatan = "";
	String tarikhDaftar = "";
	String idKeselamatan = "";
	String noPenggal = "";
	String tajukPenggal = "";
	String tarikhMansuh = "";
	
	Vector listSenarai = null;

	Date now = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	HttpSession session = this.request.getSession();
	
	String user_id = (String) session.getAttribute("_ekptg_user_id");
	String idEnakmen = getParam("idEnakmen");
	
	public String doTemplate2() throws Exception {
		String vm = "";
	
		myLogger.debug("vm:"+vm);
		//myLogger.debug("action..."+action);
		return vm;
	}	 
	

}
