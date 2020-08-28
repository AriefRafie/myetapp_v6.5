
package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmPYWSenaraiMesyuaratData;
import ekptg.model.php2.utiliti.PHPUtilHTML;

/**
 * @author nurulain
 * 
 */
public class FrmPYWSenaraiMesyuaratView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(ekptg.view.php2.FrmPYWSenaraiMesyuaratView.class);

	FrmPYWSenaraiMesyuaratData logic = new FrmPYWSenaraiMesyuaratData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String)session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		userId = (String)session.getAttribute("_ekptg_user_id");
		userRole = (String)session.getAttribute("myrole");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
		
		this.context.put("userId", userId);
		this.context.put("userRole", userRole);
		this.context.put("idNegeriUser", idNegeriUser);

		// GET DEFAULT PARAM
		String vm = "";
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String step = getParam("step");
		String submit = getParam("command");
		String hitButton = getParam("hitButton");
		String mode = getParam("mode");
		String actionMesyuarat = getParam("actionMesyuarat");
		String selectedTabUpper = getParam("selectedTabUpper").toString();
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}
        if (mode.isEmpty()){
        	mode = "view";
        }

		// GET ID PARAM
		String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String idMesyuarat = getParam("idMesyuarat");
        String idKehadiran = getParam("idKehadiran");
		String flagPermohonanDari = getParam("flagPermohonanDari");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		
		// VECTOR
		Vector list = null;
		Vector senaraiMesyuarat = null;
		Vector beanMaklumatMesyuarat = null;
		Vector beanMaklumatKehadiran = null;
		Vector senaraiKehadiran = null;
		Vector senaraiFailMohonBaru = null;
		Vector beanMaklumatPengerusi = null;

		// GET DROPDOWN PARAM
		String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}
		String idLokasi = getParam("socLokasi");
		if (idLokasi == null || idLokasi.trim().length() == 0){
			idLokasi = "99999";
		}
		String idJamDari = getParam("socJamDari");
		if (idJamDari == null || idJamDari.trim().length() == 0){
			idJamDari = "99999";
		}
		String idMinitDari = getParam("socMinitDari");
		if (idMinitDari == null || idMinitDari.trim().length() == 0){
			idMinitDari = "99999";
		}
		String idJamHingga = getParam("socJamHingga");
		if (idJamHingga == null || idJamHingga.trim().length() == 0){
			idJamHingga = "99999";
		}
		String idMinitHingga = getParam("socMinitHingga");
		if (idMinitHingga == null || idMinitHingga.trim().length() == 0){
			idMinitHingga = "99999";
		}
		String idPegawai = getParam("socPegawai");
		if (idPegawai == null || idPegawai.trim().length() == 0) {
			idPegawai = "99999";
		}
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
				
		//HITBUTTON
		if (postDB) {
			if ("simpanMesyuarat".equals(hitButton)) {
				idMesyuarat = logic.simpanMesyuarat(getParam("txtTarikhMesyuarat"), 
						getParam("txtBilMesyuarat"), getParam("txtTujuanMesyuarat"), idJamDari, idMinitDari,
						idJamHingga, idMinitHingga, getParam("txtCatatanMesyuarat"), idLokasi, session);
			}
			if ("simpanKemaskiniMesyuarat".equals(hitButton)) {
				logic.simpanKemaskiniMesyuarat(idMesyuarat, getParam("txtTarikhMesyuarat"),
						getParam("txtBilMesyuarat"), getParam("txtTajukMesyuarat"), idJamDari, idMinitDari,
						idJamHingga, idMinitHingga, getParam("txtCatatanMesyuarat"), idLokasi, session);
			}
		}		
		
		myLogger.info("actionPenyewaan : " +actionMesyuarat);
		myLogger.info("mode : " +mode);
		
		if ("daftarBaru".equals(actionMesyuarat)) {
			
			vm = "app/php2/frmPYWDaftarMesyuarat.jsp";
			
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");
			
			this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "", ""));
			this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "", ""));
			this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamDari), "", ""));
			this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitDari), "", ""));
			this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "", ""));
			
		} else if ("papar".equals(actionMesyuarat)) {
			
			vm = "app/php2/frmPYWMaklumatMesyuarat.jsp";
			
			if ("1".equals(selectedTabUpper)) {

				if ("update".equals(modePopup)) {

					this.context.put("readonlyPopup", "");
					this.context.put("inputTextClassPopup", "");

					beanMaklumatKehadiran = new Vector();
					logic.setMaklumatKehadiran(idKehadiran);
					beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
					this.context.put("BeanMaklumatKehadiran", beanMaklumatKehadiran);

				} else {

					this.context.put("readonlyPopup", "readonly");
					this.context.put("inputTextClassPopup", "disabled");

					beanMaklumatKehadiran = new Vector();
					logic.setMaklumatKehadiran(idKehadiran);
					beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
					this.context.put("BeanMaklumatKehadiran", beanMaklumatKehadiran);
				}

				// SENARAI KEHADIRAN MESYUARAT
				logic.setSenaraiKehadiran(idMesyuarat);
				senaraiKehadiran = logic.getListKehadiran();
				this.context.put("SenaraiKehadiran", senaraiKehadiran);

			} else if ("2".equals(selectedTabUpper)) {
				
				// SENARAI MESYUARAT PERMOHONAN BAHARU
				logic.setSenaraiPermohonanBaharu(idMesyuarat);
				senaraiFailMohonBaru = logic.getListPermohonanBaharu();
				this.context.put("SenaraiFailMohonBaru", senaraiFailMohonBaru);
				
			} else {

				// MODE VIEW
				if ("view".equals(mode)) {

					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");

					beanMaklumatMesyuarat = new Vector();
					logic.setMaklumatMesyuarat(idMesyuarat);
					beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
					this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);
					if (beanMaklumatMesyuarat.size() != 0) {
						Hashtable hashMesyuarat = (Hashtable) logic.getBeanMaklumatMesyuarat().get(0);
						idLokasi = (String) (hashMesyuarat.get("idLokasi"));
						idJamDari = (String) (hashMesyuarat.get("idJamDari"));
						idMinitDari = (String) (hashMesyuarat.get("idMinitDari"));
						idJamHingga = (String) (hashMesyuarat.get("idJamHingga"));
						idMinitHingga = (String) (hashMesyuarat.get("idMinitHingga"));
					}

					this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(idLokasi), "disabled", " class=\"disabled\""));
					this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "disabled", " class=\"disabled\""));
					this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "disabled", " class=\"disabled\""));
					this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamHingga), "disabled", " class=\"disabled\""));
					this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitHingga), "disabled", " class=\"disabled\""));

				} else {

					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");

					beanMaklumatMesyuarat = new Vector();
					logic.setMaklumatMesyuarat(idMesyuarat);
					beanMaklumatMesyuarat = logic.getBeanMaklumatMesyuarat();
					this.context.put("BeanMaklumatMesyuarat", beanMaklumatMesyuarat);

					this.context.put("selectJamDari", HTML.SelectJam("socJamDari", Long.parseLong(idJamDari), "", ""));
					this.context.put("selectMinitDari", HTML.SelectMinit("socMinitDari", Long.parseLong(idMinitDari), "", ""));
					this.context.put("selectJamHingga", HTML.SelectJam("socJamHingga", Long.parseLong(idJamDari), "", ""));
					this.context.put("selectMinitHingga", HTML.SelectMinit("socMinitHingga", Long.parseLong(idMinitDari), "", ""));
					this.context.put("selectLokasi", HTML.SelectLokasiMesyuarat("socLokasi", Long.parseLong(idLokasi), "", ""));
				}
			}
			
		} else {
			
			// GO TO LIST SENARAI MESYUARAT
			vm = "app/php2/frmPYWSenaraiFailMesyuarat.jsp";

			logic.carianSenaraiMesyuarat(getParam("txtTajukMesyuarat"), getParam("txtBilMesyuarat"), 
					getParam("txtTarikhMesyuarat"), userId, userRole);
			
			list = new Vector();
			list = logic.getSenaraiMesyuarat();
			this.context.put("SenaraiMesyurat", list);

			this.context.put("txtTajukMesyuarat", getParam("txtTajukMesyuarat"));
			this.context.put("txtBilMesyuarat", getParam("txtBilMesyuarat"));
			this.context.put("txtTarikhMesyuarat", getParam("txtTarikhMesyuarat"));
			setupPage(session, action, list);
			
		}

		// SET DEFAULT PARAM
		this.context.put("actionMesyuarat", actionMesyuarat);
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
	    this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idMesyuarat", idMesyuarat); 
	    this.context.put("idKehadiran", idKehadiran);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
	    this.context.put("flagPermohonanDari", flagPermohonanDari); 
	        
	    this.context.put("step",step);
	    
	    
	    if (!"".equals(getParam("flagFrom"))){
        	session.setAttribute("FLAG_FROM", getParam("flagFrom"));
        }
		
		if (session.getAttribute("MSG") != null){
			this.context.put("errMsg", session.getAttribute("MSG"));
			session.removeAttribute("MSG");
		} else {
			this.context.put("errMsg", "");
		}
				
		return vm;
	}
	
	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
			}

			if ("getNext".equals(action)) {
				page++;
			} else if ("getPrevious".equals(action)) {
				page--;
			} else if ("getPage".equals(action)) {
				page = getParamAsInteger("value");
			} else if ("doChangeItemPerPage".equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage");
			}

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiMesyuarat", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
	
	
}
