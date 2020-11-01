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
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmCRBSenaraiFailData;

public class FrmCRBSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmCRBSenaraiFailData logic = new FrmCRBSenaraiFailData();

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
		
		String jenisHakmilik = getParam("jenisHakmilik");
		String jenisLot = getParam("jenisLot");
		String namaNegeriTanah = getParam("namaNegeriTanah");
		String namaDaerahTanah = getParam("namaDaerahTanah");
		String namaMukimTanah = getParam("namaMukimTanah");

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
		if (idSeksyenJKPTG == null || idSeksyenJKPTG.trim().length() == 0) {
			idSeksyenJKPTG = "99999";
		}
		String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0) {
			idJenisTanah = "99999";
		}
		String idJenisHakmilik = getParam("socJenisHakmilik");
		if (idJenisHakmilik == null || idJenisHakmilik.trim().length() == 0) {
			idJenisHakmilik = "99999";
		}
		String idJenisLot = getParam("socJenisLot");
		if (idJenisLot == null || idJenisLot.trim().length() == 0) {
			idJenisLot = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		String idNegeriTanah = getParam("socNegeriTanah");
		if (idNegeriTanah == null || idNegeriTanah.trim().length() == 0) {
			idNegeriTanah = "99999";
		}
		String idDaerahTanah = getParam("socDaerahTanah");
		if (idDaerahTanah == null || idDaerahTanah.trim().length() == 0) {
			idDaerahTanah = "99999";
		}
		String idMukimTanah = getParam("socMukimTanah");
		if (idMukimTanah == null || idMukimTanah.trim().length() == 0) {
			idMukimTanah = "99999";
		}

		// DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		// HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
								
				// SAVE HAKMILIK SEMENTARA FIRST
				if ("4".equals(getParam("socJenisTanah"))) {
					idHakmilikSementara = logic.saveHakmilikSementara(idJenisHakmilik, getParam("txtNoHakmilikTanah"),  
							getParam("txtNoWarta"), getParam("tarikhWarta"), idJenisLot, getParam("txtNoLot"),  
							getParam("txtLuasBersamaan"), idNegeriTanah, idDaerahTanah, idMukimTanah, session);
				}
				idFail = logic.daftarBaru(idJenisTanah,
						getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
						idKategoriPemohon, getParam("txtNama"),
						getParam("txtNoPendaftaran"), getParam("txtAlamat1"),
						getParam("txtAlamat2"), getParam("txtAlamat3"),
						getParam("txtPoskod"), idBandar, idNegeri,
						getParam("txtEmel"), getParam("txtNoTel"),
						getParam("txtNoFaks"), idKementerian, idAgensi,
						idPejabat, idSeksyenJKPTG, getParam("txtNamaPengadu"),
						getParam("txtNoFailNegeri"), getParam("idKementerianTanah"),
						getParam("idNegeriTanah"), idHakmilikAgensi,
						idPPTBorangK, idHakmilikUrusan, idPHPBorangK,
						idHakmilikSementara, getParam("txtPeganganHakmilik"), session);
			}
		}

		this.context.put("errorPeganganHakmilik", "");

		if ("papar".equals(actionPengkuatkuasaan)) {

			// GO TO VIEW PENGUATKUASAAN
			vm = "app/php2/frmCRBDaftarManual.jsp";

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
			if (logic.getBeanMaklumatPemohon().size() != 0) {
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idPejabat = (String) hashPemohon.get("idPejabat");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
			}

			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenguatkuasa("socKategoriPemohon",Long.parseLong(idKategoriPemohon), "disabled"," class=\"disabled\""));
			
			if ("3".equals(idKategoriPemohon)) {

				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);

				this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian),"disabled", " class=\"disabled\""));
				this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian,Long.parseLong(idAgensi), "disabled"," class=\"disabled\""));

			} else if ("1".equals(idKategoriPemohon) || "2".equals(idKategoriPemohon) || "6".equals(idKategoriPemohon)
					|| "7".equals(idKategoriPemohon) || "9".equals(idKategoriPemohon)) {

				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled"," class=\"disabled\""));
				this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar),"disabled", " class=\"disabled\""));

			} else if (("4".equals(idKategoriPemohon)) || ("5".equals(idKategoriPemohon)) || ("8".equals(idKategoriPemohon))) {

				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled"," class=\"disabled\""));
				
				if ("8".equals(idKategoriPemohon)) {

					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					Hashtable hashNegeri = (Hashtable) logic.getBeanMaklumatPejabat().get(0);
					this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyenJKPTG", Long.parseLong(idSeksyenJKPTG),"disabled", " class=\"disabled\""));
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "disabled"," class=\"disabled\""));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat",Long.parseLong(idPejabat), "disabled"," class=\"disabled\"",(String) hashNegeri.get("idNegeri"), "4"));

				} else if ("4".equals(idKategoriPemohon)) {

					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"disabled", " class=\"disabled\"",idNegeri, "1"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);

				} else if ("5".equals(idKategoriPemohon)) {

					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),"disabled", " class=\"disabled\"",idNegeri, "2"));
					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabat(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
				}
			}

			String flagBorangK = "";
			logic.setMaklumatHakmilik(logic.getIdHakmilikPermohonanByIdFail(idFail));
			if (logic.getBeanMaklumatHakmilik().size() != 0) {
				Hashtable hashHakmilik = (Hashtable) logic.getBeanMaklumatHakmilik().get(0);
				flagBorangK = (String) hashHakmilik.get("flagBorangK");
			}

			if ("Y".equals(flagBorangK)) {
				beanMaklumatBorangK = new Vector();
				beanMaklumatBorangK = logic.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
				this.context.put("idJenisTanah", "3");
				
			} else if ("4".equals(idJenisTanah)) {
				
				beanMaklumatTanah = new Vector();
	        	logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
				beanMaklumatTanah = logic.getBeanMaklumatTanah();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

				this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "disabled", "class=\"disabled\""));
				this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot", Long.parseLong(idJenisLot), "disabled", "class=\"disabled\""));
				this.context.put("selectNegeriTanah", HTML.SelectNegeri("socNegeriTanah", Long.parseLong(idNegeriTanah), "disabled", "class=\"disabled\""));
				this.context.put("selectDaerahTanah", HTML.SelectDaerahByIdNegeri(idNegeriTanah, "socDaerahTanah", Long.parseLong(idDaerahTanah), "disabled", "class=\"disabled\""));
				this.context.put("selectMukimTanah", HTML.SelectMukimNoKodByDaerah(idDaerahTanah, "socMukimTanah", Long.parseLong(idMukimTanah), "disabled", "class=\"disabled\""));

			} else {
				
				beanMaklumatTanah = new Vector();
				beanMaklumatTanah = logic.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				this.context.put("idJenisTanah", "1");
			}
			
		} else if ("daftarBaru".equals(actionPengkuatkuasaan)) {

			// GO TO DAFTAR BARU PENGUATKUASAAN
			vm = "app/php2/frmCRBDaftarManual.jsp";

			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");
			this.context.put("jenisPemohon", "");
			this.context.put("jenisPemohonDetail", "");

			if ("doChangeKategori".equals(submit)) {
				idNegeri = "99999";
				idPejabat = "99999";
				idSeksyenJKPTG = "99999";
			}
			if ("doChangeKementerian".equals(submit)) {
				idAgensi = "99999";
			}
			if ("doChangeNegeri".equals(submit)) {
				idPejabat = "99999";
			}
			if ("doChangeJenisTanah".equals(submit)) {
				idHakmilikAgensi = "";
				idPHPBorangK = "";
			}
			
			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailNegeri", getParam("txtNoFailNegeri") == null ? "": getParam("txtNoFailNegeri"));
			hashPermohonan.put("jenisFail", getParam("jenisFail") == null ? "" : getParam("jenisFail"));
			hashPermohonan.put("tarikhTerima", getParam("tarikhTerima") == null ? "" : getParam("tarikhTerima"));
			hashPermohonan.put("tarikhSurat", getParam("tarikhSurat") == null ? "" : getParam("tarikhSurat"));
			hashPermohonan.put("perkara", getParam("txtPerkara") == null ? "" : getParam("txtPerkara"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

			// MAKLUMAT PEMOHON
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonPenguatkuasa("socKategoriPemohon", Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));

			beanMaklumatPemohon = new Vector();
			Hashtable hashP = new Hashtable();
			hashP.put("namaPengadu", getParam("txtNamaPengadu") == null ? "" : getParam("txtNamaPengadu"));
			beanMaklumatPemohon.addElement(hashP);
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);

			if ("3".equals(idKategoriPemohon)) {

				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);

				this.context.put("selectKementerian", HTML.SelectKementerian(
						"socKementerian", Long.parseLong(idKementerian), "",
						" onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi", HTML
						.SelectAgensiByKementerian("socAgensi", idKementerian,
								Long.parseLong(idAgensi), "",
								" onChange=\"doChangeAgensi();\""));

			} else if ("1".equals(idKategoriPemohon)
					|| "2".equals(idKategoriPemohon)
					|| "6".equals(idKategoriPemohon)
					|| "7".equals(idKategoriPemohon)
					|| ("9".equals(idKategoriPemohon))) {

				beanMaklumatPemohon = new Vector();
				Hashtable hashPemohon = new Hashtable();
				hashPemohon.put("nama", getParam("txtNama") == null ? ""
						: getParam("txtNama"));
				hashPemohon.put("noPendaftaran",
						getParam("txtNoPendaftaran") == null ? ""
								: getParam("txtNoPendaftaran"));
				hashPemohon.put("jenisPerniagaan",
						getParam("txtJenisPerniagaan") == null ? ""
								: getParam("txtJenisPerniagaan"));
				hashPemohon.put("alamat1", getParam("txtAlamat1") == null ? ""
						: getParam("txtAlamat1"));
				hashPemohon.put("alamat2", getParam("txtAlamat2") == null ? ""
						: getParam("txtAlamat2"));
				hashPemohon.put("alamat3", getParam("txtAlamat3") == null ? ""
						: getParam("txtAlamat3"));
				hashPemohon.put("poskod", getParam("txtPoskod") == null ? ""
						: getParam("txtPoskod"));
				hashPemohon.put("emel", getParam("txtEmel") == null ? ""
						: getParam("txtEmel"));
				hashPemohon.put("noTel", getParam("txtNoTel") == null ? ""
						: getParam("txtNoTel"));
				hashPemohon.put("noFaks", getParam("txtNoFaks") == null ? ""
						: getParam("txtNoFaks"));
				hashPemohon.put("namaPengadu",
						getParam("txtNamaPengadu") == null ? ""
								: getParam("txtNamaPengadu"));
				beanMaklumatPemohon.addElement(hashPemohon);

				this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);

				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Long.parseLong(idNegeri), "",
						" onChange=\"doChangeNegeri();\""));
				this.context.put(
						"selectBandar",
						HTML.SelectBandarByNegeri(idNegeri, "socBandar",
								Long.parseLong(idBandar), ""));

			} else if (("4".equals(idKategoriPemohon)) || ("5".equals(idKategoriPemohon)) || ("8".equals(idKategoriPemohon))) {

				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
				
				if ("8".equals(idKategoriPemohon)) {

					this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyenJKPTG",Long.parseLong(idSeksyenJKPTG), "", " "));
					this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat",Long.parseLong(idPejabat), ""," onChange=\"doChangePejabat();\"",idNegeri, "4"));

					beanMaklumatPejabat = new Vector();
					logic.setMaklumatPejabatJKPTG(idPejabat);
					beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
					this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);

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
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", idJenisTanah);
			} else if ("2".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", idJenisTanah);
			} else if ("3".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "selected");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", idJenisTanah);
			} else if ("4".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "selected");
				this.context.put("idJenisTanah", idJenisTanah);
			} else {
				this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", "0");
			}

			// MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"));
				if (idHakmilikAgensi.isEmpty()) {
					idHakmilikSementara = logic.getIdHakmilikSementaraByPeganganHakmilik(getParam("txtPeganganHakmilik"));
					if (idHakmilikSementara.isEmpty()) {
						this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
					}
				}
			}						
			
			this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", "onChange=\"doChangeJenisHakmilik();\""));
			this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot", Long.parseLong(idJenisLot), "", "onChange=\"doChangeJenisLot();\"").toUpperCase());
			this.context.put("selectNegeriTanah", HTML.SelectNegeri("socNegeriTanah", Long.parseLong(idNegeriTanah), "", "onChange=\"doChangeNegeriTanah();\""));
			this.context.put("selectDaerahTanah", HTML.SelectDaerahByIdNegeri(idNegeriTanah, "socDaerahTanah", Long.parseLong(idDaerahTanah), "", "onChange=\"doChangeDaerahTanah();\""));
			this.context.put("selectMukimTanah", HTML.SelectMukimNoKodByDaerah(idDaerahTanah, "socMukimTanah", Long.parseLong(idMukimTanah), "", "onChange=\"doChangeMukimTanah();\""));
			
			if ("4".equals(idJenisTanah)) {
				//TODO
				beanMaklumatTanah = new Vector();
				Hashtable hashMaklumatTanah = new Hashtable();
				hashMaklumatTanah.put("noHakmilik", getParam("txtNoHakmilikTanah") == null ? ""
						: getParam("txtNoHakmilikTanah"));
				hashMaklumatTanah.put("noWarta", getParam("txtNoWarta") == null ? ""
						: getParam("txtNoWarta"));
				hashMaklumatTanah.put("tarikhWarta", getParam("tarikhWarta") == null ? ""
						: getParam("tarikhWarta"));
				hashMaklumatTanah.put("noLot", getParam("txtNoLot") == null ? ""
						: getParam("txtNoLot"));
				if ("doChangeLuas".equals(submit)) {
					hashMaklumatTanah.put("luas1", "");
					hashMaklumatTanah.put("luas2", "");
					hashMaklumatTanah.put("luas3", "");
					hashMaklumatTanah.put("luasBersamaan", "");
				} else {
					hashMaklumatTanah.put("luas1", getParam("txtLuas1") == null ? ""
							: getParam("txtLuas1"));
					hashMaklumatTanah.put("luas2", getParam("txtLuas2") == null ? ""
							: getParam("txtLuas2"));
					hashMaklumatTanah.put("luas3", getParam("txtLuas3") == null ? ""
							: getParam("txtLuas3"));
					hashMaklumatTanah.put("luasBersamaan", getParam("txtLuasBersamaan") == null ? ""
							: getParam("txtLuasBersamaan"));
				}
				if ("doChangeJenisHakmilik".equals(submit)) {
					jenisHakmilik = logic.getKodJenisHakmilik(idJenisHakmilik);
					this.context.put("jenisHakmilik", jenisHakmilik);
				}  
				if ("doChangeJenisLot".equals(submit)) {
					jenisLot = logic.getKodJenisLot(idJenisLot);
					this.context.put("jenisLot", jenisLot);
				}
				if ("doChangeNegeriTanah".equals(submit)) {
					namaNegeriTanah = logic.getNamaNegeriById(idNegeriTanah);
					this.context.put("namaNegeriTanah", namaNegeriTanah);
					this.context.put("idNegeriTanah", idNegeriTanah);
				}
				if ("doChangeDaerahTanah".equals(submit)) {
					namaDaerahTanah = logic.getNamaDaerahById(idDaerahTanah);
					this.context.put("namaDaerahTanah", namaDaerahTanah);
				}
				if ("doChangeMukimTanah".equals(submit)) {
					namaMukimTanah = logic.getNamaMukimById(idMukimTanah);
					this.context.put("namaMukimTanah", namaMukimTanah);
				}
				
				beanMaklumatTanah.addElement(hashMaklumatTanah);
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				
				if ("doChangeNegeriTanah".equals(submit)){
	        		idDaerahTanah = "99999";
	        		idMukimTanah = "99999";
	        	}
	        	if ("doChangeDaerahTanah".equals(submit)){
	        		idMukimTanah = "99999";
	        	}
	        	
	        	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(idJenisHakmilik), "", "onChange=\"doChangeJenisHakmilik();\""));
				this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot", Long.parseLong(idJenisLot), "", "onChange=\"doChangeJenisLot();\""));
				this.context.put("selectNegeriTanah", HTML.SelectNegeri("socNegeriTanah", Long.parseLong(idNegeriTanah), "", "onChange=\"doChangeNegeriTanah();\""));
				this.context.put("selectDaerahTanah", HTML.SelectDaerahByIdNegeri(idNegeriTanah, "socDaerahTanah", Long.parseLong(idDaerahTanah), "", "onChange=\"doChangeDaerahTanah();\""));
				this.context.put("selectMukimTanah", HTML.SelectMukimNoKodByDaerah(idDaerahTanah, "socMukimTanah", Long.parseLong(idMukimTanah), "", "onChange=\"doChangeMukimTanah();\""));
	        	
			}
			else {
				beanMaklumatTanah = new Vector();
				logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
				beanMaklumatTanah = logic.getBeanMaklumatTanah();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			}
			
			// MAKLUMAT BORANG K
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
			logic.setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan,idPHPBorangK);
			beanMaklumatBorangK = logic.getBeanMaklumatBorangK();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);

		} else {

			// DROP DOWN CARIAN
			String idJenisHakmilikC = getParam("socJenisHakmilikC");
			if (idJenisHakmilikC == null
					|| idJenisHakmilikC.trim().length() == 0) {
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
			String idStatusC = getParam("socStatusC");
			if (idStatusC == null || idStatusC.trim().length() == 0) {
				idStatusC = "99999";
			}
			String idKementerianTanah = getParam("socKementerianTanah");
			if (idKementerianTanah == null
					|| idKementerianTanah.trim().length() == 0) {
				idKementerianTanah = "99999";
			}
			String idAgensiTanah = getParam("socAgensiTanah");
			if (idAgensiTanah == null || idAgensiTanah.trim().length() == 0) {
				idAgensiTanah = "99999";
			}
			String flagDetail = getParam("flagDetail");

			// GO TO LIST FAIL PENGUATKUASAAN
			vm = "app/php2/frmCRBSenaraiFail.jsp";

			logic.carianFail(getParam("txtNoFail"), getParam("txtNoFailNegeri"), getParam("txtPemohon"),
					getParam("txtNoPengenalan"), getParam("txdTarikhTerima"),
					idNegeriC, idDaerahC, idMukimC, idJenisHakmilikC,
					getParam("txtNoHakmilik"), getParam("txtNoWarta"), idLotC,
					getParam("txtNoLot"), getParam("txtNoPegangan"), idStatusC,
					idKementerianTanah, idAgensiTanah, getParam("txtKegunaanTnh"), 
					userId, idNegeriUser, userRole);

			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);

			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtNoFailNegeri", getParam("txtNoFailNegeri"));
			this.context.put("txtPemohon", getParam("txtPemohon"));
			this.context.put("txtNoPengenalan", getParam("txtNoPengenalan"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));

			this.context.put("txtNoPegangan", getParam("txtNoPegangan"));
			this.context.put(
					"selectJenisHakmilik",
					HTML.SelectJenisHakmilik("socJenisHakmilikC",
							Long.parseLong(idJenisHakmilikC), ""));
			this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("selectLot",
					HTML.SelectLot("socLotC", Long.parseLong(idLotC), ""));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("txtKegunaanTnh", getParam("txtKegunaanTnh"));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeriC",
					Long.parseLong(idNegeriC), "",
					" onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(
					idNegeriC, "socDaerahC", Long.parseLong(idDaerahC), "",
					" onChange=\"doChangeDaerah();\""));
			this.context.put(
					"selectMukim",
					HTML.SelectMukimByDaerah(idDaerahC, "socMukimC",
							Long.parseLong(idMukimC), ""));
			this.context.put(
					"selectStatus",
					HTML.SelectStatusPenguatkuasan("socStatusC",
							Long.parseLong(idStatusC), "", ""));
			this.context.put("selectKementerian", HTML.SelectKementerian(
					"socKementerianTanah", Long.parseLong(idKementerianTanah),
					"", " onChange=\"doChangeKementerianTanah();\""));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian(
					"socAgensiTanah", idKementerianTanah,
					Long.parseLong(idAgensiTanah), "", ""));

			this.context.put("flagDetail", flagDetail);
			setupPage(session, action, list);
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
		this.context.put("idPPTBorangK", idPPTBorangK);
		this.context.put("idHakmilikUrusan", idHakmilikUrusan);
		this.context.put("idPHPBorangK", idPHPBorangK);
		this.context.put("idHakmilikSementara", idHakmilikSementara);
		this.context.put("idNegeriTanah", idNegeriTanah);
		this.context.put("idDaerahTanah", idDaerahTanah);
		this.context.put("idJenisHakmilik", idJenisHakmilik);
		this.context.put("idJenisLot", idJenisLot);
		this.context.put("idJenisTanah", idJenisTanah);
		this.context.put("idLuas", idLuas);
		
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
