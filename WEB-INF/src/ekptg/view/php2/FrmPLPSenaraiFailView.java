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
import ekptg.model.php2.FrmPLPSenaraiFailData;

public class FrmPLPSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmPLPSenaraiFailData logic = new FrmPLPSenaraiFailData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		
		userId = (String)session.getAttribute("_ekptg_user_id");
		userRole = (String)session.getAttribute("myrole");
		idNegeriUser = (String)session.getAttribute("_ekptg_user_negeri");
		
		this.context.put("userId", userId);
		this.context.put("userRole", userRole);
		this.context.put("idNegeriUser", idNegeriUser);

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPelepasan = getParam("actionPelepasan");
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

		// VECTOR
		Vector list = null;

		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatPemohon = null;
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
		
		//HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idJenisTanah,
						getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
						idKategoriPemohon, getParam("txtNama"),
						getParam("txtNoPendaftaran"), getParam("txtAlamat1"),
						getParam("txtAlamat2"), getParam("txtAlamat3"),
						getParam("txtPoskod"), idBandar, idNegeri,
						getParam("txtEmel"), getParam("txtNoTel"),
						getParam("txtNoFaks"), idHakmilikAgensi, idPPTBorangK,
						idHakmilikUrusan, idPHPBorangK, idKementerian,
						idAgensi, idPejabat, idLuasKegunaan,
						getParam("txtTujuanKegunaan"),
						getParam("idKementerianTanah"),
						getParam("idNegeriTanah"), getParam("txtNoFailNegeri"),
						getParam("idLuasTanah"), getParam("luasTanah"),
						idHakmilikSementara, session);
				
				idStatus = "1610198"; // MAKLUMAT PERMOHONAN

			}
		}

		this.context.put("errorPeganganHakmilik", "");

		if ("papar".equals(actionPelepasan)) {

			// GO TO VIEW PELEPASAN
			vm = "app/php2/frmPLPDaftarManual.jsp";

			this.context.put("mode", "view");
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPemohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				idLuasKegunaan = (String) hashPemohonan.get("flagGuna");
				
			}
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\""));
			
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

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPelepasan("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			if ("3".equals(idKategoriPemohon)) {
				
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);
				
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
				
			} else if ("1".equals(idKategoriPemohon) || "2".equals(idKategoriPemohon) || "6".equals(idKategoriPemohon) || "7".equals(idKategoriPemohon)) {
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
				
			} else if (("4".equals(idKategoriPemohon)) || ("5".equals(idKategoriPemohon)) || ("8".equals(idKategoriPemohon))) {
				
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
				if ("8".equals(idKategoriPemohon)) {
					
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",idNegeri, "4"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat",beanMaklumatPejabat);
					
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
			
			if ("3".equals(idKategoriPemohon)) {
				idNegeri ="";
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
			
		} else if ("daftarBaru".equals(actionPelepasan)) {

			// GO TO DAFTAR BARU PELEPASAN
			vm = "app/php2/frmPLPDaftarManual.jsp";

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

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailNegeri", getParam("txtNoFailNegeri") == null ? "": getParam("txtNoFailNegeri"));
			hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
			hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
			hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
			hashPermohonan.put("perkara", getParam("txtPerkara") == null ? "": getParam("txtPerkara"));
			hashPermohonan.put("tujuanKegunaan", getParam("txtTujuanKegunaan") == null ? "": getParam("txtTujuanKegunaan"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " "));

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
					
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));
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
				this.context.put("idJenisTanah", idJenisTanah);
        	} else if ("2".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("idJenisTanah", idJenisTanah);
        	} else {
        		this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
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

			// GO TO LIST FAIL PELEPASAN
			vm = "app/php2/frmPLPSenaraiFail.jsp";


			logic.carianFail(getParam("txtNoFail"),
					getParam("txtNoFailNegeri"), getParam("txtTajukFail"),
					getParam("txtPemohon"), getParam("txtNoPengenalan"),
					getParam("txdTarikhTerima"), idNegeriC, idDaerahC,
					idMukimC, idJenisHakmilikC, getParam("txtNoHakmilik"),
					getParam("txtNoWarta"), idLotC, getParam("txtNoLot"),
					getParam("txtNoPegangan"), idStatusC, idKementerianC,
					idAgensiC, getParam("checkTanah"), userId, idNegeriUser,
					userRole);
			
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);

			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtNoFailNegeri", getParam("txtNoFailNegeri"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
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
			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerianC", Long.parseLong(idKementerianC), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensiC", idKementerianC,  Long.parseLong(idAgensiC), "", ""));
			this.context.put("selectStatus", HTML.SelectStatusPelepasan("socStatusC", Long.parseLong(idStatusC), "", ""));
			this.context.put("checkTanah", getParam("checkTanah"));
			this.context.put("flagTanah", getParam("checkTanah"));
			this.context.put("flagDetail", flagDetail);
			setupPage(session, action, list);
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
		this.context.put("idJenisTanah", idJenisTanah);
		
		if (session.getAttribute("MSG") != null){
			this.context.put("errMsg", session.getAttribute("MSG"));
			session.removeAttribute("MSG");
		} else {
			this.context.put("errMsg", "");
		}
		
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
