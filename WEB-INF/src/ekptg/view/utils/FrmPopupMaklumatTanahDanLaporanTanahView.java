package ekptg.view.utils;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.utils.FrmMaklumatTanahDanLaporanTanahData;
import ekptg.model.views.FrmEkptgViewsModel;

public class FrmPopupMaklumatTanahDanLaporanTanahView extends AjaxBasedModule {

	FrmMaklumatTanahDanLaporanTanahData logic = new FrmMaklumatTanahDanLaporanTanahData();
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Vector beanMaklumatTanahDanLaporanTanah = new Vector();
		FrmEkptgViewsModel model = new FrmEkptgViewsModel(); 
		boolean paramExist = false;
		String vm = "app/utils/frmPopupMaklumatTanahDanLaporanTanah.jsp";
		
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String noLot = getParam("noLot");
		String txtCarian = getParam("txtCarian");
		
		if(noLot!=null && noLot.length()>0){
			model.setNoLot(noLot);
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

		if(paramExist)
			beanMaklumatTanahDanLaporanTanah = logic.setSenaraiMaklumatTanahDanLaporanTanahByParam(model);
		else
			beanMaklumatTanahDanLaporanTanah = logic.setSenaraiMaklumatTanahDanLaporanTanah();
		
		this.context.put("BeanMaklumatTanahDanLaporanTanah", beanMaklumatTanahDanLaporanTanah);
		setupPage(session, action, beanMaklumatTanahDanLaporanTanah);
		
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
