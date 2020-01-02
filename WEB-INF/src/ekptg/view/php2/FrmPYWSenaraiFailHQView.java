/**
 * 
 */
package ekptg.view.php2;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmPYWSenaraiFailHQData;

public class FrmPYWSenaraiFailHQView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmPYWSenaraiFailHQData logic = new FrmPYWSenaraiFailHQData();
	
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String)session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String submit = getParam("command");

		// GET ID PARAM
		String idFail = getParam("idFail");
		
		// VECTOR
		Vector list = null;
		
		// DROP DOWN CARIAN
		String idJenisHakmilikC = getParam("socJenisHakmilikC");
		if (idJenisHakmilikC == null || idJenisHakmilikC.trim().length() == 0) {
			idJenisHakmilikC = "99999";
		}
		String idLotC = getParam("socLotC");
		if (idLotC == null || idLotC.trim().length() == 0) {
			idLotC = "99999";
		}
		String idNegeriC = getParam("socNegeriC");
		if (idNegeriC == null || idNegeriC.trim().length() == 0) {
			idNegeriC = "99999";
		}
		String idDaerahC = getParam("socDaerahC");
		if (idDaerahC == null || idDaerahC.trim().length() == 0) {
			idDaerahC = "99999";
		}
		String idMukimC = getParam("socMukimC");
		if (idMukimC == null || idMukimC.trim().length() == 0) {
			idMukimC = "99999";
		}

		String idStatusC = getParam("socStatusC");
		if (idStatusC == null || idStatusC.trim().length() == 0) {
			idStatusC = "99999";
		}
		String flagDetail = getParam("flagDetail");
		
		// GO TO LIST FAIL PENYEWAAN
		vm = "app/php2/frmPYWSenaraiFailHQ.jsp";

		logic.carianFail(getParam("txtNoFail"), getParam("txtPemohon"), getParam("txtNoPengenalan"),
				getParam("txdTarikhTerima"), idNegeriC, idDaerahC,
				idMukimC, idJenisHakmilikC, getParam("txtNoHakmilik"),
				getParam("txtNoWarta"), idLotC, getParam("txtNoLot"),
				getParam("txtNoPegangan"), idStatusC);
		
		list = new Vector();
		list = logic.getSenaraiFail();
		this.context.put("SenaraiFail", list);

		this.context.put("txtNoFail", getParam("txtNoFail"));
		this.context.put("txtPemohon", getParam("txtPemohon"));
		this.context.put("txtNoPengenalan", getParam("txtNoPengenalan"));
		this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		
		this.context.put("txtNoPegangan", getParam("txtNoPegangan"));
		this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilikC", Long.parseLong(idJenisHakmilikC), ""));
		this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		this.context.put("txtNoWarta", getParam("txtNoWarta"));
		this.context.put("selectLot", HTML.SelectLot("socLotC",Long.parseLong(idLotC), ""));
		this.context.put("txtNoLot", getParam("txtNoLot"));
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeriC",Long.parseLong(idNegeriC), ""," onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), ""," onChange=\"doChangeDaerah();\""));
		this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));
		this.context.put("selectStatus", HTML.SelectStatusPenyewaan("socStatusC", Long.parseLong(idStatusC), "", ""));

		this.context.put("flagDetail", flagDetail);
		setupPage(session, action, list);


		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		
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
			this.context.put("SenaraiFail", paging.getPage(page));
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
