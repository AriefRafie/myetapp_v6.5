/**
 *
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.php2.FrmREVPopupPerjanjianSewaData;

/**
 * @author mohd faizal
 *
 */
public class FrmREVPopupPerjanjianSewaView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmREVPopupPerjanjianSewaData logic = new FrmREVPopupPerjanjianSewaData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }

	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionPopup = getParam("actionPopup");
	    String submit = getParam("command");
	    String hitButton = getParam("hitButton");

	    String idPerjanjian = getParam("idPerjanjian");
	    String idFail = getParam("idFail");
	    String idHasil = getParam("idHasil");
	    String flagSkrin = getParam("flagSkrin");

	    //VECTOR
        Vector beanMaklumatPerjanjian = null;

        this.context.put("close_window", "");
        this.context.put("error_msg", "");

        vm = "app/php2/frmREVPopupMaklumatPerjanjian.jsp";

        //SEND TO MODEL
        if ("simpan".equals(hitButton)) {
    	  idPerjanjian = logic.savePerjanjian(idFail, idHasil, getParam("txtNoSiri"), getParam("txtTarikhMula"), getParam("txtTempoh"), getParam("txtTarikhTamat"),
    			  	getParam("txtTarikhMulaDasar"), getParam("txtTempohDasar"), getParam("txtTarikhTamatDasar"),getParam("txtKadarSewa"),
					getParam("txtCagaran"), getParam("flagKelulusanDasar"), getParam("txtCatatan"), getParam("modCajSewaan"), getParam("flagPerjanjian"), flagSkrin, session);
    	  this.context.put("close_window", "yes");
      	}

        if ("simpanKemaskini".equals(hitButton)) {
      	  logic.kemaskiniPerjanjian(idFail, idHasil, idPerjanjian, getParam("txtNoSiri"), getParam("txtTarikhMula"), getParam("txtTempoh"), getParam("txtTarikhTamat"),
      			  	getParam("txtTarikhMulaDasar"), getParam("txtTempohDasar"), getParam("txtTarikhTamatDasar"),getParam("txtKadarSewa"),
					getParam("txtCagaran"), getParam("flagKelulusanDasar"), getParam("txtCatatan"), getParam("modCajSewaan"), getParam("flagPerjanjian"), flagSkrin, session);
      	  this.context.put("close_window", "yes");
        }

        if ("hapus".equals(hitButton)) {
        	if ("U".equals(flagSkrin)) {
        		if (logic.getBilPerjanjian(idHasil) > 1){
            		logic.doHapus(idPerjanjian, idHasil, idFail);
            		this.context.put("close_window", "yes");
            	} else {
            		this.context.put("error_msg", "yes");
            	}
        	} else {
        		logic.doHapus(idPerjanjian, idHasil, idFail);
        		this.context.put("close_window", "yes");
        	}
		}

        if ("new".equals(actionPopup)){

        	this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");

			// MAKLUMAT PERJANJIAN
			beanMaklumatPerjanjian = new Vector();
			Hashtable hashPerjanjian = new Hashtable();
			hashPerjanjian.put("noSiri", getParam("txtNoSiri") == null ? "": getParam("txtNoSiri"));
			hashPerjanjian.put("flagKelulusanDasar", getParam("flagKelulusanDasar") == null ? "": getParam("flagKelulusanDasar"));
			hashPerjanjian.put("flagPerjanjian", getParam("flagPerjanjian") == null ? "": getParam("flagPerjanjian"));
			hashPerjanjian.put("tarikhMula",getParam("txtTarikhMula") == null ? "": getParam("txtTarikhMula"));
			hashPerjanjian.put("tempoh", getParam("txtTempoh") == null ? "": getParam("txtTempoh"));
			hashPerjanjian.put("tarikhTamat", getParam("txtTarikhTamat") == null ? "": getParam("txtTarikhTamat"));
			hashPerjanjian.put("kadarSewa", getParam("txtKadarSewa") == null ? "": getParam("txtKadarSewa"));
			hashPerjanjian.put("cagaran", getParam("txtCagaran") == null ? "": getParam("txtCagaran"));
			hashPerjanjian.put("catatan", getParam("txtCatatan") == null ? "": getParam("txtCatatan"));
			hashPerjanjian.put("modCajSewaan", getParam("modCajSewaan") == null ? "": getParam("modCajSewaan"));
			beanMaklumatPerjanjian.addElement(hashPerjanjian);
			this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);

        } else {

        	this.context.put("mode", "update");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");

        	//MAKLUMAT PERJANJIAN
	    	if ("doChangeFlagKelulusanDasar".equals(submit) || "doChangeFlagKelulusanDasar".equals(submit)) {
	    		beanMaklumatPerjanjian = new Vector();
				Hashtable hashPerjanjian = new Hashtable();
				hashPerjanjian.put("noSiri", getParam("txtNoSiri") == null ? "": getParam("txtNoSiri"));
				hashPerjanjian.put("flagKelulusanDasar", getParam("flagKelulusanDasar") == null ? "": getParam("flagKelulusanDasar"));
				hashPerjanjian.put("flagPerjanjian", getParam("flagPerjanjian") == null ? "": getParam("flagPerjanjian"));
				hashPerjanjian.put("tarikhMula",getParam("txtTarikhMula") == null ? "": getParam("txtTarikhMula"));
				hashPerjanjian.put("tempoh", getParam("txtTempoh") == null ? "": getParam("txtTempoh"));
				hashPerjanjian.put("tarikhTamat", getParam("txtTarikhTamat") == null ? "": getParam("txtTarikhTamat"));
				hashPerjanjian.put("kadarSewa", getParam("txtKadarSewa") == null ? "": getParam("txtKadarSewa"));
				hashPerjanjian.put("cagaran", getParam("txtCagaran") == null ? "": getParam("txtCagaran"));
				hashPerjanjian.put("catatan", getParam("txtCatatan") == null ? "": getParam("txtCatatan"));
				hashPerjanjian.put("modCajSewaan", getParam("modCajSewaan") == null ? "": getParam("modCajSewaan"));
				beanMaklumatPerjanjian.addElement(hashPerjanjian);
				this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
	    	} else {
	    		beanMaklumatPerjanjian = new Vector();
				logic.setMaklumatPerjanjian(idPerjanjian);
				beanMaklumatPerjanjian = logic.getBeanMaklumatPerjanjian();
		    	this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
	    	}
        }

        this.context.put("actionPopup", actionPopup);
        this.context.put("idFail", idFail);
        this.context.put("idHasil", idHasil);
        this.context.put("idPerjanjian", idPerjanjian);
        this.context.put("flagSkrin", flagSkrin);

		return vm;
	}

}
