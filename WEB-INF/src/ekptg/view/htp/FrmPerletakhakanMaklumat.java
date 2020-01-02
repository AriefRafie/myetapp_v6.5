package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmPerletakhakanMaklumatData;

public class FrmPerletakhakanMaklumat extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	private static Logger myLogger = Logger.getLogger(FrmPerletakhakanMaklumat.class);

	FrmPerletakhakanMaklumatData logic = new FrmPerletakhakanMaklumatData();

	public String doTemplate2() throws Exception {
		
		// TODO Auto-generated method stub
		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		String selectedTabUpper = getParam("selectedTabUpper").toString();
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}

		String submit = getParam("command"); // untuk doAjaxCall
		String action = getParam("action");
		String actionPerletakhakan = getParam("actionPerletakhakan");
		String mode = getParam("mode");
		if ("".equals(mode)){
			mode = "view";
		}
		String idFail = getParam("idFail");		
		String idPermohonan = getParam("idPermohonan");		
		String idLetakhak = getParam("idLetakhak");
		String hitButton = getParam("hitButton");
		String idBayaran = getParam("idBayaran");		
		String idBorang30A = getParam("idBorang30A");		

		Vector list = null;

		String vm = "";
		
		String idCaraBayar = getParam("socCaraBayar");
		if (idCaraBayar == null || idCaraBayar.trim().length() == 0) {
			idCaraBayar = "99999";
		}
		
		if (postDB) {
			
			if ("simpanAkta".equals(hitButton)){
				logic.simpanAkta(idPermohonan, idLetakhak, getParam("txtNoAkta"), getParam("txdTarikhKuatkuasa"), getParam("txtTajukAkta"), session);
			}
			
			if ("cariFail".equals(hitButton)){
				String noFail = getParam("txtNoFail");
				System.out.println("carian======================" +noFail);				
				cariFail(noFail);
				vm = "app/htp/frmPerletakhakanSenaraiMaklumat.jsp";
				this.context.put("SenaraiFail", list);
				//logic.simpanAkta(idPermohonan, idLetakhak, getParam("txtNoAkta"), getParam("txdTarikhKuatkuasa"), getParam("txtTajukAkta"), session);
			}
			
			if ("simpanPerintah".equals(hitButton)){
				logic.simpanPerintah(idPermohonan, idLetakhak, getParam("txtNoSamanPemula"), getParam("txtNamaMahkamah"), getParam("txdTarikhPerintah"), session);
			}
			
			if ("simpanBayaran".equals(hitButton)){
				logic.simpanBayaran(idPermohonan, idBayaran, idCaraBayar, getParam("txtNoCek"), Utils.RemoveSymbol(getParam("txtJumlahBayar")), getParam("txtNoResit"),
						Utils.RemoveSymbol(getParam("txtAmaunResit")), getParam("txdTarikhResit"), getParam("txdHantarResit"), getParam("txdTarikhTerima"), session);
				logic.simpanBorang30A(idPermohonan, idBorang30A, getParam("txdTarikhTerimaBorang30A"), getParam("txdTarikhTandatanganPTP"),
						getParam("txdTarikhHantar"), getParam("txdTarikhDaftar30A"), session);
				
			}
		}
		

		if ("papar".equals(actionPerletakhakan)) {

			vm = "app/htp/frmPerletakhakanMaklumat.jsp";

			// HEADER MASTER
			logic.setMaklumatFailMaster(idFail);
			list = logic.getMaklumatFailMaster();
			this.context.put("List", list);		
			if (list.size() != 0) {
				Hashtable hashHeader = (Hashtable) logic.getMaklumatFailMaster().get(0);
				idPermohonan = (String) hashHeader.get("idPermohonan");
			}
			
			idLetakhak = logic.getIdLetakHakByIdPermohonan(idPermohonan);
			idBayaran = logic.getIdBayaranByIdPermohonan(idPermohonan);
			idBorang30A = logic.getIdBorang30AByIdPermohonan(idPermohonan);
			
			if ("view".equals(mode)){
				
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				
				//MAKLUMAT LETAKHAK
				if ("".equals(idLetakhak)){
					
					// akta@warta terlibat
					this.context.put("txtNoAkta", "");
					this.context.put("txdTarikhKuatkuasa", "");
					this.context.put("txtTajukAkta", "");

					// perintah mahkamah
					this.context.put("txtNoSamanPemula", "");
					this.context.put("txtNamaMahkamah", "");
					this.context.put("txdTarikhPerintah", "");
					
				} else {
					logic.setMaklumatLetakHak(idLetakhak);
					if (logic.getMaklumatLetakHak().size() != 0){
						Hashtable hashMaklumatLetakHak = (Hashtable) logic.getMaklumatLetakHak().get(0);
						
						// akta@warta terlibat
						this.context.put("txtNoAkta", hashMaklumatLetakHak.get("NO_AKTA"));
						this.context.put("txdTarikhKuatkuasa", hashMaklumatLetakHak.get("TARIKH_KUATKUASA"));
						this.context.put("txtTajukAkta", hashMaklumatLetakHak.get("TAJUK_AKTA"));

						// perintah mahkamah
						this.context.put("txtNoSamanPemula", hashMaklumatLetakHak.get("NO_SAMANMULA"));
						this.context.put("txtNamaMahkamah", hashMaklumatLetakHak.get("NAMA_MAHKAMAH"));
						this.context.put("txdTarikhPerintah", hashMaklumatLetakHak.get("TARIKH_PERINTAH"));
					}
				}
				
				//MAKLUMAT BAYARAN
				if ("".equals(idBayaran)){
					
					// untuk Bayaran
					this.context.put("txtNoCek", "");
					this.context.put("txtNoResit", "");
					this.context.put("txdTarikhResit", "");
					this.context.put("txdHantarResit", "");
					this.context.put("txtJumlahBayar", "");
					this.context.put("txtAmaunResit", "");
					this.context.put("txdTarikhTerima", "");
					
					this.context.put("selectCaraBayar",HTML.selectCaraBayar("socCaraBayar", Long.parseLong(idCaraBayar), "disabled", " class=\"disabled\""));
					
				} else {
					logic.setMaklumatBayaran(idBayaran);
					if (logic.getMaklumatBayaran().size() != 0){
						Hashtable hashMaklumatBayaran = (Hashtable) logic.getMaklumatBayaran().get(0);
						
						this.context.put("txtNoCek", hashMaklumatBayaran.get("NO_BAYARAN"));
						this.context.put("txtNoResit", hashMaklumatBayaran.get("NO_RESIT"));
						this.context.put("txdTarikhResit", hashMaklumatBayaran.get("TARIKH_RESIT"));
						this.context.put("txdHantarResit", hashMaklumatBayaran.get("TARIKH_HANTAR_RESIT"));
						this.context.put("txtJumlahBayar", hashMaklumatBayaran.get("JUMLAH_BAYARAN"));
						this.context.put("txtAmaunResit", hashMaklumatBayaran.get("AMAUN_ATAS_RESIT"));
						this.context.put("txdTarikhTerima", hashMaklumatBayaran.get("TARIKH_TERIMA"));
						
						this.context.put("selectCaraBayar",HTML.selectCaraBayar("socCaraBayar", Long.parseLong((String) hashMaklumatBayaran.get("ID_CARABAYAR")), "disabled", " class=\"disabled\""));
					}
				}
				
				//MAKLUMAT BORANG 30A
				if ("".equals(idBorang30A)){
					
					// untuk borang 30a
					this.context.put("txdTarikhTerimaBorang30A", "");
					this.context.put("txdTarikhTandatanganPTP", "");
					this.context.put("txdTarikhHantar", "");
					this.context.put("txdTarikhDaftar30A", "");
					
				} else {
					logic.setMaklumatBorang30A(idBorang30A);
					if (logic.getMaklumatBorang30A().size() != 0){
						Hashtable hashMaklumatBorang30A = (Hashtable) logic.getMaklumatBorang30A().get(0);
						
						this.context.put("txdTarikhTerimaBorang30A", hashMaklumatBorang30A.get("TARIKH_TERIMA"));
						this.context.put("txdTarikhTandatanganPTP", hashMaklumatBorang30A.get("TARIKH_TANDATANGAN_PTP"));
						this.context.put("txdTarikhHantar", hashMaklumatBorang30A.get("TARIKH_HANTAR"));
						this.context.put("txdTarikhDaftar30A", hashMaklumatBorang30A.get("TARIKH_DAFTAR"));
					}
				}
				
			} else if ("update".equals(mode)){
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				// akta@warta terlibat
				this.context.put("txtNoAkta", getParam("txtNoAkta"));
				this.context.put("txdTarikhKuatkuasa", getParam("txdTarikhKuatkuasa"));
				this.context.put("txtTajukAkta", getParam("txtTajukAkta"));

				// perintah mahkamah
				this.context.put("txtNoSamanPemula", getParam("txtNoSamanPemula"));
				this.context.put("txtNamaMahkamah", getParam("txtNamaMahkamah"));
				this.context.put("txdTarikhPerintah", getParam("txdTarikhPerintah"));
				
				// untuk Bayaran
				this.context.put("txtNoCek", getParam("txtNoCek"));
				this.context.put("txtNoResit", getParam("txtNoResit"));
				this.context.put("txdTarikhResit", getParam("txdTarikhResit"));
				this.context.put("txdHantarResit", getParam("txdHantarResit"));
				this.context.put("txtJumlahBayar", getParam("txtJumlahBayar"));
				this.context.put("txtAmaunResit", getParam("txtAmaunResit"));
				this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
				
				this.context.put("selectCaraBayar",HTML.selectCaraBayar("socCaraBayar", Long.parseLong(idCaraBayar), "", ""));
				
				// untuk borang 30a
				this.context.put("txdTarikhTerimaBorang30A", getParam("txdTarikhTerimaBorang30A"));
				this.context.put("txdTarikhTandatanganPTP", getParam("txdTarikhTandatanganPTP"));
				this.context.put("txdTarikhHantar", getParam("txdTarikhHantar"));
				this.context.put("txdTarikhDaftar30A", getParam("txdTarikhDaftar30A"));
			}

		} else {
			 //set senarai
			logic.setMaklumatSenarai(getParam("txtNoFail"));
			list = logic.getMaklumatSenarai();
			vm = "app/htp/frmPerletakhakanSenaraiMaklumat.jsp";
			this.context.put("SenaraiFail", list);

			setupPage(session, action, list);
		}

		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("mode", mode);
		this.context.put("actionPerletakhakan", actionPerletakhakan);
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idLetakhak", idLetakhak);
		this.context.put("idBayaran", idBayaran);
		this.context.put("idBorang30A", idBorang30A);
		
		return vm;
	}
	public void cariFail(String noFail) throws Exception {
		System.out.println("++++++++++++++++++carian");
		Vector list = logic.setMaklumatSenaraiCarian(noFail);
		this.context.put("SenaraiFail", list);
			
	}
	// SETUP PAGE
	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10 : getParamAsInteger("itemsPerPage");
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
