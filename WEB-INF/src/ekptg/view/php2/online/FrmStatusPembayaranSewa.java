package ekptg.view.php2.online;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.Util;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmREVMemantauBayaranSewaData;
import ekptg.model.php2.online.FrmPYWOnlineSenaraiFailData;

public class FrmStatusPembayaranSewa extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmREVMemantauBayaranSewaData logic_sewa = new FrmREVMemantauBayaranSewaData();
	FrmPYWOnlineSenaraiFailData logic = new FrmPYWOnlineSenaraiFailData();
	
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Vector status_pembayaranSewa = new Vector();
		
		String action = getParam("action");
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		String vm = "";
		String command = getParam("command");
		String flag_penyewaan = getParam("flag_penyewaan");
		String idFail = getParam("idFail");
		String idHasil = getParam("idHasil");
		String findNamaPenyewa = getParam("findNamaPenyewa");
		String findTarikhResit = getParam("findTarikhResit");
		String mode = getParam("mode");
		
		String nModul = getParam("namamodul");
		this.context.put("nModul", nModul);
		
		String nTab = getParam("namatab");
		this.context.put("nTab", nTab);
		
        if (mode.isEmpty()){
        	mode = "view";
        }
		
		//default init
		context.put("ID_HASIL", "");
		context.put("SenaraiDeposit", "");
		context.put("totalDeposit", "");
		context.put("NO_FAIL", "");
		context.put("flag_penyewaan", flag_penyewaan);
		
		try {
		
		if ("cari".equals(command)) {
			
			status_pembayaranSewa = logic.statusPembayaranSewa(findNamaPenyewa, findTarikhResit, idFail, idHasil, id_user);
			this.context.put("status_pembayaranSewa", status_pembayaranSewa);
			setupPage(session, action, status_pembayaranSewa);
			
			context.put("findNamaPenyewa", findNamaPenyewa);	
			context.put("findTarikhResit", findTarikhResit);

			// screen
			vm = "app/php2/online/pembayaranSewa/frmStatusPembayaranSewa.jsp";

		}// close cari
		else if ("paparDetails".equals(command)){
			
			String ID_HASIL = getParam("ID_HASIL");
			String NO_FAIL = getParam("NO_FAIL");
			context.put("NO_FAIL", NO_FAIL);
			context.put("ID_HASIL", ID_HASIL);
			
			//MAKLUMAT DEPOSIT
			senaraiDeposit(ID_HASIL);
			
			//MAKLUMAT SEWA
			senaraiMaklumatSewa(ID_HASIL);
			
			//MAKLUMAT BAYARAN
			senaraiBayaranLL(ID_HASIL);
			
			//MAKLUMAT PERJANJIAN
			senaraiPerjanjian(ID_HASIL);
			
        	vm = "app/php2/online/pembayaranSewa/frmDetailsHasil.jsp";
		}
		else
		{
			status_pembayaranSewa = logic.statusPembayaranSewa(findNamaPenyewa, findTarikhResit, idFail, idHasil, id_user);
			this.context.put("status_pembayaranSewa", status_pembayaranSewa);
			setupPage(session, action, status_pembayaranSewa);
			
			context.remove("findNamaPenyewa");
			context.remove("findTarikhResit");
			
			vm = "app/php2/online/pembayaranSewa/frmStatusPembayaranSewa.jsp";
		}
		
		System.out.println("vm FrmStatusPembayaranSewa : "+vm);
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		// SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("idFail", idFail);
	    this.context.put("idHasil", idHasil);
	    
		return vm;
	}
	
	private void senaraiDeposit(String idHasil) throws Exception {
		Vector senaraiDeposit = new Vector();
		senaraiDeposit = logic.setListDeposit(idHasil);
		this.context.put("senaraiDeposit", senaraiDeposit);
		
		//CALCULATE TOTAL
		Double total = 0D;
		total = logic_sewa.calculateTotalDeposit(idHasil);
		
		if (total > 0D){
			this.context.put("totalDeposit", Util.formatDecimal(total));
		} else if (total < 0D){
			this.context.put("totalDeposit", "(" + Util.formatDecimal(total * -1) + ")");
		} else {
			this.context.put("totalDeposit", "0.00");
		}		
	}
	
	private void senaraiMaklumatSewa(String idHasil) throws Exception {
		Vector senaraiMaklumatSewa = new Vector();
		senaraiMaklumatSewa = logic.setListMaklumatSewa(idHasil);
		context.put("senaraiMaklumatSewa", senaraiMaklumatSewa);
		
		//CALCULATE TOTAL
		Double totalMaklumatSewa = 0D;
		totalMaklumatSewa = logic_sewa.calculateTotal(idHasil);
		
		if (totalMaklumatSewa > 0D){
			this.context.put("totalMaklumatSewa", Util.formatDecimal(totalMaklumatSewa));
			this.context.put("flag_tunggakan", "Y");
		} else if (totalMaklumatSewa < 0D){
			this.context.put("totalMaklumatSewa", "(" + Util.formatDecimal(totalMaklumatSewa * -1) + ")");
			this.context.put("flag_tunggakan", "T");
		} else {
			this.context.put("totalMaklumatSewa", "0.00");
			this.context.put("flag_tunggakan", "T");
		}	
	}
	
	private void senaraiBayaranLL(String idHasil) throws Exception {
		Vector senaraiBayaranLL = new Vector();
		senaraiBayaranLL = logic.setListBayaranLL(idHasil);
		context.put("senaraiBayaranLL", senaraiBayaranLL);
		
		//CALCULATE TOTAL
		Double totalBayaranLL = 0D;
		totalBayaranLL = logic_sewa.calculateTotalLL(idHasil);
		
		if (totalBayaranLL > 0D){
			this.context.put("totalBayaranLL", Util.formatDecimal(totalBayaranLL));
			this.context.put("flag_tunggakan", "Y");
		} else if (totalBayaranLL < 0D){
			this.context.put("totalBayaranLL", "(" + Util.formatDecimal(totalBayaranLL * -1) + ")");
			this.context.put("flag_tunggakan", "T");
		} else {
			this.context.put("totalBayaranLL", "0.00");
			this.context.put("flag_tunggakan", "T");
		}		
	}
	
	private void senaraiPerjanjian(String idHasil) throws Exception {
		Vector senaraiPerjanjian = new Vector();
		senaraiPerjanjian = logic.setListPerjanjian(idHasil);
		this.context.put("senaraiPerjanjian", senaraiPerjanjian);		
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
