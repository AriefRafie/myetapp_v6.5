/**
 * 
 */
package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmCRBKutipanData;

public class FrmCRBKutipanDataView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmCRBKutipanData logic = new FrmCRBKutipanData ();

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
		String actionPengkuatkuasaan = getParam("actionPengkuatkuasaan");
		String submit = getParam("command");
		String hitButton = getParam("hitButton");

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idHakmilikSementara = getParam("idHakmilikSementara");
		String idPHPBorangK = getParam("idPHPBorangK");
		String idPPTBorangK = getParam("idPPTBorangK");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");

		// VECTOR
		Vector list = null;
		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatPemohon = null;
		Vector beanMaklumatPejabat = null;
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatBorangK = null;
		Vector beanMaklumatAgensi = null;
		
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
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0) {
			idBandar = "99999";
		}
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0) {
			idPejabat = "99999";
		}
		String idSeksyenJKPTG = getParam("socSeksyenJKPTG");
		if (idSeksyenJKPTG == null || idSeksyenJKPTG.trim().length() == 0){
			idSeksyenJKPTG = "99999";
		}
		String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}

		//DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idJenisTanah, getParam("noFail"), getParam("tarikhTerima"),
						getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
						idKategoriPemohon, getParam("txtNama"),
						getParam("txtNoPendaftaran"),getParam("txtAlamat1"),
						getParam("txtAlamat2"), getParam("txtAlamat3"),
						getParam("txtPoskod"), idBandar, idNegeri,
						getParam("txtEmel"), getParam("txtNoTel"), getParam("txtNoFaks"),							
						idKementerian, idAgensi, idPejabat,
						idSeksyenJKPTG, getParam("txtNamaPengadu"), getParam("idKementerianTanah"), getParam("idNegeriTanah"), 
						idHakmilikAgensi, idPPTBorangK, idHakmilikUrusan, idPHPBorangK, idHakmilikSementara, session);
			}
		}

		this.context.put("errorPeganganHakmilik", "");

		if ("papar".equals(actionPengkuatkuasaan)) {

			// GO TO VIEW KUTIPAN DATA
			vm = "app/php2/frmCRBKutipanData.jsp";

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
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idPejabat = (String) hashPemohon.get("idPejabat");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
			}

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenguatkuasa("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			if ("3".equals(idKategoriPemohon)) {
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
				
			}else
			if ("1".equals(idKategoriPemohon) || "2".equals(idKategoriPemohon) || "6".equals(idKategoriPemohon) || "7".equals(idKategoriPemohon) || "9".equals(idKategoriPemohon)) {
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
				
			} else if (("4".equals(idKategoriPemohon)) || ("5".equals(idKategoriPemohon)) || ("8".equals(idKategoriPemohon))) {
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				if ("8".equals(idKategoriPemohon)) {
					
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					Hashtable hashNegeri = (Hashtable) logic.getBeanMaklumatPejabat().get(0);
					this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyenJKPTG", Long.parseLong(idSeksyenJKPTG), "disabled", " class=\"disabled\""));
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					this.context.put("selectPejabat",HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", (String) hashNegeri.get("idNegeri")));
					
				} else if ("4".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "1"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					
				} else if ("5".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled", " class=\"disabled\"" ,idNegeri, "2"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
				}
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

		} else {

			//GO TO KUTIPAN PENGUATKUASAAN       	
        	vm = "app/php2/frmCRBKutipanData.jsp";
        	
			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("jenisPemohon", "");
        	this.context.put("jenisPemohonDetail","");
			
			if ("doChangeKategori".equals(submit)){
				idNegeri = "99999";
				idPejabat = "99999";
				idSeksyenJKPTG ="99999";
			}
			if ("doChangeKementerian".equals(submit)){
				idAgensi = "99999";				
			}
			if ("doChangeNegeri".equals(submit)){
				idPejabat = "99999";
			}
			if ("doChangeJenisTanah".equals(submit)){
				idHakmilikAgensi = "";
				idPHPBorangK = "";
			}

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail",getParam("noFail") == null  || "".equals(getParam("noFail"))? "JKPTG/BPHP/" : getParam("noFail"));
			hashPermohonan.put("jenisFail", getParam("jenisFail") == null ? "" : getParam("jenisFail"));
			hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null  ? "" : getParam("tarikhTerima"));
			hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
			hashPermohonan.put("perkara", getParam("txtPerkara") == null ? "": getParam("txtPerkara"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			// MAKLUMAT PEMOHON
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenguatkuasa("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
			
			beanMaklumatPemohon = new Vector();
			Hashtable hashP = new Hashtable();
			hashP.put("namaPengadu", getParam("txtNamaPengadu") == null ? "" : getParam("txtNamaPengadu"));
			beanMaklumatPemohon.addElement(hashP);
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
			
			if ("3".equals(idKategoriPemohon)) {
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
				
			}else
			if ("1".equals(idKategoriPemohon) || "2".equals(idKategoriPemohon) || "6".equals(idKategoriPemohon) || "7".equals(idKategoriPemohon) || ("9".equals(idKategoriPemohon))) {
				
				beanMaklumatPemohon = new Vector();
				Hashtable hashPemohon = new Hashtable();
				hashPemohon.put("nama", getParam("txtNama") == null ? "": getParam("txtNama"));
				hashPemohon.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
				hashPemohon.put("jenisPerniagaan",getParam("txtJenisPerniagaan") == null ? "": getParam("txtJenisPerniagaan"));
				hashPemohon.put("alamat1", getParam("txtAlamat1") == null ? "": getParam("txtAlamat1"));
				hashPemohon.put("alamat2", getParam("txtAlamat2") == null ? "": getParam("txtAlamat2"));
				hashPemohon.put("alamat3", getParam("txtAlamat3") == null ? "": getParam("txtAlamat3"));
				hashPemohon.put("poskod", getParam("txtPoskod") == null ? "": getParam("txtPoskod"));
				hashPemohon.put("emel", getParam("txtEmel") == null ? "": getParam("txtEmel"));
				hashPemohon.put("noTel", getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
				hashPemohon.put("noFaks", getParam("txtNoFaks") == null ? "": getParam("txtNoFaks"));
				hashPemohon.put("namaPengadu", getParam("txtNamaPengadu") == null ? "" : getParam("txtNamaPengadu"));
				beanMaklumatPemohon.addElement(hashPemohon);
	
				this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
				
			} else if (("4".equals(idKategoriPemohon)) || ("5".equals(idKategoriPemohon)) || ("8".equals(idKategoriPemohon))) {
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				if ("8".equals(idKategoriPemohon)) {
					
					this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyenJKPTG", Long.parseLong(idSeksyenJKPTG), "", " "));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeri("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
				} else if ("4".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "1"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					
				} else if ("5".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"", " onChange=\"doChangePejabat();\"",idNegeri, "2"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
				}
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
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"));
				if (idHakmilikAgensi.isEmpty()) {
					idHakmilikSementara = logic.getIdHakmilikSementaraByPeganganHakmilik(getParam("txtPeganganHakmilik"));
					if (idHakmilikSementara.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
					}
				}
			}
			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
			//MAKLUMAT BORANG K
			if ("doChangePeganganHakmilikBorangK".equals(submit)) {
				idPHPBorangK = logic.getIdPHPBorangKByPeganganHakmilik(getParam("txtPeganganHakmilik"));
				if (idPHPBorangK.isEmpty()) {
					idHakmilikUrusan = logic.getIdHakmilikUrusanByPeganganHakmilik(getParam("txtPeganganHakmilik"));
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
		this.context.put("actionPengkuatkuasaan", actionPengkuatkuasaan);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
		this.context.put("idNegeri", idNegeri);
		this.context.put("idKementerian", idKementerian);
		this.context.put("idAgensi", idAgensi);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		this.context.put("idPHPBorangK", idPHPBorangK);
		this.context.put("idPPTBorangK", idPPTBorangK);
		this.context.put("idHakmilikUrusan", idHakmilikUrusan);
		this.context.put("idHakmilikSementara", idHakmilikSementara);

		return vm;
	}
}
