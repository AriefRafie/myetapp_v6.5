package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmPLPKutipanData;
import ekptg.model.php2.FrmPNWKutipanData;
import ekptg.model.php2.FrmTKRKutipanData;

public class FrmUtilitiKutipanDataView extends AjaxBasedModule{
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String actionPelepasan = getParam("actionPelepasan");
		String submit = getParam("command");
		String hitButton = getParam("hitButton");

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idPPTBorangK = getParam("idPPTBorangK");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String idPHPBorangK = getParam("idPHPBorangK");
		String idHakmilikSementara = getParam("idHakmilikSementara");

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
		String idJenisPendaftaran = getParam("scoJenisPendaftaran");
		if (idJenisPendaftaran == null || idJenisPendaftaran.trim().length() == 0) {
			idJenisPendaftaran = "99999";
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
		this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " "));
		
		this.context.put("errorPeganganHakmilik", "");
        
		String vm = "";
		String idSubUrusan = getParam("socSubUrusan");
		if (idSubUrusan == null || idSubUrusan.trim().length() == 0){
			idSubUrusan = "99999";
		}

		boolean afterPost = false;
		
		if ("PLP".equals(idSubUrusan)) {
			this.context.put("selected", "selected");
			this.context.put("selected1", "");
			this.context.put("selected2", "");
			vm = "app/php2/frmPLPKutipanData.jsp";
			
			FrmPLPKutipanData logic = new FrmPLPKutipanData();
			
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
							idHakmilikAgensi, idPPTBorangK, idHakmilikUrusan, idPHPBorangK, idKementerian, idAgensi, idPejabat,
							idLuasKegunaan, getParam("txtTujuanKegunaan"), 
							getParam("idKementerianTanah"), getParam("idNegeriTanah"), getParam("idLuasTanah"), getParam("luasTanah"),
							idHakmilikSementara, session);
					afterPost = true;
				}
			}
			
			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			
			if ("doChangeKategori".equals(submit)){
				idNegeri = "99999";
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
			if ("doChangeNegeri".equals(submit)){
				idPejabat = "99999";
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

			// MAKLUMAT PEMOHON
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPelepasan("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
			
			if ("3".equals(idKategoriPemohon)) {
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
				
			} else if ("1".equals(idKategoriPemohon) || "2".equals(idKategoriPemohon) || "6".equals(idKategoriPemohon) || "7".equals(idKategoriPemohon)) {
				
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
				beanMaklumatPemohon.addElement(hashPemohon);
	
				this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
				
			} else if (("4".equals(idKategoriPemohon)) || ("5".equals(idKategoriPemohon)) || ("8".equals(idKategoriPemohon))) {
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				if ("8".equals(idKategoriPemohon)) {
					
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
			
			if ("3".equals(idKategoriPemohon)) {
				idNegeri ="";
			}
			
			//MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"), idNegeri);
				if (idHakmilikAgensi.isEmpty()) {
					idHakmilikAgensi = logic.getIdHakmilikSementaraByPeganganHakmilik(getParam("txtPeganganHakmilik"), idNegeri);
					if (idHakmilikAgensi.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
					}
				}
			}
			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara, idNegeri);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
			//MAKLUMAT BORANG K
			if ("doChangePeganganHakmilikBorangK".equals(submit)) {
				idPHPBorangK = logic.getIdPHPBorangKByPeganganHakmilik(getParam("txtPeganganHakmilik"), idNegeri);
				if (idPHPBorangK.isEmpty()) {
					idHakmilikUrusan = logic.getIdHakmilikUrusanByPeganganHakmilik(getParam("txtPeganganHakmilik"), idNegeri);
					if (idHakmilikUrusan.isEmpty()) {
						this.context.put("errorPeganganHakmilik","Maklumat Borang K tidak wujud.");
					}					
				}
			}				
			
			beanMaklumatBorangK = new Vector();
			logic.setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan, idPHPBorangK, idNegeri);
			beanMaklumatBorangK = logic.getBeanMaklumatBorangK();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
			
    	} else if ("TKR".equals(idSubUrusan)) {
			this.context.put("selected", "");
			this.context.put("selected1", "selected");
			this.context.put("selected2", "");
			vm = "app/php2/frmTKRKutipanData.jsp";
			
			FrmTKRKutipanData logic = new FrmTKRKutipanData();
			
			//HITBUTTON
			if (postDB) {
				if ("daftarBaru".equals(hitButton)) {
					idFail = logic.daftarBaru(idJenisTanah, getParam("noFail"), getParam("tarikhTerima"),
							getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
							idKategoriPemohon, idKementerian, idAgensi, idPejabat,
							idHakmilikAgensi, idPPTBorangK, idHakmilikUrusan, idPHPBorangK, 
							idLuasKegunaan, getParam("txtTujuanKegunaan"), 
							getParam("idKementerianTanah"), getParam("idNegeriTanah"), getParam("idLuasTanah"), getParam("luasTanah"),
							session);
					afterPost = true;
				}
			}
			
			String idNegeriJKPTG = getParam("socNegeriJKPTG");
			if (idNegeriJKPTG == null || idNegeriJKPTG.trim().length() == 0){
				idNegeriJKPTG = "99999";
			}
			
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
			
    	} else if ("PNW".equals(idSubUrusan)) {
			this.context.put("selected", "");
			this.context.put("selected1", "");
			this.context.put("selected2", "selected");
			vm = "app/php2/frmPNWKutipanData.jsp";
			
			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			
			FrmPNWKutipanData logic = new FrmPNWKutipanData();
			
			//HITBUTTON
			if (postDB) {
				if ("daftarBaru".equals(hitButton)) {
					idFail = logic.daftarBaru(idJenisTanah, getParam("noFail"), getParam("tarikhTerima"),
							getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
							idKategoriPemohon, idKementerian, idAgensi,
							idHakmilikAgensi, idPPTBorangK, idHakmilikUrusan, idPHPBorangK, 
							getParam("idKementerianTanah"), getParam("idNegeriTanah"), getParam("idLuasTanah"), getParam("luasTanah"),
							session);
					afterPost = true;
				}
			}
			
			if ("doChangeKategori".equals(submit)){
				idKementerian = "99999";
				idAgensi = "99999";
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

			// MAKLUMAT PEMOHON
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenawaran("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
			
			if ("3".equals(idKategoriPemohon)) {
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", " onChange=\"doChangeAgensi();\""));
				
			} else if (("8".equals(idKategoriPemohon))) {
				
				idPejabat = "102";
				
				beanMaklumatPejabat = new Vector();
				logic.setMaklumatPejabatJKPTG(idPejabat);
				beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
				this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
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
			
    	} else {
			vm = "app/php2/frmUtilitiKutipanData.jsp";
    	}
		
		if(afterPost){
			vm = "app/php2/frmUtilitiKutipanData.jsp";
			this.context.put("afterPost", "yes");
		}
		
		// SET DEFAULT PARAM
		this.context.put("actionPelepasan", actionPelepasan);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
		this.context.put("idNegeri", idNegeri);
		this.context.put("idKementerian", idKementerian);
		this.context.put("idAgensi", idAgensi);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		this.context.put("idPPTBorangK", idPPTBorangK);
		this.context.put("idHakmilikUrusan", idHakmilikUrusan);
		this.context.put("idPHPBorangK", idPHPBorangK);		
		this.context.put("idHakmilikSementara", idHakmilikSementara);
		
		this.context.put("socSubUrusan", idSubUrusan);
		System.out.println ("*********** Test  :" +vm);
	    return vm;

	}
}
