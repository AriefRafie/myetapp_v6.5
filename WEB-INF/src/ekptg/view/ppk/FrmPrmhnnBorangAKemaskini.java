package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmBorangPSek17OnlineData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8Data;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8Data;
import ekptg.model.ppk.FrmPrmhnnSek8SecaraOnlineData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHData;

public class FrmPrmhnnBorangAKemaskini extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmPrmhnnSek8Data logic = null;
	FrmPrmhnnSek8SecaraOnlineData logiconline = null;
	FrmPrmhnnSek8DaftarSek8InternalData logicSek8Internal = null;
	FrmBorangPSek17OnlineData logic17 = null;

	static Logger myLogger = Logger.getLogger(FrmPrmhnnBorangAKemaskini.class);

	public String doTemplate2() throws Exception {

		logic = new FrmPrmhnnSek8Data();
		logiconline = new FrmPrmhnnSek8SecaraOnlineData();
		logicSek8Internal = new FrmPrmhnnSek8DaftarSek8InternalData();
		logic17 = new FrmBorangPSek17OnlineData();

		// app/ppk/frmBorangAMaklumatPemohonKemaskini.jsp
		HttpSession session = this.request.getSession();
		String vm = "";
		String submit = getParam("hitButt");
		String mode = getParam("mode");

		this.context.put("show_tarikh", "");

		int eventStatus = 0;
		int Submission = 0;
		int idAlert = 0;
		this.context.put("Util", new lebah.util.Util());
		context.put("onchange", "no");

		// System.out.println("PEJE DEBUG START ---------------------------");
		System.out.println("submit : " + submit);

		System.out.println("View --->>>frmPrmhnnBorangAKemaskini");
		System.out.println("idPermohonan--->" + getParam("idPermohonan"));
		System.out.println("mode--->" + mode);
		System.out.println("command--->" + getParam("command"));

		String ajaxmode = getParam("command"); // First Level - default by
												// AjaxBasedModule Call

		myLogger.debug("command:" + ajaxmode);

		if (!getParam("command").equals("")) {
			submit = ajaxmode;
		} else {
			submit = "check_kp";
		}
		this.context.put("val_tab", getParam("v_tab"));

		myLogger.debug("submit:" + submit);

		Vector listEmptyField = null;
		String hideTabPengesahan = getParam("hideTabPengesahan");

		System.out.println(getParam("tabIdatas"));
		System.out.println(getParam("tabIdtengah"));
		System.out.println(getParam("tabIdbawah"));
		System.out.println(getParam("tabIdtepi"));

		if ("check_kp".equals(submit)) {
			String typez = getParam("typez");
			myLogger.debug("typez:" + typez);
			String NoKPBaruSimati = "";
			String NoKPLamaSimati = "";
			String NoKPLainSimati = "";
			String NoPermohonan = "";
			if (typez.equals("online")) {
				NoPermohonan = getParam("nopermohonan");
				Vector infoPermohonan = logiconline.semakDetailSimati(
						NoKPBaruSimati, NoKPLamaSimati, NoKPLainSimati,
						NoPermohonan);
				Hashtable l = (Hashtable) infoPermohonan.get(0);
				NoKPBaruSimati = l.get("nokpbarusimati").toString();
				NoKPLamaSimati = l.get("nokplamasimati").toString();
				NoKPLainSimati = l.get("nokplainsimati").toString();
				hideTabPengesahan = checkEmptyField(l.get("idPermohonan")
						.toString());
				context.put("hideTabPengesahan", hideTabPengesahan);
				System.out.println("idPermohonan---"
						+ l.get("idPermohonan").toString());

			} else {
				NoKPBaruSimati = getParam("txtNoKPBaru1a")
						+ getParam("txtNoKPBaru2a") + getParam("txtNoKPBaru3a");
				NoKPLamaSimati = getParam("txtNoKPLamaa");
				NoKPLainSimati = getParam("txtNoKPLaina");
				NoPermohonan = getParam("txtNoPermohonan");
				return "app/ppk/warning.jsp";
			}
			myLogger.debug("NoKPBaruSimati" + NoKPBaruSimati);
			myLogger.debug("NoKPLamaSimati" + NoKPLamaSimati);
			myLogger.debug("NoKPLainSimati" + NoKPLainSimati);
			myLogger.debug("NoPermohonan" + NoPermohonan);

			int getResultChkId = logiconline.checkSimati(NoKPBaruSimati,
					NoKPLamaSimati, NoKPLainSimati, NoPermohonan,
					(String) session.getAttribute("_ekptg_user_id"));

			myLogger.debug("getResultChkId:" + getResultChkId);

			if (getResultChkId == 0) {
				idAlert = 1;
				this.context.put("IdAlert", idAlert);
			} else {
				Vector detailPermohonan = logiconline.semakDetailSimati(
						NoKPBaruSimati, NoKPLamaSimati, NoKPLainSimati,
						NoPermohonan);
				this.context.put("DetailPemohon", detailPermohonan);
				Hashtable k = (Hashtable) detailPermohonan.get(0);
				String idPermohonan = (String) k.get("idPermohonan");
				this.context.put("idtarafkptg", (String) k.get("tarafkptg"));
				this.context.put("idsaudara", (String) k.get("saudara"));

				int simpanStatus = 1;
				this.context.put("SimpanStatus", simpanStatus);
				this.context.put("new_data", "no");
				this.context.put("setmode", "class=\"disabled\"");
				this.context.put("setmode2", "class=\"disabled\"");
				this.context.put("setmode3", "disabled");
				this.context.put("readonly", "readonly");
				this.context.put("sijilmode", "class=\"disabled\"");

				Vector listDataSimati = logiconline
						.semakDataSimati(idPermohonan);
				Hashtable x = (Hashtable) listDataSimati.get(0);

				if (x.get("idsimati") != "") {
					String idSimati = (String) x.get("idsimati");
					this.context.put("idSimati", idSimati);
				}
				if (x.get("flaghubungan") == "")
					x.put("flaghubungan", "0");
				int flaghub = Integer
						.parseInt(x.get("flaghubungan").toString());
				this.context.put("ViewDataSimat", listDataSimati);
				this.context.put("idpermohonansimati", (String) x
						.get("idpermohonansimati"));
				this.context.put("IdPermohonan", idPermohonan);
				this.context.put("idstatus", (String) x.get("idstatus"));
				this.context.put("id", idPermohonan);
				this.context.put("flaghub", flaghub);
				if (x.get("nokpbarusimati3") != "") {
					String nokppemohon = x.get("nokpbarusimati3").toString();
					String lastDigit = nokppemohon.substring(3, 4);
					int digitValue = Integer.parseInt(lastDigit);
					String gender = "";
					if (digitValue == 0 || digitValue == 2 || digitValue == 4
							|| digitValue == 6 || digitValue == 8) {
						gender = "2"; // female
					} else {
						gender = "1"; // male
					}
					this.context.put("jantina", gender);
				} else {
					this.context.put("jantina", 0);
				}

				if (x.get("idBuktiMati") != "1")
					x.put("sijilmode", "disabled");

				eventStatus = 1;
				this.context.put("EventStatus", eventStatus);

				initNewTab();

				Vector jenisAgama = logiconline.getAgama();
				this.context.put("JenisAgama", jenisAgama);

				Vector jenisWarga = logiconline.getWarganegara();
				this.context.put("JenisWarga", jenisWarga);

				Vector jenisBuktiMati = FrmPrmhnnSek8DaftarSek8Data
						.getListbuktimati();
				this.context.put("JenisBuktiMati", jenisBuktiMati);

				Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
				this.context.put("listkp", view1);

				Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("ListNegeri", listNegeri);

				Vector listBandar = logiconline.getBandar();
				this.context.put("ListBandar", listBandar);

				Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
				this.context.put("ListTaraf", listTaraf);

				String Waris = getParam("sorWaris");
				String OB = getParam("sorOB");
				this.context.put("sorWaris", Waris);
				this.context.put("sorOB", OB);

				String valueSor = getParam("sorAdaHTA");
				this.context.put("sorAdaHTA", valueSor);
				initTextfieldSimati();

				vm = "app/ppk/frmPrmhnnBorangASimati.jsp";
			}

		} else if ("goAlert".equals(submit)) {
			String idPermohonan = getParam("idPermohonan");
			int getResultChkIdSimati = logiconline
					.countDataSimati(idPermohonan);
			if (getResultChkIdSimati == 0) {
				int simpanStatus = 0;
				this.context.put("SimpanStatus", simpanStatus);
				this.context.put("new_data", "yes");
			} else {
				int simpanStatus = 1;
				this.context.put("SimpanStatus", simpanStatus);
				this.context.put("new_data", "no");
			}

			// tarik dan paparkan data simati
			Vector listDataSimati = logiconline.semakDataSimati(idPermohonan);
			this.context.put("ViewDataSimat", listDataSimati);
			this.context.put("IdPermohonan", idPermohonan);

			eventStatus = 1;
			this.context.put("EventStatus", eventStatus);

			// untuk papar tab
			initNewTab();

			Vector jenisAgama = logiconline.getAgama();
			this.context.put("JenisAgama", jenisAgama);

			Vector jenisWarga = logiconline.getWarganegara();
			this.context.put("JenisWarga", jenisWarga);

			Vector jenisBuktiMati = FrmPrmhnnSek8DaftarSek8Data
					.getListbuktimati();
			this.context.put("JenisBuktiMati", jenisBuktiMati);

			Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
			this.context.put("listkp", view1);

			Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
			this.context.put("ListNegeri", listNegeri);

			Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
			this.context.put("ListTaraf", listTaraf);

			String Waris = getParam("sorWaris");
			String OB = getParam("sorOB");
			this.context.put("sorWaris", Waris);
			this.context.put("sorOB", OB);

			String valueSor = getParam("sorAdaHTA");
			this.context.put("sorAdaHTA", valueSor);

			vm = "app/ppk/frmPrmhnnBorangASimati.jsp";
		} else if ("Waris".equals(submit)) {
			String readability1 = "";
			String readability2 = "readonly class=\"disabled\"";
			String disability1 = " disabled readonly class=\"disabled\"";
			String disability2 = "";

			this.context.put("checkmati", "0");
			this.context.put("check4", "");

			initTabData();

			// context.put("DATEUTIL",new DateUtil());

			Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
			this.context.put("listnegeri", listNegeri);

			Vector ListSaudara = FrmPrmhnnSek8DaftarSek8Data.getListsaudara();
			this.context.put("listsaudara", ListSaudara);

			Vector list = logiconline.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);

			Hashtable a = (Hashtable) list.get(0);
			String id1 = (String) a.get("idsimati");
			String id2 = (String) a.get("idPemohon");
			String id = getParam("idPermohonan");
			int flaghub = Integer.parseInt(a.get("flaghubungan").toString());
			String idpermohonansimati = (String) a.get("idpermohonansimati");
			this.context.put("idstatus", (String) a.get("idstatus"));
			this.context.put("idpermohonansimati", idpermohonansimati);
			this.context.put("idSimati", id1);
			this.context.put("id_Simati", id1);
			this.context.put("IdPemohon", id2);
			this.context.put("IdPermohonan", id);
			this.context.put("flaghub", flaghub);

			hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
			context.put("hideTabPengesahan", hideTabPengesahan);

			if ("Warisview".equals(mode)) {
				String mati = id1;
				initDataWarisView();
				initListTextfieldWarisBlank();
				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);

				logic.setDataWarisLapisanIdMati(idpermohonansimati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

				ListSaudara = FrmPrmhnnSek8DaftarSek8Data.getListsaudara();
				this.context.put("listsaudara", ListSaudara);

				// listWaris = logic.getDataWaris();
				// this.context.put("listWaris",listWaris);
				// this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));

				this.context.put("readmode", "");
				this.context.put("show_tambah_button", "yes");
				this.context.put("show_kemaskini_button", "yes");
				this.context.put("show_batalinsert_button", "yes");
				this.context.put("show_table_waris", "yes");
				this.context.put("show_waris_update", "");
				this.context.put("show_tambah_waris", "yes");
				this.context.put("show_lapisan_berikut", "");
				this.context.put("show_lapisan_bawah", "yes");
				this.context.put("show_batal_waris", "");
				this.context.put("buttonwarisSimpan", "Simpan");

				// logic.setDataWarisOB();
				// Vector listWarisOB = logic.getDataWarisOB();
				// this.context.put("listWarisOB",listWarisOB);

				initBandarNegeriBaru();

			} else if ("onChangeBandarTetap".equals(mode)) {
				initListTextfieldWaris();
				initValueBandarNegeri(getParam("socNegeriTetap"),
						getParam("socDaerah"), getParam("socSuratNegeri"),
						getParam("socDaerahSurat"));

			} else if ("onChangeDuplicate".equals(mode)) {
				initListTextfieldWaris();
				initTextfieldWarisDuplicate();
				initValueBandarNegeriDuplicate(getParam("socNegeriTetap"),
						getParam("socDaerah"));

			} else if ("onChangeNotDuplicate".equals(mode)) {
				initListTextfieldWaris();
				initTextfieldWarisNotDuplicate();
				initValueBandarNegeriNotDuplicate(getParam("socNegeriTetap"),
						getParam("socDaerah"));

			} else if ("onChangeDuplicatePemohonWaris".equals(mode)) {

				initListTextfieldWaris();
				// initTextfieldWarisDuplicate();
				duplicateAddressPemohon(id2);
				// initValueBandarNegeriDuplicate(getParam("socNegeriTetap"),getParam("socDaerah"));

			} else if ("onChangeNotDuplicatePemohonWaris".equals(mode)) {
				initListTextfieldWaris();
				// initTextfieldWarisNotDuplicate();
				duplicateAddressPemohon(id2);
				initValueBandarNegeriNotDuplicate(getParam("socNegeriTetap"),
						getParam("socDaerah"));
				this.context.put("alamat1", "");
				this.context.put("alamat2", "");
				this.context.put("alamat3", "");
				this.context.put("poskod", "");
				this.context
						.put(
								"selectNegeriTetap",
								HTML
										.SelectNegeri(
												"socNegeriTetap",
												Long.parseLong("0"),
												null,
												"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
				this.context
						.put(
								"selectBandarTetap",
								HTML
										.SelectBandarByNegeri("0", "socDaerah",
												Long.parseLong("0"),
												"style=\"width:225px;text-transform:uppercase;\""));
				this.context.put("check4", "");
			} else if ("onChangeDuplicatePemohonWarisLapis".equals(mode)) {

				initListTextfieldWaris();
				// initTextfieldWarisDuplicate();
				duplicateAddressPemohon(id2);
				// initValueBandarNegeriDuplicate(getParam("socNegeriTetap"),getParam("socDaerah"));

			} else if ("onChangeNotDuplicatePemohonWarisLapis".equals(mode)) {
				initListTextfieldWaris();
				// initTextfieldWarisNotDuplicate();
				duplicateAddressPemohon(id2);
				// initValueBandarNegeriNotDuplicate(getParam("socNegeriTetap"),getParam("socDaerah"));
				this.context.put("check4", "");
			} else if ("onChangeBandarTetapWaris".equals(mode)) {
				String idp = getParam("idparentlapis");
				initButtonTambahWaris(idp);
				// logic.setDataWarisParent(idp);
				// Vector listWarisParent = logic.getDataWarisParent();
				// this.context.put("listWarisParent",listWarisParent);
				// initPageWarisLapisan();
				initTabLapisan();
				initListTextfieldWaris();
				initValueBandarNegeriWaris(getParam("socNegeriTetap"),
						getParam("socDaerah"), getParam("socSuratNegeri"),
						getParam("socDaerahSurat"));

			} else if ("onChangeDuplicateWaris".equals(mode)) {
				String idp = getParam("idparentlapis");
				initButtonTambahWaris(idp);
				// logic.setDataWarisParent(idp);
				// Vector listWarisParent = logic.getDataWarisParent();
				// this.context.put("listWarisParent",listWarisParent);
				// initPageWarisLapisan();
				// initPageWarisLapisanUpdate();
				initListTextfieldWaris();
				initTextfieldWarisDuplicate();
				initValueBandarNegeriDuplicateWaris(getParam("socNegeriTetap"),
						getParam("socDaerah"));
			} else if ("onChangeNotDuplicateWaris".equals(mode)) {
				// int idp=Integer.parseInt(getParam("idparentlapis"));
				// initButtonTambahWaris(idp);
				// logic.setDataWarisParent(idp);
				// Vector listWarisParent = logic.getDataWarisParent();
				// this.context.put("listWarisParent",listWarisParent);
				// initPageWarisLapisan();
				initPageWarisLapisanUpdate();
				initListTextfieldWaris();
				initTextfieldWarisNotDuplicate();
				initValueBandarNegeriNotDuplicate(getParam("socNegeriTetap"),
						getParam("socDaerah"));
			} else if ("Newwaris".equals(mode)) {
				id = (String) getParam("idPermohonan");
				id2 = (String) getParam("idPemohon");
				String mati = (String) getParam("idSimati");

				this.context.put("checkmati", "0");

				ListSaudara = FrmPrmhnnSek8DaftarSek8Data.getListsaudara();
				this.context.put("listsaudara", ListSaudara);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);

				String kpBaru = getParam("txtNoKPBaru1Waris")
						+ getParam("txtNoKPBaru2Waris")
						+ getParam("txtNoKPBaru3Waris");
				String kpLama = getParam("txtNoKPLamaWaris");
				String kpJenis = getParam("socJenisKPLainWaris");
				String kpLain = getParam("txtNoKPLainWaris");

				if (!kpBaru.equals("") || !kpLama.equals("")
						|| !kpJenis.equals("") || !kpLain.equals("")) {
					int cntDataExist = logiconline.getCountWaris(kpBaru,
							kpLama, kpJenis, kpLain, getParam("idPermohonan"));
					if (cntDataExist > 0) {
						Vector dataWarisExist = logiconline.checkwaris(kpBaru,
								kpLama, kpJenis, kpLain,
								getParam("idPermohonan"));
						Hashtable b = (Hashtable) dataWarisExist.get(0);

						String idwaris = (String) b.get("idOb");
						logic.setDataWarisUpdate(idwaris);
						Vector listWarisUpdate = logic.getDataWarisUpdate();

						this.context.put("show_waris_update", "yes");
						this.context.put("show_table_waris", "");
						this.context.put("buttonwaris", "Kemaskini");
						this.context.put("buttonwarisSimpan", "");
						this.context.put("listWarisUpdate", listWarisUpdate);
						this.context.put("show_batal_waris", "");
						this.context.put("show_tambah_waris", "yes");
						this.context.put("readmode", disability1);
						this.context.put("show_lapisan_berikut", "");
						this.context.put("show_lapisan_bawah", "");
					} else {
						this.context.put("nokpbaru1",
								getParam("txtNoKPBaru1Waris"));
						this.context.put("nokpbaru2",
								getParam("txtNoKPBaru2Waris"));
						this.context.put("nokpbaru3",
								getParam("txtNoKPBaru3Waris"));
						this.context.put("nokpwaris",
								getParam("txtNoKPLamaWaris"));
						this.context.put("jenisKp",
								getParam("socJenisKPLainWaris"));
						this.context.put("nokplain",
								getParam("txtNoKPLainWaris"));
						this.context.put("umur", getParam("txtUmurWaris"));

						this.context.put("buttonwarisSimpan", "Simpan");
						this.context.put("listWaris", listWaris);
						this.context.put("readmode", "");
						this.context.put("show_tambah_button", "yes");
						this.context.put("show_kemaskini_button", "yes");
						this.context.put("show_batalinsert_button", "");
						this.context.put("show_lapisan_bawah", "");
						this.context.put("show_table_waris", "yes");
						this.context.put("show_waris_update", "");
						this.context.put("show_batal_waris", "");
						this.context.put("show_lapisan_berikut", "");
						this.context.put("show_lapisan_bawah", "");

					}
				}

				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);
			} else if ("Waristarikh_lapisan_update".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				String idparent = getParam("idparentlapis");
				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				Vector listWarisup = new Vector();

				Hashtable h = new Hashtable();

				if (getParam("checkHidupWaris").equals("")) {
					h.put("statushidup", "0");

				} else {
					h.put("statushidup", "1");

				}

				h.put("idwaris", Integer.parseInt(getParam("idwarisup")));
				h.put("idSimati", Integer
						.parseInt(getParam("txtIdSimatiWaris")));
				h.put("nama_Ob", getParam("txtNamaOBWaris"));

				String kp_Waris = getParam("txtNoKPBaru1Waris")
						+ getParam("txtNoKPBaru2Waris")
						+ getParam("txtNoKPBaru3Waris");

				h.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				h.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				h.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));
				h.put("nokplama", getParam("txtNoKPLamaWaris"));
				h.put("noTel", getParam("txtNoTeleponWaris"));
				h.put("jeniskp", getParam("socJenisKPLainWaris"));
				h.put("nokplain", getParam("txtNoKPLainWaris"));

				this.context.put("umur", "txtUmurWaris");
				this.context.put("dob", "txdTarikhLahirWaris");
				this.context.put("noberanak", "txtNoSuratBeranakWaris");

				h.put("jantina", getParam("socJantinaWaris"));
				h.put("agama", getParam("socAgamaWaris"));
				h.put("warga", getParam("socWarganegaraWaris"));
				h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				h.put("poskod", getParam("txtPoskodWaris"));
				h.put("bandar", getParam("txtBandarWaris"));
				h.put("umur", getParam("txtUmurWaris"));
				h.put("dob", getParam("txdTarikhLahirWaris"));
				h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
				if (getParam("socNegeriWaris").equals("")) {
					h.put("negeri", 0);
				} else {
					h.put("negeri", Integer
							.parseInt(getParam("socNegeriWaris")));
				}
				h.put("catatan", getParam("txtCatatanWaris"));
				if (getParam("socSaudaraWaris").equals("")) {
					h.put("saudara", 0);
				} else {
					h.put("saudara", Integer
							.parseInt(getParam("socSaudaraWaris")));
				}
				if (getParam("socStatusOBWaris").equals("")) {
					h.put("statusWaris", 0);
				} else {
					h.put("statusWaris", Integer
							.parseInt(getParam("socStatusOBWaris")));
				}
				h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				h.put("waktumati", getParam("txtWaktuKematianWaris"));
				h.put("nohp", getParam("txtNoTeleponBimbitWaris"));
				listWarisup.addElement(h);

				this.context.put("listWarisLapisanUpdate", listWarisup);
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_tambah", "");
				this.context.put("show_lapisan_berikut_update", "yes");

				this.context.put("buttonwarislapisan", "Simpan");
				this.context.put("show_batal_waris_lapisan", "");
				this.context.put("buttonwarislapisanSimpan", "");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);
			} else if ("Waristarikh_lapisan_updatesaudara".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				String idparent = getParam("idparentlapis");
				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				Vector listWarisup = new Vector();
				Hashtable h = new Hashtable();
				if (getParam("checkHidupWaris").equals("")) {
					h.put("statushidup", "0");
				} else {
					h.put("statushidup", "1");
				}

				h.put("idwaris", Integer.parseInt(getParam("idwarisup")));
				h.put("idSimati", Integer
						.parseInt(getParam("txtIdSimatiWaris")));
				h.put("nama_Ob", getParam("txtNamaOBWaris"));

				String kp_Waris = getParam("txtNoKPBaru1Waris")
						+ getParam("txtNoKPBaru2Waris")
						+ getParam("txtNoKPBaru3Waris");

				h.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				h.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				h.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));

				h.put("nokplama", getParam("txtNoKPLamaWaris"));
				h.put("noTel", getParam("txtNoTeleponWaris"));

				h.put("jeniskp", getParam("socJenisKPLainWaris"));
				h.put("nokplain", getParam("txtNoKPLainWaris"));

				this.context.put("umur", "txtUmurWaris");

				this.context.put("dob", "txdTarikhLahirWaris");
				this.context.put("noberanak", "txtNoSuratBeranakWaris");

				h.put("jantina", getParam("socJantinaWaris"));
				h.put("agama", getParam("socAgamaWaris"));
				h.put("warga", getParam("socWarganegaraWaris"));
				h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				h.put("poskod", getParam("txtPoskodWaris"));
				h.put("bandar", getParam("txtBandarWaris"));
				h.put("umur", getParam("txtUmurWaris"));
				h.put("dob", getParam("txdTarikhLahirWaris"));
				h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
				h.put("idnegeri", getParam("socNegeriWaris"));
				h.put("catatan", getParam("txtCatatanWaris"));
				h.put("saudara", "");

				if (getParam("socStatusOBWaris").equals("")) {
					h.put("status_Ob", 0);
				} else {
					h.put("status_Ob", Integer
							.parseInt(getParam("socStatusOBWaris")));
				}
				h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				h.put("waktumati", getParam("txtWaktuKematianWaris"));
				h.put("nohp", getParam("txtNoTeleponBimbitWaris"));
				listWarisup.addElement(h);

				this.context.put("listWarisLapisanUpdate", listWarisup);
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_tambah", "");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("buttonwarislapisan", "Simpan");
				this.context.put("show_batal_waris_lapisan", "");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);
			} else if ("Waristarikh_update".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				Vector listWarisup = new Vector();
				this.context.put("show_table_waris", "");
				this.context.put("show_waris_update", "yes");

				Hashtable h = new Hashtable();
				h.put("idwaris", getParam("idwarisup"));
				h.put("idSimati", getParam("txtIdSimatiWaris"));
				h.put("nama_Ob", getParam("txtNamaOBWaris"));

				String kp_Waris = getParam("txtNoKPBaru1Waris")
						+ getParam("txtNoKPBaru2Waris")
						+ getParam("txtNoKPBaru3Waris");

				h.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				h.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				h.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));

				h.put("nokplama", getParam("txtNoKPLamaWaris"));
				h.put("noTel", getParam("txtNoTeleponWaris"));

				h.put("jeniskp", getParam("socJenisKPLainWaris"));
				h.put("nokplain", getParam("txtNoKPLainWaris"));
				this.context.put("umur", "txtUmurWaris");
				this.context.put("dob", "txdTarikhLahirWaris");
				this.context.put("noberanak", "txtNoSuratBeranakWaris");
				// h.put("taraf",
				// Integer.parseInt(getParam("socTarafKepentinganPenting")));
				h.put("jantina", getParam("socJantinaWaris"));
				h.put("agama", getParam("socAgamaWaris"));
				h.put("warga", getParam("socWarganegaraWaris"));

				h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				h.put("poskod", getParam("txtPoskodWaris"));
				h.put("bandar", getParam("txtBandarWaris"));
				h.put("umur", getParam("txtUmurWaris"));
				h.put("dob", getParam("txdTarikhLahirWaris"));
				h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
				h.put("catatan", getParam("txtCatatanWaris"));

				if (getParam("socSaudaraWaris").equals("")) {
					h.put("saudara", 0);
				} else {
					h.put("saudara", Integer
							.parseInt(getParam("socSaudaraWaris")));
				}

				if (getParam("socStatusOBWaris").equals("")) {
					h.put("statusWaris", 0);
				} else {
					h.put("statusWaris", Integer
							.parseInt(getParam("socStatusOBWaris")));
				}

				if (getParam("checkHidupWaris").equals("")) {
					h.put("statushidup", "0");
					this.context.put("show_lapisan_waris", "");

				} else {
					h.put("statushidup", "1");
					this.context.put("show_lapisan_waris", "yes");

				}
				h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				h.put("waktumati", getParam("txtWaktuKematianWaris"));
				h.put("nohp", getParam("txtNoTeleponBimbitWaris"));
				listWarisup.addElement(h);

				this.context.put("listWarisUpdate", listWarisup);

				this.context.put("show_batal_waris", "");
				this.context.put("show_lapisan_berikut", "");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);
			}

			else if ("Waristarikh_updatesaudara".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				Vector listWarisup = new Vector();
				this.context.put("show_table_waris", "");
				this.context.put("show_waris_update", "yes");

				Hashtable h = new Hashtable();
				h.put("idwaris", getParam("idwarisup"));
				h.put("idSimati", getParam("txtIdSimatiWaris"));
				h.put("nama_Ob", getParam("txtNamaOBWaris"));

				String kp_Waris = getParam("txtNoKPBaru1Waris")
						+ getParam("txtNoKPBaru2Waris")
						+ getParam("txtNoKPBaru3Waris");

				h.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				h.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				h.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));

				h.put("nokplama", getParam("txtNoKPLamaWaris"));
				h.put("noTel", getParam("txtNoTeleponWaris"));

				h.put("jeniskp", getParam("socJenisKPLainWaris"));
				h.put("nokplain", getParam("txtNoKPLainWaris"));

				this.context.put("umur", "txtUmurWaris");

				this.context.put("dob", "txdTarikhLahirWaris");
				this.context.put("noberanak", "txtNoSuratBeranakWaris");
				// h.put("taraf",
				// Integer.parseInt(getParam("socTarafKepentinganPenting")));
				h.put("jantina", getParam("socJantinaWaris"));
				h.put("agama", getParam("socAgamaWaris"));
				h.put("warga", getParam("socWarganegaraWaris"));

				h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				h.put("poskod", getParam("txtPoskodWaris"));
				h.put("bandar", getParam("txtBandarWaris"));

				h.put("umur", getParam("txtUmurWaris"));

				h.put("dob", getParam("txdTarikhLahirWaris"));
				h.put("noberanak", getParam("txtNoSuratBeranakWaris"));

				if (getParam("socNegeriWaris").equals("")) {
					h.put("idnegeri", "");
				} else {
					h.put("idnegeri", Integer
							.parseInt(getParam("socNegeriWaris")));
				}

				// h.put("negeri",
				// Integer.parseInt(getParam("socNegeriPenting")));
				h.put("catatan", getParam("txtCatatanWaris"));
				h.put("saudara", "");

				if (getParam("socStatusOBWaris").equals("")) {
					h.put("statusWaris", 0);
				} else {
					h.put("statusWaris", Integer
							.parseInt(getParam("socStatusOBWaris")));
				}

				if (getParam("checkHidupWaris").equals("")) {
					h.put("statushidup", "0");
					this.context.put("show_lapisan_waris", "");

				} else {
					h.put("statushidup", "1");
					this.context.put("show_lapisan_waris", "yes");

				}
				h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				h.put("waktumati", getParam("txtWaktuKematianWaris"));

				h.put("nohp", getParam("txtNoTeleponBimbitWaris"));

				listWarisup.addElement(h);

				this.context.put("listWarisUpdate", listWarisup);

				this.context.put("show_batal_waris", "");
				this.context.put("show_lapisan_berikut", "");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			} else if ("Waristarikh".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				this.context
						.put("selectNegeri", HTML.SelectNegeri("socNegeri"));

				ListSaudara = FrmPrmhnnSek8DaftarSek8Data.getListsaudara();
				this.context.put("listsaudara", ListSaudara);

				System.out.println("checkHidupWaris"
						+ getParam("checkHidupWaris"));

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("checkmati", "0");
					this.context.put("show_tarikh", "");
				} else {
					this.context.put("checkmati", "1");
					this.context.put("show_tarikh", "yes");
				}

				this.context.put("idSimati", Integer
						.parseInt(getParam("txtIdSimatiWaris")));
				this.context.put("namaOB", getParam("txtNamaOBWaris"));
				this.context.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				this.context.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				this.context.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));

				if (getParam("txtUmurWaris").equals("")) {
					this.context.put("umur", "");
				} else {
					this.context.put("umur", getParam("txtUmurWaris"));
				}
				this.context.put("dob", getParam("txdTarikhLahirWaris"));
				this.context.put("noberanak",
						getParam("txtNoSuratBeranakWaris"));
				this.context.put("nokpwaris", getParam("txtNoKPLamaWaris"));
				this.context.put("notel", getParam("txtNoTeleponWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("jenisKp", getParam("socJenisKPLainWaris"));
				this.context.put("nokplain", getParam("txtNoKPLainWaris"));
				this.context.put("jantina", getParam("socJantinaWaris"));
				this.context.put("agama", getParam("socAgamaWaris"));
				this.context.put("warga", getParam("socWarganegaraWaris"));
				this.context
						.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				this.context
						.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				this.context
						.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				this.context.put("poskod", getParam("txtPoskodWaris"));
				this.context.put("bandar", getParam("txtBandarWaris"));

				if (getParam("socNegeriWaris").equals("")) {
					this.context.put("negeri", "");
				} else {
					this.context.put("negeri", getParam("socNegeriWaris"));
				}

				this.context.put("catatan", getParam("txtCatatanWaris"));
				if (getParam("socSaudaraWaris").equals("")) {
					this.context.put("saudara", "");
				} else {
					this.context.put("saudara", getParam("socSaudaraWaris"));
				}
				if (getParam("socStatusOBWaris").equals("")) {
					this.context.put("statusWaris", "");
				} else {
					this.context.put("statusWaris",
							getParam("socStatusOBWaris"));
				}
				this.context.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				this.context.put("waktumatiwaris",
						getParam("txtWaktuKematianWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("show_lapisan_berikut", "");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			} else if ("Waristarikhsaudara".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("checkmati", "0");
					this.context.put("show_tarikh", "");
				} else {
					this.context.put("checkmati", "1");
					this.context.put("show_tarikh", "yes");

				}

				this.context.put("idSimati", getParam("txtIdSimatiWaris"));
				this.context.put("namaOB", getParam("txtNamaOBWaris"));
				this.context.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				this.context.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				this.context.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));

				if (getParam("txtUmurWaris").equals("")) {
					this.context.put("umur", "");
				} else {
					this.context.put("umur", getParam("txtUmurWaris"));
				}
				this.context.put("dob", getParam("txdTarikhLahirWaris"));
				this.context.put("noberanak",
						getParam("txtNoSuratBeranakWaris"));
				this.context.put("nokpwaris", getParam("txtNoKPLamaWaris"));
				this.context.put("notel", getParam("txtNoTeleponWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("jenisKp", getParam("socJenisKPLainWaris"));
				this.context.put("nokplain", getParam("txtNoKPLainWaris"));
				this.context.put("jantina", getParam("socJantinaWaris"));
				this.context.put("agama", getParam("socAgamaWaris"));
				this.context.put("warga", getParam("socWarganegaraWaris"));
				this.context
						.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				this.context
						.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				this.context
						.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				this.context.put("poskod", getParam("txtPoskodWaris"));
				this.context.put("bandar", getParam("txtBandarWaris"));

				if (getParam("socNegeriWaris").equals("")) {
					this.context.put("negeri", "");
				} else {
					this.context.put("negeri", getParam("socNegeriWaris"));
				}

				this.context.put("catatan", getParam("txtCatatanWaris"));
				if (getParam("socSaudaraWaris").equals("")) {
					this.context.put("saudara", "");
				} else {
					this.context.put("saudara", "");
				}
				if (getParam("socStatusOBWaris").equals("")) {
					this.context.put("statusWaris", "");
				} else {
					this.context.put("statusWaris",
							getParam("socStatusOBWaris"));
				}
				this.context.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				this.context.put("waktumatiwaris",
						getParam("txtWaktuKematianWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("show_lapisan_berikut", "");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			} else if ("Waristarikh_lapisan".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				String idparent = getParam("idparentlapis");
				String idwarisup = getParam("idwarisup");

				this.context.put("id_Simati", getParam("txtIdSimatiWaris"));
				this.context.put("idparentlapis", getParam("idparentlapis"));
				this.context.put("idwww", getParam("idwarislapis"));
				this.context.put("ip", getParam("idwarisup"));
				this.context.put("idwarisup", getParam("idwarisup"));

				logic.setDataWarisLapisan(idwarisup);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				logic.setDataWarisParent(idwarisup);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("checkmati", "0");
					this.context.put("show_tarikh", "");
				} else {
					this.context.put("checkmati", "1");
					this.context.put("show_tarikh", "yes");

				}
				this.context.put("id_Simati", getParam("txtIdSimatiWaris"));
				this.context.put("namaOB", getParam("txtNamaOBWaris"));

				this.context.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				this.context.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				this.context.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));
				this.context.put("dob", getParam("txdTarikhLahirWaris"));
				this.context.put("noberanak",
						getParam("txtNoSuratBeranakWaris"));
				this.context.put("nokpwaris", getParam("txtNoKPLamaWaris"));
				this.context.put("notel", getParam("txtNoTeleponWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("jenisKp", getParam("socJenisKPLainWaris"));
				this.context.put("nokplain", getParam("txtNoKPLainWaris"));
				this.context.put("jantina", getParam("socJantinaWaris"));
				this.context.put("agama", getParam("socAgamaWaris"));
				this.context.put("warga", getParam("socWarganegaraWaris"));
				this.context
						.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				this.context
						.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				this.context
						.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				this.context.put("poskod", getParam("txtPoskodWaris"));
				this.context.put("bandar", getParam("txtBandarWaris"));

				if (getParam("txtUmurWaris").equals("")) {
					this.context.put("umur", "");
				} else {
					this.context.put("umur", getParam("txtUmurWaris"));
				}

				if (getParam("socNegeriWaris").equals("")) {
					this.context.put("negeri", "");
				} else {
					this.context.put("negeri", getParam("socNegeriWaris"));
				}

				this.context.put("catatan", getParam("txtCatatanWaris"));
				if (getParam("socSaudaraWaris").equals("")) {
					this.context.put("saudara", "");
				} else {
					this.context.put("saudara", getParam("socSaudaraWaris"));
				}
				if (getParam("socStatusOBWaris").equals("")) {
					this.context.put("statusWaris", "");
				} else {
					this.context.put("statusWaris",
							getParam("socStatusOBWaris"));
				}
				this.context.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				this.context.put("waktumatiwaris",
						getParam("txtWaktuKematianWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_lapisan_berikut_tambah", "yes");
				this.context.put("buttonwarislapisan", "");
				this.context.put("buttonwarislapisanSimpan", "Simpan");
				this.context.put("show_lapisan_berikut_update", "");
				this.context.put("show_button_lapisan", "yes");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			} else if ("Waristarikh_lapisansaudara".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				String idparent = getParam("idparentlapis");

				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("checkmati", "0");
					this.context.put("show_tarikh", "");
				} else {
					this.context.put("checkmati", "1");
					this.context.put("show_tarikh", "yes");
				}
				this.context.put("id_Simati", getParam("txtIdSimatiWaris"));
				this.context.put("namaOB", getParam("txtNamaOBWaris"));

				this.context.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				this.context.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				this.context.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));
				this.context.put("dob", getParam("txdTarikhLahirWaris"));
				this.context.put("noberanak",
						getParam("txtNoSuratBeranakWaris"));
				this.context.put("nokpwaris", getParam("txtNoKPLamaWaris"));
				this.context.put("notel", getParam("txtNoTeleponWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("jenisKp", getParam("socJenisKPLainWaris"));
				this.context.put("nokplain", getParam("txtNoKPLainWaris"));
				this.context.put("jantina", getParam("socJantinaWaris"));
				this.context.put("agama", getParam("socAgamaWaris"));
				this.context.put("warga", getParam("socWarganegaraWaris"));
				this.context
						.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				this.context
						.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				this.context
						.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				this.context.put("poskod", getParam("txtPoskodWaris"));
				this.context.put("bandar", getParam("txtBandarWaris"));

				if (getParam("txtUmurWaris").equals("")) {
					this.context.put("umur", "");
				} else {
					this.context.put("umur", getParam("txtUmurWaris"));
				}

				if (getParam("socNegeriWaris").equals("")) {
					this.context.put("negeri", "");
				} else {
					this.context.put("negeri", getParam("socNegeriWaris"));
				}

				this.context.put("catatan", getParam("txtCatatanWaris"));

				this.context.put("saudara", "");

				if (getParam("socStatusOBWaris").equals("")) {
					this.context.put("statusWaris", "");
				} else {
					this.context.put("statusWaris",
							getParam("socStatusOBWaris"));
				}
				this.context.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				this.context.put("waktumatiwaris",
						getParam("txtWaktuKematianWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_lapisan_berikut_tambah", "yes");
				this.context.put("buttonwarislapisan", "Tambah");
				this.context.put("show_lapisan_berikut_update", "");
				this.context.put("show_button_lapisan", "yes");
				;
				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			} else if ("Getwaris".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				String idwaris = "0";
				if (getParam("idwaris").equals("")) {
					idwaris = "0";
				} else {
					idwaris = getParam("idwaris");
				}

				this.context.put("idparentlapis", idwaris);

				initListTextfieldWarisBlank();

				logic.setDataWarisUpdate(idwaris);
				Vector listWarisUpdate = logic.getDataWarisUpdate();
				Hashtable t = (Hashtable) listWarisUpdate.get(0);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);

				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);
				this.context.put("show_waris_update", "yes");
				this.context.put("show_table_waris", "");
				this.context.put("buttonwaris", "Kemaskini");
				this.context.put("buttonwarisSimpan", "");
				this.context.put("listWarisUpdate", listWarisUpdate);

				this.context.put("show_batal_waris", "");
				this.context.put("show_tambah_waris", "yes");
				this.context.put("readmode", disability1);
				this.context.put("show_lapisan_berikut", "");
				this.context.put("show_lapisan_bawah", "");

				logic.setDataWarisLapisanIdMatiDelete(idwaris);
				Vector listWarisLapisanIdMatiDelete = logic
						.getDataWarisLapisanIdMatiDelete();

				this.context.put("listWarisLapisanIdMatiDelete",
						listWarisLapisanIdMatiDelete);

				initDeclareDataBandarNegeri(t.get("idnegeri").toString(), t
						.get("idbandar").toString(), t.get("idnegerisurat")
						.toString(), t.get("idbandarsurat").toString());

			}

			else if ("Lapisan_berikut".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				// int id_simati=Integer.parseInt(getParam("txtIdSimatiWaris"));
				String idwaris = getParam("idwarisup");

				this.context.put("idparent", idwaris);
				this.context.put("ip", idwaris);
				this.context.put("idww", idwaris);
				this.context.put("idwarisup", idwaris);

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_lapisan_berikut_tambah", "yes");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("buttonwarislapisanSimpan", "Simpan");

				logic.setDataWarisLapisan(idwaris);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				logic.setDataWarisParent(idwaris);
				Vector listWarisParent = logic.getDataWarisParent();

				this.context.put("readmode", "");
				this.context.put("listWa", listWarisParent);

				logic.setDataWarisParent(idwaris);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);
			}

			else if ("Lapisan_berikut_lapisan".equals(mode)) {
				initListTextfieldWarisBlank();
				this.context.put("buttonwarislapisan", "Kemaskini");

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				this.context.put("idwww", getParam("idwarislapis"));
				this.context.put("idparentlapis", getParam("idparentlapis"));
				this.context.put("ip", getParam("idparentlapis"));
				this.context.put("idwarisup", getParam("idwarisup"));

				String idwaris1 = getParam("idwarislapis");

				logic.setDataWarisUpdate(idwaris1);
				Vector listWarisUpdate1 = logic.getDataWarisUpdate();
				this.context.put("listWarisLapisanUpdate", listWarisUpdate1);

				String idwaris = getParam("idwarisup");
				this.context.put("show_lapisan_berikut", "");
				logic.setDataWarisLapisan(idwaris);

				logic.setDataWarisParent(idwaris);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				Vector listWarisLapisan = logic.getDataWarisLapisan();

				this.context.put("listWarisLapisan", listWarisLapisan);

				logic.setDataWarisParent(idwaris);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWa", listWarisParent);

				this.context.put("readmode", "");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_lapisan_berikut_tambah", "yes");
				this.context.put("buttonwarislapisanSimpan", "Simpan");
				this.context.put("buttonwarislapisan", "Simpan");

				this.context.put("show_lapisan_berikut_update", "");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_bawah", "");

			} else if ("Lapisan_sebelum_lapisan".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				String idwaris = getParam("idparentlapis");
				// int idwaris=239;
				this.context.put("show_lapisan_berikut", "yes");

				this.context.put("idparent", idwaris);

				logic.setDataWarisLapisan(idwaris);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("show_lapisan_berikut_tambah", "");
				this.context.put("show_lapisan_berikut_update", "");
				this.context.put("show_button_lapisan", "");

				this.context.put("listWarisLapisan", listWarisLapisan);

				logic.setDataWarisParent(idwaris);
				Vector listWarisParent = logic.getDataWarisParent();

				this.context.put("readmode", "");
				this.context.put("listWa", listWarisParent);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			} else if ("Lapisan_sebelum".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				String idwaris = getParam("txtNamaOBWaris");
				String lapis = getParam("txtNamaOBWaris");

				this.context.put("readmode", disability1);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			} else if ("tambah_waris_lapisan".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				String idparent = getParam("idparentlapis");
				String idwaris = getParam("idwarisup");

				this.context.put("idparentlapis", idparent);
				this.context.put("idwarisup", idwaris);

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_lapisan_berikut_tambah", "yes");
				this.context.put("buttonwarislapisanSimpan", "Simpan");
				this.context.put("buttonwarislapisan", "Simpan");
				this.context.put("show_lapisan_berikut_update", "");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_bawah", "");

				this.context.put("show_tarikh", "");
				this.context.put("listpenting", "");
				this.context.put("readmode", "");

				String kpBaru = getParam("txtNoKPBaru1Waris")
						+ getParam("txtNoKPBaru2Waris")
						+ getParam("txtNoKPBaru3Waris");
				String kpLama = getParam("txtNoKPLamaWaris");
				String kpJenis = getParam("socJenisKPLainWaris");
				String kpLain = getParam("txtNoKPLainWaris");

				if (!kpBaru.equals("") || !kpLama.equals("")
						|| !kpJenis.equals("") || !kpLain.equals("")) {
					int cntDataExist = logiconline.getCountWarisLapisan(kpBaru,
							kpLama, kpJenis, kpLain, getParam("idPermohonan"));
					if (cntDataExist == 0) {
						addWarisBerikut(session);
					} else {
						updatewaris(session);
					}
				}

				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}

				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				logic.setDataWaris(idwaris);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);

				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

				logic.setDataWarisParent(idparent);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				initListTextfieldWarisBlank();
				// initDeclareDataBandarNegeri(String negeriTetap, String
				// daerahTetap, String negeriSurat, String daerahSurat)
			} else if ("GetwarisLapisan".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				System.out.println("mati---" + mati);

				this.context.put("idwww", getParam("idwarislapis"));
				this.context.put("idparentlapis", getParam("idparentlapis"));
				this.context.put("ip", getParam("idparentlapis"));
				this.context.put("idwarisup", getParam("idwarisup"));

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_tambah", "");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("buttonwarislapisan", "Kemaskini");
				this.context.put("buttonwarislapisanSimpan", "");
				this.context.put("show_batal_waris_lapisan", "");
				this.context.put("show_lapisan_bawah", "");

				this.context.put("readmode", disability1);

				String idwaris = getParam("idwarislapis");
				String idparent = getParam("idparentlapis");

				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();

				logic.setDataWarisUpdate(idwaris);
				Vector listWarisUpdate = logic.getDataWarisUpdate();
				Hashtable h = (Hashtable) listWarisUpdate.get(0);

				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();

				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				this.context.put("listWarisParent", listWarisParent);
				logic.setDataWarisLapisanIdMatiDelete(idwaris);

				Vector listWarisLapisanIdMatiDelete = logic
						.getDataWarisLapisanIdMatiDelete();

				this.context.put("listWarisLapisanIdMatiDelete",
						listWarisLapisanIdMatiDelete);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);
				initDeclareDataBandarNegeri(h.get("idnegeri").toString(), h
						.get("idbandar").toString(), h.get("idnegerisurat")
						.toString(), h.get("idbandarsurat").toString());

			} else if ("Lapisan_waris_sebelum".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_tambah", "");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("buttonwarislapisan", "Kemaskini");
				this.context.put("buttonwarislapisanSimpan", "");
				this.context.put("show_batal_waris_lapisan", "");
				this.context.put("show_lapisan_bawah", "");

				this.context.put("readmode", disability1);

				String idparent = getParam("idparentlapiss");

				Vector getValue = logiconline.setDataWarisSebelum(idparent,
						mati);
				Hashtable h = (Hashtable) getValue.get(0);
				String idwaris = (String) h.get("idwaris");
				String idparents = (String) h.get("idparentob");

				this.context.put("idwww", idwaris);
				this.context.put("idparentlapis", idparents);
				this.context.put("ip", idparents);
				this.context.put("idwarisup", idwaris);

				logic.setDataWarisLapisan(idparents);
				Vector listWarisLapisan = logic.getDataWarisLapisan();

				logic.setDataWarisUpdate(idwaris);
				Vector listWarisUpdate = logic.getDataWarisUpdate();

				logic.setDataWarisParent(idparents);
				Vector listWarisParent = logic.getDataWarisParent();

				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				this.context.put("listWarisParent", listWarisParent);

				logic.setDataWarisLapisanIdMatiDelete(idwaris);

				Vector listWarisLapisanIdMatiDelete = logic
						.getDataWarisLapisanIdMatiDelete();

				this.context.put("listWarisLapisanIdMatiDelete",
						listWarisLapisanIdMatiDelete);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);
			}

			else if ("hapus_waris_lapisan".equals(mode)) {
				id = (String) getParam("idPermohonan");
				id2 = (String) getParam("idPemohon");
				String mati = getParam("idSimati");

				this.context.put("idparentlapis", getParam("idparentlapis"));
				this.context.put("idwww", getParam("idwarislapis"));
				this.context.put("ip", getParam("idparentlapis"));
				this.context.put("idwarisup", getParam("idwarisup"));
				this.context.put("show_tambah_button", "yes");

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_lapisan_berikut_tambah", "yes");
				this.context.put("buttonwarislapisanSimpan", "Simpan");
				this.context.put("buttonwarislapisan", "Simpan");
				this.context.put("show_lapisan_berikut_update", "");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_bawah", "");
				this.context.put("show_tarikh", "");
				this.context.put("listpenting", "");
				this.context.put("readmode", "");

				initListTextfieldWarisBlank();

				String idwarisup = getParam("idwarisup");
				String idparent = getParam("idparentlapiss");

				FrmPrmhnnSek8Data.deleteWarisHubungan(idwarisup);
				FrmPrmhnnSek8Data.deleteWaris(idwarisup);

				// this.context.put("buttonwarislapisan","Tambah");
				this.context.put("buttonwarislapisanSimpan", "Simpan");
				this.context.put("show_batal_waris_lapisan", "");

				this.context.put("idparent", getParam("idwarisup"));

				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				if (getParam("checkHidupWaris").equals("")) {

					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}

				logic.setDataWarisOB();
				Vector listWarisOB = logic.getDataWarisOB();
				this.context.put("listWarisOB", listWarisOB);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			}

			else if ("GetwarisLapisanX".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				System.out.println("mati---" + mati);

				this.context.put("idparentlapis", getParam("idparentlapisX"));
				this.context.put("idwww", getParam("idwarislapisX"));
				this.context.put("ip", getParam("idparentlapisX"));
				this.context.put("idwarisup", getParam("idwarislapisX"));

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_button_lapisan", "yes");

				this.context.put("show_lapisan_berikut_tambah", "");
				this.context.put("show_lapisan_berikut_update", "yes");

				this.context.put("buttonwarislapisan", "Kemaskini");
				this.context.put("buttonwarislapisanSimpan", "");
				this.context.put("show_batal_waris_lapisan", "");

				String idwaris = getParam("idwarislapisX");
				String idparent = getParam("idparentlapisX");
				this.context.put("idwww", getParam("idwarislapisX"));

				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				logic.setDataWarisUpdate(idwaris);
				Vector listWarisUpdate = logic.getDataWarisUpdate();
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				Hashtable h = (Hashtable) listWarisUpdate.get(0);

				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				this.context.put("readmode", disability1);
				this.context.put("show_lapisan_bawah", "");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);
				initDeclareDataBandarNegeri(h.get("idnegeri").toString(), h
						.get("idbandar").toString(), h.get("idnegerisurat")
						.toString(), h.get("idbandarsurat").toString());

			} else if ("Tambah_lapisan_berikut".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				// int idwaris=Integer.parseInt(getParam("idwarislapis"));
				String idp = getParam("idparentlapis");
				// int idp=Integer.parseInt(getParam("idwarisup"));
				this.context.put("idwww", idp);
				this.context.put("idparentlapis", idp);

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_lapisan_berikut_tambah", "yes");
				this.context.put("buttonwarislapisanSimpan", "Simpan");
				this.context.put("buttonwarislapisan", "Simpan");

				initTabLapisan();
				initListTextfieldWarisBlank();

				initBandarNegeriBaruWaris();

				Vector listWarisup = new Vector();
				Hashtable h = new Hashtable();
				h.put("statushidup", "0");
				listWarisup.addElement(h);
				this.context.put("listWarisLapisanUpdate", listWarisup);

				// this.context.put("idparent",idwaris);

				logic.setDataWarisParent(idp);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);

				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

				logic.setDataWarisLapisan(idp);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				this.context.put("readmode", "");

			} else if ("tambah_waris".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				String kpBaru = getParam("txtNoKPBaru1Waris")
						+ getParam("txtNoKPBaru2Waris")
						+ getParam("txtNoKPBaru3Waris");
				String kpLama = getParam("txtNoKPLamaWaris");
				String kpJenis = getParam("socJenisKPLainWaris");
				String kpLain = getParam("txtNoKPLainWaris");

				if (!kpBaru.equals("") || !kpLama.equals("")
						|| !kpJenis.equals("") || !kpLain.equals("")) {
					int cntDataExist = logiconline.getCountWaris(kpBaru,
							kpLama, kpJenis, kpLain, getParam("idPermohonan"));
					if (cntDataExist == 0) {
						addWaris(session);
					}
				}
				this.context.put("show_table_waris", "yes");
				this.context.put("show_tambah_waris", "yes");
				this.context.put("show_lapisan_bawah", "yes");
				this.context.put("buttonwaris", "Kemaskini");
				initBandarNegeriBaru();

				if (getParam("checkHidupWaris").equals("")) {

					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}
				this.context.put("show_batal_waris", "");
				this.context.put("show_lapisan_berikut", "");

				this.context.put("listpenting", "");
				initListTextfieldWarisBlank();

				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

				// logic.setDataWarisOB();
				// Vector listWarisOB = logic.getDataWarisOB();
				// this.context.put("listWarisOB",listWarisOB);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);

			} else if ("hapus_waris".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				String idwarisup = getParam("idwarisup");
				FrmPrmhnnSek8Data.deleteWaris(idwarisup);

				this.context.put("buttonwarisSimpan", "Simpan");
				this.context.put("show_table_waris", "yes");
				this.context.put("show_tambah_waris", "yes");
				this.context.put("show_lapisan_bawah", "yes");
				this.context.put("buttonwaris", "Kemaskini");
				this.context.put("readmode", "");

				initListTextfieldWarisBlank();
				initBandarNegeriBaru();

				if (getParam("checkHidupWaris").equals("")) {

					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}

				this.context.put("show_batal_waris", "");
				this.context.put("show_lapisan_berikut", "");
				this.context.put("show_waris_update", "");
				// logic.setDataWarisOB();
				// Vector listWarisOB = logic.getDataWarisOB();
				// this.context.put("listWarisOB",listWarisOB);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			} else if ("kemaskini_waris".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				String idwaris = getParam("idwarisup");

				this.context.put("readmode", "");
				this.context.put("show_table_waris", "");
				this.context.put("show_tambah_waris", "yes");
				this.context.put("buttonwaris", "Simpan");
				this.context.put("buttonwarisSimpan", "");
				this.context.put("show_batal_waris", "yes");

				// vm = "app/ppk/frmPrmhnnSek8Waris.jsp";
				this.context.put("show_lapisan_berikut", "");

				logic.setDataWarisUpdate(idwaris);
				Vector listWarisUpdate = logic.getDataWarisUpdate();
				this.context.put("listWarisUpdate", listWarisUpdate);
				Hashtable t = (Hashtable) listWarisUpdate.get(0);

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

				initDeclareDataBandarNegeriKemaskini(t.get("idnegeri")
						.toString(), t.get("idbandar").toString(), t.get(
						"idnegerisurat").toString(), t.get("idbandarsurat")
						.toString());

			} else if ("kemaskini_waris_lapisan".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_tambah", "");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("buttonwarislapisan", "Simpan");
				this.context.put("buttonwarislapisanSimpan", "");
				this.context.put("show_batal_waris_lapisan", "");
				this.context.put("show_lapisan_bawah", "");
				this.context.put("readmode", "");

				this.context.put("show_batal_waris", "yes");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

				// int idwaris=Integer.parseInt(getParam("idwarislapis"));
				String idwaris = getParam("idwarisup");
				String idparent = getParam("idparentlapis");

				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				logic.setDataWarisUpdate(idwaris);
				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				Vector listWarisUpdate = logic.getDataWarisUpdate();
				logic.setDataWarisParent(idparent);
				// Vector listWarisParent = logic.getDataWarisParent();
				// this.context.put("listWarisParent",listWarisParent);
				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				Hashtable h = (Hashtable) listWarisUpdate.get(0);
				logic.setDataWarisLapisanIdMatiDelete(idwaris);
				Vector listWarisLapisanIdMatiDelete = logic
						.getDataWarisLapisanIdMatiDelete();
				this.context.put("listWarisLapisanIdMatiDelete",
						listWarisLapisanIdMatiDelete);

				initDeclareDataBandarNegeriKemaskiniWaris(h.get("idnegeri")
						.toString(), h.get("idbandar").toString(), h.get(
						"idnegerisurat").toString(), h.get("idbandarsurat")
						.toString());
			} else if ("batal_waris".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				readability1 = " ";
				readability2 = "readonly class=\"disabled\"";
				disability1 = "disabled";
				disability2 = "";

				this.context
						.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
				this.context.put("readmode", "");

				this.context.put("show_kemaskini_button", "");
				// this.context.put("show_simpan_button","");
				this.context.put("show_simpan_button", "yes");

				this.context.put("show_batal_waris", "yes");

				// context.put("DATEUTIL",new DateUtil());

				this.context.put("show_lapisan_berikut", "");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

				String idparent = getParam("idparentlapis");

				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
			}

			else if ("simpan_waris".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				disability1 = "disabled readonly class=\"disabled\"";
				updatewaris(session);

				this.context.put("id_Simati", getParam("txtIdSimatiWaris"));

				Vector listpenting = new Vector();

				Hashtable h;

				h = new Hashtable();
				this.context.put("idwaris", getParam("idwarisup"));
				listpenting.addElement(h);
				this.context.put("listwaris", listpenting);
				// this.context.put("idwaris",
				// Integer.parseInt(getParam("idwaris")));
				this.context.put("idSimati", Integer
						.parseInt(getParam("txtIdSimatiWaris")));
				this.context.put("namaOB", getParam("txtNamaOBWaris"));
				this.context.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				this.context.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				this.context.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));
				this.context.put("nokpwaris", getParam("txtNoKPLamaWaris"));
				this.context.put("notel", getParam("txtNoTeleponWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("jenisKp", getParam("socJenisKPLainWaris"));
				this.context.put("nokplain", getParam("txtNoKPLainWaris"));
				// h.put("taraf",
				// Integer.parseInt(getParam("socTarafKepentinganPenting")));
				this.context.put("jantina", getParam("socJantinaWaris"));
				this.context.put("agama", getParam("socAgamaWaris"));
				this.context.put("warga", getParam("socWarganegaraWaris"));
				this.context
						.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				this.context
						.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				this.context
						.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				this.context.put("poskod", getParam("txtPoskodWaris"));
				this.context.put("bandar", getParam("txtBandarWaris"));

				if (getParam("socNegeriWaris").equals("")) {
					this.context.put("negeri", "");
				} else {
					this.context.put("negeri", getParam("socNegeriWaris"));
				}

				// h.put("negeri",
				// Integer.parseInt(getParam("socNegeriPenting")));
				this.context.put("catatan", getParam("txtCatatanWaris"));

				if (getParam("socSaudaraWaris").equals("")) {
					this.context.put("saudara", "");
				} else {
					this.context.put("saudara", getParam("socSaudaraWaris"));
				}

				if (getParam("socStatusOBWaris").equals("")) {
					this.context.put("statusWaris", "");
				} else {
					this.context.put("statusWaris",
							getParam("socStatusOBWaris"));
				}

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("checkmati", "0");
					this.context.put("show_lapisanan_waris", "");
				} else {
					this.context.put("checkmati", "1");
					this.context.put("show_lapisanan_waris", "yes");
				}
				this.context.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				this.context.put("waktumatiwaris",
						getParam("txtWaktuKematianWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				logic.setDataWarisUpdate(getParam("idwarisup"));
				Vector listWarisUpdate = logic.getDataWarisUpdate();
				this.context.put("listWarisUpdate", listWarisUpdate);
				Hashtable t = (Hashtable) listWarisUpdate.get(0);
				initDeclareDataBandarNegeri(t.get("idnegeri").toString(), t
						.get("idbandar").toString(), t.get("idnegerisurat")
						.toString(), t.get("idbandarsurat").toString());

				this.context.put("invalid_entry", "");
				this.context.put("valid_entry", "Kemaskini berjaya!");
				this.context.put("show_kemaskini_button", "yes");
				this.context.put("show_simpan_button", "");

				this.context.put("readmode", disability1);
				this.context.put("show_table_waris", "");
				this.context.put("show_tambah_waris", "yes");

				this.context.put("show_batal_waris", "");
				this.context.put("show_lapisan_berikut", "");
				this.context.put("buttonwaris", "Kemaskini");

				logic.setDataWaris(id);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

			} else if ("simpan_waris_lapisan".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				String idparent = getParam("idparentlapis");
				String idxx = getParam("idwarisup");

				disability1 = "disabled readonly class=\"disabled\"";
				updatewaris(session);

				this.context.put("id_Simati", getParam("txtIdSimatiWaris"));

				Vector listpenting = new Vector();

				Hashtable h;

				h = new Hashtable();
				this.context.put("idwaris", getParam("idwarisup"));
				listpenting.addElement(h);
				this.context.put("listwaris", listpenting);

				this.context.put("idSimati", Integer
						.parseInt(getParam("txtIdSimatiWaris")));
				this.context.put("namaOB", getParam("txtNamaOBWaris"));
				this.context.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
				this.context.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
				this.context.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));
				this.context.put("nokpwaris", getParam("txtNoKPLamaWaris"));
				this.context.put("notel", getParam("txtNoTeleponWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				this.context.put("jenisKp", getParam("socJenisKPLainWaris"));
				this.context.put("nokplain", getParam("txtNoKPLainWaris"));
				this.context.put("jantina", getParam("socJantinaWaris"));
				this.context.put("agama", getParam("socAgamaWaris"));
				this.context.put("warga", getParam("socWarganegaraWaris"));
				this.context
						.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
				this.context
						.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
				this.context
						.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
				this.context.put("poskod", getParam("txtPoskodWaris"));
				this.context.put("bandar", getParam("txtBandarWaris"));

				if (getParam("socNegeriWaris").equals("")) {
					this.context.put("negeri", "");
				} else {
					this.context.put("negeri", getParam("socNegeriWaris"));
				}

				this.context.put("catatan", getParam("txtCatatanWaris"));
				if (getParam("socSaudaraWaris").equals("")) {
					this.context.put("saudara", "");
				} else {
					this.context.put("saudara", getParam("socSaudaraWaris"));
				}
				if (getParam("socStatusOBWaris").equals("")) {
					this.context.put("statusWaris", "");
				} else {
					this.context.put("statusWaris",
							getParam("socStatusOBWaris"));
				}

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("checkmati", "0");
					this.context.put("show_lapisanan_waris", "");

				} else {
					this.context.put("checkmati", "1");
					this.context.put("show_lapisanan_waris", "yes");
				}

				this.context.put("tarikhmati", getParam("txdTarikhMatiWaris"));
				this.context.put("waktumatiwaris",
						getParam("txtWaktuKematianWaris"));
				this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
				logic.setDataWarisUpdate(getParam("idwarisup"));
				Vector listWarisUpdate = logic.getDataWarisUpdate();
				this.context.put("listWarisUpdate", listWarisUpdate);
				logic.setDataWarisUpdate(idxx);
				Hashtable t = (Hashtable) listWarisUpdate.get(0);
				initDeclareDataBandarNegeri(t.get("idnegeri").toString(), t
						.get("idbandar").toString(), t.get("idnegerisurat")
						.toString(), t.get("idbandarsurat").toString());

				logic.setDataWaris(idxx);
				Vector listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);

				logic.setDataWarisLapisanIdMati(mati);
				Vector listWarisLapisanIdMati = logic
						.getDataWarisLapisanIdMati();
				this.context.put("listWarisLapisanIdMati",
						listWarisLapisanIdMati);

				// int idparent=Integer.parseInt(getParam("txtIdParent"));
				logic.setDataWarisLapisan(idparent);
				Vector listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				logic.setDataWarisParent(idparent);
				Vector listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				this.context.put("invalid_entry", "");
				this.context.put("valid_entry", "Kemaskini berjaya!");
				this.context.put("show_kemaskini_button", "yes");
				this.context.put("show_simpan_button", "");
				this.context.put("readmode", disability1);
				this.context.put("show_table_waris", "");
				this.context.put("show_tambah_waris", "yes");
				this.context.put("buttonwarislapisan", "Kemaskini");
				this.context.put("show_batal_waris", "");
				this.context.put("show_lapisan_berikut", "yes");

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_tambah", "");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("buttonwarislapisan", "Kemaskini");
				this.context.put("buttonwarislapisanSimpan", "");
				this.context.put("show_batal_waris_lapisan", "");
				this.context.put("show_lapisan_bawah", "");

			}

			vm = "app/ppk/frmPrmhnnBorangAWaris.jsp";

		} else if ("Simati".equals(submit)) {
			String id = getParam("idPermohonan");
			this.context.put("idPermohonan", id);
			this.context.put("IdPermohonan", id);

			Vector listBandar = logiconline.getBandar();
			this.context.put("ListBandar", listBandar);

			if ("Simatiview".equals(mode)) {
				initTabData();
				initSimatiNewData();
				int getChkIdSimati = logiconline.countDataSimati(id);
				if (getChkIdSimati == 0) {
					Vector listDataPemohon = logiconline
							.semakDataPemohon(getParam("idPermohonan"));
					Hashtable b = (Hashtable) listDataPemohon.get(0);
					this.context.put("idtarafkptg", b.get("tarafkptg")
							.toString());
					this.context.put("idsaudara", b.get("saudara").toString());
				} else {
					this.context.put("SimpanStatus", 1);
					this.context.put("new_data", "no");
					this.context.put("setmode", "class=\"disabled\"");
					this.context.put("setmode2", "class=\"disabled\"");
					this.context.put("setmode3", "disabled");
					this.context.put("readonly", "readonly");
					this.context.put("idBandar", 0);
					Vector list = logiconline
							.semakDataSimati(getParam("idPermohonan"));
					this.context.put("ViewDataSimat", list);
					Hashtable a = (Hashtable) list.get(0);
					String id1 = (String) a.get("idsimati");
					this.context.put("idSimati", id1);
					this.context.put("id_Simati", id1);
					this.context.put("idpermohonansimati", Integer.parseInt(a
							.get("idpermohonansimati").toString()));
					String idpermohonansimati = (String) a
							.get("idpermohonansimati");
					this.context.put("idpermohonansimati", idpermohonansimati);
					this.context.put("idtarafkptg", a.get("idtarafkptg")
							.toString());
					this.context
							.put("idsaudara", a.get("idsaudara").toString());
					this.context.put("idstatus", a.get("idstatus").toString());
					String id2 = (String) a.get("idPemohon");
					// Azam add
					int flaghub;
					if (a.get("flaghubungan") == null
							|| "".equals(a.get("flaghubungan"))) {
						flaghub = 0;
					} else {
						flaghub = Integer.parseInt(a.get("flaghubungan")
								.toString());
					}

					this.context.put("IdPemohon", id2);
					this.context.put("flaghub", flaghub);
					if (a.get("nokpbarusimati3") != "") {
						// kiraan umur based on ic
						String nokppemohon = a.get("nokpbarusimati3")
								.toString();
						String lastDigit = nokppemohon.substring(3, 4);
						int digitValue;
						if (lastDigit != null) {
							digitValue = 0;
						} else {
							digitValue = Integer.parseInt(lastDigit);
						}

						String gender = "";
						if (digitValue == 0 || digitValue == 2
								|| digitValue == 4 || digitValue == 6
								|| digitValue == 8) {
							gender = "2"; // female
						} else {
							gender = "1"; // male
						}
						this.context.put("jantina", gender);
					} else {
						this.context.put("jantina", 0);
					}
				}

				Vector jenisAgama = logiconline.getAgama();
				this.context.put("JenisAgama", jenisAgama);

				Vector jenisWarga = logiconline.getWarganegara();
				this.context.put("JenisWarga", jenisWarga);

				Vector jenisBuktiMati = FrmPrmhnnSek8DaftarSek8Data
						.getListbuktimati();
				this.context.put("JenisBuktiMati", jenisBuktiMati);

				Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
				this.context.put("listkp", view1);

				Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("ListNegeri", listNegeri);

				Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
				this.context.put("ListTaraf", listTaraf);

				String Waris = getParam("sorWaris");
				String OB = getParam("sorOB");
				this.context.put("sorWaris", Waris);
				this.context.put("sorOB", OB);

				String valueSor = getParam("sorAdaHTA");
				this.context.put("sorAdaHTA", valueSor);

				initTextfieldSimati();

				hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
				context.put("hideTabPengesahan", hideTabPengesahan);

				vm = "app/ppk/frmPrmhnnBorangASimati.jsp";
			} else if ("getBandar".equals(mode)) {
				initTabData();

				this.context.put("SimpanStatus", 0);

				Vector jenisAgama = logiconline.getAgama();
				this.context.put("JenisAgama", jenisAgama);

				Vector jenisWarga = logiconline.getWarganegara();
				this.context.put("JenisWarga", jenisWarga);

				Vector jenisBuktiMati = FrmPrmhnnSek8DaftarSek8Data
						.getListbuktimati();
				this.context.put("JenisBuktiMati", jenisBuktiMati);

				Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
				this.context.put("listkp", view1);

				Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("ListNegeri", listNegeri);

				Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
				this.context.put("ListTaraf", listTaraf);

				String Waris = getParam("sorWaris");
				String OB = getParam("sorOB");
				this.context.put("sorWaris", Waris);
				this.context.put("sorOB", OB);

				String valueSor = getParam("sorAdaHTA");
				this.context.put("sorAdaHTA", valueSor);
				initSimatiGetBandar();

				if (getParam("socNegeri") != "" || getParam("socNegeri") != "0") {
					Vector listBandarByNegeri = logiconline
							.getBandarByNegeri(getParam("socNegeri"));
					this.context.put("listBandarbyNegeri", listBandarByNegeri);
				}

				vm = "app/ppk/frmPrmhnnBorangASimati.jsp";
			} else if ("simpan_simati".equals(mode)) {

				id = getParam("idPermohonan");
				int getResultChkIdSimati = logiconline.countDataSimati(id);
				if (getResultChkIdSimati == 0) {
					insertNewDataSimati(session, id);
				} else {
					updateDataSimati(session, id);
				}
				this.context.put("new_data", "no");
				this.context.put("SimpanStatus", 1);
				initTabData();
				this.context.put("new_data", "no");
				this.context.put("setmode", "class=\"disabled\"");
				this.context.put("setmode2", "class=\"disabled\"");
				this.context.put("setmode3", "disabled");
				Vector jenisAgama = logiconline.getAgama();
				this.context.put("JenisAgama", jenisAgama);

				Vector jenisWarga = logiconline.getWarganegara();
				this.context.put("JenisWarga", jenisWarga);

				Vector jenisBuktiMati = FrmPrmhnnSek8DaftarSek8Data
						.getListbuktimati();
				this.context.put("JenisBuktiMati", jenisBuktiMati);

				Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
				this.context.put("listkp", view1);

				Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("ListNegeri", listNegeri);

				Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
				this.context.put("ListTaraf", listTaraf);

				Vector list = logiconline
						.semakDataSimati(getParam("idPermohonan"));
				this.context.put("ViewDataSimat", list);
				Hashtable a = (Hashtable) list.get(0);
				// if (a.get("idsimati").toString() != ""){
				String id1 = (String) a.get("idsimati");
				this.context.put("idSimati", id1);
				this.context.put("id_Simati", id1);
				this.context.put("idpermohonansimati", (String) a
						.get("idpermohonansimati"));

				// logiconline.checkFieldEmpty(getParam("idPermohonan"));
				// listEmptyField = logiconline.getListEmptyField();

				if (listEmptyField.size() == 0) {

					context.put("hideTabPengesahan", "hide");
				} else if (listEmptyField.size() == 1) {

					Hashtable hEmpty = (Hashtable) listEmptyField.get(0);

					if ("".equals(hEmpty.get("ID_BUKTIMATI"))
							&& "".equals(hEmpty.get("TARIKH_MATI"))
							&& "".equals(hEmpty.get("SEBAB_MATI"))
							&& "".equals(hEmpty.get("ALAMAT_1"))
							&& "".equals(hEmpty.get("ALAMAT1_SURAT"))
							&& "".equals(hEmpty.get("POSKOD"))
							&& "".equals(hEmpty.get("POSKOD_SURAT"))
							&& "0".equals(hEmpty.get("ID_NEGERI"))
							&& "".equals(hEmpty.get("ID_NEGERISURAT"))
							&& "".equals(hEmpty.get("ID_HTA"))) {
						context.put("hideTabPengesahan", "hide");

					}

				} else {
					context.put("hideTabPengesahan", "show");
				}

				vm = "app/ppk/frmPrmhnnBorangASimati.jsp";
			} else if ("kemaskini_simati".equals(mode)) {
				id = getParam("idPermohonan");

				initTabData();

				Vector jenisAgama = logiconline.getAgama();
				this.context.put("JenisAgama", jenisAgama);

				Vector jenisWarga = logiconline.getWarganegara();
				this.context.put("JenisWarga", jenisWarga);

				Vector jenisBuktiMati = FrmPrmhnnSek8DaftarSek8Data
						.getListbuktimati();
				this.context.put("JenisBuktiMati", jenisBuktiMati);

				Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
				this.context.put("listkp", view1);

				Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("ListNegeri", listNegeri);

				Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
				this.context.put("ListTaraf", listTaraf);

				Vector list = logiconline
						.semakDataSimati(getParam("idPermohonan"));
				this.context.put("ViewDataSimat", list);
				Hashtable a = (Hashtable) list.get(0);
				// if (a.get("idsimati").toString() != ""){
				String id1 = (String) a.get("idsimati");
				this.context.put("idSimati", id1);
				this.context.put("id_Simati", id1);
				this.context.put("idpermohonansimati", (String) a
						.get("idpermohonansimati"));

				initSimatiKemaskini();

				if (a.get("idNegeri") != "" || a.get("idNegeri") != "0") {
					Vector listBandarByNegeri = logiconline.getBandarByNegeri(a
							.get("idNegeri").toString());
					this.context.put("listBandarbyNegeri", listBandarByNegeri);
				}

				vm = "app/ppk/frmPrmhnnBorangASimati.jsp";
			} else if ("kembali_simati".equals(mode)) {
				initKembalisimati();
				vm = "app/ppk/frmPrmhnnBorangASimati.jsp";
			}
		} else if ("Pemohon".equals(submit)) {

			Vector list = logiconline.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);

			Hashtable a = (Hashtable) list.get(0);
			if (a.get("idsimati").toString() != "") {
				String id1 = (String) a.get("idsimati");
				this.context.put("idSimati", id1);
				this.context.put("id_Simati", id1);
			} else {
				this.context.put("SimpanStatus", 1);
			}
			String id2 = (String) a.get("idPemohon");

			if (a.get("flaghubungan") == "")
				a.put("flaghubungan", "0");
			int flaghub = Integer.parseInt(a.get("flaghubungan").toString());
			String id = getParam("idPermohonan");
			String idpermohonansimati = (String) (a.get("idpermohonansimati"));
			this.context.put("idpermohonansimati", idpermohonansimati);
			this.context.put("idstatus", a.get("idstatus").toString());
			this.context.put("IdPemohon", id2);
			this.context.put("flaghub", flaghub);
			this.context.put("IdPermohonan", id);

			hideTabPengesahan = checkEmptyField(id);
			context.put("hideTabPengesahan", hideTabPengesahan);

			if ("Pemohonview".equals(mode)) {

				int getChkIdSimati = logiconline.countDataSimati(id);
				if (getChkIdSimati == 0) {
					this.context.put("new_data", "yes");
				} else {
					this.context.put("new_data", "no");
				}

				initTabData();

				int kemaskiniStatus = 1;
				this.context.put("KemaskiniStatus", kemaskiniStatus);

				this.context.put("setmode", "class=\"disabled\"");
				this.context.put("setmode2", "class=\"disabled\"");
				this.context.put("setmode3", "disabled");
				this.context.put("readonly", "readonly");
				this.context.put("agama", "");
				this.context.put("warga", "");

				Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
				this.context.put("ListTaraf", listTaraf);

				Vector listSaudara = FrmPrmhnnSek8DaftarSek8Data
						.getListsaudara();
				this.context.put("ListPersaudaraan", listSaudara);

				Vector jenisAgama = logiconline.getAgama();
				this.context.put("JenisAgama", jenisAgama);

				Vector jenisWarga = logiconline.getWarganegara();
				this.context.put("JenisWarga", jenisWarga);

				Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("ListNegeri", listNegeri);

				Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
				this.context.put("listkp", view1);

				this.context.put("IdPermohonan", getParam("idPermohonan"));

				if (getParam("idPermohonan") == "") {
					id = "0";
					Vector listDataPemohon = logiconline.semakDataPemohon(id);
					this.context.put("ListDataPemohon", listDataPemohon);
				} else {
					id = getParam("idPermohonan");
					Vector listDataPemohon = logiconline.semakDataPemohon(id);
					this.context.put("ListDataPemohon", listDataPemohon);
					Hashtable t = (Hashtable) listDataPemohon.get(0);
					// kiraan umur based on ic
					if (flaghub == 1) {
						if (t.get("noKpBaruPemohon1") == "")
							t.put("noKpBaruPemohon1", "");
						// if
						// (t.get("noKpBaruPemohon1")=="")t.put("noKpBaruPemohon1",
						// "000000");

						String noKpBaru = (String) t.get("NRIC");
						if ((noKpBaru != null && !noKpBaru.equals(""))
								&& noKpBaru.length() == 12) {
							String icYEAR = noKpBaru.substring(0, 2);
							String icMONTH = noKpBaru.substring(2, 4);
							String icDAY = noKpBaru.substring(4, 6);
							String lastDigit = noKpBaru.substring(noKpBaru
									.length() - 1);

							int digitValue = Integer.parseInt(lastDigit);
							String gender = "";
							if (digitValue == 2 || digitValue == 4
									|| digitValue == 6 || digitValue == 8) {
								gender = "2"; // female
							} else {
								gender = "1"; // male
							}
							this.context.put("jantina", gender);

							String yearx = "19" + icYEAR;

							int y = Integer.parseInt(yearx);
							int m = Integer.parseInt(icMONTH) - 1;
							int d = Integer.parseInt(icDAY);

							Calendar cal = new GregorianCalendar(y, m, d);
							Calendar now = new GregorianCalendar();

							int res = now.get(Calendar.YEAR)
									- cal.get(Calendar.YEAR);
							if ((cal.get(Calendar.MONTH) > now
									.get(Calendar.MONTH))
									|| (cal.get(Calendar.MONTH) == now
											.get(Calendar.MONTH) && cal
											.get(Calendar.DAY_OF_MONTH) > now
											.get(Calendar.DAY_OF_MONTH))) {

								res--;

							}
							this.context.put("umur", res);

						}

					}
					initListTextfieldPemohon();
					initDeclareDataBandarNegeri(t.get("idnegeri").toString(), t
							.get("iddaerahtetap").toString(), t.get(
							"idnegerisurat").toString(), t.get("iddaerahsurat")
							.toString());

					hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
					context.put("hideTabPengesahan", hideTabPengesahan);

				}
				vm = "app/ppk/frmPrmhnnBorangAPemohon.jsp";
			} else if ("onChangeBandarTetap".equals(mode)) {
				context.put("onchange", "yes");
				initListTextfieldPemohon();
				initValueBandarNegeri(getParam("socNegeriTetap"),
						getParam("socDaerah"), getParam("socSuratNegeri"),
						getParam("socDaerahSurat"));
				vm = "app/ppk/frmPrmhnnBorangAPemohon.jsp";
			} else if ("onChangeDuplicate".equals(mode)) {
				context.put("onchange", "yes");
				initListTextfieldPemohon();
				initTextfieldPemohonDuplicate();
				initValueBandarNegeriDuplicate(getParam("socNegeriTetap"),
						getParam("socDaerah"));
				vm = "app/ppk/frmPrmhnnBorangAPemohon.jsp";
				System.out.println("check on");
			} else if ("onChangeNotDuplicate".equals(mode)) {
				context.put("onchange", "yes");
				initListTextfieldPemohon();
				initBandarNegeriPemohonBlank();
				initValueBandarNegeriNotDuplicate(getParam("socNegeriTetap"),
						getParam("socDaerah"));
				vm = "app/ppk/frmPrmhnnBorangAPemohon.jsp";
				System.out.println("check off");
			} else if ("kemaskini_pemohon".equals(mode)) {
				initTabData();
				this.context.put("setmode", "");
				this.context.put("setmode2", "");
				this.context.put("setmode3", "");
				this.context.put("readonly", "");
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				int kemaskiniStatus = 0;
				this.context.put("KemaskiniStatus", kemaskiniStatus);

				Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
				this.context.put("ListTaraf", listTaraf);

				Vector listSaudara = FrmPrmhnnSek8DaftarSek8Data
						.getListsaudara();
				this.context.put("ListPersaudaraan", listSaudara);

				Vector jenisAgama = logiconline.getAgama();
				this.context.put("JenisAgama", jenisAgama);

				Vector jenisWarga = logiconline.getWarganegara();
				this.context.put("JenisWarga", jenisWarga);

				Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("ListNegeri", listNegeri);

				Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
				this.context.put("listkp", view1);

				this.context.put("IdPermohonan", getParam("idPermohonan"));

				if (getParam("idPermohonan") == "") {
					id = "0";
					Vector listDataPemohon = logiconline.semakDataPemohon(id);
					this.context.put("ListDataPemohon", listDataPemohon);
				} else {
					id = getParam("idPermohonan");
					Vector listDataPemohon = logiconline.semakDataPemohon(id);
					this.context.put("ListDataPemohon", listDataPemohon);
					Hashtable t = (Hashtable) listDataPemohon.get(0);
					if (flaghub == 1) {
						if (t.get("nokp") == "")
							t.put("nokp", "0");
						int noKp = Integer.parseInt(t.get("nokp").toString());

						String noKpBaru = (String) t.get("NRIC");
						if ((noKpBaru != null && !noKpBaru.equals(""))
								&& noKpBaru.length() == 12) {
							String icYEAR = noKpBaru.substring(0, 2);
							String icMONTH = noKpBaru.substring(2, 4);
							String icDAY = noKpBaru.substring(4, 6);
							String lastDigit = noKpBaru.substring(noKpBaru
									.length() - 1);

							int digitValue = Integer.parseInt(lastDigit);
							String gender = "";
							if (digitValue == 2 || digitValue == 4
									|| digitValue == 6 || digitValue == 8) {
								gender = "2"; // female
							} else {
								gender = "1"; // male
							}
							this.context.put("jantina", gender);

							String yearx = "19" + icYEAR;

							int y = Integer.parseInt(yearx);
							int m = Integer.parseInt(icMONTH) - 1;
							int d = Integer.parseInt(icDAY);

							Calendar cal = new GregorianCalendar(y, m, d);
							Calendar now = new GregorianCalendar();

							int res = now.get(Calendar.YEAR)
									- cal.get(Calendar.YEAR);
							if ((cal.get(Calendar.MONTH) > now
									.get(Calendar.MONTH))
									|| (cal.get(Calendar.MONTH) == now
											.get(Calendar.MONTH) && cal
											.get(Calendar.DAY_OF_MONTH) > now
											.get(Calendar.DAY_OF_MONTH))) {

								res--;

							}
							this.context.put("umur", res);

						}

					}
					initDeclareDataBandarNegeriKemaskini(t.get("idnegeri")
							.toString(), t.get("iddaerahtetap").toString(), t
							.get("idnegerisurat").toString(), t.get(
							"iddaerahsurat").toString());
					hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
					context.put("hideTabPengesahan", hideTabPengesahan);

				}
				vm = "app/ppk/frmPrmhnnBorangAPemohon.jsp";
			} else if ("simpan_pemohon_data".equals(mode)) {
				initTabData();

				this.context.put("setmode", "class=\"disabled\"");
				this.context.put("setmode2", "class=\"disabled\"");
				this.context.put("setmode3", "disabled");
				this.context.put("readonly", "readonly");

				this.context.put("SimpanStatus", getParam("simpanStatus"));
				id = getParam("idPermohonan");
				updateNewDataPemohon(session, id);

				initListTextfieldPemohon();
				initDeclareDataBandarNegeri(getParam("socNegeriTetap"),
						getParam("socDaerah"), getParam("socSuratNegeri"),
						getParam("socDaerahSurat"));

				int kemaskiniStatus = 1;
				this.context.put("KemaskiniStatus", kemaskiniStatus);

				Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
				this.context.put("ListTaraf", listTaraf);

				Vector listSaudara = FrmPrmhnnSek8DaftarSek8Data
						.getListsaudara();
				this.context.put("ListPersaudaraan", listSaudara);

				Vector jenisAgama = logiconline.getAgama();
				this.context.put("JenisAgama", jenisAgama);

				Vector jenisWarga = logiconline.getWarganegara();
				this.context.put("JenisWarga", jenisWarga);

				Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("ListNegeri", listNegeri);

				Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
				this.context.put("listkp", view1);

				String idPermohonan = getParam("idPermohonan");
				this.context.put("IdPermohonan", idPermohonan);

				Vector listx = logiconline
						.semakDataSimati(getParam("idPermohonan"));
				Hashtable t = (Hashtable) listx.get(0);
				int flaghubx = Integer.parseInt(t.get("flaghubungan")
						.toString());
				this.context.put("flaghub", flaghubx);
				if (getParam("idPermohonan") == "") {
					id = "0";
					Vector listDataPemohon = logiconline.semakDataPemohon(id);
					this.context.put("ListDataPemohon", listDataPemohon);
				} else {
					id = getParam("idPermohonan");
					Vector listDataPemohon = logiconline.semakDataPemohon(id);
					this.context.put("ListDataPemohon", listDataPemohon);
				}

				hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
				context.put("hideTabPengesahan", hideTabPengesahan);

				vm = "app/ppk/frmPrmhnnBorangAPemohon.jsp";
			}
		} else if ("Peguam".equals(submit)) {

			hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
			context.put("hideTabPengesahan", hideTabPengesahan);

			if ("Peguamview".equals(mode)) {
				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("IdPermohonan", getParam("idPermohonan"));
				this.context.put("IdPemohon", getParam("idPemohon"));
				this.context.put("idpeguam", 0);

				initTextfieldPeguamBlank();
				initBandarNegeriBaruSingle();

				String id2 = getParam("idPemohon");
				String namaFirma = getParam("txtNamaFirmaPeguam");
				logic17.setDataPeguam(id2);
				Vector listPeguamdata = logic17.getDataPeguam();
				this.context.put("ListPeguam", listPeguamdata);
				int cnt = listPeguamdata.size();
				this.context.put("cntList", cnt);

				this.context.put("KemaskiniPeguamStatus", 0);

				Vector list = logiconline
						.semakDataSimati(getParam("idPermohonan"));
				this.context.put("View", list);
				Hashtable n = (Hashtable) list.get(0);
				int flaghub = Integer
						.parseInt(n.get("flaghubungan").toString());
				this.context.put("flaghub", flaghub);
				this.context.put("set_data", "yes");
				if (n.get("idstatus") == "")
					n.put("idstatus", 0);
				this.context.put("idstatus", n.get("idstatus"));

				vm = "app/ppk/frmPrmhnnBorangAPeguam.jsp";
			} else if ("onChangeBandarTetap".equals(mode)) {
				initTextfieldPeguam();
				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));
				vm = "app/ppk/frmPrmhnnBorangAPeguam.jsp";
			} else if ("kemaskini_peguam".equals(mode)) {
				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("IdPermohonan", getParam("idPermohonan"));
				this.context.put("IdPemohon", getParam("idPemohon"));

				String id2 = getParam("idPemohon");
				Vector listPeguamdatax = logic17
						.setDataPeguamData(getParam("idPeguam"));
				this.context.put("listPeguam", listPeguamdatax);
				Hashtable n1 = (Hashtable) listPeguamdatax.get(0);

				if (n1.get("idnegeri") == "")
					n1.put("idnegeri", "0");
				if (n1.get("idbandar") == "")
					n1.put("idbandar", "0");
				;
				initValueBandarNegeriSingle(n1.get("idnegeri").toString(), n1
						.get("idbandar").toString());

				this.context.put("KemaskiniPeguamStatus", 2);
				this.context.put("buttonSimpan", "yes");
				this.context.put("buttonKemaskini", "no");
				this.context.put("readmode", "");

				vm = "app/ppk/frmPrmhnnBorangAPeguam.jsp";
			} else if ("hapus_peguam".equals(mode)) {
				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("IdPermohonan", getParam("idPermohonan"));
				this.context.put("IdPemohon", getParam("idPemohon"));

				String id2 = getParam("idPemohon");
				logiconline.deletePeguam(getParam("idPeguam"));

				logic17.setDataPeguam(id2);
				Vector listPeguamdata = logic.getDataPeguam();
				this.context.put("ListPeguam", listPeguamdata);

				initTextfieldPeguamBlank();
				initBandarNegeriBaruSingle();

				int kemaskiniPeguamStatus = 0;
				this.context
						.put("KemaskiniPeguamStatus", kemaskiniPeguamStatus);

				this.context.put("set_data", "yes");

				vm = "app/ppk/frmPrmhnnBorangAPeguam.jsp";
			} else if ("simpan_peguam".equals(mode)) {
				initTabData();

				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("IdPermohonan", getParam("idPermohonan"));
				this.context.put("IdPemohon", getParam("idPemohon"));

				String id2 = getParam("idPemohon");
				String namaFirma = getParam("txtNamaFirmaPeguam");

				int getResultChkIdPeguam = logiconline
						.countDataPeguam(getParam("idPeguam"));
				if (getResultChkIdPeguam == 0) {
					insertNewDataPeguam(session, id2);
				} else {
					updatepeguam(session, id2);
				}

				logic17.setDataPeguam(id2);
				Vector listPeguamdata = logic17.getDataPeguam();
				this.context.put("ListPeguam", listPeguamdata);
				int cnt = listPeguamdata.size();
				this.context.put("cntList", cnt);

				int kemaskiniPeguamStatus = 0;
				this.context
						.put("KemaskiniPeguamStatus", kemaskiniPeguamStatus);

				initTextfieldPeguamBlank();
				initBandarNegeriBaruSingle();
				this.context.put("set_data", "yes");

				vm = "app/ppk/frmPrmhnnBorangAPeguam.jsp";
			} else if ("edit_peguam".equals(mode)) {
				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("IdPermohonan", getParam("idPermohonan"));
				this.context.put("IdPemohon", getParam("idPemohon"));

				String id2 = getParam("idPeguam");
				Vector listPeguamdatax = logic17.setDataPeguamData(id2);
				this.context.put("listPeguam", listPeguamdatax);
				Hashtable n1 = (Hashtable) listPeguamdatax.get(0);

				if (n1.get("idnegeri") == "")
					n1.put("idnegeri", "0");
				if (n1.get("idbandar") == "")
					n1.put("idbandar", "0");

				initValueBandarNegeriSingleDisabled(n1.get("idnegeri")
						.toString(), n1.get("idbandar").toString());

				this.context.put("KemaskiniPeguamStatus", 2);

				logic17.setDataPeguam(getParam("idPemohon"));
				Vector listPeguamdata = logic17.getDataPeguam();
				this.context.put("ListPeguam", listPeguamdata);

				this.context.put("KemaskiniPeguamStatus", 1);
				this.context.put("buttonSimpan", "no");
				this.context.put("buttonKemaskini", "yes");
				this.context.put("set_data", "no");
				this.context.put("readmode", "readonly class=\"disabled\"");

				vm = "app/ppk/frmPrmhnnBorangAPeguam.jsp";
			}
		} else if ("Penting".equals(submit)) {
			String disability1 = "disabled";

			// context.put("DATEUTIL",new DateUtil());

			Vector listTaraf = FrmPrmhnnSek8DaftarSek8Data.getListtaraf();
			this.context.put("listtaraf", listTaraf);

			Vector listNegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
			this.context.put("listnegeri", listNegeri);

			Vector list2 = logiconline
					.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list2);
			Hashtable n = (Hashtable) list2.get(0);
			String id1 = (String) (n.get("idsimati"));
			String id2 = (String) n.get("idPemohon");
			int flaghub = Integer.parseInt((String) n.get("flaghubungan"));
			String idpermohonansimati = (String) n.get("idpermohonansimati");
			String id = getParam("idPermohonan");
			this.context.put("idstatus", n.get("idstatus").toString());
			this.context.put("IdSimati", id1);
			this.context.put("idPemohon", id2);
			this.context.put("IdPermohonan", id);
			this.context.put("idpermohonansimati", idpermohonansimati);
			this.context.put("flaghub", flaghub);
			this.context.put("idhub", n.get("idhubungan").toString());

			System.out.println("idhub---" + n.get("idhubungan").toString());

			// logiconline.checkFieldEmpty(getParam("idPermohonan"));
			// listEmptyField = logiconline.getListEmptyField();

			if (listEmptyField.size() == 0) {

				context.put("hideTabPengesahan", "hide");
			} else if (listEmptyField.size() == 1) {

				Hashtable hEmpty = (Hashtable) listEmptyField.get(0);

				if ("".equals(hEmpty.get("ID_BUKTIMATI"))
						&& "".equals(hEmpty.get("TARIKH_MATI"))
						&& "".equals(hEmpty.get("SEBAB_MATI"))
						&& "".equals(hEmpty.get("ALAMAT_1"))
						&& "".equals(hEmpty.get("ALAMAT1_SURAT"))
						&& "".equals(hEmpty.get("POSKOD"))
						&& "".equals(hEmpty.get("POSKOD_SURAT"))
						&& "0".equals(hEmpty.get("ID_NEGERI"))
						&& "".equals(hEmpty.get("ID_NEGERISURAT"))
						&& "".equals(hEmpty.get("ID_HTA"))) {
					context.put("hideTabPengesahan", "hide");

				}

			} else {
				context.put("hideTabPengesahan", "show");
			}

			if ("Pentingview".equals(mode)) {
				// int mati = id1;
				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_button_penting", "yes");
				this.context.put("buttonpenting", "Simpan");
				this.context.put("readmode", "");
				this.context.put("idSimati", id1);
				initTabData();
				initBandarNegeriBaruSingle();
				initListTextfieldPentingBlank();
				System.out.println("jenisPB++" + getParam("jenisPB"));

				// if (getParam("jenisPB").equals("1")){
				System.out.println("1");
				Vector dataIndividu = logiconline.setDataPentingAgensi(id);
				this.context.put("listPenting", dataIndividu);
				// }
				// else if (flaghub==2){
				// System.out.println("2");
				// logic.setDataPenting(id);
				// Vector dataIndividu = logiconline.setDataPentingAgensi(id);
				// this.context.put("listPenting",dataIndividu);
				// }else{
				// System.out.println("3");
				// Vector dataIndividu = logiconline.setDataPentingAgensi(id);
				// this.context.put("listPenting",dataIndividu);
				// }
			} else if ("onChangeBandarTetap".equals(mode)) {
				context.put("onchange", "yes");
				initListTextfieldPenting();
				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));
			} else if ("tambah_penting".equals(mode)) {
				String mati = getParam("idSimati");

				if (getParam("jenisPB").equals("1")) {
					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);
				} else if (getParam("jenisPB").equals("2")) {
					logic.setDataPenting(id);
					Vector listPenting = logic.getDataPenting();
					this.context.put("listPenting", listPenting);
				}

				String idob = getParam("txtIdOb");
				logic.setDataPentingbyIDOB(idob);
				Vector listPentingbyIDOB = logic.getDataPentingbyIDOB();
				this.context.put("listPentingbyIDOB", listPentingbyIDOB);

				disability1 = "disabled";
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", disability1);
				this.context.put("buttonpenting", "Kemaskini");
			} else if ("tambah_penting_baru".equals(mode)) {
				String mati = getParam("idSimati");
				this.context.put("buttonpenting", "tambah");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_button_penting", "yes");

				if (getParam("jenisPB").equals("1")) {
					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);
				} else if (getParam("jenisPB").equals("2")) {
					logic.setDataPenting(id);
					Vector listPenting = logic.getDataPenting();
					this.context.put("listPenting", listPenting);
				}
				this.context.put("readmode", "");
			} else if ("hapus_penting".equals(mode)) {
				String mati = getParam("idSimati");
				String iddob = getParam("txtIdOb");

				FrmPrmhnnSek8Data.deletePenting(iddob);

				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", "");
				this.context.put("buttonpenting", "Simpan");

				if (getParam("jenisPB").equals("1")) {
					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);
				} else if (getParam("jenisPB").equals("2")) {
					logic.setDataPenting(id);
					Vector listPenting = logic.getDataPenting();
					this.context.put("listPenting", listPenting);
				}

				initBandarNegeriBaruSingle();
				initListTextfieldPentingBlank();

				this.context.put("readmode", "");
			} else if ("GetPenting".equals(mode)) {
				String mati = getParam("idSimati");
				disability1 = "disabled class = disabled";
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", disability1);
				this.context.put("buttonpenting", "Kemaskini");

				String idob = getParam("txtIdOb");
				logic.setDataPentingbyIDOB(idob);
				Vector listPentingbyIDOB = logic.getDataPentingbyIDOB();
				this.context.put("listPentingbyIDOB", listPentingbyIDOB);
				Hashtable n1 = (Hashtable) listPentingbyIDOB.get(0);
				if (n1.get("idnegeri") == "")
					n1.put("idnegeri", "0");
				if (n1.get("idbandar") == "")
					n1.put("idbandar", "0");
				initValueBandarNegeriSingleDisabled(n1.get("idnegeri")
						.toString(), n1.get("idbandar").toString());

				if (getParam("jenisPB").equals("1")) {
					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);
				} else if (getParam("jenisPB").equals("2")) {
					logic.setDataPenting(id);
					Vector listPenting = logic.getDataPenting();
					this.context.put("listPenting", listPenting);
				}

				initListTextfieldPentingBlank();
			} else if ("batal_update".equals(mode)) {
				String mati = getParam("idSimati");
				disability1 = "";
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", disability1);
				this.context.put("buttonpenting", "Simpan");
				String idob = getParam("txtIdOb");
				logic.setDataPentingbyIDOB(idob);
				Vector listPentingbyIDOB = logic.getDataPentingbyIDOB();
				this.context.put("listPentingbyIDOB", listPentingbyIDOB);

				if (getParam("jenisPB").equals("1")) {
					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);
				} else if (getParam("jenisPB").equals("2")) {
					logic.setDataPenting(id);
					Vector listPenting = logic.getDataPenting();
					this.context.put("listPenting", listPenting);
				}
			} else if ("KemaskiniPenting".equals(mode)) {
				String mati = getParam("idSimati");
				disability1 = "";
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", "");
				this.context.put("buttonpenting", "Simpan");
				String idob = getParam("txtIdOb");

				logic.setDataPentingbyIDOB(idob);
				Vector listPentingbyIDOB = logic.getDataPentingbyIDOB();
				this.context.put("listPentingbyIDOB", listPentingbyIDOB);
				Hashtable n1 = (Hashtable) listPentingbyIDOB.get(0);
				if (n1.get("idnegeri") == "")
					n1.put("idnegeri", "0");
				if (n1.get("idbandar") == "")
					n1.put("idbandar", "0");
				initValueBandarNegeriSingle(n1.get("idnegeri").toString(), n1
						.get("idbandar").toString());

				if (getParam("jenisPB").equals("1")) {
					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);

				} else if (getParam("jenisPB").equals("2")) {
					logic.setDataPenting(id);
					Vector listPenting = logic.getDataPenting();
					this.context.put("listPenting", listPenting);
				} else {
					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);
				}
				initListTextfieldPentingBlank();
			} else if ("simpan_penting".equals(mode)) {
				String mati = getParam("idSimati");
				String idob = getParam("txtIdOb");
				if (idob.equals("")) {
					addPenting(session);
					disability1 = "disabled";
					this.context.put("nk_button_penting", "yes");
					this.context.put("nk_update_penting", "");
					this.context.put("nk_tambah_penting", "");
					this.context.put("readmode", "");
					this.context.put("buttonpenting", "Simpan");

					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);
				} else {
					updatepenting(session);
					disability1 = "disabled class=disabled";
					this.context.put("nk_button_penting", "yes");
					this.context.put("nk_update_penting", "yes");
					this.context.put("nk_tambah_penting", "");
					this.context.put("readmode", disability1);
					this.context.put("buttonpenting", "Kemaskini");

					String idobx = getParam("txtIdOb");
					logic.setDataPentingbyIDOB(idobx);
					Vector listPentingbyIDOB = logic.getDataPentingbyIDOB();
					this.context.put("listPentingbyIDOB", listPentingbyIDOB);
					Hashtable n1 = (Hashtable) listPentingbyIDOB.get(0);
					if (n1.get("idnegeri") == "")
						n1.put("idnegeri", "0");
					if (n1.get("idbandar") == "")
						n1.put("idbandar", "0");
					initValueBandarNegeriSingleDisabled(n1.get("idnegeri")
							.toString(), n1.get("idbandar").toString());

					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);
				}

				if (getParam("jenisPB").equals("1")) {
					Vector dataIndividu = logiconline.setDataPentingAgensi(id);
					this.context.put("listPenting", dataIndividu);
				} else if (getParam("jenisPB").equals("2")) {
					logic.setDataPenting(id);
					Vector listPenting = logic.getDataPenting();
					this.context.put("listPenting", listPenting);
				}
			}

			hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
			context.put("hideTabPengesahan", hideTabPengesahan);

			vm = "app/ppk/frmPrmhnnBorangAOB.jsp";
		} else if ("Pemiutang".equals(submit)) {

			String disability1 = "disabled";

			initTabData();

			// context.put("DATEUTIL",new DateUtil());

			Vector list = logiconline.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);
			Hashtable h = (Hashtable) list.get(0);
			String id1 = (String) h.get("idsimati");
			String id2 = (String) h.get("idPemohon");
			String id = getParam("idPermohonan");
			String idpermohonansimati = (String) h.get("idpermohonansimati");
			int flaghub = Integer.parseInt(h.get("flaghubungan").toString());
			this.context.put("idstatus", h.get("idstatus").toString());
			this.context.put("flaghub", flaghub);
			this.context.put("idSimati", id1);
			this.context.put("id_Simati", id1);
			this.context.put("idPemohon", id2);
			this.context.put("idpermohonansimati", idpermohonansimati);
			this.context.put("id", id);

			Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
			this.context.put("listnegeri", listnegeri);

			hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
			context.put("hideTabPengesahan", hideTabPengesahan);

			if ("Pemiutangview".equals(mode)) {

				String jenisHutang = "";
				if (getParam("socJenisHutangPentingU").equals("")) {
					jenisHutang = "0";
				} else {
					jenisHutang = getParam("socJenisHutangPentingU");
				}
				this.context.put("socJenisHutang", jenisHutang);

				String mati = id1;
				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_button_penting", "yes");
				this.context.put("buttonpenting", "tambah");
				this.context.put("readmode", "");
				this.context.put("value", "");
				this.context.put("hutangrm", "");
				this.context.put("nojeniskp", "0");
				this.context.put("setmode2", "");

				initInputPemiutang();
				initPemiutangView();
				logic.setDataPenting(id);
				Vector listPenting = logic.getDataPenting();
				this.context.put("listPenting", listPenting);

				this.context.put("idSimati", id1);
				this.context.put("idPemohon", id2);
				this.context.put("id", id);

			} else if ("getBandarTetap".equals(mode)) {
				initPemiutangGetBandarTetap();
				if (getParam("socNegeriPenting") != ""
						|| getParam("socNegeriPenting") != "0") {
					Vector listBandarByNegeri = logiconline
							.getBandarByNegeri(getParam("socNegeriPenting"));
					this.context.put("listBandarbyNegeri", listBandarByNegeri);
				}
			} else if ("tambah_pemiutang".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				this.context.put("tambah_baru", "");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_tambah_penting", "yes");
				this.context.put("buttonpenting", "tambah");
				this.context.put("readmode", "");

				addPemiutang(session);

				initPemiutangView();

				logic.setDataPenting(id);
				Vector listPenting = logic.getDataPenting();
				this.context.put("listPenting", listPenting);

				String jenisHutang = "";
				if (getParam("socJenisHutangPentingU").equals("")) {
					jenisHutang = "0";
				} else {
					jenisHutang = getParam("socJenisHutangPentingU");
				}
				this.context.put("socJenisHutang", jenisHutang);

			}

			else if ("tambah_pemiutang_baru".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				this.context.put("buttonpenting", "tambah");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_button_penting", "yes");

				logic.setDataPenting(id);
				Vector listPenting = logic.getDataPenting();
				this.context.put("listPenting", listPenting);
				this.context.put("readmode", "");

			}

			else if ("hapus_pemiutang".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				String iddob = getParam("txtIdOb");

				FrmPrmhnnSek8Data.deletePenting(iddob);

				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_button_penting", "yes");
				this.context.put("buttonpenting", "tambah");

				logic.setDataPenting(id);
				Vector listPenting = logic.getDataPenting();
				this.context.put("listPenting", listPenting);
				this.context.put("readmode", "");

			}

			else if ("GetPemiutang".equals(mode)) {
				initTabData();
				initPemiutangView();
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				disability1 = "disabled readonly class=\"disabled\"";
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", disability1);
				this.context.put("buttonpenting", "Kemaskini");
				String idob = getParam("idOb");

				logic.setDataPentingbyIDOB(idob);
				Vector listPentingbyIDOB = logic.getDataPentingbyIDOB();
				this.context.put("listPentingbyIDOB", listPentingbyIDOB);

				Vector listBandar = logiconline.getBandar();
				this.context.put("ListBandar", listBandar);

				logic.setDataPenting(id);
				Vector listPenting = logic.getDataPenting();
				this.context.put("listPenting", listPenting);

			} else if ("batal_update_pemiutang".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				disability1 = "";

				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", disability1);
				this.context.put("buttonpenting", "Simpan");
				String idob = getParam("txtIdOb");

				logic.setDataPentingbyIDOB(idob);
				Vector listPentingbyIDOB = logic.getDataPentingbyIDOB();
				this.context.put("listPentingbyIDOB", listPentingbyIDOB);

				logic.setDataPenting(id);
				Vector listPenting = logic.getDataPenting();
				this.context.put("listPenting", listPenting);

			} else if ("KemaskiniPemiutang".equals(mode)) {
				initTabData();
				initPemiutangView();
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				disability1 = "";

				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", disability1);
				this.context.put("buttonpenting", "Simpan");
				String idob = getParam("txtIdOb");

				logic.setDataPentingbyIDOB(idob);
				Vector listPentingbyIDOB = logic.getDataPentingbyIDOB();
				this.context.put("listPentingbyIDOB", listPentingbyIDOB);
				Hashtable t = (Hashtable) listPentingbyIDOB.get(0);

				logic.setDataPenting(id);
				Vector listPenting = logic.getDataPenting();
				this.context.put("listPenting", listPenting);

				Vector listBandar = logiconline.getBandar();
				this.context.put("ListBandar", listBandar);

				if (t.get("idbandar") != "" || t.get("idbandar") != "0") {
					Vector listBandarByNegeri = logiconline.getBandarByNegeri(t
							.get("idnegeri").toString());
					this.context.put("listBandarbyNegeri", listBandarByNegeri);
				}

			} else if ("simpan_pemiutang".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				String mati = getParam("idSimati");

				updatepenting(session);

				disability1 = "disabled readonly class=\"disabled\"";
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", disability1);
				this.context.put("buttonpenting", "Kemaskini");
				String idob = getParam("txtIdOb");

				logic.setDataPentingbyIDOB(idob);
				Vector listPentingbyIDOB = logic.getDataPentingbyIDOB();
				this.context.put("listPentingbyIDOB", listPentingbyIDOB);

				logic.setDataPenting(id);
				Vector listPenting = logic.getDataPenting();
				this.context.put("listPenting", listPenting);
			}
			vm = "app/ppk/frmPrmhnnBorangAPemiutang.jsp";
		} else if ("Penghutang".equals(submit)) {
			String disability1 = "disabled readonly class=\"disabled\"";

			Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
			this.context.put("listkp", view1);

			Vector list = logiconline.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);
			Hashtable h = (Hashtable) list.get(0);
			String id1 = (String) h.get("idsimati");
			String id2 = (String) h.get("idPemohon");
			String id = getParam("idPermohonan");
			int flaghub = Integer.parseInt(h.get("flaghubungan").toString());
			this.context.put("idpermohonansimati", Integer.parseInt(h.get(
					"idpermohonansimati").toString()));
			this.context.put("idstatus", h.get("idstatus").toString());
			this.context.put("flaghub", flaghub);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);
			this.context.put("id", id);

			hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
			context.put("hideTabPengesahan", hideTabPengesahan);

			if ("Penghutangview".equals(mode)) {
				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_button_penting", "yes");
				this.context.put("buttonpenting", "Simpan");
				this.context.put("readmode", "");
				this.context.put("id", id);
				this.context.put("idSimati", id1);
				this.context.put("idPemohon", id2);
				this.context.put("socJenisHutang",
						getParam("socJenisHutangPenting"));

				logic17.setDataPenghutang((String) h.get("idpermohonansimati"));
				Vector listPenghutang = logic17.getDataPenghutang();
				this.context.put("listPenting", listPenghutang);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				if (getParam("socJenisHutangPenting").equals("")) {
					// initBandarNegeriBaruSingleSelectionDisabled();
					initBandarNegeriBaruSingle();
				} else {
					initBandarNegeriBaruSingle();
				}

				initListTextfieldPenghutangBlank();

				vm = "app/ppk/frmPrmhnnBorangAPenghutang.jsp";
			} else if ("onChangeBandarTetap".equals(mode)) {
				initValueBandarNegeri(getParam("socNegeriTetap"),
						getParam("socDaerah"), getParam("socSuratNegeri"),
						getParam("socDaerahSurat"));
				initListTextfieldPenghutang();
				vm = "app/ppk/frmPrmhnnBorangAPenghutang.jsp";
			} else if ("tambah_penghutang".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String nokp = "";
				if (getParam("socJenisHutangPentingU").equals("2")) {
					nokp = getParam("txtNoKPBaru1PentingU")
							+ getParam("txtNoKPBaru2PentingU")
							+ getParam("txtNoKPBaru3PentingU");
				} else if (getParam("socJenisHutangPentingU").equals("1")) {
					nokp = getParam("txtSyarikatPentingU");
				}
				Vector checkDataPenghutang = logiconline
						.getCheckDataPenghutang(nokp);
				Hashtable t = (Hashtable) checkDataPenghutang.get(0);
				int chkDataPenghutang = Integer.parseInt(t.get("cntid")
						.toString());

				if (chkDataPenghutang == 0) {
					addPenghutang(session);
				}

				logic.setDataPenghutang(id1);
				Vector listPenghutang = logic.getDataPenghutang();
				this.context.put("listPenting", listPenghutang);
				this.context.put("tambah_baru", "");

				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_button_penting", "yes");
				this.context.put("buttonpenting", "Simpan");

				initListTextfieldPenghutangBlank();
			}

			else if ("tambah_penghutang_baru".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_button_penting", "yes");
				this.context.put("buttonpenting", "Simpan");

				logic.setDataPenghutang(id1);
				Vector listPenghutang = logic.getDataPenghutang();
				this.context.put("listPenting", listPenghutang);
				this.context.put("readmode", "");
				this.context.put("readmodekp", disability1);
				this.context.put("readmodesy", disability1);
				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				// context.put("DATEUTIL",new DateUtil());
			}

			else if ("hapus_penghutang".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String iddob = getParam("txtIdOb");
				FrmPrmhnnSek8Data.deletePenghutang(iddob);

				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_update_penting", "");
				this.context.put("nk_button_penting", "yes");
				this.context.put("buttonpenting", "Simpan");

				logic.setDataPenghutang(id1);
				Vector listPenghutang = logic.getDataPenghutang();
				this.context.put("listPenting", listPenghutang);
				this.context.put("readmode", "");

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				// context.put("DATEUTIL",new DateUtil());
			} else if ("GetPenghutang".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode",
						"disabled readonly class=\"disabled\"");
				this.context.put("readmode1",
						"disabled readonly class=\"disabled\"");
				this.context.put("buttonpenting", "Kemaskini");
				this.context.put("buttonpenting", "Kemaskini");
				String idob = getParam("idOb");

				logic.setDataPenghutangbyIDOB(idob,
						getParam("idpermohonansimati"));
				Vector listPenghutangbyIDOB = logic.getDataPenghutangbyIDOB();
				this.context.put("listPentingbyIDOB", listPenghutangbyIDOB);
				Hashtable t1 = (Hashtable) listPenghutangbyIDOB.get(0);

				logic.setDataPenghutang(id1);
				Vector listPenghutang = logic.getDataPenghutang();
				this.context.put("listPenting", listPenghutang);

				initValueBandarNegeriSingleDisabled(t1.get("idnegeri")
						.toString(), t1.get("idbandar").toString());
			} else if ("batal_update_penghutang".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				disability1 = "";
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", disability1);
				this.context.put("buttonpenting", "Simpan");
				String idob = getParam("txtIdOb");
				logic.setDataPenghutangbyIDOB(idob,
						getParam("idpermohonansimati"));
				Vector listPenghutangbyIDOB = logic.getDataPenghutangbyIDOB();
				this.context.put("listPentingbyIDOB", listPenghutangbyIDOB);
				logic.setDataPenghutang(id1);
				Vector listPenghutang = logic.getDataPenghutang();
				this.context.put("listPenting", listPenghutang);

			} else if ("KemaskiniPenghutang".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				disability1 = "";
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", "");
				this.context.put("buttonpenting", "Update");
				this.context.put("readmode1", "");
				String idob = getParam("txtIdOb");

				logic.setDataPenghutangbyIDOB(idob,
						getParam("idpermohonansimati"));
				Vector listPenghutangbyIDOB = logic.getDataPenghutangbyIDOB();
				this.context.put("listPentingbyIDOB", listPenghutangbyIDOB);
				Hashtable t1 = (Hashtable) listPenghutangbyIDOB.get(0);

				logic.setDataPenghutang(id1);
				Vector listPenghutang = logic.getDataPenghutang();
				this.context.put("listPenting", listPenghutang);

				initValueBandarNegeriPenghutangValue(t1.get("idnegeri")
						.toString(), t1.get("idbandar").toString());
			} else if ("simpan_penghutang".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				updatepenghutang(session);

				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("nk_tambah_penting", "");
				this.context.put("readmode", "readonly class=\"disabled\"");
				this.context.put("readmode1", "readonly class=\"disabled\"");
				this.context.put("buttonpenting", "Kemaskini");

				String idob = getParam("txtIdOb");
				logic.setDataPenghutangbyIDOB(idob,
						getParam("idpermohonansimati"));
				Vector listPenghutangbyIDOB = logic.getDataPenghutangbyIDOB();
				this.context.put("listPentingbyIDOB", listPenghutangbyIDOB);
				Hashtable t1 = (Hashtable) listPenghutangbyIDOB.get(0);
				initValueBandarNegeriSingleDisabled(t1.get("idnegeri")
						.toString(), t1.get("idbandar").toString());

				logic.setDataPenghutang(id1);
				Vector listPenghutang = logic.getDataPenghutang();
				this.context.put("listPenting", listPenghutang);
			}
			vm = "app/ppk/frmPrmhnnBorangAPenghutang.jsp";

		} else if ("harta_alih".equals(submit)) {
			initTabData();
			this.context.put("SimpanStatus", getParam("simpanStatus"));
			this.context.put("Id", getParam("idPermohonan"));

			Vector listJenisha = FrmPrmhnnSek8DaftarSek8Data.getJenisHa();
			this.context.put("ViewJenisHa", listJenisha);

			Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
			this.context.put("listnegeri", listnegeri);

			Vector list = logiconline.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);
			Hashtable h = (Hashtable) list.get(0);
			String id1 = (String) h.get("idsimati");
			String id2 = (String) h.get("idPemohon");
			String id = getParam("idPermohonan");
			String idpermohonansimati = (String) h.get("idpermohonansimati");
			this.context.put("idstatus", h.get("idstatus").toString());
			this.context.put("idpermohonansimati", idpermohonansimati);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);
			this.context.put("idSimati", id1);

			hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
			context.put("hideTabPengesahan", hideTabPengesahan);

			if ("view_harta_alih".equals(mode)) {
				this.context.put("idJenisHa", "");
				this.context.put("tutup", "");
				this.context.put("socJenisHa", "0");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("nilaitarikhmohon", "0.00");
				this.context.put("nilaitarikhmati", "0.00");
				this.context.put("nilaiunit", "0.00");
				this.context.put("nilaimohon", "0.00");
				this.context.put("bilunit", "0");
				this.context.put("nilaiseunit", "0.00");
				this.context.put("agensi", "");
				this.context.put("add1", "");
				this.context.put("add2", "");
				this.context.put("add3", "");
				this.context.put("poskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("catatan", "");
				this.context.put("idha", "0");
				this.context.put("snegeri", "0");

				String readability1 = " ";
				String readability2 = "readonly";
				String disability1 = "disabled";
				String disability2 = "";

				this.context.put("id", id);
				this.context.put("id2", id2);
				this.context.put("id1", id1);

				view_mode_ppkha(session, idpermohonansimati);
				view_sum_ppkha(session, id1);
				view_overallsum_ppkha(session, id1);
				view_overallsummati_ppkha(session, id1);

				String socJenisha = "0";
				socJenisha = getParam("socJenisHartaAlih");
				this.context.put("socJenisHa", socJenisha);

				Vector listppkha = logicSek8Internal.getDataHa();
				this.context.put("listHa", listppkha);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context
						.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
				this.context.put("readmode", disability1);

				this.context.put("show_kemaskini_button", "yes");
				this.context.put("show_simpan_button", "");
				this.context.put("show_batal_button", "");
				this.context.put("show_kembali_butt", "");

				// context.put("DATEUTIL",new DateUtil());

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("EventStatus", 0);

				// sum_nilai_ppkpermohonan(session, id, id1,
				// idpermohonansimati);

				vm = "app/ppk/frmPrmhnnBorangAHA.jsp";
			} else if ("tambah_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");
				this.context.put("idPermohonan", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);
				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("idJenisHa", 0);

				this.context.put("tutup", "");
				this.context.put("EventStatus", 0);
				this.context.put("tutup", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("nilaitarikhmohon", "0.00");
				this.context.put("nilaitarikhmati", "0.00");
				this.context.put("nilaimohon", "0.00");
				this.context.put("bilunit", "0");
				this.context.put("nilaiseunit", "0.00");
				this.context.put("agensi", "");
				this.context.put("add1", "");
				this.context.put("add2", "");
				this.context.put("add3", "");
				this.context.put("poskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("catatan", "");

				String socJenishax = getParam("socJenisHartaAlih");
				this.context.put("socJenisHa", socJenishax);

				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logicSek8Internal.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				vm = "app/ppk/frmPrmhnnBorangAHA.jsp";

			} else if ("simpan_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("idJenisHa", "");
				this.context.put("tutup", "");
				this.context.put("socJenisHa", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("nilaitarikhmohon", "0.00");
				this.context.put("nilaitarikhmati", "0.00");
				this.context.put("nilaimohon", "0");
				this.context.put("nilaiunit", "0.00");
				this.context.put("bilunit", "0");
				this.context.put("nilaiseunit", "0.00");
				this.context.put("agensi", "");
				this.context.put("add1", "");
				this.context.put("add2", "");
				this.context.put("add3", "");
				this.context.put("poskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("catatan", "");

				this.context.put("EventStatus", 0);

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);

				addHa(session);

				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logicSek8Internal.getDataHa();
				this.context.put("listHa", listppkha);

				view_sum_ppkha(session, id1);
				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_overallsum_ppkha(session, id1);
				view_overallsummati_ppkha(session, id1);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkhtath(session, id);
				Vector listppkhtath = frmPrmhnnSek8SenaraiHTATHData.getHtath();
				this.context.put("listHtaht2", listppkhtath);
				int countListhtath = listppkhtath.size();
				this.context.put("jumListHtaht", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));
				this.context.put("socJenisHa", 0);

				vm = "app/ppk/frmPrmhnnBorangAHA.jsp";

			} else if ("batal_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("tutup", "");
				String eventstatus = getParam("eventStatus");
				this.context.put("EventStatus", eventstatus);

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);

				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logicSek8Internal.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				vm = "app/ppk/frmPrmhnnBorangAHA.jsp";

			} else if ("edit_harta".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("tutup", "yes");
				String eventstatus = getParam("eventStatus");
				this.context.put("EventStatus", eventstatus);

				String idha = "0";
				idha = getParam("idha");

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);
				this.context.put("idha", idha);

				Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
						.setSelectedDataHa(id1, idha);
				this.context.put("DataHa", selectedppkha);
				Hashtable v = (Hashtable) selectedppkha.get(0);
				if (v.get("idnegeri").toString() != "") {
					// Vector listDaerahByNegeri =
					// FrmPrmhnnSek8DaftarSek8Data.getListDaerahbyNegeri(Integer.parseInt(v.get("idnegeri").toString()));
					Vector listBandarByNegeri = logiconline.getBandarByNegeri(v
							.get("idnegeri").toString());
					this.context.put("listDaerahbyNegeri", listBandarByNegeri);
				}
				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logicSek8Internal.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				vm = "app/ppk/frmPrmhnnBorangAHA.jsp";

			} else if ("kemaskini_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("tutup", "yes");
				String eventstatus = getParam("eventStatus");
				this.context.put("EventStatus", eventstatus);

				this.context.put("id1", id1);

				String idha = "0";
				idha = getParam("idha");

				this.context.put("id", id);
				this.context.put("id2", id2);
				this.context.put("idha", idha);
				this.context.put("socJenisHa", 0);

				// view_data_ppkha(session,id1,idha);
				Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
						.setSelectedDataHa(id1, idha);
				this.context.put("DataHa", selectedppkha);
				Hashtable v = (Hashtable) selectedppkha.get(0);
				if (v.get("idnegeri") == "")
					v.put("idnegeri", "0");
				Vector listBandarByNegeri = logiconline.getBandarByNegeri(v
						.get("idnegeri").toString());
				this.context.put("listDaerahbyNegeri", listBandarByNegeri);

				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logicSek8Internal.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				vm = "app/ppk/frmPrmhnnBorangAHA.jsp";
			} else if ("update_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("tutup", "yes");
				String eventstatus = getParam("eventStatus");
				this.context.put("EventStatus", eventstatus);

				String idha = getParam("idha");

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);
				this.context.put("idha", idha);
				updateHa(session);
				// view_data_ppkha(session,id1,idha);
				view_sum_ppkha(session, id1);
				view_overallsum_ppkha(session, id1);
				view_overallsummati_ppkha(session, id1);

				// listkan senarai harta alih
				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logicSek8Internal.getDataHa();
				this.context.put("listHa", listppkha);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
						.setSelectedDataHa(id1, idha);
				this.context.put("DataHa", selectedppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				vm = "app/ppk/frmPrmhnnBorangAHA.jsp";
			} else if ("hapus_harta".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("idJenisHa", "");
				this.context.put("tutup", "");
				this.context.put("socJenisHa", "");
				this.context.put("bhgnmati1", "");
				this.context.put("bhgnmati2", "");
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("nilaitarikhmohon", "0.00");
				this.context.put("nilaitarikhmati", "0.00");
				this.context.put("nilaiunit", "0.00");
				this.context.put("nilaimohon", "0.00");
				this.context.put("bilunit", "0");
				this.context.put("nilaiseunit", "0.00");
				this.context.put("agensi", "");
				this.context.put("add1", "");
				this.context.put("add2", "");
				this.context.put("add3", "");
				this.context.put("poskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("catatan", "");

				this.context.put("tutup", "");
				this.context.put("EventStatus", 0);

				this.context.put("id1", id1);

				// id_tbl harta alih
				String idha = getParam("idha");

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("idha", idha);

				delete_mode_ppkha(session, id1, idha);
				Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
						.setSelectedDataHa(id1, idha);
				this.context.put("DataHa", selectedppkha);

				view_mode_ppkha(session, idpermohonansimati);
				Vector listppkha = logicSek8Internal.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				vm = "app/ppk/frmPrmhnnBorangAHA.jsp";
			} else if ("get_daerah".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				Vector listBandarByNegeri = logiconline
						.getBandarByNegeri(getParam("socNegeri"));
				this.context.put("listDaerahbyNegeri", listBandarByNegeri);

				if (!getParam("valueSocHa").equals("0")) {
					this.context.put("socJenisHa", getParam("valueSocHa"));
					this.context.put("idJenisHa", 0);
					this.context.put("tutup", "");
					this.context.put("EventStatus", 0);
					this.context.put("negeri", getParam("socNegeri"));
				} else if (!getParam("valueJenisHa").equals("0")) {
					this.context.put("idJenisHa", getParam("valueJenisHa"));
					this.context.put("socJenisHa", 0);
					this.context.put("tutup", "yes");
					this.context.put("EventStatus", 3);
					this.context.put("idha", getParam("idha"));
					this.context.put("id1", getParam("id1"));
					this.context.put("snegeri", getParam("socNegeri"));
				}

				this.context.put("bhgnmati1", getParam("txtBhgnSimati1"));
				this.context.put("bhgnmati2", getParam("txtBhgnSimati2"));
				this.context.put("norujukan", getParam("txtNoRujukan"));
				this.context.put("nosijil", getParam("txtNoSijil"));
				this.context.put("nilaitarikhmohon",
						getParam("txtNilaiTarikhMohon"));
				this.context.put("nilaimohon", getParam("txtNilaiTarikhMohon"));
				this.context.put("bilunit", getParam("txtBilUnit"));
				this.context.put("nilaiseunit", getParam("txtNilaiSeunit"));
				this.context.put("agensi", getParam("txtAgensi"));
				this.context.put("catatan", getParam("txtCatatan"));
				this.context.put("add1", getParam("txtAlamatHartaAlih1"));
				this.context.put("add2", getParam("txtAlamatHartaAlih2"));
				this.context.put("add3", getParam("txtAlamatHartaAlih3"));
				this.context.put("poskod", getParam("txtPoskodPemohon"));
				this.context.put("daerah", getParam("socDaerah"));
				this.context.put("catatan", getParam("txtCatatan"));
				this.context.put("disabled", "");

				// id_tbl harta alih
				this.context.put("id", id);
				this.context.put("id1", id1);
				view_mode_ppkha(session, idpermohonansimati);
				if (!getParam("idha").equals("0")) {
					this.context.put("idha", getParam("idha"));
					Vector selectedppkha = FrmPrmhnnSek8DaftarSek8Data
							.setSelectedDataHa(id1, getParam("idha"));
					this.context.put("DataHa", selectedppkha);
				}
				Vector listppkha = logicSek8Internal.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				Vector sumppkha = FrmPrmhnnSek8DaftarSek8Data.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				view_mode_ppkhta(session, id);
				FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
				Vector listppkhta = frmPrmhnnSek8SenaraiHTATHData.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				view_mode_ppkha2(session, id);
				Vector listppkha2 = frmPrmhnnSek8SenaraiHTATHData.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				Vector overallnilai = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSum();
				this.context.put("overall", overallnilai);

				Vector overallnilaimati = FrmPrmhnnSek8DaftarSek8Data
						.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				vm = "app/ppk/frmPrmhnnBorangAHA.jsp";
			}
		} else if ("HtaamX".equals(submit)) {
			String readability2 = "readonly class=disabled";
			String disability1 = "disabled readonly class=\"disabled\"";
			String disability2 = "";

			this.context.put("radio2", "yes");
			this.context.put("radio3", "yes");

			Vector listluas = FrmPrmhnnSek8DaftarSek8Data.getListLuas();
			this.context.put("listluas", listluas);

			Vector list2 = logiconline
					.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list2);
			Hashtable n = (Hashtable) list2.get(0);
			String id1 = (String) n.get("idsimati");
			String id2 = (String) n.get("idPemohon").toString();
			String id = getParam("idPermohonan");
			String idpermohonansimati = (String) n.get("idpermohonansimati");
			this.context.put("idstatus", (String) n.get("idstatus"));
			this.context.put("idpermohonansimati", idpermohonansimati);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);

			Vector listHTAX = logiconline.getDataHTAX(idpermohonansimati);
			this.context.put("listHTAX", listHTAX);

			hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
			context.put("hideTabPengesahan", hideTabPengesahan);

			if ("HtaamviewX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");

				this.context.put("buttonhtaam", "Tambah");

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				initBandarNegeriBaruSingle();
				initValueBandarNegeriSinglehtaamx(getParam("socNegeriHtaamX"),
						getParam("socDaerahx"));
				sum_nilai_ppkpermohonan(session, id, id1, idpermohonansimati);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			}

			if ("add_new".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");

				this.context.put("radio2", "yes");
				this.context.put("radio3", "");
				this.context.put("checked1", "checked");
				this.context.put("checked2", "");
				this.context.put("checked3", "");

				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");

				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");

				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");

				this.context.put("catatan", "");

				this.context.put("noperserahan", "");

				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");

				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");

				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");
				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");
				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");
				this.context.put("nolot", "");
				this.context.put("nocagaran", "");
				this.context.put("buttonhtaam", "Tambah");
				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
				context.put("hideTabPengesahan", hideTabPengesahan);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			}
			if ("HtaamviewX1".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));

				this.context.put("checked1", "checked");
				this.context.put("checked2", "");
				this.context.put("checked3", "");

				this.context.put("radio2", "yes");
				this.context.put("radio3", "");

				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");

				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");

				this.context.put("catatan", "");

				this.context.put("noperserahan", "");

				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");

				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");

				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");

				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");

				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");

				this.context.put("nolot", "");

				this.context.put("nocagaran", "");
				this.context.put("buttonhtaam", "Tambah");

				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());
				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				this.context.put("show_button", "yes");

				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));
				initBandarNegeriBaruSinglehtaamx();

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			}

			if ("HtaamviewX2".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");
				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));

				this.context.put("checked1", "");
				this.context.put("checked2", "checked");
				this.context.put("checked3", "");

				this.context.put("radio2", "");
				this.context.put("radio3", "yes");
				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");
				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");
				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");
				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");
				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");
				this.context.put("nolot", "");
				this.context.put("nocagaran", "");
				this.context.put("buttonhtaam", "Tambah");

				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				this.context.put("show_button", "yes");

				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			}
			if ("HtaamviewX3".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));

				this.context.put("checked1", "");
				this.context.put("checked2", "");
				this.context.put("checked3", "checked");

				this.context.put("radio2", "");
				this.context.put("radio3", "");
				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");
				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");
				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");
				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");
				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");
				this.context.put("nocagaran", "");
				this.context.put("buttonhtaam", "Tambah");

				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				this.context.put("show_button", "yes");

				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			} else if ("onChangeBandarTetap".equals(mode)) {
				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));
				if (getParam("radioHtaamViewX1").equals("1")) {

					this.context.put("radio2", "yes");
					this.context.put("radio3", "");
					this.context.put("checked1", "checked");
					this.context.put("checked2", "");
					this.context.put("checked3", "");
				} else if (getParam("radioHtaamViewX1").equals("2")) {
					this.context.put("radio2", "");
					this.context.put("radio3", "yes");
					this.context.put("checked1", "");
					this.context.put("checked2", "checked");
					this.context.put("checked3", "");
				} else if (getParam("radioHtaamViewX1").equals("3")) {
					this.context.put("radio2", "");
					this.context.put("radio3", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("checked3", "checked");
				}

				textfieldHtaamValue();
				initValueBandarNegeriSingle(getParam("socNegeriTetap"),
						getParam("socDaerah"));
				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			} else if ("tambah_htaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");
				this.context.put("idPermohonan", id);
				this.context.put("idSimati", id1);
				this.context.put("idPemohon", id2);

				logic.setDataPenting(id1);
				Vector listPenting = logic.getDataPenting();

				logic.setDataSaksi(id1);
				Vector listSaksi = logic.getDataSaksi();
				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");
				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");
				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");
				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");
				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");
				this.context.put("nolot", "");
				this.context.put("nocagaran", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			}

			else if ("masukkanX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				addHtaamX(session);
				sum_nilai_ppkpermohonan(session, id, id1, idpermohonansimati);

				this.context.put("radio2", "yes");
				this.context.put("radio3", "");
				this.context.put("checked1", "checked");
				this.context.put("checked2", "");
				this.context.put("checked3", "");
				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("namapemaju", "");
				this.context.put("alamatpemaju1", "");
				this.context.put("alamatpemaju2", "");
				this.context.put("alamatpemaju3", "");
				this.context.put("poskodpemaju", "");
				this.context.put("bandarpemaju", "");
				this.context.put("negeripemaju", "");
				this.context.put("alamathta1", "");
				this.context.put("alamathta2", "");
				this.context.put("alamathta3", "");
				this.context.put("poskodhta", "");
				this.context.put("bandarhta", "");
				this.context.put("noperjanjian", "");
				this.context.put("tarikhperjanjian", "");
				this.context.put("namarancangan", "");
				this.context.put("noroh", "");
				this.context.put("nolot", "");
				this.context.put("nolot", "");
				this.context.put("nocagaran", "");
				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			}

			else if ("negerichangeX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", disability1);

				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				if (getParam("radioHtaamViewX1") == "1") {
					this.context.put("radio2", "yes");
					this.context.put("radio3", "");
					this.context.put("checked1", "checked");
					this.context.put("checked2", "");
					this.context.put("checked3", "");
				}
				if (getParam("radioHtaamViewX1") == "2") {
					this.context.put("radio2", "");
					this.context.put("radio3", "yes");
					this.context.put("checked1", "");
					this.context.put("checked2", "checked");
					this.context.put("checked3", "");
				}
				if (getParam("radioHtaamViewX1") == "3") {
					this.context.put("radio2", "");
					this.context.put("radio3", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("checked3", "checked");
				}

				this.context.put("nopt", getParam("txtNoPTHtaamX"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaaX"));
				this.context.put("nilai_Hta_mati",
						getParam("txtNilaiTarikhMatiHtaamX"));
				this.context
						.put("kategori", getParam("socKategoriTanahHtaamX"));

				this.context.put("luashmp", getParam("txtLuasHMpHtaamX"));
				this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
				this.context.put("nopajakan", getParam("txtNoPajakanX"));
				this.context.put("jenistanah", getParam("socJenisTanahHtaamX"));
				this.context.put("basimati", getParam("txtBahagianSimati1X"));
				this.context.put("bbsimati", getParam("txtBahagianSimati2X"));

				this.context.put("tanggungan", getParam("txtTanggunganHtaamX"));
				this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
				this.context.put("catatan", getParam("txtCatatanHtaamX"));

				this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
				this.context.put("alamatpemaju1",
						getParam("txtAlamatPemaju1HtaamX"));
				this.context.put("alamatpemaju2",
						getParam("txtAlamatPemaju2HtaamX"));
				this.context.put("alamatpemaju3",
						getParam("txtAlamatPemaju3HtaamX"));

				this.context.put("poskodpemaju",
						getParam("txtPoskodPemaju1HtaamX"));
				this.context.put("bandarpemaju",
						getParam("txtBandarPemaju1HtaamX"));
				this.context.put("negeripemaju",
						getParam("socNegeriPemajuHtaamX"));
				this.context.put("alamathta1",
						getParam("txtAlamatHarta1HtaamX"));
				this.context.put("alamathta2",
						getParam("txtAlamatHarta2HtaamX"));
				this.context.put("alamathta3",
						getParam("txtAlamatHarta3HtaamX"));

				this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
				this.context.put("bandarhta", getParam("txtBandarHartaHtaamX"));
				this.context.put("noperjanjian",
						getParam("txtNoPerjanjianHtaamX"));
				this.context.put("tarikhperjanjian",
						getParam("txtTarikhPerjanjianHtaamX"));

				this.context.put("namarancangan",
						getParam("txtNamaRancanganHtaamX"));
				this.context.put("noroh", getParam("txtNoRohHtaamX"));
				this.context.put("nolot", getParam("txtLotIdHtaamX"));
				this.context.put("nolot", getParam("txtLotIdHtaamX"));
				this.context.put("nocagaran", getParam("txtNoCagaranX"));

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				initValueBandarNegeriSinglehtaamx(getParam("socNegeriHtaamX"),
						getParam("socDaerahx"));

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			}

			else if ("negerichangeupX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", disability1);

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);
				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				Vector v = new Vector();
				Hashtable h = new Hashtable();

				h.put("idhta", getParam("idhtaamXid"));
				h.put("negeri", idnegeri);
				h.put("idSimati", getParam("idSimatiX"));
				h.put("nopt", getParam("txtNoPTHtaamX"));
				h
						.put("nilai_Hta_memohon",
								getParam("txtNilaiTarikhMohonHtaaX"));
				h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
				h.put("kategori", getParam("socKategoriTanahHtaamX"));

				h.put("luashmp", getParam("txtLuasHMpHtaamX"));
				h.put("luasasal", getParam("txtLuasAsalHtaamX"));

				h.put("nopajakan", getParam("txtNoPajakanX"));
				h.put("jenistanah", getParam("socJenisTanahHtaamX"));
				h.put("basimati", getParam("txtBahagianSimati1X"));
				h.put("bbsimati", getParam("txtBahagianSimati2X"));

				h.put("tanggungan", getParam("txtTanggunganHtaamX"));
				h.put("jenisluas", getParam("socJenisLuasHtaamX"));

				h.put("catatan", getParam("txtCatatanHtaamX"));

				h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
				h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
				h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
				h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));

				h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
				h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));

				h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
				h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
				h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
				h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));

				h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
				h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
				h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
				h
						.put("tarikhperjanjian",
								getParam("txtTarikhPerjanjianHtaamX"));

				h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
				h.put("noroh", getParam("txtNoRohHtaamX"));
				h.put("nolot", getParam("txtLotIdHtaamX"));

				h.put("nolot", getParam("txtLotIdHtaamX"));
				h.put("flag", getParam("flag"));

				h.put("nocagaran", getParam("txtNoCagaranX"));
				v.addElement(h);

				this.context.put("listHTAXid", v);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");
				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("daerahchangeupX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));

				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", iddaerah);
				this.context.put("mukim", "");

				Vector v = new Vector();
				Hashtable h = new Hashtable();

				h.put("idhta", getParam("idhtaamXid"));
				h.put("negeri", idnegeri);
				h.put("daerah", iddaerah);
				h.put("idSimati", getParam("idSimatiX"));
				h.put("nopt", getParam("txtNoPTHtaamX"));
				h
						.put("nilai_Hta_memohon",
								getParam("txtNilaiTarikhMohonHtaaX"));
				h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));
				h.put("kategori", getParam("socKategoriTanahHtaamX"));

				h.put("luashmp", getParam("txtLuasHMpHtaamX"));
				h.put("luasasal", getParam("txtLuasAsalHtaamX"));

				h.put("nopajakan", getParam("txtNoPajakanX"));
				h.put("jenistanah", getParam("socJenisTanahHtaamX"));
				h.put("basimati", getParam("txtBahagianSimati1X"));
				h.put("bbsimati", getParam("txtBahagianSimati2X"));

				h.put("tanggungan", getParam("txtTanggunganHtaamX"));
				h.put("jenisluas", getParam("socJenisLuasHtaamX"));

				h.put("catatan", getParam("txtCatatanHtaamX"));

				h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
				h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
				h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
				h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));

				h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
				h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));

				h.put("negeripemaju", getParam("socNegeriPemajuHtaamX"));
				h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
				h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
				h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));

				h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
				h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
				h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
				h
						.put("tarikhperjanjian",
								getParam("txtTarikhPerjanjianHtaamX"));

				h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
				h.put("noroh", getParam("txtNoRohHtaamX"));
				h.put("nolot", getParam("txtLotIdHtaamX"));

				h.put("nolot", getParam("txtLotIdHtaamX"));
				h.put("flag", getParam("flag"));

				h.put("nocagaran", getParam("txtNoCagaranX"));

				v.addElement(h);

				this.context.put("listHTAXid", v);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");
				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("getHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				logic.setDataHTAXbyIdHtaam(idhtaam);
				Vector listHTAXid = logic.getDataHTAXbyIdHtaam();

				Hashtable b = (Hashtable) listHTAXid.get(0);
				if (b.get("negeri") == "")
					b.put("negeri", "0");
				if (b.get("daerah") == "")
					b.put("daerah", "0");
				if (b.get("negeripemaju") == "")
					b.put("negeripemaju", "0");
				if (b.get("idbandarpemaju") == "")
					b.put("idbandarpemaju", "0");
				if (b.get("idbandarhta") == "")
					b.put("idbandarhta", "0");

				int idnegeri = Integer.parseInt((String) b.get("negeri"));
				int iddaerah = Integer.parseInt((String) b.get("daerah"));

				initValueBandarNegeriSingleDisabled(b.get("negeripemaju")
						.toString(), b.get("idbandarpemaju").toString());
				initValueBandarNegeriSingleDisabledhtaamx(b.get("negeri")
						.toString(), b.get("idbandarhta").toString());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukim2 = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukim2);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerah);

				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);

				this.context.put("listHTAXid", listHTAXid);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", disability1);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			} else if ("batalHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				logic.setDataHTAXbyIdHtaam(idhtaam);
				Vector listHTAXid = logic.getDataHTAXbyIdHtaam();

				Hashtable b = (Hashtable) listHTAXid.get(0);
				int idnegeri = Integer.parseInt(b.get("negeri").toString());
				int iddaerah = Integer.parseInt(b.get("daerah").toString());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukim2 = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukim2);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerah);

				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);

				this.context.put("listHTAXid", listHTAXid);
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "disabled");

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("kembaliHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				initTextfieldHtaamxBlank();

				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				this.context.put("selectedTabatas", getParam("tabIdatas"));
				this.context.put("selectedTabtengah", getParam("tabIdtengah"));
				this.context.put("selectedTabbawah", getParam("tabIdbawah"));
				this.context.put("selectedTabtepi", getParam("tabIdtepi"));
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");

				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("hapusHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				FrmPrmhnnSek8Data.deleteHtaam(idhtaam);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				this.context.put("radio2", "yes");
				this.context.put("radio3", "");
				this.context.put("checked2", "");
				this.context.put("checked1", "checked");
				this.context.put("checked3", "");

				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";

			} else if ("kemaskiniHtaamX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				logic.setDataHTAXbyIdHtaam(idhtaam);
				Vector listHTAXid = logic.getDataHTAXbyIdHtaam();
				Hashtable b = (Hashtable) listHTAXid.get(0);
				if (b.get("negeri") == "")
					b.put("negeri", "0");
				if (b.get("daerah") == "")
					b.put("daerah", "0");
				if (b.get("negeripemaju") == "")
					b.put("negeripemaju", "0");
				if (b.get("idbandarpemaju") == "")
					b.put("idbandarpemaju", "0");
				if (b.get("idbandarhta") == "")
					b.put("idbandarhta", "0");

				initValueBandarNegeriSingle(b.get("negeripemaju").toString(), b
						.get("idbandarpemaju").toString());
				initValueBandarNegeriSinglehtaamx(b.get("negeri").toString(), b
						.get("idbandarhta").toString());

				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);

				this.context.put("listHTAXid", listHTAXid);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "");

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "yes");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			} else if ("simpanHtaamX".equals(mode)) {
				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				String idhtaam = getParam("idhtaamXid");
				// update data
				updateHtaamX(session);

				logic.setDataHTAXbyIdHtaam(idhtaam);
				Vector listHTAXid = logic.getDataHTAXbyIdHtaam();
				Hashtable b = (Hashtable) listHTAXid.get(0);
				if (b.get("negeri") == "")
					b.put("negeri", "0");
				if (b.get("daerah") == "")
					b.put("daerah", "0");
				if (b.get("negeripemaju") == "")
					b.put("negeripemaju", "0");
				if (b.get("idbandarpemaju") == "")
					b.put("idbandarpemaju", "0");
				if (b.get("idbandarhta") == "")
					b.put("idbandarhta", "0");

				initValueBandarNegeriSingleDisabled(b.get("negeripemaju")
						.toString(), b.get("idbandarpemaju").toString());
				initValueBandarNegeriSingleDisabledhtaamx(b.get("negeri")
						.toString(), b.get("idbandarhta").toString());

				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);

				this.context.put("listHTAXid", listHTAXid);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", disability1);
				this.context.put("readmodecheckbox", disability1);

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				this.context.put("radio2", "yes");
				this.context.put("radio3", "");
				this.context.put("checked1", "checked");
				this.context.put("checked2", "");
				this.context.put("checked3", "");

				// list senarai harta tak alih simati
				listHTAX = logiconline.getDataHTAX(idpermohonansimati);
				this.context.put("listHTAX", listHTAX);

				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			} else if ("daerahchangeX".equals(mode)) {
				this.context.put("idPermohonan", getParam("idPermohonan"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("idPemohon", getParam("idPemohon"));
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				initBandarNegeriBaruSingle();

				this.context.put("radioHtaamViewX1",
						getParam("radioHtaamViewX1"));

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", iddaerah);
				this.context.put("mukim", "");

				if (getParam("radioHtaamViewX1") == "1") {

					this.context.put("radio2", "yes");
					this.context.put("radio3", "");
					this.context.put("checked1", "checked");
					this.context.put("checked2", "");
					this.context.put("checked3", "");
				}
				if (getParam("radioHtaamViewX1") == "2") {
					this.context.put("radio2", "");
					this.context.put("radio3", "yes");
					this.context.put("checked1", "");
					this.context.put("checked2", "checked");
					this.context.put("checked3", "");
				}
				if (getParam("radioHtaamViewX1") == "3") {
					this.context.put("radio2", "");
					this.context.put("radio3", "");
					this.context.put("checked1", "");
					this.context.put("checked2", "");
					this.context.put("checked3", "checked");
				}

				this.context.put("nopt", getParam("txtNoPTHtaamX"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaaX"));
				this.context.put("nilai_Hta_mati",
						getParam("txtNilaiTarikhMatiHtaamX"));
				this.context
						.put("kategori", getParam("socKategoriTanahHtaamX"));

				this.context.put("luashmp", getParam("txtLuasHMpHtaamX"));
				this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
				this.context.put("nopajakan", getParam("txtNoPajakanX"));
				this.context.put("jenistanah", getParam("socJenisTanahHtaamX"));
				this.context.put("basimati", getParam("txtBahagianSimati1X"));
				this.context.put("bbsimati", getParam("txtBahagianSimati2X"));
				this.context.put("tanggungan", getParam("txtTanggunganHtaamX"));
				this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
				this.context.put("catatan", getParam("txtCatatanHtaamX"));
				this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
				this.context.put("alamatpemaju1",
						getParam("txtAlamatPemaju1HtaamX"));
				this.context.put("alamatpemaju2",
						getParam("txtAlamatPemaju2HtaamX"));
				this.context.put("alamatpemaju3",
						getParam("txtAlamatPemaju3HtaamX"));
				this.context.put("poskodpemaju",
						getParam("txtPoskodPemaju1HtaamX"));
				this.context.put("bandarpemaju",
						getParam("txtBandarPemaju1HtaamX"));
				this.context.put("negeripemaju",
						getParam("socNegeriPemajuHtaamX"));
				this.context.put("alamathta1",
						getParam("txtAlamatHarta1HtaamX"));
				this.context.put("alamathta2",
						getParam("txtAlamatHarta2HtaamX"));
				this.context.put("alamathta3",
						getParam("txtAlamatHarta3HtaamX"));
				this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
				this.context.put("bandarhta", getParam("txtBandarHartaHtaamX"));
				this.context.put("noperjanjian",
						getParam("txtNoPerjanjianHtaamX"));
				this.context.put("tarikhperjanjian",
						getParam("txtTarikhPerjanjianHtaamX"));
				this.context.put("namarancangan",
						getParam("txtNamaRancanganHtaamX"));
				this.context.put("noroh", getParam("txtNoRohHtaamX"));
				this.context.put("nolot", getParam("txtLotIdHtaamX"));
				this.context.put("nocagaran", getParam("txtNoCagaranX"));
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
			}
			vm = "app/ppk/frmPrmhnnBorangAHTATH.jsp";
		} else if ("Htaam".equals(submit)) {
			String readability1 = " ";
			String readability2 = "readonly";
			String disability1 = "disabled readonly class=\"disabled\"";
			String disability2 = "";

			String statushak = "Y";
			Vector listjenishakmilik = FrmPrmhnnSek8DaftarSek8Data
					.getListJenisHakMilik(statushak);
			this.context.put("listJenisHakMilik", listjenishakmilik);

			Vector listluas = FrmPrmhnnSek8DaftarSek8Data.getListLuas();
			this.context.put("listluas", listluas);

			Vector listkategori = FrmPrmhnnSek8DaftarSek8Data.getListKategori();
			this.context.put("listkategori", listkategori);

			Vector list = logiconline.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);
			Hashtable n = (Hashtable) list.get(0);
			String id1 = (String) n.get("idsimati");
			String id2 = (String) n.get("idPemohon");
			String id = getParam("idPermohonan");

			String idpermohonansimati = (String) n.get("idpermohonansimati");
			this.context.put("idstatus", n.get("idstatus").toString());
			this.context.put("idpermohonansimati", idpermohonansimati);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);

			if ("Htaamview".equals(mode)) {
				this.context.put("buttonhtaam", "Tambah");
				logic.setDataHTA(id1);
				Vector listHTA = logiconline.getDataHTA(id);
				this.context.put("listHTA", listHTA);
				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();

				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_button", "yes");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_htaa_view_table", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");

				hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
				context.put("hideTabPengesahan", hideTabPengesahan);

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			} else if ("add_new".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("idhta", "");
				this.context.put("noHakmilik", "");

				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nocagaran", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("jenishta", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("nopt", "");
				this.context.put("nilai_Hta_memohon", "");
				this.context.put("nilai_Hta_mati", "");
				this.context.put("kategori", "");
				this.context.put("jenishakmilik", "");
				this.context.put("pemilikan", "");
				this.context.put("luashmp", "");
				this.context.put("luasasal", "");
				this.context.put("nopajakan", "");
				this.context.put("jenistanah", "");
				this.context.put("basimati", "");
				this.context.put("bbsimati", "");
				this.context.put("tanggungan", "");
				this.context.put("jenisluas", "");
				this.context.put("catatan", "");
				this.context.put("noperserahan", "");
				this.context.put("buttonhtaam", "Tambah");

				logic.setDataHTA(id1);
				Vector listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				// context.put("DATEUTIL",new DateUtil());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			}
			/*
			 * else if ("tambah_htaam".equals(mode)) { id =
			 * getParam("idPermohonan"); id2 = getParam("idPemohon");
			 * 
			 * 
			 * this.context.put("id", id); this.context.put("idPemohon", id2);
			 * this.context.put("idSimati", id1);
			 * 
			 * //logic.setDataPenting(id1); //Vector listPenting =
			 * logic.getDataPenting();
			 * 
			 * //logic.setDataSaksi(id1); //Vector listSaksi =
			 * logic.getDataSaksi();
			 * 
			 * this.context.put("idhta", ""); this.context.put("noHakmilik",
			 * ""); this.context.put("nopt","");
			 * this.context.put("nilai_Hta_memohon", "");
			 * this.context.put("nilai_Hta_mati", "");
			 * this.context.put("kategori", "");
			 * this.context.put("jenishakmilik", "");
			 * this.context.put("pemilikan", ""); this.context.put("negeri",
			 * ""); this.context.put("daerah", ""); this.context.put("mukim",
			 * ""); this.context.put("luashmp",""); this.context.put("luasasal",
			 * ""); this.context.put("nocagaran", "");
			 * this.context.put("nopajakan",""); this.context.put("jenistanah",
			 * ""); this.context.put("basimati", "");
			 * this.context.put("bbsimati",""); this.context.put("jenishta","");
			 * this.context.put("tanggungan",""); this.context.put("jenisluas",
			 * ""); this.context.put("catatan","");
			 * this.context.put("noperserahan",""); this.context.put("idSimati",
			 * getParam("idSimati")); this.context.put("nopt","");
			 * this.context.put("nilai_Hta_memohon", "");
			 * this.context.put("nilai_Hta_mati","");
			 * this.context.put("kategori","");
			 * this.context.put("jenishakmilik","");
			 * this.context.put("pemilikan",""); this.context.put("luashmp","");
			 * this.context.put("luasasal","");
			 * this.context.put("nopajakan",""); this.context.put("jenistanah",
			 * ""); this.context.put("basimati","");
			 * this.context.put("bbsimati","");
			 * this.context.put("tanggungan","");
			 * this.context.put("jenisluas",""); this.context.put("catatan","");
			 * this.context.put("noperserahan","");
			 * this.context.put("show_simpan_add_htaam","yes");
			 * this.context.put("show_batal_add_htaam","yes");
			 * this.context.put("show_kemaskini_htaam","");
			 * this.context.put("show_save_update_htaam","");
			 * this.context.put("show_batal_update_htaam","");
			 * this.context.put("show_hapus_htaam","");
			 * this.context.put("show_kembali_htaam","");
			 * this.context.put("show_button","yes");
			 * this.context.put("show_htaa_update_table","");
			 * this.context.put("show_htaa_add_table","yes");
			 * 
			 * vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp"; }
			 */
			else if ("masukkan".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				addHtaam(session);
				Vector listHTA = logiconline.getDataHTA(id);

				this.context.put("listHTA", listHTA);
				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();

				// context.put("DATEUTIL",new DateUtil());

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				hideTabPengesahan = checkEmptyField(getParam("idPermohonanp"));
				context.put("hideTabPengesahan", hideTabPengesahan);

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			}

			else if ("negerichange".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", disability1);

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeri", idnegeri);
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
				this.context.put("idSimati", getParam("idSimati"));

				this.context.put("nopt", getParam("txtNoPTHtaam"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaam"));
				this.context.put("nilai_Hta_mati",
						getParam("txtNilaiTarikhMatiHtaam"));
				this.context.put("kategori", getParam("socKategoriTanahHtaam"));
				this.context.put("jenishakmilik",
						getParam("socJenisHakmilikHtaam"));
				this.context.put("pemilikan",
						getParam("socStatusPemilikanHtaam"));

				this.context.put("luashmp", getParam("txtLuasHMpHtaam"));
				this.context.put("luasasal", getParam("txtLuasAsalHtaam"));

				this.context.put("nopajakan", getParam("txtNoPajakan"));
				this.context.put("jenistanah", getParam("socJenisTanahHtaam"));
				this.context.put("basimati", getParam("txtBahagianSimati1"));
				this.context.put("bbsimati", getParam("txtBahagianSimati2"));

				this.context.put("tanggungan", getParam("txtTanggunganHtaam"));
				this.context.put("jenisluas", getParam("socJenisLuasHtaam"));

				this.context.put("catatan", getParam("txtCatatanHtaam"));
				this.context.put("noperserahan", getParam("txtNoPersHtaam"));

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_button", "yes");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				this.context.put("sekatan", getParam("txtSekatan"));
				this.context.put("syaratNyata", getParam("txtSyaratNyata"));
				
				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			}

			else if ("negerichangeup".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", disability1);

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamUp"));

				this.context.put("negeriup", idnegeri);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("negeriup", idnegeri);
				this.context.put("daerahup", "");
				this.context.put("mukimup", "");
				Vector v = new Vector();
				Hashtable k = new Hashtable();
				k.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
				k.put("idSimati", getParam("idSimati"));
				k.put("idhta", getParam("id_htaam"));
				k.put("nopt", getParam("txtNoPTHtaamUp"));
				k.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHt"));
				k.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamUpd"));
				k.put("kategori", getParam("socKategoriTanahHtaamUp"));
				k.put("jenishakmilik", getParam("socJenisHakmilikHtaamUp"));
				k.put("pemilikan", getParam("socStatusPemilikanHtaamUpd"));
				k.put("negeri", idnegeri);
				k.put("daerah", "");
				k.put("luashmp", getParam("txtLuasHMpHtaamUpd"));
				k.put("luasasal", getParam("txtLuasAsalHtaamUpd"));
				k.put("nopajakan", getParam("txtNoPajakanUp"));
				k.put("jenistanah", getParam("socJenisTanahHtaamUpd"));
				k.put("basimati", getParam("txtBahagianSimati1Up"));
				k.put("bbsimati", getParam("txtBahagianSimati2Up"));
				k.put("tanggungan", getParam("txtTanggunganHtaamUp"));
				k.put("jenisluas", getParam("socJenisLuasHtaamUpd"));
				k.put("catatan", getParam("txtCatatanHt"));
				k.put("noperserahan", getParam("txtNoPersHtaamUp"));
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				k.put("sekatan", getParam("txtSekatan"));
				k.put("syaratNyata", getParam("txtSyaratNyata"));
				
				v.addElement(k);

				this.context.put("listHTAid", v);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";

			} else if ("daerahchangeup".equals(mode)) {

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamUp"));
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamUp"));

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				this.context.put("negeriup", idnegeri);
				this.context.put("daerahup", "");
				this.context.put("mukimup", "");
				Vector v = new Vector();
				Hashtable h = new Hashtable();

				// this.context.put("idhta", "");
				h.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
				h.put("idSimati", getParam("idSimati"));
				h.put("idhta", getParam("id_htaam"));

				h.put("nopt", getParam("txtNoPTHtaamUp"));
				h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHt"));
				h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamUpd"));
				h.put("kategori", getParam("socKategoriTanahHtaamUp"));
				h.put("jenishakmilik", getParam("socJenisHakmilikHtaamUp"));
				h.put("pemilikan", getParam("socStatusPemilikanHtaamUpd"));
				h.put("negeri", idnegeri);
				h.put("daerah", iddaerah);
				h.put("mukim", "");
				h.put("luashmp", getParam("txtLuasHMpHtaamUpd"));
				h.put("luasasal", getParam("txtLuasAsalHtaamUpd"));
				h.put("nopajakan", getParam("txtNoPajakanUp"));
				h.put("jenistanah", getParam("socJenisTanahHtaamUpd"));
				h.put("basimati", getParam("txtBahagianSimati1Up"));
				h.put("bbsimati", getParam("txtBahagianSimati2Up"));
				h.put("tanggungan", getParam("txtTanggunganHtaamUp"));
				h.put("jenisluas", getParam("socJenisLuasHtaamUpd"));

				h.put("catatan", getParam("txtCatatanHt"));
				h.put("noperserahan", getParam("txtNoPersHtaamUp"));
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", getParam("txtSekatan"));
				h.put("syaratNyata", getParam("txtSyaratNyata"));
				
				v.addElement(h);

				this.context.put("listHTAid", v);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");
				this.context.put("show_button", "yes");

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			} else if ("getHtaam".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				// ID RECORD
				String idhtaam = (String) getParam("idhtaam");

				logic.setDataHTAbyIdHtaam(idhtaam);
				Vector listHTAid = logic.getDataHTAbyIdHtaam();
				this.context.put("listHTAid", listHTAid);
				this.context.put("idhtaam", idhtaam);

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listHTA = logiconline.getDataHTA(id);
				this.context.put("listHTA", listHTA);
				if (listHTA.size() > 0) {
					Hashtable f = (Hashtable) listHTAid.get(0);
					int idnegeri = Integer.parseInt(f.get("negeri").toString());
					int iddaerah = Integer.parseInt(f.get("daerah").toString());

					Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
							.getListMukimbyDaerah(iddaerah);
					this.context.put("listMukimbyDaerah", listmukim);
					Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
							.getListMukimbyDaerah(iddaerah);
					this.context.put("listmukim", listmukimbyDaerah);

					Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
							.getListDaerahbyNegeri(idnegeri);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
					Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
							.getListDaerahbyNegeri(idnegeri);
					this.context.put("listdaerah", listdaerahbynegeri);
				}

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", disability1);

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");

				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");

				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				initTabData();

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			} else if ("batalHtaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				Vector listHTA = logiconline.getDataHTA(id1);
				this.context.put("listHTA", listHTA);

				String idhtaam = getParam("idhtaamid");
				logic.setDataHTAbyIdHtaam(idhtaam);
				Vector listHTAid = logic.getDataHTAbyIdHtaam();
				this.context.put("listHTAid", listHTAid);
				this.context.put("idhtaam", idhtaam);

				Hashtable f = (Hashtable) listHTAid.get(0);
				int idnegeri = Integer.parseInt(f.get("negeri").toString());
				int iddaerah = Integer.parseInt(f.get("daerah").toString());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukimbyDaerah);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerahbynegeri);

				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "");

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");

				this.context.put("show_kembali_htaam", "");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

				initTabData();

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			}

			else if ("hapusHtaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				String idhtaam = (String) getParam("idhtaamid");
				FrmPrmhnnSek8Data.deleteHtaam(idhtaam);
				sum_nilai_ppkpermohonan(session, id, id1, idpermohonansimati);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);

				initTabData();

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				// context.put("DATEUTIL",new DateUtil());
				Vector listHTA = logiconline.getDataHTA(id);
				this.context.put("listHTA", listHTA);

				if (listHTA.size() > 0) {
					Hashtable f = (Hashtable) listHTA.get(0);
					int idnegeri = Integer.parseInt(f.get("negeri").toString());
					int iddaerah = Integer.parseInt(f.get("daerah").toString());

					Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
							.getListMukimbyDaerah(iddaerah);
					this.context.put("listMukimbyDaerah", listmukim);
					Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
							.getListMukimbyDaerah(iddaerah);
					this.context.put("listmukim", listmukimbyDaerah);

					Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
							.getListDaerahbyNegeri(idnegeri);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
					Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
							.getListDaerahbyNegeri(idnegeri);
					this.context.put("listdaerah", listdaerahbynegeri);
				}

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("mukim", "");

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");

				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_button", "");

				this.context.put("show_kembali_htaam", "yes");

				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");

				hideTabPengesahan = checkEmptyField(getParam("idPermohonan"));
				context.put("hideTabPengesahan", hideTabPengesahan);

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			} else if ("kemaskiniHtaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);
				;

				String idhtaam = (String) getParam("idhtaamid");
				logic.setDataHTAbyIdHtaam(idhtaam);
				this.context.put("idhtaam", idhtaam);

				Vector listHTA = logiconline.getDataHTA(id);
				this.context.put("listHTA", listHTA);

				Vector listHTAid = logic.getDataHTAbyIdHtaam();
				this.context.put("listHTAid", listHTAid);

				Hashtable f = (Hashtable) listHTAid.get(0);
				int idnegeri = Integer.parseInt(f.get("negeri").toString());
				int iddaerah = Integer.parseInt(f.get("daerah").toString());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukimbyDaerah);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listdaerah", listdaerahbynegeri);

				this.context.put("readmodenegeri", disability1);
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "");

				this.context.put("show_simpan_add_htaam", "");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_htaa_add_table", "");

				initTabData();

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			} else if ("simpanHtaam".equals(mode)) {
				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				String idhtaam = getParam("idhtaamid");
				this.context.put("idhtaam", idhtaam);

				updateHtaam(session);

				Vector listHTA = logiconline.getDataHTA(id);
				this.context.put("listHTA", listHTA);

				logic.setDataHTAbyIdHtaam(idhtaam);
				Vector listHTAid = logic.getDataHTAbyIdHtaam();
				this.context.put("listHTAid", listHTAid);
				this.context.put("idhtaam", idhtaam);

				Hashtable f = (Hashtable) listHTAid.get(0);
				int idnegeri = Integer.parseInt(f.get("negeri").toString());
				int iddaerah = Integer.parseInt(f.get("daerah").toString());

				Vector listnegeri = FrmPrmhnnSek8DaftarSek8Data.getListnegeri();
				this.context.put("listnegeri", listnegeri);

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Vector listmukimbyDaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listmukim", listmukimbyDaerah);

				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Vector listdaerahbynegeri = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(idnegeri);

				this.context.put("listdaerah", listdaerahbynegeri);
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", disability1);
				this.context.put("readmodemukim", disability1);
				this.context.put("readmode", "");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_kembali_htaam", "");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");
				initTabData();
				this.context.put("SimpanStatus", getParam("simpanStatus"));

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			} else if ("daerahchange".equals(mode)) {

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("readmode", "");
				this.context.put("readmodenegeri", "");
				this.context.put("readmodedaerah", "");
				this.context.put("readmodemukim", "");

				int iddaerah = Integer.parseInt(getParam("socDaerahHtaam"));
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));

				Vector listmukim = FrmPrmhnnSek8DaftarSek8Data
						.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				this.context.put("negeri", idnegeri);
				this.context.put("daerah", iddaerah);
				this.context.put("mukim", "");
				this.context.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("nopt", getParam("txtNoPTHtaam"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaam"));
				this.context.put("nilai_Hta_mati",
						getParam("txtNilaiTarikhMatiHtaam"));
				this.context.put("kategori", getParam("socKategoriTanahHtaam"));
				this.context.put("jenishakmilik",
						getParam("socJenisHakmilikHtaam"));
				this.context.put("pemilikan",
						getParam("socStatusPemilikanHtaam"));
				this.context.put("luashmp", getParam("txtLuasHMpHtaam"));
				this.context.put("luasasal", getParam("txtLuasAsalHtaam"));
				this.context.put("nopajakan", getParam("txtNoPajakan"));
				this.context.put("jenistanah", getParam("socJenisTanahHtaam"));
				this.context.put("basimati", getParam("txtBahagianSimati1"));
				this.context.put("bbsimati", getParam("txtBahagianSimati2"));
				this.context.put("tanggungan", getParam("txtTanggunganHtaam"));
				this.context.put("jenisluas", getParam("socJenisLuasHtaam"));
				this.context.put("catatan", getParam("txtCatatanHtaam"));
				this.context.put("noperserahan", getParam("txtNoPersHtaam"));
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kemaskini_htaam", "");
				this.context.put("show_save_update_htaam", "");
				this.context.put("show_batal_update_htaam", "");
				this.context.put("show_hapus_htaam", "");
				this.context.put("show_button", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "");
				this.context.put("show_htaa_add_table", "yes");
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				this.context.put("sekatan", getParam("txtSekatan"));
				this.context.put("syaratNyata", getParam("txtSyaratNyata"));

				vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
			}
			vm = "app/ppk/frmPrmhnnBorangAHTAAH.jsp";
		} else if ("pengesahan_permohonan".equals(submit)) {
			initTabData();

			Vector list = logiconline.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);
			Hashtable n = (Hashtable) list.get(0);
			String id1 = (String) n.get("idsimati");
			String id2 = (String) n.get("idPemohon");
			String id = getParam("idPermohonan");
			this.context.put("idStatus", (String) n.get("idstatus"));
			this.context.put("IdPermohonan", id);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);
			if (n.get("nopermohonanonline") == "")
				n.put("nopermohonanonline", "");
			this.context.put("nopermohonanonline", n.get("nopermohonanonline")
					.toString());

			if (n.get("flaghubungan") == "")
				n.put("flaghubungan", "0");
			int flaghub = Integer.parseInt(n.get("flaghubungan").toString());
			this.context.put("idpermohonansimati", Integer.parseInt(n.get(
					"idpermohonansimati").toString()));
			this.context.put("flaghub", flaghub);

			// negeri based on negeri hta
			if (n.get("idpermohonansimati") == "")
				n.put("idpermohonansimati", "0");
			Vector listNegeriByPpkUnit = logiconline
					.getListNegeriByhta((String) getParam("idpermohonansimati"));
			this.context.put("senaraiNegeriByPpkUnit", listNegeriByPpkUnit);
			this.context.put("saizData", listNegeriByPpkUnit.size());

			if (n.get("idnegerimhn").toString() != "") {
				int idnegerimhn = Integer.parseInt(n.get("idnegerimhn")
						.toString());
				int iddaerahmhn = Integer.parseInt(n.get("iddaerahmhn")
						.toString());

				this.context.put("selNegeri", 0);
				this.context.put("selDaerah", 0);
				this.context.put("negerimhn", idnegerimhn);
				this.context.put("daerahmhn", iddaerahmhn);

				// Vector getPpkAddressDaerah =
				// logiconline.getListDaerahByPpkUnit(idnegerimhn);
				Vector listnegeribydaerah = logiconline.getListDaerahByhta(
						idnegerimhn, id1);
				// FrmPrmhnnSek8DaftarSek8Data.getListDaerahbyNegerihta(idnegerimhn,id);
				// logiconline.getListDaerahByhta(idnegerimhn);
				//	            
				this.context.put("selectedDaerah", listnegeribydaerah);

				Vector getPpkAddressNegerimhn = logiconline
						.getListDaerahByPpkUnitSelected(idnegerimhn,
								iddaerahmhn);
				this.context
						.put("selectedDaerahByUnit", getPpkAddressNegerimhn);

				Vector getAddressPpkDaerahmhn = logiconline.getAddress(Integer
						.parseInt(n.get("iddaerahmhn").toString()));
				this.context.put("selectedPpkAddress", getAddressPpkDaerahmhn);
				context.put("disabled", "readonly class = \"disabled\"");
				context.put("readonly", "readonly");
				// context.put("disabledDropdown","disabled readonly class = \"disabled\"");

			} else if (n.get("idnegerimhn").toString() != ""
					&& n.get("iddaerahmhn").toString() != "") {
				Vector getAddressPpkDaerahmhn = logiconline.getAddress(Integer
						.parseInt(n.get("iddaerahmhn").toString()));
				this.context.put("selectedPpkAddress", getAddressPpkDaerahmhn);
			} else {

				initInputPpkPengesahan();
			}

			if ("pengesahanView".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("selNegeri", "0");
				this.context.put("selDaerah", "0");
				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				if (n.get("idnegerimhn").toString() != "") {
					int iddaerahmhn = Integer.parseInt(n.get("iddaerahmhn")
							.toString());
					Vector getAddressPpkDaerahmhn = logiconline
							.getAddress(iddaerahmhn);
					this.context.put("selectedPpkAddress",
							getAddressPpkDaerahmhn);

					Hashtable m = (Hashtable) getAddressPpkDaerahmhn.get(0);
					String namaPejabat = m.get("namapejabat").toString();
					String alamatOne = m.get("alamatOne").toString();
					String alamatTwo = m.get("alamatTwo").toString();
					String alamatThree = m.get("alamatThree").toString();
					String poskod = m.get("poskod").toString();
					String notel = m.get("notel").toString();
					String notel_sambungan = m.get("notel_sambungan")
							.toString();
					String negeriNama = m.get("negerinama").toString();

					this.context.put("namapejabat", namaPejabat);
					this.context.put("alamat1", alamatOne);
					this.context.put("alamat2", alamatTwo);
					this.context.put("alamat3", alamatThree);
					this.context.put("poskod", poskod);
					this.context.put("no_tel", notel);
					this.context.put("no_tel_samb", notel_sambungan);

				} else {
					Vector getPpkAddress = logiconline.getListNegeriByPpkUnit();
					this.context.put("selectedPpkAddress", getPpkAddress);
					initInputPpkPengesahan();
				}
			} else if ("selection_daerah".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("selDaerah", "0");

				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				if (!getParam("socNegeriPengesahan").equals("")) {
					int idnegeri = Integer
							.parseInt(getParam("socNegeriPengesahan"));
					this.context.put("selNegeri",
							getParam("socNegeriPengesahan"));

					Vector listnegeribydaerah = logiconline.getListDaerahByhta(
							Integer.parseInt(getParam("socNegeriPengesahan")),id1);
					// FrmPrmhnnSek8DaftarSek8Data.getListDaerahbyNegeri(Integer.parseInt(getParam("socNegeriPengesahan")));
					this.context.put("selectedDaerah", listnegeribydaerah);

				} else {
					this.context.put("selectedDaerah", "");
					this.context.put("selNegeri", 0);
				}
			} else if ("ppkAddressView".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("selNegeri", getParam("socNegeriPengesahan"));
				this.context.put("selDaerah", getParam("socDaerahPengesahan"));
				Vector listnegeribydaerah = FrmPrmhnnSek8DaftarSek8Data
						.getListDaerahbyNegeri(Integer
								.parseInt(getParam("socNegeriPengesahan")));
				this.context.put("selectedDaerah", listnegeribydaerah);
				Vector getAddressPpk = logiconline.getAddress(Integer
						.parseInt(getParam("socDaerahPengesahan")));
				this.context.put("selectedPpkAddress", getAddressPpk);
			}
			vm = "app/ppk/frmPrmhnnBorangAPengesahan.jsp";
		} else if ("cetak_surat".equals(submit)) {
			initTabData();
			Vector list = logiconline.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);
			Hashtable n = (Hashtable) list.get(0);
			String id1 = (String) n.get("idsimati");
			String id2 = (String) n.get("idPemohon");
			String id = getParam("idPermohonan");
			System.out.println("id status permohonan :"
					+ (String) n.get("idstatus"));
			this.context.put("idStatus", (String) n.get("idstatus"));
			this.context.put("command", "cetak_surat");
			String idPermohonan = getParam("idPermohonan");
			String temp = getParam("socNegeriPengesahan");
			if (temp.equals("")) {
				temp = "0";
			}
			String temp2 = getParam("socDaerahPengesahan");
			if (temp2.equals("")) {
				temp2 = "0";
			}
			int idnegeri = Integer.parseInt(temp);
			int iddaerah = Integer.parseInt(temp2);
			this.context.put("selNegeri", getParam("socNegeriPengesahan"));
			this.context.put("selDaerah", getParam("socDaerahPengesahan"));
			context.put("disabledDropdown",
					"disabled readonly class = \"disabled\"");
			context.put("readonly", "readonly");
			Vector idfail = logiconline.getIdFail(idPermohonan);
			Hashtable t = (Hashtable) idfail.get(0);

			logiconline.insertDaerahMohon(idnegeri, iddaerah, idPermohonan,
					(String) session.getAttribute("_ekptg_user_id"), (String) t
							.get("noidfail"));

			Vector getAddressPpk = logiconline.getAddress(iddaerah);
			this.context.put("selectedPpkAddress", getAddressPpk);

			this.context.put("idStatus", logiconline
					.getIdStatus(getParam("idPermohonan")));
			vm = "app/ppk/frmPrmhnnBorangAPengesahan.jsp";

		} else if (getParam("command").equals("doChangeNegeri")) {
			String idNegeri = getParam("socNegeri");
			if (idNegeri == null || idNegeri.trim().length() == 0) {
				idNegeri = "0";
			}
			this.context.put("idNegeri", idNegeri);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
					Long.parseLong(idNegeri), null,
					" onChange=\"doChangeNegeri()\""));
			this.context.put("selectBandarByNegeri", HTML.SelectNegeri(
					"socBandar", Long.parseLong(idNegeri), ""));
			vm = "app/ppk/frmPrmhnnBorangAPenghutang.jsp";
		} else {
			Vector listDataPemohon = logiconline.getUserParticular((String) session.getAttribute("_ekptg_user_id"));
			Hashtable n = (Hashtable) listDataPemohon.get(0);

			this.context.put("noKpBaru1", n.get("icno1").toString());
			this.context.put("noKpBaru2", n.get("icno2").toString());
			this.context.put("noKpBaru3", n.get("icno3").toString());

			eventStatus = 0;
			this.context.put("EventStatus", eventStatus);

			idAlert = 0;
			this.context.put("IdAlert", idAlert);

			Vector view1 = FrmPrmhnnSek8DaftarSek8Data.getJenisKp();
			this.context.put("listkp", view1);

			// vm = "app/ppk/frmBorangAMaklumatPemohonKemaskini.jsp";
		}
		// this.context.put("idwaris", getParam("idwaris"));
		System.out.println(vm);
		return vm;
	}

	private void duplicateAddressPemohon(String idPemohon) throws Exception {
		Db db = null;

		try {
			db = new Db();
			String sql = "SELECT ALAMAT_1,ALAMAT_2,ALAMAT_3,POSKOD,ID_BANDAR,ID_NEGERI FROM TBLPPKPEMOHON WHERE ID_PEMOHON = '"
					+ idPemohon + "'";

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				this.context.put("alamat1",
						rs.getString("ALAMAT_1") == null ? "" : rs
								.getString("ALAMAT_1"));
				this.context.put("alamat2",
						rs.getString("ALAMAT_2") == null ? "" : rs
								.getString("ALAMAT_2"));
				this.context.put("alamat3",
						rs.getString("ALAMAT_3") == null ? "" : rs
								.getString("ALAMAT_3"));
				this.context.put("poskod", rs.getString("POSKOD") == null ? ""
						: rs.getString("POSKOD"));
				this.context
						.put(
								"selectNegeriTetap",
								HTML
										.SelectNegeri(
												"socNegeriTetap",
												Long
														.parseLong(rs
																.getString("ID_NEGERI")),
												null,
												"style=\"width:225px;text-transform:uppercase;\" onChange=\"javascript:onChangeBandarTetapWaris()\""));
				this.context
						.put(
								"selectBandarTetap",
								HTML
										.SelectBandarByNegeri(
												rs.getString("ID_NEGERI"),
												"socDaerah",
												Long
														.parseLong(rs
																.getString("ID_BANDAR")),
												"style=\"width:225px;text-transform:uppercase;\""));
				this.context.put("check4", "checked");
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	/*
	 * private void check_mode_pemohon(HttpSession session, String kpBaru,
	 * String kpLama, String kpLain) throws Exception {
	 * logiconline.checkPemohon(kpBaru,kpLama,kpLain); }
	 */
	public String checkEmptyField(String idpermohonan) throws Exception {
		Vector listEmptyField = null;
		// logiconline.checkFieldEmpty(idpermohonan);
		// listEmptyField = logiconline.getListEmptyField();
		String hideTabPengesahan = "";
		Hashtable hEmpty = null;
		int i = 0;
		if (listEmptyField.size() == 0) {

			hideTabPengesahan = "hide";
		} else if (listEmptyField.size() == 1) {

			hEmpty = (Hashtable) listEmptyField.get(0);

			if ("".equals(hEmpty.get("ID_BUKTIMATI"))
					|| "".equals(hEmpty.get("TARIKH_MATI"))
					|| "".equals(hEmpty.get("SEBAB_MATI"))
					|| "".equals(hEmpty.get("ALAMAT_1"))
					|| "".equals(hEmpty.get("ALAMAT1_SURAT"))
					|| "".equals(hEmpty.get("POSKOD"))
					|| "".equals(hEmpty.get("POSKOD_SURAT"))
					|| "0".equals(hEmpty.get("ID_NEGERI"))
					|| "".equals(hEmpty.get("ID_NEGERISURAT"))
					|| hEmpty.get("ID_HTA").equals("0")) {

				hideTabPengesahan = "hide";

			}

		} else {
			hideTabPengesahan = "show";
		}

		return hideTabPengesahan;
	}

	private void check_data_pemohon(HttpSession session, String kpBaru,
			String kpLama, String kpLain) throws Exception {
		logiconline.semakDetailPemohon(kpBaru, kpLama, kpLain);
	}

	private void sum_nilai_ppkpermohonan(HttpSession session, String id,
			String id1, String idpermohonansimati) throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8DaftarSek8Data
				.updateDataNilai(id, id1, idpermohonansimati);
	}

	private void delete_mode_ppkha(HttpSession session, String id1, String id3)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8DaftarSek8Data.deleteDataHa(id1, id3);
	}

	/*
	 * private void view_data_ppkha(HttpSession session, int id1, int id3)
	 * throws Exception { //int id2 = Integer.parseInt(getParam("id2"));
	 * FrmPrmhnnSek8DaftarSek8Data.setSelectedDataHa(id1,id3); }
	 */
	private void view_mode_ppkha(HttpSession session, String idpermohonansimati)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logicSek8Internal.setDataHa(idpermohonansimati);
	}

	private void view_sum_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8DaftarSek8Data.setDataHa(id1);
	}

	private void view_overallsum_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8DaftarSek8Data.setOverallSum(id1);
	}

	private void view_overallsummati_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8DaftarSek8Data.setOverallSumMati(id1);
	}

	private void view_mode_ppkhta(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
		frmPrmhnnSek8SenaraiHTATHData.setDataHta(id1);
	}

	private void view_mode_ppkha2(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
		frmPrmhnnSek8SenaraiHTATHData.setDataHa(id1);
	}

	private void view_mode_ppkhtath(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		FrmPrmhnnSek8SenaraiHTATHData frmPrmhnnSek8SenaraiHTATHData = new FrmPrmhnnSek8SenaraiHTATHData();
		frmPrmhnnSek8SenaraiHTATHData.setDataHtath(id1);
	}

	private void insertNewDataSimati(HttpSession session, String idPermohonan)
			throws Exception {
		Hashtable h = null;
		h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("IdPermohonan", getParam("idPermohonan"));
		h.put("noKPBaru", getParam("txtNoKPBaru1Simati")
				+ getParam("txtNoKPBaru2Simati")
				+ getParam("txtNoKPBaru3Simati"));
		h.put("noKPLama", getParam("txtNoKPLamaSimati"));
		h.put("jenisKPLain", getParam("socJenisKPLainSimati"));
		h.put("noKpLain", getParam("txtNoKPLainSimati"));
		h.put("namaSimati", getParam("txtNamaSimati"));
		h.put("namaLainSimati", getParam("txtNamaLainSimati"));
		h.put("SocJantina", getParam("socJantina"));
		h.put("SocAgama", getParam("socAgama"));
		h.put("SocWarga", getParam("socWarganegara"));
		h.put("umurSimati", getParam("txtUmurSimati"));
		h.put("buktiMati", getParam("socBuktiMati"));
		h.put("noSijilMatiSimati", getParam("txtNoSijilMatiSimati"));
		h.put("tarikhMati", getParam("txdTarikhMati"));
		h.put("waktuKematianSimati", getParam("socWaktuKematianSimati"));
		h.put("tempatMati", getParam("txtTempatMati"));
		h.put("sebabKematianSimati", getParam("txtSebabKematianSimati"));
		h.put("alamatTerakhir1Simati", getParam("txtAlamatTerakhir1Simati"));
		h.put("alamatTerakhir2Simati", getParam("txtAlamatTerakhir2Simati"));
		h.put("alamatTerakhir3Simati", getParam("txtAlamatTerakhir3Simati"));
		h.put("poskodSimati", getParam("txtPoskodSimati"));
		h.put("bandarSimati", getParam("txtBandarSimati"));
		h.put("negeri", getParam("socNegeri"));
		h.put("catatanSimati", getParam("txtCatatanSimati"));
		h.put("idpermohonasimati", getParam("idpermohonansimati"));
		h.put("idtarafkptg", getParam("idtarafkptg"));
		h.put("idsaudara", getParam("idsaudara"));
		logiconline.insertDataSimati(h);
	}

	private void updateDataSimati(HttpSession session, String idPermohonan)
			throws Exception {

		Hashtable h = null;
		h = new Hashtable();
		h.put("idpermohonan", getParam("idPermohonan"));
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idSimati", getParam("idSimati"));
		h.put("noKPBaru", getParam("txtNoKPBaru1Simati")
				+ getParam("txtNoKPBaru2Simati")
				+ getParam("txtNoKPBaru3Simati"));
		h.put("noKPLama", getParam("txtNoKPLamaSimati"));
		h.put("jenisKPLain", getParam("socJenisKPLainSimati"));
		h.put("noKpLain", getParam("txtNoKPLainSimati"));
		h.put("namaSimati", getParam("txtNamaSimati"));
		h.put("namaLainSimati", getParam("txtNamaLainSimati"));
		h.put("SocJantina", getParam("socJantina"));
		h.put("SocAgama", getParam("socAgama"));
		h.put("SocWarga", getParam("socWarganegara"));
		h.put("umurSimati", getParam("txtUmurSimati"));
		h.put("buktiMati", getParam("socBuktiMati"));
		h.put("noSijilMatiSimati", getParam("txtNoSijilMatiSimati"));
		h.put("tarikhMati", getParam("txdTarikhMati"));
		h.put("waktuKematianSimati", getParam("socWaktuKematianSimati"));
		h.put("tempatMati", getParam("txtTempatMati"));
		h.put("sebabKematianSimati", getParam("txtSebabKematianSimati"));
		h.put("alamatTerakhir1Simati", getParam("txtAlamatTerakhir1Simati"));
		h.put("alamatTerakhir2Simati", getParam("txtAlamatTerakhir2Simati"));
		h.put("alamatTerakhir3Simati", getParam("txtAlamatTerakhir3Simati"));
		h.put("poskodSimati", getParam("txtPoskodSimati"));
		h.put("bandarSimati", getParam("txtBandarSimati"));
		if (getParam("socNegeri") == "" || getParam("socNegeri") == "0") {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeri")));
		}
		if (getParam("socDaerah") == "" || getParam("socDaerah") == "") {
			h.put("bandar", 0);
		} else {
			h.put("bandar", Integer.parseInt(getParam("socDaerah")));
		}
		h.put("catatanSimati", getParam("txtCatatanSimati"));
		logiconline.updateDataSimati(h);
	}

	private void updateNewDataPemohon(HttpSession session, String idPermohonan)
			throws Exception {

		String kp_Waris = getParam("txtnoKpBaru1Pemohon")
				+ getParam("txtnoKpBaru2Pemohon")
				+ getParam("txtnoKpBaru3Pemohon");
		// int cntWujudWarisDariPemohon =
		// FrmPrmhnnSek8SecaraOnlineData.cntWujudWarisDariPemohon(kp_Waris,getParam("txtNoKPLamaPemohon"),getParam("txtNoKPLainPemohon"),getParam("idPermohonan"));

		logic.setCheckWaris(kp_Waris, getParam("txtNoKPLamaPemohon"),
				getParam("txtNoKPLainPemohon"), getParam("idPermohonan"));
		Vector checkWaris = logic.getCheckWaris();
		String cntValWaris = "";
		if (checkWaris.size() != 0) {
			Hashtable cw = (Hashtable) checkWaris.get(0);
			cntValWaris = (String) cw.get("cntWaris");
		}

		Hashtable h = null;
		h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("IdPermohonan", getParam("idPermohonan"));
		h.put("idPemohon", getParam("idPemohon"));
		h.put("noKPLamaPemohon", getParam("txtNoKPLamaPemohon"));
		h.put("namaPemohonPemohon", getParam("txtNamaPemohonPemohon"));
		h.put("jenisKP", getParam("socJenisKP"));
		h.put("noKpLain", getParam("txtNoKPLainPemohon"));
		h.put("jenisTaraf", getParam("socTaraf"));
		h.put("talianSaudara", getParam("socTalianSaudara"));
		h.put("jantina", getParam("socJantina"));
		h.put("agama", getParam("socAgama"));
		h.put("warga", getParam("socWarga"));
		h.put("umurPemohon", getParam("txtUmurPemohon"));
		h.put("alamatTerakhir1Pemohon", getParam("txtAlamatTerakhir1Pemohon"));
		h.put("alamatTerakhir2Pemohon", getParam("txtAlamatTerakhir2Pemohon"));
		h.put("alamatTerakhir3Pemohon", getParam("txtAlamatTerakhir3Pemohon"));
		h.put("poskodPemohon", getParam("txtPoskodPemohon"));
		h.put("bandarPemohon", getParam("txtBandarPemohon"));
		h.put("noTelefonPemohon", getParam("txtNoTelefonPemohon"));
		h.put("noTelefonBimbitPemohon", getParam("txtNoTelefonBimbitPemohon"));
		h.put("noFaksPemohon", getParam("txtNoFaksPemohon"));
		h.put("emelPemohon", getParam("txtEmelPemohon"));
		h.put("catatanPemohon", getParam("txtCatatanPemohon"));
		h.put("kp_Waris", kp_Waris);

		String newIcNo = getParam("txtnoKpBaru1Pemohon")
				+ getParam("txtnoKpBaru2Pemohon")
				+ getParam("txtnoKpBaru3Pemohon");
		h.put("newIcNo", newIcNo);

		// if(getParam("checkPeguam")=="1"){
		h.put("peguam", getParam("checkPeguam"));
		// }
		// else{
		// h.put("peguam", "T");
		// }

		h.put("wujudWaris", cntValWaris);
		h.put("chcAlamat", getParam("chcAlamat"));
		h.put("alamatSurat1Pemohon", getParam("txtAlamatSurat1Pemohon"));
		h.put("alamatSurat2Pemohon", getParam("txtAlamatSurat2Pemohon"));
		h.put("alamatSurat3Pemohon", getParam("txtAlamatSurat3Pemohon"));
		h.put("poskodSuratPemohon", getParam("txtPoskodSuratPemohon"));
		h.put("bandarSuratPemohon", getParam("txtBandarSuratPemohon"));

		if (getParam("socNegeriTetap").equals("")
				|| getParam("socNegeriTetap").equals("0")) {
			h.put("negeri", "0");

		} else {
			h.put("negeri", getParam("socNegeriTetap"));
		}
		if (getParam("socSuratNegeri").equals("")
				|| getParam("socSuratNegeri").equals("0")) {
			h.put("negeriSurat", "0");

		} else {
			h.put("negeriSurat", getParam("socSuratNegeri"));
		}
		if (getParam("socDaerah").equals("")
				|| getParam("socDaerah").equals("0")) {
			h.put("id_bandar", 0);

		} else {
			h.put("id_bandar", Integer.parseInt(getParam("socDaerah")));
		}
		if (getParam("socDaerahSurat").equals("")
				|| getParam("socDaerahSurat").equals("0")) {
			h.put("id_bandarsurat", 0);
		} else {
			h.put("id_bandarsurat", Integer
					.parseInt(getParam("socDaerahSurat")));
		}

		logiconline.updateDataPemohon(h);
	}

	private void insertNewDataPeguam(HttpSession session, String idPemohon)
			throws Exception {
		Hashtable k = new Hashtable();
		k.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		k.put("idPermohonan", getParam("idPermohonan"));
		k.put("idPemohon", idPemohon);
		k.put("firma", getParam("txtNamaFirmaPeguam"));
		k.put("rujfirma", getParam("txtNoRujukanFirma"));
		k.put("alamat1", getParam("txtAlamat1Peguam"));
		k.put("alamat2", getParam("txtAlamat2Peguam"));
		k.put("alamat3", getParam("txtAlamat3Peguam"));
		k.put("poskod", getParam("txtPoskodPeguam"));
		k.put("bandar", getParam("txtBandarPeguam"));
		if (getParam("socNegeriTetap") == "") {
			k.put("idnegeri", 0);
		} else {
			k.put("idnegeri", Integer.parseInt(getParam("socNegeriTetap")));
		}
		if (getParam("socBandarTetap") == "") {
			k.put("idbandar", 0);
		} else {
			k.put("idbandar", Integer.parseInt(getParam("socBandarTetap")));
		}
		k.put("noTel", getParam("txtNomborTelefonPeguam"));
		k.put("noFax", getParam("txtNomborFaksPeguam"));
		k.put("emel", getParam("txtEmelPeguam"));
		System.out.println("getParam  socDaerah-->>" + getParam("socDaerah"));
		logiconline.insertpeguam(k);
	}

	private void updatepeguam(HttpSession session, String idPemohon)
			throws Exception {
		Hashtable k = new Hashtable();
		k.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		k.put("idPermohonan", getParam("idPermohonan"));
		k.put("idPeguam", getParam("idPeguam"));
		k.put("idPemohon", idPemohon);
		k.put("firma", getParam("txtNamaFirmaPeguam"));
		k.put("rujfirma", getParam("txtNoRujukanFirma"));
		k.put("alamat1", getParam("txtAlamat1Peguam"));
		k.put("alamat2", getParam("txtAlamat2Peguam"));
		k.put("alamat3", getParam("txtAlamat3Peguam"));
		k.put("poskod", getParam("txtPoskodPeguam"));
		k.put("bandar", getParam("txtBandarPeguam"));
		if (getParam("socNegeriTetap") == "") {
			k.put("idnegeri", 0);
		} else {
			k.put("idnegeri", Integer.parseInt(getParam("socNegeriTetap")));
		}
		if (getParam("socBandarTetap") == "") {
			k.put("idbandar", 0);
		} else {
			k.put("idbandar", Integer.parseInt(getParam("socBandarTetap")));
		}
		k.put("noTel", getParam("txtNomborTelefonPeguam"));
		k.put("noFax", getParam("txtNomborFaksPeguam"));
		k.put("emel", getParam("txtEmelPeguam"));
		FrmPrmhnnSek8Data.updatepeguam(k);
	}

	private void addWarisBerikut(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idSimati", getParam("txtIdSimatiWaris"));
		h.put("namaOB", getParam("txtNamaOBWaris"));

		long idOB_gabung = DB.getNextID("TBLPPKOB_SEQ");

		String kp_Waris = getParam("txtNoKPBaru1Waris")
				+ getParam("txtNoKPBaru2Waris") + getParam("txtNoKPBaru3Waris");
		h.put("idOB_gabung", idOB_gabung);
		h.put("nokpbaru", kp_Waris);
		h.put("nokpwaris", getParam("txtNoKPLamaWaris"));
		h.put("notel", getParam("txtNoTeleponWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		h.put("jenisKp", getParam("socJenisKPLainWaris"));
		h.put("nokplain", getParam("txtNoKPLainWaris"));
		h.put("idparent", getParam("txtIdParent"));
		if (getParam("txtLapisParent").equals("")) {
			h.put("lapis", 0);
		} else {
			h.put("lapis", Integer.parseInt(getParam("txtLapisParent")));
		}
		h.put("jantina", getParam("socJantinaWaris"));
		h.put("agama", getParam("socAgamaWaris"));
		h.put("warga", getParam("socWarganegaraWaris"));
		h.put("tarikhlahir", getParam("txdTarikhLahirWaris"));
		h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		h.put("poskod", getParam("txtPoskodWaris"));
		h.put("bandar", getParam("txtBandarWaris"));
		h.put("alamatsurat1", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamatsurat2", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamatsurat3", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodsurat", getParam("txtPoskodWarisSurat"));

		if (getParam("socNegeriTetap").equals("")) {
			h.put("negeri", "0");
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriTetap")));
		}
		if (getParam("socSuratNegeri").equals("")) {
			h.put("negerisurat", 0);
		} else {
			h.put("negerisurat", Integer.parseInt(getParam("socSuratNegeri")));
		}
		if (getParam("socDaerah").equals("")) {
			h.put("id_bandar", 0);
		} else {
			h.put("id_bandar", Integer.parseInt(getParam("socDaerah")));
		}
		if (getParam("socDaerahSurat").equals("")) {
			h.put("id_bandarsurat", 0);
		} else {
			h.put("id_bandarsurat", Integer
					.parseInt(getParam("socDaerahSurat")));
		}

		// if(getParam("socNegeriTetap").equals(""))
		// {
		// h.put("negeri",0);
		// }else
		// {
		// h.put("negeri", Integer.parseInt(getParam("socNegeriTetap")));
		// }
		// if(getParam("socNegeriSurat").equals(""))
		// {
		// h.put("negerisurat",0);
		// }else
		// {
		// h.put("negerisurat", Integer.parseInt(getParam("socNegeriSurat")));
		// }
		// if(getParam("socDaerahTetap").equals(""))
		// {
		// h.put("daerahtetap",0);
		// }else
		// {
		// h.put("daerahtetap", Integer.parseInt(getParam("daerahtetap")));
		// }
		// if(getParam("socDaerahSurat").equals(""))
		// {
		// h.put("daerahsurat",0);
		// }else
		// {
		// h.put("daerahsurat", Integer.parseInt(getParam("socDaerahSurat")));
		// }
		h.put("catatan", getParam("txtCatatanWaris"));

		if (getParam("socSaudaraWaris").equals("")) {
			h.put("saudara", 0);
		} else {
			h.put("saudara", Integer.parseInt(getParam("socSaudaraWaris")));
		}

		if (getParam("socStatusOBWaris").equals("")) {
			h.put("statusWaris", 0);
		} else {
			h
					.put("statusWaris", Integer
							.parseInt(getParam("socStatusOBWaris")));
		}

		if (getParam("checkHidupWaris").equals("")) {
			h.put("checkmati", "0");
		} else {
			h.put("checkmati", "1");
		}

		if (getParam("txtUmurWaris").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));
		}
		h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
		h.put("waktumatiwaris", getParam("txtWaktuKematianWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		FrmPrmhnnSek8Data.addWarisBerikut(h);
	}

	private void addWaris(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idSimati", getParam("txtIdSimatiWaris"));
		h.put("namaOB", getParam("txtNamaOBWaris"));
		String kp_Waris = getParam("txtNoKPBaru1Waris")
				+ getParam("txtNoKPBaru2Waris") + getParam("txtNoKPBaru3Waris");
		h.put("nokpbaru", kp_Waris);
		h.put("nokpwaris", getParam("txtNoKPLamaWaris"));
		h.put("notel", getParam("txtNoTeleponWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		h.put("jenisKp", getParam("socJenisKPLainWaris"));
		h.put("nokplain", getParam("txtNoKPLainWaris"));
		h.put("jantina", getParam("socJantinaWaris"));
		h.put("agama", getParam("socAgamaWaris"));
		h.put("warga", getParam("socWarganegaraWaris"));
		h.put("tarikhlahir", getParam("txdTarikhLahirWaris"));
		h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		h.put("poskod", getParam("txtPoskodWaris"));
		h.put("bandar", getParam("txtBandarWaris"));
		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		// h.put("bandarSurat", getParam("txtBandarWarisSurat"));
		if (getParam("socNegeriTetap").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriTetap")));
		}
		if (getParam("socSuratNegeri").equals("")) {
			h.put("negerisurat", 0);
		} else {
			h.put("negerisurat", Integer.parseInt(getParam("socSuratNegeri")));
		}

		if (getParam("socDaerah").equals("")) {
			h.put("id_bandar", 0);
		} else {
			h.put("id_bandar", Integer.parseInt(getParam("socDaerah")));
		}
		if (getParam("socDaerahSurat").equals("")) {
			h.put("id_bandarsurat", 0);
		} else {
			h.put("id_bandarsurat", Integer
					.parseInt(getParam("socDaerahSurat")));
		}

		h.put("catatan", getParam("txtCatatanWaris"));

		if (getParam("socSaudaraWaris").equals("")) {
			h.put("saudara", 0);
		} else {
			h.put("saudara", Integer.parseInt(getParam("socSaudaraWaris")));
		}

		if (getParam("socStatusOBWaris").equals("")) {
			h.put("statusWaris", 0);
		} else {
			h
					.put("statusWaris", Integer
							.parseInt(getParam("socStatusOBWaris")));
		}

		if (getParam("checkHidupWaris").equals("")) {
			h.put("checkmati", "0");
		} else {
			h.put("checkmati", "1");
		}

		if (getParam("txtUmurWaris").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));
		}

		if (getParam("chkAddWaris").equals("")) {
			h.put("chkAddWaris", "0");
		} else {
			h.put("chkAddWaris", getParam("chkAddWaris"));
		}
		h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
		h.put("waktumatiwaris", getParam("txtWaktuKematianWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		FrmPrmhnnSek8Data.addWaris(h);
	}

	private void updatewaris(HttpSession session) throws Exception {
		String kp_Waris = getParam("txtNoKPBaru1Waris")
				+ getParam("txtNoKPBaru2Waris") + getParam("txtNoKPBaru3Waris");

		// int pemohonid =
		// FrmPrmhnnSek8SecaraOnlineData.getPemohonId(getParam("idwarisup"),getParam("idPermohonan"));

		Hashtable h = new Hashtable();
		h.put("idwaris", getParam("idwarisup"));
		h.put("idSimati", getParam("txtIdSimatiWaris"));
		h.put("namaOB", getParam("txtNamaOBWaris"));

		h.put("nokpbaru", kp_Waris);
		h.put("nokpwaris", getParam("txtNoKPLamaWaris"));
		h.put("notel", getParam("txtNoTeleponWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		h.put("jenisKp", getParam("socJenisKPLainWaris"));
		h.put("nokplain", getParam("txtNoKPLainWaris"));
		h.put("jantina", getParam("socJantinaWaris"));
		h.put("agama", getParam("socAgamaWaris"));
		h.put("warga", getParam("socWarganegaraWaris"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		h.put("poskod", getParam("txtPoskodWaris"));
		h.put("bandar", getParam("txtBandarWaris"));
		h.put("idPermohonan", getParam("idPermohonan"));
		if (getParam("socNegeriTetap").equals("")) {
			h.put("negeri", "0");
		} else {
			h.put("negeri", getParam("socNegeriTetap"));
		}
		if (getParam("socSuratNegeri").equals("")) {
			h.put("negerisurat", 0);
		} else {
			h.put("negerisurat", getParam("socSuratNegeri"));
		}
		if (getParam("socDaerah").equals("")) {
			h.put("id_bandar", 0);
		} else {
			h.put("id_bandar", Integer.parseInt(getParam("socDaerah")));
		}
		if (getParam("socDaerahSurat").equals("")) {
			h.put("id_bandarsurat", 0);
		} else {
			h.put("id_bandarsurat", Integer
					.parseInt(getParam("socDaerahSurat")));
		}
		h.put("catatan", getParam("txtCatatanWaris"));

		if (getParam("socSaudaraWaris").equals("")) {
			h.put("saudara", 0);
		} else {
			h.put("saudara", Integer.parseInt(getParam("socSaudaraWaris")));
		}

		if (getParam("socStatusOBWaris").equals("")) {
			h.put("statusWaris", 0);
		} else {
			h
					.put("statusWaris", Integer
							.parseInt(getParam("socStatusOBWaris")));
		}

		if (getParam("checkHidupWaris").equals("")) {
			h.put("checkmati", "0");
		} else {
			h.put("checkmati", "1");
		}

		h.put("tarikhlahir", getParam("txdTarikhLahirWaris"));
		h.put("noberanak", getParam("txtNoSuratBeranakWaris"));

		if (getParam("txtUmurWaris").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));
		}
		h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
		h.put("waktumatiwaris", getParam("txtWaktuKematianWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		if (getParam("chkAddWaris").equals("")) {
			h.put("chcAlamat", "0");
		} else {
			h.put("chcAlamat", "1");
		}
		h.put("alamatSurat1Pemohon", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamatSurat2Pemohon", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamatSurat3Pemohon", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSuratPemohon", getParam("txtPoskodWarisSurat"));
		h.put("bandarSuratPemohon", getParam("txtBandarWarisSurat"));

		FrmPrmhnnSek8Data.updateWaris(h);

		Vector list = new Vector();
		list = FrmPrmhnnSek8DaftarSek8Data.getData();
		this.context.put("View", list);
	}

	private void addHa(HttpSession session) throws Exception {
		String id = getParam("id");
		String id1 = getParam("id1");
		String socJenisHartaAlih = getParam("socJenisHartaAlih");
		String txtNoRujukan = getParam("txtNoRujukan");
		String txtNilaiTarikhMati = getParam("txtNilaiTarikhMati");
		String txtNoSijil = getParam("txtNoSijil");
		String txtNilaiTarikhMohon = getParam("txtNilaiTarikhMohon");
		String txtBilUnit = getParam("txtBilUnit");
		String txtNilaiSeunit = getParam("txtNilaiSeunit");
		String txtAgensi = getParam("txtAgensi");
		String txtCatatan = getParam("txtCatatan");
		String basimati = getParam("txtBahagianSimatiHA1");
		String bbsimati = getParam("txtBahagianSimatiHA2");
		String bil = getParam("bil");

		Hashtable h = null;
		h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id", id);
		h.put("id1", id1);
		h.put("socJenisHartaAlih", socJenisHartaAlih);
		h.put("txtNoRujukan", txtNoRujukan);
		if (txtNilaiTarikhMati != "") {
			h.put("txtNilaiTarikhMati", Double
					.parseDouble(getParam("txtNilaiTarikhMati")));
		} else {
			h.put("txtNilaiTarikhMati", 0.0);
		}

		if (txtNilaiTarikhMohon != "") {
			h.put("txtNilaiTarikhMohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohon")));
		} else {
			h.put("txtNilaiTarikhMohon", 0.0);
		}

		h.put("txtNoSijil", txtNoSijil);
		h.put("txtBilUnit", txtBilUnit);
		h.put("txtNilaiSeunit", txtNilaiSeunit);
		h.put("txtAgensi", txtAgensi);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);
		h.put("alamat1", getParam("txtAlamatHartaAlih1"));
		h.put("alamat2", getParam("txtAlamatHartaAlih2"));
		h.put("alamat3", getParam("txtAlamatHartaAlih3"));
		h.put("poskod", getParam("txtPoskodPemohon"));
		h.put("idnegeri", getParam("socNegeri"));
		if (getParam("txtBahagianSimatiHA1") != "") {
			h.put("basimati", basimati);
		}
		if (getParam("txtBahagianSimatiHA2") != "") {
			h.put("bbsimati", bbsimati);
		}
		if (getParam("socDaerah") == "") {
			h.put("iddaerah", 0);
		} else {
			h.put("iddaerah", getParam("socDaerah"));
		}
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		FrmPrmhnnSek8DaftarSek8Data.addHa(h);
	}

	private void updateHa(HttpSession session) throws Exception {
		String id1 = getParam("id1");
		String id3 = getParam("idha");
		String socJenisHartaAlih = getParam("socJenisHartaAlih");
		String basimati = getParam("txtBahagianSimatiHA1");
		String bbsimati = getParam("txtBahagianSimatiHA2");
		String txtNoRujukan = getParam("txtNoRujukan");
		String txtNilaiTarikhMati = getParam("txtNilaiTarikhMati");
		String txtNoSijil = getParam("txtNoSijil");
		String txtNilaiTarikhMohon = getParam("txtNilaiTarikhMohon");
		String txtBilUnit = getParam("txtBilUnit");
		String txtNilaiSeunit = getParam("txtNilaiSeunit");
		String Agensi = getParam("txtAgensi");
		String txtCatatan = getParam("txtCatatan");
		String bil = getParam("bil");

		Hashtable h = null;
		h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id3", id3);
		h.put("id1", id1);
		h.put("socJenisHartaAlih", socJenisHartaAlih);
		if (getParam("txtBahagianSimatiHA1") != "") {
			h.put("basimati", basimati);
		}
		if (getParam("txtBahagianSimatiHA2") != "") {
			h.put("bbsimati", bbsimati);
		}

		if (txtNilaiTarikhMati != "") {
			h.put("txtNilaiTarikhMati", Double
					.parseDouble(getParam("txtNilaiTarikhMati")));
		} else {
			h.put("txtNilaiTarikhMati", 0.0);
		}

		if (txtNilaiTarikhMohon != "") {
			h.put("txtNilaiTarikhMohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohon")));
		} else {
			h.put("txtNilaiTarikhMohon", 0.0);
		}
		h.put("txtNoRujukan", txtNoRujukan);
		h.put("txtNoSijil", txtNoSijil);
		h.put("txtBilUnit", txtBilUnit);
		h.put("txtNilaiSeunit", txtNilaiSeunit);
		h.put("Agensi", Agensi);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);
		h.put("alamat1", getParam("txtAlamatHartaAlih1"));
		h.put("alamat2", getParam("txtAlamatHartaAlih2"));
		h.put("alamat3", getParam("txtAlamatHartaAlih3"));
		h.put("poskod", getParam("txtPoskodPemohon"));
		h.put("idnegeri", getParam("socNegeri"));
		h.put("iddaerah", getParam("socDaerah"));
		logiconline.kemaskiniHa(h);
	}

	public void addHtaam(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaam"));
		if (getParam("txtNilaiTarikhMohonHtaa") != "") {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHtaa")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}
		if (getParam("socJenisHakmilikHtaam") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaam")));
		} else {
			h.put("jenishakmilik", 0);
		}
		if (getParam("socNegeriHtaam") != "") {
			h.put("negeri", Integer.parseInt(getParam("socNegeriHtaam")));
		} else {
			h.put("negeri", 0);
		}

		if (getParam("socDaerahHtaam") != "") {
			h.put("daerah", Integer.parseInt(getParam("socDaerahHtaam")));
		} else {
			h.put("daerah", 0);
		}

		if (getParam("socMukimHtaam") != "") {
			h.put("mukim", Integer.parseInt(getParam("socMukimHtaam")));
		} else {
			h.put("mukim", 0);
		}
		if (getParam("txtBahagianSimati1") != "") {
			h.put("basimati", getParam("txtBahagianSimati1"));
		} else {
			h.put("basimati", "0");
		}

		if (getParam("txtBahagianSimati2") != "") {
			h.put("bbsimati", getParam("txtBahagianSimati2"));
		} else {
			h.put("bbsimati", "0");
		}
		h.put("catatan", getParam("txtCatatanHtaam"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));

		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", getParam("txtSekatan") == null ? ""
				: getParam("txtSekatan"));
		h.put("syaratNyata", getParam("txtSyaratNyata") == null ? ""
				: getParam("txtSyaratNyata"));

		logiconline.addHtaam(h);
	}

	private void updateHtaam(HttpSession session) throws Exception {
		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idhta", getParam("id_htaam"));
		h.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaamUp"));

		if (getParam("txtNilaiTarikhMohonHt") != "") {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHt")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}

		if (getParam("socJenisHakmilikHtaamUp") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaamUp")));
		} else {
			h.put("jenishakmilik", 0);
		}

		if (getParam("socNegeriHtaamUp") != "") {
			h.put("negeri", Integer.parseInt(getParam("socNegeriHtaamUp")));
		} else {
			h.put("negeri", 0);
		}

		if (getParam("socDaerahHtaamUp") != "") {
			h.put("daerah", Integer.parseInt(getParam("socDaerahHtaamUp")));
		} else {
			h.put("daerah", 0);
		}

		if (getParam("socMukimHtaamUp") != "") {
			h.put("mukim", Integer.parseInt(getParam("socMukimHtaamUp")));
		} else {
			h.put("mukim", 0);
		}
		if (getParam("txtBahagianSimati1Up") != "") {
			h.put("basimati", Integer
					.parseInt(getParam("txtBahagianSimati1Up")));
		} else {
			h.put("basimati", 0);
		}

		if (getParam("txtBahagianSimati2Up") != "") {
			h.put("bbsimati", Integer
					.parseInt(getParam("txtBahagianSimati2Up")));
		} else {
			h.put("bbsimati", 0);
		}
		h.put("catatan", getParam("txtCatatanHt"));
		
		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", getParam("txtSekatan") == null ? ""
				: getParam("txtSekatan"));
		h.put("syaratNyata", getParam("txtSyaratNyata") == null ? ""
				: getParam("txtSyaratNyata"));

		logiconline.updateHtaam(h);
	}

	public void addHtaamX(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaamX"));

		if (!getParam("txtNilaiTarikhMohonHtaaX").equals("")) {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHtaaX")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}

		if (!getParam("txtNilaiTarikhMatiHtaamX").equals("")) {
			h.put("nilai_Hta_mati", Double
					.parseDouble(getParam("txtNilaiTarikhMatiHtaamX")));
		} else {
			h.put("nilai_Hta_mati", 0.0);
		}

		if (!getParam("socKategoriTanahHtaamX").equals("")) {
			h.put("kategori", Integer
					.parseInt(getParam("socKategoriTanahHtaamX")));
		} else {
			h.put("kategori", 0);
		}

		if (!getParam("socJenisHakmilikHtaamX").equals("")) {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaamX")));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));

		if (!getParam("socNegeriHtaamX").equals("")) {
			h.put("negeri", Integer.parseInt(getParam("socNegeriHtaamX")));
		} else {
			h.put("negeri", 0);
		}

		if (!getParam("socDaerahHtaamX").equals("")) {
			h.put("daerah", Integer.parseInt(getParam("socDaerahHtaamX")));
		} else {
			h.put("daerah", 0);
		}

		if (!getParam("socMukimHtaamX").equals("")) {
			h.put("mukim", Integer.parseInt(getParam("socMukimHtaamX")));
		} else {
			h.put("mukim", 0);
		}

		h.put("luashmp", getParam("txtLuasHMpHtaamX"));
		h.put("luasasal", getParam("txtLuasAsalHtaamX"));
		h.put("nopajakan", getParam("txtNoPajakanX"));
		h.put("jenistanah", getParam("socJenisTanahHtaamX"));

		if (!getParam("txtBahagianSimati1X").equals("")) {
			h
					.put("basimati", Integer
							.parseInt(getParam("txtBahagianSimati1X")));
		} else {
			h.put("basimati", 0);
		}

		if (!getParam("txtBahagianSimati2X").equals("")) {
			h
					.put("bbsimati", Integer
							.parseInt(getParam("txtBahagianSimati2X")));
		} else {
			h.put("bbsimati", 0);
		}

		if (!getParam("socJenisLuasHtaamX").equals("")) {
			h
					.put("jenisluas", Integer
							.parseInt(getParam("socJenisLuasHtaamX")));
		} else {
			h.put("jenisluas", 0);
		}
		h.put("tanggungan", getParam("txtTanggunganHtaamX"));
		h.put("catatan", getParam("txtCatatanHtaamX"));
		h.put("noperserahan", getParam("txtNoPersHtaamX"));
		h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
		h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
		h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
		h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
		h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
		h.put("bandarpemaju", getParam("txtBandarPemaju1HtaamX"));
		h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
		h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		if (getParam("socNegeriTetap").equals("")) {
			h.put("id_negeripemaju", 0);
		} else {
			h.put("id_negeripemaju", Integer
					.parseInt(getParam("socNegeriTetap")));
		}
		if (getParam("socBandarTetap").equals("")) {
			h.put("id_bandarpemaju", 0);
		} else {
			h.put("id_bandarpemaju", Integer
					.parseInt(getParam("socBandarTetap")));
		}
		if (getParam("socDaerahx").equals("")) {
			h.put("id_bandarhta", 0);
		} else {
			h.put("id_bandarhta", Integer.parseInt(getParam("socDaerahx")));
		}
		h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
		h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
		h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));
		h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
		h.put("noroh", getParam("txtNoRohHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));
		h.put("nocagaran", getParam("txtNoCagaranX"));
		h.put("flag", getParam("radioHtaamViewX1"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		h.put("jeniskepentingan", getParam("txtKepentinganHtaamX"));
		FrmPrmhnnSek8Data.addHtaamX(h);
	}

	private void updateHtaamX(HttpSession session) throws Exception {
		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idhta", getParam("idhtaamXid"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaamX"));
		if (getParam("txtNilaiTarikhMohonHtaaX") != "") {
			h.put("nilai_Hta_memohon", Double
					.parseDouble(getParam("txtNilaiTarikhMohonHtaaX")));
		} else {
			h.put("nilai_Hta_memohon", 0.0);
		}

		if (getParam("txtNilaiTarikhMatiHtaamX") != "") {
			h.put("nilai_Hta_mati", Double
					.parseDouble(getParam("txtNilaiTarikhMatiHtaamX")));
		} else {
			h.put("nilai_Hta_mati", 0.0);
		}

		if (getParam("socKategoriTanahHtaamX") != "") {
			h.put("kategori", Integer
					.parseInt(getParam("socKategoriTanahHtaamX")));
		} else {
			h.put("kategori", 0);
		}

		if (getParam("socJenisHakmilikHtaamX") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaamX")));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));

		if (getParam("socNegeriHtaamX") != "") {
			h.put("negeri", Integer.parseInt(getParam("socNegeriHtaamX")));
		} else {
			h.put("negeri", 0);
		}

		if (getParam("socDaerahHtaamX") != "") {
			h.put("daerah", Integer.parseInt(getParam("socDaerahHtaamX")));
		} else {
			h.put("daerah", 0);
		}

		if (getParam("socMukimHtaamX") != "") {
			h.put("mukim", Integer.parseInt(getParam("socMukimHtaamX")));
		} else {
			h.put("mukim", 0);
		}

		h.put("luashmp", getParam("txtLuasHMpHtaamX"));
		h.put("luasasal", getParam("txtLuasAsalHtaamX"));
		h.put("nopajakan", getParam("txtNoPajakanX"));
		h.put("jenistanah", getParam("socJenisTanahHtaamX"));

		if (getParam("txtBahagianSimati1X") != "") {
			h
					.put("basimati", Integer
							.parseInt(getParam("txtBahagianSimati1X")));
		} else {
			h.put("basimati", 0);
		}

		if (getParam("txtBahagianSimati2X") != "") {
			h
					.put("bbsimati", Integer
							.parseInt(getParam("txtBahagianSimati2X")));
		} else {
			h.put("bbsimati", 0);
		}

		if (getParam("socJenisLuasHtaamX") != "") {
			h
					.put("jenisluas", Integer
							.parseInt(getParam("socJenisLuasHtaamX")));
		} else {
			h.put("jenisluas", 0);
		}

		h.put("tanggungan", getParam("txtTanggunganHtaamX"));
		h.put("catatan", getParam("txtCatatanHtaamX"));
		h.put("noperserahan", getParam("txtNoPersHtaamX"));
		h.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
		h.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
		h.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
		h.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
		h.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
		if (getParam("socNegeriTetap").equals("")) {
			h.put("id_negeripemaju", 0);
		} else {
			h.put("id_negeripemaju", Integer
					.parseInt(getParam("socNegeriTetap")));
		}
		if (getParam("socBandarTetap").equals("")) {
			h.put("id_bandarpemaju", 0);
		} else {
			h.put("id_bandarpemaju", Integer
					.parseInt(getParam("socBandarTetap")));
		}
		h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
		h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		if (getParam("socDaerahx").equals("")) {
			h.put("id_bandarhta", 0);
		} else {
			h.put("id_bandarhta", Integer.parseInt(getParam("socDaerahx")));
		}
		h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
		h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));
		h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
		h.put("noroh", getParam("txtNoRohHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));
		h.put("nocagaran", getParam("txtNoCagaranX"));
		h.put("flag", getParam("radioHtaamViewX1"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		h.put("jeniskepentingan", getParam("txtKepentinganHtaamX"));
		FrmPrmhnnSek8Data.updateHtaamX(h);
	}

	private void addPenghutang(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idSimati", getParam("idSimati"));
		String kp_penting = getParam("txtNoKPBaru1PentingU")
				+ getParam("txtNoKPBaru2PentingU")
				+ getParam("txtNoKPBaru3PentingU");
		h.put("nokpbaru", kp_penting);
		if (getParam("socJenisHutangPenting").equals("2")) {
			h.put("namaOB", getParam("txtNamaOBPenting"));
			h.put("nokppenting", getParam("txtNoKPLamaPenting"));
		} else if (getParam("socJenisHutangPenting").equals("1")) {
			h.put("nokppenting", getParam("txtSyarikatPentingU"));
			h.put("namaOB", getParam("txtNamaSyktPenting"));
		}
		h.put("jenishutang", getParam("socJenisHutangPenting"));
		h.put("jenisKp", getParam("socJenisKPLainPenting"));
		h.put("nokplain", getParam("txtNoKPLainPenting"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		h.put("bandar", getParam("txtBandarPenting"));
		if (getParam("socNegeriTetap").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriTetap")));
		}

		if (getParam("socDaerah").equals("")) {
			h.put("id_bandar", 0);
		} else {
			h.put("id_bandar", Integer.parseInt(getParam("socDaerah")));
		}

		if (getParam("txtNilaiHutangPenting").equals("")) {
			h.put("nilaihutang", 0.00);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPenting")));
		}
		h.put("noakaun", getParam("txtNoAkaunPenting"));
		h.put("butiranhutang", getParam("txtButiranHutangPenting"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		logiconline.addPenghutang(h);

	}

	private void updatepenghutang(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idSimati", getParam("idSimati"));
		h.put("idob", getParam("txtIdOb"));
		h.put("jenishutang", getParam("jenisHutangPentingU"));
		h.put("jenisKp", getParam("socJenisHutangPentingU"));
		h.put("nokplain", getParam("txtNoKPLainPentingU"));
		h.put("alamat1", getParam("txtAlamatTerakhir1PentingU"));
		h.put("alamat2", getParam("txtAlamatTerakhir2PentingU"));
		h.put("alamat3", getParam("txtAlamatTerakhir3PentingU"));
		h.put("poskod", getParam("txtPoskodPentingU"));
		h.put("bandar", getParam("txtBandarPentingU"));
		String kp_penting = getParam("txtNoKPBaru1PentingU")
				+ getParam("txtNoKPBaru2PentingU")
				+ getParam("txtNoKPBaru3PentingU");
		h.put("nokpbaru", kp_penting);
		if (getParam("jenisHutangPentingU").equals("2")) {
			h.put("namaOB", getParam("txtNamaOBPenting"));
			h.put("nokppenting", getParam("txtNoKPLamaPentingU"));
		} else if (getParam("jenisHutangPentingU").equals("1")) {
			h.put("nokppenting", getParam("txtSyarikatPentingU"));
			h.put("namaOB", getParam("txtNamaSyktPenting"));
		}
		if (getParam("socNegeriTetap").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriTetap")));
		}

		if (getParam("socDaerah").equals("")) {
			h.put("id_bandar", 0);
		} else {
			h.put("id_bandar", Integer.parseInt(getParam("socDaerah")));
		}
		h.put("catatan", getParam("txtCatatanPentingU"));

		if (getParam("txtNilaiHutangPentingU").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPentingU")));
		}
		h.put("noakaun", getParam("txtNoAkaunPentingU"));
		h.put("butiranhutang", getParam("txtButiranHutangPentingU"));
		FrmPrmhnnSek8Data.updatePenghutang(h);
	}

	private void addPenting(HttpSession session) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		Hashtable h = new Hashtable();

		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idpermohonansimati", (String) getParam("idpermohonansimati"));
		h.put("idPemohon", (String) getParam("idPemohon"));
		h.put("jenisPB", getParam("jenisPB"));
		h.put("idSimati", (String) getParam("idSimati"));
		h.put("namaOB", getParam("txtNamaOBPenting"));
		String kp_penting = getParam("txtNoKPBaru1Penting")
				+ getParam("txtNoKPBaru2Penting")
				+ getParam("txtNoKPBaru3Penting");
		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPenting"));
		h.put("jenisKp", getParam("socJenisKPLainPenting"));
		h.put("nokplain", getParam("txtNoKPLainPenting"));
		h.put("statusOB", getParam("socStatusOB"));
		if (getParam("socTarafKepentinganPenting").equals("")) {
			int a = 0;
			h.put("taraf", a);
		} else {
			h.put("taraf", Integer
					.parseInt(getParam("socTarafKepentinganPenting")));
		}
		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", getParam("socJantinaPenting"));
		h.put("agama", getParam("socAgamaPenting"));
		h.put("warga", getParam("socWarganegaraPenting"));
		if (getParam("txtUmurPenting").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurPenting")));
		}

		h.put("dob", getParam("txdTarikhLahirPenting"));
		h.put("noberanak", getParam("txtNoSuratBeranakPenting"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		if (getParam("socNegeriTetap").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriTetap")));
		}

		if (getParam("socBandarTetap").equals("")) {
			h.put("id_bandar", 0);
		} else {
			h.put("id_bandar", Integer.parseInt(getParam("socBandarTetap")));
		}
		h.put("catatan", getParam("txtCatatanPenting"));
		h.put("notepon", getParam("txtNoTelefon"));
		h.put("nofaks", getParam("txtFaks"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);
		FrmPrmhnnSek8Data.addPenting(h);
	}

	private void updatepenting(HttpSession session) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		Hashtable h = new Hashtable();

		h.put("idobx", getParam("txtIdOb"));
		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idSimati", getParam("idSimati"));

		if (!getParam("txtNamaOBPenting").equals("")) {
			h.put("namaOB", getParam("txtNamaOBPenting"));
		} else if (!getParam("txtNamaOBPenting1").equals("")) {
			h.put("namaOB", getParam("txtNamaOBPenting1"));
		}
		String kp_penting = getParam("txtNoKPBaru1Penting")
				+ getParam("txtNoKPBaru2Penting")
				+ getParam("txtNoKPBaru3Penting");
		h.put("nokpbaru", kp_penting);

		if (!getParam("txtNoKPLamaPenting").equals("")) {
			h.put("nokppenting", getParam("txtNoKPLamaPenting"));
		} else if (!getParam("txtNoKPLamaPenting1").equals("")) {
			h.put("nokppenting", getParam("txtNoKPLamaPenting1"));
		} else {
			h.put("nokppenting", getParam("txtNoKPLamaPenting"));
		}

		h.put("jenisKp", getParam("socJenisKPLainPenting"));
		h.put("nokplain", getParam("txtNoKPLainPenting"));
		h.put("statusOB", getParam("socStatusOB"));

		if (getParam("socTarafKepentinganPenting").equals("")) {
			int a = 0;
			h.put("taraf", a);
		} else {
			h.put("taraf", Integer
					.parseInt(getParam("socTarafKepentinganPenting")));
		}

		h.put("jantina", getParam("socJantinaPenting"));
		h.put("agama", getParam("socAgamaPenting"));
		h.put("warga", getParam("socWarganegaraPenting"));
		if (getParam("txtUmurPenting").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurPenting")));
		}
		h.put("dob", getParam("txdTarikhLahirPenting"));
		h.put("noberanak", getParam("txtNoSuratBeranakPenting"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		h.put("bandar", getParam("txtBandarPenting"));
		h.put("notel", getParam("txtNoTelefon"));
		if (getParam("socNegeriTetap").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriTetap")));
		}

		h.put("catatan", getParam("txtCatatanPentingU"));

		if (getParam("txtNilaiHutangPenting").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPenting")));
		}

		// h.put("nilaihutang", getParam("txtNilaiHutangPentingU"));
		h.put("noakaun", getParam("txtNoAkaunPenting"));
		h.put("butiranhutang", getParam("txtButiranHutangPenting"));
		h.put("nofaks", getParam("txtFaks"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);
		if (getParam("socBandarTetap").equals("")) {
			h.put("id_bandar", 0);
		} else {
			h.put("id_bandar", Integer.parseInt(getParam("socBandarTetap")));
		}
		if (getParam("socDaerah").equals("")) {
			h.put("idbandar", 0);
		} else {
			h.put("idbandar", Integer.parseInt(getParam("socDaerah")));
		}
		FrmPrmhnnSek8Data.updatePenting(h);
	}

	private void addPemiutang(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();

		h.put("iduser", (String) session.getAttribute("_ekptg_user_id"));
		h.put("idSimati", getParam("idSimati"));

		if (!getParam("txtNamaOBPenting").equals("")) {
			h.put("namaOB", getParam("txtNamaOBPenting"));
		} else {
			h.put("namaOB", getParam("txtNamaOBPenting1"));
		}
		h.put("jenispemiutang", getParam("socJenisHutangPentingU"));
		String kp_penting = getParam("txtNoKPBaru1Penting")
				+ getParam("txtNoKPBaru2Penting")
				+ getParam("txtNoKPBaru3Penting");
		h.put("nokpbaru", kp_penting);

		if (!getParam("txtNoKPLamaPenting").equals("")) {
			h.put("nokppenting", getParam("txtNoKPLamaPenting"));
		} else {
			h.put("nokppenting", getParam("txtNoKPLamaPenting1"));
		}
		h.put("jenisKp", getParam("socJenisKPLainPenting"));
		h.put("nokplain", getParam("txtNoKPLainPenting"));
		h.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		h.put("poskod", getParam("txtPoskodPenting"));
		if (getParam("socNegeriPenting").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		}

		if (getParam("txtNilaiHutangPenting").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPenting")));
		}

		// h.put("nilaihutang", getParam("txtNilaiHutangPentingU"));
		h.put("noakaun", getParam("txtNoAkaunPenting"));
		h.put("idpermohonansimati", getParam("idpermohonansimati"));
		h.put("butiranhutang", getParam("txtButiranHutangPenting"));
		if (getParam("socDaerah").equals("")) {
			h.put("id_bandar", 0);
		} else {
			h.put("id_bandar", Integer.parseInt(getParam("socDaerah")));
		}
		FrmPrmhnnSek8Data.addPemiutang(h);

	}

	public void initTabData() {
		System.out.println("tab tengah " + getParam("tabIdtengah"));
		this.context.put("selectedTabatas", getParam("tabIdatas"));
		this.context.put("selectedTabtengah", getParam("tabIdtengah"));
		this.context.put("selectedTabbawah", getParam("tabIdbawah"));
		this.context.put("selectedTabtepi", getParam("tabIdtepi"));
	}

	public void initInputPpkPengesahan() {
		this.context.put("namapejabat", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("poskod", "");
		this.context.put("no_tel", "");
		this.context.put("no_tel_samb", "");
		context.put("disabledDropdown", "");

	}

	public void initInputSimati() {
		this.context.put("nokpbaru1", "");
		this.context.put("nokpbaru2", "");
		this.context.put("nokpbaru3", "");
		this.context.put("nokplama", "");
		this.context.put("nojeniskp", "");
		this.context.put("nokplain", "");
		this.context.put("simatinama", "");
		this.context.put("simatinamalain", "");
		this.context.put("waktumati", "");
		this.context.put("tmptmati", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("sbbmati", "");
		this.context.put("bandar", "");
		this.context.put("poskod", "");
		this.context.put("catatan", "");
		this.context.put("agama", "");
		this.context.put("jantina", "0");
		this.context.put("umur", "");
		this.context.put("warga", "");
		this.context.put("tarikhmati", "");
		this.context.put("buktimati", "");
		this.context.put("negeriId", "");
	}

	public void initInputPemiutang() {
		if (getParam("txtNilaiHutangPenting") == "") {
			this.context.put("hutangrm", "");
		} else {
			this.context.put("hutangrm", getParam("txtNilaiHutangPenting"));
		}
		this.context.put("akaun", getParam("txtNoAkaunPenting"));
		this.context.put("alamatakhir1", getParam("txtAlamatTerakhir1Penting"));
		this.context.put("alamatakhir2", getParam("txtAlamatTerakhir2Penting"));
		this.context.put("alamatakhir3", getParam("txtAlamatTerakhir3Penting"));
		this.context.put("poskodakhir", getParam("txtPoskodPenting"));
		this.context.put("bandarakhir", getParam("txtBandarPenting"));
		this.context.put("negeri", getParam("socNegeriPenting"));
		this.context.put("butiran", getParam("txtButiranHutangPenting"));
	}

	public void initInputPemiutangNew() {
		if (getParam("socJenisHutangPentingU") == "") {
			this.context.put("socJenisHutang", "0");
		} else {
			this.context.put("socJenisHutang",
					getParam("socJenisHutangPentingU"));
		}
		this.context.put("nokp1", "");
		this.context.put("nokp2", "");
		this.context.put("nokp3", "");
		this.context.put("nolama1", "");
		this.context.put("nolama2", "");
		this.context.put("jenisKp", "");
		this.context.put("nolain", "");
		this.context.put("ob1", "");
		this.context.put("ob2", "");
		this.context.put("hutangrm", "");
		this.context.put("akaun", "");
		this.context.put("alamatakhir1", "");
		this.context.put("alamatakhir2", "");
		this.context.put("alamatakhir3", "");
		this.context.put("poskodakhir", "");
		this.context.put("bandarakhir", "");
		this.context.put("negeri", "");
		this.context.put("butiran", "");
	}

	public void initNewTab() {
		String selectedTabatas = "";
		String selectedTabtengah = "";
		String selectedTabbawah = "";
		String selectedTabtepi = "";

		selectedTabatas = "0";
		selectedTabtengah = "0";
		selectedTabbawah = "0";
		selectedTabtepi = "0";

		this.context.put("selectedTabatas", selectedTabatas);
		this.context.put("selectedTabtengah", selectedTabtengah);
		this.context.put("selectedTabbawah", selectedTabbawah);
		this.context.put("selectedTabtepi", selectedTabtepi);
	}

	public void initKembalisimati() {
		String selectedTabatas = getParam("tabIdatas").toString();
		String selectedTabtengah = getParam("tabIdtengah").toString();
		String selectedTabbawah = getParam("tabIdbawah").toString();
		String selectedTabtepi = getParam("tabIdtepi").toString();
		this.context.put("SimpanStatus", getParam("simpanStatus"));

		this.context.put("selectedTabatas", selectedTabatas);
		this.context.put("selectedTabtengah", selectedTabtengah);
		this.context.put("selectedTabbawah", selectedTabbawah);
		this.context.put("selectedTabtepi", selectedTabtepi);
	}

	public void initTabLapisan() {
		this.context.put("show_lapisan_berikut_update", "");
		this.context.put("show_button_lapisan", "yes");
		this.context.put("show_lapisan_bawah", "");
		this.context.put("show_tarikh", "");
		this.context.put("listpenting", "");
		this.context.put("readmode", "");
		this.context.put("namaOB", "");
		this.context.put("nokpbaru1", "");
		this.context.put("nokpbaru2", "");
		this.context.put("nokpbaru3", "");
		this.context.put("nokpsaksi", "");
		this.context.put("jenisKp", "");
		this.context.put("nokplain", "");
		this.context.put("statusOB", "");
		this.context.put("notel", "");
		this.context.put("taraf", "");
		this.context.put("bandar", "");
		this.context.put("statusWaris", "");
		this.context.put("jantina", "");
		this.context.put("agama", "");
		this.context.put("warga", "");
		this.context.put("umur", "");
		this.context.put("dob", "");
		this.context.put("noberanak", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("poskod", "");
		this.context.put("negeri", "");
		this.context.put("catatan", "");
		this.context.put("hp", "");
		this.context.put("tarikhmati", "");
		this.context.put("nokpwaris", "");
		this.context.put("checkmati", "0");
		this.context.put("waktumatiwaris", "");
		this.context.put("saudara", "");
	}

	public void initSimatiNewData() {
		this.context.put("SimpanStatus", 0);
		this.context.put("new_data", "yes");
		this.context.put("setmode", "");
		this.context.put("setmode2", "");
		this.context.put("setmode3", "");
		this.context.put("readonly", "");
		this.context.put("sijilmode", "readonly class=\"disabled\"");
		this.context.put("nokpbaru1", "");
		this.context.put("nokpbaru2", "");
		this.context.put("nokpbaru3", "");
		this.context.put("nokplama", "");
		this.context.put("nojeniskp", "");
		this.context.put("nokplain", "");
		this.context.put("simatinama", "");
		this.context.put("simatinamalain", "");
		this.context.put("waktumati", "");
		this.context.put("tmptmati", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("sbbmati", "");
		this.context.put("bandar", "");
		this.context.put("poskod", "");
		this.context.put("catatan", "");
		this.context.put("agama", "0");
		this.context.put("jantina", "0");
		this.context.put("umur", "");
		this.context.put("warga", "0");
		this.context.put("tarikhmati", "");
		this.context.put("buktimati", "");
		this.context.put("negeriId", "");
		this.context.put("negeriIdx", "0");
	}

	public void initSimatiKemaskini() {
		this.context.put("SimpanStatus", 0);
		this.context.put("new_data", "no");
		this.context.put("setmode", "");
		this.context.put("setmode2", "");
		this.context.put("setmode3", "");
		this.context.put("readonly", "");
		this.context.put("simatinamalain", getParam("txtNamaLainSimati"));
		this.context.put("buktimati", getParam("socBuktiMati"));
		this.context.put("waktumati", "");
		this.context.put("tmptmati", "");
		this.context.put("alamat1", getParam("txtAlamatTerakhir1Simati"));
		this.context.put("alamat2", getParam("txtAlamatTerakhir2Simati"));
		this.context.put("alamat3", getParam("txtAlamatTerakhir3Simati"));
		this.context.put("sbbmati", "");
		this.context.put("bandar", "");
		this.context.put("poskod", getParam("txtPoskodSimati"));
		this.context.put("catatan", getParam("txtCatatanSimati"));
		if (!getParam("socAgama").equals("0")) {
			this.context.put("agama", getParam("socAgama"));
		} else {
			this.context.put("agama", 0);
		}
		this.context.put("umur", "");
		this.context.put("warga", "0");
		this.context.put("tarikhmati", "");
		this.context.put("buktimati", "");
		this.context.put("negeriId", "");
		this.context.put("negeriIdx", "0");

		if (!getParam("txtNoKPBaru3Simati").equals("")) {
			String nokppemohon = getParam("txtNoKPBaru3Simati").toString();
			String lastDigit = nokppemohon.substring(3, 4);
			int digitValue = Integer.parseInt(lastDigit);
			String gender = "";
			if (digitValue == 0 || digitValue == 2 || digitValue == 4
					|| digitValue == 6 || digitValue == 8) {
				gender = "2"; // female
			} else {
				gender = "1"; // male
			}
			this.context.put("jantina", gender);
		} else {
			this.context.put("jantina", 0);
		}

	}

	public void initSimatiGetBandar() {
		this.context.put("new_data", "yes");
		this.context.put("negeriIdx", getParam("socNegeri"));
		this.context.put("setmode", "");
		this.context.put("setmode2", "");
		this.context.put("setmode3", "");
		this.context.put("nokpbaru1", getParam("txtNoKPBaru1Simati"));
		this.context.put("nokpbaru2", getParam("txtNoKPBaru2Simati"));
		this.context.put("nokpbaru3", getParam("txtNoKPBaru3Simati"));
		this.context.put("nokplama", getParam("txtNoKPLamaSimati"));
		this.context.put("nojeniskp", getParam("socJenisKPLainSimati"));
		this.context.put("nokplain", getParam("txtNoKPLainSimati"));
		this.context.put("simatinama", getParam("txtNamaSimati"));
		this.context.put("simatinamalain", getParam("txtNamaLainSimati"));
		this.context.put("jantina", getParam("socJantina"));
		this.context.put("agama", getParam("socAgama"));
		this.context.put("warga", getParam("socWarganegara"));
		this.context.put("buktimati", getParam("socBuktiMati"));
		this.context.put("nosijil", getParam("txtNoSijilMatiSimati"));
		this.context.put("tarikhmati", getParam("txdTarikhMati"));
		this.context.put("umur", getParam("txtUmurSimati"));
		this.context.put("waktumati", getParam("socWaktuKematianSimati"));
		this.context.put("tmptmati", getParam("txtTempatMati"));
		this.context
				.put("txtSebabKematian", getParam("txtSebabKematianSimati"));
		this.context.put("alamat1", getParam("txtAlamatTerakhir1Simati"));
		this.context.put("alamat2", getParam("txtAlamatTerakhir2Simati"));
		this.context.put("alamat3", getParam("txtAlamatTerakhir3Simati"));
		this.context.put("poskod", getParam("txtPoskodSimati"));
		this.context.put("catatan", getParam("txtCatatanSimati"));
		this.context.put("sbbmati", getParam("txtSebabKematianSimati"));
	}

	public void initPemiutangGetBandarTetap() {
		this.context.put("nokpbaru1", getParam("txtNoKPBaru1Penting"));
		this.context.put("nokpbaru2", getParam("txtNoKPBaru2Penting"));
		this.context.put("nokpbaru3", getParam("txtNoKPBaru3Penting"));
		this.context.put("nolama1", getParam("txtNoKPLamaPenting"));
		this.context.put("nolama2", getParam("txtNoKPLamaPenting1"));
		this.context.put("jenisKp", getParam("socJenisKPLainPenting"));
		this.context.put("nolain", getParam("txtNoKPLainPenting"));
		this.context.put("ob1", getParam("txtNamaOBPenting"));
		this.context.put("ob2", getParam("txtNamaOBPenting1"));
		this.context.put("hutangrm", getParam("txtNilaiHutangPenting"));
		this.context.put("akaun", getParam("txtNoAkaunPenting"));
		this.context.put("alamatakhir1", getParam("txtAlamatTerakhir1Penting"));
		this.context.put("alamatakhir2", getParam("txtAlamatTerakhir2Penting"));
		this.context.put("alamatakhir3", getParam("txtAlamatTerakhir3Penting"));
		this.context.put("poskodakhir", getParam("txtPoskodPenting"));
		this.context.put("butiran", getParam("txtButiranHutangPenting"));
		this.context.put("negeri", getParam("socNegeriPenting"));
	}

	public void initPemiutangView() {
		this.context.put("nokpbaru1", "");
		this.context.put("nokpbaru2", "");
		this.context.put("nokpbaru3", "");
		this.context.put("nolama1", "");
		this.context.put("nolama2", "");
		this.context.put("jenisKp", "0");
		this.context.put("nolain", "");
		this.context.put("ob1", "");
		this.context.put("ob2", "");
		this.context.put("hutangrm", "");
		this.context.put("akaun", "");
		this.context.put("alamatakhir1", "");
		this.context.put("alamatakhir2", "");
		this.context.put("alamatakhir3", "");
		this.context.put("poskodakhir", "");
		this.context.put("butiran", "");
		this.context.put("negeri", "0");
	}

	public void initDataWarisView() {
		this.context.put("show_tambah_waris", "");
		this.context.put("listpenting", "");
		this.context.put("namaOB", "");
		this.context.put("nokpbaru1", "");
		this.context.put("nokpbaru2", "");
		this.context.put("nokpbaru3", "");
		this.context.put("nokpsaksi", "");
		this.context.put("jenisKp", "");
		this.context.put("nokplain", "");
		this.context.put("statusOB", "");
		this.context.put("notel", "");
		this.context.put("taraf", "");
		this.context.put("bandar", "");
		this.context.put("statusWaris", "");
		this.context.put("jantina", "");
		this.context.put("agama", "");
		this.context.put("warga", "");
		this.context.put("umur", "");
		this.context.put("dob", "");
		this.context.put("noberanak", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("poskod", "");
		this.context.put("negeri", "");
		this.context.put("catatan", "");
		this.context.put("hp", "");
		this.context.put("tarikhmati", "");
		this.context.put("nokpwaris", "");
		this.context.put("checkmati", "0");
		this.context.put("waktumatiwaris", "");
		this.context.put("saudara", "");
	}

	public void initValueBandarNegeri(String socNegeriTetap,
			String socBandarTetap, String socNegeriSurat, String socBandarSurat)
			throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}
		String idNegeriSurat = socNegeriSurat;
		if (idNegeriSurat == null || idNegeriSurat.trim().length() == 0) {
			idNegeriSurat = "0";
		}
		String idBandarSurat = socBandarSurat;
		if (idBandarSurat == null || idBandarSurat.trim().length() == 0) {
			idBandarSurat = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriSurat),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarSurat", HTML.SelectBandarByNegeri(
				idNegeriSurat, "socDaerahSurat", Long.parseLong(idBandarSurat),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initValueBandarNegeriWaris(String socNegeriTetap,
			String socBandarTetap, String socNegeriSurat, String socBandarSurat)
			throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}
		String idNegeriSurat = socNegeriSurat;
		if (idNegeriSurat == null || idNegeriSurat.trim().length() == 0) {
			idNegeriSurat = "0";
		}
		String idBandarSurat = socBandarSurat;
		if (idBandarSurat == null || idBandarSurat.trim().length() == 0) {
			idBandarSurat = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetapWaris()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriSurat),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetapWaris()\""));
		this.context.put("selectBandarSurat", HTML.SelectBandarByNegeri(
				idNegeriSurat, "socDaerahSurat", Long.parseLong(idBandarSurat),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initValueBandarNegeriDuplicate(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarSurat", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerahSurat", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));

	}

	public void initValueBandarNegeriDuplicateWaris(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetapWaris()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetapWaris()\""));
		this.context.put("selectBandarSurat", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerahSurat", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));

	}

	public void initValueBandarNegeriNotDuplicate(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}
		String idNegeriSurat = "";
		if (idNegeriSurat == null || idNegeriSurat.trim().length() == 0) {
			idNegeriSurat = "18";
		}
		String idBandarSurat = "";
		if (idBandarSurat == null || idBandarSurat.trim().length() == 0) {
			idBandarSurat = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriSurat),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarSurat", HTML.SelectBandarByNegeri(
				idNegeriSurat, "socDaerahSurat", Long.parseLong(idBandarSurat),
				"style=\"width:225px;text-transform:uppercase;\""));

	}

	public void initTextfieldPemohonDuplicate() throws Exception {
		this.context.put("add1", getParam("txtAlamatTerakhir1Pemohon"));
		this.context.put("add2", getParam("txtAlamatTerakhir2Pemohon"));
		this.context.put("add3", getParam("txtAlamatTerakhir3Pemohon"));
		this.context.put("poskod", getParam("txtPoskodPemohon"));
		this.context.put("add1surat", getParam("txtAlamatTerakhir1Pemohon"));
		this.context.put("add2surat", getParam("txtAlamatTerakhir2Pemohon"));
		this.context.put("add3surat", getParam("txtAlamatTerakhir3Pemohon"));
		this.context.put("poskodsurat", getParam("txtPoskodPemohon"));
		this.context.put("check3", "checked");
		this.context.put("new_data", "yes");
	}

	public void initBandarNegeriPemohonBlank() throws Exception {
		this.context.put("add1surat", "");
		this.context.put("add2surat", "");
		this.context.put("add3surat", "");
		this.context.put("poskodsurat", "");
		this.context.put("check3", "");
		this.context.put("new_data", "yes");
	}

	public void initListTextfieldPemohon() {
		this.context.put("new_data", "yes");
		this.context.put("noKpBaru1", getParam("txtnoKpBaru1Pemohon"));
		this.context.put("flaghub", getParam("flaghub"));
		this.context.put("noKpBaru2", getParam("txtnoKpBaru2Pemohon"));
		this.context.put("noKpBaru3", getParam("txtnoKpBaru3Pemohon"));
		this.context.put("nokplama", getParam("txtNoKPLamaPemohon"));
		this.context.put("jenisKpPemohon", getParam("socJenisKP"));
		this.context.put("nokplain", getParam("txtNoKPLainPemohon"));
		this.context.put("name", getParam("txtNamaPemohonPemohon"));
		this.context.put("jantina", getParam("socJantina"));
		this.context.put("taraf", getParam("socTaraf"));
		this.context.put("saudara", getParam("socTalianSaudara"));
		this.context.put("agama", getParam("socAgama"));
		this.context.put("warga", getParam("socWarga"));
		System.out.println("idhub : " + getParam("socTalianSaudara"));

		if (getParam("checkPeguam") == "Y") {
			context.put("checkPeguam", "checked");
		} else {
			context.put("checkPeguam", "");
		}
		this.context.put("peguam", getParam("checkPeguam"));
		this.context.put("add1", getParam("txtAlamatTerakhir1Pemohon"));
		this.context.put("add2", getParam("txtAlamatTerakhir2Pemohon"));
		this.context.put("add3", getParam("txtAlamatTerakhir3Pemohon"));
		this.context.put("poskod", getParam("txtPoskodPemohon"));
		this.context.put("add1surat", getParam("txtAlamatSurat1Pemohon"));
		this.context.put("add2surat", getParam("txtAlamatSurat2Pemohon"));
		this.context.put("add3surat", getParam("txtAlamatSurat3Pemohon"));
		this.context.put("poskodsurat", getParam("txtPoskodSuratPemohon"));
		this.context.put("notel", getParam("txtNoTelefonPemohon"));
		this.context.put("nohp", getParam("txtNoTelefonBimbitPemohon"));
		this.context.put("nofax", getParam("txtNoFaksPemohon"));
		this.context.put("email", getParam("txtEmelPemohon"));
		this.context.put("catatan", getParam("txtCatatanPemohon"));
		System.out.println("peguam---" + getParam("sorPeguam"));

	}

	public void initListTextfieldPemohonBlank() {
		this.context.put("new_data", "yes");
		this.context.put("noKpBaru1", "");
		this.context.put("noKpBaru2", "");
		this.context.put("noKpBaru3", "");
		this.context.put("nokplama", "");
		this.context.put("jenisKpPemohon", "0");
		this.context.put("nokplain", "");
		this.context.put("name", "");
		this.context.put("taraf", "0");
		this.context.put("peguam", "");
		this.context.put("add1", "");
		this.context.put("add2", "");
		this.context.put("add3", "");
		this.context.put("poskod", "");
		this.context.put("add1surat", "");
		this.context.put("add2surat", "");
		this.context.put("add3surat", "");
		this.context.put("poskodsurat", "");
		this.context.put("notel", "");
		this.context.put("nohp", "");
		this.context.put("nofax", "");
		this.context.put("email", "");
		this.context.put("catatan", "");
	}

	public void initDeclareDataBandarNegeri(String negeriTetap,
			String daerahTetap, String negeriSurat, String daerahSurat)
			throws Exception {
		String idNegeriTetap = negeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = daerahTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}
		String idNegeriSurat = negeriSurat;
		if (idNegeriSurat == null || idNegeriSurat.trim().length() == 0) {
			idNegeriSurat = "0";
		}
		String idBandarSurat = daerahSurat;
		if (idBandarSurat == null || idBandarSurat.trim().length() == 0) {
			idBandarSurat = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context
				.put(
						"selectBandarTetap",
						HTML
								.SelectBandarByNegeri(idNegeriTetap,
										"socDaerah", Long
												.parseLong(idBandarTetap),
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriSurat),
										null,
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context
				.put(
						"selectBandarSurat",
						HTML
								.SelectBandarByNegeri(idNegeriSurat,
										"socDaerahSurat", Long
												.parseLong(idBandarSurat),
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initDeclareDataBandarNegeriKemaskini(String negeriTetap,
			String daerahTetap, String negeriSurat, String daerahSurat)
			throws Exception {
		String idNegeriTetap = negeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "18";
		}
		String idBandarTetap = daerahTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}
		String idNegeriSurat = negeriSurat;
		if (idNegeriSurat == null || idNegeriSurat.trim().length() == 0) {
			idNegeriSurat = "18";
		}
		String idBandarSurat = daerahSurat;
		if (idBandarSurat == null || idBandarSurat.trim().length() == 0) {
			idBandarSurat = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriSurat),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarSurat", HTML.SelectBandarByNegeri(
				idNegeriSurat, "socDaerahSurat", Long.parseLong(idBandarSurat),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initDeclareDataBandarNegeriKemaskiniWaris(String negeriTetap,
			String daerahTetap, String negeriSurat, String daerahSurat)
			throws Exception {
		String idNegeriTetap = negeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = daerahTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}
		String idNegeriSurat = negeriSurat;
		if (idNegeriSurat == null || idNegeriSurat.trim().length() == 0) {
			idNegeriSurat = "0";
		}
		String idBandarSurat = daerahSurat;
		if (idBandarSurat == null || idBandarSurat.trim().length() == 0) {
			idBandarSurat = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetapWaris()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriSurat),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetapWaris()\""));
		this.context.put("selectBandarSurat", HTML.SelectBandarByNegeri(
				idNegeriSurat, "socDaerahSurat", Long.parseLong(idBandarSurat),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initBandarNegeriBaru() throws Exception {
		String idNegeriTetap = "";
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = "";
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}
		String idNegeriSurat = "";
		if (idNegeriSurat == null || idNegeriSurat.trim().length() == 0) {
			idNegeriSurat = "0";
		}
		String idBandarSurat = "";
		if (idBandarSurat == null || idBandarSurat.trim().length() == 0) {
			idBandarSurat = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"javascript:onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriSurat),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"javascript:onChangeBandarTetap()\""));
		this.context.put("selectBandarSurat", HTML.SelectBandarByNegeri(
				idNegeriSurat, "socDaerahSurat", Long.parseLong(idBandarSurat),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initBandarNegeriBaruWaris() throws Exception {
		String idNegeriTetap = "";
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = "";
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}
		String idNegeriSurat = "";
		if (idNegeriSurat == null || idNegeriSurat.trim().length() == 0) {
			idNegeriSurat = "0";
		}
		String idBandarSurat = "";
		if (idBandarSurat == null || idBandarSurat.trim().length() == 0) {
			idBandarSurat = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"javascript:onChangeBandarTetapWaris()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
		this.context
				.put(
						"selectNegeriSurat",
						HTML
								.SelectNegeri(
										"socSuratNegeri",
										Long.parseLong(idNegeriSurat),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"javascript:onChangeBandarTetapWaris()\""));
		this.context.put("selectBandarSurat", HTML.SelectBandarByNegeri(
				idNegeriSurat, "socDaerahSurat", Long.parseLong(idBandarSurat),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	// Textfield Waris
	public void initTextfieldWarisDuplicate() throws Exception {
		this.context.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		this.context.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		this.context.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		this.context.put("poskod", getParam("txtPoskodWaris"));
		this.context.put("alamat1surat", getParam("txtAlamatTerakhir1Waris"));
		this.context.put("alamat2surat", getParam("txtAlamatTerakhir2Waris"));
		this.context.put("alamat3surat", getParam("txtAlamatTerakhir3Waris"));
		this.context.put("poskodsurat", getParam("txtPoskodWaris"));
		this.context.put("check3", "checked");
		// this.context.put("new_data","yes");
	}

	public void initTextfieldWarisNotDuplicate() throws Exception {
		this.context.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		this.context.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		this.context.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		this.context.put("poskod", getParam("txtPoskodWaris"));
		this.context.put("alamat1surat", "");
		this.context.put("alamat2surat", "");
		this.context.put("alamat3surat", "");
		this.context.put("poskodsurat", "");
		this.context.put("check3", "");
		// this.context.put("new_data","yes");
	}

	public void initListTextfieldWarisBlank() {
		this.context.put("namaOB", "");
		this.context.put("nokpbaru1", "");
		this.context.put("nokpbaru2", "");
		this.context.put("nokpbaru3", "");
		this.context.put("nokpsaksi", "");
		this.context.put("jenisKp", "");
		this.context.put("nokplain", "");
		this.context.put("statusOB", "");
		this.context.put("notel", "");
		this.context.put("taraf", "");
		this.context.put("statusWaris", "");
		this.context.put("jantina", "");
		this.context.put("agama", "");
		this.context.put("warga", "");
		this.context.put("umur", "");
		this.context.put("dob", "");
		this.context.put("noberanak", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("poskod", "");
		this.context.put("alamat1surat", "");
		this.context.put("alamat2surat", "");
		this.context.put("alamat3surat", "");
		this.context.put("poskodsurat", "");
		this.context.put("catatan", "");
		this.context.put("hp", "");
		this.context.put("tarikhmati", "");
		this.context.put("nokpwaris", "");
		this.context.put("checkmati", "0");
		this.context.put("waktumatiwaris", "");
		this.context.put("saudara", "");
	}

	public void initListTextfieldWaris() {
		this.context.put("namaOB", getParam("txtNamaOBWaris"));
		this.context.put("nokpbaru1", getParam("txtNoKPBaru1Waris"));
		this.context.put("nokpbaru2", getParam("txtNoKPBaru2Waris"));
		this.context.put("nokpbaru3", getParam("txtNoKPBaru3Waris"));
		this.context.put("nokpwaris", getParam("txtNoKPLamaWaris"));
		this.context.put("jenisKp", getParam("socJenisKPLainWaris"));
		this.context.put("nokplain", getParam("txtNoKPLainWaris"));
		this.context.put("statusOB", "");
		this.context.put("taraf", "");
		this.context.put("statusWaris", "");
		this.context.put("jantina", "");
		this.context.put("agama", getParam("socAgamaWaris"));
		this.context.put("warga", getParam("txtUmurWaris"));
		this.context.put("umur", getParam("txtUmurWaris"));
		this.context.put("dob", "");
		this.context.put("noberanak", "");
		this.context.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		this.context.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		this.context.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		this.context.put("poskod", getParam("txtPoskodWaris"));
		this.context.put("alamat1surat",
				getParam("txtAlamatTerakhir1WarisSurat"));
		this.context.put("alamat2surat",
				getParam("txtAlamatTerakhir2WarisSurat"));
		this.context.put("alamat3surat",
				getParam("txtAlamatTerakhir3WarisSurat"));
		this.context.put("poskodsurat", getParam("txtPoskodWarisSurat"));
		this.context.put("catatan", "");
		this.context.put("notel", getParam("txtNoTeleponWaris"));
		this.context.put("hp", getParam("txtNoTeleponBimbitWaris"));
		if (getParam("checkHidupWaris").equals("")) {
			this.context.put("checkmati", "0");
			this.context.put("show_tarikh", "");
			this.context.put("waktumatiwaris", "");
			this.context.put("tarikhmati", "");
		} else {
			this.context.put("checkmati", "1");
			this.context.put("show_tarikh", "yes");
			this.context.put("waktumatiwaris",
					getParam("txtWaktuKematianWaris"));
			this.context.put("tarikhmati", getParam("txdTarikhMatiWaris"));
		}
		this.context.put("saudara", getParam("socSaudaraWaris"));
		if (!getParam("chkAddWaris").equals("")) {
			this.context.put("check3", "checked");
		} else {
			this.context.put("check3", "");
		}
		if (!getParam("chkAddWarisWaris").equals("")) {
			this.context.put("check3", "checked");
		} else {
			this.context.put("check3", "");
		}
		this.context.put("new_data", "no");

	}

	public void initPageWarisLapisan() {
		this.context.put("show_lapisan_berikut", "yes");
		this.context.put("show_lapisan_berikut_tambah", "yes");
		this.context.put("buttonwarislapisanSimpan", "Simpan");
		this.context.put("buttonwarislapisan", "Simpan");

		// this.context.put("show_lapisan_berikut","yes");
		// this.context.put("show_button_lapisan","yes");
		// this.context.put("show_lapisan_berikut_tambah","");
		// this.context.put("show_lapisan_berikut_update","yes");
		// this.context.put("buttonwarislapisan","Simpan");
		// this.context.put("buttonwarislapisanSimpan","");
		// this.context.put("show_batal_waris_lapisan","");
		// this.context.put("show_lapisan_bawah","");
	}

	public void initPageWarisLapisanUpdate() {
		// this.context.put("show_lapisan_berikut","yes");
		// this.context.put("show_lapisan_berikut_tambah","yes");
		// this.context.put("buttonwarislapisanSimpan","Simpan");
		// this.context.put("buttonwarislapisan","Simpan");

		this.context.put("show_lapisan_berikut", "yes");
		this.context.put("show_button_lapisan", "yes");
		this.context.put("show_lapisan_berikut_tambah", "");
		this.context.put("show_lapisan_berikut_update", "yes");
		this.context.put("buttonwarislapisan", "Simpan");
		this.context.put("buttonwarislapisanSimpan", "");
		this.context.put("show_batal_waris_lapisan", "");
		this.context.put("show_lapisan_bawah", "");
		this.context.put("readmode", "");

		this.context.put("show_batal_waris", "yes");
	}

	// textfield penghutang
	public void initListTextfieldPenghutang() {
		this.context.put("socJenisHutang", getParam("socJenisHutangPenting"));
		if (getParam("socJenisHutangPenting").equals("2")) {
			this.context.put("checkradio1", "");
			this.context.put("checkradio2", "checked");
			this.context.put("checked3a", "");
			this.context.put("checked4a", "");
		} else {
			this.context.put("checkradio1", "checked");
			this.context.put("checkradio2", "");
			this.context.put("checked3a", "");
			this.context.put("checked4a", "");
		}

		/*
		 * if (getParam("socJenisHutangPentingU").equals("1")){
		 * this.context.put("checkradio1","checked");
		 * this.context.put("checkradio2",""); this.context.put("checked3a","");
		 * this.context.put("checked4a",""); }else if
		 * (getParam("socJenisHutangPentingU").equals("2")){
		 * this.context.put("checkradio1","");
		 * this.context.put("checkradio2","checked");
		 * this.context.put("checked3a",""); this.context.put("checked4a","");
		 * }else{ this.context.put("checkradio1","");
		 * this.context.put("checkradio2","");
		 * this.context.put("checked3a","readonly class=\"disabled\"");
		 * this.context.put("checked4a","disabled readonly class=\"disabled\"");
		 * }
		 */
		this.context.put("nokpbaru1", getParam("txtNoKPBaru1PentingU"));
		this.context.put("nokpbaru2", getParam("txtNoKPBaru2PentingU"));
		this.context.put("nokpbaru3", getParam("txtNoKPBaru3PentingU"));
		this.context.put("nokplama", getParam("txtSyarikatPentingU"));
		this.context.put("nokplama", getParam("txtNoKPLamaPenting"));
		this.context.put("nojeniskp", getParam("socJenisKPLainPenting"));
		this.context.put("nokplain", getParam("txtNoKPLainPenting"));
		this.context.put("namaOB", getParam("txtNamaOBPenting"));
		this.context.put("namasykt", getParam("txtNamaSyktPenting"));
		this.context.put("noacct", getParam("txtNoAkaunPenting"));
		this.context.put("noacctu", getParam("txtNoAkaunPentingU"));
		this.context.put("nilai", getParam("txtNilaiHutangPenting"));
		this.context.put("nilaihutang", getParam("txtNilaiHutangPentingU"));
		this.context.put("butiran", getParam("txtButiranHutangPenting"));
		this.context.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		this.context.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		this.context.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		this.context.put("poskod", getParam("txtPoskodPenting"));

	}

	public void initListTextfieldPenghutangBlank() {

		if (getParam("socJenisHutangPenting").equals("1")) {
			this.context.put("checkradio1", "checked");
			this.context.put("checkradio2", "");
			this.context.put("checked3a", "");
			this.context.put("checked4a", "");
			this.context.put("socJenisHutang", "1");
		} else if (getParam("socJenisHutangPenting").equals("2")) {
			this.context.put("checkradio1", "");
			this.context.put("checkradio2", "checked");
			this.context.put("checked3a", "");
			this.context.put("checked4a", "");
			this.context.put("socJenisHutang", "2");
		} else {
			this.context.put("checkradio1", "");
			this.context.put("checkradio2", "");
			this.context.put("checked3a", "readonly class=\"disabled\"");
			this.context.put("checked4a",
					"disabled readonly class=\"disabled\"");
			this.context.put("socJenisHutang", "");
		}

		this.context.put("nokpbaru1", "");
		this.context.put("nokpbaru2", "");
		this.context.put("nokpbaru3", "");
		this.context.put("nokplama", "");
		this.context.put("nokplama", "");
		this.context.put("nojeniskp", "");
		this.context.put("nokplain", "");
		this.context.put("namaOB", "");
		this.context.put("namasykt", "");
		this.context.put("noacct", "");
		this.context.put("nilai", "0.00");
		if (getParam("chcAlamat").equals("1")) {
			this.context.put("check3", "checked");
		} else {
			this.context.put("check3", "");
		}
		this.context.put("butiran", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("poskod", "");
		this.context.put("alamat1surat", "");
		this.context.put("alamat2surat", "");
		this.context.put("alamat3surat", "");
		this.context.put("poskodsurat", "");
	}

	public void initTextfieldPenghutangDuplicate() {
		this.context.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		this.context.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		this.context.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		this.context.put("poskod", getParam("txtPoskodPenting"));
		this.context.put("alamat1surat", getParam("txtAlamatTerakhir1Penting"));
		this.context.put("alamat2surat", getParam("txtAlamatTerakhir2Penting"));
		this.context.put("alamat3surat", getParam("txtAlamatTerakhir3Penting"));
		this.context.put("poskodsurat", getParam("txtPoskodPenting"));
	}

	public void initTextfieldPenghutangNotDuplicate() {
		this.context.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		this.context.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		this.context.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		this.context.put("poskod", getParam("txtPoskodPenting"));
		this.context.put("alamat1surat", "");
		this.context.put("alamat2surat", "");
		this.context.put("alamat3surat", "");
		this.context.put("poskodsurat", "");
	}

	// button Tambah Waris
	public void initButtonTambahWaris(String idp) {
		this.context.put("idwww", idp);
		this.context.put("idparentlapis", idp);
		this.context.put("show_lapisan_berikut", "yes");
		this.context.put("show_lapisan_berikut_tambah", "yes");
		this.context.put("buttonwarislapisanSimpan", "Simpan");
		this.context.put("buttonwarislapisan", "Simpan");
	}

	// peguam
	public void initTextfieldPeguamBlank() {
		this.context.put("namafirma", "");
		this.context.put("rujukfirma", "");
		this.context.put("add1", "");
		this.context.put("add2", "");
		this.context.put("add3", "");
		this.context.put("poskod", "");
		this.context.put("notel", "");
		this.context.put("faxno", "");
		this.context.put("email", "");
	}

	public void initTextfieldPeguam() {
		this.context.put("namafirma", getParam("txtNamaFirmaPeguam"));
		this.context.put("rujukfirma", getParam("txtNoRujukanFirma"));
		this.context.put("add1", getParam("txtAlamat1Peguam"));
		this.context.put("add2", getParam("txtAlamat2Peguam"));
		this.context.put("add3", getParam("txtAlamat3Peguam"));
		this.context.put("poskod", getParam("txtPoskodPeguam"));
		this.context.put("notel", getParam("txtNomborTelefonPeguam"));
		this.context.put("faxno", getParam("txtNomborFaksPeguam"));
		this.context.put("email", getParam("txtEmelPeguam"));
	}

	// single bandar negeri
	public void initBandarNegeriBaruSingle() throws Exception {
		String idNegeriTetap = "";
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = "";
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"javascript:onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initBandarNegeriBaruSingleSelectionDisabled() throws Exception {
		String idNegeriTetap = "";
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = "";
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\" onChange=\"javascript:onChangeBandarTetap()\""));
		this.context
				.put(
						"selectBandarTetap",
						HTML
								.SelectBandarByNegeri(idNegeriTetap,
										"socDaerah", Long
												.parseLong(idBandarTetap),
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initValueBandarNegeriSingle(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socBandarTetap", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initValueBandarNegeriSingleDisabled(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context
				.put(
						"selectBandarTetap",
						HTML
								.SelectBandarByNegeri(idNegeriTetap,
										"socBandarTetap", Long
												.parseLong(idBandarTetap),
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initValueBandarNegeriPenghutangDisabled(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context
				.put(
						"selectBandarTetap",
						HTML
								.SelectBandarByNegeri(idNegeriTetap,
										"socDaerah", Long
												.parseLong(idBandarTetap),
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initValueBandarNegeriPenghutangValue(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegeriTetap",
						HTML
								.SelectNegeri(
										"socNegeriTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetap()\""));
		this.context.put("selectBandarTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerah", Long.parseLong(idBandarTetap),
				" style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initListTextfieldPenting() {
		this.context.put("nokp1", getParam("txtNoKPBaru1Penting"));
		this.context.put("nokp2", getParam("txtNoKPBaru2Penting"));
		this.context.put("nokp3", getParam("txtNoKPBaru3Penting"));
		this.context.put("nokplama", getParam("txtNoKPLamaPenting"));
		this.context.put("jenisKp", getParam("socJenisKPLainPenting"));
		this.context.put("nokplain", getParam("txtNoKPLainPenting"));
		this.context.put("namaob", getParam("txtNamaOBPenting"));
		this.context.put("taraf", getParam("socTarafKepentinganPenting"));
		this.context.put("alamat1", getParam("txtAlamatTerakhir1Penting"));
		this.context.put("alamat2", getParam("txtAlamatTerakhir2Penting"));
		this.context.put("alamat3", getParam("txtAlamatTerakhir3Penting"));
		this.context.put("poskod", getParam("txtPoskodPenting"));
	}

	public void initListTextfieldPentingBlank() {
		this.context.put("nokp1", "");
		this.context.put("nokp2", "");
		this.context.put("nokp3", "");
		this.context.put("nokplama", "");
		this.context.put("jenisKp", "0");
		this.context.put("nokplain", "");
		this.context.put("namaob", "");
		this.context.put("taraf", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("poskod", "");
		this.context.put("taraf", "0");

	}

	public void initBandarNegeriBaruSinglehtaamx() throws Exception {
		String idNegeriTetap = "";
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = "";
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegerixTetap",
						HTML
								.SelectNegeri(
										"socNegerixTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"javascript:onChangeBandarTetaphtaam()\""));
		this.context.put("selectBandarxTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerahx", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initValueBandarNegeriSinglehtaamx(String socNegeriTetap,
			String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegerixTetap",
						HTML
								.SelectNegeri(
										"socNegerixTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetaphtaam()\""));
		this.context.put("selectBandarxTetap", HTML.SelectBandarByNegeri(
				idNegeriTetap, "socDaerahx", Long.parseLong(idBandarTetap),
				"style=\"width:225px;text-transform:uppercase;\""));
	}

	public void initValueBandarNegeriSingleDisabledhtaamx(
			String socNegeriTetap, String socBandarTetap) throws Exception {
		String idNegeriTetap = socNegeriTetap;
		if (idNegeriTetap == null || idNegeriTetap.trim().length() == 0) {
			idNegeriTetap = "0";
		}
		String idBandarTetap = socBandarTetap;
		if (idBandarTetap == null || idBandarTetap.trim().length() == 0) {
			idBandarTetap = "0";
		}

		this.context
				.put(
						"selectNegerixTetap",
						HTML
								.SelectNegeri(
										"socNegerixTetap",
										Long.parseLong(idNegeriTetap),
										null,
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\" onChange=\"onChangeBandarTetaphtaam()\""));
		this.context
				.put(
						"selectBandarxTetap",
						HTML
								.SelectBandarByNegeri(idNegeriTetap,
										"socDaerahx", Long
												.parseLong(idBandarTetap),
										"class=\"disabled\" disabled style=\"width:225px;text-transform:uppercase;\""));
	}

	public void textfieldHtaamValue() {
		this.context.put("mukim", getParam("socMukimHtaamX"));
		this.context.put("namapemaju", getParam("txtNamaPemajuHtaamX"));
		this.context.put("alamatpemaju1", getParam("txtAlamatPemaju1HtaamX"));
		this.context.put("alamatpemaju2", getParam("txtAlamatPemaju2HtaamX"));
		this.context.put("alamatpemaju3", getParam("txtAlamatPemaju3HtaamX"));
		this.context.put("poskodpemaju", getParam("txtPoskodPemaju1HtaamX"));
		this.context.put("luasasal", getParam("txtLuasAsalHtaamX"));
		this.context.put("jenisluas", getParam("socJenisLuasHtaamX"));
		this.context.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		this.context.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		this.context.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
		this.context.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		this.context.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
		this.context.put("tarikhperjanjian",
				getParam("txtTarikhPerjanjianHtaamX"));
		this.context.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
		this.context.put("noroh", getParam("txtNoRoh"));
		this.context.put("nolot", getParam("txtLotId"));
		this.context.put("jeniskepentingan", getParam("txtKepentinganHtaamX"));
	}

	public void initTextfieldHtaamxBlank() {
		this.context.put("radio2", "yes");
		this.context.put("radio3", "");
		this.context.put("checked2", "");
		this.context.put("checked1", "checked");
		this.context.put("checked3", "");
		this.context.put("idhta", "");
		this.context.put("noHakmilik", "");
		this.context.put("nopt", "");
		this.context.put("nilai_Hta_memohon", "");
		this.context.put("nilai_Hta_mati", "");
		this.context.put("kategori", "");
		this.context.put("jenishakmilik", "");
		this.context.put("pemilikan", "");
		this.context.put("negeri", "");
		this.context.put("daerah", "");
		this.context.put("mukim", "");
		this.context.put("luashmp", "");
		this.context.put("luasasal", "");
		this.context.put("nocagaran", "");
		this.context.put("nopajakan", "");
		this.context.put("jenistanah", "");
		this.context.put("basimati", "");
		this.context.put("bbsimati", "");
		this.context.put("jenishta", "");
		this.context.put("tanggungan", "");
		this.context.put("jenisluas", "");
		this.context.put("catatan", "");
		this.context.put("noperserahan", "");
		this.context.put("namapemaju", "");
		this.context.put("alamatpemaju1", "");
		this.context.put("alamatpemaju2", "");
		this.context.put("alamatpemaju3", "");
		this.context.put("poskodpemaju", "");
		this.context.put("bandarpemaju", "");
		this.context.put("negeripemaju", "");
		this.context.put("alamathta1", "");
		this.context.put("alamathta2", "");
		this.context.put("alamathta3", "");
		this.context.put("poskodhta", "");
		this.context.put("bandarhta", "");
		this.context.put("noperjanjian", "");
		this.context.put("tarikhperjanjian", "");
		this.context.put("namarancangan", "");
		this.context.put("noroh", "");
		this.context.put("nolot", "");
		this.context.put("nolot", "");
		this.context.put("nocagaran", "");
	}

	public void initTextfieldSimati() {
		this.context.put("nokpbaru1", "");
		this.context.put("nokpbaru2", "");
		this.context.put("nokpbaru3", "");
		this.context.put("nokplama", "");
		this.context.put("nojeniskp", "");
		this.context.put("nokplain", "");
		this.context.put("simatinama", getParam("txtNamaSimati"));
		this.context.put("simatinamalain", "");
		this.context.put("jantina", "");
		this.context.put("agama", "");
		this.context.put("warga", "");
		this.context.put("buktimati", "");
		this.context.put("nosijil", "");
		this.context.put("tarikhmati", "");
		this.context.put("umur", "");
		this.context.put("waktumati", "");
		this.context.put("tmptmati", "");
		this.context.put("txtSebabKematian", "");
		this.context.put("alamat1", "");
		this.context.put("alamat2", "");
		this.context.put("alamat3", "");
		this.context.put("poskod", "");
		this.context.put("catatan", "");
		this.context.put("sbbmati", "");
	}

	// DoPost
	public void initDoPost(String doPost) {
		if (doPost == "false") {
			System.out.println("browser refresh **********");
			submit = ""; // do what ever approriate action
		}
	}
}
