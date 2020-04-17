/**
 * 
 */
package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmPerintahPopupPAPTData;

/**
 * 
 *
 */
public class PopupPerintahPTHTA extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	FrmPerintahPopupPAPTData logic = new FrmPerintahPopupPAPTData();
	
	static Logger myLogger = Logger.getLogger(PopupPerintahPTHTA.class);

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		String vm = "";
		String hitButt = getParam("hitButt");

		String idOB = getParam("idOB");
		String idPerintahHTAOBMST = getParam("idPerintahHTAOBMST");
		String idPermohonanSimati = getParam("idPermohonanSimati");
		String idSimati = getParam("idSimati");
		String statusWaris = getParam("statusWaris");
		String idPerintah = getParam("idPerintah");
		String mode = getParam("mode");
		String flag_kemaskini_selesai = getParam("flag_kemaskini_selesai");

		Vector senaraiPT = new Vector();

		senaraiPT.clear();

		vm = "app/ppk/frmPerintahPopupPilihPAPT.jsp";

		this.context.put("idOB", idOB);
		this.context.put("idPerintahHTAOBMST", idPerintahHTAOBMST);
		this.context.put("idPermohonanSimati", idPermohonanSimati);
		this.context.put("idSimati", idSimati);
		this.context.put("statusWaris", statusWaris);
		this.context.put("idPerintah", idPerintah);
		this.context.put("mode", mode);
		this.context.put("flag_kemaskini_selesai", flag_kemaskini_selesai);
		this.context.put("close_window", "");
		
		if (mode.equals("update")){
			this.context.put("disabled", "");
		} else {
			this.context.put("disabled", "disabled");
		}

		// GET ROW WARIS IF EXIST
		if (logic.checkRowWarisHTA(idOB, idPerintahHTAOBMST)){
			myLogger.info("POPUP ID_OB:"+idOB);
			if (idOB.equals("")){
				logic.setDataSenaraiPTTiadaWarisHTA(idPerintahHTAOBMST, idPermohonanSimati,idSimati);
			} else {
				logic.setDataSenaraiPTHTA(idOB, idPerintahHTAOBMST, idPermohonanSimati,idSimati);
			}
			
			senaraiPT = logic.getSenaraiPT();
			this.context.put("Senarai", senaraiPT);
			
			Hashtable h = (Hashtable) senaraiPT.get(0);
			if (h.get("bil").toString().equals("")){
				this.context.put("listSize", 0);
			} else {
				this.context.put("listSize", senaraiPT.size());
			}
			
			this.context.put("flagRowWaris", "Y");
		} else {
			
			this.context.put("flagRowWaris", "T");
		}
		
		//SAVE
		if (hitButt.equals("simpan")){
			String idPA1 = "";
			String idPA2 = "";
			String idPA3 = "";
			String idPA4 = "";
			String ids[] = request.getParameterValues("ids");
			
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					if (i == 0){
						idPA1 = ids[i];
					} else if (i == 1){
						idPA2 = ids[i];
					} else if (i == 2){
						idPA3 = ids[i];
					} else if (i == 3){
						idPA4 = ids[i];
					}
				}
			}
			myLogger.info("updatePentadbir( control");
//			logic.updatePentadbir(idPA1, idPA2, idPA3, idPA4, idPerintah, session);
			logic.updatePentadbirHTA(idOB, idPerintahHTAOBMST, idPA1, idPA2, idPA3, idPA4, idPerintah, session);
			this.context.put("close_window", "yes");
		}

		return vm;
	}
}
