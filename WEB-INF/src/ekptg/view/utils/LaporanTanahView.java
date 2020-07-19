package ekptg.view.utils;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.rekod.ITanahCarian;
import ekptg.model.utils.FrmMaklumatTanahDanLaporanTanahData;
import ekptg.model.utils.LaporanTanahCarian;
import ekptg.model.views.FrmEkptgViewsModel;

public class LaporanTanahView extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8623692013606869201L;
	static Logger myLog = Logger.getLogger(ekptg.view.utils.LaporanTanahView.class);

	FrmMaklumatTanahDanLaporanTanahData logic = new FrmMaklumatTanahDanLaporanTanahData();
	private ITanahCarian iCarian = null;

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Vector beanMaklumatTanahDanLaporanTanah = null;
		FrmEkptgViewsModel model = new FrmEkptgViewsModel(); 
		boolean paramExist = false;
		String vm = "app/utils/laporantanah/";
		
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String step = getParam("step");
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
			
		if (step.equals("daftar")) {			
			vm += "daftarLaporanTanah.jsp";
			
			//MAKLUMAT HAKMILIK
//			if ("doChangePeganganHakmilik".equals(submit)) {
//				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"));
//				if (idHakmilikAgensi.isEmpty()) {
//					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
//				}
//			}			
//			
//			beanMaklumatTanah = new Vector();
//			beanMaklumatTanah = logic.getMaklumatTanah(idHakmilikAgensi);
//			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
//			
//			this.context.put("txdTarikhLawatan", getParam("txdTarikhLawatan"));
//			this.context.put("txtTujuanLaporan", getParam("txtTujuanLaporan"));
//			this.context.put("txtCatatan", getParam("txtCatatan"));
//			this.context.put("txtPelapor", getParam("txtPelapor"));
//			this.context.put("txtJawatan", getParam("txtJawatan"));
//			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "",""));
			
		}else {
			vm = vm+"senaraiLaporanTanah.jsp";
		}
		
		senarai = new Vector();
//		String idJenisTanah
//		,String idNegeri, String idDaerah,String idMukim
//		,String noFail
//		,String idJenisHakmilik,String noHakmilikWarta
//		,String idLot,String noLot
//		,String idAgensi, String idKementerian
//		,String idStatus)throws Exception {
		senarai = getTanah().getCarianSenaraiHakmilikRizab("1"
				, idNegeriC, idDaerahC, idMukimC
				, null
				, null, txtNoHakmilikC
				, kodLot, noLot, null, null
				, null);
		//logic.carian(txtCarian, idNegeriC, idDaerahC, txtNoHakmilikC, kodLot, idKementerianC , idMukimC);
		//senarai = logic.getSenarai();
		setupPage(session, action, senarai);
//		this.context.put("SenaraiFail", senarai);
		
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
		
		
		return vm;
		
	}

	private ITanahCarian getTanah(){
		if(iCarian== null)
			iCarian = new LaporanTanahCarian();
		return iCarian;
	}
	

}
