package ekptg.view.integrasi.etanah;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.htp.IHtp;
import ekptg.model.integrasi.IIntegrasi;
import ekptg.model.integrasi.IIntegrasiStatus;

public class FrmMaklumateTanah extends AjaxBasedModule {

	private final String PATH = "app/integrasi/etanah/";
	private final String MODUL = "LHDN";
	private final IHtp iHTP = null;
	private final IIntegrasi iIntegrasi = null;
	private final IIntegrasiStatus iStatus = null;
	private static final long serialVersionUID = 1L;
	private final String DISABILITY = " disabled class=\"disabled\" ";
	private final String DISABILITYNUM = " disabled class=\"inputnumberdisabled\" ";
	private final String inputStyle = DISABILITY;
	private final String inputStyleNum = DISABILITYNUM;
	static Logger myLog = Logger
			.getLogger(ekptg.view.integrasi.etanah.FrmMaklumateTanah.class);

	String idUser = "";
	String role = null;
	Utils utils = new Utils();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		role = (String) session.getAttribute("myrole");
		myLog.info("CURRENT ROLE :" + role);
		idUser = (String) session.getAttribute("_ekptg_user_id");
		// GET DEFAULT PARAM
		String vm = "";
		String mode = getParam("mode");
		String hitButt = getParam("hitButt");
		String submit = getParam("command");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ
		// VECTOR
		Vector list = null;
		vm = PATH + "indexeTanah2.jsp";
		String flagAdvSearch = getParam("flagAdvSearch");
		myLog.info(submit + ",action=" + action);
		if (submit.equals("")) {
			list = new Vector();
			// this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp",
			// Long.parseLong(jenisKp), "", "class=\"autoselect\""));
			// this.context.put("txtNoFail", getParam("txtNoFail"));
			// this.context.put("txtPemohon", getParam("txtPemohon"));
			// this.context.put("txtSimati", getParam("txtSimati"));
			// this.context.put("jenisIc", getParam("socJenisKp"));
			// this.context.put("txtIcSimati", getParam("txtIcSimati"));
			// list = getIntegrasi().carianPerintahHQ(role,
			// getParam("txtNoFail"),getParam("txtPemohon")
			// ,
			// getParam("txtSimati"),getParam("socJenisKp"),getParam("txtIcSimati")
			// , session,true);
			// setupPage(session,action,list);

			// ETanah2Stub stub = new ETanah2Stub();
			// IsUserIntegrasi iu = new IsUserIntegrasi();
			// iu.setUser("etanahv2");
			// iu.setKataLaluan("etanahv2");
			// IsUserIntegrasiResponse res = stub.isUserIntegrasi(iu);
			// myLog.info("ETanah2Client 1="+res.is_returnSpecified());

		} else if (submit.equals("tambahFail")) {
			// vm = PATH+"senaraiBaruIndex.jsp";
			myLog.info("submit =" + submit);

		}
		// SET DEFAULT PARAM
		context.put("flagAdvSearch", flagAdvSearch);
		context.put("mode", mode);
		// this.context.put("flagPopup", flagPopup);
		// this.context.put("modePopup", modePopup);
		return vm;

	}

	// private IIntegrasi getIntegrasi(){
	// if(iIntegrasi==null){
	// iIntegrasi = new FrmLHDNBean();
	// }
	// return iIntegrasi;
	//
	// }
	//
	// private IIntegrasiStatus getStatus(){
	// if(iStatus==null){
	// iStatus = new FrmIntegrasiStatusBean();
	// }
	// return iStatus;
	//
	// }
	//
	// private IHtp getIHTP(){
	// if(iHTP== null)
	// iHTP = new HtpBean();
	// return iHTP;
	//
	// }

}