/**
 * 
 */
package ekptg.view.php2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmPYWSenaraiFailData;
import ekptg.model.php2.utiliti.PHPUtilHTML;

/**
 * @author hilda
 * 
 */
public class FrmPYWSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPYWSenaraiFailView.class);

	FrmPYWSenaraiFailData logic = new FrmPYWSenaraiFailData();
	
	String userId = null;
	String userRole = null;
	String idNegeriUser = null;

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String)session.getAttribute("doPost");
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
		String actionPenyewaan = getParam("actionPenyewaan");
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
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatBorangK = null;

		// GET DROPDOWN PARAM
		String idUrusan = getParam("socUrusan");
		if (idUrusan == null || idUrusan.trim().length() == 0) {
			idUrusan = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0) {
			idSuburusan = "99999";
		}
		String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0) {
			idBandar = "99999";
		}
		String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}
		String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idJenisTujuan = getParam("socJenisTujuan");
		if (idJenisTujuan == null || idJenisTujuan.trim().length() == 0) {
			idJenisTujuan = "99999";
		}
		String idJenisTujuan2 = getParam("socJenisTujuan2");
		if (idJenisTujuan2 == null || idJenisTujuan2.trim().length() == 0) {
			idJenisTujuan2 = "99999";
		}
		String idJenisTujuan3 = getParam("socJenisTujuan3");
		if (idJenisTujuan3 == null || idJenisTujuan3.trim().length() == 0) {
			idJenisTujuan3 = "99999";
		}
		
		//DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		//HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idJenisTanah, getParam("tarikhTerima"),
						getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
						idKategoriPemohon, getParam("txtNama"), getParam("txtNamaPegawai"),
						getParam("txtNoPendaftaran"), getParam("txtPekerjaan"), getParam("txtAlamat1"),
						getParam("txtAlamat2"), getParam("txtAlamat3"), getParam("txtEmel"), 
						getParam("txtPoskod"), idBandar, idNegeri, getParam("txtNoTel"), getParam("txtNoFaks"),
						idHakmilikAgensi, idPPTBorangK, idHakmilikUrusan, idPHPBorangK, idJenisTujuan, idJenisTujuan2,
						idJenisTujuan3, getParam("txtTujuan"), getParam("txtTujuan2"), getParam("txtTujuan3"),
						getParam("idKementerianTanah"), getParam("idNegeriTanah"), getParam("idLuasTanah"), getParam("luasTanah"),
						idUrusan, idSuburusan, "J", getParam("txtCatatan"), idHakmilikSementara, getParam("txtNoFailNegeri"),
						idLuasKegunaan, session);
				
				idStatus = "1610198"; //MAKLUMAT PERMOHONAN	
			}
		}
		
		this.context.put("errorPeganganHakmilik", "");
		
		if ("papar".equals(actionPenyewaan)) {

			// GO TO VIEW PENYEWAAN
			vm = "app/php2/frmPYWDaftarManual.jsp";

			this.context.put("mode", "view");
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				idUrusan = (String) hashPermohonan.get("idUrusan");
				idSuburusan = (String) hashPermohonan.get("idSuburusan");
				idJenisTujuan = (String) hashPermohonan.get("idJenisTujuan");
				idJenisTujuan2 = (String) hashPermohonan.get("idJenisTujuan");
				idJenisTujuan3 = (String) hashPermohonan.get("idJenisTujuan");
			}
			
			this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled", " class=\"disabled\""));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "disabled", " class=\"disabled\""));
			this.context.put("selectJenisTujuan", PHPUtilHTML.SelectTujuanByIdSuburusan(idSuburusan, "socJenisTujuan", Long.parseLong(idJenisTujuan), "disabled", "class=\"disabled\""));
			this.context.put("selectJenisTujuan2", PHPUtilHTML.SelectTujuanByIdSuburusan(idSuburusan, "socJenisTujuan2", Long.parseLong(idJenisTujuan2), "disabled", "class=\"disabled\""));
			this.context.put("selectJenisTujuan3", PHPUtilHTML.SelectTujuanByIdSuburusan(idSuburusan, "socJenisTujuan3", Long.parseLong(idJenisTujuan3), "disabled", "class=\"disabled\""));
			
			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
			}
			
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon", Long.parseLong(idKategoriPemohon), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
			
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
				this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
			}		

		} else if ("daftarBaru".equals(actionPenyewaan)) {

			// GO TO DAFTAR BARU PENYEWAAN
			vm = "app/php2/frmPYWDaftarManual.jsp";

			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");
			
			if ("doChangeJenisTanah".equals(submit)){
				idHakmilikAgensi = "";
				idPHPBorangK = "";
				idPPTBorangK = "";
				idHakmilikUrusan = "";
			}

			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailNegeri", getParam("txtNoFailNegeri") == null ? "": getParam("txtNoFailNegeri"));
			hashPermohonan.put("flagProsesFail",getParam("socFlagProsesFail") == null ? "": getParam("socFlagProsesFail"));
			hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
			hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
			hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
			hashPermohonan.put("perkara", getParam("txtPerkara") == null ? "": getParam("txtPerkara"));
			hashPermohonan.put("catatan", getParam("txtCatatan") == null ? "": getParam("txtCatatan"));
			hashPermohonan.put("tujuan", getParam("txtTujuan") == null ? "": getParam("txtTujuan"));
			hashPermohonan.put("tujuan2", getParam("txtTujuan2") == null ? "": getParam("txtTujuan2"));
			hashPermohonan.put("tujuan3", getParam("txtTujuan3") == null ? "": getParam("txtTujuan3"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "", " onChange=\"doChangeUrusan();\""));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "", " onChange=\"doChangeSuburusan();\""));
			this.context.put("selectJenisTujuan", PHPUtilHTML.SelectTujuanByIdSuburusan(idSuburusan, "socJenisTujuan", Long.parseLong(idJenisTujuan), "", " onChange=\"doChangeTujuan();\""));
			this.context.put("selectJenisTujuan2", PHPUtilHTML.SelectTujuanByIdSuburusan(idSuburusan, "socJenisTujuan2", Long.parseLong(idJenisTujuan2), "", " onChange=\"doChangeTujuan();\""));
			this.context.put("selectJenisTujuan3", PHPUtilHTML.SelectTujuanByIdSuburusan(idSuburusan, "socJenisTujuan3", Long.parseLong(idJenisTujuan3), "", " onChange=\"doChangeTujuan();\""));
			
			// MAKLUMAT PEMOHON			
			beanMaklumatPemohon = new Vector();
			Hashtable hashPemohon = new Hashtable();
			hashPemohon.put("nama", getParam("txtNama") == null ? "": getParam("txtNama"));
			hashPemohon.put("namaPegawai", getParam("txtNamaPegawai") == null ? "": getParam("txtNamaPegawai"));
			hashPemohon.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
			hashPemohon.put("pekerjaan",getParam("txtPekerjaan") == null ? "": getParam("txtPekerjaan"));
			hashPemohon.put("alamat1", getParam("txtAlamat1") == null ? "": getParam("txtAlamat1"));
			hashPemohon.put("alamat2", getParam("txtAlamat2") == null ? "": getParam("txtAlamat2"));
			hashPemohon.put("alamat3", getParam("txtAlamat3") == null ? "": getParam("txtAlamat3"));
			hashPemohon.put("poskod", getParam("txtPoskod") == null ? "": getParam("txtPoskod"));
			hashPemohon.put("emel", getParam("txtEmel") == null ? "": getParam("txtEmel"));
			hashPemohon.put("noTel", getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
			hashPemohon.put("noFaks", getParam("txtNoFaks") == null ? "": getParam("txtNoFaks"));
			beanMaklumatPemohon.addElement(hashPemohon);

			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
			
			this.context.put("selectKategoriPemohon", HTML.SelectKategoriPemohonIndividuAndSyarikat("socKategoriPemohon",Long.parseLong(idKategoriPemohon), ""," onChange=\"doChangeKategori();\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
			
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
        	}else {
        		this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("selected3", "");
				this.context.put("selected4", "");
				this.context.put("idJenisTanah", "0");
        	}
			
			//MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"));
				if (idHakmilikAgensi.isEmpty()) {
					idHakmilikAgensi = logic.getIdHakmilikSementaraByPeganganHakmilik(getParam("txtPeganganHakmilik"));
					if (idHakmilikAgensi.isEmpty()) {
					this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
					}
				}
			}			
			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));
			
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

			String idStatusC = getParam("socStatusC");
			if (idStatusC == null || idStatusC.trim().length() == 0) {
				idStatusC = "99999";
			}
			String flagDetail = getParam("flagDetail");
			
			//FILTER BY NEGERI
			if (idNegeriUser != null && idNegeriUser.length() > 0){
				if (!"16".equals(idNegeriUser)){
					idNegeriC = idNegeriUser;
				}
			}

			// GO TO LIST FAIL PENYEWAAN
			vm = "app/php2/frmPYWSenaraiFail.jsp";

			logic.carianFail(getParam("txtNoFail"), getParam("txtNoFailNegeri"), getParam("txtPemohon"), getParam("txtNoPengenalan"),
					getParam("txdTarikhTerima"), idNegeriC, idDaerahC,
					idMukimC, idJenisHakmilikC, getParam("txtNoHakmilik"),
					getParam("txtNoWarta"), idLotC, getParam("txtNoLot"),
					getParam("txtNoPegangan"), idStatusC, userId, idNegeriUser, userRole);
			
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);

			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtNoFailNegeri", getParam("txtNoFailNegeri"));
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
			this.context.put("selectStatus", HTML.SelectStatusPenyewaan("socStatusC", Long.parseLong(idStatusC), "", ""));

			this.context.put("flagDetail", flagDetail);
			setupPage(session, action, list);
		}

		// SET DEFAULT PARAM
		this.context.put("actionPenyewaan", actionPenyewaan);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idKategoriPemohon", idKategoriPemohon);
		this.context.put("idNegeri", idNegeri);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		this.context.put("idPPTBorangK", idPPTBorangK);
		this.context.put("idHakmilikUrusan", idHakmilikUrusan);
		this.context.put("idPHPBorangK", idPHPBorangK);
		this.context.put("idHakmilikSementara", idHakmilikSementara);
		this.context.put("idUrusan", idUrusan);
		this.context.put("idSuburusan", idSuburusan);
		this.context.put("idJenisTujuan", idJenisTujuan);
		this.context.put("idJenisTujuan2", idJenisTujuan2);
		this.context.put("idJenisTujuan3", idJenisTujuan3);
		this.context.put("namatujuan", logic.getNamaTujuan(idJenisTujuan));
		this.context.put("namatujuan2", logic.getNamaTujuan(idJenisTujuan2));
		this.context.put("namatujuan3", logic.getNamaTujuan(idJenisTujuan3));
		
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
