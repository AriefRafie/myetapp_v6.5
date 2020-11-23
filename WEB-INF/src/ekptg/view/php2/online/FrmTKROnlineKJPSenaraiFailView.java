
package ekptg.view.php2.online;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.php2.online.FrmPNWOnlineKJPSenaraiFailData;
import ekptg.model.php2.online.FrmTKRHeaderData;
import ekptg.model.php2.FrmTKRJabatanTeknikalData;
import ekptg.model.php2.online.FrmTKROnlineKJPSenaraiFailData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserKJPBean;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.lampiran.ILampiran;
import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

//test 13/8/2020
public class FrmTKROnlineKJPSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private String readonly = "disabled class = \"disabled\"";
	private IOnline iOnline = null;
	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;
	private IHtp iHTP = null;
	private ekptg.model.utils.emel.IEmel emelSemak = null;
	private IUserPegawai iUser = null;
	private String idSuburusan = "33";
	private ILampiran iLampiran = null;
	String idJawatan = "";
	private String templateDir = "app/php2/online/ulasanKJP/tkr";

	FrmTKRHeaderData logicHeader = new FrmTKRHeaderData();
	FrmTKRJabatanTeknikalData logicJabatanTeknikal = new FrmTKRJabatanTeknikalData();
	FrmTKROnlineKJPSenaraiFailData logic = new FrmTKROnlineKJPSenaraiFailData();
	static Logger myLog = Logger.getLogger(FrmTKROnlineKJPSenaraiFailView.class);

	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		String userId = (String) session.getAttribute("_ekptg_user_id");

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String submit = getParam("command");
		String submit2 = getParam("submit2");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }

        String userRole = "";
		String userJawatan = "";
		String layerKJP = "";
		String idNegeriPemohon = "";
		String nama = "";
		String namaPemohon = getParam("namaPemohon");
		String kadPengenalanPemohon = getParam("kadPengenalanPemohon");
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";
		String negeri = "";
		String perkara = "";
		String keterangan = "";
		String noFail = "";

		myLog.info("submit="+submit);
		myLog.info("submit2="+submit2);
		String hitButton = getParam("hitButton");
		myLog.info("hitButton="+hitButton);


		// GET ID PARAM
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
		this.context.put("idFail", idFail);
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idHakmilikSementara = null;
		idHakmilikSementara = getParam("idHakmilikSementara");
		String idHakmilik = getParam("idHakmilik");
		String idDokumen = getParam("idDokumen"); // ADD MAKLUMAT LAMPIRAN
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idPermohonan = getParam("idPermohonan");
		String kategori = getParam("kategori");
		String langkah = getParam("langkah");

		String idKategoriPemohon = "";
		String idJenisTanah = "1";
		String namaJenisTanah = "TANAH MILIK PERSEKUTUAN";
		String namaKementerian = "";
		String namaAgensi = "";

		// VECTOR
		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatPemohon = null;
		Vector beanMaklumatAgensi = null;
		Vector beanMaklumatPejabat = null;
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatBorangK = null;
		Vector beanMaklumatLampiran = null;
		Vector listDetailKJP = null;
		Vector senaraiSemak = null;
		Vector senaraiLampiran = null;
		Vector beanMaklumatTukarguna = null;
		Vector beanHeader = null;

		String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String idUrusan = getParam("socUrusan");
		if (idUrusan == null || idUrusan.trim().length() == 0) {
			idUrusan = "99999";
		}

		String idSubsuburusan = getParam("socSubsuburusan");
		if (idSubsuburusan == null || idSubsuburusan.trim().length() == 0) {
			idSubsuburusan = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		myLog.info("idAgensi "+idAgensi);
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
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}

		this.context.put("command", submit);
		this.context.put("templateDir", templateDir);
		this.context.put("errorPeganganHakmilik", "");

		Hashtable hUser = getIUser().getPengguna(userId);
		userRole = logic.getUserRole(userId);
		userJawatan = logic.getUserJawatan(userId);
		userJawatan = String.valueOf(hUser.get("idJawatan"));
		idJawatan = userJawatan;
		idKementerian =  String.valueOf(hUser.get("idKementerian"));

		if ("24".equals(userJawatan)) {
			layerKJP = "1";
		} else if ("9".equals(userJawatan)) {
			layerKJP = "2";
		} else if ("4".equals(userJawatan)) {
			layerKJP = "3";
		} else {
			layerKJP = "1";
		}

		this.context.put("userRole", userRole);
		this.context.put("userJawatan", userJawatan);
		this.context.put("layerKJP", layerKJP);

		listDetailKJP = logic.getIdNegeriKJPByUserId(userId);
		if (!listDetailKJP.isEmpty() && listDetailKJP.size() > 0) {
			myLog.info("listDetailKJP >>>> "+listDetailKJP);
			Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
			idNegeriPemohon = hashRayuanDB.get("idNegeri").toString();
			idKementerian = hashRayuanDB.get("idKementerian").toString();
			idAgensi = hashRayuanDB.get("idAgensi").toString();
			myLog.info("JAWATAN="+userJawatan);
			myLog.info("IDKEMENTERIAN="+hashRayuanDB.get("idKementerian").toString());

		}
		userJawatan = String.valueOf(hUser.get("userJawatan"));
		context.put("idjawatan", idJawatan);

		//this.context.put("idNegeriPemohon", idNegeriPemohon);
		this.context.put("idKementerian", idKementerian);
		this.context.put("idAgensi", idAgensi);
		this.context.put("onload", "");
		this.context.put("completed", false);

		// DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		FrmSemakan semak = null;

		myLog.info("idSuburusan==="+idSuburusan);
		myLog.info("hitButton==="+hitButton);
		myLog.info("idHakmilikAgensi==="+idHakmilikAgensi);
		myLog.info("idHakmilik==="+idHakmilik);
		// HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idUrusan,idSuburusan,idSubsuburusan,idJenisTanah, getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtNoRujukanSurat"), getParam("txtPerkara"), "3", idKementerian, idAgensi,
						idHakmilikAgensi, idLuasKegunaan,
						getParam("txtTujuanKegunaan"), getParam("idKementerianTanah"), getParam("idNegeriTanah"),
						getParam("idLuasTanah"), getParam("luasTanah"), idHakmilikSementara, session);

			}
			if ("doSimpanKemaskiniMaklumatTnh".equals(hitButton)) {
				logic.updateTanah(idPermohonan, idHakmilikAgensi, idHakmilik, idFail,session);
			}
			if ("doSimpanKemaskiniMaklumatPermohonan".equals(hitButton)) {
				logic.updateMaklumatPermohonan(idFail,idPermohonan,getParam("tarikhTerima"), getParam("tarikhSurat"),
						idLuasKegunaan,getParam("txtTujuanKegunaan"),getParam("idLuasTanah"), getParam("luasTanah"),
						getParam("txtLuasMohon1"),getParam("txtBakiLuas"),idLuas,getParam("txtLuasBersamaan"));
			}
			if("doSimpanSenaraiSemak".equals(hitButton)){
				logic.simpanKemaskiniLampiran(idDokumen, getParam("txtNamaLampiran"), getParam("txtCatatanLampiran"),
						session);
			}
			//SENARAI SEMAK
			if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
				myLog.info("id Permohonan ros senarai semaka 2>>>> "+idPermohonan);
	        		String cbsemaks [] = this.request.getParameterValues("idsSenaraiSemak");
	    			//logic.updateSenaraiSemak(idPermohonan,semaks,session);

	        		//String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	    			FrmSemakan frmSemak = new FrmSemakan();
	    			frmSemak.semakanHapusByPermohonan(idPermohonan);
	    			if (cbsemaks != null) {
	    				for (int i = 0; i < cbsemaks.length; i++) {
	    					FrmSemakan.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
	    				}
	    			}
        	}
			if ("doHantarEmel".equals(hitButton)){

				if (logic.getBeanMaklumatPermohonan().size() != 0){
					Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
					idUrusan = (String) hashPermohonan.get("idUrusan");
					idSuburusan = (String) hashPermohonan.get("idSuburusan");
					idSubsuburusan = (String) hashPermohonan.get("idTujuan");
					idPermohonan= (String) hashPermohonan.get("idPermohonan");
				}
				if (logic.checkMaklumatPywLengkap(idPermohonan)){
    				this.context.put("onload", " \"alert('Masih terdapat maklumat tukar guna yang belum lengkap.')\"");
				} else {
					logic.updatePermohonanEmel(idFail,idPermohonan,session);
				}
			}
		}

		//this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session));
		this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session, "phptkr"));


		myLog.info("submit="+submit);
		myLog.info("submit2="+submit2);
		myLog.info("hitButton="+hitButton);
		this.context.put("errorPeganganHakmilik", "");

		try {

			if ("refreshDokumenMuatNaik".equals(submit)) {

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);

				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);

				vm = "/maklumatUlasan.jsp";

			} else if ("muatNaikDokumen".equals(submit)) {

				logic.hapusDokumen(idUlasanTeknikal);
				uploadFiles(idUlasanTeknikal, session);

				vm = "/refreshDokumenMuatNaik.jsp";

			} else if ("hantarUlasan".equals(submit)) {
				context.remove("flagStatus");

				//String userId = (String) session.getAttribute("_ekptg_user_id");
				String txtTarikhSurat = getParam("txtTarikhSurat");
				String txtNoRujukanSurat = getParam("txtNoRujukanSurat");
				String txtUlasan = getParam("txtUlasan");
				String txtKeputusan = getParam("txtKeputusan");
				String txtNamaPengulas = getParam("txtNamaPengulas");
				String txtNoTelPengulas = getParam("txtNoTelPengulas");

				String flagStatus = logic.hantarUlasan(idUlasanTeknikal, txtTarikhSurat, txtNoRujukanSurat, txtUlasan, txtKeputusan,
						txtNamaPengulas, txtNoTelPengulas, userId);
				this.context.put("flagStatus", flagStatus);

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);

				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);

				vm = "/maklumatUlasan.jsp";

			} else if ("simpanUlasan".equals(submit)) {
				context.remove("flagStatus");

				//String userId = (String) session.getAttribute("_ekptg_user_id");
				String txtTarikhSurat = getParam("txtTarikhSurat");
				String txtNoRujukanSurat = getParam("txtNoRujukanSurat");
				String txtUlasan = getParam("txtUlasan");
				String txtKeputusan = getParam("txtKeputusan");
				String txtNamaPengulas = getParam("txtNamaPengulas");
				String txtNoTelPengulas = getParam("txtNoTelPengulas");

				String flagStatus = logic.simpanUlasan(idUlasanTeknikal, txtTarikhSurat, txtNoRujukanSurat, txtUlasan, txtKeputusan,
						txtNamaPengulas, txtNoTelPengulas, userId);
				this.context.put("flagStatus", flagStatus);

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);

				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);

				vm = "/maklumatUlasan.jsp";

			} else if ("papar".equals(submit2)) {
				vm = "/frmTKRKJPDaftarManual.jsp";
				myLog.info("papar=================");
				myLog.info("idFail========="+idFail);

				this.context.put("mode", "view");
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");

				// HEADER
				beanHeader = new Vector();
				logic.setMaklumatHeader(idFail);
				beanHeader = logic.getBeanMaklumatHeader();
				this.context.put("BeanHeader", beanHeader);

				if (beanHeader.size() != 0) {
					Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatHeader().get(0);
					//idFail = (String) hashHeader.get("idFail");
					idPermohonan = (String) hashHeader.get("idPermohonan");
					idStatus = (String) hashHeader.get("idStatus");
					idHakmilikAgensi = (String) hashHeader.get("idHakmilikAgensi");
					idNegeriPemohon = (String) hashHeader.get("idNegeriPemohon");
					nama = (String) hashHeader.get("namaKementerian");
					namaAgensi = (String) hashHeader.get("namaAgensi");
					alamat1 = (String) hashHeader.get("alamat1");
					alamat2 = (String) hashHeader.get("alamat2");
					alamat3 = (String) hashHeader.get("alamat3");
					poskod = (String) hashHeader.get("poskod");
					negeri = (String) hashHeader.get("negeri");
					perkara = (String) hashHeader.get("perkara");
					idKementerian = (String) hashHeader.get("idKementerian");
					idAgensi = (String) hashHeader.get("idAgensi");
					keterangan = (String) hashHeader.get("keterangan");
					this.context.put("namaKementerian", nama);
					this.context.put("namaAgensi", namaAgensi);
					this.context.put("alamat1", alamat1);
					this.context.put("alamat2", alamat2);
					this.context.put("alamat3", alamat3);
					this.context.put("poskod", poskod);
					this.context.put("negeri", negeri);
					this.context.put("perkara", perkara);
					this.context.put("idKementerian", idKementerian);
					this.context.put("idAgensi", idAgensi);
					this.context.put("keterangan", keterangan);
				}

				// MAKLUMAT PERMOHONAN
				beanMaklumatPermohonan = new Vector();
				logic.setMaklumatPermohonan(idFail);
				beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
				if (beanMaklumatPermohonan.size() != 0){
	    			Hashtable hashMaklumatPelepasan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
	    			idLuasKegunaan = (String) hashMaklumatPelepasan.get("flagGuna");
				}
				this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));
				this.context.put("selectNegeri",HTML.SelectNegeri ("socNegeri", Long.parseLong(idNegeri), "disabled", "class=\"disabled\" style=\"width:auto\""));

				// MAKLUMAT PEMOHON
				logic.setMaklumatPemohon(idFail);
				if (logic.getBeanMaklumatPemohon().size() != 0){
					Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
					idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
					idPejabat = (String) hashPemohon.get("idPejabat");
					//idKementerian = (String) hashPemohon.get("idKementerian");
					//idAgensi = (String) hashPemohon.get("idAgensi");
				}
				idKategoriPemohon = logic.getKategoriPemohonTukarguna();

				//MAKLUMAT KEMENTERIAN/ AGENSI
				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("BeanMaklumatAgensi",beanMaklumatAgensi);

				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
				this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));

				// MAKLUMAT KEGUNAAN TANAH
				beanMaklumatTanah = new Vector();
				logic.setMaklumatHakmilik(logic.getIdHakmilikPermohonanByIdFail(idFail));
				beanMaklumatTanah = logic.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				this.context.put("selectNegeri",HTML.SelectNegeri ("socNegeri", Long.parseLong(idNegeri), "disabled", " class=\"disabled\" style=\"width:auto\""));

				//vm = "/start.jsp";

			}  else if ("kembali".equals(submit2)){

				myLog.info("masuk else");
				//String userId = (String) session.getAttribute("_ekptg_user_id");
				Vector listFail = logic.getSenaraiFail(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, userId);
				this.context.put("SenaraiFail", listFail);
				setupPage(session, action, listFail);

				context.remove("findNoFail");
				context.remove("findNoPermohonan");
				context.remove("findTajukFail");
				context.remove("findPemohon");
				context.remove("findNoPengenalan");
				context.remove("findTarikhTerima");
				context.remove("findNoHakmilik");
				context.remove("findNoWarta");
				context.remove("findNoPegangan");
				context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("findJenisHakmilik",Long.parseLong("9999"), "", ""));
				context.put("selectLot", HTML.SelectLot("findJenisLot",Long.parseLong("9999"), "", ""));
				context.remove("findNoLot");
				context.put("selectNegeri", HTML.SelectNegeri("findNegeri",Long.parseLong("9999"), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectDaerah", HTML.SelectDaerahByIdNegeri("9999", "findDaerah", Long.parseLong("9999"), ""," onChange=\"doChangeDaerah();\""));
				context.put("selectMukim", HTML.SelectMukimByDaerah("9999", "findMukim", Long.parseLong("9999"), "",""));

				context.put("namaJenisTanah", namaJenisTanah);
				context.put("idJenisTanah", idJenisTanah);
				myLog.info("masuk else idFail "+idFail);
				myLog.info("masuk else idUlasanTeknikal "+idUlasanTeknikal);
				// SET DEFAULT ID PARAM
				this.context.put("idFail", idFail);
				this.context.put("idStatus", idStatus);
				this.context.put("idKategoriPemohon", idKategoriPemohon);
				this.context.put("idAgensi", idAgensi);
				//this.context.put("actionTukarguna", actionTukarguna);


				//this.context.put("idPPTBorangK", idPPTBorangK);
				//this.context.put("idHakmilikUrusan", idHakmilikUrusan);
				//this.context.put("idPHPBorangK", idPHPBorangK);

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				//Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);

				Vector maklumatLampiran = null;
				maklumatLampiran = new Vector();
				logicJabatanTeknikal.setLampiranKJP(logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				maklumatLampiran = logicJabatanTeknikal.getBeanMaklumatLampiranKJP();

				//this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				//this.context.put("idFail", idFail);
				this.context.put("maklumatLampiran", maklumatLampiran);

				vm = "/start.jsp";

			}else if ("seterusnya".equals(submit2)) {
				myLog.info("baca seterusnya");
				myLog.info("idFail=========" + idFail);
				// GO TO MAKLUMAT PERMOHONAN
				vm = "/frmTKRKJPMaklumatPermohonan.jsp";

				// this.context.put("mode", "view");
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");

				// HEADER
				beanHeader = new Vector();
				logic.setMaklumatHeader(idFail);
				beanHeader = logic.getBeanMaklumatHeader();
				this.context.put("BeanHeader", beanHeader);

				if (beanHeader.size() != 0) {
					Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatHeader().get(0);
					//idFail = (String) hashHeader.get("idFail");
					idPermohonan = (String) hashHeader.get("idPermohonan");
					idStatus = (String) hashHeader.get("idStatus");
					idHakmilikAgensi = (String) hashHeader.get("idHakmilikAgensi");
					idNegeriPemohon = (String) hashHeader.get("idNegeriPemohon");
					nama = (String) hashHeader.get("namaKementerian");
					namaAgensi = (String) hashHeader.get("namaAgensi");
					alamat1 = (String) hashHeader.get("alamat1");
					alamat2 = (String) hashHeader.get("alamat2");
					alamat3 = (String) hashHeader.get("alamat3");
					poskod = (String) hashHeader.get("poskod");
					negeri = (String) hashHeader.get("negeri");
					perkara = (String) hashHeader.get("perkara");
					idKementerian = (String) hashHeader.get("idKementerian");
					idAgensi = (String) hashHeader.get("idAgensi");
					this.context.put("namaKementerian", nama);
					this.context.put("namaAgensi", namaAgensi);
					this.context.put("alamat1", alamat1);
					this.context.put("alamat2", alamat2);
					this.context.put("alamat3", alamat3);
					this.context.put("poskod", poskod);
					this.context.put("negeri", negeri);
					this.context.put("perkara", perkara);
					this.context.put("idKementerian", idKementerian);
					this.context.put("idAgensi", idAgensi);
				}


				// MAKLUMAT PEMOHON
				logic.setMaklumatPemohon(idFail);
				beanMaklumatPemohon = new Vector();
				if (logic.getBeanMaklumatPemohon().size() != 0) {
					Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
					idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
					idPejabat = (String) hashPemohon.get("idPejabat");
					//idKementerian = (String) hashPemohon.get("idKementerian");
					//idAgensi = (String) hashPemohon.get("idAgensi");
					this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);

				}

				// MODE VIEW
				if ("view".equals(mode)) {
					myLog.info("baca mode view");
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");

					// MAKLUMAT PERMOHONAN
					beanMaklumatPermohonan = new Vector();
					logic.setMaklumatPermohonan(idFail);
					beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
					this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

					idKategoriPemohon = logic.getKategoriPemohonTukarguna();
					this.context.put("idKategoriPemohon", idKategoriPemohon);
					this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",
							Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian,
							Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));

					// MAKLUMAT KEMENTERIAN/ AGENSI
					beanMaklumatAgensi = new Vector();
					logic.setMaklumatAgensi(idAgensi);
					Hashtable hashAgensi = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
					beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
					this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
					namaKementerian = (String) hashAgensi.get("kementerian");

					this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",
							Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
					this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian,
							Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
					this.context.put("kementerian", namaKementerian);

					// MAKLUMAT TANAH
					beanMaklumatTanah = new Vector();
					logic.setMaklumatHakmilik(logic.getIdHakmilikPermohonanByIdFail(idFail));
					beanMaklumatTanah = logic.getBeanMaklumatHakmilik();
					this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
					if (beanMaklumatTanah.size() != 0){
		    			Hashtable hashMaklumatTanah = (Hashtable) logic.getBeanMaklumatHakmilik().get(0);
		    			idNegeri = (String) hashMaklumatTanah.get("idNegeri");
		    		}



					if (logic.getBeanMaklumatPermohonan().size() != 0){
						Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
						idUrusan = (String) hashPermohonan.get("idUrusan");
						idSuburusan = (String) hashPermohonan.get("idSuburusan");
						idSubsuburusan = (String) hashPermohonan.get("idTujuan");
						idPermohonan= (String) hashPermohonan.get("idPermohonan");
					}

					// MAKLUMAT KEGUNAAN TANAH
					beanMaklumatTukarguna = new Vector();
					logic.setMaklumatTukarguna(idPermohonan);

					myLog.info("masuk sini tak lepas error nie");
					beanMaklumatTukarguna = logic.getBeanMaklumatTukarguna();
					this.context.put("beanMaklumatTukarguna", beanMaklumatTukarguna);

					if (beanMaklumatTukarguna.size() != 0){
		    			Hashtable hashMaklumatTukarguna = (Hashtable) logic.getBeanMaklumatTukarguna().get(0);
		        		if (hashMaklumatTukarguna.get("flagGuna") != null && hashMaklumatTukarguna.get("flagGuna").toString().trim().length() != 0){
		        			idLuasKegunaan = (String) hashMaklumatTukarguna.get("flagGuna");
		        		} else {
		        			idLuasKegunaan = "99999";
		        		}
		        		if (hashMaklumatTukarguna.get("idLuasMohon") != null && hashMaklumatTukarguna.get("idLuasMohon").toString().trim().length() != 0){
		        			idLuas = (String) hashMaklumatTukarguna.get("idLuasMohon");
		        		} else {
		        			idLuas = "99999";

		        		}
		    		}
					this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\""));
					this.context.put("selectNegeri",HTML.SelectNegeri ("socNegeri", Long.parseLong(idNegeri), "disabled", " class=\"disabled\" style=\"width:auto\""));
					myLog.info("idLuas >>> "+idLuas);
					if ("1".equals(idLuas)) {
						this.context.put("selected", "");
						this.context.put("selectedL1", "selected");
						this.context.put("selectedL2", "");
						this.context.put("selectedL3", "");
						this.context.put("selectedL4", "");
						this.context.put("selectedL5", "");
						this.context.put("selectedL6", "");
						this.context.put("selectedL7", "");
						this.context.put("selectedL8", "");
						this.context.put("selectedL9", "");
						this.context.put("idLuas", idLuas);
			    	} else if ("2".equals(idLuas)) {
						this.context.put("selected", "");
						this.context.put("selectedL1", "");
						this.context.put("selectedL2", "selected");
						this.context.put("selectedL3", "");
						this.context.put("selectedL4", "");
						this.context.put("selectedL5", "");
						this.context.put("selectedL6", "");
						this.context.put("selectedL7", "");
						this.context.put("selectedL8", "");
						this.context.put("selectedL9", "");
						this.context.put("idLuas", idLuas);
			    	} else if ("3".equals(idLuas)) {
						this.context.put("selected", "");
						this.context.put("selectedL1", "");
						this.context.put("selectedL2", "");
						this.context.put("selectedL3", "selected");
						this.context.put("selectedL4", "");
						this.context.put("selectedL5", "");
						this.context.put("selectedL6", "");
						this.context.put("selectedL7", "");
						this.context.put("selectedL8", "");
						this.context.put("selectedL9", "");
						this.context.put("idLuas", idLuas);
			    	}  else if ("4".equals(idLuas)) {
						this.context.put("selected", "");
						this.context.put("selectedL1", "");
						this.context.put("selectedL2", "");
						this.context.put("selectedL3", "");
						this.context.put("selectedL4", "selected");
						this.context.put("selectedL5", "");
						this.context.put("selectedL6", "");
						this.context.put("selectedL7", "");
						this.context.put("selectedL8", "");
						this.context.put("selectedL9", "");
						this.context.put("idLuas", idLuas);
			    	} else if ("5".equals(idLuas)) {
						this.context.put("selected", "");
						this.context.put("selectedL1", "");
						this.context.put("selectedL2", "");
						this.context.put("selectedL3", "");
						this.context.put("selectedL4", "");
						this.context.put("selectedL5", "selected");
						this.context.put("selectedL6", "");
						this.context.put("selectedL7", "");
						this.context.put("selectedL8", "");
						this.context.put("selectedL9", "");
						this.context.put("idLuas", idLuas);
			    	} else if ("6".equals(idLuas)) {
						this.context.put("selected", "");
						this.context.put("selectedL1", "");
						this.context.put("selectedL2", "");
						this.context.put("selectedL3", "");
						this.context.put("selectedL4", "");
						this.context.put("selectedL5", "");
						this.context.put("selectedL6", "selected");
						this.context.put("selectedL7", "");
						this.context.put("selectedL8", "");
						this.context.put("selectedL9", "");
						this.context.put("idLuas", idLuas);
			    	} else if ("7".equals(idLuas)) {
						this.context.put("selected", "");
						this.context.put("selectedL1", "");
						this.context.put("selectedL2", "");
						this.context.put("selectedL3", "");
						this.context.put("selectedL4", "");
						this.context.put("selectedL5", "");
						this.context.put("selectedL6", "");
						this.context.put("selectedL7", "selected");
						this.context.put("selectedL8", "");
						this.context.put("selectedL9", "");
						this.context.put("idLuas", idLuas);
			    	} else if ("8".equals(idLuas)) {
						this.context.put("selected", "");
						this.context.put("selectedL1", "");
						this.context.put("selectedL2", "");
						this.context.put("selectedL3", "");
						this.context.put("selectedL4", "");
						this.context.put("selectedL5", "");
						this.context.put("selectedL6", "");
						this.context.put("selectedL7", "");
						this.context.put("selectedL8", "selected");
						this.context.put("selectedL9", "");
						this.context.put("idLuas", idLuas);
			    	} else if ("9".equals(idLuas)) {
						this.context.put("selected", "");
						this.context.put("selectedL1", "");
						this.context.put("selectedL2", "");
						this.context.put("selectedL3", "");
						this.context.put("selectedL4", "");
						this.context.put("selectedL5", "");
						this.context.put("selectedL6", "");
						this.context.put("selectedL7", "");
						this.context.put("selectedL8", "");
						this.context.put("selectedL9", "selected");
						this.context.put("idLuas", idLuas);
			    	}else {
			    		this.context.put("selected", "selected");
						this.context.put("selectedL1", "");
						this.context.put("selectedL2", "");
						this.context.put("selectedL3", "");
						this.context.put("selectedL4", "");
						this.context.put("selectedL5", "");
						this.context.put("selectedL6", "");
						this.context.put("selectedL7", "");
						this.context.put("selectedL8", "");
						this.context.put("selectedL9", "");
						this.context.put("idLuas", "0");
			    	}

					// SET DEFAULT ID PARAM
					this.context.put("idFail", idFail);
					this.context.put("idStatus", idStatus);
					this.context.put("idNegeriPemohon", idNegeriPemohon);
					this.context.put("idHakmilikAgensi", idHakmilikAgensi);

					logic.getIdNegeriKJPByUserId(userId);
	    			Hashtable hashList = (Hashtable) listDetailKJP.get(0);
	    			listDetailKJP = logic.getIdNegeriKJPByUserId(userId);
	    			idNegeriPemohon = hashList.get("idNegeri").toString();
	    			namaPemohon = (String) hashList.get("namaPemohon");
	    			kadPengenalanPemohon = (String) hashList.get("kadPengenalanPemohon");
	    			userId = (String) hashList.get("userId");

	    			this.context.put("namaPemohon", namaPemohon);
	    			this.context.put("kadPengenalanPemohon", kadPengenalanPemohon);

					//senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
					semak = new FrmSemakan();
					semak.mode = mode;
					senaraiSemak = semak.getSenaraiSemakanAttach("phptukar",idPermohonan);
	    			this.context.put("SenaraiSemak", senaraiSemak);
	    			this.context.put("mode", mode);


	    			myLog.info("senaraiSemak ="+senaraiSemak);

					// POPUP LAMPIRAN
					/*if ("3".equals(selectedTabUpper)) {
						myLog.info("baca popup selectedTabUpper 3=====");

						if ("openPopupLampiran".equals(flagPopup)) {
							myLog.info("baca openPopupLampiran flagPopup=====");

							if ("new".equals(modePopup)) {
								myLog.info("baca popup lampiran new=====");

								this.context.put("readonlyPopup", "");
								this.context.put("inputTextClassPopup", "");

								beanMaklumatLampiran = new Vector();
								Hashtable hashMaklumatLampiran = new Hashtable();
								hashMaklumatLampiran.put("namaLampiran", "");
								hashMaklumatLampiran.put("catatanLampiran", "");
								beanMaklumatLampiran.addElement(hashMaklumatLampiran);
								this.context.put("BeanMaklumatLampiran", beanMaklumatLampiran);

							} else if ("update".equals(modePopup)) {
								myLog.info("baca popup lampiran update=====");

								this.context.put("readonlyPopup", "");
								this.context.put("inputTextClassPopup", "");

								// MAKLUMAT LAMPIRAN
								beanMaklumatLampiran = new Vector();
								logic.setMaklumatLampiran(idDokumen);
								beanMaklumatLampiran = logic.getBeanMaklumatLampiran();
								this.context.put("BeanMaklumatLampiran", beanMaklumatLampiran);

							} else if ("view".equals(modePopup)) {

								this.context.put("readonlyPopup", "readonly");
								this.context.put("inputTextClassPopup", "disabled");

								// MAKLUMAT LAMPIRAN
								beanMaklumatLampiran = new Vector();
								logic.setMaklumatLampiran(idDokumen);
								beanMaklumatLampiran = logic.getBeanMaklumatLampiran();
								this.context.put("BeanMaklumatLampiran", beanMaklumatLampiran);
							}
							this.context.put("mode", mode);
							this.context.put("selectedTabUpper", selectedTabUpper);
							this.context.put("flagPopup", flagPopup);
							this.context.put("modePopup", modePopup);
						}

					}*/
					//SENARAI LAMPIRAN
	    			senaraiLampiran = new Vector();
	    			//logic.setSenaraiLampiran(idPermohonan);
	    			//senaraiLampiran = logic.getListLampiran();
	    			this.context.put("SenaraiLampiran", senaraiLampiran);

	    			// tambah untuk langkah
	    			String semakMode = "";
	    			String statusSemasa = "1";


	    			if (getIOnline().isHantar(Long.parseLong(String.valueOf(33)),
	    					Long.parseLong(String.valueOf(idPermohonan)),
	    					Long.parseLong(String.valueOf(idFail)), "4")) {
	    				semakMode = "xupdate";
	    			} else {

	    				//myLog.info("idPermohonan::" + logic.getBeanMaklumatHeader().get(0));
	    				semakMode = "update";

	    				if (getIOnline().isHantarAktif(Long.parseLong(String.valueOf(33)),
	    						Long.parseLong(String.valueOf(idPermohonan)),
	    						Long.parseLong(String.valueOf(idFail)), "1")) {
	    					statusSemasa = "1";

	    				} else if (getIOnline().isHantarAktif(Long.parseLong(String.valueOf(33)),
	    						Long.parseLong(String.valueOf(idPermohonan)),
	    						Long.parseLong(String.valueOf(idFail)), "2")) {
	    					statusSemasa = "2";

	    				} else if (getIOnline().isHantarAktif(Long.parseLong(String.valueOf(33)),
	    						Long.parseLong(String.valueOf(idPermohonan)),
	    						Long.parseLong(String.valueOf(idFail)), "3")) {
	    					statusSemasa = "3";

	    				}
	    			}
	    			myLog.info("semakMode="+semakMode);
	    			context.put("semakMode", semakMode);
	    			myLog.info("statusSemasa:" + statusSemasa);
	    			context.put("statussemasa", statusSemasa);
	    			context.put("idFail", (idFail));
	    			context.put("buttonSend", "disabled");
	    			context.put("idPermohonan", Long.parseLong(String.valueOf(idPermohonan)));

				}
				// MODE UPDATE
				else if ("update".equals(mode)) {
					myLog.info("baca mode update");
					myLog.info("submit >>> "+submit);
					semak = new FrmSemakan();
					semak.mode = mode;
					senaraiSemak = semak.getSenaraiSemakanAttach("phptukar",idPermohonan);
	    			this.context.put("SenaraiSemak", senaraiSemak);
	    			this.context.put("mode", mode);

	    			this.context.put("readonly", "");
	        		this.context.put("inputTextClass", "");
	        		this.context.put("disabled", "");

	    			// MAKLUMAT TANAH
					beanMaklumatTanah = new Vector();
					logic.setMaklumatHakmilik(logic.getIdHakmilikPermohonanByIdFail(idFail));
					beanMaklumatTanah = logic.getBeanMaklumatHakmilik();
					this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
					this.context.put("idPermohonan", idPermohonan);

	    			//MAKLUMAT HAKMILIK
					if ("doChangePeganganHakmilik".equals(submit)) {

						idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtnoLot").trim(), getParam("txtnoHakmilik").trim(),
								getParam("socNegeri"));
							if (idHakmilikAgensi.isEmpty()) {
							this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
							this.context.put("BeanMaklumatTanah", "");
							this.context.put("idHakmilikAgensi", "");
							}else{
								beanMaklumatTanah = new Vector();
								myLog.info("idHakmilikAgensi: "+idHakmilikAgensi+" idHakmilikSementara: "+idHakmilikSementara);
								//logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
								logic.setMaklumatTanah(idHakmilikAgensi,idHakmilikSementara);
								beanMaklumatTanah = logic.getBeanMaklumatTanah();
								this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
								this.context.put("idHakmilikAgensi", idHakmilikAgensi);
							}

					}

					String idNegeri2 = getParam("socNegeri");

		           	if ("doChangeNegeri".equals(submit)){
		           		idNegeri = idNegeri2;
		           	}else{
		           	}

		           	this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", "onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));
		           	this.context.put("selectNegeri",HTML.SelectNegeri ("socNegeri", Long.parseLong(idNegeri), "", "onChange=\"doChangeNegeri()\" style=\"width:auto\""));
		           	beanMaklumatPermohonan = new Vector();
					logic.setMaklumatPermohonan(idFail);
					beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
					String idKegunaan = getParam("socLuasKegunaan");

		           	myLog.info("idLuasKegunaan 2>>> "+idLuasKegunaan);
		           	if ("doChangeLuasKegunaan".equals(submit)){
		           		idLuasKegunaan = idKegunaan;
		           	}else{
		           		if (beanMaklumatPermohonan.size() != 0){
			    			Hashtable hashMaklumatPelepasan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
			    			idLuasKegunaan = (String) hashMaklumatPelepasan.get("flagGuna");
						}
		           	}
		           	myLog.info("idLuasKegunaan 1>>> "+idLuasKegunaan);

		          //MAKLUMAT TUKARGUNA
		        	beanMaklumatTukarguna = new Vector();
		    		logic.setMaklumatTukarguna(idPermohonan);
		    		Hashtable hashMaklumatTukargunaDB = (Hashtable) logic.getBeanMaklumatTukarguna().get(0);
					Hashtable hashMaklumatTukarguna = new Hashtable();
					hashMaklumatTukarguna.put("tujuanLain", getParam("txtTujuanLain"));
					hashMaklumatTukarguna.put("tarikhTerima", getParam("tarikhTerima"));
					hashMaklumatTukarguna.put("tarikhSurat", getParam("tarikhSurat"));
					hashMaklumatTukarguna.put("perkara", getParam("txtPerkara"));
					hashMaklumatTukarguna.put("luasAsal", hashMaklumatTukargunaDB.get("luasAsal"));
					hashMaklumatTukarguna.put("idLuas", hashMaklumatTukargunaDB.get("idLuas"));
					hashMaklumatTukarguna.put("keteranganLuasAsal", hashMaklumatTukargunaDB.get("keteranganLuasAsal"));
					if ("doChangeLuas".equals(submit)){
						myLog.info("masuk x sini do change luas");
						hashMaklumatTukarguna.put("luas1", "");
						hashMaklumatTukarguna.put("luas2", "");
						hashMaklumatTukarguna.put("luas3", "");
						hashMaklumatTukarguna.put("luasBersamaan", "");
						hashMaklumatTukarguna.put("luasBaki", "");
						idLuasKegunaan = idKegunaan;
						myLog.info("idLuas >>>> "+idLuas);
						//this.context.put("socLuas", idLuas);
					} else {
						hashMaklumatTukarguna.put("luas1", getParam("txtLuasMohon1"));
						hashMaklumatTukarguna.put("luas2", getParam("txtLuasMohon2"));
						hashMaklumatTukarguna.put("luas3", getParam("txtLuasMohon3"));
						if ("1".equals(idLuasKegunaan)){
							hashMaklumatTukarguna.put("luasBersamaan",  hashMaklumatTukargunaDB.get("luasAsal"));
							hashMaklumatTukarguna.put("luasBaki", Utils.formatLuas(0D));
						} else {
							hashMaklumatTukarguna.put("luasBersamaan", getParam("txtLuasBersamaan"));
							hashMaklumatTukarguna.put("luasBaki", getParam("txtBakiLuas"));
						}
						idLuasKegunaan = idKegunaan;
					}

					beanMaklumatTukarguna.addElement(hashMaklumatTukarguna);
		           	this.context.put("BeanMaklumatTukarguna", beanMaklumatTukarguna);

					this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
					myLog.info("update beanMaklumatPermohonan >>>> "+beanMaklumatPermohonan);

				}



			}else if ("daftarBaru".equals(submit)) {
				myLog.info("masuk daftar Baru");
				vm = "/frmTKRKJPDaftarManual.jsp";
				this.context.put("mode", "new");
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				myLog.info("idFail========="+idFail);
				if ("doChangeKementerian".equals(submit)){
					idAgensi = "99999";
				}
				if ("doChangeAgensi".equals(submit)){
					idHakmilikAgensi = "";
				}
				if ("doChangeJenisTanah".equals(submit)){
					idHakmilikAgensi = "";
				}

				// MAKLUMAT PERMOHONAN
				beanMaklumatPermohonan = new Vector();
				Hashtable hashPermohonan = new Hashtable();
				hashPermohonan.put("noPermohonan", "");
				hashPermohonan.put("noRujukanOnline", "");
				hashPermohonan.put("tarikhTerima",getParam("tarikhTerima") == null || "".equals(getParam("tarikhTerima"))? sdf.format(currentDate) : getParam("tarikhTerima"));
				hashPermohonan.put("tarikhSurat",getParam("tarikhSurat") == null ? "": getParam("tarikhSurat"));
				hashPermohonan.put("noRujukanSurat",getParam("txtNoRujukanSurat") == null ? "": getParam("txtNoRujukanSurat"));
				hashPermohonan.put("perkara", getParam("txtPerkara") == null ? "": getParam("txtPerkara"));
				hashPermohonan.put("tujuanKegunaan", getParam("txtTujuanKegunaan") == null ? "": getParam("txtTujuanKegunaan"));
				beanMaklumatPermohonan.addElement(hashPermohonan);
				this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

				// MAKLUMAT PEMOHON
				idKategoriPemohon = logic.getKategoriPemohonTukarguna();
				myLog.info("idKategoriPemohonDaftar: " + idKategoriPemohon);

				beanMaklumatAgensi = new Vector();
				logic.setMaklumatAgensi(idAgensi);
				beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
				this.context.put("idKategoriPemohon", idKategoriPemohon);
				this.context.put("idAgensi", idAgensi);
				this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
				this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",
						Long.parseLong(idKementerian), "", readonly+" style=\"width:400\" "));
				this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian,
						Long.parseLong(idAgensi), "", readonly+" style=\"width:400\" "));

				// MAKLUMAT KEGUNAAN TANAH
				this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "", " "));
				this.context.put("selectNegeri",HTML.SelectNegeri ("socNegeri", Long.parseLong(idNegeri), "", " "));

				//MAKLUMAT HAKMILIK
				if ("doChangePeganganHakmilik".equals(submit2)) {
					//idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik").trim());\
					idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtnoLot").trim(), getParam("txtnoHakmilik").trim(),
							getParam("socNegeri"));
					if (idHakmilikAgensi.isEmpty()) {
						this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
					}
				}

				beanMaklumatTanah = new Vector();
				logic.setMaklumatTanah(idHakmilikAgensi,idHakmilikSementara);
				//logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
				beanMaklumatTanah = logic.getBeanMaklumatTanah();
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				this.context.put("idFail", idFail);
				this.context.put("idHakmilikSementara", idHakmilikSementara);
				this.context.put("idHakmilikAgensi", idHakmilikAgensi);


				context.put("namaJenisTanah", namaJenisTanah);
				//vm = "/start.jsp";

			} else if ("paparFail".equals(submit)) {
				myLog.info("bacaaaa paparFail");
				vm = "/paparFail.jsp";
				//TO CLEAR CONTEXT
				context.remove("BeanHeader");
				context.remove("BeanMaklumatTanah");
				context.remove("lampiran");
				context.remove("flagStatus");

				setMaklumatHeader(idFail, session);
				setMaklumatTanah(idFail, session);

				myLog.info("idFail: "+idFail);

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);

				Vector maklumatLampiran = null;
				maklumatLampiran = new Vector();
				logicJabatanTeknikal.setLampiranKJP(logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				maklumatLampiran = logicJabatanTeknikal.getBeanMaklumatLampiranKJP();

				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				this.context.put("idFail", idFail);
				this.context.put("maklumatLampiran", maklumatLampiran);

				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);



			}else if ("carian".equals(submit)) {
				//String userId = (String) session.getAttribute("_ekptg_user_id");
				myLog.info("masuk carian");
				String findNoFail = getParam("findNoFail");
				String findNoRujukanOnline = getParam("findNoRujukanOnline");
				String findNoPermohonan = getParam("findNoPermohonan");
				String findTajukFail = getParam("findTajukFail");
				String findPemohon = getParam("findPemohon");
				String findNoPengenalan = getParam("findNoPengenalan");
				String findTarikhTerima = getParam("findTarikhTerima");
				String findNoHakmilik = getParam("findNoHakmilik");
				String findNoWarta = getParam("findNoWarta");
				String findNoPegangan = getParam("findNoPegangan");
				String findJenisHakmilik = getParam("findJenisHakmilik");
				if(findJenisHakmilik.equals(""))
				myLog.info("findNoFail====="+findNoFail);
				{
					findJenisHakmilik = "9999";
				}
				String findJenisLot = getParam("findJenisLot");
				if(findJenisLot.equals(""))
				{
					findJenisLot = "9999";
				}
				String findNoLot = getParam("findNoLot");

				String findNegeri = getParam("findNegeri");
				if(findNegeri.equals(""))
				{
					findNegeri = "9999";
				}
				String findDaerah = getParam("findDaerah");
				if(findDaerah.equals(""))
				{
					findDaerah = "9999";
				}
				String findMukim = getParam("findMukim");
				if(findMukim.equals(""))
				{
					findMukim = "9999";
				}

				Vector listFail = logic.getSenaraiFail(findNoFail, findNoRujukanOnline, findNoPermohonan, findTajukFail, findPemohon, findNoPengenalan, findTarikhTerima,
						findNoHakmilik, findNoWarta, findNoPegangan, findJenisHakmilik, findJenisLot, findNoLot
						, findNegeri, findDaerah, findMukim
						, userId);
				this.context.put("SenaraiFail", listFail);
				setupPage(session, action, listFail);

				context.put("findNoFail", findNoFail);
				context.put("findNoRujukanOnline", findNoRujukanOnline);
				context.put("findTajukFail", findTajukFail);
				context.put("findPemohon", findPemohon);
				context.put("findNoPengenalan", findNoPengenalan);
				context.put("findTarikhTerima", findTarikhTerima);
				context.put("findNoHakmilik", findNoHakmilik);
				context.put("findNoWarta", findNoWarta);
				context.put("findNoPegangan", findNoPegangan);
				context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("findJenisHakmilik",Long.parseLong(findJenisHakmilik), "", ""));
				context.put("selectLot", HTML.SelectLot("findJenisLot",Long.parseLong(findJenisLot), "", ""));
				context.put("findNoLot", findNoLot);
				context.put("selectNegeri", HTML.SelectNegeri("findNegeri",Long.parseLong(findNegeri), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectDaerah", HTML.SelectDaerahByIdNegeri(findNegeri, "findDaerah", Long.parseLong(findDaerah), ""," onChange=\"doChangeDaerah();\""));
				context.put("selectMukim", HTML.SelectMukimByDaerah(findDaerah, "findMukim", Long.parseLong(findMukim), "",""));

				vm = "/start.jsp";

			} else if ("kembali".equals(submit)) {
				myLog.info("kembali");
				//String userId = (String) session.getAttribute("_ekptg_user_id");
				Vector listFail = logic.getSenaraiFail(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, userId);
				this.context.put("SenaraiFail", listFail);
				setupPage(session, action, listFail);

				context.remove("findNoFail");
				context.remove("findTajukFail");
				context.remove("findPemohon");
				context.remove("findNoPengenalan");
				context.remove("findTarikhTerima");
				context.remove("findNoHakmilik");
				context.remove("findNoWarta");
				context.remove("findNoPegangan");;
				context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("findJenisHakmilik",Long.parseLong("9999"), "", ""));
				context.put("selectLot", HTML.SelectLot("findJenisLot",Long.parseLong("9999"), "", ""));
				context.remove("findNoLot");
				context.put("selectNegeri", HTML.SelectNegeri("findNegeri",Long.parseLong("9999"), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectDaerah", HTML.SelectDaerahByIdNegeri("9999", "findDaerah", Long.parseLong("9999"), ""," onChange=\"doChangeDaerah();\""));
				context.put("selectMukim", HTML.SelectMukimByDaerah("9999", "findMukim", Long.parseLong("9999"), "",""));

				context.put("namaJenisTanah", namaJenisTanah);
				context.put("idJenisTanah", idJenisTanah);

				// SET DEFAULT ID PARAM
				this.context.put("idFail", idFail);
				this.context.put("idStatus", idStatus);
				this.context.put("idKategoriPemohon", idKategoriPemohon);
				this.context.put("idAgensi", idAgensi);
				//this.context.put("actionTukarguna", actionTukarguna);


				//this.context.put("idPPTBorangK", idPPTBorangK);
				//this.context.put("idHakmilikUrusan", idHakmilikUrusan);
				//this.context.put("idPHPBorangK", idPHPBorangK);


				vm = "/start.jsp";

			}else if ("simpanpengesahan2".equals(submit)){

				String semakMode = "";
    			String statusSemasa = "1";

    			if (getIOnline().isHantar(Long.parseLong(String.valueOf(33)),
    					Long.parseLong(String.valueOf(idPermohonan)),
    					Long.parseLong(String.valueOf(idFail)), "4")) {
    				semakMode = "xupdate";
    			} else {

    				if (getIOnline().isHantarAktif(Long.parseLong(String.valueOf(33)),
    						Long.parseLong(String.valueOf(idPermohonan)),
    						Long.parseLong(String.valueOf(idFail)), "1")) {
    					statusSemasa = "1";

    				} else if (getIOnline().isHantarAktif(Long.parseLong(String.valueOf(33)),
    						Long.parseLong(String.valueOf(idPermohonan)),
    						Long.parseLong(String.valueOf(idFail)), "2")) {
    					statusSemasa = "2";

    				} else if (getIOnline().isHantarAktif(Long.parseLong(String.valueOf(33)),
    						Long.parseLong(String.valueOf(idPermohonan)),
    						Long.parseLong(String.valueOf(idFail)), "3")) {
    					statusSemasa = "3";

    				}
    			}
    			myLog.info("semakMode="+semakMode);
    			context.put("semakMode", semakMode);
    			myLog.info("statusSemasa:" + statusSemasa);
    			context.put("statussemasa", statusSemasa);
    			context.put("idFail", (idFail));
    			context.put("buttonSend", "disabled");
    			context.put("idPermohonan", Long.parseLong(String.valueOf(idPermohonan)));

				//myLog.info("simpanpengesahan2 ::idpermohonan=" + logic.getBeanMaklumatHeader().get(0));
				logic.setMaklumatPermohonan(idFail);
				beanHeader = logic.getBeanMaklumatPermohonan();
				//idPermohonan = getParam("idPermohonan");
				Hashtable permohonan2 = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				myLog.info("simpanpengesahan1 ::id_permohonan="+permohonan2);
		    	myLog.info("simpanpengesahan2 ::idpermohonan="+logic.getBeanMaklumatPermohonan().get(0));
				semakMode="";



				/*
				 * -1 untuk status - Pra-daftar
				 * -2 untuk status - Tindakan Penyemak
				 * -3 untuk status - Tindakan Pelulus
				 * -4 untuk status - Permohonan Online (Pengesahan)
				 * -5  untuk status - Penerimaan Permohonan
				*/
				String langkah2 = "2";
				EmailConfig ec = new EmailConfig();

				//myLog.info("from="+email.FROM);
				String emelSubjek = ec.tajukSemakan+"Tukar Guna";
				String kandungan = "";
				if(idJawatan.equals("20")||idJawatan.equals("24")){
					myLog.info("BACA SINIIIII============"+idKementerian);

					this.context.put("idKementerian", idKementerian);

					langkah2 = "2";
					//email remove bler push
					/*

					kandungan = getEmelSemak().setKandungan(String.valueOf(permohonan2.get("tajukfail")), String.valueOf(hUser.get("nama")));

					if(!getEmelSemak().checkEmail(userId).equals(""))
						getIHTP().getErrorHTML("[ONLINE-HTP TUKAR GUNA] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

					ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
							, "9"
							, String.valueOf(String.valueOf(idKementerian))
							, emelSubjek, kandungan);*/

				}else if (idJawatan.equals("9")){
					langkah2 = "3";
					//email remove bler push
					/*

					kandungan = getEmelSemak().setKandungan(String.valueOf(permohonan2.get("tajukfail")), String.valueOf(hUser.get("nama")));

					if(!getEmelSemak().checkEmail(userId).equals(""))
						getIHTP().getErrorHTML("[ONLINE-HTP TUKAR GUNA] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

					ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
							, "4"
							, String.valueOf(String.valueOf(idKementerian))
							, emelSubjek, kandungan);*/

				}else if (idJawatan.equals("4")){
					langkah2 = "4";
					emelSubjek = ec.tajukHantarPermohonan + "Tukar Guna";

					//email remove bler push
					//kandungan = getEmelSemak().setKandungan(String.valueOf(String.valueOf(permohonan2.get("tajukFail")))
					//			,String.valueOf(String.valueOf(permohonan2.get("idKementerian")))
					//			,String.valueOf(String.valueOf(permohonan2.get("noPermohonan"))));

					//if(!getEmelSemak().checkEmail(userId).equals(""))
					//	getIHTP().getErrorHTML("[ONLINE-HTP TUKAR GUNA] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");
					//   (HTP)HQPenggunaPembelianPerletakhakan,   (HTP)HQPenggunaPembelian, (HTP)HQPengguna

					//email remove bler push
					//ec.hantarPermohonan(getEmelSemak().checkEmail(userId), "(HTP)HQPenggunaPembelianPerletakhakan", emelSubjek, kandungan);

				}
				Hashtable permohonan3 = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				//idPermohonan = String.valueOf(permohonan3.get("idPermohonan"));


				Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
				myLog.info("Langkah=="+langkah2+" idSubUrusan=="+idSuburusan);
				long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah2,idSuburusan,"=");
				myLog.info("setIdSuburusanstatus="+setIdSuburusanstatus);
				myLog.info("idPermohonan="+idPermohonan);
				rsusf.setIdPermohonan(Long.parseLong(idPermohonan));
				myLog.info("idFail="+idFail+",idPermohonan="+idPermohonan);
				rsusf.setIdFail(Long.parseLong(idFail));
				rsusf.setIdSuburusanstatusfail(Long.parseLong(idSuburusan));
				rsusf.setIdSuburusanstatus(setIdSuburusanstatus);
				rsusf.setUrl("-");
				rsusf.setIdMasuk(Long.parseLong(userId));
				myLog.info("userId=="+userId);

				simpanPengesahan(rsusf,langkah2,userId);

				long setIdstatus = FrmUtilData.getIdStatusByLangkah (langkah2,idSuburusan,"=");
				FrmUtilData utildata = new FrmUtilData();
				utildata.kemaskiniStatusPermohonan(idPermohonan,String.valueOf(setIdstatus));

//				simpanPengesahan(rsusf,langkah2);


				if(getIOnline().isHantar(Long.parseLong(String.valueOf(33)),
						Long.parseLong(idPermohonan),
						Long.parseLong(idFail),langkah)){
					semakMode = "xupdate";
				}else{
					semakMode = "update";
				}
				myLog.info("selectedTab=======");
				context.put("semakMode", semakMode);
				context.put("statussemasa", langkah2);
				idFail = (String) permohonan2.get("idFail");
				idPermohonan = (String) permohonan2.get("idPermohonan");
				idStatus = (String) permohonan2.get("idStatus");
				this.context.put("BeanHeader", beanHeader);

				Vector listFail = logic.getSenaraiFail(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, userId);
				this.context.put("SenaraiFail", listFail);
				setupPage(session, action, listFail);

				context.remove("findNoFail");
				context.remove("findNoPermohonan");
				context.remove("findTajukFail");
				context.remove("findPemohon");
				context.remove("findNoPengenalan");
				context.remove("findTarikhTerima");
				context.remove("findNoHakmilik");
				context.remove("findNoWarta");
				context.remove("findNoPegangan");
				context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("findJenisHakmilik",Long.parseLong("9999"), "", ""));
				context.put("selectLot", HTML.SelectLot("findJenisLot",Long.parseLong("9999"), "", ""));
				context.remove("findNoLot");
				context.put("selectNegeri", HTML.SelectNegeri("findNegeri",Long.parseLong("9999"), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectDaerah", HTML.SelectDaerahByIdNegeri("9999", "findDaerah", Long.parseLong("9999"), ""," onChange=\"doChangeDaerah();\""));
				context.put("selectMukim", HTML.SelectMukimByDaerah("9999", "findMukim", Long.parseLong("9999"), "",""));

				context.put("namaJenisTanah", namaJenisTanah);
				context.put("idJenisTanah", idJenisTanah);
				myLog.info("hantar idFail "+idFail);
				myLog.info("hantar idUlasanTeknikal "+idUlasanTeknikal);
				// SET DEFAULT ID PARAM
				this.context.put("idFail", idFail);
				this.context.put("idStatus", idStatus);
				this.context.put("idKategoriPemohon", idKategoriPemohon);
				this.context.put("idAgensi", idAgensi);

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));

				Vector maklumatLampiran = null;
				maklumatLampiran = new Vector();
				logicJabatanTeknikal.setLampiranKJP(logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				maklumatLampiran = logicJabatanTeknikal.getBeanMaklumatLampiranKJP();
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				this.context.put("maklumatLampiran", maklumatLampiran);

				vm = "/senaraiFail.jsp";

			}else if ("simpanpengesahan3".equals(submit)){

				String semakMode = "";
    			String statusSemasa = "2";

    			myLog.info("idJawatan >>> "+idJawatan);
    			myLog.info("semakMode="+semakMode);
    			context.put("semakMode", semakMode);
    			myLog.info("statusSemasa:" + statusSemasa);
    			context.put("statussemasa", statusSemasa);
    			context.put("idFail", (idFail));
    			context.put("buttonSend", "disabled");
    			context.put("idPermohonan", Long.parseLong(String.valueOf(idPermohonan)));

				//myLog.info("simpanpengesahan2 ::idpermohonan=" + logic.getBeanMaklumatHeader().get(0));
				logic.setMaklumatPermohonan(idFail);
				beanHeader = logic.getBeanMaklumatPermohonan();
				//idPermohonan = getParam("idPermohonan");
				Hashtable permohonan2 = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				myLog.info("simpanpengesahan1 ::id_permohonan="+permohonan2);
		    	myLog.info("simpanpengesahan2 ::idpermohonan="+logic.getBeanMaklumatPermohonan().get(0));
				semakMode="";



				/*
				 * -1 untuk status - Pra-daftar
				 * -2 untuk status - Tindakan Penyemak
				 * -3 untuk status - Tindakan Pelulus
				 * -4 untuk status - Permohonan Online (Pengesahan)
				 * -5  untuk status - Penerimaan Permohonan
				 * -11 untuk status - Tindakan Penyedia
				*/
				String langkah2 = "11";
				EmailConfig ec = new EmailConfig();

				//myLog.info("from="+email.FROM);
				String emelSubjek = ec.tajukSemakan+"Tukar Guna";
				String kandungan = "";
				if(idJawatan.equals("4")){
					myLog.info("BACA SINIIIII============"+idKementerian);

					this.context.put("idKementerian", idKementerian);

					langkah2 = "11";
					//email remove bler push


					/*kandungan = getEmelSemak().setKandungan(String.valueOf(permohonan2.get("tajukfail")), String.valueOf(hUser.get("nama")));

					if(!getEmelSemak().checkEmail(userId).equals(""))
						getIHTP().getErrorHTML("[ONLINE-HTP TUKAR GUNA] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

					ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
							, "9"
							, String.valueOf(String.valueOf(idKementerian))
							, emelSubjek, kandungan);*/
				}
					/*}else if (idJawatan.equals("9")){
					langkah2 = "3";
					//email remove bler push


					kandungan = getEmelSemak().setKandungan(String.valueOf(permohonan2.get("tajukfail")), String.valueOf(hUser.get("nama")));

					if(!getEmelSemak().checkEmail(userId).equals(""))
						getIHTP().getErrorHTML("[ONLINE-HTP TUKAR GUNA] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

					ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
							, "4"
							, String.valueOf(String.valueOf(idKementerian))
							, emelSubjek, kandungan);

				}else if (idJawatan.equals("4")){
					langkah2 = "4";
					emelSubjek = ec.tajukHantarPermohonan + "Tukar Guna";

					//email remove bler push
					//kandungan = getEmelSemak().setKandungan(String.valueOf(String.valueOf(permohonan2.get("tajukFail")))
					//			,String.valueOf(String.valueOf(permohonan2.get("idKementerian")))
					//			,String.valueOf(String.valueOf(permohonan2.get("noPermohonan"))));

					//if(!getEmelSemak().checkEmail(userId).equals(""))
					//	getIHTP().getErrorHTML("[ONLINE-HTP TUKAR GUNA] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");
					//   (HTP)HQPenggunaPembelianPerletakhakan,   (HTP)HQPenggunaPembelian, (HTP)HQPengguna

					//email remove bler push
					//ec.hantarPermohonan(getEmelSemak().checkEmail(userId), "(HTP)HQPenggunaPembelianPerletakhakan", emelSubjek, kandungan);

				}
				*/
				Hashtable permohonan3 = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				//idPermohonan = String.valueOf(permohonan3.get("idPermohonan"));


				Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
				myLog.info("Langkah=="+langkah2+" idSubUrusan=="+idSuburusan);
				long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah2,idSuburusan,"=");
				myLog.info("setIdSuburusanstatus="+setIdSuburusanstatus);
				myLog.info("idPermohonan="+idPermohonan);
				rsusf.setIdPermohonan(Long.parseLong(idPermohonan));
				myLog.info("idFail="+idFail+",idPermohonan="+idPermohonan);
				rsusf.setIdFail(Long.parseLong(idFail));
				rsusf.setIdSuburusanstatusfail(Long.parseLong(idSuburusan));
				rsusf.setIdSuburusanstatus(setIdSuburusanstatus);
				rsusf.setUrl("-");
				rsusf.setIdMasuk(Long.parseLong(userId));
				myLog.info("userId=="+userId);

				simpanPengesahan(rsusf,langkah2,userId);

				long setIdstatus = FrmUtilData.getIdStatusByLangkah (langkah2,idSuburusan,"=");
				FrmUtilData utildata = new FrmUtilData();
				utildata.kemaskiniStatusPermohonan(idPermohonan,String.valueOf(setIdstatus));

//				simpanPengesahan(rsusf,langkah2);


				if(getIOnline().isHantar(Long.parseLong(String.valueOf(33)),
						Long.parseLong(idPermohonan),
						Long.parseLong(idFail),langkah)){
					semakMode = "xupdate";
				}else{
					semakMode = "update";
				}
				myLog.info("selectedTab=======");
				context.put("semakMode", semakMode);
				context.put("statussemasa", langkah2);
				idFail = (String) permohonan2.get("idFail");
				idPermohonan = (String) permohonan2.get("idPermohonan");
				idStatus = (String) permohonan2.get("idStatus");
				this.context.put("BeanHeader", beanHeader);

				Vector listFail = logic.getSenaraiFail(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, userId);
				this.context.put("SenaraiFail", listFail);
				setupPage(session, action, listFail);

				context.remove("findNoFail");
				context.remove("findNoPermohonan");
				context.remove("findTajukFail");
				context.remove("findPemohon");
				context.remove("findNoPengenalan");
				context.remove("findTarikhTerima");
				context.remove("findNoHakmilik");
				context.remove("findNoWarta");
				context.remove("findNoPegangan");
				context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("findJenisHakmilik",Long.parseLong("9999"), "", ""));
				context.put("selectLot", HTML.SelectLot("findJenisLot",Long.parseLong("9999"), "", ""));
				context.remove("findNoLot");
				context.put("selectNegeri", HTML.SelectNegeri("findNegeri",Long.parseLong("9999"), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectDaerah", HTML.SelectDaerahByIdNegeri("9999", "findDaerah", Long.parseLong("9999"), ""," onChange=\"doChangeDaerah();\""));
				context.put("selectMukim", HTML.SelectMukimByDaerah("9999", "findMukim", Long.parseLong("9999"), "",""));

				context.put("namaJenisTanah", namaJenisTanah);
				context.put("idJenisTanah", idJenisTanah);
				myLog.info("hantar idFail "+idFail);
				myLog.info("hantar idUlasanTeknikal "+idUlasanTeknikal);
				// SET DEFAULT ID PARAM
				this.context.put("idFail", idFail);
				this.context.put("idStatus", idStatus);
				this.context.put("idKategoriPemohon", idKategoriPemohon);
				this.context.put("idAgensi", idAgensi);

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));

				Vector maklumatLampiran = null;
				maklumatLampiran = new Vector();
				logicJabatanTeknikal.setLampiranKJP(logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				maklumatLampiran = logicJabatanTeknikal.getBeanMaklumatLampiranKJP();
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				this.context.put("maklumatLampiran", maklumatLampiran);

				vm = "/senaraiFail.jsp";

			}else {
				myLog.info("masuk+ else");
				//String userId = (String) session.getAttribute("_ekptg_user_id");
				Vector listFail = logic.getSenaraiFail(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, userId);
				this.context.put("SenaraiFail", listFail);
				setupPage(session, action, listFail);

				context.remove("findNoFail");
				context.remove("findNoPermohonan");
				context.remove("findTajukFail");
				context.remove("findPemohon");
				context.remove("findNoPengenalan");
				context.remove("findTarikhTerima");
				context.remove("findNoHakmilik");
				context.remove("findNoWarta");
				context.remove("findNoPegangan");
				context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("findJenisHakmilik",Long.parseLong("9999"), "", ""));
				context.put("selectLot", HTML.SelectLot("findJenisLot",Long.parseLong("9999"), "", ""));
				context.remove("findNoLot");
				context.put("selectNegeri", HTML.SelectNegeri("findNegeri",Long.parseLong("9999"), ""," onChange=\"doChangeNegeri();\""));
				context.put("selectDaerah", HTML.SelectDaerahByIdNegeri("9999", "findDaerah", Long.parseLong("9999"), ""," onChange=\"doChangeDaerah();\""));
				context.put("selectMukim", HTML.SelectMukimByDaerah("9999", "findMukim", Long.parseLong("9999"), "",""));

				context.put("namaJenisTanah", namaJenisTanah);
				context.put("idJenisTanah", idJenisTanah);
				myLog.info("masuk else: idFail= "+idFail);
				myLog.info("masuk else: idUlasanTeknikal= "+idUlasanTeknikal);
				// SET DEFAULT ID PARAM
				this.context.put("idFail", idFail);
				this.context.put("idStatus", idStatus);
				this.context.put("idKategoriPemohon", idKategoriPemohon);
				this.context.put("idAgensi", idAgensi);
				//this.context.put("actionTukarguna", actionTukarguna);


				//this.context.put("idPPTBorangK", idPPTBorangK);
				//this.context.put("idHakmilikUrusan", idHakmilikUrusan);
				//this.context.put("idPHPBorangK", idPHPBorangK);

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				//Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);

				Vector maklumatLampiran = null;
				maklumatLampiran = new Vector();
				logicJabatanTeknikal.setLampiranKJP(logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				maklumatLampiran = logicJabatanTeknikal.getBeanMaklumatLampiranKJP();

				//this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				//this.context.put("idFail", idFail);
				this.context.put("maklumatLampiran", maklumatLampiran);

				vm = "/start.jsp";

			}


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.context.put("idFail", idFail);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("idLuasKegunaan", idLuasKegunaan);
	    this.context.put("idLuas", idLuas);

		return templateDir + vm;

	}

	private void uploadFiles(String idUlasanTeknikal, HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal), session);
				}
			}
		}
	}

	private void saveData(FileItem item, String idUlasanTeknikal, String idPermohonan,
			HttpSession session) {
		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");

		try {
			db = new Db();
			// TBLPHPDOKUMEN
			long idDokumenUpload = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("insert into TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, ID_MASUK, TARIKH_MASUK, CONTENT, JENIS_MIME, NAMA_FAIL, ID_ULASANTEKNIKAL, FLAG_DOKUMEN, ID_PERMOHONAN) "
							+ "values(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumenUpload);
			ps.setString(2, null);
			ps.setString(3, null);
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idUlasanTeknikal);
			ps.setString(9, "L");
			ps.setString(10, idPermohonan);
			ps.executeUpdate();

			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}

		this.context.put("flagStatus", "Y");
		this.context.put("idUlasanTeknikalReload", idUlasanTeknikal);
	}

	private void setMaklumatTanah(String idFail, HttpSession session) throws Exception {
		String flagBorangK = "";
		logicHeader.setMaklumatHakmilik(logicHeader.getIdHakmilikPermohonanByIdFail(idFail));
		if (logicHeader.getBeanMaklumatHakmilik().size() != 0){
			Hashtable hashHakmilik = (Hashtable) logicHeader.getBeanMaklumatHakmilik().get(0);
			flagBorangK = (String) hashHakmilik.get("flagBorangK");
		}
		this.context.put("flagBorangK", flagBorangK);
		myLog.info("flagBorangK >>> "+flagBorangK);

		if ("Y".equals(flagBorangK)){
			Vector beanMaklumatBorangK = new Vector();
			beanMaklumatBorangK = logicHeader.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
		} else {
			Vector beanMaklumatTanah = new Vector();
			beanMaklumatTanah = logicHeader.getBeanMaklumatHakmilik();
			myLog.info("beanMaklumatTanah >>>> "+beanMaklumatTanah);
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
		}
	}

	private void setMaklumatHeader(String idFail, HttpSession session) throws Exception {
		Vector beanHeader = new Vector();
		logicHeader.setMaklumatPermohonan(idFail, session);
		beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
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

	private void simpanPengesahan(Tblrujsuburusanstatusfail rsusf, String langkah2,String userid) throws Exception {
		myLog.info("simpanPengesahan::rsusf >>>> "+rsusf);
		myLog.info("simpanPengesahan::langkah2 >>>> "+langkah2);
		myLog.info("simpanPengesahan::userid >>>> "+userid);
		try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(rsusf.getIdPermohonan());
			subUrusanStatusFail.setIdFail(rsusf.getIdFail());
			subUrusanStatusFail.setAktif("0");

			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah2,
					String.valueOf(rsusf.getIdSuburusanstatusfail()), "=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			subUrusanStatusFailN.setUrl(Utils.isNull(rsusf.getUrl()));
			//subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
			//subUrusanStatusFailN.setIdKemaskini(Long.parseLong(userId));
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userid));
			subUrusanStatusFailN.setIdKemaskini(Long.parseLong(userid));
			getIHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail, subUrusanStatusFailN);

		} catch (Exception e) {
			// throw new Exception("Ralat FrmGadaian[554]:"+e.getCause());
			getIHTP().getErrorHTML(e.toString());

		}
	}

	private ILampiran getDocPHP(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;

	}
	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;

	}
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
//	private IEmel getEmelSemak(){
//		if(emelSemak == null)
//			emelSemak = new HTPEmelJRPBean();
//		return emelSemak;
//	}
	private IUserPegawai getIUser(){
		if(iUser==null){
			iUser = new UserKJPBean();
		}
		return iUser;

	}

}
//2020/08/21
