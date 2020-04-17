/**
 * 
 */
package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;

/**
 * @author elly
 *
 */
public class PopupTambahWaris17 extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(PopupTambahWaris17.class);		
	
	private static final long serialVersionUID = 1L;

	FrmPrmhnnSek8KptsanBicaraData logic = new FrmPrmhnnSek8KptsanBicaraData();

	@Override
	public String doTemplate2() throws Exception {

		myLogger.info(".:PopupTambahWaris Seksyen 17:.");

		HttpSession session = this.request.getSession();

		String vm = "";
		String hitButt = getParam("hitButt");
		System.out.println("hitButt = "+hitButt);

		String id_perbicaraan = getParam("id_perbicaraan");
		String idPermohonanSimati = getParam("idPermohonanSimati");

		Vector senarai_waris = new Vector();

		senarai_waris.clear();

		vm = "app/ppk/frmROTSPopupPilihWaris17.jsp";

		this.context.put("id_perbicaraan", id_perbicaraan);

		// senarai Waris
		logic.setSenaraiWaris17(id_perbicaraan,idPermohonanSimati);
		senarai_waris = logic.getSenaraiWaris17();
		Hashtable h = new Hashtable();
		if (senarai_waris.size()!=0){
			
			h = (Hashtable) senarai_waris.get(0);
			
			if (h.get("bil").toString().equals("")){
				this.context.put("listSize", 0);
			} else {
				this.context.put("listSize", senarai_waris.size());
			}
		}
		this.context.put("dataListWaris", senarai_waris);

		if (hitButt.equals("pilih")) {
			String cbsemaks[] = request.getParameterValues("cbsemaks");
			String temp = null;
			if (cbsemaks != null) {
				for (int i = 0; i < cbsemaks.length; i++) {
					if (temp != null){
						temp = temp + "," + cbsemaks[i];
					} else {
						temp = cbsemaks[i];
					}
				}
			}
			session.setAttribute("listSelectedWaris", temp);
			
		}

		return vm;
	}
}
