/**
 * 
 */
package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmPNWSenaraiFailData;

public class FrmPNWSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmPNWSenaraiFailData logic = new FrmPNWSenaraiFailData();

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPenawaran = getParam("actionPenawaran");
		String submit = getParam("command");
		String hitButton = getParam("hitButton");

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idHakmilikSementara = getParam("idHakmilikSementara");
		String idPPTBorangK = getParam("idPPTBorangK");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String idPHPBorangK = getParam("idPHPBorangK");
		
		String idKategoriPemohon = "";
		String idJenisTanah = "1";
		String namaJenisTanah = "TANAH MILIK PERSEKUTUAN";
		String idPejabat = "";

		// VECTOR
		Vector list = null;

		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatAgensi = null;
		Vector beanMaklumatPejabat = null;
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatBorangK = null;

		// GET DROPDOWN PARAM
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}		

		//DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		//HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idJenisTanah, getParam("tarikhTerima"),
						getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
						"3", idKementerian, idAgensi,						
						idHakmilikAgensi, idPPTBorangK, idHakmilikUrusan, idPHPBorangK, 
						getParam("idKementerianTanah"), getParam("idNegeriTanah"), getParam("idLuasTanah"), getParam("luasTanah"),
						idHakmilikSementara, session);
			}
		}

		this.context.put("errorPeganganHakmilik", "");

		if ("papar".equals(actionPenawaran)) {

			// GO TO VIEW PENAWARAN
			vm = "app/php2/frmPNWDaftarManual.jsp";

			this.context.put("mode", "view");
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);		
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idPejabat = (String) hashPemohon.get("idPejabat");
				idKementerian = (String) hashPemohon.get("idKementerian");
				idAgensi = (String) hashPemohon.get("idAgensi");
			}

			idKategoriPemohon = logic.getKategoriPemohonPenawaran();
							
			//MAKLUMAT KEMENTERIAN/ AGENSI
			beanMaklumatAgensi = new Vector();
			logic.setMaklumatAgensi(idAgensi);
			beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
			this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
			
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
							
			
			// MAKLUMAT PERMOHONAN
			beanMaklumatTanah = new Vector();
			logic.setMaklumatHakmilik(logic.getIdHakmilikPermohonanByIdFail(idFail));
			beanMaklumatTanah = logic.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

		} else if ("daftarBaru".equals(actionPenawaran)) {

			// GO TO DAFTAR BARU PENAWARAN
			vm = "app/php2/frmPNWDaftarManual.jsp";

			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			
			/*if ("doChangeKategori".equals(submit)){
				idKementerian = "99999";
				idAgensi = "99999";
				idHakmilikAgensi = "";
				idPPTBorangK = "";
				idHakmilikUrusan = "";
				idPHPBorangK = "";
			}*/
			if ("doChangeKementerian".equals(submit)){
				idAgensi = "99999";
				idHakmilikAgensi = "";
				idPPTBorangK = "";
				idHakmilikUrusan = "";
				idPHPBorangK = "";
			}
			if ("doChangeAgensi".equals(submit)){
				idHakmilikAgensi = "";
				idPPTBorangK = "";
				idHakmilikUrusan = "";
				idPHPBorangK = "";
			}
			if ("doChangeJenisTanah".equals(submit)){
				idHakmilikAgensi = "";
				idPPTBorangK = "";
				idHakmilikUrusan = "";
				idPHPBorangK = "";
			}

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
			hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
			hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
			hashPermohonan.put("perkara", getParam("txtPerkara") == null ? "": getParam("txtPerkara"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

			// MAKLUMAT PEMOHON
			idKategoriPemohon = logic.getKategoriPemohonPenawaran();
							
			beanMaklumatAgensi = new Vector();
			logic.setMaklumatAgensi(idAgensi);
			beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
			this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
			
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
				
			// MAKLUMAT TANAH
			/*if ("1".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "selected");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("idJenisTanah", idJenisTanah);
        	} else if ("2".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("selected3", "");
				this.context.put("idJenisTanah", idJenisTanah);
        	} else if ("3".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "selected");
				this.context.put("idJenisTanah", idJenisTanah);
        	} else {
        		this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("idJenisTanah", "0");
        	}*/
						
			//MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"), "3", idAgensi);
				if (idHakmilikAgensi.isEmpty()) {
					idHakmilikAgensi = logic.getIdHakmilikSementaraByPeganganHakmilik(getParam("txtPeganganHakmilik"), "3", idAgensi);
					if (idHakmilikAgensi.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
					}
				}
			}
			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);	
			
			//MAKLUMAT BORANG K
			/*if ("doChangePeganganHakmilikBorangK".equals(submit)) {
				idPHPBorangK = logic.getIdPHPBorangKByPeganganHakmilik(getParam("txtPeganganHakmilik"), idKategoriPemohon, idAgensi);
				if (idPHPBorangK.isEmpty()) {
					idHakmilikUrusan = logic.getIdHakmilikUrusanByPeganganHakmilik(getParam("txtPeganganHakmilik"), idKategoriPemohon, idAgensi);
					if (idHakmilikUrusan.isEmpty()) {
						this.context.put("errorPeganganHakmilik","Maklumat Borang K tidak wujud.");
					}					
				}
			}
			
			beanMaklumatBorangK = new Vector();
			logic.setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan, idPHPBorangK);
			beanMaklumatBorangK = logic.getBeanMaklumatBorangK();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);*/
			
		} else {
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
			String idKementerianC = getParam("socKementerianC");
			if (idKementerianC == null || idKementerianC.trim().length() == 0) {
				idKementerianC = "99999";
			}
			String idAgensiC = getParam("socAgensiC");
			if (idAgensiC == null || idAgensiC.trim().length() == 0) {
				idAgensiC = "99999";
			}

			String idStatusC = getParam("socStatusC");
			if (idStatusC == null || idStatusC.trim().length() == 0) {
				idStatusC = "99999";
			}
			String flagDetail = getParam("flagDetail");

			// GO TO LIST FAIL PENAWARAN
			vm = "app/php2/frmPNWSenaraiFail.jsp";

			logic.carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txtPemohon"),
					getParam("txdTarikhTerima"), idNegeriC, idDaerahC,
					idMukimC, idJenisHakmilikC, getParam("txtNoHakmilik"),
					getParam("txtNoWarta"), idLotC, getParam("txtNoLot"),
					getParam("txtNoPegangan"), idStatusC, idKementerianC, idAgensiC, getParam("checkTanah"));
			
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);

			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
			this.context.put("txtPemohon", getParam("txtPemohon"));
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
			this.context.put("selectStatus", HTML.SelectStatusPenawaran("socStatusC", Long.parseLong(idStatusC), "", ""));
			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerianC", Long.parseLong(idKementerianC), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensiC", idKementerianC,  Long.parseLong(idAgensiC), "", ""));
			this.context.put("checkTanah", getParam("checkTanah"));
			this.context.put("flagTanah", getParam("checkTanah"));
			this.context.put("flagDetail", flagDetail);
			setupPage(session, action, list);
		}

		// SET DEFAULT PARAM
		this.context.put("actionPenawaran", actionPenawaran);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
		this.context.put("idAgensi", idAgensi);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		this.context.put("idPPTBorangK", idPPTBorangK);
		this.context.put("idHakmilikUrusan", idHakmilikUrusan);
		this.context.put("idPHPBorangK", idPHPBorangK);
		this.context.put("idHakmilikSementara", idHakmilikSementara);
		this.context.put("namaJenisTanah", namaJenisTanah);
		this.context.put("idJenisTanah", idJenisTanah);

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
