/**
 *
 */
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

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.php2.FrmPYWHeaderData;
import ekptg.model.php2.online.FrmTKRHeaderData;
import ekptg.model.php2.FrmTKRJabatanTeknikalData;
import ekptg.model.php2.online.FrmTKROnlineKJPSenaraiUlasanFailData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.utils.lampiran.ILampiran;

public class FrmTKROnlineKJPSenaraiUlasanFailView extends AjaxBasedModule {
//test 14/8/2020
	private static final long serialVersionUID = 1L;
	private String readonly = "disabled class = \"disabled\"";
	static Logger myLog = Logger.getLogger(FrmTKROnlineKJPSenaraiUlasanFailView.class);
	private ILampiran iLampiran = null;

	FrmTKRHeaderData logicHeader = new FrmTKRHeaderData();
	FrmTKRJabatanTeknikalData logicJabatanTeknikal = new FrmTKRJabatanTeknikalData();
	FrmTKROnlineKJPSenaraiUlasanFailData logic = new FrmTKROnlineKJPSenaraiUlasanFailData();
	private String templateDir = "app/php2/online/ulasanKJP/tkr/ulasan";

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
		//String actionTukarguna = getParam("actionTukarguna");
		String submit = getParam("command");
		String submit2 = getParam("submit2");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }

		myLog.info("submit="+submit);
		myLog.info("submit2="+submit2);
		String hitButton = getParam("hitButton");
		//myLog.info("actionTukarguna="+actionTukarguna);
		myLog.info("hitButton="+hitButton);
		myLog.info("-------------------");
		String idNegeriPemohon = "";
		String userRole = "";
		String userJawatan = "";
		String layerKJP = "";

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
		this.context.put("idFail", idFail);
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idHakmilikSementara = null;
		idHakmilikSementara = getParam("idHakmilikSementara");
		String idDokumen = getParam("idDokumen"); // ADD MAKLUMAT LAMPIRAN
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idPermohonan = getParam("idPermohonan");
		String kategori = getParam("kategori");

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

		// GET DROPDOWN PARAM
		/*String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}*/
		String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String idUrusan = getParam("socUrusan");
		if (idUrusan == null || idUrusan.trim().length() == 0) {
			idUrusan = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0) {
			idSuburusan = "99999";
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
		/*String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}*/

		this.context.put("command", submit);
		this.context.put("templateDir", templateDir);

		this.context.put("errorPeganganHakmilik", "");

		userRole = logic.getUserRole(userId);
		userJawatan = logic.getUserJawatan(userId);

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
			Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
			idNegeriPemohon = hashRayuanDB.get("idNegeri").toString();
			idKementerian = hashRayuanDB.get("idKementerian").toString();
			idAgensi = hashRayuanDB.get("idAgensi").toString();

			myLog.info("JAWATAN="+userJawatan);
			myLog.info("IDKEMENTERIAN="+hashRayuanDB.get("idKementerian").toString());

		}


		this.context.put("idNegeriPemohon", idNegeriPemohon);
		this.context.put("idKementerian", idKementerian);
		this.context.put("idAgensi", idAgensi);
		this.context.put("onload", "");
		this.context.put("completed", false);



		// DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		FrmSemakan semak = null;

		// HITBUTTON
		/*if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idUrusan,idSuburusan,idSubsuburusan,idJenisTanah, getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtNoRujukanSurat"), getParam("txtPerkara"), "3", idKementerian, idAgensi,
						idHakmilikAgensi, idLuasKegunaan,
						getParam("txtTujuanKegunaan"), getParam("idKementerianTanah"), getParam("idNegeriTanah"),
						getParam("idLuasTanah"), getParam("luasTanah"), idHakmilikSementara, session);
			}
			if("doSimpanSenaraiSemak".equals(hitButton)){
				logic.simpanKemaskiniLampiran(idDokumen, getParam("txtNamaLampiran"), getParam("txtCatatanLampiran"),
						session);
			}
			if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
	        		String cbsemaks [] = this.request.getParameterValues("idsSenaraiSemak");
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
		}*/

		myLog.info("submit="+submit);
		myLog.info("submit2="+submit2);
		myLog.info("hitButton="+hitButton);
		this.context.put("errorPeganganHakmilik", "");

		try {

			if ("refreshDokumenMuatNaik".equals(submit)) {//ros guna nie

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);

				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);

				vm = "/maklumatUlasan.jsp";

			} else if ("muatNaikDokumen".equals(submit)) {//ros guna nie

				logic.hapusDokumen(idUlasanTeknikal);
				uploadFiles(idUlasanTeknikal, session);

				vm = "/refreshDokumenMuatNaik.jsp";

			} else if ("hantarUlasan".equals(submit)) {//ros guna nie
				context.remove("flagStatus");

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

			} else if ("simpanUlasan".equals(submit)) {//ros guna nie
				context.remove("flagStatus");

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

			}/* else if ("papar".equals(submit2)) {
				vm = "/frmTKRKJPDaftarManual.jsp";

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


				//vm = "/start.jsp";

			} else if ("daftarBaru".equals(submit)) {
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


				//MAKLUMAT HAKMILIK
				if ("doChangePeganganHakmilik".equals(submit)) {
					idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"), "3", idAgensi);

						if (idHakmilikAgensi.isEmpty()) {
						this.context.put("errorPeganganHakmilik","Hakmilik tidak wujud.");
						}

				}

				beanMaklumatTanah = new Vector();
				myLog.info("idHakmilikAgensi: "+idHakmilikAgensi+" idHakmilikSementara: "+idHakmilikSementara);
				logic.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
				beanMaklumatTanah = logic.getBeanMaklumatTanah();
				context.put("BeanMaklumatTanah", beanMaklumatTanah);
				this.context.put("idFail", idFail);
				this.context.put("idHakmilikSementara", idHakmilikSementara);
				this.context.put("idHakmilikAgensi", idHakmilikAgensi);


				context.put("namaJenisTanah", namaJenisTanah);
				//vm = "/start.jsp";

			}*/ else if ("paparFail".equals(submit)) {//ros guna ni
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



			}else if ("carian".equals(submit)) { //ros ni guna
				//String userId = (String) session.getAttribute("_ekptg_user_id");
				myLog.info("masuk carian");
				String findNoFail = getParam("findNoFail");
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

				Vector listFail = logic.getSenaraiFail(findNoFail, findNoPermohonan, findTajukFail, findPemohon, findNoPengenalan, findTarikhTerima,
						findNoHakmilik, findNoWarta, findNoPegangan, findJenisHakmilik, findJenisLot, findNoLot
						, findNegeri, findDaerah, findMukim
						, userId);
				this.context.put("SenaraiFail", listFail);
				setupPage(session, action, listFail);

				context.put("findNoFail", findNoFail);
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

			}/* else if ("kembali".equals(submit)) {
				myLog.info("kembali");
				//String userId = (String) session.getAttribute("_ekptg_user_id");
				Vector listFail = logic.getSenaraiFail(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, userId);
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

			} else if ("seterusnya".equals(submit2)) {

				myLog.info("baca seterusnya");
				myLog.info("idFail=========" + idFail);
				// GO TO MAKLUMAT PERMOHONAN
				vm = "/frmTKRKJPMaklumatPermohonan.jsp";

				// this.context.put("mode", "view");
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");

				// MODE VIEW
				if ("view".equals(mode)) {
					myLog.info("baca mode view");

					// MAKLUMAT PERMOHONAN
					beanMaklumatPermohonan = new Vector();
					logic.setMaklumatPermohonan(idFail);
					beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
					if (beanMaklumatPermohonan.size() != 0){
		    			Hashtable hashMaklumatPelepasan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
		    			idLuasKegunaan = (String) hashMaklumatPelepasan.get("flagGuna");
					}
					//myLog.info("idLuasKegunaan >>>> "+idLuasKegunaan);
					Hashtable hashMaklumatPelepasan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
					this.context.put("selectLuasKegunaan", HTML.SelectLuasKegunaan("socLuasKegunaan",
							Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\""));
					this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

					//MAKLUMAT PEMOHON
					logicHeader = new FrmTKRHeaderData();
	    			Vector<Hashtable<String,String>> vec = logicHeader.setMaklumatPemohon(userId);
	    			this.context.put("pemohon", vec.get(0));

					// MAKLUMAT PEMOHON
					beanMaklumatPemohon = new Vector();
					logic.setMaklumatPemohon(idFail);
					if (logic.getBeanMaklumatPemohon().size() != 0) {
						Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
						idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
						idPejabat = (String) hashPemohon.get("idPejabat");
						idKementerian = (String) hashPemohon.get("idKementerian");
						idAgensi = (String) hashPemohon.get("idAgensi");
						namaKementerian = (String) hashPemohon.get("namaKementerian");
						beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
					}
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

					// MAKLUMAT KEGUNAAN TANAH
					beanMaklumatTukarguna = new Vector();
					logic.setMaklumatTukarguna(idPermohonan);
					beanMaklumatTukarguna = logic.getBeanMaklumatTukarguna();
					this.context.put("beanMaklumatTukarguna", beanMaklumatTukarguna);

					// SET DEFAULT ID PARAM
					this.context.put("idFail", idFail);
					this.context.put("idStatus", idStatus);
					this.context.put("idLuasKegunaan", idLuasKegunaan);
					this.context.put("idNegeriPemohon", idNegeriPemohon);
					this.context.put("idHakmilikAgensi", idHakmilikAgensi);

					//senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
					if (logic.getBeanMaklumatPermohonan().size() != 0){
						Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
						idUrusan = (String) hashPermohonan.get("idUrusan");
						idSuburusan = (String) hashPermohonan.get("idSuburusan");
						idSubsuburusan = (String) hashPermohonan.get("idTujuan");
						idPermohonan= (String) hashPermohonan.get("idPermohonan");
					}
					semak = new FrmSemakan();
					semak.mode = mode;
					senaraiSemak = semak.getSenaraiSemakanAttach("phptukar",idPermohonan);
	    			this.context.put("SenaraiSemak", senaraiSemak);
	    			this.context.put("mode", mode);

					// POPUP LAMPIRAN
					if ("3".equals(selectedTabUpper)) {
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

					}
					//SENARAI LAMPIRAN
	    			senaraiLampiran = new Vector();
	    			//logic.setSenaraiLampiran(idPermohonan);
	    			//senaraiLampiran = logic.getListLampiran();
	    			this.context.put("SenaraiLampiran", senaraiLampiran);

				}

				// MODE UPDATE
				else if ("update".equals(mode)) {
					myLog.info("baca mode update");

					myLog.info("baca mode update idPermohonan >>> "+idPermohonan);
					semak = new FrmSemakan();
					semak.mode = mode;
					senaraiSemak = semak.getSenaraiSemakanAttach("phptukar",idPermohonan);
	    			this.context.put("SenaraiSemak", senaraiSemak);
	    			this.context.put("mode", mode);
				}



			}*/ else {
				myLog.info("masuk else"); //ros ni guna
				//String userId = (String) session.getAttribute("_ekptg_user_id");
				Vector listFail = logic.getSenaraiFail(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, userId);
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
				context.remove("findNoPegangan");;
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
			}


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.context.put("idFail", idFail);
		this.context.put("selectedTabUpper", selectedTabUpper);

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

	private ILampiran getDocPHP(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;

	}
}