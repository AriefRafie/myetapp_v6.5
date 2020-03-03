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
import ekptg.model.php2.FrmTKRHeaderData;
import ekptg.model.php2.FrmTKRJabatanTeknikalData;
import ekptg.model.php2.online.FrmTKROnlineKJPSenaraiFailData;

public class FrmTKROnlineKJPSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(FrmPLPOnlineKJPSenaraiFailView.class);

	FrmTKRHeaderData logicHeader = new FrmTKRHeaderData();
	FrmTKRJabatanTeknikalData logicJabatanTeknikal = new FrmTKRJabatanTeknikalData();
	FrmTKROnlineKJPSenaraiFailData logic = new FrmTKROnlineKJPSenaraiFailData();
	private String templateDir = "app/php2/online/ulasanKJP/tkr";


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
		String actionTukarguna = getParam("actionTukarguna");
		String command = getParam("command");
		String hitButton = getParam("hitButton");
		
		// GET ID PARAM
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idHakmilikSementara = getParam("idHakmilikSementara");
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
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0) {
			idBandar = "99999";
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

		context.put("command", command);
		context.put("templateDir", templateDir);

		String idFail = getParam("idFail");
		String idUlasanTeknikal = getParam("idUlasanTeknikal");

		//DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idJenisTanah, getParam("tarikhTerima"),
						getParam("tarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtPerkara"),
						idKategoriPemohon, idKementerian, idAgensi,	idPejabat,						
						idHakmilikAgensi, idPPTBorangK, idHakmilikUrusan, idPHPBorangK,
						idLuasKegunaan, getParam("txtTujuanKegunaan"), 
						getParam("idKementerianTanah"), getParam("idNegeriTanah"), getParam("idLuasTanah"), getParam("luasTanah"),
						idHakmilikSementara, session);
			}
		}
		this.context.put("errorPeganganHakmilik", "");
		
		

		try {
			if ("refreshDokumenMuatNaik".equals(command)) {

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);

				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);

				vm = "/maklumatUlasan.jsp";	

			} else if ("muatNaikDokumen".equals(command)) {

				logic.hapusDokumen(idUlasanTeknikal);
				uploadFiles(idUlasanTeknikal, session);

				vm = "/refreshDokumenMuatNaik.jsp";	

			} else if ("hantarUlasan".equals(command)) {
				context.remove("flagStatus");

				String userId = (String) session.getAttribute("_ekptg_user_id");
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

			} else if ("simpanUlasan".equals(command)) {
				context.remove("flagStatus");

				String userId = (String) session.getAttribute("_ekptg_user_id");
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

			} else if ("paparFail".equals(command)) {
				//TO CLEAR CONTEXT
				context.remove("BeanHeader");
				context.remove("BeanMaklumatTanah");
				context.remove("lampiran");
				context.remove("flagStatus");

				setMaklumatHeader(idFail, session);
				setMaklumatTanah(idFail, session);

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);

				Vector maklumatLampiran = null;
				maklumatLampiran = new Vector();
				logicJabatanTeknikal.setLampiranKJP(logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				maklumatLampiran = logicJabatanTeknikal.getBeanMaklumatLampiranKJP();

				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);
				this.context.put("maklumatLampiran", maklumatLampiran);

				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);

				vm = "/start.jsp";

			} else if ("carian".equals(command)) {

				String userId = (String) session.getAttribute("_ekptg_user_id");
				String findNoFail = getParam("findNoFail");
				String findTajukFail = getParam("findTajukFail");				
				String findPemohon = getParam("findPemohon");
				String findNoPengenalan = getParam("findNoPengenalan");
				String findTarikhTerima = getParam("findTarikhTerima");
				String findNoHakmilik = getParam("findNoHakmilik");
				String findNoWarta = getParam("findNoWarta");
				String findNoPegangan = getParam("findNoPegangan");
				String findJenisHakmilik = getParam("findJenisHakmilik");
				if(findJenisHakmilik.equals(""))
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

				Vector listFail = logic.getSenaraiFail(findNoFail, findTajukFail, findPemohon, findNoPengenalan, findTarikhTerima, 
						findNoHakmilik, findNoWarta, findNoPegangan, findJenisHakmilik, findJenisLot, findNoLot, findNegeri, findDaerah, findMukim, userId);
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

			} else if ("papar".equals(actionTukarguna)) {

				// GO TO VIEW TUKARGUNA
				vm = "/tkrKJPdaftarPermohonan.jsp";

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

			} else if ("daftarBaru".equals(actionTukarguna)) {

				// GO TO DAFTAR BARU TUKARGUNA
				vm = "app/php2/frmTKRDaftarManual.jsp";

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
				hashPermohonan.put("noFail", "");
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
						idHakmilikAgensi = logic.getIdHakmilikSementaraByPeganganHakmilik(getParam("txtPeganganHakmilik"), idKategoriPemohon, idAgensi);
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

				// GO TO LIST FAIL TUKARGUNA
				vm = "/tkrKJPdaftarPermohonan.jsp";

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
				this.context.put("selectStatus", HTML.SelectStatusTukarguna("socStatusC", Long.parseLong(idStatusC), "", ""));
				this.context.put("selectKementerian", HTML.SelectKementerian("socKementerianC", Long.parseLong(idKementerianC), "", " onChange=\"doChangeKementerian();\""));
				this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensiC", idKementerianC,  Long.parseLong(idAgensiC), "", ""));
				this.context.put("checkTanah", getParam("checkTanah"));
				this.context.put("flagTanah", getParam("checkTanah"));
				this.context.put("flagDetail", flagDetail);
				setupPage(session, action, list);
				
			} if ("daftarBaru".equals(command)) {

				vm = "/tkrKJPdaftarPermohonan.jsp";

				this.context.put("mode", "new");
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");

				// MAKLUMAT PERMOHONAN
				beanMaklumatPermohonan = new Vector();
				Hashtable hashPermohonan = new Hashtable();
				hashPermohonan.put("noFail", "");
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


			} else {

				String userId = (String) session.getAttribute("_ekptg_user_id");
				Vector listFail = logic.getSenaraiFail(null, null, null, null, null, null, null, null, null, null, null, null, null, null, userId);
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

				vm = "/start.jsp";
			}


		} catch (Exception ex) {
			ex.printStackTrace();
		} 

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

		if ("Y".equals(flagBorangK)){
			Vector beanMaklumatBorangK = new Vector();
			beanMaklumatBorangK = logicHeader.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatBorangK", beanMaklumatBorangK);
		} else {
			Vector beanMaklumatTanah = new Vector();
			beanMaklumatTanah = logicHeader.getBeanMaklumatHakmilik();
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
}
