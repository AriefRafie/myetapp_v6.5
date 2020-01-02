package ekptg.view.utils;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.utils.FrmMaklumatTanahDanLaporanTanahData;
import ekptg.model.views.FrmEkptgViewsModel;

public class FrmMaklumatTanahDanLaporanTanahView extends AjaxBasedModule {

	FrmMaklumatTanahDanLaporanTanahData logic = new FrmMaklumatTanahDanLaporanTanahData();
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Vector beanMaklumatTanahDanLaporanTanah = null;
		FrmEkptgViewsModel model = new FrmEkptgViewsModel(); 
		boolean paramExist = false;
		String vm = "app/utils/frmSenaraiMaklumatTanahDanLaporanTanah.jsp";
		
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String noLot = getParam("noLot");
		String txtCarian = getParam("txtCarian");
		String txtNoHakmilikC = getParam("txtNoHakmilikC");
		Vector senarai = null;
		
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
		String idKementerianC = getParam("socKementerianC");
		if (idKementerianC == null || idKementerianC.trim().length() == 0) {
			idKementerianC = "99999";
		}
		
		String kodLot = getParam("kodLot");

		if(noLot!=null && noLot.length()>0){
			model.setNoLot(noLot);
			paramExist = true;
		}
		
		/*if(txtCarian!=null && txtCarian.length()>0){
//			model.setKodLot(txtCarian);
//			model.setNoLot(txtCarian);
			model.setKodNoLot(txtCarian);
			model.setNoFail(txtCarian);
			model.setTajukFail(txtCarian);
			model.setStatus(txtCarian);
			model.setNamaUrusan(txtCarian);
			model.setKeputusan(txtCarian);
			paramExist = true;
		}
		
		
		if(txtCarian!=null && txtCarian.length()>0){
//			model.setKodLot(txtCarian);
//			model.setNoLot(txtCarian);
			model.setKodNoLot(txtCarian);
			model.setNoFail(txtCarian);
			model.setTajukFail(txtCarian);
			model.setStatus(txtCarian);
			model.setNamaUrusan(txtCarian);
			model.setKeputusan(txtCarian);
			paramExist = true;
		}

		
		
		/*if(paramExist)
			beanMaklumatTanahDanLaporanTanah = logic.setSenaraiMaklumatTanahDanLaporanTanahByParam(model);
		else
			beanMaklumatTanahDanLaporanTanah = logic.setSenaraiMaklumatTanahDanLaporanTanah();
		
		*/
//		if(paramExist)
//			logic.carian(txtCarian, idNegeriC, idDaerahC, txtNoHakmilikC, kodLot, idKementerianC , idMukimC);
//		else
//		    logic.carian1();
		
	
		senarai = new Vector();
		logic.carian(txtCarian, idNegeriC, idDaerahC, txtNoHakmilikC, kodLot, idKementerianC , idMukimC);
		senarai = logic.getSenarai();
		this.context.put("SenaraiFail", senarai);
//		this.context.put("SenaraiFail", beanMaklumatTanahDanLaporanTanah);
		
//		this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",Long.parseLong(jenisLot), ""));
//		this.context.put("selectJenisLot", HTML.SelectLotTanah("socJenisLot",Long.parseLong(jenisLot), ""));
		this.context.put("BeanMaklumatTanahDanLaporanTanah", beanMaklumatTanahDanLaporanTanah);
		this.context.put("SenaraiFail", senarai);
		this.context.put("kodLot", getParam("kodLot"));
		this.context.put("txtNoHakmilikC", getParam("txtNoHakmilikC"));
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeriC",Long.parseLong(idNegeriC), ""," onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), ""," onChange=\"doChangeDaerah();\""));
		this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));
		this.context.put("selectKementerian", HTML.SelectKementerian("socKementerianC", Long.parseLong(idKementerianC), "", " onChange=\"doChangeKementerian();\""));
		

		
		setupPage(session, action, senarai);
		
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
			this.context.put("BeanMaklumatTanahDanLaporanTanah", paging.getPage(page));
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
