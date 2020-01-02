/**
 * @author norzaily jasmi
 */
package ekptg.view.ppk;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;

public class PopupTambahWaris extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	FrmPrmhnnSek8KptsanBicaraData logic = new FrmPrmhnnSek8KptsanBicaraData();

	@Override
	public String doTemplate2() throws Exception {

		System.out.println(".:PopupTambahWaris:.");

		HttpSession session = this.request.getSession();

		String vm = "";
		String hitButt = getParam("hitButt");
		System.out.println("hitButt = "+hitButt);

//		int id_perbicaraan = Integer.parseInt(getParam("id_perbicaraan"));
		String id = getParam("idPermohonan");
		String idPermohonanSimati = getParam("idPermohonanSimati");
		context.put("idPermohonan", id);
		context.put("idPermohonanSimati", idPermohonanSimati);

		Vector senarai_waris = new Vector();

		senarai_waris.clear();

		vm = "app/ppk/frmROTSPopupPilihWaris.jsp";

//		this.context.put("id_perbicaraan", id_perbicaraan);

		// senarai Waris
		logic.setSenaraiWaris(id,idPermohonanSimati);
		senarai_waris = logic.getSenaraiWaris();
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
