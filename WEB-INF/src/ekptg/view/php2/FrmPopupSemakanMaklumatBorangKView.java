package ekptg.view.php2;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmPopupSemakanMaklumatBorangKData;

public class FrmPopupSemakanMaklumatBorangKView extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	
	FrmPopupSemakanMaklumatBorangKData logic = new FrmPopupSemakanMaklumatBorangKData();
	
	String idNegeriUser = null;

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPopup = getParam("actionPopup");
		String submit = getParam("command");
		String idPPTBorangK = getParam("idPPTBorangK");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String idPHPBorangK = getParam("idPHPBorangK");
		
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
		
		// VECTOR
		Vector list = null;

		Vector beanMaklumatBorangK = null;
		
		String jenisHakmilik = getParam("socJenisHakmilik");
		if (jenisHakmilik == null || jenisHakmilik.trim().length() == 0) {
			jenisHakmilik = "99999";
		}
		String jenisLot = getParam("socJenisLot");
		if (jenisLot == null || jenisLot.trim().length() == 0) {
			jenisLot = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0) {
			idDaerah = "99999";
		}
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0) {
			idMukim = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		
		//FILTER BY NEGERI
		if (idNegeriUser != null && idNegeriUser.length() > 0){
			if (!"16".equals(idNegeriUser)){
				idNegeri = idNegeriUser;
			}
		}
		
		if ("papar".equals(actionPopup)) {

			// GO TO DETAIL MAKLUMAT TANAH
			vm = "app/php2/frmPopupSemakanMaklumatBorangK.jsp";

			beanMaklumatBorangK = new Vector();
			logic.setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan, idPHPBorangK);
			beanMaklumatBorangK = logic.getBeanMaklumatBorangK();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);

		} else {

			// GO TO LIST TANAH
			vm = "app/php2/frmPopupSemakanSenaraiBorangK.jsp";

			if ("doChangeNegeri".equals(submit)) {
				idDaerah = "99999";
				idMukim = "99999";
			}
			if ("doChangeDaerah".equals(submit)) {
				idMukim = "99999";
			}

			this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik(
					"socJenisHakmilik", Long.parseLong(jenisHakmilik), ""));
			this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",
					Long.parseLong(jenisLot), ""));			
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
					Long.parseLong(idNegeri), "",
					" onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(
					idNegeri, "socDaerah", Long.parseLong(idDaerah), "",
					" onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,
					"socMukim", Long.parseLong(idMukim), ""));
			this.context.put("selectKementerian", HTML.SelectKementerian(
					"socKementerian", Long.parseLong(idKementerian), "",
					" onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian(
					"socAgensi", idKementerian, Long.parseLong(idAgensi), ""));

			this.context.put("txtPeganganHakmilik",
					getParam("txtPeganganHakmilik"));
			this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("tarikhWarta", getParam("tarikhWarta"));

			list = new Vector();
			logic.carianBorangK(getParam("txtPeganganHakmilik"),
					jenisHakmilik, getParam("txtNoHakmilik"), jenisLot,
					getParam("txtNoLot"), getParam("txtNoWarta"),
					getParam("tarikhWarta"), idNegeri, idDaerah, idMukim,
					idKementerian, idAgensi);
			list = logic.getSenaraiBorangK();
			this.context.put("SenaraiBorangK", list);

			setupPage(session, action, list);
		}

		return vm;
	}
	
	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
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
			this.context.put("SenaraiBorangK", paging.getPage(page));
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
