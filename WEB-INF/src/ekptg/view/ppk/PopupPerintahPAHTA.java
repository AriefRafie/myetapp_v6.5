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
public class PopupPerintahPAHTA extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	FrmPerintahPopupPAPTData logic = new FrmPerintahPopupPAPTData();
	static Logger myLogger = Logger.getLogger(PopupPerintahPAHTA.class);

	@Override
	public String doTemplate2() throws Exception {
		
			
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    } 
	    
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
		String idFail = getParam("idFail");
		
		 myLogger.info("MODE POP PA HTA :"+mode);
		 myLogger.info("HITBUTT POP PA HTA :"+hitButt);

		Vector senaraiPA = new Vector();

		senaraiPA.clear();

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
		this.context.put("cetakBorangHH", "0");
		
		if (mode.equals("update")){
			this.context.put("disabled", "");
		} else {
			this.context.put("disabled", "disabled");
		}

		// GET ROW WARIS IF EXIST
		if (logic.checkRowWarisHTA(idOB, idPerintahHTAOBMST)){
			System.out.println(":::::::::::;;waris exist");
			logic.setDataSenaraiPAHTA(idOB, idPerintahHTAOBMST, idPermohonanSimati,idSimati);
			senaraiPA = logic.getSenaraiPAHTA();
			this.context.put("Senarai", senaraiPA);
			
			Hashtable h = (Hashtable) senaraiPA.get(0);
			if (h.get("bil").toString().equals("")){
				this.context.put("listSize", 0);
			} else {
				this.context.put("listSize", senaraiPA.size());
			}
			
			this.context.put("flagRowWaris", "Y");
			
		} else {
			System.out.println(":::::::::::;; not waris exist xxx");
			this.context.put("flagRowWaris", "T");
		}
		
		//SAVE
		if (hitButt.equals("simpan")){
			String idPA1 = "";
			String idPA2 = "";
			String idPA3 = "";
			String idPA4 = "";
			
			String ids[] = request.getParameterValues("ids");
			String cetakBorangHH = getParam("cetakBorangHH");
			String idFail2 = getParam("idFail");
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
			
			logic.updatePAHTA(idOB, idPerintahHTAOBMST, idPA1, idPA2, idPA3, idPA4, session);
			
			
			//aishah add
			logic.deletePENJAGA(idOB);
			
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					logic.insertTBLPPKPENJAGA(idOB, idPerintahHTAOBMST,ids[i], session);
				}
			}
			
			this.context.put("cetakBorangHH", cetakBorangHH);
			this.context.put("idFail", idFail2);
			this.context.put("close_window", "no");
			
		}

		return vm;
	}
}
