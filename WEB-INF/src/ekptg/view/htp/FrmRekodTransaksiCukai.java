package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmRekodTransaksiCukaiData;

public class FrmRekodTransaksiCukai extends AjaxBasedModule {
	private static final long serialVersionUID = 1L;
//	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmRekodTransaksiCukai.class);
	
	@Override
	public String doTemplate2() throws Exception {	
		String vm = "";
		HttpSession session = this.request.getSession();		
		vm = "app/htp/frmRekodTransaksiCukai.jsp";
		// VIEW TRANSAKSI CUKAI
		view_modeTransaksiCukai(session);
		return vm;
		
	}

	// VIEW TRANSAKSI CUKAI BY ID
	private void view_modeTransaksiCukai(HttpSession session) throws Exception {
		String idString = getParam("idHakmilik");
		Vector<Hashtable<String,String>> list =null;
		list = FrmRekodTransaksiCukaiData.getTransaksiCukaiById(idString);
		this.context.put("SenaraiTransaksi",list);
		
	}
	
}