package ekptg.view.php2;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPYWPopupCetakUtilitiData;

public class FrmPYWPopupCetakUtilitiView extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmPYWPopupCetakUtilitiData logic = new FrmPYWPopupCetakUtilitiData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;

	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
		userId = (String)session.getAttribute("_ekptg_user_id");
		userRole = (String)session.getAttribute("myrole");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");

		//GET DEFAULT PARAM
	    String vm = "app/php2/frmPYWPopupCetakUtiliti.jsp";
	    
		String report = getParam("report");
		
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0){
			idPegawai = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0) {
			idBandar = "99999";
		}
	
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
		this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
		
		this.context.put("txtNoFailSPHP", getParam("txtNoFailSPHP"));
		this.context.put("txtNoFailPemohon",getParam("txtNoFailPemohon"));
		this.context.put("txtKandungan",getParam("txtKandungan"));
		this.context.put("txtNamaPemohon", getParam("txtNamaPemohon"));
		this.context.put("txtAlamat1", getParam("txtAlamat1"));
		this.context.put("txtAlamat2", getParam("txtAlamat2"));
		this.context.put("txtAlamat3", getParam("txtAlamat3"));
		this.context.put("txtPoskod", getParam("txtPoskod"));
		this.context.put("txtTajukSurat", getParam("txtTajukSurat"));
		this.context.put("selectPegawai",HTML.selectPegawaiUnitPenyewaanByNegeri(idNegeriUser,"socPegawai", Utils.parseLong(idPegawai), "", ""));
		

		this.context.put("idNegeri", idNegeri);
		this.context.put("report", report);
		return vm;
	}

}
