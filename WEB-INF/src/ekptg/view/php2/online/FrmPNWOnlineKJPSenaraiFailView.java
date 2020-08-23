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
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.php2.online.FrmPNWOnlineKJPSenaraiFailData;
import ekptg.model.php2.utiliti.LampiranBean;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserKJPBean;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.IEmel;
import ekptg.model.utils.lampiran.ILampiran;
import ekptg.view.htp.online.jrp.HTPEmelJRPBean;

public class FrmPNWOnlineKJPSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private String readonly = "disabled class = \"disabled\"";
	private IOnline iOnline = null;
	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;
	private IHtp iHTP = null;
	private ekptg.model.utils.emel.IEmel emelSemak = null;
	private IUserPegawai iUser = null;
	private String idSubUrusan = "32";
	private ILampiran iLampiran = null;
	String idJawatan = "";
	
	FrmPNWOnlineKJPSenaraiFailData logic = new FrmPNWOnlineKJPSenaraiFailData();
	static Logger myLog = Logger.getLogger(FrmPNWOnlineKJPSenaraiFailView.class);

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}
		String userId = (String) session.getAttribute("_ekptg_user_id");

		// DROP DOWN PENDAFTARAN
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionOnline = getParam("actionOnline");
		String submit = getParam("command");

		// GET ID PARAM
		String idHakmilikAgensi = getParam("idHakmilikAgensi");
		String idHakmilikSementara = null;
		idHakmilikSementara = getParam("idHakmilikSementara");
		String idHakmilikAgensiPopup = null;
		idHakmilikAgensiPopup = getParam("idHakmilikAgensiPopup");
		String idPPTBorangK = getParam("idPPTBorangK");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String idPHPBorangK = getParam("idPHPBorangK");
//		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String langkah = getParam("langkah");

		String idFailSession = "";
		if (session.getAttribute("idFail") != null) {
			idFailSession = (String) session.getAttribute("idFail");
		}
		String idFail = getParam("idFail");
		String idStatus = getParam("idStatus");
		String idSubUrusan = "32";
		String mode = getParam("mode");
		if (mode.isEmpty()) {
			mode = "view";
		}
		String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String userRole = "";
		String userJawatan = "";
		String layerKJP = "";
		String idNegeriPemohon = "";
		String nama = "";
		String namaAgensi = "";
		String namaPemohon = getParam("namaPemohon");
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";
		String negeri = "";
		String perkara = "";
		String keterangan = "";
		String noFail = "";
		String idPermohonan = getParam("idPermohonan");
		String idPermohonanPelepasan = getParam("idPermohonanPelepasan");
		String idTanahGanti = getParam("idTanahGanti");
		String idPemohon = getParam("idPemohon");
		String idDokumen = getParam("idDokumen");
		String idKategoriPemohon = "";
		String idJenisTanah = "1";
//		String namaJenisTanah = "TANAH MILIK PERSEKUTUAN";
		String namaKementerian = "";
		String kategori = getParam("kategori");

		// VECTOR
		Vector list = null;
		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatPemohon = null;
		Vector beanMaklumatTanah = null;
		Vector listDetailKJP = null;
		Vector beanHeader = null;
		Vector beanMaklumatPenawaran = null;
		Vector beanMaklumatKeputusan = null;
		Vector senaraiAgensi = null;
		Vector beanMaklumatImejan = null;
		Vector senaraiImejan = null;
		Vector beanMaklumatAgensi = null;
		Vector beanMaklumatPejabat = null;
		Vector beanMaklumatBorangK = null;
		Vector senaraiSemak = null;
		FrmSemakan semak = null;

		// GET DROPDOWN PARAM
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0) {
			idPejabat = "99999";
		}
		String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0) {
			idLuasKegunaan = "99999";
		}
		
		String idJenisProjek = getParam("socJenisProjek");
		if (idJenisProjek == null || idJenisProjek.trim().length() == 0) {
			idJenisProjek = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0) {
			idLuas = "99999";
		}

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
			Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
			idNegeriPemohon = hashRayuanDB.get("idNegeri").toString();
			idKementerian = hashRayuanDB.get("idKementerian").toString();
			idAgensi = hashRayuanDB.get("idAgensi").toString();
			myLog.info("JAWATAN="+userJawatan);
			myLog.info("IDKEMENTERIAN="+hashRayuanDB.get("idKementerian").toString());

		}

		userJawatan = String.valueOf(hUser.get("userJawatan"));
		context.put("idjawatan", idJawatan);

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
			if ("doDaftarBaru".equals(hitButton)) {

				idFail = logic.daftarBaru(idJenisTanah, getParam("tarikhTerima"),
						getParam("tarikhSurat"), getParam("txtNoRujukanSurat"),
						"3", idKementerian, idAgensi, idLuasKegunaan,
						getParam("txtTujuanKegunaan"),
						idHakmilikAgensi, idPPTBorangK, idHakmilikUrusan, idPHPBorangK,
						getParam("idKementerianTanah"), getParam("idNegeriTanah"), getParam("idLuasTanah"), getParam("luasTanah"),
						idHakmilikSementara, session);

			}
			if ("doSimpanKemaskiniMaklumatTnh".equals(hitButton)) {
				logic.updateTanah(idPermohonan, idHakmilikAgensi, session);
			}
			if ("doSimpanKemaskiniMaklumatPelepasan".equals(hitButton)) {
				logic.updatePermohonanPenawaran(idPermohonanPelepasan, idLuasKegunaan, idLuas,
						getParam("txtLuasMohon1"), getParam("txtLuasMohon2"), getParam("txtLuasMohon3"),
						getParam("txtLuasBersamaan"), getParam("txtBakiLuas"), session);
			}
			if ("doHantarSemakan".equals(hitButton)) {

				if (logic.checkMaklumatPenawaranLengkap(idPermohonan)) {
					this.context.put("onload", " \"alert('Masih terdapat maklumat penawaran yang belum lengkap.')\"");
				} else {
					logic.updatePermohonanSemakan(idPermohonan, idKementerian, session);
				}
				
			}
			if ("doHantarKelulusan".equals(hitButton)) {

				if (logic.checkMaklumatPenawaranLengkap(idPermohonan)) {
					this.context.put("onload", " \"alert('Masih terdapat maklumat penawaran yang belum lengkap.')\"");
				} else {
					logic.updatePermohonanKelulusan(idPermohonan, idKementerian, session);
				}
			}
			if ("doHantarEmel".equals(hitButton)) {

				if (logic.checkMaklumatPenawaranLengkap(idPermohonan)) {
					this.context.put("onload", " \"alert('Masih terdapat maklumat penawaran yang belum lengkap.')\"");
				} else {
					logic.updatePermohonanEmel(idFail, idPermohonan, session);
				}
			}
			if ("doHapus".equals(hitButton)) {
				logic.hapusPermohonan(idFail);
			}

			if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idPermohonan, session);
			}
			if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen, getParam("txtNamaImej"), getParam("txtCatatanImej"), session);
			}
			if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen);
			}
			// SENARAI SEMAK shiqa 29092020
			if ("doSimpanKemaskiniSenaraiSemak".equals(hitButton)) {
        		String cbsemaks [] = this.request.getParameterValues("idsSenaraiSemak");
//    			logic.updateSenaraiSemak(idPermohonan,semaks,session);
    				
        		//String[] cbsemaks = this.request.getParameterValues("cbsemaks");
    			FrmSemakan frmSemak = new FrmSemakan();
    			frmSemak.semakanHapusByPermohonan(idPermohonan);
    			if (cbsemaks != null) {
    				for (int i = 0; i < cbsemaks.length; i++) {
    					FrmSemakan.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
    				}
    			}
    				
        	}
		}
		this.context.put("errorPeganganHakmilik", "");
		myLog.info("actionOnline=" + actionOnline);
		myLog.info("submit=" + submit);
		myLog.info("mode=" + mode);
		
		//SHIQA SENARAI SEMAK SCRIPT 29072020
		this.context.put("javascriptLampiran", getDocPHP().javascriptUpload("", "paparLampiran", "idDokumen",session, "phppnw"));


		if ("papar".equals(actionOnline)) {

			// GO TO VIEW PENAWARAN
			vm = "app/php2/online/ulasanKJP/pnw/frmPNWKJPDaftarManual.jsp";

			mode = "view";
			this.context.put("mode", "view");
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");


			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

			// MAKLUMAT KEGUNAAN TANAH
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(logic.getIdHakmilikAgensi(idFail));
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\""));

			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			if (logic.getBeanMaklumatPemohon().size() != 0) {
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idPejabat = (String) hashPemohon.get("idPejabat");
				idKementerian = (String) hashPemohon.get("idKementerian");
				idAgensi = (String) hashPemohon.get("idAgensi");
			}
			idKategoriPemohon = logic.getKategoriPemohonPenawaran();
			senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);

		} else if ("daftarBaru".equals(actionOnline)) {

			// GO TO DAFTAR BARU PENAWARAN
			vm = "app/php2/online/ulasanKJP/pnw/frmPNWKJPDaftarManual.jsp";

			mode = "new";
			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");

			if ("doChangeKementerian".equals(submit)) {
				idAgensi = "99999";
				idHakmilikAgensi = "";
				
			}
			if ("doChangeAgensi".equals(submit)) {
				idHakmilikAgensi = "";
				
			}
			if ("doChangeJenisTanah".equals(submit)) {
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
			hashPermohonan.put("luasTanah", getParam("luasTanah") == null ? "": getParam("luasTanah"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

			// MAKLUMAT PEMOHON
			idKategoriPemohon = logic.getKategoriPemohonPenawaran();

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
			
			myLog.info("agensi===="+idAgensi);
			myLog.info("idFail===="+idFail);



			// MAKLUMAT HAKMILIK
			if ("doChangePeganganHakmilik".equals(submit)) {
				idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik"), "3", idAgensi);
				if (idHakmilikAgensi.isEmpty()) {
					this.context.put("errorPeganganHakmilik", "Hakmilik tidak wujud.");
				}
			}
			
			// MAKLUMAT KEGUNAAN TANAH

			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("selectLuasKegunaan",HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong("1"), "disabled", "class=\"disabled\""));
			this.context.put("idHakmilikAgensi", idHakmilikAgensi);
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			this.context.put("idFail", idFail);
			this.context.put("idHakmilikSementara", idHakmilikSementara);
			
			

		} else if ("seterusnya".equals(actionOnline)) {

			// GO TO MAKLUMAT PERMOHONAN
			vm = "app/php2/online/frmPNWMaklumatPermohonan.jsp";
			myLog.info("IDSTATUS========"+idStatus);
			myLog.info("USERID========"+userId);

			// HEADER
			beanHeader = new Vector();
			logic.setMaklumatHeader(idFail);
			beanHeader = logic.getBeanMaklumatHeader();
			this.context.put("BeanHeader", beanHeader);

			if (beanHeader.size() != 0) {
				Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatHeader().get(0);
				idFail = (String) hashHeader.get("idFail");
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
				this.context.put("namaKementerian", nama);
				this.context.put("namaAgensi", namaAgensi);
				this.context.put("alamat1", alamat1);
				this.context.put("alamat2", alamat2);
				this.context.put("alamat3", alamat3);
				this.context.put("poskod", poskod);
				this.context.put("negeri", negeri);
				this.context.put("perkara", perkara);
			}
			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = new Vector();
			if (logic.getBeanMaklumatPemohon().size() != 0) {
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idPejabat = (String) hashPemohon.get("idPejabat");
				idKementerian = (String) hashPemohon.get("idKementerian");
				idAgensi = (String) hashPemohon.get("idAgensi");
				this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
				idKategoriPemohon = logic.getKategoriPemohonPenawaran();
				this.context.put("idKategoriPemohon", idKategoriPemohon);
			}

			// MODE VIEW
			if ("view".equals(mode)) {
				myLog.info("baca view!!");
    			String id = getParam("id_kemaskini");
    			logic.setMaklumatHeader(idFail);
    			beanHeader = logic.getBeanMaklumatHeader();
    			this.context.put("BeanHeader", beanHeader);
    			Hashtable permohonan = (Hashtable) logic.getBeanMaklumatHeader().get(0);
    			myLog.info("pengesahan ::idpermohonan=" + logic.getBeanMaklumatHeader().get(0));

    			idFail = (String) permohonan.get("idFail");
    			idPermohonan = (String) permohonan.get("idPermohonan");
    			idStatus = (String) permohonan.get("idStatus");
    			idHakmilikAgensi = (String) permohonan.get("idHakmilikAgensi");
    			idNegeriPemohon = (String) permohonan.get("idNegeriPemohon");
    			nama = (String) permohonan.get("namaKementerian");
    			namaAgensi = (String) permohonan.get("namaAgensi");
    			alamat1 = (String) permohonan.get("alamat1");
    			alamat2 = (String) permohonan.get("alamat2");
    			alamat3 = (String) permohonan.get("alamat3");
    			poskod = (String) permohonan.get("poskod");
    			negeri = (String) permohonan.get("negeri");
    			perkara = (String) permohonan.get("perkara");
    			keterangan = (String) permohonan.get("keterangan");
    			this.context.put("noFail", noFail);
    			this.context.put("namaKementerian", nama);
    			this.context.put("namaAgensi", namaAgensi);
    			this.context.put("alamat1", alamat1);
    			this.context.put("alamat2", alamat2);
    			this.context.put("alamat3", alamat3);
    			this.context.put("poskod", poskod);
    			this.context.put("negeri", negeri);
    			this.context.put("perkara", perkara);
    			this.context.put("keterangan", keterangan);
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
    			idKategoriPemohon = logic.getKategoriPemohonPenawaran();
    			this.context.put("idKategoriPemohon", idKategoriPemohon);
    			this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian",
    					Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
    			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian,
    					Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
    			
    			logic.getIdNegeriKJPByUserId(userId);
    			Hashtable hashList = (Hashtable) listDetailKJP.get(0);
    			listDetailKJP = logic.getIdNegeriKJPByUserId(userId);
    			idNegeriPemohon = hashList.get("idNegeri").toString();
    			namaPemohon = (String) hashList.get("namaPemohon");
    			userId = (String) hashList.get("userId");

    			this.context.put("ListdetailKJP", listDetailKJP);
    			this.context.put("namaPemohon", namaPemohon);
    			this.context.put("userId", userId);
    			myLog.info("namaPemohon:: " + namaPemohon);

    			String semakMode = "";
    			String statusSemasa = "1";
    			

    			if (getIOnline().isHantar(Long.parseLong(String.valueOf(permohonan.get("idSubUrusan"))),
    					Long.parseLong(String.valueOf(permohonan.get("idPermohonan"))),
    					Long.parseLong(String.valueOf(permohonan.get("idFail"))), "4")) {
    				semakMode = "xupdate";
    			} else {

    				myLog.info("idPermohonan::" + logic.getBeanMaklumatHeader().get(0));
    				semakMode = "update";
    				if (getIOnline().isHantarAktif(Long.parseLong(String.valueOf(permohonan.get("idSubUrusan"))),
    						Long.parseLong(String.valueOf(permohonan.get("idPermohonan"))),
    						Long.parseLong(String.valueOf(permohonan.get("idFail"))), "1")) {
    					statusSemasa = "1";

    				} else if (getIOnline().isHantarAktif(Long.parseLong(String.valueOf(permohonan.get("idSubUrusan"))),
    						Long.parseLong(String.valueOf(permohonan.get("idPermohonan"))),
    						Long.parseLong(String.valueOf(permohonan.get("idFail"))), "2")) {
    					statusSemasa = "2";

    				} else if (getIOnline().isHantarAktif(Long.parseLong(String.valueOf(permohonan.get("idSubUrusan"))),
    						Long.parseLong(String.valueOf(permohonan.get("idPermohonan"))),
    						Long.parseLong(String.valueOf(permohonan.get("idFail"))), "3")) {
    					statusSemasa = "3";

    				}
    			}
    			context.put("semakMode", semakMode);
    			myLog.info("statusSemasa:" + statusSemasa);
    			context.put("statussemasa", statusSemasa);
    			context.put("idFail", (String.valueOf(permohonan.get("idFail"))));
    			context.put("buttonSend", "disabled");
    			context.put("idPermohonan", Long.parseLong(String.valueOf(permohonan.get("idPermohonan"))));
				
				String luasAsal = "";
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				this.context.put("disabled", "disabled");

				if ("0".equals(selectedTabUpper)) {
					// MAKLUMAT TANAH
					beanMaklumatTanah = new Vector();
					logic.setMaklumatTanah(idHakmilikAgensi);
					beanMaklumatTanah = logic.getBeanMaklumatTanah();
					this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				} else if ("1".equals(selectedTabUpper)) {
					// MAKLUMAT PENAWARAN
					beanMaklumatPenawaran = new Vector();
					logic.setMaklumatPenawaran(idPermohonan);
					Hashtable hashMaklumatPenawaranDB = (Hashtable) logic.getBeanMaklumatPenawaran().get(0);
					
					beanMaklumatPenawaran = logic.getBeanMaklumatPenawaran();
					this.context.put("BeanMaklumatPenawaran", beanMaklumatPenawaran);
					
					// SET DEFAULT ID PARAM
					this.context.put("idFail", idFail);
					this.context.put("idStatus", idStatus);
					this.context.put("userId", userId);
					this.context.put("idLuasKegunaan", idLuasKegunaan);
					this.context.put("idNegeriPemohon", idNegeriPemohon);
					this.context.put("idHakmilikAgensi", idHakmilikAgensi);
					
					Hashtable hashMaklumatPenawaran1 = new Hashtable();
					hashMaklumatPenawaran1.put("tarikhTerima", getParam("tarikhTerima"));
					hashMaklumatPenawaran1.put("tarikhSurat", getParam("tarikhSurat"));
					hashMaklumatPenawaran1.put("perkara", getParam("txtPerkara"));
					hashMaklumatPenawaran1.put("luasAsal", hashMaklumatPenawaranDB.get("luasAsal"));
					hashMaklumatPenawaran1.put("keteranganLuasAsal", hashMaklumatPenawaranDB.get("keteranganLuasAsal"));
					
					if (beanMaklumatPenawaran.size() != 0) {
						Hashtable hashMaklumatPenawaran = (Hashtable) logic.getBeanMaklumatPenawaran().get(0);
						idPermohonanPelepasan = (String) (hashMaklumatPenawaran.get("idPermohonanPelepasan"));
						if (hashMaklumatPenawaran.get("flagGuna") != null
								&& hashMaklumatPenawaran.get("flagGuna").toString().trim().length() != 0) {
							idLuasKegunaan = (String) hashMaklumatPenawaran.get("flagGuna");
						} else {
							idLuasKegunaan = "99999";
						}
						if (hashMaklumatPenawaran.get("idLuasMohon") != null
								&& hashMaklumatPenawaran.get("idLuasMohon").toString().trim().length() != 0) {
							idLuas = (String) hashMaklumatPenawaran.get("idLuasMohon");
						} else {
							idLuas = "99999";
						}
						
					}
					this.context.put("luasAsal", hashMaklumatPenawaranDB.get("luasAsal"));
					this.context.put("selectLuasKegunaan", HTML.SelectLuasKegunaan("socLuasKegunaan",
							Long.parseLong(idLuasKegunaan), "disabled", " class=\"disabled\" style=\"width:auto\""));

				} else if ("2".equals(selectedTabUpper)){
					myLog.info("mode=" + mode);
	            	this.context.put("completed", false);
	        		this.context.put("flagPopup", "closePopupLampiran");
					semak = new FrmSemakan();
					semak.mode = mode;
					senaraiSemak = semak.getSenaraiSemakanAttach("phppnw",idPermohonan);
	            	
					//SENARAI SEMAK SHIQA-29072020
//	            	senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
	    			this.context.put("SenaraiSemak", senaraiSemak);
	    			
	    			if ("update".equals(mode)){
	    				
	        			//senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
		    			//this.context.put("SenaraiSemak", senaraiSemak);
	    			}
	    			else if ("view".equals(mode)){
	        			
	        			//senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
		    			//this.context.put("SenaraiSemak", senaraiSemak);
		    			
	        		}
	    			
	    		//shiqa - pengesahan 20072020	
	            } else if ("3".equals(selectedTabUpper)) {
	            	namaPemohon = getParam("namaPemohon");
	            	this.context.put("namaPemohon", namaPemohon);
	            	myLog.info("namaaaaaa========="+namaPemohon);
	            	myLog.info("idFail========="+idFail);
	            	

	    			if ("simpanpengesahan2".equals(submit)){
	    				myLog.info("simpanpengesahan2 :::::::::::idpermohonan=" + logic.getBeanMaklumatHeader().get(0));
	    				logic.setMaklumatHeader(idFail);
	    				beanHeader = logic.getBeanMaklumatHeader();
	    				idPermohonan = getParam("idPermohonan");
	    				Hashtable permohonan2 = (Hashtable) logic.getBeanMaklumatHeader().get(0);
	    				myLog.info("simpanpengesahan1 ::id_permohonan="+permohonan2);	
	    		    	myLog.info("simpanpengesahan2 ::idpermohonan="+logic.getBeanMaklumatHeader().get(0));	
	    				semakMode="";
	    				
	    				

	    				
	    				/*
	    				 * 1 untuk status - Pra-daftar
	    				 * 2 untuk status - Tindakan Penyemak 
	    				 * 3 untuk status - Tindakan Pelulus
	    				 * 4 untuk status - Permohonan Online (Pengesahan) 
	    				 * 5  untuk status - Penerimaan Permohonan
	    				*/
	    				String langkah2 = "2";
	    				EmailConfig ec = new EmailConfig();

	    				String emelSubjek = ec.tajukSemakan+"Penawaran";
	    				String kandungan = "";
	    				if(idJawatan.equals("20")||idJawatan.equals("24")){
	    					
	    					this.context.put("idKementerian", idKementerian);
	    					
	    					langkah2 = "2";
	    					
	    					kandungan = getEmelSemak().setKandungan(String.valueOf(permohonan2.get("namaAgensi")), String.valueOf(hUser.get("nama")));
	    	    			
	    					if(!getEmelSemak().checkEmail(userId).equals(""))
	    						getIHTP().getErrorHTML("[ONLINE-HTP PENAWARAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

	    					ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
	    							, "9"
	    							, String.valueOf(String.valueOf(permohonan2.get("idKementerian")))
	    							, emelSubjek, kandungan);

	    				}else if (idJawatan.equals("9")){
	    					langkah2 = "3";				
	    					
	    					kandungan = getEmelSemak().setKandungan(String.valueOf(permohonan2.get("namaAgensi")), String.valueOf(hUser.get("nama")));
	    	    			
	    					if(!getEmelSemak().checkEmail(userId).equals(""))
	    						getIHTP().getErrorHTML("[ONLINE-HTP PENAWARAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");

	    					ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
	    							, "4"
	    							, String.valueOf(String.valueOf(permohonan2.get("idKementerian")))
	    							, emelSubjek, kandungan);
	    								
	    				}else if (idJawatan.equals("4")){
	    					langkah2 = "4";
	    					emelSubjek = ec.tajukHantarPermohonan + "Penawaran";
	    							
	    					kandungan = getEmelSemak().setKandungan(String.valueOf(permohonan2.get("namaAgensi")), String.valueOf(hUser.get("nama")));

	    	    			
	    					if(!getEmelSemak().checkEmail(userId).equals(""))
	    						getIHTP().getErrorHTML("[ONLINE-HTP PENAWARAN] Emel Pengguna Perlu Dikemaskini Terlebih Dahulu.");
	    					//   (HTP)HQPenggunaPembelianPerletakhakan,   (HTP)HQPenggunaPembelian, (HTP)HQPengguna

	    					ec.sendByRoleKJP(getEmelSemak().checkEmail(userId)
	    							, "4"
	    							, String.valueOf(String.valueOf(permohonan2.get("idKementerian")))
	    							, emelSubjek, kandungan);
	    									
	    				}
	    				Hashtable permohonan3 = (Hashtable) logic.getBeanMaklumatHeader().get(0);
	    				idPermohonan = String.valueOf(permohonan3.get("idPermohonan"));

	    			
	    				Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
	    				long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah2,idSubUrusan,"=");
	    				rsusf.setIdPermohonan(Long.parseLong(idPermohonan));
	    				rsusf.setIdFail(Long.parseLong(idFail));
	    				rsusf.setIdSuburusanstatusfail(Long.parseLong(idSubUrusan));
	    				rsusf.setIdSuburusanstatus(setIdSuburusanstatus);
	    				rsusf.setUrl("-");
	    				rsusf.setIdMasuk(Long.parseLong(userId));
	    			
	    				simpanPengesahan(rsusf,langkah2,userId);
	    				
	    				long setIdstatus = FrmUtilData.getIdStatusByLangkah (langkah2,idSubUrusan,"=");
	    				FrmUtilData utildata = new FrmUtilData();
	    				utildata.kemaskiniStatusPermohonan(idPermohonan,String.valueOf(setIdstatus));

	    				
	    				if(getIOnline().isHantar(Long.parseLong(String.valueOf(permohonan3.get("idSubUrusan"))),
	    						Long.parseLong(String.valueOf(permohonan3.get("idPermohonan"))),
	    						Long.parseLong(String.valueOf(permohonan3.get("idFail"))),langkah)){
	    					semakMode = "xupdate";			
	    				}else{
	    					semakMode = "update";
	    				}
	    				context.put("semakMode", semakMode);
	    				context.put("statussemasa", langkah2);
	    				idFail = (String) permohonan2.get("idFail");
	    				idPermohonan = (String) permohonan2.get("idPermohonan");
	    				idStatus = (String) permohonan2.get("idStatus");
	    				this.context.put("BeanHeader", beanHeader);
	    				
	    				
	    				vm = "app/php2/online/frmPNWMaklumatPermohonan.jsp";			
	    			
	    			}
	            }
			}

			// MODE UPDATE
			else if ("update".equals(mode)) {
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				this.context.put("disabled", "");

				if ("0".equals(selectedTabUpper)) {

					// MAKLUMAT TANAH
					beanMaklumatTanah = new Vector();
					if(idHakmilikAgensiPopup!="") {
						idHakmilikAgensi=idHakmilikAgensiPopup;
					}
					this.context.put("idAgensi", idAgensi);
					logic.setMaklumatTanah(idHakmilikAgensi);
					beanMaklumatTanah = logic.getBeanMaklumatTanah();
					this.context.put("BeanMaklumatTanah", beanMaklumatTanah);

					if ("doChangePeganganHakmilik1".equals(submit)) {
						beanMaklumatTanah = new Vector();
						idHakmilikAgensi = logic.getIdHakmilikAgensiByPeganganHakmilik(getParam("txtPeganganHakmilik1"),
								"3", idAgensi);
						logic.setMaklumatTanah(idHakmilikAgensi);
						beanMaklumatTanah = logic.getBeanMaklumatTanah();
						this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
						this.context.put("idHakmilikAgensi", idHakmilikAgensi);
						this.context.put("idKementerian", getParam("idKementerian"));
						this.context.put("kodKementerian", getParam("kodKementerian"));
						this.context.put("idNegeriTanah", getParam("idNegeriTanah"));
						this.context.put("kodNegeriTanah", getParam("kodNegeriTanah"));
						if (idHakmilikAgensi.isEmpty()) {
							this.context.put("errorPeganganHakmilik", "Hakmilik tidak wujud.");
						}
					} else if ("doChangeMaklumatTanah".equals(submit)) {
						
						beanMaklumatAgensi = new Vector();
						logic.setMaklumatAgensi(idAgensi);
						beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
						this.context.put("idKategoriPemohon", idKategoriPemohon);
						this.context.put("idAgensi", idAgensi);
						this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
						
						
						beanMaklumatTanah = new Vector();
						idHakmilikAgensi = getParam("idHakmilikAgensi");
						if(idHakmilikAgensiPopup!="") {
							idHakmilikAgensi=idHakmilikAgensiPopup;
						}
						logic.setMaklumatTanah(idHakmilikAgensi);
						beanMaklumatTanah = logic.getBeanMaklumatTanah();
						this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
						this.context.put("idHakmilikAgensi", idHakmilikAgensi);
			
					}

				} else if ("1".equals(selectedTabUpper)) {

					// MAKLUMAT PENAWARAN
					beanMaklumatPenawaran = new Vector();
					logic.setMaklumatPenawaran(idPermohonan);
					Hashtable hashMaklumatPenawaranDB = (Hashtable) logic.getBeanMaklumatPenawaran().get(0);

					if ("99999".equals(idLuasKegunaan)) {
						idLuasKegunaan = (String) hashMaklumatPenawaranDB.get("flagGuna");
						if (!"".equals(idLuasKegunaan)) {
							idLuasKegunaan = (String) hashMaklumatPenawaranDB.get("flagGuna");
						} else {
							idLuasKegunaan = "99999";
						}

					}
					if ("99999".equals(idLuas)) {
						idLuas = (String) hashMaklumatPenawaranDB.get("idLuasMohon");
						if (!"".equals(idLuasKegunaan)) {
							idLuas = (String) hashMaklumatPenawaranDB.get("idLuasMohon");
						} else {
							idLuas = "99999";
						}
					}

					Hashtable hashMaklumatPenawaran = new Hashtable();
					hashMaklumatPenawaran.put("tarikhTerima", getParam("tarikhTerima"));
					hashMaklumatPenawaran.put("tarikhSurat", getParam("tarikhSurat"));
					hashMaklumatPenawaran.put("perkara", getParam("txtPerkara"));
					hashMaklumatPenawaran.put("luasAsal", hashMaklumatPenawaranDB.get("luasAsal"));
					hashMaklumatPenawaran.put("keteranganLuasAsal", hashMaklumatPenawaranDB.get("keteranganLuasAsal"));
					if ("doChangeLuas".equals(submit)) {
						hashMaklumatPenawaran.put("luas1", "");
						hashMaklumatPenawaran.put("luas2", "");
						hashMaklumatPenawaran.put("luas3", "");
						hashMaklumatPenawaran.put("luasBersamaan", "");
						hashMaklumatPenawaran.put("luasBaki", "");
					} else {
						hashMaklumatPenawaran.put("luas1", getParam("txtLuasMohon1"));
						hashMaklumatPenawaran.put("luas2", getParam("txtLuasMohon2"));
						hashMaklumatPenawaran.put("luas3", getParam("txtLuasMohon3"));
						if ("1".equals(idLuasKegunaan)) {
							hashMaklumatPenawaran.put("luasBersamaan", hashMaklumatPenawaranDB.get("luasAsal"));
							hashMaklumatPenawaran.put("luasBaki", Utils.formatLuas(0D));
						} else {
							hashMaklumatPenawaran.put("luasBersamaan", getParam("txtLuasBersamaan"));
							hashMaklumatPenawaran.put("luasBaki", getParam("txtBakiLuas"));
						}
					}
					beanMaklumatPenawaran.addElement(hashMaklumatPenawaran);
					this.context.put("BeanMaklumatPenawaran", beanMaklumatPenawaran);

					this.context.put("selectLuasKegunaan",
							HTML.SelectLuasKegunaan("socLuasKegunaan", Long.parseLong(idLuasKegunaan), "",
									" onChange=\"doChangeLuasKegunaan()\" style=\"width:auto\""));

				} else if ("2".equals(selectedTabUpper)) {
					myLog.info("mode=" + mode);
					this.context.put("completed", false);
					this.context.put("flagPopup", "closePopupLampiran");
					semak = new FrmSemakan();
					semak.mode = mode;
					senaraiSemak = semak.getSenaraiSemakanAttach("phppnw", idPermohonan);

					// SENARAI SEMAK SHIQA-29072020
					// senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
					this.context.put("SenaraiSemak", senaraiSemak);

					if ("update".equals(mode)) {

						// senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
						// this.context.put("SenaraiSemak", senaraiSemak);
					} else if ("view".equals(mode)) {

						// senaraiSemak = logic.getSenaraiSemak(idPermohonan, kategori);
						// this.context.put("SenaraiSemak", senaraiSemak);

					}

				}
			}

			// OPEN POPUP DOKUMEN
			if ("openPopupDokumen".equals(flagPopup)) {

				if ("new".equals(modePopup)) {

					this.context.put("readonlyPopup", "");
					this.context.put("inputTextClassPopup", "");

					beanMaklumatImejan = new Vector();
					Hashtable hashMaklumatImejan = new Hashtable();
					hashMaklumatImejan.put("namaImej", "");
					hashMaklumatImejan.put("catatanImej", "");
					beanMaklumatImejan.addElement(hashMaklumatImejan);
					this.context.put("BeanMaklumatImejan", beanMaklumatImejan);

				} else if ("update".equals(modePopup)) {

					this.context.put("readonlyPopup", "");
					this.context.put("inputTextClassPopup", "");

					// MAKLUMAT DOKUMEN
					beanMaklumatImejan = new Vector();
					logic.setMaklumatImej(idDokumen);
					beanMaklumatImejan = logic.getBeanMaklumatImejan();
					this.context.put("BeanMaklumatImejan", beanMaklumatImejan);

				} else if ("view".equals(modePopup)) {

					this.context.put("readonlyPopup", "readonly");
					this.context.put("inputTextClassPopup", "disabled");

					// MAKLUMAT DOKUMEN
					beanMaklumatImejan = new Vector();
					logic.setMaklumatImej(idDokumen);
					beanMaklumatImejan = logic.getBeanMaklumatImejan();
					this.context.put("BeanMaklumatImejan", beanMaklumatImejan);
				}
			}

			// SENARAI IMEJAN
			senaraiImejan = new Vector();
			logic.setSenaraiImejan(idPermohonan);
			senaraiImejan = logic.getListImejan();
			this.context.put("SenaraiImejan", senaraiImejan);

			logic.setMaklumatPemohon(idFail);
			if (logic.getBeanMaklumatPemohon().size() != 0) {
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idKategoriPemohon = (String) hashPemohon.get("idKategoriPemohon");
				idPejabat = (String) hashPemohon.get("idPejabat");
				idKementerian = (String) hashPemohon.get("idKementerian");
				idAgensi = (String) hashPemohon.get("idAgensi");
			}

		}else {
			myLog.info("baca else");

			String jenisHakmilik = getParam("socJenisHakmilik");
			if (jenisHakmilik == null || jenisHakmilik.trim().length() == 0) {
				jenisHakmilik = "99999";
			}
			String jenisLot = getParam("socJenisLot");
			if (jenisLot == null || jenisLot.trim().length() == 0) {
				jenisLot = "99999";
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

			// GO TO LIST FAIL PENAWARAN
			vm = "app/php2/online/ulasanKJP/pnw/senaraiFail.jsp";

			logic.carianFail(userId, userRole, getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txtNoPermohonan"),
					getParam("txdTarikhTerima"), idNegeriC, idDaerahC, idMukimC, jenisHakmilik,
					getParam("txtNoHakmilik"), getParam("txtNoWarta"), jenisLot, getParam("txtNoLot"),
					getParam("txtNoPegangan"), idStatusC);
			list = new Vector();
			list = logic.getSenaraiFail();
			this.context.put("SenaraiFail", list);

			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtTajukFail", getParam("txtTajukFail"));
			this.context.put("txtNoPermohonan", getParam("txtNoPermohonan"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));

			this.context.put("txtNoPegangan", getParam("txtNoPegangan"));
			this.context.put("selectJenisHakmilik",
					HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(jenisHakmilik), ""));
			this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("selectLot", HTML.SelectLot("socJenisLot", Long.parseLong(jenisLot), ""));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("selectNegeri",
					HTML.SelectNegeri("socNegeriC", Long.parseLong(idNegeriC), "", " onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeriC, "socDaerahC",
					Long.parseLong(idDaerahC), "", " onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim",
					HTML.SelectMukimByDaerah(idDaerahC, "socMukimC", Long.parseLong(idMukimC), ""));

			this.context.put("flagDetail", flagDetail);
			setupPage(session, action, list);
		}

		// SET DEFAULT PARAM
		this.context.put("actionOnline", actionOnline);
		this.context.put("mode", mode);
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);

		// SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idStatus", idStatus);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idPermohonanPelepasan", idPermohonanPelepasan);
		this.context.put("idTanahGanti", idTanahGanti);
		this.context.put("idLuasKegunaan", idLuasKegunaan);
		this.context.put("idLuas", idLuas);
		this.context.put("idNegeriPemohon", idNegeriPemohon);
		this.context.put("idHakmilikAgensi", idHakmilikAgensi);
		this.context.put("idPemohon", idPemohon);
		this.context.put("idDokumen", idDokumen);

		return vm;
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10 : getParamAsInteger("itemsPerPage");
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

	// UPLOAD FILE
	private void uploadFiles(String idPermohonan, HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
					saveData(item, idPermohonan, session);
				}
			}
		}
	}

	private void saveData(FileItem item, String idPermohonan, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");

		try {
			db = new Db();

			// TBLPHPDOKUMEN
			long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
					+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_PERMOHONAN,FLAG_DOKUMEN) "
					+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaImej"));
			ps.setString(3, getParam("catatanImej"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idPermohonan);
			ps.setString(9, "L");
			ps.executeUpdate();

			con.commit();

		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}
	//shiqa - simpanpengesahan 20072020
	private void simpanPengesahan(Tblrujsuburusanstatusfail rsusf, String langkah2,String userid) throws Exception {
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
	private IEmel getEmelSemak(){
		if(emelSemak == null)
			emelSemak = new HTPEmelJRPBean();
		return emelSemak;
	}
	private IUserPegawai getIUser(){
		if(iUser==null){
			iUser = new UserKJPBean();
		}
		return iUser;
			
	}
	private ILampiran getDocPHP(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;
				
	}
	
}
