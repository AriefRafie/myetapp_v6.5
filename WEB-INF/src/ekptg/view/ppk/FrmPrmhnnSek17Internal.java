package ekptg.view.ppk;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakInternalData;
import ekptg.model.ppk.FrmSemakHartaSek17;
import ekptg.model.ppk.FrmSenaraiFailInternalCarianData;
import ekptg.model.ppk.FrmSenaraiFailInternalData;

public class FrmPrmhnnSek17Internal extends VTemplate {
	private static final long serialVersionUID = 1L;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String currentDate = dateFormat.format(date);
	FrmPrmhnnSek8InternalData logic_internal = null;
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = null;
	FrmPrmhnnSek8SenaraiHTATHInternalData logic_B = null;
	FrmPrmhnnSek8SenaraiSemakInternalData logic_C = null;
	FrmSenaraiFailInternalCarianData logic_D = null;
	FrmSenaraiFailInternalData logic_E = null;
	FrmSemakHartaSek17 FrmSemakHartaSek17 = null;
	FrmHeaderPpk mainheader = null;

	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek17Senarai.class);

	public Template doTemplate() throws Exception {
		logic_internal = new FrmPrmhnnSek8InternalData();
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
		logic_B = new FrmPrmhnnSek8SenaraiHTATHInternalData();
		logic_C = new FrmPrmhnnSek8SenaraiSemakInternalData();
		logic_D = new FrmSenaraiFailInternalCarianData();
		logic_E = new FrmSenaraiFailInternalData();
		FrmSemakHartaSek17 = new FrmSemakHartaSek17();
		mainheader = new FrmHeaderPpk();

		headerppk_baru_default();

		String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
		String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
		String flagForView = getParam("flagForView");

		/*
		Vector count_dunia1 = logic_A.getNofaildunia(2, 1, 17);
		this.context.put("count_dunia", count_dunia1);
		*/
		
		this.context.put("skrin_online_17", "");
		this.context.put("skrin_online", "");
		this.context.put("skrin_online_popup", "");
		this.context.put("appear_skrin_info", "");

		Calendar cal = Calendar.getInstance();
		Calendar currentcal = Calendar.getInstance();
		cal.set(2009, Calendar.SEPTEMBER, 1);
		currentcal.set(currentcal.get(Calendar.YEAR), currentcal
				.get(Calendar.MONTH), currentcal.get(Calendar.DAY_OF_MONTH));

		this.context.put("nowpast", getParam("nowpast"));
		String bolehsimpan = "";
		HttpSession session = this.request.getSession();
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			// System.out.println("browser true **********");
			bolehsimpan = "yes";

		} else {

			// System.out.println("browser false **********");
		}

		String selectedTabatas = "";
		String selectedTabtengah = "";
		String selectedTabbawah = "";
		String selectedTabtepi = "";
		String selectLevelTab = "";
		// HttpSession session = this.request.getSession();
		String vm = "";
		String submit = getParam("command");
		String mode = getParam("mode");
		String idAlert = getParam("idAlert");
		myLogger.info("submit :" + submit);
		myLogger.info("mode :" + mode);
		myLogger.info("bolehsimpan :" + bolehsimpan);
		// String idFlag = getParam("idFlag");
		int simpanStatus = 0;
		int eventStatus = 0;
		int backstatus = 0;

		String Carix = "";
		String disability1 = "";
		String disability2 = "";
		String readability1 = "";
		String readability2 = "";
		Vector listdaerah = null;
		Vector list = null;
		Vector listFail = null;
		Vector listPemohon = null;
		Vector listSimati = null;
		Vector listPeguam = null;
		Vector listKeputusan = null;
		Vector listWaris = null;
		Vector listWarisOB = null;
		Vector listHTA = null;
		Vector listHTAX = null;
		Vector listHTAid = null;
		Vector listHTAXid = null;
		Vector listWarisLapisanIdMati = null;
		Vector listWarisLapisan = null;
		Vector listWarisParent = null;
		Vector listWarisUpdate = null;
		Vector listHta = null;
		Vector listHtath = null;
		Vector listHa = null;
		Vector listPenting = null;
		Vector listPentingdulu = null;
		Vector listPenghutang = null;
		Vector listPenghutangdulu = null;
		Vector listPemiutang = null;
		Vector listSaksi = null;
		Vector listCheckPeguam = null;
		Vector listPenghutangbyIDOB = null;
		Vector listPentingbyIDOB = null;
		Vector listWarisLapisanIdMatiDelete = null;
		Vector listDaerahByUser = null;
		Vector chkId = null;
		Vector listUserid = null;
		Vector list1 = null;
		Vector listSemak = null;
		Vector listSemakSimati = null;
		Vector view1 = null;
		Vector list2 = null;
		Vector listIds = null;
		//Vector view2 = null;
		Vector listHTAXdulu = null;
		Vector listpeguam = null;
		Vector listPemohonOB = null;
		Vector listKPSimati = null;
		Vector listpeguamX = null;
		Vector listWarisup = null;
		Vector listpenting = null;
		Vector listnegeri = null;
		Vector listpeguamcheck = null;
		Vector listSemak2 = null;
		Vector listnegeribydaerah = null;
		Vector listabc = null;
		Vector listppkha = null;
		Vector sumppkha = null;
		Vector listppkhta = null;
		Vector listppkha2 = null;
		Vector overallnilai = null;
		Vector overallnilaimati = null;
		Vector listppkhtath = null;
		Vector listmukim = null;
		Vector listJenisha = null;
		Vector selectedppkha = null;
		Vector sumppkhta = null;
		Vector sumoverallppkhta = null;
		Vector listxxx = null;
		Vector v = null;
		Vector listWarisLapisanIdMatiDulu = null;
		Vector listppkhadulu = null;
		Vector listb = null;
		Vector listHTAdulu = null;
		Vector sumppkhtadulu = null;

		Vector listsemakhta = null;
		Vector listsemakha = null;

		Vector listOBHTAdulu = null;
		this.context.put("listOBHTAdulu", "");

		Vector listOBHAdulu = null;
		this.context.put("listOBHAdulu", "");

		this.context.put("listsemakhta", "");
		this.context.put("listsemakha", "");

		int flagno = 0;
		int idFlag = 0;
		int flag_no = 0;

		readability1 = "";
		this.context.put("SimpanStatus", "");
		this.context.put("mode1", "");
		this.context.put("flagno", "");
		this.context.put("idFlag", "");
		this.context.put("View", "");
		this.context.put("noFail", "");
		this.context.put("namapemohon", "");
		this.context.put("nokppemohon", "");
		this.context.put("carix", "");
		this.context.put("usid", "");
		this.context.put("Senaraifail", "");
		this.context.put("CountList", "");
		this.context.put("ListDaerahByUserX", "");
		this.context.put("GetNewPemohon", "");
		this.context.put("NegId", "");
		this.context.put("EventStatus", "");

		this.context.put("IdPermohonan", "");
		this.context.put("backStatus", "");
		this.context.put("ListSemakan", "");
		this.context.put("ListSemakanSimati", "");
		this.context.put("listkp", "");
		this.context.put("listkp2", "");
		this.context.put("tarikhmohon", "");
		this.context.put("EventStatus", "");
		this.context.put("idFlag", "");
		this.context.put("flagno", "");
		this.context.put("idAlert", "");
		this.context.put("IdPemohon", "");
		this.context.put("IdSimati", "");
		this.context.put("IdPemohon", "");
		this.context.put("tarikhmohondaftar", "");
		this.context.put("pendaftaran", "yes");
		this.context.put("duplicate", "");
		this.context.put("listKPSimati", "");
		this.context.put("tarikhmohon", "");
		this.context.put("readmode", "");
		this.context.put("show_kemaskini_button", "");
		this.context.put("show_simpan_button", "");
		this.context.put("show_batal_button", "");
		this.context.put("selectedTabatas", "");
		this.context.put("selectedTabtengah", "");
		this.context.put("selectedTabbawah", "");
		this.context.put("selectedTabtepi", "");
		this.context.put("show_kemaskini_button", "");
		this.context.put("show_simpan_button", "");
		this.context.put("show_batal_button", "");
		this.context.put("dah_daftar_ke", "");
		this.context.put("daftar", "");
		this.context.put("show_pemohonsimati", "");
		this.context.put("pemohonbaru", "");
		this.context.put("pemohonlama", "");
		this.context.put("kosongkan_pemohon", "");
		this.context.put("listPemohonD17", "");
		this.context.put("xdak_nofail", "");
		this.context.put("msg_permohonansebelum", "");

		this.context.put("listPemohonOB", "");

		this.context.put("list_semakhta_check", "");
		this.context.put("list_semakha_check", "");

		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));

		String v_tab = getParam("v_tab");
		// System.out.print("V_TAB" + v_tab + ";");

		this.context.put("val_tab", v_tab);

		if ("tab".equals(submit)) {
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			// int id = Integer.parseInt(getParam("idpermohonan"));
			String id = getParam("idpermohonan_baru");
			String id1 = getParam("idSimati");
			list = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("View", list);
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");
			logic_internal.setDataSimati(id);
			listSimati = logic_internal.getDataSimati();

			Hashtable h1 = (Hashtable) listSimati.get(0);
			if (h1.get("idnegeri").toString() != ""
					&& h1.get("idnegeri").toString() != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h1
						.get("idnegeri").toString()));
				this.context.put("listBandarTetapbyNegeri", s3);
			} else {
				this.context.put("listBandarTetapbyNegeri", "");
			}

			int s1 = Integer.parseInt(getParam("no_subjaket"));
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			this.context.put("listSimati", listSimati);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
			this.context.put("readmode", disability1);
			this.context.put("show_kemaskini_button", "yes");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");
			vm = "app/ppk/frmPraPrmhnnSek17Simati.jsp";
			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
		} else if ("papar".equals(submit)) {
			String idSimati = getParam("idSimati");
			simpanStatus = 0;
			this.context.put("SimpanStatus", simpanStatus);
			eventStatus = 0;
			backstatus = 0;
			this.context.put("EventStatus", eventStatus);
			this.context.put("backStatus", backstatus);
			String NegId = "1";
			String IDPermohonan = "0";
			this.context.put("IdPermohonan", IDPermohonan);
			this.context.put("NegId", NegId);
			this.context.put("idFlag", idFlag);
			this.context.put("idSimati", idSimati);
			String idpermohonan = getParam("idpermohonan");

			String idp = idpermohonan;
			this.context.put("idpermohonan", idpermohonan);
			list2 = logic_A.setData(idp, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("listb", "");
			this.context.put("ViewLamaSub", list2);
			// hashtable maklumat header
			// headerppk_baru(session,idp,"Y","","T");
			this.context.put("View", "");
			this.context.put("dah_daftar_ke", "belum");
			if (cal.before(currentcal)) {
				this.context.put("lepassatusept", "no");
			} else if (cal.after(currentcal)) {
				this.context.put("lepassatusept", "yes");
			} else {
				this.context.put("lepassatusept", "yes");
			}
			listsemakhta = logic_internal.setTujuanSemak_hta(idpermohonan);
			this.context.put("listsemakhta", listsemakhta);
			listsemakha = logic_internal.setTujuanSemak_ha(idpermohonan);
			this.context.put("listsemakha", listsemakha);
			String id_permohonan = "";
			String flag_daftar = "";
			id_permohonan = idpermohonan;
			flag_daftar = "LAMA";

			this.context.put("list_semakhta_check", FrmSemakHartaSek17
					.list_semakhta(idSimati, id_permohonan, flag_daftar));
			this.context.put("list_semakha_check", FrmSemakHartaSek17
					.list_semakha(idSimati, id_permohonan, flag_daftar));
			
			//aishahlatip : checking sama ada no fail ini mengandungi permohonan online tetapi belum hantar permohonan
			boolean isPermohonanExist= logic_internal.setIsPermohonanOnlineExist(idSimati);
			this.context.put("isPermohonanExist",isPermohonanExist);
			
			vm = "app/ppk/frmPraPrmhnnSek17SenaraiSemak.jsp";

		} else if ("Kemaskini".equals(submit)) {
			String tempid = getParam("idtemp");
			String negId = getParam("negid");
			this.context.put("NegId", negId);
			String idPermohonan = tempid;
			String eventstatus = getParam("eventStatus");

			this.context.put("EventStatus", eventstatus);
			simpanStatus = 1;
			this.context.put("SimpanStatus", simpanStatus);
			FrmPrmhnnSek8SenaraiSemakInternalData frmPrmhnnSek8SenaraiSemakData = new FrmPrmhnnSek8SenaraiSemakInternalData();
			// frmPrmhnnSek8SenaraiSemakData.setDataSemakan(idPermohonan);
			this.context.put("IdPermohonan", idPermohonan);

			logic_C.setDataSemakan(idPermohonan);
			listSemak2 = logic_C.getListSemakan();
			this.context.put("ListSemakan", listSemak2);
			frmPrmhnnSek8SenaraiSemakData.setDataSemakanSimati(idPermohonan);

			listSemakSimati = logic_C.getListSemakanSimati();
			this.context.put("ListSemakanSimati", listSemakSimati);

			String idSimati = getParam("idSimati");
			this.context.put("idSimati", idSimati);
			String idpermohonan = getParam("idpermohonan");

			this.context.put("idpermohonan", idpermohonan);
			String idp = idpermohonan;
			list2 = logic_A.setData(idp, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ViewLamaSub", list2);
			// hashtable maklumat header
			// headerppk_baru(session,idp,"Y","","T");
			String baru_id = getParam("idpermohonan_baru");
			listb = logic_A.setDataBaru(baru_id, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("listb", listb);
			headerppk_baru(session, baru_id, "Y", "", "T");

			this.context.put("daftar", "yes");
			this.context.put("dah_daftar_ke", getParam("dah_daftar_ke"));

			this.context.put("txtNomborResit", getParam("txtNomborResit"));
			this.context.put("txdTarikhByrn", getParam("txdTarikhByrn"));
			this.context.put("bkp", getParam("bkp"));
			this.context.put("lp", getParam("lp"));
			this.context.put("bpa", getParam("bpa"));
			this.context.put("lpa", getParam("lpa"));
			this.context.put("ht", getParam("ht"));
			this.context.put("lt", getParam("lt"));

			listsemakhta = logic_internal
					.setTujuanSemak_hta(getParam("idtempTerdahulu"));
			this.context.put("listsemakhta", listsemakhta);
			listsemakha = logic_internal
					.setTujuanSemak_ha(getParam("idtempTerdahulu"));
			this.context.put("listsemakha", listsemakha);

			String id_permohonan_baru = getParam("idpermohonan_baru");
			String id_permohonan_lama = getParam("idpermohonan");
			String id_permohonan = "";
			String flag_daftar = "";
			if (id_permohonan_baru.equals("")) {
				id_permohonan = id_permohonan_lama;
				flag_daftar = "LAMA";
			} else {
				id_permohonan = id_permohonan_baru;
				flag_daftar = "BARU";
			}

			this.context.put("list_semakhta_check", FrmSemakHartaSek17
					.list_semakhta(idSimati, id_permohonan, flag_daftar));
			this.context.put("list_semakha_check", FrmSemakHartaSek17
					.list_semakha(idSimati, id_permohonan, flag_daftar));

			vm = "app/ppk/frmPraPrmhnnSek17SenaraiSemak.jsp";
			
		} else if ("Simpan".equals(submit)) {
			String eventstatus = getParam("eventStatus");
			eventStatus = Integer.parseInt(eventstatus);
			if (eventStatus == 0) {
				long idPermohonan = DB.getNextID("TBLPPKPERMOHONAN_SEQ");
				// String buktimati = getParam("cbsemakradio");
				myLogger.info("idPermohonan = "+idPermohonan);
				String tempid = Long.toString(idPermohonan);
				if (bolehsimpan.equals("yes")) {
					delete_semakan(session, tempid);
				}
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
				FrmPrmhnnSek8SenaraiSemakInternalData frmPrmhnnSek8SenaraiSemakData = new FrmPrmhnnSek8SenaraiSemakInternalData();
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						String txtbox = "";
						String txtbox2 = "";
						String tarikhresit = "";

						if (cbsemaks[i].equals("9")) {
							txtbox = getParam("txtNomborResit");
							tarikhresit = getParam("txdTarikhByrn");
						} else if (cbsemaks[i].equals("17")) {
							txtbox = getParam("txtLainLainTujuan");
						}
						else if (cbsemaks[i].equals("23")) {
							txtbox = getParam("txtLainLainDokumen");
							//System.out.println("txtbox2 kalau csbsemak(8)= " + txtbox);
						}
						// frmPrmhnnSek8SenaraiSemakData.semakanAdd(cbsemaks[i],
						// String.valueOf(idPermohonan), String.valueOf(txtbox),
						// String.valueOf(tarikhresit));
						frmPrmhnnSek8SenaraiSemakData.semakanAdd17(cbsemaks[i],
								String.valueOf(idPermohonan), String
										.valueOf(txtbox), String
										.valueOf(tarikhresit));

					}
				}
				eventStatus = 1;
				this.context.put("EventStatus", eventStatus);
				simpanStatus = 2;
				int getnewpemohon = 1;
				String idNeg = getParam("negid");
				this.context.put("NegId", idNeg);
				this.context.put("GetNewPemohon", getnewpemohon);
				this.context.put("SimpanStatus", simpanStatus);
				logic_C.setDataSemakan("" + idPermohonan);
				listSemak = logic_C.getListSemakan();

				this.context.put("IdPermohonan", idPermohonan);
				this.context.put("ListSemakan", listSemak);

				listsemakhta = logic_internal
						.setTujuanSemak_hta(getParam("idtempTerdahulu"));
				this.context.put("listsemakhta", listsemakhta);
				listsemakha = logic_internal
						.setTujuanSemak_ha(getParam("idtempTerdahulu"));
				this.context.put("listsemakha", listsemakha);

				String idSimati = getParam("idSimati");

				String id_permohonan_baru = getParam("idpermohonan_baru");
				String id_permohonan_lama = getParam("idpermohonan");
				String id_permohonan = "";
				String flag_daftar = "";
				if (id_permohonan_baru.equals("")) {
					id_permohonan = id_permohonan_lama;
					flag_daftar = "LAMA";
				} else {
					id_permohonan = id_permohonan_baru;
					flag_daftar = "BARU";
				}

				this.context.put("list_semakhta_check", FrmSemakHartaSek17
						.list_semakhta(idSimati, id_permohonan, flag_daftar));
				this.context.put("list_semakha_check", FrmSemakHartaSek17
						.list_semakha(idSimati, id_permohonan, flag_daftar));

				updateFlagHarta(session);

				vm = "app/ppk/frmPraPrmhnnSek17SenaraiSemak.jsp";

			} else if (eventStatus == 1) {
				String tempid = getParam("idtemp");
				String idPerm = tempid;
				// String buktimati = getParam("cbsemakradio");
				String idPermohonan = tempid;
				if (bolehsimpan.equals("yes")) {
					delete_semakan(session, tempid);
				}
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
				//String[] cbsemaks20 = this.request.getParameterValues("cbsemaks20");
				String txtbox2 = getParam("txtLainLainDokumen");
				FrmPrmhnnSek8SenaraiSemakInternalData frmPrmhnnSek8SenaraiSemakData = new FrmPrmhnnSek8SenaraiSemakInternalData();
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						String txtbox = "";
						txtbox2 = "";
						String tarikhresit = "";
						if (cbsemaks[i].equals("9")) {
							txtbox = getParam("txtNomborResit");
							tarikhresit = getParam("txdTarikhByrn");
							if (bolehsimpan.equals("yes")) {
								// //System.out.println("");
								logic_C
										.semakanUpdateBayar(
												String.valueOf(tempid),
												txtbox,
												tarikhresit,
												(String) session
														.getAttribute("_ekptg_user_id"));
							}
						} else if (cbsemaks[i].equals("17")) {
							txtbox = getParam("txtLainLainTujuan");
						}
						 if (cbsemaks[i].equals("23")) {
							//txtbox2 = getParam("txtLainLainDokumen");
							 txtbox = getParam("txtLainLainDokumen");
							//System.out.println("txtbox " + txtbox);
						}

						frmPrmhnnSek8SenaraiSemakData.semakanUpdate17(
								cbsemaks[i], String.valueOf(idPermohonan),
								String.valueOf(txtbox), String
										.valueOf(tarikhresit));

					}
				}

				if (bolehsimpan.equals("yes")) {
					logic_C.semakanUpdateTujuanHT(String.valueOf(tempid),
							(String) session.getAttribute("_ekptg_user_id"),
							getParam("ht"));
					logic_C.semakanUpdateTujuanLPA(String.valueOf(tempid),
							(String) session.getAttribute("_ekptg_user_id"),
							getParam("lpa"));
					logic_C.semakanUpdateTujuanBPA(String.valueOf(tempid),
							(String) session.getAttribute("_ekptg_user_id"),
							getParam("bpa"));
					logic_C.semakanUpdateTujuanLP(String.valueOf(tempid),
							(String) session.getAttribute("_ekptg_user_id"),
							getParam("lp"));
					logic_C.semakanUpdateTujuanBKP(String.valueOf(tempid),
							(String) session.getAttribute("_ekptg_user_id"),
							getParam("bkp"));
					// ::
					logic_C.semakanUpdateLainTujuan(String.valueOf(tempid),
							(String) session.getAttribute("_ekptg_user_id"),
							getParam("lt"));
				}

				eventStatus = 1;
				this.context.put("EventStatus", eventStatus);
				simpanStatus = 2;
				int getnewpemohon = 0;
				String idNeg = getParam("negid");

				this.context.put("NegId", idNeg);
				this.context.put("GetNewPemohon", getnewpemohon);
				this.context.put("SimpanStatus", simpanStatus);
				logic_C.setDataSemakan(idPermohonan);
				this.context.put("IdPermohonan", idPermohonan);

				String t_id = getParam("idtemp");
				list2 = logic_A.setData(t_id, (String) session
						.getAttribute("_ekptg_user_id"));
				this.context.put("listb", list2);
				// hashtable maklumat header
				headerppk_baru(session, t_id, "Y", "", "T");

				listSemak = listSemak = logic_C.getListSemakan();
				this.context.put("ListSemakan", listSemak);
				listsemakhta = logic_internal
						.setTujuanSemak_hta(getParam("idtempTerdahulu"));
				this.context.put("listsemakhta", listsemakhta);
				listsemakha = logic_internal
						.setTujuanSemak_ha(getParam("idtempTerdahulu"));
				this.context.put("listsemakha", listsemakha);

				String idSimati = getParam("idSimati");

				String id_permohonan_baru = getParam("idpermohonan_baru");
				String id_permohonan_lama = getParam("idpermohonan");
				String id_permohonan = "";
				String flag_daftar = "";
				if (id_permohonan_baru.equals("")) {
					id_permohonan = id_permohonan_lama;
					flag_daftar = "LAMA";
				} else {
					id_permohonan = id_permohonan_baru;
					flag_daftar = "BARU";
				}

				this.context.put("list_semakhta_check", FrmSemakHartaSek17
						.list_semakhta(idSimati, id_permohonan, flag_daftar));
				this.context.put("list_semakha_check", FrmSemakHartaSek17
						.list_semakha(idSimati, id_permohonan, flag_daftar));

				updateFlagHarta(session);

				vm = "app/ppk/frmPraPrmhnnSek17SenaraiSemak.jsp";

			}

			this.context.put("txtNomborResit", getParam("txtNomborResit"));
			this.context.put("txdTarikhByrn", getParam("txdTarikhByrn"));
			this.context.put("bkp", getParam("bkp"));
			this.context.put("lp", getParam("lp"));
			this.context.put("bpa", getParam("bpa"));
			this.context.put("lpa", getParam("lpa"));
			this.context.put("ht", getParam("ht"));
			// ::
			this.context.put("lt", getParam("lt"));

			String idSimati = getParam("idSimati");
			this.context.put("idSimati", idSimati);
			String idpermohonan = getParam("idpermohonan");
			this.context.put("idpermohonan", idpermohonan);
			String idtempTerdahulu = getParam("idtempTerdahulu");
			String idp = idtempTerdahulu;
			list2 = logic_A.setData(idp, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("daftar", "yes");
			this.context.put("dah_daftar_ke", getParam("dah_daftar_ke"));

			this.context.put("ViewLamaSub", list2);
			// hashtable maklumat header
			// headerppk_baru(session,idp,"Y","","T");

			String baru_id = getParam("idpermohonan_baru");
			if (baru_id != "") {
				listb = logic_A.setDataBaru(baru_id, (String) session
						.getAttribute("_ekptg_user_id"));
				this.context.put("listb", listb);
				headerppk_baru(session, baru_id, "Y", "", "T");
			}

			listsemakhta = logic_internal.setTujuanSemak_hta(idtempTerdahulu);
			this.context.put("listsemakhta", listsemakhta);
			listsemakha = logic_internal.setTujuanSemak_ha(idtempTerdahulu);
			this.context.put("listsemakha", listsemakha);

		}

		else if ("Seterusnya".equals(submit)) {
			String idSimati = getParam("idSimati");
			String ids = idSimati;
			int no_sj = 0;
			if (!"".equals(getParam("no_subjaket"))) {
				no_sj = Integer.parseInt(getParam("no_subjaket"));
			}
			this.context.put("idSimati", idSimati);

			logic_A.setDataLama(ids, (no_sj + 1));
			Vector list4 = logic_A.getDataLama();
			this.context.put("ViewBaruNoFailLama", list4);

			// System.out.println("list4.size():" + list4.size());

			chkId = logic_A.getId();
			/*
			view1 = logic_A.getJenisKp();
			this.context.put("listkp", view1);
			view2 = logic_A.getJenisKp();
			this.context.put("listkp2", view2);
			*/

			if (list4.size() == 0) {

				String idt = getParam("idtempTerdahulu");

				logic_A.setDataFail(idt);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);

				this.context.put("id_Permohonan_terdahulu", idt);

				list2 = logic_A.setData(idt, (String) session
						.getAttribute("_ekptg_user_id"));
				this.context.put("ViewLama", list2);
				// hashtable maklumat header
				// headerppk_baru(session,idt,"Y","","T");
				this.context.put("ViewBaru", "");
				int evenstatus = 0;
				int idflag = 2;
				int flagNo = 1;
				idAlert = "0";
				this.context.put("idFlag", idflag);
				this.context.put("flagno", flagNo);
				this.context.put("idAlert", idAlert);
				this.context.put("EventStatus", evenstatus);
				String tempid = getParam("idtemp");
				this.context.put("IdPermohonan", tempid);
				long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
				this.context.put("IdPemohon", idPemohon);
				String IdNeg = getParam("negid");
				this.context.put("NegId", IdNeg);

				Hashtable h = (Hashtable) list2.get(0);
				this.context.put("IdSimati", Long.parseLong(h.get("idSimati")
						.toString()));
				this.context.put("IdPemohon", Long.parseLong(h.get("idPemohon")
						.toString()));

				if (h.get("pmidnegeri").toString() == "") {
					this.context.put("negeri", "");
					this.context.put("daerah", "");
				} else {
					this.context.put("negeri", h.get("pmidnegeri").toString());
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("pmidnegeri").toString()));
					this.context.put("listBandarbyNegeri", s3);
					this.context.put("daerah", "");
				}
				if (h.get("idbandar").toString() == "") {
					this.context.put("daerah", "");
				} else {
					this.context.put("daerah", h.get("idbandar").toString());
				}

				listDaerahByUser = logic_A
						.getDaerahByNegeriUser((String) session
								.getAttribute("_ekptg_user_id"));
				// System.out.println("ID SESSION"
				// + (String) session.getAttribute("_ekptg_user_id"));
				this.context.put("ListDaerahByUser", listDaerahByUser);
				this.context.put("tarikhmohondaftar", "");
				this.context.put("tarikhmohon", currentDate);
				System.out.println("CURRRR DATE::" + currentDate);
				// this.context.put("pendaftaran", "");
				this.context.put("dah_daftar_ke", "belum");

				Vector listSenaraiPemohonSimati = null;
				logic_A.SenaraiPemohonSimati(idSimati);
				listSenaraiPemohonSimati = logic_A.getSenaraiPemohonSimati();
				this.context.put("listSenaraiPemohonSimati",
						listSenaraiPemohonSimati);

				this.context.put("show_pemohonsimati", "yes");
				this.context.put("pemohonbaru", "yes");
				this.context.put("pemohonlama", "");
				this.context.put("kosongkan_pemohon", "yes");

			} else {
				idFlag = 2;
				flag_no = 2;
				eventStatus = 1;
				this.context.put("tarikhmohon", getParam("tarikh_daftar"));
				this.context.put("flagno", flag_no);
				this.context.put("idFlag", idFlag);
				this.context.put("EventStatus", eventStatus);
				String IdNeg = getParam("negid");
				this.context.put("NegId", IdNeg);
				// view_mode_pemohon(session);
				String idt = getParam("idtempTerdahulu");
				logic_A.setDataFail(idt);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);

				this.context.put("id_Permohonan_terdahulu", idt);

				String id = getParam("idPermohonan");
				String idSimatim = getParam("idSimati");

				Vector list3 = logic_A.setData(id, (String) session.getAttribute("_ekptg_user_id"));
			
				// hashtable maklumat header
				headerppk_baru(session, id, "Y", "", "T");

				this.context.put("ViewBaru", list3);
				this.context.put("ViewLama", "");
				Hashtable h = (Hashtable) list3.get(0);
				if (h.get("pmidnegeri").toString() == "") {
					this.context.put("negeri", "");
					this.context.put("daerah", "");
				} else {
					this.context.put("negeri", h.get("pmidnegeri").toString());
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("pmidnegeri").toString()));
					this.context.put("listBandarbyNegeri", s3);
					this.context.put("daerah", "");
				}
				if (h.get("idbandar").toString() == "") {
					this.context.put("daerah", "");
				} else {
					this.context.put("daerah", h.get("idbandar").toString());
				}

				String sj = h.get("no_subjaket").toString();
				// Azam change here
				int no_sjj = 0;
				if (!"".equals(sj)) {
					no_sjj = Integer.parseInt(sj);
				}

				logic_A.setDataLama(idSimatim, (no_sjj - 1));
				Vector list9 = logic_A.getDataLama();
				this.context.put("ViewBaruNoFailLama", list9);
				logic_A.setGetId(id);
				listIds = logic_A.getIds();

				Hashtable k = (Hashtable) listIds.get(0);
				this.context.put("IdSimati", Long.parseLong(k.get("idsimati")
						.toString()));
				this.context.put("IdPemohon", Long.parseLong(k.get("idpemohon")
						.toString()));
				this.context.put("IdPermohonan", getParam("idPermohonan"));
				listDaerahByUser = logic_A
						.getDaerahByNegeriUser((String) session
								.getAttribute("_ekptg_user_id"));
				this.context.put("ListDaerahByUser", listDaerahByUser);
				// this.context.put("pendaftaran", "yes");
				this.context.put("dah_daftar_ke", "sudah");
				this.context.put("show_pemohonsimati", "");

			}

			this.context.put("txtNomborResitH", getParam("txtNomborResit"));
			this.context.put("txdTarikhByrnH", getParam("txdTarikhByrn"));
			this.context.put("bkp", getParam("bkp"));
			this.context.put("lp", getParam("lp"));
			this.context.put("bpa", getParam("bpa"));
			this.context.put("lpa", getParam("lpa"));
			this.context.put("ht", getParam("ht"));
			// ::
			this.context.put("lt", getParam("lt"));

			/*
			 * int t_id = Integer.parseInt(getParam("idtemp")); list2 =
			 * logic_A.setData
			 * (t_id,(String)session.getAttribute("_ekptg_user_id"));
			 * this.context.put("list2",list2);
			 */
			String id_b = getParam("idpermohonan_baru");
			if (id_b != "") {
				String pb = id_b;
				logic_internal.setDataPemohonOB(pb);
				listPemohonOB = logic_internal.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
			}

			vm = "app/ppk/frmPrmhnnSek8DaftarSek17.jsp";
		} else if ("getBandar".equals(submit)) {
			String IdPermohonan = getParam("idPermohonan");
			String Idppp = getParam("idpermohonan");
			check_mode_permohonan(session);
			chkId = logic_A.getId();
			Hashtable b = (Hashtable) chkId.get(0);
			int cntID = Integer.parseInt(b.get("count_id").toString());
			String useridx = (String) session.getAttribute("_ekptg_user_id");
			logic_A.setGetUserId(useridx);
			listUserid = logic_A.getUserIds();
			Hashtable t = (Hashtable) listUserid.get(0);
			String userIdDaerah = t.get("idDaerah").toString();
			String userIdNeg = t.get("idNegeri").toString();
			String userIdKodDaerah = t.get("kodDaerah").toString();
			String userIdKodNegeri = t.get("kodnegeri").toString();
			String userIdPejabat = t.get("idpejabat").toString();
			String negeribandar = getParam("socNegeri");
			String bandar_bandar = getParam("socBandar");

			String ids = getParam("idSimati");

			// Azam change
			// int no_sj = Integer.parseInt(getParam("no_subjaket"));
			int no_sj = 0;
			if (!"".equals(getParam("no_subjaket"))) {
				no_sj = Integer.parseInt(getParam("no_subjaket"));
			}
			/*
			view1 = logic_A.getJenisKp();
			this.context.put("listkp", view1);
			*/
			this.context.put("tarikhmohon", currentDate);
			int evenstatus = 0;
			int idflag = 2;
			int flagNo = 1;
			this.context.put("idFlag", idflag);
			this.context.put("flagno", flagNo);
			this.context.put("EventStatus", evenstatus);
			String tempid = getParam("idPermohonan");
			this.context.put("IdPermohonan", tempid);
			Hashtable k = new Hashtable();
			v = new Vector();
			k.put("idFail", getParam("idFail"));
			k.put("idPemohon", getParam("idPemohon"));
			k.put("idPemohonan", getParam("idpermohonan"));
			k.put("umursimati", getParam("txtUmurSimati"));
			k.put("jantinasimati", getParam("socJantinaSimati"));
			k.put("umurpemohon", getParam("txtUmurPemohon"));
			k.put("jantinapemohon", getParam("socJantinaPemohon"));

			if (getParam("dah_daftar_ke").equals("belum")) {
				k.put("noFail", getParam("txtNoFail"));
			}
			if (getParam("dah_daftar_ke").equals("sudah")) {
				k.put("noFail", getParam("txtNoFailBaru"));
			}

			k.put("tarikhMohon", getParam("txdTarikhMohon"));
			k.put("noKpBaru1", getParam("txtNoKPBaruSimati1"));
			k.put("noKpBaru2", getParam("txtNoKPBaruSimati2"));
			k.put("noKpBaru3", getParam("txtNoKPBaruSimati3"));
			k.put("noKpLama", getParam("txtNoKPLamaSimati"));
			k.put("jenisKp", getParam("socJenisKPLainSimati"));
			k.put("noKpLain", getParam("txtNoKPLainSimati"));
			k.put("idSimati", getParam("idSimati"));
			k.put("namaSimati", getParam("txtNamaSimati"));
			k.put("tarikhMati", getParam("txtTarikhMati"));
			k.put("noKpBaruPemohon1", getParam("txtNoKPBaruPemohon1"));
			k.put("noKpBaruPemohon2", getParam("txtNoKPBaruPemohon2"));
			k.put("noKpBaruPemohon3", getParam("txtNoKPBaruPemohon3"));
			k.put("noKpLamaPemohon", getParam("txtNoKPLamaPemohon"));
			k.put("noKpLainPemohon", getParam("txtNoKPLainPemohon"));
			k.put("jenisKpPemohon", getParam("socJenisKPLainPemohon"));
			k.put("namaPemohon", getParam("txtNamaPemohon"));
			k.put("alamat1", getParam("txtAlamat1"));
			k.put("alamat2", getParam("txtAlamat2"));
			k.put("alamat3", getParam("txtAlamat3"));
			k.put("poskod", getParam("txtPoskod"));
			k.put("bandarsurat", getParam("txtBandar"));
			// k.put("idDaerah",getParam("socDaerahinput"));
			k.put("umursimati", getParam("txtUmurSimati"));
			k.put("jantinasimati", getParam("socJantinaSimati"));
			k.put("umurpemohon", getParam("txtUmurPemohon"));
			k.put("jantinapemohon", getParam("socJantinaPemohon"));
			k.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));
			k.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
			k.put("id_Permohonansimati", getParam("id_Permohonansimati"));
			k.put("no_subjaket", getParam("no_subjaket"));
			k.put("idDaerah", getParam("socDaerah"));

			k.put("taraf_penting", getParam("taraf_penting"));
			k.put("no_tel", getParam("no_tel"));
			k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
			k.put("jenis_pemohon", getParam("jenis_pemohon"));

			k.put("no_hp", getParam("no_hp"));
			k.put("jenis_pej", getParam("jenis_pej"));
			k.put("socSaudaraWaris", getParam("socSaudaraWaris"));

			v.addElement(k);
			// this.context.put("ViewLama",v);

			if (getParam("dah_daftar_ke").equals("belum")) {
				this.context.put("ViewLama", v);
				this.context.put("ViewBaru", "");
				this.context.put("dah_daftar_ke", "belum");

				this.context.put("show_pemohonsimati", "yes");

				if (getParam("radioP").equals("B")) {
					this.context.put("pemohonbaru", "yes");
					this.context.put("pemohonlama", "");
				}
				if (getParam("radioP").equals("L")) {
					this.context.put("pemohonlama", "yes");
					this.context.put("pemohonbaru", "");
					this.context
							.put("pemohonSimati", getParam("pemohonSimati"));

					if (getParam("pemohonSimati") != "") {
						Vector listPemohonD17 = null;
						logic_internal
								.setDataPemohonD17(getParam("pemohonSimati"));
						listPemohonD17 = logic_internal.getDataPemohonD17();
						// this.context.put("listPemohonD17", listPemohonD17);

						/*
						 * Hashtable h = (Hashtable)listPemohonD17.get(0);
						 * if(h.get("idnegeri").toString()!="" &&
						 * h.get("idnegeri").toString()!="0"){ Vector s3 =
						 * logic_A
						 * .getListBandarByNegeri(Integer.parseInt(h.get(
						 * "idnegeri" ).toString()));
						 * this.context.put("listBandarTetapbyNegeri",s3);}
						 * else{this.context.put("listBandarTetapbyNegeri","");}
						 */
					} else {
						this.context.put("kosongkan_pemohon", "yes");
						this.context.put("pemohonSimati", "");
					}

					this.context
							.put("pemohonSimati", getParam("pemohonSimati"));

					Vector listSenaraiPemohonSimati = null;
					logic_A.SenaraiPemohonSimati(ids);
					listSenaraiPemohonSimati = logic_A
							.getSenaraiPemohonSimati();
					this.context.put("listSenaraiPemohonSimati",
							listSenaraiPemohonSimati);

				}

			} else {
				this.context.put("ViewLama", "");
				this.context.put("ViewBaru", v);

				logic_A.setDataLama(ids, (no_sj - 1));
				Vector list4 = logic_A.getDataLama();
				this.context.put("ViewBaruNoFailLama", list4);

				String idt = getParam("idpermohonan");
				logic_A.setDataFail(idt);
				listFail = logic_A.getDataFail();
				headerppk_baru(session, idt, "Y", "", "T");
				this.context.put("ViewFail", listFail);
				this.context.put("dah_daftar_ke", "sudah");

			}

			String id = tempid;
			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);
			if (getParam("socNegeri") == "") {
				this.context.put("negeri", "");
				this.context.put("daerah", "");
			} else {
				this.context.put("negeri", getParam("socNegeri"));
				Vector s = logic_A.getListBandarByNegeri(Integer
						.parseInt(getParam("socNegeri")));
				this.context.put("listBandarbyNegeri", s);
				this.context.put("daerah", "");
			}

			listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);

			this.context.put("id_Permohonan_terdahulu",
					getParam("id_Permohonan_terdahulu"));
			this.context.put("id_Permohonan_terdahulu",
					getParam("idtempTerdahulu"));
			this.context.put("txtNomborResitH", getParam("txtNomborResitH"));
			this.context.put("txdTarikhByrnH", getParam("txdTarikhByrnH"));
			this.context.put("IdSimati", getParam("idSimati"));

			String id_b = getParam("idPermohonan");
			if (id_b != "") {
				String pb = id_b;
				logic_internal.setDataPemohonOB(pb);
				listPemohonOB = logic_internal.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
			}
			/*
			 * if (getParam("pemohonSimati") != "") { Vector listPemohonD17 =
			 * null; logic_internal.setDataPemohonD17(Integer
			 * .parseInt(getParam("pemohonSimati"))); listPemohonD17 =
			 * logic_internal.getDataPemohonD17();
			 * 
			 * }
			 */

			vm = "app/ppk/frmPrmhnnSek8DaftarSek17.jsp";
		}

		else if ("getPemohon".equals(submit)) {
			String IdPermohonan = getParam("idPermohonan");
			String Idppp = getParam("idpermohonan");
			check_mode_permohonan(session);
			chkId = logic_A.getId();
			Hashtable b = (Hashtable) chkId.get(0);
			int cntID = Integer.parseInt(b.get("count_id").toString());
			String useridx = (String) session.getAttribute("_ekptg_user_id");
			logic_A.setGetUserId(useridx);
			listUserid = logic_A.getUserIds();
			Hashtable t = (Hashtable) listUserid.get(0);
			String userIdDaerah = t.get("idDaerah").toString();
			String userIdNeg = t.get("idNegeri").toString();
			String userIdKodDaerah = t.get("kodDaerah").toString();
			String userIdKodNegeri = t.get("kodnegeri").toString();
			String userIdPejabat = t.get("idpejabat").toString();
			String negeribandar = getParam("socNegeri");
			String bandar_bandar = getParam("socBandar");

			String ids = getParam("idSimati");
			int no_sj = 0;
			if (!"".equals(getParam("no_subjaket"))) {
				no_sj = Integer.parseInt(getParam("no_subjaket"));
			}

			/*
			view1 = logic_A.getJenisKp();
			this.context.put("listkp", view1);*/
			this.context.put("tarikhmohon", currentDate);
			int evenstatus = 0;
			int idflag = 2;
			int flagNo = 1;
			this.context.put("idFlag", idflag);
			this.context.put("flagno", flagNo);
			this.context.put("EventStatus", evenstatus);
			String tempid = getParam("idPermohonan");
			this.context.put("IdPermohonan", tempid);
			Hashtable k = new Hashtable();
			v = new Vector();
			k.put("idFail", getParam("idFail"));
			k.put("idPemohon", getParam("idPemohon"));
			k.put("idPemohonan", getParam("idpermohonan"));
			k.put("umursimati", getParam("txtUmurSimati"));
			k.put("jantinasimati", getParam("socJantinaSimati"));
			k.put("umurpemohon", getParam("txtUmurPemohon"));
			k.put("jantinapemohon", getParam("socJantinaPemohon"));
			k.put("noFail", getParam("txtNoFail"));
			k.put("tarikhMohon", getParam("txdTarikhMohon"));
			k.put("noKpBaru1", getParam("txtNoKPBaruSimati1"));
			k.put("noKpBaru2", getParam("txtNoKPBaruSimati2"));
			k.put("noKpBaru3", getParam("txtNoKPBaruSimati3"));
			k.put("noKpLama", getParam("txtNoKPLamaSimati"));
			k.put("jenisKp", getParam("socJenisKPLainSimati"));
			k.put("noKpLain", getParam("txtNoKPLainSimati"));
			k.put("idSimati", getParam("idSimati"));
			k.put("namaSimati", getParam("txtNamaSimati"));
			k.put("tarikhMati", getParam("txtTarikhMati"));
			k.put("noKpBaruPemohon1", getParam("txtNoKPBaruPemohon1"));
			k.put("noKpBaruPemohon2", getParam("txtNoKPBaruPemohon2"));
			k.put("noKpBaruPemohon3", getParam("txtNoKPBaruPemohon3"));
			k.put("noKpLamaPemohon", getParam("txtNoKPLamaPemohon"));
			k.put("noKpLainPemohon", getParam("txtNoKPLainPemohon"));
			k.put("jenisKpPemohon", getParam("socJenisKPLainPemohon"));
			k.put("namaPemohon", getParam("txtNamaPemohon"));
			k.put("alamat1", getParam("txtAlamat1"));
			k.put("alamat2", getParam("txtAlamat2"));
			k.put("alamat3", getParam("txtAlamat3"));
			k.put("poskod", getParam("txtPoskod"));
			k.put("bandarsurat", getParam("txtBandar"));
			// k.put("idDaerah",getParam("socDaerahinput"));
			k.put("umursimati", getParam("txtUmurSimati"));
			k.put("jantinasimati", getParam("socJantinaSimati"));
			k.put("umurpemohon", getParam("txtUmurPemohon"));
			k.put("jantinapemohon", getParam("socJantinaPemohon"));
			k.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));
			k.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
			k.put("id_Permohonansimati", getParam("id_Permohonansimati"));
			k.put("no_subjaket", getParam("no_subjaket"));
			k.put("idDaerah", getParam("socDaerah"));
			k.put("taraf_penting", getParam("taraf_penting"));
			k.put("no_tel", getParam("no_tel"));
			k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
			k.put("no_hp", getParam("no_hp"));
			k.put("jenis_pej", getParam("jenis_pej"));
			k.put("jenis_pemohon", getParam("jenis_pemohon"));
			k.put("socSaudaraWaris", getParam("socSaudaraWaris"));
			v.addElement(k);
			// this.context.put("ViewLama",v);

			if (getParam("dah_daftar_ke").equals("belum")) {
				this.context.put("ViewLama", v);
				this.context.put("ViewBaru", "");
				this.context.put("dah_daftar_ke", "belum");
			} else {
				this.context.put("ViewLama", "");
				this.context.put("ViewBaru", v);

				logic_A.setDataLama(ids, (no_sj - 1));
				Vector list4 = logic_A.getDataLama();
				this.context.put("ViewBaruNoFailLama", list4);

				String idt = getParam("idpermohonan");
				logic_A.setDataFail(idt);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);
				this.context.put("dah_daftar_ke", "sudah");

			}

			String id = tempid;
			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);

			if (getParam("socNegeri") == "") {
				this.context.put("negeri", "");
				this.context.put("daerah", "");
			} else {
				this.context.put("negeri", getParam("socNegeri"));
				Vector s = logic_A.getListBandarByNegeri(Integer
						.parseInt(getParam("socNegeri")));
				this.context.put("listBandarbyNegeri", s);

				if (getParam("socBandar") != "0" && getParam("socBandar") != "") {
					this.context.put("daerah", getParam("socBandar"));
				} else {
					this.context.put("daerah", "");
				}
			}

			listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);

			this.context.put("id_Permohonan_terdahulu",
					getParam("id_Permohonan_terdahulu"));
			this.context.put("id_Permohonan_terdahulu",
					getParam("idtempTerdahulu"));
			this.context.put("txtNomborResitH", getParam("txtNomborResitH"));
			this.context.put("txdTarikhByrnH", getParam("txdTarikhByrnH"));
			this.context.put("IdSimati", getParam("idSimati"));

			Vector listSenaraiPemohonSimati = null;
			logic_A.SenaraiPemohonSimati(ids);
			listSenaraiPemohonSimati = logic_A.getSenaraiPemohonSimati();
			this.context.put("listSenaraiPemohonSimati",
					listSenaraiPemohonSimati);

			this.context.put("show_pemohonsimati", "yes");

			if (getParam("radioP").equals("B")) {
				this.context.put("pemohonbaru", "yes");
				this.context.put("pemohonlama", "");
				this.context.put("kosongkan_pemohon", "yes");

			}
			if (getParam("radioP").equals("L")) {
				this.context.put("pemohonlama", "yes");
				this.context.put("pemohonbaru", "");

				if (getParam("pemohonSimati") != "") {
					Vector listPemohonD17 = null;
					logic_internal.setDataPemohonD17(getParam("pemohonSimati"));
					listPemohonD17 = logic_internal.getDataPemohonD17();
					this.context.put("listPemohonD17", listPemohonD17);

					Hashtable h = (Hashtable) listPemohonD17.get(0);
					if (h.get("idnegeri").toString() != ""
							&& h.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(h.get("idnegeri").toString()));
						this.context.put("listBandarbyNegeri", s3);
					} else {
						this.context.put("listBandarbyNegeri", "");
					}
				} else {
					this.context.put("kosongkan_pemohon", "yes");
					this.context.put("pemohonSimati", "");
				}
				this.context.put("pemohonSimati", getParam("pemohonSimati"));

			}

			String id_b = getParam("idPermohonan");
			if (id_b != "") {
				String pb = id_b;
				logic_internal.setDataPemohonOB(pb);
				listPemohonOB = logic_internal.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
			}

			vm = "app/ppk/frmPrmhnnSek8DaftarSek17.jsp";
		} else if ("Kemaskini_daftar_pemohon".equals(submit)) {

			this.context.put("tarikhmohondaftar", "");
			// this.context.put("pendaftaran", "yes");
			/*
			view1 = logic_A.getJenisKp();
			this.context.put("listkp", view1);*/
			this.context.put("tarikhmohon", currentDate);
			String idSimati = getParam("idSimati");

			int evenstatus = 0;
			int idflag = 2;
			int flagNo = 1;
			this.context.put("idFlag", idflag);
			this.context.put("flagno", flagNo);
			this.context.put("EventStatus", evenstatus);

			String tempid = getParam("idPermohonan");
			this.context.put("IdPermohonan", tempid);

			String id = getParam("idPermohonan");
			list2 = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ViewBaru", list2);
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);

			Hashtable h = (Hashtable) list2.get(0);
			if (h.get("pmidnegeri").toString() == "") {
				this.context.put("negeri", "");
				this.context.put("daerah", "");
			} else {
				this.context.put("negeri", h.get("pmidnegeri").toString());
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h
						.get("pmidnegeri").toString()));
				this.context.put("listBandarbyNegeri", s3);
				this.context.put("daerah", "");
			}
			if (h.get("idbandar").toString() == "") {
				this.context.put("daerah", "");
			} else {
				this.context.put("daerah", h.get("idbandar").toString());
			}

			String sj = h.get("no_subjaket").toString();

			// Azam change
			// int no_sj = Integer.parseInt(sj);

			int no_sj = 0;
			if (!"".equals(sj)) {
				no_sj = Integer.parseInt(sj);
			}

			logic_A.setDataLama(idSimati, (no_sj - 1));
			Vector list4 = logic_A.getDataLama();
			this.context.put("ViewBaruNoFailLama", list4);

			listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);

			view_get_id(session);
			listIds = logic_A.getIds();

			Hashtable k = (Hashtable) listIds.get(0);
			this.context.put("IdSimati", Long.parseLong(k.get("idsimati")
					.toString()));
			this.context.put("IdPemohon", Long.parseLong(k.get("idpemohon")
					.toString()));
			this.context.put("IdPermohonan", getParam("idPermohonan"));

			/*
			 * int idp = Integer.parseInt(getParam("id_Permohonan_terdahulu"));
			 * logic_A.setDataFail(idp); listFail = logic_A.getDataFail();
			 * this.context.put("ViewFail", listFail);
			 */

			String id_b = getParam("idPermohonan");
			if (id_b != "") {
				String pb = id_b;
				logic_internal.setDataPemohonOB(pb);
				listPemohonOB = logic_internal.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
			}
			this.context.put("dah_daftar_ke", "sudah");
			vm = "app/ppk/frmPrmhnnSek8DaftarSek17.jsp";

		}

		else if ("Simpanx".equals(submit)) {
			myLogger.info("Simpanx");
			check_mode_permohonan(session);
			chkId = logic_A.getId();
			Hashtable b = (Hashtable) chkId.get(0);
			int cntID = Integer.parseInt(b.get("count_id").toString());
			System.out.println("cntID::" + cntID);
			// view_get_userid(session);
			String useridx = (String) session.getAttribute("_ekptg_user_id");
			// String useridx = "14";
			logic_A.setGetUserId(useridx);
			listUserid = logic_A.getUserIds();
			Hashtable t = (Hashtable) listUserid.get(0);
			String userIdDaerah = t.get("idDaerah").toString();
			String userIdNeg = t.get("idNegeri").toString();
			String userIdKodDaerah = t.get("kodDaerah").toString();
			// System.out.println("USER KOD DAERAH VIEW!!!::" +
			// userIdKodDaerah);

			this.context.put("txtNomborResitH", getParam("txtNomborResitH"));
			this.context.put("txdTarikhByrnH", getParam("txdTarikhByrnH"));
			this.context.put("bkp", getParam("bkp"));
			this.context.put("lp", getParam("lp"));
			this.context.put("bpa", getParam("bpa"));
			this.context.put("lpa", getParam("lpa"));
			this.context.put("ht", getParam("ht"));
			// ::
			this.context.put("lt", getParam("lt"));

			String userIdKodNegeri = t.get("kodnegeri").toString();
			String userIdPejabat = t.get("idpejabat").toString();

			String idp_dulu = getParam("id_Permohonan_terdahulu");

			Vector v_flag = new Vector();
			v_flag = logic_A.setFlag(idp_dulu);

			if (cntID == 0) {

				if (v_flag.size() > 0) {

					if (bolehsimpan.equals("yes")) {
						addPermohonan(session, userIdNeg, userIdPejabat,
								userIdKodDaerah, userIdKodNegeri);
					}
					this.context.put("tarikhmohondaftar", "");

				} else {
					String IdPermohonan = getParam("idPermohonan");
					String Idppp = getParam("idpermohonan");
					check_mode_permohonan(session);
					chkId = logic_A.getId();
					Hashtable b1 = (Hashtable) chkId.get(0);
					cntID = Integer.parseInt(b1.get("count_id").toString());
					useridx = (String) session.getAttribute("_ekptg_user_id");
					logic_A.setGetUserId(useridx);
					listUserid = logic_A.getUserIds();
					Hashtable t1 = (Hashtable) listUserid.get(0);
					userIdDaerah = t1.get("idDaerah").toString();
					userIdNeg = t1.get("idNegeri").toString();
					userIdKodDaerah = t1.get("kodDaerah").toString();
					userIdKodNegeri = t1.get("kodnegeri").toString();
					userIdPejabat = t1.get("idpejabat").toString();
					String negeribandar = getParam("socNegeri");
					String bandar_bandar = getParam("socBandar");

					String ids = getParam("idSimati");
					// azam chage
					// int no_sj = Integer.parseInt(getParam("no_subjaket"));
					int no_sj = 0;
					if (!"".equals(getParam("no_subjaket"))) {
						no_sj = Integer.parseInt(getParam("no_subjaket"));
					}
					/*
					view1 = logic_A.getJenisKp();
					this.context.put("listkp", view1);*/
					this.context.put("tarikhmohon", currentDate);
					int evenstatus = 0;
					int idflag = 2;
					int flagNo = 1;
					this.context.put("idFlag", idflag);
					this.context.put("flagno", flagNo);
					this.context.put("EventStatus", evenstatus);
					String tempid = getParam("idPermohonan");
					this.context.put("IdPermohonan", tempid);
					Hashtable k = new Hashtable();
					v = new Vector();
					k.put("idFail", getParam("idFail"));
					k.put("idPemohon", getParam("idPemohon"));
					k.put("idPemohonan", getParam("idpermohonan"));
					k.put("umursimati", getParam("txtUmurSimati"));
					k.put("jantinasimati", getParam("socJantinaSimati"));
					k.put("umurpemohon", getParam("txtUmurPemohon"));
					k.put("jantinapemohon", getParam("socJantinaPemohon"));

					if (getParam("dah_daftar_ke").equals("belum")) {
						k.put("noFail", getParam("txtNoFail"));
					}
					if (getParam("dah_daftar_ke").equals("sudah")) {
						k.put("noFail", getParam("txtNoFailBaru"));
					}

					k.put("tarikhMohon", getParam("txdTarikhMohon"));
					k.put("noKpBaru1", getParam("txtNoKPBaruSimati1"));
					k.put("noKpBaru2", getParam("txtNoKPBaruSimati2"));
					k.put("noKpBaru3", getParam("txtNoKPBaruSimati3"));
					k.put("noKpLama", getParam("txtNoKPLamaSimati"));
					k.put("jenisKp", getParam("socJenisKPLainSimati"));
					k.put("noKpLain", getParam("txtNoKPLainSimati"));
					k.put("idSimati", getParam("idSimati"));
					k.put("namaSimati", getParam("txtNamaSimati"));
					k.put("tarikhMati", getParam("txtTarikhMati"));
					k.put("noKpBaruPemohon1", getParam("txtNoKPBaruPemohon1"));
					k.put("noKpBaruPemohon2", getParam("txtNoKPBaruPemohon2"));
					k.put("noKpBaruPemohon3", getParam("txtNoKPBaruPemohon3"));
					k.put("noKpLamaPemohon", getParam("txtNoKPLamaPemohon"));
					k.put("noKpLainPemohon", getParam("txtNoKPLainPemohon"));
					k.put("jenisKpPemohon", getParam("socJenisKPLainPemohon"));
					k.put("namaPemohon", getParam("txtNamaPemohon"));
					k.put("alamat1", getParam("txtAlamat1"));
					k.put("alamat2", getParam("txtAlamat2"));
					k.put("alamat3", getParam("txtAlamat3"));
					k.put("poskod", getParam("txtPoskod"));
					k.put("bandarsurat", getParam("txtBandar"));
					// k.put("idDaerah",getParam("socDaerahinput"));
					k.put("umursimati", getParam("txtUmurSimati"));
					k.put("jantinasimati", getParam("socJantinaSimati"));
					k.put("umurpemohon", getParam("txtUmurPemohon"));
					k.put("jantinapemohon", getParam("socJantinaPemohon"));
					k.put("id_Suburusanstatusfail",
							getParam("id_Suburusanstatusfail"));
					k.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
					k.put("id_Permohonansimati",
							getParam("id_Permohonansimati"));
					k.put("no_subjaket", getParam("no_subjaket"));
					k.put("idDaerah", getParam("socDaerah"));

					k.put("taraf_penting", getParam("taraf_penting"));
					k.put("no_tel", getParam("no_tel"));
					k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
					k.put("no_hp", getParam("no_hp"));
					k.put("jenis_pej", getParam("jenis_pej"));
					k.put("jenis_pemohon", getParam("jenis_pemohon"));
					k.put("socSaudaraWaris", getParam("socSaudaraWaris"));

					v.addElement(k);
					// this.context.put("ViewLama",v);

					if (getParam("dah_daftar_ke").equals("belum")) {
						this.context.put("ViewLama", v);
						this.context.put("ViewBaru", "");
						this.context.put("dah_daftar_ke", "belum");

						this.context.put("show_pemohonsimati", "yes");

						if (getParam("radioP").equals("B")) {
							this.context.put("pemohonbaru", "yes");
							this.context.put("pemohonlama", "");
						}
						if (getParam("radioP").equals("L")) {
							this.context.put("pemohonlama", "yes");
							this.context.put("pemohonbaru", "");
							this.context.put("pemohonSimati",
									getParam("pemohonSimati"));

							if (getParam("pemohonSimati") != "") {
								Vector listPemohonD17 = null;
								logic_internal
										.setDataPemohonD17(getParam("pemohonSimati"));
								listPemohonD17 = logic_internal
										.getDataPemohonD17();

							} else {
								this.context.put("kosongkan_pemohon", "yes");
								this.context.put("pemohonSimati", "");
							}

							this.context.put("pemohonSimati",
									getParam("pemohonSimati"));

							Vector listSenaraiPemohonSimati = null;
							logic_A.SenaraiPemohonSimati(ids);
							listSenaraiPemohonSimati = logic_A
									.getSenaraiPemohonSimati();
							this.context.put("listSenaraiPemohonSimati",
									listSenaraiPemohonSimati);

						}

					} else {
						this.context.put("ViewLama", "");
						this.context.put("ViewBaru", v);

						logic_A.setDataLama(ids, (no_sj - 1));
						Vector list4 = logic_A.getDataLama();
						this.context.put("ViewBaruNoFailLama", list4);

						String idt = getParam("idpermohonan");
						logic_A.setDataFail(idt);
						listFail = logic_A.getDataFail();
						this.context.put("ViewFail", listFail);
						this.context.put("dah_daftar_ke", "sudah");

					}

					String id = tempid;
					String IdNeg = getParam("negid");
					this.context.put("NegId", IdNeg);
					if (getParam("socNegeri") == "") {
						this.context.put("negeri", "");
						this.context.put("daerah", "");
					} else {
						this.context.put("negeri", getParam("socNegeri"));
						Vector s = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeri")));
						this.context.put("listBandarbyNegeri", s);
						// this.context.put("daerah", "");
					}

					if (getParam("socBandar") != "") {
						this.context.put("daerah", getParam("socBandar"));

					} else {
						this.context.put("daerah", "");
					}

					listDaerahByUser = logic_A
							.getDaerahByNegeriUser((String) session
									.getAttribute("_ekptg_user_id"));
					this.context.put("ListDaerahByUser", listDaerahByUser);

					this.context.put("id_Permohonan_terdahulu",
							getParam("id_Permohonan_terdahulu"));
					this.context.put("id_Permohonan_terdahulu",
							getParam("idtempTerdahulu"));
					this.context.put("txtNomborResitH",
							getParam("txtNomborResitH"));
					this.context.put("txdTarikhByrnH",
							getParam("txdTarikhByrnH"));
					this.context.put("IdSimati", getParam("idSimati"));

					this.context.put("msg_permohonansebelum", "yes");

					vm = "app/ppk/frmPrmhnnSek8DaftarSek17.jsp";
				}

			} else {
				if (bolehsimpan.equals("yes")) {
					updatePermohonan(session);
				}
				this.context.put("tarikhmohondaftar", "");

				//

				//

			}

			// System.out.println("V_F"+v_flag);

			// if (v_flag.size() > 0)
			{
				/*
				view1 = logic_A.getJenisKp();
				this.context.put("listkp", view1);
	*/
				idFlag = 2;
				flag_no = 2;
				eventStatus = 1;
				this.context.put("tarikhmohon", getParam("tarikh_daftar"));
				this.context.put("flagno", flag_no);
				this.context.put("idFlag", idFlag);
				this.context.put("EventStatus", eventStatus);

				String IdNeg = getParam("negid");
				this.context.put("NegId", IdNeg);

				// view_mode_pemohon(session);
				String id = getParam("idPermohonan");
				String idSimati = getParam("idSimati");

				Vector list3 = logic_A.setData(id, (String) session
						.getAttribute("_ekptg_user_id"));
				// hashtable maklumat header
				headerppk_baru(session, id, "Y", "", "T");

				this.context.put("ViewBaru", list3);
				this.context.put("ViewLama", "");

				Hashtable h = (Hashtable) list3.get(0);
				String sj = "";

				if (h.get("pmidnegeri").toString() == "") {
					this.context.put("negeri", "");
					this.context.put("daerah", "");
				} else {
					this.context.put("negeri", h.get("pmidnegeri").toString());
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("pmidnegeri").toString()));
					this.context.put("listBandarbyNegeri", s3);
					this.context.put("daerah", "");
				}
				if (h.get("idbandar").toString() == "") {
					this.context.put("daerah", "");
				} else {
					this.context.put("daerah", h.get("idbandar").toString());
				}

				sj = h.get("no_subjaket").toString();

				// Azam change
				// int no_sj = Integer.parseInt(sj);

				int no_sj = 0;
				if (!"".equals(sj)) {
					no_sj = Integer.parseInt(sj);
				}

				logic_A.setDataLama(idSimati, (no_sj - 1));
				Vector list4 = logic_A.getDataLama();
				this.context.put("ViewBaruNoFailLama", list4);

				logic_A.setGetId(id);
				listIds = logic_A.getIds();
				Hashtable k = (Hashtable) listIds.get(0);
				this.context.put("IdSimati", Long.parseLong(k.get("idsimati")
						.toString()));
				this.context.put("IdPemohon", Long.parseLong(k.get("idpemohon")
						.toString()));

				this.context.put("IdPermohonan", getParam("idPermohonan"));

				listDaerahByUser = logic_A
						.getDaerahByNegeriUser((String) session
								.getAttribute("_ekptg_user_id"));
				this.context.put("ListDaerahByUser", listDaerahByUser);
				// this.context.put("pendaftaran", "yes");

				String idp = getParam("id_Permohonan_terdahulu");
				logic_A.setDataFail(idp);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);

				this.context.put("dah_daftar_ke", "sudah");
				this.context.put("id_Permohonan_terdahulu", idp);

				String id_b = getParam("idPermohonan");
				if (id_b != "") {
					String pb = id_b;
					logic_internal.setDataPemohonOB(pb);
					listPemohonOB = logic_internal.getDataPemohonOB();
					this.context.put("listPemohonOB", listPemohonOB);
				}

				vm = "app/ppk/frmPrmhnnSek8DaftarSek17.jsp";

			}

		} else if ("kembali_daftar_pemohon".equals(submit)) {

			String idPermohonand = getParam("id_Permohonan_terdahulu");
			String idPermohonan = getParam("idPermohonan");

			// FrmPrmhnnSek8SenaraiSemakInternalData
			// frmPrmhnnSek8SenaraiSemakData = new
			// FrmPrmhnnSek8SenaraiSemakInternalData();
			eventStatus = 1;
			this.context.put("EventStatus", eventStatus);
			simpanStatus = 2;
			int getnewpemohon = 1;
			String idNeg = getParam("negid");
			this.context.put("NegId", idNeg);
			this.context.put("GetNewPemohon", getnewpemohon);
			this.context.put("SimpanStatus", simpanStatus);
			logic_C.setDataSemakan(idPermohonan);
			this.context.put("IdPermohonan", idPermohonan);

			listSemak = logic_C.getListSemakan();
			this.context.put("ListSemakan", listSemak);

			listb = logic_A.setDataBaru(idPermohonan, (String) session
					.getAttribute("_ekptg_user_id"));
			// System.out.println("LISTB ::" + listb);
			this.context.put("listb", listb);

			list2 = logic_A.setData(idPermohonand, (String) session
					.getAttribute("_ekptg_user_id"));
			// System.out.println("LISTL ::" + list2);
			// hashtable maklumat header
			headerppk_baru(session, idPermohonan, "Y", "", "T");

			this.context.put("ViewLamaSub", list2);
			this.context.put("idpermohonan", idPermohonand);

			this.context.put("dah_daftar_ke", getParam("dah_daftar_ke"));
			this.context.put("daftar", "yes");

			listsemakhta = logic_internal.setTujuanSemak_hta(idPermohonand);
			this.context.put("listsemakhta", listsemakhta);
			listsemakha = logic_internal.setTujuanSemak_ha(idPermohonand);
			this.context.put("listsemakha", listsemakha);

			String idSimati = getParam("idSimati");
			String id_permohonan_baru = getParam("idpermohonan_baru");
			String id_permohonan_lama = getParam("idpermohonan");
			String id_permohonan = "";
			String flag_daftar = "";
			if (id_permohonan_baru.equals("")) {
				id_permohonan = id_permohonan_lama;
				flag_daftar = "LAMA";
			} else {
				id_permohonan = id_permohonan_baru;
				flag_daftar = "BARU";
			}

			this.context.put("list_semakhta_check", FrmSemakHartaSek17
					.list_semakhta(idSimati, id_permohonan, flag_daftar));
			this.context.put("list_semakha_check", FrmSemakHartaSek17
					.list_semakha(idSimati, id_permohonan, flag_daftar));

			vm = "app/ppk/frmPraPrmhnnSek17SenaraiSemak.jsp";
		} else if ("seterusnya".equals(submit)) {
			myLogger.info("Seterusnya");
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			String idPermohonand = getParam("id_Permohonan_terdahulu");
			String id = getParam("idPermohonan");
			String id2 = getParam("idPemohon");
			String id1 = getParam("idSimati");
			int s1 = Integer.parseInt(getParam("no_subjaket"));

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			this.context.put("id", id);
			this.context.put("id2", id2);
			this.context.put("id1", id1);

			list = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("View", list);
			headerppk_baru(session, id, "Y", "", "T");

			logic_internal.setDataSimati(id);
			listSimati = logic_internal.getDataSimati();
			this.context.put("listSimati", listSimati);

			Hashtable h1 = (Hashtable) listSimati.get(0);
			if (h1.get("idnegeri").toString() != ""
					&& h1.get("idnegeri").toString() != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h1
						.get("idnegeri").toString()));
				this.context.put("listBandarTetapbyNegeri", s3);
			} else {
				this.context.put("listBandarTetapbyNegeri", "");
			}

			this.context.put("readmode", disability1);
			this.context.put("show_kemaskini_button", "yes");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");
			context.put("DATEUTIL", new DateUtil());
			selectedTabatas = "0";
			selectedTabtengah = "0";
			selectedTabbawah = "0";
			selectedTabtepi = "0";
			this.context.put("selectedTabatas", selectedTabatas);
			this.context.put("selectedTabtengah", selectedTabtengah);
			this.context.put("selectedTabbawah", selectedTabbawah);
			this.context.put("selectedTabtepi", selectedTabtepi);

			logic_A.setlistGetPermohonanSebelum(id1, (s1 - 1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			vm = "app/ppk/frmPraPrmhnnSek17Simati.jsp";
		} else if ("kembali_simati".equals(submit)) {
			idFlag = 2;
			flag_no = 2;
			eventStatus = 1;

			String id_sub = getParam("id_Suburusanstatusfail");
			String id_Fail = getParam("id_Fail");
			String id = getParam("idPermohonan");
			if (getParam("t_status").equals("yes")) {

				// logic_A.daftarstatus(id, (String)
				// session.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
				// :::SUB
				// ID_STATUS 170
				// ID_SUBURUSAN 553

				// logic_A.kemaskiniSubUrusanStatusFail(id,(String)
				// session.getAttribute("_ekptg_user_id"),"170","553",id_Fail);

			}

			this.context.put("tarikhmohon", getParam("tarikh_daftar"));

			// Azam change here
			// int no_sj = Integer.parseInt(getParam("no_subjaket"));
			int no_sj = 0;
			if (!"".equals(getParam("no_subjaket"))) {
				no_sj = Integer.parseInt(getParam("no_subjaket"));
			}

			this.context.put("flagno", flag_no);
			this.context.put("idFlag", idFlag);
			this.context.put("EventStatus", eventStatus);
			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);
			// int id = Integer.parseInt(getParam("idPermohonan"));
			String idt = getParam("id_Permohonan_terdahulu");
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			this.context.put("id_Permohonan_terdahulu", idt);

			String idSimatim = getParam("idSimati");
			// FrmPrmhnnSek8DaftarSek8InternalData.setData(id);
			Vector list3 = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");

			this.context.put("ViewBaru", list3);
			this.context.put("ViewLama", "");
			Hashtable h = (Hashtable) list3.get(0);
			String sj = "";

			if (h.get("pmidnegeri").toString() == "") {
				this.context.put("negeri", "");
				this.context.put("daerah", "");
			} else {
				this.context.put("negeri", h.get("pmidnegeri").toString());
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h
						.get("pmidnegeri").toString()));
				this.context.put("listBandarbyNegeri", s3);
				this.context.put("daerah", "");
			}
			if (h.get("idbandar").toString() == "") {
				this.context.put("daerah", "");
			} else {
				this.context.put("daerah", h.get("idbandar").toString());
			}

			sj = h.get("no_subjaket").toString();

			// Azam change
			// int no_sjj = Integer.parseInt(sj);
			int no_sjj = 0;
			if (!"".equals(sj)) {
				no_sjj = Integer.parseInt(sj);
			}

			logic_A.setDataLama(idSimatim, (no_sjj - 1));
			Vector list9 = logic_A.getDataLama();
			this.context.put("ViewBaruNoFailLama", list9);
			logic_A.setGetId(id);
			listIds = logic_A.getIds();
			Hashtable k = (Hashtable) listIds.get(0);
			this.context.put("IdSimati", Long.parseLong(k.get("idsimati")
					.toString()));
			this.context.put("IdPemohon", Long.parseLong(k.get("idpemohon")
					.toString()));
			this.context.put("IdPermohonan", getParam("idPermohonan"));
			listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);
			// this.context.put("pendaftaran", "yes");

			this.context.put("dah_daftar_ke", getParam("dah_daftar_ke"));
			this.context.put("daftar", "yes");

			String id_b = getParam("idpermohonan_baru");
			if (id_b != "") {
				String pb = id_b;
				logic_internal.setDataPemohonOB(pb);
				listPemohonOB = logic_internal.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
			}

			vm = "app/ppk/frmPrmhnnSek8DaftarSek17.jsp";
		}

		// paste suma coding tab

		else if ("Pemohon".equals(submit)) {
			String check_copy = getParam("copy");
			this.context.put("check_copy", check_copy);
			// System.out.println("COPY CHECK :" + check_copy);
			this.context.put("readmode", "");
			this.context.put("show_kemaskini_button", "");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");
			if ("Pemohonview".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				logic_internal.setDataPemohon(id);
				listPemohon = logic_internal.getDataPemohon();
				this.context.put("listPemohon", listPemohon);
				Hashtable h = (Hashtable) listPemohon.get(0);
				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				Hashtable k = (Hashtable) listPemohon.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s4 = logic_A.getListBandarByNegeri(Integer
							.parseInt(k.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s4);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}
				logic_internal.setDataPemohonOB(id);
				listPemohonOB = logic_internal.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
				logic_internal.setCheckPeguam17(id);
				listCheckPeguam = logic_internal.getCheckPeguam17();
				this.context.put("listCheckPeguam", listCheckPeguam);
				if (listCheckPeguam.size() != 0) {
					this.context.put("show_peguam_tab", "yes");
				} else {
					this.context.put("show_peguam_tab", "");
				}
				this.context
						.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
				this.context.put("readmode", "disabled");
				this.context.put("show_kemaskini_button", "yes");
			} else if ("kemaskini_pemohon".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				logic_internal.setCheckPeguam17(id);
				listCheckPeguam = logic_internal.getCheckPeguam17();
				this.context.put("listCheckPeguam", listCheckPeguam);
				if (listCheckPeguam.size() != 0) {
					this.context.put("show_peguam_tab", "yes");
				} else {
					this.context.put("show_peguam_tab", "");
				}
				logic_internal.setDataPemohon(id);
				listPemohon = logic_internal.getDataPemohon();
				this.context.put("listPemohon", listPemohon);
				Hashtable h = (Hashtable) listPemohon.get(0);
				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				Hashtable k = (Hashtable) listPemohon.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s4 = logic_A.getListBandarByNegeri(Integer
							.parseInt(k.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s4);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}
				this.context.put("show_simpan_button", "yes");
				this.context.put("show_batal_button", "yes");
			} else if ("batal_pemohon".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				logic_internal.setDataPemohonOB(id);
				listPemohonOB = logic_internal.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
				logic_internal.setDataSimati(id);
				listSimati = logic_internal.getDataSimati();
				this.context.put("listSimati", listSimati);
				logic_internal.setDataPemohon(id);
				listPemohon = logic_internal.getDataPemohon();
				Hashtable h = (Hashtable) listPemohon.get(0);
				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				Hashtable k = (Hashtable) listPemohon.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s4 = logic_A.getListBandarByNegeri(Integer
							.parseInt(k.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s4);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}
				this.context.put("listPemohon", listPemohon);
				this.context
						.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
				logic_internal.setCheckPeguam17(id);
				listCheckPeguam = logic_internal.getCheckPeguam17();
				this.context.put("listCheckPeguam", listCheckPeguam);
				if (listCheckPeguam.size() != 0) {
					this.context.put("show_peguam_tab", "yes");
				} else {
					this.context.put("show_peguam_tab", "");
				}
				this.context.put("show_simpan_button", "yes");
			} else if ("getSaudara".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String bandarevent = getParam("bandar_event");
				logic_internal.setDataPemohonOB(id);
				listPemohonOB = logic_internal.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
				String idsaudara = getParam("socTarafKePemohonanPemohon");
				// System.out.println("SAUUUU" + idsaudara);
				String id_bandartetap = "";
				String id_bandarsurat = "";
				String id_saudare = "";
				String dapatbandar = getParam("txtBandarPemohon");
				String dapatbandarsurat = getParam("txtBandarPemohonSurat");

				if (getParam("socNegeriPemohon") != ""
						&& getParam("socNegeriPemohon") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPemohon")));
					this.context.put("listBandarTetapbyNegeri", s3);
					id_bandartetap = "";
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
					id_bandartetap = "";
				}

				if (getParam("socNegeriPemohonSurat") != ""
						&& getParam("socNegeriPemohonSurat") != "0") {
					Vector s4 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPemohonSurat")));
					this.context.put("listBandarSuratbyNegeri", s4);
					id_bandarsurat = "";
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
					id_bandarsurat = "";
				}

				// System.out.println("bandar event :" +
				// getParam("bandar_event"));

				if (bandarevent.equals("no")) {
					id_saudare = "";
					id_bandartetap = dapatbandar;
					id_bandarsurat = dapatbandarsurat;
					// System.out.println("NO");
				}
				if (bandarevent.equals("yes")) {
					id_bandartetap = "";
					id_bandarsurat = dapatbandarsurat;
					id_saudare = getParam("socTalianPersaudaraanPemohon");
					// System.out.println("YES");
				}
				if (bandarevent.equals("yesno")) {
					id_bandarsurat = "";
					id_bandartetap = dapatbandar;
					id_saudare = getParam("socTalianPersaudaraanPemohon");
					// System.out.println("YES");
				}
				if (bandarevent.equals("copy")) {
					id_bandarsurat = dapatbandar;
					id_bandartetap = dapatbandar;
					id_saudare = getParam("socTalianPersaudaraanPemohon");
					// System.out.println("YES");
					Vector s5 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPemohon")));
					this.context.put("listBandarSuratbyNegeri", s5);
				}
				if (bandarevent.equals("copycancel")) {

					id_bandartetap = dapatbandar;
					id_saudare = getParam("socTalianPersaudaraanPemohon");
					// System.out.println("YES");
					this.context.put("listBandarSuratbyNegeri", "");

				}

				// copycancel

				Hashtable k = new Hashtable();
				v = new Vector();
				String nkpbarupemohon = getParam("txtnoKpBaru1Pemohon")
						+ getParam("txtnoKpBaru2Pemohon")
						+ getParam("txtnoKpBaru3Pemohon");
				k.put("idPermohonan", getParam("idPermohonan"));
				k.put("idPemohon", getParam("idPemohon"));
				k.put("noKpBaru", nkpbarupemohon);
				k.put("noKpBaru1", getParam("txtnoKpBaru1Pemohon"));
				k.put("noKpBaru2", getParam("txtnoKpBaru2Pemohon"));
				k.put("noKpBaru3", getParam("txtnoKpBaru3Pemohon"));
				k.put("jenisKp", getParam("socJenisKPLainPemohon"));
				k.put("noKpLain", getParam("txtNoKPLainPemohon"));
				k.put("namaPemohon", getParam("txtNamaPemohonPemohon"));
				k.put("idTarafkptg", getParam("socTarafKePemohonanPemohon"));
				k.put("jantina", getParam("socJantinaPemohon"));
				k.put("noKpLama", getParam("txtNoKPLamaPemohon"));
				k.put("status_pemohon", getParam("status_pemohon"));

				k.put("idsaudara", id_saudare);

				k.put("jenisWarga", getParam("socWarganegaraPemohon"));
				k.put("nama_warga", getParam("nama_warga"));
				k.put("jenisAgama", getParam("socAgamaPemohon"));
				if (getParam("txtUmurPemohon") != "") {
					k.put("umur", getParam("txtUmurPemohon"));
				}
				if (getParam("txtUmurPemohon") == "") {
					k.put("umur", "");
				}
				k.put("noFax", getParam("txtNoFaksPemohon"));
				k.put("noTel", getParam("txtNoTelefonPemohon"));
				k.put("noHp", getParam("txtNoTelefonBimbitPemohon"));
				k.put("emel", getParam("txtEmelPemohon"));

				if (bandarevent.equals("copy")) {
					k.put("alamat1", getParam("txtAlamatTerakhir1Pemohon"));
					k.put("alamat2", getParam("txtAlamatTerakhir2Pemohon"));
					k.put("alamat3", getParam("txtAlamatTerakhir3Pemohon"));
					k.put("poskod", getParam("txtPoskodPemohon"));
					k.put("bandartetap", id_bandartetap);
					k.put("idnegeri", getParam("socNegeriPemohon"));

					k
							.put("alamat1Surat",
									getParam("txtAlamatTerakhir1Pemohon"));
					k
							.put("alamat2Surat",
									getParam("txtAlamatTerakhir2Pemohon"));
					k
							.put("alamat3Surat",
									getParam("txtAlamatTerakhir3Pemohon"));
					k.put("poskodSurat", getParam("txtPoskodPemohon"));
					k.put("bandarsurat", id_bandartetap);
					k.put("idnegeriSurat", getParam("socNegeriPemohon"));
					
					k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
					k.put("nama_pelbagainegara_surat",getParam("nama_pelbagainegara"));
				} else if (bandarevent.equals("copycancel")) {
					k.put("alamat1", getParam("txtAlamatTerakhir1Pemohon"));
					k.put("alamat2", getParam("txtAlamatTerakhir2Pemohon"));
					k.put("alamat3", getParam("txtAlamatTerakhir3Pemohon"));
					k.put("poskod", getParam("txtPoskodPemohon"));
					k.put("bandartetap", id_bandartetap);
					k.put("idnegeri", getParam("socNegeriPemohon"));

					k.put("alamat1Surat", "");
					k.put("alamat2Surat", "");
					k.put("alamat3Surat", "");
					k.put("poskodSurat", "");
					k.put("bandarsurat", "");
					k.put("idnegeriSurat", "");
					
					k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
					k.put("nama_pelbagainegara_surat","");

				} else {
					k.put("alamat1", getParam("txtAlamatTerakhir1Pemohon"));
					k.put("alamat2", getParam("txtAlamatTerakhir2Pemohon"));
					k.put("alamat3", getParam("txtAlamatTerakhir3Pemohon"));
					k.put("poskod", getParam("txtPoskodPemohon"));
					k.put("bandartetap", id_bandartetap);
					k.put("idnegeri", getParam("socNegeriPemohon"));

					k.put("alamat1Surat",
							getParam("txtAlamatTerakhir1PemohonSurat"));
					k.put("alamat2Surat",
							getParam("txtAlamatTerakhir2PemohonSurat"));
					k.put("alamat3Surat",
							getParam("txtAlamatTerakhir3PemohonSurat"));
					k.put("poskodSurat", getParam("txtPoskodPemohonSurat"));
					k.put("bandarsurat", id_bandarsurat);
					k.put("idnegeriSurat", getParam("socNegeriPemohonSurat"));
					
					 k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
						k.put("nama_pelbagainegara_surat", getParam("nama_pelbagainegara_surat"));

				}

				k.put("catatan", getParam("txtCatatanPemohon"));
				k.put("adataraf", getParam("adataraf"));
				k.put("namanegeri", getParam("socNegeriPemohon"));
				v.addElement(k);

				this.context.put("listPemohon", v);
				logic_internal.setCheckPeguam17(id);
				listCheckPeguam = logic_internal.getCheckPeguam17();
				this.context.put("listCheckPeguam", listCheckPeguam);
				if (listCheckPeguam.size() != 0) {
					this.context.put("show_peguam_tab", "yes");
				} else {
					this.context.put("show_peguam_tab", "");
				}
				this.context.put("show_simpan_button", "yes");

			}

			else if ("simpan_pemohon_data".equals(mode)) {

				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				updatepemohon(session);
				logic_internal.setDataPemohon(id);
				listPemohon = logic_internal.getDataPemohon();
				this.context.put("listPemohon", listPemohon);
				Hashtable h = (Hashtable) listPemohon.get(0);
				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				Hashtable k = (Hashtable) listPemohon.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s4 = logic_A.getListBandarByNegeri(Integer
							.parseInt(k.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s4);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}
				logic_internal.setCheckPeguam17(id);
				listCheckPeguam = logic_internal.getCheckPeguam17();
				this.context.put("listCheckPeguam", listCheckPeguam);
				if (listCheckPeguam.size() != 0) {
					this.context.put("show_peguam_tab", "yes");
				} else {
					this.context.put("show_peguam_tab", "");
				}

				this.context.put("show_kemaskini_button", "yes");
				this.context.put("readmode", "disabled");

			}

			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 1);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);

			String id = getParam("idPermohonan");
			String id2 = getParam("idPemohon");
			logic_internal.setDataPemohonOB(id);
			listPemohonOB = logic_internal.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);
			// logic_A.setData(id);
			list = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			// list = logic_A.getData();
			this.context.put("View", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			String id1 = getParam("idSimati");
			int s1 = Integer.parseInt(getParam("no_subjaket"));
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			vm = "app/ppk/frmPraPrmhnnSek17Pemohon.jsp";
		} else if ("Simati".equals(submit)) {

			String id2 = getParam("idPemohon");
			String id1 = getParam("idSimati");
			int s1 = Integer.parseInt(getParam("no_subjaket"));
			readability2 = "readonly";
			disability1 = "disabled";
			this.context.put("show_kemaskini_button", "");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");
			this.context.put("readmode", "");
			String id = getParam("idPermohonan");
			logic_internal.setDataSimati(id);
			listSimati = logic_internal.getDataSimati();
			this.context.put("listSimati", listSimati);
			Hashtable h1 = (Hashtable) listSimati.get(0);
			if (h1.get("idnegeri").toString() != ""
					&& h1.get("idnegeri").toString() != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h1
						.get("idnegeri").toString()));
				this.context.put("listBandarTetapbyNegeri", s3);
			} else {
				this.context.put("listBandarTetapbyNegeri", "");
			}
			if ("Simatiview".equals(mode)) {

				this.context.put("readmode", disability1);
				this.context.put("show_kemaskini_button", "yes");
			}
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);
			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			String idm = getParam("idPermohonan");
			logic_A.setDataFail(idm);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			list = logic_A.setData(idm, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("View", list);
			headerppk_baru(session, idm, "Y", "", "T");
			vm = "app/ppk/frmPraPrmhnnSek17Simati.jsp";
		}

		else if ("Peguam".equals(submit)) {

			String id = getParam("idPermohonan");
			String id2 = getParam("idPemohon");
			logic_internal.setCheckPeguam17(id);
			listCheckPeguam = logic_internal.getCheckPeguam17();
			this.context.put("listCheckPeguam", listCheckPeguam);
			logic_internal.setDataPeguam(id2);
			listpeguamcheck = logic_internal.getDataPeguam();
			logic_internal.setDataPeguam(id2);
			listpeguam = logic_internal.getDataPeguam();
			this.context.put("listPenting", listpeguam);

			this.context.put("add_new_peguam", "");
			this.context.put("insertbaru", "");
			this.context.put("readmode", "");
			this.context.put("show_kemaskini_button", "");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");
			this.context.put("nk_tambah_penting", "");
			this.context.put("nk_update_penting", "");
			this.context.put("nk_button_penting", "");
			this.context.put("button_Kembali", "");
			this.context.put("button_tambah", "");
			this.context.put("listBandarTetapbyNegeri", "");

			if ("Peguamview".equals(mode)) {

				this.context.put("button_Kembali", "yes");
				this.context.put("button_tambah", "yes");

			}

			else if ("peguam_baru".equals(mode)) {

				this.context.put("add_new_peguam", "yes");
				this.context.put("insertbaru", "yes");

			} else if ("getBandar2".equals(mode)) {
				String key = "";
				String value = "";
				Enumeration allparam = request.getParameterNames();
				for (; allparam.hasMoreElements();) {
					key = (String) allparam.nextElement();
					value = request.getParameter(key);
					this.context.put(key, value);
				}

				if (getParam("socNegeriPeguam2") != ""
						&& getParam("socNegeriPeguam2") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPeguam2")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				this.context.put("add_new_peguam", "yes");
			}

			else if ("getBandar".equals(mode)) {

				Hashtable h = new Hashtable();
				v = new Vector();
				h.put("idPemohon", getParam("idPemohon"));
				h.put("idPeguam", getParam("idPeguam"));
				h.put("namaPeguam", "");
				h.put("noRujukanFirma", getParam("txtNoRujukanFirma"));
				h.put("alamat1", getParam("txtAlamat1Peguam"));
				h.put("alamat2", getParam("txtAlamat2Peguam"));
				h.put("alamat3", getParam("txtAlamat3Peguam"));
				h.put("bandar", "");
				h.put("poskod", getParam("txtPoskodPeguam"));
				h.put("idnegeri", getParam("socNegeriPeguam"));
				h.put("noTel", getParam("txtNomborTelefonPeguam"));
				h.put("noFax", getParam("txtNomborFaksPeguam"));
				h.put("noSyarikat", "");
				h.put("namaFirma", getParam("txtNamaFirmaPeguam"));
				h.put("emel", getParam("txtEmelPeguam"));

				v.addElement(h);
				this.context.put("listpeguamX", v);

				if (getParam("socNegeriPeguam") != ""
						&& getParam("socNegeriPeguam") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPeguam")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				this.context.put("show_simpan_button", "yes");
				this.context.put("button_tambah", "yes");
				this.context.put("add_new_peguam", "update");

			}

			else if ("kemaskini_peguam".equals(mode)) {

				String idpeguam = getParam("idPeguam");
				logic_internal.setDataPeguamX(idpeguam);
				listpeguamX = logic_internal.getDataPeguamX();
				this.context.put("listpeguamX", listpeguamX);
				Hashtable h1 = (Hashtable) listpeguamX.get(0);
				if (h1.get("idnegeri").toString() != ""
						&& h1.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h1.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				this.context.put("show_simpan_button", "yes");
				this.context.put("button_tambah", "yes");
				this.context.put("add_new_peguam", "update");

			}

			else if ("GetPeguam".equals(mode)) {
				String idpeguam = getParam("idpeguam");
				logic_internal.setDataPeguamX(idpeguam);
				listpeguamX = logic_internal.getDataPeguamX();
				this.context.put("listpeguamX", listpeguamX);
				Hashtable h1 = (Hashtable) listpeguamX.get(0);
				if (h1.get("idnegeri").toString() != ""
						&& h1.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h1.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				this.context.put("button_tambah", "yes");
				this.context.put("readmode", "disabled");
				this.context.put("add_new_peguam", "update");
				this.context.put("show_kemaskini_button", "yes");

			} else if ("tambah_peguam".equals(mode)) {
				if (bolehsimpan.equals("yes")) {
					tambahpeguam(session);
				}

				String id2X = getParam("idPemohon");
				logic_internal.setDataPeguam(id2X);
				listpeguam = logic_internal.getDataPeguam();
				this.context.put("listPenting", listpeguam);

				this.context.put("button_Kembali", "yes");
				this.context.put("button_tambah", "yes");

			} else if ("hapus_peguam".equals(mode)) {
				String idpeguam = getParam("idPeguam");
				logic_internal.deletePeguam(idpeguam);

				String id2X = getParam("idPemohon");
				logic_internal.setDataPeguam(id2X);
				listpeguam = logic_internal.getDataPeguam();

				this.context.put("listPenting", listpeguam);
				this.context.put("button_Kembali", "yes");
				this.context.put("button_tambah", "yes");

			}

			else if ("simpan_peguam".equals(mode)) {

				String id2X = getParam("idPemohon");
				String idpeguam = getParam("idPeguam");

				updatepeguam(session);

				logic_internal.setDataPeguamX(idpeguam);
				listpeguamX = logic_internal.getDataPeguamX();
				this.context.put("listpeguamX", listpeguamX);
				Hashtable h1 = (Hashtable) listpeguamX.get(0);
				if (h1.get("idnegeri").toString() != ""
						&& h1.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h1.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				logic_internal.setDataPeguam(id2X);
				listpeguam = logic_internal.getDataPeguam();
				this.context.put("listPenting", listpeguam);

				this.context.put("add_new_peguam", "update");
				this.context.put("readmode", "disabled");
				this.context.put("show_kemaskini_button", "yes");
				this.context.put("button_tambah", "yes");

			}
			String idX = getParam("idPermohonan");
			list = logic_A.setData(idX, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, idX, "Y", "", "T");
			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 1);
			this.context.put("selectedTabbawah", 1);
			this.context.put("selectedTabtepi", 0);
			this.context.put("View", list);
			logic_A.setDataFail(idX);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			String id1 = getParam("idSimati");
			int s1 = Integer.parseInt(getParam("no_subjaket"));
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			vm = "app/ppk/frmPraPrmhnnSek17Peguam.jsp";

		}

		else if ("Waris".equals(submit)) {
			this.context.put("flag_dup", getParam("flag_dup"));
			readability1 = "";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			String check_copy = getParam("copyAlamat");
			this.context.put("check_copy", check_copy);
			String check_copyP = getParam("copyAlamatP");
			this.context.put("check_copyP", check_copyP);
			this.context.put("Tambah_lapisan_berikut", "");
			this.context.put("addnew", "");
			this.context.put("show_lapisan_berikut", "");
			this.context.put("show_waris_update", "");
			this.context.put("buttonwaris", "");
			this.context.put("show_lapisan_waris", "");
			this.context.put("show_batal_waris", "");
			this.context.put("show_table_waris", "");
			this.context.put("readmode", "");
			this.context.put("buttonwarisSimpan", "");
			this.context.put("show_batal_waris", "");
			this.context.put("show_tambah_waris1", "");
			this.context.put("button_Kembali1", "");
			this.context.put("show_lapisan_berikut", "");
			this.context.put("show_lapisan_berikut_tambah", "");
			this.context.put("show_tarikh", "");
			this.context.put("show_button_lapisan", "");
			this.context.put("buttonwarislapisanSimpan", "");
			this.context.put("buttonwarislapisan", "");
			this.context.put("buttonwarislapisan", "");
			this.context.put("buttonwarislapisanSimpan", "");
			this.context.put("nk_tambah_lapisan", "");
			this.context.put("show_button_lapisan", "");
			this.context.put("show_button", "");
			this.context.put("show_senarai_lapis_pertama", "");
			this.context.put("show_lapisan_bawah", "");
			this.context.put("show_lapisan_berikut_update", "");
			this.context.put("showdefaulttarikh", "");

			if ("Warisview".equals(mode)) {
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("show_lapisan_bawah", "yes");
				this.context.put("show_tambah_waris1", "yes");
				this.context.put("button_Kembali1", "yes");
				this.context.put("nowpast", "now");
			}

			if ("Warisviewdulu".equals(mode)) {
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("show_lapisan_bawah", "yes");
				this.context.put("show_tambah_waris1", "yes");
				this.context.put("button_Kembali1", "yes");
				this.context.put("nowpast", "past");
			}

			else if ("Newwaris".equals(mode)) {
				this.context.put("show_batal_waris", "yes");
				this.context.put("buttonwarisSimpan", "Simpan");
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("addnew", "yes");
				this.context.put("show_table_waris", "yes");
				this.context.put("listBandarTetapbyNegeri", "");
				this.context.put("listBandarSuratbyNegeri", "");
			} else if ("Waristarikh".equals(mode)) {
				this.context.put("listWarisUpdate", "");
				this.context.put("listWarisLapisanUpdate", "");
				String key = "";
				String value = "";
				Enumeration allparam = request.getParameterNames();
				for (; allparam.hasMoreElements();) {
					key = (String) allparam.nextElement();
					value = request.getParameter(key);
					this.context.put(key, value);
				}
				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("checkHidupWaris", "0");
					this.context.put("show_tarikh", "");
					this.context.put("txdTarikhMatiWaris", "");
					this.context.put("txtWaktuKematianWaris", "");
				} else {
					this.context.put("checkHidupWaris", "1");
					this.context.put("show_tarikh", "yes");
					this.context.put("txdTarikhMatiWaris", "");
					this.context.put("txtWaktuKematianWaris", "");
					this.context.put("socStatusOBWaris", "3");
				}

				if (getParam("socNegeriWarisSurat") != ""
						&& getParam("socNegeriWarisSurat") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriWarisSurat")));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (getParam("socNegeriWaris") != ""
						&& getParam("socNegeriWaris") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriWaris")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				if (getParam("up_in").equals("in")) {
					this.context.put("showdefaulttarikh", "yes");
					this.context.put("show_batal_waris", "yes");
					this.context.put("buttonwarisSimpan", "Simpan");
					this.context.put("show_senarai_lapis_pertama", "yes");
					this.context.put("show_table_waris", "yes");
				}
				if (getParam("up_in").equals("up")) {
					this.context.put("show_waris_update", "yes");
					this.context.put("buttonwaris", "Simpan");
					this.context.put("show_batal_waris", "yes");
					this.context.put("show_senarai_lapis_pertama", "yes");
					this.context.put("show_tambah_waris1", "yes");
				}
				if (getParam("up_in").equals("in_lap")) {
					String idparent = getParam("idparentlapis");
					logic_internal.setDataWarisLapisan(idparent);
					listWarisLapisan = logic_internal.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);
					logic_internal.setDataWarisParent(idparent);
					listWarisParent = logic_internal.getDataWarisParent();
					this.context.put("listWarisParent", listWarisParent);
					this.context.put("show_lapisan_berikut_tambah", "yes");
					this.context.put("show_button_lapisan", "yes");
					this.context.put("show_lapisan_berikut", "yes");
					this.context.put("buttonwarislapisanSimpan", "Simpan");
				}
				if (getParam("up_in").equals("up_lap")) {

					String idparent = getParam("idparentlapis");
					logic_internal.setDataWarisLapisan(idparent);
					listWarisLapisan = logic_internal.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);
					logic_internal.setDataWarisParent(idparent);
					listWarisParent = logic_internal.getDataWarisParent();

					this.context.put("nk_tambah_lapisan", "yes");
					this.context.put("show_button_lapisan", "yes");
					this.context.put("show_lapisan_berikut_update", "yes");
					this.context.put("show_lapisan_berikut", "yes");
					this.context.put("buttonwarislapisan", "Simpan");
				}
			}

			else if ("Waristarikhsaudara".equals(mode)) {

				this.context.put("listWarisUpdate", "");
				this.context.put("listWarisLapisanUpdate", "");
				String key = "";
				String value = "";
				Enumeration allparam = request.getParameterNames();
				for (; allparam.hasMoreElements();) {
					key = (String) allparam.nextElement();
					value = request.getParameter(key);
					this.context.put(key, value);
				}
				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("checkHidupWaris", "0");
					this.context.put("show_tarikh", "");
				} else {
					this.context.put("checkHidupWaris", "1");
					this.context.put("show_tarikh", "yes");
					this.context.put("socStatusOBWaris", "3");
				}

				if (getParam("simpanmode").equals("get_saudara")) {
					this.context.put("socSaudaraWaris", "");

				}

				if (getParam("simpanmode").equals("get_negeritetap")) {
					this.context.put("txtBandarWaris", "");
					if (getParam("socNegeriWaris") != ""
							&& getParam("socNegeriWaris") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWaris")));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}

					if (getParam("socNegeriWarisSurat") != ""
							&& getParam("socNegeriWarisSurat") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWarisSurat")));
						this.context.put("listBandarSuratbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
					}
				}

				if (getParam("simpanmode").equals("get_negerisurat")) {
					this.context.put("txtBandarWarisSurat", "");
					if (getParam("socNegeriWarisSurat") != ""
							&& getParam("socNegeriWarisSurat") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWarisSurat")));
						this.context.put("listBandarSuratbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
					}

					if (getParam("socNegeriWaris") != ""
							&& getParam("socNegeriWaris") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWaris")));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}
				}
				if (getParam("simpanmode").equals("copy1_true")) {
					
					this.context.put("nama_pelbagainegara_surat",getParam("nama_pelbagainegara"));
					
					this.context.put("txtAlamatTerakhir1WarisSurat",
							getParam("txtAlamatTerakhir1Waris"));
					this.context.put("txtAlamatTerakhir2WarisSurat",
							getParam("txtAlamatTerakhir2Waris"));
					this.context.put("txtAlamatTerakhir3WarisSurat",
							getParam("txtAlamatTerakhir3Waris"));
					this.context.put("txtPoskodWarisSurat",
							getParam("txtPoskodWaris"));
					this.context.put("txtBandarWarisSurat",
							getParam("txtBandarWaris"));
					this.context.put("socNegeriWarisSurat",
							getParam("socNegeriWaris"));

					if (getParam("socNegeriWaris") != ""
							&& getParam("socNegeriWaris") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWaris")));
						this.context.put("listBandarSuratbyNegeri", s3);
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
						this.context.put("listBandarTetapbyNegeri", "");
					}
				}
				if (getParam("simpanmode").equals("copy1_false")) {
					this.context.put("nama_pelbagainegara_surat", "");
					
					this.context.put("txtAlamatTerakhir1WarisSurat", "");
					this.context.put("txtAlamatTerakhir2WarisSurat", "");
					this.context.put("txtAlamatTerakhir3WarisSurat", "");
					this.context.put("txtPoskodWarisSurat", "");
					this.context.put("txtBandarWarisSurat", "");
					this.context.put("socNegeriWarisSurat", "");

					if (getParam("socNegeriWaris") != ""
							&& getParam("socNegeriWaris") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWaris")));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}
					this.context.put("listBandarSuratbyNegeri", "");
				}
				if (getParam("up_in").equals("in")) {
					this.context.put("show_batal_waris", "yes");
					this.context.put("buttonwarisSimpan", "Simpan");
					this.context.put("show_senarai_lapis_pertama", "yes");
					this.context.put("show_table_waris", "yes");
				}
				if (getParam("up_in").equals("up")) {
					this.context.put("show_waris_update", "yes");
					this.context.put("buttonwaris", "Simpan");
					this.context.put("show_batal_waris", "yes");
					this.context.put("show_senarai_lapis_pertama", "yes");
					this.context.put("show_tambah_waris1", "yes");
				}
				if (getParam("up_in").equals("in_lap")) {
					String idparent = getParam("idparentlapis");
					logic_internal.setDataWarisLapisan(idparent);
					listWarisLapisan = logic_internal.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);
					logic_internal.setDataWarisParent(idparent);
					listWarisParent = logic_internal.getDataWarisParent();
					this.context.put("listWarisParent", listWarisParent);
					this.context.put("show_lapisan_berikut_tambah", "yes");
					this.context.put("show_button_lapisan", "yes");
					this.context.put("show_lapisan_berikut", "yes");
					this.context.put("buttonwarislapisanSimpan", "Simpan");
				}
				if (getParam("up_in").equals("up_lap")) {

					String idparent = getParam("idparentlapis");
					logic_internal.setDataWarisLapisan(idparent);
					listWarisLapisan = logic_internal.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);

					logic_internal.setDataWarisParent(idparent);
					listWarisParent = logic_internal.getDataWarisParent();
					this.context.put("listWarisParent", listWarisParent);

					this.context.put("listWarisParent", listWarisParent);
					this.context.put("nk_tambah_lapisan", "yes");
					this.context.put("show_button_lapisan", "yes");
					this.context.put("show_lapisan_berikut_update", "yes");
					this.context.put("show_lapisan_berikut", "yes");
					this.context.put("buttonwarislapisan", "Simpan");
				}

			}

			else if ("WaristarikhsaudaraP".equals(mode)) {

				this.context.put("listWarisUpdate", "");
				this.context.put("listWarisLapisanUpdate", "");
				String key = "";
				String value = "";
				Enumeration allparam = request.getParameterNames();
				for (; allparam.hasMoreElements();) {
					key = (String) allparam.nextElement();
					value = request.getParameter(key);
					this.context.put(key, value);
				}
				String id = getParam("idPermohonan");
				list = logic_A.setData(id, (String) session
						.getAttribute("_ekptg_user_id"));
				// list = logic_A.getData();
				headerppk_baru(session, id, "Y", "", "T");

				Hashtable x = (Hashtable) list.get(0);

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("checkHidupWaris", "0");
					this.context.put("show_tarikh", "");
				} else {
					this.context.put("checkHidupWaris", "1");
					this.context.put("show_tarikh", "yes");
					this.context.put("socStatusOBWaris", "3");
				}

				if (getParam("simpanmode").equals("get_saudara")) {
					this.context.put("socSaudaraWaris", "");

				}

				if (getParam("simpanmode").equals("get_negeritetap")) {
					this.context.put("txtBandarWaris", "");
					if (getParam("socNegeriWaris") != ""
							&& getParam("socNegeriWaris") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWaris")));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}

					if (getParam("socNegeriWarisSurat") != ""
							&& getParam("socNegeriWarisSurat") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWarisSurat")));
						this.context.put("listBandarSuratbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
					}
				}

				if (getParam("simpanmode").equals("get_negerisurat")) {
					this.context.put("txtBandarWarisSurat", "");
					if (getParam("socNegeriWarisSurat") != ""
							&& getParam("socNegeriWarisSurat") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWarisSurat")));
						this.context.put("listBandarSuratbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
					}

					if (getParam("socNegeriWaris") != ""
							&& getParam("socNegeriWaris") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWaris")));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}
				}
				if (getParam("simpanmode").equals("copy1_true")) {
					// (x.get("pmidnegeri").toString() == "");
					
					this.context.put("nama_pelbagainegara", x.get("nama_pelbagainegara").toString());
		            this.context.put("nama_pelbagainegara_surat", x.get("nama_pelbagainegara_surat").toString());

					this.context.put("txtAlamatTerakhir1WarisSurat", x.get(
							"alamat1surat").toString());
					this.context.put("txtAlamatTerakhir2WarisSurat", x.get(
							"alamat2surat").toString());
					this.context.put("txtAlamatTerakhir3WarisSurat", x.get(
							"alamat3surat").toString());
					this.context.put("txtPoskodWarisSurat", x
							.get("poskodsurat").toString());
					this.context.put("txtBandarWarisSurat", x.get(
							"idbandarsurat").toString());
					this.context.put("socNegeriWarisSurat", x.get(
							"idnegerisurat").toString());

					this.context.put("txtAlamatTerakhir1Waris", x
							.get("alamat1").toString());
					this.context.put("txtAlamatTerakhir2Waris", x
							.get("alamat2").toString());
					this.context.put("txtAlamatTerakhir3Waris", x
							.get("alamat3").toString());
					this.context.put("txtPoskodWaris", x.get("poskod")
							.toString());
					this.context.put("txtBandarWaris", x.get("idbandar")
							.toString());
					this.context.put("socNegeriWaris", x.get("pmidnegeri")
							.toString());

					if (x.get("pmidnegeri").toString() != ""
							&& x.get("pmidnegeri").toString() != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(x.get("pmidnegeri").toString()));
						// this.context.put("listBandarSuratbyNegeri",s3);
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						// this.context.put("listBandarSuratbyNegeri","");
						this.context.put("listBandarTetapbyNegeri", "");
					}

					if (x.get("idnegerisurat").toString() != ""
							&& x.get("idnegerisurat").toString() != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(x.get("idnegerisurat").toString()));
						this.context.put("listBandarSuratbyNegeri", s3);
						// this.context.put("listBandarTetapbyNegeri",s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
						// this.context.put("listBandarTetapbyNegeri","");
					}

				}
				if (getParam("simpanmode").equals("copy1_false")) {
					
					this.context.put("nama_pelbagainegara_surat", "");
		            this.context.put("nama_pelbagainegara", "");
		             
		             
					this.context.put("txtAlamatTerakhir1WarisSurat", "");
					this.context.put("txtAlamatTerakhir2WarisSurat", "");
					this.context.put("txtAlamatTerakhir3WarisSurat", "");
					this.context.put("txtPoskodWarisSurat", "");
					this.context.put("txtBandarWarisSurat", "");
					this.context.put("socNegeriWarisSurat", "");

					this.context.put("txtAlamatTerakhir1Waris", "");
					this.context.put("txtAlamatTerakhir2Waris", "");
					this.context.put("txtAlamatTerakhir3Waris", "");
					this.context.put("txtPoskodWaris", "");
					this.context.put("txtBandarWaris", "");
					this.context.put("socNegeriWaris", "");

					this.context.put("listBandarTetapbyNegeri", "");
					this.context.put("listBandarSuratbyNegeri", "");
				}
				if (getParam("up_in").equals("in")) {
					this.context.put("show_batal_waris", "yes");
					this.context.put("buttonwarisSimpan", "Simpan");
					this.context.put("show_senarai_lapis_pertama", "yes");
					this.context.put("show_table_waris", "yes");
				}
				if (getParam("up_in").equals("up")) {
					this.context.put("show_waris_update", "yes");
					this.context.put("buttonwaris", "Simpan");
					this.context.put("show_batal_waris", "yes");
					this.context.put("show_senarai_lapis_pertama", "yes");
					this.context.put("show_tambah_waris1", "yes");
				}
				if (getParam("up_in").equals("in_lap")) {
					String idparent = getParam("idparentlapis");
					logic_internal.setDataWarisLapisan(idparent);
					listWarisLapisan = logic_internal.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);
					logic_internal.setDataWarisParent(idparent);
					listWarisParent = logic_internal.getDataWarisParent();
					this.context.put("listWarisParent", listWarisParent);
					this.context.put("show_lapisan_berikut_tambah", "yes");
					this.context.put("show_button_lapisan", "yes");
					this.context.put("show_lapisan_berikut", "yes");
					this.context.put("buttonwarislapisanSimpan", "Simpan");
				}
				if (getParam("up_in").equals("up_lap")) {

					String idparent = getParam("idparentlapis");
					logic_internal.setDataWarisLapisan(idparent);
					listWarisLapisan = logic_internal.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);

					logic_internal.setDataWarisParent(idparent);
					listWarisParent = logic_internal.getDataWarisParent();
					this.context.put("listWarisParent", listWarisParent);

					this.context.put("listWarisParent", listWarisParent);
					this.context.put("nk_tambah_lapisan", "yes");
					this.context.put("show_button_lapisan", "yes");
					this.context.put("show_lapisan_berikut_update", "yes");
					this.context.put("show_lapisan_berikut", "yes");
					this.context.put("buttonwarislapisan", "Simpan");

				}
				this.context.put("check_copy", "");

			}

			else if ("Getwaris".equals(mode)) {
				String idwaris = getParam("idwaris");
				logic_internal.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic_internal.getDataWarisUpdate();
				this.context.put("listWarisUpdate", listWarisUpdate);

				Hashtable h = (Hashtable) listWarisUpdate.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				logic_internal.setDataWarisLapisanIdMatiDelete(idwaris);
				listWarisLapisanIdMatiDelete = logic_internal
						.getDataWarisLapisanIdMatiDelete();
				this.context.put("listWarisLapisanIdMatiDelete",
						listWarisLapisanIdMatiDelete);

				this.context.put("readmode", "disabled");
				this.context.put("show_waris_update", "yes");
				this.context.put("buttonwaris", "Kemaskini");
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("show_tambah_waris1", "yes");
			} else if ("Lapisan_berikut".equals(mode)) {
				String idwaris = getParam("idwarisup");
				this.context.put("idparent", idwaris);
				this.context.put("ip", idwaris);
				logic_internal.setDataWarisLapisan(idwaris);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				logic_internal.setDataWarisParent(idwaris);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("readmode", "");
				this.context.put("listWa", listWarisParent);
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("nk_tambah_lapisan", "yes");
			} else if ("Lapisan_berikut_lapisan".equals(mode)) {
				String idwaris = getParam("idwarisup");
				this.context.put("ip", idwaris);
				logic_internal.setDataWarisLapisan(idwaris);
				logic_internal.setDataWarisParent(idwaris);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				logic_internal.setDataWarisParent(idwaris);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWa", listWarisParent);
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("nk_tambah_lapisan", "yes");

			} else if ("tambah_waris_lapisan".equals(mode)) {
				String idparent = getParam("idparentlapis");
				String idwaris = getParam("idwarisup");
				if (bolehsimpan.equals("yes")) {
					addWarisBerikut(session);
				}
				logic_internal.setDataWarisLapisan(idparent);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}
				logic_internal.setDataWarisParent(idparent);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				logic_internal.setDataWaris(idwaris);
				listWaris = logic_internal.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic_internal.setDataWarisParent(idparent);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				logic_internal.setDataWarisLapisan(idparent);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("nk_tambah_lapisan", "yes");

			} else if ("GetwarisLapisan".equals(mode)) {
				this.context.put("readmode", disability1);
				String idwaris = getParam("idwarislapis");
				String idparent = getParam("idparentlapis");
				logic_internal.setDataWarisLapisan(idparent);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				logic_internal.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic_internal.getDataWarisUpdate();
				logic_internal.setDataWarisParent(idparent);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				this.context.put("listWarisParent", listWarisParent);
				logic_internal.setDataWarisLapisanIdMatiDelete(idwaris);
				listWarisLapisanIdMatiDelete = logic_internal
						.getDataWarisLapisanIdMatiDelete();

				Hashtable h = (Hashtable) listWarisUpdate.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				this.context.put("listWarisLapisanIdMatiDelete",
						listWarisLapisanIdMatiDelete);
				this.context.put("nk_tambah_lapisan", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("buttonwarislapisan", "Kemaskini");
				this.context.put("readmode", "disabled");

			} else if ("hapus_waris_lapisan".equals(mode)) {
				String idwarisup = getParam("idwarisup");
				logic_internal.deleteWarisHubungan(idwarisup,
						getParam("id_Permohonansimati"));
				logic_internal.deleteWaris(idwarisup,
						getParam("id_Permohonansimati"));
				String idparent = getParam("idparentlapis");
				this.context.put("idparent", idparent);
				logic_internal.setDataWarisParent(idparent);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				logic_internal.setDataWarisLapisan(idparent);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}
				String id_Permohonansimati = getParam("id_Permohonansimati");
				logic_internal.setDataWarisOB(id_Permohonansimati);
				listWarisOB = logic_internal.getDataWarisOB();
				this.context.put("listWarisOB", listWarisOB);

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("nk_tambah_lapisan", "yes");

			} else if ("GetwarisLapisanX".equals(mode)) {
				String idwaris = getParam("idwarislapisX");
				String idparent = getParam("idparentlapisX");
				logic_internal.setDataWarisLapisan(idparent);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				logic_internal.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic_internal.getDataWarisUpdate();
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				logic_internal.setDataWarisLapisanIdMatiDelete(idwaris);
				listWarisLapisanIdMatiDelete = logic_internal
						.getDataWarisLapisanIdMatiDelete();
				this.context.put("listWarisLapisanIdMatiDelete",
						listWarisLapisanIdMatiDelete);
				logic_internal.setDataWarisParent(idparent);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);

				Hashtable h = (Hashtable) listWarisUpdate.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				this.context.put("readmode", disability1);
				this.context.put("show_lapisan_bawah", "");
				this.context.put("nk_tambah_lapisan", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("buttonwarislapisan", "Kemaskini");
				this.context.put("readmode", "disabled");
			} else if ("Tambah_lapisan_berikut".equals(mode)) {
				listWarisup = new Vector();
				String idp = getParam("idparentlapis");
				Hashtable h = new Hashtable();
				h.put("statushidup", "0");
				listWarisup.addElement(h);
				this.context.put("listWarisLapisanUpdate", listWarisup);

				logic_internal.setDataWarisParent(idp);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				logic_internal.setDataWarisLapisan(idp);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("addnew", "yes");
				this.context.put("show_lapisan_berikut_tambah", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("buttonwarislapisanSimpan", "Simpan");
				this.context.put("Tambah_lapisan_berikut", "yes");

			} else if ("tambah_waris".equals(mode)) {
				if (bolehsimpan.equals("yes")) {
					addWaris(session);
				}
				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}
				String id_Permohonansimati = getParam("id_Permohonansimati");
				logic_internal.setDataWarisOB(id_Permohonansimati);
				listWarisOB = logic_internal.getDataWarisOB();
				this.context.put("listWarisOB", listWarisOB);
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("show_lapisan_bawah", "yes");
				this.context.put("show_tambah_waris1", "yes");
				this.context.put("button_Kembali1", "yes");
			} else if ("hapus_waris".equals(mode)) {
				String idwarisup = getParam("idwarisup");
				logic_internal.deleteWaris(idwarisup,
						getParam("id_Permohonansimati"));
				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}
				String id_Permohonansimati = getParam("id_Permohonansimati");
				logic_internal.setDataWarisOB(id_Permohonansimati);
				listWarisOB = logic_internal.getDataWarisOB();
				this.context.put("listWarisOB", listWarisOB);
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("show_lapisan_bawah", "yes");
				this.context.put("show_tambah_waris1", "yes");
				this.context.put("button_Kembali1", "yes");
			} else if ("kemaskini_waris".equals(mode)) {
				String idwaris = getParam("idwarisup");
				logic_internal.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic_internal.getDataWarisUpdate();
				this.context.put("listWarisUpdate", listWarisUpdate);
				Hashtable h = (Hashtable) listWarisUpdate.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				this.context.put("show_waris_update", "yes");
				this.context.put("buttonwaris", "Simpan");
				this.context.put("show_batal_waris", "yes");
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("show_tambah_waris1", "yes");
			} else if ("kemaskini_waris_lapisan".equals(mode)) {
				String idwaris = getParam("idwarisup");
				String idparent = getParam("idparentlapis");
				logic_internal.setDataWarisLapisan(idparent);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				logic_internal.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				logic_internal.setDataWarisParent(idparent);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				listWarisUpdate = logic_internal.getDataWarisUpdate();
				logic_internal.setDataWarisParent(idparent);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				logic_internal.setDataWarisLapisanIdMatiDelete(idwaris);
				listWarisLapisanIdMatiDelete = logic_internal
						.getDataWarisLapisanIdMatiDelete();
				this.context.put("listWarisLapisanIdMatiDelete",
						listWarisLapisanIdMatiDelete);

				Hashtable h = (Hashtable) listWarisUpdate.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				this.context.put("nk_tambah_lapisan", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("buttonwarislapisan", "Simpan");
			} else if ("simpan_waris".equals(mode)) {

				disability1 = "disabled";
				updatewaris(session);
				logic_internal.setDataWarisUpdate(getParam("idwarisup"),
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic_internal.getDataWarisUpdate();
				this.context.put("listWarisUpdate", listWarisUpdate);
				Hashtable h = (Hashtable) listWarisUpdate.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				this.context.put("readmode", "disabled");
				this.context.put("show_waris_update", "yes");
				this.context.put("buttonwaris", "Kemaskini");
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("show_tambah_waris1", "yes");

			}

			else if ("simpan_waris_lapisan".equals(mode)) {
				listpenting = new Vector();
				String idparent = getParam("idparentlapis");
				String idxx = getParam("idwarisup");
				disability1 = "disabled";
				updatewaris(session);
				logic_internal.setDataWarisUpdate(getParam("idwarisup"),
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic_internal.getDataWarisUpdate();
				this.context.put("listWarisUpdate", listWarisUpdate);
				logic_internal.setDataWarisUpdate(idxx,
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic_internal.getDataWarisUpdate();
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				logic_internal.setDataWaris(idxx);
				listWaris = logic_internal.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic_internal.setDataWarisLapisan(idparent);
				listWarisLapisan = logic_internal.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				logic_internal.setDataWarisParent(idparent);
				listWarisParent = logic_internal.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				Hashtable h = (Hashtable) listWarisUpdate.get(0);
				if (h.get("idnegeriSurat").toString() != ""
						&& h.get("idnegeriSurat").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeriSurat").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (h.get("idnegeri").toString() != ""
						&& h.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				this.context.put("nk_tambah_lapisan", "yes");
				this.context.put("show_button_lapisan", "yes");
				this.context.put("show_lapisan_berikut_update", "yes");
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("buttonwarislapisan", "Kemaskini");
				this.context.put("readmode", "disabled");
			}

			String id = getParam("idPermohonan");
			String id_Permohonansimati = getParam("id_Permohonansimati");
			logic_internal.setDataSimati(id);
			listSimati = logic_internal.getDataSimati();
			this.context.put("listSimati", listSimati);
			logic_internal.setDataWaris(id_Permohonansimati);
			listWaris = logic_internal.getDataWaris();
			this.context.put("listWaris", listWaris);
			logic_internal.setDataWarisLapisanIdMati(id_Permohonansimati);
			listWarisLapisanIdMati = logic_internal.getDataWarisLapisanIdMati();
			this.context.put("listWarisLapisanIdMati", listWarisLapisanIdMati);
			logic_internal.setDataWarisLapisanIdMatiDulu(id_Permohonansimati);
			listWarisLapisanIdMatiDulu = logic_internal
					.getDataWarisLapisanIdMatiDulu();
			this.context.put("listWarisLapisanIdMatiDulu",
					listWarisLapisanIdMatiDulu);

			logic_internal.setDataWarisDulu(id_Permohonansimati);
			Vector listWarisDulu = logic_internal.getDataWarisDulu();
			this.context.put("listWarisDulu", listWarisDulu);
			logic_internal.setDataWarisOB(id_Permohonansimati);
			listWarisOB = logic_internal.getDataWarisOB();
			this.context.put("listWarisOB", listWarisOB);
			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 2);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			context.put("DATEUTIL", new DateUtil());
			list = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			// list = logic_A.getData();
			this.context.put("View", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			String id1 = getParam("idSimati");
			int s1 = Integer.parseInt(getParam("no_subjaket"));
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			Vector list_getListOBUpdate = null;
			list_getListOBUpdate = logic_A.getListOBUpdate(id_Permohonansimati);
			this.context.put("list_getListOBUpdate", list_getListOBUpdate);
/*
			Vector kenegaraan = null;
			kenegaraan = mainheader.kenegaraan();
			this.context.put("kenegaraan", kenegaraan);
*/
			vm = "app/ppk/FrmPraPrmhnnSek17Waris.jsp";
		}

		else if ("Penting".equals(submit) || "Saksi".equals(submit)
				|| "Pemiutang".equals(submit) || "Penghutang".equals(submit)) {
			String check_copy = getParam("copyAlamat");
			this.context.put("check_copy", check_copy);
			this.context.put("nk_tambah_penting", "");
			this.context.put("nk_update_penting", "");
			this.context.put("nk_button_penting", "");
			this.context.put("tambah_ob_button", "");
			this.context.put("kembali_ob_button", "");
			this.context.put("tambah_baru", "");
			this.context.put("buttonpenting", "");
			this.context.put("readmode", "");
			this.context.put("insertbaru", "");

			if ("Pentingview".equals(mode) || "Saksiview".equals(mode)
					|| "Pemiutangview".equals(mode)
					|| "Penghutangview".equals(mode)) {
				this.context.put("tambah_ob_button", "yes");
				this.context.put("kembali_ob_button", "yes");
				this.context.put("nowpast", "now");
			} else if ("Pentingviewdulu".equals(mode)
					|| "Saksiviewdulu".equals(mode)
					|| "Pemiutangviewdulu".equals(mode)
					|| "Penghutangviewdulu".equals(mode)) {
				this.context.put("tambah_ob_button", "yes");
				this.context.put("kembali_ob_button", "yes");
				this.context.put("nowpast", "past");
			}

			else if ("tambah_penting".equals(mode)) {
				if ("Penghutang".equals(submit)) {
					if (bolehsimpan.equals("yes")) {
						addPenghutang(session);
					}
				} else {
					if (bolehsimpan.equals("yes")) {
						addPenting(session);
					}
				}
				this.context.put("tambah_ob_button", "yes");
				this.context.put("kembali_ob_button", "yes");

			} else if ("getBandar".equals(mode)) {
				this.context.put("listPentingbyIDOB", "");
				String key = "";
				String value = "";
				Enumeration allparam = request.getParameterNames();
				for (; allparam.hasMoreElements();) {
					key = (String) allparam.nextElement();
					value = request.getParameter(key);
					// System.out.println(key + ":" + value);
					this.context.put(key, value);
				}
				String jenisbandar = getParam("bandar");
				if (jenisbandar.equals("tetap")) {
					this.context.put("txtBandarPenting", "");
					this.context.put("txtBandarPentingU", "");
					this.context.put("socBandarPentingU", "");
					if (getParam("simpanmode").equals("insert")) {
						if (getParam("socNegeriPenting") != ""
								&& getParam("socNegeriPenting") != "0") {
							Vector s3 = logic_A.getListBandarByNegeri(Integer
									.parseInt(getParam("socNegeriPenting")));
							this.context.put("listBandarTetapbyNegeri", s3);
						} else {
							this.context.put("listBandarTetapbyNegeri", "");
						}
					}
					if (getParam("simpanmode").equals("update")) {
						if (getParam("socNegeriPentingU") != ""
								&& getParam("socNegeriPentingU") != "0") {
							Vector s3 = logic_A.getListBandarByNegeri(Integer
									.parseInt(getParam("socNegeriPentingU")));
							this.context.put("listBandarTetapbyNegeri", s3);
						} else {
							this.context.put("listBandarTetapbyNegeri", "");
						}
					}
					if (getParam("socNegeriWarisSurat") != ""
							&& getParam("socNegeriWarisSurat") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWarisSurat")));
						this.context.put("listBandarSuratbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
					}
				}
				if (jenisbandar.equals("surat")) {
					this.context.put("txtBandarWarisSurat", "");
					this.context.put("socBandarWarisSurat", "");
					if (getParam("socNegeriWarisSurat") != ""
							&& getParam("socNegeriWarisSurat") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriWarisSurat")));
						this.context.put("listBandarSuratbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
					}
					if (getParam("simpanmode").equals("insert")) {
						if (getParam("socNegeriPenting") != ""
								&& getParam("socNegeriPenting") != "0") {
							Vector s3 = logic_A.getListBandarByNegeri(Integer
									.parseInt(getParam("socNegeriPenting")));
							this.context.put("listBandarTetapbyNegeri", s3);
						} else {
							this.context.put("listBandarTetapbyNegeri", "");
						}
					}
					if (getParam("simpanmode").equals("update")) {
						if (getParam("socNegeriPentingU") != ""
								&& getParam("socNegeriPentingU") != "0") {
							Vector s3 = logic_A.getListBandarByNegeri(Integer
									.parseInt(getParam("socNegeriPentingU")));
							this.context.put("listBandarTetapbyNegeri", s3);
						} else {
							this.context.put("listBandarTetapbyNegeri", "");
						}
					}
				}
				if (jenisbandar.equals("copy")
						&& getParam("simpanmode").equals("insert")) {
					this.context.put("nama_pelbagainegara_surat",getParam("nama_pelbagainegara"));
					
					this.context.put("txtAlamatTerakhir1WarisSurat",
							getParam("txtAlamatTerakhir1Penting"));
					this.context.put("txtAlamatTerakhir2WarisSurat",
							getParam("txtAlamatTerakhir2Penting"));
					this.context.put("txtAlamatTerakhir3WarisSurat",
							getParam("txtAlamatTerakhir3Penting"));
					this.context.put("txtPoskodWarisSurat",
							getParam("txtPoskodPenting"));
					this.context.put("socNegeriWarisSurat",
							getParam("socNegeriPenting"));
					this.context.put("txtBandarWarisSurat",
							getParam("txtBandarPenting"));
					if (getParam("socNegeriPenting") != ""
							&& getParam("socNegeriPenting") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriPenting")));
						this.context.put("listBandarSuratbyNegeri", s3);
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
						this.context.put("listBandarTetapbyNegeri", "");
					}
				}
				if (jenisbandar.equals("copy")
						&& getParam("simpanmode").equals("update")) {
					this.context.put("nama_pelbagainegara_surat",getParam("nama_pelbagainegara"));
					
					
					this.context.put("txtAlamatTerakhir1WarisSurat",
							getParam("txtAlamatTerakhir1PentingU"));
					this.context.put("txtAlamatTerakhir2WarisSurat",
							getParam("txtAlamatTerakhir2PentingU"));
					this.context.put("txtAlamatTerakhir3WarisSurat",
							getParam("txtAlamatTerakhir3PentingU"));
					this.context.put("txtPoskodWarisSurat",
							getParam("txtPoskodPentingU"));
					this.context.put("socNegeriWarisSurat",
							getParam("socNegeriPentingU"));
					this.context.put("socBandarWarisSurat",
							getParam("socBandarPentingU"));
					if (getParam("socNegeriPentingU") != ""
							&& getParam("socNegeriPentingU") != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeriPentingU")));
						this.context.put("listBandarSuratbyNegeri", s3);
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
						this.context.put("listBandarTetapbyNegeri", "");
					}
				}
				if (jenisbandar.equals("copyfalse")) {
					this.context.put("nama_pelbagainegara_surat", "");
					
					this.context.put("txtAlamatTerakhir1WarisSurat", "");
					this.context.put("txtAlamatTerakhir2WarisSurat", "");
					this.context.put("txtAlamatTerakhir3WarisSurat", "");
					this.context.put("txtPoskodWarisSurat", "");
					this.context.put("socNegeriWarisSurat", "");
					this.context.put("txtBandarWarisSurat", "");
					this.context.put("socBandarWarisSurat", "");
				}
				if (getParam("simpanmode").equals("insert")) {
					this.context.put("buttonpenting", "tambah");
					this.context.put("nk_tambah_penting", "yes");
					this.context.put("nk_button_penting", "yes");
				}
				if (getParam("simpanmode").equals("update")) {
					this.context.put("nk_button_penting", "yes");
					this.context.put("nk_update_penting", "yes");
					this.context.put("buttonpenting", "Simpan");
					this.context.put("tambah_ob_button", "yes");
				}
			} else if ("tambah_penting_baru".equals(mode)) {
				this.context.put("insertbaru", "yes");
				this.context.put("buttonpenting", "tambah");
				this.context.put("listPentingbyIDOB", "");
				this.context.put("nk_tambah_penting", "yes");
				this.context.put("nk_button_penting", "yes");
				this.context.put("listBandarSuratbyNegeri", "");
				this.context.put("listBandarTetapbyNegeri", "");
				this.context.put("readmodekp", "disabled");
				this.context.put("readmodesy", "disabled");
				this.context.put("readmodesy", "disabled");
			}

			else if ("hapus_penting".equals(mode)) {
				String iddob = getParam("txtIdOb");

				if ("Penghutang".equals(submit)) {
					logic_internal.deletePenghutang(iddob);
				} else {
					logic_internal.deletePenting(iddob);
				}
				this.context.put("tambah_ob_button", "yes");
				this.context.put("kembali_ob_button", "yes");
			}

			else if ("GetPenting".equals(mode)) {

				String idob = getParam("txtIdOb");

				if ("Penghutang".equals(submit)) {
					logic_internal.setDataPenghutangbyIDOB(idob);
					listPenghutangbyIDOB = logic_internal
							.getDataPenghutangbyIDOB();
					this.context.put("listPentingbyIDOB", listPenghutangbyIDOB);
					Hashtable m = (Hashtable) listPenghutangbyIDOB.get(0);
					if (m.get("idnegeri").toString() != ""
							&& m.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(m.get("idnegeri").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}
				} else {
					logic_internal.setDataPentingbyIDOB(idob,
							getParam("id_Permohonansimati"));
					listPentingbyIDOB = logic_internal.getDataPentingbyIDOB();
					this.context.put("listPentingbyIDOB", listPentingbyIDOB);
					Hashtable k = (Hashtable) listPentingbyIDOB.get(0);
					if (k.get("idnegeriSurat").toString() != ""
							&& k.get("idnegeriSurat").toString() != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(k.get("idnegeriSurat").toString()));
						this.context.put("listBandarSuratbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
					}
					Hashtable h = (Hashtable) listPentingbyIDOB.get(0);
					if (h.get("idnegeri").toString() != ""
							&& h.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(h.get("idnegeri").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}
				}

				if (getParam("simpanmode").equals("getpenting")) {
					this.context.put("nk_button_penting", "yes");
					this.context.put("nk_update_penting", "yes");
					this.context.put("readmode", "disabled");
					this.context.put("buttonpenting", "Kemaskini");
					this.context.put("tambah_ob_button", "yes");
				}
				if (getParam("simpanmode").equals("kemaskinipenting")) {
					this.context.put("nk_button_penting", "yes");
					this.context.put("nk_update_penting", "yes");
					this.context.put("buttonpenting", "Simpan");
					this.context.put("tambah_ob_button", "yes");
				}
			} else if ("simpan_penting".equals(mode)) {
				String idob = getParam("txtIdOb");
				if ("Penghutang".equals(submit)) {
					updatepenghutang(session);
					logic_internal.setDataPenghutangbyIDOB(idob);
					listPenghutangbyIDOB = logic_internal
							.getDataPenghutangbyIDOB();

					Hashtable x = (Hashtable) listPenghutangbyIDOB.get(0);
					if (x.get("idnegeri").toString() != ""
							&& x.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(x.get("idnegeri").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}
					this.context.put("listPentingbyIDOB", listPenghutangbyIDOB);
				} else {
					updatepenting(session);
					logic_internal.setDataPentingbyIDOB(idob,
							getParam("id_Permohonansimati"));
					listPentingbyIDOB = logic_internal.getDataPentingbyIDOB();
					Hashtable k = (Hashtable) listPentingbyIDOB.get(0);
					if (k.get("idnegeriSurat").toString() != ""
							&& k.get("idnegeriSurat").toString() != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(k.get("idnegeriSurat").toString()));
						this.context.put("listBandarSuratbyNegeri", s3);
					} else {
						this.context.put("listBandarSuratbyNegeri", "");
					}
					Hashtable h = (Hashtable) listPentingbyIDOB.get(0);
					if (h.get("idnegeri").toString() != ""
							&& h.get("idnegeri").toString() != "0") {
						Vector s3 = logic_A.getListBandarByNegeri(Integer
								.parseInt(h.get("idnegeri").toString()));
						this.context.put("listBandarTetapbyNegeri", s3);
					} else {
						this.context.put("listBandarTetapbyNegeri", "");
					}
					this.context.put("listPentingbyIDOB", listPentingbyIDOB);
				}
				this.context.put("nk_button_penting", "yes");
				this.context.put("nk_update_penting", "yes");
				this.context.put("readmode", "disabled");
				this.context.put("buttonpenting", "Kemaskini");
				this.context.put("tambah_ob_button", "yes");

			}
			String id_Permohonansimati = getParam("id_Permohonansimati");
			logic_internal.setDataPenting(id_Permohonansimati);
			listPenting = logic_internal.getDataPenting();
			this.context.put("listPenting", listPenting);
			logic_internal.setDataPentingDulu(id_Permohonansimati);
			listPentingdulu = logic_internal.getDataPentingDulu();
			this.context.put("listPentingdulu", listPentingdulu);

			logic_internal.setDataPenghutang(id_Permohonansimati);
			listPenghutang = logic_internal.getDataPenghutang();
			this.context.put("listPenting", listPenghutang);

			logic_internal.setDataPenghutangDulu(id_Permohonansimati);
			listPenghutangdulu = logic_internal.getDataPenghutangDulu();
			this.context.put("listPentingdulu", listPenghutangdulu);

			this.context.put("selectedTabatas", 0);

			if ("Penting".equals(submit)) {
				this.context.put("selectedTabtengah", 3);
				this.context.put("listPenting", listPenting);
				this.context.put("listPentingdulu", listPentingdulu);
				vm = "app/ppk/frmPraPrmhnnSek17OrgBerkepentingan.jsp";
			} else if ("Saksi".equals(submit)) {
				this.context.put("selectedTabtengah", 4);
				this.context.put("listPenting", listPenting);
				this.context.put("listPentingdulu", listPentingdulu);
				vm = "app/ppk/frmPraPrmhnnSek17Saksi.jsp";
			} else if ("Pemiutang".equals(submit)) {
				this.context.put("selectedTabtengah", 5);
				this.context.put("listPenting", listPenting);
				this.context.put("listPentingdulu", listPentingdulu);
				vm = "app/ppk/frmPraPrmhnnSek17Pemiutang.jsp";
			} else if ("Penghutang".equals(submit)) {
				this.context.put("selectedTabtengah", 6);
				this.context.put("listPenting", listPenghutang);
				this.context.put("listPentingdulu", listPenghutangdulu);
				vm = "app/ppk/frmPraPrmhnnSek17Penghutang.jsp";

			}
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			context.put("DATEUTIL", new DateUtil());
			String id = getParam("idPermohonan");
			list = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);

			String id1 = getParam("idSimati");
			int s1 = Integer.parseInt(getParam("no_subjaket"));
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

		}

		else if ("Htaam".equals(submit)) {
			this.context.put("add_new_harta", "");
			this.context.put("buttonhtaam", "");
			this.context.put("tambahharta", "");
			this.context.put("kembaliharta", "");
			this.context.put("add_new_harta", "");
			this.context.put("negeri", "");
			this.context.put("daerah", "");
			this.context.put("mukim", "");
			this.context.put("readmode", "");
			this.context.put("readmodenegeri", "");
			this.context.put("readmodedaerah", "");
			this.context.put("readmodemukim", "");
			this.context.put("show_simpan_add_htaam", "");
			this.context.put("show_batal_add_htaam", "");
			this.context.put("show_kemaskini_htaam", "");
			this.context.put("show_save_update_htaam", "");
			this.context.put("show_batal_update_htaam", "");
			this.context.put("show_hapus_htaam", "");
			this.context.put("show_kembali_htaam", "");
			this.context.put("show_button", "");
			this.context.put("show_htaa_update_table", "");
			this.context.put("show_htaa_add_table", "");
			this.context.put("listDaerahbyNegeri", "");
			this.context.put("listMukimbyDaerah", "");
			this.context.put("listnegeri", "");

			if ("Htaamview".equals(mode)) {
				/*
				 * int mati = Integer.parseInt(getParam("id_Permohonansimati"));
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */

				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");
				this.context.put("nowpast", "now");
			} else if ("Simpan_pilihan".equals(mode)) {
				simpanpilihanHTA(session, bolehsimpan);
			} else if ("Htaamviewdulu".equals(mode)) {
				/*
				 * int mati = Integer.parseInt(getParam("id_Permohonansimati"));
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");
				this.context.put("nowpast", "past");
			}

			else if ("add_new".equals(mode)) {
				/*
				 * int mati = Integer.parseInt(getParam("id_Permohonansimati"));
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				this.context.put("readmodedaerah", "readmode");
				this.context.put("readmodemukim", "readmode");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("add_new_harta", "yes");
				this.context.put("buttonhtaam", "Tambah");
			}

			else if ("masukkan".equals(mode)) {
				if (bolehsimpan.equals("yes")) {
					addHtaam(session);
				}
				String id = getParam("idPermohonan");
				String mati = getParam("id_Permohonansimati");
				/*
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");
				// logic_A.updateDataNilai(id,mati,(String)session.getAttribute("_ekptg_user_id"));
				// int mati = Integer.parseInt(getParam("id_Permohonansimati"));
				String idSimati = getParam("idSimati");
				logic_A.updateDataNilai17(id, idSimati, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));
			} else if ("negerichange".equals(mode)) {
				String mati = getParam("id_Permohonansimati");
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));
				/*
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				this.context.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("nopt", getParam("txtNoPTHtaam"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaa"));
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
				this.context.put("negeri", idnegeri);
				this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_add_table", "yes");
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				this.context.put("sekatan", getParam("txtSekatan"));
				this.context.put("syaratNyata", getParam("txtSyaratNyata"));
				
			} else if ("daerahchange".equals(mode)) {
				String mati = getParam("id_Permohonansimati");
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaam"));
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));
				/*
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				listmukim = logic_A.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				this.context.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
				this.context.put("idSimati", getParam("idSimati"));
				this.context.put("nopt", getParam("txtNoPTHtaam"));
				this.context.put("nilai_Hta_memohon",
						getParam("txtNilaiTarikhMohonHtaa"));
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
				this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("negeri", idnegeri);
				this.context.put("daerah", iddaerah);
				this.context.put("show_htaa_add_table", "yes");
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				this.context.put("sekatan", getParam("txtSekatan"));
				this.context.put("syaratNyata", getParam("txtSyaratNyata"));
			}

			else if ("negerichangeup".equals(mode)) {
				v = new Vector();
				String mati = getParam("id_Permohonansimati");
				/*
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamUp"));
				this.context.put("negeriup", idnegeri);
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				Hashtable h = new Hashtable();
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
				h.put("daerah", "");
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
				h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", getParam("txtSekatan"));
				h.put("syaratNyata", getParam("txtSyaratNyata"));
				
				v.addElement(h);
				this.context.put("listHTAid", v);
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_button", "yes");
				this.context.put("tambahharta", "yes");
				this.context.put("negeriup", idnegeri);
			} else if ("daerahchangeup".equals(mode)) {
				v = new Vector();
				String mati = getParam("id_Permohonansimati");
				/*
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamUp"));
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamUp"));
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				listmukim = logic_A.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				Hashtable h = new Hashtable();
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
				h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
				
				// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
				h.put("sekatan", getParam("txtSekatan"));
				h.put("syaratNyata", getParam("txtSyaratNyata"));
				
				v.addElement(h);
				this.context.put("listHTAid", v);
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("show_button", "yes");
				this.context.put("tambahharta", "yes");
			} else if ("getHtaam".equals(mode)) {
				String idhtaam = getParam("idhtaam");
				String mati = getParam("id_Permohonansimati");
				logic_internal.setDataHTAbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAid = logic_internal.getDataHTAbyIdHtaam();
				/*
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				this.context.put("idhtaam", idhtaam);
				this.context.put("listHTAid", listHTAid);
				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("tambahharta", "yes");
				this.context.put("readmodenegeri", "disabled");
				this.context.put("readmodedaerah", "disabled");
				this.context.put("readmodemukim", "disabled");
				this.context.put("readmode", "disabled");
			} else if ("getHtaamStatus".equals(mode)) {

				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");
				this.context.put("nowpast", "now");

				String id = getParam("idPermohonan");
				String id_sub = getParam("id_Suburusanstatusfail");
				String id_Fail = getParam("id_Fail");

				if (bolehsimpan.equals("yes")) {
					logic_A.htaamstatus17(session, id, (String) session
							.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
					// :::SUB
					// ID_STATUS 9
					// ID_SUBURUSAN 432

				}
				logic_A.kemaskiniSubUrusanStatusFail(session, id,
						(String) session.getAttribute("_ekptg_user_id"), "9",
						"432", id_Fail);

				/*
				 * int idhtaam = Integer.parseInt(getParam("idhtaamid")); int
				 * mati = Integer.parseInt(getParam("id_Permohonansimati")); int
				 * id = Integer.parseInt(getParam("idPermohonan")); int id_sub =
				 * Integer .parseInt(getParam("id_Suburusanstatusfail")); int
				 * id_Fail = Integer.parseInt(getParam("id_Fail"));
				 * 
				 * if (bolehsimpan.equals("yes")) { logic_A.htaamstatus17(id,
				 * (String) session .getAttribute("_ekptg_user_id"), id_sub,
				 * id_Fail); } logic_internal.setDataHTAbyIdHtaam(idhtaam);
				 * listHTAid = logic_internal.getDataHTAbyIdHtaam();
				 * 
				 * this.context.put("idhtaam", idhtaam);
				 * this.context.put("listHTAid", listHTAid);
				 * this.context.put("show_kemaskini_htaam", "yes");
				 * this.context.put("show_hapus_htaam", "yes");
				 * this.context.put("show_kembali_htaam", "yes");
				 * this.context.put("show_button", "yes");
				 * this.context.put("show_htaa_update_table", "yes");
				 * this.context.put("tambahharta", "yes");
				 * this.context.put("readmodenegeri", "disabled");
				 * this.context.put("readmodedaerah", "disabled");
				 * this.context.put("readmodemukim", "disabled");
				 * this.context.put("readmode", "disabled");
				 */
			} else if ("hapusHtaam".equals(mode)) {
				String idhtaam = getParam("idhtaamid");
				logic_internal.deleteHtaam(session, idhtaam,
						getParam("id_Permohonansimati"));
				/*
				 * int mati = Integer.parseInt(getParam("id_Permohonansimati"));
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");
				String id = getParam("idPermohonan");
				String idSimati = getParam("idSimati");
				logic_A.updateDataNilai17(id, idSimati, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));

			} else if ("kemaskiniHtaam".equals(mode)) {
				String flag_tukar_jenis_hta = getParam("flag_tukar_jenis_hta");
				if (flag_tukar_jenis_hta.equals("ADA")) {
					if (bolehsimpan.equals("yes")) {

						if (getParam("nama_skrin").equals("tiadahakmilik")) {
							updateHtaamX(session);
						} else {
							updateHtaam(session);
						}
					}
				}

				String mati = getParam("id_Permohonansimati");
				String idhtaam = getParam("idhtaamid");
				/*
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				logic_internal.setDataHTAbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAid = logic_internal.getDataHTAbyIdHtaam();
				Hashtable b = (Hashtable) listHTAid.get(0);
				String nn = b.get("negeri").toString();
				String dd = b.get("daerah").toString();
				if (nn != "" && nn != "0") {
					int idn = Integer.parseInt(nn);
					listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				}
				if (dd != "" && dd != "0") {
					int idd = Integer.parseInt(dd);
					listmukim = logic_A.getListMukimbyDaerah(idd);
					this.context.put("listMukimbyDaerah", listmukim);
				}
				this.context.put("idhtaam", idhtaam);
				this.context.put("listHTAid", listHTAid);
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("tambahharta", "yes");
			} else if ("simpanHtaam".equals(mode)) {
				String mati = getParam("id_Permohonansimati");
				if (bolehsimpan.equals("yes")) {
					updateHtaam(session);
				}
				String idhtaam = getParam("idhtaamid");
				logic_internal.setDataHTAbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAid = logic_internal.getDataHTAbyIdHtaam();
				this.context.put("idhtaam", idhtaam);
				this.context.put("listHTAid", listHTAid);
				/*
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA();
				 * this.context.put("listHTA",listHTA);
				 */
				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("tambahharta", "yes");
				this.context.put("readmodedaerah", "disabled");
				this.context.put("readmodenegeri", "disabled");
				this.context.put("readmodemukim", "disabled");
				this.context.put("readmode", "disabled");
				/*
				 * int id = Integer.parseInt(getParam("idPermohonan"));
				 * if(bolehsimpan.equals("yes")){
				 * logic_A.updateDataNilai(id,mati
				 * ,(String)session.getAttribute("_ekptg_user_id"));
				 * 
				 * }
				 */
				// int mati = Integer.parseInt(getParam("id_Permohonansimati"));
				String id = getParam("idPermohonan");
				String idSimati = getParam("idSimati");
				logic_A.updateDataNilai17(id, idSimati, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));
			}

			this.context.put("selectedTabatas", 1);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			context.put("DATEUTIL", new DateUtil());
			String id = getParam("idPermohonan");
			list = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			String mati = getParam("id_Permohonansimati");
			String idSimati = getParam("idSimati");
			logic_A.updateDataNilai17(id, idSimati, (String) session
					.getAttribute("_ekptg_user_id"),
					getParam("id_Permohonansimati"));

			logic_internal.setDataHTA(mati);
			listHTA = logic_internal.getDataHTA();
			this.context.put("listHTA", listHTA);

			String bkp = getParam("bkp");
			String lp = getParam("lp");
			String bpa = getParam("bpa");
			String lpa = getParam("lpa");

			// System.out.println("bpa" + bpa);
			// System.out.println("lpa" + lpa);

			// System.out.println("bkp" + bkp);
			// System.out.println("lp" + lp);

			logic_internal.setDataHTAdulu(mati, bkp, lp, bpa, lpa);

			Vector check_pilihan = logic_C.check_pilihan(mati);
			this.context.put("check_pilihan", check_pilihan);

			listHTAdulu = logic_internal.getDataHTAdulu();
			this.context.put("listHTAdulu", listHTAdulu);

			Vector check_pilihan_hta_ob = logic_C.check_pilihan_hta_ob(mati);
			this.context.put("check_pilihan_hta_ob", check_pilihan_hta_ob);
			logic_internal.setDataOBHTAdulu(mati, bkp, lp, bpa, lpa, "Y");
			listOBHTAdulu = logic_internal.getDataOBHTAdulu();
			this.context.put("listOBHTAdulu", listOBHTAdulu);

			String id1 = getParam("idSimati");
			int s1 = Integer.parseInt(getParam("no_subjaket"));
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			vm = "app/ppk/FrmPraPrmhnnSek17HTAAH.jsp";
		}

		else if ("HtaamX".equals(submit)) {

			this.context.put("idhtaamX", "");
			this.context.put("idhtaam", "");
			this.context.put("add_new", "");
			this.context.put("tambahbutton", "");
			this.context.put("kembalibutton", "");
			this.context.put("show_button", "");
			this.context.put("HtaamviewX1", "");
			this.context.put("HtaamviewX2", "");
			this.context.put("HtaamviewX3", "");
			this.context.put("negeri", "");
			this.context.put("daerah", "");
			this.context.put("mukim", "");
			this.context.put("readmode", "");
			this.context.put("readmodenegeri", "");
			this.context.put("readmodedaerah", "");
			this.context.put("readmodemukim", "");
			this.context.put("show_simpan_add_htaam", "");
			this.context.put("show_batal_add_htaam", "");
			this.context.put("show_kemaskini_htaam", "");
			this.context.put("show_save_update_htaam", "");
			this.context.put("show_batal_update_htaam", "");
			this.context.put("show_hapus_htaam", "");
			this.context.put("show_kembali_htaam", "");
			this.context.put("show_htaa_update_table", "");
			this.context.put("show_htaa_add_table", "");
			this.context.put("buttonhtaam", "");
			this.context.put("tambahbutton", "");
			this.context.put("kembalibutton", "");
			this.context.put("radio1", "");
			this.context.put("radio2", "");
			this.context.put("radio3", "");
			this.context.put("checked1", "");
			this.context.put("checked2", "");
			this.context.put("checked3", "");

			if ("HtaamviewX".equals(mode)) {
				String id = getParam("idPermohonan");
				String mati = getParam("id_Permohonansimati");
				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");
				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");
				this.context.put("nowpast", "now");
			} else if ("Simpan_pilihan".equals(mode)) {
				simpanpilihanHTAX(session, bolehsimpan);
			}

			else if ("HtaamviewXDulu".equals(mode)) {
				String id = getParam("idPermohonan");
				String mati = getParam("id_Permohonansimati");
				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");
				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");
				this.context.put("nowpast", "past");
			}

			if ("add_new".equals(mode)) {
				String id = getParam("idPermohonan");
				String mati = getParam("id_Permohonansimati");
				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */

				this.context.put("show_button", "yes");
				this.context.put("add_new", "yes");
				this.context.put("buttonhtaam", "Tambah");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_htaa_add_table", "yes");

			}
			if ("HtaamviewX1".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */
				this.context.put("HtaamviewX1", "yes");
				this.context.put("buttonhtaam", "Tambah");
				this.context.put("radio2", "yes");
				this.context.put("checked1", "checked");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");
			}

			if ("HtaamviewX2".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */

				this.context.put("radio3", "yes");
				this.context.put("checked2", "checked");
				this.context.put("buttonhtaam", "Tambah");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");
				this.context.put("HtaamviewX2", "yes");

			}

			if ("HtaamviewX3".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */

				this.context.put("buttonhtaam", "Tambah");
				this.context.put("checked3", "checked");
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");
				this.context.put("HtaamviewX3", "yes");

			}

			else if ("masukkanX".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				if (bolehsimpan.equals("yes")) {
					addHtaamX(session);
				}
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */
				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");

				String id = getParam("idPermohonan");
				// logic_A.updateDataNilai(id,mati,(String)session.getAttribute("_ekptg_user_id"));

				// int id = Integer.parseInt(getParam("idPermohonan"));
				String idSimati = getParam("idSimati");
				logic_A.updateDataNilai17(id, idSimati, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));

			}

			else if ("negerichangeX".equals(mode)) {
				String mati = getParam("id_Permohonansimati");

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					int idnegeri = Integer
							.parseInt(getParam("socNegeriHtaamX"));
					listnegeribydaerah = logic_A
							.getListDaerahbyNegeri(idnegeri);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
					this.context.put("negeri", idnegeri);
					this.context.put("bandarhta", "");
				} else {
					this.context.put("listDaerahbyNegeri", "");
					this.context.put("negeri", "");
					this.context.put("bandarhta", "");
				}

				if (getParam("socNegeriPemajuHtaamX") != ""
						&& getParam("socNegeriPemajuHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPemajuHtaamX")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriHtaamX")));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				String[] radioHtaamViewX = this.request
						.getParameterValues("radioHtaamViewX");
				int n = 0;
				for (int i = 0; i < radioHtaamViewX.length; i++) {
					if (radioHtaamViewX[i].equals("1")) {
						n = 1;
					} else if (radioHtaamViewX[i].equals("2")) {
						n = 2;
					} else if (radioHtaamViewX[i].equals("3")) {
						n = 3;
					}
				}
				String radioX = getParam("noradio");
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);
				if (n == 1) {
					this.context.put("radio2", "yes");
					this.context.put("checked1", "checked");

				}
				if (n == 2) {
					this.context.put("radio3", "yes");
					this.context.put("checked2", "checked");

				}
				if (n == 3) {

					this.context.put("checked3", "checked");
				}

				this.context.put("idSimati", getParam("idSimatiX"));
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
				this.context.put("pemilikan",
						getParam("socStatusPemilikanHtaamX"));
				this.context.put("jeniskepentingan",
						getParam("txtJenisKepentinganX"));
				this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_add_table", "yes");

				this.context.put("show_button", "yes");

			}

			else if ("negerichangepemajuX".equals(mode)) {
				String mati = getParam("id_Permohonansimati");

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					int idnegeri = Integer
							.parseInt(getParam("socNegeriHtaamX"));
					listnegeribydaerah = logic_A
							.getListDaerahbyNegeri(idnegeri);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
					this.context.put("negeri", idnegeri);
				} else {
					this.context.put("listDaerahbyNegeri", "");
					this.context.put("negeri", "");
				}

				if (getParam("socDaerahHtaamX") != ""
						&& getParam("socDaerahHtaamX") != "0") {
					int iddaerah = Integer
							.parseInt(getParam("socDaerahHtaamX"));
					listmukim = logic_A.getListMukimbyDaerah(iddaerah);
					this.context.put("listMukimbyDaerah", listmukim);
					this.context.put("daerah", iddaerah);
					this.context.put("mukim", getParam("socMukimHtaamX"));

				} else {
					this.context.put("listMukimbyDaerah", "");
					this.context.put("daerah", "");
					this.context.put("mukim", "");
				}

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriHtaamX")));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (getParam("socNegeriPemajuHtaamX") != ""
						&& getParam("socNegeriPemajuHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPemajuHtaamX")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				String[] radioHtaamViewX = this.request
						.getParameterValues("radioHtaamViewX");
				int n = 0;
				for (int i = 0; i < radioHtaamViewX.length; i++) {
					if (radioHtaamViewX[i].equals("1")) {
						n = 1;
					} else if (radioHtaamViewX[i].equals("2")) {
						n = 2;
					} else if (radioHtaamViewX[i].equals("3")) {
						n = 3;
					}
				}
				String radioX = getParam("noradio");
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */
				if (n == 1) {
					this.context.put("radio2", "yes");
					this.context.put("checked1", "checked");

				}
				if (n == 2) {
					this.context.put("radio3", "yes");
					this.context.put("checked2", "checked");

				}
				if (n == 3) {

					this.context.put("checked3", "checked");
				}

				this.context.put("idSimati", getParam("idSimatiX"));
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
				this.context.put("bandarpemaju", "");
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
				this.context.put("pemilikan",
						getParam("socStatusPemilikanHtaamX"));
				this.context.put("jeniskepentingan",
						getParam("txtJenisKepentinganX"));
				this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("show_button", "yes");

			}

			else if ("negerichangeupX".equals(mode)) {
				v = new Vector();
				String mati = getParam("id_Permohonansimati");
				this.context.put("listMukimbyDaerah", "");
				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					int idnegeri = Integer
							.parseInt(getParam("socNegeriHtaamX"));
					listnegeribydaerah = logic_A
							.getListDaerahbyNegeri(idnegeri);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
					this.context.put("negeri", idnegeri);
					this.context.put("bandarhta", "");
				} else {
					this.context.put("listDaerahbyNegeri", "");
					this.context.put("negeri", "");
					this.context.put("bandarhta", "");
				}

				if (getParam("socNegeriPemajuHtaamX") != ""
						&& getParam("socNegeriPemajuHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPemajuHtaamX")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriHtaamX")));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */

				Hashtable h = new Hashtable();
				h.put("idhta", getParam("idhtaamid"));

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					int idnegeri = Integer
							.parseInt(getParam("socNegeriHtaamX"));
					h.put("negeri", idnegeri);
				} else {
					h.put("negeri", "");
				}

				h.put("daerah", "");
				h.put("mukim", "");
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
				h.put("bandarhta", "");
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
				h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
				h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
				h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
				v.addElement(h);
				this.context.put("listHTAXid", v);

				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");
				// this.context.put("negeri", idnegeri);
				this.context.put("tambahbutton", "yes");
				this.context.put("show_button", "yes");
				this.context.put("idhtaam", getParam("idhtaamid"));

			}

			else if ("negerichangepemajuupX".equals(mode)) {
				v = new Vector();
				String mati = getParam("id_Permohonansimati");

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					int idnegeri = Integer
							.parseInt(getParam("socNegeriHtaamX"));
					listnegeribydaerah = logic_A
							.getListDaerahbyNegeri(idnegeri);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
					this.context.put("negeri", idnegeri);
				} else {
					this.context.put("listDaerahbyNegeri", "");
					this.context.put("negeri", "");
				}

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriHtaamX")));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (getParam("socNegeriPemajuHtaamX") != ""
						&& getParam("socNegeriPemajuHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPemajuHtaamX")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				/*
				 * 
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */

				Hashtable h = new Hashtable();
				h.put("idhta", getParam("idhtaamid"));

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					int idnegeri = Integer
							.parseInt(getParam("socNegeriHtaamX"));
					h.put("negeri", idnegeri);
				} else {
					h.put("negeri", "");
				}

				if (getParam("socDaerahHtaamX") != ""
						&& getParam("socDaerahHtaamX") != "0") {
					int iddaerah = Integer
							.parseInt(getParam("socDaerahHtaamX"));
					listmukim = logic_A.getListMukimbyDaerah(iddaerah);
					this.context.put("listMukimbyDaerah", listmukim);
					h.put("daerah", iddaerah);
					h.put("mukim", getParam("socMukimHtaamX"));
				} else {
					this.context.put("listMukimbyDaerah", "");
					h.put("daerah", "");
					h.put("mukim", "");
				}

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
				h.put("bandarpemaju", "");
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
				h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
				h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
				h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
				v.addElement(h);
				this.context.put("listHTAXid", v);

				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");
				// this.context.put("negeri", idnegeri);
				this.context.put("tambahbutton", "yes");
				this.context.put("show_button", "yes");
				this.context.put("idhtaam", getParam("idhtaamid"));

			}

			else if ("daerahchangeupX".equals(mode)) {
				v = new Vector();
				String mati = getParam("id_Permohonansimati");
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
				listmukim = logic_A.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriHtaamX")));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				if (getParam("socNegeriPemajuHtaamX") != ""
						&& getParam("socNegeriPemajuHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPemajuHtaamX")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				Hashtable h = new Hashtable();
				// h.put("idhta",getParam("idhtaamXid"));
				h.put("idhta", getParam("idhtaamid"));
				h.put("negeri", idnegeri);
				h.put("daerah", iddaerah);
				h.put("mukim", "");
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
				h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
				h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
				h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
				v.addElement(h);
				this.context.put("listHTAXid", v);

				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("negeri", idnegeri);
				this.context.put("daerah", iddaerah);
				this.context.put("tambahbutton", "yes");
				this.context.put("show_button", "yes");
				this.context.put("idhtaam", getParam("idhtaamid"));

			}

			else if ("getHtaamStatus".equals(mode)) {
				String id = getParam("idPermohonan");
				String mati = getParam("id_Permohonansimati");
				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");
				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");
				this.context.put("nowpast", "now");

				String id_sub = getParam("id_Suburusanstatusfail");
				String id_Fail = getParam("id_Fail");
				if (bolehsimpan.equals("yes")) {
					// logic_A.htaamstatus17(id, (String)
					// session.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
					// :::SUB
					// ID_STATUS 9
					// ID_SUBURUSAN 432

				}
				logic_A.kemaskiniSubUrusanStatusFail(session, id,
						(String) session.getAttribute("_ekptg_user_id"), "9",
						"432", id_Fail);

				/*
				 * int mati = Integer.parseInt(getParam("id_Permohonansimati"));
				 * int idhtaam = Integer.parseInt(getParam("idhtaamXid"));
				 * logic_internal.setDataHTAXbyIdHtaam(idhtaam); listHTAXid =
				 * logic_internal.getDataHTAXbyIdHtaam();
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX(); this.context.put("listHTAX",
				 * listHTAX); listmukim = logic_A.getListMukim();
				 * this.context.put("listMukimbyDaerah", listmukim); int id =
				 * Integer.parseInt(getParam("idPermohonan")); int id_sub =
				 * Integer .parseInt(getParam("id_Suburusanstatusfail")); int
				 * id_Fail = Integer.parseInt(getParam("id_Fail")); if
				 * (bolehsimpan.equals("yes")) { logic_A.htaamstatus17(id,
				 * (String) session .getAttribute("_ekptg_user_id"), id_sub,
				 * id_Fail); } this.context.put("tambahbutton", "yes");
				 * this.context.put("show_button", "yes");
				 * this.context.put("idhtaamX", idhtaam);
				 * this.context.put("idhtaam", idhtaam);
				 * this.context.put("listHTAXid", listHTAXid);
				 * this.context.put("readmodenegeri", "disabled");
				 * this.context.put("readmodedaerah", "disabled");
				 * this.context.put("readmodemukim", "disabled");
				 * this.context.put("readmode", "disabled");
				 * this.context.put("show_kemaskini_htaam", "yes");
				 * this.context.put("show_hapus_htaam", "yes");
				 * this.context.put("show_kembali_htaam", "yes");
				 * this.context.put("show_htaa_update_table", "yes");
				 */
			}

			else if ("getHtaamX".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				String idhtaam = getParam("idhtaamXid");
				logic_internal.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic_internal.getDataHTAXbyIdHtaam();
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */
				listmukim = logic_A.getListMukim();
				this.context.put("listMukimbyDaerah", listmukim);

				Hashtable m = (Hashtable) listHTAXid.get(0);
				if (m.get("negeripemaju").toString() != ""
						&& m.get("negeripemaju").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(m.get("negeripemaju").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				Hashtable k = (Hashtable) listHTAXid.get(0);
				if (k.get("negeri").toString() != ""
						&& k.get("negeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(k.get("negeri").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				this.context.put("tambahbutton", "yes");
				this.context.put("show_button", "yes");
				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);
				this.context.put("listHTAXid", listHTAXid);
				this.context.put("readmodenegeri", "disabled");
				this.context.put("readmodedaerah", "disabled");
				this.context.put("readmodemukim", "disabled");
				this.context.put("readmode", "disabled");
				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");

			}

			else if ("getHtaamXstatus".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				String idhtaam = getParam("idhtaamXid");

				String id = getParam("idPermohonan");
				String id_sub = getParam("id_Suburusanstatusfail");
				String id_Fail = getParam("id_Fail");
				if (bolehsimpan.equals("yes")) {
					// logic_A.htaamstatus17(id, (String)
					// session.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
					// :::SUB
					// ID_STATUS 9
					// ID_SUBURUSAN 432

				}
				logic_A.kemaskiniSubUrusanStatusFail(session, id,
						(String) session.getAttribute("_ekptg_user_id"), "9",
						"432", id_Fail);

				logic_internal.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic_internal.getDataHTAXbyIdHtaam();
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */
				listmukim = logic_A.getListMukim();
				this.context.put("listMukimbyDaerah", listmukim);

				this.context.put("tambahbutton", "yes");
				this.context.put("show_button", "yes");
				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);
				this.context.put("listHTAXid", listHTAXid);
				this.context.put("readmodenegeri", "disabled");
				this.context.put("readmodedaerah", "disabled");
				this.context.put("readmodemukim", "disabled");
				this.context.put("readmode", "disabled");
				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");

			}

			else if ("batalHtaamX".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				String idhtaam = getParam("idhtaamXid");
				logic_internal.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic_internal.getDataHTAXbyIdHtaam();
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */
				this.context.put("listHTAXid", listHTAXid);
				Hashtable m = (Hashtable) listHTAXid.get(0);
				if (m.get("negeripemaju").toString() != ""
						&& m.get("negeripemaju").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(m.get("negeripemaju").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				Hashtable k = (Hashtable) listHTAXid.get(0);
				if (k.get("negeri").toString() != ""
						&& k.get("negeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(k.get("negeri").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}
				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);
				this.context.put("idhtaam", getParam("idhtaamid"));
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("tambahbutton", "yes");
				this.context.put("show_button", "yes");

			} else if ("hapusHtaamX".equals(mode)) {
				String idhtaam = getParam("idhtaamXid");
				String mati = getParam("id_Permohonansimati");
				logic_internal.deleteHtaam(session, idhtaam,
						getParam("id_Permohonansimati"));
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */
				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");
				String id = getParam("idPermohonan");
				String idSimati = getParam("idSimati");
				logic_A.updateDataNilai17(id, idSimati, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));
			} else if ("kemaskiniHtaamX".equals(mode)) {

				String flag_tukar_jenis_hta = getParam("flag_tukar_jenis_hta");
				if (flag_tukar_jenis_hta.equals("TIADA")) {
					if (bolehsimpan.equals("yes")) {

						if (getParam("nama_skrin").equals("adahakmilik")) {
							updateHtaam(session);
						} else {
							updateHtaamX(session);
						}
					}
				}

				String mati = getParam("id_Permohonansimati");
				String idhtaam = getParam("idhtaamXid");
				logic_internal.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic_internal.getDataHTAXbyIdHtaam();
				this.context.put("listHTAXid", listHTAXid);
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */
				Hashtable m = (Hashtable) listHTAXid.get(0);
				if (m.get("negeripemaju").toString() != ""
						&& m.get("negeripemaju").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(m.get("negeripemaju").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				Hashtable k = (Hashtable) listHTAXid.get(0);
				if (k.get("negeri").toString() != ""
						&& k.get("negeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(k.get("negeri").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				Hashtable b = (Hashtable) listHTAXid.get(0);
				String nn = b.get("negeri").toString();
				String dd = b.get("daerah").toString();

				// System.out.println("negeri :" + nn + "daerah :" + dd);

				if (nn != "" && nn != "0") {
					int idn = Integer.parseInt(nn);

					listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				}

				if (dd != "" && dd != "0") {
					int idd = Integer.parseInt(dd);
					listmukim = logic_A.getListMukimbyDaerah(idd);
					this.context.put("listMukimbyDaerah", listmukim);

				}
				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);
				this.context.put("show_save_update_htaam", "yes");
				this.context.put("show_batal_update_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("tambahbutton", "yes");
				this.context.put("show_button", "yes");

			} else if ("simpanHtaamX".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				if (bolehsimpan.equals("yes")) {
					if (bolehsimpan.equals("yes")) {
						updateHtaamX(session);
					}
				}
				// int idhtaam=Integer.parseInt(getParam("idhtaamXid"));
				String idhtaam = getParam("idhtaamid");
				// h.put("idhta",getParam("idhtaamid"));

				logic_internal.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic_internal.getDataHTAXbyIdHtaam();
				this.context.put("listHTAXid", listHTAXid);

				Hashtable m = (Hashtable) listHTAXid.get(0);
				if (m.get("negeripemaju").toString() != ""
						&& m.get("negeripemaju").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(m.get("negeripemaju").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				Hashtable k = (Hashtable) listHTAXid.get(0);
				if (k.get("negeri").toString() != ""
						&& k.get("negeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(k.get("negeri").toString()));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */
				this.context.put("idhtaamX", idhtaam);
				this.context.put("idhtaam", idhtaam);

				this.context.put("readmodenegeri", "disabled");
				this.context.put("readmodedaerah", "disabled");
				this.context.put("readmodemukim", "disabled");
				this.context.put("readmode", "disabled");
				this.context.put("show_kemaskini_htaam", "yes");
				this.context.put("show_hapus_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_update_table", "yes");
				this.context.put("radio2", "yes");
				this.context.put("checked1", "checked");
				this.context.put("tambahbutton", "yes");
				this.context.put("show_button", "yes");
				this.context.put("idhtaam", getParam("idhtaamid"));

				String id = getParam("idPermohonan");
				// logic_A.updateDataNilai(id,mati,(String)session.getAttribute("_ekptg_user_id"));

				// int id = Integer.parseInt(getParam("idPermohonan"));
				String idSimati = getParam("idSimati");
				logic_A.updateDataNilai17(id, idSimati, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));

			} else if ("daerahchangeX".equals(mode)) {
				String mati = getParam("id_Permohonansimati");
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
				listmukim = logic_A.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				/*
				 * logic_internal.setDataHTAX(mati); listHTAX =
				 * logic_internal.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX);
				 */

				if (getParam("socNegeriPemajuHtaamX") != ""
						&& getParam("socNegeriPemajuHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriPemajuHtaamX")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}

				if (getParam("socNegeriHtaamX") != ""
						&& getParam("socNegeriHtaamX") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriHtaamX")));
					this.context.put("listBandarSuratbyNegeri", s3);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

				String[] radioHtaamViewX = this.request
						.getParameterValues("radioHtaamViewX");
				int n = 0;
				for (int i = 0; i < radioHtaamViewX.length; i++) {
					if (radioHtaamViewX[i].equals("1")) {
						n = 1;
					} else if (radioHtaamViewX[i].equals("2")) {
						n = 2;
					} else if (radioHtaamViewX[i].equals("3")) {
						n = 3;
					}
				}

				if (n == 1) {
					this.context.put("radio2", "yes");
					this.context.put("checked1", "checked");
				}
				if (n == 2) {
					this.context.put("radio3", "yes");
					this.context.put("checked2", "checked");
				}
				if (n == 3) {
					this.context.put("checked3", "checked");
				}

				this.context.put("idSimati", getParam("idSimatiX"));
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
				this.context.put("pemilikan",
						getParam("socStatusPemilikanHtaamX"));
				this.context.put("jeniskepentingan",
						getParam("txtJenisKepentinganX"));
				this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_add_table", "yes");
				this.context.put("negeri", idnegeri);
				this.context.put("daerah", iddaerah);
				this.context.put("show_button", "yes");

			}
			this.context.put("selectedTabatas", 1);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 1);
			String id = getParam("idPermohonan");
			String idSimati = getParam("idSimati");
			list = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);
			String mati = getParam("id_Permohonansimati");
			// logic_A.updateDataNilai(id,mati,(String)session.getAttribute("_ekptg_user_id"));
			logic_internal.setDataHTAX(mati);
			listHTAX = logic_internal.getDataHTAX();
			this.context.put("listHTAX", listHTAX);

			String bkp = getParam("bkp");
			String lp = getParam("lp");
			String bpa = getParam("bpa");
			String lpa = getParam("lpa");

			Vector check_pilihan = logic_C.check_pilihan(mati);
			this.context.put("check_pilihan", check_pilihan);

			logic_internal.setDataHTAXdulu_Pilihan(mati, bkp, lp, bpa, lpa);
			listHTAXdulu = logic_internal.getDataHTAXdulu_Pilihan();
			this.context.put("listHTAXdulu", listHTAXdulu);

			Vector check_pilihan_hta_ob = logic_C.check_pilihan_hta_ob(mati);
			this.context.put("check_pilihan_hta_ob", check_pilihan_hta_ob);
			logic_internal.setDataOBHTAdulu(mati, bkp, lp, bpa, lpa, "T");
			listOBHTAdulu = logic_internal.getDataOBHTAdulu();
			this.context.put("listOBHTAdulu", listOBHTAdulu);

			// int id = Integer.parseInt(getParam("idPermohonan"));

			logic_A.updateDataNilai17(id, idSimati, (String) session
					.getAttribute("_ekptg_user_id"),
					getParam("id_Permohonansimati"));

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			String id1 = getParam("idSimati");
			int s1 = Integer.parseInt(getParam("no_subjaket"));
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			context.put("DATEUTIL", new DateUtil());
			vm = "app/ppk/frmPraPrmhnnSek17HTATH.jsp";

		}

		else if ("harta_alih".equals(submit)) {

			this.context.put("showbuttonkembali", "");
			this.context.put("showbuttontambah", "");
			this.context.put("barula", "");
			this.context.put("showadd", "0");
			this.context.put("tutup", "");
			this.context.put("readmodenegeri", "");
			this.context.put("readmodedaerah", "");
			this.context.put("socJenisHa", "0");
			this.context.put("readmode", "");
			this.context.put("show_kemaskini_button", "");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");
			this.context.put("EventStatus", "");
			this.context.put("listDaerahbyNegeri", "");
			this.context.put("id", "");
			this.context.put("id2", "");
			this.context.put("id1", "");
			this.context.put("listnegeri", "");
			this.context.put("idha", "");

			String socJenisha = "0";

			if ("view_harta_alih".equals(mode)) {

				this.context.put("showbuttonkembali", "yes");
				this.context.put("showbuttontambah", "yes");
				this.context.put("EventStatus", 1);
				this.context.put("nowpast", "now");

			}
			if ("view_harta_alih_dulu".equals(mode)) {

				this.context.put("showbuttonkembali", "yes");
				this.context.put("showbuttontambah", "yes");
				this.context.put("EventStatus", 1);
				this.context.put("nowpast", "past");

			}

			else if ("Simpan_pilihan".equals(mode)) {
				simpanpilihanHA(session, bolehsimpan);

			}

			else if ("tambah_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String eventstatus = getParam("eventStatus");

				socJenisha = getParam("socJenisHartaAlih");
				this.context.put("socJenisHa", socJenisha);

				this.context.put("negeri", "");
				this.context.put("nama_saham", "");
				this.context.put("butiran", "");
				this.context.put("daerah", "");
				this.context.put("socJenisHa", getParam("socJenisHartaAlih"));
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("bilunit", "");
				this.context.put("nilaiseunit", "");
				this.context.put("agensi", "");
				this.context.put("txtAlamat1", getParam("txtAlamat1"));
				this.context.put("txtAlamat2", getParam("txtAlamat2"));
				this.context.put("txtAlamat3", getParam("txtAlamat3"));

				this.context.put("txtPoskod", getParam("txtPoskod"));
				this.context.put("negeri", getParam("socNegeriHtaam"));
				this.context.put("daerah", getParam("socDaerahHtaam"));
				this.context.put("bhgnmati1", getParam("txtBhgnSimati1"));
				this.context.put("bhgnmati2", getParam("txtBhgnSimati2"));
				this.context.put("nilaitarikhmati",
						getParam("txtNilaiTarikhMati"));
				this.context.put("nilaitarikhmohon",
						getParam("txtNilaiTarikhMohon"));
				this.context.put("catatan", getParam("txtCatatan"));
				this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				String jh = "0";
				jh = getParam("socJenisHartaAlih");
				int jj = 0;
				if (jh != null && jh != "" && jh != "0") {
					jj = Integer.parseInt(jh);
				} else {
					jj = 0;
				}

				if (jj > 0) {
					this.context.put("showadd", "1");
				} else {
					this.context.put("showadd", "0");

				}

				int idne = 0;
				if (!"".equals(getParam("socNegeriHtaam"))
						&& !"0".equals(getParam("socNegeriHtaam"))) {
					idne = Integer.parseInt(getParam("socNegeriHtaam"));

				} else {
					idne = 0;

				}

				if (jh != "0" && jh != "") {
					if (idne != 0) {
						listnegeribydaerah = logic_A
								.getListDaerahbyNegeri(idne);
						this.context.put("listDaerahbyNegeri",
								listnegeribydaerah);
					} else {
						this.context.put("readmodedaerah", "");
						this.context.put("listDaerahbyNegeri", "");
					}

				}

				else {

					this.context.put("readmodedaerah", "");
					this.context.put("listDaerahbyNegeri", "");
				}
				this.context.put("EventStatus", eventstatus);
			}

			else if ("tambah_harta_baru".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");

				this.context.put("EventStatus", 0);
				this.context.put("barula", "yes");
				this.context.put("id", id);
				this.context.put("id1", id1);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("socJenisHa", getParam("socJenisHartaAlih"));
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("bilunit", "");
				this.context.put("nilaiseunit", "");
				this.context.put("agensi", "");
				this.context.put("txtAlamat1", "");
				this.context.put("txtAlamat2", "");
				this.context.put("txtAlamat3", "");
				this.context.put("nama_saham", "");
				this.context.put("txtPoskod", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("bhgnmati1", "");
				this.context.put("bhgnmati2", "");
				this.context.put("nilaitarikhmati", "");
				this.context.put("nilaitarikhmohon", "");
				this.context.put("catatan", "");
				this.context.put("readmodedaerah", "");
				this.context.put("butiran", "");
				this.context.put("FLAG_DAFTAR", "");

			}

			// //tanda////

			else if ("simpan_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String eventstatus = getParam("eventStatus");
				if (bolehsimpan.equals("yes")) {
					addHa(session);
				}

				this.context.put("showbuttonkembali", "yes");
				this.context.put("showbuttontambah", "yes");
				this.context.put("tutup", "yes");
				this.context.put("EventStatus", eventstatus);
				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);

				// int id = Integer.parseInt(getParam("idPermohonan"));
				String idSimati = getParam("idSimati");
				logic_A.updateDataNilai17(id, idSimati, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));

			} else if ("batal_harta".equals(mode)) {

				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String idha = getParam("idha");
				String eventstatus = getParam("eventStatus");

				logic_A.setSelectedDataHa(mati, idha);
				selectedppkha = logic_A.getSelectedDataHa();
				this.context.put("DataHa", selectedppkha);

				this.context.put("showbuttontambah", "yes");
				this.context.put("showadd", "1");
				this.context.put("EventStatus", eventstatus);
				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);

			} else if ("edit_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String eventstatus = getParam("eventStatus");
				String idha = "";
				idha = getParam("idha");

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);
				this.context.put("idha", idha);

				logic_A.setSelectedDataHa(mati, idha);
				selectedppkha = logic_A.getSelectedDataHa();
				this.context.put("DataHa", selectedppkha);

				Hashtable b = (Hashtable) selectedppkha.get(0);

				String nn = b.get("negeri").toString();

				if (nn != "" && nn != "0") {
					int idn = Integer.parseInt(nn);

					listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				} else {
					this.context.put("listDaerahbyNegeri", "");

				}

				// System.out.println("selectedppkha" + selectedppkha);

				this.context.put("showbuttontambah", "yes");
				this.context.put("showadd", "1");
				this.context.put("tutup", "yes");
				this.context.put("EventStatus", eventstatus);

			} else if ("kemaskini_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String idha = "";
				idha = getParam("idha");

				logic_A.setSelectedDataHa(mati, idha);
				selectedppkha = logic_A.getSelectedDataHa();
				this.context.put("DataHa", selectedppkha);

				Hashtable b = (Hashtable) selectedppkha.get(0);

				String nn = b.get("negeri").toString();

				if (nn != "" && nn != "0") {
					int idn = Integer.parseInt(nn);

					listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				} else {
					this.context.put("readmodedaerah", "");
					this.context.put("listDaerahbyNegeri", "");
				}

				this.context.put("id", id);
				this.context.put("id2", id2);
				this.context.put("idha", idha);
				this.context.put("tutup", "yes");
				this.context.put("EventStatus", 3);
				this.context.put("id1", id1);
				this.context.put("showbuttontambah", "yes");
				this.context.put("showadd", "1");

			} else if ("update_harta".equals(mode)) {
				if (bolehsimpan.equals("yes")) {
					updateHa(session);
				}

				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String eventstatus = getParam("eventStatus");
				String idha = getParam("idha");

				logic_A.setSelectedDataHa(mati, idha);
				selectedppkha = logic_A.getSelectedDataHa();
				this.context.put("DataHa", selectedppkha);

				Hashtable b = (Hashtable) selectedppkha.get(0);

				String nn = b.get("negeri").toString();

				if (nn != "" && nn != "0") {
					int idn = Integer.parseInt(nn);

					listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				}

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);
				this.context.put("idha", idha);
				this.context.put("tutup", "yes");
				this.context.put("EventStatus", eventstatus);
				this.context.put("showbuttontambah", "yes");
				this.context.put("showadd", "1");

				// int id = Integer.parseInt(getParam("idPermohonan"));
				String idSimati = getParam("idSimati");
				logic_A.updateDataNilai17(id, idSimati, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));

			} else if ("hapus_harta".equals(mode)) {

				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String eventstatus = getParam("eventStatus");
				String idha = getParam("idha");
				delete_mode_ppkha(session, mati, idha);

				// int id = Integer.parseInt(getParam("idPermohonan"));
				String idSimati = getParam("idSimati");
				logic_A.updateDataNilai17(id, idSimati, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));

				this.context.put("showbuttonkembali", "yes");
				this.context.put("showbuttontambah", "yes");
				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("idha", idha);
				this.context.put("EventStatus", eventstatus);

			} else if ("negerichange".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String eventstatus = getParam("eventStatus");

				int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));

				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				this.context.put("socJenisHa", getParam("socJenisHartaAlih"));
				// this.context.put("socJenisHa", getParam("socJenisHa"));
				this.context.put("norujukan", getParam("txtNoRujukan"));
				this.context.put("nosijil", getParam("txtNoSijil"));
				this.context.put("bilunit", getParam("txtBilUnit"));
				this.context.put("nilaiseunit", getParam("txtNilaiSeunit"));
				this.context.put("agensi", getParam("txtAgensi"));
				this.context.put("txtAlamat1", getParam("txtAlamat1"));
				this.context.put("txtAlamat2", getParam("txtAlamat2"));
				this.context.put("txtAlamat3", getParam("txtAlamat3"));
				this.context.put("txtPoskod", getParam("txtPoskod"));
				this.context.put("negeri", getParam("socNegeriHtaam"));
				this.context.put("daerah", getParam("socDaerahHtaam"));
				this.context.put("bhgnmati1", getParam("txtBhgnSimati1"));
				this.context.put("bhgnmati2", getParam("txtBhgnSimati2"));
				this.context.put("nama_saham", getParam("nama_saham"));
				this.context.put("butiran", getParam("butiran"));
				this.context.put("nilaitarikhmati",
						getParam("txtNilaiTarikhMati"));
				this.context.put("nilaitarikhmohon",
						getParam("txtNilaiTarikhMohon"));
				this.context.put("catatan", getParam("txtCatatan"));
				this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				this.context.put("EventStatus", 0);

				this.context.put("negeri", idnegeri);
				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("showadd", "1");

			}

			else if ("negerichangeU".equals(mode)) {

				listabc = new Vector();

				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String eventstatus = getParam("eventStatus");
				this.context.put("socJenisHa", getParam("socJenisHartaAlih"));

				int idnegeri = 0;
				if (!"".equals(getParam("socNegeriHtaam"))
						&& !"0".equals(getParam("socNegeriHtaam"))) {
					idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));
				} else {
					idnegeri = 0;
				}
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);

				Hashtable h;
				h = new Hashtable();

				String idha = getParam("idha");

				h.put("negeri", idnegeri);
				h.put("daerah", "");
				h.put("socJenisHa", getParam("socJenisHartaAlih"));
				h.put("norujukan", getParam("txtNoRujukan"));
				h.put("nosijil", getParam("txtNoSijil"));
				h.put("bilunit", getParam("txtBilUnit"));
				h.put("jenama", getParam("txtAgensi"));

				h.put("nilaiseunit", getParam("txtNilaiSeunit"));
				h.put("agensi", getParam("txtAgensi"));
				h.put("alamat1", getParam("txtAlamat1"));
				h.put("alamat2", getParam("txtAlamat2"));
				h.put("alamat3", getParam("txtAlamat3"));
				h.put("poskod", getParam("txtPoskod"));
				h.put("nama_saham", getParam("nama_saham"));
				// h.put("negeri", idnegeri);
				// h.put("daerah", getParam("socDaerahHtaam"));
				h.put("basimati", getParam("txtBhgnSimati1"));
				h.put("bbsimati", getParam("txtBhgnSimati2"));
				h.put("nilaiha_tarikhmati", getParam("txtNilaiTarikhMati"));
				h.put("nilaiha_tarikhmohon", getParam("txtNilaiTarikhMohon"));
				h.put("catatan", getParam("txtCatatan"));
				h.put("butiran", getParam("butiran"));
				h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				listabc.addElement(h);

				this.context.put("DataHa", listabc);

				this.context.put("showbuttontambah", "yes");
				this.context.put("tutup", "yes");
				this.context.put("EventStatus", 3);
				this.context.put("id", id);
				this.context.put("id2", id2);
				this.context.put("idha", idha);
				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("showadd", "1");
			}
			context.put("DATEUTIL", new DateUtil());
			String id = getParam("idPermohonan");
			this.context.put("selectedTabatas", 2);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			this.context.put("selectLevelTab", 0);
			list = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("View", list);
			headerppk_baru(session, id, "Y", "", "T");
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			String id1 = getParam("idSimati");
			int s1 = Integer.parseInt(getParam("no_subjaket"));
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			String idSimati = getParam("idSimati");
			String mati = getParam("id_Permohonansimati");

			logic_A.setDataHa(mati);
			listppkha = logic_A.getDataHa();
			this.context.put("listHa", listppkha);

			int countList = listppkha.size();
			this.context.put("jumList", countList);

			String bkp = getParam("bkp");
			String lp = getParam("lp");
			String bpa = getParam("bpa");
			String lpa = getParam("lpa");

			Vector check_pilihan_ha = logic_C.check_pilihan_ha(mati);
			this.context.put("check_pilihan_ha", check_pilihan_ha);

			logic_A.setDataHaDulu_Pilihan(mati, bkp, lp, bpa, lpa);
			listppkhadulu = logic_A.getDataHaDulu_Pilihan();
			this.context.put("listHadulu", listppkhadulu);

			Vector check_pilihan_ha_ob = logic_C.check_pilihan_ha_ob(mati);
			this.context.put("check_pilihan_ha_ob", check_pilihan_ha_ob);
			logic_internal.setDataOBHAdulu(mati, bkp, lp, bpa, lpa, "T");
			listOBHAdulu = logic_internal.getDataOBHAdulu();
			this.context.put("listOBHAdulu", listOBHAdulu);

			logic_A.updateDataNilai17(id, idSimati, (String) session
					.getAttribute("_ekptg_user_id"),
					getParam("id_Permohonansimati"));

			logic_A.setSumDataHa(mati);
			sumppkha = logic_A.getSumDataHa();
			this.context.put("jumppkha", sumppkha);

			logic_B.setDataHta(id);
			listppkhta = logic_B.getDataHta();
			this.context.put("listhta2", listppkhta);
			int countListhta = listppkhta.size();
			this.context.put("jumListhta2", countListhta);

			logic_B.setDataHa(id);
			listppkha2 = logic_B.getHa2();
			this.context.put("listHa2", listppkha2);
			int countListha2 = listppkha2.size();
			this.context.put("jumListHa2", countListha2);

			logic_A.setOverallSum(mati);
			overallnilai = logic_A.getOverallSum();
			this.context.put("overall", overallnilai);

			logic_A.setOverallSumMati(mati);
			overallnilaimati = logic_A.getOverallSumMati();
			this.context.put("overallmati", overallnilaimati);

			listJenisha = logic_A.getJenisHa();
			this.context.put("ViewJenisHa", listJenisha);

			vm = "app/ppk/frmPraPrmhnnSek17HA.jsp";
		}

		else if ("nilai_harta".equals(submit)) {
			this.context.put("id", "");
			this.context.put("id2", "");
			this.context.put("id1", "");
			this.context.put("readmode", "");
			this.context.put("show_kemaskini_button", "");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");
			this.context.put("EventStatus", "");
			this.context.put("tutup", "");

			if ("view_nilai_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");

				this.context.put("id", id);
				this.context.put("id2", id2);
				this.context.put("id1", id1);

				logic_A.setSumDataHta(mati);
				sumppkhta = logic_A.getSumDataHta();
				this.context.put("jumppkhta", sumppkhta);

				//
				logic_A.setSumDataHtaDulu(mati);
				sumppkhtadulu = logic_A.getSumDataHtaDulu();
				this.context.put("sumppkhtadulu", sumppkhtadulu);

				logic_A.setOverallSumHta(mati);
				sumoverallppkhta = logic_A.getOverallSumHta();
				this.context.put("jumoverallppkhta", sumoverallppkhta);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);

				logic_A.setDataHaDulu(mati);
				listppkhadulu = logic_A.getDataHaDulu();
				this.context.put("listHaDulu", listppkhadulu);

				/*
				 * String bkp = getParam("bkp"); String lp = getParam("lp");
				 * String bpa = getParam("bpa"); String lpa = getParam("lpa");
				 * 
				 * Vector check_pilihan_ha = logic_C.check_pilihan_ha(mati);
				 * this.context.put("check_pilihan_ha",check_pilihan_ha);
				 * 
				 * 
				 * logic_A.setDataHaDulu_Pilihan(mati, bkp, lp, bpa, lpa);
				 * listppkhadulu = logic_A.getDataHaDulu_Pilihan();
				 * this.context.put("listHadulu", listppkhadulu);
				 */

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				logic_B.setDataHta(id);
				listppkhta = logic_B.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				logic_B.setDataHa(id);
				listppkha2 = logic_B.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				logic_B.setDataHtath(id);
				listppkhtath = logic_B.getHtath();
				this.context.put("listHtaht2", listppkhtath);
				int countListhtath = listppkhtath.size();
				this.context.put("jumListHtaht", countList);

				this.context
						.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
				this.context.put("readmode", "disabled");
				this.context.put("show_kemaskini_button", "yes");

			}

			else if ("kemaskini_nilai_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");

				this.context.put("EventStatus", 4);
				this.context.put("id", id);
				this.context.put("id1", id1);

				logic_A.setSumDataHta(mati);
				sumppkhta = logic_A.getSumDataHta();
				this.context.put("jumppkhta", sumppkhta);

				logic_A.setSumDataHtaDulu(mati);
				sumppkhtadulu = logic_A.getSumDataHtaDulu();
				this.context.put("sumppkhtadulu", sumppkhtadulu);

				logic_A.setOverallSumHta(mati);
				sumoverallppkhta = logic_A.getOverallSumHta();
				this.context.put("jumoverallppkhta", sumoverallppkhta);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);

				logic_A.setDataHaDulu(mati);
				listppkhadulu = logic_A.getDataHaDulu();
				this.context.put("listHaDulu", listppkhadulu);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				logic_B.setDataHta(id);
				listppkhta = logic_B.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				logic_B.setDataHa(id);
				listppkha2 = logic_B.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				logic_B.setDataHtath(id);
				listppkhtath = logic_B.getHtath();
				this.context.put("listHtaht2", listppkhtath);
				int countListhtath = listppkhtath.size();
				this.context.put("jumListHtaht", countList);

			} else if ("hantar_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String idstatus = getParam("idstatus");
				// if (bolehsimpan.equals("yes")) {
				updateStatus17(session);
				// }

				this.context.put("EventStatus", 1);
				this.context.put("id", id);
				this.context.put("id1", id1);

				String[] idHta = this.request.getParameterValues("idHta");
				String[] idHa = this.request.getParameterValues("idHa");
				String[] HtaNilaiTarikhMohon = this.request
						.getParameterValues("txtHtaNilaiTarikhMohon");
				String[] HtaNilaiTarikhMati = this.request
						.getParameterValues("txtHtaNilaiTarikhMati");
				String[] HaNilaiTarikhMohon = this.request
						.getParameterValues("txtHaNilaiTarikhMohon");
				String[] HaNilaiTarikhMati = this.request
						.getParameterValues("txtHaNilaiTarikhMati");

				if (idHta != null) {
					for (int i = 0; i < idHta.length; i++) {
						for (int i2 = 0; i2 < HtaNilaiTarikhMohon.length; i2++) {
							for (int i3 = 0; i3 < HtaNilaiTarikhMati.length; i3++) {
								logic_internal.updateNilaiHartaBaruHta(
										idHta[i], HtaNilaiTarikhMohon[i],
										HtaNilaiTarikhMati[i], String
												.valueOf(mati), String
												.valueOf(id));
							}
						}
					}
				}
				if (idHa != null) {
					for (int i = 0; i < idHa.length; i++) {
						for (int i2 = 0; i2 < HaNilaiTarikhMohon.length; i2++) {
							for (int i3 = 0; i3 < HaNilaiTarikhMati.length; i3++) {
								logic_internal.updateNilaiHartaBaruHa(idHa[i],
										HaNilaiTarikhMohon[i],
										HaNilaiTarikhMati[i], String
												.valueOf(mati), String
												.valueOf(id));
							}
						}
					}
				}

				logic_A.setSumDataHta(mati);
				sumppkhta = logic_A.getSumDataHta();
				this.context.put("jumppkhta", sumppkhta);

				logic_A.setSumDataHtaDulu(mati);
				sumppkhtadulu = logic_A.getSumDataHtaDulu();
				this.context.put("sumppkhtadulu", sumppkhtadulu);

				logic_A.setOverallSumHta(mati);
				sumoverallppkhta = logic_A.getOverallSumHta();
				this.context.put("jumoverallppkhta", sumoverallppkhta);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);

				logic_A.setDataHaDulu(mati);
				listppkhadulu = logic_A.getDataHaDulu();
				this.context.put("listHaDulu", listppkhadulu);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				logic_B.setDataHta(id);
				listppkhta = logic_B.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				logic_B.setDataHa(id);
				listppkha2 = logic_B.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				logic_B.setDataHtath(id);
				listppkhtath = logic_B.getHtath();
				this.context.put("listHtaht2", listppkhtath);
				int countListhtath = listppkhtath.size();
				this.context.put("jumListHtaht", countList);

			}

			else if ("simpan_nilai_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				this.context.put("EventStatus", 1);
				this.context.put("id", id);
				this.context.put("id1", id1);

				String[] idHta = this.request.getParameterValues("idHta");
				String[] idHa = this.request.getParameterValues("idHa");
				String[] HtaNilaiTarikhMohon = this.request
						.getParameterValues("txtHtaNilaiTarikhMohon");
				String[] HtaNilaiTarikhMati = this.request
						.getParameterValues("txtHtaNilaiTarikhMati");
				String[] HaNilaiTarikhMohon = this.request
						.getParameterValues("txtHaNilaiTarikhMohon");
				String[] HaNilaiTarikhMati = this.request
						.getParameterValues("txtHaNilaiTarikhMati");

				if (idHta != null) {
					for (int i = 0; i < idHta.length; i++) {
						for (int i2 = 0; i2 < HtaNilaiTarikhMohon.length; i2++) {
							for (int i3 = 0; i3 < HtaNilaiTarikhMati.length; i3++) {
								logic_internal.updateNilaiHartaBaruHta(
										idHta[i], HtaNilaiTarikhMohon[i],
										HtaNilaiTarikhMati[i], String
												.valueOf(mati), String
												.valueOf(id));
							}
						}
					}
				}
				if (idHa != null) {
					for (int i = 0; i < idHa.length; i++) {
						for (int i2 = 0; i2 < HaNilaiTarikhMohon.length; i2++) {
							for (int i3 = 0; i3 < HaNilaiTarikhMati.length; i3++) {
								logic_internal.updateNilaiHartaBaruHa(idHa[i],
										HaNilaiTarikhMohon[i],
										HaNilaiTarikhMati[i], String
												.valueOf(mati), String
												.valueOf(id));
							}
						}
					}
				}

				logic_A.setSumDataHta(mati);
				sumppkhta = logic_A.getSumDataHta();
				this.context.put("jumppkhta", sumppkhta);

				logic_A.setSumDataHtaDulu(mati);
				sumppkhtadulu = logic_A.getSumDataHtaDulu();
				this.context.put("sumppkhtadulu", sumppkhtadulu);

				logic_A.setOverallSumHta(mati);
				sumoverallppkhta = logic_A.getOverallSumHta();
				this.context.put("jumoverallppkhta", sumoverallppkhta);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);

				logic_A.setDataHaDulu(mati);
				listppkhadulu = logic_A.getDataHaDulu();
				this.context.put("listHaDulu", listppkhadulu);

				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				logic_B.setDataHta(id);
				listppkhta = logic_B.getDataHta();
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				logic_B.setDataHa(id);
				listppkha2 = logic_B.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				logic_B.setDataHtath(id);
				listppkhtath = logic_B.getHtath();
				this.context.put("listHtaht2", listppkhtath);
				int countListhtath = listppkhtath.size();
				this.context.put("jumListHtaht", countList);

				logic_A.updateDataNilai17(id, id1, (String) session
						.getAttribute("_ekptg_user_id"),
						getParam("id_Permohonansimati"));

			}
			String id = getParam("idPermohonan");

			list = logic_A.setData(id, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("View", list);
			headerppk_baru(session, id, "Y", "", "T");
			context.put("DATEUTIL", new DateUtil());
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			this.context.put("selectedTabatas", 3);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			// int id = Integer.parseInt(getParam("idPermohonan"));
			String mati = getParam("id_Permohonansimati");
			// logic_A.updateDataNilai(id,mati,(String)session.getAttribute("_ekptg_user_id"));

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			String id1 = getParam("idSimati");

			logic_A.updateDataNilai17(id, id1, (String) session
					.getAttribute("_ekptg_user_id"),
					getParam("id_Permohonansimati"));

			int s1 = Integer.parseInt(getParam("no_subjaket"));
			logic_A.setlistGetPermohonanSebelum(id1, (s1));
			Vector listGetPermohonanSebelum = logic_A
					.getlistGetPermohonanSebelum();
			this.context.put("listGetPermohonanSebelum",
					listGetPermohonanSebelum);

			Vector check_pilihan = logic_C.check_pilihan(mati);
			this.context.put("check_pilihan", check_pilihan);

			Vector check_pilihan_ha = logic_C.check_pilihan_ha(mati);
			this.context.put("check_pilihan_ha", check_pilihan_ha);

			vm = "app/ppk/frmPraPrmhnnSek17NilaianHarta.jsp";

		}

		else if ("Cari".equals(submit)) {
			Carix = "2";
			this.context.put("carix", 1);
			String noFail = getParam("txtNoFail");
			String namaPemohon = getParam("txtPemohon");
			String namaSimati = getParam("txtSimati");
			String icSimati = getParam("txtIcSimati");
			String JenisIc = getParam("jenisIc");
			String usid = (String) session.getAttribute("_ekptg_user_id");
			logic_D.setCarianFail17(noFail, namaPemohon, namaSimati, icSimati,
					JenisIc, (String) session.getAttribute("_ekptg_user_id"));
			list1 = logic_D.getList17();

			listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUserX", listDaerahByUser);
			int countList1 = list1.size();

			this.context.put("Senaraifail", list1);

			this.context.put("CountList", countList1);

			prepareItemForDisplay(session, list1, 25, "first");
			vm = "app/ppk/frmSenaraiFailPusakaKecilSek17.jsp";
		}

		else if (("next".equals(submit)) || ("previous".equals(submit))) {

			this.context.put("carix", 1);

			list = logic_E.setList17((String) session
					.getAttribute("_ekptg_user_id"));

			session.setAttribute("Senaraifail", list);

			list = (Vector) session.getAttribute("Senaraifail");
			this.context.put("Senaraifail", list);

			prepareItemForDisplay(session, list, 25, submit);
			int countList = list.size();
			this.context.put("CountList", countList);
			vm = "app/ppk/frmSenaraiFailPusakaKecilSek17.jsp";

		}

		else {

			String usid = (String) session.getAttribute("_ekptg_user_id");
			this.context.put("usid", usid);
			// System.out.println("JENIS USER" + usid);
			this.context.put("noFail", "");
			this.context.put("namapemohon", "");
			this.context.put("nokppemohon", "");
			Carix = "1";
			this.context.put("carix", Carix);

			list = logic_E.setList17((String) session
					.getAttribute("_ekptg_user_id"));

			// System.out.println("!!--" + list);
			int countList = list.size();
			this.context.put("Senaraifail", list);
			this.context.put("CountList", countList);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
			prepareItemForDisplay(session, list, 25, "first");
			vm = "app/ppk/frmSenaraiFailPusakaKecilSek17.jsp";
		}
		
		
		
		
		Db db = null;
		try {
		db = new Db();
		
		
		this.context.put("DATEUTIL", new DateUtil());
		
		Vector count_dunia1 = logic_A.getNofailduniaDb(2, 1, 17,db);
		this.context.put("count_dunia", count_dunia1);		
		listnegeri = logic_A.getListnegeriDb(db);
		this.context.put("listnegeri", listnegeri);
		Vector listtaraf = logic_A.getListtarafDb(db);
		this.context.put("listtaraf", listtaraf);
		Vector listsaudara = logic_A.getListsaudaraDb(db);
		this.context.put("listsaudara", listsaudara);
		Vector listbuktimati = logic_A.getListbuktimatiDb(db);
		this.context.put("listbuktimati", listbuktimati);
		listdaerah = logic_A.getListDaerahDb(db);
		this.context.put("listdaerah", listdaerah);
		Vector listluas = logic_A.getListLuasDb(db);
		this.context.put("listluas", listluas);
		String statuspemilik = "Y";
		Vector listpemilikan = logic_A.getListStatusPemilikDb(statuspemilik,db);
		this.context.put("listpemilikan", listpemilikan);
		Vector listtanah = logic_A.getListJenisTanahDb(db);
		this.context.put("listtanah", listtanah);
		listmukim = logic_A.getListMukimDb(db);
		this.context.put("listmukim", listmukim);
		String statushak = "Y";
		Vector listjenishakmilik = logic_A.getListJenisHakMilikDb(statushak,db);
		this.context.put("listJenisHakMilik", listjenishakmilik);
		Vector listkategori = logic_A.getListKategoriDb(db);
		this.context.put("listkategori", listkategori);
		view1 = logic_A.getJenisKpDb(db);
		this.context.put("listkp", view1);
		this.context.put("listkp2", view1);		
		Vector kenegaraan = null;
		kenegaraan = mainheader.kenegaraanDb(db);
		this.context.put("kenegaraan", kenegaraan);
		FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahARBDb(db);
		FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatPentadbirTanah(db);
		Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
				.getMaklumatMahkamahARB();
		Vector listMaklumatPentadbirTanah = FrmPrmhnnSek8KeputusanPermohonanInternalData
				.getMaklumatPentadbirTanah();
		this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);
		this.context.put("listMaklumatPentadbirTanah", listMaklumatPentadbirTanah);
		this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
		this.context.put("flagForView", flagForView);
		this.context.put("flagFromSenaraiPermohonanSek8",flagFromSenaraiPermohonanSek8);
		
		}
		catch (SQLException se2) {
		throw new Exception("Error:" + se2.getMessage());
		}
		finally {		
		if (db != null)
		db.close();
		}

		Template template = this.engine.getTemplate(vm);
		return template;

	}

	private void delete_semakan(HttpSession session, String idPermohonan)
			throws Exception {
		myLogger.info("delete semakan");
		FrmPrmhnnSek8SenaraiSemakInternalData frmonline = new FrmPrmhnnSek8SenaraiSemakInternalData();
		frmonline.semakanDelete(idPermohonan);
	}

	private void view_get_userid(HttpSession session) throws Exception {
		String useridx = (String) session.getAttribute("_ekptg_user_id");
		logic_A.setGetUserId(useridx);
	}

	private void check_mode_permohonan(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		logic_A.checkData(id);
	}

	private void addPermohonan(HttpSession session, String userIdNeg,
			String userIdPejabat, String userIdKodDaerah, String userIdKodNegeri)
			throws Exception {
		myLogger.info("addPermohonan");
		String id_Fail = getParam("id_Fail");
		String txtNoFail = getParam("txtNoFail");
		String IdPermohonan = getParam("idPermohonan");
		String no_subjaket = getParam("no_subjaket");
		String no_daerah = getParam("socDaerah");
		String tarikh_masuk = getParam("txdTarikhMohon");
		String no_kpbaru_simati = getParam("txtNoKPBaruSimati1")
				+ getParam("txtNoKPBaruSimati2")
				+ getParam("txtNoKPBaruSimati3");
		String no_kplama_simati = getParam("txtNoKPLamaSimati");
		String sel_jeniskp_simati = getParam("socJenisKPLainSimati");
		String no_kplain_simati = getParam("txtNoKPLainSimati");
		String nama_simati = getParam("txtNamaSimati");
		String tarikh_simati = getParam("txtTarikhMati");
		String no_kpbaru_pemohon = getParam("txtNoKPBaruPemohon1")
				+ getParam("txtNoKPBaruPemohon2")
				+ getParam("txtNoKPBaruPemohon3");
		String no_kplama_pemohon = getParam("txtNoKPLamaPemohon");
		String sel_jeniskp_pemohon = getParam("socJenisKPLainPemohon");
		String no_kplain_pemohon = getParam("txtNoKPLainPemohon");
		String nama_pemohon = getParam("txtNamaPemohon");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String bandar = getParam("socBandar");
		String poskod = getParam("txtPoskod");
		String negeri = getParam("socNegeri");
		String negId = getParam("negid");
		String id_Suburusanstatusfail = getParam("id_Suburusanstatusfail");
		String txtNomborResitH = getParam("txtNomborResitH");
		String txdTarikhByrnH = getParam("txdTarikhByrnH");
		String idSimati = getParam("idSimati");
		String txtUmurSimati = getParam("txtUmurSimati");
		String socJantinaSimati = getParam("socJantinaSimati");
		String txtUmurPemohon = getParam("txtUmurPemohon");
		String socJantinaPemohon = getParam("socJantinaPemohon");

		String no_tel = getParam("no_tel");
		String nama_pelbagainegara = getParam("nama_pelbagainegara");
		String taraf_penting = getParam("taraf_penting");
		String jenis_pemohon = getParam("jenis_pemohon");

		String socSaudaraWaris = getParam("socSaudaraWaris");

		Hashtable h = null;
		h = new Hashtable();
		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		// h.put("userId","14");
		h.put("negId", negId);
		h.put("userIdPejabat", userIdPejabat);
		h.put("userIdKodDaerah", userIdKodDaerah);
		h.put("id_Suburusanstatusfail", id_Suburusanstatusfail);
		h.put("txtNoFail", txtNoFail);
		h.put("userIdKodNegeri", userIdKodNegeri);
		h.put("userIdNeg", userIdNeg);
		h.put("IdPermohonan", IdPermohonan);
		h.put("no_daerah", no_daerah);
		h.put("tarikh_masuk", tarikh_masuk);
		h.put("no_kp_baru", no_kpbaru_simati);
		h.put("no_kplama_simati", no_kplama_simati);
		h.put("sel_jeniskp_simati", sel_jeniskp_simati);
		h.put("no_kplain_simati", no_kplain_simati);
		h.put("nama_simati", nama_simati);
		h.put("tarikh_simati", tarikh_simati);
		h.put("no_kpbaru_pemohon", no_kpbaru_pemohon);
		h.put("no_kplama_pemohon", no_kplama_pemohon);
		h.put("sel_jeniskp_pemohon", sel_jeniskp_pemohon);
		h.put("no_kplain_pemohon", no_kplain_pemohon);
		h.put("nama_pemohon", nama_pemohon);
		h.put("alamat1", alamat1);
		h.put("alamat2", alamat2);
		h.put("alamat3", alamat3);
		h.put("bandar", bandar);
		h.put("poskod", poskod);
		h.put("negeri", negeri);
		h.put("idSimati", idSimati);
		h.put("txtUmurSimati", txtUmurSimati);
		h.put("socJantinaSimati", socJantinaSimati);
		h.put("txtUmurPemohon", txtUmurPemohon);
		h.put("socJantinaPemohon", socJantinaPemohon);
		h.put("txtNomborResitH", txtNomborResitH);
		h.put("txdTarikhByrnH", txdTarikhByrnH);

		h.put("no_tel", no_tel);
		h.put("nama_pelbagainegara", nama_pelbagainegara);
		h.put("no_hp", getParam("no_hp"));
		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("taraf_penting", taraf_penting);
		h.put("jenis_pemohon", jenis_pemohon);
		h.put("adaob", getParam("adaob"));

		h.put("bkp", getParam("bkp"));
		h.put("lp", getParam("lp"));
		h.put("bpa", getParam("bpa"));
		h.put("lpa", getParam("lpa"));
		h.put("ht", getParam("ht"));
		// ::
		h.put("lt", getParam("lt"));

		h.put("idp_dulu", getParam("id_Permohonan_terdahulu"));
		h.put("socSaudaraWaris", getParam("socSaudaraWaris"));

		h.put("no_subjaket", no_subjaket);
		if (getParam("pemohonSimati") != "" && getParam("pemohonSimati") != "0") {
			h.put("pemohonSimati", getParam("pemohonSimati"));
			h.put("idpemohonsimati", getParam("idpemohonsimati"));
			logic_A.addPermohonan17PL(session, h);
		} else {
			logic_A.addPermohonan17(session, h);
		}
	}

	private void updatePermohonan(HttpSession session) throws Exception {
		String idFail = getParam("idFail");
		String IdSimati = getParam("idSimati");

		String IdPemohon = getParam("idPemohon");
		String IdPermohonan = getParam("idPermohonan");
		String no_daerah = getParam("socDaerah");
		String tarikh_daftar = getParam("tarikh_daftar");
		String tarikh_masuk = getParam("txdTarikhMohon");
		String no_kpbaru_simati = getParam("txtNoKPBaruSimati1")
				+ getParam("txtNoKPBaruSimati2")
				+ getParam("txtNoKPBaruSimati3");
		String no_kplama_simati = getParam("txtNoKPLamaSimati");
		String sel_jeniskp_simati = getParam("socJenisKPLainSimati");
		String no_kplain_simati = getParam("txtNoKPLainSimati");
		String nama_simati = getParam("txtNamaSimati");
		String tarikh_simati = getParam("txtTarikhMati");
		String no_kpbaru_pemohon = getParam("txtNoKPBaruPemohon1")
				+ getParam("txtNoKPBaruPemohon2")
				+ getParam("txtNoKPBaruPemohon3");
		String no_kplama_pemohon = getParam("txtNoKPLamaPemohon");
		String sel_jeniskp_pemohon = getParam("socJenisKPLainPemohon");
		String no_kplain_pemohon = getParam("txtNoKPLainPemohon");
		String nama_pemohon = getParam("txtNamaPemohon");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String bandar = getParam("socBandar");
		String poskod = getParam("txtPoskod");
		String negeri = getParam("socNegeri");

		String txtUmurSimati = getParam("txtUmurSimati");
		String socJantinaSimati = getParam("socJantinaSimati");
		String txtUmurPemohon = getParam("txtUmurPemohon");
		String socJantinaPemohon = getParam("socJantinaPemohon");

		String no_tel = getParam("no_tel");
		String nama_pelbagainegara = getParam("nama_pelbagainegara");
		String taraf_penting = getParam("taraf_penting");
		String jenis_pemohon = getParam("jenis_pemohon");
		String socSaudaraWaris = getParam("socSaudaraWaris");

		// if()

		Hashtable h = null;
		h = new Hashtable();
		h.put("IdFail", idFail);
		h.put("IdSimati", IdSimati);
		h.put("IdPemohon", IdPemohon);
		h.put("IdPermohonan", IdPermohonan);
		h.put("no_daerah", no_daerah);
		h.put("tarikh_daftar", tarikh_daftar);
		h.put("tarikh_masuk", tarikh_masuk);
		h.put("no_kp_baru", no_kpbaru_simati);
		h.put("no_kplama_simati", no_kplama_simati);
		h.put("sel_jeniskp_simati", sel_jeniskp_simati);
		h.put("no_kplain_simati", no_kplain_simati);
		h.put("nama_simati", nama_simati);
		h.put("tarikh_simati", tarikh_simati);
		h.put("no_kpbaru_pemohon", no_kpbaru_pemohon);
		h.put("no_kplama_pemohon", no_kplama_pemohon);
		h.put("sel_jeniskp_pemohon", sel_jeniskp_pemohon);
		h.put("no_kplain_pemohon", no_kplain_pemohon);
		h.put("nama_pemohon", nama_pemohon);
		h.put("alamat1", alamat1);
		h.put("alamat2", alamat2);
		h.put("alamat3", alamat3);
		h.put("idbandar", bandar);
		h.put("bandar", "");
		h.put("poskod", poskod);
		h.put("negeri", negeri);
		String usid = (String) session.getAttribute("_ekptg_user_id");
		h.put("id_Masuk", usid);
		h.put("txtUmurSimati", txtUmurSimati);
		h.put("socJantinaSimati", socJantinaSimati);
		h.put("txtUmurPemohon", txtUmurPemohon);
		h.put("socJantinaPemohon", socJantinaPemohon);
		h.put("no_tel", no_tel);
		h.put("nama_pelbagainegara", nama_pelbagainegara);
		h.put("taraf_penting", taraf_penting);
		h.put("jenis_pemohon", jenis_pemohon);
		h.put("adaob", getParam("adaob"));

		h.put("no_hp", getParam("no_hp"));
		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("socSaudaraWaris", getParam("socSaudaraWaris"));
		logic_A.updatePermohonan(h);
	}

	/*
	 * private void view_mode_pemohon(HttpSession session) throws Exception {
	 * int id = Integer.parseInt(getParam("idPermohonan")); logic_A.setData(id);
	 * }
	 */
	private void view_get_id(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		logic_A.setGetId(id);
	}

	private void updatepemohon(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		Hashtable k = new Hashtable();

		logic_internal.checkpemohonwaris(getParam("idPemohon"));
		Vector cpw = logic_internal.getcheckpemohonwaris();

		// System.out.println("CW SIZE :" + cpw.size());

		k.put("pemohonwaris", cpw.size());

		String nkpbarupemohon = getParam("txtnoKpBaru1Pemohon")
				+ getParam("txtnoKpBaru2Pemohon")
				+ getParam("txtnoKpBaru3Pemohon");

		k.put("adataraf", getParam("adataraf"));
		// System.out.println("
		k.put("idPermohonan", getParam("idPermohonan"));
		k.put("idPemohon", getParam("idPemohon"));
		k.put("idSimati", getParam("idSimati"));

		k.put("noKpBaru", nkpbarupemohon);
		// k.put("noKpLama", getParam("txtNoKPLamaSimati"));
		k.put("jenisKp", getParam("socJenisKPLainPemohon"));
		k.put("noKpLain", getParam("txtNoKPLainPemohon"));
		k.put("no_Kp_Lama", getParam("txtNoKPLamaPemohon"));
		k.put("namaPemohon", getParam("txtNamaPemohonPemohon"));

		// k.put("taraf", getParam("socTarafKepentinganPemohon"));
		k.put("saudara", getParam("socTalianPersaudaraanPemohon"));
		// k.put("tarikhMatidb",getParam("txdTarikhMatiSimati"));
		k.put("jantina", getParam("socJantinaPemohon"));
		k.put("jenisWarga", getParam("socWarganegaraPemohon"));
		k.put("nama_warga", getParam("nama_warga"));
		k.put("jenisAgama", getParam("socAgamaPemohon"));

		int tr = 0;
		if (getParam("socTarafKePemohonanPemohon") == ""
				|| getParam("socTarafKePemohonanPemohon") == "0") {
			k.put("taraf", tr);
		}
		if (getParam("socTarafKePemohonanPemohon") != ""
				&& getParam("socTarafKePemohonanPemohon") != "0") {
			k.put("taraf", Integer
					.parseInt(getParam("socTarafKePemohonanPemohon")));
		}

		if (getParam("txtUmurPemohon") != "") {
			k.put("umur", Integer.parseInt(getParam("txtUmurPemohon")));
		}
		int umu = 0;
		if (getParam("txtUmurPemohon") == "") {
			k.put("umur", umu);
		}

		k.put("nofaks", getParam("txtNoFaksPemohon"));
		k.put("notelefon", getParam("txtNoTelefonPemohon"));
		k.put("notelefonbimbit", getParam("txtNoTelefonBimbitPemohon"));
		k.put("emel", getParam("txtEmelPemohon"));
		// k.put("jeniswaktumati", getParam("socWaktuKematianSimati"));
		// k.put("sebabMati", getParam("txtSebabKematianSimati"));
		k.put("alamat1", getParam("txtAlamatTerakhir1Pemohon"));
		k.put("alamat2", getParam("txtAlamatTerakhir2Pemohon"));
		k.put("alamat3", getParam("txtAlamatTerakhir3Pemohon"));
		k.put("poskod", getParam("txtPoskodPemohon"));
		// k.put("bandar", getParam("txtBandarPemohon"));
		k.put("bandartetap", getParam("txtBandarPemohon"));

		int ne = 0;
		if (getParam("socNegeriPemohon") == "") {
			k.put("idnegeri", ne);
		}
		if (getParam("socNegeriPemohon") != "") {
			k.put("idnegeri", Integer.parseInt(getParam("socNegeriPemohon")));
		}

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);

		k.put("catatan", getParam("txtCatatanPemohon"));
		// k.put("id_Permohonansimati",
		// Integer.parseInt(getParam("id_Permohonansimati")));
		k.put("id_Permohonansimati", getParam("id_Permohonansimati"));

		k.put("status_Peguam", "");
		k.put("status_Pemohon", getParam("status_pemohon"));
		k.put("id_Masuk", "");

		k.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		k.put("tarikh_Kemaskini", currentDate);
		k.put("id_Db", "");

		k.put("namanegeri", getParam("socNegeriPemohon"));// nama negeri

		k.put("txdTarikhLahirPemohon", getParam("txdTarikhLahirPemohon"));
		k.put("alamat1Surat", getParam("txtAlamatTerakhir1PemohonSurat"));
		k.put("alamat2Surat", getParam("txtAlamatTerakhir2PemohonSurat"));
		k.put("alamat3Surat", getParam("txtAlamatTerakhir3PemohonSurat"));
		k.put("poskodSurat", getParam("txtPoskodPemohonSurat"));
		// k.put("bandarSurat", getParam("txtBandarPemohonSurat"));
		k.put("bandarsurat", getParam("txtBandarPemohonSurat"));

		int nes = 0;
		if (getParam("socNegeriPemohonSurat") == "") {
			k.put("idnegeriSurat", nes);
		}
		if (getParam("socNegeriPemohonSurat") != "") {
			k.put("idnegeriSurat", Integer
					.parseInt(getParam("socNegeriPemohonSurat")));
		}
				
		k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
		k.put("nama_pelbagainegara_surat", getParam("nama_pelbagainegara_surat"));

		logic_internal.updatepemohon(k);

	}

	private void tambahpeguam(HttpSession session) throws Exception {
		Hashtable k = new Hashtable();
		k.put("idPermohonan", getParam("idPermohonan"));
		k.put("idPemohon", getParam("idPemohon"));
		k.put("firma", getParam("txtNamaFirmaPeguam2"));
		k.put("rujfirma", getParam("txtNoRujukanFirma2"));
		k.put("alamat1", getParam("txtAlamat1Peguam2"));
		k.put("alamat2", getParam("txtAlamat2Peguam2"));
		k.put("alamat3", getParam("txtAlamat3Peguam2"));
		k.put("poskod", getParam("txtPoskodPeguam2"));
		k.put("bandar", getParam("txtBandarPeguam2"));
		k.put("idnegeri", getParamAsInteger("socNegeriPeguam2"));
		k.put("noTel", getParam("txtNomborTelefonPeguam2"));
		k.put("noFax", getParam("txtNomborFaksPeguam2"));
		k.put("emel", getParam("txtEmelPeguam2"));
		k.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic_internal.tambahpeguam(k);
		Vector list = new Vector();

	}

	private void updatepeguam(HttpSession session) throws Exception {

		Hashtable k = new Hashtable();

		k.put("idPermohonan", getParam("idPermohonan"));
		k.put("idPeguam", getParam("idPeguam"));
		k.put("idPemohon", getParam("idPemohon"));
		k.put("firma", getParam("txtNamaFirmaPeguam"));
		k.put("rujfirma", getParam("txtNoRujukanFirma"));
		k.put("alamat1", getParam("txtAlamat1Peguam"));
		k.put("alamat2", getParam("txtAlamat2Peguam"));
		k.put("alamat3", getParam("txtAlamat3Peguam"));
		k.put("poskod", getParam("txtPoskodPeguam"));
		k.put("bandar", getParam("txtBandarPeguam"));
		k.put("idnegeri", getParam("socNegeriPeguam"));
		k.put("noTel", getParam("txtNomborTelefonPeguam"));
		k.put("noFax", getParam("txtNomborFaksPeguam"));
		k.put("emel", getParam("txtEmelPeguam"));
		k.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));

		logic_internal.updatepeguam(k);
		Vector list = new Vector();

	}

	private void addWaris(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();

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

		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", getParam("socJantinaWaris"));
		h.put("agama", getParam("socAgamaWaris"));
		h.put("warga", getParam("socWarganegaraWaris"));
		h.put("nama_warga", getParam("nama_warga"));

		h.put("tarikhlahir", getParam("txdTarikhLahirWaris"));
		h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
		// h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));

		h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		h.put("poskod", getParam("txtPoskodWaris"));
		// h.put("bandar", getParam("txtBandarWaris"));

		if (getParam("txtBandarWaris").equals("")) {
			h.put("bandar", 0);
		} else {
			h.put("bandar", Integer.parseInt(getParam("txtBandarWaris")));
		}

		if (getParam("socNegeriWaris").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriWaris")));
		}

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		// h.put("bandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("txtBandarWarisSurat").equals("")) {
			h.put("bandarSurat", 0);
		} else {
			h.put("bandarSurat", Integer
					.parseInt(getParam("txtBandarWarisSurat")));
		}

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
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

		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);
		h.put("jeniswaktu", getParam("jeniswaktu"));

		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		
		h.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
        h.put("nama_pelbagainegara_surat", getParam("nama_pelbagainegara_surat"));

		logic_internal.addWaris(h);
	}

	private void addWarisBerikut(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();

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

		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", getParam("socJantinaWaris"));
		h.put("agama", getParam("socAgamaWaris"));
		h.put("warga", getParam("socWarganegaraWaris"));
		h.put("nama_warga", getParam("nama_warga"));

		h.put("tarikhlahir", getParam("txdTarikhLahirWaris"));
		h.put("noberanak", getParam("txtNoSuratBeranakWaris"));
		// h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));

		h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		h.put("poskod", getParam("txtPoskodWaris"));
		// h.put("bandar", getParam("txtBandarWaris"));

		if (getParam("txtBandarWaris").equals("")) {
			h.put("bandar", 0);
		} else {
			h.put("bandar", Integer.parseInt(getParam("txtBandarWaris")));
		}

		if (getParam("socNegeriWaris").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriWaris")));
		}

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		// h.put("bandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("txtBandarWarisSurat").equals("")) {
			h.put("bandarSurat", 0);
		} else {
			h.put("bandarSurat", Integer
					.parseInt(getParam("txtBandarWarisSurat")));
		}

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
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

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("jeniswaktu", getParam("jeniswaktu"));

		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		
		
		h.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
        h.put("nama_pelbagainegara_surat", getParam("nama_pelbagainegara_surat"));

		logic_internal.addWarisBerikut(h);
	}

	private void addWarisHubungan(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("idParent", getParam("txtIdParent"));
		h.put("idOb", getParam("txtIdParent"));
		h.put("saudara", Integer.parseInt(getParam("socSaudaraWaris")));

		logic_internal.addWarisBerikut(h);
	}

	private void updatewaris(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();
		h.put("idwaris", getParam("idwarisup"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("idSimati", getParam("txtIdSimatiWaris"));
		h.put("namaOB", getParam("txtNamaOBWaris"));

		String kp_Waris = getParam("txtNoKPBaru1Waris")
				+ getParam("txtNoKPBaru2Waris") + getParam("txtNoKPBaru3Waris");

		h.put("nokpbaru", kp_Waris);
		h.put("id_Pemohon", getParam("id_Pemohon"));

		h.put("nokpwaris", getParam("txtNoKPLamaWaris"));
		h.put("notel", getParam("txtNoTeleponWaris"));
		h.put("hp", getParam("txtNoTeleponBimbitWaris"));
		h.put("jenisKp", getParam("socJenisKPLainWaris"));
		h.put("nokplain", getParam("txtNoKPLainWaris"));

		// h.put("taraf",
		// Integer.parseInt(getParam("socTarafKepentinganPenting")));
		h.put("jantina", getParam("socJantinaWaris"));
		h.put("agama", getParam("socAgamaWaris"));
		h.put("warga", getParam("socWarganegaraWaris"));
		h.put("nama_warga", getParam("nama_warga"));

		h.put("alamat1", getParam("txtAlamatTerakhir1Waris"));
		h.put("alamat2", getParam("txtAlamatTerakhir2Waris"));
		h.put("alamat3", getParam("txtAlamatTerakhir3Waris"));
		h.put("poskod", getParam("txtPoskodWaris"));
		// h.put("bandar", getParam("txtBandarWaris"));

		if (getParam("txtBandarWaris").equals("")) {
			h.put("bandar", 0);
		} else {
			h.put("bandar", Integer.parseInt(getParam("txtBandarWaris")));
		}

		if (getParam("socNegeriWaris").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriWaris")));
		}

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		// h.put("bandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("txtBandarWarisSurat").equals("")) {
			h.put("bandarSurat", 0);
		} else {
			h.put("bandarSurat", Integer
					.parseInt(getParam("txtBandarWarisSurat")));
		}

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
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
		// h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));

		if (getParam("txtUmurWaris").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurWaris")));
		}

		h.put("tarikhmati", getParam("txdTarikhMatiWaris"));
		h.put("waktumatiwaris", getParam("txtWaktuKematianWaris"));

		h.put("hp", getParam("txtNoTeleponBimbitWaris"));

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);
		h.put("jeniswaktu", getParam("jeniswaktu"));

		h.put("id_ob_list_getListOBUpdate",
				getParam("id_ob_list_getListOBUpdate"));

		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		
		h.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
		h.put("nama_pelbagainegara_surat", getParam("nama_pelbagainegara_surat"));  
		
		logic_internal.updateWaris(h);

	}

	private void addPenghutang(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		// int id_Permohonansimati =
		// Integer.parseInt(getParam("id_Permohonansimati"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("idSimati", getParam("idSimati"));
		h.put("namaOB", getParam("txtNamaOBPenting"));
		String kp_penting = getParam("txtNoKPBaru1Penting")
				+ getParam("txtNoKPBaru2Penting")
				+ getParam("txtNoKPBaru3Penting");
		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPenting"));
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

		if (getParam("txtBandarPenting").equals("")) {
			h.put("bandar", 0);
		} else {
			h.put("bandar", Integer.parseInt(getParam("txtBandarPenting")));
		}
		h.put("agama", getParam("socAgamaPenting"));
		h.put("warga", getParam("socWarganegaraPenting"));

		if (getParam("txtNilaiHutangPenting").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPenting")));
		}

		h.put("noakaun", getParam("txtNoAkaunPenting"));
		h.put("butiranhutang", getParam("txtButiranHutangPenting"));
		h.put("jenishutang", getParam("socJenisHutangPenting"));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		logic_internal.addPenghutang(h);

	}

	private void updatepenghutang(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("idSimati", getParam("txtIdSimatiPenting"));
		h.put("idob", getParam("txtIdOb"));
		h.put("jenishutang", getParam("socJenisHutangPentingU"));
		h.put("namaOB", getParam("txtNamaOBPentingU"));
		String kp_penting = getParam("txtNoKPBaru1PentingU")
				+ getParam("txtNoKPBaru2PentingU")
				+ getParam("txtNoKPBaru3PentingU");
		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPentingU"));
		h.put("jenisKp", getParam("socJenisKPLainPentingU"));
		h.put("nokplain", getParam("txtNoKPLainPentingU"));
		h.put("alamat1", getParam("txtAlamatTerakhir1PentingU"));
		h.put("alamat2", getParam("txtAlamatTerakhir2PentingU"));
		h.put("alamat3", getParam("txtAlamatTerakhir3PentingU"));
		h.put("poskod", getParam("txtPoskodPentingU"));
		// h.put("bandar", getParam("txtBandarPentingU"));

		if (getParam("socBandarPentingU").equals("")) {
			h.put("bandar", 0);
		} else {
			h.put("bandar", Integer.parseInt(getParam("socBandarPentingU")));
		}
		h.put("agama", getParam("socAgamaPentingU"));
		h.put("warga", getParam("socWarganegaraPentingU"));

		if (getParam("socNegeriPentingU").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPentingU")));
		}
		h.put("catatan", getParam("txtCatatanPentingU"));

		if (getParam("txtNilaiHutangPentingU").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPentingU")));
		}

		// h.put("nilaihutang", getParam("txtNilaiHutangPentingU"));
		h.put("noakaun", getParam("txtNoAkaunPentingU"));
		h.put("butiranhutang", getParam("txtButiranHutangPentingU"));

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);

		logic_internal.updatePenghutang(h);

	}

	private void addPenting(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();

		h.put("idSimati", getParam("idSimati"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("namaOB", getParam("txtNamaOBPenting"));
		// int id_Permohonansimati =
		// Integer.parseInt(getParam("id_Permohonansimati"));

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
		h.put("idbandar", getParam("txtBandarPenting"));

		if (getParam("socNegeriPenting").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		}

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		h.put("catatan", getParam("txtCatatanPenting"));
		h.put("catatanhutang", getParam("txtButiranHutangPenting"));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		h.put("idbandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		h.put("notel", getParam("txtNoTeleponPenting"));

		h.put("jenishutang", getParam("socJenisHutangPenting"));

		h.put("noakaun", getParam("txtNoAkaunPenting"));

		if (getParam("txtNilaiHutangPenting").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPenting")));
		}

		// baruOB
		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("no_fax", getParam("txtNoFaxPenting"));
		h.put("jenis_pemohon", getParam("jenis_pemohon"));
		h.put("no_hp", getParam("txtNoHpPenting"));
		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		logic_internal.addPenting(h);
	}

	private void updatepenting(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));

		h.put("idSimati", getParam("txtIdSimatiPenting"));
		h.put("jenishutang", getParam("socJenisHutangPentingU"));
		h.put("id_Pemohon", getParam("id_Pemohon"));
		h.put("idob", getParam("txtIdOb"));
		h.put("namaOB", getParam("txtNamaOBPentingU"));
		String kp_penting = getParam("txtNoKPBaru1PentingU")
				+ getParam("txtNoKPBaru2PentingU")
				+ getParam("txtNoKPBaru3PentingU");
		h.put("nokpbaru", kp_penting);
		h.put("nokppenting", getParam("txtNoKPLamaPentingU"));
		h.put("jenisKp", getParam("socJenisKPLainPentingU"));
		h.put("nokplain", getParam("txtNoKPLainPentingU"));
		h.put("statusOB", getParam("socStatusOBU"));

		if (getParam("socTarafKepentinganPentingU").equals("")) {
			int a = 0;
			h.put("taraf", a);
		} else {
			h.put("taraf", Integer
					.parseInt(getParam("socTarafKepentinganPentingU")));
		}

		h.put("jantina", getParam("socJantinaPentingU"));
		h.put("agama", getParam("socAgamaPentingU"));
		h.put("warga", getParam("socWarganegaraPentingU"));
		if (getParam("txtUmurPentingU").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurPentingU")));
		}
		h.put("dob", getParam("txdTarikhLahirPentingU"));
		h.put("noberanak", getParam("txtNoSuratBeranakPentingU"));
		h.put("alamat1", getParam("txtAlamatTerakhir1PentingU"));
		h.put("alamat2", getParam("txtAlamatTerakhir2PentingU"));
		h.put("alamat3", getParam("txtAlamatTerakhir3PentingU"));
		h.put("poskod", getParam("txtPoskodPentingU"));
		h.put("bandar", getParam("txtBandarPentingU"));

		if (getParam("socBandarPentingU").equals("")) {
			h.put("idbandar", 0);
		} else {
			h.put("idbandar", Integer.parseInt(getParam("socBandarPentingU")));
		}

		if (getParam("socNegeriPentingU").equals("")) {
			h.put("negeri", 0);
		} else {
			h.put("negeri", Integer.parseInt(getParam("socNegeriPentingU")));
		}
		h.put("catatan", getParam("txtCatatanPentingU"));

		if (getParam("txtNilaiHutangPentingU").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang", Double
					.parseDouble(getParam("txtNilaiHutangPentingU")));
		}

		// h.put("nilaihutang", getParam("txtNilaiHutangPentingU"));
		h.put("noakaun", getParam("txtNoAkaunPentingU"));
		h.put("butiranhutang", getParam("txtButiranHutangPentingU"));

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);

		h.put("alamat1Surat", getParam("txtAlamatTerakhir1WarisSurat"));
		h.put("alamat2Surat", getParam("txtAlamatTerakhir2WarisSurat"));
		h.put("alamat3Surat", getParam("txtAlamatTerakhir3WarisSurat"));
		h.put("poskodSurat", getParam("txtPoskodWarisSurat"));
		h.put("bandarSurat", getParam("txtBandarWarisSurat"));

		if (getParam("socBandarWarisSurat").equals("")) {
			h.put("idbandarSurat", 0);
		} else {
			h.put("idbandarSurat", Integer
					.parseInt(getParam("socBandarWarisSurat")));
		}

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat", Integer
					.parseInt(getParam("socNegeriWarisSurat")));
		}

		h.put("notel", getParam("txtNoTeleponPentingU"));

		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("no_fax", getParam("txtNoFaxPenting"));
		h.put("jenis_pemohon", getParam("jenis_pemohon"));
		h.put("no_hp", getParam("txtNoHpPenting"));

		if (getParam("txtBandarPentingU_X").equals("")) {
			h.put("bandartetap_x", 0);
		} else {
			h.put("bandartetap_x", Integer
					.parseInt(getParam("txtBandarPentingU_X")));
		}

		if (getParam("txtBandarWarisSurat_X").equals("")) {
			h.put("bandarsurat_x", 0);
		} else {
			h.put("bandarsurat_x", Integer
					.parseInt(getParam("txtBandarWarisSurat_X")));
		}

		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

		logic_internal.updatePenting(h);

	}

	public void addHtaam(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

		h.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
		h.put("idSimati", getParam("idSimati"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		// int mati = Integer.parseInt(getParam("id_Permohonansimati"));
		h.put("nopt", getParam("txtNoPTHtaam"));

		/*
		 * if (getParam("txtNilaiTarikhMohonHtaa") != "") {
		 * h.put("nilai_Hta_memohon", Double
		 * .parseDouble(getParam("txtNilaiTarikhMohonHtaa"))); } else {
		 * h.put("nilai_Hta_memohon", 0.0); }
		 */

		h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHtaa"));
		/*
		 * if (getParam("txtNilaiTarikhMatiHtaam") != "") {
		 * h.put("nilai_Hta_mati", Double
		 * .parseDouble(getParam("txtNilaiTarikhMatiHtaam"))); } else {
		 * h.put("nilai_Hta_mati", 0.0); }
		 */

		h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaam"));

		if (getParam("socKategoriTanahHtaam") != "") {
			h.put("kategori", Integer
					.parseInt(getParam("socKategoriTanahHtaam")));
		} else {
			h.put("kategori", 0);
		}

		if (getParam("socJenisHakmilikHtaam") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaam")));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", getParam("socStatusPemilikanHtaam"));

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

		h.put("luashmp", getParam("txtLuasHMpHtaam"));
		h.put("luasasal", getParam("txtLuasAsalHtaam"));
		h.put("nopajakan", getParam("txtNoPajakan"));
		h.put("jenistanah", getParam("socJenisTanahHtaam"));

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

		if (getParam("socJenisLuasHtaam") != "") {
			h.put("jenisluas", Integer.parseInt(getParam("socJenisLuasHtaam")));
		} else {
			h.put("jenisluas", 0);
		}

		h.put("tanggungan", getParam("txtTanggunganHtaam"));

		h.put("catatan", getParam("txtCatatanHtaam"));
		h.put("noperserahan", getParam("txtNoPersHtaam"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", getParam("txtSekatan") == null ? ""
				: getParam("txtSekatan"));
		h.put("syaratNyata", getParam("txtSyaratNyata") == null ? ""
				: getParam("txtSyaratNyata"));

		logic_internal.addHtaam(h);

	}

	/*
	 * private void updateHtaam(HttpSession session) throws Exception {
	 * 
	 * Vector v = new Vector(); Hashtable h = new Hashtable();
	 * 
	 * h.put("idhta",getParam("id_htaam"));
	 * 
	 * h.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
	 * h.put("idSimati",getParam("idSimati")); h.put("nopt",
	 * getParam("txtNoPTHtaamUp"));
	 * h.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHt"));
	 * 
	 * 
	 * h.put("nilai_Hta_mati",getParam("txtNilaiTarikhMatiHtaamUpd"));
	 * 
	 * 
	 * if (getParam("socKategoriTanahHtaamUp") != "") { h.put("kategori",
	 * Integer .parseInt(getParam("socKategoriTanahHtaamUp"))); } else {
	 * h.put("kategori", 0); }
	 * 
	 * 
	 * 
	 * if (getParam("socJenisHakmilikHtaamUp") != "") { h.put("jenishakmilik",
	 * Integer .parseInt(getParam("socJenisHakmilikHtaamUp"))); } else {
	 * h.put("jenishakmilik", 0); }
	 * 
	 * h.put("pemilikan", getParam("socStatusPemilikanHtaamUpd"));
	 * 
	 * if (getParam("socNegeriHtaamUp") != "") { h.put("negeri",
	 * Integer.parseInt(getParam("socNegeriHtaamUp"))); } else { h.put("negeri",
	 * 0); }
	 * 
	 * if (getParam("socDaerahHtaamUp") != "") { h.put("daerah",
	 * Integer.parseInt(getParam("socDaerahHtaamUp"))); } else { h.put("daerah",
	 * 0); }
	 * 
	 * if (getParam("socMukimHtaamUp") != "") { h.put("mukim",
	 * Integer.parseInt(getParam("socMukimHtaamUp"))); } else { h.put("mukim",
	 * 0); }
	 * 
	 * h.put("luashmp", getParam("txtLuasHMpHtaamUpd")); h.put("luasasal",
	 * getParam("txtLuasAsalHtaamUpd")); h.put("nopajakan",
	 * getParam("txtNoPajakanUp")); h.put("jenistanah",
	 * getParam("socJenisTanahHtaamUpd"));
	 * 
	 * if (getParam("txtBahagianSimati1Up") != "") { h.put("basimati",
	 * getParam("txtBahagianSimati1Up")); } else { h.put("basimati", "0"); }
	 * 
	 * if (getParam("txtBahagianSimati2Up") != "") {
	 * h.put("bbsimati",getParam("txtBahagianSimati2Up")); } else {
	 * h.put("bbsimati", "0"); }
	 * 
	 * if (getParam("socJenisLuasHtaamUpd") != "") { h.put("jenisluas", Integer
	 * .parseInt(getParam("socJenisLuasHtaamUpd"))); } else { h.put("jenisluas",
	 * 0); }
	 * 
	 * h.put("tanggungan", getParam("txtTanggunganHtaamUp"));
	 * 
	 * h.put("catatan", getParam("txtCatatanHt")); h.put("noperserahan",
	 * getParam("txtNoPersHtaamUp"));
	 * 
	 * h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
	 * h.put("tarikh_Kemaskini", currentDate);
	 * 
	 * logic_internal.updateHtaam(h);
	 * 
	 * }
	 */
	public void addHtaamX(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		// h.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
		h.put("idSimati", getParam("idSimati"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		// int mati = Integer.parseInt(getParam("id_Permohonansimati"));
		h.put("nopt", getParam("txtNoPTHtaamX"));
		/*
		 * if (getParam("txtNilaiTarikhMohonHtaaX") != "") {
		 * h.put("nilai_Hta_memohon", Double
		 * .parseDouble(getParam("txtNilaiTarikhMohonHtaaX"))); } else {
		 * h.put("nilai_Hta_memohon", 0.0); }
		 * 
		 * if (getParam("txtNilaiTarikhMatiHtaamX") != "") {
		 * h.put("nilai_Hta_mati", Double
		 * .parseDouble(getParam("txtNilaiTarikhMatiHtaamX"))); } else {
		 * h.put("nilai_Hta_mati", 0.0); }
		 */
		h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHtaaX"));
		h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));

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
			h.put("basimati", getParam("txtBahagianSimati1X"));
		} else {
			h.put("basimati", "0");
		}

		if (getParam("txtBahagianSimati2X") != "") {
			h.put("bbsimati", getParam("txtBahagianSimati2X"));
		} else {
			h.put("bbsimati", "0");
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
		// h.put("bandarpemaju",getParam("txtBandarPemaju1HtaamX"));

		if (getParam("txtBandarPemaju1HtaamX").equals("")) {
			h.put("bandarpemaju", 0);
		} else {
			h.put("bandarpemaju", Integer
					.parseInt(getParam("txtBandarPemaju1HtaamX")));
		}

		if (getParam("socNegeriPemajuHtaamX").equals("")) {
			h.put("negeripemaju", 0);
		} else {
			h.put("negeripemaju", Integer
					.parseInt(getParam("socNegeriPemajuHtaamX")));
		}

		h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));

		h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		// h.put("bandarhta",getParam("txtBandarHartaHtaamX"));

		if (getParam("txtBandarHartaHtaamX").equals("")) {
			h.put("bandarhta", 0);
		} else {
			h.put("bandarhta", Integer
					.parseInt(getParam("txtBandarHartaHtaamX")));
		}

		h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
		h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));

		h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
		h.put("noroh", getParam("txtNoRohHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));

		h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));

		h.put("nocagaran", getParam("txtNoCagaranX"));

		String[] radioHtaamViewX = this.request
				.getParameterValues("radioHtaamViewX");
		int n = 0;
		for (int i = 0; i < radioHtaamViewX.length; i++) {
			if (radioHtaamViewX[i].equals("1")) {
				n = 1;
			} else if (radioHtaamViewX[i].equals("2")) {
				n = 2;
			} else if (radioHtaamViewX[i].equals("3")) {
				n = 3;
			}
		}

		// String flag="";
		if (n == 1) {
			h.put("flag", "1");
		}
		if (n == 2) {
			h.put("flag", "2");
		}
		if (n == 3) {
			h.put("flag", "3");
		}
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		logic_internal.addHtaamX(h);

	}

	/*
	 * private void updateHtaamX(HttpSession session) throws Exception {
	 * 
	 * Vector v = new Vector(); Hashtable h = new Hashtable();
	 * 
	 * // h.put("idhta", Integer.parseInt(getParam("idhtaamXid")));
	 * h.put("idhta",getParam("idhtaamid")); // int
	 * idhtaam=Integer.parseInt(getParam("idhtaamid"));
	 * 
	 * h.put("idSimati",getParam("idSimati")); h.put("nopt",
	 * getParam("txtNoPTHtaamX"));
	 * h.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHtaaX"));
	 * h.put("nilai_Hta_mati",getParam("txtNilaiTarikhMatiHtaamX"));
	 * 
	 * if (getParam("socKategoriTanahHtaamX") != "") { h.put("kategori", Integer
	 * .parseInt(getParam("socKategoriTanahHtaamX"))); } else {
	 * h.put("kategori", 0); }
	 * 
	 * if (getParam("socJenisHakmilikHtaamX") != "") { h.put("jenishakmilik",
	 * Integer .parseInt(getParam("socJenisHakmilikHtaamX"))); } else {
	 * h.put("jenishakmilik", 0); }
	 * 
	 * h.put("pemilikan", getParam("socStatusPemilikanHtaamX"));
	 * 
	 * if (getParam("socNegeriHtaamX") != "") { h.put("negeri",
	 * Integer.parseInt(getParam("socNegeriHtaamX"))); } else { h.put("negeri",
	 * 0); }
	 * 
	 * if (getParam("socDaerahHtaamX") != "") { h.put("daerah",
	 * Integer.parseInt(getParam("socDaerahHtaamX"))); } else { h.put("daerah",
	 * 0); }
	 * 
	 * if (getParam("socMukimHtaamX") != "") { h.put("mukim",
	 * Integer.parseInt(getParam("socMukimHtaamX"))); } else { h.put("mukim",
	 * 0); }
	 * 
	 * h.put("luashmp", getParam("txtLuasHMpHtaamX")); h.put("luasasal",
	 * getParam("txtLuasAsalHtaamX")); h.put("nopajakan",
	 * getParam("txtNoPajakanX")); h.put("jenistanah",
	 * getParam("socJenisTanahHtaamX"));
	 * 
	 * if (getParam("txtBahagianSimati1X") != "") { h
	 * .put("basimati",getParam("txtBahagianSimati1X")); } else {
	 * h.put("basimati", "0"); }
	 * 
	 * if (getParam("txtBahagianSimati2X") != "") { h .put("bbsimati",
	 * getParam("txtBahagianSimati2X")); } else { h.put("bbsimati", "0"); }
	 * 
	 * if (getParam("socJenisLuasHtaamX") != "") { h .put("jenisluas", Integer
	 * .parseInt(getParam("socJenisLuasHtaamX"))); } else { h.put("jenisluas",
	 * 0); }
	 * 
	 * h.put("tanggungan", getParam("txtTanggunganHtaamX")); h.put("catatan",
	 * getParam("txtCatatanHtaamX")); h.put("noperserahan",
	 * getParam("txtNoPersHtaamX")); h.put("namapemaju",
	 * getParam("txtNamaPemajuHtaamX")); h.put("alamatpemaju1",
	 * getParam("txtAlamatPemaju1HtaamX")); h.put("alamatpemaju2",
	 * getParam("txtAlamatPemaju2HtaamX")); h.put("alamatpemaju3",
	 * getParam("txtAlamatPemaju3HtaamX")); h.put("poskodpemaju",
	 * getParam("txtPoskodPemaju1HtaamX")); //
	 * h.put("bandarpemaju",getParam("txtBandarPemaju1HtaamX")); if
	 * (getParam("txtBandarPemaju1HtaamX").equals("")) { h.put("bandarpemaju",
	 * 0); } else { h.put("bandarpemaju", Integer
	 * .parseInt(getParam("txtBandarPemaju1HtaamX"))); }
	 * 
	 * if (getParam("socNegeriPemajuHtaamX").equals("")) { h.put("negeripemaju",
	 * 0); } else { h.put("negeripemaju", Integer
	 * .parseInt(getParam("socNegeriPemajuHtaamX"))); }
	 * 
	 * h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
	 * h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
	 * h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));
	 * 
	 * h.put("poskodhta", getParam("txtPoskodHartaHtaamX")); //
	 * h.put("bandarhta",getParam("txtBandarHartaHtaamX"));
	 * 
	 * if (getParam("txtBandarHartaHtaamX").equals("")) { h.put("bandarhta", 0);
	 * } else { h.put("bandarhta", Integer
	 * .parseInt(getParam("txtBandarHartaHtaamX"))); } h.put("noperjanjian",
	 * getParam("txtNoPerjanjianHtaamX")); h.put("tarikhperjanjian",
	 * getParam("txtTarikhPerjanjianHtaamX"));
	 * 
	 * h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
	 * h.put("noroh", getParam("txtNoRohHtaamX")); h.put("nolot",
	 * getParam("txtLotIdHtaamX"));
	 * 
	 * h.put("nolot", getParam("txtLotIdHtaamX"));
	 * 
	 * h.put("nocagaran", getParam("txtNoCagaranX"));
	 * 
	 * h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));
	 * 
	 * String flag1 = getParam("radioHtaamViewX1"); String flag2 =
	 * getParam("radioHtaamViewX2"); String flag3 =
	 * getParam("radioHtaamViewX3"); String flag = ""; if (flag1 != "") {
	 * h.put("flag", "1"); } if (flag2 != "") { h.put("flag", "2"); } if (flag3
	 * != "") { h.put("flag", "3"); }
	 * 
	 * h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
	 * h.put("tarikh_Kemaskini", currentDate); logic_internal.updateHtaamX(h);
	 * 
	 * }
	 */

	private void addHa(HttpSession session) throws Exception {
		String id = getParam("id");
		String id1 = getParam("idSimati");
		String socJenisHartaAlih = getParam("socJenisHartaAlih");
		String txtBhgnSimati1 = getParam("txtBhgnSimati1");
		String txtBhgnSimati2 = getParam("txtBhgnSimati2");
		String txtNoRujukan = getParam("txtNoRujukan");
		String txtNilaiTarikhMati = getParam("txtNilaiTarikhMati");
		String txtNoSijil = getParam("txtNoSijil");
		String txtNilaiTarikhMohon = getParam("txtNilaiTarikhMohon");
		String txtBilUnit = getParam("txtBilUnit");
		String txtNilaiSeunit = getParam("txtNilaiSeunit");
		String txtAgensi = getParam("txtAgensi");
		String txtCatatan = getParam("txtCatatan");
		String socNegeriHtaam = getParam("socNegeriHtaam");
		String socDaerahHtaam = getParam("socDaerahHtaam");
		String bil = getParam("bil");
		String mati = getParam("id_Permohonansimati");

		String txtAlamat1 = getParam("txtAlamat1");
		String nama_saham = getParam("nama_saham");
		String txtAlamat2 = getParam("txtAlamat2");
		String txtAlamat3 = getParam("txtAlamat3");
		String txtPoskod = getParam("txtPoskod");

		Hashtable h = null;
		h = new Hashtable();
		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		h.put("id", id);
		h.put("id1", id1);
		h.put("id_Permohonansimati", mati);
		h.put("nama_saham", nama_saham);
		h.put("socJenisHartaAlih", socJenisHartaAlih);
		h.put("txtBhgnSimati1", txtBhgnSimati1);
		h.put("txtBhgnSimati2", txtBhgnSimati2);
		h.put("txtNoRujukan", txtNoRujukan);
		h.put("txtNilaiTarikhMati", txtNilaiTarikhMati);
		h.put("txtNoSijil", txtNoSijil);
		h.put("txtNilaiTarikhMohon", txtNilaiTarikhMohon);
		h.put("txtBilUnit", txtBilUnit);
		h.put("txtNilaiSeunit", txtNilaiSeunit);
		h.put("txtAgensi", txtAgensi);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);

		h.put("txtAlamat1", txtAlamat1);
		h.put("txtAlamat2", txtAlamat2);
		h.put("txtAlamat3", txtAlamat3);
		h.put("txtPoskod", txtPoskod);
		h.put("butiran", getParam("butiran"));

		h.put("socNegeriHtaam", socNegeriHtaam);
		h.put("socDaerahHtaam", socDaerahHtaam);
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));

		logic_A.addHa(h);
	}

	private void updateHa(HttpSession session) throws Exception {
		String id1 = getParam("idSimati");
		String id3 = getParam("idha");
		String socJenisHartaAlih = getParam("socJenisHartaAlih");
		String txtBhgnSimati1 = getParam("txtBhgnSimati1");
		String txtBhgnSimati2 = getParam("txtBhgnSimati2");
		String txtNoRujukan = getParam("txtNoRujukan");
		String txtNilaiTarikhMati = getParam("txtNilaiTarikhMati");
		String txtNoSijil = getParam("txtNoSijil");
		String txtNilaiTarikhMohon = getParam("txtNilaiTarikhMohon");
		String txtBilUnit = getParam("txtBilUnit");
		String txtNilaiSeunit = getParam("txtNilaiSeunit");
		String Agensi = getParam("txtAgensi");
		String txtCatatan = getParam("txtCatatan");
		String bil = getParam("bil");
		String txtAlamat1 = getParam("txtAlamat1");
		String txtAlamat2 = getParam("txtAlamat2");
		String txtAlamat3 = getParam("txtAlamat3");
		String txtPoskod = getParam("txtPoskod");
		String nama_saham = getParam("nama_saham");

		String socDaerahHtaam = getParam("socDaerahHtaam");
		String socNegeriHtaam = getParam("socNegeriHtaam");

		Hashtable h = null;
		h = new Hashtable();
		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("id3", id3);
		h.put("id1", id1);
		h.put("socJenisHartaAlih", socJenisHartaAlih);
		h.put("txtBhgnSimati1", txtBhgnSimati1);
		h.put("txtBhgnSimati2", txtBhgnSimati2);
		h.put("txtNoRujukan", txtNoRujukan);
		h.put("txtNilaiTarikhMati", txtNilaiTarikhMati);
		h.put("txtNoSijil", txtNoSijil);
		h.put("txtNilaiTarikhMohon", txtNilaiTarikhMohon);
		h.put("txtBilUnit", txtBilUnit);
		h.put("txtNilaiSeunit", txtNilaiSeunit);
		h.put("Agensi", Agensi);
		h.put("nama_saham", nama_saham);
		h.put("txtCatatan", txtCatatan);
		h.put("bil", bil);
		h.put("txtAlamat1", txtAlamat1);
		h.put("txtAlamat2", txtAlamat2);
		h.put("txtAlamat3", txtAlamat3);
		h.put("txtPoskod", txtPoskod);
		h.put("butiran", getParam("butiran"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));

		if (socNegeriHtaam != "" && socNegeriHtaam != "0") {
			h.put("socDaerahHtaam", socDaerahHtaam);
		} else {
			h.put("socDaerahHtaam", "0");
		}

		h.put("socNegeriHtaam", socNegeriHtaam);
		logic_A.kemaskiniHa(h);
	}

	private void delete_mode_ppkha(HttpSession session, String id1, String id3)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.deleteDataHa(id1, id3);
	}

	public void updateStatus(HttpSession session) throws Exception {

		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("idPermohonan", getParam("idPermohonan"));
		h.put("idstatus", Integer.parseInt(getParam("idstatus")));
		h.put("id_Fail", getParam("id_Fail"));

		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
		h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));
		// :::SUB
		// ID_STATUS 14
		// ID_SUBURUSAN 353
		logic_internal.updateStatus(session, h);
		logic_A.kemaskiniSubUrusanStatusFail(session, getParam("idPermohonan"),
				(String) session.getAttribute("_ekptg_user_id"), "14", "353",
				getParam("id_Fail"));

	}

	public void updateStatus17(HttpSession session) throws Exception {

		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("idPermohonan", getParam("idPermohonan"));
		h.put("idstatus", Integer.parseInt(getParam("idstatus")));
		h.put("id_Fail", getParam("id_Fail"));

		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
		h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));
		// :::SUB
		// ID_STATUS 14
		// ID_SUBURUSAN 434
		// logic_internal.updateStatus17(h);
		logic_A.kemaskiniSubUrusanStatusFail(session, getParam("idPermohonan"),
				(String) session.getAttribute("_ekptg_user_id"), "14", "434",
				getParam("id_Fail"));

	}

	private void prepareItemForDisplay(HttpSession session, Vector objVector,
			int cntItemPage, String command) {
		int x;
		int startno = 0;
		if (command == null)
			command = "first";
		if (session.getAttribute("_portal_startnoInternalFail") != null)
			startno = ((Integer) session
					.getAttribute("_portal_startnoInternalFail")).intValue();
		if (command.equals("previous"))
			if (startno == objVector.size()) {
				x = startno % cntItemPage;
				if (x > 0) {
					startno -= x;
					startno -= cntItemPage;
				} else {
					startno -= cntItemPage * 2;
					if (startno < 0)
						startno = 0;
				}
			} else {
				startno -= cntItemPage * 2;
				if (startno < 0)
					startno = 0;
			}
		else if (command.equals("first"))
			startno = 0;

		else if (command.equals("last"))
			x = cntItemPage;
		else if (command.equals("back"))
			if (startno == objVector.size()) {
				x = startno % cntItemPage;
				if (x == 0)
					x = cntItemPage;
				startno -= x;
			} else {
				startno -= cntItemPage;
				if (startno < 0)
					startno = 0;

			}

		Vector moduleVector = new Vector();
		int i = 0;
		int cnt = 0;
		if (objVector.size() > 0) {
			cnt = 0;
			for (i = startno; (cnt < cntItemPage) && (i < objVector.size());) {
				moduleVector.addElement(objVector.elementAt(i));

				++i;
				++cnt;
			}

		}

		session.setAttribute("_portal_moduleVectorInternalFail", moduleVector);

		this.context.put("startnoInt", new Integer(startno));
		this.context.put("totalInt", new Integer(objVector.size()));

		startno = i;

		session.setAttribute("_portal_startnoInternalFail",
				new Integer(startno));

	}

	public void updateFlagHarta(HttpSession session) throws Exception {

		String id_permohonan_baru = getParam("idpermohonan_baru");
		String id_permohonan_lama = getParam("idpermohonan");
		String id_permohonan = "";
		String flag_daftar = "";
		if (id_permohonan_baru.equals("")) {
			id_permohonan = id_permohonan_lama;
			flag_daftar = "LAMA";
		} else {
			id_permohonan = id_permohonan_baru;
			flag_daftar = "BARU";
		}

		String[] id_hta_check = this.request.getParameterValues("id_hta_check");
		String[] id_perintahhtaobmst_check = this.request
				.getParameterValues("id_perintahhtaobmst");
		if (id_hta_check != null) {
			for (int i = 0; i < id_hta_check.length; i++) {
				myLogger.info("id_hta_check :" + id_hta_check[i]);
				String flag_pa_hta_temp = "flag_pa_hta" + (i + 1);
				String flag_pt_hta_temp = "flag_pt_hta" + (i + 1);
				String flag_selesai_hta_temp = "flag_selesai_hta" + (i + 1);
				myLogger
						.info("flag_pa_hta_temp :" + getParam(flag_pa_hta_temp));
				myLogger
						.info("flag_pt_hta_temp :" + getParam(flag_pt_hta_temp));
				myLogger.info("flag_selesai_hta_temp :"
						+ getParam(flag_selesai_hta_temp));
				String fpa = getParam(flag_pa_hta_temp);
				String fpt = getParam(flag_pt_hta_temp);
				String fselesai = getParam(flag_selesai_hta_temp);
				if (fpa.equals("")) {
					fpa = "T";
				}
				if (fpt.equals("")) {
					fpt = "T";
				}
				if (fselesai.equals("")) {
					fselesai = "T";
				}
				FrmSemakHartaSek17.updateHta(session, id_hta_check[i], fpa,
						fpt, fselesai, (String) session
								.getAttribute("_ekptg_user_id"),
						id_perintahhtaobmst_check[i], id_permohonan,
						flag_daftar);
			}
		}

		String[] id_ha_check = this.request.getParameterValues("id_ha_check");
		String[] id_perintahhaobmst_check = this.request
				.getParameterValues("id_perintahhaobmst");
		if (id_ha_check != null) {
			for (int i = 0; i < id_ha_check.length; i++) {
				myLogger.info("id_ha_check :" + id_ha_check[i]);
				String flag_pa_ha_temp = "flag_pa_ha" + (i + 1);
				String flag_pt_ha_temp = "flag_pt_ha" + (i + 1);
				String flag_selesai_ha_temp = "flag_selesai_ha" + (i + 1);
				myLogger.info("flag_pa_ha_temp :" + getParam(flag_pa_ha_temp));
				myLogger.info("flag_pt_ha_temp :" + getParam(flag_pt_ha_temp));
				myLogger.info("flag_selesai_ha_temp :"
						+ getParam(flag_selesai_ha_temp));
				String fpa = getParam(flag_pa_ha_temp);
				String fpt = getParam(flag_pt_ha_temp);
				String fselesai = getParam(flag_selesai_ha_temp);
				if (fpa.equals("")) {
					fpa = "T";
				}
				if (fpt.equals("")) {
					fpt = "T";
				}
				if (fselesai.equals("")) {
					fselesai = "T";
				}
				FrmSemakHartaSek17
						.updateHa(
								session,
								id_ha_check[i],
								fpa,
								fpt,
								fselesai,
								(String) session.getAttribute("_ekptg_user_id"),
								id_perintahhaobmst_check[i], id_permohonan,
								flag_daftar);
			}
		}

	}

	private void updateHtaamX(HttpSession session) throws Exception {

		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));

		// h.put("idhta", Integer.parseInt(getParam("idhtaamXid")));
		h.put("idhta", getParam("idhtaamid"));
		// String idhtaam=Integer.parseInt(getParam("idhtaamid"));

		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaamX"));
		/*
		 * if (getParam("txtNilaiTarikhMohonHtaaX") != "") {
		 * h.put("nilai_Hta_memohon", Double
		 * .parseDouble(getParam("txtNilaiTarikhMohonHtaaX"))); } else {
		 * h.put("nilai_Hta_memohon", 0.0); }
		 * 
		 * if (getParam("txtNilaiTarikhMatiHtaamX") != "") {
		 * h.put("nilai_Hta_mati", Double
		 * .parseDouble(getParam("txtNilaiTarikhMatiHtaamX"))); } else {
		 * h.put("nilai_Hta_mati", 0.0); }
		 */

		h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHtaaX"));
		h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamX"));

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
			h.put("basimati", getParam("txtBahagianSimati1X"));
		} else {
			h.put("basimati", "0");
		}

		if (getParam("txtBahagianSimati2X") != "") {
			h.put("bbsimati", getParam("txtBahagianSimati2X"));
		} else {
			h.put("bbsimati", "0");
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
		// h.put("bandarpemaju",getParam("txtBandarPemaju1HtaamX"));
		if (getParam("txtBandarPemaju1HtaamX").equals("")) {
			h.put("bandarpemaju", 0);
		} else {
			h.put("bandarpemaju", Integer
					.parseInt(getParam("txtBandarPemaju1HtaamX")));
		}

		if (getParam("socNegeriPemajuHtaamX").equals("")) {
			h.put("negeripemaju", 0);
		} else {
			h.put("negeripemaju", Integer
					.parseInt(getParam("socNegeriPemajuHtaamX")));
		}

		h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));

		h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		// h.put("bandarhta",getParam("txtBandarHartaHtaamX"));

		if (getParam("txtBandarHartaHtaamX").equals("")) {
			h.put("bandarhta", "0");
		} else {
			h.put("bandarhta", getParam("txtBandarHartaHtaamX"));
		}
			
		h.put("noperjanjian", getParam("txtNoPerjanjianHtaamX"));
		h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));

		h.put("namarancangan", getParam("txtNamaRancanganHtaamX"));
		h.put("noroh", getParam("txtNoRohHtaamX"));
		h.put("nolot", getParam("txtLotIdHtaamX"));
		h.put("nocagaran", getParam("txtNoCagaranX"));

		h.put("jeniskepentingan", getParam("txtJenisKepentinganX"));

		/*
		 * String flag1 = getParam("radioHtaamViewX1"); String flag2 =
		 * getParam("radioHtaamViewX2"); String flag3 =
		 * getParam("radioHtaamViewX3");
		 */

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);

		// /fungsi baru untuk penambahbaikkan...boleh kemaskini jenis HTATH
		String radioHtaamViewX_update = getParam("radioHtaamViewX_update");
		myLogger.info("radioHtaamViewX_update :" + radioHtaamViewX_update);
		String no_flag = "1";
		if (radioHtaamViewX_update.equals("1")) {
			no_flag = "1";
		}
		if (radioHtaamViewX_update.equals("2")) {
			no_flag = "2";
		}
		if (radioHtaamViewX_update.equals("3")) {
			no_flag = "3";
		}
		h.put("flag", no_flag);

		String flag_tukar_jenis_hta = getParam("flag_tukar_jenis_hta");
		if (flag_tukar_jenis_hta.equals("ADA")) {
			h.put("jenis_Hta", "Y");

		} else if (flag_tukar_jenis_hta.equals("TIADA")) {
			h.put("jenis_Hta", "T");
			h.put("flag", no_flag);
		} else {
			h.put("jenis_Hta", "T");
			// h.put("flag", "1");

		}
		logic_internal.updateHtaamX(h);

	}

	private void updateHtaam(HttpSession session) throws Exception {

		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("idhta", getParam("id_htaam"));
		h.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaamUp"));
		/*
		 * if (getParam("txtNilaiTarikhMohonHt") != "") {
		 * h.put("nilai_Hta_memohon", Double
		 * .parseDouble(getParam("txtNilaiTarikhMohonHt"))); } else {
		 * h.put("nilai_Hta_memohon", 0.0); }
		 */
		h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHt"));
		/*
		 * if (getParam("txtNilaiTarikhMatiHtaamUpd") != "") {
		 * h.put("nilai_Hta_mati", Double
		 * .parseDouble(getParam("txtNilaiTarikhMatiHtaamUpd"))); } else {
		 * h.put("nilai_Hta_mati", 0.0); }
		 */
		h.put("nilai_Hta_mati", getParam("txtNilaiTarikhMatiHtaamUpd"));
		if (getParam("socKategoriTanahHtaamUp") != "") {
			h.put("kategori", Integer
					.parseInt(getParam("socKategoriTanahHtaamUp")));
		} else {
			h.put("kategori", 0);
		}
		if (getParam("socJenisHakmilikHtaamUp") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(getParam("socJenisHakmilikHtaamUp")));
		} else {
			h.put("jenishakmilik", 0);
		}
		h.put("pemilikan", getParam("socStatusPemilikanHtaamUpd"));
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
		h.put("luashmp", getParam("txtLuasHMpHtaamUpd"));
		h.put("luasasal", getParam("txtLuasAsalHtaamUpd"));
		h.put("nopajakan", getParam("txtNoPajakanUp"));
		h.put("jenistanah", getParam("socJenisTanahHtaamUpd"));
		// Azam change on 30/4/2010
		// change int to String utk cater for 14 digit bahagian simati
		// pembawah dan pengatas
		if (getParam("txtBahagianSimati1Up") != "") {
			h.put("basimati", getParam("txtBahagianSimati1Up"));
		} else {
			h.put("basimati", "0");
		}
		if (getParam("txtBahagianSimati2Up") != "") {
			h.put("bbsimati", getParam("txtBahagianSimati2Up"));
		} else {
			h.put("bbsimati", "0");
		}
		if (getParam("socJenisLuasHtaamUpd") != "") {
			h.put("jenisluas", Integer
					.parseInt(getParam("socJenisLuasHtaamUpd")));
		} else {
			h.put("jenisluas", 0);
		}
		h.put("tanggungan", getParam("txtTanggunganHtaamUp"));
		h.put("catatan", getParam("txtCatatanHt"));
		h.put("noperserahan", getParam("txtNoPersHtaamUp"));
		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);

		// /fungsi baru untuk penambahbaikkan...boleh kemaskini jenis HTATH
		String radioHtaamViewX_update = getParam("radioHtaamViewX_update");
		myLogger.info("radioHtaamViewX_update :" + radioHtaamViewX_update);
		String no_flag = "1";
		if (radioHtaamViewX_update.equals("1")) {
			no_flag = "1";
		}
		if (radioHtaamViewX_update.equals("2")) {
			no_flag = "2";
		}
		if (radioHtaamViewX_update.equals("3")) {
			no_flag = "3";
		}
		h.put("flag", no_flag);

		String flag_tukar_jenis_hta = getParam("flag_tukar_jenis_hta");
		if (flag_tukar_jenis_hta.equals("ADA")) {
			h.put("jenis_Hta", "Y");
		} else if (flag_tukar_jenis_hta.equals("TIADA")) {
			h.put("jenis_Hta", "T");
			h.put("flag", no_flag);
		} else {
			h.put("jenis_Hta", "Y");
			h.put("flag", "");
		}
		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		
		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", getParam("txtSekatan") == null ? ""
				: getParam("txtSekatan"));
		h.put("syaratNyata", getParam("txtSyaratNyata") == null ? ""
				: getParam("txtSyaratNyata"));
		
		logic_internal.updateHtaam(h);
	}

	/*
	 * private void simpanpilihanHTA(HttpSession session,String bolehsimpan)
	 * throws Exception {
	 * 
	 * String per_mati = getParam("id_Permohonansimati"); String user_id =
	 * (String) session.getAttribute("_ekptg_user_id");
	 * logic_C.clear_pilihanHTAMainDelete(per_mati,"Y");
	 * logic_C.clear_pilihanHAMainDelete(per_mati,"");
	 * myLogger.info("NAK SIMPAN PILIHAN OB"); String[] ids =
	 * this.request.getParameterValues("ids"); myLogger.info("ids :"+ids);
	 * String[] idsx = this.request.getParameterValues("idsx");
	 * myLogger.info("idsx :"+idsx);
	 * myLogger.info("ids temp :"+getParam("ids"));
	 * myLogger.info("per_mati :"+per_mati);
	 * myLogger.info("bolehsimpan :"+bolehsimpan); String temp = null; if (ids
	 * != null) { for (int i = 0; i < ids.length; i++) { if
	 * (bolehsimpan.equals("yes")) {
	 * 
	 * long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
	 * logic_C.pilihanAdd(per_mati,ids[i],user_id,id_Pilihanhta); String
	 * temp_id_ob = "main_ob"+ids[i]; String[] ids_ob =
	 * this.request.getParameterValues(temp_id_ob);
	 * 
	 * if (ids_ob != null) { for (int y = 0; y < ids_ob.length; y++) { String
	 * temp_id_ob_sub = "check_ob"+ids[i]+ids_ob[y]; String[] ids_ob_sub =
	 * this.request.getParameterValues(temp_id_ob_sub); String ids_ob_sub1 =
	 * "",ids_ob_sub2 = "",ids_ob_sub3 = "",ids_ob_sub4 = ""; if (ids_ob_sub !=
	 * null) {
	 * 
	 * for (int x = 0; x < ids_ob_sub.length; x++) {
	 * 
	 * if(x==0) { ids_ob_sub1 = ids_ob_sub[x]; } if(x==1) { ids_ob_sub2 =
	 * ids_ob_sub[x]; } if(x==2) { ids_ob_sub3 = ids_ob_sub[x]; } if(x==3) {
	 * ids_ob_sub4 = ids_ob_sub[x]; }
	 * 
	 * } }
	 * 
	 * if(!ids_ob_sub1.equals("") || !ids_ob_sub2.equals("") ||
	 * !ids_ob_sub3.equals("") || !ids_ob_sub4.equals("")) {
	 * logic_C.pilihanAddOBHTA
	 * (per_mati,ids_ob[y],ids_ob_sub1,ids_ob_sub2,ids_ob_sub3
	 * ,ids_ob_sub4,(String
	 * )session.getAttribute("_ekptg_user_id"),id_Pilihanhta+""); } } } } } }
	 * 
	 * 
	 * this.context.put("tambahharta", "yes"); this.context.put("kembaliharta",
	 * "yes"); this.context.put("nowpast", "past");
	 * //this.context.put("appear_skrin_info", "simpan_pilihan");
	 * 
	 * }
	 * 
	 * private void simpanpilihanHTAX(HttpSession session,String bolehsimpan)
	 * throws Exception { String per_mati = getParam("id_Permohonansimati");
	 * String user_id = (String) session .getAttribute("_ekptg_user_id");
	 * logic_C.clear_pilihanHTAMainDelete(per_mati,"T");
	 * myLogger.info("NAK SIMPAN PILIHAN OB"); String[] ids =
	 * this.request.getParameterValues("ids"); myLogger.info("ids :"+ids);
	 * String temp = null; if (ids != null) { for (int i = 0; i < ids.length;
	 * i++) { if (bolehsimpan.equals("yes")) {
	 * //logic_C.clear_pilihanHTADelete(per_mati
	 * ,ids[i],(String)session.getAttribute("_ekptg_user_id")); long
	 * id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
	 * logic_C.pilihanAdd(per_mati,ids[i],user_id,id_Pilihanhta); String
	 * temp_id_ob = "main_ob"+ids[i]; String[] ids_ob =
	 * this.request.getParameterValues(temp_id_ob);
	 * myLogger.info("temp_id_ob :"+temp_id_ob);
	 * myLogger.info("main_ob :"+ids_ob); if (ids_ob != null) { for (int y = 0;
	 * y < ids_ob.length; y++) { String temp_id_ob_sub =
	 * "check_ob"+ids[i]+ids_ob[y]; String[] ids_ob_sub =
	 * this.request.getParameterValues(temp_id_ob_sub); String ids_ob_sub1 =
	 * "",ids_ob_sub2 = "",ids_ob_sub3 = "",ids_ob_sub4 = ""; if (ids_ob_sub !=
	 * null) {
	 * 
	 * for (int x = 0; x < ids_ob_sub.length; x++) {
	 * 
	 * if(x==0) { ids_ob_sub1 = ids_ob_sub[x]; } if(x==1) { ids_ob_sub2 =
	 * ids_ob_sub[x]; } if(x==2) { ids_ob_sub3 = ids_ob_sub[x]; } if(x==3) {
	 * ids_ob_sub4 = ids_ob_sub[x]; }
	 * 
	 * } }logic_C.pilihanAddOBHTAX(per_mati,ids_ob[y],ids_ob_sub1,ids_ob_sub2,
	 * ids_ob_sub3
	 * ,ids_ob_sub4,(String)session.getAttribute("_ekptg_user_id"),id_Pilihanhta
	 * +""); } } } } }
	 * 
	 * String radioX1 = getParam("radioHtaamViewX1"); String radioX2 =
	 * getParam("radioHtaamViewX2"); String radioX3 =
	 * getParam("radioHtaamViewX3"); this.context.put("tambahbutton","yes");
	 * this.context.put("kembalibutton","yes");
	 * this.context.put("nowpast","past");
	 * //this.context.put("appear_skrin_info", "simpan_pilihan"); }
	 * 
	 * private void simpanpilihanHA(HttpSession session,String bolehsimpan)
	 * throws Exception { String per_mati = getParam("id_Permohonansimati");
	 * String user_id = (String) session .getAttribute("_ekptg_user_id");
	 * logic_C.clear_pilihanHAMainDelete(per_mati,"");
	 * logic_C.clear_pilihanHTAMainDelete(per_mati,"Y");
	 * myLogger.info("NAK SIMPAN PILIHAN OB"); String[] ids =
	 * this.request.getParameterValues("ids"); myLogger.info("ids :"+ids);
	 * 
	 * String temp = null; if (ids != null) { for (int i = 0; i < ids.length;
	 * i++) { if (bolehsimpan.equals("yes")) { long id_Pilihanha =
	 * DB.getNextID("TBLPPKPILIHANHA_SEQ");
	 * logic_C.pilihanAdd_HA(per_mati,ids[i],user_id,id_Pilihanha); String
	 * temp_id_ob = "main_ob"+ids[i]; String[] ids_ob =
	 * this.request.getParameterValues(temp_id_ob);
	 * myLogger.info("temp_id_ob :"+temp_id_ob);
	 * myLogger.info("ids_ob :"+ids_ob); if (ids_ob != null) { for (int y = 0; y
	 * < ids_ob.length; y++) { String temp_id_ob_sub =
	 * "check_ob"+ids[i]+ids_ob[y]; String[] ids_ob_sub =
	 * this.request.getParameterValues(temp_id_ob_sub); String ids_ob_sub1 =
	 * "",ids_ob_sub2 = "",ids_ob_sub3 = "",ids_ob_sub4 = ""; if (ids_ob_sub !=
	 * null) {
	 * 
	 * for (int x = 0; x < ids_ob_sub.length; x++) {
	 * 
	 * if(x==0) { ids_ob_sub1 = ids_ob_sub[x]; } if(x==1) { ids_ob_sub2 =
	 * ids_ob_sub[x]; } if(x==2) { ids_ob_sub3 = ids_ob_sub[x]; } if(x==3) {
	 * ids_ob_sub4 = ids_ob_sub[x]; }
	 * 
	 * } } if(!ids_ob_sub1.equals("") || !ids_ob_sub2.equals("") ||
	 * !ids_ob_sub3.equals("") || !ids_ob_sub4.equals("")) {
	 * logic_C.pilihanAddOBHA
	 * (per_mati,ids_ob[y],ids_ob_sub1,ids_ob_sub2,ids_ob_sub3
	 * ,ids_ob_sub4,(String
	 * )session.getAttribute("_ekptg_user_id"),id_Pilihanha+""); } } } } } }
	 * 
	 * 
	 * this.context.put("showbuttonkembali", "yes");
	 * this.context.put("showbuttontambah", "yes");
	 * this.context.put("EventStatus", 1); this.context.put("nowpast", "past");
	 * //this.context.put("appear_skrin_info", "simpan_pilihan"); }
	 */

	private void simpanpilihanHTA(HttpSession session, String bolehsimpan)
			throws Exception {

		String per_mati = getParam("id_Permohonansimati");
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		logic_C.clear_pilihanHTAMainDelete(per_mati, "Y");
		// logic_C.clear_pilihanHAMainDelete(per_mati,"");
		myLogger.info("NAK SIMPAN PILIHAN OB");
		String[] ids = this.request.getParameterValues("ids");
		myLogger.info("ids :" + ids);
		String[] idsx = this.request.getParameterValues("idsx");
		myLogger.info("idsx :" + idsx);
		myLogger.info("ids temp :" + getParam("ids"));
		myLogger.info("per_mati :" + per_mati);
		myLogger.info("bolehsimpan :" + bolehsimpan);

		// check ada x perintah kuasa tadbir
		int check_pkt = 0;
		int check_pkt_true = 0;
		String flag_perlu_batal_kt = "";
		String[] ids_check = this.request.getParameterValues("ids_check");
		if (ids_check != null) {
			for (int ii = 0; ii < ids_check.length; ii++) {
				String temp_jenis_perintah_check = "jenis_perintah_harta"
						+ ids_check[ii];
				String jenis_perintah_check = getParam(temp_jenis_perintah_check);
				if (jenis_perintah_check.equals("2")) {
					check_pkt++;
				}
			}
		}
		// tutup

		String temp = null;
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (bolehsimpan.equals("yes")) {

					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					String flag_daftar = "flag_daftar" + ids[i];
					logic_C.pilihanAdd(per_mati, ids[i], user_id,
							id_Pilihanhta, getParam(flag_daftar));
					String temp_id_ob = "main_ob" + ids[i];
					String[] ids_ob = this.request
							.getParameterValues(temp_id_ob);
					// buka
					String temp_jenis_perintah = "jenis_perintah_harta"
							+ ids[i];
					String jenis_perintah = getParam(temp_jenis_perintah);
					if (jenis_perintah.equals("2")) {
						check_pkt_true++;
					}
					// tutup

					if (ids_ob != null) {
						for (int y = 0; y < ids_ob.length; y++) {
							String temp_id_ob_sub = "check_ob" + ids[i]
									+ ids_ob[y];

							String[] ids_ob_sub = this.request
									.getParameterValues(temp_id_ob_sub);
							String ids_ob_sub1 = "", ids_ob_sub2 = "", ids_ob_sub3 = "", ids_ob_sub4 = "";
							if (ids_ob_sub != null) {

								for (int x = 0; x < ids_ob_sub.length; x++) {

									if (x == 0) {
										ids_ob_sub1 = ids_ob_sub[x];
									}
									if (x == 1) {
										ids_ob_sub2 = ids_ob_sub[x];
									}
									if (x == 2) {
										ids_ob_sub3 = ids_ob_sub[x];
									}
									if (x == 3) {
										ids_ob_sub4 = ids_ob_sub[x];
									}

								}
							}

							String flag_daftar_ob = "flag_daftar_ob" + ids[i]
									+ ids_ob[y];
							logic_C.pilihanAddOBHTA(jenis_perintah, per_mati,
									ids_ob[y], ids_ob_sub1, ids_ob_sub2,
									ids_ob_sub3, ids_ob_sub4, (String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanhta + "",
									getParam(flag_daftar_ob), "");

						}
					}
				}

			}
		}
		// buka
		myLogger.info("check_pkt :" + check_pkt);
		myLogger.info("check_pkt_true :" + check_pkt_true);

		if (check_pkt > 0 && check_pkt_true == 0) {
			flag_perlu_batal_kt = "unbatalkan";
		} else if (check_pkt > 0 && check_pkt_true > 0) {
			flag_perlu_batal_kt = "batalkan";
		}
		myLogger.info("flag_perlu_batal_kt:" + flag_perlu_batal_kt);

		if (!flag_perlu_batal_kt.equals("")) {
			String bkp = getParam("bkp");
			String lp = getParam("lp");
			String bpa = getParam("bpa");
			String lpa = getParam("lpa");
			Vector listHartaTakAlihdulu = new Vector();
			listHartaTakAlihdulu = logic_internal.setDataSemuaHartaTakAlihdulu(
					per_mati, bkp, lp, bpa, lpa);
			for (int i = 0; i < listHartaTakAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaTakAlihdulu.get(i);
				myLogger.info("GET ID HTA :" + h.get("idhta").toString());
				logic_C.clear_pilihanHTAMainDelete_byId(h.get("idhta")
						.toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					logic_C.pilihanAdd(per_mati, h.get("idhta").toString(),
							user_id, id_Pilihanhta, "");

					Vector listHartaTakAlihduluOB = new Vector();
					logic_internal.setDataOBHTAdulu(per_mati, bkp, lp, bpa,
							lpa, "");
					listHartaTakAlihduluOB = logic_internal.getDataOBHTAdulu();
					for (int ix = 0; ix < listHartaTakAlihduluOB.size(); ix++) {
						Hashtable hx = (Hashtable) listHartaTakAlihduluOB
								.get(ix);
						myLogger.info("GET ID HTA OB :"
								+ hx.get("ID_HTA").toString());
						if (h.get("idhta").toString().equals(
								hx.get("ID_HTA").toString())) {
							myLogger.info("GET ID OB OB :"
									+ hx.get("ID_OB").toString());
							logic_C.pilihanAddOBHTA("", per_mati, hx.get(
									"ID_OB").toString(), "", "", "", "",
									(String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanhta + "", "", "");
						}
					}
				}
			}

			Vector listHartaAlihdulu = new Vector();
			listHartaAlihdulu = logic_internal.setDataSemuaHartaAlihdulu(
					per_mati, bkp, lp, bpa, lpa);
			for (int i = 0; i < listHartaAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaAlihdulu.get(i);
				myLogger.info("GET ID HA :" + h.get("id_Ha").toString());
				logic_C.clear_pilihanHAMainDelete_byId(h.get("id_Ha")
						.toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
					logic_C.pilihanAdd_HA(per_mati, h.get("id_Ha").toString(),
							user_id, id_Pilihanha, "");

					Vector listHartaAlihduluOB = new Vector();
					logic_internal.setDataOBHAdulu(per_mati, bkp, lp, bpa, lpa,
							"");
					listHartaAlihduluOB = logic_internal.getDataOBHAdulu();
					for (int iy = 0; iy < listHartaAlihduluOB.size(); iy++) {
						Hashtable hy = (Hashtable) listHartaAlihduluOB.get(iy);
						myLogger.info("GET ID HA OB :"
								+ hy.get("ID_HA").toString());
						if (h.get("id_Ha").toString().equals(
								hy.get("ID_HA").toString())) {
							myLogger.info("GET ID OB OB :"
									+ hy.get("ID_OB").toString());
							logic_C.pilihanAddOBHA("", per_mati, hy
									.get("ID_OB").toString(), "", "", "", "",
									(String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanha + "", "", "");
						}
					}
				}
			}

		}
		// tutup
		this.context.put("tambahharta", "yes");
		this.context.put("kembaliharta", "yes");
		this.context.put("nowpast", "past");
		// this.context.put("appear_skrin_info", "simpan_pilihan");

	}

	private void simpanpilihanHTAX(HttpSession session, String bolehsimpan)
			throws Exception {
		String per_mati = getParam("id_Permohonansimati");
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		logic_C.clear_pilihanHTAMainDelete(per_mati, "T");
		myLogger.info("NAK SIMPAN PILIHAN OB");
		String[] ids = this.request.getParameterValues("ids");
		myLogger.info("ids :" + ids);

		// check ada x perintah kuasa tadbir
		int check_pkt = 0;
		int check_pkt_true = 0;
		String flag_perlu_batal_kt = "";
		String[] ids_check = this.request.getParameterValues("ids_check");
		if (ids_check != null) {
			for (int ii = 0; ii < ids_check.length; ii++) {
				String temp_jenis_perintah_check = "jenis_perintah_harta"
						+ ids_check[ii];
				String jenis_perintah_check = getParam(temp_jenis_perintah_check);
				if (jenis_perintah_check.equals("2")) {
					check_pkt++;
				}
			}
		}
		// tutup

		String temp = null;
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (bolehsimpan.equals("yes")) {
					// logic_C.clear_pilihanHTADelete(per_mati,ids[i],(String)session.getAttribute("_ekptg_user_id"));
					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					String flag_daftar = "flag_daftar" + ids[i];
					logic_C.pilihanAdd(per_mati, ids[i], user_id,
							id_Pilihanhta, getParam(flag_daftar));
					String temp_id_ob = "main_ob" + ids[i];
					String[] ids_ob = this.request
							.getParameterValues(temp_id_ob);
					myLogger.info("temp_id_ob :" + temp_id_ob);
					myLogger.info("main_ob :" + ids_ob);

					// buka
					String temp_jenis_perintah = "jenis_perintah_harta"
							+ ids[i];
					String jenis_perintah = getParam(temp_jenis_perintah);
					if (jenis_perintah.equals("2")) {
						check_pkt_true++;
					}
					// tutup

					if (ids_ob != null) {
						for (int y = 0; y < ids_ob.length; y++) {
							String temp_id_ob_sub = "check_ob" + ids[i]
									+ ids_ob[y];
							String[] ids_ob_sub = this.request
									.getParameterValues(temp_id_ob_sub);
							String ids_ob_sub1 = "", ids_ob_sub2 = "", ids_ob_sub3 = "", ids_ob_sub4 = "";
							if (ids_ob_sub != null) {

								for (int x = 0; x < ids_ob_sub.length; x++) {

									if (x == 0) {
										ids_ob_sub1 = ids_ob_sub[x];
									}
									if (x == 1) {
										ids_ob_sub2 = ids_ob_sub[x];
									}
									if (x == 2) {
										ids_ob_sub3 = ids_ob_sub[x];
									}
									if (x == 3) {
										ids_ob_sub4 = ids_ob_sub[x];
									}

								}
							}
							logic_C.pilihanAddOBHTAX(per_mati, ids_ob[y],
									ids_ob_sub1, ids_ob_sub2, ids_ob_sub3,
									ids_ob_sub4, (String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanhta + "");
						}
					}
				}
			}
		}

		// buka
		myLogger.info("check_pkt :" + check_pkt);
		myLogger.info("check_pkt_true :" + check_pkt_true);

		if (check_pkt > 0 && check_pkt_true == 0) {
			flag_perlu_batal_kt = "unbatalkan";
		} else if (check_pkt > 0 && check_pkt_true > 0) {
			flag_perlu_batal_kt = "batalkan";
		}
		myLogger.info("flag_perlu_batal_kt:" + flag_perlu_batal_kt);

		if (!flag_perlu_batal_kt.equals("")) {
			String bkp = getParam("bkp");
			String lp = getParam("lp");
			String bpa = getParam("bpa");
			String lpa = getParam("lpa");
			Vector listHartaTakAlihdulu = new Vector();
			listHartaTakAlihdulu = logic_internal.setDataSemuaHartaTakAlihdulu(
					per_mati, bkp, lp, bpa, lpa);
			for (int i = 0; i < listHartaTakAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaTakAlihdulu.get(i);
				myLogger.info("GET ID HTA :" + h.get("idhta").toString());
				logic_C.clear_pilihanHTAMainDelete_byId(h.get("idhta")
						.toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					logic_C.pilihanAdd(per_mati, h.get("idhta").toString(),
							user_id, id_Pilihanhta, "");

					Vector listHartaTakAlihduluOB = new Vector();
					logic_internal.setDataOBHTAdulu(per_mati, bkp, lp, bpa,
							lpa, "");
					listHartaTakAlihduluOB = logic_internal.getDataOBHTAdulu();
					for (int ix = 0; ix < listHartaTakAlihduluOB.size(); ix++) {
						Hashtable hx = (Hashtable) listHartaTakAlihduluOB
								.get(ix);
						myLogger.info("GET ID HTA OB :"
								+ hx.get("ID_HTA").toString());
						if (h.get("idhta").toString().equals(
								hx.get("ID_HTA").toString())) {
							myLogger.info("GET ID OB OB :"
									+ hx.get("ID_OB").toString());
							logic_C.pilihanAddOBHTA("", per_mati, hx.get(
									"ID_OB").toString(), "", "", "", "",
									(String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanhta + "", "", "");
						}
					}
				}
			}

			Vector listHartaAlihdulu = new Vector();
			listHartaAlihdulu = logic_internal.setDataSemuaHartaAlihdulu(
					per_mati, bkp, lp, bpa, lpa);
			for (int i = 0; i < listHartaAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaAlihdulu.get(i);
				myLogger.info("GET ID HA :" + h.get("id_Ha").toString());
				logic_C.clear_pilihanHAMainDelete_byId(h.get("id_Ha")
						.toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
					logic_C.pilihanAdd_HA(per_mati, h.get("id_Ha").toString(),
							user_id, id_Pilihanha, "");

					Vector listHartaAlihduluOB = new Vector();
					logic_internal.setDataOBHAdulu(per_mati, bkp, lp, bpa, lpa,
							"");
					listHartaAlihduluOB = logic_internal.getDataOBHAdulu();
					for (int iy = 0; iy < listHartaAlihduluOB.size(); iy++) {
						Hashtable hy = (Hashtable) listHartaAlihduluOB.get(iy);
						myLogger.info("GET ID HA OB :"
								+ hy.get("ID_HA").toString());
						if (h.get("id_Ha").toString().equals(
								hy.get("ID_HA").toString())) {
							myLogger.info("GET ID OB OB :"
									+ hy.get("ID_OB").toString());
							logic_C.pilihanAddOBHA("", per_mati, hy
									.get("ID_OB").toString(), "", "", "", "",
									(String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanha + "", "", "");
						}
					}
				}
			}

		}
		// tutup

		String radioX1 = getParam("radioHtaamViewX1");
		String radioX2 = getParam("radioHtaamViewX2");
		String radioX3 = getParam("radioHtaamViewX3");
		this.context.put("tambahbutton", "yes");
		this.context.put("kembalibutton", "yes");
		this.context.put("nowpast", "past");
		// this.context.put("appear_skrin_info", "simpan_pilihan");
	}

	private void simpanpilihanHA(HttpSession session, String bolehsimpan)
			throws Exception {
		String per_mati = getParam("id_Permohonansimati");
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		logic_C.clear_pilihanHAMainDelete(per_mati, "");
		// logic_C.clear_pilihanHTAMainDelete(per_mati,"Y");
		myLogger.info("NAK SIMPAN PILIHAN OB");
		String[] ids = this.request.getParameterValues("ids");
		myLogger.info("ids :" + ids);

		// check ada x perintah kuasa tadbir
		int check_pkt = 0;
		int check_pkt_true = 0;
		String flag_perlu_batal_kt = "";
		String[] ids_check = this.request.getParameterValues("ids_check");
		if (ids_check != null) {
			for (int ii = 0; ii < ids_check.length; ii++) {
				String temp_jenis_perintah_check = "jenis_perintah_harta"
						+ ids_check[ii];
				String jenis_perintah_check = getParam(temp_jenis_perintah_check);
				if (jenis_perintah_check.equals("2")) {
					check_pkt++;
				}
			}
		}
		// tutup

		String temp = null;
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (bolehsimpan.equals("yes")) {
					long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
					String flag_daftar = "flag_daftar" + ids[i];
					logic_C.pilihanAdd_HA(per_mati, ids[i], user_id,
							id_Pilihanha, getParam(flag_daftar));
					String temp_id_ob = "main_ob" + ids[i];
					String[] ids_ob = this.request
							.getParameterValues(temp_id_ob);
					myLogger.info("temp_id_ob :" + temp_id_ob);
					myLogger.info("ids_ob :" + ids_ob);

					// buka
					String temp_jenis_perintah = "jenis_perintah_harta"
							+ ids[i];
					String jenis_perintah = getParam(temp_jenis_perintah);
					if (jenis_perintah.equals("2")) {
						check_pkt_true++;
					}
					// tutup

					if (ids_ob != null) {
						for (int y = 0; y < ids_ob.length; y++) {
							String temp_id_ob_sub = "check_ob" + ids[i]
									+ ids_ob[y];
							String[] ids_ob_sub = this.request
									.getParameterValues(temp_id_ob_sub);
							String ids_ob_sub1 = "", ids_ob_sub2 = "", ids_ob_sub3 = "", ids_ob_sub4 = "";
							if (ids_ob_sub != null) {

								for (int x = 0; x < ids_ob_sub.length; x++) {

									if (x == 0) {
										ids_ob_sub1 = ids_ob_sub[x];
									}
									if (x == 1) {
										ids_ob_sub2 = ids_ob_sub[x];
									}
									if (x == 2) {
										ids_ob_sub3 = ids_ob_sub[x];
									}
									if (x == 3) {
										ids_ob_sub4 = ids_ob_sub[x];
									}

								}
							}
							if (!ids_ob_sub1.equals("")
									|| !ids_ob_sub2.equals("")
									|| !ids_ob_sub3.equals("")
									|| !ids_ob_sub4.equals("")) {
							}
							String flag_daftar_ob = "flag_daftar_ob" + ids[i]
									+ ids_ob[y];
							logic_C.pilihanAddOBHA("", per_mati, ids_ob[y],
									ids_ob_sub1, ids_ob_sub2, ids_ob_sub3,
									ids_ob_sub4, (String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanha + "",
									getParam(flag_daftar_ob), "");

						}
					}
				}
			}
		}

		// buka
		myLogger.info("check_pkt :" + check_pkt);
		myLogger.info("check_pkt_true :" + check_pkt_true);

		if (check_pkt > 0 && check_pkt_true == 0) {
			flag_perlu_batal_kt = "unbatalkan";
		} else if (check_pkt > 0 && check_pkt_true > 0) {
			flag_perlu_batal_kt = "batalkan";
		}
		myLogger.info("flag_perlu_batal_kt:" + flag_perlu_batal_kt);

		if (!flag_perlu_batal_kt.equals("")) {
			String bkp = getParam("bkp");
			String lp = getParam("lp");
			String bpa = getParam("bpa");
			String lpa = getParam("lpa");
			Vector listHartaTakAlihdulu = new Vector();
			listHartaTakAlihdulu = logic_internal.setDataSemuaHartaTakAlihdulu(
					per_mati, bkp, lp, bpa, lpa);
			for (int i = 0; i < listHartaTakAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaTakAlihdulu.get(i);
				myLogger.info("GET ID HTA :" + h.get("idhta").toString());
				logic_C.clear_pilihanHTAMainDelete_byId(h.get("idhta")
						.toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					logic_C.pilihanAdd(per_mati, h.get("idhta").toString(),
							user_id, id_Pilihanhta, "");

					Vector listHartaTakAlihduluOB = new Vector();
					logic_internal.setDataOBHTAdulu(per_mati, bkp, lp, bpa,
							lpa, "");
					listHartaTakAlihduluOB = logic_internal.getDataOBHTAdulu();
					for (int ix = 0; ix < listHartaTakAlihduluOB.size(); ix++) {
						Hashtable hx = (Hashtable) listHartaTakAlihduluOB
								.get(ix);
						myLogger.info("GET ID HTA OB :"
								+ hx.get("ID_HTA").toString());
						if (h.get("idhta").toString().equals(
								hx.get("ID_HTA").toString())) {
							myLogger.info("GET ID OB OB :"
									+ hx.get("ID_OB").toString());
							logic_C.pilihanAddOBHTA("", per_mati, hx.get(
									"ID_OB").toString(), "", "", "", "",
									(String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanhta + "", "", "");
						}
					}
				}
			}

			Vector listHartaAlihdulu = new Vector();
			listHartaAlihdulu = logic_internal.setDataSemuaHartaAlihdulu(
					per_mati, bkp, lp, bpa, lpa);
			for (int i = 0; i < listHartaAlihdulu.size(); i++) {
				Hashtable h = (Hashtable) listHartaAlihdulu.get(i);
				myLogger.info("GET ID HA :" + h.get("id_Ha").toString());
				logic_C.clear_pilihanHAMainDelete_byId(h.get("id_Ha")
						.toString(), per_mati, "");

				if (flag_perlu_batal_kt.equals("batalkan")) {
					long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
					logic_C.pilihanAdd_HA(per_mati, h.get("id_Ha").toString(),
							user_id, id_Pilihanha, "");

					Vector listHartaAlihduluOB = new Vector();
					logic_internal.setDataOBHAdulu(per_mati, bkp, lp, bpa, lpa,
							"");
					listHartaAlihduluOB = logic_internal.getDataOBHAdulu();
					for (int iy = 0; iy < listHartaAlihduluOB.size(); iy++) {
						Hashtable hy = (Hashtable) listHartaAlihduluOB.get(iy);
						myLogger.info("GET ID HA OB :"
								+ hy.get("ID_HA").toString());
						if (h.get("id_Ha").toString().equals(
								hy.get("ID_HA").toString())) {
							myLogger.info("GET ID OB OB :"
									+ hy.get("ID_OB").toString());
							logic_C.pilihanAddOBHA("", per_mati, hy
									.get("ID_OB").toString(), "", "", "", "",
									(String) session
											.getAttribute("_ekptg_user_id"),
									id_Pilihanha + "", "", "");
						}
					}
				}
			}

		}
		// tutup

		this.context.put("showbuttonkembali", "yes");
		this.context.put("showbuttontambah", "yes");
		this.context.put("EventStatus", 1);
		this.context.put("nowpast", "past");
		// this.context.put("appear_skrin_info", "simpan_pilihan");
	}

	private void headerppk_baru(HttpSession session, String id_permohonan,
			String flag_permohonan, String id_fail, String flag_fail)
			throws Exception {
		// hashtable maklumat header
		this.context.put("headerppk", mainheader.getHeaderData(session,
				id_permohonan, flag_permohonan, id_fail, flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan, flag_permohonan,
				id_fail, flag_fail);
		this.context.put("list_sub_header", list_sub);
		this.context.put("flag_jenis_vm", "vtemplate");
		// Vector kenegaraan = null;
		// kenegaraan = mainheader.kenegaraan();
		// this.context.put("kenegaraan",kenegaraan);
	}

	private void headerppk_baru_default() {
		Hashtable headerppk = null;
		this.context.put("headerppk", "");
		this.context.put("list_sub_header", "");
		this.context.put("flag_jenis_vm", "vtemplate");
		this.context.put("kenegaraan", "");
	}

}
