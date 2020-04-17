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
import ekptg.model.php2.FrmTKRKutipanData;

public class FrmTKRKutipanDataView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmTKRKutipanData logic = new FrmTKRKutipanData();

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// DROP DOWN PENDAFTARAN
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionTukarguna = getParam("actionTukarguna");
		String submit = getParam("command");
		String hitButton = getParam("hitButton");

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idPPTBorangK = getParam("idPPTBorangK");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String idPHPBorangK = getParam("idPHPBorangK");

		// VECTOR
		Vector list = null;

		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatAgensi = null;
		Vector beanMaklumatPejabat = null;
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatBorangK = null;

		// GET DROPDOWN PARAM
		String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String idNegeriJKPTG = getParam("socNegeriJKPTG");
		if (idNegeriJKPTG == null || idNegeriJKPTG.trim().length() == 0){
			idNegeriJKPTG = "99999";
		}
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0){
			idPejabat = "99999";
		}
		String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}

		//DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		//HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idJenisTanah, getParam("noFail"), getParam("tarikhTerima"),
						getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
						idKategoriPemohon, idKementerian, idAgensi,	idPejabat,						
						idHakmilikAgensi, idPPTBorangK, idHakmilikUrusan, idPHPBorangK,
						idLuasKegunaan, getParam("txtTujuanKegunaan"), 
						getParam("idKementerianTanah"), getParam("idNegeriTanah"), getParam("idLuasTanah"), getParam("luasTanah"),
						session);
			}
		}

		this.context.put("errorPeganganHakmilik", "");
		
		vm = "app/php2/frmTKRKutipanData.jsp";
		
		if ("papar".equals(actionTukarguna)) {
			
			vm = "app/php2/frmTKRKutipanData.jsp";

			this.context.put("mode", "view");
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			if (beanMaklumatPermohonan.size() != 0){
    			Hashtable hashMaklumatPelepasan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
    			idLuasKegunaan = (String) hashMaklumatPelepasan.get("flagGuna");
			}
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\""));

			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);		
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idPejabat = (String) hashPemohon.get("idPejabat");
				idKementerian = (String) hashPemohon.get("idKementerian");
				idAgensi = (String) hashPemohon.get("idAgensi");
			}

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenawaran("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			
			if ("3".equals(idKategoriPemohon)) {
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
				
			} else if (("8".equals(idKategoriPemohon))) {
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
									
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));

				Hashtable hashNegeri = (Hashtable) logic.getBeanMaklumatPejabat().get(0);
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong((String) hashNegeri.get("idNegeri")), "disabled", " class=\"disabled\""));
				this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", (String) hashNegeri.get("idNegeri")));
			}
			
			String flagBorangK = "";
			logic.setMaklumatHakmilik(logic.getIdHakmilikPermohonanByIdFail(idFail));
			if (logic.getBeanMaklumatHakmilik().size() != 0){
				Hashtable hashHakmilik = (Hashtable) logic.getBeanMaklumatHakmilik().get(0);
				flagBorangK = (String) hashHakmilik.get("flagBorangK");
			}
			
			if ("Y".equals(flagBorangK)){
				beanMaklumatBorangK = new Vector();
				beanMaklumatBorangK = logic.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
				this.context.put("idJenisTanah", "3");
			} else {
				beanMaklumatTanah = new Vector();
				beanMaklumatTanah = logic.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				this.context.put("idJenisTanah", "1");
			}

		} else  {
			
			vm = "app/php2/frmTKRKutipanData.jsp";

			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			
			if ("doChangeKategori".equals(submit)){
				idKementerian = "99999";
				idAgensi = "99999";
				idPejabat = "99999";
				idHakmilikAgensi = "";
				idPPTBorangK = "";
				idHakmilikUrusan = "";
				idPHPBorangK = "";
			}
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
			hashPermohonan.put("noFail",getParam("noFail") == null  || "".equals(getParam("noFail"))? "JKPTG/SPHP/879/" : getParam("noFail"));
			hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
			hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
			hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
			hashPermohonan.put("perkara", getParam("txtPerkara") == null ? "": getParam("txtPerkara"));
			hashPermohonan.put("tujuanKegunaan", getParam("txtTujuanKegunaan") == null ? "": getParam("txtTujuanKegunaan"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

			// MAKLUMAT PEMOHON
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenawaran("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " "));
			
			if ("3".equals(idKategoriPemohon)) {
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
				
			} else if (("8".equals(idKategoriPemohon))) {
				
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeriJKPTG", Long.parseLong(idNegeriJKPTG), "", " onChange=\"doChangeNegeri();\""));
				this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "", " onChange=\"doChangePejabat();\"", idNegeriJKPTG));
				
				if ("doChangeNegeri".equals(submit)){
					
					this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "", " onChange=\"doChangePejabat();\"", idNegeriJKPTG));
					idPejabat = "99999";
				}
				
				if ("doChangePejabat".equals(submit)){
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);			
				}
				
				if (!"99999".equals(idPejabat)){
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
				} else {
					beanMaklumatPejabat = new Vector();
					Hashtable hashPejabat = new Hashtable();
					hashPejabat.put("namaPejabat", "");
					hashPejabat.put("alamat1", "");
					hashPejabat.put("alamat2", "");
					hashPejabat.put("alamat3", "");
					hashPejabat.put("poskod", "");
					hashPejabat.put("bandar", "");
					hashPejabat.put("negeri", "");
					hashPejabat.put("noTel", "");
					hashPejabat.put("noFax", "");
					beanMaklumatPejabat.addElement(hashPejabat);
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
				}
				this.context.put("idPejabat", idPejabat);
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
				
			}
			
			if ("1".equals(idJenisTanah)) {
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
        	}
			
			//MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"), idKategoriPemohon, idAgensi);
				if (idHakmilikAgensi.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
				}
			}
			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
			//MAKLUMAT BORANG K
			if ("doChangePeganganHakmilikBorangK".equals(submit)) {
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
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
			
		} 
		// SET DEFAULT PARAM
		this.context.put("actionTukarguna", actionTukarguna);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
		this.context.put("idAgensi", idAgensi);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		this.context.put("idPPTBorangK", idPPTBorangK);
		this.context.put("idHakmilikUrusan", idHakmilikUrusan);
		this.context.put("idPHPBorangK", idPHPBorangK);

		return vm;
	}

}
