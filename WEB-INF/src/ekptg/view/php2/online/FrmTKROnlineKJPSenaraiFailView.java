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
	private String readonly = "disabled class = \"disabled\"";
	static Logger myLog = Logger.getLogger(FrmTKROnlineKJPSenaraiFailView.class);

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
		
		String userId = (String) session.getAttribute("_ekptg_user_id");

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String vm2 = "";
		//String actionTukarguna = getParam("actionTukarguna");
		String submit = getParam("command");
		String submit2 = getParam("submit2");
		
		if ("kembali".equals(submit2) || "seterusnya".equals(submit2)) {
			submit = submit2;
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
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idHakmilikSementara = null;
		idHakmilikSementara = getParam("idHakmilikSementara");
		//String idPPTBorangK = getParam("idPPTBorangK");
		//String idHakmilikUrusan = getParam("idHakmilikUrusan");
		//String idPHPBorangK = getParam("idPHPBorangK");
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		
		String idKategoriPemohon = "";
		String idJenisTanah = "1";
		String namaJenisTanah = "TANAH MILIK PERSEKUTUAN";
		
		

		// VECTOR
		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatAgensi = null;
		Vector beanMaklumatPejabat = null;
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatBorangK = null;
		Vector listDetailKJP = null;

		// GET DROPDOWN PARAM
		/*String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}*/
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
		
		// HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(idJenisTanah, getParam("tarikhTerima"), getParam("tarikhSurat"),
						getParam("txtNoRujukanSurat"), getParam("txtPerkara"), "3", idKementerian, idAgensi,
						idHakmilikAgensi, idLuasKegunaan,
						getParam("txtTujuanKegunaan"), getParam("idKementerianTanah"), getParam("idNegeriTanah"),
						getParam("idLuasTanah"), getParam("luasTanah"), idHakmilikSementara, session);
			}
		}

		
		//myLog.info("actionTukarguna="+actionTukarguna);
		//myLog.info("submit2="+submit2);
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

				vm = "/frmTKRKJPDaftarManual.jsp"; 
				this.context.put("mode", "new");
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				/*if ("doChangeKategori".equals(submit)){
					idKementerian = "99999";
					idAgensi = "99999";
					idPejabat = "99999";
					idHakmilikAgensi = "";
					idPPTBorangK = "";
					idHakmilikUrusan = "";
					idPHPBorangK = "";
				}*/
				if ("doChangeKementerian".equals(submit)){
					idAgensi = "99999";
					idHakmilikAgensi = "";
//					idPPTBorangK = "";
//					idHakmilikUrusan = "";
//					idPHPBorangK = "";
				}
				if ("doChangeAgensi".equals(submit)){
					idHakmilikAgensi = "";
//					idPPTBorangK = "";
//					idHakmilikUrusan = "";
//					idPHPBorangK = "";
				}
				if ("doChangeJenisTanah".equals(submit)){
					idHakmilikAgensi = "";
//					idPPTBorangK = "";
//					idHakmilikUrusan = "";
//					idPHPBorangK = "";
				}

				// MAKLUMAT PERMOHONAN
				beanMaklumatPermohonan = new Vector();
				Hashtable hashPermohonan = new Hashtable();
				hashPermohonan.put("noPermohonan", "");
				hashPermohonan.put("noFail", "");
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
				this.context.put("idHakmilikSementara", idHakmilikSementara);
				this.context.put("idHakmilikAgensi", idHakmilikAgensi);
				

				context.put("namaJenisTanah", namaJenisTanah);
				//vm = "/start.jsp";

			} else if ("carian".equals(submit)) {

				//String userId = (String) session.getAttribute("_ekptg_user_id");
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

			} else if ("kembali".equals(submit)) {
				myLog.info("kembali");
				//String userId = (String) session.getAttribute("_ekptg_user_id");
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

			}else if ("seterusnya".equals(submit)) {
				myLog.info("seterusnya");
				
				// GO TO MAKLUMAT PERMOHONAN
				vm = "/frmTKRKJPMaklumatPermohonan.jsp";

				
				

			} else {

				//String userId = (String) session.getAttribute("_ekptg_user_id");
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
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		//this.context.put("actionTukarguna", actionTukarguna);

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